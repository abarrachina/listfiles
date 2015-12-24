package com.ixxus.preflight.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface ReportController {

    public ModelAndView generateHTMLPage(ModelAndView mv);

    public void generatePDFPage(HttpServletResponse response);
}
