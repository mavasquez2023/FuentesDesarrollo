/**
 * 
 */
package cl.laaraucana.ventaremota.ibatis.dao;

import java.util.HashMap;
import java.util.List;

import cl.laaraucana.ventaremota.ibatis.vo.PreguntaVO;
import cl.laaraucana.ventaremota.ibatis.vo.RespuestaVO;
import cl.laaraucana.ventaremota.ibatis.vo.ResultadosBitacoraVO;


/**
 * @author IBM Software Factory
 *
 */
public interface PreguntasDao {
	
	public List<PreguntaVO> obtienePreguntas(HashMap<String, Integer> idPreguntas) throws Exception;
	
	public List<RespuestaVO> obtieneRespuestas(int idPregunta) throws Exception;
	
	public int totalPreguntas() throws Exception;
	
	public int numeroIntentos(HashMap<String, Long> params) throws Exception;
	
	public void insertRespuestasBitacora(ResultadosBitacoraVO resultado) throws Exception;
	
	public void updateIntentosRespuestas(ResultadosBitacoraVO resultado) throws Exception;
	
	public List<Integer> preguntasActivas() throws Exception;
	
	public List<Integer> preguntasPersonales() throws Exception;
	
}
