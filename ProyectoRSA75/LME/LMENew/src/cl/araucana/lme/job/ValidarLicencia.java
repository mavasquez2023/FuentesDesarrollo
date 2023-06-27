/**
 * 
 */
package cl.araucana.lme.job;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.apache.log4j.Logger;

import wwwLmeGovClLme.STJornadaReposo.Enum;

import cl.araucana.lme.helper.Helper;
import cl.araucana.lme.ibatis.domain.Ilfe002VO;
import cl.araucana.lme.ibatis.domain.Ilfe004VO;
import cl.araucana.lme.ibatis.domain.Ilfe021VO;
import cl.araucana.lme.ibatis.domain.IlfeOpeVO;
import cl.araucana.lme.svc.IAS400Svc_LME;
import cl.araucana.lme.svc.SvcFactory_LME;
import cl.araucana.lme.util.Constants;
import cl.araucana.lme.util.EndPointUtil;
import conector.configuracion.excepciones.ConfiguracionException;
import conector.lme.ws.cliente.operador.LicenciaSimpleType;
import conector.lme.ws.cliente.operador.RespuestaDetalleLicencia;
import conector.lme.ws.cliente.operador.ResultadoType;
import conector.lme.ws.cliente.operador.ServiciosMultiOperador;
import conector.lme.ws.cliente.operador.excepciones.ErrorInvocacionOperadorException;
import conector.vo.SalidaLMEInfValCCAF;

/**
 * @author usist199
 *
 */
public class ValidarLicencia {
	
