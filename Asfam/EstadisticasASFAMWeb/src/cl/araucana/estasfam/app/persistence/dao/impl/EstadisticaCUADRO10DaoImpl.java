package cl.araucana.estasfam.app.persistence.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import cl.araucana.estasfam.app.business.model.CargasPorColumnaDTO;
import cl.araucana.estasfam.app.persistence.dao.EstadisticaCUADRO10Dao;
import cl.araucana.estasfam.app.persistence.dao.mapper.CargasPorColumnaMapper;
import cl.araucana.estasfam.common.exceptions.DaoException;
import cl.araucana.estasfam.common.util.FechaUtil;

@Repository
public class EstadisticaCUADRO10DaoImpl extends JdbcDaoSupport implements EstadisticaCUADRO10Dao{

	private static Logger log = Logger.getLogger(EstadisticaCUADRO10DaoImpl.class);
	
	private SimpleJdbcCall procReadActor;
	
	@Autowired
	public EstadisticaCUADRO10DaoImpl(DataSource dataSource) {
		super();
		super.setDataSource(dataSource);
	}

	@Override
	public List<CargasPorColumnaDTO> getDatosCUADRO10(Date fecPeriodo) throws DaoException {
		log.debug("Invocando ");
		List<CargasPorColumnaDTO> listDatosCUADRO10 = new ArrayList<CargasPorColumnaDTO>();

		try{
			SqlParameterSource params = new MapSqlParameterSource()
			.addValue("PARAM1", FechaUtil.formatearFecha("yyyyMM", fecPeriodo));
			
			this.procReadActor = new SimpleJdbcCall(super.getDataSource())
			.withProcedureName("PA_GET_DATOS_CUADRO_10")
			.returningResultSet("datosCUADRO10", new CargasPorColumnaMapper());
			this.procReadActor.setSchemaName("AFDTA");
			
			Map<String, Object> res = this.procReadActor.execute(params);
			listDatosCUADRO10 = (List<CargasPorColumnaDTO>)res.get("datosCUADRO10");
			
			return listDatosCUADRO10;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DaoException("","");
		}
	}
}
