package cl.araucana.sivegam.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import cl.araucana.sivegam.dao.GenerarPlanosAFCDAO;
import cl.araucana.sivegam.dao.GenerarReportesDAO;
import cl.araucana.sivegam.helper.Helper;
import cl.araucana.sivegam.helper.HelperAFCCesantia;
import cl.araucana.sivegam.helper.IND_Constants;
import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.vo.AfcVO;
import cl.araucana.sivegam.vo.DetalleReporteSivegamVO;
import cl.araucana.sivegam.vo.LinAfcAFF01VO;
import cl.araucana.sivegam.vo.ProcesosAFCCesantiaVO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.param.ListadoParametros;

public class GenerarPlanoAFCImpl {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    /**
     * Funcion que toma la ruta de los planos de cesantia, dependiendo del tipo
     * de Reporte, desde las tablas parametricas.
     */
    public static StringBuffer retornarRutaPlanos(int tipoReporte, String periodo) {

        String carpeta = "";
        String nombre = "";
        StringBuffer ruta = new StringBuffer();
        File file = new File("");
        ListadoParametros lp = ListadoParametros.getInstancia();
        ProcesosAFCCesantiaVO[] procesos = lp.getListProcesoAfcCesantia();

        for (int i = 0; i < procesos.length; i++) {
            long idprocAfcSc = 0;
            switch (tipoReporte) {
            case 8:
                idprocAfcSc = 1;
                break;
            case 9:
                idprocAfcSc = 2;
                break;
            default:
                logger.debug("No es posible asignar un idproceso porque el id del reporte no coincide con los archivos de afc");
            }

            if (procesos[i].getId_proceso_afc_cesantia() == idprocAfcSc) {
                carpeta = procesos[i].getRuta_txt_servidor();
                nombre = procesos[i].getNomenclatura_nombre_txt();
            }
        }

        String cabeceraArchivo1 = "";
        String cabeceraArchivo2 = "";
        StringBuffer nombreArchivo = new StringBuffer();

        StringTokenizer st = new StringTokenizer(nombre, "#");
        while (st.hasMoreTokens()) {
            cabeceraArchivo1 = st.nextToken();
            cabeceraArchivo2 = st.nextToken();
        }

        nombreArchivo.append(cabeceraArchivo1);
        nombreArchivo.append(periodo);
        nombreArchivo.append(cabeceraArchivo2);
        nombreArchivo.append(HelperAFCCesantia.retornarFormatoReporteAfc());

        ruta.append(file.getAbsolutePath());
        ruta.append(carpeta);
        ruta.append(nombreArchivo);
        /* aniade nombre a la ruta. */

        return ruta;

    }

    public static RespuestaVO generarPlanosAfc(String tipoReporte, String flagReporte, String periodo, String idMaestroSiv, String mesPeriodo, String usser, String fechaReporte)
            throws Exception, SQLException {

        AfcVO afc = new AfcVO();
        RespuestaVO resp = new RespuestaVO();

        switch (Integer.parseInt(tipoReporte)) {
        case 8:
            afc.setListAfcAFF01VO(GenerarPlanosAFCDAO.generarPlanosAfcRetroactivo());
            break;
        case 9:
            afc.setListAfcAFF01VO(GenerarPlanosAFCDAO.generarPlanosAfcMensual());
            break;
        default:
            resp.setCodRespuesta(99);
            return resp;
        }

        /*
         * desde aca se genera el reporte con el archivo, con la ruta
         * especifica.
         */
        try {
            File file = new File("");
            GenerarPlanoAFCImpl impl = new GenerarPlanoAFCImpl();

            StringBuffer AFCFileTxt = new StringBuffer();
            StringBuffer nombreArchivo = new StringBuffer();

 //           logger.debug("Flag Reporte: " + flagReporte);
            if ("R".equals(flagReporte)) {

                AFCFileTxt.append(file.getAbsolutePath());
                AFCFileTxt.append(IND_Constants.DIR_AFC_TXT_AFCRET);
                AFCFileTxt.append(IND_Constants.NOM_AFC);
                AFCFileTxt.append(periodo);
                AFCFileTxt.append("13_S");
                AFCFileTxt.append(IND_Constants.AFC_DAT);

                // nombre del archivo retroactivo
                nombreArchivo.append(IND_Constants.NOM_AFC);
                nombreArchivo.append(periodo);
                nombreArchivo.append("13_S");
                nombreArchivo.append(IND_Constants.AFC_DAT);
            } else {
                AFCFileTxt.append(file.getAbsolutePath());
                AFCFileTxt.append(IND_Constants.DIR_AFC_TXT_AFCMEN);
                AFCFileTxt.append(IND_Constants.NOM_AFC);
                AFCFileTxt.append(periodo);
                AFCFileTxt.append("30_S");
                AFCFileTxt.append(IND_Constants.AFC_DAT);

                // nombre del archivo mensual
                nombreArchivo.append(IND_Constants.NOM_AFC);
                nombreArchivo.append(periodo);
                nombreArchivo.append("30_S");
                nombreArchivo.append(IND_Constants.AFC_DAT);
            }

            impl.generarPlanoAfcTxt(AFCFileTxt.toString(), afc.getListAfcAFF01VO());

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
                detalleReporte.setTipo_proceso(Integer.parseInt(tipoReporte));
                detalleReporte.setPeriodo_proceso(Integer.parseInt(mesPeriodo));
                detalleReporte.setFomato_reporte(2);
                detalleReporte.setUsuario_sivegam(Long.parseLong(usser));

                resp = GenerarReportesDAO.insertDetalleReporteSivegam(detalleReporte);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (resp.getCodRespuesta() != 99) {
                resp.setRutaArchivo(AFCFileTxt.toString());
                afc.setNombreArchivo(nombreArchivo.toString());
                resp.setCodRespuesta(0);
            }
        } catch (IOException f) {
            f.printStackTrace();
        }

        return resp;
    }

