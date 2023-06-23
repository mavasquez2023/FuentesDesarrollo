/**
 * 
 */
package cl.araucana.tgr.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Claudio Lillo
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "trabajadorVO",
		propOrder = { "codAFP", "periodo", "fechaPago", "tipoPago", "rutTrabajador", "dvRutTrabajador", 
				"apellidoPaterno", "apellidoMaterno", "nombres", "rentaImponible",
				"montoPagado", "tipoProceso", "tipoCliente", "diasTrabajados", "codMovPer", "montoPagoSalud" })
public class TrabajadorVO {
@XmlElement(name="PERIODO_PAGO", required=true)
private int periodo;
@XmlElement(name="CODIGO_AFP", required=true)
private int codAFP;
@XmlElement(name="RUT_AFILIADO", required=true)
private int rutTrabajador;
@XmlElement(name="DV_AFILIADO", required=true)
private String dvRutTrabajador;
@XmlElement(name="APE_PATERNO", required=true)
private String apellidoPaterno;
@XmlElement(name="APE_MATERNO")
private String apellidoMaterno;
@XmlElement(name="NOMBRE", required=true)
private String nombres;
@XmlElement(name="MONTO_IMPONIBLE", required=true)
private int rentaImponible;
@XmlElement(name="MONTO_COTIZACION", required=true)
private int montoPagado;
@XmlElement(name="FECHA_PAGO", required=true)
private String fechaPago;
@XmlElement(name="TIPO_PAGO", required=true)
private int tipoPago;
@XmlElement(name="TIPO_NOMINA", required=true)
private int tipoProceso;
@XmlElement(name="TIPO_CLIENTE", required=true)
private int tipoCliente;
@XmlElement(name="DIAS_TRABAJADOS", required=true)
private int diasTrabajados;
@XmlElement(name="CODIGO_AUSENCIA", required=true)
private int codMovPer;
@XmlElement(name="MONTO_PAGO_SALUD")
private int montoPagoSalud;

/**
 * @return the periodo
 */
public int getPeriodo() {
	return periodo;
}
/**
 * @param periodo the periodo to set
 */
public void setPeriodo(int periodo) {
	this.periodo = periodo;
}
/**
 * @return the codAFP
 */
public int getCodAFP() {
	return codAFP;
}
/**
 * @param codAFP the codAFP to set
 */
public void setCodAFP(int codAFP) {
	this.codAFP = codAFP;
}
/**
 * @return the rutTrabajador
 */
public int getRutTrabajador() {
	return rutTrabajador;
}
/**
 * @param rutTrabajador the rutTrabajador to set
 */
public void setRutTrabajador(int rutTrabajador) {
	this.rutTrabajador = rutTrabajador;
}
/**
 * @return the dvRutTrabajador
 */
public String getDvRutTrabajador() {
	return dvRutTrabajador;
}
/**
 * @param dvRutTrabajador the dvRutTrabajador to set
 */
public void setDvRutTrabajador(String dvRutTrabajador) {
	this.dvRutTrabajador = dvRutTrabajador;
}
/**
 * @return the apellidoPaterno
 */
public String getApellidoPaterno() {
	return apellidoPaterno;
}
/**
 * @param apellidoPaterno the apellidoPaterno to set
 */
public void setApellidoPaterno(String apellidoPaterno) {
	this.apellidoPaterno = apellidoPaterno;
}
/**
 * @return the apellidoMaterno
 */
public String getApellidoMaterno() {
	return apellidoMaterno;
}
/**
 * @param apellidoMaterno the apellidoMaterno to set
 */
public void setApellidoMaterno(String apellidoMaterno) {
	this.apellidoMaterno = apellidoMaterno;
}
/**
 * @return the nombres
 */
public String getNombres() {
	return nombres;
}
/**
 * @param nombres the nombres to set
 */
public void setNombres(String nombres) {
	this.nombres = nombres;
}
/**
 * @return the rentaImponible
 */
public int getRentaImponible() {
	return rentaImponible;
}
/**
 * @param rentaImponible the rentaImponible to set
 */
public void setRentaImponible(int rentaImponible) {
	this.rentaImponible = rentaImponible;
}
/**
 * @return the montoPagado
 */
public int getMontoPagado() {
	return montoPagado;
}
/**
 * @param montoPagado the montoPagado to set
 */
public void setMontoPagado(int montoPagado) {
	this.montoPagado = montoPagado;
}
/**
 * @return the fechaPago
 */
public String getFechaPago() {
	return fechaPago;
}
/**
 * @param fechaPago the fechaPago to set
 */
public void setFechaPago(String fechaPago) {
	this.fechaPago = fechaPago;
}
/**
 * @return the tipoPago
 */
public int getTipoPago() {
	return tipoPago;
}
/**
 * @param tipoPago the tipoPago to set
 */
public void setTipoPago(int tipoPago) {
	this.tipoPago = tipoPago;
}
/**
 * @return the tipoProceso
 */
public int getTipoProceso() {
	return tipoProceso;
}
/**
 * @param tipoProceso the tipoProceso to set
 */
public void setTipoProceso(int tipoProceso) {
	this.tipoProceso = tipoProceso;
}
/**
 * @return the tipoCliente
 */
public int getTipoCliente() {
	return tipoCliente;
}
/**
 * @param tipoCliente the tipoCliente to set
 */
public void setTipoCliente(int tipoCliente) {
	this.tipoCliente = tipoCliente;
}
/**
 * @return the diasTrabajados
 */
public int getDiasTrabajados() {
	return diasTrabajados;
}
/**
 * @param diasTrabajados the diasTrabajados to set
 */
public void setDiasTrabajados(int diasTrabajados) {
	this.diasTrabajados = diasTrabajados;
}
/**
 * @return the codMovPer
 */
public int getCodMovPer() {
	return codMovPer;
}
/**
 * @param codMovPer the codMovPer to set
 */
public void setCodMovPer(int codMovPer) {
	this.codMovPer = codMovPer;
}
/**
 * @return the montoPagoSalud
 */
public int getMontoPagoSalud() {
	return montoPagoSalud;
}
/**
 * @param montoPagoSalud the montoPagoSalud to set
 */
public void setMontoPagoSalud(int montoPagoSalud) {
	this.montoPagoSalud = montoPagoSalud;
}


}
