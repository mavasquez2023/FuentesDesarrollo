package cl.araucana.sivegam.struts.dwr.actions;

import cl.araucana.sivegam.impl.AgregarRegistroDivisionPrevisionalImpl;
import cl.araucana.sivegam.vo.LinSif011VO;
import cl.araucana.sivegam.vo.LinSif012VO;
import cl.araucana.sivegam.vo.LinSif014VO;
import cl.araucana.sivegam.vo.RespuestaVO;

public class AgregarRegistroDivisionPrevisionalDWR {

    public RespuestaVO insertSif011(String mesInformado, String rutEmpresa, String dvEmpresa, String nombreEmpresa, String numeroDeclaracion, String rutAfiliado,
            String dvAfiliado, String nombreAfiliado, String tipoBeneficio, String tipoBeneficiario, String rutCausante, String dvCausante, String nombreCausante,
            String tipoCausante, String fechaInicioBeneficio, String fechaTerminoBeneficio, String diasAsfam, String codigoTramo, String montoBeneficio, String tipoEmision,
            String tipoEgreso, String modalidadPago, String montoDocumento, String numeroSerie, String numeroDocumento, String fechaEmision, String codigoBanco,
            String fuenteOrigen, String tipoDeclaracion) {

        LinSif011VO linsif011 = new LinSif011VO();
        String fecha_inicio = fechaInicioBeneficio.substring(6, 10) + fechaInicioBeneficio.substring(3, 5) + fechaInicioBeneficio.substring(0, 2);
        String fecha_termino = fechaTerminoBeneficio.substring(6, 10) + fechaTerminoBeneficio.substring(3, 5) + fechaTerminoBeneficio.substring(0, 2);
        String fecha_emision;
        Long mesinformado = Long.parseLong(mesInformado);
        if (fechaEmision.length() > 0) {
            fecha_emision = fechaEmision.substring(6, 10) + fechaEmision.substring(3, 5) + fechaEmision.substring(0, 2);
        } else {
            fecha_emision = "";
        }
        linsif011.setRut_empresa(Long.parseLong(rutEmpresa));
        linsif011.setDv_empresa(dvEmpresa);
        linsif011.setNombre_empresa(nombreEmpresa);
        linsif011.setRut_afiliado(Long.parseLong(rutAfiliado));
        linsif011.setDv_afiliado(dvAfiliado);
        linsif011.setNombre_afiliado(nombreAfiliado);
        linsif011.setTipo_beneficiario(Integer.parseInt(tipoBeneficiario));
        linsif011.setCod_tipo_beneficio(Integer.parseInt(tipoBeneficio));
        linsif011.setRut_causante(Long.parseLong(rutCausante));
        linsif011.setDv_causante(dvCausante);
        linsif011.setNombre_causante(nombreCausante);
        linsif011.setCod_tipo_causante(Integer.parseInt(tipoCausante));
        linsif011.setFecha_inicio_benef(Long.parseLong(fecha_inicio));
        linsif011.setFecha_termino_benef(Long.parseLong(fecha_termino));
        linsif011.setDias_asfam(Integer.parseInt(diasAsfam));
        linsif011.setCodigo_tramo(Integer.parseInt(codigoTramo));
        linsif011.setMonto_beneficio(Long.parseLong(montoBeneficio));
        linsif011.setTipo_emision(Integer.parseInt(tipoEmision));
        linsif011.setCod_tipo_egreso(Integer.parseInt(tipoEgreso));
        linsif011.setModalidad_pago(Integer.parseInt(modalidadPago));
        if (montoDocumento.length() > 0) {
            linsif011.setMonto_documento(Long.parseLong(montoDocumento));
        } else {
            linsif011.setMonto_documento(0);
        }
        linsif011.setNumero_serie(numeroSerie);
        linsif011.setNumero_documento(numeroDocumento);
        if (fecha_emision.length() > 0) {
            linsif011.setFecha_emision_documento(Long.parseLong(fecha_emision));
        } else {
            linsif011.setFecha_emision_documento(0);
        }
        linsif011.setCodigo_banco(Long.parseLong(codigoBanco));
        linsif011.setFuente_origen(Integer.parseInt(fuenteOrigen));
        linsif011.setTipo_declaracion(Integer.parseInt(tipoDeclaracion));
        linsif011.setCodigo_barra(numeroDeclaracion);
        if (Long.parseLong(fuenteOrigen) == 1) {
            if ((mesinformado % 100) < 2) {
                mesinformado = mesinformado - 89;
            } else {
                mesinformado = mesinformado - 1;
            }
        }
        linsif011.setMes_remuneracion(mesinformado);

        return AgregarRegistroDivisionPrevisionalImpl.insertSif011(linsif011);

    }

