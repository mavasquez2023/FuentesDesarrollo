package cl.laaraucana.simulacion.actions;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.EmpresaVO;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.util.UserPrincipal;
import cl.laaraucana.simulacion.VO.UsuarioVO;
import cl.laaraucana.simulacion.utils.Configuraciones;
import cl.laaraucana.simulacion.utils.RutUtil;
 
public class LoginRedirectAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forward = "error";
		HttpSession sesion = request.getSession();
		try {
			String service = request.getParameter("service");
			String url = Configuraciones.getConfig(service);
			String uc = request.getParameter("uc");
			
			if(uc!=null){
				UserPrincipal newUser = UserPrincipal.decodeUserCredentials(uc);
				UsuarioVO user = (UsuarioVO) sesion.getAttribute("datosUsuario");
				sesion.setAttribute("esEmpresa", null);
				/*if(user==null || !newUser.getName().equals(user.getRut())){*/
					//Autentica a nuevo usuario
					UserRegistryConnection conn = null;
					try {
						//Autentica Ldap
						conn = new UserRegistryConnection(newUser);
						user = new UsuarioVO();
						user.setRut(newUser.getName());
						
						//Verifica si es empresa
						try {
							String rutEmp = request.getParameter("empresa");
							Long rutUser = RutUtil.getRutLong(user.getRut());
							//Indica por parametro que es empresa
							if(rutEmp!=null && !rutEmp.trim().isEmpty()){
								ServicesAutoconsultaDelegate delegate = new ServicesAutoconsultaDelegate();
								@SuppressWarnings("unchecked")
								Collection<EmpresaVO> emp = delegate.getDatosEmpresa(Long.parseLong(rutEmp));
								for (EmpresaVO empresa : emp) {
									user.setRut(empresa.getRut() +"-"+empresa.getDv());
									user.setNombreCompleto(empresa.getNombre());
								}
								sesion.setAttribute("esEmpresa", "1");
							}else{
								//Verifica en la base de datos si es empresa
								ServicesAutoconsultaDelegate delegate = new ServicesAutoconsultaDelegate();
								@SuppressWarnings("unchecked")
								Collection<EmpresaVO> emp = delegate.getDatosEmpresa(rutUser);
								if(emp.size() != 0)
									sesion.setAttribute("esEmpresa", "1");
							}
						} catch (Exception e) {
							e.printStackTrace();
							
						}
						sesion.setAttribute("datosUsuario", user);
						conn.close();
						response.sendRedirect(request.getContextPath()+url);
						return null;
					} catch (Exception e) {
						e.printStackTrace();
						conn.close();
						request.setAttribute("errorMsg", "Error;Usuario no superó autenticación");
						forward = "error";
					}
			/*	}else{
					//Si ya inicio sesion, redirecciona
					response.sendRedirect(request.getContextPath()+url);
					return null;
				}*/
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", "Error;La url a la que está intentando acceder no está disponible");
			forward = "error";
		}
		return mapping.findForward(forward);
	}
}
