package cl.araucana.bienestar.bonificaciones.ui.actions.preCasos;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.vo.DatosMovimientoTesoreriaVO;
import cl.araucana.bienestar.bonificaciones.vo.DatosProfesionalesVO;

/**
 * @author DESEX14
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class QuitarDetalleSeleccionadoAction extends Action {
	
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
			
		Logger logger = Logger.getLogger(AdministrarDatosProfesionalesAction.class);	
		String target = null;
		
		int indexProf = Integer.parseInt( 
						(String)request.getParameter("indexProf"));
		int indexDetalle = Integer.parseInt( 
						(String)request.getParameter("indexDetalle"));
						
		logger.debug("indexProf: "+indexProf);
		logger.debug("indexDetalle: "+indexDetalle);
		
		DatosMovimientoTesoreriaVO datosMovimientoTesoreriaVO = (DatosMovimientoTesoreriaVO)request.getSession(false).getAttribute("datosMovimientoTesoreriaVO");
		DatosProfesionalesVO profe =(DatosProfesionalesVO)datosMovimientoTesoreriaVO.getListaProfesionales().get(indexProf);
		((ArrayList)profe.getDetalles()).remove(indexDetalle);
	
		request.getSession(false).setAttribute("datosMovimientoTesoreriaVO", datosMovimientoTesoreriaVO);
		
		target="datosProfesionales";
		
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.saveToken(request);
		return (forward);

	}
}
