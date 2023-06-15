package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class InformacionCreditoVO implements Serializable {
	
	// Tipo Credito
	public static final int CREDITO_DIRECTO=0;
	public static final int CREDITO_INDIRECTO=1;
	
	private long rutTitular=0;
	private int estado=0;
	private int tipoDeuda=0;
	private String oficina=null;
	private String folio=null;
	private int montoCuota=0;
	private int tipoOperacion=0;
	private int cuotasImpagas=0;
	private int cuotasVigentes=0;
	private int totalMontoAbonado=0;
	private String vencimiento=null;
	private int cuotaInicial=0;
	private int cuotaFinal=0;
	private int saldoImpago=0;
	private int saldoVigente=0;
	private int saldoTotal=0;
	private DatosTitularCreditoVO datosTitular =null;
	private Collection cuotas = new ArrayList(); //CuotaCreditoVO

	
	/**
	 * Devuelve si la deuda es nominal
	 * @return
	 */
	public boolean isDeudaNominal() {
		if (cuotasImpagas > 0 && tipoDeuda==CREDITO_INDIRECTO)
			return true;
		else
			return false;
	}

	
	/**
	 * Devuelve oficina-folio
	 * @return
	 */
	public String getOficinaFolio() {
		return oficina + "-" + folio;
	}

	/**
	 * Devuelve el rango de cuotas vigentes
	 * @return
	 */
	public String getRangoCuotasVigentes() {
		return cuotaInicial + " - " + cuotaFinal;
	}

	/**
	 * @return
	 */
	public int getEstado() {
		return estado;
	}

	/**
	 * @return
	 */
	public String getFolio() {
		return folio;
	}

	/**
	 * @return
	 */
	public int getMontoCuota() {
		return montoCuota;
	}

	/**
	 * @return
	 */
	public String getOficina() {
		return oficina;
	}

	/**
	 * @return
	 */
	public int getTipoDeuda() {
		return tipoDeuda;
	}

	/**
	 * @return
	 */
	public int getTipoOperacion() {
		return tipoOperacion;
	}

	/**
	 * @param i
	 */
	public void setEstado(int i) {
		estado = i;
	}

	/**
	 * @param string
	 */
	public void setFolio(String string) {
		folio = string;
	}

	/**
	 * @param i
	 */
	public void setMontoCuota(int i) {
		montoCuota = i;
	}

	/**
	 * @param string
	 */
	public void setOficina(String string) {
		oficina = string;
	}

	/**
	 * @param i
	 */
	public void setTipoDeuda(int i) {
		tipoDeuda = i;
	}

	/**
	 * @param i
	 */
	public void setTipoOperacion(int i) {
		tipoOperacion = i;
	}

	/**
	 * @return
	 */
	public DatosTitularCreditoVO getDatosTitular() {
		return datosTitular;
	}

	/**
	 * @param creditoVO
	 */
	public void setDatosTitular(DatosTitularCreditoVO creditoVO) {
		datosTitular = creditoVO;
	}

	/**
	 * @return
	 */
	public Collection getCuotas() {
		return cuotas;
	}

	/**
	 * @param collection
	 */
	public void setCuotas(Collection collection) {
		cuotas = collection;
	}

	/**
	 * @return
	 */
	public int getCuotasImpagas() {
		return cuotasImpagas;
	}

	/**
	 * @return
	 */
	public int getCuotasVigentes() {
		return cuotasVigentes;
	}

	/**
	 * @param i
	 */
	public void setCuotasImpagas(int i) {
		cuotasImpagas = i;
	}

	/**
	 * @param i
	 */
	public void setCuotasVigentes(int i) {
		cuotasVigentes = i;
	}

	/**
	 * @return
	 */
	public int getTotalMontoAbonado() {
		return totalMontoAbonado;
	}

	/**
	 * @param i
	 */
	public void setTotalMontoAbonado(int i) {
		totalMontoAbonado = i;
	}

	/**
	 * @return
	 */
	public String getVencimiento() {
		return vencimiento;
	}

	/**
	 * @param string
	 */
	public void setVencimiento(String string) {
		vencimiento = string;
	}

	/**
	 * @return
	 */
	public int getCuotaFinal() {
		return cuotaFinal;
	}

	/**
	 * @return
	 */
	public int getCuotaInicial() {
		return cuotaInicial;
	}

	/**
	 * @param i
	 */
	public void setCuotaFinal(int i) {
		cuotaFinal = i;
	}

	/**
	 * @param i
	 */
	public void setCuotaInicial(int i) {
		cuotaInicial = i;
	}

	/**
	 * @return
	 */
	public int getSaldoImpago() {
		return saldoImpago;
	}

	/**
	 * @return
	 */
	public int getSaldoTotal() {
		return saldoTotal;
	}

	/**
	 * @return
	 */
	public int getSaldoVigente() {
		return saldoVigente;
	}

	/**
	 * @param i
	 */
	public void setSaldoImpago(int i) {
		saldoImpago = i;
	}

	/**
	 * @param i
	 */
	public void setSaldoTotal(int i) {
		saldoTotal = i;
	}

	/**
	 * @param i
	 */
	public void setSaldoVigente(int i) {
		saldoVigente = i;
	}

	/**
	 * @return
	 */
	public long getRutTitular() {
		return rutTitular;
	}

	/**
	 * @param l
	 */
	public void setRutTitular(long l) {
		rutTitular = l;
	}


}
