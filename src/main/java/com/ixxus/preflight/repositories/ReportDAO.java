package com.ixxus.preflight.repositories;

import java.util.List;
import java.util.Map;

import com.ixxus.preflight.model.FileResult;

public interface ReportDAO {
    
    public List<FileResult> getResults(Map<String, String> options);
}
