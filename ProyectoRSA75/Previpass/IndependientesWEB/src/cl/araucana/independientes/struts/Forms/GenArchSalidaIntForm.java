package cl.araucana.independientes.struts.Forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class GenArchSalidaIntForm extends ActionForm{

    private static final long serialVersionUID = 1L;

    private String opcion;
    private String resultado;

    private String txt_Usuario;
    private String txt_Fecha;
    private String txt_NumSesion;
    private String txt_FecSesion;
    private String txt_FecInicio;
    private String txt_FecCorte;

    //campos de id.
    private String idSesionDirectorio;

    /*Inicializador*/
    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        ActionErrors errores = new ActionErrors();

        return errores;
    }

    /*Generación de get and Set */
    public String getIdSesionDirectorio() {
        return idSesionDirectorio;
    }

    public void setIdSesionDirectorio(String idSesionDirectorio) {
        this.idSesionDirectorio = idSesionDirectorio;
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

    public String getTxt_FecCorte() {
        return txt_FecCorte;
    }

    public void setTxt_FecCorte(String txt_FecCorte) {
        this.txt_FecCorte = txt_FecCorte;
    }

    public String getTxt_Fecha() {
        return txt_Fecha;
    }

    public void setTxt_Fecha(String txt_Fecha) {
        this.txt_Fecha = txt_Fecha;
    }

    public String getTxt_FecInicio() {
        return txt_FecInicio;
    }

    public void setTxt_FecInicio(String txt_FecInicio) {
        this.txt_FecInicio = txt_FecInicio;
    }

    public String getTxt_FecSesion() {
        return txt_FecSesion;
    }

    public void setTxt_FecSesion(String txt_FecSesion) {
        this.txt_FecSesion = txt_FecSesion;
    }

    public String getTxt_NumSesion() {
        return txt_NumSesion;
    }

    public void setTxt_NumSesion(String txt_NumSesion) {
        this.txt_NumSesion = txt_NumSesion;
    }

    public String getTxt_Usuario() {
        return txt_Usuario;
    }

    public void setTxt_Usuario(String txt_Usuario) {
        this.txt_Usuario = txt_Usuario;
    }
}
