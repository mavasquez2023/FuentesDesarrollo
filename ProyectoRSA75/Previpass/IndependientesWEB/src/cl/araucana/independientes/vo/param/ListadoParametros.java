package cl.araucana.independientes.vo.param;

import cl.araucana.independientes.dao.ParametrosDAO;
import cl.araucana.independientes.vo.AgrupacionVO;

/*	Clase: ListadoParametros.
 * 	Contiene arreglos de Parametros que serán usados en la aplicación.
 * */
public class ListadoParametros {

    private static ListadoParametros LISTA_UNICA = null;

    public static void setLISTA_UNICA(ListadoParametros lista_unica) {
        LISTA_UNICA = lista_unica;
    }

    /* Listados de Formulario*/
    private Parametro[] listAfp;
    private Parametro[] listDocumento;
    private Parametro[] listEstadoCivil;
    private Parametro[] listEstadoSolicitudAfiliacion;
    private Parametro[] listEstadoSolicitudDesafiliacion;
    private Parametro[] listEstadoAfiliado;
    private Parametro[] listGiroComercial;
    private Parametro[] listLocalidad;
    private Parametro[] listNacionalidad;
    private Parametro[] listNivelEducacional;
    private Parametro[] listPersona;
    private Parametro[] listProfesion;
    private Parametro[] listRegimenSalud;
    private Parametro[] listSexo;
    private Parametro[] listSolicitud;
    private Parametro[] listEstadoDocumento;
    private Parametro[] listTipoDocumentosSol;
    private Documento[] listTipoDocumentosFull;
    private Documento[] listTipoDocumentosDesafiliacion;
    private Parametro[] listEstadoAporte;
    private Parametro[] listFormaPagoAporte;
    private Parametro[] listTipoOrg;
    private Parametro[] listTipoCargo;

    private Parametro[] listRegion;
    private Parametro[] listProvincia;
    private Parametro[] listComuna;


    private Parametro[] listMailToNominaSolicitudes;
    //REQ5348
    private AgrupacionVO[] listAgrupacionFull;
    private String 		txtValorACotizar;
    //FIN REQ5348

    private Parametro[] listCodPareoCajas;
    private Parametro[] listTxtPareoCajas;

    private Beneficio[]	listBeneficioFull;
    private Parametro[] listTipoPagoBeneficio;
    private Parametro[] listEstadoBeneficio;

    private String 		txtSelTipoCalculoAporte;
    private String		txtValorCalculoAporte;

    /* Listados de La Araucana*/
    private Parametro[] listCaja;
    private Parametro[] listOficina;

    /* Listados Usados en desafiliacion*/
    private Parametro[] listMotivo;

    /* Litados de los tipos de Mantenedores*/
    private Parametro[] listMantTabGlob;

    /* Litados de Parametros usados en los mantenedores*/
    private Parametro[] listEntidades;
    private Parametro[] listEstados;
    private Parametro[] listPerfiles;
    private Parametro[] listGlosaDocBeneficio;
    private Parametro[] listGlosaBeneficio;
    private Parametro[] listGlosaTipoSol;

    private Parametro[] listGlosaCortaDocBeneficio;
    private Parametro[] listGlosaCortaBeneficio;

    private Parametro[] listTipoPagoAporte;

    /* Listados Generales*/
    private Parametro[] flagGeneral;
    private Parametro[] perfilamiento;
    private Parametro[] usuarios;

    private String servletContextRealPath; // No esta en el constructor	

    /*Listado para intercaja*/
    private Parametro[] listMailToSalidaIntercaja;
    private Parametro[] listTipoArchivo;
    private Parametro[] listStatusProceso;
    private Parametro[] listParametrosConexionCobol;
    private Parametro[] listVigenciaMinima;
    private Parametro[] listParametrosConexionFTPIntercaja;
    private Parametro[] listRangoIntercaja;

