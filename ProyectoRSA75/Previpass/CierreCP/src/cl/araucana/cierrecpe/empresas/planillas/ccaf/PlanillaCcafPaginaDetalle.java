

/*
 * @(#) PlanillaCcafPaginaDetalle.java    1.0 13-ago-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.planillas.ccaf;

import java.util.ArrayList;
import java.util.List;

import cl.araucana.cierrecpe.business.Constants;
import cl.araucana.cierrecpe.empresas.planillas.PlanillaBase;
import cl.araucana.cierrecpe.empresas.planillas.PlanillaBasePaginaDetalle;
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
public class PlanillaCcafPaginaDetalle extends PlanillaBasePaginaDetalle implements Constants {

	
	private List cotizantes;
	private PlanillaCcafDocumentModel cabeceraPlanilla;
	private int tipoDetalle;
	/*
     * HEADER DE LA PAGINA DETALLE
     */

    private String folioDetalle;
    
    public void addCotizante(PlanillaCcafCotizante cotizante){
    	if (cotizantes==null){
    		cotizantes= new ArrayList();
    	}
    	cotizantes.add(cotizante);
    }
    
	/**
	 * @return el cabeceraPlanilla
	 */
	public PlanillaBase getCabeceraPlanilla() {
		return cabeceraPlanilla;
	}
	/**
	 * @param cabeceraPlanilla el cabeceraPlanilla a establecer
	 */
	public void setCabeceraPlanilla(PlanillaCcafDocumentModel cabeceraPlanilla) {
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
	 * @return el tipoDetalle
	 */
	public int getTipoDetalle() {
		return tipoDetalle;
	}

	/**
	 * @param tipoDetalle el tipoDetalle a establecer
	 */
	public void setTipoDetalle(int tipoDetalle) {
		this.tipoDetalle = tipoDetalle;
	}

	/**
	 * @return el folioDetalle
	 */
	public String getFolioDetalle() {
		int folio= Integer.parseInt(getCabeceraPlanilla().getFolio());
		switch (getTipoDetalle()) {
		case CCAF_ATTACHMENT_CREDITO:
			folio= folio + 1;
			break;
		case CCAF_ATTACHMENT_LEASING:
			folio= folio + 2;
			break;
		case CCAF_ATTACHMENT_CONVENIO_DENTAL:
			folio= folio + 3;
			break;
		case CCAF_ATTACHMENT_SEGURO_VIDA:
			folio= folio + 4;
			break;
		}
		return Formato.padding(folio, 7);
	}

	/**
	 * @param folioDetalle el folioDetalle a establecer
	 */
	public void setFolioDetalle(String folioDetalle) {
		this.folioDetalle = folioDetalle;
	}
    
	
	/*
     * TOTALES PAGINA DETALLE
     */
    /*
    private long totalPaginaAsigFamiliarCotizante;
    private long totalPaginaCantidadCargasInvlCotizante;
    private long totalPaginaCantidadCargasMatCotizante;
    private long totalPaginaCantidadCargasSimpCotizante;
    private long totalPaginaRemImponibleAfiliadosIsapreCotizante;
    private long totalPaginaRemImponibleNoAfiliadosIsapreCotizante;
//  private String totalPaginaAsigFamiliarReintegroCotizante;
//  private String totalPaginaAsigFamiliarRetroactivoCotizante;
    
    private long totalPlanillaAsigFamiliarCotizante;
    private long totalPlanillaCantidadCargasInvlCotizante;
    private long totalPlanillaCantidadCargasMatCotizante;
    private long totalPlanillaCantidadCargasSimpCotizante;
    private long totalPlanillaRemImponibleAfiliadosIsapreCotizante;
    private long totalPlanillaRemImponibleNoAfiliadosIsapreCotizante;
//  private String totalPlanillaAsigFamiliarReintegroCotizante;
//  private String totalPlanillaAsigFamiliarRetroactivoCotizante;
    
    */
    
}
