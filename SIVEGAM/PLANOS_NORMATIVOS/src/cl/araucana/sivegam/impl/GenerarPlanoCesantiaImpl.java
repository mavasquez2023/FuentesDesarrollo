package cl.araucana.sivegam.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import cl.araucana.sivegam.dao.GenerarPlanoCesantiaDAO;
import cl.araucana.sivegam.dao.GenerarReportesDAO;
import cl.araucana.sivegam.helper.Helper;
import cl.araucana.sivegam.helper.IND_Constants;
import cl.araucana.sivegam.vo.CesantiaVO;
import cl.araucana.sivegam.vo.DetalleReporteSivegamVO;
import cl.araucana.sivegam.vo.LinCesantia041VO;
import cl.araucana.sivegam.vo.LinCesantia042VO;
import cl.araucana.sivegam.vo.LinCesantia043VO;
import cl.araucana.sivegam.vo.LinCesantia044VO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.TipoProcesosVO;
import cl.araucana.sivegam.vo.param.ListadoParametros;

public class GenerarPlanoCesantiaImpl {

    /**
     * Funcion que toma la ruta de los planos de cesantia, dependiendo del tipo
     * de Reporte, desde las tablas parametricas.
     */
    public static StringBuffer retornarRutaPlanos(int tipoReporte) {

        String carpeta = "";
        StringBuffer ruta = new StringBuffer();
        File file = new File("");
        ListadoParametros lp = ListadoParametros.getInstancia();
        TipoProcesosVO[] tp = lp.getListTipoProcesos();

        for (int i = 0; i < tp.length; i++) {
            if (tp[i].getId_tipo_proceso() == tipoReporte) {
                carpeta = tp[i].getDesc_carpeta_txt();
                break;
            }
        }

        ruta.append(file.getAbsolutePath());
        ruta.append(carpeta);

        return ruta;

    }

    /** Funcion que genera el plano de cesantia numero 41. */
    public static RespuestaVO generarPlanoCesantia041(int tipoReporte, String periodo, String idMaestroSiv, String mesPeriodo, String usser, String fechaReporte) throws Exception,
            SQLException {

        CesantiaVO cesantia = new CesantiaVO();
        RespuestaVO respuesta = new RespuestaVO();

        cesantia.setListCesantia041VO(GenerarPlanoCesantiaDAO.generarPlanoCesantia041());
        /*
         * desde aca se genera el reporte con el archivo, con la ruta
         * especifica.
         */
        try {
            File file = new File("");
            GenerarPlanoCesantiaImpl impl = new GenerarPlanoCesantiaImpl();

            StringBuffer cesantiaFileTxt = new StringBuffer();
            StringBuffer nombreArchivo = new StringBuffer();

            cesantiaFileTxt.append(file.getAbsolutePath());
            cesantiaFileTxt.append(IND_Constants.DIR_CESANTIA_TXT_PLANO41);
            cesantiaFileTxt.append(IND_Constants.NOM_SC_041);
            cesantiaFileTxt.append(periodo);
            cesantiaFileTxt.append(IND_Constants.EXT_texto);

            nombreArchivo.append(IND_Constants.NOM_SC_041);
            nombreArchivo.append(periodo);
            nombreArchivo.append(IND_Constants.EXT_texto);

            impl.generarPlanoCesantia041Txt(cesantiaFileTxt.toString(), cesantia.getListCesantia041VO());

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
                detalleReporte.setTipo_proceso(tipoReporte);
                detalleReporte.setPeriodo_proceso(Integer.parseInt(mesPeriodo));
                detalleReporte.setFomato_reporte(2);
                detalleReporte.setUsuario_sivegam(Long.parseLong(usser));

                respuesta = GenerarReportesDAO.insertDetalleReporteSivegam(detalleReporte);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (respuesta.getCodRespuesta() != 99) {
                cesantia.setRutaTxtCesantia(cesantiaFileTxt.toString());
                cesantia.setNombreArchivoCesantia(nombreArchivo.toString());
                respuesta.setCodRespuesta(0);
            }
        } catch (IOException f) {
            f.printStackTrace();
        }

        return respuesta;
    }

