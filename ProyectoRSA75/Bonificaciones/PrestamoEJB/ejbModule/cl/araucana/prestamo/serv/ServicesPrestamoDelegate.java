package cl.araucana.prestamo.serv;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.naming.InitialContext;

import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;
import cl.araucana.prestamo.serv.ServicesPrestamoSLBean.ServicesPrestamo;
import cl.araucana.prestamo.serv.ServicesPrestamoSLBean.ServicesPrestamoHome;
import cl.araucana.prestamo.vo.CuotaPendiente_PrestamoVO;
import cl.araucana.prestamo.vo.ImpuestoVO;
import cl.araucana.prestamo.vo.TotalInteresesVO;

import com.schema.patterns.BusinessDelegate;
import com.schema.util.FileSettings;
import com.schema.util.GeneralException;


/**
 * @author asepulveda
 * Business Delegate para Servicios de Prestamo
 */
public class ServicesPrestamoDelegate extends BusinessDelegate {
	// Home y Remote del EJB
	private final static Class homeClass=ServicesPrestamoHome.class;
	private ServicesPrestamo services=null;
	
	/**
	 * Constructor del BDelegate. Establece la conexion con el EJB
	 * @throws GeneralException
	 */
	public ServicesPrestamoDelegate() throws GeneralException {
		// Nombre JNDI del EJB
		String jndi = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
		              "/application-settings/ejbs/prestamo/prestamo-services");
		              
		try {
			InitialContext ic=new InitialContext();
			services = (ServicesPrestamo)super.getServiceBean(ic, jndi,homeClass);
		} catch (Exception e) {
			throw new GeneralException(this,e);
		}
	}
		
	/**
	 * Calcula el valor del impuesto segun nº de cuotas y monto de dinero solicitado
	 * @param monto
	 * @param numCuotas
	 * @return Impuesto
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ImpuestoVO calcularImpuesto(double monto, int numCuotas) throws Exception,BusinessException {
		return services.calcularImpuesto(monto, numCuotas);
	}

	/**
	 * Obtiene las cuotas por Cobrar
	 * @return ArrayList de Cuotas
	 * @throws Exception
	 * @throws BusinessException
	 */ 

	public ArrayList getCuotas() throws Exception, BusinessException {
		return services.getCuotas();
	}

	/**
	 * Obtiene las cuotas por Cobrar de un afiliado
	 * @return ArrayList de Cuotas
	 * @throws Exception
	 * @throws BusinessException
	 */ 

	public ArrayList getCuotas(String rut) throws Exception, BusinessException {
		return services.getCuotas(rut);
	}

	/**
	 * @param string
	 * @param i
	 * @param j
	 */
	public CuotaPendiente_PrestamoVO getMontoUFPrestamoActivo(String rut, int numCuotaLimInf, int numTotalCuotas) throws BusinessException, Exception {
		Collection col = services.getMontoUFPrestamoActivo(rut,numCuotaLimInf,numTotalCuotas);
		Iterator iter = col.iterator();	
		CuotaPendiente_PrestamoVO cuota = new CuotaPendiente_PrestamoVO();
		while (iter.hasNext()){
			cuota = (CuotaPendiente_PrestamoVO) iter.next();
		}
		return cuota;
	}
	
	public long getInteresesPagoAnticipado(long idPrestamo,int cuotaLimIni, int cuotaLimFinal) throws BusinessException, Exception{
		Collection col = services.getInteresesPagoAnticipado(idPrestamo,cuotaLimIni,cuotaLimFinal);
		Iterator iter = col.iterator();
		TotalInteresesVO total = new TotalInteresesVO();
		long totalInt = 0;
		
		while(iter.hasNext()){
			total =(TotalInteresesVO) iter.next();
			totalInt = total.getMontoTotal();
		}
		
		return totalInt;
	}
	
	public TotalInteresesVO getInteresPagoAnticipadoPeriodo(long idPrestamo,int idCuota) throws BusinessException, Exception{
		Collection col = services.getInteresPagoAnticipadoPeriodo(idPrestamo,idCuota);
		Iterator iter = col.iterator();
		TotalInteresesVO total = new TotalInteresesVO();
		long totalInt = 0;
		
		while(iter.hasNext()){
			total =(TotalInteresesVO) iter.next();
			totalInt = total.getMontoTotal();
		}
		
		return total;
	}	

}
