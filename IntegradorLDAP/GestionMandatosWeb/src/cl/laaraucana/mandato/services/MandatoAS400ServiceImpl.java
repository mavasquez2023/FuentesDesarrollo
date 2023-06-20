package cl.laaraucana.mandato.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import cl.laaraucana.mandato.ibatis.dao.MandatoAS400Dao;
import cl.laaraucana.mandato.ibatis.dao.MandatoAS400DaoImpl;
import cl.laaraucana.mandato.ibatis.vo.MandatoAS400Vo;

@Service
public class MandatoAS400ServiceImpl implements MandatoAS400Service {

	MandatoAS400Dao dao = new MandatoAS400DaoImpl();

	@Override
	public List<MandatoAS400Vo> consultaMandatos(int rut) throws Exception {
		// TODO Auto-generated method stub
		return dao.consultaMandatos(rut);
	}


	@Override
	public void insertMandato(int rut, MandatoAS400Vo mandatos) throws Exception {
		// TODO Auto-generated method stub
       dao.insertMandato(rut, mandatos);
	}
	
	@Override
	public boolean deleteMandato(int rut) throws Exception {
		// TODO Auto-generated method stub
       return dao.deleteMandato(rut);
	}
	
	@Override
	public boolean deleteMandatoById(long id) throws Exception {
		// TODO Auto-generated method stub
       return dao.deleteMandatoById(id);
	}

	@Override
	public List<MandatoAS400Vo> consultaMandatosRev(int rut) throws Exception {
		// TODO Auto-generated method stub
		return dao.consultaMandatosRev(rut);
	}
	
	@Override
	public List<MandatoAS400Vo> consultaMandatosRechazados(int rut) throws Exception {
		// TODO Auto-generated method stub
		return dao.consultaMandatosRechazados(rut);
	}
	
	@Override
	public MandatoAS400Vo consultaMandatosById(long id) throws Exception {
		// TODO Auto-generated method stub
		return dao.consultaMandatosById(id);
	}

	@Override
	public MandatoAS400Vo consultaMandatosRevById(long id) throws Exception {
		// TODO Auto-generated method stub
		return dao.consultaMandatosRevById(id);
	}

	@Override
	public long consultaMandatosGetId() throws Exception {
		// TODO Auto-generated method stub
		return MandatoAS400DaoImpl.getNextId();
	}


	@Override
	public MandatoAS400Vo consultaMandatoxRechazo(
			HashMap<String, String> params) throws Exception {
		// TODO Auto-generated method stub
		return dao.consultaMandato(params);
	}


	@Override
	public boolean rechazaMandato(MandatoAS400Vo mandato) throws Exception {
		// TODO Auto-generated method stub
		return dao.rechazoMandato(mandato);
	}
	
}
