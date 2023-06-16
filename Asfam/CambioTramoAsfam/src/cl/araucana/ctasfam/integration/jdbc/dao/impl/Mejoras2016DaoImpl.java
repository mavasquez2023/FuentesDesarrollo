package cl.araucana.ctasfam.integration.jdbc.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.araucana.ctasfam.business.to.AfiliadosPropuestaTO;
import cl.araucana.ctasfam.business.to.AfiliadosTO;
import cl.araucana.ctasfam.business.to.ParametrosTO;
import cl.araucana.ctasfam.business.to.ProcesoBashTO;
import cl.araucana.ctasfam.integration.jdbc.exception.RentaPropuestasException;
import cl.araucana.ctasfam.presentation.struts.vo.PeticionProcesamientoDto;
import cl.araucana.ctasfam.resources.util.CustomDataSource;
import cl.araucana.ctasfam.resources.util.Parametros;




public class Mejoras2016DaoImpl  {
	
	private final String INSERT_BASH = "INSERT INTO AFDTA.AFP66F1(AFP7A, AFOVA, AFP66ARC, AFP66EST, SAJKM94, OBF002, OBF003, AFP66TRI, AFP66ORI, AFP66CIN, AFP66FPA, AFP66TRP) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private final String UPDATE_BASH = "UPDATE AFDTA.AFP66F1 SET(AFP66EST, AFP66TRI, AFP66TRP, AFP66TRN, AFP66CIN, AFP66ARC, AFP66FPA, OBF005, OBF006, SAJKM94)= (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) WHERE AFOVA = ? AND AFP7A = ?";
	
	private final String SELECT_EXIST_EMPRESA = "SELECT AFP66CIN FROM AFDTA.AFP66F1 WHERE AFOVA = ? AND AFP7A = ?";
	
	private final String SELECT_PETICION_PROCESAMIENTO_BY_EMPRESA = "SELECT AFP66ID,AFP7A,AFOVA,AFOYA,AFOZA,AFP66ARC,AFP66EST,AFP66TRI,AFP66FPA,AFP66TRP,AFP66ORI,AFP66CIN,OBF002,OBF003,OBF005,OBF006,SAJKM94,SAJKM92,AFP66TRN FROM AFDTA.AFP66F1 WHERE AFOVA = ? AND AFP7A = ?";
	
	private final String SELECT_FEC_CAMBIO_TRAMO = "SELECT AFP80COD, AFP80VAL FROM AFDTA.AFP80F1";
	
	private final String UPDATE_PARAMS = "UPDATE AFDTA.AFP80F1 set AFP80VAL =  ? WHERE AFP80COD= ? ";
	
	private static final Log log = LogFactory
			.getLog(Mejoras2016DaoImpl.class);
	
	//private static Properties Config = null;

	
	public Mejoras2016DaoImpl()  {
	
	}
	
	
	public List<String> getEmpresasPropuesta(int periodo, String empresas) {
		// --clillo 08-06-2018--//
		CustomDataSource cds = new CustomDataSource();
		Connection cn = null;
		List<String> listaEmpresas= new ArrayList<String>();
		int etapa = 0;
		PreparedStatement ps = null;
		try {
			cds.openConnection();
			cn = cds.getConnection();
			ResultSet rs = null;
			StringBuffer sql= new StringBuffer();
			
			sql.append("select distinct AFOVA from afdta.AFP64F1 a ");
			sql.append("where a.AFP7A>  " + periodo + " ");
			sql.append("and AFOVA in (" + empresas +") ");
			//System.out.println(">>query=" + sql);
			ps = cn.prepareStatement(sql.toString());

			rs = ps.executeQuery();
			while (rs.next()) {
				listaEmpresas.add(rs.getString(1));
			} 

		} catch (Exception e) {
			e.printStackTrace();
			etapa = 0;
		} finally {
			try {
				if (cds != null)
					cds.closeConnection();
				if (ps != null)
					ps.close();
			} catch (Exception ignored) {
			}
		}
		return listaEmpresas;
	}
	
