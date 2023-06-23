package cl.araucana.tgr.mgr;

import cl.araucana.tgr.dao.CotizacionDAO;
import cl.araucana.tgr.util.TokenFactory;
import cl.araucana.tgr.util.Utiles;
import cl.araucana.tgr.vo.CotizacionVO;
import cl.araucana.tgr.vo.CredentialWSTGR;
import cl.araucana.tgr.vo.DatoEntradaVO;
import cl.araucana.tgr.vo.DatosEntradaVO;
import cl.araucana.tgr.vo.EmpleadorVO;
import cl.araucana.tgr.vo.RequestsWSTGR;
import cl.araucana.tgr.vo.ResponseWSTGR;
import cl.araucana.tgr.vo.TrabajadorVO;
import cl.araucana.tgr.vo.RequestWSTGR;
import java.util.*;

import javax.annotation.Resource;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.log4j.Logger;

import com.ibm.trl.soap.SOAPException;
import com.ibm.ws.http.HttpRequest;


/** 
 * @version 	1.0
 * @author Claudio Lillo
 *  
 */

@WebService( portName = "ConsultaCotizacionesPort",
			serviceName = "ConsultaCotizacionesService",
			targetNamespace = "http://servicios.laaraucana.cl/bonificaTGR"
)
@HandlerChain(file = "/etc/serversoaphandler.xml")
public class ConsultaCotizacionesImpl implements ConsultaCotizaciones{
	static ResourceBundle credentials = ResourceBundle.getBundle("etc/credential");
	
	@Resource
    private WebServiceContext wsCtx;
	
	Logger log = Logger.getLogger(this.getClass());
	/**
	 * ESTA CLASE PERMITIR ARMAR UN XML CON LAS COTIZACIONES DE REMUNERACIONES Y GRATIFICACIONES PARA DAR SERVICIO A LA TESORERIA GENERAL DE LA REPUBLICA
	 * PARA ACCESO DE DATOS SE USA IBATIS
	 */

	/**
		* 
		* @throws SOAPException 
	 * @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
		*/
	@WebMethod(action="http://servicios.laaraucana.cl/bonificaTGR/getStatus",  operationName="getStatus")
	//@RequestWrapper(localName = "getStatusWS",
   // className = "cl.araucana.tgr.vo.TokenVOWrapper")
	public Boolean getStatus(@WebParam(name="token") @XmlElement(name="token", required=true) String token) throws SOAPException{
		MessageContext mctx = wsCtx.getMessageContext();
	/*	Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);

		List userList = (List) http_headers.get("Username");
		List passList = (List) http_headers.get("Password");
		if (userList != null) {
			// get username
			username = userList.get(0).toString();
		}
		if (passList != null) {
			// get password
			password = passList.get(0).toString();
		}
*/
		log.info("EN getStatus");
		if(!isValidToken(token)){
			mctx.put("tipo", "Client");
			log.warn("Token No Válido");
			throw new SOAPException("Token No Válido");
		}
		
		int status = CotizacionDAO.obtenerStatus();
		log.info("status=" + status);
		boolean salida= (status==1)? true: false;
		return Boolean.valueOf(salida);
	}
	
	@WebMethod(action="http://servicios.laaraucana.cl/bonificaTGR/autenticacionWS",  operationName="autenticacionWS")
	public String autenticacionWS(@WebParam(name="credentials") @XmlElement(name="credentials", required=true) CredentialWSTGR param) throws SOAPException{
		MessageContext mctx = wsCtx.getMessageContext();
		log.info("EN autenticacionWS");
		
		String user = credentials.getString("app.ws.user");
		String password = credentials.getString("app.ws.password");
		
		if(param.getUser().equals(user) && param.getPassword().equals(password)){
			String remoteip= ((HttpServletRequest)(wsCtx.getMessageContext().get(MessageContext.SERVLET_REQUEST))).getRemoteAddr();
			String token_encode= Utiles.generaToken(remoteip);
			String fecha= Utiles.getFecha();
			TokenFactory.getInstance().addToken(remoteip, fecha);
			return token_encode;
		}else{
			mctx.put("tipo", "Client");
			log.warn("Usuario o Contraseña No Válido");
			throw new SOAPException("Usuario o Contraseña No Válido");
		}
	}
	
	private boolean isValidParams(DatoEntradaVO in){
		if(in.getRuttra()<=0 || in.getRutemp()<=0 || in.getAnio()<=0 || Integer.parseInt(in.getMes())>12){
			return false;
		}
		return true;
	}
	
