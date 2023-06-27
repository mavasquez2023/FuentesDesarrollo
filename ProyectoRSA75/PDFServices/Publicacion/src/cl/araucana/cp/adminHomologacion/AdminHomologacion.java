package cl.araucana.cp.adminHomologacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.xalan.xsltc.compiler.sym;

import com.a.a.a.a.a.d;
import com.ibm.net.ssl.www.protocol.http.t;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class AdminHomologacion extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	public AdminHomologacion() {
		super();
		// TODO Apéndice de constructor generado automáticamente
	}
	
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		try {
			
			
 			String accion = request.getParameter("accion").toString();
 			String error = "";
		
			
			JSONArray json = new JSONArray(); 
			AdminHomologacionDAO dao = new AdminHomologacionDAO();	
			
			//arma tabla con los datos.
			if (accion.equals("desplegar")) {
				
				String tipo = request.getParameter("tipo").toString();
				
				String tabla = "<table width='650' border='0' cellspacing='0' cellpadding='0' id='tabla_cont'>";
				tabla+="<tr>";
				
				tabla+="<td width='20%'>Tipo</td>";
				tabla+="<td width='20%'>Valor CAJA</td>";
				tabla+="<td width='10%'>Valor DT</td>";
				tabla+="<td width='40%'>Descripci&oacute;n</td>";
				tabla+="<td width='10%'></td>";
				
				tabla+="</tr>";
				tabla+="</table>";
				
				tabla+="<div id='scrolling'>";
				
				tabla+= "<table width='650' border='0' cellspacing='0' cellpadding='0' id='tabla_cont'>";
				
				List lista = new ArrayList();
				try {
					lista = dao.GetLista(tipo);
				} catch (Exception e) {
					error = "Error al extraer los datos. contacte a su administrador";
				}
				
				for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
					HomologacionTO campo = (HomologacionTO) iterator.next();
					tabla+="<tr>";
					tabla+="<td width='20%'>"+tipo.trim()+"</td>";
					tabla+="<td width='20%'><input size='15' maxlength='20' type='text' id='campo_"+campo.getId()+"_caja' style='font-family:Arial;font-size:8pt;' value='"+ campo.getValorCaja() +"'/></td>";
					tabla+="<td width='15%'><input size='8' maxlength='20' type='text' id='campo_"+campo.getId()+"_dt' style='font-family:Arial;font-size:8pt;' value='"+ campo.getValorDT() +"'/></td>";
					tabla+="<td width='35%'><input size='35' maxlength='50' type='text' id='campo_"+campo.getId()+"_des' style='font-family:Arial;font-size:8pt;' value='"+ campo.getDesdcripcion() +"'/></td>";
					tabla+="<td width='5%'><div id='load_"+campo.getId()+"' style='display:none'><img src='Images/load.gif'/></div></td>";
					tabla+="<td width='10%'><input type='button' value='Guardar' class='btn1' style='Cursor:Pointer; Cursor:Hand' onclick=\"guardaCampo('"+campo.getId()+"','"+tipo.trim()+"')\"></td>";
					
					tabla+="</tr>";
				}
				tabla+="</table>";
				tabla+="</div>";
				json.add(error);
				json.add(tabla);
			}
			else
			{
				int id = Integer.parseInt(request.getParameter("id").toString());
				String valorCaja = request.getParameter("valorCaja").toString();
				String valorDT = request.getParameter("valorDt").toString();
				String descripcion = request.getParameter("descripcion").toString();
				String tipo = request.getParameter("tipo").toString();
				
				try {
					//verifica si el valor existe una sola vez si se repite el tipo y el valor caja en otro registro, no debe actualizarlo
					int x = dao.validaExistencia(tipo, valorCaja, id);
					if (x>0) {
						error = "La combinacion tipo con valorCaja deben ser unicos";
						HomologacionTO datosOriginales = dao.getCampo(id);
						
						valorCaja = datosOriginales.getValorCaja();
						valorDT = datosOriginales.getValorDT();
						descripcion = datosOriginales.getDesdcripcion();
					}
					else
					{
						x = dao.update(id, valorCaja, valorDT, descripcion);
						if (x==0) {
							error = "Error al actualizar los datos";
							
							HomologacionTO datosOriginales = dao.getCampo(id);
							
							valorCaja = datosOriginales.getValorCaja();
							valorDT = datosOriginales.getValorDT();
							descripcion = datosOriginales.getDesdcripcion();
						}
					}
					
				} catch (Exception e) {
					error = "Error al actualizar los datos, contacte a su administrador";
					System.out.println(e.getMessage());
				}
				
				json.add(error);
				json.add(valorCaja);
				json.add(valorDT);
				json.add(descripcion);
			}
			
			response.getWriter().write(json.toString());
			
		
		} catch (Exception e) {
			System.out.println("werror: "+e.getMessage());
		}
	}
	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}   	  	    
}
