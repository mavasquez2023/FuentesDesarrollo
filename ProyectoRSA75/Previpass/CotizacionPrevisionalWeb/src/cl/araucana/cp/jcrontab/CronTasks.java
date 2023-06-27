package cl.araucana.cp.jcrontab;

import java.io.File;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import cl.araucana.core.integration.DAO.DAOException;
import cl.araucana.core.util.Directory;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.dao.ComprobantePagoDAO;
/*
* @(#) Crontasks.java 1.3 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
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
	 * @throws ServiceException 
	 */
	public static void main(String[] args) throws SQLException, NamingException, ServiceException 
	{
		try
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
		}
	}
}
