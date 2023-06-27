

package cl.araucana.cierrecpe.empresas.planillas.otros;


import cl.araucana.cierrecp.empresas.comprobantes.Comprobante_Encabezado;
import cl.araucana.cierrecpe.business.Constants;
import cl.araucana.cierrecpe.empresas.planillas.PlanillaBase;

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

// TODO Verificar que atributo numeroHojasAnexas sea igual a
// largo de la lista paginasDetalle

public class PlanillaAfbrDocumentModel extends PlanillaBase implements Constants {

	/*
     * SECCION II: RESUMEN DE APORTES
     */

	private long cotizacionTotalAporte;
	private long cotizacionTotalAPagar;
//	private String reajustes;
//	private String intereses;
//	private String efectivo;
//	private String numeroCheque;
//	private String banco;
//	private String plaza;

	/*
     * SECCION III - ANTECEDENTES GENERALES 
     */
	private String tipoPago;
	private String tipoDeclaracion;
	 private long totalRemuneraciones=0;

	/**
	 * Constructor de PlanillaIsapreDocumentModel.
	 * @param comprobante : encabezado del comprobante
	 */
    public PlanillaAfbrDocumentModel(Comprobante_Encabezado comprobante) {
		super(comprobante);
		// TODO Apéndice de constructor generado automáticamente
	}

	/**
	 * @return el cotizacionTotalAPagar
	 */
	public long getCotizacionTotalAPagar() {
		return cotizacionTotalAPagar;
	}

	/**
	 * @param cotizacionTotalAPagar el cotizacionTotalAPagar a establecer
	 */
	public void setCotizacionTotalAPagar(long cotizacionTotalAPagar) {
		this.cotizacionTotalAPagar = cotizacionTotalAPagar;
	}

	/**
	 * @return el cotizacionTotalAporte
	 */
	public long getCotizacionTotalAporte() {
		return cotizacionTotalAporte;
	}

	/**
	 * @param cotizacionTotalAporte el cotizacionTotalAporte a establecer
	 */
	public void setCotizacionTotalAporte(long cotizacionTotalAporte) {
		this.cotizacionTotalAporte = cotizacionTotalAporte;
	}

	/**
	 * @return el tipoDeclaracion
	 */
	public String getTipoDeclaracion() {
		return tipoDeclaracion;
	}

	/**
	 * @param tipoDeclaracion el tipoDeclaracion a establecer
	 */
	public void setTipoDeclaracion(String tipoDeclaracion) {
		this.tipoDeclaracion = tipoDeclaracion;
	}

	/**
	 * @return el tipoPago
	 */
	public String getTipoPago() {
		return tipoPago;
	}

	/**
	 * @param tipoPago el tipoPago a establecer
	 */
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	/**
	 * @return el totalRemuneraciones
	 */
	public long getTotalRemuneraciones() {
		return totalRemuneraciones;
	}

	/**
	 * @param totalRemuneraciones el totalRemuneraciones a establecer
	 */
	public void setTotalRemuneraciones(long totalRemuneraciones) {
		this.totalRemuneraciones = totalRemuneraciones;
	}
	    
    
	}