    private ListadoParametros(){

        this.listDocumento = ParametrosDAO.obtenerParametroList("1");
        this.listSexo = ParametrosDAO.obtenerParametroList("2");
        this.listPersona = ParametrosDAO.obtenerParametroList("3");
        this.listNacionalidad = ParametrosDAO.obtenerParametroList("4");
        this.listEstadoCivil = ParametrosDAO.obtenerParametroList("5");
        this.listEstadoAfiliado = ParametrosDAO.obtenerParametroList("6");
        this.listGiroComercial = ParametrosDAO.obtenerParametroList("7");
        this.listRegimenSalud = ParametrosDAO.obtenerParametroList("8");
        this.listAfp = ParametrosDAO.obtenerParametroList("9");
        this.listLocalidad = ParametrosDAO.obtenerParametroList("10");
        this.listEstadoSolicitudAfiliacion = ParametrosDAO.obtenerParametroList("11");
        this.listSolicitud = ParametrosDAO.obtenerParametroList("12");
        this.listEstadoSolicitudDesafiliacion = ParametrosDAO.obtenerParametroList("13");
        this.listNivelEducacional = ParametrosDAO.obtenerParametroList("14");
        this.listProfesion = ParametrosDAO.obtenerParametroList("15");
        this.listEstadoAporte = ParametrosDAO.obtenerParametroList("20");
        this.listFormaPagoAporte = ParametrosDAO.obtenerParametroList("24");
        this.listEstadoDocumento = ParametrosDAO.obtenerParametroList("22");
        this.listTipoDocumentosSol = ParametrosDAO.obtenerTipoDocumentoList();
        this.listTipoDocumentosFull = ParametrosDAO.obtenerTipoDocumentoListFull();
        this.listTipoDocumentosDesafiliacion = ParametrosDAO.obtenerTipoDocumentoListDesafiliacion();
        this.listMailToNominaSolicitudes = ParametrosDAO.obtenerParametroList("25");
        this.listTipoPagoAporte = ParametrosDAO.obtenerParametroList("29");

        this.txtSelTipoCalculoAporte = ParametrosDAO.obtenerParametroUnico("30");
        this.txtValorCalculoAporte = ParametrosDAO.obtenerParametroUnico("31");
        this.listTipoPagoBeneficio = ParametrosDAO.obtenerParametroList("61");
        this.listEstadoBeneficio = ParametrosDAO.obtenerParametroList("64");

        this.listCodPareoCajas = ParametrosDAO.obtenerParametroList("40");
        this.listMailToSalidaIntercaja = ParametrosDAO.obtenerParametroList("41");
        this.listTxtPareoCajas = ParametrosDAO.obtenerParametroList("42");
        this.listTipoArchivo = ParametrosDAO.obtenerParametroList("43");
        this.listStatusProceso = ParametrosDAO.obtenerParametroList("44");
        this.listParametrosConexionCobol = ParametrosDAO.obtenerParametroList("52");
        this.listVigenciaMinima = ParametrosDAO.obtenerParametroList("65");
        this.listParametrosConexionFTPIntercaja = ParametrosDAO.obtenerParametroList("66");
        this.listRangoIntercaja = ParametrosDAO.obtenerParametroList("68");

        this.listCaja = ParametrosDAO.obtenerCajas();
        this.listOficina = ParametrosDAO.obtenerOficinas();
        this.listMantTabGlob = ParametrosDAO.obtenerMantenedores();
        this.listEntidades = ParametrosDAO.obtenerEntidades();
        this.listEstados = ParametrosDAO.obtenerParametroList("21");
        this.listPerfiles = ParametrosDAO.obtenerParametroList("17");
        this.listGlosaDocBeneficio= ParametrosDAO.obtenerGlosaDocBeneficio();
        this.listGlosaBeneficio= ParametrosDAO.obtenerGlosaBeneficio();
        this.listGlosaTipoSol = ParametrosDAO.obtenerParametroList("12");
        this.listGlosaCortaDocBeneficio= ParametrosDAO.obtenerGlosaCortaDocBeneficio();
        this.listGlosaCortaBeneficio= ParametrosDAO.obtenerGlosaCortaBeneficio();
        this.listTipoOrg= ParametrosDAO.obtenerParametroList("50");
        this.listTipoCargo= ParametrosDAO.obtenerParametroList("51");

        // PARA DESAFILIACION
        //this.listMotivo = ParametrosDAO.obtenerMotivo();

        this.flagGeneral = null;
        this.perfilamiento = null;
        this.usuarios = null;
        //REQ5348
        this.listAgrupacionFull=ParametrosDAO.obtenerAgrupacion();
        //FIN REQ5348

        this.listBeneficioFull = ParametrosDAO.obtenerBeneficioListFull(this.listTipoPagoBeneficio);

        this.servletContextRealPath = "";
    }

    /*Inicializador*/
    public synchronized static ListadoParametros getInstancia()
    {
        if (LISTA_UNICA == null){ 
            LISTA_UNICA = new ListadoParametros();
        }	
        return LISTA_UNICA;
    }

    /*Creación de Getting and Setting de clase ListadoParametros*/
    public Parametro[] getListRangoIntercaja() {
        return listRangoIntercaja;
    }

    public void setListRangoIntercaja(Parametro[] listRangoIntercaja) {
        this.listRangoIntercaja = listRangoIntercaja;
    }

