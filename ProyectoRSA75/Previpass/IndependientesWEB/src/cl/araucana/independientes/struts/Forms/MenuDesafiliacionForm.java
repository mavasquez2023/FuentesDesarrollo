package cl.araucana.independientes.struts.Forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.*;

/*Clase MenuPpalForm
 * Contiene la variable que contiene el valor de la opcion que se eligi�, dependiendo del tipo de formulario al
 * que se har� ingreso. */

public class MenuDesafiliacionForm extends ActionForm {

    private static final long serialVersionUID = 1L;

    /*Declaraci�n de variables de la clase.*/	
    private String opcion;

    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        ActionErrors errores = new ActionErrors();

        return errores;
    }

    /*Generaci�n de Getting and Setting de la clase.*/
    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

}
