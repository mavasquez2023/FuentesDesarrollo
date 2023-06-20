/**
 * 
 */
package cl.araucana.lme.job;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import wwwLmeGovClLme.CTDetalleHaber;
import wwwLmeGovClLme.CTDireccionArchivo;
import wwwLmeGovClLme.CTEstado;
import wwwLmeGovClLme.CTLugarReposo;
import wwwLmeGovClLme.CTRemuneracion;
import wwwLmeGovClLme.CTTelefono;
import wwwLmeGovClLme.STJornadaReposo;
import wwwLmeGovClLme.STJornadaReposo.Enum;
import cl.araucana.lme.helper.Helper;
import cl.araucana.lme.ibatis.domain.DetalleVO;
import cl.araucana.lme.ibatis.domain.Ilfe002VO;
import cl.araucana.lme.ibatis.domain.IlfeOpeVO;
import cl.araucana.lme.svc.IAS400Svc_LME;
import cl.araucana.lme.svc.SvcFactory_LME;
import cl.araucana.lme.svc.exception.SvcException;
import cl.araucana.lme.util.EndPointUtil;
import cl.araucana.lme.util.LabelValueVO;
import cl.araucana.lme.util.Util;
import conector.lme.ws.cliente.operador.RespuestaDetalleLicencia;
import conector.lme.ws.cliente.operador.ServiciosMultiOperador;
import conector.vo.SalidaLMEDetLcc;

/**
 * @author usist199
 *
 */
public class ConsumirDetalle {
	
	private Logger log = Logger.getLogger(this.getClass());
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private final String TIPO_INSTITUCION = "C";
	private IAS400Svc_LME svc_a = null;

