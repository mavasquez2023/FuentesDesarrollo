package cl.araucana.sivegam.struts.dwr.actions;

import java.io.IOException;
import java.sql.SQLException;

import cl.araucana.sivegam.helper.GlobalProperties;
import cl.araucana.sivegam.helper.IND_Constants;
import cl.araucana.sivegam.impl.EditarReporteCotizacionesImpl;
import cl.araucana.sivegam.vo.CausalReliquidacionVO;
import cl.araucana.sivegam.vo.CodigoArchivoVO;
import cl.araucana.sivegam.vo.CodigoBancoVO;
import cl.araucana.sivegam.vo.EstadoDelDocumentoVO;
import cl.araucana.sivegam.vo.LinSif016VO;
import cl.araucana.sivegam.vo.LinSif017VO;
import cl.araucana.sivegam.vo.LinSif018VO;
import cl.araucana.sivegam.vo.LinSif019VO;
import cl.araucana.sivegam.vo.ModalidadDePagoVO;
import cl.araucana.sivegam.vo.PeriodoProcesoVO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.Sif016VO;
import cl.araucana.sivegam.vo.Sif017VO;
import cl.araucana.sivegam.vo.Sif018VO;
import cl.araucana.sivegam.vo.Sif019VO;
import cl.araucana.sivegam.vo.TipoBeneficiarioVO;
import cl.araucana.sivegam.vo.TipoBeneficioVO;
import cl.araucana.sivegam.vo.TipoCausanteVO;
import cl.araucana.sivegam.vo.TipoDeclaracionVO;
import cl.araucana.sivegam.vo.TipoEgresoVO;
import cl.araucana.sivegam.vo.TipoEmisionVO;
import cl.araucana.sivegam.vo.TipoProcesosVO;
import cl.araucana.sivegam.vo.TipoReintegroVO;
import cl.araucana.sivegam.vo.TipoSaldoVO;
import cl.araucana.sivegam.vo.param.ListadoParametros;

public class EditarReporteCotizacionesDWR {

    static GlobalProperties global = GlobalProperties.getInstance();

    /**
     * *************** FUNCIONES QUE IMPLEMENTAN FUNCIONALIDADES DEL REPORTE
     * SIF016 ************************
     */
    /**
     * Funcion que obtiene data de la tabla SIF016 a partir de un rut
     * perteneciente a dicha tabla.
     */
    public Sif016VO obtenerDatosSif016PorRut(String rut) {

        EditarReporteCotizacionesImpl impl = new EditarReporteCotizacionesImpl();
        Sif016VO vo = new Sif016VO();

        try {

            vo = impl.obtenerDatosSif016PorRut(rut);

            return vo;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException f) {
            f.printStackTrace();
        }

        return vo;
    }

