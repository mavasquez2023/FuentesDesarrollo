/**
 * 
 */
package cl.laaraucana.claves.business;

import cl.laaraucana.claves.dao.ConsultaServicesDAO;
import cl.laaraucana.claves.dao.vo.RegistroVO;

/**
 * @author IBM Software Factory
 *
 */
public class UtilBitacora {
	
	public static int modificaRegistro(RegistroVO registro){
		int resultado=0;
		try {	
			ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
			//resultado=consultaDAO.updateBitacora(registro); se comenta porque se estaba duplicando el NUM_MODIFICACION
			//de tabla RegistroGestorClaves cuando se invoca el cambio en WS de cambio clave.
			resultado=consultaDAO.insertRecupera(registro);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
}
