package cl.laaraucana.RentasPrevired.services;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.laaraucana.RentasPrevired.entities.AfiliadoConsultaEntity;


@Service
public interface AfiliadoConsultaService {

	public void saveAfiliadoConsulta(AfiliadoConsultaEntity afi);
	
	public List<AfiliadoConsultaEntity> findAll();

}
