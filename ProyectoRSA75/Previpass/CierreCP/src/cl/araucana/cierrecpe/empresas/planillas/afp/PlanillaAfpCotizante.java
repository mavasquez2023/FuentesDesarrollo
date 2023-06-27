/*
 * Creada el 14-04-2008
 *
 */
package cl.araucana.cierrecpe.empresas.planillas.afp;

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
public class PlanillaAfpCotizante extends PlanillaBaseCotizante {

	

	/*
	 * PROPIEDADES DE PAGINA DE DETALLE
	 */
	
	//Montos Previsión
	private int remuneracionImponibleFdoPensionCotizante=0;
    private int cotizacionObligatoriaFdoPensionCotizante=0;
    private int depositoCtaAhorroFdoPensionCotizante=0;
    
    //Montos Cesantía
    private int remuneracionImponibleCesantiaCotizante=0;
    private int cotizacionAfiliadoCesantiaCotizante=0;
    private int cotizacionEmpleadorCesantiaCotizante=0;
    //Propiedades T.Pesados
    private double tasaTrabajoPesadoCotizante=0.0;
    private String tipotrabajopesadoCotizante="";
    private int cotizacionTrabajoPesadoCotizante=0;
	//Montos SIS
    private int seguroInvalidezFdoPensionCotizante=0;
    //Movimiento de Personal
    private int codigoMovimientoPersonalCotizante=0;
    private AbsoluteDate fechaInicioMovimientoPersonalCotizante;
    private AbsoluteDate fechaTerminoMovimientoPersonalCotizante;
    private Rut rutEntidadPagadorSubsidioCotizante;
    
    //Propiedades Afiliado Voluntario
    private int codigoAfpAfiliadoVoluntarioCotizante=0;
    private Rut rutDependienteAfiliadoVoluntario=null;
	private String apellidosDependienteAfiliadoVoluntario="";
	private String nombresDependienteAfiliadoVoluntario="";
	
	//Propiedades Fonasa Independientes
	private int cotizacionFonasaIndependiente=0;
	
	//Gratificaciones
	 private AbsoluteDate fechaInicioGratificacionesCotizante;
	 private AbsoluteDate fechaTerminoGratificacionesCotizante;
	    
