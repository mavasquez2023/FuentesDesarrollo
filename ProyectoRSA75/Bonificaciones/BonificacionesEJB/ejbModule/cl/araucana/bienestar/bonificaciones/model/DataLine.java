package cl.araucana.bienestar.bonificaciones.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

import cl.araucana.bienestar.bonificaciones.common.Util;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class DataLine implements Serializable{
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	
	SimpleDateFormat formato = new SimpleDateFormat ("dd-MM-yyyy", Locale.getDefault());
	
	private String line= null;
	private int numLine = 0;
	private String rut = null;
	private String dv = null;
	private int monto = 0;
	private Date fechaOcurrencia = null;
	private String boleta = null;
	
	/**
	 * Contructor Base
	 *
	 */
	public DataLine() {
		
	}
	
	/**
	 * Contructor a partir de los parámetros de una linea de un archivo entregada
	 * @param line: String con la linea
	 * @param numLine: Numero de Linea dentro del Archivo
	*/
	public DataLine(String line, int numLine) throws ParseException {
	
		this.line = line;
		this.numLine = numLine;
		StringTokenizer st = new StringTokenizer(line, ";");
		if (st.hasMoreTokens()) {
			this.rut = st.nextToken();
			if (st.hasMoreTokens()){
				this.dv =  st.nextToken();
				if (st.hasMoreTokens()){
					this.monto =  Integer.parseInt(st.nextToken());
					if (st.hasMoreTokens()){
						this.fechaOcurrencia =  formato.parse(st.nextToken());
						if (st.hasMoreTokens())
							this.boleta =  st.nextToken();
					}						
				}
			}		  	
		}
	}
	
	/**
	 * Indica si el rut es valido o no
	 * @return boolean
	 */
	public boolean isRutValido() {
		return Util.validateRut(this.getRut(),this.getDv());
	}
	
	/**
	 * @return
	 */
	public String getRut() {
		while(rut.length()<8) {
			rut="0"+rut;
		}
			
		return rut;
	}
	
	/**
	 * @return fullRut
	 */
	public String getFullRut() {
		return getRut() + "-" + dv;
	}

	/**
	 * @return
	 */
	public String getBoleta() {
		return boleta;
	}

	/**
	 * @return
	 */
	public String getDv() {
		return dv;
	}

	/**
	 * @return
	 */
	public Date getFechaOcurrencia() {
		return fechaOcurrencia;
	}

	/**
	 * @return
	 */
	public int getMonto() {
		return monto;
	}

	/**
	 * @return
	 */
	public int getNumLine() {
		return numLine;
	}

	/**
	 * @param string
	 */
	public void setBoleta(String string) {
		boleta = string;
	}

	/**
	 * @param string
	 */
	public void setDv(String string) {
		dv = string;
	}

	/**
	 * @param date
	 */
	public void setFechaOcurrencia(Date date) {
		fechaOcurrencia = date;
	}

	/**
	 * @param i
	 */
	public void setMonto(int i) {
		monto = i;
	}

	/**
	 * @param i
	 */
	public void setNumLine(int i) {
		numLine = i;
	}

	/**
	 * @param string
	 */
	public void setRut(String string) {
		rut = string;
	}

	/**
	 * @return
	 */
	public String getLine() {
		return line;
	}

	/**
	 * @param string
	 */
	public void setLine(String string) {
		line = string;
	}

}
