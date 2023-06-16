

/*
 * @(#) PlanillaApvCotizante.java    1.0 04-ago-2008
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.cierrecpe.empresas.planillas.tp;

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
 *            <TD align="center"> <B>Versi�n</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripci�n</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 05-05-2008 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> Claudio Lillo <BR> clillo@laaraucana.cl </TD>
 *            <TD> Versi�n inicial. </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Germ�n Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class PlanillaTpCotizante extends PlanillaBaseCotizante{
	
	
	/**
	 * PROPIEDADES DE PAGINA DE DETALLE
	 */
	
    private int remuneracionImponibleFdoPensionCotizante=0;
    //Informaci�n T. Pesados
    private double tasaTrabajoPesadoCotizante=0.0;
    private String tipotrabajopesadoCotizante="";
    private int cotizacionTrabajoPesadoCotizante=0;
    //Informaci�n Mov. de Personal
    private int codigoMovimientoPersonalCotizante=0;
    private AbsoluteDate fechaInicioMovimientoPersonalCotizante;
    private AbsoluteDate fechaTerminoMovimientoPersonalCotizante;
    
//  Gratificaciones
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
		
		if(tipotrabajopesadoCotizante.length()>30){
			return tipotrabajopesadoCotizante.substring(0, 30);
		}else{
			return tipotrabajopesadoCotizante;
		}
		
	}
	/**
	 * @param tipotrabajopesadoCotizante el tipotrabajopesadoCotizante a establecer
	 */
	public void setTipotrabajopesadoCotizante(String tipotrabajopesadoCotizante) {
		this.tipotrabajopesadoCotizante = tipotrabajopesadoCotizante;
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
	