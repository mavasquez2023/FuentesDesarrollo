package cl.araucana.tesoreria.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class Detalle implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	private int item=0;
	private String identificadorSubsistema="";
	private int montoDetalle=0;
	private String observaciónMovimientoDetalle="";
	private int montoPagoEfectivo=0;
	private String documentoRespaldo="";
	private int cantidadDocumentos=0;
	private int montoPagoCheque=0;
	private int numeroCaratula=0;
	private int codigoConcepto=0;
	private long folioMovimiento=0;
	private Date creationDate=null;
	private Date creationTime=null;
	private Date lastChangeDate=null;
	private Date lastChangeTime=null;
	private String creationUser="";
	private String lastChangeUser="";


	/**
	 * @return
	 */
	public int getCantidadDocumentos() {
		return cantidadDocumentos;
	}

	/**
	 * @return
	 */
	public int getCodigoConcepto() {
		return codigoConcepto;
	}

	/**
	 * @return
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @return
	 */
	public Date getCreationTime() {
		return creationTime;
	}

	/**
	 * @return
	 */
	public String getCreationUser() {
		return creationUser;
	}

	/**
	 * @return
	 */
	public String getDocumentoRespaldo() {
		return documentoRespaldo;
	}

	/**
	 * @return
	 */
	public long getFolioMovimiento() {
		return folioMovimiento;
	}


	/**
	 * @return
	 */
	public int getItem() {
		return item;
	}

	/**
	 * @return
	 */
	public Date getLastChangeDate() {
		return lastChangeDate;
	}

	/**
	 * @return
	 */
	public Date getLastChangeTime() {
		return lastChangeTime;
	}

	/**
	 * @return
	 */
	public String getLastChangeUser() {
		return lastChangeUser;
	}

	/**
	 * @return
	 */
	public int getMontoDetalle() {
		return montoDetalle;
	}

	/**
	 * @return
	 */
	public int getMontoPagoCheque() {
		return montoPagoCheque;
	}

	/**
	 * @return
	 */
	public int getMontoPagoEfectivo() {
		return montoPagoEfectivo;
	}

	/**
	 * @return
	 */
	public int getNumeroCaratula() {
		return numeroCaratula;
	}

	/**
	 * @return
	 */
	public String getObservaciónMovimientoDetalle() {
		return observaciónMovimientoDetalle;
	}

	/**
	 * @param i
	 */
	public void setCantidadDocumentos(int i) {
		cantidadDocumentos = i;
	}

	/**
	 * @param i
	 */
	public void setCodigoConcepto(int i) {
		codigoConcepto = i;
	}

	/**
	 * @param date
	 */
	public void setCreationDate(Date date) {
		creationDate = date;
	}

	/**
	 * @param date
	 */
	public void setCreationTime(Date date) {
		creationTime = date;
	}

	/**
	 * @param string
	 */
	public void setCreationUser(String string) {
		creationUser = string;
	}

	/**
	 * @param string
	 */
	public void setDocumentoRespaldo(String string) {
		documentoRespaldo = string;
	}

	/**
	 * @param l
	 */
	public void setFolioMovimiento(long l) {
		folioMovimiento = l;
	}

	/**
	 * @param i
	 */
	public void setItem(int i) {
		item = i;
	}

	/**
	 * @param date
	 */
	public void setLastChangeDate(Date date) {
		lastChangeDate = date;
	}

	/**
	 * @param date
	 */
	public void setLastChangeTime(Date date) {
		lastChangeTime = date;
	}

	/**
	 * @param string
	 */
	public void setLastChangeUser(String string) {
		lastChangeUser = string;
	}

	/**
	 * @param i
	 */
	public void setMontoDetalle(int i) {
		montoDetalle = i;
	}

	/**
	 * @param i
	 */
	public void setMontoPagoCheque(int i) {
		montoPagoCheque = i;
	}

	/**
	 * @param i
	 */
	public void setMontoPagoEfectivo(int i) {
		montoPagoEfectivo = i;
	}

	/**
	 * @param i
	 */
	public void setNumeroCaratula(int i) {
		numeroCaratula = i;
	}

	/**
	 * @param string
	 */
	public void setObservaciónMovimientoDetalle(String string) {
		observaciónMovimientoDetalle = string;
	}

	/**
	 * @return
	 */
	public String getIdentificadorSubsistema() {
		return identificadorSubsistema;
	}

	/**
	 * @param string
	 */
	public void setIdentificadorSubsistema(String string) {
		identificadorSubsistema = string;
	}

}
