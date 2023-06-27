

/*
 * @(#) PlanillaCcafCotizante.java    1.0 13-ago-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.planillas.ccaf;

import cl.araucana.cierrecpe.empresas.planillas.PlanillaBaseCotizante;
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
public class PlanillaCcafCotizante extends PlanillaBaseCotizante{

	 /*
     * COTIZANTE 
     */
	private int codigoSexo;
    private int entidadSalud;
    private char nacionalidad;
    
    private int remuneracionImponibleCotizante;
    private int diasTrabajadosCotizante;
    private int cantidadCargasSimCotizante;
    private int cantidadCargasInvCotizante;
    private int cantidadCargasMatCotizante;
    private int montoAsigFamiliarCotizante;
    private int montoAsigFamiliarReintegroCotizante;
    private int montoAsigFamiliarRetroactivoCotizante;
    private int monto06Cotizante;
    private int montoOtrosCotizante;
    private char codigoTramo='0';
    private int codigoMovimiento;
    private AbsoluteDate fechaDesdeAsigFamiliarCotizante;
    private AbsoluteDate fechaHastaAsigFamiliarCotizante;
    
    private int numeroLineaDetalleCredito;
    private int montoCuotaCredito;
    private int numeroLineaDetalleLeasing;
    private int montoCuotaLeasing;
    private int numeroLineaDetalleSeguroVida;
    private int montoCuotaSeguroVida;
    private int numeroLineaDetalleConvenioDental;
    private int montoCuotaConvenioDental;
	
