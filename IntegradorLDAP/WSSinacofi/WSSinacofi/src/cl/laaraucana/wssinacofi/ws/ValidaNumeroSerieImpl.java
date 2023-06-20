package cl.laaraucana.wssinacofi.ws;

import cl.laaraucana.wssinacofi.ibatis.dao.AdministracionDAO;
import cl.laaraucana.wssinacofi.ibatis.dao.BitacoraDAO;
import cl.laaraucana.wssinacofi.services.ClienteSinacofiImpl;
import cl.laaraucana.wssinacofi.vo.ConstantesRespuestasWS;
import cl.laaraucana.wssinacofi.vo.EntradaSinacofiVO;
import cl.laaraucana.wssinacofi.vo.SalidaSinacofiVO;
import java.util.*;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import org.apache.log4j.Logger;
import com.ibm.trl.soap.SOAPException;


/** 
 * @version 	1.0
 * @author Claudio Lillo
 *  
 */

@WebService( portName = "ValidaNumeroSeriePort",
			serviceName = "ValidaNumeroSerieService",
			targetNamespace = "http://servicios.laaraucana.cl/validaNumeroSerie"
)

public class ValidaNumeroSerieImpl implements ValidaNumeroSerie{
	
	@Resource
    private WebServiceContext wsCtx;
	
	Logger log = Logger.getLogger(this.getClass());
	
	private boolean isValidParams(String rut, String serie){
		if(rut==null || rut.equals("") || rut.length()>10){
			return false;
		}
		if(serie==null || serie.equals("") || serie.length()>14){
			return false;
		}
		return true;
	}
	
	
	@WebMethod(action="http://servicios.laaraucana.cl/sinacofi/validaNumeroSerie",  operationName="validaNumeroSerie")
	public ResponseWS validaNumeroSerie(@WebParam(name="credenciales") @XmlElement(name="credenciales", required=true) CredencialesWS param, @WebParam(name="cedula") @XmlElement(name="cedula", required=true) RequestWS request) throws SOAPException{
		MessageContext mctx = wsCtx.getMessageContext();
		log.info("En validaNumeroSerie Sinacofi");
		
		ResponseWS response=new ResponseWS();
		String usuario="";
		try {
			
			usuario= param.getUsuario();
			String password= param.getPassword();
			if(usuario==null || usuario.equals("") || password==null || password.equals("")){
				response.setCodigoRetorno("99999");
				response.setMensaje("Usuario o Contraseña no válido");
			}else{
				AdministracionDAO adminDao= new AdministracionDAO();
				Map dataUsuario= adminDao.validaUsuarioWS(param.getUsuario());
				if(dataUsuario==null || !dataUsuario.get("password").toString().equals(password)){
					response.setCodigoRetorno("99999");
					response.setMensaje("Usuario o Contraseña no válido");
					log.warn("Usuario o contraseña no válido:" + usuario);
				}else{
					//Validando datos de entrada
					if(isValidParams(request.getRut(), request.getNumero_serie())){
						log.info("Obteniendo Estado Afiliación Empresa RUT Empresa:" + request.getRut());

						SalidaSinacofiVO salida =  consultaSinacofi(request.getRut(), request.getNumero_serie());
						response.setCedulaVigente(salida.getCedulaVigente());
						response.setCodigoRetorno(salida.getCodigoRetorno());
						response.setExisteDetalle(salida.getExisteDetalle());
						response.setMensaje(salida.getMensaje());
						response.setNumeroRegistros(salida.getNumeroRegistros());

					}else{
						response.setCodigoRetorno("10001");
						response.setMensaje("Rut o número serie vo válido");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response.setCodigoRetorno("00000");
			response.setMensaje(e.getMessage());

		}finally{
			//Insertando bitácora
			Map<String, String> bita_param= new HashMap<String, String>();
			bita_param.put("usuario", usuario);
			bita_param.put("rut", request.getRut() );
			bita_param.put("vigente", response.getCedulaVigente());
			bita_param.put("codigo", response.getCodigoRetorno());
			bita_param.put("mensaje", response.getMensaje());
			

			BitacoraDAO.insertBitacora(bita_param);

		}
		return response;

	}
	
	private SalidaSinacofiVO consultaSinacofi(String rut, String serie){
		log.info("Consultando Sinacofi, Rut:" + rut + ", serie=" + serie);
		//Consulta Sinacofi
		String mensaje="";
		ClienteSinacofiImpl client= new ClienteSinacofiImpl();
		EntradaSinacofiVO entrada= new EntradaSinacofiVO();
		entrada.setRut(rut);
		entrada.setSerie(serie);
		SalidaSinacofiVO salida2=null;
		try {
			salida2 = (SalidaSinacofiVO)client.call(entrada);
			
			if(salida2.getCodigoError()==ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS){
				log.info("Respuesta Sinacofi, codigo retorno= " + salida2.getCodigoRetorno());
				if(salida2.getCodigoRetorno().equals("10000")){
					log.info("Cedula Vigente=" + salida2.getCedulaVigente() + ".");
					if(salida2.getCedulaVigente().equals("NO")){
						log.info("Respuesta a Cédula NO Vigente");
						mensaje= "Cédula NO Vigente";
					}else{
						log.info("Respuesta a Cédula Vigente");
						//Exito en ambas validaciones
						mensaje="Cédula Vigente";
					}
				}else{
					if(salida2.getCodigoRetorno().equals("10001")){
						mensaje="Error en parámetros de entrada";
					}else if(salida2.getCodigoRetorno().equals("10002")){
						mensaje="Error interno del servicio";
					}else if(salida2.getCodigoRetorno().equals("10003")){
						mensaje="Error en la autenticación del usuario";
					}else if(salida2.getCodigoRetorno().equals("10004")){
						mensaje="Error de permiso";
					}else if(salida2.getCodigoRetorno().equals("10005")){
						mensaje="RUT no válido";
					}
				}
			}else{
				log.info("Servicio Sinacofi Error");
				//Si servicio SINACOFI no responde se notifica error
				mensaje=salida2.getMensaje();
				salida2.setCodigoRetorno("00000");

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
			salida2.setMensaje(mensaje);

			return salida2;
		}
	}
	
}