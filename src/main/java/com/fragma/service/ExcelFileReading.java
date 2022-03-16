package com.fragma.service;

import com.fragma.config.ConfigurationHelper;
import com.fragma.dto.Controls;
import com.fragma.dto.MainDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

@Service
public class ExcelFileReading {

    private static final Logger LOG = LoggerFactory.getLogger(ExcelFileReading.class);

    XSSFWorkbook workbook = new XSSFWorkbook();

    DecimalFormat df = new DecimalFormat("#,###.00");

    public void readAllSheets( MainDto mainDto) throws Exception {

        readingInwardRemittanceExcelSheetData(mainDto, ConfigurationHelper.getExcelLocationForInwardRemittance());

       // FileOutputStream out = new FileOutputStream(excelFileLocation);
        //this.workbook.write(out);
        //out.close();
        LOG.info(" Excel file read successfully ");
    }

    public void readingInwardRemittanceExcelSheetData(MainDto mainDto, String excelLocationForInwardRemittance) throws Exception {

        LOG.info("***** executing readingInwardRemittanceExcelSheetData ****** ");

        try {
            FileInputStream file = new FileInputStream(new File(excelLocationForInwardRemittance));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

            String strDate=" ";

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                    LOG.info("Excel Read Data");

                    //LOG.info("ExitDate==>" + row.getCell(1));


                int firstRowNum = row.getRowNum();

                if (firstRowNum == 0)
                {
                   Date  exitDate = new Date(String.valueOf(row.getCell(1)));
                    LOG.info("Before Parsing Date"+exitDate);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                     strDate = dateFormat.format(exitDate);
                    LOG.info("DATEEEE==>"+strDate);
                }






                    String unit = checkNotNull(row.getCell(0));

                     LOG.info("rowname==>" + unit);

                    if(unit.equalsIgnoreCase("TOTAL")) {



                        /*Date exitDate = new Date(String.valueOf(row.getCell(1)));
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String strDate = dateFormat.format(exitDate);*/
                        //long totalVolume = (long) (row.getCell(1).getNumericCellValue());

                        LOG.info("strDate==>"+strDate);
                        LOG.info("BusinessDate==>"+mainDto.getBusinessDate());

                       /* if (strDate.equals(String.valueOf(mainDto.getBusinessDate()))){

                            LOG.info(strDate+"==================="+mainDto.getBusinessDate());
*/


                        long cleanVolSlaNotMet = checkValueNotNull(row.getCell(2));
                        long referralVolume = checkValueNotNull(row.getCell(3));
                        long referralVolMet = checkValueNotNull(row.getCell(4));
                        long referralVolNotMet = checkValueNotNull(row.getCell(5));
                       // long awaitingCover = checkValueNotNull(row.getCell(6));
                        long opsMissesVol = checkValueNotNull(row.getCell(7));
                        //long itMissesVol = (long) (row.getCell(7).getNumericCellValue());
                        long itMissesVol =  checkValueNotNull(row.getCell(8));


                        //LOG.info("Total_Volume==>" + row.getCell(1));
                        LOG.info("Clean SLA not met==>" + cleanVolSlaNotMet);
                        LOG.info("Referred Vol==>" + referralVolume);
                        LOG.info("Referral_Vol_MET==>" + referralVolMet);
                        LOG.info("Referral_Vol_Not_MET==>" + referralVolNotMet);
                       // LOG.info("Awaiting Cover"+awaitingCover);
                        LOG.info("OPSMisses Vol"+ opsMissesVol);
                        LOG.info("IT Misses Vol"+itMissesVol);

                        LOG.info("unit==>" + unit +   " cleanVolSlaNotMet==>" + cleanVolSlaNotMet + " referralVolume==>" + referralVolume + " referralVolMet==>" + referralVolMet + " referralVolNotMet==>" + referralVolNotMet);

                        mainDto.populateInwardRemitanceDataOfExcel( Controls.Inward_Remittance,cleanVolSlaNotMet, referralVolume, referralVolMet, referralVolNotMet,opsMissesVol,itMissesVol);

                        System.out.println("");
                        /*}else {
                            LOG.info("Not Equal");
                        }*/
                    }
            }
            file.close();
        } catch (Exception e) {
            LOG.error(String.valueOf(e));

            e.printStackTrace();

        }

