

/*
 * @(#) EncabezadoRendicionBES.java    1.0 06-08-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.spl.beans;

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
 *            en el encabezado.
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
public class EncabezadoRendicionBES {
	
	private String tipoReg;
	private Date fechaRendicion;
	private int banco;
	private long rutConcentrador;
	private String digConcentrador;
	private String glosaServicio;
	private String filler;
	
    private String codError; //Guarda motivo de alguna inconsistencia o problema detectado
	
	private List listErrorImportacion = new ArrayList();
	private List listErrorInconsistente = new ArrayList();
	
	/**
	 * @return the banco
	 */
	public int getBanco() {
		return banco;
	}
	/**
	 * @param banco the banco to set
	 */
	public void setBanco(int banco) {
		this.banco = banco;
	}
	/**
	 * @return the digConcentrador
	 */
	public String getDigConcentrador() {
		return digConcentrador;
	}
	/**
	 * @param digConcentrador the digConcentrador to set
	 */
	public void setDigConcentrador(String digConcentrador) {
		this.digConcentrador = digConcentrador;
	}
	/**
	 * @return the fechaRendicion
	 */
	public Date getFechaRendicion() {
		return fechaRendicion;
	}
	/**
	 * @param fechaRendicion the fechaRendicion to set
	 */
	public void setFechaRendicion(Date fechaRendicion) {
		this.fechaRendicion = fechaRendicion;
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
	 * @return the glosaServicio
	 */
	public String getGlosaServicio() {
		return glosaServicio;
	}
	/**
	 * @param glosaServicio the glosaServicio to set
	 */
	public void setGlosaServicio(String glosaServicio) {
		this.glosaServicio = glosaServicio;
	}
	/**
	 * @return the rutConcentrador
	 */
	public long getRutConcentrador() {
		return rutConcentrador;
	}
	/**
	 * @param rutConcentrador the rutConcentrador to set
	 */
	public void setRutConcentrador(long rutConcentrador) {
		this.rutConcentrador = rutConcentrador;
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
	
	
	
}

