package cl.araucana.cp.web.menu;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Tab 
{
	private String nombre;
	private String url;
	private String subAccion;
	private String onclick;
	private String activar;//que link del submenu activar
	private List subTabs = new ArrayList();
	private String rolesPermitidos;
	private String visible;

	public String getSubAccion() {
		return this.subAccion;
	}
	public void setSubAccion(String subAccion) {
		this.subAccion = subAccion;
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
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	public String getActivar() {
		return this.activar;
	}
	public void setActivar(String activar) {
		this.activar = activar;
	}
	public List getSubtabs() {
		return this.subTabs;
	}
	public void setSubtabs(List subtabs) {
		this.subTabs = subtabs;
	}
	public void addSubTab(SubTab subTab) {
		this.subTabs.add(subTab);
	}
	public String getOnclick()
	{
		return this.onclick;
	}
	public void setOnclick(String onclick)
	{
		this.onclick = onclick;
	}
	public String getRolesPermitidos()
	{
		return this.rolesPermitidos;
	}
	public void setRolesPermitidos(String rolesPermitidos)
	{
		this.rolesPermitidos = rolesPermitidos;
	}
	public String getVisible() {
		return this.visible;
	}
	public void setVisible(String visible) {
		this.visible = visible;
	}
}

