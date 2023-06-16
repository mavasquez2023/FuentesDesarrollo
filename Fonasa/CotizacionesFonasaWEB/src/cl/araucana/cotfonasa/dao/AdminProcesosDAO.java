package cl.araucana.cotfonasa.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import cl.araucana.cotfonasa.config.ConfiguracionSqlMap;

import cl.araucana.cotfonasa.vo.LogVO;
import cl.araucana.cotfonasa.vo.ParamVO;


import com.ibatis.sqlmap.client.SqlMapClient;

public class AdminProcesosDAO {

	/**
	 * @param args
	 */
	
   private String SCHEMA;
   
   
   public AdminProcesosDAO() {
	   try {
			Properties props = new Properties();
			props.load(AdminProcesosDAO.class.getClassLoader().getResourceAsStream("cl/araucana/cotfonasa/properties/parametros.properties"));
			
			SCHEMA = props.getProperty("SCHEMA");
			
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}

public int buscaAfiliado(String rut){
		
		SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
		
	    List datos = null;
		try{
			
			 HashMap parametros = new HashMap();
	       
			 System.out.println("rut a buscar:"+rut);
	         parametros.put("input", rut);
	         datos = sqlMap.queryForList("cotFonasa.buscaAfiliado", parametros);
	  
	         return datos.size();
        
		}catch(Exception e){
			e.printStackTrace();
		}
		return -1;
		
		
	}
   
   public int getRemuneracionImponible(String rutCotizante, String rutEmpleador, String periodo){
		
		SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
		int remuneracion =0;
	    List data = null;
		try{
			
			 HashMap parametros = new HashMap();
	         parametros.put("rutEmpleador", rutEmpleador);
	         parametros.put("rutCotizante", rutCotizante);
	         parametros.put("periodo", periodo);
	         data = sqlMap.queryForList("cotFonasa.getRemImponible", parametros);
	  
	         if(data != null && data.size() > 0){

	        	 remuneracion = Integer.parseInt(((String)data.get(0)));
	          }
	         
	         return remuneracion;
       
		}catch(Exception e){
			e.printStackTrace();
		}
		return -1;
		
		
	}
   
   public int getMontoImponible(String rutCotizante, String rutEmpleador, String periodo){
		
		SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
		int monto =0;
	    List data = null;
		try{
			
			 HashMap parametros = new HashMap();
	         parametros.put("rutEmpleador", rutEmpleador);
	         parametros.put("rutCotizante", rutCotizante);
	         parametros.put("periodo", periodo);
	         data = sqlMap.queryForList("cotFonasa.getMontoImponible", parametros);
	  
	         if(data != null && data.size() > 0){

	        	 monto = Integer.parseInt(((String)data.get(0)));
	          }
	         
	         return monto;
      
		}catch(Exception e){
			e.printStackTrace();
		}
		return -1;
		
		
	}
   
   public int getLicPagSubMon(String rutCotizante, String rutEmpleador, String periodo){
		
		SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
		int pagSubMon =0;
	    List data = null;
		try{
			
			 HashMap parametros = new HashMap();
	         parametros.put("rutEmpleador", rutEmpleador);
	         parametros.put("rutCotizante", rutCotizante);
	         parametros.put("periodo", periodo);
	         data = sqlMap.queryForList("cotFonasa.getLicPagSubMon", parametros);
	  
	         if(data != null && data.size() > 0){

	        	 pagSubMon = Integer.parseInt(((String)data.get(0)));
	          }
	         
	         return pagSubMon;
     
		}catch(Exception e){
			e.printStackTrace();
		}
		return -1;
		
		
	}
   
   
   
   public ParamVO[] cargaAnos(){

       SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
       List datos = null;
       ParamVO[] result = null;

     
       
       // se buscan los registros del log, correspondientes al ano solicitado
       
       try {
           HashMap parametros = new HashMap();
           //parametros.put("ANOPROCESO", ano);
           parametros.put("CTDTA", SCHEMA);
           
           System.out.println("SCHEMA CONSULTA anos: "+SCHEMA);
           datos = sqlMap.queryForList("cotFonasa.cargaAnos",parametros);
           
           System.out.println("datos size: "+datos.size());
           result = (ParamVO[])datos.toArray(new ParamVO[datos.size()]);
           
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
	
	public LogVO[] consultaLogAno(String ano){

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        List datos = null;
        LogVO[] result = null;

        System.out.print("ano: "+ano);
        
        // se buscan los registros del log, correspondientes al ano solicitado
        
        try {
            HashMap parametros = new HashMap();
            parametros.put("ANOPROCESO", ano);
            parametros.put("CTDTA", SCHEMA);
            
            System.out.println("SCHEMA CONSULTA LOG: "+SCHEMA);
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
	
	
	/*public  int insertInputFonasa(InputFonasaVO input) throws Exception{

       
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        int resp=0;
        HashMap parametros = new HashMap();

        try{
            sqlMap.startTransaction(0);
            parametros = new HashMap();
            //parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", input);
            sqlMap.insert("cotFonasa.insertInputFonasa", parametros);
            
            resp=1;

            return resp;
        }   
        catch (SQLException e) {

            resp=99;
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return resp;
    }*/
   
	/*
	public  int insertInputFonasa(ArrayList inputVos) throws Exception{

	       
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        int resp=0,i=0;
        HashMap parametros = new HashMap();

        try{
            sqlMap.startTransaction(0);
           
            //parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            
            while (i < inputVos.size() )
            {
            	
            	 parametros = new HashMap();
                 parametros.put("input", inputVos.get(i));
                 sqlMap.insert("cotFonasa.insertInputFonasa", parametros);
                 i++;
            }
            resp=1;

            return resp;
        }   
        catch (SQLException e) {

            resp=99;
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return resp;
    }
    */

}
