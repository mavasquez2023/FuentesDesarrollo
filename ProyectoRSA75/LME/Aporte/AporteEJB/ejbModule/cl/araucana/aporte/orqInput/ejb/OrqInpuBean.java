package cl.araucana.aporte.orqInput.ejb;

import cl.araucana.aporte.orqInput.bo.OrqInputResultBO;
import cl.araucana.aporte.orqInput.logic.OrqInputMgr;
import cl.araucana.aporte.orqInput.logic.OrqInputMgrImpl;

/**
 * Bean implementation class for Enterprise Bean: OrqInputBean
 */
public class OrqInpuBean implements javax.ejb.SessionBean {

    static final long serialVersionUID = 3206093459760846163L;
    private javax.ejb.SessionContext mySessionCtx;
    /**
     * getSessionContext
     */
    public javax.ejb.SessionContext getSessionContext() {
        return mySessionCtx;
    }
    /**
     * setSessionContext
     */
    public void setSessionContext(javax.ejb.SessionContext ctx) {
        mySessionCtx = ctx;
    }
    /**
     * ejbCreate
     */
    public void ejbCreate() throws javax.ejb.CreateException {
    }
    /**
     * ejbActivate
     */
    public void ejbActivate() {
    }
    /**
     * ejbPassivate
     */
    public void ejbPassivate() {
    }
    /**
     * ejbRemove
     */
    public void ejbRemove() {
    }

    public OrqInputResultBO obtenerInfoPago (int rut){
        //System.out.println("OrqInpuBean");
        OrqInputMgr orqInputMgr = new OrqInputMgrImpl();
        OrqInputResultBO orqInputBO = orqInputMgr.obtenerInfoPago(rut);
        return orqInputBO;
    }

}
