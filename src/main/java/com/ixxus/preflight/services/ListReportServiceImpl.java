package com.ixxus.preflight.services;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ixxus.preflight.model.FileResult;
import com.ixxus.preflight.repositories.ReportDAO;

@Service
public class ListReportServiceImpl implements ListReportService {

    @Autowired
    private ReportFactory reportFactory;

    @Autowired
    @Qualifier ("fileReportDAO")
    private ReportDAO reportDAO;

    @Override
    public List<FileResult> getResults(Map<String, String> options) {
        return reportDAO.getResults(options);
    }

    @Override
    public void generateReport(String reportName, Map<String, String> options, OutputStream outputStream) {

        List<FileResult> data = reportDAO.getResults(options);
        Report report = reportFactory.getInstance(reportName);
        report.create(data, outputStream);
    }

}
