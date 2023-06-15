package cl.araucana.tupla2.struts.bussiness.TO;

import org.apache.struts.action.ActionForm;

/**
 * Informacion de extincion desde los sistemas DB2 de La Araucana
 * @author Daniel Sepulveda
 *
 */
public class DatosExtinsionTO extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4306843056256382660L;

	public DatosExtinsionTO() {

	}

	private String id;
	private String fechaEmision;
	private String fechaExtincion;
	private String codigoExtincion;
	private String rutCausante;
	private String fechaRec;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getFechaExtincion() {
		return fechaExtincion;
	}

	public void setFechaExtincion(String fechaExtincion) {
		this.fechaExtincion = fechaExtincion;
	}

	public String getFechaRec() {
		return fechaRec;
	}

	public void setFechaRec(String fechaRec) {
		this.fechaRec = fechaRec;
	}

	public String getCodigoExtincion() {
		return codigoExtincion;
	}

	public void setCodigoExtincion(String codigoExtioncion) {
		this.codigoExtincion = codigoExtioncion;
	}

	public String getRutCausante() {
		return rutCausante;
	}

	public void setRutCausante(String rutCausante) {
		this.rutCausante = rutCausante;
	}

}
