package cl.laaraucana.satelites.certificados.finiquito.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.laaraucana.satelites.Utils.Constants;

/**
 * @version 	1.0
 * @author
 */
public class FiniquitoInicioViewAction extends Action

{

    public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {

	//ActionMessages errors = new ActionMessages();
	ActionForward forward = new ActionForward(); // return value

	try {
		//System.out.println("=================cargando la variable de ambiente desde webphere");
    	//String ambiente = System.getProperty("CustomAmbienteSatelites");
    	//System.out.println(ambiente);
    	//CustomAmbienteSatelites 
    	//Context ctx = new InitialContext();
    	//String myVar = (String) ctx.lookup("java:comp/env/ambienteSatelites");
    	//System.out.println("desde el java:comp:env "+myVar);
		/*HttpSession sesion = request.getSession();
		UsuarioVO usuario = (UsuarioVO) sesion.getAttribute("datosUsuario");
		
		UsuarioAfiliadoVO user = UsuarioServiceUtil.obtenerAfiliado(usuario.getRut() + "-" + usuario.getDv());
		System.out.println("=================================================");
		System.out.println("se ingreso el rut " + usuario.getRut() + "-"+ usuario.getDv() + " y el nombre es "+ user.getNombreAfiliado());
		if (!user.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
			String mensaje = user.getMensaje();
			return new ActionForward(mapping.findForward("error").getPath()+ "?errorMsg=" + mensaje, false);
		}*/
		
		HttpSession sesion = request.getSession();
		UsuarioVO usuario = (UsuarioVO) sesion.getAttribute("datosUsuario");
		if(usuario == null){
			request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
			request.setAttribute("message", Constants.SESION_EXPIRED);
			return mapping.findForward("customError");
		}
		
		forward = mapping.findForward("success");

	} catch (Exception e) {
	}

	return (forward);

    }
}
