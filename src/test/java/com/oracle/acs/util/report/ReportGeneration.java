package com.oracle.acs.util.report;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Color;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class ReportGeneration {
    public static String report_Unique = null;
    public static String report_File_Name = null;
    public static String strTC_ID;
    public static String strTC_Name;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;
    Properties prop = new Properties();

    public ReportGeneration(String strTestCase_ID, String strTestCase_Name) throws IOException {
        if (report_File_Name == null) {
            htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\HtmlReport.html");
            htmlReporter.config().setDocumentTitle("Test Execution Report");
            htmlReporter.config().setReportName("<b><font size=\"3\" face=\"Palatino\" color=\"GreenYellow\">OFS Automation Testing Report</font><b>");
            htmlReporter.config().setTheme(Theme.DARK);
            extent = new ExtentReports();
            extent.attachReporter(new ExtentReporter[]{htmlReporter});
        }

        report_File_Name = String.valueOf(strTestCase_ID) + "_" + strTestCase_Name;
        if (!(new File("target/ofs-reports/report.xml")).exists()) {
            if (!(new File("target/ofs-reports")).exists()) {
                (new File("target/ofs-reports")).mkdirs();
            }

            FileOutputStream fos = new FileOutputStream("target/ofs-reports/report.xml");
            this.prop.storeToXML(fos, "XML generated for report generation");
            fos.close();
        } else {
            System.out.println("target/ofs-reports/report.xml Exists...!!!");
            this.prop.loadFromXML(new FileInputStream("target/ofs-reports/report.xml"));
            String oldFileName = this.prop.getProperty("reportFileName");
            if (oldFileName != null && !oldFileName.equalsIgnoreCase(report_File_Name)) {
                report_Unique = null;
            }
        }

        strTC_ID = strTestCase_ID != null && strTestCase_ID.length() >= 1 ? strTestCase_ID : "";
        strTC_Name = strTestCase_Name != null && strTestCase_Name.length() >= 1 ? strTestCase_Name : "";
    }

    public ReportGeneration() throws IOException {
        if (!(new File("target/ofs-reports/report.xml")).exists()) {
            if (!(new File("target/ofs-reports")).exists()) {
                (new File("target/ofs-reports")).mkdir();
            }

            FileOutputStream fos = new FileOutputStream("target/ofs-reports/report.xml");
            this.prop.storeToXML(fos, "XML generated for report generation");
            fos.close();
        }

    }

    public void generateReport(String tc_ID_Name, String step_Description, String actionOrOperation, String input_Data, String expected_Result, String actual_Result, String status, String comments, boolean screenshot) throws IOException, AWTException, ParseException, InterruptedException {
        if (report_Unique == null) {
            this.reportInitialSetup();
        }

        this.prop.loadFromXML(new FileInputStream("target/ofs-reports/report.xml"));
        if (this.prop.getProperty("report_step_no") == null) {
            this.prop.setProperty("report_step_no", "1");
        } else {
            this.prop.setProperty("report_step_no", String.valueOf(Integer.parseInt(this.prop.getProperty("report_step_no")) + 1));
        }

        String reportRow = this.prop.getProperty("reportRow");
        String reportPath = this.prop.getProperty("report_xlsPath");
        String report_step_no = this.prop.getProperty("report_step_no");
        String screenRow = this.prop.getProperty("screenRow");
        int rowIndex = Integer.parseInt(reportRow);
        InputStream open = new FileInputStream(reportPath);
        XSSFWorkbook wb = new XSSFWorkbook(open);
        XSSFSheet xSSFSheet = wb.getSheet("Report");
        Row row1 = xSSFSheet.createRow((short)rowIndex);
        XSSFCellStyle xSSFCellStyle = wb.createCellStyle();
        XSSFFont xSSFFont = wb.createFont();
        if (status.equalsIgnoreCase("Passed")) {
            xSSFFont.setColor(IndexedColors.GREEN.getIndex());
        } else if (status.equalsIgnoreCase("Failed")) {
            xSSFFont.setColor(IndexedColors.RED.getIndex());
        } else {
            xSSFFont.setColor(IndexedColors.BLACK.getIndex());
        }

        xSSFFont.setBoldweight((short)700);
        xSSFCellStyle.setFont(xSSFFont);
        this.setCell((CellStyle)null, row1.createCell(0), report_step_no);
        this.setCell((CellStyle)null, row1.createCell(1), tc_ID_Name);
        this.setCell((CellStyle)null, row1.createCell(2), step_Description);
        this.setCell((CellStyle)null, row1.createCell(3), actionOrOperation);
        this.setCell((CellStyle)null, row1.createCell(4), input_Data);
        this.setCell((CellStyle)null, row1.createCell(5), expected_Result);
        this.setCell((CellStyle)null, row1.createCell(6), actual_Result);
        this.setCell(xSSFCellStyle, row1.createCell(7), status);
        xSSFCellStyle = wb.createCellStyle();
        xSSFCellStyle.setWrapText(true);
        row1.getCell(4).setCellStyle(xSSFCellStyle);
        if (screenshot || status.equalsIgnoreCase("Failed")) {
            this.createLink(wb, 8, screenRow, reportRow);
            this.addImage(wb, screenRow);
        }

        this.setCell((CellStyle)null, row1.createCell(9), comments);
        FileOutputStream fileOut = new FileOutputStream(reportPath);
        wb.write(fileOut);
        fileOut.close();
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("Testcase Name\t: " + tc_ID_Name);
        System.out.println("Step Description: " + step_Description);
        System.out.println("Action/Operation: " + actionOrOperation);
        System.out.println("Input Data\t: " + input_Data);
        System.out.println("Expected Result\t: " + expected_Result);
        System.out.println("Actual result\t: " + actual_Result);
        if (status.equalsIgnoreCase("failed")) {
            System.err.println("Status\t\t: " + status);
            Thread.sleep(10L);
        } else {
            System.out.println("Status\t\t: " + status);
        }

        System.out.println("Comment\t\t: " + comments);
        System.out.println("------------------------------------------------------------------------------------");
        this.prop.setProperty("reportRow", String.valueOf(Integer.parseInt(this.prop.getProperty("reportRow")) + 1));
        if (screenshot) {
            this.prop.setProperty("screenRow", String.valueOf(Integer.parseInt(this.prop.getProperty("screenRow")) + 45));
        }

        this.prop.storeToXML(new FileOutputStream("./target/ofs-reports/report.xml"), "XML generated for report generation");
        if (status.toLowerCase().contains("pass")) {
            logger.log(Status.PASS, "<b>Step Description:</b> " + step_Description + "</br><b>InputData:</b> " + input_Data + "</br><b>Expected:</b> " + expected_Result + "</br><b>Actual:</b> " + actual_Result);
        } else if (status.toLowerCase().contains("info")) {
            logger.log(Status.INFO, "<b>Step Description:</b> " + step_Description + "</br><b>InputData:</b> " + input_Data + "</br><b>Expected:</b> " + expected_Result + "</br><b>Actual:</b> " + actual_Result);
        } else if (status.toLowerCase().contains("fail")) {
            logger.log(Status.FAIL, "<b>Step Description:</b> " + step_Description + "</br><b>InputData:</b> " + input_Data + "</br><b>Expected:</b> " + expected_Result + "</br><b>Actual:</b> " + actual_Result);
        }

        extent.flush();
    }

    public void reportInitialSetup() throws IOException, ParseException {
        logger = extent.createTest(report_File_Name);
        report_Unique = "Report Started";
        InputStream inProp = new FileInputStream("target/ofs-reports/report.xml");
        this.prop.loadFromXML(inProp);
        String folder = "";
        if (this.prop.getProperty("report_xlsFolder") == null) {
            folder = "target/ofs-reports";
        } else {
            folder = this.prop.getProperty("report_xlsFolder");
        }

        String xlsPath = String.valueOf(folder) + (report_File_Name != null && !report_File_Name.equals("") ? "\\" + report_File_Name + "_" : "\\Report_") + this.futureDate("dd-MMM-yyyy hh-mm-ss aaa", 0L) + ".xlsx";
        System.out.println("Report Path is \t: " + folder);
        System.out.println("Report file \t: " + xlsPath);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException var11) {
            var11.printStackTrace();
        } catch (InstantiationException var12) {
            var12.printStackTrace();
        } catch (IllegalAccessException var13) {
            var13.printStackTrace();
        } catch (UnsupportedLookAndFeelException var14) {
            var14.printStackTrace();
        }

        (new File(folder)).mkdirs();
        XSSFWorkbook xlsBk = new XSSFWorkbook();
        xlsBk.createSheet("Report");
        xlsBk.createSheet("Screenshots");
        XSSFSheet xSSFSheet = xlsBk.getSheet("Report");
        InetAddress ip = InetAddress.getLocalHost();
        this.header(xlsBk, xSSFSheet, ip.getHostName(), ip.getHostAddress(), this.futureDate("dd-MMM-yyyy", 0L), this.futureDate("hh:mm:ss aaa", 0L));
        Row row = xSSFSheet.createRow(8);
        XSSFCellStyle xSSFCellStyle = xlsBk.createCellStyle();
        xSSFCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        xSSFCellStyle.setFillPattern((short)1);
        XSSFFont xSSFFont = xlsBk.createFont();
        xSSFFont.setBoldweight((short)700);
        xSSFCellStyle.setFont(xSSFFont);
        this.setCell(xSSFCellStyle, row.createCell(0), "Step No");
        this.setCell(xSSFCellStyle, row.createCell(1), "TC_ID_Name");
        this.setCell(xSSFCellStyle, row.createCell(2), "Step Description");
        this.setCell(xSSFCellStyle, row.createCell(3), "Action/Operation");
        this.setCell(xSSFCellStyle, row.createCell(4), "Input Data");
        this.setCell(xSSFCellStyle, row.createCell(5), "Expected Result");
        this.setCell(xSSFCellStyle, row.createCell(6), "Actual Result");
        this.setCell(xSSFCellStyle, row.createCell(7), "Status");
        this.setCell(xSSFCellStyle, row.createCell(8), "ScreenShot");
        this.setCell(xSSFCellStyle, row.createCell(9), "Comments");

        for(int i = 0; i < 10; ++i) {
            xSSFSheet.autoSizeColumn(i);
        }

        FileOutputStream fileOut = new FileOutputStream(xlsPath);
        xlsBk.write(fileOut);
        fileOut.close();
        inProp.close();
        this.prop.setProperty("report_xlsFolder", folder);
        this.prop.setProperty("report_xlsPath", xlsPath);
        this.prop.setProperty("reportRow", "9");
        this.prop.setProperty("screenRow", "0");
        this.prop.setProperty("reportFileName", report_File_Name);
        this.prop.storeToXML(new FileOutputStream("./target/ofs-reports/report.xml"), "XML generated for report generation");
    }

    public void enterStepHeader(String heading) throws NumberFormatException, IOException, ParseException {
        if (report_Unique == null) {
            this.reportInitialSetup();
        }

        InputStream inProp = new FileInputStream("target/ofs-reports/report.xml");
        this.prop.loadFromXML(inProp);
        String reportRow = this.prop.getProperty("reportRow");
        String reportPath = this.prop.getProperty("report_xlsPath");
        int rowIndex = Integer.parseInt(reportRow);
        InputStream open = new FileInputStream(reportPath);
        XSSFWorkbook wb = new XSSFWorkbook(open);
        XSSFSheet xSSFSheet = wb.getSheet("Report");
        Row row1 = xSSFSheet.createRow((short)rowIndex);
        XSSFCellStyle xSSFCellStyle = wb.createCellStyle();
        XSSFFont xSSFFont = wb.createFont();
        xSSFFont.setColor(IndexedColors.BLACK.getIndex());
        xSSFFont.setBoldweight((short)700);
        xSSFCellStyle.setFont(xSSFFont);
        this.setCell(xSSFCellStyle, row1.createCell(0), heading);
        FileOutputStream fileOut = new FileOutputStream(reportPath);
        wb.write(fileOut);
        fileOut.close();
        inProp.close();
        FileOutputStream out = new FileOutputStream("./target/ofs-reports/report.xml");
        if (this.prop.getProperty("report_step_no") != null) {
            this.prop.remove("report_step_no");
        }

        this.prop.setProperty("reportRow", String.valueOf(Integer.parseInt(this.prop.getProperty("reportRow")) + 1));
        this.prop.storeToXML(out, "XML generated for report generation");
        out.close();
    }

    public void openReport() throws IOException {
        JOptionPane pane = new JOptionPane();
        final JDialog dialog = pane.createDialog("Choose an option");
        Object[] options = new Object[]{"Open Excel", "Open Folder", "Cancel"};
        final JFrame counter = new JFrame("Timer");
        (new Thread(new Runnable() {
            public void run() {
                try {
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    JLabel lab = new JLabel();
                    lab.revalidate();
                    counter.add(lab);
                    counter.getContentPane().setBackground(Color.white);
                    counter.setTitle("Timer");
                    counter.pack();
                    counter.setSize(counter.getWidth() + 50, counter.getHeight() + 50);
                    counter.setLocation(screenSize.width - counter.getWidth() - 200, screenSize.height - counter.getHeight() - 200);
                    counter.setAlwaysOnTop(true);
                    counter.setVisible(true);

                    for(int i = 10; i > 0; --i) {
                        Thread.sleep(1000L);
                        lab.setText("   " + i + "sec remaining");
                    }

                    counter.dispose();
                    dialog.dispose();
                } catch (Throwable var4) {
                }

            }
        })).start();
        int n = JOptionPane.showOptionDialog(dialog, "Please select an option", "Open Result", 1, 3, (Icon)null, options, options[2]);
        System.out.println(n);
        InputStream inProp = new FileInputStream("./target/ofs-reports/report.xml");
        this.prop.loadFromXML(inProp);
        if (n == 0) {
            counter.dispose();
            Desktop.getDesktop().open(new File(this.prop.getProperty("report_xlsPath")));
        } else if (n == 1) {
            Desktop.getDesktop().open(new File(this.prop.getProperty("report_xlsFolder")));
            counter.dispose();
        } else {
            counter.dispose();
        }

    }

    public void chooseResultFolder() throws IOException {
        String folder = "";

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException var6) {
            var6.printStackTrace();
        } catch (InstantiationException var7) {
            var7.printStackTrace();
        } catch (IllegalAccessException var8) {
            var8.printStackTrace();
        } catch (UnsupportedLookAndFeelException var9) {
            var9.printStackTrace();
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setDialogTitle("Select Report Folder");
        chooser.setFileSelectionMode(1);
        chooser.setAcceptAllFileFilterUsed(false);
        final JFrame jfra = new JFrame("Choose a folder");
        final JFrame counter = new JFrame("Timer");
        (new Thread(new Runnable() {
            public void run() {
                try {
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    JLabel lab = new JLabel();
                    lab.revalidate();
                    counter.add(lab);
                    counter.getContentPane().setBackground(Color.white);
                    counter.setTitle("Timer");
                    counter.pack();
                    counter.setSize(counter.getWidth() + 50, counter.getHeight() + 50);
                    counter.setLocation(screenSize.width - counter.getWidth() - 200, screenSize.height - counter.getHeight() - 200);
                    counter.setVisible(true);
                    counter.setAlwaysOnTop(true);

                    for(int i = 20; i > 0; --i) {
                        Thread.sleep(1000L);
                        lab.setText("   " + i + "sec remaining");
                        lab.revalidate();
                    }

                    counter.dispose();
                    jfra.dispose();
                } catch (Throwable var4) {
                }

            }
        })).start();
        if (chooser.showOpenDialog(jfra) == 0) {
            counter.dispose();
            System.out.println("getCurrentDirectory()\t: " + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() \t\t: " + chooser.getSelectedFile());
            folder = chooser.getSelectedFile().getPath();
        } else {
            System.out.println("No Selection ");
            counter.dispose();
        }

        if (folder.equals("")) {
            folder = "./target/ofs-reports";
        }

        FileOutputStream out = new FileOutputStream("./target/ofs-reports/report.xml");
        this.prop.setProperty("report_xlsFolder", folder);
        this.prop.storeToXML(out, "XML generated for report generation");
        out.close();
    }

    public void suspendScript(String task) throws NumberFormatException, IOException, ParseException {
        System.out.println("----------------------------");
        System.out.println(String.valueOf(task) + "\n Please dont click 'OK' untill you finish manual intervention");
        System.out.println("----------------------------");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception var4) {
        }

        JOptionPane jP = new JOptionPane(String.valueOf(task) + "\n Please dont click 'OK' untill you finish manual intervention");
        JDialog jd = jP.createDialog("Manual Intervention");
        jd.setAlwaysOnTop(true);
        jd.setVisible(true);
    }

    public String addImage(XSSFWorkbook wb, String screenRow) throws AWTException, IOException {
        int rowIndex = Integer.parseInt(screenRow) + 5;
        XSSFSheet xSSFSheet = wb.getSheet("Screenshots");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "jpeg", os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        byte[] bytes = IOUtils.toByteArray(is);
        int pictureIdx = wb.addPicture(bytes, 5);
        is.close();
        XSSFCreationHelper xSSFCreationHelper = wb.getCreationHelper();
        Drawing drawing = xSSFSheet.createDrawingPatriarch();
        XSSFClientAnchor xSSFClientAnchor = new XSSFClientAnchor(0, 0, 255, 255, 1, rowIndex, 17, rowIndex + 40);
        xSSFClientAnchor.setAnchorType(3);
        drawing.createPicture(xSSFClientAnchor, pictureIdx);
        return String.valueOf(rowIndex + 40);
    }

    public void setCell(CellStyle style, Cell cell, String value) {
        if (style != null) {
            cell.setCellStyle(style);
        }

        cell.setCellType(1);
        cell.setCellValue(value);
    }

    public void header(XSSFWorkbook xlsBk, Sheet rep_sheet, String hostName, String hostIP, String date, String time) throws ParseException {
        XSSFCellStyle xSSFCellStyle = xlsBk.createCellStyle();
        Row rowHeader = rep_sheet.createRow(0);
        Cell cellHeader = rowHeader.createCell(0);
        XSSFFont xSSFFont = xlsBk.createFont();
        xSSFFont.setFontName("Cambria");
        xSSFFont.setBoldweight((short)700);
        xSSFFont.setFontHeightInPoints((short)14);
        xSSFFont.setColor(IndexedColors.GREY_80_PERCENT.getIndex());
        xSSFCellStyle.setFont(xSSFFont);
        xSSFCellStyle.setAlignment((short)2);
        cellHeader.setCellStyle(xSSFCellStyle);
        cellHeader.setCellValue("O.R.A.F.T - Result (Powered by Oracle Functional Service)");
        rep_sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 9));
        xSSFCellStyle = xlsBk.createCellStyle();
        rowHeader = rep_sheet.createRow(3);
        cellHeader = rowHeader.createCell(0);
        xSSFFont = xlsBk.createFont();
        xSSFFont.setFontHeightInPoints((short)10);
        xSSFFont.setBoldweight((short)700);
        xSSFFont.setItalic(true);
        xSSFFont.setUnderline((byte)1);
        xSSFFont.setColor(IndexedColors.GREY_80_PERCENT.getIndex());
        xSSFCellStyle.setFont(xSSFFont);
        xSSFCellStyle.setAlignment((short)2);
        cellHeader.setCellStyle(xSSFCellStyle);
        cellHeader.setCellValue("( Oracle Reusable Automation Framework for Testing(O.R.A.F.T) - Result )");
        rep_sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 9));
        xSSFCellStyle = xlsBk.createCellStyle();
        rowHeader = rep_sheet.createRow(4);
        cellHeader = rowHeader.createCell(0);
        xSSFCellStyle.setAlignment((short)1);
        xSSFFont = xlsBk.createFont();
        xSSFFont.setFontHeightInPoints((short)8);
        xSSFFont.setBoldweight((short)700);
        xSSFFont.setItalic(true);
        xSSFFont.setColor(IndexedColors.GREY_80_PERCENT.getIndex());
        xSSFCellStyle.setFont(xSSFFont);
        cellHeader.setCellStyle(xSSFCellStyle);
        cellHeader.setCellValue("Executed By : " + hostName);
        rep_sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 4));
        rowHeader = rep_sheet.createRow(5);
        cellHeader = rowHeader.createCell(0);
        cellHeader.setCellStyle(xSSFCellStyle);
        cellHeader.setCellValue("System IP : " + hostIP);
        rep_sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 4));
        rowHeader = rep_sheet.createRow(6);
        cellHeader = rowHeader.createCell(0);
        cellHeader.setCellStyle(xSSFCellStyle);
        cellHeader.setCellValue("Automation Tool Name : Selenium For Oracle SaaS");
        rep_sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 4));
        rowHeader = rep_sheet.createRow(7);
        cellHeader = rowHeader.createCell(0);
        cellHeader.setCellStyle(xSSFCellStyle);
        System.out.println("TestCase_ID : " + strTC_ID);
        cellHeader.setCellValue("TestCase_ID : " + strTC_ID);
        rep_sheet.addMergedRegion(new CellRangeAddress(7, 7, 0, 4));
        rowHeader = rep_sheet.getRow(4);
        cellHeader = rowHeader.createCell(5);
        cellHeader.setCellStyle(xSSFCellStyle);
        cellHeader.setCellValue("Date : " + date);
        rep_sheet.addMergedRegion(new CellRangeAddress(4, 4, 5, 9));
        rowHeader = rep_sheet.getRow(5);
        cellHeader = rowHeader.createCell(5);
        cellHeader.setCellStyle(xSSFCellStyle);
        cellHeader.setCellValue("Creation Time : " + time);
        rep_sheet.addMergedRegion(new CellRangeAddress(5, 5, 5, 9));
        rowHeader = rep_sheet.getRow(6);
        cellHeader = rowHeader.createCell(5);
        if (report_File_Name != null && !report_File_Name.equals("")) {
            cellHeader.setCellStyle(xSSFCellStyle);
            cellHeader.setCellValue("Script : " + report_File_Name);
            rep_sheet.addMergedRegion(new CellRangeAddress(6, 6, 5, 9));
        }

        rowHeader = rep_sheet.getRow(7);
        cellHeader = rowHeader.createCell(5);
        cellHeader.setCellStyle(xSSFCellStyle);
        System.out.println("TestCase_Name : " + strTC_Name);
        cellHeader.setCellValue("TestCase_Name : " + strTC_Name);
        rep_sheet.addMergedRegion(new CellRangeAddress(7, 7, 5, 9));
    }

    public void createLink(XSSFWorkbook wb, int loc, String screenRow, String reportRow) throws IOException {
        XSSFCreationHelper xSSFCreationHelper = wb.getCreationHelper();
        XSSFCellStyle xSSFCellStyle = wb.createCellStyle();
        XSSFFont xSSFFont = wb.createFont();
        xSSFFont.setUnderline((byte)1);
        xSSFFont.setColor(IndexedColors.BLUE.getIndex());
        xSSFCellStyle.setFont(xSSFFont);
        XSSFSheet xSSFSheet = wb.getSheet("Report");
        Row row = xSSFSheet.getRow(Integer.parseInt(reportRow));
        Cell cell = row.createCell((short)loc);
        cell.setCellValue("View Screenshot");
        Hyperlink link2 = xSSFCreationHelper.createHyperlink(2);
        link2.setAddress("'Screenshots'!B" + (Integer.parseInt(screenRow) + 5) + ":Q" + (Integer.parseInt(screenRow) + 47));
        cell.setHyperlink(link2);
        cell.setCellStyle(xSSFCellStyle);
    }

    public String futureDate(String format, long noOfDays) {
        String reqDateFormat = format;
        String returnDate = "";
        Date date = new Date();
        if (format.indexOf("\"") != -1) {
            reqDateFormat = format.replace("\"", "");
        }

        SimpleDateFormat reqFormat = new SimpleDateFormat(reqDateFormat);
        if (noOfDays != 0L) {
            int oneDay = 86400000;
            String nextDate = reqFormat.format(date.getTime() + (long)oneDay * noOfDays);
            returnDate = nextDate;
        } else {
            returnDate = reqFormat.format(date.getTime());
        }

        return returnDate;
    }

    public void popup(String title, int timerInSec, Object... obj) {
        JOptionPane jP = new JOptionPane(obj);
        JDialog jD = jP.createDialog(title);
        jD.setAlwaysOnTop(true);
        if (timerInSec != 0) {
            this.timerThread(jD, timerInSec);
        }

        jD.setTitle("Manual Intervention ");
        jD.setVisible(true);
    }

    public void timerThread(final JDialog jD, final int counter) {
        Thread timer = new Thread(new Runnable() {
            public void run() {
                try {
                    int i = counter;

                    while(i >= 0) {
                        jD.setTitle("Manual Intervention " + i + "secs left");
                        Thread.sleep(1000L);
                        --i;
                        if (!jD.isVisible()) {
                            break;
                        }
                    }

                    if (jD.isVisible()) {
                        jD.dispose();
                    }
                } catch (Throwable var2) {
                }

            }
        });
        timer.start();
    }
}
