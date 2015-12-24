package com.ixxus.preflight.repositories;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class DummyReportDAO implements ReportDAO {

    @Override
    public Map<String, String> getResults(Map<String, String> options) {
        // TODO Auto-generated method stub

        HashMap<String, String> results = new HashMap<>();

        results.put("blogTitle", "Freemarker Template Demo using Spring by Tacho");
        results.put("message", "Getting started with Freemarker.<br/>Find a Freemarker template demo using Spring.");

        return results;
    }

}
