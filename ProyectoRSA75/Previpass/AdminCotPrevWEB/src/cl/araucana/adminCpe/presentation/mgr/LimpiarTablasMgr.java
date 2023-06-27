package cl.araucana.adminCpe.presentation.mgr;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.NominaDAO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) LimpiarTablasMgr.java 1.3 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cllanos
 * @author cchamblas
 * 
 * @version 1.3
 */
public class LimpiarTablasMgr
{
	private NominaDAO nominaDao;
	static final Integer int1 = new Integer(1);

	public LimpiarTablasMgr()
	{}

	public LimpiarTablasMgr(Session session)
	{
		this.nominaDao = new NominaDAO(session);
	}

	/**
	 * limpia tablas nominas
	 * @return
	 * @throws DaoException
	 */
	public String limpiaTablasNominas(Set nominas)throws DaoException
	{
		return this.nominaDao.borraNominas(nominas);
	}
	/**
	 * limpia tablas comprobantes
	 * @return
	 * @throws DaoException
	 */
	public String limpiaTablasComprobantes()throws DaoException
	{
		return this.nominaDao.borraComprobantes();
	}
	
	/**
	 * Limpia las tablas de la nomina segun un listado de rutEmpresa
	 * 
	 * @author gmallea
	 * 
	 * @param List rutEmpresas
	 * @return
	 * @throws DaoException
	 */
	public String limpiaTablasNominasEmpresa(List rutEmpresas)throws DaoException
	{
		return this.nominaDao.borraNominasEmpresa(rutEmpresas);
	}
	
	/**
	 * Actualiza el estado de las nominas de 9 a 3
	 * 
	 * @author gmallea
	 *
	 * @param List nominas
	 * @return
	 * @throws DaoException
	 */
	public void actualizaEstadoNominaReIndependiente(List nominas)throws DaoException
	{
		this.nominaDao.actualizaEstadoNominaReIndependiente(nominas);
	}
	
	/**
	 * Limpia las tablas segun un alista de codigos de barra en este orden (configPDF, detalleSeccion, Seccion y comprobantePago) segun en listado de codigos de barra ojo 
	 * que al eliminar el comprobante se borran tambien los datos de  configPDF , detalleSeccion, seccion.
	 * 
	 * @author gmallea
	 * 
	 * @param List listCodBarra
	 * @return
	 * @throws DaoException
	 */
	public String borraComprobantesEmpresa(List listCodBarra)throws DaoException
	{
		return this.nominaDao.borraComprobantesEmpresa(listCodBarra);
	}
	
}
