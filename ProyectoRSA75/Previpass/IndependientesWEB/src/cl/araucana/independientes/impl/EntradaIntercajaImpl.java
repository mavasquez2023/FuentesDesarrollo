package cl.araucana.independientes.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import cl.araucana.independientes.dao.EntradaIntercajaDAO;
import cl.araucana.independientes.helper.ConsumidorCobol;
import cl.araucana.independientes.helper.GlobalProperties;
import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.helper.IND_Constants;
import cl.araucana.independientes.helper.SendFileToAS400;
import cl.araucana.independientes.helper.UploadFileFTP;
import cl.araucana.independientes.intercaja.cobol.bo.ParametrosConexionBO;
import cl.araucana.independientes.intercaja.cobol.bo.ParametrosLlamadaBO;
import cl.araucana.independientes.vo.EntradaIntercajaVO;
import cl.araucana.independientes.vo.SalidaIntercajaVO;
import cl.araucana.independientes.vo.param.ListadoParametros;
import cl.araucana.independientes.vo.param.Parametro;

public class EntradaIntercajaImpl {

    /*Funcion que inserta datos en la tabla maestro intercaja*/
    public static EntradaIntercajaVO insertarMaestroIntercaja(String nombreArchivo, String fechaCabecera, String fechaProceso, String tipoArchivo, String rutAnalista)
    {
        return EntradaIntercajaDAO.insertarMaestroIntercaja(nombreArchivo, fechaCabecera, fechaProceso, tipoArchivo, rutAnalista);
    }

    /*Funcion que lee el archivo para encontrar el tipo de archivo y la fecha de cabecera.
     * Recibe como parametro la ruta del archivo
     * retorna 0 si todo resulto ok, 
     * ademas retorna el tipo de archivo y la fecha de cabecera.
     * retorna 99 si ocurrio un error en la lectura del archivo.*/

    public static EntradaIntercajaVO leerArchivo(String rutaArchivo, String tipoArchivo)
    {
        Date date = new Date();

        EntradaIntercajaVO resp = new EntradaIntercajaVO();
        resp.setCodResultado(99);

        if(tipoArchivo.equals("7"))
        {
            resp.setCodResultado(99);
            return resp;
        }

        String nombreArchivo = rutaArchivo.substring(rutaArchivo.lastIndexOf("\\")).toLowerCase();
        nombreArchivo = nombreArchivo.substring(1,5);
        String nameFile = rutaArchivo.substring(rutaArchivo.lastIndexOf("\\")).toLowerCase();
        nameFile = nameFile.substring(1,3);

        if(nombreArchivo.equals("base") || nombreArchivo.equals(nameFile))
        {
            EntradaIntercajaImpl.leerArchivoFlujo4(rutaArchivo);
            resp.setTipoArchivoLeido(IND_Constants.str_baseComunAfiliaciones);
            resp.setTipoArchivo(6);
            resp.setCodResultado(0);

        }else
        {
            String DATE_FORMAT1 = "dd/MM/yyyy";
            String DATE_FORMAT2 = "dd.MMM.yyyy"; // El que se envia a la Vista
            SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
            SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

            try 
            {
                /*Primero debe verificar que el archivo es de estadicticas sino es cualquiera de los otros.(excepto base comun)*/
                boolean verify;
                verify = EntradaIntercajaImpl.verificarSiEsArchivoEstadisticas(rutaArchivo);

                if(verify)
                {
                    String encabezado = "";
                    encabezado = EntradaIntercajaImpl.retornaCabeceraArchivoEstadistica(rutaArchivo);

                    String fechaEstadistica = encabezado.substring(69,80);
                    date = sdf2.parse(fechaEstadistica);
                    resp.setFechaCabecera(sdf1.format(date));

                    if(encabezado.indexOf(IND_Constants.str_estadistica) > -1)
                    {
                        resp.setTipoArchivoLeido(IND_Constants.str_estadistica);
                        resp.setTipoArchivo(4);
                        resp.setCodResultado(0);
                    }

                    return resp;

                }else{

                    FileReader fr = new FileReader(rutaArchivo);
                    BufferedReader bf = new BufferedReader(fr);
                    String sCadena;
                    //String colisiones = "COLISIONES";
                    String cad = "";
                    int i = 0;

                    while(i <= 3)
                    {
                        sCadena = bf.readLine();

                        if(i==2)
                        {
                            cad = sCadena;
                        }	
                        i++;
                    }
                    bf.close();

                    String fecha = cad.substring(69, 80);
                    date = sdf2.parse(fecha);

                    resp.setFechaCabecera(sdf1.format(date));

                    if(cad.indexOf(IND_Constants.str_traspasados) > -1)
                    {
                        resp.setTipoArchivoLeido(IND_Constants.str_traspasados);
                        resp.setTipoArchivo(1);
                        resp.setCodResultado(0);

                    }else{
                        if(cad.indexOf(IND_Constants.str_colisiones) > -1){
                            resp.setTipoArchivoLeido(IND_Constants.str_colisiones);
                            resp.setTipoArchivo(2);
                            resp.setCodResultado(0);

                        }else{
                            if(cad.indexOf(IND_Constants.str_recibidos) > -1){
                                resp.setTipoArchivoLeido(IND_Constants.str_recibidos);
                                resp.setTipoArchivo(3);
                                resp.setCodResultado(0);

                            }else{
                                if(cad.indexOf(IND_Constants.str_estadistica) > -1){
                                    resp.setTipoArchivoLeido(IND_Constants.str_estadistica);
                                    resp.setTipoArchivo(4);
                                    resp.setCodResultado(0);

                                }else{
                                    if(cad.indexOf(IND_Constants.str_errores) > -1){
                                        resp.setTipoArchivoLeido(IND_Constants.str_errores);
                                        resp.setTipoArchivo(5);
                                        resp.setCodResultado(0);

                                    }else{
                                        resp.setCodResultado(99);
                                        resp.setResultado("Error, no es el archivo especificado.");

                                    }
                                }
                            }	
                        }
                    }

                }
                return resp;

            } catch (FileNotFoundException fnfe){

                resp.setCodResultado(99);
                fnfe.printStackTrace();

            } catch (IOException ioe){

                resp.setCodResultado(99);
                ioe.printStackTrace();

            }catch (ParseException e) {

                resp.setCodResultado(99);
                e.printStackTrace();
            }catch (Exception e) {

                resp.setCodResultado(99);
                e.printStackTrace();
            }

        }

        return resp;
    }

