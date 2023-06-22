package cl.araucana.sivegam.struts.Forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ModifSif012Form extends ActionForm {

    private static final long serialVersionUID = 1L;

    /* Declaración de variables de la clase. */
    private String opcion;
    private String error;
    private String idSelectedItem;
    private String idSif012_glob;
    private String rutSearch;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

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

    public String getIdSelectedItem() {
        return idSelectedItem;
    }

    public void setIdSelectedItem(String idSelectedItem) {
        this.idSelectedItem = idSelectedItem;
    }

    public String getIdSif012_glob() {
        return idSif012_glob;
    }

    public void setIdSif012_glob(String idSif012_glob) {
        this.idSif012_glob = idSif012_glob;
    }

    public String getRutSearch() {
        return rutSearch;
    }

    public void setRutSearch(String rutSearch) {
        this.rutSearch = rutSearch;
    }
}