	public Vector<String> getTrabajadoresPropuesta(int periodo, int rutEmpresa) {
		// --clillo 08-06-2018--//
		CustomDataSource cds = new CustomDataSource();
		Connection cn = null;
		//List<String> dataEmpresa= new ArrayList<String>();
		Vector<String> dataEmp= new Vector<String>();
		int etapa = 0;
		PreparedStatement ps = null;
		try {
			cds.openConnection();
			cn = cds.getConnection();
			ResultSet rs = null;
			StringBuffer sql= new StringBuffer();
			String sep=";";
			sql.append("select a.AFP7A as Periodo, ");
			sql.append("a.AFOVA as RutEmpresa, ");
			sql.append("a.AFP0A as DVEmpresa, ");
			sql.append("a.AFOWA as RutTrabajador, ");
			sql.append("a.AFP1A as DVTrabajador, ");
			sql.append("SE5FBIM as ApeliidoPaterno, ");
			sql.append("SE5FBIK as ApellidoMaterno, ");
			sql.append("SE5FBIO as Nombres, ");
			sql.append("a.AF15NA as RemuMismoEmpleador, ");
			sql.append("a.AF15OA as RemuOtroEmpleador, ");
			sql.append("a.AF15PA as RemuIndependiente, ");
			sql.append("a.AF15QA as Subsidios, ");
			sql.append("a.AF15RA as Pensiones, ");
			sql.append("a.AF15SA as TotalIngresos, ");
			sql.append("a.AF15TA as NumeroMeses, ");
			sql.append("a.AF15UA as IngresoPromedio, ");
			sql.append("a.AF15VA as DeclaracionJurada ");
			sql.append("from afdta." + Parametros.getInstance().getParam().getCopiaAFP64() + " a ");
			sql.append("left join afdta.AF02f1 b ");
			sql.append("on a.AFOWA= b.SE5FAJC ");
			sql.append("where afova= " + rutEmpresa + " ");
			sql.append("and AFP7A> " + periodo  + " ");
			sql.append("order by a.AFOWA " );

			//System.out.println(">>query=" + sql);
			ps = cn.prepareStatement(sql.toString());

			rs = ps.executeQuery();
			while (rs.next()) {
				dataEmp.add(rs.getInt(1)+sep+rs.getInt(2)+sep+rs.getString(3)+sep+rs.getInt(4)+sep+rs.getString(5)
						+sep+rs.getString(6)+sep+rs.getString(7)+sep+rs.getString(8)+sep+rs.getInt(9)+sep+rs.getInt(10)
						+sep+rs.getInt(11)+sep+rs.getInt(12)+sep+rs.getInt(13)+sep+rs.getInt(14)+sep+rs.getInt(15)
						+sep+rs.getInt(16)+sep+rs.getInt(17));
			} 

		} catch (Exception e) {
			e.printStackTrace();
			etapa = 0;
		} finally {
			try {
				if (cds != null)
					cds.closeConnection();
				if (ps != null)
					ps.close();
			} catch (Exception ignored) {
			}
		}
		return dataEmp;
	}
	
