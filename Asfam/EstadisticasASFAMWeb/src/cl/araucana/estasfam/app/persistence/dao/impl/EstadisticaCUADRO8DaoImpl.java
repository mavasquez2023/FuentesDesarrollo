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

import cl.araucana.estasfam.app.business.model.AfiliadosPorTipoDTO;
import cl.araucana.estasfam.app.persistence.dao.EstadisticaCUADRO8Dao;
import cl.araucana.estasfam.app.persistence.dao.mapper.AfiliadosPorTipoMapper;
import cl.araucana.estasfam.app.persistence.extractor.AfiliadosPorTipoExtractor;
import cl.araucana.estasfam.common.exceptions.DaoException;
import cl.araucana.estasfam.common.util.FechaUtil;

@Repository
public class EstadisticaCUADRO8DaoImpl extends JdbcDaoSupport implements EstadisticaCUADRO8Dao{

	private static Logger log = Logger.getLogger(EstadisticaCUADRO8DaoImpl.class);
	
	private SimpleJdbcCall procReadActor;
	
	@Autowired
	public EstadisticaCUADRO8DaoImpl(DataSource dataSource) {
		super();
		super.setDataSource(dataSource);
	}

	@Override
	public List<AfiliadosPorTipoDTO> getDatosCUADRO8(Date fecPeriodo) throws DaoException {
		log.debug("Invocando ");
		List<AfiliadosPorTipoDTO> listDatosCUADRO8 = new ArrayList<AfiliadosPorTipoDTO>();

		try{
			SqlParameterSource params = new MapSqlParameterSource()
			.addValue("PARAM1", FechaUtil.formatearFecha("yyyyMM", fecPeriodo))
			.addValue("PARAM2", FechaUtil.formatearFecha("yyyy-MM-dd", fecPeriodo))
			.addValue("PARAM3", FechaUtil.formatearFecha("yyyy-MM-dd", FechaUtil.sumarMeses(fecPeriodo, 1)))
			.addValue("PARAM4", FechaUtil.formatearFecha("yyyyMMdd", fecPeriodo))
			.addValue("PARAM5", FechaUtil.formatearFecha("yyyyMMdd", FechaUtil.sumarMeses(fecPeriodo, 1)));
			
			this.procReadActor = new SimpleJdbcCall(super.getDataSource())
			.withProcedureName("PA_GET_DATOS_CUADRO_8")
			.returningResultSet("datosCUADRO8", new AfiliadosPorTipoMapper());
			this.procReadActor.setSchemaName("AFDTA");
			
			Map<String, Object> res = this.procReadActor.execute(params);
			listDatosCUADRO8 = (List<AfiliadosPorTipoDTO>)res.get("datosCUADRO8");
			
			return listDatosCUADRO8;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DaoException("","");
		}
	}
}
