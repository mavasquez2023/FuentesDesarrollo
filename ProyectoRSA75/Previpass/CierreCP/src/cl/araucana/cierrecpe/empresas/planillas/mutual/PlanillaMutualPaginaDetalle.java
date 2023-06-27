/*
 * Creada el 03-04-2008
 *
 */
package cl.araucana.cierrecpe.empresas.planillas.mutual;


import java.util.ArrayList;
import java.util.List;

import cl.araucana.cierrecpe.empresas.planillas.PlanillaBase;
import cl.araucana.cierrecpe.empresas.planillas.PlanillaBasePaginaDetalle;


/**
 * @author Jorge Marcelo González Covili -jmgonzalezcovili@hotmail.com.
 *
 */
public class PlanillaMutualPaginaDetalle extends PlanillaBasePaginaDetalle{

	private List cotizantes;
	private PlanillaMutualDocumentModel cabeceraPlanilla;
	
	public void addCotizante(PlanillaMutualCotizante cotizante){
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
	public void setCabeceraPlanilla(PlanillaMutualDocumentModel cabeceraPlanilla) {
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
