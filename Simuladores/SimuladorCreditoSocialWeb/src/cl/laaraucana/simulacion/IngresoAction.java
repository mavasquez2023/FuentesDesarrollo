package cl.laaraucana.simulacion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.servicios.simuladorCreditoSocial.RequestWS;
import cl.laaraucana.servicios.simuladorCreditoSocial.SimuladorCreditoSocialServiceLocator;
import cl.laaraucana.simulacion.ibatis.dao.UtilesDAO;
import cl.laaraucana.simulacion.ibatis.vo.ParametroVO;
import cl.laaraucana.simulacion.utils.ConstantesFormalizar;

public class IngresoAction extends Action {

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        String forward = "ingreso";
        
        try {
			ParametroVO parametroVO = new ParametroVO();
			parametroVO = UtilesDAO.consultaParametro(1, 3);
			String parametrosCondiciones= parametroVO.getValor();
			if (parametrosCondiciones != null) {
				request.setAttribute("parametrosCondiciones", parametrosCondiciones);

			} else {
				request.setAttribute("observaciones", "Error al procesar información de la base de datos");
			}
		} catch (Exception e) {
			request.setAttribute("observaciones", "Error al obtener información desde la base de datos");
			e.printStackTrace();
		}
        
       // TODO Cargar variables desde base de datos, textos legales
        
        return mapping.findForward(forward);
    }
}
