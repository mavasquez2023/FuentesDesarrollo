

/*
 * @(#) TrabajadorTO.java    1.0 22/02/2010
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.business;

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
 *            <TD> 22/02/2010 </TD>
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
public class TrabajadorTGR {
private int periodo;
private int tipoNomina;
private Rut rutEmpresa;
private int codigoAFP;
private Rut rutTrabajador;
private String apellidoPaterno;
private String apellidoMaterno;
private String nombres;
private int rentaImponible;
private int montoCotizacion;
/**
 * @return el apellidoMaterno
 */
public String getApellidoMaterno() {
	if(apellidoMaterno.length()>15){
		return apellidoMaterno.substring(0, 15);
	}else{
		return apellidoMaterno;
	}
}
/**
 * @param apellidoMaterno el apellidoMaterno a establecer
 */
public void setApellidoMaterno(String apellidoMaterno) {
	this.apellidoMaterno = apellidoMaterno;
}
/**
 * @return el apellidoPaterno
 */
public String getApellidoPaterno() {
	if(apellidoPaterno.length()>15){
		return apellidoPaterno.substring(0, 15);
	}else{
		return apellidoPaterno;
	}
}
/**
 * @param apellidoPaterno el apellidoPaterno a establecer
 */
public void setApellidoPaterno(String apellidoPaterno) {
	this.apellidoPaterno = apellidoPaterno;
}
/**
 * @return el codigoAFP
 */
public int getCodigoAFP() {
	return codigoAFP;
}
/**
 * @param codigoAFP el codigoAFP a establecer
 */
public void setCodigoAFP(int codigoAFP) {
	this.codigoAFP = codigoAFP;
}
/**
 * @return el montoCotizacion
 */
public int getMontoCotizacion() {
	return montoCotizacion;
}
/**
 * @param montoCotizacion el montoCotizacion a establecer
 */
public void setMontoCotizacion(int montoCotizacion) {
	this.montoCotizacion = montoCotizacion;
}
/**
 * @return el nombres
 */
public String getNombres() {
	if(nombres.length()>20){
		return nombres.substring(0, 20);
	}else{
		return nombres;
	}
}
/**
 * @param nombres el nombres a establecer
 */
public void setNombres(String nombres) {
	this.nombres = nombres;
}
/**
 * @return el periodo
 */
public int getPeriodo() {
	return periodo;
}
/**
 * @param periodo el periodo a establecer
 */
public void setPeriodo(int periodo) {
	this.periodo = periodo;
}
/**
 * @return el rentaImponible
 */
public int getRentaImponible() {
	return rentaImponible;
}
/**
 * @param rentaImponible el rentaImponible a establecer
 */
public void setRentaImponible(int rentaImponible) {
	this.rentaImponible = rentaImponible;
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
 * @return el rutTrabajador
 */
public Rut getRutTrabajador() {
	return rutTrabajador;
}
/**
 * @param rutTrabajador el rutTrabajador a establecer
 */
public void setRutTrabajador(Rut rutTrabajador) {
	this.rutTrabajador = rutTrabajador;
}
/**
 * @return el tipoNomina
 */
public int getTipoNomina() {
	return tipoNomina;
}
/**
 * @param tipoNomina el tipoNomina a establecer
 */
public void setTipoNomina(int tipoNomina) {
	this.tipoNomina = tipoNomina;
}



}

