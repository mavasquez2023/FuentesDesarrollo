package cl.laaraucana.simulacion;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simulacion.ibatis.dao.CuotasDAO;
import cl.laaraucana.simulacion.ibatis.dao.SucursalesDAO;
import cl.laaraucana.simulacion.ibatis.dao.UtilesDAO;
import cl.laaraucana.simulacion.ibatis.vo.ParametroVO;
import cl.laaraucana.simulacion.utils.BodyEmailUtils;
import cl.laaraucana.simulacion.utils.MailUtils;

public class SolicitarCreditoAction extends Action {

	protected Logger logger = Logger.getLogger(this.getClass());

	

	public ActionForward execute(ActionMapping mapping, ActionForm _form, HttpServletRequest request,
			HttpServletResponse response) {

		TestForm form = new TestForm();

		HttpSession session = request.getSession();

		form = (TestForm) session.getAttribute("testForm");

		String forward = "credito";
		try {

		} catch (Exception e) {

			logger.error("Error al solicitar el crédito", e);
		}

		try {

			SucursalesDAO sucursalesDao = new SucursalesDAO();

			

			request.setAttribute("sucursales", sucursalesDao.consultaSucursales());

			CuotasDAO cuotasDao = new CuotasDAO();

			request.setAttribute("cuotas", cuotasDao.consultaCuotas());

		
			ParametroVO parametroVO = new ParametroVO();
			parametroVO = UtilesDAO.consultaParametro(1, 4);
			String parametrosCondiciones = parametroVO.getValor();

			if (parametrosCondiciones != null) {
				request.setAttribute("parametrosCondiciones", parametrosCondiciones);
			} else {
				request.setAttribute("observaciones", "Error al procesar información de la base de datos");
			}
		} catch (Exception e) {
			request.setAttribute("observaciones", "Error al obtener información desde la base de datos");
			e.printStackTrace();
		}

		session.setAttribute("testForm", form);

		return mapping.findForward(forward);
	}
}
