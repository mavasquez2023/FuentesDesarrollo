package cl.araucana.adminCpe.presentation.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.NodosProcesamientoMgr;
import cl.araucana.adminCpe.presentation.struts.forms.EstadisticasForm;

import com.bh.talon.Message;
import com.bh.talon.MessageList;
import com.bh.talon.User;

/*
 * @(#) EstadisticasAction.java 1.6 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author aacuña
 * 
 * @version 1.6
 */
public class EstadisticasAction extends AppAction
{
	public static final String FORWARD = "forward";
	private static Logger log = Logger.getLogger(EstadisticasAction.class);

	/**
	 * procesamiento Nodos
	 */
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try
		{
			NodosProcesamientoMgr estadisticasMgr = new NodosProcesamientoMgr(HibernateUtil.getSession(), user.getLogin());
			EstadisticasForm ef = (EstadisticasForm) form;
			if (ef.getSubAccion() != null && ef.getSubAccion().equals("clean") && ef.getCheckbox() != null)
			{
				estadisticasMgr.limpiaCache(ef.getCheckbox());
				log.debug("check:" + request.getParameter("checkbox") + "::");
			}

			log.info("solicitando estadisticas");
			List estadisticas = estadisticasMgr.getStatsXNodo();

			request.setAttribute("reportes", estadisticas);
			return mapping.findForward(FORWARD);
		} catch (Exception e)
		{
			log.error("error en estadisticas action:", e);
		}

		MessageList l = new MessageList();
		l.add(new Message("No se Encontro Nodo Distribuidor o nodo procesador", "verificar Base de Datos"));
		request.setAttribute("messageList", l);
		return mapping.findForward("error");
	}
}
