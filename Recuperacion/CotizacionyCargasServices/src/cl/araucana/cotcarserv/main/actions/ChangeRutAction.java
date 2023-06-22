package cl.araucana.cotcarserv.main.actions;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.cotcarserv.main.dao.ConsultaServicesDAO;
import cl.laaraucana.satelites.Utils.RutUtil;


public class ChangeRutAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		final Logger logger = Logger.getLogger(this.getClass());
		ActionForward forward = new ActionForward(); // return value
		HttpSession sesion= request.getSession();
		String rut_empresa= request.getParameter("rutemp_menu").replaceAll("\\.", "");
		try {
			logger.info("Cambiando empresa: " + rut_empresa);
			if(RutUtil.IsRutValido(rut_empresa)){
				sesion.setAttribute("rutEmpresa", rut_empresa);
				ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
				logger.info("Consultando nombre empresa");
				Map<String, String> dataemp= consultaDAO.consultaNombreEmpresa(RutUtil.obtenerParteNumerica(rut_empresa));
				dataemp.put("RUT_EMPRESA", rut_empresa);
				request.setAttribute("dataemp", dataemp);
				
				//guarda empresa en sesion para opciones menú
				Map<String, String> listamap= new HashMap<String, String>();
				listamap.put(rut_empresa, dataemp.get("RAZON_SOCIAL"));
				TreeMap<String, String> lista_sorted= new TreeMap(listamap);
				sesion.setAttribute("empresas", lista_sorted);
			}
		} catch (Exception e) {
			request.setAttribute("msg", "Error Genérico");
		}
		forward = mapping.findForward("init");
		return (forward);
	}
}
