package cl.araucana.bienestar.bonificaciones.ui.actions.convenio;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.model.Convenio;
import cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosDelegate;

/**
 * @version 	1.0
 * @author
 */
public class SetFichaConvenioAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		// Objeto de Permisos de la Aplicación
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

//		Logger logger = Logger.getLogger(SetFichaConvenioAction.class);

		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm) form;

		String codigo=request.getParameter("convenio");
		Convenio convenio=null;

		ArrayList opciones=null;
		ArrayList opcionesValores=null;
		String botonera=null;

		if(codigo==null) codigo="";
		if(codigo.equals("")){
			convenio=new Convenio();
			opciones=new ArrayList();
			opcionesValores=new ArrayList();
			if (userinformation.hasAccess("convCrea")) {
				opciones.add("Crear Convenio");
				opcionesValores.add("1");				
			}
		}
		else{
			ServicesConveniosDelegate delegate=new ServicesConveniosDelegate();
			convenio=delegate.getConvenio((long)Long.parseLong(codigo)); 
			if(convenio.getEstado().equals(Convenio.STD_BORRADOR)){
				opciones=new ArrayList();
				opcionesValores=new ArrayList();
				if (userinformation.hasAccess("convActiva")) {
					opciones.add("Activar Convenio");
					opcionesValores.add("2");
				}
				if (userinformation.hasAccess("convBaja")) {
					opciones.add("Dar de Baja Convenio");
					opcionesValores.add("3"); 
				}
				if (userinformation.hasAccess("convActualiza")) {
					opciones.add("Actualizar Convenio");
					opcionesValores.add("4");
				}
			}
			else if(convenio.getEstado().equals(Convenio.STD_ACTIVO)){
				opciones=new ArrayList();
				opcionesValores=new ArrayList();
				if (userinformation.hasAccess("convBaja")) {
					opciones.add("Dar de Baja Convenio");
					opcionesValores.add("3"); 
				}
				if (userinformation.hasAccess("convActualiza")) {
					opciones.add("Actualizar Convenio");
					opcionesValores.add("4");
				}
				if (userinformation.hasAccess("casCrea")) {
					opciones.add("Crear Caso");
					opcionesValores.add("5");
				}
				botonera="si";
			}
		}
		dynaValidatorActionForm.set("nombre",convenio.getNombre());
		dynaValidatorActionForm.set("concepto",String.valueOf(convenio.getCodigoConcepto()));
		dynaValidatorActionForm.set("rut",convenio.getRut());
		dynaValidatorActionForm.set("dv",convenio.getDvRut());
		dynaValidatorActionForm.set("estado",convenio.getEstado());
		dynaValidatorActionForm.set("numCuotasExternas",String.valueOf(convenio.getNumeroMaximoCuotas()));
		request.getSession(false).setAttribute("convenio",convenio);
		request.getSession(false).setAttribute("convenio.opciones",opciones);
		request.getSession(false).setAttribute("convenio.opciones.valores",opcionesValores);
		request.getSession(false).setAttribute("convenio.botonera",botonera);


		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward("convenio");
		this.saveToken(request);
		return (forward);
	}
}
