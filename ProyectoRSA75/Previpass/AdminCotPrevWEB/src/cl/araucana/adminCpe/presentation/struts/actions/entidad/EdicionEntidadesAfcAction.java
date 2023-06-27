package cl.araucana.adminCpe.presentation.struts.actions.entidad;

import java.util.ArrayList;
import java.util.Iterator;
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
import cl.araucana.adminCpe.presentation.mgr.BancoMgr;
import cl.araucana.adminCpe.presentation.mgr.EntidadesMgr;
import cl.araucana.adminCpe.presentation.mgr.FoliacionMgr;
import cl.araucana.adminCpe.presentation.struts.forms.entidad.EdicionEntidadesAfcActionForm;
import cl.araucana.adminCpe.presentation.struts.javaBeans.LineaEntidadFicha;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadAFCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPagadoraVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPensionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.FoliacionVO;

import com.bh.talon.User;

/*
 * @(#) EdicionEntidadesAfcAction.java 1.17 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author jdelgado
 * @author cchamblas
 * 
 * @version 1.17
 */
public class EdicionEntidadesAfcAction extends AppAction
{
	private static Logger logger = Logger.getLogger(EdicionEntidadesSaludAction.class);

	public EdicionEntidadesAfcAction()
	{
		super();
		this.btns.add("guardar");
		this.btns.add("cancelar");
	}

