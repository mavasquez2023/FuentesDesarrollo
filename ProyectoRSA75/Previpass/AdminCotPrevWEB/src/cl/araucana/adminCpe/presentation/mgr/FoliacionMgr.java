package cl.araucana.adminCpe.presentation.mgr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.FoliacionDAO;
import cl.araucana.adminCpe.presentation.struts.javaBeans.LineaEntidadFicha;
import cl.araucana.cp.distribuidor.hibernate.beans.FoliacionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) FoliacionMgr.java 1.5 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author jdelgado
 * @author cchamblas
 * 
 * @version 1.5
 */
public class FoliacionMgr
{
	static Logger log = Logger.getLogger(FoliacionMgr.class);
	private FoliacionDAO foliacionDAO;

	public FoliacionMgr(Session session)
	{
		this.foliacionDAO = new FoliacionDAO(session);
	}

	/**
	 * agrega folios
	 * @param listaGuardar
	 * @throws DaoException
	 */
	public List agregaFolios(List listaGuardar)
	{
	    List lista = new ArrayList();
		try 
		{
			log.info("FoliacionMgr:agregaFolios n folios:" + (listaGuardar.size()-1) + "::");
			for (Iterator it = listaGuardar.iterator(); it.hasNext();) 
			{
				LineaEntidadFicha linea = (LineaEntidadFicha) it.next();
				lista.add(new FoliacionVO(linea.getIdFoliacion(), linea.getIdEntPagadora(), linea.getFoliosEnUso(), linea.getFolioInicial(), linea.getFolioFinal(), linea.getFolioActual()));				
			}
			return this.foliacionDAO.guardaFolios(lista);			
		} catch (Exception ex) 
		{
			log.error("Error en FoliacionMgr.guardaFolios: ", ex);
			return null;
		}
	}

	/**
	 * elimina folios entidad
	 * @param idEntidad
	 * @throws Exception
	 */
	public void borraFoliosEntidad(int idEntidad) throws Exception 
	{
		this.foliacionDAO.borraFoliosEntidad(idEntidad);
	}

	/**
	 * lista folios entidad pagadora
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public List getFoliosEntidadPagadora(int id) throws DaoException
	{
		return this.foliacionDAO.getFoliosEntidadPagadora(id);
	}
	
	/**
	 * Reinicia el folio actual
	 *
	 */
	public void reiniciaFolioActual() {
		this.foliacionDAO.reiniciaFolioActual();
	}
}