    /** Funcion que hace el update a la tabla sif016a2 */
    public RespuestaVO updateSif016(String id, String rutEmpresa, String dvEmpresa, String nombreEmpresa, String numeroDeclaracion, String fechaDeclaracion,
            String numeroTotalTrabajador, String numeroTotalCargas, String cargasRetroactivas, String montoAsfamMes, String montoAsfamRetro, String montoReintegroMes,
            String totalPagoAsigFam, String totalCotizacion, String otrosDescuentos, String resultadoNeto, String tipoSaldo, String modalidadPago, String montoDocumento,
            String numeroSerie, String numeroDocumento, String fechaEmision, String codigoBanco) {

        LinSif016VO linsif016 = new LinSif016VO();
        String fechDeclaracion = "0";
        String fechEmision = "0";
        if (fechaDeclaracion != null && fechaDeclaracion.length() == 10) {
            fechDeclaracion = fechaDeclaracion.substring(6, 10) + fechaDeclaracion.substring(3, 5) + fechaDeclaracion.substring(0, 2);
        }
        if (fechaEmision != null && fechaEmision.length() == 10) {
            fechEmision = fechaEmision.substring(6, 10) + fechaEmision.substring(3, 5) + fechaEmision.substring(0, 2);
        }
        linsif016.setId_sif016(Long.parseLong(id));
        linsif016.setRut_empresa(Long.parseLong(rutEmpresa));
        linsif016.setDv_empresa(dvEmpresa);
        linsif016.setNombre_empresa(nombreEmpresa);
        linsif016.setNumero_declaracion(numeroDeclaracion);
        linsif016.setFech_declaracion(Long.parseLong(fechDeclaracion));
        linsif016.setNum_total_trabajador(Integer.parseInt(numeroTotalTrabajador));
        linsif016.setNum_total_cargas(Integer.parseInt(numeroTotalCargas));
        linsif016.setCargas_retroactivas(Integer.parseInt(cargasRetroactivas));
        linsif016.setMto_asfam_mes(Long.parseLong(montoAsfamMes));
        linsif016.setMto_asfam_mes_retro(Long.parseLong(montoAsfamRetro));
        linsif016.setMto_reintegros_mes(Long.parseLong(montoReintegroMes));
        linsif016.setTotal_pago_asigfam(Long.parseLong(totalPagoAsigFam));
        linsif016.setTotal_de_cotizacion(Long.parseLong(totalCotizacion));
        linsif016.setOtros_descuentos(Long.parseLong(otrosDescuentos));
        linsif016.setResultado_neto(Long.parseLong(resultadoNeto));
        linsif016.setTipo_de_saldo(Integer.parseInt(tipoSaldo));
        linsif016.setModalidad_pago(Integer.parseInt(modalidadPago));
        try {
            linsif016.setMonto_documento(Long.parseLong(montoDocumento));
        } catch (NumberFormatException nfe) {
            linsif016.setMonto_documento(0);
        }
        linsif016.setNumero_serie(numeroSerie);
        linsif016.setNumero_documento(numeroDocumento);
        linsif016.setFech_emision_doc(Long.parseLong(fechEmision));
        linsif016.setCodigo_banco(Integer.parseInt(codigoBanco));

        return EditarReporteCotizacionesImpl.updateSif016(linsif016);

    }

    /**
     * Funcion que obtiene los datos modificados por rut, para actualizar la
     * grilla una vez que se hizo el update.
     */
    public Sif016VO obtenerDatosModificadosPorRut016(String id, String rut) {

        return EditarReporteCotizacionesImpl.obtenerDatosModificadosSif016(id, rut);
    }

    /**
     * Funcion que obtiene los datos modificados, dada una busqueda por rango,
     * para actualizar la grilla
     */
    public Sif016VO obtenerDatosModificadosPorRango016(String id, String uno, String dos) {

        return EditarReporteCotizacionesImpl.obtenerDatosModificadosSif016Rango(uno, dos);
    }

    public Sif016VO busquedaPorRangoSif016(String id, String rangoUno, String rangoDos) {

        return EditarReporteCotizacionesImpl.busquedaPorRangoSif016(rangoUno, rangoDos);
    }

    public Sif016VO dataEstaticaPorIdSif016(String rangoUno, String rangoDos) {

        return EditarReporteCotizacionesImpl.dataEstaticaPorIdSif016(rangoUno);
    }

    public Sif016VO obtenerDatosSif016ParaEditar(String rut, String id, String idSelected) {

        return EditarReporteCotizacionesImpl.obtenerDatosSif016ParaEditar(rut, id, idSelected);
    }

    public Sif016VO obtenerEstaticosPorRutSif016(String rut) {

        return EditarReporteCotizacionesImpl.obtenerEstaticosPorRutSif016(rut);
    }

    public static RespuestaVO eliminarRegistroIndividualSif016(String id) {

        return EditarReporteCotizacionesImpl.eliminarRegistroIndividualSif016(id);
    }

    public RespuestaVO eliminarRegistroCorrelativoSif016(String id) {

        return EditarReporteCotizacionesImpl.eliminarRegistroCorrelativoSif016(id);
    }

    public Sif016VO obtenerDatosPorRut016(String id, String rut) {

        return EditarReporteCotizacionesImpl.obtenerDatosPorRut016(rut);
    }

    public Sif016VO actualizarPorCorrelativo016(String max, String min) {

        return EditarReporteCotizacionesImpl.actualizarPorCorrelativo016(max, min);
    }

