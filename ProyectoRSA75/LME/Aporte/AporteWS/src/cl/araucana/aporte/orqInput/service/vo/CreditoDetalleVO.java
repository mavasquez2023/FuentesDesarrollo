package cl.araucana.aporte.orqInput.service.vo;

import java.io.Serializable;

public class CreditoDetalleVO implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 4833139352644342558L;
    private int codigoOficina;
    private int folioCredito;
    private int numCuota;
    private int totalCoutas;
    private int estadoCouta;
    private int fechaVencimiento;
    private int lineaCredito;
    private int valorCouta;
    private int capital;
    private int seguros;
    private int intereses;
    private int gravamenes;
    private int multas;
    private int montoAbonado;
    private int montoDescuento;

    public int getCapital() {
        return capital;
    }
    public void setCapital(int capital) {
        this.capital = capital;
    }
    public int getCodigoOficina() {
        return codigoOficina;
    }
    public void setCodigoOficina(int codigoOficina) {
        this.codigoOficina = codigoOficina;
    }
    public int getEstadoCouta() {
        return estadoCouta;
    }
    public void setEstadoCouta(int estadoCouta) {
        this.estadoCouta = estadoCouta;
    }
    public int getFechaVencimiento() {
        return fechaVencimiento;
    }
    public void setFechaVencimiento(int fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    public int getFolioCredito() {
        return folioCredito;
    }
    public void setFolioCredito(int folioCredito) {
        this.folioCredito = folioCredito;
    }
    public int getGravamenes() {
        return gravamenes;
    }
    public void setGravamenes(int gravamenes) {
        this.gravamenes = gravamenes;
    }
    public int getIntereses() {
        return intereses;
    }
    public void setIntereses(int intereses) {
        this.intereses = intereses;
    }
    public int getLineaCredito() {
        return lineaCredito;
    }
    public void setLineaCredito(int lineaCredito) {
        this.lineaCredito = lineaCredito;
    }
    public int getMontoAbonado() {
        return montoAbonado;
    }
    public void setMontoAbonado(int montoAbonado) {
        this.montoAbonado = montoAbonado;
    }
    public int getMontoDescuento() {
        return montoDescuento;
    }
    public void setMontoDescuento(int montoDescuento) {
        this.montoDescuento = montoDescuento;
    }
    public int getMultas() {
        return multas;
    }
    public void setMultas(int multas) {
        this.multas = multas;
    }
    public int getNumCuota() {
        return numCuota;
    }
    public void setNumCuota(int numCuota) {
        this.numCuota = numCuota;
    }
    public int getSeguros() {
        return seguros;
    }
    public void setSeguros(int seguros) {
        this.seguros = seguros;
    }
    public int getTotalCoutas() {
        return totalCoutas;
    }
    public void setTotalCoutas(int totalCoutas) {
        this.totalCoutas = totalCoutas;
    }
    public int getValorCouta() {
        return valorCouta;
    }
    public void setValorCouta(int valorCouta) {
        this.valorCouta = valorCouta;
    }
}
