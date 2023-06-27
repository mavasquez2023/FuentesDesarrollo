

package cl.araucana.cierrecpe.empresas.planillas.isapre;


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

public class PlanillaIsapreDocumentModel extends PlanillaBase implements Constants {

	/*
     * SECCION C: ANTECEDENTES DE LA COTIZACION
     */
	private long cotizacionLegal;
	private long cotizacionLey18566;
	private long cotizacionAdicionalVoluntaria;
	private long cotizacionTotalAPagar;
//	private String reajustes;
//	private String intereses;
	private long subtotal;
//	private String efectivo;
//	private String numeroCheque;
//	private String banco;
//	private String plaza;

	/*
     * SECCION D - ANTECEDENTES GENERALES 
     */
	private int tipoPago;
	private String tipoDeclaracion;
	private String tipoEntePagador;

	/**
	 * Constructor de PlanillaIsapreDocumentModel.
	 * @param comprobante : encabezado del comprobante
	 */
    public PlanillaIsapreDocumentModel(Comprobante_Encabezado comprobante) {
		super(comprobante);
		// TODO Apéndice de constructor generado automáticamente
	}
	/**
	 * @return el cotizacionAdicionalVoluntaria
	 */
	public long getCotizacionAdicionalVoluntaria() {
		return cotizacionAdicionalVoluntaria;
	}

	/**
	 * @param cotizacionAdicionalVoluntaria el cotizacionAdicionalVoluntaria a establecer
	 */
	public void setCotizacionAdicionalVoluntaria(long cotizacionAdicionalVoluntaria) {
		this.cotizacionAdicionalVoluntaria = cotizacionAdicionalVoluntaria;
	}

	/**
	 * @return el cotizacionLegal
	 */
	public long getCotizacionLegal() {
		return cotizacionLegal;
	}

	/**
	 * @param cotizacionLegal el cotizacionLegal a establecer
	 */
	public void setCotizacionLegal(long cotizacionLegal) {
		this.cotizacionLegal = cotizacionLegal;
	}

	/**
	 * @return el cotizacionLey18566
	 */
	public long getCotizacionLey18566() {
		return cotizacionLey18566;
	}

	/**
	 * @param cotizacionLey18566 el cotizacionLey18566 a establecer
	 */
	public void setCotizacionLey18566(long cotizacionLey18566) {
		this.cotizacionLey18566 = cotizacionLey18566;
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
	 * @return el subtotal
	 */
	public long getSubtotal() {
		return subtotal;
	}

	/**
	 * @param subtotal el subtotal a establecer
	 */
	public void setSubtotal(long subtotal) {
		this.subtotal = subtotal;
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
	 * @return el tipoEntePagador
	 */
	public String getTipoEntePagador() {
		return tipoEntePagador;
	}

	/**
	 * @param tipoEntePagador el tipoEntePagador a establecer
	 */
	public void setTipoEntePagador(String tipoEntePagador) {
		this.tipoEntePagador = tipoEntePagador;
	}

	/**
	 * @return el tipoPago
	 */
	public int getTipoPago() {
		return tipoPago;
	}

	/**
	 * @param tipoPago el tipoPago a establecer
	 */
	public void setTipoPago(int tipoPago) {
		this.tipoPago = tipoPago;
	}
    
    
	}
