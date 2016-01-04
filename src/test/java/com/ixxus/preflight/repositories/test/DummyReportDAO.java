package com.ixxus.preflight.repositories.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ixxus.preflight.model.FileResult;
import com.ixxus.preflight.repositories.ReportDAO;

@Repository
public class DummyReportDAO implements ReportDAO {

    @Override
    public List<FileResult> getResults(Map<String, String> options) {
        // TODO Auto-generated method stub

    	List<FileResult> newlist = new ArrayList<FileResult>();
    	newlist.add(new FileResult("Document 1", "Incorrect"));
    	newlist.add(new FileResult("Document 2", "Correct"));
    	newlist.add(new FileResult("Document 3", "Uncompleted"));
    	newlist.add(new FileResult("Document 4", "Correct"));
    	
        return newlist;
    }

}
