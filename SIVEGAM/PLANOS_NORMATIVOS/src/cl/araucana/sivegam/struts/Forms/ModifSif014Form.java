package cl.araucana.sivegam.struts.Forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ModifSif014Form extends ActionForm {

    private static final long serialVersionUID = 1L;

    /* Declaración de variables de la clase. */
    private String opcion;
    private String error;
    private String idSelectedItem;
    private String idSif014_glob;
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

    public String getIdSif014_glob() {
        return idSif014_glob;
    }

    public void setIdSif014_glob(String idSif014_glob) {
        this.idSif014_glob = idSif014_glob;
    }

    public String getRutSearch() {
        return rutSearch;
    }

    public void setRutSearch(String rutSearch) {
        this.rutSearch = rutSearch;
    }
}
