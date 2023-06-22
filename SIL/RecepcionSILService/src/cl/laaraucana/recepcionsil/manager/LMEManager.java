package cl.laaraucana.recepcionsil.manager;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlValidationError;
import org.w3c.dom.Node;

import wwwLmeGovClLme.CTEstado;
import wwwLmeGovClLme.CTLugarReposo;
import wwwLmeGovClLme.CTTelefono;
import wwwLmeGovClLme.CTZONA0;
import wwwLmeGovClLme.CTZONAA;
import wwwLmeGovClLme.CTZONAB;
import wwwLmeGovClLme.CTZONAC;
import wwwLmeGovClLme.CTZONAD;
import wwwLmeGovClLme.LMEDocument;
import cl.laaraucana.recepcionsil.service.vo.CamposXmlVO;
import conector.lme.ws.cliente.operador.RespuestaDetalleLicencia;

public class LMEManager {

	public static CamposXmlVO obtenerCamposXML(String base64) throws Exception {
		CamposXmlVO campos = new CamposXmlVO();
		if (base64!=null) {
			campos = mapeoCamposXML(decodeBase64XmlLME(base64));
		}
		return campos;
	}

	public static RespuestaDetalleLicencia decodeBase64XmlLME(String base64) throws Exception {

		LMEDocument lmeDocument = null;
		String xmlAsString = null;

		byte[] bytes = Base64.decodeBase64(base64.getBytes());

		XmlOptions options = new XmlOptions();
		//		Map<String, String> substNamespaces = new HashMap<String, String>();
		//		substNamespaces.put("http://www.lme.gov.cl/lme", "urn:www:lme:gov:cl:lme");
		//		substNamespaces.put("", "urn:www:lme:gov:cl:lme");
		//		options.setLoadSubstituteNamespaces(substNamespaces);

		try {
			xmlAsString = decodeXmlasString(bytes, "UTF-8");
			//System.out.println(xmlAsString);
			lmeDocument = LMEDocument.Factory.parse(xmlAsString, options);
		} catch (Exception e) {
			// log("Error al decodificar xml de licencia: " + as + " en UTF-8");
			try {
				// log("Se intenta decodificar xml en ISO-8859-1");
				xmlAsString = decodeXmlasString(bytes, "ISO-8859-1");
				//System.out.println(xmlAsString);
				lmeDocument = LMEDocument.Factory.parse(xmlAsString, options);
				// log("Decodificacion exitosa de xml en ISO-8859-1");
			} catch (Exception e2) {
				// log("Error al decodificar xml de licencia: " + aas +
				// " en ISO-8859-1");
				throw new Exception("Error en parse Xml B64");
			}
		}

		CTZONA0 zona0 = lmeDocument.getLME().getZONA0();
		CTZONAA zonaA = lmeDocument.getLME().getZONAA();
		CTZONAB zonaB = lmeDocument.getLME().getZONAB();
		CTZONAC zonaC = lmeDocument.getLME().getZONAC();
		CTZONAD[] zonaD = lmeDocument.getLME().getZONADArray();

		RespuestaDetalleLicencia respuestaDetalleLicencia = new RespuestaDetalleLicencia(null, null, zona0, zonaA, zonaB, zonaC, zonaD, xmlAsString);

		// log("Licencia:" + numLicencia);
		//validar("Zona A", respuestaDetalleLicencia.getZonaA());
		//validar("Zona B", respuestaDetalleLicencia.getZonaB());
		//validar("Zona C", respuestaDetalleLicencia.getZonaC());
		//validar("Zona D", respuestaDetalleLicencia.getZonaD());

		return respuestaDetalleLicencia;
	}

	private static String decodeXmlasString(byte[] bs, String charset) throws java.io.UnsupportedEncodingException {
		String xmlAsString = new String(bs, charset);
		return xmlAsString;
	}

	static void validar(String prefijo, XmlObject root) {
		if (root == null) {
			return;
		}

		ArrayList<XmlValidationError> validationErrors = new ArrayList<XmlValidationError>();
		//XmlOptions m_validationOptions = new XmlOptions();
		//m_validationOptions.setErrorListener(validationErrors);

		// log(prefijo + " Validacion: " + root.validate(m_validationOptions));

		for (XmlValidationError error : validationErrors) {
			Node node = error.getCursorLocation().getDomNode();
			Node parent = node.getParentNode();
			String path = node.getNodeName();
			while (parent != null && parent != node) {
				path = parent.getNodeName() + "/" + path;
				node = parent;
				parent = parent.getParentNode();
			}
			// log(prefijo + "\t" + path + "\n\t" + error);
		}
	}

