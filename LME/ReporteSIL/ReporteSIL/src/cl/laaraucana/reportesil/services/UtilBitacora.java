/**
 * 
 */
package cl.laaraucana.reportesil.services;

import cl.laaraucana.reportesil.dao.ConsultaServicesDAO;
import cl.laaraucana.reportesil.dao.vo.BitacoraVO;

/**
 * @author IBM Software Factory
 *
 */
public class UtilBitacora {
	public static int addRegistro(String rut, String nombre, String celular, String telefono, String email){
		int resultado=0;
		try {
			//Se guarda registro en BD
			BitacoraVO registro= new BitacoraVO();
			String[] rutdv= rut.split("-");
			registro.setRut(Integer.parseInt(rutdv[0]));
			registro.setDv(rutdv[1]);
			registro.setNombre(nombre);
			registro.setCelular(celular);
			registro.setTelefono(telefono);
			registro.setEmail(email);
			
			ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
			resultado=consultaDAO.insertBitacora(registro);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
}
