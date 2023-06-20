package cl.laaraucana.mandato.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cl.laaraucana.mandato.ibatis.dao.MandatoAS400Dao;
import cl.laaraucana.mandato.ibatis.dao.MandatoAS400DaoImpl;
import cl.laaraucana.mandato.ibatis.vo.BancoVo;
import cl.laaraucana.mandato.ibatis.vo.TipoCuentaVo;


@Service
public class BancoServiceImpl implements BancoService{

	
	MandatoAS400Dao dao = new MandatoAS400DaoImpl();
	
	@Override
	public BancoVo findBanckById(int codigo) throws Exception {
		// TODO Auto-generated method stub
		return dao.findBanckById(codigo);
	}

	@Override
	public TipoCuentaVo findAccountkById(int codigo) throws Exception {
		// TODO Auto-generated method stub
		return dao.findAccountkById(codigo);
	}

	@Override
	public List<BancoVo> getBanco() throws Exception {
		// TODO Auto-generated method stub
		return dao.getBanco();
	}

	@Override
	public List<TipoCuentaVo> getTipoCuenta() throws Exception {
		// TODO Auto-generated method stub
		return dao.getTipoCuenta();
	}

	@Override
	public BancoVo findBancoById(int codigo) throws Exception {
		// TODO Auto-generated method stub
		return dao.findBancoById(codigo);
	}

	@Override
	public TipoCuentaVo findCuentaById(int codigo) throws Exception {
		return dao.findAccountkById(codigo);
	}


}
