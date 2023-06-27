/**
 * 
 */
package cl.araucana.lme.job;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import cl.araucana.lme.helper.Helper;
import cl.araucana.lme.ibatis.domain.Ilfe002VO;
import cl.araucana.lme.ibatis.domain.Ilfe004VO;
import cl.araucana.lme.ibatis.domain.Ilfe021VO;
import cl.araucana.lme.ibatis.domain.Ilfe051VO;
import cl.araucana.lme.ibatis.domain.IlfeOpeVO;
import cl.araucana.lme.svc.IAS400Svc_LME;
import cl.araucana.lme.svc.SvcFactory_LME;
import cl.araucana.lme.svc.exception.SvcException;
import cl.araucana.lme.util.Constants;
import cl.araucana.lme.util.EndPointUtil;
import cl.araucana.lme.util.LabelValueVO;
import conector.lme.ws.cliente.operador.ServiciosMultiOperador;
import conector.vo.SalidaLMEDevEmp;
import conector.vo.SalidaLMEInfValCCAF;

/**
 * @author usist199
 *
 */
public class DevolverLicencia {
	private Logger log = Logger.getLogger(this.getClass());
	private final String TIPO_INSTITUCION = "C";
	private IAS400Svc_LME svc_a = null;
	
	public DevolverLicencia(){
		svc_a = SvcFactory_LME.getAS400Svc_LME();
	}
	/**
	 * @param vo
	 */
	public String devolverLicenciaME051(Ilfe051VO vo) {

		Date now = new Date();
		// String urlOpe = vo.getUrlOpe(); //Web Service
		String respWs = "1";
		String enviada = "0";
		String glosaEstado = Constants.WS_UNAVAILABLE;
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

			BigInteger numLicencia = Helper.toBigInteger(vo.getNumImpre().trim());
			String digLicencia = Helper.dv(numLicencia).trim();

			int motivoDevolucion = vo.getCodMot().intValue();

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
					//respuesta= new SalidaLMEDevEmp();
					/** **************************************************** */
					// se asignan errores de timeout segun la respuesta del
					// servico y se guarda el tiempo de demoro del servicio que
					// funciona correctamente
					if (respuesta.isError1()) {

						if (instanciaEndPoint.getEstadoError(vo.getCodOpe(), "1") == Boolean.FALSE) {
							instanciaEndPoint.cambiarEstadoError(vo.getCodOpe(), "1", Boolean.TRUE);
							log.warn("Problemas por TimeOut con la licencia " + vo.getNumImpre() + " al tratar de ejecutar servicio " + url1 + " (Vordel) en el operador " + vo.getCodOpe());
						}
						if (respuesta.isError2()) {
							instanciaEndPoint.cambiarEstadoError(vo.getCodOpe(), "2", Boolean.TRUE);
							log.warn("Problemas por TimeOut con la licencia " + vo.getNumImpre() + " al tratar de ejecutar servicio " + url2 + " (" + instanciaEndPoint.getNombreOperador(vo.getCodOpe()) + ") en el operador " + vo.getCodOpe());
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
						vo.setGlosaEstado(glosaEstado);
						return enviada;
					}
					/** *************************************************** */

				}

			}catch (Exception e) {
				e.printStackTrace();
				logError(e);
				vo.setGlosaEstado(Helper.reformateaGlosaEstado(e.getMessage()));
				return enviada;
			} 
			/*catch (ConfiguracionException e) {
				logError(e);
				return false;
			} catch (ErrorInvocacionOperadorException e) {
				logError(e);
				return false;
			}*/
			
			
			if (null != respuesta && respuesta.getRespuesta() != null) {
				enviada = "1";
				respWs = respuesta.getRespuesta().getEstado() != 0 ? "1" : "0";
				glosaEstado = respuesta.getRespuesta().getGloEstado();
			}
			
