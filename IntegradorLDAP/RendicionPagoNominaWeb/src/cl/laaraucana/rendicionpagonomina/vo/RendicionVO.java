/**
 * 
 */
package cl.laaraucana.rendicionpagonomina.vo;

import java.util.List;
import java.util.Map;

import cl.laaraucana.rendicionpagonomina.entities.CabeceraEntity;
import cl.laaraucana.rendicionpagonomina.entities.DetalleEntity;

/**
 * @author IBM Software Factory
 *
 */
public class RendicionVO {
	private CabeceraEntity cabecera;
	private List<DetalleEntity> detalles;
	private Map<String, DetalleEntity> mapDetalles;
	/**
	 * @return the cabecera
	 */
	public CabeceraEntity getCabecera() {
		return cabecera;
	}
	/**
	 * @param cabecera the cabecera to set
	 */
	public void setCabecera(CabeceraEntity cabecera) {
		this.cabecera = cabecera;
	}
	/**
	 * @return the detalles
	 */
	public List<DetalleEntity> getDetalles() {
		return detalles;
	}
	/**
	 * @param detalles the detalles to set
	 */
	public void setDetalles(List<DetalleEntity> detalles) {
		this.detalles = detalles;
	}
	/**
	 * @return the mapDetalles
	 */
	public Map<String, DetalleEntity> getMapDetalles() {
		return mapDetalles;
	}
	/**
	 * @param mapDetalles the mapDetalles to set
	 */
	public void setMapDetalles(Map<String, DetalleEntity> mapDetalles) {
		this.mapDetalles = mapDetalles;
	}
	
	
}
