package cl.araucana.independientes.struts.Forms;

import org.apache.struts.action.*;

public class HistoricoBeneficiosForm extends ActionForm {

    private static final long serialVersionUID = 1L;

    private String opcion;
    private String resultado;

    private String txt_NRut;
    private String txt_NNumVerif;
    private String txt_estadoAfiliado;
    private String txt_nombreAfiliado;
    private String txt_cantReembolsos;
    private String txt_montoReembolsado;

    public String getTxt_cantReembolsos() {
        return txt_cantReembolsos;
    }
    public void setTxt_cantReembolsos(String txt_cantReembolsos) {
        this.txt_cantReembolsos = txt_cantReembolsos;
    }
    public String getTxt_estadoAfiliado() {
        return txt_estadoAfiliado;
    }
    public void setTxt_estadoAfiliado(String txt_estadoAfiliado) {
        this.txt_estadoAfiliado = txt_estadoAfiliado;
    }
    public String getTxt_montoReembolsado() {
        return txt_montoReembolsado;
    }
    public void setTxt_montoReembolsado(String txt_montoReembolsado) {
        this.txt_montoReembolsado = txt_montoReembolsado;
    }
    public String getTxt_nombreAfiliado() {
        return txt_nombreAfiliado;
    }
    public void setTxt_nombreAfiliado(String txt_nombreAfiliado) {
        this.txt_nombreAfiliado = txt_nombreAfiliado;
    }
    public String getTxt_NNumVerif() {
        return txt_NNumVerif;
    }
    public void setTxt_NNumVerif(String txt_NNumVerif) {
        this.txt_NNumVerif = txt_NNumVerif;
    }
    public String getTxt_NRut() {
        return txt_NRut;
    }
    public void setTxt_NRut(String txt_NRut) {
        this.txt_NRut = txt_NRut;
    }
    public String getOpcion() {
        return opcion;
    }
    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }
    public String getResultado() {
        return resultado;
    }
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

}
