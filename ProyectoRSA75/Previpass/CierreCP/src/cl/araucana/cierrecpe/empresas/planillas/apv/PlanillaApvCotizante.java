

/*
 * @(#) PlanillaApvCotizante.java    1.0 04-ago-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.planillas.apv;

import cl.araucana.cierrecpe.empresas.planillas.PlanillaBaseCotizante;
import cl.araucana.core.util.AbsoluteDate;
import cl.araucana.core.util.Rut;
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
public class PlanillaApvCotizante extends PlanillaBaseCotizante{
	
	
	
	/*
	 * PROPIEDADES DE PAGINA DE DETALLE COTIZANTE
	 */
    
    private int remuneracionImponibleFdoPensionCotizante=0;
    private int cotizacionVoluntariaFdoPensionCotizante=0;
    private int depositoConvenidoCotizante=0;
    private int remuneracionImponibleCtaIndemCotizante=0;
    private int regimenPrevisionalCtaIndemCotizante=0;
    private double tasaPactadaCtaIdemCotizante=0.0;
    private int aporteCtaIndemCotizante=0;
    private int numeroPeriodosCtaIndemCotizante=0;
    private AbsoluteDate fechaDesdeCtaIndemCotizante;
    private AbsoluteDate fechaHastaCtaIndemCotizante;
    
	/*
	 * PROPIEDADES APV COLECTIVO
	 */
    private int aporteEmpleadorFdoPensionColectivoCotizante=0;
    private int aporteTrabajadorFdoPensionColectivoCotizante=0;
    private String codigoContratoCotizante;
    private int codigoMovimientoCotizante=0;
    private Rut rutEntidadSubsidiadoraCotizante;
	
	/**
	 * @return el aporteCtaIndemCotizante
	 */
	public int getAporteCtaIndemCotizante() {
		return aporteCtaIndemCotizante;
	}
	/**
	 * @param aporteCtaIndemCotizante el aporteCtaIndemCotizante a establecer
	 */
	public void setAporteCtaIndemCotizante(int aporteCtaIndemCotizante) {
		this.aporteCtaIndemCotizante = aporteCtaIndemCotizante;
	}
	/**
	 * @return el aporteEmpleadorFdoPensionColectivoCotizante
	 */
	public int getAporteEmpleadorFdoPensionColectivoCotizante() {
		return aporteEmpleadorFdoPensionColectivoCotizante;
	}
	/**
	 * @param aporteEmpleadorFdoPensionColectivoCotizante el aporteEmpleadorFdoPensionColectivoCotizante a establecer
	 */
	public void setAporteEmpleadorFdoPensionColectivoCotizante(
			int aporteEmpleadorFdoPensionColectivoCotizante) {
		this.aporteEmpleadorFdoPensionColectivoCotizante = aporteEmpleadorFdoPensionColectivoCotizante;
	}
	/**
	 * @return el aporteTrabajadorFdoPensionColectivoCotizante
	 */
	public int getAporteTrabajadorFdoPensionColectivoCotizante() {
		return aporteTrabajadorFdoPensionColectivoCotizante;
	}
	/**
	 * @param aporteTrabajadorFdoPensionColectivoCotizante el aporteTrabajadorFdoPensionColectivoCotizante a establecer
	 */
	public void setAporteTrabajadorFdoPensionColectivoCotizante(
			int aporteTrabajadorFdoPensionColectivoCotizante) {
		this.aporteTrabajadorFdoPensionColectivoCotizante = aporteTrabajadorFdoPensionColectivoCotizante;
	}
	/**
	 * @return el codigoContratoCotizante
	 */
	public String getCodigoContratoCotizante() {
		return codigoContratoCotizante;
	}
	/**
	 * @param codigoContratoCotizante el codigoContratoCotizante a establecer
	 */
	public void setCodigoContratoCotizante(String codigoContratoCotizante) {
		this.codigoContratoCotizante = codigoContratoCotizante;
	}
	/**
	 * @return el codigoMovimientoCotizante
	 */
	public int getCodigoMovimientoCotizante() {
		return codigoMovimientoCotizante;
	}
	/**
	 * @param codigoMovimientoCotizante el codigoMovimientoCotizante a establecer
	 */
	public void setCodigoMovimientoCotizante(int codigoMovimientoCotizante) {
		this.codigoMovimientoCotizante = codigoMovimientoCotizante;
	}
	/**
	 * @return el cotizacionVoluntariaFdoPensionCotizante
	 */
	public int getCotizacionVoluntariaFdoPensionCotizante() {
		return cotizacionVoluntariaFdoPensionCotizante;
	}
	/**
	 * @param cotizacionVoluntariaFdoPensionCotizante el cotizacionVoluntariaFdoPensionCotizante a establecer
	 */
	public void setCotizacionVoluntariaFdoPensionCotizante(
			int cotizacionVoluntariaFdoPensionCotizante) {
		this.cotizacionVoluntariaFdoPensionCotizante = cotizacionVoluntariaFdoPensionCotizante;
	}
	/**
	 * @return el depositoConvenidoCotizante
	 */
	public int getDepositoConvenidoCotizante() {
		return depositoConvenidoCotizante;
	}
	/**
	 * @param depositoConvenidoCotizante el depositoConvenidoCotizante a establecer
	 */
	public void setDepositoConvenidoCotizante(int depositoConvenidoCotizante) {
		this.depositoConvenidoCotizante = depositoConvenidoCotizante;
	}
	/**
	 * @return el fechaDesdeCtaIndemCotizante
	 */
	public AbsoluteDate getFechaDesdeCtaIndemCotizante() {
		return fechaDesdeCtaIndemCotizante;
	}
	/**
	 * @param fechaDesdeCtaIndemCotizante el fechaDesdeCtaIndemCotizante a establecer
	 */
	public void setFechaDesdeCtaIndemCotizante(
			AbsoluteDate fechaDesdeCtaIndemCotizante) {
		this.fechaDesdeCtaIndemCotizante = fechaDesdeCtaIndemCotizante;
	}
	/**
	 * @return el fechaHastaCtaIndemCotizante
	 */
	public AbsoluteDate getFechaHastaCtaIndemCotizante() {
		return fechaHastaCtaIndemCotizante;
	}
	/**
	 * @param fechaHastaCtaIndemCotizante el fechaHastaCtaIndemCotizante a establecer
	 */
	public void setFechaHastaCtaIndemCotizante(
			AbsoluteDate fechaHastaCtaIndemCotizante) {
		this.fechaHastaCtaIndemCotizante = fechaHastaCtaIndemCotizante;
	}
	/**
	 * @return el numeroPeriodosCtaIndemCotizante
	 */
	public int getNumeroPeriodosCtaIndemCotizante() {
		return numeroPeriodosCtaIndemCotizante;
	}
	/**
	 * @param numeroPeriodosCtaIndemCotizante el numeroPeriodosCtaIndemCotizante a establecer
	 */
	public void setNumeroPeriodosCtaIndemCotizante(
			int numeroPeriodosCtaIndemCotizante) {
		this.numeroPeriodosCtaIndemCotizante = numeroPeriodosCtaIndemCotizante;
	}
	/**
	 * @return el regimenPrevisionalCtaIndemCotizante
	 */
	public int getRegimenPrevisionalCtaIndemCotizante() {
		return regimenPrevisionalCtaIndemCotizante;
	}
	/**
	 * @param regimenPrevisionalCtaIndemCotizante el regimenPrevisionalCtaIndemCotizante a establecer
	 */
	public void setRegimenPrevisionalCtaIndemCotizante(
			int regimenPrevisionalCtaIndemCotizante) {
		this.regimenPrevisionalCtaIndemCotizante = regimenPrevisionalCtaIndemCotizante;
	}
	/**
	 * @return el remuneracionImponibleCtaIndemCotizante
	 */
	public int getRemuneracionImponibleCtaIndemCotizante() {
		return remuneracionImponibleCtaIndemCotizante;
	}
	/**
	 * @param remuneracionImponibleCtaIndemCotizante el remuneracionImponibleCtaIndemCotizante a establecer
	 */
	public void setRemuneracionImponibleCtaIndemCotizante(
			int remuneracionImponibleCtaIndemCotizante) {
		this.remuneracionImponibleCtaIndemCotizante = remuneracionImponibleCtaIndemCotizante;
	}
	/**
	 * @return el remuneracionImponibleFdoPensionCotizante
	 */
	public int getRemuneracionImponibleFdoPensionCotizante() {
		return remuneracionImponibleFdoPensionCotizante;
	}
	/**
	 * @param remuneracionImponibleFdoPensionCotizante el remuneracionImponibleFdoPensionCotizante a establecer
	 */
	public void setRemuneracionImponibleFdoPensionCotizante(
			int remuneracionImponibleFdoPensionCotizante) {
		this.remuneracionImponibleFdoPensionCotizante = remuneracionImponibleFdoPensionCotizante;
	}
	/**
	 * @return el rutEntidadSubsidiadoraCotizante
	 */
	public Rut getRutEntidadSubsidiadoraCotizante() {
		return rutEntidadSubsidiadoraCotizante;
	}
	/**
	 * @param rutEntidadSubsidiadoraCotizante el rutEntidadSubsidiadoraCotizante a establecer
	 */
	public void setRutEntidadSubsidiadoraCotizante(
			Rut rutEntidadSubsidiadoraCotizante) {
		this.rutEntidadSubsidiadoraCotizante = rutEntidadSubsidiadoraCotizante;
	}
	/**
	 * @return el tasaPactadaCtaIdemCotizante
	 */
	public double getTasaPactadaCtaIdemCotizante() {
		return tasaPactadaCtaIdemCotizante;
	}
	/**
	 * @param tasaPactadaCtaIdemCotizante el tasaPactadaCtaIdemCotizante a establecer
	 */
	public void setTasaPactadaCtaIdemCotizante(double tasaPactadaCtaIdemCotizante) {
		this.tasaPactadaCtaIdemCotizante = tasaPactadaCtaIdemCotizante;
	}
    
    
    }
