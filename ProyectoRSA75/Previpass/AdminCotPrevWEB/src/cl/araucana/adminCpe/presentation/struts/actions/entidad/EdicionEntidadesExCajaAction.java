package cl.araucana.adminCpe.presentation.struts.actions.entidad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
import cl.araucana.adminCpe.presentation.mgr.EntidadesMgr;
import cl.araucana.adminCpe.presentation.struts.forms.entidad.EdicionEntidadesExCajaActionForm;
import cl.araucana.adminCpe.presentation.struts.javaBeans.LineaEntidadFicha;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadExCajaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RegImpositivoVO;

import com.bh.talon.User;
import com.ibm.wsspi.sib.exitpoint.ra.HashMap;

/*
 * @(#) EdicionEntidadesExCajaAction.java 1.18 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author jdelgado
 * @author cchamblas
 * 
 * @version 1.18
 */
public class EdicionEntidadesExCajaAction extends AppAction
{
	private static Logger logger = Logger.getLogger(EdicionEntidadesExCajaAction.class);

	public EdicionEntidadesExCajaAction()
	{
		super();
	}

	/**
	 * editar entidad ex caja
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EdicionEntidadesExCajaActionForm actForm = (EdicionEntidadesExCajaActionForm) form;
		actForm.setTiposEdicion(null);
		actForm.setOrigen(request.getParameter("origen"));
		String padre = request.getParameter("origen");
		actForm.setRutPadre(padre);
		boolean procesar = false;
		boolean bGuardar = false;
		boolean actualiza = false;
		boolean volverError = false;

		ActionMessages am = new ActionMessages();
		ActionErrors ae = new ActionErrors();
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			EntidadesMgr entidadesMgr = new EntidadesMgr(session);
			if (request.getParameter("tipoEdicion") != null && !("NEW").equals(request.getParameter("accionInterna")) && !("ADD").equals(request.getParameter("accionInterna")))
				if (request.getParameter("tipoEdicion").equals("NUEVO"))
				{
					actForm.setListaEntidadRegimenImpositivo(null);
					actForm.setCantidadRegistros(0);
				}
			ActionRedirect redirect = new ActionRedirect();
			if (request.getParameter("codigoEntidadAntiguo") != null)
				if (!request.getParameter("codigoEntidadAntiguo").equals(""))
					actForm.setCodigoEntidadAntiguo(Integer.parseInt(request.getParameter("codigoEntidadAntiguo")));
				else
				{
					try
					{
						actForm.setCodigoEntidadAntiguo(Integer.parseInt(request.getParameter("idEntidad")));
					} catch (Exception ex)
					{
						actForm.setCodigoEntidadAntiguo(-1);
					}
				}
			else
			{
				try
				{
					actForm.setCodigoEntidadAntiguo(Integer.parseInt(request.getParameter("idEntidad")));
				} catch (Exception ex)
				{
					actForm.setCodigoEntidadAntiguo(-1);
				}
			}
			if (actForm.getListaEntidadRegimenImpositivo() != null)
			{
				String valor = "1";
				int cont = 0;
				if (request.getParameter("codigoIngresado_0") != null)
				{
					actForm.setListaEntidadRegimenImpositivo(new ArrayList());
					while (valor != null)
					{
						valor = request.getParameter("codigoIngresado_" + cont);
						if (valor != null)
						{
							LineaEntidadFicha _linea = new LineaEntidadFicha();
							_linea.setNombre(request.getParameter("nombreIngresado_" + cont).trim());
							_linea.setTasaPension(request.getParameter("tasaIngresado_" + cont).replace('.', ',').trim());
							_linea.setIdCodigo(valor.trim());
							actForm.getListaEntidadRegimenImpositivo().add(_linea);

						}
						cont++;
					}
				}
			}
			if (request.getParameter("accionInterna") != null)
			{
				if (request.getParameter("accionInterna").equals("CANCELAR"))
				{
					redirect = new ActionRedirect(mapping.findForward("cancelar"));
					redirect.addParameter("origen", request.getParameter("origen"));
					return redirect;
				} else if (request.getParameter("accionInterna").equals("GUARDAR"))
				{// GUARDA LA ENTIDAD
					EntidadExCajaVO entidadExCajaVO = new EntidadExCajaVO();

					if (String.valueOf(actForm.getCodigoEntidadAntiguo()).equals(request.getParameter("codigoEntidad")))
					{
						entidadExCajaVO.setId(Integer.parseInt(request.getParameter("codigoEntidad")));
						entidadExCajaVO.setIdEntPagadora(Integer.parseInt(request.getParameter("idEntPagadora")));
						entidadExCajaVO.setNombre(request.getParameter("nombreEntidad"));
						List listaRegimenVO = new LinkedList();
						if (entidadesMgr.actualizaEntidadExCaja(entidadExCajaVO))
						{
							List listaReg = actForm.getListaEntidadRegimenImpositivo();
							if (listaReg != null)
							{
								Iterator iter = listaReg.iterator();
								int i = 0;
								while (iter.hasNext())
								{
									LineaEntidadFicha linea = (LineaEntidadFicha) iter.next();
									RegImpositivoVO reg = new RegImpositivoVO();
									reg.setDescripcion(request.getParameter("nombreIngresado_" + i));
									reg.setTasaPension(Float.parseFloat(request.getParameter("tasaIngresado_" + i).replace(',', '.')));
									reg.setIdEntExCaja(linea.getIdEntidad());
									reg.setIdRegImpositivo(Integer.parseInt(request.getParameter("codigoIngresado_" + i)));
									reg.setIdEntExCaja(entidadExCajaVO.getId());
									listaRegimenVO.add(reg);
									i++;
								}
							}
							if (request.getParameter("ingresoDirecto") != null && request.getParameter("ingresoDirecto").length() > 2)
							{
								RegImpositivoVO reg = new RegImpositivoVO();
								reg.setDescripcion(actForm.getNombreReg());
								reg.setTasaPension(Float.parseFloat(actForm.getTasaReg().replace(',', '.')));
								reg.setIdEntExCaja(entidadExCajaVO.getId());
								reg.setIdRegImpositivo(Integer.parseInt(actForm.getCodigoReg()));
								listaRegimenVO.add(reg);
							}
							int error = entidadesMgr.actualizaRegimen(listaRegimenVO);
							if (error != 0)
							{
								tx.rollback();
								am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.entidades." + error));
								this.saveMessages(request, am);
								return mapping.findForward("exito");
							}
						}
					} else
					{
						int error = entidadesMgr.eliminaRegimen(Integer.parseInt(actForm.getCodigoEntidadInicial()));
						if (error == 0)
							error = entidadesMgr.eliminaEntidadExCaja(Integer.parseInt(actForm.getCodigoEntidadInicial()));

						List listaReg = actForm.getListaEntidadRegimenImpositivo();
						List listaRegimenVO = new LinkedList();

						if (error == 0)
						{
							entidadExCajaVO = new EntidadExCajaVO();
							entidadExCajaVO.setId(Integer.parseInt(request.getParameter("codigoEntidad")));
							String rut = request.getParameter("rutEntidad");
							entidadExCajaVO.setIdEntPagadora(Utils.desFormatRut(rut.substring(0, rut.length() - 1)));

							entidadExCajaVO.setNombre(request.getParameter("nombreEntidad"));

							error = entidadesMgr.guardarEntExCaja(entidadExCajaVO);
							if (error == 0)
							{
								if (listaReg != null)
								{
									Iterator iter = listaReg.iterator();

									int i = 0;
									while (iter.hasNext())
									{
										LineaEntidadFicha linea = (LineaEntidadFicha) iter.next();
										RegImpositivoVO reg = new RegImpositivoVO();
										reg.setDescripcion(request.getParameter("nombreIngresado_" + i));
										reg.setTasaPension(Float.parseFloat(request.getParameter("tasaIngresado_" + i).replace(',', '.')));
										reg.setIdEntExCaja(linea.getIdEntidad());
										reg.setIdRegImpositivo(Integer.parseInt(request.getParameter("codigoIngresado_" + i)));
										reg.setIdEntExCaja(entidadExCajaVO.getId());
										listaRegimenVO.add(reg);
										i++;
									}
								}
							}
							if (request.getParameter("ingresoDirecto") != null && request.getParameter("ingresoDirecto").length() > 2)
							{
								RegImpositivoVO reg = new RegImpositivoVO();
								reg.setDescripcion(actForm.getNombreReg());
								reg.setTasaPension(Float.parseFloat(actForm.getTasaReg().replace(',', '.')));
								reg.setIdEntExCaja(entidadExCajaVO.getId());
								reg.setIdRegImpositivo(Integer.parseInt(actForm.getCodigoReg()));
								listaRegimenVO.add(reg);
							}
							error = entidadesMgr.guardaRegimen(listaRegimenVO);
						}
						if (error != 0)
						{
							tx.rollback();
							am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.entidades." + error));
							this.saveMessages(request, am);
							return mapping.findForward("exito");
						}
					}
					try
					{
						tx.commit();
					} catch (Exception e)
					{
						ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.existe", "una Entidad", ""));
						this.saveErrors(request, ae);
						return mapping.findForward("exito");
					}

					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardada", "Entidad ", entidadExCajaVO.getNombre().trim()));
					this.saveMessages(request.getSession(), am);

					actForm.setCodigoReg("");
					actForm.setNombreReg("");
					actForm.setTasaReg("");
					redirect = new ActionRedirect(mapping.findForward("cancelar"));
					redirect.addParameter("origen", request.getParameter("origen"));
					return redirect;

				} else if (request.getParameter("accionInterna").equals("DEL"))
				{// BORRA EL REGIMEN
					int index = -1;
					if (request.getParameter("RegimenBorrar") != null)
						index = Integer.parseInt(request.getParameter("RegimenBorrar"));
					if (index >= 0)
						actForm.getListaEntidadRegimenImpositivo().remove(index);
					actForm.setCantidadRegistros(actForm.getListaEntidadRegimenImpositivo().size());
					actForm.setMostrarBoton(0);
					tx.commit();
					procesar = true;
				} else if (request.getParameter("accionInterna").equals("DEL_ENTIDAD"))
				{// BORRA LA ENTIDAD
					logger.debug("eliminar entidad ex-caja");
					bGuardar = true;
					int idEntidad = Integer.parseInt(request.getParameter("idEntidad"));
					int error = entidadesMgr.eliminaRegimen(idEntidad);
					if (error == 0)
						error = entidadesMgr.eliminaEntidadExCaja(idEntidad);
					if (error != 0)
					{
						tx.rollback();
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.entidades." + error));
						this.saveMessages(request, am);
						return mapping.findForward("cancelar");
					}
					if (bGuardar)
					{
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.borrar", "Entidad ", ""));
						this.saveMessages(request.getSession(), am);
					}
					procesar = true;

					tx.commit();
					actForm.setCodigoReg("");
					actForm.setNombreReg("");
					actForm.setTasaReg("");
					redirect = new ActionRedirect(mapping.findForward("cancelar"));
					redirect.addParameter("origen", request.getParameter("origen"));

					return redirect;
				} else if (request.getParameter("accionInterna").equals("SAVE"))
				{// GUARDA LOS REG
					logger.debug("guardar registro");
					tx.commit();
					volverError = true;
					procesar = true;
				} else if (request.getParameter("accionInterna").equals("ADD"))
				{// AGREGA UN NUEVO REGIMEN
					logger.debug("agregar registro");
					if (actForm.getListaEntidadRegimenImpositivo() == null)
						actForm.setListaEntidadRegimenImpositivo(new LinkedList());
					LineaEntidadFicha entidad = new LineaEntidadFicha();
					if (request.getParameter("rutEntidad") != null && request.getParameter("rutEntidad").length() > 2)
					{
						String rut = request.getParameter("rutEntidad");
						entidad.setIdEntidad(Utils.desFormatMonto(rut.substring(0, rut.length() - 1)));
					}
					entidad.setNombre(actForm.getNombreReg());
					entidad.setTasaPension(actForm.getTasaReg().replace('.', ','));
					entidad.setIdCodigo(actForm.getCodigoReg());
					HashMap listaRegimen = new HashMap();
					Iterator it = actForm.getListaEntidadRegimenImpositivo().iterator();
					while (it.hasNext())
					{
						LineaEntidadFicha reg = (LineaEntidadFicha) it.next();
						listaRegimen.put(reg.getIdCodigo(), reg);
					}
					if (listaRegimen.get(actForm.getCodigoReg()) == null)
					{
						actForm.getListaEntidadRegimenImpositivo().add(entidad);
						actForm.setNombreReg("");
						actForm.setTasaReg("");
						actForm.setCodigoReg("");
					} else
					{
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.entidades.3"));
						this.saveMessages(request.getSession(), am);
					}
					tx.commit();
					actForm.setMostrarBoton(0);

					if ("NUEVO".equals(request.getParameter("tipoEdicion")))
					{
						actForm.setNuevaEntidad(true);
					}
					redirect = new ActionRedirect(mapping.findForward("refresh"));
					redirect.addParameter("origen", request.getParameter("origen"));
					return redirect;
				} else if (request.getParameter("accionInterna").equals("NEW"))
				{
					EntidadExCajaVO entidadExCajaVO = new EntidadExCajaVO();
					entidadExCajaVO.setId(Integer.parseInt(request.getParameter("codigoEntidad")));

					entidadExCajaVO.setIdEntPagadora(Integer.parseInt(request.getParameter("rutEntidad")));

					entidadExCajaVO.setNombre(request.getParameter("nombreEntidad"));
					int error = entidadesMgr.guardarEntExCaja(entidadExCajaVO);
					if (error != 0)
					{
						tx.rollback();
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.entidades." + error));
						this.saveMessages(request, am);
						return mapping.findForward("exito");
					}
					List listaReg = actForm.getListaEntidadRegimenImpositivo();
					List listaRegimenVO = new LinkedList();
					if (listaReg != null)
					{
						Iterator iter = listaReg.iterator();
						int i = 0;
						while (iter.hasNext())
						{
							LineaEntidadFicha linea = (LineaEntidadFicha) iter.next();
							RegImpositivoVO reg = new RegImpositivoVO();
							reg.setDescripcion(request.getParameter("nombreIngresado_" + i));
							reg.setTasaPension(Float.parseFloat(request.getParameter("tasaIngresado_" + i).replace(',', '.')));
							reg.setIdEntExCaja(linea.getIdEntidad());
							reg.setIdRegImpositivo(Integer.parseInt(request.getParameter("codigoIngresado_" + i)));
							reg.setIdEntExCaja(entidadExCajaVO.getId());
							listaRegimenVO.add(reg);
							i++;
						}
					}
					if (request.getParameter("ingresoDirecto") != null && request.getParameter("ingresoDirecto").length() > 2)
					{
						RegImpositivoVO reg = new RegImpositivoVO();
						reg.setDescripcion(actForm.getNombreReg());
						reg.setTasaPension(Float.parseFloat(actForm.getTasaReg().replace(',', '.')));
						reg.setIdEntExCaja(entidadExCajaVO.getId());
						reg.setIdRegImpositivo(Integer.parseInt(actForm.getCodigoReg()));
						listaRegimenVO.add(reg);
					}
					error = entidadesMgr.guardaRegimen(listaRegimenVO);
					if (error != 0)
					{
						tx.rollback();
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.entidades." + error));
						this.saveMessages(request, am);
						return mapping.findForward("exito");
					}
					try
					{
						tx.commit();
					} catch (Exception e)
					{
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.existe", "una Entidad", ""));
						this.saveErrors(request, am);
						return mapping.findForward("exito");
					}
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardada", "Entidad ", entidadExCajaVO.getNombre().trim()));
					this.saveMessages(request.getSession(), am);
					actForm.setCodigoReg("");
					actForm.setNombreReg("");
					actForm.setTasaReg("");
					redirect = new ActionRedirect(mapping.findForward("cancelar"));
					redirect.addParameter("origen", request.getParameter("origen"));

					return redirect;
				}
			}
			if (volverError)
			{
				if (request.getParameter("tipoEdicion") != null)
				{
					if (request.getParameter("tipoEdicion").equals("NUEVO"))
					{
						actForm.setNuevaEntidad(true);
						actForm.setTiposEdicion(getEntidades());

						actualiza = true;

					}
					if (request.getParameter("codigoEntidad") != null)
						actForm.setCodigoEntidad(request.getParameter("codigoEntidad"));

					if (request.getParameter("nombreEntidad") != null)
						actForm.setNombreEntidad(request.getParameter("nombreEntidad"));

					if (request.getParameter("rutEntidad") != null)
						actForm.setRutEntidad(request.getParameter("rutEntidad"));

				}
				redirect = new ActionRedirect(mapping.findForward("exito"));
				redirect.addParameter("origen", request.getParameter("origen"));
				return redirect;
			}
			if (request.getParameter("tipoEdicion") != null)
			{
				if (request.getParameter("tipoEdicion").equals("EXCAJA"))
				{
					actualiza = true;
					actForm.setNuevaEntidad(false);
				} else if (request.getParameter("tipoEdicion").equals("NUEVO"))
				{
					actForm.setNuevaEntidad(true);
					actForm.setCodigoEntidad("");
					actForm.setTieneConvenio(true);
					actForm.setNombreEntidad("");
					actForm.setMostrarBoton(0);
					actForm.setRutEntidad("");

					redirect = new ActionRedirect(mapping.findForward("exito"));
					redirect.addParameter("origen", request.getParameter("origen"));
					redirect.addParameter("entidadPadre", request.getParameter("origen"));
					return redirect;
				}
			}
			if (actualiza || procesar)
			{
				int idEntPagadora = -1;
				int entidadOrigen = -1;
				int idEntidad = -1;
				if (request.getParameter("origen") != null)
					entidadOrigen = Integer.parseInt(String.valueOf(request.getParameter("origen")));

				if (request.getParameter("idEntPagadora") != null)
					idEntPagadora = Integer.parseInt(String.valueOf(request.getParameter("idEntPagadora")));

				if (request.getParameter("idEntidad") != null)
					idEntidad = Integer.parseInt(String.valueOf(request.getParameter("idEntidad")));

				EntidadExCajaVO entidad = entidadesMgr.getEntExCaja(idEntPagadora, idEntidad);
				if (entidad != null)
				{
					actForm.setEntidadOrigen(Utils.formatMonto(entidadOrigen) + "-" + Utils.generaDV(entidadOrigen));
					actForm.setRutEntidad(Utils.formatMonto(idEntPagadora) + "-" + Utils.generaDV(idEntPagadora));
					actForm.setCodigoEntidad(String.valueOf(entidad.getId()));
					actForm.setCodigoEntidadInicial(String.valueOf(entidad.getId()));
					actForm.setCodigoEntidadAntiguo(entidad.getId());
					actForm.setNombreEntidad(entidad.getNombre().trim());
					String tipo = request.getParameter("tipoEdicion");

					if (tipo != null && !("NUEVO").equals(tipo))
					{
						List entidadRegimenImpositivo = entidadesMgr.getRegImp(Integer.parseInt(request.getParameter("idEntidad")));
						RegImpositivoVO entidadVO;
						LineaEntidadFicha lEntidadReg;
						actForm.setListaEntidadRegimenImpositivo(new ArrayList());
						for (Iterator iter = entidadRegimenImpositivo.iterator(); iter.hasNext();)
						{
							entidadVO = (RegImpositivoVO) iter.next();
							if (entidadVO.getIdEntExCaja() > 0)
							{
								String nombre = entidadVO.getDescripcion().trim();
								lEntidadReg = new LineaEntidadFicha();
								lEntidadReg.setNombre(nombre);
								nombre = "";
								lEntidadReg.setIdEntidad(entidadVO.getIdEntExCaja());
								lEntidadReg.setIdCodigo(String.valueOf(entidadVO.getIdRegImpositivo()));
								lEntidadReg.setTasaPension(String.valueOf(entidadVO.getTasaPension()).replace('.', ','));
								actForm.getListaEntidadRegimenImpositivo().add(lEntidadReg);
								actForm.setMostrarBoton(0);
							}
						}
					} else
					{
						actForm.setListaEntidadRegimenImpositivo(new ArrayList());
					}
					actForm.setCantidadRegistros(actForm.getListaEntidadRegimenImpositivo().size());
				}
			}
			if (procesar == true)
			{
				redirect = new ActionRedirect(mapping.findForward("refresh"));
				if (request.getParameter("tipoEdicion") != null && !request.getParameter("tipoEdicion").equals(""))
					redirect.addParameter("tipoEdicion", request.getParameter("tipoEdicion"));

				if (request.getParameter("idEntPagadora") != null && !request.getParameter("idEntPagadora").equals(""))
					redirect.addParameter("idEntPagadora", request.getParameter("idEntPagadora"));
				redirect.addParameter("origen", request.getParameter("origen"));
				return redirect;
			}
			try
			{
				actForm.setIdEntPagadora(Integer.parseInt(request.getParameter("idEntPagadora")));
			} catch (Exception e)
			{
			}
			redirect = new ActionRedirect(mapping.findForward("exito"));
			redirect.addParameter("origen", request.getParameter("origen"));
			return redirect;
		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en EdicionEntidadesExCajaAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("error");
		}
	}

	/**
	 * lista entidades
	 * 
	 * @return
	 */
	private List getEntidades()
	{
		List listaEntidades = new ArrayList();
		EntidadVO entidades = new EntidadVO();
		entidades.setId(1);
		entidades.setNombre("SALUD");
		listaEntidades.add(entidades);
		entidades = new EntidadVO();
		entidades.setId(2);
		entidades.setNombre("SIL");
		listaEntidades.add(entidades);
		entidades = new EntidadVO();
		entidades.setId(3);
		entidades.setNombre("AFP");
		listaEntidades.add(entidades);
		entidades = new EntidadVO();
		entidades.setId(4);
		entidades.setNombre("APV");
		listaEntidades.add(entidades);
		entidades = new EntidadVO();
		entidades.setId(5);
		entidades.setNombre("CCAF");
		listaEntidades.add(entidades);
		entidades = new EntidadVO();
		entidades.setId(6);
		entidades.setNombre("AFC");
		listaEntidades.add(entidades);
		entidades = new EntidadVO();
		entidades.setId(7);
		entidades.setNombre("MUTUAL");
		listaEntidades.add(entidades);
		return listaEntidades;
	}
}
