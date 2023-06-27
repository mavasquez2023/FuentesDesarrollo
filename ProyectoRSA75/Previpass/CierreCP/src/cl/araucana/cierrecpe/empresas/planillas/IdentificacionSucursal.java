

/*
 * @(#) IdentificacionSucursal.java    1.0 21-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.planillas;

import cl.recursos.Formato;


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
 *            <TD> 21-07-2009 </TD>
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
public class IdentificacionSucursal {
	private String codigo="";
	private String direccion="";
	private String telefono="";
	private int idComuna=0;
	private String comuna="";
    private String ciudad="";
    private String region="";
    private String email="";
    private String fax="";
	/**
	 * @return el ciudad
	 */
	public String getCiudad(int largo) {
		return Formato.truncateText(ciudad, largo);
	}
	/**
	 * @param ciudad el ciudad a establecer
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	/**
	 * @return el comuna
	 */
	public String getComuna(int largo) {
		return Formato.truncateText(comuna, largo);
	}
	/**
	 * @param comuna el comuna a establecer
	 */
	public void setComuna(String comuna) {
		this.comuna = comuna;
	}
	/**
	 * @return el direccion
	 */
	public String getDireccion(int largo) {
		return Formato.truncateText(direccion, largo);
	}
	/**
	 * @param direccion el direccion a establecer
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return el region
	 */
	public String getRegion(int largo) {
		return Formato.truncateText(region, largo);
	}
	/**
	 * @param region el region a establecer
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return el telefono
	 */
	public String getTelefono(int largo) {
		return Formato.truncateText(telefono, largo);
	}
	/**
	 * @param telefono el telefono a establecer
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * @return el codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo el codigo a establecer
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return el email
	 */
	public String getEmail(int largo) {
		return Formato.truncateText(email, largo);
	}
	/**
	 * @param email el email a establecer
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return el fax
	 */
	public String getFax(int largo) {
		return Formato.truncateText(fax, largo);
	}
	/**
	 * @param fax el fax a establecer
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
	/**
	 * @return el ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}
	/**
	 * @return el comuna
	 */
	public String getComuna() {
		return comuna;
	}
	/**
	 * @return el direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @return el email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return el fax
	 */
	public String getFax() {
		int largo=12;
		return Formato.truncateText(fax, largo);
	}
	/**
	 * @return el region
	 */
	public String getRegion() {
		String regionaux=Formato.padding(Integer.parseInt(region), 2);
		return regionaux;
	}
	/**
	 * @return el telefono
	 */
	public String getTelefono() {
		int largo=12;
		return Formato.truncateText(telefono, largo);
	}
	/**
	 * @return el id_comuna
	 */
	public int getIdComuna() {
		return idComuna;
	}
	/**
	 * @param id_comuna el id_comuna a establecer
	 */
	public void setIdComuna(int idComuna) {
		this.idComuna = idComuna;
	} 
}

