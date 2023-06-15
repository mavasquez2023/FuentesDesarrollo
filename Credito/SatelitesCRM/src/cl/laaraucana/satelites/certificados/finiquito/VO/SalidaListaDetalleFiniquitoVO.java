package cl.laaraucana.satelites.certificados.finiquito.VO;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaListaDetalleFiniquitoVO extends AbstractSalidaVO {
	private String nroCuenta;
	private String lineaComercial;
	private String cantidadTotalCuotas;
	private String cuotaDesde;
	private String nroCuotaActual;

	public String getNroCuotaActual() {
		return nroCuotaActual;
	}

	public void setNroCuotaActual(String nroCuotaActual) {
		this.nroCuotaActual = nroCuotaActual;
	}

	private List<SalidaDetalleFiniquitoVO> listaCuotas = new ArrayList<SalidaDetalleFiniquitoVO>();

	public SalidaListaDetalleFiniquitoVO() {
	}

	public SalidaListaDetalleFiniquitoVO(List<SalidaDetalleFiniquitoVO> listaCuotas) {
		super();
		this.listaCuotas = listaCuotas;
	}

	public List<SalidaDetalleFiniquitoVO> getListaCuotas() {
		return listaCuotas;
	}

	public void setListaCuotas(List<SalidaDetalleFiniquitoVO> listaCuotas) {
		this.listaCuotas = listaCuotas;
	}

	public String getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public String getLineaComercial() {
		return lineaComercial;
	}

	public void setLineaComercial(String lineaComercial) {
		this.lineaComercial = lineaComercial;
	}

	public String getCantidadTotalCuotas() {
		return cantidadTotalCuotas;
	}

	public void setCantidadTotalCuotas(String cantidadTotalCuotas) {
		this.cantidadTotalCuotas = cantidadTotalCuotas;
	}

	public String getCuotaDesde() {
		return cuotaDesde;
	}

	public void setCuotaDesde(String cuotaDesde) {
		this.cuotaDesde = cuotaDesde;
	}

}
