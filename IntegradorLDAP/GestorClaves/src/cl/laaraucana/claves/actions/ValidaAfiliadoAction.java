package cl.laaraucana.claves.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.claves.business.UtilInfoAfiliado;
import cl.laaraucana.claves.clientesws.ClienteSinacofi;
import cl.laaraucana.claves.clientesws.model.ConstantesRespuestasWS;
import cl.laaraucana.claves.clientesws.vo.SalidaSinacofiVO;
import cl.laaraucana.claves.clientesws.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.claves.dao.ConsultaServicesDAO;
import cl.laaraucana.claves.dao.vo.RegistroVO;


/**
 * @version 1.0
 * @author
 */
public class ValidaAfiliadoAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
		try {	
			String rut = request.getParameter("rut");
			if(rut==null){
				forward = mapping.findForward("init");
				return forward;
			}
			rut= rut.toUpperCase();
			rut= rut.replaceAll("\\.", "");
			rut= Integer.parseInt(rut.split("-")[0]) + "-" + rut.split("-")[1];
			request.getSession().setAttribute("rut", rut);
			
			String serie = request.getParameter("serie");
			if(serie!=null){
				serie= serie.trim();
			}
			logger.info("-->A validar Afiliación Rut=" + rut);
			//Consulta afiliado CRM-SAP
			SalidainfoAfiliadoVO salida= UtilInfoAfiliado.consultaAfiliado(rut);
			if(salida!=null && salida.getCodigoError()==ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS ){
				logger.info("Respuesta exitosa InfoAfilado");
				request.getSession().setAttribute("nombre", salida.getNombreCompleto());
				if(salida.getNombreCompleto()==null){
					logger.info("RUT No es Afiliado");
					forward = mapping.findForward("noafiliado");
					request.setAttribute("errorMsg", "rut_error");
				}else{
					
					logger.info("Consultando Sinacofi, Rut:" + rut + ", serie=" + serie);
					
					ClienteSinacofi cli = new ClienteSinacofi();
					SalidaSinacofiVO respSina = (SalidaSinacofiVO) cli.call(rut, serie);
					
					if (respSina == null || respSina.getCodigoError() != ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS || respSina.getCodigoRetorno().equals("00000")) {
						if(respSina!=null){
							logger.info("Respuesta Sinacofi, codigo retorno= " + respSina.getCodigoRetorno() + ", Cedula Vigente=" + respSina.getCedulaVigente() + ", mensaje: " + respSina.getMensaje());
						}
						request.setAttribute("errorMsg", "servicio_error");
						forward = mapping.findForward("noafiliado");
					}else if (!respSina.getCodigoRetorno().equals("10000") || respSina.getCedulaVigente().trim().equals("NO")) {
							logger.info("mensaje: " + respSina.getMensaje());
							request.setAttribute("errorMsg", "serie_error");
							forward = mapping.findForward("noafiliado");
					}else{
						//Se valida que usuario no esté ya registrado
						//Consulto datos de afiliado en Bitacora
						ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
						int rutint= Integer.parseInt(rut.split("-")[0]);
						RegistroVO registro= consultaDAO.consultaRegistro(rutint);
						if(registro!=null){
							logger.info("RUT ya registrado");
							forward = mapping.findForward("yaregistrado");
						}else{
							forward = mapping.findForward("success");
						}
					}
				}
				
			}else{
				//Si servicio CRM no responde se notifica error
				logger.info("Servicio CRM Error");
				forward = mapping.findForward("error");
				request.setAttribute("errorMsg", "error validación afiliado");
			}
		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en Valida RUT: " + e.getMessage());
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error mensaje:" + e.getMessage());
			request.setAttribute("error", "1");
			forward = mapping.findForward("error");
		}
		
		return (forward);

	}
	  
}
