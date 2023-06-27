package cl.araucana.independientes.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;

import com.ibatis.sqlmap.client.SqlMapClient;
import cl.araucana.independientes.config.ConfiguracionSqlMap;
import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.helper.IND_Constants;
import cl.araucana.independientes.struts.Forms.GenArchSalidaIntForm;
import cl.araucana.independientes.vo.DetalleSalidaIntercajaVO;
import cl.araucana.independientes.vo.FiltroDetalleSalidaIntercajaVO;
import cl.araucana.independientes.vo.LinSalidaIntercajaVO;
import cl.araucana.independientes.vo.MaestroIntercajaVO;
import cl.araucana.independientes.vo.RespuestaVO;
import cl.araucana.independientes.vo.SalidaIntercajaVO;
import cl.araucana.independientes.vo.SesionDirectorioVO;

public class SalidaIntercajaDAO 
{
    public static LinSalidaIntercajaVO[] consultaSalidaAfiliadosIntercaja(String fechaInicio, String fechaFin, String user){

        /*Declaracion de variables SQLMap.*/
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        List datos = null;

        //String fecha = "";
        Date date = new Date();

        String newFechaSolicitud = "";
        //TODO SToro se cambio el substring pro el formateo correcto
        //String fechaFormatSol = "";
        String codigoMovimiento="";
        //contadores
        int countAfiliacionesNuevas = 0;
        int countDesafiliados = 0;
        int countCambioCCAF = 0;
        int countFallecidos = 0;

        String newFechaCotizacion = "";
        //String fechaFormatCot = "";
        String DATE_FORMAT1 = "MM/dd/yyyy";
        String DATE_FORMAT2 = "ddMMyyyy"; // El que se envia a la Vista

        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        LinSalidaIntercajaVO[] salida = new LinSalidaIntercajaVO[0];
        LinSalidaIntercajaVO[] result = null;
        GenArchSalidaIntForm form = new GenArchSalidaIntForm();

        form.setTxt_FecInicio(fechaInicio);
        form.setTxt_FecCorte(fechaFin);

        try
        {
            sqlMap.startTransaction(0);

            //preparación de parámetros de entrada.
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", form);

            //Aqui realizar la consulta
            datos = sqlMap.queryForList("intercajaNS.generarArchivoSalida",parametros);
            
            if(datos != null && datos.size()>0)
            {
                result = (LinSalidaIntercajaVO[])datos.toArray(new LinSalidaIntercajaVO[datos.size()]);

                for(int i = 0; i < result.length ; i++)
                {
                    LinSalidaIntercajaVO lineaTemp = new LinSalidaIntercajaVO();
                    lineaTemp =	result[i];

                    //Formateo de la fecha de ingreso.
                    lineaTemp.setCcafProcedencia(302);
                    lineaTemp.setCeros(IND_Constants.str_ceros);
                    lineaTemp.setBlancoUno(IND_Constants.str_blancos);
                    lineaTemp.setCodigoInternoUno(IND_Constants.str_cod_int_1);
                    lineaTemp.setBlancoDos(IND_Constants.str_blancos);

                    //fecha solicitud de afiliacion.
                    String fechaTemp1 = lineaTemp.getFechaSolicitudAfiliacion();
                    date = sdf1.parse(fechaTemp1);
                    newFechaSolicitud = sdf2.format(date);
                    //fechaFormatSol = newFechaSolicitud.substring(0, 2) + newFechaSolicitud.substring(3, 5) + newFechaSolicitud.substring(6, 10);
                    lineaTemp.setFechaSolicitudAfiliacion(newFechaSolicitud); //Era fechaFormatSol

                    //fecha de ultima cotizacion.
                    String fechaTemp2 = lineaTemp.getFechaUltCotizacion();
                    date = sdf1.parse(fechaTemp2);
                    newFechaCotizacion = sdf2.format(date);
                    //fechaFormatCot = newFechaCotizacion.substring(0, 2) + newFechaCotizacion.substring(3, 5) + newFechaCotizacion.substring(6, 10);
                    lineaTemp.setFechaUltCotizacion(newFechaCotizacion); //Era fechaFormatCot

                    //Aqui manejo de codigo de movimiento de intercaja.
                    String tipoEstadoAfiliado = lineaTemp.getTipoEstadoAfiliado();
                    String tipoEstadoSolicitud = lineaTemp.getTipoEstadoSolicitud();
                    String tipoCajaOrigen = Long.toString(lineaTemp.getTipoCajaOrigen());
                    String tipoSolicitud = lineaTemp.getTipoSolicitud();
                    long tipoCajaOr = lineaTemp.getTipoCajaOrigen();


                    if(tipoEstadoAfiliado.equals("1") && 
                            (tipoEstadoSolicitud.equals("2") || tipoEstadoSolicitud.equals("4")) && 
                            tipoCajaOrigen.equals("1") && tipoSolicitud.equals("1"))
                    {
                        codigoMovimiento = "1";
                        lineaTemp.setCodigoMovimiento(codigoMovimiento);
                        countAfiliacionesNuevas +=1;
                    }else
                    {
                        if(tipoEstadoAfiliado.equals("1") && (tipoEstadoSolicitud.equals("2") || tipoEstadoSolicitud.equals("4")) && 
                                tipoCajaOr != 1 && tipoSolicitud.equals("1"))
                        {
                            codigoMovimiento = "3";
                            lineaTemp.setCodigoMovimiento(codigoMovimiento);
                            countCambioCCAF += 1;
                        }else
                        {
                            if(tipoEstadoAfiliado.equals("6") && tipoEstadoSolicitud.equals("7"))
                            {
                                codigoMovimiento = "4";
                                lineaTemp.setCodigoMovimiento(codigoMovimiento);
                                countFallecidos +=1;
                            }else{
                                codigoMovimiento = "2";
                                lineaTemp.setCodigoMovimiento(codigoMovimiento);
                                countDesafiliados +=1;
                            }
                        }
                    }

                    lineaTemp.setCountAfiliacionesNuevas(countAfiliacionesNuevas);
                    lineaTemp.setCountCambioCCAF(countCambioCCAF);
                    lineaTemp.setCountDesafiliados(countDesafiliados);
                    lineaTemp.setCountFallecidos(countFallecidos);

                    result[i] = lineaTemp;
                }
            }else{
                System.out.println("ERROR EN SALIDAINTERCAJADAO -- FUNCION consultaSalidaAfiliadosIntercaja()");

                return salida;	
            }

            return result;

        }catch(SQLException e){
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        finally {
            try { sqlMap.endTransaction(); 
            }catch (SQLException e) {				
                e.printStackTrace(); }	 
        }		
        return result;
    }
    
//    public static List getDummy()
//    {
//    	List listaDummy = new ArrayList();
//    	
//    	LinSalidaIntercajaVO sal = new LinSalidaIntercajaVO("14143159","5","12/13/2013", "GLORIA MADELINE", "ROJAS", "FLORES", 15240, 
//    		"12/12/2013", "1", "2", "26001", "1", "2671", "2671", 1);
//    	listaDummy.add(sal);
//    	
//    	LinSalidaIntercajaVO sal1 = new LinSalidaIntercajaVO("10315047", "7", "12/30/2013", "ROBERTO ALEJANDRO",  "PACHECO", "ESPINOZA", 210000, 
//    			"11/26/2013", "1", "2", "14953", "1", "2673", "2673", 1);
//        	listaDummy.add(sal1);
//       
//        LinSalidaIntercajaVO sal2 = new LinSalidaIntercajaVO("6917604", "6", "12/09/2013", "LUIS ORLANDO", "LOAIZA", "CARDENAS", 193000, 
//        		"10/31/2013", "1", "2", "27252",  "1", "2670", "2670", 1);
//            listaDummy.add(sal2);
//        
//        LinSalidaIntercajaVO sal3 = new LinSalidaIntercajaVO("6858032", "3", "12/30/2013", "ISABEL DEL CARMEN", "ARAVENA", "JORQUERA", 26670, 
//        		"11/29/2013", "1", "2", "27153", "1", "2672", "2672", 1);
//            listaDummy.add(sal3);
//            
//    	return listaDummy;
//    }

    public static SalidaIntercajaVO insertaRegistroLogIntercaja(String nombreArchivo, 
            String numeroSesion, 
            String fechaSesion, 
            String fechaInicio, 
            String fechaCorte, 
            String fechaProceso, 
            String rutAnalista,
            String afiliadosNuevos,
            String desafiliados,
            String cambioCCAF,
            String fallecidos,
            String desafiliadosTipoUno,
            String desafiliadosTipoDos){

        /*
         * codigos de retorno:
         * 0: insert realizado con éxito.
         * 99: error de base de datos.
         */

        List datos = null;
        String fecha = "";
        long idSecuenciaSesion;
        long idMaestroArchivo;
        long idDetalleSalida;

        Date dateSesion = new Date();
        Date dateIni = new Date();
        Date dateFin = new Date();
        Date dateProc = new Date();

        SalidaIntercajaVO resp = new SalidaIntercajaVO();
        SesionDirectorioVO sessionDirectorio = new SesionDirectorioVO();
        MaestroIntercajaVO masterIntercaja = new MaestroIntercajaVO();
        DetalleSalidaIntercajaVO detalleSalida = new DetalleSalidaIntercajaVO();

        String DATE_FORMAT2 = "dd/MM/yyyy"; // El que se envia a la Vista

        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try
        {
            sqlMap.startTransaction(0);

            //parseo de las fechas
            fecha = fechaSesion;
            dateSesion = sdf2.parse(fecha);

            fecha = fechaInicio;
            dateIni = sdf2.parse(fecha);

            fecha = fechaCorte;
            dateFin = sdf2.parse(fecha);

            fecha = fechaProceso;
            dateProc = sdf2.parse(fecha);

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            datos = sqlMap.queryForList("intercajaNS.selectIdSesionDirectorio",parametros);

            if(datos != null && datos.size() > 0)
            {
                idSecuenciaSesion = Long.parseLong((String)datos.get(0));

                //seteo de variables 
                sessionDirectorio.setIdSesionDirectorio(idSecuenciaSesion);
                sessionDirectorio.setNumeroSesion(Long.parseLong(numeroSesion));
                sessionDirectorio.setFechaSesionDate(dateSesion);
                sessionDirectorio.setFechaInicioMovDate(dateIni);
                sessionDirectorio.setFechaCorteMovDate(dateFin);

            }else{
                resp.setResultado("Error al traer idSecuenciaSesion.");
                resp.setCodResultado(99);
                return resp;
            }

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", sessionDirectorio);
            sqlMap.insert("intercajaNS.insertSesionDirectorio", parametros);

            /*Aqui insert para tabla maestro intercaja*/
            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            datos = sqlMap.queryForList("intercajaNS.selectIdMaestroIntercaja",parametros);

            if(datos != null && datos.size() > 0)
            {
                idMaestroArchivo = Long.parseLong((String)datos.get(0));

                //seteo de variables
                masterIntercaja.setIdMaestroArchivo(idMaestroArchivo);
                masterIntercaja.setNombreArchivo(nombreArchivo);
                masterIntercaja.setStatusProceso(3);
                masterIntercaja.setFechaProcesoDate(dateProc);
                masterIntercaja.setIdTipoArchivo(7);
                masterIntercaja.setIdAnalista(Long.parseLong(rutAnalista));
                /*Agregar statusSendMail, para saber si correo fue enviado.*/
                masterIntercaja.setStatusSendMail(1);
            }else{
                resp.setResultado("Error al traer idMaestroArchivo.");
                resp.setCodResultado(99);
                return resp;
            }

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", masterIntercaja);
            sqlMap.insert("intercajaNS.insertMaestroIntercaja", parametros);

            //Aqui insert para tabla DetalleSalidaIntercaja.
            for(int i=1; i<=5; i++)
            {
                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                datos = sqlMap.queryForList("intercajaNS.selectIdDetalleSalidaIntercaja",parametros);

                if(datos != null && datos.size() > 0)
                {
                    idDetalleSalida = Long.parseLong((String)datos.get(0));

                    //seteo de variables
                    detalleSalida.setIdDetalleSalida(idDetalleSalida);
                    detalleSalida.setIdMaestroArchivo(idMaestroArchivo);
                    detalleSalida.setIdSesionDirectorio(idSecuenciaSesion);

                    if(i == 1)
                    {	
                        detalleSalida.setCantMovimientos(Integer.parseInt(afiliadosNuevos));
                        detalleSalida.setIdTipoMovIntercaja(i);
                        detalleSalida.setIdTipoMovIndependientes((long)i);
                    }	
                    if(i == 2){
                        detalleSalida.setCantMovimientos(Integer.parseInt(desafiliadosTipoDos));
                        detalleSalida.setIdTipoMovIntercaja(2);
                        detalleSalida.setIdTipoMovIndependientes((long)i);
                    }
                    if(i == 3)
                    {
                        detalleSalida.setCantMovimientos(Integer.parseInt(desafiliadosTipoUno));
                        detalleSalida.setIdTipoMovIntercaja(2);
                        detalleSalida.setIdTipoMovIndependientes((long)i);
                    }
                    if(i == 4)
                    {
                        detalleSalida.setCantMovimientos(Integer.parseInt(cambioCCAF));
                        detalleSalida.setIdTipoMovIntercaja(3);
                        detalleSalida.setIdTipoMovIndependientes((long)i);
                    }
                    if(i == 5){
                        detalleSalida.setCantMovimientos(Integer.parseInt(fallecidos));
                        detalleSalida.setIdTipoMovIntercaja(4);
                        detalleSalida.setIdTipoMovIndependientes((long)i);
                    }					

                }else{
                    resp.setResultado("Error al traer idDetalleSalida");
                    resp.setCodResultado(99);
                    return resp;
                }
                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", detalleSalida);
                sqlMap.insert("intercajaNS.insertDetalleSalidaIntercaja", parametros);
            }

        }catch(SQLException e) {
            resp.setResultado("Error al insertar en la tabla sesionDirectorio.");
            resp.setCodResultado(99);
            e.printStackTrace();
        }
        catch (ParseException e) {
            resp.setResultado("Error al insertar en la tabla sesionDirectorio.");
            resp.setCodResultado(99);
            e.printStackTrace();
        }
        finally {
            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }
        }

        return resp;
    }

