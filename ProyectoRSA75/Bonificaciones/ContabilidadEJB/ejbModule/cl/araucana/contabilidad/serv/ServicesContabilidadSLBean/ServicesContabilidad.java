package cl.araucana.contabilidad.serv.ServicesContabilidadSLBean;

import cl.araucana.common.BusinessException;
import cl.araucana.contabilidad.model.Asiento;

/**
 * Remote interface for Enterprise Bean: ServicesContabilidad
 */
public interface ServicesContabilidad extends javax.ejb.EJBObject {
	
	/**
	 * Crea un Asiento contable con sus líneas de detalle
	 * @param asiento
	 * @param lineas
	 * @throws Exception
	 * @throws BusinessException
	 */
	public long creaAsientoContableBienestar(Asiento asiento) throws Exception, BusinessException;
	
}
