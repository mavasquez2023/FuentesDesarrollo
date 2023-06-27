

/*
 * @(#) PlanillaDocumentModel.java    1.0 05-05-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecp.empresas.comprobantes;

import java.sql.Timestamp;
import java.util.Collection;

import cl.araucana.cierrecpe.empresas.planillas.IdentificacionEmpleador;
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
public class Comprobante_Encabezado{
	/*
     * CONCEPTOS GENERALES
     */
	private int periodo;
	private String tipoProceso;
	private int convenio;
	private int grupoConvenio;
	private Timestamp fechaEmision;
	private AbsoluteDate fechaPago;
	private AbsoluteDate fechaPublicacion;
	private long id_codigo_barra;
	private int folioTesoreria;
	private long montoTotal;
	private int numTrabajadores;
	private IdentificacionEmpleador datosEmpleador;
	private Collection entidades;
	private Collection totales;
	private AbsoluteDate fechaInicioGrati;
	private AbsoluteDate fechaTerminoGrati;
	private int idCcaf;
	private int idMutual;
	private String nombreCcaf;
	private String nombreMutual;
	private int numeroAdherenteMutual;
	private String idCasaMatriz;
	private boolean generarPlanillaxSucursalINP=true;
	private boolean generarPlanillaxSucursalMUTUAL=true;
	private boolean generarPlanillaxSucursalCCAF=true;
	private boolean mutualCalculoIndividual= true;
	private String tipoCliente;
	private int cierre;
	private long totalRemuneraciones;
	private char formaPago;
	
	/**
	 * @return el datosEmpleador
	 */
	public IdentificacionEmpleador getDatosEmpleador() {
		return datosEmpleador;
	}
	/**
	 * @param datosEmpleador el datosEmpleador a establecer
	 */
	public void setDatosEmpleador(IdentificacionEmpleador datosEmpleador) {
		this.datosEmpleador = datosEmpleador;
	}
	/**
	 * @return el entidades
	 */
	public Collection getEntidades() {
		return entidades;
	}
	/**
	 * @param entidades el entidades a establecer
	 */
	public void setEntidades(Collection entidades) {
		this.entidades = entidades;
	}
	/**
	 * @return el fechaPago
	 */
	public AbsoluteDate getFechaPago() {
		return fechaPago;
	}
	/**
	 * @param fechaPago el fechaPago a establecer
	 */
	public void setFechaPago(AbsoluteDate fechaPago) {
		this.fechaPago = fechaPago;
	}
	/**
	 * @return el id_codigo_barra
	 */
	public long getId_codigo_barra() {
		return id_codigo_barra;
	}
	/**
	 * @param id_codigo_barra el id_codigo_barra a establecer
	 */
	public void setId_codigo_barra(long id_codigo_barra) {
		this.id_codigo_barra = id_codigo_barra;
	}
	/**
	 * @return el periodo
	 */
	public int getPeriodo() {
		return periodo;
	}
	/**
	 * @param periodo el periodo a establecer
	 */
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	/**
	 * @return el tipoProceso
	 */
	public String getTipoProceso() {
		return tipoProceso;
	}
	/**
	 * @param tipoProceso el tipoProceso a establecer
	 */
	public void setTipoProceso(String tipoProceso) {
		this.tipoProceso = tipoProceso;
	}
	/**
	 * @return el convenio
	 */
	public int getConvenio() {
		return convenio;
	}
	/**
	 * @param convenio el convenio a establecer
	 */
	public void setConvenio(int convenio) {
		this.convenio = convenio;
	}
	/**
	 * @return el grupoConvenio
	 */
	public int getGrupoConvenio() {
		return grupoConvenio;
	}
	/**
	 * @param grupoConvenio el grupoConvenio a establecer
	 */
	public void setGrupoConvenio(int grupoConvenio) {
		this.grupoConvenio = grupoConvenio;
	}
	/**
	 * @return el fechaInicioGrati
	 */
	public AbsoluteDate getFechaInicioGrati() {
		return fechaInicioGrati;
	}
	/**
	 * @param fechaInicioGrati el fechaInicioGrati a establecer
	 */
	public void setFechaInicioGrati(AbsoluteDate fechaInicioGrati) {
		this.fechaInicioGrati = fechaInicioGrati;
	}
	/**
	 * @return el fechaterminoGrati
	 */
	public AbsoluteDate getFechaTerminoGrati() {
		return fechaTerminoGrati;
	}
	/**
	 * @param fechaterminoGrati el fechaterminoGrati a establecer
	 */
	public void setFechaTerminoGrati(AbsoluteDate fechaTerminoGrati) {
		this.fechaTerminoGrati = fechaTerminoGrati;
	}
	/**
	 * @return el idCcaf
	 */
	public int getIdCcaf() {
		return idCcaf;
	}
	/**
	 * @param idCcaf el idCcaf a establecer
	 */
	public void setIdCcaf(int idCcaf) {
		this.idCcaf = idCcaf;
	}
	/**
	 * @return el idMutual
	 */
	public int getIdMutual() {
		return idMutual;
	}
	/**
	 * @param idMutual el idMutual a establecer
	 */
	public void setIdMutual(int idMutual) {
		this.idMutual = idMutual;
	}
	/**
	 * @return el nombreCcaf
	 */
	public String getNombreCcaf() {
		return nombreCcaf;
	}
	/**
	 * @param nombreCcaf el nombreCcaf a establecer
	 */
	public void setNombreCcaf(String nombreCcaf) {
		this.nombreCcaf = nombreCcaf;
	}
	/**
	 * @return el nombreMutual
	 */
	public String getNombreMutual() {
		return nombreMutual;
	}
	/**
	 * @param nombreMutual el nombreMutual a establecer
	 */
	public void setNombreMutual(String nombreMutual) {
		this.nombreMutual = nombreMutual;
	}
	/**
	 * @return el numeroAdherenteMutual
	 */
	public int getNumeroAdherenteMutual() {
		return numeroAdherenteMutual;
	}
	/**
	 * @param numeroAdherenteMutual el numeroAdherenteMutual a establecer
	 */
	public void setNumeroAdherenteMutual(int numeroAdherenteMutual) {
		this.numeroAdherenteMutual = numeroAdherenteMutual;
	}
	/**
	 * @return el idCasaMatriz
	 */
	public String getIdCasaMatriz() {
		return idCasaMatriz;
	}
	/**
	 * @param idCasaMatriz el idCasaMatriz a establecer
	 */
	public void setIdCasaMatriz(String idCasaMatriz) {
		this.idCasaMatriz = idCasaMatriz;
	}
	/**
	 * @return el generarPlanillaxSucursalCCAF
	 */
	public boolean isGenerarPlanillaxSucursalCCAF() {
		return generarPlanillaxSucursalCCAF;
	}
	/**
	 * @param generarPlanillaxSucursalCCAF el generarPlanillaxSucursalCCAF a establecer
	 */
	public void setGenerarPlanillaxSucursalCCAF(boolean generarPlanillaxSucursalCCAF) {
		this.generarPlanillaxSucursalCCAF = generarPlanillaxSucursalCCAF;
	}
	/**
	 * @return el generarPlanillaxSucursalINP
	 */
	public boolean isGenerarPlanillaxSucursalINP() {
		return generarPlanillaxSucursalINP;
	}
	/**
	 * @param generarPlanillaxSucursalINP el generarPlanillaxSucursalINP a establecer
	 */
	public void setGenerarPlanillaxSucursalINP(boolean generarPlanillaxSucursalINP) {
		this.generarPlanillaxSucursalINP = generarPlanillaxSucursalINP;
	}
	/**
	 * @return el generarPlanillaxSucursalMUTUAL
	 */
	public boolean isGenerarPlanillaxSucursalMUTUAL() {
		return generarPlanillaxSucursalMUTUAL;
	}
	/**
	 * @param generarPlanillaxSucursalMUTUAL el generarPlanillaxSucursalMUTUAL a establecer
	 */
	public void setGenerarPlanillaxSucursalMUTUAL(
			boolean generarPlanillaxSucursalMUTUAL) {
		this.generarPlanillaxSucursalMUTUAL = generarPlanillaxSucursalMUTUAL;
	}
	/**
	 * @return el folioTesoreria
	 */
	public int getFolioTesoreria() {
		return folioTesoreria;
	}
	/**
	 * @param folioTesoreria el folioTesoreria a establecer
	 */
	public void setFolioTesoreria(int folioTesoreria) {
		this.folioTesoreria = folioTesoreria;
	}
	/**
	 * @return el fechaEmision
	 */
	public Timestamp getFechaEmision() {
		return fechaEmision;
	}
	/**
	 * @param fechaEmision el fechaEmision a establecer
	 */
	public void setFechaEmision(Timestamp fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	/**
	 * @return el numTrabajadores
	 */
	public int getNumTrabajadores() {
		return numTrabajadores;
	}
	/**
	 * @param numTrabajadores el numTrabajadores a establecer
	 */
	public void setNumTrabajadores(int numTrabajadores) {
		this.numTrabajadores = numTrabajadores;
	}
	/**
	 * @return el montoTotal
	 */
	public long getMontoTotal() {
		return montoTotal;
	}
	/**
	 * @param montoTotal el montoTotal a establecer
	 */
	public void setMontoTotal(long montoTotal) {
		this.montoTotal = montoTotal;
	}
	/**
	 * @return el totales
	 */
	public Collection getTotales() {
		return totales;
	}
	/**
	 * @param totales el totales a establecer
	 */
	public void setTotales(Collection totales) {
		this.totales = totales;
	}
	/**
	 * @return el mutualCalculoIndividual
	 */
	public boolean isMutualCalculoIndividual() {
		return mutualCalculoIndividual;
	}
	/**
	 * @param mutualCalculoIndividual el mutualCalculoIndividual a establecer
	 */
	public void setMutualCalculoIndividual(boolean mutualCalculoIndividual) {
		this.mutualCalculoIndividual = mutualCalculoIndividual;
	}
	/**
	 * @return el fechaPublicacion
	 */
	public AbsoluteDate getFechaPublicacion() {
		return fechaPublicacion;
	}
	/**
	 * @param fechaPublicacion el fechaPublicacion a establecer
	 */
	public void setFechaPublicacion(AbsoluteDate fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	/**
	 * @return el tipoCliente
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}
	/**
	 * @param tipoCliente el tipoCliente a establecer
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	/**
	 * @return el cierre
	 */
	public int getCierre() {
		return cierre;
	}
	/**
	 * @param cierre el cierre a establecer
	 */
	public void setCierre(int cierre) {
		this.cierre = cierre;
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
	 * @return el formaPago
	 */
	public char getFormaPago() {
		return formaPago;
	}
	/**
	 * @param formaPago el formaPago a establecer
	 */
	public void setFormaPago(char formaPago) {
		this.formaPago = formaPago;
	}


}
