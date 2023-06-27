package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class DetalleDescuentosOficinaVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	private long codigoDescuento=0;
	private String codigoOficina=null;
	private String oficina=null;
	private int montoTotal=0;
	private int filas=0;
	private ArrayList detalleSocios=new ArrayList(); //DetalleDescuentosSocioVO




	/**
	 * @return
	 */
	public String getCodigoOficina() {
		return codigoOficina;
	}

	/**
	 * @return
	 */
	public ArrayList getDetalleSocios() {
		return detalleSocios;
	}

	/**
	 * @return
	 */
	public int getFilas() {
		return filas;
	}

	/**
	 * @return
	 */
	public int getMontoTotal() {
		return montoTotal;
	}

	/**
	 * @return
	 */
	public String getOficina() {
		return oficina;
	}

	/**
	 * @param string
	 */
	public void setCodigoOficina(String string) {
		codigoOficina = string;
	}

	/**
	 * @param list
	 */
	public void setDetalleSocios(ArrayList list) {
		detalleSocios = list;
	}

	/**
	 * @param i
	 */
	public void setFilas(int i) {
		filas = i;
	}

	/**
	 * @param i
	 */
	public void setMontoTotal(int i) {
		montoTotal = i;
	}

	/**
	 * @param string
	 */
	public void setOficina(String string) {
		oficina = string;
	}

	/**
	 * @return
	 */
	public long getCodigoDescuento() {
		return codigoDescuento;
	}

	/**
	 * @param l
	 */
	public void setCodigoDescuento(long l) {
		codigoDescuento = l;
	}

}
