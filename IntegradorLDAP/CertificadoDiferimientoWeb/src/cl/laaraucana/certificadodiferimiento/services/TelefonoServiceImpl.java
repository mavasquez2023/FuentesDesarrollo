package cl.laaraucana.certificadodiferimiento.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.certificadodiferimiento.dao.GenericDao;
import cl.laaraucana.certificadodiferimiento.entities.PrefijoTelefonoEntity;






@Service
public class TelefonoServiceImpl implements TelefonoService{

	
	@Autowired
	private GenericDao<PrefijoTelefonoEntity> dao;
	
	int tipo_celular=1;
	int tipo_telefono=2;
	@Override
	public List<PrefijoTelefonoEntity> getPrefijoTelefono() throws Exception {
		return dao.prefijoTelefono(PrefijoTelefonoEntity.class, tipo_telefono);
	}

	@Override
	public List<PrefijoTelefonoEntity> getPrefijoCelular() throws Exception {

		return dao.prefijoTelefono(PrefijoTelefonoEntity.class, tipo_celular);
	}
}
