package cl.jfactory.planillas.service.util;

import java.util.HashMap;

import cl.sbs.util.reflection.impl.UtilReflectionImpl;

public class HebraUtil {

	String nombre = "";
	String clase = "";
	String metodo = "";
	Class[] tipos = new Class[0];
	Object[] parametros = new Object[0];
	public HebraUtil(String nombre, String clase, String metodo, Class[] tipos, Object[] parametros){
		this.nombre = nombre;
		this.clase = clase;
		this.metodo = metodo;
		this.tipos = tipos;
		this.parametros = parametros;

		UtilReflectionImpl.executeReflection(clase, metodo, tipos, parametros);
	}
	
}
