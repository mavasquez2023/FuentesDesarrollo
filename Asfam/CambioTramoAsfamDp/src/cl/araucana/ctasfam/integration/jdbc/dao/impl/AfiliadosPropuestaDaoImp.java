 package cl.araucana.ctasfam.integration.jdbc.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.araucana.ctasfam.business.to.AfiliadosPropuestaTO;
import cl.araucana.ctasfam.integration.jdbc.dao.AfiliadosPropuestaDao;
import cl.araucana.ctasfam.integration.jdbc.dao.mapper.AfiliadosPropuestaToMapper;
import cl.araucana.ctasfam.integration.jdbc.dao.mapper.RowMapper;
import cl.araucana.ctasfam.integration.jdbc.exception.AfiliadosPropuestaException;

public class AfiliadosPropuestaDaoImp extends GenericDaoImpl implements
		AfiliadosPropuestaDao {
	
	
	public static final String ONLYENTERPRISE=" AND ( F1.AFOYA = ? ) AND ( F1.AFOZA = "+
					"?) ";
	
	/*public static final String SQLAFILIADOS ="SELECT  *  " +
					" FROM AFDTA.AFP64F1 F1 JOIN AFDTA . AFP64F2 F2 ON F1 " + 
					". AFP7A = F2.AFP7A AND F1 . AFOVA = F2 . AFOVA AND F1 "+ 
					". AFOWA = F2.AFOWA  WHERE F1.SAJKM94 NOT IN ( 'ENCEMP-ENI' "
					+ ", 'ENCEMP-ENA' , 'DIVPRE-ENI' , 'DIVPRE-ENA' , 'DIVPRE-GHI' ," +
					" 'DIVPRE-GHA' ) AND F1.AFP7A = ? AND F1.AFOVA = ? ";
	
	*/

/*	public static final String SQLINFORMADOS ="SELECT  * "+
	"FROM AFDTA.AFP64F1 F1 INNER JOIN AFDTA.AFP64F2 F2 ON F1.AFP7A = F2.AFP7A AND F1.AFOVA = F2.AFOVA AND F1.AFOWA = F2.AFOWA WHERE	" +
	"F1.AFP6A='E' AND F1.SAJKM94='CRGAMASIVA' " +
	" AND F1.AFP7A = ? AND F1.AFOVA = ?  ";
	*/
	public static final String SQLAFILIADOS = "SELECT F1 . AFOYA , F1 . AFOZA , F1 . AFOVA , F1 . AFP0A , F1 . AFOWA , F1 . AFP1A , F1 . AFP2A , '' AS AFP2A2 , '' AS AFP2A3 ,"+ 
	" F1 . AF15NA , F1 . AF15OA , F1 . AF15PA , F1 . AF15QA , F1 . AF15RA , F1 . AF15SA , F1 . AF15TA , F1 . AF15UA , F1 . AF15VA , "+
	" F2 . AFCODTRA , F2 . AFVALTRA " + 
	" FROM AFDTA . AFP64F1 F1 JOIN AFDTA . AFP64F2 F2 ON F1 . AFP7A = F2 . AFP7A AND F1 . AFOVA = F2 . AFOVA AND F1 . AFOWA = F2 . AFOWA " + 
	" WHERE F1 . SAJKM94 = 'CRGAMASIVA' " +
//	" WHERE F1 . SAJKM94 = 'ENCEMP-ENI' " +
	"AND AFP6A NOT IN ( 'E' , 'C' , 'M' ) " +
	" AND F1 . AFP7A = ? AND F1 . AFOVA = ? AND F1 . OBF005 = ? " ; 
	
	public static final String SQLINFORMADOS = "SELECT F1 . * , F2 . * " +
	" FROM AFDTA . AFP64F1 F1 JOIN AFDTA . AFP64F2 F2 ON F1 . AFP7A = F2 . AFP7A AND F1 . AFOVA = F2 . AFOVA AND F1 . AFOWA = F2 . AFOWA WHERE " +	
	" F1 . SAJKM94 = 'CRGAMASIVA' " +
//	" F1 . SAJKM94 = 'ENCEMP-ENI'" +
	" AND AFP6A IN ( 'E' , 'C' , 'M' ) " +
	" AND F1 . AFP7A = ? AND F1 . AFOVA = ? AND F1 . OBF005 = ?"; 
	
	public static final String SQLULTIMAFECHA = "SELECT MAX(F1 . OBF005) FROM AFDTA . AFP64F1 F1";
	 
	private static final Log log = LogFactory
			.getLog(AfiliadosPropuestaDaoImp.class);

	public AfiliadosPropuestaDaoImp(String dataSourceName) throws Exception {
		super(dataSourceName);
	}

 
	public List select(String tipoBusqueda,
			AfiliadosPropuestaTO afiliadosPropuestaTO) throws Exception {
		
		String SQLINF="";
		String SQLPROP="";
		if(afiliadosPropuestaTO.getOficina()==0||afiliadosPropuestaTO.getSucursal()==0)
		{		SQLINF=SQLINFORMADOS;
		       SQLPROP=SQLAFILIADOS;
		}else {
			
			SQLINF=SQLINFORMADOS + ONLYENTERPRISE;
		    SQLPROP=SQLAFILIADOS + ONLYENTERPRISE;
			
		}
		
		 Properties Param = new Properties();
		Param.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		String periodo=Param.getProperty("PERIODO");
		Connection cn = null;
		CallableStatement ps = null;
		ResultSet rs = null;
		CallableStatement psFecha = null;
		ResultSet rsFecha = null;
		java.sql.Date ultimaFecha = new java.sql.Date(new Date().getTime());
		List result = new ArrayList();
		RowMapper rm = new AfiliadosPropuestaToMapper();
		try {
			cn = super.getConnection();
			
			psFecha = cn.prepareCall(SQLULTIMAFECHA);
			rsFecha = psFecha.executeQuery();
			
			while (rsFecha.next()) {
				ultimaFecha = rsFecha.getDate(1);
			}
			
			if (tipoBusqueda == "<>") {
				ps = cn
						.prepareCall(SQLINF);
			} else {
				ps = cn
						.prepareCall(SQLPROP);
			}
			if(afiliadosPropuestaTO.getOficina()==0||afiliadosPropuestaTO.getSucursal()==0){
			ps.setInt(1, Integer.parseInt(periodo));
			ps.setInt(2, afiliadosPropuestaTO.getRutEmpresa());
			ps.setDate(3, ultimaFecha);
			}else{
			ps.setInt(1, Integer.parseInt(periodo));
			ps.setInt(2, afiliadosPropuestaTO.getRutEmpresa());
			ps.setInt(3, afiliadosPropuestaTO.getOficina());
			ps.setInt(4, afiliadosPropuestaTO.getSucursal());
			ps.setDate(5, ultimaFecha);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				result.add(rm.mapRow(rs, 0));
			}
		} catch (SQLException e) {
			log.error("Error: al seleccionar afiliado, "
					+ e.getLocalizedMessage(), e);
		} catch (Exception e) {
			log.error("Error: al seleccionar afiliado, "
					+ e.getLocalizedMessage(), e);
		} finally {
			try {
				rs.close();
				ps.close();
				cn.close();
			} catch (SQLException e) {
				log.error("Error: al finalizar conexiones, "
						+ e.getLocalizedMessage(), e);
				throw new AfiliadosPropuestaException(
						"Error: al finalizar conexiones, "
								+ e.getLocalizedMessage());
			}
		}
		return result;
	}

 
	public List selectPropuestaSaldos(AfiliadosPropuestaTO afiliadosPropuestaTO)
	throws Exception {
		 Properties Param = new Properties();
			Param.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
			String periodo=Param.getProperty("PERIODO");
Connection cn = null;
CallableStatement ps = null;
ResultSet rs = null;
List result = new ArrayList();
RowMapper rm = new AfiliadosPropuestaToMapper();
try {
	cn = super.getConnection();
	ps = cn
			.prepareCall("{CALL AFDTA.PA_GET_AFILIADOS_SALDOS(?,?,?,?)}");
	ps.setInt(1, Integer.parseInt(periodo));
	ps.setInt(2, afiliadosPropuestaTO.getRutEmpresa());
	ps.setInt(3, afiliadosPropuestaTO.getOficina());
	ps.setInt(4, afiliadosPropuestaTO.getSucursal());
	rs = ps.executeQuery();
	while (rs.next()) {
		result.add(rm.mapRow(rs, 0));
	}
} catch (SQLException e) {
	log.error("Error: al seleccionar afiliado, "
			+ e.getLocalizedMessage(), e);
} catch (Exception e) {
	log.error("Error: al seleccionar afiliado, "
			+ e.getLocalizedMessage(), e);
} finally {
	try {
		rs.close();
		ps.close();
		cn.close();
	} catch (SQLException e) {
		log.error("Error: al finalizar conexiones, "
				+ e.getLocalizedMessage(), e);
		throw new AfiliadosPropuestaException(
				"Error: al finalizar conexiones, "
						+ e.getLocalizedMessage());
	}
}
return result;
}
	
	public List selectPropuestaInsertados(AfiliadosPropuestaTO afiliadosPropuestaTO)
	throws Exception {
		 Properties Param = new Properties();
			Param.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
			String periodo=Param.getProperty("PERIODO");
Connection cn = null;
CallableStatement ps = null;
ResultSet rs = null;
List result = new ArrayList();
RowMapper rm = new AfiliadosPropuestaToMapper();
try {
	cn = super.getConnection();
	ps = cn
			.prepareCall("{CALL AFDTA.PA_GET_AFILIADOS_INSERTADOS(?,?,?,?)}");
	ps.setInt(1, Integer.parseInt(periodo));
	ps.setInt(2, afiliadosPropuestaTO.getRutEmpresa());
	ps.setInt(3, afiliadosPropuestaTO.getOficina());
	ps.setInt(4, afiliadosPropuestaTO.getSucursal());
	rs = ps.executeQuery();
	while (rs.next()) {
		result.add(rm.mapRow(rs, 0));
	}
} catch (SQLException e) {
	log.error("Error: al seleccionar afiliado, "
			+ e.getLocalizedMessage(), e);
} catch (Exception e) {
	log.error("Error: al seleccionar afiliado, "
			+ e.getLocalizedMessage(), e);
} finally {
	try {
		rs.close();
		ps.close();
		cn.close();
	} catch (SQLException e) {
		log.error("Error: al finalizar conexiones, "
				+ e.getLocalizedMessage(), e);
		throw new AfiliadosPropuestaException(
				"Error: al finalizar conexiones, "
						+ e.getLocalizedMessage());
	}
}
return result;
}	
}