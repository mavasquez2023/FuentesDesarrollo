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
import cl.araucana.bienestar.bonificaciones.vo.DetalleMovimientoPreCasoVO;

/**
 * @author DESEX14
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class SetearDetalleSeleccionadoAction extends Action {
	
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm) form;
			
		Logger logger = Logger.getLogger(AdministrarDatosProfesionalesAction.class);	
		String target = null;
		
		int indexProf = Integer.parseInt( 
						(String)request.getSession(false).getAttribute("indexProf"));
						
		logger.debug("Monto: "	+ (String)dynaValidatorActionForm.get("monto"));						
		logger.debug("indice: "	+ (String)dynaValidatorActionForm.get("indice"));
		logger.debug("opcion: "	+ (String)dynaValidatorActionForm.get("opcion"));

		int opcion = Integer.parseInt( 
						(String)dynaValidatorActionForm.get("opcion"));
								
		switch (opcion){
			case 1:
				//Agregar Detalle
				int indice = Integer.parseInt( 
								(String)dynaValidatorActionForm.get("indice"));
												
				double monto = Double.parseDouble( 
								(String)dynaValidatorActionForm.get("monto"));
	
				DatosMovimientoTesoreriaVO datosMovimientoTesoreriaVO = (DatosMovimientoTesoreriaVO)request.getSession(false).getAttribute("datosMovimientoTesoreriaVO");
				DatosProfesionalesVO profe =(DatosProfesionalesVO)datosMovimientoTesoreriaVO.getListaProfesionales().get(indexProf);
							
				ArrayList listaDetalles = (ArrayList)request.getSession(false).getAttribute("listaDetalles");
				DetalleMovimientoPreCasoVO detalle = (DetalleMovimientoPreCasoVO)listaDetalles.get(indice);
				detalle.setMonto(monto);
				profe.getDetalles().add(detalle);		
		
				request.getSession(false).setAttribute("datosMovimientoTesoreriaVO", datosMovimientoTesoreriaVO);			
				break;
			case 2:
				//Cancelar
				break;
		}
		
		target="datosProfesionales";
		
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.saveToken(request);
		return (forward);

	}
}
