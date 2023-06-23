package cl.araucana.spl.action.roles;

import java.util.Date;

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

public class OpcionDVO {
	
	/** Id de la opcion **/
	private long id;
	/** Id del Sistema **/
	private long idSistema;
	/** Descripcion de la Opcion **/
	private String descripcion;
	/** Codigo interno de la Opcion **/
	private String codigo;
	/** Fecha de Creacion de la Opcion **/
	private Date fechaCreacion;
	/** Usuario que creo la Opcion **/
	private String usuario;
	/** Indica si se trata de un rol publico **/
	private boolean publico;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdSistema() {
		return idSistema;
	}
	public void setIdSistema(long idSistema) {
		this.idSistema = idSistema;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public boolean isPublico() {
		return publico;
	}
	public void setPublico(boolean publico) {
		this.publico = publico;
	}
}
