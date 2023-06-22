package cl.araucana.sivegam.dao;

import java.sql.SQLException;
import java.util.List;
import com.ibatis.sqlmap.client.SqlMapClient;
import cl.araucana.sivegam.config.ConfiguracionSqlMap;
import cl.araucana.sivegam.helper.Helper;
import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.vo.LinSif016VO;
import cl.araucana.sivegam.vo.LinSif017VO;
import cl.araucana.sivegam.vo.LinSif018VO;
import cl.araucana.sivegam.vo.LinSif019VO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.Sif016VO;
import cl.araucana.sivegam.vo.Sif017VO;
import cl.araucana.sivegam.vo.Sif018VO;
import cl.araucana.sivegam.vo.Sif019VO;

public class EditarReporteCotizacionesDAO {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    /**
     * ******************** FUNCIONES QUE IMPLEMENTAN FUNCIONALIDADES DEL
     * REPORTE SIF016 ******************************
     */
    /** Funcion que obtiene la data de la tabla sif017 dado un rut en particular. */
    public static LinSif016VO[] obtenerDatosSif016PorRut(String rut) {

        List datos = null;
        Sif016VO sif016vo = new Sif016VO();
        LinSif016VO[] linSif016 = null;
        LinSif016VO[] vacio = new LinSif016VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif016vo.setRutSearch(Long.parseLong(rut));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarCotizacionesSiv.obtenerDatosSif016PorRut", sif016vo);

            if (datos != null && datos.size() > 0) {

                linSif016 = (LinSif016VO[]) datos.toArray(new LinSif016VO[datos.size()]);
                for (int i = 0; i < linSif016.length; i++) {

                    String mtoAsfamMes = Helper.separadorDeMiles(Long.toString(linSif016[i].getMto_asfam_mes()));
                    String mtoAsfamMesRetro = Helper.separadorDeMiles(Long.toString(linSif016[i].getMto_asfam_mes_retro()));
                    String mtoReintegroMes = Helper.separadorDeMiles(Long.toString(linSif016[i].getMto_reintegros_mes()));
                    String totalPagoAsigFam = Helper.separadorDeMiles(Long.toString(linSif016[i].getTotal_pago_asigfam()));
                    String totalCotizaciones = Helper.separadorDeMiles(Long.toString(linSif016[i].getTotal_de_cotizacion()));
                    String otrosDescuentos = Helper.separadorDeMiles(Long.toString(linSif016[i].getOtros_descuentos()));
                    String resultadoNeto = Helper.separadorDeMiles(Long.toString(linSif016[i].getResultado_neto()));

                    linSif016[i].setMontoAsfamMesMiles(mtoAsfamMes);
                    linSif016[i].setMontoAsfamMesRetroMiles(mtoAsfamMesRetro);
                    linSif016[i].setMontoReintegroMesMiles(mtoReintegroMes);
                    linSif016[i].setTotalPagoAsigFamMiles(totalPagoAsigFam);
                    linSif016[i].setTotalCotizacionesMiles(totalCotizaciones);
                    linSif016[i].setOtrosDescuentosMiles(otrosDescuentos);
                    linSif016[i].setResultadoNetoMiles(resultadoNeto);
                }

                return linSif016;

            } else {

                return vacio;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {

                e.printStackTrace();
            }

        }
        return vacio;

    }

    public static RespuestaVO updateSif016(LinSif016VO linsif016) {

        RespuestaVO resp = new RespuestaVO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            sqlMap.update("editarCotizacionesSiv.updateSif016", linsif016);
            sqlMap.commitTransaction();

            resp.setCodRespuesta(0);
            return resp;

        } catch (SQLException e) {

            resp.setCodRespuesta(99);
            resp.setMsgRespuesta(e.getMessage());
            e.printStackTrace();

        } finally {

            try {

                sqlMap.endTransaction();

            } catch (SQLException e) {

                resp.setCodRespuesta(99);
                resp.setMsgRespuesta(e.getMessage());
                e.printStackTrace();
            }
        }

