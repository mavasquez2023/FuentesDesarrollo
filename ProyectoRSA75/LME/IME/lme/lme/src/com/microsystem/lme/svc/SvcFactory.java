/*
 * Created on 16-10-2011
 *
 */
package com.microsystem.lme.svc;

import com.microsystem.lme.svc.impl.AS400SvcImpl;

/**
 * @author microsystem
 *
 */
public class SvcFactory {

	private IAS400Svc as400Svc = null;
	private static SvcFactory svcFactory = null;

	private SvcFactory() {
		as400Svc = new AS400SvcImpl();
	}

	private static SvcFactory getInstance() {
		if (svcFactory == null)
			svcFactory = new SvcFactory();
		return svcFactory;
	}

	public static IAS400Svc getAS400Svc() {
		return getInstance().as400Svc;
	}

	public static void setSvcFactory(SvcFactory svcFactory) {
		SvcFactory.svcFactory = svcFactory;
	}

	
}
