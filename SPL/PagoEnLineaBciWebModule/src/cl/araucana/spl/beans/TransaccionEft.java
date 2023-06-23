package cl.araucana.spl.beans;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.validator.routines.BigDecimalValidator;
import org.w3c.dom.Document;

import cl.araucana.spl.util.Renderer;
import cl.araucana.spl.util.XmlHelper;

public abstract class TransaccionEft implements Transaccion {
	
	private BigDecimal id;
	private Pago pago;
	private BigDecimal total;
	private String idContrato;
	private BigDecimal nroPagos;
	private BigDecimal codRetorno;
	private String descRetorno;
	private BigDecimal idRegistro;
	private String indicadorPago;
	private BigDecimal nroComprobante;
	private String codigoIdTrx;
	
	private List detalles = new ArrayList();
	
	protected abstract String getNombreObjeto();
	
	//Constructor
	public TransaccionEft() {
		super();
		detalles = new ArrayList();
	}
	
	public String toString() {
		return new StringBuffer("[" + getNombreObjeto() + "::id=").append(id)
			.append(",pago=").append(pago)
			.append(",total=").append(total)
			.append(",idContrato=").append(idContrato)
			.append(",nroPagos=").append(nroPagos)
			.append(",codRetorno=").append(codRetorno)
			.append(",descRetorno=").append(descRetorno)
			.append(",idRegistro=").append(idRegistro)
			.append(",indicadorPago=").append(indicadorPago)
			.append(",nroComprobante=").append(nroComprobante)
			.append(",codigoIdTrx=").append(codigoIdTrx)			
			.append("]").toString();
	}	
	
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Pago getPago() {
		return pago;
	}
	public BigDecimal getIdPago() {
		return pago.getId();
	}
	public void setPago(Pago pago) {
		this.pago = pago;
	}
	public String getUrlCgi() {
		return getPago().getUrlCgi();
	}
	public String getCodigoBanco() {
		return getPago().getCodigoBanco();
	}
	
	public void readFrom(Document document) throws ParseException, UnsupportedEncodingException {
		Renderer render = new Renderer();
		Pago pagoAux = new Pago();
		pagoAux.setSistema(new Sistema());
		setPago(pagoAux);
		
		setCodRetorno(getBigDecimal(XmlHelper.getTextByTagName("CODRET", document)));
		setDescRetorno(render.decodeURL(XmlHelper.getTextByTagName("DESCRET", document)));
		setIdContrato(XmlHelper.getTextByTagName("IDCOM", document));
		setCodigoIdTrx(XmlHelper.getTextByTagName("IDTRX", document));
		setTotal(getBigDecimal(XmlHelper.getTextByTagName("TOTAL", document)));
		setNroPagos(getBigDecimal(XmlHelper.getTextByTagName("NROPAGOS", document)));
		setFechaTransaccion(render.parseMsgDatetime(XmlHelper.getTextByTagName("FECHATRX", document)));
		setFechaContable(render.parseMsgDate(XmlHelper.getTextByTagName("FECHACONT", document)));
		
		setNroComprobante(getBigDecimal(XmlHelper.getTextByTagName("NUMCOMP", document)));
		setIdRegistro(getBigDecimal(XmlHelper.getTextByTagName("IDREG", document)));
		setIndicadorPago(XmlHelper.getTextByTagName("INDPAGO", document));
	}
	
