package com.axxoncloud.data.export;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stimulsoft.base.exception.StiException;
import com.stimulsoft.base.exception.StiExceptionProvider;
import com.stimulsoft.report.StiExportManager;
import com.stimulsoft.report.StiReport;
import com.stimulsoft.report.StiSerializeManager;
import com.stimulsoft.report.dictionary.databases.StiJsonDatabase;
import com.stimulsoft.report.enums.StiCalculationMode;
import com.stimulsoft.report.enums.StiExportFormat;
import com.stimulsoft.report.export.settings.StiPdfExportSettings;

import java.io.*;
import java.util.Arrays;

// https://www.stimulsoft.com/ru/samples/js/export-report-to-pdf
// https://forumru.stimulsoft.com/viewforum.php?f=19&sid=138c2b928e1dd8bcdb25fcbb23735b58

// Stimulsoft.Base.StiFontCollection.addOpentypeFontFile("Roboto-Black.ttf");

public class Main {

    final static String demoDir = "/Users/avgx/soft/itv/pos/stimulsoftreports.my.tests/stimulreport/";
    private static void createReport() {

        try {
            StiJsonDatabase jsonDatabase = new StiJsonDatabase("Demo", demoDir + "Demo.json");
            StiReport renderReport = StiSerializeManager.deserializeReport(new File(demoDir + "SimpleList.mrt"));
            renderReport.getDictionary().getDatabases().clear();
            renderReport.getDictionary().getDatabases().add(jsonDatabase);
            renderReport.setCalculationMode(StiCalculationMode.Interpretation);
            renderReport.Render(true);

            export(StiExportFormat.Pdf, renderReport);

        } catch (Exception e) {
            StiExceptionProvider.show(e, null);
        }

    }

    private static void export(StiExportFormat format, StiReport report) {

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(demoDir + "Demo.pdf");

            switch (format) {
                case Pdf:
                    StiPdfExportSettings pdfExportSettings = new StiPdfExportSettings();
                    pdfExportSettings.setStandardPdfFonts(true);
                    pdfExportSettings.setEmbeddedFonts(false);
                    StiExportManager.exportPdf(report, pdfExportSettings, outputStream);
                    break;
                case Xps:
                    StiExportManager.exportXps(report, outputStream);
                    break;
                case Html:
                    StiExportManager.exportHtml(report, outputStream);
                    break;
                case Text:
                    StiExportManager.exportText(report, outputStream);
                    break;
                case Rtf:
                    StiExportManager.exportRtf(report, outputStream);
                    break;
                case Word2007:
                    StiExportManager.exportWord2007(report, outputStream);
                    break;
                case Excel:
                    StiExportManager.exportExcel(report, outputStream);
                    break;
                case ExcelXml:
                    StiExportManager.exportExcelXml(report, outputStream);
                    break;
                case Excel2007:
                    StiExportManager.exportExcel2007(report, outputStream);
                    break;
                case Csv:
                    StiExportManager.exportCsv(report, outputStream);
                    break;
                case Xml:
                    StiExportManager.exportXml(report, outputStream);
                    break;
                case Sylk:
                    StiExportManager.exportSylk(report, outputStream);
                    break;
                case ImageBmp:
                    StiExportManager.exportImageBmp(report, outputStream);
                    break;
                case ImageJpeg:
                    StiExportManager.exportImageJpeg(report, outputStream);
                    break;
                case ImagePcx:
                    StiExportManager.exportImagePcx(report, outputStream);
                    break;
                case ImagePng:
                    StiExportManager.exportImagePng(report, outputStream);
                    break;
                case ImageSvg:
                    StiExportManager.exportImageSvg(report, outputStream);
                    break;
                case ImageSvgz:
                    StiExportManager.exportImageSvgz(report, outputStream);
                    break;

            }
        } catch (FileNotFoundException e) {
            StiExceptionProvider.show(e, null);
        } catch (StiException e) {
            StiExceptionProvider.show(e, null);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    StiExceptionProvider.show(e, null);
                }
            }
        }
    }

    public static void main(final String[] args) {

        System.out.println(Main.class.getPackage().getName());
        long start = System.currentTimeMillis();
//        log.debug("export...");

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable ex) {
//                log.debug("Crashed thread " + t.getName());
//                log.debug("Exception was: " + ex.toString());
            }
        });

        createReport();
//
//        try {
//            String jsonFile = Arrays.stream(args).filter(s -> s.endsWith(".json")).findFirst().get();
//
//            String outFile = Arrays.stream(args).filter(s -> !jsonFile.equals(s))
//                    .findFirst().get();
////            String ext = FilenameUtils.getExtension(outFile);
////            GenericReport.Format format = GenericReport.Format.valueOf(ext);
//
//            //String jsonFile = "/Users/avgx/soft/itv/pos/axxonnext.data/backend/export-lib/r1.json";
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(jsonFile));
//            //Reader jsonReader = Files.readAllBytes(jsonFile, StandardCharsets.UTF_8);
//            Gson gson = new GsonBuilder().create();
////            GenericReport report = gson.fromJson(bufferedReader, GenericReport.class);
////            String outputFile = String.format("%s_%s.%s", report.title, report.date, format);
////            System.out.println("Output: " + outputFile);
//
////            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//
////            ExportHelper.export(format, report, outputFile);
//
////            byte[] result = stream.toByteArray();
////            System.out.println("result: " + new Integer(result.length).toString());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
//        log.debug("export time : " + (System.currentTimeMillis() - start));
    }
}

