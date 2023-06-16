/*
 * Creada el 14-04-2008
 *
 */
package cl.araucana.cierrecpe.empresas.planillas.otros;


import cl.araucana.cierrecpe.empresas.planillas.PlanillaBaseCotizante;

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
 *            <TD> 05-05-2008 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> Claudio Lillo <BR> clillo@laaraucana.cl </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Claudio Lillo (clillo@laaraucana.cl)
 *
 * @version 1.0
 */
public class PlanillaAfbrCotizante extends PlanillaBaseCotizante{

	/*
	 * PROPIEDADES DE PAGINA DE DETALLE COTIZANTE
	 */
	    
    //Cotización
	private int remuneracionImponible=0;
	private int aporteAFBR=0;
	/**
	 * @return el aporteAFBR
	 */
	public int getAporteAFBR() {
		return aporteAFBR;
	}
	/**
	 * @param aporteAFBR el aporteAFBR a establecer
	 */
	public void setAporteAFBR(int aporteAFBR) {
		this.aporteAFBR = aporteAFBR;
	}
	/**
	 * @return el remuneracionImponible
	 */
	public int getRemuneracionImponible() {
		return remuneracionImponible;
	}
	/**
	 * @param remuneracionImponible el remuneracionImponible a establecer
	 */
	public void setRemuneracionImponible(int remuneracionImponible) {
		this.remuneracionImponible = remuneracionImponible;
	}
	
		
}
