/**
 * 
 */
package cl.laaraucana.sinacofi.business;

import cl.laaraucana.sinacofi.clientesws.ClienteSinacofi;
import cl.laaraucana.sinacofi.clientesws.vo.EntradaSinacofiVO;
import cl.laaraucana.sinacofi.clientesws.vo.SalidaSinacofiVO;


/**
 * @author IBM Software Factory
 *
 */
public class UtilInfoAfiliado {
	
	public static SalidaSinacofiVO consultaSinacofi(String url, String usuario, String clave, String rut, String serie){
		SalidaSinacofiVO salida=null;
		try {
			rut= rut.replaceAll("-", "");
			
			ClienteSinacofi client= new ClienteSinacofi();
			EntradaSinacofiVO entrada= new EntradaSinacofiVO();
			entrada.setUrl(url);
			entrada.setUsuario(usuario);
			entrada.setClave(clave);
			entrada.setRut(rut);
			entrada.setSerie(serie);
			salida = (SalidaSinacofiVO)client.call(entrada);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return salida;
	}
}
