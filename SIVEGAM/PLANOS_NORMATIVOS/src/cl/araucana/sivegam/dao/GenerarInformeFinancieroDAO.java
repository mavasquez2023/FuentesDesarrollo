package cl.araucana.sivegam.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.araucana.sivegam.config.ConfiguracionSqlMap;
import cl.araucana.sivegam.vo.DevolucionDeSaldosVO;
import cl.araucana.sivegam.vo.DevolucionesVO;
import cl.araucana.sivegam.vo.EgresosVO;
import cl.araucana.sivegam.vo.InfoInformeFinancieroVO;
import cl.araucana.sivegam.vo.InformeFinancieroVO;
import cl.araucana.sivegam.vo.IngresosVO;
import cl.araucana.sivegam.vo.PagoDelMesVO;
import cl.araucana.sivegam.vo.PagosRetroactivosVO;
import cl.araucana.sivegam.vo.RespuestaVO;

public class GenerarInformeFinancieroDAO {

    public static RespuestaVO insertInformeFinanciero(InformeFinancieroVO informeFinanciero) {

        List datos = null;
        long idInformeFinanciero;
        long idIngresos;
        long idEgresos;
        long idPagoDelMes;
        long idPagosRetroactivos;
        long idDevoluciones;
        long idDevolucionDeSaldos;
        RespuestaVO respuesta = new RespuestaVO();
        InfoInformeFinancieroVO informacionFinanciera = new InfoInformeFinancieroVO();
        IngresosVO ingreso = new IngresosVO();
        PagoDelMesVO pagoDelMes = new PagoDelMesVO();
        PagosRetroactivosVO pagoRetroactivo = new PagosRetroactivosVO();
        EgresosVO egreso = new EgresosVO();
        DevolucionesVO devoluciones = new DevolucionesVO();
        DevolucionDeSaldosVO devolucionSaldos = new DevolucionDeSaldosVO();

        informacionFinanciera = informeFinanciero.getInformacionInformeFinancieroVO();
        ingreso = informeFinanciero.getIngresoVO();
        egreso = informeFinanciero.getEgresosVO();
        pagoDelMes = informeFinanciero.getPagoDelMesVO();
        pagoRetroactivo = informeFinanciero.getPagosRetroVO();
        devoluciones = informeFinanciero.getDevolucionesVO();
        devolucionSaldos = informeFinanciero.getDevolucionSaldosVO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            datos = sqlMap.queryForList("svInformeFinanciero.selectIdInformeFinanciero");
            if (datos != null && datos.size() > 0) {

                idInformeFinanciero = Long.parseLong((String) datos.get(0));
                informacionFinanciera.setIdInformeFinanciero(idInformeFinanciero);
                ingreso.setIdInformeFinanciero(idInformeFinanciero);
                egreso.setIdInformeFinanciero(idInformeFinanciero);
                devoluciones.setIdInformeFinanciero(idInformeFinanciero);
                devolucionSaldos.setIdInformeFinanciero(idInformeFinanciero);
            } else {
                respuesta.setCodRespuesta(99);
                return respuesta;
            }

            sqlMap.insert("svInformeFinanciero.insertInformeFinanciero", informacionFinanciera);

            datos = sqlMap.queryForList("svInformeFinanciero.selectIdIngresos");
            if (datos != null && datos.size() > 0) {

                idIngresos = Long.parseLong((String) datos.get(0));
                ingreso.setIdIngresos(idIngresos);

            } else {
                respuesta.setCodRespuesta(99);
                return respuesta;
            }
            sqlMap.insert("svInformeFinanciero.insertIngresos", ingreso);

            datos = sqlMap.queryForList("svInformeFinanciero.selectIdEgresos");
            if (datos != null && datos.size() > 0) {

                idEgresos = Long.parseLong((String) datos.get(0));
                egreso.setIdEgresos(idEgresos);
                pagoDelMes.setIdEgresos(idEgresos);
                pagoRetroactivo.setIdEgresos(idEgresos);
            } else {
                respuesta.setCodRespuesta(99);
                return respuesta;
            }
            sqlMap.insert("svInformeFinanciero.insertEgresos", egreso);

            datos = sqlMap.queryForList("svInformeFinanciero.selectIdPagoDelMes");
            if (datos != null && datos.size() > 0) {

                idPagoDelMes = Long.parseLong((String) datos.get(0));
                pagoDelMes.setIdPagoMes(idPagoDelMes);
            } else {
                respuesta.setCodRespuesta(99);
                return respuesta;
            }
            sqlMap.insert("svInformeFinanciero.insertPagoDelMes", pagoDelMes);

            datos = sqlMap.queryForList("svInformeFinanciero.selectIdPagosRetroactivos");
            if (datos != null && datos.size() > 0) {

                idPagosRetroactivos = Long.parseLong((String) datos.get(0));
                pagoRetroactivo.setIdPagosRetroactivos(idPagosRetroactivos);
            } else {
                respuesta.setCodRespuesta(99);
                return respuesta;
            }
            sqlMap.insert("svInformeFinanciero.insertPagoRetroactivo", pagoRetroactivo);

            datos = sqlMap.queryForList("svInformeFinanciero.selectIdDevoluciones");
            if (datos != null && datos.size() > 0) {

                idDevoluciones = Long.parseLong((String) datos.get(0));
                devoluciones.setIdDevoluciones(idDevoluciones);
            } else {
                respuesta.setCodRespuesta(99);
                return respuesta;
            }
            sqlMap.insert("svInformeFinanciero.insertDevoluciones", devoluciones);

            datos = sqlMap.queryForList("svInformeFinanciero.selectIdDevolucionDeSaldos");
            if (datos != null && datos.size() > 0) {

                idDevolucionDeSaldos = Long.parseLong((String) datos.get(0));
                devolucionSaldos.setIdDevolucionDeSaldos(idDevolucionDeSaldos);
            } else {
                respuesta.setCodRespuesta(99);
                return respuesta;
            }
            sqlMap.insert("svInformeFinanciero.insertDevolucionDeSaldos", devolucionSaldos);

            sqlMap.commitTransaction();
            respuesta.setCodRespuesta(0);

        } catch (SQLException e) {
            e.printStackTrace();
            respuesta.setCodRespuesta(99);
        } finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                respuesta.setCodRespuesta(99);
                e.printStackTrace();
            }
        }

        return respuesta;
    }

    /**
     * Funcion que obtiene los datos de un informe financiero, previo ingreso
     * dado un periodo determinado.
     */
    public static InformeFinancieroVO gettInformeFinanciero(String periodoDelInforme) {

        List datos = null;
        long idInformeFinanciero;
        long idEgreso;
        int count;
        String fecha = "";
        Date date = new Date();
        String DATE_FORMAT2 = "dd/MM/yyyy";
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        InfoInformeFinancieroVO informacionFinanciera = new InfoInformeFinancieroVO();
        IngresosVO ingreso = new IngresosVO();
        PagoDelMesVO pagoDelMes = new PagoDelMesVO();
        PagosRetroactivosVO pagoRetroactivo = new PagosRetroactivosVO();
        EgresosVO egreso = new EgresosVO();
        DevolucionesVO devoluciones = new DevolucionesVO();
        DevolucionDeSaldosVO devolucionSaldos = new DevolucionDeSaldosVO();

        InformeFinancieroVO informe = new InformeFinancieroVO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        informacionFinanciera.setPeriodo(periodoDelInforme);

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("svInformeFinanciero.obtenerContadorPorPeriodoInforme", informacionFinanciera);

            if ((String) datos.get(0) == null) {

                informe.setCodResultado(1);
                return informe;

            } else {

                if (datos != null && datos.size() > 0) {
                    count = Integer.parseInt((String) datos.get(0));

                    if (count == 1) {

                        datos = sqlMap.queryForList("svInformeFinanciero.obtenerInformacionFinanciera", informacionFinanciera);
                        if (datos != null && datos.size() > 0) {
                            informacionFinanciera = (InfoInformeFinancieroVO) datos.get(0);

                            date = informacionFinanciera.getFecDepositoExcedenteDate();

                            fecha = sdf2.format(date);
                            informacionFinanciera.setFecDepositoExcedente(fecha);
                        }

                        idInformeFinanciero = informacionFinanciera.getIdInformeFinanciero();

                        ingreso.setIdInformeFinanciero(idInformeFinanciero);
                        egreso.setIdInformeFinanciero(idInformeFinanciero);
                        devoluciones.setIdInformeFinanciero(idInformeFinanciero);
                        devolucionSaldos.setIdInformeFinanciero(idInformeFinanciero);

                        datos = sqlMap.queryForList("svInformeFinanciero.obtenerIngresos", ingreso);
                        if (datos != null && datos.size() > 0) {
                            ingreso = (IngresosVO) datos.get(0);
                        }

                        datos = sqlMap.queryForList("svInformeFinanciero.obtenerEgreso", egreso);
                        if (datos != null && datos.size() > 0) {
                            egreso = (EgresosVO) datos.get(0);
                        }

                        idEgreso = egreso.getIdEgresos();
                        pagoDelMes.setIdEgresos(idEgreso);
                        pagoRetroactivo.setIdEgresos(idEgreso);

                        datos = sqlMap.queryForList("svInformeFinanciero.obtenerPagoDelMes", pagoDelMes);
                        if (datos != null && datos.size() > 0) {
                            pagoDelMes = (PagoDelMesVO) datos.get(0);
                        }

                        datos = sqlMap.queryForList("svInformeFinanciero.obtenerPagosRetroactivos", pagoRetroactivo);
                        if (datos != null && datos.size() > 0) {
                            pagoRetroactivo = (PagosRetroactivosVO) datos.get(0);
                        }

                        datos = sqlMap.queryForList("svInformeFinanciero.obtenerDevoluciones", devoluciones);
                        if (datos != null && datos.size() > 0) {
                            devoluciones = (DevolucionesVO) datos.get(0);
                        }

                        datos = sqlMap.queryForList("svInformeFinanciero.obtenerDevolucionDeSaldos", devolucionSaldos);
                        if (datos != null && datos.size() > 0) {
                            devolucionSaldos = (DevolucionDeSaldosVO) datos.get(0);
                        }

                        informe.setInformacionInformeFinancieroVO(informacionFinanciera);
                        informe.setIngresoVO(ingreso);
                        informe.setEgresosVO(egreso);
                        informe.setPagoDelMesVO(pagoDelMes);
                        informe.setPagosRetroVO(pagoRetroactivo);
                        informe.setDevolucionesVO(devoluciones);
                        informe.setDevolucionSaldosVO(devolucionSaldos);
                        informe.setCodResultado(0);
                    } else {
                        informe.setCodResultado(1);
                        return informe;
                    }
                }
            }

            return informe;

        } catch (SQLException e) {
            informe.setCodResultado(1);
            e.printStackTrace();

        } finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                informe.setCodResultado(1);
                e.printStackTrace();
            }
        }

        return informe;
    }

    public static RespuestaVO updateInformeFinanciero(InformeFinancieroVO informeFinanciero) {

        RespuestaVO respuesta = new RespuestaVO();
        InfoInformeFinancieroVO informacionFinanciera = new InfoInformeFinancieroVO();
        IngresosVO ingreso = new IngresosVO();
        PagoDelMesVO pagoDelMes = new PagoDelMesVO();
        PagosRetroactivosVO pagoRetroactivo = new PagosRetroactivosVO();
        EgresosVO egreso = new EgresosVO();
        DevolucionesVO devoluciones = new DevolucionesVO();
        DevolucionDeSaldosVO devolucionSaldos = new DevolucionDeSaldosVO();

        informacionFinanciera = informeFinanciero.getInformacionInformeFinancieroVO();
        ingreso = informeFinanciero.getIngresoVO();
        egreso = informeFinanciero.getEgresosVO();
        pagoDelMes = informeFinanciero.getPagoDelMesVO();
        pagoRetroactivo = informeFinanciero.getPagosRetroVO();
        devoluciones = informeFinanciero.getDevolucionesVO();
        devolucionSaldos = informeFinanciero.getDevolucionSaldosVO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);
            sqlMap.update("svInformeFinanciero.updateInformeFinanciero", informacionFinanciera);
            sqlMap.update("svInformeFinanciero.updateIngresos", ingreso);
            sqlMap.update("svInformeFinanciero.updateEgresos", egreso);
            sqlMap.update("svInformeFinanciero.updatePagoDelMes", pagoDelMes);
            sqlMap.update("svInformeFinanciero.updatePagoRetroactivo", pagoRetroactivo);
            sqlMap.update("svInformeFinanciero.updateDevoluciones", devoluciones);
            sqlMap.update("svInformeFinanciero.updateDevolucionDeSaldos", devolucionSaldos);

            sqlMap.commitTransaction();
            respuesta.setCodRespuesta(0);

            return respuesta;

        } catch (SQLException e) {
            respuesta.setCodRespuesta(99);
            respuesta.setMsgRespuesta(e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                respuesta.setCodRespuesta(99);
                respuesta.setMsgRespuesta(e.getMessage());
                e.printStackTrace();
            }
        }

        return respuesta;
    }
}
