package cl.araucana.sivegam.struts.dwr.actions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import cl.araucana.sivegam.impl.GenerarInformeFinancieroImpl;
import cl.araucana.sivegam.vo.DevolucionDeSaldosVO;
import cl.araucana.sivegam.vo.DevolucionesVO;
import cl.araucana.sivegam.vo.EgresosVO;
import cl.araucana.sivegam.vo.InfoInformeFinancieroVO;
import cl.araucana.sivegam.vo.InformeFinancieroVO;
import cl.araucana.sivegam.vo.IngresosVO;
import cl.araucana.sivegam.vo.PagoDelMesVO;
import cl.araucana.sivegam.vo.PagosRetroactivosVO;
import cl.araucana.sivegam.vo.PeriodoProcesoVO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.param.ListadoParametros;

public class GenerarInformeFinancieroDWR {

    public RespuestaVO insertInformeFinanciero(String _periodo, String _codigoEntidad, String _nombreEntidad, String _fechaDeposito, String _provision, String _reintegro,
            String _totalIngresos, String _asigFamTrabActivos, String _asigFamPensionados, String _asigFamTrabCesantes, String _asigFamInstituciones, String _totalPagoDelMes,
            String _asigFamTrabActiv, String _asifFamTrabPens, String _asigFamTrabCes, String _asigFamTrabInst, String _totalPagosRetroactivos, String _docRevalidados,
            String _comisionAdministracion, String _totalEgresos, String _documentosCaducados, String _documentosAnulados, String _totalDevoluciones, String _TotalD,
            String _saldoFavorEmpleador, String _devolucionDocSAFEMCaducados, String _devolucionDocSAFEMAnulados, String _documentosSAFEMRevalidados, String _totalDevolucionesE,
            String _totalF) {

        String provision = "";
        String reintegro = "";
        String totalIngresos = "";
        String asigFamTrabActivos = "";
        String asigFamPensionados = "";
        String asigFamTrabCesantes = "";
        String asigFamInstituciones = "";
        String totalPagoDelMes = "";
        String asigFamTrabActiv = "";
        String asifFamTrabPens = "";
        String asigFamTrabCes = "";
        String asigFamTrabInst = "";
        String totalPagosRetroactivos = "";
        String docRevalidados = "";
        String comisionAdministracion = "";
        String totalEgresos = "";
        String documentosCaducados = "";
        String documentosAnulados = "";
        String totalDevoluciones = "";
        String TotalD = "";
        String saldoFavorEmpleador = "";
        String devolucionDocSAFEMCaducados = "";
        String devolucionDocSAFEMAnulados = "";
        String documentosSAFEMRevalidados = "";
        String totalDevolucionesE = "";
        String totalF = "";

        StringTokenizer st = new StringTokenizer(_provision, ".");
        while (st.hasMoreTokens()) {
            provision = provision + st.nextToken();
        }

        st = new StringTokenizer(_reintegro, ".");
        while (st.hasMoreTokens()) {
            reintegro = reintegro + st.nextToken();
        }

        st = new StringTokenizer(_totalIngresos, ".");
        while (st.hasMoreTokens()) {
            totalIngresos = totalIngresos + st.nextToken();
        }

        st = new StringTokenizer(_asigFamTrabActivos, ".");
        while (st.hasMoreTokens()) {
            asigFamTrabActivos = asigFamTrabActivos + st.nextToken();
        }

        st = new StringTokenizer(_asigFamPensionados, ".");
        while (st.hasMoreTokens()) {
            asigFamPensionados = asigFamPensionados + st.nextToken();
        }

        st = new StringTokenizer(_asigFamTrabCesantes, ".");
        while (st.hasMoreTokens()) {
            asigFamTrabCesantes = asigFamTrabCesantes + st.nextToken();
        }

        st = new StringTokenizer(_asigFamInstituciones, ".");
        while (st.hasMoreTokens()) {
            asigFamInstituciones = asigFamInstituciones + st.nextToken();
        }

        st = new StringTokenizer(_totalPagoDelMes, ".");
        while (st.hasMoreTokens()) {
            totalPagoDelMes = totalPagoDelMes + st.nextToken();
        }

        st = new StringTokenizer(_asigFamTrabActiv, ".");
        while (st.hasMoreTokens()) {
            asigFamTrabActiv = asigFamTrabActiv + st.nextToken();
        }

        st = new StringTokenizer(_asifFamTrabPens, ".");
        while (st.hasMoreTokens()) {
            asifFamTrabPens = asifFamTrabPens + st.nextToken();
        }

        st = new StringTokenizer(_asigFamTrabCes, ".");
        while (st.hasMoreTokens()) {
            asigFamTrabCes = asigFamTrabCes + st.nextToken();
        }

        st = new StringTokenizer(_asigFamTrabInst, ".");
        while (st.hasMoreTokens()) {
            asigFamTrabInst = asigFamTrabInst + st.nextToken();
        }

        st = new StringTokenizer(_totalPagosRetroactivos, ".");
        while (st.hasMoreTokens()) {
            totalPagosRetroactivos = totalPagosRetroactivos + st.nextToken();
        }

        st = new StringTokenizer(_docRevalidados, ".");
        while (st.hasMoreTokens()) {
            docRevalidados = docRevalidados + st.nextToken();
        }

        st = new StringTokenizer(_comisionAdministracion, ".");
        while (st.hasMoreTokens()) {
            comisionAdministracion = comisionAdministracion + st.nextToken();
        }

        st = new StringTokenizer(_totalEgresos, ".");
        while (st.hasMoreTokens()) {
            totalEgresos = totalEgresos + st.nextToken();
        }

        st = new StringTokenizer(_documentosCaducados, ".");
        while (st.hasMoreTokens()) {
            documentosCaducados = documentosCaducados + st.nextToken();
        }

        st = new StringTokenizer(_documentosAnulados, ".");
        while (st.hasMoreTokens()) {
            documentosAnulados = documentosAnulados + st.nextToken();
        }

        st = new StringTokenizer(_totalDevoluciones, ".");
        while (st.hasMoreTokens()) {
            totalDevoluciones = totalDevoluciones + st.nextToken();
        }

        st = new StringTokenizer(_TotalD, ".");
        while (st.hasMoreTokens()) {
            TotalD = TotalD + st.nextToken();
        }

        st = new StringTokenizer(_saldoFavorEmpleador, ".");
        while (st.hasMoreTokens()) {
            saldoFavorEmpleador = saldoFavorEmpleador + st.nextToken();
        }

        st = new StringTokenizer(_devolucionDocSAFEMCaducados, ".");
        while (st.hasMoreTokens()) {
            devolucionDocSAFEMCaducados = devolucionDocSAFEMCaducados + st.nextToken();
        }

        st = new StringTokenizer(_devolucionDocSAFEMAnulados, ".");
        while (st.hasMoreTokens()) {
            devolucionDocSAFEMAnulados = devolucionDocSAFEMAnulados + st.nextToken();
        }

        st = new StringTokenizer(_documentosSAFEMRevalidados, ".");
        while (st.hasMoreTokens()) {
            documentosSAFEMRevalidados = documentosSAFEMRevalidados + st.nextToken();
        }

        st = new StringTokenizer(_totalDevolucionesE, ".");
        while (st.hasMoreTokens()) {
            totalDevolucionesE = totalDevolucionesE + st.nextToken();
        }

        st = new StringTokenizer(_totalF, ".");
        while (st.hasMoreTokens()) {
            totalF = totalF + st.nextToken();
        }

        /*
         * RespuestaVO resp = new RespuestaVO(); resp.setCodRespuesta(0); return
         * resp;
         */
        String fecha = "";
        Date dateProceso = new Date();
        String DATE_FORMAT2 = "dd/MM/yyyy";
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        InfoInformeFinancieroVO informacionFinanciera = new InfoInformeFinancieroVO();
        IngresosVO ingreso = new IngresosVO();
        EgresosVO egreso = new EgresosVO();
        PagoDelMesVO pagoDelMes = new PagoDelMesVO();
        PagosRetroactivosVO pagoRetroactivo = new PagosRetroactivosVO();
        DevolucionesVO devoluciones = new DevolucionesVO();
        DevolucionDeSaldosVO devolucionSaldos = new DevolucionDeSaldosVO();
        InformeFinancieroVO informeFinanciero = new InformeFinancieroVO();

        informacionFinanciera.setPeriodo(_periodo);
        informacionFinanciera.setCodigoEntidad(Integer.parseInt(_codigoEntidad));
        informacionFinanciera.setNombreEntidad(_nombreEntidad);

        try {
            fecha = _fechaDeposito;
            dateProceso = sdf2.parse(fecha);
            informacionFinanciera.setFecDepositoExcedenteDate(dateProceso);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        informacionFinanciera.setTotalSuperAvitDeficit(Long.parseLong(TotalD));
        informacionFinanciera.setTotalSuperAvitDeficitFinal(Long.parseLong(totalF));

        ingreso.setProvision(Long.parseLong(provision));
        ingreso.setReintegro(Long.parseLong(reintegro));
        ingreso.setTotalIngresos(Long.parseLong(totalIngresos));

        pagoDelMes.setAsigFamTrabActivos(Long.parseLong(asigFamTrabActivos));
        pagoDelMes.setAsigFamPensionados(Long.parseLong(asigFamPensionados));
        pagoDelMes.setAsigFamTrabCesantes(Long.parseLong(asigFamTrabCesantes));
        pagoDelMes.setAsigFamInstituciones(Long.parseLong(asigFamInstituciones));
        pagoDelMes.setTotalPagoDelMes(Long.parseLong(totalPagoDelMes));

        pagoRetroactivo.setAsigFamTrabActiv(Long.parseLong(asigFamTrabActiv));
        pagoRetroactivo.setAsifFamTrabPens(Long.parseLong(asifFamTrabPens));
        pagoRetroactivo.setAsigFamTrabCes(Long.parseLong(asigFamTrabCes));
        pagoRetroactivo.setAsigFamTrabInst(Long.parseLong(asigFamTrabInst));
        pagoRetroactivo.setTotalPagosRetroactivos(Long.parseLong(totalPagosRetroactivos));

        egreso.setDocRevalidados(Long.parseLong(docRevalidados));
        egreso.setComisionAdministracion(Long.parseLong(comisionAdministracion));
        egreso.setTotalEgresos(Long.parseLong(totalEgresos));

        devoluciones.setDocumentosCaducados(Long.parseLong(documentosCaducados));
        devoluciones.setDocumentosAnulados(Long.parseLong(documentosAnulados));
        devoluciones.setTotalDevoluciones(Long.parseLong(totalDevoluciones));

        devolucionSaldos.setSaldoFavorEmpleador(Long.parseLong(saldoFavorEmpleador));
        devolucionSaldos.setDevolucionDocSAFEMCaducados(Long.parseLong(devolucionDocSAFEMCaducados));
        devolucionSaldos.setDevolucionDocSAFEMAnulados(Long.parseLong(devolucionDocSAFEMAnulados));
        devolucionSaldos.setDocumentosSAFEMRevalidados(Long.parseLong(documentosSAFEMRevalidados));
        devolucionSaldos.setTotalDevolucionesE(Long.parseLong(totalDevolucionesE));

        informeFinanciero.setInformacionInformeFinancieroVO(informacionFinanciera);
        informeFinanciero.setIngresoVO(ingreso);
        informeFinanciero.setEgresosVO(egreso);
        informeFinanciero.setPagoDelMesVO(pagoDelMes);
        informeFinanciero.setPagosRetroVO(pagoRetroactivo);
        informeFinanciero.setDevolucionesVO(devoluciones);
        informeFinanciero.setDevolucionSaldosVO(devolucionSaldos);

        return GenerarInformeFinancieroImpl.insertInformeFinanciero(informeFinanciero);

    }

    public RespuestaVO obtenerMesInformado(String mesInformado) {

        RespuestaVO respuesta = new RespuestaVO();
        ListadoParametros listaParam = ListadoParametros.getInstancia();
        PeriodoProcesoVO[] periodoProceso = listaParam.getListPeriodoProcesos();

        for (int i = 0; i < periodoProceso.length; i++) {
            if (periodoProceso[i].getPeriodo_proceso() == Integer.parseInt(mesInformado)) {
                respuesta.setPeriodoProceso(periodoProceso[i].getDescripcion_periodo_proceso());
                respuesta.setCodRespuesta(0);
                break;
            }
        }

        return respuesta;
    }

    public InformeFinancieroVO gettInformeFinanciero(String periodoDelInforme) {

        return GenerarInformeFinancieroImpl.gettInformeFinanciero(periodoDelInforme);
    }

    public RespuestaVO updateInformeFinanciero(String _idInfoInformeFinanciero, String _idEgresos, String _periodo, String _codigoEntidad, String _nombreEntidad,
            String _fechaDeposito, String _provision, String _reintegro, String _totalIngresos, String _asigFamTrabActivos, String _asigFamPensionados,
            String _asigFamTrabCesantes, String _asigFamInstituciones, String _totalPagoDelMes, String _asigFamTrabActiv, String _asifFamTrabPens, String _asigFamTrabCes,
            String _asigFamTrabInst, String _totalPagosRetroactivos, String _docRevalidados, String _comisionAdministracion, String _totalEgresos, String _documentosCaducados,
            String _documentosAnulados, String _totalDevoluciones, String _TotalD, String _saldoFavorEmpleador, String _devolucionDocSAFEMCaducados,
            String _devolucionDocSAFEMAnulados, String _documentosSAFEMRevalidados, String _totalDevolucionesE, String _totalF) {

        String provision = "";
        String reintegro = "";
        String totalIngresos = "";
        String asigFamTrabActivos = "";
        String asigFamPensionados = "";
        String asigFamTrabCesantes = "";
        String asigFamInstituciones = "";
        String totalPagoDelMes = "";
        String asigFamTrabActiv = "";
        String asifFamTrabPens = "";
        String asigFamTrabCes = "";
        String asigFamTrabInst = "";
        String totalPagosRetroactivos = "";
        String docRevalidados = "";
        String comisionAdministracion = "";
        String totalEgresos = "";
        String documentosCaducados = "";
        String documentosAnulados = "";
        String totalDevoluciones = "";
        String TotalD = "";
        String saldoFavorEmpleador = "";
        String devolucionDocSAFEMCaducados = "";
        String devolucionDocSAFEMAnulados = "";
        String documentosSAFEMRevalidados = "";
        String totalDevolucionesE = "";
        String totalF = "";

        StringTokenizer st = new StringTokenizer(_provision, ".");
        while (st.hasMoreTokens()) {
            provision = provision + st.nextToken();
        }

        st = new StringTokenizer(_reintegro, ".");
        while (st.hasMoreTokens()) {
            reintegro = reintegro + st.nextToken();
        }

        st = new StringTokenizer(_totalIngresos, ".");
        while (st.hasMoreTokens()) {
            totalIngresos = totalIngresos + st.nextToken();
        }

        st = new StringTokenizer(_asigFamTrabActivos, ".");
        while (st.hasMoreTokens()) {
            asigFamTrabActivos = asigFamTrabActivos + st.nextToken();
        }

        st = new StringTokenizer(_asigFamPensionados, ".");
        while (st.hasMoreTokens()) {
            asigFamPensionados = asigFamPensionados + st.nextToken();
        }

        st = new StringTokenizer(_asigFamTrabCesantes, ".");
        while (st.hasMoreTokens()) {
            asigFamTrabCesantes = asigFamTrabCesantes + st.nextToken();
        }

        st = new StringTokenizer(_asigFamInstituciones, ".");
        while (st.hasMoreTokens()) {
            asigFamInstituciones = asigFamInstituciones + st.nextToken();
        }

        st = new StringTokenizer(_totalPagoDelMes, ".");
        while (st.hasMoreTokens()) {
            totalPagoDelMes = totalPagoDelMes + st.nextToken();
        }

        st = new StringTokenizer(_asigFamTrabActiv, ".");
        while (st.hasMoreTokens()) {
            asigFamTrabActiv = asigFamTrabActiv + st.nextToken();
        }

        st = new StringTokenizer(_asifFamTrabPens, ".");
        while (st.hasMoreTokens()) {
            asifFamTrabPens = asifFamTrabPens + st.nextToken();
        }

        st = new StringTokenizer(_asigFamTrabCes, ".");
        while (st.hasMoreTokens()) {
            asigFamTrabCes = asigFamTrabCes + st.nextToken();
        }

        st = new StringTokenizer(_asigFamTrabInst, ".");
        while (st.hasMoreTokens()) {
            asigFamTrabInst = asigFamTrabInst + st.nextToken();
        }

        st = new StringTokenizer(_totalPagosRetroactivos, ".");
        while (st.hasMoreTokens()) {
            totalPagosRetroactivos = totalPagosRetroactivos + st.nextToken();
        }

        st = new StringTokenizer(_docRevalidados, ".");
        while (st.hasMoreTokens()) {
            docRevalidados = docRevalidados + st.nextToken();
        }

        st = new StringTokenizer(_comisionAdministracion, ".");
        while (st.hasMoreTokens()) {
            comisionAdministracion = comisionAdministracion + st.nextToken();
        }

        st = new StringTokenizer(_totalEgresos, ".");
        while (st.hasMoreTokens()) {
            totalEgresos = totalEgresos + st.nextToken();
        }

        st = new StringTokenizer(_documentosCaducados, ".");
        while (st.hasMoreTokens()) {
            documentosCaducados = documentosCaducados + st.nextToken();
        }

        st = new StringTokenizer(_documentosAnulados, ".");
        while (st.hasMoreTokens()) {
            documentosAnulados = documentosAnulados + st.nextToken();
        }

        st = new StringTokenizer(_totalDevoluciones, ".");
        while (st.hasMoreTokens()) {
            totalDevoluciones = totalDevoluciones + st.nextToken();
        }

        st = new StringTokenizer(_TotalD, ".");
        while (st.hasMoreTokens()) {
            TotalD = TotalD + st.nextToken();
        }

        st = new StringTokenizer(_saldoFavorEmpleador, ".");
        while (st.hasMoreTokens()) {
            saldoFavorEmpleador = saldoFavorEmpleador + st.nextToken();
        }

        st = new StringTokenizer(_devolucionDocSAFEMCaducados, ".");
        while (st.hasMoreTokens()) {
            devolucionDocSAFEMCaducados = devolucionDocSAFEMCaducados + st.nextToken();
        }

        st = new StringTokenizer(_devolucionDocSAFEMAnulados, ".");
        while (st.hasMoreTokens()) {
            devolucionDocSAFEMAnulados = devolucionDocSAFEMAnulados + st.nextToken();
        }

        st = new StringTokenizer(_documentosSAFEMRevalidados, ".");
        while (st.hasMoreTokens()) {
            documentosSAFEMRevalidados = documentosSAFEMRevalidados + st.nextToken();
        }

        st = new StringTokenizer(_totalDevolucionesE, ".");
        while (st.hasMoreTokens()) {
            totalDevolucionesE = totalDevolucionesE + st.nextToken();
        }

        st = new StringTokenizer(_totalF, ".");
        while (st.hasMoreTokens()) {
            totalF = totalF + st.nextToken();
        }

        String fecha = "";
        Date dateProceso = new Date();
        String DATE_FORMAT2 = "dd/MM/yyyy";
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        InfoInformeFinancieroVO informacionFinanciera = new InfoInformeFinancieroVO();
        IngresosVO ingreso = new IngresosVO();
        EgresosVO egreso = new EgresosVO();
        PagoDelMesVO pagoDelMes = new PagoDelMesVO();
        PagosRetroactivosVO pagoRetroactivo = new PagosRetroactivosVO();
        DevolucionesVO devoluciones = new DevolucionesVO();
        DevolucionDeSaldosVO devolucionSaldos = new DevolucionDeSaldosVO();
        InformeFinancieroVO informeFinanciero = new InformeFinancieroVO();

        informacionFinanciera.setIdInformeFinanciero(Long.parseLong(_idInfoInformeFinanciero));
        informacionFinanciera.setPeriodo(_periodo);
        informacionFinanciera.setCodigoEntidad(Integer.parseInt(_codigoEntidad));
        informacionFinanciera.setNombreEntidad(_nombreEntidad);

        try {
            fecha = _fechaDeposito;
            dateProceso = sdf2.parse(fecha);
            informacionFinanciera.setFecDepositoExcedenteDate(dateProceso);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        informacionFinanciera.setTotalSuperAvitDeficit(Long.parseLong(TotalD));
        informacionFinanciera.setTotalSuperAvitDeficitFinal(Long.parseLong(totalF));

        ingreso.setIdInformeFinanciero(Long.parseLong(_idInfoInformeFinanciero));
        ingreso.setProvision(Long.parseLong(provision));
        ingreso.setReintegro(Long.parseLong(reintegro));
        ingreso.setTotalIngresos(Long.parseLong(totalIngresos));

        pagoDelMes.setIdEgresos(Long.parseLong(_idEgresos));
        pagoDelMes.setAsigFamTrabActivos(Long.parseLong(asigFamTrabActivos));
        pagoDelMes.setAsigFamPensionados(Long.parseLong(asigFamPensionados));
        pagoDelMes.setAsigFamTrabCesantes(Long.parseLong(asigFamTrabCesantes));
        pagoDelMes.setAsigFamInstituciones(Long.parseLong(asigFamInstituciones));
        pagoDelMes.setTotalPagoDelMes(Long.parseLong(totalPagoDelMes));

        pagoRetroactivo.setIdEgresos(Long.parseLong(_idEgresos));
        pagoRetroactivo.setAsigFamTrabActiv(Long.parseLong(asigFamTrabActiv));
        pagoRetroactivo.setAsifFamTrabPens(Long.parseLong(asifFamTrabPens));
        pagoRetroactivo.setAsigFamTrabCes(Long.parseLong(asigFamTrabCes));
        pagoRetroactivo.setAsigFamTrabInst(Long.parseLong(asigFamTrabInst));
        pagoRetroactivo.setTotalPagosRetroactivos(Long.parseLong(totalPagosRetroactivos));

        egreso.setIdInformeFinanciero(Long.parseLong(_idInfoInformeFinanciero));
        egreso.setDocRevalidados(Long.parseLong(docRevalidados));
        egreso.setComisionAdministracion(Long.parseLong(comisionAdministracion));
        egreso.setTotalEgresos(Long.parseLong(totalEgresos));

        devoluciones.setIdInformeFinanciero(Long.parseLong(_idInfoInformeFinanciero));
        devoluciones.setDocumentosCaducados(Long.parseLong(documentosCaducados));
        devoluciones.setDocumentosAnulados(Long.parseLong(documentosAnulados));
        devoluciones.setTotalDevoluciones(Long.parseLong(totalDevoluciones));

        devolucionSaldos.setIdInformeFinanciero(Long.parseLong(_idInfoInformeFinanciero));
        devolucionSaldos.setSaldoFavorEmpleador(Long.parseLong(saldoFavorEmpleador));
        devolucionSaldos.setDevolucionDocSAFEMCaducados(Long.parseLong(devolucionDocSAFEMCaducados));
        devolucionSaldos.setDevolucionDocSAFEMAnulados(Long.parseLong(devolucionDocSAFEMAnulados));
        devolucionSaldos.setDocumentosSAFEMRevalidados(Long.parseLong(documentosSAFEMRevalidados));
        devolucionSaldos.setTotalDevolucionesE(Long.parseLong(totalDevolucionesE));

        informeFinanciero.setInformacionInformeFinancieroVO(informacionFinanciera);
        informeFinanciero.setIngresoVO(ingreso);
        informeFinanciero.setEgresosVO(egreso);
        informeFinanciero.setPagoDelMesVO(pagoDelMes);
        informeFinanciero.setPagosRetroVO(pagoRetroactivo);
        informeFinanciero.setDevolucionesVO(devoluciones);
        informeFinanciero.setDevolucionSaldosVO(devolucionSaldos);

        return GenerarInformeFinancieroImpl.updateInformeFinanciero(informeFinanciero);

    }

    /** Funcion que obtiene los meses correspondiente a la tabla periodoprocesos. */
    public PeriodoProcesoVO[] obtenerDataPeriodoProceso(String glosa) {

        ListadoParametros listaParam = ListadoParametros.getInstancia();
        PeriodoProcesoVO[] periodoProceso = new PeriodoProcesoVO[0];

        if ("PeriodoProceso".equals(glosa)) {
            periodoProceso = listaParam.getListPeriodoProcesos();

            return periodoProceso;
        }

        return periodoProceso;
    }
}
