package cl.araucana.cp.presentation.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.mgr.ParametroMgr;

import com.bh.talon.User;

import org.apache.log4j.Logger;
import org.hibernate.Session;

public class RedireccionPlanillasAction  extends AppAction  {
	
	static Logger logger = Logger.getLogger(RedireccionPlanillasAction.class);;
	
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("\n\n\nredireccionando a Mis Plantillas:");
		
		Session session = HibernateUtil.getSession();
		ParametroMgr parametro = new ParametroMgr(session);

		String urlPlanillas = parametro.getParam(Constants.PARAM_URL_MIS_PLANILLAS);

		response.sendRedirect(response.encodeRedirectURL(urlPlanillas));

		HibernateUtil.closeSession();
		return null;
	}
}
