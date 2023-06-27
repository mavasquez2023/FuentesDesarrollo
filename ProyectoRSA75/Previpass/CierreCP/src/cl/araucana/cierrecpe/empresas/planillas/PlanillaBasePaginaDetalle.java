/*
 * Creada el 03-04-2008
 *
 */
package cl.araucana.cierrecpe.empresas.planillas;

import java.util.List;


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
public class PlanillaBasePaginaDetalle {

	private List cotizantes;
	private PlanillaBase cabeceraPlanilla;
	
	/*
     * HEADER DE LA PAGINA DETALLE
     */

    private int paginaActual;
    private int paginaFinal;
    private String secuenciaFolio;
    
	/**
	 * @return el cabeceraPlanilla
	 */
	public PlanillaBase getCabeceraPlanilla() {
		return cabeceraPlanilla;
	}

	/**
	 * @param cabeceraPlanilla el cabeceraPlanilla a establecer
	 */
	public void setCabeceraPlanilla(PlanillaBase cabeceraPlanilla) {
		this.cabeceraPlanilla = cabeceraPlanilla;
	}
	
	/**
	 * @return el cotizantes
	 */
	public List getCotizantes() {
		return cotizantes;
	}

	/**
	 * @param cotizantes el cotizantes a establecer
	 */
	public void setCotizantes(List cotizantes) {
		this.cotizantes = cotizantes;
	}
	
	/**
	 * @return el paginaActual
	 */
	public int getPaginaActual() {
		return paginaActual;
	}

	/**
	 * @param paginaActual el paginaActual a establecer
	 */
	public void setPaginaActual(int paginaActual) {
		this.paginaActual = paginaActual;
	}

	/**
	 * @return el paginaFinal
	 */
	public int getPaginaFinal() {
		return paginaFinal;
	}

	/**
	 * @param paginaFinal el paginaFinal a establecer
	 */
	public void setPaginaFinal(int paginaFinal) {
		this.paginaFinal = paginaFinal;
	}

	/**
	 * @return el secuenciaFolio
	 */
	public String getSecuenciaFolio() {
		return secuenciaFolio;
	}

	/**
	 * @param secuenciaFolio el secuenciaFolio a establecer
	 */
	public void setSecuenciaFolio(String secuenciaFolio) {
		this.secuenciaFolio = secuenciaFolio;
	}
    
}
