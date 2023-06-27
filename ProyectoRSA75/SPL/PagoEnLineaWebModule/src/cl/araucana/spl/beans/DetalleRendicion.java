package cl.araucana.spl.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase utilizada en preview de rendiciones (no es un access bean).
 * @author malvarez
 *
 */
public class DetalleRendicion {
	private String idPago;
	private String detalle;
	
	private List listErrorImportacion = new ArrayList();
	private List listErrorInconsistente = new ArrayList();

	
	public String toString() {
		return new StringBuffer("[DETALLERENDICION::idPago=").append(idPago)
			.append(",detalle=").append(detalle)
			.append(",listErrorImportacion=").append(listErrorImportacion)
			.append(",listErrorInconsistente=").append(listErrorInconsistente)
			.append("]").toString();
	}


	/**
	 * @return the detalle
	 */
	public String getDetalle() {
		return detalle;
	}


	/**
	 * @param detalle the detalle to set
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}


	/**
	 * @return the idPago
	 */
	public String getIdPago() {
		return idPago;
	}


	/**
	 * @param idPago the idPago to set
	 */
	public void setIdPago(String idPago) {
		this.idPago = idPago;
	}


	/**
	 * @return the listErrorImportacion
	 */
	public List getListErrorImportacion() {
		return listErrorImportacion;
	}


	/**
	 * @param listErrorImportacion the listErrorImportacion to set
	 */
	public void setListErrorImportacion(List listErrorImportacion) {
		this.listErrorImportacion = listErrorImportacion;
	}


	/**
	 * @return the listErrorInconsistente
	 */
	public List getListErrorInconsistente() {
		return listErrorInconsistente;
	}


	/**
	 * @param listErrorInconsistente the listErrorInconsistente to set
	 */
	public void setListErrorInconsistente(List listErrorInconsistente) {
		this.listErrorInconsistente = listErrorInconsistente;
	}	
}
