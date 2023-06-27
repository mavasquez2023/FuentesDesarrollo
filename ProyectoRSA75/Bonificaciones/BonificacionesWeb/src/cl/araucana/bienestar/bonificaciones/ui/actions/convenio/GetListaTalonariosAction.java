package cl.araucana.bienestar.bonificaciones.ui.actions.convenio;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.model.Convenio;
import cl.araucana.bienestar.bonificaciones.model.Talonario;
import cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosDelegate;

/**
 * @version 	1.0
 * @author
 */
public class GetListaTalonariosAction extends Action {

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
		
		Convenio convenio=(Convenio)request.getSession(false).getAttribute("convenio");							
		Talonario talonario = new Talonario();
		if(((String)dynaValidatorActionForm.get("inicioSecuencia")).trim().equals("")){
			talonario.setInicioSecuencia(0);
		}
		else{
			talonario.setInicioSecuencia((long)Long.parseLong((String)dynaValidatorActionForm.get("inicioSecuencia")));
		}
		if(((String)dynaValidatorActionForm.get("finSecuencia")).trim().equals("")){
			talonario.setFinSecuencia(0);
		}
		else{
			talonario.setFinSecuencia((long)Long.parseLong((String)dynaValidatorActionForm.get("finSecuencia")));
		}
		Logger logger = Logger.getLogger(GetListaTalonariosAction.class);

		logger.debug("convenio.getCodigo():"+convenio.getCodigo());
		logger.debug("talonario.getCodigoTalonario():"+talonario.getCodigoTalonario());
		logger.debug("talonario.getEstado():"+talonario.getEstado());
		logger.debug("talonario.getInicioSecuencia():"+talonario.getInicioSecuencia());
		logger.debug("talonario.getFinSecuencia():"+talonario.getFinSecuencia());
		logger.debug("talonario.getFechaIngreso():"+talonario.getFechaIngreso());

		ServicesConveniosDelegate delegate = new ServicesConveniosDelegate();
		logger.debug("Servicio iniciado");
		convenio=delegate.getTalonarios(convenio,talonario);
		logger.debug("Convenio obtenido");
		ArrayList listaTalonarios=convenio.getTalonarios();
		logger.debug("lista.size:"+listaTalonarios.size());
		
		ArrayList opciones=new ArrayList();
		ArrayList opcionesValores=new ArrayList();
		if (userinformation.hasAccess("convTalCrea")) {
			opciones.add("Agregar Talonario");
			opcionesValores.add("1");
		}

		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("opciones",opciones);
		request.getSession(false).setAttribute("opciones.valores",opcionesValores);
		request.getSession(false).setAttribute("convenio",convenio);
		request.getSession(false).setAttribute("lista.talonarios",listaTalonarios);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward("listaTalonarios");
		return (forward);

	}
}
