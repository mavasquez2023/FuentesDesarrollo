package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class InformeReembolsosVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	private long codigo=0;
	private Date fecha=null;
	private long folioEgresoBienestar=0;
	private long folioIngresoBienestar=0;
	private long folioIngresoAraucana=0;
	private int totalReembolso=0;
	private int totalFilas=0;
	private ArrayList detalleInforme=new ArrayList();

	

	public InformeReembolsosVO () {
	}

	public InformeReembolsosVO (ReembolsoTotalVO reeTot) {
		codigo=reeTot.getCodigo();
		fecha=reeTot.getFecha();
		folioEgresoBienestar=reeTot.getFolioEgresoBienestar();
		folioIngresoBienestar=reeTot.getFolioIngresoBienestar();
		folioIngresoAraucana=reeTot.getFolioIngresoAraucana();
		totalReembolso=reeTot.getTotal();
	}

	/**
	 * @return
	 */
	public long getCodigo() {
		return codigo;
	}

	/**
	 * @return
	 */
	public ArrayList getDetalleInforme() {
		return detalleInforme;
	}

	/**
	 * @return
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @return
	 */
	public long getFolioEgresoBienestar() {
		return folioEgresoBienestar;
	}

	/**
	 * @return
	 */
	public long getFolioIngresoAraucana() {
		return folioIngresoAraucana;
	}

	/**
	 * @return
	 */
	public long getFolioIngresoBienestar() {
		return folioIngresoBienestar;
	}

	/**
	 * @param l
	 */
	public void setCodigo(long l) {
		codigo = l;
	}

	/**
	 * @param list
	 */
	public void setDetalleInforme(ArrayList list) {
		detalleInforme = list;
	}

	/**
	 * @param date
	 */
	public void setFecha(Date date) {
		fecha = date;
	}

	/**
	 * @param l
	 */
	public void setFolioEgresoBienestar(long l) {
		folioEgresoBienestar = l;
	}

	/**
	 * @param l
	 */
	public void setFolioIngresoAraucana(long l) {
		folioIngresoAraucana = l;
	}

	/**
	 * @param l
	 */
	public void setFolioIngresoBienestar(long l) {
		folioIngresoBienestar = l;
	}

	/**
	 * @return
	 */
	public int getTotalFilas() {
		return totalFilas;
	}

	/**
	 * @return
	 */
	public int getTotalReembolso() {
		return totalReembolso;
	}

	/**
	 * @param i
	 */
	public void setTotalFilas(int i) {
		totalFilas = i;
	}

	/**
	 * @param i
	 */
	public void setTotalReembolso(int i) {
		totalReembolso = i;
	}

}
