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
import cl.araucana.bienestar.bonificaciones.model.Caso;
import cl.araucana.bienestar.bonificaciones.model.Convenio;
import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;
import cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosDelegate;
import cl.araucana.bienestar.bonificaciones.serv.ServicesSociosDelegate;
import cl.araucana.bienestar.bonificaciones.vo.CasoVO;
import cl.araucana.bienestar.bonificaciones.vo.DatosMovimientoTesoreriaVO;
import cl.araucana.common.BusinessException;

/**
 * @version 	1.0
 * @author		Alejandro Sepúlveda
 */
public class PrepareEgresoAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

			// Objeto de Permisos de la Aplicación
			cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
			if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();
		
			Logger logger = Logger.getLogger(PrepareEgresoAction.class);
		
			ServicesCasosDelegate casosDelegate = new ServicesCasosDelegate();
			ServicesSociosDelegate servicesSociosDelegate = new ServicesSociosDelegate();
			ServicesConveniosDelegate servicesConveniosDelegate = new ServicesConveniosDelegate();  
			DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm) form;
			ArrayList opciones=new ArrayList();
			ArrayList opcionesValores=new ArrayList();
			String target=null;
			boolean preguntarParaQuienEsCheque=false;
			ArrayList casosSeleccionados=new ArrayList();
		
			String vieneDesdeOpciones = (String)request.getSession(false).getAttribute("vieneDesdeOpciones");
			String [] opcion=null;
			ArrayList listaCasos=new ArrayList();
		
			if(vieneDesdeOpciones==null){
				opcion=request.getParameterValues("indices1");
				listaCasos=(ArrayList)request.getSession(false).getAttribute("listaPreCasosPorGenerarPrimerEgreso");
			}else{
				request.getSession(false).removeAttribute("vieneDesdeOpciones");
				opcion=request.getParameterValues("indices3");
				listaCasos=(ArrayList)request.getSession(false).getAttribute("listaPreCasosConPorLoMenosUnEgresoGenerado");
			}
				
			if(opcion==null || opcion.length==0)
				throw new BusinessException("CCAF_BONIF_PROCESOINVALIDO",
						   "No ha seleccionado casos para generar egresos");
			
			int maximoPermitidoSeleccion=9;
			logger.debug("Largo :"+opcion.length);			   
			if(opcion.length > maximoPermitidoSeleccion)
				throw new BusinessException("CCAF_BONIF_PROCESOINVALIDO",
						   "No puede seleccionar más de " + maximoPermitidoSeleccion + " Pre-Casos");
					
			double total=0;
			DatosMovimientoTesoreriaVO datosMovimientoTesoreriaVO = new DatosMovimientoTesoreriaVO();
		
			request.getSession(false).removeAttribute("preguntarParaQuienEsCheque");
		
			if(opcion.length==1){
				// Solo un caso seleccionado
				CasoVO casoVO = (CasoVO)listaCasos.get((int)Integer.parseInt(opcion[0]));
				casoVO.setDetalleCaso(casosDelegate.getDetallesCasoPreCasos(casoVO.getCasoID()));

				if(casoVO.getTipoBono().equals(Caso.TIPOBONO_SOCIO)){ 
					// por defecto seleccionado en efectivo
					dynaValidatorActionForm.set("formaPago","EFECTIVO");

					//Total de egreso
					casoVO.setMontoEgresoTesoreriaPrevio(casosDelegate.getMontoEgresosPrevios(casoVO.getCasoID()));
					casoVO.setMontoIngresoIsapreTesoreriaPrevio(casosDelegate.getMontoIngresosPreviosIsapre(casoVO.getCasoID()));
					casoVO.setMontoIngresoOtrosTesoreriaPrevio(casosDelegate.getMontoIngresosPreviosOtros(casoVO.getCasoID()));
					/**
					 * total es return (int) (super.getTotal() - montoEgresoTesoreriaPrevio);
					 * es decir, es el monto que debemos asignar en la generación de los egresos.
					*/
					total=total+casoVO.getMontoEgresoTesoreria();
					datosMovimientoTesoreriaVO.setMonto(total);
								
					// En este caso el egreso es para el socio
					Socio socio = servicesSociosDelegate.getSocio(casoVO.getRutSocio());
					datosMovimientoTesoreriaVO.setRut(socio.getRut());
					datosMovimientoTesoreriaVO.setDigito(String.valueOf(socio.getDigito()));
					datosMovimientoTesoreriaVO.setNombre(socio.getNombre());
					datosMovimientoTesoreriaVO.setApePat(socio.getApePat());
					datosMovimientoTesoreriaVO.setApeMat(socio.getApeMat());
								
					// pagar a socio
					dynaValidatorActionForm.set("pagarA",Constants.PAGAR_A_SOCIO);
				
				}else
				if(casoVO.getTipoBono().equals(Caso.TIPOBONO_CONVENIO)){
					// por defecto seleccionado en cheque 
					dynaValidatorActionForm.set("formaPago","CHEQUE");
					preguntarParaQuienEsCheque=true;
					
					//Total de egreso
					casoVO.setMontoEgresoTesoreriaPrevio(casosDelegate.getMontoEgresosPrevios(casoVO.getCasoID()));
					casoVO.setMontoIngresoIsapreTesoreriaPrevio(casosDelegate.getMontoIngresosPreviosIsapre(casoVO.getCasoID()));
					casoVO.setMontoIngresoOtrosTesoreriaPrevio(casosDelegate.getMontoIngresosPreviosOtros(casoVO.getCasoID()));
					total=total+casoVO.getMontoEgresoTesoreria();					
					datosMovimientoTesoreriaVO.setMonto(total);
				
					// En este caso el egreso puede ser para el convenio o para varios 
					// rut's que corresponden a diferentes profesionales					
					Convenio convenio = servicesConveniosDelegate.getConvenio(casoVO.getCodigoConvenio());
					datosMovimientoTesoreriaVO.setRut(convenio.getRut());
					datosMovimientoTesoreriaVO.setDigito(convenio.getDvRut());
					datosMovimientoTesoreriaVO.setNombre(convenio.getNombre());				
				
					// por defecto se setea pagar a convenio (el usuario puede cambiarlo en la pagina)
					dynaValidatorActionForm.set("pagarA",Constants.PAGAR_A_CONVENIO);
								
				}
				casosSeleccionados.add(casoVO);
				datosMovimientoTesoreriaVO.setListaCasos(casosSeleccionados);
						
			}else{
				// Varios casos seleccionados
				long codigoConvenioAnterior=0;
				String tipoCompraBonoAnterior=null;
				for(int t=0;t<opcion.length;t++){
					CasoVO casoVO = (CasoVO)listaCasos.get((int)Integer.parseInt(opcion[t]));
					casoVO.setDetalleCaso(casosDelegate.getDetallesCasoPreCasos(casoVO.getCasoID()));

					if(t==0){
						codigoConvenioAnterior=casoVO.getCodigoConvenio();
						tipoCompraBonoAnterior=casoVO.getTipoBono();
					}else{
						if(codigoConvenioAnterior!=casoVO.getCodigoConvenio()){
							throw new BusinessException("CCAF_BONIF_PROCESOINVALIDO",
									   "Todos los casos seleccionados deben ser del mismo convenio");						
						}
						
						if(!tipoCompraBonoAnterior.equals(casoVO.getTipoBono())){
							throw new BusinessException("CCAF_BONIF_PROCESOINVALIDO",
									   "Todos los casos seleccionados deben tener la misma marca Compra Bono (Socio/Convenio)");						
						}						
						
					}
					// por defecto seleccionado en cheque 
					dynaValidatorActionForm.set("formaPago","CHEQUE");
					preguntarParaQuienEsCheque=true;
				
					//Total de egreso
					casoVO.setMontoEgresoTesoreriaPrevio(casosDelegate.getMontoEgresosPrevios(casoVO.getCasoID()));
					casoVO.setMontoIngresoIsapreTesoreriaPrevio(casosDelegate.getMontoIngresosPreviosIsapre(casoVO.getCasoID()));
					casoVO.setMontoIngresoOtrosTesoreriaPrevio(casosDelegate.getMontoIngresosPreviosOtros(casoVO.getCasoID()));					
					total=total+casoVO.getMontoEgresoTesoreria();
					datosMovimientoTesoreriaVO.setMonto(total);
				
					casosSeleccionados.add(casoVO);
					logger.debug("Indice: "+(int)Integer.parseInt(opcion[t]));				
				}
			
				// En este caso el egreso puede ser para el convenio o para varios 
				// rut's que corresponden a diferentes profesionales
				Convenio convenio = servicesConveniosDelegate.getConvenio(codigoConvenioAnterior);
				datosMovimientoTesoreriaVO.setRut(convenio.getRut());
				datosMovimientoTesoreriaVO.setDigito(convenio.getDvRut());
				datosMovimientoTesoreriaVO.setNombre(convenio.getNombre());
				datosMovimientoTesoreriaVO.setListaCasos(casosSeleccionados);				
			
				// pagar a convenio	
				dynaValidatorActionForm.set("pagarA",Constants.PAGAR_A_CONVENIO);			
			}
		
			if(datosMovimientoTesoreriaVO.getMonto()<=0)
				throw new BusinessException("CCAF_BONIF_PROCESOINVALIDO",
						   "El monto del Egreso debe ser mayor que cero");		

			request.getSession(false).setAttribute("datosMovimientoTesoreriaVO",datosMovimientoTesoreriaVO);
		
			logger.debug("preguntarParaQuienEsCheque: "+preguntarParaQuienEsCheque);
			
			if(preguntarParaQuienEsCheque) {
				request.getSession(false).setAttribute("preguntarParaQuienEsCheque","yes");
				if (userinformation.hasAccess("prcEgreso")) {
					opciones.add("Continuar");
					opcionesValores.add("1");
				}
			}else{
				if (userinformation.hasAccess("prcEgreso")) {
					opciones.add("Generar Egreso");
					opcionesValores.add("1");
				}			
			}

			// Guardar en memoria el resultado
			request.getSession(false).setAttribute("opciones",opciones);
			request.getSession(false).setAttribute("opciones.valores",opcionesValores);
		

			target="fichaEgreso";

			ActionForward forward = new ActionForward();
			forward = mapping.findForward(target);
			this.saveToken(request);
			return (forward);


	}
}
