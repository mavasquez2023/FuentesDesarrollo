package cl.laaraucana.boletaelectronica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.boletaelectronica.dao.GenericDao;
import cl.laaraucana.boletaelectronica.entities.BoletaBase;

@Service
public class BaseServiceImpl implements BaseServices {

	@Autowired
	private GenericDao<BoletaBase> dao;

	@Override
	public List<BoletaBase> getBoleta(int estado) throws Exception {
		return dao.findAllByEstate(BoletaBase.class, estado);
	}

	@Override
	public BoletaBase getBoletaById(long id) throws Exception {
		return dao.findById(BoletaBase.class, id);
	}

	@Override
	public void save(BoletaBase boleta) throws Exception {
		dao.save(boleta);
	}

	@Override
	public List<BoletaBase> getBoletaByFolio(long folio) throws Exception {
		return dao.findByFolioBoleta(BoletaBase.class, folio);
	}

	@Override
	public boolean existNumberAndFol(long numboleta) throws Exception {
		return dao.existNumberAndFol(BoletaBase.class, numboleta);
	}

	@Override
	public void updateBase(BoletaBase boletaBase) throws Exception {
		dao.update(boletaBase);
	}

	@Override
	public List<BoletaBase> findAllEmitidas() throws Exception {
		return dao.findAllEmitidas(BoletaBase.class);
	}

	@Override
	public List<BoletaBase> getBoletaByNumberAndEstate(long folio, int estado) throws Exception {
		return dao.findByNumber(BoletaBase.class, folio, estado);
	}

	@Override
	public List<BoletaBase> findByNumBolAndTipoDoc(long numboleta, int tipodoc) throws Exception {
		return dao.findByNumBolAndTipoDoc(BoletaBase.class, numboleta, tipodoc);
	}

	@Override
	public List<BoletaBase> findByNumber(long folio) throws Exception {
		return dao.findByNumber(BoletaBase.class, folio);
	}

	@Override
	public List<BoletaBase> findByFecha(long fechaInicio, long fechaFin, long folio) throws Exception {
		return dao.findByFecha(BoletaBase.class, fechaInicio, fechaFin, folio);
	}

	 

}
