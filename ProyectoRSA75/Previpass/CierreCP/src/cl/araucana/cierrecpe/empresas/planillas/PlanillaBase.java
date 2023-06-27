

/*
 * @(#) PlanillaBase.java    1.0 21-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.planillas;

import java.text.DecimalFormat;
import java.util.Collection;

import cl.araucana.cierrecp.empresas.comprobantes.Comprobante_Encabezado;
import cl.araucana.core.util.AbsoluteDate;
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
 *            <TD> 21-07-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> CLAUDIO LILLO AZORÍN <BR> clillo@laaraucana.cl </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD>   </TD>
 *            <TD align="center">  </TD>
 *            <TD>   </TD>
 *            <TD>  </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author CLAUDIO LILLO AZORÍN (clillo@laaraucana.cl)
 *
 * @version 1.0
 */
public class PlanillaBase extends PlanillaDocumentModel{

	protected String $id;
	/*
	 * CONCEPTOS HEREDADOS DEL COMPROBANTE
	 */
	private int periodo;
	private String tipoProceso;
	private int fechaPago;
	private IdentificacionEmpleador datosEmpleador;
	private int convenio;
	private int grupoConvenio;
	private String codigoSucursal;
	private int fechaInicioGrati=0;
	private int fechaTerminoGrati=0;
	/*
     * CONCEPTOS GENERALES
     */
	private String nombreEntidad;
	private String folio;
	private String secuenciaFolio;
	private long totalAPagar;
	private int numeroHojasAnexas;
	private int numeroAfiliadosInformados;
	private Collection paginasDetalle;
	private IdentificacionSucursal datosSucursal;
	
	/**
	 * 
	 */
	public PlanillaBase(Comprobante_Encabezado comprobante) {
		setPeriodo(comprobante.getPeriodo());
		setTipoProceso(comprobante.getTipoProceso());
		String fecha= comprobante.getFechaPublicacion().getPeriod() + "" + Formato.padding(comprobante.getFechaPublicacion().getDay(), 2);
		setFechaPago(Integer.parseInt(fecha));
		setDatosEmpleador(comprobante.getDatosEmpleador());
		setConvenio(comprobante.getConvenio());
		setGrupoConvenio(comprobante.getGrupoConvenio());
		AbsoluteDate fechaInicioGrati= comprobante.getFechaInicioGrati();
		AbsoluteDate fechaTerminoGrati= comprobante.getFechaTerminoGrati();
		if (fechaInicioGrati!=null && fechaTerminoGrati!=null){
			setFechaInicioGrati(fechaInicioGrati.getPeriod()*100 + fechaInicioGrati.getDay());
			setFechaTerminoGrati(fechaTerminoGrati.getPeriod()*100  + fechaInicioGrati.getDay());
		}
	}
	
	public String docID() {
		if ($id == null) {
			$id = getPeriodo() + "/"
				+ getNombreEntidad() + "/"
				+ getFolio();
		}

		return $id;
	}

	public String formatoDecimal(String patron, double number){
		DecimalFormat formato= new DecimalFormat(patron);
		return formato.format(number);
		
	}
	/**
	 * @return el datosSucursal
	 */
	public IdentificacionSucursal getDatosSucursal() {
		return datosSucursal;
	}

	/**
	 * @param datosSucursal el datosSucursal a establecer
	 */
	public void setDatosSucursal(IdentificacionSucursal datosSucursal) {
		this.datosSucursal = datosSucursal;
	}

	/**
	 * @return el folio
	 */
	public String getFolio() {
		return folio;
	}

	/**
	 * @param folio el folio a establecer
	 */
	public void setFolio(String folio) {
		this.folio = folio;
	}

	/**
	 * @return el nombreEntidad
	 */
	public String getNombreEntidad() {
		if(nombreEntidad.length()>40){
			return nombreEntidad.substring(0, 40).trim();
		}
		return nombreEntidad.trim();
	}

	/**
	 * @param nombreEntidad el nombreEntidad a establecer
	 */
	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}

	/**
	 * @return el numeroAfiliadosInformados
	 */
	public int getNumeroAfiliadosInformados() {
		return numeroAfiliadosInformados;
	}

	/**
	 * @param numeroAfiliadosInformados el numeroAfiliadosInformados a establecer
	 */
	public void setNumeroAfiliadosInformados(int numeroAfiliadosInformados) {
		this.numeroAfiliadosInformados = numeroAfiliadosInformados;
	}

	/**
	 * @return el numeroHojasAnexas
	 */
	public int getNumeroHojasAnexas() {
		return numeroHojasAnexas;
	}

	/**
	 * @param numeroHojasAnexas el numeroHojasAnexas a establecer
	 */
	public void setNumeroHojasAnexas(int numeroHojasAnexas) {
		this.numeroHojasAnexas = numeroHojasAnexas;
	}

	/**
	 * @return el paginasDetalle
	 */
	public Collection getPaginasDetalle() {
		return paginasDetalle;
	}

	/**
	 * @param paginasDetalle el paginasDetalle a establecer
	 */
	public void setPaginasDetalle(Collection paginasDetalle) {
		this.paginasDetalle = paginasDetalle;
	}

	/**
	 * @return el secuenciaFolio
	 */
	public String getSecuenciaFolio() {
		return secuenciaFolio;
	}

	/**
	 * @param secuenciaFolio el secuenciaFolio a establecer
	 */
	public void setSecuenciaFolio(String secuenciaFolio) {
		this.secuenciaFolio = secuenciaFolio;
	}

	/**
	 * @return el totalAPagar
	 */
	public long getTotalAPagar() {
		return totalAPagar;
	}

	/**
	 * @param totalAPagar el totalAPagar a establecer
	 */
	public void setTotalAPagar(long totalAPagar) {
		this.totalAPagar = totalAPagar;
	}

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
	 * @return el fechaPago
	 */
	public int getFechaPago() {
		return fechaPago;
	}

	/**
	 * @param fechaPago el fechaPago a establecer
	 */
	public void setFechaPago(int fechaPago) {
		this.fechaPago = fechaPago;
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
	public int getFechaInicioGrati() {
		return fechaInicioGrati;
	}

	/**
	 * @param fechaInicioGrati el fechaInicioGrati a establecer
	 */
	public void setFechaInicioGrati(int fechaInicioGrati) {
		this.fechaInicioGrati = fechaInicioGrati;
	}

	/**
	 * @return el fechaTerminoGrati
	 */
	public int getFechaTerminoGrati() {
		return fechaTerminoGrati;
	}

	/**
	 * @param fechaTerminoGrati el fechaTerminoGrati a establecer
	 */
	public void setFechaTerminoGrati(int fechaTerminoGrati) {
		this.fechaTerminoGrati = fechaTerminoGrati;
	}

	/**
	 * @return el codigoSucursal
	 */
	public String getCodigoSucursal() {
		return codigoSucursal;
	}

	/**
	 * @param codigoSucursal el codigoSucursal a establecer
	 */
	public void setCodigoSucursal(String codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}

}

