package cl.araucana.sivegam.impl;

import java.io.IOException;
import java.sql.SQLException;

import cl.araucana.sivegam.dao.EditarReporteCotizacionesDAO;
import cl.araucana.sivegam.vo.LinSif016VO;
import cl.araucana.sivegam.vo.LinSif017VO;
import cl.araucana.sivegam.vo.LinSif018VO;
import cl.araucana.sivegam.vo.LinSif019VO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.Sif016VO;
import cl.araucana.sivegam.vo.Sif017VO;
import cl.araucana.sivegam.vo.Sif018VO;
import cl.araucana.sivegam.vo.Sif019VO;

public class EditarReporteCotizacionesImpl {

    /* FUNCIONES QUE IMPLEMENTAN FUNCIONALIDADES EN SIF016 */
    /** Funcion que obtiene datos de la tabla sif016 */
    public Sif016VO obtenerDatosSif016PorRut(String rut) throws IOException, SQLException {

        Sif016VO result = new Sif016VO();

        result.setListSif016(EditarReporteCotizacionesDAO.obtenerDatosSif016PorRut(rut));

        return result;
    }

    /** Funcion que realiza update de los campos modificados en la tabla sif016A2 */
    public static RespuestaVO updateSif016(LinSif016VO linsif016) {

        return EditarReporteCotizacionesDAO.updateSif016(linsif016);
    }

    /**
     * Funcion que obtiene los datos modificados para actualizar la grilla, dada
     * una modificacion con filtro por rut
     */
    public static Sif016VO obtenerDatosModificadosSif016(String id, String rut) {

        Sif016VO sif016 = new Sif016VO();
        sif016.setListSif016(EditarReporteCotizacionesDAO.obtenerDatosSif016PorRut(rut));
        return sif016;
    }

    /**
     * Funcion que obtiene los datos modificados dada una busqueda por rango,
     * para actualizar la grilla.
     */
    public static Sif016VO obtenerDatosModificadosSif016Rango(String uno, String dos) {

        Sif016VO sif016 = new Sif016VO();

        sif016.setListSif016(EditarReporteCotizacionesDAO.busquedaPorRangoSif016(uno, dos));

        return sif016;
    }

    public static Sif016VO busquedaPorRangoSif016(String rangoUno, String rangoDos) {

        Sif016VO sif016 = new Sif016VO();
        sif016.setListSif016(EditarReporteCotizacionesDAO.busquedaPorRangoSif016(rangoUno, rangoDos));
        return sif016;
    }

    public static Sif016VO dataEstaticaPorIdSif016(String id) {

        Sif016VO vo = new Sif016VO();
        vo.setListSif016(EditarReporteCotizacionesDAO.dataEstaticaPorIdSif016(id));
        return vo;
    }

    public static Sif016VO obtenerDatosSif016ParaEditar(String rut, String id, String idSelected) {

        Sif016VO vo = new Sif016VO();
        LinSif016VO[] list016 = new LinSif016VO[0];

        switch (Integer.parseInt(idSelected)) {
        case 1:
            vo.setListSif016(EditarReporteCotizacionesDAO.obtenerDatosParaEditarRutID016(rut, id));
            break;
        case 2:
            vo.setListSif016(EditarReporteCotizacionesDAO.obtenerDatosParaEditarID016(rut, id));
            break;
        default:
            vo.setListSif016(list016);
            break;
        }

        return vo;

    }

    public static Sif016VO obtenerEstaticosPorRutSif016(String rut) {

        Sif016VO sif016 = new Sif016VO();
        sif016.setListSif016(EditarReporteCotizacionesDAO.obtenerEstaticosPorRutSif016(rut));
        return sif016;
    }

    public static RespuestaVO eliminarRegistroIndividualSif016(String id) {

        return EditarReporteCotizacionesDAO.eliminarRegistroIndividualSif016(id);
    }

    public static RespuestaVO eliminarRegistroCorrelativoSif016(String id) {

        return EditarReporteCotizacionesDAO.eliminarRegistroIndividualSif016(id);
    }

