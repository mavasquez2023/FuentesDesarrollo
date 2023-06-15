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
import cl.araucana.estasfam.app.business.model.CargasPorTipoDTO;
import cl.araucana.estasfam.app.business.model.PagosDirectoPorTipoDTO;
import cl.araucana.estasfam.app.persistence.dao.EstadisticaASI5491Dao;
import cl.araucana.estasfam.app.persistence.dao.mapper.CargasPorColumnaMapper;
import cl.araucana.estasfam.app.persistence.dao.mapper.CargasPorTipoMapper;
import cl.araucana.estasfam.app.persistence.dao.mapper.PagosDirectoPorTipoMapper;
import cl.araucana.estasfam.common.exceptions.DaoException;
import cl.araucana.estasfam.common.util.FechaUtil;

@Repository
public class EstadisticaASI5491DaoImpl extends JdbcDaoSupport implements EstadisticaASI5491Dao{

	private static Logger log = Logger.getLogger(EstadisticaASI5491DaoImpl.class);
	
	private SimpleJdbcCall procReadActor;
	
	@Autowired
	public EstadisticaASI5491DaoImpl(DataSource dataSource) {
		super();
		super.setDataSource(dataSource);
	}

	@Override
	public List<CargasPorColumnaDTO> getCargasAutorizadas(Date mesEstadistica) throws DaoException {
		log.debug("Invocando ");
		List<CargasPorColumnaDTO> listCargasAutorizadas = new ArrayList<CargasPorColumnaDTO>();
		
		try{
			SqlParameterSource params = new MapSqlParameterSource()
			.addValue("MESESTADISTICA", FechaUtil.formatearFecha("yyyyMM", mesEstadistica));
			
			this.procReadActor = new SimpleJdbcCall(super.getDataSource())
			.withProcedureName("PA_GET_CARGAS_AUTORIZADAS")
			.returningResultSet("cargasAutorizadas", new CargasPorColumnaMapper());
			this.procReadActor.setSchemaName("AFDTA");
			
			Map<String, Object> res = this.procReadActor.execute(params);
			listCargasAutorizadas = (List<CargasPorColumnaDTO>)res.get("cargasAutorizadas");
			
			return listCargasAutorizadas;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DaoException("","");
		}
	}
	
	@Override
	public List<PagosDirectoPorTipoDTO> getPagosDirectosAutorizados(Date fecGeneracion, Date fecDesde, Date fecHasta) throws DaoException {
		log.debug("Invocando ");
		List<PagosDirectoPorTipoDTO> listPagosDirectosAutorizados = new ArrayList<PagosDirectoPorTipoDTO>();
		
		try{
			SqlParameterSource params = new MapSqlParameterSource()
			.addValue("FECGENERACION", FechaUtil.formatearFecha("yyyy-MM-dd", fecGeneracion))
			.addValue("FECDESDE", FechaUtil.formatearFecha("yyyy-MM-dd", fecDesde))
			.addValue("FECHASTA", FechaUtil.formatearFecha("yyyy-MM-dd", fecHasta));
			
			this.procReadActor = new SimpleJdbcCall(super.getDataSource())
			.withProcedureName("PA_GET_PAGOS_DIRECTOS_AUTORIZADOS")
			.returningResultSet("pagosDirectosAutorizados", new PagosDirectoPorTipoMapper());
			this.procReadActor.setSchemaName("AFDTA");
			
			Map<String, Object> res = this.procReadActor.execute(params);
			listPagosDirectosAutorizados = (List<PagosDirectoPorTipoDTO>)res.get("pagosDirectosAutorizados");
			
			return listPagosDirectosAutorizados;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DaoException("","");
		}
	}
	
	@Override
	public List<CargasPorTipoDTO> getSubsidiosVigenetes() throws DaoException {
		log.debug("Invocando ");
		List<CargasPorTipoDTO> listSubsidiosVigentes = new ArrayList<CargasPorTipoDTO>();
		
		try{
			this.procReadActor = new SimpleJdbcCall(super.getDataSource())
			.withProcedureName("PA_GET_SUBSIDIOS_VIGENTES")
			.returningResultSet("subsidiosVigentes", new CargasPorTipoMapper());
			this.procReadActor.setSchemaName("AFDTA");
			
			Map<String, Object> res = this.procReadActor.execute();
			listSubsidiosVigentes = (List<CargasPorTipoDTO>)res.get("subsidiosVigentes");
			
			return listSubsidiosVigentes;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DaoException("","");
		}
	}
}
