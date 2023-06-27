package cl.araucana.adminCpe.presentation.struts.actions.nodo;

import java.util.List;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.EnvioMgr;
import cl.araucana.adminCpe.presentation.mgr.NodoMgr;
import cl.araucana.adminCpe.presentation.mgr.NodosProcesamientoMgr;
import cl.araucana.adminCpe.presentation.struts.forms.nodo.NodoForm;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.NodoVO;

import com.bh.talon.User;
/*
* @(#) ListarNodoAction.java 1.3 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.3
 */
public class ListarNodoAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ListarNodoAction.class);

	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		NodoForm actForm = (NodoForm) form;
		ActionMessages am = new ActionMessages();
		ActionErrors ae = new ActionErrors(); 
		String mappingFoward = "";
		Transaction tx = null;
		boolean bImprimir = false;
		if (request.getParameter("imprimir") != null)
			bImprimir = true;

		try 
		{
			Session session  = HibernateUtil.getSession();

			//Instancia los managers correspondientes
			NodoMgr nodoMgr = new NodoMgr(session);
			NodoVO nodoVO = new NodoVO();
			String operacion = request.getParameter("operacion");
			logger.info("ListarNodoAction:operacion:" + operacion + "::");

			if (operacion == null || operacion.equals("") || operacion.equals("Cancelar") || bImprimir)
			{
				List listaNodos = nodoMgr.getListaNodos();
				if (!bImprimir) 
				{
					int pagina = request.getParameter("paginaNumero")!= null ? (Integer.parseInt(request.getParameter("paginaNumero"))) : 1;
					HashMap paginacion = Utils.llenarPaginacionAdmin(pagina, listaNodos);
					actForm.setConsulta((List)paginacion.get("data"));
					actForm.setNumeroFilas((List)paginacion.get("paginas"));//enlaces listos, llegar y mostrar. llama a funcion JS
				} else
				{
					logger.info("ListarNodoAction:para imprimir n nodos:" + listaNodos.size() + "::");
					actForm.setConsulta(listaNodos);
				}
				if (!bImprimir)
				{
					mappingFoward = "Listado";
					//request.setAttribute("cambioParam", "Nodos");
				} else
					mappingFoward = "imprimir";
			} else
			{
				if (operacion.equals("Crear_Nodo"))
				{
					actForm.setAccion("Crear");
					mappingFoward = "Crear";
					request.setAttribute("cambioParam", "Creación de Nodo");
				}
				if (request.getParameter("operacion").equals("borrar"))
				{
					if (nodoMgr.getNodo(Integer.valueOf(request.getParameter("idUsuarioBorrar")).intValue()).getDistribuidor() != 0 )
					{
						ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.eliminacionNodoDistribuidor"));
						this.saveErrors(request, ae);
					} else
					{
						if (nodoMgr.getListaNodos().size() > 1)
						{
							EnvioMgr envioMgr = new EnvioMgr(session);
							if ( envioMgr.getNumEnviosPorNodo(Integer.valueOf(request.getParameter("idUsuarioBorrar")).intValue()) == 0)
							{
								tx = session.beginTransaction();
								nodoMgr.eliminaNodo(Integer.valueOf(request.getParameter("idUsuarioBorrar")).intValue());
								tx.commit();

								am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.borrar", "Nodo", request.getParameter("idUsuarioBorrar")));
								this.saveMessages(request, am);
							} else
							{
								ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.eliminacionNodoEnUso"));
								this.saveErrors(request, ae);
							}
						} else
						{
							ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.eliminacionNodoUltimo"));
							this.saveErrors(request, ae);
						}
					}
					List listaNodos = nodoMgr.getListaNodos();
					int pagina = request.getParameter("paginaNumero")!= null ? (Integer.parseInt(request.getParameter("paginaNumero"))) : 1;
					HashMap paginacion = Utils.llenarPaginacionAdmin(pagina, listaNodos);
					actForm.setConsulta((List)paginacion.get("data"));
					actForm.setNumeroFilas((List)paginacion.get("paginas"));//enlaces listos, llegar y mostrar. llama a funcion JS
					mappingFoward = "Listado";
					//request.setAttribute("cambioParam", "Nodos");
				}

				if (operacion.equals("Modificar"))
				{
					nodoVO = nodoMgr.getNodo(Integer.valueOf(request.getParameter("idNodo")).intValue());
					actForm.setAdminPort(nodoVO.getAdminPort());
					actForm.setDesc(nodoVO.getDescripcion().trim());
					actForm.setDistribuidor(nodoVO.getDistribuidor());
					actForm.setHabiliatado(nodoVO.getHabilitado());
					actForm.setHost(nodoVO.getHost().trim());
					actForm.setIdNodo(nodoVO.getIdNodo());
					actForm.setInitial_context_factory(nodoVO.getContextFactory().trim());
					actForm.setNumConDisponibles(nodoVO.getNumConnDisponibles());
					actForm.setNumConMax(nodoVO.getNumConnMaximas());
					actForm.setPort(nodoVO.getPort());
					actForm.setUrl(nodoVO.getUrl().trim());
					actForm.setUsoSistMax(nodoVO.getUsoSistMaximo());
					actForm.setUsoSistMin(nodoVO.getUsoSistMinimo());
					actForm.setAccion("Modificacion");
					mappingFoward = "Modificacion";
					request.setAttribute("cambioParam", "Edición de Nodo");
				}

				if (operacion.equals("Guardar"))
				{
					tx = session.beginTransaction();
					nodoVO.setAdminPort(actForm.getAdminPort());
					nodoVO.setDescripcion(actForm.getDesc());
					nodoVO.setDistribuidor(actForm.getDistribuidor());
					nodoVO.setHabilitado(actForm.getHabiliatado());
					nodoVO.setHost(actForm.getHost());
					nodoVO.setIdNodo(actForm.getIdNodo());
					nodoVO.setContextFactory(actForm.getInitial_context_factory());
					nodoVO.setNumConnDisponibles(actForm.getNumConDisponibles());
					nodoVO.setNumConnMaximas(actForm.getNumConMax());
					nodoVO.setPort(actForm.getPort());
					nodoVO.setUrl(actForm.getUrl());
					nodoVO.setUsoSistMaximo(actForm.getUsoSistMax());
					nodoVO.setUsoSistMinimo(actForm.getUsoSistMin());
					try
					{
						int idNodo = nodoMgr.saveNodo(nodoVO);
						tx.commit();
						nodoVO.setIdNodo(idNodo);
						if (nodoVO.getHabilitado() == Constants.COD_HABILITACION_NODO || nodoVO.getDistribuidor() == Constants.COD_HABILITACION_DISTRIBIDOR)
						{
							NodosProcesamientoMgr npMgr = new NodosProcesamientoMgr(session, usuario.getLogin());
							boolean result = npMgr.actualizaNodo(nodoVO);
							if (result)
							{
								am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardarNodoActualizado", "" + nodoVO.getIdNodo()));
								this.saveMessages(request, am);
							} else
							{
								ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.actualizacionNodo", "" + nodoVO.getIdNodo()));
								this.saveErrors(request, ae);
							}
						} else
						{
							am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardarNodo", "" + nodoVO.getIdNodo()));
							this.saveMessages(request, am);
						}
					} catch(Exception ex)
					{
						ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.guardarNodo", "" + nodoVO.getIdNodo()));
						this.saveErrors(request, ae);
					}

					List listaNodos = nodoMgr.getListaNodos();
					int pagina = request.getParameter("paginaNumero")!= null ? (Integer.parseInt(request.getParameter("paginaNumero"))) : 1;
					HashMap paginacion = Utils.llenarPaginacionAdmin(pagina, listaNodos);
					actForm.setConsulta((List)paginacion.get("data"));
					actForm.setNumeroFilas((List)paginacion.get("paginas"));//enlaces listos, llegar y mostrar. llama a funcion JS
					mappingFoward = "Listado";					
				}
			}
			

			return mapping.findForward(mappingFoward);
		} catch (Exception ex) 
		{
			logger.error("Se produjo una excepcion en ListarNodoAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("error");
		} 
	}
}
