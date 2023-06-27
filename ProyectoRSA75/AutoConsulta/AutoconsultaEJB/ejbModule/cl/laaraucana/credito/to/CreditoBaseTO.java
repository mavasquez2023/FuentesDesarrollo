package cl.laaraucana.credito.to;

import java.io.Serializable;

public class CreditoBaseTO implements Serializable{
	protected String oficina; 
	protected long folio;
	protected int estado;
	/**
	 * @return el estado
	 */
	public int getEstado() {
		return estado;
	}
	/**
	 * @param estado el estado a establecer
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	/**
	 * @return el folio
	 */
	public long getFolio() {
		return folio;
	}
	/**
	 * @param folio el folio a establecer
	 */
	public void setFolio(long folio) {
		this.folio = folio;
	}
	/**
	 * @return el oficina
	 */
	public String getOficina() {
		return oficina;
	}
	/**
	 * @param oficina el oficina a establecer
	 */
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}

}
