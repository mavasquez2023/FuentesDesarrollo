package cl.laaraucana.satelites.certificados.afiliacion.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.araucana.core.util.UserPrincipal;
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.certificados.afiliacion.forms.GeneraCertificadoForm;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusRolOUT.ServicioQueryBPStatusRol;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusRolOUT.VO.SalidaAfiliadoRolVO;
import cl.laaraucana.satelites.webservices.model.DetalleEmpresaAfiliado;
import cl.laaraucana.satelites.webservices.model.UsuarioAfiliadoVO;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;
import cl.laaraucana.satelites.webservices.utils.UsuarioServiceUtilSinAS400;

/**
 * @version 1.0
 * @author
 */
public class GeneraCertificadoAfiliacionAction extends Action

{
	protected Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward(); // return value
		HttpSession sesion = request.getSession();
		GeneraCertificadoForm form = (GeneraCertificadoForm) _form;

		String rutEmpresa = form.getRutEmpresa();
		String numero = form.getNumero();
		String rol = form.getRol();
		String uc= request.getParameter("uc");

		String rut=null;
		String fechaAfiliacion=null;
		boolean encontrado = false;
		
		if(rol==null){
			rol= (String)sesion.getAttribute("rol");
		}
		//String fechaRol=null;
		try {
			if(uc!=null){
				UserPrincipal newUser = UserPrincipal.decodeUserCredentials(uc);
				rut= newUser.getName();
				sesion.setAttribute("uc", uc);
			}else{
				rut= (String) sesion.getAttribute("rut");
			}
			logger.info("Generando certificado Afiliado " + rut + ", rol=" + rol);
			if(rut == null ){
				//Cuando faltan datos para llenar el certificado
				request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
				request.setAttribute("message", Constants.REPORT_DATA_ERROR);
				return mapping.findForward("customError");
			}
			
			if(rol.equals("Pensionado") || rol.equals("Independiente")){
				SalidaAfiliadoRolVO salidaFechaRol= ServicioQueryBPStatusRol.obtenerFechaRol(rut, rol);
				String fechaInicioRol= "";
				String fechaTerminoRol= "";
				if(salidaFechaRol!=null){
					fechaInicioRol= Utils.dateToString(salidaFechaRol.getFechaInicioRol());
					fechaTerminoRol= Utils.dateToString(salidaFechaRol.getFechaTerminoRol());
					sesion.setAttribute("fechaRol", fechaInicioRol);
					sesion.setAttribute("fechaTerminoRol", fechaTerminoRol);
					if(fechaTerminoRol.substring(6).equals("9999")){
						forward = mapping.findForward("generaCertificadoPensionado");
					}else{
						forward = mapping.findForward("generaCertificadoInvPensionado");
					}
					encontrado = true;
				}
			}else{
				forward = mapping.findForward("generaCertificado");
				List<DetalleEmpresaAfiliado> empresas = (ArrayList<DetalleEmpresaAfiliado>) sesion.getAttribute("empresasList");
				if(empresas== null){
					UsuarioAfiliadoVO afiliadoCRM= (UsuarioAfiliadoVO)UsuarioServiceUtilSinAS400.obtenerAfiliado(rut);
					empresas= afiliadoCRM.getDetalleEmpresaList();
				}
				if (empresas == null) {
					request.setAttribute("error", "2");
					return forward;
				}
				
				int numLista = 0;
				for (DetalleEmpresaAfiliado empresa : empresas) {
					// Busca el rut de la empresa seleccionada en la sesion
					if (empresa.getRutEmpresa().equals(rutEmpresa) && numero.equals(String.valueOf(numLista))) {
						
						//System.out.println("entro a la cuestión");
						fechaAfiliacion= empresa.getFechaAfiliacion();
						//fechaRol= empresa.getFechaRol();
						sesion.setAttribute("fechaAfiliacion", fechaAfiliacion);
						sesion.setAttribute("nombreEmpresa", empresa.getNombreEmpresa());
						if(ServiciosConst.RES_SERVICIOS.getString("estado.afiliacion.inactivo").trim().equalsIgnoreCase(empresa.getEstadoAfiliacion())){
							forward = mapping.findForward("generaCertificadoInv");
						}
						request.setAttribute("error", "0");
						encontrado = true;
						break;
					}
					numLista++;
				}
			}
			request.setAttribute("fechaEmision", Utils.getFechaCompleta());
			

		} catch (Exception e) {
			forward = Utils.returnErrorForward(mapping, e, this.getClass());
		}
		logger.debug(">>Salida CertificadoAfiliacionAction");
		return forward;

	}

}
