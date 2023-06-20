package cl.laaraucana.rendicionpagonomina.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.laaraucana.rendicionpagonomina.dao.GenericDao;
import cl.laaraucana.rendicionpagonomina.entities.CabeceraManualEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.CabeceraManualDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.CabeceraManualDaoImpl;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.DetalleManualDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.DetalleManualDaoImpl;
import cl.laaraucana.rendicionpagonomina.vo.NominaManualVo;

@Service
public class CabeceraManualServiceImpl implements CabeceraManualService{
	
	private static final Logger logger = Logger.getLogger(CabeceraManualServiceImpl.class);
	
	@Autowired
	private GenericDao<CabeceraManualEntity> dao;
	
	private CabeceraManualDao daoCabManual= new CabeceraManualDaoImpl();
	
	private DetalleManualDao daoDetManual= new DetalleManualDaoImpl();

	@Override
	public CabeceraManualEntity save(CabeceraManualEntity entity) throws Exception {
		// TODO Auto-generated method stub
		return dao.saveOrupdate(entity);
	}

	@Override
	public void update(CabeceraManualEntity entity) throws Exception {
		// TODO Auto-generated method stub
		dao.saveOrupdate(entity);
	}

	@Override
	public List<CabeceraManualEntity> findByParams(NominaManualVo params) throws Exception {
		// TODO Auto-generated method stub
		return daoCabManual.findManualByParams(params);
	}

	@Override
	public CabeceraManualEntity findById(long id) throws Exception {
		// TODO Auto-generated method stub
		return daoCabManual.findById(id);
	}

	@Override
	public void updateMontoPendiente(long idCabecera) throws Exception {
		// TODO Auto-generated method stub
		daoCabManual.updateMontoPendiente(idCabecera);
	}

	@Override
	public boolean updateTotalesNomina(long idCabecera) throws Exception {
		CabeceraManualEntity cabeceraEntity = new CabeceraManualEntity();	
		cabeceraEntity.setIdCabecera(idCabecera);
		cabeceraEntity.setEstado(5);
		try {
			if(daoDetManual.existenRegistrosPendientes((int)idCabecera)) {
				cabeceraEntity.setEstado(4);
			}
		} catch (Exception e) {
			logger.fatal(e);
		}
		//definir si existen detalles con estado 3, fijo 4
		HashMap<String, Object> dataPagados = null;
		HashMap<String, Object> dataPendientes = null;

		try {
			//Se actualiza monto pendiente de cabecera manual
			dataPendientes = daoCabManual.getSumTotalesPorEstadoPago((int)idCabecera, 3);
			
		} catch (Exception e1) {
			logger.fatal(e1);
		}

		logger.debug("dataPendientes: "+dataPendientes);
		
		cabeceraEntity.setTotalPendientes(0);
		cabeceraEntity.setMontoPendiente(0);

		if(dataPendientes != null) {
			cabeceraEntity.setMontoPendiente(((BigDecimal)  dataPendientes.get("MONTO")).intValue());
			cabeceraEntity.setTotalPendientes(((Integer)  dataPendientes.get("REGISTROS")).intValue() );
		}
		
		logger.debug("actualizando cabecera ["+idCabecera+"]");
	
		boolean resultActualizacionCabecera = daoCabManual.actualizarTotalesRendicion(cabeceraEntity);
		logger.debug("Resultado actualizacion cabecera manual: "+ resultActualizacionCabecera );
		
		return resultActualizacionCabecera;
	}
	
	
}
