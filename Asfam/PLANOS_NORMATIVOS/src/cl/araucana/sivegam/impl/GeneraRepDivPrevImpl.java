package cl.araucana.sivegam.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.StringTokenizer;

import cl.araucana.sivegam.conexion.cobol.bo.ParametrosConexionBO;
import cl.araucana.sivegam.conexion.cobol.bo.ParametrosLlamadaBO;
import cl.araucana.sivegam.config.ConsumidorCobol;
import cl.araucana.sivegam.dao.GeneraRepDivPrevDAO;
import cl.araucana.sivegam.helper.Helper;
import cl.araucana.sivegam.helper.IND_Constants;
import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.vo.LinSif018VO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.Sif018VO;
import cl.araucana.sivegam.vo.ValoresConexionAS400VO;
import cl.araucana.sivegam.vo.param.ListadoParametros;

public class GeneraRepDivPrevImpl {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    /* Funcion que genera el reporte requerido (para SIF018 en formato TXT). */
    public void generarReporteSif018Txt(String archivo, LinSif018VO[] lineas) throws IOException {

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
            String nombre_empleador = registro.getNombre_empleador();
            String mod_pago = Integer.toString(registro.getMod_pago());
            String monto_documento = Long.toString(registro.getMonto_documento());
            String numero_serie = registro.getNumero_serie();
            String numero_documento = registro.getNumero_documento();
            String fecha_emision_documento = Long.toString(registro.getFecha_emision_documento());
            String codigo_banco = Integer.toString(registro.getCodigo_banco());
            String fecha_cobro = Long.toString(registro.getFecha_cobro());

            StringTokenizer stTexto = new StringTokenizer(numero_serie);
            String numeroDeSerie = "";
            while (stTexto.hasMoreElements()) {
                numeroDeSerie += stTexto.nextElement();
            }

            stTexto = new StringTokenizer(numero_documento);
            String numeroDocumento = "";
            while (stTexto.hasMoreElements()) {
                numeroDocumento += stTexto.nextToken();
            }

            String nombreEmpleador = nombre_empleador.trim();

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
            linea.append(nombreEmpleador);
            linea.append(pipe);
            linea.append(mod_pago);
            linea.append(pipe);
            linea.append(monto_documento);
            linea.append(pipe);
            linea.append(numeroDeSerie);
            linea.append(pipe);
            linea.append(numeroDocumento);
            linea.append(pipe);
            linea.append(fecha_emision_documento);
            linea.append(pipe);
            linea.append(codigo_banco);
            linea.append(pipe);
            linea.append(fecha_cobro);
            linea.append(nuevaLinea);

            fw.write(linea.toString());
        }

        linea = new StringBuffer("");
        fw.close();

    }

    /* Funcion que obtiene la lista con los registros de la tabla SIF018. */
    public static Sif018VO consultaRegistrosSif018() throws IOException, SQLException {

        GeneraRepDivPrevDAO dao = new GeneraRepDivPrevDAO();
        Sif018VO vo = new Sif018VO();

        /*
         * desde aca se genera el reporte con el archivo, con la ruta
         * especifica.
         */
        try {
            vo.setListSif018(dao.consultaRegistrosSif018());
            File file = new File("");
            GeneraRepDivPrevImpl impl = new GeneraRepDivPrevImpl();

            StringBuffer sif018FileTxt = new StringBuffer();
            StringBuffer nombreArchivo = new StringBuffer();

            sif018FileTxt.append(file.getAbsolutePath());
            sif018FileTxt.append(IND_Constants.DIR_SIVEGAM_TXT_SIF018);
            sif018FileTxt.append(IND_Constants.SUF_SIVEGAM_SIF018);
            sif018FileTxt.append(IND_Constants.EXT_texto);

            nombreArchivo.append(IND_Constants.SUF_SIVEGAM_SIF018);
            nombreArchivo.append(IND_Constants.EXT_texto);

            impl.generarReporteSif018Txt(sif018FileTxt.toString(), vo.getListSif018());
            vo.setRutaArchivo(sif018FileTxt.toString());
            vo.setNombreArchivo(nombreArchivo.toString());
            vo.setCodResultado(0);

        } catch (IOException f) {
            f.printStackTrace();
        }

        return vo;
    }
}
