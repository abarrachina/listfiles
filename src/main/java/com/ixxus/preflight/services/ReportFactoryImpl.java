package com.ixxus.preflight.services;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * 
 * Simple implementation of ReportFactoryImpl using Spring 3 context inject.
 * 
 * @author Ignacio Carpintero Duran <ignacio.carpintero@ixxus.com>
 *
 */
@Service
public class ReportFactoryImpl implements ReportFactory {

    @Autowired
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext appCtx) throws BeansException {
        context = appCtx;
    }

    @Override
    public Report getInstance(String beanName) {
        return (Report) context.getBean(beanName);
    }

}
