package cl.araucana.independientes.struts.Forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class UploadFileForm extends ActionForm{

    private static final long serialVersionUID = 1L;

    /*Declaración de variables de la clase.*/	
    private String opcion;
    private String resultado;	

    private String txt_Usuario;
    private String txt_Fecha;


    private FormFile theFile;//se usa para hacer upload al file.

    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        ActionErrors errores = new ActionErrors();

        return errores;
    }

    /*Generación de Getting and Setting de la clase.*/
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

    public FormFile getTheFile() {
        return theFile;
    }

    public void setTheFile(FormFile theFile) {
        this.theFile = theFile;
    }

    public String getTxt_Fecha() {
        return txt_Fecha;
    }

    public void setTxt_Fecha(String txt_Fecha) {
        this.txt_Fecha = txt_Fecha;
    }

    public String getTxt_Usuario() {
        return txt_Usuario;
    }

    public void setTxt_Usuario(String txt_Usuario) {
        this.txt_Usuario = txt_Usuario;
    }

}
