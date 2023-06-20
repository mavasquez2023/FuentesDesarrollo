package cl.laaraucana.rendicionpagonomina.services;


import java.util.List;


import cl.laaraucana.rendicionpagonomina.entities.CabeceraManualEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ArchivoManualVO;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ResumenCargaPagoManualVo;



public interface ProcessBeneficiosService {

	
	//public ResumenCargaBeneficiosVo cargaTablaBeneficios(List<String> beneficios)throws Exception;
	
	public String generaArchivoNomina(ArchivoManualVO archivoManualVO) throws Exception;
	
	public CabeceraManualEntity generarCabeceraManual(ResumenCargaPagoManualVo cargaManual) throws Exception;

}
