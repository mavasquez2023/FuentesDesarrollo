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
import cl.araucana.independientes.vo.LinConsModMasivaSolVO;
import cl.araucana.independientes.vo.SolicitudVO;
import cl.araucana.independientes.vo.param.Parametro;

public class ConsModMasivaSolDAO {

    public LinConsModMasivaSolVO[] consultaMasivaSolicitudes(String fechaInicio, String fechaFin, String estado){

        //declaracion de variable sqlMap.
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        HashMap paramMap = new HashMap();
        List datos = null;

        String fecha = "";
        Date date = new Date();
        Date dateIni = new Date();
        Date dateFin = new Date();		

        String DATE_FORMAT1 = "MM/dd/yyyy";
        String DATE_FORMAT2 = "dd/MM/yyyy"; // El que se envia a la Vista
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        LinConsModMasivaSolVO[] result = null;

        try {
            fecha = fechaInicio;
            dateIni = sdf2.parse(fecha);

            fecha = fechaFin;
            dateFin = sdf2.parse(fecha);

            //preparación de parámetros de entrada.
            paramMap.put("P_FECHADESDE", new String(sdf2.format(dateIni)));
            paramMap.put("P_FECHAHASTA", new String(sdf2.format(dateFin)));
            paramMap.put("P_ESTADOSOLICITUD", new Integer(estado));

            //Se realiza la consulta al procedimiento almacenado
            datos = sqlMap.queryForList("ConsModMasivaSol.SP_NOMASOL",paramMap);

            result = (LinConsModMasivaSolVO[])datos.toArray(new LinConsModMasivaSolVO[datos.size()]);

            for(int i = 0; i < result.length ; i++){

                LinConsModMasivaSolVO lineaTemp = result[i];

                //Formateo de la fecha de ingreso.
                String fechaTemp = lineaTemp.getFechaIngreso();
                date = sdf1.parse(fechaTemp);
                lineaTemp.setFechaIngreso(sdf2.format(date));

                String rutTemp = lineaTemp.getRut();
                rutTemp = Helper.separadorDeMiles(rutTemp) + "-" + Helper.digitoVerificadorRut(rutTemp);
                lineaTemp.setRut(rutTemp);

                result[i] = lineaTemp;
            }

            return result;

        }   
        catch (SQLException e) {

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

    /*Funcion que obtiene id se solicitud de la tabla Solicitud*/
    public static SolicitudVO retornaIdSolcitud(String folio){
        /* Retornos:
         * 0 - Ok.
         * 94 - Error, no existe el folio.
         * 95 - Error, no existe el rut.
         * 96 = Error al consultar por el par folio/rut.
         * 97 = Los datos de folio y rut llegaron al DAO sin valores
         * 98 = Error en data. Existen mas de una solicitud asociada al RUT
         * 99 = Error Desconocido de BD
         * */

        //Declaracion de variables usadas en la función.
        List datos = null;
        SolicitudVO solicitudVO = new SolicitudVO();
        //int newFolio = Integer.parseInt(folio);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try{
            sqlMap.startTransaction(0);

            //Se consulta por idsolicitud
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", folio);
            datos = sqlMap.queryForList("ConsModMasivaSol.obtenerIdCheckBox",parametros);

            if(datos != null && datos.size() > 0){
                solicitudVO = (SolicitudVO)datos.get(0);
                return solicitudVO;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return solicitudVO;
    }

    /*Funcion que hace un update al tipoestadosolicitud */
    public static int updateMasivaSolicitud(String folio, String tipoEstado){
        /*
         * retorna 0 si todo esta Ok.
         * retorna 99 si hay error desconocido en DB
         */

        SolicitudVO solicitudVO = new SolicitudVO();

        int tipoEstadoSolicitud = Integer.parseInt(tipoEstado);
        int idFolio = Integer.parseInt(folio);

        solicitudVO.setFolio(idFolio);
        solicitudVO.setTipoEstadoSolicitud(tipoEstadoSolicitud);
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try{
            sqlMap.startTransaction(0);

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", solicitudVO);
            sqlMap.update("ConsModMasivaSol.updateTipoEstadoSolicitudCheckBox", parametros);
            sqlMap.commitTransaction();
            return 0;

        }catch(SQLException e){
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace(); }

        }
        return 99;
    }

    /* Función que obtiene los estados posibles a los que esta asociado un determinado estado actual.
     * Recibe como entrada en estado actual
     * Retorna un arreglo de tipo Parametro con los estados asociados al estado actual.*/
    public static Parametro[] getEstadosDestinoPosibles(String estadoActual){

        List datos = null;
        Parametro[] salida = new Parametro[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", estadoActual);
            datos = sqlMap.queryForList("ConsModMasivaSol.getEstadosDestinoPosiblesMasiva", parametros);

            return (Parametro[]) datos.toArray(new Parametro[datos.size()]);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return salida;
    }
}
