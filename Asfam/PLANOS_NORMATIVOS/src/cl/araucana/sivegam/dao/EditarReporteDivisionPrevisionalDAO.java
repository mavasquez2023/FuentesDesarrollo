package cl.araucana.sivegam.dao;

import java.sql.SQLException;
import java.util.List;
import cl.araucana.sivegam.config.ConfiguracionSqlMap;
import cl.araucana.sivegam.helper.Helper;
import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.vo.LinSif011VO;
import cl.araucana.sivegam.vo.LinSif012VO;
import cl.araucana.sivegam.vo.LinSif014VO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.Sif011VO;
import cl.araucana.sivegam.vo.Sif012VO;
import cl.araucana.sivegam.vo.Sif014VO;

import com.ibatis.sqlmap.client.SqlMapClient;

public class EditarReporteDivisionPrevisionalDAO {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    /** Funcion que obtiene data de la tabla Sif011 filtrando por rut de empresa */
    public static LinSif011VO[] obtenerDatosPorRutEmpresa(String rut) {

        List datos = null;
        Sif011VO sif011vo = new Sif011VO();
        LinSif011VO[] linSif011 = null;
        LinSif011VO[] result = new LinSif011VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif011vo.setRutSearch(Long.parseLong(rut));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosPorRutEmpresa", sif011vo);

            if (datos != null && datos.size() > 0) {

                linSif011 = (LinSif011VO[]) datos.toArray(new LinSif011VO[datos.size()]);

                for (int i = 0; i < linSif011.length; i++) {

                    String montoBeneficio = Helper.separadorDeMiles(Long.toString(linSif011[i].getMonto_beneficio()));

                    linSif011[i].setMontoBeneficioMiles(montoBeneficio);
                }

                return linSif011;
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

    /**
     * Funcion que obtiene la data de la tabla sif012todo para ser mostrada en
     * la pantalla de modificacion. Dicha funcion filtra por rut de empresa y su
     * respectivo idsif012
     */
    public static LinSif012VO[] obtenerDatosPorRutEmpresaSif012Id(String rut, String id) {
        List datos = null;
        Sif012VO sif012 = new Sif012VO();
        LinSif012VO[] linsif012 = null;
        LinSif012VO[] result = new LinSif012VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif012.setRutSearch(Long.parseLong(rut));
        sif012.setIdsif012(Long.parseLong(id));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosPorRutEmpresaSif012Id", sif012);

            if (datos != null && datos.size() > 0) {

                linsif012 = (LinSif012VO[]) datos.toArray(new LinSif012VO[datos.size()]);
                for (int i = 0; i < linsif012.length; i++) {

                    String fechaInicial = Long.toString(linsif012[i].getFecha_inicio_benef());
                    String fechaFinal = Long.toString(linsif012[i].getFecha_termino_benef());
                    String fechaIn = fechaInicial.substring(6, 8) + "/" + fechaInicial.substring(4, 6) + "/" + fechaInicial.substring(0, 4);
                    String fechaFin = fechaFinal.substring(6, 8) + "/" + fechaFinal.substring(4, 6) + "/" + fechaFinal.substring(0, 4);

                    linsif012[i].setFechaInicioBeneficioDate(fechaIn);
                    linsif012[i].setFechaTerminoBeneficioDate(fechaFin);

                    //logger.debug(linsif012[i].getFechaInicioBeneficioDate());
                    //logger.debug(linsif012[i].getFechaTerminoBeneficioDate());
                }
                return linsif012;
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

    /**
     * Funcion que obtiene los datos para ser mostrados en la pantalla de
     * modificacion, desde la tabla sif012todo. Dicha funcion filtra por rut
     * causante y su respectivo idsif012
     */
    public static LinSif012VO[] obtenerDatosPorRutAfiliadoSif012Id(String rut, String id) {

        List datos = null;
        Sif012VO sif012 = new Sif012VO();
        LinSif012VO[] linsif012 = null;
        LinSif012VO[] result = new LinSif012VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif012.setRutSearch(Long.parseLong(rut));
        sif012.setIdsif012(Long.parseLong(id));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosPorRutAfiliadoSif012Id", sif012);

            if (datos != null && datos.size() > 0) {

                linsif012 = (LinSif012VO[]) datos.toArray(new LinSif012VO[datos.size()]);
                for (int i = 0; i < linsif012.length; i++) {

                    String fechaInicial = Long.toString(linsif012[i].getFecha_inicio_benef());
                    String fechaFinal = Long.toString(linsif012[i].getFecha_termino_benef());
                    String fechaIn = fechaInicial.substring(6, 8) + "/" + fechaInicial.substring(4, 6) + "/" + fechaInicial.substring(0, 4);
                    String fechaFin = fechaFinal.substring(6, 8) + "/" + fechaFinal.substring(4, 6) + "/" + fechaFinal.substring(0, 4);

                    linsif012[i].setFechaInicioBeneficioDate(fechaIn);
                    linsif012[i].setFechaTerminoBeneficioDate(fechaFin);

                    //logger.debug(linsif012[i].getFechaInicioBeneficioDate());
                    //logger.debug(linsif012[i].getFechaTerminoBeneficioDate());
                }
                return linsif012;
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

    /** Funcion que obtiene los datos filtrados por rut causante */
    public static LinSif012VO[] obtenerDatosPorRutCausanteSif012Id(String rut, String id) {

        List datos = null;
        Sif012VO sif012 = new Sif012VO();
        LinSif012VO[] linsif012 = null;
        LinSif012VO[] result = new LinSif012VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif012.setRutSearch(Long.parseLong(rut));
        sif012.setIdsif012(Long.parseLong(id));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosPorRutCausanteSif012Id", sif012);

            if (datos != null && datos.size() > 0) {

                linsif012 = (LinSif012VO[]) datos.toArray(new LinSif012VO[datos.size()]);
                for (int i = 0; i < linsif012.length; i++) {

                    String fechaInicial = Long.toString(linsif012[i].getFecha_inicio_benef());
                    String fechaFinal = Long.toString(linsif012[i].getFecha_termino_benef());
                    String fechaIn = fechaInicial.substring(6, 8) + "/" + fechaInicial.substring(4, 6) + "/" + fechaInicial.substring(0, 4);
                    String fechaFin = fechaFinal.substring(6, 8) + "/" + fechaFinal.substring(4, 6) + "/" + fechaFinal.substring(0, 4);

                    linsif012[i].setFechaInicioBeneficioDate(fechaIn);
                    linsif012[i].setFechaTerminoBeneficioDate(fechaFin);

                    //logger.debug(linsif012[i].getFechaInicioBeneficioDate());
                    //logger.debug(linsif012[i].getFechaTerminoBeneficioDate());
                }
                return linsif012;
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

    /**
     * Funcion que obtiene los datos por correlativo desde la tabla sif012todo
     * para ser mostrados desde la pantalla de modificacion. Como la funcion
     * debe devolver un registro unico, recibe como parametro el idsif012 en
     * particular, seleccionado desde la grilla de modificacion
     */
    public static LinSif012VO[] obtenerDatosPorCorrelativoIDSif012(String id) {

        logger.debug("INI : obtenerDatosPorCorrelativoIDSif012");
        List datos = null;
        Sif012VO sif012 = new Sif012VO();
        LinSif012VO[] linsif012 = null;
        LinSif012VO[] result = new LinSif012VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif012.setIdsif012(Long.parseLong(id));

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.selectCorrelativoSif012Id", sif012);

            if (datos != null && datos.size() > 0) {

                linsif012 = (LinSif012VO[]) datos.toArray(new LinSif012VO[datos.size()]);
                for (int i = 0; i < linsif012.length; i++) {

                    String fechaInicial = Long.toString(linsif012[i].getFecha_inicio_benef());
                    String fechaFinal = Long.toString(linsif012[i].getFecha_termino_benef());
                    String fechaEmision = Long.toString(linsif012[i].getFech_emision_doc());
                    String fechaIn = "";
                    String fechaFin = "";
                    String fechaEmi = "";
                    if (fechaInicial != null && fechaInicial.length() == 8) {
                        fechaIn = fechaInicial.substring(6, 8) + "/" + fechaInicial.substring(4, 6) + "/" + fechaInicial.substring(0, 4);
                    }
                    if (fechaFinal != null && fechaFinal.length() == 8) {
                        fechaFin = fechaFinal.substring(6, 8) + "/" + fechaFinal.substring(4, 6) + "/" + fechaFinal.substring(0, 4);
                    }
                    if (fechaEmision != null && fechaEmision.length() == 8) {
                        fechaEmi = fechaEmision.substring(6, 8) + "/" + fechaEmision.substring(4, 6) + "/" + fechaEmision.substring(0, 4);
                    }
                    linsif012[i].setFechaInicioBeneficioDate(fechaIn);
                    linsif012[i].setFechaTerminoBeneficioDate(fechaFin);
                    linsif012[i].setFechaEmisionDocumentoDate(fechaEmi);

                    //logger.debug("RESULTADO : " + linsif012[i].toString());
                }
                logger.debug("FIN : obtenerDatosPorCorrelativoIDSif012");
                return linsif012;
            } else {
                logger.debug("FIN : obtenerDatosPorCorrelativoIDSif012");

                return result;
            }

        } catch (SQLException e) {
            logger.debug("ERROR : " + e.getMessage());
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                logger.debug("ERROR : " + e.getMessage());

                e.printStackTrace();
            }

        }
        logger.debug("FIN : obtenerDatosPorCorrelativoIDSif012");

        return result;
    }

    public static LinSif011VO[] obtenerDatosPorRutEmpresaId(String rut, String idSif011) {

        List datos = null;
        Sif011VO sif011vo = new Sif011VO();
        LinSif011VO[] linSif011 = null;
        LinSif011VO[] result = new LinSif011VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif011vo.setRutSearch(Long.parseLong(rut));
        sif011vo.setIdsif011(Long.parseLong(idSif011));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosPorRutEmpresaId", sif011vo);

            if (datos != null && datos.size() > 0) {

                linSif011 = (LinSif011VO[]) datos.toArray(new LinSif011VO[datos.size()]);
                for (int i = 0; i < linSif011.length; i++) {

                    String fechaInicial = Long.toString(linSif011[i].getFecha_inicio_benef());
                    String fechaFinal = Long.toString(linSif011[i].getFecha_termino_benef());
                    String fechaIn = fechaInicial.substring(6, 8) + "/" + fechaInicial.substring(4, 6) + "/" + fechaInicial.substring(0, 4);
                    String fechaFin = fechaFinal.substring(6, 8) + "/" + fechaFinal.substring(4, 6) + "/" + fechaFinal.substring(0, 4);

                    linSif011[i].setFechaInicioBeneficioDate(fechaIn);
                    linSif011[i].setFechaTerminoBeneficioDate(fechaFin);

                    //logger.debug(linSif011[i].getFechaInicioBeneficioDate());
                    //logger.debug(linSif011[i].getFechaTerminoBeneficioDate());
                }
                return linSif011;
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

    /** Funcion que obtiene data de la tabla sif011 filtrando por rut de afiliado */
    public static LinSif011VO[] obtenerDatosPorRutAfiliado(String rut) {

        List datos = null;
        Sif011VO sif011vo = new Sif011VO();
        LinSif011VO[] linSif011 = null;
        LinSif011VO[] result = new LinSif011VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif011vo.setRutSearch(Long.parseLong(rut));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosPorRutAfiliado", sif011vo);

            if (datos != null && datos.size() > 0) {

                linSif011 = (LinSif011VO[]) datos.toArray(new LinSif011VO[datos.size()]);

                for (int i = 0; i < linSif011.length; i++) {

                    String montoBeneficio = Helper.separadorDeMiles(Long.toString(linSif011[i].getMonto_beneficio()));

                    linSif011[i].setMontoBeneficioMiles(montoBeneficio);
                }

                return linSif011;
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

    public static LinSif011VO[] obtenerDatosPorRutAfiliadoId(String rut, String id) {

        List datos = null;
        Sif011VO sif011vo = new Sif011VO();
        LinSif011VO[] linSif011 = null;
        LinSif011VO[] result = new LinSif011VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif011vo.setRutSearch(Long.parseLong(rut));
        sif011vo.setIdsif011(Long.parseLong(id));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosPorRutAfiliadoId", sif011vo);

            if (datos != null && datos.size() > 0) {

                linSif011 = (LinSif011VO[]) datos.toArray(new LinSif011VO[datos.size()]);

                for (int i = 0; i < linSif011.length; i++) {

                    String fechaInicial = Long.toString(linSif011[i].getFecha_inicio_benef());
                    String fechaFinal = Long.toString(linSif011[i].getFecha_termino_benef());
                    String fechaIn = fechaInicial.substring(6, 8) + "/" + fechaInicial.substring(4, 6) + "/" + fechaInicial.substring(0, 4);
                    String fechaFin = fechaFinal.substring(6, 8) + "/" + fechaFinal.substring(4, 6) + "/" + fechaFinal.substring(0, 4);

                    linSif011[i].setFechaInicioBeneficioDate(fechaIn);
                    linSif011[i].setFechaTerminoBeneficioDate(fechaFin);

                    //logger.debug(linSif011[i].getFechaInicioBeneficioDate());
                    //logger.debug(linSif011[i].getFechaTerminoBeneficioDate());

                    String montoBeneficio = Helper.separadorDeMiles(Long.toString(linSif011[i].getMonto_beneficio()));
                    linSif011[i].setMontoBeneficioMiles(montoBeneficio);
                }

                return linSif011;
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

    public static LinSif011VO[] busquedaPorRangoSif011(String id, String rangoUno, String rangoDos) {

        List datos = null;
        Sif011VO sif011 = new Sif011VO();
        LinSif011VO[] result = null;
        LinSif011VO[] linsif011 = new LinSif011VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif011.setRangoUno(Long.parseLong(rangoUno));
        sif011.setRangoDos(Long.parseLong(rangoDos));

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.selectRangoSif011", sif011);

            if (datos != null && datos.size() > 0) {

                linsif011 = (LinSif011VO[]) datos.toArray(new LinSif011VO[datos.size()]);
                for (int i = 0; i < linsif011.length; i++) {
                    String montoBeneficio = Helper.separadorDeMiles(Long.toString(linsif011[i].getMonto_beneficio()));
                    linsif011[i].setMontoBeneficioMiles(montoBeneficio);
                }
                return linsif011;
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

    /**
     * Busca la data estatica del reporte sif011 cuando la busqueda es por
     * correlativo
     */
    public static LinSif011VO[] dataEstaticaPorId(String id_1, String id_2) {

        List datos = null;
        Sif011VO sif011 = new Sif011VO();
        LinSif011VO[] result = null;
        LinSif011VO[] linsif011 = new LinSif011VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif011.setRangoUno(Long.parseLong(id_1));
        sif011.setRangoDos(Long.parseLong(id_2));

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.selectEstaticaSif011Id", sif011);

            if (datos != null && datos.size() > 0) {

                linsif011 = (LinSif011VO[]) datos.toArray(new LinSif011VO[datos.size()]);
                return linsif011;
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

    /**
     * Funcion que obtiene los datos a mostrar para ser modificados en
     * sif011todo.
     */
    public static LinSif011VO[] obtenerDatosPorCorrelativoId(String correlativo) {
        List datos = null;
        Sif011VO sif011 = new Sif011VO();
        LinSif011VO[] result = null;
        LinSif011VO[] linsif011 = new LinSif011VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif011.setIdsif011(Long.parseLong(correlativo));

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.selectCorrelativoSif011Id", sif011);

            if (datos != null && datos.size() > 0) {

                linsif011 = (LinSif011VO[]) datos.toArray(new LinSif011VO[datos.size()]);
                for (int i = 0; i < linsif011.length; i++) {

                    String fechaInicial = Long.toString(linsif011[i].getFecha_inicio_benef());
                    String fechaFinal = Long.toString(linsif011[i].getFecha_termino_benef());
                    String fechaEmision = Long.toString(linsif011[i].getFecha_emision_documento());

                    String fechaIn = "0";
                    String fechaFin = "0";
                    String fechaEmi = "0";

                    if (fechaInicial != null && fechaInicial.length() == 8) {
                        fechaIn = fechaInicial.substring(6, 8) + "/" + fechaInicial.substring(4, 6) + "/" + fechaInicial.substring(0, 4);
                    }
                    if (fechaFinal != null && fechaFinal.length() == 8) {
                        fechaFin = fechaFinal.substring(6, 8) + "/" + fechaFinal.substring(4, 6) + "/" + fechaFinal.substring(0, 4);
                    }
                    if (fechaEmision != null && fechaEmision.length() == 8) {
                        fechaEmi = fechaEmision.substring(6, 8) + "/" + fechaEmision.substring(4, 6) + "/" + fechaEmision.substring(0, 4);
                    }

                    linsif011[i].setFechaInicioBeneficioDate(fechaIn);
                    linsif011[i].setFechaTerminoBeneficioDate(fechaFin);
                    linsif011[i].setFechaEmisionDocumentoDate(fechaEmi);

                    //logger.debug(linsif011[i].getFechaInicioBeneficioDate());
                    //logger.debug(linsif011[i].getFechaTerminoBeneficioDate());

                    String codigoBarra = "";
                    String dvAfiliado = "";
                    String dvCausante = "";
                    String dvEmpresa = "";
                    String fechaEmisionDocumentoDate = "";
                    String fechaInicioBeneficioDate = "";
                    String fechaTerminoBeneficioDate = "";
                    String montoBeneficioMiles = "";
                    String nombreAfiliado = "";
                    String nombreCausante = "";
                    String nombreEmpresa = "";
                    String numeroDocumento = "";
                    String numeroSerie = "";

                    if (linsif011[i].getCodigo_barra() != null) {
                        codigoBarra = linsif011[i].getCodigo_barra().trim();
                    }
                    if (linsif011[i].getDv_afiliado() != null) {
                        dvAfiliado = linsif011[i].getDv_afiliado().trim();
                    }
                    if (linsif011[i].getDv_causante() != null) {
                        dvCausante = linsif011[i].getDv_causante().trim();
                    }
                    if (linsif011[i].getDv_empresa() != null) {
                        dvEmpresa = linsif011[i].getDv_empresa().trim();
                    }
                    if (linsif011[i].getFechaEmisionDocumentoDate() != null) {
                        fechaEmisionDocumentoDate = linsif011[i].getFechaEmisionDocumentoDate().trim();
                    }
                    if (linsif011[i].getFechaInicioBeneficioDate() != null) {
                        fechaInicioBeneficioDate = linsif011[i].getFechaInicioBeneficioDate().trim();
                    }
                    if (linsif011[i].getFechaTerminoBeneficioDate() != null) {
                        fechaTerminoBeneficioDate = linsif011[i].getFechaTerminoBeneficioDate().trim();
                    }
                    if (linsif011[i].getMontoBeneficioMiles() != null) {
                        montoBeneficioMiles = linsif011[i].getMontoBeneficioMiles().trim();
                    }
                    if (linsif011[i].getNombre_afiliado() != null) {
                        nombreAfiliado = linsif011[i].getNombre_afiliado().trim();
                    }
                    if (linsif011[i].getNombre_causante() != null) {
                        nombreCausante = linsif011[i].getNombre_causante().trim();
                    }
                    if (linsif011[i].getNombre_empresa() != null) {
                        nombreEmpresa = linsif011[i].getNombre_empresa().trim();
                    }
                    if (linsif011[i].getNumero_documento() != null) {
                        numeroDocumento = linsif011[i].getNumero_documento().trim();
                    }
                    if (linsif011[i].getNumero_serie() != null) {
                        numeroSerie = linsif011[i].getNumero_serie().trim();
                    }

                    linsif011[i].setCodigo_barra(codigoBarra);
                    linsif011[i].setDv_afiliado(dvAfiliado);
                    linsif011[i].setDv_causante(dvCausante);
                    linsif011[i].setDv_empresa(dvEmpresa);
                    linsif011[i].setFechaEmisionDocumentoDate(fechaEmisionDocumentoDate);
                    linsif011[i].setFechaInicioBeneficioDate(fechaInicioBeneficioDate);
                    linsif011[i].setFechaTerminoBeneficioDate(fechaTerminoBeneficioDate);
                    linsif011[i].setMontoBeneficioMiles(montoBeneficioMiles);
                    linsif011[i].setNombre_afiliado(nombreAfiliado);
                    linsif011[i].setNombre_causante(nombreCausante);
                    linsif011[i].setNombre_empresa(nombreEmpresa);
                    linsif011[i].setNumero_documento(numeroDocumento);
                    linsif011[i].setNumero_serie(numeroSerie);

                }
                return linsif011;
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

    /**
     * Funcion que elimina registro individual de la tabla sif011todo, lo que
     * hace es hacer una eliminacion logica del registro haciendo un update a la
     * tabla sif011 todo en el registro flgdlt
     */
    public static RespuestaVO eliminarRegistroIndividualSif011(String id) {

        RespuestaVO respuesta = new RespuestaVO();
        Sif011VO sif011 = new Sif011VO();

        sif011.setRangoUno(Long.parseLong(id));
        respuesta.setCodRespuesta(99);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            sqlMap.update("editarDivisionPrevisionalSiv.deleteSif011", sif011);

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

    /** Funcion que elimina registro individual por rut afiliado */
    public static RespuestaVO eliminarRegistroAfiliadoSif011(String id) {

        RespuestaVO respuesta = new RespuestaVO();
        Sif011VO sif011 = new Sif011VO();

        sif011.setRangoUno(Long.parseLong(id));
        respuesta.setCodRespuesta(99);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            sqlMap.update("editarDivisionPrevisionalSiv.deleteAfiliadoSif011", sif011);

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

    /** Funcion que elimina un registro dada una busqueda por correlativo */
    public static RespuestaVO eliminarRegistroCorrelativoSif011(String id) {

        RespuestaVO respuesta = new RespuestaVO();
        Sif011VO sif011 = new Sif011VO();

        sif011.setRangoUno(Long.parseLong(id));
        respuesta.setCodRespuesta(99);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            sqlMap.update("editarDivisionPrevisionalSiv.deleteCorrelativoSif011", sif011);

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

    public static LinSif012VO[] obtenerDatosSif012PorRutEmpresa(String rut) {

        List datos = null;
        Sif012VO sif012vo = new Sif012VO();
        LinSif012VO[] linSif012 = null;
        LinSif012VO[] result = new LinSif012VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif012vo.setRutSearch(Long.parseLong(rut));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosSif012PorRutEmpresa", sif012vo);

            if (datos != null && datos.size() > 0) {

                linSif012 = (LinSif012VO[]) datos.toArray(new LinSif012VO[datos.size()]);

                for (int i = 0; i < linSif012.length; i++) {

                    String montoBeneficio = Helper.separadorDeMiles(Long.toString(linSif012[i].getMonto_beneficio()));
                    linSif012[i].setMontoBeneficioMiles(montoBeneficio);

                }
                return linSif012;
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

    /** Funcion que obtiene data de la tabla sif011 filtrando por rut de afiliado */
    public static LinSif012VO[] obtenerDatosSif012PorRutAfiliado(String rut) {

        List datos = null;
        Sif012VO sif012vo = new Sif012VO();
        LinSif012VO[] linSif012 = null;
        LinSif012VO[] result = new LinSif012VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif012vo.setRutSearch(Long.parseLong(rut));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosSif012PorRutAfiliado", sif012vo);

            if (datos != null && datos.size() > 0) {

                linSif012 = (LinSif012VO[]) datos.toArray(new LinSif012VO[datos.size()]);

                for (int i = 0; i < linSif012.length; i++) {

                    String montoBeneficio = Helper.separadorDeMiles(Long.toString(linSif012[i].getMonto_beneficio()));
                    linSif012[i].setMontoBeneficioMiles(montoBeneficio);

                }

                return linSif012;
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

    /**
     * Funcion que obtiene la data de la tabla sif012todo filtrado por rut
     * causante
     */
    public static LinSif012VO[] obtenerDatosSif012PorRutCaucante(String rut) {

        List datos = null;
        Sif012VO sif012vo = new Sif012VO();
        LinSif012VO[] linSif012 = null;
        LinSif012VO[] result = new LinSif012VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif012vo.setRutSearch(Long.parseLong(rut));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosSif012PorRutCausante", sif012vo);

            if (datos != null && datos.size() > 0) {

                linSif012 = (LinSif012VO[]) datos.toArray(new LinSif012VO[datos.size()]);

                for (int i = 0; i < linSif012.length; i++) {

                    String montoBeneficio = Helper.separadorDeMiles(Long.toString(linSif012[i].getMonto_beneficio()));
                    linSif012[i].setMontoBeneficioMiles(montoBeneficio);

                }
                return linSif012;
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

    /**
     * Implementacion de busqueda por correlativo. Realiza un filtro de los
     * datos sobre la tabla sif012todo por rango, correspondiente al idsif012
     */
    public static LinSif012VO[] busquedaPorRangoSif012(String idSelectedItem, String primerRango, String segundoRango) {

        List datos = null;
        Sif012VO sif012vo = new Sif012VO();
        LinSif012VO[] linSif012 = null;
        LinSif012VO[] result = new LinSif012VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif012vo.setRangoUno(Long.parseLong(primerRango));
        sif012vo.setRangoDos(Long.parseLong(segundoRango));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosSif012PorCorrelativo", sif012vo);

            if (datos != null && datos.size() > 0) {

                linSif012 = (LinSif012VO[]) datos.toArray(new LinSif012VO[datos.size()]);

                for (int i = 0; i < linSif012.length; i++) {

                    String montoBeneficio = Helper.separadorDeMiles(Long.toString(linSif012[i].getMonto_beneficio()));
                    linSif012[i].setMontoBeneficioMiles(montoBeneficio);

                }

                return linSif012;
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

    /** Funcion que busca por rango para actualizar la grilla */
    public static LinSif012VO[] actualizarGrillaCorrelativoSif012(String min, String max) {

        List datos = null;
        Sif012VO sif012vo = new Sif012VO();
        LinSif012VO[] linSif012 = null;
        LinSif012VO[] result = new LinSif012VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif012vo.setRangoUno(Long.parseLong(min));
        sif012vo.setRangoDos(Long.parseLong(max));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosSif012PorCorrelativo", sif012vo);

            if (datos != null && datos.size() > 0) {

                linSif012 = (LinSif012VO[]) datos.toArray(new LinSif012VO[datos.size()]);

                for (int i = 0; i < linSif012.length; i++) {

                    String montoBeneficio = Helper.separadorDeMiles(Long.toString(linSif012[i].getMonto_beneficio()));
                    linSif012[i].setMontoBeneficioMiles(montoBeneficio);

                }

                return linSif012;
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

    /** Funcion que obtiene la data estatica por rut empresa */
    public static LinSif012VO[] obtenerDataEstaticaRutEmpresa(String rut) {

        List datos = null;
        Sif012VO sif012vo = new Sif012VO();
        LinSif012VO[] linSif012 = null;
        LinSif012VO[] result = new LinSif012VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif012vo.setRutSearch(Long.parseLong(rut));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosSif012EstaticaRutEmpresa", sif012vo);

            if (datos != null && datos.size() > 0) {

                linSif012 = (LinSif012VO[]) datos.toArray(new LinSif012VO[datos.size()]);
                //logDatos(linSif012);
                return linSif012;
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

    /** funcion que obtiene la data estatica filtrada por rut afiliado */
    public static LinSif012VO[] obtenerDataEstaticaRutAfiliado(String rut) {

        List datos = null;
        Sif012VO sif012vo = new Sif012VO();
        LinSif012VO[] linSif012 = null;
        LinSif012VO[] result = new LinSif012VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif012vo.setRutSearch(Long.parseLong(rut));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosSif012EstaticaRutAfiliado", sif012vo);

            if (datos != null && datos.size() > 0) {

                linSif012 = (LinSif012VO[]) datos.toArray(new LinSif012VO[datos.size()]);
                //logDatos(linSif012);
                return linSif012;
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

    /** funcion que obtiene la data estatica filtrada por rut causante */
    public static LinSif012VO[] obtenerDataEstaticaRutCausante(String rut) {

        List datos = null;
        Sif012VO sif012vo = new Sif012VO();
        LinSif012VO[] linSif012 = null;
        LinSif012VO[] result = new LinSif012VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif012vo.setRutSearch(Long.parseLong(rut));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosSif012EstaticaRutCausante", sif012vo);

            if (datos != null && datos.size() > 0) {

                linSif012 = (LinSif012VO[]) datos.toArray(new LinSif012VO[datos.size()]);
                //logDatos(linSif012);
                return linSif012;
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

    /** Funcion que obtiene la data estatica de la tabla sif012vo */
    public static LinSif012VO[] dataEstaticaPorIdSif012(String rangoUno, String rangoDos) {
        List datos = null;
        Sif012VO sif012vo = new Sif012VO();
        LinSif012VO[] linSif012 = null;
        LinSif012VO[] result = new LinSif012VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif012vo.setRangoUno(Long.parseLong(rangoUno));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosSif012Estatica", sif012vo);

            if (datos != null && datos.size() > 0) {

                linSif012 = (LinSif012VO[]) datos.toArray(new LinSif012VO[datos.size()]);
                //logDatos(linSif012);
                return linSif012;
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

    /**
     * Implementacion de la funcion que realiza el update sobre los campos
     * modificables de la tabla sif012todo
     */
    public static RespuestaVO updateSif012(LinSif012VO linsif012) {

        RespuestaVO respuesta = new RespuestaVO();
        respuesta.setCodRespuesta(99);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            sqlMap.update("editarDivisionPrevisionalSiv.updateSif012", linsif012);

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

    /** Elimina un registro de la grilla, cuando se filtra por rut de empresa */
    public static RespuestaVO eliminarRegistroSif012PorRutEmpresa(String id, String rut) {

        RespuestaVO respuesta = new RespuestaVO();
        Sif012VO vo = new Sif012VO();
        respuesta.setCodRespuesta(99);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        vo.setRangoUno(Long.parseLong(id));
        vo.setRutSearch(Long.parseLong(rut));

        try {

            sqlMap.startTransaction(0);

            sqlMap.update("editarDivisionPrevisionalSiv.deleteSif012", vo);

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

    /** Elimina un registro de la grilla, cuando se filtra por rut de afiliado */
    public static RespuestaVO eliminarRegistroSif012PorRutAfiliado(String id, String rut) {

        RespuestaVO respuesta = new RespuestaVO();
        Sif012VO vo = new Sif012VO();
        respuesta.setCodRespuesta(99);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        vo.setRangoUno(Long.parseLong(id));
        vo.setRutSearch(Long.parseLong(rut));

        try {

            sqlMap.startTransaction(0);

            sqlMap.update("editarDivisionPrevisionalSiv.deleteSif012", vo);

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

    /** Elimina un registro de la grilla, cuando se filtra por rut de causante */
    public static RespuestaVO eliminarRegistroSif012PorRutCausante(String id, String rut) {

        RespuestaVO respuesta = new RespuestaVO();
        Sif012VO vo = new Sif012VO();
        respuesta.setCodRespuesta(99);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        vo.setRangoUno(Long.parseLong(id));
        vo.setRutSearch(Long.parseLong(rut));

        try {

            sqlMap.startTransaction(0);

            sqlMap.update("editarDivisionPrevisionalSiv.deleteSif012", vo);

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

    /** Elimina un registro de la grilla, cuando se filtra por rut de causante */
    public static RespuestaVO eliminarRegistroSif012porCorrelativo(String id) {

        RespuestaVO respuesta = new RespuestaVO();
        Sif012VO vo = new Sif012VO();
        respuesta.setCodRespuesta(99);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        vo.setRangoUno(Long.parseLong(id));

        try {

            sqlMap.startTransaction(0);

            sqlMap.update("editarDivisionPrevisionalSiv.deleteSif012", vo);

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

    /** *************************************************************************** */
    /**
     * ***** FUNCIONES QUE IMPLEMENTAN FUNCIONALIDADES DE SIF014
     * ****************
     */
    /**
     * Funcion que obtiene la data de la tabla sif014 filtrado por rut de
     * empresa.
     */
    public static LinSif014VO[] obtenerDatosSif014PorRutEmpresa(String rut) {

        List datos = null;
        Sif014VO sif014vo = new Sif014VO();
        LinSif014VO[] linSif014 = null;
        LinSif014VO[] result = new LinSif014VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif014vo.setRutSearch(Long.parseLong(rut));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosSif014PorRutEmpresa", sif014vo);

            if (datos != null && datos.size() > 0) {

                linSif014 = (LinSif014VO[]) datos.toArray(new LinSif014VO[datos.size()]);
                return linSif014;
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

    /** Funcion que obtiene data de la tabla sif014 filtrando por rut de afiliado */
    public static LinSif014VO[] obtenerDatosSif014PorRutBeneficiario(String rut) {

        List datos = null;
        Sif014VO sif014vo = new Sif014VO();
        LinSif014VO[] linSif014 = null;
        LinSif014VO[] result = new LinSif014VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif014vo.setRutSearch(Long.parseLong(rut));
        //logger.debug(rut);
        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosSif014PorRutBeneficiario", sif014vo);

            if (datos != null && datos.size() > 0) {

                linSif014 = (LinSif014VO[]) datos.toArray(new LinSif014VO[datos.size()]);
                return linSif014;
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

    /** Funcion que obtiene data de la tabla sif014 filtrando por rut causante */
    public static LinSif014VO[] obtenerDatosSif014PorRutCausante(String rut) {

        List datos = null;
        Sif014VO sif014vo = new Sif014VO();
        LinSif014VO[] linSif012 = null;
        LinSif014VO[] result = new LinSif014VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif014vo.setRutSearch(Long.parseLong(rut));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosSif014PorRutCausante", sif014vo);

            if (datos != null && datos.size() > 0) {

                linSif012 = (LinSif014VO[]) datos.toArray(new LinSif014VO[datos.size()]);
                return linSif012;
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

    /** Funcion que obtiene la data estatica filtrada por rut empresa */
    public static LinSif014VO[] obtenerDataEstaticaSif014PorRutEmpresa(String rut) {

        List datos = null;
        Sif014VO sif014vo = new Sif014VO();
        LinSif014VO[] linSif012 = null;
        LinSif014VO[] result = new LinSif014VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif014vo.setRutSearch(Long.parseLong(rut));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosSif014EstaticosRutEmpresa", sif014vo);

            if (datos != null && datos.size() > 0) {

                linSif012 = (LinSif014VO[]) datos.toArray(new LinSif014VO[datos.size()]);
                return linSif012;
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

    /** Funcion que obtiene la data estatica filtrado por rut beneficiario */
    public static LinSif014VO[] obtenerDataEstaticaSif014PorRutBeneficiario(String rut) {

        List datos = null;
        Sif014VO sif014vo = new Sif014VO();
        LinSif014VO[] linSif012 = null;
        LinSif014VO[] result = new LinSif014VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif014vo.setRutSearch(Long.parseLong(rut));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosSif014EstaticosRutBeneficiario", sif014vo);

            if (datos != null && datos.size() > 0) {

                linSif012 = (LinSif014VO[]) datos.toArray(new LinSif014VO[datos.size()]);
                return linSif012;
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

    /** Funcion que obtiene la data estatica filtrado por rut causante */
    public static LinSif014VO[] obtenerDataEstaticaSif014PorRutCausante(String rut) {

        List datos = null;
        Sif014VO sif014vo = new Sif014VO();
        LinSif014VO[] linSif012 = null;
        LinSif014VO[] result = new LinSif014VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif014vo.setRutSearch(Long.parseLong(rut));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosSif014EstaticosRutCausante", sif014vo);

            if (datos != null && datos.size() > 0) {

                linSif012 = (LinSif014VO[]) datos.toArray(new LinSif014VO[datos.size()]);
                return linSif012;
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

    /**
     * Funcion que realiza el update a la tabla sif014todo. recibe como
     * parametro una lista tipo linsif014vo que contiene la data a modificar, la
     * cual es identificable con el idsif014
     */
    public static RespuestaVO updateSif014(LinSif014VO vo) {

        RespuestaVO respuesta = new RespuestaVO();
        respuesta.setCodRespuesta(99);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            sqlMap.update("editarDivisionPrevisionalSiv.updateSif014", vo);

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

    /**
     * Funcion que obtiene la data que va a ser editada, filtrado por rur de
     * empresa.
     */
    public static LinSif014VO[] obtenerDatosPorRutEmpresaSif014Id(String rut, String id) {

        List datos = null;
        Sif014VO sif014 = new Sif014VO();
        LinSif014VO[] linsif014 = null;
        LinSif014VO[] result = new LinSif014VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif014.setRutSearch(Long.parseLong(rut));
        sif014.setIdsif014(Long.parseLong(id));

        //logger.debug(id);
        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosPorRutEmpresaSif014Id", sif014);

            if (datos != null && datos.size() > 0) {

                linsif014 = (LinSif014VO[]) datos.toArray(new LinSif014VO[datos.size()]);

                for (int i = 0; i < linsif014.length; i++) {

                    String fechaInicial = Long.toString(linsif014[i].getInicio_period_reinte());
                    String fechaFinal = Long.toString(linsif014[i].getFinal_period_reinte());
                    String fechaIn = fechaInicial.substring(6, 8) + "/" + fechaInicial.substring(4, 6) + "/" + fechaInicial.substring(0, 4);
                    String fechaFin = fechaFinal.substring(6, 8) + "/" + fechaFinal.substring(4, 6) + "/" + fechaFinal.substring(0, 4);

                    linsif014[i].setInicioPeriodoReintegroDate(fechaIn);
                    linsif014[i].setFinalPeriodoReintegro(fechaFin);

                }

                return linsif014;
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

    /**
     * Funcion que obtiene la data que va a ser editada, filtrada por rut
     * beneficiario
     */
    public static LinSif014VO[] obtenerDatosPorRutBeneficiarioSif014Id(String rut, String id) {

        List datos = null;
        Sif014VO sif014 = new Sif014VO();
        LinSif014VO[] linsif014 = null;
        LinSif014VO[] result = new LinSif014VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif014.setRutSearch(Long.parseLong(rut));
        sif014.setIdsif014(Long.parseLong(id));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosPorRutBeneficiarioSif014Id", sif014);

            if (datos != null && datos.size() > 0) {

                linsif014 = (LinSif014VO[]) datos.toArray(new LinSif014VO[datos.size()]);

                for (int i = 0; i < linsif014.length; i++) {

                    String fechaInicial = Long.toString(linsif014[i].getInicio_period_reinte());
                    String fechaFinal = Long.toString(linsif014[i].getFinal_period_reinte());
                    String fechaIn = fechaInicial.substring(6, 8) + "/" + fechaInicial.substring(4, 6) + "/" + fechaInicial.substring(0, 4);
                    String fechaFin = fechaFinal.substring(6, 8) + "/" + fechaFinal.substring(4, 6) + "/" + fechaFinal.substring(0, 4);

                    linsif014[i].setInicioPeriodoReintegroDate(fechaIn);
                    linsif014[i].setFinalPeriodoReintegro(fechaFin);

                }

                return linsif014;
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

    /**
     * Funcion que obtiene la data que va a ser editada, filtrada por rut
     * causante
     */
    public static LinSif014VO[] obtenerDatosPorRutCausanteSif014Id(String rut, String id) {

        List datos = null;
        Sif014VO sif014 = new Sif014VO();
        LinSif014VO[] linsif014 = null;
        LinSif014VO[] result = new LinSif014VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif014.setRutSearch(Long.parseLong(rut));
        sif014.setIdsif014(Long.parseLong(id));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosPorRutCausanteSif014Id", sif014);

            if (datos != null && datos.size() > 0) {

                linsif014 = (LinSif014VO[]) datos.toArray(new LinSif014VO[datos.size()]);

                for (int i = 0; i < linsif014.length; i++) {

                    String fechaInicial = Long.toString(linsif014[i].getInicio_period_reinte());
                    String fechaFinal = Long.toString(linsif014[i].getFinal_period_reinte());
                    String fechaIn = fechaInicial.substring(6, 8) + "/" + fechaInicial.substring(4, 6) + "/" + fechaInicial.substring(0, 4);
                    String fechaFin = fechaFinal.substring(6, 8) + "/" + fechaFinal.substring(4, 6) + "/" + fechaFinal.substring(0, 4);

                    linsif014[i].setInicioPeriodoReintegroDate(fechaIn);
                    linsif014[i].setFinalPeriodoReintegro(fechaFin);

                }

                return linsif014;
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

    /**
     * Funcion que obtiene la data que va a ser editada, dado un filtro por
     * correlativo
     */
    public static LinSif014VO[] obtenerDatosPorCorrelativoIDSif014(String id) {

        List datos = null;
        Sif014VO sif012 = new Sif014VO();
        LinSif014VO[] linsif014 = null;
        LinSif014VO[] result = new LinSif014VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif012.setIdsif014(Long.parseLong(id));

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.selectCorrelativoSif014Id", sif012);

            if (datos != null && datos.size() > 0) {

                linsif014 = (LinSif014VO[]) datos.toArray(new LinSif014VO[datos.size()]);

                for (int i = 0; i < linsif014.length; i++) {

                    String fechaIn = "";
                    String fechaFin = "";
                    String fechaInicial = Long.toString(linsif014[i].getInicio_period_reinte());
                    String fechaFinal = Long.toString(linsif014[i].getFinal_period_reinte());
                    if (fechaInicial != null && fechaInicial.length() == 8) {
                        fechaIn = fechaInicial.substring(6, 8) + "/" + fechaInicial.substring(4, 6) + "/" + fechaInicial.substring(0, 4);
                    }

                    if (fechaFinal != null && fechaFinal.length() == 8) {
                        fechaFin = fechaFinal.substring(6, 8) + "/" + fechaFinal.substring(4, 6) + "/" + fechaFinal.substring(0, 4);
                    }
                    linsif014[i].setInicioPeriodoReintegroDate(fechaIn);
                    linsif014[i].setFinalPeriodoReintegro(fechaFin);

                }

                return linsif014;
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

    public static LinSif014VO[] busquedaPorRangoSif014(String min, String max) {

        List datos = null;
        Sif014VO sif014vo = new Sif014VO();
        LinSif014VO[] linSif014 = null;
        LinSif014VO[] result = new LinSif014VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif014vo.setRangoUno(Long.parseLong(min));
        sif014vo.setRangoDos(Long.parseLong(max));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosSif014PorCorrelativo", sif014vo);

            if (datos != null && datos.size() > 0) {

                linSif014 = (LinSif014VO[]) datos.toArray(new LinSif014VO[datos.size()]);

                return linSif014;
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

    public static LinSif014VO[] obtenerDataEstaticaSif014PorCorrelativo(String min, String max) {

        List datos = null;
        Sif014VO sif014vo = new Sif014VO();
        LinSif014VO[] linSif012 = null;
        LinSif014VO[] result = new LinSif014VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif014vo.setRangoUno(Long.parseLong(min));
        sif014vo.setRangoDos(Long.parseLong(max));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDatosSif014EstaticosRutCorrelativo", sif014vo);

            if (datos != null && datos.size() > 0) {

                linSif012 = (LinSif014VO[]) datos.toArray(new LinSif014VO[datos.size()]);
                return linSif012;
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

    /**
     * Funcino que elimina un registro especifico de la grilla, dado su id y su
     * rut. El filtro usado en la grilla es rut empresa
     */
    public static RespuestaVO eliminarRegistroSif014PorRutEmpresa(String id, String rut) {

        RespuestaVO respuesta = new RespuestaVO();
        Sif014VO vo = new Sif014VO();
        respuesta.setCodRespuesta(99);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        vo.setRangoUno(Long.parseLong(id));
        vo.setRutSearch(Long.parseLong(rut));

        try {

            sqlMap.startTransaction(0);

            sqlMap.update("editarDivisionPrevisionalSiv.deleteSif014", vo);

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

    /**
     * Funcion que elimina un registro especifico de la grilla dado un filtro
     * por rut beneficiario. Recibe como parametro el id del registro y el rut
     * beneficiario correspondiente.
     */
    public static RespuestaVO eliminarRegistroSif014PorRutAfiliado(String id, String rut) {

        RespuestaVO respuesta = new RespuestaVO();
        Sif014VO vo = new Sif014VO();
        respuesta.setCodRespuesta(99);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        vo.setRangoUno(Long.parseLong(id));
        vo.setRutSearch(Long.parseLong(rut));

        try {

            sqlMap.startTransaction(0);

            sqlMap.update("editarDivisionPrevisionalSiv.deleteSif014", vo);

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

    /**
     * Funcion que elimina un registro de la grilla dado un filtro por rut
     * causante. Recibe como parametros el id del registro y su respectivo rut
     * causante
     */
    public static RespuestaVO eliminarRegistroSif014PorRutCausante(String id, String rut) {

        RespuestaVO respuesta = new RespuestaVO();
        Sif014VO vo = new Sif014VO();
        respuesta.setCodRespuesta(99);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        vo.setRangoUno(Long.parseLong(id));
        vo.setRutSearch(Long.parseLong(rut));

        try {

            sqlMap.startTransaction(0);

            sqlMap.update("editarDivisionPrevisionalSiv.deleteSif014", vo);

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

    /**
     * Funcion que elimina un registro de la grilla dado un filtro por
     * correlativo. Recibe como parametro el id correlativo del registro que se
     * requiere eliminar.
     */
    public static RespuestaVO eliminarRegistroSif014porCorrelativo(String id) {

        RespuestaVO respuesta = new RespuestaVO();
        Sif014VO vo = new Sif014VO();
        respuesta.setCodRespuesta(99);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        vo.setRangoUno(Long.parseLong(id));

        try {

            sqlMap.startTransaction(0);

            sqlMap.update("editarDivisionPrevisionalSiv.deleteSif014", vo);

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

    /** ******************************************************************************************** */
    /*
     * Funciones que eliminan registros de las tablas relacionadas a reportes de
     * division previsional
     */
    public static RespuestaVO deleteDivisionPrevisionalConRango(String idReporte, String rangoUno, String rangoDos) {

        List datos = null;
        int contador;
        Sif011VO sif011 = new Sif011VO();
        Sif012VO sif012 = new Sif012VO();
        Sif014VO sif014 = new Sif014VO();
        RespuestaVO respuesta = new RespuestaVO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            switch (Integer.parseInt(idReporte)) {

            case 11:

                sif011.setRangoUno(Long.parseLong(rangoUno));
                sif011.setRangoDos(Long.parseLong(rangoDos));

                datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.verificarRangoUnoDos011", sif011);

                if (datos != null && datos.size() > 0) {
                    contador = Integer.parseInt((String) datos.get(0));
                    if (contador == 0) {
                        respuesta.setCodRespuesta(99);
                    } else {
                        sqlMap.update("editarDivisionPrevisionalSiv.deleteRangoSif011", sif011);
                        respuesta.setCodRespuesta(0);
                    }
                }

                break;

            case 12:
                sif012.setRangoUno(Long.parseLong(rangoUno));
                sif012.setRangoDos(Long.parseLong(rangoDos));

                datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.verificarRangoUnoDos012", sif012);

                if (datos != null && datos.size() > 0) {
                    contador = Integer.parseInt((String) datos.get(0));
                    if (contador == 0) {
                        respuesta.setCodRespuesta(99);
                    } else {
                        sqlMap.update("editarDivisionPrevisionalSiv.deleteRangoSif012", sif012);
                        respuesta.setCodRespuesta(0);
                    }
                }
                break;

            case 14:
                sif014.setRangoUno(Long.parseLong(rangoUno));
                sif014.setRangoDos(Long.parseLong(rangoDos));

                datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.verificarRangoUnoDos014", sif014);

                if (datos != null && datos.size() > 0) {
                    contador = Integer.parseInt((String) datos.get(0));
                    if (contador == 0) {
                        respuesta.setCodRespuesta(99);
                    } else {
                        sqlMap.update("editarDivisionPrevisionalSiv.deleteRangoSif014", sif014);
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

    public static RespuestaVO deleteDivisionPrevisionalSinRango(String idReporte, String rangoUno) {

        int contador;
        List datos = null;
        Sif011VO sif011 = new Sif011VO();
        Sif012VO sif012 = new Sif012VO();
        Sif014VO sif014 = new Sif014VO();
        RespuestaVO respuesta = new RespuestaVO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            switch (Integer.parseInt(idReporte)) {
            case 11:
                sif011.setRangoUno(Long.parseLong(rangoUno));
                datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.verificarSinRango011", sif011);

                if (datos != null && datos.size() > 0) {
                    contador = Integer.parseInt((String) datos.get(0));
                    if (contador == 0) {
                        respuesta.setCodRespuesta(99);
                    } else {
                        sqlMap.delete("editarDivisionPrevisionalSiv.deleteSif011", sif011);
                        respuesta.setCodRespuesta(0);
                    }
                }

                break;

            case 12:
                sif012.setRangoUno(Long.parseLong(rangoUno));
                datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.verificarSinRango012", sif012);

                if (datos != null && datos.size() > 0) {
                    contador = Integer.parseInt((String) datos.get(0));
                    if (contador == 0) {
                        respuesta.setCodRespuesta(99);
                    } else {
                        sqlMap.delete("editarDivisionPrevisionalSiv.deleteSif012", sif012);
                        respuesta.setCodRespuesta(0);
                    }
                }

                break;

            case 14:
                sif014.setRangoUno(Long.parseLong(rangoUno));
                datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.verificarSinRango014", sif014);

                if (datos != null && datos.size() > 0) {
                    contador = Integer.parseInt((String) datos.get(0));
                    if (contador == 0) {
                        respuesta.setCodRespuesta(99);
                    } else {
                        sqlMap.delete("editarDivisionPrevisionalSiv.deleteSif014", sif014);
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

    /**
     * Funcion que hace el update a la tabla sif011todo. Recibe como parametro
     * una lista con los datos de la tabla que se van a updatear La funcion
     * retorna un 0 si los datos fueron modificados de manera exitosa, de lo
     * contrario, retorna un valor 99 indicando que hubo problemas al realizar
     * algun cambio en la data de la tabla.
     */
    public static RespuestaVO updateSif011(LinSif011VO linSif011) {

        RespuestaVO respuesta = new RespuestaVO();
        respuesta.setCodRespuesta(99);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            sqlMap.update("editarDivisionPrevisionalSiv.updateSif011", linSif011);

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

    public static LinSif011VO[] obtenerEstaticosRut(String rut) {

        List datos = null;
        Sif011VO sif011 = new Sif011VO();
        LinSif011VO[] linSif = new LinSif011VO[0];
        LinSif011VO[] result = null;

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        sif011.setRutSearch(Long.parseLong(rut));
        try {
            sqlMap.startTransaction(0);

            datos = sqlMap.queryForList("editarDivisionPrevisionalSiv.obtenerDataEstSif011RutEmp", sif011);

            if (datos != null && datos.size() > 0) {

                linSif = (LinSif011VO[]) datos.toArray(new LinSif011VO[datos.size()]);
                //for (int i = 0; i < linSif.length; i++) {
                //  logger.debug("" + linSif[i].getFecha_proceso());
                //}
                return linSif;

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

    //    private static void logDatos(LinSif012VO[] linSif012) {
    //    for (int i = 0; i < linSif012.length; i++) {
    //        logger.debug("trae info: " + linSif012[i].getCodigo_archivo());
    //  }

    //}
}
