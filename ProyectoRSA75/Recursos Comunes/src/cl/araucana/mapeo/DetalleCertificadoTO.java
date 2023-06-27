/**
 * 
 */
package cl.araucana.mapeo;

/**
 * @author usist24
 *
 */
public class DetalleCertificadoTO {
private String tipoNomina="R";
private int rutemp;
private int rutafi;
private String tipoEntidad;
private String entidad;
private String mesRemu;
private int anioRemu;
private char dvRutemp;
private char dvRutafi;
private int remuneracion;
private int monto;
private int fechaPago;
private String folio;
private String secuenciaFolio;
private int convenio=1;
private int holding;
/**
 * @return el anioRemu
 */
public int getAnioRemu() {
	return anioRemu;
}
/**
 * @param anioRemu el anioRemu a establecer
 */
public void setAnioRemu(int anioRemu) {
	this.anioRemu = anioRemu;
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
 * @return el entidad
 */
public String getEntidad() {
	return entidad;
}
/**
 * @param entidad el entidad a establecer
 */
public void setEntidad(String entidad) {
	this.entidad = entidad;
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
 * @return el folio
 */
public String getFolio() {
	return folio;
}
/**
 * @param folio el folio a establecer
 */
public void setFolio(String folio) {
	this.folio = folio;
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
 * @return el mesRemu
 */
public String getMesRemu() {
	return mesRemu;
}
/**
 * @param mesRemu el mesRemu a establecer
 */
public void setMesRemu(String mesRemu) {
	this.mesRemu = mesRemu;
}
/**
 * @return el monto
 */
public int getMonto() {
	return monto;
}
/**
 * @param monto el monto a establecer
 */
public void setMonto(int monto) {
	this.monto = monto;
}
/**
 * @return el remuneracion
 */
public int getRemuneracion() {
	return remuneracion;
}
/**
 * @param remuneracion el remuneracion a establecer
 */
public void setRemuneracion(int remuneracion) {
	this.remuneracion = remuneracion;
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
 * @return el secuenciaFolio
 */
public String getSecuenciaFolio() {
	
	return secuenciaFolio;
}
/**
 * @param secuenciaFolio el secuenciaFolio a establecer
 */
public void setSecuenciaFolio(String secuenciaFolio) {
	this.secuenciaFolio = secuenciaFolio;
}
/**
 * @return el tipoEntidad
 */
public String getTipoEntidad() {
	return tipoEntidad;
}
/**
 * @param tipoEntidad el tipoEntidad a establecer
 */
public void setTipoEntidad(String tipoEntidad) {
	this.tipoEntidad = tipoEntidad;
}

public String toString(){
	String salida="";
	String tabla="";
	try {
		if(getTipoNomina().equals("R")){
			if(getTipoEntidad().equals("1")){
				//APV
				tabla="pwf6103D";
			}else{
				//Remu
				tabla="pwf6100D";
			}
		}else{
			//Grati
			tabla="pwf6101D";
		}
			salida= "insert into pwdtad." + tabla + " values(" + getRutemp() + ", " + getRutafi() + ", '" + getTipoEntidad() + "', '" + getEntidad() + "', '" + getMesRemu() + "', " + getAnioRemu() + ", '" + getDvRutemp() + "', '" + getDvRutafi() + "', " + getRemuneracion() + ", " + getMonto() + ", " + getFechaPago() + ", '" + padding(getFolio(), 3, '0', 'I') + "', '" + getSecuenciaFolio() + "'," + getConvenio() + ", 0)"; 
		
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
