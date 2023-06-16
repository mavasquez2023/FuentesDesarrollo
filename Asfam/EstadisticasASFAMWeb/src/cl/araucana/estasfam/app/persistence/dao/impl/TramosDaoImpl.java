package cl.araucana.estasfam.app.persistence.dao.impl;

import java.util.ArrayList;
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
import cl.araucana.estasfam.app.business.model.TramosDTO;
import cl.araucana.estasfam.app.persistence.dao.TramosDao;
import cl.araucana.estasfam.app.persistence.dao.mapper.CargasMesConPagoMapper;
import cl.araucana.estasfam.app.persistence.dao.mapper.TramosMapper;
import cl.araucana.estasfam.common.exceptions.DaoException;
import cl.araucana.estasfam.common.util.FechaUtil;

@Repository
public class TramosDaoImpl extends JdbcDaoSupport implements TramosDao {
	
private static Logger log = Logger.getLogger(EstadisticaASI5490DaoImpl.class);
	
	private SimpleJdbcCall procReadActor;
	
	@Autowired
	public TramosDaoImpl(DataSource dataSource) {
		super();
		super.setDataSource(dataSource);
	}

	@Override
	public List<TramosDTO> getTramos(Integer anho) throws DaoException {
		log.debug("Invocando ");
		List<TramosDTO> listTramos = new ArrayList<TramosDTO>();
		anho = 2013;
		try{
			SqlParameterSource params = new MapSqlParameterSource()
			.addValue("YEAR_TRAMO", anho);
			
			this.procReadActor = new SimpleJdbcCall(super.getDataSource())
			.withProcedureName("PA_GET_TRAMOS_ASFAM")
			.returningResultSet("tramos", new TramosMapper());
			this.procReadActor.setSchemaName("AFDTA");
			
			Map<String, Object> res = this.procReadActor.execute(params);
			listTramos = (List<TramosDTO>)res.get("tramos");
			
			return listTramos;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DaoException("","");
		}
	}

}
