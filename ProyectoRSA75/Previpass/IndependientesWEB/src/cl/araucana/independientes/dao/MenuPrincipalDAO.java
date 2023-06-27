package cl.araucana.independientes.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import cl.araucana.independientes.config.ConfiguracionSqlMap;
import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.helper.IND_Constants;

import com.ibatis.sqlmap.client.SqlMapClient;

public class MenuPrincipalDAO 
{
    public static int verificaOficinaAnalista(String idAnalista)
    {
        List datos = null;
        int salida = -1;

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try 
        {
            sqlMap.startTransaction(0);

            //realiza una consulta para obtener la oficica asociada al idAnalista
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", idAnalista);
            datos = sqlMap.queryForList("menuPrincipalNS.obtenerOficinaAnalista", parametros);

            salida = Integer.parseInt((String)datos.get(0));

            return salida;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return salida;
    }

    public static int guardarOficinaAnalista(String idAnalista, String nombre, String apePat, String apeMat, String oficina)
    {
        /*
         * retorna 0 si todo esta Ok.
         * retorna 99 si hay error desconocido en DB
         */

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try{
            sqlMap.startTransaction(0);

            HashMap parametros = new HashMap();

            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("idAnalista", idAnalista);
            parametros.put("nombre", nombre);
            parametros.put("apePat", apePat);
            parametros.put("apeMat", apeMat);
            parametros.put("oficina", oficina);

            sqlMap.insert("menuPrincipalNS.insertOficinaAnalista", parametros);
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
}


