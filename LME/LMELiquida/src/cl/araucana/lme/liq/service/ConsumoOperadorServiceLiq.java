/**
 * 
 */
package cl.araucana.lme.liq.service;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import conector.configuracion.excepciones.ConfiguracionException;
import conector.lme.ws.cliente.operador.ServiciosMultiOperador;
import conector.lme.ws.cliente.operador.excepciones.ErrorInvocacionOperadorException;
import conector.vo.SalidaLMEInfSeccC;

import wwwLmeGovClLme.CTDireccion;
import wwwLmeGovClLme.CTHaberes;
import wwwLmeGovClLme.CTZONAC;
import wwwLmeGovClLme.CTZONAC1;
import wwwLmeGovClLme.CTZONAC2;
import wwwLmeGovClLme.CTZONAC3;
import wwwLmeGovClLme.CTZONAC4;
import wwwLmeGovClLme.CTZONACC;
import wwwLmeGovClLme.STCodigoComuna;
import wwwLmeGovClLme.STEntidadPagadora;

import cl.araucana.lme.liq.ibatis.domain.Ilfe031VO;
import cl.araucana.lme.liq.ibatis.domain.Ilfe033VO;
import cl.araucana.lme.liq.ibatis.domain.Ilfe034VO;
import cl.araucana.lme.liq.job.ManagerLME;
import cl.araucana.lme.liq.util.EndPointUtilLiq;
import cl.araucana.lme.liq.util.FechaUtil;
/**
 * @author usist199
 *
 */
public class ConsumoOperadorServiceLiq {
	private Logger log = Logger.getLogger(this.getClass());
	final BigInteger ZERO = new BigInteger("0");
	final String TIPO_INSTITUCION = "C";
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private SimpleDateFormat sdm = new SimpleDateFormat("yyyyMM");
	private boolean logDetail = false;
	private final String WS_UNAVAILABLE = "CCAF LA => Web Service no disponible";
	
