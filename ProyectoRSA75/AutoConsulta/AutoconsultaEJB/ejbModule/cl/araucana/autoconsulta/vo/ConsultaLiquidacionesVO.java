/*
 * Creado el 18-11-2005
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author USIST15
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class ConsultaLiquidacionesVO implements Serializable{

	private LiquidacionesVO  liquidacion= null;
	private DatosTrabajadoresLiquidacionesVO trabajador= null;
	private Collection movimientos= null;
	private double montoRejuste= 0;


	

	/**
	 * @return
	 */
	public LiquidacionesVO getLiquidacion() {
		return liquidacion;
	}

	/**
	 * @return
	 */
	public Collection getMovimientos() {
		return movimientos;
	}

	/**
	 * @return
	 */
	public DatosTrabajadoresLiquidacionesVO getTrabajador() {
		return trabajador;
	}

	/**
	 * @param liquidacionesVO
	 */
	public void setLiquidacion(LiquidacionesVO liquidacionesVO) {
		liquidacion = liquidacionesVO;
	}

	/**
	 * @param liquidacionesVO
	 */
	public void setTrabajador(DatosTrabajadoresLiquidacionesVO liquidacionesVO) {
		trabajador = liquidacionesVO;
	}

	/**
	 * @param collection
	 */
	public void setMovimientos(Collection collection) {
		movimientos = collection;
	}

	/**
	 * @return
	 */
	public double getMontoRejuste() {
		return montoRejuste;
	}

	/**
	 * @param d
	 */
	public void setMontoRejuste(double d) {
		montoRejuste = d;
	}

}
