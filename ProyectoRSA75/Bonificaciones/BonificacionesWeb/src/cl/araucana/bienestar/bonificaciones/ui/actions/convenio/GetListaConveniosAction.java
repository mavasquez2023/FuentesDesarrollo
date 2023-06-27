package cl.araucana.bienestar.bonificaciones.ui.actions.convenio;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.common.Constants;
import cl.araucana.bienestar.bonificaciones.model.Convenio;
import cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosDelegate;
import cl.araucana.bienestar.bonificaciones.serv.ServicesOperacionesDelegate;

/**
 * @version 	1.0
 * @author
 */
public class GetListaConveniosAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		// Objeto de Permisos de la Aplicación
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		DynaValidatorActionForm dynaValidatorActionForm =
				(DynaValidatorActionForm) form;
			
		String concepto=(String)dynaValidatorActionForm.get("concepto");
		String cobertura=request.getParameter("cobertura");
		String source=request.getParameter("source");

		String target="listaConvenios";
										
		Convenio convenio = new Convenio(); 
		if(((String)dynaValidatorActionForm.get("codigo")).trim().equals("")){
			convenio.setCodigo(0);
		}
		else{
			convenio.setCodigo((long)Long.parseLong((String)dynaValidatorActionForm.get("codigo")));
		}
		convenio.setNombre((String)dynaValidatorActionForm.get("nombre"));
		convenio.setEstado((String)dynaValidatorActionForm.get("estado"));
		convenio.setRut((String)dynaValidatorActionForm.get("rut"));

		if(source==null) source="";
		if(source.equals("caso")){
			target="listaConveniosCasos";
		}
		else if(concepto.equals("")){
			
			if(((String)dynaValidatorActionForm.get("codigoConcepto")).equals("")){
				convenio.setCodigoConcepto(0);
			}
			else{
				convenio.setCodigoConcepto((long)Long.parseLong((String)dynaValidatorActionForm.get("codigoConcepto")));
			}
		}
		else{
			convenio.setCodigoConcepto((long)Long.parseLong(concepto));
			target="listaConveniosConcepto";
		}

		ServicesConveniosDelegate delegateConvenio = new ServicesConveniosDelegate();
		ServicesOperacionesDelegate delegateOperaciones = new ServicesOperacionesDelegate();
		ArrayList listaConvenios=null;
		if(cobertura==null) cobertura="";
		
		/**
		 * INICIO NUEVO
		 */
		HttpSession sesion = request.getSession();
		
		Long grupoUsuario = (Long) sesion.getAttribute("grupoUsuario");
		if(grupoUsuario != null && grupoUsuario.longValue() == Constants.TIPO_USUARIO_SOCIO){//si es socio
			listaConvenios=delegateConvenio.getConveniosRe(convenio);
		}else{//si es admin (dejamos por defecto mostrar todos los convenios si grupoUsuario == null)
			if(cobertura.equals(""))
			{
				
				listaConvenios = delegateConvenio.getConvenios(convenio);
			} else
			{
				listaConvenios = delegateConvenio.getConvenios(Long.parseLong(cobertura));
				target = "listaConveniosCobertura";
				dynaValidatorActionForm.set("cobertura", cobertura);
			}			
		}		
		/**
		 * FIN NUEVO
		 */
		ArrayList opciones=new ArrayList();
		ArrayList opcionesValores=new ArrayList();
		if (userinformation.hasAccess("convCrea")) {
			opciones.add("Agregar Convenio");
			opcionesValores.add("1");
		}
		
		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("opciones",opciones);
		request.getSession(false).setAttribute("opciones.valores",opcionesValores);

		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("lista.convenios",listaConvenios);

		// Cambio Concepto: Solo un tipo
		//ArrayList listaConceptos=delegateOperaciones.getConceptos(Concepto.TIPO_CONVENIO);
 
		ArrayList listaConceptos=delegateOperaciones.getConceptos();
		request.getSession(false).setAttribute("lista.opciones.conceptos",listaConceptos);
		// Write logic determining how the user should be forwarded.

		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);

	}
}
