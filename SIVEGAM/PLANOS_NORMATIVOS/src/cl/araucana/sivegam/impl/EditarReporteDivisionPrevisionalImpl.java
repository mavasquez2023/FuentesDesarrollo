package cl.araucana.sivegam.impl;

import java.io.IOException;
import java.sql.SQLException;
import cl.araucana.sivegam.dao.EditarReporteDivisionPrevisionalDAO;
import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.vo.LinSif011VO;
import cl.araucana.sivegam.vo.LinSif012VO;
import cl.araucana.sivegam.vo.LinSif014VO;
import cl.araucana.sivegam.vo.PeriodoProcesoVO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.Sif011VO;
import cl.araucana.sivegam.vo.Sif012VO;
import cl.araucana.sivegam.vo.Sif014VO;
import cl.araucana.sivegam.vo.TipoProcesosVO;
import cl.araucana.sivegam.vo.param.ListadoParametros;

public class EditarReporteDivisionPrevisionalImpl {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    public Sif011VO obtenerDatosPorRut(String idSelectedItem, String rut) throws IOException, SQLException {

        Sif011VO result = new Sif011VO();
        LinSif011VO[] linsif011vo = new LinSif011VO[0];

//        logger.debug("idSelectedItem: " + idSelectedItem);
        switch (Integer.parseInt(idSelectedItem)) {

        case 1:
//            logger.debug("entra case 1");
            result.setListSif011(EditarReporteDivisionPrevisionalDAO.obtenerDatosPorRutEmpresa(rut));
            break;
        case 2:
 //           logger.debug("entra case 2");
            result.setListSif011(EditarReporteDivisionPrevisionalDAO.obtenerDatosPorRutAfiliado(rut));
            break;

        default:
 //           logger.debug("entra case default");
            result.setListSif011(linsif011vo);
        }

        return result;
    }

    public Sif011VO obtenerDatosPorRutId(String idSelectedItem, String idSif011, String rut) throws IOException, SQLException {

 //       logger.debug("INI: obtenerDatosPorRutId");

        Sif011VO result = new Sif011VO();
        LinSif011VO[] linsif011vo = new LinSif011VO[0];

  //      logger.debug("idSelectedItem: " + idSelectedItem);
  //      logger.debug("idSif011: " + idSif011);
  //      logger.debug("rut: " + rut);

        switch (Integer.parseInt(idSelectedItem)) {

        case 1:
  //          logger.debug("entra case 1");
            result.setListSif011(EditarReporteDivisionPrevisionalDAO.obtenerDatosPorRutEmpresaId(rut, idSif011));
            break;
        case 2:
 //           logger.debug("entra case 2");
            result.setListSif011(EditarReporteDivisionPrevisionalDAO.obtenerDatosPorRutAfiliadoId(rut, idSif011));
            break;
        case 3:
 //           logger.debug("entra case 3");
            result.setListSif011(EditarReporteDivisionPrevisionalDAO.obtenerDatosPorCorrelativoId(idSif011));
            break;
        default:
 //           logger.debug("entra case default");
            result.setListSif011(linsif011vo);
        }

 //       logger.debug("FIN: obtenerDatosPorRutId");

        return result;
    }

    public static Sif011VO busquedaPorRangoSif011(String id, String rangoUno, String rangoDos) {

        Sif011VO result = new Sif011VO();

        result.setListSif011(EditarReporteDivisionPrevisionalDAO.busquedaPorRangoSif011(id, rangoUno, rangoDos));

        return result;
    }

    /** Busca la data estatica por id para el reporte sif011 */
    public static Sif011VO dataEstaticaPorId(String id_1, String id_2) {

        Sif011VO sif011 = new Sif011VO();

        sif011.setListSif011(EditarReporteDivisionPrevisionalDAO.dataEstaticaPorId(id_1, id_2));

        return sif011;
    }

    /** funcion que elimina un registro individual de la tabla sif011todo */
    public static RespuestaVO eliminarRegistroIndividualSif011(String id) {

        return EditarReporteDivisionPrevisionalDAO.eliminarRegistroIndividualSif011(id);
    }

