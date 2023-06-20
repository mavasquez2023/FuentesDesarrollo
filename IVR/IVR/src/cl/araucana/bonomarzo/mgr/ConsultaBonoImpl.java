package cl.araucana.bonomarzo.mgr;

import java.sql.SQLException;
import java.util.*;

import javax.annotation.Resource;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import org.apache.log4j.Logger;
import cl.araucana.bonomarzo.dao.BonoMarzoDAO;
import cl.araucana.bonomarzo.vo.DatoEntradaVO;
import cl.araucana.bonomarzo.vo.RequestWS;
import cl.araucana.bonomarzo.vo.ResponseWS;
import cl.araucana.bonomarzo.vo.TrabajadorVO;
import cl.araucana.core.util.Rut;

import com.ibm.jvm.dtfjview.commands.infocommands.InfoClassCommand;
import com.ibm.trl.soap.SOAPException;


/** 
 * @version 	1.0
 * @author Claudio Lillo
 *  
 */

@WebService( portName = "ConsultaBonoPort",
			serviceName = "ConsultaBonoService",
			targetNamespace = "http://servicios.laaraucana.cl/bonomarzo"
)

public class ConsultaBonoImpl implements ConsultaBono{
	static ResourceBundle credentials = ResourceBundle.getBundle("etc/credential");
	
	@Resource
    private WebServiceContext wsCtx;
	
	Logger log = Logger.getLogger(this.getClass());

	private DatoEntradaVO isValidParams(RequestWS req){
		DatoEntradaVO entrada= new DatoEntradaVO();
		Rut rutvalid=null;
		String[] rutfull=null;
		try {
			rutfull= req.getRut_cliente().split("-");
			entrada.setRutcliente(Integer.parseInt(rutfull[0]));
			entrada.setDvrutcliente(rutfull[1]);
			entrada.setId(req.getId_consulta());
			rutvalid= new Rut(rutfull[0]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
		if(entrada.getRutcliente()<=0 || entrada.getId()==null || entrada.getId().equals("") || rutvalid.getDV()!=rutfull[1].charAt(0) ){
			return null;
		}
		return entrada;
	}
	
	
	private boolean isValidCredentials(RequestWS in){
		try {
			String clave= (String)BonoMarzoDAO.validaCredenciales(in.getWs_usuario());
			if(clave==null || !clave.trim().equals(in.getWs_clave())){
				log.warn("Usuario o Contraseña No Válido");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	@WebMethod(action="http://servicios.laaraucana.cl/bonomarzo/getBonoTrabajador",  operationName="getBonoTrabajador")
	public ResponseWS getBonoTrabajador(@WebParam(name="request") @XmlElement(name="request", required=true) RequestWS req) throws SOAPException {
		int estadoConsulta=1;
		log.info("EN getBonoTrabajador");
		log.info("Params: ID= " + req.getId_consulta() + ", Rut Cliente= " + req.getRut_cliente());
	    
		
		ResponseWS response=new ResponseWS();
		HashMap bitacora= new HashMap();
		try {
			    
//			SE OBTIENEN INFO BONO MARZO SEGUN DATOS DE ENTRADA 
			
			DatoEntradaVO entrada= isValidParams(req);
			
			//Validando datos de entrada
			if(entrada!=null && isValidCredentials(req) ){

				log.info("Obteniendo Datos Trabajador:" + req.getRut_cliente());
				TrabajadorVO infobonocliente = (TrabajadorVO) BonoMarzoDAO.obtenerBonoTrabajador(entrada);
				response.setTrabajador(infobonocliente);
				if (infobonocliente==null){
					estadoConsulta=0;
				}
			}else{
				//parámetros no válidos
				estadoConsulta=2;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			estadoConsulta=3;
		}finally{
			bitacora.put("ESTADO", String.valueOf(estadoConsulta));
			bitacora.put("ID", req.getId_consulta());
			bitacora.put("RUT", req.getRut_cliente());
			bitacora.put("USUARIO", req.getWs_usuario());
			try {
				BonoMarzoDAO.insertBitacora(bitacora);
			} catch (SQLException e) {
				e.printStackTrace();
				log.warn("Error al grabar bitácora, mensaje:" + e.getMessage());
			}
			response.setRespuesta(estadoConsulta);
			response.setId_consulta(req.getId_consulta());
			response.setRut_cliente(req.getRut_cliente());
			return response;
		}

	}
	
	
}