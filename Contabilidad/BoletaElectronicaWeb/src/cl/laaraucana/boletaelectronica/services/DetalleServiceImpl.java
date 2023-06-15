package cl.laaraucana.boletaelectronica.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.laaraucana.boletaelectronica.dao.GenericDao;
import cl.laaraucana.boletaelectronica.entities.BoletaDetalle;

@Service
public class DetalleServiceImpl implements DetalleService{

	@Autowired
	private GenericDao<BoletaDetalle> dao;
	
	@Override
	public List<BoletaDetalle> getDetalle() throws Exception {
		
		return dao.findAll(BoletaDetalle.class);
	}

	@Override
	public void save(BoletaDetalle detalle) throws Exception {
		
		dao.save(detalle);
		
	}
	
	

}
