package cl.araucana.adminCpe.presentation.struts.actions.calendario;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionRedirect;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.CalendarioMgr;
import cl.araucana.adminCpe.presentation.struts.forms.calendario.EdicionCalendarioActionForm;
import cl.araucana.adminCpe.presentation.struts.javaBeans.LineaListaCalendario;
import cl.araucana.cp.distribuidor.hibernate.beans.CalendarioVO;

import com.bh.talon.User;

/*
 * @(#) EdicionCalendarioAction.java 1.2 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author jdelgado
 * @author aacuña
 * 
 * @version 1.5
 */
public class EdicionCalendarioAction extends AppAction
{
	private static Logger logger = Logger.getLogger(EdicionCalendarioAction.class);

	public EdicionCalendarioAction()
	{
		super();
	}

	/**
	 * edicion calendario
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EdicionCalendarioActionForm actForm = (EdicionCalendarioActionForm) form;

		ActionMessages am = new ActionMessages();
		Session session = null;
		Transaction tx = null;
		SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");
		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			CalendarioMgr calendarioMgr = new CalendarioMgr(session);
			List consulta;
			String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
			if (request.getParameter("operacion") == null || "Listar".equals(request.getParameter("operacion")))
			{
				consulta = calendarioMgr.getCalendario();
				CalendarioVO calendario;
				List calendarios = new ArrayList();
				for (int i = 0; i < consulta.size(); i++)
				{
					LineaListaCalendario linea = new LineaListaCalendario();
					calendario = (CalendarioVO) consulta.get(i);
					linea.setIdCal(calendario.getIdCal());
					linea.setMes(calendario.getMes());
					//linea.setFecha1(dateFormat.format(calendario.getFecha1()));
					//linea.setFecha2(dateFormat.format(calendario.getFecha2()));
					//linea.setFecha3(dateFormat.format(calendario.getFecha3()));
					//linea.setFecha4(dateFormat.format(calendario.getFecha4()));
					//linea.setHora(String.valueOf(calendario.getFecha4().getHours()));
					//linea.setMin(String.valueOf(calendario.getFecha4().getMinutes()));
					linea.setInformacion(calendario.getInformacion());
					calendarios.add(linea);
				}

				actForm.setConsulta(calendarios);
				return mapping.findForward("Listar");
			}
			if ("Guardar".equals(request.getParameter("operacion")))
			{				
				String[] id = request.getParameterValues("idCal");
				String[] informacion = request.getParameterValues("informacion");
				CalendarioVO calen = new CalendarioVO();
				for (int i = 0; i < informacion.length; i++)
				{
					calen.setMes(meses[i]);
					calen.setIdCal(Integer.parseInt(id[i]));
					calen.setFecha1(new java.sql.Date(new java.util.Date().getTime()));
					calen.setFecha2(new java.sql.Date(new java.util.Date().getTime()));
					calen.setFecha3(new java.sql.Date(new java.util.Date().getTime()));
					Timestamp f4 = (new java.sql.Timestamp(new java.util.Date().getTime()));					
					calen.setFecha4(f4);
					calen.setInformacion(informacion[i]);
					calendarioMgr.guardarCalendario(calen);
				}
				tx.commit();
				am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardar", "Calendario ", ""));
				this.saveMessages(request.getSession(), am);
				ActionRedirect redirect = new ActionRedirect(mapping.findForward("exitoGuardar"));
				redirect.addParameter("operacion", "Listar");
				return redirect;
			}
		} catch (Exception e)
		{
			logger.error("errror", e);
		}
		return mapping.findForward("error");
	}
}
