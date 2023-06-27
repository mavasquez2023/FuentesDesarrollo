package cl.araucana.independientes.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import cl.araucana.independientes.dao.SalidaIntercajaDAO;
import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.helper.IND_Constants;
import cl.araucana.independientes.helper.SendFileToAS400;
import cl.araucana.independientes.helper.UploadFileFTP;
import cl.araucana.independientes.vo.LinSalidaIntercajaVO;
import cl.araucana.independientes.vo.RespuestaVO;
import cl.araucana.independientes.vo.SalidaIntercajaVO;
import cl.araucana.independientes.vo.param.ListadoParametros;
import cl.araucana.independientes.vo.param.Parametro;

public class SalidaIntercajaImpl {

    public static SalidaIntercajaVO consultaSalidaAfiliadosIntercaja(String fechaInicio, String fechaFin, String user)throws IOException,SQLException
    {
        String result = "";

        SalidaIntercajaVO resp = new SalidaIntercajaVO();
        SalidaIntercajaVO resp2 = null;

        System.out.println("Archivo Salida Intercaja fechaInicio: "+ fechaInicio);
        System.out.println("Archivo Salida Intercaja fechaFin: "+ fechaFin);
        System.out.println("Archivo Salida Intercaja user: "+ user);
        System.out.println("Realiza consulta salida afiliados intercaja inicio");

        resp.setLisSalidaIntercaja(SalidaIntercajaDAO.consultaSalidaAfiliadosIntercaja(fechaInicio, fechaFin, user));

        System.out.println("Realiza consulta salida afiliados intercaja fin");

        //Inicio REQ-7173 JLGN 16-05-2013 Inserta en Tabla FILTRODETALLESALIDAINTERCAJA 
        String idDocumento = "";
        String digVerificador = "";
        boolean insFilSal = false;

        for(int x=0; x < resp.getLisSalidaIntercaja().length;x++){
            //Inserta en la tabla los afiliados fallecidos y los desafiliados
            String tipoEstadoAfiliado = resp.getLisSalidaIntercaja()[x].getTipoEstadoAfiliado();
            String tipoEstadoSolicitud = resp.getLisSalidaIntercaja()[x].getTipoEstadoSolicitud();
            String tipoSolicitud = resp.getLisSalidaIntercaja()[x].getTipoSolicitud();

            if(tipoEstadoAfiliado.equals("6") && tipoEstadoSolicitud.equals("7")){
                //afiliado fallecido
                insFilSal = true;
            }else if(tipoSolicitud.equals("2") && tipoEstadoSolicitud.equals("4")){
                //Solicitud desafiliacion aprobado
                insFilSal = true;
            }else if(tipoEstadoAfiliado.equals("4")){
                //desafiliado por sistema
                insFilSal = true;
            }

            if(insFilSal){
                idDocumento = resp.getLisSalidaIntercaja()[x].getRutAfiliado();
                digVerificador = resp.getLisSalidaIntercaja()[x].getDigVerificador();			
                System.out.println("["+x+"]idDocumento: "+ idDocumento +"-"+digVerificador);
                resp2 = SalidaIntercajaDAO.insertaRegistroFiltroSalida(Long.parseLong(idDocumento), digVerificador);
                insFilSal = false;
            }
        }
        //Fin REQ-7173 JLGN 16-05-2013

        String fecha = "";
        Date date = new Date();

        String DATE_FORMAT1 = "dd/MM/yyyy"; // El que se recibe de la BD
        String DATE_FORMAT2 = "yyyyMMdd"; // El que se envia a la Vista
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        try
        {
            date = sdf1.parse(fechaInicio);
            fecha = sdf2.format(date);
        }catch (ParseException e) {
            e.printStackTrace();
        }

        /*Se genera el archivo que contiene la interfaz*/
        try
        {
            String anio = fecha.substring(0,4);
            String mes = fecha.substring(4,6);

            File file = new File("");

            StringBuffer intercajaFile = new StringBuffer();
            StringBuffer nombreArchivo = new StringBuffer();

            intercajaFile.append(file.getAbsolutePath());
            intercajaFile.append(IND_Constants.DIR_SITC);
            intercajaFile.append(IND_Constants.str_salida);
            intercajaFile.append(mes);
            intercajaFile.append(anio);
            intercajaFile.append(IND_Constants.EXT_texto);

            //nombre del archivo.
            nombreArchivo.append(IND_Constants.str_salida);
            nombreArchivo.append(mes);
            nombreArchivo.append(anio);
            nombreArchivo.append(IND_Constants.EXT_texto);

            System.out.println("Nombre archivo intercaja: "+ intercajaFile.toString());
            System.out.println("Nombre archivo: "+ nombreArchivo.toString());
            System.out.println("Genera Archivo de Salida inicio");

            result = SalidaIntercajaImpl.obtenerArchivoSalida(intercajaFile.toString(), resp.getLisSalidaIntercaja(), fechaInicio, fechaFin);

            System.out.println("Genera Archivo de Salida fin");
            resp.setArchivoSalidaIntercaja(intercajaFile.toString());
            resp.setNombreArchivo(nombreArchivo.toString());

            String afiliacionesNuevas="";
            String desafiliados="";
            String cambioCCAF="";
            String fallecidos="";
            String desafiliados1 = "";
            String desafiliados2 = "";

            String[] temp;
            String gato = "#";
            temp = result.split(gato);

            for(int i=0; i<temp.length; i++)
            {
                if(i==0){
                    afiliacionesNuevas = temp[i];
                }
                if(i==1){
                    desafiliados = temp[i];
                }
                if(i==2){
                    cambioCCAF = temp[i];
                }
                if(i==3){
                    fallecidos = temp[i];
                }
                if(i==4){
                    desafiliados1 = temp[i];
                }
                if(i==5){
                    desafiliados2 = temp[i];
                }
            }

            resp.setAfiliacionesNuevas(afiliacionesNuevas);
            resp.setDesafiliados(desafiliados);
            resp.setCambioCCAF(cambioCCAF);
            resp.setFallecidos(fallecidos);
            resp.setDesafiliadosTipo1(desafiliados1);
            resp.setDesafiliadosTipo2(desafiliados2);

        }catch(SQLException e){
            e.printStackTrace();
        }catch(IOException f){
            f.printStackTrace();
        }

        return resp;
    }