	public boolean enviarZonaC(Ilfe031VO vo31, List vo33, List vo34) throws ParseException {

		Date now = new Date();
		CTZONAC zonaC = CTZONAC.Factory.newInstance();
		// ZonaC1
		// ----------------------------------------------------------------
		CTZONAC1 ctzonac1 = zonaC.addNewZONAC1();
		zonaC.getZONAC1().setCodigoActividadLaboral(toBigInteger(vo31.getCodigoActividadLaboral())); // C1COACLA
		zonaC.getZONAC1().setCodigoComunaCompin(toBigInteger(vo31.getCodigoComunaCompin()));// C1COCOCP
		zonaC.getZONAC1().setCodigoOcupacion(toBigInteger(vo31.getCodigoOcupacion())); // C1COOCUP

		ctzonac1.addNewEmpDireccion();
		zonaC.getZONAC1().getEmpDireccion().setCalle(vo31.getDireccionEmpleador());
		zonaC.getZONAC1().getEmpDireccion().setCiudad(vo31.getCiudadEmpleador());
		String comunaEmpresa = ((vo31.getComunaEmpleador() == null) || vo31.getDireccionEmpleador().length() == 0) ? "13101" : vo31.getComunaEmpleador();
		ctzonac1.getEmpDireccion().setComuna(STCodigoComuna.Enum.forString(comunaEmpresa));
		zonaC.getZONAC1().getEmpDireccion().setNumero(vo31.getNumeroDireccionEmpleador());
		zonaC.getZONAC1().getEmpDireccion().setDepto(vo31.getDeptoDireccionEmpleador());
		zonaC.getZONAC1().getEmpDireccion().setPais(vo31.getPaisEmpleador()); // C1EMPPAI
		zonaC.getZONAC1().setEmpFechaRecepcion(cal(String.valueOf(vo31.getFechaRecepcionEmpresa())));
		zonaC.getZONAC1().setEmpNombre(vo31.getNombreEmpleador().trim());
		zonaC.getZONAC1().setEmpOtraOcupacion(vo31.getOtraOcupacion());
		zonaC.getZONAC1().setEmpRut(vo31.getRutEmpleador() + "-" + vo31.getDvRutEmpleador());

		ctzonac1.addNewEmpTelefono();
		zonaC.getZONAC1().getEmpTelefono().setCodigoArea(ZERO);
		zonaC.getZONAC1().getEmpTelefono().setCodigoPais(ZERO);
		zonaC.getZONAC1().getEmpTelefono().setTelefono(toBigInteger(vo31.getTelefonoEmpleador()));

		// ZonaC2
		// ----------------------------------------------------------------
		CTZONAC2 ctzonac2 = zonaC.addNewZONAC2();

		zonaC.getZONAC2().setCodigoCalidadTrabajador(toBigInteger(vo31.getCodigoCalidadTrabajador()));
		ctzonac2.setCodigoEntidadPagadora(STEntidadPagadora.Enum.forString(vo31.getCodigoEntidadPagadora()));
		zonaC.getZONAC2().setCodigoLetraCaja(vo31.getCodigoLetraCaja());
		zonaC.getZONAC2().setCodigoRegimenPrevisional(toBigInteger(vo31.getCodigoEntidadPrevisonal()));
		zonaC.getZONAC2().setCodigoSeguroAfc(toBigInteger(vo31.getTrabajadorAfiliadoAFC()));
		zonaC.getZONAC2().setCodigoSeguroIndef(toBigInteger(vo31.getContratoDuracionIndefinido()));
		zonaC.getZONAC2().setCodigoTipoRegimenPrevisional(toBigInteger(vo31.getCodigoTipoRegimenPrevisional()));
		zonaC.getZONAC2().setFechaAfiliacion(cal(String.valueOf(vo31.getFechaAfiliacion())));
		zonaC.getZONAC2().setFechaContrato(cal(String.valueOf(vo31.getFechaContrato())));
		zonaC.getZONAC2().setPrevFechaRecepcionCcaf(cal(String.valueOf(vo31.getFechaRecepcionCaja())));
		zonaC.getZONAC2().setPrevNombre(vo31.getNombreEntidadPrevisional());
		zonaC.getZONAC2().setPrevNombrePagador(vo31.getNombreEntidadPagadora());

		// ZonaC3
		// ----------------------------------------------------------------

		try {
			if (null != vo33 && vo33.size() > 0) {
				CTZONAC3 ctzonac3 = zonaC.addNewZONAC3();
				int i = 0;
				for (Iterator iter = vo33.iterator(); iter.hasNext();) {
					Ilfe033VO o = (Ilfe033VO) iter.next();

					ctzonac3.addNewRemuneracion();
					if (i == 0) {
						zonaC.getZONAC3().setMontoImponibleMesAnterior(toBigInteger(vo31.getRentaImponible()));
						zonaC.getZONAC3().setPorcenDesahucio(toBigDecimal(vo31.getPorcentajeDesahucio()));
					}
					/*
					 * Línea modificada 29-07-2014 dado que dato en formulario
					 * venia en formato incorrecto. original ->
					 * zonaC.getZONAC3()
					 * .getRemuneracionArray()[i].setAnoMesRemAnt
					 * (cal(o.getAnoMesRemAnt()));
					 */
					zonaC.getZONAC3().getRemuneracionArray()[i].setAnoMesRemAnt(cal2(String.valueOf(o.getPeriodoRenta())));
					zonaC.getZONAC3().getRemuneracionArray()[i].setCodigoPrevisionRemAnt(toBigInteger(o.getCodigoEntidadPrevisional()));
					zonaC.getZONAC3().getRemuneracionArray()[i].setMontoImponibleRemAnt(toBigInteger(o.getTotalRemuneraciones()));
					zonaC.getZONAC3().getRemuneracionArray()[i].setMontoIncapacidadRemAnt(toBigInteger(o.getMontoSubsidio()));
					zonaC.getZONAC3().getRemuneracionArray()[i].setMontoTotalRemAnt(toBigInteger(o.getRemuneracionMesAnterior()));
					zonaC.getZONAC3().getRemuneracionArray()[i].setNdiasIncapacidadRemAnt(toBigInteger(o.getNumeroDiasSubsidio()));
					zonaC.getZONAC3().getRemuneracionArray()[i].setNdiasRemAnt(toBigInteger(o.getNumeroDiasRemuneracion()));

					++i;

				}
			} else {
				log.info("No se encontraron licencias para envio de ZonaC3 ");
			}
		} catch (Exception e) {
			log.error(e);
		}

		// ZonaC4
		// ----------------------------------------------------------------

		try {
			CTZONAC4 ctzonac4 = zonaC.addNewZONAC4();
			String lmaLicenciasAnt = "2";
			String auxlmaLicenciasAnt = null == vo31.getLicenciasAnteriores() ? "NO" : vo31.getLicenciasAnteriores();
			
			if (auxlmaLicenciasAnt.equalsIgnoreCase("SI"))
				lmaLicenciasAnt = "1";

			if (lmaLicenciasAnt.equalsIgnoreCase("1")) {
			
				if (null != vo34 && vo34.size() > 0) {
					int j = 0;
					for (Iterator iter = vo34.iterator(); iter.hasNext() && j < 6;) {
						Ilfe034VO o = (Ilfe034VO) iter.next();
						ctzonac4.addNewLicenciaAnterior();
						zonaC.getZONAC4().getLicenciaAnteriorArray()[j].setLmaFechaDesde(cal(String.valueOf(o.getFechaDesde())));
						zonaC.getZONAC4().getLicenciaAnteriorArray()[j].setLmaFechaHasta(cal(String.valueOf(o.getFechaHasta())));
						zonaC.getZONAC4().getLicenciaAnteriorArray()[j].setLmaNdias(toBigInteger(o.getNumeroDias()));

						++j;
					}
				} else {
					lmaLicenciasAnt = "2";
				}
			}
			zonaC.getZONAC4().setLmaLicenciasAnt(toBigInteger(lmaLicenciasAnt));
		} catch (Exception e1) {
			log.error(e1);
		}

		// ZonaCC
		// ----------------------------------------------------------------
		CTZONACC ctzonacc = zonaC.addNewZONACC();

		zonaC.getZONACC().setCodigoTramitacionCCAF(toBigInteger(vo31.getCodigoTramitacionCCAF()));
		zonaC.getZONACC().setTieneMas100(toBigInteger(vo31.getTieneMas100()));

		CTHaberes ctHaberes = ctzonacc.addNewHaberes();
		ctHaberes.addNewArchivo();
		zonaC.getZONACC().getHaberes().getArchivoArray()[0].setTipoArchivo(new BigInteger("4"));
		zonaC.getZONACC().getHaberes().getArchivoArray()[0].setUrlArchivo("http://");

		ctHaberes.addNewDetalle();
		zonaC.getZONACC().getHaberes().getDetalleArray()[0].setAnoMesRenta(cal("19000101"));
		zonaC.getZONACC().getHaberes().getDetalleArray()[0].setMontoHaber(ZERO);
		zonaC.getZONACC().getHaberes().getDetalleArray()[0].setNombreHaber("No aplica");

		// ZONACF
		zonaC.addNewZONACF();
		// String urlOpe = vo.getUrlOpe();
		// UrlBorderVO urlVO = new UrlBorderVO();
		// urlVO.setCodOpe(vo.getCodOpe());
		// urlVO.setNombreServicio("ZONA C");
		String nombreHash = "ZONA_C";
		// ZONACF.FIRMA
		zonaC.getZONACF().addNewFirma();
		zonaC.getZONACF().getFirmaArray()[0].setDescripcion("Documento firmado en papel");
		try {
			/*
			 * String url = svc.getUrlVordel(urlVO); if(null==url){
			 * //log("IGNORANDO SERVICIO"); return; } urlOpe = url;
			 */
			/*********************************************/
			// guarda la url que no tenga error segun la prioridad
			EndPointUtilLiq instanciaEndPoint = EndPointUtilLiq.getInstance();
			String urlUtilizada = "";
			long tiempoUtilizado = 0;
			String url1 = "";
			Boolean error1 = instanciaEndPoint.getEstadoError(vo31.getCodOpe(), "1");
			if (error1 != null && error1 == Boolean.FALSE) {
				// System.out.println("No tiene error en la prioridad 1");
				url1 = instanciaEndPoint.getEndPoint(vo31.getCodOpe(), nombreHash, "1");
				log.info("Url 1 Operador= " + url1);
			}

			String url2 = "";
			Boolean error2 = instanciaEndPoint.getEstadoError(vo31.getCodOpe(), "2");
			if (error2 != null && error2 == Boolean.FALSE) {
				// System.out.println("No tiene error en la prioridad 2");
				url2 = instanciaEndPoint.getEndPoint(vo31.getCodOpe(), nombreHash, "2");
				log.info("Url 2 Operador= " + url2);
			}

			/************************************************/
			String tipoInstitucion = "C";
			Date fechaProceso = new Date();

			if (logDetail) {
				log.info("Licencia:" + vo31.getNroLicencia() + "\n" + zonaC.toString().replaceAll("urn:www:lme:gov:cl:lme", "").replaceAll("xmlns=\"\"", "").replaceAll("urn:", "lme:"));
			}

			// ServiciosMultiOperador servicio = new
			// ServiciosMultiOperador(vo.getCodOpe().trim(), tipoInstitucion,
			// vo.getCodCcaf().trim(), vo.getPwdCcaf().trim(), urlOpe);
			ServiciosMultiOperador servicio = new ServiciosMultiOperador(vo31.getCodOpe().trim(), tipoInstitucion, vo31.getCodCcaf().trim(), vo31.getPwdCcaf().trim(), url1, url2);
			// servicio.validar("LMEInfSeccC ZonaC-v2:", zonaC);
			// RespuestaServicio respuesta = null;//
			// servicio.informaRentaLicencia(vo.getNumImpre().intValue(),
			// digLicencia, fechaProceso, zonaC);
			// ejecuta el nuevo servicio que maneja el timeout y utiliza la dos
			// url enviadas
			SalidaLMEInfSeccC respuesta = null;//servicio.informaRentaLicencia(vo
												// .getNumImpre().intValue(),
												// digLicencia, fechaProceso,
												// zonaC);

			try {
				// respuesta =
				// servicio.informaRentaLicencia(vo.getNumImpre().intValue(),
				// digLicencia, fechaProceso, zonaC);
				// ejecuta el nuevo servicio que maneja el timeout y utiliza la
				// dos url enviadas
				respuesta = servicio.informaRentaLicencia2(vo31.getNroLicencia(), vo31.getDvNroLicencia(), fechaProceso, zonaC);

				/*******************************************************/
				// se asignan errores de timeout segun la respuesta del servico
				// y se guarda el tiempo de demoro del servicio que funciona
				// correctamente
				if (respuesta.isError1()) {

					if (instanciaEndPoint.getEstadoError(vo31.getCodOpe(), "1") == Boolean.FALSE) {
						instanciaEndPoint.cambiarEstadoError(vo31.getCodOpe(), "1", Boolean.TRUE);
						log.info("Problemas por TimeOut con la licencia " + vo31.getNroLicencia() + " al tratar de ejecutar servicio " + url1 + " (Vordel) en el operador " + vo31.getCodOpe());
					}
					if (respuesta.isError2()) {
						instanciaEndPoint.cambiarEstadoError(vo31.getCodOpe(), "2", Boolean.TRUE);
						log.info("Problemas por TimeOut con la licencia " + vo31.getNroLicencia() + " al tratar de ejecutar servicio " + url2 + " (" + instanciaEndPoint.getNombreOperador(vo31.getCodOpe()) + ") en el operador " + vo31.getCodOpe());
						// System.out.println(
						// "Problemas por TimeOut con la licencia "
						// +vo.getNumImpre
						// ()+" al tratar de ejecutar servicio "+url2
						// +" en el operador "+vo.getCodOpe());
					} else {
						urlUtilizada = url2;
						tiempoUtilizado = respuesta.getTiempo2();
					}
				} else {
					urlUtilizada = url1;
					tiempoUtilizado = respuesta.getTiempo1();
				}

				if (respuesta.isError1() && respuesta.isError2()) {
					instanciaEndPoint.cambiarEstadoError(vo31.getCodOpe(), Boolean.TRUE);
					return false;
				}
				/******************************************************/
			} catch (ConfiguracionException e) {
				log.error(e);
			} catch (ErrorInvocacionOperadorException e) {
				log.error(e);
			}
			String enviada = "0";
			String respWs = "1";
			String glosaEstado = WS_UNAVAILABLE;
			if (null != respuesta && respuesta.getRespuesta() != null) {
				enviada = "1";
				respWs = respuesta.getRespuesta().getEstado() != 0 ? "1" : "0";
				glosaEstado = respuesta.getRespuesta().getGloEstado();
			}

			log.info("[" + urlUtilizada + "] - Licencia:" + vo31.getNroLicencia() + "  Estado:" + glosaEstado + "  DEMORO:" + String.valueOf(tiempoUtilizado) + " Milisegundos");
			glosaEstado = reformateaGlosaEstado(glosaEstado);

			// listaResult.add(
			// "numLicencia: ${numLicencia}, codEstado: ${respuesta.estado}, glosaEstado: ${respuesta.gloEstado}"
			// );
			//Ilfe031VO newVO = new Ilfe031VO();
			vo31.setRespuestaWS(respWs);// String.valueOf(respuesta.getEstado())
			vo31.setGlosaRespuesta(glosaEstado);
			vo31.setFechaRespuesta(Integer.parseInt(FechaUtil.getFechaHoyAs400()));// yyyyMMdd
			vo31.setHoraRespuesta(Integer.parseInt(FechaUtil.getHoraAs400()));
			vo31.setEnviada(enviada);// respuesta.getEstado()==0?"1":"0"
			
			// SET ENVIADA = #enviada#, RESPWS = #respWs#, GLORESP =
			// #glosaEstado#,
			// FECRESP = #fechaConsulta#, HORRESP = #hora#
			// WHERE IDEOPE = #ideOpe# AND NUMIMPRE = #numImpre#
			ManagerLME manager= new ManagerLME();
			boolean exito= manager.updateIlfe031(vo31);
			
			if(exito && glosaEstado.trim().equalsIgnoreCase("OK")){
			//if(exito){
				return true;
			}
			// listaResult.add("update: ${resUpdate}");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			log.error(e.getClass() + "; " + e.getMessage());
		}
		return false;
	}// end enviarZonaC
	
