package com.ixxus.preflight.services;

import org.springframework.context.ApplicationContextAware;

/**
 * 
 * <p>
 * Glossary Terms Service Factory Interface to obtain a Glossary Terms Service Implementation
 * </p>
 * 
 * @author Ignacio Carpintero Duran <ignacio.carpintero@ixxus.com>
 *
 */
public interface ReportFactory extends ApplicationContextAware {

    /**
     * 
     * Get Instance for GlossaryTermsService valid in Spring context.
     * 
     * @param beanName Correct bean identifier in Spring context
     * @return a valid object.
     */
    public Report getInstance(String beanName);
}
