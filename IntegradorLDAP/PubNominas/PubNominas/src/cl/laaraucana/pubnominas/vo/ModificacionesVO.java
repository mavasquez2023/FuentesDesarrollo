/**
 * 
 */
package cl.laaraucana.pubnominas.vo;

import java.util.List;

import cl.laaraucana.pubnominas.dto.asfam.AutorizacionesMODDto;
import cl.laaraucana.pubnominas.dto.asfam.CabeceraMODDto;
import cl.laaraucana.pubnominas.dto.asfam.PendientesMODDto;
import cl.laaraucana.pubnominas.dto.asfam.SuspensionesMODDto;



/**
 * @author IBM Software Factory
 *
 */
public class ModificacionesVO {
	CabeceraMODDto cabecera;
	List<AutorizacionesMODDto> autorizaciones;
	List<SuspensionesMODDto> suspensiones;
	List<PendientesMODDto> pendientes;
	int cantAutorizaciones;
	int desdeAutorizaciones;
	int hastaAutorizaciones;
	int cantSuspensiones;
	int desdeSuspensiones;
	int hastaSuspensiones;
	int cantPendientes;
	int desdePendientes;
	int hastaPendientes;
	/**
	 * @return the cabecera
	 */
	public CabeceraMODDto getCabecera() {
		return cabecera;
	}
	/**
	 * @param cabecera the cabecera to set
	 */
	public void setCabecera(CabeceraMODDto cabecera) {
		this.cabecera = cabecera;
	}
	/**
	 * @return the autorizaciones
	 */
	public List<AutorizacionesMODDto> getAutorizaciones() {
		return autorizaciones;
	}
	/**
	 * @param autorizaciones the autorizaciones to set
	 */
	public void setAutorizaciones(List<AutorizacionesMODDto> autorizaciones) {
		this.autorizaciones = autorizaciones;
	}
	/**
	 * @return the suspensiones
	 */
	public List<SuspensionesMODDto> getSuspensiones() {
		return suspensiones;
	}
	/**
	 * @param suspensiones the suspensiones to set
	 */
	public void setSuspensiones(List<SuspensionesMODDto> suspensiones) {
		this.suspensiones = suspensiones;
	}
	/**
	 * @return the pendientes
	 */
	public List<PendientesMODDto> getPendientes() {
		return pendientes;
	}
	/**
	 * @param pendientes the pendientes to set
	 */
	public void setPendientes(List<PendientesMODDto> pendientes) {
		this.pendientes = pendientes;
	}
	/**
	 * @return the cantAutorizaciones
	 */
	public int getCantAutorizaciones() {
		return cantAutorizaciones;
	}
	/**
	 * @param cantAutorizaciones the cantAutorizaciones to set
	 */
	public void setCantAutorizaciones(int cantAutorizaciones) {
		this.cantAutorizaciones = cantAutorizaciones;
	}
	/**
	 * @return the desdeAutorizaciones
	 */
	public int getDesdeAutorizaciones() {
		return desdeAutorizaciones;
	}
	/**
	 * @param desdeAutorizaciones the desdeAutorizaciones to set
	 */
	public void setDesdeAutorizaciones(int desdeAutorizaciones) {
		this.desdeAutorizaciones = desdeAutorizaciones;
	}
	/**
	 * @return the hastaAutorizaciones
	 */
	public int getHastaAutorizaciones() {
		return hastaAutorizaciones;
	}
	/**
	 * @param hastaAutorizaciones the hastaAutorizaciones to set
	 */
	public void setHastaAutorizaciones(int hastaAutorizaciones) {
		this.hastaAutorizaciones = hastaAutorizaciones;
	}
	/**
	 * @return the cantSuspensiones
	 */
	public int getCantSuspensiones() {
		return cantSuspensiones;
	}
	/**
	 * @param cantSuspensiones the cantSuspensiones to set
	 */
	public void setCantSuspensiones(int cantSuspensiones) {
		this.cantSuspensiones = cantSuspensiones;
	}
	/**
	 * @return the desdeSuspensiones
	 */
	public int getDesdeSuspensiones() {
		return desdeSuspensiones;
	}
	/**
	 * @param desdeSuspensiones the desdeSuspensiones to set
	 */
	public void setDesdeSuspensiones(int desdeSuspensiones) {
		this.desdeSuspensiones = desdeSuspensiones;
	}
	/**
	 * @return the hastaSuspensiones
	 */
	public int getHastaSuspensiones() {
		return hastaSuspensiones;
	}
	/**
	 * @param hastaSuspensiones the hastaSuspensiones to set
	 */
	public void setHastaSuspensiones(int hastaSuspensiones) {
		this.hastaSuspensiones = hastaSuspensiones;
	}
	/**
	 * @return the cantPendientes
	 */
	public int getCantPendientes() {
		return cantPendientes;
	}
	/**
	 * @param cantPendientes the cantPendientes to set
	 */
	public void setCantPendientes(int cantPendientes) {
		this.cantPendientes = cantPendientes;
	}
	/**
	 * @return the desdePendientes
	 */
	public int getDesdePendientes() {
		return desdePendientes;
	}
	/**
	 * @param desdePendientes the desdePendientes to set
	 */
	public void setDesdePendientes(int desdePendientes) {
		this.desdePendientes = desdePendientes;
	}
	/**
	 * @return the hastaPendientes
	 */
	public int getHastaPendientes() {
		return hastaPendientes;
	}
	/**
	 * @param hastaPendientes the hastaPendientes to set
	 */
	public void setHastaPendientes(int hastaPendientes) {
		this.hastaPendientes = hastaPendientes;
	}
	
	
}
