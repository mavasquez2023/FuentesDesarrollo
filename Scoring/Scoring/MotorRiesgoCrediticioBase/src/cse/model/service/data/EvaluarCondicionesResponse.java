package cse.model.service.data;

import cse.model.businessobject.CondicionOtorgamiento;

public class EvaluarCondicionesResponse {

    private CondicionOtorgamiento[] condiciones;

    private String perfilRiesgo;

    private Float score;
    
    private Integer scoreEquifax;
    
    private Integer numSueldos;
    
    private String idSolicitud;
    
    private Integer endeudMax;

    public EvaluarCondicionesResponse() {
        super();
    }

    public EvaluarCondicionesResponse(CondicionOtorgamiento[] condiciones,
            String perfilRiesgo, Float score, Integer numSueldos) {
        super();
        this.condiciones = condiciones;
        this.perfilRiesgo = perfilRiesgo;
        this.score = score;
        this.numSueldos = numSueldos;
    }

    /**
     * @return Returns the condiciones.
     */
    public CondicionOtorgamiento[] getCondiciones() {
        return condiciones;
    }

    /**
     * @param condiciones
     *            The condiciones to set.
     */
    public void setCondiciones(CondicionOtorgamiento[] condiciones) {
        this.condiciones = condiciones;
    }

    /**
     * @return Returns the perfilRiesgo.
     */
    public String getPerfilRiesgo() {
        return perfilRiesgo;
    }

    /**
     * @param perfilRiesgo
     *            The perfilRiesgo to set.
     */
    public void setPerfilRiesgo(String perfilRiesgo) {
        this.perfilRiesgo = perfilRiesgo;
    }

    /**
     * @return Returns the score.
     */
    public Float getScore() {
        return score;
    }

    /**
     * @param score
     *            The score to set.
     */
    public void setScore(Float score) {
        this.score = score;
    }

	public void setNumSueldos(Integer numSueldos) {
		this.numSueldos = numSueldos;
	}

	public Integer getNumSueldos() {
		return numSueldos;
	}

	public String getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(String idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public void setEndeudMax(Integer endeudMax) {
		this.endeudMax = endeudMax;
	}

	public Integer getEndeudMax() {
		return endeudMax;
	}

	public void setScoreEquifax(Integer scoreEquifax) {
		this.scoreEquifax = scoreEquifax;
	}

	public Integer getScoreEquifax() {
		return scoreEquifax;
	}
}