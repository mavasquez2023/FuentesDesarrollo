package cl.araucana.cp.hibernate.dao;


 
 
 
import java.util.List;
import java.util.ArrayList;
import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import cl.araucana.cp.hibernate.beans.MesesbeanVO;
 

public class monthDAO {
	
	
	public final String ESQUEMA="PWDTAD";
	 
	public Connection coneccionDatasource()
	    {
	     try{  
	
	        Hashtable env = new Hashtable();
	        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
	       javax.naming.Context ctx=new InitialContext(env);
	    
	     //**cambiar aqui**DataSource ds = (DataSource)ctx.lookup("jdbc/cppub");
	       DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/cppub");
	       ctx.close();
	        return ds.getConnection();
	  
	     }
	       catch(Exception ex)
	        {ex.printStackTrace();}
	        
	       return null;
	    } 

	
  
  
 
 public List getDatosDescarga(MesesbeanVO MesesVO ){
	 
	 List lista=new ArrayList();
	  
	 
		String rutaf=""; 
		String sucursal="";
		String sql;
		String table="";
		String listaRut="";
		String nomTrabajador="";
		String holding="";
		String sqlTodos="";
		String SQL="";
		
		
		 Connection conexion=null;
		 ResultSet rs=null;
		  
		 
		 try{
		 
			 
			 
		conexion=this.coneccionDatasource();  
		
		
		 if(!"".equals(MesesVO.getNombreTrabajador())&&MesesVO.getNombreTrabajador()!=null)
			 nomTrabajador=" AND UPPER(trim(PWCCAPEAF)) || ' ' || UPPER(trim(PWCCNOMAF)) like '%" + MesesVO.getNombreTrabajador().trim().toUpperCase()+ "%'";
			
		 
		 if(!"".equals(MesesVO.getCentid())&&MesesVO.getCentid()!=null)
			   sucursal=" AND PWCCSUCUR IN ('" + MesesVO.getCentid() + "')";
		 
		 if(!"".equals(MesesVO.getHolding())&&MesesVO.getHolding()!=null)
			   holding=" AND PWCCCDHOL IN (" + MesesVO.getHolding() + ")";
		 
		 if(!"".equals(MesesVO.getListaRut())&&MesesVO.getListaRut()!=null)
			   listaRut=" AND PWCCRUTAF IN (" + MesesVO.getListaRut() + ") ";
			   else
			   if(!"".equals(MesesVO.getRutTrabajador())&&MesesVO.getRutTrabajador()!=null)
					 rutaf=" AND PWCCRUTAF = " + MesesVO.getRutTrabajador() + "";
		 
		 String consulta=" where PWCCRUTEM IN (" + MesesVO.getRutEmpresa()+ ")  and PWCCCONVE IN('" + MesesVO.getConvenio()+ 
		 "') " + sucursal +  listaRut  + rutaf + nomTrabajador;
		
		 
		 
		 if("T".equals(MesesVO.getTipoProceso()))
		  {   sqlTodos="select distinct(pwccrutaf) as PWCCRUTAF,PWCCRAZSO, PWCCRUTEM, PWCCNOMAF ,PWCCAPEAF,PWCCDIGEM,PWCCDIGAF,PWCCCONVE,PWCCSUCUR, 'R' AS PWCCTIPO" + 
			  ",PWCCCDHOL from  " + ESQUEMA + ".PWF6000 "
			  + consulta + holding + " UNION SELECT  distinct(pwccrutaf) as PWCCRUTAF,PWCCRAZSO, PWCCRUTEM, PWCCNOMAF , " +
			 " PWCCAPEAF,PWCCDIGEM,PWCCDIGAF,PWCCCONVE,PWCCSUCUR, 'G' AS PWCCTIPO, PWCCCDHOL from " + ESQUEMA + ".PWF6001 "
			  + consulta + " UNION SELECT distinct(pwccrutaf) as PWCCRUTAF,PWCCRAZSO, PWCCRUTEM, PWCCNOMAF ,PWCCAPEAF,PWCCDIGEM,PWCCDIGAF,PWCCCONVE,PWCCSUCUR, 'D' AS PWCCTIPO" +
			 ",PWCCCDHOL from "+ ESQUEMA + ".PWF6002  " + consulta + " UNION SELECT  distinct(pwccrutaf) as PWCCRUTAF,PWCCRAZSO, PWCCRUTEM, PWCCNOMAF , " +
			 " PWCCAPEAF,PWCCDIGEM,PWCCDIGAF,PWCCCONVE,PWCCSUCUR, 'S' AS PWCCTIPO, PWCCCDHOL from " + ESQUEMA + ".PWF6006 "
			  + consulta +  " ORDER BY PWCCRUTEM,PWCCRUTAF fetch first 200 rows only";
		  
		  }
		  else if("R".equals(MesesVO.getTipoProceso()))
		   table=" " + ESQUEMA + ".PWF6000 ";
		  else if("L".equals(MesesVO.getTipoProceso()))
			   table=" " + ESQUEMA + ".PWF6005 ";
		  
		  else if("G".equals(MesesVO.getTipoProceso()))
		  {
			  table=" " + ESQUEMA + ".PWF6001 ";
			  holding="";
		  }
		 else if("D".equals(MesesVO.getTipoProceso()))
		 { table=" " + ESQUEMA + ".PWF6002 ";
		 holding="";
		 }else if("S".equals(MesesVO.getTipoProceso())){ 
			 table=" " + ESQUEMA + ".PWF6006 ";
			 holding="";
		  } 
		  
		 sql= "select distinct(pwccrutaf) as PWCCRUTAF,PWCCRAZSO, PWCCRUTEM, PWCCNOMAF ,PWCCAPEAF,PWCCDIGEM,PWCCDIGAF,PWCCCONVE,PWCCSUCUR, '" + MesesVO.getTipoProceso()+ "' as PWCCTIPO " +
		 " ,PWCCCDHOL from  "  + table + "  where PWCCRUTEM IN (" + MesesVO.getRutEmpresa()+ ")  and PWCCCONVE IN('" + MesesVO.getConvenio()+ 
		 "') " + sucursal +  listaRut  + holding +  rutaf + nomTrabajador 
		 + " ORDER BY PWCCRUTEM,PWCCRUTAF fetch first 200 rows only";
	      
		 if("T".equals(MesesVO.getTipoProceso()))
			 SQL=sqlTodos;
			
			 else
				 SQL=sql;
		 System.out.println(">>Certificado, sql descarga " + SQL);
			 
		 PreparedStatement press=conexion.prepareStatement(SQL);
		 rs=press.executeQuery();
		if(rs.next()){
		 do{
			 
			 MesesbeanVO omeses=new MesesbeanVO();   
			 omeses.setCentid(rs.getString("PWCCSUCUR"));
			 omeses.setRutEmpresa(rs.getString("PWCCRUTEM"));
			 omeses.setNombreApellidos(rs.getString("PWCCNOMAF").trim() + " " + rs.getString("PWCCAPEAF").trim());
			 omeses.setRutTrabajador(rs.getString("PWCCRUTAF").trim());
			 omeses.setNombreEmpresa(rs.getString("PWCCRAZSO").trim());
			 omeses.setDigem(rs.getString("PWCCDIGEM"));
			 omeses.setDigaf(rs.getString("PWCCDIGAF"));
			 omeses.setConvenio(rs.getString("PWCCCONVE"));
			 omeses.setPWCCTIPO(rs.getString("PWCCTIPO"));
			 omeses.setMes(MesesVO.getMes());
			 omeses.setHolding(rs.getString("PWCCCDHOL"));
			 
			 lista.add(omeses.getRutEmpresa());
			 lista.add(omeses.getNombreApellidos());
			 lista.add(omeses.getRutTrabajador());
			 lista.add(omeses.getCentid());
			 lista.add(omeses.getNombreEmpresa());
			 lista.add(omeses.getDigaf());
			 lista.add(omeses.getDigem());
			 lista.add(omeses.getConvenio());
			 lista.add(omeses.getMes());
			 lista.add(omeses.getPWCCTIPO());
			 lista.add(omeses.getHolding());
			 
			 
			 
			
			 
			
		 }while(rs.next());
			
		 
		 }
		else
			return null;
		
		conexion.close();
		return lista;
		 
		
		 }catch(SQLException ex)
		 {
			 ex.printStackTrace();
			 
		 }
		 finally{
			 try{
			 if(conexion!=null)
				 conexion.close();
			 }catch(Exception ignored)
			 {}
			 }
	 
	 return null;
}
 
public List getDatosDescarga2(MesesbeanVO MesesVO ){
	 
	 List lista=new ArrayList();
	  
	 
		String rutaf=""; 
		String sucursal="";
		String sql;
		String table="";
		String listaRut="";
		String nomTrabajador="";
		String holding="";
		String sqlTodos="";
		String SQL="";
		
		
		 Connection conexion=null;
		 ResultSet rs=null;
		  
		 
		 try{
		 
			 
			 
		conexion=this.coneccionDatasource();  
		
		
		 if(!"".equals(MesesVO.getNombreTrabajador())&&MesesVO.getNombreTrabajador()!=null)
			 nomTrabajador=" AND UPPER(trim(PWCCAPEAF)) || ' ' || UPPER(trim(PWCCNOMAF)) like '%" + MesesVO.getNombreTrabajador().trim().toUpperCase()+ "%'";
			
		 
		 if(!"".equals(MesesVO.getCentid())&&MesesVO.getCentid()!=null)
			   sucursal=" AND PWCCSUCUR IN ('" + MesesVO.getCentid() + "')";
		 
		 if(!"".equals(MesesVO.getHolding())&&MesesVO.getHolding()!=null)
			   holding=" AND PWCCCDHOL IN (" + MesesVO.getHolding() + ")";
		 
		 if(!"".equals(MesesVO.getListaRut())&&MesesVO.getListaRut()!=null)
			   listaRut=" AND PWCCRUTAF IN (" + MesesVO.getListaRut() + ") ";
			   else
			   if(!"".equals(MesesVO.getRutTrabajador())&&MesesVO.getRutTrabajador()!=null)
					 rutaf=" AND PWCCRUTAF = " + MesesVO.getRutTrabajador() + "";
		 
		 String consulta=" where PWCCRUTEM IN (" + MesesVO.getRutEmpresa()+ ")  and PWCCCONVE IN('" + MesesVO.getConvenio()+ 
		 "') " + sucursal +  listaRut  + rutaf + nomTrabajador
		 + " AND PWDCANORE * 100 + INT(PWDCMESRE) BETWEEN " + MesesVO.getFecha1() + " AND " + MesesVO.getFecha2();
		
		 
		 //clillo 17-07-2014 Se elimina del join el holding
		  if("T".equals(MesesVO.getTipoProceso()))
		  {   sqlTodos="select distinct(pwccrutaf) as PWCCRUTAF,PWCCRAZSO, PWCCRUTEM, PWCCNOMAF ,PWCCAPEAF,PWCCDIGEM,PWCCDIGAF,PWCCCONVE,PWCCSUCUR, 'R' AS PWCCTIPO" + 
			  ",PWCCCDHOL from  " + ESQUEMA + ".PWF6000 JOIN " + ESQUEMA + ".PWF6100 "
			  + " ON PWCCRUTEM = PWDCRUTEM "
			  + " AND PWCCRUTAF = PWDCRUTAF "
			  //+ " AND PWCCCDHOL = PWDCCDHOL "
			  + " AND PWCCCONVE = PWDCCONVE "
			  + consulta + holding + " UNION SELECT  distinct(pwccrutaf) as PWCCRUTAF,PWCCRAZSO, PWCCRUTEM, PWCCNOMAF , " +
			  " PWCCAPEAF,PWCCDIGEM,PWCCDIGAF,PWCCCONVE,PWCCSUCUR, 'G' AS PWCCTIPO, PWCCCDHOL from " + ESQUEMA + ".PWF6001 JOIN " + ESQUEMA + ".PWF6101 "
			  + " ON PWCCRUTEM = PWDCRUTEM "
			  + " AND PWCCRUTAF = PWDCRUTAF "
			  //+ " AND PWCCCDHOL = PWDCCDHOL "
			  + " AND PWCCCONVE = PWDCCONVE "
			  + consulta + " UNION SELECT  distinct(pwccrutaf) as PWCCRUTAF,PWCCRAZSO, PWCCRUTEM, PWCCNOMAF , " +
			  " PWCCAPEAF,PWCCDIGEM,PWCCDIGAF,PWCCCONVE,PWCCSUCUR, 'L' AS PWCCTIPO, PWCCCDHOL from " + ESQUEMA + ".PWF6005 JOIN " + ESQUEMA + ".PWF6105 "
			  + " ON PWCCRUTEM = PWDCRUTEM "
			  + " AND PWCCRUTAF = PWDCRUTAF "
			  //+ " AND PWCCCDHOL = PWDCCDHOL "
			  + " AND PWCCCONVE = PWDCCONVE "
			  + consulta + " UNION SELECT distinct(pwccrutaf) as PWCCRUTAF,PWCCRAZSO, PWCCRUTEM, PWCCNOMAF ,PWCCAPEAF,PWCCDIGEM,PWCCDIGAF,PWCCCONVE,PWCCSUCUR, 'D' AS PWCCTIPO" +
			  ",PWCCCDHOL from "+ ESQUEMA + ".PWF6002 JOIN " + ESQUEMA+ ".PWF6102 "
			  + " ON PWCCRUTEM = PWDCRUTEM "
			  + " AND PWCCRUTAF = PWDCRUTAF "
			  + " AND PWCCCDHOL = PWDCCDHOL "
			  + " AND PWCCCONVE = PWDCCONVE "
			  + consulta + " UNION SELECT distinct(pwccrutaf) as PWCCRUTAF,PWCCRAZSO, PWCCRUTEM, PWCCNOMAF ,PWCCAPEAF,PWCCDIGEM,PWCCDIGAF,PWCCCONVE,PWCCSUCUR, 'S' AS PWCCTIPO" +
			  ",PWCCCDHOL from "+ ESQUEMA + ".PWF6006 JOIN " + ESQUEMA+ ".PWF6106 "
			  + " ON PWCCRUTEM = PWDCRUTEM "
			  + " AND PWCCRUTAF = PWDCRUTAF "
			  //+ " AND PWCCCDHOL = PWDCCDHOL "
			  + " AND PWCCCONVE = PWDCCONVE "
			  + consulta + " ORDER BY PWCCRUTEM,PWCCRUTAF fetch first 200 rows only";
		  
		  }
		  else if("R".equals(MesesVO.getTipoProceso()))
			  table=" " + ESQUEMA + ".PWF6000 JOIN" + ESQUEMA + ".PWF6100 "
			  + " ON PWCCRUTEM = PWDCRUTEM "
			  + " AND PWCCRUTAF = PWDCRUTAF "
			  //+ " AND PWCCCDHOL = PWDCCDHOL "
			  + " AND PWCCCONVE = PWDCCONVE ";
		  else if("L".equals(MesesVO.getTipoProceso()))
			   table=" " + ESQUEMA + ".PWF6005 JOIN" + ESQUEMA + ".PWF6105 "
			   + " ON PWCCRUTEM = PWDCRUTEM "
			   + " AND PWCCRUTAF = PWDCRUTAF "
			   //+ " AND PWCCCDHOL = PWDCCDHOL "
			   + " AND PWCCCONVE = PWDCCONVE ";
		  
		  else if("G".equals(MesesVO.getTipoProceso()))
		  {
			  table=" " + ESQUEMA + ".PWF6001 JOIN" + ESQUEMA + ".PWF6101 "
			  + " ON PWCCRUTEM = PWDCRUTEM "
			  + " AND PWCCRUTAF = PWDCRUTAF "
			  //+ " AND PWCCCDHOL = PWDCCDHOL "
			  + " AND PWCCCONVE = PWDCCONVE ";
			  holding="";
		  }
		 else if("D".equals(MesesVO.getTipoProceso()))
		 { 	table=" " + ESQUEMA + ".PWF6002 JOIN" + ESQUEMA + ".PWF6102 "
			+ " ON PWCCRUTEM = PWDCRUTEM "
			+ " AND PWCCRUTAF = PWDCRUTAF "
			//+ " AND PWCCCDHOL = PWDCCDHOL "
			+ " AND PWCCCONVE = PWDCCONVE ";
		 	holding="";
		 }
		 else if("S".equals(MesesVO.getTipoProceso()))
		 { 	table=" " + ESQUEMA + ".PWF6006 JOIN" + ESQUEMA + ".PWF6106 "
			+ " ON PWCCRUTEM = PWDCRUTEM "
			+ " AND PWCCRUTAF = PWDCRUTAF "
			//+ " AND PWCCCDHOL = PWDCCDHOL "
			+ " AND PWCCCONVE = PWDCCONVE ";
		 	holding="";
		 } 
		  
		 sql= "select distinct(pwccrutaf) as PWCCRUTAF,PWCCRAZSO, PWCCRUTEM, PWCCNOMAF ,PWCCAPEAF,PWCCDIGEM,PWCCDIGAF,PWCCCONVE,PWCCSUCUR, '" + MesesVO.getTipoProceso()+ "' as PWCCTIPO " +
		 " ,PWCCCDHOL from  "  + table + "  where PWCCRUTEM IN (" + MesesVO.getRutEmpresa()+ ")  and PWCCCONVE IN('" + MesesVO.getConvenio()+ 
		 "') " + sucursal +  listaRut  + holding +  rutaf + nomTrabajador 
		 + " ORDER BY PWCCRUTEM,PWCCRUTAF fetch first 200 rows only";
	      
		 if("T".equals(MesesVO.getTipoProceso()))
			 SQL=sqlTodos;
			
		 else
			 SQL=sql;
		 System.out.println(">>Certificado, sql descarga " + SQL);
			 
		 PreparedStatement press=conexion.prepareStatement(SQL);
		 rs=press.executeQuery();
		 if(rs.next()){
			 do{
			 
				 MesesbeanVO omeses=new MesesbeanVO();   
				 omeses.setCentid(rs.getString("PWCCSUCUR"));
				 omeses.setRutEmpresa(rs.getString("PWCCRUTEM"));
				 omeses.setNombreApellidos(rs.getString("PWCCNOMAF").trim() + " " + rs.getString("PWCCAPEAF").trim());
				 omeses.setRutTrabajador(rs.getString("PWCCRUTAF").trim());
				 omeses.setNombreEmpresa(rs.getString("PWCCRAZSO").trim());
				 omeses.setDigem(rs.getString("PWCCDIGEM"));
				 omeses.setDigaf(rs.getString("PWCCDIGAF"));
				 omeses.setConvenio(rs.getString("PWCCCONVE"));
				 omeses.setPWCCTIPO(rs.getString("PWCCTIPO"));
				 omeses.setHolding(rs.getString("PWCCCDHOL"));
				 omeses.setMes(MesesVO.getMes());
				 omeses.setFecha1(MesesVO.getFecha1());
				 omeses.setFecha2(MesesVO.getFecha2());
				 
				 lista.add(omeses.getRutEmpresa());
				 lista.add(omeses.getNombreApellidos());
				 lista.add(omeses.getRutTrabajador());
				 lista.add(omeses.getCentid());
				 lista.add(omeses.getNombreEmpresa());
				 lista.add(omeses.getDigaf());
				 lista.add(omeses.getDigem());
				 lista.add(omeses.getConvenio());
				 lista.add(omeses.getMes());
				 lista.add(omeses.getPWCCTIPO());
				 lista.add(omeses.getHolding());
				 lista.add(omeses.getFecha1());
				 lista.add(omeses.getFecha2());
			 
			 
			 
			
			 
			
			 }while(rs.next());
			
		 
		}
		else
			return null;
		
		conexion.close();
		return lista;
		 
		
		 }catch(SQLException ex)
		 {
			 ex.printStackTrace();
			 
		 }
		 finally{
			 try{
			 if(conexion!=null)
				 conexion.close();
			 }catch(Exception ignored)
			 {}
			 }
	 
	 return null;
} 
 
public List getDatos(MesesbeanVO MesesVO ){
	 
	 List lista=new ArrayList();
	  
	 
	 String rutaf=""; 
	 String sucursal="";
	 String sql;
	 String table="";
	 String listaRut="";
	 String nomTrabajador="";
	 String holding="";
	 String sqlTodos="";
	 String SQL="";
	 String SQLR="";
		
		
	 Connection conexion=null;
		  
			  
	 ResultSet rs=null;
			
	 try{
		
		   
		 conexion=this.coneccionDatasource();
		
		 
		
		 if(!"".equals(MesesVO.getNombreTrabajador())&&MesesVO.getNombreTrabajador()!=null)
			 nomTrabajador=" AND UPPER(trim(PWCCAPEAF)) || ' ' || UPPER(trim(PWCCNOMAF)) like '%" + MesesVO.getNombreTrabajador().trim().toUpperCase()+ "%'";
			
		 
		 if(!"".equals(MesesVO.getCentid())&&MesesVO.getCentid()!=null)
			 sucursal=" AND PWCCSUCUR IN ('" + MesesVO.getCentid() + "')";
		 
		 if(!"".equals(MesesVO.getHolding())&&MesesVO.getHolding()!=null)
			 holding=" AND PWCCCDHOL IN (" + MesesVO.getHolding() + ")";
		 
		 if(!"".equals(MesesVO.getListaRut())&&MesesVO.getListaRut()!=null)
			 listaRut=" AND PWCCRUTAF IN (" + MesesVO.getListaRut() + ") ";
		 else
			 if(!"".equals(MesesVO.getRutTrabajador())&&MesesVO.getRutTrabajador()!=null)
				 rutaf=" AND PWCCRUTAF = " + MesesVO.getRutTrabajador() + "";
		 
		  
		 		String consulta=" where PWCCRUTEM IN (" + MesesVO.getRutEmpresa()+ ")  and PWCCCONVE IN('" + MesesVO.getConvenio()+ 
		 		"') " + sucursal +  listaRut  + holding + rutaf + nomTrabajador;
		
		 
		 
		 
		 		if("T".equals(MesesVO.getTipoProceso())){
			 		sqlTodos="select distinct(pwccrutaf) as PWCCRUTAF,PWCCRAZSO, PWCCRUTEM, PWCCNOMAF , " + 
			 				"PWCCAPEAF,PWCCDIGEM,PWCCDIGAF,PWCCCONVE,PWCCSUCUR, 'R' AS PWCCTIPO, PWCCCDHOL " 
			 				+ "from  " + ESQUEMA + ".PWF6000 " + consulta
			 		+ " UNION SELECT  distinct(pwccrutaf) as PWCCRUTAF,PWCCRAZSO, PWCCRUTEM, PWCCNOMAF , " +
			 				" PWCCAPEAF,PWCCDIGEM,PWCCDIGAF,PWCCCONVE,PWCCSUCUR, 'G' AS PWCCTIPO, PWCCCDHOL " + 
			 				"from " + ESQUEMA + ".PWF6001 " + consulta  
			 		+ " UNION SELECT  distinct(pwccrutaf) as PWCCRUTAF,PWCCRAZSO, PWCCRUTEM, PWCCNOMAF , " +
			 				" PWCCAPEAF,PWCCDIGEM,PWCCDIGAF,PWCCCONVE,PWCCSUCUR, 'L' AS PWCCTIPO, PWCCCDHOL " + 
			 				"from " + ESQUEMA + ".PWF6005 " + consulta  
			 		+ " UNION SELECT distinct(pwccrutaf) as PWCCRUTAF,PWCCRAZSO, PWCCRUTEM, PWCCNOMAF , " + 
			 				"PWCCAPEAF,PWCCDIGEM,PWCCDIGAF,PWCCCONVE,PWCCSUCUR, 'D' AS PWCCTIPO, PWCCCDHOL " + 
			 				"from " + ESQUEMA + ".PWF6002 " + consulta  
			 		+ " UNION SELECT  distinct(pwccrutaf) as PWCCRUTAF,PWCCRAZSO, PWCCRUTEM, PWCCNOMAF , " +
			 				" PWCCAPEAF,PWCCDIGEM,PWCCDIGAF,PWCCCONVE,PWCCSUCUR, 'S' AS PWCCTIPO, PWCCCDHOL " + 
			 				"from " + ESQUEMA + ".PWF6006 " + consulta 
			 		+ " ORDER BY PWCCRUTEM,PWCCRUTAF fetch first 200 rows only";
			 	}
		 	else if("R".equals(MesesVO.getTipoProceso()))
		 		table=" " + ESQUEMA + ".PWF6000 ";
		 	else if("L".equals(MesesVO.getTipoProceso()))
		 		table=" " + ESQUEMA + ".PWF6005 ";
		  
		  
		 
		 	else if("G".equals(MesesVO.getTipoProceso()))
		 		{table=" " + ESQUEMA + ".PWF6001 ";
		 		 holding="";
		 		}
		 
		 	else if("D".equals(MesesVO.getTipoProceso()))
		 		{ table=" " + ESQUEMA + ".PWF6002 ";
		 		  holding="";
		 		}
		 	else if("S".equals(MesesVO.getTipoProceso()))
			 { table=" " + ESQUEMA + ".PWF6006 ";
			 holding="";
			 }
		 
		 	sql= "select distinct(pwccrutaf) as PWCCRUTAF,PWCCRAZSO, PWCCRUTEM, PWCCNOMAF ,PWCCAPEAF,PWCCDIGEM,PWCCDIGAF,PWCCCONVE,PWCCSUCUR, '" + MesesVO.getTipoProceso()+ "' as PWCCTIPO " +
		 	", PWCCCDHOL from  "  + table + "  where PWCCRUTEM IN (" + MesesVO.getRutEmpresa()+ ")  and PWCCCONVE IN('" + MesesVO.getConvenio()+ 
		 	"') " + sucursal +  listaRut  + holding +  rutaf + nomTrabajador 
		 	+ " ORDER BY PWCCRUTEM,PWCCRUTAF fetch first 200 rows only";

		 	if("T".equals(MesesVO.getTipoProceso()))
		 		SQL=sqlTodos;
		 	else
		 		SQL=sql;
		 	
		 	System.out.println(">>Certificado, sql lista:" + SQL);
		 	 
		 	PreparedStatement press=conexion.prepareStatement(SQL);  
		 	rs=press.executeQuery();
		  
		 	if(rs.next()){
		 		do{
			 
		 			MesesbeanVO omeses=new MesesbeanVO();   
		 			omeses.setCentid(rs.getString("PWCCSUCUR"));
		 			omeses.setRutEmpresa(rs.getString("PWCCRUTEM"));
		 			omeses.setNombreApellidos(rs.getString("PWCCNOMAF").trim() + " " + rs.getString("PWCCAPEAF").trim());
		 			omeses.setRutTrabajador(rs.getString("PWCCRUTAF").trim());
		 			omeses.setNombreEmpresa(rs.getString("PWCCRAZSO").trim());
		 			omeses.setDigem(rs.getString("PWCCDIGEM"));
		 			omeses.setDigaf(rs.getString("PWCCDIGAF"));
		 			omeses.setConvenio(rs.getString("PWCCCONVE"));
		 			omeses.setPWCCTIPO(rs.getString("PWCCTIPO"));
		 			omeses.setHolding(rs.getString("PWCCCDHOL"));
		 			omeses.setMes(MesesVO.getMes());
		 			omeses.setTipoProceso(MesesVO.getTipoProceso());
		 			omeses.setFecha1("1");
			 
		 			lista.add(omeses.getRutEmpresa());
		 			lista.add(omeses.getNombreApellidos());
			 		lista.add(omeses.getRutTrabajador());
			 		lista.add(omeses.getCentid());
			 		lista.add(omeses.getNombreEmpresa());
			 		lista.add(omeses.getDigaf());
			 		lista.add(omeses.getDigem());
			 		lista.add(omeses.getConvenio());
			 		lista.add(omeses.getMes());
			 		lista.add(omeses.getTipoProceso());
			 		lista.add(omeses.getPWCCTIPO());
			 		lista.add(omeses.getConvenio());
			 		lista.add(omeses.getHolding());
			 		lista.add(omeses.getFecha1());
		 		}while(rs.next());
			
		 
		 	}
		 	else
		 		return null;
		
		 		conexion.close();
		 		return lista;
		 
		
	 }catch(SQLException ex)
	 	{
		 	ex.printStackTrace();
			 
		}
	 finally{
		 try{
			 if(conexion!=null)
				 conexion.close();
		 }catch(Exception ignored)
			 {}
			 }
	 
	 return null; 
}

public List getDatos2(MesesbeanVO MesesVO ){
	 
	 List lista=new ArrayList();
	  
	 
		String rutaf=""; 
		String sucursal="";
		String sql = "";
		String table="";
		String listaRut="";
		String nomTrabajador="";
		String holding="";
		String sqlTodos="";
		String SQL="";
		
		
		 Connection conexion=null;
		  
			  
			ResultSet rs=null;
			
		 try{
		
		   
		   conexion=this.coneccionDatasource();
		
		  
		
		 if(!"".equals(MesesVO.getNombreTrabajador())&&MesesVO.getNombreTrabajador()!=null)
			 nomTrabajador=" AND UPPER(trim(PWCCAPEAF)) || ' ' || UPPER(trim(PWCCNOMAF)) like '%" + MesesVO.getNombreTrabajador().trim().toUpperCase()+ "%'";
			
		 
		 if(!"".equals(MesesVO.getCentid())&&MesesVO.getCentid()!=null)
			   sucursal=" AND PWCCSUCUR IN ('" + MesesVO.getCentid() + "')";
		 
		 if(!"".equals(MesesVO.getHolding())&&MesesVO.getHolding()!=null)
			   holding=" AND PWCCCDHOL IN (" + MesesVO.getHolding() + ")";
		 
		 if(!"".equals(MesesVO.getListaRut())&&MesesVO.getListaRut()!=null)
			 listaRut=" AND PWCCRUTAF IN (" + MesesVO.getListaRut() + ") ";
		 else
			 if(!"".equals(MesesVO.getRutTrabajador())&&MesesVO.getRutTrabajador()!=null)
				 rutaf=" AND PWCCRUTAF = " + MesesVO.getRutTrabajador() + "";

		  
		String consulta=" where PWCCRUTEM IN (" + MesesVO.getRutEmpresa()+ ")  and PWCCCONVE IN('" + MesesVO.getConvenio()+ "') " 
		+ sucursal +  listaRut  + holding +  rutaf + nomTrabajador 
		 + " AND PWDCANORE * 100 + INT(PWDCMESRE) BETWEEN " + MesesVO.getFecha1() + " AND " + MesesVO.getFecha2();
		 
		 
		 
		 //clillo 17-07-2014 Se elimina del join el holding
		  if("T".equals(MesesVO.getTipoProceso())){
			  sqlTodos="select distinct(pwccrutaf) as PWCCRUTAF,PWCCRAZSO, PWCCRUTEM, PWCCNOMAF ,PWCCAPEAF,PWCCDIGEM,PWCCDIGAF,PWCCCONVE,PWCCSUCUR, 'R' AS PWCCTIPO" + 
			  ", PWCCCDHOL from  " + ESQUEMA + ".PWF6000 JOIN " + ESQUEMA + ".PWF6100 " 
			  + " ON PWCCRUTEM = PWDCRUTEM "
			  + " AND PWCCRUTAF = PWDCRUTAF "
			  //+ " AND PWCCCDHOL = PWDCCDHOL "
			  + " AND PWCCCONVE = PWDCCONVE "
			  + consulta + " UNION SELECT  distinct(pwccrutaf) as PWCCRUTAF,PWCCRAZSO, PWCCRUTEM, PWCCNOMAF , " +
			 " PWCCAPEAF,PWCCDIGEM,PWCCDIGAF,PWCCCONVE,PWCCSUCUR, 'G' AS PWCCTIPO, PWCCCDHOL from " + ESQUEMA + ".PWF6001 JOIN " + ESQUEMA + ".PWF6101 "
			  + " ON PWCCRUTEM = PWDCRUTEM "
			  + " AND PWCCRUTAF = PWDCRUTAF "
			  //+ " AND PWCCCDHOL = PWDCCDHOL "
			  + " AND PWCCCONVE = PWDCCONVE "
			  + consulta + " UNION SELECT  distinct(pwccrutaf) as PWCCRUTAF,PWCCRAZSO, PWCCRUTEM, PWCCNOMAF , " +
			  " PWCCAPEAF,PWCCDIGEM,PWCCDIGAF,PWCCCONVE,PWCCSUCUR, 'L' AS PWCCTIPO, PWCCCDHOL from " + ESQUEMA + ".PWF6005 JOIN " + ESQUEMA + ".PWF6105 "
			  + " ON PWCCRUTEM = PWDCRUTEM "
			  + " AND PWCCRUTAF = PWDCRUTAF "
			  //+ " AND PWCCCDHOL = PWDCCDHOL "
			  + " AND PWCCCONVE = PWDCCONVE "
			  	
			  + consulta + " UNION SELECT distinct(pwccrutaf) as PWCCRUTAF,PWCCRAZSO, PWCCRUTEM, PWCCNOMAF ,PWCCAPEAF,PWCCDIGEM,PWCCDIGAF,PWCCCONVE,PWCCSUCUR, 'D' AS PWCCTIPO" +
			  ", PWCCCDHOL from " + ESQUEMA+ ".PWF6002 JOIN " + ESQUEMA+ ".PWF6102 " 
			  + " ON PWCCRUTEM = PWDCRUTEM "
			  + " AND PWCCRUTAF = PWDCRUTAF "
			  //+ " AND PWCCCDHOL = PWDCCDHOL "
			  + " AND PWCCCONVE = PWDCCONVE "
			  + consulta + " UNION SELECT distinct(pwccrutaf) as PWCCRUTAF,PWCCRAZSO, PWCCRUTEM, PWCCNOMAF ,PWCCAPEAF,PWCCDIGEM,PWCCDIGAF,PWCCCONVE,PWCCSUCUR, 'S' AS PWCCTIPO" +
			  ", PWCCCDHOL from " + ESQUEMA+ ".PWF6006 JOIN " + ESQUEMA+ ".PWF6106 " 
			  + " ON PWCCRUTEM = PWDCRUTEM "
			  + " AND PWCCRUTAF = PWDCRUTAF "
			  //+ " AND PWCCCDHOL = PWDCCDHOL "
			  + " AND PWCCCONVE = PWDCCONVE "
			  
			  //movimiento personal
			  + consulta + " UNION SELECT distinct(pwccrutaf) as PWCCRUTAF,PWCCRAZSO, PWCCRUTEM, PWCCNOMAF ,PWCCAPEAF,PWCCDIGEM,PWCCDIGAF,PWCCCONVE,PWCCSUCUR, 'R' AS PWCCTIPO" +
			  ", PWCCCDHOL from " + ESQUEMA+ ".PWF6000 JOIN " + ESQUEMA+ ".PWF6104 " 
			  + " ON PWCCRUTEM = PWDCRUTEM "
			  + " AND PWCCRUTAF = PWDCRUTAF "
			  //+ " AND PWCCCDHOL = PWDCCDHOL "
			  + " AND PWCCCONVE = PWDCCONVE "
			  
			  + consulta + " ORDER BY PWCCRUTEM,PWCCRUTAF fetch first 200 rows only";
		  }
		  else{
			  if("R".equals(MesesVO.getTipoProceso()))
			  table=" " + ESQUEMA + ".PWF6000 JOIN " + ESQUEMA + ".PWF6100 "
			  + " ON PWCCRUTEM = PWDCRUTEM "
			  + " AND PWCCRUTAF = PWDCRUTAF "
			  //+ " AND PWCCCDHOL = PWDCCDHOL "
			  + " AND PWCCCONVE = PWDCCONVE ";
		  
		  else if("L".equals(MesesVO.getTipoProceso()))
			  table=" " + ESQUEMA + ".PWF6005 JOIN " + ESQUEMA + ".PWF6105 "
			  + " ON PWCCRUTEM = PWDCRUTEM "
			  + " AND PWCCRUTAF = PWDCRUTAF "
			  //+ " AND PWCCCDHOL = PWDCCDHOL "
			  + " AND PWCCCONVE = PWDCCONVE ";
		  
		 
		 else if("G".equals(MesesVO.getTipoProceso()))
		 	{table=" " + ESQUEMA + ".PWF6001 JOIN " + ESQUEMA + ".PWF6101 "
			 + " ON PWCCRUTEM = PWDCRUTEM "
			 + " AND PWCCRUTAF = PWDCRUTAF "
			 //+ " AND PWCCCDHOL = PWDCCDHOL "
			 + " AND PWCCCONVE = PWDCCONVE ";
		 	 holding="";
		 	}
		 
		 else if("D".equals(MesesVO.getTipoProceso()))
		 { table=" " + ESQUEMA + ".PWF6002 JOIN " + ESQUEMA + ".PWF6102 "
			 + " ON PWCCRUTEM = PWDCRUTEM "
			 + " AND PWCCRUTAF = PWDCRUTAF "
			 //+ " AND PWCCCDHOL = PWDCCDHOL "
			 + " AND PWCCCONVE = PWDCCONVE ";
		 holding="";
		 }
		 else if("S".equals(MesesVO.getTipoProceso()))
		 { table=" " + ESQUEMA + ".PWF6006 JOIN " + ESQUEMA + ".PWF6106 "
			 + " ON PWCCRUTEM = PWDCRUTEM "
			 + " AND PWCCRUTAF = PWDCRUTAF "
			 //+ " AND PWCCCDHOL = PWDCCDHOL "
			 + " AND PWCCCONVE = PWDCCONVE ";
		 holding="";
		 }
			  
			  sql= "select distinct(pwccrutaf) as PWCCRUTAF,PWCCRAZSO, PWCCRUTEM, PWCCNOMAF ,PWCCAPEAF,PWCCDIGEM,PWCCDIGAF,PWCCCONVE,PWCCSUCUR, '" + MesesVO.getTipoProceso()+ "' as PWCCTIPO " +
				 ", PWCCCDHOL from  "  + table + "  where PWCCRUTEM IN (" + MesesVO.getRutEmpresa()+ ")  and PWCCCONVE IN('" + MesesVO.getConvenio()+ 
			 "') " + sucursal +  listaRut  +  rutaf + nomTrabajador 
			 + " AND PWDCANORE * 100 + INT(PWDCMESRE) BETWEEN " + MesesVO.getFecha1() + " AND " + MesesVO.getFecha2()
			 //clillo 17-07-2014 Se agrega order y fetch omitidos en REQ-7300
			 + " ORDER BY PWCCRUTEM,PWCCRUTAF fetch first 200 rows only";
			  
		 //sql= "select distinct(pwccrutaf) as PWCCRUTAF,PWCCRAZSO, PWCCRUTEM, PWCCNOMAF ,PWCCAPEAF,PWCCDIGEM,PWCCDIGAF,PWCCCONVE,PWCCSUCUR, '" + MesesVO.getTipoProceso()+ "' as PWCCTIPO " +
		 //", PWCCCDHOL from  "  + table + "  where PWCCRUTEM IN ('" + MesesVO.getRutEmpresa()+ "')  and PWCCCONVE IN('" + MesesVO.getConvenio()+ 
		 //"') " + sucursal +  listaRut  + holding +  rutaf + nomTrabajador + " fetch first 200 rows only";
		 }
		 if("T".equals(MesesVO.getTipoProceso()))
		 SQL=sqlTodos;
		 else if ("R".equals(MesesVO.getTipoProceso())) {
			 SQL=" select distinct(pwccrutaf) as PWCCRUTAF,PWCCRAZSO, PWCCRUTEM, PWCCNOMAF ,PWCCAPEAF,PWCCDIGEM,PWCCDIGAF,PWCCCONVE,PWCCSUCUR, '" + MesesVO.getTipoProceso()+ "' as PWCCTIPO " +
			 ", PWCCCDHOL from  " + ESQUEMA + ".PWF6000 JOIN " + ESQUEMA+ ".PWF6104 " 
			 + " ON PWCCRUTEM = PWDCRUTEM "
			 + " AND PWCCRUTAF = PWDCRUTAF "
			 //+ " AND PWCCCDHOL = PWDCCDHOL "
			 + " AND PWCCCONVE = PWDCCONVE where PWCCRUTEM IN (" + MesesVO.getRutEmpresa()+ ")  and PWCCCONVE IN('" + MesesVO.getConvenio()+ 
			 "') " + sucursal +  listaRut  + holding +  rutaf + nomTrabajador  
			 + " AND PWDCANORE * 100 + INT(PWDCMESRE) BETWEEN " + MesesVO.getFecha1() + " AND " + MesesVO.getFecha2()
			 + " union " + sql;
			 
			 //SQL=sql;
		 }else
		 {
			 SQL=sql;
		 }
		System.out.println("Certificado, consulta: "+ SQL);
		 
	      PreparedStatement press=conexion.prepareStatement(SQL);  
		  rs=press.executeQuery();
		  
		if(rs.next()){
		 do{
			 
			 MesesbeanVO omeses=new MesesbeanVO();   
			 omeses.setCentid(rs.getString("PWCCSUCUR"));
			 omeses.setRutEmpresa(rs.getString("PWCCRUTEM"));
			 omeses.setNombreApellidos(rs.getString("PWCCNOMAF").trim() + " " + rs.getString("PWCCAPEAF").trim());
			 omeses.setRutTrabajador(rs.getString("PWCCRUTAF").trim());
			 omeses.setNombreEmpresa(rs.getString("PWCCRAZSO").trim());
			 omeses.setDigem(rs.getString("PWCCDIGEM"));
			 omeses.setDigaf(rs.getString("PWCCDIGAF"));
			 omeses.setConvenio(rs.getString("PWCCCONVE"));
			 omeses.setPWCCTIPO(rs.getString("PWCCTIPO"));
			 omeses.setHolding(rs.getString("PWCCCDHOL"));
			 omeses.setMes(MesesVO.getMes());
			 omeses.setTipoProceso(MesesVO.getTipoProceso());
			 omeses.setFecha1(MesesVO.getFecha1());
			 omeses.setFecha2(MesesVO.getFecha2());
			 
			 lista.add(omeses.getRutEmpresa());
			 lista.add(omeses.getNombreApellidos());
			 lista.add(omeses.getRutTrabajador());
			 lista.add(omeses.getCentid());
			 lista.add(omeses.getNombreEmpresa());
			 lista.add(omeses.getDigaf());
			 lista.add(omeses.getDigem());
			 lista.add(omeses.getConvenio());
			 lista.add(omeses.getMes());
			 lista.add(omeses.getTipoProceso());
			 lista.add(omeses.getPWCCTIPO());
			 lista.add(omeses.getConvenio());
			 lista.add(omeses.getHolding());
			 lista.add(omeses.getFecha1());
			 lista.add(omeses.getFecha2());
			 
			
			 
			
		 }while(rs.next());
			
		 
		 }
		else
			return null;
		
		conexion.close();
		return lista;
		 
		
		 }catch(SQLException ex)
		 {
			 ex.printStackTrace();
			 
		 }
		 finally{
			 try{
			 if(conexion!=null)
				 conexion.close();
			 }catch(Exception ignored)
			 {}
			 }
	 
	 return null; 
}

public Integer fechaHasta(String tipoProceso, String rutaf, String rutem, Connection con){
	
	 
	String tabla="";
	if("R".equals(tipoProceso))
		tabla=" " + ESQUEMA + " .PWF6000 ";
	else if("G".equals(tipoProceso))
		tabla=" " + ESQUEMA + " .PWF6001 ";
	else if("D".equals(tipoProceso))
		tabla=" " + ESQUEMA + " .PWF6002 ";
	else if("L".equals(tipoProceso))
		tabla=" " + ESQUEMA + " .PWF6005 ";
	else if("S".equals(tipoProceso))
		tabla=" " + ESQUEMA + " .PWF6006 ";
	else
		tabla=null;
	
	
	Integer fecha=null;
	try{
		
		ResultSet rs=null;
		String sql="select max(PWCCCOPRO) from " + tabla + "where PWCCRUTAF=" + rutaf + 
		" and PWCCRUTEM=" + rutem ;
		
		System.out.println("Certificado, Periodo Hasta>>" + sql); 
		
		CallableStatement press=con.prepareCall(sql);  
		rs=press.executeQuery();
		if(rs.next())
			fecha=new Integer(rs.getString(1));
		else 
			return null;
		//con.close();
	}catch(Exception ex)
	{ex.printStackTrace();}
	 /* finally{
		 try{
		 if(con!=null)
			 con.close();
		 }catch(Exception ignored)
		 {}
		 } */
	return fecha;
}


public Integer fechaEmision(String tipoProceso, String rutaf, String rutem,String convenio){
	
	Connection conexion=this.coneccionDatasource();
	
	 /*CÓDIGO TEMPORAL.. YA QUE NO FUNCIONA EL RESCATE DEL JNDI EN WAS 6.1
	 try{
			Class.forName("com.ibm.as400.access.AS400JDBCDriver");
			 
			conexion=DriverManager.getConnection("jdbc:as400://146.83.1.32/PWDTAD;user=QONDADM;password=QONDADM");
	 }catch(Exception e){}
	 END TEMPORAL CODE*/
	 
	String tabla="";
	if("R".equals(tipoProceso))
		tabla=" " + ESQUEMA + ".PWF6000 ";
	else if("L".equals(tipoProceso))
		tabla=" " + ESQUEMA + ".PWF6005 ";
	else if("G".equals(tipoProceso))
		tabla=" " + ESQUEMA + ".PWF6001 ";
	else if("D".equals(tipoProceso))
		tabla=" " + ESQUEMA + " .PWF6002 ";
	else if("S".equals(tipoProceso))
		tabla=" " + ESQUEMA + " .PWF6006 ";
	else
		tabla=null;
	
	
	Integer fecpa=null;
	try{
		
		ResultSet rs=null;
		String sql="select PWCCFECEM from " + tabla + "where PWCCRUTAF=" + rutaf + 
		" and PWCCRUTEM=" + rutem + " and PWCCCONVE="+convenio;
		
		System.out.println(">>Certificado, query fecha emisión:"+sql);  
		
		CallableStatement press=conexion.prepareCall(sql);  
		rs=press.executeQuery();
		if(rs.next())
			fecpa=new Integer(rs.getString("PWCCFECEM"));
		else
			return null;
		conexion.close();
	}catch(Exception ex)
	{ex.printStackTrace();}
	  finally{
		 try{
		 if(conexion!=null)
			 conexion.close();
		 }catch(Exception ignored)
		 {}
		 }
	return fecpa;
}
  
}

