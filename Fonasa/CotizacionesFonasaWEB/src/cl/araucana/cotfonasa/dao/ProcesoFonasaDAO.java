package cl.araucana.cotfonasa.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import cl.araucana.cotfonasa.config.ConfiguracionSqlMap;
import cl.araucana.cotfonasa.impl.ProcesoFonasaImpl;
import cl.araucana.cotfonasa.vo.LogVO;
import cl.araucana.cotfonasa.vo.ParametrosVO;
import cl.araucana.cotfonasa.vo.RespSpVO;



import com.ibatis.sqlmap.client.SqlMapClient;

public class ProcesoFonasaDAO {
	
	private String SCHEMA="";
	
	
	
	public ProcesoFonasaDAO() {
		
		
		
		try {
			Properties props = new Properties();
			props.load(ProcesoFonasaDAO.class.getClassLoader().getResourceAsStream("cl/araucana/cotfonasa/properties/parametros.properties"));
			
			SCHEMA = props.getProperty("SCHEMA");
			
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}

	public  int updateEstadoLog(String periodo, int estado) 
	{

	       
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        int resp=0,i=0;
        HashMap parametros = new HashMap();

        try{
            sqlMap.startTransaction(0);
     
                	 System.out.println("update parametros");
                	 parametros = new HashMap();
                     LogVO log = new LogVO();
                	 log.setEstado(estado);
                	 log.setPeriodoInsert(Integer.parseInt(periodo));
                     
                     parametros.put("input",log );
                     parametros.put("CTDTA", SCHEMA);
                     sqlMap.insert("cotFonasa.updateEstadoLog", parametros);
                 
                 
            resp=1;

            return resp;
        }   
        catch (SQLException e) {

            resp=0;
            e.printStackTrace();
        }
        catch (Exception e) {

            resp=0;
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
	
	public  int guardaParametros(ParametrosVO parametrosVo) 
	{

	       
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        int resp=0,i=0;
        HashMap parametros = new HashMap();

        try{
            sqlMap.startTransaction(0);
     
            	 parametros = new HashMap();
           
                 
                 //primero se revisa si existe registro , si existe se hace un update, sino un insert
                 /*parametros = new HashMap();
                 parametros.put("input", parametrosVo.getIdParametro());
                 parametros.put("CTDTA",SCHEMA );
                 List datos = sqlMap.queryForList("cotFonasa.getCountParam", parametros);
                 
                 System.out.println("datos size: "+datos.size());*/
                 
                 if(Integer.parseInt(parametrosVo.getIdParametro()) != -1)
                 {
                	 
                	
                	 System.out.println("update parametros");
                	 parametros = new HashMap();
                	 parametros.put("CTDTA", SCHEMA);
                     parametros.put("input", parametrosVo);
                     sqlMap.insert("cotFonasa.updateParam", parametros);
                     
                 }else{
                    	 
                    	 System.out.println("insert parametros");
                    	 parametros = new HashMap();
                    	 parametros.put("CTDTA", SCHEMA);
                         parametros.put("input", parametrosVo);
                         sqlMap.insert("cotFonasa.insertParam", parametros);
                    
                        
                     }
             
                 
                 
            resp=1;

            return resp;
        }   
        catch (SQLException e) {

            resp=0;
            e.printStackTrace();
        }
        catch (Exception e) {

            resp=0;
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
	
	
	
	
	public  int insertLog(String periodo,String archivoEntrada,String archivoSalida,Date fechaInicio,Date horaInicio,
			Date horaTermino, Date fechaTermino, int estado) 
	throws Exception{

	       
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        int resp=0,i=0;
        HashMap parametros = new HashMap();

        try{
            sqlMap.startTransaction(0);
            
            System.out.println("periodo:"+periodo+ "arch ent:"+archivoEntrada+"arch salida: "+ archivoSalida);
     
            	 parametros = new HashMap();
            	 LogVO logVo = new LogVO();
                 logVo.setPeriodoInsert(Integer.parseInt(periodo));
                 logVo.setArchivoEntrada(archivoEntrada);
                 logVo.setArchivoSalida(archivoSalida);
                 logVo.setFechaInicioProcesoDate(fechaInicio);
                 logVo.setFechaTerminoProcesoDate(fechaTermino);
                 logVo.setHoraInicioProcesoDate(horaInicio);
                 logVo.setHoraTerminoProcesoDate(horaTermino);
                 logVo.setEstado(estado);
                 
                 
                 
                 //primero se revisa si existe registro para periodo, si existe se hace un update, sino un insert
                 parametros = new HashMap();
                 parametros.put("input", periodo);
                 parametros.put("CTDTA", SCHEMA);
                 List datos = sqlMap.queryForList("cotFonasa.getCountLog", parametros);
                 if(datos != null && datos.size() >0 )
                 {
                	 
                	 parametros = new HashMap();
                     System.out.println("update log");
                     parametros.put("input", logVo);
                     parametros.put("CTDTA", SCHEMA);
                     sqlMap.insert("cotFonasa.updateLog", parametros);
                 }
                 else{
                	 
                	 parametros = new HashMap();
                	 System.out.println("insert log");
                     parametros.put("input", logVo);
                     parametros.put("CTDTA", SCHEMA);
                     sqlMap.insert("cotFonasa.insertLog", parametros);
                
                    
                 }
                 
                 
            resp=1;

            return resp;
        }
        catch (SQLException e) {

            resp=0;
            e.printStackTrace();
        }catch(Exception e){
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
	
	public  int  deleteTable(String schemaTable) 
	throws Exception{

	       
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        int resp=0,i=0;
        

        try{
            sqlMap.startTransaction(0);
     
            	
                 
            HashMap parametros = new HashMap();
            parametros = new HashMap();
            
            parametros.put("TABLE", schemaTable);
            
           
            sqlMap.delete("cotFonasa.deleteTable", parametros);
                 
            resp=1;

            return resp;
        }   
        catch (SQLException e) {

            resp=0;
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
public ArrayList getDatosOutput(){
		
		SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
		int monto =0;
	    List data = null;
		try{
			
			
	         data = sqlMap.queryForList("cotFonasa.getDatosOutput");
	  
	        
	         
	         return (ArrayList) data;
      
		}catch(Exception e){
			e.printStackTrace();
		}
		//return -1;
		return null;
		
		
	}
	
	
	public RespSpVO ejecutaSpProceso () throws SQLException
	{
		
        	SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
    	
    		HashMap parametros = new HashMap();
            RespSpVO resp = new RespSpVO();
            
    		parametros.put("RET_VAL", new Integer(1));
            
    		parametros.put("CTDTA", SCHEMA);
			sqlMap.queryForObject("cotFonasa.SP_PROCESO",parametros);
		   
			String sqlCode = (String)parametros.get("RET_VAL");
			Integer totalAraucana = (Integer)parametros.get("REG_ARAUCANA");
			Integer totalNoAraucana = (Integer)parametros.get("REG_NOT_ARAUCANA");
			Integer totalConDatosArau = (Integer)parametros.get("REG_CON_DATOS_ARAU");

			System.out.println("Fin de Procedimiento Almacenado"); 
			resp.setSqlCode(sqlCode);
			resp.setTotalAraucana(totalAraucana.intValue());
			resp.setTotalConDatosArau(totalConDatosArau.intValue());
			resp.setTotalNoAraucana(totalNoAraucana.intValue());
			resp.setTotalRegistros(totalAraucana.intValue()  + totalNoAraucana.intValue());
	
			resp.setTotalRegistrosPorc(100);
			
			int totalReg = (totalAraucana.intValue()  + totalNoAraucana.intValue());
			
			resp.setTotalAraucanaPorc((totalAraucana.intValue() * 100 )/ (totalReg));
			resp.setTotalConDatosArauPorc( (totalConDatosArau.intValue() * 100 )/(totalReg));
			
			return resp;
		
	
		
	}
	
	public ParametrosVO[] getParametros(){

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        List datos = null;
        ParametrosVO[] result = null;

       
        
        // se buscan los registros del log, correspondientes al ano solicitado
        
        try {
            HashMap parametros = new HashMap();
            parametros.put("CTDTA", SCHEMA);
            datos = sqlMap.queryForList("cotFonasa.getParametros",parametros);
            
           

            result = (ParametrosVO[])datos.toArray(new ParametrosVO[datos.size()]);
            
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
	
	public int consultaProceso(){

	    SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
	    List datos = null;
	   

	   // se consulta si algun procesa esta en estado = 2, procesando
	    
	    try {
	        HashMap parametros = new HashMap();
	       // parametros.put("ANOPROCESO", ano);
	        parametros.put("CTDTA", SCHEMA);
	        
	        System.out.println("SCHEMA CONSULTA proceso: "+SCHEMA);
	        datos = sqlMap.queryForList("cotFonasa.consultaProceso",parametros);
	        
	        if(datos != null && datos.size() >0 )
	        return 1;
	        else 
	        return 0;

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
	    return -1;
	 }
	

}
