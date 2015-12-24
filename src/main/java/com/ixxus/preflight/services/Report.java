package com.ixxus.preflight.services;

import java.io.OutputStream;
import java.util.Map;

/**
 * Represents a kind of report
 * 
 * @author Ignacio Carpintero Duran <ignacio.carpintero@ixxus.com>
 *
 */
public interface Report {

    /**
     * Method to create a singular report
     * 
     * @param data
     * @param outputStream
     */
    public void create(Map<String, String> data, OutputStream outputStream);

}
