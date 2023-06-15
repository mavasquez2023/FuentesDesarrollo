package cl.laaraucana.boletaelectronica.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.axis.types.NonNegativeInteger;
import org.apache.axis.types.PositiveInteger;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.acepta.soap.ca4xml.BOLETADefType;
import com.acepta.soap.ca4xml.BOLETADefTypeDetalle;
import com.acepta.soap.ca4xml.BOLETADefTypeEncabezado;
import com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoEmisor;
import com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoIdDoc;
import com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoIdDocIndServicio;
import com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoReceptor;
import com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoTotales;
import com.acepta.soap.ca4xml.Ca4XmlDatos;
import com.acepta.soap.ca4xml.Ca4XmlResponseRetval;
import com.acepta.soap.ca4xml.Ca4Xml_BindingStub;
import com.acepta.soap.ca4xml.DTEDefType;
import com.acepta.soap.ca4xml.DTEType;
import cl.laaraucana.boletaelectronica.entities.BoletaBase;
import cl.laaraucana.boletaelectronica.entities.BoletaDetalle;
import cl.laaraucana.boletaelectronica.utils.Configuraciones;
import cl.laaraucana.boletaelectronica.vo.OrigenBoletaVo;
import cl.laaraucana.boletaelectronica.vo.ResultadoVo;

@Service
public class AceptaServiceImpl implements AceptaService {

	private static final Logger logger = Logger.getLogger(AceptaServiceImpl.class);

