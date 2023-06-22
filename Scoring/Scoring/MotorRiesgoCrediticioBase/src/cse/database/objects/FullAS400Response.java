package cse.database.objects;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import cse.model.businessobject.EvaluacionResponse;

/**
 * Esta clase se encarga de producir y contener la respuesta plana que será
 * insertada en tabla AS/400, con el formato que La Araucana necesita.
 * 
 * @author Equipo Metric Arts
 * 
 */
public class FullAS400Response {

	private EvaluacionResponse response; // perfil, score, rentas, condiciones
	private Integer rut;
	private String dv;
	private String idSolicitud;
	private String plainResponse;
	private Integer endeudMax;

	// El constructor aprovecha de generar la respuesta plana
	public FullAS400Response(Integer rut, String dv, String idSolicitud, EvaluacionResponse response) {
		this.rut = rut;
		this.dv = dv;
		this.idSolicitud = idSolicitud;
		this.response = response;

		makePlainString();
	}

	public void makePlainString() {

		String p_rut = StringUtils.leftPad(String.valueOf(this.rut), 9, "0");
		String p_dv = StringUtils.leftPad(this.dv, 1, " ");
		String p_idSolicitud = this.idSolicitud;
		String p_perfil = StringUtils.leftPad(this.response.getPerfilRiesgo(), 1, " ");
		String p_score = StringUtils.leftPad(String.valueOf(this.response.getScore()), 5, "0");
		String p_rentas = StringUtils.leftPad(String.valueOf(this.response.getNumSueldos()), 2, "0");
		String p_endeudMax = StringUtils.leftPad(String.valueOf(this.endeudMax), 10, "0");

		// Padding de condiciones
		HashMap mapa = new HashMap();
		String[] cond = this.response.getListaCondiciones();

		for (int i = 0; i < cond.length; i++) {
			// Aval
			if (cond[i].trim().equals("A"))
				mapa.put("A", "SI");
			else
				mapa.put("A", "NO");

			// Seguro Cesantía
			if (cond[i].trim().equals("SC"))
				mapa.put("SC", "SI");
			else
				mapa.put("SC", "NO");

			// Aval y Seguro Cesantía
			if (cond[i].trim().equals("ASC"))
				mapa.put("ASC", "SI");
			else
				mapa.put("ASC", "NO");

			// Revisión departamento Riesgo
			if (cond[i].trim().equals("DR"))
				mapa.put("DR", "SI");
			else
				mapa.put("DR", "NO");

			// Comité de Crédito
			if (cond[i].trim().equals("CC"))
				mapa.put("CC", "SI");
			else
				mapa.put("CC", "NO");
		}

		StringBuffer buf = new StringBuffer();
		buf.append(p_rut);
		buf.append(p_dv);
		buf.append(p_idSolicitud);
		buf.append(p_perfil);
		buf.append(p_score);
		buf.append(p_rentas);
		buf.append(p_endeudMax);

		buf.append(mapa.get("A"));
		buf.append(mapa.get("SC"));
		buf.append(mapa.get("ASC"));
		buf.append(mapa.get("DR"));
		buf.append(mapa.get("CC"));

		buf.append(StringUtils.leftPad("0", 141, "0"));

		this.plainResponse = buf.toString();
	}

	public EvaluacionResponse getResponse() {
		return response;
	}

	public void setResponse(EvaluacionResponse response) {
		this.response = response;
	}

	public Integer getRut() {
		return rut;
	}

	public void setRut(Integer rut) {
		this.rut = rut;
	}

	public String getDv() {
		return dv;
	}

	public void setDv(String dv) {
		this.dv = dv;
	}

	public void setIdSolicitud(String idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public String getIdSolicitud() {
		return idSolicitud;
	}

	public void setPlainResponse(String plainResponse) {
		this.plainResponse = plainResponse;
	}

	public String getPlainResponse() {
		return plainResponse;
	}

	public void setEndeudMax(Integer endeudMax) {
		this.endeudMax = endeudMax;
	}

	public Integer getEndeudMax() {
		return endeudMax;
	}
}
