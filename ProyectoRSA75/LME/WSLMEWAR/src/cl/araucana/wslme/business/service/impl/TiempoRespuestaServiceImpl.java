package cl.araucana.wslme.business.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.wslme.business.service.TiempoRespuestaService;
import cl.araucana.wslme.business.to.TiempoRespuesta;
import cl.araucana.wslme.common.exception.WSLMEException;
import cl.araucana.wslme.common.util.ConfigUtil;
import cl.araucana.wslme.integration.dao.ReporteDao;
import cl.araucana.wslme.integration.dao.TiempoRespuestaDao;
import cl.araucana.wslme.integration.dao.impl.ReporteDaoImpl;
import cl.araucana.wslme.integration.dao.impl.TiempoRespuestaDaoImpl;

public class TiempoRespuestaServiceImpl implements TiempoRespuestaService{
	
	private Logger log = Logger.getLogger(TiempoRespuestaServiceImpl.class);
	private SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
	
	private File repTiempResp;
	
	public File generaReporteTiemposRespuesta(Integer anoMes) throws WSLMEException{
		
		log.debug("Abriendo la conexion al servidor de base de datos");
		TiempoRespuestaDao tiempRespDao = new TiempoRespuestaDaoImpl();
		
		log.debug("Obteniendo los tiempos de respuesta para generar el reporte");
		List listaTiempResp = tiempRespDao.getTiemposRespuesta(anoMes);
		
		log.debug("Generando el contenido del reporte");
		List contenido = new ArrayList();
		contenido.add("Dia;Respuesta en Segundos;Solicitudes");
		
		List listAux = new ArrayList();
		String fechAux = null; 
		for(Iterator it = listaTiempResp.iterator(); it.hasNext(); ){
			TiempoRespuesta objTiempResp = (TiempoRespuesta) it.next();
			
			if(fechAux == null){
				listAux.add(sdf1.format(objTiempResp.getFecha()));
				fechAux = sdf1.format(objTiempResp.getFecha());
			}else{
				if(!fechAux.equals(sdf1.format(objTiempResp.getFecha()))){
					listAux.add(sdf1.format(objTiempResp.getFecha()));
					fechAux = sdf1.format(objTiempResp.getFecha());
				}
			}
		}
		
		int cantMaxPorDia = Integer.parseInt(ConfigUtil.getValorConfiguracionesDeNegocio("araucana.wslme.business.reportes.tiemposrespuesta.cantmaxpordia"));
		for(Iterator it = listAux.iterator(); it.hasNext(); ){
			String fecTemp = (String)it.next();
			
			for(int i = 0; i <= cantMaxPorDia; i++){
				String line = fecTemp + ";";
				line += i + ";";
				
				String canSolTemp = "0";
				for(Iterator it2 = listaTiempResp.iterator(); it2.hasNext(); ){
					TiempoRespuesta objTiempResp = (TiempoRespuesta) it2.next();
					if(sdf1.format(objTiempResp.getFecha()).equals(fecTemp) && objTiempResp.getSegundos().intValue() == i){
						canSolTemp = objTiempResp.getCantSol() + "";
						break;
					}
				}
				line += canSolTemp;
				contenido.add(line);
				
			}
			
			String line = fecTemp + ";";
			line += ">" + cantMaxPorDia + ";";
			int sum = 0;
			for(Iterator it2 = listaTiempResp.iterator(); it2.hasNext(); ){
				TiempoRespuesta objTiempResp = (TiempoRespuesta) it2.next();
				if(sdf1.format(objTiempResp.getFecha()).equals(fecTemp) && objTiempResp.getSegundos().intValue() > cantMaxPorDia){
					sum += objTiempResp.getCantSol().intValue();
				}
			}
			line += sum;
			contenido.add(line);
			
		}
		
		String nombreReporte = "Tiempos.Ejecucion." + anoMes + "." + sdf2.format(new Date());
		log.debug("Creando el archivo de reporte [" + nombreReporte + "]");
		ReporteDao repDao = new ReporteDaoImpl(nombreReporte, true);
		
		log.debug("Guardando el contenido en el archivo de reporte [" + nombreReporte + "]");
		repTiempResp = repDao.guardarReporte(contenido);
		return repTiempResp;
	}
}
