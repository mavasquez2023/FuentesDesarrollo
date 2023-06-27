/*
 * Created on 11-10-2011
 *
 */
package com.microsystem.lme.job;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
//import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.apache.struts.util.MessageResources;

import wwwLmeGovClLme.CTDetalleHaber;
import wwwLmeGovClLme.CTDireccion;
import wwwLmeGovClLme.CTDireccionArchivo;
import wwwLmeGovClLme.CTEstado;
import wwwLmeGovClLme.CTHaberes;
import wwwLmeGovClLme.CTLugarReposo;
import wwwLmeGovClLme.CTRemuneracion;
import wwwLmeGovClLme.CTResolucion;
import wwwLmeGovClLme.CTTelefono;
import wwwLmeGovClLme.CTZONAC;
import wwwLmeGovClLme.CTZONAC1;
import wwwLmeGovClLme.CTZONAC2;
import wwwLmeGovClLme.CTZONAC3;
import wwwLmeGovClLme.CTZONAC4;
import wwwLmeGovClLme.CTZONACC;
import wwwLmeGovClLme.CTZONAD;
import wwwLmeGovClLme.CTZONAD1;
import wwwLmeGovClLme.STCodigoComuna;
import wwwLmeGovClLme.STEntidadPagadora;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.CommandCall;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.ObjectDoesNotExistException;
import com.ibm.as400.access.ProgramCall;
import com.microsystem.lme.cobol.bo.ParametrosConexionBO;
import com.microsystem.lme.cobol.bo.ParametrosLlamadaBO;
import com.microsystem.lme.cobol.config.ConsumidorCobol;
import com.microsystem.lme.helper.Helper;
import com.microsystem.lme.ibatis.domain.Ilf1000VO;
import com.microsystem.lme.ibatis.domain.Ilfe000VO;
import com.microsystem.lme.ibatis.domain.Ilfe002InversoVO;
import com.microsystem.lme.ibatis.domain.Ilfe002VO;
import com.microsystem.lme.ibatis.domain.Ilfe011VO;
import com.microsystem.lme.ibatis.domain.Ilfe013VO;
import com.microsystem.lme.ibatis.domain.Ilfe021VO;
import com.microsystem.lme.ibatis.domain.Ilfe031VO;
import com.microsystem.lme.ibatis.domain.Ilfe033VO;
import com.microsystem.lme.ibatis.domain.Ilfe034VO;
import com.microsystem.lme.ibatis.domain.Ilfe051RVO;
import com.microsystem.lme.ibatis.domain.Ilfe051VO;
import com.microsystem.lme.ibatis.domain.Ilfe080VO;
import com.microsystem.lme.ibatis.domain.Ilfe081VO;
import com.microsystem.lme.ibatis.domain.Ilfe082VO;
import com.microsystem.lme.ibatis.domain.IlfeOpeVO;
import com.microsystem.lme.ibatis.domain.UrlBorderVO;
import com.microsystem.lme.svc.IAS400Svc_LME;
import com.microsystem.lme.svc.IAS400Svc_SIL;
import com.microsystem.lme.svc.SvcFactory_LME;
import com.microsystem.lme.svc.SvcFactory_SIL;
import com.microsystem.lme.svc.exception.SvcException;
import com.microsystem.lme.util.BdConstants;
import com.microsystem.lme.util.Configuraciones;
import com.microsystem.lme.util.Constants;
import com.microsystem.lme.util.EndPointUtil;
import com.microsystem.lme.util.LabelValueVO;
import com.microsystem.lme.util.Util;

import conector.configuracion.excepciones.ConfiguracionException;
import conector.lme.ws.cliente.operador.LicenciaSimpleType;
import conector.lme.ws.cliente.operador.LicenciaType;
import conector.lme.ws.cliente.operador.RespuestaDetalleLicencia;
import conector.lme.ws.cliente.operador.ResultadoType;
import conector.lme.ws.cliente.operador.ServiciosMultiOperador;
import conector.lme.ws.cliente.operador.excepciones.ErrorInvocacionOperadorException;
import conector.lme.ws.cliente.operador.excepciones.ErrorRespuestaOperadorException;
import conector.vo.SalidaLMEDetLcc;
import conector.vo.SalidaLMEDevEmp;
import conector.vo.SalidaLMEEvenLcc;
import conector.vo.SalidaLMEInfLiquid;
import conector.vo.SalidaLMEInfSeccC;
import conector.vo.SalidaLMEInfValCCAF;

/**
 * @author microsystem
 * 
 */
public class ConsumoOperadorService {

	// private LoggerHelper logger = new LoggerHelper();
	private Logger log = Logger.getLogger(this.getClass());

	final BigInteger ZERO = new BigInteger("0");

	final String TIPO_INSTITUCION = "C";

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private SimpleDateFormat sdm = new SimpleDateFormat("yyyyMM");
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");

	private SimpleDateFormat shf = new SimpleDateFormat("HHmmss");

	// private IAS400Svc svc = null;

	private IAS400Svc_LME svc_a = null;
	private IAS400Svc_SIL svc_b = null;

	private String host = null;

	private String user = null;

	private String pass = null;

	private String path = null;

	/* 20121121 */
	private String cobol = null;

	private String version = null;

	private String dirIntra = null;

	private String operador = "";

	private String proceso = "";

	private boolean logDetail = false;

	private boolean reinject = false;

	private Date dateLcc = null;
	private Date dateFec = null;

	private StringBuffer logLcc = new StringBuffer();

	private List lmeList = null;

	private LabelValueVO labelValueVO = new LabelValueVO();

	public ConsumoOperadorService(MessageResources m) {
		if ("yes".equals(m.getMessage("http.proxy"))) {
			System.getProperties().put("http.proxyHost", m.getMessage("http.proxyHost"));
			System.getProperties().put("http.proxyPort", m.getMessage("http.proxyPort"));
		}
		host = m.getMessage("host");
		user = m.getMessage("user");
		pass = m.getMessage("pass");
		path = m.getMessage("path");
		/* 20130114 */
		cobol = m.getMessage("cobol");

		version = m.getMessage("version");
		dirIntra = m.getMessage("imgDir");
		logDetail = Boolean.valueOf(m.getMessage("logDetail")).booleanValue();
		reinject = Boolean.valueOf(m.getMessage("reinject")).booleanValue();

		/*
		 * commandAS400("CLRPFM FILE(LIEXP/ILFE002)");
		 * commandAS400("CLRPFM FILE(LIEXP/ILFE003)");
		 * commandAS400("CLRPFM FILE(LIEXP/ILFE004)");
		 * commandAS400("CLRPFM FILE(LIEXP/ILFE005)");
		 * commandAS400("CLRPFM FILE(LIEXP/ILFE006)");
		 * commandAS400("CLRPFM FILE(LIEXP/ILFE007)");
		 * commandAS400("CLRPFM FILE(LIEXP/ILFE008)");
		 * commandAS400("CLRPFM FILE(LIEXP/ILFE009)");
		 */

		svc_a = SvcFactory_LME.getAS400Svc_LME();
		svc_b = SvcFactory_SIL.getAS400Svc_SIL();
		// Delete
		svc_a.deleteIlfe002();
		svc_a.deleteIlfe003();
		svc_a.deleteIlfe004();
		svc_a.deleteIlfe005();
		svc_a.deleteIlfe006();
		svc_a.deleteIlfe007();
		svc_a.deleteIlfe008();
		svc_a.deleteIlfe009();

	}

	public void limpiaTablas() {
		svc_a.deleteIlfe002();
		svc_a.deleteIlfe003();
		svc_a.deleteIlfe004();
		svc_a.deleteIlfe005();
		svc_a.deleteIlfe006();
		svc_a.deleteIlfe007();
		svc_a.deleteIlfe008();
		svc_a.deleteIlfe009();
	}

	public void testConexion() throws SvcException {
		/*
		 * SvcFactory.setSvcFactory(null); svc = SvcFactory.getAS400Svc();
		 */
		List l = svc_b.getLicenciasMixtas();

		for (Iterator iter = l.iterator(); iter.hasNext();) {

			IlfeOpeVO o = (IlfeOpeVO) iter.next();
			System.out.println(o);
		}
	}

	public void lmeMixtas() {
		proceso = "1";

		//
		log.info("INICIO LME		AS400[" + host + "]" + " version[" + version + "]");
		log.info("---------------------------------------------------------------------------------------------------------------------------");
		log.info("Detalle Log:" + logDetail);
		log.info("Reinyeccion:" + reinject);
		//
		startProcess("LmeMixtas");
		String dataInf = "";

		try {
			List l = svc_b.getLicenciasMixtas();
			if (!empty(l)) {
				dataInf = "Licencias Mixtas[" + l.size() + "]";

				// auxiliar distinto a un codigo de operador
				int aux = -999;
				for (Iterator iter = l.iterator(); iter.hasNext();) {

					IlfeOpeVO o = (IlfeOpeVO) iter.next();

					/** ************************************** */
					/*
					 * la primera vez pasa correctamente el codigo de operador y
					 * para las otras opciones se valida si el operador tenia
					 * errores y se salta al siguiente ciclo del for
					 */

					int codOpeInt = Integer.parseInt(o.getCodOpe());

					if (aux == codOpeInt) {
						Boolean errorTotal;
						try {
							errorTotal = EndPointUtil.getInstance().getEstadoError(o.getCodOpe());
							if (errorTotal != null && errorTotal == Boolean.TRUE) {
								// System.out.println("tiene error en los dos
								// endpoint");
								continue;
							}
						} catch (Exception e2) {
							// e2.printStackTrace();
							log.error(e2.getClass() + "; " + e2.getMessage());
						}
					} else {
						aux = codOpeInt;
					}

					/** ************************************** */

					log.info(":::LmeMixtas	ILFE002R	CODOPE[" + o.getCodOpe() + "] NUMIMPRE[" + o.getNumLicencia() + "]");
					o.setNumImpre(o.getNumLicencia());
					// o.setDigLicencia(dv(null==o.getNumLicencia()?"0":o.
					// getNumLicencia()));
					operador = o.getCodOpe();
					// validar errores
					LabelValueVO lValue=null;
					try {
						lValue = consumirDetalleLME(o, "MIXTA");
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (lValue==null || lValue.getValue().equals("-1")) {// ERROR
						Map h = new Hashtable();
						h.put("ideOpe", o.getIdeOpe());
						h.put("numImpre", o.getNumImpre());
						if (lValue!=null){
							h.put("label", lValue.getLabel());
						}else{
							h.put("label", "Posible comuna 16");
						}
						svc_b.updateIlfe002RError(h);
					} else if (lValue.getValue().equals("0")) {// licencia no
						// encontrada
						// log("ENVIA MAIL........");
					} else {// Licencia existe (flag=1) o no corresponde a la Caja (flag=-2) => update
						// UPDATE ILFE002R SET CODERR = 0 WHERE IDEOPE = [idope]
						// AND NUMIMRPE = [numimpre ]
						Map h = new Hashtable();
						h.put("ideOpe", o.getIdeOpe());
						h.put("numImpre", o.getNumImpre());
						h.put("label", lValue.getLabel());
						svc_b.updateIlfe002R(h);
					}
				}
			} else {
				dataInf = "No se encontraron licencias mixtas";
			}
		} catch (Throwable e) {// SvcException
			logError(e);
		}
		operador = "";
		endProcess("LmeMixtas", dataInf);
	}

	public void enviarZonasC() {
		proceso = "2";
		startProcess("EnviarZonasC");
		String dataInf = "";
		// Cambio ALMESOP-3 12-03-2012
		Ilfe031VO ilfe031VO = new Ilfe031VO();
		ilfe031VO.setEnviada("0");
		//
		try {
			List l = svc_a.getIlfe031(ilfe031VO);
			// auxiliar distinto a un codigo de operador
			int aux = -999;
			if (null != l && l.size() > 0) {
				for (Iterator iter = l.iterator(); iter.hasNext();) {

					Ilfe031VO vo = (Ilfe031VO) iter.next();

					/** ************************************** */
					/*
					 * la primera vez pasa correctamente el codigo de operador y
					 * para las otras opciones se valida si el operador tenia
					 * errores y se salta al siguiente ciclo del for
					 */
					int codOpeInt = Integer.parseInt(vo.getCodOpe());
					if (aux == codOpeInt) {
						Boolean errorTotal;
						try {
							errorTotal = EndPointUtil.getInstance().getEstadoError(vo.getCodOpe());
							if (errorTotal != null && errorTotal == Boolean.TRUE) {
								// System.out.println("tiene error en los dos
								// endpoint");
								continue;
							}
						} catch (Exception e2) {
							// e2.printStackTrace();
							log.error(e2.getClass() + "; " + e2.getMessage());
						}
					} else {
						aux = codOpeInt;
					}

					/** ************************************** */

					operador = vo.getCodOpe();
					enviarZonaC(vo);
				}
			} else {
				dataInf = "No se encontraron licencias para envio de Zona C";
			}

		} catch (Throwable e) {// SvcException
			logError(e);
		}
		operador = "";
		endProcess("EnviarZonasC", dataInf);

	}

	public void enviarZonaC(Ilfe031VO vo) throws ParseException {

		Date now = new Date();
		CTZONAC zonaC = CTZONAC.Factory.newInstance();
		// ZonaC1
		// ----------------------------------------------------------------
		CTZONAC1 ctzonac1 = zonaC.addNewZONAC1();
		zonaC.getZONAC1().setCodigoActividadLaboral(toBigInteger(vo.getCodigoActividadLaboral())); // C1COACLA
		zonaC.getZONAC1().setCodigoComunaCompin(toBigInteger(vo.getCodigoComunaCompin()));// C1COCOCP
		zonaC.getZONAC1().setCodigoOcupacion(toBigInteger(vo.getCodigoOcupacion())); // C1COOCUP

		ctzonac1.addNewEmpDireccion();
		/*
		 * IBM RAC-7021 modificacion ZONA_C /29-07-2014]
		 */
		CTDireccion auxDirec = this.reformateaDireccionEmpresa(vo.getEmpDireccionCalle());
		/*
		 * Línea modificada 24-03-2014 dado que dato en formulario venia
		 * encriptado. original ->
		 * zonaC.getZONAC1().getEmpDireccion().setCalle(Helper
		 * .encodeBase64(vo.getEmpDireccionCalle().trim().getBytes()));
		 */
		// zonaC.getZONAC1().getEmpDireccion().setCalle(Helper.encodeBase64(vo.
		// getEmpDireccionCalle().trim().getBytes()));
		zonaC.getZONAC1().getEmpDireccion().setCalle(auxDirec.getCalle());
		zonaC.getZONAC1().getEmpDireccion().setCiudad(vo.getEmpDireccionCiudad());

		/*
		 * Modificación 24-04-2014, se agrega el código 13101 si el dato viene
		 * en blanco o nulo.
		 */
		String comunaEmpresa = ((vo.getEmpDireccionComuna() == null) || vo.getEmpDireccionComuna().length() == 0) ? "13101" : vo.getEmpDireccionComuna();
		// System.out.println("Comuna origen = " + vo.getEmpDireccionComuna());
		// System.out.println("Comuna de Empresa = " + comunaEmpresa);
		ctzonac1.getEmpDireccion().setComuna(STCodigoComuna.Enum.forString(comunaEmpresa));
		//ctzonac1.getEmpDireccion().setComuna(STCodigoComuna.Enum.forString(vo.
		// getEmpDireccionComuna()));
		/* Fin modificación. */

		// zonaC.getZONAC1().getEmpDireccion().setDepto(""); //TODO validar
		// mappping empDireccionDepto
		zonaC.getZONAC1().getEmpDireccion().setDepto(auxDirec.getDepto()); // TODO
																			// validar
																			// mappping
																			// empDireccionDepto

		// zonaC.getZONAC1().getEmpDireccion().setNumero(ZERO.toString());//TODO
		// validar mappping empDireccionNumero
		zonaC.getZONAC1().getEmpDireccion().setNumero(auxDirec.getNumero());// TODO
																			// validar
																			// mappping
																			// empDireccionNumero

		zonaC.getZONAC1().getEmpDireccion().setPais(vo.getEmpDireccionPais()); // C1EMPPAI
		zonaC.getZONAC1().setEmpFechaRecepcion(cal(vo.getEmpFechaRecepcion()));
		/*
		 * Línea modificada 24-03-2014 dado que dato en formulario venia
		 * encriptado. original ->
		 * zonaC.getZONAC1().setEmpNombre(Helper.encodeBase64
		 * (vo.getEmpNombre().trim().getBytes()));
		 */
		zonaC.getZONAC1().setEmpNombre(vo.getEmpNombre().trim());

		// System.out.println("EmpNombre = " + vo.getEmpNombre().trim());

		zonaC.getZONAC1().setEmpOtraOcupacion(vo.getEmpOtraOcupacion());
		String empRut = vo.getC1empRut() + "-" + vo.getC1emprutDv();
		zonaC.getZONAC1().setEmpRut(empRut);

		ctzonac1.addNewEmpTelefono();
		zonaC.getZONAC1().getEmpTelefono().setCodigoArea(ZERO);// TODO validar
																// mappping
																// empTelefonoCodigoArea
		zonaC.getZONAC1().getEmpTelefono().setCodigoPais(ZERO);// TODO validar
																// mappping
																// empTelefonoCodigoPais
		zonaC.getZONAC1().getEmpTelefono().setTelefono(ZERO);// TODO validar
																// mappping
																// empTelefonoTelefono

		// ZonaC2
		// ----------------------------------------------------------------
		CTZONAC2 ctzonac2 = zonaC.addNewZONAC2();

		zonaC.getZONAC2().setCodigoCalidadTrabajador(toBigInteger(vo.getCodigoCalidadTrabajador()));
		ctzonac2.setCodigoEntidadPagadora(STEntidadPagadora.Enum.forString(vo.getCodigoEntidadPagadora()));
		//zonaC.getZONAC2().setCodigoLetraCaja(toString(vo.getCodigoLetraCaja()));
		zonaC.getZONAC2().setCodigoRegimenPrevisional(toBigInteger(vo.getCodigoRegimenPrevisional()));
		zonaC.getZONAC2().setCodigoSeguroAfc(toBigInteger(vo.getCodigoSeguroAfc()));
		zonaC.getZONAC2().setCodigoSeguroIndef(toBigInteger(vo.getCodigoSeguroIndef()));
		zonaC.getZONAC2().setCodigoTipoRegimenPrevisional(toBigInteger(vo.getCodigoTipoRegimenPrevisional()));
		zonaC.getZONAC2().setFechaAfiliacion(cal(vo.getFechaAfiliacion()));
		zonaC.getZONAC2().setFechaContrato(cal(vo.getFechaContrato()));
		zonaC.getZONAC2().setPrevFechaRecepcionCcaf(cal(vo.getPrevFechaRecepcionCcaf().toString()));
		zonaC.getZONAC2().setPrevNombre(vo.getPrevNombre());
		zonaC.getZONAC2().setPrevNombrePagador(vo.getPrevNombrePagador());

		// ZonaC3
		// ----------------------------------------------------------------

		Ilfe033VO vo33 = new Ilfe033VO();
		vo33.setAfiRut(vo.getAfiRut());
		vo33.setEmpRut(vo.getC1empRut());
		try {
			List l = svc_a.getIlfe033(vo33);
			if (null != l && l.size() > 0) {
				CTZONAC3 ctzonac3 = zonaC.addNewZONAC3();
				int i = 0;
				for (Iterator iter = l.iterator(); iter.hasNext();) {
					Ilfe033VO o = (Ilfe033VO) iter.next();

					ctzonac3.addNewRemuneracion();
					if (i == 0) {
						zonaC.getZONAC3().setMontoImponibleMesAnterior(toBigInteger(vo.getMontoImponibleMesAnterior()));
						zonaC.getZONAC3().setPorcenDesahucio(vo.getPorcenDesahucio());
					}
					/*
					 * Línea modificada 29-07-2014 dado que dato en formulario
					 * venia en formato incorrecto. original ->
					 * zonaC.getZONAC3()
					 * .getRemuneracionArray()[i].setAnoMesRemAnt
					 * (cal(o.getAnoMesRemAnt()));
					 */
					zonaC.getZONAC3().getRemuneracionArray()[i].setAnoMesRemAnt(cal2(o.getAnoMesRemAnt()));
					zonaC.getZONAC3().getRemuneracionArray()[i].setCodigoPrevisionRemAnt(toBigInteger(o.getCodigoPrevisionRemAnt()));
					zonaC.getZONAC3().getRemuneracionArray()[i].setMontoImponibleRemAnt(toBigInteger(o.getMontoImponibleRemAnt()));
					zonaC.getZONAC3().getRemuneracionArray()[i].setMontoIncapacidadRemAnt(toBigInteger(o.getMontoIncapacidadRemAnt()));
					zonaC.getZONAC3().getRemuneracionArray()[i].setMontoTotalRemAnt(toBigInteger(o.getMontoTotalRemAnt()));
					zonaC.getZONAC3().getRemuneracionArray()[i].setNdiasIncapacidadRemAnt(toBigInteger(o.getNdiasIncapacidadRemAnt()));
					zonaC.getZONAC3().getRemuneracionArray()[i].setNdiasRemAnt(toBigInteger(o.getNdiasRemAnt()));

					++i;

				}
			} else {
				log.info("No se encontraron licencias para envio de ZonaC3 ");
			}
		} catch (SvcException e) {
			logError(e);
		}

		// ZonaC4
		// ----------------------------------------------------------------
		Ilfe034VO vo34 = new Ilfe034VO();
		vo34.setAfiRut(vo.getAfiRut());
		try {
			CTZONAC4 ctzonac4 = zonaC.addNewZONAC4();
			String lmaLicenciasAnt = "2";
			String auxlmaLicenciasAnt = null == vo.getLmaLicenciasAnt() ? "NO" : vo.getLmaLicenciasAnt();
			/**
			 * IBM correccion sentencia if para licencias de
			 * 
			 * **/
			if (auxlmaLicenciasAnt.equalsIgnoreCase("SI"))
				lmaLicenciasAnt = "1";

			if (lmaLicenciasAnt.equalsIgnoreCase("1")) {
				List l34 = svc_a.getIlfe034(vo34);
				if (null != l34 && l34.size() > 0) {
					int j = 0;
					for (Iterator iter = l34.iterator(); iter.hasNext() && j < 6;) {
						Ilfe034VO o = (Ilfe034VO) iter.next();
						ctzonac4.addNewLicenciaAnterior();
						zonaC.getZONAC4().getLicenciaAnteriorArray()[j].setLmaFechaDesde(cal(o.getLmaFechaDesde()));
						zonaC.getZONAC4().getLicenciaAnteriorArray()[j].setLmaFechaHasta(cal(o.getLmaFechaHasta()));
						zonaC.getZONAC4().getLicenciaAnteriorArray()[j].setLmaNdias(toBigInteger(o.getLmaNdias()));

						++j;
					}
				} else {
					lmaLicenciasAnt = "2";
				}
			}
			zonaC.getZONAC4().setLmaLicenciasAnt(toBigInteger(lmaLicenciasAnt));
		} catch (SvcException e1) {
			logError(e1);
		}

		// ZonaCC
		// ----------------------------------------------------------------
		CTZONACC ctzonacc = zonaC.addNewZONACC();

		zonaC.getZONACC().setCodigoTramitacionCCAF(toBigInteger(vo.getCodigoTramitacionCCAF()));
		zonaC.getZONACC().setTieneMas100(toBigInteger(vo.getTieneMas100()));

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
			EndPointUtil instanciaEndPoint = EndPointUtil.getInstance();
			String urlUtilizada = "";
			long tiempoUtilizado = 0;
			String url1 = "";
			Boolean error1 = instanciaEndPoint.getEstadoError(vo.getCodOpe(), "1");
			if (error1 != null && error1 == Boolean.FALSE) {
				// System.out.println("No tiene error en la prioridad 1");
				url1 = instanciaEndPoint.getEndPoint(vo.getCodOpe(), nombreHash, "1");
			}

			String url2 = "";
			Boolean error2 = instanciaEndPoint.getEstadoError(vo.getCodOpe(), "2");
			if (error2 != null && error2 == Boolean.FALSE) {
				// System.out.println("No tiene error en la prioridad 2");
				url2 = instanciaEndPoint.getEndPoint(vo.getCodOpe(), nombreHash, "2");
			}

			/************************************************/
			String tipoInstitucion = "C";
			String digLicencia = dv(vo.getNumImpre().intValue());
			Date fechaProceso = new Date();

			if (logDetail) {
				log.info("Licencia:" + vo.getNumImpre() + "\n" + zonaC.toString().replaceAll("urn:www:lme:gov:cl:lme", "").replaceAll("xmlns=\"\"", "").replaceAll("urn:", "lme:"));
			}

			// ServiciosMultiOperador servicio = new
			// ServiciosMultiOperador(vo.getCodOpe().trim(), tipoInstitucion,
			// vo.getCodCcaf().trim(), vo.getPwdCcaf().trim(), urlOpe);
			ServiciosMultiOperador servicio = new ServiciosMultiOperador(vo.getCodOpe().trim(), tipoInstitucion, vo.getCodCcaf().trim(), vo.getPwdCcaf().trim(), url1, url2);
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
				respuesta = servicio.informaRentaLicencia2(vo.getNumImpre().intValue(), digLicencia, fechaProceso, zonaC);

				/*******************************************************/
				// se asignan errores de timeout segun la respuesta del servico
				// y se guarda el tiempo de demoro del servicio que funciona
				// correctamente
				if (respuesta.isError1()) {

					if (instanciaEndPoint.getEstadoError(vo.getCodOpe(), "1") == Boolean.FALSE) {
						instanciaEndPoint.cambiarEstadoError(vo.getCodOpe(), "1", Boolean.TRUE);
						log.info("Problemas por TimeOut con la licencia " + vo.getNumImpre() + " al tratar de ejecutar servicio " + url1 + " (Vordel) en el operador " + vo.getCodOpe());
					}
					if (respuesta.isError2()) {
						instanciaEndPoint.cambiarEstadoError(vo.getCodOpe(), "2", Boolean.TRUE);
						log.info("Problemas por TimeOut con la licencia " + vo.getNumImpre() + " al tratar de ejecutar servicio " + url2 + " (" + instanciaEndPoint.getNombreOperador(vo.getCodOpe()) + ") en el operador " + vo.getCodOpe());
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
					instanciaEndPoint.cambiarEstadoError(vo.getCodOpe(), Boolean.TRUE);
					return;
				}
				/******************************************************/
			} catch (ConfiguracionException e) {
				logError(e);
			} catch (ErrorInvocacionOperadorException e) {
				logError(e);
			}
			String enviada = "0";
			String respWs = "1";
			String glosaEstado = Constants.WS_UNAVAILABLE;
			if (null != respuesta && respuesta.getRespuesta() != null) {
				enviada = "1";
				respWs = respuesta.getRespuesta().getEstado() != 0 ? "1" : "0";
				glosaEstado = respuesta.getRespuesta().getGloEstado();
			}

			log.info("[" + urlUtilizada + "] - Licencia:" + vo.getNumImpre() + "  Estado:" + glosaEstado + "  DEMORO:" + String.valueOf(tiempoUtilizado) + " Milisegundos");
			glosaEstado = reformateaGlosaEstado(glosaEstado);

			// listaResult.add(
			// "numLicencia: ${numLicencia}, codEstado: ${respuesta.estado}, glosaEstado: ${respuesta.gloEstado}"
			// );
			Ilfe031VO newVO = new Ilfe031VO();
			newVO.setRespWs(respWs);// String.valueOf(respuesta.getEstado())
			newVO.setGlosaEstado(glosaEstado);
			newVO.setFechaConsulta(sdf.format(now));// yyyyMMdd
			newVO.setHora(shf.format(now));
			newVO.setIdeOpe(vo.getIdeOpe());
			newVO.setNumImpre(vo.getNumImpre());
			newVO.setEnviada(enviada);// respuesta.getEstado()==0?"1":"0"

			// SET ENVIADA = #enviada#, RESPWS = #respWs#, GLORESP =
			// #glosaEstado#,
			// FECRESP = #fechaConsulta#, HORRESP = #hora#
			// WHERE IDEOPE = #ideOpe# AND NUMIMPRE = #numImpre#
			svc_a.updateIlfe031(newVO);

			// listaResult.add("update: ${resUpdate}");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			log.error(e.getClass() + "; " + e.getMessage());
		}

	}// end enviarZonaC

