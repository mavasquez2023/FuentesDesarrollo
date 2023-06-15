package cl.araucana.sivegam.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import cl.araucana.sivegam.dao.GenerarPlanosDivisionPrevisionalDAO;
import cl.araucana.sivegam.dao.GenerarReportesDAO;
import cl.araucana.sivegam.helper.IND_Constants;
import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.vo.DetalleReporteSivegamVO;
import cl.araucana.sivegam.vo.LinSif011VO;
import cl.araucana.sivegam.vo.LinSif012VO;
import cl.araucana.sivegam.vo.LinSif014VO;
import cl.araucana.sivegam.vo.PlanoVO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.Sif011VO;
import cl.araucana.sivegam.vo.Sif012VO;
import cl.araucana.sivegam.vo.Sif014VO;

public class GenerarPlanosDivisionPrevisionalImpl {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    /* Funcion que genera el reporte requerido (para SIF011 en formato TXT). */
    public void generarReporteSif011Txt(String archivo, LinSif011VO[] lineas) throws IOException {

        System.gc();
        //String nuevaLinea = "\n";
        String nuevaLinea = "\r\n";
        String pipe = "|";

        FileWriter fw = new FileWriter(archivo);
        StringBuffer linea = new StringBuffer("");

        for (int i = 0; i < lineas.length; i++) {

            linea = new StringBuffer("");
            LinSif011VO registro = new LinSif011VO();
            registro = lineas[i];

            String fecha_proceso = Long.toString(registro.getFecha_proceso());
            String codigo_entidad = Long.toString(registro.getCodigo_entidad());
            String codigo_archivo = Integer.toString(registro.getCodigo_archivo());
            String mes_rec = Long.toString(registro.getMes_cotizaciones());
            String mes_remuneracion = Long.toString(registro.getMes_remuneracion());
            String tipo_declaracion = Integer.toString(registro.getTipo_declaracion());
            String codigo_barra = registro.getCodigo_barra();
            String rut_empresa = Long.toString(registro.getRut_empresa());
            String dv_empresa = registro.getDv_empresa();
            String nombre_empresa = formatPipeSpace(registro.getNombre_empresa());
            String rut_afiliado = Long.toString(registro.getRut_afiliado());
            String dv_afiliado = registro.getDv_afiliado();
            String nombre_afiliado = formatPipeSpace(registro.getNombre_afiliado());
            String cod_tipo_beneficio = Integer.toString(registro.getCod_tipo_beneficio());
            String tipo_beneficiario = Integer.toString(registro.getTipo_beneficiario());
            String rut_causante = Long.toString(registro.getRut_causante());
            String dv_causante = registro.getDv_causante();
            String nombre_causante = formatPipeSpace(registro.getNombre_causante());
            String cod_tipo_causante = Integer.toString(registro.getCod_tipo_causante());
            String fecha_inicio_benef = Long.toString(registro.getFecha_inicio_benef());
            String fecha_termino_benef = Long.toString(registro.getFecha_termino_benef());
            String dias_asfam = Integer.toString(registro.getDias_asfam());
            String codigo_tramo = Integer.toString(registro.getCodigo_tramo());
            String monto_beneficio = Long.toString(registro.getMonto_beneficio());
            String tipo_emision = Integer.toString(registro.getTipo_emision());
            String cod_tipo_egreso = Integer.toString(registro.getCod_tipo_egreso());
            String modalidad_pago = Integer.toString(registro.getModalidad_pago());
            String monto_documento = "";
            String numero_serie = "";
            String numero_documento = "";
            String fechaEmisionDocumento = "";
            String codigo_banco = "";

            if ("7".equals(modalidad_pago)) {
                monto_documento = "";
                numero_serie = "";
                numero_documento = "";
                fechaEmisionDocumento = "";
                codigo_banco = "";
            } else {
                monto_documento = Long.toString(registro.getMonto_documento());
                numero_serie = registro.getNumero_serie();
                numero_documento = registro.getNumero_documento();
                fechaEmisionDocumento = Long.toString(registro.getFecha_emision_documento());
                codigo_banco = Long.toString(registro.getCodigo_banco());
            }

            codigo_barra = codigo_barra.trim();
            nombre_empresa = nombre_empresa.trim();
            nombre_afiliado = nombre_afiliado.trim();
            nombre_causante = nombre_causante.trim();

            if ("0".equals(fecha_inicio_benef)) {
                fecha_inicio_benef = " ";
            }

            if ("0".equals(fecha_termino_benef)) {
                fecha_termino_benef = " ";
            }
            numero_serie = numero_serie.trim();
            numero_documento = numero_documento.trim();

            linea.append(fecha_proceso);
            linea.append(pipe);
            linea.append(codigo_entidad);
            linea.append(pipe);
            linea.append(codigo_archivo);
            linea.append(pipe);
            linea.append(mes_rec);
            linea.append(pipe);
            linea.append(mes_remuneracion);
            linea.append(pipe);
            linea.append(tipo_declaracion);
            linea.append(pipe);
            linea.append(codigo_barra);
            linea.append(pipe);
            linea.append(rut_empresa);
            linea.append(pipe);
            linea.append(dv_empresa);
            linea.append(pipe);
            linea.append(nombre_empresa);
            linea.append(pipe);
            linea.append(rut_afiliado);
            linea.append(pipe);
            linea.append(dv_afiliado);
            linea.append(pipe);
            linea.append(nombre_afiliado);
            linea.append(pipe);
            linea.append(cod_tipo_beneficio);
            linea.append(pipe);
            linea.append(tipo_beneficiario);
            linea.append(pipe);
            linea.append(rut_causante);
            linea.append(pipe);
            linea.append(dv_causante);
            linea.append(pipe);
            linea.append(nombre_causante);
            linea.append(pipe);
            linea.append(cod_tipo_causante);
            linea.append(pipe);
            linea.append(fecha_inicio_benef);
            linea.append(pipe);
            linea.append(fecha_termino_benef);
            linea.append(pipe);
            linea.append(dias_asfam);
            linea.append(pipe);
            linea.append(codigo_tramo);
            linea.append(pipe);
            linea.append(monto_beneficio);
            linea.append(pipe);
            linea.append(tipo_emision);
            linea.append(pipe);
            linea.append(cod_tipo_egreso);
            linea.append(pipe);
            linea.append(modalidad_pago);
            linea.append(pipe);
            linea.append(monto_documento);
            linea.append(pipe);
            linea.append(numero_serie);
            linea.append(pipe);
            linea.append(numero_documento);
            linea.append(pipe);
            linea.append(fechaEmisionDocumento);
            linea.append(pipe);
            linea.append(codigo_banco);
            linea.append(nuevaLinea);

            fw.write(linea.toString());
        }

        System.gc();

        linea = new StringBuffer("");
        fw.close();

    }

