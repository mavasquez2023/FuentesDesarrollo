package cl.araucana.ctasfam.integration.jdbc.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.sql.Types;

import com.ibm.websphere.ce.cm.ObjectClosedException;

import cl.araucana.core.registry.Enterprise;
import cl.araucana.ctasfam.business.to.AfiliadosPropuestaTO;
import cl.araucana.ctasfam.business.to.AfiliadosTO;
import cl.araucana.ctasfam.business.to.EstadisticaProcesoTO;
import cl.araucana.ctasfam.business.to.EtapasTO;
import cl.araucana.ctasfam.business.to.FlujoTO;
import cl.araucana.ctasfam.business.to.HoldingafiliadosTO;
import cl.araucana.ctasfam.business.to.RentaproTO;
import cl.araucana.ctasfam.presentation.struts.vo.AfiliadosPropuesta;
import cl.araucana.ctasfam.resources.util.CustomDataSource;
import cl.araucana.ctasfam.resources.util.Utils;
 
 

 

public class AraucanaJdbcDao {
	
	  
	private static Properties Config = null;
	private static CallableStatement ctmt = null;
	
	public AraucanaJdbcDao()
	{
		if(Config == null)
		{
			Config = new Properties();
	  		
		  	try{
		  		Config.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
	  		}catch(Exception e){
	  			System.out.println(e.getMessage());
	  			e.printStackTrace();
	  		}
		}
	}
	
public String[] properties(){
	
	String resu[]=new String[5];
	 try{
		// Properties Param = new Properties();
		// Param.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
	resu[0]=Config.getProperty("EmpNormal");
	resu[1]=Config.getProperty("EmpNormalA");
	resu[2]=Config.getProperty("EmpHolding");
	resu[3]=Config.getProperty("EmpHoldingA");
	resu[4]=Config.getProperty("PERIODO");
	
	 
	 }catch(Exception ex)
		 {ex.printStackTrace();}

		 return resu;
}


	private static CallableStatement ctmtSelect = null;
	
	public String[]  existeAfiliado(String periodo, String rutemp, String rutaf){
		
	 //--alexis advise 15-06-2012--//
		
		CustomDataSource cds = new CustomDataSource();
		
	    Connection conexion = null;
	    ResultSet result=null;
	    String valor[]=new String[3];

		try{
			
	    	cds.openConnection();	    	
	    	conexion = cds.getConnection();	 
	    	
	    	String sql = "SELECT AFP6A , COUNT ( * ) AS CONTADOR, AFAMA FROM AFDTA . AFP64F1 WHERE AFP7A = "
					+ periodo
					+ " AND AFOVA = "
					+ rutemp
					+ " AND AFOWA = "
					+ rutaf + " GROUP BY AFP6A, AFAMA ";

			ctmt = conexion.prepareCall(sql);

			result = ctmt.executeQuery();

			if (result.next()) {

				valor[0] = String.valueOf(result.getInt(2));
				valor[1] = result.getString(1);
				valor[2] = String.valueOf(result.getInt(3));

			} else {
				valor[0] = "0";
				valor[1] = "H";
				valor[2] = "0";
			}
	    	 
                         
            cds.closeConnection(); 
			
		}catch(Exception e){
			
			cds.closeConnection();
			e.printStackTrace();
			valor[0] = "0";
			valor[1] = "H";
			valor[2] = "0";
	    	return valor;
			
		}	
		finally
		{
			try{
				if(cds.getConnection()!=null)
					cds.closeConnection();
				if (ctmt != null)
					ctmt.close();
			}
			catch(Exception ignored)
			{}
		}
		
		return valor;
		
	}
	
