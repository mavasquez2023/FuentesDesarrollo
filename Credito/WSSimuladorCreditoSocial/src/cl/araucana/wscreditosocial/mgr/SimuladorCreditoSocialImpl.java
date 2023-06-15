package cl.araucana.wscreditosocial.mgr;


import cl.araucana.wscreditosocial.util.ConstantesRespuestasWS;
import cl.araucana.wscreditosocial.util.SimuladorServicesUtil;
import cl.araucana.wscreditosocial.vo.RequestWS;
import cl.araucana.wscreditosocial.vo.ResponseWS;
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

@WebService( portName = "SimuladorCreditoSocialPort",
			serviceName = "SimuladorCreditoSocialService",
			targetNamespace = "http://servicios.laaraucana.cl/simuladorCreditoSocial"
)
public class SimuladorCreditoSocialImpl implements SimuladorCreditoSocial{
		
	@Resource
    private WebServiceContext wsCtx;
	
	Logger log = Logger.getLogger(this.getClass());
	

	/**
		* 
		* @throws SOAPException 
	 * @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
		*/
	
	
	private boolean isValidParams(RequestWS req){
		
		try {
			if(req.getMonto()<=0){
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		try {
			if(req.getCuotas()<=0){
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		try {
			if(!req.getTipo_afiliado().equals("1") && !req.getTipo_afiliado().equals("2")){
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		try {
			if(!req.getSeguro_cesantia().equalsIgnoreCase("SI") && !req.getSeguro_cesantia().equalsIgnoreCase("NO")){
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		try {
			if(!req.getSeguro_desgravamen().equalsIgnoreCase("SI") && !req.getSeguro_desgravamen().equalsIgnoreCase("NO")){
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	
		
	@WebMethod(action="http://servicios.laaraucana.cl/credito/getSimuladorCreditoSocial",  operationName="getSimuladorCreditoSocial")
	public ResponseWS getSimuladorCreditoSocial(@WebParam(name="request") @XmlElement(name="request", required=true) RequestWS req) throws SOAPException {
		MessageContext mctx = wsCtx.getMessageContext();
		log.info("EN getSimuladorCreditoSocial");
		int isAfiliado=0;
		boolean param;
		
		ResponseWS response=new ResponseWS();

		int i=0;
		try {
			 
			//Validando datos de entrada
			if(isValidParams(req)){
				log.info("Parámetros de consulta válidos");
				response= SimuladorServicesUtil.getResultadoSimulacion(req);
				log.info("Código respuesta SAP:" + response.getLog());
				if(response.getLog().equals(ConstantesRespuestasWS.COD_RESPUESTA_SAP_NOK)){
					i=-1;
				}
			}else{
				//parámetros no válidos
				i=-2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			i=-1;
		}finally{
			if(i==-1){
				//mctx.put("tipo", "Server");
				//throw new SOAPException("Error Interno del Servicio:");
				response.setLog(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
			}else if(i==-2){
				//mctx.put("tipo", "Client");
				//throw new SOAPException("Parámetros No Válidos");
				response.setLog(ConstantesRespuestasWS.COD_RESPUESTA_PARAM);
			}else{
				response.setLog(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS);
			}
			log.info("Código log respuesta:" + response.getLog());
			return response;
		}

	}
	
	
	/**
	 * Metodo para cambiar los caracteres especiales.
	 * */
	/*private StringBuffer ChangeCharacter(StringBuffer param) {

		char[] letras =
			{ 'Á', 'É', 'Í', 'Ó', 'Ú', 'À', 'È', 'Ì', 'Ò', 'Ù', 'Ä', 'Ë', 'Ï', 'Ö', 'Ü', 'Ñ', 'á', 'é', 'í', 'ó', 'ú', 'à', 'è', 'ì', 'ò', 'ù', 'ä', 'ë', 'ï', 'ö', 'ü', 'ñ', '°' };
		char[] letrasM =
			{ 'A', 'E', 'I', 'O', 'U', 'A', 'E', 'I', 'O', 'U', 'A', 'E', 'I', 'O', 'U', 'N', 'a', 'e', 'i', 'o', 'u', 'a', 'e', 'i', 'o', 'u', 'a', 'e', 'i', 'o', 'u', 'n', ' ' };
		int pos = 0;

		String param1 = null;

		for (int i = 0; i < letras.length; i++) {
			while ((pos = param.toString().indexOf(letras[i])) != -1) {
				param.setCharAt(pos, letrasM[i]);
			}
		}
		return param;
	} */
	
	/*public static void main(String[] args) {
		TesoreriaImpl tgr= new TesoreriaImpl();
		RequestWSTGR req= new RequestWSTGR();
		req.setPeriodo(201703);
		req.setRut_empleador(70016160);
		req.setRut_trabajador(10104927);
		ResponseWSTGR salida= tgr.getCotizacionesByTrabajador(req);
		
	}*/
	
}