    /** ***************************************************************************************** */
    /**
     * *************** FUNCIONES QUE IMPLEMENTAN FUNCIONALIDADES DE SIF017
     * *********************
     */
    /**
     * Funcion que obtiene data de la tabla sif017 a partir de un rut en
     * particular.
     */
    public Sif017VO obtenerDatosSif017PorRut(String rut) {

        EditarReporteCotizacionesImpl impl = new EditarReporteCotizacionesImpl();
        Sif017VO vo = new Sif017VO();

        try {

            vo = impl.obtenerDatosSif017PorRut(rut);

            return vo;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException f) {
            f.printStackTrace();
        }

        return vo;
    }

    /**
     * Funcion que obtiene los datos `para ser editados. Discrimina por registro
     * unico, por tanto recibe rut y su respectivo idsif017
     */
    public static Sif017VO obtenerDatosSif017ParaEditar(String rut, String id, String idSelected) {

        return EditarReporteCotizacionesImpl.obtenerDatosSif017ParaEditar(rut, id, idSelected);

    }

    /**
     * Funcion que utiliza el update de los campos modificados a la tabla
     * sif017. Su funcion es setear los campos que seran modificados.
     */
    public RespuestaVO updateSif017(String id, String mesInformado, String rut, String dvEmpresa, String nombreEmpresa, String codTipoEgreso, String modalidadPago,
            String montoDocumento, String numeroSerie, String numeroDocumento, String fechaEmisionDocumento, String codigoBanco, String numeroCartola, String estadoDocumento,
            String fechaRendicion) {
        LinSif017VO linsif017 = new LinSif017VO();

        String fechaEmision = "0";
        String fechaRendic = "0";
        String entidad = global.getValor("SVG.entidad");
        String codArchivo = global.getValor("SVG.cod.sif017");

        if (fechaEmisionDocumento != null && fechaEmisionDocumento.length() == 10) {
            fechaEmision = fechaEmisionDocumento.substring(6, 10) + fechaEmisionDocumento.substring(3, 5) + fechaEmisionDocumento.substring(0, 2);
        }
        if (fechaRendicion != null && fechaRendicion.length() == 10) {
            fechaRendic = fechaRendicion.substring(6, 10) + fechaRendicion.substring(3, 5) + fechaRendicion.substring(0, 2);
        }
        linsif017.setRut_empresa(Long.parseLong(rut));
        linsif017.setDv_empresa(dvEmpresa);
        linsif017.setId_sif017(Long.parseLong(id));
        linsif017.setNombre_empresa(nombreEmpresa);
        linsif017.setCod_tipo_egreso(Integer.parseInt(codTipoEgreso));
        linsif017.setModalidad_de_pago(Integer.parseInt(modalidadPago));
        linsif017.setMonto_documento(Long.parseLong(montoDocumento));
        linsif017.setNumero_serie(numeroSerie);
        linsif017.setNumero_documento(numeroDocumento);
        linsif017.setFech_emision_doc(Long.parseLong(fechaEmision));
        linsif017.setCodigo_banco(Integer.parseInt(codigoBanco));
        linsif017.setNumero_cartola(numeroCartola);
        linsif017.setEstado_documento(Integer.parseInt(estadoDocumento));
        linsif017.setFecha_rendicion(Long.parseLong(fechaRendic));

        linsif017.setCodigo_entidad(Integer.parseInt(entidad));
        linsif017.setCodigo_archivo(Integer.parseInt(codArchivo));
        linsif017.setFecha_proceso(Integer.parseInt(mesInformado));

        return EditarReporteCotizacionesImpl.updateSif017(linsif017);
    }

    /**
     * funcion que consulta datos a la tabla sif017 filtrados por rut y
     * correlativo, dependiendo del la seleccion de busqueda que se haya elegido
     * en el formulario, luego de haber modificado los datos.
     */
    public Sif017VO obtenerDatosModificadosPorRut(String idSelected, String rut) {

        return EditarReporteCotizacionesImpl.obtenerDatosModificadosSif017(idSelected, rut);
    }

    /**
     * Funcion que consulta los datos modificados por rango, para actualizar la
     * grilla luego de haber efectuado una busqueda y haber modificado algunos
     * de los registros de forma individual.
     */
    public Sif017VO obtenerDatosModificadosPorRango(String id, String rango1, String rango2) {

        return EditarReporteCotizacionesImpl.obtenerDatosModificadosSif017Rango(rango1, rango2);
    }

