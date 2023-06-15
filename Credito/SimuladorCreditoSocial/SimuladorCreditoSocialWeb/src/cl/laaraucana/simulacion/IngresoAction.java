package cl.laaraucana.simulacion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.servicios.simuladorCreditoSocial.RequestWS;
import cl.laaraucana.servicios.simuladorCreditoSocial.SimuladorCreditoSocialServiceLocator;
import cl.laaraucana.simulacion.ibatis.dao.CuotasDAO;
import cl.laaraucana.simulacion.ibatis.dao.SucursalesDAO;
import cl.laaraucana.simulacion.ibatis.dao.UtilesDAO;
import cl.laaraucana.simulacion.ibatis.vo.ParametroVO;
import cl.laaraucana.simulacion.ibatis.vo.ParamsSingleton;
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
        	//Se setea Sucursales
        	request.setAttribute("sucursales", ParamsSingleton.getInstance().getSucursales());
        	//se setea cuotas
        	request.setAttribute("cuotas", ParamsSingleton.getInstance().getCuotas());
        	//Se setea texto condiciones
        	request.setAttribute("parametrosCondiciones", ParamsSingleton.getInstance().getParametrosCondiciones());
        	
		} catch (Exception e) {
			request.setAttribute("observaciones", "Error al obtener información desde la base de datos");
			e.printStackTrace();
		}
        
       // TODO Cargar variables desde base de datos, textos legales
        
        return mapping.findForward(forward);
    }
}