    /** Funcion que elimina registro individual dada la busqueda por rut afiliado */
    public static RespuestaVO eliminarRegistroAfiliadoSif011(String id) {

        return EditarReporteDivisionPrevisionalDAO.eliminarRegistroAfiliadoSif011(id);
    }

    /** Funcion que elimina un registro dada una busqueda por correlativo */
    public static RespuestaVO eliminarRegistroCorrelativoSif011(String id) {
        return EditarReporteDivisionPrevisionalDAO.eliminarRegistroCorrelativoSif011(id);
    }

    /**
     * Funcion que actualiza la busqueda por rango luego de haber eliminado un
     * registro que se encuentre dentro del rango
     */
    public static Sif011VO actualizarPorCorrelativo(String id_1, String id_2) {
        String id = "";
        Sif011VO sif011 = new Sif011VO();
        sif011.setListSif011(EditarReporteDivisionPrevisionalDAO.busquedaPorRangoSif011(id, id_1, id_2));

        return sif011;
    }

    public static Sif011VO obtenerEstaticos(String id, String rut) {

        Sif011VO sif011 = new Sif011VO();
        LinSif011VO[] result = new LinSif011VO[0];

        switch (Integer.parseInt(id)) {
        case 1:
            sif011.setListSif011(EditarReporteDivisionPrevisionalDAO.obtenerEstaticosRut(rut));
            break;

        /*
         * case 2: return
         * EditarReporteDivisionPrevisionalDAO.obtenerEstaticosAfiliado(rut);
         * break;
         */
        default:

            sif011.setListSif011(result);

        }

        return sif011;

    }

    public static Sif011VO obtenerDataEstatica(String tipoArchivo, String periodoArchivo) {
        String tipo_Archivo = "";
        String periodo_Archivo = "";
        Sif011VO sif011vo = new Sif011VO();

        ListadoParametros listaParam1 = ListadoParametros.getInstancia();
        TipoProcesosVO[] tipoProcesos = listaParam1.getListTipoProcesos();

        for (int j = 0; j < tipoProcesos.length; j++) {
            if (tipoProcesos[j].getId_tipo_proceso() == Integer.parseInt(tipoArchivo)) {
                tipo_Archivo = tipoProcesos[j].getCodigo_tipo_proceso();
                sif011vo.setTipoArchivoGlosa(tipo_Archivo);
                break;
            }
        }

        ListadoParametros listaParam2 = ListadoParametros.getInstancia();
        PeriodoProcesoVO[] periodoProcesos = listaParam2.getListPeriodoProcesos();

        for (int i = 0; i < periodoProcesos.length; i++) {
            if (periodoProcesos[i].getPeriodo_proceso() == Long.parseLong(periodoArchivo)) {
                periodo_Archivo = periodoProcesos[i].getDescripcion_periodo_proceso();
                sif011vo.setPeriodoArchivoGlosa(periodo_Archivo);
                break;
            }
        }

        sif011vo.setCodResultado(0);

        return sif011vo;
    }

    /**
     * ******************************** FUNCIONES PARA MANTENEDORES SIF012
     * ***************************
     */
    public Sif012VO obtenerDatosSif012PorRut(String idSelectedItem, String rut) throws IOException, SQLException {

        Sif012VO result = new Sif012VO();
        LinSif012VO[] linsif012vo = new LinSif012VO[0];

        switch (Integer.parseInt(idSelectedItem)) {

        case 1:
            result.setListSif012(EditarReporteDivisionPrevisionalDAO.obtenerDatosSif012PorRutEmpresa(rut));
            break;
        case 2:
            result.setListSif012(EditarReporteDivisionPrevisionalDAO.obtenerDatosSif012PorRutAfiliado(rut));
            break;
        case 3:
            result.setListSif012(EditarReporteDivisionPrevisionalDAO.obtenerDatosSif012PorRutCaucante(rut));
            break;
        default:
            result.setListSif012(linsif012vo);
        }

        return result;
    }

