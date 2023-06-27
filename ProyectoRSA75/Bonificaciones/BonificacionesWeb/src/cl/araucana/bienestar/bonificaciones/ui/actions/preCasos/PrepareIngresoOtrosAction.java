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
import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;
import cl.araucana.bienestar.bonificaciones.serv.ServicesSociosDelegate;
import cl.araucana.bienestar.bonificaciones.vo.CasoVO;
import cl.araucana.bienestar.bonificaciones.vo.DatosMovimientoTesoreriaVO;
import cl.araucana.common.BusinessException;

/**
 * @version 	1.0
 * @author		Alejandro Sepúlveda
 */
public class PrepareIngresoOtrosAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		// Objeto de Permisos de la Aplicación
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();
		
		Logger logger = Logger.getLogger(PrepareIngresoOtrosAction.class);
		
		ServicesCasosDelegate casosDelegate = new ServicesCasosDelegate();
		ServicesSociosDelegate servicesSociosDelegate = new ServicesSociosDelegate();

		DynaValidatorActionForm dynaValidatorActionForm =
				(DynaValidatorActionForm) form;

		ArrayList opciones=new ArrayList();
		ArrayList opcionesValores=new ArrayList();
		String target=null;
		
		String [] opcion=request.getParameterValues("indices3");
		ArrayList listaCasos=(ArrayList)request.getSession(false).getAttribute("listaPreCasosConPorLoMenosUnEgresoGenerado");
		ArrayList casosSeleccionados=new ArrayList();		
		
		if(opcion==null || opcion.length==0)
			throw new BusinessException("CCAF_BONIF_PROCESOINVALIDO",
					   "Debe seleccionar un Pre-Caso previamente");
					   
		if(opcion.length>1)
			throw new BusinessException("CCAF_BONIF_PROCESOINVALIDO",
					   "Debe seleccionar sólo un Pre-Caso");   
		
		//Recupero el caso seleccionado	
		CasoVO casoVO = (CasoVO)listaCasos.get((int)Integer.parseInt(opcion[0]));		
			
		if (userinformation.hasAccess("prcIngreso")) {
			opciones.add("Generar Ingreso");
			opcionesValores.add("1");
		}
		
		logger.debug("Generar Ingreso Caso ID: " + casoVO.getCasoID());
		
		DatosMovimientoTesoreriaVO datosMovimientoTesoreriaVO = new DatosMovimientoTesoreriaVO();

		dynaValidatorActionForm.set("formaPago","EFECTIVO");

		casoVO.setMontoIngresoOtrosTesoreriaPrevio(casosDelegate.getMontoIngresosPreviosOtros(casoVO.getCasoID()));
		double total=casoVO.getMontoIngresoOtrosTesoreria();
			
		datosMovimientoTesoreriaVO.setMonto(total);
	
		Socio socio = servicesSociosDelegate.getSocio(casoVO.getRutSocio());
		datosMovimientoTesoreriaVO.setTipoMovimiento(Constants.MOVI_INGRESO_OTROS);
		datosMovimientoTesoreriaVO.setRut(socio.getRut());
		datosMovimientoTesoreriaVO.setDigito(String.valueOf(socio.getDigito()));
		datosMovimientoTesoreriaVO.setNombre(socio.getNombre());
		datosMovimientoTesoreriaVO.setApePat(socio.getApePat());
		datosMovimientoTesoreriaVO.setApeMat(socio.getApeMat());
	
		casosSeleccionados.add(casoVO);
		datosMovimientoTesoreriaVO.setListaCasos(casosSeleccionados);

		if(datosMovimientoTesoreriaVO.getMonto()==0)
			throw new BusinessException("CCAF_BONIF_PROCESOINVALIDO",
					   "El Monto del Ingreso debe ser mayor que cero");		

		request.getSession(false).setAttribute("datosMovimientoTesoreriaVO",datosMovimientoTesoreriaVO);
	
		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("opciones",opciones);
		request.getSession(false).setAttribute("opciones.valores",opcionesValores);
	

		target="fichaIngreso";

		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.saveToken(request);
		return (forward);

	}
}
