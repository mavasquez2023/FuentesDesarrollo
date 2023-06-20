package cl.laaraucana.rendicionpagonomina.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cl.laaraucana.rendicionpagonomina.entities.CabeceraEntity;
import cl.laaraucana.rendicionpagonomina.entities.DetalleEntity;
import cl.laaraucana.rendicionpagonomina.exception.ErrorMessage;
import cl.laaraucana.rendicionpagonomina.exception.MiException;
import cl.laaraucana.rendicionpagonomina.ftp.exceptions.FTPErrors;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.CommonLicenciaDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.CommonLicenciaDaoImpl;
import cl.laaraucana.rendicionpagonomina.utils.FileManagmentUtils;
import cl.laaraucana.rendicionpagonomina.vo.FTPConfigurationVO;

@Service
public class ProcesaRendicionesBCIImpl implements ProcesaRendicionesBCI{


	private static final Logger logger = Logger.getLogger(ProcesaRendicionesBCIImpl.class);


	@Autowired
	ProcesaRendicionNominas24HService procesaRendicionNominas24HService;
	
	@Autowired
	ProcesaRendicionNominasValeVistaService procesaRendicionNominasValeVistaService;
	
	@Autowired
	ProcesaRendicionNominasPNOLService procesaRendicionNominasPNOLService;
	
	ArrayList<HashMap<String, Object>> execs = new ArrayList<HashMap<String,Object>>();
	
	
	@Override
	public int execute() throws MiException {
		logger.debug("Procesando licencias BCI ");
		String mensajeSalida = "";
		int resultadoOperacion = 0;
		logger.debug("ProcesaLicenciasBCIImpl iniciando el proceso rendicion24H ");
		try {
			resultadoOperacion = procesaRendicionNominas24HService.loadData();
		} catch (MiException e) {
			mensajeSalida = e.getMessage();
			logger.info(mensajeSalida);
		}
		logger.debug("ProcesaLicenciasBCIImpl despachando resultado de la operacion 24H:["+resultadoOperacion+"]");
		
		logger.debug("ProcesaLicenciasBCIImpl iniciando el proceso rendicionVV ");
		try {
			resultadoOperacion = procesaRendicionNominasValeVistaService.loadData();
		} catch (MiException e) {
			mensajeSalida = e.getMessage();
			logger.info(mensajeSalida);
		}
		logger.debug("ProcesaLicenciasBCIImpl resultado de la operacion VV:["+resultadoOperacion+"]");
		
		logger.debug("ProcesaLicenciasBCIImpl iniciando el proceso rendicionPNOL ");
		try {
			resultadoOperacion = procesaRendicionNominasPNOLService.loadData();
		} catch (MiException e) {
			mensajeSalida = e.getMessage();
			logger.info(mensajeSalida);
		}
		logger.debug("ProcesaLicenciasBCIImpl despachando resultado de la operacion PNOL:["+resultadoOperacion+"]");
		
		return 0;
	}

	@Override
	public ArrayList<HashMap<String, Object>> getAvailablesFiles() throws MiException {
		ArrayList<HashMap<String, Object>> nominas = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> rendicion24H = procesaRendicionNominas24HService.getAvailablesFiles();
		ArrayList<HashMap<String, Object>> rendicionValeVista = procesaRendicionNominasValeVistaService.getAvailablesFiles();
		ArrayList<HashMap<String, Object>> rendicionPNOL = procesaRendicionNominasPNOLService.getAvailablesFiles();
		
		for (HashMap<String, Object> file : rendicion24H) {
			boolean exist = false;
			HashMap<String, Object> item = null;
			for (HashMap<String, Object> exec : execs) {
				if(file.get("file").equals(exec.get("file"))) {
					exist = true;
				}
			}
			if(!exist) {
				item = file;
			}
			nominas.add(item);
		}
		
		for (HashMap<String, Object> file : rendicionValeVista) {
			boolean exist = false;
			HashMap<String, Object> item = null;
			for (HashMap<String, Object> exec : execs) {
				if(file.get("file").equals(exec.get("file"))) {
					exist = true;
				}
			}
			if(!exist) {
				item = file;
			}
			nominas.add(item);
		}
		
		for (HashMap<String, Object> file : rendicionPNOL) {
			boolean exist = false;
			HashMap<String, Object> item = null;
			for (HashMap<String, Object> exec : execs) {
				if(file.get("file").equals(exec.get("file"))) {
					exist = true;
				}
			}
			if(!exist) {
				item = file;
			}
			nominas.add(item);
		}

		nominas.addAll(execs);		
		
		return nominas;
	}

	@Override
	public int executeByFiles(ArrayList<HashMap<String, Object>> files) throws MiException {
		for (HashMap<String, Object> file : files) {
			boolean exist = false;
			for (HashMap<String, Object> exec : execs) {
				if(exec.get(file.get("file"))!=null) {
					exist = true;
				}
			}
			if(!exist) {
				if("24H".equals(file.get("type"))){
					file.put("beginDate", new Date().getTime());
					file.put("status", "iniciado");
					file.put("enabled", false);
					int result = -1;
					try {
						result = procesaRendicionNominas24HService.loadDataFromFile(file.get("file").toString());
					}catch (Exception e) {
						
					}
					file.put("endDate", new Date().getTime());
					if(result == 1) {
						file.put("status", "Terminado Correctamente");
					}
					else if(result == -1) {
						file.put("status", "Terminado con Error");
					}
					execs.add(file);
				}
				else if("Vale Vista".equals(file.get("type"))){
					file.put("beginDate", new Date().getTime());
					file.put("status", "iniciado");
					file.put("enabled", false);
					int result = -1;
					try {
						result = procesaRendicionNominasValeVistaService.loadDataFromFile(file.get("file").toString());
					}catch (Exception e) {
						
					}
					file.put("endDate", new Date().getTime());
					if(result == 1) {
						file.put("status", "Terminado Correctamente");
					}
					else if(result == -1) {
						file.put("status", "Terminado con Error");
					}
					execs.add(file);
				}else if("PNOL".equals(file.get("type"))){
					file.put("beginDate", new Date().getTime());
					file.put("status", "iniciado");
					file.put("enabled", false);
					int result = -1;
					try {
						result = procesaRendicionNominasPNOLService.loadDataFromFile(file.get("file").toString());
					}catch (Exception e) {
						
					}
					file.put("endDate", new Date().getTime());
					if(result == 1) {
						file.put("status", "Terminado Correctamente");
					}
					else if(result == -1) {
						file.put("status", "Terminado con Error");
					}
					execs.add(file);
				}
			}
			
		}
		return 0;
	}

		
}
