package cl.araucana.bienestar.bonificaciones.ui.actions.preCasos;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.common.Constants;
import cl.araucana.bienestar.bonificaciones.model.DetalleCaso;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;
import cl.araucana.bienestar.bonificaciones.vo.CasoVO;
import cl.araucana.bienestar.bonificaciones.vo.DatosMovimientoTesoreriaVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleMovimientoPreCasoVO;
import cl.araucana.bienestar.bonificaciones.vo.UsuarioVO;

/**
 * @version 	1.0
 * @author		Alejandro Sepúlveda
 */
public class RegistrarEgresoAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		
		Logger logger = Logger.getLogger(RegistrarEgresoAction.class);	
			
		ServicesCasosDelegate casosDelegate = new ServicesCasosDelegate();
		
		DynaValidatorActionForm dynaValidatorActionForm =(DynaValidatorActionForm) form;		
		String target=null;
		
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		UsuarioVO user=new UsuarioVO();
		user.setNombre(userinformation.getNombres());
		user.setApellidoMaterno(userinformation.getApellidoMaterno());
		user.setApellidoPaterno(userinformation.getApellidoPaterno());
		user.setCodigoOficina(userinformation.getCodOficina());
		user.setUsuario(userinformation.getUsuario());		
				
		String pagarA = (String)dynaValidatorActionForm.get("pagarA");
		String formaPago = (String)dynaValidatorActionForm.get("formaPago");
		logger.debug("pagarA: "+pagarA);
		logger.debug("formaPago: "+formaPago);		
		
		request.getSession(false).removeAttribute("opciones");
		request.getSession(false).removeAttribute("opciones.valores");			
		
		DatosMovimientoTesoreriaVO datosMovimientoTesoreriaVO = (DatosMovimientoTesoreriaVO)request.getSession(false).getAttribute("datosMovimientoTesoreriaVO");		
		
		if( pagarA.equals(Constants.PAGAR_A_SOCIO) ) {

			datosMovimientoTesoreriaVO.setTipoPago(formaPago);	

			logger.debug("egreso para socio");
			
			ArrayList listaCasos = (ArrayList)datosMovimientoTesoreriaVO.getListaCasos();
			ArrayList listaDetalles = new ArrayList();
			ArrayList detalles = new ArrayList();
			for(int x=0;x<listaCasos.size();x++){
				CasoVO casoVo = (CasoVO)listaCasos.get(x);
				listaDetalles = casoVo.getDetalleCaso();
				for(int y=0;y<listaDetalles.size();y++){
					DetalleMovimientoPreCasoVO detVo = new DetalleMovimientoPreCasoVO();
					DetalleCaso detalleCaso = (DetalleCaso)listaDetalles.get(y);
					detVo.setCasoId(casoVo.getCasoID());
					detVo.setCoberturaCodigo(detalleCaso.getProducto().getCobertura().getCodigo());
					detVo.setDescripcion(detalleCaso.getProducto().getCobertura().getDescripcion());
					/**
					 * RAC 9759
					 */
					detVo.setIdDetalle(detalleCaso.getIdDetalle());
					//detVo.setMonto(detalleCaso.getTotalMenosIsapreYDescuento());
					double montoTotalEgresosPrevios = casosDelegate.getMontoPrevioGeneradoDetalle(casoVo.getCasoID(),detVo.getIdDetalle());
					detVo.setMonto(detalleCaso.getTotalMenosIsapreYDescuento() - montoTotalEgresosPrevios);
					detalles.add(detVo);
				}
			}

			datosMovimientoTesoreriaVO.setDetalles(detalles);
			
			casosDelegate.registrarEgresoTesoreriaPreCaso(datosMovimientoTesoreriaVO, user);
		
			target="success";

		}else		
		if ( pagarA.equals(Constants.PAGAR_A_CONVENIO) ) {

			logger.debug("Pre cheque: "+
				(String)request.getSession(false).getAttribute("preguntarParaQuienEsCheque"));
			if((String)request.getSession(false).getAttribute("preguntarParaQuienEsCheque")==null){
				
				datosMovimientoTesoreriaVO.setTipoPago(formaPago);	
				
				logger.debug("egreso para convenio");
				
				ArrayList listaCasos = (ArrayList)datosMovimientoTesoreriaVO.getListaCasos();
				ArrayList listaDetalles = new ArrayList();
				ArrayList detalles = new ArrayList();
				for(int x=0;x<listaCasos.size();x++){
					CasoVO casoVo = (CasoVO)listaCasos.get(x);
					listaDetalles = casoVo.getDetalleCaso();
					for(int y=0;y<listaDetalles.size();y++){
						DetalleMovimientoPreCasoVO detVo = new DetalleMovimientoPreCasoVO();
						DetalleCaso detalleCaso = (DetalleCaso)listaDetalles.get(y);
						detVo.setCasoId(casoVo.getCasoID());
						detVo.setCoberturaCodigo(detalleCaso.getProducto().getCobertura().getCodigo());
						detVo.setDescripcion(detalleCaso.getProducto().getCobertura().getDescripcion());
						detVo.setMonto(detalleCaso.getTotalMenosIsapreYDescuento());
						detalles.add(detVo);
					}
				}

				datosMovimientoTesoreriaVO.setDetalles(detalles);
								
				casosDelegate.registrarEgresoTesoreriaPreCaso(datosMovimientoTesoreriaVO, user);
		
				target="success";
		   
			}else{
				request.getSession(false).removeAttribute("preguntarParaQuienEsCheque");
				dynaValidatorActionForm.set("pagarA",Constants.PAGAR_A_CONVENIO);
			
				ArrayList opciones=new ArrayList();
				ArrayList opcionesValores=new ArrayList();
				
				if (userinformation.hasAccess("prcEgreso")) {
					opciones.add("Generar Egreso");
					opcionesValores.add("2");
				}
				
				request.getSession(false).setAttribute("opciones",opciones);
				request.getSession(false).setAttribute("opciones.valores",opcionesValores);				
				logger.debug("Pregunta para quien es cheque");
				target="fichaEgreso";						
			}
		}else
		if( pagarA.equals(Constants.PAGAR_A_PROFESIONALES) ) {
		
			ArrayList opcionesDatosProfesionales=new ArrayList();
			ArrayList opcionesValoresProfesionales=new ArrayList();
					
			opcionesDatosProfesionales.add("Agregar Profesional");
			opcionesValoresProfesionales.add("1");
			opcionesDatosProfesionales.add("Modificar Profesional");
			opcionesValoresProfesionales.add("2");
			opcionesDatosProfesionales.add("Eliminar Profesional");
			opcionesValoresProfesionales.add("3");
			
			request.getSession(false).setAttribute("opciones.datos.profesionales", opcionesDatosProfesionales);
			request.getSession(false).setAttribute("opciones.valores.profesionales",opcionesValoresProfesionales);					
			
			logger.debug("para profesionales");
			target="datosProfesionales";
		}	
		
		String referer="/prepareListaPreCasos.do";
		request.getSession(false).setAttribute("referer",referer);

		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.saveToken(request);
		return (forward);

	}
}
