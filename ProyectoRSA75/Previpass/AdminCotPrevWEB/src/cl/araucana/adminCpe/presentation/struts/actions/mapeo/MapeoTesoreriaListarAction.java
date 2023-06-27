package cl.araucana.adminCpe.presentation.struts.actions.mapeo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.TesoreriaMgr;
import cl.araucana.adminCpe.presentation.struts.forms.mapeo.MapeoTesoreriaListarActionForm;
import cl.araucana.cp.distribuidor.base.Utils;

import com.bh.talon.User;
/*
* @(#) MapeotesoreriaListarAction.java 1.5 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author malvarez
 * @author cchamblas
 * 
 * @version 1.5
 */
public class MapeoTesoreriaListarAction extends AppAction
{
	private static Logger logger = Logger.getLogger(MapeoTesoreriaListarAction.class);
	
	public MapeoTesoreriaListarAction() 
	{
		super();
	}

	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MapeoTesoreriaListarActionForm actForm = (MapeoTesoreriaListarActionForm) form;
	
		Session session = null;
		try 
		{
			session = HibernateUtil.getSession();
			TesoreriaMgr tesoreriaMgr = new TesoreriaMgr(session);
			
			HttpSession httpSession = request.getSession(true);
					
			List listaTipoSeccion = tesoreriaMgr.getTipoSeccion();
			List listaTipoNomina = tesoreriaMgr.getTipoNomina();
			
			request.setAttribute("listaTipoSeccion", listaTipoSeccion);
			request.setAttribute("listaTipoNomina", listaTipoNomina);
			
			String tipoBusqueda = (String)request.getParameter("_cmd");
			String idTipoNomina = "0";
			long idTipoSeccion = 0;
			
			if (tipoBusqueda != null){
				String tipoNomina = "";
				String tipoSeccion = "";
				if ((String)request.getParameter("_tipoNomina") == null)
					tipoNomina = (String)httpSession.getAttribute("_tipoNomina");
				else
					tipoNomina = (String)request.getParameter("_tipoNomina");
				if ((String)request.getParameter("_tipoSeccion") == null)
					tipoSeccion = (String)httpSession.getAttribute("_tipoSeccion");
				else
					tipoSeccion = (String)request.getParameter("_tipoSeccion");
				
				httpSession.setAttribute("_tipoNomina", tipoNomina);
				httpSession.setAttribute("_tipoSeccion", tipoSeccion);
				
				idTipoNomina = tipoNomina;
				idTipoSeccion = Long.parseLong(tipoSeccion);
			}
			/*if (httpSession.getAttribute("_tipoNomina") != null && httpSession.getAttribute("_tipoSeccion") != null){
				idTipoNomina = (String)httpSession.getAttribute("_tipoNomina");
				idTipoSeccion = Long.parseLong((String) httpSession.getAttribute("_tipoSeccion"));
			}*/
			
			request.setAttribute("_cmd", tipoBusqueda);
			
			int numeroTotalRegistros = tesoreriaMgr.getCantidadRegistros(idTipoSeccion, idTipoNomina);

			if(numeroTotalRegistros > 0)
			{
				int pagina = request.getParameter("paginaNumero")!= null ? (Integer.parseInt(request.getParameter("paginaNumero"))) : 1;				
				List lista  = tesoreriaMgr.getDetalleTesoreria(idTipoSeccion, idTipoNomina);
				HashMap paginacion = Utils.llenarPaginacionAdmin(pagina, lista);
				actForm.setLista((List)paginacion.get("data"));
				actForm.setNumeroFilas((List)paginacion.get("paginas"));
			}
			else
			{
				actForm.setLista(new ArrayList());
				actForm.setNumeroFilas(new ArrayList());
			}
	
			return mapping.findForward("exito");
		} catch (Exception ex) {
			logger.error("Se produjo una excepcion en ListaMapeoTesoreriaActionForm.doTask()", ex);
			return mapping.findForward("error");
		}
	}
}
