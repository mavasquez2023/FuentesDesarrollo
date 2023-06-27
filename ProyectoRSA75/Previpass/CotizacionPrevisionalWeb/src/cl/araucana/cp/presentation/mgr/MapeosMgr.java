package cl.araucana.cp.presentation.mgr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.hibernate.beans.MapaCodigoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapaNominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.presentation.beans.LineaCodigoFicha;
import cl.araucana.cp.hibernate.dao.MapeosDAO;

/*
 * @(#) MapeosMgr.java 1.12 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.12
 */
public class MapeosMgr
{
	private MapeosDAO mapeosDao;
	static Logger log = Logger.getLogger(MapeosMgr.class);

	public MapeosMgr(Session session)
	{
		this.mapeosDao = new MapeosDAO(session);
	}

	/**
	 * mapeos
	 * 
	 * @param idMapaCod
	 * @param tipo
	 * @param tipoEntidad
	 * @return
	 * @throws DaoException
	 */
	public List getMapeos(int idMapaCod, Class tipo, Class tipoEntidad) throws DaoException
	{
		return this.mapeosDao.getMapeos(idMapaCod, tipo, tipoEntidad);
	}

	/**
	 * mapeos
	 * 
	 * @param idMapaCod
	 * @param tipo
	 * @param tipoEntidad
	 * @param idExcluir
	 * @return
	 * @throws DaoException
	 */
	public List getMapeos(int idMapaCod, Class tipo, Class tipoEntidad, int idExcluir) throws DaoException
	{
		return this.mapeosDao.getMapeos(idMapaCod, tipo, tipoEntidad, idExcluir);
	}

	/**
	 * guarda mapeos
	 * 
	 * @param idMapaCod
	 * @param listaGuardar
	 * @param tipo
	 * @throws DaoException
	 */
	public void guardaMapeo(int idMapaCod, List listaGuardar, Class tipo) throws DaoException
	{
		List lista = new ArrayList();
		log.debug("MapeosMgr:guardaMapeo idMapaCod:" + idMapaCod + ":tipo:" + tipo.getName() + "::");
		try
		{
			Class claseMapeo = Class.forName(tipo.getName());
			MapeoVO mapeo;
			Class[] argTypes = { Integer.class, Integer.class, String.class };
			Object[] argValues;
			LineaCodigoFicha linea;
			for (Iterator it = listaGuardar.iterator(); it.hasNext();)
			{
				linea = (LineaCodigoFicha) it.next();
				if (linea == null)
					continue;
				argValues = new Object[3];
				argValues[0] = new Integer(idMapaCod);
				argValues[1] = new Integer(linea.getIdEntidad());
				argValues[2] = linea.getIdCodigoNew();
				mapeo = (MapeoVO) claseMapeo.getConstructor(argTypes).newInstance(argValues);
				log.info(mapeo.toString());
				lista.add(mapeo);
			}

			this.mapeosDao.guardaMapeos(idMapaCod, lista, tipo);
		} catch (Exception ex)
		{
			log.error("Error en MapeosMgr.guardaMapeo: " + ex);
			throw new DaoException("Error en MapeosMgr:guardaMapeo:", ex);
		}
	}

	/**
	 * mapa nomina
	 * 
	 * @param idMapaNom
	 * @return
	 * @throws DaoException
	 */
	public MapaNominaVO getMapaNomina(int idMapaNom) throws DaoException
	{
		return this.mapeosDao.getMapaNomina(idMapaNom);
	}

	/**
	 * guarda mapa nomina
	 * 
	 * @param mapaNom
	 * @throws DaoException
	 */
	public void guardarMapaNomina(MapaNominaVO mapaNom) throws DaoException
	{
		this.mapeosDao.guardarMapaNomina(mapaNom);
	}

	/**
	 * mapa codigo
	 * 
	 * @param idMapaCod
	 * @return
	 * @throws DaoException
	 */
	public MapaCodigoVO getMapaCodigo(int idMapaCod) throws DaoException
	{
		return this.mapeosDao.getMapaCodigo(idMapaCod);
	}
	
	/**
	 * Obtiene UN Mapeo de Concepto
	 * 
	 * @param tipoNomina
	 * @param idMapaCod
	 * @param idConcepto
	 * @return
	 * @throws DaoException
	 */
	public MapeoConceptoVO getMapeoConcepto(String tipoNomina, int idMapaNom, int idConcepto) throws DaoException {
		return this.mapeosDao.getMapeoConcepto(tipoNomina, idMapaNom, idConcepto);
	}

	/**
	 * Devuelve la cantidad máxima de conceptos. Para el caso de Nominas con Caracter de Separador.
	 * 
	 * @param idMapa
	 * @param tipoProceso
	 * @return
	 * @throws DaoException
	 */
	public int getCantidadDeConceptos(int idMapa, char tipoProceso) throws DaoException {
		return this.mapeosDao.getCantidadDeConceptos(idMapa,tipoProceso);
	}
}