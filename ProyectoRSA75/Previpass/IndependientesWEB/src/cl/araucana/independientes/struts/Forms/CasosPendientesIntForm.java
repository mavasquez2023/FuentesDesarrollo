package cl.araucana.independientes.struts.Forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class CasosPendientesIntForm extends ActionForm{

    private static final long serialVersionUID = 1L;

    /*Declaracion de las variables de la clase GenGenArchEntradaIntForm*/

    private String opcion;
    private String resultado;	

    private String txt_Usuario;
    private String txt_Fecha;
    private String txt_FechaInferior;
    private String txt_FechaSuperior;
    private String dbx_tipoArchivo;

    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        ActionErrors errores = new ActionErrors();

        return errores;
    }

    /*Establecimiento de get and set*/
    public String getDbx_tipoArchivo() {
        return dbx_tipoArchivo;
    }

    public void setDbx_tipoArchivo(String dbx_tipoArchivo) {
        this.dbx_tipoArchivo = dbx_tipoArchivo;
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

    public String getTxt_Fecha() {
        return txt_Fecha;
    }

    public void setTxt_Fecha(String txt_Fecha) {
        this.txt_Fecha = txt_Fecha;
    }

    public String getTxt_FechaInferior() {
        return txt_FechaInferior;
    }

    public void setTxt_FechaInferior(String txt_FechaInferior) {
        this.txt_FechaInferior = txt_FechaInferior;
    }

    public String getTxt_FechaSuperior() {
        return txt_FechaSuperior;
    }

    public void setTxt_FechaSuperior(String txt_FechaSuperior) {
        this.txt_FechaSuperior = txt_FechaSuperior;
    }

    public String getTxt_Usuario() {
        return txt_Usuario;
    }

    public void setTxt_Usuario(String txt_Usuario) {
        this.txt_Usuario = txt_Usuario;
    }

}
