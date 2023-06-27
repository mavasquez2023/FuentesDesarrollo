package cl.araucana.bienestar.bonificaciones.ui.actions.caso;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.exception.CalculoBonifException;
import cl.araucana.bienestar.bonificaciones.exception.CasoSaldadoAnticipadoException;
import cl.araucana.bienestar.bonificaciones.exception.GeneracionFolioException;
import cl.araucana.bienestar.bonificaciones.exception.SinAtributosException;
import cl.araucana.bienestar.bonificaciones.exception.SocioNoEncontradoException;
import cl.araucana.bienestar.bonificaciones.model.Caso;
import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;
import cl.araucana.bienestar.bonificaciones.serv.ServicesOperacionesDelegate;
import cl.araucana.bienestar.bonificaciones.serv.ServicesSociosDelegate;
import cl.araucana.bienestar.bonificaciones.vo.DescuentosVO;
import cl.araucana.bienestar.bonificaciones.vo.UsuarioVO;
import cl.araucana.common.BusinessException;

/**
 * @version 	1.0
 * @author
 */
public class SaldoDeudaTotalCasoAction extends Action {

	Logger logger = Logger.getLogger(SaldoDeudaTotalCasoAction.class);
	
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		logger.info(">> SaldoDeudaTotalCasoAction");
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		String target = "success";
		// return value
	
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();


