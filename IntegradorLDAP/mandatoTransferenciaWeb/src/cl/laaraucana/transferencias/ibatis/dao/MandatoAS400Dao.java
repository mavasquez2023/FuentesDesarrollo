package cl.laaraucana.transferencias.ibatis.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cl.laaraucana.transferencias.ibatis.vo.BancoVo;
import cl.laaraucana.transferencias.ibatis.vo.MandatoAS400Vo;
import cl.laaraucana.transferencias.ibatis.vo.TipoCuentaVo;

public interface MandatoAS400Dao {
	
	public BancoVo findBanckById(int codigo) throws Exception;

	public List<BancoVo> getBanco() throws Exception;

	public List<TipoCuentaVo> getTipoCuenta() throws Exception;
	
	public TipoCuentaVo findAccountkById(int codigo) throws Exception;
	
	public BancoVo findBancoById(int codigo) throws Exception;
	
	public List<MandatoAS400Vo> consultaMandatos(int rut) throws Exception;
	
	public List<MandatoAS400Vo> consultaMandatosRev(int rut) throws Exception;
	
	public List<MandatoAS400Vo> consultaMandatosRechazados(int rut) throws Exception;
	
	public void insertMandato(int rut, MandatoAS400Vo mandatos) throws Exception;
	
	public boolean deleteMandato(int rut) throws Exception;
	
	public boolean deleteMandato(HashMap<String, String> params) throws Exception;
	
	public boolean deleteMandatoById(long id) throws Exception;
	
	public MandatoAS400Vo consultaMandatosById(long id) throws Exception;
	
	public MandatoAS400Vo consultaMandatosRevById(long id) throws Exception;

}