    /*añadido para leer archivo del flujo 4*/
    public static EntradaIntercajaVO leerArchivoFlujo4(String rutaArchivo)
    {
        EntradaIntercajaVO resp = new EntradaIntercajaVO();
        resp.setCodResultado(99);

        try 
        {
            FileReader fr = new FileReader(rutaArchivo);
            BufferedReader bf = new BufferedReader(fr);
            String sCadena;

            String cad = "";

            int i = 0;
            while(i <= 1)
            {
                sCadena = bf.readLine();

                if(i == 0)
                {
                    cad = sCadena;
                }

                i++;
            }
            bf.close();

            if(cad.length() == 108)
            {
                if(cad.indexOf(IND_Constants.str_fileFlujo4) > -1)
                {
                    resp.setTipoArchivoLeido(IND_Constants.str_baseComunAfiliaciones);
                    resp.setTipoArchivo(6);
                    resp.setCodResultado(0);
                }else
                {
                    resp.setCodResultado(99);
                    return resp;
                }	
            }else{

                resp.setCodResultado(99);
                return resp;
            }

            return resp;

        } catch (FileNotFoundException fnfe){

            resp.setCodResultado(99);
            fnfe.printStackTrace();

        } catch (IOException ioe){

            resp.setCodResultado(99);
            ioe.printStackTrace();

        }	

        return resp;		
    }
    /*fin leer archivo para flujo 4*/

    public static EntradaIntercajaVO obtenerUltimaDiaMes(String fechaActual)
    {
        EntradaIntercajaVO resp = new EntradaIntercajaVO();

        resp.setFechaUltDia(Helper.obtenerUltimoDiaMes(fechaActual));
        resp.setCodResultado(0);

        return resp;
    }

    /*Funcion que obtiene los datos para la grilla.*/
    public EntradaIntercajaVO obtenerDataArchivosProcesados(String fechaInicio, String fechaFin)throws IOException,SQLException
    {
        EntradaIntercajaVO resp = new EntradaIntercajaVO();

        resp.setLisEntradaIntercaja(EntradaIntercajaDAO.obtenerDataArchivosProcesados(fechaInicio, fechaFin));

        return resp;
    }

