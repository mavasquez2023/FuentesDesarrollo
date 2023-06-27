package cl.araucana.bienestar.bonificaciones.ui.actions.operacion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.serv.ServicesOperacionesDelegate;
import cl.araucana.bienestar.bonificaciones.vo.UsuarioVO;

/**
 * @version 	1.0
 * @author
 */
public class GenerarPagosConveniosAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		DynaValidatorActionForm dynaValidatorActionForm =(DynaValidatorActionForm) form;

		String codigo=(String)dynaValidatorActionForm.get("codigo");
		
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		UsuarioVO user=new UsuarioVO();
		user.setNombre(userinformation.getNombres());
		user.setApellidoMaterno(userinformation.getApellidoMaterno());
		user.setApellidoPaterno(userinformation.getApellidoPaterno());
		user.setCodigoOficina(userinformation.getCodOficina());
		user.setUsuario(userinformation.getUsuario());

		ServicesOperacionesDelegate delegate = new ServicesOperacionesDelegate();
		delegate.generarPagoConvenios(Long.parseLong(codigo),user);
		String referer="/setFichaPagoConvenios.do";
		String target="success";

		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("referer",referer);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);

	}
}
