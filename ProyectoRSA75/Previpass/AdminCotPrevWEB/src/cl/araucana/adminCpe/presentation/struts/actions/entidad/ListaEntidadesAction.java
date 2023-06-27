package cl.araucana.adminCpe.presentation.struts.actions.entidad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.EntidadesMgr;
import cl.araucana.adminCpe.presentation.mgr.FoliacionMgr;
import cl.araucana.adminCpe.presentation.struts.forms.entidad.ListaEntidadesActionForm;
import cl.araucana.adminCpe.presentation.struts.javaBeans.LineaEntidadFicha;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadAFCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadMutualVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPensionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadSaludVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadSilVO;
import cl.araucana.cp.distribuidor.hibernate.beans.FoliacionVO;

import com.bh.talon.User;

/*
 * @(#) ListaEntidadesAction.java 1.5 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author jdelgado
 * @author cchamblas
 * 
 * @version 1.5
 */
public class ListaEntidadesAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ListaEntidadesAction.class);

	private FoliacionMgr foliacionMgr;

	public ListaEntidadesAction()
	{
		super();
		this.btns.add("imprimir");
	}

	/**
	 * lista entidades
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ListaEntidadesActionForm actForm = (ListaEntidadesActionForm) form;

		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			// Instancia los managers correspondientes
			this.foliacionMgr = new FoliacionMgr(session);

			EntidadesMgr entidadesMgr = new EntidadesMgr(session);

			List entidadSalud = entidadesMgr.getEntsSalud(false);
			Collections.sort(entidadSalud);
			List entidadSIL = entidadesMgr.getEntsSIL();
			Collections.sort(entidadSIL);
			List entidadFondoPension = entidadesMgr.getEntsPension(false);
			Collections.sort(entidadFondoPension);
			List entidadAPV = entidadesMgr.getEntsApvs();
			Collections.sort(entidadAPV);
			List entidadCCAF = entidadesMgr.getEntsCCAF();
			Collections.sort(entidadCCAF);
			List entidadAFC = entidadesMgr.getEntsAFC();
			Collections.sort(entidadAFC);
			List entidadMutual = entidadesMgr.getEntsMutual();
			Collections.sort(entidadMutual);

			EntidadSaludVO entidadSaludVO;
			LineaEntidadFicha lEntidad;
			actForm.setListaEntidadSalud(new ArrayList());
			actForm.getListaEntidadSalud().clear();
			for (Iterator it = entidadSalud.iterator(); it.hasNext();)
			{
				entidadSaludVO = (EntidadSaludVO) it.next();
				String nombre = entidadSaludVO.getNombre();
				if (entidadSaludVO.getIdEntPagadora() > 0)
				{
					List listaFolios = this.foliacionMgr.getFoliosEntidadPagadora(entidadSaludVO.getIdEntPagadora());
					if (listaFolios.size() > 0)
					{
						for (Iterator itF = listaFolios.iterator(); itF.hasNext();)
						{
							FoliacionVO folios = (FoliacionVO) itF.next();
							lEntidad = new LineaEntidadFicha();
							lEntidad.setNombre(nombre);
							nombre = "";
							lEntidad.setIdEntPagadora(entidadSaludVO.getIdEntPagadora());
							lEntidad.setFolioActual(folios.getFolioActual());
							lEntidad.setFolioInicial(folios.getFolioInicial());
							lEntidad.setFolioFinal(folios.getFolioFinal());
							lEntidad.setIdFoliacion(folios.getIdFoliacion());
							lEntidad.setIdEntidad(entidadSaludVO.getId());
							actForm.getListaEntidadSalud().add(lEntidad);
						}
					} else
					{
						lEntidad = new LineaEntidadFicha();
						lEntidad.setNombre(nombre);
						nombre = "";
						lEntidad.setIdEntPagadora(entidadSaludVO.getIdEntPagadora());
						lEntidad.setFolioActual(0);
						lEntidad.setFolioInicial(0);
						lEntidad.setFolioFinal(0);
						lEntidad.setIdFoliacion(0);
						lEntidad.setIdEntidad(entidadSaludVO.getId());
						actForm.getListaEntidadSalud().add(lEntidad);
					}
				}
			}
			
			EntidadSilVO entidadSilVO;
			actForm.setListaEntidadSil(new ArrayList());
			actForm.getListaEntidadSil().clear();
			for (Iterator it = entidadSIL.iterator(); it.hasNext();)
			{
				entidadSilVO = (EntidadSilVO) it.next();
				if (entidadSilVO.getIdEntPagadora() > 0)
				{
					String nombre = entidadSilVO.getNombre();
					List listaFolios = this.foliacionMgr.getFoliosEntidadPagadora(entidadSilVO.getIdEntPagadora());
					if (listaFolios.size() > 0)
					{
						for (Iterator itF = listaFolios.iterator(); itF.hasNext();)
						{
							FoliacionVO folios = (FoliacionVO) itF.next();
							lEntidad = new LineaEntidadFicha();
							lEntidad.setNombre(nombre);
							nombre = "";
							lEntidad.setIdEntPagadora(entidadSilVO.getIdEntPagadora());
							lEntidad.setFolioActual(folios.getFolioActual());
							lEntidad.setFolioInicial(folios.getFolioInicial());
							lEntidad.setFolioFinal(folios.getFolioFinal());
							lEntidad.setIdFoliacion(folios.getIdFoliacion());
							lEntidad.setIdEntidad(entidadSilVO.getId());
							actForm.getListaEntidadSil().add(lEntidad);
						}
					} else
					{
						lEntidad = new LineaEntidadFicha();
						lEntidad.setNombre(nombre);
						nombre = "";
						lEntidad.setIdEntPagadora(entidadSilVO.getIdEntPagadora());
						lEntidad.setFolioActual(0);
						lEntidad.setFolioInicial(0);
						lEntidad.setFolioFinal(0);
						lEntidad.setIdFoliacion(0);
						lEntidad.setIdEntidad(entidadSilVO.getId());
						actForm.getListaEntidadSil().add(lEntidad);
					}
				}
			}
			EntidadPensionVO entidadPensionVO;
			actForm.setListaEntidadFondoPension(new ArrayList());
			actForm.getListaEntidadFondoPension().clear();
			for (Iterator it = entidadFondoPension.iterator(); it.hasNext();)
			{
				entidadPensionVO = (EntidadPensionVO) it.next();
				if (entidadPensionVO.getId() == Constants.ID_AFP_NINGUNA)
					continue;
				String nombre = entidadPensionVO.getNombre();
				List listaFolios = this.foliacionMgr.getFoliosEntidadPagadora(entidadPensionVO.getIdEntPagadora());
				if (listaFolios.size() > 0)
				{
					for (Iterator itF = listaFolios.iterator(); itF.hasNext();)
					{
						FoliacionVO folios = (FoliacionVO) itF.next();
						lEntidad = new LineaEntidadFicha();
						lEntidad.setNombre(nombre);
						nombre = "";
						lEntidad.setIdEntPagadora(entidadPensionVO.getIdEntPagadora());
						lEntidad.setFolioActual(folios.getFolioActual());
						lEntidad.setFolioInicial(folios.getFolioInicial());
						lEntidad.setFolioFinal(folios.getFolioFinal());
						lEntidad.setIdFoliacion(folios.getIdFoliacion());
						lEntidad.setIdEntidad(entidadPensionVO.getId());
						actForm.getListaEntidadFondoPension().add(lEntidad);
					}
				} else
				{
					lEntidad = new LineaEntidadFicha();
					lEntidad.setNombre(nombre);
					nombre = "";
					lEntidad.setIdEntPagadora(entidadPensionVO.getIdEntPagadora());
					lEntidad.setFolioActual(0);
					lEntidad.setFolioInicial(0);
					lEntidad.setFolioFinal(0);
					lEntidad.setIdFoliacion(0);
					lEntidad.setIdEntidad(entidadPensionVO.getId());
					actForm.getListaEntidadFondoPension().add(lEntidad);
				}
			}
			EntidadApvVO entidadApvVO;
			actForm.setListaEntidadApv(new ArrayList());
			actForm.getListaEntidadApv().clear();
			for (Iterator it = entidadAPV.iterator(); it.hasNext();)
			{
				entidadApvVO = (EntidadApvVO) it.next();
				if (entidadApvVO.getIdEntPagadora() > 0)
				{
					String nombre = entidadApvVO.getNombre();
					List listaFolios = this.foliacionMgr.getFoliosEntidadPagadora(entidadApvVO.getIdEntPagadora());
					if (listaFolios.size() > 0)
					{
						for (Iterator itF = listaFolios.iterator(); itF.hasNext();)
						{
							FoliacionVO folios = (FoliacionVO) itF.next();
							lEntidad = new LineaEntidadFicha();
							lEntidad.setNombre(nombre);
							nombre = "";
							lEntidad.setIdEntPagadora(entidadApvVO.getIdEntPagadora());
							lEntidad.setFolioActual(folios.getFolioActual());
							lEntidad.setFolioInicial(folios.getFolioInicial());
							lEntidad.setFolioFinal(folios.getFolioFinal());
							lEntidad.setIdFoliacion(folios.getIdFoliacion());
							lEntidad.setIdEntidad(entidadApvVO.getId());
							actForm.getListaEntidadApv().add(lEntidad);
						}
					} else
					{
						lEntidad = new LineaEntidadFicha();
						lEntidad.setNombre(nombre);
						nombre = "";
						lEntidad.setIdEntPagadora(entidadApvVO.getIdEntPagadora());
						lEntidad.setFolioActual(0);
						lEntidad.setFolioInicial(0);
						lEntidad.setFolioFinal(0);
						lEntidad.setIdFoliacion(0);
						lEntidad.setIdEntidad(entidadApvVO.getId());
						actForm.getListaEntidadApv().add(lEntidad);
					}
				}
			}
			EntidadCCAFVO entidadCCAFVO;
			actForm.setListaEntidadCcaf(new ArrayList());
			actForm.getListaEntidadCcaf().clear();
			for (Iterator it = entidadCCAF.iterator(); it.hasNext();)
			{
				entidadCCAFVO = (EntidadCCAFVO) it.next();
				if (entidadCCAFVO.getIdEntPagadora() > 0)
				{
					String nombre = entidadCCAFVO.getNombre();
					List listaFolios = this.foliacionMgr.getFoliosEntidadPagadora(entidadCCAFVO.getIdEntPagadora());
					if (listaFolios.size() > 0)
					{
						for (Iterator itF = listaFolios.iterator(); itF.hasNext();)
						{
							FoliacionVO folios = (FoliacionVO) itF.next();
							lEntidad = new LineaEntidadFicha();
							lEntidad.setNombre(nombre);
							nombre = "";
							lEntidad.setIdEntPagadora(entidadCCAFVO.getIdEntPagadora());
							lEntidad.setFolioActual(folios.getFolioActual());
							lEntidad.setFolioInicial(folios.getFolioInicial());
							lEntidad.setFolioFinal(folios.getFolioFinal());
							lEntidad.setIdFoliacion(folios.getIdFoliacion());
							lEntidad.setIdEntidad(entidadCCAFVO.getId());
							actForm.getListaEntidadCcaf().add(lEntidad);
						}
					} else
					{
						lEntidad = new LineaEntidadFicha();
						lEntidad.setNombre(nombre);
						nombre = "";
						lEntidad.setIdEntPagadora(entidadCCAFVO.getIdEntPagadora());
						lEntidad.setFolioActual(0);
						lEntidad.setFolioInicial(0);
						lEntidad.setFolioFinal(0);
						lEntidad.setIdFoliacion(0);
						lEntidad.setIdEntidad(entidadCCAFVO.getId());
						actForm.getListaEntidadCcaf().add(lEntidad);
					}
				}
			}
			EntidadAFCVO entidadAFCVO;
			actForm.setListaEntidadAfc(new ArrayList());
			actForm.getListaEntidadAfc().clear();
			for (Iterator it = entidadAFC.iterator(); it.hasNext();)
			{
				entidadAFCVO = (EntidadAFCVO) it.next();
				if (entidadAFCVO.getIdEntPagadora() > 0)
				{
					String nombre = entidadAFCVO.getNombre();
					List listaFolios = this.foliacionMgr.getFoliosEntidadPagadora(entidadAFCVO.getIdEntPagadora());
					if (listaFolios.size() > 0)
					{
						for (Iterator itF = listaFolios.iterator(); itF.hasNext();)
						{
							FoliacionVO folios = (FoliacionVO) itF.next();
							lEntidad = new LineaEntidadFicha();
							lEntidad.setNombre(nombre);
							nombre = "";
							lEntidad.setIdEntPagadora(entidadAFCVO.getIdEntPagadora());
							lEntidad.setFolioActual(folios.getFolioActual());
							lEntidad.setFolioInicial(folios.getFolioInicial());
							lEntidad.setFolioFinal(folios.getFolioFinal());
							lEntidad.setIdFoliacion(folios.getIdFoliacion());
							lEntidad.setIdEntidad(entidadAFCVO.getId());
							actForm.getListaEntidadAfc().add(lEntidad);
						}
					} else
					{
						lEntidad = new LineaEntidadFicha();
						lEntidad.setNombre(nombre);
						nombre = "";
						lEntidad.setIdEntPagadora(entidadAFCVO.getIdEntPagadora());
						lEntidad.setFolioActual(0);
						lEntidad.setFolioInicial(0);
						lEntidad.setFolioFinal(0);
						lEntidad.setIdFoliacion(0);
						lEntidad.setIdEntidad(entidadAFCVO.getId());
						actForm.getListaEntidadAfc().add(lEntidad);
					}
				}
			}
			EntidadMutualVO entidadMutualVO;
			actForm.setListaEntidadMutual(new ArrayList());
			actForm.getListaEntidadMutual().clear();
			for (Iterator it = entidadMutual.iterator(); it.hasNext();)
			{
				entidadMutualVO = (EntidadMutualVO) it.next();
				if (entidadMutualVO.getIdEntPagadora() > 0)
				{
					String nombre = entidadMutualVO.getNombre();
					List listaFolios = this.foliacionMgr.getFoliosEntidadPagadora(entidadMutualVO.getIdEntPagadora());
					if (listaFolios.size() > 0)
					{
						for (Iterator itF = listaFolios.iterator(); itF.hasNext();)
						{
							FoliacionVO folios = (FoliacionVO) itF.next();
							lEntidad = new LineaEntidadFicha();
							lEntidad.setNombre(nombre);
							nombre = "";
							lEntidad.setIdEntPagadora(entidadMutualVO.getIdEntPagadora());
							lEntidad.setFolioActual(folios.getFolioActual());
							lEntidad.setFolioInicial(folios.getFolioInicial());
							lEntidad.setFolioFinal(folios.getFolioFinal());
							lEntidad.setIdFoliacion(folios.getIdFoliacion());
							lEntidad.setIdEntidad(entidadMutualVO.getId());
							actForm.getListaEntidadMutual().add(lEntidad);
						}
					} else
					{
						lEntidad = new LineaEntidadFicha();
						lEntidad.setNombre(nombre);
						nombre = "";
						lEntidad.setIdEntPagadora(entidadMutualVO.getIdEntPagadora());
						lEntidad.setFolioActual(0);
						lEntidad.setFolioInicial(0);
						lEntidad.setFolioFinal(0);
						lEntidad.setIdFoliacion(0);
						lEntidad.setIdEntidad(entidadMutualVO.getId());
						actForm.getListaEntidadMutual().add(lEntidad);
					}
				}
			}
			tx.commit();

			return mapping.findForward("exito");
		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en ListaEntidadesAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("error");
		}
	}
}
