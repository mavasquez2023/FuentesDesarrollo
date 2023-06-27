package cl.araucana.independientes.struts.Forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.*;

/* Clase ModSolicitudForm
 * contiene todas las variables del formulario de modificación de solicitud que interactuan con el sistema
 * Se pide no modificar la numeración de las variables, de modificarlas se pide reordenar dicha numeración
 * y modificar la cadena en el formulario correspondiente. (jsp).*/
public class ModSolicitudForm extends ActionForm {

    private static final long serialVersionUID = 1L;

    /*Declaracion de las variables de la clase ModSolicitudForm.*/	
    private String opcion;//01
    private String resultado;//02
    private String dbx_EstSolicitud;//03
    private String txt_EstSolicitud;//campo de texto informativo, no se inserta ni se consulta
    private String existeCambio;//campo para ver cambios, no se inserta ni se consulta

    //-1. Encabezado de Busqueda
    private String txt_NFolio;//04
    private String txt_NRut;//05
    private String txt_NNumVerif;//06

    //0. Informacion del Formulario
    private String txt_Folio;//07

    //1. Informacion de Identificación del formulario de modificación de solicitud.
    private String txt_Rut;//08
    private String txt_NumVerif;//09
    private String txt_ApePat;//10
    private String txt_ApeMat;//11
    private String txt_Nombres;//12
    private String dbx_Nacionalidad;//13
    private String txt_FecNac;//14
    private String dbx_Sexo;//15
    private String dbx_EstCivil;//16
    private String txt_codAreaCelular;//17
    private String txt_TelCelular;//18
    private String txt_codAreaContacto;//19
    private String txt_TelContacto;//20
    private String txt_Email;//21
    private String txt_Calle;//22
    private String txt_Numero;//23
    private String txt_PoblVilla;//24
    private String txt_Departamento;//25
    private String dbx_Region;//26
    private String dbx_Provincia;//27
    private String dbx_Comuna;//28
    private String dbx_NivEstudios;//29
    private String dbx_TitAcademico;//30
    private String rbt_Agrupacion;//31
    private String dbx_RazonSocialAgrup;//32
    private String txt_RutAgrup;//33
    private String txt_NumVerifAgrup;//34
    private String dbx_TipoAgrup;//35
    private String dbx_RegPrevisional;//36
    private String dbx_RegSalud;//37
    private String rbt_Conyugue;//38
    private String txt_Hijos;//39

    //2. Informacion Actividad Comercial del formulario de modificación de solicitud.
    private String txt_Actividad;//40
    private String rbt_Honorarios;//41
    private String txt_CalleComerc;//42
    private String txt_NumeroComerc;//43
    private String txt_PoblVillaComerc;//44
    private String txt_DptoComerc;//45
    private String txt_codAreaComerc;//46
    private String txt_TelComerc;//47
    private String dbx_RegComerc;//48
    private String dbx_ProvinciaComerc;//49
    private String dbx_ComunaComerc;//50

    //3. Informacion de Renta del formulario de modificación de solicitud
    private String txt_RentaImp;//51
    private String txt_RentaCot;//52
    private String txt_ValorACot;//53

    //4. Información de Afiliación a CCAF La Araucana y Desafiliación de Otra Caja de Compensación
    private String txt_CajaCompens;//54
    private String txt_FecVigAfil;//55

    //6. Antecedentes Proceso de Afiliación.
    private String txt_RutEjec;//56
    private String txt_ApePatEjec;//57
    private String txt_ApeMatEjec;//58
    private String txt_NombreEjec;//59
    private String txt_Sucursal;//60
    private String txt_FecIngr;//61
    private String txt_FecFirma;//62

    //7. Campos Ocultos (ID)
    private String idPersona;//63
    private String idSolicitud;//64
    private String idAfiliadoAgrupacion;//65
    private String idGrupoFam;//66
    private String idIngEconom;//67
    private String idSecuenciaTelefonoPart;//68
    private String idSecuenciaTelefonoCelu;//69
    private String idSecuenciaEmail;//70
    private String idSecuenciaDireccionPart;//71
    private String idSecuenciaDireccionComerc;//72
    private String idSecuenciaTelefonoComerc;//73

    //5. Campos del Mantenedor de Documentos
    private String lbl_DocFolio;
    private String lbl_DocRUT;
    private String lbl_DocNombre;

