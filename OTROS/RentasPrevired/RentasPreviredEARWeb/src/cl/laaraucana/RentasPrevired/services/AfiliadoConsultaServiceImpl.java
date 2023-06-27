package cl.laaraucana.RentasPrevired.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.RentasPrevired.dao.GenericDao;
import cl.laaraucana.RentasPrevired.entities.AfiliadoConsultaEntity;
 
@Service
public class AfiliadoConsultaServiceImpl implements AfiliadoConsultaService {

	@Autowired
	private GenericDao<AfiliadoConsultaEntity> dao;

	@Override
	public void saveAfiliadoConsulta(AfiliadoConsultaEntity afi) {

		dao.save(afi);

	}

	@Override
	public List<AfiliadoConsultaEntity> findAll() {
		
		 
		return dao.findAll(AfiliadoConsultaEntity.class);
	}

}