//  Gratificaciones
	 private AbsoluteDate fechaInicioGratificacionesCotizante;
	 private AbsoluteDate fechaTerminoGratificacionesCotizante;
    
    public int getRemImponibleNoAfiliadosIsapreCotizante(){
    	if(getEntidadSalud()<=0){
    		return getRemuneracionImponibleCotizante();
    	}else{
    		return 0;
    	}
    }
    public int getRemImponibleAfiliadosIsapreCotizante(){
    	if(getEntidadSalud()>0){
    		return getRemuneracionImponibleCotizante();
    	}else{
    		return 0;
    	}
    }
   
	/**
	 * @return el cantidadCargasInvlCotizante
	 */
	public int getCantidadCargasInvCotizante() {
		return cantidadCargasInvCotizante;
	}
	/**
	 * @param cantidadCargasInvlCotizante el cantidadCargasInvlCotizante a establecer
	 */
	public void setCantidadCargasInvCotizante(int cantidadCargasInvCotizante) {
		this.cantidadCargasInvCotizante = cantidadCargasInvCotizante;
	}
	/**
	 * @return el cantidadCargasMatCotizante
	 */
	public int getCantidadCargasMatCotizante() {
		return cantidadCargasMatCotizante;
	}
	/**
	 * @param cantidadCargasMatCotizante el cantidadCargasMatCotizante a establecer
	 */
	public void setCantidadCargasMatCotizante(int cantidadCargasMatCotizante) {
		this.cantidadCargasMatCotizante = cantidadCargasMatCotizante;
	}
	/**
	 * @return el cantidadCargasSimpCotizante
	 */
	public int getCantidadCargasSimCotizante() {
		return cantidadCargasSimCotizante;
	}
	/**
	 * @param cantidadCargasSimpCotizante el cantidadCargasSimpCotizante a establecer
	 */
	public void setCantidadCargasSimCotizante(int cantidadCargasSimCotizante) {
		this.cantidadCargasSimCotizante = cantidadCargasSimCotizante;
	}
	/**
	 * @return el codigoMovimiento
	 */
	public int getCodigoMovimiento() {
		return codigoMovimiento;
	}
	/**
	 * @param codigoMovimiento el codigoMovimiento a establecer
	 */
	public void setCodigoMovimiento(int codigoMovimiento) {
		this.codigoMovimiento = codigoMovimiento;
	}
	/**
	 * @return el codigoTramo
	 */
	public char getCodigoTramo() {
		return codigoTramo;
	}
	/**
	 * @param codigoTramo el codigoTramo a establecer
	 */
	public void setCodigoTramo(char codigoTramo) {
		this.codigoTramo = codigoTramo;
	}
	/**
	 * @return el diasTrabajadosCotizante
	 */
	public int getDiasTrabajadosCotizante() {
		return diasTrabajadosCotizante;
	}
	/**
	 * @param diasTrabajadosCotizante el diasTrabajadosCotizante a establecer
	 */
	public void setDiasTrabajadosCotizante(int diasTrabajadosCotizante) {
		this.diasTrabajadosCotizante = diasTrabajadosCotizante;
	}
	/**
	 * @return el fechaDesdeAsigFamiliarCotizante
	 */
	public AbsoluteDate getFechaDesdeAsigFamiliarCotizante() {
		return fechaDesdeAsigFamiliarCotizante;
	}
	/**
	 * @param fechaDesdeAsigFamiliarCotizante el fechaDesdeAsigFamiliarCotizante a establecer
	 */
	public void setFechaDesdeAsigFamiliarCotizante(
			AbsoluteDate fechaDesdeAsigFamiliarCotizante) {
		this.fechaDesdeAsigFamiliarCotizante = fechaDesdeAsigFamiliarCotizante;
	}
	/**
	 * @return el fechaHastaAsigFamiliarCotizante
	 */
	public AbsoluteDate getFechaHastaAsigFamiliarCotizante() {
		return fechaHastaAsigFamiliarCotizante;
	}
	/**
	 * @param fechaHastaAsigFamiliarCotizante el fechaHastaAsigFamiliarCotizante a establecer
	 */
	public void setFechaHastaAsigFamiliarCotizante(
			AbsoluteDate fechaHastaAsigFamiliarCotizante) {
		this.fechaHastaAsigFamiliarCotizante = fechaHastaAsigFamiliarCotizante;
	}
	
	/**
	 * @return el montoAsigFamiliarCotizante
	 */
	public int getMontoAsigFamiliarCotizante() {
		return montoAsigFamiliarCotizante;
	}
	/**
	 * @param montoAsigFamiliarCotizante el montoAsigFamiliarCotizante a establecer
	 */
	public void setMontoAsigFamiliarCotizante(int montoAsigFamiliarCotizante) {
		this.montoAsigFamiliarCotizante = montoAsigFamiliarCotizante;
	}
	/**
	 * @return el montoAsigFamiliarReintegroCotizante
	 */
	public int getMontoAsigFamiliarReintegroCotizante() {
		return montoAsigFamiliarReintegroCotizante;
	}
	/**
	 * @param montoAsigFamiliarReintegroCotizante el montoAsigFamiliarReintegroCotizante a establecer
	 */
	public void setMontoAsigFamiliarReintegroCotizante(
			int montoAsigFamiliarReintegroCotizante) {
		this.montoAsigFamiliarReintegroCotizante = montoAsigFamiliarReintegroCotizante;
	}
	/**
	 * @return el montoAsigFamiliarRetroactivoCotizante
	 */
	public int getMontoAsigFamiliarRetroactivoCotizante() {
		return montoAsigFamiliarRetroactivoCotizante;
	}
	/**
	 * @param montoAsigFamiliarRetroactivoCotizante el montoAsigFamiliarRetroactivoCotizante a establecer
	 */
	public void setMontoAsigFamiliarRetroactivoCotizante(
			int montoAsigFamiliarRetroactivoCotizante) {
		this.montoAsigFamiliarRetroactivoCotizante = montoAsigFamiliarRetroactivoCotizante;
	}
	/**
	 * @return el remImponibleAfiliadosIsapreCotizante
	 */
	public int getRemuneracionImponibleCotizante() {
		return remuneracionImponibleCotizante;
	}
	/**
	 * @param remImponibleAfiliadosIsapreCotizante el remImponibleAfiliadosIsapreCotizante a establecer
	 */
	public void setRemuneracionImponibleCotizante(
			int remuneracionImponibleCotizante) {
		this.remuneracionImponibleCotizante = remuneracionImponibleCotizante;
	}
	/**
	 * @return el montoCuotaConvenioDental
	 */
	public int getMontoCuotaConvenioDental() {
		return montoCuotaConvenioDental;
	}
	/**
	 * @param montoCuotaConvenioDental el montoCuotaConvenioDental a establecer
	 */
	public void setMontoCuotaConvenioDental(int montoCuotaConvenioDental) {
		this.montoCuotaConvenioDental = montoCuotaConvenioDental;
	}
	/**
	 * @return el montoCuotaCredito
	 */
	public int getMontoCuotaCredito() {
		return montoCuotaCredito;
	}
	/**
	 * @param montoCuotaCredito el montoCuotaCredito a establecer
	 */
	public void setMontoCuotaCredito(int montoCuotaCredito) {
		this.montoCuotaCredito = montoCuotaCredito;
	}
	/**
	 * @return el montoCuotaLeasing
	 */
	public int getMontoCuotaLeasing() {
		return montoCuotaLeasing;
	}
	/**
	 * @param montoCuotaLeasing el montoCuotaLeasing a establecer
	 */
	public void setMontoCuotaLeasing(int montoCuotaLeasing) {
		this.montoCuotaLeasing = montoCuotaLeasing;
	}
	/**
	 * @return el montoCuotaSeguroVida
	 */
	public int getMontoCuotaSeguroVida() {
		return montoCuotaSeguroVida;
	}
	/**
	 * @param montoCuotaSeguroVida el montoCuotaSeguroVida a establecer
	 */
	public void setMontoCuotaSeguroVida(int montoCuotaSeguroVida) {
		this.montoCuotaSeguroVida = montoCuotaSeguroVida;
	}
	/**
	 * @return el codigoSexo
	 */
	public int getCodigoSexo() {
		return codigoSexo;
	}
	/**
	 * @param codigoSexo el codigoSexo a establecer
	 */
	public void setCodigoSexo(int codigoSexo) {
		this.codigoSexo = codigoSexo;
	}
	/**
	 * @return el monto06Cotizante
	 */
	public int getMonto06Cotizante() {
		return monto06Cotizante;
	}
	/**
	 * @param monto06Cotizante el monto06Cotizante a establecer
	 */
	public void setMonto06Cotizante(int monto06Cotizante) {
		this.monto06Cotizante = monto06Cotizante;
	}
	/**
	 * @return el montoOtrosCotizante
	 */
	public int getMontoOtrosCotizante() {
		return montoOtrosCotizante;
	}
	/**
	 * @param montoOtrosCotizante el montoOtrosCotizante a establecer
	 */
	public void setMontoOtrosCotizante(int montoOtrosCotizante) {
		this.montoOtrosCotizante = montoOtrosCotizante;
	}
	/**
	 * @return el entidadSalud
	 */
	public int getEntidadSalud() {
		return entidadSalud;
	}
	/**
	 * @param entidadSalud el entidadSalud a establecer
	 */
	public void setEntidadSalud(int entidadSalud) {
		this.entidadSalud = entidadSalud;
	}
	/**
	 * @return el numeroLineaDetalleConvenioDental
	 */
	public int getNumeroLineaDetalleConvenioDental() {
		return numeroLineaDetalleConvenioDental;
	}
	/**
	 * @param numeroLineaDetalleConvenioDental el numeroLineaDetalleConvenioDental a establecer
	 */
	public void setNumeroLineaDetalleConvenioDental(
			int numeroLineaDetalleConvenioDental) {
		this.numeroLineaDetalleConvenioDental = numeroLineaDetalleConvenioDental;
	}
	/**
	 * @return el numeroLineaDetalleCredito
	 */
	public int getNumeroLineaDetalleCredito() {
		return numeroLineaDetalleCredito;
	}
	/**
	 * @param numeroLineaDetalleCredito el numeroLineaDetalleCredito a establecer
	 */
	public void setNumeroLineaDetalleCredito(int numeroLineaDetalleCredito) {
		this.numeroLineaDetalleCredito = numeroLineaDetalleCredito;
	}
	/**
	 * @return el numeroLineaDetalleLeasing
	 */
	public int getNumeroLineaDetalleLeasing() {
		return numeroLineaDetalleLeasing;
	}
	/**
	 * @param numeroLineaDetalleLeasing el numeroLineaDetalleLeasing a establecer
	 */
	public void setNumeroLineaDetalleLeasing(int numeroLineaDetalleLeasing) {
		this.numeroLineaDetalleLeasing = numeroLineaDetalleLeasing;
	}
	/**
	 * @return el numeroLineaDetalleSeguroVida
	 */
	public int getNumeroLineaDetalleSeguroVida() {
		return numeroLineaDetalleSeguroVida;
	}
	/**
	 * @param numeroLineaDetalleSeguroVida el numeroLineaDetalleSeguroVida a establecer
	 */
	public void setNumeroLineaDetalleSeguroVida(int numeroLineaDetalleSeguroVida) {
		this.numeroLineaDetalleSeguroVida = numeroLineaDetalleSeguroVida;
	}
	/**
	 * @return el nacionalidad
	 */
	public char getNacionalidad() {
		return nacionalidad;
	}
	/**
	 * @param nacionalidad el nacionalidad a establecer
	 */
	public void setNacionalidad(char nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	/**
	 * @return el fechaInicioGratificacionesCotizante
	 */
	public AbsoluteDate getFechaInicioGratificacionesCotizante() {
		return fechaInicioGratificacionesCotizante;
	}
	/**
	 * @param fechaInicioGratificacionesCotizante el fechaInicioGratificacionesCotizante a establecer
	 */
	public void setFechaInicioGratificacionesCotizante(
			AbsoluteDate fechaInicioGratificacionesCotizante) {
		this.fechaInicioGratificacionesCotizante = fechaInicioGratificacionesCotizante;
	}
	/**
	 * @return el fechaTerminoGratificacionesCotizante
	 */
	public AbsoluteDate getFechaTerminoGratificacionesCotizante() {
		return fechaTerminoGratificacionesCotizante;
	}
	/**
	 * @param fechaTerminoGratificacionesCotizante el fechaTerminoGratificacionesCotizante a establecer
	 */
	public void setFechaTerminoGratificacionesCotizante(
			AbsoluteDate fechaTerminoGratificacionesCotizante) {
		this.fechaTerminoGratificacionesCotizante = fechaTerminoGratificacionesCotizante;
	}
    
    }
