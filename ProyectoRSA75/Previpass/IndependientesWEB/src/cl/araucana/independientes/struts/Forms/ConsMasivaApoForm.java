package cl.araucana.independientes.struts.Forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.*;

public class ConsMasivaApoForm extends ActionForm{

    private static final long serialVersionUID = 1L;

    /*Declaración de variables usadas.*/
    private String opcion;
    private String resultado;
    private String archivo;

    private String txt_FecPrdDesde;
    private String txt_FecPrdHasta;
    private String dbx_EstAporte;
    private String dbx_Oficina;

    public String getDbx_Oficina() {
        return dbx_Oficina;
    }

    public void setDbx_Oficina(String dbx_Oficina) {
        this.dbx_Oficina = dbx_Oficina;
    }

    /*Inicializador*/
    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        ActionErrors errores = new ActionErrors();

        return errores;
    }

    public String getDbx_EstAporte() {
        return dbx_EstAporte;
    }

    public void setDbx_EstAporte(String dbx_EstAporte) {
        this.dbx_EstAporte = dbx_EstAporte;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getTxt_FecPrdDesde() {
        return txt_FecPrdDesde;
    }

    public void setTxt_FecPrdDesde(String txt_FecPrdDesde) {
        this.txt_FecPrdDesde = txt_FecPrdDesde;
    }

    public String getTxt_FecPrdHasta() {
        return txt_FecPrdHasta;
    }

    public void setTxt_FecPrdHasta(String txt_FecPrdHasta) {
        this.txt_FecPrdHasta = txt_FecPrdHasta;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

}
