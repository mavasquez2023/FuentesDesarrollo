package cl.araucana.infdeupre.main.actions;

import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.openjpa.lib.log.Log;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.infdeupre.dao.VO.ParamVO;
import cl.araucana.infdeupre.main.dao.ConsultaDeudaDAO;
import cl.araucana.infdeupre.main.forms.ConsultaDeudaForm;
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
			/*ConsultaDeudaForm formParam = (ConsultaDeudaForm) form;

			String rut = formParam.getRut();*/

			HttpSession sesion = request.getSession();
			//sesion.invalidate();

			String rutEmpresa = request.getParameter("rutEmpresa");
			rutEmpresa= rutEmpresa.replaceAll("\\.", "");
			logger.info("Consulta Rut Empresa=" + rutEmpresa);
			int pos= rutEmpresa.indexOf("-");
			int rutemp=0;
			if(pos>-1){
				rutemp=Integer.parseInt(rutEmpresa.substring(0, pos));
			}else{
				rutemp= Integer.parseInt(rutEmpresa);
			}	

			try {
				//se instancia e invoca DAO para obtener deuda empresa
				ConsultaDeudaDAO deudaDAO= new ConsultaDeudaDAO();
				List<ParamVO> oficinasEempresa= deudaDAO.consultaOficinasDeudaEmpresa(rutemp);
				logger.info("Cantidad Oficinas a desplegar: " + oficinasEempresa.size());
				if(oficinasEempresa.size()>0){
					int contador=0;
					for (Iterator iterator = oficinasEempresa.iterator(); iterator.hasNext();) {
						ParamVO paramVO = (ParamVO) iterator.next();
						paramVO.setId(contador);
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
				RequestDispatcher rq = request.getRequestDispatcher("/main/error.do");
				request.setAttribute("title", "Error General");
				request.setAttribute("errorMsg", e.getMessage());
				request.setAttribute("error", "1");
				rq.forward(request, response);					
			}



			forward = mapping.findForward("success");
		} catch (Exception e) {
			forward = Utils.returnErrorForward(mapping, e, this.getClass());
		}
		return (forward);

	}
}
