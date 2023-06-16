

/*
 * @(#) IdentificacionEmpleador.java    1.0 14-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.business;

import cl.araucana.core.util.Rut;



/**
 * ...
 *
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 14-07-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> CLAUDIO LILLO AZORÍN <BR> clillo@laaraucana.cl </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD>   </TD>
 *            <TD align="center">  </TD>
 *            <TD>   </TD>
 *            <TD>  </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author CLAUDIO LILLO AZORÍN (clillo@laaraucana.cl)
 *
 * @version 1.0
 */
public class NuevoConvenio {
	private Rut rutEmpresa;
	private int convenio;
	private String razonSocial="";
	private int codActEconomica=0;
    private String direccion="";
    private String numero="";
    private String local="";
    private String telefono="";
    private String email="";
    private String comuna="";
    private String ciudad="";
    private String region="";
	/**
	 * @return el ciudad
	 */
	public String getCiudad() {
		if(ciudad.length()>40){
			return ciudad.substring(0, 40);
		}
		return ciudad;
	}
	/**
	 * @param ciudad el ciudad a establecer
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	/**
	 * @return el codActEconomica
	 */
	public int getCodActEconomica() {
		return codActEconomica;
	}
	/**
	 * @param codActEconomica el codActEconomica a establecer
	 */
	public void setCodActEconomica(int codActEconomica) {
		this.codActEconomica = codActEconomica;
	}
	/**
	 * @return el comuna
	 */
	public String getComuna() {
		if(comuna.length()>40){
			return comuna.substring(0, 40);
		}
		return comuna;
	}
	/**
	 * @param comuna el comuna a establecer
	 */
	public void setComuna(String comuna) {
		this.comuna = comuna;
	}
	/**
	 * @return el convenio
	 */
	public int getConvenio() {
		return convenio;
	}
	/**
	 * @param convenio el convenio a establecer
	 */
	public void setConvenio(int convenio) {
		this.convenio = convenio;
	}
	/**
	 * @return el direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion el direccion a establecer
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return el local
	 */
	public String getLocal() {
		return local;
	}
	/**
	 * @param local el local a establecer
	 */
	public void setLocal(String local) {
		this.local = local;
	}
	/**
	 * @return el numero
	 */
	public String getNumero() {
		return numero;
	}
	/**
	 * @param numero el numero a establecer
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	/**
	 * @return el razonSocial
	 */
	public String getRazonSocial() {
		if(razonSocial.length()>40){
			return razonSocial.substring(0, 40);
		}
		return razonSocial;
	}
	/**
	 * @param razonSocial el razonSocial a establecer
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	/**
	 * @return el region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region el region a establecer
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return el rutEmpresa
	 */
	public Rut getRutEmpresa() {
		return rutEmpresa;
	}
	/**
	 * @param rutEmpresa el rutEmpresa a establecer
	 */
	public void setRutEmpresa(Rut rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
	/**
	 * @return el telefono
	 */
	public String getTelefono() {
		if(telefono.length()>12){
			return telefono.substring(0, 12);
		}
		return telefono;
	}
	/**
	 * @param telefono el telefono a establecer
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * @return el email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email el email a establecer
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
}

