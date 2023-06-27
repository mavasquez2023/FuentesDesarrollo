package cl.araucana.independientes.struts.Forms;

import org.apache.struts.action.ActionForm;

public class SolBeneficiosForm extends ActionForm{

    private static final long serialVersionUID = 1L;

    private String opcion;
    private String txt_NRut;
    private String txt_NNumVerif;
    private String txt_estadoAfiliado;
    private String txt_nombreAfiliado;
    private String txt_fechaVigencia;
    private String txt_diasAfiliado;
    private String txt_montoReembolsar;
    private String txt_estadoAporte;

    private String txt_idPersonaAfiliado;

    private String txt_FecCarencia;

    public String getTxt_idPersonaAfiliado() {
        return txt_idPersonaAfiliado;
    }
    public void setTxt_idPersonaAfiliado(String txt_idPersonaAfiliado) {
        this.txt_idPersonaAfiliado = txt_idPersonaAfiliado;
    }
    public String getOpcion() {
        return opcion;
    }
    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }
    public String getTxt_diasAfiliado() {
        return txt_diasAfiliado;
    }
    public void setTxt_diasAfiliado(String txt_diasAfiliado) {
        this.txt_diasAfiliado = txt_diasAfiliado;
    }
    public String getTxt_estadoAfiliado() {
        return txt_estadoAfiliado;
    }
    public void setTxt_estadoAfiliado(String txt_estadoAfiliado) {
        this.txt_estadoAfiliado = txt_estadoAfiliado;
    }
    public String getTxt_estadoAporte() {
        return txt_estadoAporte;
    }
    public void setTxt_estadoAporte(String txt_estadoAporte) {
        this.txt_estadoAporte = txt_estadoAporte;
    }
    public String getTxt_fechaVigencia() {
        return txt_fechaVigencia;
    }
    public void setTxt_fechaVigencia(String txt_fechaVigencia) {
        this.txt_fechaVigencia = txt_fechaVigencia;
    }
    public String getTxt_montoReembolsar() {
        return txt_montoReembolsar;
    }
    public void setTxt_montoReembolsar(String txt_montoReembolsar) {
        this.txt_montoReembolsar = txt_montoReembolsar;
    }
    public String getTxt_NNumVerif() {
        return txt_NNumVerif;
    }
    public void setTxt_NNumVerif(String txt_NNumVerif) {
        this.txt_NNumVerif = txt_NNumVerif;
    }
    public String getTxt_nombreAfiliado() {
        return txt_nombreAfiliado;
    }
    public void setTxt_nombreAfiliado(String txt_nombreAfiliado) {
        this.txt_nombreAfiliado = txt_nombreAfiliado;
    }
    public String getTxt_NRut() {
        return txt_NRut;
    }
    public void setTxt_NRut(String txt_NRut) {
        this.txt_NRut = txt_NRut;
    }
    public String getTxt_FecCarencia() {
        return txt_FecCarencia;
    }
    public void setTxt_FecCarencia(String txt_FecCarencia) {
        this.txt_FecCarencia = txt_FecCarencia;
    }
}
