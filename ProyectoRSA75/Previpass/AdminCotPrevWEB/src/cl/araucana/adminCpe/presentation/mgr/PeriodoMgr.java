package cl.araucana.adminCpe.presentation.mgr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.NodoDAO;
import cl.araucana.adminCpe.hibernate.dao.NominaDAO;
import cl.araucana.adminCpe.hibernate.dao.ParametroDAO;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.ComprobanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaREVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) PeriodoMgr.java 1.2 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**a
 * @author cchamblas
 * 
 * @version 1.2
 */
public class PeriodoMgr
{
	private NominaDAO nominaDao;
	private NodoDAO nodoDao;
	private ParametroDAO parametroDao;

	static Logger logger = Logger.getLogger(PeriodoMgr.class);

	public PeriodoMgr(Session session)
	{
		this.nominaDao = new NominaDAO(session);
		this.nodoDao = new NodoDAO(session);
		this.parametroDao = new ParametroDAO(session);
	}

	/**
	 * hashmap cuenta data
	 * @return
	 * @throws DaoException
	 */
	public HashMap cuentaData() throws DaoException 
	{
		HashMap result = new HashMap();
		this.nominaDao.limpiaCache();
		result.put("numCmps", this.nominaDao.count(ComprobanteVO.class));
		result.put("numTrabs", this.nominaDao.count(CotizanteVO.class));
		result.put("numNomRA", this.nominaDao.count(NominaRAVO.class));
		result.put("numNomDC", this.nominaDao.count(NominaDCVO.class));
		result.put("numNomGR", this.nominaDao.count(NominaGRVO.class));
		result.put("numNomRE", this.nominaDao.count(NominaREVO.class));
		return result;
	}
	
	/**
	 * hashmap cuenta data
	 * @return
	 * @throws DaoException
	 */
	public HashMap cuentaDataTipoEmInd() throws DaoException 
	{
		List lisEmpresa= new ArrayList();
		List lisIndependiente= new ArrayList();
		HashMap result = new HashMap();
		this.nominaDao.limpiaCache();
		
		// Empresa
		
		List listNomiReEmp= this.nominaDao.countTipo(NominaREVO.class , Constants.TIPO_EMPRESA);
		List listNomiDCEmp= this.nominaDao.countTipo(NominaDCVO.class , Constants.TIPO_EMPRESA);
		List listNomiGREmp= this.nominaDao.countTipo(NominaGRVO.class , Constants.TIPO_EMPRESA);
		List listNomiRAEmp= this.nominaDao.countTipo(NominaRAVO.class , Constants.TIPO_EMPRESA);
		

		result.put("numNomRAEmp", new Integer(listNomiRAEmp.size()));
		result.put("numNomDCEmp", new Integer(listNomiDCEmp.size()));
		result.put("numNomGREmp", new Integer(listNomiGREmp.size()));
		result.put("numNomREEmp", new Integer(listNomiReEmp.size()));
		
		lisEmpresa.addAll(listNomiReEmp);
		lisEmpresa.addAll(listNomiDCEmp);
		lisEmpresa.addAll(listNomiGREmp);
		lisEmpresa.addAll(listNomiRAEmp);
		
		HashSet hsEmpresa = new HashSet();
		hsEmpresa.addAll(lisEmpresa);		 
		lisEmpresa.clear();
		lisEmpresa.addAll(hsEmpresa);
				
		result.put("numCmpsEmp", new Integer(this.nominaDao.countComprobantes(lisEmpresa).size()));
		result.put("numTrabsEmp", new Integer(this.nominaDao.countCotizantes(CotizanteVO.class,Constants.TIPO_EMPRESA)));
		
		//Fin empresa
		
		//Independiente
		
		List listNomiReInd= this.nominaDao.countTipo(NominaREVO.class , Constants.TIPO_EMPRESA_INDEPENDIENTE);
		List listNomiDCInd= this.nominaDao.countTipo(NominaDCVO.class , Constants.TIPO_EMPRESA_INDEPENDIENTE);
		List listNomiGRInd= this.nominaDao.countTipo(NominaGRVO.class , Constants.TIPO_EMPRESA_INDEPENDIENTE);
		List listNomiRAvInd= this.nominaDao.countTipo(NominaRAVO.class , Constants.TIPO_EMPRESA_INDEPENDIENTE);
		
		result.put("numNomRAInd", new Integer(listNomiRAvInd.size()));
		result.put("numNomDCInd", new Integer(listNomiDCInd.size()));
		result.put("numNomGRInd", new Integer(listNomiGRInd.size()));
		result.put("numNomREInd", new Integer(listNomiReInd.size()));
		
		lisIndependiente.addAll(listNomiRAvInd);
		lisIndependiente.addAll(listNomiDCInd);
		lisIndependiente.addAll(listNomiGRInd);
		lisIndependiente.addAll(listNomiReInd);
		
		HashSet hsIndependiente = new HashSet();
		hsIndependiente.addAll(lisIndependiente);		 
		lisIndependiente.clear();
		lisIndependiente.addAll(hsIndependiente);
		
		result.put("numCmpsInd", new Integer(this.nominaDao.countComprobantes(lisIndependiente).size()));
		result.put("numTrabsInd", new Integer(this.nominaDao.countCotizantes(CotizanteVO.class,Constants.TIPO_EMPRESA_INDEPENDIENTE)));
		
		//Fin Independiente
		
		return result;
	}

	/**
	 * actualiza balanceo
	 * @param idPersona
	 * @return
	 * @throws DaoException
	 */
	public boolean actualizaBalanceo() throws DaoException 
	{
		return this.nodoDao.actualizaBalanceoCarga(this.parametroDao.getFactoresCarga());
	}
}
