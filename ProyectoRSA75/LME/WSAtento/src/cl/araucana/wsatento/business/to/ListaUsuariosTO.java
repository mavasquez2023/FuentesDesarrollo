package cl.araucana.wsatento.business.to;

import java.util.ArrayList;
import java.util.Date;


public class ListaUsuariosTO extends ArrayList{
	private Date ultimaActualizacion;

	public ListaUsuariosTO(){
		super();
	}
	public ListaUsuariosTO(Date ultimaActualizacion){
		super();
		this.ultimaActualizacion = ultimaActualizacion;
	}
	
	public Date getUltimaActualizacion() {
		return ultimaActualizacion;
	}
	public void setUltimaActualizacion(Date ultimaActualizacion) {
		this.ultimaActualizacion = ultimaActualizacion;
	}
}
