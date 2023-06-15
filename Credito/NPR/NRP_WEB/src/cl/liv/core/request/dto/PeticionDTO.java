package cl.liv.core.request.dto;

import java.util.ArrayList;
import java.util.HashMap;

public class PeticionDTO {

	
	private String idPeticion = null;
	private String metodosAceptados = null;
	private String formatoSalida = null;
	private boolean isArrayList = false;
	private String dtoPrincipal = null;
	private ArrayList procesos = new ArrayList();
	private String sesion = "";
	private boolean usarTransaccion = false;
	private Object objetoTransaccion = null;
	private boolean estadoTransaccion = true;
	private HashMap resultados = new HashMap();
	
	public String getIdPeticion() {
		return idPeticion;
	}
	public void setIdPeticion(String idPeticion) {
		this.idPeticion = idPeticion;
	}
	public String getMetodosAceptados() {
		return metodosAceptados;
	}
	public void setMetodosAceptados(String metodosAceptados) {
		this.metodosAceptados = metodosAceptados;
	}
	public String getFormatoSalida() {
		return formatoSalida;
	}
	public void setFormatoSalida(String formatoSalida) {
		this.formatoSalida = formatoSalida;
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
	public ArrayList getProcesos() {
		return procesos;
	}
	public void setProcesos(ArrayList procesos) {
		this.procesos = procesos;
	}
	public String getSesion() {
		return sesion;
	}
	public void setSesion(String sesion) {
		this.sesion = sesion;
	}
	public HashMap getResultados() {
		return resultados;
	}
	public void setResultados(HashMap resultados) {
		this.resultados = resultados;
	}
	public boolean isUsarTransaccion() {
		return usarTransaccion;
	}
	public void setUsarTransaccion(boolean usarTransaccion) {
		this.usarTransaccion = usarTransaccion;
	}
	public Object getObjetoTransaccion() {
		return objetoTransaccion;
	}
	public void setObjetoTransaccion(Object objetoTransaccion) {
		this.objetoTransaccion = objetoTransaccion;
	}
	public boolean isEstadoTransaccion() {
		return estadoTransaccion;
	}
	public void setEstadoTransaccion(boolean estadoTransaccion) {
		this.estadoTransaccion = estadoTransaccion;
	}
	
	
	public static PeticionDTO clone(PeticionDTO peticionAux){
		PeticionDTO peticion = new PeticionDTO();	
		if(peticionAux == null)
			return peticion;
		peticion.setIdPeticion			(peticionAux.getIdPeticion());
		peticion.setMetodosAceptados	(peticionAux.getMetodosAceptados());
		peticion.setFormatoSalida		(peticionAux.getFormatoSalida());
		peticion.setArrayList			(peticionAux.isArrayList());
		peticion.setDtoPrincipal		(peticionAux.getDtoPrincipal());
		peticion.setSesion				(peticionAux.getSesion());
		peticion.setUsarTransaccion		(peticionAux.isUsarTransaccion());
		peticion.setObjetoTransaccion	(peticionAux.getObjetoTransaccion());
		peticion.setEstadoTransaccion	(peticionAux.isEstadoTransaccion());
		peticion.setResultados			(peticionAux.getResultados());
		
		if(peticionAux.getProcesos() != null && peticionAux.getProcesos().size()> 0){
			for (int i=0; i< peticionAux.getProcesos().size();  i++) {
				ProcesoDTO proceso = (ProcesoDTO)peticionAux.getProcesos().get(i);
				peticion.getProcesos().add( ProcesoDTO.clone(  proceso ) );
			}
		}
	
		return peticion;
	}
	
	
}