	private Logger log = Logger.getLogger(this.getClass());
	private final String TIPO_INSTITUCION = "C";
	final BigInteger ZERO = new BigInteger("0");
	private IAS400Svc_LME svc_a = null;

	
	public ValidarLicencia(){
		svc_a = SvcFactory_LME.getAS400Svc_LME();
	}
	/**
	 * @param vo
	 */
	public String validarLicenciaME(Ilfe021VO vo) {

		Date now = new Date();
		String respWs = "1";
		String enviada = "0";
		
		BigInteger idLicencia = Helper.toBigInteger(vo.getNumImpre());
		String dvLicencia = Helper.dv(idLicencia.intValue());
		// String urlOpe = vo.getUrlOpe();

		LicenciaSimpleType[] listaLicencias = new LicenciaSimpleType[1];
		listaLicencias[0] = new LicenciaSimpleType(idLicencia, dvLicencia, vo.getEstado());

		// UrlBorderVO urlVO = new UrlBorderVO();
		// urlVO.setCodOpe(vo.getCodOpe());
		// urlVO.setNombreServicio("VALIDACION");
		String nombreHash = "VALIDACION";
		String glosaEstado = Constants.WS_UNAVAILABLE;
		
		try {

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
			
			try {
				// ejecuta el nuevo servicio que maneja el timeout y utiliza la
				// dos url enviadas
				respuesta = servicio.informaValidacionLicencias2(listaLicencias);
				//respuesta= new SalidaLMEInfValCCAF();
				/** **************************************************** */
				// se asignan errores de timeout segun la respuesta del servico
				// y se guarda el tiempo de demoro del servicio que funciona
				// correctamente
				if (respuesta.isError1()) {

					if (instanciaEndPoint.getEstadoError(vo.getCodOpe(), "1") == Boolean.FALSE) {
						instanciaEndPoint.cambiarEstadoError(vo.getCodOpe(), "1", Boolean.TRUE);
						log.warn("Problemas por TimeOut con la licencia " + vo.getNumImpre() + " al tratar de ejecutar servicio " + url1 + " (Vordel) en el operador " + vo.getCodOpe());
					}
					if (respuesta.isError2()) {
						instanciaEndPoint.cambiarEstadoError(vo.getCodOpe(), "2", Boolean.TRUE);
						log.warn("Problemas por TimeOut con la licencia " + vo.getNumImpre() + " al tratar de ejecutar servicio " + url2 + " (" + instanciaEndPoint.getNombreOperador(vo.getCodOpe()) + ") en el operador " + vo.getCodOpe());
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
					vo.setGlosaEstado(glosaEstado);
					return enviada;
				}
				/** *************************************************** */

			} /*catch (ConfiguracionException e1) {
				logError(e1);
				glosaEstado = e1.getMessage();
				return false;
			} catch (ErrorInvocacionOperadorException e1) {
				logError(e1);
				glosaEstado = e1.getMessage();
				return false;
			}*/ catch (Exception e1) {
				logError(e1);
				glosaEstado = e1.getMessage();
				return Helper.reformateaGlosaEstado(glosaEstado);
			}

			
			

			if (respuesta != null && respuesta.getRespuesta() != null && respuesta.getRespuesta().getEstado().equals(ZERO)) {
				
				enviada = "1";
				ResultadoType[] resultados = respuesta.getRespuesta().getListaResultado();
				for (int i = 0; i < resultados.length; i++) {
					ResultadoType resultadoOp = resultados[i];
					respWs = resultadoOp.getCodEstado().toString();
					respWs = "0".equals(respWs) ? "0" : "1";//respWs.length()>1?
															// "1":respWs;
					glosaEstado = resultadoOp.getGloEstado();
				}
				
				// listaResult.add("IdLicencia: ${idLicencia}, codEstado:
				// ${codEstado}, glosaEstado: ${glosaEstado}");
			}
			
			log.info("[" + urlUtilizada + "] - IdLicencia:" + idLicencia + ", codEstado:" + respWs + ", glosaEstado:" + glosaEstado + ", DEMORO:" + String.valueOf(tiempoUtilizado) + " Milisegundos");
			glosaEstado = Helper.reformateaGlosaEstado(glosaEstado);
			/**
			 * UPDATE ILFE021 SET ENVIADA = 1, RESPWS = '${respuesta.estado}',
			 * GLORESP = '${glosaEstado}', FECRESP = ${fechaConsulta}, HORRESP =
			 * ${hora} WHERE IDEOPE = ${op.IDEOPE} AND NUMIMPRE = ${idLicencia}
			 */
			
			//Actualiza tabla 21
			vo.setEnviada(enviada);
			vo.setRespWs(respWs);
			vo.setGlosaEstado(glosaEstado);
			vo.setFechaRespuesta(Helper.getSdf()); // yyyyMMdd
			vo.setHoraRespuesta(Helper.getShf()); // HHmmss
			
			
		} catch (Exception e) {
			// log.error(e.getClass() + "; "+ e.getMessage());
			logError(e);
			e.printStackTrace();
			return Helper.reformateaGlosaEstado(e.getMessage());
		}
		if(respWs.equals("0")){
			log.info("VALIDACION EXITOSA");
		}else{
			log.info("VALIDACION RESPUETA WS ERRONEA");
		}
		return enviada;
	}
	
	public boolean actualizar8600(Ilfe002VO lic2R, Ilfe004VO lic4R, String estadoValidacion, int indiceConvenio) {
		//INSERT 8600
		
		try {
			String ESTADO="3";
			String ORIGEN= "1";
			Map h = new HashMap();
			h.put("recuper", lic2R.getRecuperabilidad());
			h.put("jorreposo", lic2R.getJornadaReposo());
			h.put("jusreposo", Helper.truncateText(lic2R.getJustificarOtro(), 30));
			h.put("initrinv", lic2R.getInicioTramiteInvalidez());
			h.put("caltrabaj", lic4R.getCalidadTrabajador());
			h.put("estado", ESTADO);
			h.put("contindef", lic4R.getContratoDuracionIndef());
			h.put("ofiorig", ORIGEN);
			h.put("numLicencia", lic2R.getNumimprela());
			h.put("afirut", lic2R.getAfiRut());
			if(estadoValidacion.equals("T")){
				h.put("licper1", lic2R.getLicperiodo1());
				h.put("licper2", lic2R.getLicperiodo2());
				h.put("licper3", lic2R.getLicperiodo3());
				h.put("licper4", lic2R.getLicperiodo4());
				h.put("licper5", lic2R.getLicperiodo5());
				h.put("licper6", lic2R.getLicperiodo6());
				h.put("renta60", lic2R.getRentaImponible60());
				h.put("renta90", lic2R.getRentaImponible90());
			}
			h.put("indiceConvenio", new Integer(indiceConvenio));
			//indice de convenio
			svc_a.updateIlf8600(h);
		} catch (Exception e) {
			log.error("No se actualizó tabla 8600, mensaje= " + e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String getEstadoValidacion(Ilfe002VO detalle){
		String estado="";
		String fechaContinua= Helper.getFechaContinua(detalle.getFechaInicio());
		
		Map valores = new HashMap();
		valores.put("AFIRUT", detalle.getAfiRut());
		valores.put("FECHAHASTA", new Integer(fechaContinua));
		valores.put("PERIODO", detalle.getPeriodoLicencia());
		valores.put("IDEOPE", detalle.getIdeOpe());
		valores.put("NUMIMPRE", detalle.getNumImpre());
		
		
		//Curativas
		if(detalle.getTipoLicencia().intValue()== 1 || detalle.getTipoLicencia().intValue()== 7){
			int cantidad_rentas=svc_a.getArchivoRentas(valores);
			if(cantidad_rentas>0){
				estado="TC";
			}else{
				estado="PC";
			}
		}else{
			//Maternales
			HashMap licAnterior=(HashMap)svc_a.getLicContinua(valores);
			
			if(licAnterior!=null && licAnterior.size()>0){
				log.info("Licencia " + detalle.getNumimprela() + " continua con: " + (BigDecimal)licAnterior.get("LICIMPNUM"));
				BigDecimal tipoSubsidio= (BigDecimal)licAnterior.get("AFISUBTIP");
				log.info("Tipo Licencia= " + detalle.getTipoLicencia().intValue() + ", Tipo Subsidio Lic. Continua=" + tipoSubsidio.intValue());
				if(detalle.getTipoLicencia().intValue()==tipoSubsidio.intValue()){
					estado="T";
				}
			}else{
				licAnterior=(HashMap)svc_a.getLicMismoPeriodo(valores);

				if(licAnterior!=null && licAnterior.size()>0){
					log.info("Licencia " + detalle.getNumimprela() + " mismo periodo con: " + (BigDecimal)licAnterior.get("LICIMPNUM"));
					estado="T";
				}
			}
			
			if(estado.equals("T")){
				log.info("Licencia Validación TOTAL , No Curativa");
				BigDecimal lic_periodo1= (BigDecimal)licAnterior.get("LICPER1");
				BigDecimal lic_periodo2= (BigDecimal)licAnterior.get("LICPER2");
				BigDecimal lic_periodo3= (BigDecimal)licAnterior.get("LICPER3");
				BigDecimal lic_periodo4= (BigDecimal)licAnterior.get("LICPER4");
				BigDecimal lic_periodo5= (BigDecimal)licAnterior.get("LICPER5");
				BigDecimal lic_periodo6= (BigDecimal)licAnterior.get("LICPER6");
				BigDecimal rentaImponible60= (BigDecimal)licAnterior.get("LICRTAIMP");
				BigDecimal rentaImponible90= (BigDecimal)licAnterior.get("RTAIMP90");
				if(lic_periodo4.intValue()!= 0){
					log.info("Licencia Maternal");
				}
				detalle.setLicperiodo1(new Integer(lic_periodo1.intValue()));
				detalle.setLicperiodo2(new Integer(lic_periodo2.intValue()));
				detalle.setLicperiodo3(new Integer(lic_periodo3.intValue()));
				detalle.setLicperiodo4(new Integer(lic_periodo4.intValue()));
				detalle.setLicperiodo5(new Integer(lic_periodo5.intValue()));
				detalle.setLicperiodo6(new Integer(lic_periodo6.intValue()));
				detalle.setRentaImponible60(new Integer(rentaImponible60.intValue()));
				detalle.setRentaImponible90(new Integer(rentaImponible90.intValue()));
			}
		}
		
		
		return estado;
	}
	
	public Ilfe021VO set021(Ilfe002VO cab_licencia, IlfeOpeVO vo, Integer rutEmpresa, String dvEmpresa, String estadoValidacion, int indiceConvenio){
		Ilfe021VO vo21 = new Ilfe021VO();
		try {
			if(estadoValidacion.equals("TC") || estadoValidacion.equals("PC")){
				vo21.setUser("FULL1");
				if(indiceConvenio==0){
					vo21.setUser("FULL2");
				}
			}else{
				vo21.setUser("LME");
			}
			vo21.setIdeOpe(vo.getIdeOpe());
			vo21.setCodOpe(vo.getCodOpe());
			vo21.setNumImpre(vo.getNumImpre());
			vo21.setNumImpDV(cab_licencia.getDvImpre());
			vo21.setEstado(estadoValidacion.substring(0, 1));
			vo21.setEnviada("1");
			vo21.setFechaProceso(Helper.getSdf());
			vo21.setRespWs("0");
			vo21.setAfiRut(cab_licencia.getAfiRut());
			vo21.setAfiRutDV(cab_licencia.getAfiRutDv());
			vo21.setAfiNom(cab_licencia.getAfiNom());
			vo21.setEmpRut(rutEmpresa);
			vo21.setEmpRutDV(dvEmpresa);
			vo21.setCodCcaf(vo.getCodCcaf());
			vo21.setPwdCcaf(vo.getPwdCcaf());
			vo21.setUrlOpe(vo.getUrlOpe());
			vo21.setHoraEnvio(Helper.getShf());
												
		} catch (Exception e) {
			log.error("Licencia no seteada vo021, mensaje= " + e.getMessage());
			e.printStackTrace();
		}
		return vo21;
	}
	
	public boolean insertIlfe021(Ilfe021VO vo){
		try {
			vo.setEnviada("1");
			vo.setFechaRespuesta(Helper.getSdf()); // yyyyMMdd
			vo.setHoraRespuesta(Helper.getShf()); // HHmmss
			
			log.info(":::InsertIlfe021::: " + svc_a.insertIlfe021(vo));
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			log.error(e.getMessage());
			return false;
		}
		return true;
	}
	
	
	private void logError(Throwable e) {
		log.error(e.getClass() + ": " + e.getMessage());
		// Date now = new Date();
		// svc.insertLog(new LmeLogVO("ERROR", proceso, "",sdf.format(now),
		// shf.format(now), dataTruncation(e.getClass() + "; "+
		// e.getMessage(),500)));
	}


}

