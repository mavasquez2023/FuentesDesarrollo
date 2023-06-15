/**
 * 
 */
package cl.laaraucana.simulacion.actions.simuladoracuerdo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.simple.JSONValue;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaCreditoVigenteVO;
import cl.laaraucana.simulacion.webservices.client.DataServiceUtil;
import cl.laaraucana.simulacion.webservices.client.CampañaAP.VO.SalidaCampañaVO;

/**
 * @author IBM Software Factory
 *
 */
public class DispatchData extends DispatchAction{
	
	public void getCabecerasCredito(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String contrato= request.getParameter("contrato").toString();
		List<Map<String, String>> listaConceptos= new ArrayList<Map<String,String>>();
		try {
			//tipoAfiliado = URLDecoder.decode(request.getParameter("tipoAfiliado"), "UTF-8");
			HttpSession sesion= request.getSession();
			Map<String, SalidaCreditoVigenteVO> cabeceras= (Map<String, SalidaCreditoVigenteVO>)sesion.getAttribute("cabeceras");
			SalidaCreditoVigenteVO cabeceraVO= cabeceras.get(contrato);
			listaConceptos= DataServiceUtil.obtenerMapCabeceraCredito(cabeceraVO);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		setResponse(response, listaConceptos);
	}
	
	public void getCampanhasAP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String salida="";
		List<Map<String, String>> lista= new ArrayList<Map<String, String>>();
		try {
			String contrato= request.getParameter("contrato").toString();
			Map<String, SalidaCampañaVO> listaCampañas= (Map<String, SalidaCampañaVO>)request.getSession().getAttribute("campañas");
			if(listaCampañas!=null){
				SalidaCampañaVO campaña= listaCampañas.get(contrato);
				if(campaña!=null){
					salida= DataServiceUtil.obtenerTextoCampañasAP(campaña, contrato);

					Map<String, String> mapCampaña= new HashMap<String, String>();
					mapCampaña.put("concepto", "mensajeCampanha");
					mapCampaña.put("valor", salida);
					lista.add(mapCampaña);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		setResponse(response, lista);
	}
	
	public void getCuotas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tipoProducto= request.getParameter("tipoProducto").toString();
		List<Map<String, String>> listaCuotas= new ArrayList<Map<String,String>>();
		try {
			//tipoAfiliado = URLDecoder.decode(request.getParameter("tipoAfiliado"), "UTF-8");
			listaCuotas= DataServiceUtil.obtenerMapCuotas(tipoProducto, 0);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		setResponse(response, listaCuotas);
	}
	
	private void setResponse(HttpServletResponse response, List<Map<String, String>> list) throws IOException {
		//response.setContentType(Constants.CONTENT_TYPE_JSON);
		String jsonString = JSONValue.toJSONString(list);
		response.setContentType("text/json");
		response.getWriter().write(jsonString);
		
		/*for (Iterator iterator = str.iterator(); iterator.hasNext();) {
			String value = (String) iterator.next();
			String option=  "<option value=\"" + value + "\">" + value + "</option>";
			response.getWriter().write("\n" + option);
		}*/
		//response.getWriter().write("<br>" + str);
		//System.out.println("la res es: " + str);
	}
	
}


