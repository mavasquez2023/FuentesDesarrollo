package cl.araucana.independientes.impl;

import java.io.IOException;
import java.sql.SQLException;
import cl.araucana.independientes.dao.MantenedoresDAO;
import cl.araucana.independientes.vo.MantTabGlobVO;
import cl.araucana.independientes.vo.MantTabPerfVO;
import cl.araucana.independientes.vo.MantTabPromVO;
import cl.araucana.independientes.vo.MantTabTipoDocVO;
import cl.araucana.independientes.vo.MantTabBenefVO;
import cl.araucana.independientes.vo.MantTabDocsBenefsDinVO;
import cl.araucana.independientes.vo.MantTabRelBenefsDinDocsBenefsVO;
import cl.araucana.independientes.vo.RespuestaVO;

public class MantenedoresImpl {

    //	------------------------- CONSULTAS -------------------------//

    public MantTabGlobVO consultaMantTabGlob() throws IOException,SQLException{

        MantenedoresDAO dao = new MantenedoresDAO();

        MantTabGlobVO resp = new MantTabGlobVO();

        resp.setPaginaActualMantTabGlob(2);
        resp.setLisMantTabGlob(dao.consultaMantTabGlob());

        return resp;
    }

    public MantTabGlobVO filtraMantTabGlob(String entidad) throws IOException,SQLException{

        MantenedoresDAO dao = new MantenedoresDAO();

        MantTabGlobVO resp = new MantTabGlobVO();

        resp.setPaginaActualMantTabGlob(2);
        resp.setLisMantTabGlob(dao.filtraMantTabGlob(entidad));

        return resp;
    }

    public MantTabPerfVO consultaMantTabPerf() throws IOException,SQLException{

        MantenedoresDAO dao = new MantenedoresDAO();

        MantTabPerfVO resp = new MantTabPerfVO();

        resp.setPaginaActualMantTabPerf(2);
        resp.setLisMantTabPerf(dao.consultaMantTabPerf());

        return resp;
    }

    public MantTabPerfVO filtraMantTabPerf(String rut) throws IOException,SQLException{

        MantenedoresDAO dao = new MantenedoresDAO();

        MantTabPerfVO resp = new MantTabPerfVO();

        resp.setPaginaActualMantTabPerf(2);
        resp.setLisMantTabPerf(dao.filtraMantTabPerf(rut));

        return resp;
    }

    public MantTabPromVO consultaMantTabProm() throws IOException,SQLException{

        MantenedoresDAO dao = new MantenedoresDAO();

        MantTabPromVO resp = new MantTabPromVO();

        resp.setPaginaActualMantTabProm(2);
        resp.setLisMantTabProm(dao.consultaMantTabProm());

        return resp;
    }

    public MantTabPromVO filtraMantTabProm(String rut) throws IOException,SQLException{

        MantenedoresDAO dao = new MantenedoresDAO();

        MantTabPromVO resp = new MantTabPromVO();

        resp.setPaginaActualMantTabProm(2);
        resp.setLisMantTabProm(dao.filtraMantTabProm(rut));

        return resp;
    }


    public MantTabTipoDocVO consultaMantTabTipoDoc() throws IOException,SQLException{

        MantenedoresDAO dao = new MantenedoresDAO();

        MantTabTipoDocVO resp = new MantTabTipoDocVO();

        resp.setPaginaActualMantTabTipoDoc(2);
        resp.setLisMantTabTipoDoc(dao.consultaMantTabTipoDoc());

        return resp;
    }

    public MantTabBenefVO consultaMantTabBenef() throws IOException,SQLException{

        MantenedoresDAO dao = new MantenedoresDAO();

        MantTabBenefVO resp = new MantTabBenefVO();

        resp.setPaginaActualMantTabBenef(2);
        resp.setLisMantTabBenef(dao.consultaMantTabBenef());

        return resp;
    }

    public MantTabDocsBenefsDinVO consultaMantTabDocsBenefsDin() throws IOException,SQLException{

        MantenedoresDAO dao = new MantenedoresDAO();

        MantTabDocsBenefsDinVO resp = new MantTabDocsBenefsDinVO();

        resp.setPaginaActualMantTabDocsBenefsDin(2);
        resp.setLisMantTabDocsBenefsDin(dao.consultaMantTabDocsBenefsDin());

        return resp;
    }

    public MantTabRelBenefsDinDocsBenefsVO consultaMantTabRelBenefsDinDocsBenefs() throws IOException,SQLException{

        MantenedoresDAO dao = new MantenedoresDAO();

        MantTabRelBenefsDinDocsBenefsVO resp = new MantTabRelBenefsDinDocsBenefsVO();

        resp.setPaginaActualMantTabRelBenefsDinDocsBenefs(2);
        resp.setLisMantTabRelBenefsDinDocsBenefs(dao.consultaMantTabRelBenefsDinDocsBenefs());

        return resp;
    }

    //	------------------------- TABLA GLOBALES-------------------------//

    public static RespuestaVO saveDataGlobalTable(String glosa, String entidad){

        return MantenedoresDAO.saveDataGlobalTable(glosa, entidad);
    }

    public static RespuestaVO modifDataGlobalTable(String codigo, String glosa, String entidad){

        return MantenedoresDAO.modifDataGlobalTable(codigo, glosa, entidad);
    }

