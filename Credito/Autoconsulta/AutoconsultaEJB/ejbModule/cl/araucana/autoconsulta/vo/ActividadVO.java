package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class ActividadVO implements Serializable {
		 
	public static final int CONSULTA_LICENCIA_MEDICA=1;
	public static final int CERTIFICADO_LICENCIA_MEDICA=2;
	public static final int CONSULTA_CREDITOS_VIGENTES=3;
	public static final int CERTIFICADO_DEUDA_VIGENTE=4;
	public static final int CONSULTA_EVALUACION_CREDITO=5;
	public static final int CONSULTA_EMPLEADORES=6;
	public static final int CONSULTA_CARTOLA_LEASING=7;
	public static final int CONSULTA_CHEQUES_EMPRESA=8;
	public static final int CERTIFICADO_AFILIACION=9;
	public static final int CERTIFICADO_ASIGNACION_FAMILIAR=10;
	public static final int CONSULTA_LIQUIDACION_REEMBOLSO=11;
	
	
	
	private long rutEmpresa = 0;
	private long rutUsuario = 0;
	private long rutAfiliado = 0;
	
	private String dispositivo=null;
	private Date fechaTransaccion=null;
	private Time horaTransaccion=null;
	private int funcion=0;




	/**
	 * @return
	 */
	public String getDispositivo() {
		return dispositivo;
	}

	/**
	 * @return
	 */
	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}

	/**
	 * @return
	 */
	public int getFuncion() {
		return funcion;
	}

	/**
	 * @return
	 */
	public Time getHoraTransaccion() {
		return horaTransaccion;
	}

	/**
	 * @param string
	 */
	public void setDispositivo(String string) {
		dispositivo = string;
	}

	/**
	 * @param date
	 */
	public void setFechaTransaccion(Date date) {
		fechaTransaccion = date;
	}

	/**
	 * @param i
	 */
	public void setFuncion(int i) {
		funcion = i;
	}

	/**
	 * @param time
	 */
	public void setHoraTransaccion(Time time) {
		horaTransaccion = time;
	}

	public long getRutAfiliado() {
		return rutAfiliado;
	}

	public void setRutAfiliado(long rutAfiliado) {
		this.rutAfiliado = rutAfiliado;
	}

	public long getRutEmpresa() {
		return rutEmpresa;
	}

	public void setRutEmpresa(long rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

	public long getRutUsuario() {
		return rutUsuario;
	}

	public void setRutUsuario(long rutUsuario) {
		this.rutUsuario = rutUsuario;
	}

}
