package cl.laaraucana.resultadonrp.actions;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.resultadonrp.dao.ConsultaServicesDAO;
import cl.laaraucana.resultadonrp.util.Utils;


public class EstadisticaDisponibilizacionAction extends Action {
	private static Logger logger = Logger.getLogger("InitAction");
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward(); 
		HttpSession sesion = request.getSession();
		sesion = request.getSession();
		String proceso= request.getParameter("proceso");
		request.setAttribute("menu", "disponibilizacion");
		String periodo= Utils.obtenerPeriodoCualquiera(-6);
		
		ConsultaServicesDAO nrpDAO= new ConsultaServicesDAO();
		List<HashMap> registros= nrpDAO.estadisticasArchivosDisponibilizacion(periodo);
		request.setAttribute("datos", registros);
		
		forward = mapping.findForward("success");
		return forward;
	}

}
