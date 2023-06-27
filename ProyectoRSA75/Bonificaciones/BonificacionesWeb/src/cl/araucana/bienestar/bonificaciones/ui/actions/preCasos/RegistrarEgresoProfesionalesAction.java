package cl.araucana.bienestar.bonificaciones.ui.actions.preCasos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.common.BusinessException;

/**
 * @version 	1.0
 * @author		Alejandro Sepúlveda
 */
public class RegistrarEgresoProfesionalesAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		
//		Logger logger = Logger.getLogger(RegistrarEgresoProfesionalesAction.class);		
//		ServicesCasosDelegate casosDelegate = new ServicesCasosDelegate();
//		DynaValidatorActionForm dynaValidatorActionForm =
//				(DynaValidatorActionForm) form;
		String target=null;
//		
//		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
//		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();
//
//		UsuarioVO user=new UsuarioVO();
//		user.setNombre(userinformation.getNombres());
//		user.setApellidoMaterno(userinformation.getApellidoMaterno());
//		user.setApellidoPaterno(userinformation.getApellidoPaterno());
//		user.setCodigoOficina(userinformation.getCodOficina());
//		user.setUsuario(userinformation.getUsuario());
//		
		
//		ArrayList listaMensajes = new ArrayList();
//		request.getSession(false).removeAttribute("validation.message");		
//		
//		logger.debug("Inicio Registrar Egreso Profesionales");
//		
//		String pagarA = (String)dynaValidatorActionForm.get("pagarA");
//		//String formaPago = (String)dynaValidatorActionForm.get("formaPago");
//		
//		DatosMovimientoTesoreriaVO datosMovimientoTesoreriaVO = (DatosMovimientoTesoreriaVO)request.getSession(false).getAttribute("datosMovimientoTesoreriaVO");
//		//datosMovimientoTesoreriaVO.setTipoPago(formaPago);
//		
//		logger.debug("Monto Total: "+datosMovimientoTesoreriaVO.getMonto());
//		logger.debug("Monto Total prof.: "+datosMovimientoTesoreriaVO.getMontoTotalProfesionales());
//		logger.debug("Son : "+datosMovimientoTesoreriaVO.getListaProfesionales().size() + " Profs.");
//		
//		boolean existenErrores=false;
//		if(datosMovimientoTesoreriaVO.getListaProfesionales().size()==0){
//			listaMensajes.add("Debe indicar los datos de al menos un profesional para generar el egreso");
//			existenErrores=true;
//		}
//		
//		if(datosMovimientoTesoreriaVO.getMonto()!=datosMovimientoTesoreriaVO.getMontoTotalProfesionales()){
//			listaMensajes.add("La suma de los montos parciales debe corresponder al monto total a egresar");
//			existenErrores=true;				
//		}
//		
//		if(existenErrores){
//			target="datosProfesionales";
//		}else{

//			logger.debug("egreso para profesionales");
//			casosDelegate.registrarEgresoProfesionalesTesoreriaPreCaso(datosMovimientoTesoreriaVO, user);

			//Este action no se está usando
			if(true)
				throw new BusinessException("CCAF_BONIF_PROCESOINVALIDO","Action sin codigo");	
					
			target="success";	

			String referer="/prepareListaPreCasos.do";
			request.getSession(false).setAttribute("referer",referer);			
//		}
//		
//		logger.debug("listaMensajes: "+listaMensajes.size());
//		if(listaMensajes.size()>0)
//			request.getSession(false).setAttribute("validation.message", listaMensajes);		

		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.saveToken(request);
		return (forward);

	}
}
