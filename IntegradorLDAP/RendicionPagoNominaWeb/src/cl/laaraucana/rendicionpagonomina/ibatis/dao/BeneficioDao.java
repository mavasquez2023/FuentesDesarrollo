package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import java.util.HashMap;
import java.util.List;

import cl.laaraucana.rendicionpagonomina.ibatis.vo.BeneficiarioEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.BeneficioEntity;


public interface BeneficioDao {

	public List<BeneficioEntity> consultaBeneficios() throws Exception;
	
	public List<BeneficioEntity> consultaBeneficiosByParams(HashMap<String, String> params) throws Exception;
	
	public List<BeneficiarioEntity> consultaBeneficiariosBES() throws Exception;
	
	public List<BeneficiarioEntity> consultaBeneficiariosBCI() throws Exception;
	
	public int updateBeneficiario(HashMap<String, String> params) throws Exception;
	
	public int rollbackBeneficiarios(Integer idCabecera) throws Exception;
	
	public int updateBeneficiarioById(HashMap<String, String> params) throws Exception;
	
	public int updateBeneficiarioRendicion(HashMap<String, String> params) throws Exception;
	
}
