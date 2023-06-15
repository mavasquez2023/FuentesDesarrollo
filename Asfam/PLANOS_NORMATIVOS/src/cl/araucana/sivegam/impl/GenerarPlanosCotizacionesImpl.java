package cl.araucana.sivegam.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cl.araucana.sivegam.dao.GenerarPlanosCotizacionesDAO;
import cl.araucana.sivegam.dao.GenerarReportesDAO;
import cl.araucana.sivegam.helper.IND_Constants;
import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.vo.DetalleReporteSivegamVO;
import cl.araucana.sivegam.vo.LinSif016VO;
import cl.araucana.sivegam.vo.PlanoVO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.Sif016VO;
import cl.araucana.sivegam.vo.LinSif017VO;
import cl.araucana.sivegam.vo.Sif017VO;
import cl.araucana.sivegam.vo.LinSif018VO;
import cl.araucana.sivegam.vo.Sif018VO;
import cl.araucana.sivegam.vo.LinSif019VO;
import cl.araucana.sivegam.vo.Sif019VO;

public class GenerarPlanosCotizacionesImpl {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    /* Funcion que genera el reporte requerido (para SIF016 en formato TXT). */
    public void generarReporteSif016Txt(String archivo, LinSif016VO[] lineas) throws IOException {

        System.gc();
        //String nuevaLinea = "\n";
        String nuevaLinea = "\r\n";
        String pipe = "|";

        FileWriter fw = new FileWriter(archivo);
        StringBuffer linea = new StringBuffer("");

        for (int i = 0; i < lineas.length; i++) {

            linea = new StringBuffer("");
            LinSif016VO registro = new LinSif016VO();
            registro = lineas[i];

            String fecha_proceso = Integer.toString(registro.getFecha_proceso());
            String codigo_entidad = Integer.toString(registro.getCodigo_entidad());
            String codigo_archivo = Integer.toString(registro.getCodigo_archivo());
            String mes_recaudacion = Integer.toString(registro.getMes_recaudacion());
            String mes_remuneracion = Integer.toString(registro.getMes_remuneracion());
            String cod_tipo_declaracion = Integer.toString(registro.getCod_tipo_declaracion());
            String numero_declaracion = registro.getNumero_declaracion();
            String fech_declaracion = Long.toString(registro.getFech_declaracion());
            String rut_empresa = Long.toString(registro.getRut_empresa());
            String dv_empresa = registro.getDv_empresa();
            String nombre_empresa = formatPipeSpace(registro.getNombre_empresa());
            String num_total_trabajador = Integer.toString(registro.getNum_total_trabajador());
            String num_total_cargas = Integer.toString(registro.getNum_total_cargas());
            String cargas_retroactivas = Integer.toString(registro.getCargas_retroactivas());
            String mto_asfam_mes = Long.toString(registro.getMto_asfam_mes());
            String mto_asfam_mes_retro = Long.toString(registro.getMto_asfam_mes_retro());
            String mto_reintegros_mes = Long.toString(registro.getMto_reintegros_mes());
            String total_pago_asigfam = Long.toString(registro.getTotal_pago_asigfam());
            String total_de_cotizacion = Long.toString(registro.getTotal_de_cotizacion());
            String otros_descuentos = Long.toString(registro.getOtros_descuentos());
            String resultado_neto = Long.toString(registro.getResultado_neto());
            String tipo_de_saldo = Integer.toString(registro.getTipo_de_saldo());
            String modalidad_pago = Integer.toString(registro.getModalidad_pago());
            String monto_documento = Long.toString(registro.getMonto_documento());
            String numero_serie = "";
            String numero_documento = "";
            String fecha_emision_documento = "";
            String codigo_banco = "";
            if (Long.parseLong(monto_documento) > 0) {

                numero_serie = registro.getNumero_serie();
                numero_documento = registro.getNumero_documento();
                fecha_emision_documento = Long.toString(registro.getFech_emision_doc());
                codigo_banco = Integer.toString(registro.getCodigo_banco());
                numero_serie = numero_serie.trim();
                numero_documento = numero_documento.trim();

                if ("0".equals(fecha_emision_documento)) {
                    fecha_emision_documento = "";
                }

            } else {
                monto_documento = "";
                modalidad_pago = "";
            }
            numero_declaracion = numero_declaracion.trim();

            if ("0".equals(fech_declaracion)) {
                fech_declaracion = " ";
            }

            nombre_empresa = nombre_empresa.trim();

            linea.append(fecha_proceso);
            linea.append(pipe);
            linea.append(codigo_entidad);
            linea.append(pipe);
            linea.append(codigo_archivo);
            linea.append(pipe);
            linea.append(mes_recaudacion);
            linea.append(pipe);
            linea.append(mes_remuneracion);
            linea.append(pipe);
            linea.append(cod_tipo_declaracion);
            linea.append(pipe);
            linea.append(numero_declaracion);
            linea.append(pipe);
            linea.append(fech_declaracion);
            linea.append(pipe);
            linea.append(rut_empresa);
            linea.append(pipe);
            linea.append(dv_empresa);
            linea.append(pipe);
            linea.append(nombre_empresa);
            linea.append(pipe);
            linea.append(num_total_trabajador);
            linea.append(pipe);
            linea.append(num_total_cargas);
            linea.append(pipe);
            linea.append(cargas_retroactivas);
            linea.append(pipe);
            linea.append(mto_asfam_mes);
            linea.append(pipe);
            linea.append(mto_asfam_mes_retro);
            linea.append(pipe);
            linea.append(mto_reintegros_mes);
            linea.append(pipe);
            linea.append(total_pago_asigfam);
            linea.append(pipe);
            linea.append(total_de_cotizacion);
            linea.append(pipe);
            linea.append(otros_descuentos);
            linea.append(pipe);
            linea.append(resultado_neto);
            linea.append(pipe);
            linea.append(tipo_de_saldo);
            linea.append(pipe);
            linea.append(modalidad_pago);
            linea.append(pipe);
            linea.append(monto_documento);
            linea.append(pipe);
            linea.append(numero_serie);
            linea.append(pipe);
            linea.append(numero_documento);
            linea.append(pipe);
            linea.append(fecha_emision_documento);
            linea.append(pipe);
            linea.append(codigo_banco);
            linea.append(nuevaLinea);

            fw.write(linea.toString());
        }

        System.gc();
        linea = new StringBuffer("");
        fw.close();

    }

