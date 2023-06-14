package cl.laaraucana.boletaelectronica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.laaraucana.boletaelectronica.dao.GenericDao;
import cl.laaraucana.boletaelectronica.entities.OrigenBoleta;

@Service
public class OrigenBoletaServiceImpl implements OrigenBoletaService {

	@Autowired
	private GenericDao<OrigenBoleta> dao;

	@Override
	public List<OrigenBoleta> getOrigenByNumber(int folio) throws Exception {
		return dao.findByNumber(OrigenBoleta.class, folio);
	}

	@Override
	public List<OrigenBoleta> findAllNoEmitidas() throws Exception {
		return dao.findAllNoEmitidas(OrigenBoleta.class);
	}

	@Override
	public List<OrigenBoleta> findByNumBolAndTipoDocOrigen(int folio, int tipodoc) throws Exception {
		return dao.findByNumBolAndTipoDocOrigen(OrigenBoleta.class, folio, tipodoc);
	}

	@Override
	public void saveOrUpdate(OrigenBoleta entity) throws Exception {
		dao.update(entity);
	}

	@Override
	public void updateOrigen(long numero, int folio) throws Exception {
		dao.updateOrigen(OrigenBoleta.class, numero, folio);
	}

	 

}
