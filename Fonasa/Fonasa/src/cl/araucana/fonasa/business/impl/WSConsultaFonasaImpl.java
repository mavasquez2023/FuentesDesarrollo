	/**
 * 
 */
package cl.araucana.fonasa.business.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import WSFonaCajasNM.LstEstados;
import WSFonaCajasNM.RespVerEstLicCCAF;
import WSFonaCajasNM.WSFonaCajasSoapStub;

import cl.araucana.fonasa.business.WSConsultaFonasa;
import cl.araucana.fonasa.business.to.RequestWSFonasaTO;
import cl.araucana.fonasa.business.to.ResponseFormFonasaTO;
import cl.araucana.fonasa.dao.VO.FormularioVO;
import cl.araucana.fonasa.utils.ConstantsUtil;
import cl.araucana.fonasa.utils.FechaUtil;

/**
 * @author usist199
 *
 */
public class WSConsultaFonasaImpl implements WSConsultaFonasa{
	protected Logger logger = Logger.getLogger(this.getClass());
	private int num_timeout=0;
	private int num_remoteException=0;
	boolean timeout;
	boolean remoteException;
	public List<FormularioVO> consultaListaFormulario(List<FormularioVO> lista){
		List<FormularioVO>  respuesta= new ArrayList<FormularioVO>();
		logger.info("Cantidad formularios a validar:" + lista.size());
		int num_intentos=Integer.parseInt(ConstantsUtil.RES_CONFIGURACION.getString("araucana.clientewsfonasa.business.intentos"));
		logger.info("Numero reintentos contra WS Fonasa definidos: " + num_intentos);
		
		boolean reintento=true;
		for (int i = 0; i <= num_intentos; i++) {
			if(i==0){
				logger.info("Validación, ciclo: " + i);
			}else{
				logger.info("Validación por timeout, ciclo: " + i);
			}
			
			List<FormularioVO> errores_timeout= new ArrayList<FormularioVO>();
			if(i==num_intentos){
				reintento=false;
			}
			if(lista.size()==0){
				logger.info("Saliendo del ciclo, lista vacía.");
				break;
			}
			int j=0;
			for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
				timeout=false;
				remoteException= false;
				FormularioVO tipoform = (FormularioVO) iterator.next();
				try {

					ResponseFormFonasaTO responseFonasa= consultarEstadoFormulario(tipoform.getTipoFormulario(), tipoform.getNumeroLicencia(), reintento);
					if(responseFonasa!=null){
						tipoform.setResponseWS(responseFonasa);
						respuesta.add(tipoform);
					}else{
						if(remoteException){
							num_remoteException++;
						}else if(timeout){
							errores_timeout.add(tipoform);
						}
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(j%100==0){
					logger.info("Procesando licencia: " + j);
				}
				j++;
				if(num_remoteException%lista.size()>0.05 && respuesta.size()==0){
					logger.info("cantidad de remote_exception: " +  num_remoteException);
					logger.error("Se ha detectado un porcentaje alto de error remoto en conexión a Fonasa y se cancelará proceso");
					respuesta=null;
					break;
				}
			}
			if (respuesta==null){
				break;
			}
			logger.info("cantidad de timeout: " +  errores_timeout.size());
			lista.clear();
			lista.addAll(errores_timeout);
		}
		return respuesta;
	}
		
	public ResponseFormFonasaTO consultarEstadoFormulario(int color, int numeroFormulario, boolean reintento)
			throws Exception {
				
				logger.debug("Consultar estado Formulario, numero:" + numeroFormulario + ", color:" + color);
				RequestWSFonasaTO request= new RequestWSFonasaTO();
				request.setFecCertifica(FechaUtil.getFechaHoy("yyyyMMdd"));
				request.setRutInstitucion(ConstantsUtil.RES_CONFIGURACION.getString("araucana.clientewsfonasa.business.rutinstitucion"));
				request.setCodigoUsuario(ConstantsUtil.RES_CONFIGURACION.getString("araucana.clientewsfonasa.business.codigousuario"));
				request.setClaveUsuario(ConstantsUtil.RES_CONFIGURACION.getString("araucana.clientewsfonasa.business.claveusuario"));
				request.setCodigoAsegurador(Short.parseShort(ConstantsUtil.RES_CONFIGURACION.getString("araucana.clientewsfonasa.business.codigoasegurador")));
				
				ResponseFormFonasaTO objRes= null;
				try {
					if(numeroFormulario <=0 || color>2 || color <=0){
						objRes= new ResponseFormFonasaTO();
						objRes.setEstado(new Short((short)-10)); 
						objRes.setGlosaEstado("Error en los parámetros ingresados al WS");
						return objRes;
					}
					
					
					
					//	Realiza la llamada al WS Fonasa a traves del cliente WS
					//WSFonaCajasSoapProxy proxy = new WSFonaCajasSoapProxy();
					String ep= ConstantsUtil.RES_CONFIGURACION.getString("ep.fonasa.estado.validacion");
					//proxy.setEndpoint(ep);
					WSFonaCajasSoapStub stub= new WSFonaCajasSoapStub();
					stub.setTimeout(10000);
					stub._setProperty(WSFonaCajasSoapStub.ENDPOINT_ADDRESS_PROPERTY, ep);
					RespVerEstLicCCAF obj=null;
					try {
						obj = stub.verEstLicCCAF(request.getCodigoUsuario(), request.getClaveUsuario(), request.getCodigoAsegurador(), request.getRutInstitucion(), (short)color, numeroFormulario);
						timeout=false;
					} catch (Exception e) {
						logger.info("Timeout para licencia:" + numeroFormulario);
						timeout=true;
					}
					//RespVerEstLicCCAF obj = proxy.verEstLicCCAF(request.getCodigoUsuario(), request.getClaveUsuario(), request.getCodigoAsegurador(), request.getRutInstitucion(), (short)color, numeroLicencia);

					if (obj != null) {
						//		Recibe respuesta desde WS
						LstEstados[] listaEstados= obj.getListaEstados();
						String ultima_fecha="0";
						short ultimo_estado=0;
						String comentario="";
						if(obj.getEstado()==0){
							for (int i = 0; i < listaEstados.length; i++) {
								String fechaEvento= listaEstados[i].getFecEvento();
								if(Integer.parseInt(fechaEvento)>=Integer.parseInt(ultima_fecha)){
									ultima_fecha= fechaEvento;
									ultimo_estado= listaEstados[i].getCodEstado();
									comentario= listaEstados[i].getComentario();
								}
							}
							if(ultimo_estado!=72 && ultimo_estado!=90){
								objRes = new ResponseFormFonasaTO(obj.getEstado(),	obj.getGloEstado(), ultimo_estado, comentario);
							}
						}else{
							String glosaEstado= obj.getGloEstado();
							if(obj.getEstado()==2){
								glosaEstado="Licencia se encuentra tramitada por otra entidad";
							}
							objRes = new ResponseFormFonasaTO(obj.getEstado(), glosaEstado, ultimo_estado, comentario);
							
						}

					}else{
						if(!reintento){
							objRes = new ResponseFormFonasaTO((short)-1, "Timeout", (short)0, "");
							num_timeout++;
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Ocurrio un problema en la llamada al WS Fonasa, mensaje:" + e.getMessage());
					remoteException=true;
				}
				return objRes;
			}
	
	public int getNum_timeout() {
		return num_timeout;
	}

	public void setNum_timeout(int num_timeout) {
		this.num_timeout = num_timeout;
	}
	
}
