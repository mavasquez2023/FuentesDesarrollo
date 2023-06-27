/*
 * Creada el 14-04-2008
 *
 */
package cl.araucana.cierrecpe.empresas.planillas.isapre;


import java.text.DecimalFormat;

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
public class PlanillaIsapreCotizante extends PlanillaBaseCotizante{

	/*
	 * PROPIEDADES DE PAGINA DE DETALLE COTIZANTE
	 */
	    
    //Cotización
	private int remuneracionImponible=0;
	private int cotizacion7Porciento=0;
	private double porcentajeLey18566=0.0;
	private int cotizacionLey18566=0;
	private int cotizacionAdicionalVoluntaria=0;
	private int cotizacionTotalAPagar=0;
	private int cotizacionPactada=0;
	
	//Mov. personal
	private int codigoMovimientoPersonal=0;
	private AbsoluteDate fechaInicioMovimientoPersonal;
	private AbsoluteDate fechaTerminoMovimientoPersonal;

	private DecimalFormat porcentajeLey18566Format = new DecimalFormat("0.00");

	
	/**
	 * @return el codigoMovimientoPersonal
	 */
	public int getCodigoMovimientoPersonal() {
		return codigoMovimientoPersonal;
	}

	/**
	 * @param codigoMovimientoPersonal el codigoMovimientoPersonal a establecer
	 */
	public void setCodigoMovimientoPersonal(int codigoMovimientoPersonal) {
		this.codigoMovimientoPersonal = codigoMovimientoPersonal;
	}

	/**
	 * @return el cotizacion7Porciento
	 */
	public int getCotizacion7Porciento() {
		return cotizacion7Porciento;
	}

	/**
	 * @param cotizacion7Porciento el cotizacion7Porciento a establecer
	 */
	public void setCotizacion7Porciento(int cotizacion7Porciento) {
		this.cotizacion7Porciento = cotizacion7Porciento;
	}

	/**
	 * @return el cotizacionAdicionalVoluntaria
	 */
	public int getCotizacionAdicionalVoluntaria() {
		return cotizacionAdicionalVoluntaria;
	}

	/**
	 * @param cotizacionAdicionalVoluntaria el cotizacionAdicionalVoluntaria a establecer
	 */
	public void setCotizacionAdicionalVoluntaria(int cotizacionAdicionalVoluntaria) {
		this.cotizacionAdicionalVoluntaria = cotizacionAdicionalVoluntaria;
	}

	/**
	 * @return el cotizacionLey18566
	 */
	public int getCotizacionLey18566() {
		return cotizacionLey18566;
	}

	/**
	 * @param cotizacionLey18566 el cotizacionLey18566 a establecer
	 */
	public void setCotizacionLey18566(int cotizacionLey18566) {
		this.cotizacionLey18566 = cotizacionLey18566;
	}

	/**
	 * @return el cotizacionPactada
	 */
	public int getCotizacionPactada() {
		return cotizacionPactada;
	}

	/**
	 * @param cotizacionPactada el cotizacionPactada a establecer
	 */
	public void setCotizacionPactada(int cotizacionPactada) {
		this.cotizacionPactada = cotizacionPactada;
	}

	/**
	 * @return el cotizacionTotalAPagar
	 */
	public int getCotizacionTotalAPagar() {
		return cotizacionTotalAPagar;
	}

	/**
	 * @param cotizacionTotalAPagar el cotizacionTotalAPagar a establecer
	 */
	public void setCotizacionTotalAPagar(int cotizacionTotalAPagar) {
		this.cotizacionTotalAPagar = cotizacionTotalAPagar;
	}

	/**
	 * @return el fechaInicioMovimientoPersonal
	 */
	public AbsoluteDate getFechaInicioMovimientoPersonal() {
		return fechaInicioMovimientoPersonal;
	}

	/**
	 * @param fechaInicioMovimientoPersonal el fechaInicioMovimientoPersonal a establecer
	 */
	public void setFechaInicioMovimientoPersonal(
			AbsoluteDate fechaInicioMovimientoPersonal) {
		this.fechaInicioMovimientoPersonal = fechaInicioMovimientoPersonal;
	}

	/**
	 * @return el fechaTerminoMovimientoPersonal
	 */
	public AbsoluteDate getFechaTerminoMovimientoPersonal() {
		return fechaTerminoMovimientoPersonal;
	}

	/**
	 * @param fechaTerminoMovimientoPersonal el fechaTerminoMovimientoPersonal a establecer
	 */
	public void setFechaTerminoMovimientoPersonal(
			AbsoluteDate fechaTerminoMovimientoPersonal) {
		this.fechaTerminoMovimientoPersonal = fechaTerminoMovimientoPersonal;
	}

	/**
	 * @return el porcentajeLey18566
	 */
	public double getPorcentajeLey18566() {
		return porcentajeLey18566;
	}

	/**
	 * @param porcentajeLey18566 el porcentajeLey18566 a establecer
	 */
	public void setPorcentajeLey18566(double porcentajeLey18566) {
		this.porcentajeLey18566 = porcentajeLey18566;
	}

	/**
	 * @return el remuneracionImponible
	 */
	public int getRemuneracionImponible() {
		return remuneracionImponible;
	}

	/**
	 * @param remuneracionImponible el remuneracionImponible a establecer
	 */
	public void setRemuneracionImponible(int remuneracionImponible) {
		this.remuneracionImponible = remuneracionImponible;
	}

}
