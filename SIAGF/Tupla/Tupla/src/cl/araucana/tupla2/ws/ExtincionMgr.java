/**
 * 
 */
package cl.araucana.tupla2.ws;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;

import cl.araucana.tupla2.struts.bussiness.TO.DatosExtinsionTO;
import cl.araucana.tupla2.struts.bussiness.TO.RetornoTO;
import cl.araucana.tupla2.struts.bussiness.TO.TuplaTO;
import cl.araucana.tupla2.struts.utils.ConstruyeXml;
import cl.araucana.tupla2.struts.utils.RutUtil;
import cl.araucana.tupla2.struts.utils.Utiles;
import cl.araucana.tupla2.struts.utils.XmlParse;
import cl.araucana.util.LoadPropertiesFile;
import cl.araucana.wssiagf.vo.DataRequest;
import cl.araucana.wssiagf.vo.DataResponse;
import cl.araucana.wssiagf.vo.ResponseWS;
import cl.paperless.siagf.ws.AutenticacionPortTypeProxy;
import cl.paperless.siagf.ws.ConsultaCausantePortTypeProxy;
import cl.paperless.siagf.ws.ExtincionReconocimientoPortTypeProxy;

/**
 * @author IBM Software Factory
 *
 */
public class ExtincionMgr {
	private Logger log = Logger.getLogger(this.getClass());
	@SuppressWarnings("finally")
	public ResponseWS extingueTrabajador(List<DataRequest> listaCausantes) {
		XmlParse parse = new XmlParse();
		ConstruyeXml oConstruye = new ConstruyeXml();
		RetornoTO oRetorno = new RetornoTO();
		ResponseWS respuesta= new ResponseWS();
		int codigoExtincion=0;
		String mensajeExtincion="";
		int conerrores=0;
		List<DataResponse> listaSalida= new ArrayList<DataResponse>();
		try {
			//Archivo de configuracion
			Properties properties = new LoadPropertiesFile().load("wssiagf.properties");

			//WebServices utilizados
			AutenticacionPortTypeProxy autentica = new AutenticacionPortTypeProxy();
			autentica.setEndpoint(properties.getProperty("webService.Autenticacion"));
			ExtincionReconocimientoPortTypeProxy extincion = new ExtincionReconocimientoPortTypeProxy();
			extincion.setEndpoint(properties.getProperty("webService.ExtincionReconocimiento"));
			ConsultaCausantePortTypeProxy consulta = new ConsultaCausantePortTypeProxy();
			consulta.setEndpoint(properties.getProperty("webService.ConsultaCausante"));

			//Autentificacion con WS
			String token = autentica.login(new Integer(properties.getProperty("credential.id").trim()), properties.getProperty("credential.userName"), properties.getProperty("credential.password"));
			String parsetoken = parse.parsearXMLautentica(token);
			
			String estado="0";
			for (int i = 0; i < listaCausantes.size(); i++) {
				DataRequest trabajadorDB2 = (DataRequest) listaCausantes.get(i);
				try {
					int causanteValidos=0;
					int causantesActualizados=0;

					//se valida parámetros entrada
					if(!RutUtil.IsRutValido(trabajadorDB2.getRutTrabajador())
						|| !RutUtil.IsRutValido(trabajadorDB2.getRutEmpresa())
						|| !Utiles.validaFechaSIAGF(trabajadorDB2.getFechaEmision())
						|| !Utiles.validaFechaSIAGF(trabajadorDB2.getFechaExtincion())){
						estado="-2";
						conerrores++;
					}else{
						//Consulta del causante desde WS
						String respuestaConsulta = consulta.consultaCausante(parsetoken, trabajadorDB2.getRutTrabajador());
						List listaTuplas = parse.parseaXmlTupla(respuestaConsulta);
						log.info("[Extingir]" + i + ".-Largo de Tupla de consulta causante:" + listaTuplas.size());
						log.info("[Extingir] Xml respuesta ConsultaCausante: " + respuestaConsulta);

						log.info("Evaluando CodEntidadAdm = 10105, CodEstadoTupla = 3, Empresa(WS) = Empresa(SIAGF), Trabajador(WS) = Beneficiario (SIAGF)");
						for (int j = 0; j < listaTuplas.size(); j++) {
							TuplaTO causanteWS = (TuplaTO) listaTuplas.get(j);
							log.info("Cod Entidad Adm: " + causanteWS.getCodEntidadAdm() + ", Cod Estado Tupla: " + causanteWS.getCodEstadoTupla());

							if (causanteWS.getCodEntidadAdm().trim().equalsIgnoreCase("10105") && causanteWS.getCodEstadoTupla().trim().equalsIgnoreCase("1") 
									&& causanteWS.getRutEmpleador().trim().equalsIgnoreCase(trabajadorDB2.getRutEmpresa().split("-")[0]) && causanteWS.getRutBeneficiario().equals(trabajadorDB2.getRutTrabajador().split("-")[0])) {
								causanteValidos++;
								DatosExtinsionTO causanteDB2 = new DatosExtinsionTO();
								causanteDB2.setCodigoExtincion(properties.getProperty("webService.ExtingueCausante.codigo"));
								causanteDB2.setFechaEmision(trabajadorDB2.getFechaEmision());
								causanteDB2.setFechaExtincion(trabajadorDB2.getFechaExtincion());
								causanteDB2.setFechaRec(causanteWS.getFecRecCausante());
								causanteDB2.setRutCausante(causanteWS.getRutCausante());
int ok=0;
								String xmlExtinsion = oConstruye.creaXmlExtinsion(causanteDB2, causanteWS);
								log.info("[Extingir]" + i + ".-" + j + ".-XML de Extinsion:" + xmlExtinsion);
								String respuestaExtincion = extincion.extincionReconocimiento(parsetoken, xmlExtinsion);

								//Almacena respuesta de la extincion en DB2
								log.info("[Extingir]" + i + ".-" + j + ".-XML de respuesta:" + respuestaExtincion);
								oRetorno = parse.parsearXMLRetorno(respuestaExtincion);
								String codigoRetorno= oRetorno.getCodigo();
								if(codigoRetorno.equals("0")){
									causantesActualizados++;
								}
							}
						}
						if(causanteValidos==causantesActualizados){
							estado="1";
						}

					}
				} catch (Exception ex) {
					estado="-1";
					conerrores++;
					log.warn("Warning, mensaje:" + ex.getMessage());
					ex.printStackTrace();
				}
				
				DataResponse respuestaTra= new DataResponse();
				respuestaTra.setRutEmpresa(trabajadorDB2.getRutEmpresa());
				respuestaTra.setRutTrabajador(trabajadorDB2.getRutTrabajador());
				respuestaTra.setEstado(estado);
				listaSalida.add(respuestaTra);
				
			}
			if(conerrores==0){
				codigoExtincion=1;
				mensajeExtincion="Todos los registros procesados";
			}else{
				codigoExtincion=2;
				mensajeExtincion="Uno o mas registros procesados con error";
			}
		} catch (Exception e) {
			codigoExtincion=-1;
			log.error("Error, mensaje:" + e.getMessage());
			mensajeExtincion="Error, registros no procesados: " + e.getMessage() ;
			e.printStackTrace();
		}
		finally{
			respuesta.setCodigo(codigoExtincion);
			respuesta.setDescripcion(mensajeExtincion);
			respuesta.setData(listaSalida);
			return respuesta;
		}
	}
}
