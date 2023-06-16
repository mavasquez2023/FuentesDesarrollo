package cl.laaraucana.botonpago.web.manager;

import java.util.HashMap;

import org.apache.log4j.Logger;

import cl.laaraucana.botonpago.web.database.dao.Procedimientos_GTI050_DAO;
import cl.laaraucana.botonpago.web.database.vo.DatosTransferenciaVO;
import cl.laaraucana.botonpago.web.utils.Util;

/**
 * clase que se encarga de registrar en libro banco una transaccion 
 * 
 * **/
public class ManagerLibroBanco {

	/**
	 * se actualiza el banco on los datos de la transferencia y llamando a un procedimiento almacenado
	 * @param transf
	 * @return
	 */

	private static Logger logger = Logger.getLogger(ManagerLibroBanco.class);

	public static DatosTransferenciaVO registrarLibroBanco(DatosTransferenciaVO transf) {

		HashMap<String, String> params = new HashMap<String, String>();
		String dato1 = "";
		String dato2 = "";
		String dato3 = "";
		String dato4 = "";
		String dato5 = "";
		String dato7 = "";
		String dato6 = "";
		String dato8 = "";
		String dato9 = "";

		logger.debug("libroBanco");
		try {
			//obtencion parametros libro banco desde constantes.			
			/*para test consola, comentar para instalcion*/
			dato1 = Util.rellenarCampos(transf.getBanco(), 3, "0");
			dato2 = Util.rellenarCampos(transf.getNumCtaCte(), 15, " ");
			dato3 = transf.getFechaMovimiento();
			dato4 = Util.getFormatoMontoEntero(transf.getMonto(), 13);
			dato5 = transf.getTipoAbono();
			dato6 = Util.getFormatoMontoEntero(transf.getNumeroDeposito(), 11);
			dato7 = Util.rellenarCampos(transf.getCodigoOperacionInterna(), 5, "0");
			dato8 = getGlosaLibroBanco(transf.getFolioTesoreria(), transf.getRutCliente(), transf.getDvCliente());
			dato9 = "0";

			/*cargamos valores formateados.*/
			params.put("DATO1", dato1);
			params.put("DATO2", dato2);
			params.put("DATO3", dato3);
			params.put("DATO4", dato4);
			params.put("DATO5", dato5);
			params.put("DATO6", dato6);
			params.put("DATO7", dato7);
			params.put("DATO8", dato8);
			params.put("DATO9", dato9);

			logger.debug("[*] Call proLibroBanco");
			String respuesta = Procedimientos_GTI050_DAO.callProLibroBanco(params);

			logger.debug("\nrespuesta Dato9: [" + respuesta + "]\n");

			if (respuesta.equals("0")) {

				//sin error en tep990
				transf.setFlag(true);
				transf.setEstadoCodigo("LIB");
			} else {
				//error al actualizar libro banco con tep990
				transf.setFlag(false);
				transf.setEstadoCodigo("LIB");
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("CATCH: " + e);
			transf.setFlag(false);
			transf.setEstadoCodigo("LIB");
		}
		return (transf);
	}//end RegistrarLibroBanco

	private static String getGlosaLibroBanco(String folio, String rut, String dv) throws Exception {
		String g = "PDD-" + folio + "-" + rut + dv + "";
		return (Util.rellenarCampos(g, 78, " "));
	}

}//end Class LibroBanco