	public List<AfiliadosPropuestaTO> getTrabajadoresInforme(int periodo, int rutEmpresa) {
		// --clillo 08-06-2018--//
		CustomDataSource cds = new CustomDataSource();
		Connection cn = null;
		//List<String> dataEmpresa= new ArrayList<String>();
		List<AfiliadosPropuestaTO> dataEmp= new ArrayList<AfiliadosPropuestaTO>();
		int etapa = 0;
		PreparedStatement ps = null;
		try {
			cds.openConnection();
			cn = cds.getConnection();
			ResultSet rs = null;
			StringBuffer sql= new StringBuffer();
			String sep=";";
			sql.append("select a.AFP7A as Periodo, ");
			sql.append("a.AFOVA as RutEmpresa, ");
			sql.append("a.AFP0A as DVEmpresa, ");
			sql.append("a.AFOWA as RutTrabajador, ");
			sql.append("a.AFP1A as DVTrabajador, ");
			sql.append("SE5FBIM as ApeliidoPaterno, ");
			sql.append("SE5FBIK as ApellidoMaterno, ");
			sql.append("SE5FBIO as Nombres, ");
			sql.append("a.AF15NA as RemuMismoEmpleador, ");
			sql.append("a.AF15OA as RemuOtroEmpleador, ");
			sql.append("a.AF15PA as RemuIndependiente, ");
			sql.append("a.AF15QA as Subsidios, ");
			sql.append("a.AF15RA as Pensiones, ");
			sql.append("a.AF15SA as TotalIngresos, ");
			sql.append("a.AF15TA as NumeroMeses, ");
			sql.append("a.AF15UA as IngresoPromedio, ");
			sql.append("a.AF15VA as DeclaracionJurada ");
			sql.append("from afdta." + Parametros.getInstance().getParam().getCopiaAFP64() + " a ");
			sql.append("left join afdta.AF02f1 b ");
			sql.append("on a.AFOWA= b.SE5FAJC ");
			sql.append("where afova= " + rutEmpresa + " ");
			sql.append("and AFP7A> " + periodo  + " ");
			sql.append("order by a.AFOWA " );

			//System.out.println(">>query=" + sql);
			ps = cn.prepareStatement(sql.toString());

			rs = ps.executeQuery();
			while (rs.next()) {
				AfiliadosPropuestaTO afiliadoPropTO= new AfiliadosPropuestaTO();
				afiliadoPropTO.setPeriodo(rs.getInt(1));
				afiliadoPropTO.setRutEmpresa(rs.getInt(2));
				afiliadoPropTO.setDvRutEmpresa(rs.getString(3));
				afiliadoPropTO.setRutAfiliado(rs.getInt(4));
				afiliadoPropTO.setDvRutAfiliado(rs.getString(5));
				afiliadoPropTO.setApaterno(rs.getString(6));
				afiliadoPropTO.setAmaterno(rs.getString(7));
				afiliadoPropTO.setNombreAfiliado(rs.getString(8));
				afiliadoPropTO.setRemuneracionEmpleador(rs.getInt(9));
				afiliadoPropTO.setRemuneracionOtroEmpleador(rs.getInt(10));
				afiliadoPropTO.setRemuneracionIndependiente(rs.getInt(11));
				afiliadoPropTO.setRentaSubsidio(rs.getInt(12));
				afiliadoPropTO.setRentaPensiones(rs.getInt(13));
				afiliadoPropTO.setTotalIngresos(rs.getInt(14));
				afiliadoPropTO.setNumeroMeses(rs.getInt(15));
				afiliadoPropTO.setIngresoPromedio(rs.getInt(16));
				afiliadoPropTO.setDeclaracion(rs.getInt(17));
				dataEmp.add(afiliadoPropTO);
				
			} 

		} catch (Exception e) {
			e.printStackTrace();
			etapa = 0;
		} finally {
			try {
				if (cds != null)
					cds.closeConnection();
				if (ps != null)
					ps.close();
			} catch (Exception ignored) {
			}
		}
		return dataEmp;
	}
	
	public Vector<String> getTrabajadoresNoDeclarados(int periodo, int rutEmpresa) {
		// --clillo 08-06-2018--//
		CustomDataSource cds = new CustomDataSource();
		Connection cn = null;
		//List<String> dataEmpresa= new ArrayList<String>();
		Vector<String> dataEmp= new Vector<String>();
		int etapa = 0;
		PreparedStatement ps = null;
		try {
			cds.openConnection();
			cn = cds.getConnection();
			ResultSet rs = null;
			StringBuffer sql= new StringBuffer();
			String sep=";";
			sql.append("select a.AFP7A as Periodo, ");
			sql.append("a.AFOVA as RutEmpresa, ");
			sql.append("a.AFP0A as DVEmpresa, ");
			sql.append("a.AFOWA as RutTrabajador, ");
			sql.append("a.AFP1A as DVTrabajador, ");
			sql.append("SE5FBIM as ApeliidoPaterno, ");
			sql.append("SE5FBIK as ApellidoMaterno, ");
			sql.append("SE5FBIO as Nombres, ");
			sql.append("a.AF15NA as RemuMismoEmpleador, ");
			sql.append("a.AF15OA as RemuOtroEmpleador, ");
			sql.append("a.AF15PA as RemuIndependiente, ");
			sql.append("a.AF15QA as Subsidios, ");
			sql.append("a.AF15RA as Pensiones, ");
			sql.append("a.AF15SA as TotalIngresos, ");
			sql.append("a.AF15TA as NumeroMeses, ");
			sql.append("a.AF15UA as IngresoPromedio, ");
			sql.append("a.AF15VA as DeclaracionJurada ");
			sql.append("from afdta.AFP64F1 a ");
			sql.append("left join afdta.AF02f1 b ");
			sql.append("on a.AFOWA= b.SE5FAJC ");
			sql.append("where afova= " + rutEmpresa + " ");
			sql.append("and AFP7A> " + periodo  + " ");
			sql.append("and AFP8A <> 'D' ");
			sql.append("order by a.AFOWA " );

			//System.out.println(">>query=" + sql);
			ps = cn.prepareStatement(sql.toString());

			rs = ps.executeQuery();
			while (rs.next()) {
				dataEmp.add(rs.getInt(1)+sep+rs.getInt(2)+sep+rs.getString(3)+sep+rs.getInt(4)+sep+rs.getString(5)
						+sep+rs.getString(6)+sep+rs.getString(7)+sep+rs.getString(8)+sep+rs.getInt(9)+sep+rs.getInt(10)
						+sep+rs.getInt(11)+sep+rs.getInt(12)+sep+rs.getInt(13)+sep+rs.getInt(14)+sep+rs.getInt(15)
						+sep+rs.getInt(16)+sep+rs.getInt(17));
			} 

		} catch (Exception e) {
			e.printStackTrace();
			etapa = 0;
		} finally {
			try {
				if (cds != null)
					cds.closeConnection();
				if (ps != null)
					ps.close();
			} catch (Exception ignored) {
			}
		}
		return dataEmp;
	}
	
