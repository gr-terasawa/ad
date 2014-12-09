/*-
 * Copyright (c) 2004-2014 GMO-RESEARCH Inc. All rights reserved.
 * Redistribution in source and binary forms, with or without
 * modification, is limited by contract. 
 */
package jp.gmor.research.ad.listener;

import javax.servlet.ServletContextEvent;

import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

import ch.qos.logback.classic.LoggerContext;

/**
 * @author usr160056
 * @since 2014/12/09
 */
public class MyContextLoaderListener
        extends ContextLoaderListener {

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        if ( LoggerFactory.getILoggerFactory() instanceof LoggerContext ) {
            ((LoggerContext) LoggerFactory.getILoggerFactory() ).stop();
        }
        super.contextDestroyed(event);
    }
}
