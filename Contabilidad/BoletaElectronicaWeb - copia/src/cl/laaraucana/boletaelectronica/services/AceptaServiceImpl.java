package cl.laaraucana.boletaelectronica.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.axis.types.NonNegativeInteger;
import org.apache.axis.types.PositiveInteger;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

		for (BoletaBase vo : boletasEmitidas) {

			Ca4XmlResponseRetval resultado = null;

			String ep = Configuraciones.getConfig("url.acepta");

			Ca4Xml_BindingStub stub = new Ca4Xml_BindingStub();
			stub._setProperty(Ca4Xml_BindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);

			BOLETADefType boleta = new BOLETADefType();

			// ENCABEZADO
			BOLETADefTypeEncabezado encabezado = new BOLETADefTypeEncabezado();

			// IdDOC
			BOLETADefTypeEncabezadoIdDoc idDoc = new BOLETADefTypeEncabezadoIdDoc();
			idDoc.setTipoDTE(DTEType.fromString("39"));
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

			// RECETOR
			BOLETADefTypeEncabezadoReceptor receptor = new BOLETADefTypeEncabezadoReceptor();
			receptor.setRUTRecep(vo.getRUTREC());
			receptor.setRznSocRecep(vo.getNOMREC());
			encabezado.setReceptor(receptor);

			// Totales
			BOLETADefTypeEncabezadoTotales totales = new BOLETADefTypeEncabezadoTotales();
			totales.setMntNeto(new NonNegativeInteger(String.valueOf(vo.getMONTONETO())));
			if (vo.getMONTOIVA() >= 1) {
				totales.setIVA(new PositiveInteger(String.valueOf(vo.getMONTOIVA())));
			}
			totales.setMntTotal(new NonNegativeInteger(String.valueOf(vo.getMONTOTAL())));
			encabezado.setTotales(totales);

			// Se setea Encabezado
			boleta.setEncabezado(encabezado);

			// DETALLE
			int i = 0;
			int size = vo.getBoletaDetalle().size();

			BOLETADefTypeDetalle[] detalles = new BOLETADefTypeDetalle[size];

			for (BoletaDetalle deta : vo.getBoletaDetalle()) {

				BOLETADefTypeDetalle detalle = new BOLETADefTypeDetalle();
				detalle.setNroLinDet(new PositiveInteger(String.valueOf("1")));
				detalle.setNmbItem(deta.getNOMITEM());
				detalle.setDscItem(deta.getNOMITEM());
				detalle.setQtyItem(new BigDecimal(deta.getCANTIDAD()));
				detalle.setUnmdItem(deta.getUNIMED());
				detalle.setPrcItem(new BigDecimal(deta.getPRECUNIT()));
				detalle.setMontoItem(new NonNegativeInteger(String.valueOf(deta.getCANTIDAD() * deta.getPRECUNIT())));
				detalles[i] = detalle;

				i++;
			}

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

			resultado = stub.ca4Xml(String.valueOf(vo.getIdbase()), comando, parametro, datos);

			ResultadoVo res = new ResultadoVo();

			if (resultado != null) {

				res.setEstado(resultado.getEstado());
				res.setFolio(resultado.getFolio());
				res.setGlosa(resultado.getGlosa());
				res.setuRLDTE(resultado.getUrlDte());

				logger.debug("Estado=" + resultado.getEstado());
				logger.debug("Folio=" + resultado.getFolio());
				logger.debug("Glosa=" + resultado.getGlosa());
				logger.debug("URL DTE=" + resultado.getUrlDte());

				OrigenBoletaVo origen = new OrigenBoletaVo();

				origen.setNUMBOL(res.getFolio());
				origen.setRUTREC(vo.getRUTREC());
				origen.setNOMREC(vo.getNOMREC());
				origen.setFOLIO(vo.getFOLDOC() + "");
				origen.setUrlDte(res.getuRLDTE());

				origenListEmitidas.add(origen);

			} else {
				logger.debug("Sin Resultado");
			}
		}
		return origenListEmitidas;
	}

}
