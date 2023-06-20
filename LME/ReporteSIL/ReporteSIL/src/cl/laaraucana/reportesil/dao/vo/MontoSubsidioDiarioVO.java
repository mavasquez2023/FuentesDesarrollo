/**
 * 
 */
package cl.laaraucana.reportesil.dao.vo;

/**
 * @author IBM Software Factory
 *
 */
public class MontoSubsidioDiarioVO {
private double montoDiario;
private int numeroDias;
private int montoxDias;
private int seguroCesantia;
private int montoaPagar;
private int remuneracionMesAnterior;
private int baseCotizacionDiaria;
private int entidad;
private CotizacionesPagadasVO cotizaciones;

/**
 * @return the montoDiario
 */
public double getMontoDiario() {
	return montoDiario;
}
/**
 * @param montoDiario the montoDiario to set
 */
public void setMontoDiario(double montoDiario) {
	this.montoDiario = montoDiario;
}
/**
 * @return the numeroDias
 */
public int getNumeroDias() {
	return numeroDias;
}
/**
 * @param numeroDias the numeroDias to set
 */
public void setNumeroDias(int numeroDias) {
	this.numeroDias = numeroDias;
}
/**
 * @return the montoxDias
 */
public int getMontoxDias() {
	return montoxDias;
}
/**
 * @param montoxDias the montoxDias to set
 */
public void setMontoxDias(int montoxDias) {
	this.montoxDias = montoxDias;
}
/**
 * @return the seguroCesantia
 */
public int getSeguroCesantia() {
	return seguroCesantia;
}
/**
 * @param seguroCesantia the seguroCesantia to set
 */
public void setSeguroCesantia(int seguroCesantia) {
	this.seguroCesantia = seguroCesantia;
}
/**
 * @return the montoaPagar
 */
public int getMontoaPagar() {
	return montoaPagar;
}
/**
 * @param montoaPagar the montoaPagar to set
 */
public void setMontoaPagar(int montoaPagar) {
	this.montoaPagar = montoaPagar;
}
/**
 * @return the remuneracionMesAnterior
 */
public int getRemuneracionMesAnterior() {
	return remuneracionMesAnterior;
}
/**
 * @param remuneracionMesAnterior the remuneracionMesAnterior to set
 */
public void setRemuneracionMesAnterior(int remuneracionMesAnterior) {
	this.remuneracionMesAnterior = remuneracionMesAnterior;
}
/**
 * @return the baseCotizacionDiaria
 */
public int getBaseCotizacionDiaria() {
	return baseCotizacionDiaria;
}
/**
 * @param baseCotizacionDiaria the baseCotizacionDiaria to set
 */
public void setBaseCotizacionDiaria(int baseCotizacionDiaria) {
	this.baseCotizacionDiaria = baseCotizacionDiaria;
}

/**
 * @return the entidad
 */
public int getEntidad() {
	return entidad;
}
/**
 * @param entidad the entidad to set
 */
public void setEntidad(int entidad) {
	this.entidad = entidad;
}
/**
 * @return the cotizaciones
 */
public CotizacionesPagadasVO getCotizaciones() {
	return cotizaciones;
}
/**
 * @param cotizaciones the cotizaciones to set
 */
public void setCotizaciones(CotizacionesPagadasVO cotizaciones) {
	this.cotizaciones = cotizaciones;
}


}