    public RespuestaVO insertSif012(String rutEmpresa, String dvEmpresa, String nombreEmpresa, String rutAfiliado, String dvAfiliado, String nombreAfiliado, String tipoBeneficio,
            String tipoBeneficiario, String rutCausante, String dvCausante, String nombreCausante, String tipoCausante, String fechaInicioBeneficio, String fechaTerminoBeneficio,
            String diasAsfam, String codigoTramo, String montoBeneficio, String tipoEmision, String tipoEgreso, String modalidadPago, String montoDocumento, String numeroSerie,
            String numeroDocumento, String fechaEmision, String codigoBanco, String fuenteOrigen, String tipoDeclaracion, String causalReliquidacion, String origenInfo,
            String numeroDeclaracion) {

        LinSif012VO linsif012 = new LinSif012VO();
        String fecha_inicio = fechaInicioBeneficio.substring(6, 10) + fechaInicioBeneficio.substring(3, 5) + fechaInicioBeneficio.substring(0, 2);
        String fecha_termino = fechaTerminoBeneficio.substring(6, 10) + fechaTerminoBeneficio.substring(3, 5) + fechaTerminoBeneficio.substring(0, 2);
        String fecha_emision;
        if (fechaEmision.length() > 0) {
            fecha_emision = fechaEmision.substring(6, 10) + fechaEmision.substring(3, 5) + fechaEmision.substring(0, 2);
        } else {
            fecha_emision = "";
        }

        linsif012.setRut_empresa(Long.parseLong(rutEmpresa));
        linsif012.setDv_empresa(dvEmpresa);
        linsif012.setNombre_empresa(nombreEmpresa);
        linsif012.setRut_afiliado(Long.parseLong(rutAfiliado));
        linsif012.setDv_afiliado(dvAfiliado);
        linsif012.setNombre_afiliado(nombreAfiliado);
        linsif012.setTipo_beneficiario(Integer.parseInt(tipoBeneficiario));
        linsif012.setCod_tipo_beneficio(Integer.parseInt(tipoBeneficio));
        linsif012.setRut_causante(Long.parseLong(rutCausante));
        linsif012.setDv_causante(dvCausante);
        linsif012.setNombre_causante(nombreCausante);
        linsif012.setCod_tipo_causante(Integer.parseInt(tipoCausante));
        linsif012.setFecha_inicio_benef(Long.parseLong(fecha_inicio));
        linsif012.setFecha_termino_benef(Long.parseLong(fecha_termino));
        linsif012.setDias_asfam(Integer.parseInt(diasAsfam));
        linsif012.setCodigo_tramo(Integer.parseInt(codigoTramo));
        linsif012.setMonto_beneficio(Long.parseLong(montoBeneficio));
        linsif012.setTipo_emision(Integer.parseInt(tipoEmision));
        linsif012.setCod_tipo_egreso(Integer.parseInt(tipoEgreso));
        linsif012.setModalidad_pago(Integer.parseInt(modalidadPago));
        if (montoDocumento.length() > 0) {
            linsif012.setMonto_documento(Long.parseLong(montoDocumento));
        } else {
            linsif012.setMonto_documento(0);
        }
        linsif012.setNumero_serie(numeroSerie);
        linsif012.setNumero_documento(numeroDocumento);
        if (fecha_emision.length() > 0) {
            linsif012.setFech_emision_doc(Long.parseLong(fecha_emision));
        } else {
            linsif012.setFech_emision_doc(0);
        }
        linsif012.setCodigo_banco(Long.parseLong(codigoBanco));
        linsif012.setFuente_origen(Integer.parseInt(fuenteOrigen));
        linsif012.setTipo_declaracion(Integer.parseInt(tipoDeclaracion));
        linsif012.setCausal_reliquidacion(Integer.parseInt(causalReliquidacion));
        linsif012.setNumero_declaracion(numeroDeclaracion);
        linsif012.setReferncia_origen(origenInfo);

        return AgregarRegistroDivisionPrevisionalImpl.insertSif012(linsif012);
    }

