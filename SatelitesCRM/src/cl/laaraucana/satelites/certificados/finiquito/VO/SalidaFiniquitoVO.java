package cl.laaraucana.satelites.certificados.finiquito.VO;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaFiniquitoVO extends AbstractSalidaVO {

	private String folio;
	private String cuotaDesde;
	private String cuotaHasta;
	private double saldoCapital;
	private String tipoCredito;
	private double gravamenes;
	private double montoTotal;
	private String estadoCredito;
	private String cuotasVigentes;
	private String sumaGCob = "";
	private String sumaCuotas = "";
	private String sumaGCobFormato = "";
	private String sumaCuotasFormato = "";
	private double montoPrima;
	private double montoDeudaCapital;
	private double montoSaldoCredito;
	private SalidaListaDetalleFiniquitoVO listaDetalle = new SalidaListaDetalleFiniquitoVO();
	private List<SalidaDetalleFiniquitoVO> listaCuotasUltimos = new ArrayList<SalidaDetalleFiniquitoVO>();

	public SalidaFiniquitoVO() {
	}

	public SalidaFiniquitoVO(String folio, String cuotaDesde, String cuotaHasta, double saldoCapital, String tipoCredito, double gravamenes, String sumaGCob, String sumaCuotas) {
		super();
		this.folio = folio;
		this.cuotaDesde = cuotaDesde;
		this.cuotaHasta = cuotaHasta;
		this.saldoCapital = saldoCapital;
		this.tipoCredito = tipoCredito;
		this.gravamenes = gravamenes;
		this.sumaGCob = sumaGCob;
		this.sumaCuotas = sumaCuotas;

	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getCuotaDesde() {
		return cuotaDesde;
	}

	public void setCuotaDesde(String cuotaDesde) {
		this.cuotaDesde = cuotaDesde;
	}

	public String getCuotaHasta() {
		return cuotaHasta;
	}

	public void setCuotaHasta(String cuotaHasta) {
		this.cuotaHasta = cuotaHasta;
	}

	public double getSaldoCapital() {
		return saldoCapital;
	}

	public void setSaldoCapital(double saldoCapital) {
		this.saldoCapital = saldoCapital;
	}

	public double getGravamenes() {
		return gravamenes;
	}

	public void setGravamenes(double gravamenes) {
		this.gravamenes = gravamenes;
	}

	public String getTipoCredito() {
		return tipoCredito;
	}

	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}

	public String getCuotaCredito() {
		return cuotaDesde + " a la " + cuotaHasta;
	}

	public SalidaListaDetalleFiniquitoVO getListaDetalle() {
		return listaDetalle;
	}

	public void setListaDetalle(SalidaListaDetalleFiniquitoVO listaDetalle) {
		this.listaDetalle = listaDetalle;
	}

	public List<SalidaDetalleFiniquitoVO> getListaCuotasUltimos() {
		return listaCuotasUltimos;
	}

	public void setListaCuotasUltimos(List<SalidaDetalleFiniquitoVO> listaCuotasUltimos) {
		this.listaCuotasUltimos = listaCuotasUltimos;
	}

	public double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public String getEstadoCredito() {
		return estadoCredito;
	}

	public void setEstadoCredito(String estadoCredito) {
		this.estadoCredito = estadoCredito;
	}

	public String getCuotasVigentes() {
		return cuotasVigentes;
	}

	public void setCuotasVigentes(String cuotasVigentes) {
		this.cuotasVigentes = cuotasVigentes;
	}

	public String getSumaGCob() {
		return sumaGCob;
	}

	public void setSumaGCob(String sumaGCob) {
		this.sumaGCob = sumaGCob;
	}

	public String getSumaCuotas() {
		return sumaCuotas;
	}

	public void setSumaCuotas(String sumaCuotas) {
		this.sumaCuotas = sumaCuotas;
	}

	public double getMontoPrima() {
		return montoPrima;
	}

	public void setMontoPrima(double montoPrima) {
		this.montoPrima = montoPrima;
	}

	public double getMontoDeudaCapital() {
		return montoDeudaCapital;
	}

	public void setMontoDeudaCapital(double montoDeudaCapital) {
		this.montoDeudaCapital = montoDeudaCapital;
	}

	public double getMontoSaldoCredito() {
		return montoSaldoCredito;
	}

	public void setMontoSaldoCredito(double montoSaldoCredito) {
		this.montoSaldoCredito = montoSaldoCredito;
	}

	public String getSumaGCobFormato() {
		if (this.sumaGCob != null && this.sumaGCob.length() != 0) {
			return this.sumaGCobFormato = "$" + Utils.formateaDobleSinDecimal(Double.valueOf(this.sumaGCob));
		} else {
			return "";
		}
	}

	public String getSumaCuotasFormato() {
		if (this.sumaCuotas != null && this.sumaCuotas.length() != 0) {
			return this.sumaCuotasFormato = Utils.formateaDobleSinDecimal(Double.valueOf(this.sumaCuotas));
		} else {
			return "";
		}
	}

}
