/*
 * Created on 11-10-2011
 *
 */
package cl.araucana.lme.job;

import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
//import java.util.TimeZone;

import org.apache.log4j.Logger;
import conector.vo.SalidaLMEDetLcc;
import cl.araucana.lme.helper.Helper;
import cl.araucana.lme.ibatis.domain.Ilfe002VO;
import cl.araucana.lme.ibatis.domain.Ilfe004VO;
import cl.araucana.lme.ibatis.domain.Ilfe021VO;
import cl.araucana.lme.ibatis.domain.Ilfe051VO;
import cl.araucana.lme.ibatis.domain.IlfeOpeVO;
import cl.araucana.lme.svc.IAS400Svc_LME;
import cl.araucana.lme.svc.SvcFactory_LME;
import cl.araucana.lme.svc.exception.SvcException;
import cl.araucana.lme.util.Configuraciones;
import cl.araucana.lme.util.EndPointUtil;
import cl.araucana.lme.util.LabelValueVO;


/**
 * @author j-factory
 * 
 */
public class JobControllerService {

	// private LoggerHelper logger = new LoggerHelper();
	private Logger log = Logger.getLogger(this.getClass());


	private IAS400Svc_LME svc_a = null;
	private String operador = "";
	private boolean logDetail = false;
	private Date dateLcc = null;
	
	public JobControllerService(){
		svc_a = SvcFactory_LME.getAS400Svc_LME();
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
		String dataInf = ":::" + processName + ", Inicio.";
		log.info(dataInf);
	}

