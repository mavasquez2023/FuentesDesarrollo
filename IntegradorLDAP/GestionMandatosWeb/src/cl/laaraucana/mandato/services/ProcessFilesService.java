package cl.laaraucana.mandato.services;



import org.springframework.web.multipart.commons.CommonsMultipartFile;
import cl.laaraucana.mandato.ibatis.vo.ResumenCargaRechazoVo;


public interface ProcessFilesService {

	public ResumenCargaRechazoVo cargaArchivoRechazo(CommonsMultipartFile file)throws Exception;
	

}
