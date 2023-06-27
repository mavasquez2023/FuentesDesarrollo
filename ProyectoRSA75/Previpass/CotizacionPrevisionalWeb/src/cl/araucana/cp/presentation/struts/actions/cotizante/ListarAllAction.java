package cl.araucana.cp.presentation.struts.actions.cotizante;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.base.UsuarioCP;
import cl.araucana.cp.presentation.mgr.ConceptoMgr;
import cl.araucana.cp.presentation.mgr.ConvenioMgr;
import cl.araucana.cp.presentation.mgr.CotizanteMgr;
import cl.araucana.cp.presentation.mgr.ParametroMgr;
import cl.araucana.cp.presentation.mgr.PersonaMgr;
import cl.araucana.cp.presentation.mgr.ProcesoMgr;
import cl.araucana.cp.presentation.mgr.UsuarioMgr;
import cl.araucana.cp.presentation.struts.forms.cotizante.ListarAllActionForm;
import cl.araucana.cp.presentation.utils.FactoryView;

import com.bh.talon.Message;
import com.bh.talon.MessageList;
import com.bh.talon.User;

/*
 * @(#) ListarAllAction.java 1.25 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.25
 */
public class ListarAllAction extends AppAction
{
	public static final String FORWARD = "trabListaAll";
	static Logger logger = Logger.getLogger(ListarAllAction.class);

	public ListarAllAction()
	{
		super();
		this.btns.add("buscar");
	}

	/**
	 * Método que carga el Combobox con los Convenios
	 * Obtiene los grupos de convenios relacionados con convenios de empresas que el usuario administra o
	 * grupos de convenios sobre los que el usuario tiene permiso de lectura o escritura.
	 * 
	 * @param usuarioCP
	 * @param sesion
	 * @throws DaoException
	 */
	private void llenaCombosEdicion(ListarAllActionForm actForm, UsuarioCP usuarioCP, Session sesion) throws DaoException
	{
		ConvenioMgr convenioMgr = new ConvenioMgr(sesion);
 
		List convenios     = convenioMgr.getConveniosEmpresasIn(usuarioCP.getUnionEmpresasLectura());
		Set gruposConvenio = new HashSet(CollectionUtils.collect(convenios, new Transformer()
		{
			public Object transform(Object input)
			{
				return new Integer(((ConvenioVO) input).getIdGrupoConvenio());
			}
		}));

		// Combo grupos de convenios
		List listaGrupos = convenioMgr.getGruposConveniosIn(gruposConvenio);
		List grupos = new ArrayList();
		GrupoConvenioVO grupo;
		for (Iterator it = listaGrupos.iterator(); it.hasNext();)
		{
			grupo = (GrupoConvenioVO) it.next();
			grupos.add(new LabelValueBean(grupo.getNombre().trim(), Integer.toString(grupo.getIdGrupoConvenio())));
		}
		Collections.sort(grupos, LabelValueBean.CASE_INSENSITIVE_ORDER);
		actForm.setGruposConvenio(grupos);
	}

