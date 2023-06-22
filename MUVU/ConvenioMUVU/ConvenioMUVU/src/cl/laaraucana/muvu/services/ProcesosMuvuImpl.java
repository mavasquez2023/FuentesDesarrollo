/**
 * 
 */
package cl.laaraucana.muvu.services;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.laaraucana.muvu.entities.Resumen;
import cl.laaraucana.muvu.util.Configuraciones;
import cl.laaraucana.muvu.util.Utils;
import cl.laaraucana.muvu.util.UtilsFecha;
import cl.laaraucana.muvu.util.UtilsFile;
import cl.laaraucana.muvu.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.muvu.ws.ClienteInfoAfiliado;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;

/**
 * @author IBM Software Factory
 *
 */
@Service
public class ProcesosMuvuImpl implements ProcesosMuvu {
	
	private static final Logger logger = Logger.getLogger(ProcesosMuvuImpl.class);
	
	@Autowired
	private SFTPService sFTPService;
	
	@Autowired
	private ResumenService resumenService;
	
	@Autowired
	private ReporteService reporteService;
	
	@Autowired
	private MailService mailService;
	
	/* (non-Javadoc)
	 * @see cl.laaraucana.muvu.services.ProcesosMuvu#procesarAltas()
	 */
	@Override
	public boolean procesarAltas(String periodo) throws Exception {
		logger.info("Iniciando el proceso para altas Muvu ");
		try {
			String host= Configuraciones.getConfig("hostFTP");
			int port= Integer.parseInt(Configuraciones.getConfig("portFTP"));
			String username= Configuraciones.getConfig("usuarioFTP");
			String password= Configuraciones.getConfig("claveFTP");
			String directory= Configuraciones.getConfig("FTPCarpeta");
			//formato archivo altas: ALTAS_MUVU_AAAAMMDD.txt
			String downloadFile= Configuraciones.getConfig("FTPArchivo");
			if(periodo==null){
				downloadFile= downloadFile.replaceAll("AAAAMMDD", UtilsFecha.getFechaAyerAs400());
			}else{
				downloadFile= downloadFile.replaceAll("AAAAMMDD", periodo);
			}
			String saveFile= Configuraciones.getConfig("carpeta.altas");
			
			//Se leen los archivos de SFTP
			logger.info("Conectando a host: " + host + ":" + port + ", con usuario:" + username);
			ChannelSftp sftp= sFTPService.connect(host, port, username, password);
			logger.info("is channel connected= "+ sftp.isConnected());
			
			if(!sftp.isConnected()){
				
				logger.warn("Saliendo Proceso Altas Muvu, Error al conectar a sitio SFTP");
				return false;
			}
			
			String pathfile =saveFile + downloadFile;
			logger.info("Descargando desde carpeta ftp " + directory + ", archivo " + downloadFile + ", y dejando en ruta:" + pathfile);
			sFTPService.download(directory, downloadFile, pathfile, sftp);
			
			//Se rescatan los archivos validos de Altas
			/*List<String> archivos_validos= new ArrayList<String>();
			for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
				LsEntry entry = (LsEntry) iterator.next();
				System.out.println("descargando archivo:" + entry.getFilename());
				if(entry.getFilename().split("_")[0].equals("ALTAS")){
					sFTPService.download(directory, entry.getFilename(), saveFile + entry.getFilename(), sftp);
					if(!archivos_validos.contains(saveFile + entry.getFilename())){
						archivos_validos.add(saveFile + entry.getFilename());
					}
				}
				
			}*/
			//Se leen registros de archivo
			
			//for (Iterator iterator = archivos_validos.iterator(); iterator.hasNext();) {
				//String pathfile = (String) iterator.next();
				logger.info("Procesando archivo:" + pathfile);
				List<String> listaRegistros= UtilsFile.leerArchivo(pathfile);
				logger.info("Cantidad registros en archivo de altas:" + listaRegistros.size());
				if(listaRegistros.size()==0){
					logger.info("Listando archivos en carpeta FTP: " + directory);
					Vector<String> lista= sFTPService.listFiles(directory, sftp);
					logger.info("Archivos en carpeta FTP:");
					for (Iterator iterator = lista.iterator(); iterator
							.hasNext();) {
						Object fileftp = (Object) iterator.next();
						logger.info(fileftp.toString());
					}
					
				}
				//Se recorre lista de registros y se actualiza fecha de Alta
				for (Iterator iterator2 = listaRegistros.iterator(); iterator2
						.hasNext();) {
					String registro = (String) iterator2.next();
					String[] columnas=registro.split(",");
					int rut= Integer.parseInt(columnas[1]);
					logger.info("Buscando en tabla Resumen, Rut: " + rut);
					Resumen resumen= resumenService.findByRut(rut);
					if(resumen!=null){
						Date fechaAlta=null;
						String email_alta= columnas[0].trim();
						Date fechaInscripcion= UtilsFecha.stringToDateSAP(columnas[3]);
						Date fechaSincronizacion= UtilsFecha.stringToDateSAP(columnas[4]);
						Date fechaEnrolamiento= resumen.getFechaEnrolamiento();
						if(fechaInscripcion.compareTo(fechaSincronizacion)>0){
							if(fechaInscripcion.compareTo(fechaEnrolamiento)>0){
								fechaAlta=fechaInscripcion;
							}else{
								fechaAlta=fechaEnrolamiento;
							}
						}else if(fechaSincronizacion.compareTo(fechaEnrolamiento)>0){
							fechaAlta= fechaSincronizacion;
						}else{
							fechaAlta= fechaEnrolamiento;
						}
						resumen.setFechaAlta(fechaAlta);
						resumen.setFechaActFisica(fechaSincronizacion);
						resumen.setFechaInscripcion(fechaInscripcion);
						resumen.setEstado("Vigente");
						if(!resumen.getEmail().trim().equals(email_alta)){
							logger.info("Actualizando nuevo correo informado por MUVU: " + email_alta + ", anterior: " + resumen.getEmail());
							resumen.setEmail(email_alta);
						}
						
						logger.info("Actualizando Resumen, Rut: " + rut);
						resumenService.updateResumen(resumen);
						
						//Rescatando nombre Afiliado
						//Conectando a CRM
						String rutAfiliado= resumen.getRutAfiliado()+ "-" + resumen.getDvAfiliado();
						logger.info("Recuperando nombre afiliado en CRM para " + rutAfiliado);
						ClienteInfoAfiliado client = new ClienteInfoAfiliado();
						SalidainfoAfiliadoVO salida = (SalidainfoAfiliadoVO) client.getDataAfiliado(rutAfiliado);
						
						//seteando nombre afiliado
						if(salida.getNombreCompleto()!=null){
							resumen.setNombreAfiliado(salida.getNombreCompleto());
						}
						
						//ENVIANDO mail
						logger.info("Generando reporte Poliza Seguro Vida Sana en PDF");
						String rutaPDF = reporteService.generarReport(resumen, Configuraciones.getConfig("muvu.carpeta"));
						logger.info("Enviando contrato altas por correo a " + resumen.getEmail());
						mailService.sendEmailClie(resumen.getEmail(), Configuraciones.getConfig("mail.asunto.cliente"),
								Utils.emailClienteAlta(resumen.getNombreAfiliado()), rutAfiliado, rutaPDF);
					}else{
						logger.warn("Rut " + rut + " no encontrado en tabla Resumen, no procesado");
					}
				}
			//}
			
				logger.info("Fin proceso Altas Muvu ");
				return true;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Error Proceso Altas Muvu ", e);
		}
		return false;
	}

}
