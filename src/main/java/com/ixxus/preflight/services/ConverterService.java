package com.ixxus.preflight.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.lowagie.text.DocumentException;

public interface ConverterService {

    public void convert(InputStream is, OutputStream os) throws DocumentException, IOException, ParserConfigurationException, SAXException;

}