	public String getOficinaSucursal(int empresa, int afiliado) {
		CustomDataSource cds = new CustomDataSource();
		Connection cn = null;
		PreparedStatement ps = null;
		String ofisuc="1:1";

		try {
			cds.openConnection();
			cn = cds.getConnection();
			ResultSet rs = null;
			String sql = "select CMBA, CM13A from afdta.af03f1 where cmna= "
					+ empresa + " and se5fajc=" + afiliado;
			ps = cn.prepareStatement(sql);

			rs = ps.executeQuery();
			if (rs.next()) {
				int rutemp = rs.getInt(1);
				int rutafi = rs.getInt(2);
				ofisuc= rutemp+":"+rutafi;
			} 

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (cds != null)
					cds.closeConnection();
				if (ps != null)
					ps.close();
			} catch (Exception ignored) {
			}
		}
		return ofisuc;
	}
	
	public int existBash(int rutEmpresa, int periodo){
		PreparedStatement ps = null;
		CustomDataSource cds = new CustomDataSource();
		Connection cn = null;
		int cantidadintentos=0;
		try {
			
			cds.openConnection();
			cn = cds.getConnection();
			
			//valida si ya existe cabecera
			ps = cn.prepareStatement(SELECT_EXIST_EMPRESA);
			ps.setInt(1, rutEmpresa);
			ps.setInt(2, periodo);
			ResultSet rs = ps.executeQuery();
			if(rs != null){
				while(rs.next()){
					cantidadintentos= rs.getInt("AFP66CIN");
				}
			}
			
			
		} catch (SQLException e) {
			log.error("Error: en existBash para proceso bash, "
					+ e.getLocalizedMessage(), e);
		} catch (Exception e) {
			log.error("Error: en existBash para proceso bash, "
					+ e.getLocalizedMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null){
					ps.close();
				}
				if (cds != null)
					cds.closeConnection();
			} catch (SQLException e) {
				log.error("Error: al finalizar conexiones, "
						+ e.getLocalizedMessage(), e);

			}
		}
		return cantidadintentos;
	}
	
	public boolean insertBash(ProcesoBashTO procesoBashTO){
		

		PreparedStatement ps = null;
		CustomDataSource cds = new CustomDataSource();
		Connection cn = null;
		try {
			int cantidadintentos=0;
			cds.openConnection();
			cn = cds.getConnection();
			
			ps = cn.prepareStatement(INSERT_BASH);
			ps.setInt(1, procesoBashTO.getPeriodo());
			ps.setInt(2, procesoBashTO.getEmpresa());
			ps.setString(3, procesoBashTO.getRutaArchivo());
			ps.setString(4, procesoBashTO.getEstado());
			ps.setString(5, procesoBashTO.getUsuarioSube());
			ps.setDate(6, java.sql.Date.valueOf(procesoBashTO.getFechaSubida()));
			ps.setTime(7, java.sql.Time.valueOf(procesoBashTO.getHoraSubida()));
			ps.setInt(8, procesoBashTO.getRegistrosInformados());
			ps.setString(9, procesoBashTO.getOrigen());
			ps.setInt(10, procesoBashTO.getCantidadIntento());
			ps.setTimestamp(11, procesoBashTO.getFechaProcesamiento());
			ps.setInt(12, procesoBashTO.getRegistrosProcesados());
			

			int result = ps.executeUpdate();
			if (result > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			log.error("Error: al insertar informacion para proceso bash, "
					+ e.getLocalizedMessage(), e);
		} catch (Exception e) {
			log.error("Error: al insertar informacion para proceso bash, "
					+ e.getLocalizedMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null){
					ps.close();
				}
				if (cds != null)
					cds.closeConnection();
			} catch (SQLException e) {
				log.error("Error: al finalizar conexiones, "
						+ e.getLocalizedMessage(), e);

			}
		}
		
		
		return false;
	}
	
