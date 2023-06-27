package cl.araucana.spl.beans.xmlbit;

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
 *            <TD> 13-01-2014 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Gonzalo Mallea Lorca <BR> gmallea@schema.cl </TD>
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
 * @author Gonzalo Mallea Lorca (gmallea@schema.cl)
 *
 * @version 1.0
 */

public class DetallePagos {
	private String idConvenio;
	private String numeroProducto;
	private String numeroCliente;
	private String expiracionProducto;
	private String descProducto;
	private String montoProducto;
	private String fechaOperacion;
	
	public String toString() {
		return new StringBuffer("[DetallePagos::idConvenio=").append(idConvenio)
			.append(",numeroProducto=").append(numeroProducto)
			.append(",numeroCliente=").append(numeroCliente)
			.append(",expiracionProducto=").append(expiracionProducto)
			.append(",descProducto=").append(descProducto)
			.append(",montoProducto=").append(montoProducto)
			.append(",fechaHoraOperacion=").append(fechaOperacion)
			.append("]").toString();
	}	
	
	public String getFechaOperacion() {
		return fechaOperacion;
	}
	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
	public String getDescProducto() {
		return descProducto;
	}
	public void setDescProducto(String descProducto) {
		this.descProducto = descProducto;
	}
	public String getExpiracionProducto() {
		return expiracionProducto;
	}
	public void setExpiracionProducto(String expiracionProducto) {
		this.expiracionProducto = expiracionProducto;
	}
	public String getIdConvenio() {
		return idConvenio;
	}
	public void setIdConvenio(String idConvenio) {
		this.idConvenio = idConvenio;
	}
	public String getMontoProducto() {
		return montoProducto;
	}
	public void setMontoProducto(String montoProducto) {
		this.montoProducto = montoProducto;
	}
	public String getNumeroCliente() {
		return numeroCliente;
	}
	public void setNumeroCliente(String numeroCliente) {
		this.numeroCliente = numeroCliente;
	}
	public String getNumeroProducto() {
		return numeroProducto;
	}
	public void setNumeroProducto(String numeroProducto) {
		this.numeroProducto = numeroProducto;
	}

}
