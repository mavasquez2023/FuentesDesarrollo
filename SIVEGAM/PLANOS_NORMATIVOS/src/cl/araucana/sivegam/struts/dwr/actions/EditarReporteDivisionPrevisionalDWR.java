package cl.araucana.sivegam.struts.dwr.actions;

import java.io.IOException;
import java.sql.SQLException;

import cl.araucana.sivegam.helper.GlobalProperties;
import cl.araucana.sivegam.impl.EditarReporteDivisionPrevisionalImpl;
import cl.araucana.sivegam.vo.CodigoTramoVO;
import cl.araucana.sivegam.vo.LinSif011VO;
import cl.araucana.sivegam.vo.LinSif012VO;
import cl.araucana.sivegam.vo.LinSif014VO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.Sif011VO;
import cl.araucana.sivegam.vo.Sif012VO;
import cl.araucana.sivegam.vo.Sif014VO;
import cl.araucana.sivegam.vo.param.ListadoParametros;

public class EditarReporteDivisionPrevisionalDWR {

    static GlobalProperties global = GlobalProperties.getInstance();

    /** Funciones del formulario modsif011 */
    public Sif011VO obtenerDatosPorRut(String idSelectedItem, String rut) {

        EditarReporteDivisionPrevisionalImpl impl = new EditarReporteDivisionPrevisionalImpl();
        Sif011VO vo = new Sif011VO();

        try {

            vo = impl.obtenerDatosPorRut(idSelectedItem, rut);

            return vo;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException f) {
            f.printStackTrace();
        }

        return vo;
    }

    public Sif011VO obtenerDatosPorRutId(String idSelectedItem, String idSif011, String rut) {

        EditarReporteDivisionPrevisionalImpl impl = new EditarReporteDivisionPrevisionalImpl();
        Sif011VO vo = new Sif011VO();

        try {

            vo = impl.obtenerDatosPorRutId(idSelectedItem, idSif011, rut);

            return vo;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException f) {
            f.printStackTrace();
        }

        return vo;
    }

    /* Obtiene data estatica directamente de la tabla */
    public static Sif011VO obtenerEstaticos(String id, String rut) {

        return EditarReporteDivisionPrevisionalImpl.obtenerEstaticos(id, rut);
    }

    /* Obtiene data estaica directamente de las parametricas */
    public static Sif011VO obtenerDataEstatica(String tipoArchivo, String periodoArchivo) {

        return EditarReporteDivisionPrevisionalImpl.obtenerDataEstatica(tipoArchivo, periodoArchivo);
    }

    /* funcion que elimina registro individual sif011 */
    public RespuestaVO eliminarRegistroIndividualSif011(String id) {

        return EditarReporteDivisionPrevisionalImpl.eliminarRegistroIndividualSif011(id);
    }

    /*
     * Funcion que elimina de forma individual un registro dada la busqueda por
     * rut de afiliado
     */
    public RespuestaVO eliminarRegistroAfiliadoSif011(String id) {
        return EditarReporteDivisionPrevisionalImpl.eliminarRegistroAfiliadoSif011(id);
    }

    /*
     * funcion que elimina de forma individual un registro de la tabla
     * sif011todo dada una busqueda oir correlativo
     */
    public RespuestaVO eliminarRegistroCorrelativoSif011(String id) {
        return EditarReporteDivisionPrevisionalImpl.eliminarRegistroCorrelativoSif011(id);
    }

