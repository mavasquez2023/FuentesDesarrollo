/**
 * 
 */
package cl.laaraucana.ventaremota.ibatis.vo;

/**
 * @author IBM Software Factory
 *
 */
public class PreguntaVO {

	public int idPregunta;
	public String pregunta;
	public int maxRespuestas;
	public String tipoAfiliado;
	public String nombreCampo;
	public int isLista;
	public int isInfoPersonal;
	
	/**
	 * @return the idPregunta
	 */
	public int getIdPregunta() {
		return idPregunta;
	}
	/**
	 * @param idPregunta the idPregunta to set
	 */
	public void setIdPregunta(int idPregunta) {
		this.idPregunta = idPregunta;
	}
	/**
	 * @return the pregunta
	 */
	public String getPregunta() {
		return pregunta;
	}
	/**
	 * @param pregunta the pregunta to set
	 */
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	/**
	 * @return the maxRespuestas
	 */
	public int getMaxRespuestas() {
		return maxRespuestas;
	}
	/**
	 * @param maxRespuestas the maxRespuestas to set
	 */
	public void setMaxRespuestas(int maxRespuestas) {
		this.maxRespuestas = maxRespuestas;
	}
	/**
	 * @return the tipoAfiliado
	 */
	public String getTipoAfiliado() {
		return tipoAfiliado;
	}
	/**
	 * @param tipoAfiliado the tipoAfiliado to set
	 */
	public void setTipoAfiliado(String tipoAfiliado) {
		this.tipoAfiliado = tipoAfiliado;
	}
	/**
	 * @return the nombreCampo
	 */
	public String getNombreCampo() {
		return nombreCampo;
	}
	/**
	 * @param nombreCampo the nombreCampo to set
	 */
	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}
	/**
	 * @return the isLista
	 */
	public int getIsLista() {
		return isLista;
	}
	/**
	 * @param isLista the isLista to set
	 */
	public void setIsLista(int isLista) {
		this.isLista = isLista;
	}
	/**
	 * @return the isInfoPersonal
	 */
	public int getIsInfoPersonal() {
		return isInfoPersonal;
	}
	/**
	 * @param isInfoPersonal the isInfoPersonal to set
	 */
	public void setIsInfoPersonal(int isInfoPersonal) {
		this.isInfoPersonal = isInfoPersonal;
	}
	
	
}
