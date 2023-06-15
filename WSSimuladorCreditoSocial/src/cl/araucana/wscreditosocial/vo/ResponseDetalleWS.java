/**
 * 
 */
package cl.araucana.wscreditosocial.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author IBM Software Factory
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
name = "detalleVO",
		propOrder = { "num_cuota", "fecha_vencimiento", "monto_cuota", "monto_interes", 
		"seguro_desgravamen", "seguro_cesantia", "saldo_capital" })
public class ResponseDetalleWS {
						
	@XmlElement(name="NUM_CUOTA", required=true)
	private int num_cuota;
	
	@XmlElement(name="FECHA_VENCIMIENTO", required=true)
    private int fecha_vencimiento;
	
	@XmlElement(name="MONTO_CUOTA", required=true)
    private int monto_cuota;
	
	@XmlElement(name="MONTO_INTERES", required=true)
    private int monto_interes;
	
	@XmlElement(name="SEGURO_DESGRAVAMEN", required=true)
    private int seguro_desgravamen;
	
	@XmlElement(name="SEGURO_CESANTIA", required=true)
    private int seguro_cesantia;
	
	@XmlElement(name="SALDO_CAPITAL", required=true)
    private int saldo_capital;

	/**
	 * @return the num_cuota
	 */
	public int getNum_cuota() {
		return num_cuota;
	}

	/**
	 * @param num_cuota the num_cuota to set
	 */
	public void setNum_cuota(int num_cuota) {
		this.num_cuota = num_cuota;
	}

	/**
	 * @return the fecha_vencimiento
	 */
	public int getFecha_vencimiento() {
		return fecha_vencimiento;
	}

	/**
	 * @param fecha_vencimiento the fecha_vencimiento to set
	 */
	public void setFecha_vencimiento(int fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}

	/**
	 * @return the monto_cuota
	 */
	public int getMonto_cuota() {
		return monto_cuota;
	}

	/**
	 * @param monto_cuota the monto_cuota to set
	 */
	public void setMonto_cuota(int monto_cuota) {
		this.monto_cuota = monto_cuota;
	}

	/**
	 * @return the monto_interes
	 */
	public int getMonto_interes() {
		return monto_interes;
	}

	/**
	 * @param monto_interes the monto_interes to set
	 */
	public void setMonto_interes(int monto_interes) {
		this.monto_interes = monto_interes;
	}

	/**
	 * @return the seguro_desgravamen
	 */
	public int getSeguro_desgravamen() {
		return seguro_desgravamen;
	}

	/**
	 * @param seguro_desgravamen the seguro_desgravamen to set
	 */
	public void setSeguro_desgravamen(int seguro_desgravamen) {
		this.seguro_desgravamen = seguro_desgravamen;
	}

	/**
	 * @return the seguro_cesantia
	 */
	public int getSeguro_cesantia() {
		return seguro_cesantia;
	}

	/**
	 * @param seguro_cesantia the seguro_cesantia to set
	 */
	public void setSeguro_cesantia(int seguro_cesantia) {
		this.seguro_cesantia = seguro_cesantia;
	}

	/**
	 * @return the saldo_capital
	 */
	public int getSaldo_capital() {
		return saldo_capital;
	}

	/**
	 * @param saldo_capital the saldo_capital to set
	 */
	public void setSaldo_capital(int saldo_capital) {
		this.saldo_capital = saldo_capital;
	}
	
	
}
