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
import cl.araucana.independientes.vo.LinPendientesIntercajaVO;
import cl.araucana.independientes.vo.PendientesIntercajaVO;
import cl.araucana.independientes.vo.param.ListadoParametros;
import cl.araucana.independientes.vo.param.ParametroLong;

public class PendientesIntercajaDAO 
{
    public static LinPendientesIntercajaVO[] obtenerCasosPendientes(String idMaestroArchivo, String tipoArchivo)
    {	
        List datos = null;
        Date date = new Date();
        String fechaProc = "";
        int idTipoArchivo = Integer.parseInt(tipoArchivo);

        String DATE_FORMAT1 = "MM/dd/yyyy";
        String DATE_FORMAT2 = "dd/MM/yyyy";
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        LinPendientesIntercajaVO[] pendientesSalida = new LinPendientesIntercajaVO[0];
        LinPendientesIntercajaVO[] pendientesIntercaja = null;
        PendientesIntercajaVO pendienteVo = new PendientesIntercajaVO();

        /*Declaracion de variables SQLMap.*/
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        HashMap parametros = new HashMap();

        /*Seteo de variables.*/
        pendienteVo.setIdMaestroArchivo(Long.parseLong(idMaestroArchivo));
        pendienteVo.setTipoArchivo(Integer.parseInt(tipoArchivo));

        try
        {
            sqlMap.startTransaction(0);

            switch(idTipoArchivo)
            {
            case 1: /*Para archivo TRASPASOS*/
                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", pendienteVo);

                datos = sqlMap.queryForList("intercajaNS.selectTraPendIntercaja",parametros);

                if(datos != null && datos.size() > 0)
                {
                    pendientesIntercaja = (LinPendientesIntercajaVO[])datos.toArray(new LinPendientesIntercajaVO[datos.size()]);

                    for(int i=0; i<pendientesIntercaja.length; i++)
                    {
                        LinPendientesIntercajaVO pendientesIntercajaTemp = new LinPendientesIntercajaVO();
                        pendientesIntercajaTemp = pendientesIntercaja[i];

                        /*Seteo de la fecha de carga*/
                        String fechaTemp1 = pendientesIntercajaTemp.getFechaSolicitud();
                        date = sdf1.parse(fechaTemp1);
                        fechaProc = sdf2.format(date);
                        pendientesIntercajaTemp.setFechaSolicitud(fechaProc);

                        pendientesIntercaja[i] = pendientesIntercajaTemp;
                    }
                }else{
                    System.out.println("PENDIENTESINTERCAJADAO -- Ha ocurrido un error al consultar por CasosPendientes.");
                    return pendientesSalida;
                }
                break;

            case 2: /*Para archivo COLISIONES*/

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", pendienteVo);

                datos = sqlMap.queryForList("intercajaNS.selectColPendIntercaja",parametros);

                if(datos != null && datos.size() > 0){

                    pendientesIntercaja = (LinPendientesIntercajaVO[])datos.toArray(new LinPendientesIntercajaVO[datos.size()]);

                }else{

                    System.out.println("PENDIENTESINTERCAJADAO -- Ha ocurrido un error al consultar por CasosPendientes.");
                    return pendientesSalida;
                }					
                break;

            case 3:/*Para archivo RECIBIDOS*/

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", pendienteVo);

                datos = sqlMap.queryForList("intercajaNS.selectRecPendIntercaja",parametros);

                if(datos != null && datos.size() > 0)
                {
                    pendientesIntercaja = (LinPendientesIntercajaVO[])datos.toArray(new LinPendientesIntercajaVO[datos.size()]);

                    for(int i=0; i<pendientesIntercaja.length; i++)
                    {
                        LinPendientesIntercajaVO pendientesIntercajaTemp = new LinPendientesIntercajaVO();
                        pendientesIntercajaTemp = pendientesIntercaja[i];

                        /*Seteo de la fecha de carga*/
                        String fechaTemp1 = pendientesIntercajaTemp.getFechaProcesamiento();
                        date = sdf1.parse(fechaTemp1);
                        fechaProc = sdf2.format(date);
                        pendientesIntercajaTemp.setFechaProcesamiento(fechaProc);

                        pendientesIntercaja[i] = pendientesIntercajaTemp;
                    }
                }else{

                    System.out.println("PENDIENTESINTERCAJADAO -- Ha ocurrido un error al consultar por CasosPendientes RECIBIDOS.");
                    return pendientesSalida;
                }
                break;

            case 5: /*Para archivo ERRORES*/

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", pendienteVo);

                datos = sqlMap.queryForList("intercajaNS.selectErrPendIntercaja",parametros);

                if(datos != null && datos.size() > 0)
                {
                    pendientesIntercaja = (LinPendientesIntercajaVO[])datos.toArray(new LinPendientesIntercajaVO[datos.size()]);
                }else
                {
                    System.out.println("PENDIENTESINTERCAJADAO -- Ha ocurrido un error al consultar por CasosPendientes CASE 5.- ERRORES.");
                    return pendientesSalida;
                }					
                break;

            default:
                System.out.println("PENDIENTESINTERCAJADAO -- Ha ocurrido un error al consultar en la funcion obtenerCasosPendientes().");
            return pendientesSalida;
            }

            return pendientesIntercaja;

        }catch(SQLException e){

            e.printStackTrace();

        }catch (ParseException e) {
            e.printStackTrace();

        }finally {

            try { sqlMap.endTransaction(); 

            }catch (SQLException e) { 

                e.printStackTrace(); }

        }

        return pendientesIntercaja;
    }