	private BigInteger toBigInteger(Integer value) {
		return null == value ? ZERO : new BigInteger(value.toString());
	}

	private BigInteger toBigInteger(String value) {
		return null == value || "".equals(value.trim()) ? ZERO : new BigInteger(value);
	}

	private BigInteger toBigInteger(int value) {
		return new BigInteger(String.valueOf(value));
	}

	private BigDecimal toBigDecimal(int value) {
		return new BigDecimal(value);
	}
	
	private BigInteger toBigInteger(BigDecimal value) {
		return null == value ? ZERO : new BigInteger(value.toString());
	}
	
	private CTDireccion reformateaDireccionEmpresa(String direccion) {
		CTDireccion auxDirec = CTZONAC.Factory.newInstance().addNewZONAC1().addNewEmpDireccion();
		String temp[] = new String[3];
		int largo = 0;
		int vc = 0;
		boolean key = false;
		//System.out.println("* * * * * reformateaDireccionEmpresa * * * * * ");
		if (direccion != null && direccion != "") {
			// System.out.println("* * * * * NOT NULL * * * * * ");
			try {
				largo = direccion.length();
				// System.out.println("* * * * * LARGO: [" + largo +
				// "] * * * * * ");
				if (largo < 100) {
					for (vc = 0; vc < (100 - largo); vc++) {
						direccion += " ";
					}
					largo = direccion.length();
				}
				// System.out.println("* * * * * LARGO_2: [" + largo +
				// "] * * * * * ");
				/*
				 * - calle --> ILFE031.C1EMPDIR(1 a 80)- numero -->
				 * ILFE031.C1EMPDIR(81 a 90)- depto --> ILFE031.C1EMPDIR(91 a
				 * 100)
				 */

				temp[0] = direccion.substring(0, 79);// calle (1-80)
				temp[1] = direccion.substring(80, 89);// numero (81-90)
				temp[2] = direccion.substring(90, 99);// depto (91-100)

				// System.out.println("* * * * * TEMP[0](calle): [" + temp[0] +
				// "] * * * * * ");
				// System.out.println("* * * * * TEMP[1](numero): [" + temp[1] +
				// "] * * * * * ");
				// System.out.println("* * * * * TEMP[2](depto): [" + temp[2] +
				// "] * * * * * ");

				auxDirec.setCalle(temp[0].trim());// calle (1-80)
				auxDirec.setNumero(temp[1].trim());// numero (81-90)
				auxDirec.setDepto(temp[2].trim());// depto (91-100)
			} catch (Exception ex) {
				// System.out.println("* * * * * CATCH: [" + ex +
				// "] * * * * * ");
				key = true;
			}
		} else {
			// System.out.println("* * * * * ELSE NULL * * * * * ");
			key = true;
		}

		if (key) {
			// System.out.println("* * * * * [IF blancos] * * * * * ");
			auxDirec.setCalle("");
			auxDirec.setDepto("");
			auxDirec.setNumero(ZERO.toString());
		}

		// System.out.println(
		// "* * * * * [RETURN: reformateaDireccionEmpresa] * * * * * ");
		return auxDirec;
	}// END reformateaDireccionEmpresa

	private String reformateaGlosaEstado(String glosaEstado) {
		if (null != glosaEstado && !glosaEstado.equals("")) {
			glosaEstado = glosaEstado.replace('\'', ' ');
			if (glosaEstado.length() > 60)
				glosaEstado = glosaEstado.substring(0, 60);
		} else {
			glosaEstado = "Operador no informa la glosa";
		}

		return glosaEstado;
	}
	
	private Calendar cal(String date) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		if (date.equals("190001"))
			date += "01";
		try {
			calendar.setTime(sdf.parse(date));
		} catch (ParseException e) {
			log.error(e);
		}
		return calendar;
	}
	
	private Calendar cal2(String date) throws ParseException {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		try {

			calendar.setTime(sdm.parse(date));
		} catch (ParseException e) {
			log.error(e);
		}
		// System.out.println("Calendar2 = " + calendar);
		return calendar;
	}
	
	public static String dv(int num) {
		int M = 0, S = 1, T = num;
		for (; T != 0; T /= 10)
			S = (S + T % 10 * (9 - M++ % 6)) % 11;
		char r = (char) (S != 0 ? S + 47 : 75);
		return String.valueOf(r);
	}
}
