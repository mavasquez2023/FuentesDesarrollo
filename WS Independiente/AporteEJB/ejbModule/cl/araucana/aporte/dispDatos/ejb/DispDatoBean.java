package cl.araucana.aporte.dispDatos.ejb;

import cl.araucana.aporte.dispDatos.bo.DispDatosResultBO;
import cl.araucana.aporte.dispDatos.logic.DispDatosMgr;
import cl.araucana.aporte.dispDatos.logic.DispDatosMgrImpl;

/**
 * Bean implementation class for Enterprise Bean: DispDatosBean
 */
public class DispDatoBean implements javax.ejb.SessionBean {

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
    public DispDatosResultBO obtenerInfoDatos (int rut){
        //System.out.println("DispDatoBean");
        DispDatosMgr dispDatosMgr  = new DispDatosMgrImpl();
        DispDatosResultBO dispdatosResult = dispDatosMgr.obtenerInfoDatos(rut);
        return dispdatosResult;

    }	
}