	/**
	 * @param lic
	 */
	public SalidaLMEDetLcc consumirDetalleLME(IlfeOpeVO lic) {

		String flg = "-1";
		String error = "OK";
		String numimpre = "";
		
		//svc_a = SvcFactory_LME.getAS400Svc_LME();
		
		// UrlBorderVO urlVO = new UrlBorderVO();
		// urlVO.setCodOpe(lic.getCodOpe());
		numimpre = lic.getNumLicencia();
		// urlVO.setNombreServicio("DETALLE");
		String nombreHash = "DETALLE";

		try {
			EndPointUtil instanciaEndPoint = EndPointUtil.getInstance();
			/*
			 * String url = svc.getUrlVordel(urlVO); if(null==url){
			 * log("IGNORANDO SERVICIO"); return new LabelValueVO("SERVICIO
			 * IGNORANDO URL_VORDEL=null","-1"); } String urlOpe = url;
			 */

			/** ****************************************** */
			// guarda la url que no tenga error segun la prioridad
			String url1 = "";
			String urlUtilizada = "";
			long tiempoUtilizado = 0;
			Boolean error1 = instanciaEndPoint.getEstadoError(lic.getCodOpe(), "1");
			if (error1 != null && error1 == Boolean.FALSE) {
				// System.out.println("No tiene error en la prioridad 1");
				url1 = instanciaEndPoint.getEndPoint(lic.getCodOpe(), nombreHash, "1");
			}
	
			String url2 = "";
			Boolean error2 = instanciaEndPoint.getEstadoError(lic.getCodOpe(), "2");
			if (error2 != null && error2 == Boolean.FALSE) {
				// System.out.println("No tiene error en la prioridad 2");
				url2 = instanciaEndPoint.getEndPoint(lic.getCodOpe(), nombreHash, "2");
			}

			/** ********************************************* */

			ServiciosMultiOperador servicio = new ServiciosMultiOperador(lic.getCodOpe().trim(), TIPO_INSTITUCION, lic.getCodCcaf().trim(), lic.getPwdCcaf().trim(), url1, url2);

			// ejecuta el nuevo servicio que maneja el timeout y utiliza la dos url enviadas
			SalidaLMEDetLcc respuesta = servicio.consultaDetalleLicencia2(Helper.toBigInteger(lic.getNumLicencia()), lic.getDigLicencia());

			/** **************************************************** */
			// se asignan errores de timeout segun la respuesta del servico y se
			// guarda el tiempo de demoro del servicio que funciona
			// correctamente
			if (respuesta.isError1()) {

				if (instanciaEndPoint.getEstadoError(lic.getCodOpe(), "1") == Boolean.FALSE) {
					instanciaEndPoint.cambiarEstadoError(lic.getCodOpe(), "1", Boolean.TRUE);
					log.warn("Problemas por TimeOut con la licencia " + lic.getNumLicencia() + " al tratar de ejecutar servicio " + url1 + " (Vordel) en el operador " + lic.getCodOpe());
				}
				if (respuesta.isError2()) {
					instanciaEndPoint.cambiarEstadoError(lic.getCodOpe(), "2", Boolean.TRUE);
					log.warn("Problemas por TimeOut con la licencia " + lic.getNumLicencia() + " al tratar de ejecutar servicio " + url2 + " (" + instanciaEndPoint.getNombreOperador(lic.getCodOpe()) + ") en el operador " + lic.getCodOpe());
					// System.out.println("Problemas por TimeOut con la licencia
					// "+vo.getNumImpre()+" al tratar de ejecutar servicio
					// "+url2+" en el operador "+vo.getCodOpe());
				} else {
					urlUtilizada = url2;
					tiempoUtilizado = respuesta.getTiempo2();
				}

				// System.out.println("Problemas por TimeOut con la licencia
				// "+vo.getNumImpre()+" al tratar de ejecutar servicio "+url1+"
				// en el operador "+vo.getCodOpe());
			} else {
				urlUtilizada = url1;
				tiempoUtilizado = respuesta.getTiempo1();
			}

			if (respuesta.isError1() && respuesta.isError2()) {
				instanciaEndPoint.cambiarEstadoError(lic.getCodOpe(), Boolean.TRUE);
				//return new LabelValueVO("error en ambos endpoint con operador " + lic.getCodOpe(), "-1");
				return null;
			}
			
			return respuesta;
			/** *************************************************** */

		} catch (Exception e) {
			logError(e);
			error = Helper.dataTruncation(e.getCause().getMessage(), 50);
			return null;
		}
		// System.out.println("la error 1 (error) es: " + error);
		//return new LabelValueVO(error, flg);
	}
		
	
	public void grabarZona0(RespuestaDetalleLicencia respuesta, IlfeOpeVO lic) {

		
		int ultimoEstado= Integer.parseInt(lic.getEstado());
		String fechaUltEst= lic.getFechaEstado();
		String horaUltEst= lic.getHoraEstado();
		log.info("::: Consumiendo Licencia[" + lic.getNumLicencia() + "]	Estado[" + String.valueOf(ultimoEstado) + "]");// urlOpe

		// Zona 0
		//----------------------------------------------------------------------
		// -----------------

		/*
		 * Toma de variables para armar cabecera de la zona 0.
		 */
		String AFIRUT = "";
		String AFIRUTDV = "";

		/* CABECERA, ILFE008. */
		AFIRUT = Helper.set(respuesta.getZonaA().getZONAA1().getTrabajador().getRut()); // Rut
		// Afiliado.

		/*
		 * Uso de método llamado "separaNumDigRut" para separar el rut del
		 * afiliado en cuerpo y dígito verificador.
		 */
		String rut = AFIRUT;
		AFIRUT = Helper.separaNumDigRut(rut, "N");
		AFIRUTDV = Helper.separaNumDigRut(rut, "D");		
		String ADSCRITO = Helper.set(respuesta.getZona0().getZONA01().getEmpleadorAdscrito()); // Empleador
																						// Adscrito
		String CODTIPSU = Helper.set(respuesta.getZona0().getZONA01().getCodigoEntidad()); // Entidad
																					// Pagadora

		/*
		 * Armado de HashMap con datos generados, para ingresar datos a base de
		 * datos a través de ORM Ibatis 2.0.
		 */
		Hashtable h = new Hashtable();
		h.put("ideope", lic.getIdeOpe());
		h.put("numLicencia", lic.getNumLicencia());
		h.put("digLicencia", lic.getDigLicencia());
		h.put("AFIRUT", AFIRUT);
		h.put("AFIRUTDV", AFIRUTDV);
		h.put("ADSCRITO", ADSCRITO);
		h.put("CODTIPSU", CODTIPSU);

		/*
		 * Se inserta en la tabla DB2 LIEXP.ILFE008, en caso de error por código
		 * SQL "SQL0803" (Controla error por PK) registra en Log.
		 */
		String valueIlfe008 = svc_a.insertIlfe008(h);

		if (!"OK".equals(valueIlfe008) || valueIlfe008.indexOf("SQL0803") != -1) {
			if (valueIlfe008.indexOf("SQL0803") != -1) {
				log.error("La licencia número [" + lic.getNumLicencia() + "] con estado [" + ultimoEstado + "] falló al intentar registrar en ILFE008, PK");
			} else {
				log.error("La licencia número [" + lic.getNumLicencia() + "] con estado [" + ultimoEstado + "] falló al intentar registrar en ILFE008, " + valueIlfe008);

			}
			// labelValueVO.setValue(valueIlfe008);
			// labelValueVO.setLabel("Ilfe008");
		} else {
			log.info("La licencia número [" + lic.getNumLicencia() + "] con estado [" + ultimoEstado + "] se registró exitosamente en ILFE008");
		}

		/* DETALLE, ILFE009 */
		CTEstado[] listaEstado = respuesta.getZona0().getZONA01().getEstadoArray();

		String ESTLICEN = "";
		String FECHAEST = "";
		String HORAEST = "";
		String MOTNREC = "";
		String FECTERREL = "";
		String RUTEMPLE = "";
		String DIGEMPLE = "";

		String CODCCAF = "";
		String MOTDEVOL = "";
		String TIPOLIQ = "";

		String pruebaRut = "";

		/*
		 * Condicional que pregunta por cantidad de licencias que posee el
		 * listo, haciendo enfasís en el número de números de licencias (datos
		 * ingresado en archivo LOG).
		 */
		if (listaEstado != null) {
			log.info("La cantidad de estados del listado de licencias es = " + listaEstado.length);
			/*
			 * Se recorre listado "CTEstado[] listaEstado" para obtener datos a
			 * ingresar en detalle de las listas en ILFE009.
			 */
			for (int i = 0; i < listaEstado.length; i++) {

				// pruebaRut = listaEstado[i].getEmpRut();
				AFIRUT = Helper.set(respuesta.getZonaA().getZONAA1().getTrabajador().getRut()); // Rut
																							// Afiliado
																							// .
				AFIRUTDV = "";

				rut = AFIRUT;
				AFIRUT = Helper.separaNumDigRut(rut, "N");
				AFIRUTDV = Helper.separaNumDigRut(rut, "D");

				ESTLICEN = Helper.set(String.valueOf(listaEstado[i].getEstadoLicencia()));
				String FechaHora = String.valueOf(listaEstado[i].getFechaEstado());
				MOTNREC = null == listaEstado[i].getMotivoNorecepcion() ? "" : Helper.set(Util.decodeUTF8(String.valueOf(listaEstado[i].getMotivoNorecepcion())));
				FECTERREL = null == listaEstado[i].getFechaTerminoRelacion() ? "19000101" : Helper.set(listaEstado[i].getFechaTerminoRelacion());
				RUTEMPLE = Helper.set(listaEstado[i].getEmpRut());
				CODCCAF = Helper.set(String.valueOf(listaEstado[i].getCodigoTramitacionCCAF()));
				MOTDEVOL = null == listaEstado[i].getMotivoDevolucionCCAF() ? "" : Helper.set(Util.decodeUTF8(String.valueOf(listaEstado[i].getMotivoDevolucionCCAF())));
				TIPOLIQ = null == listaEstado[i].getTipoLiquidacionCCAF() ? "0" : Helper.set(listaEstado[i].getTipoLiquidacionCCAF());

				if (RUTEMPLE == "0-")
					RUTEMPLE = "0-0";

				/*
				 * Uso de método llamado "separaNumDigRut" para separar el rut
				 * del afiliado en cuerpo y dígito verificador.
				 */
				rut = RUTEMPLE;
				RUTEMPLE = Helper.separaNumDigRut(rut, "N");
				DIGEMPLE = Helper.separaNumDigRut(rut, "D");

				/*
				 * Se realiza split de la fecha&hora para ingreso de datos de
				 * acuerdo a formato en DB.
				 */
				FECHAEST = FechaHora.substring(0, 10).replaceAll("-", "");
				HORAEST = FechaHora.substring(11).replaceAll(":", "");

				// String partes[] = FECHAEST.split("-");
				// String comp[] = HORAEST.split(":");

				// String fecha = partes[0] + partes[1] + partes[2];
				// String hora = comp[0] + comp[1] + comp[2];

				/*
				 * Armado de HashMap con datos generados, para ingresar datos a
				 * base de datos a través de ORM Ibatis 2.0.
				 */
				h = new Hashtable();
				h.put("ideope", lic.getIdeOpe());
				h.put("numLicencia", lic.getNumLicencia());
				h.put("AFIRUT", AFIRUT);
				h.put("ESTLICEN", ESTLICEN);
				h.put("FECHAEST", FECHAEST);
				h.put("HORAEST", HORAEST);
				h.put("MOTNREC", MOTNREC);
				h.put("FECTERREL", FECTERREL);
				h.put("RUTEMPLE", RUTEMPLE);
				h.put("DIGEMPLE", DIGEMPLE);
				h.put("CODCCAF", CODCCAF);
				h.put("MOTDEVOL", MOTDEVOL);
				h.put("TIPOLIQ", TIPOLIQ);

				/*
				 * Se inserta en la tabla DB2 LIEXP.ILFE009, en caso de error
				 * por código SQL "SQL0803" (Controla error por PK) registra en
				 * Log.
				 */
				String valueIlfe009 = svc_a.insertIlfe009(h);

				if (!"OK".equals(valueIlfe009) || valueIlfe009.indexOf("SQL0803") != -1) {

					if (valueIlfe009.indexOf("SQL0803") != -1) {
						log.error("La licencia número [" + lic.getNumLicencia() + "] con estado [" + ultimoEstado + "] falló al intentar registrar en ILFE009, PK");
					} else {
						log.error("La licencia número [" + lic.getNumLicencia() + "] con estado [" + ultimoEstado + "] falló al intentar registrar en ILFE009, " + valueIlfe009);
					}
					// labelValueVO.setValue(valueIlfe009);
					// labelValueVO.setLabel("Ilfe009");
				} else {
					log.info("La licencia número [" + lic.getNumLicencia() + "] con estado [" + ultimoEstado + "] se registró exitosamente en ILFE009");
				}
			}
		} else {
			log.info("No presenta estados la licencia número #" + lic.getNumLicencia());
		}
	}
	