    /* Funcion que genera el reporte requerido (para SIF018 en formato TXT). */
    public void generarReporteSif017Txt(String archivo, LinSif017VO[] lineas) throws IOException {

        System.gc();
        //String nuevaLinea = "\n";
        String nuevaLinea = "\r\n";
        String pipe = "|";

        FileWriter fw = new FileWriter(archivo);
        StringBuffer linea = new StringBuffer("");

        for (int i = 0; i < lineas.length; i++) {

            linea = new StringBuffer("");
            LinSif017VO registro = new LinSif017VO();
            registro = lineas[i];

            String fecha_proceso = Integer.toString(registro.getFecha_proceso());
            String codigo_entidad = Integer.toString(registro.getCodigo_entidad());
            String codigo_archivo = Integer.toString(registro.getCodigo_archivo());
            String rut_empresa = Long.toString(registro.getRut_empresa());
            String dv_empresa = registro.getDv_empresa();
            String nombre_empresa = formatPipeSpace(registro.getNombre_empresa());
            String cod_tipo_egreso = Integer.toString(registro.getCod_tipo_egreso());
            String modalidad_de_pago = Integer.toString(registro.getModalidad_de_pago());
            String monto_documento = Long.toString(registro.getMonto_documento());
            String numero_serie = registro.getNumero_serie();
            String numero_documento = registro.getNumero_documento();
            String fech_emision_doc = Long.toString(registro.getFech_emision_doc());
            String codigo_banco = Integer.toString(registro.getCodigo_banco());
            String numero_cartola = registro.getNumero_cartola();
            String estado_documento = Integer.toString(registro.getEstado_documento());
            String fecha_rendicion = Long.toString(registro.getFecha_rendicion());

            nombre_empresa = nombre_empresa.trim();
            numero_serie = numero_serie.trim();
            numero_documento = numero_documento.trim();

            if ("0".equals(fech_emision_doc)) {
                fech_emision_doc = " ";
            }

            numero_cartola = numero_cartola.trim();

            if ("0".equals(fecha_rendicion)) {
                fecha_rendicion = " ";
            }

            linea.append(fecha_proceso);
            linea.append(pipe);
            linea.append(codigo_entidad);
            linea.append(pipe);
            linea.append(codigo_archivo);
            linea.append(pipe);
            linea.append(rut_empresa);
            linea.append(pipe);
            linea.append(dv_empresa);
            linea.append(pipe);
            linea.append(nombre_empresa);
            linea.append(pipe);
            linea.append(cod_tipo_egreso);
            linea.append(pipe);
            linea.append(modalidad_de_pago);
            linea.append(pipe);
            linea.append(monto_documento);
            linea.append(pipe);
            linea.append(numero_serie);
            linea.append(pipe);
            linea.append(numero_documento);
            linea.append(pipe);
            linea.append(fech_emision_doc);
            linea.append(pipe);
            linea.append(codigo_banco);
            linea.append(pipe);
            linea.append(numero_cartola);
            linea.append(pipe);
            linea.append(estado_documento);
            linea.append(pipe);
            linea.append(fecha_rendicion);
            linea.append(nuevaLinea);

            fw.write(linea.toString());
        }

        System.gc();
        linea = new StringBuffer("");
        fw.close();

    }

