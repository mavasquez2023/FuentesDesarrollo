/**
 * 
 */
package cl.araucana.wscreditosocial.vo;

import java.io.Serializable;

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
		name = "requestWS",
		propOrder = { "monto", "cuotas", "sucursal", "tipo_afiliado", "seguro_cesantia", "seguro_desgravamen" })
public class RequestWS implements Serializable{
	
@XmlElement(name="MONTO", required=true)
private int monto;

@XmlElement(name="CUOTAS", required=true)
private int cuotas;

@XmlElement(name="SUCURSAL", required=true)
private String sucursal;

@XmlElement(name="TIPO_AFILIADO", required=true)
private String tipo_afiliado;

@XmlElement(name="SEGURO_CESANTIA", required=true)
private String seguro_cesantia;

@XmlElement(name="SEGURO_DESGRAVAMEN", required=true)
private String seguro_desgravamen;

/**
 * @return the monto
 */
public int getMonto() {
	return monto;
}

/**
 * @param monto the monto to set
 */
public void setMonto(int monto) {
	this.monto = monto;
}

/**
 * @return the cuotas
 */
public int getCuotas() {
	return cuotas;
}

/**
 * @param cuotas the cuotas to set
 */
public void setCuotas(int cuotas) {
	this.cuotas = cuotas;
}

/**
 * @return the sucursal
 */
public String getSucursal() {
	return sucursal;
}

/**
 * @param sucursal the sucursal to set
 */
public void setSucursal(String sucursal) {
	this.sucursal = sucursal;
}

/**
 * @return the tipo_afiliado
 */
public String getTipo_afiliado() {
	return tipo_afiliado;
}

/**
 * @param tipo_afiliado the tipo_afiliado to set
 */
public void setTipo_afiliado(String tipo_afiliado) {
	this.tipo_afiliado = tipo_afiliado;
}

/**
 * @return the seguro_cesantia
 */
public String getSeguro_cesantia() {
	return seguro_cesantia;
}

/**
 * @param seguro_cesantia the seguro_cesantia to set
 */
public void setSeguro_cesantia(String seguro_cesantia) {
	this.seguro_cesantia = seguro_cesantia;
}

/**
 * @return the seguro_desgravamen
 */
public String getSeguro_desgravamen() {
	return seguro_desgravamen;
}

/**
 * @param seguro_desgravamen the seguro_desgravamen to set
 */
public void setSeguro_desgravamen(String seguro_desgravamen) {
	this.seguro_desgravamen = seguro_desgravamen;
}



}