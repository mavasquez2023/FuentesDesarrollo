

/*
 * @(#) SocioInactivoConCasoAbierto.java    1.0 23-09-2009
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;


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
 *            <TD align="center"> <B>Versi�n</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripci�n</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 23-09-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Fabrizio Barisione Biso <BR> fbarisione@laaraucana.cl </TD>
 *            <TD> Versi�n inicial. </TD>
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
 * @author Fabrizio Barisione Biso (fbarisione@laaraucana.cl)
 *
 * @version 1.0
 */
public class SocioInactivoConCasoAbierto implements Serializable{
	
	/** Serial */
	private static final long serialVersionUID = 1L;
	
	private long rut;
	private String dv;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private int contadorCasosAbiertos;
	
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	
	public SocioInactivoConCasoAbierto(){
	}
	
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getDv() {
		return dv;
	}
	public void setDv(String dv) {
		this.dv = dv;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public long getRut() {
		return rut;
	}
	public void setRut(long rut) {
		this.rut = rut;
	}

	public int getContadorCasosAbiertos() {
		return contadorCasosAbiertos;
	}

	public void setContadorCasosAbiertos(int contadorCasosAbiertos) {
		this.contadorCasosAbiertos = contadorCasosAbiertos;
	}
	
	
}

