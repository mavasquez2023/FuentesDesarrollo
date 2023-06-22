package cl.araucana.sivegam.struts.dwr.actions;

import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.impl.AgregarRegistroCotizacionesImpl;
import cl.araucana.sivegam.vo.LinSif016VO;
import cl.araucana.sivegam.vo.LinSif017VO;
import cl.araucana.sivegam.vo.LinSif018VO;
import cl.araucana.sivegam.vo.LinSif019VO;
import cl.araucana.sivegam.vo.RespuestaVO;

public class AgregarRegistroCotizacionesDWR {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    public RespuestaVO insertSif018(String codArchivo, String rutEmpresa, String dvEmpresa, String nombreEmpresa, String modalidadPago, String montoDocumento, String numeroSerie,
            String numeroDocumento, String fechaEmision, String codigoBanco, String fechaCobro) {

        logger.debug("INI     : [AgregarRegistroCotizacionesDWR] insertSif018");
        LinSif018VO linsif018 = new LinSif018VO();

        String fecha_emision_documento = fechaEmision.substring(6, 10) + fechaEmision.substring(3, 5) + fechaEmision.substring(0, 2);
        String fecha_cobro;
        if (fechaCobro.length() > 0) {
            fecha_cobro = fechaCobro.substring(6, 10) + fechaCobro.substring(3, 5) + fechaCobro.substring(0, 2);
        } else {
            fecha_cobro = "0";

        }

        linsif018.setCodigo_archivo(Integer.parseInt(codArchivo));
        linsif018.setRut_empleador(Long.parseLong(rutEmpresa));
        linsif018.setDv_empleador(dvEmpresa);
        linsif018.setNombre_empleador(nombreEmpresa);
        linsif018.setMod_pago(Integer.parseInt(modalidadPago));
        linsif018.setMonto_documento(Long.parseLong(montoDocumento));
        linsif018.setNumero_serie(numeroSerie);
        linsif018.setNumero_documento(numeroDocumento);
        linsif018.setFecha_emision_documento(Integer.parseInt(fecha_emision_documento));
        linsif018.setCodigo_banco(Integer.parseInt(codigoBanco));
        linsif018.setFecha_cobro(Integer.parseInt(fecha_cobro));

        logger.debug("IN      : [insertSif018] Codigo_archivo          [" + linsif018.getCodigo_archivo() + "]");
        logger.debug("IN      : [insertSif018] Rut_empleador           [" + linsif018.getRut_empleador() + "]");
        logger.debug("IN      : [insertSif018] Dv_empleador            [" + linsif018.getDv_empleador() + "]");
        logger.debug("IN      : [insertSif018] Nombre_empleador        [" + linsif018.getNombre_empleador() + "]");
        logger.debug("IN      : [insertSif018] Mod_pago                [" + linsif018.getMod_pago() + "]");
        logger.debug("IN      : [insertSif018] Monto_documento         [" + linsif018.getMonto_documento() + "]");
        logger.debug("IN      : [insertSif018] Numero_serie            [" + linsif018.getNumero_serie() + "]");
        logger.debug("IN      : [insertSif018] Numero_documento        [" + linsif018.getNumero_documento() + "]");
        logger.debug("IN      : [insertSif018] Fecha_emision_documento [" + linsif018.getFecha_emision_documento() + "]");
        logger.debug("IN      : [insertSif018] Codigo_banco            [" + linsif018.getCodigo_banco() + "]");
        logger.debug("IN      : [insertSif018] Fecha_cobro             [" + linsif018.getFecha_cobro() + "]");
        logger.debug("FIN     : [AgregarRegistroCotizacionesDWR] insertSif018");
        return AgregarRegistroCotizacionesImpl.insertSif018(linsif018);
    }

    public RespuestaVO insertSif019(String rutEmpresa, String dvEmpresa, String nombreEmpresa, String mesOrigenGasto, String modalidadPagoOrigen, String montoDocumentoOrigen,
            String numeroSerieOrigen, String numeroDocumentoOrigen, String fechaEmisionOrigen, String codigoBancoOrigen, String modalidadPagoNuevo, String montoDocumentoNuevo,
            String numeroSerieNuevo, String numeroDocumentoNuevo, String fechaEmisionNuevo, String codigoBancoNuevo) {

        LinSif019VO linsif019 = new LinSif019VO();
        String mes_origen_gasto = mesOrigenGasto.substring(6, 10) + mesOrigenGasto.substring(3, 5) + mesOrigenGasto.substring(0, 2);
        String fecha_emision_origen = fechaEmisionOrigen.substring(6, 10) + fechaEmisionOrigen.substring(3, 5) + fechaEmisionOrigen.substring(0, 2);
        String fecha_emision_nuevo = fechaEmisionNuevo.substring(6, 10) + fechaEmisionNuevo.substring(3, 5) + fechaEmisionNuevo.substring(0, 2);

        linsif019.setRut_empresa(Long.parseLong(rutEmpresa));
        linsif019.setDv_empresa(dvEmpresa);
        linsif019.setNombre_empresa(nombreEmpresa);
        linsif019.setMes_origen_gasto(Long.parseLong(mes_origen_gasto));
        linsif019.setModo_pago_orig(Integer.parseInt(modalidadPagoOrigen));
        linsif019.setMonto_docum_orig(Long.parseLong(montoDocumentoOrigen));
        linsif019.setNum_serie_orig(numeroSerieOrigen);
        linsif019.setNum_docum_orig(Long.parseLong(numeroDocumentoOrigen));
        linsif019.setFecha_emision_orig(Long.parseLong(fecha_emision_origen));
        linsif019.setCodigo_banco_orig(Integer.parseInt(codigoBancoOrigen));
        linsif019.setModo_pago_nuevo(Integer.parseInt(modalidadPagoNuevo));
        linsif019.setMonto_docum_nuevo(Long.parseLong(montoDocumentoNuevo));
        linsif019.setNum_serie_nuevo(numeroSerieNuevo);
        linsif019.setNum_docum_nuevo(Long.parseLong(numeroDocumentoNuevo));
        linsif019.setFecha_emision_nuevo(Integer.parseInt(fecha_emision_nuevo));
        linsif019.setCodigo_banco_nuevo(Integer.parseInt(codigoBancoNuevo));

        return AgregarRegistroCotizacionesImpl.insertSif019(linsif019);

    }

