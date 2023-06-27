package cl.araucana.prestamo.dao;

import java.util.ArrayList;
import java.util.Collection;

import cl.araucana.common.BusinessException;
import cl.araucana.prestamo.vo.CuotaVO;

import com.schema.util.InstanceGenerator;

/**
 * @author asepulveda
 *
 */
public class DummyPrestamoDAO implements PrestamoDAO {
	
	/**
	 * Obtiene las cuotas por Cobrar
	 * @return ArrayList de CuotaVO
	 * @throws Exception
	 * @throws BusinessException
	 */ 

	public ArrayList getCuotas() throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(CuotaVO.class);
		
	}


	/* (no Javadoc)
	 * @see cl.araucana.prestamo.dao.PrestamoDAO#getMontoUFPrestamoActivo(java.lang.String, int, int)
	 */
	public Collection getMontoUFPrestamoActivo(String rut, int numCuotaActual, int numTotalCuotas) throws Exception, BusinessException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	/* (no Javadoc)
	 * @see cl.araucana.prestamo.dao.PrestamoDAO#getInteresesPagoAnticipado(long)
	 */
	public Collection getInteresesPagoAnticipado(long idPrestamo,int mes, int anioActual) throws Exception, BusinessException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}


	/* (no Javadoc)
	 * @see cl.araucana.prestamo.dao.PrestamoDAO#getInteresesPagoAnticipadoPeriodo(long, int, int)
	 */
	public Collection getInteresesPagoAnticipadoPeriodo(long idPrestamo, int month, int year)throws Exception, BusinessException  {
		// TODO Apéndice de método generado automáticamente
		return null;
	}


	/* (no Javadoc)
	 * @see cl.araucana.prestamo.dao.PrestamoDAO#getInteresesPagoAnticipadoPeriodo(long, int)
	 */
	public Collection getInteresesPagoAnticipadoPeriodo(long idPrestamo, int idCuota) throws Exception, BusinessException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}


	/* (no Javadoc)
	 * @see cl.araucana.prestamo.dao.PrestamoDAO#getCuotas(java.lang.String)
	 */
	public ArrayList getCuotas(String rut) throws Exception, BusinessException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

}
