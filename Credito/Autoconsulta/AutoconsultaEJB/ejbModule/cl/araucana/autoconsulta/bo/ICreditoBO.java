package cl.araucana.autoconsulta.bo;

import java.util.Collection;

import cl.araucana.common.BusinessException;
import cl.laaraucana.credito.to.CreditoTO;
/*
 * 2013 07 01
 */
public interface ICreditoBO {
	
	
	/**
	 * Inicializa rut de consulta de credito
	 * @param long rutAfiliado
	 * @return no hay
	 */
	public void setRut(long rutAfiliado);

	/**
	 * Inicializa rut de consulta de credito
	 * @param UsuarioVO usrVo
	 * @return no hay
	 */
	public void setRut(cl.araucana.autoconsulta.vo.UsuarioVO usrVo);

	/**
	 * Inicializa rut de consulta de credito
	 * @param long rutEmpleador, long rutAfiliado
	 * @return no hay
	 */
	public void setRut(long rutEmpleador, long rutAfiliado);

	/**
	 * Inicializa BO con un credito
	 * @param CreditoTO credito
	 * @return no hay
	 */
	public void setCredito(CreditoTO credito);

	/**
	 * Obtiene los credito de un afiliado, segun rut inicializado
	 * @param 
	 * @return Collection CreditoTO
	 */
	public Collection getCreditos();

	/**
	 * Obtiene el credito de un afiliado, segun credito de inicializacion
	 * @param 
	 * @return Collection CreditoTO
	 */
	public Collection getCredito();

	/**
	 * Obtiene los pagos de un credito, segun credito de inicializacion
	 * @param 
	 * @return Collection PagoCuotaTO
	 */
	public Collection obtenerPagos();

	/**
	 * Obtiene las cuotas de un credito, segun credito de inicializacion
	 * @param 
	 * @return Collection CuotaTO
	 */
	public Collection obtenerCuotas();

	/**
	 * Objeto: Consulta de seguros comprometidos
	 * @param String oficina, String folio
	 * @return coleccion de SeguroComprometidoTO
	 * @author adiaz ( Artered Chile )
	 * @version 1.0
	 */
	public Collection obtenerSegurosCredito();

}
