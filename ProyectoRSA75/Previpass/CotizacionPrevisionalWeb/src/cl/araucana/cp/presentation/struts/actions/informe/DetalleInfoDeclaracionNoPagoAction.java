package cl.araucana.cp.presentation.struts.actions.informe;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ParametroVO;
import cl.araucana.cp.hibernate.dao.ParametrosDAO;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.base.UsuarioCP;
import cl.araucana.cp.presentation.mgr.PersonaMgr;
import cl.araucana.cp.presentation.mgr.ProcesoMgr;
import cl.araucana.cp.presentation.struts.forms.InformeActionDNPForm;
import cl.araucana.cp.presentation.struts.forms.InformeActionForm;

import com.bh.talon.User;

/*
 * @(#) DetalleInformeAction.java 1.0 23/08/2010
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author jlandero
 * 
 * @version 1.0
 */
public class DetalleInfoDeclaracionNoPagoAction extends AppAction
{
	static Logger logger = Logger.getLogger(DetalleInfoDeclaracionNoPagoAction.class);

	/**
	 *	Action encargado de mostrar la informacion sobre el listado de errores
	 */
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception
	{	
		Session session = null;
		Transaction tx = null;		

		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			ProcesoMgr procesoMgr = new ProcesoMgr(session);
			PersonaMgr personaMgr = new PersonaMgr(session);

			UsuarioCP usuarioCP = (UsuarioCP) user;
			// Recupera las empresas de las que es encargado el user o es administrador
			List listaEmpresas = personaMgr.getListaEmpresasIn(usuarioCP.getUnionEmpresasLectura());			

			Collection tiposDeNominas = procesoMgr.getTiposProceso();
			request.getSession().setAttribute("tiposDeNominas", tiposDeNominas);

			InformeActionDNPForm form = (InformeActionDNPForm) actionForm;
			
			ParametrosDAO parametrosDAO = new ParametrosDAO(session);
			
			ParametroVO parametroVO = parametrosDAO.getParametro(new Integer(Constants.PARAM_PERIODO).intValue());
			if(form.getPeriodo() == null || form.getPeriodo().trim().equals(""))
				form.setPeriodo(parametroVO.getValor());
			
			//csanchez.
			//Se solicitó que cuando se ingrese desde el menú izquierdo se carge por defecto lo reportes asociados a Remuneraciones.
			if (form.getRutEmpresa() == null && form.getTipoProceso() == null && form.getTipoProceso() == null) {
				form.setRutEmpresa("");
				form.setRazonSocial("");
				form.setTipoProceso("R");
				form.setTipoProceso("R");
			}
			
			String desFormatRut = String.valueOf(Utils.desFormatRut(form.getRutEmpresa().trim()));			
			
			//List listadoEmpresas = procesoMgr.getEmpresasAvisosErrores(desFormatRut, form.getRazonSocial(), form.getTipoProceso(), listaEmpresas);
			List listadoEmpresasII = procesoMgr.getEmpresasDeclaraNoPago(desFormatRut, form.getRazonSocial(), form.getTipoProceso(), listaEmpresas,form.getPeriodo());

			request.setAttribute("listadoEmpresas", listadoEmpresasII);
			request.setAttribute("tipoNomina", form.getTipoProceso());
			tx.commit();

			return mapping.findForward("exito");
		} catch (Exception ex)
		{
			if (tx != null)
				tx.rollback();
			logger.error("Ha ocurrido la siguiente excepcion: ", ex);
			return mapping.findForward("error");
		}
	}
}