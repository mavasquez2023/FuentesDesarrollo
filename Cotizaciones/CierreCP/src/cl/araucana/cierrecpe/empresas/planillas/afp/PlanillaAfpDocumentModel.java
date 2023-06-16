

/*
 * @(#) PlanillaAfpDocumentModel.java    1.0 24-07-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.planillas.afp;

import cl.araucana.cierrecp.empresas.comprobantes.Comprobante_Encabezado;
import cl.araucana.cierrecpe.business.Constants;
import cl.araucana.cierrecpe.empresas.planillas.PlanillaBase;
import cl.araucana.core.util.AbsoluteDate;



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
public class PlanillaAfpDocumentModel extends PlanillaBase
		implements Constants {

	// Para el Tipo de Declaración y Pago de la Planilla a producir.
	private int filter;
	
    /*
     * SECCION III.1: FONDO DE PENSIONES
     */

    private long cotizacionObligFdoPensiones;
    private long depositosCtaAhorroFdoPensiones;
    private long seguroInvalidezFdoPensiones;
    private int interesesFdoPensiones;
    private int reajustesFdoPensiones;
    private long subtotalAPagarFdoPensiones;
    private long totalAPagarFdoPensiones;

    /*
     * SECCION III.2: A.F.P.
     */

    private int costasCobranzaAfc;
    private int recargo20PorcInteresAfp;
    private long totalAPagarAfp;

    /*
     * SECCION III.3: FONDO DE CESANTIA
     */

    private long cotizacionAfiliadoFdoCesantia;
    private long cotizacionEmpleadorFdoCesantia;
    private int interesesFdoCesantia;
    private int reajusteFdoCesantia;
    private long subtotalAPagarFdoCesantia;
    private long totalAPagarFdoCesantia;
    
    /*
     * SECCION III.3: OTRAS COTIZACIONES(INDEPENDIENTES)
     */
    private long cotizacionSalud=0;
    private long totalAPagarAFPSalud=0;
    
    /*
     * SECCION III.4: A.F.C.
     */

    private int costasCobranzaAfp;
    private long totalAPagarAfc;

    /*
     * SECCION IV: ANTECEDENTES GENERALES
     */

    private AbsoluteDate fechaDeclaracion;
    private int numeroAfiliadosFdoCesantia=0;
    private int numeroAfiliadosFdoPensiones=0;
    private long totalRemuneracionesOGratifFdoCesantia=0;
    private long totalRemuneracionesOGratifFdoPensiones=0;
    //private String tipoIngresoImponible;
    private String tipoPago;


	/**
	 * Constructor de PlanillaAfpDocumentModel.
	 * @param comprobante : encabezado del comprobante
	 */
	public PlanillaAfpDocumentModel(Comprobante_Encabezado comprobante) {
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
	 * @return el costasCobranzaAfc
	 */
	public int getFilter() {
		return filter;
	}

	/**
	 * @param costasCobranzaAfc el costasCobranzaAfc a establecer
	 */
	public void setFilter(int filter) {
		this.filter = filter;
	}
	
	/**
	 * @return el costasCobranzaAfc
	 */
	public int getCostasCobranzaAfc() {
		return costasCobranzaAfc;
	}

	/**
	 * @param costasCobranzaAfc el costasCobranzaAfc a establecer
	 */
	public void setCostasCobranzaAfc(int costasCobranzaAfc) {
		this.costasCobranzaAfc = costasCobranzaAfc;
	}

	/**
	 * @return el costasCobranzaAfp
	 */
	public int getCostasCobranzaAfp() {
		return costasCobranzaAfp;
	}

	/**
	 * @param costasCobranzaAfp el costasCobranzaAfp a establecer
	 */
	public void setCostasCobranzaAfp(int costasCobranzaAfp) {
		this.costasCobranzaAfp = costasCobranzaAfp;
	}

	/**
	 * @return el cotizacionAfiliadoFdoCesantia
	 */
	public long getCotizacionAfiliadoFdoCesantia() {
		return cotizacionAfiliadoFdoCesantia;
	}

	/**
	 * @param cotizacionAfiliadoFdoCesantia el cotizacionAfiliadoFdoCesantia a establecer
	 */
	public void setCotizacionAfiliadoFdoCesantia(long cotizacionAfiliadoFdoCesantia) {
		this.cotizacionAfiliadoFdoCesantia = cotizacionAfiliadoFdoCesantia;
	}

	/**
	 * @return el cotizacionEmpleadorFdoCesantia
	 */
	public long getCotizacionEmpleadorFdoCesantia() {
		return cotizacionEmpleadorFdoCesantia;
	}

	/**
	 * @param cotizacionEmpleadorFdoCesantia el cotizacionEmpleadorFdoCesantia a establecer
	 */
	public void setCotizacionEmpleadorFdoCesantia(
			long cotizacionEmpleadorFdoCesantia) {
		this.cotizacionEmpleadorFdoCesantia = cotizacionEmpleadorFdoCesantia;
	}

	/**
	 * @return el cotizacionObligFdoPensiones
	 */
	public long getCotizacionObligFdoPensiones() {
		return cotizacionObligFdoPensiones;
	}

	/**
	 * @param cotizacionObligFdoPensiones el cotizacionObligFdoPensiones a establecer
	 */
	public void setCotizacionObligFdoPensiones(long cotizacionObligFdoPensiones) {
		this.cotizacionObligFdoPensiones = cotizacionObligFdoPensiones;
	}

	/**
	 * @return el depositosCtaAhorroFdoPensiones
	 */
	public long getDepositosCtaAhorroFdoPensiones() {
		return depositosCtaAhorroFdoPensiones;
	}

	/**
	 * @param depositosCtaAhorroFdoPensiones el depositosCtaAhorroFdoPensiones a establecer
	 */
	public void setDepositosCtaAhorroFdoPensiones(
			long depositosCtaAhorroFdoPensiones) {
		this.depositosCtaAhorroFdoPensiones = depositosCtaAhorroFdoPensiones;
	}

	/**
	 * @return el fechaDeclaracion
	 */
	public AbsoluteDate getFechaDeclaracion() {
		return fechaDeclaracion;
	}

	/**
	 * @param fechaDeclaracion el fechaDeclaracion a establecer
	 */
	public void setFechaDeclaracion(AbsoluteDate fechaDeclaracion) {
		this.fechaDeclaracion = fechaDeclaracion;
	}

	/**
	 * @return el interesesFdoCesantia
	 */
	public int getInteresesFdoCesantia() {
		return interesesFdoCesantia;
	}

	/**
	 * @param interesesFdoCesantia el interesesFdoCesantia a establecer
	 */
	public void setInteresesFdoCesantia(int interesesFdoCesantia) {
		this.interesesFdoCesantia = interesesFdoCesantia;
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
	 * @return el numeroAfiliadosFdoCesantia
	 */
	public int getNumeroAfiliadosFdoCesantia() {
		return numeroAfiliadosFdoCesantia;
	}

	/**
	 * @param numeroAfiliadosFdoCesantia el numeroAfiliadosFdoCesantia a establecer
	 */
	public void setNumeroAfiliadosFdoCesantia(int numeroAfiliadosFdoCesantia) {
		this.numeroAfiliadosFdoCesantia = numeroAfiliadosFdoCesantia;
	}

	/**
	 * @return el numeroAfiliadosFdoPensiones
	 */
	public int getNumeroAfiliadosFdoPensiones() {
		return numeroAfiliadosFdoPensiones;
	}

	/**
	 * @param numeroAfiliadosFdoPensiones el numeroAfiliadosFdoPensiones a establecer
	 */
	public void setNumeroAfiliadosFdoPensiones(int numeroAfiliadosFdoPensiones) {
		this.numeroAfiliadosFdoPensiones = numeroAfiliadosFdoPensiones;
	}

	/**
	 * @return el reajusteFdoCesantia
	 */
	public int getReajusteFdoCesantia() {
		return reajusteFdoCesantia;
	}

	/**
	 * @param reajusteFdoCesantia el reajusteFdoCesantia a establecer
	 */
	public void setReajusteFdoCesantia(int reajusteFdoCesantia) {
		this.reajusteFdoCesantia = reajusteFdoCesantia;
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
	 * @return el recargo20PorcInteresAfp
	 */
	public int getRecargo20PorcInteresAfp() {
		return recargo20PorcInteresAfp;
	}

	/**
	 * @param recargo20PorcInteresAfp el recargo20PorcInteresAfp a establecer
	 */
	public void setRecargo20PorcInteresAfp(int recargo20PorcInteresAfp) {
		this.recargo20PorcInteresAfp = recargo20PorcInteresAfp;
	}

	/**
	 * @return el subtotalAPagarFdoCesantia
	 */
	public long getSubtotalAPagarFdoCesantia() {
		return subtotalAPagarFdoCesantia;
	}

	/**
	 * @param subtotalAPagarFdoCesantia el subtotalAPagarFdoCesantia a establecer
	 */
	public void setSubtotalAPagarFdoCesantia(long subtotalAPagarFdoCesantia) {
		this.subtotalAPagarFdoCesantia = subtotalAPagarFdoCesantia;
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
	 * @return el totalAPagarAfc
	 */
	public long getTotalAPagarAfc() {
		return totalAPagarAfc;
	}

	/**
	 * @param totalAPagarAfc el totalAPagarAfc a establecer
	 */
	public void setTotalAPagarAfc(long totalAPagarAfc) {
		this.totalAPagarAfc = totalAPagarAfc;
	}

	/**
	 * @return el totalAPagarAfp
	 */
	public long getTotalAPagarAfp() {
		return totalAPagarAfp;
	}

	/**
	 * @param totalAPagarAfp el totalAPagarAfp a establecer
	 */
	public void setTotalAPagarAfp(long totalAPagarAfp) {
		this.totalAPagarAfp = totalAPagarAfp;
	}

	/**
	 * @return el totalAPagarFdoCesantia
	 */
	public long getTotalAPagarFdoCesantia() {
		return totalAPagarFdoCesantia;
	}

	/**
	 * @param totalAPagarFdoCesantia el totalAPagarFdoCesantia a establecer
	 */
	public void setTotalAPagarFdoCesantia(long totalAPagarFdoCesantia) {
		this.totalAPagarFdoCesantia = totalAPagarFdoCesantia;
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
	 * @return el totalRemuneracionesOGratifFdoCesantia
	 */
	public long getTotalRemuneracionesOGratifFdoCesantia() {
		return totalRemuneracionesOGratifFdoCesantia;
	}

	/**
	 * @param totalRemuneracionesOGratifFdoCesantia el totalRemuneracionesOGratifFdoCesantia a establecer
	 */
	public void setTotalRemuneracionesOGratifFdoCesantia(
			long totalRemuneracionesOGratifFdoCesantia) {
		this.totalRemuneracionesOGratifFdoCesantia = totalRemuneracionesOGratifFdoCesantia;
	}

	/**
	 * @return el totalRemuneracionesOGratifFdoPensiones
	 */
	public long getTotalRemuneracionesOGratifFdoPensiones() {
		return totalRemuneracionesOGratifFdoPensiones;
	}

	/**
	 * @param totalRemuneracionesOGratifFdoPensiones el totalRemuneracionesOGratifFdoPensiones a establecer
	 */
	public void setTotalRemuneracionesOGratifFdoPensiones(
			long totalRemuneracionesOGratifFdoPensiones) {
		this.totalRemuneracionesOGratifFdoPensiones = totalRemuneracionesOGratifFdoPensiones;
	}

	/**
	 * @return el seguroInvalidezFdoPensiones
	 */
	public long getSeguroInvalidezFdoPensiones() {
		return seguroInvalidezFdoPensiones;
	}

	/**
	 * @param seguroInvalidezFdoPensiones el seguroInvalidezFdoPensiones a establecer
	 */
	public void setSeguroInvalidezFdoPensiones(long seguroInvalidezFdoPensiones) {
		this.seguroInvalidezFdoPensiones = seguroInvalidezFdoPensiones;
	}

	/**
	 * @return el cotizacionSalud
	 */
	public long getCotizacionSalud() {
		return cotizacionSalud;
	}

	/**
	 * @param cotizacionSalud el cotizacionSalud a establecer
	 */
	public void setCotizacionSalud(long cotizacionSalud) {
		this.cotizacionSalud = cotizacionSalud;
	}

	/**
	 * @return el totalAPagarAFPSalud
	 */
	public long getTotalAPagarAFPSalud() {
		return totalAPagarAFPSalud;
	}

	/**
	 * @param totalAPagarAFPSalud el totalAPagarAFPSalud a establecer
	 */
	public void setTotalAPagarAFPSalud(long totalAPagarAFPSalud) {
		this.totalAPagarAFPSalud = totalAPagarAFPSalud;
	}

	

}