        return resp;
    }

    /** Funcion que implementa una busqueda por rango, en la tabla Sif016A2 */
    public static LinSif016VO[] busquedaPorRangoSif016(String uno, String dos) {

        List datos = null;
        Sif016VO vo = new Sif016VO();
        LinSif016VO[] resp = null;
        LinSif016VO[] linVo = new LinSif016VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        vo.setRangoUno(Long.parseLong(uno));
        vo.setRangoDos(Long.parseLong(dos));

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarCotizacionesSiv.obtenerDatosPorCorrelativoSif016", vo);

            if (datos != null && datos.size() > 0) {

                resp = (LinSif016VO[]) datos.toArray(new LinSif016VO[datos.size()]);

                for (int i = 0; i < resp.length; i++) {

                    String mtoAsfamMes = Helper.separadorDeMiles(Long.toString(resp[i].getMto_asfam_mes()));
                    String mtoAsfamMesRetro = Helper.separadorDeMiles(Long.toString(resp[i].getMto_asfam_mes_retro()));
                    String mtoReintegroMes = Helper.separadorDeMiles(Long.toString(resp[i].getMto_reintegros_mes()));
                    String totalPagoAsigFam = Helper.separadorDeMiles(Long.toString(resp[i].getTotal_pago_asigfam()));
                    String totalCotizaciones = Helper.separadorDeMiles(Long.toString(resp[i].getTotal_de_cotizacion()));
                    String otrosDescuentos = Helper.separadorDeMiles(Long.toString(resp[i].getOtros_descuentos()));
                    String resultadoNeto = Helper.separadorDeMiles(Long.toString(resp[i].getResultado_neto()));

                    resp[i].setMontoAsfamMesMiles(mtoAsfamMes);
                    resp[i].setMontoAsfamMesRetroMiles(mtoAsfamMesRetro);
                    resp[i].setMontoReintegroMesMiles(mtoReintegroMes);
                    resp[i].setTotalPagoAsigFamMiles(totalPagoAsigFam);
                    resp[i].setTotalCotizacionesMiles(totalCotizaciones);
                    resp[i].setOtrosDescuentosMiles(otrosDescuentos);
                    resp[i].setResultadoNetoMiles(resultadoNeto);
                }

                return resp;

            } else {
                return linVo;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                sqlMap.endTransaction();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return linVo;
    }

    public static LinSif016VO[] dataEstaticaPorIdSif016(String id) {

        List datos = null;
        Sif016VO vo = new Sif016VO();
        LinSif016VO[] resp = null;
        LinSif016VO[] linVo = new LinSif016VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        vo.setRangoUno(Long.parseLong(id));

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarCotizacionesSiv.obtenerDatosEstaticosIdSif016", vo);

            if (datos != null && datos.size() > 0) {

                resp = (LinSif016VO[]) datos.toArray(new LinSif016VO[datos.size()]);
                return resp;

            } else {
                return linVo;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                sqlMap.endTransaction();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return linVo;
    }

    public static LinSif016VO[] obtenerDatosParaEditarRutID016(String rut, String id) {

        List datos = null;
        Sif016VO vo = new Sif016VO();
        LinSif016VO[] resp = null;
        LinSif016VO[] linVo = new LinSif016VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        vo.setRutSearch(Long.parseLong(rut));
        vo.setIdsif016(Long.parseLong(id));

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarCotizacionesSiv.selectSif016ModificarPorRutId", vo);

            if (datos != null && datos.size() > 0) {

                resp = (LinSif016VO[]) datos.toArray(new LinSif016VO[datos.size()]);
                for (int i = 0; i < resp.length; i++) {
                    //logger.debug("num trabajador: " + resp[i].getNum_total_trabajador());
                }
                return resp;

            } else {
                return linVo;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                sqlMap.endTransaction();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return linVo;
    }

    public static LinSif016VO[] obtenerDatosParaEditarID016(String rut, String id) {

        List datos = null;
        Sif016VO vo = new Sif016VO();
        LinSif016VO[] resp = null;
        LinSif016VO[] linVo = new LinSif016VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        vo.setRutSearch(Long.parseLong(rut));
        vo.setIdsif016(Long.parseLong(id));

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarCotizacionesSiv.selectSif016ModificarPorId", vo);

            if (datos != null && datos.size() > 0) {

                resp = (LinSif016VO[]) datos.toArray(new LinSif016VO[datos.size()]);

                for (int i = 0; i < resp.length; i++) {

                    String fechaDec = "";
                    String fechaEmi = "";
                    String fechaDeclaracion = Long.toString(resp[i].getFech_declaracion());
                    String fechaEmision = Long.toString(resp[i].getFech_emision_doc());
                    if (fechaDeclaracion != null && fechaDeclaracion.length() == 8) {
                        fechaDec = fechaDeclaracion.substring(6, 8) + "/" + fechaDeclaracion.substring(4, 6) + "/" + fechaDeclaracion.substring(0, 4);
                    }

                    if (fechaEmision != null && fechaEmision.length() == 8) {
                        fechaEmi = fechaEmision.substring(6, 8) + "/" + fechaEmision.substring(4, 6) + "/" + fechaEmision.substring(0, 4);
                    }
                    resp[i].setFechaDeclaracion(fechaDec);
                    resp[i].setFechaEmision(fechaEmi);

                }

                return resp;

            } else {
                return linVo;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                sqlMap.endTransaction();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return linVo;
    }

    public static LinSif016VO[] obtenerEstaticosPorRutSif016(String rut) {

        List datos = null;
        Sif016VO vo = new Sif016VO();
        LinSif016VO[] resp = null;
        LinSif016VO[] linVo = new LinSif016VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        vo.setRutSearch(Long.parseLong(rut));

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarCotizacionesSiv.obtenerDatosEstaticosRutSif016", vo);

            if (datos != null && datos.size() > 0) {

                resp = (LinSif016VO[]) datos.toArray(new LinSif016VO[datos.size()]);

                return resp;

            } else {
                return linVo;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                sqlMap.endTransaction();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return linVo;
    }

    /** Elimina un unico registro de la grilla, cuando se busca por rut. */
    public static RespuestaVO eliminarRegistroIndividualSif016(String id) {

        RespuestaVO respuesta = new RespuestaVO();
        Sif016VO sif016 = new Sif016VO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);
            sif016.setRangoUno(Long.parseLong(id));
            sqlMap.update("editarCotizacionesSiv.deleteSif016", sif016);

            sqlMap.commitTransaction();
            respuesta.setCodRespuesta(0);

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

    /** **************************************************************************************************************** */
    /**
     * ******************** FUNCIONES QUE IMPLEMENTAN FUNCIONALIDADES DEL
     * REPORTE SIF017 ******************************
     */
    /** Funcion que obtiene la data de la tabla sif017 dado un rut en particular. */
    public static LinSif017VO[] obtenerDatosSif017PorRut(String rut) {

        List datos = null;
        Sif017VO sif017vo = new Sif017VO();
        LinSif017VO[] linSif017 = null;
        LinSif017VO[] vacio = new LinSif017VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif017vo.setRutSearch(Long.parseLong(rut));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarCotizacionesSiv.obtenerDatosSif017PorRut", sif017vo);

            if (datos != null && datos.size() > 0) {

                linSif017 = (LinSif017VO[]) datos.toArray(new LinSif017VO[datos.size()]);
                for (int i = 0; i < linSif017.length; i++) {

                    String montoDocumento = Helper.separadorDeMiles(Long.toString(linSif017[i].getMonto_documento()));
                    //logger.debug("Monto Documento: " + montoDocumento);
                    linSif017[i].setMontoDocumentoEnMiles(montoDocumento);
                }
                return linSif017;

            } else {

                return vacio;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {

                e.printStackTrace();
            }

        }
        return vacio;

    }

    /**
     * Funcion que implementa el update a la tabla sif017. Recibe como parametro
     * un objeto LinSif017 con los datos seteados y listos para ser modificados.
     * La funcion retorna un 0 si el proceso de update se realiza de forma
     * satisfactoria, de lo contrario retorna un 99 indicando que hubieron
     * problemas.
     */
    public static RespuestaVO updateSif017(LinSif017VO linsif017) {

        RespuestaVO resp = new RespuestaVO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            sqlMap.update("editarCotizacionesSiv.updateSif017", linsif017);
            sqlMap.commitTransaction();

            resp.setCodRespuesta(0);
            return resp;

        } catch (SQLException e) {

            resp.setCodRespuesta(99);
            resp.setMsgRespuesta(e.getMessage());
            e.printStackTrace();

        } finally {

            try {

                sqlMap.endTransaction();

            } catch (SQLException e) {

                resp.setCodRespuesta(99);
                resp.setMsgRespuesta(e.getMessage());
                e.printStackTrace();
            }
        }

        return resp;
    }

    public static LinSif017VO[] busquedaPorRangoSif017(String rangoUno, String rangoDos) {

        List datos = null;
        Sif017VO vo = new Sif017VO();
        LinSif017VO[] resp = null;
        LinSif017VO[] linVo = new LinSif017VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        vo.setRangoUno(Long.parseLong(rangoUno));
        vo.setRangoDos(Long.parseLong(rangoDos));

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarCotizacionesSiv.obtenerDatosPorCorrelativoSif017", vo);

            if (datos != null && datos.size() > 0) {

                resp = (LinSif017VO[]) datos.toArray(new LinSif017VO[datos.size()]);

                for (int i = 0; i < resp.length; i++) {
                    String montoDocumento = Helper.separadorDeMiles(Long.toString(resp[i].getMonto_documento()));
                    //logger.debug("Monto Documento: " + montoDocumento);
                    resp[i].setMontoDocumentoEnMiles(montoDocumento);

                }
                return resp;

            } else {
                return linVo;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                sqlMap.endTransaction();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return linVo;
    }

    public static LinSif017VO[] dataEstaticaPorIdSif017(String rangoUno) {

        List datos = null;
        Sif017VO vo = new Sif017VO();
        LinSif017VO[] resp = null;
        LinSif017VO[] linVo = new LinSif017VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        vo.setRangoUno(Long.parseLong(rangoUno));

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarCotizacionesSiv.obtenerDatosEstaticosIdSif017", vo);

            if (datos != null && datos.size() > 0) {

                resp = (LinSif017VO[]) datos.toArray(new LinSif017VO[datos.size()]);
                return resp;

            } else {
                return linVo;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                sqlMap.endTransaction();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return linVo;
    }

    public static LinSif017VO[] obtenerEstaticosPorRutSif017(String rut) {

        List datos = null;
        Sif017VO vo = new Sif017VO();
        LinSif017VO[] resp = null;
        LinSif017VO[] linVo = new LinSif017VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        vo.setRutSearch(Long.parseLong(rut));

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarCotizacionesSiv.obtenerDatosEstaticosRutSif017", vo);

            if (datos != null && datos.size() > 0) {

                resp = (LinSif017VO[]) datos.toArray(new LinSif017VO[datos.size()]);
                for (int i = 0; i < resp.length; i++) {
                    //logger.debug("trae este valor: " + resp[i].getFecha_proceso());
                }
                return resp;

            } else {
                return linVo;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                sqlMap.endTransaction();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return linVo;
    }

    /**
     * funcion que obtiene la data especifica para ser editarada, filtrada por
     * rut e id
     */
    public static LinSif017VO[] obtenerDatosParaEditarRutID017(String rut, String id) {

        List datos = null;
        Sif017VO vo = new Sif017VO();
        LinSif017VO[] resp = null;
        LinSif017VO[] linVo = new LinSif017VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        vo.setRutSearch(Long.parseLong(rut));
        vo.setIdsif017(Long.parseLong(id));

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarCotizacionesSiv.selectSif017ModificarPorRutId", vo);

            if (datos != null && datos.size() > 0) {

                resp = (LinSif017VO[]) datos.toArray(new LinSif017VO[datos.size()]);
                for (int i = 0; i < resp.length; i++) {

                    String fechaEmision = Long.toString(resp[i].getFech_emision_doc());
                    String fechaRendicion = Long.toString(resp[i].getFecha_rendicion());

                    String fecha_emision_doc = fechaEmision.substring(6, 8) + "/" + fechaEmision.substring(4, 6) + "/" + fechaEmision.substring(0, 4);
                    String fecha_rendicion = fechaRendicion.substring(6, 8) + "/" + fechaRendicion.substring(4, 6) + "/" + fechaRendicion.substring(0, 4);

                    //logger.debug("fecha emision: " + fecha_emision_doc);
                    resp[i].setFechaEmisionDocumentoDate(fecha_emision_doc);
                    resp[i].setFechaRendicionDate(fecha_rendicion);
                }
                return resp;

            } else {
                return linVo;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                sqlMap.endTransaction();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return linVo;
    }

    /**
     * Funcion que obtiene la data especifica para ser editada, filtrada solo
     * por id
     */
    public static LinSif017VO[] obtenerDatosParaEditarID017(String id) {

        List datos = null;
        Sif017VO vo = new Sif017VO();
        LinSif017VO[] resp = null;
        LinSif017VO[] linVo = new LinSif017VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        vo.setIdsif017(Long.parseLong(id));

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarCotizacionesSiv.selectSif017ModificarPorId", vo);

            if (datos != null && datos.size() > 0) {

                resp = (LinSif017VO[]) datos.toArray(new LinSif017VO[datos.size()]);
                for (int i = 0; i < resp.length; i++) {

                    String fechaEmision = Long.toString(resp[i].getFech_emision_doc());
                    String fechaRendicion = Long.toString(resp[i].getFecha_rendicion());

                    String fecha_emision_doc = fechaEmision.substring(6, 8) + "/" + fechaEmision.substring(4, 6) + "/" + fechaEmision.substring(0, 4);
                    String fecha_rendicion = fechaRendicion.substring(6, 8) + "/" + fechaRendicion.substring(4, 6) + "/" + fechaRendicion.substring(0, 4);

                    //logger.debug("fecha emision: " + fecha_emision_doc);
                    resp[i].setFechaEmisionDocumentoDate(fecha_emision_doc);
                    resp[i].setFechaRendicionDate(fecha_rendicion);
                }
                return resp;

            } else {
                return linVo;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                sqlMap.endTransaction();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return linVo;
    }

    /** Elimina un unico registro de la grilla, cuando se busca por rut. */
    public static RespuestaVO eliminarRegistroIndividualSif017(String id) {

        RespuestaVO respuesta = new RespuestaVO();
        Sif017VO sif017 = new Sif017VO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);
            sif017.setRangoUno(Long.parseLong(id));
            sqlMap.update("editarCotizacionesSiv.deleteSif017", sif017);

            sqlMap.commitTransaction();
            respuesta.setCodRespuesta(0);

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

    /** ************************************************************************************************************** */

    public static LinSif018VO[] obtenerDatosPorRut(String rut) {

        List datos = null;
        Sif018VO sif018vo = new Sif018VO();
        LinSif018VO[] linSif018 = null;
        LinSif018VO[] result = new LinSif018VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif018vo.setRutSearch(Long.parseLong(rut));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarCotizacionesSiv.obtenerDatosPorRut", sif018vo);

            if (datos != null && datos.size() > 0) {

                linSif018 = (LinSif018VO[]) datos.toArray(new LinSif018VO[datos.size()]);

                for (int i = 0; i < linSif018.length; i++) {
                    String fecha = Long.toString(linSif018[i].getFecha_emision_documento());
                    String fechaFormateada = fecha.substring(6, 8) + "/" + fecha.substring(4, 6) + "/" + fecha.substring(0, 4);
                    linSif018[i].setFechaEmisionDocumentoMod(fechaFormateada);

                    String montoDocumento = Helper.separadorDeMiles(Long.toString(linSif018[i].getMonto_documento()));
                    //logger.debug("monto documento: " + montoDocumento);
                    linSif018[i].setMontoDocumentoMiles(montoDocumento);
                }

                return linSif018;
            } else {

                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {

                e.printStackTrace();
            }

        }
        return result;
    }

    public static Sif018VO obtenerIdSif018(String rutBusqueda) {

        List datos = null;
        Sif018VO sif018 = new Sif018VO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        sif018.setRutSearch(Long.parseLong(rutBusqueda));

        //logger.debug("rut busqueda: " + rutBusqueda);
        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarCotizacionesSiv.obtenerIdSif018", sif018);

            if (datos != null && datos.size() > 0) {

                long idsif018 = Long.parseLong((String) (datos.get(0)));
                //logger.debug("id busqueda: " + idsif018);
                sif018.setIdsif018(idsif018);
                sif018.setCodResultado(0);
                return sif018;

            } else {
                sif018.setCodResultado(99);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {

                e.printStackTrace();
            }

        }

        return sif018;

    }

    public static Sif018VO updateSif018(LinSif018VO listSif018) {

        Sif018VO sif018vo = new Sif018VO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            sqlMap.update("editarCotizacionesSiv.updateSif018", listSif018);
            sqlMap.commitTransaction();

            sif018vo.setCodResultado(0);
            return sif018vo;

        } catch (SQLException e) {

            sif018vo.setCodResultado(99);
            e.printStackTrace();

        } finally {

            try {

                sqlMap.endTransaction();

            } catch (SQLException e) {

                sif018vo.setCodResultado(99);
                e.printStackTrace();
            }
        }

        return sif018vo;
    }

    /** Funcion que obtiene data a partir del correlativo. */
    public static LinSif018VO[] obtenerDatosPorCorrelativo(String rangoUno, String rangoDos) {

        List datos = null;
        Sif018VO vo = new Sif018VO();
        LinSif018VO[] resp = null;
        LinSif018VO[] linVo = new LinSif018VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        vo.setRangoUno(Long.parseLong(rangoUno));
        vo.setRangoDos(Long.parseLong(rangoDos));

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarCotizacionesSiv.obtenerDatosPorCorrelativoSif018", vo);

            if (datos != null && datos.size() > 0) {

                resp = (LinSif018VO[]) datos.toArray(new LinSif018VO[datos.size()]);

                for (int i = 0; i < resp.length; i++) {
                    String fechaFormateada = "0";
                    String fecha = Long.toString(resp[i].getFecha_emision_documento());
                    if (fecha != null && fecha.length() == 8) {
                        fechaFormateada = fecha.substring(6, 8) + "/" + fecha.substring(4, 6) + "/" + fecha.substring(0, 4);
                    } else {
                        fechaFormateada = fecha;
                    }
                    resp[i].setFechaEmisionDocumentoMod(fechaFormateada);

                    String montoDocumento = Helper.separadorDeMiles(Long.toString(resp[i].getMonto_documento()));
                    //logger.debug("monto documento: " + montoDocumento);
                    resp[i].setMontoDocumentoMiles(montoDocumento);
                }
                return resp;

            } else {
                return linVo;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                sqlMap.endTransaction();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return linVo;
    }

    /** Funcion que obtiene la data estatica filtrada por id */
    public static LinSif018VO[] dataEstaticaPorIdSif018(String id) {

        List datos = null;
        Sif018VO vo = new Sif018VO();
        LinSif018VO[] resp = null;
        LinSif018VO[] linVo = new LinSif018VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        vo.setIdsif018(Long.parseLong(id));

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarCotizacionesSiv.obtenerDatosEstaticosIdSif018", vo);

            if (datos != null && datos.size() > 0) {

                resp = (LinSif018VO[]) datos.toArray(new LinSif018VO[datos.size()]);
                return resp;

            } else {
                return linVo;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                sqlMap.endTransaction();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return linVo;

    }

    /** Funcion que obtiene la data estatica filtrada por rut. */
    public static LinSif018VO[] obtenerEstaticosPorRutSif018(String rut) {
        List datos = null;
        Sif018VO vo = new Sif018VO();
        LinSif018VO[] resp = null;
        LinSif018VO[] linVo = new LinSif018VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        vo.setRutSearch(Long.parseLong(rut));

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarCotizacionesSiv.obtenerDatosEstaticosRutIdSif018", vo);

            if (datos != null && datos.size() > 0) {

                resp = (LinSif018VO[]) datos.toArray(new LinSif018VO[datos.size()]);
                return resp;

            } else {
                return linVo;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                sqlMap.endTransaction();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return linVo;

    }

    /** Funciones que obtienen data para editar */
    /** Obtiene la data a editar, filtrada por rut e id */
    public static LinSif018VO[] obtenerDatosParaEditarRutID018(String rut, String id) {

        List datos = null;
        Sif018VO vo = new Sif018VO();
        LinSif018VO[] resp = null;
        LinSif018VO[] linVo = new LinSif018VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        vo.setRutSearch(Long.parseLong(rut));
        vo.setIdsif018(Long.parseLong(id));

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarCotizacionesSiv.selectSif018ModificarPorRutId", vo);

            if (datos != null && datos.size() > 0) {

                resp = (LinSif018VO[]) datos.toArray(new LinSif018VO[datos.size()]);
                for (int i = 0; i < resp.length; i++) {

                    String fechaEmision = Long.toString(resp[i].getFecha_emision_documento());
                    String fechaCobro = Long.toString(resp[i].getFecha_cobro());

                    if ("0".equals(fechaCobro)) {
                        fechaCobro = "19000101";
                    }

                    String fecha_emision_doc = fechaEmision.substring(6, 8) + "/" + fechaEmision.substring(4, 6) + "/" + fechaEmision.substring(0, 4);
                    String fecha_cobro = fechaCobro.substring(6, 8) + "/" + fechaCobro.substring(4, 6) + "/" + fechaCobro.substring(0, 4);

                    resp[i].setFechaEmisionDocumentoDate(fecha_emision_doc);
                    resp[i].setFechaRendicionDate(fecha_cobro);

                }
                return resp;

            } else {
                return linVo;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                sqlMap.endTransaction();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return linVo;
    }

    /** Obtiene la data para editar, filtrada por id */
    public static LinSif018VO[] obtenerDatosParaEditarID018(String id) {

        List datos = null;
        Sif018VO vo = new Sif018VO();
        LinSif018VO[] resp = null;
        LinSif018VO[] linVo = new LinSif018VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        vo.setIdsif018(Long.parseLong(id));

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarCotizacionesSiv.selectSif018ModificarPorId", vo);

            if (datos != null && datos.size() > 0) {

                resp = (LinSif018VO[]) datos.toArray(new LinSif018VO[datos.size()]);
                for (int i = 0; i < resp.length; i++) {

                    String fechaEmision = Long.toString(resp[i].getFecha_emision_documento());
                    String fechaCobro = Long.toString(resp[i].getFecha_cobro());
                    String fecha_emision_doc = "0";
                    String fecha_cobro = "0";
                    if (fechaEmision != null && fechaEmision.length() == 8) {
                        fecha_emision_doc = fechaEmision.substring(6, 8) + "/" + fechaEmision.substring(4, 6) + "/" + fechaEmision.substring(0, 4);
                    }
                    if (fechaCobro != null && fechaCobro.length() == 8) {
                        fecha_cobro = fechaCobro.substring(6, 8) + "/" + fechaCobro.substring(4, 6) + "/" + fechaCobro.substring(0, 4);
                    }
                    resp[i].setFechaEmisionDocumentoDate(fecha_emision_doc);
                    resp[i].setFechaRendicionDate(fecha_cobro);

                }
                return resp;

            } else {
                return linVo;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                sqlMap.endTransaction();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return linVo;
    }

    public static RespuestaVO eliminarRegistroIndividualSif018(String id) {

        RespuestaVO respuesta = new RespuestaVO();
        Sif018VO sif018 = new Sif018VO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);
            sif018.setRangoUno(Long.parseLong(id));
            sqlMap.update("editarCotizacionesSiv.deleteSif018", sif018);

            sqlMap.commitTransaction();
            respuesta.setCodRespuesta(0);

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

    /** ********************************************************************************************* */
    /** Funcion que obtiene la data de la tabla sif019 dado un rut en particular. */
    public static LinSif019VO[] obtenerDatosSif019PorRut(String rut) {

        List datos = null;
        Sif019VO sif019vo = new Sif019VO();
        LinSif019VO[] linSif019 = null;
        LinSif019VO[] vacio = new LinSif019VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif019vo.setRutSearch(Long.parseLong(rut));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarCotizacionesSiv.obtenerDatosSif019PorRut", sif019vo);

            if (datos != null && datos.size() > 0) {

                linSif019 = (LinSif019VO[]) datos.toArray(new LinSif019VO[datos.size()]);

                return linSif019;

            } else {

                return vacio;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {

                e.printStackTrace();
            }

        }
        return vacio;

    }

    public static LinSif019VO[] obtenerDatosSif019PorCorrelativo(String min, String max) {

        List datos = null;
        Sif019VO vo = new Sif019VO();
        LinSif019VO[] resp = null;
        LinSif019VO[] linVo = new LinSif019VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        vo.setRangoUno(Long.parseLong(min));
        vo.setRangoDos(Long.parseLong(max));

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarCotizacionesSiv.obtenerDatosPorCorrelativoSif019", vo);

            if (datos != null && datos.size() > 0) {

                resp = (LinSif019VO[]) datos.toArray(new LinSif019VO[datos.size()]);
                return resp;

            } else {
                return linVo;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                sqlMap.endTransaction();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return linVo;
    }

    /** Funcion que obtiene data para editar, filtrada por rut e id */
    public static LinSif019VO[] obtenerDatosParaEditarRutID019(String rut, String id) {

        List datos = null;
        Sif019VO vo = new Sif019VO();
        LinSif019VO[] resp = null;
        LinSif019VO[] linVo = new LinSif019VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        vo.setRutSearch(Long.parseLong(rut));
        vo.setIdSif019(Long.parseLong(id));

        try {
            String fecha_emision_doc = "";
            String fecha_rendicion = "";

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarCotizacionesSiv.selectSif019ModificarPorRutId", vo);

            if (datos != null && datos.size() > 0) {

                resp = (LinSif019VO[]) datos.toArray(new LinSif019VO[datos.size()]);
                for (int i = 0; i < resp.length; i++) {

                    String fechaOrigen = Long.toString(resp[i].getFecha_emision_orig());
                    String fechaNuevo = Long.toString(resp[i].getFecha_emision_nuevo());

                    fecha_emision_doc = fechaOrigen.substring(6, 8) + "/" + fechaOrigen.substring(4, 6) + "/" + fechaOrigen.substring(0, 4);
                    fecha_rendicion = fechaNuevo.substring(6, 8) + "/" + fechaNuevo.substring(4, 6) + "/" + fechaNuevo.substring(0, 4);

                    resp[i].setFechaEmisionOrigenDate(fecha_emision_doc);
                    resp[i].setFechaEmisionNuevoDate(fecha_rendicion);
                }
                return resp;

            } else {
                return linVo;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                sqlMap.endTransaction();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return linVo;
    }

    /** Funcion que obtiene la data para editar, filtrada por id */
    public static LinSif019VO[] obtenerDatosParaEditarID019(String id) {

        List datos = null;
        Sif019VO vo = new Sif019VO();
        LinSif019VO[] resp = null;
        LinSif019VO[] linVo = new LinSif019VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        vo.setIdSif019(Long.parseLong(id));

        try {
            String fecha_emision_doc = "";
            String fecha_rendicion = "";
            String mes_origen_gasto = "";

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarCotizacionesSiv.selectSif019ModificarPorId", vo);

            if (datos != null && datos.size() > 0) {

                resp = (LinSif019VO[]) datos.toArray(new LinSif019VO[datos.size()]);
                for (int i = 0; i < resp.length; i++) {

                    String fechaOrigen = Long.toString(resp[i].getFecha_emision_orig());
                    String fechaNuevo = Long.toString(resp[i].getFecha_emision_nuevo());
                    String mesOrigen = Long.toString(resp[i].getMes_origen_gasto());

                    if (fechaOrigen != null & fechaOrigen.length() == 8) {
                        fecha_emision_doc = fechaOrigen.substring(6, 8) + "/" + fechaOrigen.substring(4, 6) + "/" + fechaOrigen.substring(0, 4);
                    }

                    //logger.debug(fecha_emision_doc);
                    resp[i].setFechaEmisionOrigenDate(fecha_emision_doc);

                    if (fechaNuevo != null & fechaNuevo.length() == 8) {
                        fecha_rendicion = fechaNuevo.substring(6, 8) + "/" + fechaNuevo.substring(4, 6) + "/" + fechaNuevo.substring(0, 4);
                    }

                    resp[i].setFechaEmisionNuevoDate(fecha_rendicion);
                    //logger.debug("fecha: " + resp[i].getFechaEmisionNuevoDate());

                    if (fechaNuevo != null & fechaNuevo.length() == 8) {
                        mes_origen_gasto = mesOrigen.substring(6, 8) + "/" + mesOrigen.substring(4, 6) + "/" + mesOrigen.substring(0, 4);
                    }

                    resp[i].setMesOrigenGastoDate(mes_origen_gasto);
                    //logger.debug("fecha: " + resp[i].getMesOrigenGastoDate());
                }
                return resp;

            } else {
                return linVo;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                sqlMap.endTransaction();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return linVo;
    }

    public static RespuestaVO updateSif019(LinSif019VO vo) {

        RespuestaVO resp = new RespuestaVO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            sqlMap.update("editarCotizacionesSiv.updateSif019", vo);
            sqlMap.commitTransaction();

            resp.setCodRespuesta(0);
            return resp;

        } catch (SQLException e) {

            resp.setCodRespuesta(99);
            resp.setMsgRespuesta(e.getMessage());
            e.printStackTrace();

        } finally {

            try {

                sqlMap.endTransaction();

            } catch (SQLException e) {

                resp.setCodRespuesta(99);
                resp.setMsgRespuesta(e.getMessage());
                e.printStackTrace();
            }
        }

        return resp;
    }

    /** Funcion que elimina un registro de la grilla, dada una busqueda por rut */
    public static RespuestaVO eliminarRegistroIndividualSif019(String id) {

        RespuestaVO respuesta = new RespuestaVO();
        Sif019VO sif019 = new Sif019VO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);
            sif019.setRangoUno(Long.parseLong(id));
            sqlMap.update("editarCotizacionesSiv.deleteSif019", sif019);

            sqlMap.commitTransaction();
            respuesta.setCodRespuesta(0);

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

    public static LinSif019VO[] obtenerEstaticosPorRutSif019(String rut) {

        List datos = null;
        Sif019VO vo = new Sif019VO();
        LinSif019VO[] resp = null;
        LinSif019VO[] linVo = new LinSif019VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        vo.setRutSearch(Long.parseLong(rut));

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarCotizacionesSiv.obtenerDatosEstaticosRutIdSif019", vo);

            if (datos != null && datos.size() > 0) {

                resp = (LinSif019VO[]) datos.toArray(new LinSif019VO[datos.size()]);
                return resp;

            } else {
                return linVo;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                sqlMap.endTransaction();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return linVo;
    }

    public static LinSif019VO[] dataEstaticaPorIdSif019(String id) {
        List datos = null;
        Sif019VO vo = new Sif019VO();
        LinSif019VO[] resp = null;
        LinSif019VO[] linVo = new LinSif019VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        vo.setRangoUno(Long.parseLong(id));

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarCotizacionesSiv.obtenerDatosEstaticosPorIdSif019", vo);

            if (datos != null && datos.size() > 0) {

                resp = (LinSif019VO[]) datos.toArray(new LinSif019VO[datos.size()]);
                return resp;

            } else {
                return linVo;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                sqlMap.endTransaction();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return linVo;
    }

    /** ******************************************************************************************************* */

    public static RespuestaVO deleteCotizacionesConRango(String idReporte, String rangoUno, String rangoDos) {

        int contador;
        List datos = null;
        RespuestaVO respuesta = new RespuestaVO();
        Sif016VO sif016 = new Sif016VO();
        Sif017VO sif017 = new Sif017VO();
        Sif018VO sif018 = new Sif018VO();
        Sif019VO sif019 = new Sif019VO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            switch (Integer.parseInt(idReporte)) {

            case 16:
                sif016.setRangoUno(Long.parseLong(rangoUno));
                sif016.setRangoDos(Long.parseLong(rangoDos));
                datos = sqlMap.queryForList("editarCotizacionesSiv.verificarRangoUnoDos016", sif016);

                if (datos != null && datos.size() > 0) {
                    contador = Integer.parseInt((String) datos.get(0));
                    if (contador == 0) {
                        respuesta.setCodRespuesta(99);
                    } else {
                        sqlMap.update("editarCotizacionesSiv.deleteRangoSif016", sif016);
                        respuesta.setCodRespuesta(0);
                    }
                }
                break;

            case 17:
                sif017.setRangoUno(Long.parseLong(rangoUno));
                sif017.setRangoDos(Long.parseLong(rangoDos));
                datos = sqlMap.queryForList("editarCotizacionesSiv.verificarRangoUnoDos017", sif017);

                if (datos != null && datos.size() > 0) {
                    contador = Integer.parseInt((String) datos.get(0));
                    if (contador == 0) {
                        respuesta.setCodRespuesta(99);
                    } else {
                        sqlMap.update("editarCotizacionesSiv.deleteRangoSif017", sif017);
                        respuesta.setCodRespuesta(0);
                    }
                }
                break;

            case 18:
                sif018.setRangoUno(Long.parseLong(rangoUno));
                sif018.setRangoDos(Long.parseLong(rangoDos));
                datos = sqlMap.queryForList("editarCotizacionesSiv.verificarRangoUnoDos018", sif018);

                if (datos != null && datos.size() > 0) {
                    contador = Integer.parseInt((String) datos.get(0));
                    if (contador == 0) {
                        respuesta.setCodRespuesta(99);
                    } else {
                        sqlMap.update("editarCotizacionesSiv.deleteRangoSif018", sif018);
                        respuesta.setCodRespuesta(0);
                    }
                }
                break;

            case 19:
                sif019.setRangoUno(Long.parseLong(rangoUno));
                sif019.setRangoDos(Long.parseLong(rangoDos));
                datos = sqlMap.queryForList("editarCotizacionesSiv.verificarRangoUnoDos019", sif019);

                if (datos != null && datos.size() > 0) {
                    contador = Integer.parseInt((String) datos.get(0));
                    if (contador == 0) {
                        respuesta.setCodRespuesta(99);
                    } else {
                        sqlMap.update("editarCotizacionesSiv.deleteRangoSif019", sif019);
                        respuesta.setCodRespuesta(0);
                    }
                }
                break;

            default:
                respuesta.setCodRespuesta(99);
            }

            sqlMap.commitTransaction();

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

    public static RespuestaVO deleteCotizacionesSinRango(String idReporte, String rangoUno) {

        int contador;
        List datos = null;
        Sif016VO sif016 = new Sif016VO();
        Sif017VO sif017 = new Sif017VO();
        Sif018VO sif018 = new Sif018VO();
        Sif019VO sif019 = new Sif019VO();
        RespuestaVO respuesta = new RespuestaVO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            switch (Integer.parseInt(idReporte)) {

            case 16:
                sif016.setRangoUno(Long.parseLong(rangoUno));
                datos = sqlMap.queryForList("editarCotizacionesSiv.verificarSinRango016", sif016);
                if (datos != null && datos.size() > 0) {
                    contador = Integer.parseInt((String) datos.get(0));
                    if (contador == 0) {
                        respuesta.setCodRespuesta(99);
                    } else {
                        sqlMap.update("editarCotizacionesSiv.deleteSif016", sif016);
                        respuesta.setCodRespuesta(0);
                    }
                }
                break;

            case 17:
                sif017.setRangoUno(Long.parseLong(rangoUno));
                datos = sqlMap.queryForList("editarCotizacionesSiv.verificarSinRango017", sif017);
                if (datos != null && datos.size() > 0) {
                    contador = Integer.parseInt((String) datos.get(0));
                    if (contador == 0) {
                        respuesta.setCodRespuesta(99);
                    } else {
                        sqlMap.update("editarCotizacionesSiv.deleteSif017", sif017);
                        respuesta.setCodRespuesta(0);
                    }
                }
                break;

            case 18:
                sif018.setRangoUno(Long.parseLong(rangoUno));
                datos = sqlMap.queryForList("editarCotizacionesSiv.verificarSinRango018", sif018);
                if (datos != null && datos.size() > 0) {
                    contador = Integer.parseInt((String) datos.get(0));
                    if (contador == 0) {
                        respuesta.setCodRespuesta(99);
                    } else {
                        sqlMap.update("editarCotizacionesSiv.deleteSif018", sif018);
                        respuesta.setCodRespuesta(0);
                    }
                }
                break;

            case 19:
                sif019.setRangoUno(Long.parseLong(rangoUno));
                datos = sqlMap.queryForList("editarCotizacionesSiv.verificarSinRango019", sif019);
                if (datos != null && datos.size() > 0) {
                    contador = Integer.parseInt((String) datos.get(0));
                    if (contador == 0) {
                        respuesta.setCodRespuesta(99);
                    } else {
                        sqlMap.update("editarCotizacionesSiv.deleteSif019", sif019);
                        respuesta.setCodRespuesta(0);
                    }
                }
                break;

            default:
                respuesta.setCodRespuesta(99);
            }

            sqlMap.commitTransaction();

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
