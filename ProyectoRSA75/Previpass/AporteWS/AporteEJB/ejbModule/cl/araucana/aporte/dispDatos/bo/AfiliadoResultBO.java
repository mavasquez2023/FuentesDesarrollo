package cl.araucana.aporte.dispDatos.bo;

import java.io.Serializable;

public class AfiliadoResultBO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -6968377241315908169L;
    private int rut;
    private String dlgVerificador;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombres;
    private int genero; 
    private int actEconomica;
    private int tipoDireccion; 
    private String direccion;
    private String numero;
    private String departamento;
    private int comuna;
    private String mail;
    private String telefono; 
    private String fax;  
    private String celular; 


    public int getActEconomica() {
        return actEconomica;
    }
    public void setActEconomica(int actEconomica) {
        this.actEconomica = actEconomica;
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
    public String getCelular() {
        return celular;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getDlgVerificador() {
        return dlgVerificador;
    }
    public void setDlgVerificador(String dlgVerificador) {
        this.dlgVerificador = dlgVerificador;
    }
    public String getFax() {
        return fax;
    }
    public void setFax(String fax) {
        this.fax = fax;
    }
    public int getGenero() {
        return genero;
    }
    public void setGenero(int genero) {
        this.genero = genero;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public int getRut() {
        return rut;
    }
    public void setRut(int rut) {
        this.rut = rut;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public int getTipoDireccion() {
        return tipoDireccion;
    }
    public void setTipoDireccion(int tipoDireccion) {
        this.tipoDireccion = tipoDireccion;
    }
    public int getComuna() {
        return comuna;
    }
    public void setComuna(int comuna) {
        this.comuna = comuna;
    }
}
