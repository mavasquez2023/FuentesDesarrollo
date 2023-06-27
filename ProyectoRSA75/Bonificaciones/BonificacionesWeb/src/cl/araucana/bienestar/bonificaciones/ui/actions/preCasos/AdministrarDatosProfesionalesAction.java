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

import cl.araucana.bienestar.bonificaciones.vo.DatosMovimientoTesoreriaVO;
import cl.araucana.bienestar.bonificaciones.vo.DatosProfesionalesVO;
import cl.araucana.common.BusinessException;

/**
 * @version 	1.0
 * @author		Alejandro Sepúlveda
 */
public class AdministrarDatosProfesionalesAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		
		Logger logger = Logger.getLogger(AdministrarDatosProfesionalesAction.class);

		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm) form;

		String target=null;
		
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();		
						
		DatosMovimientoTesoreriaVO datosMovimientoTesoreriaVO = (DatosMovimientoTesoreriaVO)request.getSession(false).getAttribute("datosMovimientoTesoreriaVO");		
		
		DatosProfesionalesVO datosProfesionalesVO = new DatosProfesionalesVO();
		
		ArrayList listaMensajes = new ArrayList();
		request.getSession(false).removeAttribute("validation.message");		
		
		String index=(String)dynaValidatorActionForm.get("indice");
		int indice=0;
		
		if(index==null) index="";
		if(!index.equals(""))
			indice=(int)Integer.parseInt(index);
		
		ArrayList listaProfesionales = datosMovimientoTesoreriaVO.getListaProfesionales();
		int monto=0;		
		int montoTotalProfesionales=0;
		
		int opcion = (int)Integer.parseInt((String)dynaValidatorActionForm.get("opcion"));
		
		logger.debug("opcion: "+opcion);
		
		switch(opcion){
			case 1:		
				//Agregar Profesional
				boolean existenErrores=false;
				
				if(!existenErrores){
					
					datosProfesionalesVO.setNombre((String)dynaValidatorActionForm.get("nombre"));
					datosProfesionalesVO.setRut((String)dynaValidatorActionForm.get("rut"));
					datosProfesionalesVO.setDigito((String)dynaValidatorActionForm.get("dv"));
					datosProfesionalesVO.setTipoPago((String)dynaValidatorActionForm.get("formaPago"));

					
					monto = (int)Integer.parseInt((String)dynaValidatorActionForm.get("monto"));
					if(monto>datosMovimientoTesoreriaVO.getMonto()){
						listaMensajes.add("El monto ingreado no puede ser mayor que el monto total a egresar");
						
					}else{
						datosProfesionalesVO.setMonto(monto);
						
						montoTotalProfesionales=0;
						for(int x=0;x<listaProfesionales.size();x++){
							DatosProfesionalesVO datoProfesional = (DatosProfesionalesVO)listaProfesionales.get(x);
							montoTotalProfesionales= montoTotalProfesionales + (int)datoProfesional.getMonto();
						}
					
						if(monto+montoTotalProfesionales>datosMovimientoTesoreriaVO.getMonto()){
							listaMensajes.add("La suma de los montos de los egresos parciales, no puede ser mayor que el total a egresar"); 
						}else{
							montoTotalProfesionales=montoTotalProfesionales+monto;						
							datosMovimientoTesoreriaVO.setMontoTotalProfesionales(montoTotalProfesionales);
						
							listaProfesionales.add(datosProfesionalesVO);
						}	
					}							
				}
										
				break;
			case 2:		
				//Modificar Profesional
				datosProfesionalesVO = (DatosProfesionalesVO)datosMovimientoTesoreriaVO.getListaProfesionales().get(indice);
				
				datosProfesionalesVO.setNombre((String)dynaValidatorActionForm.get("nombre"));
				datosProfesionalesVO.setRut((String)dynaValidatorActionForm.get("rut"));
				datosProfesionalesVO.setDigito((String)dynaValidatorActionForm.get("dv"));
				datosProfesionalesVO.setTipoPago((String)dynaValidatorActionForm.get("formaPago"));
				
				montoTotalProfesionales=0;
				for(int x=0;x<listaProfesionales.size();x++){
					DatosProfesionalesVO datoProfesional = (DatosProfesionalesVO)listaProfesionales.get(x);
					montoTotalProfesionales= montoTotalProfesionales + (int)datoProfesional.getMonto();
				}
			
				monto = (int)Integer.parseInt((String)dynaValidatorActionForm.get("monto"));
						
				if(monto+montoTotalProfesionales>datosMovimientoTesoreriaVO.getMonto()){
					listaMensajes.add("La suma de los montos de los egresos parciales, no puede ser mayor que el total a egresar"); 
				}else{
					montoTotalProfesionales=montoTotalProfesionales+monto;
					datosMovimientoTesoreriaVO.setMontoTotalProfesionales(montoTotalProfesionales);							
					datosProfesionalesVO.setMonto(monto);								
				}
				
				break;
			case 3:	
				//Eliminar Profesional
				datosMovimientoTesoreriaVO.getListaProfesionales().remove(indice);
				montoTotalProfesionales=0;
				for(int x=0;x<listaProfesionales.size();x++){
					DatosProfesionalesVO datoProfesional = (DatosProfesionalesVO)listaProfesionales.get(x);
					montoTotalProfesionales= montoTotalProfesionales + (int)datoProfesional.getMonto();
				}						
				
				datosMovimientoTesoreriaVO.setMontoTotalProfesionales(montoTotalProfesionales);
										
				break;
			default:	
				logger.debug("La opcion es incorrecta, error en programacion");
				throw new BusinessException("CCAF_BONIF_PROCESOINVALIDO","opcion no valida");
		}

		dynaValidatorActionForm.set("nombre","");
		dynaValidatorActionForm.set("rut","");
		dynaValidatorActionForm.set("dv","");
		dynaValidatorActionForm.set("monto","");
		dynaValidatorActionForm.set("formaPago","CHEQUE");			

		ArrayList opciones=new ArrayList();
		ArrayList opcionesValores=new ArrayList();
		
		logger.debug("listaProfesionales.size: "+listaProfesionales.size());

		switch (listaProfesionales.size()){
			case 0:
				//Sin opciones
				logger.debug("Sin opciones");
				break;
			default:
				logger.debug("puede generar egreso directamente (1 prof)");
				if (userinformation.hasAccess("prcEgreso")) {
					opciones.add("Generar Egreso");
					opcionesValores.add("1");
				}				
				break;
		}
		
		request.getSession(false).setAttribute("opciones",opciones);
		request.getSession(false).setAttribute("opciones.valores",opcionesValores);		
		
		target="datosProfesionales";
		
		logger.debug("listaMensajes: "+listaMensajes.size());
		if(listaMensajes.size()>0)
			request.getSession(false).setAttribute("validation.message", listaMensajes);		

		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.saveToken(request);
		return (forward);

	}
}
