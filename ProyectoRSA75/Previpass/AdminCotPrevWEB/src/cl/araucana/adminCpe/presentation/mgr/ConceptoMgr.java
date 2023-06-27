package cl.araucana.adminCpe.presentation.mgr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.ConceptoDAO;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapaNominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.presentation.beans.LineaMapeoConcepto;
/*
* @(#) ConceptoMgr.java 1.5 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.5
 */
public class ConceptoMgr
{
	private ConceptoDAO conceptoDao;
	private MapeosMgr mapeosMgr;
	private ConvenioMgr convenioMgr;

	static Logger logger = Logger.getLogger(ConceptoMgr.class);

	public ConceptoMgr(Session session)
	{
		this.conceptoDao = new ConceptoDAO(session);
		this.mapeosMgr = new MapeosMgr(session);
		this.convenioMgr = new ConvenioMgr(session);
	}

	/**
	 * mapa conceptos
	 * @param tipoNomina
	 * @return
	 * @throws DaoException
	 */
	public HashMap getMapaConceptos(String tipoNomina) throws DaoException
	{
		return this.conceptoDao.getMapaConceptos(tipoNomina);
	}

	/**
	 * lista mapeos concepto
	 * @param idMapaNom
	 * @param tipoNomina
	 * @return
	 * @throws DaoException
	 */
	public List getListaMapeosConcepto(int idMapaNom, String tipoNomina) throws DaoException
	{
		return this.conceptoDao.getListaMapeosConcepto(idMapaNom, tipoNomina);
	}

/**
 * mapeo concepto
 * @param tiposProceso
 * @param listaConvenios
 * @return
 * @throws DaoException
 */
	public HashMap getHashMapeosConcepto(String [] tiposProceso, Collection listaConvenios) throws DaoException
	{
		HashMap result = new HashMap();
		for (Iterator it = listaConvenios.iterator(); it.hasNext();)
		{
			ConvenioVO convenio = (ConvenioVO)it.next();
			GrupoConvenioVO grupoConvenio = this.convenioMgr.getGrupoConvenio(convenio.getIdGrupoConvenio());
			for (int i = 0; i < tiposProceso.length; i++)
			{
				result.put(convenio.getIdEmpresa() + "#" + convenio.getIdConvenio() + "#" + tiposProceso[i], getListaMapeosConcepto(grupoConvenio.getIdMapaNom(tiposProceso[i].charAt(0)), tiposProceso[i]));
			}
		}
		return result;
	}

	/**
	 * guarda lista mapeos concepto
	 * @param lista
	 * @param idMapaNom
	 * @param tipoNomina
	 * @param editar
	 * @throws DaoException
	 */
	public void guardarListaMapeosConcepto(List lista, int idMapaNom, String tipoNomina, boolean editar) throws DaoException
	{
		LineaMapeoConcepto linea;
		MapeoConceptoVO mapeo;
		List listaMapeo = new ArrayList();
		int largoMax = -1;
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			linea = (LineaMapeoConcepto) it.next();
			mapeo = new MapeoConceptoVO();
			mapeo.setIdMapa(idMapaNom);
			mapeo.setTipoProceso(tipoNomina.charAt(0));
			mapeo.setIdConcepto(linea.getIdConcepto());
			mapeo.setPosicion(linea.getPosicion());

			//TODO csanchez
			if (linea.getTipoSeparador() == Constants.TIPO_SEPARADOR_CARACTER) {
				mapeo.setLargo(0);
				mapeo.setCaracterSeparador(linea.getCaracterSeparador());
				mapeo.setTipoSeparador(Constants.TIPO_SEPARADOR_CARACTER);
			} else {
				mapeo.setLargo(linea.getLargo());
				mapeo.setTipoSeparador(Constants.TIPO_SEPARADOR_POSICION);
			}
			
			if ((mapeo.getPosicion() + mapeo.getLargo()) > largoMax)
				largoMax = mapeo.getPosicion() + mapeo.getLargo();

			listaMapeo.add(mapeo);
		}
		this.conceptoDao.guardaMapeosConcep(listaMapeo);

		MapaNominaVO mapaNomina = this.mapeosMgr.getMapaNomina(idMapaNom);
		mapaNomina.setLargoRegistro(largoMax);
		this.mapeosMgr.guardarMapaNominaAlternativa(mapaNomina,editar);
	}

	/**
	 * lista conceptos
	 * @param tipoProceso
	 * @return
	 * @throws DaoException
	 */
	public List getListaConceptos(String tipoProceso) throws DaoException
	{
		return this.conceptoDao.getListaConceptos(tipoProceso);
	}

	/**
	 * hashmap conceptos
	 * @param tiposProceso
	 * @return
	 * @throws DaoException
	 */
	public HashMap getHashConceptos(String[] tiposProceso) throws DaoException
	{
		HashMap result = new HashMap();
		for (int i = 0; i < tiposProceso.length; i++)
			result.put(tiposProceso[i], this.getListaConceptos(tiposProceso[i]));
		return result;
	}
	/**+
	 * lista valida movimiento personas
	 * @return
	 * @throws DaoException
	 */
	 public List getLstValidaMovPers() throws DaoException 
	    {
	    	return this.conceptoDao.getLstValidaMovPers();
	    }
	 /**
	  * lista valida APV
	  * @return
	  * @throws DaoException
	  */
	 public List getLstValidaAPVs() throws DaoException 
	    {
	    	return this.conceptoDao.getLstValidaAPVs();
	    }
}