	/**
	 * edicion entidad Afc
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EdicionEntidadesAfcActionForm actForm = (EdicionEntidadesAfcActionForm) form;

		actForm.setLista(null);

		ActionMessages am = new ActionMessages();
		Session session = null;
		Transaction tx = null;
		try
		{
			Class tipoEntidad = EntidadAFCVO.class;
			int error = 0;
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			FoliacionMgr foliacionMgr = new FoliacionMgr(session);
			BancoMgr bancoMgr = new BancoMgr(session);
			EntidadesMgr entidadesMgr = new EntidadesMgr(session);
			AdminEntidades adminEntidades = new AdminEntidades(session);

			ActionRedirect redirect = new ActionRedirect();
			String accionInterna = request.getParameter("accionInterna");

			if (actForm.getListaFolios() == null)
				actForm.setListaFolios("");
			if (actForm.getCodigoEntidadAntiguo() == 0)
				actForm.setCodigoEntidadAntiguo(-1);
			if (request.getParameter("tipoEdicionSeleccionada") != null)
				actForm.setTipoEdicionSeleccionada(request.getParameter("tipoEdicionSeleccionada"));
			actForm.setTiposEdicion(adminEntidades.getEntidades());
			if (request.getParameter("tipoEdicionSeleccionada") != null)
				actForm.setTipoEdicionSeleccionada(request.getParameter("tipoEdicionSeleccionada"));
			if (request.getParameter("tipoEdicion") != null)
				actForm.setTipoEdicion(request.getParameter("tipoEdicion"));

			EntidadPagadoraVO entidadPagadoraVO = new EntidadPagadoraVO();
			entidadPagadoraVO.setTieneConvenio(actForm.isTieneConvenio());
			entidadPagadoraVO.setImprime(actForm.isImprime());
			if (request.getParameter("idBancoSeleccionado") != null && !request.getParameter("idBancoSeleccionado").equals(""))
			{
				entidadPagadoraVO.setIdCtoBanco(Integer.parseInt(request.getParameter("idBancoSeleccionado")));
				actForm.setIdBanco(Integer.parseInt(request.getParameter("idBancoSeleccionado")));
			}
			if (actForm.getIdCtaCte() == null)
				actForm.setIdCtaCte("0");
			else if (actForm.getIdCtaCte().trim().equals(""))
				actForm.setIdCtaCte("0");
			entidadPagadoraVO.setIdCtaCte(actForm.getIdCtaCte());

			actForm.setListaBancos(bancoMgr.getBancos());

			entidadPagadoraVO.setNombre(actForm.getNombreEntidad());
			if (actForm.getRutEntidad() != null && !actForm.getRutEntidad().equals(""))
			{
				String rut = actForm.getRutEntidad();
				entidadPagadoraVO.setIdEntPagadora(Utils.desFormatMonto(rut.substring(0, rut.length() - 1)));
			}

			//Secccion Datos Nuevos
			if (request.getParameter("idBancoSpl") != null && !request.getParameter("idBancoSpl").equals("")){
				entidadPagadoraVO.setIdBancoSpl(actForm.getIdBancoSpl());
			}
			entidadPagadoraVO.setGeneraCheque(actForm.isGeneraCheque());
			entidadPagadoraVO.setIdCtaCteSpl(actForm.getIdCtaCteSpl());			
			//Fin Seccion Datos Nuevos			
			
			//10/10/2013 GMALLEA NUEVOS CAMPOS FTP
			entidadPagadoraVO.setCorreoContacto(actForm.getCorreoContacto());
			entidadPagadoraVO.setNombreContacto(actForm.getNombreContacto());
			entidadPagadoraVO.setEntidadFTP(actForm.getEntidadFTP());
			entidadPagadoraVO.setCarpetaFTP(actForm.getCarpetaFTP());
			entidadPagadoraVO.setUsuarioFTP(actForm.getUsuarioFTP());
			entidadPagadoraVO.setClaveFTP(actForm.getClaveFTP());
			entidadPagadoraVO.setTipoArchMovimiento(new Integer(actForm.getTipoArchMP()));
			//Fin Seccion Datos Nuevos

			entidadPagadoraVO = adminEntidades.regularizaBanco(entidadPagadoraVO);

			EntidadAFCVO entidadAfcVO = new EntidadAFCVO();
			if (actForm.getCodigoEntidad() != null && !actForm.getCodigoEntidad().equals(""))
				entidadAfcVO.setId(Integer.parseInt(actForm.getCodigoEntidad()));
			else
				entidadAfcVO.setId(-1);
			if (actForm.getCodigoEntidadAntiguo() == -1)
				actForm.setCodigoEntidadAntiguo(entidadAfcVO.getId());
			entidadAfcVO.setNombre(entidadPagadoraVO.getNombre());
			entidadAfcVO.setIdEntPagadora(entidadPagadoraVO.getIdEntPagadora());

			int idEntidadPagadora = entidadPagadoraVO.getIdEntPagadora();

			LineaEntidadFicha folios = new LineaEntidadFicha();
			if (request.getParameter("foliosEnUsos-1") != null && !request.getParameter("foliosEnUsos-1").equals(""))
				folios.setFoliosEnUso(Integer.parseInt(request.getParameter("foliosEnUsos-1")));
			if (request.getParameter("folioInicial-1") != null && !request.getParameter("folioInicial-1").equals(""))
				folios.setFolioInicial(Integer.parseInt(request.getParameter("folioInicial-1")));
			if (request.getParameter("folioFinal-1") != null && !request.getParameter("folioFinal-1").equals(""))
				folios.setFolioFinal(Integer.parseInt(request.getParameter("folioFinal-1")));
			if (request.getParameter("folioActual-1") != null && !request.getParameter("folioActual-1").equals(""))
				folios.setFolioActual(Integer.parseInt(request.getParameter("folioActual-1")));
			String folioBorrar = request.getParameter("folioBorrar");
			actForm.setListaFolios(request.getParameter("listaFolios"));
			actForm.setLista(new ArrayList());

			// Evaluar accionInterna:
			if ("CANCELAR".equals(accionInterna))
			{
				redirect = new ActionRedirect(mapping.findForward("cancelar"));
				return redirect;
			} else if ("GUARDAR".equals(accionInterna)) // actualizar la entidad
			{
				error = adminEntidades.updateEntidadPagadora(false, entidadPagadoraVO);
				if (error == 0)
					error = adminEntidades.updateEntidad(tipoEntidad, actForm.getCodigoEntidadAntiguo(), entidadAfcVO);
				if (error == 0)
				{
					EntidadPensionVO afp = new EntidadPensionVO(entidadAfcVO.getId(), entidadAfcVO.getIdEntPagadora());
					afp.setIdBanco(actForm.getIdBanco());
					afp.setIdCtaCte(actForm.getIdCtaCte());
					afp.setIdAfc(entidadAfcVO.getId());
					error = adminEntidades.updateEntidad(false, EntidadPensionVO.class, actForm.getCodigoEntidadAntiguo(), afp);
				}
				if (error == 0)
				{
					List listaFolios = adminEntidades.actualizaFolios(folios, entidadPagadoraVO.getIdEntPagadora(), actForm.getListaFolios(), true, folioBorrar);
					if (listaFolios == null)
					{
						error = 11;
						actForm.setLista(new ArrayList());
					} else
						actForm.setLista(listaFolios);
					actForm.setListaFolios(adminEntidades.nuevaListaFolios(actForm.getLista()));
					actForm.setLargoFolios(actForm.getLista().size());
				}
				if (error != 0)
				{
					tx.rollback();
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(("error.entidades." + error)));
					this.saveMessages(request, am);
					actForm.setLista(adminEntidades.actualizaFolios(folios, entidadPagadoraVO.getIdEntPagadora(), actForm.getListaFolios(), false, folioBorrar));
					actForm.setListaFolios(adminEntidades.nuevaListaFolios(actForm.getLista()));
					actForm.setLargoFolios(actForm.getLista().size());
					return mapping.findForward("exito");
				}
				am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardada", "Entidad ", entidadPagadoraVO.getNombre().trim()));
				saveMessages(request.getSession(), am);
				tx.commit();
				return mapping.findForward("cancelar");

			} else if ("DEL".equals(accionInterna)) // BORRA EL FOLIO
			{
				actForm.setLista(adminEntidades.actualizaFolios(folios, entidadPagadoraVO.getIdEntPagadora(), actForm.getListaFolios(), false, folioBorrar));
				actForm.setListaFolios(adminEntidades.nuevaListaFolios(actForm.getLista()));
				actForm.setLargoFolios(actForm.getLista().size());
				tx.commit();
				return mapping.findForward("exito");
			} else if ("DEL_ENTIDAD".equals(accionInterna))
			{ // BORRA LA ENTIDAD

				idEntidadPagadora = Integer.parseInt(request.getParameter("idEntPagadora"));
				EntidadPagadoraVO _ent = (EntidadPagadoraVO) entidadesMgr.getEntidadPagadora(idEntidadPagadora);
				EntidadAFCVO _entAfc = (EntidadAFCVO) entidadesMgr.getEntsAFC(idEntidadPagadora);
				String nombre = _ent.getNombre().trim();
				List listaFolios = adminEntidades.actualizaFolios(folios, idEntidadPagadora, "", true, folioBorrar);
				if (listaFolios == null)
				{
					error = 11;
					actForm.setLista(adminEntidades.actualizaFolios(folios, idEntidadPagadora, actForm.getListaFolios(), false, folioBorrar));
				} else
					actForm.setLista(listaFolios);

				actForm.setListaFolios(adminEntidades.nuevaListaFolios(actForm.getLista()));
				actForm.setLargoFolios(actForm.getLista().size());
				if (error == 0)
					error = adminEntidades.delEntidad(tipoEntidad, _entAfc);
				if (error == 0)
					adminEntidades.delEntidadPagadora(_ent);
				if (error != 0)
				{
					tx.rollback();
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(("error.entidades." + error)));
					this.saveMessages(request, am);
					return mapping.findForward("cancelar");
				}

				tx.commit();
				am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.borrada", "Entidad ", nombre));
				saveMessages(request, am);
				return mapping.findForward("cancelar");
			} else if ("SAVE".equals(accionInterna)) // GUARDA LOS FOLIOS al listado
			{
				actForm.setLista(adminEntidades.actualizaFolios(folios, entidadPagadoraVO.getIdEntPagadora(), actForm.getListaFolios(), false, folioBorrar));
				actForm.setListaFolios(adminEntidades.nuevaListaFolios(actForm.getLista()));
				actForm.setLargoFolios(actForm.getLista().size());
				tx.commit();
				return mapping.findForward("exito");
			} else if ("ADD".equals(accionInterna)) // AGREGA UN NUEVO FOLIO
			{
				actForm.setLista(adminEntidades.actualizaFolios(folios, entidadPagadoraVO.getIdEntPagadora(), actForm.getListaFolios(), false, folioBorrar));
				actForm.setListaFolios(adminEntidades.nuevaListaFolios(actForm.getLista()));
				actForm.setLargoFolios(actForm.getLista().size());

				tx.commit();
				return mapping.findForward("exito");
			} else if ("NEW".equals(accionInterna)) // Nueva entidad
			{
				entidadPagadoraVO.setIdCtaCte(Constants.CTA_CTE_FALSA);
				entidadPagadoraVO.setIdCtoBanco(Constants.BANCO_FALSO);
				if (!adminEntidades.saveEntidadPagadora(entidadPagadoraVO, false))
				{
					tx.rollback();
					String rutAux = Utils.formatRut(idEntidadPagadora);
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.existeRut", rutAux));
					saveMessages(request, am);
					actForm.setLista(adminEntidades.actualizaFolios(folios, entidadPagadoraVO.getIdEntPagadora(), actForm.getListaFolios(), false, folioBorrar));
					actForm.setListaFolios(adminEntidades.nuevaListaFolios(actForm.getLista()));
					actForm.setLargoFolios(actForm.getLista().size());
					return mapping.findForward("exito");
				}
				if (!adminEntidades.saveEntidad(tipoEntidad, entidadAfcVO))
				{
					tx.rollback();
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.existe", "una Entidad", ""));
					saveMessages(request, am);
					actForm.setLista(adminEntidades.actualizaFolios(folios, entidadPagadoraVO.getIdEntPagadora(), actForm.getListaFolios(), false, folioBorrar));
					actForm.setListaFolios(adminEntidades.nuevaListaFolios(actForm.getLista()));
					actForm.setLargoFolios(actForm.getLista().size());
					return mapping.findForward("exito");
				}
				EntidadPensionVO afp = new EntidadPensionVO(entidadAfcVO.getId(), entidadAfcVO.getIdEntPagadora());
				afp.setIdBanco(entidadPagadoraVO.getIdCtoBanco());
				afp.setIdCtaCte(entidadPagadoraVO.getIdCtaCte());
				afp.setIdAfc(entidadAfcVO.getId());
				if (!adminEntidades.saveEntidad(EntidadPensionVO.class, afp))
				{
					tx.rollback();
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.entidades.10"));
					saveMessages(request, am);
					actForm.setLista(adminEntidades.actualizaFolios(folios, entidadPagadoraVO.getIdEntPagadora(), actForm.getListaFolios(), false, folioBorrar));
					actForm.setListaFolios(adminEntidades.nuevaListaFolios(actForm.getLista()));
					actForm.setLargoFolios(actForm.getLista().size());
					return mapping.findForward("exito");
				}

				actForm.setLista(adminEntidades.actualizaFolios(folios, entidadPagadoraVO.getIdEntPagadora(), actForm.getListaFolios(), true, folioBorrar));
				actForm.setListaFolios(adminEntidades.nuevaListaFolios(actForm.getLista()));
				actForm.setLargoFolios(actForm.getLista().size());

				tx.commit();
				am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardada", "Entidad ", entidadPagadoraVO.getNombre().trim()));
				saveMessages(request.getSession(), am);
				return mapping.findForward("cancelar");
			} else if ("LOAD".equals(accionInterna)) // recoge la entidad
			{
				actForm.setLista(new ArrayList());
				actForm.setNuevaEntidad(false);
				idEntidadPagadora = Integer.parseInt(request.getParameter("idEntPagadora"));

				entidadAfcVO = entidadesMgr.getEntsAFC(idEntidadPagadora);
				entidadPagadoraVO = entidadesMgr.getEntidadPagadora(idEntidadPagadora);
				if (entidadAfcVO != null && entidadPagadoraVO != null)
				{
					actForm.setIdEntPagadora(entidadAfcVO.getIdEntPagadora());
					actForm.setCodigoEntidad(String.valueOf(entidadAfcVO.getId()).trim());
					actForm.setCodigoEntidadAntiguo(entidadAfcVO.getId());
					actForm.setNombreEntidad(entidadPagadoraVO.getNombre().trim());
					actForm.setTieneConvenio(entidadPagadoraVO.getTieneConvenio());
					actForm.setImprime(entidadPagadoraVO.getImprime());
					actForm.setRutEntidad(idEntidadPagadora + "-" + Utils.generaDV(idEntidadPagadora));
					actForm.setDigitoEntidad(String.valueOf(Utils.generaDV(idEntidadPagadora)));
					actForm.setIdEntPagadora(idEntidadPagadora);
					
					actForm.setIdCtaCteSpl(entidadPagadoraVO.getIdCtaCteSpl());
					
					actForm.setCorreoContacto(entidadPagadoraVO.getCorreoContacto());
					actForm.setNombreContacto(entidadPagadoraVO.getNombreContacto());
					actForm.setEntidadFTP(entidadPagadoraVO.getEntidadFTP());
					actForm.setCarpetaFTP(entidadPagadoraVO.getCarpetaFTP());
					actForm.setUsuarioFTP(entidadPagadoraVO.getUsuarioFTP());
					actForm.setClaveFTP(entidadPagadoraVO.getClaveFTP());
					actForm.setTipoArchMP(entidadPagadoraVO.getTipoArchMovimiento() != null  ? entidadPagadoraVO.getTipoArchMovimiento().intValue() : 0 );
					
				}
				EntidadPensionVO afp = entidadesMgr.getEntsPension(idEntidadPagadora);
				if (afp != null)
				{
					actForm.setIdBanco(afp.getIdBanco());
					actForm.setIdCtaCte(afp.getIdCtaCte().trim());
				}
				List listaFolios = foliacionMgr.getFoliosEntidadPagadora(idEntidadPagadora);
				if (listaFolios.size() > 0)
				{
					for (Iterator itF = listaFolios.iterator(); itF.hasNext();)
					{
						FoliacionVO _folios = (FoliacionVO) itF.next();
						LineaEntidadFicha lEntidad = new LineaEntidadFicha();
						lEntidad.setTipoEdicion(actForm.getTipoEdicion());
						lEntidad.setEntidadPagadora(entidadPagadoraVO);
						lEntidad.setNombre(entidadAfcVO != null ? entidadAfcVO.getNombre().trim() : "");
						lEntidad.setIdEntPagadora(idEntidadPagadora);
						lEntidad.setFolioActual(_folios.getFolioActual());
						lEntidad.setFolioInicial(_folios.getFolioInicial());
						lEntidad.setFolioFinal(_folios.getFolioFinal());
						lEntidad.setIdFoliacion(_folios.getIdFoliacion());
						lEntidad.setFoliosEnUso(_folios.getFoliosEnUso());
						lEntidad.setIdEntidad(entidadAfcVO != null ? entidadAfcVO.getId() : 0);
						actForm.getLista().add(lEntidad);
						actForm.setListaFolios(actForm.getListaFolios() + lEntidad.getIdFoliacion() + "/" + lEntidad.getIdEntPagadora() + "/" + lEntidad.getFoliosEnUso() + "/"
								+ lEntidad.getFolioInicial() + "/" + lEntidad.getFolioFinal() + "/" + lEntidad.getFolioActual() + "*");
					}
				}
				actForm.setListaFolios(adminEntidades.nuevaListaFolios(actForm.getLista()));
				actForm.setLargoFolios(actForm.getLista().size());
				tx.commit();
				return mapping.findForward("exito");
			} else if (actForm.getTipoEdicion().equals("NUEVO"))
			{
				actForm.setNuevaEntidad(true);
				actForm.setCodigoEntidad("");
				actForm.setTieneConvenio(true);
				actForm.setNombreEntidad("");
				actForm.setIdCtaCte("");
				actForm.setImprime(true);
				actForm.setTiposEdicion(adminEntidades.getEntidades());
				actForm.setListaBancos(bancoMgr.getBancos());
				actForm.setIdBanco(0);
				actForm.setRutEntidad("");
				actForm.setDigitoEntidad("");
				
				actForm.setCorreoContacto("");
				actForm.setNombreContacto("");
				actForm.setEntidadFTP("");
				actForm.setCarpetaFTP("");
				actForm.setUsuarioFTP("");
				actForm.setClaveFTP("");
				actForm.setTipoArchMP(0);
				
				actForm.setLista(new ArrayList());
				if (request.getParameter("tipoEdicionSeleccionada") == null)
				{
					actForm.setTipoEdicionSeleccionada("0");
					tx.commit();
					return mapping.findForward("exito");
				}
			}

			return mapping.findForward("exito");
		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en EdicionEntidadesAfcAction.doTask()", ex);
			am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(("error.entidades." + 0)));
			this.saveMessages(request, am);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("cancelar");
		}
	}
}
