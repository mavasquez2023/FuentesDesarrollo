package cl.laaraucana.simulacion.webservices.client.QueryBPStatusOUT;

import cl.laaraucana.simulacion.webservices.client.QueryBPStatusOUT.VO.EntradaAfiliadoVO;
import cl.laaraucana.simulacion.webservices.client.QueryBPStatusOUT.VO.SalidaListaAfiliadoVO;

public class ServicioQueryBPStatus {

	public static SalidaListaAfiliadoVO obtenerAfiliadoByRutSap(String rut) throws Exception {
		EntradaAfiliadoVO entradaVO = new EntradaAfiliadoVO();
		entradaVO.setRut(rut);
		
		ClienteQueryBPStatusOUT cliente = new ClienteQueryBPStatusOUT();
		SalidaListaAfiliadoVO salidaVO = new SalidaListaAfiliadoVO();
			salidaVO = (SalidaListaAfiliadoVO) cliente.call(entradaVO);
		
		return salidaVO;
	}
}
