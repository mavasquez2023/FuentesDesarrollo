

/*
 * @(#) DetalleRendicionBES.java    1.0 06-08-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.spl.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
 *            <TD> 06-08-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Jorge Landeros <BR> jlandero@schema.cl </TD>
 *            <TD> Versión inicial. Contiene la información que llega en el archivo de conciliación batch
 *            en las líneas de detalle.
 *            </TD>
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
 * @author Lorge Landeros (jlandero@schema.cl)
 *
 * @version 1.0
 */

public class DetalleRendicionBES {
	
	private BigDecimal idResDetalleRend;
	private BigDecimal idDetalleRend;
	private long idRepresentativo;
	private String tipoReg;
	private int subTipo;
	private long rutCompra;
	private String digRutCompra;
	private String identificador;
	private String glosaMultipago;
	private Date fechaVencimiento;
	private String estadoPago;
	private long txBanco;
	private Date fechaOperacion;
	private Date horaOperacion;
	private Date fechaContable;
	private long rutCuenta;
	private String digRutCuenta;
	private long convenio;
	private String glosaPago;
	private BigDecimal codigoPago;
	private BigDecimal montoPago;
	private String respuestaBatch;
	private int alerta;
	private String filler;
	
	private String codError; //Guarda motivo de alguna inconsistencia o problema detectado
	
