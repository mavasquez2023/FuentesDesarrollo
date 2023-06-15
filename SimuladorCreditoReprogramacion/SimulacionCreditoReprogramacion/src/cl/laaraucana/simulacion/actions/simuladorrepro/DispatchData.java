/**
 * 
 */
package cl.laaraucana.simulacion.actions.simuladorrepro;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
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
			for (Iterator iterator = listaConceptos.iterator(); iterator
					.hasNext();) {
				Map<String, String> map = (Map<String, String>) iterator.next();
				sesion.setAttribute(map.get("concepto"), map.get("valor"));
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		setResponse(response, listaConceptos);
	}
	
	public void getProductos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tipoAfiliado= request.getParameter("tipoAfiliado").toString();
		String insolvencia= request.getSession().getAttribute("insolvencia").toString();
		List<Map<String, String>> listaProductos= new ArrayList<Map<String,String>>();
		try {
			//tipoAfiliado = URLDecoder.decode(request.getParameter("tipoAfiliado"), "UTF-8");
			boolean tieneInsolvencia= insolvencia.equals("X")?true:false;
			listaProductos= DataServiceUtil.obtenerMapProductosSap(tipoAfiliado, tieneInsolvencia);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		setResponse(response, listaProductos);
	}
	
	public void getCuotas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tipoProducto= request.getParameter("tipoProducto").toString();
		String edad= request.getParameter("edad").toString();
		List<Map<String, String>> listaCuotas= new ArrayList<Map<String,String>>();
		try {
			//tipoAfiliado = URLDecoder.decode(request.getParameter("tipoAfiliado"), "UTF-8");
			listaCuotas= DataServiceUtil.obtenerMapCuotas(tipoProducto, Integer.parseInt(edad));
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		setResponse(response, listaCuotas);
	}
	
	public void getMesesGracia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Object param=  request.getParameter("tipoProducto");
		String tipoProducto= param!=null?param.toString():"";
		param=  request.getParameter("tipoAfiliado");
		String tipoAfiliado= param!=null?param.toString():"";
		List<Map<String, String>> listaMesesGracia= new ArrayList<Map<String,String>>();
		try {
			//tipoAfiliado = URLDecoder.decode(request.getParameter("tipoAfiliado"), "UTF-8");
			listaMesesGracia= DataServiceUtil.obtenerMapMesesGracia(tipoProducto, tipoAfiliado);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		setResponse(response, listaMesesGracia);
	}
	
	public void getCesantia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {

		Object param= request.getParameter("tipoAfiliado");
		String tipoAfiliado= param!=null?param.toString():"";
		List<Map<String, String>> listaCesantia= new ArrayList<Map<String,String>>();
		try {
			listaCesantia= DataServiceUtil.obtenerMapCesantia(tipoAfiliado);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		setResponse(response, listaCesantia);
	}
	
	public void getDesgravamen(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		List<Map<String, String>> listaDesgrvamen= new ArrayList<Map<String,String>>();
		try {
			listaDesgrvamen= DataServiceUtil.obtenerMapDesgravamen();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		setResponse(response, listaDesgrvamen);
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


