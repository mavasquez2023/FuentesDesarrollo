/*
 * Creado el 01-06-2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.recursos;

import java.util.Vector;


/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class GeneradorXLS_ContenedorXML {
	//Antecedentes Trabajador
	private Vector v1,v2,v3,v4;
	
	protected GeneradorXLS_ContenedorXML() {
		v1= new Vector();
		v2= new Vector();
		v3= new Vector();
		v4= new Vector();
	}
	public void setNombre (String texto)
	{
		if (texto== null){
			texto="";
		}
		this.v1.add(texto);
	}
	public void setPosIni (String texto)
	{
		this.v2.add(texto);
	}
	public void setLargo (String texto)
	{
		if (texto== null){
			texto="0";
		}
		this.v3.add(texto);
	}
	public void setAlign(String texto)
	{
		if (texto== null){
			texto="center";
		}
		this.v4.add(texto);
	}
	public Vector getNombres()
	{
		return v1;
	}
	public Vector getPosIni()
	{
		return v2;
	}
	public Vector getLargos()
	{
		return v3;
	}
	public Vector getAligns()
	{
		return v4;
	}
	
}
