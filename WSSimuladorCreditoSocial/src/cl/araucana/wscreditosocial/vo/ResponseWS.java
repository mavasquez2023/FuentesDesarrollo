/**
 * 
 */
package cl.araucana.wscreditosocial.vo;

import java.io.Serializable;
import java.util.List;

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
		name = "creditoVO",
		propOrder = { "log", "monto_cuota", "tasa_int_mensual", "tasa_int_anual", "cae", "impuesto", 
				"gasto_notarial", "costo_total_credito", "costo_mensual_desgravamen", "costo_total_desgravamen",
				"costo_mensual_cesantia", "costos_total_cesantia", "fecha_primer_vencimiento", "detalleCuotas" })
public class ResponseWS implements Serializable{
		@XmlElement(name="LOG_RESPUESTA", required=true)
	    private String log;
		
		@XmlElement(name="MONTO_CUOTA", required=true)
	    private int monto_cuota;
		
		@XmlElement(name="TASA_INT_MENSUAL", required=true)
	    private double tasa_int_mensual;
		
		@XmlElement(name="TASA_INT_ANUAL", required=true)
	    private double tasa_int_anual;
		
		@XmlElement(name="CAE", required=true)
	    private double cae;
		
		@XmlElement(name="IMPUESTO", required=true)
	    private int impuesto;
		
		@XmlElement(name="GASTO_NOTARIAL", required=true)
	    private int gasto_notarial;
		
		@XmlElement(name="CTC", required=true)
	    private int costo_total_credito;
		
		@XmlElement(name="COSTO_MENSUAL_DESGRAVAMEN", required=false)
	    private int costo_mensual_desgravamen;
		
		@XmlElement(name="COSTO_TOTAL_DESGRAVAMEN", required=false)
	    private int costo_total_desgravamen;
		
		@XmlElement(name="COSTO_MENSUAL_CESANTIA", required=false)
	    private int costo_mensual_cesantia;
		
		@XmlElement(name="COSTOS_TOTAL_CESANTIA", required=false)
	    private int costos_total_cesantia;
		
		@XmlElement(name="FECHA_PRIMER_VENCIMIENTO", required=true)
	    private int fecha_primer_vencimiento;
		
		@XmlElement(name="DETALLE_CUOTA", required=false)
		private List<ResponseDetalleWS> detalleCuotas;
		/**
		 * @return the log
		 */
		public String getLog() {
			return log;
		}

		/**
		 * @param log the log to set
		 */
		public void setLog(String log) {
			this.log = log;
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
		 * @return the tasa_int_mensual
		 */
		public double getTasa_int_mensual() {
			return tasa_int_mensual;
		}

		/**
		 * @param tasa_int_mensual the tasa_int_mensual to set
		 */
		public void setTasa_int_mensual(double tasa_int_mensual) {
			this.tasa_int_mensual = tasa_int_mensual;
		}

		/**
		 * @return the tasa_int_anual
		 */
		public double getTasa_int_anual() {
			return tasa_int_anual;
		}

		/**
		 * @param tasa_int_anual the tasa_int_anual to set
		 */
		public void setTasa_int_anual(double tasa_int_anual) {
			this.tasa_int_anual = tasa_int_anual;
		}

		/**
		 * @return the cae
		 */
		public double getCae() {
			return cae;
		}

		/**
		 * @param cae the cae to set
		 */
		public void setCae(double cae) {
			this.cae = cae;
		}

		/**
		 * @return the impuesto
		 */
		public int getImpuesto() {
			return impuesto;
		}

		/**
		 * @param impuesto the impuesto to set
		 */
		public void setImpuesto(int impuesto) {
			this.impuesto = impuesto;
		}

		/**
		 * @return the gasto_notarial
		 */
		public int getGasto_notarial() {
			return gasto_notarial;
		}

		/**
		 * @param gasto_notarial the gasto_notarial to set
		 */
		public void setGasto_notarial(int gasto_notarial) {
			this.gasto_notarial = gasto_notarial;
		}

		/**
		 * @return the costo_total_credito
		 */
		public int getCosto_total_credito() {
			return costo_total_credito;
		}

		/**
		 * @param costo_total_credito the costo_total_credito to set
		 */
		public void setCosto_total_credito(int costo_total_credito) {
			this.costo_total_credito = costo_total_credito;
		}

		/**
		 * @return the costo_mensual_desgravamen
		 */
		public int getCosto_mensual_desgravamen() {
			return costo_mensual_desgravamen;
		}

		/**
		 * @param costo_mensual_desgravamen the costo_mensual_desgravamen to set
		 */
		public void setCosto_mensual_desgravamen(int costo_mensual_desgravamen) {
			this.costo_mensual_desgravamen = costo_mensual_desgravamen;
		}

		/**
		 * @return the costo_total_desgravamen
		 */
		public int getCosto_total_desgravamen() {
			return costo_total_desgravamen;
		}

		/**
		 * @param costo_total_desgravamen the costo_total_desgravamen to set
		 */
		public void setCosto_total_desgravamen(int costo_total_desgravamen) {
			this.costo_total_desgravamen = costo_total_desgravamen;
		}

		/**
		 * @return the costo_mensual_cesantia
		 */
		public int getCosto_mensual_cesantia() {
			return costo_mensual_cesantia;
		}

		/**
		 * @param costo_mensual_cesantia the costo_mensual_cesantia to set
		 */
		public void setCosto_mensual_cesantia(int costo_mensual_cesantia) {
			this.costo_mensual_cesantia = costo_mensual_cesantia;
		}

		/**
		 * @return the costos_total_cesantia
		 */
		public int getCostos_total_cesantia() {
			return costos_total_cesantia;
		}

		/**
		 * @param costos_total_cesantia the costos_total_cesantia to set
		 */
		public void setCostos_total_cesantia(int costos_total_cesantia) {
			this.costos_total_cesantia = costos_total_cesantia;
		}

		/**
		 * @return the fecha_primer_vencimiento
		 */
		public int getFecha_primer_vencimiento() {
			return fecha_primer_vencimiento;
		}

		/**
		 * @param fecha_primer_vencimiento the fecha_primer_vencimiento to set
		 */
		public void setFecha_primer_vencimiento(int fecha_primer_vencimiento) {
			this.fecha_primer_vencimiento = fecha_primer_vencimiento;
		}

		/**
		 * @return the detalleCuotas
		 */
		public List<ResponseDetalleWS> getDetalleCuotas() {
			return detalleCuotas;
		}

		/**
		 * @param detalleCuotas the detalleCuotas to set
		 */
		public void setDetalleCuotas(List<ResponseDetalleWS> detalleCuotas) {
			this.detalleCuotas = detalleCuotas;
		}
		
		

}
