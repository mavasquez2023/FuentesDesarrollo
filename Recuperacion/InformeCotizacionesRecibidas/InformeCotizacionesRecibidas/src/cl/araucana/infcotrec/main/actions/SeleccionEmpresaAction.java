package cl.araucana.infcotrec.main.actions;

import java.util.Iterator;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.infcotrec.dao.VO.ParamVO;
import cl.araucana.infcotrec.main.dao.ConsultaPagosDAO;
import cl.araucana.infcotrec.utils.CertificadoConst;
import cl.laaraucana.satelites.Utils.Utils;

/**
 * @version 1.0
 * @author
 */
public class SeleccionEmpresaAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
		try {
			logger.info("Petición Informe Cotizaciones Recibidas");
			/*ConsultaPagosForm formParam = (ConsultaPagosForm) form;

		String rut = formParam.getRut();*/

			HttpSession sesion = request.getSession();
			//sesion.invalidate();

			String rutEmpresa = request.getParameter("rutEmpresa");
			logger.info("Consulta Rut empresa=" + rutEmpresa);
			rutEmpresa= rutEmpresa.replaceAll("\\.", "");
			
			int pos= rutEmpresa.indexOf("-");
			int rutemp=0;
			if(pos>-1){
				rutemp=Integer.parseInt(rutEmpresa.substring(0, pos));
			}else{
				rutemp= Integer.parseInt(rutEmpresa);
			}


			//se instancia e invoca DAO para obtener deuda empresa
			ConsultaPagosDAO deudaDAO= new ConsultaPagosDAO();
			ParamVO paramConsulta= new ParamVO();
			paramConsulta.setRutEmpresa(rutemp);
			int cantMeses= Integer.parseInt(CertificadoConst.RES_CERTIFICADOS.getString("certificado.infcotrec.meses"));
			if(Utils.obtenerDiaActual()<15){
				cantMeses++;
			}
			
			Integer maxPeriodo= deudaDAO.consultaMaxPeriodoCotizacion(rutemp);
			request.getSession().setAttribute("maxperiodo", maxPeriodo);
			logger.info("Cantidad de Meses Informe= " + cantMeses);
			String periodo=Utils.obtenerPeriodoCualquiera(String.valueOf(maxPeriodo), cantMeses*-1);
			paramConsulta.setPeriodo(periodo);

			logger.info("Periodo a consultar= " + paramConsulta.getPeriodo());
			List<ParamVO> oficinasEempresa= deudaDAO.consultaOficinasPagosEmpresa(paramConsulta);
			logger.info("Cantidad Oficinas a desplegar: " + oficinasEempresa.size());
			if(oficinasEempresa.size()>0){
				int contador=0;
				for (Iterator iterator = oficinasEempresa.iterator(); iterator.hasNext();) {
					ParamVO paramVO = (ParamVO) iterator.next();
					paramVO.setId(contador);
					paramVO.setPeriodo(periodo);
					contador++;
				}
				sesion.setAttribute("empresas", oficinasEempresa);
				request.setAttribute("error", "0");
			}else{
				request.setAttribute("error", "-1");
			}
		} catch (Exception e) {
			// Rut no valido
			e.printStackTrace();
			logger.error("Error mensaje: " + e.getMessage());
			request.setAttribute("title", "Error General");
			request.setAttribute("errorMsg", e.getMessage());
			request.setAttribute("error", "1");					
		}

		forward = mapping.findForward("success");
		return (forward);

	}
}