	public void grabarZonaA(RespuestaDetalleLicencia respuesta, IlfeOpeVO lic) {
		// Zona A
		//----------------------------------------------------------------------
		// -----------------

		String AFIRUT = Helper.set(respuesta.getZonaA().getZONAA1().getTrabajador().getRut());
		String AFIRUTDV = "";

		String APAAFI = Helper.set(Util.decodeUTF8(respuesta.getZonaA().getZONAA1().getTrabajador().getApellidoPaterno()));
		APAAFI = Helper.dataTruncation(APAAFI, 30);
		String AMAAFI = Helper.set(Util.decodeUTF8(respuesta.getZonaA().getZONAA1().getTrabajador().getApellidoMaterno()));
		AMAAFI = Helper.dataTruncation(AMAAFI, 30);
		String NOMAFI = Helper.set(Util.decodeUTF8(respuesta.getZonaA().getZONAA1().getTrabajador().getNombres()));
		NOMAFI = Helper.dataTruncation(NOMAFI, 30);
		
		String FECEMILI = null == respuesta.getZonaA().getZONAA1() ? "19000101" : Helper.set(respuesta.getZonaA().getZONAA1().getFechaEmision());
		String FECINIRE = null == respuesta.getZonaA().getZONAA1() ? "19000101" : Helper.set(respuesta.getZonaA().getZONAA1().getFechaInicioReposo());
		
		try {
			sdf.parse(FECINIRE);
		} catch (ParseException e) {
			// TODO Bloque catch generado automáticamente
			// e.printStackTrace();
			log.info("::: Al realizar el consumo de la licencia Licencia[" + lic.getNumLicencia() + "] - Estado[" + lic.getEstado() + "] se produjo un problema por el formato de la ( Fecha de Inicio Reposo Fecha[" + FECINIRE + "] ), no insertaremos esta licencia");
			logError(e);
			//return null;
		}

		String AFIEDAD = null == respuesta.getZonaA().getZONAA1() ? "0" : Helper.set(respuesta.getZonaA().getZONAA1().getTrabajador().getEdad());
		AFIEDAD = Helper.dataTruncation(AFIEDAD, 2);
		String AFISEXO = null == respuesta.getZonaA().getZONAA1() ? "" : respuesta.getZonaA().getZONAA1().getTrabajador().getSexo().toString();
		AFISEXO = Helper.dataTruncation(AFISEXO, 1);
		String AFIEMAIL = Helper.dataTruncation(Helper.set(respuesta.getZonaA().getZONAAC().getEmailTrabajador()), 40);
		String NUMDIALI = Helper.set(respuesta.getZonaA().getZONAA1().getTraNdias());
		NUMDIALI = Helper.dataTruncation(NUMDIALI, 2);
		String RUTHIJO = null == respuesta.getZonaA().getZONAA2() || null == respuesta.getZonaA().getZONAA2().getHijo() ? "0" : Helper.set(respuesta.getZonaA().getZONAA2().getHijo().getRut());
		String DIVHIJO = "0";
		String APAHIJO = null == respuesta.getZonaA().getZONAA2() || null == respuesta.getZonaA().getZONAA2().getHijo() ? "" : Helper.set(Util.decodeUTF8(respuesta.getZonaA().getZONAA2().getHijo().getApellidoPaterno()));
		APAHIJO = Helper.dataTruncation(APAHIJO, 30);
		String AMAHIJO = null == respuesta.getZonaA().getZONAA2() || null == respuesta.getZonaA().getZONAA2().getHijo() ? "" : Helper.set(Util.decodeUTF8(respuesta.getZonaA().getZONAA2().getHijo().getApellidoMaterno()));
		AMAHIJO = Helper.dataTruncation(AMAHIJO, 30);
		String NOMHIJO = null == respuesta.getZonaA().getZONAA2() || null == respuesta.getZonaA().getZONAA2().getHijo() ? "" : Helper.set(Util.decodeUTF8(respuesta.getZonaA().getZONAA2().getHijo().getNombres()));
		NOMHIJO = Helper.dataTruncation(NOMHIJO, 30);
		String FECNACHI = null == respuesta.getZonaA().getZONAA2() || null == respuesta.getZonaA().getZONAA2().getHijo() ? "19000101" : Helper.set(respuesta.getZonaA().getZONAA2().getHijo().getFechaNacimiento());// 19000101
		String TIPLICEN = null == respuesta.getZonaA().getZONAA3() ? "" : Helper.set(respuesta.getZonaA().getZONAA3().getCodigoTipoLicencia());
		TIPLICEN = Helper.dataTruncation(TIPLICEN, 2);
		
		String RECUPERA = null == respuesta.getZonaA().getZONAA3() ? "" : Helper.set(respuesta.getZonaA().getZONAA3().getCodigoRecuperabilidad());
		RECUPERA = Helper.dataTruncation(RECUPERA, 2);
		String INITRAMI = null == respuesta.getZonaA().getZONAA3() ? "" : Helper.set(respuesta.getZonaA().getZONAA3().getCodigoInicioTramInv());
		INITRAMI = Helper.dataTruncation(INITRAMI, 2);
		String FECACCID = null == respuesta.getZonaA().getZONAA3() ? "" : Helper.set(respuesta.getZonaA().getZONAA3().getFechaAccidente());
		String TRAYECTO = null == respuesta.getZonaA().getZONAA3() ? "" : Helper.set(respuesta.getZonaA().getZONAA3().getCodigoTrayecto());
		TRAYECTO = Helper.dataTruncation(TRAYECTO, 2);
		String FECCONCE = null == respuesta.getZonaA().getZONAA3() ? "" : Helper.set(respuesta.getZonaA().getZONAA3().getFechaConcepcion());
		
		String TIPREPOS = null == respuesta.getZonaA().getZONAA3() ? "" : Helper.set(respuesta.getZonaA().getZONAA4().getCodigoTipoReposo());
		TIPREPOS = Helper.dataTruncation(TIPREPOS, 2);

		String JORACUER = "0";
		String LUGREPOP = "0"; // lugaresReposo[i].codigoLugarReposo ?: ''
		String JUSSIOTR = "";
		String DIRREP01 = ""; // lugaresReposo[i].calle ?: ''
		String CODCOM01 = "0";
		String GLOCOM01 = ""; // lugaresReposo[i].comuna ?: ''
		String TELEFO01 = "";// telenfonosReposo[i].telefono ?: ''

		String LUGREP02 = "0"; // lugaresReposo[i].codigoLugarReposo ?: ''
		String DIRREP02 = ""; // lugaresReposo[i].calle ?: ''
		String CODCOM02 = "0";
		String GLOCOM02 = ""; // lugaresReposo[i].comuna ?: ''
		String TELEFO02 = ""; // telenfonosReposo[i].telefono ?: ''
		
		CTLugarReposo[] lugarReposo = respuesta.getZonaA().getZONAA4().getLugarReposoArray();
		for (int i = 0; i < lugarReposo.length; i++) {
			if (i == 0) {
				LUGREPOP = Helper.set(lugarReposo[i].getCodigoLugarReposo());
				LUGREPOP = Helper.dataTruncation(LUGREPOP, 2);
				JUSSIOTR = Helper.set(lugarReposo[i].getJustificaDomicilio());
				JUSSIOTR = Helper.dataTruncation(JUSSIOTR, 128);
				DIRREP01 = Helper.set(Util.decodeUTF8(lugarReposo[i].getDireccionReposo().getCalle()));
				DIRREP01 = Helper.dataTruncation(DIRREP01, 100);
				CODCOM01 = lugarReposo[i].getDireccionReposo().getComuna().toString();
				CODCOM01 = Helper.dataTruncation(CODCOM01, 5);
				GLOCOM01 = lugarReposo[i].getDireccionReposo().getComuna().toString();
				GLOCOM01 = Helper.dataTruncation(GLOCOM01, 30);
			} else {
				LUGREP02 = lugarReposo[i].getCodigoLugarReposo().toString();
				LUGREP02 = Helper.dataTruncation(LUGREP02, 2);
				DIRREP02 = Helper.set(lugarReposo[i].getDireccionReposo().getCalle());
				DIRREP02 = Helper.dataTruncation(DIRREP02, 100);
				CODCOM02 = lugarReposo[i].getDireccionReposo().getComuna().toString();
				CODCOM02 = Helper.dataTruncation(CODCOM02, 5);
			}
		}
		if(Integer.parseInt(TIPREPOS)==2){
			Enum jornadaReposo = respuesta.getZonaA().getZONAA4().getCodigoJornadaReposoArray(0);
			JORACUER= null == jornadaReposo ? "0" : Helper.set(jornadaReposo.toString());
		}
		CTTelefono[] telefono = respuesta.getZonaA().getZONAA4().getTelefonoReposoArray();
		for (int i = 0; telefono != null && i < telefono.length; i++) {
			if (i == 0) {
				TELEFO01 = Helper.dataTruncation(Helper.set(telefono[i].getTelefono()), 12);
			} else {
				TELEFO02 = Helper.dataTruncation(Helper.set(telefono[i].getTelefono()), 12);
			}
		}
		String RUTPROFE = Helper.set(respuesta.getZonaA().getZONAA5().getProfesional().getRut());
		String DIVPROFE = "0";
		String APAPROFE = Helper.set(Util.decodeUTF8(respuesta.getZonaA().getZONAA5().getProfesional().getApellidoPaterno()));
		APAPROFE = Helper.dataTruncation(APAPROFE, 30);
		String AMAPROFE = Helper.set(Util.decodeUTF8(respuesta.getZonaA().getZONAA5().getProfesional().getApellidoMaterno()));
		AMAPROFE = Helper.dataTruncation(AMAPROFE, 30);
		String NOMPROFE = Helper.set(Util.decodeUTF8(respuesta.getZonaA().getZONAA5().getProfesional().getNombres()));
		NOMPROFE = Helper.dataTruncation(NOMPROFE, 30);
		String DIRPROFE = Helper.set(Util.decodeUTF8(respuesta.getZonaA().getZONAA5().getProfDireccion().getCalle()));
		DIRPROFE = Helper.dataTruncation(DIRPROFE, 100);
		String CODCOMPR = respuesta.getZonaA().getZONAA5().getProfDireccion().getComuna().toString();
		CODCOMPR = Helper.dataTruncation(CODCOMPR, 5);
		String GLOCOMPR = "";
		String FONPROFE = Helper.set(respuesta.getZonaA().getZONAA5().getProfTelefono().getTelefono());
		FONPROFE = Helper.dataTruncation(FONPROFE, 12);
		String FAXPROFE = respuesta.getZonaA().getZONAA5().getProfFax() == null ? "0" : Helper.set(respuesta.getZonaA().getZONAA5().getProfFax().getTelefono());
		FAXPROFE = Helper.dataTruncation(FAXPROFE, 12);
		String EMAPROFE = Helper.set(respuesta.getZonaA().getZONAA5().getProfEmail());
		EMAPROFE = Helper.dataTruncation(EMAPROFE, 40);
		String GLOESPEC = Helper.set(Util.decodeUTF8(respuesta.getZonaA().getZONAA5().getProfEspecialidad()));
		GLOESPEC = Helper.dataTruncation(GLOESPEC, 80);
		String CODESPEC = Helper.set(respuesta.getZonaA().getZONAA5().getCodigoTipoProfesional());
		String TIPPREST = "0";
		String NROCOMED = Helper.set(respuesta.getZonaA().getZONAA5().getProfRegistroColegio());
		NROCOMED = Helper.dataTruncation(NROCOMED, 32);
		String CODDIAPR = Helper.dataTruncation(Helper.set(respuesta.getZonaA().getZONAAC().getCoddiagnosticoPrincipal()), 512);
		String GLODIAPR = Helper.dataTruncation(Helper.set(respuesta.getZonaA().getZONAA6().getDiagnosticoPrincipal()), 512);
		String CODDIASE = Helper.set(respuesta.getZonaA().getZONAAC().getCoddiagnosticoSecundario());
		CODDIASE = Helper.dataTruncation(CODDIASE, 12);
		String GLODIASE = Helper.dataTruncation(Helper.set(respuesta.getZonaA().getZONAAC().getDiagnosticoSecundario()), 511);
		String CODDIAOT = Helper.set(respuesta.getZonaA().getZONAAC().getCoddiagnosticoOtro());
		CODDIAOT = Helper.dataTruncation(CODDIAOT, 12);
		String GLODIAOT = Helper.dataTruncation(Helper.set(respuesta.getZonaA().getZONAA6().getDiagnosticoOtro()), 511);
		String ANTECCLI = Helper.dataTruncation(Helper.set(respuesta.getZonaA().getZONAA6().getAntecedentesClinicos()), 511);
		String EXAAPOYO = Helper.dataTruncation(Helper.set(respuesta.getZonaA().getZONAA6().getExamenesApoyo()), 511);

		String rut = AFIRUT;
		AFIRUT = Helper.separaNumDigRut(rut, "N");
		AFIRUTDV = Helper.separaNumDigRut(rut, "D");

		rut = RUTHIJO.trim();
		if (rut == "")
			rut = "0-0";

		RUTHIJO = Helper.separaNumDigRut(rut, "N");
		DIVHIJO = Helper.separaNumDigRut(rut, "D");

		rut = RUTPROFE;
		RUTPROFE = Helper.separaNumDigRut(rut, "N");
		DIVPROFE = Helper.separaNumDigRut(rut, "D");

		Hashtable h = new Hashtable();
		h.put("ideope", lic.getIdeOpe());
		h.put("numLicencia", lic.getNumLicencia());
		h.put("digLicencia", lic.getDigLicencia());
		h.put("ultimoEstado", String.valueOf(lic.getEstado()));
		h.put("horaUltEstado", lic.getHoraEstado()); 
											
		h.put("fechaUltEstado", lic.getFechaEstado()); 
												
		h.put("AFIRUT", AFIRUT);
		h.put("AFIRUTDV", AFIRUTDV);
		h.put("APAAFI", APAAFI);
		h.put("AMAAFI", AMAAFI);
		h.put("NOMAFI", NOMAFI);
		h.put("FECEMILI", FECEMILI);
		h.put("FECINIRE", FECINIRE);
		h.put("AFIEDAD", AFIEDAD);
		h.put("AFISEXO", AFISEXO);
		h.put("AFIEMAIL", AFIEMAIL);
		h.put("NUMDIALI", NUMDIALI);
		h.put("RUTHIJO", RUTHIJO);
		h.put("DIVHIJO", DIVHIJO);
		h.put("APAHIJO", APAHIJO);
		h.put("AMAHIJO", AMAHIJO);
		h.put("NOMHIJO", NOMHIJO);
		h.put("FECNACHI", FECNACHI);
		h.put("TIPLICEN", TIPLICEN);
		h.put("RECUPERA", RECUPERA);
		h.put("INITRAMI", INITRAMI);
		h.put("FECACCID", FECACCID);
		h.put("TRAYECTO", TRAYECTO);
		h.put("FECCONCE", FECCONCE);
		h.put("TIPREPOS", TIPREPOS);
		h.put("JORACUER", JORACUER);
		h.put("LUGREPOP", LUGREPOP);
		h.put("JUSSIOTR", JUSSIOTR);
		h.put("DIRREP01", DIRREP01);
		h.put("CODCOM01", CODCOM01);
		h.put("GLOCOM01", GLOCOM01);
		h.put("TELEFO01", TELEFO01);
		h.put("LUGREP02", LUGREP02);
		h.put("DIRREP02", DIRREP02);
		h.put("CODCOM02", CODCOM02);
		h.put("GLOCOM02", GLOCOM02);
		h.put("TELEFO02", TELEFO02);
		h.put("RUTPROFE", RUTPROFE);
		h.put("DIVPROFE", DIVPROFE);
		h.put("APAPROFE", APAPROFE);
		h.put("AMAPROFE", AMAPROFE);
		h.put("NOMPROFE", NOMPROFE);
		h.put("DIRPROFE", DIRPROFE);
		h.put("CODCOMPR", CODCOMPR);
		h.put("GLOCOMPR", GLOCOMPR);
		h.put("FONPROFE", FONPROFE);
		h.put("FAXPROFE", FAXPROFE);
		h.put("EXAAPOYO", EXAAPOYO);
		h.put("EMAPROFE", EMAPROFE);
		h.put("GLOESPEC", GLOESPEC);
		h.put("CODESPEC", CODESPEC);
		h.put("TIPPREST", TIPPREST);
		h.put("NROCOMED", NROCOMED);
		h.put("CODDIAPR", CODDIAPR);
		h.put("GLODIAPR", GLODIAPR);
		h.put("CODDIASE", CODDIASE);
		h.put("GLODIASE", GLODIASE);
		h.put("CODDIAOT", CODDIAOT);
		h.put("GLODIAOT", GLODIAOT);
		h.put("ANTECCLI", ANTECCLI);

		// System.out.println(
		// "##################################################################################################################"
		// );
		// System.out.println("String valueIlfe002 = svc.insertIlfe002(h);");
		// System.out.println(
		// "##################################################################################################################"
		// );

		String valueIlfe002 = svc_a.insertIlfe002(h);
		if (!"OK".equals(valueIlfe002) || valueIlfe002.indexOf("SQL0803") != -1) {
			if (valueIlfe002.indexOf("SQL0803") != -1) {
				log.error("La licencia número [" + lic.getNumLicencia() + "] con estado [" + lic.getEstado() + "] falló al intentar registrar en ILFE002, PK");
			} else {
				log.error("La licencia número [" + lic.getNumLicencia() + "] con estado [" + lic.getEstado() + "] falló al intentar registrar en ILFE002, " + valueIlfe002);

			}
			// labelValueVO.setValue(valueIlfe002);
			// labelValueVO.setLabel("Ilfe002");
		} else {
			log.info("La licencia número [" + lic.getNumLicencia() + "] con estado [" + lic.getEstado() + "] se registró exitosamente en ILFE002");
		}

	}
	
