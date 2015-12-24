package com.ixxus.preflight.services;

import java.io.OutputStream;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ixxus.preflight.repositories.ReportDAO;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportFactory reportFactory;

    @Autowired
    private ReportDAO reportDAO;

    @Override
    public Map<String, String> getResults(Map<String, String> options) {
        return reportDAO.getResults(options);
    }

    @Override
    public void generateReport(String reportName, Map<String, String> options, OutputStream outputStream) {

        Map<String, String> data = reportDAO.getResults(options);
        Report report = reportFactory.getInstance(reportName);
        report.create(data, outputStream);
    }

}
