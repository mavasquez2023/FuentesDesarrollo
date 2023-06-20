/**
 * 
 */
package cl.laaraucana.ventaremota.ibatis.vo;

/**
 * @author IBM Software Factory
 *
 */
public class RespuestaVO {

	public int idPregunta;
	public int idRespuesta;
	public int isRango;
	public int menorValor;
	public int mayorValor;
	public String igualValor;
	public String respuesta;
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
	 * @return the idRespuesta
	 */
	public int getIdRespuesta() {
		return idRespuesta;
	}
	/**
	 * @param idRespuesta the idRespuesta to set
	 */
	public void setIdRespuesta(int idRespuesta) {
		this.idRespuesta = idRespuesta;
	}
	/**
	 * @return the isRango
	 */
	public int getIsRango() {
		return isRango;
	}
	/**
	 * @param isRango the isRango to set
	 */
	public void setIsRango(int isRango) {
		this.isRango = isRango;
	}
	/**
	 * @return the menorValor
	 */
	public int getMenorValor() {
		return menorValor;
	}
	/**
	 * @param menorValor the menorValor to set
	 */
	public void setMenorValor(int menorValor) {
		this.menorValor = menorValor;
	}
	/**
	 * @return the mayorValor
	 */
	public int getMayorValor() {
		return mayorValor;
	}
	/**
	 * @param mayorValor the mayorValor to set
	 */
	public void setMayorValor(int mayorValor) {
		this.mayorValor = mayorValor;
	}
	/**
	 * @return the igualValor
	 */
	public String getIgualValor() {
		return igualValor;
	}
	/**
	 * @param igualValor the igualValor to set
	 */
	public void setIgualValor(String igualValor) {
		this.igualValor = igualValor;
	}
	/**
	 * @return the respuesta
	 */
	public String getRespuesta() {
		return respuesta;
	}
	/**
	 * @param respuesta the respuesta to set
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	
}
