package cl.laaraucana.contratocr.vo;

import java.util.List;

public class QuestionVo {
	
	private String pregunta;
	private String codPregunta;
	private int codRetorno;
	private List<AnswerVo> respuesta;

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getCodPregunta() {
		return codPregunta;
	}

	public void setCodPregunta(String codPregunta) {
		this.codPregunta = codPregunta;
	}

	public int getCodRetorno() {
		return codRetorno;
	}

	public void setCodRetorno(int codRetorno) {
		this.codRetorno = codRetorno;
	}

	public List<AnswerVo> getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(List<AnswerVo> respuesta) {
		this.respuesta = respuesta;
	}


}
