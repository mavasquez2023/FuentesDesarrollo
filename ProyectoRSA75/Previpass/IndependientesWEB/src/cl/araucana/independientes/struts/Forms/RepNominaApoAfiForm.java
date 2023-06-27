package cl.araucana.independientes.struts.Forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RepNominaApoAfiForm  extends ActionForm{
    /**
     * 
     */
    private static final long serialVersionUID = 7585955870948170638L;
    private String opcion;
    private String resultado;
    private String archivo;

    private String txt_rut;
    private String txt_nombreAfiliado;
    private String txt_apellidoPaternoAfiliado;
    private String txt_apellidoMaternoAfiliado;
    private String txt_estadoAfiliado;
    private String txt_oficinaAfiliacion;


    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        ActionErrors errores = new ActionErrors();
        return errores;
    }

    public String getArchivo() {
        return archivo;
    }
    public void setArchivo(String archivo) {
        this.archivo = archivo;
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
    public String getTxt_rut() {
        return txt_rut;
    }
    public void setTxt_rut(String txt_rut) {
        this.txt_rut = txt_rut;
    }

    public String getTxt_apellidoMaternoAfiliado() {
        return txt_apellidoMaternoAfiliado;
    }

    public void setTxt_apellidoMaternoAfiliado(String txt_apellidoMaternoAfiliado) {
        this.txt_apellidoMaternoAfiliado = txt_apellidoMaternoAfiliado;
    }

    public String getTxt_apellidoPaternoAfiliado() {
        return txt_apellidoPaternoAfiliado;
    }

    public void setTxt_apellidoPaternoAfiliado(String txt_apellidoPaternoAfiliado) {
        this.txt_apellidoPaternoAfiliado = txt_apellidoPaternoAfiliado;
    }

    public String getTxt_estadoAfiliado() {
        return txt_estadoAfiliado;
    }

    public void setTxt_estadoAfiliado(String txt_estadoAfiliado) {
        this.txt_estadoAfiliado = txt_estadoAfiliado;
    }

    public String getTxt_nombreAfiliado() {
        return txt_nombreAfiliado;
    }

    public void setTxt_nombreAfiliado(String txt_nombreAfiliado) {
        this.txt_nombreAfiliado = txt_nombreAfiliado;
    }

    public String getTxt_oficinaAfiliacion() {
        return txt_oficinaAfiliacion;
    }

    public void setTxt_oficinaAfiliacion(String txt_oficinaAfiliacion) {
        this.txt_oficinaAfiliacion = txt_oficinaAfiliacion;
    }

}
