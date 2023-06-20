package cl.laaraucana.rendicionpagonomina.services;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import cl.laaraucana.rendicionpagonomina.dao.GenericDao;
import cl.laaraucana.rendicionpagonomina.entities.CabeceraEntity;
import cl.laaraucana.rendicionpagonomina.entities.CabeceraManualEntity;
import cl.laaraucana.rendicionpagonomina.entities.DetalleEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.CabeceraTefDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.CabeceraTefDaoImpl;
import cl.laaraucana.rendicionpagonomina.utils.FileManagmentUtils;
import cl.laaraucana.rendicionpagonomina.vo.NominaVo;

@Service
public class CabeceraServiceImpl implements CabeceraService{
	
	
	private static final Logger logger = Logger.getLogger(CabeceraServiceImpl.class);
	
	//private PrintWriter trackingFile = FileManagmentUtils.getOpenedFileToWrite(RENDICION_PATH + fileNameFTP +"_"+ formato.format(new Date()));
	
	@Autowired
	private GenericDao<CabeceraEntity> dao;
	
	private CabeceraTefDao daoTef= new CabeceraTefDaoImpl();

	@Override
	public CabeceraEntity save(CabeceraEntity entity) throws Exception {
		// TODO Auto-generated method stub
		return dao.saveOrupdate(entity);
	}

	@Override
	public void update(CabeceraEntity entity) throws Exception {
		// TODO Auto-generated method stub
		dao.saveOrupdate(entity);
	}

	@Override
	public void delete(CabeceraEntity entity) throws Exception {
		// TODO Auto-generated method stub
		dao.remove(entity);
	}

	@Override
	public CabeceraEntity findByCodigoNomina(long codigoNomina) throws Exception {
		// TODO Auto-generated method stub
		return daoTef.findByCodigoNomina(codigoNomina);
	}

	@Override
	public List<CabeceraEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		return dao.findAll(CabeceraEntity.class);
	}

	@Override
	public List<CabeceraEntity> getNominasCabecera(NominaVo nomina) throws Exception {
		// TODO Auto-generated method stub
		return dao.getNominasCabecera(CabeceraEntity.class, nomina);
	}

	@Override
	public void deleteBycodNom(long codigoNomina) throws Exception {
		// TODO Auto-generated method stub
		daoTef.deleteByCodigoNomina(codigoNomina);
	}

	@Override
	public List<CabeceraEntity> findConvenioAndNomina(long convenio, long nomina) throws Exception {
		// TODO Auto-generated method stub
		return dao.findConvenioAndNomina(CabeceraEntity.class, convenio, nomina);
	}

	@Override
	public List<CabeceraEntity> findNominasTEF(HashMap<String, String> params) throws Exception {
		// TODO Auto-generated method stub
		return daoTef.findNominasSeguimiento(params);
	}
	
	@Override
	public CabeceraEntity findById(long id) throws Exception {
		// TODO Auto-generated method stub
		return daoTef.findById(id);
	}

	@Override
	public List<CabeceraEntity> findNominasRendicion(
			HashMap<String, String> params) throws Exception {
		// TODO Auto-generated method stub
		return daoTef.findNominasRendicion(params);
	}

	@Override
	public void updateNomina(CabeceraEntity entity) throws Exception {
		// TODO Auto-generated method stub
		daoTef.updateNominaTEF(entity);
	}

	@Override
	public int validaCRC(HashMap<String, String> params) throws Exception {
		// TODO Auto-generated method stub
		return daoTef.validaCRC(params);
	}

	@Override
	public boolean updateTotalesNomina(long idCabecera, long codigoNomina) throws Exception {
		
		CabeceraEntity cabeceraEntity = new CabeceraEntity();	
		cabeceraEntity.setIdCabecera(idCabecera);
		cabeceraEntity.setEstadoNomina(5);
		cabeceraEntity.setCodigoNomina(codigoNomina);
		
		try {
			if(daoTef.existenRegistrosEnEstado3(idCabecera)) {
				cabeceraEntity.setEstadoNomina(4);
			}
		} catch (Exception e) {
			logger.fatal(e);
		}
		//definir si existen detalles con estado 3, fijo 4
		HashMap<String, Object> dataPagados = null;
		HashMap<String, Object> dataRechazados = null;
		HashMap<String, Object> dataDevueltos = null;
		try {
			dataPagados = daoTef.getSumTotalesPorEstadoPago(idCabecera, 1);
			dataRechazados = daoTef.getSumTotalesPorEstadoPago(idCabecera, 4);
			dataDevueltos = daoTef.getSumTotalesPorEstadoPago(idCabecera, 6);
			
		} catch (Exception e1) {
			logger.fatal(e1);
		}

		logger.debug("dataPagados: "+dataPagados);
		logger.debug("dataRechazados: "+dataRechazados);
		logger.debug("dataDevueltos: "+dataDevueltos);
		
		cabeceraEntity.setTotalPagado(0);
		cabeceraEntity.setCantidadPagado(0);
		cabeceraEntity.setTotalRechazado(0);
		cabeceraEntity.setCantidadRechazado(0);
		cabeceraEntity.setTotalDevuelto(0);
		cabeceraEntity.setCantidadDevuelto(0);
		if(dataPagados != null) {
			cabeceraEntity.setTotalPagado(((BigDecimal)  dataPagados.get("MONTO")).intValue());
			cabeceraEntity.setCantidadPagado(((Integer)  dataPagados.get("REGISTROS")).intValue() );
		}
		if(dataRechazados != null) {
			cabeceraEntity.setTotalRechazado(((BigDecimal)  dataRechazados.get("MONTO")).intValue());
			cabeceraEntity.setCantidadRechazado(((Integer)  dataRechazados.get("REGISTROS")).intValue() );
		}
		if(dataDevueltos != null) {
			cabeceraEntity.setTotalDevuelto(((BigDecimal)  dataDevueltos.get("MONTO")).intValue());
			cabeceraEntity.setCantidadDevuelto(((Integer)  dataDevueltos.get("REGISTROS")).intValue() );
		}
		logger.debug("actualizando cabecera ["+idCabecera+"]");
	
		boolean resultActualizacionCabecera = daoTef.actualizarTotalesRendicion(cabeceraEntity);
		logger.debug("Resultado actualizacion cabecera: "+ resultActualizacionCabecera );
		
		return resultActualizacionCabecera;
	}

	@Override
	public int rollbackNominaTEF(long condigoNomina) throws Exception {
		return daoTef.rollbackNominaTEF(condigoNomina);
	}

}
