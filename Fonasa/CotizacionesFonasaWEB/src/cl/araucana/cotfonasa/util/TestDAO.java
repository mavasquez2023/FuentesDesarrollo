package cl.araucana.cotfonasa.util;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import cl.araucana.cotfonasa.config.ConfiguracionSqlMap;
import cl.araucana.cotfonasa.vo.LogVO;


import com.ibatis.sqlmap.client.SqlMapClient;

public class TestDAO {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Apéndice de método generado automáticamente
		
		 LogVO[] result = null;
		 int i=0;
		 
		 result = consultaLogAno("2011");
		 
		 while (result.length > 0)
		 {
			 LogVO logVo = new LogVO();
			 
			   logVo = result[i];
			   
			   System.out.println("logVo "+logVo);
			 
			 i++;
		 }
		
		
		

	}
	
	public static LogVO[] consultaLogAno(String ano){

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        List datos = null;
        LogVO[] result = null;

        int codigo=0;
        int codigoTipoPago=0;
        int codigoEstado=0;

        
        // se consulta las oficinas de oficinas_ti haciendo un pareo con cmdta.cm01f1
        try {
            HashMap parametros = new HashMap();
            parametros.put("ANOPROCESO", ano);
            datos = sqlMap.queryForList("cotFonasa.consultaLogAno",parametros);
            
        	

            result = (LogVO[])datos.toArray(new LogVO[datos.size()]);
            
            return result;
        }   
        catch (SQLException e) {

            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return result;
    }

}
