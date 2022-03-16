package com.fragma.client;

import com.fragma.config.ConfigurationHelper;
import com.fragma.config.SMTPMailThreadConfig;
import com.fragma.dao.ReportDao;
import com.fragma.dto.Controls;
import com.fragma.dto.MainDto;
import com.fragma.service.ExcelFileReading;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class Runner implements ApplicationRunner {
    static Logger LOG = LoggerFactory.getLogger(Runner.class);

    private ReportDao reportDao;
    private Session session;
    private TemplateEngine templateEngine;
    private SMTPMailThreadConfig smtpMailThreadConfig;
    private ConfigurationHelper configurationHelper;
    private ExcelFileReading excelFileReading;


    @Autowired
    public Runner( TemplateEngine templateEngine, ReportDao reportDao, Session session, SMTPMailThreadConfig smtpMailThreadConfig, ConfigurationHelper configurationHelper,ExcelFileReading excelFileReading) {

        this.reportDao = reportDao;
        this.session = session;
        this.templateEngine=templateEngine;
        this.smtpMailThreadConfig = smtpMailThreadConfig;
        this.configurationHelper = configurationHelper;
        this.excelFileReading = excelFileReading;

    }

    @Override
    public void run(ApplicationArguments args) {

        try {
            Context context = new Context();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

            LOG.info("run started");

            List<String> businessDates = args.getOptionValues("businessDate");
            Date businessDate;
            String bdString;

            if (businessDates != null && businessDates.size() > 0) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                bdString = businessDates.get(0);
                businessDate = dateFormat.parse(bdString);
            } else {
                Calendar instance = Calendar.getInstance();
                instance.add(Calendar.DAY_OF_MONTH, -1);
                businessDate = instance.getTime();
            }
            LOG.info("Date:"+businessDate);

            MainDto mainDto=new MainDto();

            Calendar businessDateCal = Calendar.getInstance();
            businessDateCal.setTime(businessDate);
            businessDateCal.set(businessDateCal.get(Calendar.YEAR), businessDateCal.get(Calendar.MONTH), businessDateCal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);


            Calendar nextBusinessDateCal = Calendar.getInstance();
            nextBusinessDateCal.setTime(businessDate);
            nextBusinessDateCal.add(Calendar.DAY_OF_MONTH, 1);
            nextBusinessDateCal.set(nextBusinessDateCal.get(Calendar.YEAR), nextBusinessDateCal.get(Calendar.MONTH), nextBusinessDateCal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);


            LOG.info("Today:"+nextBusinessDateCal.getTime());

            mainDto.setDates(businessDate);


            mainDto.hardCordData();

           // reportDao.getOutWardRemitanceData(mainDto);

            reportDao.getPCBUData(mainDto,businessDate,Controls.PCBU);
            reportDao.getInwardRemitanceData(mainDto,businessDate);
            //reportDao.getInvestigationData(mainDto);
            //reportDao.getInwardClearingData(mainDto);
            //reportDao.getOutwardClearingData(mainDto);
            reportDao.getWpsandNonwpsPayrollData(mainDto,businessDate);
            reportDao.getPDCTranferandChequeCollectionData(mainDto,businessDate);
            reportDao.getCMUData(mainDto,businessDate);


            reportDao.getInvestigationData(mainDto,nextBusinessDateCal.getTime(),Controls.Investigation);

            reportDao.getReconData(mainDto,businessDate,Controls.Recon);

            excelFileReading.readingInwardRemittanceExcelSheetData(mainDto,ConfigurationHelper.getExcelLocationForInwardRemittance());
            excelFileReading.readingWPSandNonWPSPayrollExcelSheetData(Controls.WPS_And_Non_WPS_Payroll,mainDto,ConfigurationHelper.getExcelLocationForWPSandNonWPSPayroll());

            excelFileReading.readingCMUAndPDCTransferandChequeCollectionExcelSheetData(Controls.CMU,mainDto,ConfigurationHelper.getExcelLocationForCMU());
            excelFileReading.readingCMUAndPDCTransferandChequeCollectionExcelSheetData(Controls.PDC_Transfer_And_Cheque_Collection,mainDto,ConfigurationHelper.getExcelLocationForPDCTransferAndChequeCollection());


            mainDto.calculatingTotalOfAllUnits();

            mainDto.getMapData();


            //mainDto.calculatingWeightedCleanAndReferredSLA();

            context.setVariable("mainDto", mainDto);




        //CIBG
            StringWriter writer2 = new StringWriter();

            templateEngine.process("CPC", context, writer2);
            sendMailWithAttachment(writer2.toString(), smtpMailThreadConfig.getToAddress(), smtpMailThreadConfig.getCcAddress(), smtpMailThreadConfig.getSubject());


            LOG.info("Mail Sent Successfully");
        }
        catch (Exception e) {
            LOG.error("Exception : " + ExceptionUtils.getStackTrace(e));
        }
    }

    public void sendMailWithAttachment(String body, String toAddress, String ccAddress, String subject) {

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setFrom(new InternetAddress(smtpMailThreadConfig.getFromAddress(), smtpMailThreadConfig.getFromName()));
            msg.setReplyTo(InternetAddress.parse(smtpMailThreadConfig.getFromAddress(), false));
            msg.setContent(body, "text/html");
            msg.setSubject(subject, "UTF-8");

            MimeBodyPart messageBodyPart1 = new MimeBodyPart();

            messageBodyPart1.addHeader("Content-type", "text/HTML; charset=UTF-8");
            messageBodyPart1.addHeader("format", "flowed");
            messageBodyPart1.addHeader("Content-Transfer-Encoding", "8bit");
            // messageBodyPart1.setText(st.toString());
            messageBodyPart1.setContent(body, "text/html");

            Multipart multipart = new MimeMultipart();

            multipart.addBodyPart(messageBodyPart1);

           // LOG.info("Excel Location ->"+configurationHelper.getExcelLocation());

            //addAttachment(multipart, configurationHelper.getExcelLocation());

            msg.setContent(multipart);
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress, false));
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccAddress, false));
            Transport.send(msg);

        } catch (Exception e) {
            LOG.error("ERROR RAISED WHILE SENDING MAIL WITH AN ATTACHMENT..=" + ExceptionUtils.getStackTrace(e));
            throw new RuntimeException(e);
        }
    }

    public void addAttachment(Multipart multipart, String fileName) throws MessagingException {

        DataSource source = new FileDataSource(fileName);
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(new File(fileName).getName());
        multipart.addBodyPart(messageBodyPart);

    }

}
