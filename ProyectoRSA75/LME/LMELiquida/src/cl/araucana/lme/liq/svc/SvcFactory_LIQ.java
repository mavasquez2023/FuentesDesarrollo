/*
 * Created on 16-10-2011
 *
 */
package cl.araucana.lme.liq.svc;

import cl.araucana.lme.liq.svc.impl.AS400SvcImpl_LIQ;;

/**
 * @author j-factory
 *
 */
public class SvcFactory_LIQ {

	private IAS400Svc_LIQ as400Svc_LME = null;
	private static SvcFactory_LIQ svcFactory_LME = null;

	private SvcFactory_LIQ() {
		as400Svc_LME = new AS400SvcImpl_LIQ();
	}

	private static SvcFactory_LIQ getInstance() {
		if (svcFactory_LME == null)
			svcFactory_LME = new SvcFactory_LIQ();
		return svcFactory_LME;
	}

	public static IAS400Svc_LIQ getAS400Svc_LME() {
		return getInstance().as400Svc_LME;
	}

	public static void setSvcFactory_LME(SvcFactory_LIQ svcFactory_LME) {
		SvcFactory_LIQ.svcFactory_LME = svcFactory_LME;
	}

	
}