	public void consumirEstadosLME() {

		startProcess("Consumir Estados 31,41,51 LME");
		String dataInf = "";
		boolean grabaZonaC= false;
		int validadas=0;
		int devueltas=0;
		int consumoZonaC=0;
		int totales=0;
		
		try {

			/**BUSCO OPERADORES***************************************/
			IlfeOpeVO ilfeOpeVO = new IlfeOpeVO();
			ilfeOpeVO.setStsOpe(Integer.valueOf("1"));
			List l = svc_a.getIlfeOpe(ilfeOpeVO);
			if (null != l && l.size() > 0) {

				// auxiliar distinto a un codigo de operador
				int aux = -999;
				for (Iterator iter = l.iterator(); iter.hasNext();) {
					IlfeOpeVO vo = (IlfeOpeVO) iter.next();
					/*
					 * la primera vez pasa correctamente el codigo de operador y
					 * para las otras opciones se valida si el operador tenia
					 * errores y se salta al siguiente ciclo del for
					 */
					validadas=0;
					devueltas=0;
					consumoZonaC=0;

					int codOpeInt = Integer.parseInt(vo.getCodOpe());
					if (aux == codOpeInt) {
						Boolean errorTotal;
						try {
							errorTotal = EndPointUtil.getInstance().getEstadoError(String.valueOf(vo.getCodOpe()));
							if (errorTotal != null && errorTotal == Boolean.TRUE) {
								continue;
							}
						} catch (Exception e2) {
							// e2.printStackTrace();
							log.error(e2.getClass() + ": " + e2.getMessage());
						}
					} else {
						aux = codOpeInt;
					}
					operador = vo.getCodOpe();

					/**CONSUMO CABECERA***************************************/

					//Invocando servicio Cabeceras
					log.info("Se busca en 2R licencias estado 31, 41 o 51");
					Consumir2R consumirLCC= new Consumir2R();
					List licencias= consumirLCC.consumirEstadoLME(vo);
					totales= licencias.size();
					dataInf = "Licencias Filtradas [" + totales + "]";
					log.info(dataInf);
					int conteo=1;
					for (Iterator iterator = licencias.iterator(); iterator.hasNext();) {
						log.info("Procesando " + conteo + "/" + totales);
						boolean isDevolucion= true;

						try {
							Ilfe002VO cab_licencia = (Ilfe002VO) iterator.next();
							vo.setNumImpre(String.valueOf(cab_licencia.getNumImpre()));
							vo.setDigLicencia(cab_licencia.getDvImpre());
							vo.setNumLicencia(String.valueOf(cab_licencia.getNumImpre()));
							vo.setEstado(String.valueOf(cab_licencia.getEstadoLicencia()));
							
							log.info("LIC=" + cab_licencia.getNumImpre() + ", ESTADO:" + cab_licencia.getEstadoLicencia() + ", CODOPE=" + vo.getCodOpe() + ", CODCCAF=" + vo.getCodCcaf() + ", PWDCCAF=" + vo.getPwdCcaf());
							
							int estadoLicencia= cab_licencia.getEstadoLicencia().intValue();
							grabaZonaC= false;
							LabelValueVO err= new LabelValueVO();
							
							//se busca el RutEmpresa que se necesita para insertar en 021 o 051
							Map param04= new HashMap();
							param04.put("ideOpe", vo.getIdeOpe());
							param04.put("numImpre",new Integer(vo.getNumImpre()));
							Integer rutEmpresa=new Integer(0);
							String dvEmpresa="0";
							Ilfe004VO ilfe004=null;
							
							//Estado 51 siempre VALIDAR Op.
							err.setValue("0");
							err.setLabel("OK");
							
							String estadoValidacion="";
							
							//Si es estado 31 o 41 se devuelve
							if(estadoLicencia==31 || estadoLicencia==41){
								
								err.setValue("4");
								err.setLabel("Liq.de sueldo no Acompaña");
								log.info("se busca el RutEmpresa que se necesita para insertar en 021 o 051 en tabla 009R");
								ilfe004= (Ilfe004VO) svc_a.getIlfe009R(param04);
								if(ilfe004!=null){
									rutEmpresa=ilfe004.getEmpRut();
									dvEmpresa= ilfe004.getEmpRutDv();
								}
								if(excedeDevoluciones(cab_licencia)){
									isDevolucion= false;
									estadoValidacion="P";
								}
								
							}
							//Si estado es 51, valido si tiene ZonaC
							else if(estadoLicencia==51){

								log.info("se busca el RutEmpresa que se necesita para insertar en 021 o 051 en tabla 004R");
								ilfe004= (Ilfe004VO) svc_a.getIlfe004R(param04);
								if(ilfe004==null){
									log.info("se intenta obtener ZonaC ");
									if(grabarZonaC(vo, err)){
										consumoZonaC++;
										isDevolucion= false;
									}else{
										err.setValue("4");
										err.setLabel("Liq.de sueldo no Acompaña");
										isDevolucion= true;
									}
								}else{
									rutEmpresa=ilfe004.getEmpRut();
									dvEmpresa= ilfe004.getEmpRutDv();
								}
								
								//Se valida si es Afiliado
								boolean isAfiliado= svc_a.getAF03F1(rutEmpresa.intValue(), cab_licencia.getAfiRut().intValue());
								if(isAfiliado){
									isDevolucion= false;
								}else{
									log.info("Afiliado No tiene calidad de Trabajador");
									//Devolver porque no existe afiliado
									err.setValue("2");
									err.setLabel("No tiene calidad de trabajador");
									isDevolucion= true;
								}

								
								if(isDevolucion && excedeDevoluciones(cab_licencia)){
									isDevolucion= false;
									estadoValidacion="P";
								}
								
							}

							
							//Devolución 
							if(isDevolucion){
								//51 sin zona C o No afiliada y 31 y 41 normales (que no excede máximo)
								startProcess("DevolverLicenciaLME");
								log.info("CONSUMO DEVOLUCIÓN");
								
								//Se setea objeto 051
								DevolverLicencia devolver= new DevolverLicencia();
								Ilfe051VO  vo51= devolver.set051(cab_licencia, vo, rutEmpresa+"-"+dvEmpresa, err);
								
								//se devuelve licencia a operador
								log.info("Devolver licencia a operador: " + cab_licencia.getNumImpre());
								String resultadoDevolucion= devolver.devolverLicenciaME051(vo51);
								
								//If resultadoDevolución == 1 significa que logró ser enviada licencia al operador, 
								//independiente si resultado Devolución fue exitosa o no (vo.getRespWs()).
								//caso contrario se inserta en 51 con RsWS=9
								svc_a.startTransaction();
	
								if(resultadoDevolucion.equals("1")){
									devueltas++;
									//RespWS puede ser 0 en caso de Devolución exitosa o 1 en caso de rechazada
								}else{
									vo51.setRespWs("9");
								}
								if(devolver.existsIlfe051(vo51)>0){
									//se acyualiza tabla 051
									log.info("se actualiza tabla 051");
									devolver.updateIlfe051(vo51);
								}else{
									//se inserta tabla 051
									log.info("se inserta tabla 051");
									devolver.insertIlfe051(vo51);
								}
								
								svc_a.commitTransaction();
								svc_a.endTransaction();
								
							}
							//Validación
							else{
								//estado 51 con Zona C o 31, 41 y 51 que exceden el límte de devoluciones.
								//31, 41 y 51 que exceden el límite de devoluciones no deben validarse contra la AF03F1 y deben ser 'Validadas' como Parcial
								log.info("CONSUMO VALIDACION");
								
								int indiceConvenio=0;
								ValidarLicencia validar = new ValidarLicencia();
								if(estadoValidacion.equals("")){
									log.info("Se busca Estado de Validación Total o Parcial");
									estadoValidacion= validar.getEstadoValidacion(cab_licencia);

									log.info("Estado Validación:" + estadoValidacion);
									//T: Total cuando es continua o mismo periodo que licencia anterior (Deprecado)
									//TC: Total cuando es Curativa
									//PC: Parcial Curativa
									
									//se obtine Indice de Convenio
									log.info("se obtine Indice de Convenio");
									indiceConvenio=svc_a.getIndiceConvenio(rutEmpresa.intValue(), cab_licencia.getAfiRut().intValue());
								}
								
								startProcess("ValidaciónLME");
								Ilfe021VO vo21=null;
				
								log.info("Validación LME en operador: " + cab_licencia.getNumImpre());
								String resultadoValidacion="";
								if(!estadoValidacion.equals("")){
									vo21=validar.set021(cab_licencia, vo, rutEmpresa, dvEmpresa, estadoValidacion, 0);
									
									resultadoValidacion= validar.validarLicenciaME(vo21);
								}
								//If resultadoValidacion == 1 significa que logró ser enviada licencia al operador, 
								//independiente si resultado Validación fue exitosa o no (vo.getRespWs()).
								svc_a.startTransaction();
								
								if(resultadoValidacion.equals("1")){
									validadas++;
									
									//Insert 21
									log.info("Insert 21");
									boolean resultado= validar.insertIlfe021(vo21);

									if(resultado && estadoLicencia==51){
										//Actualiza 8600
										log.info("Se actualiza 8600");
										resultado= validar.actualizar8600(cab_licencia, ilfe004, estadoValidacion, indiceConvenio);

										if(resultado){
											log.info("Commit trasaction");
											svc_a.commitTransaction();
										}
									}
									
								}else{
									log.info("Licencia no se pudo Validar, mensaje: " + vo.getGlosaEstado());
								}						
								svc_a.endTransaction();
							}
								
							
						} catch (Exception e) {
							logError(e);
							log.error("Error, continua con sigiente licecnia");
							svc_a.endTransaction();
							e.printStackTrace();
						}
						conteo++;
					}

					try {
						log.info("validadas= " +validadas+ ", devueltas= " + devueltas);
						if ((validadas+ devueltas)>0){
							generarEstadisticas(vo.getCodOpe(), totales, validadas, devueltas, consumoZonaC);
						}
						//svc_a.closeConnection();
					} catch (Exception e) {
						log.error(e.getMessage());
					}
				}
			}

		} catch (Throwable e) {
			logError(e);
			e.printStackTrace();
		}
		operador = "";
		endProcess("Consumir Estados 31,41,51 LME", dataInf);
	}
	
