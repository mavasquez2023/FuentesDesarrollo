package cl.araucana.bienestar.bonificaciones.ui.actions.socio;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.model.Vale;
import cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosDelegate;

/**
 * @version 	1.0
 * @author
 */
public class SetFichaValeAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		String codigo=request.getParameter("codigo");
		String codigoTal=request.getParameter("codigoTal");
		String codigoVale=request.getParameter("codigoVale");
		Socio socio=(Socio)request.getSession(false).getAttribute("socio");
		ArrayList opciones=new ArrayList();
		ArrayList opcionesValores=new ArrayList();

		// Objeto de Permisos de la Aplicación
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		ServicesConveniosDelegate delegate= new ServicesConveniosDelegate();
		if(codigo==null) codigo="";
		if(codigoTal==null) codigoTal="";
		if(codigoVale==null) codigoVale="";
		Vale vale=null;
		String target="vale";
		String listaOpciones="si";
		
		if(!codigoTal.equals("")){
			vale=delegate.getValeDisponible((long)Long.parseLong(codigoTal));
			if (userinformation.hasAccess("socAsignaVale")) {
				opciones.add("Asociar Vale");
				opcionesValores.add("1");	
			}
		} else if(!codigoVale.equals("")){
			vale=delegate.getVale((long)Long.parseLong(codigoVale));
			listaOpciones=null; 
		}

		else if(!codigo.equals("")){
			ArrayList listaVales=socio.getVale();
			vale=(Vale)listaVales.get((int)Integer.parseInt(codigo));
			if (userinformation.hasAccess("socAnulaVale")) {
				opciones.add("Anular Vale");
				opcionesValores.add("2");	
			}
		}
		

		request.getSession(false).setAttribute("vale",vale);
		request.getSession(false).setAttribute("vale.opciones",opciones);
		request.getSession(false).setAttribute("vale.opciones.valores",opcionesValores);
		request.getSession(false).setAttribute("vale.lista.opciones",listaOpciones);
		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.saveToken(request);
		return (forward);


	}
}
