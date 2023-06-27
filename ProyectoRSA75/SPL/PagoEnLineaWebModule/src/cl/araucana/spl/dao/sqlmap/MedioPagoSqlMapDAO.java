package cl.araucana.spl.dao.sqlmap;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import cl.araucana.spl.beans.CTACTE;
import cl.araucana.spl.beans.Convenio;
import cl.araucana.spl.beans.MedioPago;
import cl.araucana.spl.dao.MedioPagoDAO;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;

public class MedioPagoSqlMapDAO extends SqlMapDaoTemplate implements MedioPagoDAO {
	private static final String SQL_FIND_MEDIO_PAGO_BY_ID = "sqlFindMedioPagoById";

	private static final String SQL_FIND_MEDIO_PAGO_BY_CODIGO = "sqlFindMedioPagoByCodigo";

	private static final String SQL_FIND_MEDIOS_PAGO = "sqlFindMediosPago";

	private static final String SQL_FIND_MEDIOS_PAGO_BATCH = "sqlFindMediosPagoBatch";

	private static final String SQL_FIND_MONTO_BY_MEDIOS_PAGO = "findMontoPagadoPorMedioPago";
	
	private static final String SQL_UPDATE_LIBRO_BANCO= "updateMarcaLibroBanco";

	private static final String SQL_FIND_CONVENIO_BY_CODIGO_BANCO = "sqlFindConvenioByCodigoBanco";

	public MedioPagoSqlMapDAO(DaoManager daoManager) {
		super(daoManager);
	}

	public MedioPago getMedioPagoById(BigDecimal idMedioPago) {
		return (MedioPago) queryForObject(SQL_FIND_MEDIO_PAGO_BY_ID,
				idMedioPago);
	}

	public MedioPago getMedioPagoByCodigo(String codigo) {
		return (MedioPago) queryForObject(SQL_FIND_MEDIO_PAGO_BY_CODIGO, codigo);
	}

	public List getMediosPago() {
		return queryForList(SQL_FIND_MEDIOS_PAGO);
	}

	public Convenio getConvenioByCodigoBanco(String codigoBanco) {
		return (Convenio) queryForObject(SQL_FIND_CONVENIO_BY_CODIGO_BANCO,
				codigoBanco);
	}

	public List getMediosPagoBatch() {
		return queryForList(SQL_FIND_MEDIOS_PAGO_BATCH);
	}

	public CTACTE getMontoPagadoPorMedioPago(HashMap map) {
		return  (CTACTE) queryForObject(SQL_FIND_MONTO_BY_MEDIOS_PAGO, map);
	}

	public int actualizaMarcaLibroBanco(HashMap map) {
		return update(SQL_UPDATE_LIBRO_BANCO, map);
	
	}
}
