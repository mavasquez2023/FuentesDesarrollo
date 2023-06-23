package cl.araucana.spl.dao.sqlmap;

import java.math.BigDecimal;
import java.util.List;

import cl.araucana.spl.beans.Estado;
import cl.araucana.spl.dao.EstadoDAO;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;

public class EstadoSqlMapDAO extends SqlMapDaoTemplate implements EstadoDAO {
	private static final String SQL_FIND_ESTADO_BY_ID = "sqlFindEstadoById";
	private static final String FIND_ESTADOS = "sqlFindEstados";

	public EstadoSqlMapDAO(DaoManager daoManager) {
		super(daoManager);
	}

	public List getEstados() {
		return queryForList(FIND_ESTADOS);
	}

	public Estado getEstadoById(BigDecimal idEstado) {
		return (Estado) queryForObject(SQL_FIND_ESTADO_BY_ID, idEstado);
	}
}
