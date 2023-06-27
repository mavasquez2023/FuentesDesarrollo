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

import cl.araucana.bienestar.bonificaciones.model.Cobertura;
import cl.araucana.bienestar.bonificaciones.model.Convenio;
import cl.araucana.bienestar.bonificaciones.model.Producto;
import cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosDelegate;

/**
 * @version 	1.0
 * @author
 */
public class SetFichaProductoAction extends Action {

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
		String codigoProducto=request.getParameter("producto");
		ArrayList opciones=new ArrayList();
		ArrayList opcionesValores=new ArrayList();
		Convenio convenio=(Convenio)request.getSession(false).getAttribute("convenio");
		ArrayList listaCoberturas=(ArrayList)request.getSession(false).getAttribute("lista.coberturas");
		String indiceCobertura=(String)request.getParameter("cobertura");
		
		Producto producto=null;
		
		String lookup=null;
		String botonera=null;
		
		if(codigo==null) codigo="";
		
		if(codigoProducto==null) codigoProducto="";
		
		if(!codigoProducto.equals("")){
			ServicesConveniosDelegate delegate=new ServicesConveniosDelegate();
			producto=delegate.getProducto((long)Long.parseLong(codigoProducto),convenio.getCodigo());
			
			if (userinformation.hasAccess("convProActualiza")) {
				opciones.add("Modificar Producto");
				opcionesValores.add("2");
			}
			if (userinformation.hasAccess("convProElimina")) {
				opciones.add("Eliminar Producto");
				opcionesValores.add("3");
			}
			botonera="si";
		}
		else if(codigo.equals("")){
			producto=new Producto();
			producto.setFechaIngreso(new Date());
			if(indiceCobertura!=null){
				producto.setCobertura((Cobertura)listaCoberturas.get((int)Integer.parseInt(indiceCobertura)));
			}
			else producto.setCobertura(new Cobertura());
			if (userinformation.hasAccess("convProCrea")) {
				opciones.add("Agregar Producto");
				opcionesValores.add("1");
			}
			lookup="si";
		}
		else{
			producto=convenio.getProducto((int)Integer.parseInt(codigo));
			if (userinformation.hasAccess("convProActualiza")) {
				opciones.add("Modificar Producto");
				opcionesValores.add("2");
			}
			if (userinformation.hasAccess("convProElimina")) {
				opciones.add("Eliminar Producto");
				opcionesValores.add("3");
			}
			botonera="si";
		}
		dynaValidatorActionForm.set("descuento",String.valueOf((int)producto.getDescuento()));
		dynaValidatorActionForm.set("porcentajeAporteConvenio",String.valueOf((int)producto.getPorcentajeAporteConvenio()));

		request.getSession(false).setAttribute("producto",producto);		
		request.getSession(false).setAttribute("producto.botonera",botonera);		
		request.getSession(false).setAttribute("producto.lookup",lookup);		
		request.getSession(false).setAttribute("producto.opciones",opciones);		
		request.getSession(false).setAttribute("producto.opciones.valores",opcionesValores);		
		request.getSession(false).setAttribute("lista.rangos",null);		
		
		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward("producto");
		this.saveToken(request);
		return (forward);

	}
}
