package cl.laaraucana.ventafullweb.ws;

import java.rmi.RemoteException;

import javax.xml.soap.SOAPException;

import org.apache.axis.AxisFault;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.log4j.Logger;
import org.json.*;

import cl.laaraucana.ventafullweb.util.Configuraciones;
import cl.laaraucana.ventafullweb.util.Utils;
import cl.laaraucana.ventafullweb.vo.AfiliadoVo;
import net.ais_int.schemas.soap.AIS_Rules_WebServices.EvaluadorModelos.EvaluadorModelosSoapStub;


public class ClienteEvaluadorModeloAIS {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	public JSONObject getRespuestaWSEvalModelo(AfiliadoVo afiliado) {
		String ep = Configuraciones.getConfig("ep.WSMotorAIS");
		String username = Configuraciones.getConfig("ws.AIS.user");
		String password= Configuraciones.getConfig("ws.AIS.password");
		int idModelo = Integer.parseInt(Configuraciones.getConfig("ws.AIS.idModelo"));	
		
		String contenidoPeticion = "";
		StringBuilder sb = new StringBuilder();
		
		sb.append("<GMR_DATALIST><GMR_DATA><GMR_HEADER><GMR_FLOW>1</GMR_FLOW><GMR_VERSION>0</GMR_VERSION><GMR_RESERVED>00</GMR_RESERVED><GMR_IDELEMENTO>0000000001</GMR_IDELEMENTO></GMR_HEADER>");
		sb.append("<SolicitudIn>");
		if(afiliado.getSegmento().equals("PENSIONADOS")) {
			sb.append("<SEGMENTO>ZAFPEN</SEGMENTO>");			
		} else {
			sb.append("<SEGMENTO>ZAFTRA</SEGMENTO>");		
		}
		sb.append("<RUTEMPRESA>" + afiliado.getRutEmpresa() + "</RUTEMPRESA>");
		sb.append("<MONTOCUOTA>" + afiliado.getMontoCuota() + "</MONTOCUOTA>");
		sb.append("<PLAZOSIMULADO>" + afiliado.getCuotas() + "</PLAZOSIMULADO>");
		sb.append("<MONTOSIMULADO>" + afiliado.getMontoSimulacion() + "</MONTOSIMULADO>");
		sb.append("<NUMSERIECI>" + afiliado.getSerieCedula() + "</NUMSERIECI>");
		sb.append("<RENTACOTIZADA>" + afiliado.getRentaPromedio() + "</RENTACOTIZADA>");
		sb.append("<RUTAFILIADO>" + afiliado.getRutAfiliado() + "</RUTAFILIADO>");
		sb.append("<MONTOTOTALCRED>" + afiliado.getCostoTotalCredito() + "</MONTOTOTALCRED>");
		if(afiliado.getInscripcionPension() == null) {
			sb.append("<NUMINSCPENSCAMP></NUMINSCPENSCAMP>");			
		} else {
			sb.append("<NUMINSCPENSCAMP>" + afiliado.getInscripcionPension() + "</NUMINSCPENSCAMP>");	
		}
		sb.append("<IDANEXOCAMPANA>" + afiliado.getIdCampagna() + "</IDANEXOCAMPANA>"); 
		sb.append("<TIPOSEGURO>" + afiliado.getTipoSeguro() + "</TIPOSEGURO>");
		sb.append("<SEGURODESGRAVAM>" + afiliado.getSeguroDesgravamen() + "</SEGURODESGRAVAM>");
		sb.append("<SEGUROCESANTIA>" + afiliado.getSeguroCesantia() + "</SEGUROCESANTIA>");
		sb.append("<FECHASIMULACION>" + afiliado.getFechaVigencia() + "</FECHASIMULACION>"); // Preguntar si es fecha de hoy o la de vigencia
		sb.append("</SolicitudIn></GMR_DATA></GMR_DATALIST>");
		
		contenidoPeticion = sb.toString();
		
		String respuestaXml="";
		JSONObject json = new JSONObject();
		JSONObject jsonSalidaOut = new JSONObject();
		JSONObject jsonGMR_ERRDATA = new JSONObject();
		String codigoError = "";
		JSONObject jsonSalida = new JSONObject();
		
		try {
			EvaluadorModelosSoapStub stub = new EvaluadorModelosSoapStub();
			
			stub._setProperty(EvaluadorModelosSoapStub.ENDPOINT_ADDRESS_PROPERTY, ep);
			
			SOAPHeaderElement authentication = new SOAPHeaderElement("http://schemas.ais-int.net/soap/AIS.Rules.WebServices/EvaluadorModelos","AuthHeader");
			SOAPHeaderElement user = new SOAPHeaderElement("http://schemas.ais-int.net/soap/AIS.Rules.WebServices/EvaluadorModelos","Username", username);
			SOAPHeaderElement pass = new SOAPHeaderElement("http://schemas.ais-int.net/soap/AIS.Rules.WebServices/EvaluadorModelos","Password", password);
			
			try {
				authentication.addChild(user);
				authentication.addChild(pass);
			} catch (SOAPException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			stub.setHeader(authentication);
			
			try {
				respuestaXml = stub.evaluarMotorGMR(idModelo, contenidoPeticion);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			json = XML.toJSONObject(respuestaXml);
			json = (JSONObject) json.get("GMR_DATALIST");
			json = (JSONObject) json.get("GMR_DATA");
			jsonSalidaOut = (JSONObject) json.get("SolicitudOut");
			jsonGMR_ERRDATA = (JSONObject) json.get("GMR_ERRDATA");
			jsonGMR_ERRDATA = (JSONObject) jsonGMR_ERRDATA.get("GMR_ERROR");
			codigoError = jsonGMR_ERRDATA.getString("GMR_ERRCODE");
			jsonSalida = Utils.formatJsonMotorAIS(jsonSalidaOut, codigoError, respuestaXml);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		return jsonSalida;
		
		
		
	}
	 
	
}