    public Parametro[] getListParametrosConexionCobol() {
        return listParametrosConexionCobol;
    }

    public void setListParametrosConexionCobol(
            Parametro[] listParametrosConexionCobol) {
        this.listParametrosConexionCobol = listParametrosConexionCobol;
    }

    public Parametro[] getFlagGeneral() {
        return flagGeneral;
    }

    public void setFlagGeneral(Parametro[] flagGeneral) {
        this.flagGeneral = flagGeneral;
    }

    public Parametro[] getListAfp() {
        return listAfp;
    }

    public void setListAfp(Parametro[] listAfp) {
        this.listAfp = listAfp;
    }

    public AgrupacionVO[] getListAgrupacionFull() {
        return listAgrupacionFull;
    }

    public void setListAgrupacionFull(AgrupacionVO[] listAgrupacionFull) {
        this.listAgrupacionFull = listAgrupacionFull;
    }

    public Beneficio[] getListBeneficioFull() {
        return listBeneficioFull;
    }

    public void setListBeneficioFull(Beneficio[] listBeneficioFull) {
        this.listBeneficioFull = listBeneficioFull;
    }

    public Parametro[] getListCaja() {
        return listCaja;
    }

    public void setListCaja(Parametro[] listCaja) {
        this.listCaja = listCaja;
    }

    public Parametro[] getListDocumento() {
        return listDocumento;
    }

    public void setListDocumento(Parametro[] listDocumento) {
        this.listDocumento = listDocumento;
    }

    public Parametro[] getListEstadoAfiliado() {
        return listEstadoAfiliado;
    }

    public void setListEstadoAfiliado(Parametro[] listEstadoAfiliado) {
        this.listEstadoAfiliado = listEstadoAfiliado;
    }

    public Parametro[] getListEstadoCivil() {
        return listEstadoCivil;
    }

    public void setListEstadoCivil(Parametro[] listEstadoCivil) {
        this.listEstadoCivil = listEstadoCivil;
    }

    public Parametro[] getListEstadoDocumento() {
        return listEstadoDocumento;
    }

    public void setListEstadoDocumento(Parametro[] listEstadoDocumento) {
        this.listEstadoDocumento = listEstadoDocumento;
    }

    public Parametro[] getListEstadoSolicitudAfiliacion() {
        return listEstadoSolicitudAfiliacion;
    }

    public void setListEstadoSolicitudAfiliacion(
            Parametro[] listEstadoSolicitudAfiliacion) {
        this.listEstadoSolicitudAfiliacion = listEstadoSolicitudAfiliacion;
    }

    public Parametro[] getListEstadoSolicitudDesafiliacion() {
        return listEstadoSolicitudDesafiliacion;
    }

    public void setListEstadoSolicitudDesafiliacion(
            Parametro[] listEstadoSolicitudDesafiliacion) {
        this.listEstadoSolicitudDesafiliacion = listEstadoSolicitudDesafiliacion;
    }

    public Parametro[] getListGiroComercial() {
        return listGiroComercial;
    }

    public void setListGiroComercial(Parametro[] listGiroComercial) {
        this.listGiroComercial = listGiroComercial;
    }

    public Parametro[] getListLocalidad() {
        return listLocalidad;
    }

    public void setListLocalidad(Parametro[] listLocalidad) {
        this.listLocalidad = listLocalidad;
    }

    public Parametro[] getListMailToNominaSolicitudes() {
        return listMailToNominaSolicitudes;
    }

    public void setListMailToNominaSolicitudes(
            Parametro[] listMailToNominaSolicitudes) {
        this.listMailToNominaSolicitudes = listMailToNominaSolicitudes;
    }

    public Parametro[] getListMailToSalidaIntercaja() {
        return listMailToSalidaIntercaja;
    }

    public void setListMailToSalidaIntercaja(Parametro[] listMailToSalidaIntercaja) {
        this.listMailToSalidaIntercaja = listMailToSalidaIntercaja;
    }

    public Parametro[] getListNacionalidad() {
        return listNacionalidad;
    }

    public void setListNacionalidad(Parametro[] listNacionalidad) {
        this.listNacionalidad = listNacionalidad;
    }

    public Parametro[] getListNivelEducacional() {
        return listNivelEducacional;
    }

    public void setListNivelEducacional(Parametro[] listNivelEducacional) {
        this.listNivelEducacional = listNivelEducacional;
    }
    /* OFICINA */
    public Parametro[] getListOficina() {
        return listOficina;
    }

    public void setListOficina(Parametro[] listOficina) {
        this.listOficina = listOficina;
    }

