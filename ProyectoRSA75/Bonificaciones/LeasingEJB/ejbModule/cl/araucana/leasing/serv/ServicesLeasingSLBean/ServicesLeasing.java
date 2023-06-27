package cl.araucana.leasing.serv.ServicesLeasingSLBean;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.Collection;

import com.schema.util.GeneralException;

import cl.araucana.common.BusinessException;
import cl.araucana.leasing.vo.CuentaAhorroVO;


/**
 * Remote interface for Enterprise Bean: ServicesCreditos
 */
public interface ServicesLeasing extends javax.ejb.EJBObject {
	
	/** 
	 * Obtiene información de las cuentas de ahorro que tiene el rut consultado
	 * @param long rut
	 * @return Collection 
	 */
	public Collection getListaCuentaAhorroByRut(long rut) throws Exception, BusinessException, GeneralException, RemoteException;
	
	/** 
	 * Obtiene el detalle de una cartola de ahorro
	 * @param CuentaAhorroVO cuenta
	 * @return Collection DetalleMovimientosCuentaAhorroVO
	 */
	public Collection getDetalleCuentaAhorroByCuentaAhorro(CuentaAhorroVO cuenta) throws Exception, BusinessException, GeneralException, RemoteException;

	public Collection getValorUF(Date fechaCalculo) throws Exception;
}