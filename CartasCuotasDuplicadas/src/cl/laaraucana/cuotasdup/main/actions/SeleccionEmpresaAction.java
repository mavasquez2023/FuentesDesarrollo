package cl.laaraucana.cuotasdup.main.actions;

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

import cl.laaraucana.cuotasdup.dao.VO.CuotaVO;
import cl.laaraucana.cuotasdup.dao.VO.ParamVO;
import cl.laaraucana.cuotasdup.main.dao.ConsultaTrabajadoresDAO;
import cl.laaraucana.cuotasdup.main.forms.IntegracionCrmForm;
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
			IntegracionCrmForm formCRM = (IntegracionCrmForm) form;

			String rut = formCRM.getRut();

			HttpSession sesion = request.getSession();
			sesion.invalidate();
			sesion = request.getSession();

			String rutEmpresa = request.getParameter("RutEmpresa");
			rutEmpresa= rutEmpresa!=null && rutEmpresa.equals("")?null:rutEmpresa;
			logger.info("Consulta Rut empresa=" + rutEmpresa);
			String razonSocial = request.getParameter("RazonSocial");
			razonSocial= razonSocial!=null && razonSocial.equals("")?null:razonSocial;
			String oficina = request.getParameter("CodigoOficina");
			oficina= oficina!=null && oficina.equals("")?null:oficina;
			String sucursal = request.getParameter("CodigoSucursal");
			sucursal= sucursal!=null && sucursal.equals("")?null:sucursal;
			ParamVO param= new ParamVO(rutEmpresa, razonSocial, oficina, sucursal);
			
				
	
						try {
							ConsultaTrabajadoresDAO listaTra= new ConsultaTrabajadoresDAO();
							//logger.info("consulta DAO");
							List<ParamVO> empresas= listaTra.consultaEmpresas(param);
							if(empresas.size()>0){
								request.setAttribute("empresas", empresas);
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
