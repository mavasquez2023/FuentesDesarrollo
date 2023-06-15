package cl.araucana.wsafiliado.mgr;

import cl.araucana.core.util.Rut;
import cl.araucana.wsafiliado.dao.AdministracionDAO;
import cl.araucana.wsafiliado.dao.AfiliacionDAO;
import cl.araucana.wsafiliado.dao.BitacoraDAO;
import cl.araucana.wsafiliado.util.GenerarResponseWS;
import cl.araucana.wsafiliado.util.TokenFactory;
import cl.araucana.wsafiliado.util.Utiles;
import cl.araucana.wsafiliado.vo.CargaVO;
import cl.araucana.wsafiliado.vo.CredentialWS;
import cl.araucana.wsafiliado.vo.DataCargaVO;
import cl.araucana.wsafiliado.vo.DataAfiliadoVO;
import cl.araucana.wsafiliado.vo.EmpresaVO;
import cl.araucana.wsafiliado.vo.RequestWS;
import cl.araucana.wsafiliado.vo.ResponseDataWS;
import cl.araucana.wsafiliado.vo.ResponseWS;
import cl.araucana.wsafiliado.vo.TitularVO;

import java.sql.SQLException;
import java.util.*;

import javax.annotation.Resource;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import org.apache.log4j.Logger;

import com.ibm.trl.soap.SOAPException;
import com.ibm.xylem.optimizers.SplitFunctions;



/** 
 * @version 	1.0
 * @author Claudio Lillo
 *  
 */

@WebService( portName = "ConsultaAfiliacionPort",
			serviceName = "EstadoAfiliacionService",
			targetNamespace = "http://servicios.laaraucana.cl/estadoAfiliacion"
)
@HandlerChain(file = "/etc/serversoaphandler.xml")
public class ConsultaAfiliacionImpl implements ConsultaAfiliacion{
	//static ResourceBundle credentials = ResourceBundle.getBundle("etc/credential");
	final String METODO_GET_DATOS_AFILIACION="GDA";
	
	@Resource
    private WebServiceContext wsCtx;
	
	Logger log = Logger.getLogger(this.getClass());
	

	/**
		* 
		* @throws SOAPException 
	 * @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
		*/
	@WebMethod(action="http://servicios.laaraucana.cl/estadoAfiliacion/getStatus",  operationName="getStatus")
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
		String usuario= isValidToken(token);
		if(usuario.equals("")){
			mctx.put("tipo", "Client");
			log.warn("Token No Válido");
			throw new SOAPException("Token No Válido");
		}
		