    public Sif017VO busquedaPorRangoSif017(String id, String rangoUno, String rangoDos) {

        return EditarReporteCotizacionesImpl.busquedaPorRangoSif017(rangoUno, rangoDos);
    }

    public Sif017VO dataEstaticaPorIdSif017(String rangoUno, String rangoDos) {

        return EditarReporteCotizacionesImpl.dataEstaticaPorIdSif017(rangoUno);
    }

    public static Sif017VO obtenerEstaticosPorRutSif017(String rut) {

        return EditarReporteCotizacionesImpl.obtenerEstaticosPorRutSif017(rut);
    }

    public static RespuestaVO eliminarRegistroIndividualSif017(String id) {

        return EditarReporteCotizacionesImpl.eliminarRegistroIndividualSif017(id);
    }

    public static RespuestaVO eliminarRegistroCorrelativoSif017(String id) {

        return EditarReporteCotizacionesImpl.eliminarRegistroCorrelativoSif017(id);
    }

    public Sif017VO actualizarPorCorrelativo(String a, String b) {

        return EditarReporteCotizacionesImpl.actualizarPorCorrelativo(a, b);
    }

    public Sif017VO obtenerDatosPorRut017(String id, String rut) {

        return EditarReporteCotizacionesImpl.obtenerDatosPorRut017(rut);
    }

    /** ****************************************************************************************** */

    /**
     * ************* FUNCIONES QUE IMPLEMENTAN FUNCIONALIDADES DEL REPORTE
     * SIF018.*********************
     */
    public Sif018VO obtenerDatosPorRut(String rut) {

        EditarReporteCotizacionesImpl impl = new EditarReporteCotizacionesImpl();
        Sif018VO vo = new Sif018VO();

        try {

            vo = impl.obtenerDatosPorRut(rut);
            return vo;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException f) {
            f.printStackTrace();
        }

        return vo;
    }

    public Sif018VO updateSif018(String idSif018, String codArchivo, String rutEmpresa, String dvEmpresa, String nombreEmpresa, String modalidadPago, String montoDocumento,
            String numeroSerie, String numeroDocumento, String fechaEmisionDocumento, String codigoBanco, String fechaCobro) {

        LinSif018VO listSif018 = new LinSif018VO();
        String fechaEmision = "0";
        String fechaDeCobro = "0";
        if (fechaEmisionDocumento != null && fechaEmisionDocumento.length() == 10) {
            fechaEmision = fechaEmisionDocumento.substring(6, 10) + fechaEmisionDocumento.substring(3, 5) + fechaEmisionDocumento.substring(0, 2);
        }
        if (fechaCobro != null && fechaCobro.length() == 10) {
            fechaDeCobro = fechaCobro.substring(6, 10) + fechaCobro.substring(3, 5) + fechaCobro.substring(0, 2);
        }

        listSif018.setId_sif018(Long.parseLong(idSif018));
        listSif018.setFlag_reg_modificado(Integer.parseInt(IND_Constants.SIF018_FLAG_MODIFICAR));
        listSif018.setCodigo_archivo(Integer.parseInt(codArchivo));
        listSif018.setRut_empleador(Long.parseLong(rutEmpresa));
        listSif018.setDv_empleador(dvEmpresa);
        listSif018.setNombre_empleador(nombreEmpresa);
        listSif018.setMod_pago(Integer.parseInt(modalidadPago));
        listSif018.setMonto_documento(Long.parseLong(montoDocumento));
        listSif018.setNumero_serie(numeroSerie);
        listSif018.setNumero_documento(numeroDocumento);

        listSif018.setFecha_emision_documento(Long.parseLong(fechaEmision));

        listSif018.setCodigo_banco(Integer.parseInt(codigoBanco));
        listSif018.setFecha_cobro(Long.parseLong(fechaDeCobro));

        return EditarReporteCotizacionesImpl.updateSif018(listSif018);
    }

    /** Funcion que retorna el id de un registro dado un rut de busqueda. */
    public Sif018VO obtenerIdSif018(String rutBusqueda) {

        return EditarReporteCotizacionesImpl.obtenerIdSif018(rutBusqueda);

    }

