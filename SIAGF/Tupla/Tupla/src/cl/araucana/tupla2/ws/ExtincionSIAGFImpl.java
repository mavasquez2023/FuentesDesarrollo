package cl.araucana.tupla2.ws;

import cl.araucana.tupla2.struts.jdbcDAO.Araucanajdbcdao;
import cl.araucana.tupla2.struts.utils.TokenFactory;
import cl.araucana.tupla2.struts.utils.Utiles;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import cl.araucana.wssiagf.vo.*;
import javax.annotation.Resource;
import javax.ejb.AccessTimeout;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
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

@WebService( portName = "ExtincionSIAGFPort",
			serviceName = "ExtincionSIAGFService",
			targetNamespace = "http://servicios.laaraucana.cl/extincionSIAGF"
)
@AccessTimeout(unit=TimeUnit.SECONDS, value=20)

public class ExtincionSIAGFImpl implements ExtincionSIAGF{
	
	static ResourceBundle credentials = ResourceBundle.getBundle("wssiagf");
	private Logger log = Logger.getLogger(this.getClass());
	//static Properties properties = new LoadPropertiesFile().load("wssiagf.properties");
	@Resource
    private WebServiceContext wsCtx;
	
	/**
		* 
		* @throws SOAPException 
	 * @throws SQLException 
	 * @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
		*/
	@WebMethod(action="http://servicios.laaraucana.cl/extincionSIAGF/getStatus",  operationName="getStatus")
	//@RequestWrapper(localName = "getStatusWS",
   // className = "cl.araucana.tgr.vo.TokenVOWrapper")
	public Boolean getStatus(@WebParam(name="token") @XmlElement(name="token", required=true) String token){
		MessageContext mctx = wsCtx.getMessageContext();
		boolean status;

		log.info("EN getStatus");
		String usuario= isValidToken(token);
		if(usuario.equals("")){
			log.warn("Token No Válido");
			status=false;
		}else{
			try {
				status = Araucanajdbcdao.obtenerStatus();
			} catch (SQLException e) {
				status=false;
			}
		}
		log.info("status=" + status);
		return Boolean.valueOf(status);
	}
	

	@WebMethod(action="http://servicios.laaraucana.cl/extincionSIAGF/autenticacionWS",  operationName="autenticacionWS")
	public String autenticacionWS(@WebParam(name="credentials") @XmlElement(name="credentials", required=true) CredentialWS param){
		MessageContext mctx = wsCtx.getMessageContext();
		 // get request headers
        Map<?,?> requestHeaders = (Map<?,?>) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
        for (Iterator iterator = requestHeaders.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			 System.out.println("Key=" + key);
			 System.out.println("Value=" + requestHeaders.get(key)) ;
		}
       
        
		//Archivo de configuracion
				
		String user = credentials.getString("app.ws.user");
		String password = credentials.getString("app.ws.password");
		log.info("Usuario:" + param.getUser());

		if(param.getUser().equals(user) && param.getPassword().equals(password)){
			String remoteip= ((HttpServletRequest)(wsCtx.getMessageContext().get(MessageContext.SERVLET_REQUEST))).getRemoteAddr();
			String token_encode= Utiles.generaToken(remoteip, param.getUser());
			String fecha= Utiles.getFecha();
			TokenFactory.getInstance().addToken(param.getUser(), token_encode);
			return token_encode;
		}else{
			mctx.put("tipo", "Client");
			log.warn("Usuario o Contraseña No Válido");
			return "";
		}

	}
		
	
	private String isValidToken(String token){
		String usuario="";
		try {
			String[] data= Utiles.decodeToken(token);
			String remoteip= ((HttpServletRequest)(wsCtx.getMessageContext().get(MessageContext.SERVLET_REQUEST))).getRemoteAddr();
			
			String tokenFactory= TokenFactory.getInstance().getToken(data[1]);
			String dataTokenFacory[]= Utiles.decodeToken(tokenFactory);
			
			String fecha= dataTokenFacory[2];
			usuario=dataTokenFacory[1];
			if(fecha!=null){
				if(Utiles.validaFecha(fecha)){
					if(remoteip.equals(data[0])){
						return usuario;
					}
				}else{
					 TokenFactory.getInstance().delToken(data[0]);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	@SuppressWarnings("finally")
	@WebMethod(action="http://servicios.laaraucana.cl/extincionSIAGF/setExtingueCausantes",  operationName="extingueCausantes")
	public ResponseWS setExtingueCausantes(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="request") @XmlElement(name="request", required=true) RequestWS req) {
		log.info("EN setExtingueCausantes");
		
		ResponseWS response=new ResponseWS();
		 // Dependency  Injection
	    String usuario= isValidToken(token);
		if(usuario.equals("")){
			log.warn("Token No Válido");
			response.setCodigo(-1);
			response.setDescripcion("Token No Válido");
			response.setData(null);
		}else{
			ExtincionMgr extincion= new ExtincionMgr();
			response= extincion.extingueTrabajador(req.getData());
		}
				
		return response;


	}
	
}