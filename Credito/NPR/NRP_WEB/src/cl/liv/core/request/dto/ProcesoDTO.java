package cl.liv.core.request.dto;

public class ProcesoDTO {
/*
	id="proceso_1" type="reflection" data="test.ServiceDummy"
			data_adicional="implementacionDummyGetPersonas" array_list="true"
			dto_principal="cl.sbs.modelo.dto.PersonaDTO" salida="true"
	*/
	
	private String idProceso = null;
	private String tipo = null;
	private String data = null;
	private String dataAdicional = null;
	private boolean isArrayList = false;
	private String dtoPrincipal = "";
	private boolean isSalida = false;
	private boolean isAsincrono = false;
	private String key = null;
	private String condicion = null;
	private String validarResultado = null;
	
	public String getIdProceso() {
		return idProceso;
	}
	public void setIdProceso(String idProceso) {
		this.idProceso = idProceso;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDataAdicional() {
		return dataAdicional;
	}
	public void setDataAdicional(String dataAdicional) {
		this.dataAdicional = dataAdicional;
	}
	public boolean isArrayList() {
		return isArrayList;
	}
	public void setArrayList(boolean isArrayList) {
		this.isArrayList = isArrayList;
	}
	public String getDtoPrincipal() {
		return dtoPrincipal;
	}
	public void setDtoPrincipal(String dtoPrincipal) {
		this.dtoPrincipal = dtoPrincipal;
	}
	public boolean isSalida() {
		return isSalida;
	}
	public void setSalida(boolean isSalida) {
		this.isSalida = isSalida;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getCondicion() {
		return condicion;
	}
	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}
	public String getValidarResultado() {
		return validarResultado;
	}
	public void setValidarResultado(String validarResultado) {
		this.validarResultado = validarResultado;
	}
	
	
	public boolean isAsincrono() {
		return isAsincrono;
	}
	public void setAsincrono(boolean isAsincrono) {
		this.isAsincrono = isAsincrono;
	}
	public static ProcesoDTO clone(ProcesoDTO _proceso){
		ProcesoDTO proceso = new ProcesoDTO();
		proceso.setIdProceso	(_proceso.getIdProceso());
		proceso.setTipo			(_proceso.getTipo());
		proceso.setData			(_proceso.getData());
		proceso.setDataAdicional(_proceso.getDataAdicional());
		proceso.setKey			(_proceso.getKey());
		proceso.setCondicion	(_proceso.getCondicion());
		proceso.setValidarResultado(_proceso.getValidarResultado());
		proceso.setDtoPrincipal	(_proceso.getDtoPrincipal());
		proceso.setArrayList	(_proceso.isArrayList());
		proceso.setSalida		(_proceso.isSalida());	
		proceso.setAsincrono	(_proceso.isAsincrono());	
		return proceso;
	}
}
