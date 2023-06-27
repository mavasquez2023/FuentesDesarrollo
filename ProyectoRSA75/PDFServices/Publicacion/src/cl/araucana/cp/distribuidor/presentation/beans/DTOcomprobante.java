package cl.araucana.cp.distribuidor.presentation.beans;


public class DTOcomprobante implements Comparable {
	private String nombre = null;
	private String entidad = null;
	private Object pension = null;
	private Object sis = null;
	private Object afc = null;
	private Object total = null;
	private Integer trabajadores = null;
	private boolean istotal = false;
	private Object foliotesoreria = null;
	
	public DTOcomprobante() {
	}

	public DTOcomprobante(String nombre, String entidad, Object pension, Object sis, Object afc, Object total, Integer trabajadores, boolean istotal) {
		this.nombre = nombre;
		this.entidad = entidad;
		this.pension = pension;
		this.sis = sis;
		this.afc = afc;
		this.total = total;
		this.trabajadores = trabajadores;
		this.istotal = istotal;
	}
	
	public DTOcomprobante(String nombre, String entidad, Object pension, Object sis, Object afc, Object total, Integer trabajadores) {
		this.nombre = nombre;
		this.entidad = entidad;
		this.pension = pension;
		this.sis = sis;
		this.afc = afc;
		this.total = total;
		this.trabajadores = trabajadores;
	}
	
	
	public Object getFoliotesoreria() {
		return foliotesoreria;
	}


	public void setFoliotesoreria(Object foliotesoreria) {
		this.foliotesoreria = foliotesoreria;
	}
	
	
	public boolean getIstotal() {
		return istotal;
	}


	public void setIstotal(boolean istotal) {
		this.istotal = istotal;
	}


	public Object getAfc() {
		return afc;
	}
	public void setAfc(Integer afc) {
		this.afc = afc;
	}
	public String getEntidad() {
		return entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Object getPension() {
		return this.pension;
	}
	public void setPension(Integer pension) {
		this.pension = pension;
	}
	public Object getSis() {
		return this.sis;
	}
	public void setSis(Integer sis) {
		this.sis = sis;
	}
	public Object getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Object getTrabajadores() {
		return trabajadores;
	}
	public void setTrabajadores(Integer trabajadores) {
		this.trabajadores = trabajadores;
	}


	public int compareTo(DTOcomprobante o) {
		// TODO Apéndice de método generado automáticamente
		return this.entidad.compareTo(o.entidad);
	}


	public int compareTo(Object o) {
		// TODO Apéndice de método generado automáticamente
		DTOcomprobante val = (DTOcomprobante)o;
		return this.entidad.compareTo(val.entidad);
	}


	public String toString() {
		// TODO Apéndice de método generado automáticamente
		return getNombre()+","+getEntidad()+","+getPension()+","+getSis()+","+getAfc()+","+getTotal()+","+getTrabajadores();
	}
}