    /*Funcion que verifica si un registro ya fue insertado en la tabla maestroIntercaja*/	
    public static EntradaIntercajaVO validaRegistroMaestroIntercaja(String nombreArchivo, String tipoArchivo)
    {
        return EntradaIntercajaDAO.validaRegistroMaestroIntercaja(nombreArchivo, tipoArchivo);
    }

    /*Funcion que invoca al proceso Cobol.*/
    public static EntradaIntercajaVO invocarProcesoCobol(String idTipoArchivo, String nombreArchivo, String idMaestroArchivo, String idUsuarioLlamada)
    {	
        /*
         * Retornos de la funcion:
         * 0: si el proceso fue realizado con exito (control = 03 y logAs400 = 000000000000)
         * 95: si el proceso fue fallido (control = 02 y logAS400 = 000000000000)
         * 99: si proceso fue fallido (control = 02 y logAS400 != 000000000000) 
         * */
        String ipServer = "";
        String usuarioConexion = "";
        String claveConexion = "";
        String programa = "";
        String control = "";
        String idLogAS400 = "";

        EntradaIntercajaVO resp = new EntradaIntercajaVO();

        ParametrosConexionBO paramConexion = new ParametrosConexionBO();//para setear parametros de conexion
        ParametrosLlamadaBO[] paramLlamada = new ParametrosLlamadaBO[6];//para setear parametros de entrada al programa cobol

        /*Para cargar parametrica con los parametros de conexion a la máquina AS400.*/
        /*
		ListadoParametros ListaParam = ListadoParametros.getInstancia();
		Parametro[] param = ListaParam.getListParametrosConexionCobol();
         */
        //conversion de parametros de entrada para el proceso cobol
        String param1 = Helper.paddingString(idTipoArchivo, 2, '0', true);
        String param2 = Helper.paddingString(idMaestroArchivo, 12, '0', true);
        String param3 = nombreArchivo;
        String param4 = Helper.paddingString(idUsuarioLlamada, 10, '0', true);

        /*Obtencion de parametros de conexion*/
        /*
		for(int i = 0; i<param.length; i++)
		{
			int codigo = 0;
			codigo = param[i].getCodigo();

			if(codigo == 1){
				ipServer = Helper.obtenerDescripcion(param, codigo);
			}
			if(codigo == 2){
				usuarioConexion = Helper.obtenerDescripcion(param, codigo);
			}
			if(codigo == 3){
				claveConexion = Helper.obtenerDescripcion(param, codigo);
			}
			if(codigo == 4){
				programa = Helper.obtenerDescripcion(param, codigo);
			}
		}
         */

        GlobalProperties global = GlobalProperties.getInstance();

        ipServer = global.getValorExterno("IND.conexion.cobol.intercaja.IP");
        usuarioConexion = global.getValorExterno("IND.conexion.cobol.intercaja.user");
        claveConexion = global.getValorExterno("IND.conexion.cobol.intercaja.pass");
        programa =  global.getValorExterno("IND.conexion.cobol.intercaja.prog");

        System.out.println("ipServer: "+ ipServer);
        System.out.println("usuarioConexion: "+ usuarioConexion);
        System.out.println("claveConexion: "+ claveConexion);
        System.out.println("programa: "+ programa);

        System.out.println("idTipoArchivo: "+ idTipoArchivo);
        System.out.println("nombreArchivo: "+ nombreArchivo);
        System.out.println("idMaestroArchivo: "+ idMaestroArchivo);
        System.out.println("idUsuarioLlamada: "+ idUsuarioLlamada);

        System.out.println("param1: "+ param1);
        System.out.println("param2: "+ param2);
        System.out.println("param3: "+ param3);
        System.out.println("param4: "+ param4);


        /*Seteo de los parametros de conexion.*/
        paramConexion.setIpServer(ipServer);
        paramConexion.setUsuarioConexion(usuarioConexion);
        paramConexion.setClaveConexion(claveConexion);
        paramConexion.setPrograma(programa);

        /*Seteo de los parametros de entrada para el proceso COBOL, mediante paramLlamada.*/
        ParametrosLlamadaBO parametro1 = new ParametrosLlamadaBO(); //idtipoarchivo
        ParametrosLlamadaBO parametro2 = new ParametrosLlamadaBO(); //idmaestroArchivo
        ParametrosLlamadaBO parametro3 = new ParametrosLlamadaBO(); //nombreArchivo
        ParametrosLlamadaBO parametro4 = new ParametrosLlamadaBO(); //idUsuario

        /*Seteo de los parametros de salida del proceso COBOL*/
        ParametrosLlamadaBO parametro5 = new ParametrosLlamadaBO(); //idLogAS400
        ParametrosLlamadaBO parametro6 = new ParametrosLlamadaBO(); //control

        parametro1.setTipo("STRING");
        parametro1.setLargo(param1.length());
        parametro1.setValor(param1);
        parametro1.setDireccion("IN");
        paramLlamada[0] = parametro1;

        parametro2.setTipo("STRING");
        parametro2.setLargo(param2.length());
        parametro2.setValor(param2);
        parametro2.setDireccion("IN");
        paramLlamada[1] = parametro2;

        parametro3.setTipo("STRING");
        parametro3.setLargo(param3.length());
        parametro3.setValor(param3);
        parametro3.setDireccion("IN");
        paramLlamada[2] = parametro3;		

        parametro4.setTipo("STRING");
        parametro4.setLargo(param4.length());
        parametro4.setValor(param4);
        parametro4.setDireccion("IN");
        paramLlamada[3] = parametro4;

        parametro5.setTipo("STRING");
        parametro5.setLargo(12);
        parametro5.setValor("000000000000");
        parametro5.setDireccion("OUT");
        paramLlamada[4] = parametro5;

        parametro6.setTipo("STRING");
        parametro6.setLargo(2);
        parametro6.setValor("00");
        parametro6.setDireccion("OUT");
        paramLlamada[5] = parametro6;

        try
        {
            ParametrosLlamadaBO[] salida = ConsumidorCobol.call(paramConexion, paramLlamada);
            idLogAS400 = (String)salida[4].getValor();
            control = (String)salida[5].getValor();

            System.out.println("idLogAS400: "+ idLogAS400);
            System.out.println("control: "+ control);

            /*caso fallido control = 02 y idLogAS400 = 0*/
            if(Integer.parseInt(control.substring(1,2))== 2)
            {
                if(idLogAS400.equals(IND_Constants.str_logAS400))
                {
                    resp.setCodResultado(95);
                }else
                {
                    resp.setCodResultado(99);
                    resp.setIdLogAS400(idLogAS400);
                }
            }else
            {
                if(Integer.parseInt(control.substring(1,2))== 3)
                {
                    resp.setCodResultado(0);
                }
            }

            return resp;

        }catch(Exception e){
            resp.setCodResultado(99);
            e.printStackTrace();
        }

        return resp;
    }

