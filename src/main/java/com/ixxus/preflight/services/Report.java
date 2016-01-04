package com.ixxus.preflight.services;

import java.io.OutputStream;
import java.util.List;

import com.ixxus.preflight.model.File;

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
    public void create(List<File> data, OutputStream outputStream);

}