	/**
	 * @return the codigoIdTrx
	 */
	public String getCodigoIdTrx() {
		return codigoIdTrx;
	}
	/**
	 * @param codigoIdTrx the codigoIdTrx to set
	 */
	public void setCodigoIdTrx(String codigoIdTrx) {
		this.codigoIdTrx = codigoIdTrx;
	}
	/**
	 * @return the codRetorno
	 */
	public BigDecimal getCodRetorno() {
		return codRetorno;
	}
	/**
	 * @param codRetorno the codRetorno to set
	 */
	public void setCodRetorno(BigDecimal codRetorno) {
		this.codRetorno = codRetorno;
	}
	/**
	 * @return the descRetorno
	 */
	public String getDescRetorno() {
		return descRetorno;
	}
	/**
	 * @param descRetorno the descRetorno to set
	 */
	public void setDescRetorno(String descRetorno) {
		this.descRetorno = descRetorno;
	}
	/**
	 * @return the idContrato
	 */
	public String getIdContrato() {
		return idContrato;
	}
	/**
	 * @param idContrato the idContrato to set
	 */
	public void setIdContrato(String idContrato) {
		this.idContrato = idContrato;
	}
	/**
	 * @return the idRegistro
	 */
	public BigDecimal getIdRegistro() {
		return idRegistro;
	}
	/**
	 * @param idRegistro the idRegistro to set
	 */
	public void setIdRegistro(BigDecimal idRegistro) {
		this.idRegistro = idRegistro;
	}
	/**
	 * @return the indicadorPago
	 */
	public String getIndicadorPago() {
		return indicadorPago;
	}
	/**
	 * @param indicadorPago the indicadorPago to set
	 */
	public void setIndicadorPago(String indicadorPago) {
		this.indicadorPago = indicadorPago;
	}
	/**
	 * @return the nroComprobante
	 */
	public BigDecimal getNroComprobante() {
		return nroComprobante;
	}
	/**
	 * @param nroComprobante the nroComprobante to set
	 */
	public void setNroComprobante(BigDecimal nroComprobante) {
		this.nroComprobante = nroComprobante;
	}
	/**
	 * @return the nroPagos
	 */
	public BigDecimal getNroPagos() {
		return nroPagos;
	}
	/**
	 * @param nroPagos the nroPagos to set
	 */
	public void setNroPagos(BigDecimal nroPagos) {
		this.nroPagos = nroPagos;
	}
	/**
	 * @return the total
	 */
	public BigDecimal getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}	

	/**
	 * @return the detalles
	 */
	public List getDetalles() {
		return detalles;
	}
	/**
	 * @param detalles the detalles to set
	 */
	public void setDetalles(List detalles) {
		this.detalles = detalles;
	}
	
	public void addDetalle(DetalleTrxEft detalle) {
		detalles.add(detalle);
	}	
	
	public void setFechaTransaccion(Date fecha) {
		getPago().setFechaTransaccion(fecha);
	}
	
	public void setFechaContable(Date fecha) {
		getPago().setFechaContable(fecha);
	}
	
	public void addDatosNotificacion(TransaccionEft trx) {
		setCodRetorno(trx.getCodRetorno());
		setDescRetorno(trx.getDescRetorno());
		setFechaTransaccion(trx.getPago().getFechaTransaccion());
		setFechaContable(trx.getPago().getFechaContable());
		setNroComprobante(trx.getNroComprobante());
		setIdRegistro(trx.getIdRegistro());	
	}
	
	public void addDatosFinalizacion(TransaccionEft trx) {
		setCodRetorno(trx.getCodRetorno());
		setIndicadorPago(trx.getIndicadorPago());
		setIdRegistro(trx.getIdRegistro());
	}
	
	public void addDatosConfirmacion(TransaccionEft trx) {
		setCodRetorno(trx.getCodRetorno());
		setDescRetorno(trx.getDescRetorno());
		setFechaTransaccion(trx.getPago().getFechaTransaccion());
		setFechaContable(trx.getPago().getFechaContable());
		setNroComprobante(trx.getNroComprobante());
		setIdRegistro(trx.getIdRegistro());
	}
	
	/**
	 * Entrega un BigDecimal valido.
	 * @param numero
	 * @return
	 */
	private static BigDecimal getBigDecimal(String numero) {
		BigDecimal result = new BigDecimal(0);
		BigDecimalValidator val = BigDecimalValidator.getInstance();
		if (val.validate(numero)!=null) {
			result = new BigDecimal(numero); 	
		}
		return result;
	}
}
