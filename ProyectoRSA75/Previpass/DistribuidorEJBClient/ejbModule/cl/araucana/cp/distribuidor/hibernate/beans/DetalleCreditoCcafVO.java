package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class DetalleCreditoCcafVO implements Serializable{
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idCredito;
      private long idCodigoBarra;
      private long capital;
      private int codigoOficina;
      private int estadoActual;
      private int fechaVencimiente;
      private int folioCredito;
      private long gravamenes;
      private long interes;
      private long lineaCredito;
      private long montoAbonado;
      private long montoDescuenro;
      private long multa;
      private int numCuata;
      private long seguros;
      private long totalCouta;
      private long valorCuota;
      private long montoTotal;
      
    public DetalleCreditoCcafVO(long idCodigoBarra){
    	this.idCodigoBarra=idCodigoBarra;
    }
    public DetalleCreditoCcafVO(){
    	
    }
      
	public long getCapital() {
		return capital;
	}
	public void setCapital(long capital) {
		this.capital = capital;
	}
	public int getCodigoOficina() {
		return codigoOficina;
	}
	public void setCodigoOficina(int codigoOficina) {
		this.codigoOficina = codigoOficina;
	}
	public int getEstadoActual() {
		return estadoActual;
	}
	public void setEstadoActual(int estadoActual) {
		this.estadoActual = estadoActual;
	}
	public int getFechaVencimiente() {
		return fechaVencimiente;
	}
	public void setFechaVencimiente(int fechaVencimiente) {
		this.fechaVencimiente = fechaVencimiente;
	}
	public int getFolioCredito() {
		return folioCredito;
	}
	public void setFolioCredito(int folioCredito) {
		this.folioCredito = folioCredito;
	}
	public long getGravamenes() {
		return gravamenes;
	}
	public void setGravamenes(long gravamenes) {
		this.gravamenes = gravamenes;
	}
	public long getIdCodigoBarra() {
		return idCodigoBarra;
	}
	public void setIdCodigoBarra(long idCodigoBarra) {
		this.idCodigoBarra = idCodigoBarra;
	}
	public int getIdCredito() {
		return idCredito;
	}
	public void setIdCredito(int idCredito) {
		this.idCredito = idCredito;
	}
	public long getInteres() {
		return interes;
	}
	public void setInteres(long interes) {
		this.interes = interes;
	}
	public long getLineaCredito() {
		return lineaCredito;
	}
	public void setLineaCredito(long lineaCredito) {
		this.lineaCredito = lineaCredito;
	}
	public long getMontoAbonado() {
		return montoAbonado;
	}
	public void setMontoAbonado(long montoAbonado) {
		this.montoAbonado = montoAbonado;
	}
	public long getMontoDescuenro() {
		return montoDescuenro;
	}
	public void setMontoDescuenro(long montoDescuenro) {
		this.montoDescuenro = montoDescuenro;
	}
	public long getMulta() {
		return multa;
	}
	public void setMulta(long multa) {
		this.multa = multa;
	}
	public int getNumCuata() {
		return numCuata;
	}
	public void setNumCuata(int numCuata) {
		this.numCuata = numCuata;
	}
	public long getSeguros() {
		return seguros;
	}
	public void setSeguros(long seguros) {
		this.seguros = seguros;
	}
	public long getTotalCouta() {
		return totalCouta;
	}
	public void setTotalCouta(long totalCouta) {
		this.totalCouta = totalCouta;
	}
	public long getValorCuota() {
		return valorCuota;
	}
	public void setValorCuota(long valorCuota) {
		this.valorCuota = valorCuota;
	}
	public long getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(long montoTotal) {
		this.montoTotal = montoTotal;
	}
   }
