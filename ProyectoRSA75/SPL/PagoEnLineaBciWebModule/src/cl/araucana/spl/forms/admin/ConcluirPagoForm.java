package cl.araucana.spl.forms.admin;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

import cl.araucana.spl.beans.FiltroConcluirPago;
import cl.araucana.spl.beans.ResumenInconsistencias;
import cl.araucana.spl.util.Renderer;

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
 *            <TD> 13-01-2014 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Gonzalo Mallea Lorca <BR> gmallea@schema.cl </TD>
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
 * @author Gonzalo Mallea Lorca (gmallea@schema.cl)
 *
 * @version 1.0
 */
public class ConcluirPagoForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private static Renderer renderer = new Renderer();
	
	private List estados;
	private List bancos;
	private List sistemas;
	private String buscar;
	
	private List concluirPago;
	private ResumenInconsistencias resumen;

	private FiltroConcluirPago filtro;
	
	public ConcluirPagoForm() {
		filtro = new FiltroConcluirPago();
		filtro.setTipoFecha(FiltroConcluirPago.TIPO_FECHA_PAGO);
		// filtro.setEstado(Constants.ESTADO_PAGO_INCONSISTENTE);
		Date now = new Date(System.currentTimeMillis());
		filtro.setFechaPagoDesde(now);
		filtro.setFechaPagoHasta(now);
		filtro.setFechaContableDesde(now);
		filtro.setFechaContableHasta(now);
		filtro.setTrxConsiderar("todas");
	}

	public List getBancos() {
		return bancos;
	}

	public void setBancos(List bancos) {
		this.bancos = bancos;
	}

	public String getBuscar() {
		return buscar;
	}

	public void setBuscar(String buscar) {
		this.buscar = buscar;
	}

	public List getEstados() {
		return estados;
	}

	public void setEstados(List estados) {
		this.estados = estados;
	}

	public List getSistemas() {
		return sistemas;
	}

	public void setSistemas(List sistemas) {
		this.sistemas = sistemas;
	}

	public FiltroConcluirPago getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroConcluirPago filtro) {
		this.filtro = filtro;
	}

	public String getBanco() {
		return renderer.formatSimple(filtro.getBanco());
	}

	public String getEstado() {
		return renderer.formatSimple(filtro.getEstado());
	}

	public String getFechaContableDesde() {
		return filtro.getStrFechaContableDesde();
	}

	public String getFechaContableHasta() {
		return filtro.getStrFechaContableHasta();
	}

	public String getFechaPagoDesde() {
		return filtro.getStrFechaPagoDesde();
	}

	public String getFechaPagoHasta() {
		return filtro.getStrFechaPagoHasta();
	}

	public String getNumeroFolio() {
		return renderer.formatSimple(filtro.getNumeroFolio());
	}

	public String getNumeroPago() {
		return renderer.formatSimple(filtro.getNumeroPago());
	}

	public String getSistema() {
		return renderer.formatSimple(filtro.getSistema());
	}

	public String getTipoFecha() {
		return filtro.getTipoFecha();
	}

	public void setBanco(String banco) {
		filtro.setBanco(renderer.parseBigDecimal(banco));
	}

	public void setEstado(String estado)  {
		filtro.setEstado(renderer.parseBigDecimal(estado));
	}

	
	
	
	
	public void setFechaContableDesde(String fechaContableDesde) throws ParseException {
		filtro.setFechaContableDesde(renderer.parseDate(fechaContableDesde));
	}

	public void setFechaContableHasta(String fechaContableHasta) throws ParseException {
		filtro.setFechaContableHasta(renderer.parseDate(fechaContableHasta));
	}

	public void setFechaPagoDesde(String fechaPagoDesde) throws ParseException {
		filtro.setFechaPagoDesde(renderer.parseDate(fechaPagoDesde));
	}

	public void setFechaPagoHasta(String fechaPagoHasta) throws ParseException {
		filtro.setFechaPagoHasta(renderer.parseDate(fechaPagoHasta));
	}

	public void setNumeroFolio(String numeroFolio) {
		filtro.setNumeroFolio(renderer.parseBigDecimal(numeroFolio));
	}

	public void setNumeroPago(String numeroPago) {
		filtro.setNumeroPago(renderer.parseBigDecimal(numeroPago));
	}

	
	public void setSistema(String sistema) {
		filtro.setSistema(renderer.parseBigDecimal(sistema));
	}

	public void setTipoFecha(String tipoFecha) {
		filtro.setTipoFecha(tipoFecha);
	}

	public ResumenInconsistencias getResumen() {
		return resumen;
	}

	public void setResumen(ResumenInconsistencias resumen) {
		this.resumen = resumen;
	}

	public String getTrxConsiderar() {
		return filtro.getTrxConsiderar();
	}

	public void setTrxConsiderar(String trxConsiderar) {
		filtro.setTrxConsiderar(trxConsiderar);
	}

	public static Renderer getRenderer() {
		return renderer;
	}

	public static void setRenderer(Renderer renderer) {
		ConcluirPagoForm.renderer = renderer;
	}

	public List getConcluirPago() {
		return concluirPago;
	}

	public void setConcluirPago(List concluirPago) {
		this.concluirPago = concluirPago;
	}
	
	
}
