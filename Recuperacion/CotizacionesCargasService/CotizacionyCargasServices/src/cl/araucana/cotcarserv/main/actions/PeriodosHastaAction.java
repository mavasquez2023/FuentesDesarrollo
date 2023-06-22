package cl.araucana.cotcarserv.main.actions;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONObject;

import cl.araucana.autoconsulta.vo.PeriodoVO;
import cl.araucana.cotcarserv.dao.VO.CargasVO;
import cl.araucana.cotcarserv.dao.VO.ParamVO;
import cl.araucana.cotcarserv.dao.VO.PeriodosVO;
import cl.araucana.cotcarserv.main.dao.ConsultaServicesDAO;
import cl.araucana.cotcarserv.main.forms.ConsultaCotizacionForm;
import cl.araucana.cotcarserv.servlets.EmpresasLDAP;
import cl.laaraucana.satelites.Utils.GeneratorXLS;
import cl.laaraucana.satelites.Utils.RutUtil;
import cl.laaraucana.satelites.Utils.Utils;
import cl.recursos.EnviarMail;

/**
 * @version 1.0
 * @author
 */
public class PeriodosHastaAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value

		String periodo="";
		
		try {
			HttpSession sesion = request.getSession();
			sesion = request.getSession();
			String periodoActual= (String)sesion.getAttribute("periodoActual");
			periodo = request.getParameter("id");
		
			List<String> listaPeriodos= new ArrayList<String>();
			
			int maxperiodos=3;
			
			for (int i = 0; i < maxperiodos; i++) {
				
				String periodonext= Utils.obtenerPeriodoCualquieraFrom(periodo, i, false);
				if(Integer.parseInt(periodonext) <= Integer.parseInt(periodoActual)){
					listaPeriodos.add(periodonext);
				}
			}
			JSONObject salida = new JSONObject();
			salida.put("periodos", listaPeriodos);
			String html = salida.toJSONString();
			registrarSalida(response, html);
			return null;

			
			
			

			

		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en consulta Periodos: " );
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error al consultar datos:" + e.getMessage());
			request.setAttribute("error", "1");
			forward = mapping.findForward("success");				
		}

		return (forward);

	}
		
	public void registrarSalida(HttpServletResponse response, String result) {
		response.setCharacterEncoding("iso-8859-1");
		response.setContentType("text/html");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	    
}
