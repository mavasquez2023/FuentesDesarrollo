/**
 * 
 */
package cl.laaraucana.rendicionpagonomina.services;

import java.util.HashMap;
import java.util.List;

import cl.laaraucana.rendicionpagonomina.entities.CabeceraEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ResumenCargaPagoBecasVo;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ResumenCargaPagoBenefVo;

/**
 * @author IBM Software Factory
 *
 */
public interface BecasService {
	public List<ResumenCargaPagoBecasVo> leerTablaBecas(String banco) throws Exception;
	
	public void cargarDatosmandato(List<ResumenCargaPagoBecasVo> listaResumen) throws Exception;
	
	public int cargarTablasTEF(List<ResumenCargaPagoBecasVo> listaResumen, String banco) throws Exception;
	
	public String generaArchivoNomina(CabeceraEntity cabeceraTEF, String banco) throws Exception;
	
	public int updateBecadoRendicion(HashMap<String, String> params) throws Exception;
	
	public boolean enviarCorreo(HashMap<String, String> params) throws Exception;
	
}
