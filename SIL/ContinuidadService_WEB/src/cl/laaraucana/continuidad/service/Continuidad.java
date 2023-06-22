package cl.laaraucana.continuidad.service;

import cl.laaraucana.continuidad.manager.ContinuidadMgr;
import cl.laaraucana.continuidad.service.vo.EntradaContRentas;
import cl.laaraucana.continuidad.service.vo.SalidaContRentas;


public class Continuidad {
	
	public SalidaContRentas continuidad(EntradaContRentas entrada){
		ContinuidadMgr mgr = new ContinuidadMgr();
		SalidaContRentas salida = mgr.continuidad(entrada);
		return salida;
	}

}
