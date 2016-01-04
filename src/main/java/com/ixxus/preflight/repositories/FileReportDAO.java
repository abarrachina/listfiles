package com.ixxus.preflight.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.File;

import org.springframework.stereotype.Repository;

import com.ixxus.preflight.model.FileResult;

@Repository
public class FileReportDAO implements ReportDAO {

    @Override
    public List<FileResult> getResults(Map<String, String> options) {
    	
    	String path = options.get("path");
    	List<FileResult> newlist = new ArrayList<FileResult>();
    	
    	if (path != null){
    		File f = new File(path);
        	
        	if (f.exists()){
        		File[] ficheros = f.listFiles();
        		for (int x=0;x<ficheros.length;x++){
        			newlist.add(new FileResult(ficheros[x].getName(), "Undefined"));
        		}
        	}
        	else{
        		newlist.add(new FileResult("Directory doesn't exist", "Undefined"));
        	}    		
    	}
    	else{
    		newlist.add(new FileResult("Directory doesn't exist", "Undefined"));
    	}    	
  	
        return newlist;
    }
}
