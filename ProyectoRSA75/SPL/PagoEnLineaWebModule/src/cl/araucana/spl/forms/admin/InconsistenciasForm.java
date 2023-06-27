package cl.araucana.spl.forms.admin;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

import cl.araucana.spl.beans.FiltroInconsistencias;
import cl.araucana.spl.beans.ResumenInconsistencias;
import cl.araucana.spl.util.Renderer;

public class InconsistenciasForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private static Renderer renderer = new Renderer();
	
	private List estados;
	private List bancos;
	private List sistemas;
	private String buscar;
	
	private List inconsistencias;
	private ResumenInconsistencias resumen;

	private FiltroInconsistencias filtro;
	
	public InconsistenciasForm() {
		filtro = new FiltroInconsistencias();
		filtro.setTipoFecha(FiltroInconsistencias.TIPO_FECHA_PAGO);
		// filtro.setEstado(Constants.ESTADO_PAGO_INCONSISTENTE);
		Date now = new Date(System.currentTimeMillis());
		filtro.setFechaPagoDesde(now);
		filtro.setFechaPagoHasta(now);
		filtro.setFechaContableDesde(now);
		filtro.setFechaContableHasta(now);
		filtro.setTrxConsiderar("todas");
	}
	
	public List getBancos() {
		return bancos;
	}

	public void setBancos(List bancos) {
		this.bancos = bancos;
	}

	public String getBuscar() {
		return buscar;
	}

	public void setBuscar(String buscar) {
		this.buscar = buscar;
	}

	public List getEstados() {
		return estados;
	}

	public void setEstados(List estados) {
		this.estados = estados;
	}

	public List getSistemas() {
		return sistemas;
	}

	public void setSistemas(List sistemas) {
		this.sistemas = sistemas;
	}

	public FiltroInconsistencias getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroInconsistencias filtro) {
		this.filtro = filtro;
	}

	public String getBanco() {
		return renderer.formatSimple(filtro.getBanco());
	}

	public String getEstado() {
		return renderer.formatSimple(filtro.getEstado());
	}

	public String getFechaContableDesde() {
		return filtro.getStrFechaContableDesde();
	}

	public String getFechaContableHasta() {
		return filtro.getStrFechaContableHasta();
	}

	public String getFechaPagoDesde() {
		return filtro.getStrFechaPagoDesde();
	}

	public String getFechaPagoHasta() {
		return filtro.getStrFechaPagoHasta();
	}

	public String getNumeroFolio() {
		return renderer.formatSimple(filtro.getNumeroFolio());
	}

	public String getNumeroPago() {
		return renderer.formatSimple(filtro.getNumeroPago());
	}

	public String getSistema() {
		return renderer.formatSimple(filtro.getSistema());
	}

	public String getTipoFecha() {
		return filtro.getTipoFecha();
	}

	public void setBanco(String banco) {
		filtro.setBanco(renderer.parseBigDecimal(banco));
	}

	public void setEstado(String estado)  {
		filtro.setEstado(renderer.parseBigDecimal(estado));
	}

	
	
	
	
	public void setFechaContableDesde(String fechaContableDesde) throws ParseException {
		filtro.setFechaContableDesde(renderer.parseDate(fechaContableDesde));
	}

	public void setFechaContableHasta(String fechaContableHasta) throws ParseException {
		filtro.setFechaContableHasta(renderer.parseDate(fechaContableHasta));
	}

	public void setFechaPagoDesde(String fechaPagoDesde) throws ParseException {
		filtro.setFechaPagoDesde(renderer.parseDate(fechaPagoDesde));
	}

	public void setFechaPagoHasta(String fechaPagoHasta) throws ParseException {
		filtro.setFechaPagoHasta(renderer.parseDate(fechaPagoHasta));
	}

	public void setNumeroFolio(String numeroFolio) {
		filtro.setNumeroFolio(renderer.parseBigDecimal(numeroFolio));
	}

	public void setNumeroPago(String numeroPago) {
		filtro.setNumeroPago(renderer.parseBigDecimal(numeroPago));
	}

	
	public void setSistema(String sistema) {
		filtro.setSistema(renderer.parseBigDecimal(sistema));
	}

	public void setTipoFecha(String tipoFecha) {
		filtro.setTipoFecha(tipoFecha);
	}

	public List getInconsistencias() {
		return inconsistencias;
	}

	public void setInconsistencias(List inconsistencias) {
		this.inconsistencias = inconsistencias;
	}

	public ResumenInconsistencias getResumen() {
		return resumen;
	}

	public void setResumen(ResumenInconsistencias resumen) {
		this.resumen = resumen;
	}

	public String getTrxConsiderar() {
		return filtro.getTrxConsiderar();
	}

	public void setTrxConsiderar(String trxConsiderar) {
		filtro.setTrxConsiderar(trxConsiderar);
	}
	
	
}