    public static LinPendientesIntercajaVO[] obtenerDetallePendiente(String idDetalleFile, String idFileType)
    {
        List datos = null;

        ListadoParametros listParametros = ListadoParametros.getInstancia();

        Date date = new Date();
        String fechaSolicitud = "";
        String fechaTemp = "";
        String rutTemp = "";
        String rutFormateado = "";

        String DATE_FORMAT1 = "MM/dd/yyyy";
        String DATE_FORMAT2 = "dd/MM/yyyy";
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        LinPendientesIntercajaVO[] pendientesSalida = new LinPendientesIntercajaVO[0];
        LinPendientesIntercajaVO[] pendientes = null;
        PendientesIntercajaVO pendienteVo = new PendientesIntercajaVO();

        /*Declaracion de variables SQLMap.*/
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        HashMap parametros = new HashMap();

        /*seteo de parametros de consulta.*/
        pendienteVo.setCorrelCasePendiente(Long.parseLong(idDetalleFile));
        pendienteVo.setTipoArchivo(Integer.parseInt(idFileType));
        int idTipoArchivo = Integer.parseInt(idFileType);

        try
        {
            sqlMap.startTransaction(0);

            switch(idTipoArchivo)
            {
            case 1: /*Tipo de archivo TRASPASOS*/

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", pendienteVo);

                datos = sqlMap.queryForList("intercajaNS.selectDetalleTraPendInt",parametros);
                sqlMap.endTransaction();

                if(datos != null && datos.size() > 0)
                {
                    pendientes = (LinPendientesIntercajaVO[])datos.toArray(new LinPendientesIntercajaVO[datos.size()]);

                    for(int i=0; i<pendientes.length; i++)
                    {
                        LinPendientesIntercajaVO pendientesIntercajaTemp = new LinPendientesIntercajaVO();
                        pendientesIntercajaTemp = pendientes[i];

                        String codigoCCAFext = Long.toString(pendientesIntercajaTemp.getCcafCajaOrigen());

                        int codigoCCAFint = Helper.obtenerCodigo(listParametros.getListCodPareoCajas(), codigoCCAFext);
                        String glosaCaja = Helper.obtenerDescripcion(listParametros.getListTxtPareoCajas(), codigoCCAFint);
                        pendientesIntercajaTemp.setCcafCajaOrigenGlosa(glosaCaja);

                        codigoCCAFext = Long.toString(pendientesIntercajaTemp.getCcafCajaDestino());

                        codigoCCAFint = Helper.obtenerCodigo(listParametros.getListCodPareoCajas(), codigoCCAFext);
                        glosaCaja = Helper.obtenerDescripcion(listParametros.getListTxtPareoCajas(), codigoCCAFint);
                        pendientesIntercajaTemp.setCcafCajaDestinoGlosa(glosaCaja);

                        fechaTemp = pendientesIntercajaTemp.getFechaSolicitud();
                        date = sdf1.parse(fechaTemp);
                        fechaSolicitud = sdf2.format(date);
                        pendientesIntercajaTemp.setFechaSolicitud(fechaSolicitud);

                        fechaTemp = pendientesIntercajaTemp.getFechaInicio();
                        date = sdf1.parse(fechaTemp);
                        fechaSolicitud = sdf2.format(date);
                        pendientesIntercajaTemp.setFechaInicio(fechaSolicitud);

                        rutTemp = pendientesIntercajaTemp.getRutAfiliado();
                        rutFormateado = Helper.separadorDeMiles(rutTemp) + "-" + Helper.digitoVerificadorRut(rutTemp);
                        pendientesIntercajaTemp.setRutAfiliado(rutFormateado);

                        pendientes[i] = pendientesIntercajaTemp;
                    }
                }else{
                    System.out.println("PENDIENTESINTERCAJADAO -- Ha ocurrido un error al consultar en la funcion obtenerDetallePendiente() para tipo de archivo de TRASPASOS.");
                    return pendientesSalida;
                }

                break;

            case 2: /*Tipo de archivo COLISIONES.*/

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", pendienteVo);

                datos = sqlMap.queryForList("intercajaNS.selectDetalleColPendInt",parametros);

                if(datos != null && datos.size() > 0)
                {
                    pendientes = (LinPendientesIntercajaVO[])datos.toArray(new LinPendientesIntercajaVO[datos.size()]);

                    for(int i=0; i<pendientes.length; i++)
                    {
                        LinPendientesIntercajaVO pendientesIntercajaTemp = new LinPendientesIntercajaVO();
                        pendientesIntercajaTemp = pendientes[i];

                        rutTemp = pendientesIntercajaTemp.getRutAfiliado();
                        rutFormateado = Helper.separadorDeMiles(rutTemp) + "-" + Helper.digitoVerificadorRut(rutTemp);
                        pendientesIntercajaTemp.setRutAfiliado(rutFormateado);

                        pendientes[i] = pendientesIntercajaTemp;
                    }

                }else{
                    System.out.println("PENDIENTESINTERCAJADAO -- Ha ocurrido un error al consultar en la funcion obtenerDetallePendiente() para tipo de archivo COLISIONES.");
                    return pendientesSalida;
                }

                break;

            case 3: /*Tipo de archivo RECIBIDOS.*/

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", pendienteVo);

                datos = sqlMap.queryForList("intercajaNS.selectDetalleRecPendInt",parametros);
                sqlMap.endTransaction();

                if(datos != null && datos.size() > 0)
                {
                    pendientes = (LinPendientesIntercajaVO[])datos.toArray(new LinPendientesIntercajaVO[datos.size()]);

                    for(int i=0; i<pendientes.length; i++)
                    {
                        LinPendientesIntercajaVO pendientesIntercajaTemp = new LinPendientesIntercajaVO();
                        pendientesIntercajaTemp = pendientes[i];

                        String codigoCCAFext = Long.toString(pendientesIntercajaTemp.getCcafCajaOrigen());

                        int codigoCCAFint = Helper.obtenerCodigo(listParametros.getListCodPareoCajas(), codigoCCAFext);
                        String glosaCaja = Helper.obtenerDescripcion(listParametros.getListTxtPareoCajas(), codigoCCAFint);
                        pendientesIntercajaTemp.setCcafCajaOrigenGlosa(glosaCaja);

                        codigoCCAFext = Long.toString(pendientesIntercajaTemp.getCcafCajaDestino());

                        codigoCCAFint = Helper.obtenerCodigo(listParametros.getListCodPareoCajas(), codigoCCAFext);
                        glosaCaja = Helper.obtenerDescripcion(listParametros.getListTxtPareoCajas(), codigoCCAFint);
                        pendientesIntercajaTemp.setCcafCajaDestinoGlosa(glosaCaja);

                        fechaTemp = pendientesIntercajaTemp.getFechaSolicitud();
                        date = sdf1.parse(fechaTemp);
                        fechaSolicitud = sdf2.format(date);
                        pendientesIntercajaTemp.setFechaSolicitud(fechaSolicitud);

                        fechaTemp = pendientesIntercajaTemp.getFechaInicio();
                        date = sdf1.parse(fechaTemp);
                        fechaSolicitud = sdf2.format(date);
                        pendientesIntercajaTemp.setFechaInicio(fechaSolicitud);

                        rutTemp = pendientesIntercajaTemp.getRutAfiliado();
                        rutFormateado = Helper.separadorDeMiles(rutTemp) + "-" + Helper.digitoVerificadorRut(rutTemp);
                        pendientesIntercajaTemp.setRutAfiliado(rutFormateado);

                        pendientes[i] = pendientesIntercajaTemp;
                    }	
                }else{

                    System.out.println("PENDIENTESINTERCAJADAO -- Ha ocurrido un error al consultar en la funcion obtenerDetallePendiente() para tipo de archivo RECIBIDOS.");
                    return pendientesSalida;
                }

                break;

            case 5: /*AQUI DEBIERA IR PARA TIPO DE ARCHIVO DE ERRORES, FALTA DEFINIR PROCEDIMIENTOS*/

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", pendienteVo);

                datos = sqlMap.queryForList("intercajaNS.selectDetalleErrPendInt",parametros);

                if(datos != null && datos.size() > 0){

                    pendientes = (LinPendientesIntercajaVO[])datos.toArray(new LinPendientesIntercajaVO[datos.size()]);

                }else{

                    System.out.println("PENDIENTESINTERCAJADAO -- Ha ocurrido un error al consultar en la funcion obtenerDetallePendiente() para tipo de archivo ERRORES.");
                    return pendientesSalida;
                }

                break;

            default :

                System.out.println("PENDIENTESINTERCAJADAO -- Ha ocurrido un error al consultar en la funcion obtenerDetallePendiente().");
            return pendientesSalida;
            }

            return pendientes;

        }catch(SQLException e)
        {
            e.printStackTrace();
        }catch (ParseException e) 
        {
            e.printStackTrace();
        }finally {

            try { sqlMap.endTransaction(); 

            }catch (SQLException e) { 

                e.printStackTrace(); }
        }		
        return pendientes;
    }

