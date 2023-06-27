package cl.araucana.independientes.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import com.ibatis.sqlmap.client.SqlMapClient;
import cl.araucana.independientes.config.ConfiguracionSqlMap;
import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.helper.IND_Constants;
import cl.araucana.independientes.vo.EntradaIntercajaVO;
import cl.araucana.independientes.vo.LinEntradaIntercajaVO;
import cl.araucana.independientes.vo.MaestroIntercajaVO;
import cl.araucana.independientes.vo.param.ListadoParametros;
import cl.araucana.independientes.vo.param.Parametro;

public class EntradaIntercajaDAO {

    public static EntradaIntercajaVO insertarMaestroIntercaja(String nombreArchivo, String fechaCabecera, String fechaProceso, String tipoArchivo, String rutAnalista){

        /*
         * Codigos de retorno:
         * 0: si la inserción fue realizada de forma exitosa.
         * 99: si ocurre un error en la inserción de los datos.
         * */

        List datos = null;		
        String fecha = "";
        long idMaestroArchivo;
        String DATE_FORMAT2 = "dd/MM/yyyy";
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);
        StringBuffer nameFile = new StringBuffer();
        String archivo = "";
        String extension = IND_Constants.EXT_texto;

        Date dateCabecera = new Date();
        Date dateProceso = new Date();

