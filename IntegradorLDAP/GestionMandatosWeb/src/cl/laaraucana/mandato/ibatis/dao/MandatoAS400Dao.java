package cl.laaraucana.mandato.ibatis.dao;

import java.util.HashMap;
import java.util.List;

import cl.laaraucana.mandato.ibatis.vo.BancoVo;
import cl.laaraucana.mandato.ibatis.vo.MandatoAS400Vo;
import cl.laaraucana.mandato.ibatis.vo.TipoCuentaVo;

public interface MandatoAS400Dao {
	
	public BancoVo findBanckById(int codigo) throws Exception;

	public List<BancoVo> getBanco() throws Exception;

	public List<TipoCuentaVo> getTipoCuenta() throws Exception;
	
	public TipoCuentaVo findAccountkById(int codigo) throws Exception;
	
	public BancoVo findBancoById(int codigo) throws Exception;
	
	public List<MandatoAS400Vo> consultaMandatos(int rut) throws Exception;
	
	public List<MandatoAS400Vo> consultaMandatosDiaVigentes() throws Exception;
	
	public List<MandatoAS400Vo> consultaMandatosDiaRevocados() throws Exception;
	
	public List<MandatoAS400Vo> consultaMandatosRev(int rut) throws Exception;
	
	public List<MandatoAS400Vo> consultaMandatosRechazados(int rut) throws Exception;
	
	public void insertMandato(int rut, MandatoAS400Vo mandatos) throws Exception;
	
	public boolean rechazoMandato(MandatoAS400Vo mandato) throws Exception;
	
	public boolean deleteMandato(int rut) throws Exception;
	
	public boolean deleteMandatoById(long id) throws Exception;
	
	public MandatoAS400Vo consultaMandatosById(long id) throws Exception;
	
	public MandatoAS400Vo consultaMandatosRevById(long id) throws Exception;
	
	public MandatoAS400Vo consultaMandato(HashMap<String, String> params) throws Exception;

}
