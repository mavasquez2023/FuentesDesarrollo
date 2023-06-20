/**
 * 
 */
package cl.laaraucana.rendicionpagonomina.services;

import java.util.HashMap;
import java.util.List;

import cl.laaraucana.rendicionpagonomina.entities.CabeceraEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ResumenCargaPagoBenefVo;

/**
 * @author IBM Software Factory
 *
 */
public interface BeneficioService {
	public List<ResumenCargaPagoBenefVo> leerTablaBeneficios(String banco) throws Exception;
	
	public int cargarTablasTEF(List<ResumenCargaPagoBenefVo> listaResumen, String banco) throws Exception;
	
	public String generaArchivoNomina(CabeceraEntity cabeceraTEF, String banco) throws Exception;
	
	public int updateBeneficiarioById(HashMap<String, String> params) throws Exception;
	
	public int updateBeneficiarioRendicion(HashMap<String, String> params) throws Exception;
	
	public boolean enviarCorreo(HashMap<String, String> params) throws Exception;
	
}
