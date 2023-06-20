package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import java.util.List;

import cl.laaraucana.rendicionpagonomina.ibatis.vo.BancoEntity;


public interface BancoDao {

	public List<String> consultaBancosConvenio() throws Exception;
	
	public List<BancoEntity> getBancos() throws Exception;
	
}
