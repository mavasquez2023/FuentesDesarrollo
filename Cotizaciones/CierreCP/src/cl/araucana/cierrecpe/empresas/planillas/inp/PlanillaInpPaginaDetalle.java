

/*
 * @(#) PlanillaInpPaginaDetalle.java    1.0 18-ago-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.planillas.inp;

import java.util.ArrayList;
import java.util.List;

import cl.araucana.cierrecpe.empresas.planillas.PlanillaBase;
import cl.araucana.cierrecpe.empresas.planillas.PlanillaBasePaginaDetalle;



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
public class PlanillaInpPaginaDetalle extends PlanillaBasePaginaDetalle{
	
	private List cotizantes;
	private PlanillaInpDocumentModel cabeceraPlanilla;
	
    
    /*
     * Sección totales Página y General (Acumulativo)
     */
    private long totalRemuneraciones;
    private long totalPensiones;
    private long totalFonasa;
    private long totalAccidente;
    private long totalRemuneracionesDesahucio;
    private long totalCotizacionDesahucio;
    private long totalMontoSimple;
    private long totalMontoInvalida;
    private long totalMontoMaternal;
    private long totalBonificacion15366;
    private long totalGeneralRemuneraciones;
    private long totalGeneralPensiones;
    private long totalGeneralFonasa;
    private long totalGeneralAccidente;
    private long totalGeneralRemuneracionesDesahucio;
    private long totalGeneralCotizacionDesahucio;
    private long totalGeneralMontoSimple;
    private long totalGeneralMontoInvalida;
    private long totalGeneralMontoMaternal;
    private long totalGeneralBonificacion15366;
    
    
    public void addCotizante(PlanillaInpCotizante cotizante){
    	if (cotizantes==null){
    		cotizantes= new ArrayList();
    	}
    	cotizantes.add(cotizante);
    	setTotalRemuneraciones(cotizante.getRemImponibleCotizante());
    	setTotalPensiones(cotizante.getPensionInpCotizante());
    	setTotalFonasa(cotizante.getFonasaCotizante());
    	setTotalAccidente(cotizante.getAccDelTrabajoCotizante());
    	setTotalRemuneracionesDesahucio(cotizante.getRemImponibleDesahucioCotizante());
    	setTotalCotizacionDesahucio(cotizante.getCotizacionDesahucioCotizante());
    	setTotalMontoSimple(cotizante.getMontoAsigFamiliarSimpleCotizante());
    	setTotalMontoInvalida(cotizante.getMontoAsigFamiliarInvalidaCotizante());
    	setTotalMontoMaternal(cotizante.getMontoAsigFamiliarMaternalCotizante());
    	setTotalBonificacion15366(cotizante.getMontoBonificacionLey15385Cotizante());
    	setTotalGeneralRemuneraciones(cotizante.getRemImponibleCotizante());
    	setTotalGeneralPensiones(cotizante.getPensionInpCotizante());
    	setTotalGeneralFonasa(cotizante.getFonasaCotizante());
    	setTotalGeneralAccidente(cotizante.getAccDelTrabajoCotizante());
    	setTotalGeneralRemuneracionesDesahucio(cotizante.getRemImponibleDesahucioCotizante());
    	setTotalGeneralCotizacionDesahucio(cotizante.getCotizacionDesahucioCotizante());
    	setTotalGeneralMontoSimple(cotizante.getMontoAsigFamiliarSimpleCotizante());
    	setTotalGeneralMontoInvalida(cotizante.getMontoAsigFamiliarInvalidaCotizante());
    	setTotalGeneralMontoMaternal(cotizante.getMontoAsigFamiliarMaternalCotizante());
    	setTotalGeneralBonificacion15366(cotizante.getMontoBonificacionLey15385Cotizante());
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
	public void setCabeceraPlanilla(PlanillaInpDocumentModel cabeceraPlanilla) {
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
	 * @return el totalAccidente
	 */
	public long getTotalAccidente() {
		return totalAccidente;
	}

	/**
	 * @param totalAccidente el totalAccidente a establecer
	 */
	private void setTotalAccidente(long totalAccidente) {
		this.totalAccidente = this.totalAccidente + totalAccidente;
	}

	/**
	 * @return el totalBonificacion15366
	 */
	public long getTotalBonificacion15366() {
		return totalBonificacion15366;
	}

	/**
	 * @param totalBonificacion15366 el totalBonificacion15366 a establecer
	 */
	private void setTotalBonificacion15366(long totalBonificacion15366) {
		this.totalBonificacion15366 = this.totalBonificacion15366 + totalBonificacion15366;
	}

	/**
	 * @return el totalCotizacionDesahucio
	 */
	public long getTotalCotizacionDesahucio() {
		return totalCotizacionDesahucio;
	}

	/**
	 * @param totalCotizacionDesahucio el totalCotizacionDesahucio a establecer
	 */
	private void setTotalCotizacionDesahucio(long totalCotizacionDesahucio) {
		this.totalCotizacionDesahucio = this.totalCotizacionDesahucio + totalCotizacionDesahucio;
	}

	/**
	 * @return el totalFonasa
	 */
	public long getTotalFonasa() {
		return totalFonasa;
	}

	/**
	 * @param totalFonasa el totalFonasa a establecer
	 */
	private void setTotalFonasa(long totalFonasa) {
		this.totalFonasa = this.totalFonasa + totalFonasa;
	}

	/**
	 * @return el totalGeneralAccidente
	 */
	public long getTotalGeneralAccidente() {
		return totalGeneralAccidente;
	}

	/**
	 * @param totalGeneralAccidente el totalGeneralAccidente a establecer
	 */
	public void setTotalGeneralAccidente(long totalGeneralAccidente) {
		this.totalGeneralAccidente = this.totalGeneralAccidente + totalGeneralAccidente;
	}

	/**
	 * @return el totalGeneralBonificacion15366
	 */
	public long getTotalGeneralBonificacion15366() {
		return totalGeneralBonificacion15366;
	}

	/**
	 * @param totalGeneralBonificacion15366 el totalGeneralBonificacion15366 a establecer
	 */
	public void setTotalGeneralBonificacion15366(long totalGeneralBonificacion15366) {
		this.totalGeneralBonificacion15366 = this.totalGeneralBonificacion15366 + totalGeneralBonificacion15366;
	}

	/**
	 * @return el totalGeneralCotizacionDesahucio
	 */
	public long getTotalGeneralCotizacionDesahucio() {
		return totalGeneralCotizacionDesahucio;
	}

	/**
	 * @param totalGeneralCotizacionDesahucio el totalGeneralCotizacionDesahucio a establecer
	 */
	public void setTotalGeneralCotizacionDesahucio(
			long totalGeneralCotizacionDesahucio) {
		this.totalGeneralCotizacionDesahucio = this.totalGeneralCotizacionDesahucio + totalGeneralCotizacionDesahucio;
	}

	/**
	 * @return el totalGeneralFonasa
	 */
	public long getTotalGeneralFonasa() {
		return totalGeneralFonasa;
	}

	/**
	 * @param totalGeneralFonasa el totalGeneralFonasa a establecer
	 */
	public void setTotalGeneralFonasa(long totalGeneralFonasa) {
		this.totalGeneralFonasa = this.totalGeneralFonasa + totalGeneralFonasa;
	}

	/**
	 * @return el totalGeneralMontoInvalida
	 */
	public long getTotalGeneralMontoInvalida() {
		return totalGeneralMontoInvalida;
	}

	/**
	 * @param totalGeneralMontoInvalida el totalGeneralMontoInvalida a establecer
	 */
	public void setTotalGeneralMontoInvalida(long totalGeneralMontoInvalida) {
		this.totalGeneralMontoInvalida = this.totalGeneralMontoInvalida + totalGeneralMontoInvalida;
	}

	/**
	 * @return el totalGeneralMontoMaternal
	 */
	public long getTotalGeneralMontoMaternal() {
		return totalGeneralMontoMaternal;
	}

	/**
	 * @param totalGeneralMontoMaternal el totalGeneralMontoMaternal a establecer
	 */
	public void setTotalGeneralMontoMaternal(long totalGeneralMontoMaternal) {
		this.totalGeneralMontoMaternal = this.totalGeneralMontoMaternal + totalGeneralMontoMaternal;
	}

	/**
	 * @return el totalGeneralMontoSimple
	 */
	public long getTotalGeneralMontoSimple() {
		return totalGeneralMontoSimple;
	}

	/**
	 * @param totalGeneralMontoSimple el totalGeneralMontoSimple a establecer
	 */
	public void setTotalGeneralMontoSimple(long totalGeneralMontoSimple) {
		this.totalGeneralMontoSimple = this.totalGeneralMontoSimple + totalGeneralMontoSimple;
	}

	/**
	 * @return el totalGeneralPensiones
	 */
	public long getTotalGeneralPensiones() {
		return totalGeneralPensiones;
	}

	/**
	 * @param totalGeneralPensiones el totalGeneralPensiones a establecer
	 */
	public void setTotalGeneralPensiones(long totalGeneralPensiones) {
		this.totalGeneralPensiones = this.totalGeneralPensiones + totalGeneralPensiones;
	}

	/**
	 * @return el totalGeneralRemuneraciones
	 */
	public long getTotalGeneralRemuneraciones() {
		return totalGeneralRemuneraciones;
	}

	/**
	 * @param totalGeneralRemuneraciones el totalGeneralRemuneraciones a establecer
	 */
	public void setTotalGeneralRemuneraciones(long totalGeneralRemuneraciones) {
		this.totalGeneralRemuneraciones = this.totalGeneralRemuneraciones + totalGeneralRemuneraciones;
	}

	/**
	 * @return el totalGeneralRemuneracionesDesahucio
	 */
	public long getTotalGeneralRemuneracionesDesahucio() {
		return totalGeneralRemuneracionesDesahucio;
	}

	/**
	 * @param totalGeneralRemuneracionesDesahucio el totalGeneralRemuneracionesDesahucio a establecer
	 */
	public void setTotalGeneralRemuneracionesDesahucio(
			long totalGeneralRemuneracionesDesahucio) {
		this.totalGeneralRemuneracionesDesahucio = this.totalGeneralRemuneracionesDesahucio + totalGeneralRemuneracionesDesahucio;
	}

	/**
	 * @return el totalMontoInvalida
	 */
	public long getTotalMontoInvalida() {
		return totalMontoInvalida;
	}

	/**
	 * @param totalMontoInvalida el totalMontoInvalida a establecer
	 */
	private void setTotalMontoInvalida(long totalMontoInvalida) {
		this.totalMontoInvalida = this.totalMontoInvalida + totalMontoInvalida;
	}

	/**
	 * @return el totalMontoMaternal
	 */
	public long getTotalMontoMaternal() {
		return totalMontoMaternal;
	}

	/**
	 * @param totalMontoMaternal el totalMontoMaternal a establecer
	 */
	private void setTotalMontoMaternal(long totalMontoMaternal) {
		this.totalMontoMaternal = this.totalMontoMaternal + totalMontoMaternal;
	}

	/**
	 * @return el totalMontoSimple
	 */
	public long getTotalMontoSimple() {
		return totalMontoSimple;
	}

	/**
	 * @param totalMontoSimple el totalMontoSimple a establecer
	 */
	private void setTotalMontoSimple(long totalMontoSimple) {
		this.totalMontoSimple = this.totalMontoSimple + totalMontoSimple;
	}

	/**
	 * @return el totalPensiones
	 */
	public long getTotalPensiones() {
		return totalPensiones;
	}

	/**
	 * @param totalPensiones el totalPensiones a establecer
	 */
	private void setTotalPensiones(long totalPensiones) {
		this.totalPensiones = this.totalPensiones + totalPensiones;
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
	private void setTotalRemuneraciones(long totalRemuneraciones) {
		this.totalRemuneraciones = this.totalRemuneraciones + totalRemuneraciones;
	}

	/**
	 * @return el totalRemuneracionesDesahucio
	 */
	public long getTotalRemuneracionesDesahucio() {
		return totalRemuneracionesDesahucio;
	}

	/**
	 * @param totalRemuneracionesDesahucio el totalRemuneracionesDesahucio a establecer
	 */
	private void setTotalRemuneracionesDesahucio(long totalRemuneracionesDesahucio) {
		this.totalRemuneracionesDesahucio = this.totalRemuneracionesDesahucio + totalRemuneracionesDesahucio;
	}
    
    
	}
