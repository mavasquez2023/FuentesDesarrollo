package cl.araucana.bienestar.bonificaciones.serv.ServicesSociosSLBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import cl.araucana.bienestar.bonificaciones.dao.BonificacionesDAO;
import cl.araucana.bienestar.bonificaciones.dao.DAOFactory;
import cl.araucana.bienestar.bonificaciones.exception.UFNoEncontradaLeasingException;
import cl.araucana.bienestar.bonificaciones.model.Carga;
import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosDelegate;
import cl.araucana.bienestar.bonificaciones.serv.ServicesSociosDelegate;
import cl.araucana.bienestar.bonificaciones.vo.CuotaPrestamoVO;
import cl.araucana.bienestar.bonificaciones.vo.DescuentoPendienteSocioVO;
import cl.araucana.bienestar.bonificaciones.vo.InformeSocioVO;
import cl.araucana.bienestar.bonificaciones.vo.ParamOperacionesVO;
import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;
import cl.araucana.leasing.serv.ServicesLeasingDelegate;
import cl.araucana.personal.serv.ServicesEmpleadosDelegate;
import cl.araucana.personal.vo.CargaVO;
import cl.araucana.personal.vo.EmpleadoVO;
import cl.araucana.prestamo.serv.ServicesPrestamoDelegate;
import cl.araucana.prestamo.vo.CuotaPendiente_PrestamoVO;
import cl.araucana.prestamo.vo.TotalInteresesVO;

import com.schema.util.FileSettings;

/**
 * @author aituarte
 * Bean implementation class for Enterprise Bean: ServicesSocios
 * Servicios de Consulta a Información de Socios de Bienestar de La Araucana
 */
public class ServicesSociosBean implements javax.ejb.SessionBean {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	
	private BonificacionesDAO bonificacionesDao;
	
	Logger logger = Logger.getLogger(ServicesSociosBean.class);
	