    /** Funcion que carga los campos estaticos. */
    public static Sif018VO obtenerDataEstatica(String tipoArchivo, String periodoArchivo) {
        String tipo_Archivo = "";
        String periodo_Archivo = "";
        Sif018VO sif018vo = new Sif018VO();

        ListadoParametros listaParam1 = ListadoParametros.getInstancia();
        TipoProcesosVO[] tipoProcesos = listaParam1.getListTipoProcesos();

        for (int j = 0; j < tipoProcesos.length; j++) {
            if (tipoProcesos[j].getId_tipo_proceso() == Integer.parseInt(tipoArchivo)) {
                tipo_Archivo = tipoProcesos[j].getCodigo_tipo_proceso();
                sif018vo.setTipoArchivoGlosa(tipo_Archivo);
                break;
            }
        }

        ListadoParametros listaParam2 = ListadoParametros.getInstancia();
        PeriodoProcesoVO[] periodoProcesos = listaParam2.getListPeriodoProcesos();

        for (int i = 0; i < periodoProcesos.length; i++) {
            if (periodoProcesos[i].getPeriodo_proceso() == Long.parseLong(periodoArchivo)) {
                periodo_Archivo = periodoProcesos[i].getDescripcion_periodo_proceso();
                sif018vo.setPeriodoArchivoGlosa(periodo_Archivo);
                break;
            }
        }

        sif018vo.setCodResultado(0);
        return sif018vo;
    }

    /** Funcion que obtiene data a partir del correlativo */
    public Sif018VO obtenerDatosPorCorrelativo(String id, String rangoUno, String rangoDos) {

        Sif018VO vo = new Sif018VO();

        try {

            vo = EditarReporteCotizacionesImpl.obtenerDatosPorCorrelativo(rangoUno, rangoDos);

            return vo;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException f) {
            f.printStackTrace();
        }

        return vo;
    }

    /** Funcion que obtiene la data estatica por id */
    public Sif018VO dataEstaticaPorIdSif018(String id) {

        return EditarReporteCotizacionesImpl.dataEstaticaPorIdSif018(id);
    }

    /** Funcion que obtiene la data estatioa por rut */
    public Sif018VO obtenerEstaticosPorRutSif018(String rut) {

        return EditarReporteCotizacionesImpl.obtenerEstaticosPorRutSif018(rut);
    }

    public Sif018VO obtenerDatosSif018ParaEditar(String rut, String id, String idSelected) {

        return EditarReporteCotizacionesImpl.obtenerDatosSif018ParaEditar(rut, id, idSelected);
    }

    public Sif018VO obtenerDatosModificadosPorRango018(String id, String min, String max) throws IOException, SQLException {

        return EditarReporteCotizacionesImpl.obtenerDatosPorCorrelativo(min, max);
    }

    public RespuestaVO eliminarRegistroIndividualSif018(String id) {

        return EditarReporteCotizacionesImpl.eliminarRegistroIndividualSif018(id);
    }

    public RespuestaVO eliminarRegistroCorrelativoSif018(String id) {

        return EditarReporteCotizacionesImpl.eliminarRegistroCorrelativoSif018(id);
    }

    public Sif018VO obtenerDatosPorRut018(String id, String rut) {

        return EditarReporteCotizacionesImpl.obtenerDatosPorRut018(rut);
    }

    public Sif018VO actualizarPorCorrelativo018(String min, String max) {

        return EditarReporteCotizacionesImpl.actualizarPorCorrelativo018(min, max);
    }

    /** ******************************************************************************************** */
    /**
     * ********************* FUNCIONES QUE IMPLEMENTAN FUNCIONALIDADES DE
     * SIF019N2 ********************
     */
    /**
     * Funcion que obtiene data de la tabla SIF019 a partir de un rut
     * perteneciente a dicha tabla.
     */
    public Sif019VO obtenerDatosSif019PorRut(String rut) {

        EditarReporteCotizacionesImpl impl = new EditarReporteCotizacionesImpl();
        Sif019VO vo = new Sif019VO();

        try {

            vo = impl.obtenerDatosSif019PorRut(rut);

            return vo;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException f) {
            f.printStackTrace();
        }

        return vo;
    }

