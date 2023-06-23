package cl.araucana.spl.mgr;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.Folio;
import cl.araucana.spl.beans.WrapperXmlMedioPago;
import cl.araucana.spl.exceptions.IntegracionException;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.util.LRUCache;
import cl.araucana.spl.util.crypto.SimpleEncoder;
import cl.araucana.spl.util.crypto.TripleDesEngine;
import cl.araucana.spl.util.crypto.TripleDesEngineException;

public class IntegracionManager {

	private static final Logger logger = Logger.getLogger(IntegracionManager.class);
	public WrapperXmlMedioPago wrapperXmlMedioPago(String claveSistema, String sCrypted, String sIvector) throws PagoEnLineaException  {
		boolean updateCorrelativo = false;
		return wrapperXmlMedioPago(claveSistema, sCrypted, sIvector, updateCorrelativo);
	}
	public WrapperXmlMedioPago wrapperXmlMedioPago(String claveSistema, String sCrypted, String sIvector, boolean updateCorrelativo) throws PagoEnLineaException  {
		WrapperXmlMedioPago wxml = getDatosXml(claveSistema, sCrypted, sIvector);
		validaXml(wxml, updateCorrelativo);
		return wxml;
	}

	private WrapperXmlMedioPago getDatosXml(String claveSistema, String sCrypted, String sIvector) throws IntegracionException {
		Document document;
		try {
			String xml = desEncriptaXml(claveSistema, sCrypted, sIvector);
			document = DocumentHelper.parseText(xml);
		} catch (DocumentException de) {
			throw new IntegracionException("Problemas leyendo xml de entrada: " + de, de);
		} catch (TripleDesEngineException te) {
			throw new IntegracionException("Problemas leyendo xml de entrada: " + te, te);
		}

		Element root = document.getRootElement();
		WrapperXmlMedioPago wxml = new WrapperXmlMedioPago();
		
		wxml.setGlosa(getValueElement(root.element("glosa")));
		wxml.setPagador(getValueElement(root.element("pagador")));
		wxml.setCorrelativo(getValueElement(root.element("correlativo")));
		wxml.setFecha(getValueElement(root.element("fecha")));
		wxml.setUrlRetorno(getValueElement(root.element("urlRetorno")));
		wxml.setUrlNotificacion(getValueElement(root.element("urlNotificacion")));
		wxml.setMediosPagoBeans(getListMediosPago(root.element("bancos")));
		wxml.setRutCliente(getValueElement(root.element("rutCliente")));
		
		wxml.setFoliosBeans(getListFolios(root.element("folios")));
		return wxml;
	}

	
	/**
	 * Se desencripta el archivo xml, enviado por el sistema origen. 
	 * @author vmorales BuilderHouse 
	 * @version 1.0 26/03/2008 - Inicio Sistema. 
	 * @since 1.0 
	 * @param claveSistema Clave del sistema origen, obligatorio para el proceso de desencriptacion.
	 * @param sCrypted Vector con los datos a desencriptar.
	 * @param sIvector Vector de inicio de la desencriptacion.
	 * @return Un String con formato XML, enviado por el sistema de origen.
	 * @throws TripleDesEngineException 
	 */
	private String desEncriptaXml(String claveSistema, String sCrypted, String sIvector) throws TripleDesEngineException{
		logger.debug("VML: claveSistema = " + claveSistema);
		logger.debug("VML: sIvector   = " + sIvector);
		logger.debug("VML: sCrypted   = " + sCrypted);
		TripleDesEngine cipher = new TripleDesEngine();
		SimpleEncoder encoder = new SimpleEncoder();
		String retorno = cipher.decrypt(claveSistema, encoder.hexToBytes(sIvector), encoder.hexToBytes(sCrypted), Constants.CHARSET_XML_ENTRADA);			
		return retorno;
	}

