package cl.laaraucana.cuotasdup.main.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.cuotasdup.dao.VO.CuotaVO;
import cl.laaraucana.cuotasdup.main.dao.ConsultaTrabajadoresDAO;
import cl.laaraucana.cuotasdup.main.forms.IntegracionCrmForm;
import cl.laaraucana.satelites.Utils.Utils;

/**
 * @version 1.0
 * @author
 */
public class ConsultaEmpresaAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward forward = new ActionForward(); // return value
		try {
			IntegracionCrmForm formCRM = (IntegracionCrmForm) form;

			String rut = formCRM.getRut();

			HttpSession sesion = request.getSession();
			sesion.invalidate();
			sesion = request.getSession();

			String rutParam = request.getParameter("rut");
			
				logger.info("Seleciona rut:" + rutParam);
				if (rutParam != null) {
					int pos= rutParam.indexOf("-");
					if(pos>-1){
						int rutemp=Integer.parseInt(rutParam.substring(0, pos));
						try {
							ConsultaTrabajadoresDAO listaTra= new ConsultaTrabajadoresDAO();
							List<CuotaVO> trabajadores= listaTra.consultaTrabajadores(rutemp);
							if(trabajadores.size()>0){
							trabajadores.add(0, new CuotaVO());
							CuotaVO registro= trabajadores.get(1);
							sesion.setAttribute("rutEmpresa", rutParam);
							sesion.setAttribute("nombreEmpresa", registro.getRazonSocial());
							sesion.setAttribute("sucursal", String.valueOf(registro.getSucursal()));
							sesion.setAttribute("oficina", String.valueOf(registro.getOficina()));
							sesion.setAttribute("nomOficina", registro.getNombreOficina());
							sesion.setAttribute("trabajadores", trabajadores);
							sesion.setAttribute("fechaEmision", Utils.getFechaCompleta());
							request.setAttribute("error", "0");
							
							//Insert Bitácora
							ConsultaTrabajadoresDAO dao= new ConsultaTrabajadoresDAO();
							Map param= new HashMap();
							param.put("accion", "CONSULTA");
							param.put("rutEmpresa", rutParam);
							param.put("oficina", registro.getOficina());
							param.put("sucursal", registro.getSucursal());
							dao.insertBitacora(param);
							
							}else{
								request.setAttribute("error", "-1");
							}
						} catch (Exception e) {
							// Rut no valido
							logger.error("Error en consulta Rut: " + rutParam);
							RequestDispatcher rq = request.getRequestDispatcher("/main/error.do");
							request.setAttribute("title", "Usuario Error");
							request.setAttribute("errorMsg", "Rut no válido");
							request.setAttribute("error", "1");
							rq.forward(request, response);					
						}
					}else{
						logger.info("Rut no válido: " + rutParam);
						RequestDispatcher rq = request.getRequestDispatcher("/main/error.do");
						request.setAttribute("title", "Usuario Error");
						request.setAttribute("errorMsg", "Rut no válido");
						request.setAttribute("error", "2");
						rq.forward(request, response);	
					}
					
			}else{
				request.setAttribute("error", "2");
			}
				
			forward = mapping.findForward("success");
		} catch (Exception e) {
			forward = Utils.returnErrorForward(mapping, e, this.getClass());
		}
		return (forward);

	}
}
