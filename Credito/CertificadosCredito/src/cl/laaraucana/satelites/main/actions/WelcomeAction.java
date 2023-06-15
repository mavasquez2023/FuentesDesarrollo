package cl.laaraucana.satelites.main.actions;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.laaraucana.satelites.Utils.ProxyLDAP;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.main.forms.IngresoRutForm;
import cl.laaraucana.satelites.webservices.client.InfoAfiliado.ClienteInfoAfiliado;
import cl.laaraucana.satelites.webservices.client.InfoAfiliado.VO.EntradaInfoAfiliadoVO;
import cl.laaraucana.satelites.webservices.client.InfoAfiliado.VO.SalidainfoAfiliadoVO;
import cl.laaraucana.satelites.webservices.model.UsuarioAfiliadoVO;
import cl.laaraucana.satelites.webservices.utils.UsuarioServiceUtil;

/**
 * @version 1.0
 * @author
 */
public class WelcomeAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());
	public ActionForward execute(ActionMapping mapping, ActionForm _form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward forward = new ActionForward(); // return value
		try {
			
			IngresoRutForm form = (IngresoRutForm) _form;
			String rut = form.getRut();
			rut= rut.replaceAll("\\.", "");
			String rutemp = form.getRutemp();
			rutemp= rutemp.replaceAll("\\.", "");
			
			HttpSession sesion = request.getSession();
			//Principal principal = request.getUserPrincipal();
			ActionErrors errores = form.validate(mapping, request);
			//String username=null;
			
			if(!errores.isEmpty()){		
				saveErrors(request, errores);
				sesion.setAttribute("rutCRM", rut);
				sesion.setAttribute("rut", "");
				sesion.setAttribute("nombre", "");
				sesion.setAttribute("rutEmpresa", rutemp);
				return mapping.findForward("success");
			}
			
			
			if (rut != null && !rut.equals("")) {
				UsuarioVO userCRM = new UsuarioVO();
				try {
					ClienteInfoAfiliado infoafil= new ClienteInfoAfiliado();
					EntradaInfoAfiliadoVO entradainfoVO = new EntradaInfoAfiliadoVO();
					entradainfoVO.setRut(rut);
					SalidainfoAfiliadoVO salidainfoVO= (SalidainfoAfiliadoVO)infoafil.call(entradainfoVO);
					
					sesion.setAttribute("rut", rut);
					sesion.setAttribute("nombre", salidainfoVO.getNombreCompleto());
					sesion.setAttribute("nombreAfiliado",  salidainfoVO.getNombreCompleto());
					userCRM.setNombre(salidainfoVO.getNombreCompleto());
					userCRM.setRut(Integer.parseInt(rut.split("-")[0]));
					userCRM.setDv(rut.split("-")[1]);
					sesion.removeAttribute("mensaje");
					if(salidainfoVO.isDeudordirecto()){
						sesion.setAttribute("mensaje", "RUT ingresado no es afiliado a Caja de Compensación de Asignación Familiar La Araucana");
					}
					userCRM.setNombre(salidainfoVO.getNombreCompleto());
					//Limpiar las variables de sesion de rut anterior
					sesion.removeAttribute("creditosCancelados");
					sesion.removeAttribute("creditosVigentes");
					sesion.removeAttribute("creditosPrepago");
					sesion.removeAttribute("certificadoPrepago");
					sesion.removeAttribute("mensaje");
					sesion.removeAttribute("afiliadoCRM");
					sesion.removeAttribute("empresasList");
				} catch (Exception e) {
					// Rut no valido
					errores.add("rut", new ActionMessage("errors.rut.invalido"));
					saveErrors(request, errores);
				}
				
				
				sesion.setAttribute("rutCRM", rut);
				sesion.setAttribute("datosUsuario", userCRM);
				sesion.setAttribute("url", "/CertificadosCredito2/main/empty.jsp");
			} 
			if (rutemp != null && !rutemp.equals("")) {
				sesion.setAttribute("rutEmpresa", rutemp);
				sesion.setAttribute("url", "/CertificadosCredito2/main/emptyEmp.jsp");
			}
			
			forward = mapping.findForward("success");
		} catch (Exception e) {
			forward = Utils.returnErrorForward(mapping, e, this.getClass());
		}
		return (forward);

	}
}
