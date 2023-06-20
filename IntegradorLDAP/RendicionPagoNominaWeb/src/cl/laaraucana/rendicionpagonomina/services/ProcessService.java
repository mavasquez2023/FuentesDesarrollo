package cl.laaraucana.rendicionpagonomina.services;

import java.util.List;

import cl.laaraucana.rendicionpagonomina.entities.DetalleManualEntity;

public interface ProcessService {
	
	public String zipFiles(String folder, String filezip);
	
	public void zipDir(List<String> srcFiles, String path) throws Exception;
	
	public void decodeBase64(String data, String filename, String path);
	
	public String encodeBase64(byte[] data);
	

}
