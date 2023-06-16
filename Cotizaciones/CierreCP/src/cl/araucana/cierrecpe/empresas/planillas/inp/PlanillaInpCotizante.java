
/*
 * @(#) PlanillaInpCotizante.java    1.0 18-ago-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */

package cl.araucana.cierrecpe.empresas.planillas.inp;

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
public class PlanillaInpCotizante extends PlanillaBaseCotizante{
	/*
	 * PROPIEDADES DE PAGINA DE DETALLE
	 */
	private int diasTrabajadosCotizante;
	private int remImponibleCotizante=0;
	
	//Cotizaciones y Aportes
	private int pensionInpCotizante=0;
	private int fonasaCotizante=0;
	private int accDelTrabajoCotizante=0;
	
	//Desahucio
	private int cotizacionDesahucioCotizante=0;
	
	//Rebajas
	private int codigoTramoCotizante=0;
	private int montoBonificacionLey15385Cotizante=0;
	private int nroCargasInvalidaCotizante=0;
	private int nroCargasMaternalCotizante=0;
	private int nroCargasSimpleCotizante=0;
	private int montoAsigFamiliarCotizante=0;
	private int montoAsigFamiliarRetroactivoCotizante=0;
	private int montoAsigFamiliarReintegrosCotizante=0;
	private int valorTramoCotizante=0;
	
	//Variables globales para distribuir la diferencia entre lo informado y lo calculado, en caso de venir Retroactivo o Reintegros.
	private int diferenciaAsfam=0;
	private int totalCargas=0;
	private int promedioDiferencia=0;
	
//	Gratificaciones
	 private AbsoluteDate fechaInicioGratificacionesCotizante;
	 private AbsoluteDate fechaTerminoGratificacionesCotizante;
	 
	public void setDiferenciaAsfam(){
		int informadoAsfam= getMontoAsigFamiliarCotizante()+getMontoAsigFamiliarRetroactivoCotizante()-getMontoAsigFamiliarReintegrosCotizante();
		int calculadoAsfam= getMontoAsigFamiliarSimpleCotizante() + getMontoAsigFamiliarInvalidaCotizante() + getMontoAsigFamiliarMaternalCotizante();
		diferenciaAsfam= informadoAsfam - calculadoAsfam;
		totalCargas= nroCargasSimpleCotizante + nroCargasInvalidaCotizante + nroCargasMaternalCotizante;
		if (diferenciaAsfam>0 && totalCargas>0){
			promedioDiferencia= Math.round(diferenciaAsfam/totalCargas);
		}
	}
	
	public int getDiferenciaAsfam(){
		return diferenciaAsfam;
	}
	
	private int getDiferenciaAsfam(int nroCargas){
		int ponderado=0;
		if(nroCargas>0){
			totalCargas-= nroCargas;
			if(totalCargas>0){
				ponderado= promedioDiferencia*nroCargas;
				diferenciaAsfam-= ponderado;
			}else{
				ponderado=diferenciaAsfam;
			}
		}
		return ponderado;
	}
	
	/**
	 * @return el montoAsigFamiliarInvalidaCotizante
	 */
	public int getMontoAsigFamiliarInvalidaCotizante() {
		return nroCargasInvalidaCotizante*2 * valorTramoCotizante + getDiferenciaAsfam(nroCargasInvalidaCotizante);
	}
	
	/**
	 * @return el montoAsigFamiliarMaternalCotizante
	 */
	public int getMontoAsigFamiliarMaternalCotizante() {
		return nroCargasMaternalCotizante * valorTramoCotizante + getDiferenciaAsfam(nroCargasMaternalCotizante);
	}
	
	/**
	 * @return el montoAsigFamiliarSimpleCotizante
	 */
	public int getMontoAsigFamiliarSimpleCotizante() {
		return nroCargasSimpleCotizante * valorTramoCotizante + getDiferenciaAsfam(nroCargasSimpleCotizante);
	}
	
	/**
	 * @return el accDelTrabajoCotizante
	 */
	public int getAccDelTrabajoCotizante() {
		return accDelTrabajoCotizante;
	}
	/**
	 * @param accDelTrabajoCotizante el accDelTrabajoCotizante a establecer
	 */
	public void setAccDelTrabajoCotizante(int accDelTrabajoCotizante) {
		this.accDelTrabajoCotizante = accDelTrabajoCotizante;
	}
	