    public void generarPlanoAfcTxt(String archivo, LinAfcAFF01VO[] lineas) throws IOException {

        System.gc();
       //String nuevaLinea = "\n";
       String nuevaLinea = "\r\n";

        FileWriter fw = new FileWriter(archivo);
        StringBuffer linea = new StringBuffer("");

        for (int i = 0; i < lineas.length; i++) {

            linea = new StringBuffer("");
            LinAfcAFF01VO registro = new LinAfcAFF01VO();
            registro = lineas[i];

            String rut_Afiliado = Long.toString(registro.getRut_Afiliado());
            String digito_Verificador_Afiliado = registro.getDigito_Verificador_Afiliado();
            String nombres_Afiliado = registro.getNombres_Afiliado();
            String apellido_Paterno_Afiliado = registro.getApellido_Paterno_Afiliado();
            String apellido_Materno_Afiliado = registro.getApellido_Materno_Afiliado();
            String tramo = registro.getTramo();
            String rut_Causante = Long.toString(registro.getRut_Causante());
            String digito_Verificador_Causante = registro.getDigito_Verificador_Causante();
            String nombres_Causantes = registro.getNombres_Causantes();
            String apellido_Paterno_Causante = registro.getApellido_Paterno_Causante();
            String apellido_Materno_Causante = registro.getApellido_Materno_Causante();
            String fecha_de_Nacimiento = Long.toString(registro.getFecha_de_Nacimeinto());
            String codigo_Tipo_Causa = Integer.toString(registro.getCodigo_Tipo_Causa());
            String tipo_Causante = registro.getTipo_Causante();
            String tipo_Asignación_familiar = registro.getTipo_Asignacion_familiar();
            String numero_Solicitud = Long.toString(registro.getNumero_Solicitud());
            String tipo_Solicitud = registro.getTipo_Solicitud();
            String sexo = registro.getSexo();
            String monto = Long.toString(registro.getMonto());
            String renta = Long.toString(registro.getRenta());
            String comuna = Integer.toString(registro.getComuna());
            String region = Integer.toString(registro.getRegion());
            String tipo_Beneficiario = Integer.toString(registro.getTipo_Beneficiario());

            tipo_Causante = tipo_Causante.trim();

            if (tipo_Causante.length() > 20) {
                tipo_Causante = tipo_Causante.substring(0, 20);
            }

            /**
             * Formato del archivo, se completan algunos campos con 0 a la
             * izquierda y otros con espacios a la derecha.
             */
            linea.append(Helper.paddingString(rut_Afiliado, 10, '0', true));
            linea.append(Helper.paddingString(digito_Verificador_Afiliado, 1, ' ', false));
            linea.append(Helper.paddingString(nombres_Afiliado, 20, ' ', false));
            linea.append(Helper.paddingString(apellido_Paterno_Afiliado, 20, ' ', false));
            linea.append(Helper.paddingString(apellido_Materno_Afiliado, 20, ' ', false));
            linea.append(Helper.paddingString(tramo, 1, ' ', false));
            linea.append(Helper.paddingString(rut_Causante, 10, '0', true));
            linea.append(Helper.paddingString(digito_Verificador_Causante, 1, ' ', false));
            linea.append(Helper.paddingString(nombres_Causantes, 20, ' ', false));
            linea.append(Helper.paddingString(apellido_Paterno_Causante, 20, ' ', false));
            linea.append(Helper.paddingString(apellido_Materno_Causante, 20, ' ', false));
            linea.append(Helper.paddingString(fecha_de_Nacimiento, 8, ' ', false));
            linea.append(Helper.paddingString(codigo_Tipo_Causa, 2, '0', true));
            linea.append(Helper.paddingString(tipo_Causante, 20, ' ', false));
            linea.append(Helper.paddingString(tipo_Asignación_familiar, 1, ' ', false));
            linea.append(Helper.paddingString(numero_Solicitud, 10, '0', true));
            linea.append(Helper.paddingString(tipo_Solicitud, 2, ' ', false));
            linea.append(Helper.paddingString(sexo, 1, ' ', false));
            linea.append(Helper.paddingString(monto, 10, '0', true));
            linea.append(Helper.paddingString(renta, 13, '0', true));
            linea.append(Helper.paddingString(comuna, 5, '0', true));
            linea.append(Helper.paddingString(region, 2, '0', true));
            linea.append(Helper.paddingString(tipo_Beneficiario, 2, '0', true));
            linea.append(nuevaLinea);

            fw.write(linea.toString());
        }
        System.gc();
        linea = new StringBuffer("");
        fw.close();
    }
}
