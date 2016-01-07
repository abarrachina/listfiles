package com.ixxus.preflight.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ixxus.preflight.model.FileResult;
import com.ixxus.preflight.services.ListReportService;

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
    private ListReportService reportService;

    @Value("test-template")
    private String generateHTMLPageTemplate;
    
    @Value("${reportStrategy}")
    private String reportStrategy;

    /**
     * Method in spring MVC to create web page with the report
     * 
     * @param mv Spring model and view
     * @return
     */
    @RequestMapping(value = "/")
    public ModelAndView generateHTMLPage(@RequestParam String folderPath, ModelAndView mv) {
    	Map<String,String> options = new HashMap<>();
    	options.put("path", folderPath);
    	List<FileResult> newlist = reportService.getResults(options);
    	
    	mv.addObject("files", newlist);
        mv.addObject("folderPath", "/pdf?folderPath="+folderPath);
        
        mv.setViewName("listfiles");
        
        return mv;
    }

    @RequestMapping(value = "/pdf")
    public void generatePDFPage(@RequestParam String folderPath,HttpServletResponse response) {

        Map<String, String> options = new HashMap<>();
        options.put("path", folderPath);
           	        
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            reportService.generateReport(reportStrategy, options, response.getOutputStream());
            os.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
