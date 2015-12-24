package com.ixxus.preflight.services;

import java.io.OutputStream;
import java.util.Map;

/**
 * 
 * Service to create reports in prefligh app.
 * 
 * @author Ignacio Carpintero Duran <ignacio.carpintero@ixxus.com>
 *
 */
public interface ReportService {

    /**
     * 
     * Method, giving a report identifier, creates a associated report.
     * 
     * @param reportName report identifier
     * @param options options for configure report
     * @param outputStream output channel
     */
    public void generateReport(String reportName, Map<String, String> options, OutputStream outputStream);

    public Map<String, String> getResults(Map<String, String> options);

}
