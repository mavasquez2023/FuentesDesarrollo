package cl.araucana.wslme.business.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.wslme.business.service.EstadisticaService;
import cl.araucana.wslme.business.to.Estadistica;
import cl.araucana.wslme.common.exception.WSLMEException;
import cl.araucana.wslme.integration.dao.EstadisticaDao;
import cl.araucana.wslme.integration.dao.ReporteDao;
import cl.araucana.wslme.integration.dao.impl.EstadisticaDaoImpl;
import cl.araucana.wslme.integration.dao.impl.ReporteDaoImpl;

public class EstadisticaServiceImpl implements EstadisticaService{
	
	private Logger log = Logger.getLogger(EstadisticaServiceImpl.class);
	private SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
	
	private File repEst;
	
	public File generaReporteEstadisticas(Integer anoMes) throws WSLMEException{
		
		log.debug("Abriendo la conexion al servidor de base de datos");
		EstadisticaDao estadisticaDao = new EstadisticaDaoImpl();
		
		log.info("Obteniendo las estradisticas para generar el reporte");
		List listaEstadistica = estadisticaDao.getEstadisticas(anoMes);
		
		log.debug("Generando el contenido del reporte");
		List contenido = new ArrayList();
		contenido.add("Dia;Solicitudes Totales;Respuestas OK con pago;Respuestas OK sin pago;Respuestas NOK;Respuestas Totales; Periodo Servicio");
		for(Iterator it = listaEstadistica.iterator(); it.hasNext();){
			Estadistica objEst = (Estadistica) it.next();
			String line = sdf1.format(objEst.getFecha()) + ";";
			line += objEst.getSolTot() + ";";
			line += objEst.getRespOkConPago() + ";";
			line += objEst.getRespOkSinPago() + ";";
			line += objEst.getRespNok() + ";";
			line += objEst.getRespTot() + ";";
			line += objEst.getHoraPrimeraSol().replaceAll("\\.", ":") + " - " + objEst.getHoraUltimaSol().replaceAll("\\.", ":") + " Hrs";
			contenido.add(line);
		}
		
		String nombreReporte = "Estadisticas." + anoMes + "." + sdf2.format(new Date());
		log.info("Creando el archivo de reporte [" + nombreReporte + "]");
		ReporteDao repDao = new ReporteDaoImpl(nombreReporte, true);
		
		//log.debug("Guardando el contenido en el archivo de reporte [" + nombreReporte + "]");
		repEst = repDao.guardarReporte(contenido);
		return repEst;
	}
}