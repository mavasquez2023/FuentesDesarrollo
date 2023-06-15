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
import cl.laaraucana.simulacion.webservices.client.Campa�aAP.VO.SalidaCampa�aVO;

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
			Map<String, SalidaCampa�aVO> listaCampa�as= (Map<String, SalidaCampa�aVO>)request.getSession().getAttribute("campa�as");
			if(listaCampa�as!=null){
				SalidaCampa�aVO campa�a= listaCampa�as.get(contrato);
				if(campa�a!=null){
					salida= DataServiceUtil.obtenerTextoCampa�asAP(campa�a, contrato);

					Map<String, String> mapCampa�a= new HashMap<String, String>();
					mapCampa�a.put("concepto", "mensajeCampanha");
					mapCampa�a.put("valor", salida);
					lista.add(mapCampa�a);
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