public boolean updateBash(ProcesoBashTO procesoBashTO){
		

		PreparedStatement ps = null;
		CustomDataSource cds = new CustomDataSource();
		Connection cn = null;
		try {
			
			cds.openConnection();
			cn = cds.getConnection();
			ps = cn.prepareStatement(UPDATE_BASH);
			ps.setString(1, procesoBashTO.getEstado());
			ps.setInt(2, procesoBashTO.getRegistrosInformados());
			ps.setInt(3, procesoBashTO.getRegistrosProcesados());
			ps.setInt(4, procesoBashTO.getRegistrosNoInformados());
			ps.setInt(5, procesoBashTO.getCantidadIntento());
			ps.setString(6, procesoBashTO.getRutaArchivo());
			ps.setTimestamp(7, procesoBashTO.getFechaProcesamiento());
			ps.setDate(8, java.sql.Date.valueOf(procesoBashTO.getFechaSubida()));
			ps.setTime(9, java.sql.Time.valueOf(procesoBashTO.getHoraSubida()));
			ps.setString(10, procesoBashTO.getUsuarioSube());
			ps.setInt(11, procesoBashTO.getEmpresa());
			ps.setInt(12, procesoBashTO.getPeriodo());
			
			

			int result = ps.executeUpdate();
			if (result > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			log.error("Error: al insertar informacion para proceso bash, "
					+ e.getLocalizedMessage(), e);
		} catch (Exception e) {
			log.error("Error: al insertar informacion para proceso bash, "
					+ e.getLocalizedMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null){
					ps.close();
				}
				if (cds != null)
					cds.closeConnection();
			} catch (SQLException e) {
				log.error("Error: al finalizar conexiones, "
						+ e.getLocalizedMessage(), e);

			}
		}
		
		
		return false;
	}

public boolean updateOficinaSucursal(int rutEmpresa, int periodo){
	

	PreparedStatement ps = null;
	CustomDataSource cds = new CustomDataSource();
	Connection cn = null;
	try {
		
		cds.openConnection();
		cn = cds.getConnection();
		StringBuffer query= new StringBuffer();
		query.append("update afdta.AFP64F1 a ");
		query.append(" set (a.AFOYA, a.AFOZA) = ");
		query.append(" (select b.AFOYA, b.AFOZA from afdta." + Parametros.getInstance().getParam().getCopiaAFP64() + " b where a.AFP7A=b.AFP7A and a.AFOVA= b.AFOVA and a.AFOWA=b.AFOWA) ");
		query.append(" where a.AFOVA=  ? ");
		query.append(" and AFP7A= ? ");
		query.append(" and afp8a='D' ");
		query.append(" and exists (select 1 from AFDTA." + Parametros.getInstance().getParam().getCopiaAFP64() + " c where a.AFP7A=c.AFP7A and a.AFOVA=c.AFOVA and a.AFOWA= c.AFOWA) ");
		ps = cn.prepareStatement(query.toString());
		ps.setInt(1, rutEmpresa);
		ps.setInt(2, periodo);
		
		int result = ps.executeUpdate();
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	} catch (SQLException e) {
		log.error("Error: al insertar informacion para proceso update oficina sucursal, "
				+ e.getLocalizedMessage(), e);
	} catch (Exception e) {
		log.error("Error: al insertar informacion para proceso update oficina sucursal, "
				+ e.getLocalizedMessage(), e);
		e.printStackTrace();
	} finally {
		try {
			if(ps!=null){
				ps.close();
			}
			if (cds != null)
				cds.closeConnection();
		} catch (SQLException e) {
			log.error("Error: al finalizar conexiones, "
					+ e.getLocalizedMessage(), e);

		}
	}
	
	
	return false;
}

