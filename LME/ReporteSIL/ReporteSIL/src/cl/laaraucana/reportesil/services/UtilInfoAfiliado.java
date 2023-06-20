/**
 * 
 */
package cl.laaraucana.reportesil.services;

import cl.laaraucana.reportesil.clientesws.ClienteInfoAfiliado;
import cl.laaraucana.reportesil.clientesws.vo.EntradaInfoAfiliadoVO;
import cl.laaraucana.reportesil.clientesws.vo.SalidainfoAfiliadoVO;

/**
 * @author IBM Software Factory
 *
 */
public class UtilInfoAfiliado {
	public static SalidainfoAfiliadoVO consultaAfiliado(String rut){
		SalidainfoAfiliadoVO salida=null;
		try {
			ClienteInfoAfiliado client= new ClienteInfoAfiliado();
			EntradaInfoAfiliadoVO entrada= new EntradaInfoAfiliadoVO();
			entrada.setRut(rut);
			salida = (SalidainfoAfiliadoVO)client.call(entrada);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return salida;
	}
	
}
