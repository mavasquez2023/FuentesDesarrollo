package cl.araucana.personal.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author asepulveda
 * Representa la informacion de retorno de Consulta de Empleados
 */
public class EmpleadoVO implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	public static final String STD_INACTIVO="I";	
	public static final String STD_ACTIVO="A";	
	
   private String rut;
   private String nombre;
   private String apePaterno;
   private String apeMaterno;
   private String estCivil;
   private Date fecIngreso;
   private Date fecNacimiento;
   private Date fecEgreso;
   private Date fecInicioBeneficio;
   private String codCargo;
   private String codOficina;
   private String sexo;
   private String tipoContrato=null;
   private String domicilioParticular = null;
   private String codCiudad = null;
   private String codComuna = null;
   private String fonoParticular = null;
   private String fonoEmergencia = null;
   private String estado;

	public EmpleadoVO() {
		rut="";
		nombre="";
		apePaterno="";
		apeMaterno="";
		estCivil="";
		fecIngreso=null;
		fecNacimiento=null;
		fecEgreso=null;
		fecInicioBeneficio=null;
		sexo="";
		tipoContrato="";
		codCargo="";
		codOficina="";
		domicilioParticular = null;
		codCiudad = "";
		codComuna = "";
		fonoParticular = "";
		fonoEmergencia = "";
		estado = "";
	}
	
	/**
	 * @return
	 */
	public String getApeMaterno() {
		return apeMaterno;
	}
	
	/**
	 * @return
	 */
	public String getApePaterno() {
		return apePaterno;
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
	public Date getFecEgreso() {
		return fecEgreso;
	}
	
	/**
	 * @return
	 */
	public Date getFecIngreso() {
		return fecIngreso;
	}
	
	/**
	 * @return
	 */
	public Date getFecNacimiento() {
		return fecNacimiento;
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
	public String getRut() {
		return rut;
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
	public void setApeMaterno(String string) {
		apeMaterno = string;
	}
	
	/**
	 * @param string
	 */
	public void setApePaterno(String string) {
		apePaterno = string;
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
	public void setFecEgreso(Date date) {
		fecEgreso = date;
	}
	
	/**
	 * @param date
	 */
	public void setFecIngreso(Date date) {
		fecIngreso = date;
	}
	
	/**
	 * @param date
	 */
	public void setFecNacimiento(Date date) {
		fecNacimiento = date;
	}
	
	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}
	
	/**
	 * @param string
	 */
	public void setRut(String string) {
		rut = string;
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
	public Date getFecInicioBeneficio() {
		return fecInicioBeneficio;
	}
	
	/**
	 * @return
	 */
	public String getTipoContrato() {
		return tipoContrato;
	}
	
	/**
	 * @param date
	 */
	public void setFecInicioBeneficio(Date date) {
		fecInicioBeneficio = date;
	}
	
	/**
	 * @param string
	 */
	public void setTipoContrato(String string) {
		tipoContrato = string;
	}
	
	/**
	 * @return
	 */
	public String getEstado() {
		return estado;
	}
	
	/**
	 * @param string
	 */
	public void setEstado(String string) {
		estado = string;
	}

/**
 * @return
 */
public String getCodOficina() {
	return codOficina;
}

/**
 * @param string
 */
public void setCodOficina(String string) {
	codOficina = string;
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
