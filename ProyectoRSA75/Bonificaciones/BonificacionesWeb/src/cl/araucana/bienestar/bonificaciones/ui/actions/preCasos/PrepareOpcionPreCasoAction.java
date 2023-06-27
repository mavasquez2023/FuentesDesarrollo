package cl.araucana.bienestar.bonificaciones.ui.actions.preCasos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.common.BusinessException;

/**
 * @version 	1.0
 * @author		Alejandro Sepúlveda
 */
public class PrepareOpcionPreCasoAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		// Objeto de Permisos de la Aplicación
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();
		
		Logger logger = Logger.getLogger(PrepareOpcionPreCasoAction.class);

		DynaValidatorActionForm dynaValidatorActionForm =
				(DynaValidatorActionForm) form;

		String target=null;
		
		String [] opcion=request.getParameterValues("indices3");
		
		String accion = (String)dynaValidatorActionForm.get("accion");
		logger.debug("Accion: "+accion);
		
		request.getSession(false).removeAttribute("vieneDesdeOpciones");
		
		if(opcion==null || opcion.length==0)
			throw new BusinessException("CCAF_BONIF_PROCESOINVALIDO",
					   "Debe seleccionar un Pre-Caso previamente");
		
		if(accion.equals("1")) //Generar Ingreso Aporte Isapre
			target="ingresoIsapre";
			
		if(accion.equals("2")) //Generar Otros Ingresos
			target="ingresoOtros";			

		if(accion.equals("3")){ //Generar Egreso
			request.getSession(false).setAttribute("vieneDesdeOpciones","true");
			target="egreso";
		}
			
		if(accion.equals("4")) //Terminar Pre-Caso
			target="activar";

		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.saveToken(request);
		return (forward);

	}
}
