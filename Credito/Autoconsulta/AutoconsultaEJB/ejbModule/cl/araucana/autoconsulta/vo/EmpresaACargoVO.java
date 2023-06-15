package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class EmpresaACargoVO implements Serializable {
	
	public static final String ALL_OFICINAS_SI="SI";
	public static final String ALL_OFICINAS_NO="NO";
	
	private long rutEncargado=0;
	private long rut=0;
	private String dv="?";
	private String nombre=null;
	private String apellidoMaterno=null;
	private String apellidoPaterno=null;	
	private String allOficinasSucursales=null;
	private Collection listaOficinasSucursalesACargo=new ArrayList(); //OficinasSucursalesVO
	
	/**
	 * Devuelve el npmbre completo
	 * @return
	 */
	public String getFullNombre() {
		
		String fn="";
		if(nombre!=null)
			fn=nombre;
			
		if(apellidoPaterno!=null)
			fn=fn+ " " + apellidoPaterno;
			
		if(apellidoMaterno!=null)
			fn=fn+ " " + apellidoMaterno;
			
		return fn;
	}	
	

	/**
	 * @return
	 */
	public long getRut() {
		return rut;
	}

	/**
	 * @param l
	 */
	public void setRutEncargado(long l) {
		rutEncargado = l;
	}

	/**
	 * @return
	 */
	public long getRutEncargado() {
		return rutEncargado;
	}

	/**
	 * @param l
	 */
	public void setRut(long l) {
		rut = l;
	}
	/**
	 * @return
	 */
	public String getDv() {
		return dv;
	}

	/**
	 * @param l
	 */
	public void setDv(String s) {
		dv = s;
	}
	
	/**
	 * @return
	 */
	public String getFullRut() {
		return rut+"-"+dv;
	}	
	/**
	 * @return
	 */
	public String getAllOficinasSucursales() {
		return allOficinasSucursales;
	}

	/**
	 * @param string
	 */
	public void setAllOficinasSucursales(String string) {
		allOficinasSucursales = string;
	}

	/**
	 * @return
	 */
	public Collection getListaOficinasSucursalesACargo() {
		return listaOficinasSucursalesACargo;
	}

	/**
	 * @param collection
	 */
	public void setListaOficinasSucursalesACargo(Collection collection) {
		listaOficinasSucursalesACargo = collection;
	}

	/**
	 * @return
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/**
	 * @return
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param string
	 */
	public void setApellidoMaterno(String string) {
		apellidoMaterno = string;
	}

	/**
	 * @param string
	 */
	public void setApellidoPaterno(String string) {
		apellidoPaterno = string;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}

}
