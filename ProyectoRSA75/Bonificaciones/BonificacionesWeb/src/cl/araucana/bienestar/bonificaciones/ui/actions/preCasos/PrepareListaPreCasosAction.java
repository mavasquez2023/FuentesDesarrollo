package cl.araucana.bienestar.bonificaciones.ui.actions.preCasos;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;

/**
 * @version 	1.0
 * @author		Alejandro Sepúlveda
 */
public class PrepareListaPreCasosAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		// Objeto de Permisos de la Aplicación
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();
		
		ServicesCasosDelegate casosDelegate = new ServicesCasosDelegate();

		ArrayList opciones=new ArrayList();
		ArrayList opcionesValores=new ArrayList();
		ArrayList acciones=new ArrayList();
		ArrayList accionesValores=new ArrayList();
		String target=null;

		if (userinformation.hasAccess("casCrea")) {
			opciones.add("Crear PreCaso");
			opcionesValores.add("1");
		}

		if (userinformation.hasAccess("prcEgreso"))
			request.getSession(false).setAttribute("puedeGenerarEgreso","true");
		else
			request.getSession(false).removeAttribute("puedeGenerarEgreso");
			
		if (userinformation.hasAccess("prcIngreso")){
			request.getSession(false).setAttribute("puedeGenerarIngreso","true");	
			acciones.add("Generar Ingreso Aporte Isapre");
			accionesValores.add("1");
			acciones.add("Generar Otros Ingresos");
			accionesValores.add("2");			
			acciones.add("Generar Egreso");
			accionesValores.add("3");
			acciones.add("Terminar Pre-Caso");
			accionesValores.add("4");
		}
		else
			request.getSession(false).removeAttribute("puedeGenerarIngreso");
					
		if (userinformation.hasAccess("prcActivar"))
			request.getSession(false).setAttribute("puedeActivar","true");
		else
			request.getSession(false).removeAttribute("puedeActivar");
		
		ArrayList listaPreCasosPorGenerarEgreso = casosDelegate.getListaPreCasosPorGenerarEgreso();
		ArrayList listaPreCasosConPorLoMenosUnEgresoGenerado = casosDelegate.getListaPreCasosConPorLoMenosUnEgresoGenerado();
		
		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("opciones",opciones);
		request.getSession(false).setAttribute("opciones.valores",opcionesValores);
		request.getSession(false).setAttribute("acciones",acciones);
		request.getSession(false).setAttribute("acciones.valores",accionesValores);		
		request.getSession(false).setAttribute("listaPreCasosPorGenerarPrimerEgreso",listaPreCasosPorGenerarEgreso);
		request.getSession(false).setAttribute("listaPreCasosConPorLoMenosUnEgresoGenerado",listaPreCasosConPorLoMenosUnEgresoGenerado);	

		target="listaPreCasos";

		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.saveToken(request);
		return (forward);

	}
}
