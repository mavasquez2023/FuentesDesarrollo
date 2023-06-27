package cl.araucana.independientes.struts.dwr.actions;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import cl.araucana.independientes.impl.MantenedoresImpl;
import cl.araucana.independientes.vo.MantTabGlobVO;
import cl.araucana.independientes.vo.MantTabPerfVO;
import cl.araucana.independientes.vo.MantTabPromVO;
import cl.araucana.independientes.vo.MantTabTipoDocVO;
import cl.araucana.independientes.vo.MantTabBenefVO;
import cl.araucana.independientes.vo.MantTabDocsBenefsDinVO;
import cl.araucana.independientes.vo.MantTabRelBenefsDinDocsBenefsVO;
import cl.araucana.independientes.vo.RespuestaVO;
import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.helper.IND_Constants;
import cl.araucana.independientes.vo.param.Parametro;

import cl.araucana.independientes.vo.param.ListadoParametros;

public class MantenedoresDWR{

    //	------------------------- CONSULTAS -------------------------//

    public MantTabGlobVO consultaMantTabGlob (){

        MantenedoresImpl impl = new MantenedoresImpl();
        MantTabGlobVO ret = new MantTabGlobVO();

        try{
            ret = impl.consultaMantTabGlob();
            return ret;

        }catch(IOException e){

        }catch(SQLException f){
        }
        return ret;
    }

    public MantTabGlobVO filtraMantTabGlob(String entidad){

        MantenedoresImpl impl = new MantenedoresImpl();
        MantTabGlobVO ret = new MantTabGlobVO();

        try{
            ret = impl.filtraMantTabGlob(entidad);
            return ret;

        }catch(IOException e){

        }catch(SQLException f){
        }
        return ret;
    }

    public MantTabPerfVO consultaMantTabPerf (){

        MantenedoresImpl impl = new MantenedoresImpl();
        MantTabPerfVO ret = new MantTabPerfVO();

        try{
            ret = impl.consultaMantTabPerf();
            return ret;

        }catch(IOException e){

        }catch(SQLException f){
        }
        return ret;
    }

    public MantTabPerfVO filtraMantTabPerf(String rut){

        MantenedoresImpl impl = new MantenedoresImpl();
        MantTabPerfVO ret = new MantTabPerfVO();

        try{
            ret = impl.filtraMantTabPerf(rut);
            return ret;

        }catch(IOException e){

        }catch(SQLException f){
        }
        return ret;
    }

    public MantTabPromVO consultaMantTabProm (){

        MantenedoresImpl impl = new MantenedoresImpl();
        MantTabPromVO ret = new MantTabPromVO();

        try{
            ret = impl.consultaMantTabProm();

            return ret;

        }catch(IOException e){

        }catch(SQLException f){
        }
        return ret;
    }

    public MantTabPromVO filtraMantTabProm (String rut){

        MantenedoresImpl impl = new MantenedoresImpl();
        MantTabPromVO ret = new MantTabPromVO();

        try{
            ret = impl.filtraMantTabProm(rut);

            return ret;

        }catch(IOException e){

        }catch(SQLException f){
        }
        return ret;
    }

    public MantTabTipoDocVO consultaMantTabTipoDoc (){

        MantenedoresImpl impl = new MantenedoresImpl();
        MantTabTipoDocVO ret = new MantTabTipoDocVO();

        try{
            ret = impl.consultaMantTabTipoDoc();
            return ret;

        }catch(IOException e){

        }catch(SQLException f){
        }
        return ret;
    }

    public MantTabBenefVO consultaMantTabBenef (){

        MantenedoresImpl impl = new MantenedoresImpl();
        MantTabBenefVO ret = new MantTabBenefVO();

        try{
            ret = impl.consultaMantTabBenef();
            return ret;

        }catch(IOException e){

        }catch(SQLException f){
        }
        return ret;
    }

    public MantTabDocsBenefsDinVO consultaMantTabDocsBenefsDin (){

        MantenedoresImpl impl = new MantenedoresImpl();
        MantTabDocsBenefsDinVO ret = new MantTabDocsBenefsDinVO();

        try{
            ret = impl.consultaMantTabDocsBenefsDin();
            return ret;

        }catch(IOException e){

        }catch(SQLException f){
        }
        return ret;
    }

