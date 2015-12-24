package com.ixxus.preflight.repositories;

import java.util.Map;

public interface ReportDAO {
    
    public Map<String, String> getResults(Map<String, String> options);
}
