package cl.laaraucana.transferencias.services;

import java.util.List;

import cl.laaraucana.transferencias.ibatis.vo.BancoVo;
import cl.laaraucana.transferencias.ibatis.vo.TipoCuentaVo;

public interface BancoService {

	public BancoVo findBanckById(int codigo) throws Exception;

	public TipoCuentaVo findAccountkById(int codigo) throws Exception;

	public List<BancoVo> getBanco() throws Exception;

	public List<TipoCuentaVo> getTipoCuenta() throws Exception;

	public BancoVo findBancoById(int codigo) throws Exception;

	public TipoCuentaVo findCuentaById(int codigo) throws Exception;


}
