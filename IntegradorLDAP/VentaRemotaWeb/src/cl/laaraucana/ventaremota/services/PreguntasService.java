/**
 * 
 */
package cl.laaraucana.ventaremota.services;

import java.util.HashMap;
import java.util.List;

import cl.laaraucana.ventaremota.ibatis.vo.ResultadosBitacoraVO;
import cl.laaraucana.ventaremota.model.PreguntaRespuestas;

/**
 * @author IBM Software Factory
 *
 */
public interface PreguntasService {
	public List<PreguntaRespuestas> obtienePreguntas(List<Integer> idPreguntas) throws Exception;
	
	public int totalPreguntas() throws Exception;
	
	public int numeroIntentos(HashMap<String, Long> params) throws Exception;
	
	public void insertRespuestasBitacora(ResultadosBitacoraVO resultado) throws Exception;
	
	public void updateIntentosRespuestas(ResultadosBitacoraVO resultado) throws Exception;
	
	public List<Integer> preguntasActivas() throws Exception;
	
	public List<Integer> preguntasPersonales() throws Exception;
}