		int status = AfiliacionDAO.obtenerStatus();
		log.info("status=" + status);
		boolean salida= (status==1)? true: false;
		return Boolean.valueOf(salida);
	}
	
	@WebMethod(action="http://servicios.laaraucana.cl/estadoAfiliacion/autenticacionWS",  operationName="autenticacionWS")
	public String autenticacionWS(@WebParam(name="credentials") @XmlElement(name="credentials", required=true) CredentialWS param) throws SOAPException, SQLException{
		MessageContext mctx = wsCtx.getMessageContext();
		log.info("EN autenticacionWS");
		
		Map dataUsuario= AdministracionDAO.validaUsuarioWS(param.getUser());
		if(dataUsuario!=null){
			String password= dataUsuario.get("PASSWORD").toString();
			String base= dataUsuario.get("BASE").toString();
			if(param.getPassword().equals(password)){
				String remoteip= ((HttpServletRequest)(wsCtx.getMessageContext().get(MessageContext.SERVLET_REQUEST))).getRemoteAddr();
				String token_encode= Utiles.generaToken(remoteip, param.getUser());
				String fecha= Utiles.getFecha();
				TokenFactory.getInstance().addToken(param.getUser(), token_encode);
				TokenFactory.getInstance().addBaseUsuarios(param.getUser(), base);
				return token_encode;
			}else{
				mctx.put("tipo", "Client");
				log.warn("Usuario o Contraseña No Válido");
				throw new SOAPException("Usuario o Contraseña No Válido");
			}
		}else{
			mctx.put("tipo", "Client");
			log.warn("Usuario o Contraseña No Válido");
			throw new SOAPException("Usuario o Contraseña No Válido");
		}
		//String user = credentials.getString("app.ws.user");
		//String password = credentials.getString("app.ws.password");
		
	}
	
	private boolean isValidParams(int rut){
		if(rut<=0){
			return false;
		}
		return true;
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
			log.info("Usuario: " + usuario);
			if(fecha!=null){
				if(Utiles.validaFecha(fecha)){
					if(remoteip.equals(data[0])){
						return usuario;
					}
				}else{
					 TokenFactory.getInstance().delToken(data[0]);
				}
			}
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
		return "";
	}
	
	private boolean isAutorizado(String usuario, String metodo){
		try {
			int resultado= AdministracionDAO.isUsuarioAutorizado(usuario, metodo);
			return (resultado==1)? true: false;
		} catch (SQLException e) {
			return false;
		} 
		
	}
	
	@WebMethod(action="http://servicios.laaraucana.cl/estadoAfiliacion/getEstadoAfiliacion",  operationName="getEstadoAfiliacion")
	public ResponseWS getEstadoAfiliacion(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="request") @XmlElement(name="request", required=true) RequestWS req) throws SOAPException {
		MessageContext mctx = wsCtx.getMessageContext();
		log.info("EN getEstadoAfiliacion");
		Map<String, Integer> salida = new HashMap<String, Integer>();
		int isAfiliado=0;
		String base="";
		 // Dependency  Injection
	    String usuario= isValidToken(token);
		if(usuario.equals("")){
			mctx.put("tipo", "Client");
			log.warn("Token No Válido");
			throw new SOAPException("Token No Válido");
		}
				
		ResponseWS response=new ResponseWS();

		int i=0;
		int rutbenef=0;
		try {
			 
			//Validando datos de entrada
			if(isValidParams(req.getRut_trabajador())){
				log.info("Obteniendo Estado Afiliado o Carga RUT:" + req.getRut_trabajador());
				//obtenerEstadoAfiliacion incluye cargas (parámetro true)
				salida =  ConsultaAfiliadoSistemas.obtenerEstadoAfiliacion(req.getRut_trabajador(), true);
				if(salida!=null){
					isAfiliado= salida.get("ESTADO");
					rutbenef= (int)salida.get("RUTBENEF");
					log.info("Estado afiliacion RUT " + req.getRut_trabajador() + " : " + isAfiliado);
					//Si no es afiliado se valida si es carga en SIAGF
					if(isAfiliado==0){
						ConsultaCausanteSIAGF consulta= new ConsultaCausanteSIAGF();
						Rut rut= new Rut(req.getRut_trabajador());
						//Si es carga es SIAGF devuelve el Rut del Beneficiario
						String rutBeneficiario=consulta.getConsultaBeneficiarioSIAGF(rut.toString().replaceAll("\\.", ""));
						log.info("RUT Beneficiario en SIAGF=" + rutBeneficiario);
						if(rutBeneficiario!=null){
							//obtenerEstadoAfiliacion excluye cargas (parámetro false)
							salida =  ConsultaAfiliadoSistemas.obtenerEstadoAfiliacion(Integer.parseInt(rutBeneficiario), false);
							if(salida!=null){
								isAfiliado= salida.get("ESTADO");
								log.info("Estado afiliado beneficiario RUT " + rutBeneficiario + " : " + isAfiliado);
								rutbenef= Integer.parseInt((rutBeneficiario.split("-"))[0]);
							}else{
								i=-1;
							}
						}
					}else if(isAfiliado==-1){
						i=-1;
					}
				}else{
					i=-1;
				}
			}else{
				//parámetros no válidos
				i=-2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			mctx.put("tipo", "Server");
			i=-1;
			throw new SOAPException("Error interno del servicio");
		}finally{
			if(i==-1){
				mctx.put("tipo", "Server");
				throw new SOAPException("Error Interno del Servicio:");
			}else if(i==-2){
				mctx.put("tipo", "Client");
				throw new SOAPException("Parámetros No Válidos");
			}else{
				response.setAfiliado(isAfiliado);
				log.info("Respondiendo..." + isAfiliado);
				//Insertando bitácora
				Map bita_param= new HashMap();
				bita_param.put("periodo", new Integer(Utiles.getPeriodo()));
				bita_param.put("usuario", usuario);
				bita_param.put("rut",   new Integer( req.getRut_trabajador()));
				bita_param.put("rutbenef", new Integer(rutbenef));
				bita_param.put("estado", new Integer(isAfiliado));
				base= TokenFactory.getInstance().getBaseUsuarios().get(usuario);
				if(base.equals("DB2")){
					log.info("insertando bitácora en DB");
					BitacoraDAO.insertConsultaBitacora(bita_param);
				}else{
					log.info("insertando bitácora en SQLServer");
					BitacoraDAO.insertConsultaBitacoraAux(bita_param);
				}
				return response;
			}
		}

	}
	
	@WebMethod(action="http://servicios.laaraucana.cl/estadoAfiliacion/isAfiliadoTitular",  operationName="isAfiliadoTitular")
	public ResponseWS isAfiliadoTitular(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="request") @XmlElement(name="request", required=true) RequestWS req) throws SOAPException {
		MessageContext mctx = wsCtx.getMessageContext();
		log.info("EN isAfiliadoTitular");
		Map<String, Integer> salida = new HashMap<String, Integer>();
		int isAfiliado=0;
		String base="";
		 // Dependency  Injection
	    String usuario= isValidToken(token);
		if(usuario.equals("")){
			mctx.put("tipo", "Client");
			log.warn("Token No Válido");
			throw new SOAPException("Token No Válido");
		}
				
		ResponseWS response=new ResponseWS();

		int i=0;
		int rutbenef=0;
		try {
			 
			//Validando datos de entrada
			if(isValidParams(req.getRut_trabajador())){
				log.info("Obteniendo Estado Afiliado RUT: " + req.getRut_trabajador());

				salida =  ConsultaAfiliadoSistemas.obtenerEstadoAfiliacion(req.getRut_trabajador(), false);
				if(salida!=null){
					isAfiliado= salida.get("ESTADO");
					rutbenef= (int)salida.get("RUTBENEF");
					log.info("Estado afiliacion RUT " +  rutbenef + ":" + isAfiliado);					
				}else{
					i=-1;
				}
			}else{
				//parámetros no válidos
				i=-2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			mctx.put("tipo", "Server");
			i=-1;
			throw new SOAPException("Error interno del servicio");
		}finally{
			if(i==-1){
				mctx.put("tipo", "Server");
				throw new SOAPException("Error Interno del Servicio:");
			}else if(i==-2){
				mctx.put("tipo", "Client");
				throw new SOAPException("Parámetros No Válidos");
			}else{
				response.setAfiliado(isAfiliado);
				log.info("Respondiendo..." + isAfiliado);
				//Insertando bitácora
				Map bita_param= new HashMap();
				bita_param.put("periodo", new Integer(Utiles.getPeriodo()));
				bita_param.put("usuario", usuario);
				bita_param.put("rut",   new Integer( req.getRut_trabajador()));
				bita_param.put("rutbenef", new Integer(rutbenef));
				bita_param.put("estado", new Integer(isAfiliado));
				base= TokenFactory.getInstance().getBaseUsuarios().get(usuario);
				if(base.equals("DB2")){
					log.info("insertando bitácora en DB");
					BitacoraDAO.insertConsultaBitacora(bita_param);
				}else{
					log.info("insertando bitácora en SQLServer");
					BitacoraDAO.insertConsultaBitacoraAux(bita_param);
				}
				return response;
			}
		}

	}
/*
	@WebMethod(action="http://servicios.laaraucana.cl/estadoAfiliacion/getDatosAfiliacion",  operationName="getDatosAfiliacion")
	public ResponseDataWS getDatosAfiliacionFull(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="request") @XmlElement(name="request", required=true) RequestWS req)
			throws SOAPException {
		MessageContext mctx = wsCtx.getMessageContext();
		log.info("EN getDatosAfiliacion");
		log.info("Params: RutConsulta=" + req.getRut_trabajador());
		Rut rut_consulta= new Rut(req.getRut_trabajador());
		List<DataAfiliadoVO> dataAfiliado=null;
		List<DataCargaVO> dataCarga=null;
		String base="";
		 // Dependency  Injection
	    String usuario= isValidToken(token);
		if(usuario.equals("")){
			mctx.put("tipo", "Client");
			log.warn("Token No Válido");
			throw new SOAPException("Token No Válido");
		}
		if(!isAutorizado(usuario, METODO_GET_DATOS_AFILIACION)){
			mctx.put("tipo", "Client");
			log.warn("Usuario No Autorizado Ejecutar Método");
			throw new SOAPException("Usuario No Autorizado Ejecutar Método");
		}
		
		ResponseDataWS response=null;

		int i=0;
		try {
			 
			//Validando datos de entrada
			if(isValidParams(req.getRut_trabajador())){
				log.info("Obteniendo Datos Afiliado o Carga Rut:" + req.getRut_trabajador());
				dataAfiliado =  AfiliacionDAO.obtenerDatosAfiliacion(req.getRut_trabajador());
				//Si no es afiliado se valida si es carga en Caja
				String rutBeneficiario=null;
				if(dataAfiliado==null || dataAfiliado.size()==0){
					dataCarga= AfiliacionDAO.obtenerDatosCarga(req.getRut_trabajador());
					//Si no es carga en Caja se valida si es carga en SIAGF
					
					if(dataCarga==null || dataCarga.size()==0){
						ConsultaCausanteSIAGF consulta= new ConsultaCausanteSIAGF();
						Rut rut= new Rut(req.getRut_trabajador());
						//Si es carga es SIAGF devuelve el Rut del Beneficiario y datos de la Carga
						dataCarga=consulta.getConsultaCausanteSIGAF(rut.toString().replaceAll("\\.", ""));
						if(dataCarga!=null && dataCarga.size()>0){
							rutBeneficiario=dataCarga.get(0).getRutBeneficiario()+"";
							log.info("Rut Beneficiario en SIAGF=" + dataCarga.get(0).getRutBeneficiario());
						}
						
					}else{
						rutBeneficiario= String.valueOf(dataCarga.get(0).getRutBeneficiario());
					}
					
					if(rutBeneficiario!=null){
						dataAfiliado =  AfiliacionDAO.obtenerDatosAfiliacion(Integer.parseInt(rutBeneficiario));
					}
				}
				
				response= GenerarResponseWS.getObjetoDataWS(dataAfiliado, dataCarga,rutBeneficiario);
				
				response.setRutConsulta(rut_consulta.toString().replaceAll("\\.", ""));
			}else{
				//parámetros no válidos
				i=-2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			mctx.put("tipo", "Server");
			i=-1;
			throw new SOAPException("Error interno del servicio");
			
		}finally{
			
		}
		return response;
	}
	*/
	@WebMethod(action="http://servicios.laaraucana.cl/estadoAfiliacion/getDatosAfiliacion",  operationName="getDatosAfiliacion")
	public ResponseDataWS getDatosAfiliacion(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="request") @XmlElement(name="request", required=true) RequestWS req)
			throws SOAPException {
		MessageContext mctx = wsCtx.getMessageContext();
		log.info("EN getDatosAfiliacion");
		List<DataAfiliadoVO> dataAfiliado=null;
		List<DataCargaVO> dataCarga=null;
		String base="";
		 // Dependency  Injection
	    String usuario= isValidToken(token);
		if(usuario.equals("")){
			mctx.put("tipo", "Client");
			log.warn("Token No Válido");
			throw new SOAPException("Token No Válido");
		}
		if(!isAutorizado(usuario, METODO_GET_DATOS_AFILIACION)){
			mctx.put("tipo", "Client");
			log.warn("Usuario No Autorizado Ejecutar Método");
			throw new SOAPException("Usuario No Autorizado Ejecutar Método");
		}
		
		ResponseDataWS response=null;

		int i=0;
		String rutBeneficiario=null;
		String observacion=null;
		try {
			 
			//Validando datos de entrada
			if(isValidParams(req.getRut_trabajador())){
				log.info("Obteniendo Datos Afiliado o Carga Rut:" + req.getRut_trabajador());
				//dataAfiliado =  AfiliacionDAO.obtenerDatosAfiliacion(req.getRut_trabajador());
				dataAfiliado= ConsultaAfiliadoSistemas.obtenerDataAfiliacion(req.getRut_trabajador(), true);
				//Si no es afiliado se valida si excepcion COVID-19
				if(dataAfiliado==null || dataAfiliado.size()==0 || (dataAfiliado.size()>0 && dataAfiliado.get(0).getEstado().equals("0"))){
					dataAfiliado= ConsultaAfiliadoSistemas.obtenerDataExcepcion(req.getRut_trabajador());
				}
				//Si no es afiliado se valida si es carga en Caja
				if(dataAfiliado==null || dataAfiliado.size()==0){
					
					//Si no es afiliado en Caja se valida si es carga en SIAGF
					ConsultaCausanteSIAGF consulta= new ConsultaCausanteSIAGF();
					Rut rut= new Rut(req.getRut_trabajador());
					//Si es carga es SIAGF devuelve el Rut del Beneficiario y datos de la Carga
					dataCarga=consulta.getConsultaCausanteSIGAF(rut.toString().replaceAll("\\.", ""));
					if(dataCarga!=null && dataCarga.size()>0){
						rutBeneficiario=dataCarga.get(0).getRutBeneficiario()+"";
						log.info("Rut Beneficiario en SIAGF=" + dataCarga.get(0).getRutBeneficiario());
						if(rutBeneficiario!=null){
							//dataAfiliado =  AfiliacionDAO.obtenerDatosAfiliacion(Integer.parseInt(rutBeneficiario));
							dataAfiliado = ConsultaAfiliadoSistemas.obtenerDataAfiliacion(Integer.parseInt(rutBeneficiario), false);
						}
					}
				}
				response= GenerarResponseWS.getObjetoDataWS(dataAfiliado, dataCarga,rutBeneficiario);
				i=1;
			}else{
				//parámetros no válidos
				i=-2;
				observacion="Parámetros no válidos";
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			observacion="Error general, mensaje:" + e.getMessage();
			i=-1;
			
		}finally{
			if(response==null){
				response= new ResponseDataWS();
			}
			response.setCodigo_respuesta(i);
			response.setObservacion(observacion);
			//Insertando bitácora
			Map bita_param= new HashMap();
			bita_param.put("periodo", new Integer(Utiles.getPeriodo()));
			bita_param.put("usuario", usuario);
			bita_param.put("rut",   new Integer( req.getRut_trabajador()));
			if(rutBeneficiario==null){
				rutBeneficiario="0";
			}
			bita_param.put("rutbenef", new Integer(rutBeneficiario));
			bita_param.put("estado", new Integer(response.getEstado()));
			base= TokenFactory.getInstance().getBaseUsuarios().get(usuario);
			if(base.equals("DB2")){
				log.info("insertando bitácora en DB");
				BitacoraDAO.insertConsultaBitacora(bita_param);
			}else{
				log.info("insertando bitácora en SQLServer");
				BitacoraDAO.insertConsultaBitacoraAux(bita_param);
			}
		}
		return response;
	}
	
}