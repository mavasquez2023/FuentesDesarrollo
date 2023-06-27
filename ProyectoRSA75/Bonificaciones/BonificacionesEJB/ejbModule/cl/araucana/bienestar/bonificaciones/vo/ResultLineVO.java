package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class ResultLineVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;
	
	//Resultados Posibles
	public static final int STD_OK_CREADO=0;
	public static final int STD_NOK_CREADO=1;
	public static final int STD_YA_CREADO=2;
	
	//Mensajes
	public static final String MSG_OBSERVACIONES0="La información del registro no es correcta";
	public static final String MSG_OBSERVACIONES1="Caso en borrador sin detalle";
	public static final String MSG_OBSERVACIONES2="Caso en borrador con detalle";
	public static final String MSG_SIN_OBSERVACIONES="Sin Observaciones";
	public static final String MSG_ACTUALIZADO="Complementa información";
	public static final String MSG_CREADO_POR_USUARIO="Creado por el Usuario";
	public static final String MSG_RUT_INVALIDO="El Rut es incorrecto";
	public static final String MSG_BOLETA_INVALIDA="El número de boleta es obligatorio";
	public static final String MSG_MONTO_INVALIDO="El monto de la compra debe ser mayor que cero";
	
		
	private int fila=0;
	private String rut=null;
	private String dv=null;
	private String boleta=MSG_OBSERVACIONES0;
	private int resultado=STD_NOK_CREADO;
	private String mensaje=null;
	private long casoId=0;

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
	public int getFila() {
		return fila;
	}

	/**
	 * @return
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @return
	 */
	public int getResultado() {
		return resultado;
	}

	/**
	 * @return
	 */
	public String getRut() {
		return rut;
	}

	/**
	 * @param string
	 */
	public void setBoleta(String string) {
		boleta = string;
	}

	/**
	 * @param i
	 */
	public void setFila(int i) {
		fila = i;
	}

	/**
	 * @param string
	 */
	public void setMensaje(String string) {
		mensaje = string;
	}

	/**
	 * @param i
	 */
	public void setResultado(int i) {
		resultado = i;
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
	public long getCasoId() {
		return casoId;
	}

	/**
	 * @param l
	 */
	public void setCasoId(long l) {
		casoId = l;
	}

	/**
	 * @return
	 */
	public String getDv() {
		return dv;
	}

	/**
	 * @param string
	 */
	public void setDv(String string) {
		dv = string;
	}

}
