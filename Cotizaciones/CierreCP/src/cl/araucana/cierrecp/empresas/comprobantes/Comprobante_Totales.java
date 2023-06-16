

/*
 * @(#) Entidades.java    1.0 21-07-2009
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.cierrecp.empresas.comprobantes;

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
 *            <TD> 21-07-2009 </TD>
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
public class Comprobante_Totales {
	private int tipo_seccion;
	private String nombreSeccion;
	private int n_trabajadores;
	private long mtotal;
	private int tipoDeclaracionPago;
	private Comprobante_Encabezado encabezado;
	/**
	 * @return el encabezado
	 */
	public Comprobante_Encabezado getEncabezado() {
		return encabezado;
	}
	/**
	 * @param encabezado el encabezado a establecer
	 */
	public void setEncabezado(Comprobante_Encabezado encabezado) {
		this.encabezado = encabezado;
	}
	/**
	 * @return el mtotal
	 */
	public long getMtotal() {
		return mtotal;
	}
	/**
	 * @param mtotal el mtotal a establecer
	 */
	public void setMtotal(long mtotal) {
		this.mtotal = mtotal;
	}
	/**
	 * @return el n_trabajadores
	 */
	public int getN_trabajadores() {
		return n_trabajadores;
	}
	/**
	 * @param n_trabajadores el n_trabajadores a establecer
	 */
	public void setN_trabajadores(int n_trabajadores) {
		this.n_trabajadores = n_trabajadores;
	}
	/**
	 * @return el nombreSeccion
	 */
	public String getNombreSeccion() {
		return nombreSeccion;
	}
	/**
	 * @param nombreSeccion el nombreSeccion a establecer
	 */
	public void setNombreSeccion(String nombreSeccion) {
		this.nombreSeccion = nombreSeccion;
	}
	/**
	 * @return el tipo_seccion
	 */
	public int getTipo_seccion() {
		return tipo_seccion;
	}
	/**
	 * @param tipo_seccion el tipo_seccion a establecer
	 */
	public void setTipo_seccion(int tipo_seccion) {
		this.tipo_seccion = tipo_seccion;
	}
	/**
	 * @return el tipoDeclaracionPago
	 */
	public int getTipoDeclaracionPago() {
		return tipoDeclaracionPago;
	}
	/**
	 * @param tipoDeclaracionPago el tipoDeclaracionPago a establecer
	 */
	public void setTipoDeclaracionPago(int tipoDeclaracionPago) {
		this.tipoDeclaracionPago = tipoDeclaracionPago;
	}
		
}

