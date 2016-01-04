package com.ixxus.preflight.repositories.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ixxus.preflight.model.File;
import com.ixxus.preflight.repositories.ReportDAO;

@Repository
public class DummyReportDAO implements ReportDAO {

    @Override
    public List<File> getResults(Map<String, String> options) {
        // TODO Auto-generated method stub

    	List<File> newlist = new ArrayList<File>();
    	newlist.add(new File("Document 1", "Incorrect"));
    	newlist.add(new File("Document 2", "Correct"));
    	newlist.add(new File("Document 3", "Uncompleted"));
    	newlist.add(new File("Document 4", "Correct"));
    	
        return newlist;
    }

}
