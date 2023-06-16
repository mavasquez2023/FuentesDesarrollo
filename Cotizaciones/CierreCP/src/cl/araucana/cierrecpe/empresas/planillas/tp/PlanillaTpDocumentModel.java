

/*
 * @(#) PlanillaTpDocumentModel.java    1.0 08-08-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.planillas.tp;


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
 *            <TD> 08-08-2008 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> AUTHOR <BR> EMAIL </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author AUTHOR (EMAIL)
 *
 * @version 1.0
 */
public class PlanillaTpDocumentModel extends PlanillaBase
implements Constants  {

    /*
     * SUBSECCION III.1: FONDO DE PENSIONES
     */
    private long cotizacionTrabajosPesadosFdoPensiones=0;
//    private String reajustesFdoPensiones;
//    private String interesesFdoPensiones;
    private long totalAPagarFdoPensiones=0;

    /*
     * SUBSECCION III .2. A.F.P.
     */

//    private String recargo20PorcInteresAfp;
//    private String costasCobranzaAfp;
//    private long totalAPagarAfp;


    /*
     * SECCION IV - ANTECEDENTES GENERALES
     */

    //private AbsoluteDate fechaDeclaracion;
    private long totalRemuneraciones=0;
    private long totalGratificaciones=0;
    //private String tipoIngresoImponible;
    private String tipoPago;

    /*
     * SUBSECCION V - 1.PRIMER RECUADRO
     */

//    private int tipoPagoFdoPensiones = UNDEFINED_PROPERTY;
//    private String numeroChequeFdoPensiones;
//    private String bancoFdoPensiones;
//    private String plazaFdoPensiones;

    /*
     * SUBSECCION V - 2.SEGUNDO RECUADRO
     */
//    private int tipoPagoAfp = UNDEFINED_PROPERTY;
//    private String numeroChequeAfp;
//    private String bancoAfp;
//    private String plazaAfp;

    /*
     * Variable que será formateada con formatMonth, para reutilizarla
     * en otros metodos que requieren el mes como argumento.
     */
    private String mesPeriodoRemuneraciones;

    /**
	 * Constructor de PlanillaTpDocumentModel.
	 * @param comprobante : encabezado del comprobante
	 */
	public PlanillaTpDocumentModel(Comprobante_Encabezado comprobante) {
		super(comprobante);
	}

	/**
	 * @return el tipoIngresoImponible
	 */
	public String getTipoIngresoImponible() {
		if (!super.getTipoProceso().equals("G")){
			return "10";
		}else{
			return "01";
		}
	}

	/**
	 * @return el cotizacionTrabajosPesadosFdoPensiones
	 */
	public long getCotizacionTrabajosPesadosFdoPensiones() {
		return cotizacionTrabajosPesadosFdoPensiones;
	}

	/**
	 * @param cotizacionTrabajosPesadosFdoPensiones el cotizacionTrabajosPesadosFdoPensiones a establecer
	 */
	public void setCotizacionTrabajosPesadosFdoPensiones(
			long cotizacionTrabajosPesadosFdoPensiones) {
		this.cotizacionTrabajosPesadosFdoPensiones = cotizacionTrabajosPesadosFdoPensiones;
	}

	/**
	 * @return el mesPeriodoRemuneraciones
	 */
	public String getMesPeriodoRemuneraciones() {
		return mesPeriodoRemuneraciones;
	}

	/**
	 * @param mesPeriodoRemuneraciones el mesPeriodoRemuneraciones a establecer
	 */
	public void setMesPeriodoRemuneraciones(String mesPeriodoRemuneraciones) {
		this.mesPeriodoRemuneraciones = mesPeriodoRemuneraciones;
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
	 * @return el totalAPagarFdoPensiones
	 */
	public long getTotalAPagarFdoPensiones() {
		return totalAPagarFdoPensiones;
	}

	/**
	 * @param totalAPagarFdoPensiones el totalAPagarFdoPensiones a establecer
	 */
	public void setTotalAPagarFdoPensiones(long totalAPagarFdoPensiones) {
		this.totalAPagarFdoPensiones = totalAPagarFdoPensiones;
	}

	/**
	 * @return el totalGratificaciones
	 */
	public long getTotalGratificaciones() {
		return totalGratificaciones;
	}

	/**
	 * @param totalGratificaciones el totalGratificaciones a establecer
	 */
	public void setTotalGratificaciones(long totalGratificaciones) {
		this.totalGratificaciones = totalGratificaciones;
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