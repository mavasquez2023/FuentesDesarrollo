/*
 * Creada el 03-04-2008
 *
 */
package cl.araucana.cierrecpe.empresas.planillas.isapre;


import java.util.ArrayList;
import java.util.List;

import cl.araucana.cierrecpe.empresas.planillas.PlanillaBase;
import cl.araucana.cierrecpe.empresas.planillas.PlanillaBasePaginaDetalle;


/**
 * @author Claudio Lillo
 *
 */
public class PlanillaIsaprePaginaDetalle extends PlanillaBasePaginaDetalle {
	
	private List cotizantes;
	private PlanillaIsapreDocumentModel cabeceraPlanilla;
	
	public void addCotizante(PlanillaIsapreCotizante cotizante){
    	if (cotizantes==null){
    		cotizantes= new ArrayList();
    	}
    	cotizantes.add(cotizante);
    }

	/**
	 * @return el cabeceraPlanilla
	 */
	public PlanillaBase getCabeceraPlanilla() {
		return cabeceraPlanilla;
	}

	/**
	 * @param cabeceraPlanilla el cabeceraPlanilla a establecer
	 */
	public void setCabeceraPlanilla(PlanillaIsapreDocumentModel cabeceraPlanilla) {
		this.cabeceraPlanilla = cabeceraPlanilla;
	}

	/**
	 * @return el cotizantes
	 */
	public List getCotizantes() {
		return cotizantes;
	}

	/**
	 * @param cotizantes el cotizantes a establecer
	 */
	public void setCotizantes(List cotizantes) {
		this.cotizantes = cotizantes;
	}

}