    /**
     * Funcion que obtiene data de la tabla sif019N a partir de un rango
     * perteneciente a dicha tabla
     */
    public Sif019VO obtenerDatosSif019PorCorrelativo(String primerRango, String segundoRango) {

        EditarReporteCotizacionesImpl impl = new EditarReporteCotizacionesImpl();
        Sif019VO vo = new Sif019VO();

        try {

            vo = impl.obtenerDatosSif019PorCorrelativo(primerRango, segundoRango);

            return vo;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException f) {
            f.printStackTrace();
        }

        return vo;
    }

    /**
     * Funcion que obtiene data para ser modificada, dada una busqueda
     * especifica por rut con id, o id solo.
     */
    public Sif019VO obtenerDatosSif019ParaEditar(String rut, String id, String idSelected) {

        return EditarReporteCotizacionesImpl.obtenerDatosSif019ParaEditar(rut, id, idSelected);
    }

    /** Funcion que obtiene los datos modificados, para actualizar la grilla */
    public Sif019VO obtenerDatosModificadosPorRutSif019(String id, String rut) {

        return EditarReporteCotizacionesImpl.obtenerDatosModificadosPorRutSif019(rut);
    }

    public Sif019VO obtenerDatosModificadosPorRangoSif019(String id, String min, String max) {

        return EditarReporteCotizacionesImpl.obtenerDatosModificadosPorRangoSif019(min, max);
    }

    public RespuestaVO updateSif019(String idSif019_glob, String rutEmpresa, String dvEmpresa, String nombreEmpresa, String mesOrigenGasto, String estadoDocumento,
            String modoPagoOrigen, String numeroSerieOrigen, String numeroDocumentoOrigen, String montoDocumentoOrigen, String fecEmisionOrigen, String codigoBancoOrigen,
            String modoPagoNuevo, String numeroSerie, String numeroDocumento, String montoDocumento, String fecEmisionNuevo, String codigoBancoNuevo) {

        String fechaOrigen = "0";
        String fechaNuevo = "0";
        String mesOrigen = "0";
        LinSif019VO vo = new LinSif019VO();

        if (fecEmisionOrigen != null && fecEmisionOrigen.length() == 10) {
            fechaOrigen = fecEmisionOrigen.substring(6, 10) + fecEmisionOrigen.substring(3, 5) + fecEmisionOrigen.substring(0, 2);
        }

        if (fecEmisionNuevo != null && fecEmisionNuevo.length() == 10) {
            fechaNuevo = fecEmisionNuevo.substring(6, 10) + fecEmisionNuevo.substring(3, 5) + fecEmisionNuevo.substring(0, 2);
        }

        if (mesOrigenGasto != null && mesOrigenGasto.length() == 10) {
            mesOrigen = mesOrigenGasto.substring(6, 10) + mesOrigenGasto.substring(3, 5) + mesOrigenGasto.substring(0, 2);
        }

        vo.setRut_empresa(Long.parseLong(rutEmpresa));
        vo.setDv_empresa(dvEmpresa);
        vo.setId_sif019(Long.parseLong(idSif019_glob));
        vo.setNombre_empresa(nombreEmpresa);
        vo.setMes_origen_gasto(Long.parseLong(mesOrigen));
        vo.setEstado_doc_orig(Integer.parseInt(estadoDocumento));
        vo.setModo_pago_orig(Integer.parseInt(modoPagoOrigen));
        vo.setNum_serie_orig(numeroSerieOrigen);
        vo.setNum_docum_orig(Long.parseLong(numeroDocumentoOrigen));
        vo.setMonto_docum_orig(Long.parseLong(montoDocumentoOrigen));
        vo.setFecha_emision_orig(Long.parseLong(fechaOrigen));
        vo.setCodigo_banco_orig(Integer.parseInt(codigoBancoOrigen));
        vo.setModo_pago_nuevo(Integer.parseInt(modoPagoNuevo));
        vo.setNum_serie_nuevo(numeroSerie);
        vo.setNum_docum_nuevo(Long.parseLong(numeroDocumento));
        vo.setMonto_docum_nuevo(Long.parseLong(montoDocumento));
        vo.setFecha_emision_nuevo(Long.parseLong(fechaNuevo));
        vo.setCodigo_banco_nuevo(Integer.parseInt(codigoBancoNuevo));

        return EditarReporteCotizacionesImpl.updateSif019(vo);
    }