    /** Funcion que hace el update a los campos de la tabla sif011todo */
    public RespuestaVO updateSif011(String id, String fteOrigen, String mesInformado, String rutEmpresa, String dvEmpresa, String nombreEmpresa, String numeroDeclaracion,
            String rutAfiliado, String dvAfiliado, String nombreAfiliado, String tipoBeneficio, String tipoBeneficiario, String rutCausante, String dvCausante,
            String nombreCausante, String tipoCausante, String fechaInicioBeneficio, String fechaTerminoBeneficio, String diasAsfam, String codigoTramo, String montoBeneficio,
            String tipoEmision, String tipoEgreso, String modalidadPago, String montoDocumento, String numeroSerie, String numeroDocumento, String fechaEmision,
            String codigoBanco, String fuenteOrigen, String tipoDeclaracion) {

        LinSif011VO listSif011 = new LinSif011VO();
        String entidad = global.getValor("SVG.entidad");
        String codArchivo = global.getValor("SVG.cod.sif011");
        Long mesinformado = Long.parseLong(mesInformado);

        String fechaInicio = "0";
        String fechaFin = "0";
        String fechaEmi = "0";

        if (fechaInicioBeneficio != null && fechaInicioBeneficio.length() == 10) {
            fechaInicio = fechaInicioBeneficio.substring(6, 10) + fechaInicioBeneficio.substring(3, 5) + fechaInicioBeneficio.substring(0, 2);
        }
        if (fechaTerminoBeneficio != null && fechaTerminoBeneficio.length() == 10) {
            fechaFin = fechaTerminoBeneficio.substring(6, 10) + fechaTerminoBeneficio.substring(3, 5) + fechaTerminoBeneficio.substring(0, 2);
        }
        if (fechaEmision != null && fechaEmision.length() == 10) {
            fechaEmi = fechaEmision.substring(6, 10) + fechaEmision.substring(3, 5) + fechaEmision.substring(0, 2);
        }

        listSif011.setId_sif011(Long.parseLong(id));
        listSif011.setFecha_proceso(Long.parseLong(mesInformado));
        listSif011.setFuente_origen(Integer.parseInt(fteOrigen));
        listSif011.setRut_empresa(Long.parseLong(rutEmpresa));
        listSif011.setDv_empresa(dvEmpresa);
        listSif011.setNombre_empresa(nombreEmpresa);
        listSif011.setCodigo_barra(numeroDeclaracion);
        listSif011.setRut_afiliado(Long.parseLong(rutAfiliado));
        listSif011.setDv_afiliado(dvAfiliado);
        listSif011.setNombre_afiliado(nombreAfiliado);
        listSif011.setCod_tipo_beneficio(Integer.parseInt(tipoBeneficio));
        listSif011.setTipo_beneficiario(Integer.parseInt(tipoBeneficiario));
        listSif011.setRut_causante(Long.parseLong(rutCausante));
        listSif011.setDv_causante(dvCausante);
        listSif011.setNombre_causante(nombreCausante);
        listSif011.setCod_tipo_causante(Integer.parseInt(tipoCausante));
        listSif011.setFecha_inicio_benef(Long.parseLong(fechaInicio));
        listSif011.setFecha_termino_benef(Long.parseLong(fechaFin));
        listSif011.setDias_asfam(Integer.parseInt(diasAsfam));
        listSif011.setCodigo_tramo(Integer.parseInt(codigoTramo));
        listSif011.setMonto_beneficio(Long.parseLong(montoBeneficio));
        listSif011.setTipo_emision(Integer.parseInt(tipoEmision));
        listSif011.setCod_tipo_egreso(Integer.parseInt(tipoEgreso));
        listSif011.setModalidad_pago(Integer.parseInt(modalidadPago));
        try{
        	listSif011.setMonto_documento(Long.parseLong(montoDocumento));
        }catch(NumberFormatException ne){
            listSif011.setMonto_documento(0);
        }
        listSif011.setNumero_serie(numeroSerie);
        listSif011.setNumero_documento(numeroDocumento);
        try{
        	listSif011.setFecha_emision_documento(Long.parseLong(fechaEmi));
        }catch(NumberFormatException ne){
        	listSif011.setFecha_emision_documento(0);
        }
        listSif011.setCodigo_banco(Long.parseLong(codigoBanco));
        listSif011.setFuente_origen(Integer.parseInt(fuenteOrigen));
        listSif011.setTipo_declaracion(Integer.parseInt(tipoDeclaracion));

        listSif011.setCodigo_entidad(Long.parseLong(entidad));
        listSif011.setCodigo_archivo(Integer.parseInt(codArchivo));
        listSif011.setMes_remuneracion(Long.parseLong(mesInformado));

        if (Long.parseLong(fuenteOrigen) == 1) {
            if ((mesinformado % 100) < 2) {
                mesinformado = mesinformado - 89;
            } else {
                mesinformado = mesinformado - 1;
            }
        }
        listSif011.setMes_cotizaciones(mesinformado);

        return EditarReporteDivisionPrevisionalImpl.updateSif011(listSif011);
    }

