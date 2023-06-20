package cl.laaraucana.rendicionpagonomina.services;


import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cl.laaraucana.rendicionpagonomina.ibatis.vo.ArchivoManualVO;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ResumenCargaPagoManualVo;


public interface ProcesaManualService {
	
	public ResumenCargaPagoManualVo cargaPagoManual(CommonsMultipartFile file, List<String> beneficios)throws Exception;
	
	public String generaArchivoNomina(ArchivoManualVO archivoManualVO) throws Exception;

}
