package cl.araucana.independientes.vo;

import java.util.Date;

public class LinMantTabPromVO {

    /*Declaracion de variables*/
    private String id;
    private String codigo;
    private String rut;
    private String nombres;
    private String estado;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String tipoOrgan;
    private String tipoCargo;
    private String codArea;
    private String nroTelefono;
    private String fechaInicio;
    private Date fechaInicioDate;
    private String fechaBaja;
    private Date fechaBajaDate;
    private String glosaCalle;

    public String getCodArea() {
        return codArea;
    }
    public void setCodArea(String codArea) {
        this.codArea = codArea;
    }
    public String getFechaBaja() {
        return fechaBaja;
    }
    public void setFechaBaja(String fechaBaja) {
        this.fechaBaja = fechaBaja;
    }
    public Date getFechaBajaDate() {
        return fechaBajaDate;
    }
    public void setFechaBajaDate(Date fechaBajaDate) {
        this.fechaBajaDate = fechaBajaDate;
    }
    public String getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public Date getFechaInicioDate() {
        return fechaInicioDate;
    }
    public void setFechaInicioDate(Date fechaInicioDate) {
        this.fechaInicioDate = fechaInicioDate;
    }
    public String getGlosaCalle() {
        return glosaCalle;
    }
    public void setGlosaCalle(String glosaCalle) {
        this.glosaCalle = glosaCalle;
    }
    public String getNroTelefono() {
        return nroTelefono;
    }
    public void setNroTelefono(String nroTelefono) {
        this.nroTelefono = nroTelefono;
    }
    public String getTipoCargo() {
        return tipoCargo;
    }
    public void setTipoCargo(String tipoCargo) {
        this.tipoCargo = tipoCargo;
    }
    public String getTipoOrgan() {
        return tipoOrgan;
    }
    public void setTipoOrgan(String tipoOrgan) {
        this.tipoOrgan = tipoOrgan;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getRut() {
        return rut;
    }
    public void setRut(String rut) {
        this.rut = rut;
    }
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
}
