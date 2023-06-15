package cl.laaraucana.simulacion.webservices.client.CreaCotizacion.VO;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.simulacion.webservices.model.AbstractSalidaVO;

public class CreaCotizacionSalidaVO extends AbstractSalidaVO {
	private String businessPartner;
	private String nroCotizacion;
	private String tasaIntMensual;
	private String tasaIntAnual;
	private String segDesgravamen;
	private String segCesantia;
	private String lte;
	private String gastosNotariales;
	private String cumpleCondiciones;
	private String montoCuotaMax;
	private List<TasasInteresVO> tasasInteresList = new ArrayList<TasasInteresVO>();

	public String getBusinessPartner() {
		return businessPartner;
	}

	public void setBusinessPartner(String businessPartner) {
		this.businessPartner = businessPartner;
	}

	public String getNroCotizacion() {
		return nroCotizacion;
	}

	public void setNroCotizacion(String nroCotizacion) {
		this.nroCotizacion = nroCotizacion;
	}

	public String getTasaIntMensual() {
		return tasaIntMensual;
	}

	public void setTasaIntMensual(String tasaIntMensual) {
		this.tasaIntMensual = tasaIntMensual;
	}

	public String getTasaIntAnual() {
		return tasaIntAnual;
	}

	public void setTasaIntAnual(String tasaIntAnual) {
		this.tasaIntAnual = tasaIntAnual;
	}

	public String getSegDesgravamen() {
		return segDesgravamen;
	}

	public void setSegDesgravamen(String segDesgravamen) {
		this.segDesgravamen = segDesgravamen;
	}

	public String getSegCesantia() {
		return segCesantia;
	}

	public void setSegCesantia(String segCesantia) {
		this.segCesantia = segCesantia;
	}

	public String getLte() {
		return lte;
	}

	public void setLte(String lte) {
		this.lte = lte;
	}

	public String getGastosNotariales() {
		return gastosNotariales;
	}

	public void setGastosNotariales(String gastosNotariales) {
		this.gastosNotariales = gastosNotariales;
	}

	public String getCumpleCondiciones() {
		return cumpleCondiciones;
	}

	public void setCumpleCondiciones(String cumpleCondiciones) {
		this.cumpleCondiciones = cumpleCondiciones;
	}

	public List<TasasInteresVO> getTasasInteresList() {
		return tasasInteresList;
	}

	public void setTasasInteresList(List<TasasInteresVO> tasasInteresList) {
		this.tasasInteresList = tasasInteresList;
	}

	public String getMontoCuotaMax() {
		return montoCuotaMax;
	}

	public void setMontoCuotaMax(String montoCuotaMax) {
		this.montoCuotaMax = montoCuotaMax;
	}
	
	

}
