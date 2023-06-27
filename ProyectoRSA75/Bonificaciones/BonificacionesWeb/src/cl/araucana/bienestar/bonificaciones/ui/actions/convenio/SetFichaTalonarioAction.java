package cl.araucana.bienestar.bonificaciones.ui.actions.convenio;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.model.Convenio;
import cl.araucana.bienestar.bonificaciones.model.Talonario;

/**
 * @version 	1.0
 * @author
 */
public class SetFichaTalonarioAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

			// Objeto de Permisos de la Aplicación
			cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
			if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

			DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm) form;

			String codigo=request.getParameter("codigo");
			Talonario talonario=null;
			Convenio convenio=(Convenio)request.getSession(false).getAttribute("convenio");
			ArrayList opciones=new ArrayList();
			ArrayList opcionesValores=new ArrayList();
//			String botonera=null;

			if(codigo==null) codigo="";
			if(codigo.equals("")){
				talonario=new Talonario();
				talonario.setFechaIngreso(new Date());
				if (userinformation.hasAccess("convTalCrea")) {
					opciones.add("Crear Talonario");
					opcionesValores.add("1");				
				}
			}
			else{
//				ServicesSociosDelegate delegate=new ServicesSociosDelegate();
				int indice=(int)Integer.parseInt(codigo);
				talonario=convenio.getTalonario(indice); 
				if (userinformation.hasAccess("convTalElimina")) {
					opciones.add("Eliminar Talonario");
					opcionesValores.add("2");
				}
				if (userinformation.hasAccess("convTalActualiza")) {			
					opciones.add("Modificar Talonario");
					opcionesValores.add("3");
				}
//				botonera="si";
			}
			dynaValidatorActionForm.set("inicioSecuencia",String.valueOf(talonario.getInicioSecuencia()));
			dynaValidatorActionForm.set("finSecuencia",String.valueOf(talonario.getFinSecuencia()));

			request.getSession(false).setAttribute("talonario",talonario);
			request.getSession(false).setAttribute("talonario.opciones",opciones);
			request.getSession(false).setAttribute("talonario.opciones.valores",opcionesValores);

			// Write logic determining how the user should be forwarded.
			ActionForward forward = new ActionForward();
			forward = mapping.findForward("talonario");
			this.saveToken(request);
			return (forward);

	}
}