    /*Funcion que consulta por estadistica de registros procesados*/
    public static EntradaIntercajaVO estadisticaTipoArchivoprocesadoCobol(String idTipoArchivo, String idMaestroArchivo)
    {
        return EntradaIntercajaDAO.estadisticaTipoArchivoProcesadoCobol(idTipoArchivo, idMaestroArchivo);
    }

    /*Funcion que obtiene el detalle del archivo procesado*/
    public static EntradaIntercajaVO obtenerCabeceraDetalleFile(String nombreArchivo)
    {
        return EntradaIntercajaDAO.obtenerCabeceraDetalleFile(nombreArchivo);
    }

    public static EntradaIntercajaVO obtenerCabeceraDetalleFileFallido(String nombreArchivo, String statusProceso)
    {
        return EntradaIntercajaDAO.obtenerCabeceraDetalleFileFallido(nombreArchivo, statusProceso);
    }

    public static EntradaIntercajaVO obtenerCabeceraDetalleFileCobol(String nombreArchivo, String idMaestroArchivo)
    {
        return EntradaIntercajaDAO.obtenerCabeceraDetalleFileCobol(nombreArchivo, idMaestroArchivo);
    }

    public static String enviarArchivoFTP(String fileName, String source, String tipoArchivo)throws MalformedURLException, IOException
    {
        int tipoFile = Integer.parseInt(tipoArchivo);
        String ftpServer = "";
        String user = ""; 
        String password = "";
        String destino = "";

        ListadoParametros listaParam = ListadoParametros.getInstancia();
        Parametro[] paramConexionFTP = listaParam.getListParametrosConexionFTPIntercaja();

        //Se obtiene ruta del servidor. (ip).
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

            if(codigo == 6 && tipoFile == 1){
                destino = paramConexionFTP[i].getGlosa();
            }

            if(codigo == 7  && tipoFile == 2){
                destino = paramConexionFTP[i].getGlosa();
            }

            if(codigo == 8  && tipoFile == 3){
                destino = paramConexionFTP[i].getGlosa();
            }

            if(codigo == 9 && tipoFile == 4){
                destino = paramConexionFTP[i].getGlosa();
            }

            if(codigo == 10 && tipoFile == 5){
                destino = paramConexionFTP[i].getGlosa();
            }

            if(codigo == 11 && tipoFile == 6){
                destino = paramConexionFTP[i].getGlosa();
            }
        }

