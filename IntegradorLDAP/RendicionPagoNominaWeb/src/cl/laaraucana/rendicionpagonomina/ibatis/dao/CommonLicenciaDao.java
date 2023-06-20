package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import java.util.HashMap;
import java.util.List;

import cl.laaraucana.rendicionpagonomina.ibatis.vo.ProductoEntity;

public interface CommonLicenciaDao {

	public HashMap<String,String> getBancos() throws Exception;
	public HashMap<String,String> getFormasPago() throws Exception;
	public HashMap<String,String> getDescripcionRechazo() throws Exception;
}