    public Parametro[] getListMantTabGlob() {
        return listMantTabGlob;
    }

    public void setListMantTabGlob(Parametro[] listMantTabGlob) {
        this.listMantTabGlob = listMantTabGlob;
    }

    public Parametro[] getListEntidades() {
        return listEntidades;
    }

    public void setListEntidades(Parametro[] listEntidades) {
        this.listEntidades = listEntidades;
    }

    public Parametro[] getListEstados() {
        return listEstados;
    }

    public void setListEstados(Parametro[] listEstados) {
        this.listEstados = listEstados;
    }

    public Parametro[] getListPersona() {
        return listPersona;
    }

    public void setListPersona(Parametro[] listPersona) {
        this.listPersona = listPersona;
    }

    public Parametro[] getListProfesion() {
        return listProfesion;
    }

    public void setListProfesion(Parametro[] listProfesion) {
        this.listProfesion = listProfesion;
    }

    public Parametro[] getListRegimenSalud() {
        return listRegimenSalud;
    }

    public void setListRegimenSalud(Parametro[] listRegimenSalud) {
        this.listRegimenSalud = listRegimenSalud;
    }

    public Parametro[] getListSexo() {
        return listSexo;
    }

    public void setListSexo(Parametro[] listSexo) {
        this.listSexo = listSexo;
    }

    public Parametro[] getListSolicitud() {
        return listSolicitud;
    }

    public void setListSolicitud(Parametro[] listSolicitud) {
        this.listSolicitud = listSolicitud;
    }

    public Documento[] getListTipoDocumentosFull() {
        return listTipoDocumentosFull;
    }

    public void setListTipoDocumentosFull(Documento[] listTipoDocumentosFull) {
        this.listTipoDocumentosFull = listTipoDocumentosFull;
    }

    public Parametro[] getListTipoDocumentosSol() {
        return listTipoDocumentosSol;
    }

    public void setListTipoDocumentosSol(Parametro[] listTipoDocumentosSol) {
        this.listTipoDocumentosSol = listTipoDocumentosSol;
    }

    public Parametro[] getPerfilamiento() {
        return perfilamiento;
    }

    public void setPerfilamiento(Parametro[] perfilamiento) {
        this.perfilamiento = perfilamiento;
    }

    public String getTxtSelTipoCalculoAporte() {
        return txtSelTipoCalculoAporte;
    }

    public void setTxtSelTipoCalculoAporte(String txtSelTipoCalculoAporte) {
        this.txtSelTipoCalculoAporte = txtSelTipoCalculoAporte;
    }

    public String getTxtValorCalculoAporte() {
        return txtValorCalculoAporte;
    }

    public void setTxtValorCalculoAporte(String txtValorCalculoAporte) {
        this.txtValorCalculoAporte = txtValorCalculoAporte;
    }