    public static String obtenerArchivoSalida(String archivo, LinSalidaIntercajaVO[] lineas, String fechaInicio, String fechaFin) throws IOException,SQLException
    {
        String resultado = "";
        String newLine = "\n";
        int afiNews=0;
        int desafi1 = 0;
        int desafi2 = 0;
        int desaf = 0;
        int cambCCAF=0;
        int fallec=0;

        FileWriter fw = new FileWriter(archivo);
        StringBuffer linea = new StringBuffer("");

        for(int i=0; i< lineas.length; i++)
        {
            linea = new StringBuffer("");
            LinSalidaIntercajaVO registro = new LinSalidaIntercajaVO();
            registro = lineas[i];

            String ccaf = Integer.toString(registro.getCcafProcedencia());;
            String rut = registro.getRutAfiliado();
            String dv = registro.getDigVerificador();
            String fechaSolicitud = registro.getFechaSolicitudAfiliacion();
            String codigoMov = "1";//registro.getCodigoMovimiento();
            String nombre = registro.getNombreAfiliado();
            String apePat = registro.getApellidoPaternoAfiliado();
            String apeMat = registro.getApellidoMaternoAfiliado();
            String cero = registro.getCeros();
            String blancos = registro.getBlancoUno();
            String codInt1 = registro.getCodigoInternoUno();
            String codInt2 = IND_Constants.str_cod_int_2;
            String montoUltCot = Long.toString(registro.getMontoUltCotizacion());
            String fechaUltCot = registro.getFechaUltCotizacion();
            String blancos2 = registro.getBlancoDos();
            String tipoCajaOrigen = Long.toString(registro.getTipoCajaOrigen());

            if(codigoMov.equals("1"))
            {
                afiNews++;
            }else{
                if(codigoMov.equals("2")){
                    if(tipoCajaOrigen.equals("1")){
                        desafi1++;//desafiliaciones del tipo 1
                    }else{
                        desafi2++;//desafiliaciones del tipo 2
                    }
                }else{
                    if(codigoMov.equals("3")){
                        cambCCAF++;
                    }else{
                        fallec++;
                    }
                }
            }

            String apellidos = apePat + " " + apeMat;

            if(apellidos.length()>20){
                apellidos = apellidos.substring(0, 19);
            }

            if(nombre.length()>20){
                nombre = nombre.substring(0,19);
            }
            // ingresar los valores al archivo y darle formato.
            linea.append(ccaf);
            linea.append(Helper.paddingString(rut, 8, '0', true));
            linea.append(dv);
            linea.append(fechaSolicitud);
            linea.append(codigoMov);
            linea.append(Helper.paddingString(apellidos,20,' ',false));
            linea.append(Helper.paddingString(nombre, 20, ' ', false));
            linea.append(cero);
            linea.append(blancos);
            linea.append(codInt1);
            linea.append(codInt2);
            linea.append(Helper.paddingString(montoUltCot, 8, '0', true));
            linea.append(fechaUltCot);
            linea.append(blancos2);linea.append(newLine);

            fw.write(linea.toString());
        }

        linea = new StringBuffer("");
        fw.close();

        desaf = desafi1 + desafi2;

        resultado = Integer.toString(afiNews) + "#" + Integer.toString(desaf) + "#" + Integer.toString(cambCCAF) + "#" + Integer.toString(fallec) +"#"+ Integer.toString(desafi1)+ "#" + Integer.toString(desafi2);
        return resultado;
    }

