package cl.araucana.bienestar.bonificaciones.model;

import java.io.Serializable;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class Parametro implements Serializable{
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	
	public static final int TIPO_CONCEPTO_TESORERIA_BIENESTAR_CONVENIOS_INGRESOS=0;
	public static final int TIPO_CONCEPTO_TESORERIA_BIENESTAR_CONVENIOS_EGRESOS=1;
	public static final int TIPO_CONCEPTO_TESORERIA_BIENESTAR_SALUD_INGRESOS=2;
	public static final int TIPO_CONCEPTO_TESORERIA_BIENESTAR_SALUD_EGRESOS=3;
	public static final int TIPO_CUENTA_CONTABLE=4;
	public static final int TIPO_AREA_TESORERIA=5;
	public static final int TIPO_SALDO_DEUDA_TOTAL = 6;
	
	public static final String SUB_TIPO_TODOS="*";
		
	//La descripción debe corresponder a la existente
	//en el archivo arau-settings.xml
	public static final String SUB_TIPO_CUENTAS_DEUDORES="deudor";
	public static final String SUB_TIPO_CUENTAS_ACREEDORES="acreedor";
	public static final String SUB_TIPO_CUENTAS_PROVISIONES="provision";
	public static final String SUB_TIPO_CUENTAS_EGRESOS="egreso";
	
	public static final String SUB_TIPO_AREA_SELECCIONABLE="1";

	private String tipo=null;
	private String codigo=null;
	private String descripcion=null;
	private String codigoPadre=null;


	/**
	 * @return
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param string
	 */
	public void setCodigo(String string) {
		codigo = string;
	}

	/**
	 * @param string
	 */
	public void setDescripcion(String string) {
		descripcion = string;
	}

	/**
	 * @return
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param string
	 */
	public void setTipo(String string) {
		tipo = string;
	}

	/**
	 * @return
	 */
	public String getCodigoPadre() {
		return codigoPadre;
	}

	/**
	 * @param string
	 */
	public void setCodigoPadre(String string) {
		codigoPadre = string;
	}

}