    /* Funcion que obtiene la lista con los registros de la tabla SIF011. */
    public static RespuestaVO consultaRegistrosSif011(String flag, String periodoTxt, String idMaestroSiv, String mesPeriodo, String usser, String fechaReporte)
            throws IOException, SQLException {

        RespuestaVO resp = new RespuestaVO();
        Sif011VO vo = new Sif011VO();
        PlanoVO planoVo = new PlanoVO();
        planoVo.setPeriodo(periodoTxt);

        vo.setListSif011(GenerarPlanosDivisionPrevisionalDAO.consultaRegistrosSif011(planoVo));

        /*
         * desde aca se genera el reporte con el archivo, con la ruta
         * especifica.
         */
        try {
            File file = new File("");
            GenerarPlanosDivisionPrevisionalImpl impl = new GenerarPlanosDivisionPrevisionalImpl();

            StringBuffer sif011FileTxt = new StringBuffer();
            StringBuffer nombreArchivo = new StringBuffer();

            sif011FileTxt.append(file.getAbsolutePath());
            sif011FileTxt.append(IND_Constants.DIR_SIVEGAM_TXT_SIF011);
            sif011FileTxt.append(IND_Constants.SUF_SIVEGAM_SIF011);
            sif011FileTxt.append(periodoTxt);
            sif011FileTxt.append(IND_Constants.EXT_texto);

            nombreArchivo.append(IND_Constants.SUF_SIVEGAM_SIF011);
            nombreArchivo.append(periodoTxt);
            nombreArchivo.append(IND_Constants.EXT_texto);

 //           logger.debug("largo registros consultados: " + vo.getListSif011().length);
 //           logger.debug("ruta donde debe escribir: " + sif011FileTxt.toString());

            impl.generarReporteSif011Txt(sif011FileTxt.toString(), vo.getListSif011());

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
                resp.setRutaArchivo(sif011FileTxt.toString());
                resp.setCodRespuesta(0);
            }

        } catch (IOException f) {
            f.printStackTrace();
        }

