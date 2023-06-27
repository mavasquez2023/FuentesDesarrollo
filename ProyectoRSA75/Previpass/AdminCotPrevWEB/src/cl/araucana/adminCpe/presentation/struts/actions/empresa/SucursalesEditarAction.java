package cl.araucana.adminCpe.presentation.struts.actions.empresa;

import java.util.ArrayList;
import java.util.Collections;
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
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.EmpresaMgr;
import cl.araucana.adminCpe.presentation.mgr.EntidadesMgr;
import cl.araucana.adminCpe.presentation.struts.forms.empresa.SucursalActionForm;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.CiudadVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ComunaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RegionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SucursalVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

import com.bh.talon.User;
/*
* @(#) SucursalesEditarAction.java 1.8 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.8
 */
public class SucursalesEditarAction extends AppAction {

	private static Logger logger = Logger.getLogger(SucursalesEditarAction.class);

	private EmpresaMgr empresaMgr;
	private EntidadesMgr entidadesMgr;
	
	/**
	 * llena combos ediatr sucursal
	 * @param actForm
	 * @param deCombo
	 * @throws DaoException
	 */
	private void llenaCombosEdicion(SucursalActionForm actForm, boolean deCombo)  throws DaoException 
	{	
		List sucursales = new ArrayList();
		List listaSucs = this.empresaMgr.getSucursales(actForm.getRutEmpresa());
		SucursalVO sucursal;
		for (Iterator it = listaSucs.iterator(); it.hasNext();) {
			sucursal = (SucursalVO) it.next();
			sucursales.add(new LabelValueBean(sucursal.getNombre().trim(), sucursal.getIdSucursal()));
		}
		Collections.sort(sucursales, LabelValueBean.CASE_INSENSITIVE_ORDER);
		
		List regiones, ciudades, comunas;
		regiones = new ArrayList();
		ciudades = new ArrayList();
		comunas = new ArrayList();
		
		List listaRegiones = this.entidadesMgr.getRegiones();
		RegionVO region;
		for (Iterator it = listaRegiones.iterator(); it.hasNext();) {
			region = (RegionVO) it.next();
			regiones.add(new LabelValueBean(region.getNombre().trim(), Integer.toString(region.getIdRegion())));
		}

		if (!deCombo) {
			List listaCiudades = this.entidadesMgr.getCiudades(Integer.parseInt(actForm.getOpcRegion()));
			CiudadVO ciudad;
			for (Iterator it = listaCiudades.iterator(); it.hasNext();) {
				ciudad = (CiudadVO) it.next();
				ciudades.add(new LabelValueBean(ciudad.getNombre().trim(), Integer.toString(ciudad.getIdCiudad())));
			}

			List listaComunas = this.entidadesMgr.getComunas(Integer.parseInt(actForm.getOpcCiudad()));
			ComunaVO comuna;
			for (Iterator it = listaComunas.iterator(); it.hasNext();) {
				comuna = (ComunaVO) it.next();
				comunas.add(new LabelValueBean(comuna.getNombre().trim(), Integer.toString(comuna.getIdComuna())));
			}
		} else {
			if (actForm.getOpcRegion().equals("-1")) {
				actForm.setOpcCiudad("-1");
				actForm.setOpcComuna("-1");
			} else {
				List listaCiudades = this.entidadesMgr.getCiudades(Integer.parseInt(actForm.getOpcRegion()));
				CiudadVO ciudad;
				for (Iterator it = listaCiudades.iterator(); it.hasNext();) {
					ciudad = (CiudadVO) it.next();
					ciudades.add(new LabelValueBean(ciudad.getNombre().trim(), Integer.toString(ciudad.getIdCiudad())));
				}

				if (actForm.getOpcCiudad().equals("-1") && actForm.getOpcComuna().equals("-1")) {
					//Usuario cambio region
					actForm.setOpcCiudad("-1");
				} else if (actForm.getOpcComuna().equals("-1")) {
					//Usuario cambio ciudad
					List listaComunas = this.entidadesMgr.getComunas(Integer.parseInt(actForm.getOpcCiudad()));
					ComunaVO comuna;
					for (Iterator it = listaComunas.iterator(); it.hasNext();) {
						comuna = (ComunaVO) it.next();
						comunas.add(new LabelValueBean(comuna.getNombre().trim(), Integer.toString(comuna.getIdComuna())));
					}
					actForm.setOpcComuna("-1");
				}
			}
		}
		actForm.setSucursales(sucursales);
		actForm.setRegiones(regiones);
		actForm.setCiudades(ciudades);
		actForm.setComunas(comunas);
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
	 * <dd>Si este parametro es sucursalesCrear, se muestra la creacion de una nueva sucursal.
	 * Si el paramtero es sucursalesEditar, se muestra la edicion de una sucursal existente.</dd>
	 * <dt>rutEmpresa</dt>
	 * <dd>El rut de la empresa (<code>int</code>) a la que pertenece la sucursal que se quiere editar/mostrar.</dd>
	 * <dt>idSucursal</dt>
	 * <dd>La id de la sucursal que se desea editar.</dd>
	 * <dt>combosLugaresClick</dt>
	 * <dd>Opcional. Tiene valor "true" si el request se origino al cambiar uno de los combos de seleccion
	 * de Region o Ciudad. Esto cargara los siguientes combos de lugares.</dd>
	 * <dt>operacion</dt>
	 * <dd>Si es "Editar", se redireccionara a la edicion de la sucursal idSucursal de la empresa rutEmpresa. 
	 * Si es "Guardar, se guarda el contenido del formulario en la base de datos.
	 * Si es "Cancelar", se redirecciona a la lista de sucursales para la empresa rutEmpresa.</dd>
	 * </dl> 
	 *
	 * @param	usuario		el usuario que esta loggeado en la sesion en cuyo contexto se llama a este metodo
	 * @param	mapping		el objeto con los mapeos de accion para este <code>Action</code>
	 * @param	form		el objeto <code>ActionForm</code> correspondiente
	 * @param	request		el objeto <code>request</code> con los parametros para procesar
	 * @param	response	el objeto <code>response</code> con la respuesta al cliente
	 * @return	el mapeo de accion correspondiente
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SucursalActionForm actForm = (SucursalActionForm) form;
		
		ActionErrors ae = new ActionErrors(); 
		ActionMessages am = new ActionMessages(); 
		Session session = null;
		Transaction tx = null;
		boolean bGuardar = false;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			
			//Instancia los managers correspondientes
			this.empresaMgr = new EmpresaMgr(session);
			this.entidadesMgr = new EntidadesMgr(session); 
			
			int rut = -1;
			String idSucursal = "";
			String tipoOper = "";
			boolean bVaciar = true;
			if (request.getParameter("subSubAccion").equals("sucursalesEditar"))
				tipoOper = "editar";
			else if (request.getParameter("subSubAccion").equals("sucursalesFicha"))
				tipoOper = "ficha";
			else if (request.getParameter("subSubAccion").equals("sucursalesCrear"))
				tipoOper = "crear";

			if (request.getParameter("operacion") == null) {
				if (request.getParameter("idSucursal") == null) {
					if ((request.getParameter("combosLugaresClick") != null) && request.getParameter("combosLugaresClick").equals("true")) {
						//Usuario cambio los combos de region o ciudad
						llenaCombosEdicion(actForm, true);
						tx.commit();

						actForm.setTipoOper(tipoOper);
						return mapping.findForward("editar");
					}
					rut = Integer.parseInt(request.getParameter("rutEmpresa"));
						idSucursal = actForm.getOpcSucursal();
				} else 
				{
					rut = Integer.parseInt(request.getParameter("rutEmpresa"));
					idSucursal = request.getParameter("idSucursal");
				}
			} else {
				//Lamado desde dentro
				if (request.getParameter("operacion").equals("Editar")) {
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("editarRedir"));
					redirect.addParameter("accion", request.getParameter("accion"));
					redirect.addParameter("subAccion", request.getParameter("subAccion"));
					redirect.addParameter("subSubAccion", "sucursalesEditar");
					redirect.addParameter("rutEmpresa", request.getParameter("rutEmpresa"));
					redirect.addParameter("idSucursal", actForm.getOpcSucursal());
					
					tx.commit();
					
					return redirect;
				} else if (request.getParameter("operacion").equals("Guardar")) {
					bGuardar = true;
					
					rut = Integer.parseInt(request.getParameter("rutEmpresa"));
					idSucursal = actForm.getOpcSucursal(); 
					
					SucursalVO sucursal;
					if (!tipoOper.equals("crear")) {
						sucursal = this.empresaMgr.getSucursal(rut, idSucursal);
					} else { 
						sucursal = new SucursalVO();
						sucursal.setIdEmpresa(rut);
						sucursal.setIdSucursal(actForm.getCodigo().trim());
					}
					
					sucursal.setNombre(actForm.getNombre() == null ? "" : actForm.getNombre().trim());
					sucursal.setDireccion(actForm.getDireccion() == null ? "" : actForm.getDireccion().trim());
					sucursal.setNumero(actForm.getNumero() == null ? "" : actForm.getNumero().trim());
					sucursal.setDepartamento(actForm.getDpto() == null ? "" : actForm.getDpto().trim());
					if ((actForm.getOpcComuna() == null) || (Integer.parseInt(actForm.getOpcComuna()) == -1))
						sucursal.setComuna(this.entidadesMgr.getComuna(Constants.ID_COMUNA_DEFAULT));
					else
						sucursal.setComuna(this.entidadesMgr.getComuna(Integer.parseInt(actForm.getOpcComuna())));
					sucursal.setEmail(actForm.getEmail() == null ? "" : actForm.getEmail().trim());
					sucursal.setTelefono(actForm.getFono() == null && actForm.getCodigoFono() == null ? "" :"("+actForm.getCodigoFono()+")"+ actForm.getFono().trim());
					sucursal.setCelular(actForm.getCelular().trim().equals("") ? 0 : Integer.parseInt(actForm.getCelular().trim()));
					if(actForm.getFax()!=null&&actForm.getCodigoFax()!=null)
						sucursal.setFax(actForm.getFax().length()==0 && actForm.getCodigoFax().length()==0 ? "" :"("+actForm.getCodigoFax()+")"+ actForm.getFax().trim());

					if (!tipoOper.equals("crear")) {
						this.empresaMgr.guardaSucursal(sucursal);
					} else {
						SucursalVO sucursalP = this.empresaMgr.getSucursal(sucursal.getIdEmpresa(), sucursal.getIdSucursal().trim());
						if (sucursalP == null) {
							this.empresaMgr.saveSucursal(sucursal);
						} else {
							llenaCombosEdicion(actForm, false);
							
							tx.commit();
							
							ae = new ActionErrors();
							ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.existe", "Sucursal"));
							this.saveErrors(request, ae);
							
							actForm.setTipoOper(tipoOper);
							return mapping.findForward(tipoOper);
						}
					}

					String idSucu = sucursal.getIdSucursal().trim();
					tx.commit();
					
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("Cancelar"));
					redirect.addParameter("accion", request.getParameter("accion"));
					redirect.addParameter("subAccion", request.getParameter("subAccion"));
					redirect.addParameter("subSubAccion", request.getParameter("sucursalesLista"));
					redirect.addParameter("rutEmpresa", request.getParameter("rutEmpresa"));
					
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardada", "Sucursal", idSucu));
					this.saveMessages(request.getSession(), am);
					
					return redirect;

				} else if (request.getParameter("operacion").equals("Cancelar")) {
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("Cancelar"));
					redirect.addParameter("accion", "admin");
					redirect.addParameter("subAccion", "empresas");
					redirect.addParameter("subSubAccion", "sucursalesLista");
					redirect.addParameter("rutEmpresa", request.getParameter("rutEmpresa"));