	private void validaXml(WrapperXmlMedioPago wxml, boolean updateCorrelativo) throws IntegracionException {
		if (wxml.getGlosa() == null || "".equals(wxml.getGlosa())) {
			throw new IntegracionException("VML: No se encontro elemento en xml: glosa");	
		}

		if (wxml.getCorrelativo() == null || "".equals(wxml.getCorrelativo())) {
			throw new IntegracionException("VML: No se encontro elemento en xml: correlativo");	
		}

		if (wxml.getFecha()==null || "".equals(wxml.getFecha())) {
			throw new IntegracionException("VML: No se encontro elemento en xml: fecha");
		}
		
		if (wxml.getFoliosBeans()==null || wxml.getFoliosBeans().size()==0) {
			throw new IntegracionException("VML: No se encontraron folios en xml");
		}

		if (wxml.getUrlRetorno()==null || "".equals(wxml.getUrlRetorno())) {
			throw new IntegracionException("VML: No se encontro elemento en xml: urlRetorno");
		}

		if (wxml.getUrlNotificacion() ==null || "".equals(wxml.getUrlNotificacion())) {
			throw new IntegracionException("VML: No se encontro elemento en xml: urlNotificacion");
		}
		
		validaCorrelativo(wxml.getCorrelativo(), updateCorrelativo);
		validaFecha(wxml.getFecha());
	}

	/**
	 * Valida que el correlativo recibido no se encuentre en un listado cah'e.
	 * 
	 * @throws IntegracionException Si el correlativo es invalido.
	 */
	
	private static final LRUCache cache = new LRUCache(Constants.LRU_CACHE_SIZE, Constants.LRU_CACHE_TIMEOUT_MILLIS);
	private void validaCorrelativo(String correlativo, boolean updateCorrelativo) throws IntegracionException {
		boolean existe = false;
		
		synchronized (cache) {
			if (cache.contains(correlativo)) {
				existe = true;
			} else if (updateCorrelativo) {
				cache.put(correlativo);
			}
		}
		
		if (existe) {
			throw new IntegracionException("No se permite reuso de peticiones");
		}
	}

	private void validaFecha(String fecha) throws IntegracionException {
		long actual = System.currentTimeMillis();
		long entrada;
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.FORMATO_FECHA_XML_ENTRADA);
		try {
			Date d = sdf.parse(fecha);
			entrada = d.getTime();
		} catch (ParseException e) {
			throw new IntegracionException("Fecha no valida: " + fecha);
		}
		if ((actual - entrada) > Constants.MILISEGUNDOS_EXPIRACION_DESDE_ORIGEN) { 
			throw new IntegracionException("Solicitud expirada: " + fecha);
		}
	}


	private String getValueElement(Element elementXml){
		String retorno = null;
		if (elementXml!=null) {
			retorno = elementXml.getText();
		}
		logger.debug("VML valor retorno = "+retorno);
		return retorno;	
	}

	private List getListFolios(Element raizfolios){
		List retorno = new ArrayList();
		if (raizfolios!=null){
			List folios = raizfolios.elements("folio");
			for (int ifolio = 0; ifolio<folios.size(); ifolio++){
				Element folio = (Element)folios.get(ifolio);
					Folio newFolio = new Folio();
						String numero = getValueElement(folio.element("numero"));
						newFolio.setNumero(new BigDecimal(numero));
						newFolio.setMonto(new BigDecimal(getValueElement(folio.element("monto"))));
						newFolio.setDetalle(getValueElement(folio.element("detalle")));
					retorno.add(newFolio);							
			}
		}
		
		return retorno;
	}
	
	private List getListMediosPago(Element  raizbancos){		
		List mediosPago = new ArrayList();
		
		if (raizbancos!=null){
			List codigos = raizbancos.elements("codigo");
			for (Iterator it = codigos.iterator(); it.hasNext(); ) {
				String codigoMedioPago = getValueElement((Element)it.next());		
				mediosPago.add(codigoMedioPago);
			}
		}
		
		return mediosPago;
	}
}
