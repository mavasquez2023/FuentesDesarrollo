package cl.laaraucana.utils;

import cl.araucana.autoconsulta.vo.UsuarioVO;

public class ValidaSesionServicio {
	public static boolean validar(UsuarioVO usr, String serviciosValidos, int servicioAValidar) {
		if (usr == null || serviciosValidos == null ) {
			System.out.println("ValidaSesionServicio: sin usr sin servicios");
			return false;
		}
		if (serviciosValidos.length()==0 || usr.getRutusuarioAutenticado()==0) {
			System.out.println("ValidaSesionServicio: sin rut sin servicios");
			return false;
		}
		System.out.println("ValidaSesionServicio: [" + serviciosValidos.indexOf("" + servicioAValidar + ",") + "] servicios[" + serviciosValidos + "]");
		return (serviciosValidos.indexOf("" + servicioAValidar + ",") >=0);	
	}
}
