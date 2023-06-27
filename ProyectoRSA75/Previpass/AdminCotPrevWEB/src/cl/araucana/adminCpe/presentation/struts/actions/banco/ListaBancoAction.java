package cl.araucana.adminCpe.presentation.struts.actions.banco;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.BancoMgr;
import cl.araucana.adminCpe.presentation.struts.forms.banco.ListaBancoActionForm;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.BancoVO;

import com.bh.talon.User;

/*
 * @(#) ListaBancoAction.java 1.2 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author jdelgado
 * @author acuña
 * 
 * @version 1.2
 */
public class ListaBancoAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ListaBancoAction.class);
	private BancoMgr bancoMgr;

	public ListaBancoAction()
	{
		super();
	}

	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ListaBancoActionForm actForm = (ListaBancoActionForm) form;

		Session session = null;
		try
		{
			session = HibernateUtil.getSession();

			// Instancia los managers correspondientes
			this.bancoMgr = new BancoMgr(session);

			List lista = this.bancoMgr.getBancos(false);
			List listaAux = new ArrayList();
			for (Iterator it = lista.iterator(); it.hasNext();)
			{
				BancoVO banco = (BancoVO) it.next();
				banco.setRutBanco(Utils.formatRut((new Integer(banco.getRutBanco()).intValue())));
				listaAux.add(banco);
			}

			actForm.setLista(listaAux);

			return mapping.findForward("exito");
		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en ListaBancoAction.doTask()", ex);
			return mapping.findForward("error");
		}
	}
}
