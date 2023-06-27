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
import cl.araucana.adminCpe.presentation.struts.forms.entidad.EdicionEntidadesSilActionForm;
import cl.araucana.adminCpe.presentation.struts.javaBeans.LineaEntidadFicha;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPagadoraVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadSilVO;
import cl.araucana.cp.distribuidor.hibernate.beans.FoliacionVO;

import com.bh.talon.User;

/*
 * @(#) EdicionEntidadesSilAction.java 1.17 10/05/2009
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
public class EdicionEntidadesSilAction extends AppAction
{
	private static Logger logger = Logger.getLogger(EdicionEntidadesSaludAction.class);

	private FoliacionMgr foliacionMgr;
	private BancoMgr bancoMgr;
	private EntidadesMgr entidadesMgr;
	private AdminEntidades adminEntidades;
	private EntidadPagadoraVO entidadPagadoraVO;
	private EntidadSilVO entidadSilVO;
	private int idEntidadPagadora;
	private Class tipoEntidad = EntidadSilVO.class;
	private int error;

	public EdicionEntidadesSilAction()
	{
		super();

		this.btns.add("guardar");
		this.btns.add("cancelar");
	}

	/**
	 * edicion entidad Sil
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EdicionEntidadesSilActionForm actForm = (EdicionEntidadesSilActionForm) form;

		actForm.setLista(null);

		ActionMessages am = new ActionMessages();
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			this.foliacionMgr = new FoliacionMgr(session);
			this.bancoMgr = new BancoMgr(session);
			this.entidadesMgr = new EntidadesMgr(session);
			this.adminEntidades = new AdminEntidades(session);

			ActionRedirect redirect = new ActionRedirect();
			String accionInterna = request.getParameter("accionInterna");

			if (actForm.getListaFolios() == null)
				actForm.setListaFolios("");
			if (actForm.getCodigoEntidadAntiguo() == 0)
				actForm.setCodigoEntidadAntiguo(-1);
			if (request.getParameter("tipoEdicionSeleccionada") != null)
				actForm.setTipoEdicionSeleccionada(request.getParameter("tipoEdicionSeleccionada"));
			actForm.setTiposEdicion(this.adminEntidades.getEntidades());
			if (request.getParameter("tipoEdicionSeleccionada") != null)
				actForm.setTipoEdicionSeleccionada(request.getParameter("tipoEdicionSeleccionada"));
			if (request.getParameter("tipoEdicion") != null)
				actForm.setTipoEdicion(request.getParameter("tipoEdicion"));

			this.entidadPagadoraVO = new EntidadPagadoraVO();
			this.entidadPagadoraVO.setTieneConvenio(actForm.isTieneConvenio());
			this.entidadPagadoraVO.setImprime(actForm.isImprime());
			if (request.getParameter("idBancoSeleccionado") != null && !request.getParameter("idBancoSeleccionado").equals(""))
			{
				this.entidadPagadoraVO.setIdCtoBanco(Integer.parseInt(request.getParameter("idBancoSeleccionado")));
				actForm.setIdBanco(Integer.parseInt(request.getParameter("idBancoSeleccionado")));
			}
			if (actForm.getIdCtaCte() == null)
				actForm.setIdCtaCte("0");
			else if (actForm.getIdCtaCte().trim().equals(""))
				actForm.setIdCtaCte("0");
			this.entidadPagadoraVO.setIdCtaCte(actForm.getIdCtaCte());

			actForm.setListaBancos(this.bancoMgr.getBancos());

			this.entidadPagadoraVO.setNombre(actForm.getNombreEntidad());
			if (actForm.getRutEntidad() != null && !actForm.getRutEntidad().equals(""))
			{
				String rut = actForm.getRutEntidad();
				this.entidadPagadoraVO.setIdEntPagadora(Utils.desFormatMonto(rut.substring(0, rut.length() - 1)));
			}

			//Secccion Datos Nuevos
			if (request.getParameter("idBancoSpl") != null && !request.getParameter("idBancoSpl").equals("")){
				this.entidadPagadoraVO.setIdBancoSpl(actForm.getIdBancoSpl());
			}
			this.entidadPagadoraVO.setGeneraCheque(actForm.isGeneraCheque());
			this.entidadPagadoraVO.setIdCtaCteSpl(actForm.getIdCtaCteSpl());			
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
			

			this.entidadPagadoraVO = this.adminEntidades.regularizaBanco(this.entidadPagadoraVO);

			this.entidadSilVO = new EntidadSilVO();
			if (actForm.getCodigoEntidad() != null && !actForm.getCodigoEntidad().equals(""))
				this.entidadSilVO.setId(Integer.parseInt(actForm.getCodigoEntidad()));
			else
				this.entidadSilVO.setId(-1);
			if (actForm.getCodigoEntidadAntiguo() == -1)
				actForm.setCodigoEntidadAntiguo(this.entidadSilVO.getId());
			this.entidadSilVO.setNombre(this.entidadPagadoraVO.getNombre());
			this.entidadSilVO.setIdEntPagadora(this.entidadPagadoraVO.getIdEntPagadora());

			this.idEntidadPagadora = this.entidadPagadoraVO.getIdEntPagadora();

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
				this.error = this.adminEntidades.updateEntidadPagadora(this.entidadPagadoraVO);
				if (this.error == 0)
					this.error = this.adminEntidades.updateEntidad(this.tipoEntidad, actForm.getCodigoEntidadAntiguo(), this.entidadSilVO);
				if (this.error == 0)
				{
					List listaFolios = this.adminEntidades.actualizaFolios(folios, this.entidadPagadoraVO.getIdEntPagadora(), actForm.getListaFolios(), true, folioBorrar);
					if (listaFolios == null)
					{
						this.error = 11;
						actForm.setLista(new ArrayList());
					} else
					{
						actForm.setLista(listaFolios);
					}
					actForm.setListaFolios(this.adminEntidades.nuevaListaFolios(actForm.getLista()));
					actForm.setLargoFolios(actForm.getLista().size());
				}
				if (this.error != 0)
				{
					tx.rollback();
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(("error.entidades." + this.error)));
					this.saveMessages(request, am);
					actForm.setLista(this.adminEntidades.actualizaFolios(folios, this.entidadPagadoraVO.getIdEntPagadora(), actForm.getListaFolios(), false, folioBorrar));
					actForm.setListaFolios(this.adminEntidades.nuevaListaFolios(actForm.getLista()));
					actForm.setLargoFolios(actForm.getLista().size());
					return mapping.findForward("exito");
				}
				am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardada", "Entidad ", this.entidadPagadoraVO.getNombre().trim()));
				this.saveMessages(request.getSession(), am);
				tx.commit();
				return mapping.findForward("cancelar");

			} else if ("DEL".equals(accionInterna)) // BORRA EL FOLIO
			{
				actForm.setLista(this.adminEntidades.actualizaFolios(folios, this.entidadPagadoraVO.getIdEntPagadora(), actForm.getListaFolios(), false, folioBorrar));
				actForm.setListaFolios(this.adminEntidades.nuevaListaFolios(actForm.getLista()));
				actForm.setLargoFolios(actForm.getLista().size());
				tx.commit();
				return mapping.findForward("exito");
			} else if ("DEL_ENTIDAD".equals(accionInterna))
			{ // BORRA LA ENTIDAD

				this.idEntidadPagadora = Integer.parseInt(request.getParameter("idEntPagadora"));
				EntidadPagadoraVO _ent = (EntidadPagadoraVO) this.entidadesMgr.getEntidadPagadora(this.idEntidadPagadora);
				EntidadSilVO _entSil = (EntidadSilVO) this.entidadesMgr.getEntsSil(this.idEntidadPagadora);
				String nombre = _ent.getNombre().trim();
				List listaFolios = this.adminEntidades.actualizaFolios(folios, this.idEntidadPagadora, "", true, folioBorrar);
				if (listaFolios == null)
				{
					this.error = 11;
					actForm.setLista(this.adminEntidades.actualizaFolios(folios, this.idEntidadPagadora, actForm.getListaFolios(), false, folioBorrar));
				} else
					actForm.setLista(listaFolios);

				actForm.setListaFolios(this.adminEntidades.nuevaListaFolios(actForm.getLista()));
				actForm.setLargoFolios(actForm.getLista().size());
				if (this.error == 0)
					this.error = this.adminEntidades.delEntidad(this.tipoEntidad, _entSil);
				if (this.error == 0)
					this.adminEntidades.delEntidadPagadora(_ent);
				if (this.error != 0)
				{
					tx.rollback();
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.noBorro", "una Entidad", ""));
					this.saveMessages(request, am);
					return mapping.findForward("cancelar");
				}

				tx.commit();
				am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.borrada", "Entidad ", nombre));
				this.saveMessages(request.getSession(), am);
				return mapping.findForward("cancelar");
			} else if ("SAVE".equals(accionInterna)) // GUARDA LOS FOLIOS al listado
			{
				actForm.setLista(this.adminEntidades.actualizaFolios(folios, this.entidadPagadoraVO.getIdEntPagadora(), actForm.getListaFolios(), false, folioBorrar));
				actForm.setListaFolios(this.adminEntidades.nuevaListaFolios(actForm.getLista()));
				actForm.setLargoFolios(actForm.getLista().size());
				tx.commit();
				return mapping.findForward("exito");
			} else if ("ADD".equals(accionInterna)) // AGREGA UN NUEVO FOLIO
			{
				actForm.setLista(this.adminEntidades.actualizaFolios(folios, this.entidadPagadoraVO.getIdEntPagadora(), actForm.getListaFolios(), false, folioBorrar));
				actForm.setListaFolios(this.adminEntidades.nuevaListaFolios(actForm.getLista()));
				actForm.setLargoFolios(actForm.getLista().size());

				tx.commit();
				return mapping.findForward("exito");
			} else if ("NEW".equals(accionInterna)) // Nueva entidad
			{
				if (!this.adminEntidades.saveEntidadPagadora(this.entidadPagadoraVO))
				{
					tx.rollback();
					String rutAux = Utils.formatRut(this.idEntidadPagadora);
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.existeRut", rutAux));
					this.saveMessages(request, am);
					actForm.setLista(this.adminEntidades.actualizaFolios(folios, this.entidadPagadoraVO.getIdEntPagadora(), actForm.getListaFolios(), false, folioBorrar));
					actForm.setListaFolios(this.adminEntidades.nuevaListaFolios(actForm.getLista()));
					actForm.setLargoFolios(actForm.getLista().size());
					return mapping.findForward("exito");
				}
				if (!this.adminEntidades.saveEntidad(this.tipoEntidad, this.entidadSilVO))
				{
					tx.rollback();
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.existe", "Un Código de entidad", ""));
					this.saveMessages(request, am);
					actForm.setLista(this.adminEntidades.actualizaFolios(folios, this.entidadPagadoraVO.getIdEntPagadora(), actForm.getListaFolios(), false, folioBorrar));
					actForm.setListaFolios(this.adminEntidades.nuevaListaFolios(actForm.getLista()));
					actForm.setLargoFolios(actForm.getLista().size());
					return mapping.findForward("exito");
				}

				actForm.setLista(this.adminEntidades.actualizaFolios(folios, this.entidadPagadoraVO.getIdEntPagadora(), actForm.getListaFolios(), true, folioBorrar));
				actForm.setListaFolios(this.adminEntidades.nuevaListaFolios(actForm.getLista()));
				actForm.setLargoFolios(actForm.getLista().size());

				tx.commit();
				am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardada", "Entidad ", this.entidadPagadoraVO.getNombre().trim()));
				this.saveMessages(request.getSession(), am);
				return mapping.findForward("cancelar");
			} else if ("LOAD".equals(accionInterna)) // recoge la entidad
			{
				actForm.setLista(new ArrayList());
				actForm.setNuevaEntidad(false);
				this.idEntidadPagadora = Integer.parseInt(request.getParameter("idEntPagadora"));

				this.entidadSilVO = this.entidadesMgr.getEntsSil(this.idEntidadPagadora);
				this.entidadPagadoraVO = this.entidadesMgr.getEntidadPagadora(this.idEntidadPagadora);
				if (this.entidadSilVO != null && this.entidadPagadoraVO != null)
				{
					actForm.setIdEntPagadora(this.entidadSilVO.getIdEntPagadora());
					actForm.setCodigoEntidad(String.valueOf(this.entidadSilVO.getId()).trim());
					actForm.setCodigoEntidadAntiguo(this.entidadSilVO.getId());
					actForm.setIdBanco(this.entidadPagadoraVO.getIdCtoBanco());
					actForm.setIdCtaCte(this.entidadPagadoraVO.getIdCtaCte().trim());
					actForm.setNombreEntidad(this.entidadPagadoraVO.getNombre().trim());
					actForm.setTieneConvenio(this.entidadPagadoraVO.getTieneConvenio());
					actForm.setImprime(this.entidadPagadoraVO.getImprime());
					actForm.setRutEntidad(this.idEntidadPagadora + "-" + Utils.generaDV(this.idEntidadPagadora));
					actForm.setDigitoEntidad(String.valueOf(Utils.generaDV(this.idEntidadPagadora)));
					actForm.setIdEntPagadora(this.idEntidadPagadora);
					
					actForm.setIdCtaCteSpl(entidadPagadoraVO.getIdCtaCteSpl());
					
					actForm.setCorreoContacto(entidadPagadoraVO.getCorreoContacto());
					actForm.setNombreContacto(entidadPagadoraVO.getNombreContacto());
					actForm.setEntidadFTP(entidadPagadoraVO.getEntidadFTP());
					actForm.setCarpetaFTP(entidadPagadoraVO.getCarpetaFTP());
					actForm.setUsuarioFTP(entidadPagadoraVO.getUsuarioFTP());
					actForm.setClaveFTP(entidadPagadoraVO.getClaveFTP());
					actForm.setTipoArchMP(entidadPagadoraVO.getTipoArchMovimiento() != null  ? entidadPagadoraVO.getTipoArchMovimiento().intValue() : 0 );
					
				}
				List listaFolios = this.foliacionMgr.getFoliosEntidadPagadora(this.idEntidadPagadora);
				if (listaFolios.size() > 0)
				{
					for (Iterator itF = listaFolios.iterator(); itF.hasNext();)
					{
						FoliacionVO _folios = (FoliacionVO) itF.next();
						LineaEntidadFicha lEntidad = new LineaEntidadFicha();
						lEntidad.setTipoEdicion(actForm.getTipoEdicion());
						lEntidad.setEntidadPagadora(this.entidadPagadoraVO);
						lEntidad.setNombre(this.entidadSilVO.getNombre().trim());
						lEntidad.setIdEntPagadora(this.idEntidadPagadora);
						lEntidad.setFolioActual(_folios.getFolioActual());
						lEntidad.setFolioInicial(_folios.getFolioInicial());
						lEntidad.setFolioFinal(_folios.getFolioFinal());
						lEntidad.setIdFoliacion(_folios.getIdFoliacion());
						lEntidad.setFoliosEnUso(_folios.getFoliosEnUso());
						lEntidad.setIdEntidad(this.entidadSilVO.getId());
						actForm.getLista().add(lEntidad);
						actForm.setListaFolios(actForm.getListaFolios() + lEntidad.getIdFoliacion() + "/" + lEntidad.getIdEntPagadora() + "/" + lEntidad.getFoliosEnUso() + "/"
								+ lEntidad.getFolioInicial() + "/" + lEntidad.getFolioFinal() + "/" + lEntidad.getFolioActual() + "*");
					}
				}
				actForm.setListaFolios(this.adminEntidades.nuevaListaFolios(actForm.getLista()));
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
				actForm.setTiposEdicion(this.adminEntidades.getEntidades());
				actForm.setListaBancos(this.bancoMgr.getBancos());
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
			logger.error("Se produjo una excepcion en EdicionEntidadesSilAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("error");
		}
	}
}
