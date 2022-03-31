package com.fragma.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MainDto {

    static Logger LOG = LoggerFactory.getLogger(MainDto.class);

    private Map<String, HtmlSummary> cpcReportMap = new LinkedHashMap<>();
    private Set<String> cpcTableSet = new LinkedHashSet<>();

    private Date bdate;
    private LocalDate businessDate;
    private String bd_toDisplay;

    double weightedCleanVolumePercentage;
    double weightedRefferedVolumePercentage;

    public String getBd_toDisplay() {
        return bd_toDisplay;
    }

    public String subDate(){
        LocalDate yesturday=businessDate;
        String date = yesturday.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
        return date;

    }

    public void setBd_toDisplay(String bd_toDisplay) {
        this.bd_toDisplay = bd_toDisplay;
    }

    public LocalDate getBusinessDate() {
        return businessDate;
    }



    public Map<String, HtmlSummary> getCpcReportMap() {
        return cpcReportMap;
    }

    public Set<String> getCpcTableSet() {
        return cpcTableSet;
    }

    public int getSr() {
        return sr;
    }

    public void setDates(Date date) {

        Calendar businessDate = Calendar.getInstance();
        businessDate.setTime(date);
        businessDate.set(businessDate.get(Calendar.YEAR), businessDate.get(Calendar.MONTH), businessDate.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        this.bdate = businessDate.getTime();
        this.businessDate = businessDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public double getWeightedCleanVolumePercentage() {
        return weightedCleanVolumePercentage;
    }

    public void setWeightedCleanVolumePercentage(double weightedCleanVolumePercentage) {
        this.weightedCleanVolumePercentage = weightedCleanVolumePercentage;
    }

    public double getWeightedRefferedVolumePercentage() {
        return weightedRefferedVolumePercentage;
    }

    public void setWeightedRefferedVolumePercentage(double weightedRefferedVolumePercentage) {
        this.weightedRefferedVolumePercentage = weightedRefferedVolumePercentage;
    }

    public void hardCordData() {

        cpcTableSet.add(Controls.Outward_Remittance);
        cpcTableSet.add(Controls.Inward_Remittance);
        cpcTableSet.add(Controls.PCBU);
        cpcTableSet.add(Controls.Investigation);
        cpcTableSet.add(Controls.Inward_Clearing);
        cpcTableSet.add(Controls.Outward_Clearing);
        cpcTableSet.add(Controls.WPS_And_Non_WPS_Payroll);
        cpcTableSet.add(Controls.PDC_Transfer_And_Cheque_Collection);
        cpcTableSet.add(Controls.CMU);
        cpcTableSet.add(Controls.Recon);
        cpcTableSet.add(Controls.Total);

        for (String table : cpcTableSet) {

            Map<String, HtmlSummary> lhm = new LinkedHashMap<>();

                HtmlSummary htmlSummary = new HtmlSummary();
                if(table.equalsIgnoreCase("Outward Remittance"))
                {
                    htmlSummary.setApplication("EDMS,Flex,UAEFTS,SWIFT And Online Channels");
                }
                if(table.equalsIgnoreCase("Inward Remittance"))
                {
                    htmlSummary.setApplication("EDMS and Flex");
                }
                if(table.equalsIgnoreCase("PCBU"))
                {
                    htmlSummary.setApplication("EDMS");
                }
            if(table.equalsIgnoreCase("Investigation"))
            {
                htmlSummary.setApplication("Sherlock,EDMS and TLM");
            }
            if(table.equalsIgnoreCase("Inward Clearing"))
            {
                htmlSummary.setApplication("ICCS and Dexter");
            }
            if(table.equalsIgnoreCase("Outward Clearing"))
            {
                htmlSummary.setApplication("ICCS and Dexter");
            }
            if(table.equalsIgnoreCase("WPS And Non WPS Payroll"))
            {
                htmlSummary.setApplication("WPS and GTS");

            }
            if(table.equalsIgnoreCase("PDC,Transfer And Cheque Collection"))
            {
                htmlSummary.setApplication("Flex and ICCS");

            }
            if(table.equalsIgnoreCase("CMU"))
            {
                htmlSummary.setApplication("GTS,ECCS,FLEX and CCS");
            }
            if(table.equalsIgnoreCase(Controls.Recon))
            {
                htmlSummary.setApplication("TLM");
            }

            cpcReportMap.put(table, htmlSummary);
        }




    }



    public String isNullOrEmpty(Long  value)
    {
        LOG.info("Value"+value);
        LOG.info("KKKKKKKK");
        if(value == null || value == 0 )
        {
            return "0%";
        }
        else
            return  value+"%";
    }

    public String isWeightedPartNullOrEmpty(Long  value)
    {
        LOG.info("Value"+value);
        LOG.info("KKKKKKKK");
        if(value == null || value == 0 )
        {
            return "0%";
        }
        else
            return  value+"%";
    }

    public String isNullOrEmpty(Long  value,int totalValue)
    {
        if(totalValue == 0)
        {
            return  "-";
        }
        if(value == null || value == 0 )
        {
            return "0%";
        }
        else
            return  value+"%";
    }

    public String isNullOrEmpty(String  value)
    {
        LOG.info("UUUUUUUUUUUUU");
        LOG.info("value"+value);
        if(value == null || value.isEmpty() || value.equals("\"\""))
        {
            return "-";
        }
        else
            return  value;
    }

    int sr = 1;

    public int serialNum() {
        return sr++;
    }


    public void getMapData(){
        LOG.info("Inside Total CIBG MAp");


        for (Map.Entry<String, HtmlSummary> entry : cpcReportMap.entrySet()) {

            LOG.info("AAAAAAAAAAAAAAAAAAAAAAA"+entry.getKey());

                LOG.info("ExitDate:"+entry.getValue().getExitDate());
                LOG.info("Unit:"+entry.getValue().getUnit());
                LOG.info("TotalVolume:"+entry.getValue().getTotalVolume());
                LOG.info("CleanVolume:"+entry.getValue().getCleanVolume());
                LOG.info("CleanVolSlaMet:"+entry.getValue().getCleanVolSlaMet());
                LOG.info("CleanVolSlaNotMet:"+entry.getValue().getCleanVolSlaNotMet());
                LOG.info("CleanVolSlaMet Percentage:"+entry.getValue().getCleanSlaMetPercentage());
                LOG.info("RefferalVolume:"+entry.getValue().getRefferalVolume());
                LOG.info("RefferalVolSlaMet:"+entry.getValue().getRefferalVolSlaMet());
                LOG.info("RefferalVolSlaNotMet:"+entry.getValue().getRefferalVolSlaNotMet());
                LOG.info("RefferalVolSlaNotMet Percentage:"+entry.getValue().getRefferedSlaMetPercentage());

        }
        LOG.info(" Total CIBG MAp End");

    }

    public Long getPercentage(Long value,Long total){

        if(total == null || total == 0L){
            return 0L;
        }

        Double percentage=((double) (((double)value*100)/total));
        LOG.info("Percentage:"+percentage);

        return  Math.round(percentage);
    }

    public Long getPercentage(double value,double total){

        double percentage= ((value*100)/total);
        LOG.info("Percentage:"+percentage);

        return  Math.round(percentage);
    }

    public String longToString(long amount)
    {
        if(amount== 0)
        {
            return "0";
        }
        else {

            DecimalFormat df = new DecimalFormat("0"); // or pattern "###,###.##$"

            NumberFormat nf= NumberFormat.getInstance();
            nf.setGroupingUsed(true);
            return df.format(amount);
        }
    }

    public String longToString(long amount,int totalValue)
    {
        if(totalValue == 0)
        {
            return  "-";
        }

        if(amount== 0)
        {
            return "0";
        }
        else {

            DecimalFormat df = new DecimalFormat("0"); // or pattern "###,###.##$"

            NumberFormat nf= NumberFormat.getInstance();
            nf.setGroupingUsed(true);
            return df.format(amount);
        }
    }


    public String getColor(int value) {

        if ( value >= 95)
            return "green";
        else
            return "red";
    }

    public String getColor(int value,Long totalValue) {

        if(totalValue == 0L)
        {
            return  "blank";
        }
        if ( value >= 95) {
            return "green";
        }
        else {
            return "red";
        }

    }

    public int longToLong(long amount)
    {
        if( Long.valueOf(amount)==null ||amount== 0)
        {
            return 0;
        }
        else {

            return (int) amount;
        }
    }

    public void populateData(String exitDate, String unit, Long totalVolume, Long cleanVolume, Long cleanVolSlaMet, Long cleanVolSlaNotMet, Long refferalVolume, Long refferalVolSlaMet, Long refferalVolSlaNotMet, Long awaitingCover, String application) {

        cpcReportMap.get(unit).setUnit(unit);
       // cpcReportMap.get(unit).setApplication(application);
        LOG.info("bd_toDisplay"+bd_toDisplay);
        LOG.info("exitDate"+exitDate);
       // if(exitDate.equalsIgnoreCase(bd_toDisplay)){

            LOG.info("================");


            LOG.info("BBBBBBBBBBBBBBB"+cpcTableSet);
            if (cpcTableSet.contains(unit) ) {
                LOG.info("KKKKKKKKKKKKKKK" + unit);

                LOG.info("LLLLLLLLLLLLLLLLLLLLLL");


                cpcReportMap.get(unit).setITMissesVol(0L);
                cpcReportMap.get(unit).setOpsMissesVol(0L);
                cpcReportMap.get(unit).setAwaitingCover(awaitingCover);
                cpcReportMap.get(unit).setExitDate(exitDate);

                cpcReportMap.get(unit).setTotalVolume(totalVolume);
                LOG.info("HHHHHHHHHHHHHHHHHHH:" + cpcReportMap.get(unit).getTotalVolume());
                cpcReportMap.get(unit).setCleanVolume(cleanVolume);

                Long cleanVolPercentage = getPercentage(cpcReportMap.get(unit).getCleanVolume(), cpcReportMap.get(unit).getTotalVolume());
                cpcReportMap.get(unit).setCleanVolumePercentage(cleanVolPercentage);

                cpcReportMap.get(unit).setCleanVolSlaMet(cleanVolSlaMet);

                Long cleanSlaMetPercentage = getPercentage(cpcReportMap.get(unit).getCleanVolSlaMet(), cpcReportMap.get(unit).getCleanVolume());
                cpcReportMap.get(unit).setCleanSlaMetPercentage(cleanSlaMetPercentage);

                cpcReportMap.get(unit).setCleanVolSlaNotMet(cleanVolSlaNotMet);
                cpcReportMap.get(unit).setRefferalVolume(refferalVolume);

                Long refferedVolPercentage = getPercentage(cpcReportMap.get(unit).getRefferalVolume(), cpcReportMap.get(unit).getTotalVolume());
                cpcReportMap.get(unit).setRefferedVolPercentage(refferedVolPercentage);

                cpcReportMap.get(unit).setRefferalVolSlaMet(refferalVolSlaMet);

                Long refferedSlaMetPercentage = getPercentage(cpcReportMap.get(unit).getRefferalVolSlaMet(), cpcReportMap.get(unit).getRefferalVolume());
                cpcReportMap.get(unit).setRefferedSlaMetPercentage(refferedSlaMetPercentage);

                cpcReportMap.get(unit).setRefferalVolSlaNotMet(refferalVolSlaNotMet);



            }
       // }

    }


    public void setInwardsRemittanceTotalVolAndAwaitingReqDataFromQuery(String unit, String exitDate, Long totalVolume, Long awaiting_req) {

        LOG.info("WWWWWWWWWWWWWWWWWWW");

        cpcReportMap.get(unit).setUnit(unit);
        // cpcReportMap.get(unit).setApplication(application);
        LOG.info("bd_toDisplay"+bd_toDisplay);
        LOG.info("exitDate"+exitDate);

            LOG.info("================");

            LOG.info("BBBBBBBBBBBBBBB"+cpcTableSet);
            if (cpcTableSet.contains(unit) ) {
                LOG.info("KKKKKKKKKKKKKKK" + unit);

                LOG.info("LLLLLLLLLLLLLLLLLLLLLL");

                cpcReportMap.get(unit).setAwaitingCover(awaiting_req);
                cpcReportMap.get(unit).setExitDate(exitDate);

                cpcReportMap.get(unit).setTotalVolume(totalVolume);
                LOG.info("HHHHHHHHHHHHHHHHHHH:" + cpcReportMap.get(unit).getTotalVolume());


            }




    }

    public void populateInwardRemitanceDataOfExcel(String unit, long cleanVolSlaNotMet, long referralVolume, long referralVolMet, long referralVolNotMet, long opsMissesVol, long itMissesVol) {

        if(cpcReportMap.get(unit).getTotalVolume() != 0) {



            cpcReportMap.get(unit).setCleanVolSlaNotMet(cleanVolSlaNotMet);
            cpcReportMap.get(unit).setRefferalVolume(referralVolume);
            cpcReportMap.get(unit).setRefferalVolSlaMet(referralVolMet);
            cpcReportMap.get(unit).setRefferalVolSlaNotMet(referralVolNotMet);

           // cpcReportMap.get(unit).setAwaitingCover(awaitingCover);

            cpcReportMap.get(unit).setOpsMissesVol(opsMissesVol);
            cpcReportMap.get(unit).setITMissesVol(itMissesVol);


            cpcReportMap.get(unit).setCleanVolume(cpcReportMap.get(unit).getTotalVolume() - cpcReportMap.get(unit).getRefferalVolume() - cpcReportMap.get(unit).getAwaitingCover());
            cpcReportMap.get(unit).setCleanVolSlaMet(cpcReportMap.get(unit).getCleanVolume() - cpcReportMap.get(unit).getCleanVolSlaNotMet());

            Long cleanVolPercentage = getPercentage(cpcReportMap.get(unit).getCleanVolume(), cpcReportMap.get(unit).getTotalVolume());
            cpcReportMap.get(unit).setCleanVolumePercentage(cleanVolPercentage);

            Long cleanSlaMetPercentage = getPercentage(cpcReportMap.get(unit).getCleanVolSlaMet(), cpcReportMap.get(unit).getCleanVolume());
            cpcReportMap.get(unit).setCleanSlaMetPercentage(cleanSlaMetPercentage);

            Long refferedVolPercentage = getPercentage(cpcReportMap.get(unit).getRefferalVolume(), cpcReportMap.get(unit).getTotalVolume());
            cpcReportMap.get(unit).setRefferedVolPercentage(refferedVolPercentage);

            Long refferedSlaMetPercentage = getPercentage(cpcReportMap.get(unit).getRefferalVolSlaMet(), cpcReportMap.get(unit).getRefferalVolume());
            cpcReportMap.get(unit).setRefferedSlaMetPercentage(refferedSlaMetPercentage);
        }


    }

    public void calculatingWeightedCleanAndReferredSLA(){

        for (String unit: cpcTableSet) {

            if(unit != "Total"){

                LOG.info("Unit == >"+ unit);

                if (cpcReportMap.get(unit).getTotalVolume() != 0) {

                    LOG.info("Total Volume" + cpcReportMap.get(unit).getTotalVolume());
                    //Setting volume weight
                    cpcReportMap.get(unit).setVolumeWeight((double) cpcReportMap.get(unit).getTotalVolume() / cpcReportMap.get("Total").getTotalVolume());

                    //Setting Weighted Clean Volume
                    cpcReportMap.get(unit).setWeightedCleanVolume(cpcReportMap.get(unit).getCleanVolume() * cpcReportMap.get(unit).getVolumeWeight());

                    //Setting Weighted Clean Volume SLA Met
                    cpcReportMap.get(unit).setWeightedCleanVolumeSlaMet(cpcReportMap.get(unit).getVolumeWeight() * cpcReportMap.get(unit).getCleanVolSlaMet());


                    //Setting Weighted Referred Volume
                    cpcReportMap.get(unit).setWeightedReferralVolume(cpcReportMap.get(unit).getRefferalVolume() * cpcReportMap.get(unit).getVolumeWeight());

                    //Setting Weighted Referred Volume SLA Met
                    cpcReportMap.get(unit).setWeightedReferralVolumeSlaMet(cpcReportMap.get(unit).getVolumeWeight() * cpcReportMap.get(unit).getRefferalVolSlaMet());


                    cpcReportMap.get("Total").setWeightedCleanVolume(cpcReportMap.get("Total").getWeightedCleanVolume() + cpcReportMap.get(unit).getWeightedCleanVolume());

                    cpcReportMap.get("Total").setWeightedCleanVolumeSlaMet(cpcReportMap.get("Total").getWeightedCleanVolumeSlaMet() + cpcReportMap.get(unit).getWeightedCleanVolumeSlaMet());

                    cpcReportMap.get("Total").setWeightedReferralVolume(cpcReportMap.get("Total").getWeightedReferralVolume() + cpcReportMap.get(unit).getWeightedReferralVolume() );

                    cpcReportMap.get("Total").setWeightedReferralVolumeSlaMet(cpcReportMap.get("Total").getWeightedReferralVolumeSlaMet() + cpcReportMap.get(unit).getWeightedReferralVolumeSlaMet());


                }

            }

        }

        weightedCleanVolumePercentage =getPercentage(cpcReportMap.get("Total").getWeightedCleanVolumeSlaMet() , cpcReportMap.get("Total").getWeightedCleanVolume());

        weightedRefferedVolumePercentage = getPercentage(cpcReportMap.get("Total").getWeightedReferralVolumeSlaMet() , cpcReportMap.get("Total").getWeightedReferralVolume());


        LOG.info("weightedCleanVolumePercentage" + weightedCleanVolumePercentage);
        LOG.info("weightedRefferedVolumePercentage" + weightedRefferedVolumePercentage);


    }

    public void setWpsandNonwpsPayrollUnitCleanVolumeData(String unit, String exitDate, Long cleanVolume, String application) {

        LOG.info("Inside setWpsandNonwpsPayrollUnitCleanVolumeData method");



        cpcReportMap.get(unit).setUnit(unit);

        LOG.info("exitDate" + exitDate);

        LOG.info("================");

        LOG.info("BBBBBBBBBBBBBBB" + cpcTableSet);
        if (cpcTableSet.contains(unit)) {
            LOG.info("KKKKKKKKKKKKKKK" + unit);

            LOG.info("LLLLLLLLLLLLLLLLLLLLLL");


           // cpcReportMap.get(unit).setExitDate(exitDate);

            cpcReportMap.get(unit).setCleanVolume(cleanVolume);
            LOG.info("UUUUUnit"+unit);
            LOG.info("Clean Volume:" + cpcReportMap.get(unit).getCleanVolume());


        }
    }

    public void populateWPSandNonWPSPayrollDataOfExcel(String unit, long cleanVolSlaMet, long cleanVolSlaNotMet, long referralVolume, long referralVolMet, long referralVolNotMet, long awaitingCover, long itMissesVol, long opsMissesVol) {

        if(cpcReportMap.get(unit).getCleanVolume() == 0) {
            cpcReportMap.get(unit).setCleanVolume(0L);
        }


            //cpcReportMap.get(unit).setCleanVolume(cpcReportMap.get(unit).getCleanVolume());
            cpcReportMap.get(unit).setCleanVolSlaMet(cleanVolSlaMet);
            cpcReportMap.get(unit).setCleanVolSlaNotMet(cleanVolSlaNotMet);
            cpcReportMap.get(unit).setRefferalVolume(referralVolume);
            cpcReportMap.get(unit).setRefferalVolSlaMet(referralVolMet);
            cpcReportMap.get(unit).setRefferalVolSlaNotMet(referralVolNotMet);

            cpcReportMap.get(unit).setAwaitingCover(awaitingCover);
            cpcReportMap.get(unit).setOpsMissesVol(opsMissesVol);
            cpcReportMap.get(unit).setITMissesVol(itMissesVol);

            cpcReportMap.get(unit).setTotalVolume(cpcReportMap.get(unit).getCleanVolume() + cpcReportMap.get(unit).getRefferalVolume());

            Long cleanVolPercentage = getPercentage(cpcReportMap.get(unit).getCleanVolume(), cpcReportMap.get(unit).getTotalVolume());
            cpcReportMap.get(unit).setCleanVolumePercentage(cleanVolPercentage);

            Long cleanSlaMetPercentage = getPercentage(cpcReportMap.get(unit).getCleanVolSlaMet(), cpcReportMap.get(unit).getCleanVolume());
            cpcReportMap.get(unit).setCleanSlaMetPercentage(cleanSlaMetPercentage);

            Long refferedVolPercentage = getPercentage(cpcReportMap.get(unit).getRefferalVolume(), cpcReportMap.get(unit).getTotalVolume());
            cpcReportMap.get(unit).setRefferedVolPercentage(refferedVolPercentage);

            Long refferedSlaMetPercentage = getPercentage(cpcReportMap.get(unit).getRefferalVolSlaMet(), cpcReportMap.get(unit).getRefferalVolume());
            cpcReportMap.get(unit).setRefferedSlaMetPercentage(refferedSlaMetPercentage);




    }

    public void populateCMUAndPDCTransferAndChequeCollectionDataOfExcel(String unit, long cleanVolSlaNotMet, long referralVolume, long referralVolMet, long referralVolNotMet, long opsMissesVol, long itMissesVol, long cleanVolume, long awaitingCover, long cleanVolSlaMet) {

        if(cpcReportMap.get(unit).getCleanVolume() == 0) {
            cpcReportMap.get(unit).setCleanVolume(0L);
        }


            if (unit.equalsIgnoreCase(Controls.CMU)) {
                LOG.info("NNUnit" + unit);
                LOG.info("CleanVolume" + cpcReportMap.get(unit).getCleanVolume());
                LOG.info("Excel Clean Volume" + cleanVolume);


                cpcReportMap.get(unit).setCleanVolume(cpcReportMap.get(unit).getCleanVolume() + cleanVolume);

                LOG.info("Total Clean volume" + cpcReportMap.get(unit).getCleanVolume());
                cpcReportMap.get(unit).setCleanVolSlaMet(cpcReportMap.get(unit).getCleanVolume() - cleanVolSlaNotMet);
            } else {
                cpcReportMap.get(unit).setCleanVolSlaMet(cleanVolSlaMet);

            }

            cpcReportMap.get(unit).setCleanVolSlaNotMet(cleanVolSlaNotMet);
            cpcReportMap.get(unit).setRefferalVolume(referralVolume);
            cpcReportMap.get(unit).setRefferalVolSlaMet(referralVolMet);
            cpcReportMap.get(unit).setRefferalVolSlaNotMet(referralVolNotMet);

            cpcReportMap.get(unit).setAwaitingCover(awaitingCover);
            cpcReportMap.get(unit).setOpsMissesVol(opsMissesVol);
            cpcReportMap.get(unit).setITMissesVol(itMissesVol);

            cpcReportMap.get(unit).setTotalVolume(cpcReportMap.get(unit).getCleanVolume() + cpcReportMap.get(unit).getRefferalVolume());

            Long cleanVolPercentage = getPercentage(cpcReportMap.get(unit).getCleanVolume(), cpcReportMap.get(unit).getTotalVolume());
            cpcReportMap.get(unit).setCleanVolumePercentage(cleanVolPercentage);

            Long cleanSlaMetPercentage = getPercentage(cpcReportMap.get(unit).getCleanVolSlaMet(), cpcReportMap.get(unit).getCleanVolume());
            cpcReportMap.get(unit).setCleanSlaMetPercentage(cleanSlaMetPercentage);

            Long refferedVolPercentage = getPercentage(cpcReportMap.get(unit).getRefferalVolume(), cpcReportMap.get(unit).getTotalVolume());
            cpcReportMap.get(unit).setRefferedVolPercentage(refferedVolPercentage);

            Long refferedSlaMetPercentage = getPercentage(cpcReportMap.get(unit).getRefferalVolSlaMet(), cpcReportMap.get(unit).getRefferalVolume());
            cpcReportMap.get(unit).setRefferedSlaMetPercentage(refferedSlaMetPercentage);




    }

    public void calculatingTotalOfAllUnits() {

        LOG.info("Calculating Total");

        for (String unit: cpcTableSet ) {

            HtmlSummary htmlSummary = cpcReportMap.get(unit);

            if( !unit.equalsIgnoreCase(Controls.Total) && htmlSummary.getTotalVolume() != 0) {

                if (cpcReportMap.get("Total").getTotalVolume() == 0) {

                    LOG.info("Fiirst Unit"+unit);

                    cpcReportMap.get("Total").setAwaitingCover(cpcReportMap.get(unit).getAwaitingCover());
                    cpcReportMap.get("Total").setOpsMissesVol(cpcReportMap.get(unit).getOpsMissesVol());
                    cpcReportMap.get("Total").setITMissesVol(cpcReportMap.get(unit).getITMissesVol());

                    cpcReportMap.get("Total").setExitDate(cpcReportMap.get(unit).getExitDate());

                    cpcReportMap.get("Total").setTotalVolume(cpcReportMap.get(unit).getTotalVolume());
                    //LOG.info("HHHHHHHHHHHHHHHHHHH:" + cpcReportMap.get("Total").getTotalVolume());
                    cpcReportMap.get("Total").setCleanVolume(cpcReportMap.get(unit).getCleanVolume());

                    Long totalcleanVolPercentage = getPercentage(cpcReportMap.get("Total").getCleanVolume(), cpcReportMap.get("Total").getTotalVolume());
                    cpcReportMap.get("Total").setCleanVolumePercentage(totalcleanVolPercentage);

                    cpcReportMap.get("Total").setCleanVolSlaMet(cpcReportMap.get(unit).getCleanVolSlaMet());

                    Long totalcleanSlaMetPercentage = getPercentage(cpcReportMap.get("Total").getCleanVolSlaMet(), cpcReportMap.get("Total").getCleanVolume());
                    cpcReportMap.get("Total").setCleanSlaMetPercentage(totalcleanSlaMetPercentage);

                    cpcReportMap.get("Total").setCleanVolSlaNotMet(cpcReportMap.get(unit).getCleanVolSlaNotMet());
                    cpcReportMap.get("Total").setRefferalVolume(cpcReportMap.get(unit).getRefferalVolume());

                    Long totalrefferedVolPercentage = getPercentage(cpcReportMap.get("Total").getRefferalVolume(), cpcReportMap.get("Total").getTotalVolume());
                    cpcReportMap.get("Total").setRefferedVolPercentage(totalrefferedVolPercentage);

                    cpcReportMap.get("Total").setRefferalVolSlaMet(cpcReportMap.get(unit).getRefferalVolSlaMet());

                    Long totalrefferedSlaMetPercentage = getPercentage(cpcReportMap.get("Total").getRefferalVolSlaMet(), cpcReportMap.get("Total").getRefferalVolume());
                    cpcReportMap.get("Total").setRefferedSlaMetPercentage(totalrefferedSlaMetPercentage);

                    cpcReportMap.get("Total").setRefferalVolSlaNotMet(cpcReportMap.get(unit).getRefferalVolSlaNotMet());

                } else
                    {


                    LOG.info("Other Unit"+unit);


                    LOG.info("FFFFFFFFFFFFFFFFFFFFFFFFFFFF");
                    cpcReportMap.get("Total").setAwaitingCover(cpcReportMap.get("Total").getAwaitingCover() + cpcReportMap.get(unit).getAwaitingCover());


                    cpcReportMap.get("Total").setOpsMissesVol(cpcReportMap.get("Total").getOpsMissesVol() + cpcReportMap.get(unit).getOpsMissesVol());

                    LOG.info("QQQQQQQQQQQQQQQQQQQQq");
                    cpcReportMap.get("Total").setITMissesVol(cpcReportMap.get("Total").getITMissesVol() + cpcReportMap.get(unit).getITMissesVol());

                    cpcReportMap.get("Total").setTotalVolume(cpcReportMap.get("Total").getTotalVolume() + cpcReportMap.get(unit).getTotalVolume());
                    LOG.info("HHHHHHHHHHHHHHHHHHH:" + cpcReportMap.get("Total").getTotalVolume());
                    cpcReportMap.get("Total").setCleanVolume(cpcReportMap.get("Total").getCleanVolume() + cpcReportMap.get(unit).getCleanVolume());

                    Long totalcleanVolPercentage = getPercentage(cpcReportMap.get("Total").getCleanVolume(), cpcReportMap.get("Total").getTotalVolume());
                    cpcReportMap.get("Total").setCleanVolumePercentage(totalcleanVolPercentage);

                    cpcReportMap.get("Total").setCleanVolSlaMet(cpcReportMap.get("Total").getCleanVolSlaMet() + cpcReportMap.get(unit).getCleanVolSlaMet());

                    Long totalcleanSlaMetPercentage = getPercentage(cpcReportMap.get("Total").getCleanVolSlaMet(), cpcReportMap.get("Total").getCleanVolume());
                    cpcReportMap.get("Total").setCleanSlaMetPercentage(totalcleanSlaMetPercentage);

                    cpcReportMap.get("Total").setCleanVolSlaNotMet(cpcReportMap.get("Total").getCleanVolSlaNotMet() + cpcReportMap.get(unit).getCleanVolSlaNotMet());
                    cpcReportMap.get("Total").setRefferalVolume(cpcReportMap.get("Total").getRefferalVolume() + cpcReportMap.get(unit).getRefferalVolume());

                    Long totalrefferedVolPercentage = getPercentage(cpcReportMap.get("Total").getRefferalVolume(), cpcReportMap.get("Total").getTotalVolume());
                    cpcReportMap.get("Total").setRefferedVolPercentage(totalrefferedVolPercentage);

                    cpcReportMap.get("Total").setRefferalVolSlaMet(cpcReportMap.get("Total").getRefferalVolSlaMet() + cpcReportMap.get(unit).getRefferalVolSlaMet());

                    Long totalrefferedSlaMetPercentage = getPercentage(cpcReportMap.get("Total").getRefferalVolSlaMet(), cpcReportMap.get("Total").getRefferalVolume());
                    cpcReportMap.get("Total").setRefferedSlaMetPercentage(totalrefferedSlaMetPercentage);

                    cpcReportMap.get("Total").setRefferalVolSlaNotMet(cpcReportMap.get("Total").getRefferalVolSlaNotMet() + cpcReportMap.get(unit).getRefferalVolSlaNotMet());

                }


                if (cpcReportMap.get(unit).getTotalVolume() != 0) {

                    LOG.info("Total Volume" + cpcReportMap.get(unit).getTotalVolume());
                    //Setting volume weight
                    cpcReportMap.get(unit).setVolumeWeight((double) cpcReportMap.get(unit).getTotalVolume() / cpcReportMap.get("Total").getTotalVolume());

                    //Setting Weighted Clean Volume
                    cpcReportMap.get(unit).setWeightedCleanVolume(cpcReportMap.get(unit).getCleanVolume() * cpcReportMap.get(unit).getVolumeWeight());

                    //Setting Weighted Clean Volume SLA Met
                    cpcReportMap.get(unit).setWeightedCleanVolumeSlaMet(cpcReportMap.get(unit).getVolumeWeight() * cpcReportMap.get(unit).getCleanVolSlaMet());


                    //Setting Weighted Referred Volume
                    cpcReportMap.get(unit).setWeightedReferralVolume(cpcReportMap.get(unit).getRefferalVolume() * cpcReportMap.get(unit).getVolumeWeight());

                    //Setting Weighted Referred Volume SLA Met
                    cpcReportMap.get(unit).setWeightedReferralVolumeSlaMet(cpcReportMap.get(unit).getVolumeWeight() * cpcReportMap.get(unit).getRefferalVolSlaMet());


                    cpcReportMap.get("Total").setWeightedCleanVolume(cpcReportMap.get("Total").getWeightedCleanVolume() + cpcReportMap.get(unit).getWeightedCleanVolume());

                    cpcReportMap.get("Total").setWeightedCleanVolumeSlaMet(cpcReportMap.get("Total").getWeightedCleanVolumeSlaMet() + cpcReportMap.get(unit).getWeightedCleanVolumeSlaMet());

                    cpcReportMap.get("Total").setWeightedReferralVolume(cpcReportMap.get("Total").getWeightedReferralVolume() + cpcReportMap.get(unit).getWeightedReferralVolume());

                    cpcReportMap.get("Total").setWeightedReferralVolumeSlaMet(cpcReportMap.get("Total").getWeightedReferralVolumeSlaMet() + cpcReportMap.get(unit).getWeightedReferralVolumeSlaMet());


                }


            }
        }

        weightedCleanVolumePercentage =getPercentage(cpcReportMap.get("Total").getWeightedCleanVolumeSlaMet() , cpcReportMap.get("Total").getWeightedCleanVolume());

        weightedRefferedVolumePercentage = getPercentage(cpcReportMap.get("Total").getWeightedReferralVolumeSlaMet() , cpcReportMap.get("Total").getWeightedReferralVolume());


        LOG.info("weightedCleanVolumePercentage" + weightedCleanVolumePercentage);
        LOG.info("weightedRefferedVolumePercentage" + weightedRefferedVolumePercentage);

    }

    public void setOutwardClearingReferralansSLAMetCount(String unit, String dated, long referralCount, long ocCount, Long awaitingCover, String application) {

        cpcReportMap.get(unit).setExitDate(dated);
        cpcReportMap.get(unit).setUnit(unit);
        cpcReportMap.get(unit).setRefferalVolume(referralCount);

        Long cleanSLAMet = ocCount - referralCount ;
        cpcReportMap.get(unit).setCleanVolSlaMet(cleanSLAMet);

        cpcReportMap.get(unit).setApplication(application);
        cpcReportMap.get(unit).setAwaitingCover(awaitingCover);
    }

    public void populateOutwardClearingDataOfExcel(String unit, long totalVolume, long referralVolMet, long referralVolNotMet) {

        cpcReportMap.get(unit).setTotalVolume(totalVolume);

        long cleanVolume = cpcReportMap.get(unit).getTotalVolume() - cpcReportMap.get(unit).getRefferalVolume();
        cpcReportMap.get(unit).setCleanVolume(cleanVolume);

        long cleanVolSlaNotMet = cpcReportMap.get(unit).getCleanVolume() - cpcReportMap.get(unit).getCleanVolSlaMet();
        cpcReportMap.get(unit).setCleanVolSlaNotMet(cleanVolSlaNotMet);

        cpcReportMap.get(unit).setRefferalVolSlaMet(referralVolMet);
        cpcReportMap.get(unit).setRefferalVolSlaNotMet(referralVolNotMet);


        cpcReportMap.get(unit).setOpsMissesVol(0L);
        cpcReportMap.get(unit).setITMissesVol(0L);

        Long cleanVolPercentage = getPercentage(cpcReportMap.get(unit).getCleanVolume(), cpcReportMap.get(unit).getTotalVolume());
        cpcReportMap.get(unit).setCleanVolumePercentage(cleanVolPercentage);

        Long cleanSlaMetPercentage = getPercentage(cpcReportMap.get(unit).getCleanVolSlaMet(), cpcReportMap.get(unit).getCleanVolume());
        cpcReportMap.get(unit).setCleanSlaMetPercentage(cleanSlaMetPercentage);

        Long refferedVolPercentage = getPercentage(cpcReportMap.get(unit).getRefferalVolume(), cpcReportMap.get(unit).getTotalVolume());
        cpcReportMap.get(unit).setRefferedVolPercentage(refferedVolPercentage);

        Long refferedSlaMetPercentage = getPercentage(cpcReportMap.get(unit).getRefferalVolSlaMet(), cpcReportMap.get(unit).getRefferalVolume());
        cpcReportMap.get(unit).setRefferedSlaMetPercentage(refferedSlaMetPercentage);

    }
}
