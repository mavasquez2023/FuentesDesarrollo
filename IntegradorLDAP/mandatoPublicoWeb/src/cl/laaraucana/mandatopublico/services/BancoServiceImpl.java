package cl.laaraucana.mandatopublico.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cl.laaraucana.mandatopublico.ibatis.dao.MandatoAS400Dao;
import cl.laaraucana.mandatopublico.ibatis.dao.MandatoAS400DaoImpl;
import cl.laaraucana.mandatopublico.ibatis.vo.BancoVo;
import cl.laaraucana.mandatopublico.ibatis.vo.TipoCuentaVo;



@Service
public class BancoServiceImpl implements BancoService{

	
	MandatoAS400Dao dao = new MandatoAS400DaoImpl();
	

	@Override
	public TipoCuentaVo findAccountkById(int codigo) throws Exception {
		// TODO Auto-generated method stub
		return dao.findAccountkById(codigo);
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