	static void validar(String prefijo, XmlObject[] root) {
		if (root == null) {
			return;
		}

		for (int i = 0; i < root.length; i++) {
			validar(prefijo, root[i]);
		}
	}

	private static CamposXmlVO mapeoCamposXML(RespuestaDetalleLicencia respuesta) {
		CamposXmlVO campos = new CamposXmlVO();
		CTEstado[] listaEstado = respuesta.getZona0().getZONA01().getEstadoArray();

		campos.setEstadoLicencia(getUltimoEstado(listaEstado));
		campos.setApellidoPaternoAfilia(set(decodeUTF8(respuesta.getZonaA().getZONAA1().getTrabajador().getApellidoPaterno())));
		campos.setApellidoMaternoAfilia(set(decodeUTF8(respuesta.getZonaA().getZONAA1().getTrabajador().getApellidoMaterno())));
		campos.setNombreAfiliado(set(decodeUTF8(respuesta.getZonaA().getZONAA1().getTrabajador().getNombres())));
		campos.setFechaEmisionLME(null == respuesta.getZonaA().getZONAA1() ? "19000101" : set(respuesta.getZonaA().getZONAA1().getFechaEmision()));
		campos.setEmailAfiliado(dataTruncation(set(respuesta.getZonaA().getZONAAC().getEmailTrabajador()), 40));
		campos.setRutBeneficiario(separaNumDigRut(null == respuesta.getZonaA().getZONAA2() || null == respuesta.getZonaA().getZONAA2().getHijo() ? "0" : set(respuesta.getZonaA().getZONAA2().getHijo().getRut()), "N"));
		campos.setRutBeneficiarioDV(separaNumDigRut(null == respuesta.getZonaA().getZONAA2() || null == respuesta.getZonaA().getZONAA2().getHijo() ? "0" : set(respuesta.getZonaA().getZONAA2().getHijo().getRut()), "D"));
		campos.setApellidoPaternoBenf(null == respuesta.getZonaA().getZONAA2() || null == respuesta.getZonaA().getZONAA2().getHijo() ? "" : set(decodeUTF8(respuesta.getZonaA().getZONAA2().getHijo().getApellidoPaterno())));
		campos.setApellidoMaternoBenf(null == respuesta.getZonaA().getZONAA2() || null == respuesta.getZonaA().getZONAA2().getHijo() ? "" : set(decodeUTF8(respuesta.getZonaA().getZONAA2().getHijo().getApellidoPaterno())));
		campos.setNombreBenef(null == respuesta.getZonaA().getZONAA2() || null == respuesta.getZonaA().getZONAA2().getHijo() ? "" : set(decodeUTF8(respuesta.getZonaA().getZONAA2().getHijo().getNombres())));
		campos.setFechaNacBenef(null == respuesta.getZonaA().getZONAA2() || null == respuesta.getZonaA().getZONAA2().getHijo() ? "19000101" : set(respuesta.getZonaA().getZONAA2().getHijo().getFechaNacimiento()));
		campos.setRecuperabilidad(null == respuesta.getZonaA().getZONAA3() ? "" : set(respuesta.getZonaA().getZONAA3().getCodigoRecuperabilidad()));
		campos.setInicioTramite(null == respuesta.getZonaA().getZONAA3() ? "" : set(respuesta.getZonaA().getZONAA3().getCodigoInicioTramInv()));
		campos.setFechaAccidente(null == respuesta.getZonaA().getZONAA3() ? "" : set(respuesta.getZonaA().getZONAA3().getFechaAccidente()));
		campos.setTrayecto(null == respuesta.getZonaA().getZONAA3() ? "" : set(respuesta.getZonaA().getZONAA3().getCodigoTrayecto()));
		campos.setFechaConcepcion(null == respuesta.getZonaA().getZONAA3() ? "" : set(respuesta.getZonaA().getZONAA3().getFechaConcepcion()));
		campos.setJornadaacuerdo("");
		//LUGAR DE REPOSO
		CTLugarReposo[] lugarReposo = respuesta.getZonaA().getZONAA4().getLugarReposoArray();
		for (int i = 0; i < lugarReposo.length; i++) {
			if (i == 0) {
				campos.setLugardereposo1(dataTruncation(set(lugarReposo[i].getCodigoLugarReposo()), 2));
				campos.setJustifSiesOtro(dataTruncation(set(lugarReposo[i].getJustificaDomicilio()), 128));
				campos.setDireccionReposo1(dataTruncation(set(decodeUTF8(lugarReposo[i].getDireccionReposo().getCalle())), 100));
				campos.setCodigoComuna1(dataTruncation(lugarReposo[i].getDireccionReposo().getComuna().toString(), 5));
				campos.setGlosaComuna1(dataTruncation(lugarReposo[i].getDireccionReposo().getComuna().toString(), 30));
			} else {
				campos.setLugarReposo2(dataTruncation(lugarReposo[i].getCodigoLugarReposo().toString(), 2));
				campos.setDireccionReposo2(dataTruncation(set(lugarReposo[i].getDireccionReposo().getCalle()), 100));
				campos.setCodigoComuna2(dataTruncation(lugarReposo[i].getDireccionReposo().getComuna().toString(), 5));
				campos.setGlosaComuna2("");
			}
		}
		//TELEFONO
		CTTelefono[] telefono = respuesta.getZonaA().getZONAA4().getTelefonoReposoArray();
		for (int i = 0; telefono != null && i < telefono.length; i++) {
			if (i == 0) {
				campos.setTelefono1(dataTruncation(set(telefono[i].getTelefono()), 12));
			} else {
				campos.setTelefono2(dataTruncation(set(telefono[i].getTelefono()), 12));
			}
		}

		campos.setRutProfesional(separaNumDigRut(set(respuesta.getZonaA().getZONAA5().getProfesional().getRut()), "N"));
		campos.setRutProfesionalDV(separaNumDigRut(set(respuesta.getZonaA().getZONAA5().getProfesional().getRut()), "D"));
		campos.setApellidoPaternoProf(set(decodeUTF8(respuesta.getZonaA().getZONAA5().getProfesional().getApellidoPaterno())));
		campos.setApellidoMaternoProf(set(decodeUTF8(respuesta.getZonaA().getZONAA5().getProfesional().getApellidoMaterno())));
		campos.setNombreProfesional(set(decodeUTF8(respuesta.getZonaA().getZONAA5().getProfesional().getNombres())));
		campos.setDireccionProf(set(decodeUTF8(respuesta.getZonaA().getZONAA5().getProfDireccion().getCalle())));
		campos.setCodigoComunaProf(respuesta.getZonaA().getZONAA5().getProfDireccion().getComuna().toString());
		campos.setGlosaComunaProf("");
		campos.setTelefonoProfesional(set(respuesta.getZonaA().getZONAA5().getProfTelefono().getTelefono()));
		campos.setFaxProfesional(respuesta.getZonaA().getZONAA5().getProfFax() == null ? "0" : set(respuesta.getZonaA().getZONAA5().getProfFax().getTelefono()));
		campos.setEmailProfesional(set(respuesta.getZonaA().getZONAA5().getProfEmail()));
		campos.setGlosaEspecialidad(set(decodeUTF8(respuesta.getZonaA().getZONAA5().getProfEspecialidad())));
		campos.setCodigoEspecialidad(set(respuesta.getZonaA().getZONAA5().getCodigoTipoProfesional()));
		campos.setTipodePrestador("0");
		campos.setNColegioMedico(set(respuesta.getZonaA().getZONAA5().getProfRegistroColegio()));
		campos.setCodigoDiagPrincipal(dataTruncation(set(respuesta.getZonaA().getZONAAC().getCoddiagnosticoPrincipal()), 512));
		campos.setGlosaDiagPrincipal(dataTruncation(set(respuesta.getZonaA().getZONAA6().getDiagnosticoPrincipal()), 512));
		campos.setCodigoDiagSecundario(set(respuesta.getZonaA().getZONAAC().getCoddiagnosticoSecundario()));
		campos.setGlosaDiagSecundario(dataTruncation(set(respuesta.getZonaA().getZONAAC().getDiagnosticoSecundario()), 511));
		campos.setCodigoDiagOtro(set(respuesta.getZonaA().getZONAAC().getCoddiagnosticoOtro()));
		campos.setGlosaDiagOtro(dataTruncation(set(respuesta.getZonaA().getZONAA6().getDiagnosticoOtro()), 511));
		campos.setAntecedentesClinicos(dataTruncation(set(respuesta.getZonaA().getZONAA6().getAntecedentesClinicos()), 511));
		campos.setExamendeapoyo(dataTruncation(set(respuesta.getZonaA().getZONAA6().getExamenesApoyo()), 511));

		campos.setCodigodeError("0");
		campos.setMensajedeerror(" ");
		campos.setFechaEstadoOper("99991205");
		campos.setHoraEstadoOper("122055");
		//	***se ingresan en el mainManager ***
		//	campos.setGlosaConsumo(glosaConsumo); se ingresan en el mainmanager
		//	campos.setCodigoRespuestaConsumo(codigoRespuesta);

		//ILFE008
		campos.setEmpleadorAdscrito(String.valueOf(respuesta.getZona0().getZONA01().getEmpleadorAdscrito()));
		campos.setEntidadPagadora(String.valueOf(respuesta.getZona0().getZONA01().getCodigoEntidad()));
		//ILFE009
		//		CTEstado[] listaEstado1 = respuesta.getZona0().getZONA01().getEstadoArray();
		if (listaEstado != null) {
			for (int i = 0; i < listaEstado.length; i++) {
				campos.setMotivoNoRecepcion(null == listaEstado[i].getMotivoNorecepcion() ? "" : set(decodeUTF8(String.valueOf(listaEstado[i].getMotivoNorecepcion()))));
				campos.setFechaTerminoRelacion(null == listaEstado[i].getFechaTerminoRelacion() ? "19000101" : set(listaEstado[i].getFechaTerminoRelacion()));
				campos.setRutEmpleador(separaNumDigRut(listaEstado[i].getEmpRut(), "N"));
				campos.setRutEmpleadorDV(separaNumDigRut(listaEstado[i].getEmpRut(), "D"));
				campos.setCodigoCCAFTramite(set(String.valueOf(listaEstado[i].getCodigoTramitacionCCAF())));
				campos.setMotivoDevolucionCCAF(null == listaEstado[i].getMotivoDevolucionCCAF() ? "" : set(decodeUTF8(String.valueOf(listaEstado[i].getMotivoDevolucionCCAF()))));
				campos.setTipoLiquidacionCCAF(null == listaEstado[i].getTipoLiquidacionCCAF() ? "0" : set(listaEstado[i].getTipoLiquidacionCCAF()));
			}
		}
		return campos;
	}

