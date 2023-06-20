package cl.laaraucana.rendicionpagonomina.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.laaraucana.rendicionpagonomina.dao.GenericDao;
import cl.laaraucana.rendicionpagonomina.entities.DetalleEntity;
import cl.laaraucana.rendicionpagonomina.entities.DetalleManualEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.DetalleManualDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.DetalleManualDaoImpl;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ArchivoManualVO;
import cl.laaraucana.rendicionpagonomina.vo.NominaVo;

@Service
public class DetalleManualServiceImpl implements DetalleManualService{

	@Autowired
	private GenericDao<DetalleManualEntity> dao;
	
	DetalleManualDao detalleDao= new DetalleManualDaoImpl();
	
	@Override
	public void save(DetalleManualEntity entity) throws Exception {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void saveOrupdate(DetalleManualEntity entity) throws Exception {
		// TODO Auto-generated method stub
		dao.saveOrupdate(entity);
	}

	@Override
	public List<DetalleManualEntity> findByIdCabecera(long id) throws Exception {
		// TODO Auto-generated method stub
		return detalleDao.findByIdCabecera(id);
	}

	@Override
	public DetalleManualEntity findByIdDetalle(long id) throws Exception {
		// TODO Auto-generated method stub
		return detalleDao.findByIdDetalle(id);
	}

	@Override
	public void updatePendientes(ArchivoManualVO data) throws Exception {
		detalleDao.updatePendientes(data);
		
	}
	
	@Override
	public void updateMandatoDetalle(DetalleManualEntity detalle) throws Exception {
		detalleDao.updateMandatoDetalle(detalle);
		
	}

	@Override
	public List<DetalleManualEntity> findByConvenio_Producto(String convenio,
			String producto) throws Exception {
		// TODO Auto-generated method stub
		return detalleDao.getDetallesxConvenioProducto(convenio, producto);
	}

	@Override
	public DetalleManualEntity findByRut(HashMap<String, Long> params)
			throws Exception {
		// TODO Auto-generated method stub
		return detalleDao.findByRut(params);
	}

	@Override
	public void rollbackTransferencia(ArchivoManualVO data) throws Exception {
		detalleDao.rollbackTransferencia(data);
		
	}

	@Override
	public int updateEstadoDetalleManual(HashMap params)
			throws Exception {
		return detalleDao.updateEstadoDetalleManual(params);
	}

	@Override
	public Long getIdCabeceraByIdDetalle(long idDetalle) throws Exception {
		return detalleDao.getIdCabeceraByIdDetalle(idDetalle);
	}

	
}
