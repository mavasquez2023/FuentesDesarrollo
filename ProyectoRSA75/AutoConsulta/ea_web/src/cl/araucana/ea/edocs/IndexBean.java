
/*
 * @(#) IndexBean.java    1.0 30-10-2006
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.ea.edocs;


import java.io.Serializable;
import java.util.Date;


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
 *            <TD> 30-10-2006 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD>   </TD>
 *            <TD align="center">  </TD>
 *            <TD>   </TD>
 *            <TD>  </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Germán Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class IndexBean implements Serializable {

	private TrackableTask trackable;
	
	public IndexBean(TrackableTask trackable) {
		this.trackable = trackable;
	}

	public IndexBean() {
		throw new UnsupportedOperationException();
	}

	public int getStatus() {
		return trackable.getStatus();
	}

	public String getAbortMessage() {
		return trackable.getAbortMessage();
	}

	public int getWorkUnits() {
		return trackable.getWorkUnits();
	}

	public int getWorkUnitsDone() {
		return trackable.getWorkUnitsDone();
	}

	public int getWorkPercentDone() {
		return (int) trackable.getWorkPercentDone();
	}

	public int getWorkPercentPending() {
		return 100 - getWorkPercentDone();
	}

	public Date getBeginTime() {
		return trackable.getBeginTime();
	}

	public Date getEndTime() {
		return trackable.getEndTime();
	}

	public String getElapsedTime() {
		long elapsedTime = trackable.elapsedTime() / 1000L;
		long hours = elapsedTime / 3600L;
		long minutes = (elapsedTime - 3600L * hours) / 60L;
		long seconds = elapsedTime - 3600L * hours - 60L * minutes;
		
		return lpad(hours, 2) + ":" + lpad(minutes, 2) + ":" + lpad(seconds, 2);
	}
	
	private String lpad(long n, int width) {
		String s = n + "";
		int padLength = width - s.length();
		
		for (int i = 0; i < padLength; i++) {
			s = "0" + s;
		}
		
		return s;
	}
}
