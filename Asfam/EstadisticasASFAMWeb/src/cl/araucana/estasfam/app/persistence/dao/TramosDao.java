package cl.araucana.estasfam.app.persistence.dao;

import java.util.List;

import cl.araucana.estasfam.app.business.model.TramosDTO;
import cl.araucana.estasfam.common.exceptions.DaoException;

public interface TramosDao {
	
	public List<TramosDTO> getTramos(Integer anho) throws DaoException;

}