	/**
	 * cjto convenio acc edicion
	 */
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm formulario, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Transaction tx = null;
		try
		{
			//Carga una página en blanco cuando se selecciona "Nóminas Trabajadores" en el menu lateral izquierdo.
			String auxFlgBlanco = request.getParameter("flgBlanco") == null ? "0" : request.getParameter("flgBlanco");
			if (auxFlgBlanco.equals("1"))
				return mapping.findForward("blanco");

			Date init = new Date();
			String operacion = "";
			if (request.getParameter("operacion") != null)
				operacion = request.getParameter("operacion");	//VALUE del Submit del Formulario
			Session session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			logger.info("ListarAllAction:operacion:" + operacion + "::");
			ListarAllActionForm form = (ListarAllActionForm) formulario;

			HashMap filtros = new HashMap();

			//Búsqueda por Trabajador
			if (form.getRutOculto() != null)
				if (!form.getRutOculto().trim().equals(""))
					filtros.put("idCotizante", new Integer(Utils.desFormatRut(form.getRutOculto().trim())));
			if (form.getNombreOculto() != null)
				if (!form.getNombreOculto().trim().equals(""))
				{
					StringTokenizer tokenizer = new StringTokenizer(form.getNombreOculto().trim().toUpperCase(), " ");
					StringBuffer sb = new StringBuffer(tokenizer.nextToken());
					while (tokenizer.hasMoreTokens())
						sb.append('%').append(tokenizer.nextToken());
					logger.info(sb);
					filtros.put("nombre", sb.toString());
				}
			if (form.getApellidosOculto() != null)
				if (!form.getApellidosOculto().trim().equals(""))
					filtros.put("apellidos", form.getApellidosOculto().trim().toUpperCase());

			UsuarioCP usuarioCP = (UsuarioCP) user;

			CotizanteMgr cotizanteMgr = new CotizanteMgr(session);
			UsuarioMgr usuarioMgr = new UsuarioMgr(session);
			PersonaMgr personaMgr = new PersonaMgr(session);

			String[] tiposProceso;
			Date i = new Date();

			//Búsqueda por Empresa
			int FLG_Nomina = 0;
			if (form.getRutEmpresa() != null && !form.getRutEmpresa().trim().equals(""))
			{
				filtros.put("rutEmpresa",  new Integer(Utils.desFormatRut(form.getRutEmpresa().trim())));
			}

			if (form.getRazonSocial() != null && !form.getRazonSocial().trim().equals(""))
			{
				filtros.put("razonSocial", form.getRazonSocial().trim().toUpperCase());
			}

			if (form.getTipoProceso() != null && !form.getTipoProceso().equals("0"))
			{
				filtros.put("proceso", form.getTipoProceso());
				FLG_Nomina++;
			}

			if (form.getOpcGrupoConvenio() != null && !form.getOpcGrupoConvenio().equals("0"))
			{
				filtros.put("convenio", form.getOpcGrupoConvenio());
			}

			Collection listaEmpresas; 

			if (FLG_Nomina == 0)
			{
				tiposProceso  = cotizanteMgr.getTiposProceso();
				listaEmpresas = personaMgr.getListaEmpresasIn(usuarioCP.getUnionEmpresasLectura());
			} else
			{
				tiposProceso    = new String[1];
				tiposProceso[0] = form.getTipoProceso();
				request.setAttribute("procesoOculto", form.getTipoProceso());
				listaEmpresas   = personaMgr.getListaEmpresas(usuarioCP.getUnionEmpresasLectura(), filtros);
				
				//TODO 31/07/2012 GMALLEA Se agregan a la lista las empresas que tienen solo errores
					List listaEmpresasCausa = personaMgr.getListaEmpresasCausa(usuarioCP.getUnionEmpresasLectura(), filtros);
					
					for(Iterator it = listaEmpresasCausa.iterator() ; it.hasNext() ; ){
						EmpresaVO empresa = (EmpresaVO)it.next();
						if(!listaEmpresas.contains(empresa)){
							listaEmpresas.add(empresa);
						}
						
					}
				}


			ProcesoMgr procesoMgr = new ProcesoMgr(session);

			//Carga el combobox de Tipos de Nómina
			Collection tiposDeNominas = procesoMgr.getTiposProceso();
			request.getSession().setAttribute("tiposDeNominas", tiposDeNominas);

			//Carga el combobox de Convenios
			llenaCombosEdicion(form, usuarioCP, session);

			int FLG_Paginacion = 0;
			if (request.getParameter("FLG_Paginacion") != null)
			{
				FLG_Paginacion = Integer.parseInt(request.getParameter("FLG_Paginacion"));
			}

			if (operacion.equals("") && FLG_Paginacion == 0)
			{
				//Sólo se omite la lista cuando se ingresa a la página desde el Link (operacion = "").
				//operacion también será "" desde los links de paginación, por eso cuando ésta exista FLG_Paginacion será 1 
				form.setMostrarLista("NO");
				return mapping.findForward(FORWARD);
			} else
			{
				form.setMostrarLista("SI");
			}

			int pagina    = request.getParameter("paginaNumero") != null ? (Integer.parseInt(request.getParameter("paginaNumero"))) : 1;
			int primerReg = Constants.NUM_REG_PAG_CL * (pagina - 1);

			HashMap dataTrabajadores = cotizanteMgr.getAllTrabPaginados(pagina, primerReg, ((PersonaVO)user.getUserReference()).getIdPersona().intValue(), filtros);

			HashMap hashTrabajadores       = (HashMap) dataTrabajadores.get("aprobados");
			HashMap hashTrabajadoresAvisos = (HashMap) dataTrabajadores.get("avisos");
			HashMap hashTrabPend           = (HashMap) dataTrabajadores.get("pendientes");

			List listaConvenios = (List) dataTrabajadores.get("listaConvApro");
			List listaConvPend  = (List) dataTrabajadores.get("listaConvPend");

			listaConvenios.addAll(listaConvPend);
			int nPaginas = ((Integer)dataTrabajadores.get("nPaginas")).intValue();
			// guarda solo las nominas que se pueden editar: tp + "#" + rutEmrpesa + "#" + idConvenio
			HashMap nominasEditables = calculaEditables(user, usuarioMgr, listaConvenios, tiposProceso, procesoMgr);

			Date f = new Date();
			logger.info("\n\ntiempo getListaAllTrabs:" + (f.getTime() - i.getTime()) + "::");
			i = new Date();
			f = new Date();
			logger.info("\n\ntiempo getListaAllPend:" + (f.getTime() - i.getTime()) + "::");

			if (hashTrabPend.size() > 0 || hashTrabajadores.size() > 0 || hashTrabajadoresAvisos.size() > 0)
			{
				List listaTrabPend =  new ArrayList();
				FactoryView fv = new FactoryView();

				if (hashTrabPend.size() > 0)
				{
					ConceptoMgr conceptoMgr = new ConceptoMgr(session);
					HashMap hashConceptos = new HashMap();
					HashMap hashMapeo = new HashMap();
					logger.debug("buscando conceptos");
					hashConceptos = conceptoMgr.getHashConceptos(tiposProceso);
					logger.debug("buscando mapeos conceptos");
					hashMapeo = conceptoMgr.getHashMapeosConcepto(tiposProceso, listaConvPend);

					Properties mapConceptos = new Properties();
					mapConceptos.load(getClass().getResourceAsStream("/files/mapeoConceptos.properties"));
					listaTrabPend = fv.trabsToViewAllPend(listaEmpresas, hashTrabPend, hashConceptos, hashMapeo, mapConceptos, nominasEditables);
					Collections.sort(listaTrabPend);
					int cant = Math.min(listaTrabPend.size(), primerReg + Constants.NUM_REG_PAG_CL) - primerReg;
					listaTrabPend = listaTrabPend.subList(primerReg, primerReg + cant);
				}

				List listaTrabajadores = fv.trabsToViewAll(hashTrabajadores, listaEmpresas, nominasEditables);
				List listaTrabajadoresAvisos = fv.trabsToViewAll(hashTrabajadoresAvisos, listaEmpresas, nominasEditables);

				if (listaTrabajadores.size() == 0 && listaTrabajadoresAvisos.size() == 0 && listaTrabPend.size() == 0)
				{
					ActionMessages am = new ActionMessages();
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.sinTrabajadores", ""));
					this.saveMessages(request, am);
					form.setTrabajadores(null);
					return mapping.findForward(FORWARD);
				} else if (listaTrabajadores.size() == 0 && listaTrabajadoresAvisos.size() == 0 && listaTrabPend.size() != 0 && request.getParameter("operacion") != null)
				{
					ActionMessages am = new ActionMessages();
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.trabNoEncontradoBusqAprox"));
					this.saveMessages(request, am);
				} else if ((listaTrabajadores.size() != 0 || listaTrabajadoresAvisos.size() != 0) && listaTrabPend.size() != 0 && request.getParameter("operacion") != null)
				{
					ActionMessages am = new ActionMessages();
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.trabEncontradoBusqAprox"));
					this.saveMessages(request, am);
				}
				logger.info("\n\nn pend:" + listaTrabPend.size() + ":n trab:" + listaTrabajadores.size() + ":n avisos:" + listaTrabajadoresAvisos.size() + "::");

				form.setPendientes(listaTrabPend);
				form.setAvisos(listaTrabajadoresAvisos);
				form.setTrabajadores(listaTrabajadores);
				form.setNumeroFilas(Utils.generaPaginacion(pagina, nPaginas, Constants.NUM_PAG_CL));
			} else
			{
				ActionMessages am = new ActionMessages();
				am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.sinTrabajadores", ""));
				this.saveMessages(request, am);
				form.setTrabajadores(null);
				form.setPendientes(null);
			}
			tx.commit();

			Date fin = new Date();
			logger.info("\n\ninit:" + init + "::");
			logger.info("\nfin:" + fin + "::");
			logger.info("\ndiff:" + (fin.getTime() - init.getTime()) + "::");
			return mapping.findForward(FORWARD);
		} catch (Exception e)
		{
			logger.error("ListarAllAction::", e);
			if (tx != null)
				tx.rollback();

			MessageList l = new MessageList();
			l.add(new Message("Ha ocurrido un Error", e.getMessage()));
			request.setAttribute("messageList", l);
			return mapping.findForward(PARAM_ERROR);
		}
	}

	/**
	 * hace calculos para determinar si es posible o no editar la nomina: 
	 * - fecha actual vs fecha limite edicion nomina (tabla parametro) 
	 * - permisos del usuario logeado (admin empresa, encargado lector/editor) 
	 * - estado de la nomina
	 * 
	 * @param user
	 * @param cotizanteMgr
	 * @param usuarioMgr
	 * @param listaConvenios
	 * @param tiposProceso
	 * @param procesoMgr
	 * @param nominasEditables
	 * @param nominasNivelError
	 * @throws DaoException
	 */
	private HashMap calculaEditables(User user, UsuarioMgr usuarioMgr, Collection listaConvenios, String[] tiposProceso, ProcesoMgr procesoMgr) throws DaoException
	{
		HashMap nominasEditables = new HashMap();
		ParametroMgr parametro = new ParametroMgr(HibernateUtil.getSession());
		int finEdicionNom = parametro.plazoCerrado(Constants.PARAM_FIN_EDICION_NOM);
		for (Iterator it = listaConvenios.iterator(); it.hasNext();)
		{
			ConvenioVO convenio = (ConvenioVO) it.next();
			if (convenio.getHabilitado() != Constants.COD_HABILITACION_CONVENIO)
				continue;
			// si el plazo esta cerrado, asigna permiso lector SIEMPRE
			int idNivelAcceso = (finEdicionNom != 1 ? usuarioMgr.getNivelPermiso(((PersonaVO) user.getUserReference()).getIdPersona().intValue(), convenio.getIdConvenio(), convenio.getIdEmpresa())
					: Constants.NIVEL_ACC_CONV_SUC_LECTOR);
			for (int ii = 0; ii < tiposProceso.length; ii++)
			{
				// edicion nomina
				if (idNivelAcceso == Constants.NIVEL_ACC_CONV_SUC_EDITOR)
				{// esta dentro del plazo de edicion, tiene permisos de editor (admin/encargado), => depende estado de nomina
					NominaVO nomina = procesoMgr.getNomina(tiposProceso[ii], convenio.getIdEmpresa(), convenio.getIdConvenio());
					if (nomina != null && (nomina.getIdEstado() != Constants.EST_NOM_PAGADO				 &&
										   nomina.getIdEstado() != Constants.EST_NOM_PAGADO_PARCIALMENTE &&
										   nomina.getIdEstado() != Constants.EST_NOM_PRECURSADA			 &&
										   nomina.getIdEstado() != Constants.EST_NOM_PREPAGADA))
					{
						logger.info("agregando a nominas editables:" + tiposProceso[ii] + "#" + convenio.getIdEmpresa() + "#" + convenio.getIdConvenio() + ":  estado:" + nomina.getIdEstado() + "::");
						nominasEditables.put(tiposProceso[ii] + "#" + convenio.getIdEmpresa() + "#" + convenio.getIdConvenio(), "1");
					}
				}
			}
		}
		return nominasEditables;
	}
}
