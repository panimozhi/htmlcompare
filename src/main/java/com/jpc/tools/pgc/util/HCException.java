package com.jpc.tools.pgc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpc.tools.pgc.schedule.HCScheduler;

public class HCException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static Logger logger = LoggerFactory.getLogger(HCScheduler.class.getName());

	public HCException() {
		super();
	}

	public HCException(String exception, Throwable e) {
		super(exception, e);
		logger.error(exception, e);
	}
}
