

/*
 * @(#) EntidadesFolios.java    1.0 24-07-2009
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.cierrecpe.empresas.planillas;

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
 *            <TD align="center"> <B>Versi�n</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripci�n</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 24-07-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> CLAUDIO LILLO AZOR�N <BR> clillo@laaraucana.cl </TD>
 *            <TD> Versi�n inicial. </TD>
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
 * @author CLAUDIO LILLO AZOR�N (clillo@laaraucana.cl)
 *
 * @version 1.0
 */
public class EntidadFolio {
	private String nombreEntidad;
	private Rut rutEntidad;
	private int folioActual;
	private int rangoFinal;
	private int folioFinal;
	private int deltaRango;
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
	 * @return el nombreEntidad
	 */
	public String getNombreEntidad() {
		return nombreEntidad;
	}
	/**
	 * @param nombreEntidad el nombreEntidad a establecer
	 */
	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}
	/**
	 * @return el rutEntidad
	 */
	public Rut getRutEntidad() {
		return rutEntidad;
	}
	/**
	 * @param rutEntidad el rutEntidad a establecer
	 */
	public void setRutEntidad(Rut rutEntidad) {
		this.rutEntidad = rutEntidad;
	}
	/**
	 * @return el rangoFinal
	 */
	public int getRangoFinal() {
		return rangoFinal;
	}
	/**
	 * @param rangoFinal el rangoFinal a establecer
	 */
	public void setRangoFinal(int rangoFinal) {
		this.rangoFinal = rangoFinal;
	}
	/**
	 * @return el deltaRango
	 */
	public int getDeltaRango() {
		return deltaRango;
	}
	/**
	 * @param deltaRango el deltaRango a establecer
	 */
	public void setDeltaRango(int deltaRango) {
		this.deltaRango = deltaRango;
	}
}

