

/*
 * @(#) FoliosEntidadTO.java    1.0 13-03-2012
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.entidades.to;

import cl.araucana.core.util.Rut;


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
 *            <TD> 13-03-2012 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> CLAUDIO LILLO AZORÍN <BR> clillo@laaraucana.cl </TD>
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
 * @author CLAUDIO LILLO AZORÍN (clillo@laaraucana.cl)
 *
 * @version 1.0
 */
public class FoliosEntidadTO {
	private int folioInicial;
	private int folioFinal;
	private int folioActual;
	private Rut RutEntidad;
	/**
	 * @return el folioActual
	 */
	public int getFolioActual() {
		return folioActual;
	}
	/**
	 * @param folioActual el folioActual a establecer
	 */
	public void setFolioActual(int folioActual) {
		this.folioActual = folioActual;
	}
	/**
	 * @return el folioFinal
	 */
	public int getFolioFinal() {
		return folioFinal;
	}
	/**
	 * @param folioFinal el folioFinal a establecer
	 */
	public void setFolioFinal(int folioFinal) {
		this.folioFinal = folioFinal;
	}
	/**
	 * @return el folioInicial
	 */
	public int getFolioInicial() {
		return folioInicial;
	}
	/**
	 * @param folioInicial el folioInicial a establecer
	 */
	public void setFolioInicial(int folioInicial) {
		this.folioInicial = folioInicial;
	}
	/**
	 * @return el rutEntidad
	 */
	public Rut getRutEntidad() {
		return RutEntidad;
	}
	/**
	 * @param rutEntidad el rutEntidad a establecer
	 */
	public void setRutEntidad(Rut rutEntidad) {
		RutEntidad = rutEntidad;
	}

}

