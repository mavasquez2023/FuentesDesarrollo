/**
 * 
 */
package cl.araucana.cp.distribuidor.business.mgr;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.ComprobanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.dao.ValidacionDAO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.hibernate.exceptions.SeccionException;
import cl.araucana.cp.distribuidor.hibernate.utils.HibernateUtil;

/**
 * @author usist199
 *
 */
public class ComprobanteThread extends Thread {
	protected static Logger logger = Logger.getLogger(ComprobanteThread.class);
	private Session session;
	private NominaVO nomina = null;
	private char tipoProceso;
	private int idDocumento;
	private long folio;
	private HashMap parametrosNegocio;
	private ConvenioVO convenio;
	
	/**
	 * 
	 */
	public ComprobanteThread(char tipoProceso, int idDocumento, NominaVO nomina, ConvenioVO convenio, long folio, HashMap parametrosNegocio) {
		this.session= getSession();
		this.nomina= nomina;
		this.convenio= convenio;
		this.tipoProceso= tipoProceso;
		this.idDocumento= idDocumento;
		this.folio= folio;
		this.parametrosNegocio= parametrosNegocio;
	}

	public void run() { 
		Transaction tx = null;
    	try { 
    		if(session!= null && session.isConnected()){
    			tx = session.beginTransaction();
    			logger.info("Cambiando estado Nómina mientras se genera Comprobante");
    			this.nomina.setIdEstado(Constants.EST_NOM_EN_PROCESO);
    			session.update(nomina);
    			session.flush();
    			
    			ValidacionDAO validacionDao = new ValidacionDAO(session);
    			logger.info("Leyendo Cotizantes para generar Comprobante");
    			HashMap cotizantes = validacionDao.getCotizantes(tipoProceso, this.nomina);
    			logger.info("Numero cotizantes en nómina: " + cotizantes.size());
    			ComprobanteVO comprobante= generaComprobante(tipoProceso, idDocumento, this.nomina.getNumCotizaciones(), folio, cotizantes, parametrosNegocio);
    			
    			logger.info(">>>>Guarda Comprobante: " + comprobante.getIdCodigoBarra());
    			validacionDao.guardaComprobante(comprobante, comprobante.getSecciones());
    			this.nomina.setIdCodigoBarras(comprobante.getIdCodigoBarra());

    			this.convenio.sumNumNominasOK();
    			this.convenio.sumNumNominasCorr();
    			
    			logger.info("Nómina OK, cambiando estado Nómina, Por Pagar");
    			nomina.setIdEstado(Constants.EST_NOM_POR_PAGAR);
    			session.update(nomina);
    			session.flush();
    			tx.commit();
    		}else{
    			logger.warn("No session, no se pudo generar comprobante pago, rut:" + this.nomina.getRutEmpresa() + ", convenio:" + this.nomina.getIdConvenio());
    		}
    		logger.info(">>>>>>>>>>>>>>>>>>>>>>> TERMINA ComprobanteThread, rut:" + this.nomina.getRutEmpresa() + ", convenio:" + this.nomina.getIdConvenio());
    	}catch(Exception e){
    		logger.error("Error al generar Comprobante Pago, Mensaje: " + e.getMessage());
    		logger.info("Nómina ERROR, cambiando estado Nómina a Error");
    		e.printStackTrace();
    		nomina.setIdEstado(Constants.EST_NOM_CON_ERRORES);
			session.update(nomina);
			session.flush();
    		
    	}
	}
	
	private ComprobanteVO generaComprobante(char tipoProceso, int idDocumento, int numTrabs, long folio, HashMap cotizantes, HashMap parametros) throws DaoException, SeccionException
	{
		ComprobanteVO comprobante=null;
		try
		{
			logger.info("Instanciando  nuevo Comprobante, folio: " + folio + "::");
			comprobante = new ComprobanteVO(0
												,("" + Constants.EST_CMP_POR_PAGAR).charAt(0)
												,idDocumento
												,new Timestamp((new java.util.Date()).getTime())
												,(byte) 0
												,(byte) 0
												,new Timestamp((new java.util.Date(1)).getTime())
												,this.nomina.getNumCotizaciones()
												,0);

			SeccionMgr seccionMgr = new SeccionMgr(this.session, tipoProceso, parametros);
			seccionMgr.cargaProperties();
			logger.info("Generando Secciones.");
			List secciones = seccionMgr.generaSecciones(this.convenio, cotizantes);
			comprobante.setSecciones(secciones);
			comprobante.setTotal(seccionMgr.getTotal());
			comprobante.setFolioTesoreria(folio);
			comprobante.setNumTrabajadores(numTrabs);
			comprobante.setRenta_imponible(seccionMgr.getRentaImponible());

			logger.info("Fin generaComprobante, folio: " + folio + "::");
		} catch (SeccionException se)
		{
			logger.error("ERROR genera comprobante:", se);
			throw se;
		}
		return comprobante;
	}
	
	private Session getSession()  
	{
		try
		{
			if (this.session == null)
				this.session = HibernateUtil.getSession();
			if (this.session.isConnected())
				return this.session;
			this.session = HibernateUtil.getSession();
			return this.session;
		} catch (Exception e)
		{
			logger.error("problemas obtencion session hibernate EJB", e);
			return null;
		} finally
		{
			logger.info("Conexion conectada?" + this.session.isConnected());
		}
	}
}
