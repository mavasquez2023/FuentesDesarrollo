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

import cl.araucana.estasfam.app.business.model.ActividadEconomicaDTO;
import cl.araucana.estasfam.app.business.model.CargasPagosDirectoDTO;
import cl.araucana.estasfam.app.persistence.dao.EstadisticaASI4560Dao;
import cl.araucana.estasfam.app.persistence.dao.mapper.ActividadEconomicaMapper;
import cl.araucana.estasfam.app.persistence.dao.mapper.CargasPagosDirectoPorRegionMapper;
import cl.araucana.estasfam.common.exceptions.DaoException;
import cl.araucana.estasfam.common.util.FechaUtil;

@Repository
public class EstadisticaASI4560DaoImpl extends JdbcDaoSupport implements EstadisticaASI4560Dao{

	private static Logger log = Logger.getLogger(EstadisticaASI4560DaoImpl.class);
	
	private SimpleJdbcCall procReadActor;
	
	@Autowired
	public EstadisticaASI4560DaoImpl(DataSource dataSource) {
		super();
		super.setDataSource(dataSource);
	}

	@Override
	public List<CargasPagosDirectoDTO> getCargasPagosDirectosPorRegion(Date mesEstadistica, Date fecGeneracion, 
			Date fecDesde, Date fecHasta) throws DaoException {
		log.debug("Invocando ");
		List<CargasPagosDirectoDTO> listCargasPagosDirectosPorRegion = new ArrayList<CargasPagosDirectoDTO>();
		
		try{
			SqlParameterSource params = new MapSqlParameterSource()
			.addValue("MESESTADISTICA", FechaUtil.formatearFecha("yyyyMM", mesEstadistica))
			.addValue("FECGENERACION", FechaUtil.formatearFecha("yyyy-MM-dd", fecGeneracion))
			.addValue("FECDESDE", FechaUtil.formatearFecha("yyyy-MM-dd", fecDesde))
			.addValue("FECHASTA", FechaUtil.formatearFecha("yyyy-MM-dd", fecHasta));
			
			this.procReadActor = new SimpleJdbcCall(super.getDataSource())
			.withProcedureName("PA_GET_CAGAS_Y_PAGOS_DIRECTOS_POR_REGION")
			.returningResultSet("cargasPagosPorRegion", new CargasPagosDirectoPorRegionMapper());
			this.procReadActor.setSchemaName("AFDTA");
			
			Map<String, Object> res = this.procReadActor.execute(params);
			listCargasPagosDirectosPorRegion = (List<CargasPagosDirectoDTO>)res.get("cargasPagosPorRegion");
			
			return listCargasPagosDirectosPorRegion;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DaoException("","");
		}
	}
	
	@Override
	public List<ActividadEconomicaDTO> getActividades() throws DaoException {
		log.debug("Invocando ");
		List<ActividadEconomicaDTO> listActividades = new ArrayList<ActividadEconomicaDTO>();
		
		try{
			this.procReadActor = new SimpleJdbcCall(super.getDataSource())
			.withProcedureName("PA_GET_ACTIVIDADES_ECONOMICAS")
			.returningResultSet("actividadesEconomicas", new ActividadEconomicaMapper());
			this.procReadActor.setSchemaName("AFDTA");
			
			Map<String, Object> res = this.procReadActor.execute();
			listActividades = (List<ActividadEconomicaDTO>)res.get("actividadesEconomicas");
			
			return listActividades;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DaoException("","");
		}
	}
}
