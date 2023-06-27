/*
 * Creada el 22-Julio-2008.
 *
 */
package cl.araucana.cierrecpe.empresas.planillas.mutual;

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

public class PlanillaMutualCotizante extends PlanillaBaseCotizante{

//	private static final int AMOUNT_WIDTH = 11;

	/*
	 * PROPIEDADES DE PAGINA DE DETALLE COTIZANTE
	 */
	
    private int remuneracionImponible=0;
    private int montoCotizacion=0;
    private char codigoSexo;
    private int numeroDiasTrabajados=0;
	
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
	/**
	 * @return el codigoSexo
	 */
	public char getCodigoSexo() {
		return codigoSexo;
	}
	/**
	 * @param codigoSexo el codigoSexo a establecer
	 */
	public void setCodigoSexo(char codigoSexo) {
		this.codigoSexo = codigoSexo;
	}
	/**
	 * @return el montoCotizacion
	 */
	public int getMontoCotizacion() {
		return montoCotizacion;
	}
	/**
	 * @param montoCotizacion el montoCotizacion a establecer
	 */
	public void setMontoCotizacion(int montoCotizacion) {
		this.montoCotizacion = montoCotizacion;
	}
	/**
	 * @return el numeroDiasTrabajados
	 */
	public int getNumeroDiasTrabajados() {
		return numeroDiasTrabajados;
	}
	/**
	 * @param numeroDiasTrabajados el numeroDiasTrabajados a establecer
	 */
	public void setNumeroDiasTrabajados(int numeroDiasTrabajados) {
		this.numeroDiasTrabajados = numeroDiasTrabajados;
	}
	
}