package cl.araucana.spl.mgr;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.spl.beans.CTACTE;
import cl.araucana.spl.beans.Convenio;
import cl.araucana.spl.beans.MedioPago;
import cl.araucana.spl.dao.MedioPagoDAO;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.exceptions.PagoEnLineaException;

import com.ibatis.dao.client.DaoManager;

public class MedioPagoManager {
	private static final Logger logger = Logger.getLogger(MedioPagoManager.class);
	private MedioPagoDAO dao;
	
	public MedioPagoManager(){
		DaoManager mgr = DaoConfig.getDaoManager();
		dao = (MedioPagoDAO)mgr.getDao(MedioPagoDAO.class);
	}

	/**
	 * Se consulta por codigo de medio de pago. 
	 * @author vmorales BuilderHouse 
	 * @since 1.0 26/03/2008 - Inicio Sistema. 
	 * @param codigo Codigo del Medio de Pago.
	 * @return Un Medio Pago registrado en la tabla MEDIOPAGO
	 */
	public MedioPago getMedioPagoByCodigo(String codigo) {
		logger.debug("Recuperando medio " + codigo);
		return dao.getMedioPagoByCodigo(codigo);
	}
	
	/**
	 * Se consulta por id de medio de pago.
	 * @author vmorales BuilderHouse 
	 * @since 1.0 26/03/2008 - Inicio Sistema. 
	 * @param idMedioPago Id del Medio de Pago.
	 * @return Un Medio Pago registrado en la tabla MEDIOPAGO
	 */
	public MedioPago getMedioPagoById(BigDecimal idMedioPago){
		logger.debug("Recuperando medio " + idMedioPago);
		return dao.getMedioPagoById(idMedioPago);	
	}
	
	/**
	 * Dado un listado de codigos de medio de pago, lista los medios activos.
	 * Si la lista recibida es vacia, se devuelven todos los medios activos existentes en el sistema.
	 * De otra forma, se entregan todos los medios recibidos que esten activos en el sistema.
	 * 
	 * @param codMedios Lista de codigos de medios de pago.
	 * @throws PagoEnLineaException Si el resultado no arroja ningun medio  
	 */
	public List getMediosActivos(List codMedios) throws PagoEnLineaException {
		if (logger.isDebugEnabled()) {
			logger.debug("Recuperando medios activos entre " + codMedios);
		}
		List mediosActivos = getMediosPagoActivos();
		if (logger.isDebugEnabled()) {
			logger.debug("Medios activos: " + mediosActivos);
		}
		
		// No vienen medios ==> se retornan *todos* los activos. 
		if (codMedios.size() == 0) {
			return mediosActivos;
		}
		
		// Se cruzan los medios activos con los recibidos como parametro.
		List resultado = new ArrayList();
		for (Iterator it = mediosActivos.iterator(); it.hasNext(); ) {
			MedioPago medio = (MedioPago) it.next();
			if (logger.isDebugEnabled()) {
				logger.debug("Medio  " + medio.getCodigo() + ", contenido en entrada? " + codMedios.contains(medio.getCodigo()));
			}
			if (codMedios.contains(medio.getCodigo())) {
				resultado.add(medio);
			}
		}
		
		// Si en el cruce no quedaron medios disponibles ==> error.
		if (resultado.size() == 0) {
			throw new PagoEnLineaException("Los medios solicitados no estan activos en el sistema");
		}
		
		
		return resultado;
	}
	
	/**
	 * Retorna los medios de pago activos
	 * @return Lista de medios
	 */
	public List getMediosPagoActivos(){
		List medios = dao.getMediosPago();
		List retorno = new ArrayList();
		
		for (Iterator it = medios.iterator(); it.hasNext(); ) {
			MedioPago mp = (MedioPago) it.next();
			if (mp.esMedioActivo()) {
				retorno.add(mp);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Cantidad de medios activos recuperados: " + retorno.size());
		}
		return retorno;
	}

	public List getMediosPago() {
		List medios = dao.getMediosPago();
		if (logger.isDebugEnabled()) {
			logger.debug("Cantidad de medios recuperados: " + medios.size());
		}
		return medios;
	}
	
	public List getMediosPagoBatch() {
		List medios = dao.getMediosPagoBatch();
		if (logger.isDebugEnabled()) {
			logger.debug("Cantidad de medios recuperados: " + medios.size());
		}
		return medios;
	}
	
	public CTACTE getMontoPagadoPorMedioPago(String idMedioPago, String fechaContable) {
		CTACTE cta = new CTACTE();
		HashMap map = new HashMap();
		map.put("idMedioPago", idMedioPago);
		map.put("fechaContable", fechaContable);
	
	    cta = dao.getMontoPagadoPorMedioPago(map);
		if (logger.isDebugEnabled()) {
			logger.debug("Datos recuperados");
		}
		return cta;
	}
	
	public int updateMarcaLibroBanco(String idMedioPago, String fechaContable, String idConvenio) {
		HashMap map = new HashMap();
		map.put("idMedioPago", idMedioPago);
		map.put("fechaContable", fechaContable);
		map.put("idConvenio", idConvenio);
		int i = 0;
		i = dao.actualizaMarcaLibroBanco(map);
		if (logger.isDebugEnabled()) {
			logger.debug("Datos recuperados");
		}
		return i;
	}
	
	

	public Convenio getConvenio(String codigoBanco) throws PagoEnLineaException {
		Convenio convenio = dao.getConvenioByCodigoBanco(codigoBanco);
		if (convenio == null) {
			throw new PagoEnLineaException("No se encontro convenio para banco " + codigoBanco);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("Convenio recuperado: " + convenio);
		}
		return convenio;
	}
}
