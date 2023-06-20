package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import java.util.HashMap;
import java.util.List;

import cl.laaraucana.rendicionpagonomina.ibatis.vo.ProductoEntity;

public interface ProductoDao {

	public List<ProductoEntity> consultaProductos() throws Exception;
	
	public List<ProductoEntity> consultaProductosByConvenioCargaManual(int convenio) throws Exception;
	
	public HashMap<String, Object> consultaProducto(Integer idConvenio, String idProducto) throws Exception ;
	
	public HashMap<String, Object> consultaProducto(Integer idConvenio) throws Exception ;
	
	public List<HashMap<String, Object>> consultaProductosByConvenioAndCargaManual(Integer idConvenio, String cargaManual) throws Exception ;
}
