

/*
 * @(#) PlanillaInpDocumentModel.java    1.0 18-08-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.planillas.inp;

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
public class PlanillaInpDocumentModel extends PlanillaBase implements Constants{


	// Para controlar la cantidad de cabeceras de la planilla a producir
	private int filter = 1;
	private String folio_Recaudador;
	/*
     * SECCION I - IDENTIFICACION DEL EMPLEADOR
     */
    private String nombreCCAF;
    private String nombreMutual;
    private int codigoCCAF;
    private int codigoMutual;
    
	/*
     * RESUMEN DE OBLIGACIONES PREVISIONALES
     */
	
	//Periodo de Remuneracion o Gratificacion
	private AbsoluteDate fechaDeclaracion;
    private AbsoluteDate fechaGratificacionVolDesde;
    private AbsoluteDate fechaGratificacionVolHasta;
    
    /*
     * RECUADRO COTIZACIONES
     */
    private long totalMontoPensionesInp;
    private long totalMontoFonasa;
    private long totalMontoAccDelTrabajo;
    private long totalMontoDesahucio;
    private long totalMontoCotizaciones;

    private long totalMontoReajusteEInteres;
    private double tasaReajusteEInteres;
    private long totalMontoMultas;
    private long totalMontoGravamenes;

    private long totalMontoAsigFamiliar;
    private long totalMontoBonificacionLey15386;
    private long totalMontoRebajas;
    private long totalMontoSaldoFavorEmpleador;
    private long totalMontoSaldoFavorInstitucion;

    /*
     * ANTECEDENTES SOBRE EL PAGO
     */