        LOG.info("Inward Remittance Excel file read successfully ");

    }

    private long checkValueNotNull(Cell cell) {
        if(cell == null)
        {
            return 0;
        }
        return  (long)(cell.getNumericCellValue());
    }


    private String checkUnitIsNullOrNot(String unit) {

        if (unit == null)
        {
            return "";
        }
        return unit;

    }

    public void readingWPSandNonWPSPayrollExcelSheetData(String unit, MainDto mainDto, String excelLocationForWPSandNonWPSPayroll) {

        LOG.info("***** executing readingWPSandNonWPSPayrollExcelSheetData ****** ");

        try {
            LOG.info("Unit"+unit);
            FileInputStream file = new FileInputStream(new File(excelLocationForWPSandNonWPSPayroll));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();


                LOG.info("Excel Read Data");


                //LOG.info("ExitDate==>" + row.getCell(1));


                String rowname = checkNotNull(row.getCell(0));

                LOG.info("rowname==>" + rowname);

                if(rowname.equalsIgnoreCase("TOTAL")) {




                        /*Date exitDate = new Date(String.valueOf(row.getCell(1)));
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String strDate = dateFormat.format(exitDate);*/
                    //long totalVolume = (long) (row.getCell(1).getNumericCellValue());

                    long cleanVolSlaMet = checkValueNotNull(row.getCell(3));
                    long cleanVolSlaNotMet = checkValueNotNull(row.getCell(4));
                    long referralVolume = checkValueNotNull(row.getCell(5));
                    long referralVolMet = checkValueNotNull(row.getCell(6));
                    long referralVolNotMet = checkValueNotNull(row.getCell(7));
                    long awaitingCover = checkValueNotNull(row.getCell(8));
                    long opsMissesVol = checkValueNotNull(row.getCell(9));
                    long itMissesVol = checkValueNotNull(row.getCell(10));

                    LOG.info("Clean SLA  met==>" + cleanVolSlaMet);
                    LOG.info("Clean SLA not met==>" + cleanVolSlaNotMet);
                    LOG.info("Referred Vol==>" + referralVolume);
                    LOG.info("Referral_Vol_MET==>" + referralVolMet);
                    LOG.info("Referral_Vol_Not_MET==>" + referralVolNotMet);
                    LOG.info("Awaiting Cover "+ awaitingCover);

                    LOG.info("OPSMisses Vol"+ opsMissesVol);
                    LOG.info("IT Misses Vol"+itMissesVol);



                  //  LOG.info("unit==>" + unit +   " cleanVolSlaNotMet==>" + cleanVolSlaNotMet + " referralVolume==>" + referralVolume + " referralVolMet==>" + referralVolMet + " referralVolNotMet==>" + referralVolNotMet);

                    mainDto.populateWPSandNonWPSPayrollDataOfExcel(unit,cleanVolSlaMet,cleanVolSlaNotMet, referralVolume, referralVolMet, referralVolNotMet,awaitingCover,itMissesVol,opsMissesVol);


                    System.out.println("");
                }

            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

        LOG.info("Inward Remittance Excel file read successfully ");

    }

    public void readingCMUAndPDCTransferandChequeCollectionExcelSheetData(String unit, MainDto mainDto, String cmu) {

        LOG.info("***** executing readingCMUAndPDCTransferandChequeCollectionExcelSheetData ****** ");

        try {
            LOG.info("Unit"+unit);

            FileInputStream file = new FileInputStream(new File(cmu));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();



                LOG.info("Excel Read Data");


                //LOG.info("ExitDate==>" + row.getCell(1));

                String rowname = checkNotNull(row.getCell(0));
                //String rowname = checkUnitIsNullOrNot(row.getCell(0).getStringCellValue());

                LOG.info("rowname==>" + rowname);

                if(rowname.equalsIgnoreCase("TOTAL")) {

                        /*Date exitDate = new Date(String.valueOf(row.getCell(1)));
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String strDate = dateFormat.format(exitDate);*/
                    //long totalVolume = (long) (row.getCell(1).getNumericCellValue());

                    long cleanVolume = 0L;
                    long cleanVolSlaMet = 0L;


                    if (unit.equalsIgnoreCase(Controls.CMU))
                    {
                       // (row.getCell(2).getNumericCellValue())
                         cleanVolume = checkValueNotNull(row.getCell(2));
                    }
                    else {
                        cleanVolSlaMet = checkValueNotNull (row.getCell(2));
                    }



                    long cleanVolSlaNotMet = checkValueNotNull (row.getCell(3));
                    long referralVolume = checkValueNotNull (row.getCell(4));
                    long referralVolMet = checkValueNotNull (row.getCell(5));
                    long referralVolNotMet = checkValueNotNull (row.getCell(6));
                    long awaitingCover = checkValueNotNull (row.getCell(7));
                    long opsMissesVol = checkValueNotNull (row.getCell(8));
                    long itMissesVol = checkValueNotNull (row.getCell(9));

                    LOG.info("Unit"+unit);
                    LOG.info("Clean Volume==>" + cleanVolume);
                    LOG.info("Clean SLA met==>" + cleanVolSlaMet);
                    LOG.info("Clean SLA not met==>" + cleanVolSlaNotMet);
                    LOG.info("Referred Vol==>" + referralVolume);
                    LOG.info("Referral_Vol_MET==>" + referralVolMet);
                    LOG.info("Referral_Vol_Not_MET==>" + referralVolNotMet);
                    LOG.info("Awaiting Cover "+ awaitingCover);

                    LOG.info("OPSMisses Vol"+ opsMissesVol);
                    LOG.info("IT Misses Vol"+ itMissesVol);


                    mainDto.populateCMUAndPDCTransferAndChequeCollectionDataOfExcel( unit,cleanVolSlaNotMet, referralVolume, referralVolMet, referralVolNotMet,opsMissesVol,itMissesVol,cleanVolume,awaitingCover,cleanVolSlaMet);


                    //  LOG.info("unit==>" + unit +   " cleanVolSlaNotMet==>" + cleanVolSlaNotMet + " referralVolume==>" + referralVolume + " referralVolMet==>" + referralVolMet + " referralVolNotMet==>" + referralVolNotMet);

                   // mainDto.populateWPSandNonWPSPayrollDataOfExcel(unit,cleanVolSlaMet,cleanVolSlaNotMet, referralVolume, referralVolMet, referralVolNotMet,awaitingCover,itMissesVol,opsMissesVol);


                    System.out.println("");
                }

            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

        LOG.info("Inward Remittance Excel file read successfully ");


    }

    private String checkNotNull(Cell cell) {
        if (cell == null){
            return " ";
        }
        return cell.getStringCellValue();
    }

    public long checkValueIsNullOrNot(Row row, int i){
        Cell c = row.getCell(3);
        if (c == null || c.getStringCellValue() == null) {
            return 0;
        }
        return (long) (row.getCell(i).getNumericCellValue());
    }
}
