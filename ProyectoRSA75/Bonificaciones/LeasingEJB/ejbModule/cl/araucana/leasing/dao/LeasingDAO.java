package cl.araucana.leasing.dao;

import java.util.Collection;

import cl.araucana.common.BusinessException;
import cl.araucana.leasing.vo.CuentaAhorroVO;



/**
 * @author asepulveda
 * Metodos expuestos por LeasingDAO
 */
public interface LeasingDAO {

	/** 
	 * Obtiene información de las cuentas de ahorro que tiene el rut consultado
	 * @param long rut
	 * @return Collection 
	 */
	public Collection getListaCuentaAhorroByRut(long rut) throws Exception, BusinessException;
	
	/** 
	 * Obtiene el detalle de una cartola de ahorro
	 * @param CuentaAhorroVO cuenta
	 * @return Collection DetalleMovimientosCuentaAhorroVO
	 */
	public Collection getDetalleCuentaAhorroByCuentaAhorro(CuentaAhorroVO cuenta) throws Exception, BusinessException;


	public Collection getValorUF(String fechaCalculo) throws Exception;
}
