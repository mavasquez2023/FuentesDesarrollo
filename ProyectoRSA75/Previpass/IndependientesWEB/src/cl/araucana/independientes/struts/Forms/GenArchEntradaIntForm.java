package cl.araucana.independientes.struts.Forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class GenArchEntradaIntForm extends ActionForm{

    private static final long serialVersionUID = 1L;

    /*Declaracion de las variables de la clase GenGenArchEntradaIntForm*/

    private String opcion;
    private String resultado;	

    private String txt_Usuario;
    private String txt_Fecha;
    private String txt_rutaArchivo;
    private String dbx_tipoArchivo;

    private FormFile theFile;//se usa para hacer upload al file.

    /*Inicializador*/
    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        ActionErrors errores = new ActionErrors();

        return errores;
    }

    /*Generación de get and Set */
    public FormFile getTheFile() {
        return theFile;
    }

    public void setTheFile(FormFile theFile) {
        this.theFile = theFile;
    }

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

    public String getTxt_rutaArchivo() {
        return txt_rutaArchivo;
    }

    public void setTxt_rutaArchivo(String txt_rutaArchivo) {
        this.txt_rutaArchivo = txt_rutaArchivo;
    }

    public String getTxt_Usuario() {
        return txt_Usuario;
    }

    public void setTxt_Usuario(String txt_Usuario) {
        this.txt_Usuario = txt_Usuario;
    }
}
