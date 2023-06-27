package cl.araucana.bienestar.bonificaciones.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import cl.araucana.personal.vo.EmpleadoVO;

/**
 * @author asepulveda
 * Representa la informacion de un Socio de Bienestar
 */
public class Socio implements Serializable {

	/** Serial */
	private static final long serialVersionUID = 1L;

	
   public static final String STD_BORRADOR="BORRADOR";	
   public static final String STD_ACTIVO="V";	
   public static final String STD_ELIMINADO="N";	
	
	
   private String rut=null;
   private char digito='?';
   private String nombre=null;
   private String apePat=null;
   private String apeMat=null;
   private Date fecNac=null;
   private Date fecIng=null;
   private Date fecEgr=null;
   
   private String sexo=null;
   private String codCargo=null;
   private String oficina = null;
   private String estado=STD_BORRADOR;
   private String estCivil=null;
   private String domicilioParticular = null;
   private String codCiudad = null;
   private String codComuna = null;
   private String fonoParticular = null;
   private String fonoEmergencia = null;
   private ArrayList vale = new ArrayList();
   
   public Socio () {
   }

   /**
    * Constructor de un Socio a partir de un Value Object de Empleado
    * @param emp: el empleado
    */
   public Socio (EmpleadoVO emp) {
	StringTokenizer st = new StringTokenizer(emp.getRut(),"-");				
	rut=st.nextToken();   	
	digito= st.nextToken().charAt(0);
	nombre = emp.getNombre();
	apePat = emp.getApePaterno();
	apeMat = emp.getApeMaterno();
	fecNac = emp.getFecNacimiento();
	sexo= emp.getSexo();
	codCargo = emp.getCodCargo();
	oficina = emp.getCodOficina();
	estCivil= emp.getEstCivil(); 
	domicilioParticular = emp.getDomicilioParticular();
	codCiudad = emp.getCodCiudad();
	codComuna = emp.getCodComuna();
	fonoParticular = emp.getFonoParticular();
	fonoEmergencia = emp.getFonoEmergencia();  
	fecIng = emp.getFecInicioBeneficio();
	fecEgr = emp.getFecEgreso();
   }
   
   public void removeAllVales() {
	vale.clear();
   }
   
   /**
	* Retorna el rut compuesto del Socio
	* @return String con el rut
	*/
   public String getFullRut() {
	   return rut+"-"+digito;
   }
   
	/**
	 * @return
	 */
	public String getApeMat() {
		return apeMat;
	}
	
	/**
	 * @return
	 */
	public String getApePat() {
		return apePat;
	}
	
	/**
	 * @return
	 */
	public char getDigito() {
		return digito;
	}
	
	/**
	 * @return
	 */
	public String getEstado() {
		return estado;
	}
	
	/**
	 * @return
	 */
	public String getEstCivil() {
		return estCivil;
	}
	
	/**
	 * @return
	 */
	public Date getFecEgr() {
		return fecEgr;
	}
	
	/**
	 * @return
	 */
	public Date getFecIng() {
		return fecIng;
	}
	
	/**
	 * @return
	 */
	public Date getFecNac() {
		return fecNac;
	}
	
	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}
	

	/**
	 * @return
	 */
	public String getSexo() {
		return sexo;
	}
	
	/**
	 * @param string
	 */
	public void setApeMat(String string) {
		apeMat = string;
	}
	
	/**
	 * @param string
	 */
	public void setApePat(String string) {
		apePat = string;
	}
	
	/**
	 * @param c
	 */
	public void setDigito(char c) {
		digito = c;
	}
	
	/**
	 * @param string
	 */
	public void setEstado(String string) {
		estado = string;
	}
	
	/**
	 * @param string
	 */
	public void setEstCivil(String string) {
		estCivil = string;
	}
	
	/**
	 * @param date
	 */
	public void setFecEgr(Date date) {
		fecEgr = date;
	}
	
	/**
	 * @param date
	 */
	public void setFecIng(Date date) {
		fecIng = date;
	}
	
	/**
	 * @param date
	 */
	public void setFecNac(Date date) {
		fecNac = date;
	}

	
	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}
	
	/**
	 * @param c
	 */
	public void setSexo(String string) {
		sexo = string;
	}

	/**
	 * @return
	 */
	public String getRut() {
		return rut;
	}
	
	/**
	 * @param string
	 */
	public void setRut(String string) {
		rut = string;
	}

	/**
	 * @return
	 */
	public ArrayList getVale() {
		return vale;
	}
	
	/**
	 * @param list
	 */
	public void setVale(ArrayList list) {
		vale = list;
	}
	
	/**
	 * @return
	 */
	public String getOficina() {
		return oficina;
	}
	
	/**
	 * @param string
	 */
	public void setOficina(String string) {
		oficina = string;
	}
	
	/**
	 * @return
	 */
	public String getCodCiudad() {
		return codCiudad;
	}
	
	/**
	 * @return
	 */
	public String getCodComuna() {
		return codComuna;
	}
	
	/**
	 * @return
	 */
	public String getDomicilioParticular() {
		return domicilioParticular;
	}
	
	/**
	 * @return
	 */
	public String getFonoEmergencia() {
		return fonoEmergencia;
	}
	
	/**
	 * @return
	 */
	public String getFonoParticular() {
		return fonoParticular;
	}
	
	/**
	 * @param string
	 */
	public void setCodCiudad(String string) {
		codCiudad = string;
	}
	
	/**
	 * @param string
	 */
	public void setCodComuna(String string) {
		codComuna = string;
	}
	
	/**
	 * @param string
	 */
	public void setDomicilioParticular(String string) {
		domicilioParticular = string;
	}
	
	/**
	 * @param string
	 */
	public void setFonoEmergencia(String string) {
		fonoEmergencia = string;
	}
	
	/**
	 * @param string
	 */
	public void setFonoParticular(String string) {
		fonoParticular = string;
	}

/**
 * @return
 */
public String getCodCargo() {
	return codCargo;
}

/**
 * @param string
 */
public void setCodCargo(String string) {
	codCargo = string;
}

}