	public List<OrigenBoletaVo> wsAcepta(List<BoletaBase> boletasEmitidas) throws Exception {
		List<OrigenBoletaVo> origenListEmitidas = new ArrayList<OrigenBoletaVo>();
		String ep = Configuraciones.getConfig("url.acepta");
		logger.info("url acepta: " + ep);
		
		//Se instancia objeto Binding
		Ca4XmlResponseRetval resultado = null;
		Ca4Xml_BindingStub stub = new Ca4Xml_BindingStub();
		stub._setProperty(Ca4Xml_BindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		
		for (BoletaBase vo : boletasEmitidas) {
			try{
			BOLETADefType boleta = new BOLETADefType();
			// ENCABEZADO
			logger.debug("Generando objeto boleta folio: " + vo.getFOLDOC());
			BOLETADefTypeEncabezado encabezado = new BOLETADefTypeEncabezado();
			// IdDOC
			BOLETADefTypeEncabezadoIdDoc idDoc = new BOLETADefTypeEncabezadoIdDoc();
			idDoc.setTipoDTE(DTEType.fromString(String.valueOf(vo.getTIPDOC())));
			idDoc.setFolio(new PositiveInteger(String.valueOf(vo.getFOLDOC())));
			idDoc.setFchEmis(new Date());
			idDoc.setIndServicio(BOLETADefTypeEncabezadoIdDocIndServicio.fromString(String.valueOf(vo.getINDICSERV())));
			encabezado.setIdDoc(idDoc);
			// EMISOR
			BOLETADefTypeEncabezadoEmisor emisor = new BOLETADefTypeEncabezadoEmisor();
			emisor.setRUTEmisor(vo.getRUTEM());
			emisor.setRznSocEmisor(vo.getRAZONSOCEM());
			emisor.setGiroEmisor(vo.getGIRONEGEM());
			emisor.setDirOrigen(vo.getDIRORIGEN());
			emisor.setCiudadOrigen(vo.getCIUORIGEN());
			emisor.setCmnaOrigen(vo.getCOMORIGEN());
			encabezado.setEmisor(emisor);
			// RECEPTOR
			BOLETADefTypeEncabezadoReceptor receptor = new BOLETADefTypeEncabezadoReceptor();
			receptor.setRUTRecep(vo.getRUTREC());
			receptor.setRznSocRecep(vo.getNOMREC());
			encabezado.setReceptor(receptor);
			// Totales
			BOLETADefTypeEncabezadoTotales totales = new BOLETADefTypeEncabezadoTotales();
			totales.setMntNeto(new NonNegativeInteger(String.valueOf(vo.getMONTONETO())));
			totales.setMntTotal(new NonNegativeInteger(String.valueOf(vo.getMONTOTAL())));
			// DETALLE
			int i = 0;
			int size = vo.getBoletaDetalle().size();
			BOLETADefTypeDetalle[] detalles = new BOLETADefTypeDetalle[size];
			logger.info("Monto Total: " + vo.getMONTOTAL());
			logger.info("IVA Original: " + vo.getMONTOIVA());
			long totalIva = 0;
			for (BoletaDetalle deta : vo.getBoletaDetalle()) {
				BOLETADefTypeDetalle detalle = new BOLETADefTypeDetalle();
				detalle.setNroLinDet(new PositiveInteger(String.valueOf("1")));
				detalle.setNmbItem(deta.getNOMITEM());
				detalle.setDscItem(deta.getNOMITEM());
				detalle.setUnmdItem(deta.getUNIMED());
				detalle.setQtyItem(new BigDecimal(1));
				detalle.setPrcItem(new BigDecimal(deta.getPRECUNIT()));
				totalIva = totalIva + deta.getVALLINDET();
				//TODO: Activar descuento en WS ACEPTA
				
				detalle.setDescuentoMonto(new NonNegativeInteger(String.valueOf(deta.getMTODSC())));
				logger.info("Informando Dcto: " + detalle.getDescuentoMonto());
				detalle.setMontoItem(new NonNegativeInteger(String.valueOf(deta.getSUBTOTAL())));
				detalles[i] = detalle;
				i++;
			}
			if(totalIva > 0) {
				totales.setIVA(new PositiveInteger(String.valueOf(totalIva)));
			} 
			encabezado.setTotales(totales);
			boleta.setEncabezado(encabezado);
			
			// Se setea Detalle
			boleta.setDetalle(detalles);
			// FIRMA
			Calendar cal = Calendar.getInstance();
			boleta.setTmstFirma(cal);
			Ca4XmlDatos datos = new Ca4XmlDatos();
			DTEDefType type = new DTEDefType();
			type.setBoleta(boleta);
			datos.setDTE(type);
			String comando = Configuraciones.getConfig("acepta.comando");
			String parametro = Configuraciones.getConfig("acepta.parametro");
			
			//Invocanso WS con Objeto boleta
			logger.info("invocando ws folio " + vo.getFOLDOC() );
			resultado = stub.ca4Xml(String.valueOf(vo.getFOLDOC()), comando, parametro, datos);
			ResultadoVo res = new ResultadoVo();
			if (resultado != null) {
				logger.info("Resultado nro. boleta: "+ resultado.getFolio() + " ,estado=" + resultado.getEstado() + " , Glosa:" + resultado.getGlosa());
				String estado = resultado.getEstado();
				if(estado.equals("1")) {
					logger.error("Error en el servicio ca4Xml: " + resultado.getGlosa());
					return null;
				}
				if(resultado.getFolio().equals("")) {
					res.setFolio("0");
				} else {
					res.setFolio(resultado.getFolio());
				}
				res.setEstado(resultado.getEstado());
				res.setGlosa(resultado.getGlosa());
				res.setuRLDTE(resultado.getUrlDte());
				logger.info("URL DTE=" + resultado.getUrlDte());
				OrigenBoletaVo origen = new OrigenBoletaVo();
				origen.setNUMBOL(res.getFolio());
				origen.setRUTREC(vo.getRUTREC());
				origen.setNOMREC(vo.getNOMREC());
				origen.setFOLIO(vo.getFOLDOC() + "");
				origen.setUrlDte(res.getuRLDTE());
				origen.setGlosaError(res.getGlosa());
				origenListEmitidas.add(origen);
			} else {
				logger.debug("Sin Resultado");
			}
			}catch (Exception e) {
				logger.error("Error al enviar boleta para folio: " + vo.getFOLDOC());
				logger.error("Mensaje: " + e.getMessage());
			}
		}
		return origenListEmitidas;
	}
}