    public static Sif016VO actualizarPorCorrelativo016(String a, String b) {

        Sif016VO resp = new Sif016VO();

        resp.setListSif016(EditarReporteCotizacionesDAO.busquedaPorRangoSif016(a, b));

        return resp;
    }

    public static Sif016VO obtenerDatosPorRut016(String rut) {

        Sif016VO vo = new Sif016VO();
        vo.setListSif016(EditarReporteCotizacionesDAO.obtenerDatosSif016PorRut(rut));
        return vo;
    }

    /** *************************************************************************** */
    /** ******* FUNCIONES QUE IMPLEMENTAN FUNCIONALIDADES DE SIF017 ******** */
    /**
     * Funcion que ontiene la data de la tabla Sif017
     */
    public Sif017VO obtenerDatosSif017PorRut(String rut) throws IOException, SQLException {

        Sif017VO result = new Sif017VO();

        result.setListSif017(EditarReporteCotizacionesDAO.obtenerDatosSif017PorRut(rut));

        return result;
    }

    /**
     * Update de los campos modificables en sif017. Sirve como puente de
     * comunicacion.
     */
    public static RespuestaVO updateSif017(LinSif017VO linsif017) {

        return EditarReporteCotizacionesDAO.updateSif017(linsif017);
    }

    /**
     * Funcion que consulta usando filtro por rut, de los datos modificados para
     * actualizar la grilla.
     */
    public static Sif017VO obtenerDatosModificadosSif017(String idSelected, String rut) {

        Sif017VO sif017 = new Sif017VO();
        sif017.setListSif017(EditarReporteCotizacionesDAO.obtenerDatosSif017PorRut(rut));

        return sif017;
    }

    public static Sif017VO obtenerDatosModificadosSif017Rango(String rango1, String rango2) {

        Sif017VO sif017 = new Sif017VO();

        sif017.setListSif017(EditarReporteCotizacionesDAO.busquedaPorRangoSif017(rango1, rango2));

        return sif017;
    }

    public static Sif017VO busquedaPorRangoSif017(String rangoUno, String rangoDos) {

        Sif017VO sif017 = new Sif017VO();

        sif017.setListSif017(EditarReporteCotizacionesDAO.busquedaPorRangoSif017(rangoUno, rangoDos));

        return sif017;

    }

    public static Sif017VO dataEstaticaPorIdSif017(String rangoUno) {

        Sif017VO vo = new Sif017VO();

        vo.setListSif017(EditarReporteCotizacionesDAO.dataEstaticaPorIdSif017(rangoUno));

        return vo;
    }

    public static Sif017VO obtenerEstaticosPorRutSif017(String rut) {

        Sif017VO vo = new Sif017VO();

        vo.setListSif017(EditarReporteCotizacionesDAO.obtenerEstaticosPorRutSif017(rut));

        return vo;

    }

    public static Sif017VO obtenerDatosSif017ParaEditar(String rut, String id, String idSelected) {

        Sif017VO vo = new Sif017VO();
        LinSif017VO[] list017 = new LinSif017VO[0];

        switch (Integer.parseInt(idSelected)) {
        case 1:
            vo.setListSif017(EditarReporteCotizacionesDAO.obtenerDatosParaEditarRutID017(rut, id));
            break;
        case 2:
            vo.setListSif017(EditarReporteCotizacionesDAO.obtenerDatosParaEditarID017(id));
            break;
        default:
            vo.setListSif017(list017);
            break;
        }

        return vo;

    }

    public static RespuestaVO eliminarRegistroIndividualSif017(String id) {

        return EditarReporteCotizacionesDAO.eliminarRegistroIndividualSif017(id);
    }

    public static RespuestaVO eliminarRegistroCorrelativoSif017(String id) {

        return EditarReporteCotizacionesDAO.eliminarRegistroIndividualSif017(id);
    }

