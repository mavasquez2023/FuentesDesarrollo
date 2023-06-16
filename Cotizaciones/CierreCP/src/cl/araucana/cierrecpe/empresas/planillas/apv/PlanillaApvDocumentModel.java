

/*
 * @(#) PlanillaApvDocumentModel.java    1.0 04-08-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.planillas.apv;

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
 *            <TD> 04-08-2008 </TD>
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
public class PlanillaApvDocumentModel extends PlanillaBase implements Constants{

//	 Para el Tipo de Declaración y Pago de la Planilla a producir.
	private int filter;
	
	/*
     * SUBSECCION III.1: FONDO DE PENSIONES
     */
    private long cotizacionVoluntariaFdoPensiones=0;
    private long depositoConvenidoFdoPensiones=0;
    private long aporteIndemnizacionFdoPensiones=0;
    private long subtotalAPagarFdoPensiones=0;
    private int reajustesFdoPensiones=0;
    private int interesesFdoPensiones=0;
    private long totalAPagarFdoPensiones=0;
    
    /*
     * APV COLECTIVO
     */
    private long aporteEmpleadorFdoPensionesColectivo=0;
    private long aporteTrabajadorFdoPensionesColectivo=0;
    
    
    /*
     * SUBSECCION III .2. A.F.P. 
     */
    
    //private int recargo20PorcInteresAfp;
    //private int costasCobranzaAfp;
    
    
    /*
     * SECCION IV - ANTECEDENTES GENERALES 
     */
    private long totalRemuneracionesOGratificaciones=0;
    private String tipoPago;
    
    /**
	 * Constructor de PlanillaApvDocumentModel.
	 * @param comprobante : encabezado del comprobante
	 */
    public PlanillaApvDocumentModel(Comprobante_Encabezado comprobante) {
		super(comprobante);
		// TODO Apéndice de constructor generado automáticamente
	}
    
    /**
	 * @return el tipoIngresoImponible
	 */
	public String getTipoIngresoImponible() {
		return "10";
	}
	
	/**
	 * @return el aporteEmpleadorFdoPensionesColectivo
	 */
	public long getAporteEmpleadorFdoPensionesColectivo() {
		return aporteEmpleadorFdoPensionesColectivo;
	}
	/**
	 * @param aporteEmpleadorFdoPensionesColectivo el aporteEmpleadorFdoPensionesColectivo a establecer
	 */
	public void setAporteEmpleadorFdoPensionesColectivo(
			long aporteEmpleadorFdoPensionesColectivo) {
		this.aporteEmpleadorFdoPensionesColectivo = aporteEmpleadorFdoPensionesColectivo;
	}
	/**
	 * @return el aporteIndemnizacionFdoPensiones
	 */
	public long getAporteIndemnizacionFdoPensiones() {
		return aporteIndemnizacionFdoPensiones;
	}
	/**
	 * @param aporteIndemnizacionFdoPensiones el aporteIndemnizacionFdoPensiones a establecer
	 */
	public void setAporteIndemnizacionFdoPensiones(
			long aporteIndemnizacionFdoPensiones) {
		this.aporteIndemnizacionFdoPensiones = aporteIndemnizacionFdoPensiones;
	}
	/**
	 * @return el aporteTrabajadorFdoPensionesColectivo
	 */
	public long getAporteTrabajadorFdoPensionesColectivo() {
		return aporteTrabajadorFdoPensionesColectivo;
	}
	/**
	 * @param aporteTrabajadorFdoPensionesColectivo el aporteTrabajadorFdoPensionesColectivo a establecer
	 */
	public void setAporteTrabajadorFdoPensionesColectivo(
			long aporteTrabajadorFdoPensionesColectivo) {
		this.aporteTrabajadorFdoPensionesColectivo = aporteTrabajadorFdoPensionesColectivo;
	}
	/**
	 * @return el cotizacionVoluntariaFdoPensiones
	 */
	public long getCotizacionVoluntariaFdoPensiones() {
		return cotizacionVoluntariaFdoPensiones;
	}
	/**
	 * @param cotizacionVoluntariaFdoPensiones el cotizacionVoluntariaFdoPensiones a establecer
	 */
	public void setCotizacionVoluntariaFdoPensiones(
			long cotizacionVoluntariaFdoPensiones) {
		this.cotizacionVoluntariaFdoPensiones = cotizacionVoluntariaFdoPensiones;
	}
	/**
	 * @return el depositoConvenidoFdoPensiones
	 */
	public long getDepositoConvenidoFdoPensiones() {
		return depositoConvenidoFdoPensiones;
	}
	/**
	 * @param depositoConvenidoFdoPensiones el depositoConvenidoFdoPensiones a establecer
	 */
	public void setDepositoConvenidoFdoPensiones(long depositoConvenidoFdoPensiones) {
		this.depositoConvenidoFdoPensiones = depositoConvenidoFdoPensiones;
	}
	/**
	 * @return el interesesFdoPensiones
	 */
	public int getInteresesFdoPensiones() {
		return interesesFdoPensiones;
	}
	/**
	 * @param interesesFdoPensiones el interesesFdoPensiones a establecer
	 */
	public void setInteresesFdoPensiones(int interesesFdoPensiones) {
		this.interesesFdoPensiones = interesesFdoPensiones;
	}
	/**
	 * @return el reajustesFdoPensiones
	 */
	public int getReajustesFdoPensiones() {
		return reajustesFdoPensiones;
	}
	/**
	 * @param reajustesFdoPensiones el reajustesFdoPensiones a establecer
	 */
	public void setReajustesFdoPensiones(int reajustesFdoPensiones) {
		this.reajustesFdoPensiones = reajustesFdoPensiones;
	}
	/**
	 * @return el subtotalAPagarFdoPensiones
	 */
	public long getSubtotalAPagarFdoPensiones() {
		return subtotalAPagarFdoPensiones;
	}
	/**
	 * @param subtotalAPagarFdoPensiones el subtotalAPagarFdoPensiones a establecer
	 */
	public void setSubtotalAPagarFdoPensiones(long subtotalAPagarFdoPensiones) {
		this.subtotalAPagarFdoPensiones = subtotalAPagarFdoPensiones;
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
	 * @return el totalRemuneracionesOGratifFdoPensiones
	 */
	public long getTotalRemuneracionesOGratificaciones() {
		return totalRemuneracionesOGratificaciones;
	}
	/**
	 * @param totalRemuneracionesOGratifFdoPensiones el totalRemuneracionesOGratifFdoPensiones a establecer
	 */
	public void setTotalRemuneracionesOGratificaciones(
			long totalRemuneracionesOGratificaciones) {
		this.totalRemuneracionesOGratificaciones = totalRemuneracionesOGratificaciones;
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
	 * @return el filter
	 */
	public int getFilter() {
		return filter;
	}

	/**
	 * @param filter el filter a establecer
	 */
	public void setFilter(int filter) {
		this.filter = filter;
	}
    
    
}