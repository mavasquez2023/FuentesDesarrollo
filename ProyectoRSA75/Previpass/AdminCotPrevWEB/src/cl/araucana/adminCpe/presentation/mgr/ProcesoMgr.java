package cl.araucana.adminCpe.presentation.mgr;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.EmpresaDAO;
import cl.araucana.adminCpe.hibernate.dao.NominaDAO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

/*
 * @(#) ProcesoaMgr.java 1.1 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @version 1.1
 */
public class ProcesoMgr
{
	private NominaDAO nominaDao;
	private EmpresaDAO empresaDao;

	static Logger logger = Logger.getLogger(ProcesoMgr.class);

	public ProcesoMgr(Session session)
	{
		this.nominaDao = new NominaDAO(session);
		this.empresaDao = new EmpresaDAO(session);
	}

	/**
	 * collection tipos proceso
	 * 
	 * @return
	 * @throws DaoException
	 */
	public Collection getTiposProceso() throws DaoException
	{
		return this.nominaDao.getTiposNominas();
	}

	/**
	 * empresa
	 * 
	 * @param idEmpresa
	 * @return
	 * @throws DaoException
	 */
	public EmpresaVO getEmpresa(int idEmpresa) throws DaoException
	{
		return this.empresaDao.getEmpresa(idEmpresa);
	}

	public String getNombreTipoNomina(String tipoNomina) throws DaoException
	{
		return this.nominaDao.getNombreTipoNomina(tipoNomina);
	}
}