	/**
	 * @return el codigoMovimientoPersonalCotizante
	 */
	public int getCodigoMovimientoPersonalCotizante() {
		return codigoMovimientoPersonalCotizante;
	}
	/**
	 * @param codigoMovimientoPersonalCotizante el codigoMovimientoPersonalCotizante a establecer
	 */
	public void setCodigoMovimientoPersonalCotizante(
			int codigoMovimientoPersonalCotizante) {
		this.codigoMovimientoPersonalCotizante = codigoMovimientoPersonalCotizante;
	}
	/**
	 * @return el cotizacionAfiliadoCesantiaCotizante
	 */
	public int getCotizacionAfiliadoCesantiaCotizante() {
		return cotizacionAfiliadoCesantiaCotizante;
	}
	/**
	 * @param cotizacionAfiliadoCesantiaCotizante el cotizacionAfiliadoCesantiaCotizante a establecer
	 */
	public void setCotizacionAfiliadoCesantiaCotizante(
			int cotizacionAfiliadoCesantiaCotizante) {
		this.cotizacionAfiliadoCesantiaCotizante = cotizacionAfiliadoCesantiaCotizante;
	}
	/**
	 * @return el cotizacionEmpleadorCesantiaCotizante
	 */
	public int getCotizacionEmpleadorCesantiaCotizante() {
		return cotizacionEmpleadorCesantiaCotizante;
	}
	/**
	 * @param cotizacionEmpleadorCesantiaCotizante el cotizacionEmpleadorCesantiaCotizante a establecer
	 */
	public void setCotizacionEmpleadorCesantiaCotizante(
			int cotizacionEmpleadorCesantiaCotizante) {
		this.cotizacionEmpleadorCesantiaCotizante = cotizacionEmpleadorCesantiaCotizante;
	}
	/**
	 * @return el cotizacionObligatoriaFdoPensionCotizante
	 */
	public int getCotizacionObligatoriaFdoPensionCotizante() {
		return cotizacionObligatoriaFdoPensionCotizante;
	}
	/**
	 * @param cotizacionObligatoriaFdoPensionCotizante el cotizacionObligatoriaFdoPensionCotizante a establecer
	 */
	public void setCotizacionObligatoriaFdoPensionCotizante(
			int cotizacionObligatoriaFdoPensionCotizante) {
		this.cotizacionObligatoriaFdoPensionCotizante = cotizacionObligatoriaFdoPensionCotizante;
	}
	/**
	 * @return el depositoCtaAhorroFdoPensionCotizante
	 */
	public int getDepositoCtaAhorroFdoPensionCotizante() {
		return depositoCtaAhorroFdoPensionCotizante;
	}
	/**
	 * @param depositoCtaAhorroFdoPensionCotizante el depositoCtaAhorroFdoPensionCotizante a establecer
	 */
	public void setDepositoCtaAhorroFdoPensionCotizante(
			int depositoCtaAhorroFdoPensionCotizante) {
		this.depositoCtaAhorroFdoPensionCotizante = depositoCtaAhorroFdoPensionCotizante;
	}
	/**
	 * @return el fechaInicioMovimientoPersonalCotizante
	 */
	public AbsoluteDate getFechaInicioMovimientoPersonalCotizante() {
		return fechaInicioMovimientoPersonalCotizante;
	}
	/**
	 * @param fechaInicioMovimientoPersonalCotizante el fechaInicioMovimientoPersonalCotizante a establecer
	 */
	public void setFechaInicioMovimientoPersonalCotizante(
			AbsoluteDate fechaInicioMovimientoPersonalCotizante) {
		this.fechaInicioMovimientoPersonalCotizante = fechaInicioMovimientoPersonalCotizante;
	}
	/**
	 * @return el fechaTerminoMovimientoPersonalCotizante
	 */
	public AbsoluteDate getFechaTerminoMovimientoPersonalCotizante() {
		return fechaTerminoMovimientoPersonalCotizante;
	}
	/**
	 * @param fechaTerminoMovimientoPersonalCotizante el fechaTerminoMovimientoPersonalCotizante a establecer
	 */
	public void setFechaTerminoMovimientoPersonalCotizante(
			AbsoluteDate fechaTerminoMovimientoPersonalCotizante) {
		this.fechaTerminoMovimientoPersonalCotizante = fechaTerminoMovimientoPersonalCotizante;
	}
	