        UploadFileFTP.subirArchivo(ftpServer, user, password, fileName, source, destino);

        return "Ok";
    }

    public static String enviarArchivoAProcesarFTP(String nombreArchivo, String rutaArchivo)throws MalformedURLException, IOException
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

            if(codigo == 4){
                destino = paramConexionFTP[i].getGlosa();
            }
        }

        UploadFileFTP.subirArchivo(ftpServer, user, password, nombreArchivo, rutaArchivo, destino);

        return "Ok";
    }

    /*Transfiere el archivo que fue procesado, a la carpeta historica, dependiendo del archivo*/
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
            if((cod == 6) && (Integer.parseInt(tipoArchivo)) == 1){
                destino = parametro[i].getGlosa();
            }
            if((cod == 7)  && (Integer.parseInt(tipoArchivo)) == 2){
                destino = parametro[i].getGlosa();
            }
            if((cod == 8)  && (Integer.parseInt(tipoArchivo)) == 3){
                destino = parametro[i].getGlosa();
            }
            if((cod == 9) && (Integer.parseInt(tipoArchivo)) == 4){
                destino = parametro[i].getGlosa();
            }
            if((cod == 10) && (Integer.parseInt(tipoArchivo)) == 5){
                destino = parametro[i].getGlosa();
            }
            if((cod == 11) && (Integer.parseInt(tipoArchivo)) == 6){
                destino = parametro[i].getGlosa();
            }
        }

        // define un archivo origen de Windows		
        String origen = rutaArchivo;
        StringBuffer destinoAS400 = new StringBuffer();
        destinoAS400.append(destino);
        destinoAS400.append(nombreArchivo);

        try {
            SendFileToAS400 files= new SendFileToAS400(ip_conexion, user_conexion, pass_conexion);
            files.copiarArchivoToAS400(origen, destinoAS400.toString());
            files.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Ok";
    }

    /*Transfiere el archivo (puede ser cualquiera de intercaja) a la carpeta de procesamiento para ser tomado por un proceso cobol.*/
    public static String transferirFileToProccessAS400(String rutaArchivo, String nombreArchivo)
    {
        String ip_AS400 = "";
        String user_AS400 = "";
        String pass_AS400 = "";
        String destino = "";

        ListadoParametros listaParam = ListadoParametros.getInstancia();
        Parametro[] parametro = listaParam.getListParametrosConexionFTPIntercaja();

        for(int i=0; i<parametro.length; i++)
        {
            int cod = 0;
            cod = parametro[i].getCodigo();
            if(cod == 1){
                ip_AS400 = parametro[i].getGlosa();
            }
            if(cod == 2){
                user_AS400 = parametro[i].getGlosa();
            }
            if(cod == 3){
                pass_AS400 = parametro[i].getGlosa();
            }
            if(cod == 4){
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
            System.out.println("ip_AS400: "+ ip_AS400);
            System.out.println("user_AS400: "+ user_AS400);
            System.out.println("pass_AS400: "+ pass_AS400);
            System.out.println("destinoAS400: "+ destinoAS400.toString());

            SendFileToAS400 files= new SendFileToAS400(ip_AS400, user_AS400, pass_AS400);
            files.copiarArchivoToAS400(origen,destinoAS400.toString());
            files.closeConnection();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return "Ok";
    }

    public static String deleteFileServer(String rutaArchivo)
    {
        int i = Helper.deleteFile(rutaArchivo);

        if(i == 1)
        {
            return "Ok";
        }else
        {
            return "Ko";
        }
    }

    public static EntradaIntercajaVO buscarRutaArchivo()
    {
        File file = new File("");
        StringBuffer ruta = new StringBuffer();
        EntradaIntercajaVO resp = new EntradaIntercajaVO();

        ruta.append(file.getAbsolutePath());
        ruta.append(IND_Constants.DIR_UPLFILE);

        File fileTemp = new File(ruta.toString());

        File[] rutaArchivo = fileTemp.listFiles();

        for(int i=0; i<rutaArchivo.length; i++)
        {
            File temp = rutaArchivo[i];

            if(temp != null){
                resp.setCodResultado(0);
                resp.setRutaArchivo(temp.toString());
            }
            else{
                resp.setCodResultado(99);
            }
        }

        return resp;
    }

    public static EntradaIntercajaVO obtenerRangoIntercaja()
    {
        EntradaIntercajaVO entradaVo = new EntradaIntercajaVO();
        ListadoParametros listaParam = ListadoParametros.getInstancia();
        Parametro[] rangoIntercaja = listaParam.getListRangoIntercaja();

        for(int i=0; i<rangoIntercaja.length; i++)
        {
            int codigo = 0;
            codigo = rangoIntercaja[i].getCodigo();

            if(codigo == 1)
            {
                entradaVo.setRangoUno(rangoIntercaja[i].getGlosa());
                entradaVo.setCodResultado(0);
            }
            if(codigo == 2)
            {
                entradaVo.setRangoDos(rangoIntercaja[i].getGlosa());
                entradaVo.setCodResultado(0);
            }
            if(codigo == 3)
            {
                entradaVo.setRangoTres(rangoIntercaja[i].getGlosa());
                entradaVo.setCodResultado(0);
            }
        }

        return entradaVo;
    }

    /*Funcion que lee un archivo en particular, para saber si el archivo es de estadística.*/
    public static boolean verificarSiEsArchivoEstadisticas(String rutaArchivo) throws IOException
    {
        FileReader fr = new FileReader(rutaArchivo);
        BufferedReader bf = new BufferedReader(fr);
        String sCadena;

        String cad = "";
        int i = 0;
        while(i <= 0){

            sCadena = bf.readLine();

            if(i==0){
                cad = sCadena;
            }	
            i++;
        }
        bf.close();

        for(int j=0; j<cad.length(); j++){
            if( ! Character.isWhitespace( cad.charAt(j) ) ){

                return true;
            }
        }

        return false;
    }

    /*Funcion que retorna el encabezado para un archivo de estadistica*/
    public static String retornaCabeceraArchivoEstadistica(String rutaArchivo)throws IOException
    {
        FileReader fr = new FileReader(rutaArchivo);
        BufferedReader bf = new BufferedReader(fr);
        String sCadena;

        String cad = "";
        int i = 0;

        while(i <= 2)
        {
            sCadena = bf.readLine();

            if(i==1)
            {
                cad = sCadena;
            }	
            i++;
        }
        bf.close();
        return cad;
    }

    public static EntradaIntercajaVO consultarStatusProceso(String nombreArchivo, String idTipoArchivo)
    {
        return EntradaIntercajaDAO.consultarStatusProceso(nombreArchivo, idTipoArchivo);
    }

    public static EntradaIntercajaVO borrarArchivos()
    {
        EntradaIntercajaVO resp = new EntradaIntercajaVO();

        int respuesta;
        respuesta = Helper.deleteFilesIntercaja();

        if(respuesta == 1)
        {
            resp.setCodResultado(1);
        }else
        {
            resp.setCodResultado(0);
        }
        return resp;
    }

    public static EntradaIntercajaVO obtenerPrimerYUltimoDiaMes(String fechaSistema)
    {
        EntradaIntercajaVO resp = new EntradaIntercajaVO();

        resp.setFechaConPimerDiaMes(Helper.obtenerPrimerDiaMes(fechaSistema));
        resp.setFechaConUltimoDiaMes(Helper.obtenerUltimoDiaMes(fechaSistema));

        return resp;
    }
}