    /** Funcion que implementa la busqueda por rango en la tabla SIF011TODO */
    public Sif011VO busquedaPorRangoSif011(String id, String rangoUno, String rangoDos) {

        return EditarReporteDivisionPrevisionalImpl.busquedaPorRangoSif011(id, rangoUno, rangoDos);
    }

    /**
     * Realiza una busqueda luego de haber eliminado un registro dada un rango
     * de busqueda por correlativo
     */
    public Sif011VO actualizarPorCorrelativo(String id_1, String id_2) {

        return EditarReporteDivisionPrevisionalImpl.actualizarPorCorrelativo(id_1, id_2);
    }

    public Sif011VO dataEstaticaPorId(String id_1, String id_2) {

        return EditarReporteDivisionPrevisionalImpl.dataEstaticaPorId(id_1, id_2);
    }

    /** Funciones del formulario modifsif012 */
    /** Funcion que obtiene los datos de la tabla sif012todo filtrado por rut. */
    public Sif012VO obtenerDatosSif012PorRut(String idSelectedItem, String rut) {

        EditarReporteDivisionPrevisionalImpl impl = new EditarReporteDivisionPrevisionalImpl();
        Sif012VO vo = new Sif012VO();

        try {

            vo = impl.obtenerDatosSif012PorRut(idSelectedItem, rut);

            return vo;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException f) {
            f.printStackTrace();
        }

        return vo;
    }

    /** Funcion que implementa busqueda por correlativo */
    public Sif012VO busquedaPorRangoSif012(String idSelectedItem, String primerRango, String segundoRango) {

        return EditarReporteDivisionPrevisionalImpl.busquedaPorRangoSif012(idSelectedItem, primerRango, segundoRango);
    }

    /**
     * Funcion que realiza una busqueda de los datos estaticos de la tabla
     * sif012
     */
    public Sif012VO dataEstaticaPorIdSif012(String rangoUno, String rangoDos) {
        return EditarReporteDivisionPrevisionalImpl.dataEstaticaPorIdSif012(rangoUno, rangoDos);
    }

