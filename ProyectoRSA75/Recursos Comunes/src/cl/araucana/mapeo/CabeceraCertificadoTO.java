/**
 * 
 */
package cl.araucana.mapeo;

/**
 * @author usist24
 *
 */
public class CabeceraCertificadoTO {
private String tipoNomina="R";
private int rutemp;
private int rutafi;
private String apellidos;
private String nombres;
private String razonSocial;
private String direccion;
private String region;
private int periodo;
private char dvRutemp;
private char dvRutafi;
private int fechaEmision;
private String sucursal;
private int convenio;
private int holding;


public String toString(){
	String salida="";
	String tabla="";
	try {
		if(getTipoNomina().equals("R")){
			//Remu
			tabla="pwf6000D";
		}else{
			//Grati
			tabla="pwf6001D";
		}
			salida= "insert into pwdtad." + tabla + " values(" + getRutemp() + ", " + getRutafi() + ", " + getFechaEmision() + ", '" + getDvRutemp() + "', '" + getDvRutafi() + "', '" + getApellidos()+ "', '" + getNombres() + "', '" + getRazonSocial() + "', '" + getDireccion() + "', '', '', 0, '', '','" + getRegion() + "', '', '', " + getPeriodo() + ", " + getConvenio() + ", '" + getSucursal() + "', " + getHolding() + ")"; 
		return salida;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return salida;
}

//Rellena un texto con caracteres a la derecha ('D') o izquierda ('I') hasta completar <largo>
private String padding(String text, int largo, char character, char ubicacion) {
	String caracter= String.valueOf(character);
	String relleno= "";
	
	//se trunca el string en caso de venir mas largo que lo solicitado
	text=truncateText(text, largo);
	//Se generar los espacios necesarios
	for (int i=1;i<=largo - text.length();i++){
		relleno= relleno.concat(caracter);
	}
	if(ubicacion=='D'){
		//se concatena los caracteres a la derecha del string
		return text.concat(relleno);
	}else{
		return relleno.concat(text);
	}
}

//trunca un string <text> hasta <largo>
public String truncateText(String text, int length){

	if(text != null && text.length() > length){
		text = text.substring(0,length);
	}
	return text;

}

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
 * @return el dvRutafi
 */
public char getDvRutafi() {
	return dvRutafi;
}

/**
 * @param dvRutafi el dvRutafi a establecer
 */
public void setDvRutafi(char dvRutafi) {
	this.dvRutafi = dvRutafi;
}

/**
 * @return el dvRutemp
 */
public char getDvRutemp() {
	return dvRutemp;
}

/**
 * @param dvRutemp el dvRutemp a establecer
 */
public void setDvRutemp(char dvRutemp) {
	this.dvRutemp = dvRutemp;
}

/**
 * @return el fechaEmision
 */
public int getFechaEmision() {
	return fechaEmision;
}

/**
 * @param fechaEmision el fechaEmision a establecer
 */
public void setFechaEmision(int fechaEmision) {
	this.fechaEmision = fechaEmision;
}

/**
 * @return el holding
 */
public int getHolding() {
	return holding;
}

/**
 * @param holding el holding a establecer
 */
public void setHolding(int holding) {
	this.holding = holding;
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
 * @return el rutafi
 */
public int getRutafi() {
	return rutafi;
}

/**
 * @param rutafi el rutafi a establecer
 */
public void setRutafi(int rutafi) {
	this.rutafi = rutafi;
}

/**
 * @return el rutemp
 */
public int getRutemp() {
	return rutemp;
}

/**
 * @param rutemp el rutemp a establecer
 */
public void setRutemp(int rutemp) {
	this.rutemp = rutemp;
}

/**
 * @return el sucursal
 */
public String getSucursal() {
	return sucursal;
}

/**
 * @param sucursal el sucursal a establecer
 */
public void setSucursal(String sucursal) {
	this.sucursal = sucursal;
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
	this.nombres = nombres;
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
 * @return el direccion
 */
public String getDireccion() {
	return direccion;
}

/**
 * @param direccion el direccion a establecer
 */
public void setDireccion(String direccion) {
	if(direccion.trim().equals(",")){
		direccion="";
	}
	this.direccion = direccion;
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
 * @return el tipoNomina
 */
public String getTipoNomina() {
	return tipoNomina;
}

/**
 * @param tipoNomina el tipoNomina a establecer
 */
public void setTipoNomina(String tipoNomina) {
	this.tipoNomina = tipoNomina;
}

}