    public static SalidaIntercajaVO verificaNumeroSesion(String numeroSesion)
    {
        /*
         * Códigos de retorno, para verificar que no exista el número de sesión en la tabla sesiondirectorio.
         * 0: no existe el numero de sesión.
         * 99: existe el numero de sesión.
         */

        List datos = null;
        long numberSesion;
        SalidaIntercajaVO resp = new SalidaIntercajaVO();
        GenArchSalidaIntForm form = new GenArchSalidaIntForm();

        form.setTxt_NumSesion(numeroSesion);
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try
        {
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", numeroSesion);
            datos = sqlMap.queryForList("intercajaNS.selectCountNumSesion",parametros);

            if(datos != null && datos.size() > 0)
            {
                numberSesion = Long.parseLong((String)datos.get(0));

                if(numberSesion == 0){
                    resp.setCodResultado(0);
                }else{
                    resp.setCodResultado(99);
                }
            }

            return resp;

        }catch(SQLException e){
            resp.setResultado("Error, el numero de sesión ya existe en base de datos.");
            resp.setCodResultado(99);
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return resp;
    }

    public static SalidaIntercajaVO updateStatusSendMail(String nombreArchivo)
    {
        List datos = null;
        long idMaestroArchivo;
        MaestroIntercajaVO masterVO = new MaestroIntercajaVO();
        SalidaIntercajaVO resp = new SalidaIntercajaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        /*seteo de variable de entrada a la consulta.*/
        masterVO.setNombreArchivo(nombreArchivo);

        try
        {
            sqlMap.startTransaction(0);

            /*Primero consultar por idmaestroarchivo dado el nombre del archivo enviado por mail.*/
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", masterVO);

            datos = sqlMap.queryForList("intercajaNS.selectIdMaestroArchivoMail",parametros);

            if(datos != null && datos.size() > 0)
            {
                idMaestroArchivo = Long.parseLong((String)datos.get(0));

                masterVO = new MaestroIntercajaVO();
                masterVO.setIdMaestroArchivo(idMaestroArchivo);

                /*Se realiza el update luego de obtener el idMaestroArchivo*/
                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", masterVO);

                sqlMap.update("intercajaNS.upateMailMaestroInterc",parametros);

                resp.setCodResultado(0);

            }else{
                resp.setCodResultado(99);
                System.out.println("Error al consultar por IDMAESTROARCHIVO en funcion updateStatusSendMail() -- SALIDAINTERCAJADAO");
            }

            sqlMap.commitTransaction();
            return resp;

        }catch(SQLException e){
            resp.setCodResultado(99);
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return resp;
    }

    public static RespuestaVO verificarFlagCorreo(String nombreArchivoEnviado)
    {
        int flagCorreo;
        long idMaestroArchivo;		
        List datoIdMaestroArchivo = null;
        List datoFlagCorreo = null;
        RespuestaVO resp = new RespuestaVO();
        MaestroIntercajaVO masterVO = new MaestroIntercajaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        masterVO.setNombreArchivo(nombreArchivoEnviado);

        try
        {
            sqlMap.startTransaction(0);

            /*Primero consultar por idmaestroarchivo dado el nombre del archivo enviado por mail.*/
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", masterVO);

            datoIdMaestroArchivo = sqlMap.queryForList("intercajaNS.selectIdMaestroArchivoMail",parametros);

            if(datoIdMaestroArchivo != null && datoIdMaestroArchivo.size() > 0)
            {
                idMaestroArchivo = Long.parseLong((String)datoIdMaestroArchivo.get(0));
                masterVO.setIdMaestroArchivo(idMaestroArchivo);
            }else{
                resp.setCodRespuesta(99);
            }

            /*con el idMaestroArchivo (unico) se consulta por el flag de correo.*/
            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", masterVO);

            datoFlagCorreo = sqlMap.queryForList("intercajaNS.consultarFlagCorreo", parametros);

            if(datoFlagCorreo != null && datoFlagCorreo.size() > 0)
            {
                flagCorreo = Integer.parseInt((String)datoFlagCorreo.get(0));

                if(flagCorreo == 1)
                {
                    resp.setCodRespuesta(1);
                }else{
                    resp.setCodRespuesta(0);
                }
            }

            return resp;

        }catch(SQLException e){
            resp.setCodRespuesta(99);
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return resp;
    }

    //Inicio REQ-7173 JLGN 16-05-2013 Inserta en Tabla FILTRODETALLESALIDAINTERCAJA 
    public static SalidaIntercajaVO insertaRegistroFiltroSalida(long idDocumento,
            String digVerificador){

        long idFiltroDetalleSal;
        List datos = null;
        SalidaIntercajaVO resp = new SalidaIntercajaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        FiltroDetalleSalidaIntercajaVO detalleSalidaIntercajaVO = new FiltroDetalleSalidaIntercajaVO();

        try
        {
            sqlMap.startTransaction(0);
            resp.setCodResultado(0);

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            datos = sqlMap.queryForList("intercajaNS.selectIdFiltroSalInt",parametros);

            if(datos != null && datos.size() > 0)
            {
                idFiltroDetalleSal = Long.parseLong((String)datos.get(0));
                detalleSalidaIntercajaVO.setIdFiltroDetalle(idFiltroDetalleSal);
                detalleSalidaIntercajaVO.setIdDocumento(idDocumento);
                detalleSalidaIntercajaVO.setDigVerificador(digVerificador);

            }else{
                resp.setResultado("Error al traer idFiltroDetalle.");
                resp.setCodResultado(99);
                return resp;
            }

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", detalleSalidaIntercajaVO);
            sqlMap.insert("intercajaNS.insertFiltroDetalleSalidaIntercaja", parametros);

        }catch(SQLException e) {
            resp.setResultado("Error al insertar en la tabla sesionDirectorio.");
            resp.setCodResultado(99);
            e.printStackTrace();
        }	
        finally {
            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }
        }
        return resp;
    }
    //Fin REQ-7173 JLGN 16-05-2013 Inserta en Tabla FILTRODETALLESALIDAINTERCAJA

    
}
