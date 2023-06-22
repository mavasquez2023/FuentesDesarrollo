package cl.araucana.sivegam.struts.Forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class GeneracionReportesCesantiaForm extends ActionForm {

    private static final long serialVersionUID = 1L;

    /* Declaración de variables de la clase. */
    private String error;
    private String opcion;
    private String rutaArchivo;
    private String idReporte;
    private String anio;
    private String mes;

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errores = new ActionErrors();

        return errores;
    }

    /* Generación de Getting and Setting de la clase. */
    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(String idReporte) {
        this.idReporte = idReporte;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