    /**
     * Funcion que realiza el update de los campos modificados en la tabla
     * sif012todo
     */
    public RespuestaVO updateSif012(String idSelected, String mesInformado, String rutEmpresa, String dvEmpresa, String nombreEmpresa, String rutAfiliado, String dvAfiliado,
            String nombreAfiliado, String tipoBeneficio, String tipoBeneficiario, String rutCausante, String dvCausante, String nombreCausante, String tipoCausante,
            String fechaInicioBeneficio, String fechaTerminoBeneficio, String diasAsfam, String codigoTramo, String montoBeneficio, String tipoEmision, String tipoEgreso,
            String modalidadPago, String montoDocumento, String numeroSerie, String numeroDocumento, String fechaEmision, String codigoBanco, String fuenteOrigen,
            String tipoDeclaracion, String causalReliquidacion, String origenInfo, String numeroDeclaracion) {

        LinSif012VO linsif012 = new LinSif012VO();
        String fechaInicio = "0";
        String fechaFin = "0";
        String fechaEmi = "0";
        String entidad = global.getValor("SVG.entidad");
        String codArchivo = global.getValor("SVG.cod.sif012");
        int mesinformado = Integer.parseInt(mesInformado);

        if (fechaInicioBeneficio != null && fechaInicioBeneficio.length() == 10) {
            fechaInicio = fechaInicioBeneficio.substring(6, 10) + fechaInicioBeneficio.substring(3, 5) + fechaInicioBeneficio.substring(0, 2);
        }
        if (fechaTerminoBeneficio != null && fechaTerminoBeneficio.length() == 10) {
            fechaFin = fechaTerminoBeneficio.substring(6, 10) + fechaTerminoBeneficio.substring(3, 5) + fechaTerminoBeneficio.substring(0, 2);
        }
        if (fechaEmision != null && fechaEmision.length() == 10) {
            fechaEmi = fechaEmision.substring(6, 10) + fechaEmision.substring(3, 5) + fechaEmision.substring(0, 2);
        }
        linsif012.setId_sif012(Long.parseLong(idSelected));

        linsif012.setRut_empresa(Long.parseLong(rutEmpresa));
        linsif012.setDv_empresa(dvEmpresa);
        linsif012.setNombre_empresa(nombreEmpresa);
        linsif012.setRut_afiliado(Long.parseLong(rutAfiliado));
        linsif012.setDv_afiliado(dvAfiliado);
        linsif012.setNombre_afiliado(nombreAfiliado);
        linsif012.setCod_tipo_beneficio(Integer.parseInt(tipoBeneficio));
        linsif012.setTipo_beneficiario(Integer.parseInt(tipoBeneficiario));
        linsif012.setRut_causante(Long.parseLong(rutCausante));
        linsif012.setDv_causante(dvCausante);
        linsif012.setNombre_causante(nombreCausante);
        linsif012.setCod_tipo_causante(Integer.parseInt(tipoCausante));
        linsif012.setFecha_inicio_benef(Long.parseLong(fechaInicio));
        linsif012.setFecha_termino_benef(Long.parseLong(fechaFin));
        linsif012.setDias_asfam(Integer.parseInt(diasAsfam));
        linsif012.setCodigo_tramo(Integer.parseInt(codigoTramo));
        linsif012.setMonto_beneficio(Long.parseLong(montoBeneficio));
        linsif012.setTipo_emision(Integer.parseInt(tipoEmision));
        linsif012.setCod_tipo_egreso(Integer.parseInt(tipoEgreso));
        linsif012.setModalidad_pago(Integer.parseInt(modalidadPago));
        linsif012.setMonto_documento(Long.parseLong(montoDocumento));
        linsif012.setNumero_serie(numeroSerie);
        linsif012.setNumero_documento(numeroDocumento);
        linsif012.setFech_emision_doc(Long.parseLong(fechaEmi));
        linsif012.setCodigo_banco(Long.parseLong(codigoBanco));
        linsif012.setFuente_origen(Integer.parseInt(fuenteOrigen));
        linsif012.setTipo_declaracion(Integer.parseInt(tipoDeclaracion));
        linsif012.setCausal_reliquidacion(Integer.parseInt(causalReliquidacion));
        linsif012.setNumero_declaracion(numeroDeclaracion);

        linsif012.setCodigo_entidad(Integer.parseInt(entidad));
        linsif012.setCodigo_archivo(Integer.parseInt(codArchivo));
        linsif012.setFecha_proceso(Integer.parseInt(mesInformado));

        if ((mesinformado % 100) < 2) {
            mesinformado = mesinformado - 89;
        } else {
            mesinformado = mesinformado - 1;
        }

        if (Long.parseLong(fuenteOrigen) == 1) {
            linsif012.setMes_recaudacion(Integer.parseInt(mesInformado));
            linsif012.setMes_remuneracion(mesinformado);
        } else if (Long.parseLong(fuenteOrigen) == 2) {
            linsif012.setMes_recaudacion(Integer.parseInt(mesInformado));
            linsif012.setMes_remuneracion(Integer.parseInt(mesInformado));
        } else if (Long.parseLong(fuenteOrigen) == 3) {
            linsif012.setMes_recaudacion(mesinformado);
            linsif012.setMes_remuneracion(mesinformado);
        } else if (Long.parseLong(fuenteOrigen) == 4) {
            linsif012.setMes_recaudacion(Integer.parseInt(mesInformado));
            linsif012.setMes_remuneracion(mesinformado);
        } else {
            linsif012.setMes_recaudacion(Integer.parseInt(mesInformado));
            linsif012.setMes_remuneracion(Integer.parseInt(mesInformado));
        }

        return EditarReporteDivisionPrevisionalImpl.updateSif012(linsif012);
    }

