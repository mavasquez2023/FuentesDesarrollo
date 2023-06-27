package cl.araucana.bienestar.bonificaciones.ui.actions.preCasos;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;
import cl.araucana.bienestar.bonificaciones.vo.DatosMovimientoTesoreriaVO;
import cl.araucana.bienestar.bonificaciones.vo.DatosProfesionalesVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleMovimientoPreCasoVO;
import cl.araucana.bienestar.bonificaciones.vo.UsuarioVO;
import cl.araucana.common.BusinessException;

/**
 * @version 	1.0
 * @author		Alejandro Sepúlveda
 */
public class ValidarEgresoProfesionalesAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		
		Logger logger = Logger.getLogger(ValidarEgresoProfesionalesAction.class);		
		ServicesCasosDelegate casosDelegate = new ServicesCasosDelegate();
		DynaValidatorActionForm dynaValidatorActionForm =
				(DynaValidatorActionForm) form;
		String target=null;
		
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		UsuarioVO user=new UsuarioVO();
		user.setNombre(userinformation.getNombres());
		user.setApellidoMaterno(userinformation.getApellidoMaterno());
		user.setApellidoPaterno(userinformation.getApellidoPaterno());
		user.setCodigoOficina(userinformation.getCodOficina());
		user.setUsuario(userinformation.getUsuario());
		
		ArrayList listaMensajes = new ArrayList();
		request.getSession(false).removeAttribute("validation.message");		
		
		logger.debug("Inicio Validar Egreso Profesionales");
		
		String pagarA = (String)dynaValidatorActionForm.get("pagarA");
		int opcion = Integer.parseInt((String)dynaValidatorActionForm.get("opcion"));
		
		logger.debug("pagarA: "+pagarA);
		logger.debug("opcion: "+opcion);
		
		DatosMovimientoTesoreriaVO datosMovimientoTesoreriaVO = (DatosMovimientoTesoreriaVO)request.getSession(false).getAttribute("datosMovimientoTesoreriaVO");
		
		logger.debug("Monto Total: "+datosMovimientoTesoreriaVO.getMonto());
		logger.debug("Monto Total prof.: "+datosMovimientoTesoreriaVO.getMontoTotalProfesionales());
		logger.debug("Son : "+datosMovimientoTesoreriaVO.getListaProfesionales().size() + " Profs.");
		
		boolean existenErrores=false;
		if(datosMovimientoTesoreriaVO.getListaProfesionales().size()==0){
			listaMensajes.add("Debe indicar los datos de al menos un profesional para generar el egreso");
			existenErrores=true;
		}
		
		if(datosMovimientoTesoreriaVO.getMonto()!=datosMovimientoTesoreriaVO.getMontoTotalProfesionales()){
			listaMensajes.add("La suma de los montos de los profesionales debe corresponder al monto total a egresar");
			existenErrores=true;				
		}
		
		ArrayList listaProfesionales = datosMovimientoTesoreriaVO.getListaProfesionales();
		for(int x=0;x<listaProfesionales.size();x++){
			DatosProfesionalesVO datosProfesionalesVO =(DatosProfesionalesVO)listaProfesionales.get(x);
			ArrayList detalles = (ArrayList)datosProfesionalesVO.getDetalles();
			int totalDetalles=0;
			for(int y=0;y<detalles.size();y++){
				DetalleMovimientoPreCasoVO detalleMovimientoPreCasoVO = (DetalleMovimientoPreCasoVO)detalles.get(y);
				totalDetalles = totalDetalles + (int)detalleMovimientoPreCasoVO.getMonto();
			}
			if((int)datosProfesionalesVO.getMonto()!=totalDetalles){
				listaMensajes.add("La suma de los montos de los detalles de los profesionales debe corresponder al monto del profesional");
				existenErrores=true;
			}
		}
		
		if(existenErrores){
			target="datosProfesionales";
		}else{	
			logger.debug("Informacion Profesionales OK");
			
			switch (opcion){
				case 1:
					logger.debug("egreso para profesionales");
					casosDelegate.registrarEgresoProfesionalesTesoreriaPreCaso(datosMovimientoTesoreriaVO, user);
			
					target="success";	
	
					String referer="/prepareListaPreCasos.do";
					request.getSession(false).setAttribute("referer",referer);
					break;
				default:
					logger.debug("La opcion es incorrecta, error en programacion");
					throw new BusinessException("CCAF_BONIF_PROCESOINVALIDO","opcion no valida");
			}
			
		}
		
		logger.debug("listaMensajes: "+listaMensajes.size());
		logger.debug("target: "+target);
		if(listaMensajes.size()>0)
			request.getSession(false).setAttribute("validation.message", listaMensajes);		

		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.saveToken(request);
		return (forward);

	}
}
