/**
 * 
 */
package cl.laaraucana.pubnominas.utils;


/**
 * @author J-Factory
 *
 */
public class AvisoSingleton {
	private static AvisoSingleton instance = new AvisoSingleton();
	
	String parrafo1;
	String parrafo2;
	String nota;
	int catidadAnios;
	boolean activo=false;
	
	public static AvisoSingleton getInstance(){
		return instance;
	}
	
	public AvisoSingleton(){
		try {
			this.parrafo1= Configuraciones.getConfig("aviso.importante.encargados.parrafo1");
			this.parrafo2= Configuraciones.getConfig("aviso.importante.encargados.parrafo2");
			this.nota= Configuraciones.getConfig("aviso.importante.encargados.nota");
			this.catidadAnios= Integer.parseInt(Configuraciones.getConfig("param.rango.years"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the parrafo1
	 */
	public String getParrafo1() {
		return parrafo1;
	}

	/**
	 * @param parrafo1 the parrafo1 to set
	 */
	public void setParrafo1(String parrafo1) {
		this.parrafo1 = parrafo1;
	}

	/**
	 * @return the parrafo2
	 */
	public String getParrafo2() {
		return parrafo2;
	}

	/**
	 * @param parrafo2 the parrafo2 to set
	 */
	public void setParrafo2(String parrafo2) {
		this.parrafo2 = parrafo2;
	}

	/**
	 * @return the nota
	 */
	public String getNota() {
		return nota;
	}

	/**
	 * @param nota the nota to set
	 */
	public void setNota(String nota) {
		this.nota = nota;
	}

	/**
	 * @return the activo
	 */
	public boolean isActivo() {
		return activo;
	}

	/**
	 * @param activo the activo to set
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	/**
	 * @return the catidadAnios
	 */
	public int getCatidadAnios() {
		return catidadAnios;
	}

	/**
	 * @param catidadAnios the catidadAnios to set
	 */
	public void setCatidadAnios(int catidadAnios) {
		this.catidadAnios = catidadAnios;
	}

	

	
	
}
