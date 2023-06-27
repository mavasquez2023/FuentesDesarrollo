package cl.araucana.bienestar.bonificaciones.ui.actions.operacion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.model.Cobertura;
import cl.araucana.bienestar.bonificaciones.model.Convenio;
import cl.araucana.bienestar.bonificaciones.model.Producto;
import cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosDelegate;

/**
 * @version 	1.0
 * @author
 */
public class PrepareLookUpUpLoadFileAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {


		Logger logger = Logger.getLogger(PrepareLookUpUpLoadFileAction.class);

		String target=null;
		String destino=request.getParameter("destino");
		String codigoConvenio=request.getParameter("codigoConvenio");
		String nombreConvenio=request.getParameter("nombreConvenio");
		String codigoProducto=request.getParameter("codigoProducto");
		String nombreProducto=request.getParameter("nombreProducto");
		
		long codConvenio = 0;
		long codProducto = 0;
		if(codigoConvenio!=null) {
			codConvenio=Long.parseLong(codigoConvenio);
		}
		if(nombreConvenio==null) nombreConvenio="";
		
		if(codigoProducto!=null) {
			codProducto=Long.parseLong(codigoProducto);
		}
		if(nombreProducto==null) nombreProducto="";
		
		
		if(destino.equals("convenios")){
			ServicesConveniosDelegate delegateConvenio = new ServicesConveniosDelegate();
			Convenio convenio = new Convenio();
			convenio.setEstado(Convenio.STD_ACTIVO);
			if(codConvenio>0)
				convenio.setCodigo(codConvenio);
			if(!nombreConvenio.equals(""))
				convenio.setNombre(nombreConvenio);
			// Guardar en memoria el resultado
			request.getSession(false).setAttribute("lista.convenios",delegateConvenio.getConvenios(convenio));

			target="convenios";
		}
		else if(destino.equals("productos")){
			Producto productoFiltro = new Producto();
			Cobertura coberturaFiltro = new Cobertura();

			ServicesConveniosDelegate delegateConvenio = new ServicesConveniosDelegate();
			Convenio convenio = delegateConvenio.getConvenio(codConvenio);
		
			if(codProducto>0)
				coberturaFiltro.setCodigo(codProducto);
			if(!nombreProducto.equals(""))
				coberturaFiltro.setDescripcion(nombreProducto);
			productoFiltro.setCobertura(coberturaFiltro);
			logger.debug("convenio: "+codConvenio);
			
			convenio = delegateConvenio.getProductos(convenio,productoFiltro);
			
			// Guardar en memoria el resultado
			request.getSession(false).setAttribute("convenio",convenio);
			
			target="productos";
		}


		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);
	}
	
}
