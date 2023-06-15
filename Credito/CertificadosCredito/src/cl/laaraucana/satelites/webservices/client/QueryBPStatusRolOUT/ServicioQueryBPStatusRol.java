package cl.laaraucana.satelites.webservices.client.QueryBPStatusRolOUT;


import cl.laaraucana.satelites.webservices.client.QueryBPStatusRolOUT.VO.EntradaAfiliadoRolVO;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusRolOUT.VO.SalidaAfiliadoRolVO;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusRolOUT.VO.SalidaListaAfiliadoRolVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;


public class ServicioQueryBPStatusRol {

	public static SalidaAfiliadoRolVO obtenerFechaRol(String rut, String rol) throws Exception {
		EntradaAfiliadoRolVO entradaVO = new EntradaAfiliadoRolVO();
		
		entradaVO.setRut(rut);
		entradaVO.setRol(rol);
		ClienteQueryBPStatusRolOUT cliente = new ClienteQueryBPStatusRolOUT();
		SalidaListaAfiliadoRolVO salidaVO = new SalidaListaAfiliadoRolVO();
			salidaVO = (SalidaListaAfiliadoRolVO) cliente.call(entradaVO);
			if(salidaVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)){
				return null;
			}
		return salidaVO.getListaAfiliado().get(0);
	}
}