		try {
			ServicesOperacionesDelegate servicio = new ServicesOperacionesDelegate();
			ServicesCasosDelegate servicioCasos = new ServicesCasosDelegate();
			
			Caso caso=(Caso)request.getSession(false).getAttribute("caso");	
				
			/**
			 * se valida que este el caso.
			 */
			if(caso == null)
				throw new SinAtributosException();
			
			ServicesSociosDelegate servicioSocios = new ServicesSociosDelegate();
			/**
			 * se valida que este el socio en el sistema.
			 */			
			Socio socio = servicioSocios.getSocio(caso.getRutSocio());
			if(socio == null)
				throw new SocioNoEncontradoException();
			
			/**
			 * Si ocurre lo siguiente: Socio inactivo - caso con cuotas internas
			 * Solo se debe cerrar el caso.
			 */
			if(socio.getEstado().equals(Socio.STD_ELIMINADO) && caso.getCuotasBienestar() > 0){
				caso.setEstado(Caso.STD_CERRADO);
				servicioCasos.cierraCasosUsuerDesvinculados(caso);
				//servicioCasos.actualizarCaso(caso);
				String msg = "Su operaci&oacute;n ha sido realizada con &eacute;xito.";
				String subMsg = "El caso fue cerrado.";
				request.getSession(false).setAttribute("msg",msg);
				request.getSession(false).setAttribute("subMsg",subMsg);
				target="genericMsg";
			}
			else{
				UsuarioVO usuario=new UsuarioVO();
				usuario.setNombre(userinformation.getNombres());
				usuario.setApellidoMaterno(userinformation.getApellidoMaterno());
				usuario.setApellidoPaterno(userinformation.getApellidoPaterno());
				usuario.setCodigoOficina(userinformation.getOficina());
				usuario.setUsuario(userinformation.getUsuario());
		
				String referer="/getListaCasos.do";
	
				request.getSession(false).setAttribute("referer",referer);
	//			1ero se ejecuta el proceso de descuentos de manera individual
				
				System.out.println("id caso : "+caso.getCasoID());	
				
				//Caso c = (Caso) servicioCasos.getCasoVO(caso.getCasoID());		
				if(caso.getIndicadorDescontado().equals(Caso.ESTADOINDICADOR_SI)){
					throw new CasoSaldadoAnticipadoException();
				}
				
				ArrayList descuento = servicioCasos.getCasoPorDescontar(caso.getCasoID());
				if(descuento.size() == 0){
					throw new CalculoBonifException();
				}
				servicio.generarProcesoCasoIndividual(caso.getRutSocio(),descuento,usuario);
				
				Iterator i = descuento.iterator();
				long montoDescuento = 0;
				if(i.hasNext()){
					DescuentosVO d = (DescuentosVO) i.next();
					montoDescuento = new Double(d.getMontoDescuento()).longValue();
				
				}
	//			2do generar comprobante de ingreso en tesorería de bienestar por el total de las cuotas impagas para saldo deuda total anticipada.
	//			en casos de pago anticipado
				long folio = 0;
				
				if(!socio.getEstado().equals(Socio.STD_ELIMINADO)){
					/**
					 * socio activo, se genera ingreso en tesoreria.
					 */
					if(montoDescuento > 0){
						folio = servicio.generarComprobanteIngresoBienestar(caso.getCasoID(),montoDescuento,usuario.getUsuario(),caso.getRutSocio(),caso.getCodigoConvenio());
						System.out.println("++++++ Folio: " + folio);
					}
		
					if(folio > 0){		
						//target="success";
						String msg = "Su operaci&oacute;n ha sido realizada con &eacute;xito.";
						String subMsg = "Se generó un comprobante de ingreso en tesoreria con folio : "+folio;
						request.getSession(false).setAttribute("msg",msg);
						request.getSession(false).setAttribute("subMsg",subMsg);
						target="genericMsg";
					}
					else{
						throw new GeneracionFolioException();
					}
				}
				else{
					/**
					 * socio inactivo con cuotas EXTERNAS no genera ingreso en tesoreria.
					 */
					String msg = "Su operaci&oacute;n ha sido realizada con &eacute;xito.";
					String subMsg = "El caso fue marcado y el saldo se pagar&aacute; en el proximo proceso de pago convenios";
					request.getSession(false).setAttribute("msg",msg);
					request.getSession(false).setAttribute("subMsg",subMsg);
					target="genericMsg";
				}
			}
		}catch(CasoSaldadoAnticipadoException casoE){
			String msg = "El Caso ya esta marcado para pago completo.";
			request.getSession(false).setAttribute("msg",msg);

			String subMsg = "se cerrará en el proximo proceso mensual de pagos";
			request.getSession(false).setAttribute("subMsg",subMsg);
			target="genericMsg"; 
		}catch(GeneracionFolioException genFolE){
			String msg = "No se generó folio egreso.";
			request.getSession(false).setAttribute("msg",msg);

			String subMsg = "Avisar a División Sistemas";
			request.getSession(false).setAttribute("subMsg",subMsg);
			target="genericMsg"; 
		}catch(CalculoBonifException calculoE){
			String msg = "Debe calcular la bonificación del caso.";
			request.getSession(false).setAttribute("msg",msg);

			String subMsg = "Caso sin cuotas";
			request.getSession(false).setAttribute("subMsg",subMsg);
			target="genericMsg"; 
		}catch(SocioNoEncontradoException socioE){
				String msg = "No se puede procesar debido a que no encontró al socio.";
				request.getSession(false).setAttribute("msg",msg);

				String subMsg = "Avisar a División Sistemas";
				request.getSession(false).setAttribute("subMsg",subMsg);
				target="genericMsg"; 
		}catch(SinAtributosException attrE){
			String msg = "No se puede procesar debido a que no se entrego caso.";
			request.getSession(false).setAttribute("msg",msg);

			String subMsg = "Avisar a División Sistemas.";
			request.getSession(false).setAttribute("subMsg",subMsg);
			target="genericMsg"; 
		}catch(BusinessException be){
			//String msg = "No se puede procesar debido a que no encontró al socio.";
			request.getSession(false).setAttribute("msg",be.getMsgAdic());

			String subMsg = "Avisar a División Sistemas";
			request.getSession(false).setAttribute("subMsg",subMsg);
			target="genericMsg";
		}catch (Exception e) {
			
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));

		}

		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		if (!errors.isEmpty()) {
			saveErrors(request, errors);

			// Forward control to the appropriate 'failure' URI (change name as desired)
			//forward = mapping.findForward("failure");

		} else {

			// Forward control to the appropriate 'success' URI (change name as desired)
			forward = mapping.findForward(target);

		}
		logger.info("<< SaldoDeudaTotalCasoAction");
		// Finish with
		return (forward);

	}
}