	/* Modificación 30-07-2014 */
	private Calendar cal2(String date) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		try {

			calendar.setTime(sdm.parse(date));
		} catch (ParseException e) {
			logError(e);
		}
		// System.out.println("Calendar2 = " + calendar);
		return calendar;
	}

	/* Fin modificación */

	/**
	 * IBM RAC-7021 modificacion ZONA_C /30-07-2014] separacion datos de
	 * direccion empresa en: calle (encodeBase64) depto numero
	 * **/

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

	public static String dv(int num) {
		int M = 0, S = 1, T = num;
		for (; T != 0; T /= 10)
			S = (S + T % 10 * (9 - M++ % 6)) % 11;
		char r = (char) (S != 0 ? S + 47 : 75);
		return String.valueOf(r);
	}

	public static String dv(String numLicen) {
		BigInteger num = new BigInteger(numLicen);
		return dv(num);
	}

	public static String dv(BigInteger num) {
		long M = 0, S = 1, T = num.longValue();
		for (; T != 0; T /= 10)
			S = (S + T % 10 * (9 - M++ % 6)) % 11;
		char r = (char) (S != 0 ? S + 47 : 75);
		return String.valueOf(r);
	}

	private BigInteger toBigInteger(Integer value) {
		return null == value ? ZERO : new BigInteger(value.toString());
	}

	private BigInteger toBigInteger(String value) {
		return null == value || "".equals(value.trim()) ? ZERO : new BigInteger(value);
	}

	private BigInteger toBigInteger(Short value) {
		return null == value ? ZERO : new BigInteger(value.toString());
	}

	private BigInteger toBigInteger(BigDecimal value) {
		return null == value ? ZERO : new BigInteger(value.toString());
	}

	private String toString(String value) {
		return null == value ? "" : value.trim();
	}

	private Calendar assingCalendar(String value) {
		return null;
	}

	public Date dateCal() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return date;
	}

	public void endProcess(String processName, String msg) {
		String dataInf = ":::" + processName + ", Resultado: " + msg;// +
		// resultado.rows
		log.info(dataInf);
		dataInf = ":::" + processName + ", Fin.";
		log.info(dataInf);
		dataInf = "---------------------------------------------------------------------------------------------------------------------------";
		log.info(dataInf);
	}

	public void startProcess(String processName) {
		String dataInf = ":::" + processName + ", Inicio: " + dateCal();
		log.info(dataInf);
	}

	public void validarLicenciasME() {
		proceso = "3";
		String dataInf = "";
		startProcess("ValidarLicenciasME");
		try {
			List l = svc_a.getIlfe021();
			if (!empty(l)) {

				// auxiliar distinto a un codigo de operador
				int aux = -999;
				for (Iterator iter = l.iterator(); iter.hasNext();) {

					Ilfe021VO vo = (Ilfe021VO) iter.next();

					/** ************************************** */
					/*
					 * la primera vez pasa correctamente el codigo de operador y
					 * para las otras opciones se valida si el operador tenia
					 * errores y se salta al siguiente ciclo del for
					 */
					int codOpeInt = Integer.parseInt(vo.getCodOpe());
					if (aux == codOpeInt) {
						Boolean errorTotal;
						try {
							errorTotal = EndPointUtil.getInstance().getEstadoError(vo.getCodOpe());
							if (errorTotal != null && errorTotal == Boolean.TRUE) {
								// System.out.println("tiene error en los dos
								// endpoint");
								continue;
							}
						} catch (Exception e2) {
							// e2.printStackTrace();
							log.error(e2.getClass() + "; " + e2.getMessage());
						}
					} else {
						aux = codOpeInt;
					}

					/** ************************************** */

					operador = vo.getCodOpe();
					validarLicenciaME(vo);
				}
			} else {
				dataInf = "No se encontraron licencias para validar";
			}

		} catch (Throwable e) {
			logError(e);
		}
		operador = "";
		endProcess("ValidarLicenciasME", dataInf);

	}

	/**
	 * @param vo
	 */
	private void validarLicenciaME(Ilfe021VO vo) {

		// ALMESOAP-43 12-03-2012
		// Ilfe031VO ilfe031VO = new Ilfe031VO();
		// ilfe031VO.setNumImpre(new Long(vo.getNumImpre().toString()));
		// try {
		// List l = svc.getIlfe031(ilfe031VO);
		// if(empty(l)){
		// log("Licencia "+vo.getNumImpre()+" sin ZONA_C");
		// return;
		// }
		// } catch (SvcException e2) {
		// logError(e2);
		// }
		//
		Date now = new Date();

		BigInteger idLicencia = toBigInteger(vo.getNumImpre());
		String dvLicencia = dv(idLicencia.intValue());
		// String urlOpe = vo.getUrlOpe();

		LicenciaSimpleType[] listaLicencias = new LicenciaSimpleType[1];
		listaLicencias[0] = new LicenciaSimpleType(idLicencia, dvLicencia, vo.getEstado());

		// UrlBorderVO urlVO = new UrlBorderVO();
		// urlVO.setCodOpe(vo.getCodOpe());
		// urlVO.setNombreServicio("VALIDACION");
		String nombreHash = "VALIDACION";
		try {
			/*
			 * String url = svc.getUrlVordel(urlVO); if(null==url){
			 * log("IGNORANDO SERVICIO"); return; }
			 * 
			 * urlOpe = url ;
			 */
			/** ****************************************** */
			// guarda la url que no tenga error segun la prioridad
			EndPointUtil instanciaEndPoint = EndPointUtil.getInstance();
			String url1 = "";
			String urlUtilizada = "";
			long tiempoUtilizado = 0;
			Boolean error1 = instanciaEndPoint.getEstadoError(vo.getCodOpe(), "1");
			if (error1 != null && error1 == Boolean.FALSE) {
				// System.out.println("No tiene error en la prioridad 1");
				url1 = instanciaEndPoint.getEndPoint(vo.getCodOpe(), nombreHash, "1");
			}

			String url2 = "";
			Boolean error2 = instanciaEndPoint.getEstadoError(vo.getCodOpe(), "2");
			if (error2 != null && error2 == Boolean.FALSE) {
				// System.out.println("No tiene error en la prioridad 2");
				url2 = instanciaEndPoint.getEndPoint(vo.getCodOpe(), nombreHash, "2");
			}

			/** ********************************************* */

			ServiciosMultiOperador servicio = new ServiciosMultiOperador(vo.getCodOpe().trim(), TIPO_INSTITUCION, vo.getCodCcaf().trim(), vo.getPwdCcaf().trim(), url1, url2);
			// LMEInfValCCAFResponse respuesta =
			// null;//servicio.informaValidacionLicencias(listaLicencias);
			SalidaLMEInfValCCAF respuesta = null;// servicio.
													// informaValidacionLicencias
													// (listaLicencias);
			String glosaEstado = Constants.WS_UNAVAILABLE;
			try {
				// ejecuta el nuevo servicio que maneja el timeout y utiliza la
				// dos url enviadas
				respuesta = servicio.informaValidacionLicencias2(listaLicencias);

				/** **************************************************** */
				// se asignan errores de timeout segun la respuesta del servico
				// y se guarda el tiempo de demoro del servicio que funciona
				// correctamente
				if (respuesta.isError1()) {

					if (instanciaEndPoint.getEstadoError(vo.getCodOpe(), "1") == Boolean.FALSE) {
						instanciaEndPoint.cambiarEstadoError(vo.getCodOpe(), "1", Boolean.TRUE);
						log.info("Problemas por TimeOut con la licencia " + vo.getNumImpre() + " al tratar de ejecutar servicio " + url1 + " (Vordel) en el operador " + vo.getCodOpe());
					}
					if (respuesta.isError2()) {
						instanciaEndPoint.cambiarEstadoError(vo.getCodOpe(), "2", Boolean.TRUE);
						log.info("Problemas por TimeOut con la licencia " + vo.getNumImpre() + " al tratar de ejecutar servicio " + url2 + " (" + instanciaEndPoint.getNombreOperador(vo.getCodOpe()) + ") en el operador " + vo.getCodOpe());
						// System.out.println("Problemas por TimeOut con la
						// licencia "+vo.getNumImpre()+" al tratar de ejecutar
						// servicio "+url2+" en el operador "+vo.getCodOpe());
					} else {
						urlUtilizada = url2;
						tiempoUtilizado = respuesta.getTiempo2();
					}

					// System.out.println("Problemas por TimeOut con la licencia
					// "+vo.getNumImpre()+" al tratar de ejecutar servicio
					// "+url1+" en el operador "+vo.getCodOpe());
				} else {
					urlUtilizada = url1;
					tiempoUtilizado = respuesta.getTiempo1();
				}

				if (respuesta.isError1() && respuesta.isError2()) {
					instanciaEndPoint.cambiarEstadoError(vo.getCodOpe(), Boolean.TRUE);
					return;
				}
				/** *************************************************** */

			} catch (ConfiguracionException e1) {
				logError(e1);
				glosaEstado = e1.getMessage();
			} catch (ErrorInvocacionOperadorException e1) {
				logError(e1);
				glosaEstado = e1.getMessage();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				logError(e1);
				glosaEstado = e1.getMessage();
			}

			String enviada = "0";
			String respWs = "1";

			if (respuesta != null && respuesta.getRespuesta() != null && respuesta.getRespuesta().getEstado().equals(ZERO)) {
				ResultadoType[] resultados = respuesta.getRespuesta().getListaResultado();
				for (int i = 0; i < resultados.length; i++) {
					ResultadoType resultadoOp = resultados[i];
					respWs = resultadoOp.getCodEstado().toString();
					respWs = "0".equals(respWs) ? "0" : "1";//respWs.length()>1?
															// "1":respWs;
					glosaEstado = resultadoOp.getGloEstado();
				}
				enviada = "1";
				// listaResult.add("IdLicencia: ${idLicencia}, codEstado:
				// ${codEstado}, glosaEstado: ${glosaEstado}");
			}

			log.info("[" + urlUtilizada + "] - IdLicencia:" + idLicencia + ", codEstado:" + respWs + ", glosaEstado:" + glosaEstado + ", DEMORO:" + String.valueOf(tiempoUtilizado) + " Milisegundos");
			glosaEstado = reformateaGlosaEstado(glosaEstado);
			/**
			 * UPDATE ILFE021 SET ENVIADA = 1, RESPWS = '${respuesta.estado}',
			 * GLORESP = '${glosaEstado}', FECRESP = ${fechaConsulta}, HORRESP =
			 * ${hora} WHERE IDEOPE = ${op.IDEOPE} AND NUMIMPRE = ${idLicencia}
			 */
			Ilfe021VO vo21 = new Ilfe021VO();
			vo21.setIdeOpe(vo.getIdeOpe());
			vo21.setNumImpre(vo.getNumImpre());
			vo21.setEnviada(enviada);
			vo21.setRespWs(respWs);
			vo21.setGlosaEstado(glosaEstado);
			vo21.setFechaRespuesta(sdf.format(now)); // yyyyMMdd
			vo21.setHoraRespuesta(shf.format(now)); // HHmmss

			log.info(":::UpdateIlfe021::: " + svc_a.updateIlfe021(vo21));
		} catch (Exception e) {
			// log.error(e.getClass() + "; "+ e.getMessage());
			logError(e);
		}

	}

	/* 20120819 INICIO */
	public void devolverLicenciasME051R() {
		proceso = "9";

		String dataInf = "";
		try {
			List l = svc_a.getIlfe051R();
			if (l.size() > 0) {

				// auxiliar distinto a un codigo de operador
				int aux = -999;
				for (Iterator iter = l.iterator(); iter.hasNext();) {
					Ilfe051RVO vo = (Ilfe051RVO) iter.next();
					// Ilfe051VO vo = (Ilfe051VO) iter.next();

					/** ************************************** */
					/*
					 * la primera vez pasa correctamente el codigo de operador y
					 * para las otras opciones se valida si el operador tenia
					 * errores y se salta al siguiente ciclo del for
					 */
					int codOpeInt = Integer.parseInt(vo.getCodOpe());
					if (aux == codOpeInt) {
						Boolean errorTotal;
						try {
							errorTotal = EndPointUtil.getInstance().getEstadoError(vo.getCodOpe());
							if (errorTotal != null && errorTotal == Boolean.TRUE) {
								// System.out.println("tiene error en los dos
								// endpoint");
								continue;
							}
						} catch (Exception e2) {
							// e2.printStackTrace();
							log.error(e2.getClass() + "; " + e2.getMessage());
						}
					} else {
						aux = codOpeInt;
					}
					/** ************************************** */
					operador = vo.getCodOpe();
					devolverLicenciaME051R(vo);
				}
			} else {
				dataInf = "No se encontraron licencias para devolver";
			}

		} catch (Throwable e) {// SvcException
			// log("ERROR "+e.getCause().getMessage());
			logError(e);
		}
		operador = "";
		endProcess("DevolverLicenciasME", dataInf);
	}

	/**
	 * @param vo
	 */
	private void devolverLicenciaME051R(Ilfe051RVO vo) {

		Date now = new Date();
		// String urlOpe = vo.getUrlOpe(); //Web Service

		// UrlBorderVO urlVO = new UrlBorderVO();
		try {
			// urlVO.setCodOpe(vo.getCodOpe().trim());
			// urlVO.setNombreServicio("DEVOLUCION");
			String nombreHash = "DEVOLUCION";

			/*
			 * String url = svc.getUrlVordel(urlVO); if(null==url){
			 * //log("IGNORANDO SERVICIO"); return; } urlOpe = url.trim();
			 */

			/** ****************************************** */
			// guarda la url que no tenga error segun la prioridad
			EndPointUtil instanciaEndPoint = EndPointUtil.getInstance();

			String url1 = "";
			String urlUtilizada = "";
			long tiempoUtilizado = 0;
			Boolean error1 = instanciaEndPoint.getEstadoError(vo.getCodOpe(), "1");
			if (error1 != null && error1 == Boolean.FALSE) {
				// System.out.println("No tiene error en la prioridad 1");
				url1 = instanciaEndPoint.getEndPoint(vo.getCodOpe(), nombreHash, "1");
			}

			String url2 = "";
			Boolean error2 = instanciaEndPoint.getEstadoError(vo.getCodOpe(), "2");
			if (error2 != null && error2 == Boolean.FALSE) {
				// System.out.println("No tiene error en la prioridad 2");
				url2 = instanciaEndPoint.getEndPoint(vo.getCodOpe(), nombreHash, "2");
			}

			/** ********************************************* */

			BigInteger numLicencia = toBigInteger(vo.getNumImpre().trim());
			String digLicencia = dv(numLicencia).trim();

			int motivoDevolucion = 0;

			if (vo.getGloMot().toString() != null && vo.getGloMot().toString().trim() != "") {
				motivoDevolucion = Integer.parseInt(vo.getGloMot().toString().trim());
			}

			// ServiciosMultiOperador servicio = new
			// ServiciosMultiOperador(vo.getCodOpe().trim(), TIPO_INSTITUCION,
			// vo.getCodCcaf().trim(), vo.getPwdCcaf().trim(), urlOpe.trim());
			ServiciosMultiOperador servicio = new ServiciosMultiOperador(vo.getCodOpe().trim(), TIPO_INSTITUCION, vo.getCodCcaf().trim(), vo.getPwdCcaf().trim(), url1, url2);
			// RespuestaServicio respuesta =
			// null;//servicio.devolucionLicencia(numLicencia.intValue(),
			// digLicencia, motivoDevolucion, new Date());
			SalidaLMEDevEmp respuesta = null;// servicio.devolucionLicencia(
												// numLicencia.intValue(),
			// digLicencia,
			// motivoDevolucion, new
			// Date());

			try {

				if (motivoDevolucion != 0) {
					// ejecuta el nuevo servicio que maneja el timeout y utiliza
					// la dos url enviadas
					respuesta = servicio.devolucionLicencia2(numLicencia.intValue(), digLicencia, motivoDevolucion, new Date());

					/** **************************************************** */
					// se asignan errores de timeout segun la respuesta del
					// servico y se guarda el tiempo de demoro del servicio que
					// funciona correctamente
					if (respuesta.isError1()) {

						if (instanciaEndPoint.getEstadoError(vo.getCodOpe(), "1") == Boolean.FALSE) {
							instanciaEndPoint.cambiarEstadoError(vo.getCodOpe(), "1", Boolean.TRUE);
							log.info("Problemas por TimeOut con la licencia " + vo.getNumImpre() + " al tratar de ejecutar servicio " + url1 + " (Vordel) en el operador " + vo.getCodOpe());
						}
						if (respuesta.isError2()) {
							instanciaEndPoint.cambiarEstadoError(vo.getCodOpe(), "2", Boolean.TRUE);
							log.info("Problemas por TimeOut con la licencia " + vo.getNumImpre() + " al tratar de ejecutar servicio " + url2 + " (" + instanciaEndPoint.getNombreOperador(vo.getCodOpe()) + ") en el operador " + vo.getCodOpe());
							// System.out.println("Problemas por TimeOut con la
							// licencia "+vo.getNumImpre()+" al tratar de
							// ejecutar servicio "+url2+" en el operador
							// "+vo.getCodOpe());
						} else {
							urlUtilizada = url2;
							tiempoUtilizado = respuesta.getTiempo2();
						}

						// System.out.println("Problemas por TimeOut con la
						// licencia "+vo.getNumImpre()+" al tratar de ejecutar
						// servicio "+url1+" en el operador "+vo.getCodOpe());
					} else {
						urlUtilizada = url1;
						tiempoUtilizado = respuesta.getTiempo1();
					}

					if (respuesta.isError1() && respuesta.isError2()) {
						instanciaEndPoint.cambiarEstadoError(vo.getCodOpe(), Boolean.TRUE);
						return;
					}
					/** *************************************************** */

				}

			} catch (ConfiguracionException e) {
				logError(e);
			} catch (ErrorInvocacionOperadorException e) {
				logError(e);
			}

			String enviada = "0";
			String respWs = "1";
			String glosaEstado = Constants.WS_UNAVAILABLE;
			if (null != respuesta && respuesta.getRespuesta() != null) {
				enviada = "1";
				respWs = respuesta.getRespuesta().getEstado() != 0 ? "1" : "0";
				glosaEstado = respuesta.getRespuesta().getGloEstado();
			}

			log.info("[" + urlUtilizada + "] - Licencia:" + numLicencia + "-" + digLicencia + ",	Estado=" + glosaEstado + ",  DEMORO:" + String.valueOf(tiempoUtilizado) + " Milisegundos");
			glosaEstado = reformateaGlosaEstado(glosaEstado);

			Ilfe051RVO vo51 = new Ilfe051RVO();
			vo51.setIdeOpe(vo.getIdeOpe());
			vo51.setNumImpre(vo.getNumImpre());
			vo51.setEnviada(enviada);
			vo51.setRespWs(respWs);
			vo51.setGlosaEstado(glosaEstado);
			vo51.setFechaRespuesta(sdf.format(now)); // yyyyMMdd
			vo51.setHoraRespuesta(shf.format(now)); // HHmmss

			svc_a.updateIlfe051R(vo51);
		} catch (Exception e) {
			logError(e);
		}

	}/* FIN */

	public void devolverLicenciasME() {
		proceso = "2";

		String dataInf = "";
		try {
			List l = svc_a.getIlfe051();
			if (l.size() > 0) {

				// auxiliar distinto a un codigo de operador
				int aux = -999;
				for (Iterator iter = l.iterator(); iter.hasNext();) {
					Ilfe051VO vo = (Ilfe051VO) iter.next();

					/** ************************************** */
					/*
					 * la primera vez pasa correctamente el codigo de operador y
					 * para las otras opciones se valida si el operador tenia
					 * errores y se salta al siguiente ciclo del for
					 */
					int codOpeInt = Integer.parseInt(vo.getCodOpe());
					if (aux == codOpeInt) {
						Boolean errorTotal;
						try {
							errorTotal = EndPointUtil.getInstance().getEstadoError(vo.getCodOpe());
							if (errorTotal != null && errorTotal == Boolean.TRUE) {
								// System.out.println("tiene error en los dos
								// endpoint");
								continue;
							}
						} catch (Exception e2) {
							// e2.printStackTrace();
							log.error(e2.getClass() + "; " + e2.getMessage());
						}
					} else {
						aux = codOpeInt;
					}

					/** ************************************** */

					operador = vo.getCodOpe();
					devolverLicenciaME(vo);

				}
			} else {
				dataInf = "No se encontraron licencias para devolver";
			}

		} catch (Throwable e) {// SvcException
			// log("ERROR "+e.getCause().getMessage());
			logError(e);
		}
		operador = "";
		endProcess("DevolverLicenciasME", dataInf);
	}

	/**
	 * @param vo
	 */
	private void devolverLicenciaME(Ilfe051VO vo) {

		Date now = new Date();
		// String urlOpe = vo.getUrlOpe(); //Web Service

		// UrlBorderVO urlVO = new UrlBorderVO();
		try {
			// urlVO.setCodOpe(vo.getCodOpe());
			// urlVO.setNombreServicio("DEVOLUCION");
			String nombreHash = "DEVOLUCION";

			/*
			 * String url = svc.getUrlVordel(urlVO); if(null==url){
			 * //log("IGNORANDO SERVICIO"); return; } urlOpe = url;
			 */

			/** ****************************************** */
			// guarda la url que no tenga error segun la prioridad
			EndPointUtil instanciaEndPoint = EndPointUtil.getInstance();
			String url1 = "";
			String urlUtilizada = "";
			long tiempoUtilizado = 0;
			Boolean error1 = instanciaEndPoint.getEstadoError(vo.getCodOpe(), "1");
			if (error1 != null && error1 == Boolean.FALSE) {
				// System.out.println("No tiene error en la prioridad 1");
				url1 = instanciaEndPoint.getEndPoint(vo.getCodOpe(), nombreHash, "1");
			}

			String url2 = "";
			Boolean error2 = instanciaEndPoint.getEstadoError(vo.getCodOpe(), "2");
			if (error2 != null && error2 == Boolean.FALSE) {
				// System.out.println("No tiene error en la prioridad 2");
				url2 = instanciaEndPoint.getEndPoint(vo.getCodOpe(), nombreHash, "2");
			}

			/** ********************************************* */

			BigInteger numLicencia = toBigInteger(vo.getNumImpre());
			String digLicencia = dv(numLicencia);
			int motivoDevolucion = vo.getCodMot().intValue();

			// ServiciosMultiOperador servicio = new
			// ServiciosMultiOperador(vo.getCodOpe().trim(), TIPO_INSTITUCION,
			// vo.getCodCcaf().trim(), vo.getPwdCcaf().trim(), urlOpe);
			ServiciosMultiOperador servicio = new ServiciosMultiOperador(vo.getCodOpe().trim(), TIPO_INSTITUCION, vo.getCodCcaf().trim(), vo.getPwdCcaf().trim(), url1, url2);
			SalidaLMEDevEmp respuesta = null;// servicio.devolucionLicencia(
												// numLicencia.intValue(),
			// digLicencia,
			// motivoDevolucion, new
			// Date());

			try {
				// ejecuta el nuevo servicio que maneja el timeout y utiliza la
				// dos url enviadas
				respuesta = servicio.devolucionLicencia2(numLicencia.intValue(), digLicencia, motivoDevolucion, new Date());

				/** **************************************************** */
				// se asignan errores de timeout segun la respuesta del servico
				// y se guarda el tiempo de demoro del servicio que funciona
				// correctamente
				if (respuesta.isError1()) {

					if (instanciaEndPoint.getEstadoError(vo.getCodOpe(), "1") == Boolean.FALSE) {
						instanciaEndPoint.cambiarEstadoError(vo.getCodOpe(), "1", Boolean.TRUE);
						log.info("Problemas por TimeOut con la licencia " + vo.getNumImpre() + " al tratar de ejecutar servicio " + url1 + " (Vordel) en el operador " + vo.getCodOpe());
					}
					if (respuesta.isError2()) {
						instanciaEndPoint.cambiarEstadoError(vo.getCodOpe(), "2", Boolean.TRUE);
						log.info("Problemas por TimeOut con la licencia " + vo.getNumImpre() + " al tratar de ejecutar servicio " + url2 + " (" + instanciaEndPoint.getNombreOperador(vo.getCodOpe()) + ") en el operador " + vo.getCodOpe());
						// System.out.println("Problemas por TimeOut con la
						// licencia "+vo.getNumImpre()+" al tratar de ejecutar
						// servicio "+url2+" en el operador "+vo.getCodOpe());
					} else {
						urlUtilizada = url2;
						tiempoUtilizado = respuesta.getTiempo2();
					}

					// System.out.println("Problemas por TimeOut con la licencia
					// "+vo.getNumImpre()+" al tratar de ejecutar servicio
					// "+url1+" en el operador "+vo.getCodOpe());
				} else {
					urlUtilizada = url1;
					tiempoUtilizado = respuesta.getTiempo1();
				}

				if (respuesta.isError1() && respuesta.isError2()) {
					instanciaEndPoint.cambiarEstadoError(vo.getCodOpe(), Boolean.TRUE);
					return;
				}
				/** *************************************************** */

			} catch (ConfiguracionException e) {
				logError(e);
			} catch (ErrorInvocacionOperadorException e) {
				logError(e);
			} catch (Exception e) {
				logError(e);
			}

			String enviada = "0";
			String respWs = "1";
			String glosaEstado = Constants.WS_UNAVAILABLE;
			if (null != respuesta && respuesta.getRespuesta() != null) {
				enviada = "1";
				respWs = respuesta.getRespuesta().getEstado() != 0 ? "1" : "0";
				glosaEstado = respuesta.getRespuesta().getGloEstado();
			}

			log.info("[" + urlUtilizada + "] - Licencia:" + numLicencia + "-" + digLicencia + ",  Estado:" + glosaEstado + ",  DEMORO:" + String.valueOf(tiempoUtilizado) + " Milisegundos");
			glosaEstado = reformateaGlosaEstado(glosaEstado);

			Ilfe051VO vo51 = new Ilfe051VO();
			vo51.setIdeOpe(vo.getIdeOpe());
			vo51.setNumImpre(vo.getNumImpre());
			vo51.setEnviada(enviada);
			vo51.setRespWs(respWs);
			vo51.setGlosaEstado(glosaEstado);
			vo51.setFechaRespuesta(sdf.format(now)); // yyyyMMdd
			vo51.setHoraRespuesta(shf.format(now)); // HHmmss

			svc_a.updateIlfe051(vo51);
		} catch (Exception e) {
			logError(e);
		}

	}

	/*
	 * Informa Zona D al operador para todas las LME que encuentre disponibles
	 * en LIEXP.ILFE011
	 */
	public void liquidarLicenciasME() {
		proceso = "5";
		// Despliega mensaje con el inicio del proceso de liquidación
		startProcess("LiquidarLicenciasME");
		String dataInf = "";
		try {
			// obtiene el listado de LME a informar Zona D, ver ILFE011.xml
			List l = svc_a.getIlfe011();

			// si tengo datos en la lista obtenida, la recorro
			if (!empty(l)) {

				// auxiliar distinto a un codigo de operador
				int aux = -999;
				for (Iterator iter = l.iterator(); iter.hasNext();) {
					Ilfe011VO vo = (Ilfe011VO) iter.next();

					/** ************************************** */
					/*
					 * la primera vez pasa correctamente el codigo de operador y
					 * para las otras opciones se valida si el operador tenia
					 * errores y se salta al siguiente ciclo del for
					 */
					int codOpeInt = Integer.parseInt(vo.getCodOpe());
					if (aux == codOpeInt) {
						Boolean errorTotal;
						try {
							errorTotal = EndPointUtil.getInstance().getEstadoError(vo.getCodOpe());
							if (errorTotal != null && errorTotal == Boolean.TRUE) {
								// System.out.println("tiene error en los dos
								// endpoint");
								continue;
							}
						} catch (Exception e2) {
							// e2.printStackTrace();
							log.error(e2.getClass() + "; " + e2.getMessage());
						}
					} else {
						aux = codOpeInt;
					}

					/** ************************************** */

					// log(":::op: "+vo.get);
					operador = vo.getCodOpe();
					// consume servicio de liquidación y construye XML para
					// informar Zona D
					liquidarLicenciaME(vo);
				}
			} else {
				dataInf = "No se encontraron licencias para liquidar";
			}

		} catch (Throwable e) {
			logError(e);
		}
		operador = "";
		// despliega mensaje de fin del proceso de liquidación
		endProcess("LiquidarLicenciasME", dataInf);
	}

	/*
	 * Permite crear el XML de Zona D y consumir el servicio de liquidación
	 * publicado por el operador
	 */
	private void liquidarLicenciaME(Ilfe011VO vo) {

		try {
			Date now = new Date();
			// Datos de la sección liquidación
			Date periodoRenta = date(vo.getPeriodo().toString(), "yyyyMM"); // obligatorio
			// según
			// esquema
			Date fechaPagoProbable = date(vo.getFecPropag(), "yyyyMMdd");
			Date fechaDesdeLiquidacion = date(vo.getFecDesde(), "yyyyMMdd");
			Date fechaHastaLiquidacion = date(vo.getFecHasta(), "yyyyMMdd");
			Date fechaProceso = date(vo.getFecProce(), "yyyyMMdd");

			// url del servicio a ejecutar para esta licencia
			// String urlOpe = vo.getUrlOpe().trim();

			// genero una intancia de la Zona ZonaD
			CTZONAD zonaD = CTZONAD.Factory.newInstance();
			CTZONAD1 ctzonad1 = zonaD.addNewZONAD1();
			zonaD.addNewZONADF();
			ctzonad1.addNewLiquidacion();

			/*
			 * información obligatorio de la sección liquidación del XML de Zona
			 * D, periodo de renta, tipo y evento de liquidación
			 */
			zonaD.getZONAD1().getLiquidacion().setPeriodoRenta(cal(periodoRenta));
			zonaD.getZONAD1().getLiquidacion().setEventoLiquidacionCCAF(toBigInteger(vo.getEventoLiquidacion()));
			zonaD.getZONAD1().getLiquidacion().setTipoLiquidacionCCAF(toBigInteger(vo.getTipoLiquidacion()));

			/*
			 * corroboro si el tipo de liquidación implica pago, de ser asi hay
			 * que agregar la información del pago al XML de Zona D
			 */
			if (vo.getTipoLiquidacion().intValue() == 1) {
				zonaD.getZONAD1().getLiquidacion().setFechaDesdeLiquidacion(cal(fechaDesdeLiquidacion));
				zonaD.getZONAD1().getLiquidacion().setFechaHastaLiquidacion(cal(fechaHastaLiquidacion));
				zonaD.getZONAD1().getLiquidacion().setFechaPagoProbable(cal(fechaPagoProbable));
				zonaD.getZONAD1().getLiquidacion().setMontoApagarSubsidio(toBigInteger(vo.getMontoApagarSubsidio()));
				zonaD.getZONAD1().getLiquidacion().setMontoAportePensiones(toBigInteger(vo.getMontoAportePensiones()));
				zonaD.getZONAD1().getLiquidacion().setMontoAporteSalud(toBigInteger(vo.getMontoAporteSalud()));
				zonaD.getZONAD1().getLiquidacion().setMontoSeguroCesantia(toBigInteger(vo.getMontoSeguroCesantia()));
				zonaD.getZONAD1().getLiquidacion().setMontoSubsidioDiario(toBigInteger(vo.getMontoSubsidioDiario()));
				zonaD.getZONAD1().getLiquidacion().setNdiasApagarPrevision(toBigInteger(vo.getNdiasApagarPrevision()));
				zonaD.getZONAD1().getLiquidacion().setNdiasApagarSubsidios(toBigInteger(vo.getNdiasApagarSubsidios()));
			}

			Ilfe013VO vo13 = new Ilfe013VO();
			/*
			 * establesco la información de rut afiliado y rut empresa para
			 * buscar las remuneraciones de la base de calculo del trabajador
			 */
			vo13.setAfiRut(vo.getAfiRut());
			vo13.setEmpRut(vo.getEmpRut());
			// busco las remuneraciones
			List l = svc_a.getIlfe013(vo13);

			// agrego las remuneraciones que encuentre en el archivo ilfe013
			if (null != l && l.size() > 0) {
				int i = 0;
				/*
				 * el XML acepta hasta 12 remuneraciones, pero en la base de
				 * datos deberían existir solo 3 o 6 registros esto depende de
				 * si la LME es maternal o no, si la licencia es maternal son 6
				 * registros
				 */
				for (Iterator iter = l.iterator(); iter.hasNext() && i < 6;) {
					Ilfe013VO o = (Ilfe013VO) iter.next();

					Date anoMesRemAnt = date(o.getPeriodo().toString(), "yyyyMM");

					ctzonad1.addNewRemuneraciones();
					/*
					 * los datos que son obligatorios son: AnoMesRemAnt,
					 * CodigoPrevisionRemAnt y NdiasRemAnt, de no estar
					 * presentes el XML no estaría bajo el esquema 1.3, esta es
					 * ininformación el aplicativo as400 LIEXP.ILPLME06
					 */
					zonaD.getZONAD1().getRemuneracionesArray()[i].setAnoMesRemAnt(cal(anoMesRemAnt));
					zonaD.getZONAD1().getRemuneracionesArray()[i].setCodigoPrevisionRemAnt(toBigInteger(o.getCodigoPrevisionRemAnt()));
					zonaD.getZONAD1().getRemuneracionesArray()[i].setMontoImponibleRemAnt(toBigInteger(o.getMontoImponibleRemAnt()));
					zonaD.getZONAD1().getRemuneracionesArray()[i].setMontoIncapacidadRemAnt(toBigInteger(o.getMontoIncapacidadRemAnt()));
					zonaD.getZONAD1().getRemuneracionesArray()[i].setMontoTotalRemAnt(toBigInteger(o.getMontoTotalRemAnt()));
					zonaD.getZONAD1().getRemuneracionesArray()[i].setNdiasIncapacidadRemAnt(toBigInteger(o.getNdiasIncapacidadRemAnt()));
					zonaD.getZONAD1().getRemuneracionesArray()[i].setNdiasRemAnt(toBigInteger(o.getNdiasRemAnt()));
					++i;
				}
			} else {
				/*
				 * si no encontro remuneraciones en LIEXP.ILFE013 debe crear por
				 * lo menos una remuneración, por lo menos deben estar presente
				 * los valores obligatorio
				 */
				log.info("Licencia:" + vo.getNumImpre() + " sin remuneraciones, se agregó remuneración por defecto");
				ctzonad1.addNewRemuneraciones();
				zonaD.getZONAD1().getRemuneracionesArray()[0].setAnoMesRemAnt(cal(periodoRenta));
				zonaD.getZONAD1().getRemuneracionesArray()[0].setCodigoPrevisionRemAnt(ZERO);
				zonaD.getZONAD1().getRemuneracionesArray()[0].setNdiasRemAnt(ZERO);
			}

			// agrego la firma del XML de Zona D
			zonaD.getZONADF().addNewFirma();
			zonaD.getZONADF().getFirmaArray()[0].setDescripcion("Documento respaldado por antecedentes en papel");

			// 22/03/2012
			CTZONAD[] zonaDArray = { zonaD };

			/*
			 * obtengo la url bordel (basada en el codigo del operador) para
			 * consumir el servicio de liquidación
			 */
			// UrlBorderVO urlVO = new UrlBorderVO();
			// urlVO.setCodOpe(vo.getCodOpe());
			// urlVO.setNombreServicio("LIQUIDACION");
			String nombreHash = "LIQUIDACION";

			/*
			 * String url = svc.getUrlVordel(urlVO); si no secuentra la URL
			 * basada en el servicio de liquidación para el operador en
			 * ejecución desplegar mensaje en el LOG y salir del metodo
			 * if(null==url){ COMENTATIZADO POR LVC log("IGNORANDO SERVICIO
			 * "+vo.getNumImpre()); log("No se ha encontrado la URL para
			 * consumir el servicio de LIQUIDACION (CODOPE="+vo.getCodOpe()+",
			 * NUMIMPRE="+vo.getNumImpre()); return; }
			 * 
			 * //fijamos la url que nos facilita el servicio de Liquidación
			 * urlOpe = url;
			 */

			/** ****************************************** */
			// guarda la url que no tenga error segun la prioridad
			EndPointUtil instanciaEndPoint = EndPointUtil.getInstance();

			String url1 = "";
			String urlUtilizada = "";
			long tiempoUtilizado = 0;
			Boolean error1 = instanciaEndPoint.getEstadoError(vo.getCodOpe(), "1");
			if (error1 != null && error1 == Boolean.FALSE) {
				// System.out.println("No tiene error en la prioridad 1");
				url1 = instanciaEndPoint.getEndPoint(vo.getCodOpe(), nombreHash, "1");
			}

			String url2 = "";
			Boolean error2 = instanciaEndPoint.getEstadoError(vo.getCodOpe(), "2");
			if (error2 != null && error2 == Boolean.FALSE) {
				// System.out.println("No tiene error en la prioridad 2");
				url2 = instanciaEndPoint.getEndPoint(vo.getCodOpe(), nombreHash, "2");
			}

			/** ********************************************* */

			// Se establece la instancia para consumir el servicio
			// ServiciosMultiOperador servicio = new
			// ServiciosMultiOperador(vo.getCodOpe().trim(), TIPO_INSTITUCION,
			// vo.getCodCcaf().trim(), vo.getPwdCcaf().trim(), urlOpe);
			ServiciosMultiOperador servicio = new ServiciosMultiOperador(vo.getCodOpe().trim(), TIPO_INSTITUCION, vo.getCodCcaf().trim(), vo.getPwdCcaf().trim(), url1, url2);
			// RespuestaServicio respuesta = null;
			SalidaLMEInfLiquid respuesta = null;
			BigInteger numImpre = toBigInteger(vo.getNumImpre());
			if (logDetail) {
				// en el LOG despliego el XML de Zona D
				log.info("Licencia:" + vo.getNumImpre() + "\n" + zonaD.toString().replaceAll("urn:www:lme:gov:cl:lme", "").replaceAll("xmlns=\"\"", ""));
			}

			try {
				// ejecuto el servicio de LIQUIDACION y ricibo la respuesta del
				// operador
				// ejecuta el nuevo servicio que maneja el timeout y utiliza la
				// dos url enviadas
				respuesta = servicio.liquidacionLicencia2(numImpre.intValue(), dv(numImpre.intValue()), fechaProceso, vo.getTipoLiquidacion().intValue(), zonaDArray);

				/** **************************************************** */
				// se asignan errores de timeout segun la respuesta del servico
				// y se guarda el tiempo de demoro del servicio que funciona
				// correctamente
				if (respuesta.isError1()) {

					if (instanciaEndPoint.getEstadoError(vo.getCodOpe(), "1") == Boolean.FALSE) {
						instanciaEndPoint.cambiarEstadoError(vo.getCodOpe(), "1", Boolean.TRUE);
						log.info("Problemas por TimeOut con la licencia " + vo.getNumImpre() + " al tratar de ejecutar servicio " + url1 + " (Vordel) en el operador " + vo.getCodOpe());
					}
					if (respuesta.isError2()) {
						instanciaEndPoint.cambiarEstadoError(vo.getCodOpe(), "2", Boolean.TRUE);
						log.info("Problemas por TimeOut con la licencia " + vo.getNumImpre() + " al tratar de ejecutar servicio " + url2 + " (" + instanciaEndPoint.getNombreOperador(vo.getCodOpe()) + ") en el operador " + vo.getCodOpe());
						// System.out.println("Problemas por TimeOut con la
						// licencia "+vo.getNumImpre()+" al tratar de ejecutar
						// servicio "+url2+" en el operador "+vo.getCodOpe());
					} else {
						urlUtilizada = url2;
						tiempoUtilizado = respuesta.getTiempo2();
					}

					// System.out.println("Problemas por TimeOut con la licencia
					// "+vo.getNumImpre()+" al tratar de ejecutar servicio
					// "+url1+" en el operador "+vo.getCodOpe());
				} else {
					urlUtilizada = url1;
					tiempoUtilizado = respuesta.getTiempo1();
				}

				if (respuesta.isError1() && respuesta.isError2()) {
					instanciaEndPoint.cambiarEstadoError(vo.getCodOpe(), Boolean.TRUE);
					return;
				}
				/** *************************************************** */

			} catch (ConfiguracionException e) {
				logError(e);
			} catch (ErrorInvocacionOperadorException e) {
				logError(e);
			} catch (Exception e) {
				logError(e);
			}
			// String enviada = "0";
			// 0=no se ha enviado, 1=implica que se ejecuto servicio
			String enviada = "1"; // 01-03-2012
			// 0=no hubieron problemas con el operador, 1=si ubieron problemas
			// con el operador
			String respWs = "1";
			// se establece la glosa por defecto
			String glosaEstado = Constants.WS_UNAVAILABLE;
			String fechaEstado = "";
			String horaEstado = "";
			// verifico si llego respuesta de operador
			if (null != respuesta && respuesta.getRespuesta() != null) {
				// enviada="1"; // 01-03-2012
				// fijo la respuesta
				respWs = respuesta.getRespuesta().getEstado() != 0 ? "1" : "0";
				// fijo la glosa del respuesta del operador
				glosaEstado = respuesta.getRespuesta().getGloEstado();

			}

			// despliego mensaje en el LOG con la respuesta del consumo del
			// servicio LIQUIDACION
			log.info("[" + urlUtilizada + "] - [respuesta:" + respWs + " , Estado:" + glosaEstado + ", DEMORO:" + String.valueOf(tiempoUtilizado) + " Milisegundos]");
			// formatea la glosa, y la corta a 60 caracteres
			glosaEstado = reformateaGlosaEstado(glosaEstado);

			/*
			 * establesco la respuesta del consumo del servicio en el registro
			 * correspondiente de LIEXP.ILFE011
			 */
			Ilfe011VO vo11 = new Ilfe011VO();
			vo11.setIdeOpe(vo.getIdeOpe());
			vo11.setNumImpre(vo.getNumImpre());
			vo11.setRespWs(respWs);
			vo11.setGlosaEstado(glosaEstado);
			vo11.setFechaRespuesta(sdf.format(now));
			vo11.setHoraRespuesta(shf.format(now));
			vo11.setEnviada(enviada);// respWs.equals("0")? "1" : "0"
			vo11.setFechaEstado(vo.getFechaEstado());
			vo11.setHoraEstado(vo.getHoraEstado());

			// actualizo el registro en LIEXP.ILFE011
			svc_a.updateIlfe011(vo11);

		} catch (ParseException e) {
			logError(e);
		} catch (SvcException e) {
			logError(e);
		} catch (Exception e) {
			logError(e);
		}
	}

	protected static Date date(String formated, String format) throws ParseException {
		if (null == formated || "0".equals(formated.trim()))
			formated = "19000101";

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.parse(formated);
	}

	private Calendar cal(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	private Calendar cal(String date) {
		Calendar calendar = Calendar.getInstance();
		if (date.equals("190001"))
			date += "01";
		try {
			calendar.setTime(sdf.parse(date));
		} catch (ParseException e) {
			logError(e);
		}
		return calendar;
	}

	public void consumirEstadosLME(String tipoConsumo) {
		proceso = "6";
		startProcess("ConsumirEstadosLME");
		String dataInf = "";

		StringBuffer result = new StringBuffer();
		try {
			// Guarda las licencias con error marcons=0 en ilfe000HER
			result.append("Respaldo de Licencias con error en ILFE000HER ").append(svc_a.insertIlfe000HER());

			// Borra tabla de la licencias temporal
			result.append("Limpia ILFE000: ").append(svc_a.deleteIlfe000());
			dataInf = result.toString();
			log.info(dataInf);

			IlfeOpeVO ilfeOpeVO = new IlfeOpeVO();
			ilfeOpeVO.setStsOpe(Integer.valueOf("1"));
			List l = svc_a.getIlfeOpe(ilfeOpeVO);
			if (null != l && l.size() > 0) {
				dataInf = "Licencias[" + l.size() + "]";

				// auxiliar distinto a un codigo de operador
				int aux = -999;
				for (Iterator iter = l.iterator(); iter.hasNext();) {
					IlfeOpeVO vo = (IlfeOpeVO) iter.next();

					/** ************************************** */
					/*
					 * la primera vez pasa correctamente el codigo de operador y
					 * para las otras opciones se valida si el operador tenia
					 * errores y se salta al siguiente ciclo del for
					 */
					int codOpeInt = Integer.parseInt(vo.getCodOpe());
					if (aux == codOpeInt) {
						Boolean errorTotal;
						try {
							errorTotal = EndPointUtil.getInstance().getEstadoError(vo.getCodOpe());
							if (errorTotal != null && errorTotal == Boolean.TRUE) {
								// System.out.println("tiene error en los dos
								// endpoint");
								continue;
							}
						} catch (Exception e2) {
							// e2.printStackTrace();
							log.error(e2.getClass() + "; " + e2.getMessage());
						}
					} else {
						aux = codOpeInt;
					}

					/** ************************************** */

					operador = vo.getCodOpe();
					vo.setTipoConsumo(tipoConsumo);
					consumirEstadoLME(vo);
				}
			}

		} catch (Throwable e) {
			logError(e);
		}
		operador = "";
		endProcess("ConsumirEstadosLME", dataInf);
	}

	/**
	 * @param vo
	 */
	private void consumirEstadoLME(IlfeOpeVO vo) {

		Date desde = null == dateLcc ? new Date() : dateLcc;
		Date hasta = dateFec;
		
		String urlope = vo.getUrlOpe();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(desde);

		ResourceBundle properties = ResourceBundle.getBundle("lme.resources.ApplicationResources");
		boolean hourParams = Boolean.valueOf(properties.getString("hourParams")).booleanValue();
		int hour = hourParams ? Integer.valueOf(properties.getString("hour")).intValue() : calendar.get(Calendar.HOUR_OF_DAY);
		int minute = hourParams ? Integer.valueOf(properties.getString("minute")).intValue() : calendar.get(Calendar.MINUTE);

		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		log.info("HORA CALENDAR ANTES: " + calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) + 1 + "/" + calendar.get(Calendar.DAY_OF_MONTH) + " - " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
		
		// REQ-8000001332 - Definir desplazamiento de hora segun operador
		int desplazamientoConsulta = 0;
		int desplazamientResp = 0;
		if (vo.getCodOpe().equals(Configuraciones.getConfig("codigo.medipass"))) {
			desplazamientoConsulta = BdConstants.getInstance().DESPLAZAMIENTO_MEDIPASS;
			desplazamientResp = BdConstants.getInstance().DESPLAZAMIENTO_MEDIPASS_RESP;
		} else if (vo.getCodOpe().equals(Configuraciones.getConfig("codigo.imed"))) {
			desplazamientoConsulta = BdConstants.getInstance().DESPLAZAMIENTO_IMED;
			desplazamientResp = BdConstants.getInstance().DESPLAZAMIENTO_IMED_RESP;
		}

		//Desplazar hora
		if(vo.getTipoConsumo().equals("LMEEventLcc")){
			calendar.add(Calendar.HOUR, desplazamientoConsulta);
		}else{
			calendar.add(Calendar.HOUR, desplazamientoConsulta+3);
		}
		
		log.info("HORA CALENDAR DESPUES: " + calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) + 1 + "/" + calendar.get(Calendar.DAY_OF_MONTH) + " - " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
		
		int agno = calendar.get(Calendar.YEAR);
		int mes = calendar.get(Calendar.MONTH) + 1;
		int dia = calendar.get(Calendar.DAY_OF_MONTH);

		UrlBorderVO urlVO = new UrlBorderVO();
		try {
			urlVO.setCodOpe(vo.getCodOpe());
			urlVO.setNombreServicio("CONSULTA");
			String nombreHash = "CONSULTA";

			/*
			 * String url = svc.getUrlVordel(urlVO); if(null==url){
			 * log("IGNORANDO SERVICIO CODOPE:{0}",new
			 * Object[]{vo.getCodOpe()}); return; } //log("CODOPE[{0}] :
			 * URL[{1}]",new Object[]{vo.getCodOpe(),url}); urlope = url.trim();
			 */

			/** ****************************************** */
			// guarda la url que no tenga error segun la prioridad
			EndPointUtil instanciaEndPoint = EndPointUtil.getInstance();

			String url1 = "";
			String urlUtilizada = "";
			long tiempoUtilizado = 0;
			Boolean error1 = instanciaEndPoint.getEstadoError(vo.getCodOpe(), "1");
			if (error1 != null && error1 == Boolean.FALSE) {
				// System.out.println("No tiene error en la prioridad 1");
				url1 = instanciaEndPoint.getEndPoint(vo.getCodOpe(), nombreHash, "1");
			}

			String url2 = "";
			Boolean error2 = instanciaEndPoint.getEstadoError(vo.getCodOpe(), "2");
			if (error2 != null && error2 == Boolean.FALSE) {
				// System.out.println("No tiene error en la prioridad 2");
				url2 = instanciaEndPoint.getEndPoint(vo.getCodOpe(), nombreHash, "2");
			}

			/** ********************************************* */

			// ServiciosMultiOperador servicio = new
			// ServiciosMultiOperador(vo.getCodOpe().trim(), TIPO_INSTITUCION,
			// vo.getCodCcaf().trim(),vo.getPwdCcaf().trim(), urlope);
			ServiciosMultiOperador servicio = new ServiciosMultiOperador(vo.getCodOpe().trim(), TIPO_INSTITUCION, vo.getCodCcaf().trim(), vo.getPwdCcaf().trim(), url1, url2);

			// System.out.println(
			// "##############################################################################################"
			// );
			// System.out.println("SE CARGARAN LOS DATOS DUMMY PARA EL OPERADOR
			// ->"+vo.getCodOpe().trim()+"tipo_inst ->"+TIPO_INSTITUCION);
			// LicenciaType[] listaLicencias =
			// servicio.consultaEventosLicencias(agno, mes, dia, hour, minute);
			// ejecuta el nuevo servicio que maneja el timeout y utiliza la dos
			// url enviadas

			log.info("Consumo servicio LmeEventLcc, Cod. ope: " + vo.getCodOpe() + " desplazamiento horario: " + desplazamientoConsulta + ", Timezone: " + calendar.getTimeZone() + " \n");
			// REQ-8000001332 - Ignora hora del propertie (00:01:00), ahora envía hora desplazada
			//SalidaLMEEvenLcc respuesta = servicio.consultaEventosLicencias2(agno, mes, dia, hour, minute);
			SalidaLMEEvenLcc respuesta;
			if(vo.getTipoConsumo().equals("LMEEventLcc")){
				log.info("Consumo Nuevos Estados usando LMEEventLcc");
				respuesta = servicio.consultaEventosLicencias2(agno, mes, dia, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
			}else{
				//LMEEventFec
				log.info("Consumo Nuevos Estados usando LMEEventFec");
				Calendar calendarHasta = Calendar.getInstance();
				calendarHasta.setTime(hasta);
				calendarHasta.add(Calendar.HOUR, desplazamientoConsulta+3);
				respuesta = servicio.consultaEventosLicenciasFec(calendar, calendarHasta);
				desplazamientResp = BdConstants.getInstance().DESPLAZAMIENTO_MEDIPASS_RESP;
			}

			/** **************************************************** */
			// se asignan errores de timeout segun la respuesta del servico y se
			// guarda el tiempo de demoro del servicio que funciona
			// correctamente
			if (respuesta.isError1()) {

				if (instanciaEndPoint.getEstadoError(vo.getCodOpe(), "1") == Boolean.FALSE) {
					instanciaEndPoint.cambiarEstadoError(vo.getCodOpe(), "1", Boolean.TRUE);
					log.info("Problemas URL1 por TimeOut al tratar de ejecutar servicio " + url1 + " (Vordel) en el operador " + vo.getCodOpe());
				}
				if (respuesta.isError2()) {
					instanciaEndPoint.cambiarEstadoError(vo.getCodOpe(), "2", Boolean.TRUE);
					log.info("Problemas URL2 por TimeOut al tratar de ejecutar servicio " + url2 + " (" + instanciaEndPoint.getNombreOperador(vo.getCodOpe()) + ") en el operador " + vo.getCodOpe());
					// System.out.println("Problemas por TimeOut con la licencia
					// "+vo.getNumImpre()+" al tratar de ejecutar servicio
					// "+url2+" en el operador "+vo.getCodOpe());
				} else {
					urlUtilizada = url2;
					tiempoUtilizado = respuesta.getTiempo2();
				}
			} else {
				urlUtilizada = url1;
				tiempoUtilizado = respuesta.getTiempo1();
			}

			if (respuesta.isError1() && respuesta.isError2()) {
				instanciaEndPoint.cambiarEstadoError(vo.getCodOpe(), Boolean.TRUE);
				return;
			}
			/** *************************************************** */

			// REINJECT
			LicenciaType[] licencias = respuesta.getListaLicencias();
			int n41 = 0, n51 = 0, nX1 = 0;
			BigInteger numLic = null;
			List licReinject = new ArrayList();
			if (reinject) {
				if (null != licencias && licencias.length > 0) {
					for (int i = 0; i < licencias.length; i++) {
						numLic = licencias[i].getNumLicencia();
						nX1 = licencias[i].getEstado().intValue();
						for (int j = i + 1; j < licencias.length && (nX1 == 41 || nX1 == 51); j++) {
							if (numLic.equals(licencias[j].getNumLicencia())) {
								if (licencias[j].getEstado().intValue() == 41)
									++n41;
								if (licencias[j].getEstado().intValue() == 51)
									++n51;
							}
						}
						if (n41 > 0 || n51 > 0) {
							licReinject.add(numLic);
							// log("REINYECCION:"+numLic);
						}
						n41 = 0;
						n51 = 0;
					}
				}

				if (licReinject.size() > 0) {
					for (Iterator iterator = licReinject.iterator(); iterator.hasNext();) {
						BigInteger lic = (BigInteger) iterator.next();
						log.info("REINYECCION:: IDEOPE=" + vo.getIdeOpe().toString() + " NUMLIC=" + lic.toString());
						Map h = new HashMap();
						h.put("IDEOPE", vo.getIdeOpe().toString());
						h.put("NUMIMPRE", lic.toString());
						svc_b.deleteIlfe002R(h);
						svc_b.deleteIlfe003R(h);
						svc_b.deleteIlfe004R(h);
						svc_b.deleteIlfe005R(h);
						svc_a.deleteIlfe051(h); // 13-02-2012

					}
				}
			}

			int numerolicencias = 0;
			if (null != licencias && licencias.length > 0) {
				numerolicencias = licencias.length;
				for (int i = 0; i < licencias.length; i++) {
					LicenciaType estadoLicencia = licencias[i];
					Ilfe000VO ilfe000VO = new Ilfe000VO();
					ilfe000VO.setIdeOpe(vo.getIdeOpe());
					ilfe000VO.setNumLicencia(Integer.valueOf(estadoLicencia.getNumLicencia().toString()));
					ilfe000VO.setDigLicencia(estadoLicencia.getDigLicencia());

					// REQ-8000001332 - Desplazamiento de hora de licencia
					//System.out.println("Fecha estado: antes" +Util.obtieneSoloFecha(estadoLicencia.getFecha().getTime()) + " (-) " + Util.obtieneSoloHora(estadoLicencia.getFecha().getTime()));
					estadoLicencia.getFecha().add(Calendar.HOUR, desplazamientResp);
					//System.out.println("Fecha estado: despues" +Util.obtieneSoloFecha(estadoLicencia.getFecha().getTime()) + " (-) " + Util.obtieneSoloHora(estadoLicencia.getFecha().getTime()));
					ilfe000VO.setHoraEstado(Util.obtieneSoloHora(estadoLicencia.getFecha().getTime())); 
					// obtiene hora del estado Agregado 02 - 10 - 2014
					ilfe000VO.setFechaEstado(Util.obtieneSoloFecha(estadoLicencia.getFecha().getTime())); 
					// obtiene fecha del estado Agregado 02 - 10 - 2014

					// System.out.println ("operador
					// >>>>>>>>>>>>>>>>>
					// "+estadoLicencia.getNumLicencia().toString()+")
					// fecha-->
					// "+sdf.format(estadoLicencia.getFecha().getTime())+" -
					// "+shf.format(estadoLicencia.getFecha().getTime()));
					// System.out.println ("Capa
					// Int.>>>>>>>>>>>>>>>>>
					// "+estadoLicencia.getNumLicencia().toString()+")
					// fecha--> "+sdf.format(now)+" - "+shf.format(now));

					ilfe000VO.setFechaConsulta(sdf.format(desde));
					ilfe000VO.setHora(shf.format(desde));

					ilfe000VO.setEstado(estadoLicencia.getEstado().toString().trim());
					ilfe000VO.setMarcons(String.valueOf(0));
					ilfe000VO.setCodOpe(vo.getCodOpe().trim());
					ilfe000VO.setCodCcaf(vo.getCodCcaf().trim());
					ilfe000VO.setPwdCcaf(vo.getPwdCcaf().trim());
					if (null != dateLcc)
						lmeListAdd(ilfe000VO);// lmeList.add(ilfe000VO);

					// log(
					// "Ilfe000 ideOpe={0}, licencia={1}-{2}, fechaConsulta={3}, estado={4}"
					// , new Object[] { ilfe000VO.getIdeOpe(),
					// ilfe000VO.getNumLicencia().toString(),
					// ilfe000VO.getDigLicencia(),
					// ilfe000VO.getFechaConsulta(), ilfe000VO.getEstado() });

					// se aplica validacion a licencias existenes en ilfe000
					// if (svc.existeEnIlfe000(ilfe000VO)) {
					// log(
					// "Ya existe en Ilfe000 ideOpe={0}, licencia={1}-{2}, fechaConsulta={3}, estado={4}"
					// , new Object[] { ilfe000VO.getIdeOpe(),
					// ilfe000VO.getNumLicencia().toString(),
					// ilfe000VO.getDigLicencia(), ilfe000VO.getFechaConsulta(),
					// ilfe000VO.getEstado() });
					// } else {
					// log(
					// "Se ingresa a Ilfe000 ideOpe={0}, licencia={1}-{2}, fechaConsulta={3}, estado={4}"
					// , new Object[] { ilfe000VO.getIdeOpe(),
					// ilfe000VO.getNumLicencia().toString(),
					// ilfe000VO.getDigLicencia(), ilfe000VO.getFechaConsulta(),
					// ilfe000VO.getEstado() });
					//				
					String respIlfe000 = svc_a.insertIlfe000(ilfe000VO);

					if (!"OK".equals(respIlfe000) || respIlfe000.indexOf("SQL0803") != -1) {
						if (respIlfe000.indexOf("SQL0803") != -1) {
							log.info("Ya existe en Ilfe000 ideOpe=" + ilfe000VO.getIdeOpe() + ", licencia=" + ilfe000VO.getNumLicencia().toString() + "-" + ilfe000VO.getDigLicencia() + ", fechaConsulta=" + ilfe000VO.getFechaConsulta() + ", estado=" + ilfe000VO.getEstado() + ", fechaEstado="
									+ ilfe000VO.getFechaEstado() + ", horaEstado=" + ilfe000VO.getHoraEstado() + ", PK");
						} else {
							log.info("Ya existe en Ilfe000 ideOpe=" + ilfe000VO.getIdeOpe() + ", licencia=" + ilfe000VO.getNumLicencia().toString() + "-" + ilfe000VO.getDigLicencia() + ", fechaConsulta=" + ilfe000VO.getFechaConsulta() + ", estado=" + ilfe000VO.getEstado() + ", fechaEstado="
									+ ilfe000VO.getFechaEstado() + ", horaEstado=" + ilfe000VO.getHoraEstado() + ", " + respIlfe000);
						}
						// log.error("La licencia número [" +
						// ilfe000VO.getNumLicencia() +
						// "] falló al intentar registrar en ILFE008");

					} else {
						log.info("Se ingresa a Ilfe000 ideOpe=" + ilfe000VO.getIdeOpe() + ", licencia=" + ilfe000VO.getNumLicencia().toString() + "-" + ilfe000VO.getDigLicencia() + ", fechaConsulta=" + ilfe000VO.getFechaConsulta() + ", estado=" + ilfe000VO.getEstado() + ", fechaEstado="
								+ ilfe000VO.getFechaEstado() + ", horaEstado=" + ilfe000VO.getHoraEstado());
					}
					// }
				}
				// delete todo ilfe000 que exista en ilfe002R
				int n = svc_b.deleteIlfe000InIlfe002R();
				log.info("Se eliminaron " + n + " registros de la tabla Ilfe000 porque ya existian en ilfe002R");
				
				if(vo.getTipoConsumo().equals("LMEEventFec")){
					//actualizando estadistica Evenfec
					String date = Util.getToday_dd_MM_yyy();
					BdConstants.getInstance().getBitacora_reconsumo().put(date, (numerolicencias-n) + "/" + numerolicencias);
				}

			} else {
				log.info(" OK: No se encontraron nuevos estados de licencia");
			}
			/* ----> */
			log.info("[" + urlUtilizada + "] - cantidad de licencias:" + String.valueOf(numerolicencias) + " DEMORO:" + String.valueOf(tiempoUtilizado) + " Milisegundos");
		} catch (ConfiguracionException e) {
			logError(e);
		} catch (ErrorInvocacionOperadorException e) {
			logError(e);
		} catch (Exception e) {
			// e.printStackTrace();
			logError(e);
		}

	}

	public void lmeListAdd(Ilfe000VO ilfe000VO) {
		boolean b = false;
		Iterator i = lmeList.iterator();
		while (i.hasNext()) {
			Ilfe000VO vo = (Ilfe000VO) i.next();
			if (vo.getCodOpe().equals(ilfe000VO.getCodOpe()) && vo.getNumLicencia().equals(ilfe000VO.getNumLicencia())) {
				b = true;
			}
		}
		if (!b)
			lmeList.add(ilfe000VO);
	}

	public void consumirDetallesLME() {
		proceso = "7";
		startProcess("ConsumirDetallesLME");
		String dataInf = "";

		try {
			List l = svc_b.getLicencias();
			if (!empty(l)) {

				// auxiliar distinto a un codigo de operador
				int aux = -999;
				for (Iterator iter = l.iterator(); iter.hasNext();) {
					IlfeOpeVO lic = (IlfeOpeVO) iter.next();
					// dataInf+="\n::: Licencia: "+lic.getNumLicencia()+", URL:
					// "+lic.getUrlOpe();

					/** ************************************** */
					/*
					 * la primera vez pasa correctamente el codigo de operador y
					 * para las otras opciones se valida si el operador tenia
					 * errores y se salta al siguiente ciclo del for
					 */
					int codOpeInt = Integer.parseInt(lic.getCodOpe());
					if (aux == codOpeInt) {
						Boolean errorTotal;
						try {
							errorTotal = EndPointUtil.getInstance().getEstadoError(lic.getCodOpe());
							if (errorTotal != null && errorTotal == Boolean.TRUE) {
								// System.out.println("tiene error en los dos
								// endpoint");
								continue;
							}
						} catch (Exception e2) {
							// e2.printStackTrace();
							logError(e2);
						}
					} else {
						aux = codOpeInt;
					}

					/** ************************************** */

					operador = lic.getCodOpe();
					try {
						consumirDetalleLME(lic, "DETALLES");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				dataInf = "Licencias[" + l.size() + "]";
			} else {
				dataInf = "No se encontraron licencias para procesar";
			}

		} catch (Throwable e) {
			logError(e);
		}
		operador = "";
		endProcess("ConsumirDetallesLME", dataInf);
	}

	/**
	 * @param l
	 * @return
	 */
	private boolean empty(List l) {
		return null == l || l.size() == 0;
	}

	/**
	 * @param lic
	 */
	public LabelValueVO consumirDetalleLME(IlfeOpeVO lic, String origen) {

		String flg = "-1";
		String error = "OK";
		String numimpre = "";

		// UrlBorderVO urlVO = new UrlBorderVO();
		// urlVO.setCodOpe(lic.getCodOpe());
		numimpre = lic.getNumImpre();
		if(numimpre==null){
			numimpre= lic.getNumLicencia();
		}
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

			// ServiciosMultiOperador servicio = new
			// ServiciosMultiOperador(lic.getCodOpe().trim(), TIPO_INSTITUCION,
			// lic.getCodCcaf().trim(), lic.getPwdCcaf().trim(), urlOpe);
			ServiciosMultiOperador servicio = new ServiciosMultiOperador(lic.getCodOpe().trim(), TIPO_INSTITUCION, lic.getCodCcaf().trim(), lic.getPwdCcaf().trim(), url1, url2);

			// RespuestaDetalleLicencia respuesta =
			//servicio.consultaDetalleLicencia(toBigInteger(lic.getNumLicencia()
			// ),
			// lic.getDigLicencia());
			// ejecuta el nuevo servicio que maneja el timeout y utiliza la dos
			// url enviadas
			SalidaLMEDetLcc respuesta = servicio.consultaDetalleLicencia2(toBigInteger(lic.getNumLicencia()), lic.getDigLicencia());

			/** **************************************************** */
			// se asignan errores de timeout segun la respuesta del servico y se
			// guarda el tiempo de demoro del servicio que funciona
			// correctamente
			if (respuesta.isError1()) {

				if (instanciaEndPoint.getEstadoError(lic.getCodOpe(), "1") == Boolean.FALSE) {
					instanciaEndPoint.cambiarEstadoError(lic.getCodOpe(), "1", Boolean.TRUE);
					log.info("Problemas por TimeOut con la licencia " + lic.getNumLicencia() + " al tratar de ejecutar servicio " + url1 + " (Vordel) en el operador " + lic.getCodOpe());
				}
				if (respuesta.isError2()) {
					instanciaEndPoint.cambiarEstadoError(lic.getCodOpe(), "2", Boolean.TRUE);
					log.info("Problemas por TimeOut con la licencia " + lic.getNumLicencia() + " al tratar de ejecutar servicio " + url2 + " (" + instanciaEndPoint.getNombreOperador(lic.getCodOpe()) + ") en el operador " + lic.getCodOpe());
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
				return new LabelValueVO("error en ambos endpoint con operador " + lic.getCodOpe(), "-1");
			}
			/** *************************************************** */

			if (null != respuesta && respuesta.getRespuesta() != null && null != respuesta.getRespuesta().getZonaA() && null != respuesta.getRespuesta().getZonaA().getZONAA1() && null != respuesta.getRespuesta().getZonaA().getZONAA1().getTrabajador()
					&& null != respuesta.getRespuesta().getZonaA().getZONAA1().getTrabajador().getRut()) {
				CTEstado[] listaEstado = respuesta.getRespuesta().getZona0().getZONA01().getEstadoArray();

				// busca ultimo estado
				int ultimoEstado = 0;
				Date fechaUtimoEstado = null;
				
				if(lic.getUltimoEstado().equals("72")){
					Date fechaUtimoEstado72 = null;
					for (int i = 0; i < listaEstado.length; i++) {
						if (fechaUtimoEstado == null) {
							ultimoEstado = listaEstado[i].getEstadoLicencia().intValue();
							fechaUtimoEstado = listaEstado[i].getFechaEstado().getTime();

						}
						//System.out.println("Compararación=" + fechaUtimoEstado.compareTo(listaEstado[i].getFechaEstado().getTime()));
						//System.out.println("Estado= " + listaEstado[i].getEstadoLicencia().intValue());
						//System.out.println("Fecha= " + listaEstado[i].getFechaEstado().getTime());

						//if (fechaUtimoEstado.compareTo(listaEstado[i].getFechaEstado().getTime()) < 0) {
						if (  listaEstado[i].getEstadoLicencia().intValue()==72)  {
							// actualizo estado
							ultimoEstado = listaEstado[i].getEstadoLicencia().intValue();
							fechaUtimoEstado = listaEstado[i].getFechaEstado().getTime();
							if (fechaUtimoEstado72!=null && fechaUtimoEstado72.compareTo(listaEstado[i].getFechaEstado().getTime()) > 0){
								fechaUtimoEstado = fechaUtimoEstado72;
							}
							if(ultimoEstado==72){
								fechaUtimoEstado72= fechaUtimoEstado;
							}
						}
					}
				}else{
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
				}
				
				// separa fecha del ultimo estado en fecha y hora.
				String soloFecha = Util.obtieneSoloFechaDate(fechaUtimoEstado);
				String soloHora = Util.obtieneSoloHoraDate(fechaUtimoEstado);

				lic.setFechaEstado(soloFecha);
				lic.setHoraEstado(soloHora);

				String AfiRut = respuesta.getRespuesta().getZonaA().getZONAA1().getTrabajador().getRut();
				String RutAux = AfiRut;
				AfiRut = separaNumDigRut(RutAux, "N");
				// ultimoEstado=11;

				// System.out.println("numimpre: " + numimpre);
				// System.out.println("Ultimo Estado: " + ultimoEstado);

				if (consumoValidoLME(lic, ultimoEstado)) {

					if (origen.equals("MIXTA") && (ultimoEstado == 1 || ultimoEstado == 11 || ultimoEstado == 12)) {

						if (consumoInverso(lic, AfiRut)) {
							if (ultimoEstado == 11 || ultimoEstado == 12) {
								int estado = 1;

								if (consumoValidoLME(lic, estado)) {
									consumoLicenciasLME(respuesta.getRespuesta(), lic, ultimoEstado, soloFecha, soloHora);
									flg = "1";
								} else {
									log.info("[" + urlUtilizada + "] - Licencia: " + lic.getNumLicencia().toString() + " con estado: " + String.valueOf(ultimoEstado) + ", existe con estado 1 en el core. DEMORA " + String.valueOf(tiempoUtilizado));
									flg = "-2";

								}

							} else {
								consumoLicenciasLME(respuesta.getRespuesta(), lic, ultimoEstado, soloFecha, soloHora);
								flg = "1";
							}

						} else {
							log.info("[" + urlUtilizada + "] - Licencia " + lic.getNumLicencia().toString() + " con estado: " + String.valueOf(ultimoEstado) + ", no existe con estado 0 en el core. DEMORA " + String.valueOf(tiempoUtilizado));
							flg = "-2";
						}

					} else {
						//Consumo particulares o Detalle de Estados Nuevos
						consumoLicenciasLME(respuesta.getRespuesta(), lic, ultimoEstado, soloFecha, soloHora);
						flg = "1";
					}

					// svc.updateIlfe000(h);

				}else{
					log.info("[" + urlUtilizada + "] - Licencia " + lic.getNumLicencia().toString() + " con estado: " + String.valueOf(ultimoEstado) + ", ya existe en el core. DEMORA " + String.valueOf(tiempoUtilizado));
					flg = "-2";
				}

			} else {
				// licencia sin zona A, se deberá tratar de cargar nuevamente
				flg = "-1";
			}

		} catch (ErrorInvocacionOperadorException e) {
			flg = "-1";
			logError(e);
			error = dataTruncation(e.getCause().getMessage(), 50);
		} catch (ErrorRespuestaOperadorException e) {
			flg = "-2";
			logError(e);
			error = dataTruncation(e.getCause().getMessage(), 50);
		} catch (Exception e) {
			flg = "-2";
			logError(e);
			error = dataTruncation(e.getCause().getMessage(), 50);
		}
		// System.out.println("la error 1 (error) es: " + error);
		return new LabelValueVO(error, flg);
	}


	private void consumoLicenciasLME(RespuestaDetalleLicencia respuesta, IlfeOpeVO lic, int ultimoEstado, String fechaUltEst, String horaUltEst) {

		int agnoImagen = 1900;
		int mesImagen = 0;

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
		AFIRUT = set(respuesta.getZonaA().getZONAA1().getTrabajador().getRut()); // Rut
		// Afiliado.

		/*
		 * Uso de método llamado "separaNumDigRut" para separar el rut del
		 * afiliado en cuerpo y dígito verificador.
		 */
		String rut = AFIRUT;
		AFIRUT = separaNumDigRut(rut, "N");
		AFIRUTDV = separaNumDigRut(rut, "D");

		String ADSCRITO = set(respuesta.getZona0().getZONA01().getEmpleadorAdscrito()); // Empleador
																						// Adscrito
		String CODTIPSU = set(respuesta.getZona0().getZONA01().getCodigoEntidad()); // Entidad
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
				AFIRUT = set(respuesta.getZonaA().getZONAA1().getTrabajador().getRut()); // Rut
																							// Afiliado
																							// .
				AFIRUTDV = "";

				rut = AFIRUT;
				AFIRUT = separaNumDigRut(rut, "N");
				AFIRUTDV = separaNumDigRut(rut, "D");

				ESTLICEN = set(String.valueOf(listaEstado[i].getEstadoLicencia()));
				String FechaHora = String.valueOf(listaEstado[i].getFechaEstado());
				MOTNREC = null == listaEstado[i].getMotivoNorecepcion() ? "" : set(Util.decodeUTF8(String.valueOf(listaEstado[i].getMotivoNorecepcion())));
				FECTERREL = null == listaEstado[i].getFechaTerminoRelacion() ? "19000101" : set(listaEstado[i].getFechaTerminoRelacion());
				RUTEMPLE = set(listaEstado[i].getEmpRut());
				CODCCAF = set(String.valueOf(listaEstado[i].getCodigoTramitacionCCAF()));
				MOTDEVOL = null == listaEstado[i].getMotivoDevolucionCCAF() ? "" : set(Util.decodeUTF8(String.valueOf(listaEstado[i].getMotivoDevolucionCCAF())));
				try {
					TIPOLIQ = null == listaEstado[i].getTipoLiquidacionCCAF() ? "0" : set(listaEstado[i].getTipoLiquidacionCCAF());
				} catch (Exception e) {
					// TODO Bloque catch generado automáticamente
					TIPOLIQ="0";
				}

				if (RUTEMPLE == "0-")
					RUTEMPLE = "0-0";

				/*
				 * Uso de método llamado "separaNumDigRut" para separar el rut
				 * del afiliado en cuerpo y dígito verificador.
				 */
				rut = RUTEMPLE;
				RUTEMPLE = separaNumDigRut(rut, "N");
				DIGEMPLE = separaNumDigRut(rut, "D");

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

		// Zona A
		//----------------------------------------------------------------------
		// -----------------
		RUTEMPLE = "";
		DIGEMPLE = "";

		AFIRUT = set(respuesta.getZonaA().getZONAA1().getTrabajador().getRut());
		AFIRUTDV = "";

		String APAAFI = set(Util.decodeUTF8(respuesta.getZonaA().getZONAA1().getTrabajador().getApellidoPaterno()));
		APAAFI = dataTruncation(APAAFI, 30);
		String AMAAFI = set(Util.decodeUTF8(respuesta.getZonaA().getZONAA1().getTrabajador().getApellidoMaterno()));
		AMAAFI = dataTruncation(AMAAFI, 30);
		String NOMAFI = set(Util.decodeUTF8(respuesta.getZonaA().getZONAA1().getTrabajador().getNombres()));
		NOMAFI = dataTruncation(NOMAFI, 30);
		String FECEMILI = null == respuesta.getZonaA().getZONAA1() ? "19000101" : set(respuesta.getZonaA().getZONAA1().getFechaEmision());
		String FECINIRE = null == respuesta.getZonaA().getZONAA1() ? "19000101" : set(respuesta.getZonaA().getZONAA1().getFechaInicioReposo());

		/*
		 * inicio modificación 29-04-2014 modificado por lvc, idenificar fechas
		 * mal formateadas
		 */
		try {
			sdf.parse(FECINIRE);
		} catch (ParseException e) {
			// TODO Bloque catch generado automáticamente
			// e.printStackTrace();
			logError(e);
			log.info("::: Al realizar el consumo de la licencia Licencia[" + lic.getNumLicencia() + "] - Estado[" + String.valueOf(ultimoEstado) + "] se produjo un problema por el formato de la ( Fecha de Inicio Reposo Fecha[" + FECINIRE + "] ), no insertaremos esta licencia");
			return;
		}
		/* fin modificación 29-04-2014 */

		String AFIEDAD = null == respuesta.getZonaA().getZONAA1() ? "0" : set(respuesta.getZonaA().getZONAA1().getTrabajador().getEdad());
		AFIEDAD = dataTruncation(AFIEDAD, 2);
		String AFISEXO = null == respuesta.getZonaA().getZONAA1() ? "" : respuesta.getZonaA().getZONAA1().getTrabajador().getSexo().toString();
		AFISEXO = dataTruncation(AFISEXO, 1);
		String AFIEMAIL = dataTruncation(set(respuesta.getZonaA().getZONAAC().getEmailTrabajador()), 40);
		String NUMDIALI = set(respuesta.getZonaA().getZONAA1().getTraNdias());
		NUMDIALI = dataTruncation(NUMDIALI, 2);
		String RUTHIJO = null == respuesta.getZonaA().getZONAA2() || null == respuesta.getZonaA().getZONAA2().getHijo() ? "0" : set(respuesta.getZonaA().getZONAA2().getHijo().getRut());
		String DIVHIJO = "0";
		String APAHIJO = null == respuesta.getZonaA().getZONAA2() || null == respuesta.getZonaA().getZONAA2().getHijo() ? "" : set(Util.decodeUTF8(respuesta.getZonaA().getZONAA2().getHijo().getApellidoPaterno()));
		APAHIJO = dataTruncation(APAHIJO, 30);
		String AMAHIJO = null == respuesta.getZonaA().getZONAA2() || null == respuesta.getZonaA().getZONAA2().getHijo() ? "" : set(Util.decodeUTF8(respuesta.getZonaA().getZONAA2().getHijo().getApellidoMaterno()));
		AMAHIJO = dataTruncation(AMAHIJO, 30);
		String NOMHIJO = null == respuesta.getZonaA().getZONAA2() || null == respuesta.getZonaA().getZONAA2().getHijo() ? "" : set(Util.decodeUTF8(respuesta.getZonaA().getZONAA2().getHijo().getNombres()));
		NOMHIJO = dataTruncation(NOMHIJO, 30);
		String FECNACHI = null == respuesta.getZonaA().getZONAA2() || null == respuesta.getZonaA().getZONAA2().getHijo() ? "19000101" : set(respuesta.getZonaA().getZONAA2().getHijo().getFechaNacimiento());// 19000101
		String TIPLICEN = null == respuesta.getZonaA().getZONAA3() ? "" : set(respuesta.getZonaA().getZONAA3().getCodigoTipoLicencia());
		TIPLICEN = dataTruncation(TIPLICEN, 2);
		String RECUPERA = null == respuesta.getZonaA().getZONAA3() ? "" : set(respuesta.getZonaA().getZONAA3().getCodigoRecuperabilidad());
		RECUPERA = dataTruncation(RECUPERA, 2);
		String INITRAMI = null == respuesta.getZonaA().getZONAA3() ? "" : set(respuesta.getZonaA().getZONAA3().getCodigoInicioTramInv());
		INITRAMI = dataTruncation(INITRAMI, 2);
		String FECACCID = null == respuesta.getZonaA().getZONAA3() ? "" : set(respuesta.getZonaA().getZONAA3().getFechaAccidente());
		String TRAYECTO = null == respuesta.getZonaA().getZONAA3() ? "" : set(respuesta.getZonaA().getZONAA3().getCodigoTrayecto());
		TRAYECTO = dataTruncation(TRAYECTO, 2);
		String FECCONCE = null == respuesta.getZonaA().getZONAA3() ? "" : set(respuesta.getZonaA().getZONAA3().getFechaConcepcion());
		String TIPREPOS = null == respuesta.getZonaA().getZONAA3() ? "" : set(respuesta.getZonaA().getZONAA4().getCodigoTipoReposo());
		TIPREPOS = dataTruncation(TIPREPOS, 2);

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
				LUGREPOP = set(lugarReposo[i].getCodigoLugarReposo());
				LUGREPOP = dataTruncation(LUGREPOP, 2);
				JUSSIOTR = set(lugarReposo[i].getJustificaDomicilio());
				JUSSIOTR = dataTruncation(JUSSIOTR, 128);
				DIRREP01 = set(Util.decodeUTF8(lugarReposo[i].getDireccionReposo().getCalle()));
				DIRREP01 = dataTruncation(DIRREP01, 100);
				int poscom= lugarReposo[i].toString().indexOf("urn:comuna");
				String comuna= lugarReposo[i].toString().substring(poscom+11, poscom+16);
				CODCOM01 = comuna;
				//CODCOM01 = lugarReposo[i].getDireccionReposo().getComuna().toString();
				CODCOM01 = dataTruncation(CODCOM01, 5);
				//GLOCOM01 = lugarReposo[i].getDireccionReposo().getComuna().toString();
				GLOCOM01 = comuna;
				GLOCOM01 = dataTruncation(GLOCOM01, 30);
			} else {
				LUGREP02 = lugarReposo[i].getCodigoLugarReposo().toString();
				LUGREP02 = dataTruncation(LUGREP02, 2);
				DIRREP02 = set(lugarReposo[i].getDireccionReposo().getCalle());
				DIRREP02 = dataTruncation(DIRREP02, 100);
				int poscom= lugarReposo[i].toString().indexOf("urn:comuna");
				String comuna= lugarReposo[i].toString().substring(poscom+11, poscom+16);
				CODCOM02 = comuna;
				//CODCOM02 = lugarReposo[i].getDireccionReposo().getComuna().toString();
				CODCOM02 = dataTruncation(CODCOM02, 5);
			}
		}

		CTTelefono[] telefono = respuesta.getZonaA().getZONAA4().getTelefonoReposoArray();
		for (int i = 0; telefono != null && i < telefono.length; i++) {
			if (i == 0) {
				TELEFO01 = dataTruncation(set(telefono[i].getTelefono()), 12);
			} else {
				TELEFO02 = dataTruncation(set(telefono[i].getTelefono()), 12);
			}
		}
		String RUTPROFE = set(respuesta.getZonaA().getZONAA5().getProfesional().getRut());
		String DIVPROFE = "0";
		String APAPROFE = set(Util.decodeUTF8(respuesta.getZonaA().getZONAA5().getProfesional().getApellidoPaterno()));
		APAPROFE = dataTruncation(APAPROFE, 30);
		String AMAPROFE = set(Util.decodeUTF8(respuesta.getZonaA().getZONAA5().getProfesional().getApellidoMaterno()));
		AMAPROFE = dataTruncation(AMAPROFE, 30);
		String NOMPROFE = set(Util.decodeUTF8(respuesta.getZonaA().getZONAA5().getProfesional().getNombres()));
		NOMPROFE = dataTruncation(NOMPROFE, 30);
		String DIRPROFE = set(Util.decodeUTF8(respuesta.getZonaA().getZONAA5().getProfDireccion().getCalle()));
		DIRPROFE = dataTruncation(DIRPROFE, 100);
		int poscom= respuesta.getZonaA().getZONAA5().getProfDireccion().toString().indexOf("urn:comuna");
		String comuna= respuesta.getZonaA().getZONAA5().getProfDireccion().toString().substring(poscom+11, poscom+16);
		//String CODCOMPR = respuesta.getZonaA().getZONAA5().getProfDireccion().getComuna().toString();
		String CODCOMPR = comuna;
		CODCOMPR = dataTruncation(CODCOMPR, 5);
		String GLOCOMPR = "";
		String FONPROFE = set(respuesta.getZonaA().getZONAA5().getProfTelefono().getTelefono());
		FONPROFE = dataTruncation(FONPROFE, 12);
		String FAXPROFE = respuesta.getZonaA().getZONAA5().getProfFax() == null ? "0" : set(respuesta.getZonaA().getZONAA5().getProfFax().getTelefono());
		FAXPROFE = dataTruncation(FAXPROFE, 12);
		String EMAPROFE = set(respuesta.getZonaA().getZONAA5().getProfEmail());
		EMAPROFE = dataTruncation(EMAPROFE, 40);
		String GLOESPEC = set(Util.decodeUTF8(respuesta.getZonaA().getZONAA5().getProfEspecialidad()));
		GLOESPEC = dataTruncation(GLOESPEC, 80);
		String CODESPEC = set(respuesta.getZonaA().getZONAA5().getCodigoTipoProfesional());
		String TIPPREST = "0";
		String NROCOMED = set(respuesta.getZonaA().getZONAA5().getProfRegistroColegio());
		NROCOMED = dataTruncation(NROCOMED, 32);
		String CODDIAPR = dataTruncation(set(respuesta.getZonaA().getZONAAC().getCoddiagnosticoPrincipal()), 512);
		String GLODIAPR = dataTruncation(set(respuesta.getZonaA().getZONAA6().getDiagnosticoPrincipal()), 512);
		String CODDIASE = set(respuesta.getZonaA().getZONAAC().getCoddiagnosticoSecundario());
		CODDIASE = dataTruncation(CODDIASE, 12);
		String GLODIASE = dataTruncation(set(respuesta.getZonaA().getZONAAC().getDiagnosticoSecundario()), 511);
		String CODDIAOT = set(respuesta.getZonaA().getZONAAC().getCoddiagnosticoOtro());
		CODDIAOT = dataTruncation(CODDIAOT, 12);
		String GLODIAOT = dataTruncation(set(respuesta.getZonaA().getZONAA6().getDiagnosticoOtro()), 511);
		String ANTECCLI = dataTruncation(set(respuesta.getZonaA().getZONAA6().getAntecedentesClinicos()), 511);
		String EXAAPOYO = dataTruncation(set(respuesta.getZonaA().getZONAA6().getExamenesApoyo()), 511);

		rut = AFIRUT;
		AFIRUT = separaNumDigRut(rut, "N");
		AFIRUTDV = separaNumDigRut(rut, "D");

		rut = RUTHIJO.trim();
		if (rut == "")
			rut = "0-0";

		RUTHIJO = separaNumDigRut(rut, "N");
		DIVHIJO = separaNumDigRut(rut, "D");

		rut = RUTPROFE;
		RUTPROFE = separaNumDigRut(rut, "N");
		DIVPROFE = separaNumDigRut(rut, "D");

		/* Aqui estaba la validacion del estado 63 , cambio realizado 27082012 */

		h = new Hashtable();
		h.put("ideope", lic.getIdeOpe());
		h.put("numLicencia", lic.getNumLicencia());
		h.put("digLicencia", lic.getDigLicencia());
		h.put("ultimoEstado", String.valueOf(ultimoEstado));
		h.put("horaUltEstado", horaUltEst); // agregado 02-10-2014 REC-7021
											// fase3
		h.put("fechaUltEstado", fechaUltEst); // agregado 02-10-2014 REC-7021
												// fase3
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
				log.error("La licencia número [" + lic.getNumLicencia() + "] con estado [" + ultimoEstado + "] falló al intentar registrar en ILFE002, PK");
			} else {
				log.error("La licencia número [" + lic.getNumLicencia() + "] con estado [" + ultimoEstado + "] falló al intentar registrar en ILFE002, " + valueIlfe002);

			}
			// labelValueVO.setValue(valueIlfe002);
			// labelValueVO.setLabel("Ilfe002");
		} else {
			log.info("La licencia número [" + lic.getNumLicencia() + "] con estado [" + ultimoEstado + "] se registró exitosamente en ILFE002");
		}

		// Zona B
		// ---------------------------------------------------------------------
		if (null != respuesta.getZonaB()) {
			String NRORESOL = "";
			String CODCAURE;
			String DERSUBSI;
			String CIE10;
			String JORREPAU;
			String TIPREPAU;
			String IDECOMPI = "0";
			String TIPFALLO;
			String NOMBRECO;
			String REGCOLME;
			String RUTCONTR;
			String DIASPREV;
			String FECAUTDE;
			String FECAUTHA;
			String FECREPFI;
			String DIASAUT;
			String PENDXOUT = "";
			String FECREDIC;
			String FECRESFI;
			String CODCONTI;
			String CODESTAB;
			String CODREDIC;
			String ENTIDAD;
			String ESTABLEC;

			CTResolucion[] resolucions = respuesta.getZonaB().getZONAB1().getResolucionArray();
			// for (int i=0; i < resolucions.length; i++) {
			int i = resolucions.length - 1;
			NRORESOL = set(resolucions[i].getNResolucion());
			NRORESOL = dataTruncation(NRORESOL, 15);
			CODCAURE = set(resolucions[i].getCodigoCausaRechazo());
			CODCAURE = dataTruncation(CODCAURE, 2);
			DERSUBSI = resolucions[i].getCodigoDerechoSubsidio().toString();
			DERSUBSI = dataTruncation(DERSUBSI, 1);
			CIE10 = set(resolucions[i].getCodigoDiagnostico());
			CIE10 = dataTruncation(CIE10, 12);

			// STJornadaReposo.Enum codigoJornadaReposoAutorizado =
			// resolucions[i].getCodigoJornadaReposoAutorizadoArray(0)
			JORREPAU = "";

			TIPREPAU = set(resolucions[i].getCodigoReposoAutorizado());
			TIPREPAU = dataTruncation(TIPREPAU, 2);
			IDECOMPI = set(resolucions[i].getCodigoTipoLicenciaEntidad());
			IDECOMPI = dataTruncation(IDECOMPI, 2);
			TIPFALLO = set(resolucions[i].getCodigoTipoResolucion());
			TIPFALLO = dataTruncation(TIPFALLO, 4);
			NOMBRECO = set(Util.decodeUTF8(resolucions[i].getContralorNombre()));
			NOMBRECO = dataTruncation(NOMBRECO, 80);
			REGCOLME = set(resolucions[i].getContralorRegistroColegio());
			REGCOLME = dataTruncation(REGCOLME, 20);

			RUTCONTR = resolucions[i].getContralorRut();
			RUTCONTR = dataTruncation(RUTCONTR, 12);
			DIASPREV = set(resolucions[i].getDiasPrevios());
			DIASPREV = dataTruncation(DIASPREV, 4);
			FECAUTDE = set(resolucions[i].getEntidadFechaDesde());
			FECAUTHA = set(resolucions[i].getEntidadFechaHasta());
			FECREPFI = set(resolucions[i].getEntidadFechaRecepcion());
			DIASAUT = set(resolucions[i].getEntidadNdias());
			DIASAUT = dataTruncation(DIASAUT, 4);
			PENDXOUT = set(resolucions[i].getEntidadPendiente());
			PENDXOUT = dataTruncation(PENDXOUT, 60);
			FECREDIC = set(resolucions[i].getFechaRedictamen());
			FECRESFI = set(resolucions[i].getFechaResolucion());

			CODCONTI = set(resolucions[i].getCodigoContinuacion());
			CODCONTI = dataTruncation(CODCONTI, 7);
			CODESTAB = set(resolucions[i].getCodigoEstablecimiento());
			CODESTAB = dataTruncation(CODESTAB, 7);
			CODREDIC = set(resolucions[i].getCodigoRedictamen());
			CODREDIC = dataTruncation(CODREDIC, 7);
			ENTIDAD = set(resolucions[i].getEntidad());
			ENTIDAD = dataTruncation(ENTIDAD, 30);
			ESTABLEC = set(resolucions[i].getEstablecimiento());
			ESTABLEC = dataTruncation(ESTABLEC, 30);

			h = new Hashtable();
			h.put("ideope", lic.getIdeOpe());
			h.put("numLicencia", lic.getNumLicencia());
			h.put("ultimoEstado", String.valueOf(ultimoEstado));
			h.put("horaUltEstado", horaUltEst); // agregado 02-10-2014 REC-7021
												// fase3
			h.put("fechaUltEstado", fechaUltEst); // agregado 02-10-2014
													// REC-7021 fase3
			h.put("AFIRUT", AFIRUT);
			h.put("AFIRUTDV", AFIRUTDV);
			h.put("DIASAUT", DIASAUT);
			h.put("FECAUTDE", FECAUTDE);
			h.put("FECAUTHA", FECAUTHA);
			h.put("DIASPREV", DIASPREV);
			h.put("TIPREPAU", TIPREPAU);
			h.put("DERSUBSI", DERSUBSI);
			h.put("FECREPFI", FECREPFI);
			h.put("FECRESFI", FECRESFI);
			h.put("CODCAURE", CODCAURE);
			h.put("TIPFALLO", TIPFALLO);
			h.put("IDECOMPI", IDECOMPI);
			h.put("NRORESOL", NRORESOL);
			h.put("PENDXOUT", PENDXOUT);
			h.put("FECREDIC", FECREDIC);
			h.put("CIE10", CIE10);
			h.put("RUTCONTR", RUTCONTR);
			h.put("NOMBRECO", NOMBRECO);
			h.put("REGCOLME", REGCOLME);
			h.put("CODCONTI", CODCONTI);
			h.put("CODESTAB", CODESTAB);
			h.put("CODREDIC", CODREDIC);
			h.put("ENTIDAD", ENTIDAD);
			h.put("ESTABLEC", ESTABLEC);
			h.put("JORREPAU", JORREPAU);

			// System.out.println(
			// "##################################################################################################################"
			// );
			//System.out.println("String valueIlfe003 = svc.insertIlfe003(h);");
			// System.out.println(
			// "##################################################################################################################"
			// );

			String valueIlfe003 = svc_a.insertIlfe003(h);
			if (!"OK".equals(valueIlfe003) || valueIlfe003.indexOf("SQL0803") != -1) {
				// labelValueVO.setValue(valueIlfe003);
				// labelValueVO.setLabel("Ilfe003");
				if (valueIlfe003.indexOf("SQL0803") != -1) {
					log.error("La licencia número [" + lic.getNumLicencia() + "] con estado [" + ultimoEstado + "] falló al intentar registrar en ILFE003, PK");
				} else {
					log.error("La licencia número [" + lic.getNumLicencia() + "] con estado [" + ultimoEstado + "] falló al intentar registrar en ILFE003, " + valueIlfe003);
				}
			} else {
				log.info("La licencia número [" + lic.getNumLicencia() + "] con estado [" + ultimoEstado + "] se registró exitosamente en ILFE003");
			}

		}
		// Zona C
		//----------------------------------------------------------------------
		// -----------------
		if (null != respuesta.getZonaC()) {
			RUTEMPLE = respuesta.getZonaC().getZONAC1().getEmpRut();// "0-0"
			if (RUTEMPLE == "0-")
				RUTEMPLE = "0-0";

			DIGEMPLE = "";
			String NOMEMPLE = dataTruncation(Util.decodeUTF8(respuesta.getZonaC().getZONAC1().getEmpNombre()), 255);
			String APAEMPLE = "";
			String AMAEMPLE = "";
			String TIPEMPLE = "0";
			String DIREMPLE = respuesta.getZonaC().getZONAC1().getEmpDireccion() == null ? "" : Util.decodeUTF8(respuesta.getZonaC().getZONAC1().getEmpDireccion().getCalle());
			DIREMPLE = dataTruncation(DIREMPLE, 100);
			int poscom_ZC= respuesta.getZonaC().getZONAC1().getEmpDireccion().toString().indexOf("urn:comuna");
			String comuna_ZC="";
			if(poscom_ZC>-1){
				comuna_ZC= respuesta.getZonaC().getZONAC1().getEmpDireccion().toString().substring(poscom_ZC+11, poscom_ZC+16);
			}
			//String COMEMPLE = respuesta.getZonaC().getZONAC1().getEmpDireccion() == null ? "" : String.valueOf(respuesta.getZonaC().getZONAC1().getEmpDireccion().getComuna());
			String COMEMPLE = comuna_ZC;
			COMEMPLE = dataTruncation(COMEMPLE, 5);
			String GLOCOMEM = "";
			String FONOEMPL = respuesta.getZonaC().getZONAC1().getEmpTelefono() == null ? "0" : set(respuesta.getZonaC().getZONAC1().getEmpTelefono().getTelefono());
			FONOEMPL = dataTruncation(FONOEMPL, 12);
			String EMAIEMPL = "";
			String FECREPEM = set(respuesta.getZonaC().getZONAC1().getEmpFechaRecepcion());// "0" habilitado

			String FECENVEM = "0";
			CODCCAF = null == respuesta.getZonaC().getZONACC() ? "0" : set(respuesta.getZonaC().getZONACC().getCodigoTramitacionCCAF());
			CODCCAF = dataTruncation(CODCCAF, 10); // ERRO LARGO, VALOR: 10105
			poscom_ZC= respuesta.getZonaC().getZONAC1().toString().indexOf("urn:codigo_comuna_compin");
			String comunaCompin_ZC="0";
			if(poscom_ZC>-1){
				comunaCompin_ZC= respuesta.getZonaC().getZONAC1().toString().substring(poscom_ZC+25, poscom_ZC+30);
			}
			
			String CODCOMPI = set(comunaCompin_ZC);
			//String CODCOMPI = set(respuesta.getZonaC().getZONAC1().getCodigoComunaCompin());
			CODCOMPI = dataTruncation(CODCOMPI, 4);
			String CODACTLA = set(respuesta.getZonaC().getZONAC1().getCodigoActividadLaboral());
			CODACTLA = dataTruncation(CODACTLA, 2);
			String CODOCUPA = set(respuesta.getZonaC().getZONAC1().getCodigoOcupacion());
			CODOCUPA = dataTruncation(CODOCUPA, 2);
			String GLOOTROC = "";
			String CODREGPR = set(respuesta.getZonaC().getZONAC2().getCodigoRegimenPrevisional());
			CODREGPR = dataTruncation(CODREGPR, 4); // "0" //trae -1
			String PENSIONA = "0";
			String CODINSPR = set(respuesta.getZonaC().getZONAC2().getCodigoRegimenPrevisional());
			CODINSPR = dataTruncation(CODINSPR, 4); // "0"
			String LETCAJPR = set(respuesta.getZonaC().getZONAC2().getCodigoLetraCaja());
			LETCAJPR = dataTruncation(LETCAJPR, 2);
			String CODCALTR = set(respuesta.getZonaC().getZONAC2().getCodigoCalidadTrabajador());
			CODCALTR = dataTruncation(CODCALTR, 2);
			String AFC = set(respuesta.getZonaC().getZONAC2().getCodigoSeguroAfc());
			AFC = dataTruncation(AFC, 2);
			String NOMAFC = "";
			String CONINDEF = set(respuesta.getZonaC().getZONAC2().getCodigoSeguroIndef());
			CONINDEF = dataTruncation(CONINDEF, 2);
			String CODESTAT = "0";
			String FECAFILI = set(respuesta.getZonaC().getZONAC2().getFechaAfiliacion()); // "0"
																							// /
																							// /
																							// habilitado
			String FECCONTR = set(respuesta.getZonaC().getZONAC2().getFechaContrato()); // "0"
			String PORDESAU = null == respuesta.getZonaC().getZONAC3() ? "0" : set(respuesta.getZonaC().getZONAC3().getPorcenDesahucio());
			PORDESAU = dataTruncation(PORDESAU, 5);
			String RENIMPON = null == respuesta.getZonaC().getZONAC3() ? "0" : set(respuesta.getZonaC().getZONAC3().getMontoImponibleMesAnterior());
			RENIMPON = dataTruncation(RENIMPON, 10);
			CODTIPSU = String.valueOf(respuesta.getZonaC().getZONAC2().getCodigoEntidadPagadora());
			CODTIPSU = dataTruncation(CODTIPSU, 1);
			String MOTRECEM = "";

			rut = RUTEMPLE;
			RUTEMPLE = separaNumDigRut(rut, "N");
			DIGEMPLE = separaNumDigRut(rut, "D");

			h = new Hashtable();
			h.put("ideope", lic.getIdeOpe());
			h.put("numLicencia", lic.getNumLicencia());
			h.put("AFIRUT", AFIRUT);
			h.put("ultimoEstado", String.valueOf(ultimoEstado));
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
					log.error("La licencia número [" + lic.getNumLicencia() + "] con estado [" + ultimoEstado + "] falló al intentar registrar en ILFE004, PK");
				} else {
					log.error("La licencia número [" + lic.getNumLicencia() + "] con estado [" + ultimoEstado + "] falló al intentar registrar en ILFE004, " + valueIlfe004);
				}
				// labelValueVO.setValue(valueIlfe004);
				// labelValueVO.setLabel("Ilfe004");
			} else {
				log.info("La licencia número [" + lic.getNumLicencia() + "] con estado [" + ultimoEstado + "] se registró exitosamente en ILFE004");
			}
		}
		// ZONA C - RENTAS
		//----------------------------------------------------------------------
		// ----------

		String CODINSPR = "0";
		String LETCAJPR = "";
		String PERRENTA = "0";
		String NUMDIATR = "0";
		String REMMUNIM = "0";
		String IMPDESAH = "0";
		String IMPCESAN = "0";
		String SUEBASE = "0";
		String SUBDIAS = "0";
		String SUBMONTO = "0";
		TIPLICEN = null == respuesta.getZonaA() || null == respuesta.getZonaA().getZONAA3() ? "0" : set(respuesta.getZonaA().getZONAA3().getCodigoTipoLicencia());
		TIPLICEN = dataTruncation(TIPLICEN, 2);
		LETCAJPR = null == respuesta.getZonaC() || null == respuesta.getZonaC().getZONAC2() ? "" : set(respuesta.getZonaC().getZONAC2().getCodigoLetraCaja());
		LETCAJPR = dataTruncation(LETCAJPR, 1);
		CODINSPR = "0";
		if (null != respuesta.getZonaC() && null != respuesta.getZonaC().getZONAC3()) {
			CTRemuneracion[] remuneracion = respuesta.getZonaC().getZONAC3().getRemuneracionArray();
			for (int i = 0; i < remuneracion.length; i++) {
				PERRENTA = setPeriodo(remuneracion[i].getAnoMesRemAnt()); // "1900-01"
				NUMDIATR = set(remuneracion[i].getNdiasRemAnt());
				NUMDIATR = dataTruncation(NUMDIATR, 2);
				REMMUNIM = set(remuneracion[i].getMontoImponibleRemAnt());
				REMMUNIM = dataTruncation(REMMUNIM, 10);
				SUEBASE = set(remuneracion[i].getMontoTotalRemAnt());
				SUEBASE = dataTruncation(SUEBASE, 10);
				SUBDIAS = set(remuneracion[i].getNdiasIncapacidadRemAnt());
				SUBDIAS = dataTruncation(SUBDIAS, 4);
				SUBMONTO = set(remuneracion[i].getMontoIncapacidadRemAnt());
				SUBMONTO = dataTruncation(SUBMONTO, 10);

				h = new Hashtable();
				h.put("ideope", lic.getIdeOpe());
				h.put("numLicencia", lic.getNumLicencia());
				h.put("ultimoEstado", String.valueOf(ultimoEstado));
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
						log.error("La licencia número [" + lic.getNumLicencia() + "] con estado [" + ultimoEstado + "] falló al intentar registrar en ILFE005, PK");
					} else {
						log.error("La licencia número [" + lic.getNumLicencia() + "] con estado [" + ultimoEstado + "] falló al intentar registrar en ILFE005, " + valueIlfe005);
					}
					// labelValueVO.setValue(valueIlfe005);
					// labelValueVO.setLabel("Ilfe005");
				} else {
					log.info("La licencia número [" + lic.getNumLicencia() + "] con estado [" + ultimoEstado + "] se registró exitosamente en ILFE005");
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
				STTIPARC = set(archivo[i].getTipoArchivo());
				STTIPARC = dataTruncation(STTIPARC, 5);

				h = new Hashtable();
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
						log.error("La licencia número [" + lic.getNumLicencia() + "] con estado [" + ultimoEstado + "] falló al intentar registrar en ILFE006, PK");
					} else {
						log.error("La licencia número [" + lic.getNumLicencia() + "] con estado [" + ultimoEstado + "] falló al intentar registrar en ILFE006, " + valueIlfe006);
					}
					// labelValueVO.setValue(valueIlfe006);
					// labelValueVO.setLabel("Ilfe006");
				} else {
					log.info("La licencia número [" + lic.getNumLicencia() + "] con estado [" + ultimoEstado + "] se registró exitosamente en ILFE006");
				}
			}

			// Detalle Haberes
			CTDetalleHaber[] detalleHaberes = respuesta.getZonaC().getZONACC().getHaberes().getDetalleArray();
			for (int i = 0; i < detalleHaberes.length; i++) {

				PERIODO = setPeriodo(detalleHaberes[i].getAnoMesRenta());// "1900-01"
				NOMHABER = dataTruncation(set(detalleHaberes[i].getNombreHaber()), 40);
				MONTO = dataTruncation(set(detalleHaberes[i].getMontoHaber()), 12);
				//
				// cmd = "INSERT INTO ILFE007 (AFIRUT, EMPRUT, PERIODO,
				// NOMHABER, MONTO"
				// cmd += " ) VALUES ("
				// cmd += " ${AFIRUT}, ${RUTEMPLE}, '${PERIODO}', '${NOMHABER}',
				// ${MONTO})"
				//
				// listaCmd << cmd
				h = new Hashtable();
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
						log.error("La licencia número [" + lic.getNumLicencia() + "] con estado [" + ultimoEstado + "] falló al intentar registrar en ILFE007, PK");
					} else {
						log.error("La licencia número [" + lic.getNumLicencia() + "] con estado [" + ultimoEstado + "] falló al intentar registrar en ILFE007, " + valueIlfe007);
					}
					// labelValueVO.setValue(valueIlfe007);
					// labelValueVO.setLabel("Ilfe007");
				} else {
					log.info("La licencia número [" + lic.getNumLicencia() + "] con estado [" + ultimoEstado + "] se registró exitosamente en ILFE007");
				}
			}
		}
		// update a la tabla de lista de licencias ILFE000, con ESTADO =
		// â€œ01ï¿½? ( procesado-ok )
		// cmd = "UPDATE ILFE000 SET ESTADO = 1 WHERE NUMLIC = ${numLicencia}";
		h = new Hashtable();
		h.put("ESTADO", String.valueOf(ultimoEstado));// Integer.valueOf("1")
		h.put("NUMLIC", lic.getNumLicencia());
	}

	/**
	 * @param codactla
	 * @param i
	 * @return
	 */
	private String dataTruncation(String str, int i) {
		return str.length() > i ? str.substring(0, i) : str;
	}

	/**
	 * @param entidadFechaDesde
	 * @return
	 */
	private String set(Calendar c) {
		String d = "19000101";
		if (null != c) {
			d = sdf.format(c.getTime());
		}
		return d;
	}

	private String setPeriodo(Calendar c) {
		String d = "1900-01";
		if (null != c) {
			d = sdf2.format(c.getTime());
		}
		return d;
	}

	/**
	 * @param rut
	 * @param string
	 * @return
	 */
	private String separaNumDigRut(String rut, String tipo) {
		String auxRut = "0";
		if (rut != "0") {
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

	private String set(String s) {
		return null == s ? "" : s.trim();
	}

	private String set(BigInteger i) {
		return null == i ? "0" : i.toString();
	}

	private String set(BigDecimal d) {
		return null == d ? "0" : d.toString();
	}

	private String formaFechaAS(String fecha) {
		String auxFecha = "19000101";
		if (!fecha.equals("0")) {
			if (fecha.indexOf("-") != -1) // fecha[4] == "-" && fecha[7] ==
			// "-")
			{
				auxFecha = fecha.substring(0, 3) + fecha.substring(5, 6) + fecha.substring(8, 9);// "${fecha[0..3]}${fecha[5..6]}${fecha[8..9]}"
			} else if (fecha.indexOf("/") != -1)// (fecha[2] == "/" && fecha[5]
			// == "/"){
			{
				auxFecha = fecha.substring(6, 9) + fecha.substring(3, 4) + fecha.substring(0, 1);// "${fecha[6..9]}${fecha[3..4]}${fecha[0..1]}"
			}
		}
		return auxFecha;
	}

	private boolean consumoInverso(IlfeOpeVO vo, String AfiRut) {

		Ilfe002InversoVO inv = new Ilfe002InversoVO();

		inv.setIdeOpe(vo.getIdeOpe());
		inv.setNumImpre(new Integer(vo.getNumLicencia().toString()));
		inv.setAfiRut(Long.parseLong(AfiRut));

		boolean f = false;
		try {
			List l = svc_b.getInverso(inv);
			if (!empty(l)) {
				Iterator iter = l.iterator();

				while (iter.hasNext()) {
					Ilfe002InversoVO obj = (Ilfe002InversoVO) iter.next();

					if (obj.getEstLicen().toString().equals("0")) {
						f = true;
					}
				}

			}
		} catch (SvcException e) {
			logError(e);
		}
		return f;

	}

	/**
	 * @param lic
	 * @param ultimoEstado
	 * @return
	 */
	private boolean consumoValidoLME(IlfeOpeVO vo, int ultimoEstado) {
		Ilfe002VO o = new Ilfe002VO();
		o.setIdeOpe(vo.getIdeOpe());
		o.setNumImpre(new Integer(vo.getNumLicencia().toString()));
		o.setEstadoLicencia(new Integer(ultimoEstado));
		o.setFechaEstado(vo.getFechaEstado());
		o.setHoraEstado(vo.getHoraEstado());
		boolean f = true;
		try {
			List l = svc_b.getIlfe002R(o);
			if (!empty(l))
				f = false;
		} catch (SvcException e) {
			// log.error(e.getClass() + "; "+ e.getMessage());
			logError(e);
		}
		return f;
	}

	public boolean actualizarLmeCCAF() {
		boolean resp = false;
		operador = " ";
		proceso = "8";
		startProcess("ActualizarLmeCCAF");
		String dataInf = "";

		if (!"OK".equals(labelValueVO.getValue()) && null != labelValueVO.getValue()) {
			dataInf = "ERROR, no se ejecutara " + path + " , debido a un problema al insertar en la tabla " + labelValueVO.getLabel();
			endProcess("ActualizarLmeCCAF", dataInf);
			return resp;
		}

		AS400 as400 = new AS400(host, user, pass);
		ProgramCall pgm = new ProgramCall(as400);
		try {
			pgm.setProgram(path);
			if (pgm.run() != true) {
				AS400Message[] msgList = pgm.getMessageList();

				// datosConsulta.estado = "ERROR";
				for (int i = 0; i < msgList.length; i++) {
					log.info(msgList[i].getText());
				}

			} else {
				// Asigna valores de los parametros de retorno
				dataInf = "datosConsulta.estado = OK";
				resp = true;
			}
		} catch (PropertyVetoException e) {
			logError(e);
		} catch (AS400SecurityException e) {
			logError(e);
		} catch (ErrorCompletingRequestException e) {
			logError(e);
		} catch (IOException e) {
			logError(e);
		} catch (InterruptedException e) {
			logError(e);
		} catch (ObjectDoesNotExistException e) {
			logError(e);
		} finally {
			as400.disconnectService(AS400.COMMAND);
		}

		endProcess("ActualizarLmeCCAF", dataInf);
		return resp;
	}

	public void commandAS400(String command) {
		AS400 as400 = new AS400(host, user, pass);
		CommandCall cc = new CommandCall(as400);
		try {
			cc.run("ADDLIBLE LIEXP *LAST");
			if (!cc.run(command)) {
				AS400Message[] msgList = cc.getMessageList();
				for (int i = 0; i < msgList.length; i++) {
					log.info(msgList[i].getText());
				}
			}
		} catch (AS400SecurityException e) {
			logError(e);
		} catch (ErrorCompletingRequestException e) {
			logError(e);
		} catch (IOException e) {
			logError(e);
		} catch (InterruptedException e) {
			logError(e);
		} catch (PropertyVetoException e) {
			logError(e);
		} finally {
			as400.disconnectService(AS400.COMMAND);
		}
	}

	private void log(String msg, Object[] paramArrayOfObject) {
		log(Util.stringFormat(msg, paramArrayOfObject));
	}

	private void log(String message) {
		log.info(message);
		logLcc(message);
		// Date now = new Date();
		// svc.insertLog(new LmeLogVO("INFO", proceso, "",sdf.format(now),
		// shf.format(now), dataTruncation(message,500)));
	}

	private void logError(Throwable e) {
		log.error(e.getClass() + "; " + e.getMessage());
		logLcc("ERROR " + e.getMessage());
		// Date now = new Date();
		// svc.insertLog(new LmeLogVO("ERROR", proceso, "",sdf.format(now),
		// shf.format(now), dataTruncation(e.getClass() + "; "+
		// e.getMessage(),500)));
	}

	private void logg(String message) {
		log.info(message);
	}

	private void logLcc(String message) {
		if (null != dateLcc)
			logLcc.append("<br/>&nbsp;&nbsp;" + message);
	}

	/**
	 * @return Returns the dateLcc.
	 */
	public Date getDateLcc() {
		return dateLcc;
	}

	/**
	 * @param dateLcc
	 *            The dateLcc to set.
	 */
	public void setDateLcc(Date dateLcc) {
		this.dateLcc = dateLcc;
	}

	/**
	 * @return the dateFec
	 */
	public Date getDateFec() {
		return dateFec;
	}

	/**
	 * @param dateFec the dateFec to set
	 */
	public void setDateFec(Date dateFec) {
		this.dateFec = dateFec;
	}

	/**
	 * @return Returns the logLcc.
	 */
	public StringBuffer getLogLcc() {
		return logLcc;
	}

	/**
	 * @param logLcc
	 *            The logLcc to set.
	 */
	public void setLogLcc(StringBuffer logLcc) {
		this.logLcc = logLcc;
	}

	public List getLmeList() {
		return lmeList;
	}

	public void setLmeList(List lmeList) {
		this.lmeList = lmeList;
	}

	/**
	 * 
	 */
	/*
	 * public void close() { svc.closeSqlMap();
	 * 
	 * }
	 */

	public String DeleteLME(Ilf1000VO vo) {

		String respuestaCobol = null;

		String ipServer = this.host;
		String usuarioConexion = this.user;
		String claveConexion = this.pass;
		String programa = this.cobol;

		String ideOpe = Helper.paddingString(vo.getIdeOpe().toString(), 1, '0', true);
		String afiRut = Helper.paddingString(String.valueOf(vo.getAfiRut()), 9, '0', true);
		String numImpre = null;

		log.debug("vo.getNumImpre(): " + vo.getNumImpre());
		if (vo.getNumImpre().intValue() < 0) {
			log.debug("menor a 0");
			numImpre = "-" + Helper.paddingString(String.valueOf(((vo.getNumImpre().intValue()) * (-1))), 9, '0', true);

			// numImpre=Helper.paddingString(vo.getNumImpre().toString(),10,'0',
			// true);
		} else {
			log.debug("mayor a 0");
			numImpre = Helper.paddingString(String.valueOf(vo.getNumImpre()), 10, '0', true);
		}
		log.debug("numImpre: " + numImpre);
		// System.out.print("numImpre: " + numImpre);

		String numImprela = Helper.paddingString(vo.getLicimpnum().toString(), 7, '0', true);

		/*
		 * SECCION DONDE SE SETEAN LOS PARAMETROS DE LLAMADA PARA LA INVOCACION
		 * DEL PROCESO COBOL
		 */
		ParametrosConexionBO paramConexion = new ParametrosConexionBO();// para
		// setear
		// parametros
		// de
		// conexion
		ParametrosLlamadaBO[] paramLlamada = new ParametrosLlamadaBO[6];// para
		// setear
		// parametros
		// de
		// entrada
		// al
		// programa
		// cobol,
		// INDICA
		// LA
		// CANTIDAD
		// DE
		// PARAMETROS
		// DE
		// ENTRADA
		// Y
		// SALIDA
		// A
		// ENVIAR
		// AL CL

		/* Seteo de los parametros de conexion. */
		paramConexion.setIpServer(ipServer);
		paramConexion.setUsuarioConexion(usuarioConexion);
		paramConexion.setClaveConexion(claveConexion);
		paramConexion.setPrograma(programa);

		/*
		 * Seteo de los parametros de entrada/SALIDA para el proceso COBOL,
		 * mediante paramLlamada.
		 */
		ParametrosLlamadaBO IDEOPE = new ParametrosLlamadaBO();
		ParametrosLlamadaBO NUMIMPRE = new ParametrosLlamadaBO();
		ParametrosLlamadaBO AFIRUT = new ParametrosLlamadaBO();
		ParametrosLlamadaBO NUMIMPRELA = new ParametrosLlamadaBO();
		ParametrosLlamadaBO CONTROL = new ParametrosLlamadaBO();
		ParametrosLlamadaBO FILESTATUS = new ParametrosLlamadaBO();

		/*
		 * 03 WS-IDEOPE PIC 9(01). 03 WS-AFIRUT PIC 9(09). 03 WS-NUMIMPRE PIC
		 * 9(10). 03 WS-LICIMPNUM PIC 9(07).
		 */

		// LOS PARAMETROS DE ENTRADA Y SALIDA SE FIJAN DE ACUERDO A LA CANTIDAD
		// DE ESTOS MISMOS QUE ACEPTE EL CL, EN ESTE CASO
		// SON 2 PARAMETROS DE ENTRADA Y 2 DE SALIDA.
		// PARAMETROS DE ENTRADA (IN)
		IDEOPE.setTipo("STRING");
		IDEOPE.setLargo(ideOpe.length());
		IDEOPE.setValor(ideOpe);
		IDEOPE.setDireccion("IN");
		paramLlamada[0] = IDEOPE;

		AFIRUT.setTipo("STRING");
		AFIRUT.setLargo(afiRut.length());
		AFIRUT.setValor(afiRut);
		AFIRUT.setDireccion("IN");
		paramLlamada[1] = AFIRUT;

		NUMIMPRE.setTipo("STRING");
		NUMIMPRE.setLargo(numImpre.length());
		NUMIMPRE.setValor(numImpre);
		NUMIMPRE.setDireccion("IN");
		paramLlamada[2] = NUMIMPRE;

		NUMIMPRELA.setTipo("STRING");
		NUMIMPRELA.setLargo(numImprela.length());
		NUMIMPRELA.setValor(numImprela);
		NUMIMPRELA.setDireccion("IN");
		paramLlamada[3] = NUMIMPRELA;

		CONTROL.setTipo("STRING");
		CONTROL.setLargo(1);
		CONTROL.setValor("0");
		CONTROL.setDireccion("OUT");
		paramLlamada[4] = CONTROL;

		FILESTATUS.setTipo("STRING");
		FILESTATUS.setLargo(2);
		FILESTATUS.setValor("0");
		FILESTATUS.setDireccion("OUT");
		paramLlamada[5] = FILESTATUS;

		try {

			ParametrosLlamadaBO[] salida = ConsumidorCobol.call(paramConexion, paramLlamada);
			// respuestaCobol =
			// (String)salida[4].getValor()+"-"+(String)salida[5].getValor();
			respuestaCobol = (String) salida[4].getValor();
		} catch (Exception e) {
			log.error(e);
		}
		return respuestaCobol;

	}

	public void procesosParticularesPorLicencia() {

		// consulta ILFE080
		final String CONSUMO_OK="1";
		final String CONSUMO_NOK="-2";
		// start
		startProcess("procesos Particulares Por Licencia");
		
		//Se borra resgistros que tienen error o no se pueden consumir
		int res_dlt= svc_b.deleteIlfe080(CONSUMO_NOK);
		log.info("La cantidad de licencias borradas em ilfe 080 antes de iniciar: " + res_dlt);
		
		List licencias = new ArrayList();
		try {
			licencias = svc_a.getIlfe080();
		} catch (SvcException e1) {
			licencias = new ArrayList();
			log.error(e1.getClass() + "; " + e1.getMessage());
		}

		// count
		log.info("La cantidad de licencias en ILFE080 son: " + licencias.size());

		// ConsumoOperadorService consumoOperadorService = new
		// ConsumoOperadorService(m);
		if(licencias.size()>0){
			limpiaTablas();

			for (int i = 0; i < licencias.size(); i++) {
				try {

					Ilfe080VO vo = (Ilfe080VO) licencias.get(i);

					String lic = vo.getNumLicencia();
					String codOpe = vo.getIdOperador();

					BigInteger numLic = new BigInteger(lic);

					IlfeOpeVO ilfeOpeVO = new IlfeOpeVO();
					ilfeOpeVO.setCodOpe(codOpe);
					ilfeOpeVO.setStsOpe(Integer.valueOf("1"));

					List l = svc_a.getIlfeOpe(ilfeOpeVO);
					ilfeOpeVO = (IlfeOpeVO) l.get(0);
					ilfeOpeVO.setNumLicencia(lic);
					ilfeOpeVO.setDigLicencia(Util.dv(numLic));
					LabelValueVO err;

					log.info("LIC=" + lic + " CODOPE=" + codOpe + " CODCCAF=" + ilfeOpeVO.getCodCcaf() + " PWDCCAF=" + ilfeOpeVO.getPwdCcaf());

					setDateLcc(new Date());
					startProcess("ConsumirDetallesLME");
					err = consumirDetalleLME(ilfeOpeVO, "PARTICULAR");
					Map h = new Hashtable();
					h.put("ideOpe", ilfeOpeVO.getIdeOpe());
					h.put("numImpre", ilfeOpeVO.getNumLicencia());
					h.put("label", err.getLabel().toString());

					svc_b.updateIlfe002RError(h);


					vo.setEstado(err.getValue());
					String txt = "(updateIlfe080) LIC=" + vo.getNumLicencia() + " CODOPE=" + vo.getIdOperador() + ",";
					txt += (svc_a.updateIlfe080(vo) > 0) ? "licencia procesada correctamente" : "problemas al procesar";
					log.info(txt);

					// svc.deleteIlfe080(vo);

					// System.out.println("la RESP 2 (res) es: " + res);
					// logger.info(
					// "SE PROCESO EL CONSUMO PARTICULAR: 'CONSUMO DE DETALLE POR LICENCIA'  ["
					// + (new Date()) + "] con licencia: " + lic);

				} catch (Exception e) {
					log.error(e.getClass() + "; " + e.getMessage());
				} catch (SvcException e) {
					log.error(e.getClass() + "; " + e.getMessage());
				}
			}
			int dlt = 0;
			if (actualizarLmeCCAF()) {
				try {
					if ((dlt = svc_b.deleteIlfe080(CONSUMO_OK)) > 0) {
						log.info("se borran:" + dlt + " registros de Ilfe080, estos fueron procesados correctamente");
					}
				} catch (Exception e) {
					log.error(e.getClass() + "; " + e.getMessage());
				}
			}
		}
		//Invocando consumo excepcional estados 72 
		procesosExcepcionalEstado72();
	}
	
	//REQ-8000003799 - mejoras Consumos de nuevos estados
	private void procesosExcepcionalEstado72() {

		// consulta ILFE085
		final String CONSUMO_OK="1";
		final String CONSUMO_NOK="-2";
		// start
		startProcess("proceso Excepcional Estado72 Por Licencia");
		
		//Se borra resgistros que tienen error o no se pueden consumir
		int res_dlt= svc_a.deleteIlfe085(CONSUMO_NOK);
		log.info("La cantidad de licencias borradas em ilfe 085 antes de iniciar: " + res_dlt);
		
		List licencias = new ArrayList();
		try {
			licencias = svc_a.getIlfe085();
		} catch (SvcException e1) {
			licencias = new ArrayList();
			log.error(e1.getClass() + "; " + e1.getMessage());
		}

		// count
		log.info("La cantidad de licencias en ILFE085 son: " + licencias.size());

		// ConsumoOperadorService consumoOperadorService = new
		// ConsumoOperadorService(m);
		if(licencias.size()>0){
			limpiaTablas();

			for (int i = 0; i < licencias.size(); i++) {
				try {

					Ilfe080VO vo = (Ilfe080VO) licencias.get(i);

					String lic = vo.getNumLicencia();
					String codOpe = vo.getIdOperador();

					BigInteger numLic = new BigInteger(lic);

					IlfeOpeVO ilfeOpeVO = new IlfeOpeVO();
					ilfeOpeVO.setCodOpe(codOpe);
					ilfeOpeVO.setStsOpe(Integer.valueOf("1"));

					List l = svc_a.getIlfeOpe(ilfeOpeVO);
					ilfeOpeVO = (IlfeOpeVO) l.get(0);
					ilfeOpeVO.setNumLicencia(lic);
					ilfeOpeVO.setDigLicencia(Util.dv(numLic));
					ilfeOpeVO.setUltimoEstado("72");
					LabelValueVO err;

					log.info("LIC=" + lic + " CODOPE=" + codOpe + " CODCCAF=" + ilfeOpeVO.getCodCcaf() + " PWDCCAF=" + ilfeOpeVO.getPwdCcaf());

					setDateLcc(new Date());
					startProcess("ConsumirDetallesLME");
					err = consumirDetalleLME(ilfeOpeVO, "PARTICULAR");
					Map h = new Hashtable();
					h.put("ideOpe", ilfeOpeVO.getIdeOpe());
					h.put("numImpre", ilfeOpeVO.getNumLicencia());
					h.put("label", err.getLabel().toString());

					svc_b.updateIlfe002RError(h);


					vo.setEstado(err.getValue());
					String txt = "(updateIlfe0855) LIC=" + vo.getNumLicencia() + " CODOPE=" + vo.getIdOperador() + ",";
					txt += (svc_a.updateIlfe085(vo) > 0) ? "licencia procesada correctamente" : "problemas al procesar";
					log.info(txt);

					// svc.deleteIlfe080(vo);

					// System.out.println("la RESP 2 (res) es: " + res);
					// logger.info(
					// "SE PROCESO EL CONSUMO PARTICULAR: 'CONSUMO DE DETALLE POR LICENCIA'  ["
					// + (new Date()) + "] con licencia: " + lic);

				} catch (Exception e) {
					log.error(e.getClass() + "; " + e.getMessage());
				} catch (SvcException e) {
					log.error(e.getClass() + "; " + e.getMessage());
				}
			}
			int dlt = 0;
			if (actualizarLmeCCAF()) {
				try {
					if ((dlt = svc_a.deleteIlfe085(CONSUMO_OK)) > 0) {
						log.info("se borran:" + dlt + " registros de Ilfe080, estos fueron procesados correctamente");
					}
				} catch (Exception e) {
					log.error(e.getClass() + "; " + e.getMessage());
				}
			}
		}
	}

	public void procesosParticularesPorFecha() {

		// Consulta ILFE081
		// start
		startProcess("Procesos Particulares Por Fecha");

		List fechas;
		try {
			fechas = svc_a.getIlfe081();
		} catch (SvcException e1) {
			fechas = new ArrayList();
			log.error(e1.getClass() + "; " + e1.getMessage());
		}

		// conunt
		log.info("La cantidad de fechas en ILFE081 son: " + fechas.size());

		// ConsumoOperadorService consumoOperadorService = new
		// ConsumoOperadorService(m);
		int dlt = 0;
		for (int i = 0; i < fechas.size(); i++) {
			limpiaTablas();
			try {

				Ilfe081VO vo = (Ilfe081VO) fechas.get(i);
				String fechaDesde = vo.getFecha();
				String fechaHasta = vo.getFechaHasta();

				Date date = Util.date(fechaDesde, "dd/MM/yyyy HH:mm");
				Date dateHasta = Util.date(fechaHasta, "dd/MM/yyyy HH:mm");
				setDateLcc(date);
				setDateFec(dateHasta);
				
				setLmeList(new ArrayList());
				consumirEstadosLME("LMEEventFec");
				consumirDetallesLME();

				// eliminar la fecha
				dlt = 0;
				if (actualizarLmeCCAF()) {
					if ((dlt = svc_a.deleteIlfe081(vo)) > 0) {
						log.info("se borran:" + dlt + " registros de Ilfe081, fecha:" + vo.getFecha());
					}
				}

				log.info("SE PROCESO EL CONSUMO PARTICULAR : 'CONSUMO NUEVOS ESTADOS' [" + (new Date()) + "] con fecha: " + fechaDesde);

			} catch (ParseException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}

	}
	
	public void procesoReconsumoEstadosNuevos() {

		// Consulta ILFE081
		// start
		
		Date now = new Date();
		int hora=Integer.parseInt( Util.getHour().substring(0, 2) );
		long inicio12 = System.currentTimeMillis();
		try {
			ResourceBundle properties = ResourceBundle.getBundle("lme.resources.ApplicationResources");
			
			String horaEjecucion = properties.getString("reconsumo.hora.ejecucion");
			if(hora>=Integer.parseInt(horaEjecucion) && hora <=Integer.parseInt(horaEjecucion)+2){
				
				String horaDesde = properties.getString("reconsumo.hora.desde");
				String horaHasta = properties.getString("reconsumo.hora.hasta");
				String date = Util.getToday_dd_MM_yyy();
				if(BdConstants.getInstance().getBitacora_reconsumo().get(date)== null){
					startProcess("Procesos Reconsumo Estados Nuevos");
					//realizar el insert a la tabla ILFE081 y entregar la respuesta
					Ilfe081VO vo = new Ilfe081VO();
					vo.setFecha(date + " " +  horaDesde);
					vo.setFechaHasta(date + " " +  horaHasta);
					vo.setEstadoImed("");
					vo.setEstadoMediPass("");
					vo.setFechaConsulta(sdf.format(now));
					vo.setHoraConsulta(shf.format(now));
					vo.setEstado("0");

					String respuesta = svc_a.insertIlfe081(vo);
					BdConstants.getInstance().addBitacora_reconsumo(date, "0");
					procesosParticularesPorFecha();
					log.info("TERMINO DE PROCESO RECONSUMO EVENFEC ["+ Util.getTiempoRestante(inicio12) + " Milisegundos]");
				}
			}
			
		} catch (Exception e1) {
			log.error(e1.getClass() + "; " + e1.getMessage());
		}

	}
	
	/*
	 * public ConsumoOperadorService() {
	 * 
	 * host="146.83.1.2"; user="USRSIST"; pass="USRSIST";
	 * 
	 * path="/QSYS.LIB/LIEXP.LIB/ILCLME01.PGM";
	 * cobol="/QSYS.LIB/LIEXP.LIB/ILCLME51.PGM"; }
	 * 
	 * public static void main(String[] args) throws SvcException {
	 * ConsumoOperadorService lmeJob= new ConsumoOperadorService();
	 * lmeJob.deleteLMECero(); }
	 */

	public void deleteLMECero() {
		// svc_a = SvcFactory.getAS400Svc();
		String respuesta = null;

		// obtener los registros a eliminar
		List l = null;
		try {
			l = svc_b.getIlfe082();
		} catch (SvcException e) {
			log.error("Error al consulta Ilf1000", e);
		}

		if (l == null || l.size() == 0) {
			// Ilfe082VO ilfe82Vo = new Ilfe082VO();
			// svc.deleteIlfe082(ilfe82Vo);//Borra todo
			log.info("No existen licencias en ILFE082 para eliminar");
		}
		for (int i = 0; i < l.size(); i++) {
			Ilfe082VO ilfe82Vo = (Ilfe082VO) l.get(i);
			try {

				// Si no está en Ilfe1000, se elimina
				if (ilfe82Vo.getEstaEnIlfe1000() == 0) {
					Ilf1000VO ilf1000Vo = new Ilf1000VO();
					ilf1000Vo.setAfiRut(ilfe82Vo.getAfiRut());
					ilf1000Vo.setLicimpnum(ilfe82Vo.getLicImpNum());
					ilf1000Vo.setNumImpre(ilfe82Vo.getNumImpre());
					ilf1000Vo.setIdeOpe(ilfe82Vo.getIdeOpe());

					log.info("Eliminar licencia: " + ilfe82Vo.getLicImpNum() + ", Rut: " + ilfe82Vo.getAfiRut());

					respuesta = DeleteLME(ilf1000Vo);
					switch (Integer.parseInt(respuesta.toString().trim())) {
					case 0:
						log.info("Licencia eliminada correctamente");
						break;
					case 1:
						log.info("PROBLEMA AL ELIMINAR TABLA ILF8600");
						break;
					case 2:
						log.info("PROBLEMA AL ELIMINAR TABLA ILFE002R");
						break;
					case 3:
						log.info("PROBLEMA AL ELIMINAR TABLA ILFE003R");
						break;
					case 4:
						log.info("PROBLEMA AL ELIMINAR TABLA ILFE004R");
						break;
					case 5:
						log.info("PROBLEMA AL ELIMINAR TABLA ILFE005R");
						break;
					case 6:
						log.info("PROBLEMA AL ELIMINAR TABLA ILFE021");
						break;
					case 7:
						log.info("PROBLEMA AL ELIMINAR TABLA ILFE031");
						break;
					case 8:
						log.info("PROBLEMA AL ELIMINAR TABLA ILF1010");
						break;
					case 9:
						log.info("PROBLEMA AL ELIMINAR TABLA ILFE034");
						break;
					}
				} else {
					// Si está, se imprime un mensaje
					log.info("Licencia: " + ilfe82Vo.getLicImpNum() + ", Rut: " + ilfe82Vo.getAfiRut() + " se encuentra en ilf1000");
				}
				// Eliminar registro de la cola
				svc_a.deleteIlfe082(ilfe82Vo);
			} catch (SvcException e) {
				log.error("Error al eliminar licencia: " + ilfe82Vo.getLicImpNum() + ", Rut: " + ilfe82Vo.getAfiRut() + " Estado 0", e);
			}
		}
	}

}
