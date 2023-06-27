

/*
 * @(#) PublishedPeriodsBean.java    1.0 30-11-2006
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
package cl.araucana.ea.edocs;


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
 *            <TD> 30-11-2006 </TD>
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
public class PublishedPeriodsBean {

	private int[] publishedPeriods;
	private int selectedPeriodIndex;
	
	public PublishedPeriodsBean() {
	}
	
	public PublishedPeriodsBean(int[] publishedPeriods,
			int selectedPeriodIndex) {
		
		if (selectedPeriodIndex < 0
				|| selectedPeriodIndex >= publishedPeriods.length) {
					
			throw new IndexOutOfBoundsException(
					"Selected period index '" + selectedPeriodIndex + "'.");
		}
		
		this.publishedPeriods = publishedPeriods;
		this.selectedPeriodIndex = selectedPeriodIndex;
	}
	
	public int getPublishedPeriodsCount() {
		return publishedPeriods.length;
	}

	public int getSelectedPeriod() {
		return publishedPeriods[selectedPeriodIndex];
	}

	public String getSelectedPeriodToString() {
		return periodToString(getSelectedPeriod());
	}
	
	public int getSelectedPeriodIndex() {
		return selectedPeriodIndex;
	}

	public int getFirstPeriod() {
		return publishedPeriods[0];
	}

	public int getSecondPeriod() {
		return publishedPeriods[1];
	}
	
	public String getFirstExpirationDate() {
		int period = DocumentController.getNextPeriod(getFirstPeriod());
		String s = periodToString(period);
		
		return "10/" + s;
	}

	public String getSecondExpirationDate() {
		int period = DocumentController.getNextPeriod(getSecondPeriod());
		String s = periodToString(period);
		
		return "10/" + s;
	}
	
	public String getFirstPeriodToString() {
		return periodToString(getFirstPeriod());
	}

	public String getSecondPeriodToString() {
		return periodToString(getSecondPeriod());
	}
	
	private String periodToString(int period) {
		return DocumentController.periodToString(period);
	}
}
