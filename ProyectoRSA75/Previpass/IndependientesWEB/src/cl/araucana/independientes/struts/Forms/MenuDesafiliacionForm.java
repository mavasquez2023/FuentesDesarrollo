package cl.araucana.independientes.struts.Forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.*;

/*Clase MenuPpalForm
 * Contiene la variable que contiene el valor de la opcion que se eligió, dependiendo del tipo de formulario al
 * que se hará ingreso. */

public class MenuDesafiliacionForm extends ActionForm {

    private static final long serialVersionUID = 1L;

    /*Declaración de variables de la clase.*/	
    private String opcion;

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

}