    /** Funcion que elimina registro desde la grilla */
    public RespuestaVO eliminarRegistroIndividualSif019(String id) {

        return EditarReporteCotizacionesImpl.eliminarRegistroIndividualSif019(id);
    }

    /**
     * Funcion que elimina registro de la grilla dada una busqueda por
     * correlativo
     */
    public RespuestaVO eliminarRegistroCorrelativoSif019(String id) {

        return EditarReporteCotizacionesImpl.eliminarRegistroCorrelativoSif019(id);
    }

    public Sif019VO actualizarPorCorrelativo019(String min, String max) {

        return EditarReporteCotizacionesImpl.actualizarPorCorrelativo019(min, max);
    }

    /**
     * Funcion que actualiza data en la grilla dada una eliminacion producto de
     * una busqueda por rut
     */
    public Sif019VO obtenerDatosPorRut019(String id, String rut) {

        return EditarReporteCotizacionesImpl.obtenerDatosPorRut019(rut);
    }

    public Sif019VO obtenerEstaticosPorRutSif019(String rut) {

        return EditarReporteCotizacionesImpl.obtenerEstaticosPorRutSif019(rut);
    }

    public Sif019VO dataEstaticaPorIdSif019(String min, String max) {

        return EditarReporteCotizacionesImpl.dataEstaticaPorIdSif019(min, max);
    }

    /** ************************************************************************************************** */
    /**
     * Conjunto de funciones que retornan un array con los datos de las tablas
     * parametricas de SIVEGAM.
     */
    /** funcion que obtiene la lista de tipo beneficiario */
    public TipoBeneficiarioVO[] obtenerDatatipoBeneficiario(String parametro) {

        ListadoParametros listaParam = ListadoParametros.getInstancia();
        TipoBeneficiarioVO[] tipoBenef = new TipoBeneficiarioVO[0];

        if ("TipoBeneficiario".equals(parametro)) {
            tipoBenef = listaParam.getListTipoBeneficiario();
            return tipoBenef;
        }

        return tipoBenef;
    }

    /** Funcion que obtiene la lista de tipo causante */
    public TipoCausanteVO[] obtenerDataTipoCausante(String parametro) {
        ListadoParametros listaParam = ListadoParametros.getInstancia();
        TipoCausanteVO[] tipoCausante = new TipoCausanteVO[0];

        if ("TipoCausante".equals(parametro)) {
            tipoCausante = listaParam.getListTipoCausante();
            return tipoCausante;
        }

        return tipoCausante;
    }

    /** Funcion que obtiene la lista de tipo beneficio */
    public TipoBeneficioVO[] obtenerDataTipoBeneficio(String parametro) {

        ListadoParametros listaParam = ListadoParametros.getInstancia();
        TipoBeneficioVO[] beneficio = new TipoBeneficioVO[0];

        if ("TipoBeneficio".equals(parametro)) {
            beneficio = listaParam.getListTipoBeneficio();
            return beneficio;
        }

        return beneficio;
    }

    /** Funcion que obtiene la lista de codigo de archivo */
    public CodigoArchivoVO[] obtenerDataCodigoArchivo(String parametro) {
        ListadoParametros listaParam = ListadoParametros.getInstancia();
        CodigoArchivoVO[] codArchivo = new CodigoArchivoVO[0];

        if ("CodigoArchivo".equals(parametro)) {
            codArchivo = listaParam.getListCodigoArchivo();
            return codArchivo;
        }

        return codArchivo;
    }

    /** Funcion que obtiene la lista de tipo emision */
    public TipoEmisionVO[] obtenerDataTipoEmision(String parametro) {
        ListadoParametros listaParam = ListadoParametros.getInstancia();
        TipoEmisionVO[] emision = new TipoEmisionVO[0];

        if ("TipoEmision".equals(parametro)) {
            emision = listaParam.getListTipoEmision();
            return emision;
        }

        return emision;
    }

