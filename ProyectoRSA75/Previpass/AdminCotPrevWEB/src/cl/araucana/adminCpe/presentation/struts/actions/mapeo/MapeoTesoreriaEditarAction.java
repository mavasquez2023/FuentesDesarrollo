package cl.araucana.adminCpe.presentation.struts.actions.mapeo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
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
import cl.araucana.adminCpe.presentation.mgr.TesoreriaMgr;
import cl.araucana.adminCpe.presentation.struts.forms.mapeo.MapeoTesoreriaEditarActionForm;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoTesoreriaVO;
import com.bh.talon.User;

/*
 * @(#) MaperoTesoreriaEditarAction.java 1.2 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author malvarez
 * @author jdelgado
 * 
 * @version 1.2
 */
public class MapeoTesoreriaEditarAction extends AppAction
{
	private static Logger logger = Logger.getLogger(MapeoTesoreriaEditarAction.class);

	public MapeoTesoreriaEditarAction()
	{
		super();
	}

	/**
	 * Mapeo Tesoreria Editar
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MapeoTesoreriaEditarActionForm actForm = (MapeoTesoreriaEditarActionForm) form;

		boolean bGuardar = false;
		ActionMessages am = new ActionMessages();
		ActionErrors ae = new ActionErrors();
		Session session = null;
		Transaction tx = null;

		try
		{
			if (request.getParameter("accionInterna") != null && request.getParameter("accionInterna").equals("CANCELAR"))
				return new ActionRedirect(mapping.findForward("cancelar"));

			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			TesoreriaMgr tesoreriaMgr = new TesoreriaMgr(session);

			ActionRedirect redirect = new ActionRedirect();

			actForm.setListaNomina(tesoreriaMgr.getTipoNomina());
			actForm.setListaConcepto(tesoreriaMgr.getConceptoTesoreria());
			actForm.setListaSeccion(tesoreriaMgr.getTipoSeccion());

			actForm.setAccion("");
			if (request.getParameter("accion") != null)
				actForm.setAccion(request.getParameter("accion"));
			char idNomina = Constants.TIPO_NOM_REMUNERACION;
			int idTipoSeccion = 1;
			int idTipoDetalle = 1;
			int idConcepto = 1;
			int idMonto = 1;

			if (request.getParameter("idNominaSeleccionado") != null && !request.getParameter("idNominaSeleccionado").equals(""))
				idNomina = request.getParameter("idNominaSeleccionado").charAt(0);

			if (request.getParameter("idTipoSeccionSeleccionado") != null && !request.getParameter("idTipoSeccionSeleccionado").equals(""))
				idTipoSeccion = Integer.parseInt(request.getParameter("idTipoSeccionSeleccionado"));

			if (idNomina == Constants.TIPO_NOM_RELIQUIDACION && idTipoSeccion < 20)
				idTipoSeccion = 20;
			if (idNomina == Constants.TIPO_NOM_GRATIFICACION && idTipoSeccion < 40)
				idTipoSeccion = 40;
			if (idNomina == Constants.TIPO_NOM_DEPOSITOCONVENIDO && idTipoSeccion < 60)
				idTipoSeccion = 60;

			actForm.setListaDetalle(tesoreriaMgr.getTipoDetalle(idNomina, idTipoSeccion));

			if (request.getParameter("idTipoDetalleSeleccionado") != null && !request.getParameter("idTipoDetalleSeleccionado").equals(""))
				idTipoDetalle = Integer.parseInt(request.getParameter("idTipoDetalleSeleccionado"));

			if (request.getParameter("idConceptoSeleccionado") != null && !request.getParameter("idConceptoSeleccionado").equals(""))
				idConcepto = Integer.parseInt(request.getParameter("idConceptoSeleccionado"));

			actForm.setListaMonto(tesoreriaMgr.getListaMontos());

			if (request.getParameter("valorMontoSeleccionado") != null && !request.getParameter("valorMontoSeleccionado").equals(""))
				idMonto = Integer.parseInt(request.getParameter("valorMontoSeleccionado"));

			MapeoTesoreriaVO mapeoTesoreriaVO = new MapeoTesoreriaVO();
			mapeoTesoreriaVO.setIdTipoNomina(idNomina);
			mapeoTesoreriaVO.setIdTipoSeccion(idTipoSeccion);
			mapeoTesoreriaVO.setIdDetalleSeccion(idTipoDetalle);
			mapeoTesoreriaVO.setIdConceptoTesoreria(idConcepto);
			mapeoTesoreriaVO.setIdMontoDetSeccion(idMonto);

			actForm.setIdNomina("" + idNomina);
			actForm.setIdTipoSeccion(idTipoSeccion);
			actForm.setIdTipoDetalle(idTipoDetalle);
			actForm.setIdConcepto(idConcepto);
			actForm.setValorMonto(idMonto);

			if (request.getParameter("idNominaActual") != null && !request.getParameter("idNominaActual").equals(""))
				actForm.setIdNominaActual(request.getParameter("idNominaActual"));
			else
				actForm.setIdNominaActual("" + idNomina);

			if (request.getParameter("idTipoSeccionActual") != null && !request.getParameter("idTipoSeccionActual").equals(""))
				actForm.setIdTipoSeccionActual(Integer.parseInt(0 + request.getParameter("idTipoSeccionActual")));
			else
				actForm.setIdTipoSeccionActual(idTipoSeccion);

			if (request.getParameter("idTipoDetalleActual") != null && !request.getParameter("idTipoDetalleActual").equals(""))
				actForm.setIdTipoDetalleActual(Integer.parseInt(request.getParameter("idTipoDetalleActual")));
			else
				actForm.setIdTipoDetalleActual(idTipoDetalle);

			if (request.getParameter("idConceptoActual") != null && !request.getParameter("idConceptoActual").equals(""))
				actForm.setIdConceptoActual(Integer.parseInt(request.getParameter("idConceptoActual")));
			else
				actForm.setIdConceptoActual(idConcepto);

			if (request.getParameter("valorMontoActual") != null && !request.getParameter("valorMontoActual").equals(""))
				actForm.setValorMontoActual(Integer.parseInt(request.getParameter("valorMontoActual")));
			else
				actForm.setValorMontoActual(idMonto);

			if (request.getParameter("accionInterna") != null)
			{
				if (request.getParameter("accionInterna").equals("GUARDAR"))
				{
					bGuardar = true;

					if (actForm.getAccion().equals("EDIT"))
					{
						MapeoTesoreriaVO _mapeoTesoreriaVO = new MapeoTesoreriaVO();
						_mapeoTesoreriaVO.setIdTipoNomina(actForm.getIdNominaActual().charAt(0));
						_mapeoTesoreriaVO.setIdTipoSeccion(actForm.getIdTipoSeccionActual());
						_mapeoTesoreriaVO.setIdDetalleSeccion(actForm.getIdTipoDetalleActual());
						_mapeoTesoreriaVO.setIdConceptoTesoreria(actForm.getIdConceptoActual());
						_mapeoTesoreriaVO.setIdMontoDetSeccion(actForm.getValorMontoActual());

						if (tesoreriaMgr.existeMapeoTesoreria(mapeoTesoreriaVO) && !mapeoTesoreriaVO.compareTo(_mapeoTesoreriaVO))
						{
							ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.existe", "Mapeo Tesoreria", ""));
							this.saveErrors(request.getSession(), ae);
						} else
						{
							if (!mapeoTesoreriaVO.compareTo(_mapeoTesoreriaVO))
							{
								tesoreriaMgr.delete(_mapeoTesoreriaVO);
								tesoreriaMgr.save(mapeoTesoreriaVO);
							}
							am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardar", "Mapeo Tesoreria", ""));
							this.saveMessages(request.getSession(), am);
						}
					} else
					{
						if (tesoreriaMgr.existeMapeoTesoreria(mapeoTesoreriaVO))
						{
							ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.existe", "Mapeo Tesoreria", ""));
							this.saveErrors(request.getSession(), ae);
						} else
						{
							tesoreriaMgr.save(mapeoTesoreriaVO);
							am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardar", "Mapeo Tesoreria", ""));
							this.saveMessages(request.getSession(), am);
						}
					}
					redirect = new ActionRedirect(mapping.findForward("cancelar"));
					tx.commit();
					return redirect;
				} else if (request.getParameter("accionInterna").equals("DEL_MAPEO"))
				{
					bGuardar = true;
					tesoreriaMgr.delete(mapeoTesoreriaVO);
					if (bGuardar)
					{
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.borrar", "Mapeo Tesoreria", ""));
						this.saveMessages(request.getSession(), am);
					}
					tx.commit();
					redirect = new ActionRedirect(mapping.findForward("cancelar"));
					return redirect;
				}
			}
			return mapping.findForward("exito");
		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en EdicionMapeoTesoreriaAction.doTask()", ex);
			if (tx != null)
			{
				tx.rollback();
			}
			return mapping.findForward("error");
		}
	}
}