	private List listErrorImportacion = new ArrayList();
	private List listErrorInconsistente = new ArrayList();
	
	
	/**
	 * @return the alerta
	 */
	public int getAlerta() {
		return alerta;
	}
	/**
	 * @param alerta the alerta to set
	 */
	public void setAlerta(int alerta) {
		this.alerta = alerta;
	}
	/**
	 * @return the codigoPago
	 */
	public BigDecimal getCodigoPago() {
		return codigoPago;
	}
	/**
	 * @param codigoPago the codigoPago to set
	 */
	public void setCodigoPago(BigDecimal codigoPago) {
		this.codigoPago = codigoPago;
	}
	/**
	 * @return the convenio
	 */
	public long getConvenio() {
		return convenio;
	}
	/**
	 * @param convenio the convenio to set
	 */
	public void setConvenio(long convenio) {
		this.convenio = convenio;
	}
	/**
	 * @return the digRutCompra
	 */
	public String getDigRutCompra() {
		return digRutCompra;
	}
	/**
	 * @param digRutCompra the digRutCompra to set
	 */
	public void setDigRutCompra(String digRutCompra) {
		this.digRutCompra = digRutCompra;
	}
	/**
	 * @return the digRutCuenta
	 */
	public String getDigRutCuenta() {
		return digRutCuenta;
	}
	/**
	 * @param digRutCuenta the digRutCuenta to set
	 */
	public void setDigRutCuenta(String digRutCuenta) {
		this.digRutCuenta = digRutCuenta;
	}
	/**
	 * @return the estadoPago
	 */
	public String getEstadoPago() {
		return estadoPago;
	}
	/**
	 * @param estadoPago the estadoPago to set
	 */
	public void setEstadoPago(String estadoPago) {
		this.estadoPago = estadoPago;
	}
	/**
	 * @return the fechaContable
	 */
	public Date getFechaContable() {
		return fechaContable;
	}
	/**
	 * @param fechaContable the fechaContable to set
	 */
	public void setFechaContable(Date fechaContable) {
		this.fechaContable = fechaContable;
	}
	/**
	 * @return the fechaOperacion
	 */
	public Date getFechaOperacion() {
		return fechaOperacion;
	}
	/**
	 * @param fechaOperacion the fechaOperacion to set
	 */
	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
	/**
	 * @return the fechaVencimiento
	 */
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	/**
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	/**
	 * @return the filler
	 */
	public String getFiller() {
		return filler;
	}
	/**
	 * @param filler the filler to set
	 */
	public void setFiller(String filler) {
		this.filler = filler;
	}
	/**
	 * @return the glosaMultipago
	 */
	public String getGlosaMultipago() {
		return glosaMultipago;
	}
	/**
	 * @param glosaMultipago the glosaMultipago to set
	 */
	public void setGlosaMultipago(String glosaMultipago) {
		this.glosaMultipago = glosaMultipago;
	}
	/**
	 * @return the glosaPago
	 */
	public String getGlosaPago() {
		return glosaPago;
	}
	/**
	 * @param glosaPago the glosaPago to set
	 */
	public void setGlosaPago(String glosaPago) {
		this.glosaPago = glosaPago;
	}
	/**
	 * @return the horaOperacion
	 */
	public Date getHoraOperacion() {
		return horaOperacion;
	}
	/**
	 * @param horaOperacion the horaOperacion to set
	 */
	public void setHoraOperacion(Date horaOperacion) {
		this.horaOperacion = horaOperacion;
	}
	/**
	 * @return the identificador
	 */
	public String getIdentificador() {
		return identificador;
	}
	/**
	 * @param identificador the identificador to set
	 */
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	/**
	 * @return the montoPago
	 */
	public BigDecimal getMontoPago() {
		return montoPago;
	}
	/**
	 * @param montoPago the montoPago to set
	 */
	public void setMontoPago(BigDecimal montoPago) {
		this.montoPago = montoPago;
	}
	/**
	 * @return the respuestaBatch
	 */
	public String getRespuestaBatch() {
		return respuestaBatch;
	}
	/**
	 * @param respuestaBatch the respuestaBatch to set
	 */
	public void setRespuestaBatch(String respuestaBatch) {
		this.respuestaBatch = respuestaBatch;
	}
	/**
	 * @return the rutCompra
	 */
	public long getRutCompra() {
		return rutCompra;
	}
	/**
	 * @param rutCompra the rutCompra to set
	 */
	public void setRutCompra(long rutCompra) {
		this.rutCompra = rutCompra;
	}
	/**
	 * @return the rutCuenta
	 */
	public long getRutCuenta() {
		return rutCuenta;
	}
	/**
	 * @param rutCuenta the rutCuenta to set
	 */
	public void setRutCuenta(long rutCuenta) {
		this.rutCuenta = rutCuenta;
	}
	/**
	 * @return the subTipo
	 */
	public int getSubTipo() {
		return subTipo;
	}
	/**
	 * @param subTipo the subTipo to set
	 */
	public void setSubTipo(int subTipo) {
		this.subTipo = subTipo;
	}
	/**
	 * @return the tipoReg
	 */
	public String getTipoReg() {
		return tipoReg;
	}
	/**
	 * @param tipoReg the tipoReg to set
	 */
	public void setTipoReg(String tipoReg) {
		this.tipoReg = tipoReg;
	}
	/**
	 * @return the txBanco
	 */
	public long getTxBanco() {
		return txBanco;
	}
	/**
	 * @param txBanco the txBanco to set
	 */
	public void setTxBanco(long txBanco) {
		this.txBanco = txBanco;
	}
	/**
	 * @return the codError
	 */
	public String getCodError() {
		return codError;
	}
	/**
	 * @param codError the codError to set
	 */
	public void setCodError(String codError) {
		this.codError = codError;
	}
	/**
	 * @return the listErrorImportacion
	 */
	public List getListErrorImportacion() {
		return listErrorImportacion;
	}
	/**
	 * @param listErrorImportacion the listErrorImportacion to set
	 */
	public void setListErrorImportacion(List listErrorImportacion) {
		this.listErrorImportacion = listErrorImportacion;
	}
	/**
	 * @return the listErrorInconsistente
	 */
	public List getListErrorInconsistente() {
		return listErrorInconsistente;
	}
	/**
	 * @param listErrorInconsistente the listErrorInconsistente to set
	 */
	public void setListErrorInconsistente(List listErrorInconsistente) {
		this.listErrorInconsistente = listErrorInconsistente;
	}
	public BigDecimal getIdDetalleRend() {
		return idDetalleRend;
	}
	public void setIdDetalleRend(BigDecimal idDetalleRend) {
		this.idDetalleRend = idDetalleRend;
	}
	/**
	 * @return the idResDetalleRend
	 */
	public BigDecimal getIdResDetalleRend() {
		return idResDetalleRend;
	}
	/**
	 * @param idResDetalleRend the idResDetalleRend to set
	 */
	public void setIdResDetalleRend(BigDecimal idResDetalleRend) {
		this.idResDetalleRend = idResDetalleRend;
	}
	/**
	 * @return the idRepresentativo
	 */
	public long getIdRepresentativo() {
		return idRepresentativo;
	}
	/**
	 * @param idRepresentativo the idRepresentativo to set
	 */
	public void setIdRepresentativo(long idRepresentativo) {
		this.idRepresentativo = idRepresentativo;
	}
	
	

}