//    private String banco;
//    private String numeroCheque;
//    private String numeroCtaCorriente;
//    private String sucursal;

    
    public PlanillaInpDocumentModel(Comprobante_Encabezado comprobante) {
		super(comprobante);
		// TODO Apéndice de constructor generado automáticamente
	}
    
	/**
	 * @return el codigoCCAF
	 */
	public int getCodigoCCAF() {
		return codigoCCAF;
	}

	/**
	 * @param codigoCCAF el codigoCCAF a establecer
	 */
	public void setCodigoCCAF(int codigoCCAF) {
		this.codigoCCAF = codigoCCAF;
	}

	/**
	 * @return el codigoMutual
	 */
	public int getCodigoMutual() {
		return codigoMutual;
	}

	/**
	 * @param codigoMutual el codigoMutual a establecer
	 */
	public void setCodigoMutual(int codigoMutual) {
		this.codigoMutual = codigoMutual;
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
	 * @return el fechaGratificacionVolDesde
	 */
	public AbsoluteDate getFechaGratificacionVolDesde() {
		return fechaGratificacionVolDesde;
	}

	/**
	 * @param fechaGratificacionVolDesde el fechaGratificacionVolDesde a establecer
	 */
	public void setFechaGratificacionVolDesde(
			AbsoluteDate fechaGratificacionVolDesde) {
		this.fechaGratificacionVolDesde = fechaGratificacionVolDesde;
	}

	/**
	 * @return el fechaGratificacionVolHasta
	 */
	public AbsoluteDate getFechaGratificacionVolHasta() {
		return fechaGratificacionVolHasta;
	}

	/**
	 * @param fechaGratificacionVolHasta el fechaGratificacionVolHasta a establecer
	 */
	public void setFechaGratificacionVolHasta(
			AbsoluteDate fechaGratificacionVolHasta) {
		this.fechaGratificacionVolHasta = fechaGratificacionVolHasta;
	}
	
	/**
	 * @return el nombreCCAF
	 */
	public String getNombreCCAF() {
		if(nombreCCAF.length()>15){
			return nombreCCAF.substring(0, 15);
		}
		return nombreCCAF;
	}

	/**
	 * @param nombreCCAF el nombreCCAF a establecer
	 */
	public void setNombreCCAF(String nombreCCAF) {
		this.nombreCCAF = nombreCCAF;
	}

	/**
	 * @return el nombreMutual
	 */
	public String getNombreMutual() {
		if(nombreMutual.length()>15){
			return nombreMutual.substring(0, 15);
		}
		return nombreMutual;
	}

	/**
	 * @param nombreMutual el nombreMutual a establecer
	 */
	public void setNombreMutual(String nombreMutual) {
		this.nombreMutual = nombreMutual;
	}
	
	/**
	 * @param qtyHeads el qtyHeads a establecer
	 */
	public void setQtyHeads(int filter) {
		this.filter = filter;
	}

	/**
	 * @return el tasaReajusteEInteres
	 */
	public double getTasaReajusteEInteres() {
		return tasaReajusteEInteres;
	}

	/**
	 * @param tasaReajusteEInteres el tasaReajusteEInteres a establecer
	 */
	public void setTasaReajusteEInteres(double tasaReajusteEInteres) {
		this.tasaReajusteEInteres = tasaReajusteEInteres;
	}

	/**
	 * @return el totalMontoAccDelTrabajo
	 */
	public long getTotalMontoAccDelTrabajo() {
		return totalMontoAccDelTrabajo;
	}

	/**
	 * @param totalMontoAccDelTrabajo el totalMontoAccDelTrabajo a establecer
	 */
	public void setTotalMontoAccDelTrabajo(long totalMontoAccDelTrabajo) {
		this.totalMontoAccDelTrabajo = totalMontoAccDelTrabajo;
	}

	/**
	 * @return el totalMontoAsigFamiliar
	 */
	public long getTotalMontoAsigFamiliar() {
		return totalMontoAsigFamiliar;
	}

	/**
	 * @param totalMontoAsigFamiliar el totalMontoAsigFamiliar a establecer
	 */
	public void setTotalMontoAsigFamiliar(long totalMontoAsigFamiliar) {
		this.totalMontoAsigFamiliar = totalMontoAsigFamiliar;
	}

	/**
	 * @return el totalMontoBonificacionLey15386
	 */
	public long getTotalMontoBonificacionLey15386() {
		return totalMontoBonificacionLey15386;
	}

	/**
	 * @param totalMontoBonificacionLey15386 el totalMontoBonificacionLey15386 a establecer
	 */
	public void setTotalMontoBonificacionLey15386(
			long totalMontoBonificacionLey15386) {
		this.totalMontoBonificacionLey15386 = totalMontoBonificacionLey15386;
	}

	/**
	 * @return el totalMontoCotizaciones
	 */
	public long getTotalMontoCotizaciones() {
		return totalMontoCotizaciones;
	}

	/**
	 * @param totalMontoCotizaciones el totalMontoCotizaciones a establecer
	 */
	public void setTotalMontoCotizaciones(long totalMontoCotizaciones) {
		this.totalMontoCotizaciones = totalMontoCotizaciones;
	}

	/**
	 * @return el totalMontoDesahucio
	 */
	public long getTotalMontoDesahucio() {
		return totalMontoDesahucio;
	}

	/**
	 * @param totalMontoDesahucio el totalMontoDesahucio a establecer
	 */
	public void setTotalMontoDesahucio(long totalMontoDesahucio) {
		this.totalMontoDesahucio = totalMontoDesahucio;
	}

	/**
	 * @return el totalMontoFonasa
	 */
	public long getTotalMontoFonasa() {
		return totalMontoFonasa;
	}

	/**
	 * @param totalMontoFonasa el totalMontoFonasa a establecer
	 */
	public void setTotalMontoFonasa(long totalMontoFonasa) {
		this.totalMontoFonasa = totalMontoFonasa;
	}

	/**
	 * @return el totalMontoGravamenes
	 */
	public long getTotalMontoGravamenes() {
		return totalMontoGravamenes;
	}

	/**
	 * @param totalMontoGravamenes el totalMontoGravamenes a establecer
	 */
	public void setTotalMontoGravamenes(long totalMontoGravamenes) {
		this.totalMontoGravamenes = totalMontoGravamenes;
	}

	/**
	 * @return el totalMontoMultas
	 */
	public long getTotalMontoMultas() {
		return totalMontoMultas;
	}

	/**
	 * @param totalMontoMultas el totalMontoMultas a establecer
	 */
	public void setTotalMontoMultas(long totalMontoMultas) {
		this.totalMontoMultas = totalMontoMultas;
	}

	/**
	 * @return el totalMontoPensionesInp
	 */
	public long getTotalMontoPensionesInp() {
		return totalMontoPensionesInp;
	}

	/**
	 * @param totalMontoPensionesInp el totalMontoPensionesInp a establecer
	 */
	public void setTotalMontoPensionesInp(long totalMontoPensionesInp) {
		this.totalMontoPensionesInp = totalMontoPensionesInp;
	}

	/**
	 * @return el totalMontoReajusteEInteres
	 */
	public long getTotalMontoReajusteEInteres() {
		return totalMontoReajusteEInteres;
	}

	/**
	 * @param totalMontoReajusteEInteres el totalMontoReajusteEInteres a establecer
	 */
	public void setTotalMontoReajusteEInteres(long totalMontoReajusteEInteres) {
		this.totalMontoReajusteEInteres = totalMontoReajusteEInteres;
	}

	/**
	 * @return el totalMontoRebajas
	 */
	public long getTotalMontoRebajas() {
		return totalMontoRebajas;
	}

	/**
	 * @param totalMontoRebajas el totalMontoRebajas a establecer
	 */
	public void setTotalMontoRebajas(long totalMontoRebajas) {
		this.totalMontoRebajas = totalMontoRebajas;
	}

	/**
	 * @return el totalMontoSaldoFavorEmpleador
	 */
	public long getTotalMontoSaldoFavorEmpleador() {
		return totalMontoSaldoFavorEmpleador;
	}

	/**
	 * @param totalMontoSaldoFavorEmpleador el totalMontoSaldoFavorEmpleador a establecer
	 */
	public void setTotalMontoSaldoFavorEmpleador(long totalMontoSaldoFavorEmpleador) {
		this.totalMontoSaldoFavorEmpleador = totalMontoSaldoFavorEmpleador;
	}

	/**
	 * @return el totalMontoSaldoFavorInstitucion
	 */
	public long getTotalMontoSaldoFavorInstitucion() {
		return totalMontoSaldoFavorInstitucion;
	}

	/**
	 * @param totalMontoSaldoFavorInstitucion el totalMontoSaldoFavorInstitucion a establecer
	 */
	public void setTotalMontoSaldoFavorInstitucion(
			long totalMontoSaldoFavorInstitucion) {
		this.totalMontoSaldoFavorInstitucion = totalMontoSaldoFavorInstitucion;
	}

	/**
	 * @return el folio_Recaudador
	 */
	public String getFolio_Recaudador() {
		return folio_Recaudador;
	}

	/**
	 * @param folio_Recaudador el folio_Recaudador a establecer
	 */
	public void setFolio_Recaudador(String folio_Recaudador) {
		this.folio_Recaudador = folio_Recaudador;
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