    /** Funcion que obtiene la data por correlativo, desde la tabla sif012todo */
    public static Sif012VO busquedaPorRangoSif012(String idSelectedItem, String primerRango, String segundoRango) {

        Sif012VO sif012 = new Sif012VO();

        sif012.setListSif012(EditarReporteDivisionPrevisionalDAO.busquedaPorRangoSif012(idSelectedItem, primerRango, segundoRango));

        return sif012;
    }

    /** Funcion que obtiene la data estatica de la tabla sif012todo */
    public static Sif012VO dataEstaticaPorIdSif012(String rangoUno, String rangoDos) {

        Sif012VO sif012 = new Sif012VO();

        sif012.setListSif012(EditarReporteDivisionPrevisionalDAO.dataEstaticaPorIdSif012(rangoUno, rangoDos));

        return sif012;
    }

    /** Funcion que realiza el update a la tabla sif012todo */
    public static RespuestaVO updateSif012(LinSif012VO linsif012) {

        return EditarReporteDivisionPrevisionalDAO.updateSif012(linsif012);
    }

    /**
     * Obtiene data a ser mostrada en la pantalla de modificacion, del reporte
     * sif012
     */
    public Sif012VO obtenerDatosSif012PorRutId(String idSelected, String id, String rut) throws IOException, SQLException {

        Sif012VO result = new Sif012VO();
        LinSif012VO[] linsif012 = new LinSif012VO[0];

        switch (Integer.parseInt(idSelected)) {
        case 1:
            result.setListSif012(EditarReporteDivisionPrevisionalDAO.obtenerDatosPorRutEmpresaSif012Id(rut, id));
            break;
        case 2:
            result.setListSif012(EditarReporteDivisionPrevisionalDAO.obtenerDatosPorRutAfiliadoSif012Id(rut, id));
            break;
        case 3:
            result.setListSif012(EditarReporteDivisionPrevisionalDAO.obtenerDatosPorRutCausanteSif012Id(rut, id));
        case 4:
            result.setListSif012(EditarReporteDivisionPrevisionalDAO.obtenerDatosPorCorrelativoIDSif012(id));
            break;
        default:
            result.setListSif012(linsif012);
        }
        return result;
    }

    public static Sif012VO selectDataEstaticaRutSif012(String id, String rut) {

        Sif012VO vo = new Sif012VO();
        LinSif012VO[] linsif012 = new LinSif012VO[0];

        switch (Integer.parseInt(id)) {
        case 1:
            vo.setListSif012(EditarReporteDivisionPrevisionalDAO.obtenerDataEstaticaRutEmpresa(rut));
            break;
        case 2:
            vo.setListSif012(EditarReporteDivisionPrevisionalDAO.obtenerDataEstaticaRutAfiliado(rut));
            break;
        case 3:
            vo.setListSif012(EditarReporteDivisionPrevisionalDAO.obtenerDataEstaticaRutCausante(rut));
            break;
        default:
            vo.setListSif012(linsif012);

        }

        return vo;
    }

    public static RespuestaVO eliminarRegistroIndividualSif012(String idSelected, String id, String rut) {

        RespuestaVO vo = new RespuestaVO();

        vo.setCodRespuesta(99);

        switch (Integer.parseInt(idSelected)) {
        case 1:
            vo = EditarReporteDivisionPrevisionalDAO.eliminarRegistroSif012PorRutEmpresa(id, rut);
            break;
        case 2:
            vo = EditarReporteDivisionPrevisionalDAO.eliminarRegistroSif012PorRutAfiliado(id, rut);
            break;
        case 3:
            vo = EditarReporteDivisionPrevisionalDAO.eliminarRegistroSif012PorRutCausante(id, rut);
            break;
        case 4:
            vo = EditarReporteDivisionPrevisionalDAO.eliminarRegistroSif012porCorrelativo(id);
        default:
            vo.setCodRespuesta(99);
        }

        return vo;

    }

