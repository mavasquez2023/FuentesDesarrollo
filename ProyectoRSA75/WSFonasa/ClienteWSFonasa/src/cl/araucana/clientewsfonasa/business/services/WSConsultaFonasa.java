/**
 * 
 */
package cl.araucana.clientewsfonasa.business.services;

import cl.araucana.clientewsfonasa.business.to.ResponseFormFonasaTO;
import cl.araucana.clientewsfonasa.business.to.ResponseWSFonasaTO;
import cl.araucana.clientewsfonasa.common.exception.DaoException;
import cl.araucana.clientewsfonasa.common.exception.ServiceException;

/**
 * @author usist199
 *
 */
public interface WSConsultaFonasa {
	public abstract ResponseWSFonasaTO consultarRutFonasa(String rut) throws ServiceException, DaoException;
	public abstract ResponseFormFonasaTO consultarEstadoFormulario(int color, int numeroLicencia, int rutAfiliado)  throws ServiceException, DaoException;
}
