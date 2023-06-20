/**
 * 
 */
package cl.laaraucana.claves.business;

import cl.laaraucana.claves.clientesws.ClienteInfoAfiliado;
import cl.laaraucana.claves.clientesws.ClienteSinacofi;
import cl.laaraucana.claves.clientesws.vo.EntradaInfoAfiliadoVO;
import cl.laaraucana.claves.clientesws.vo.EntradaSinacofiVO;
import cl.laaraucana.claves.clientesws.vo.SalidaSinacofiVO;
import cl.laaraucana.claves.clientesws.vo.SalidainfoAfiliadoVO;

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
	
	public static SalidaSinacofiVO consultaSinacofi(String rut, String serie){
		SalidaSinacofiVO salida=null;
		try {
			rut= rut.replaceAll("-", "");
			
			ClienteSinacofi client= new ClienteSinacofi();
			salida = (SalidaSinacofiVO)client.call(rut, serie);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return salida;
	}
}
