package cl.araucana.independientes.struts.Forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.*;

/*Clase MenuPpalForm
 * Contiene la variable que contiene el valor de la opcion que se eligió, dependiendo del tipo de formulario al
 * que se hará ingreso. */

public class MenuPpalForm extends ActionForm {

    private static final long serialVersionUID = 1L;

    /*Declaración de variables de la clase.*/	
    private String opcion;
    private String dbx_Oficinas;

    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        ActionErrors errores = new ActionErrors();
        /*if (usuario.trim().length() <= 0) {

			errores.add("usuario", new ActionMessage("login.usr.noCompleta"));
		}
		if (clave.trim().length() <= 0) {
			errores.add("clave", new ActionMessage("login.pwd.noCompleta"));
		}*/
        return errores;
    }

    /*Generación de Getting and Setting de la clase.*/
    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public String getDbx_Oficinas() {
        return dbx_Oficinas;
    }

    public void setDbx_Oficinas(String dbx_Oficinas) {
        this.dbx_Oficinas = dbx_Oficinas;
    }
}