	/**
	 * @return el codigoTramoCotizante
	 */
	public int getCodigoTramoCotizante() {
		return codigoTramoCotizante;
	}
	/**
	 * @param codigoTramoCotizante el codigoTramoCotizante a establecer
	 */
	public void setCodigoTramoCotizante(int codigoTramoCotizante) {
		this.codigoTramoCotizante = codigoTramoCotizante;
	}
	/**
	 * @return el cotizacionDesahucioCotizante
	 */
	public int getCotizacionDesahucioCotizante() {
		return cotizacionDesahucioCotizante;
	}
	/**
	 * @param cotizacionDesahucioCotizante el cotizacionDesahucioCotizante a establecer
	 */
	public void setCotizacionDesahucioCotizante(int cotizacionDesahucioCotizante) {
		this.cotizacionDesahucioCotizante = cotizacionDesahucioCotizante;
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
	 * @return el fonasaCotizante
	 */
	public int getFonasaCotizante() {
		return fonasaCotizante;
	}
	/**
	 * @param fonasaCotizante el fonasaCotizante a establecer
	 */
	public void setFonasaCotizante(int fonasaCotizante) {
		this.fonasaCotizante = fonasaCotizante;
	}
	
	/**
	 * @return el montoBonificacionLey15385Cotizante
	 */
	public int getMontoBonificacionLey15385Cotizante() {
		return montoBonificacionLey15385Cotizante;
	}
	/**
	 * @param montoBonificacionLey15385Cotizante el montoBonificacionLey15385Cotizante a establecer
	 */
	public void setMontoBonificacionLey15385Cotizante(
			int montoBonificacionLey15385Cotizante) {
		this.montoBonificacionLey15385Cotizante = montoBonificacionLey15385Cotizante;
	}
	/**
	 * @return el nroCargasInvalidaCotizante
	 */
	public int getNroCargasInvalidaCotizante() {
		return nroCargasInvalidaCotizante;
	}
	/**
	 * @param nroCargasInvalidaCotizante el nroCargasInvalidaCotizante a establecer
	 */
	public void setNroCargasInvalidaCotizante(int nroCargasInvalidaCotizante) {
		this.nroCargasInvalidaCotizante = nroCargasInvalidaCotizante;
	}
	/**
	 * @return el nroCargasMaternalCotizante
	 */
	public int getNroCargasMaternalCotizante() {
		return nroCargasMaternalCotizante;
	}
	/**
	 * @param nroCargasMaternalCotizante el nroCargasMaternalCotizante a establecer
	 */
	public void setNroCargasMaternalCotizante(int nroCargasMaternalCotizante) {
		this.nroCargasMaternalCotizante = nroCargasMaternalCotizante;
	}
	/**
	 * @return el nroCargasSimpleCotizante
	 */
	public int getNroCargasSimpleCotizante() {
		return nroCargasSimpleCotizante;
	}
	/**
	 * @param nroCargasSimpleCotizante el nroCargasSimpleCotizante a establecer
	 */
	public void setNroCargasSimpleCotizante(int nroCargasSimpleCotizante) {
		this.nroCargasSimpleCotizante = nroCargasSimpleCotizante;
	}
	/**
	 * @return el pensionInpCotizante
	 */
	public int getPensionInpCotizante() {
		return pensionInpCotizante;
	}
	/**
	 * @param pensionInpCotizante el pensionInpCotizante a establecer
	 */
	public void setPensionInpCotizante(int pensionInpCotizante) {
		this.pensionInpCotizante = pensionInpCotizante;
	}
	/**
	 * @return el remImponibleCotizante
	 */
	public int getRemImponibleCotizante() {
		return remImponibleCotizante;
	}
	/**
	 * @param remImponibleCotizante el remImponibleCotizante a establecer
	 */
	public void setRemImponibleCotizante(int remImponibleCotizante) {
		this.remImponibleCotizante = remImponibleCotizante;
	}
	/**
	 * @return el remImponibleDesahucioCotizante
	 */
	public int getRemImponibleDesahucioCotizante() {
		if (getCotizacionDesahucioCotizante()>0){
			return getRemImponibleCotizante();
		}else{
			return 0;
		}
	}
	/**
	 * @return el valorTramoCotizante
	 */
	public int getValorTramoCotizante() {
		return valorTramoCotizante;
	}
	/**
	 * @param valorTramoCotizante el valorTramoCotizante a establecer
	 */
	public void setValorTramoCotizante(int valorTramoCotizante) {
		this.valorTramoCotizante = valorTramoCotizante;
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
	 * @return el montoAsigFamiliarReintegrosCotizante
	 */
	public int getMontoAsigFamiliarReintegrosCotizante() {
		return montoAsigFamiliarReintegrosCotizante;
	}
	/**
	 * @param montoAsigFamiliarReintegrosCotizante el montoAsigFamiliarReintegrosCotizante a establecer
	 */
	public void setMontoAsigFamiliarReintegrosCotizante(
			int montoAsigFamiliarReintegrosCotizante) {
		this.montoAsigFamiliarReintegrosCotizante = montoAsigFamiliarReintegrosCotizante;
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
