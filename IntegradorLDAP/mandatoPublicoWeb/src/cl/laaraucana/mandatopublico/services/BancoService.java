package cl.laaraucana.mandatopublico.services;


import cl.laaraucana.mandatopublico.ibatis.vo.BancoVo;
import cl.laaraucana.mandatopublico.ibatis.vo.TipoCuentaVo;


public interface BancoService {


	public TipoCuentaVo findAccountkById(int codigo) throws Exception;

	public BancoVo findBancoById(int codigo) throws Exception;
	
	public TipoCuentaVo findCuentaById(int codigo) throws Exception;

}
