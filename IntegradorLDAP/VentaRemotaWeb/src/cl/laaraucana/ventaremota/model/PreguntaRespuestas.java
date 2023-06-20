/**
 * 
 */
package cl.laaraucana.ventaremota.model;

import java.util.List;

import cl.laaraucana.ventaremota.ibatis.vo.PreguntaVO;
import cl.laaraucana.ventaremota.ibatis.vo.RespuestaVO;

/**
 * @author IBM Software Factory
 *
 */
public class PreguntaRespuestas {

	public PreguntaVO pregunta;
	public List<RespuestaVO> respuestas;
	public String respuestaOK;
	/**
	 * @return the pregunta
	 */
	public PreguntaVO getPregunta() {
		return pregunta;
	}
	/**
	 * @param pregunta the pregunta to set
	 */
	public void setPregunta(PreguntaVO pregunta) {
		this.pregunta = pregunta;
	}
	/**
	 * @return the respuestas
	 */
	public List<RespuestaVO> getRespuestas() {
		return respuestas;
	}
	/**
	 * @param respuestas the respuestas to set
	 */
	public void setRespuestas(List<RespuestaVO> respuestas) {
		this.respuestas = respuestas;
	}
	/**
	 * @return the respuestaOK
	 */
	public String getRespuestaOK() {
		return respuestaOK;
	}
	/**
	 * @param respuestaOK the respuestaOK to set
	 */
	public void setRespuestaOK(String respuestaOK) {
		this.respuestaOK = respuestaOK;
	}
	
	
}
