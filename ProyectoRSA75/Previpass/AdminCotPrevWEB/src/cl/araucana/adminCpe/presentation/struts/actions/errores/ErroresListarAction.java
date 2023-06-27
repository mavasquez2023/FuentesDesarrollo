package cl.araucana.adminCpe.presentation.struts.actions.errores;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.TipoCausaMgr;
import cl.araucana.adminCpe.presentation.struts.forms.errores.ErroresListarActionForm;
import cl.araucana.cp.distribuidor.base.Utils;

import com.bh.talon.User;
/*
* @(#) ErroresListarAction.java 1.2 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author malvarez
 * @author cchamblas
 * 
 * @version 1.2
 */
public class ErroresListarAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ErroresListarAction.class);

	public ErroresListarAction() 
	{
		super();
	}
	
	
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ErroresListarActionForm actForm = (ErroresListarActionForm) form;		
		String tipoBusqueda = (String)request.getParameter("_cmd");
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			
			//Instancia los managers correspondientes
			TipoCausaMgr tipoCausaMgr = new TipoCausaMgr(session);
			String descripcion = null;
			if (tipoBusqueda != null){
				if ((String)request.getParameter("_descripcion") == null)
					descripcion = (String)request.getSession().getAttribute("_descripcion");
				else
					descripcion = (String)request.getParameter("_descripcion");
				
				request.getSession().setAttribute("_descripcion", descripcion);
			}
			
			List lista = tipoCausaMgr.getErrores(descripcion);
			request.setAttribute("_cmd", tipoBusqueda);
			
			if(lista.size()>0 && lista != null){
				int pagina = request.getParameter("paginaNumero")!= null ? (Integer.parseInt(request.getParameter("paginaNumero"))) : 1;
				HashMap paginacion = Utils.llenarPaginacionAdmin(pagina, lista);
				actForm.setListaErrores((List)paginacion.get("data"));
				actForm.setNumeroFilas((List)paginacion.get("paginas"));//enlaces listos, llegar y mostrar. llama a funcion JS
			}

			return mapping.findForward("exito");
		} catch (Exception ex) 
		{
			logger.error("Se produjo una excepcion en ListaClasificacionErroresAction.doTask()", ex);			
			return mapping.findForward("error");
		}
	}
}
