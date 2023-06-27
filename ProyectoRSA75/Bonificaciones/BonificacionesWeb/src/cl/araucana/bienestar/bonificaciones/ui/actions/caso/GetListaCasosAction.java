package cl.araucana.bienestar.bonificaciones.ui.actions.caso;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.model.Caso;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;

/**
 * @version 	1.0
 * @author		Alejandro Sepúlveda
 */
public class GetListaCasosAction extends Action {

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
	
		Caso caso = new Caso();
		caso.setRutSocio((String)dynaValidatorActionForm.get("rutSocio"));
		if(((String)dynaValidatorActionForm.get("casoID")).trim().equals("")){
			caso.setCasoID(0);
		}
		else{
			caso.setCasoID((long)Long.parseLong((String)dynaValidatorActionForm.get("casoID")));
		}
		String est=(String)dynaValidatorActionForm.get("estado");
		if(est==null){
			caso.setEstado(Caso.STD_ACTIVO);
		}
		else{
			caso.setEstado(est);
		}
		caso.setTipoCaso((String)dynaValidatorActionForm.get("tipoCaso"));
		if(((String)dynaValidatorActionForm.get("monto")).trim().equals("")){
			caso.setMonto(0);
		}
		else{
			caso.setMonto((double)Double.parseDouble((String)dynaValidatorActionForm.get("monto")));
		}
		caso.setTipoBono("");
		
		ServicesCasosDelegate delegate = new ServicesCasosDelegate();
	
		Logger logger = Logger.getLogger(GetListaCasosAction.class);
	
		logger.debug("caso.getRutSocio():"+caso.getRutSocio());
		logger.debug("caso.getCasoID():"+caso.getCasoID());
		logger.debug("caso.getEstado():"+caso.getEstado());
		logger.debug("caso.getTipoCaso():"+caso.getTipoCaso());
		logger.debug("caso.getMonto():"+caso.getMonto());
		
		ArrayList listaCasos=new ArrayList();
		request.getSession(false).removeAttribute("ejecutoConsulta");
		
		/**
		 * INICIO NUEVO
		 */
		HttpSession sesion = request.getSession();
//		Long grupoUsuario = (Long) sesion.getAttribute("grupoUsuario");
		
//		removemos el objeto Caso asignado a la sesion en setListaCasosAction
		sesion.removeAttribute("caso");		
		/**
		 * FIN NUEVO
		 */
		if(!caso.getRutSocio().equals("") || caso.getCasoID()!=0 || !caso.getEstado().equals("") || !caso.getTipoCaso().equals("") && caso.getMonto()!=0) {
			if(caso.getEstado().equals("*"))
				caso.setEstado("");
			if(caso.getTipoCaso().equals("*"))
				caso.setTipoCaso("");
						
//			//Ejecuta la consulta siempre que el usuario no sea socio
//			/**
//			 * INICIO NUEVO
//			 */
//			if(grupoUsuario!=null && grupoUsuario.longValue() != Constants.TIPO_USUARIO_SOCIO){		
//				listaCasos=delegate.getListaCasos(caso);
//				request.getSession(false).setAttribute("ejecutoConsulta","yes");
//			}
			//Lo anterior se reemplaza por lo siguiente
			if (userinformation.hasAccess("casVerInfoEspecial")) {
				listaCasos=delegate.getListaCasos(caso);
				request.getSession(false).setAttribute("ejecutoConsulta","yes");
			}
			
		}
		
		/**
		 * INICIO NUEVO
		 */
		//se traera de la sesion el tipo de usuario, y luego si es socio se buscaran todos los de tipo borrador 
		//y que pertenescan a el, para mostrarse en el primer despliegue.
		
//		if(grupoUsuario!=null && grupoUsuario.longValue() == Constants.TIPO_USUARIO_SOCIO){
		//Lo anterior se reemplaza por lo siguiente
		if ( !userinformation.hasAccess("casVerInfoEspecial")) {	
			
			if(userinformation.getRut() != null){
			 
				String rutAux = userinformation.getRut().substring(0,userinformation.getRut().length()-2);
				//METODO antiguo que no trae un CasoVO,por lo que falla
				//listaCasos=delegate.listaCasosBorradorPorRut(rutAux);
				Caso casoAux = new Caso();
				
				casoAux.setRutSocio(rutAux);
				casoAux.setEstado(Caso.STD_BORRADOR);
				listaCasos=delegate.getListaCasos(casoAux);
				casoAux = null;
				//DESCOMENTAR PARA MOSTRAR COMENTARIO
				//request.getSession(false).setAttribute("ejecutoConsulta","yes");
			}	
		}
		
		/**
		 * FIN NUEVO
		 */
		logger.debug("lista.size:"+listaCasos.size());
		ArrayList opciones=new ArrayList();
		ArrayList opcionesValores=new ArrayList();
		if (userinformation.hasAccess("casCrea")) {
			opciones.add("Agregar Caso");
			opcionesValores.add("1");
		}
		
		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("opciones",opciones);
		request.getSession(false).setAttribute("opciones.valores",opcionesValores);
		request.getSession(false).setAttribute("lista.casos",listaCasos);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward("listaCasos");
		return (forward);


	}
}
