package cl.laaraucana.capaservicios.webservices.service;

import cl.laaraucana.capaservicios.manager.DatosAuxiliaresMGR;
import cl.laaraucana.capaservicios.webservices.vo.DatosAuxiliares.ListaCodigosSTLOut;

public class DatosAuxiliares {
	
	public ListaCodigosSTLOut getListaBancos(){
		DatosAuxiliaresMGR mgr = new DatosAuxiliaresMGR();
		ListaCodigosSTLOut bancos = mgr.getListaBancos();
		
		return bancos;
	}
	
	public ListaCodigosSTLOut getTiposCuenta(){
		DatosAuxiliaresMGR mgr = new DatosAuxiliaresMGR();
		ListaCodigosSTLOut tiposCuenta = mgr.getTiposCuenta();
		
		return tiposCuenta;
	}
	
	public ListaCodigosSTLOut getComunasProvRegiones(String tipoEjecucion){
		DatosAuxiliaresMGR mgr = new DatosAuxiliaresMGR();
		ListaCodigosSTLOut tiposCuenta = mgr.getComunasProvRegiones(tipoEjecucion);
		return tiposCuenta;
	}
	
}
