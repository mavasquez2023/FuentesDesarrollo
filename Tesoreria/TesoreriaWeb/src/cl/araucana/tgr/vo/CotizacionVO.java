/**
 * 
 */
package cl.araucana.tgr.vo;

import cl.araucana.tgr.util.UtilXML;

/**
 * @author Claudio Lillo
 *
 */
public class CotizacionVO {
private int rutEmpresa;
private String dvRutEmpresa;
private String razonSocial;
private String region;
private int periodo;
private String codAFP;
private int rutTrabajador;
private String dvRutTrabajador;
private String apellidoPaterno;
private String apellidoMaterno;
private String apellidos;
private String nombres;
private int rentaImponible;
private int montoPagado;
private int fechaPago;
private String tipoProceso;
private int diasTrabajados;
private String codMovPer;
private int montoSalud;
/**
 * @return el apellidoMaterno
 */
public String getApellidoMaterno() {
	return apellidoMaterno;
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
	return apellidoPaterno;
}
/**
 * @param apellidoPaterno el apellidoPaterno a establecer
 */
public void setApellidoPaterno(String apellidoPaterno) {
	this.apellidoPaterno = apellidoPaterno;
}
/**
 * @return el apellidos
 */
public String getApellidos() {
	return apellidos;
}
/**
 * @param apellidos el apellidos a establecer
 */
public void setApellidos(String apellidos) {
	this.apellidos = apellidos;
	if(apellidos!=null){
		String[] apesep= UtilXML.getApellidosSeparados(apellidos);
		if(apesep.length>=1){
			setApellidoPaterno(apesep[0]);
		}
		if(apesep.length>=2){
			setApellidoMaterno(apesep[1]);
		}
	}else{
		setApellidoPaterno("");
		setApellidoMaterno("");
	}
}
/**
 * @return el codAFP
 */
public String getCodAFP() {
	return codAFP;
}
/**
 * @param codAFP el codAFP a establecer
 */
public void setCodAFP(String codAFP) {
	this.codAFP = codAFP;
}
/**
 * @return el codMovPer
 */
public String getCodMovPer() {
	return codMovPer;
}
/**
 * @param codMovPer el codMovPer a establecer
 */
public void setCodMovPer(String codMovPer) {
	if(codMovPer==null || codMovPer.equals("0")){
		codMovPer="";
	}
	int pos= codMovPer.indexOf('-');
	if(pos>-1){
		this.codMovPer = codMovPer.substring(pos);
	}else{
		this.codMovPer = codMovPer;
	}
	
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
 * @return el dvRutEmpresa
 */
public String getDvRutEmpresa() {
	return dvRutEmpresa;
}
/**
 * @param dvRutEmpresa el dvRutEmpresa a establecer
 */
public void setDvRutEmpresa(String dvRutEmpresa) {
	this.dvRutEmpresa = dvRutEmpresa;
}
/**
 * @return el dvRutTrabajador
 */
public String getDvRutTrabajador() {
	return dvRutTrabajador;
}
/**
 * @param dvRutTrabajador el dvRutTrabajador a establecer
 */
public void setDvRutTrabajador(String dvRutTrabajador) {
	this.dvRutTrabajador = dvRutTrabajador;
}
/**
 * @return el fechaPago
 */
public int getFechaPago() {
	return fechaPago;
}
/**
 * @param fechaPago el fechaPago a establecer
 */
public void setFechaPago(int fechaPago) {
	this.fechaPago = fechaPago;
}
/**
 * @return el montoPagado
 */
public int getMontoPagado() {
	return montoPagado;
}
/**
 * @param montoPagado el montoPagado a establecer
 */
public void setMontoPagado(int montoPagado) {
	this.montoPagado = montoPagado;
}
/**
 * @return el nombres
 */
public String getNombres() {
	return nombres;
}
/**
 * @param nombres el nombres a establecer
 */
public void setNombres(String nombres) {
	if(nombres==null){
		nombres="";
	}
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
 * @return el razonSocial
 */
public String getRazonSocial() {
	return razonSocial;
}
/**
 * @param razonSocial el razonSocial a establecer
 */
public void setRazonSocial(String razonSocial) {
	this.razonSocial = razonSocial;
}
/**
 * @return el region
 */
public String getRegion() {
	return region;
}
/**
 * @param region el region a establecer
 */
public void setRegion(String region) {
	this.region = region;
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
public int getRutEmpresa() {
	return rutEmpresa;
}
/**
 * @param rutEmpresa el rutEmpresa a establecer
 */
public void setRutEmpresa(int rutEmpresa) {
	this.rutEmpresa = rutEmpresa;
}
/**
 * @return el rutTrabajador
 */
public int getRutTrabajador() {
	return rutTrabajador;
}
/**
 * @param rutTrabajador el rutTrabajador a establecer
 */
public void setRutTrabajador(int rutTrabajador) {
	this.rutTrabajador = rutTrabajador;
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
	if(tipoProceso.equals("R")){
		tipoProceso="1";
	}else if(tipoProceso.equals("G")){
		tipoProceso="0";
	}
	this.tipoProceso = tipoProceso;
}
/**
 * @return the montoSalud
 */
public int getMontoSalud() {
	return montoSalud;
}
/**
 * @param montoSalud the montoSalud to set
 */
public void setMontoSalud(int montoSalud) {
	this.montoSalud = montoSalud;
}

}
