package cl.laaraucana.reportesil.actions;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.reportesil.dao.ConsultaServicesDAO;
import cl.laaraucana.reportesil.dao.vo.RentasVO;
import cl.laaraucana.reportesil.dao.vo.ResumenLicenciaVO;



/**
 * @version 1.0
 * @author
 */
public class GuardarLicenciaAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
		try {	
			//paràmetros renta adicionales
			String[] periodos = request.getParameterValues("mes");
			String[] motivos = request.getParameterValues("motivo");
			String[] montos = request.getParameterValues("monto");
			
			//parametros rentas adicionales maternal
			String[] periodos_maternal = request.getParameterValues("mes_maternal");
			String[] motivos_maternal = request.getParameterValues("motivo_maternal");
			String[] montos_maternal = request.getParameterValues("monto_maternal");
			
			ResumenLicenciaVO resumen= (ResumenLicenciaVO)request.getSession().getAttribute("licencia");
			List<RentasVO> lista= new ArrayList<RentasVO>();
			int num_otrasrentas=0;
			if(periodos!=null){
				num_otrasrentas= periodos.length;	
			}

			for (int i = 0; i < num_otrasrentas; i++) {
				if(!periodos[i].equals("")){
					RentasVO rentasVO= new RentasVO();
					rentasVO.setPeriodo(periodos[i]);
					rentasVO.setMotivo(motivos[i].trim());
					rentasVO.setMonto(montos[i].replaceAll("\\.", ""));
					rentasVO.setMaternal(0);
					lista.add(rentasVO);
				}
			}
			//Rentas maternales
			num_otrasrentas=0;
			
			if(periodos_maternal!=null){
				num_otrasrentas= periodos_maternal.length;	
			}
			for (int i = 0; i < num_otrasrentas; i++) {
				if(!periodos_maternal[i].equals("")){
					RentasVO rentasVO= new RentasVO();
					rentasVO.setPeriodo(periodos_maternal[i]);
					rentasVO.setMotivo(motivos_maternal[i].trim());
					rentasVO.setMonto(montos_maternal[i].replaceAll("\\.", ""));
					rentasVO.setMaternal(1);
					lista.add(rentasVO);
				}
			}
			resumen.setRentas(lista);
			
			//Se guarda datos contacto y rentas afiliado
			ConsultaServicesDAO dao= new ConsultaServicesDAO();
			logger.info("Se guarda datos contacto y otras rentas");
			dao.insertResumen(resumen);
			
			request.setAttribute("licencia", resumen);
			forward = mapping.findForward("success");
			
		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en resumen: " + e.getMessage());
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error mensaje:" + e.getMessage());
			request.setAttribute("error", "1");
			forward = mapping.findForward("error");
		}
		
		return (forward);

	}
	
	    
}
