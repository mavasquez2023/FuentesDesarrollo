

/*
 * @(#) ApplicationResources.java    1.0 23-06-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdfservice.ondemand;


import java.util.HashMap;
import java.util.Map;

import javax.mail.Session;

import javax.sql.DataSource;

import cl.araucana.core.util.Queue;


/**
 * ...
 *
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 23-06-2009 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author  Germán Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class ApplicationResources {

	private static final ApplicationResources instance =
			new ApplicationResources();

	private Map dataSources = new HashMap();
	private Map mailSessions = new HashMap();
	private Map queues = new HashMap();
	private Map objects = new HashMap();

	private ApplicationResources() {
	}

	public static ApplicationResources getInstance() {
		return instance;
	}

	public void addDataSource(String name, DataSource dataSource) {
		dataSources.put(name, dataSource);
	}

	public DataSource getDataSource(String name) {
		return (DataSource) dataSources.get(name);
	}

	public void removeDataSource(String name) {
		dataSources.remove(name);
	}

	public void addMailSession(String name, Session session) {
		mailSessions.put(name, session);
	}

	public Session getMailSession(String name) {
		return (Session) mailSessions.get(name);
	}

	public void removeMailSession(String name) {
		mailSessions.remove(name);
	}

	public void addQueue(String name, Queue queue) {
		queues.put(name, queue);
	}

	public Queue getQueue(String name) {
		return (Queue) queues.get(name);
	}

	public void removeQueue(String name) {
		queues.remove(name);
	}
	
	public void addObject(String name, Object object) {
		objects.put(name, object);
	}

	public Object getObject(String name) {
		return objects.get(name);
	}

	public void removeObject(String name) {
		objects.remove(name);
	}	
}
