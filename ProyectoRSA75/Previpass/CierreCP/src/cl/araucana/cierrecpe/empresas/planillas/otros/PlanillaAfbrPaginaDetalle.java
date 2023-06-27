/*
 * Creada el 03-04-2008
 *
 */
package cl.araucana.cierrecpe.empresas.planillas.otros;


import java.util.ArrayList;
import java.util.List;

import cl.araucana.cierrecpe.empresas.planillas.PlanillaBase;
import cl.araucana.cierrecpe.empresas.planillas.PlanillaBasePaginaDetalle;


/**
 * @author Claudio Lillo
 *
 */
public class PlanillaAfbrPaginaDetalle extends PlanillaBasePaginaDetalle {
	
	private List cotizantes;
	private PlanillaAfbrDocumentModel cabeceraPlanilla;
	
	public void addCotizante(PlanillaAfbrCotizante cotizante){
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
	public void setCabeceraPlanilla(PlanillaAfbrDocumentModel cabeceraPlanilla) {
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
