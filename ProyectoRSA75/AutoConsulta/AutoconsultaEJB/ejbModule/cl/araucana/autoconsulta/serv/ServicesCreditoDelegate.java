package cl.araucana.autoconsulta.serv;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.naming.InitialContext;

import cl.araucana.autoconsulta.serv.ServicesCreditoSLBean.ServicesCredito;
import cl.araucana.autoconsulta.serv.ServicesCreditoSLBean.ServicesCreditoHome;
import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;
import cl.laaraucana.credito.to.CreditoTO;

import com.schema.patterns.BusinessDelegate;
import com.schema.util.FileSettings;
import com.schema.util.GeneralException;

/**
 * @author 
 * Business Delegate para Servicios de Empresas
 */
public class ServicesCreditoDelegate extends BusinessDelegate {
	// Home y Remote del EJB
	private final static Class homeClass = ServicesCreditoHome.class;
	private ServicesCredito services = null;

	/**
	 * Constructor del BDelegate. Establece la conexion con el EJB
	 * @throws GeneralException
	 */
	public ServicesCreditoDelegate() throws GeneralException {
		// Nombre JNDI del EJB
		String jndi =
			FileSettings.getValue(
				AppConfig.getInstance().settingsFileName,
				"/application-settings/ejbs/creditos/creditos-services");

		try {
			InitialContext ic = new InitialContext();
			this.services =
				(ServicesCredito) super.getServiceBean(
					ic,
					jndi,
					homeClass);
		} catch (Exception e) {
			throw new GeneralException(this, e);
		}
	}
	/**
	 * Inicializa rut de consulta de credito
	 * @param long rutAfiliado
	 * @return no hay
	 */
	public void setRut(long rutAfiliado) {
		try {
			services.setRut(rutAfiliado);
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}

	/**
	 * Inicializa rut de consulta de credito
	 * @param long rutEmpresa, long rutAfiliado
	 * @return no hay
	 */
	public void setRut(long rutEmpleador, long rutAfiliado) {
		try {
			services.setRut(rutEmpleador, rutAfiliado);
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}

	/**
	 * Inicializa BO con un credito
	 * @param CreditoTO credito
	 * @return no hay
	 */
	public void setCredito(CreditoTO credito) {
		try {
			services.setCredito(credito);
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}

	/**
	 * Obtiene los credito de un afiliado, segun rut inicializado
	 * @param 
	 * @return Collection CreditoTO
	 */
	public Collection getCreditos() {
		try {
			return services.getCreditos();
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Obtiene el credito de un afiliado, segun credito de inicializacion
	 * @param 
	 * @return Collection CreditoTO
	 */
	public Collection getCredito() {
		try {
			return services.getCredito();
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Obtiene los pagos de un credito, segun credito de inicializacion
	 * @param 
	 * @return Collection PagoCuotaTO
	 */
	public Collection obtenerPagos() {
		try {
			return services.obtenerPagos();
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Obtiene las cuotas de un credito, segun credito de inicializacion
	 * @param 
	 * @return Collection CuotaTO
	 */
	public Collection obtenerCuotas() {
		try {
			return services.obtenerCuotas();
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return null;
	}
}
