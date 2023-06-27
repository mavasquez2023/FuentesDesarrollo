package cl.araucana.prestamo.dao;


import java.util.ArrayList;
import java.util.Collection;

import cl.araucana.common.BusinessException;

/**
 * @author aituarte
 * Metodos expuestos por PrestamoDAO
 */
public interface PrestamoDAO {

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

	public ArrayList getCuotas(String rut ) throws Exception, BusinessException;


	/**
	 * @param rut
	 * @param numCuotaActual
	 * @param numTotalCuotas
	 * @return
	 */
	public Collection getMontoUFPrestamoActivo(String rut, int cuotaLimiteInf, int numTotalCuotas) throws Exception, BusinessException;

	/**
	 * @param idPrestamo
	 * @return
	 */
	public Collection getInteresesPagoAnticipado(long idPrestamo,int mes, int anioActual) throws Exception, BusinessException;


	/**
	 * @param idPrestamo
	 * @param month
	 * @param year
	 * @return
	 */
	public Collection getInteresesPagoAnticipadoPeriodo(long idPrestamo, int idCuota) throws Exception, BusinessException ;

}
