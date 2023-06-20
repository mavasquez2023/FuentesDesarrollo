package cl.laaraucana.mandato.services;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.laaraucana.mandato.ibatis.dao.TelefonoDao;
import cl.laaraucana.mandato.ibatis.dao.TelefonoDaoImpl;



@Service
public class TelefonoServiceImpl implements TelefonoService{

	
	TelefonoDao dao = new TelefonoDaoImpl();
	final int tipo_celular=1;
	final int tipo_telefono=2;
	@Override
	public List<String> getPrefijoTelefono() throws Exception {
		return dao.getPrefijoTelefono(tipo_telefono);
	}

	@Override
	public List<String> getPrefijoCelular() throws Exception {

		return dao.getPrefijoTelefono(tipo_celular);
	}
}
