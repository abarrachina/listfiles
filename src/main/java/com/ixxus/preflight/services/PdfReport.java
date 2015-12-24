package com.ixxus.preflight.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
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

public class PdfReport implements Report {

    private String templatePath;

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    @Override
    public void create(Map<String, String> data, OutputStream outputStream) {

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            Template template = cfg.getTemplate(templatePath);

            // TODO Surround in try / catch

            Writer out = new OutputStreamWriter(baos);
            template.process(data, out);
            out.flush();

            // TODO Surround in try / catch
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            convert(is, outputStream);

            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {

            // TODO Control this
            e.printStackTrace();
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
