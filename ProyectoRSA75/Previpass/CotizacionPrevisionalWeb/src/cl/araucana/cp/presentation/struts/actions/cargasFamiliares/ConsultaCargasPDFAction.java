package cl.araucana.cp.presentation.struts.actions.cargasFamiliares;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.cp.distribuidor.hibernate.beans.ArchivoCargaFamiliarVO;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.mgr.CargaFamiliarMgr;
import cl.araucana.cp.presentation.struts.forms.cargasFamiliares.ConsultaCargasPDFActionForm;

import com.bh.talon.Message;
import com.bh.talon.MessageList;
import com.bh.talon.User;

public class ConsultaCargasPDFAction extends AppAction
{
	public String FORWARD = "cargasFamPDF";
	static Logger logger = Logger.getLogger(ConsultaCargasPDFAction.class);
	
	public ConsultaCargasPDFAction() {
		super();
	}

	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm formulario, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Transaction tx = null;
		try
		{
			//String buscarCargasPorTrab = "buscarCargasPorTrab";
			String buscarCargasPorEmp  = "buscarCargasPorEmp";

			String operacion = "";
			if (request.getParameter("operacion") != null)
				operacion = request.getParameter("operacion");	//VALUE del Submit del Formulario

			int idTrabajador = request.getParameter("idTrabajador") != null ? Integer.parseInt(request.getParameter("idTrabajador")) : 0;
			int idEmpresa    = request.getParameter("rutEmpresa")   != null ? Integer.parseInt(request.getParameter("rutEmpresa"))   : 0;
			int idCCAF		 = request.getParameter("idCaja")       != null ? Integer.parseInt(request.getParameter("idCaja"))       : 0;

			String periodo = request.getParameter("periodo") != null ? request.getParameter("periodo") : "";
			periodo = periodo.replace('-', '/');
			
			SimpleDateFormat formato = new SimpleDateFormat("MMMM yyyy", new Locale("es_ES"));
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.MONTH, Integer.parseInt(periodo.split("/")[0]) - 1 );
			cal.set(Calendar.YEAR, Integer.parseInt(periodo.split("/")[1]));
			Date fechaPeriodo = cal.getTime();
			periodo = formato.format(fechaPeriodo);

			boolean cargasPorEmp = false;

			Session session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			logger.info("ConsultaCargasAction:operacion:" + operacion + "::");
			ConsultaCargasPDFActionForm form = (ConsultaCargasPDFActionForm) formulario;

			CargaFamiliarMgr cargaFamiliarMgr = new CargaFamiliarMgr(session);
			List listaCargas = new ArrayList();

			HashMap result = cargaFamiliarMgr.getCargasPorTrabajadorPDF(idTrabajador, idEmpresa, idCCAF);
			
			List cargaFamiliarList = (List)request.getSession().getAttribute("cargaFamiliarList");
			if (cargaFamiliarList == null || cargaFamiliarList.size() == 0)
				cargaFamiliarList = cargaFamiliarMgr.getFechaEnvioByCaja(idCCAF);
			
			request.getSession().setAttribute("cargaFamiliarList", cargaFamiliarList);
			listaCargas = (List) result.get("informe");

			if (operacion.equals(buscarCargasPorEmp)) {
				cargasPorEmp = true;
			}

			form.setCargasPorEmp(cargasPorEmp);
			form.setPeriodo(periodo.trim().toUpperCase());
			form.setNombreSucursal(String.valueOf(result.get("nombreSucursal")));
			form.setDireccSucursal(String.valueOf(result.get("direccSucursal")));
			form.setMontoTotal(String.valueOf(result.get("montoTotal")));

			if (listaCargas.size() > 0) {
				form.setCargas(listaCargas.size() == 0 ? null : listaCargas);
				return mapping.findForward(FORWARD);
			} else {
				ActionMessages am = new ActionMessages();
				am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.sinCargas"));
				this.saveMessages(request, am);
				form.setCargas(null);
			}
			
			tx.commit();
			return mapping.findForward(FORWARD);
		} catch (Exception e) {
			logger.error("ConsultaCargasPDFAction::", e);
			if (tx != null)
				tx.rollback();

			MessageList l = new MessageList();
			l.add(new Message("Ha ocurrido un Error", e.getMessage()));
			request.setAttribute("messageList", l);
			return mapping.findForward(PARAM_ERROR);
		}
	}
}