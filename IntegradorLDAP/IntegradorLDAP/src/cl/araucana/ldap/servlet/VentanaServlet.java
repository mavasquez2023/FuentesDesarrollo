/**
 * 
 */
package cl.araucana.ldap.servlet;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import cl.araucana.core.registry.User;
import cl.araucana.core.util.Rut;
import cl.araucana.ldap.api.EmpresaVO;
import cl.araucana.ldap.api.ProxyLDAP;
import cl.araucana.ldap.business.IngresaUsuarioLDAP;
import cl.araucana.ldap.dao.SucursalDB2DAO;
import cl.araucana.ldap.ibatis.vo.SucursalesVO;
import cl.araucana.ldap.to.EncargadoAnexoTO;


/**
 * @author usist24
 *
 */
public class VentanaServlet extends HttpServlet {
	private static Logger log = Logger.getLogger(VentanaServlet.class);
	private static String GUARDAR_SUCURSALES = "GUARSUC";
	private static String CONSULTAR_SUCURSALES ="CONOFISUC";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String html = "";
		String forward= "";
		try{
			HttpSession session = request.getSession();
			
			String action = request.getParameter("action");
			
			 if (action.equals(CONSULTAR_SUCURSALES)) {
				forward= "sucursales.jsp";
				
				String idEmpresa = request.getParameter("rutempresa");
				idEmpresa= idEmpresa.replaceAll("\\.", "");
				String idAfiliado = request.getParameter("rutafiliado");
				String nombreEncargado = request.getParameter("nombre");
				String allOffice = request.getParameter("alloffice");
				idAfiliado= idAfiliado.replaceAll("\\.", "");
				HashMap<String, String> params= new HashMap<String, String>();
				params.put("rutemp", idEmpresa.split("-")[0]);
				params.put("rut",  idAfiliado.split("-")[0]);
				
				List<SucursalesVO> sucursales= SucursalDB2DAO.obtenerSucursales(params);
				EncargadoAnexoTO encargadoTO= new EncargadoAnexoTO();
				encargadoTO.setSucursales(sucursales);
				encargadoTO.setNombreEncargado(nombreEncargado);
				encargadoTO.setRutEncargado(idAfiliado);
				encargadoTO.setRutEmpresa(idEmpresa);
				encargadoTO.setAllOffice(allOffice);
				
				request.getSession().setAttribute("encargado", encargadoTO);


			}else if (action.equals(GUARDAR_SUCURSALES)){
				String[] sucursales = request.getParameterValues("codSucursal");
				EncargadoAnexoTO encargadoTO= (EncargadoAnexoTO) request.getSession().getAttribute("encargado");
				String rutEmpresa= encargadoTO.getRutEmpresa();
				String rutEncargado= encargadoTO.getRutEncargado();
				String allSucursal= request.getParameter("allSucursal");
				
				HashMap<String, String> params= new HashMap<String, String>();
				params.put("rutemp", rutEmpresa.split("-")[0]);
				params.put("rut", rutEncargado.split("-")[0]);
				SucursalDB2DAO.ejecutarDelete("custom.delete_oficinas_anexo", params);
				if(allSucursal==null || allSucursal.equals("")){
					params.put("allOffice", "NO");
					SucursalDB2DAO.ejecutarUpdate("custom.update_alloficina_anexo", params);
					for (int i = 0; i < sucursales.length; i++) {
						String[] ofisuc= sucursales[i].split(":");
						params.put("codOficina", ofisuc[0]);
						params.put("codSucursal", ofisuc[1]);
						SucursalDB2DAO.ejecutarInsert("custom.insert_sucursal_anexo", params);
					}
				}else{
					params.put("allOffice", "SI");
					SucursalDB2DAO.ejecutarUpdate("custom.update_alloficina_anexo", params);
				}
				List<SucursalesVO> new_sucursales= SucursalDB2DAO.obtenerSucursales(params);
				encargadoTO.setSucursales(new_sucursales);
				request.getSession().setAttribute("encargado", encargadoTO);
				forward= "sucursales.jsp";
				request.setAttribute("update_ok", "1");
			}
			
		}catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("update_ok", "0");
		}	
		log.info("Forward a:" + request.getContextPath() + "/" + forward);
		request.getRequestDispatcher("/" + forward).forward(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void insertAuditoria(String usercreate, String username, String action, String tipo, String sms, String email){
		try {
			HashMap auditoria= new HashMap();
			auditoria.put("username", username);
			auditoria.put("usercreate", usercreate);
			auditoria.put("accion", action);
			auditoria.put("tipo", tipo);
			auditoria.put("sms", sms);
			auditoria.put("email", email);
			log.info("Params auditoria, username=" + username + ", usercreate" + usercreate + ", action=" + action + ", tipo=" + tipo + ", sms=" + sms + ", email= " + email);
			if(usercreate!=null){
				int resultado= IngresaUsuarioLDAP.grabarAuditoria("custom.insertLDAP4500", auditoria);
				log.info(">>Resultado grabacion Auditoria:" + resultado);
			}
		} catch (Exception e) {
			log("Error al grabar tabla auditoria");
			e.printStackTrace();
		}
	}
}
