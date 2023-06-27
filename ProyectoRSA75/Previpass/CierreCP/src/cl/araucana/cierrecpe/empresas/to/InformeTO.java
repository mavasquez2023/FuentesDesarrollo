

/*
 * @(#) InformeTO.java    1.0 02-03-2015
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.to;

import java.util.Map;



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
 *            <TD> 02-03-2015 </TD>
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
public class InformeTO {
	private String server;
	private int cierre;
	private int grupoConvenio;
	private String rutEmpresa;
	private int convenio;
	private String tipoNomina;
	private String razonSocial;
	private long total;
	private int numtra;
	private String telefono;
	private String nombreRL;
	private String domicilio;
	private String comuna;
	private String ciudad;
	private String region;
	private String caja;
	private int folio;
	private String fechaPago;
	private String horaPago;
	private Map pagos;
	private BancoTO banco;
	private char formaPago;	
	
	/**
	 * @return el caja
	 */
	public String getCaja() {
		return caja;
	}
	/**
	 * @param caja el caja a establecer
	 */
	public void setCaja(String caja) {
		this.caja = caja;
	}
	/**
	 * @return el ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}
	/**
	 * @param ciudad el ciudad a establecer
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	/**
	 * @return el comuna
	 */
	public String getComuna() {
		return comuna;
	}
	/**
	 * @param comuna el comuna a establecer
	 */
	public void setComuna(String comuna) {
		this.comuna = comuna;
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
	 * @return el domicilio
	 */
	public String getDomicilio() {
		return domicilio;
	}
	/**
	 * @param domicilio el domicilio a establecer
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
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
	 * @return el nombreRL
	 */
	public String getNombreRL() {
		return nombreRL;
	}
	/**
	 * @param nombreRL el nombreRL a establecer
	 */
	public void setNombreRL(String nombreRL) {
		this.nombreRL = nombreRL;
	}
	/**
	 * @return el numtra
	 */
	public int getNumtra() {
		return numtra;
	}
	/**
	 * @param numtra el numtra a establecer
	 */
	public void setNumtra(int numtra) {
		this.numtra = numtra;
	}
	/**
	 * @return el razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}
	/**
	 * @param razonSocial el razonSocial a establecer
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	/**
	 * @return el rutEmpresa
	 */
	public String getRutEmpresa() {
		return rutEmpresa;
	}
	/**
	 * @param rutEmpresa el rutEmpresa a establecer
	 */
	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
	/**
	 * @return el server
	 */
	public String getServer() {
		return server;
	}
	/**
	 * @param server el server a establecer
	 */
	public void setServer(String server) {
		this.server = server;
	}
	/**
	 * @return el telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono el telefono a establecer
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * @return el tipoNomina
	 */
	public String getTipoNomina() {
		return tipoNomina;
	}
	/**
	 * @param tipoNomina el tipoNomina a establecer
	 */
	public void setTipoNomina(String tipoNomina) {
		this.tipoNomina = tipoNomina;
	}
	/**
	 * @return el total
	 */
	public long getTotal() {
		return total;
	}
	/**
	 * @param total el total a establecer
	 */
	public void setTotal(long total) {
		this.total = total;
	}
	/**
	 * @return el tipoEmpresa
	 */
	/**
	 * @return el region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region el region a establecer
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return el folio
	 */
	public int getFolio() {
		return folio;
	}
	/**
	 * @param folio el folio a establecer
	 */
	public void setFolio(int folio) {
		this.folio = folio;
	}
	/**
	 * @return el pagos
	 */
	public Map getPagos() {
		return pagos;
	}
	/**
	 * @param pagos el pagos a establecer
	 */
	public void setPagos(Map pagos) {
		this.pagos = pagos;
	}
	/**
	 * @return el banco
	 */
	public BancoTO getBanco() {
		return banco;
	}
	/**
	 * @param banco el banco a establecer
	 */
	public void setBanco(BancoTO banco) {
		this.banco = banco;
	}
	/**
	 * @return el fechaPago
	 */
	public String getFechaPago() {
		return fechaPago;
	}
	/**
	 * @param fechaPago el fechaPago a establecer
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	/**
	 * @return el horaPago
	 */
	public String getHoraPago() {
		return horaPago;
	}
	/**
	 * @param horaPago el horaPago a establecer
	 */
	public void setHoraPago(String horaPago) {
		this.horaPago = horaPago;
	}
	
	public long getAfp(){
		Long monto= (Long)pagos.get("AFP");
		long resultado= (monto==null)?0:monto.longValue();
		return resultado;
	}
	public long getIsapre(){
		Long monto= (Long)pagos.get("ISAPRE");
		long resultado= (monto==null)?0:monto.longValue();
		return resultado;
	}
	public long getIps(){
		Long monto= (Long)pagos.get("IPS");
		long resultado= (monto==null)?0:monto.longValue();
		return resultado;
	}
	public long getMutual(){
		Long monto= (Long)pagos.get("MUTUAL");
		long resultado= (monto==null)?0:monto.longValue();
		return resultado;
	}
	public long getAsfam(){
		Long monto= (Long)pagos.get("ASFAM");
		long resultado= (monto==null)?0:monto.longValue();
		return resultado;
	}
	public long getAporte(){
		Long monto= (Long)pagos.get("APORTE");
		long resultado= (monto==null)?0:monto.longValue();
		return resultado;
	}
	public long getCredito(){
		Long monto= (Long)pagos.get("CREDITO");
		long resultado= (monto==null)?0:monto.longValue();
		return resultado;
	}
	public long getLeasing(){
		Long monto= (Long)pagos.get("LEASING");
		long resultado= (monto==null)?0:monto.longValue();
		return resultado;
	}
	public long getVida(){
		Long monto= (Long)pagos.get("VIDA");
		long resultado= (monto==null)?0:monto.longValue();
		return resultado;
	}
	public long getDental(){
		Long monto= (Long)pagos.get("DENTAL");
		long resultado= (monto==null)?0:monto.longValue();
		return resultado;
	}
	public long getSfe(){
		Long monto= (Long)pagos.get("SFE");
		long resultado= (monto==null)?0:monto.longValue();
		return resultado;
	}
	public long getCcaf(){
		Long monto= (Long)pagos.get("CCAF");
		long resultado= (monto==null)?0:monto.longValue();
		return resultado;
	}
	
	public String getNombreBanco(){
		return (banco!=null)?banco.getNombre():"";
	}
	public int getCodigoBanco(){
		return (banco!=null)?banco.getIdBanco():0;
	}
	public String getCuentaBanco(){
		return (banco!=null)?banco.getIdCuenta():"";
	}
	public char getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(char medioPago) {
		this.formaPago = medioPago;
	}
	/**
	 * @return el fechaContable
	 */
	public String getFechaContable() {
		return (banco!=null)?banco.getFechaContable():"";
	}
	/**
	 * @return el tipoRegistro
	 */
	public int getTipoRegistro() {
		 return (banco!=null)?banco.getTipoRegistro():0;
	}
	/**
	 * @return el idPago
	 */
	public int getIdPago() {
		 return (banco!=null)?banco.getIdPago():0;
	}
	/**
	 * @return el montoConsolidado
	 */
	public long getMontoConsolidado() {
		 return (banco!=null)?banco.getMontoConsolidado():0;
	}
	/**
	 * @set el montoConsolidado
	 */
	public void setMontoConsolidado(long monto) {
		 if (banco!=null){
			 banco.setMontoConsolidado(monto);
		 }
	}
	/**
	 * @return el montoConsolidado
	 */
	public long getMedioPago() {
		 return (banco!=null)?banco.getMedioPago():0;
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
}

