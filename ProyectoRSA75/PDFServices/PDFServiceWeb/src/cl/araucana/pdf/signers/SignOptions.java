

/*
 * @(#) SignOptions.java    1.0 20-06-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.signers;


import java.io.Serializable;


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
 *            <TD> 20-06-2008 </TD>
 *            <TD align="center">  2.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versión inicial. </TD>
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
public class SignOptions implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4176168459371821920L;
	
	private Signer signer;
	private String logID;

	public SignOptions() {
	}

	public void setSigner(Signer signer) {
		this.signer = signer;
	}

	public Signer getSigner() {
		return signer;
	}

	public void setLogID(String id) {
		logID = id;
	}

	public String getLogID() {
		return logID;
	}

	public String toString() {
		return "signer=" + signer.getName() + " logID=" + logID;
	}
}
