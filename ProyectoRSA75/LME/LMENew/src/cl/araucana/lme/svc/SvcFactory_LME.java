/*
 * Created on 16-10-2011
 *
 */
package cl.araucana.lme.svc;

import cl.araucana.lme.svc.impl.AS400SvcImpl_LME;

/**
 * @author j-factory
 *
 */
public class SvcFactory_LME {

	private IAS400Svc_LME as400Svc_LME = null;
	private static SvcFactory_LME svcFactory_LME = null;

	private SvcFactory_LME() {
		as400Svc_LME = new AS400SvcImpl_LME();
	}

	private static SvcFactory_LME getInstance() {
		if (svcFactory_LME == null)
			svcFactory_LME = new SvcFactory_LME();
		return svcFactory_LME;
	}

	public static IAS400Svc_LME getAS400Svc_LME() {
		return getInstance().as400Svc_LME;
	}

	public static void setSvcFactory_LME(SvcFactory_LME svcFactory_LME) {
		SvcFactory_LME.svcFactory_LME = svcFactory_LME;
	}

	
}