    private String txt_ObservacionesDoc;
    private String txt_EmailComercial;	
    private String idSecuenciaEmailComercial;		

    private String txt_codOficina;

    /*Generacion de Getting and Setting de la clase.*/

    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        ActionErrors errores = new ActionErrors();

        return errores;
    }

    public String getDbx_Comuna() {
        return dbx_Comuna;
    }

    public void setDbx_Comuna(String dbx_Comuna) {
        this.dbx_Comuna = dbx_Comuna;
    }

    public String getDbx_ComunaComerc() {
        return dbx_ComunaComerc;
    }

    public void setDbx_ComunaComerc(String dbx_ComunaComerc) {
        this.dbx_ComunaComerc = dbx_ComunaComerc;
    }

    public String getDbx_EstCivil() {
        return dbx_EstCivil;
    }

    public void setDbx_EstCivil(String dbx_EstCivil) {
        this.dbx_EstCivil = dbx_EstCivil;
    }

    public String getDbx_EstSolicitud() {
        return dbx_EstSolicitud;
    }

    public void setDbx_EstSolicitud(String dbx_EstSolicitud) {
        this.dbx_EstSolicitud = dbx_EstSolicitud;
    }

    public String getDbx_Nacionalidad() {
        return dbx_Nacionalidad;
    }

    public void setDbx_Nacionalidad(String dbx_Nacionalidad) {
        this.dbx_Nacionalidad = dbx_Nacionalidad;
    }

    public String getDbx_NivEstudios() {
        return dbx_NivEstudios;
    }

    public void setDbx_NivEstudios(String dbx_NivEstudios) {
        this.dbx_NivEstudios = dbx_NivEstudios;
    }

    public String getDbx_Provincia() {
        return dbx_Provincia;
    }

    public void setDbx_Provincia(String dbx_Provincia) {
        this.dbx_Provincia = dbx_Provincia;
    }

    public String getDbx_ProvinciaComerc() {
        return dbx_ProvinciaComerc;
    }

    public void setDbx_ProvinciaComerc(String dbx_ProvinciaComerc) {
        this.dbx_ProvinciaComerc = dbx_ProvinciaComerc;
    }

    public String getDbx_RazonSocialAgrup() {
        return dbx_RazonSocialAgrup;
    }

    public void setDbx_RazonSocialAgrup(String dbx_RazonSocialAgrup) {
        this.dbx_RazonSocialAgrup = dbx_RazonSocialAgrup;
    }

    public String getDbx_RegComerc() {
        return dbx_RegComerc;
    }

    public void setDbx_RegComerc(String dbx_RegComerc) {
        this.dbx_RegComerc = dbx_RegComerc;
    }

    public String getDbx_Region() {
        return dbx_Region;
    }

    public void setDbx_Region(String dbx_Region) {
        this.dbx_Region = dbx_Region;
    }

    public String getDbx_RegPrevisional() {
        return dbx_RegPrevisional;
    }

    public void setDbx_RegPrevisional(String dbx_RegPrevisional) {
        this.dbx_RegPrevisional = dbx_RegPrevisional;
    }

    public String getDbx_RegSalud() {
        return dbx_RegSalud;
    }

    public void setDbx_RegSalud(String dbx_RegSalud) {
        this.dbx_RegSalud = dbx_RegSalud;
    }

    public String getDbx_Sexo() {
        return dbx_Sexo;
    }

    public void setDbx_Sexo(String dbx_Sexo) {
        this.dbx_Sexo = dbx_Sexo;
    }

    public String getDbx_TipoAgrup() {
        return dbx_TipoAgrup;
    }

    public void setDbx_TipoAgrup(String dbx_TipoAgrup) {
        this.dbx_TipoAgrup = dbx_TipoAgrup;
    }

    public String getDbx_TitAcademico() {
        return dbx_TitAcademico;
    }

    public void setDbx_TitAcademico(String dbx_TitAcademico) {
        this.dbx_TitAcademico = dbx_TitAcademico;
    }

    public String getExisteCambio() {
        return existeCambio;
    }

    public void setExisteCambio(String existeCambio) {
        this.existeCambio = existeCambio;
    }

    public String getIdAfiliadoAgrupacion() {
        return idAfiliadoAgrupacion;
    }

    public void setIdAfiliadoAgrupacion(String idAfiliadoAgrupacion) {
        this.idAfiliadoAgrupacion = idAfiliadoAgrupacion;
    }

    public String getIdGrupoFam() {
        return idGrupoFam;
    }

    public void setIdGrupoFam(String idGrupoFam) {
        this.idGrupoFam = idGrupoFam;
    }

    public String getIdIngEconom() {
        return idIngEconom;
    }

    public void setIdIngEconom(String idIngEconom) {
        this.idIngEconom = idIngEconom;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getIdSecuenciaDireccionComerc() {
        return idSecuenciaDireccionComerc;
    }

    public void setIdSecuenciaDireccionComerc(String idSecuenciaDireccionComerc) {
        this.idSecuenciaDireccionComerc = idSecuenciaDireccionComerc;
    }

    public String getIdSecuenciaDireccionPart() {
        return idSecuenciaDireccionPart;
    }

    public void setIdSecuenciaDireccionPart(String idSecuenciaDireccionPart) {
        this.idSecuenciaDireccionPart = idSecuenciaDireccionPart;
    }

    public String getIdSecuenciaEmail() {
        return idSecuenciaEmail;
    }

    public void setIdSecuenciaEmail(String idSecuenciaEmail) {
        this.idSecuenciaEmail = idSecuenciaEmail;
    }

    public String getIdSecuenciaEmailComercial() {
        return idSecuenciaEmailComercial;
    }

    public void setIdSecuenciaEmailComercial(String idSecuenciaEmailComercial) {
        this.idSecuenciaEmailComercial = idSecuenciaEmailComercial;
    }

    public String getIdSecuenciaTelefonoCelu() {
        return idSecuenciaTelefonoCelu;
    }

    public void setIdSecuenciaTelefonoCelu(String idSecuenciaTelefonoCelu) {
        this.idSecuenciaTelefonoCelu = idSecuenciaTelefonoCelu;
    }

    public String getIdSecuenciaTelefonoComerc() {
        return idSecuenciaTelefonoComerc;
    }

    public void setIdSecuenciaTelefonoComerc(String idSecuenciaTelefonoComerc) {
        this.idSecuenciaTelefonoComerc = idSecuenciaTelefonoComerc;
    }

    public String getIdSecuenciaTelefonoPart() {
        return idSecuenciaTelefonoPart;
    }

    public void setIdSecuenciaTelefonoPart(String idSecuenciaTelefonoPart) {
        this.idSecuenciaTelefonoPart = idSecuenciaTelefonoPart;
    }

    public String getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(String idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getLbl_DocFolio() {
        return lbl_DocFolio;
    }

    public void setLbl_DocFolio(String lbl_DocFolio) {
        this.lbl_DocFolio = lbl_DocFolio;
    }

    public String getLbl_DocNombre() {
        return lbl_DocNombre;
    }

    public void setLbl_DocNombre(String lbl_DocNombre) {
        this.lbl_DocNombre = lbl_DocNombre;
    }

    public String getLbl_DocRUT() {
        return lbl_DocRUT;
    }

    public void setLbl_DocRUT(String lbl_DocRUT) {
        this.lbl_DocRUT = lbl_DocRUT;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public String getRbt_Agrupacion() {
        return rbt_Agrupacion;
    }

    public void setRbt_Agrupacion(String rbt_Agrupacion) {
        this.rbt_Agrupacion = rbt_Agrupacion;
    }

    public String getRbt_Conyugue() {
        return rbt_Conyugue;
    }

    public void setRbt_Conyugue(String rbt_Conyugue) {
        this.rbt_Conyugue = rbt_Conyugue;
    }

    public String getRbt_Honorarios() {
        return rbt_Honorarios;
    }

    public void setRbt_Honorarios(String rbt_Honorarios) {
        this.rbt_Honorarios = rbt_Honorarios;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getTxt_Actividad() {
        return txt_Actividad;
    }

    public void setTxt_Actividad(String txt_Actividad) {
        this.txt_Actividad = txt_Actividad;
    }

    public String getTxt_ApeMat() {
        return txt_ApeMat;
    }

    public void setTxt_ApeMat(String txt_ApeMat) {
        this.txt_ApeMat = txt_ApeMat;
    }

    public String getTxt_ApeMatEjec() {
        return txt_ApeMatEjec;
    }

    public void setTxt_ApeMatEjec(String txt_ApeMatEjec) {
        this.txt_ApeMatEjec = txt_ApeMatEjec;
    }

    public String getTxt_ApePat() {
        return txt_ApePat;
    }

    public void setTxt_ApePat(String txt_ApePat) {
        this.txt_ApePat = txt_ApePat;
    }

    public String getTxt_ApePatEjec() {
        return txt_ApePatEjec;
    }

    public void setTxt_ApePatEjec(String txt_ApePatEjec) {
        this.txt_ApePatEjec = txt_ApePatEjec;
    }

    public String getTxt_CajaCompens() {
        return txt_CajaCompens;
    }

    public void setTxt_CajaCompens(String txt_CajaCompens) {
        this.txt_CajaCompens = txt_CajaCompens;
    }

    public String getTxt_Calle() {
        return txt_Calle;
    }

    public void setTxt_Calle(String txt_Calle) {
        this.txt_Calle = txt_Calle;
    }

    public String getTxt_CalleComerc() {
        return txt_CalleComerc;
    }

    public void setTxt_CalleComerc(String txt_CalleComerc) {
        this.txt_CalleComerc = txt_CalleComerc;
    }

    public String getTxt_codAreaCelular() {
        return txt_codAreaCelular;
    }

    public void setTxt_codAreaCelular(String txt_codAreaCelular) {
        this.txt_codAreaCelular = txt_codAreaCelular;
    }

    public String getTxt_codAreaComerc() {
        return txt_codAreaComerc;
    }

    public void setTxt_codAreaComerc(String txt_codAreaComerc) {
        this.txt_codAreaComerc = txt_codAreaComerc;
    }

    public String getTxt_codAreaContacto() {
        return txt_codAreaContacto;
    }

    public void setTxt_codAreaContacto(String txt_codAreaContacto) {
        this.txt_codAreaContacto = txt_codAreaContacto;
    }

    public String getTxt_codOficina() {
        return txt_codOficina;
    }

    public void setTxt_codOficina(String txt_codOficina) {
        this.txt_codOficina = txt_codOficina;
    }

    public String getTxt_Departamento() {
        return txt_Departamento;
    }

    public void setTxt_Departamento(String txt_Departamento) {
        this.txt_Departamento = txt_Departamento;
    }

    public String getTxt_DptoComerc() {
        return txt_DptoComerc;
    }

    public void setTxt_DptoComerc(String txt_DptoComerc) {
        this.txt_DptoComerc = txt_DptoComerc;
    }

    public String getTxt_Email() {
        return txt_Email;
    }

    public void setTxt_Email(String txt_Email) {
        this.txt_Email = txt_Email;
    }

    public String getTxt_EmailComercial() {
        return txt_EmailComercial;
    }

    public void setTxt_EmailComercial(String txt_EmailComercial) {
        this.txt_EmailComercial = txt_EmailComercial;
    }

    public String getTxt_EstSolicitud() {
        return txt_EstSolicitud;
    }

    public void setTxt_EstSolicitud(String txt_EstSolicitud) {
        this.txt_EstSolicitud = txt_EstSolicitud;
    }

    public String getTxt_FecFirma() {
        return txt_FecFirma;
    }

    public void setTxt_FecFirma(String txt_FecFirma) {
        this.txt_FecFirma = txt_FecFirma;
    }

    public String getTxt_FecIngr() {
        return txt_FecIngr;
    }

    public void setTxt_FecIngr(String txt_FecIngr) {
        this.txt_FecIngr = txt_FecIngr;
    }

    public String getTxt_FecNac() {
        return txt_FecNac;
    }

    public void setTxt_FecNac(String txt_FecNac) {
        this.txt_FecNac = txt_FecNac;
    }

    public String getTxt_FecVigAfil() {
        return txt_FecVigAfil;
    }

    public void setTxt_FecVigAfil(String txt_FecVigAfil) {
        this.txt_FecVigAfil = txt_FecVigAfil;
    }

    public String getTxt_Folio() {
        return txt_Folio;
    }

    public void setTxt_Folio(String txt_Folio) {
        this.txt_Folio = txt_Folio;
    }

    public String getTxt_Hijos() {
        return txt_Hijos;
    }

    public void setTxt_Hijos(String txt_Hijos) {
        this.txt_Hijos = txt_Hijos;
    }

    public String getTxt_NFolio() {
        return txt_NFolio;
    }

    public void setTxt_NFolio(String txt_NFolio) {
        this.txt_NFolio = txt_NFolio;
    }

    public String getTxt_NNumVerif() {
        return txt_NNumVerif;
    }

    public void setTxt_NNumVerif(String txt_NNumVerif) {
        this.txt_NNumVerif = txt_NNumVerif;
    }

    public String getTxt_NombreEjec() {
        return txt_NombreEjec;
    }

    public void setTxt_NombreEjec(String txt_NombreEjec) {
        this.txt_NombreEjec = txt_NombreEjec;
    }

    public String getTxt_Nombres() {
        return txt_Nombres;
    }

    public void setTxt_Nombres(String txt_Nombres) {
        this.txt_Nombres = txt_Nombres;
    }

    public String getTxt_NRut() {
        return txt_NRut;
    }

    public void setTxt_NRut(String txt_NRut) {
        this.txt_NRut = txt_NRut;
    }

    public String getTxt_Numero() {
        return txt_Numero;
    }

    public void setTxt_Numero(String txt_Numero) {
        this.txt_Numero = txt_Numero;
    }

    public String getTxt_NumeroComerc() {
        return txt_NumeroComerc;
    }

    public void setTxt_NumeroComerc(String txt_NumeroComerc) {
        this.txt_NumeroComerc = txt_NumeroComerc;
    }

    public String getTxt_NumVerif() {
        return txt_NumVerif;
    }

    public void setTxt_NumVerif(String txt_NumVerif) {
        this.txt_NumVerif = txt_NumVerif;
    }

    public String getTxt_NumVerifAgrup() {
        return txt_NumVerifAgrup;
    }

    public void setTxt_NumVerifAgrup(String txt_NumVerifAgrup) {
        this.txt_NumVerifAgrup = txt_NumVerifAgrup;
    }

    public String getTxt_ObservacionesDoc() {
        return txt_ObservacionesDoc;
    }

    public void setTxt_ObservacionesDoc(String txt_ObservacionesDoc) {
        this.txt_ObservacionesDoc = txt_ObservacionesDoc;
    }

    public String getTxt_PoblVilla() {
        return txt_PoblVilla;
    }

    public void setTxt_PoblVilla(String txt_PoblVilla) {
        this.txt_PoblVilla = txt_PoblVilla;
    }

    public String getTxt_PoblVillaComerc() {
        return txt_PoblVillaComerc;
    }

    public void setTxt_PoblVillaComerc(String txt_PoblVillaComerc) {
        this.txt_PoblVillaComerc = txt_PoblVillaComerc;
    }

    public String getTxt_RentaCot() {
        return txt_RentaCot;
    }

    public void setTxt_RentaCot(String txt_RentaCot) {
        this.txt_RentaCot = txt_RentaCot;
    }

    public String getTxt_RentaImp() {
        return txt_RentaImp;
    }

    public void setTxt_RentaImp(String txt_RentaImp) {
        this.txt_RentaImp = txt_RentaImp;
    }

    public String getTxt_Rut() {
        return txt_Rut;
    }

    public void setTxt_Rut(String txt_Rut) {
        this.txt_Rut = txt_Rut;
    }

    public String getTxt_RutAgrup() {
        return txt_RutAgrup;
    }

    public void setTxt_RutAgrup(String txt_RutAgrup) {
        this.txt_RutAgrup = txt_RutAgrup;
    }

    public String getTxt_RutEjec() {
        return txt_RutEjec;
    }

    public void setTxt_RutEjec(String txt_RutEjec) {
        this.txt_RutEjec = txt_RutEjec;
    }

    public String getTxt_Sucursal() {
        return txt_Sucursal;
    }

    public void setTxt_Sucursal(String txt_Sucursal) {
        this.txt_Sucursal = txt_Sucursal;
    }

    public String getTxt_TelCelular() {
        return txt_TelCelular;
    }

    public void setTxt_TelCelular(String txt_TelCelular) {
        this.txt_TelCelular = txt_TelCelular;
    }

    public String getTxt_TelComerc() {
        return txt_TelComerc;
    }

    public void setTxt_TelComerc(String txt_TelComerc) {
        this.txt_TelComerc = txt_TelComerc;
    }

    public String getTxt_TelContacto() {
        return txt_TelContacto;
    }

    public void setTxt_TelContacto(String txt_TelContacto) {
        this.txt_TelContacto = txt_TelContacto;
    }

    public String getTxt_ValorACot() {
        return txt_ValorACot;
    }

    public void setTxt_ValorACot(String txt_ValorACot) {
        this.txt_ValorACot = txt_ValorACot;
    }
}
