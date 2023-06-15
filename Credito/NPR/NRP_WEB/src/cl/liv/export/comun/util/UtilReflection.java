package cl.liv.export.comun.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UtilReflection {
	
	Method method = null;
	Class<?> c = null;
	Object instancia = null;
	public void loadClass(String clase) {
		
		try {
			c = Class.forName(clase);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public void loadMethod(String metodo, Class... types) {
		try {

			instancia = (Object) c.getConstructor().newInstance();
			method = c.getMethod(metodo, types);

		} catch (NoSuchMethodException e) {

			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}

	public Object executeMethod(Object... parametros) {
		try {

			Object retorno = method.invoke(instancia,parametros);

			return retorno;
		}  catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}


	
}
