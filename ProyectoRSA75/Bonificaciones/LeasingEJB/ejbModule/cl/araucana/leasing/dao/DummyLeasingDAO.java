package cl.araucana.leasing.dao;

import java.util.Collection;

import org.apache.log4j.Logger;

import cl.araucana.common.BusinessException;
import cl.araucana.leasing.vo.CuentaAhorroVO;
import cl.araucana.leasing.vo.DetalleMovimientosCuentaVO;

import com.schema.util.InstanceGenerator;

/**
 * @author asepulveda
 *
 */
public class DummyLeasingDAO implements LeasingDAO {
	static Logger logger = Logger.getLogger(DummyLeasingDAO.class);
//	private final static String PREFIX="DUMMY-";
	
	
//	private String leasingDatabase;
	
	/**
	 * Constructor de DAO
	 * Recupera nombre de Bases de Datos utilizadas
	 */
	public DummyLeasingDAO(){
	}

	/** 
	 * Obtiene información de las cuentas de ahorro que tiene el rut consultado
	 * @param long rut
	 * @return Collection 
	 */
	public Collection getListaCuentaAhorroByRut(long rut) throws Exception, BusinessException {
		
		return InstanceGenerator.buildCollection(CuentaAhorroVO.class);
		
	}
	
	/** 
	 * Obtiene el detalle de una cartola de ahorro
	 * @param CuentaAhorroVO cuenta
	 * @return Collection DetalleMovimientosCuentaAhorroVO
	 */
	public Collection getDetalleCuentaAhorroByCuentaAhorro(CuentaAhorroVO cuenta) throws Exception, BusinessException {

		return InstanceGenerator.buildCollection(DetalleMovimientosCuentaVO.class);		
		
	}

	/* (no Javadoc)
	 * @see cl.araucana.leasing.dao.LeasingDAO#getValorUF(java.sql.Date)
	 */
	public Collection getValorUF(String fechaCalculo) throws Exception {
		// TODO Apéndice de método generado automáticamente
		return null;
	}


	
}
