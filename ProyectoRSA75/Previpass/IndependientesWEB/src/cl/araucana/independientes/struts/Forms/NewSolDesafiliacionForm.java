package cl.araucana.independientes.struts.Forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.*;

/* Clase NewSolicitudForm
 * contiene todas las variables del formulario de ingreso de solicitud que interactuan con el sistema
 * */

public class NewSolDesafiliacionForm extends ActionForm {

    private static final long serialVersionUID = 1L;

    /*Declaracion de las variables del formulario de ingreso de solicitud.*/
    private String opcion;
    private String resultado;

    //0. Informacion del Formulario
    private String txt_Oficina;
    private String dbx_Oficina;
    private String txt_Fecha;
    private String txt_Folio;

    //1. Informacion de Identificación del formulario de ingreso de solicitud.
    private String txt_Rut;
    private String txt_NumVerif;
    private String txt_ApePat;
    private String txt_ApeMat;
    private String txt_Nombres;
    private String dbx_Nacionalidad;
    private String txt_FecNac;
    private String dbx_Sexo;
    private String dbx_EstCivil;
    private String txt_codAreaCelular;
    private String txt_TelCelular;
    private String txt_codAreaContacto;
    private String txt_TelContacto;
    private String txt_Email;
    private String txt_Calle;
    private String txt_Numero;
    private String txt_PoblVilla;
    private String txt_Departamento;
    private String dbx_Region;
    private String dbx_Provincia;
    private String dbx_Comuna;
    private String txt_Region;
    private String txt_Provincia;
    private String txt_Comuna;
    private String dbx_NivEstudios;
    private String dbx_TitAcademico;
    private String rbt_Agrupacion; 
    private String dbx_RazonSocialAgrup;
    private String txt_RutAgrup;
    private String txt_NumVerifAgrup;
    private String dbx_TipoAgrup;
    private String dbx_RegPrevisional;
    private String dbx_RegSalud;
    private String rbt_Conyugue; 
    private String txt_Hijos;

    //2. Informacion Actividad Comercial del formulario de ingreso de solicitud.
    private String txt_Actividad;
    private String rbt_Honorarios; 
    private String txt_CalleComerc;
    private String txt_NumeroComerc;
    private String txt_PoblVillaComerc;
    private String txt_DptoComerc;
    private String txt_codAreaComerc;
    private String txt_TelComerc;
    private String dbx_RegComerc;
    private String dbx_ProvinciaComerc;
    private String dbx_ComunaComerc;
    private String txt_EmailComercial;	

    // FLAGS PARA ACTUALIZACION O CREACION
    private String chk_flagCelu;
    private String chk_flagEmail;
    private String chk_flagDireccion;

    //3. Informacion de Renta del formulario de ingreso de solicitud.
    private String txt_RentaImp;
    private String txt_MontoUltimaCotizacion;	
    private String txt_FechaUltimaCotizacion;
    private String txt_RentaCot;
    private String txt_ValorACot;

    //4. Información de Afiliación a CCAF La Araucana y Desafiliación de Otra Caja de Compensación
    private String dbx_CajaCompensacion;
    private String txt_FecVigAfil;
    private String txt_FecUltApo;
    private String dbx_TipoMotivo;
    private String dbx_DesMotivo;
    private String dbx_ObsMotivo;

    //5. Antecedentes Proceso de Afiliación
    private String txt_RutEjec;
    private String txt_ApePatEjec;
    private String txt_ApeMatEjec;
    private String txt_NombreEjec;
    private String txt_Sucursal;
    private String txt_FecIngr;
    private String txt_FecSolDesaf;
    private String txt_Hora;	

    private String txt_FecFirma;

    // Campos Ocultos (ID)
    private String idPersona;//62
    private String idGrupoFam;//65
    private String idIngEconom;//66
    private String idSecuenciaTelefonoPart;//67
    private String idSecuenciaTelefonoCelu;//68
    private String idSecuenciaEmail;//69
    private String idSecuenciaDireccionPart;//70
    private String idSecuenciaDireccionComerc;//71
    private String idSecuenciaTelefonoComerc;//72

    /*Inicializador*/
    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        ActionErrors errores = new ActionErrors();

