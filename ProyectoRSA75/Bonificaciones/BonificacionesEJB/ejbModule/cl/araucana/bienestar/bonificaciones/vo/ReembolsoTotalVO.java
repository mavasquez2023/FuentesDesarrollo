package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class ReembolsoTotalVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	private long codigo=0;
	private Date fecha=null;
	private long folioEgresoBienestar=0;
	private long folioIngresoBienestar=0;
	private long folioIngresoAraucana=0;
	private int total=0;
	private String usuario = null;
	private boolean mailEnviado = false;
	

	/**
	 * @return
	 */
	public long getCodigo() {
		return codigo;
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
	 * @return
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param l
	 */
	public void setCodigo(long l) {
		codigo = l;
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
	 * @param i
	 */
	public void setTotal(int i) {
		total = i;
	}

	/**
	 * @return
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param string
	 */
	public void setUsuario(String string) {
		usuario = string;
	}

	public boolean isMailEnviado() {
		return mailEnviado;
	}

	public void setMailEnviado(boolean mailEnviado) {
		this.mailEnviado = mailEnviado;
	}

}