    /*private static String obtenerCajaPorCodigo(String codigoCaja)
	{
		String resp = null;
		List datos = null;
		SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
		HashMap parametros = new HashMap();

		try
		{
			sqlMap.startTransaction(0);
			parametros.put("CPEDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
			parametros.put("input", codigoCaja);

			datos = sqlMap.queryForList("intercajaNS.selectCajaPendIntercaja",parametros);

			if(datos != null && datos.size() > 0)
			{
				resp = (String)datos.get(0);
			}else{
				resp = "";
			}

			return resp;
		}catch(SQLException e){

			resp = "Error";
			e.printStackTrace();

		}finally {

			try { sqlMap.endTransaction(); 

			}catch (SQLException e) { 

				e.printStackTrace(); }

		}

		return resp;
	}*/

    public static LinPendientesIntercajaVO[] obtenerDataGrillaColisiones(String numeroRegistro)
    {
        List datos = null;
        Date date = new Date();
        String fechaSolicitud = "";

        String DATE_FORMAT1 = "MM/dd/yyyy";
        String DATE_FORMAT2 = "dd/MM/yyyy";
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        LinPendientesIntercajaVO[] pendientesSalida = new LinPendientesIntercajaVO[0];
        LinPendientesIntercajaVO[] pendientes = null;
        PendientesIntercajaVO pendienteVo = new PendientesIntercajaVO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        HashMap parametros = new HashMap();

        ListadoParametros listParametros = ListadoParametros.getInstancia();

        pendienteVo.setCorrelCasePendiente(Long.parseLong(numeroRegistro));

        try
        {
            sqlMap.startTransaction(0);
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", pendienteVo);

            datos = sqlMap.queryForList("intercajaNS.selectGrillaColPendInt",parametros);

            sqlMap.endTransaction();

            if(datos != null && datos.size() > 0)
            {
                pendientes = (LinPendientesIntercajaVO[])datos.toArray(new LinPendientesIntercajaVO[datos.size()]);

                for(int i=0; i<pendientes.length; i++)
                {
                    LinPendientesIntercajaVO pendientesIntercajaTemp = new LinPendientesIntercajaVO();
                    pendientesIntercajaTemp = pendientes[i];

                    String codigoCCAFext = Long.toString(pendientesIntercajaTemp.getId_ccaf());

                    int codigoCCAFint = Helper.obtenerCodigo(listParametros.getListCodPareoCajas(), codigoCCAFext);
                    String glosaCaja = Helper.obtenerDescripcion(listParametros.getListTxtPareoCajas(), codigoCCAFint);
                    pendientesIntercajaTemp.setGlosaCaja(glosaCaja);

                    String fechaTemp = pendientesIntercajaTemp.getFechaSolicitud();
                    date = sdf1.parse(fechaTemp);
                    fechaSolicitud = sdf2.format(date);
                    pendientesIntercajaTemp.setFechaSolicitud(fechaSolicitud);

                    int flagRecibido = pendientesIntercajaTemp.getFlagRecibe();

                    switch(flagRecibido)
                    {
                    case 0:
                        pendientesIntercajaTemp.setRecibeAfiliado(IND_Constants.str_pierdeAfiliado);
                        break;

                    case 1:
                        pendientesIntercajaTemp.setRecibeAfiliado(IND_Constants.str_ganaAfiliado);
                        break;

                    default:
                        System.out.println("ERROR EN PENSIENTESINTERCAJADAO -- funcion obtenerDataGrillaColisiones().");	
                    }

                    pendientes[i] = pendientesIntercajaTemp;
                }
            }else{
                return pendientesSalida;
            }

            return pendientes;

        }catch(SQLException e){

            e.printStackTrace();

        }catch (ParseException e) {
            e.printStackTrace();

        }finally {

            try { sqlMap.endTransaction(); 

            }catch (SQLException e) { 

                e.printStackTrace(); }

        }

        return pendientes;
    }

