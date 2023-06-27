package cl.araucana.cp.presentation.struts.actions.empresa;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
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
import org.hibernate.exception.ConstraintViolationException;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SucursalVO;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.base.UsuarioCP;
import cl.araucana.cp.presentation.mgr.EmpresaMgr;
import cl.araucana.cp.presentation.struts.forms.SucursalActionForm;

import com.bh.talon.User;
/*
* @(#) SucursalesListaAction.java 1.17 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author agatica
 * @author vagurto
 * 
 * @version 1.17
 */
public class SucursalesListaAction extends AppAction {

	private static Logger logger = Logger.getLogger(SucursalesListaAction.class);

	public SucursalesListaAction() 
	{
		super();
		this.btns.add("imprimir");
		this.btns.add("crearSucursal");
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
	 * <dd>sucursalesLista</dd>
	 * <dt>rutEmpresa</dt>
	 * <dd>El rut de la empresa (<code>int</code>) para la que se quiere mostrar las sucursales.</dd>
	 * <dt>operacion</dt>
	 * <dd>Si es "borrar", se eliminara la sucursal de la empresa cuyo codigo viene en el parametro 
	 * idEmpresaBorrar. Si es "Crear Sucursal", redireccionara a la creacion de sucursales.</dd>
	 * </dl> 
	 *
	 * @param	usuario		el usuario que esta loggeado en la sesion en cuyo contexto se llama a este metodo
	 * @param	mapping		el objeto con los mapeos de accion para este <code>Action</code>
	 * @param	form		el objeto <code>ActionForm</code> correspondiente
	 * @param	request		el objeto <code>request</code> con los parametros para procesar
	 * @param	response	el objeto <code>response</code> con la respuesta al cliente
	 * @return	el mapeo de accion correspondiente
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		SucursalActionForm actForm = (SucursalActionForm) form;
		
		ActionErrors ae = new ActionErrors(); 
		ActionMessages am = new ActionMessages(); 
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			
			//Instancia los managers correspondientes
			EmpresaMgr empresaMgr = new EmpresaMgr(session);
			
			int rut = -1;
			UsuarioCP usuarioCP = (UsuarioCP) usuario; 
			boolean bImprimir = false;
			
			if (request.getParameter("imprimir") == null) 
			{
				if (request.getParameter("operacion") != null) 
				{
					//Lamado desde dentro
					if (request.getParameter("operacion").equals("borrar")) 
					{
						rut = Integer.parseInt(request.getParameter("idEmpresaBorrar"));
						empresaMgr.borraSucursal(Integer.parseInt(request.getParameter("idEmpresaBorrar")), request.getParameter("idSucursalBorrar"));

						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.borrada", "Sucursal", request.getParameter("idSucursalBorrar")));
						this.saveMessages(request, am);
					} else if (request.getParameter("operacion").equals(Constants.TXT_BTNS.getProperty("crearSucursal"))) 
					{
						ActionRedirect redirect = new ActionRedirect(mapping.findForward("crear"));
						redirect.addParameter("accion", request.getParameter("accion"));
						redirect.addParameter("subAccion", request.getParameter("subAccion"));
						redirect.addParameter("subSubAccion", "sucursalesCrear");
						redirect.addParameter("rutEmpresa", request.getParameter("rutEmpresa"));
						
						tx.commit();
						
						return redirect;
					} else
						rut = Integer.parseInt(request.getParameter("rutEmpresa"));
				} else 
					rut = Integer.parseInt(request.getParameter("rutEmpresa"));
			} else 
			{
				rut = Integer.parseInt(request.getParameter("rutEmpresa"));
				bImprimir = true;
			}

			EmpresaVO empresa = empresaMgr.getEmpresa(rut);
			
			actForm.setNombreEmpresa(empresa.getRazonSocial().trim());
			actForm.setRutEmpresaFmt(Utils.formatRut(empresa.getIdEmpresa()));
			
			//Aplica permisos
			if (usuarioCP.getEmpresasAdmin().contains(new Integer(empresa.getIdEmpresa())))
				//Es administrador de la empresa
				actForm.setPuedeCrear(true);
			else if (usuarioCP.getEmpresasConveniosEditor().contains(new Integer(empresa.getIdEmpresa())))
				//Es editor de convenios de la empresa a la que pertenece la sucursal.
				actForm.setPuedeCrear(true);
			else
				//Es lector de convenios de la empresa a la que pertenece la sucursal.
				actForm.setPuedeCrear(false);
			
			List sucursales = empresaMgr.getSucursales(empresa.getIdEmpresa());
			SucursalVO sucursal;
			for (Iterator it = sucursales.iterator(); it.hasNext();) { 
				sucursal = (SucursalVO) it.next();
				sucursal.setNombre(sucursal.getNombre().trim());
				if (sucursal.getIdSucursal().equals(empresa.getIdCasaMatriz().trim())) {
					sucursal.setEsCasaMatriz("true");
				} else {
					sucursal.setEsCasaMatriz("false");
				}

				if (usuarioCP.getEmpresasAdmin().contains(new Integer(sucursal.getIdEmpresa()))) {
					//Es administrador de la empresa de la sucursal
					sucursal.setEditable(Boolean.TRUE);
					sucursal.setEliminable(Boolean.TRUE);
				} else if (usuarioCP.getEmpresasConveniosEditor().contains(new Integer(sucursal.getIdEmpresa()))) {
					//Es editor de convenios de la empresa a la que pertenece la sucursal.
					sucursal.setEditable(Boolean.TRUE);
					sucursal.setEliminable(Boolean.TRUE);
				} else {
					//Es lector de convenios de la empresa a la que pertenece la sucursal.
					sucursal.setEditable(Boolean.FALSE);
					sucursal.setEliminable(Boolean.FALSE);
				}
			}
			Collections.sort(sucursales);
			if (!bImprimir) 
			{
				int pagina = request.getParameter("paginaNumero")!= null ? (Integer.parseInt(request.getParameter("paginaNumero"))) : 1;
				HashMap paginacion = Utils.llenarPaginacionCL(pagina, sucursales);
				actForm.setConsulta((List)paginacion.get("data"));
				actForm.setNumeroFilas((List)paginacion.get("paginas"));//enlaces listos, llegar y mostrar. llama a funcion JS
			} else
				actForm.setConsulta(sucursales);
									
			actForm.setConsultaSucursales(sucursales);
			
			tx.commit();
			
			if (!bImprimir)
				return mapping.findForward("exito");
			return mapping.findForward("imprimir");
		} catch (Exception ex) 
		{
			logger.error("Se produjo una excepcion en SucursalesListaAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			
			if (ex instanceof ConstraintViolationException) 
			{
				String constraint = obtieneConstraint(ex.getCause().getMessage());
				logger.error(constraint);
				ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.noBorro" + constraint));
			}
			else
				ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.noBorro", "Sucursal"));
			
			ActionRedirect redirect = new ActionRedirect(mapping.findForward("refresh"));
			redirect.addParameter("accion", request.getParameter("accion"));
			redirect.addParameter("subAccion", request.getParameter("subAccion"));
			redirect.addParameter("subSubAccion", request.getParameter("subSubAccion"));
			redirect.addParameter("rutEmpresa", request.getParameter("rutEmpresa"));
			
			this.saveErrors(request, ae);
			
			return redirect;
		}
	}

	/**
	 * obtiene constraint
	 * @param message
	 * @return
	 */
	private String obtieneConstraint(String message) 
	{
		int posicion = message.indexOf(".FK_")+4;
		return message.substring(posicion,posicion + message.substring(posicion).indexOf('"'));
	}
}