    public static SalidaIntercajaVO insertaRegistroLogIntercaja(String nombreArchivo, String numeroSesion, String fechaSesion, String fechaInicio, String fechaCorte, String fechaProceso, String rutAnalista, String afiliadosNuevos, String desafiliados, String cambioCCAF, String fallecidos, String desafiliadosTipoUno, String desafiliadosTipoDos)
    {
        return SalidaIntercajaDAO.insertaRegistroLogIntercaja(nombreArchivo, numeroSesion, fechaSesion, fechaInicio, fechaCorte, fechaProceso, rutAnalista, afiliadosNuevos, desafiliados, cambioCCAF, fallecidos, desafiliadosTipoUno, desafiliadosTipoDos);
    }

    public static SalidaIntercajaVO verificaNumeroSesion(String numeroSesion)
    {
        return SalidaIntercajaDAO.verificaNumeroSesion(numeroSesion);
    }

    public static SalidaIntercajaVO obtenerPrimerYUltimoDiaMes(String fechaSistema)
    {
        SalidaIntercajaVO resp = new SalidaIntercajaVO();

        resp.setFechaConPimerDiaMes(Helper.obtenerPrimerDiaMes(fechaSistema));
        resp.setFechaConUltimoDiaMes(Helper.obtenerUltimoDiaMes(fechaSistema));

        return resp;
    }

    public static SalidaIntercajaVO updateStatusSendMail(String nombreArchivo)
    {
        return SalidaIntercajaDAO.updateStatusSendMail(nombreArchivo);
    }

    public static String enviarArchivoHistoricoAFTP(String nombreArchivo, String rutaArchivo)throws MalformedURLException, IOException
    {
        String ftpServer = "";
        String user = ""; 
        String password = "";
        String destino = "";

        ListadoParametros listaParam = ListadoParametros.getInstancia();
        Parametro[] paramConexionFTP = listaParam.getListParametrosConexionFTPIntercaja();

        for(int i=0; i<paramConexionFTP.length; i++)
        {
            int codigo = 0;
            codigo = paramConexionFTP[i].getCodigo();

            if(codigo == 1){
                ftpServer = paramConexionFTP[i].getGlosa();
            }

            if(codigo == 2){
                user = paramConexionFTP[i].getGlosa();
            }

            if(codigo == 3){
                password = paramConexionFTP[i].getGlosa();
            }

            if(codigo == 5){
                destino = paramConexionFTP[i].getGlosa();
            }
        }

        UploadFileFTP.subirArchivo(ftpServer, user, password, nombreArchivo, rutaArchivo, destino);

        return "Ok";
    }

