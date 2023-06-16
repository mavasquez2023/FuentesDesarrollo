

/*
 * @(#) PlanillaCcafsDocumentModel.java    1.0 13-08-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.planillas.ccaf;



import java.util.Collection;

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
public class PlanillaCcafDocumentModel extends PlanillaBase
		implements Constants {
	
    /*
     * VARIABLES GENERALES
     */
	private Collection paginasDetalleCredito;
	private Collection paginasDetalleLeasing;
	private Collection paginasDetalleSeguroVida;
	private Collection paginasDetalleConvenioDental;
    /*
     * SECCION I - IDENTIFICACION DEL EMPLEADOR 
     */
	    
    private String adheridoMutual;
    
    /*
     * RECUADRO ASIGNACIONES FAMILIARES
     */
    private int totalCargasSimp;
    private int totalCargasInvl;
    private int totalCargasMat;
    private int totalMontoAsigFamiliar;
    
   
    /*
     * RECUADRO CALCULO DEL PAGO
     */
    private int totalHombresAfiliadosIsapre;
    private int totalHombresNoAfiliadosIsapre;
    private int totalMujeresAfiliadosIsapre;
    private int totalMujeresNoAfiliadosIsapre;
    private long montoRemuneracionAfiliadosIsapre;
    private long montoRemuneracionNoAfiliadosIsapre;
    private long totalRemuneraciones;
    private int subtotalAfiliadoIsapre;
    private int subtotalNoAfiliadoIsapre;
    private long montoCreditos;
    private long montoLeasing;
    private int montoConvenioDental;
    private int montoSeguros;
    private int montoOtros;
    private long totalSubTotal;	
    private long saldoAFavorInstitucion;
    
    /*
     * REBAJAS TRAMO
     */
    //Cargas Simples
    private int totalCargasAsigFamiliarSimpTramoA;
    private int totalCargasAsigFamiliarSimpTramoB;
    private int totalCargasAsigFamiliarSimpTramoC;
    private int totalCargasAsigFamiliarSimpTramoD;
    private int totalCargasAsigFamiliarSimpRetroactiva;
    private int totalCargasAsigFamiliarSimpReintegros;
    //Cargas Invalidez
    private int totalCargasAsigFamiliarInvlTramoA;
    private int totalCargasAsigFamiliarInvlTramoB;
    private int totalCargasAsigFamiliarInvlTramoC;
    private int totalCargasAsigFamiliarInvlTramoD;
    private int totalCargasAsigFamiliarInvlRetroactiva;
    private int totalCargasAsigFamiliarInvlReintegros;
    //Cargas Maternales
    private int totalCargasAsigFamiliarMatTramoA;
    private int totalCargasAsigFamiliarMatTramoB;
    private int totalCargasAsigFamiliarMatTramoC;
    private int totalCargasAsigFamiliarMatTramoD;
    private int totalCargasAsigFamiliarMatRetroactiva;
    private int totalCargasAsigFamiliarMatReintegros;
    //Total Cargas
    private int totalTrabajadoresConCargaTramoA;
    private int totalTrabajadoresConCargaTramoB;
    private int totalTrabajadoresConCargaTramoC;
    private int totalTrabajadoresConCargaTramoD;
    private int totalTrabajadoresConCargaRetroactiva;
    private int totalTrabajadoresConCargaReintegros;
    //Total Montos
    private int totalMontoAsigFamiliarTramoA;
    private int totalMontoAsigFamiliarTramoB;
    private int totalMontoAsigFamiliarTramoC;
    private int totalMontoAsigFamiliarTramoD;
    private int totalMontoAsigFamiliarReintegros;
    private int totalMontoAsigFamiliarRetroactiva;
    
    private int totalRebajas;
    private int saldoAFavorEmpleador;
	
    public PlanillaCcafDocumentModel(Comprobante_Encabezado comprobante) {
		super(comprobante);
		// TODO Apéndice de constructor generado automáticamente
	}
    /**
	 * @return el adheridoMutual
	 */
	public String getAdheridoMutual() {
		return adheridoMutual;
	}
	/**
	 * @param adheridoMutual el adheridoMutual a establecer
	 */
	public void setAdheridoMutual(String adheridoMutual) {
		this.adheridoMutual = adheridoMutual;
	}
	/**
	 * @return el montoConvenioDental
	 */
	public int getMontoConvenioDental() {
		return montoConvenioDental;
	}
	/**
	 * @param montoConvenioDental el montoConvenioDental a establecer
	 */
	public void setMontoConvenioDental(int montoConvenioDental) {
		this.montoConvenioDental = montoConvenioDental;
	}
	/**
	 * @return el montoCreditos
	 */
	public long getMontoCreditos() {
		return montoCreditos;
	}
	/**
	 * @param montoCreditos el montoCreditos a establecer
	 */
	public void setMontoCreditos(long montoCreditos) {
		this.montoCreditos = montoCreditos;
	}
	/**
	 * @return el montoLeasing
	 */
	public long getMontoLeasing() {
		return montoLeasing;
	}
	/**
	 * @param montoLeasing el montoLeasing a establecer
	 */
	public void setMontoLeasing(long montoLeasing) {
		this.montoLeasing = montoLeasing;
	}
	/**
	 * @return el montoOtros
	 */
	public int getMontoOtros() {
		return montoOtros;
	}
	/**
	 * @param montoOtros el montoOtros a establecer
	 */
	public void setMontoOtros(int montoOtros) {
		this.montoOtros = montoOtros;
	}
	/**
	 * @return el montoRemuneracionAfiliadosIsapre
	 */
	public long getMontoRemuneracionAfiliadosIsapre() {
		return montoRemuneracionAfiliadosIsapre;
	}
	/**
	 * @param montoRemuneracionAfiliadosIsapre el montoRemuneracionAfiliadosIsapre a establecer
	 */
	public void setMontoRemuneracionAfiliadosIsapre(
			long montoRemuneracionAfiliadosIsapre) {
		this.montoRemuneracionAfiliadosIsapre = montoRemuneracionAfiliadosIsapre;
	}
	/**
	 * @return el montoRemuneracionNoAfiliadosIsapre
	 */
	public long getMontoRemuneracionNoAfiliadosIsapre() {
		return montoRemuneracionNoAfiliadosIsapre;
	}
	/**
	 * @param montoRemuneracionNoAfiliadosIsapre el montoRemuneracionNoAfiliadosIsapre a establecer
	 */
	public void setMontoRemuneracionNoAfiliadosIsapre(
			long montoRemuneracionNoAfiliadosIsapre) {
		this.montoRemuneracionNoAfiliadosIsapre = montoRemuneracionNoAfiliadosIsapre;
	}
	/**
	 * @return el montoSeguros
	 */
	public long getMontoSeguros() {
		return montoSeguros;
	}
	/**
	 * @param montoSeguros el montoSeguros a establecer
	 */
	public void setMontoSeguros(int montoSeguros) {
		this.montoSeguros = montoSeguros;
	}
	/**
	 * @return el saldoAFavorEmpleador
	 */
	public int getSaldoAFavorEmpleador() {
		return saldoAFavorEmpleador;
	}
	/**
	 * @param saldoAFavorEmpleador el saldoAFavorEmpleador a establecer
	 */
	public void setSaldoAFavorEmpleador(int saldoAFavorEmpleador) {
		this.saldoAFavorEmpleador = saldoAFavorEmpleador;
	}
	/**
	 * @return el saldoAFavorInstitucion
	 */
	public long getSaldoAFavorInstitucion() {
		return saldoAFavorInstitucion;
	}
	/**
	 * @param saldoAFavorInstitucion el saldoAFavorInstitucion a establecer
	 */
	public void setSaldoAFavorInstitucion(long saldoAFavorInstitucion) {
		this.saldoAFavorInstitucion = saldoAFavorInstitucion;
	}
	/**
	 * @return el subtotalAfiliadoIsapre
	 */
	public int getSubtotalAfiliadoIsapre() {
		return subtotalAfiliadoIsapre;
	}
	/**
	 * @param subtotalAfiliadoIsapre el subtotalAfiliadoIsapre a establecer
	 */
	public void setSubtotalAfiliadoIsapre(int subtotalAfiliadoIsapre) {
		this.subtotalAfiliadoIsapre = subtotalAfiliadoIsapre;
	}
	/**
	 * @return el subtotalNoAfiliadoIsapre
	 */
	public int getSubtotalNoAfiliadoIsapre() {
		return subtotalNoAfiliadoIsapre;
	}
	/**
	 * @param subtotalNoAfiliadoIsapre el subtotalNoAfiliadoIsapre a establecer
	 */
	public void setSubtotalNoAfiliadoIsapre(int subtotalNoAfiliadoIsapre) {
		this.subtotalNoAfiliadoIsapre = subtotalNoAfiliadoIsapre;
	}
	/**
	 * @return el totalCargasAsigFamiliarInvlTramoA
	 */
	public int getTotalCargasAsigFamiliarInvlTramoA() {
		return totalCargasAsigFamiliarInvlTramoA;
	}
	/**
	 * @param totalCargasAsigFamiliarInvlTramoA el totalCargasAsigFamiliarInvlTramoA a establecer
	 */
	public void setTotalCargasAsigFamiliarInvlTramoA(
			int totalCargasAsigFamiliarInvlTramoA) {
		this.totalCargasAsigFamiliarInvlTramoA = totalCargasAsigFamiliarInvlTramoA;
	}
	/**
	 * @return el totalCargasAsigFamiliarInvlTramoB
	 */
	public int getTotalCargasAsigFamiliarInvlTramoB() {
		return totalCargasAsigFamiliarInvlTramoB;
	}
	/**
	 * @param totalCargasAsigFamiliarInvlTramoB el totalCargasAsigFamiliarInvlTramoB a establecer
	 */
	public void setTotalCargasAsigFamiliarInvlTramoB(
			int totalCargasAsigFamiliarInvlTramoB) {
		this.totalCargasAsigFamiliarInvlTramoB = totalCargasAsigFamiliarInvlTramoB;
	}
	/**
	 * @return el totalCargasAsigFamiliarInvlTramoC
	 */
	public int getTotalCargasAsigFamiliarInvlTramoC() {
		return totalCargasAsigFamiliarInvlTramoC;
	}
	/**
	 * @param totalCargasAsigFamiliarInvlTramoC el totalCargasAsigFamiliarInvlTramoC a establecer
	 */
	public void setTotalCargasAsigFamiliarInvlTramoC(
			int totalCargasAsigFamiliarInvlTramoC) {
		this.totalCargasAsigFamiliarInvlTramoC = totalCargasAsigFamiliarInvlTramoC;
	}
	/**
	 * @return el totalCargasAsigFamiliarInvlTramoD
	 */
	public int getTotalCargasAsigFamiliarInvlTramoD() {
		return totalCargasAsigFamiliarInvlTramoD;
	}
	/**
	 * @param totalCargasAsigFamiliarInvlTramoD el totalCargasAsigFamiliarInvlTramoD a establecer
	 */
	public void setTotalCargasAsigFamiliarInvlTramoD(
			int totalCargasAsigFamiliarInvlTramoD) {
		this.totalCargasAsigFamiliarInvlTramoD = totalCargasAsigFamiliarInvlTramoD;
	}
	/**
	 * @return el totalCargasAsigFamiliarMatTramoA
	 */
	public int getTotalCargasAsigFamiliarMatTramoA() {
		return totalCargasAsigFamiliarMatTramoA;
	}
	/**
	 * @param totalCargasAsigFamiliarMatTramoA el totalCargasAsigFamiliarMatTramoA a establecer
	 */
	public void setTotalCargasAsigFamiliarMatTramoA(
			int totalCargasAsigFamiliarMatTramoA) {
		this.totalCargasAsigFamiliarMatTramoA = totalCargasAsigFamiliarMatTramoA;
	}
	/**
	 * @return el totalCargasAsigFamiliarMatTramoB
	 */
	public int getTotalCargasAsigFamiliarMatTramoB() {
		return totalCargasAsigFamiliarMatTramoB;
	}
	/**
	 * @param totalCargasAsigFamiliarMatTramoB el totalCargasAsigFamiliarMatTramoB a establecer
	 */
	public void setTotalCargasAsigFamiliarMatTramoB(
			int totalCargasAsigFamiliarMatTramoB) {
		this.totalCargasAsigFamiliarMatTramoB = totalCargasAsigFamiliarMatTramoB;
	}
	/**
	 * @return el totalCargasAsigFamiliarMatTramoC
	 */
	public int getTotalCargasAsigFamiliarMatTramoC() {
		return totalCargasAsigFamiliarMatTramoC;
	}
	/**
	 * @param totalCargasAsigFamiliarMatTramoC el totalCargasAsigFamiliarMatTramoC a establecer
	 */
	public void setTotalCargasAsigFamiliarMatTramoC(
			int totalCargasAsigFamiliarMatTramoC) {
		this.totalCargasAsigFamiliarMatTramoC = totalCargasAsigFamiliarMatTramoC;
	}
	/**
	 * @return el totalCargasAsigFamiliarMatTramoD
	 */
	public int getTotalCargasAsigFamiliarMatTramoD() {
		return totalCargasAsigFamiliarMatTramoD;
	}
	/**
	 * @param totalCargasAsigFamiliarMatTramoD el totalCargasAsigFamiliarMatTramoD a establecer
	 */
	public void setTotalCargasAsigFamiliarMatTramoD(
			int totalCargasAsigFamiliarMatTramoD) {
		this.totalCargasAsigFamiliarMatTramoD = totalCargasAsigFamiliarMatTramoD;
	}
	/**
	 * @return el totalCargasAsigFamiliarSimpTramoA
	 */
	public int getTotalCargasAsigFamiliarSimpTramoA() {
		return totalCargasAsigFamiliarSimpTramoA;
	}
	/**
	 * @param totalCargasAsigFamiliarSimpTramoA el totalCargasAsigFamiliarSimpTramoA a establecer
	 */
	public void setTotalCargasAsigFamiliarSimpTramoA(
			int totalCargasAsigFamiliarSimpTramoA) {
		this.totalCargasAsigFamiliarSimpTramoA = totalCargasAsigFamiliarSimpTramoA;
	}
	/**
	 * @return el totalCargasAsigFamiliarSimpTramoB
	 */
	public int getTotalCargasAsigFamiliarSimpTramoB() {
		return totalCargasAsigFamiliarSimpTramoB;
	}
	/**
	 * @param totalCargasAsigFamiliarSimpTramoB el totalCargasAsigFamiliarSimpTramoB a establecer
	 */
	public void setTotalCargasAsigFamiliarSimpTramoB(
			int totalCargasAsigFamiliarSimpTramoB) {
		this.totalCargasAsigFamiliarSimpTramoB = totalCargasAsigFamiliarSimpTramoB;
	}
	/**
	 * @return el totalCargasAsigFamiliarSimpTramoC
	 */
	public int getTotalCargasAsigFamiliarSimpTramoC() {
		return totalCargasAsigFamiliarSimpTramoC;
	}
	/**
	 * @param totalCargasAsigFamiliarSimpTramoC el totalCargasAsigFamiliarSimpTramoC a establecer
	 */
	public void setTotalCargasAsigFamiliarSimpTramoC(
			int totalCargasAsigFamiliarSimpTramoC) {
		this.totalCargasAsigFamiliarSimpTramoC = totalCargasAsigFamiliarSimpTramoC;
	}
	/**
	 * @return el totalCargasAsigFamiliarSimpTramoD
	 */
	public int getTotalCargasAsigFamiliarSimpTramoD() {
		return totalCargasAsigFamiliarSimpTramoD;
	}
	/**
	 * @param totalCargasAsigFamiliarSimpTramoD el totalCargasAsigFamiliarSimpTramoD a establecer
	 */
	public void setTotalCargasAsigFamiliarSimpTramoD(
			int totalCargasAsigFamiliarSimpTramoD) {
		this.totalCargasAsigFamiliarSimpTramoD = totalCargasAsigFamiliarSimpTramoD;
	}
	/**
	 * @return el totalCargasInvl
	 */
	public int getTotalCargasInvl() {
		return totalCargasInvl;
	}
	/**
	 * @param totalCargasInvl el totalCargasInvl a establecer
	 */
	public void setTotalCargasInvl(int totalCargasInvl) {
		this.totalCargasInvl = totalCargasInvl;
	}
	/**
	 * @return el totalCargasMat
	 */
	public int getTotalCargasMat() {
		return totalCargasMat;
	}
	/**
	 * @param totalCargasMat el totalCargasMat a establecer
	 */
	public void setTotalCargasMat(int totalCargasMat) {
		this.totalCargasMat = totalCargasMat;
	}
	/**
	 * @return el totalCargasSimp
	 */
	public int getTotalCargasSimp() {
		return totalCargasSimp;
	}
	/**
	 * @param totalCargasSimp el totalCargasSimp a establecer
	 */
	public void setTotalCargasSimp(int totalCargasSimp) {
		this.totalCargasSimp = totalCargasSimp;
	}
	/**
	 * @return el totalHombresAfiliadosIsapre
	 */
	public int getTotalHombresAfiliadosIsapre() {
		return totalHombresAfiliadosIsapre;
	}
	/**
	 * @param totalHombresAfiliadosIsapre el totalHombresAfiliadosIsapre a establecer
	 */
	public void setTotalHombresAfiliadosIsapre(int totalHombresAfiliadosIsapre) {
		this.totalHombresAfiliadosIsapre = totalHombresAfiliadosIsapre;
	}
	/**
	 * @return el totalHombresNoAfiliadosIsapre
	 */
	public int getTotalHombresNoAfiliadosIsapre() {
		return totalHombresNoAfiliadosIsapre;
	}
	/**
	 * @param totalHombresNoAfiliadosIsapre el totalHombresNoAfiliadosIsapre a establecer
	 */
	public void setTotalHombresNoAfiliadosIsapre(int totalHombresNoAfiliadosIsapre) {
		this.totalHombresNoAfiliadosIsapre = totalHombresNoAfiliadosIsapre;
	}
	/**
	 * @return el totalMontoAsigFamiliar
	 */
	public int getTotalMontoAsigFamiliar() {
		return totalMontoAsigFamiliar;
	}
	/**
	 * @param totalMontoAsigFamiliar el totalMontoAsigFamiliar a establecer
	 */
	public void setTotalMontoAsigFamiliar(int totalMontoAsigFamiliar) {
		this.totalMontoAsigFamiliar = totalMontoAsigFamiliar;
	}
	/**
	 * @return el totalMontoAsigFamiliarReintegros
	 */
	public int getTotalMontoAsigFamiliarReintegros() {
		return totalMontoAsigFamiliarReintegros;
	}
	/**
	 * @param totalMontoAsigFamiliarReintegros el totalMontoAsigFamiliarReintegros a establecer
	 */
	public void setTotalMontoAsigFamiliarReintegros(
			int totalMontoAsigFamiliarReintegros) {
		this.totalMontoAsigFamiliarReintegros = totalMontoAsigFamiliarReintegros;
	}
	/**
	 * @return el totalMontoAsigFamiliarRetroactiva
	 */
	public int getTotalMontoAsigFamiliarRetroactiva() {
		return totalMontoAsigFamiliarRetroactiva;
	}
	/**
	 * @param totalMontoAsigFamiliarRetroactiva el totalMontoAsigFamiliarRetroactiva a establecer
	 */
	public void setTotalMontoAsigFamiliarRetroactiva(
			int totalMontoAsigFamiliarRetroactiva) {
		this.totalMontoAsigFamiliarRetroactiva = totalMontoAsigFamiliarRetroactiva;
	}
	/**
	 * @return el totalMontoAsigFamiliarTramoA
	 */
	public int getTotalMontoAsigFamiliarTramoA() {
		return totalMontoAsigFamiliarTramoA;
	}
	/**
	 * @param totalMontoAsigFamiliarTramoA el totalMontoAsigFamiliarTramoA a establecer
	 */
	public void setTotalMontoAsigFamiliarTramoA(int totalMontoAsigFamiliarTramoA) {
		this.totalMontoAsigFamiliarTramoA = totalMontoAsigFamiliarTramoA;
	}
	/**
	 * @return el totalMontoAsigFamiliarTramoB
	 */
	public int getTotalMontoAsigFamiliarTramoB() {
		return totalMontoAsigFamiliarTramoB;
	}
	/**
	 * @param totalMontoAsigFamiliarTramoB el totalMontoAsigFamiliarTramoB a establecer
	 */
	public void setTotalMontoAsigFamiliarTramoB(int totalMontoAsigFamiliarTramoB) {
		this.totalMontoAsigFamiliarTramoB = totalMontoAsigFamiliarTramoB;
	}
	/**
	 * @return el totalMontoAsigFamiliarTramoC
	 */
	public int getTotalMontoAsigFamiliarTramoC() {
		return totalMontoAsigFamiliarTramoC;
	}
	/**
	 * @param totalMontoAsigFamiliarTramoC el totalMontoAsigFamiliarTramoC a establecer
	 */
	public void setTotalMontoAsigFamiliarTramoC(int totalMontoAsigFamiliarTramoC) {
		this.totalMontoAsigFamiliarTramoC = totalMontoAsigFamiliarTramoC;
	}
	/**
	 * @return el totalMontoAsigFamiliarTramoD
	 */
	public int getTotalMontoAsigFamiliarTramoD() {
		return totalMontoAsigFamiliarTramoD;
	}
	/**
	 * @param totalMontoAsigFamiliarTramoD el totalMontoAsigFamiliarTramoD a establecer
	 */
	public void setTotalMontoAsigFamiliarTramoD(int totalMontoAsigFamiliarTramoD) {
		this.totalMontoAsigFamiliarTramoD = totalMontoAsigFamiliarTramoD;
	}
	/**
	 * @return el totalMujeresAfiliadosIsapre
	 */
	public int getTotalMujeresAfiliadosIsapre() {
		return totalMujeresAfiliadosIsapre;
	}
	/**
	 * @param totalMujeresAfiliadosIsapre el totalMujeresAfiliadosIsapre a establecer
	 */
	public void setTotalMujeresAfiliadosIsapre(int totalMujeresAfiliadosIsapre) {
		this.totalMujeresAfiliadosIsapre = totalMujeresAfiliadosIsapre;
	}
	/**
	 * @return el totalMujeresNoAfiliadosIsapre
	 */
	public int getTotalMujeresNoAfiliadosIsapre() {
		return totalMujeresNoAfiliadosIsapre;
	}
	/**
	 * @param totalMujeresNoAfiliadosIsapre el totalMujeresNoAfiliadosIsapre a establecer
	 */
	public void setTotalMujeresNoAfiliadosIsapre(int totalMujeresNoAfiliadosIsapre) {
		this.totalMujeresNoAfiliadosIsapre = totalMujeresNoAfiliadosIsapre;
	}
	/**
	 * @return el totalRebajas
	 */
	public int getTotalRebajas() {
		return totalRebajas;
	}
	/**
	 * @param totalRebajas el totalRebajas a establecer
	 */
	public void setTotalRebajas(int totalRebajas) {
		this.totalRebajas = totalRebajas;
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
	public void setTotalRemuneraciones(long totalRemuneraciones) {
		this.totalRemuneraciones = totalRemuneraciones;
	}
	/**
	 * @return el totalSubTotal
	 */
	public long getTotalSubTotal() {
		return totalSubTotal;
	}
	/**
	 * @param totalSubTotal el totalSubTotal a establecer
	 */
	public void setTotalSubTotal(long totalSubTotal) {
		this.totalSubTotal = totalSubTotal;
	}
	/**
	 * @return el totalTrabajadoresConCargaTramoA
	 */
	public int getTotalTrabajadoresConCargaTramoA() {
		return totalTrabajadoresConCargaTramoA;
	}
	/**
	 * @param totalTrabajadoresConCargaTramoA el totalTrabajadoresConCargaTramoA a establecer
	 */
	public void setTotalTrabajadoresConCargaTramoA(
			int totalTrabajadoresConCargaTramoA) {
		this.totalTrabajadoresConCargaTramoA = totalTrabajadoresConCargaTramoA;
	}
	/**
	 * @return el totalTrabajadoresConCargaTramoB
	 */
	public int getTotalTrabajadoresConCargaTramoB() {
		return totalTrabajadoresConCargaTramoB;
	}
	/**
	 * @param totalTrabajadoresConCargaTramoB el totalTrabajadoresConCargaTramoB a establecer
	 */
	public void setTotalTrabajadoresConCargaTramoB(
			int totalTrabajadoresConCargaTramoB) {
		this.totalTrabajadoresConCargaTramoB = totalTrabajadoresConCargaTramoB;
	}
	/**
	 * @return el totalTrabajadoresConCargaTramoC
	 */
	public int getTotalTrabajadoresConCargaTramoC() {
		return totalTrabajadoresConCargaTramoC;
	}
	/**
	 * @param totalTrabajadoresConCargaTramoC el totalTrabajadoresConCargaTramoC a establecer
	 */
	public void setTotalTrabajadoresConCargaTramoC(
			int totalTrabajadoresConCargaTramoC) {
		this.totalTrabajadoresConCargaTramoC = totalTrabajadoresConCargaTramoC;
	}
	/**
	 * @return el totalTrabajadoresConCargaTramoD
	 */
	public int getTotalTrabajadoresConCargaTramoD() {
		return totalTrabajadoresConCargaTramoD;
	}
	/**
	 * @param totalTrabajadoresConCargaTramoD el totalTrabajadoresConCargaTramoD a establecer
	 */
	public void setTotalTrabajadoresConCargaTramoD(
			int totalTrabajadoresConCargaTramoD) {
		this.totalTrabajadoresConCargaTramoD = totalTrabajadoresConCargaTramoD;
	}
	/**
	 * @return el totalCargasAsigFamiliarInvlReintegros
	 */
	public int getTotalCargasAsigFamiliarInvlReintegros() {
		return totalCargasAsigFamiliarInvlReintegros;
	}
	/**
	 * @param totalCargasAsigFamiliarInvlReintegros el totalCargasAsigFamiliarInvlReintegros a establecer
	 */
	public void setTotalCargasAsigFamiliarInvlReintegros(
			int totalCargasAsigFamiliarInvlReintegros) {
		this.totalCargasAsigFamiliarInvlReintegros = totalCargasAsigFamiliarInvlReintegros;
	}
	/**
	 * @return el totalCargasAsigFamiliarInvlRetroactiva
	 */
	public int getTotalCargasAsigFamiliarInvlRetroactiva() {
		return totalCargasAsigFamiliarInvlRetroactiva;
	}
	/**
	 * @param totalCargasAsigFamiliarInvlRetroactiva el totalCargasAsigFamiliarInvlRetroactiva a establecer
	 */
	public void setTotalCargasAsigFamiliarInvlRetroactiva(
			int totalCargasAsigFamiliarInvlRetroactiva) {
		this.totalCargasAsigFamiliarInvlRetroactiva = totalCargasAsigFamiliarInvlRetroactiva;
	}
	/**
	 * @return el totalCargasAsigFamiliarMatReintegros
	 */
	public int getTotalCargasAsigFamiliarMatReintegros() {
		return totalCargasAsigFamiliarMatReintegros;
	}
	/**
	 * @param totalCargasAsigFamiliarMatReintegros el totalCargasAsigFamiliarMatReintegros a establecer
	 */
	public void setTotalCargasAsigFamiliarMatReintegros(
			int totalCargasAsigFamiliarMatReintegros) {
		this.totalCargasAsigFamiliarMatReintegros = totalCargasAsigFamiliarMatReintegros;
	}
	/**
	 * @return el totalCargasAsigFamiliarMatRetroactiva
	 */
	public int getTotalCargasAsigFamiliarMatRetroactiva() {
		return totalCargasAsigFamiliarMatRetroactiva;
	}
	/**
	 * @param totalCargasAsigFamiliarMatRetroactiva el totalCargasAsigFamiliarMatRetroactiva a establecer
	 */
	public void setTotalCargasAsigFamiliarMatRetroactiva(
			int totalCargasAsigFamiliarMatRetroactiva) {
		this.totalCargasAsigFamiliarMatRetroactiva = totalCargasAsigFamiliarMatRetroactiva;
	}
	/**
	 * @return el totalCargasAsigFamiliarSimpReintegros
	 */
	public int getTotalCargasAsigFamiliarSimpReintegros() {
		return totalCargasAsigFamiliarSimpReintegros;
	}
	/**
	 * @param totalCargasAsigFamiliarSimpReintegros el totalCargasAsigFamiliarSimpReintegros a establecer
	 */
	public void setTotalCargasAsigFamiliarSimpReintegros(
			int totalCargasAsigFamiliarSimpReintegros) {
		this.totalCargasAsigFamiliarSimpReintegros = totalCargasAsigFamiliarSimpReintegros;
	}
	/**
	 * @return el totalCargasAsigFamiliarSimpRetroactiva
	 */
	public int getTotalCargasAsigFamiliarSimpRetroactiva() {
		return totalCargasAsigFamiliarSimpRetroactiva;
	}
	/**
	 * @param totalCargasAsigFamiliarSimpRetroactiva el totalCargasAsigFamiliarSimpRetroactiva a establecer
	 */
	public void setTotalCargasAsigFamiliarSimpRetroactiva(
			int totalCargasAsigFamiliarSimpRetroactiva) {
		this.totalCargasAsigFamiliarSimpRetroactiva = totalCargasAsigFamiliarSimpRetroactiva;
	}
	/**
	 * @return el totalTrabajadoresConCargareintegros
	 */
	public int getTotalTrabajadoresConCargaReintegros() {
		return totalTrabajadoresConCargaReintegros;
	}
	/**
	 * @param totalTrabajadoresConCargareintegros el totalTrabajadoresConCargareintegros a establecer
	 */
	public void setTotalTrabajadoresConCargaReintegros(
			int totalTrabajadoresConCargaReintegros) {
		this.totalTrabajadoresConCargaReintegros = totalTrabajadoresConCargaReintegros;
	}
	/**
	 * @return el totalTrabajadoresConCargaRetroactiva
	 */
	public int getTotalTrabajadoresConCargaRetroactiva() {
		return totalTrabajadoresConCargaRetroactiva;
	}
	/**
	 * @param totalTrabajadoresConCargaRetroactiva el totalTrabajadoresConCargaRetroactiva a establecer
	 */
	public void setTotalTrabajadoresConCargaRetroactiva(
			int totalTrabajadoresConCargaRetroactiva) {
		this.totalTrabajadoresConCargaRetroactiva = totalTrabajadoresConCargaRetroactiva;
	}
	/**
	 * @return el paginasDetalleConvenioDental
	 */
	public Collection getPaginasDetalleConvenioDental() {
		return paginasDetalleConvenioDental;
	}
	/**
	 * @param paginasDetalleConvenioDental el paginasDetalleConvenioDental a establecer
	 */
	public void setPaginasDetalleConvenioDental(
			Collection paginasDetalleConvenioDental) {
		this.paginasDetalleConvenioDental = paginasDetalleConvenioDental;
	}
	/**
	 * @return el paginasDetalleCredito
	 */
	public Collection getPaginasDetalleCredito() {
		return paginasDetalleCredito;
	}
	/**
	 * @param paginasDetalleCredito el paginasDetalleCredito a establecer
	 */
	public void setPaginasDetalleCredito(Collection paginasDetalleCredito) {
		this.paginasDetalleCredito = paginasDetalleCredito;
	}
	/**
	 * @return el paginasDetalleLeasing
	 */
	public Collection getPaginasDetalleLeasing() {
		return paginasDetalleLeasing;
	}
	/**
	 * @param paginasDetalleLeasing el paginasDetalleLeasing a establecer
	 */
	public void setPaginasDetalleLeasing(Collection paginasDetalleLeasing) {
		this.paginasDetalleLeasing = paginasDetalleLeasing;
	}
	/**
	 * @return el paginasDetalleSeguroVida
	 */
	public Collection getPaginasDetalleSeguroVida() {
		return paginasDetalleSeguroVida;
	}
	/**
	 * @param paginasDetalleSeguroVida el paginasDetalleSeguroVida a establecer
	 */
	public void setPaginasDetalleSeguroVida(Collection paginasDetalleSeguroVida) {
		this.paginasDetalleSeguroVida = paginasDetalleSeguroVida;
	}
    
    /*
     * ANTECEDENTES DEL PAGO
     */
    /*private String banco;
    private String numeroCheque;
    private String cantidadPlanillasPagadas;
    private String montoPagadoConEfectivo;
    private String montoPagadoConCheque;
    */
    
    /*
     * CALCULO GRAVAMENES
     */
    /*private String reajustes;
    private String totalApagarEmpleador;
    private String intereses;
    private String multas;
    */

    
    
	}