        EntradaIntercajaVO resp = new EntradaIntercajaVO();
        MaestroIntercajaVO maestro = new MaestroIntercajaVO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try
        {
            sqlMap.startTransaction(0);

            fecha = fechaCabecera;
            dateCabecera = sdf2.parse(fecha);

            fecha = fechaProceso;
            dateProceso = sdf2.parse(fecha);

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            datos = sqlMap.queryForList("intercajaNS.selectIdMaestroIntercaja",parametros);


            if(datos != null && datos.size() > 0)
            {
                idMaestroArchivo = Long.parseLong((String)datos.get(0));

                //nameFile.append(Helper.paddingString(Long.toString(idMaestroArchivo), 4, '0', true));
                nameFile.append(nombreArchivo);
                nameFile.append(extension);
                archivo = nameFile.toString();

                //seteo de variables
                maestro.setIdMaestroArchivo(idMaestroArchivo);
                maestro.setNombreArchivo(archivo);
                maestro.setFechaCabeceraDate(dateCabecera);
                maestro.setStatusProceso(1);
                maestro.setFechaProcesoDate(dateProceso);
                maestro.setIdTipoArchivo(Integer.parseInt(tipoArchivo));
                maestro.setIdAnalista(Long.parseLong(rutAnalista));
                maestro.setStatusSendMail(1);
            }else{
                resp.setResultado("Error al traer idMaestroArchivo.");
                resp.setCodResultado(99);
                return resp;
            }

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", maestro);
            sqlMap.insert("intercajaNS.insertEntradaMaestroIntercaja", parametros);
            resp.setCodResultado(0);
            resp.setNombreArchivo(archivo);

        }catch(SQLException e){

            resp.setResultado("Error al insertar en la tabla MaestroIntercaja.");
            resp.setCodResultado(99);
            e.printStackTrace();

        }
        catch (ParseException e) {

            resp.setResultado("Error al insertar en la tabla MaestroIntercaja.");
            resp.setCodResultado(99);
            e.printStackTrace();

        }
        finally {
            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }
        }

        return resp;
    }

    /*Funcion que obtiene los datos para la grilla.*/
    public static LinEntradaIntercajaVO[] obtenerDataArchivosProcesados(String fechaInicio, String fechaFin){

        /*CODIGOS DE RETORNO
         * 0 : SI LA CONSULTA FUE REALIZADA DE FORMA EXITOSA
         * 99: SI OCURRIO UN ERROR DURANTE LA CONSULTA.
         * */
        List datos = null;
        String fechaCarga = "";


        String DATE_FORMAT1 = "MM/dd/yyyy";
        String DATE_FORMAT2 = "dd/MM/yyyy";
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        Date date = new Date();

        /*Declaracion de variables SQLMap.*/
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        LinEntradaIntercajaVO[] entradaIntercajaCero = new LinEntradaIntercajaVO[0];
        LinEntradaIntercajaVO[] entradaIntercaja = null;
        EntradaIntercajaVO entradaVO = new EntradaIntercajaVO();

        entradaVO.setFechaInicio(fechaInicio);
        entradaVO.setFechaFin(fechaFin);

        try
        {
            sqlMap.startTransaction(0);

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", entradaVO);

            datos = sqlMap.queryForList("intercajaNS.selectDataEntrIntercaja",parametros);

            if(datos != null && datos.size() > 0)
            {
                entradaIntercaja = (LinEntradaIntercajaVO[])datos.toArray(new LinEntradaIntercajaVO[datos.size()]);

                for(int i = 0; i < entradaIntercaja.length; i++)
                {
                    int fileType = 0;
                    int processStatus = 0;

                    String tipoArchivoGlosa = "";
                    String statusProcesoGlosa = "";

                    ListadoParametros ListaParam = ListadoParametros.getInstancia();
                    Parametro[] paramStatus = ListaParam.getListStatusProceso();
                    Parametro[] paramTipoArchivo = ListaParam.getListTipoArchivo();

                    LinEntradaIntercajaVO entradaIntercajaTemp = new LinEntradaIntercajaVO();
                    entradaIntercajaTemp = entradaIntercaja[i];

                    fileType = entradaIntercajaTemp.getTipoArchivo();
                    processStatus = entradaIntercajaTemp.getStatusProceso();

                    tipoArchivoGlosa = Helper.obtenerDescripcion(paramTipoArchivo, fileType);
                    entradaIntercajaTemp.setTipoArchivoGlosa(tipoArchivoGlosa);

                    statusProcesoGlosa = Helper.obtenerDescripcion(paramStatus, processStatus);
                    entradaIntercajaTemp.setStatusProcesoGlosa(statusProcesoGlosa);

                    /*Seteo de la fecha de carga*/
                    String fechaTemp1 = entradaIntercajaTemp.getFechaCarga();
                    date = sdf1.parse(fechaTemp1);
                    fechaCarga = sdf2.format(date);
                    entradaIntercajaTemp.setFechaCarga(fechaCarga);

                    entradaIntercaja[i] = entradaIntercajaTemp;
                }

            }else{
                System.out.println("EntradaIntercajaDAO - Ocurrió un error al consultar por data en maestroIntercaja - funcion obtenerDataArchivosProcesados().");
                return entradaIntercajaCero;
            }

            return entradaIntercaja;

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

        return entradaIntercaja;
    }

    /*funcion que verifica si un registro fue insertado en la tabla maestro intercaja*/
    public static EntradaIntercajaVO validaRegistroMaestroIntercaja(String nombreArchivo, String tipoArchivo)
    {
        /*codigos de retorno.
         * 0: si no existe el registro consultado.
         * -1: si existe el registro consultado.
         * 99: si ocurrio un error en la consulta.
         * */

        List datos = null;
        String extension = ".txt";
        //este objeto es el que se envia de entrada para la consulta.
        EntradaIntercajaVO entrada = new EntradaIntercajaVO();

        /*Declaracion de variables SQLMap.*/
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        //este objeto es el que se devuelve con la respuesta 
        EntradaIntercajaVO resp = new EntradaIntercajaVO();

        try
        {
            sqlMap.startTransaction(0);

            StringBuffer nameFile = new StringBuffer();
            nameFile.append(nombreArchivo);
            nameFile.append(extension);
            entrada.setNombreArchivo(nameFile.toString());
            entrada.setTipoArchivo(Integer.parseInt(tipoArchivo));

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", entrada);

            datos = sqlMap.queryForList("intercajaNS.selectCountRegistro",parametros);

            if(datos != null && datos.size() > 0)
            {
                int respuesta = Integer.parseInt((String)datos.get(0));

                if(respuesta == 0){
                    resp.setCodResultado(0);
                }else{
                    resp.setCodResultado(-1);
                }
            }

            return resp;

        }catch(SQLException e){
            resp.setCodResultado(99);
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace(); }
        }

        return resp;
    }

    /*Funcion que consulta por estadistica de registros procesados*/
    public static EntradaIntercajaVO estadisticaTipoArchivoProcesadoCobol(String idTipoArchivo, String idMaestroArchivo)
    {
        /* Codigos de retorno:
         * 0: si la consulta se ha realizado de forma exitosa.
         * 99: si ha ocurrido un error.
         */

        int totalRegProcesados = 0;
        int totalRegAplNegocio = 0;
        int totalRegPendientes = 0;
        List datos = null;

        EntradaIntercajaVO resp = new EntradaIntercajaVO();
        EntradaIntercajaVO entrada = new EntradaIntercajaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        HashMap parametros = new HashMap();

        entrada.setIdMaestroArchivo(Long.parseLong(idMaestroArchivo));

        try
        {
            switch(Integer.parseInt(idTipoArchivo))
            {
            /*CASE 1.- TIPO DE ARCHIVO: TRASPASOS.*/
            case 1:
                sqlMap.startTransaction(0);
                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", entrada);

                datos = sqlMap.queryForList("intercajaNS.selectCountRegProcesadosTra",parametros);

                if(datos != null && datos.size() > 0)
                {
                    totalRegProcesados = Integer.parseInt((String)datos.get(0));
                    resp.setTotalRegProcesados(totalRegProcesados);
                }

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", entrada);

                datos = sqlMap.queryForList("intercajaNS.SelectCountAplNegocioRecTra",parametros);

                if(datos != null && datos.size() > 0)
                {
                    totalRegAplNegocio = Integer.parseInt((String)datos.get(0));
                    resp.setTotalRegAplNegocio(totalRegAplNegocio);
                }

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", entrada);

                datos = sqlMap.queryForList("intercajaNS.SelectCountRegPendientesRecTra",parametros);

                if(datos != null && datos.size() > 0){

                    totalRegPendientes = Integer.parseInt((String)datos.get(0));
                    resp.setTotalRegPendientes(totalRegPendientes);
                }
                break;

                /*CASE 2.- TIPO DE ARCHIVO: COLISIONES*/		
            case 2:	
                sqlMap.startTransaction(0);
                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", entrada);

                datos = sqlMap.queryForList("intercajaNS.selectCountRegProcesadosCol",parametros);

                if(datos != null && datos.size() > 0){

                    totalRegProcesados = Integer.parseInt((String)datos.get(0));
                    resp.setTotalRegProcesados(totalRegProcesados);
                }

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", entrada);

                datos = sqlMap.queryForList("intercajaNS.SelectCountAplNegocioRecCol",parametros);

                if(datos != null && datos.size() > 0){

                    totalRegAplNegocio = Integer.parseInt((String)datos.get(0));
                    resp.setTotalRegAplNegocio(totalRegAplNegocio);
                }

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", entrada);

                datos = sqlMap.queryForList("intercajaNS.SelectCountRegPendientesRecCol",parametros);

                if(datos != null && datos.size() > 0){

                    totalRegPendientes = Integer.parseInt((String)datos.get(0));
                    resp.setTotalRegPendientes(totalRegPendientes);
                }
                break;

                /*CASE 3 .- TIPO DE ARCHIVO: RECIBIDOS*/		
            case 3:
                sqlMap.startTransaction(0);
                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", entrada);

                datos = sqlMap.queryForList("intercajaNS.selectCountRegProcesadosRec",parametros);

                if(datos != null && datos.size() > 0){

                    totalRegProcesados = Integer.parseInt((String)datos.get(0));
                    resp.setTotalRegProcesados(totalRegProcesados);
                }

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", entrada);

                datos = sqlMap.queryForList("intercajaNS.SelectCountAplNegocioRec",parametros);

                if(datos != null && datos.size() > 0)
                {
                    totalRegAplNegocio = Integer.parseInt((String)datos.get(0));
                    resp.setTotalRegAplNegocio(totalRegAplNegocio);
                }

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", entrada);

                datos = sqlMap.queryForList("intercajaNS.SelectCountRegPendientesRec",parametros);

                if(datos != null && datos.size() > 0){

                    totalRegPendientes = Integer.parseInt((String)datos.get(0));
                    resp.setTotalRegPendientes(totalRegPendientes);
                }
                break;

                /*CASE 4.- TIPO DE ARCHIVO: ESTADISTICAS.*/
            case 4:
                sqlMap.startTransaction(0);
                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", entrada);

                datos = sqlMap.queryForList("intercajaNS.selectCountRegProcesadosEst",parametros);

                if(datos != null && datos.size() > 0){

                    totalRegProcesados = Integer.parseInt((String)datos.get(0));
                    resp.setTotalRegProcesados(totalRegProcesados);

                }else{

                    resp.setTotalRegProcesados(0);
                }
                break;

                /*CASE 5.- TIPO DE ARCHIVO: ERRORES.*/		
            case 5:
                sqlMap.startTransaction(0);
                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", entrada);

                datos = sqlMap.queryForList("intercajaNS.selectCountRegProcesadosErr",parametros);

                if(datos != null && datos.size() > 0){

                    totalRegProcesados = Integer.parseInt((String)datos.get(0));
                    resp.setTotalRegProcesados(totalRegProcesados);
                }

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", entrada);

                datos = sqlMap.queryForList("intercajaNS.SelectCountAplNegocioErr",parametros);

                if(datos != null && datos.size() > 0){

                    totalRegAplNegocio = Integer.parseInt((String)datos.get(0));
                    resp.setTotalRegAplNegocio(totalRegAplNegocio);
                }

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", entrada);

                datos = sqlMap.queryForList("intercajaNS.SelectCountRegPendientesErr",parametros);

                if(datos != null && datos.size() > 0){

                    totalRegPendientes = Integer.parseInt((String)datos.get(0));
                    resp.setTotalRegPendientes(totalRegPendientes);
                }
                break;

                /*CASE 6.- TIPO DE ARCHIVO: BASE COMUN AFILIADOS*/
            case 6:
                sqlMap.startTransaction(0);
                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", entrada);

                datos = sqlMap.queryForList("intercajaNS.selectCountRegProcesadosBca",parametros);

                if(datos != null && datos.size() > 0){

                    totalRegProcesados = Integer.parseInt((String)datos.get(0));
                    resp.setTotalRegProcesados(totalRegProcesados);

                }else{

                    resp.setTotalRegProcesados(0);
                }

                break;						
            default:
                System.out.println("Ha ocurrido un error en estadisticaTipoArchivoProcesadoCobol -- EntradaIntercajaDAO");
            }


            return resp;

        }catch(SQLException e){
            resp.setCodResultado(99);
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace(); }

        }

        return resp;
    }

    /*Funcion que obtiene el detalle del archivo procesado*/
    public static EntradaIntercajaVO obtenerCabeceraDetalleFile(String nombreArchivo)
    {
        /* Codigos de retorno:
         * 0: si la consulta se ha realizado de forma exitosa.
         * 99: si ha ocurrido un error.
         */

        List datos = null;
        EntradaIntercajaVO resp = new EntradaIntercajaVO();
        EntradaIntercajaVO entrada = new EntradaIntercajaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        entrada.setNombreArchivo(nombreArchivo);

        try
        {
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", entrada);

            datos = sqlMap.queryForList("intercajaNS.selectDetalleArchivo",parametros);

            if(datos != null && datos.size() > 0)
            {
                resp = (EntradaIntercajaVO)datos.get(0);

                String glosaIdTipoArchivo = "";
                String glosaStatusProceso = "";
                ListadoParametros ListaParam = ListadoParametros.getInstancia();
                Parametro[] paramTipoArchivo = ListaParam.getListTipoArchivo();
                Parametro[] paramStatusProceso = ListaParam.getListStatusProceso();

                glosaIdTipoArchivo = Helper.obtenerDescripcion(paramTipoArchivo, resp.getTipoArchivo());
                resp.setGlosaIdTipoArchivo(glosaIdTipoArchivo);

                glosaStatusProceso = Helper.obtenerDescripcion(paramStatusProceso, resp.getStatusProceso());
                resp.setGlosaStatusProceso(glosaStatusProceso);

                resp.setCodResultado(0);
            }

            return resp;

        }catch(SQLException e){
            resp.setCodResultado(99);
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace(); }
        }

        return resp;
    }

    public static EntradaIntercajaVO obtenerCabeceraDetalleFileFallido(String nombreArchivo, String statusProceso)
    {
        /* Codigos de retorno:
         * 0: si la consulta se ha realizado de forma exitosa.
         * 99: si ha ocurrido un error.
         */

        List datos = null;
        EntradaIntercajaVO resp = new EntradaIntercajaVO();
        EntradaIntercajaVO entrada = new EntradaIntercajaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        entrada.setNombreArchivo(nombreArchivo);
        entrada.setStatusProceso(Integer.parseInt(statusProceso));

        try
        {
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", entrada);

            datos = sqlMap.queryForList("intercajaNS.selectDetalleParaArchivoFallido",parametros);

            if(datos != null && datos.size() > 0)
            {
                resp = (EntradaIntercajaVO)datos.get(0);

                String glosaIdTipoArchivo = "";
                String glosaStatusProceso = "";
                ListadoParametros ListaParam = ListadoParametros.getInstancia();
                Parametro[] paramTipoArchivo = ListaParam.getListTipoArchivo();
                Parametro[] paramStatusProceso = ListaParam.getListStatusProceso();

                glosaIdTipoArchivo = Helper.obtenerDescripcion(paramTipoArchivo, resp.getTipoArchivo());
                resp.setGlosaIdTipoArchivo(glosaIdTipoArchivo);

                glosaStatusProceso = Helper.obtenerDescripcion(paramStatusProceso, resp.getStatusProceso());
                resp.setGlosaStatusProceso(glosaStatusProceso);

                resp.setCodResultado(0);
            }

            return resp;

        }catch(SQLException e){
            resp.setCodResultado(99);
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace(); }
        }

        return resp;
    }

    public static EntradaIntercajaVO obtenerCabeceraDetalleFileCobol(String nombreArchivo, String idMaestroArchivo)
    {
        /* Codigos de retorno:
         * 0: si la consulta se ha realizado de forma exitosa.
         * 99: si ha ocurrido un error.
         */

        List datos = null;
        EntradaIntercajaVO resp = new EntradaIntercajaVO();
        EntradaIntercajaVO entrada = new EntradaIntercajaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        entrada.setNombreArchivo(nombreArchivo);
        entrada.setIdMaestroArchivo(Long.parseLong(idMaestroArchivo));

        try
        {
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", entrada);

            datos = sqlMap.queryForList("intercajaNS.selectDetalleDespuesCobol",parametros);

            if(datos != null && datos.size() > 0)
            {
                resp = (EntradaIntercajaVO)datos.get(0);

                String glosaIdTipoArchivo = "";
                String glosaStatusProceso = "";
                ListadoParametros ListaParam = ListadoParametros.getInstancia();
                Parametro[] paramTipoArchivo = ListaParam.getListTipoArchivo();
                Parametro[] paramStatusProceso = ListaParam.getListStatusProceso();

                glosaIdTipoArchivo = Helper.obtenerDescripcion(paramTipoArchivo, resp.getTipoArchivo());
                resp.setGlosaIdTipoArchivo(glosaIdTipoArchivo);

                glosaStatusProceso = Helper.obtenerDescripcion(paramStatusProceso, resp.getStatusProceso());
                resp.setGlosaStatusProceso(glosaStatusProceso);

                resp.setCodResultado(0);
            }

            return resp;

        }catch(SQLException e){
            resp.setCodResultado(99);
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace(); }
        }

        return resp;		

    }
    public static EntradaIntercajaVO consultarStatusProceso(String nombreArchivo, String idTipoArchivo)
    {
        List datos = null;
        String extension = ".txt";
        EntradaIntercajaVO resp = new EntradaIntercajaVO();
        EntradaIntercajaVO entrada = new EntradaIntercajaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        StringBuffer nameFile = new StringBuffer();
        nameFile.append(nombreArchivo);
        nameFile.append(extension);
        entrada.setNombreArchivo(nameFile.toString());
        entrada.setTipoArchivo(Integer.parseInt(idTipoArchivo));

        try
        {
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", entrada);

            datos = sqlMap.queryForList("intercajaNS.selectStatusProceso",parametros);

            if(datos != null && datos.size() > 0)
            {
                resp = (EntradaIntercajaVO)datos.get(0);

                resp.setCodResultado(0);
                resp.setNombreArchivo(nameFile.toString());
            }

            return resp;

        }catch(SQLException e){
            resp.setCodResultado(99);
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace(); }
        }
        return resp;
    }
}
