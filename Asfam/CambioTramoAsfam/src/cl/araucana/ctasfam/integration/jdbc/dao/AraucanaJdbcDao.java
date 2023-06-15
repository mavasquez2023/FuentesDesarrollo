package cl.araucana.ctasfam.integration.jdbc.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.sql.Types;

import org.apache.log4j.Logger;

import cl.araucana.core.registry.Enterprise;
import cl.araucana.ctasfam.business.to.AfiliadosPropuestaTO;
import cl.araucana.ctasfam.business.to.AfiliadosTO;
import cl.araucana.ctasfam.business.to.EstadisticaProcesoTO;
import cl.araucana.ctasfam.business.to.EstadoTO;
import cl.araucana.ctasfam.business.to.EtapasTO;
import cl.araucana.ctasfam.business.to.FlujoTO;
import cl.araucana.ctasfam.business.to.HoldingafiliadosTO;
import cl.araucana.ctasfam.business.to.RentaproTO;
import cl.araucana.ctasfam.resources.util.CustomDataSource;
import cl.araucana.ctasfam.resources.util.Parametros;
import cl.araucana.ctasfam.resources.util.Utils;

public class AraucanaJdbcDao {
	Logger log = Logger.getLogger(this.getClass());
	private static Properties Config = null;

	private static CallableStatement ctmt = null;
	
	//debido a que DB2 no cierra los statements.. debemos agregar uno aparte para actualizar los afiliados en el método updateaAfiliados
	private static CallableStatement ctmtUpdate = null;

