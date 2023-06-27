package cl.araucana.independientes.vo;

public class LinRepNominaApoAfiVO {

    private int mesAporte;
    private int fechaVigencia;
    private int fechaPago;
    private int codigoEventoContable;
    private String eventoContable;
    private int monto;
    private int codigoEstado;
    private String Estado;
    private String formaPago;
    private String tipoPago;
    private String valorPago;

    public String getTipoPago() {
        return tipoPago;
    }
    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }
    public String getValorPago() {
        return valorPago;
    }
    public void setValorPago(String valorPago) {
        this.valorPago = valorPago;
    }
    public String getFormaPago() {
        return formaPago;
    }
    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }
    public int getCodigoEstado() {
        return codigoEstado;
    }
    public void setCodigoEstado(int codigoEstado) {
        this.codigoEstado = codigoEstado;
    }
    public int getCodigoEventoContable() {
        return codigoEventoContable;
    }
    public void setCodigoEventoContable(int codigoEventoContable) {
        this.codigoEventoContable = codigoEventoContable;
    }
    public String getEstado() {
        return Estado;
    }
    public void setEstado(String estado) {
        Estado = estado;
    }
    public String getEventoContable() {
        return eventoContable;
    }
    public void setEventoContable(String eventoContable) {
        this.eventoContable = eventoContable;
    }
    public int getFechaPago() {
        return fechaPago;
    }
    public void setFechaPago(int fechaPago) {
        this.fechaPago = fechaPago;
    }
    public int getMonto() {
        return monto;
    }
    public void setMonto(int monto) {
        this.monto = monto;
    }
    public int getFechaVigencia() {
        return fechaVigencia;
    }
    public void setFechaVigencia(int fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }
    public int getMesAporte() {
        return mesAporte;
    }
    public void setMesAporte(int mesAporte) {
        this.mesAporte = mesAporte;
    }
}