			log.info("[" + urlUtilizada + "] - Licencia:" + numLicencia + "-" + digLicencia + ",	Estado=" + glosaEstado + ",  DEMORO:" + String.valueOf(tiempoUtilizado) + " Milisegundos");
			glosaEstado = Helper.reformateaGlosaEstado(glosaEstado);

			//Actualiza tabla 51
			vo.setEnviada(enviada);
			vo.setRespWs(respWs);
			vo.setGlosaEstado(glosaEstado);
			vo.setFechaRespuesta(Helper.getSdf()); // yyyyMMdd
			vo.setHoraRespuesta(Helper.getShf()); // HHmmss
			
		
		} catch (Exception e) {
			logError(e);
			return Helper.reformateaGlosaEstado(e.getMessage());
		}
		if(respWs.equals("0")){
			log.info("DEVOLUCION EXITOSA");
		}else{
			log.info("DEVOLUCION RESPUETA WS ERRONEA:");
		}
		return enviada;
	}/* FIN */
	
	public Ilfe051VO set051(Ilfe002VO cab_licencia, IlfeOpeVO vo, String rutEmpresa, LabelValueVO err){
		Ilfe051VO vo51 = new Ilfe051VO();
		try {
			
			vo51.setIdeOpe(vo.getIdeOpe());
			vo51.setCodOpe(vo.getCodOpe());
			vo51.setNumImpre(vo.getNumImpre());
			vo51.setCodMot(new Integer(err.getValue()));
			vo51.setGloMot(err.getLabel());
			vo51.setEnviada("1");
			vo51.setFechaProceso(Helper.getSdf());
			vo51.setAfiRut(cab_licencia.getAfiRut());
			vo51.setAfiRutDV(cab_licencia.getAfiRutDv());
			vo51.setAfiNom(cab_licencia.getAfiNom());
			vo51.setEmpRut(new Integer(rutEmpresa.split("-")[0]));
			vo51.setEmpRutDV(rutEmpresa.split("-")[1]);
			vo51.setCodCcaf(vo.getCodCcaf());
			vo51.setPwdCcaf(vo.getPwdCcaf());
			vo51.setUrlOpe(vo.getUrlOpe().trim());
			vo51.setHoraEnvio(Helper.getShf());
			vo51.setFechaEstado(cab_licencia.getFechaEstado()); // yyyyMMdd
			vo51.setHoraEstado(cab_licencia.getHoraEstado()); // HHmmss
			
		} catch (Exception e) {
			log.error("Error al insertar tabla 051, mensaje: " + e.getMessage());
			e.printStackTrace();
		}
		
		return vo51;
		
	}
	
	public boolean insertIlfe051(Ilfe051VO vo){
		try {
			vo.setFechaRespuesta(Helper.getSdf()); // yyyyMMdd
			vo.setHoraRespuesta(Helper.getShf()); // HHmmss
			log.info(":::InsertIlfe051::: " + svc_a.insertIlfe051(vo));
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			log.error(e.getMessage());
			return false;
		}
		return true;
	}
	
	public boolean updateIlfe051(Ilfe051VO vo){
		try {
			vo.setFechaRespuesta(Helper.getSdf()); // yyyyMMdd
			vo.setHoraRespuesta(Helper.getShf()); // HHmmss
			log.info(":::UpdateIlfe051::: " + svc_a.updateIlfe051(vo));
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			log.error(e.getMessage());
			return false;
		}
		return true;
	}
	
	public int existsIlfe051(Ilfe051VO vo){
		int count=0;
		try {
			count= svc_a.existsIlfe051(vo);
			log.info("Existe licencia en estado 99: " + count);
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (SvcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	

	private void logError(Throwable e) {
		log.error(e.getClass() + ": " + e.getMessage());
		// Date now = new Date();
		// svc.insertLog(new LmeLogVO("ERROR", proceso, "",sdf.format(now),
		// shf.format(now), dataTruncation(e.getClass() + "; "+
		// e.getMessage(),500)));
	}
	
}