    /** Funcion que obtiene la lista de tipo declaracion */
    public TipoDeclaracionVO[] obtenerDataTipoDeclaracion(String parametro) {
        ListadoParametros listaParam = ListadoParametros.getInstancia();
        TipoDeclaracionVO[] declaracion = new TipoDeclaracionVO[0];

        if ("TipoDeclaracion".equals(parametro)) {
            declaracion = listaParam.getListTipoDeclaracion();
            return declaracion;
        }

        return declaracion;
    }

    /** Funcion que obtiene la lista de tipo reintegro */
    public TipoReintegroVO[] obtenerDataTipoReintegro(String parametro) {
        ListadoParametros listaParam = ListadoParametros.getInstancia();
        TipoReintegroVO[] reintegro = new TipoReintegroVO[0];

        if ("TipoReintegro".equals(reintegro)) {
            reintegro = listaParam.getListTipoReintegro();
            return reintegro;
        }

        return reintegro;
    }

    /** Funcion que obtiene la lista de tipo saldo */
    public TipoSaldoVO[] obtenerDataTipoSaldo(String parametro) {
        ListadoParametros listaParam = ListadoParametros.getInstancia();
        TipoSaldoVO[] saldo = new TipoSaldoVO[0];

        if ("TipoSaldo".equals(parametro)) {
            saldo = listaParam.getListTipoSaldo();
            return saldo;
        }

        return saldo;
    }

    /** Funcion que obtiene la lista de causal reliquidacion */
    public CausalReliquidacionVO[] obtenerDataCausalReliquidacion(String parametro) {
        ListadoParametros listaParam = ListadoParametros.getInstancia();
        CausalReliquidacionVO[] reliquid = new CausalReliquidacionVO[0];

        if ("TipoCausalReliquidacion".equals(parametro)) {
            reliquid = listaParam.getListCausalReliquidacion();
            return reliquid;
        }

        return reliquid;
    }

    /** Funcion que obtiene la lista de tipo egreso */
    public TipoEgresoVO[] obtenerDataTipoEgreso(String parametro) {
        ListadoParametros listaParam = ListadoParametros.getInstancia();
        TipoEgresoVO[] egreso = new TipoEgresoVO[0];

        if ("TipoEgreso".equals(parametro)) {
            egreso = listaParam.getListTipoEgreso();
            return egreso;
        }

        return egreso;
    }

    /** Funcion que obtiene la lista de modalidad de pago */
    public ModalidadDePagoVO[] obtenerDataModalidadPago(String parametro) {
        ListadoParametros listaParam = ListadoParametros.getInstancia();
        ModalidadDePagoVO[] modoPago = new ModalidadDePagoVO[0];

        if ("ModalidadPago".equals(parametro)) {
            modoPago = listaParam.getListModalidadPago();
            return modoPago;
        }

        return modoPago;
    }

    /** Funcion que obtiene la lista de codigo de banco */
    public CodigoBancoVO[] obtenerDataCodigoBanco(String parametro) {
        ListadoParametros listaParam = ListadoParametros.getInstancia();
        CodigoBancoVO[] codigoBanco = new CodigoBancoVO[0];

        if ("CodigoBanco".equals(parametro)) {
            codigoBanco = listaParam.getListCodigoBanco();
            return codigoBanco;
        }

        return codigoBanco;
    }

    /** Funcion que obtiene la lista del estado del documento */
    public EstadoDelDocumentoVO[] obtenerDataEstadoDocumento(String parametro) {
        ListadoParametros listaParam = ListadoParametros.getInstancia();
        EstadoDelDocumentoVO[] estadoDocumento = new EstadoDelDocumentoVO[0];

        if (parametro.equals("EstadoDocumento")) {
            estadoDocumento = listaParam.getListEstadoDocumento();
            return estadoDocumento;
        }

        return estadoDocumento;
    }

    public RespuestaVO deleteCotizacionesConRango(String idReporte, String rangoUno, String rangoDos) {

        return EditarReporteCotizacionesImpl.deleteCotizacionesConRango(idReporte, rangoUno, rangoDos);
    }

    public RespuestaVO deleteCotizacionesSinRango(String idReporte, String rangoUno) {

        return EditarReporteCotizacionesImpl.deleteCotizacionesSinRango(idReporte, rangoUno);
    }
}
