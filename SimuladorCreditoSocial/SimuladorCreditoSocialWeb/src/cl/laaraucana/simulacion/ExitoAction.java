package cl.laaraucana.simulacion;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simulacion.ibatis.dao.CreditoSolicitudDAO;
import cl.laaraucana.simulacion.ibatis.vo.CreditoSolicitudVO;
import cl.laaraucana.simulacion.ibatis.vo.ParamsSingleton;
import cl.laaraucana.simulacion.utils.BodyEmailUtils;
import cl.laaraucana.simulacion.utils.Configuraciones;
import cl.laaraucana.simulacion.utils.MailUtils;

public class ExitoAction extends Action {

	protected Logger logger = Logger.getLogger(ExitoAction.class);
	
	private static final String asunto = Configuraciones.getConfig("solicitud.asunto.cliente");
	private static final String asuntoSucursal = Configuraciones.getConfig("solicitud.asunto.sucursal");

	public ActionForward execute(ActionMapping mapping, ActionForm _form, HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession sesion = request.getSession();

		TestForm form = (TestForm) sesion.getAttribute("testForm");

		CreditoSolicitudDAO dao = new CreditoSolicitudDAO();

		CreditoSolicitudVO credito = new CreditoSolicitudVO();
		
		String codSucursal= (String)request.getSession().getAttribute("codSucursal");

		String forward = "exito";

		try {
			String mailSucursal = ParamsSingleton.getInstance().getMapSucursales().get(codSucursal).getEmail();
			if(mailSucursal==null || mailSucursal.equals("")){
				mailSucursal= Configuraciones.getConfig("mail-araucana");
			}
			credito.setNombre(form.getNombreCompleto());
			String rut = form.getRut().replace(".", "");
			String[] rutdv = rut.split("-");
			credito.setRutAfiliado(Integer.parseInt(rutdv[0]));
			credito.setDvAfiliado(rutdv[1]);
			credito.setCelular(request.getParameter("celular"));
			credito.setTelefono(request.getParameter("telefono"));
			credito.setEmail(request.getParameter("email"));
			credito.setFecha(new Date());
			String monSim = form.getCTC().replace("$", "");
			monSim = monSim.replace(".", "");
			credito.setMontoSimulado(Long.parseLong(monSim));
			credito.setSucursal(form.getSucursal());
			credito.setNumeroCuotasSimuladas(Integer.parseInt(form.getCuotas()));
			credito.setTipoAfiliado(form.getTipoAfiliado());

			dao.saveCreditosSolicitados(credito);
			logger.info("Solicitud guardada en SQLServer");
			
			MailUtils utils = new MailUtils();

			utils.sendEmail(form.getRut(), request.getParameter("email"), asunto,
					BodyEmailUtils.BodyEmailCliente(form));
			logger.info("Correo enviado a :" + request.getParameter("email"));
			utils.sendEmail(form.getRut(), mailSucursal,
					asuntoSucursal + credito.getRutAfiliado() + "-" + credito.getDvAfiliado(),
					BodyEmailUtils.bodyMensajeSucursal(form, credito));
			logger.info("Correo enviado a :" + mailSucursal);
			request.getSession().setAttribute("rut", "");
			request.getSession().setAttribute("monto", "");
		} catch (Exception e) {

			forward = "error";
			logger.error("Error en el proceso.", e);
			e.printStackTrace();
		}

		return mapping.findForward(forward);

	}

}
