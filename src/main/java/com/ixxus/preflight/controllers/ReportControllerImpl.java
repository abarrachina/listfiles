package com.ixxus.preflight.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ixxus.preflight.services.ReportService;

/**
 * 
 * It represents Controller to create a report with html and pdf format
 * 
 * @author Ignacio Carpintero Duran <ignacio.carpintero@ixxus.com>
 *
 */
@Controller
public class ReportControllerImpl implements ReportController {

    @Autowired
    private ReportService reportService;

    @Value("test-template")
    private String generateHTMLPageTemplate;

    /**
     * Method in spring MVC to create web page with the report
     * 
     * @param mv Spring model and view
     * @return
     */
    @RequestMapping(value = "/")
    public ModelAndView generateHTMLPage(ModelAndView mv) {

        Map<String, String> options = new HashMap<>();
        Map<String, String> results = reportService.getResults(options);

        results.forEach((key, value) -> mv.addObject(key, value));

        mv.setViewName(generateHTMLPageTemplate);
        return mv;
    }

    @RequestMapping(value = "/pdf")
    public void generatePDFPage(HttpServletResponse response) {

        Map<String, String> options = new HashMap<>();
        
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            reportService.generateReport("finalPdfReport", options, response.getOutputStream());
            os.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