    /* Funcion que genera el reporte requerido (para SIF018 en formato TXT). */
    public void generarReporteSif018Txt(String archivo, LinSif018VO[] lineas) throws IOException {

        System.gc();
        //String nuevaLinea = "\n";
        String nuevaLinea = "\r\n";
        String pipe = "|";

        FileWriter fw = new FileWriter(archivo);
        StringBuffer linea = new StringBuffer("");

        for (int i = 0; i < lineas.length; i++) {

            linea = new StringBuffer("");
            LinSif018VO registro = new LinSif018VO();
            registro = lineas[i];

            String fecha_proceso = Integer.toString(registro.getFecha_proceso());
            String codigo_entidad = Integer.toString(registro.getCodigo_entidad());
            String codigo_archivo = Integer.toString(registro.getCodigo_archivo());
            String rut_empleador = Long.toString(registro.getRut_empleador());
            String dv_empleador = registro.getDv_empleador();
            String nombre_empleador = formatPipeSpace(registro.getNombre_empleador());
            String mod_pago = Integer.toString(registro.getMod_pago());
            String monto_documento = Long.toString(registro.getMonto_documento());
            String numero_serie = registro.getNumero_serie();
            String numero_documento = registro.getNumero_documento();
            String fecha_emision_documento = Long.toString(registro.getFecha_emision_documento());
            String codigo_banco = Integer.toString(registro.getCodigo_banco());
            String fecha_cobro = Long.toString(registro.getFecha_cobro());

            nombre_empleador = nombre_empleador.trim();
            numero_serie = numero_serie.trim();
            numero_documento = numero_documento.trim();

            if ("0".equals(fecha_emision_documento)) {
                fecha_emision_documento = " ";
            }

            if ("0".equals(fecha_cobro)) {
                fecha_cobro = " ";
            }

            linea.append(fecha_proceso);
            linea.append(pipe);
            linea.append(codigo_entidad);
            linea.append(pipe);
            linea.append(codigo_archivo);
            linea.append(pipe);
            linea.append(rut_empleador);
            linea.append(pipe);
            linea.append(dv_empleador);
            linea.append(pipe);
            linea.append(nombre_empleador);
            linea.append(pipe);
            linea.append(mod_pago);
            linea.append(pipe);
            linea.append(monto_documento);
            linea.append(pipe);
            linea.append(numero_serie);
            linea.append(pipe);
            linea.append(numero_documento);
            linea.append(pipe);
            linea.append(fecha_emision_documento);
            linea.append(pipe);
            linea.append(codigo_banco);
            linea.append(pipe);
            linea.append(fecha_cobro);
            linea.append(nuevaLinea);

            fw.write(linea.toString());
        }

        System.gc();
        linea = new StringBuffer("");
        fw.close();

    }