    public Parametro[] getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Parametro[] usuarios) {
        this.usuarios = usuarios;
    }

    public Parametro[] getListTipoArchivo() {
        return listTipoArchivo;
    }

    public void setListTipoArchivo(Parametro[] listTipoArchivo) {
        this.listTipoArchivo = listTipoArchivo;
    }

    public Parametro[] getListStatusProceso() {
        return listStatusProceso;
    }

    public void setListStatusProceso(Parametro[] listStatusProceso) {
        this.listStatusProceso = listStatusProceso;
    }

    public Parametro[] getListEstadoAporte() {
        return listEstadoAporte;
    }

    public void setListEstadoAporte(Parametro[] listEstadoAporte) {
        this.listEstadoAporte = listEstadoAporte;
    }

    public Parametro[] getListFormaPagoAporte() {
        return listFormaPagoAporte;
    }

    public void setListFormaPagoAporte(Parametro[] listFormaPagoAporte) {
        this.listFormaPagoAporte = listFormaPagoAporte;
    }

    public String getServletContextRealPath() {
        return servletContextRealPath;
    }

    public void setServletContextRealPath(String servletContextRealPath) {
        this.servletContextRealPath = servletContextRealPath;
    }

    public String getTxtValorACotizar() {
        return txtValorACotizar;
    }

    public void setTxtValorACotizar(String txtValorACotizar) {
        this.txtValorACotizar = txtValorACotizar;
    }

    public Parametro[] getListVigenciaMinima() {
        return listVigenciaMinima;
    }

    public void setListVigenciaMinima(Parametro[] listVigenciaMinima) {
        this.listVigenciaMinima = listVigenciaMinima;
    }

    public Parametro[] getListParametrosConexionFTPIntercaja() {
        return listParametrosConexionFTPIntercaja;
    }

    public void setListParametrosConexionFTPIntercaja(
            Parametro[] listParametrosConexionFTPIntercaja) {
        this.listParametrosConexionFTPIntercaja = listParametrosConexionFTPIntercaja;
    }
    public Parametro[] getListPerfiles() {
        return listPerfiles;
    }

    public void setListPerfiles(Parametro[] listPerfiles) {
        this.listPerfiles = listPerfiles;
    }

    public Parametro[] getListGlosaDocBeneficio() {
        return listGlosaDocBeneficio;
    }

    public void setListGlosaDocBeneficio(Parametro[] listGlosaDocBeneficio) {
        this.listGlosaDocBeneficio = listGlosaDocBeneficio;
    }

    public Parametro[] getListGlosaBeneficio() {
        return listGlosaBeneficio;
    }

    public void setListGlosaBeneficio(Parametro[] listGlosaBeneficio) {
        this.listGlosaBeneficio = listGlosaBeneficio;
    }

    public Parametro[] getListTipoPagoAporte() {
        return listTipoPagoAporte;
    }

    public void setListTipoPagoAporte(Parametro[] listTipoPagoAporte) {
        this.listTipoPagoAporte = listTipoPagoAporte;
    }

    public Parametro[] getListTipoCargo() {
        return listTipoCargo;
    }

    public void setListTipoCargo(Parametro[] listTipoCargo) {
        this.listTipoCargo = listTipoCargo;
    }

    public Parametro[] getListTipoOrg() {
        return listTipoOrg;
    }

    public void setListTipoOrg(Parametro[] listTipoOrg) {
        this.listTipoOrg = listTipoOrg;
    }

    public Parametro[] getListGlosaCortaBeneficio() {
        return listGlosaCortaBeneficio;
    }

    public void setListGlosaCortaBeneficio(Parametro[] listGlosaCortaBeneficio) {
        this.listGlosaCortaBeneficio = listGlosaCortaBeneficio;
    }

    public Parametro[] getListGlosaCortaDocBeneficio() {
        return listGlosaCortaDocBeneficio;
    }

    public void setListGlosaCortaDocBeneficio(Parametro[] listGlosaCortaDocBeneficio) {
        this.listGlosaCortaDocBeneficio = listGlosaCortaDocBeneficio;
    }
    public Parametro[] getListEstadoBeneficio() {
        return listEstadoBeneficio;
    }

    public void setListEstadoBeneficio(Parametro[] listEstadoBeneficio) {
        this.listEstadoBeneficio = listEstadoBeneficio;
    }

    public Parametro[] getListTipoPagoBeneficio() {
        return listTipoPagoBeneficio;
    }

    public void setListTipoPagoBeneficio(Parametro[] listTipoPagoBeneficio) {
        this.listTipoPagoBeneficio = listTipoPagoBeneficio;
    }

    public Parametro[] getListCodPareoCajas() {
        return listCodPareoCajas;
    }

    public void setListCodPareoCajas(Parametro[] listCodPareoCajas) {
        this.listCodPareoCajas = listCodPareoCajas;
    }

    public Parametro[] getListTxtPareoCajas() {
        return listTxtPareoCajas;
    }

    public void setListTxtPareoCajas(Parametro[] listTxtPareoCajas) {
        this.listTxtPareoCajas = listTxtPareoCajas;
    }

    public Parametro[] getListComuna() {
        return listComuna;
    }

    public void setListComuna(Parametro[] listComuna) {
        this.listComuna = listComuna;
    }

    public Parametro[] getListProvincia() {
        return listProvincia;
    }

    public void setListProvincia(Parametro[] listProvincia) {
        this.listProvincia = listProvincia;
    }

    public Parametro[] getListRegion() {
        return listRegion;
    }

    public void setListRegion(Parametro[] listRegion) {
        this.listRegion = listRegion;
    }

    public Parametro[] getListMotivo() {
        return listMotivo;
    }

    public void setListMotivo(Parametro[] listMotivo) {
        this.listMotivo = listMotivo;
    }

    public Documento[] getListTipoDocumentosDesafiliacion() {
        return listTipoDocumentosDesafiliacion;
    }

    public void setListTipoDocumentosDesafiliacion(
            Documento[] listTipoDocumentosDesafiliacion) {
        this.listTipoDocumentosDesafiliacion = listTipoDocumentosDesafiliacion;
    }

    public Parametro[] getListGlosaTipoSol() {
        return listGlosaTipoSol;
    }

    public void setListGlosaTipoSol(Parametro[] listGlosaTipoSol) {
        this.listGlosaTipoSol = listGlosaTipoSol;
    }

}