public int insertBashNoInformados(int rutEmpresa){


	PreparedStatement ps = null;
	CustomDataSource cds = new CustomDataSource();
	Connection cn = null;
	int result=0;
	try {
		cds.openConnection();
		cn = cds.getConnection();
		StringBuffer query= new StringBuffer();
		query.append("insert into afdta.AFP64F1 ");
		query.append("select a.* from AFDTA." + Parametros.getInstance().getParam().getCopiaAFP64() + " a ");
		query.append("exception join afdta.AFP64F1 b ");
		query.append("on a.AFOVA= b.AFOVA and a.AFOWA= b.AFOWA and a.AFP7A= b.AFP7A ");
		query.append("where a.AFOVA = ? ");
		ps = cn.prepareStatement(query.toString());
		ps.setInt(1, rutEmpresa);
		
		result = ps.executeUpdate();
		
	} catch (SQLException e) {
		log.error("Error: al insertar no informados para proceso bash, "
				+ e.getLocalizedMessage(), e);
	} catch (Exception e) {
		log.error("Error: al insertar no informados para proceso bash, "
				+ e.getLocalizedMessage(), e);
		e.printStackTrace();
	} finally {
		try {
			if(ps!=null){
				ps.close();
			}
			if (cds != null)
				cds.closeConnection();
		} catch (SQLException e) {
			log.error("Error: al finalizar conexiones, "
					+ e.getLocalizedMessage(), e);

		}
	}
	return result;
}

