

/*
 * @(#) Entidades.java    1.0 21-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
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
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 21-07-2009 </TD>
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
public class Comprobante_Detalle {
	private int tipo_seccion;
	private int tipo_detalle;
	private int n_trabajadores;
	private long m1;
	private long m2;
	private long m3;
	private long m4;
	private long m5;
	private long m6;
	private long m7;
	private long m8;
	private long m9;
	private long m10;
	private long m11;
	private long m12;
	private Rut rutEntidad;
	private int tipoDeclaracionPago;
	private String nombreSeccion;
	private String nombreEntidad;
	private long mtotal;
	private Comprobante_Encabezado encabezado;
	/**
	 * @return el m1
	 */
	public long getM1() {
		return m1;
	}
	/**
	 * @param m1 el m1 a establecer
	 */
	public void setM1(long m1) {
		this.m1 = m1;
	}
	/**
	 * @return el m10
	 */
	public long getM10() {
		return m10;
	}
	/**
	 * @param m10 el m10 a establecer
	 */
	public void setM10(long m10) {
		this.m10 = m10;
	}
	/**
	 * @return el m11
	 */
	public long getM11() {
		return m11;
	}
	/**
	 * @param m11 el m11 a establecer
	 */
	public void setM11(long m11) {
		this.m11 = m11;
	}
	/**
	 * @return el m12
	 */
	public long getM12() {
		return m12;
	}
	/**
	 * @param m12 el m12 a establecer
	 */
	public void setM12(long m12) {
		this.m12 = m12;
	}
	/**
	 * @return el m2
	 */
	public long getM2() {
		return m2;
	}
	/**
	 * @param m2 el m2 a establecer
	 */
	public void setM2(long m2) {
		this.m2 = m2;
	}
	/**
	 * @return el m3
	 */
	public long getM3() {
		return m3;
	}
	/**
	 * @param m3 el m3 a establecer
	 */
	public void setM3(long m3) {
		this.m3 = m3;
	}
	/**
	 * @return el m4
	 */
	public long getM4() {
		return m4;
	}
	/**
	 * @param m4 el m4 a establecer
	 */
	public void setM4(long m4) {
		this.m4 = m4;
	}
	/**
	 * @return el m5
	 */
	public long getM5() {
		return m5;
	}
	/**
	 * @param m5 el m5 a establecer
	 */
	public void setM5(long m5) {
		this.m5 = m5;
	}
	/**
	 * @return el m6
	 */
	public long getM6() {
		return m6;
	}
	/**
	 * @param m6 el m6 a establecer
	 */
	public void setM6(long m6) {
		this.m6 = m6;
	}
	/**
	 * @return el m7
	 */
	public long getM7() {
		return m7;
	}
	/**
	 * @param m7 el m7 a establecer
	 */
	public void setM7(long m7) {
		this.m7 = m7;
	}
	/**
	 * @return el m8
	 */
	public long getM8() {
		return m8;
	}
	/**
	 * @param m8 el m8 a establecer
	 */
	public void setM8(long m8) {
		this.m8 = m8;
	}
	/**
	 * @return el m9
	 */
	public long getM9() {
		return m9;
	}
	/**
	 * @param m9 el m9 a establecer
	 */
	public void setM9(long m9) {
		this.m9 = m9;
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
	 * @return el tipo_detalle
	 */
	public int getTipo_detalle() {
		return tipo_detalle;
	}
	/**
	 * @param tipo_detalle el tipo_detalle a establecer
	 */
	public void setTipo_detalle(int tipo_detalle) {
		this.tipo_detalle = tipo_detalle;
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
	
	
	
}

