package cl.laaraucana.rendicionpagonomina.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.laaraucana.rendicionpagonomina.dao.GenericDao;
import cl.laaraucana.rendicionpagonomina.entities.DetalleEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.DetalleTefDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.DetalleTefDaoImpl;
import cl.laaraucana.rendicionpagonomina.vo.NominaVo;

@Service
public class DetalleServiceImpl implements DetalleService{

	@Autowired
	private GenericDao<DetalleEntity> dao;
	
	private DetalleTefDao daoTef= new DetalleTefDaoImpl();
	
	@Override
	public void save(DetalleEntity entity) throws Exception {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void saveOrupdate(DetalleEntity entity) throws Exception {
		// TODO Auto-generated method stub
		dao.saveOrupdate(entity);
	}

	@Override
	public void delete(DetalleEntity entity) throws Exception {
		// TODO Auto-generated method stub
		dao.remove(entity);
	}

	@Override
	public void deleteByRut(long rut) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteByRut(DetalleEntity.class, rut);
	}

	@Override
	public boolean existRut(long rut) throws Exception {
		// TODO Auto-generated method stub
		return dao.existRut(DetalleEntity.class, rut);
	}

	@Override
	public List<DetalleEntity> findPendientesByIdCabecera(long id) throws Exception {
		// TODO Auto-generated method stub
		return dao.findPendientesByIdCabecera(DetalleEntity.class, id);
	}
	
	@Override
	public void deleteByCodigoNomina(long codigoNomina) throws Exception {
		// TODO Auto-generated method stub
		daoTef.deleteByCodigoNomina(codigoNomina);
		
	}

	@Override
	public List<DetalleEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		return dao.findAll(DetalleEntity.class);
	}

	@Override
	public List<DetalleEntity> getNominasDetalle(NominaVo nomina) throws Exception {
		// TODO Auto-generated method stub
		return dao.getNominasDetalle(DetalleEntity.class, nomina);
	}
	
	@Override
	public List<DetalleEntity> findByIdCabecera(long id) throws Exception {
		// TODO Auto-generated method stub
		return daoTef.findByIdCabecera(id);
	}
	
	@Override
	public DetalleEntity findByIdDetalle(long id) throws Exception {
		// TODO Auto-generated method stub
		return daoTef.findByIdDetalle(id);
	}
	
	@Override
	public DetalleEntity findByRut(HashMap<String, Long> params)
			throws Exception {
		// TODO Auto-generated method stub
		return daoTef.findByRut(params);
	}

	@Override
	public List<DetalleEntity> findByEstado(HashMap<String, Long> params)
			throws Exception {
		return daoTef.findByEstadoPago(params);
	}

	@Override
	public List<DetalleEntity> seguimientoAfiliado(
			HashMap<String, String> params) throws Exception {
		// TODO Auto-generated method stub
		return daoTef.seguimientoAfiliado(params);
	}

	@Override
	public void updateDetallePendientedePago(long id, long codigoNomina) throws Exception {
		HashMap<String, Long> params= new HashMap<String, Long>();
		params.put("idCabecera", id);
		params.put("codigoNomina", codigoNomina);
		daoTef.updateDetallePendientedePago(params);
		
	}
}