    public RespuestaVO insertSif017(String rutEmpresa, String dvEmpresa, String nombreEmpresa, String codTipoEgreso, String modalidadPago, String montoDocumento,
            String numeroSerie, String numeroDocumento, String fechaEmision, String codigoBanco, String numeroCartola, String estadoDocumento, String fechaRendicion) {

        LinSif017VO linsif017 = new LinSif017VO();

        String fechaEmisionDocumento = fechaEmision.substring(6, 10) + fechaEmision.substring(3, 5) + fechaEmision.substring(0, 2);
        String fechaRendicionDocumento = fechaRendicion.substring(6, 10) + fechaRendicion.substring(3, 5) + fechaRendicion.substring(0, 2);

        linsif017.setRut_empresa(Long.parseLong(rutEmpresa));
        linsif017.setDv_empresa(dvEmpresa);
        linsif017.setNombre_empresa(nombreEmpresa);
        linsif017.setCod_tipo_egreso(Integer.parseInt(codTipoEgreso));
        linsif017.setModalidad_de_pago(Integer.parseInt(modalidadPago));
        linsif017.setMonto_documento(Long.parseLong(montoDocumento));
        linsif017.setNumero_serie(numeroSerie);
        linsif017.setNumero_documento(numeroDocumento);
        linsif017.setFech_emision_doc(Long.parseLong(fechaEmisionDocumento));
        linsif017.setCodigo_banco(Integer.parseInt(codigoBanco));
        linsif017.setNumero_cartola(numeroCartola);
        linsif017.setEstado_documento(Integer.parseInt(estadoDocumento));
        linsif017.setFecha_rendicion(Long.parseLong(fechaRendicionDocumento));

        return AgregarRegistroCotizacionesImpl.insertSif017(linsif017);
    }

    public RespuestaVO insertSif016(String rutEmpresa, String dvEmpresa, String nombreEmpresa, String numeroDeclaracion, String fechaDeclaracion, String numeroTotalTrabajador,
            String numeroTotalCargas, String cargasRetroactivas, String montoAsfamMes, String montoAsfamRetro, String montoReintegroMes, String totalPagoAsigFam,
            String totalCotizacion, String otrosDescuentos, String resultadoNeto, String tipoSaldo, String modalidadPago, String montoDocumento, String numeroSerie,
            String numeroDocumento, String fechaEmision, String codigoBanco) {

        LinSif016VO linsif016 = new LinSif016VO();

        String fecha_declaracion = fechaDeclaracion.substring(6, 10) + fechaDeclaracion.substring(3, 5) + fechaDeclaracion.substring(0, 2);
        String fecha_emision = "0";
        if (fechaEmision.length() > 9) {
            fecha_emision = fechaEmision.substring(6, 10) + fechaEmision.substring(3, 5) + fechaEmision.substring(0, 2);
        }

        linsif016.setRut_empresa(Long.parseLong(rutEmpresa));
        linsif016.setDv_empresa(dvEmpresa);
        linsif016.setNombre_empresa(nombreEmpresa);
        linsif016.setNumero_declaracion(numeroDeclaracion);
        linsif016.setFech_declaracion(Long.parseLong(fecha_declaracion));
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
        if ("".equals(montoDocumento)) {
            linsif016.setMonto_documento(0);
        } else {
            linsif016.setMonto_documento(Long.parseLong(montoDocumento));
        }
        linsif016.setNumero_serie(numeroSerie);
        linsif016.setNumero_documento(numeroDocumento);
        linsif016.setFech_emision_doc(Long.parseLong(fecha_emision));
        linsif016.setCodigo_banco(Integer.parseInt(codigoBanco));

        return AgregarRegistroCotizacionesImpl.insertSif016(linsif016);
    }

    public RespuestaVO cargarMesProcesamiento(String idTipoReporte) {

        return AgregarRegistroCotizacionesImpl.cargarMesProcesamiento(idTipoReporte);
    }
}
