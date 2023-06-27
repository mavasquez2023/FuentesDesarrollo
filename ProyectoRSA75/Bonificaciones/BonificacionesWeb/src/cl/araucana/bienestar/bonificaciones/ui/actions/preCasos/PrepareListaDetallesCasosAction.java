package cl.araucana.bienestar.bonificaciones.ui.actions.preCasos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.model.DetalleCaso;
import cl.araucana.bienestar.bonificaciones.vo.CasoVO;
import cl.araucana.bienestar.bonificaciones.vo.DatosMovimientoTesoreriaVO;
import cl.araucana.bienestar.bonificaciones.vo.DatosProfesionalesVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleMovimientoPreCasoVO;

/**
 * @version 	1.0
 * @author		Alejandro Sepúlveda 
 */
public class PrepareListaDetallesCasosAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		Logger logger = Logger.getLogger(PrepareListaDetallesCasosAction.class);
						
		ArrayList opciones=new ArrayList();
		ArrayList opcionesValores=new ArrayList();
		String target=null;
				
		logger.debug("indexProf: "+(String)request.getParameter("indexProf"));
		request.getSession(false).setAttribute("indexProf", (String)request.getParameter("indexProf"));
		
		DatosMovimientoTesoreriaVO datosMovimientoTesoreriaVO = (DatosMovimientoTesoreriaVO)request.getSession(false).getAttribute("datosMovimientoTesoreriaVO");
		ArrayList listaCasos = datosMovimientoTesoreriaVO.getListaCasos();
		ArrayList listaDetalles = new ArrayList ();
		for(int x=0; x<listaCasos.size();x++){
			CasoVO casoVo = (CasoVO)listaCasos.get(x);
			ArrayList listaDetalleCaso = casoVo.getDetalleCaso();
			for(int y=0;y<listaDetalleCaso.size();y++){
				DetalleCaso detalleCaso = (DetalleCaso)listaDetalleCaso.get(y); 
				DetalleMovimientoPreCasoVO detalle = new DetalleMovimientoPreCasoVO();
				detalle.setCasoId( casoVo.getCasoID() );
				detalle.setCoberturaCodigo( detalleCaso.getProducto().getCobertura().getCodigo() );
				detalle.setDescripcion( detalleCaso.getProducto().getCobertura().getDescripcion() );
				detalle.setIdDetalle(detalleCaso.getIdDetalle());
				int sumaEgresosAnterioresDetalle = 
						sumaEgresosAnterioresDetallesMemoria(datosMovimientoTesoreriaVO.getListaProfesionales(),
															casoVo.getCasoID(),
															detalleCaso.getProducto().getCobertura().getCodigo(),
															detalleCaso.getIdDetalle());
				detalle.setMonto(detalleCaso.getTotalMenosIsapreYDescuento()-sumaEgresosAnterioresDetalle);
				listaDetalles.add(detalle);
			}
		}
		
		request.getSession(false).setAttribute("listaDetalles", listaDetalles);

		opciones.add("Agregar Detalle");
		opcionesValores.add("1");
		opciones.add("Cancelar");
		opcionesValores.add("2");
		
		request.getSession(false).setAttribute("opciones.datos.detalles", opciones);
		request.getSession(false).setAttribute("opciones.valores.detalles", opcionesValores);		

		target = "listaDetallesCasos";
		
		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);
	}
	
	private int sumaEgresosAnterioresDetallesMemoria(ArrayList listaProfesionales, long casoId, long codigoCobertura, int idDetalle){
		int total=0;
		
		for(int x=0;x<listaProfesionales.size();x++){
			DatosProfesionalesVO datosProfesionalesVO = (DatosProfesionalesVO)listaProfesionales.get(x);
			Collection detalles = datosProfesionalesVO.getDetalles();
			if(!detalles.isEmpty()){
				Iterator i = detalles.iterator();
				while(i.hasNext()){
					DetalleMovimientoPreCasoVO detVo = (DetalleMovimientoPreCasoVO)i.next();
					if(detVo.getCasoId()==casoId && detVo.getCoberturaCodigo()==codigoCobertura && detVo.getIdDetalle()==idDetalle){
						total = total + (int)detVo.getMonto();
					}
				}
			}
		}
		
		return total;
	}
}
