package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class ResultUpLoadFileVO implements Serializable{
		
	/** Serial */
	private static final long serialVersionUID = 1L;

	private long codigoConvenio=0;
	private long codigoProducto=0;
	private String convenio=null;
	private String producto=null;
	private String usuario=null;
	private String fileName=null;
	private Date fechaProceso=null;
	private Collection resultado=new ArrayList(); //ResultLineVO

	/**
	 * @return
	 */
	public Date getFechaProceso() {
		return fechaProceso;
	}

	/**
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @return
	 */
	public Collection getResultado() {
		return resultado;
	}

	/**
	 * @return
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param date
	 */
	public void setFechaProceso(Date date) {
		fechaProceso = date;
	}

	/**
	 * @param string
	 */
	public void setFileName(String string) {
		fileName = string;
	}

	/**
	 * @param collection
	 */
	public void setResultado(Collection collection) {
		resultado = collection;
	}

	/**
	 * @param string
	 */
	public void setUsuario(String string) {
		usuario = string;
	}

	/**
	 * @return
	 */
	public long getCodigoConvenio() {
		return codigoConvenio;
	}

	/**
	 * @return
	 */
	public long getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @return
	 */
	public String getConvenio() {
		return convenio;
	}

	/**
	 * @return
	 */
	public String getProducto() {
		return producto;
	}

	/**
	 * @param l
	 */
	public void setCodigoConvenio(long l) {
		codigoConvenio = l;
	}

	/**
	 * @param l
	 */
	public void setCodigoProducto(long l) {
		codigoProducto = l;
	}

	/**
	 * @param string
	 */
	public void setConvenio(String string) {
		convenio = string;
	}

	/**
	 * @param string
	 */
	public void setProducto(String string) {
		producto = string;
	}

}
