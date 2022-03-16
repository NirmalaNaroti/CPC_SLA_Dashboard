package com.fragma.dao;

import com.fragma.config.ConfigurationHelper;
import com.fragma.dto.Controls;
import com.fragma.dto.MainDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Repository
public class ReportDao {

    static Logger LOG = LoggerFactory.getLogger(ReportDao.class);

    private final JdbcTemplate jdbcTemplate;
    private final ConfigurationHelper configurationHelper;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    int SLNo=0;

    @Autowired
    public ReportDao(@Qualifier("hiveJdbcTemplate") JdbcTemplate jdbcTemplate, ConfigurationHelper configurationHelper) {
        this.jdbcTemplate = jdbcTemplate;
        this.configurationHelper = configurationHelper;

    }

    public String formateDate(java.sql.Date date) {

        String text = dateFormat.format(date);
        return text;
    }

    public void getPCBUData(MainDto mainDto, java.util.Date businessDate, String unit){
        LOG.info("***** executing getPCBUData *****");
        LOG.info("=======Executing Query for========"+unit);
        jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                LOG.info("Query = "+ ConfigurationHelper.getPcbuQuery() );
                PreparedStatement ps = connection.prepareStatement(ConfigurationHelper.getPcbuQuery());

                java.sql.Date date = new java.sql.Date(businessDate.getTime());
                LOG.info("Prepared Statement before bind =" + ps.toString());
                LOG.info("DATE to pass in query==>"+formateDate(date));
                ps.setString(1, formateDate(date));

                return ps;
            }
        },new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {

             //   String unit = isNullOrEmpty(resultSet.getString("Unit"));

                String exitDate = isNullOrEmpty(resultSet.getString("Exit_date"));
                String application ="EDMS";

                Long totalVolume =resultSet.getLong("Total_Volume");
                Long cleanVolume =resultSet.getLong("Clean_Volume");
                Long cleanVolSlaMet =resultSet.getLong("Clean_Vol_Met");
                Long cleanVolSlaNotMet = resultSet.getLong("clean_Vol_Not_Met");

                Long refferalVolume = resultSet.getLong("Referral_Volume");
                Long refferalVolSlaMet = resultSet.getLong("Referral_Vol_Met");
                Long refferalVolSlaNotMet = resultSet.getLong("Referral_Vol_Not_Met");
                Long awaitingCover=0L;


              // LOG.info("account_number:"+account_number+"rm_name:"+rm_name+"account_currency:"+account_currency+"account_class:"+account_class+"account_title:"+account_title+"trans_date:"+trans_date+"trn_ref_no"+trn_ref_no+"checker_id"+checker_id+"trn_ref_no"+trn_ref_no+"return_cheque_no"+return_cheque_no+"return_cheque_amount"+return_cheque_amount+"return_reason"+return_reason+"narration"+narration);

                mainDto.populateData(exitDate,unit,totalVolume,cleanVolume,cleanVolSlaMet,cleanVolSlaNotMet,refferalVolume,refferalVolSlaMet,refferalVolSlaNotMet,awaitingCover,application);
            }
        });
    }

    public void getOutWardRemitanceData(MainDto mainDto){
        LOG.info("***** executing getOutWardRemitanceData *****");
        jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                LOG.info("Query = "+ ConfigurationHelper.getOutwardRemittanceQuery() );
                PreparedStatement ps = connection.prepareStatement(ConfigurationHelper.getOutwardRemittanceQuery());



                return ps;
            }
        },new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {

                String unit = isNullOrEmpty(resultSet.getString("Unit"));

                String exitDate = isNullOrEmpty(resultSet.getString("Exit_date"));
                Long totalVolume =resultSet.getLong("Total_Volume");
                Long cleanVolume =resultSet.getLong("Clean_Volume");
                Long cleanVolSlaMet =resultSet.getLong("Clean_Vol_Met");
                Long cleanVolSlaNotMet = resultSet.getLong("clean_Vol_Not_Met");

                Long refferalVolume = resultSet.getLong("Refferal_Volume");
                Long refferalVolSlaMet = resultSet.getLong("Refferal_Vol_Met");
                Long refferalVolSlaNotMet = resultSet.getLong("Refferal_Vol_Not_Met");
                Long awaitingCover=0L;
                String application="EDMS,Flex,UAEFTS,SWIFT And Online Channels";



                // LOG.info("account_number:"+account_number+"rm_name:"+rm_name+"account_currency:"+account_currency+"account_class:"+account_class+"account_title:"+account_title+"trans_date:"+trans_date+"trn_ref_no"+trn_ref_no+"checker_id"+checker_id+"trn_ref_no"+trn_ref_no+"return_cheque_no"+return_cheque_no+"return_cheque_amount"+return_cheque_amount+"return_reason"+return_reason+"narration"+narration);

                mainDto.populateData(exitDate,unit,totalVolume,cleanVolume,cleanVolSlaMet,cleanVolSlaNotMet,refferalVolume,refferalVolSlaMet,refferalVolSlaNotMet,awaitingCover,application);
            }
        });
    }

    public void getInwardRemitanceData(MainDto mainDto, java.util.Date businessDate){
        LOG.info("***** executing getInwardRemitanceData *****");
        jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                LOG.info("Query = "+ ConfigurationHelper.getInwardwardRemittanceQuery() );
                PreparedStatement ps = connection.prepareStatement(ConfigurationHelper.getInwardwardRemittanceQuery());

                java.sql.Date date = new java.sql.Date(businessDate.getTime());
                LOG.info("Prepared Statement before bind =" + ps.toString());
                LOG.info("DATE to pass in query==>"+formateDate(date));
                ps.setString(1, formateDate(date));

                return ps;
            }
        },new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {

                String unit = isNullOrEmpty(resultSet.getString("Unit"));

                String exitDate = isNullOrEmpty(resultSet.getString("dated"));

                Long totalVolume =resultSet.getLong("Total_Vol");

                LOG.info("QQQQQQQQQQQQQQQQQQQQQQUnit"+unit+"exitDate"+exitDate+"totalVolume"+totalVolume);

                Long awaiting_req = 0L;

                if (!(resultSet.getString("Awaiting_req").equalsIgnoreCase("") || resultSet.getString("Awaiting_req") == null))
                {
                     awaiting_req = (long)(Double.parseDouble(resultSet.getString("Awaiting_req")));
                }
                else {
                     awaiting_req = 0L;

                }

                //String application="EDMS And Flex";

                if (unit.equalsIgnoreCase("Total")) {

                    mainDto.setInwardsRemittanceTotalVolAndAwaitingReqDataFromQuery("Inward Remittance", exitDate, totalVolume,awaiting_req);
                }



                // LOG.info("account_number:"+account_number+"rm_name:"+rm_name+"account_currency:"+account_currency+"account_class:"+account_class+"account_title:"+account_title+"trans_date:"+trans_date+"trn_ref_no"+trn_ref_no+"checker_id"+checker_id+"trn_ref_no"+trn_ref_no+"return_cheque_no"+return_cheque_no+"return_cheque_amount"+return_cheque_amount+"return_reason"+return_reason+"narration"+narration);

               // mainDto.populateData(exitDate,unit,totalVolume,cleanVolume,cleanVolSlaMet,cleanVolSlaNotMet,refferalVolume,refferalVolSlaMet,refferalVolSlaNotMet,awaitingCover,application);
            }
        });
    }

    public void getInvestigationData(MainDto mainDto, java.util.Date businessDate, String unit){
        LOG.info("***** executing getInvestigationData *****");
        jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                LOG.info("Query = "+ ConfigurationHelper.getInvestigationsQuery() );
                PreparedStatement ps = connection.prepareStatement(ConfigurationHelper.getInvestigationsQuery());

                java.sql.Date date = new java.sql.Date(businessDate.getTime());
                LOG.info("Prepared Statement before bind =" + ps.toString());
                LOG.info("DATE to pass in query==>"+formateDate(date));
                ps.setString(1, formateDate(date));

                return ps;
            }
        },new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {

               // String unit = isNullOrEmpty(resultSet.getString("Unit"));

                String exitDate = isNullOrEmpty(resultSet.getString("reporting_date"));
                Long totalVolume =resultSet.getLong("Total_Volume");
                Long cleanVolume =resultSet.getLong("Clean_Volume");
                Long cleanVolSlaMet =resultSet.getLong("Clean_Vol_sla_Met");
                Long cleanVolSlaNotMet = resultSet.getLong("clean_Vol_sla_Not_Met");

                Long refferalVolume = resultSet.getLong("Referral_Volume");
                Long refferalVolSlaMet = resultSet.getLong("Referral_Vol_sla_Met");
                Long refferalVolSlaNotMet = resultSet.getLong("Referral_Vol_sla_Not_Met");
                Long awaitingCover=0L;
                String application="Sherlock,EDMS And TLM";



                // LOG.info("account_number:"+account_number+"rm_name:"+rm_name+"account_currency:"+account_currency+"account_class:"+account_class+"account_title:"+account_title+"trans_date:"+trans_date+"trn_ref_no"+trn_ref_no+"checker_id"+checker_id+"trn_ref_no"+trn_ref_no+"return_cheque_no"+return_cheque_no+"return_cheque_amount"+return_cheque_amount+"return_reason"+return_reason+"narration"+narration);

                mainDto.populateData(exitDate,unit,totalVolume,cleanVolume,cleanVolSlaMet,cleanVolSlaNotMet,refferalVolume,refferalVolSlaMet,refferalVolSlaNotMet,awaitingCover,application);
            }
        });
    }

    public void getInwardClearingData(MainDto mainDto){
        LOG.info("***** executing getInwardClearingData *****");
        jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                LOG.info("Query = "+ ConfigurationHelper.getPcbuQuery() );
                PreparedStatement ps = connection.prepareStatement(ConfigurationHelper.getPcbuQuery());

                return ps;
            }
        },new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {

                String unit = isNullOrEmpty(resultSet.getString("Unit"));

                String exitDate = isNullOrEmpty(resultSet.getString("Exit_date"));
                Long totalVolume =resultSet.getLong("Total_Volume");
                Long cleanVolume =resultSet.getLong("Clean_Volume");
                Long cleanVolSlaMet =resultSet.getLong("Clean_Vol_Met");
                Long cleanVolSlaNotMet = resultSet.getLong("clean_Vol_Not_Met");

                Long refferalVolume = resultSet.getLong("Refferal_Volume");
                Long refferalVolSlaMet = resultSet.getLong("Refferal_Vol_Met");
                Long refferalVolSlaNotMet = resultSet.getLong("Refferal_Vol_Not_Met");
                Long awaitingCover=0L;
                String application="KCS and Dexter";



                // LOG.info("account_number:"+account_number+"rm_name:"+rm_name+"account_currency:"+account_currency+"account_class:"+account_class+"account_title:"+account_title+"trans_date:"+trans_date+"trn_ref_no"+trn_ref_no+"checker_id"+checker_id+"trn_ref_no"+trn_ref_no+"return_cheque_no"+return_cheque_no+"return_cheque_amount"+return_cheque_amount+"return_reason"+return_reason+"narration"+narration);

                mainDto.populateData(exitDate,unit,totalVolume,cleanVolume,cleanVolSlaMet,cleanVolSlaNotMet,refferalVolume,refferalVolSlaMet,refferalVolSlaNotMet,awaitingCover,application);
            }
        });
    }

    public void getOutwardClearingData(MainDto mainDto){
        LOG.info("***** executing getOutwardClearingData *****");
        jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                LOG.info("Query = "+ ConfigurationHelper.getPcbuQuery() );
                PreparedStatement ps = connection.prepareStatement(ConfigurationHelper.getPcbuQuery());

                return ps;
            }
        },new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {

                String unit = isNullOrEmpty(resultSet.getString("Unit"));

                String exitDate = isNullOrEmpty(resultSet.getString("Exit_date"));
                Long totalVolume =resultSet.getLong("Total_Volume");
                Long cleanVolume =resultSet.getLong("Clean_Volume");
                Long cleanVolSlaMet =resultSet.getLong("Clean_Vol_Met");
                Long cleanVolSlaNotMet = resultSet.getLong("clean_Vol_Not_Met");

                Long refferalVolume = resultSet.getLong("Refferal_Volume");
                Long refferalVolSlaMet = resultSet.getLong("Refferal_Vol_Met");
                Long refferalVolSlaNotMet = resultSet.getLong("Refferal_Vol_Not_Met");
                Long awaitingCover=0L;
                String application="KCS and Dexter";




                // LOG.info("account_number:"+account_number+"rm_name:"+rm_name+"account_currency:"+account_currency+"account_class:"+account_class+"account_title:"+account_title+"trans_date:"+trans_date+"trn_ref_no"+trn_ref_no+"checker_id"+checker_id+"trn_ref_no"+trn_ref_no+"return_cheque_no"+return_cheque_no+"return_cheque_amount"+return_cheque_amount+"return_reason"+return_reason+"narration"+narration);

                mainDto.populateData(exitDate,unit,totalVolume,cleanVolume,cleanVolSlaMet,cleanVolSlaNotMet,refferalVolume,refferalVolSlaMet,refferalVolSlaNotMet,awaitingCover,application);
            }
        });
    }

    public void getWpsandNonwpsPayrollData(MainDto mainDto, java.util.Date businessDate){
        LOG.info("***** executing getWpsandNonwpsPayrollData *****");
        jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                LOG.info("Query = "+ ConfigurationHelper.getWpsandNonwpsPayrollQuery() );
                PreparedStatement ps = connection.prepareStatement(ConfigurationHelper.getWpsandNonwpsPayrollQuery());

                java.sql.Date date = new java.sql.Date(businessDate.getTime());
                LOG.info("Prepared Statement before bind =" + ps.toString());
                LOG.info("DATE to pass in query==>"+formateDate(date));
                ps.setString(1, formateDate(date));

                return ps;
            }
        },new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {

                LOG.info("RRRRRRRRRRRRRRRRRRRRRRRRRRRRR");

                String unit = isNullOrEmpty(resultSet.getString("product"));

                String exitDate = isNullOrEmpty(resultSet.getString("date"));

                Long cleanVolume =(long) (Double.parseDouble(resultSet.getString("Clean_Volume")));

                String application="WPS and GTS";

                if(unit.equalsIgnoreCase("Total")){

                    LOG.info("Totalllllllll");

                    mainDto.setWpsandNonwpsPayrollUnitCleanVolumeData(Controls.WPS_And_Non_WPS_Payroll,exitDate,cleanVolume,application);
                }



                // LOG.info("account_number:"+account_number+"rm_name:"+rm_name+"account_currency:"+account_currency+"account_class:"+account_class+"account_title:"+account_title+"trans_date:"+trans_date+"trn_ref_no"+trn_ref_no+"checker_id"+checker_id+"trn_ref_no"+trn_ref_no+"return_cheque_no"+return_cheque_no+"return_cheque_amount"+return_cheque_amount+"return_reason"+return_reason+"narration"+narration);

                //mainDto.populateData(exitDate,unit,totalVolume,cleanVolume,cleanVolSlaMet,cleanVolSlaNotMet,refferalVolume,refferalVolSlaMet,refferalVolSlaNotMet,awaitingCover,application);
            }
        });
    }

    public void getPDCTranferandChequeCollectionData(MainDto mainDto, java.util.Date businessDate){
        LOG.info("***** executing getPDCTranferandChequeCollectionData *****");
        jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                LOG.info("Query = "+ ConfigurationHelper.getPdcTransferandChequeCollectionQuery() );
                PreparedStatement ps = connection.prepareStatement(ConfigurationHelper.getPdcTransferandChequeCollectionQuery());

                java.sql.Date date = new java.sql.Date(businessDate.getTime());
                LOG.info("Prepared Statement before bind =" + ps.toString());
                LOG.info("DATE to pass in query==>"+formateDate(date));
                ps.setString(1, formateDate(date));

                return ps;
            }
        },new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {

                String unit = isNullOrEmpty(resultSet.getString("product"));

                String exitDate = isNullOrEmpty(resultSet.getString("exit_date"));

                Long cleanVolume =resultSet.getLong("Clean_Volume");

                String application="Flex and ICCS";

                if(unit.equalsIgnoreCase("Total")){

                    LOG.info("Totalllllllll");

                    mainDto.setWpsandNonwpsPayrollUnitCleanVolumeData(Controls.PDC_Transfer_And_Cheque_Collection,exitDate,cleanVolume,application);
                }


                // LOG.info("account_number:"+account_number+"rm_name:"+rm_name+"account_currency:"+account_currency+"account_class:"+account_class+"account_title:"+account_title+"trans_date:"+trans_date+"trn_ref_no"+trn_ref_no+"checker_id"+checker_id+"trn_ref_no"+trn_ref_no+"return_cheque_no"+return_cheque_no+"return_cheque_amount"+return_cheque_amount+"return_reason"+return_reason+"narration"+narration);

               // mainDto.populateData(exitDate,unit,totalVolume,cleanVolume,cleanVolSlaMet,cleanVolSlaNotMet,refferalVolume,refferalVolSlaMet,refferalVolSlaNotMet,awaitingCover,application);
            }
        });
    }

    public void getCMUData(MainDto mainDto, java.util.Date businessDate){
        LOG.info("***** executing getCMUData *****");
        jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                LOG.info("Query = "+ ConfigurationHelper.getCmuQuery() );
                PreparedStatement ps = connection.prepareStatement(ConfigurationHelper.getCmuQuery());

                java.sql.Date date = new java.sql.Date(businessDate.getTime());
                LOG.info("Prepared Statement before bind =" + ps.toString());
                LOG.info("DATE to pass in query==>"+formateDate(date));
                 ps.setString(1, formateDate(date));

                return ps;
            }
        },new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {

                String unit = isNullOrEmpty(resultSet.getString("unit"));

                String exitDate = isNullOrEmpty(resultSet.getString("dated"));

                Long cleanVolume =resultSet.getLong("Clean_Volume");

                String application="GTS,ECCS,Flex And CCS";

                if(unit.equalsIgnoreCase("Total")){

                    LOG.info("Totalllllllll");

                    mainDto.setWpsandNonwpsPayrollUnitCleanVolumeData(Controls.CMU,exitDate,cleanVolume,application);
                }




                // LOG.info("account_number:"+account_number+"rm_name:"+rm_name+"account_currency:"+account_currency+"account_class:"+account_class+"account_title:"+account_title+"trans_date:"+trans_date+"trn_ref_no"+trn_ref_no+"checker_id"+checker_id+"trn_ref_no"+trn_ref_no+"return_cheque_no"+return_cheque_no+"return_cheque_amount"+return_cheque_amount+"return_reason"+return_reason+"narration"+narration);

               // mainDto.populateData(exitDate,unit,totalVolume,cleanVolume,cleanVolSlaMet,cleanVolSlaNotMet,refferalVolume,refferalVolSlaMet,refferalVolSlaNotMet,awaitingCover,application);
            }
        });
    }

    public void getReconData(MainDto mainDto, java.util.Date businessDate, String unit){
        LOG.info("***** executing getReconData *****");
        jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                LOG.info("Query = "+ ConfigurationHelper.getReconQuery() );
                PreparedStatement ps = connection.prepareStatement(ConfigurationHelper.getReconQuery());

                java.sql.Date date = new java.sql.Date(businessDate.getTime());
                LOG.info("Prepared Statement before bind =" + ps.toString());
                LOG.info("DATE to pass in query==>"+formateDate(date));
                ps.setString(1, formateDate(date));

                return ps;
            }
        },new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {

                // String unit = isNullOrEmpty(resultSet.getString("Unit"));

                String exitDate = isNullOrEmpty(resultSet.getString("dated"));
                Long totalVolume =resultSet.getLong("Total_Volume");

                Long cleanVolSlaMet =resultSet.getLong("Clean_Vol_sla_Met");
                Long cleanVolSlaNotMet = resultSet.getLong("clean_Vol_sla_Not_Met");

                Long cleanVolume =0L;

                if(cleanVolSlaMet != null || cleanVolSlaNotMet != null) {
                    cleanVolume = cleanVolSlaMet + cleanVolSlaNotMet;
                }

                Long refferalVolume = 0L;
                Long refferalVolSlaMet = 0L;
                Long refferalVolSlaNotMet = 0L;
                Long awaitingCover=0L;
                String application="TLM";



                // LOG.info("account_number:"+account_number+"rm_name:"+rm_name+"account_currency:"+account_currency+"account_class:"+account_class+"account_title:"+account_title+"trans_date:"+trans_date+"trn_ref_no"+trn_ref_no+"checker_id"+checker_id+"trn_ref_no"+trn_ref_no+"return_cheque_no"+return_cheque_no+"return_cheque_amount"+return_cheque_amount+"return_reason"+return_reason+"narration"+narration);

                mainDto.populateData(exitDate,unit,totalVolume,cleanVolume,cleanVolSlaMet,cleanVolSlaNotMet,refferalVolume,refferalVolSlaMet,refferalVolSlaNotMet,awaitingCover,application);
            }
        });
    }











    public String isNullOrEmpty(String value) {
        if (value == null || value.isEmpty() || value.equalsIgnoreCase("\"\"")) {
            return " ";
        } else
            return value;
    }

}