	private boolean isValidParams2(DatosEntradaVO in){
		if(in.getRutemp()<=0 || in.getAnio()<=0 || Integer.parseInt(in.getMes())>12){
			return false;
		}
		List ruts= in.getRuttra();
		for (Iterator iterator = ruts.iterator(); iterator.hasNext();) {
			try {
				Integer rut = (Integer) iterator.next();
				if(rut<=0){
					return false;				
				}
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isValidToken(String token){
		try {
			String[] data= Utiles.decodeToken(token);
			String remoteip= ((HttpServletRequest)(wsCtx.getMessageContext().get(MessageContext.SERVLET_REQUEST))).getRemoteAddr();
			
			String fecha= TokenFactory.getInstance().getToken(data[0]);
			if(fecha!=null){
				if(Utiles.validaFecha(fecha)){
					if(remoteip.equals(data[0]) && fecha.equals(data[1])){
						return true;
					}
				}else{
					 TokenFactory.getInstance().delToken(data[0]);
				}
			}
			return false;
			/*MessageContext mctx = wsCtx.getMessageContext();
			String remoteip= ((HttpServletRequest)(wsCtx.getMessageContext().get(MessageContext.SERVLET_REQUEST))).getRemoteAddr();
			String username = mctx.get("userid").toString();
			String password = mctx.get("password").toString();
			if(username.equals("WSAraucanaTGR")){
				if(password.equals("srv12345")){
					return true;
				}
			}*/
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@WebMethod(action="http://servicios.laaraucana.cl/bonificaTGR/getCotizacionesByTrabajador",  operationName="getCotizacionesByTrabajador")
	public ResponseWSTGR getCotizacionesByTrabajador(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="request") @XmlElement(name="request", required=true) RequestWSTGR req) throws SOAPException {
		MessageContext mctx = wsCtx.getMessageContext();
		log.info("EN getCotizacionesByTrabajador");
		log.info("Params: Periodo= " + req.getPeriodo() + ", RutEmpresa= " + req.getRut_empleador() + ", RutTrabajador=" + req.getRut_trabajador());
		boolean param;
		 // Dependency  Injection
	    
		if(!isValidToken(token)){
			mctx.put("tipo", "Client");
			log.warn("Token No Válido");
			throw new SOAPException("Token No Válido");
		}
		
		ResponseWSTGR response=new ResponseWSTGR();
		EmpleadorVO empleador= new EmpleadorVO();
		List<TrabajadorVO> listaTra= new ArrayList();
		int i=0;
		try {
			
			 /*SOAPPart part = request.getSOAPPart();
			 SOAPEnvelope env = part.getEnvelope();
			 SOAPHeader header = env.getHeader();*/
			    
//			SE OBTIENEN LOS TRABAJADORES SEGUN DATOS DE ENTRADA DESDE EL DAO
			List cotizaciones = new ArrayList();
			
			
			DatoEntradaVO entrada= new DatoEntradaVO();
			entrada.setAnio((int)Math.floor(req.getPeriodo()/100));
			String mes= String.valueOf(req.getPeriodo()-(entrada.getAnio()*100));
			if(mes.length()==1){
				mes= "0" + mes;
			}
			entrada.setMes(mes);
			entrada.setRutemp(req.getRut_empleador());
			entrada.setRuttra(req.getRut_trabajador());
			//Validando datos de entrada
			if(isValidParams(entrada)){

				log.info("Obteniendo Cotizaciones Trabajador:" + req.getRut_trabajador());
				cotizaciones =  CotizacionDAO.obtenerCotizaciones(entrada);
				log.info("# cotizaciones= " + cotizaciones.size());

				for (Iterator iter = cotizaciones.iterator(); iter.hasNext();) {
					CotizacionVO cotizacionVO = (CotizacionVO) iter.next();
					if(i==0){
						empleador.setRut_empleador(cotizacionVO.getRutEmpresa());
						empleador.setDv_empleador(cotizacionVO.getDvRutEmpresa());
						empleador.setRazon_social(cotizacionVO.getRazonSocial());
						String region= cotizacionVO.getRegion();
						if(region==null || region.equals("")){
							region="13";
						}
						empleador.setRegion_domicilio(Integer.parseInt(region.trim()));
					}
					TrabajadorVO trabajadorVO = new TrabajadorVO();
					trabajadorVO.setApellidoMaterno(cotizacionVO.getApellidoMaterno());
					trabajadorVO.setApellidoPaterno(cotizacionVO.getApellidoPaterno());
					trabajadorVO.setCodAFP(Integer.parseInt(cotizacionVO.getCodAFP().trim()));
					String codmovper= cotizacionVO.getCodMovPer();
					if(codmovper==null || codmovper.equals("")){
						codmovper="0";
					}
					int pos= codmovper.indexOf('-');
					if(pos>-1){
						codmovper = codmovper.substring(0, pos);
					}	
					trabajadorVO.setCodMovPer(Integer.parseInt(codmovper));
					trabajadorVO.setDiasTrabajados(cotizacionVO.getDiasTrabajados());
					trabajadorVO.setRutTrabajador(cotizacionVO.getRutTrabajador());
					trabajadorVO.setDvRutTrabajador(cotizacionVO.getDvRutTrabajador());
					String fechaPago= String.valueOf(cotizacionVO.getFechaPago());
					if(fechaPago.length()==6){
						fechaPago= fechaPago.substring(6, 8) + "-" + fechaPago.substring(4, 6) + "-" + fechaPago.substring(0, 4) + " 12:00:00";
					}
					trabajadorVO.setFechaPago(fechaPago);
					trabajadorVO.setTipoPago(0); //0:Normal , 1:Atrasado, 2:DNP
					//Se determina Tipo Cliente, Si no tiene AFP y Pago previsión es 0 ==> Jubilado
					//Si es Jubilado se mantiene Pago Salud, sino se deja en 0. 
					int tipoCliente=0;
					int montoSalud=cotizacionVO.getMontoSalud();
					if(cotizacionVO.getCodAFP().trim().equals("0") && cotizacionVO.getMontoPagado()==0){
						tipoCliente=1;
					}else{
						montoSalud=0;
					}
					trabajadorVO.setTipoCliente(tipoCliente); //0:Normal, 1:Jubilado
					
					trabajadorVO.setMontoPagado(cotizacionVO.getMontoPagado());
					trabajadorVO.setNombres(cotizacionVO.getNombres());
					trabajadorVO.setPeriodo(cotizacionVO.getPeriodo());
					trabajadorVO.setRentaImponible(cotizacionVO.getRentaImponible());
					trabajadorVO.setTipoProceso(Integer.parseInt(cotizacionVO.getTipoProceso()));
					trabajadorVO.setMontoPagoSalud(montoSalud);
					listaTra.add(trabajadorVO);
					i++;
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
			if(i==0){
				mctx.put("tipo", "Server");
				throw new SOAPException("No existe información previsional para los parámetros ingresados");
			}else if(i==-1){
				mctx.put("tipo", "Server");
				throw new SOAPException("Error Interno del Servicio:");
			}else if(i==-2){
				mctx.put("tipo", "Client");
				throw new SOAPException("Parámetros No Válidos");
			}else{
				response.setEmpleador(empleador);
				response.setTrabajador(listaTra);
				log.info("Respondiendo...");
				return response;
			}
		}

	}
	
	@WebMethod(action="http://servicios.laaraucana.cl/bonificaTGR/getCotizacionesByTrabajadores",  operationName="getCotizacionesByTrabajadores")
	public ResponseWSTGR getCotizacionesByTrabajadores(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="request") @XmlElement(name="request", required=true) RequestsWSTGR req) throws SOAPException {
		log.info("EN getCotizacionesByTrabajadores");
		log.info("Params: Periodo= " + req.getPeriodo() + ", RutEmpresa= " + req.getRut_empleador() + ", RutTrabajador=" + req.getRut_trabajador().toArray().toString());
		MessageContext mctx = wsCtx.getMessageContext();
		//log.info("Params: Periodo= " + req.getPeriodo() + ", RutEmpresa= " + req.getRut_empleador() + ", RutTrabajadores=" + req.getRut_trabajador());
		boolean param;
		
		if(!isValidToken(token)){
			mctx.put("tipo", "Client");
			log.warn("Token No Válido");
			throw new SOAPException("Token No Válido");
		}
		
		ResponseWSTGR response=new ResponseWSTGR();
		
		EmpleadorVO empleador= new EmpleadorVO();
		List<TrabajadorVO> listaTra= new ArrayList();
		int i=0;
		try {
//			SE OBTIENEN LOS TRABAJADORES SEGUN DATOS DE ENTRADA DESDE EL DAO
			List cotizaciones = new ArrayList();
			
			DatosEntradaVO entrada= new DatosEntradaVO();
			entrada.setAnio((int)Math.floor(req.getPeriodo()/100));
			String mes= String.valueOf(req.getPeriodo()-(entrada.getAnio()*100));
			if(mes.length()==1){
				mes= "0" + mes;
			}
			entrada.setMes(mes);
			entrada.setRutemp(req.getRut_empleador());
			entrada.setRuttra(req.getRut_trabajador());
			//Validando datos de entrada
			if(isValidParams2(entrada)){
				
				log.info("Obteniendo " + entrada.getRuttra().size() + " Cotizaciones para RutEmpresa:" + req.getRut_empleador());
				
				cotizaciones =  CotizacionDAO.obtenerListaCotizaciones(entrada);


				for (Iterator iter = cotizaciones.iterator(); iter.hasNext();) {
					CotizacionVO cotizacionVO = (CotizacionVO) iter.next();
					if(i==0){
						empleador.setRut_empleador(cotizacionVO.getRutEmpresa());
						empleador.setDv_empleador(cotizacionVO.getDvRutEmpresa());
						empleador.setRazon_social(cotizacionVO.getRazonSocial());
						empleador.setRegion_domicilio(Integer.parseInt(cotizacionVO.getRegion()));
					}
					TrabajadorVO trabajadorVO = new TrabajadorVO();
					trabajadorVO.setApellidoMaterno(cotizacionVO.getApellidoMaterno());
					trabajadorVO.setApellidoPaterno(cotizacionVO.getApellidoPaterno());
					trabajadorVO.setCodAFP(Integer.parseInt(cotizacionVO.getCodAFP().trim()));
					String codmovper= cotizacionVO.getCodMovPer();
					if(codmovper==null || codmovper.equals("")){
						codmovper="0";
					}
					int pos= codmovper.indexOf('-');
					if(pos>-1){
						codmovper = codmovper.substring(0, pos);
					}	
					trabajadorVO.setCodMovPer(Integer.parseInt(codmovper));
					trabajadorVO.setDiasTrabajados(cotizacionVO.getDiasTrabajados());
					trabajadorVO.setRutTrabajador(cotizacionVO.getRutTrabajador());
					trabajadorVO.setDvRutTrabajador(cotizacionVO.getDvRutTrabajador());
					String fechaPago= String.valueOf(cotizacionVO.getFechaPago());
					if(fechaPago.length()==6){
						fechaPago= fechaPago.substring(6, 8) + "-" + fechaPago.substring(4, 6) + "-" + fechaPago.substring(0, 4) + " 12:00:00";
					}
					trabajadorVO.setFechaPago(fechaPago);
					trabajadorVO.setTipoPago(0); //0:Normal , 1:Atrasado, 2:DNP
					//Se determina Tipo Cliente, Si no tiene AFP y Pago previsión es 0 ==> Jubilado
					//Si es Jubilado se mantiene Pago Salud, sino se deja en 0. 
					int tipoCliente=0;
					int montoSalud=cotizacionVO.getMontoSalud();
					if(cotizacionVO.getCodAFP().trim().equals("0") && cotizacionVO.getMontoPagado()==0){
						tipoCliente=1;
					}else{
						montoSalud=0;
					}
					trabajadorVO.setTipoCliente(tipoCliente); //0:Normal, 1:Jubilado
					
					trabajadorVO.setMontoPagado(cotizacionVO.getMontoPagado());
					trabajadorVO.setNombres(cotizacionVO.getNombres());
					String periodo= String.valueOf(cotizacionVO.getPeriodo());
					trabajadorVO.setPeriodo(cotizacionVO.getPeriodo());
					trabajadorVO.setRentaImponible(cotizacionVO.getRentaImponible());
					trabajadorVO.setTipoProceso(Integer.parseInt(cotizacionVO.getTipoProceso()));
					trabajadorVO.setMontoPagoSalud(montoSalud);
					listaTra.add(trabajadorVO);
					i++;
				}
			}else{
				//parámetros no válidos
				i=-2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			i=-1;
		} finally{
			if(i==0){
				mctx.put("tipo", "Server");
				throw new SOAPException("No existe información previsional para los parámetros ingresados");
			}else if(i==-1){
				mctx.put("tipo", "Server");
				throw new SOAPException("Error Interno del Servicio:");
			}else if(i==-2){
				mctx.put("tipo", "Client");
				throw new SOAPException("Parámetros No Válidos");
			}else{
				response.setEmpleador(empleador);
				response.setTrabajador(listaTra);
				log.info("Respondiendo...");
				return response;
			}
		}

	}
	
	/*private StringBuffer buildXML(List cotizaciones, String descError) {
		StringBuffer xml= new StringBuffer();
		StringBuffer nodo0=new StringBuffer();
		StringBuffer nodo1=new StringBuffer();
		StringBuffer nodo2=new StringBuffer();
		StringBuffer nodo2_1=new StringBuffer();
		StringBuffer nodo3=new StringBuffer();
		StringBuffer nodo3_1=new StringBuffer();
		StringBuffer nodo3_2=new StringBuffer();
		StringBuffer nodo3_2_1=null;
		String status="OK";
		nodo0.append("PLANILLAS");
		//SI NO HAY RESULTADO DEL SP SE GENERA ERROR TIPO 1, SI HUBO ERROR ES TIPO 2
		if(cotizaciones==null || cotizaciones.size()==0){
			status="NOK";
			nodo1.append(UtilXML.generarTag("STATUS", "", status));
			//System.out.println("Nodo1" + nodo1.toString());
			if(cotizaciones==null){
				nodo2.append(UtilXML.generarError("2", "Otros. " + descError));
			}else{
				nodo2.append(UtilXML.generarError("1", "No existen datos para la consulta"));
			}
			//System.out.println("nodo2:" + nodo2.toString());
			xml.append(UtilXML.generarTag(nodo0.toString(), "", nodo1.toString() + nodo2.toString()));
			//System.out.println("XML:" + xml.toString());
			
		}
		else{
			nodo1.append(UtilXML.generarTag("STATUS", "", status));
			int i=0;
			String oldafp="";
			for (Iterator iter = cotizaciones.iterator(); iter.hasNext();) {
				TrabajadorVO cottra = (TrabajadorVO) iter.next();
				if(i==0){
					nodo2_1.append(UtilXML.generarTag("REGDOM", "", cottra.getRegion()));
					nodo2_1.append(UtilXML.generarTag("RAZSOC", "", cottra.getRazonSocial()));
					nodo2_1.append(UtilXML.generarTag("RUTEMP", "", String.valueOf(cottra.getRutEmpresa())));
					nodo2_1.append(UtilXML.generarTag("DVEMP", "", String.valueOf(cottra.getDvRutEmpresa())));
					nodo2.append(UtilXML.generarTag("EMPLEADOR", "", nodo2_1.toString()));
				}
				if(!cottra.getCodAFP().equals(oldafp)){
					if(!oldafp.equals("")){
						nodo3.append(UtilXML.generarTag("AFP", "", nodo3_1.toString() + nodo3_2.toString()));
					}
					nodo3_1=new StringBuffer();
					nodo3_1.append(UtilXML.generarTag("CODAFP", "", String.valueOf(cottra.getCodAFP().trim())));
					nodo3_2= new StringBuffer();	
				}
				nodo3_2_1=new StringBuffer();
				nodo3_2_1.append(UtilXML.generarTag("PERPAGO", "", String.valueOf(cottra.getPeriodo())));
				nodo3_2_1.append(UtilXML.generarTag("FECHAPAGO", "", String.valueOf(cottra.getFechaPago())));
				nodo3_2_1.append(UtilXML.generarTag("RUTAFIL", "", String.valueOf(cottra.getRutTrabajador())));
				nodo3_2_1.append(UtilXML.generarTag("DVAFIL", "", String.valueOf(cottra.getDvRutTrabajador())));
				nodo3_2_1.append(UtilXML.generarTag("APEPATERNO", "", cottra.getApellidoPaterno()));
				nodo3_2_1.append(UtilXML.generarTag("APEMATERNO", "", String.valueOf(cottra.getApellidoMaterno())));
				nodo3_2_1.append(UtilXML.generarTag("NOMBRE", "", String.valueOf(cottra.getNombres())));
				nodo3_2_1.append(UtilXML.generarTag("REMIMPO", "", String.valueOf(cottra.getRentaImponible())));
				nodo3_2_1.append(UtilXML.generarTag("COTOBLI", "", String.valueOf(cottra.getMontoPagado())));
				nodo3_2_1.append(UtilXML.generarTag("GRATIF", "", String.valueOf(cottra.getTipoProceso())));
				nodo3_2_1.append(UtilXML.generarTag("DIASTRAB", "", String.valueOf(cottra.getDiasTrabajados())));
				nodo3_2_1.append(UtilXML.generarTag("AUSENCIA", "", String.valueOf(cottra.getCodMovPer())));
				nodo3_2.append(UtilXML.generarTag("AFILIADO", "", nodo3_2_1.toString()));
				i++;
				oldafp= cottra.getCodAFP();
				//if(i==100) break;
			}
			nodo3.append(UtilXML.generarTag("AFP", "", nodo3_1.toString() + nodo3_2.toString()));
			
			xml.append(UtilXML.generarTag(nodo0.toString(), "", nodo1.toString() + nodo2.toString() + nodo3.toString()));
		}
		return xml;
	}*/

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