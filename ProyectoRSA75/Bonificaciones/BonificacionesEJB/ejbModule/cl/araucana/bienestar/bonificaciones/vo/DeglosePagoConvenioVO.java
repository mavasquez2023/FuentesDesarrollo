

/*
 * @(#) DeglosePagoConvenioVO.java    1.0 26-01-2010
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.bienestar.bonificaciones.vo;


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
 *            <TD> 26-01-2010 </TD>
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
public class DeglosePagoConvenioVO {
	private long casoId;
	private long codConvenio;
	private long monto;
	private long codProceso;
	
	public DeglosePagoConvenioVO(long casoId, long codConvenio, long monto, long codProceso){
		this.casoId = casoId;
		this.codConvenio = codConvenio;
		this.monto = monto;
		this.codProceso = codProceso;
		
	}
	
	public long getCasoId() {
		return casoId;
	}
	public void setCasoId(long casoId) {
		this.casoId = casoId;
	}
	public long getCodConvenio() {
		return codConvenio;
	}
	public void setCodConvenio(long codConvenio) {
		this.codConvenio = codConvenio;
	}
	public long getCodProceso() {
		return codProceso;
	}
	public void setCodProceso(long codProceso) {
		this.codProceso = codProceso;
	}
	public long getMonto() {
		return monto;
	}
	public void setMonto(long monto) {
		this.monto = monto;
	}
	
	
}