    /**
     * funcion que obtiene los datos de un determinado registro mostrado en la
     * grilla.
     */
    public Sif012VO obtenerDatosSif012PorRutId(String idSelected, String id, String rut) {

        Sif012VO sif012 = new Sif012VO();
        EditarReporteDivisionPrevisionalImpl impl = new EditarReporteDivisionPrevisionalImpl();

        try {
            sif012 = impl.obtenerDatosSif012PorRutId(idSelected, id, rut);
            return sif012;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException f) {
            f.printStackTrace();
        }

        return sif012;
    }

    /**
     * Funcion que obtiene los datos estaticos por rut, dependiendo del filtro
     * escogido.
     */
    public Sif012VO selectDataEstaticaRutEmpresaSif012(String id, String rut) {

        return EditarReporteDivisionPrevisionalImpl.selectDataEstaticaRutSif012(id, rut);
    }

    /** Funcion que elimina datos de la grilla. */
    public RespuestaVO eliminarRegistroIndividualSif012(String idSelected, String id, String rut) {

        return EditarReporteDivisionPrevisionalImpl.eliminarRegistroIndividualSif012(idSelected, id, rut);
    }

    public Sif012VO actualizarGrillaRutSif012(String idSelected, String rut) {

        return EditarReporteDivisionPrevisionalImpl.actualizarGrillaRutSif012(idSelected, rut);

    }

    public static Sif012VO actualizarGrillaCorrelativoSif012(String min, String max) {

        return EditarReporteDivisionPrevisionalImpl.actualizarGrillaCorrelativoSif012(min, max);
    }

    /** ********************************************************************************* */
    /** Funciones del formulario sif014 */
    public Sif014VO obtenerDatosSif014PorRut(String idSelectedItem, String rut) {

        EditarReporteDivisionPrevisionalImpl impl = new EditarReporteDivisionPrevisionalImpl();
        Sif014VO vo = new Sif014VO();

        try {

            vo = impl.obtenerDatosSif014PorRut(idSelectedItem, rut);

            return vo;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException f) {
            f.printStackTrace();
        }

        return vo;
    }

    public Sif014VO selectDataEstaticaRutSif014(String id, String rut) {

        return EditarReporteDivisionPrevisionalImpl.selectDataEstaticaRutSif014(id, rut);
    }

    /** Funcion que hace el update a la tabla Sif014Todo */
    public RespuestaVO updateSif014(String idSif014_glob, String mesInformado, String rutEmpresa, String dvEmpresa, String nombreEmpresa, String rutBeneficiario,
            String dvBeneficiario, String nombreBeneficiario, String tipoBeneficio, String tipoBeneficiario, String rutCausante, String dvCausante, String nombreCausante,
            String tipoCausante, String inicioPeriodoReintegro, String finPeriodoReintegro, String tipoReintegro, String montoTotalReintegro, String montoReintegroMes,
            String montoTotalDeuda, String fuenteOrigen) {

        LinSif014VO vo = new LinSif014VO();
        String inicio_periodo = "0";
        String final_periodo = "0";
        String entidad = global.getValor("SVG.entidad");
        String codArchivo = global.getValor("SVG.cod.sif014");
        int mesinformado = Integer.parseInt(mesInformado);

        if (inicioPeriodoReintegro != null && inicioPeriodoReintegro.length() == 10) {
            inicio_periodo = inicioPeriodoReintegro.substring(6, 10) + inicioPeriodoReintegro.substring(3, 5) + inicioPeriodoReintegro.substring(0, 2);
        }
        if (finPeriodoReintegro != null && finPeriodoReintegro.length() == 10) {
            final_periodo = finPeriodoReintegro.substring(6, 10) + finPeriodoReintegro.substring(3, 5) + finPeriodoReintegro.substring(0, 2);
        }

        vo.setId_sif014(Long.parseLong(idSif014_glob));

        vo.setRut_empresa(Long.parseLong(rutEmpresa));
        vo.setDv_empresa(dvEmpresa);
        vo.setNombre_empresa(nombreEmpresa);
        vo.setRut_beneficiario(Long.parseLong(rutBeneficiario));
        vo.setDv_beneficiario(dvBeneficiario);
        vo.setNombre_beneficiario(nombreBeneficiario);
        vo.setTipo_beneficio(Integer.parseInt(tipoBeneficio));
        vo.setTipo_beneficiario(Integer.parseInt(tipoBeneficiario));
        vo.setRut_causante(Long.parseLong(rutCausante));
        vo.setDv_causante(dvCausante);
        vo.setNombre_causante(nombreCausante);
        vo.setTipo_causante(Integer.parseInt(tipoCausante));
        vo.setInicio_period_reinte(Long.parseLong(inicio_periodo));
        vo.setFinal_period_reinte(Long.parseLong(final_periodo));
        vo.setTipo_reintegro(Integer.parseInt(tipoReintegro));
        vo.setMonto_total_reintegro(Long.parseLong(montoTotalReintegro));
        vo.setMonto_reintegro_mes(Long.parseLong(montoReintegroMes));
        vo.setMonto_total_deuda(Long.parseLong(montoTotalDeuda));
        vo.setFuente_origen(Integer.parseInt(fuenteOrigen));

        vo.setCodigo_entidad(Integer.parseInt(entidad));
        vo.setCodigo_archivo(Integer.parseInt(codArchivo));
        vo.setFecha_proceso(Integer.parseInt(mesInformado));
        vo.setReferencia_origen("");

        return EditarReporteDivisionPrevisionalImpl.updateSif014(vo);

    }

