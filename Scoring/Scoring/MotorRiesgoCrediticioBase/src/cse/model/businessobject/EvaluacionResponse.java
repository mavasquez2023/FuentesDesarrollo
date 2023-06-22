package cse.model.businessobject;

public class EvaluacionResponse {

	private String[] listaCondiciones;
	private String perfilRiesgo;
	private Double score;
	private Double numSueldos;
	
	public EvaluacionResponse() {
		super();
	}

	public String getPerfilRiesgo() {
		return perfilRiesgo;
	}

	public void setPerfilRiesgo(String perfilRiesgo) {
		this.perfilRiesgo = perfilRiesgo;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String[] getListaCondiciones() {
		return listaCondiciones;
	}

	public void setListaCondiciones(String[] strings) {
		this.listaCondiciones = strings;
	}

	public void setNumSueldos(Double numSueldos) {
		this.numSueldos = numSueldos;
	}

	public Double getNumSueldos() {
		return numSueldos;
	}

}