    public MantTabRelBenefsDinDocsBenefsVO consultaMantTabRelBenefsDinDocsBenefs (){

        MantenedoresImpl impl = new MantenedoresImpl();
        MantTabRelBenefsDinDocsBenefsVO ret = new MantTabRelBenefsDinDocsBenefsVO();

        try{
            ret = impl.consultaMantTabRelBenefsDinDocsBenefs();
            return ret;

        }catch(IOException e){

        }catch(SQLException f){
        }
        return ret;
    }


    //	------------------------- TABLA GLOBALES -------------------------//
    public RespuestaVO saveDataGlobalTable(String glosa, String entidad, HttpSession session){

        RespuestaVO  resp = new RespuestaVO();

        resp =  MantenedoresImpl.saveDataGlobalTable(glosa, entidad);

        int entidad_aux;		
        entidad_aux = Integer.parseInt(entidad);

        ListadoParametros.setLISTA_UNICA(null);
        ListadoParametros listaParam = ListadoParametros.getInstancia();

        if(entidad_aux == 4){
            session.setAttribute(IND_Constants.lst_nacionalidad,	listaParam.getListNacionalidad());
        }else if(entidad_aux == 5){
            session.setAttribute(IND_Constants.lst_est_civl,	listaParam.getListEstadoCivil());
        }else if(entidad_aux == 14){
            session.setAttribute(IND_Constants.lst_nvl_educ,	listaParam.getListNivelEducacional());
        }else if(entidad_aux == 15){
            session.setAttribute(IND_Constants.lst_prof, listaParam.getListProfesion());
        }else if(entidad_aux == 9){
            session.setAttribute(IND_Constants.lst_afp,	listaParam.getListAfp());
        }else if(entidad_aux == 8){
            session.setAttribute(IND_Constants.lst_reg_salud,	listaParam.getListRegimenSalud());
        }else if(entidad_aux == 31){
            session.setAttribute(IND_Constants.lst_vlr_apo,	listaParam.getTxtValorCalculoAporte());
        }else if(entidad_aux == 29){
            session.setAttribute(IND_Constants.lst_tipo_pago, listaParam.getListTipoPagoAporte());
        }else{	
            return resp;
        }

        return resp;
    }

    public RespuestaVO modifDataGlobalTable(String codigo, String glosa, String entidad, HttpSession session){

        RespuestaVO resp = new RespuestaVO();

        resp = MantenedoresImpl.modifDataGlobalTable(codigo, glosa, entidad);

        int entidad_aux;
        ListadoParametros listParametros = ListadoParametros.getInstancia();
        Parametro[] entidades = listParametros.getListEntidades();		
        entidad_aux = Helper.obtenerCodigo(entidades,entidad);

        ListadoParametros.setLISTA_UNICA(null);
        ListadoParametros listaParam = ListadoParametros.getInstancia();

        if(entidad_aux == 4){
            session.setAttribute(IND_Constants.lst_nacionalidad,	listaParam.getListNacionalidad());
        }else if(entidad_aux == 5){
            session.setAttribute(IND_Constants.lst_est_civl,	listaParam.getListEstadoCivil());
        }else if(entidad_aux == 14){
            session.setAttribute(IND_Constants.lst_nvl_educ,	listaParam.getListNivelEducacional());
        }else if(entidad_aux == 15){
            session.setAttribute(IND_Constants.lst_prof, listaParam.getListProfesion());
        }else if(entidad_aux == 9){
            session.setAttribute(IND_Constants.lst_afp,	listaParam.getListAfp());
        }else if(entidad_aux == 8){
            session.setAttribute(IND_Constants.lst_reg_salud,	listaParam.getListRegimenSalud());
        }else if(entidad_aux == 31){
            session.setAttribute(IND_Constants.lst_vlr_apo,	listaParam.getTxtValorCalculoAporte());
        }else if(entidad_aux == 29){
            session.setAttribute(IND_Constants.lst_tipo_pago, listaParam.getListTipoPagoAporte());
        }else{	
            return resp;
        }

        return resp;
    }