	public AraucanaJdbcDao() {
		if (Config == null) {
			Config = new Properties();

			try {
				Config.load(getClass().getClassLoader().getResourceAsStream(
						"configuracion.properties"));
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public String[] properties() {

		String resu[] = new String[5];

		try {
			// Properties Param = new Properties();
			// Param.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
			resu[0] = Config.getProperty("EmpNormal");
			resu[1] = Config.getProperty("EmpNormalA");
			resu[2] = Config.getProperty("EmpHolding");
			resu[3] = Config.getProperty("EmpHoldingA");
			resu[4] = Config.getProperty("PERIODO");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return resu;
	}

	public String[] existeAfiliado(String periodo, String rutemp, String rutaf) {

		// --alexis advise 15-06-2012--//

		CustomDataSource cds = new CustomDataSource();

		Connection conexion = null;
		ResultSet result = null;
		String valor[] = new String[3];

		try {

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


		} catch (Exception e) {
			e.printStackTrace();
			valor[0] = "0";
			valor[1] = "H";
			valor[2] = "0";

		} finally {
			try {
				if (cds.getConnection() != null)
					cds.closeConnection();
				if (ctmt != null)
					ctmt.close();
			} catch (Exception ignored) {
			}
		}

		return valor;

	}

	

	public boolean updateaAfiliados(AfiliadosTO afil) {
		// --alexis advise 15-06-2012--//
		boolean result = true;
		
		CustomDataSource cds = new CustomDataSource();
		Connection conexion = null;
		String espacios = "                                                  ";
		String[] usuario = this.properties();
		Utils util = new Utils();

		try {
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
	/*		
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
*/
			ctmtUpdate.setInt(27, tramo);
			ctmtUpdate.setInt(28, valtramo);
			ctmtUpdate.registerOutParameter(29, Types.INTEGER);

			ctmtUpdate.execute();

			if (ctmtUpdate.getInt(29) == 0)
				result = false;

		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			try {
				if (cds.getConnection() != null)
					cds.closeConnection();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public boolean updateEstado(String periodo, String rutempresa, Time time) {
		// --alexis advise 15-06-2012--//
		boolean result = true;
		
		CustomDataSource cds = new CustomDataSource();
		Connection conexion = null;

		try {
			cds.openConnection();
			conexion = cds.getConnection();

			ctmt = conexion
					.prepareCall("{CALL AFDTA.PA_CAMBIOTRAMO_SET_ESTADO(?,?,?,?)}");

			ctmt.setInt(1, Integer.parseInt(periodo));
			ctmt.setInt(2, Integer.parseInt(rutempresa));
			ctmt.setTime(3, time);
			ctmt.registerOutParameter(4, Types.INTEGER);

			ctmt.execute();

			if (ctmt.getInt(4) == 0)
				result = false;

		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			try {
				if (cds.getConnection() != null)
					cds.closeConnection();
				if (ctmt != null)
					ctmt.close();
			} catch (Exception ex) {
				ex.printStackTrace();

			}
		}
		return result;
	}

	public boolean updateaAfiliadosholding(HoldingafiliadosTO afil) {

		// --alexis advise 15-06-2012--//
		boolean result = true;
		
		CustomDataSource cds = new CustomDataSource();
		Connection conexion = null;
		String espacios = "                                                  ";
		String[] usuario = this.properties();
		Utils util = new Utils();
		try {
			cds.openConnection();
			conexion = cds.getConnection();

			ctmt = conexion
					.prepareCall("{CALL AFDTA.PA_CAMBIOTRAMO_UPDATE_AFIL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			ctmt.setInt(1, afil.getPeriodo());
			ctmt.setInt(2, afil.getRutempresa());
			ctmt.setInt(3, afil.getRuttrabajador());
			ctmt.setInt(4, afil.getOficina());
			ctmt.setInt(5, afil.getSucursal());
			ctmt.setString(6, afil.getDvempresa());
			ctmt.setString(7, afil.getDvtrabajador());
			String nombre = afil.getNombre().trim();
			nombre = nombre.concat(espacios);

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
			ctmt.setTime(24, new java.sql.Time(new Date().getTime()));
			ctmt.setString(25, "");
			ctmt.setString(26, usuario[3].trim());
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

			if (ctmt.getInt(29) == 0)
				result = false;


		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			try {
				if (cds.getConnection() != null)
					cds.closeConnection();
				if (ctmt != null)
					ctmt.close();
			} catch (Exception ex) {
				ex.printStackTrace();

			}
		}
		return result;
	}

	public boolean insertaArchivoholding(HoldingafiliadosTO afil) {

		// --alexis advise 15-06-2012--//
		boolean result = true;
		
		CustomDataSource cds = new CustomDataSource();
		Connection conexion = null;
		String espacios = "                                                  ";
		String[] usuario = this.properties();
		try {
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
			String nombre = afil.getNombre();
			nombre = nombre.concat(espacios);

			ctmt.setString(8, nombre.substring(0, 60));
			ctmt.setInt(9, afil.getIngresopromedio());
			ctmt.setInt(10, afil.getOtrasremuneraciones());
			ctmt.setInt(11, afil.getRentatrabajadorindependiente());
			ctmt.setInt(12, afil.getSubsidio());
			ctmt.setInt(13, afil.getPensiones());
			ctmt.setInt(14, afil.getIngresopromedio());
			ctmt.setInt(15, afil.getNumeromeses());
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
			ctmt.registerOutParameter(29, Types.INTEGER);

			ctmt.execute();

			if (ctmt.getInt(29) == 0)
				result = false;

		} catch (Exception e) {

			result = false;
		} finally {
			try {
				if (cds.getConnection() != null)
					cds.closeConnection();
				if (ctmt != null)
					ctmt.close();
			} catch (Exception ignored) {
			}
		}
		return result;
	}

	// flujo

	public String[] getEtapa(EtapasTO etapas) {
		// --alexis advise 15-06-2012--//
		CustomDataSource cds = new CustomDataSource();
		Connection conexion = null;
		String etapa[] = new String[5];

		try {
			cds.openConnection();
			conexion = cds.getConnection();
			ResultSet rs = null;
			String sql = "select count(*) as nRegistros,"
					+ " ifnull(sum(case when AFETAPA=1 then 1 else 0 end),0) as nUnos, "
					+ " ifnull(sum(case when AFETAPA=2 then 1 else 0 end),0) as nDos, "
					+ " ifnull(sum(case when AFETAPA=3 then 1 else 0 end),0) as nTres, "
					+ " ifnull(sum(case when AFETAPA=4 then 1 else 0 end),0) as nCuatros "
					+ " FROM AFDTA . CTFLUJO where AFPERIODO=? and AFRUTEMP in("
					+ etapas.getEmpresa() + " ) and AFRUTENC=?";
			ctmt = conexion.prepareCall(sql);

			ctmt.setInt(1, Integer.parseInt(etapas.getPeriodo()));
			ctmt.setInt(2, Integer.parseInt(etapas.getRutEncargado()));

			rs = ctmt.executeQuery();
			rs.next();
			etapa[0] = String.valueOf(rs.getInt(1));
			etapa[1] = String.valueOf(rs.getInt(2));
			etapa[2] = String.valueOf(rs.getInt(3));
			etapa[3] = String.valueOf(rs.getInt(4));
			etapa[4] = String.valueOf(rs.getInt(5));

		} catch (Exception e) {
			e.printStackTrace();
			etapa = null;
		} finally {
			try {
				if (cds.getConnection() != null)
					cds.closeConnection();
				if (ctmt != null)
					ctmt.close();
			} catch (Exception ignored) {
			}
		}
		return etapa;
	}

	public Collection getEmpresas(String userID) {
		// --alexis advise 15-06-2012--//
		CustomDataSource cds = new CustomDataSource();
		Connection conexion = null;
		ArrayList lista = new ArrayList();
		Enterprise empresa = new Enterprise();

		try {
			cds.openConnection();
			conexion = cds.getConnection();
			ResultSet rs = null;

			String sql = "select A.RUTEMP, B.RAZSOC  FROM "
					+ " DUDTA.LDAP2500 A  inner join DUDTA.LDAP2000 "
					+ " B on A.RUTEMP=B.RUTEMP WHERE A.RUTUSR= '" + userID
					+ "' and  DNAPPROL like 'cn=PorEmpAdheEnc%'";

			ctmt = conexion.prepareCall(sql);
			rs = ctmt.executeQuery();

			while (rs.next()) {

				empresa = new Enterprise();

				empresa.setID(rs.getString(1).trim());
				empresa.setName(rs.getString(2).trim());

				lista.add(empresa);
			}

			cds.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
			cds.closeConnection();
			lista = null;
		} finally {
			try {
				if (cds.getConnection() != null)
					cds.closeConnection();
				if (ctmt != null)
					ctmt.close();
			} catch (Exception ignored) {
			}
		}
		return lista;
	}
	
	
	
	
	
	
	
	public int consultaEstado(int periodo, int empresa) {
		// --alexis advise 15-06-2012--//
		CustomDataSource cds = new CustomDataSource();
		Connection conexion = null;

		int etapa = 0;

		try {
			cds.openConnection();
			conexion = cds.getConnection();
			ResultSet rs = null;
			String sql = "select  AFETAPA from AFDTA.CTFLUJO where AFPERIODO="
					+ periodo + " and AFRUTEMP=" + empresa;
			ctmt = conexion.prepareCall(sql);

			rs = ctmt.executeQuery();
			if (rs.next()) {
				etapa = rs.getInt(1);

			} else {
				etapa = 0;

			}

		} catch (Exception e) {
			e.printStackTrace();
			cds.closeConnection();
			etapa = 0;
		} finally {
			try {
				if (cds.getConnection() != null)
					cds.closeConnection();
				if (ctmt != null)
					ctmt.close();
			} catch (Exception ignored) {
			}
		}
		return etapa;
	}
	
	
	
	public boolean updateFlujo(FlujoTO flujo) {
		// --alexis advise 15-06-2012--//
		boolean result = true;
		
		CustomDataSource cds = new CustomDataSource();
		Connection conexion = null;

		try {
			cds.openConnection();
			conexion = cds.getConnection();

			ctmt = conexion
					.prepareCall("{CALL   AFDTA.PA_CAMBIOTRAMO_UPDATE_FLUJO(?,?,?,?,?,?)}");

			ctmt.setInt(1, Integer.parseInt(flujo.getPeriodo()));
			ctmt.setInt(2, Integer.parseInt(flujo.getRutempresa()));
			ctmt.setInt(3, Integer.parseInt(flujo.getRutencargado()));
			ctmt.setInt(4, Integer.parseInt(flujo.getEtapa()));
			ctmt.setString(5, flujo.getISAJKM92());
			ctmt.registerOutParameter(6, Types.INTEGER);

			ctmt.execute();

			if (ctmt.getInt(6) == 0)
				result = false;

		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			try {
				if (cds.getConnection() != null)
					cds.closeConnection();
				if (ctmt != null)
					ctmt.close();
			} catch (Exception ex) {
				ex.printStackTrace();

			}
		}
		return result;
	}

	public boolean InsertaFlujo(FlujoTO flujo) {

		// --alexis advise 15-06-2012--//
		boolean result = true;
		
		CustomDataSource cds = new CustomDataSource();
		Connection conexion = null;

		try {
			cds.openConnection();
			conexion = cds.getConnection();

			ctmt = conexion
					.prepareCall("{CALL AFDTA.PA_CAMBIOTRAMO_SET_FLUJO(?,?,?,?,?,?)}");

			ctmt.setInt(1, Integer.parseInt(flujo.getPeriodo()));
			ctmt.setInt(2, Integer.parseInt(flujo.getRutempresa()));
			ctmt.setInt(3, Integer.parseInt(flujo.getRutencargado()));
			ctmt.setString(4, flujo.getISAJKM94());
			ctmt.setString(5, flujo.getISAJKM92());

			ctmt.registerOutParameter(6, Types.INTEGER);

			ctmt.execute();

			if (ctmt.getInt(6) == 0)
				result = false;


		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			try {
				if (cds.getConnection() != null)
					cds.closeConnection();
				if (ctmt != null)
					ctmt.close();
			} catch (Exception ignored) {
			}
		}
		return result;
	}

	public boolean InsertaBitacora(FlujoTO flujo) {

		// --alexis advise 15-06-2012--//
		boolean result = true;
		
		CustomDataSource cds = new CustomDataSource();
		Connection conexion = null;

		try {
			cds.openConnection();
			conexion = cds.getConnection();

			ctmt = conexion
					.prepareCall("{CALL AFDTA.PA_CAMBIOTRAMO_SET_BITA(?,?,?,?,?,?,?,?,?,?)}");

			ctmt.setInt(1, Integer.parseInt(flujo.getPeriodo()));
			ctmt.setInt(2, Integer.parseInt(flujo.getRutempresa()));
			String temp[] = flujo.getRutencargado().split("-");
			ctmt.setInt(3, Integer.parseInt(temp[0]));
			ctmt.setString(4, flujo.getEtapa());
			ctmt.setString(5, flujo.getOperacion());
			ctmt.setString(6, flujo.getNombrearchivo());
			ctmt.setInt(7, flujo.getCantregistros());
			ctmt.setString(8, flujo.getISAJKM94());
			ctmt.setString(9, flujo.getISAJKM92());

			ctmt.registerOutParameter(10, Types.INTEGER);

			ctmt.execute();

			if (ctmt.getInt(10) == 0)
				result = false;

		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			try {
				if (cds.getConnection() != null)
					cds.closeConnection();
				if (ctmt != null)
					ctmt.close();
			} catch (Exception ignored) {
			}
		}
		return result;
	}

	public boolean InsertaRenta(RentaproTO renta) {

		boolean result = true;
		
		CustomDataSource cds = new CustomDataSource();
		Connection conexion = null;

		try {
			cds.openConnection();
			conexion = cds.getConnection();

			ctmt = conexion
					.prepareCall("{CALL  AFDTA.PA_CAMBIOTRAMO_SET_RENTAPRO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

			ctmt.setInt(1, Integer.parseInt(renta.getRutEmpresa()));
			ctmt.setString(2, renta.getDvrutempresa());
			ctmt.setInt(3, Integer.parseInt(renta.getRutencargado()));
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
			ctmt.setString(14, renta.getNombreencargado());

			ctmt.registerOutParameter(15, Types.INTEGER);

			ctmt.execute();

			if (ctmt.getInt(15) == 0)
				result = false;

		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			try {
				if (cds.getConnection() != null)
					cds.closeConnection();
				if (ctmt != null)
					ctmt.close();
			} catch (Exception ignored) {
			}
		}
		return result;
	}

	public String[] getValores(String id, String tipo) {
		// --alexis advise 15-06-2012--//
		CustomDataSource cds = new CustomDataSource();
		Connection conexion = null;
		String v[] = new String[2];

		try {
			cds.openConnection();
			conexion = cds.getConnection();

			ctmt = conexion.prepareCall("{CALL AFDTA.GETDESCRIPCION(?,?,?,?)}");

			ctmt.setString(1, id);
			ctmt.setString(2, tipo);
			ctmt.registerOutParameter(3, Types.VARCHAR);
			ctmt.registerOutParameter(4, Types.VARCHAR);
			ctmt.executeQuery();
			v[0] = ctmt.getString(3);
			v[1] = ctmt.getString(4);

		} catch (Exception e) {
			e.printStackTrace();
			cds.closeConnection();
			v=null;
		} finally {
			try {
				if (cds.getConnection() != null)
					cds.closeConnection();
				if (ctmt != null)
					ctmt.close();
			} catch (Exception ignored) {
			}
		}
		return v;
	}

	public int[][] getTexto(String ext) {

		// --alexis advise 15-06-2012--//
		CustomDataSource cds = new CustomDataSource();
		ResultSet rsDatos = null;
		Connection conexion = null;
		int v[][] = new int[21][2];

		try {
			cds.openConnection();
			conexion = cds.getConnection();

			ctmt = conexion.prepareCall("{call AFDTA.GETTEXTOHOL(?)}");
			ctmt.setString(1, ext.toUpperCase());
			rsDatos = ctmt.executeQuery();
			int i = 0;
			while (rsDatos.next()) {

				v[i][0] = rsDatos.getInt(1);
				v[i][1] = rsDatos.getInt(2);

				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
			v=null;
		} finally {
			try {
				if (cds.getConnection() != null)
					cds.closeConnection();
				if (ctmt != null)
					ctmt.close();
			} catch (Exception ignored) {
			}
		}
		return v;
	}

	public List getAfiliadosNoInformados(int oficina, int sucursal, int empresa)
			throws IOException {

		CustomDataSource cds = new CustomDataSource();
		ResultSet rsDatos = null;
		Connection conexion = null;
		AfiliadosPropuestaTO oafil = null;
		List resu = new ArrayList();

		try {
			cds.openConnection();
			conexion = cds.getConnection();
			String Periodo = this.properties()[4];

			ctmt = conexion
					.prepareCall("{call AFDTA.PA_GET_AFILIADOS_SALDOS (?,?,?,?)}");
			ctmt.setInt(1, Integer.parseInt(Periodo));
			ctmt.setInt(2, empresa);
			ctmt.setInt(3, oficina);
			ctmt.setInt(4, sucursal);
			rsDatos = ctmt.executeQuery();

			while (rsDatos.next()) {

				oafil = new AfiliadosPropuestaTO();
				oafil.setPeriodo(Integer.parseInt(Periodo));
				oafil.setOficina(rsDatos.getInt(1));
				oafil.setSucursal(rsDatos.getInt(2));
				oafil.setRutEmpresa(rsDatos.getInt(3));
				oafil.setDvRutEmpresa(rsDatos.getString(4));
				oafil.setRutAfiliado(rsDatos.getInt(5));
				oafil.setDvRutAfiliado(rsDatos.getString(6));
				oafil.setNombreAfiliado(rsDatos.getString(7));
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
				oafil.setTramo(rsDatos.getInt(19));
				oafil.setValorTramo(rsDatos.getInt(20));

				resu.add(oafil);
			}

			cds.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
			resu = null;
		} finally {
			try {
				if (cds.getConnection() != null)
					cds.closeConnection();
				if (ctmt != null)
					ctmt.close();
			} catch (Exception ignored) {
			}
		}
		return resu;
	}

	public List getAfiliadosIngresados(int oficina, int sucursal, int empresa)
			throws IOException {

		CustomDataSource cds = new CustomDataSource();
		ResultSet rsDatos = null;
		Connection conexion = null;
		AfiliadosPropuestaTO oafil = null;
		List resu = new ArrayList();

		try {
			cds.openConnection();
			conexion = cds.getConnection();
			String Periodo = this.properties()[4];

			ctmt = conexion
					.prepareCall("{call AFDTA.PA_GET_AFILIADOS_INSERTADOS (?,?,?,?)}");
			ctmt.setInt(1, Integer.parseInt(Periodo));
			ctmt.setInt(2, empresa);
			ctmt.setInt(3, oficina);
			ctmt.setInt(4, sucursal);
			rsDatos = ctmt.executeQuery();

			while (rsDatos.next()) {

				oafil = new AfiliadosPropuestaTO();
				oafil.setPeriodo(Integer.parseInt(Periodo));
				oafil.setOficina(rsDatos.getInt(1));
				oafil.setSucursal(rsDatos.getInt(2));
				oafil.setRutEmpresa(rsDatos.getInt(3));
				oafil.setDvRutEmpresa(rsDatos.getString(4));
				oafil.setRutAfiliado(rsDatos.getInt(5));
				oafil.setDvRutAfiliado(rsDatos.getString(6));
				oafil.setNombreAfiliado(rsDatos.getString(7));
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
				oafil.setTramo(rsDatos.getInt(19));
				oafil.setValorTramo(rsDatos.getInt(20));

				resu.add(oafil);
			}

		} catch (Exception e) {
			e.printStackTrace();
			resu = null;
		} finally {
			try {
				if (cds.getConnection() != null)
					cds.closeConnection();
				if (ctmt != null)
					ctmt.close();
			} catch (Exception ignored) {
			}
		}
		return resu;
	}
	
	public int getAfama(String periodo, String rutemp, String rutaf) {

		// --alexis advise 15-06-2012--//

		CustomDataSource cds = new CustomDataSource();

		Connection conexion = null;
		ResultSet result = null;
		int valor = 0;

		try {

			cds.openConnection();
			conexion = cds.getConnection();
			String sql = "SELECT AFAMA , COUNT ( * ) AS CONTADOR FROM AFDTA . AFP64F1 WHERE AFP7A = "
					+ periodo
					+ " AND AFOVA = "
					+ rutemp
					+ " AND AFOWA = "
					+ rutaf + " GROUP BY AFAMA  ";

			ctmt = conexion.prepareCall(sql);

			result = ctmt.executeQuery();

			if (result.next()) {
				valor = result.getInt(1);

			} else {
				valor = 0;
			}


		} catch (Exception e) {
			e.printStackTrace();
			valor = 0;

		} finally {
			try {
				if (cds.getConnection() != null)
					cds.closeConnection();
				if (ctmt != null)
					ctmt.close();
			} catch (Exception ignored) {
			}
		}

		return valor;

	}
	
	 public List getEstadisticaProceso(int periodo)
	 {
		 List<EstadisticaProcesoTO> data = new ArrayList<EstadisticaProcesoTO>();
		 
		 CustomDataSource cds = new CustomDataSource();
		 Connection conexion = cds.getConnection();
		 
		 try {
			 StringBuffer query= new StringBuffer();
			 query.append("");
			 query.append("select COD_OFI, NOM_OFI, RUT_EMP, NOM_EMP, ");
			 query.append("max(TD) as Total_Declarados, ");
			 query.append("max(TNI) as Total_No_Informados, ");
			 query.append("max(TP) as Total_Propuesta, ");
			 query.append("max(TNI)*100/max(TP) as Porcentaje_Pendiente, ");
			 query.append("(max(TD)+max(TNI)-max(TP)) as Total_Nuevos ");
			 query.append("from( ");
			 query.append("select a.AFOYA as COD_OFI, b.CMCA as NOM_OFI, a.AFOVA||'-'||a.AFP0A as RUT_EMP, ");
			 query.append("c.CMPA as NOM_EMP, count(1) as TD,0 as TNI, 0 as TP ");
			 query.append("from afdta.AFP64F1 a join cmdta.cm01f1 b ");
			 query.append("on a.AFOYA= b.CMBA ");
			 query.append("join cmdta.cm02f1 c ");
			 query.append("on a.AFOVA= c.CMNA ");
			 query.append("where AFP8A='D' ");
			 query.append("and a.AFP7A> ? ");
			 query.append("group by a.AFOYA, b.CMCA, a.AFOVA||'-'||a.AFP0A, c.CMPA ");
			 query.append("UNION ");
			 query.append("select a.AFOYA as COD_OFI, b.CMCA as NOM_OFI, a.AFOVA||'-'||a.AFP0A as RUT_EMP, ");
			 query.append("c.CMPA as NOM_EMP, 0 as TD, count(1) as TNI, 0 as TP ");
			 query.append("from afdta.AFP64F1 a join cmdta.cm01f1 b ");
			 query.append("on a.AFOYA= b.CMBA ");
			 query.append("join cmdta.cm02f1 c ");
			 query.append("on a.AFOVA= c.CMNA ");
			 query.append("where AFP8A in ('I', '') ");
			 query.append("and a.AFP7A> ? ");
			 query.append("group by a.AFOYA, b.CMCA, a.AFOVA||'-'||a.AFP0A, c.CMPA ");
			 query.append("UNION ");
			 query.append("select a.AFOYA as COD_OFI, b.CMCA as NOM_OFI, a.AFOVA||'-'||a.AFP0A as RUT_EMP, ");
			 query.append("c.CMPA as NOM_EMP, 0 as TD, 0 as TNI, count(1) as TP ");
			 query.append("from afdta." + Parametros.getInstance().getParam().getCopiaAFP64() + " a join cmdta.cm01f1 b ");
			 query.append("on a.AFOYA= b.CMBA ");
			 query.append("join cmdta.cm02f1 c ");
			 query.append("on a.AFOVA= c.CMNA ");
			 query.append("where a.AFP7A> ? ");
			 query.append("group by a.AFOYA, b.CMCA, a.AFOVA||'-'||a.AFP0A, c.CMPA) as A ");
			 query.append("group by COD_OFI, NOM_OFI, RUT_EMP, NOM_EMP ");
			 query.append("order by 1, 3 ");
			 
			 CallableStatement ctmt = conexion.prepareCall(query.toString());
			 
			 ctmt.setInt(1, periodo);
			 ctmt.setInt(2, periodo);
			 ctmt.setInt(3, periodo);
			 
			 ResultSet rsDatos = ctmt.executeQuery();
			 
			 while(rsDatos.next()){
				 data.add(new EstadisticaProcesoTO(
						 rsDatos.getString("COD_OFI"),
						 rsDatos.getString("NOM_OFI"),
						 rsDatos.getString("RUT_EMP"),
						 rsDatos.getString("NOM_EMP"),
						 String.valueOf(rsDatos.getInt("Total_Declarados")),
						 String.valueOf(rsDatos.getInt("Total_No_Informados")),
						 String.valueOf(rsDatos.getInt("Total_Propuesta")),
						 String.valueOf(rsDatos.getInt("Total_Nuevos")),
						 String.valueOf(Math.round(rsDatos.getDouble("Porcentaje_Pendiente")))
						 )
				 );
			 }
		 
		 } catch (SQLException e) {
				e.printStackTrace();
		 }
		 finally {
				try {
					if (cds.getConnection() != null)
						cds.closeConnection();
					if (ctmt != null)
						ctmt.close();
				} catch (Exception ignored) {
				}
			}
		 return data;
	 }
}