        return errores;
    }

    /*Generacion de Getting and Setting de la clase.*/	
    public String getDbx_CajaCompensacion() {
        return dbx_CajaCompensacion;
    }

    public void setDbx_CajaCompensacion(String dbx_CajaCompensacion) {
        this.dbx_CajaCompensacion = dbx_CajaCompensacion;
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

    public String getDbx_Oficina() {
        return dbx_Oficina;
    }

    public void setDbx_Oficina(String dbx_Oficina) {
        this.dbx_Oficina = dbx_Oficina;
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

    public String getTxt_FecFirma() {
        return txt_FecFirma;
    }

    public void setTxt_FecFirma(String txt_FecFirma) {
        this.txt_FecFirma = txt_FecFirma;
    }

    public String getTxt_Fecha() {
        return txt_Fecha;
    }

    public void setTxt_Fecha(String txt_Fecha) {
        this.txt_Fecha = txt_Fecha;
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

    public String getDbx_RazonSocialAgrup() {
        return dbx_RazonSocialAgrup;
    }

    public void setDbx_RazonSocialAgrup(String dbx_RazonSocialAgrup) {
        this.dbx_RazonSocialAgrup = dbx_RazonSocialAgrup;
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

    public String getTxt_EmailComercial() {
        return txt_EmailComercial;
    }

    public void setTxt_EmailComercial(String txt_EmailComercial) {
        this.txt_EmailComercial = txt_EmailComercial;
    }

    public String getTxt_MontoUltimaCotizacion() {
        return txt_MontoUltimaCotizacion;
    }

    public void setTxt_MontoUltimaCotizacion(String txt_MontoUltimaCotizacion) {
        this.txt_MontoUltimaCotizacion = txt_MontoUltimaCotizacion;
    }

    public String getTxt_FechaUltimaCotizacion() {
        return txt_FechaUltimaCotizacion;
    }

    public void setTxt_FechaUltimaCotizacion(String txt_FechaUltimaCotizacion) {
        this.txt_FechaUltimaCotizacion = txt_FechaUltimaCotizacion;
    }

    public String getTxt_Hora() {
        return txt_Hora;
    }

    public void setTxt_Hora(String txt_Hora) {
        this.txt_Hora = txt_Hora;
    }

    public String getTxt_Comuna() {
        return txt_Comuna;
    }

    public void setTxt_Comuna(String txt_Comuna) {
        this.txt_Comuna = txt_Comuna;
    }

    public String getTxt_Oficina() {
        return txt_Oficina;
    }

    public void setTxt_Oficina(String txt_Oficina) {
        this.txt_Oficina = txt_Oficina;
    }

    public String getTxt_Provincia() {
        return txt_Provincia;
    }

    public void setTxt_Provincia(String txt_Provincia) {
        this.txt_Provincia = txt_Provincia;
    }

    public String getTxt_Region() {
        return txt_Region;
    }

    public void setTxt_Region(String txt_Region) {
        this.txt_Region = txt_Region;
    }

    public String getDbx_DesMotivo() {
        return dbx_DesMotivo;
    }

    public void setDbx_DesMotivo(String dbx_DesMotivo) {
        this.dbx_DesMotivo = dbx_DesMotivo;
    }

    public String getDbx_ObsMotivo() {
        return dbx_ObsMotivo;
    }

    public void setDbx_ObsMotivo(String dbx_ObsMotivo) {
        this.dbx_ObsMotivo = dbx_ObsMotivo;
    }

    public String getDbx_TipoMotivo() {
        return dbx_TipoMotivo;
    }

    public void setDbx_TipoMotivo(String dbx_TipoMotivo) {
        this.dbx_TipoMotivo = dbx_TipoMotivo;
    }

    public String getTxt_FecUltApo() {
        return txt_FecUltApo;
    }

    public void setTxt_FecUltApo(String txt_FecUltApo) {
        this.txt_FecUltApo = txt_FecUltApo;
    }

    public String getTxt_FecSolDesaf() {
        return txt_FecSolDesaf;
    }

    public void setTxt_FecSolDesaf(String txt_FecSolDesaf) {
        this.txt_FecSolDesaf = txt_FecSolDesaf;
    }

    public String getChk_flagCelu() {
        return chk_flagCelu;
    }

    public void setChk_flagCelu(String chk_flagCelu) {
        this.chk_flagCelu = chk_flagCelu;
    }

    public String getChk_flagDireccion() {
        return chk_flagDireccion;
    }

    public void setChk_flagDireccion(String chk_flagDireccion) {
        this.chk_flagDireccion = chk_flagDireccion;
    }

    public String getChk_flagEmail() {
        return chk_flagEmail;
    }

    public void setChk_flagEmail(String chk_flagEmail) {
        this.chk_flagEmail = chk_flagEmail;
    }

}
