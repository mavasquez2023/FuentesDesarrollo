package cl.araucana.cp.presentation.struts.actions.usuarios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

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

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.base.UsuarioCP;
import cl.araucana.cp.presentation.mgr.PersonaMgr;
import cl.araucana.cp.presentation.struts.forms.usuario.ListarUsuariosActionForm;
import cl.araucana.cp.presentation.struts.javaBeans.LineaListaUsuarios;

import com.bh.talon.User;
/*
* @(#) ListarUsuariosAction.java 1.21 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author vagurto
 * 
 * @version 1.21
 */
public class ListarUsuariosAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ListarUsuariosAction.class);

	public ListarUsuariosAction() 
	{
		super();
		this.btns.add("buscar");
		this.btns.add("crearEncargado");
		this.btns.add("imprimir");
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
	 * <dd>usuarioLista</dd>
	 * <dt>operacion</dt>
	 * <dd>Si es "borrar", se eliminara el usuario con del parametro idUsuarioBorrar.
	 * Si es "Crear Usuario", redireccionara a la creacion de usuarios.</dd>
	 * </dl> 
	 *
	 * @param	usuario		el usuario que está loggeado en la sesión en cuyo contexto se llama a este método
	 * @param	mapping		el objeto con los mapeos de accion para este <code>Action</code>
	 * @param	form		el objeto <code>ActionForm</code> correspondiente
	 * @param	request		el objeto <code>request</code> con los parametros para procesar
	 * @param	response	el objeto <code>response</code> con la respuesta al cliente
	 * @return	el mapeo de accion correspondiente
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ListarUsuariosActionForm actForm = (ListarUsuariosActionForm) form;
		Session session = null;
		Transaction tx = null;
		ActionErrors ae = new ActionErrors(); 
		ActionMessages am = new ActionMessages();
		UsuarioCP usuarioCP = (UsuarioCP) usuario; 
		try 
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			//Instancia los managers correspondientes
			PersonaMgr personaMgr = new PersonaMgr(session);
			boolean bImprimir = false;
			logger.info("ListarUsuariosAction.doTask():operacion :" + request.getParameter("operacion") + "::");

			HashMap filtros = new HashMap();
			int FLG_Busqueda = 0;
			if (actForm.getRut() != null && !actForm.getRut().trim().equals(""))
			{
				filtros.put("idEncargado", new Integer(Utils.desFormatRut(actForm.getRut().trim())));
				FLG_Busqueda++;
			}

			if (actForm.getNombre() != null && !actForm.getNombre().trim().equals(""))
			{
				StringTokenizer tokenizer = new StringTokenizer(actForm.getNombre().trim().toUpperCase(), " ");
				StringBuffer sb = new StringBuffer(tokenizer.nextToken());
				while (tokenizer.hasMoreTokens())
					sb.append('%').append(tokenizer.nextToken());
				logger.info(sb);
				filtros.put("nombre", sb.toString());
				FLG_Busqueda++;
			}

			if(actForm.getApellidos() != null && !actForm.getApellidos().trim().equals(""))
			{
				filtros.put("apellidos", actForm.getApellidos().trim().toUpperCase());
				FLG_Busqueda++;
			}

			if (request.getParameter("imprimir") == null) 
			{
				if (request.getParameter("operacion") != null)
				{
					if (request.getParameter("operacion").equals("borrar"))
					{
						int idUsuarioBorrar = Integer.parseInt(request.getParameter("idUsuarioBorrar"));
						personaMgr.borraPermisosEncargado(usuarioCP.getEmpresasAdmin(), idUsuarioBorrar);						
						tx.commit();

						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.borrar", "Usuario", Utils.formatRut(idUsuarioBorrar)));
						ActionRedirect redirect = new ActionRedirect(mapping.findForward("refresh"));
						redirect.addParameter("accion", request.getParameter("accion"));
						redirect.addParameter("subAccion", request.getParameter("subAccion"));
						redirect.addParameter("subSubAccion", request.getParameter("subSubAccion"));

						this.saveMessages(request, am);

						return redirect;
					} else if (request.getParameter("operacion").equals(Constants.TXT_BTNS.getProperty("crearEncargado"))) //Se cambió crearUsuario por crearEncargado
					{
						ActionRedirect redirect = new ActionRedirect(mapping.findForward("crear"));
						redirect.addParameter("accion", request.getParameter("accion"));
						redirect.addParameter("subAccion", request.getParameter("subAccion"));
						redirect.addParameter("subSubAccion", "usuarioCrear");
						redirect.addParameter("rutEmpresa", request.getParameter("rutEmpresa"));
						tx.commit();

						return redirect;
					}
				}
			} else
				bImprimir = true;

			List personas = personaMgr.getListaEncargados(usuarioCP.getEmpresasAdmin(), filtros);

			List personasCut;
			
			if (!bImprimir) 
			{
				int pagina = request.getParameter("paginaNumero")!= null ? (Integer.parseInt(request.getParameter("paginaNumero"))) : 1;
				HashMap paginacion = Utils.llenarPaginacionCL(pagina, personas);
				personasCut = ((List)paginacion.get("data"));
				Collections.sort(personasCut);
				actForm.setNumeroFilas((List)paginacion.get("paginas"));//enlaces listos, llegar y mostrar. llama a funcion JS
			} else
				personasCut = personas;

			//Formatea lo entregado
			List consulta = new ArrayList();
			LineaListaUsuarios linea;
			PersonaVO persona;
			for (Iterator it = personasCut.iterator(); it.hasNext();) 
			{
				persona = (PersonaVO) it.next();
				linea = new LineaListaUsuarios();
				linea.setIdUsuario(persona.getIdPersona().toString());
				linea.setIdUsuarioFmt(Utils.formatRut(persona.getIdPersona().intValue()));
				linea.setNombre(persona.getNombres().trim());
				linea.setApellido(persona.getApellidoPaterno().trim()
						+ " " + persona.getApellidoMaterno().trim());
				consulta.add(linea);
			}
			request.setAttribute("FLG_Busqueda", String.valueOf(FLG_Busqueda));
			actForm.setConsulta(consulta);
			
			tx.commit();
			
			this.saveMessages(request, am);
			this.saveErrors(request, ae);
			
			if (!bImprimir)
				return mapping.findForward("exito");
			return mapping.findForward("imprimir");
		} catch (Exception ex) 
		{
			logger.error("Se produjo una excepcion en ListarUsuariosAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("error");
		}
	}
}
