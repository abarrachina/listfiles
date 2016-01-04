package com.ixxus.preflight.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface ReportController {

    public ModelAndView generateHTMLPage(String path, ModelAndView mv);

    public void generatePDFPage(String path, HttpServletResponse response);
}
