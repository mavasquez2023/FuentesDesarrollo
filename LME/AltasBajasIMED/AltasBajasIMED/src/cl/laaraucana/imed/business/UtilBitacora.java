/**
 * 
 */
package cl.laaraucana.imed.business;

import org.apache.log4j.Logger;

import cl.laaraucana.imed.clientews.vo.SalidaImedVO;
import cl.laaraucana.imed.dao.ConsultaServicesDAO;
import cl.laaraucana.imed.dao.vo.BitacoraVO;
import cl.laaraucana.imed.dao.vo.RegistroAltaBajaVO;

/**
 * @author IBM Software Factory
 *
 */
public class UtilBitacora {
	protected static Logger logger = Logger.getLogger("UtilBitacora");
	public static int addRegistro(RegistroAltaBajaVO entradaAltasImedVO, SalidaImedVO salida){
		int resultado=0;
		try {
			//Se guarda registro en BD
			ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
			BitacoraVO bitacora= new BitacoraVO();
			bitacora.setRutBeneficiario(entradaAltasImedVO.getRutBeneficiario());
			bitacora.setRutTitular(entradaAltasImedVO.getRutTitular());
			bitacora.setTipoEvento(entradaAltasImedVO.getTipoEvento());
			bitacora.setEstado((short)salida.getEstado());
			bitacora.setMensaje(salida.getMensaje());
			consultaDAO.insertBitacora(bitacora);
		} catch (Exception e) {
			logger.error("Error al insertar bitácora Imed, mensaje: " + e.getMessage());
			e.printStackTrace();
		}
		return resultado;
	}
	
}
