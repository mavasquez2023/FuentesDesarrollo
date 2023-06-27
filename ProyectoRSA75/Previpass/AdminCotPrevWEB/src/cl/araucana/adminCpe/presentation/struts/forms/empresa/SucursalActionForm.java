package cl.araucana.adminCpe.presentation.struts.forms.empresa;

import java.util.Collection;
import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#) SucursalActionForm.java 1.4 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author aacuña
 * 
 * @version 1.4
 */
public class SucursalActionForm extends ActionForm {

	private static final long serialVersionUID = -468192642461147540L;
	private String rutEmpresaFmt;
	private String rutEmpresa;
	private String nombreEmpresa;
	private String tipoOper;
	
	private String codigo;
	private String nombre;
	private String direccion;
	private String numero;
	private String dpto;
	private String opcRegion;
	private String opcCiudad;
	private String opcComuna;
	private String opcSucursal;
	private String nombreComuna;
	private String nombreCiudad;
	private String nombreRegion;
	private String email;
	private String fono;
	private String celular;
	private String fax;
	private String codigoFax;
	private String codigoFono;
	
	private List sucursales;
	private List regiones;
	private List ciudades;
	private List comunas;
	
	private List consultaSucursales;
	private Collection numeroFilas;

	/**
	 * celular
	 * @return
	 */
	public String getCelular()
	{
		return this.celular;
	}
	/**
	 * celular
	 * @param celular
	 */
	public void setCelular(String celular)
	{
		this.celular = celular;
	}
	/**
	 * ciudades
	 * @return
	 */
	public List getCiudades()
	{
		return this.ciudades;
	}
	/**
	 * ciudades
	 * @param ciudades
	 */
	public void setCiudades(List ciudades)
	{
		this.ciudades = ciudades;
	}
	/**
	 * codigo
	 * @return
	 */
	public String getCodigo()
	{
		return this.codigo;
	}
	/**
	 * codigo
	 * @param codigo
	 */
	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}
	/**
	 * comunas
	 * @return
	 */
	public List getComunas()
	{
		return this.comunas;
	}
	/**
	 * comunas
	 * @param comunas
	 */
	public void setComunas(List comunas)
	{
		this.comunas = comunas;
	}
	/**
	 * direccion
	 * @return
	 */
	public String getDireccion()
	{
		return this.direccion;
	}
	/**
	 * direccion
	 * @param direccion
	 */
	public void setDireccion(String direccion)
	{
		this.direccion = direccion;
	}
	/**
	 * departamento
	 * 
	 */
	public String getDpto()
	{
		return this.dpto;
	}
	/**
	 * departamento
	 * @param dpto
	 */
	public void setDpto(String dpto)
	{
		this.dpto = dpto;
	}
	/**
	 * correo
	 * @return
	 */
	public String getEmail()
	{
		return this.email;
	}
	/**
	 * correo
	 * @param email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}
	/**
	 * fax
	 * @return
	 */
	public String getFax()
	{
		return this.fax;
	}
	/**
	 * fax
	 * @param fax
	 */
	public void setFax(String fax)
	{
		this.fax = fax;
	}
	/**
	 * telefono
	 * @return
	 */
	public String getFono()
	{
		return this.fono;
	}
	/**
	 * telefono
	 * @param fono
	 */
	public void setFono(String fono)
	{
		this.fono = fono;
	}
	/**
	 * nombre
	 * @return
	 */
	public String getNombre()
	{
		return this.nombre;
	}
	/**
	 * nombre
	 * @param nombre
	 */
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	/**
	 * nombre ciudad
	 * @return
	 */
	public String getNombreCiudad()
	{
		return this.nombreCiudad;
	}
	/**
	 * nombre ciudad
	 * @param nombreCiudad
	 */
	public void setNombreCiudad(String nombreCiudad)
	{
		this.nombreCiudad = nombreCiudad;
	}
	/**
	 * nombre comuna
	 * @return
	 */
	public String getNombreComuna()
	{
		return this.nombreComuna;
	}
	/**
	 * nombre comuna
	 * @param nombreComuna
	 */
	public void setNombreComuna(String nombreComuna)
	{
		this.nombreComuna = nombreComuna;
	}
	/**
	 * nombre empresa
	 * @return
	 */
	public String getNombreEmpresa()
	{
		return this.nombreEmpresa;
	}
	/**
	 * nombre empresa
	 * @param nombreEmpresa
	 */
	public void setNombreEmpresa(String nombreEmpresa)
	{
		this.nombreEmpresa = nombreEmpresa;
	}
	/**
	 * nombre region
	 * @return
	 */
	public String getNombreRegion()
	{
		return this.nombreRegion;
	}
	/**
	 * nombre region
	 * @param nombreRegion
	 */
	public void setNombreRegion(String nombreRegion)
	{
		this.nombreRegion = nombreRegion;
	}
	/**
	 * numero
	 * @return
	 */
	public String getNumero()
	{
		return this.numero;
	}
	/**
	 * numero
	 * @param numero
	 */
	public void setNumero(String numero)
	{
		this.numero = numero;
	}
	/**
	 * opc ciudad
	 * @return
	 */
	public String getOpcCiudad()
	{
		return this.opcCiudad;
	}
	/**
	 * opc ciudad
	 * @param opcCiudad
	 */
	public void setOpcCiudad(String opcCiudad)
	{
		this.opcCiudad = opcCiudad;
	}
	/**
	 * opc comuna
	 * @return
	 */
	public String getOpcComuna()
	{
		return this.opcComuna;
	}
	/**
	 * opc comuna
	 * @param opcComuna
	 */
	public void setOpcComuna(String opcComuna)
	{
		this.opcComuna = opcComuna;
	}
	/**
	 * opc region
	 * @return
	 */
	public String getOpcRegion()
	{
		return this.opcRegion;
	}
	/**
	 * opc region
	 * @param opcRegion
	 */
	public void setOpcRegion(String opcRegion)
	{
		this.opcRegion = opcRegion;
	}
	/**
	 * regiones
	 * @return
	 */
	public List getRegiones()
	{
		return this.regiones;
	}
	/**
	 * regiones
	 * @param regiones
	 */
	public void setRegiones(List regiones)
	{
		this.regiones = regiones;
	}
	/**
	 * rut empresa fmt
	 * @return
	 */
	public String getRutEmpresaFmt()
	{
		return this.rutEmpresaFmt;
	}
	/**
	 * rut empresa fmt
	 * @param rutEmpresaFmt
	 */
	public void setRutEmpresaFmt(String rutEmpresaFmt)
	{
		this.rutEmpresaFmt = rutEmpresaFmt;
	}
	/**
	 * sucursales
	 * @return
	 */
	public List getSucursales()
	{
		return this.sucursales;
	}
	/**
	 * sucursales
	 * @param sucursales
	 */
	public void setSucursales(List sucursales)
	{
		this.sucursales = sucursales;
	}
	/**
	 * consulta sucursales
	 * @return
	 */
	public List getConsultaSucursales()
	{
		return this.consultaSucursales;
	}
	/**
	 * consulta sucursales
	 * @param consultaSucursales
	 */
	public void setConsultaSucursales(List consultaSucursales)
	{
		this.consultaSucursales = consultaSucursales;
	}
	/**
	 * opc sucursal
	 * @return
	 */
	public String getOpcSucursal()
	{
		return this.opcSucursal;
	}
	/**
	 * opc sucursal
	 * @param opcSucursal
	 */
	public void setOpcSucursal(String opcSucursal)
	{
		this.opcSucursal = opcSucursal;
	}
	/**
	 * rut empresa
	 * @return
	 */
	public String getRutEmpresa()
	{
		return this.rutEmpresa;
	}
	/**
	 * rut empresa
	 * @param rutEmpresa
	 */
	public void setRutEmpresa(String rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
	}
	/**
	 * tipo operacion
	 * @return
	 */
	public String getTipoOper()
	{
		return this.tipoOper;
	}
	/**
	 * tipo operacion
	 * @param tipoOper
	 */
	public void setTipoOper(String tipoOper)
	{
		this.tipoOper = tipoOper;
	}
	/**
	 * numero filas
	 * @return
	 */
	public Collection getNumeroFilas() {
		return this.numeroFilas;
	}
	/**
	 * numero filas
	 * @param numeroFilas
	 */
	public void setNumeroFilas(Collection numeroFilas) {
		this.numeroFilas = numeroFilas;
	}

	/**
	 * codigo fax
	 * @return
	 */
	public String getCodigoFax() {
		return this.codigoFax;
	}
	/**
	 * codigo fax
	 * @param codigoFax
	 */
	public void setCodigoFax(String codigoFax) {
		this.codigoFax = codigoFax;
	}
	/**
	 * codigo telefono
	 * @return
	 */
	public String getCodigoFono() {
		return this.codigoFono;
	}
	/**
	 * codigo telefono
	 * @param codigoFono
	 */
	public void setCodigoFono(String codigoFono) {
		this.codigoFono = codigoFono;
	}
}
