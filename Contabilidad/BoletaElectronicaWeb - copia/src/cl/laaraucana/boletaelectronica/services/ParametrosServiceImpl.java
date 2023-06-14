package cl.laaraucana.boletaelectronica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.boletaelectronica.dao.GenericDao;
import cl.laaraucana.boletaelectronica.entities.Parametros;

@Service
public class ParametrosServiceImpl implements ParametrosService {

	@Autowired
	private GenericDao<Parametros> dao;

	@Override
	public List<Parametros> getParametros(int estado) throws Exception {

		return dao.findAllByEstate(Parametros.class, estado);
	}

	@Override
	public void deleteParametroById(long id) throws Exception {

		dao.deleteById(Parametros.class, id);

	}

	@Override
	public Parametros getParametrosById(long id) throws Exception {

		return dao.findById(Parametros.class, id);
	}

	@Override
	public void updateParametro(Parametros parametro) throws Exception {

		dao.update(parametro);
	}

	@Override
	public void saveParametro(Parametros parametro) throws Exception {

		dao.save(parametro);

	}

	@Override
	public Parametros getParamByCode(int code) throws Exception {

		return dao.getByCode(Parametros.class, code);
	}

	@Override
	public Parametros getLastParam() throws Exception {
		// TODO Auto-generated method stub
		return dao.getLastCode(Parametros.class);
	}

	@Override
	public List<Parametros> getAllParam() throws Exception {
		// TODO Auto-generated method stub
		return dao.findAll(Parametros.class);
	}

 

}