public boolean insertaArchivo(List<AfiliadosTO> afiliados, int rutEmpresa, int periodo) {
	

	boolean result = true;
	
	CustomDataSource cds = new CustomDataSource();
	Connection cn = null;
	String espacios = "                                                  ";

	//String[] usuario = this.properties();
	//Utils util = new Utils();
	PreparedStatement ps = null;
	try {
		cds.openConnection();
		cn = cds.getConnection();
		//Borrar registros previos
		ps = cn.prepareStatement("DELETE FROM AFDTA.AFP64F1 WHERE AFOVA= ? AND AFP7A= ? ");
		ps.setInt(1, rutEmpresa);
		ps.setInt(2, periodo);
		ps.execute();
		
		//Insertando registros afiliados
		ps = cn
				.prepareStatement("INSERT INTO AFDTA.AFP64F1 VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		int num=0;
		log.info("Insertando AFDTA.AFP64F1, cantidad:" + afiliados.size());
		for (Iterator iterator = afiliados.iterator(); iterator.hasNext();) {
			AfiliadosTO afil = (AfiliadosTO) iterator.next();


			ps.setInt(1, afil.getPeriodo());
			ps.setInt(2, afil.getRutempresa());
			ps.setInt(3, afil.getRuttrabajador());
			ps.setInt(4, afil.getOficina());
			ps.setInt(5, afil.getSucursal());
			ps.setString(6, afil.getDvempresa());
			ps.setString(7, afil.getDvtrabajador());
			String nombre = afil.getApellidopaterno().trim() + " "
					+ afil.getApellidomaterno().trim() + " "
					+ afil.getNombreafiliado().trim();
			nombre = nombre.concat(espacios);

			for (; nombre.length() < 61; nombre = nombre + " ")
				;

			ps.setString(8, nombre.substring(0, 60));
			ps.setInt(9, afil.getRemuneracionesmismoempleador());
			ps.setInt(10, afil.getOtrasremuneraciones());
			ps.setInt(11, afil.getRentatrabajadorindependiente());
			ps.setInt(12, afil.getSubsidio());
			ps.setInt(13, afil.getPensiones());
			ps.setInt(14, afil.getTotalingresos());
			ps.setInt(15, afil.getNumeromeses());
			ps.setInt(16, afil.getIngresopromedio());
			ps.setInt(17, afil.getTrabajadorconsindeclaracion());
			ps.setString(18, afil.getOrigen());
			ps.setString(19, "D");
			ps.setInt(20, 0);
			ps.setDate(21, new java.sql.Date(new Date().getTime()));
			ps.setTime(22, new java.sql.Time(new Date().getTime()));
			ps.setDate(23, new java.sql.Date(new Date().getTime()));
			ps.setTime(24, new java.sql.Time(new Date().getTime()));
			ps.setString(25, "ENCEMP-ENI"); // SAJKM94
			ps.setString(26, ""); // SAJKM92
			
			ps.addBatch();
			num++;
			if(num%1000==0){
				log.info("Insertando " + num + " registros de " + afiliados.size());
				ps.executeBatch();
			}
		}
		log.info("Insertando ultimos registros ");
		ps.executeBatch();
		
	} catch (Exception e) {
		e.printStackTrace();
		result = false;
	} finally {
		try {
			if (cds != null)
				cds.closeConnection();
			if (ps != null)
				ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	return result;
}

	public PeticionProcesamientoDto selectPeticionProcesamiento(int rutEmpresa, int periodo){
		//List<PeticionProcesamientoDto> listResult = new ArrayList<PeticionProcesamientoDto>();
		
		CustomDataSource cds = new CustomDataSource();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PeticionProcesamientoDto obj=null;
		try {
			cds.openConnection();
			cn = cds.getConnection();
			ps = cn.prepareStatement(SELECT_PETICION_PROCESAMIENTO_BY_EMPRESA);
			ps.setInt(1, rutEmpresa);
			ps.setInt(2, periodo);
			rs = ps.executeQuery();
			if(rs != null){
				if(rs.next()){
					obj = new PeticionProcesamientoDto();
					obj.setIdPeticionProcesamiento(rs.getInt("AFP66ID"));
					obj.setPeriodo(rs.getInt("AFP7A"));
					obj.setRutEmpresa(rs.getInt("AFOVA"));
					obj.setOficina(rs.getInt("AFOYA"));
					obj.setSucursal(rs.getInt("AFOZA"));
					obj.setEstado(rs.getString("AFP66EST"));
					obj.setRutaArchivo(rs.getString("AFP66ARC").trim());
					
					obj.setTotalRegInformados(rs.getInt("AFP66TRI"));
					obj.setFechaProcesamiento((rs.getTimestamp("AFP66FPA")!=null)?new Date(rs.getTimestamp("AFP66FPA").getTime()):null);
					obj.setTotalRegProcesados(rs.getInt("AFP66TRP"));
					obj.setCantidadIntentos(rs.getInt("AFP66CIN"));
					obj.setOrigen(rs.getString("AFP66ORI"));
					obj.setTotalRegNoDeclarados(rs.getInt("AFP66TRN"));
					Date creationDate = new Date(rs.getDate("OBF002").getTime());
					String creationTime = rs.getTime("OBF003").toString();
//					java.sql.Date lasChangeDate = rs.getDate("OBF005");
//					java.sql.Time lasChangeTime = rs.getTime("OBF006");
					
					SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sdfDateComplet = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date fechaSubida = sdfDateComplet.parse(sdfDate.format(creationDate) + " " + creationTime);
					
					obj.setFechaSubida(fechaSubida);
					obj.setUsuario(rs.getString("SAJKM94"));
					//listResult.add(obj);
				}
			}
			
		} catch (SQLException e) {
			log.error("Error: al leer informacion de peticion de proceasmiento, " + e.getLocalizedMessage(), e);
		} catch (Exception e) {
			log.error("Error: al leer informacion de peticion de proceasmiento, " + e.getLocalizedMessage(), e);
		}
		finally{
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if (cds != null)
					cds.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}
	
	public int contarRegistrosAceptados(int rutEmpresa, int periodo){
		int cantidad = 0;
		CustomDataSource cds = new CustomDataSource();
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cds.openConnection();
			cn = cds.getConnection();
			ps = cn.prepareStatement("select count(*) as cantidad from afdta." + Parametros.getInstance().getParam().getCopiaAFP64() + " where afova = ? and afp7a = ? "); //and afp6A = 'E' and sajkm94 = 'CRGAMASIVA'");
			ps.setInt(1, rutEmpresa);
			ps.setInt(2, periodo);
			
			

			ResultSet rs = ps.executeQuery();
			if(rs != null && rs.next()){
				cantidad = rs.getInt("cantidad");
				
			}
			if(rs != null) rs.close();
			
			return cantidad;
		} catch (SQLException e) {
			log.error("Error: al leer informacion de peticion de proceasmiento, " + e.getLocalizedMessage(), e);
		} catch (Exception e) {
			log.error("Error: al leer informacion de peticion de proceasmiento, " + e.getLocalizedMessage(), e);
		}
		finally{
			try {
				if(ps != null) ps.close();
				if (cds != null)
					cds.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cantidad;
	}

	public Map<String, String> selectFecha()throws RentaPropuestasException{
		CustomDataSource cds = new CustomDataSource();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, String> list = new HashMap();
//		List result = new ArrayList();
		try {
			cds.openConnection();
			cn = cds.getConnection();
			ps = cn.prepareStatement(SELECT_FEC_CAMBIO_TRAMO);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.put(rs.getString("AFP80COD").trim(), rs.getString("AFP80VAL").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("Error: al seleccionar una ó mas fechas, "
					+ e.getLocalizedMessage(), e);
		} catch (Exception e) {
			log.error("Error: al seleccionar una ó mas fechas, "
					+ e.getLocalizedMessage(), e);
		} finally {
			try {
				rs.close();
				ps.close();
				if (cds != null){
					cds.closeConnection();
				}
			} catch (SQLException e) {
				log.error("Error: al finalizar conexiones, "
						+ e.getLocalizedMessage(), e);
				throw new RentaPropuestasException(
						"Error: al finalizar conexiones, "
								+ e.getLocalizedMessage());
			}
		}
		return list;
	}
	
	public Map<String, String> selectParam()throws RentaPropuestasException{
		CustomDataSource cds = new CustomDataSource();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, String> list = new HashMap<String, String>();
//		List result = new ArrayList();
		try {
			cds.openConnection();
			cn = cds.getConnection();
			ps = cn.prepareStatement(SELECT_FEC_CAMBIO_TRAMO);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.put(rs.getString("AFP80COD").trim(), rs.getString("AFP80VAL").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("Error: al seleccionar una ó mas fechas, "
					+ e.getLocalizedMessage(), e);
		} catch (Exception e) {
			log.error("Error: al seleccionar una ó mas fechas, "
					+ e.getLocalizedMessage(), e);
		} finally {
			try {
				rs.close();
				ps.close();
				if (cds != null){
					cds.closeConnection();
				}
			} catch (SQLException e) {
				log.error("Error: al finalizar conexiones, "
						+ e.getLocalizedMessage(), e);
				throw new RentaPropuestasException(
						"Error: al finalizar conexiones, "
								+ e.getLocalizedMessage());
			}
		}
		return list;
	}
	
	public int setParams(ParametrosTO params){


		PreparedStatement ps = null;
		CustomDataSource cds = new CustomDataSource();
		Connection cn = null;
		int result=0;
		try {
			int cantidadintentos=0;
			cds.openConnection();
			cn = cds.getConnection();
			
			//Se actualiza Periodo
			ps = cn.prepareStatement(UPDATE_PARAMS);
			ps.setString(1, params.getPeriodoProceso());
			ps.setString(2, "PERIODO_PROCESO");
			result = ps.executeUpdate();
			//Se actualiza Fecha Apertura
			ps.setString(1, params.getFechaApertura());
			ps.setString(2, "FEC_CORTE_INIC");
			result = ps.executeUpdate();
			//Se actualiza Fecha Cierre
			ps.setString(1, params.getFechaCierre());
			ps.setString(2, "FEC_CORTE_FIN");
			result = ps.executeUpdate();
			//Se actualiza Fecha Envio
			ps.setString(1, params.getFechaEnvio());
			ps.setString(2, "FEC_CORTE_ENVIO");
			result = ps.executeUpdate();
			//Se actualiza Tipo Descarga
			ps.setString(1, params.getTipoDescarga());
			ps.setString(2, "TIPO_DESCARGA");
			result = ps.executeUpdate();
			//Se actualiza Copia de Tabla
			ps.setString(1, params.getCopiaAFP64());
			ps.setString(2, "CPY_AFP64");
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			log.error("Error: al insertar no informados para proceso bash, "
					+ e.getLocalizedMessage(), e);
		} catch (Exception e) {
			log.error("Error: al insertar no informados para proceso bash, "
					+ e.getLocalizedMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null){
					ps.close();
				}
				if (cds != null){
					cds.closeConnection();
				}
			} catch (SQLException e) {
				log.error("Error: al finalizar conexiones, "
						+ e.getLocalizedMessage(), e);

			}
		}
		return result;
	}
}