        return resp;
    }

    /* Funcion que genera el reporte requerido (para SIF012 en formato TXT). */
    public void generarReporteSif012Txt(String archivo, LinSif012VO[] lineas) throws IOException {

        System.gc();
        //String nuevaLinea = "\n";
        String nuevaLinea = "\r\n";
        String pipe = "|";

        FileWriter fw = new FileWriter(archivo);
        StringBuffer linea = new StringBuffer("");

        for (int i = 0; i < lineas.length; i++) {

            linea = new StringBuffer("");
            LinSif012VO registro = new LinSif012VO();
            registro = lineas[i];

            String fecha_proceso = Integer.toString(registro.getFecha_proceso());
            String codigo_entidad = Integer.toString(registro.getCodigo_entidad());
            String codigo_archivo = Integer.toString(registro.getCodigo_archivo());
            String mes_recaudacion = Integer.toString(registro.getMes_recaudacion());
            String mes_remuneracion = Integer.toString(registro.getMes_remuneracion());
            String tipo_declaracion = Integer.toString(registro.getTipo_declaracion());
            String numero_declaracion = registro.getNumero_declaracion();
            String rut_empresa = Long.toString(registro.getRut_empresa());
            String dv_empresa = registro.getDv_empresa();
            String nombre_empresa = formatPipeSpace(registro.getNombre_empresa());
            String rut_afiliado = Long.toString(registro.getRut_afiliado());
            String dv_afiliado = registro.getDv_afiliado();
            String nombre_afiliado = formatPipeSpace(registro.getNombre_afiliado());
            String cod_tipo_beneficio = Integer.toString(registro.getCod_tipo_beneficio());
            String tipo_beneficiario = Integer.toString(registro.getTipo_beneficiario());
            String rut_causante = Long.toString(registro.getRut_causante());
            String dv_causante = registro.getDv_causante();
            String nombre_causante = formatPipeSpace(registro.getNombre_causante());
            String cod_tipo_causante = Integer.toString(registro.getCod_tipo_causante());
            String fecha_inicio_benef = Long.toString(registro.getFecha_inicio_benef());
            String fecha_termino_benef = Long.toString(registro.getFecha_termino_benef());
            String dias_asfam = Integer.toString(registro.getDias_asfam());
            String codigo_tramo = Integer.toString(registro.getCodigo_tramo());
            String monto_beneficio = Long.toString(registro.getMonto_beneficio());
            String causal_reliquidacion = Integer.toString(registro.getCausal_reliquidacion());
            String tipo_emision = Integer.toString(registro.getTipo_emision());
            String cod_tipo_egreso = Integer.toString(registro.getCod_tipo_egreso());
            String modalidad_pago = Integer.toString(registro.getModalidad_pago());
            String monto_documento = "";
            String fech_emision_doc = "";
            String numero_serie = "";
            String numero_documento = "";
            String codigo_banco = "";

            if ("7".equals(modalidad_pago)) {
                monto_documento = "";
                fech_emision_doc = "";
                numero_serie = "";
                numero_documento = "";
                codigo_banco = "";
            } else {
                monto_documento = Long.toString(registro.getMonto_documento());
                fech_emision_doc = Long.toString(registro.getFech_emision_doc());
                numero_serie = registro.getNumero_serie();
                numero_documento = registro.getNumero_documento();
                codigo_banco = Long.toString(registro.getCodigo_banco());
            }

            String fuente_origen = "";

            numero_declaracion = numero_declaracion.trim();
            if ("".equals(numero_declaracion)) {
                numero_declaracion = " ";
            }
            nombre_empresa = nombre_empresa.trim();
            nombre_afiliado = nombre_afiliado.trim();
            nombre_causante = nombre_causante.trim();
            numero_serie = numero_serie.trim();

            if ("0".equals(fecha_inicio_benef)) {
                fecha_inicio_benef = " ";
            }

            if ("0".equals(fecha_termino_benef)) {
                fecha_termino_benef = " ";
            }

            if ("0".equals(fech_emision_doc)) {
                fech_emision_doc = " ";
            }
            numero_documento = numero_documento.trim();

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
            linea.append(tipo_declaracion);
            linea.append(pipe);
            linea.append(numero_declaracion);
            linea.append(pipe);
            linea.append(rut_empresa);
            linea.append(pipe);
            linea.append(dv_empresa);
            linea.append(pipe);
            linea.append(nombre_empresa);
            linea.append(pipe);
            linea.append(rut_afiliado);
            linea.append(pipe);
            linea.append(dv_afiliado);
            linea.append(pipe);
            linea.append(nombre_afiliado);
            linea.append(pipe);
            linea.append(cod_tipo_beneficio);
            linea.append(pipe);
            linea.append(tipo_beneficiario);
            linea.append(pipe);
            linea.append(rut_causante);
            linea.append(pipe);
            linea.append(dv_causante);
            linea.append(pipe);
            linea.append(nombre_causante);
            linea.append(pipe);
            linea.append(cod_tipo_causante);
            linea.append(pipe);
            linea.append(fecha_inicio_benef);
            linea.append(pipe);
            linea.append(fecha_termino_benef);
            linea.append(pipe);
            linea.append(dias_asfam);
            linea.append(pipe);
            linea.append(codigo_tramo);
            linea.append(pipe);
            linea.append(monto_beneficio);
            linea.append(pipe);
            linea.append(causal_reliquidacion);
            linea.append(pipe);
            linea.append(tipo_emision);
            linea.append(pipe);
            linea.append(cod_tipo_egreso);
            linea.append(pipe);
            linea.append(monto_documento);
            linea.append(pipe);
            linea.append(modalidad_pago);
            linea.append(pipe);
            linea.append(fech_emision_doc);
            linea.append(pipe);
            linea.append(numero_serie);
            linea.append(pipe);
            linea.append(numero_documento);
            linea.append(pipe);
            linea.append(codigo_banco);
            linea.append(pipe);
            linea.append(fuente_origen);
            linea.append(nuevaLinea);

            fw.write(linea.toString());
        }

        System.gc();
        linea = new StringBuffer("");
        fw.close();

    }

    /* Funcion que genera el reporte requerido (para SIF014 en formato TXT). */
    public void generarReporteSif014Txt(String archivo, LinSif014VO[] lineas) throws IOException {

        System.gc();
        //String nuevaLinea = "\n";
        String nuevaLinea = "\r\n";
        String pipe = "|";

        FileWriter fw = new FileWriter(archivo);
        StringBuffer linea = new StringBuffer("");

        for (int i = 0; i < lineas.length; i++) {

            linea = new StringBuffer("");
            LinSif014VO registro = new LinSif014VO();
            registro = lineas[i];

            String fecha_proceso = Integer.toString(registro.getFecha_proceso());
            String codigo_entidad = Integer.toString(registro.getCodigo_entidad());
            String codigo_archivo = Integer.toString(registro.getCodigo_archivo());
            String rut_empresa = Long.toString(registro.getRut_empresa());
            String dv_empresa = registro.getDv_empresa();
            String nombre_empresa = formatPipeSpace(registro.getNombre_empresa());
            String rut_beneficiario = Long.toString(registro.getRut_beneficiario());
            String dv_beneficiario = registro.getDv_beneficiario();
            String nombre_beneficiario = formatPipeSpace(registro.getNombre_beneficiario());
            String tipo_beneficio = Integer.toString(registro.getTipo_beneficio());
            String tipo_beneficiario = Integer.toString(registro.getTipo_beneficiario());
            String rut_causante = Long.toString(registro.getRut_causante());
            String dv_causante = registro.getDv_causante();
            String nombre_causante = formatPipeSpace(registro.getNombre_causante());
            String tipo_causante = Integer.toString(registro.getTipo_causante());
            String inicio_period_reinte = Long.toString(registro.getInicio_period_reinte());
            String final_period_reinte = Long.toString(registro.getFinal_period_reinte());
            String tipo_reintegro = Integer.toString(registro.getTipo_reintegro());
            String monto_total_reintegro = Long.toString(registro.getMonto_total_reintegro());
            String monto_reintegro_mes = Long.toString(registro.getMonto_reintegro_mes());
            String monto_total_deuda = Long.toString(registro.getMonto_total_deuda());
            String fuente_origen = Integer.toString(registro.getFuente_origen());

            nombre_empresa = nombre_empresa.trim();
            nombre_beneficiario = nombre_beneficiario.trim();
            nombre_causante = nombre_causante.trim();

            if ("0".equals(inicio_period_reinte)) {
                inicio_period_reinte = " ";
            }

            if ("0".equals(final_period_reinte)) {
                final_period_reinte = " ";
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
            linea.append(rut_beneficiario);
            linea.append(pipe);
            linea.append(dv_beneficiario);
            linea.append(pipe);
            linea.append(nombre_beneficiario);
            linea.append(pipe);
            linea.append(tipo_beneficio);
            linea.append(pipe);
            linea.append(tipo_beneficiario);
            linea.append(pipe);
            linea.append(rut_causante);
            linea.append(pipe);
            linea.append(dv_causante);
            linea.append(pipe);
            linea.append(nombre_causante);
            linea.append(pipe);
            linea.append(tipo_causante);
            linea.append(pipe);
            linea.append(inicio_period_reinte);
            linea.append(pipe);
            linea.append(final_period_reinte);
            linea.append(pipe);
            linea.append(tipo_reintegro);
            linea.append(pipe);
            linea.append(monto_total_reintegro);
            linea.append(pipe);
            linea.append(monto_reintegro_mes);
            linea.append(pipe);
            linea.append(monto_total_deuda);
            linea.append(pipe);
            linea.append(fuente_origen);
            linea.append(nuevaLinea);

            fw.write(linea.toString());
        }

        System.gc();
        linea = new StringBuffer("");
        fw.close();

    }

    /* Funcion que obtiene la lista con los registros de la tabla SIF012. */
    public static RespuestaVO consultaRegistrosSif012(String flag, String periodoTxt, String idMaestroSiv, String mesPeriodo, String usser, String fechaReporte)
            throws IOException, SQLException {

        RespuestaVO resp = new RespuestaVO();
        Sif012VO vo = new Sif012VO();
        PlanoVO planoVo = new PlanoVO();
        planoVo.setPeriodo(periodoTxt);

        vo.setListSif012(GenerarPlanosDivisionPrevisionalDAO.consultaRegistrosSif012(planoVo));

        /*
         * desde aca se genera el reporte con el archivo, con la ruta
         * especifica.
         */
        try {
            File file = new File("");
            GenerarPlanosDivisionPrevisionalImpl impl = new GenerarPlanosDivisionPrevisionalImpl();

            StringBuffer sif012FileTxt = new StringBuffer();
            StringBuffer nombreArchivo = new StringBuffer();

            sif012FileTxt.append(file.getAbsolutePath());
            sif012FileTxt.append(IND_Constants.DIR_SIVEGAM_TXT_SIF012);
            sif012FileTxt.append(IND_Constants.SUF_SIVEGAM_SIF012);
            sif012FileTxt.append(periodoTxt);
            sif012FileTxt.append(IND_Constants.EXT_texto);

            nombreArchivo.append(IND_Constants.SUF_SIVEGAM_SIF012);
            nombreArchivo.append(periodoTxt);
            nombreArchivo.append(IND_Constants.EXT_texto);

            impl.generarReporteSif012Txt(sif012FileTxt.toString(), vo.getListSif012());

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
                resp.setRutaArchivo(sif012FileTxt.toString());
                resp.setCodRespuesta(0);
            }
        } catch (IOException f) {
            f.printStackTrace();
        }

        return resp;
    }

    /* Funcion que obtiene la lista con los registros de la tabla SIF012. */
    public static RespuestaVO consultaRegistrosSif014(String flag, String periodoTxt, String idMaestroSiv, String mesPeriodo, String usser, String fechaReporte)
            throws IOException, SQLException {

        Sif014VO vo = new Sif014VO();
        RespuestaVO resp = new RespuestaVO();
        PlanoVO planoVo = new PlanoVO();
        planoVo.setPeriodo(periodoTxt);
        vo.setListSif014(GenerarPlanosDivisionPrevisionalDAO.consultaRegistrosSif014(planoVo));

        /*
         * desde aca se genera el reporte con el archivo, con la ruta
         * especifica.
         */
        try {
            File file = new File("");
            GenerarPlanosDivisionPrevisionalImpl impl = new GenerarPlanosDivisionPrevisionalImpl();

            StringBuffer sif014FileTxt = new StringBuffer();
            StringBuffer nombreArchivo = new StringBuffer();

            sif014FileTxt.append(file.getAbsolutePath());
            sif014FileTxt.append(IND_Constants.DIR_SIVEGAM_TXT_SIF014);
            sif014FileTxt.append(IND_Constants.SUF_SIVEGAM_SIF014);
            sif014FileTxt.append(periodoTxt);
            sif014FileTxt.append(IND_Constants.EXT_texto);

            nombreArchivo.append(IND_Constants.SUF_SIVEGAM_SIF014);
            nombreArchivo.append(periodoTxt);
            nombreArchivo.append(IND_Constants.EXT_texto);

            impl.generarReporteSif014Txt(sif014FileTxt.toString(), vo.getListSif014());

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
                resp.setRutaArchivo(sif014FileTxt.toString());
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