	private javax.ejb.SessionContext mySessionCtx;
	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
		// Recurso DAO de Bonificaciones
		int daoType = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/bonificaciones/dao-type"));	
		try {
			DAOFactory daoFactory = (DAOFactory)DAOFactory.getDAOFactory(daoType);
			bonificacionesDao = daoFactory.getBonificacionesDAO();
		} catch (Exception e) {
			throw new javax.ejb.CreateException(e.getMessage());
		}

	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}
	
	/** 
	 * Entrega la lista de Socios de Bienestar (dentro del BEAN se especifican los filtros a utilizar)
	 * @param rut String de busqueda en Rut
	 * @param nombre String de busqueda en nombre
	 * @return ArrayList de Socio
	 * @throws Exception
	 */
	public ArrayList getListaSocios(Socio socio) throws Exception, BusinessException {
/*
		Date fecha = new Date();
		crearAllSocios();
		logger.debug("Inicio: "+fecha+" Fecha Fin: "+new Date());
*/
		return bonificacionesDao.getListaSocios(socio);	
	}
	
	/** 
	 * Obtiene un Socio de Bienestar
	 * @param rut del Socio
	 * @return Socio
	 * @throws Exception
	 */
	public Socio getSocio(String rut) throws Exception, BusinessException {
		
		ServicesEmpleadosDelegate personal = new ServicesEmpleadosDelegate();
		Socio socio = bonificacionesDao.getSocio(rut); 
		socio.setCodComuna(personal.getComuna(socio.getCodComuna()));
		socio.setCodCiudad(personal.getCiudad(socio.getCodCiudad()));
		socio.setCodCargo(personal.getDeptoEmpleadoByCodigoCargo(socio.getCodCargo()));
		socio.setOficina(personal.getOficinaEmpleado(socio.getOficina()));
		return socio;
	}
	
	
	/** 
	 * Obtiene la lista de candidatos a Bienestar, la que se compone de
	 * los empleados de La Araucana que NO son socios en Bienestar
	 * @param socio Objeto Socio para filtrar (solo considera rut y digito
	 * para efectos de filtro) 
	 * @return ArrayList de Socios resultantes
	 */
	public ArrayList getCandidatosBienestar(Socio socio) throws Exception, BusinessException {
		logger.debug("Inicia Servicios de Candidatos");
		
		// Recupera desde Personal la lista de empleados
		logger.debug("Recuperando Personal");
		ServicesEmpleadosDelegate personal = new ServicesEmpleadosDelegate();

		String strRut = socio.getRut();
		
		ArrayList empleados = personal.getListaEmpleados(strRut, socio.getNombre());
		logger.debug("Retornaron "+empleados.size()+" empleados");
		
		// Recupera desde Bienestar la lista actual de Socios
		logger.debug("Recuperando Socios");
		ServicesSociosDelegate bonif = new ServicesSociosDelegate();
		socio.setEstado(Socio.STD_ACTIVO);
		ArrayList socios = bonif.getListaSocios(socio);
		logger.debug("Retornaron "+socios.size()+" socios");
		
		// Busca aquellos empleados que no son Socios
		logger.debug("Identificando Candidatos a partir de la informacion");
		ArrayList retorno = new ArrayList();

		for (int i=0; i<empleados.size(); i++) {
			EmpleadoVO empleado = (EmpleadoVO)empleados.get(i);
			boolean esSocio = false;
			// Comparar el empleados versus los socios
			for (int j=0; j<socios.size(); j++) {
				Socio socioLista = (Socio)socios.get(j);
				
				StringTokenizer st = new StringTokenizer(empleado.getRut(),"-");				
				String run=st.nextToken();
				//si lo encuentro, dejo de buscarlo e indico que ya es Socio
				if (run.equals(socioLista.getRut())) {
					esSocio=true;
					break;
				}
			}
			//Si no es Socio, entonces es Candidato
			if (!esSocio){
				Socio candidato = new Socio(empleado);
				candidato.setEstado(Socio.STD_BORRADOR);
				retorno.add(candidato);			
			}
		}
		return retorno;
	}

	/** 
	 * Obtiene la lista de Cargas Familiares dependientes
	 * de un Socio que esten registradas en Bienestar
	 * @param rut del Socio
	 * @return ArrayList de Carga
	 * @throws Exception
	 */
	public ArrayList getCargasSocio(String rutSocio) throws Exception, BusinessException {
		return bonificacionesDao.getCargasSocio(rutSocio);
	}
	
	/** 
	 * Obtiene la lista de Cargas Familiares registradas en Bienestar
	 * @param Carga
	 * @return ArrayList de Carga
	 * @throws Exception
	 */
	public ArrayList getListaCargas(Carga carga) throws Exception, BusinessException{
		return bonificacionesDao.getListaCargas(carga);
	}
	
	/** 
	 * Obtiene una Carga Familiar desde Bienestar
	 * @param rut de la carga familiar buscada, rut del socio
	 * @return Carga
	 * @throws Exception
	 */
	public Carga getCarga(String rutCarga, String rutSocio) throws Exception, BusinessException {
		return bonificacionesDao.getCarga(rutCarga,rutSocio);
	}
	
	/**
	 * Actualiza los datos de una carga familiar
	 * @param cargaBeneficios
	 * @param carga
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void updateCarga(Carga cargaBeneficios, Carga carga)  throws Exception, BusinessException {
		//actualiza la información de la carga con información de Sist. Beneficios
		if (cargaBeneficios.getEstadoCarga().equals(EmpleadoVO.STD_INACTIVO)) {
			carga.setEstadoCarga(Socio.STD_ELIMINADO);
		}
		if(cargaBeneficios.getEstadoCarga().equals(EmpleadoVO.STD_ACTIVO)){
			carga.setEstadoCarga(Socio.STD_ACTIVO);
		}
		cargaBeneficios.setDvCarga(carga.getDvCarga());
		cargaBeneficios.setDvSocio(carga.getDvSocio());
		cargaBeneficios.setEstadoCarga(carga.getEstadoCarga());
		cargaBeneficios.setFecIngCarga(carga.getFecIngCarga());
		cargaBeneficios.setRutCarga(carga.getRutCarga());
		cargaBeneficios.setRutSocio(carga.getRutSocio());
		
		//Actualiza Carga
		logger.debug("Actualizando Carga");
		bonificacionesDao.updateCarga(cargaBeneficios);
		logger.debug("Carga Actualizada");
	}
	
	/**
	 * Modifica los datos de una carga en Bienestar
	 * @param carga: el Objeto Carga
	 */
	public void actualizaCarga(Carga carga) throws Exception, BusinessException{
		
		logger.debug("Inicia Modificar Carga");
		//valido que la carga este en estado "Activo"
		if (!carga.getEstadoCarga().equals(Socio.STD_ACTIVO)){
			throw new BusinessException("CCAF_BONIF_CARGAINVALIDA",
									   "El estado de la Carga es incorrecto");
		}
		// Recupera desde el sistema de Beneficios la informacion de la Carga
		logger.debug("Recuperando Carga");
		ServicesEmpleadosDelegate personal = new ServicesEmpleadosDelegate();

		//debo convertir el rut de la carga en long
		long rutCarga = Long.parseLong(carga.getRutCarga());
		
		Carga cargaBeneficios = new Carga(personal.getCargaFamiliar(rutCarga));
		updateCarga(cargaBeneficios,carga);	
	}
	
	/**
	 * Modifica los datos de las cargas familiares de un socio de Bienestar
	 * @param rut del Socio
	 */
	public void actualizaCargas(String rut) throws BusinessException,Exception{
		
		ServicesEmpleadosDelegate personal = new ServicesEmpleadosDelegate();

		//debo convertir el rut del socio en long
		long rutSocio = Long.parseLong(rut);
		
		//Busco las cargas familiares en Beneficios
		ArrayList cargasBenef = personal.getListaCargasFamiliaresEmpleado(rutSocio);
		logger.debug("Retornaron " + cargasBenef.size()+ " cargas desde Beneficios");

		//Busco las cargas familiares en Bonificaciones
		ArrayList cargas = bonificacionesDao.getCargasSocio(rut); 
		logger.debug("Retornaron " + cargas.size()+ " cargas desde Bonificaciones");
	
		boolean existe = false;
		//String estadoCarga = null;
		Carga cargaBenef = null,carga=null; 
		for (int i=0; i<cargasBenef.size(); i++) {
			existe = false;
			
			cargaBenef = new Carga((CargaVO)cargasBenef.get(i));
			for (int j=0; j<cargas.size(); j++) {
				carga = (Carga)cargas.get(j);
				if (cargaBenef.getRutCarga().equals(carga.getRutCarga())) {
					existe = true;
					break;	
				}
			}
			if (existe) {
				if (carga.getEstadoCarga().equals(Socio.STD_ACTIVO)) {
					updateCarga(cargaBenef,carga);
				}
								
			}
			//Si la carga no existía en bonificaciones
			else if (cargaBenef.getEstadoCarga().equals(EmpleadoVO.STD_ACTIVO)) {
				//Esta activa en Beneficios, entonces la creo.	
				cargaBenef.setEstadoCarga(Socio.STD_ACTIVO);
				logger.debug("Creando Carga");
				bonificacionesDao.insertCarga(cargaBenef);
				logger.debug("Carga Creada");			
			}		
		}
	}
	
	
	/**
	 * Se encarga recuperar la informaciòn correspondiente al Informe 
	 * del Socio
	 * @param rut del Socio
	 */
	public InformeSocioVO getInformeSocio(String rut,java.sql.Date fechaUF) throws BusinessException,UFNoEncontradaLeasingException,Exception{
		
		// Recupera desde el sistema de Personal la informacion del Socio
		ServicesConveniosDelegate convenios = new ServicesConveniosDelegate();
		
		ServicesLeasingDelegate delLeasing = new ServicesLeasingDelegate();
		
		ServicesPrestamoDelegate delPrestamo = new ServicesPrestamoDelegate();
		
		float valorUF = 0.0f;
	
		//obtiene el valor de la UF desde el sistema de tesoreria
		valorUF = delLeasing.getValorUF(fechaUF);
		if(valorUF == 0)
			throw new UFNoEncontradaLeasingException();
				
		InformeSocioVO informe=new InformeSocioVO();

		// Recupera desde Bonificaciones la informacion del Socio
		Socio socio = bonificacionesDao.getSocio(rut);
				
		// Recupera desde el sistema de Bonificaciones la informacion de
		// las Cargas del Socio
		ArrayList listaCargas=bonificacionesDao.getCargasSocio(rut);
				
		// Recupera desde el sistema de Bonificaciones la informacion de
		// los Vales entregados sin utilizar del Socio
		ArrayList listaVales = convenios.getValesNoUsadosByRut(rut,0);

		// Recupera desde el sistema de Bonificaciones la informacion de
		// los Prestamos del Socio
		ArrayList listaPrestamos=bonificacionesDao.getCuotasPrestamoVigenteSocio(rut);
		
		Iterator iterPrestamos = listaPrestamos.iterator();
		Calendar cal = new GregorianCalendar();
		
		while(iterPrestamos.hasNext()){
			//actualizamos los datos de prestamo de Bonificaciones con el valor real de la cuota en UF desde el sistema de prestamo.
			CuotaPrestamoVO cuotaPrestamo = (CuotaPrestamoVO) iterPrestamos.next();
			
			CuotaPendiente_PrestamoVO cuotaSistemaPrestamos = delPrestamo.getMontoUFPrestamoActivo(cuotaPrestamo.getRut(),cuotaPrestamo.getCuotaActual() + 1,cuotaPrestamo.getNumeroCuotasTotales());
			
			if(cuotaSistemaPrestamos.getIdPrestamo() != 0){
			
				cuotaPrestamo.setSaldoTotal(Math.round(cuotaSistemaPrestamos.getMontoUF() * valorUF));

				//ademas debemos restar la devolución de intereses de cuotas futuras en pesos.
				long totalIntereses = delPrestamo.getInteresesPagoAnticipado(cuotaSistemaPrestamos.getIdPrestamo(),cuotaPrestamo.getCuotaActual() + 2,cuotaPrestamo.getNumeroCuotasTotales());
				
				cuotaPrestamo.setSaldoTotal(cuotaPrestamo.getSaldoTotal() - (int) totalIntereses);
			
				//y un proporcional del interes del mes en curso
				TotalInteresesVO interesPeriodo = delPrestamo.getInteresPagoAnticipadoPeriodo(cuotaSistemaPrestamos.getIdPrestamo(),cuotaPrestamo.getCuotaActual() + 1);
				
				
				long proporcional = 0;
				
				int anio = cal.get(Calendar.YEAR);
				int mes = cal.get(Calendar.MONTH) + 1;
				
				Calendar calUF = new GregorianCalendar();
				calUF.setTime(fechaUF);
				
				if(anio == interesPeriodo.getAnio() && 
					mes == interesPeriodo.getMes()){
						
					if(calUF.get(Calendar.DATE) < 25 ){	
						//(25 – Fecha de generación del informe)/ 30 * valor del interes de la cuota para el período actual
						int dias = (25 - calUF.get(Calendar.DATE) );
						float p = dias / 30f;
						p = p * interesPeriodo.getMontoTotal();
						proporcional = new Float(p).longValue();
					}
					else
						proporcional = 0;
				}
				else{
					//%100 del interes
					proporcional = interesPeriodo.getMontoTotal();
				}
				cuotaPrestamo.setSaldoTotal(cuotaPrestamo.getSaldoTotal() - (int) proporcional);
			}	
		}
		
		// Recupera desde el sistema de Bonificaciones la informacion de
		// los Descuentos del Socio
		ArrayList descuentos=bonificacionesDao.getDescuentosPendientesSocio(rut);
		ArrayList listaDescuentos = new ArrayList();
		for (int x=0;x<descuentos.size();x++) {
			DescuentoPendienteSocioVO descuentoPendiente = (DescuentoPendienteSocioVO) descuentos.get(x);
			if (descuentoPendiente.getCuotaPendiente() > 0)
				descuentoPendiente.setCuotaCobrada(descuentoPendiente.getCuotaPendiente()-1);
			if (descuentoPendiente.getNumeroCuotasBienestar() > descuentoPendiente.getNumeroCuotasConvenio())
				descuentoPendiente.setCuotas(descuentoPendiente.getNumeroCuotasBienestar());
			else
				descuentoPendiente.setCuotas(descuentoPendiente.getNumeroCuotasConvenio());
			if (descuentoPendiente.getCuotaCobrada() > 0) {
				GregorianCalendar fecha = new GregorianCalendar();
				fecha.setTime(descuentoPendiente.getFechaCobroCuotaPendiente());
				fecha.add(Calendar.MONTH,-1);
				descuentoPendiente.setFechaUltimoCobro(fecha.getTime());
			}
			listaDescuentos.add(descuentoPendiente);
		}
		
		// Recupera desde el sistema de Bonificaciones la informacion de
		// los Reembolsos del Socio
		ParamOperacionesVO param = new ParamOperacionesVO();
		param.setFechaFin(new Date()); 
		ArrayList listaReembolsos=bonificacionesDao.getCasosPorReembolsar(param,rut);

		//Carga la información recopilada en la variable de retorno
		informe.setSocio(socio);
		informe.setCargas(listaCargas);
		informe.setVales(listaVales);
		informe.setPrestamos(listaPrestamos);
		informe.setDescuentos(listaDescuentos);
		informe.setReembolsos(listaReembolsos);
		
		return informe;
	}
	
	public List getSociosInactivosConCasosAbiertos() throws BusinessException,
	Exception{
		return bonificacionesDao.getSociosInactivosConCasosAbiertos();
	}
}
