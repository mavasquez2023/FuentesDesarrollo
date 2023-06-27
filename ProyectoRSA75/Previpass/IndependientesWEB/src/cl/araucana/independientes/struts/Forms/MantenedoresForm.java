package cl.araucana.independientes.struts.Forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.*;

public class MantenedoresForm extends ActionForm {

    private static final long serialVersionUID = 1L;

    private String opcion;
    private String resultado;
    private String archivo; 

    private String dbx_Mantenedor;
    private String dbx_Entidad;
    private String dbx_EntidadFiltro;
    private String dbx_EstSolicitud;
    private String dbx_MantenedorTmp;
    private String dbx_EstSolicitudTmp;
    private String dbx_Perfil;
    private String dbx_TipoOrg;
    private String dbx_TipoCargo;
    private String dbx_BeneficioBenDinDoc;
    private String dbx_DocumentoBenDinDoc;


    public String getDbx_DocumentoBenDinDoc() {
        return dbx_DocumentoBenDinDoc;
    }

    public void setDbx_DocumentoBenDinDoc(String dbx_DocumentoBenDinDoc) {
        this.dbx_DocumentoBenDinDoc = dbx_DocumentoBenDinDoc;
    }

    public String getDbx_BeneficioBenDinDoc() {
        return dbx_BeneficioBenDinDoc;
    }

    public void setDbx_BeneficioBenDinDoc(String dbx_BeneficioBenDinDoc) {
        this.dbx_BeneficioBenDinDoc = dbx_BeneficioBenDinDoc;
    }

    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        ActionErrors errores = new ActionErrors();

        return errores;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

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

    public String getDbx_Mantenedor() {
        return dbx_Mantenedor;
    }

    public void setDbx_Mantenedor(String dbx_Mantenedor) {
        this.dbx_Mantenedor = dbx_Mantenedor;
    }

    public String getDbx_MantenedorTmp() {
        return dbx_MantenedorTmp;
    }

    public void setDbx_MantenedorTmp(String dbx_MantenedorTmp) {
        this.dbx_MantenedorTmp = dbx_MantenedorTmp;
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

    public String getDbx_Entidad() {
        return dbx_Entidad;
    }

    public void setDbx_Entidad(String dbx_Entidad) {
        this.dbx_Entidad = dbx_Entidad;
    }

    public String getDbx_Perfil() {
        return dbx_Perfil;
    }

    public void setDbx_Perfil(String dbx_Perfil) {
        this.dbx_Perfil = dbx_Perfil;
    }

    public String getDbx_TipoCargo() {
        return dbx_TipoCargo;
    }

    public void setDbx_TipoCargo(String dbx_TipoCargo) {
        this.dbx_TipoCargo = dbx_TipoCargo;
    }

    public String getDbx_TipoOrg() {
        return dbx_TipoOrg;
    }

    public void setDbx_TipoOrg(String dbx_TipoOrg) {
        this.dbx_TipoOrg = dbx_TipoOrg;
    }

    public String getDbx_EntidadFiltro() {
        return dbx_EntidadFiltro;
    }

    public void setDbx_EntidadFiltro(String dbx_EntidadFiltro) {
        this.dbx_EntidadFiltro = dbx_EntidadFiltro;
    }
}