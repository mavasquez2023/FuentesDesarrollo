package cl.araucana.cp.jcrontab;

import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import cl.araucana.core.integration.DAO.DAOException;
/*
* @(#) Crontasks.java 1.3 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cllanos
 * @author cchamblas
 * 
 * @version 1.3
 */
public class CronTasks 
{
	private static Logger log = Logger.getLogger(CronTasks.class);
	/**
	 * @param args
	 * @throws SQLException 
	 * @throws DAOException 
	 * @throws NamingException 
	 */
	public static void main(String[] args) throws SQLException, NamingException 
	{
	//Se ejecuta solo el CronTasks de empresa que incluye para independiente GMALLEA 31-05-2013
		/*try
		{
			log.info("\n\n\nborrando cmps en disco:" + Constants.RUTA_CMPS + "::");
			Directory carpetaCmps = new Directory(Constants.RUTA_CMPS);
			File[] cmpsBorrar = carpetaCmps.getEntries();
			log.info("numero archivos a borrar a borrar:" + cmpsBorrar.length + "::");
			for (int i = 0; i < cmpsBorrar.length; i++)
				cmpsBorrar[i].delete();
			log.info("fin borrado cmps en disco");
		} catch (Exception e)
		{
			log.error("problemas en main:", e);
		}
		ComprobantePagoDAO cmp = new ComprobantePagoDAO();
		try 
		{
			cmp.cambiaEstados();
		} catch (DaoException e) 
		{
			log.error("problemas en main:", e);
		}*/
	}
}