    /* Funcion que genera el reporte requerido (para SIF018 en formato TXT). */
    public void generarReporteSif019Txt(String archivo, LinSif019VO[] lineas) throws IOException {

        System.gc();
        //String nuevaLinea = "\n";
        String nuevaLinea = "\r\n";
        String pipe = "|";

        FileWriter fw = new FileWriter(archivo);
        StringBuffer linea = new StringBuffer("");

 //       logger.debug("numero lineas: " + lineas.length);
        for (int i = 0; i < lineas.length; i++) {

            linea = new StringBuffer("");
            LinSif019VO registro = new LinSif019VO();
            registro = lineas[i];

            String fecha_proceso = Integer.toString(registro.getFecha_proceso());
            String codigo_entidad = Integer.toString(registro.getCodigo_entidad());
            String codigo_archivo = Integer.toString(registro.getCodigo_archivo());
            String rut_empresa = Long.toString(registro.getRut_empresa());
            String dv_empresa = registro.getDv_empresa();
            String nombre_empresa = formatPipeSpace(registro.getNombre_empresa());
            String mes_origen_gasto = Long.toString(registro.getMes_origen_gasto());
            String estado_doc_orig = Integer.toString(registro.getEstado_doc_orig());
            String modo_pago_orig = Integer.toString(registro.getModo_pago_orig());
            String num_serie_orig = registro.getNum_serie_orig();
            String num_docum_orig = Long.toString(registro.getNum_docum_orig());
            String monto_docum_orig = Long.toString(registro.getMonto_docum_orig());
            String fecha_emision_orig = Long.toString(registro.getFecha_emision_orig());
            String codigo_banco_orig = Integer.toString(registro.getCodigo_banco_orig());
            String modo_pago_nuevo = Integer.toString(registro.getModo_pago_nuevo());
            String num_serie_nuevo = registro.getNum_serie_nuevo();
            String num_docum_nuevo = Long.toString(registro.getNum_docum_nuevo());
            String monto_docum_nuevo = Long.toString(registro.getMonto_docum_nuevo());
            String fecha_emision_nuevo = Long.toString(registro.getFecha_emision_nuevo());
            String codigo_banco_nuevo = Integer.toString(registro.getCodigo_banco_nuevo());

            nombre_empresa = nombre_empresa.trim();
            num_serie_orig = num_serie_orig.trim();
            num_docum_orig = num_docum_orig.trim();
            num_serie_nuevo = num_serie_nuevo.trim();
            num_docum_nuevo = num_docum_nuevo.trim();

            if ("0".equals(fecha_emision_orig)) {
                fecha_emision_orig = " ";
            }

            if ("0".equals(fecha_emision_nuevo)) {
                fecha_emision_nuevo = " ";
            }

            linea.append(fecha_proceso);
            linea.append(pipe);
            linea.append(codigo_entidad);
            linea.append(pipe);
            linea.append(codigo_archivo);
            linea.append(pipe);
            linea.append(rut_empresa);
            linea.append(pipe);
            linea.append(dv_empresa);
            linea.append(pipe);
            linea.append(nombre_empresa);
            linea.append(pipe);
            linea.append(mes_origen_gasto);
            linea.append(pipe);
            linea.append(estado_doc_orig);
            linea.append(pipe);
            linea.append(modo_pago_orig);
            linea.append(pipe);
            linea.append(num_serie_orig);
            linea.append(pipe);
            linea.append(num_docum_orig);
            linea.append(pipe);
            linea.append(monto_docum_orig);
            linea.append(pipe);
            linea.append(fecha_emision_orig);
            linea.append(pipe);
            linea.append(codigo_banco_orig);
            linea.append(pipe);
            linea.append(modo_pago_nuevo);
            linea.append(pipe);
            linea.append(num_serie_nuevo);
            linea.append(pipe);
            linea.append(num_docum_nuevo);
            linea.append(pipe);
            linea.append(monto_docum_nuevo);
            linea.append(pipe);
            linea.append(fecha_emision_nuevo);
            linea.append(pipe);
            linea.append(codigo_banco_nuevo);
            linea.append(nuevaLinea);

            fw.write(linea.toString());
        }

        System.gc();
        linea = new StringBuffer("");
        fw.close();

    }