					return redirect;
				}
			}
			
			actForm.setRutEmpresaFmt(Utils.formatRut(rut));
			
			EmpresaVO empresa = this.empresaMgr.getEmpresa(rut);
			SucursalVO sucursal = new SucursalVO();
			if (!tipoOper.equals("crear")) {
				sucursal = this.empresaMgr.getSucursal(rut, idSucursal);
			
				//Llena los datos para mostrar/editar la empresa
				actForm.setRutEmpresaFmt(Utils.formatRut(empresa.getIdEmpresa()));
				actForm.setNombreEmpresa(empresa.getRazonSocial().trim());
				
				//Llena los datos de la sucursal
				actForm.setCodigo(sucursal.getIdSucursal().trim());
				actForm.setOpcSucursal(sucursal.getIdSucursal().trim());
				actForm.setNombre(sucursal.getNombre().trim());
				actForm.setDireccion(sucursal.getDireccion().trim());
				actForm.setNumero(sucursal.getNumero().trim());
				actForm.setDpto(sucursal.getDepartamento().trim());
				actForm.setOpcComuna(Integer.toString(sucursal.getComuna().getIdComuna()));
				actForm.setNombreComuna(sucursal.getComuna().getNombre().trim());
				actForm.setOpcCiudad(Integer.toString(sucursal.getComuna().getCiudad().getIdCiudad()));
				actForm.setNombreCiudad(sucursal.getComuna().getCiudad().getNombre().trim());
				actForm.setOpcRegion(Integer.toString(sucursal.getComuna().getCiudad().getRegion().getIdRegion()));
				actForm.setNombreRegion(sucursal.getComuna().getCiudad().getRegion().getNombre().trim());
				actForm.setEmail(sucursal.getEmail().trim());
//				actForm.setFono(sucursal.getTelefono().trim());
				actForm.setCelular(sucursal.getCelular() != 0 ? Integer.toString(sucursal.getCelular()) : "");
				
				List fono=Utils.obtieneFono(sucursal.getTelefono().trim());
				actForm.setCodigoFono((String)fono.get(0));
				actForm.setFono((String)fono.get(1));


				List fax=Utils.obtieneFono(sucursal.getFax().trim());
				actForm.setCodigoFax((String)fax.get(0));
				actForm.setFax((String)fax.get(1));

//				actForm.setFax(sucursal.getFax().trim());

				llenaCombosEdicion(actForm, false);
			} else {
				actForm.setRutEmpresaFmt(Utils.formatRut(empresa.getIdEmpresa()));
				actForm.setNombreEmpresa(empresa.getRazonSocial().trim());
				
				if (bVaciar) {
					actForm.setCelular("");
					actForm.setCodigo("");
					actForm.setDireccion("");
					actForm.setDpto("");
					actForm.setEmail("");
					actForm.setFax("");
					actForm.setFono("");
					actForm.setNombre("");
					actForm.setNumero("");
					actForm.setOpcRegion("-1");
					actForm.setOpcCiudad("-1");
					actForm.setOpcComuna("-1");
				}

				llenaCombosEdicion(actForm, true);
			}
			actForm.setTipoOper(tipoOper);
		
			tx.commit();
			
			if (bGuardar) {
				am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardada", "Sucursal", sucursal.getIdSucursal()));
				this.saveMessages(request.getSession(), am);
			}

			return mapping.findForward(tipoOper);

		} catch (Exception ex) {
			logger.error("Se produjo una excepcion en SucursalesEditarAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			
			return mapping.findForward("error");
		}  
	}
}
