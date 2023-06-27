package cl.araucana.bienestar.bonificaciones.serv;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import cl.araucana.bienestar.bonificaciones.exception.UFNoEncontradaLeasingException;
import cl.araucana.bienestar.bonificaciones.model.Carga;
import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.serv.ServicesSociosSLBean.ServicesSocios;
import cl.araucana.bienestar.bonificaciones.serv.ServicesSociosSLBean.ServicesSociosHome;
import cl.araucana.bienestar.bonificaciones.vo.InformeSocioVO;
import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;

import com.schema.patterns.BusinessDelegate;
import com.schema.util.FileSettings;
import com.schema.util.GeneralException;

/**
 * @author asepulveda
 * Business Delegate para Servicios de Socios de Bienestar
 */
public class ServicesSociosDelegate extends BusinessDelegate {
	// Home y Remote del EJB
	private final static Class homeClass=ServicesSociosHome.class;
	private ServicesSocios services=null;
	
	Logger logger = Logger.getLogger(ServicesSociosDelegate.class);
	
	/**
	 * Constructor del BDelegate. Establece la conexion con el EJB
	 * @throws GeneralException
	 */
	public ServicesSociosDelegate() throws GeneralException {
		// Nombre JNDI del EJB
		String jndi = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
		              "/application-settings/ejbs/bonificaciones/socios-services");
		              
		try {
			InitialContext ic=new InitialContext();
			services = (ServicesSocios)super.getServiceBean(ic, jndi,homeClass);
		} catch (Exception e) {
			throw new GeneralException(this,e);
		}
	}

	/** 
	 * Obtiene la lista de Socios de Bienestar
	 * @param socio Objeto Socio para filtrar (solo considera rut y digito
	 * para efectos de filtro 
	 * @return ArrayList de Socios resultantes
	 */
	public ArrayList getListaSocios(Socio socio) throws RemoteException, Exception, BusinessException {
		return services.getListaSocios(socio);		
	}
	
	/** 
	 * Obtiene un Socio de Bienestar
	 * @param rut del Socio
	 * @return Socio
	 * @throws Exception
	 */
	public Socio getSocio(String rut) throws RemoteException, Exception, BusinessException {
		return services.getSocio(rut);
	}
		
	/** 
	 * Obtiene la lista de candidatos a Bienestar, la que se compone de
	 * los empleados de La Araucana que NO son socios en Bienestar
	 * @param socio Objeto Socio para filtrar (solo considera rut y digito
	 * para efectos de filtro 
	 * @return ArrayList de Socios resultantes
	 */
	public ArrayList getCandidatosBienestar(Socio socio) throws RemoteException, Exception, BusinessException {
		return services.getCandidatosBienestar(socio);		
	}
	
	/** 
	 * Obtiene la lista de Cargas Familiares de un Socio
	 * que esten registradas en Bienestar
	 * @param rut del Socio
	 * @return ArrayList de Carga
	 * @throws Exception
	 */
	public ArrayList getCargasSocio(String rutSocio) throws RemoteException,Exception, BusinessException {
		return services.getCargasSocio(rutSocio);
	}
	
	/** 
	 * Obtiene la lista de Cargas Familiares registradas en Bienestar
	 * @param Carga
	 * @return ArrayList de Carga
	 * @throws Exception
	 */
	public ArrayList getListaCargas(Carga carga) throws RemoteException, Exception, BusinessException{
		return services.getListaCargas(carga);
	}

	/** 
	 * Obtiene una Carga Familiar desde Bienestar
	 * @param rut de la carga familiar buscada, rut socio
	 * @return Carga
	 * @throws Exception
	 */
	public Carga getCarga(String rutCarga,String rutSocio) throws RemoteException, Exception, BusinessException{
		return services.getCarga(rutCarga,rutSocio);
	}
	
	/**
	 * Modifica los datos de una carga en Bienestar
	 * @param carga: el Objeto Carga
	 */
	public void actualizaCarga(Carga carga) throws RemoteException,BusinessException,Exception{
		services.actualizaCarga(carga);
	}
	
	/**
	 * Se encarga recuperar la informaciòn correspondiente al Informe 
	 * del Socio
	 * @param rut del Socio
	 */
	public InformeSocioVO getInformeSocio(String rut,Date fechaUF) throws RemoteException,UFNoEncontradaLeasingException, BusinessException,Exception{
		return services.getInformeSocio(rut,fechaUF);
	}
	
	public List getSociosInactivosConCasosAbiertos() throws BusinessException,
	RemoteException,Exception{
		return services.getSociosInactivosConCasosAbiertos();
	}
}
