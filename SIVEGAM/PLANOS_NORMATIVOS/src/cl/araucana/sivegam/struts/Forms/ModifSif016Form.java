package cl.araucana.sivegam.struts.Forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ModifSif016Form extends ActionForm {
    private static final long serialVersionUID = 1L;

    /* Declaración de variables de la clase. */
    private String opcion;
    private String error;
    private String idSelectedItem;
    private String idSif016_glob;
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

    public String getIdSif016_glob() {
        return idSif016_glob;
    }

    public void setIdSif016_glob(String idSif016_glob) {
        this.idSif016_glob = idSif016_glob;
    }

    public String getRutSearch() {
        return rutSearch;
    }

    public void setRutSearch(String rutSearch) {
        this.rutSearch = rutSearch;
    }

}