    public static Sif017VO actualizarPorCorrelativo(String a, String b) {

        Sif017VO resp = new Sif017VO();

        resp.setListSif017(EditarReporteCotizacionesDAO.busquedaPorRangoSif017(a, b));

        return resp;
    }

    public static Sif017VO obtenerDatosPorRut017(String rut) {

        Sif017VO vo = new Sif017VO();
        vo.setListSif017(EditarReporteCotizacionesDAO.obtenerDatosSif017PorRut(rut));
        return vo;
    }

    /** ******************************************************************************* */
    /**
     * Funcion que obtiene los datos de la tabla Sif018 mediante un filtro por
     * el rut. Recibe como parametro el rut.
     */
    public Sif018VO obtenerDatosPorRut(String rut) throws IOException, SQLException {

        Sif018VO result = new Sif018VO();

        result.setListSif018(EditarReporteCotizacionesDAO.obtenerDatosPorRut(rut));

        return result;
    }

    /**
     * Funcion que obtiene el id de un registro en particular dado el rut de la
     * empresa a buscar.
     */
    public static Sif018VO obtenerIdSif018(String rutBusqueda) {

        return EditarReporteCotizacionesDAO.obtenerIdSif018(rutBusqueda);
    }

    public static Sif018VO updateSif018(LinSif018VO listSif018) {

        return EditarReporteCotizacionesDAO.updateSif018(listSif018);
    }

    public static Sif018VO obtenerDatosPorCorrelativo(String rangoUno, String rangoDos) throws IOException, SQLException {

        Sif018VO result = new Sif018VO();

        result.setListSif018(EditarReporteCotizacionesDAO.obtenerDatosPorCorrelativo(rangoUno, rangoDos));

        return result;
    }

    public static Sif018VO dataEstaticaPorIdSif018(String id) {

        Sif018VO vo = new Sif018VO();
        vo.setListSif018(EditarReporteCotizacionesDAO.dataEstaticaPorIdSif018(id));
        return vo;
    }

    public static Sif018VO obtenerEstaticosPorRutSif018(String rut) {

        Sif018VO vo = new Sif018VO();
        vo.setListSif018(EditarReporteCotizacionesDAO.obtenerEstaticosPorRutSif018(rut));
        return vo;
    }

    public static Sif018VO obtenerDatosSif018ParaEditar(String rut, String id, String idSelected) {

        Sif018VO vo = new Sif018VO();
        LinSif018VO[] result = new LinSif018VO[0];

        switch (Integer.parseInt(idSelected)) {
        case 1:
            vo.setListSif018(EditarReporteCotizacionesDAO.obtenerDatosParaEditarRutID018(rut, id));
            break;
        case 2:
            vo.setListSif018(EditarReporteCotizacionesDAO.obtenerDatosParaEditarID018(id));
            break;
        default:
            vo.setListSif018(result);
        }

        return vo;
    }

    public static RespuestaVO eliminarRegistroIndividualSif018(String id) {

        return EditarReporteCotizacionesDAO.eliminarRegistroIndividualSif018(id);
    }

    public static RespuestaVO eliminarRegistroCorrelativoSif018(String id) {

        return EditarReporteCotizacionesDAO.eliminarRegistroIndividualSif018(id);
    }

    public static Sif018VO obtenerDatosPorRut018(String rut) {

        Sif018VO vo = new Sif018VO();

        vo.setListSif018(EditarReporteCotizacionesDAO.obtenerDatosPorRut(rut));

        return vo;
    }

    public static Sif018VO actualizarPorCorrelativo018(String min, String max) {

        Sif018VO vo = new Sif018VO();
        vo.setListSif018(EditarReporteCotizacionesDAO.obtenerDatosPorCorrelativo(min, max));
        return vo;
    }

    /** ************************************************************************************************* */
    /**
     * ******************* FUNCIONES QUE IMPLEMENTAN FUNCIONALIDADES DE SIF019
     * ***************************
     */
    /** Funcion que obtiene datos de la tabla sif019 */
    public Sif019VO obtenerDatosSif019PorRut(String rut) throws IOException, SQLException {

        Sif019VO result = new Sif019VO();

        result.setListSif019(EditarReporteCotizacionesDAO.obtenerDatosSif019PorRut(rut));

        return result;
    }

