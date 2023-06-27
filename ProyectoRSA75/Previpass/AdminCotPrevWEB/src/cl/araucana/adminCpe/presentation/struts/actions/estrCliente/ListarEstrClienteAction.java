package cl.araucana.adminCpe.presentation.struts.actions.estrCliente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
import cl.araucana.adminCpe.presentation.mgr.ConvenioMgr;
import cl.araucana.adminCpe.presentation.mgr.EmpresaMgr;
import cl.araucana.adminCpe.presentation.mgr.PersonaMgr;
import cl.araucana.adminCpe.presentation.struts.forms.estrCliente.ListarEstrClienteActionForm;
import cl.araucana.adminCpe.presentation.struts.javaBeans.TuplaEstructuraCliente;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

import com.bh.talon.User;

/*
 * @(#) ListarEstrClienteAction.java 1.7 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author malvarez
 * @author acuña
 * 
 * @version 1.7
 */
public class ListarEstrClienteAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ListarEstrClienteAction.class);

	public ListarEstrClienteAction()
	{
		super();
	}

	/**
	 * listar estructura cliente
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ListarEstrClienteActionForm actForm = (ListarEstrClienteActionForm) form;

		Session session = null;
		Transaction tx = null;

		try
		{
			String accionInterna = request.getParameter("accionInterna");
			logger.info("accion:" + accionInterna + "::");
			if (accionInterna != null)
			{
				session = HibernateUtil.getSession();
				tx = session.beginTransaction();
				if (accionInterna.equals("EMPRESA"))
				{
					actForm.setResultado("S");
					if (actForm.getBusquedaEmpresa() != null)
					{
						List tuplas = new ArrayList();
						int idEmpresa;
						String rutTmp = actForm.getBusquedaEmpresa().trim().replaceAll("\\.", "");

						int pos = rutTmp.indexOf('-');
						if (pos > -1)
							rutTmp = rutTmp.substring(0, pos);
						idEmpresa = Integer.parseInt(rutTmp);

						logger.info("---> " + idEmpresa);
						agregaAdministrador(idEmpresa, session, tuplas);
						logger.info("Administrador agregado");
						agregarConvenios(idEmpresa, session, tuplas);
						logger.info("convenio agregado");
						agregaLectores(idEmpresa, tuplas, session, "empresa");
						logger.info("lector agregado");
						actForm.setFilas(tuplas);
					}
				}
				if (accionInterna.equals("GRUPO"))
				{
					if (actForm.getBusquedaGrupo() != null)
					{
						logger.info("busqueda por grupo");
						actForm.setResultado("S");
						ConvenioMgr convenioMgr = new ConvenioMgr(session);
						List tuplas = new ArrayList();
						List tempGrupoConvenio = convenioMgr.getListaGruposConvenioPorEmpresaAgrupado(Integer.parseInt(actForm.getBusquedaGrupo().trim()));
						logger.info("cantidad de convenios :: " + tempGrupoConvenio.size());
						if (tempGrupoConvenio.size() > 0)
						{
							Iterator it = tempGrupoConvenio.iterator();
							while (it.hasNext())
							{
								Integer idEmpres = (Integer) it.next();
								logger.info("rut empresa: " + idEmpres);
								TuplaEstructuraCliente tupla = new TuplaEstructuraCliente();
								tupla.setCargo(" ");
								tupla.setNombre("Empresa :" + Utils.formatRut(idEmpres.intValue()));
								tupla.setTipo("nueva");
								tuplas.add(tupla);
								agregaAdministrador(idEmpres.intValue(), session, tuplas);
								logger.info("Administrador agregado");
								agregarConvenios(idEmpres.intValue(), session, tuplas);
								logger.info("convenio agregado");
								agregaLectores(idEmpres.intValue(), tuplas, session, "grupo");
								logger.info("lector agregado");
								logger.info("tamaño de lista:: " + tuplas.size());
							}

						} else
						{
							TuplaEstructuraCliente tupla = new TuplaEstructuraCliente();
							tupla.setCargo(" ");
							tupla.setNombre("No existen convenios para este Grupo");
							tuplas.add(tupla);
						}
						actForm.setFilas(tuplas);
					}
				}
				tx.commit();
			}
			return mapping.findForward("exito");
		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en ListaEstructuraMovPersonalAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("error");
		}
	}

	/**
	 * agregar administrador
	 * 
	 * @param idEmpresa
	 * @param session
	 * @param tuplas
	 * @throws DaoException
	 */
	private void agregaAdministrador(int idEmpresa, Session session, List tuplas) throws DaoException
	{
		PersonaMgr personaMgr = new PersonaMgr(session);
		EmpresaMgr empresaMgr = new EmpresaMgr(session);
		EmpresaVO empresaVO = empresaMgr.getEmpresaDespliegue(idEmpresa);
		if (empresaVO != null)
		{
			if (empresaVO.getIdAdmin() != null)
			{
				int idAdmin = Integer.parseInt(String.valueOf(empresaVO.getIdAdmin()));
				PersonaVO per = personaMgr.getPersona(idAdmin);
				TuplaEstructuraCliente tupla = addTupla(per, "ADMINISTRADOR");
				tupla.setAcceso("ADMINISTRADOR");
				tuplas.add(tupla);
			}
		} else
		{
			TuplaEstructuraCliente tupla = new TuplaEstructuraCliente();
			tupla.setCargo("ADMINISTRADOR");

			tupla.setNombre("No existen Administradores que cumplan con los criterios especificados");
			tuplas.add(tupla);
		}

	}

	/**
	 * agregar lectores
	 * 
	 * @param id
	 * @param tuplas
	 * @param session
	 * @param tipo
	 * @throws DaoException
	 */
	private void agregaLectores(int id, List tuplas, Session session, String tipo) throws DaoException
	{
		ConvenioMgr convenioMgr = new ConvenioMgr(session);
		logger.info("tipo lector: " + tipo);
		HashMap listaTmp = new HashMap();
		List lista = new ArrayList();
		if (tipo.equals("empresa"))
		{
			List listaLectorPlanilla = convenioMgr.getLectorPlanillasEmpresa(id);
			List listaLectorConvenio = convenioMgr.getLectorPlanillasConvenio(id);
			List listaLectorSucursal = convenioMgr.getLectorPlanillasSucursal(id);
			logger.info("listaLectorPlanilla:: " + listaLectorPlanilla.size());
			if (listaLectorPlanilla.size() > 0)
			{
				for (Iterator it3 = listaLectorPlanilla.iterator(); it3.hasNext();)
				{
					PersonaVO per = (PersonaVO) it3.next();
					if (!listaTmp.containsKey(per.getIdPersona()))
					{
						listaTmp.put(per.getIdPersona(), "a");
						lista.add(addTupla(per, "Lector Empresa"));
					}
				}
				Collections.sort(lista);
				tuplas.addAll(lista);
			} else
			{
				TuplaEstructuraCliente tupla = new TuplaEstructuraCliente();
				tupla.setCargo("Lector Empresa");
				tupla.setNombre("No existen lectores que cumplan con los criterios especificados");
				tupla.setTipo("no");
				tuplas.add(tupla);
			}
			logger.info("listaLectorConvenio:: " + listaLectorConvenio.size());
			if (listaLectorConvenio.size() > 0)
			{
				for (Iterator it3 = listaLectorConvenio.iterator(); it3.hasNext();)
				{
					PersonaVO per = (PersonaVO) it3.next();
					if (!listaTmp.containsKey(per.getIdPersona()))
					{
						listaTmp.put(per.getIdPersona(), "a");
						lista.add(addTupla(per, "Lector Convenio"));
					}
				}
				Collections.sort(lista);
				tuplas.addAll(lista);
			} else
			{
				TuplaEstructuraCliente tupla = new TuplaEstructuraCliente();
				tupla.setCargo("Lector Convenio");
				tupla.setNombre("No existen lectores que cumplan con los criterios especificados");
				tupla.setTipo("no");
				tuplas.add(tupla);
			}
			logger.info("listaLectorSucursal:: " + listaLectorSucursal.size());
			if (listaLectorSucursal.size() > 0)
			{
				for (Iterator it3 = listaLectorSucursal.iterator(); it3.hasNext();)
				{
					PersonaVO per = (PersonaVO) it3.next();
					if (!listaTmp.containsKey(per.getIdPersona()))
					{
						listaTmp.put(per.getIdPersona(), "a");
						lista.add(addTupla(per, "Lector Sucursal"));
					}
				}
				Collections.sort(lista);
				tuplas.addAll(lista);
				Collections.sort(tuplas);
			} else
			{
				TuplaEstructuraCliente tupla = new TuplaEstructuraCliente();
				tupla.setCargo("Lector Sucursal");
				tupla.setNombre("No existen lectores que cumplan con los criterios especificados");
				tupla.setTipo("no");
				tuplas.add(tupla);
			}
		} else
		{
			List listaLectorGrupos = convenioMgr.getLectorPlanillasGrupoConvenio(id);
			if (listaLectorGrupos.size() > 0)
			{
				for (Iterator it3 = listaLectorGrupos.iterator(); it3.hasNext();)
				{
					PersonaVO per = (PersonaVO) it3.next();
					if (!listaTmp.containsKey(per.getIdPersona()))
					{
						listaTmp.put(per.getIdPersona(), "a");
						lista.add(addTupla(per, "Lector Grupo Conv."));
					}
				}
				Collections.sort(lista);
				tuplas.addAll(lista);
			} else
			{
				TuplaEstructuraCliente tupla = new TuplaEstructuraCliente();
				tupla.setCargo("Lector Grupo Conv.");
				tupla.setNombre("No existen lectores que cumplan con los criterios especificados");
				tupla.setTipo("no");
				tuplas.add(tupla);
			}
		}
	}

	/**
	 * agregar convenios
	 * 
	 * @param idEmpresa
	 * @param session
	 * @param tuplas
	 * @throws DaoException
	 */
	private void agregarConvenios(int idEmpresa, Session session, List tuplas) throws DaoException
	{
		ConvenioMgr convenioMgr = new ConvenioMgr(session);
		List listaEncargadoConvenio = convenioMgr.getEncargadoGrupoConvenio(idEmpresa);
		logger.info("listaEncargadoConvenio:: " + listaEncargadoConvenio.size());
		HashMap listaTmp = new HashMap();
		if (listaEncargadoConvenio.size() > 0)
		{
			List lista = new ArrayList();
			for (Iterator it2 = listaEncargadoConvenio.iterator(); it2.hasNext();)
			{
				PersonaVO per = (PersonaVO) it2.next();
				if (!listaTmp.containsKey(per.getIdPersona()))
				{
					listaTmp.put(per.getIdPersona(), "a");
					lista.add(addTupla(per, "Encargado Convenio"));
				}
			}
			Collections.sort(lista);
			tuplas.addAll(lista);
		} else
		{
			TuplaEstructuraCliente tupla = new TuplaEstructuraCliente();
			tupla.setCargo("Encargado Convenio");
			tupla.setNombre("No existen encargados de convenio que cumplan con los criterios especificados");
			tupla.setTipo("no");
			tuplas.add(tupla);
		}
	}

	/**
	 * tupla estructura cliente
	 * 
	 * @param per
	 * @param cargo
	 * @return
	 */
	private TuplaEstructuraCliente addTupla(PersonaVO per, String cargo)
	{
		TuplaEstructuraCliente tupla = new TuplaEstructuraCliente();
		tupla.setNombre(per.getNombres() + " " + per.getApellidoPaterno() + " " + per.getApellidoMaterno());
		tupla.setAcceso(per.getNivelAcceso());
		tupla.setCargo(cargo);
		tupla.setRut(Utils.formatRut(per.getIdPersona().intValue()));
		return tupla;
	}
}
