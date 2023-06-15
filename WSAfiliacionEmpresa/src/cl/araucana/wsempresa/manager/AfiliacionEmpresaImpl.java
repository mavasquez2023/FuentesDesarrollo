package cl.araucana.wsempresa.manager;

import cl.araucana.core.util.Rut;
import cl.araucana.wsempresa.ibatis.dao.AdministracionDAO;
import cl.araucana.wsempresa.ibatis.dao.BitacoraDAO;
import cl.araucana.wsempresa.util.Utiles;
import cl.araucana.wsempresa.vo.CredentialWS;
import cl.araucana.wsempresa.vo.RequestWS;
import cl.araucana.wsempresa.vo.ResponseWS;


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

@WebService( portName = "AfiliacionEmpresaPort",
			serviceName = "AfiliacionEmpresaService",
			targetNamespace = "http://servicios.laaraucana.cl/afiliacionEmpresa"
)

public class AfiliacionEmpresaImpl implements AfiliacionEmpresa{
	
	@Resource
    private WebServiceContext wsCtx;
	
	Logger log = Logger.getLogger(this.getClass());
	
	private boolean isValidParams(int rut){
		if(rut<=0){
			return false;
		}
		return true;
	}
	
	
	
	
	
	@WebMethod(action="http://servicios.laaraucana.cl/estadoAfiliacion/getAfiliacionEmpresa",  operationName="getAfiliacionEmpresa")
	public ResponseWS getEstadoAfiliacion(@WebParam(name="credentials") @XmlElement(name="credentials", required=true) CredentialWS param, @WebParam(name="request") @XmlElement(name="request", required=true) RequestWS req) throws SOAPException {
		MessageContext mctx = wsCtx.getMessageContext();
		log.info("EN getAfiliacionEmpresa");
		
		int estado=-1;
		String glosaError="";
		ResponseWS response=null;
		String usuario="";
		try {

			usuario= param.getUser();
			String password= param.getPassword();
			if(usuario==null || usuario.equals("") || password==null || password.equals("")){
				glosaError="Usuario o Contraseña no válido";
				log.warn("Usuario o contraseña no válido:" + usuario);
			}else{

				Map dataUsuario= AdministracionDAO.validaUsuarioWS(param.getUser());
				if(dataUsuario==null){
					glosaError="Usuario o Contraseña no válido";
					log.warn("Usuario o contraseña no válido:" + usuario);
				}else{
					//Validando datos de entrada
					if(isValidParams(req.getRut_empresa())){
						log.info("Obteniendo Estado Afiliación Empresa RUT Empresa:" + req.getRut_empresa());

						response =  ConsultaAfiliadoSistemas.obtenerEstadoAfiliacion(req.getRut_empresa());
						estado= response.getEstado();
					}else{
						glosaError="Rut Empresa no válido";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			glosaError= e.getMessage();

		}finally{
			log.info("estado afiliación: " +  estado);
			if(response==null){
				response= new ResponseWS();
				response.setEstado(estado);
				response.setRazonSocial("");
				response.setGlosaError(glosaError);
			}
			//Insertando bitácora
			Map<String, String> bita_param= new HashMap<String, String>();
			bita_param.put("periodo", Utiles.getPeriodo());
			bita_param.put("usuario", usuario);
			bita_param.put("rut",  String.valueOf(req.getRut_empresa()));
			bita_param.put("estado", String.valueOf(response.getEstado()));
			if(glosaError.length()>100){
				glosaError= glosaError.substring(0, 100);
			}
			bita_param.put("glosa", glosaError);

			BitacoraDAO.insertConsultaBitacora(bita_param);

		}
		return response;

	}
	

	
	
}