    public static int eliminarArchivoServer()
    {
        String rutaArchivoServer;
        File file = new File("");
        StringBuffer ruta = new StringBuffer();

        ruta.append(file.getAbsolutePath());
        ruta.append(IND_Constants.DIR_SITC);

        File fileTemp = new File(ruta.toString());

        File[] rutaArchivo = fileTemp.listFiles();

        for(int i=0; i<rutaArchivo.length; i++)
        {
            int j;
            File temp = rutaArchivo[i];

            if(temp != null){
                rutaArchivoServer = temp.toString();
            }
            else{
                rutaArchivoServer = "";	
                return -1;
            }

            j = Helper.deleteFile(rutaArchivoServer);

            if(j == 1){
                return 0;
            }else{
                return -1;
            }
        }	
        return 0;
    }

    public static RespuestaVO verificarFlagCorreo(String nombreArchivoEnviado)
    {
        return SalidaIntercajaDAO.verificarFlagCorreo(nombreArchivoEnviado);
    }

    /*Funcion que obtiene los rangos para Intercaja de forma parametrizada.
     * No recibe parametros de entrada.
     * Retorna un objeto tipo SalidaIntercajaVO con el rango de Intercaja.*/
    public static SalidaIntercajaVO obtenerRangoIntercaja()
    {
        SalidaIntercajaVO salidaVo = new SalidaIntercajaVO();

        ListadoParametros listaParam = ListadoParametros.getInstancia();
        Parametro[] rangoIntercaja = listaParam.getListRangoIntercaja();

        for(int i=0; i<rangoIntercaja.length; i++)
        {
            int codigo = 0;
            codigo = rangoIntercaja[i].getCodigo();

            if(codigo == 1){
                salidaVo.setRangoUno(rangoIntercaja[i].getGlosa());
                salidaVo.setCodResultado(0);
            }
            if(codigo == 2){
                salidaVo.setRangoDos(rangoIntercaja[i].getGlosa());
                salidaVo.setCodResultado(0);
            }
            if(codigo == 3){
                salidaVo.setRangoTres(rangoIntercaja[i].getGlosa());
                salidaVo.setCodResultado(0);
            }
        }

        return salidaVo;
    }

    /*Transfiere el archivo que fue procesado, a la carpeta historica*/
    public static String transferirFileToAS400(String rutaArchivo, String nombreArchivo, String tipoArchivo)
    {
        String ip_conexion = "";
        String user_conexion = "";
        String pass_conexion = "";
        String destino = "";

        ListadoParametros listaParam = ListadoParametros.getInstancia();
        Parametro[] parametro = listaParam.getListParametrosConexionFTPIntercaja();

        for(int i=0; i<parametro.length; i++)
        {
            int cod = 0;
            cod = parametro[i].getCodigo();
            if(cod == 1){
                ip_conexion = parametro[i].getGlosa();
            }
            if(cod == 2){
                user_conexion = parametro[i].getGlosa();
            }
            if(cod == 3){
                pass_conexion = parametro[i].getGlosa();
            }
            if((cod == 5) && (Integer.parseInt(tipoArchivo)) == 7){
                destino = parametro[i].getGlosa();
            }	
        }

        //define un archivo origen de Windows
        String origen = rutaArchivo;

        StringBuffer destinoAS400 = new StringBuffer();
        destinoAS400.append(destino);
        destinoAS400.append(nombreArchivo);

        try 
        {
            SendFileToAS400 files= new SendFileToAS400(ip_conexion, user_conexion, pass_conexion);
            files.copiarArchivoToAS400(origen, destinoAS400.toString());
            files.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Ok";
    }	
}
