package cl.araucana.sivegam.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import cl.araucana.sivegam.config.ConfiguracionSqlMap;
import cl.araucana.sivegam.helper.Helper;
import cl.araucana.sivegam.helper.IND_Constants;
import cl.araucana.sivegam.vo.CausalReliquidacionVO;
import cl.araucana.sivegam.vo.CodigoArchivoVO;
import cl.araucana.sivegam.vo.CodigoBancoVO;
import cl.araucana.sivegam.vo.CodigoErrorVO;
import cl.araucana.sivegam.vo.CodigoTramoVO;
import cl.araucana.sivegam.vo.DominioVO;
import cl.araucana.sivegam.vo.EstadoDelDocumentoVO;
import cl.araucana.sivegam.vo.FormatoReporteVO;
import cl.araucana.sivegam.vo.ModalidadDePagoVO;
import cl.araucana.sivegam.vo.PeriodoProcesoVO;
import cl.araucana.sivegam.vo.ProcesosAFCCesantiaVO;
import cl.araucana.sivegam.vo.StatusProcesoVO;
import cl.araucana.sivegam.vo.TipoArchivoVO;
import cl.araucana.sivegam.vo.TipoBeneficiarioVO;
import cl.araucana.sivegam.vo.TipoBeneficioVO;
import cl.araucana.sivegam.vo.TipoCausanteVO;
import cl.araucana.sivegam.vo.TipoDeclaracionVO;
import cl.araucana.sivegam.vo.TipoEgresoVO;
import cl.araucana.sivegam.vo.TipoEmisionVO;
import cl.araucana.sivegam.vo.TipoOrigenVO;
import cl.araucana.sivegam.vo.TipoProcesosVO;
import cl.araucana.sivegam.vo.TipoReintegroVO;
import cl.araucana.sivegam.vo.TipoReporteVO;
import cl.araucana.sivegam.vo.TipoSaldoVO;
import cl.araucana.sivegam.vo.UsuariosSivegamVO;
import cl.araucana.sivegam.vo.ValoresConexionAS400VO;
import cl.araucana.sivegam.vo.param.Parametro;

import com.ibatis.sqlmap.client.SqlMapClient;

/* Implementación de la clase ParametrosDAO.
 * Posee la implementación de la comunicación entre la aplicación y la base de datos, mediante un intermediario xml
 * que contiene el mapeo de todas las consultas.
 * */
public class ParametrosDAO {

    /*
     * Función que obtiene los datos parametricos de la base de datos. Recibe
     * como entrada el tipo de parametro al que se desea acceder retorna un
     * arreglo de parametros con la informacion requerida.
     */
    public static Parametro[] obtenerParametroList(String tipo) {

        List datos = null;
        Parametro[] salida = new Parametro[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_SIVEGAM));
            parametros.put("input", tipo);
            datos = sqlMap.queryForList("parametrosNS.obtenerParametricos", parametros);

