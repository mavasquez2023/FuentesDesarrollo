package cl.araucana.wslme.business.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.wslme.business.service.EmpleadorService;
import cl.araucana.wslme.common.exception.WSLMEException;
import cl.araucana.wslme.common.util.ConfigUtil;
import cl.araucana.wslme.common.util.FechaUtil;
import cl.araucana.wslme.integration.dao.EmpleadorDao;
import cl.araucana.wslme.integration.dao.impl.EmpleadorDaoImpl;

public class EmpleadorServiceImpl implements EmpleadorService{
	
	private Logger log = Logger.getLogger(EmpleadorServiceImpl.class);
	
	/*
	 * @autor clillo (J-Factory)
	 * @param Integer, rut del afiliado
	 * @return List, lista de empleadores activos para ese rut 
	 */
	public List getEmpleadoresByRutAfiliado(Integer rut) throws WSLMEException{
		/*boolean error=false;
		// valida si debe filtrar por fecha de ultima cotizacion
		log.debug("Valida si debe filtrar por fecha de ultima cotizacion.");
		Date fecIni = null;
		Date fecFin = null;
		String valFecUltCot = ConfigUtil.getValorConfiguracionesDeNegocio("araucana.wslme.business.validafecultcot");
		Boolean validaFechas = new Boolean(valFecUltCot);
		if(validaFechas.booleanValue()){
			log.debug("Calculando rango de fechas para filtrar de ultima cotizacion.");
			String meses = ConfigUtil.getValorConfiguracionesDeNegocio("araucana.wslme.business.rangomeses");
			Integer cantidadMeses = new Integer(Integer.parseInt(meses));
			fecIni = FechaUtil.restarMeses(new Date(), cantidadMeses);
			fecFin = new Date();
		}*/
		log.info("Rut a consultar:" + rut.intValue());
		
		EmpleadorDao empleadorDao = new EmpleadorDaoImpl();
		List listaEmpleadores= null;
		
		log.debug("Prioridad consultas: " + ConfigUtil.getValorRecursosDeAplicacion("prioridad.ejecucion"));
		String[] comandos = ConfigUtil.getValorRecursosDeAplicacion("prioridad.ejecucion").split(";");
		
		for(int i=0; i< comandos.length; i++){
			if(comandos[i]!= null &&comandos[i].length()>1){
				//Comando es válido
				if(comandos[i].equals("SQL")){
					log.info("Consultando SQLServer...");
					listaEmpleadores = consultaSQL(empleadorDao, rut);
				}
				else if(comandos[i].equals("DB2")){
					log.info("Consultando DB2...");
					listaEmpleadores = consultaDB2(empleadorDao, rut);
				}
				else if(comandos[i].equals("CRM")){
					log.info("Consultando CRM...");
					listaEmpleadores = consultaCRM(empleadorDao, rut);
				}
				if(listaEmpleadores != null){
					break;
				}
			}
		}
		
		return listaEmpleadores;



	}
	
	//**********Consulta a SP DB2
	private List consultaDB2(EmpleadorDao empleadorDao, Integer rut){
		List listaEmpleadores=null;
		
		Date fechas[]= getFechas();
		
		try {
			listaEmpleadores = empleadorDao.getEmpleadoresAS400(rut, fechas[0], fechas[1]);
			if(listaEmpleadores==null){
				log.warn("Expiró tiempo para invocar al SP WSLME.GETEMP(?, ?, ?, ?)");
			}
		} catch (Exception e) {
			log.error("Codigo 0121: Ocurrio un problema al llamar al SP WSLME.GETEMP(?, ?, ?, ?)");
			e.printStackTrace();
		}
		return listaEmpleadores;
	}
	
	//**********Consulta a SQL Server
	private List consultaSQL(EmpleadorDao empleadorDao, Integer rut){
		List listaEmpleadores=null;
		try {
			Date fechas[]= getFechas();
			listaEmpleadores= empleadorDao.getEmpleadoresSQLServer(rut, fechas[0], fechas[1]);
			if(listaEmpleadores==null){
				log.warn("Expiró tiempo para invocar consulta SQL Server");
			}
		} catch (Exception e) {
			log.error("Ocurrio un problema al llamar SQL Server");
			e.printStackTrace();
		}
		return listaEmpleadores;
	}
	
	private List consultaCRM(EmpleadorDao empleadorDao, Integer rut){
		List listaEmpleadores=null;
		try {
			log.info("Invocando conexión a CRM");
			String endpoint= ConfigUtil.getValorRecursosDeAplicacion("crm.wslme.endpoint");
			String username= ConfigUtil.getValorRecursosDeAplicacion("crm.wslme.username");
			String password= ConfigUtil.getValorRecursosDeAplicacion("crm.wslme.password");
			listaEmpleadores = empleadorDao.getEmpleadoresCRM(rut, endpoint, username, password);
			if(listaEmpleadores==null){
				log.warn("Expiró tiempo para invocar consulta a CRM");
			}
		} catch (Exception e) {
			log.error("Ocurrio un problema al llamar CRM");
			e.printStackTrace();
		}
		return listaEmpleadores;
	}
	
	private Date[] getFechas(){
		log.debug("Calculando rango de fechas para filtrar de ultima cotizacion.");
		String meses = ConfigUtil.getValorConfiguracionesDeNegocio("araucana.wslme.business.rangomeses");
		Integer cantidadMeses = new Integer(Integer.parseInt(meses));
		Date fecIni = FechaUtil.restarMeses(new Date(), cantidadMeses);
		Date fecFin = new Date();
		Date fechas[]= {fecIni, fecFin};
		return fechas;
		
	}
}