    public RespuestaVO cambiarEstadoGlobalTable(String codigo, String entidad, int estado, HttpSession session){

        RespuestaVO resp = new RespuestaVO();

        resp = MantenedoresImpl.cambiarEstadoGlobalTable(codigo, entidad, estado);

        int entidad_aux;
        ListadoParametros listParametros = ListadoParametros.getInstancia();
        Parametro[] entidades = listParametros.getListEntidades();		
        entidad_aux = Helper.obtenerCodigo(entidades,entidad);

        ListadoParametros.setLISTA_UNICA(null);
        ListadoParametros listaParam = ListadoParametros.getInstancia();

        if(entidad_aux == 4){
            session.setAttribute(IND_Constants.lst_nacionalidad,	listaParam.getListNacionalidad());
        }else if(entidad_aux == 5){
            session.setAttribute(IND_Constants.lst_est_civl,	listaParam.getListEstadoCivil());
        }else if(entidad_aux == 14){
            session.setAttribute(IND_Constants.lst_nvl_educ,	listaParam.getListNivelEducacional());
        }else if(entidad_aux == 15){
            session.setAttribute(IND_Constants.lst_prof, listaParam.getListProfesion());
        }else if(entidad_aux == 9){
            session.setAttribute(IND_Constants.lst_afp,	listaParam.getListAfp());
        }else if(entidad_aux == 8){
            session.setAttribute(IND_Constants.lst_reg_salud,	listaParam.getListRegimenSalud());
        }else if(entidad_aux == 31){
            session.setAttribute(IND_Constants.lst_vlr_apo,	listaParam.getTxtValorCalculoAporte());
        }else if(entidad_aux == 29){
            session.setAttribute(IND_Constants.lst_tipo_pago, listaParam.getListTipoPagoAporte());
        }else{	
            return resp;
        }

        return resp;
    }

    //------------------------- TABLA PERFILES -------------------------//

    public RespuestaVO saveDataPerfiles(String rut, String perfil){

        return MantenedoresImpl.saveDataPerfiles(rut, perfil);
    }

    public RespuestaVO modifDataPerfiles(String rut, String perfil, String perfilInicial){

        return MantenedoresImpl.modifDataPerfiles(rut, perfil, perfilInicial);
    }

    public RespuestaVO cambiarEstadoPerfiles(String rut, String perfil, int estado){

        return MantenedoresImpl.cambiarEstadoPerfiles(rut, perfil, estado);
    }

    //------------------------- TABLA PROMOTOR -------------------------//

    public RespuestaVO savePromotorsTable(String rut, String numVerif, String apePat, String apeMat, String nombres, String tipoOrg, String tipoCargo, String calle, String codAreaContacto, String telContacto, String fecIni,  HttpSession session){

        return MantenedoresImpl.savePromotorsTable(rut, numVerif, apePat, apeMat, nombres, tipoOrg, tipoCargo, calle, codAreaContacto, telContacto, fecIni);			
    }

    public RespuestaVO cambiarEstadoPromotor(String rut, int estado, String fecha){

        return MantenedoresImpl.cambiarEstadoPromotor(rut, estado, fecha);
    }

    public RespuestaVO modifDataPromotor(String rut, String apePat, String apeMat, String nombres, String tipoOrg, String tipoCargo, String calle, String codAreaContacto, String telContacto, String fecIni){

        return MantenedoresImpl.modifDataPromotor(rut, apePat, apeMat, nombres, tipoOrg, tipoCargo, calle, codAreaContacto, telContacto, fecIni);
    }

    //	------------------------- TABLA TIPO DOCUMENTOS -------------------------//

    public RespuestaVO saveDataTipoDoc(String glosa, String obligatorio, String tipoSol){

        return MantenedoresImpl.saveDataTipoDoc(glosa, obligatorio, tipoSol);
    }

    public RespuestaVO modifDataTipoDoc(String codigo, String glosa, String obligatorio, String tipoSol){

        return MantenedoresImpl.modifDataTipoDoc(codigo, glosa, obligatorio, tipoSol);
    }

    public RespuestaVO cambiarEstadoTipoDoc(String codigo, int estado){

        return MantenedoresImpl.cambiarEstadoTipoDoc(codigo, estado);
    }