            return (Parametro[]) datos.toArray(new Parametro[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return salida;
    }

    public static String obtenerParametroUnico(String tipo) {

        String resultado = "";
        Parametro[] lista = obtenerParametroList(tipo);

        resultado = lista[0].getGlosa();

        return resultado;
    }

    /*
     * Función que obtiene la fecha del sistema. Retorna la fecha parseada para
     * ser usada en el sistema.
     */
    public static String obtenerFechaSistema() {

        List datos = null;
        String salida = "";
        String fecha = "";
        Date date = new Date();

        String DATE_FORMAT1 = "dd/MM/yy";
        String DATE_FORMAT2 = "dd/MM/yyyy";
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            datos = sqlMap.queryForList("parametrosNS.obtenerFechaSistema");

            fecha = (String) datos.get(0);
            date = sdf1.parse(fecha);
            salida = sdf2.format(date);

            return salida;

        } catch (SQLException e) {
            e.printStackTrace();
            salida = "34404";
        } catch (ParseException e) {
            e.printStackTrace();
            salida = "34404";
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
                salida = "34404";
            }

        }
        return salida;
    }

    /*
     * Función que obtiene los perfiles Recibe como entrada un id. Retorna el
     * perfil asociado al id.
     */
    public static String obtenerPerfil(String ID) {

        List datos = null;
        String salida = "0";

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_SIVEGAM));
            parametros.put("input", ID);
            datos = sqlMap.queryForList("parametrosNS.obtenerPerfil", parametros);

            if (datos != null && datos.size() > 0) {

                salida = (String) datos.get(0);
            }

            return salida;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return salida;
    }

    /*
     * Funcion que obtiene los datos parametricos de los tipos de procesos
     * pertenecientes a sivegam.
     */
    public static TipoProcesosVO[] obtenerTipoProcesos() {

        List datos = null;
        TipoProcesosVO[] tipoProceso = new TipoProcesosVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            datos = sqlMap.queryForList("parametrosNS.obtenerTipoProcesos");

            return (TipoProcesosVO[]) datos.toArray(new TipoProcesosVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return tipoProceso;

    }

    /*
     * Funcion que obtiene los datos parametricos de los tipos de status del
     * proceso
     */
    public static StatusProcesoVO[] obtenerStatusProcesos() {
        List datos = null;
        StatusProcesoVO[] statusProceso = new StatusProcesoVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectStatusProceso");
            return (StatusProcesoVO[]) datos.toArray(new StatusProcesoVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return statusProceso;
    }

    public static StatusProcesoVO[] obtenerStatusProcesoCarga() {
        List datos = null;
        StatusProcesoVO[] statusProceso = new StatusProcesoVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectStatusProcesoCarga");
            return (StatusProcesoVO[]) datos.toArray(new StatusProcesoVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return statusProceso;
    }

    /*
     * Funcion que obtiene los datos parametricos de los periodos de los
     * procesos.
     */
    public static PeriodoProcesoVO[] obtenerPeridosProcesos() {
        List datos = null;
        PeriodoProcesoVO[] periodoProcesos = new PeriodoProcesoVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectPeriodoProceso");
            return (PeriodoProcesoVO[]) datos.toArray(new PeriodoProcesoVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return periodoProcesos;
    }

    /*
     * Funcion que obtiene los datos parametricos de los formatos de los
     * reportes.
     */
    public static FormatoReporteVO[] obtenerFormatoReportes() {
        List datos = null;
        FormatoReporteVO[] formatoReportes = new FormatoReporteVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectFormatoReporte");
            return (FormatoReporteVO[]) datos.toArray(new FormatoReporteVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return formatoReportes;
    }

    /* Funcion que obtiene los datos parametricos para LOS TIPOS DE REPORTE. */
    public static TipoReporteVO[] obtenerTipoReportes() {
        List datos = null;
        TipoReporteVO[] tipoReportes = new TipoReporteVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectTipoReporte");
            return (TipoReporteVO[]) datos.toArray(new TipoReporteVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return tipoReportes;
    }

    /*
     * Funcion que obtiene los datos parametricos de los valores de conexion
     * para una maquina AS400.
     */
    public static ValoresConexionAS400VO[] obtenerValoresConexionAS400() {
        List datos = null;
        ValoresConexionAS400VO[] valoresConexion = new ValoresConexionAS400VO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectValoresConexion");
            return (ValoresConexionAS400VO[]) datos.toArray(new ValoresConexionAS400VO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return valoresConexion;
    }

    /* Funcion que obtiene la data de la tabla tipo origen */
    public static TipoOrigenVO[] obtenerTipoOrigen() {

        List datos = null;
        TipoOrigenVO[] tipoOrigen = new TipoOrigenVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectTipoOrigen");
            return (TipoOrigenVO[]) datos.toArray(new TipoOrigenVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return tipoOrigen;

    }

    /* Funcion que obtiene los datos parametricos de los usuarios sivegam */
    /* Funcion que obtiene los datos parametricos de los tipos de perfil */

    /** Funciones que obtienen los datos de las tablas parametricas de SIVEGAM. */
    /* Funcion que obtiene la data de la tabla tipo beneficiario */
    public static TipoBeneficiarioVO[] obtenerTipoBeneficiario() {
        List datos = null;
        TipoBeneficiarioVO[] tipoBeneficiario = new TipoBeneficiarioVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectTipoBeneficiario");
            return (TipoBeneficiarioVO[]) datos.toArray(new TipoBeneficiarioVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return tipoBeneficiario;
    }

    /* funcion que obtiene los datos de la tabla tipo causante */
    public static TipoCausanteVO[] obtenerTipoCausante() {

        List datos = null;
        TipoCausanteVO[] tipoCausante = new TipoCausanteVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectTipoCausante");
            return (TipoCausanteVO[]) datos.toArray(new TipoCausanteVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return tipoCausante;
    }

    /* funcion que obtiene la data de la tabla tipo beneficio */
    public static TipoBeneficioVO[] obtenerTipoBeneficio() {

        List datos = null;
        TipoBeneficioVO[] tipoBeneficio = new TipoBeneficioVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectTipoBeneficio");
            return (TipoBeneficioVO[]) datos.toArray(new TipoBeneficioVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tipoBeneficio;
    }

    /* funcion que obtiene la data de la tabla codigo archivo */
    public static CodigoArchivoVO[] obtenerCodigoArchivo() {

        List datos = null;
        CodigoArchivoVO[] codigoArchivo = new CodigoArchivoVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectCodigoArchivo");
            return (CodigoArchivoVO[]) datos.toArray(new CodigoArchivoVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return codigoArchivo;
    }

    /* funcion que obtiene la data de la tabla tipo emision */
    public static TipoEmisionVO[] obtenerTipoEmision() {

        List datos = null;
        TipoEmisionVO[] tipoEmision = new TipoEmisionVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectTipoEmision");
            return (TipoEmisionVO[]) datos.toArray(new TipoEmisionVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tipoEmision;
    }

    /* funcion que obtiene la data de la tabla tipo declaracion */
    public static TipoDeclaracionVO[] obtenerTipoDeclaracion() {

        List datos = null;
        TipoDeclaracionVO[] tipoDeclaracion = new TipoDeclaracionVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectTipoDeclaracion");
            return (TipoDeclaracionVO[]) datos.toArray(new TipoDeclaracionVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tipoDeclaracion;
    }

    /* funcion que obtiene la data de la tabla tipo reintegro */
    public static TipoReintegroVO[] obtenerTipoReintegro() {

        List datos = null;
        TipoReintegroVO[] tipoReintegro = new TipoReintegroVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectTipoReintegro");
            return (TipoReintegroVO[]) datos.toArray(new TipoReintegroVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tipoReintegro;
    }

    /* funcion que obtiene la data de la tabla tipo saldo */
    public static TipoSaldoVO[] obtenerTipoSaldo() {

        List datos = null;
        TipoSaldoVO[] tipoSaldo = new TipoSaldoVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectTipoSaldo");
            return (TipoSaldoVO[]) datos.toArray(new TipoSaldoVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tipoSaldo;
    }

    /* funcion que obtiene la data de la tabla causal reliquidacion */
    public static CausalReliquidacionVO[] obtenerCausalReliquidacion() {

        List datos = null;
        CausalReliquidacionVO[] causalReliquidacion = new CausalReliquidacionVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectCausalReliquidacion");
            return (CausalReliquidacionVO[]) datos.toArray(new CausalReliquidacionVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return causalReliquidacion;
    }

    /* funcion que obtiene la data de la tabla tipo egreso */
    public static TipoEgresoVO[] obtenerTipoEgreso() {

        List datos = null;
        TipoEgresoVO[] tipoEgreso = new TipoEgresoVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectTipoEgreso");
            return (TipoEgresoVO[]) datos.toArray(new TipoEgresoVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tipoEgreso;
    }

    /* FUNCION QUE OBTIENE LA DATA DE LA TABLA MODALIDAD DE PAGO. */
    public static ModalidadDePagoVO[] obtenerModalidadDePago() {

        List datos = null;
        ModalidadDePagoVO[] modPago = new ModalidadDePagoVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectModalidadPago");
            return (ModalidadDePagoVO[]) datos.toArray(new ModalidadDePagoVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return modPago;

    }

    /* funcion que obtiene la data de la tabla codigo de banco. */
    public static CodigoBancoVO[] obtenerCodigoBanco() {

        List datos = null;
        CodigoBancoVO[] codigoBanco = new CodigoBancoVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectCodigoBanco");
            return (CodigoBancoVO[]) datos.toArray(new CodigoBancoVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return codigoBanco;

    }

    /* funcion que obtiene la data de la tabla estado del documento */
    public static EstadoDelDocumentoVO[] obtenerEstadoDocumento() {

        List datos = null;
        EstadoDelDocumentoVO[] estadoDocumento = new EstadoDelDocumentoVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectEstadoDocumento");
            return (EstadoDelDocumentoVO[]) datos.toArray(new EstadoDelDocumentoVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return estadoDocumento;
    }

    /** Funcion que obtiene la data de la tabla tipo de archivo */
    public static TipoArchivoVO[] obtenerTipoArchivo() {

        List datos = null;
        TipoArchivoVO[] tipoArchivo = new TipoArchivoVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectTipoArchivo");
            return (TipoArchivoVO[]) datos.toArray(new TipoArchivoVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tipoArchivo;
    }

    /**
     * Funcion que obtiene los datos de la tabla Procesos afc cesantia, para su
     * parametrizacion.
     */
    public static ProcesosAFCCesantiaVO[] obtenerProcesosAfcCesantia() {

        List datos = null;
        ProcesosAFCCesantiaVO[] procesosAfcCesantia = new ProcesosAFCCesantiaVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectProcesosAfcCesantia");
            return (ProcesosAFCCesantiaVO[]) datos.toArray(new ProcesosAFCCesantiaVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return procesosAfcCesantia;
    }

    /**
     * Funcion que obtiene los datos de la tabla codigo tramo, para su
     * parametrizacion.
     */
    public static CodigoTramoVO[] obtenerCodigoTramo() {

        List datos = null;
        CodigoTramoVO[] codigoTramo = new CodigoTramoVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectCodigoTramo");
            return (CodigoTramoVO[]) datos.toArray(new CodigoTramoVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return codigoTramo;
    }

    /**
     * Funcion que obtiene los datos de la tabla errores, para su
     * parametrizacion.
     */
    public static CodigoErrorVO[] obtenerCodigoError() {
        List datos = null;
        CodigoErrorVO[] codigoError = new CodigoErrorVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectCodigoError");
            return (CodigoErrorVO[]) datos.toArray(new CodigoErrorVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return codigoError;
    }

    /**
     * Funcion que obtiene los datos de la tabla dominio, para su validacion de
     * reproceso.
     */
    public static DominioVO[] obtenerDominio() {
        List datos = null;
        DominioVO[] dominio = new DominioVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectDominio");
            return (DominioVO[]) datos.toArray(new DominioVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dominio;
    }

    /**
     * Funcion que obtiene los datos de la tabla errores, para su
     * parametrizacion.
     */
    public static CodigoErrorVO[] obtenerCodigoErrorAFC() {
        List datos = null;
        CodigoErrorVO[] codigoError = new CodigoErrorVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectCodigoErrorAFC");
            return (CodigoErrorVO[]) datos.toArray(new CodigoErrorVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return codigoError;
    }

    /**
     * Funcion que obtiene los datos de la tabla errores, para su
     * parametrizacion.
     */
    public static UsuariosSivegamVO[] obtenerUsuarios() {
        List datos = null;
        UsuariosSivegamVO[] usuarios = new UsuariosSivegamVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("parametrosNS.selectUsuariosSivegam");
            return (UsuariosSivegamVO[]) datos.toArray(new UsuariosSivegamVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return usuarios;
    }

}
