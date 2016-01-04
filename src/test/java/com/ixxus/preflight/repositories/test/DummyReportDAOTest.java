package com.ixxus.preflight.repositories.test;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ixxus.preflight.FreemarkerApplication;
import com.ixxus.preflight.model.FileResult;
import com.ixxus.preflight.repositories.ReportDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FreemarkerApplication.class)
@WebAppConfiguration
public class DummyReportDAOTest {

    @Autowired
    @Qualifier("dummyReportDAO")
    private ReportDAO reportDAO;

    @Test
    public void contextLoads() {
        Assert.assertNotNull(reportDAO);
    }

    @Test
    public void getResultsNull() {
        List<FileResult> results = reportDAO.getResults(null);
        Assert.assertNotNull(results);
    }

    @Test
    public void getResultsNewObject() {
        List<FileResult> results = reportDAO.getResults(new HashMap<>());
        Assert.assertNotNull(results);
    }

}
