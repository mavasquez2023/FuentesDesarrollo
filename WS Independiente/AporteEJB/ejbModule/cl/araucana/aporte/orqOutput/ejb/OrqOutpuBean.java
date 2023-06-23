package cl.araucana.aporte.orqOutput.ejb;

import cl.araucana.aporte.orqOutput.bo.OrqOutputResultBO;
import cl.araucana.aporte.orqOutput.logic.OrqOutputMgr;
import cl.araucana.aporte.orqOutput.logic.OrqOutputMgrImpl;

/**
 * Bean implementation class for Enterprise Bean: OrqOutputBean
 */
public class OrqOutpuBean implements javax.ejb.SessionBean {

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

    public OrqOutputResultBO recuperacionPago  (int rut, int montoCredito, int montoLeasing, int montoAporte, int periodoAporte, String fechaRecaudacion, String horaRecaudacion, String usuario, int documentoPago){
        OrqOutputMgr orqOutputMgr = new OrqOutputMgrImpl();
        OrqOutputResultBO orqOutputBO = orqOutputMgr.recuperacionPago(rut, montoCredito, montoLeasing, montoAporte, periodoAporte, fechaRecaudacion, horaRecaudacion, usuario, documentoPago);
        return orqOutputBO;
    }
}
