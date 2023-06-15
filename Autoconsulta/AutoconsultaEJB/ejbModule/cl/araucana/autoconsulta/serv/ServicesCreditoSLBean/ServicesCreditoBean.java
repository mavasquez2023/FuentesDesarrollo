package cl.araucana.autoconsulta.serv.ServicesCreditoSLBean;

import java.util.Collection;

import org.apache.log4j.Logger;

import cl.araucana.autoconsulta.bo.ICreditoBO;
import cl.araucana.autoconsulta.bo.impl.CreditoBO;
import cl.laaraucana.credito.to.CreditoTO;

/**
 * @author asepulveda
 * Bean implementation class for Enterprise Bean: ServicesAutoconsulta
 * Servicios de Consulta a Información de La Araucana
 */
public class ServicesCreditoBean implements javax.ejb.SessionBean {
	Logger logger = Logger.getLogger(ServicesCreditoBean.class);
	ICreditoBO creditoBO;
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
		creditoBO = new CreditoBO();
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

	/**
	 * Inicializa rut de consulta de credito
	 * @param long rut
	 * @return no hay
	 */
	public void setRut(long rut) {
		creditoBO.setRut(rut);
	}

	/**
	 * Inicializa rut de consulta de credito
	 * @param long rutEmpleador, long rutAfiliado
	 * @return no hay
	 */
	public void setRut(long rutEmpleador, long rutAfiliado) {
		creditoBO.setRut(rutEmpleador, rutAfiliado);
	}

	/**
	 * Inicializa BO con un credito
	 * @param CreditoTO credito
	 * @return no hay
	 */
	public void setCredito(CreditoTO credito) {
		creditoBO.setCredito(credito);
	}

	/**
	 * Obtiene los credito de un afiliado, segun rut inicializado
	 * @param 
	 * @return Collection CreditoTO
	 */
	public Collection getCreditos() {
		return creditoBO.getCreditos();
	}

	/**
	 * Obtiene el credito de un afiliado, segun credito de inicializacion
	 * @param 
	 * @return Collection CreditoTO
	 */
	public Collection getCredito() {
		return creditoBO.getCredito();
	}

	/**
	 * Obtiene los pagos de un credito, segun credito de inicializacion
	 * @param 
	 * @return Collection PagoCuotaTO
	 */
	public Collection obtenerPagos() {
		return creditoBO.obtenerPagos();
	}

	/**
	 * Obtiene las cuotas de un credito, segun credito de inicializacion
	 * @param 
	 * @return Collection CuotaTO
	 */
	public Collection obtenerCuotas() {
		return creditoBO.obtenerCuotas();
	}
	/**
	 * Objeto: Consulta seguros comprometidos, segun credito de inicializacion
	 * @param 
	 * @return coleccion de SeguroComprometidoTO
	 * @author adiaz ( Artered Chile )
	 * @version 1.0
	 */
	public Collection getSegurosCredito() {
		return creditoBO.obtenerSegurosCredito();
	}

}
