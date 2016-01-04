package com.ixxus.preflight.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xml.sax.SAXException;

import com.lowagie.text.DocumentException;

import freemarker.template.Configuration;
import freemarker.template.Template;

import com.ixxus.preflight.model.FileResult;

public class PdfReport implements Report {

    private String templatePath;

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    @Override
    public void create(List<FileResult> data, OutputStream outputStream) {

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setClassForTemplateLoading(PdfReport.class, "../../../../templates");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Writer out = null;
        InputStream is = null;
        try {
            Template template = cfg.getTemplate(templatePath);

            out = new OutputStreamWriter(baos);
            Map<String, Object> listFile = new HashMap<>();
            listFile.put("files", data);
            template.process(listFile, out);
            out.flush();

            is = new ByteArrayInputStream(baos.toByteArray());
            convert(is, outputStream);
            is.close();

        } catch (Exception e) {
            throw new ReportException(e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    protected void convert(InputStream is, OutputStream os) throws DocumentException, IOException, ParserConfigurationException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(is);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(doc, null);
        renderer.layout();
        renderer.createPDF(os);
        os.close();
    }

}
