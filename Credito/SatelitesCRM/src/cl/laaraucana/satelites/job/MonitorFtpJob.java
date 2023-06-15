package cl.laaraucana.satelites.job;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cl.laaraucana.satelites.Utils.ftp.Archivo;
import cl.laaraucana.satelites.Utils.ftp.FtpUtil;



/**
 * @author microsystem
 *
 */
public class MonitorFtpJob implements Job {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		try {
			log.debug("Inicio del proceso de eliminación de respaldo de PDFs obsoletos");
			FtpUtil ftpU = new FtpUtil();
			List<Archivo> archivos = ftpU.listarArchivos("PDF", "");
			int cont = 0;
			// Eliminar todos los archivos con antiguedad mayor a 90 dias
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_YEAR, -90);
			System.out.println("Existe " + archivos.size() + " archivos PDF en el servidor Ftp");
			for (Archivo archivo : archivos) {
				if (archivo.getFechaModificacion().before(cal.getTime())) {
					ftpU.eliminarArchivo(archivo.getNombre(), null);
					log.debug("archivo " + archivo.getNombre() + " eliminado" + ", Fecha: " + archivo.getFechaModificacion());
					cont++;
				}
			}
			log.debug("Total " + cont + " archivo(s) eliminado(s)");
			ftpU.desconectar();
		} catch (Throwable e) {
			log.error(e.getClass() + "; " + e.getMessage());
		}
	}
}
