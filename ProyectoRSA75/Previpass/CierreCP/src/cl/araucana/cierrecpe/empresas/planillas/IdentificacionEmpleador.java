

/*
 * @(#) IdentificacionEmpleador.java    1.0 14-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.planillas;

import java.util.HashMap;
import java.util.Map;
import cl.araucana.core.util.Rut;
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
public class IdentificacionEmpleador {
	private Rut rutEmpresa;
	private String razonSocial="";
	private int codActEconomica;
    private Rut rutRepLegal;
    private String nombresRepLegal="";
    private String apellidosRepLegal="";
	private Map sucursales;
	
	public IdentificacionEmpleador(){
		sucursales= new HashMap();
		
	}
	
	/**
	 * @param codigo, el codigo a registrar para una nueva Sucursal
	 */
	public void addSucursal(String codigo, IdentificacionSucursal sucursal){
		sucursales.put(codigo, sucursal);
	}
	
	public IdentificacionSucursal getSucursal(String codigo){
		return (IdentificacionSucursal)sucursales.get(codigo);
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
	 * @return el nombresRepLegal
	 */
	public String getNombresRepLegal() {
		int largo=20;
		return Formato.truncateText(nombresRepLegal, largo);
	}
	/**
	 * @param nombreRepLegal el nombreRepLegal a establecer
	 */
	public void setNombresRepLegal(String nombresRepLegal) {
		this.nombresRepLegal = nombresRepLegal;
	}
	/**
	 * @return el apellidosRepLegal
	 */
	public String getApellidosRepLegal() {
		int largo=20;
		return Formato.truncateText(apellidosRepLegal, largo);
	}
	/**
	 * @param apellidosRepLegal el apellidosRepLegal a establecer
	 */
	public void setApellidosRepLegal(String apellidosRepLegal) {
		this.apellidosRepLegal = apellidosRepLegal;
	} 
	/**
	 * @return el razonSocial
	 */
	public String getRazonSocial() {
		int largo=40;
		return Formato.truncateText(razonSocial, largo);
	}
	/**
	 * @param razonSocial el razonSocial a establecer
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	/**
	 * @return el rutEmpresa
	 */
	public Rut getRutEmpresa() {
		return rutEmpresa;
	}
	/**
	 * @return el rutEmpresa en formato String alineado a la derecha
	 */
	public String getRutEmpresa(int largo) {
		if(getRutEmpresa()!= null){
			return Formato.paddingLeft(String.valueOf(getRutEmpresa().getNumber()), largo, ' ');
		}
		return "";
	}
	/**
	 * @param rutEmpresa el rutEmpresa a establecer
	 */
	public void setRutEmpresa(Rut rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
	/**
	 * @return el rutRepLegal
	 */
	public Rut getRutRepLegal() {
		return rutRepLegal;
	}
	/**
	 * @return el rutRepLegal en formato String alineado a la derecha
	 */
	public String getRutRepLegal(int largo) {
		if(getRutRepLegal()!= null){
			return String.valueOf(getRutRepLegal().getNumber());
		}
		return "";
	}
	/**
	 * @param rutRepLegal el rutRepLegal a establecer
	 */
	public void setRutRepLegal(Rut rutRepLegal) {
		this.rutRepLegal = rutRepLegal;
	}
	/**
	 * @return el sucursales
	 */
	public Map getSucursales() {
		return sucursales;
	}
	/**
	 * @param sucursales el sucursales a establecer
	 */
	public void setSucursales(Map sucursales) {
		this.sucursales = sucursales;
	}
	
}

