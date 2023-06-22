package cl.laaraucana.mediopagosilws.core.manager;

import java.util.HashMap;
import java.util.Map;

import cl.laaraucana.mediopagosilws.core.database.dao.DAOFactory;
import cl.laaraucana.mediopagosilws.core.database.dao.MedioPagoDaoI;
import cl.laaraucana.mediopagosilws.core.database.exception.DaoException;
import cl.laaraucana.mediopagosilws.service.vo.DatosVO;
import cl.laaraucana.mediopagosilws.service.vo.EntradaVO;
import cl.laaraucana.mediopagosilws.service.vo.SalidaVO;
import cl.laaraucana.mediopagosilws.utils.Constante;
import cl.laaraucana.mediopagosilws.utils.Funciones;

public class MainManager {

	public static SalidaVO consultaMedioPago(EntradaVO entrada) {
		SalidaVO salida = new SalidaVO();
		try {
			//obtiene instancia DAO
			MedioPagoDaoI dao = DAOFactory.getDaoFactory().getMedioPagoDao();
			//crear map INOUT
			formatEntrada(entrada);

			Map<String, String> map = new HashMap<String, String>();
			map.put("RUTEMP", entrada.getRutEmpresa());
			map.put("DVEMP", entrada.getRutEmpresaDv());
			map.put("OFICINA", entrada.getOficinaEmisora());
			map.put("SUCURSAL", entrada.getSucursal());
			map.put("MEDPAGO", Funciones.rellenar("", 1, Constante.RELLENO_INTEGER));
			map.put("OFIPAGO", Funciones.rellenar("", 3, Constante.RELLENO_INTEGER));
			map.put("RESP", Funciones.rellenar("", 1, Constante.RELLENO_INTEGER));
			map.put("GLOSA", Funciones.rellenar("", 60, Constante.RELLENO_STRING));

			System.out.println(map);
			//consulta DAO
			try {
				dao.consultaMedioPago(map);
			} catch (DaoException e) {
				salida.setCodigoRespuesta(Constante.CODIGO_RESPUESTA_ERROR);
				salida.setGlosaRespuesta("Error consultaMedioPagoDao DaoException: " + e.getMessage());
				return salida;
			}
			//set datos
			DatosVO datos = new DatosVO();
			datos.setMedioPago(map.get("MEDPAGO"));
			datos.setOficinaPago(map.get("OFIPAGO"));
			salida.setDatos(datos);
			//set log
			salida.setCodigoRespuesta(map.get("RESP"));
			salida.setGlosaRespuesta(map.get("GLOSA"));

		} catch (Exception e) {
			salida.setCodigoRespuesta(Constante.CODIGO_RESPUESTA_ERROR);
			salida.setGlosaRespuesta("Error inesperado: " + e.getMessage());
		}
		return salida;

	}

	public static EntradaVO formatEntrada(EntradaVO entrada) {
		entrada.setRutEmpresa(Funciones.rellenar(entrada.getRutEmpresa(), 9, Constante.RELLENO_INTEGER));
		entrada.setOficinaEmisora(Funciones.rellenar(entrada.getOficinaEmisora(), 3, Constante.RELLENO_INTEGER));
		entrada.setSucursal(Funciones.rellenar(entrada.getSucursal(), 3, Constante.RELLENO_INTEGER));
		return entrada;
	}

	/**
	 * valida los datos de entrada, si no existe error retorna NULL
	 * @param entrada
	 * @return String con mnsaje de error
	 */
	public static String validaEntrada(EntradaVO entrada) {
		String msg = null;
		msg = Funciones.isInteger(entrada.getOficinaEmisora()) ? msg : "El campo \"Oficina Emisora\" no es numerico ";
		msg = Funciones.isInteger(entrada.getRutEmpresa()) ? msg : "El campo \"Rut Empresa\" no es numerico ";
		msg = Funciones.isInteger(entrada.getSucursal()) ? msg : "El campo \"Sucursal\" no es numerico ";
		if(msg!=null)return msg;
		msg = Funciones.maxLength(entrada.getOficinaEmisora(), 3) ? msg : "El campo \"Oficina Emisora\" excede el largo \"3\"";
		msg = Funciones.maxLength(entrada.getRutEmpresa(), 9) ? msg : "El campo \"Rut Empresa\" excede el largo \"9\"";
		msg = Funciones.maxLength(entrada.getRutEmpresaDv(), 1) ? msg : "El campo \"RutEmpresaDv\" excede el largo \"1\"";
		msg = Funciones.maxLength(entrada.getSucursal(), 3) ? msg : "El campo \"Sucursal\" excede el largo \"3\"";
		if(msg!=null)return msg;
		msg = Funciones.validarRut(Integer.valueOf(entrada.getRutEmpresa().trim()), entrada.getRutEmpresaDv().trim().toCharArray()[0]) ? msg : "El \"rut\" ingresado no es válido";		
		return msg;
	}

}
