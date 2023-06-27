/*
 * Creada el 14-04-2008
 *
 */
package cl.araucana.cierrecpe.empresas.planillas;

import cl.araucana.core.util.Rut;
import cl.recursos.Formato;


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
 *            <TD> 05-05-2008 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> Claudio Lillo <BR> clillo@laaraucana.cl </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Claudio Lillo (clillo@laaraucana.cl)
 *
 * @version 1.0
 */
public class PlanillaBaseCotizante {

	

	/*
	 * PROPIEDADES DE PAGINA DE DETALLE
	 */
	private int numeroLineaDetalle;
	private String id_sucursal;
	private Rut rutCotizante;
	private String apellidosCotizante;
	private String nombresCotizante;
	private int diasTrabajados;
	private String generoCotizante;
	
	/**
	 * @return el apellidosCotizante
	 */
	public String getApellidosCotizante() {
		return apellidosCotizante;
	}
	/**
	 * @param apellidosCotizante el apellidosCotizante a establecer
	 */
	public void setApellidosCotizante(String apellidosCotizante) {
		this.apellidosCotizante = apellidosCotizante;
	}
	
	/**
	 * @return el nombresCotizante
	 */
	public String getNombresCotizante() {
		return nombresCotizante;
	}
	/**
	 * @param nombresCotizante el nombresCotizante a establecer
	 */
	public void setNombresCotizante(String nombresCotizante) {
		this.nombresCotizante = nombresCotizante;
	}
	/**
	 * @return el numeroLineaDetalle
	 */
	public int getNumeroLineaDetalle() {
		return numeroLineaDetalle;
	}
	/**
	 * @param numeroLineaDetalle el numeroLineaDetalle a establecer
	 */
	public void setNumeroLineaDetalle(int numeroLineaDetalle) {
		this.numeroLineaDetalle = numeroLineaDetalle;
	}
	
	/**
	 * @return el rutCotizante
	 */
	public Rut getRutCotizante() {
		return rutCotizante;
	}
	/**
	 * @return el rutCotizante en formato String alineado a la derecha
	 */
	public String getRutCotizante(int largo) {
		if(getRutCotizante()!= null){
			return Formato.paddingLeft(String.valueOf(getRutCotizante().getNumber()), largo, ' ');
		}
		return "";
	}
	/**
	 * @param rutCotizante el rutCotizante a establecer
	 */
	public void setRutCotizante(Rut rutCotizante) {
		this.rutCotizante = rutCotizante;
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
	 * @return el diasTrabajados
	 */
	public int getDiasTrabajados() {
		return diasTrabajados;
	}
	/**
	 * @param diasTrabajados el diasTrabajados a establecer
	 */
	public void setDiasTrabajados(int diasTrabajados) {
		this.diasTrabajados = diasTrabajados;
	}
	/**
	 * @return el generoCotizante
	 */
	public String getGeneroCotizante() {
		return generoCotizante;
	}
	/**
	 * @param generoCotizante el generoCotizante a establecer
	 */
	public void setGeneroCotizante(String generoCotizante) {
		this.generoCotizante = generoCotizante;
	}

		
}