    public Sif019VO obtenerDatosSif019PorCorrelativo(String min, String max) throws IOException, SQLException {

        Sif019VO vo = new Sif019VO();
        vo.setListSif019(EditarReporteCotizacionesDAO.obtenerDatosSif019PorCorrelativo(min, max));
        return vo;
    }

    public static Sif019VO obtenerDatosSif019ParaEditar(String rut, String id, String idSelected) {

        Sif019VO vo = new Sif019VO();
        LinSif019VO[] result = new LinSif019VO[0];

        switch (Integer.parseInt(idSelected)) {
        case 1:
            vo.setListSif019(EditarReporteCotizacionesDAO.obtenerDatosParaEditarRutID019(rut, id));
            break;
        case 2:
            vo.setListSif019(EditarReporteCotizacionesDAO.obtenerDatosParaEditarID019(id));
            break;
        default:
            vo.setListSif019(result);
        }

        return vo;
    }

    public static Sif019VO obtenerDatosModificadosPorRutSif019(String rut) {

        Sif019VO vo = new Sif019VO();
        vo.setListSif019(EditarReporteCotizacionesDAO.obtenerDatosSif019PorRut(rut));
        return vo;
    }

    public static Sif019VO obtenerDatosModificadosPorRangoSif019(String min, String max) {

        Sif019VO vo = new Sif019VO();
        vo.setListSif019(EditarReporteCotizacionesDAO.obtenerDatosSif019PorCorrelativo(min, max));
        return vo;
    }

    public static RespuestaVO updateSif019(LinSif019VO vo) {

        return EditarReporteCotizacionesDAO.updateSif019(vo);
    }

    public static RespuestaVO eliminarRegistroIndividualSif019(String id) {

        return EditarReporteCotizacionesDAO.eliminarRegistroIndividualSif019(id);
    }

    public static RespuestaVO eliminarRegistroCorrelativoSif019(String id) {

        return EditarReporteCotizacionesDAO.eliminarRegistroIndividualSif019(id);
    }

    /**
     * Funcion que obtiene la data por correlativo, dada una eliminacion por el
     * mismo filtro, para actualizar la grilla
     */
    public static Sif019VO actualizarPorCorrelativo019(String min, String max) {

        Sif019VO vo = new Sif019VO();
        vo.setListSif019(EditarReporteCotizacionesDAO.obtenerDatosSif019PorCorrelativo(min, max));
        return vo;
    }

    public static Sif019VO obtenerDatosPorRut019(String rut) {

        Sif019VO vo = new Sif019VO();
        vo.setListSif019(EditarReporteCotizacionesDAO.obtenerDatosSif019PorRut(rut));
        return vo;
    }

    public static Sif019VO obtenerEstaticosPorRutSif019(String rut) {

        Sif019VO vo = new Sif019VO();
        vo.setListSif019(EditarReporteCotizacionesDAO.obtenerEstaticosPorRutSif019(rut));
        return vo;
    }

    public static Sif019VO dataEstaticaPorIdSif019(String min, String max) {
        Sif019VO vo = new Sif019VO();
        vo.setListSif019(EditarReporteCotizacionesDAO.dataEstaticaPorIdSif019(min));
        return vo;
    }

    /** ************************************************************************************************** */
    public static RespuestaVO deleteCotizacionesConRango(String idReporte, String rangoUno, String rangoDos) {

        return EditarReporteCotizacionesDAO.deleteCotizacionesConRango(idReporte, rangoUno, rangoDos);
    }

    public static RespuestaVO deleteCotizacionesSinRango(String idReporte, String rangoUno) {

        return EditarReporteCotizacionesDAO.deleteCotizacionesSinRango(idReporte, rangoUno);
    }
}
