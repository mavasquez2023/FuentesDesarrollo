package cl.araucana.bienestar.bonificaciones.serv.ServicesSociosSLBean;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import cl.araucana.bienestar.bonificaciones.exception.UFNoEncontradaLeasingException;
import cl.araucana.bienestar.bonificaciones.model.Carga;
import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.vo.InformeSocioVO;
import cl.araucana.common.BusinessException;

/**
 * Remote interface for Enterprise Bean: ServicesSocios
 */
public interface ServicesSocios extends javax.ejb.EJBObject {

	/**
	 * Obtiene la lista de candidatos a Bienestar, la que se compone de los
	 * empleados de La Araucana que NO son socios en Bienestar
	 * 
	 * @param socio
	 *            Objeto Socio para filtrar (solo considera rut y digito para
	 *            efectos de filtro )
	 * @return ArrayList de Socios resultantes
	 */
	public ArrayList getCandidatosBienestar(Socio socio)
			throws RemoteException, Exception, BusinessException;

	/**
	 * Obtiene la lista de Socios de Bienestar
	 * 
	 * @param socio
	 *            Objeto Socio para filtrar (solo considera rut y digito para
	 *            efectos de filtro )
	 * @return ArrayList de Socios resultantes
	 */
	public ArrayList getListaSocios(Socio socio) throws RemoteException,
			Exception, BusinessException;

	/**
	 * Obtiene un Socio de Bienestar
	 * 
	 * @param rut
	 *            del Socio
	 * @return Socio
	 * @throws Exception
	 */
	public Socio getSocio(String rut) throws RemoteException, Exception,
			BusinessException;

	/**
	 * Obtiene la lista de Cargas Familiares dependientes de un Socio que esten
	 * registradas en Bienestar
	 * 
	 * @param rut
	 *            del Socio
	 * @return ArrayList de Carga
	 * @throws Exception
	 */
	public ArrayList getCargasSocio(String rutSocio) throws RemoteException,
			Exception;

	/**
	 * Obtiene la lista de Cargas Familiares registradas en Bienestar
	 * 
	 * @param Carga
	 * @return ArrayList de Carga
	 * @throws Exception
	 */
	public ArrayList getListaCargas(Carga carga) throws Exception,
			BusinessException;

	/**
	 * Obtiene una Carga Familiar desde Bienestar
	 * 
	 * @param rut
	 *            de la carga familiar buscada, rut del socio
	 * @return Carga
	 * @throws Exception
	 */
	public Carga getCarga(String rutCarga, String rutSocio) throws Exception,
			BusinessException;

	/**
	 * Modifica los datos de una carga en Bienestar
	 * 
	 * @param carga:
	 *            el Objeto Carga
	 */
	public void actualizaCarga(Carga carga) throws RemoteException,
			BusinessException, Exception;

	/**
	 * Modifica los datos de las cargas familiares de un socio de Bienestar
	 * 
	 * @param rut
	 *            del Socio
	 */
	public void actualizaCargas(String rutSocio) throws RemoteException,
			BusinessException, Exception;

	/**
	 * Se encarga recuperar la informaciòn correspondiente al Informe del Socio
	 * 
	 * @param rut
	 *            del Socio
	 */
	public InformeSocioVO getInformeSocio(String rut, Date fechaUF)
			throws RemoteException, BusinessException,
			UFNoEncontradaLeasingException, Exception;

	public List getSociosInactivosConCasosAbiertos() throws BusinessException,
			Exception;
}