    public static ParametroLong[] getFiltroArchivosPorFechayTipo(String fechaDesde, String fechaHasta, String tipoArchivo)
    {
        List datos = null;
        String nombreMes = "";
        String periodo = "";

        ParametroLong[] salida = new ParametroLong[0];
        ParametroLong[] salidaDatos = null;
        PendientesIntercajaVO pendientes = new PendientesIntercajaVO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        pendientes.setFechaInferior(fechaDesde);
        pendientes.setFechaSuperior(fechaHasta);
        pendientes.setTipoArchivo(Integer.parseInt(tipoArchivo));

        try
        {
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", pendientes);

            datos = sqlMap.queryForList("intercajaNS.selectArchivosPorFechayTipo", parametros);			

            if(datos != null && datos.size() > 0)
            {
                salidaDatos = (ParametroLong[]) datos.toArray(new ParametroLong[datos.size()]);

                for(int i=0; i<salidaDatos.length; i++)
                {
                    ParametroLong salidaTemp = new ParametroLong();
                    salidaTemp = salidaDatos[i];

                    String glosa = salidaTemp.getGlosa();
                    String codigoMes = glosa.substring(2,4);
                    String codigoAnio = glosa.substring(4,8); 

                    switch(Integer.parseInt(codigoMes))
                    {
                    case 1:
                        nombreMes = IND_Constants.str_enero;
                        periodo = nombreMes + "-" + codigoAnio;
                        break;	
                    case 2:
                        nombreMes = IND_Constants.str_febrero;
                        periodo = nombreMes + "-" + codigoAnio;
                        break;
                    case 3:
                        nombreMes = IND_Constants.str_marzo;
                        periodo = nombreMes + "-" + codigoAnio;
                        break;	
                    case 4:
                        nombreMes = IND_Constants.str_abril;
                        periodo = nombreMes + "-" + codigoAnio;
                        break;	
                    case 5:
                        nombreMes = IND_Constants.str_mayo;
                        periodo = nombreMes + "-" + codigoAnio;
                        break;	
                    case 6:
                        nombreMes = IND_Constants.str_junio;
                        periodo = nombreMes + "-" + codigoAnio;
                        break;	
                    case 7:
                        nombreMes = IND_Constants.str_julio;
                        periodo = nombreMes + "-" + codigoAnio;
                        break;	
                    case 8:
                        nombreMes = IND_Constants.str_agosto;
                        periodo = nombreMes + "-" + codigoAnio;
                        break;	
                    case 9:
                        nombreMes = IND_Constants.str_septiembre;
                        periodo = nombreMes + "-" + codigoAnio;
                        break;	
                    case 10:
                        nombreMes = IND_Constants.str_octubre;
                        periodo = nombreMes + "-" + codigoAnio;
                        break;	
                    case 11:
                        nombreMes = IND_Constants.str_noviembre;
                        periodo = nombreMes + "-" + codigoAnio;
                        break;	
                    case 12:
                        nombreMes = IND_Constants.str_diciembre;
                        periodo = nombreMes + "-" + codigoAnio;
                        break;	

                    default:
                        System.out.println("ERROR AL OBTENER CODIGO DE MES - PENDIENTESINTERCAJADAO - funcion getFiltroArchivosPorFechayTipo()");
                    }

                    salidaTemp.setPeriodo(periodo);
                    salidaDatos[i] = salidaTemp;

                }

                return salidaDatos;

            }else{

                return salida;
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return salida;

    }
}
