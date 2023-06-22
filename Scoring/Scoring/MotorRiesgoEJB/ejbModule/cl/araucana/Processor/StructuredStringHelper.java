package cl.araucana.Processor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

import cse.model.businessobject.CondicionOtorgamiento;
import cse.model.service.data.EvaluarCondicionesResponse;

public class StructuredStringHelper {

	/**
	 * Esta funcion obtiene los parámetros de invocacioon
	 * 
	 * @param payload
	 * @return
	 */
	public static Map partition(String payload) {

		Map mapa = new HashMap();

		mapa.put("rut", payload.substring(0, 9));
		mapa.put("digito", payload.substring(9, 10));
		mapa.put("tipoAfiliado", payload.substring(10, 11));
		mapa.put("genero", payload.substring(11, 12));
		mapa.put("fechaNac", payload.substring(12, 20));
		mapa.put("estadoCivil", payload.substring(20, 21));
		mapa.put("remuneracion", payload.substring(21, 33));
		mapa.put("monto", payload.substring(33, 42));
		mapa.put("diasMorosidad", payload.substring(42, 50));
		mapa.put("numCreditosAnteriores", payload.substring(50, 52));
		mapa.put("numDiasLicencia", payload.substring(52, 57));
		mapa.put("antiguedadLaboral", payload.substring(57, 65));
		mapa.put("rutEmpleador", payload.substring(65, 74));
		mapa.put("digitoEmpleador", payload.substring(74, 75));
		mapa.put("clasifRiesgoEmpresa", payload.substring(75, 77));

		return mapa;
	}

	/**
	 * Devuelve un HashMap con las condiciones de otorgamiento crediticio
	 * entregadas como parte de la respuesta
	 * 
	 * @param respuesta
	 * @return
	 */
	public static HashMap parseCondiciones(EvaluarCondicionesResponse respuesta) {

		HashMap mapa = new HashMap();
		CondicionOtorgamiento[] cond = respuesta.getCondiciones();

		mapa.put("A", "NO");
		mapa.put("SC", "NO");
		mapa.put("ASC", "NO");
		mapa.put("DR", "NO");
		mapa.put("CC", "NO");

		for (int i = 0; i < cond.length; i++) {
			// Aval
			if (cond[i].getNombre().trim().equals("A"))
				mapa.put("A", "SI");

			// Seguro Cesantia
			if (cond[i].getNombre().trim().equals("SC"))
				mapa.put("SC", "SI");

			// Aval y Seguro Cesantia
			if (cond[i].getNombre().trim().equals("ASC"))
				mapa.put("ASC", "SI");

			// Revisión departamento Riesgo
			if (cond[i].getNombre().trim().equals("DR"))
				mapa.put("DR", "SI");

			// Comité de Credito
			if (cond[i].getNombre().trim().equals("CC"))
				mapa.put("CC", "SI");
		}

		return mapa;
	}

	public static String parseId(String caso, String id) {

		if (caso.equals("fecha")) {
			return id.substring(0, 8);
		} else if (caso.equals("hora")) {
			return id.substring(8, 14);
		} else if (caso.equals("rut")) {
			return id.substring(14, 23);
		} else if (caso.equals("ofipro")) {
			return id.substring(23, 26);
		} else
			return null;
	}
	
	
	/**
	 * Construye un string de largo 100 que es el primer segmento del mensaje
	 * con que le respondo al servicio AS400 con el resultado de la evaluacion
	 * crediticia
	 * 
	 * @param sistema
	 * @param idServicio
	 * @param usuario
	 * @param dirMaquina
	 * @return
	 */
	public static String buildFirstId() {

		String fecha = null;
		String hora = null;
		String timestamp = null;

		// Formatos para fecha y hora
		String formatoFecha = "yyyy-MM-dd";
		String formatoHora = "HH:mm:ss";
		String formatoTS = "yyyy-MM-dd-HH.mm.ss.SSS";

		// Formación de strings formateados
		Date hoy = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha);
		fecha = sdf.format(hoy);
		sdf = new SimpleDateFormat(formatoHora);
		hora = sdf.format(hoy);
		sdf = new SimpleDateFormat(formatoTS);
		timestamp = sdf.format(hoy) + "000";

		StringBuffer buf = new StringBuffer();

		buf.append(StringUtils.leftPad("CRE", 3, " "));
		buf.append(StringUtils.leftPad("SCORB", 5, " "));
		buf.append(fecha);
		buf.append(hora);
		buf.append(StringUtils.rightPad(System.getProperty("cl.araucana.as400.user"), 10, " "));
		buf.append(StringUtils.rightPad("SERVER SQL", 10, " "));
		buf.append(timestamp);
		buf.append(StringUtils.rightPad(" ", 100 - buf.length()));

		return buf.toString();
	}

	/**
	 * Construye el String de respuesta para el AS/400. Esta respuesta consiste
	 * de: 100 chars : Identificador creado en el lado del motor único de
	 * llamada 100 chars : id recuperado de la consulta, el cual se debe copiar
	 * integramente.
	 * 
	 * @param id
	 * @param sistema
	 * @param idServicio
	 * @param usuario
	 * @param dirMaquina
	 * @param Payload
	 * @return
	 */
	public static String buildAS400Response(String id, String as400id, HashMap payload,
			String payloadString, EvaluarCondicionesResponse respuesta) {

		StringBuffer buf = new StringBuffer();

		buf.append(buildFirstId());
		buf.append(as400id);
		buf.append(StringUtils.rightPad(payloadString,100));
		buf.append(buildResponseString(payload, respuesta));
		buf.append(StringUtils.rightPad(" ", 4000 - buf.length()));

		
		return buf.toString();
	}

	/**
	 * Si la respuesta es null, entonces construyo una respuesta de error con un
	 * 1 al principio y puros ESPACIOS después.
	 * 
	 * @param respuesta
	 * @return
	 */
	public static String buildResponseString(HashMap payload, EvaluarCondicionesResponse respuesta) {

		StringBuffer buf = null;
		if (respuesta == null) {
			buf = new StringBuffer();
			buf.append("1");
			buf.append(StringUtils.rightPad("", 99));
		}

		else {
			HashMap mapaCond = StructuredStringHelper.parseCondiciones(respuesta);
			
			buf = new StringBuffer();
			buf.append("0");
			buf.append(StringUtils.leftPad((String) payload.get("rut"), 9, "0"));
			buf.append((String)payload.get("digito"));
			buf.append((String)respuesta.getIdSolicitud());
			buf.append((String)respuesta.getPerfilRiesgo());
			buf.append(StringUtils.leftPad(String.valueOf(respuesta.getScore().intValue()),5, "0"));
			buf.append(StringUtils.leftPad(respuesta.getNumSueldos().toString(),2,"0"));
			buf.append(StringUtils.leftPad(respuesta.getEndeudMax().toString(),10,"0"));
			buf.append((String) mapaCond.get("A"));
			buf.append((String) mapaCond.get("SC"));
			buf.append((String) mapaCond.get("ASC"));
			buf.append((String) mapaCond.get("DR"));
			buf.append((String) mapaCond.get("CC"));
			buf.append(StringUtils.leftPad(" ", 100-buf.length()));
		}
		return buf.toString();
	}

	/**
	 * Obtiene el codigo numerico de 4 digitos con la respuesta del programa DIP9999 a
	 * la respuesta del motor con los contenidos de la evaluacion crediticia
	 * @param resultado
	 * @return
	 */
	public static String parseCodigoRespuestaAS400(String resultado) {
		return resultado.substring(400, 404);
	}

}
