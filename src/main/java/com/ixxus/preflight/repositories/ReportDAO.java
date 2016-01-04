package com.ixxus.preflight.repositories;

import java.util.List;
import java.util.Map;

import com.ixxus.preflight.model.File;

public interface ReportDAO {
    
    public List<File> getResults(Map<String, String> options);
}
