/**
 * 
 */
package cl.laaraucana.ventaremota.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.laaraucana.ventaremota.ibatis.dao.PreguntasDao;
import cl.laaraucana.ventaremota.ibatis.dao.PreguntasDaoImpl;
import cl.laaraucana.ventaremota.ibatis.vo.PreguntaVO;
import cl.laaraucana.ventaremota.ibatis.vo.RespuestaVO;
import cl.laaraucana.ventaremota.ibatis.vo.ResultadosBitacoraVO;
import cl.laaraucana.ventaremota.model.PreguntaRespuestas;

/**
 * @author J-Factory
 *
 */
@Service
public class PreguntasServiceImpl implements PreguntasService{
	private PreguntasDao dao= new PreguntasDaoImpl();
	
	@Override
	public List<PreguntaRespuestas> obtienePreguntas(
			List<Integer> idPreguntas) throws Exception {
		HashMap<String, Integer> params= new HashMap<String, Integer>();
		int i =1;
		for (Iterator iterator = idPreguntas.iterator(); iterator.hasNext();) {
			Integer id = (Integer) iterator.next();
			params.put("idPregunta" + i, id);
			i++;
		}
		List<PreguntaVO> preguntas=  dao.obtienePreguntas(params);
		List<PreguntaRespuestas> listaPreguntas= new ArrayList<PreguntaRespuestas>();
		for (Iterator iterator = preguntas.iterator(); iterator.hasNext();) {
			PreguntaVO preguntaVO = (PreguntaVO) iterator.next();
			PreguntaRespuestas preg_resp= new PreguntaRespuestas();
			preg_resp.setPregunta(preguntaVO);
			List<RespuestaVO> respuestas=  dao.obtieneRespuestas(preguntaVO.getIdPregunta());
			preg_resp.setRespuestas(respuestas);
			listaPreguntas.add(preg_resp);
		}
		
		return listaPreguntas;
	}

	@Override
	public int totalPreguntas() throws Exception {
		
		return dao.totalPreguntas();
	}

	@Override
	public void insertRespuestasBitacora(ResultadosBitacoraVO resultado)
			throws Exception {
		dao.insertRespuestasBitacora(resultado);
		
		
	}

	@Override
	public void updateIntentosRespuestas(ResultadosBitacoraVO resultado) throws Exception {
		dao.updateIntentosRespuestas(resultado);
		
	}

	@Override
	public int numeroIntentos(HashMap<String, Long> params) throws Exception {
		return dao.numeroIntentos(params);
	}

	@Override
	public List<Integer> preguntasActivas() throws Exception {
		return dao.preguntasActivas();
	}

	@Override
	public List<Integer> preguntasPersonales() throws Exception {
		return dao.preguntasPersonales();
	}

}
