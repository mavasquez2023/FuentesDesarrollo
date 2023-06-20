package cl.laaraucana.reportesil.actions;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.reportesil.clientesws.model.ConstantesRespuestasWS;
import cl.laaraucana.reportesil.clientesws.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.reportesil.dao.ConsultaServicesDAO;
import cl.laaraucana.reportesil.dao.vo.ResumenLicenciaVO;
import cl.laaraucana.reportesil.services.UtilInfoAfiliado;
import cl.laaraucana.reportesil.utils.Utils;


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
			request.getSession().setAttribute("rut", rut);
			rut= rut.replaceAll("\\.", "");
			int rutint= Integer.parseInt(rut.split("-")[0]);
			String fecha_desde = request.getParameter("fecha_desde");
			String[] fecha_desde_split= fecha_desde.split("/");
			int fechaDesde = Integer.parseInt(fecha_desde_split[2] + fecha_desde_split[1] + fecha_desde_split[0]);
			String fecha_hasta = request.getParameter("fecha_hasta");
			if(fecha_hasta==null || fecha_hasta.equals("")){
				fecha_hasta= Utils.dateToString2(new Date());
			}
			String[] fecha_hasta_split= fecha_hasta.split("/");
			int fechaHasta = Integer.parseInt(fecha_hasta_split[2] + fecha_hasta_split[1] + fecha_hasta_split[0]);
			logger.info("A validar Rut=" + rut + "fecha desde=" + fecha_desde);
			
			//Consulta afiliado CRM-SAP
			SalidainfoAfiliadoVO salida= UtilInfoAfiliado.consultaAfiliado(rut);
			if(salida.getCodigoError()==ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS ){				
				if(salida.isDeudordirecto()){
					logger.info("RUT No es Afiliado");
					if(salida.getNombreCompleto().equals("")){
						request.getSession().setAttribute("estado", "SIN INFO");
					}else{
						request.getSession().setAttribute("estado", "CESADO");
					}
				}else{
					logger.info("RUT es Afiliado");
					request.getSession().setAttribute("estado", "AFILIADO");
				}
				
			}else{
				//Si servicio CRM no responde se notifica error
				logger.info("Servicio CRM Error");
			}
			
			//consulta nombre afiliado de la 1000 si en CRM no existe
			String nombreAfiliado="";
			if(salida!=null){
				nombreAfiliado= salida.getNombreCompleto();
			}
			ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
			if(nombreAfiliado.equals("")){
				logger.info("Se busca nombre en ilf1000");
				nombreAfiliado= consultaDAO.nombreAfiliado(rutint);
			}
			request.getSession().setAttribute("nombre", nombreAfiliado);
			
			//consulta lista de licencias
			Map<String, Integer> paramVO= new HashMap();
			paramVO.put("rutAfiliado", rutint);
			paramVO.put("fechaDesde", fechaDesde);
			paramVO.put("fechaHasta", fechaHasta);
			List<ResumenLicenciaVO> lista= consultaDAO.consultaLicencias(paramVO);
			logger.info("Número licencias= " + lista.size());
			//Se guarda mapeo de las licencias para recuperar en siguiente paso.
			if(lista.size()>0){
				Map<String, ResumenLicenciaVO> mapeolic= new HashMap<String, ResumenLicenciaVO>();
				for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
					ResumenLicenciaVO resumenLicenciaVO = (ResumenLicenciaVO) iterator.next();
					mapeolic.put(resumenLicenciaVO.getNuminterno() + resumenLicenciaVO.getFechaHastaStr(), resumenLicenciaVO);
				}
				request.getSession().setAttribute("mapLicencias", mapeolic);
			}
			request.getSession().setAttribute("listLicencias", lista);
			forward = mapping.findForward("success");
			
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
