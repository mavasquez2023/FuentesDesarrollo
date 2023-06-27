package cl.araucana.bienestar.bonificaciones.ui.actions.cobertura;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.model.Cobertura;
import cl.araucana.bienestar.bonificaciones.model.Convenio;
import cl.araucana.bienestar.bonificaciones.model.Producto;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCoberturasDelegate;
import cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosDelegate;
import cl.araucana.bienestar.bonificaciones.serv.ServicesOperacionesDelegate;

/**
 * @version 	1.0
 * @author
 */
public class GetListaRangosAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		// Objeto de Permisos de la Aplicación
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		Logger logger = Logger.getLogger(GetListaRangosAction.class);
		
		DynaValidatorActionForm dynaValidatorActionForm =(DynaValidatorActionForm) form;
		ArrayList lr=(ArrayList)request.getSession(false).getAttribute("lista.rangos");

		String source=request.getParameter("source");
		if(source==null) source="";
		
		ArrayList opciones=new ArrayList();
		ArrayList opcionesValores=new ArrayList();
		ArrayList listaRangos=new ArrayList();
		String target=null;
		
		if(!source.equals("convenio")){
			Cobertura cobertura = (Cobertura)request.getSession(false).getAttribute("cobertura");
			if(lr==null){
				ServicesCoberturasDelegate delegate = new ServicesCoberturasDelegate();
				cobertura=delegate.getAllRangosCobertura(cobertura); 
			}
			if (userinformation.hasAccess("cobRangoAgrega")) {
				opciones.add("Agregar Rango");
				opcionesValores.add("1");
			}

			if(cobertura.getRangoVigente()!= null && cobertura.getRangoVigente().getRangos().size()>0){
				if (userinformation.hasAccess("cobRangoModifica")) {
					opciones.add("Modificar Rango");
					opcionesValores.add("2");
				}
				if (userinformation.hasAccess("cobRangoElimina")) {
					opciones.add("Eliminar Rango");
					opcionesValores.add("3");			
				}
			}
			if (userinformation.hasAccess("cobRangoRegistra")) {
				opciones.add("Registrar Información en Sistema");
				opcionesValores.add("4");			
			}

			dynaValidatorActionForm.set("cuentaGasto",String.valueOf(cobertura.getCuentaGasto()));
			
			if(cobertura.getRangoVigente()!=null)
				listaRangos=cobertura.getRangoVigente().getRangos();
	
			request.getSession(false).setAttribute("cobertura",cobertura);
			target="listaRangos";
		}
		else{
			Convenio convenio=(Convenio)request.getSession(false).getAttribute("convenio"); 
			Producto producto=(Producto)request.getSession(false).getAttribute("producto"); 
			if(lr==null){
				ServicesConveniosDelegate delegate = new ServicesConveniosDelegate();
				producto=delegate.getRangosProducto(convenio.getCodigo(),producto); 
			}
			if (userinformation.hasAccess("convProRangoAgrega")) {
				opciones.add("Agregar Rango");
				opcionesValores.add("1");
			}
			if(producto.getRango().size()>0){
				if (userinformation.hasAccess("convProRangoModifica")) {
					opciones.add("Modificar Rango");
					opcionesValores.add("2");
				}
				if (userinformation.hasAccess("convProRangoElimina")) {
					opciones.add("Eliminar Rango");
					opcionesValores.add("3");			
				}
			}
			if (userinformation.hasAccess("convProRangoRegistra")) {
				opciones.add("Registrar Información en Sistema");
				opcionesValores.add("4");			
			}
			logger.debug("cuentaGasto="+producto.getCuentaGasto());
			dynaValidatorActionForm.set("cuentaGasto",String.valueOf(producto.getCuentaGasto()));
			listaRangos=producto.getRango();
			request.getSession(false).setAttribute("producto",producto);
			target="listaRangosConvenio";
		}

		ServicesOperacionesDelegate delegate = new ServicesOperacionesDelegate();
		ArrayList cuentas=delegate.getCuentasGasto();

		request.getSession(false).setAttribute("lista.rangos",listaRangos);
		request.getSession(false).setAttribute("rangos.opciones",opciones);
		request.getSession(false).setAttribute("rangos.opciones.valores",opcionesValores);
		request.getSession(false).setAttribute("rangos.cuenta.gasto",cuentas);

		// Write logic determining how the user should be forwarded.

		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.saveToken(request);
		return (forward);

	}
}
