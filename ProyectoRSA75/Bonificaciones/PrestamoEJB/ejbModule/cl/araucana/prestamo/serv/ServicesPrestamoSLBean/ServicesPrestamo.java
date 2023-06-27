package cl.araucana.prestamo.serv.ServicesPrestamoSLBean;

import java.util.ArrayList;
import java.util.Collection;

import cl.araucana.common.BusinessException;
import cl.araucana.prestamo.vo.ImpuestoVO;



/**
 * Remote interface for Enterprise Bean: ServicesPrestamo
 */
public interface ServicesPrestamo extends javax.ejb.EJBObject {
	
	/**
	 * Calcula el valor del impuesto segun nº de cuotas y monto de dinero solicitado
	 * @param monto
	 * @param numCuotas
	 * @return Impuesto
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ImpuestoVO calcularImpuesto(double monto, int numCuotas) throws Exception,BusinessException;
   	
   	
	/**
	 * Obtiene las cuotas por Cobrar
	 * @return ArrayList de Cuotas
	 * @throws Exception
	 * @throws BusinessException
	 */ 

	public ArrayList getCuotas() throws Exception, BusinessException;

	/**
	 * Obtiene las cuotas por Cobrar de un afiliado
	 * @return ArrayList de Cuotas
	 * @throws Exception
	 * @throws BusinessException
	 */ 

	public ArrayList getCuotas(String rut) throws Exception, BusinessException;
	
	
	/**
	 * @param rut
	 * @param numCuotaActual
	 * @param numTotalCuotas
	 * return Collection
	 */
	public Collection getMontoUFPrestamoActivo(String rut, int numCuota, int numTotalCuotas)  throws Exception, BusinessException;


	/**
	 * @param idPrestamo
	 */
	public Collection getInteresesPagoAnticipado(long idPrestamo,int cuotaLimIni,int cuotaLimFinal)  throws Exception, BusinessException;
	
	public Collection getInteresPagoAnticipadoPeriodo(long idPrestamo,int cuota)  throws Exception, BusinessException;
}