    //------------------------- TABLA BENEFICIOS EN DINERO -------------------------//

    public RespuestaVO saveBenefTable(String glosaCorta, String glosa, String CodCont, String tipoPago, String valPago, String montPagMax, String carencia, String recurrencia, String plazoCobro, String fecIniVal, String fecFinVal, String causante, HttpSession session){

        RespuestaVO resp = new RespuestaVO();

        resp = MantenedoresImpl.saveBenefTable(glosaCorta, glosa, CodCont, tipoPago, valPago, montPagMax, carencia, recurrencia, plazoCobro, fecIniVal, fecFinVal, causante);

        ListadoParametros.setLISTA_UNICA(null);
        ListadoParametros listaParam = ListadoParametros.getInstancia();

        session.setAttribute(IND_Constants.lst_benef,	listaParam.getListGlosaCortaBeneficio());

        return resp;

    }

    public RespuestaVO cambiarEstadoBenef(String idBeneficio, int estado){

        return MantenedoresImpl.cambiarEstadoBenef(idBeneficio, estado);
    }

    public RespuestaVO modifDataBenef(String idBeneficio, String glosaCorta, String glosa, String codCont, String tipoPago, String valPago, String montPagMax, String carencia, String recurrencia, String plazoCobro, String fecIniVal, String fecFinVal, String causante, HttpSession session){

        RespuestaVO resp = new RespuestaVO();

        resp = MantenedoresImpl.modifDataBenef(idBeneficio, glosaCorta, glosa, codCont, tipoPago, valPago, montPagMax, carencia, recurrencia, plazoCobro, fecIniVal, fecFinVal, causante);

        ListadoParametros.setLISTA_UNICA(null);
        ListadoParametros listaParam = ListadoParametros.getInstancia();

        session.setAttribute(IND_Constants.lst_benef,	listaParam.getListGlosaCortaBeneficio());

        return resp;


    }

    //	------------------------- TABLA DOCUMENTOS BENEFICIOS EN DINERO -------------------------//

    public RespuestaVO saveDocBenefDinTable(String glosaCorta, String glosa, HttpSession session){

        RespuestaVO resp = new RespuestaVO();

        resp = MantenedoresImpl.saveDocBenefDinTable(glosaCorta, glosa);

        ListadoParametros.setLISTA_UNICA(null);
        ListadoParametros listaParam = ListadoParametros.getInstancia();

        session.setAttribute(IND_Constants.lst_docs,	listaParam.getListGlosaCortaDocBeneficio());

        return resp;
    }

    public RespuestaVO cambiarEstadoDocBenDin(String idDocBenDin, int estado){

        return MantenedoresImpl.cambiarEstadoDocBenDin(idDocBenDin, estado);
    }

    public RespuestaVO modifDataDocBenDin(String idDocBenDin, String glosaCorta, String glosa, HttpSession session){

        RespuestaVO resp = new RespuestaVO();

        resp = MantenedoresImpl.modifDataDocBenDin(idDocBenDin, glosaCorta, glosa);

        ListadoParametros.setLISTA_UNICA(null);
        ListadoParametros listaParam = ListadoParametros.getInstancia();

        session.setAttribute(IND_Constants.lst_docs, listaParam.getListGlosaCortaDocBeneficio());

        return resp;
    }

    //	------------------------- TABLA RELACION ENTRE BENEFICIOS EN DINERO Y DOCUMENTOS DE BENEFICIOS -------------------------//

    public RespuestaVO saveBenDinDocTable(long beneficio, long documento, int obligatorio){

        return MantenedoresImpl.saveBenDinDocTable(beneficio, documento, obligatorio);
    }

    public RespuestaVO cambiarEstadoBenDinDoc(String idBenDinDoc, int estado){

        return MantenedoresImpl.cambiarEstadoBenDinDoc(idBenDinDoc, estado);
    }

    public RespuestaVO modifDataBenDinDoc(long idBenDinDoc, long beneficio, long documento, int obligatorio){

        return MantenedoresImpl.modifDataBenDinDoc(idBenDinDoc, beneficio, documento, obligatorio);
    }





}