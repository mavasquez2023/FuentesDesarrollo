package cl.araucana.sivegam.struts.Forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ModifSif018Form extends ActionForm {

    private static final long serialVersionUID = 1L;

    /* Declaración de variables de la clase. */
    private String opcion;
    private String error;
    private String idSelectedItem;
    private String idSif018_glob;
    private String rutSearch;

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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getIdSelectedItem() {
        return idSelectedItem;
    }

    public void setIdSelectedItem(String idSelectedItem) {
        this.idSelectedItem = idSelectedItem;
    }

    public String getIdSif018_glob() {
        return idSif018_glob;
    }

    public void setIdSif018_glob(String idSif018_glob) {
        this.idSif018_glob = idSif018_glob;
    }

    public String getRutSearch() {
        return rutSearch;
    }

    public void setRutSearch(String rutSearch) {
        this.rutSearch = rutSearch;
    }

}
