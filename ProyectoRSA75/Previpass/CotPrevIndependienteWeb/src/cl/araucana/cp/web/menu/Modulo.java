package cl.araucana.cp.web.menu;

import java.util.ArrayList;
import java.util.List;

public class Modulo 
{
	private String nombre;
	private String url;
	private String accion;
	private String rolesPermitidos;

	private List tabs = new ArrayList();

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getAccion() {
		return this.accion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List getTabs() {
		return this.tabs;
	}

	public void setTabs(List tabs) {
		this.tabs = tabs;
	}
	public void addTab(Tab tab) {
		this.tabs.add(tab);
	}
	
	public String toString() {
		//return this.ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
		/*private String nombre;
		private String url;
		private String accion;
		private String rolesPermitidos;*/
		return "Modulo["
			+ "nombre : \"" + this.nombre
			+ "\", url : \"" + this.url;
	}

	public String getRolesPermitidos()
	{
		return this.rolesPermitidos;
	}

	public void setRolesPermitidos(String rolesPermitidos)
	{
		this.rolesPermitidos = rolesPermitidos;
	}
}

