package cl.araucana.sivegam.struts.Forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ModifSif011Form extends ActionForm {

    private static final long serialVersionUID = 1L;

    /* Declaración de variables de la clase. */
    private String opcion;
    private String error;
    private String mod;
    private String idSelectedItem;
    private String idSif011_glob; 
    private String rutSearch;
    //param busqueda
    private String dbx_filtroBusqueda;
    private String txt_rut;
    private String txt_digitoVerificador;
    private String txt_primerRango;
    private String txt_segundoRango;

    public String getIdSelectedItem() {
		return idSelectedItem;
	}

	public void setIdSelectedItem(String idSelectedItem) {
		this.idSelectedItem = idSelectedItem;
	}

	public String getIdSif011_glob() {
		return idSif011_glob;
	}

	public void setIdSif011_glob(String idSif011_glob) {
		this.idSif011_glob = idSif011_glob;
	}

	public String getRutSearch() {
		return rutSearch;
	}

	public void setRutSearch(String rutSearch) {
		this.rutSearch = rutSearch;
	}
    
    public String getMod() {
		return mod;
	}

	public void setMod(String mod) {
		this.mod = mod;
	}

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

	public String getDbx_filtroBusqueda() {
		return dbx_filtroBusqueda;
	}

	public void setDbx_filtroBusqueda(String dbx_filtroBusqueda) {
		this.dbx_filtroBusqueda = dbx_filtroBusqueda;
	}

	public String getTxt_digitoVerificador() {
		return txt_digitoVerificador;
	}

	public void setTxt_digitoVerificador(String txt_digitoVerificador) {
		this.txt_digitoVerificador = txt_digitoVerificador;
	}

	public String getTxt_primerRango() {
		return txt_primerRango;
	}

	public void setTxt_primerRango(String txt_primerRango) {
		this.txt_primerRango = txt_primerRango;
	}

	public String getTxt_rut() {
		return txt_rut;
	}

	public void setTxt_rut(String txt_rut) {
		this.txt_rut = txt_rut;
	}

	public String getTxt_segundoRango() {
		return txt_segundoRango;
	}

	public void setTxt_segundoRango(String txt_segundoRango) {
		this.txt_segundoRango = txt_segundoRango;
	}
}