	private boolean grabarZonaC(IlfeOpeVO vo, LabelValueVO err){
		/**CONSUMO DETALLE***************************************/
		boolean exito= false;
		try {
			setDateLcc(new Date());
			startProcess("ConsumirDetallesLME");
			log.info("ConsumirDetallesLME");
			ConsumirDetalle detalle= new ConsumirDetalle();
			SalidaLMEDetLcc respuesta= detalle.consumirDetalleLME(vo);

			if ( null != respuesta && null != respuesta.getRespuesta()){
					if(null != respuesta.getRespuesta().getZonaC()){
						log.info("Grabando Zona C");
						svc_a.startTransaction();
						exito= detalle.grabarZonaC(respuesta.getRespuesta(), vo);
						if (exito){
							
							//Actualizar 2R
							Map h = new Hashtable();
							h.put("ideOpe", vo.getIdeOpe());
							h.put("numImpre", vo.getNumLicencia());
							err.setLabel("Se graba Zona C");
							h.put("label", err.getLabel().toString());
							log.info("Update 2R, mensaje: " + h.get("label"));
							svc_a.updateIlfe002RError(h);
							
							//Commit
							svc_a.commitTransaction();
						}
						svc_a.endTransaction();
						return exito;
					}

			}/*else{
				log.warn("No se obtuvo respuesta del WS");
				err.setValue("-1");
				err.setLabel("No se obtuvo respuesta del WS");
			}*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean excedeDevoluciones(Ilfe002VO cab_licencia){
		try {
			//Se valida cantidad máxima Devoluciones
			log.info("Se valida cantidad máxima Devoluciones");
			int maxdev= Integer.parseInt(Configuraciones.getConfig("devolucion.cantidad"));
			int count_dev= svc_a.getIlfe051(cab_licencia);
			if(count_dev >= maxdev){
				log.info("licencia excede el límite de devoluciones, " + count_dev + " de " + maxdev);
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SvcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private void generarEstadisticas(String codope, int total, int validacion, int devolucion, int zonaC) {
		// TODO Apéndice de método generado automáticamente
		HashMap est= new HashMap();
		est.put("fechaEvento", Helper.getSdf());
		est.put("horaEvento", Helper.getShf());
		est.put("codOpe", codope);
		est.put("total", new Integer(total));
		est.put("devolucion", new Integer(devolucion));
		est.put("validacion", new Integer(validacion));
		est.put("zonac", new Integer(zonaC));
		log.info("Insertando estadísticas Operador " + codope + ", total= " + total + ", validacion=" + validacion + ", devolucion=" + devolucion);
		svc_a.insertEstadistica(est);
	}
	
	
	private List getPeriodosAnterior(String periodo, List lista){
		int year= Integer.parseInt(periodo.substring(0, 4));
		int mes= Integer.parseInt(periodo.substring(4, 6));
		mes= mes -1;
		if(mes==0){
			mes=12;
			year--;
		}
		lista.add(new Integer(year*100+mes));
		if(lista.size()==3 || lista.size()==6){
			return lista;
		}
		return getPeriodosAnterior(String.valueOf(year*100+mes), lista);
	}

	private void logError(Throwable e) {
		log.error(e.getClass() + "; " + e.getMessage());
		// Date now = new Date();
		// svc.insertLog(new LmeLogVO("ERROR", proceso, "",sdf.format(now),
		// shf.format(now), dataTruncation(e.getClass() + "; "+
		// e.getMessage(),500)));
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
	

}
