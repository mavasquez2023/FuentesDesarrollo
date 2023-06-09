package cl.araucana.independientes.struts.Forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.*;

public class ConsModMasivaSolForm extends ActionForm{

    private static final long serialVersionUID = 1L;

    /*Declaración de variables usadas.*/
    private String opcion;
    private String resultado;

    private String txt_FecDesde;
    private String txt_FecHasta;
    private String dbx_EstSolicitud;

    private String txt_FecDesdeTmp;
    private String txt_FecHastaTmp;
    private String dbx_EstSolicitudTmp;

    /*Inicializador*/
    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        ActionErrors errores = new ActionErrors();

        return errores;
    }

    /*Generación de Getting and Setting*/
    public String getDbx_EstSolicitud() {
        return dbx_EstSolicitud;
    }
    public void setDbx_EstSolicitud(String dbx_EstSolicitud) {
        this.dbx_EstSolicitud = dbx_EstSolicitud;
    }
    public String getDbx_EstSolicitudTmp() {
        return dbx_EstSolicitudTmp;
    }
    public void setDbx_EstSolicitudTmp(String dbx_EstSolicitudTmp) {
        this.dbx_EstSolicitudTmp = dbx_EstSolicitudTmp;
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
    public String getTxt_FecDesde() {
        return txt_FecDesde;
    }
    public void setTxt_FecDesde(String txt_FecDesde) {
        this.txt_FecDesde = txt_FecDesde;
    }
    public String getTxt_FecDesdeTmp() {
        return txt_FecDesdeTmp;
    }
    public void setTxt_FecDesdeTmp(String txt_FecDesdeTmp) {
        this.txt_FecDesdeTmp = txt_FecDesdeTmp;
    }
    public String getTxt_FecHasta() {
        return txt_FecHasta;
    }
    public void setTxt_FecHasta(String txt_FecHasta) {
        this.txt_FecHasta = txt_FecHasta;
    }
    public String getTxt_FecHastaTmp() {
        return txt_FecHastaTmp;
    }
    public void setTxt_FecHastaTmp(String txt_FecHastaTmp) {
        this.txt_FecHastaTmp = txt_FecHastaTmp;
    }



}
