package com.ixxus.preflight.controllers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import com.ixxus.preflight.services.ConverterService;
import com.lowagie.text.DocumentException;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

import java.util.ArrayList;
import java.util.List;

import com.ixxus.preflight.Model.File;

/**
 * 
 * It represents Controller to create a report with html and pdf format
 * 
 * @author Ignacio Carpintero Duran <ignacio.carpintero@ixxus.com>
 *
 */
@Controller
public class FreeMarketCustomController {

    // TODO Change for correct service
    @Autowired
    private ConverterService converterService;

    /**
     * Method in spring mvc to create web page with the report
     * 
     * @param mv Spring model and view
     * @return
     */
    @RequestMapping(value = "/")
    public ModelAndView generateHTMLPage(ModelAndView mv) {
    	
    	List<File> newlist = new ArrayList<File>();
    	newlist.add(new File("Document 1", "Incorrect"));
    	newlist.add(new File("Document 2", "Correct"));
    	newlist.add(new File("Document 3", "Incorrect"));
    	newlist.add(new File("Document 4", "Correct"));
    	
        // TODO Change this code to service code 
        mv.addObject("files", newlist);

        // TODO Configurable
        mv.setViewName("listfiles");
        return mv;
    }

    /**
     * 
     * @param response
     * @throws TemplateNotFoundException
     * @throws MalformedTemplateNameException
     * @throws ParseException
     * @throws IOException
     * @throws TemplateException
     * @throws DocumentException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    @RequestMapping(value = "/pdf")
    public void generatePDFPage(HttpServletResponse response) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException,
            TemplateException, DocumentException, ParserConfigurationException, SAXException {

        // TODO Change this code to service code 
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("blogTitle", "Freemarker Template Demo using Spring by Tacho");
        data.put("message", "Getting started with Freemarker.<br/>Find a Freemarker template demo using Spring.");

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);

        Template template = cfg.getTemplate("src/main/webapp/WEB-INF/views/tacho-template.ftl");

        // TODO Surround in try / catch
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Writer out = new OutputStreamWriter(baos);
        template.process(data, out);
        out.flush();

        // TODO Surround in try / catch
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        OutputStream os = response.getOutputStream();
        converterService.convert(is, os);

        os.flush();
        os.close();
    }

}
