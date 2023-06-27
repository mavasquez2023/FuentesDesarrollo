package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class DetalleDescuentosSocioVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	private String rut=null;
	private String dv=null;
	private String nombre=null;
	private String apellidoPaterno=null;
	private String apellidoMaterno=null;
	private String codigoOficina=null;
	private String oficina=null;
	private String codCargo=null;
	private String codDepartamento=null;
	private String departamento=null;
	private int montoDescuentoTotal=0;
	private Date fecha=null;
	private ArrayList casosDescontados=new ArrayList(); //CasosDescontadosVO
	private ArrayList cuotasPrestamos=new ArrayList(); //CuotasPrestamoDescontadasVO

	/**
	 * Retorna el rut compuesto del Socio
	 * @return String con el rut
	 */
	public String getFullRut() {
		return rut+"-"+dv;
	}
	
	/**
	 * Retorna el nombre completo del Socio
	 * @return String
	 */
	public String getFullNombre() {
		return nombre+" "+apellidoPaterno+" "+apellidoMaterno;
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
	public ArrayList getCasosDescontados() {
		return casosDescontados;
	}

	/**
	 * @return
	 */
	public String getCodigoOficina() {
		return codigoOficina;
	}

	/**
	 * @return
	 */
	public ArrayList getCuotasPrestamos() {
		return cuotasPrestamos;
	}

	/**
	 * @return
	 */
	public String getDv() {
		return dv;
	}

	/**
	 * @return
	 */
	public int getMontoDescuentoTotal() {
		return montoDescuentoTotal;
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
	 * @param list
	 */
	public void setCasosDescontados(ArrayList list) {
		casosDescontados = list;
	}

	/**
	 * @param string
	 */
	public void setCodigoOficina(String string) {
		codigoOficina = string;
	}

	/**
	 * @param list
	 */
	public void setCuotasPrestamos(ArrayList list) {
		cuotasPrestamos = list;
	}

	/**
	 * @param string
	 */
	public void setDv(String string) {
		dv = string;
	}

	/**
	 * @param i
	 */
	public void setMontoDescuentoTotal(int i) {
		montoDescuentoTotal = i;
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
	 * @return
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param date
	 */
	public void setFecha(Date date) {
		fecha = date;
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
	public String getCodCargo() {
		return codCargo;
	}

	/**
	 * @return
	 */
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * @param string
	 */
	public void setCodCargo(String string) {
		codCargo = string;
	}

	/**
	 * @param string
	 */
	public void setDepartamento(String string) {
		departamento = string;
	}

	/**
	 * @return
	 */
	public String getCodDepartamento() {
		return codDepartamento;
	}

	/**
	 * @param string
	 */
	public void setCodDepartamento(String string) {
		codDepartamento = string;
	}

}
