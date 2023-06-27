/*
 * Created on 16-10-2011
 *
 */
package com.microsystem.lme.svc;

import com.microsystem.lme.svc.impl.AS400SvcImpl_SIL;

/**
 * @author microsystem
 *
 */
public class SvcFactory_SIL {

	private IAS400Svc_SIL as400Svc_SIL = null;
	private static SvcFactory_SIL svcFactory_SIL = null;

	private SvcFactory_SIL() {
		as400Svc_SIL = new AS400SvcImpl_SIL();
	}

	private static SvcFactory_SIL getInstance() {
		if (svcFactory_SIL == null)
			svcFactory_SIL = new SvcFactory_SIL();
		return svcFactory_SIL;
	}

	public static IAS400Svc_SIL getAS400Svc_SIL() {
		return getInstance().as400Svc_SIL;
	}

	public static void setSvcFactory_SIL(SvcFactory_SIL svcFactory_SIL) {
		SvcFactory_SIL.svcFactory_SIL = svcFactory_SIL;
	}

	
}
