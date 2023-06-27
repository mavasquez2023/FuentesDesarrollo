

/*
 * @(#) BancoTO.java    1.0 05-09-2014
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.to;


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
 *            <TD> 05-09-2014 </TD>
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
public class BancoTO {
	private String nombre;
	private int idBanco;
	private String idCuenta;
	private String idCuentaOld;
	private String fechaContable;
	private int tipoRegistro;
	private int idPago;
	private long montoConsolidado;
	private int medioPago;
	/**
	 * @return el idBanco
	 */
	public int getIdBanco() {
		return idBanco;
	}
	/**
	 * @param idBanco el idBanco a establecer
	 */
	public void setIdBanco(int idBanco) {
		this.idBanco = idBanco;
	}
	/**
	 * @return el idCuenta
	 */
	public String getIdCuenta() {
		return idCuenta;
	}
	/**
	 * @param idCuenta el idCuenta a establecer
	 */
	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}
	/**
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre el nombre a establecer
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIdCuentaOld() {
		return idCuentaOld;
	}
	public void setIdCuentaOld(String idCuentaOld) {
		this.idCuentaOld = idCuentaOld;
	}
	/**
	 * @return el fechaContable
	 */
	public String getFechaContable() {
		return fechaContable;
	}
	/**
	 * @param fechaContable el fechaContable a establecer
	 */
	public void setFechaContable(String fechaContable) {
		this.fechaContable = fechaContable;
	}
	/**
	 * @return el tipoRegistro
	 */
	public int getTipoRegistro() {
		return tipoRegistro;
	}
	/**
	 * @param tipoRegistro el tipoRegistro a establecer
	 */
	public void setTipoRegistro(int tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	/**
	 * @return el idPago
	 */
	public int getIdPago() {
		return idPago;
	}
	/**
	 * @param idPago el idPago a establecer
	 */
	public void setIdPago(int idPago) {
		this.idPago = idPago;
	}
	/**
	 * @return el montoConsolidado
	 */
	public long getMontoConsolidado() {
		return montoConsolidado;
	}
	/**
	 * @param montoConsolidado el montoConsolidado a establecer
	 */
	public void setMontoConsolidado(long montoConsolidado) {
		this.montoConsolidado = montoConsolidado;
	}
	/**
	 * @return el medioPago
	 */
	public int getMedioPago() {
		return medioPago;
	}
	/**
	 * @param medioPago el medioPago a establecer
	 */
	public void setMedioPago(int medioPago) {
		this.medioPago = medioPago;
	}

}