    public static RespuestaVO cambiarEstadoGlobalTable(String codigo, String entidad, int estado){

        return MantenedoresDAO.cambiarEstadoGlobalTable(codigo, entidad, estado);
    }

    //	------------------------- TABLA PERFILES-------------------------//

    public static RespuestaVO saveDataPerfiles(String rut, String perfil){

        return MantenedoresDAO.saveDataPerfiles(rut, perfil);
    }

    public static RespuestaVO modifDataPerfiles(String rut, String perfil, String perfilInicial){

        return MantenedoresDAO.modifDataPerfiles(rut, perfil, perfilInicial);
    }

    public static RespuestaVO cambiarEstadoPerfiles(String rut, String perfil, int estado){

        return MantenedoresDAO.cambiarEstadoPerfiles(rut, perfil, estado);
    }

    //	------------------------- TABLA PROMOTOR -------------------------//

    public static RespuestaVO savePromotorsTable(String rut, String numVerif, String apePat, String apeMat, String nombres, String tipoOrg, String tipoCargo, String calle, String codAreaContacto, String telContacto, String fecIni){

        return MantenedoresDAO.savePromotorsTable(rut, numVerif, apePat, apeMat, nombres, tipoOrg, tipoCargo, calle, codAreaContacto, telContacto, fecIni);
    }

    public static RespuestaVO cambiarEstadoPromotor(String rut, int estado, String fecha){

        return MantenedoresDAO.cambiarEstadoPromotor(rut, estado, fecha);
    }

    public static RespuestaVO modifDataPromotor(String rut, String apePat, String apeMat, String nombres, String tipoOrg, String tipoCargo, String calle, String codAreaContacto, String telContacto, String fecIni){

        return MantenedoresDAO.modifDataPromotor(rut, apePat, apeMat, nombres, tipoOrg, tipoCargo, calle, codAreaContacto, telContacto, fecIni);
    }

    //	------------------------- TABLA TIPO DOCUMENTO -------------------------//

    public static RespuestaVO saveDataTipoDoc(String glosa, String obligatorio, String tipoSol){

        return MantenedoresDAO.saveDataTipoDoc(glosa, obligatorio, tipoSol);
    }

    public static RespuestaVO modifDataTipoDoc(String codigo, String glosa, String obligatorio, String tipoSol){

        return MantenedoresDAO.modifDataTipoDoc(codigo, glosa, obligatorio, tipoSol);
    }

    public static RespuestaVO cambiarEstadoTipoDoc(String codigo, int estado){

        return MantenedoresDAO.cambiarEstadoTipoDoc(codigo, estado);
    }

    //	------------------------- TABLA BENEFICIOS EN DINERO -------------------------//

    public static RespuestaVO saveBenefTable(String glosaCorta, String glosa, String CodCont, String tipoPago, String valPago, String montPagMax, String carencia, String recurrencia, String plazoCobro, String fecIniVal, String fecFinVal, String causante){

        return MantenedoresDAO.saveBenefTable(glosaCorta, glosa, CodCont, tipoPago, valPago, montPagMax, carencia, recurrencia, plazoCobro, fecIniVal, fecFinVal, causante);
    }

    public static RespuestaVO cambiarEstadoBenef(String idBeneficio, int estado){

        return MantenedoresDAO.cambiarEstadoBenef(idBeneficio, estado);
    }

    public static RespuestaVO modifDataBenef(String idBeneficio, String glosaCorta, String glosa, String codCont, String tipoPago, String valPago, String montPagMax, String carencia, String recurrencia, String plazoCobro, String fecIniVal, String fecFinVal, String causante){

        return MantenedoresDAO.modifDataBenef(idBeneficio, glosaCorta, glosa, codCont, tipoPago, valPago, montPagMax, carencia, recurrencia, plazoCobro, fecIniVal, fecFinVal, causante);
    }

    //	------------------------- TABLA BENEFICIOS EN DINERO -------------------------//

    public static RespuestaVO saveDocBenefDinTable(String glosaCorta, String glosa){

        return MantenedoresDAO.saveDocBenefDinTable(glosaCorta, glosa);
    }

    public static RespuestaVO cambiarEstadoDocBenDin(String idDocBenDin, int estado){

        return MantenedoresDAO.cambiarEstadoDocBenDin(idDocBenDin, estado);
    }

    public static RespuestaVO modifDataDocBenDin(String idDocBenDin, String glosaCorta, String glosa){

        return MantenedoresDAO.modifDataDocBenDin(idDocBenDin, glosaCorta, glosa);
    }

    //	------------------------- TABLA RELACION ENTRE BENEFICIOS EN DINERO Y DOCUMENTOS DE BENEFICIOS -------------------------//

    public static RespuestaVO saveBenDinDocTable(long beneficio, long documento, int obligatorio){

        return MantenedoresDAO.saveBenDinDocTable(beneficio, documento, obligatorio);
    }

    public static RespuestaVO cambiarEstadoBenDinDoc(String idBenDinDoc, int estado){

        return MantenedoresDAO.cambiarEstadoBenDinDoc(idBenDinDoc, estado);
    }

    public static RespuestaVO modifDataBenDinDoc(long idBenDinDoc, long beneficio, long documento, int obligatorio){

        return MantenedoresDAO.modifDataBenDinDoc(idBenDinDoc, beneficio, documento, obligatorio);
    }


}
