

/*
 * @(#) FiltroCotizantes.java    1.0 22-07-2009
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
 *            <TD> 22-07-2009 </TD>
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
public class FiltroCotizantesTO {
	private Rut rutEmpresa;
	private int convenio;
	private int tipo_detalle;
	private String tipoProceso;
	private int idCcaf;
	private int idMutual;
	private String id_sucursal;
	private boolean planillaxSucursal=true;
	private int tipo_seccion;
	private String tipo_cliente;
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
	 * @return el tipoProceso
	 */
	public String getTipoProceso() {
		return tipoProceso;
	}
	/**
	 * @param tipoProceso el tipoProceso a establecer
	 */
	public void setTipoProceso(String tipoProceso) {
		this.tipoProceso = tipoProceso;
	}
	/**
	 * @return el idCcaf
	 */
	public int getIdCcaf() {
		return idCcaf;
	}
	/**
	 * @param idCcaf el idCcaf a establecer
	 */
	public void setIdCcaf(int idCcaf) {
		this.idCcaf = idCcaf;
	}
	/**
	 * @return el idMutual
	 */
	public int getIdMutual() {
		return idMutual;
	}
	/**
	 * @param idMutual el idMutual a establecer
	 */
	public void setIdMutual(int idMutual) {
		this.idMutual = idMutual;
	}
	/**
	 * @return el id_sucursal
	 */
	public String getId_sucursal() {
		return id_sucursal;
	}
	/**
	 * @param id_sucursal el id_sucursal a establecer
	 */
	public void setId_sucursal(String id_sucursal) {
		this.id_sucursal = id_sucursal;
	}
	/**
	 * @return el planillaxSucursal
	 */
	public boolean isPlanillaxSucursal() {
		return planillaxSucursal;
	}
	/**
	 * @param planillaxSucursal el planillaxSucursal a establecer
	 */
	public void setPlanillaxSucursal(boolean planillaxSucursal) {
		this.planillaxSucursal = planillaxSucursal;
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
	 * @return el tipo_cliente
	 */
	public String getTipo_cliente() {
		return tipo_cliente;
	}
	/**
	 * @param tipo_cliente el tipo_cliente a establecer
	 */
	public void setTipo_cliente(String tipo_cliente) {
		this.tipo_cliente = tipo_cliente;
	}

}
