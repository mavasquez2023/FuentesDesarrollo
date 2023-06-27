package cl.laaraucana.RentasPrevired.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.RentasPrevired.dao.GenericDao;
import cl.laaraucana.RentasPrevired.entities.RespuestaCotizacionEntity;


@Service
public class CotizacionServiceImpl implements CotizacionService{

@Autowired
private GenericDao<RespuestaCotizacionEntity> dao;
	
	@Override
	public void saveRespCot(RespuestaCotizacionEntity entity) {
		
		dao.save(entity);
		
	}

}
