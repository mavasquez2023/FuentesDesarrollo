package cl.laaraucana.rendicionpagonomina.services;

import java.util.ArrayList;
import java.util.HashMap;

import cl.laaraucana.rendicionpagonomina.exception.MiException;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ConvenioEntity;
import cl.laaraucana.rendicionpagonomina.vo.EnvioNominaRespuestaVo;

public interface ProcesaArchivoGenericoTEF {
	public ArrayList<HashMap<String, Object>> getAvailablesFiles()  throws MiException;
	public int loadData() throws MiException;
	public HashMap<String, String> loadDataFromFile(String file, ConvenioEntity convenio) throws MiException;;
	public EnvioNominaRespuestaVo sendFiles(long idCabecera) throws Exception;
}