    /** Funcion que obtiene los datos a editar, filtrado por rut. */
    public Sif014VO obtenerDatosSif014PorRutId(String idSelected, String id, String rut) {

        return EditarReporteDivisionPrevisionalImpl.obtenerDatosSif014PorRutId(idSelected, id, rut);
    }

    /** Funcion que realiza una busqueda por rango de correlativo */
    public Sif014VO busquedaPorRangoSif014(String idSelected, String min, String max) {

        return EditarReporteDivisionPrevisionalImpl.busquedaPorRangoSif014(min, max);
    }

    /** Funcion que obtiene la data estatica filtrando por rango de correlativo */
    public Sif014VO dataEstaticaPorIdSif014(String min, String max) {

        return EditarReporteDivisionPrevisionalImpl.dataEstaticaPorIdSif014(min, max);
    }

    public Sif014VO actualizarGrillaSif014Correlativo(String id, String min, String max) {

        return EditarReporteDivisionPrevisionalImpl.busquedaPorRangoSif014(min, max);
    }

    public RespuestaVO eliminarRegistroIndividualSif014(String idSelected, String id, String rut) {

        return EditarReporteDivisionPrevisionalImpl.eliminarRegistroIndividualSif014(idSelected, id, rut);
    }

    /** Funcion que actualiza la grilla una vez que se ha eliminado por rut */
    public Sif014VO actualizarGrillaRutSif014(String idSelected, String rut) {

        return EditarReporteDivisionPrevisionalImpl.actualizarGrillaRutSif014(idSelected, rut);
    }

    /**
     * Funcion que actualiza la grilla cuando se ha eliminado un registro, dado
     * una busqueda por correlativo
     */
    public Sif014VO actualizarGrillaCorrelativoSif014(String min, String max) {

        return EditarReporteDivisionPrevisionalImpl.actualizarGrillaCorrelativoSif014(min, max);
    }

    /** ***************************************************************************************************** */
    public RespuestaVO deleteDisivionPrevisionalConRango(String idReporte, String rangoUno, String rangoDos) {

        return EditarReporteDivisionPrevisionalImpl.deleteDisivionPrevisionalConRango(idReporte, rangoUno, rangoDos);
    }

    public RespuestaVO deleteDivisionPrevisionalSinRango(String idReporte, String rangoUno) {

        return EditarReporteDivisionPrevisionalImpl.deleteDivisionPrevisionalSinRango(idReporte, rangoUno);
    }

    /** Funcion que obtiene un arreglo con los datos de la tabla codigo tramo. */
    public CodigoTramoVO[] obtenerDataCodigoTramo(String parametro) {

        ListadoParametros listaParam = ListadoParametros.getInstancia();
        CodigoTramoVO[] codigoTramo = new CodigoTramoVO[0];

        if ("CodigoTramo".equals(parametro)) {
            codigoTramo = listaParam.getListCodigoTramo();
            return codigoTramo;
        }

        return codigoTramo;
    }
}