    /* Funcion que obtiene la lista con los registros de la tabla SIF016. */
    public static RespuestaVO consultaRegistrosSif016(String flag, String periodoTxt, String idMaestroSiv, String mesPeriodo, String usser, String fechaReporte)
            throws IOException, SQLException {

        Sif016VO vo = new Sif016VO();
        RespuestaVO resp = new RespuestaVO();
        PlanoVO planoVo = new PlanoVO();
        planoVo.setPeriodo(periodoTxt);

        vo.setListSif016(GenerarPlanosCotizacionesDAO.consultaRegistrosSif016(planoVo));

        /*
         * desde aca se genera el reporte con el archivo, con la ruta
         * especifica.
         */
        try {
            File file = new File("");
            GenerarPlanosCotizacionesImpl impl = new GenerarPlanosCotizacionesImpl();

            StringBuffer sif016FileTxt = new StringBuffer();
            StringBuffer nombreArchivo = new StringBuffer();

            sif016FileTxt.append(file.getAbsolutePath());
            sif016FileTxt.append(IND_Constants.DIR_SIVEGAM_TXT_SIF016);
            sif016FileTxt.append(IND_Constants.SUF_SIVEGAM_SIF016);
            sif016FileTxt.append(periodoTxt);
            sif016FileTxt.append(IND_Constants.EXT_texto);

            nombreArchivo.append(IND_Constants.SUF_SIVEGAM_SIF016);
            nombreArchivo.append(periodoTxt);
            nombreArchivo.append(IND_Constants.EXT_texto);

            impl.generarReporteSif016Txt(sif016FileTxt.toString(), vo.getListSif016());

            /**
             * Seteo de valores y llamada a funcion que inserta en la tabla
             * detalle reportes sivegam - modificado por FRM
             */
            DetalleReporteSivegamVO detalleReporte = new DetalleReporteSivegamVO();

            String fecha = "";
            Date dateProceso = new Date();
            String DATE_FORMAT2 = "dd/MM/yyyy";
            SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

            try {
                detalleReporte.setMaestro_sivegam(Long.parseLong(idMaestroSiv));
                detalleReporte.setNombre_reporte(nombreArchivo.toString());

                fecha = fechaReporte;
                dateProceso = sdf2.parse(fecha);

                detalleReporte.setFechaReporteDate(dateProceso);
                detalleReporte.setStatus_proceso(3);
                detalleReporte.setTipo_proceso(Integer.parseInt(flag));
                detalleReporte.setPeriodo_proceso(Integer.parseInt(mesPeriodo));
                detalleReporte.setFomato_reporte(2);
                detalleReporte.setUsuario_sivegam(Long.parseLong(usser));

                resp = GenerarReportesDAO.insertDetalleReporteSivegam(detalleReporte);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (resp.getCodRespuesta() != 99) {
                resp.setRutaArchivo(sif016FileTxt.toString());
                vo.setNombreArchivo(nombreArchivo.toString());
                resp.setCodRespuesta(0);
            }
        } catch (IOException f) {
            f.printStackTrace();
        }

        return resp;
    }

    /* Funcion que obtiene la lista con los registros de la tabla SIF017. */
    public static RespuestaVO consultaRegistrosSif017(String flag, String periodoTxt, String idMaestroSiv, String mesPeriodo, String usser, String fechaReporte)
            throws IOException, SQLException {

        Sif017VO vo = new Sif017VO();
        RespuestaVO resp = new RespuestaVO();
        PlanoVO planoVo = new PlanoVO();
        planoVo.setPeriodo(periodoTxt);

        vo.setListSif017(GenerarPlanosCotizacionesDAO.consultaRegistrosSif017(planoVo));

        /*
         * desde aca se genera el reporte con el archivo, con la ruta
         * especifica.
         */
        try {
            File file = new File("");
            GenerarPlanosCotizacionesImpl impl = new GenerarPlanosCotizacionesImpl();

            StringBuffer sif017FileTxt = new StringBuffer();
            StringBuffer nombreArchivo = new StringBuffer();

            sif017FileTxt.append(file.getAbsolutePath());
            sif017FileTxt.append(IND_Constants.DIR_SIVEGAM_TXT_SIF017);
            sif017FileTxt.append(IND_Constants.SUF_SIVEGAM_SIF017);
            sif017FileTxt.append(periodoTxt);
            sif017FileTxt.append(IND_Constants.EXT_texto);

            nombreArchivo.append(IND_Constants.SUF_SIVEGAM_SIF017);
            nombreArchivo.append(periodoTxt);
            nombreArchivo.append(IND_Constants.EXT_texto);

            impl.generarReporteSif017Txt(sif017FileTxt.toString(), vo.getListSif017());

            /**
             * Seteo de valores y llamada a funcion que inserta en la tabla
             * detalle reportes sivegam - modificado por FRM
             */
            DetalleReporteSivegamVO detalleReporte = new DetalleReporteSivegamVO();

            String fecha = "";
            Date dateProceso = new Date();
            String DATE_FORMAT2 = "dd/MM/yyyy";
            SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

            try {
                detalleReporte.setMaestro_sivegam(Long.parseLong(idMaestroSiv));
                detalleReporte.setNombre_reporte(nombreArchivo.toString());

                fecha = fechaReporte;
                dateProceso = sdf2.parse(fecha);

                detalleReporte.setFechaReporteDate(dateProceso);
                detalleReporte.setStatus_proceso(3);
                detalleReporte.setTipo_proceso(Integer.parseInt(flag));
                detalleReporte.setPeriodo_proceso(Integer.parseInt(mesPeriodo));
                detalleReporte.setFomato_reporte(2);
                detalleReporte.setUsuario_sivegam(Long.parseLong(usser));

                resp = GenerarReportesDAO.insertDetalleReporteSivegam(detalleReporte);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (resp.getCodRespuesta() != 99) {
                resp.setRutaArchivo(sif017FileTxt.toString());
                vo.setNombreArchivo(nombreArchivo.toString());
                resp.setCodRespuesta(0);
            }

        } catch (IOException f) {
            f.printStackTrace();
        }

        return resp;
    }

    /* Funcion que obtiene la lista con los registros de la tabla SIF018. */
    public static RespuestaVO consultaRegistrosSif018(String flag, String periodoTxt, String idMaestroSiv, String mesPeriodo, String usser, String fechaReporte)
            throws IOException, SQLException {

        Sif018VO vo = new Sif018VO();
        RespuestaVO resp = new RespuestaVO();
        PlanoVO planoVo = new PlanoVO();
        planoVo.setPeriodo(periodoTxt);

        vo.setListSif018(GenerarPlanosCotizacionesDAO.consultaRegistrosSif018(planoVo));

        /*
         * desde aca se genera el reporte con el archivo, con la ruta
         * especifica.
         */
        try {
            File file = new File("");
            GenerarPlanosCotizacionesImpl impl = new GenerarPlanosCotizacionesImpl();

            StringBuffer sif018FileTxt = new StringBuffer();
            StringBuffer nombreArchivo = new StringBuffer();

            sif018FileTxt.append(file.getAbsolutePath());
            sif018FileTxt.append(IND_Constants.DIR_SIVEGAM_TXT_SIF018);
            sif018FileTxt.append(IND_Constants.SUF_SIVEGAM_SIF018);
            sif018FileTxt.append(periodoTxt);
            sif018FileTxt.append(IND_Constants.EXT_texto);

            nombreArchivo.append(IND_Constants.SUF_SIVEGAM_SIF018);
            nombreArchivo.append(periodoTxt);
            nombreArchivo.append(IND_Constants.EXT_texto);

            impl.generarReporteSif018Txt(sif018FileTxt.toString(), vo.getListSif018());

            /**
             * Seteo de valores y llamada a funcion que inserta en la tabla
             * detalle reportes sivegam - modificado por FRM
             */
            DetalleReporteSivegamVO detalleReporte = new DetalleReporteSivegamVO();

            String fecha = "";
            Date dateProceso = new Date();
            String DATE_FORMAT2 = "dd/MM/yyyy";
            SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

            try {
                detalleReporte.setMaestro_sivegam(Long.parseLong(idMaestroSiv));
                detalleReporte.setNombre_reporte(nombreArchivo.toString());

                fecha = fechaReporte;
                dateProceso = sdf2.parse(fecha);

                detalleReporte.setFechaReporteDate(dateProceso);
                detalleReporte.setStatus_proceso(3);
                detalleReporte.setTipo_proceso(Integer.parseInt(flag));
                detalleReporte.setPeriodo_proceso(Integer.parseInt(mesPeriodo));
                detalleReporte.setFomato_reporte(2);
                detalleReporte.setUsuario_sivegam(Long.parseLong(usser));

                resp = GenerarReportesDAO.insertDetalleReporteSivegam(detalleReporte);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (resp.getCodRespuesta() != 99) {
                resp.setRutaArchivo(sif018FileTxt.toString());
                vo.setNombreArchivo(nombreArchivo.toString());
                resp.setCodRespuesta(0);
            }
        } catch (IOException f) {
            f.printStackTrace();
        }

        return resp;
    }

    /* Funcion que obtiene la lista con los registros de la tabla SIF019. */
    public static RespuestaVO consultaRegistrosSif019(String flag, String periodoTxt, String idMaestroSiv, String mesPeriodo, String usser, String fechaReporte)
            throws IOException, SQLException {

        Sif019VO vo = new Sif019VO();
        RespuestaVO resp = new RespuestaVO();
        PlanoVO planoVo = new PlanoVO();
        planoVo.setPeriodo(periodoTxt);

        vo.setListSif019(GenerarPlanosCotizacionesDAO.consultaRegistrosSif019(planoVo));

        /*
         * desde aca se genera el reporte con el archivo, con la ruta
         * especifica.
         */
        try {
            File file = new File("");
            GenerarPlanosCotizacionesImpl impl = new GenerarPlanosCotizacionesImpl();

            StringBuffer sif019FileTxt = new StringBuffer();
            StringBuffer nombreArchivo = new StringBuffer();

            sif019FileTxt.append(file.getAbsolutePath());
            sif019FileTxt.append(IND_Constants.DIR_SIVEGAM_TXT_SIF019);
            sif019FileTxt.append(IND_Constants.SUF_SIVEGAM_SIF019);
            sif019FileTxt.append(periodoTxt);
            sif019FileTxt.append(IND_Constants.EXT_texto);

            nombreArchivo.append(IND_Constants.SUF_SIVEGAM_SIF019);
            nombreArchivo.append(periodoTxt);
            nombreArchivo.append(IND_Constants.EXT_texto);

            impl.generarReporteSif019Txt(sif019FileTxt.toString(), vo.getListSif019());

            /**
             * Seteo de valores y llamada a funcion que inserta en la tabla
             * detalle reportes sivegam - modificado por FRM
             */
            DetalleReporteSivegamVO detalleReporte = new DetalleReporteSivegamVO();

            String fecha = "";
            Date dateProceso = new Date();
            String DATE_FORMAT2 = "dd/MM/yyyy";
            SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

            try {
                detalleReporte.setMaestro_sivegam(Long.parseLong(idMaestroSiv));
                detalleReporte.setNombre_reporte(nombreArchivo.toString());

                fecha = fechaReporte;
                dateProceso = sdf2.parse(fecha);

                detalleReporte.setFechaReporteDate(dateProceso);
                detalleReporte.setStatus_proceso(3);
                detalleReporte.setTipo_proceso(Integer.parseInt(flag));
                detalleReporte.setPeriodo_proceso(Integer.parseInt(mesPeriodo));
                detalleReporte.setFomato_reporte(2);
                detalleReporte.setUsuario_sivegam(Long.parseLong(usser));

                resp = GenerarReportesDAO.insertDetalleReporteSivegam(detalleReporte);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (resp.getCodRespuesta() != 99) {
                resp.setRutaArchivo(sif019FileTxt.toString());
                vo.setNombreArchivo(nombreArchivo.toString());
                resp.setCodRespuesta(0);
            }
        } catch (IOException f) {
            f.printStackTrace();
        }

        return resp;
    }

    public String formatPipeSpace(String valor) {
        String formateador;
        formateador = valor.replace('|', ' ');
        return formateador;
    }

}
