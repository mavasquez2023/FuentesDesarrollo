package cl.araucana.autoconsulta.ui.actions.cartola;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.AfiliadoVO;
import cl.araucana.autoconsulta.vo.CuentaAhorroVO;
import cl.araucana.autoconsulta.vo.CuentasAhorroRutVO;
import cl.araucana.autoconsulta.vo.EmpresaVO;
import cl.araucana.autoconsulta.vo.UsuarioVO;

/**
 * @version 	1.0
 * @author asepulveda
 */
public class GetCartolaAhorroAction extends Action {
	
	private static Logger logger = Logger.getLogger(GetCartolaAhorroAction.class);
	
	public static final String GLOBAL_FORWARD_sinCuentasAhorro = "sinCuentasAhorro";
	public static final String GLOBAL_FORWARD_cartola = "cartola";
	public static final String GLOBAL_FORWARD_listaCuentasAhorro = "listaCuentasAhorro";
	

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
			HttpSession session = request.getSession(true);
			DynaValidatorActionForm daf = (DynaValidatorActionForm)form;
			ServicesAutoconsultaDelegate delegate = new ServicesAutoconsultaDelegate();
			String target=null;
			
			//String dispositivo = request.getRemoteAddr();
			//logger.debug("IP: "+dispositivo);
			
			UsuarioVO usuario = (UsuarioVO) session.getAttribute("datosUsuario");
			usuario.setIpConexion(request.getRemoteAddr());
			long rut = usuario.getRut();
			String nombreConsulta=usuario.getNombre();
			String rutConsulta=usuario.getFullRut();
			String cuentaAhorro = (String)daf.get("cuenta");
			Collection empleadores;
			
				 
			logger.debug("Rut: "+rut);
			
			if(usuario.isEsAhorrante()) {
				// Cuenta de Ahorro	
				if(cuentaAhorro==null || cuentaAhorro.length()==0) {
					logger.debug("Busca Cuentas");
					CuentasAhorroRutVO cuentasAhorroRut = delegate.getCuentasAhorroByRut(rut);
				
					logger.debug("Tiene: "+cuentasAhorroRut.getCantidadCuentas()+" Cuentas");
					logger.debug("Tiene: "+cuentasAhorroRut.getCantidadCuentasActivas()+" Cuentas Activas");
					logger.debug("Tiene: "+cuentasAhorroRut.getCantidadCuentasInactivas()+" Cuentas Inactivas");
				
					// Esta condicion no debería darse ya que ahorrante
					if(cuentasAhorroRut.getCantidadCuentas()==0) {
						session.removeAttribute("noActiva");
						target=GLOBAL_FORWARD_sinCuentasAhorro;
					}
					else if(cuentasAhorroRut.getCantidadCuentasActivas()==1){
						Collection cuentas = cuentasAhorroRut.getCuentas();
						Iterator icuentas = cuentas.iterator();
						CuentaAhorroVO cuenta = (CuentaAhorroVO)icuentas.next();
						cuentaAhorro=cuenta.getNumeroCuenta();
						session.setAttribute("nombre",nombreConsulta);
						session.setAttribute("rut",rutConsulta);
						
						logger.debug("Rut: "+rut);
						logger.debug("Cuenta Ahorro: "+cuentaAhorro);
						usuario.setRutAfiliado(rut);
						
						empleadores = delegate.getEmpleadoresByEmpleado(usuario);
						if (!empleadores.isEmpty()) {
								Iterator iempleadores = empleadores.iterator();
								EmpresaVO empleador = (EmpresaVO) iempleadores.next();
								usuario.setRutEmpresa(empleador.getRut());
						}

												
						session.setAttribute("cartola",delegate.getCartolaAhorro(usuario,cuentaAhorro));
								
						target=GLOBAL_FORWARD_cartola;
					}
					else if(cuentasAhorroRut.getCantidadCuentasActivas()>1) {
						session.setAttribute("lista.cuentas",cuentasAhorroRut.getCuentas());
						target=GLOBAL_FORWARD_listaCuentasAhorro;					
					}
					else if(cuentasAhorroRut.getCantidadCuentasInactivas()>0) {
						session.setAttribute("noActiva","yes");
						target=GLOBAL_FORWARD_sinCuentasAhorro;
					}
				}
				else {
					session.setAttribute("nombre",nombreConsulta);
					session.setAttribute("rut",rutConsulta);
						
					logger.debug("Rut: "+rut);
					logger.debug("Cuenta Ahorro: "+cuentaAhorro);
					usuario.setRutAfiliado(rut);
										
					session.setAttribute("cartola",delegate.getCartolaAhorro(usuario,cuentaAhorro));		
					target=GLOBAL_FORWARD_cartola;
				}
			}
			else {
				session.removeAttribute("noActiva");
				target=GLOBAL_FORWARD_sinCuentasAhorro;	
			}
			
			logger.debug("Presenta pagina: "+target);								
 		    return mapping.findForward(target);
	}
}
