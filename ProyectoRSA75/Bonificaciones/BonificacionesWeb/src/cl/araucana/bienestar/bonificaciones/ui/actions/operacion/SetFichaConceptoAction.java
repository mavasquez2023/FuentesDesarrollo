package cl.araucana.bienestar.bonificaciones.ui.actions.operacion;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.model.Concepto;
import cl.araucana.bienestar.bonificaciones.serv.ServicesOperacionesDelegate;

/**
 * @version 	1.0
 * @author
 */
public class SetFichaConceptoAction extends Action {

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
	
		ServicesOperacionesDelegate delegate=new ServicesOperacionesDelegate();
		String codigo=request.getParameter("codigo");
		String botoneraConvenio=null;
		String botoneraCobertura=null;
		Concepto concept=null;
		ArrayList opciones=new ArrayList();
		ArrayList opcionesValores=new ArrayList();		

		if(codigo==null) codigo="";
		if(codigo.equals("")){
			concept=new Concepto();
			concept.setFechaCreacion(new Date());
			if (userinformation.hasAccess("opeConceptoActiva")) {
				opciones.add("Activar Concepto");
				opcionesValores.add("1");	 
			}
		}
		else{
			concept=delegate.getConcepto(Long.parseLong(codigo));			

			if (userinformation.hasAccess("opeConceptoModifica")) {
				opciones.add("Modificar Concepto");
				opcionesValores.add("2");
			}
			if (userinformation.hasAccess("opeConceptoElimina")) {
				opciones.add("Eliminar Concepto");
				opcionesValores.add("3");
			}
		} 
		
		dynaValidatorActionForm.set("descripcion",concept.getDescripcion());
		dynaValidatorActionForm.set("cuentaDeudora",String.valueOf(concept.getCuentaDeudor()));
		dynaValidatorActionForm.set("cuentaAcreedora",String.valueOf(concept.getCuentaAcreedor()));
		dynaValidatorActionForm.set("areaTesoreria",String.valueOf(concept.getTesoreriaArea()));
		dynaValidatorActionForm.set("conceptoTesoreriaIngreso",String.valueOf(concept.getTesoreriaConceptoIngreso()));
		dynaValidatorActionForm.set("conceptoTesoreriaEgreso",String.valueOf(concept.getTesoreriaConceptoEgreso()));

		if(concept.getCodigo()>0){
			botoneraConvenio="SI";
			botoneraCobertura="SI";
		}

		ArrayList cuentasAcreedor=delegate.getCuentasAcreedores();
		ArrayList cuentasDeudor=delegate.getCuentasDeudores();
		ArrayList areasTesoreria=delegate.getAreasTesoreria();
		ArrayList conceptosTesoreriaConveniosIngresos=delegate.getConceptosTesoreriaBienestarConveniosIngresos();
		ArrayList conceptosTesoreriaConveniosEgresos=delegate.getConceptosTesoreriaBienestarConveniosEgresos();		
		ArrayList conceptosTesoreriaSaludIngresos=delegate.getConceptosTesoreriaBienestarSaludIngresos();
		ArrayList conceptosTesoreriaSaludEgresos=delegate.getConceptosTesoreriaBienestarSaludEgresos();
		
		ArrayList conceptosTesoreriaIngresos = new ArrayList();
		ArrayList conceptosTesoreriaEgresos = new  ArrayList();
		
		conceptosTesoreriaIngresos.addAll(conceptosTesoreriaConveniosIngresos);
		conceptosTesoreriaIngresos.addAll(conceptosTesoreriaSaludIngresos);
		
		conceptosTesoreriaEgresos.addAll(conceptosTesoreriaConveniosEgresos);
		conceptosTesoreriaEgresos.addAll(conceptosTesoreriaSaludEgresos);		

		// pongo el objeto Concepto y Opciones en memoria
		request.getSession(false).setAttribute("concepto.cuenta.acreedora",cuentasAcreedor);
		request.getSession(false).setAttribute("concepto.cuenta.deudora",cuentasDeudor);
		request.getSession(false).setAttribute("concepto.areas.tesoreria",areasTesoreria);
		request.getSession(false).setAttribute("concepto.tesoreria.ingresos",conceptosTesoreriaIngresos);
		request.getSession(false).setAttribute("concepto.tesoreria.egresos",conceptosTesoreriaEgresos);
		request.getSession(false).setAttribute("concepto",concept);
		request.getSession(false).setAttribute("concepto.opciones",opciones);
		request.getSession(false).setAttribute("concepto.opciones.valores",opcionesValores);
		request.getSession(false).setAttribute("concepto.botonera.convenio",botoneraConvenio);
		request.getSession(false).setAttribute("concepto.botonera.cobertura",botoneraCobertura);
	
		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward("concepto");
		this.saveToken(request);
		return (forward);

	}
}
