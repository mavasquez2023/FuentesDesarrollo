package cl.laaraucana.copagocredito.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.copagocredito.dao.GenericDao;
import cl.laaraucana.copagocredito.entities.MandatosEntity;

@Service
public class MandatoServiceImpl implements MandatoService {

	@Autowired
	private GenericDao<MandatosEntity> dao;

	@Override
	public List<MandatosEntity> findMandatoByRut(long rut) throws Exception {
		// TODO Auto-generated method stub
		return dao.findMandatoByRut(MandatosEntity.class, rut);
	}

}
