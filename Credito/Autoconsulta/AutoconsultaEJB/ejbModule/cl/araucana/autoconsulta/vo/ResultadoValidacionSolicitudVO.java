/*
 * Creado el 26-05-2006
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author asepulveda
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class ResultadoValidacionSolicitudVO implements Serializable {

	boolean ok = false;
	Collection mensajes = new ArrayList();
	
	/**
	 * @return
	 */
	public Collection getMensajes() {
		return mensajes;
	}

	/**
	 * @return
	 */
	public boolean isOk() {
		return ok;
	}

	/**
	 * @param collection
	 */
	public void setMensajes(Collection collection) {
		mensajes = collection;
	}

	/**
	 * @param b
	 */
	public void setOk(boolean b) {
		ok = b;
	}

}
