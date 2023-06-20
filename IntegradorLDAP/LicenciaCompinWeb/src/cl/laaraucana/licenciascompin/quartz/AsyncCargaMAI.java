package cl.laaraucana.licenciascompin.quartz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import cl.laaraucana.licenciascompin.dao.GenericDao;
import cl.laaraucana.licenciascompin.entities.Region;
import cl.laaraucana.licenciascompin.entities.RegistroDocPendientes;
import cl.laaraucana.licenciascompin.entities.RegistroLicencias;
import cl.laaraucana.licenciascompin.services.ArchivoCargaMasiva;
import cl.laaraucana.licenciascompin.services.FTPService;
import cl.laaraucana.licenciascompin.util.Configuraciones;

public class AsyncCargaMAI {
	private static final Logger logger = Logger.getLogger(AsyncCargaMAI.class);


	@Autowired
	ArchivoCargaMasiva archivoCarga;
	
	@Autowired
	FTPService ftp;
	
	@Autowired
	private GenericDao<RegistroDocPendientes> daopend;
	
	@Autowired
	private GenericDao<RegistroLicencias> daonew;
	
	@Scheduled(cron = "${config.cron.quartz.carga}")
	public void asyncProcess() throws Exception {

		logger.info("Iniciando el proceso automatizado para cargaMAI ");

		try {
			String host= Configuraciones.getConfig("hostFTP");
			int port= Integer.parseInt(Configuraciones.getConfig("portFTP"));
			String user= Configuraciones.getConfig("usuarioFTP");
			String pass= Configuraciones.getConfig("claveFTP");
			String directory= Configuraciones.getConfig("FTPCarpeta");
			
			//Se busca licencias PENDIENTES registradas en SQLServer
			
			List<RegistroDocPendientes> listaPendientes= archivoCarga.leerRegistrosPendientes();
			logger.info("Total registros PENDIENTES: " + listaPendientes.size());
			
			if(listaPendientes.size()>0){
				//Se genera Archivo de carga Mai
				String ruta_archivo= archivoCarga.generarArchivoPendientes(listaPendientes);
				logger.info("Archivo generado: " + ruta_archivo);

				if(ruta_archivo != null){
					String archivoFTP= ruta_archivo.substring(ruta_archivo.lastIndexOf("\\")+1);
					//Se envía por FTP
					logger.info("Conectando a FTP: " + host);
					ftp.connectToFTP(host, port, user, pass);
					ftp.uploadFileToFTP(ruta_archivo, directory, archivoFTP);

					//Se actualiza registros en base de datos;
					logger.info("Actualizando estado de registros");
					List<Long> ids= new ArrayList<Long>();
					for (Iterator iterator = listaPendientes.iterator(); iterator
							.hasNext();) {
						RegistroDocPendientes registroDocPendientes = (RegistroDocPendientes) iterator
								.next();
						ids.add(registroDocPendientes.getId());
					}
					daopend.UpdateByEstado(RegistroDocPendientes.class, ids);
				}
			}
			
			//Se busca licencias NUEVAS registradas en SQLServer
			List<RegistroLicencias> listaNuevos= archivoCarga.leerRegistrosNuevos();
			logger.info("Total registros NUEVOS: " + listaPendientes.size());
			
			if(listaNuevos.size()>0){
				//Se genera Archivo de carga Mai
				String ruta_archivo_nuevo= archivoCarga.generarArchivoNuevos(listaNuevos);
				logger.info("Archivo generado: " + ruta_archivo_nuevo);
				
				if(ruta_archivo_nuevo != null){
					String archivoFTP= ruta_archivo_nuevo.substring(ruta_archivo_nuevo.lastIndexOf("\\")+1);
					//Se envía por FTP
					logger.info("Conectando a FTP: " + host);
					ftp.connectToFTP(host, port, user, pass);
					ftp.uploadFileToFTP(ruta_archivo_nuevo, directory, archivoFTP);

					//Se actualiza registros en base de datos;
					logger.info("Actualizando estado de registros");
					List<Long> ids= new ArrayList<Long>();
					for (Iterator iterator = listaNuevos.iterator(); iterator
							.hasNext();) {
						RegistroLicencias registroDocNuevos = (RegistroLicencias) iterator
								.next();
						ids.add(registroDocNuevos.getId());
					}
					daonew.UpdateByEstado(RegistroLicencias.class, ids);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Proceso cronta finalizado");
	}
}
