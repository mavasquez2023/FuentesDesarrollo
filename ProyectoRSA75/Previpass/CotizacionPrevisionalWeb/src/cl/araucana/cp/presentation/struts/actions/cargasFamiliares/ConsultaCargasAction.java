package cl.araucana.cp.presentation.struts.actions.cargasFamiliares;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

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

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.base.UsuarioCP;
import cl.araucana.cp.presentation.mgr.CargaFamiliarMgr;
import cl.araucana.cp.presentation.struts.forms.cargasFamiliares.ConsultaCargasActionForm;

import com.bh.talon.Message;
import com.bh.talon.MessageList;
import com.bh.talon.User;

public class ConsultaCargasAction extends AppAction
{
	//public String FORWARD = "trabListaCargas";
	public static final String BLANCO  = "blancoCargasFam";
	static Logger logger = Logger.getLogger(ConsultaCargasAction.class);
	
	public ConsultaCargasAction()
	{
		super();
		this.btns.add("buscar");
		this.btns.add("buscarTrabajador");
		this.btns.add("buscarEmpresa");
	}

	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm formulario, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Transaction tx = null;
		try
		{
			String FORWARD = "trabListaCargas";

			String buscarTrabajador = Constants.TXT_BTNS.getProperty("buscarTrabajador");
			String buscarEmpresa    = Constants.TXT_BTNS.getProperty("buscarEmpresa");
			String buscarTrabajadores  = "buscarTrabajadores";
			String buscarCargas        = "buscarCargas";

			//Carga una página en blanco cuando se selecciona "Cargas Familiares" en el menú lateral izquierdo.
			String auxFlgBlanco = request.getParameter("flgBlanco") == null ? "0" : request.getParameter("flgBlanco");
			if (auxFlgBlanco.equals("1"))
				return mapping.findForward(BLANCO);

			String operacion = "";
			if (request.getParameter("operacion") != null)
				operacion = request.getParameter("operacion");	//VALUE del Submit del Formulario

			Session session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			logger.info("ConsultaCargasAction:operacion:" + operacion + "::");
			ConsultaCargasActionForm form = (ConsultaCargasActionForm) formulario;

			HashMap filtros = new HashMap();

			//Búsqueda por Trabajador
			if (form.getRutOculto() != null && !form.getRutOculto().trim().equals("")) {
				filtros.put("rutTrabajador", new Integer(Utils.desFormatRut(form.getRutOculto().trim())));
			}
			if (form.getNombreOculto() != null && !form.getNombreOculto().trim().equals("")) {
				StringTokenizer tokenizer = new StringTokenizer(form.getNombreOculto().trim().toUpperCase(), " ");
				StringBuffer sb = new StringBuffer(tokenizer.nextToken());
				while (tokenizer.hasMoreTokens())
					sb.append('%').append(tokenizer.nextToken());
				logger.info(sb);
				filtros.put("nombre", sb.toString());
			}

			UsuarioCP usuarioCP = (UsuarioCP) user;

			//Búsqueda por Empresa
			if (form.getRutEmpresa() != null && !form.getRutEmpresa().trim().equals("")) {
				filtros.put("rutEmpresa",  new Integer(Utils.desFormatRut(form.getRutEmpresa().trim())));
			}
			if (form.getRazonSocial() != null && !form.getRazonSocial().trim().equals("")) {
				filtros.put("razonSocial", form.getRazonSocial().trim().toUpperCase());
			}

			CargaFamiliarMgr cargaFamiliarMgr = new CargaFamiliarMgr(session);
			List listaTrab = new ArrayList();
			List listaEmpresas = new ArrayList();
			List listaCargas = new ArrayList();

			//TODO Pruebas Cargas
			if (operacion.equals(buscarTrabajador)) {
				listaTrab = cargaFamiliarMgr.getTrabajadores(filtros, usuarioCP.getUnionEmpresasLectura());
			} else if (operacion.equals(buscarEmpresa)) {
				listaEmpresas = cargaFamiliarMgr.getEmpresas(filtros, usuarioCP.getUnionEmpresasLectura());
				request.getSession().removeAttribute("idCaja");
				request.getSession().removeAttribute("cargaFamiliarList");
			} else if (operacion.equals(buscarTrabajadores)) {
				filtros.clear();
				filtros.put("rutEmpresa", new Integer(Integer.parseInt(request.getParameter("rutEmpresa"))));
				filtros.put("idCaja",     new Integer(Integer.parseInt(request.getParameter("idCaja"))));
				listaTrab = cargaFamiliarMgr.getTrabajadores(filtros, usuarioCP.getUnionEmpresasLectura());
				List cargaFamiliarList = cargaFamiliarMgr.getFechaEnvioByCaja(Integer.parseInt(request.getParameter("idCaja")));
				request.getSession().setAttribute("cargaFamiliarList", cargaFamiliarList);
				request.getSession().setAttribute("idCajaSession", request.getParameter("idCaja"));
			} else if (operacion.equals(buscarCargas)) {
				listaCargas = cargaFamiliarMgr.getCargasPorTrabajador(Integer.parseInt(request.getParameter("idTrabajador")), Integer.parseInt(request.getParameter("idEmpresa")), 0);
				List cargaFamiliarList = cargaFamiliarMgr.getFechaEnvioByCaja(Integer.parseInt((String)request.getParameter("idCaja")));
				request.getSession().setAttribute("cargaFamiliarList", cargaFamiliarList);
			}
			
			int flgPaginacion = 0;
			if (request.getParameter("FLG_Paginacion") != null)
				flgPaginacion = Integer.parseInt(request.getParameter("FLG_Paginacion"));

			if (operacion.equals("") && flgPaginacion == 0) {
				//Sólo se omite la lista cuando se ingresa a la página desde el Link (operacion = "").
				form.setMostrarLista("NO");
				return mapping.findForward(FORWARD);
			} else {
				form.setMostrarLista("SI");
			}

			if (listaTrab.size() > 0 || listaEmpresas.size() > 0 || listaCargas.size() > 0) {
				if (listaTrab.size() == 0 && operacion.equals(buscarTrabajador)) {
					ActionMessages am = new ActionMessages();
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.sinTrabajadores", ""));
					this.saveMessages(request, am);
				}

				if (listaEmpresas.size() == 0 && operacion.equals(buscarEmpresa)) {
					ActionMessages am = new ActionMessages();
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.sinEmpresa"));
					this.saveMessages(request, am);
				}

				form.setTrabajadores(listaTrab.size() == 0 ? null : listaTrab);
				form.setEmpresas(listaEmpresas.size() == 0 ? null : listaEmpresas);
				form.setCargas(listaCargas.size() == 0 ? null : listaCargas);
				return mapping.findForward(FORWARD);
			} else {
				ActionMessages am = new ActionMessages();
				if (operacion.equals(buscarTrabajador))
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.sinTrabajadores", ""));
				else if (operacion.equals(buscarEmpresa))
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.sinEmpresa"));
				else if (operacion.equals(buscarTrabajadores))
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.sinTrabajadores", ""));
				else
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.sinCargas"));
				this.saveMessages(request, am);
				form.setTrabajadores(null);
				form.setEmpresas(null);
				form.setCargas(null);
			}

			tx.commit();
			return mapping.findForward(FORWARD);
		} catch (Exception e) {
			logger.error("ConsultaCargasAction::", e);
			if (tx != null)
				tx.rollback();

			MessageList l = new MessageList();
			l.add(new Message("Ha ocurrido un Error", e.getMessage()));
			request.setAttribute("messageList", l);
			return mapping.findForward(PARAM_ERROR);
		}
	}
}