package cl.araucana.autoconsulta.ui.actions.estadoCreditos;

import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.autoconsulta.common.Constants;
import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.test.Utiles;
import cl.araucana.autoconsulta.vo.AfiliadoVO;
import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.araucana.autoconsulta.ws.estadoCredito.com.lautaro.xi.CRM.WEB_Mobile.ConsEstSolCreditoClient;
import cl.araucana.autoconsulta.ws.estadoCredito.com.lautaro.xi.CRM.WEB_Mobile.EstadoCreditosResponse;
import cl.araucana.autoconsulta.ws.estadoCredito.com.lautaro.xi.CRM.WEB_Mobile.EstadoCreditosSalida;
import cl.araucana.autoconsulta.ws.pagoenexceso.ConsultaPagoEnExcesoOut;
import cl.araucana.autoconsulta.ws.pagoenexceso.PagoEnExcesoClient;
import cl.araucana.common.BusinessException;

/**
 * @version 1.0
 * @author IBM
 */
public class GetConsultaEstadoSolCreditos extends Action {

	private static Logger logger = Logger.getLogger(GetConsultaEstadoSolCreditos.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "resultadoConsulta";
		String urlConsulta = "/web/consultaEstadoSolCredito.do";
		String mensajeError = ""; 
		String rutSesion = null;
		String tipoconsulta = null;
		EstadoCreditosSalida wsResponse = null;
		HttpSession session = request.getSession(true);
		try {
			ServicesAutoconsultaDelegate delegate = new ServicesAutoconsultaDelegate();
			String rutForm = (String)request.getParameter("rutEmpleado");
			String imprimir = (String)request.getParameter("imprimir");
			
			rutSesion = (String) session.getAttribute("rutEstadoCredito");
			long rutLong = 0;
			
			//Información de log de usuario
			UsuarioVO usuario = (UsuarioVO) session.getAttribute("datosUsuario");
			usuario.setIpConexion(request.getRemoteAddr());
			
			ConsEstSolCreditoClient wsClient = new ConsEstSolCreditoClient();
			
			if(!usuario.isEsEmpresa() && !usuario.isEsEmpresaPublica() && !usuario.isEsEncargadoEmpresa()){
				//Obtiene certificado para persona
				rutSesion = usuario.getRut() + "-" + usuario.getDv();
				wsResponse = wsClient.consultarEstadoCredito(rutSesion);
				if(imprimir!=null && imprimir.equals("true")){
					target ="imprimeConsulta";
				}
				tipoconsulta = "persona";
			}else if(usuario.isEsEmpresa() || usuario.isEsEmpresaPublica()){
				long rutEmpresa = usuario.getRut();
				//Si no envía nada de la vista, ni hay nada en sesión
				if(rutForm==null){
					if(imprimir==null){
						target = "definirEmpleadoPago";
					}else if(imprimir.equals("true") && rutSesion != null && rutSesion.trim().length()!=0){
						rutLong = Utiles.getLongRut(rutSesion);
						if(!validaPerteneceEmpresa(rutLong, rutEmpresa, delegate, request.getRemoteAddr())){
							mensajeError = "El Rut ingresado no pertenece a la empresa";
							target = "definirEmpleadoPago";
						}else{
							target ="imprimeConsulta";
							wsResponse = wsClient.consultarEstadoCredito(rutSesion);
						}
					}
				}else{
					rutForm = rutForm.replaceAll("[\\s\\.]", "");
					if(!Utiles.IsRutValido(rutForm)){
						mensajeError = "Debe ingresar Rut Válido";
						target = "definirEmpleadoPago";
					}else{
						rutSesion = rutForm;
						rutLong = Utiles.getLongRut(rutSesion);
						//Validar si rut consultado pertenece a empresa
						if(!validaPerteneceEmpresa(rutLong, rutEmpresa, delegate, request.getRemoteAddr())){
							mensajeError = "El Rut ingresado no pertenece a la empresa";
							target = "definirEmpleadoPago";
						}else{
							wsResponse = wsClient.consultarEstadoCredito(rutSesion);
						}						
					}
				}
				
				tipoconsulta = "empresa";
			}
		} catch (Exception e) {
			logger.error("Error al realizar consulta de estados de crédito",e);
			request.setAttribute("error.message", "Error al realizar consulta de estados de crédito");
			target = "failure";
		}
		
		
		session.setAttribute("tipoConsulta", tipoconsulta);
		session.setAttribute("rutEstadoCredito", rutSesion);
		request.setAttribute("fechaHoy", Utiles.getFechaHoy());
		request.setAttribute("rutConsulta", Utiles.formatearRut(rutSesion));
		request.setAttribute("mensajeError", mensajeError);
		request.setAttribute("mensajeRespuesta", wsResponse);
		request.setAttribute("urlConsulta", urlConsulta);
		return mapping.findForward(target);
	}
	
	private boolean validaPerteneceEmpresa(long rutAfiliado, long rutEmpresa, ServicesAutoconsultaDelegate delegate, String remoteAddr) throws BusinessException, Exception{
		boolean res = false;
		AfiliadoVO afiliado = delegate.getDatosEmpleado(rutAfiliado, rutEmpresa);
		if(afiliado != null) res = true;
		return res;
	}
	
	public static void main(String[] args) {
		String afiliadosDatabase = "AFDTA";
		String empresasDatabase = "CMDTA";
		String command = "SELECT 1 \"" + Constants.ALIAS_INT + "\" " + "FROM " + afiliadosDatabase + ".AF03U102 A ," + empresasDatabase + ".CM02F1 B " + "WHERE A.SE5FAJC = ? " + "AND B.CMNA = ? "
		+ "AND A.CMNA = B.CMNA";
		System.out.println(command);

	        double d = 100000567;
	        DecimalFormat df = new DecimalFormat("###,###.##");
	        System.out.print(df.format(d));
	  }
}