	/**
	 * @return el remuneracionImponibleCesantiaCotizante
	 */
	public int getRemuneracionImponibleCesantiaCotizante() {
		return remuneracionImponibleCesantiaCotizante;
	}
	/**
	 * @param remuneracionImponibleCesantiaCotizante el remuneracionImponibleCesantiaCotizante a establecer
	 */
	public void setRemuneracionImponibleCesantiaCotizante(
			int remuneracionImponibleCesantiaCotizante) {
		this.remuneracionImponibleCesantiaCotizante = remuneracionImponibleCesantiaCotizante;
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
	 * @return el rutEntidadPagadorSubsidioCotizante
	 */
	public Rut getRutEntidadPagadorSubsidioCotizante() {
		return rutEntidadPagadorSubsidioCotizante;
	}
	/**
	 * @param rutEntidadPagadorSubsidioCotizante el rutEntidadPagadorSubsidioCotizante a establecer
	 */
	public void setRutEntidadPagadorSubsidioCotizante(
			Rut rutEntidadPagadorSubsidioCotizante) {
		this.rutEntidadPagadorSubsidioCotizante = rutEntidadPagadorSubsidioCotizante;
	}
	
	/**
	 * @return el cotizacionTrabajoPesadoCotizante
	 */
	public int getCotizacionTrabajoPesadoCotizante() {
		return cotizacionTrabajoPesadoCotizante;
	}
	/**
	 * @param cotizacionTrabajoPesadoCotizante el cotizacionTrabajoPesadoCotizante a establecer
	 */
	public void setCotizacionTrabajoPesadoCotizante(
			int cotizacionTrabajoPesadoCotizante) {
		this.cotizacionTrabajoPesadoCotizante = cotizacionTrabajoPesadoCotizante;
	}
	/**
	 * @return el tasaTrabajoPesadoCotizante
	 */
	public double getTasaTrabajoPesadoCotizante() {
		return tasaTrabajoPesadoCotizante;
	}
	/**
	 * @param tasaTrabajoPesadoCotizante el tasaTrabajoPesadoCotizante a establecer
	 */
	public void setTasaTrabajoPesadoCotizante(double tasaTrabajoPesadoCotizante) {
		this.tasaTrabajoPesadoCotizante = tasaTrabajoPesadoCotizante;
	}
	/**
	 * @return el tipotrabajopesadoCotizante
	 */
	public String getTipotrabajopesadoCotizante() {
		return tipotrabajopesadoCotizante;
	}
	/**
	 * @param tipotrabajopesadoCotizante el tipotrabajopesadoCotizante a establecer
	 */
	public void setTipotrabajopesadoCotizante(String tipotrabajopesadoCotizante) {
		this.tipotrabajopesadoCotizante = tipotrabajopesadoCotizante;
	}

	/**
	 * @return el apellidosDependienteAfiliadoVoluntario
	 */
	public String getApellidosDependienteAfiliadoVoluntario() {
		return apellidosDependienteAfiliadoVoluntario;
	}
	/**
	 * @param apellidosDependienteAfiliadoVoluntario el apellidosDependienteAfiliadoVoluntario a establecer
	 */
	public void setApellidosDependienteAfiliadoVoluntario(
			String apellidosDependienteAfiliadoVoluntario) {
		this.apellidosDependienteAfiliadoVoluntario = apellidosDependienteAfiliadoVoluntario;
	}
	/**
	 * @return el codigoAfpAfiliadoVoluntarioCotizante
	 */
	public int getCodigoAfpAfiliadoVoluntarioCotizante() {
		return codigoAfpAfiliadoVoluntarioCotizante;
	}
	/**
	 * @param codigoAfpAfiliadoVoluntarioCotizante el codigoAfpAfiliadoVoluntarioCotizante a establecer
	 */
	public void setCodigoAfpAfiliadoVoluntarioCotizante(
			int codigoAfpAfiliadoVoluntarioCotizante) {
		this.codigoAfpAfiliadoVoluntarioCotizante = codigoAfpAfiliadoVoluntarioCotizante;
	}
	/**
	 * @return el nombresDependienteAfiliadoVoluntario
	 */
	public String getNombresDependienteAfiliadoVoluntario() {
		return nombresDependienteAfiliadoVoluntario;
	}
	/**
	 * @param nombresDependienteAfiliadoVoluntario el nombresDependienteAfiliadoVoluntario a establecer
	 */
	public void setNombresDependienteAfiliadoVoluntario(
			String nombresDependienteAfiliadoVoluntario) {
		this.nombresDependienteAfiliadoVoluntario = nombresDependienteAfiliadoVoluntario;
	}
	/**
	 * @return el rutDependienteAfiliadoVoluntario
	 */
	public Rut getRutDependienteAfiliadoVoluntario() {
		return rutDependienteAfiliadoVoluntario;
	}
	/**
	 * @param rutDependienteAfiliadoVoluntario el rutDependienteAfiliadoVoluntario a establecer
	 */
	public void setRutDependienteAfiliadoVoluntario(
			Rut rutDependienteAfiliadoVoluntario) {
		this.rutDependienteAfiliadoVoluntario = rutDependienteAfiliadoVoluntario;
	}
	/**
	 * @return el seguroInvalidezFdoPensionCotizante
	 */
	public int getSeguroInvalidezFdoPensionCotizante() {
		return seguroInvalidezFdoPensionCotizante;
	}
	/**
	 * @param seguroInvalidezFdoPensionCotizante el seguroInvalidezFdoPensionCotizante a establecer
	 */
	public void setSeguroInvalidezFdoPensionCotizante(
			int seguroInvalidezFdoPensionCotizante) {
		this.seguroInvalidezFdoPensionCotizante = seguroInvalidezFdoPensionCotizante;
	}
	/**
	 * @return el cotizacionFonasaIndependiente
	 */
	public int getCotizacionFonasaIndependiente() {
		return cotizacionFonasaIndependiente;
	}
	/**
	 * @param cotizacionFonasaIndependiente el cotizacionFonasaIndependiente a establecer
	 */
	public void setCotizacionFonasaIndependiente(int cotizacionFonasaIndependiente) {
		this.cotizacionFonasaIndependiente = cotizacionFonasaIndependiente;
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