    /** Funcion que actualiza la grilla. */
    public static Sif012VO actualizarGrillaRutSif012(String idSelected, String rut) {

        Sif012VO vo = new Sif012VO();
        LinSif012VO[] linsif012 = new LinSif012VO[0];

        switch (Integer.parseInt(idSelected)) {
        case 1:
            vo.setListSif012(EditarReporteDivisionPrevisionalDAO.obtenerDatosSif012PorRutEmpresa(rut));
            break;
        case 2:
            vo.setListSif012(EditarReporteDivisionPrevisionalDAO.obtenerDatosSif012PorRutAfiliado(rut));
            break;
        case 3:
            vo.setListSif012(EditarReporteDivisionPrevisionalDAO.obtenerDatosSif012PorRutCaucante(rut));
            break;
        default:
            vo.setListSif012(linsif012);
        }

        return vo;
    }

    /**
     * Funcion que actualiza la grilla dada una eliminacion filtrada por
     * correlativo
     */
    public static Sif012VO actualizarGrillaCorrelativoSif012(String min, String max) {

        Sif012VO vo = new Sif012VO();
        vo.setListSif012(EditarReporteDivisionPrevisionalDAO.actualizarGrillaCorrelativoSif012(min, max));
        return vo;
    }

    /**
     * *************************************** FUNCIONES PARA MANTENEDOR SIF014
     * ***********************************
     */
    /** Funcion que obtiene los datos filtrados por rut. */
    public Sif014VO obtenerDatosSif014PorRut(String idSelectedItem, String rut) throws IOException, SQLException {

        Sif014VO result = new Sif014VO();
        LinSif014VO[] linsif014vo = new LinSif014VO[0];

        switch (Integer.parseInt(idSelectedItem)) {

        case 1:
            result.setListSif014(EditarReporteDivisionPrevisionalDAO.obtenerDatosSif014PorRutEmpresa(rut));
            break;
        case 2:
            result.setListSif014(EditarReporteDivisionPrevisionalDAO.obtenerDatosSif014PorRutBeneficiario(rut));
            break;
        case 3:
            result.setListSif014(EditarReporteDivisionPrevisionalDAO.obtenerDatosSif014PorRutCausante(rut));
            break;
        default:
            result.setListSif014(linsif014vo);
        }

        return result;
    }

    public static Sif014VO selectDataEstaticaRutSif014(String id, String rut) {

        Sif014VO vo = new Sif014VO();
        LinSif014VO[] linsif014 = new LinSif014VO[0];

        switch (Integer.parseInt(id)) {
        case 1:
            vo.setListSif014(EditarReporteDivisionPrevisionalDAO.obtenerDataEstaticaSif014PorRutEmpresa(rut));
            break;
        case 2:
            vo.setListSif014(EditarReporteDivisionPrevisionalDAO.obtenerDataEstaticaSif014PorRutBeneficiario(rut));
            break;
        case 3:
            vo.setListSif014(EditarReporteDivisionPrevisionalDAO.obtenerDataEstaticaSif014PorRutCausante(rut));
            break;
        default:
            vo.setListSif014(linsif014);
        }

        return vo;
    }

    /** funcion que hace el update a la tabla sif014 */
    public static RespuestaVO updateSif014(LinSif014VO vo) {

        return EditarReporteDivisionPrevisionalDAO.updateSif014(vo);
    }

    /**
     * Funcion que obtiene la data para editar, filtrada por el rut
     * correspondiente
     */
    public static Sif014VO obtenerDatosSif014PorRutId(String idSelected, String id, String rut) {

        Sif014VO result = new Sif014VO();
        LinSif014VO[] linsif014 = new LinSif014VO[0];

        switch (Integer.parseInt(idSelected)) {
        case 1:
            result.setListSif014(EditarReporteDivisionPrevisionalDAO.obtenerDatosPorRutEmpresaSif014Id(rut, id));
            break;
        case 2:
            result.setListSif014(EditarReporteDivisionPrevisionalDAO.obtenerDatosPorRutBeneficiarioSif014Id(rut, id));
            break;
        case 3:
            result.setListSif014(EditarReporteDivisionPrevisionalDAO.obtenerDatosPorRutCausanteSif014Id(rut, id));
            break;
        case 4:
            result.setListSif014(EditarReporteDivisionPrevisionalDAO.obtenerDatosPorCorrelativoIDSif014(id));
            break;
        default:
            result.setListSif014(linsif014);
        }
        return result;

    }

