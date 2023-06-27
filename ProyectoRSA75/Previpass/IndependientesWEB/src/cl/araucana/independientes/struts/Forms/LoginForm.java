package cl.araucana.independientes.struts.Forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.*;

/*Clase LoginForm.
 * Implementa el logueo o ingreso de un analista a la aplicación.*/
public class LoginForm extends ActionForm {

    private static final long serialVersionUID = 1L;

    private String resultado;

    /*Declaración de variables de la clase.*/
    private String Usuario;
    private String Clave;

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

    /*Procedimiento para limpiar la variable que contiene a la clave*/
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.Clave = "";
    }

    /*Generacion de Getting and Setting de la clase.*/	
    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }


}
