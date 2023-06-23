package cl.laaraucana.capaservicios.manager;

import cl.laaraucana.capaservicios.database.dao.AuxiliarSTLDao;
import cl.laaraucana.capaservicios.util.Constantes;
import cl.laaraucana.capaservicios.webservices.vo.Log;
import cl.laaraucana.capaservicios.webservices.vo.DatosAuxiliares.ListaCodigosSTLOut;

public class DatosAuxiliaresMGR {
	/**
	 * Obtiene lista de bancos desde modelo de datos de STL
	 * 
	 * @return
	 */
	public ListaCodigosSTLOut getListaBancos(){
		ListaCodigosSTLOut salida = null;
		AuxiliarSTLDao dao = new AuxiliarSTLDao();
		Log log = null;
		try {
			salida = new ListaCodigosSTLOut();
			salida.setCodigos(dao.getListaBancos());
			log = new Log(Constantes.COD_RESPUESTA_SUCCESS, "Datos obtenidos correctamente");
		} catch (Exception e) {
			log = new Log(Constantes.COD_RESPUESTA_ERROR, "Error al obtener cuentas corrientes de AS400");
		}
		salida.setLog(log);
		return salida;
	}
	
	/**
	 * Obtiene lista de tipos de cuenta desde modelo de datos de STL
	 * @return lista de código y descripción
	 */
	public ListaCodigosSTLOut getTiposCuenta(){
		ListaCodigosSTLOut salida = null;
		AuxiliarSTLDao dao = new AuxiliarSTLDao();
		Log log = null;
		
		try {
			salida = new ListaCodigosSTLOut();
			salida.setCodigos(dao.getTiposCuenta());
			log = new Log(Constantes.COD_RESPUESTA_SUCCESS, "Datos obtenidos correctamente");
		} catch (Exception e) {
			log = new Log(Constantes.COD_RESPUESTA_ERROR, "Error al obtener cuentas corrientes de AS400");
		}
		salida.setLog(log);
		return salida;
	}
	
	/**
	 * Obtiene códigos de ciudades, provincias y regiones.
	 * @param tipoEjecucion: 1 = comunas, 2 = provincias, 3 = regiones
	 * @return
	 */
	public ListaCodigosSTLOut getComunasProvRegiones(String tipoEjecucion){
		ListaCodigosSTLOut salida = null;
		AuxiliarSTLDao dao = new AuxiliarSTLDao();
		Log log = null;
		
		try {
			salida = new ListaCodigosSTLOut();
			if(tipoEjecucion.equals("1")){
				salida.setCodigos(dao.getComunas());
			}else if(tipoEjecucion.equals("2")){
				salida.setCodigos(dao.getProvincias());
			}else if(tipoEjecucion.equals("3")){
				salida.setCodigos(dao.getRegiones());
			}else{
				log = new Log(Constantes.COD_RESPUESTA_ERROR, "Código de ejecución no válido");
				salida.setLog(log);
				return salida;
			}
			log = new Log(Constantes.COD_RESPUESTA_SUCCESS, "Datos obtenidos correctamente");
		} catch (Exception e) {
			log = new Log(Constantes.COD_RESPUESTA_ERROR, "Error al obtener datos de AS400");
		}
		salida.setLog(log);
		return salida;
	}	
}