    public void generarPlanoCesantia041Txt(String archivo, LinCesantia041VO[] lineas) throws IOException {

        System.gc();
        //String nuevaLinea = "\n";
        String nuevaLinea = "\r\n";
        String pipe = "|";

        FileWriter fw = new FileWriter(archivo);
        StringBuffer linea = new StringBuffer("");

        for (int i = 0; i < lineas.length; i++) {

            linea = new StringBuffer("");
            LinCesantia041VO registro = new LinCesantia041VO();
            registro = lineas[i];

            String mes_if = Long.toString(registro.getMes_if());
            String codigo_entidad = Long.toString(registro.getCodigo_entidad());
            String codigo_archivo = Integer.toString(registro.getCodigo_archivo());
            String mes_emision = Long.toString(registro.getMes_emision());
            String tipo_egreso = Integer.toString(registro.getTipo_egreso());
            String rut_beneficiario = Long.toString(registro.getRut_beneficiario());
            String dv_beneficiario = registro.getDv_beneficiario();
            String nombre_beneficiario = registro.getNombre_beneficiario();
            String comunaTmp = Integer.toString(registro.getComuna());
            String comuna = "";
            if ("0".equalsIgnoreCase(comunaTmp)) {
                comuna = "";
            } else {
                comuna = Helper.paddingString(comunaTmp, 5, '0', true);
            }
            String fecha_cesantia = Long.toString(registro.getFecha_cesantia());
            String fecha_solicitud = Long.toString(registro.getFecha_solicitud());
            String fecha_inicio_cuota = Long.toString(registro.getFecha_inicio_cuota());
            String fecha_termino_cuota = Long.toString(registro.getFecha_termino_cuota());
            String monto_subsidio_cesantia = Long.toString(registro.getMonto_subsidio_cesantia());
            String mod_pago = Integer.toString(registro.getMod_pago());
            String serie_documento = registro.getSerie_documento();
            String numero_documento = registro.getNumero_documento();
            String monto_documento = Long.toString(registro.getMonto_documento());
            String fecha_emision_documento = Long.toString(registro.getFecha_emision_documento());
            String codigo_banco = Integer.toString(registro.getCodigo_banco());

            nombre_beneficiario = nombre_beneficiario.trim();
            serie_documento = serie_documento.trim();
            numero_documento = numero_documento.trim();

            linea.append(mes_if);
            linea.append(pipe);
            linea.append(codigo_entidad);
            linea.append(pipe);
            linea.append(codigo_archivo);
            linea.append(pipe);
            linea.append(mes_emision);
            linea.append(pipe);
            linea.append(tipo_egreso);
            linea.append(pipe);
            linea.append(rut_beneficiario);
            linea.append(pipe);
            linea.append(dv_beneficiario);
            linea.append(pipe);
            linea.append(nombre_beneficiario);
            linea.append(pipe);
            linea.append(comuna);
            linea.append(pipe);
            linea.append(fecha_cesantia);
            linea.append(pipe);
            linea.append(fecha_solicitud);
            linea.append(pipe);
            linea.append(fecha_inicio_cuota);
            linea.append(pipe);
            linea.append(fecha_termino_cuota);
            linea.append(pipe);
            linea.append(monto_subsidio_cesantia);
            linea.append(pipe);
            linea.append(mod_pago);
            linea.append(pipe);
            linea.append(serie_documento);
            linea.append(pipe);
            linea.append(numero_documento);
            linea.append(pipe);
            linea.append(monto_documento);
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

    /** Funciones que generan el plano 042 de cesantia */

    public static RespuestaVO generarPlanoCesantia042(int tipoReporte, String periodo, String idMaestroSiv, String mesPeriodo, String usser, String fechaReporte) throws Exception,
            SQLException {

        CesantiaVO cesantia = new CesantiaVO();
        RespuestaVO respuesta = new RespuestaVO();

        cesantia.setListCesantia042VO(GenerarPlanoCesantiaDAO.generarPlanoCesantia042());
        /*
         * desde aca se genera el reporte con el archivo, con la ruta
         * especifica.
         */
        try {
            File file = new File("");
            GenerarPlanoCesantiaImpl impl = new GenerarPlanoCesantiaImpl();

            StringBuffer CesantiaFileTxt = new StringBuffer();
            StringBuffer nombreArchivo = new StringBuffer();

            CesantiaFileTxt.append(file.getAbsolutePath());
            CesantiaFileTxt.append(IND_Constants.DIR_CESANTIA_TXT_PLANO42);
            CesantiaFileTxt.append(IND_Constants.NOM_SC_042);
            CesantiaFileTxt.append(periodo);
            CesantiaFileTxt.append(IND_Constants.EXT_texto);

            nombreArchivo.append(IND_Constants.NOM_SC_042);
            nombreArchivo.append(periodo);
            nombreArchivo.append(IND_Constants.EXT_texto);

            impl.generarPlanoCesantia042Txt(CesantiaFileTxt.toString(), cesantia.getListCesantia042VO());

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
                detalleReporte.setTipo_proceso(tipoReporte);
                detalleReporte.setPeriodo_proceso(Integer.parseInt(mesPeriodo));
                detalleReporte.setFomato_reporte(2);
                detalleReporte.setUsuario_sivegam(Long.parseLong(usser));

                respuesta = GenerarReportesDAO.insertDetalleReporteSivegam(detalleReporte);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (respuesta.getCodRespuesta() != 99) {
                cesantia.setRutaTxtCesantia(CesantiaFileTxt.toString());
                cesantia.setNombreArchivoCesantia(nombreArchivo.toString());
                respuesta.setCodRespuesta(0);
            }
        } catch (IOException f) {
            f.printStackTrace();
        }

        return respuesta;
    }

    public void generarPlanoCesantia042Txt(String archivo, LinCesantia042VO[] lineas) throws IOException {

        System.gc();
        //String nuevaLinea = "\n";
        String nuevaLinea = "\r\n";
        String pipe = "|";

        FileWriter fw = new FileWriter(archivo);
        StringBuffer linea = new StringBuffer("");

        for (int i = 0; i < lineas.length; i++) {

            linea = new StringBuffer("");
            LinCesantia042VO registro = new LinCesantia042VO();
            registro = lineas[i];

            String mes_if = Long.toString(registro.getMes_if());
            String codigo_entidad = Integer.toString(registro.getCodigo_entidad());
            String codigo_archivo = Integer.toString(registro.getCodigo_archivo());
            String tipo_reintegro = Integer.toString(registro.getTipo_reintegro());
            String rut_beneficiario = Long.toString(registro.getRut_beneficiario());
            String dv_beneficiario = registro.getDv_beneficiario();
            String nombre_beneficiario = registro.getNombre_beneficiario();
            String comunaTmp = Integer.toString(registro.getComuna());
            String comuna = "";
            if ("0".equalsIgnoreCase(comunaTmp)) {
                comuna = "";
            } else {
                comuna = Helper.paddingString(comunaTmp, 5, '0', true);
            }
            String monto_sub_cesantia_reintegrado = Long.toString(registro.getMonto_sub_cesantia_reintegrado());
            String fecha_inicio_reintegro = Long.toString(registro.getFecha_inicio_reintegro());
            String fecha_inicio_cuota = Long.toString(registro.getFecha_termino_reintegro());

            linea.append(mes_if);
            linea.append(pipe);
            linea.append(codigo_entidad);
            linea.append(pipe);
            linea.append(codigo_archivo);
            linea.append(pipe);
            linea.append(tipo_reintegro);
            linea.append(pipe);
            linea.append(rut_beneficiario);
            linea.append(pipe);
            linea.append(dv_beneficiario);
            linea.append(pipe);
            linea.append(nombre_beneficiario);
            linea.append(pipe);
            linea.append(comuna);
            linea.append(pipe);
            linea.append(monto_sub_cesantia_reintegrado);
            linea.append(pipe);
            linea.append(fecha_inicio_reintegro);
            linea.append(pipe);
            linea.append(fecha_inicio_cuota);
            linea.append(nuevaLinea);

            fw.write(linea.toString());
        }

        System.gc();
       linea = new StringBuffer("");
        fw.close();
    }

    /**
     * Generacion de txt para archivo 043 de cesantia.
     * 
     * @throws IOException
     */
    public static RespuestaVO generarPlanoCesantia043(int tipoReporte, String periodo, String idMaestroSiv, String mesPeriodo, String usser, String fechaReporte) throws Exception,
            SQLException {

        CesantiaVO cesantia = new CesantiaVO();
        RespuestaVO respuesta = new RespuestaVO();

        cesantia.setListCesantia043VO(GenerarPlanoCesantiaDAO.generarPlanoCesantia043());
        /*
         * desde aca se genera el reporte con el archivo, con la ruta
         * especifica.
         */
        try {
            File file = new File("");
            GenerarPlanoCesantiaImpl impl = new GenerarPlanoCesantiaImpl();

            StringBuffer cesantiaFileTxt = new StringBuffer();
            StringBuffer nombreArchivo = new StringBuffer();

            cesantiaFileTxt.append(file.getAbsolutePath());
            cesantiaFileTxt.append(IND_Constants.DIR_CESANTIA_TXT_PLANO43);
            cesantiaFileTxt.append(IND_Constants.NOM_SC_043);
            cesantiaFileTxt.append(periodo);
            cesantiaFileTxt.append(IND_Constants.EXT_texto);

            nombreArchivo.append(IND_Constants.NOM_SC_043);
            nombreArchivo.append(periodo);
            nombreArchivo.append(IND_Constants.EXT_texto);

            impl.generarPlanoCesantia043Txt(cesantiaFileTxt.toString(), cesantia.getListCesantia043VO());

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
                detalleReporte.setTipo_proceso(tipoReporte);
                detalleReporte.setPeriodo_proceso(Integer.parseInt(mesPeriodo));
                detalleReporte.setFomato_reporte(2);
                detalleReporte.setUsuario_sivegam(Long.parseLong(usser));

                respuesta = GenerarReportesDAO.insertDetalleReporteSivegam(detalleReporte);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (respuesta.getCodRespuesta() != 99) {
                cesantia.setRutaTxtCesantia(cesantiaFileTxt.toString());
                cesantia.setNombreArchivoCesantia(nombreArchivo.toString());
                respuesta.setCodRespuesta(0);
            }
        } catch (IOException f) {
            f.printStackTrace();
        }

        return respuesta;
    }

    public void generarPlanoCesantia043Txt(String archivo, LinCesantia043VO[] lineas) throws IOException {

        System.gc();
        //String nuevaLinea = "\n";
        String nuevaLinea = "\r\n";
        String pipe = "|";

        FileWriter fw = new FileWriter(archivo);
        StringBuffer linea = new StringBuffer("");

        for (int i = 0; i < lineas.length; i++) {

            linea = new StringBuffer("");
            LinCesantia043VO registro = new LinCesantia043VO();
            registro = lineas[i];

            String mes_if = Long.toString(registro.getMes_if());
            String codigo_entidad = Integer.toString(registro.getCodigo_entidad());
            String codigo_archivo = Integer.toString(registro.getCodigo_archivo());
            String mes_emision = Long.toString(registro.getMes_emision());
            String rut_beneficiario = Long.toString(registro.getRut_beneficiario());
            String dv_beneficiario = registro.getDv_beneficiario();
            String nombre_beneficiario = registro.getNombre_beneficiario();
            String tipo_egreso = Integer.toString(registro.getTipo_egreso());
            String mod_pago = Integer.toString(registro.getMod_pago());
            String serie_documento = registro.getSerie_documento();
            String numero_documento = registro.getNumero_documento();
            String monto_subsidio_cesantia = Long.toString(registro.getMonto_subsidio_cesantia());
            String monto_documento = Long.toString(registro.getMonto_documento());
            String fecha_emision_documento = Long.toString(registro.getFecha_emision_documento());
            String codigo_banco = Integer.toString(registro.getCodigo_banco());
            String fecha_cambio_estado_documento = Long.toString(registro.getFecha_cambio_estado_documento());
            String numero_cartola = registro.getNumero_cartola();

            linea.append(mes_if);
            linea.append(pipe);
            linea.append(codigo_entidad);
            linea.append(pipe);
            linea.append(codigo_archivo);
            linea.append(pipe);
            linea.append(mes_emision);
            linea.append(pipe);
            linea.append(rut_beneficiario);
            linea.append(pipe);
            linea.append(dv_beneficiario);
            linea.append(pipe);
            linea.append(nombre_beneficiario);
            linea.append(pipe);
            linea.append(tipo_egreso);
            linea.append(pipe);
            linea.append(mod_pago);
            linea.append(pipe);
            linea.append(serie_documento);
            linea.append(pipe);
            linea.append(numero_documento);
            linea.append(pipe);
            linea.append(monto_subsidio_cesantia);
            linea.append(pipe);
            linea.append(monto_documento);
            linea.append(pipe);
            linea.append(fecha_emision_documento);
            linea.append(pipe);
            linea.append(codigo_banco);
            linea.append(pipe);
            linea.append(101);
            linea.append(pipe);
            linea.append(fecha_cambio_estado_documento);
            linea.append(pipe);
            linea.append(numero_cartola);
            linea.append(nuevaLinea);

            fw.write(linea.toString());
        }

        System.gc();
        linea = new StringBuffer("");
        fw.close();

    }

    /** Generacion de txt para archivo 044 de cesantia. */
    public static RespuestaVO generarPlanoCesantia044(int tipoReporte, String periodo, String idMaestroSiv, String mesPeriodo, String usser, String fechaReporte) throws Exception,
            SQLException {

        CesantiaVO cesantia = new CesantiaVO();
        RespuestaVO respuesta = new RespuestaVO();

        cesantia.setListCesantia044VO(GenerarPlanoCesantiaDAO.generarPlanoCesantia044());
        /*
         * desde aca se genera el reporte con el archivo, con la ruta
         * especifica.
         */
        try {
            File file = new File("");
            GenerarPlanoCesantiaImpl impl = new GenerarPlanoCesantiaImpl();

            StringBuffer cesantiaFileTxt = new StringBuffer();
            StringBuffer nombreArchivo = new StringBuffer();

            cesantiaFileTxt.append(file.getAbsolutePath());
            cesantiaFileTxt.append(IND_Constants.DIR_CESANTIA_TXT_PLANO44);
            cesantiaFileTxt.append(IND_Constants.NOM_SC_044);
            cesantiaFileTxt.append(periodo);
            cesantiaFileTxt.append(IND_Constants.EXT_texto);

            nombreArchivo.append(IND_Constants.NOM_SC_044);
            nombreArchivo.append(periodo);
            nombreArchivo.append(IND_Constants.EXT_texto);

            impl.generarPlanoCesantia044Txt(cesantiaFileTxt.toString(), cesantia.getListCesantia044VO());

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
                detalleReporte.setTipo_proceso(tipoReporte);
                detalleReporte.setPeriodo_proceso(Integer.parseInt(mesPeriodo));
                detalleReporte.setFomato_reporte(2);
                detalleReporte.setUsuario_sivegam(Long.parseLong(usser));

                respuesta = GenerarReportesDAO.insertDetalleReporteSivegam(detalleReporte);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (respuesta.getCodRespuesta() != 99) {
                cesantia.setRutaTxtCesantia(cesantiaFileTxt.toString());
                cesantia.setNombreArchivoCesantia(nombreArchivo.toString());
                respuesta.setCodRespuesta(0);
            }
        } catch (IOException f) {
            f.printStackTrace();
        }

        return respuesta;
    }

    public void generarPlanoCesantia044Txt(String archivo, LinCesantia044VO[] lineas) throws IOException {

        System.gc();
        //String nuevaLinea = "\n";
        String nuevaLinea = "\r\n";
        String pipe = "|";

        FileWriter fw = new FileWriter(archivo);
        StringBuffer linea = new StringBuffer("");

        for (int i = 0; i < lineas.length; i++) {

            linea = new StringBuffer("");
            LinCesantia044VO registro = new LinCesantia044VO();
            registro = lineas[i];

            String mes_if = Long.toString(registro.getMes_if());
            String codigo_entidad = Integer.toString(registro.getCod_ent());
            String codigo_archivo = Integer.toString(registro.getCod_arc());
            String rut_beneficiario = Long.toString(registro.getRut_ben());
            String dv_beneficiario = registro.getDv_ben();
            String nombre_beneficiario = registro.getNom_ben();
            String mes_doc_informado = Long.toString(registro.getMes_doc_inf());
            String mod_pago_doc_original = Integer.toString(registro.getMod_pag_doc_ori());
            String serie_doc_original = registro.getSer_doc_ori();
            String numero_doc_original = registro.getNum_doc_ori();
            String monto_subsidio_original = Long.toString(registro.getMto_sub_ori());
            String monto_doc_original = Long.toString(registro.getMto_doc_ori());
            String fecha_emision_doc_original = Long.toString(registro.getFec_emi_doc_ori());
            String codigo_banco_doc_original = Integer.toString(registro.getCod_bco_doc_ori());
            String mod_pago_nuevo = Integer.toString(registro.getMod_pag_nvo());
            String serie_doc_nuevo = registro.getSer_doc_nvo();
            String numero_doc_nuevo = registro.getNum_doc_nvo();
            String monto_subsidio_nuevo = Long.toString(registro.getMto_sub_nvo());
            String monto_doc_nuevo = Long.toString(registro.getMto_doc_nvo());
            String fecha_emision_doc_nuevo = Long.toString(registro.getFec_emi_doc_nvo());
            String codigo_banco_doc_nuevo = Integer.toString(registro.getCod_bco_doc_nvo());

            linea.append(mes_if);
            linea.append(pipe);
            linea.append(codigo_entidad);
            linea.append(pipe);
            linea.append(codigo_archivo);
            linea.append(pipe);
            linea.append(rut_beneficiario);
            linea.append(pipe);
            linea.append(dv_beneficiario);
            linea.append(pipe);
            linea.append(nombre_beneficiario);
            linea.append(pipe);
            linea.append(mes_doc_informado);
            linea.append(pipe);
            linea.append(mod_pago_doc_original);
            linea.append(pipe);
            linea.append(serie_doc_original);
            linea.append(pipe);
            linea.append(numero_doc_original);
            linea.append(pipe);
            linea.append(monto_subsidio_original);
            linea.append(pipe);
            linea.append(monto_doc_original);
            linea.append(pipe);
            linea.append(fecha_emision_doc_original);
            linea.append(pipe);
            linea.append(codigo_banco_doc_original);
            linea.append(pipe);
            linea.append(mod_pago_nuevo);
            linea.append(pipe);
            linea.append(serie_doc_nuevo);
            linea.append(pipe);
            linea.append(numero_doc_nuevo);
            linea.append(pipe);
            linea.append(monto_subsidio_nuevo);
            linea.append(pipe);
            linea.append(monto_doc_nuevo);
            linea.append(pipe);
            linea.append(fecha_emision_doc_nuevo);
            linea.append(pipe);
            linea.append(codigo_banco_doc_nuevo);
            linea.append(nuevaLinea);
            fw.write(linea.toString());
        }

        System.gc();
        linea = new StringBuffer("");
        fw.close();
    }
}
