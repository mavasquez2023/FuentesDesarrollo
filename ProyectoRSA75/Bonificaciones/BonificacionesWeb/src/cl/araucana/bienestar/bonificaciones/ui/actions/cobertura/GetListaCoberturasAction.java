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

import cl.araucana.bienestar.bonificaciones.model.Caso;
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
public class GetListaCoberturasAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		
		// Objeto de Permisos de la Aplicación
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		Logger logger=Logger.getLogger(this.getClass());

		DynaValidatorActionForm dynaValidatorActionForm =
				(DynaValidatorActionForm) form;
			
		String source=request.getParameter("source");							
		Convenio convenio=(Convenio)request.getSession(false).getAttribute("convenio");

/*		String concepto=request.getParameter("concepto");
		if(concepto==null) concepto="";
*/

		String concepto=(String)dynaValidatorActionForm.get("concepto");
		String target="listaCoberturas";

		Cobertura cobertura = new Cobertura();
		if(((String)dynaValidatorActionForm.get("codigo")).trim().equals("")){
			cobertura.setCodigo(0);
		}
		else{
			cobertura.setCodigo((long)Long.parseLong((String)dynaValidatorActionForm.get("codigo")));
		}
		cobertura.setDescripcion((String)dynaValidatorActionForm.get("descripcion"));
		cobertura.setEstado((String)dynaValidatorActionForm.get("estado"));
		if(concepto.equals("")){
			if(((String)dynaValidatorActionForm.get("codigoConcepto")).equals("")){
				cobertura.setConceptoCodigo(0);
			}
			else{
				cobertura.setConceptoCodigo((long)Long.parseLong((String)dynaValidatorActionForm.get("codigoConcepto")));
			}
		}
		else{
			cobertura.setConceptoCodigo((long)Long.parseLong(concepto));
			target="listaCoberturasConcepto";
		}

		ServicesCoberturasDelegate delegateCoberturas = new ServicesCoberturasDelegate();
		ServicesConveniosDelegate delegateConvenios = new ServicesConveniosDelegate();
		ServicesOperacionesDelegate delegateOperaciones = new ServicesOperacionesDelegate();
		ArrayList listaCoberturas=null;
		ArrayList opciones=new ArrayList();
		ArrayList opcionesValores=new ArrayList();

		if(source==null) source="";
		if(source.equals("convenio")){
			Producto producto=new Producto();
			producto.setCobertura(cobertura);
			convenio=delegateConvenios.getProductos(convenio,producto);
			listaCoberturas=convenio.getProductos();
			logger.debug("lista.size:"+listaCoberturas.size());
			request.getSession(false).setAttribute("convenio",convenio);
			target="listaProductos";
			
			if (userinformation.hasAccess("convProCrea")) {
				opciones.add("Agregar Producto");
				opcionesValores.add("1");
			}
		}
		else if(source.equals("lookup")){
			listaCoberturas=delegateCoberturas.getComplementoProductos(convenio.getCodigo());
			target="lookUpCoberturas";
		}
		else if(source.equals("caso")){
			Caso caso=(Caso)request.getSession(false).getAttribute("caso");
			Producto producto=new Producto();
			producto.setCobertura(cobertura);
			convenio=new Convenio();
			convenio.setCodigo(caso.getCodigoConvenio());			
			convenio=delegateConvenios.getProductosCaso(caso.getCasoID(),convenio,producto);
			listaCoberturas=convenio.getProductos();
			target="listaProductosCaso";
		}
		else{
			listaCoberturas=delegateCoberturas.getCoberturas(cobertura);
			logger.debug("listaCoberturas.size():"+listaCoberturas.size());
			if (userinformation.hasAccess("cobAgrega")) {
				opciones.add("Agregar Cobertura");
				opcionesValores.add("1");
			}
		}
		// Cambio Concepto: Solo un tipo
	 	//ArrayList listaConceptos=delegateOperaciones.getConceptos(Concepto.TIPO_COBERTURA);
		ArrayList listaConceptos=delegateOperaciones.getConceptos();

		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("opciones",opciones);
		request.getSession(false).setAttribute("opciones.valores",opcionesValores);
		request.getSession(false).setAttribute("lista.coberturas",listaCoberturas);
		request.getSession(false).setAttribute("lista.opciones.conceptos",listaConceptos);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);

	}
}
