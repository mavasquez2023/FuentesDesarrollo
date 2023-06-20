package cl.laaraucana.mandatopublico.ibatis.dao;

import java.util.List;

import cl.laaraucana.mandatopublico.ibatis.vo.BancoVo;
import cl.laaraucana.mandatopublico.ibatis.vo.MandatoAS400Vo;
import cl.laaraucana.mandatopublico.ibatis.vo.TipoCuentaVo;




public interface MandatoAS400Dao {
	
	public TipoCuentaVo findAccountkById(int codigo) throws Exception;
	
	public BancoVo findBancoById(int codigo) throws Exception;
	
	public List<MandatoAS400Vo> consultaMandatos(int rut) throws Exception;
	
	public List<MandatoAS400Vo> consultaMandatosDia() throws Exception;
	
	public List<MandatoAS400Vo> consultaMandatosRev(int rut) throws Exception;
	
	public List<MandatoAS400Vo> consultaMandatosRechazados(int rut) throws Exception;
	
	public MandatoAS400Vo consultaMandatosById(long id) throws Exception;
	
	public MandatoAS400Vo consultaMandatosRevById(long id) throws Exception;

}
