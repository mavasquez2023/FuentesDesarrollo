package cl.araucana.autoconsulta.serv.ServicesCreditoSLBean;

import java.rmi.RemoteException;
import java.util.Collection;

import cl.araucana.common.BusinessException;
import cl.laaraucana.credito.to.CreditoTO;

/**
 * Remote interface for Enterprise Bean: ServicesCredito
 */
public interface ServicesCredito extends javax.ejb.EJBObject {
	/**
	 * Inicializa rut de consulta de credito
	 * @param long rutAfiliado
	 * @return no hay
	 */
	public void setRut(long rutAfiliado) throws Exception, BusinessException, RemoteException;

	/**
	 * Inicializa rut de consulta de credito
	 * @param long rutEmpleador, long rutAfiliado
	 * @return no hay
	 */
	public void setRut(long rutEmpleador, long rutAfiliado) throws Exception, BusinessException, RemoteException;

	/**
	 * Inicializa BO con un credito
	 * @param CreditoTO credito
	 * @return no hay
	 */
	public void setCredito(CreditoTO credito) throws Exception, BusinessException, RemoteException;

	/**
	 * Obtiene los credito de un afiliado, segun rut inicializado
	 * @param 
	 * @return Collection CreditoTO
	 */
	public Collection getCreditos() throws Exception, BusinessException, RemoteException;

	/**
	 * Obtiene el credito de un afiliado, segun credito de inicializacion
	 * @param 
	 * @return Collection CreditoTO
	 */
	public Collection getCredito() throws Exception, BusinessException, RemoteException;

	/**
	 * Obtiene los pagos de un credito, segun credito de inicializacion
	 * @param 
	 * @return Collection PagoCuotaTO
	 */
	public Collection obtenerPagos() throws Exception, BusinessException, RemoteException;

	/**
	 * Obtiene las cuotas de un credito, segun credito de inicializacion
	 * @param 
	 * @return Collection CuotaTO
	 */
	public Collection obtenerCuotas() throws Exception, BusinessException, RemoteException;

	/**
	 * Objeto: Consulta de seguros comprometidos
	 * @param String oficina, String folio
	 * @return coleccion de SeguroComprometidoTO
	 * @author adiaz ( Artered Chile )
	 * @version 1.0
	 */
	public Collection getSegurosCredito(String oficina, String folio);

}
