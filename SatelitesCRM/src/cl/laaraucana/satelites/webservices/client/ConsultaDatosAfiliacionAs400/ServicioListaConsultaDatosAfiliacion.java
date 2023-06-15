package cl.laaraucana.satelites.webservices.client.ConsultaDatosAfiliacionAs400;

import cl.laaraucana.satelites.Utils.CompletaUtil;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.webservices.client.ConsultaDatosAfiliacionAs400.VO.EntradaConsultaDatosAfiliacionAs400;
import cl.laaraucana.satelites.webservices.client.ConsultaDatosAfiliacionAs400.VO.SalidaListaConsultaDatosAfiliacionAs400;

public class ServicioListaConsultaDatosAfiliacion {
	
	public static SalidaListaConsultaDatosAfiliacionAs400 obtenerAfiliadoByRutAs400(String rut) throws Exception {
		EntradaConsultaDatosAfiliacionAs400 entradaVO = new EntradaConsultaDatosAfiliacionAs400();
		//rut = CompletaUtil.llenaConCeros(rut, 11, true);
		rut = Utils.obtenerValorAnteriorA(rut, "-");
		entradaVO.setRut(CompletaUtil.llenaConCeros(rut, 9, true));
		
		ClienteConsultaDatosAfiliacionAs400 cliente = new ClienteConsultaDatosAfiliacionAs400();
		SalidaListaConsultaDatosAfiliacionAs400 salidaVO = new SalidaListaConsultaDatosAfiliacionAs400();
		salidaVO = (SalidaListaConsultaDatosAfiliacionAs400) cliente.call(entradaVO);
		
		return salidaVO;
	}

}