	private static String getUltimoEstado(CTEstado[] listaEstado) {
		// busca ultimo estado
		int ultimoEstado = 0;
		Date fechaUtimoEstado = null;
		for (int i = 0; i < listaEstado.length; i++) {
			if (fechaUtimoEstado == null) {
				ultimoEstado = listaEstado[i].getEstadoLicencia().intValue();
				fechaUtimoEstado = listaEstado[i].getFechaEstado().getTime();
			}
			if (fechaUtimoEstado.compareTo(listaEstado[i].getFechaEstado().getTime()) < 0) {
				// actualizo estado
				ultimoEstado = listaEstado[i].getEstadoLicencia().intValue();
				fechaUtimoEstado = listaEstado[i].getFechaEstado().getTime();
			}
		}
		return String.valueOf(ultimoEstado);
	}

	private static String set(String s) {
		return null == s ? "" : s.trim();
	}

	private static String set(BigInteger i) {
		return null == i ? "0" : i.toString();
	}

	private static String set(Calendar c) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String d = "19000101";
		if (null != c) {
			d = sdf.format(c.getTime());
		}
		return d;
	}

	public static String decodeUTF8(String str) {
		if (null == str)
			return "";
		try {
			byte bytes[] = str.getBytes("UTF-8");//
			str = new String(bytes, "UTF-8");//"UTF-8"
		} catch (UnsupportedEncodingException e) {
			//logger.logError(e.getClass() + "; "+ e.getMessage());
			//		logger.error(e.getClass() + "; " + e.getMessage());
		}

		return str;
	}

	private static String dataTruncation(String str, int i) {
		return str.length() > i ? str.substring(0, i) : str;
	}

	private static String separaNumDigRut(String rut, String tipo) {
		String auxRut = "0";
		if (rut != null && rut != "0") {
			int pos = rut.indexOf("-");
			if (pos > 0) {
				String[] arRut = rut.split("-");
				if (tipo.equals("N"))
					auxRut = arRut[0];
				else
					auxRut = arRut[1];
			}
		}
		return auxRut;
	}
}
