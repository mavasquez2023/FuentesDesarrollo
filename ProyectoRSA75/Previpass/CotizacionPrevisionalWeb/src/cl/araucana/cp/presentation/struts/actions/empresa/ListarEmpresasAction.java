package cl.araucana.cp.presentation.struts.actions.empresa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.base.UsuarioCP;
import cl.araucana.cp.presentation.mgr.ConvenioMgr;
import cl.araucana.cp.presentation.mgr.PersonaMgr;
import cl.araucana.cp.presentation.mgr.ProcesoMgr;
import cl.araucana.cp.presentation.struts.forms.ListarEmpresasActionForm;

import com.bh.talon.User;
/*
* @(#) ListarEmpresaAction.java 1.18 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.18
 */
public class ListarEmpresasAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ListarEmpresasAction.class);
	
	public ListarEmpresasAction() 
	{
		super();
		this.btns.add("buscar");
		this.btns.add("imprimir");
		this.btns.add("crearEmpresa");
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
	private void llenaCombosEdicion(ListarEmpresasActionForm actForm, UsuarioCP usuarioCP, Session sesion) throws DaoException
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
	 * Procesa el request para generar la respuesta html que se le entregara
	 * al cliente.
	 * <p>
	 * Los parametros de <code>request</code> que se deben agregar al llamar a este Action son
	 * los siguientes:
	 * <dl>
	 * <dt>accion</dt>
	 * <dd>admin</dd>
	 * <dt>subAccion</dt>
	 * <dd>empresas</dd>
	 * <dt>subSubAccion</dt>
	 * <dd>empresaLista</dd>
	 * </dl> 
	 *
	 * @param	usuario		el usuario que esta loggeado en la sesion en cuyo contexto se llama a este metodo
	 * @param	mapping		el objeto con los mapeos de accion para este <code>Action</code>.
	 * @param	form		el objeto <code>ActionForm</code> correspondiente
	 * @param	request		el objeto <code>request</code> con los parametros para procesar
	 * @param	response	el objeto <code>response</code> con la respuesta al cliente
	 * @return	el mapeo de accion correspondiente
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ListarEmpresasActionForm actForm = (ListarEmpresasActionForm) form; 
		Session session = null;
		Transaction tx = null;
		try 
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			boolean bImprimir = false;
			if (request.getParameter("imprimir") != null)
				bImprimir = true;

			//Carga el combobox de Tipos de Nómina (Procesos)
			ProcesoMgr procesoMgr = new ProcesoMgr(session);
			Collection tiposDeNominas = procesoMgr.getTiposProceso();
			request.getSession().setAttribute("tiposDeNominas", tiposDeNominas);

			UsuarioCP usuarioCP = (UsuarioCP) usuario;
			EmpresaVO empresa;
			PersonaMgr personaMgr = new PersonaMgr(session);

			//Carga el combobox de Convenios
			llenaCombosEdicion(actForm, usuarioCP, session);
			
			String operacion = (request.getParameter("operacion") != null ? request.getParameter("operacion") : "");
			logger.info("ListarEmpresasAction:operacion:" + operacion + "::" + Constants.TXT_BTNS.getProperty("crearEmpresa") + "::" + (operacion.equals(Constants.TXT_BTNS.getProperty("crearEmpresa"))) + "::");
			if (operacion.equals(Constants.TXT_BTNS.getProperty("crearEmpresa"))) 
			{
				ActionRedirect redirect = new ActionRedirect(mapping.findForward("crear"));
				redirect.addParameter("accion", request.getParameter("accion"));
				redirect.addParameter("subAccion", request.getParameter("subAccion"));
				redirect.addParameter("subSubAccion", "empresaCrear");

				logger.info("redireccion a crear empresa");
				tx.commit();

				return redirect;
			}

			HashMap filtros = new HashMap();

			int FLG_Busqueda = 0;
			if (actForm.getRutEmpresa() != null && !actForm.getRutEmpresa().trim().equals(""))
			{
				filtros.put("rutEmpresa", String.valueOf(Utils.desFormatRut(actForm.getRutEmpresa().trim())));
				FLG_Busqueda++;
			}

			if (actForm.getRazonSocial() != null && !actForm.getRazonSocial().trim().equals(""))
			{
				filtros.put("razonSocial", actForm.getRazonSocial().trim().toUpperCase());
				FLG_Busqueda++;
			}

			if (actForm.getTipoProceso() != null && !actForm.getTipoProceso().equals("0"))
			{
				filtros.put("proceso", actForm.getTipoProceso());
				request.setAttribute("procesoOculto", actForm.getTipoProceso());
				FLG_Busqueda++;
			}
			if (actForm.getOpcGrupoConvenio() != null && !actForm.getOpcGrupoConvenio().equals("0"))
			{
				filtros.put("convenio", actForm.getOpcGrupoConvenio());
				request.setAttribute("convenioOculto", actForm.getOpcGrupoConvenio());
				FLG_Busqueda++;
			}
			
			List empresasAdmins = null; 

			if (filtros.isEmpty())
				empresasAdmins = personaMgr.getListaEmpresasIn(usuarioCP.getUnionEmpresasLectura());
			else
				empresasAdmins = personaMgr.getListaEmpresas(usuarioCP.getUnionEmpresasLectura(), filtros);

			Collections.sort(empresasAdmins);
			if (!bImprimir) 
			{
				int pagina = request.getParameter("paginaNumero")!= null ? (Integer.parseInt(request.getParameter("paginaNumero"))) : 1;
				HashMap paginacion = Utils.llenarPaginacionCL(pagina, empresasAdmins);
				actForm.setConsulta((List)paginacion.get("data"));
				actForm.setNumeroFilas((List)paginacion.get("paginas"));//enlaces listos, llegar y mostrar. llama a funcion JS
			} else
				actForm.setConsulta(empresasAdmins);

			//Formatea antes de entregar
			for (Iterator it = actForm.getConsulta().iterator(); it.hasNext();) 
			{				
				empresa = (EmpresaVO) it.next();
				empresa.setRazonSocial(empresa.getRazonSocial().trim());
				empresa.setIdEmpresaFmt(Utils.formatRut(empresa.getIdEmpresa()));
				if (usuarioCP.getEmpresasAdmin().contains(new Integer(empresa.getIdEmpresa())))
					empresa.setEditable(true);
				else
					empresa.setEditable(false);
			}
			Collections.sort(actForm.getConsulta());
			if (this.administrador)
				actForm.setEsAdminEmpresa(true);
			else
				actForm.setEsAdminEmpresa(false);

			request.setAttribute("FLG_Busqueda", String.valueOf(FLG_Busqueda));

			tx.commit();

			if (!bImprimir)
				return mapping.findForward("exito");
			return mapping.findForward("imprimir");
		} catch (Exception ex) 
		{
			logger.error("Se produjo una excepcion en ListarEmpresasAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("error");
		}
	}
}
