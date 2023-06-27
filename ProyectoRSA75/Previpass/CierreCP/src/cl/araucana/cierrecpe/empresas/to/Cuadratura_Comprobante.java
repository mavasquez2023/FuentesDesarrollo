

/*
 * @(#) Entidades.java    1.0 21-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.to;

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
public class Cuadratura_Comprobante {
	private String nombreSeccion;
	private String nombreEntidad;
	private Rut rutEntidad;
	private Rut rutEmpresa;
	private int convenio;
	private String tipoNomina;
	private long m1=0;
	private long m2=0;
	private long m3=0;
	private long m4=0;
	private long m5=0;
	private long m6=0;
	private long m7=0;
	private long m8=0;
	private long m9=0;
	private long m10=0;
	private long m11=0;
	private long m12=0;
	private long totalM1=0;
	private long totalM2=0;
	private long totalM3=0;
	private long totalM4=0;
	private long totalM5=0;
	private long totalM6=0;
	private long totalM7=0;
	private long totalM8=0;
	private long totalM9=0;
	private long totalM10=0;
	private long totalM11=0;
	private long totalM12=0;
	private int n_trabajadores=0;
	private long total=0;
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
	 * @return el rutEmpresa
	 */
	public Rut getRutEmpresa() {
		return rutEmpresa;
	}
	/**
	 * @param rutEmpresa el rutEmpresa a establecer
	 */
	public void setRutEmpresa(Rut rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
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
	 * @return el total
	 */
	public long getTotal() {
		return total;
	}
	/**
	 * @param total el total a establecer
	 */
	public void setTotal(long total) {
		this.total = total;
	}
	/**
	 * @return el tipoNomina
	 */
	public String getTipoNomina() {
		return tipoNomina;
	}
	/**
	 * @param tipoNomina el tipoNomina a establecer
	 */
	public void setTipoNomina(String tipoNomina) {
		this.tipoNomina = tipoNomina;
	}
	/**
	 * @return el totalM1
	 */
	public long getTotalM1() {
		return totalM1;
	}
	/**
	 * @param totalM1 el totalM1 a establecer
	 */
	public void setTotalM1(long totalM1) {
		this.totalM1 = totalM1;
	}
	/**
	 * @return el totalM10
	 */
	public long getTotalM10() {
		return totalM10;
	}
	/**
	 * @param totalM10 el totalM10 a establecer
	 */
	public void setTotalM10(long totalM10) {
		this.totalM10 = totalM10;
	}
	/**
	 * @return el totalM11
	 */
	public long getTotalM11() {
		return totalM11;
	}
	/**
	 * @param totalM11 el totalM11 a establecer
	 */
	public void setTotalM11(long totalM11) {
		this.totalM11 = totalM11;
	}
	/**
	 * @return el totalM12
	 */
	public long getTotalM12() {
		return totalM12;
	}
	/**
	 * @param totalM12 el totalM12 a establecer
	 */
	public void setTotalM12(long totalM12) {
		this.totalM12 = totalM12;
	}
	/**
	 * @return el totalM2
	 */
	public long getTotalM2() {
		return totalM2;
	}
	/**
	 * @param totalM2 el totalM2 a establecer
	 */
	public void setTotalM2(long totalM2) {
		this.totalM2 = totalM2;
	}
	/**
	 * @return el totalM3
	 */
	public long getTotalM3() {
		return totalM3;
	}
	/**
	 * @param totalM3 el totalM3 a establecer
	 */
	public void setTotalM3(long totalM3) {
		this.totalM3 = totalM3;
	}
	/**
	 * @return el totalM4
	 */
	public long getTotalM4() {
		return totalM4;
	}
	/**
	 * @param totalM4 el totalM4 a establecer
	 */
	public void setTotalM4(long totalM4) {
		this.totalM4 = totalM4;
	}
	/**
	 * @return el totalM5
	 */
	public long getTotalM5() {
		return totalM5;
	}
	/**
	 * @param totalM5 el totalM5 a establecer
	 */
	public void setTotalM5(long totalM5) {
		this.totalM5 = totalM5;
	}
	/**
	 * @return el totalM6
	 */
	public long getTotalM6() {
		return totalM6;
	}
	/**
	 * @param totalM6 el totalM6 a establecer
	 */
	public void setTotalM6(long totalM6) {
		this.totalM6 = totalM6;
	}
	/**
	 * @return el totalM7
	 */
	public long getTotalM7() {
		return totalM7;
	}
	/**
	 * @param totalM7 el totalM7 a establecer
	 */
	public void setTotalM7(long totalM7) {
		this.totalM7 = totalM7;
	}
	/**
	 * @return el totalM8
	 */
	public long getTotalM8() {
		return totalM8;
	}
	/**
	 * @param totalM8 el totalM8 a establecer
	 */
	public void setTotalM8(long totalM8) {
		this.totalM8 = totalM8;
	}
	/**
	 * @return el totalM9
	 */
	public long getTotalM9() {
		return totalM9;
	}
	/**
	 * @param totalM9 el totalM9 a establecer
	 */
	public void setTotalM9(long totalM9) {
		this.totalM9 = totalM9;
	}
	/**
	 * @return el convenio
	 */
	public int getConvenio() {
		return convenio;
	}
	/**
	 * @param convenio el convenio a establecer
	 */
	public void setConvenio(int convenio) {
		this.convenio = convenio;
	}
}

