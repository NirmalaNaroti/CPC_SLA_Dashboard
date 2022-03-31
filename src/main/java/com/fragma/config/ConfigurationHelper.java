package com.fragma.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "conf")
public class ConfigurationHelper {
    private static String  pcbuQuery;
    private static String  outwardRemittanceQuery;
    private static  String inwardwardRemittanceQuery;
    private static String  investigationsQuery;
    private static String  inwardClearingQuery;
    private static String  outwardClearingQuery;
    private static String  wpsandNonwpsPayrollQuery;
    private static String  pdcTransferandChequeCollectionQuery;
    private static String  cmuQuery;
    private static String reconQuery;

    private static String excelLocationForInwardRemittance;
    private static String excelLocationForWPSandNonWPSPayroll;
    private static String excelLocationForCMU;
    private static String excelLocationForPDCTransferAndChequeCollection;
    private static String excelLocationForOutwardClearing;


    public static String getPcbuQuery() {
        return pcbuQuery;
    }

    public static void setPcbuQuery(String pcbuQuery) {
        ConfigurationHelper.pcbuQuery = pcbuQuery;
    }

    public static String getOutwardRemittanceQuery() {
        return outwardRemittanceQuery;
    }

    public static void setOutwardRemittanceQuery(String outwardRemittanceQuery) {
        ConfigurationHelper.outwardRemittanceQuery = outwardRemittanceQuery;
    }

    public static String getInwardwardRemittanceQuery() {
        return inwardwardRemittanceQuery;
    }

    public static void setInwardwardRemittanceQuery(String inwardwardRemittanceQuery) {
        ConfigurationHelper.inwardwardRemittanceQuery = inwardwardRemittanceQuery;
    }

    public static String getInvestigationsQuery() {
        return investigationsQuery;
    }

    public static void setInvestigationsQuery(String investigationsQuery) {
        ConfigurationHelper.investigationsQuery = investigationsQuery;
    }

    public static String getInwardClearingQuery() {
        return inwardClearingQuery;
    }

    public static void setInwardClearingQuery(String inwardClearingQuery) {
        ConfigurationHelper.inwardClearingQuery = inwardClearingQuery;
    }

    public static String getOutwardClearingQuery() {
        return outwardClearingQuery;
    }

    public static void setOutwardClearingQuery(String outwardClearingQuery) {
        ConfigurationHelper.outwardClearingQuery = outwardClearingQuery;
    }

    public static String getWpsandNonwpsPayrollQuery() {
        return wpsandNonwpsPayrollQuery;
    }

    public static void setWpsandNonwpsPayrollQuery(String wpsandNonwpsPayrollQuery) {
        ConfigurationHelper.wpsandNonwpsPayrollQuery = wpsandNonwpsPayrollQuery;
    }

    public static String getPdcTransferandChequeCollectionQuery() {
        return pdcTransferandChequeCollectionQuery;
    }

    public static void setPdcTransferandChequeCollectionQuery(String pdcTransferandChequeCollectionQuery) {
        ConfigurationHelper.pdcTransferandChequeCollectionQuery = pdcTransferandChequeCollectionQuery;
    }

    public static String getCmuQuery() {
        return cmuQuery;
    }

    public static void setCmuQuery(String cmuQuery) {
        ConfigurationHelper.cmuQuery = cmuQuery;
    }

    public static String getExcelLocationForInwardRemittance() {
        return excelLocationForInwardRemittance;
    }

    public static void setExcelLocationForInwardRemittance(String excelLocationForInwardRemittance) {
        ConfigurationHelper.excelLocationForInwardRemittance = excelLocationForInwardRemittance;
    }

    public static String getExcelLocationForWPSandNonWPSPayroll() {
        return excelLocationForWPSandNonWPSPayroll;
    }

    public static void setExcelLocationForWPSandNonWPSPayroll(String excelLocationForWPSandNonWPSPayroll) {
        ConfigurationHelper.excelLocationForWPSandNonWPSPayroll = excelLocationForWPSandNonWPSPayroll;
    }

    public static String getExcelLocationForCMU() {
        return excelLocationForCMU;
    }

    public static void setExcelLocationForCMU(String excelLocationForCMU) {
        ConfigurationHelper.excelLocationForCMU = excelLocationForCMU;
    }

    public static String getExcelLocationForPDCTransferAndChequeCollection() {
        return excelLocationForPDCTransferAndChequeCollection;
    }

    public static void setExcelLocationForPDCTransferAndChequeCollection(String excelLocationForPDCTransferAndChequeCollection) {
        ConfigurationHelper.excelLocationForPDCTransferAndChequeCollection = excelLocationForPDCTransferAndChequeCollection;
    }

    public static String getReconQuery() {
        return reconQuery;
    }

    public static void setReconQuery(String reconQuery) {
        ConfigurationHelper.reconQuery = reconQuery;
    }

    public static String getExcelLocationForOutwardClearing() {
        return excelLocationForOutwardClearing;
    }

    public static void setExcelLocationForOutwardClearing(String excelLocationForOutwardClearing) {
        ConfigurationHelper.excelLocationForOutwardClearing = excelLocationForOutwardClearing;
    }
}