    /** Funcion que realiza una busqueda por rango de correlativo. */
    public static Sif014VO busquedaPorRangoSif014(String min, String max) {

        Sif014VO vo = new Sif014VO();
        vo.setListSif014(EditarReporteDivisionPrevisionalDAO.busquedaPorRangoSif014(min, max));
        return vo;
    }

    /** Funcion que obtiene la data estatica dada una busqueda por correlativo */
    public static Sif014VO dataEstaticaPorIdSif014(String min, String max) {

        Sif014VO vo = new Sif014VO();
        vo.setListSif014(EditarReporteDivisionPrevisionalDAO.obtenerDataEstaticaSif014PorCorrelativo(min, max));
        return vo;
    }

    public static RespuestaVO eliminarRegistroIndividualSif014(String idSelected, String id, String rut) {

        RespuestaVO vo = new RespuestaVO();

        vo.setCodRespuesta(99);

        switch (Integer.parseInt(idSelected)) {
        case 1:
            vo = EditarReporteDivisionPrevisionalDAO.eliminarRegistroSif014PorRutEmpresa(id, rut);
            break;
        case 2:
            vo = EditarReporteDivisionPrevisionalDAO.eliminarRegistroSif014PorRutAfiliado(id, rut);
            break;
        case 3:
            vo = EditarReporteDivisionPrevisionalDAO.eliminarRegistroSif014PorRutCausante(id, rut);
            break;
        case 4:
            vo = EditarReporteDivisionPrevisionalDAO.eliminarRegistroSif014porCorrelativo(id);
        default:
            vo.setCodRespuesta(99);
        }

        return vo;
    }

    /** Funcion que actualiza la grilla dadas eliminaciones por rut */
    public static Sif014VO actualizarGrillaRutSif014(String idSelected, String rut) {

        Sif014VO vo = new Sif014VO();
        LinSif014VO[] linsif014 = new LinSif014VO[0];

        switch (Integer.parseInt(idSelected)) {
        case 1:
            vo.setListSif014(EditarReporteDivisionPrevisionalDAO.obtenerDatosSif014PorRutEmpresa(rut));
            break;
        case 2:
            vo.setListSif014(EditarReporteDivisionPrevisionalDAO.obtenerDatosSif014PorRutBeneficiario(rut));
            break;
        case 3:
            vo.setListSif014(EditarReporteDivisionPrevisionalDAO.obtenerDatosSif014PorRutCausante(rut));
            break;
        default:
            vo.setListSif014(linsif014);
        }

        return vo;
    }

    /** Fucnion que actualiza la grilla dada una eliminacion por correlativo */
    public static Sif014VO actualizarGrillaCorrelativoSif014(String min, String max) {

        Sif014VO vo = new Sif014VO();
        vo.setListSif014(EditarReporteDivisionPrevisionalDAO.busquedaPorRangoSif014(min, max));
        return vo;
    }

    /** ************************************************************************************************************ */

    public static RespuestaVO deleteDisivionPrevisionalConRango(String idReporte, String rangoUno, String rangoDos) {

        return EditarReporteDivisionPrevisionalDAO.deleteDivisionPrevisionalConRango(idReporte, rangoUno, rangoDos);

    }

    public static RespuestaVO deleteDivisionPrevisionalSinRango(String idReporte, String rangoUno) {

        return EditarReporteDivisionPrevisionalDAO.deleteDivisionPrevisionalSinRango(idReporte, rangoUno);
    }

    /** Funcion que realiza el update a la tabla sif011 */
    public static RespuestaVO updateSif011(LinSif011VO linsif011) {

        return EditarReporteDivisionPrevisionalDAO.updateSif011(linsif011);
    }
}