    public RespuestaVO insertSif014(String rutEmpresa, String dvEmpresa, String nombreEmpresa, String rutBeneficiario, String dvBeneficiario, String nombreBeneficiario,
            String tipoBeneficio, String tipoBeneficiario, String rutCausante, String dvCausante, String nombreCausante, String tipoCausante, String inicioPeriodoReintegro,
            String finPeriodoReintegro, String tipoReintegro, String montoTotalReintegro, String montoReintegroMes, String montoTotalDeuda, String fuenteOrigen) {

        LinSif014VO linsif014 = new LinSif014VO();

        String inicio_periodo_reintegro = inicioPeriodoReintegro.substring(6, 10) + inicioPeriodoReintegro.substring(3, 5) + inicioPeriodoReintegro.substring(0, 2);
        String final_periodo_reintegro = finPeriodoReintegro.substring(6, 10) + finPeriodoReintegro.substring(3, 5) + finPeriodoReintegro.substring(0, 2);

        linsif014.setRut_empresa(Long.parseLong(rutEmpresa));
        linsif014.setDv_empresa(dvEmpresa);
        linsif014.setNombre_empresa(nombreEmpresa);
        linsif014.setRut_beneficiario(Long.parseLong(rutBeneficiario));
        linsif014.setDv_beneficiario(dvBeneficiario);
        linsif014.setNombre_beneficiario(nombreBeneficiario);
        linsif014.setTipo_beneficio(Integer.parseInt(tipoBeneficio));
        linsif014.setTipo_beneficiario(Integer.parseInt(tipoBeneficiario));
        linsif014.setRut_causante(Long.parseLong(rutCausante));
        linsif014.setDv_causante(dvCausante);
        linsif014.setNombre_causante(nombreCausante);
        linsif014.setTipo_causante(Integer.parseInt(tipoCausante));
        linsif014.setInicio_period_reinte(Long.parseLong(inicio_periodo_reintegro));
        linsif014.setFinal_period_reinte(Long.parseLong(final_periodo_reintegro));
        linsif014.setTipo_reintegro(Integer.parseInt(tipoReintegro));
        linsif014.setMonto_total_reintegro(Long.parseLong(montoTotalReintegro));
        linsif014.setMonto_reintegro_mes(Long.parseLong(montoReintegroMes));
        linsif014.setMonto_total_deuda(Long.parseLong(montoTotalDeuda));
        linsif014.setFuente_origen(Integer.parseInt(fuenteOrigen));

        return AgregarRegistroDivisionPrevisionalImpl.insertSif014(linsif014);
    }

    public RespuestaVO cargarMesProcesamiento(String idTipoReporte) {

        return AgregarRegistroDivisionPrevisionalImpl.cargarMesProcesamiento(idTipoReporte);
    }

    /*
     * Funcion que obtiene la data de la tabla Sif012, usando como filtro el
     * correlativo (id)
     */
    public static LinSif012VO obtenerDataSif012(long correlativo) {

        return AgregarRegistroDivisionPrevisionalImpl.obtenerDataSif012(correlativo);
    }

    /**
     * Funcion que obtiene la data de la tabla sif011, usando como filtro el
     * correlativo, para el cruze de informacion (id)
     */
    public static LinSif011VO obtenerDataSif011(long correlativo) {

        return AgregarRegistroDivisionPrevisionalImpl.obtenerDataSif011(correlativo);
    }
}
