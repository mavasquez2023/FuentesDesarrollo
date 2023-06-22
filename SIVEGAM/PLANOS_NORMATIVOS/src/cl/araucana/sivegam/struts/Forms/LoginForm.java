package cl.araucana.sivegam.struts.Forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class LoginForm extends ActionForm {

    //private static final long serialVersionUID = 1L;

    /* declaracion de variables de la clase LoginForm */
    private String resultado;
    private String Usuario;
    private String Clave;
    private String forwardPage;

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errores = new ActionErrors();

        return null;
    }

    /* Procedimiento para limpiar la variable que contiene a la clave */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
    	forwardPage = "";
    	this.Clave = "";
    }

    /* generacion de get and set. */
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

	public String getForwardPage() {
		return forwardPage;
	}

	public void setForwardPage(String forwardPage) {
		this.forwardPage = forwardPage;
	}
}