	public boolean grabarZonaC(RespuestaDetalleLicencia respuesta, IlfeOpeVO lic) {
		try {
			// Zona C
			//----------------------------------------------------------------------
			String NOMEMPLE ="";
			String APAEMPLE = "";
			String AMAEMPLE = "";
			String TIPEMPLE = "0";
			String DIREMPLE = "";
			String COMEMPLE = "";
			String GLOCOMEM = "";
			String FONOEMPL = "0";
			String EMAIEMPL = "";
			String FECREPEM = "0";
			String FECENVEM = "0";
			String CODCOMPI = "";
			String CODACTLA = "";
			String CODOCUPA = "";
			String GLOOTROC = "";
			String CODREGPR = "";
			String PENSIONA = "0";
			String CODINSPR = "";
			String LETCAJPR = "";
			String CODCALTR = "";
			String AFC = "";
			String NOMAFC = "";
			String CONINDEF = "";
			String CODESTAT = "0";
			String FECAFILI = "0";
			String FECCONTR = "0";
			String PORDESAU = "0";
			String RENIMPON = "0";
			String MOTRECEM = "";
			String RUTEMPLE = "";
			String DIGEMPLE = "";
			String CODCCAF  = "";
			String CODTIPSU = "";
			String AFIRUT = Helper.set(respuesta.getZonaA().getZONAA1().getTrabajador().getRut());
			String rut= AFIRUT;
			
			if (null != respuesta.getZonaC()) {
				AFIRUT = Helper.separaNumDigRut(rut, "N");
				
				RUTEMPLE = respuesta.getZonaC().getZONAC1().getEmpRut();// "0-0"
				if (RUTEMPLE == "0-")
					RUTEMPLE = "0-0";

				DIGEMPLE = "";
				NOMEMPLE = Helper.dataTruncation(Util.decodeUTF8(respuesta.getZonaC().getZONAC1().getEmpNombre()), 255);
				DIREMPLE = respuesta.getZonaC().getZONAC1().getEmpDireccion() == null ? "" : Util.decodeUTF8(respuesta.getZonaC().getZONAC1().getEmpDireccion().getCalle());
				DIREMPLE = Helper.dataTruncation(DIREMPLE, 100);
				COMEMPLE = respuesta.getZonaC().getZONAC1().getEmpDireccion() == null ? "" : String.valueOf(respuesta.getZonaC().getZONAC1().getEmpDireccion().getComuna());
				COMEMPLE = Helper.dataTruncation(COMEMPLE, 5);
				
				FONOEMPL = respuesta.getZonaC().getZONAC1().getEmpTelefono() == null ? "0" : Helper.set(respuesta.getZonaC().getZONAC1().getEmpTelefono().getTelefono());
				FONOEMPL = Helper.dataTruncation(FONOEMPL, 12);
				
				FECREPEM = Helper.set(respuesta.getZonaC().getZONAC1().getEmpFechaRecepcion());// "0"

				CODCCAF = null == respuesta.getZonaC().getZONACC() ? "0" : Helper.set(respuesta.getZonaC().getZONACC().getCodigoTramitacionCCAF());
				CODCCAF = Helper.dataTruncation(CODCCAF, 10); // ERRO LARGO, VALOR: 10105
				CODCOMPI = Helper.set(respuesta.getZonaC().getZONAC1().getCodigoComunaCompin());
				CODCOMPI = Helper.dataTruncation(CODCOMPI, 4);
				CODACTLA = Helper.set(respuesta.getZonaC().getZONAC1().getCodigoActividadLaboral());
				CODACTLA = Helper.dataTruncation(CODACTLA, 2);
				CODOCUPA = Helper.set(respuesta.getZonaC().getZONAC1().getCodigoOcupacion());
				CODOCUPA = Helper.dataTruncation(CODOCUPA, 2);
				
				CODREGPR = Helper.set(respuesta.getZonaC().getZONAC2().getCodigoRegimenPrevisional());
				CODREGPR = Helper.dataTruncation(CODREGPR, 4); // "0" //trae -1
				
				CODINSPR = Helper.set(respuesta.getZonaC().getZONAC2().getCodigoRegimenPrevisional());
				CODINSPR = Helper.dataTruncation(CODINSPR, 4); // "0"
				LETCAJPR = Helper.set(respuesta.getZonaC().getZONAC2().getCodigoLetraCaja());
				LETCAJPR = Helper.dataTruncation(LETCAJPR, 2);
				CODCALTR = Helper.set(respuesta.getZonaC().getZONAC2().getCodigoCalidadTrabajador());
				CODCALTR = Helper.dataTruncation(CODCALTR, 2);
				AFC = Helper.set(respuesta.getZonaC().getZONAC2().getCodigoSeguroAfc());
				AFC = Helper.dataTruncation(AFC, 2);
				
				CONINDEF = Helper.set(respuesta.getZonaC().getZONAC2().getCodigoSeguroIndef());
				CONINDEF = Helper.dataTruncation(CONINDEF, 2);
				
				FECAFILI = Helper.set(respuesta.getZonaC().getZONAC2().getFechaAfiliacion()); // "0"
				FECAFILI = Helper.dataTruncation(FECAFILI, 8);
				
				FECCONTR = Helper.set(respuesta.getZonaC().getZONAC2().getFechaContrato()); // "0"
				FECCONTR = Helper.dataTruncation(FECCONTR, 8);
				
				PORDESAU = null == respuesta.getZonaC().getZONAC3() ? "0" : Helper.set(respuesta.getZonaC().getZONAC3().getPorcenDesahucio());
				PORDESAU = Helper.dataTruncation(PORDESAU, 5);
				RENIMPON = null == respuesta.getZonaC().getZONAC3() ? "0" : Helper.set(respuesta.getZonaC().getZONAC3().getMontoImponibleMesAnterior());
				RENIMPON = Helper.dataTruncation(RENIMPON, 10);
				CODTIPSU = String.valueOf(respuesta.getZonaC().getZONAC2().getCodigoEntidadPagadora());
				CODTIPSU = Helper.dataTruncation(CODTIPSU, 1);
				

				rut = RUTEMPLE;
				RUTEMPLE = Helper.separaNumDigRut(rut, "N");
				DIGEMPLE = Helper.separaNumDigRut(rut, "D");
				Hashtable h = new Hashtable();
				h.put("ideope", lic.getIdeOpe());
				h.put("numLicencia", lic.getNumLicencia());
				h.put("AFIRUT", AFIRUT);
				h.put("ultimoEstado", String.valueOf(lic.getEstado()));
				h.put("RUTEMPLE", RUTEMPLE);
				h.put("DIGEMPLE", DIGEMPLE);
				h.put("NOMEMPLE", NOMEMPLE);
				h.put("APAEMPLE", APAEMPLE);
				h.put("AMAEMPLE", AMAEMPLE);
				h.put("TIPEMPLE", TIPEMPLE);
				h.put("DIREMPLE", DIREMPLE);
				h.put("COMEMPLE", COMEMPLE);
				h.put("GLOCOMEM", GLOCOMEM);
				h.put("FONOEMPL", FONOEMPL);
				h.put("EMAIEMPL", EMAIEMPL);
				h.put("FECREPEM", FECREPEM);
				h.put("FECENVEM", FECENVEM);
				h.put("CODCCAF", CODCCAF);
				h.put("CODCOMPI", CODCOMPI);
				h.put("CODACTLA", CODACTLA);
				h.put("CODOCUPA", CODOCUPA);
				h.put("GLOOTROC", GLOOTROC);
				h.put("CODREGPR", CODREGPR);
				h.put("PENSIONA", PENSIONA);
				h.put("CODINSPR", CODINSPR);
				h.put("LETCAJPR", LETCAJPR);
				h.put("CODCALTR", CODCALTR);
				h.put("AFC", AFC);
				h.put("NOMAFC", NOMAFC);
				h.put("CONINDEF", CONINDEF);
				h.put("CODESTAT", CODESTAT);
				h.put("FECAFILI", FECAFILI);
				h.put("FECCONTR", FECCONTR);
				h.put("PORDESAU", PORDESAU);
				h.put("RENIMPON", RENIMPON);
				h.put("CODTIPSU", CODTIPSU);
				h.put("MOTRECEM", MOTRECEM);

				// System.out.println(
				// "##################################################################################################################"
				// );
				//System.out.println("String valueIlfe004 = svc.insertIlfe004(h);");
				// System.out.println(
				// "##################################################################################################################"
				// );

				String valueIlfe004 = svc_a.insertIlfe004(h);
				if (!"OK".equals(valueIlfe004) || valueIlfe004.indexOf("SQL0803") != -1) {
					if (valueIlfe004.indexOf("SQL0803") != -1) {
						log.error("La licencia número [" + lic.getNumLicencia() + "] con estado [" + lic.getEstado() + "] falló al intentar registrar en ILFE004, PK");
					} else {
						log.error("La licencia número [" + lic.getNumLicencia() + "] con estado [" + lic.getEstado() + "] falló al intentar registrar en ILFE004, " + valueIlfe004);
					}
					// labelValueVO.setValue(valueIlfe004);
					// labelValueVO.setLabel("Ilfe004");
				} else {
					log.info("La licencia número [" + lic.getNumLicencia() + "] con estado [" + lic.getEstado() + "] se registró exitosamente en ILFE004");
				}
			}
			// ZONA C - RENTAS
			//----------------------------------------------------------------------
			// ----------

			CODINSPR = "0";
			LETCAJPR = "";
			String PERRENTA = "0";
			String NUMDIATR = "0";
			String REMMUNIM = "0";
			String IMPDESAH = "0";
			String IMPCESAN = "0";
			String SUEBASE = "0";
			String SUBDIAS = "0";
			String SUBMONTO = "0";
			String TIPLICEN = null == respuesta.getZonaA() || null == respuesta.getZonaA().getZONAA3() ? "0" : Helper.set(respuesta.getZonaA().getZONAA3().getCodigoTipoLicencia());
			TIPLICEN = Helper.dataTruncation(TIPLICEN, 2);
			LETCAJPR = null == respuesta.getZonaC() || null == respuesta.getZonaC().getZONAC2() ? "" : Helper.set(respuesta.getZonaC().getZONAC2().getCodigoLetraCaja());
			LETCAJPR = Helper.dataTruncation(LETCAJPR, 1);
			CODINSPR = "0";
			if (null != respuesta.getZonaC() && null != respuesta.getZonaC().getZONAC3()) {
				CTRemuneracion[] remuneracion = respuesta.getZonaC().getZONAC3().getRemuneracionArray();
				for (int i = 0; i < remuneracion.length; i++) {
					PERRENTA = Helper.setPeriodo(remuneracion[i].getAnoMesRemAnt()); // "1900-01"
					NUMDIATR = Helper.set(remuneracion[i].getNdiasRemAnt());
					NUMDIATR = Helper.dataTruncation(NUMDIATR, 2);
					REMMUNIM = Helper.set(remuneracion[i].getMontoImponibleRemAnt());
					REMMUNIM = Helper.dataTruncation(REMMUNIM, 10);
					SUEBASE = Helper.set(remuneracion[i].getMontoTotalRemAnt());
					SUEBASE = Helper.dataTruncation(SUEBASE, 10);
					SUBDIAS = Helper.set(remuneracion[i].getNdiasIncapacidadRemAnt());
					SUBDIAS = Helper.dataTruncation(SUBDIAS, 4);
					SUBMONTO = Helper.set(remuneracion[i].getMontoIncapacidadRemAnt());
					SUBMONTO = Helper.dataTruncation(SUBMONTO, 10);

					Hashtable h = new Hashtable();
					h.put("ideope", lic.getIdeOpe());
					h.put("numLicencia", lic.getNumLicencia());
					h.put("ultimoEstado", String.valueOf(lic.getEstado()));
					h.put("AFIRUT", AFIRUT);
					h.put("TIPLICEN", TIPLICEN);
					h.put("CODINSPR", CODINSPR);
					h.put("LETCAJPR", LETCAJPR);
					h.put("PERRENTA", PERRENTA);
					h.put("NUMDIATR", NUMDIATR);
					h.put("REMMUNIM", REMMUNIM);
					h.put("IMPDESAH", IMPDESAH);
					h.put("IMPCESAN", IMPCESAN);
					h.put("SUEBASE", SUEBASE);
					h.put("SUBDIAS", SUBDIAS);
					h.put("SUBMONTO", SUBMONTO);

					// System.out.println(
					// "##################################################################################################################"
					// );
					// System.out.println(
					// "String valueIlfe005 = svc.insertIlfe005(h);");
					// System.out.println(
					// "##################################################################################################################"
					// );

					String valueIlfe005 = svc_a.insertIlfe005(h);
					if (!"OK".equals(valueIlfe005) || valueIlfe005.indexOf("SQL0803") != -1) {
						if (valueIlfe005.indexOf("SQL0803") != -1) {
							log.error("La licencia número [" + lic.getNumLicencia() + "] con estado [" + lic.getEstado() + "] falló al intentar registrar en ILFE005, PK");
						} else {
							log.error("La licencia número [" + lic.getNumLicencia() + "] con estado [" + lic.getEstado() + "] falló al intentar registrar en ILFE005, " + valueIlfe005);
						}
						// labelValueVO.setValue(valueIlfe005);
						// labelValueVO.setLabel("Ilfe005");
					} else {
						log.info("La licencia número [" + lic.getNumLicencia() + "] con estado [" + lic.getEstado() + "] se registró exitosamente en ILFE005");
					}
				}
			}
			// ZONA C HABERES
			//----------------------------------------------------------------------
			// ----------
			String PERIODO = "0";
			String NOMHABER = "";
			String MONTO = "0";
			String URLARCHI = "";
			String STTIPARC = "";
			int agnoImagen = 1900;
			int mesImagen = 0;
			
			if (null != respuesta.getZonaC() && null != respuesta.getZonaC().getZONACC()) {
				// URL imagen
				String strMesImagen;
				CTDireccionArchivo[] archivo = respuesta.getZonaC().getZONACC().getHaberes().getArchivoArray();
				for (int i = 0; i < archivo.length; i++) {
					// Genera periodo imagen
					++mesImagen;
					if (mesImagen > 12) {
						++agnoImagen;
						mesImagen = 1;
					}

					if (mesImagen < 10)
						strMesImagen = "0" + mesImagen;
					else
						strMesImagen = String.valueOf(mesImagen);

					PERIODO = agnoImagen + "-" + strMesImagen;

					URLARCHI = archivo[i].getUrlArchivo().trim().replaceAll("&amp;", "&");
					STTIPARC = Helper.set(archivo[i].getTipoArchivo());
					STTIPARC = Helper.dataTruncation(STTIPARC, 5);

					Hashtable h = new Hashtable();
					h.put("AFIRUT", AFIRUT);
					h.put("RUTEMPLE", RUTEMPLE);
					h.put("PERIODO", PERIODO);
					h.put("URLARCHI", URLARCHI);
					h.put("STTIPARC", STTIPARC);

					// System.out.println(
					// "##################################################################################################################"
					// );
					// System.out.println(
					// "String valueIlfe006 = svc.insertIlfe006(h);");
					// System.out.println(
					// "##################################################################################################################"
					// );

					String valueIlfe006 = svc_a.insertIlfe006(h);
					if (!"OK".equals(valueIlfe006) || valueIlfe006.indexOf("SQL0803") != -1) {

						if (valueIlfe006.indexOf("SQL0803") != -1) {
							log.error("La licencia número [" + lic.getNumLicencia() + "] con estado [" + lic.getEstado() + "] falló al intentar registrar en ILFE006, PK");
						} else {
							log.error("La licencia número [" + lic.getNumLicencia() + "] con estado [" + lic.getEstado() + "] falló al intentar registrar en ILFE006, " + valueIlfe006);
						}
						// labelValueVO.setValue(valueIlfe006);
						// labelValueVO.setLabel("Ilfe006");
					} else {
						log.info("La licencia número [" + lic.getNumLicencia() + "] con estado [" + lic.getEstado() + "] se registró exitosamente en ILFE006");
					}
				}

				// Detalle Haberes
				CTDetalleHaber[] detalleHaberes = respuesta.getZonaC().getZONACC().getHaberes().getDetalleArray();
				for (int i = 0; i < detalleHaberes.length; i++) {

					PERIODO = Helper.setPeriodo(detalleHaberes[i].getAnoMesRenta());// "1900-01"
					NOMHABER = Helper.dataTruncation(Helper.set(detalleHaberes[i].getNombreHaber()), 40);
					MONTO = Helper.dataTruncation(Helper.set(detalleHaberes[i].getMontoHaber()), 12);
					//
					// cmd = "INSERT INTO ILFE007 (AFIRUT, EMPRUT, PERIODO,
					// NOMHABER, MONTO"
					// cmd += " ) VALUES ("
					// cmd += " ${AFIRUT}, ${RUTEMPLE}, '${PERIODO}', '${NOMHABER}',
					// ${MONTO})"
					//
					// listaCmd << cmd
					Hashtable h = new Hashtable();
					h.put("AFIRUT", AFIRUT);
					h.put("RUTEMPLE", RUTEMPLE);
					h.put("PERIODO", PERIODO);
					h.put("NOMHABER", NOMHABER);
					h.put("MONTO", MONTO);
					// System.out.println(
					// "##################################################################################################################"
					// );
					// System.out.println(
					// "String valueIlfe007 = svc.insertIlfe007(h);");
					// System.out.println(
					// "##################################################################################################################"
					// );

					String valueIlfe007 = svc_a.insertIlfe007(h);
					if (!"OK".equals(valueIlfe007) || valueIlfe007.indexOf("SQL0803") != -1) {
						if (valueIlfe007.indexOf("SQL0803") != -1) {
							log.error("La licencia número [" + lic.getNumLicencia() + "] con estado [" + lic.getEstado() + "] falló al intentar registrar en ILFE007, PK");
						} else {
							log.error("La licencia número [" + lic.getNumLicencia() + "] con estado [" + lic.getEstado() + "] falló al intentar registrar en ILFE007, " + valueIlfe007);
						}
						// labelValueVO.setValue(valueIlfe007);
						// labelValueVO.setLabel("Ilfe007");
					} else {
						log.info("La licencia número [" + lic.getNumLicencia() + "] con estado [" + lic.getEstado() + "] se registró exitosamente en ILFE007");
					}
				}
			}
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	private void logError(Throwable e) {
		log.error(e.getClass() + "; " + e.getMessage());
		// Date now = new Date();
		// svc.insertLog(new LmeLogVO("ERROR", proceso, "",sdf.format(now),
		// shf.format(now), dataTruncation(e.getClass() + "; "+
		// e.getMessage(),500)));
	}
	


}
