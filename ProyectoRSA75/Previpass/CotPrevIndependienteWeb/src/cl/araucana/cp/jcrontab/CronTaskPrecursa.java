package cl.araucana.cp.jcrontab;

import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

public class CronTaskPrecursa {
	private static Logger log = Logger.getLogger(CronTaskPrecursa.class);

	public static void main(String[] args) throws SQLException, NamingException {
		
		//Se ejecuta solo el CronTasks de empresa que incluye para independiente GMALLEA 31-05-2013
		/*try	{
			log.info("\n\n\nborrando cmps en disco:" + Constants.RUTA_CMPS + "::");
			Directory carpetaCmps = new Directory(Constants.RUTA_CMPS);
			File[] cmpsBorrar = carpetaCmps.getEntries();
			log.info("numero archivos a borrar a borrar:" + cmpsBorrar.length + "::");
			for (int i = 0; i < cmpsBorrar.length; i++)
				cmpsBorrar[i].delete();
			log.info("fin borrado cmps en disco");
		} catch (Exception e) {
			log.error("problemas en main:", e);
		}

		ComprobantePagoDAO cmp = new ComprobantePagoDAO();
		try {
			cmp.precursarComprobantes();
		} catch (DaoException e) {
			log.error("problemas en main:", e);
		}*/
	}
}