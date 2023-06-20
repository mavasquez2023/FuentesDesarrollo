package cl.laaraucana.reportesil.actions;


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
public class DatosLicenciaAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
		try {
			int numrentas=3;
			String email = request.getParameter("email");
			String celular = request.getParameter("celular");
			String direccion = request.getParameter("direccion");
			String observacion = request.getParameter("observacion");
			String usuario= "USRWAS";
			
			ResumenLicenciaVO resumen= (ResumenLicenciaVO)request.getSession().getAttribute("licencia");
			
			resumen.setEmail(email.trim());
			resumen.setCelular(celular.trim());
			resumen.setDireccion(direccion.trim());
			resumen.setObservacion(observacion.trim());
			resumen.setUsuario(usuario);
			request.setAttribute("licencia", resumen);
			
			ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
			Map<String, Integer> param= new HashMap<String, Integer>();
			param.put("licencia", resumen.getNuminterno());
			param.put("rutAfiliado", resumen.getRutAfiliado());
			param.put("fechaHasta", resumen.getFechaHastaInt());
			//Se revisa si licencia es reliquidada
			if(resumen.getLiquidada()==1){
				//Se busca si licencia es reliquidada
				resumen.setReliquidada(consultaDAO.consultaReliquidada(param));
			}else{
				resumen.setReliquidada("NO");
			}
			
			logger.info("Licencia reliquidada:" + resumen.getReliquidada());
			
			//Se revisa si origen de rentas proviene de cotizaciones
			String origen_rentas= "Liquidaciones";
			int countcot= consultaDAO.isRentaCotizaciones(param);
			if(countcot>0){
				origen_rentas= "Cotizaciones";
			}
			request.setAttribute("origen_rentas", origen_rentas);
			logger.info("Origen rentas: " + origen_rentas);
			
			if(resumen.getEstado().equals("AUTORIZADA")){
				//Se busca si ya existe información rentas de afiliado
				
				param.put("tipoLicencia", new Integer(0));
				
				List<RentasVO> rentas= consultaDAO.consultaRentas(param);
				int iteraciones= numrentas- rentas.size();
				for (int i = 0; i < iteraciones; i++) {
					rentas.add(new RentasVO());
				}
				request.setAttribute("rentas", rentas);
				
				//Si es maternal se busca rentas maternales
				if(resumen.getTipoLicencia()== 1){
					param.put("tipoLicencia", new Integer(1));
					List<RentasVO> rentas_mat= consultaDAO.consultaRentas(param);
					iteraciones= numrentas- rentas_mat.size();
					for (int i = 0; i < iteraciones; i++) {
						rentas_mat.add(new RentasVO());
					}
					request.setAttribute("rentas_maternales", rentas_mat);
				}

				forward = mapping.findForward("success");
			}else{
				forward = mapping.findForward("guardar");
			}
			
			
			
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
