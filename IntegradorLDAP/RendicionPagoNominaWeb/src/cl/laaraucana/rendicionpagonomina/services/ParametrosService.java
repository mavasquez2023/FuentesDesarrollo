package cl.laaraucana.rendicionpagonomina.services;

import java.util.HashMap;
import java.util.List;

import cl.laaraucana.rendicionpagonomina.ibatis.vo.ArchivoManualVO;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.BancoEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.BeneficioEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ConvenioEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.EstadoEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ProductoEntity;

public interface ParametrosService {
	
	public void setParams() throws Exception;
		
	public List<ConvenioEntity> consultaConvenios() throws Exception;
	
	public List<ConvenioEntity> consultaConveniosActivos(String idUsuario) throws Exception;
	
	public List<ConvenioEntity> consultaConveniosconPlantilla(String idUsuario, String convenios) throws Exception;
	
	public List<ConvenioEntity> consultaConveniosActivosManual(String idusuario) throws Exception;

	public ConvenioEntity getConvenio(int codigo) throws Exception;
	
	public List<ProductoEntity> consultaProductos() throws Exception;
	
	public List<ProductoEntity> consultaProductosByConvenioCargaManual(int convenio) throws Exception;

	public List<EstadoEntity> consultaEstadosNomina() throws Exception;

	public List<EstadoEntity> consultaEstadosActivos() throws Exception;
	
	public List<EstadoEntity> consultaEstadosPago() throws Exception;

	public List<ArchivoManualVO> consultaConveniosTransferencia() throws Exception;
	
	public List<BeneficioEntity> consultaBeneficios() throws Exception;
	
	public List<BeneficioEntity> consultaBeneficiosByParams(HashMap<String, String> params) throws Exception;

}
