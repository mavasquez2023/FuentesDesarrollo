package cl.araucana.spl.dao.sqlmap;

import java.math.BigDecimal;
import java.util.List;

import cl.araucana.spl.beans.Sistema;
import cl.araucana.spl.dao.SistemaDAO;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;

public class SistemaSqlMapDAO extends SqlMapDaoTemplate implements SistemaDAO {
	private static final String SQL_FIND_SISTEMAS = "sqlFindSistemas";
	private static final String SQL_FIND_SISTEMA_ORIGEN_BY_CODIGO = "sqlFindSistemaOrigenByCodigo";
	private static final String SQL_FIND_SISTEMA_ORIGEN_BY_ID_PAGO = "sqlFindSistemaOrigenByIdPago";
	
	public SistemaSqlMapDAO(DaoManager daoManager) {
		super(daoManager);
	}

	public Sistema findSistemaOrigenByCodigo(String codigo) {
		Sistema sistema = (Sistema) queryForObject(SQL_FIND_SISTEMA_ORIGEN_BY_CODIGO, codigo);
		return sistema;
	}

	public Sistema findSistemaOrigenByIdPago(BigDecimal idPago) {
		Sistema sistema = (Sistema) queryForObject(SQL_FIND_SISTEMA_ORIGEN_BY_ID_PAGO, idPago);
		return sistema;
	}

	public List findSistemas() {
		return queryForList(SQL_FIND_SISTEMAS);
	}
}