	 public boolean insertaArchivo(AfiliadosTO afil)
	    {
		 //--alexis advise 15-06-2012--//

		CustomDataSource cds = new CustomDataSource();
	        Connection conexion = null;
	        String espacios="                                                  ";
	        Utils util = new Utils();
	        String[] usuario=this.properties();
	        try
	        {
	            cds.openConnection();
	            conexion = cds.getConnection();
	        
	            ctmt = conexion
						.prepareCall("{CALL AFDTA.PA_CAMBIOTRAMO_SET_AFIL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

				ctmt.setInt(1, afil.getPeriodo());
				ctmt.setInt(2, afil.getRutempresa());
				ctmt.setInt(3, afil.getRuttrabajador());
				ctmt.setInt(4, afil.getOficina());
				ctmt.setInt(5, afil.getSucursal());
				ctmt.setString(6, afil.getDvempresa());
				ctmt.setString(7, afil.getDvtrabajador());
				String nombre = afil.getApellidopaterno().trim() + " "
						+ afil.getApellidomaterno().trim() + " "
						+ afil.getNombreafiliado().trim();
				nombre = nombre.concat(espacios);

				for (; nombre.length() < 61; nombre = nombre + " ")
					;

				ctmt.setString(8, nombre.substring(0, 60));
				ctmt.setInt(9, afil.getRemuneracionesmismoempleador());
				ctmt.setInt(10, afil.getOtrasremuneraciones());
				ctmt.setInt(11, afil.getRentatrabajadorindependiente());
				ctmt.setInt(12, afil.getSubsidio());
				ctmt.setInt(13, afil.getPensiones());
				ctmt.setInt(14, afil.getTotalingresos());
				ctmt.setInt(15, afil.getNumeromeses());
				ctmt.setInt(16, afil.getIngresopromedio());
				ctmt.setInt(17, afil.getTrabajadorconsindeclaracion());
				ctmt.setString(18, afil.getOrigen());
				ctmt.setString(19, "I");
				ctmt.setInt(20, 0);
				ctmt.setDate(21, new java.sql.Date(new Date().getTime()));
				ctmt.setTime(22, new java.sql.Time(new Date().getTime()));
				ctmt.setDate(23, new java.sql.Date(new Date().getTime()));
				ctmt.setTime(24, afil.getTiempo());
				ctmt.setString(25, usuario[0].trim()); // SAJKM94
				ctmt.setString(26, ""); // SAJKM92
				int tramo = 0;
				int valtramo = 0;
				if (!util.isNumeric(String.valueOf(afil.getValortramo()))) {
					valtramo = 0;
				}else{
					valtramo = afil.getValortramo();
				}
				if (!util.isNumeric(String.valueOf(afil.getCodigotramo()))) {
					tramo = 0;
				}else{
					tramo = afil.getCodigotramo();
				}

				ctmt.setInt(27, tramo);
				ctmt.setInt(28, valtramo);

				ctmt.registerOutParameter(29, Types.INTEGER);

				ctmt.execute();

	           if(ctmt.getInt(29)==0)
	        	   return false;
	           
	            cds.closeConnection();
	        }
	        catch(Exception e)
	        {
				if(e.getMessage().indexOf("SQL0803") == -1){
				    e.printStackTrace();
				    return false;					
				}
	        }
	        finally
			{
				try{
					if(cds.getConnection()!=null)
						cds.closeConnection();
				}
				catch(Exception ex)
				{ 
					ex.printStackTrace();
				}
			}
	        return true;
	    }
	 
	 
	 private static CallableStatement ctmtUpdate = null;
	 
	 public boolean updateaAfiliados(AfiliadosTO afil)
	    {
		 //--alexis advise 15-06-2012--//
	        CustomDataSource cds = new CustomDataSource();
	        Connection conexion = null;
	        Utils util = new Utils();
	        String espacios="                                                  ";
	        String[] usuario=this.properties();
	         
	        try
	        {
	            cds.openConnection();
	            conexion = cds.getConnection();
	        
	            if(ctmtUpdate == null)
					ctmtUpdate = conexion.prepareCall("{CALL AFDTA.PA_CAMBIOTRAMO_UPDATE_AFIL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

				ctmtUpdate.setInt(1, afil.getPeriodo());
				ctmtUpdate.setInt(2, afil.getRutempresa());
				ctmtUpdate.setInt(3, afil.getRuttrabajador());
				ctmtUpdate.setInt(4, afil.getOficina());
				ctmtUpdate.setInt(5, afil.getSucursal());
				ctmtUpdate.setString(6, afil.getDvempresa());
				ctmtUpdate.setString(7, afil.getDvtrabajador());
				String nombre = afil.getApellidopaterno().trim() + " "
						+ afil.getApellidomaterno().trim() + " "
						+ afil.getNombreafiliado().trim();
				nombre = nombre.concat(espacios);
				
				for(;nombre.length()<61;nombre = nombre + " ");

				ctmtUpdate.setString(8, nombre.substring(0, 60));
				ctmtUpdate.setInt(9, afil.getRemuneracionesmismoempleador());
				ctmtUpdate.setInt(10, afil.getOtrasremuneraciones());
				ctmtUpdate.setInt(11, afil.getRentatrabajadorindependiente());
				ctmtUpdate.setInt(12, afil.getSubsidio());
				ctmtUpdate.setInt(13, afil.getPensiones());
				ctmtUpdate.setInt(14, afil.getTotalingresos());
				ctmtUpdate.setInt(15, afil.getNumeromeses());
				ctmtUpdate.setInt(16, afil.getIngresopromedio());
				ctmtUpdate.setInt(17, afil.getTrabajadorconsindeclaracion());
				ctmtUpdate.setString(18, afil.getOrigen());
				ctmtUpdate.setString(19, "I");
				ctmtUpdate.setInt(20, afil.getAfama());
				ctmtUpdate.setDate(21, new java.sql.Date(new Date().getTime()));
				ctmtUpdate.setTime(22, new java.sql.Time(new Date().getTime()));
				ctmtUpdate.setDate(23, new java.sql.Date(new Date().getTime()));
				ctmtUpdate.setTime(24, afil.getTiempo());
				ctmtUpdate.setString(25, "");
				ctmtUpdate.setString(26, usuario[1].trim());
				
				int tramo = 0;
				int valtramo = 0;
				
				if (!util.isNumeric(String.valueOf(afil.getValortramo()))) {
					valtramo = 0;
				}else{
					valtramo = afil.getValortramo();
				}
				if (!util.isNumeric(String.valueOf(afil.getCodigotramo()))) {
					tramo = 0;
				}else{
					tramo = afil.getCodigotramo();
				}

				ctmtUpdate.setInt(27, tramo);
				ctmtUpdate.setInt(28, valtramo);
				ctmtUpdate.registerOutParameter(29, Types.INTEGER);
	            ctmtUpdate.execute();
	            
	           if(ctmtUpdate.getInt(29)==0)
	        	   return false;
	           
	            cds.closeConnection();
	        }
	        catch(Exception e)
	        {
	           e.printStackTrace();  
	            return false;
	        }
	        finally
			{
				try{
					if(cds.getConnection()!=null)
						cds.closeConnection();
				}
				catch(Exception ex)
				{ex.printStackTrace();
				
				}
			}
	        return true;
	    }

	
	 
	 public boolean updateEstado(String periodo, String rutempresa)
	    {
		 //--alexis advise 15-06-2012--//
	        CustomDataSource cds = new CustomDataSource();
	        Connection conexion = null;
	        
	        try
	        {
	            cds.openConnection();
	            conexion = cds.getConnection();
	        
	            CallableStatement ctmt = conexion.prepareCall("{CALL AFDTA.PA_CAMBIOTRAMO_SET_ESTADO(?,?,?)}");
	            //CallableStatement ctmt = conexion.prepareCall(sql);
	            ctmt.setInt(1,  Integer.parseInt(periodo));
	            ctmt.setInt(2,  Integer.parseInt(rutempresa));
	            ctmt.registerOutParameter(3,Types.INTEGER);
	            
	            ctmt.execute();
	            
	           if(ctmt.getInt(3)==0)
	        	   return false;
	           
	            cds.closeConnection();
	        }
	        catch(Exception e)
	        {
	           e.printStackTrace();  
	            return false;
	        }
	        finally
			{
				try{
					if(cds.getConnection()!=null)
						cds.closeConnection();
				}
				catch(Exception ex)
				{ex.printStackTrace();
				
				}
			}
	        return true;
	    }

	
	 
	 
	 public boolean updateaAfiliadosholding(HoldingafiliadosTO afil)
	    {
		 
		 //--alexis advise 15-06-2012--//
	        CustomDataSource cds = new CustomDataSource();
	        Connection conexion = null;
	        String espacios="                                                  ";
	        String[] usuario=this.properties();
	        try
	        {
	            cds.openConnection();
	            conexion = cds.getConnection();
	        
	            CallableStatement ctmt = conexion.prepareCall("{CALL AFDTA.PA_CAMBIOTRAMO_UPDATE_AFIL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
	            //CallableStatement ctmt = conexion.prepareCall(sql);
	            ctmt.setInt(1, afil.getPeriodo());
	            ctmt.setInt(2, afil.getRutempresa());
	            ctmt.setInt(3, afil.getRuttrabajador());
	            ctmt.setInt(4, afil.getOficina());
	            ctmt.setInt(5, afil.getSucursal());
	            ctmt.setString(6, afil.getDvempresa());
	            ctmt.setString(7, afil.getDvtrabajador());
	            String nombre= afil.getNombre().trim();
	            nombre=nombre.concat(espacios);
	            
	            ctmt.setString(8,nombre.substring(0,60));
	            ctmt.setInt(9, afil.getRemuneracionesmismoempleador());
	            ctmt.setInt(10,afil.getOtrasremuneraciones());
	            ctmt.setInt(11,afil.getRentatrabajadorindependiente());
	            ctmt.setInt(12,afil.getSubsidio());
	            ctmt.setInt(13,afil.getPensiones());
	            ctmt.setInt(14,afil.getTotalingresos());
	            ctmt.setInt(15,afil.getNumeromeses());
	            ctmt.setInt(16, afil.getIngresopromedio());
	            ctmt.setInt(17, afil.getTrabajadorconsindeclaracion());
	            ctmt.setString(18, afil.getOrigen());
	            ctmt.setString(19, "I");
	            ctmt.setInt(20, 0);
	            ctmt.setDate(21, new java.sql.Date(new Date().getTime()));
	            ctmt.setTime(22, new java.sql.Time(new Date().getTime()));
	            ctmt.setDate(23, new java.sql.Date(new Date().getTime()));
	            ctmt.setTime(24, new java.sql.Time(new Date().getTime()));
	            ctmt.setString(25, "");
	            ctmt.setString(26, usuario[3].trim());
	            ctmt.setInt(27, afil.getCodigotramo());
	            ctmt.setInt(28, afil.getValortramo());
	            ctmt.registerOutParameter(29,Types.INTEGER);
	            
	            
	            ctmt.execute();
	            
	           if(ctmt.getInt(29)==0)
	        	   return false;
	           
	            cds.closeConnection();
	        }
	        catch(Exception e)
	        {
	           e.printStackTrace();  
	            return false;
	        }
	        finally
			{
				try{
					if(cds.getConnection()!=null)
						cds.closeConnection();
				}
				catch(Exception ex)
				{ex.printStackTrace();
				
				}
			}
	        return true;
	    }

	 

	 

	 
	 public boolean insertaArchivoholding(HoldingafiliadosTO afil)
	    {
		 
		 //--alexis advise 15-06-2012--//
	        CustomDataSource cds = new CustomDataSource();
	        Connection conexion = null;
	        String espacios="                                                  ";
	        String[] usuario=this.properties();
	        try
	        {
	            cds.openConnection();
	            conexion = cds.getConnection();
	        
	            CallableStatement ctmt = conexion.prepareCall("{CALL AFDTA.PA_CAMBIOTRAMO_SET_AFIL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
	            //CallableStatement ctmt = conexion.prepareCall(sql);
	            ctmt.setInt(1, afil.getPeriodo());
	            ctmt.setInt(2, afil.getRutempresa());
	            ctmt.setInt(3, afil.getRuttrabajador());
	            ctmt.setInt(4, afil.getOficina());
	            ctmt.setInt(5, afil.getSucursal());
	            ctmt.setString(6, afil.getDvempresa());
	            ctmt.setString(7, afil.getDvtrabajador());
	            String nombre= afil.getNombre();
	            nombre=nombre.concat(espacios);
	            
	            ctmt.setString(8,nombre.substring(0,60));
	            ctmt.setInt(9, afil.getIngresopromedio());
	            ctmt.setInt(10,afil.getOtrasremuneraciones());
	            ctmt.setInt(11,afil.getRentatrabajadorindependiente());
	            ctmt.setInt(12,afil.getSubsidio());
	            ctmt.setInt(13,afil.getPensiones());
	            ctmt.setInt(14,afil.getIngresopromedio());
	            ctmt.setInt(15,afil.getNumeromeses());
	            ctmt.setInt(16, afil.getIngresopromedio());
	            ctmt.setInt(17, afil.getTrabajadorconsindeclaracion());
	            ctmt.setString(18, afil.getOrigen());
	            ctmt.setString(19, "I");
	            ctmt.setInt(20, 0);
	            ctmt.setDate(21, new java.sql.Date(new Date().getTime()));
	            ctmt.setTime(22, new java.sql.Time(new Date().getTime()));
	            ctmt.setDate(23, new java.sql.Date(new Date().getTime()));
	            ctmt.setTime(24, new java.sql.Time(new Date().getTime()));
	            ctmt.setString(25, usuario[2].trim());
	            ctmt.setString(26, "");
	            ctmt.setInt(27, 0);
	            ctmt.setInt(28, 0);
	            ctmt.registerOutParameter(29,Types.INTEGER);
	            
	            
	            	ctmt.execute();
	           
	             
	            
	            	
	            
	           if(ctmt.getInt(29)==0)
	        	   return false;
	           
	            cds.closeConnection();
	        }
	        catch(Exception e)
	        {
	             
	            return false;
	        }
	        finally
			{
				try{
					if(cds.getConnection()!=null)
						cds.closeConnection();
				}
				catch(Exception ignored)
				{}
			}
	        return true;
	    }

	 //flujo
	 
	 public String[] getEtapa(EtapasTO etapas)
	    {
		 //--alexis advise 15-06-2012--//
	        CustomDataSource cds = new CustomDataSource();
	        Connection conexion = null;
	        String etapa[]=new String[5];
	        
	        
	         
	        try
	        {
	            cds.openConnection(); 
	            conexion = cds.getConnection();
	            ResultSet rs=null;
	        String sql="select count(*) as nRegistros," +
	" ifnull(sum(case when AFETAPA=1 then 1 else 0 end),0) as nUnos, " +
	" ifnull(sum(case when AFETAPA=2 then 1 else 0 end),0) as nDos, " +
	" ifnull(sum(case when AFETAPA=3 then 1 else 0 end),0) as nTres, " +
	" ifnull(sum(case when AFETAPA=4 then 1 else 0 end),0) as nCuatros " +
    " FROM AFDTA . CTFLUJO where AFPERIODO=? and AFRUTEMP in(" + etapas.getEmpresa() +" ) and AFRUTENC=?";
	            CallableStatement ctmt = conexion.prepareCall(sql);
	          
	             ctmt.setInt(1, Integer.parseInt(etapas.getPeriodo()));
	             ctmt.setInt(2, Integer.parseInt(etapas.getRutEncargado()));
	             
	             rs=ctmt.executeQuery();
	             rs.next();
	             etapa[0]=String.valueOf(rs.getInt(1));
	             etapa[1]=String.valueOf(rs.getInt(2));
	             etapa[2]=String.valueOf(rs.getInt(3));
	             etapa[3]=String.valueOf(rs.getInt(4));
	             etapa[4]=String.valueOf(rs.getInt(5));
	            

	            cds.closeConnection();
	        }
	        catch(Exception e)
	        {
	             e.printStackTrace();
	            cds.closeConnection();
	            return null;
	        }
	        finally
			{
				try{
					if(cds.getConnection()!=null)
						cds.closeConnection();
				}
				catch(Exception ignored)
				{}
			}
	        return etapa;
	    }
	 
	 public static final String SQLINFORMADOS = "SELECT F1 . * , F2 . * " +
		" FROM AFDTA . AFP64F1 F1 JOIN AFDTA . AFP64F2 F2 ON F1 . AFP7A = F2 . AFP7A AND F1 . AFOVA = F2 . AFOVA AND F1 . AFOWA = F2 . AFOWA WHERE " +	
		" F1 . SAJKM94 = 'CRGAMASIVA' " +
//		" F1 . SAJKM94 = 'ENCEMP-ENI'" +
		" AND AFP6A IN ( 'E' , 'C' , 'M' ) " +
		" AND F1 . AFP7A = ? AND F1 . AFOVA = ? AND F1 . OBF005 = ? AND F1 . OBF006 = ? "; 
	 
	/* public static final String SQLAFILIADOS ="SELECT  *  " +
		" FROM AFDTA.AFP64F1 F1 JOIN AFDTA . AFP64F2 F2 ON F1 " + 
		". AFP7A = F2.AFP7A AND F1 . AFOVA = F2 . AFOVA AND F1 "+ 
		". AFOWA = F2.AFOWA  WHERE F1.SAJKM94 NOT IN ( 'ENCEMP-ENI' "
		+ ", 'ENCEMP-ENA' , 'DIVPRE-ENI' , 'DIVPRE-ENA' , 'DIVPRE-GHI' ," +
		" 'DIVPRE-GHA' ) AND F1.AFP7A = ? AND F1.AFOVA = ? ";
	 */
	 
	 public static final String SQLAFILIADOS = "SELECT F1 . * , F2 . * " +
	 " FROM AFDTA . AFP64F1 F1 JOIN AFDTA . AFP64F2 F2 ON F1 . AFP7A = F2 . AFP7A AND F1 . AFOVA = F2 . AFOVA AND F1 . AFOWA = F2 . AFOWA" + 
	 " WHERE F1 . SAJKM94 = 'CRGAMASIVA' " +
//	 " WHERE F1 . SAJKM94 = 'ENCEMP-ENI'" +
	 " AND F1 . AFP7A = ? AND F1 . AFOVA = ? AND F1 . OBF005 = ? AND F1 . OBF006 = ? "; 
	 
	 public static final String ONLYENTERPRISE=" AND ( F1.AFOYA = ? ) AND ( F1.AFOZA = "+
		"?) ";
	 
	 public static final String SQLULTIMAFECHA = 	"SELECT F1 . OBF005 FECHA, MAX(F1 . OBF006) HORA " +
											 		"FROM AFDTA . AFP64F1 F1 " +
											 		"WHERE F1 . OBF005 IN( SELECT MAX(F1 . OBF005) FECHA FROM AFDTA . AFP64F1 F1 )" +
											 		"GROUP BY F1 . OBF005";
	 
	 public List getAfiliados(String tipoBusqueda, int oficina, int empresa, int sucursal)throws IOException
	    {
		 //--alexis advise 15-06-2012--//
	        CustomDataSource cds = new CustomDataSource();
	        Connection conexion = null;
	        
	       
	        Properties Param = new Properties();
			Param.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
			String periodo=Param.getProperty("PERIODO");
	        
	       String SQLINF="";
			String SQLPROP="";
			if(oficina==0||sucursal==0)
			{		SQLINF=SQLINFORMADOS;
			       SQLPROP=SQLAFILIADOS;
			}else {
				
				SQLINF=SQLINFORMADOS + ONLYENTERPRISE;
			    SQLPROP=SQLAFILIADOS + ONLYENTERPRISE;
				
			}
			
			CallableStatement ps = null;
			ResultSet rs = null;
			CallableStatement psFecha = null;
			ResultSet rsFecha = null;
			java.sql.Date ultimaFecha = new java.sql.Date(new Date().getTime());
			java.sql.Time time =  new java.sql.Time(new Date().getTime());
			List result = new ArrayList();
			AfiliadosPropuesta to = new AfiliadosPropuesta();
	         
	        try
	        {
	            cds.openConnection(); 
	            conexion = cds.getConnection();
	            
	            psFecha = conexion.prepareCall(SQLULTIMAFECHA);
				rsFecha = psFecha.executeQuery();
	            
				while (rsFecha.next()) {
					ultimaFecha = rsFecha.getDate(1);
					time = rsFecha.getTime(2);
				}
				
	            if (tipoBusqueda.equals("<>")) {
					ps = conexion
							.prepareCall(SQLINF);
				} else {
					ps = conexion
							.prepareCall(SQLPROP);
				}
				if(oficina==0||sucursal==0){
					ps.setInt(1, Integer.parseInt(periodo));
					ps.setInt(2, empresa);
					ps.setDate(3, ultimaFecha);
					ps.setTime(4, time);
				}else{
					ps.setInt(1, Integer.parseInt(periodo));
					ps.setInt(2, empresa);
					ps.setInt(3, oficina);
					ps.setInt(4, sucursal);
					ps.setDate(5, ultimaFecha);
					ps.setTime(6, time);
				}
				rs = ps.executeQuery();
				while (rs.next()) {
				    to = new AfiliadosPropuesta();
					to.setOficina(rs.getInt("AFOYA"));
					to.setRutEmpresa(rs.getInt("AFOVA"));
					to.setDvRutEmpresa(rs.getString("AFP0A"));
					to.setSucursal(rs.getInt("AFOZA"));
					to.setRutAfiliado(rs.getInt("AFOWA"));
					to.setDvRutAfiliado(rs.getString("AFP1A"));
					to.setNombreAfiliado(rs.getString("AFP2A"));
					to.setIngresoPromedio(rs.getInt("AF15UA"));
					to.setTramo(rs.getInt("AFCODTRA"));
					to.setValorTramo(rs.getInt("AFVALTRA"));
					to.setOrigen(rs.getString("AFP6A"));
					result.add(to);
				}
				//rs.close();
				//ps.close();
				//conexion.close();
				cds.closeConnection();
			} catch (SQLException e) {
				 e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				//try {
					//if(rs!=null)
						//rs.close();
					//if(ps!=null)
						//ps.close();
					//if(conexion!=null)
						//conexion.close();
					if(cds.getConnection()!=null)
						cds.closeConnection();
				//} catch (SQLException e) {
				//	 e.printStackTrace();
				//}
			}
			return result;
	    }
	 
	 
	 
	 
	 
	 public Collection getEmpresas(String userID)
	    {
		 //--alexis advise 15-06-2012--//
	        CustomDataSource cds = new CustomDataSource();
	        Connection conexion = null;
	        ArrayList lista=new ArrayList();
	        Enterprise empresa=new Enterprise();
	     
	        
	        
	         
	        try
	        {
	            cds.openConnection(); 
	            conexion = cds.getConnection();
	            ResultSet rs=null;
	           
	        String sql="select A.RUTEMP, B.RAZSOC  FROM "
	        	 + " DUDTA.LDAP2500 A  inner join DUDTA.LDAP2000 "
	        	 + " B on A.RUTEMP=B.RUTEMP WHERE A.RUTUSR= '" + userID + "' and  DNAPPROL like 'cn=PorEmpAdheEnc%'";
	        
	            CallableStatement ctmt = conexion.prepareCall(sql);
	            rs=ctmt.executeQuery();
	             
		       while(rs.next()){
		    		 
		        empresa=new Enterprise();
		        
	             empresa.setID(rs.getString(1).trim());
	             empresa.setName(rs.getString(2).trim());
	             
	             lista.add(empresa);
		       }
	             

	            cds.closeConnection();
	        }
	        catch(Exception e)
	        {
	             e.printStackTrace();
	            cds.closeConnection();
	            return null;
	        }
	        finally
			{
				try{
					if(cds.getConnection()!=null)
						cds.closeConnection();
				}
				catch(Exception ignored)
				{}
			}
	        return lista;
	    }
	 
	 
	 public int consultaEstado(int periodo, int empresa)
	    {
		 //--alexis advise 15-06-2012--//
	        CustomDataSource cds = new CustomDataSource();
	        Connection conexion = null;
	        
	        int etapa=0;
	         
	        try
	        {
	            cds.openConnection(); 
	            conexion = cds.getConnection();
	            ResultSet rs=null;
	        String sql="select  AFETAPA from AFDTA.CTFLUJO where AFPERIODO=" +periodo+ " and AFRUTEMP=" + empresa;
	            CallableStatement ctmt = conexion.prepareCall(sql);
	         
	             
	             rs=ctmt.executeQuery();
	             if(rs.next()){
	             etapa=rs.getInt(1);
	             
	             }
	             else
	             {
	            	 etapa=0;
	            	 
	             }
	            

	            cds.closeConnection();
	        }
	        catch(Exception e)
	        {
	             e.printStackTrace();
	            cds.closeConnection();
	            return 0;
	        }
	        finally
			{
				try{
					if(cds.getConnection()!=null)
						cds.closeConnection();
				}
				catch(Exception ignored)
				{}
			}
	       return etapa; 
	    }
	 
	 
	 public boolean updateFlujo(FlujoTO flujo)
	    {
		 //--alexis advise 15-06-2012--//
	        CustomDataSource cds = new CustomDataSource();
	        Connection conexion = null;
	      
	       
	        try
	        {
	            cds.openConnection();
	            conexion = cds.getConnection();
	        
	            CallableStatement ctmt = conexion.prepareCall("{CALL   AFDTA.PA_CAMBIOTRAMO_UPDATE_FLUJO(?,?,?,?,?,?)}");
	            
	            ctmt.setInt(1, Integer.parseInt(flujo.getPeriodo()));
	            ctmt.setInt(2, Integer.parseInt(flujo.getRutempresa()));
	            ctmt.setInt(3, Integer.parseInt(flujo.getRutencargado()));
	            ctmt.setInt(4, Integer.parseInt(flujo.getEtapa()));
	            ctmt.setString(5, flujo.getISAJKM92());
	              ctmt.registerOutParameter(6,Types.INTEGER);
	            
	            ctmt.execute();
	            
	           if(ctmt.getInt(6)==0)
	        	   return false;
	           
	            cds.closeConnection();
	        }
	        catch(Exception e)
	        {
	           e.printStackTrace();  
	            return false;
	        }
	        finally
			{
				try{
					if(cds.getConnection()!=null)
						cds.closeConnection();
				}
				catch(Exception ex)
				{ex.printStackTrace();
				
				}
			}
	        return true;
	    }

	 
	 
	 public boolean InsertaFlujo(FlujoTO flujo)
	    {
		 
		 //--alexis advise 15-06-2012--//
	        CustomDataSource cds = new CustomDataSource();
	        Connection conexion = null;
	         
	        
	        try
	        {
	            cds.openConnection();
	            conexion = cds.getConnection();
	        
	            CallableStatement ctmt = conexion.prepareCall("{CALL AFDTA.PA_CAMBIOTRAMO_SET_FLUJO(?,?,?,?,?,?)}");
	             
	            ctmt.setInt(1, Integer.parseInt(flujo.getPeriodo()));
	            ctmt.setInt(2, Integer.parseInt(flujo.getRutempresa()));
	            ctmt.setInt(3, Integer.parseInt(flujo.getRutencargado()));
	            ctmt.setString(4, flujo.getISAJKM94());
	            ctmt.setString(5, flujo.getISAJKM92());
	           
	            ctmt.registerOutParameter(6,Types.INTEGER);
	            
	            
	            	ctmt.execute();
	           
	             
	            
	            	
	            
	           if(ctmt.getInt(6)==0)
	        	   return false;
	           
	            cds.closeConnection();
	        }
	        catch(Exception e)
	        {
	             e.printStackTrace();
	            return false;
	        }
	        finally
			{
				try{
					if(cds.getConnection()!=null)
						cds.closeConnection();
				}
				catch(Exception ignored)
				{}
			}
	        return true;
	    }

	 public boolean InsertaBitacora(FlujoTO flujo)
	    {
		 
		 //--alexis advise 15-06-2012--//
	        CustomDataSource cds = new CustomDataSource();
	        Connection conexion = null;
	         
	        
	        try
	        {
	            cds.openConnection();
	            conexion = cds.getConnection();
	        
	            CallableStatement ctmt = conexion.prepareCall("{CALL AFDTA.PA_CAMBIOTRAMO_SET_BITA(?,?,?,?,?,?,?,?,?,?)}");
	             
	            ctmt.setInt(1, Integer.parseInt(flujo.getPeriodo()));
	            ctmt.setInt(2, Integer.parseInt(flujo.getRutempresa()));
	            String temp[]=flujo.getRutencargado().split("-");
	            ctmt.setInt(3, Integer.parseInt(temp[0]));
	            ctmt.setString(4, flujo.getEtapa());
	            ctmt.setString(5, flujo.getOperacion());
	            ctmt.setString(6, flujo.getNombrearchivo());
	            ctmt.setInt(7, flujo.getCantregistros());
	            ctmt.setString(8, flujo.getISAJKM94());
	            ctmt.setString(9, flujo.getISAJKM92());
	           
	            ctmt.registerOutParameter(10,Types.INTEGER);
	            
	            
	            	ctmt.execute();
	           
	             
	            
	            	
	            
	           if(ctmt.getInt(10)==0)
	        	   return false;
	           
	            cds.closeConnection();
	        }
	        catch(Exception e)
	        {
	             e.printStackTrace();
	            return false;
	        }
	        finally
			{
				try{
					if(cds.getConnection()!=null)
						cds.closeConnection();
				}
				catch(Exception ignored)
				{}
			}
	        return true;
	    }

	 
	 
	 public boolean InsertaRenta(RentaproTO renta)
	    {
		 
	 
	        CustomDataSource cds = new CustomDataSource();
	        Connection conexion = null;
	         
	        
	        try
	        {
	            cds.openConnection();
	            conexion = cds.getConnection();
	        
	            CallableStatement ctmt = conexion.prepareCall("{CALL  AFDTA.PA_CAMBIOTRAMO_SET_RENTAPRO(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
	            													  
	            
	            ctmt.setInt(1, Integer.parseInt(renta.getRutEmpresa()));
	            ctmt.setString(2, renta.getDvrutempresa());
	            ctmt.setInt(3,Integer.parseInt(renta.getRutencargado()));
	            ctmt.setString(4, renta.getDvencargado()); 
	            ctmt.setString(5, renta.getEtapa());
	            ctmt.setString(6, renta.getOperacion());
	            ctmt.setString(7, renta.getArchivo());
	            ctmt.setInt(8, Integer.parseInt(renta.getCantreg()));
	            ctmt.setString(9, renta.getExtencion());
	            ctmt.setString(10, renta.getCantarchivos());
	            ctmt.setString(11, renta.getMail1());
	            ctmt.setString(12, renta.getMail2());
	            ctmt.setString(13, renta.getMail3());
	           
	            ctmt.registerOutParameter(14,Types.INTEGER);
	            
	            
	            	ctmt.execute();
	           
	             
	            
	            	
	            
	           if(ctmt.getInt(14)==0)
	        	   return false;
	           
	            cds.closeConnection();
	        }
	        catch(Exception e)
	        {
	             e.printStackTrace();
	            return false;
	        }
	        finally
			{
				try{
					if(cds.getConnection()!=null)
						cds.closeConnection();
				}
				catch(Exception ignored)
				{}
			}
	        return true;
	    }

	 
	 
	 
	 
	 public String[] getValores(String id,String tipo)
	    {
		 //--alexis advise 15-06-2012--//
	        CustomDataSource cds = new CustomDataSource();
	        Connection conexion = null;
	        String v[]=new String[2];
	        
	        
	         
	        try
	        {
	            cds.openConnection();
	            conexion = cds.getConnection();
	        
	            CallableStatement ctmt = conexion.prepareCall("{CALL AFDTA.GETDESCRIPCION(?,?,?,?)}");
	          
	             ctmt.setString(1, id);
	             ctmt.setString(2, tipo);
	             ctmt.registerOutParameter(3, Types.VARCHAR);
	             ctmt.registerOutParameter(4, Types.VARCHAR);
	             ctmt.executeQuery();
	             v[0]=ctmt.getString(3);
	             v[1]=ctmt.getString(4);
	            

	            cds.closeConnection();
	        }
	        catch(Exception e)
	        {
	             e.printStackTrace();
	            cds.closeConnection();
	            return null;
	        }
	        finally
			{
				try{
					if(cds.getConnection()!=null)
						cds.closeConnection();
				}
				catch(Exception ignored)
				{}
			}
	        return v;
	    }
	 
	 public int[][] getTexto(String ext)
	    {
		 
		 //--alexis advise 15-06-2012--//
	        CustomDataSource cds = new CustomDataSource();
	        ResultSet rsDatos = null;
	        Connection conexion = null;
	        int v[][]=new int[21][2];
	        
	        try
	        {
	            cds.openConnection();
	            conexion = cds.getConnection();
	            
	            CallableStatement ctmt = conexion.prepareCall("{call AFDTA.GETTEXTOHOL(?)}");
	            ctmt.setString(1,ext.toUpperCase());
	            rsDatos=ctmt.executeQuery();
	            int i=0;
	            while(rsDatos.next()){
	          
	            	 
	            
	          v[i][0]=rsDatos.getInt(1);
	          v[i][1]=rsDatos.getInt(2);
	          
	          i++;
	           	            }

	            cds.closeConnection();
	        }
	        catch(Exception e)
	        {
	             e.printStackTrace();
	            cds.closeConnection();
	            return null;
	        }
	        finally
			{
				try{
					if(cds.getConnection()!=null)
						cds.closeConnection();
				}
				catch(Exception ignored)
				{}
			}
	        return v;
	    }
		
	 public List getAfiliadosNoInformados(int oficina, int sucursal, int empresa) throws IOException 
	    {
		 
		 
	        CustomDataSource cds = new CustomDataSource();
	        ResultSet rsDatos = null;
	        Connection conexion = null;
	        AfiliadosPropuestaTO oafil=null;
	        List resu=new ArrayList();
	        
	       
	        try
	        {
	            cds.openConnection();
	            conexion = cds.getConnection();
	            String Periodo=this.properties()[4];
	            
	            CallableStatement ctmt = conexion.prepareCall("{call AFDTA.PA_GET_AFILIADOS_SALDOS (?,?,?,?)}");
	            ctmt.setInt(1,Integer.parseInt(Periodo));
	            ctmt.setInt(2,empresa);
	            ctmt.setInt(3,oficina);
	            ctmt.setInt(4,sucursal);
	            rsDatos=ctmt.executeQuery();
	            
	            while(rsDatos.next()){
	          
	            oafil=new AfiliadosPropuestaTO();	
	            oafil.setPeriodo(Integer.parseInt(Periodo));
	            oafil.setOficina(rsDatos.getInt(1));
	            oafil.setSucursal(rsDatos.getInt(2));
	            oafil.setRutEmpresa(rsDatos.getInt(3));
	            oafil.setDvRutEmpresa(rsDatos.getString(4));
	            oafil.setRutAfiliado(rsDatos.getInt(5));
	            oafil.setDvRutAfiliado(rsDatos.getString(6));
	            oafil.setNombreAfiliado(rsDatos.getString(7));
	            System.out.println(rsDatos.getString(7));
	            System.out.println(rsDatos.getString(8));
	            System.out.println(rsDatos.getString(9));
	            oafil.setAmaterno(rsDatos.getString(8));
	            oafil.setAmaterno(rsDatos.getString(9));
	            oafil.setRemuneracionEmpleador(rsDatos.getInt(10));
	            oafil.setRemuneracionOtroEmpleador(rsDatos.getInt(11));
	            oafil.setRemuneracionIndependiente(rsDatos.getInt(12));
	            oafil.setRentaSubsidio(rsDatos.getInt(13));
	            oafil.setRentaPensiones(rsDatos.getInt(14));
	            oafil.setTotalIngresos(rsDatos.getInt(15));
	            oafil.setNumeroMeses(rsDatos.getInt(16));
	            oafil.setIngresoPromedio(rsDatos.getInt(17));
	            oafil.setDeclaracion(rsDatos.getInt(18));
	            System.out.println(rsDatos.getInt(19));
	            System.out.println(rsDatos.getInt(20));
	             oafil.setTramo(rsDatos.getInt(19));
	            oafil.setValorTramo(rsDatos.getInt(20));
	             
	            resu.add(oafil);
	           	            }

	            cds.closeConnection();
	        }
	        catch(Exception e)
	        {
	             e.printStackTrace();
	            cds.closeConnection();
	            return null;
	        }
	        finally
			{
				try{
					if(cds.getConnection()!=null)
						cds.closeConnection();
				}
				catch(Exception ignored)
				{}
			}
	        return resu;
	    }
		
	 public List getAfiliadosIngresados(int oficina, int sucursal, int empresa) throws IOException 
	    {
		 
		 
	        CustomDataSource cds = new CustomDataSource();
	        ResultSet rsDatos = null;
	        Connection conexion = null;
	        AfiliadosPropuestaTO oafil=null;
	        List resu=new ArrayList();
	        
	       
	        try
	        {
	            cds.openConnection();
	            conexion = cds.getConnection();
	            String Periodo=this.properties()[4];
	            
	            CallableStatement ctmt = conexion.prepareCall("{call AFDTA.PA_GET_AFILIADOS_INSERTADOS (?,?,?,?)}");
	            ctmt.setInt(1,Integer.parseInt(Periodo));
	            ctmt.setInt(2,empresa);
	            ctmt.setInt(3,oficina);
	            ctmt.setInt(4,sucursal);
	            rsDatos=ctmt.executeQuery();
	            
	            while(rsDatos.next()){
	          
	            oafil=new AfiliadosPropuestaTO();	
	            oafil.setPeriodo(Integer.parseInt(Periodo));
	            oafil.setOficina(rsDatos.getInt(1));
	            oafil.setSucursal(rsDatos.getInt(2));
	            oafil.setRutEmpresa(rsDatos.getInt(3));
	            oafil.setDvRutEmpresa(rsDatos.getString(4));
	            oafil.setRutAfiliado(rsDatos.getInt(5));
	            oafil.setDvRutAfiliado(rsDatos.getString(6));
	            oafil.setNombreAfiliado(rsDatos.getString(7));
	       //     System.out.println(rsDatos.getString(7));
	       //    System.out.println(rsDatos.getString(8));
	       //     System.out.println(rsDatos.getString(9));
	            oafil.setAmaterno(rsDatos.getString(8));
	            oafil.setAmaterno(rsDatos.getString(9));
	            oafil.setRemuneracionEmpleador(rsDatos.getInt(10));
	            oafil.setRemuneracionOtroEmpleador(rsDatos.getInt(11));
	            oafil.setRemuneracionIndependiente(rsDatos.getInt(12));
	            oafil.setRentaSubsidio(rsDatos.getInt(13));
	            oafil.setRentaPensiones(rsDatos.getInt(14));
	            oafil.setTotalIngresos(rsDatos.getInt(15));
	            oafil.setNumeroMeses(rsDatos.getInt(16));
	            oafil.setIngresoPromedio(rsDatos.getInt(17));
	            oafil.setDeclaracion(rsDatos.getInt(18));
	       //     System.out.println(rsDatos.getInt(19));
	       //     System.out.println(rsDatos.getInt(20));
	             oafil.setTramo(rsDatos.getInt(19));
	            oafil.setValorTramo(rsDatos.getInt(20));
	             
	            resu.add(oafil);
	           	            }

	            cds.closeConnection();
	        }
	        catch(Exception e)
	        {
	             e.printStackTrace();
	            cds.closeConnection();
	            return null;
	        }
	        finally
			{
				try{
					if(cds.getConnection()!=null)
						cds.closeConnection();
				}
				catch(Exception ignored)
				{}
			}
	        return resu;
	    }
	 
	 
	 public List getEstadisticaProceso()
	 {
		 List data = new ArrayList();
		 
		 CustomDataSource cds = new CustomDataSource();
		 Connection conexion = cds.getConnection();
		 
		 try {
			 CallableStatement ctmt = conexion.prepareCall("{call AFDTA.PA_GET_ESTADISTICA_PROCESO(?)}");
			 
			 ctmt.setInt(1, Integer.parseInt(Config.getProperty("PERIODO")));

			 ResultSet rsDatos = ctmt.executeQuery();
			 
		 
			 while(rsDatos.next()){
				 data.add(new EstadisticaProcesoTO(
						 rsDatos.getString("COD_OFI"),
						 rsDatos.getString("NOM_OFI"),
						 rsDatos.getString("RUT_EMP"),
						 rsDatos.getString("NOM_EMP"),
						 String.valueOf(Math.round(rsDatos.getDouble("PINFO"))),
						 rsDatos.getString("INFO"),
						 String.valueOf(Math.round(rsDatos.getDouble("PPEN"))),
						 rsDatos.getString("PEN"),
						 rsDatos.getString("TOTAL")
						 )
				 );
			 }
		 
		 } catch (SQLException e) {
				e.printStackTrace();
		 }
		 
		 return data;
	 }

}