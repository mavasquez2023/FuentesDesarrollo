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

import cl.araucana.estasfam.app.business.model.CargasPorTipoDTO;
import cl.araucana.estasfam.app.business.model.PagosDirectoPorTipoDTO;
import cl.araucana.estasfam.app.persistence.dao.EstadisticaASI5490Dao;
import cl.araucana.estasfam.app.persistence.dao.mapper.CargasMesConPagoMapper;
import cl.araucana.estasfam.app.persistence.dao.mapper.CargasPorTipoMapper;
import cl.araucana.estasfam.app.persistence.dao.mapper.PagosDirectoPorTipoMapper;
import cl.araucana.estasfam.common.exceptions.DaoException;
import cl.araucana.estasfam.common.util.FechaUtil;

@Repository
public class EstadisticaASI5490DaoImpl extends JdbcDaoSupport implements EstadisticaASI5490Dao{

	private static Logger log = Logger.getLogger(EstadisticaASI5490DaoImpl.class);
	
	private SimpleJdbcCall procReadActor;
	
	@Autowired
	public EstadisticaASI5490DaoImpl(DataSource dataSource) {
		super();
		super.setDataSource(dataSource);
	}

	@Override
	public List<CargasPorTipoDTO> getCargasMesConPago(Date mesEstadistica) throws DaoException {
		log.debug("Invocando ");
		List<CargasPorTipoDTO> listCargasConPagoDelMes = new ArrayList<CargasPorTipoDTO>();
		
		try{
			SqlParameterSource params = new MapSqlParameterSource()
			.addValue("MESESTADISTICA", FechaUtil.formatearFecha("yyyyMM", mesEstadistica));
			
			this.procReadActor = new SimpleJdbcCall(super.getDataSource())
			.withProcedureName("PA_GET_CARGAS_MES_CON_PAGO")
			.returningResultSet("cargasMesConPago", new CargasMesConPagoMapper());
			this.procReadActor.setSchemaName("AFDTA");
			
			Map<String, Object> res = this.procReadActor.execute(params);
			listCargasConPagoDelMes = (List<CargasPorTipoDTO>)res.get("cargasMesConPago");
			
			return listCargasConPagoDelMes;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DaoException("","");
		}
	}
	
	@Override
	public List<CargasPorTipoDTO> getCargasAtrasadasConPago(Date mesEstadistica) throws DaoException {
		log.debug("Invocando ");
		List<CargasPorTipoDTO> listCargasAtrasadasConPago = new ArrayList<CargasPorTipoDTO>();
		
		try{
			SqlParameterSource params = new MapSqlParameterSource()
			.addValue("MESESTADISTICA", FechaUtil.formatearFecha("yyyyMM", mesEstadistica));
			
			this.procReadActor = new SimpleJdbcCall(super.getDataSource())
			.withProcedureName("PA_GET_CARGAS_ATRASADAS_CON_PAGO")
			.returningResultSet("cargasAtrasadasConPago", new CargasPorTipoMapper());
			this.procReadActor.setSchemaName("AFDTA");
			
			Map<String, Object> res = this.procReadActor.execute(params);
			listCargasAtrasadasConPago = (List<CargasPorTipoDTO>)res.get("cargasAtrasadasConPago");
			
			return listCargasAtrasadasConPago;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DaoException("","");
		}
	}
	
	@Override
	public List<CargasPorTipoDTO> getCargasMesSinPago(Date mesEstadistica) throws DaoException {
		log.debug("Invocando ");
		List<CargasPorTipoDTO> listCargasMesSinPago = new ArrayList<CargasPorTipoDTO>();
		
		try{
			SqlParameterSource params = new MapSqlParameterSource()
			.addValue("MESESTADISTICA", FechaUtil.formatearFecha("yyyyMM", mesEstadistica));
			
			this.procReadActor = new SimpleJdbcCall(super.getDataSource())
			.withProcedureName("PA_GET_CARGAS_MES_SIN_PAGO")
			.returningResultSet("cargasMesSinPago", new CargasPorTipoMapper());
			this.procReadActor.setSchemaName("AFDTA");
			
			Map<String, Object> res = this.procReadActor.execute(params);
			listCargasMesSinPago = (List<CargasPorTipoDTO>)res.get("cargasMesSinPago");
			
			return listCargasMesSinPago;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DaoException("","");
		}
	}
	
	@Override
	public List<CargasPorTipoDTO> getCargasAtrasadasSinPago(Date mesEstadistica) throws DaoException {
		log.debug("Invocando ");
		List<CargasPorTipoDTO> listCargasMesSinPago = new ArrayList<CargasPorTipoDTO>();
		
		try{
			SqlParameterSource params = new MapSqlParameterSource()
			.addValue("MESESTADISTICA", FechaUtil.formatearFecha("yyyyMM", mesEstadistica));
			
			this.procReadActor = new SimpleJdbcCall(super.getDataSource())
			.withProcedureName("PA_GET_CARGAS_ATRASADAS_SIN_PAGO")
			.returningResultSet("cargasAtrasadasSinPago", new CargasPorTipoMapper());
			this.procReadActor.setSchemaName("AFDTA");
			
			Map<String, Object> res = this.procReadActor.execute(params);
			listCargasMesSinPago = (List<CargasPorTipoDTO>)res.get("cargasAtrasadasSinPago");
			
			return listCargasMesSinPago;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DaoException("","");
		}
	}
	
	@Override
	public List<PagosDirectoPorTipoDTO> getPagosDirectosConPago(Date fecGeneracion, Date fecDesde, Date fecHasta) throws DaoException {
		log.debug("Invocando ");
		List<PagosDirectoPorTipoDTO> listPagosDirectosConPago = new ArrayList<PagosDirectoPorTipoDTO>();
		
		try{
			SqlParameterSource params = new MapSqlParameterSource()
			.addValue("FECGENERACION", FechaUtil.formatearFecha("yyyy-MM-dd", fecGeneracion))
			.addValue("FECDESDE", FechaUtil.formatearFecha("yyyy-MM-dd", fecDesde))
			.addValue("FECHASTA", FechaUtil.formatearFecha("yyyy-MM-dd", fecHasta));
			
			this.procReadActor = new SimpleJdbcCall(super.getDataSource())
			.withProcedureName("PA_GET_PAGOS_DIRECTOS_CON_PAGO")
			.returningResultSet("pagosDirectosConPago", new PagosDirectoPorTipoMapper());
			this.procReadActor.setSchemaName("AFDTA");
			
			Map<String, Object> res = this.procReadActor.execute(params);
			listPagosDirectosConPago = (List<PagosDirectoPorTipoDTO>)res.get("pagosDirectosConPago");
			
			return listPagosDirectosConPago;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DaoException("","");
		}
	}
}
