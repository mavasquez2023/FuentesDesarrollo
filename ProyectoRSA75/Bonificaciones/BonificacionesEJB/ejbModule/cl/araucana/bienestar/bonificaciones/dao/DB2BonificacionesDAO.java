package cl.araucana.bienestar.bonificaciones.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.bienestar.bonificaciones.common.Constants;
import cl.araucana.bienestar.bonificaciones.model.AporteCobertura;
import cl.araucana.bienestar.bonificaciones.model.Carga;
import cl.araucana.bienestar.bonificaciones.model.Caso;
import cl.araucana.bienestar.bonificaciones.model.Cobertura;
import cl.araucana.bienestar.bonificaciones.model.CoberturaAdicional;
import cl.araucana.bienestar.bonificaciones.model.Concepto;
import cl.araucana.bienestar.bonificaciones.model.Convenio;
import cl.araucana.bienestar.bonificaciones.model.Cuota;
import cl.araucana.bienestar.bonificaciones.model.DetalleCaso;
import cl.araucana.bienestar.bonificaciones.model.Evento;
import cl.araucana.bienestar.bonificaciones.model.InformacionAsiento;
import cl.araucana.bienestar.bonificaciones.model.Producto;
import cl.araucana.bienestar.bonificaciones.model.Profesional;
import cl.araucana.bienestar.bonificaciones.model.Rango;
import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.model.Talonario;
import cl.araucana.bienestar.bonificaciones.model.Vale;
import cl.araucana.bienestar.bonificaciones.model.VigenciaRango;
import cl.araucana.bienestar.bonificaciones.vo.AgrupacionCobertura;
import cl.araucana.bienestar.bonificaciones.vo.CasoAbiertoVO;
import cl.araucana.bienestar.bonificaciones.vo.CasoVO;
import cl.araucana.bienestar.bonificaciones.vo.CasosDescontadosSinFormatoVO;
import cl.araucana.bienestar.bonificaciones.vo.ContabilidadPendienteVO;
import cl.araucana.bienestar.bonificaciones.vo.ContabilidadVO;
import cl.araucana.bienestar.bonificaciones.vo.CuotaPendienteVO;
import cl.araucana.bienestar.bonificaciones.vo.CuotaPrestamoVO;
import cl.araucana.bienestar.bonificaciones.vo.DatosInconsistenciaVO;
import cl.araucana.bienestar.bonificaciones.vo.DescuentoAplicadosSocioVO;
import cl.araucana.bienestar.bonificaciones.vo.DescuentoPendienteSocioVO;
import cl.araucana.bienestar.bonificaciones.vo.DescuentoTotalSocioVO;
import cl.araucana.bienestar.bonificaciones.vo.DescuentosVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleAporteBienestarVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleBancoVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleCasosDescontadosConvenioVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleDescuentosConveniosVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleDescuentosOficinaVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleDescuentosSocioVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleInformeReembolsosVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleMovimientoPreCasoVO;
import cl.araucana.bienestar.bonificaciones.vo.DetallePagoConvenioVO;
import cl.araucana.bienestar.bonificaciones.vo.InfoMovimientoPreCasoVO;
import cl.araucana.bienestar.bonificaciones.vo.InformeDescuentosConveniosVO;
import cl.araucana.bienestar.bonificaciones.vo.InformeDescuentosVO;
import cl.araucana.bienestar.bonificaciones.vo.InformePagoConvenioVO;
import cl.araucana.bienestar.bonificaciones.vo.PagoConPrestamoVO;
import cl.araucana.bienestar.bonificaciones.vo.PagoConvenioPendienteCuotaVO;
import cl.araucana.bienestar.bonificaciones.vo.PagoConvenioPendienteVO;
import cl.araucana.bienestar.bonificaciones.vo.PagoConvenioVO;
import cl.araucana.bienestar.bonificaciones.vo.PagoPendienteVO;
import cl.araucana.bienestar.bonificaciones.vo.ParamOperacionesVO;
import cl.araucana.bienestar.bonificaciones.vo.ParametroVO;
import cl.araucana.bienestar.bonificaciones.vo.RangoCoberturaSinFormatoVO;
import cl.araucana.bienestar.bonificaciones.vo.ReembolsoSocioVO;
import cl.araucana.bienestar.bonificaciones.vo.ReembolsoTotalVO;
import cl.araucana.bienestar.bonificaciones.vo.ReembolsoVO;
import cl.araucana.bienestar.bonificaciones.vo.ResumenMovimientosConvenioVO;
import cl.araucana.bienestar.bonificaciones.vo.SocioInactivoConCasoAbierto;
import cl.araucana.bienestar.bonificaciones.vo.TalonarioVO;
import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;

import com.schema.util.FileSettings;
import com.schema.util.GeneralException;
import com.schema.util.dao.DB2Utils;

/**
 * @author Alejandro Sepúlveda
 */
public class DB2BonificacionesDAO implements BonificacionesDAO {

	Logger logger = Logger.getLogger(DB2BonificacionesDAO.class);

	private final static String PREFIX = "DB2-";

	private String bonifDatabase;

	private String bonifJNDIDataSource;

	private String personalDatabase;

//	private String personalJNDIDataSource;

	private String benefDatabase;

	private String usuarioDatabase;

	/**
	 * Constructor de DAO Recupera nombre de Bases de Datos utilizadas
	 */
	public DB2BonificacionesDAO() {

		// Bonificacones
		bonifDatabase = FileSettings.getValue(
				AppConfig.getInstance().settingsFileName,
				"/application-settings/databases/bonificaciones");
		bonifJNDIDataSource = FileSettings.getValue(
				AppConfig.getInstance().settingsFileName,
				"/application-settings/jdbc/bonificaciones");

		// Personal
		personalDatabase = FileSettings.getValue(
				AppConfig.getInstance().settingsFileName,
				"/application-settings/databases/personal");
//		personalJNDIDataSource = FileSettings.getValue(
//				AppConfig.getInstance().settingsFileName,
//				"/application-settings/jdbc/personal");

		usuarioDatabase = FileSettings.getValue(
				AppConfig.getInstance().settingsFileName,
				"/application-settings/common/userinfo/user");// RSAAVEDRA en testdesa

		benefDatabase = FileSettings.getValue(
				AppConfig.getInstance().settingsFileName,
				"/application-settings/databases/beneficios");

	}

	/**
	 * Obtiene la lista de Socios de Bienestar
	 * 
	 * @param Socio
	 * @return ArrayList de Socio
	 * @throws Exception
	 */
	public ArrayList getListaSocios(Socio socio) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String instruccionSql = null;
		boolean filtrarPorRut = false;
		boolean filtrarPorNombre = false;
		boolean filtrarPorEstado = false;
		int contador = 0;

		String command = "SELECT SUBSTR(FNCODFUN, 1, 8) SOCRUT, "
				+ "SUBSTR(FNCODFUN, 10, 10) SOCDV, "
				+ "FNNOMBRES SOCNOMBRE, FNAPELLPAT SOCAPEPAT, "
				+ "FNAPELLMAT SOCAPEMAT, FNFEC1 SOCFECING, " //antes de req 6083 se leía: FNFECHING SOCFECING, "
				+ "FNFECHNAC SOCFECNAC, FNSEXO SOCSEXO, "
				+ "FNFECHEGR SOCFECEGR, " + "FNESTADO SOCEST, "
				+ "FNCODCARG SOCCARG," + "FNESTCIVIL SOCESTCIV, "
				+ "FNLPAGO SOCOFICINA " + "FROM "+bonifDatabase+".funcionarios ";

		// verifico si vienen filtros
		if (socio.getRut() != null && (socio.getRut()).length() > 0) {
			command = command + " WHERE FNCODFUN like ?";
			filtrarPorRut = true;
		}
		if (socio.getNombre() != null && (socio.getNombre()).length() > 0) {
			if (filtrarPorRut)
				instruccionSql = " AND ";
			else
				instruccionSql = " WHERE ";

			command = command
					+ instruccionSql
					+ "(UCASE(FNNOMBRES) LIKE ? OR UCASE(FNAPELLPAT) like ? OR UCASE(FNAPELLMAT) LIKE ?)";
			filtrarPorNombre = true;
		}

		if (socio.getEstado() != null && (socio.getEstado()).length() > 0) {
			if (filtrarPorRut || filtrarPorNombre)
				instruccionSql = " AND ";
			else
				instruccionSql = " WHERE ";
			command = command + instruccionSql + "(FNESTADO = ?)";
			filtrarPorEstado = true;
		}

		command = command + " ORDER BY SOCRUT";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			if (filtrarPorRut) {
				contador++;
				stmt.setString(contador, '%' + socio.getRut() + '%');
			}
			if (filtrarPorNombre) {
				contador++;
				stmt.setString(contador,
						'%' + socio.getNombre().toUpperCase() + '%');
				contador++;
				stmt.setString(contador,
						'%' + socio.getNombre().toUpperCase() + '%');
				contador++;
				stmt.setString(contador,
						'%' + socio.getNombre().toUpperCase() + '%');
			}
			if (filtrarPorEstado) {
				contador++;
				stmt.setString(contador, socio.getEstado());
			}

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				Socio soc = new Socio();
				soc.setRut(ors.getString("SOCRUT"));
				soc.setDigito(ors.getString("SOCDV").charAt(0));
				soc.setNombre(ors.getString("SOCNOMBRE"));
				soc.setApePat(ors.getString("SOCAPEPAT"));
				soc.setApeMat(ors.getString("SOCAPEMAT"));
				soc.setFecNac(ors.getDate("SOCFECNAC"));
				soc.setFecIng(ors.getDate("SOCFECING"));
				soc.setFecEgr(ors.getDate("SOCFECEGR"));
				// soc.setMail(ors.getString("SOCMAIL"));
				soc.setSexo(ors.getString("SOCSEXO"));
				soc.setCodCargo(ors.getString("SOCCARG"));
				soc.setEstado(ors.getString("SOCEST"));
				soc.setEstCivil(ors.getString("SOCESTCIV"));
				soc.setOficina(ors.getString("SOCOFICINA"));
				retorno.add(soc);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return retorno;
	}

	/**
	 * Obtiene un Socio de Bienestar
	 * 
	 * @param rut
	 *            del Socio
	 * @return Socio
	 * @throws Exception
	 */
	public Socio getSocio(String rut) throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command = "SELECT SUBSTR(FNCODFUN, 1, 8) SOCRUT, "
				+ "SUBSTR(FNCODFUN, 10, 10) SOCDV, "
				+ "FNNOMBRES SOCNOMBRE, FNAPELLPAT SOCAPEPAT, "
				+ "FNAPELLMAT SOCAPEMAT, FNFEC1 SOCFECING, " //antes de req 6083 se leía: FNFECHING SOCFECING, "
				+ "FNFECHNAC SOCFECNAC, FNSEXO SOCSEXO, "
				+ "FNFECHEGR SOCFECEGR, " + "FNESTADO SOCEST, "
				+ "FNCODCARG SOCCARG," + "FNESTCIVIL SOCESTCIV, "
				+ "FNLPAGO SOCOFICINA, " + "FNTXA2 SOCDIRPAR, "
				+ "FNCOD1 SOCFONOPAR, " + "FNTXA1 SOCCIUDAD, "
				+ "FNABV1 SOCCOMUNA, " + "FNCOD10 SOCFONCOM "
				+ "FROM "+bonifDatabase+".funcionarios WHERE FNCODFUN like ?";

		Socio socio = new Socio();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, '%' + rut + '%');

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			if (ors.next()) {
				socio.setRut(ors.getString("SOCRUT").trim());
				socio.setDigito(ors.getString("SOCDV").charAt(0));
				socio.setNombre(ors.getString("SOCNOMBRE"));
				socio.setApePat(ors.getString("SOCAPEPAT"));
				socio.setApeMat(ors.getString("SOCAPEMAT"));
				socio.setFecNac(ors.getDate("SOCFECNAC"));
				socio.setFecIng(ors.getDate("SOCFECING"));
				socio.setFecEgr(ors.getDate("SOCFECEGR"));
				// socio.setMail(ors.getString("SOCMAIL"));
				socio.setSexo(ors.getString("SOCSEXO"));
				socio.setCodCargo(ors.getString("SOCCARG") != null ? ors
						.getString("SOCCARG") : null);
				socio.setEstado(ors.getString("SOCEST"));
				socio.setEstCivil(ors.getString("SOCESTCIV"));
				socio.setOficina(ors.getString("SOCOFICINA"));
				socio.setDomicilioParticular(ors.getString("SOCDIRPAR"));
				socio.setCodComuna(ors.getString("SOCCOMUNA"));
				socio.setCodCiudad(ors.getString("SOCCIUDAD"));
				socio.setFonoParticular(ors.getString("SOCFONOPAR"));
				socio.setFonoEmergencia(ors.getString("SOCFONCOM"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return socio;
	}

	/**
	 * Obtiene la lista de Cargas Familiares registradas en Bienestar antes de
	 * RAC 10523
	 * 
	 * @param Carga
	 * @return ArrayList de Carga
	 * @throws Exception
	 */
	public ArrayList getListaCargas_OLD(Carga carga) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		boolean filtrarPorRutCarga = false;
		boolean filtrarPorNombreCarga = false;
		boolean filtrarPorRutSocio = false;
		boolean filtrarPorNombreSocio = false;
		boolean filtrarPorTipoCarga = false;
		int contador = 0;

		String command = "SELECT  CARRUT, C.SOCRUT, SOCDV, "
				+ "SOCNOMBRE, SOCAPEPAT, SOCAPEMAT, CARDV, CARNUM, CARNOM, CARAPEPAT,CARAPEMAT, "
				+ "CARFECNAC, CARFECING, CARFECEGR, CARTIPO, CARSEXO, CAREST FROM "
				+ bonifDatabase + ".BF02F1 C, " + bonifDatabase
				+ ".BF01F1 S WHERE C.SOCRUT = S.SOCRUT";

		/*
		 * verifico si vienen filtros
		 */
		if (carga.getRutCarga() != null && (carga.getRutCarga()).length() > 0) {
			filtrarPorRutCarga = true;
			command = command + " AND CARRUT LIKE ?";
		}
		if (carga.getNombreCarga() != null
				&& (carga.getNombreCarga()).length() > 0) {
			filtrarPorNombreCarga = true;
			command = command
					+ " AND (UCASE(CARNOM) LIKE ? OR UCASE(CARAPEPAT) LIKE ? OR UCASE(CARAPEMAT) LIKE ?)";
		}
		if (carga.getRutSocio() != null && (carga.getRutSocio()).length() > 0) {
			filtrarPorRutSocio = true;
			command = command + " AND (C.SOCRUT LIKE ?)";
		}
		if (carga.getNombreSocio() != null
				&& (carga.getNombreSocio()).length() > 0) {
			filtrarPorNombreSocio = true;
			command = command
					+ " AND (UCASE(SOCNOMBRE) LIKE ? OR UCASE(SOCAPEPAT) LIKE ? OR UCASE(SOCAPEMAT) LIKE ?)";
		}
		if (carga.getTipoCarga() != null && (carga.getTipoCarga()).length() > 0) {
			filtrarPorTipoCarga = true;
			command = command + " AND CARTIPO = ?";
		}
		command = command + " ORDER BY C.SOCRUT, CARRUT";

		ArrayList retorno = new ArrayList();

		try {

			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			if (filtrarPorRutCarga) {
				contador++;
				stmt.setString(contador, '%' + carga.getRutCarga() + '%');
			}
			if (filtrarPorNombreCarga) {
				contador++;
				stmt.setString(contador, '%' + carga.getNombreCarga()
						.toUpperCase() + '%');
				contador++;
				stmt.setString(contador, '%' + carga.getNombreCarga()
						.toUpperCase() + '%');
				contador++;
				stmt.setString(contador, '%' + carga.getNombreCarga()
						.toUpperCase() + '%');
			}
			if (filtrarPorRutSocio) {
				contador++;
				stmt.setString(contador, '%' + carga.getRutSocio() + '%');
			}
			if (filtrarPorNombreSocio) {
				contador++;
				stmt.setString(contador, '%' + carga.getNombreSocio()
						.toUpperCase() + '%');
				contador++;
				stmt.setString(contador, '%' + carga.getNombreSocio()
						.toUpperCase() + '%');
				contador++;
				stmt.setString(contador, '%' + carga.getNombreSocio()
						.toUpperCase() + '%');
			}
			if (filtrarPorTipoCarga) {
				contador++;
				stmt.setString(contador, carga.getTipoCarga());
			}

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				Carga car = new Carga();
				car.setRutCarga(ors.getString("CARRUT"));
				car.setRutSocio(ors.getString("SOCRUT"));
				car.setDvSocio(ors.getString("SOCDV"));
				car.setNombreSocio(ors.getString("SOCNOMBRE") + " "
						+ ors.getString("SOCAPEPAT") + " "
						+ ors.getString("SOCAPEMAT"));
				car.setDvCarga(ors.getString("CARDV"));
				car.setNumCarga(ors.getString("CARNUM"));
				car.setNombreCarga(ors.getString("CARNOM"));
				car.setApePatCarga(ors.getString("CARAPEPAT"));
				car.setApeMatCarga(ors.getString("CARAPEMAT"));
				car.setFecNacCarga(ors.getDate("CARFECNAC"));
				car.setFecIngCarga(ors.getDate("CARFECING"));
				car.setFecEgrCarga(ors.getDate("CARFECEGR"));
				car.setTipoCarga(ors.getString("CARTIPO"));
				car.setSexoCarga(ors.getString("CARSEXO"));
				car.setEstadoCarga(ors.getString("CAREST"));
				retorno.add(car);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return retorno;
	}

	/**
	 * RAC 10523 Obtiene la lista de Cargas Familiares registradas en Bienestar
	 * 
	 * @param Carga
	 * @return ArrayList de Carga
	 * @throws Exception
	 */
	public ArrayList getListaCargas(Carga carga) throws Exception,
			BusinessException {
		logger.info(">>getListaCargas");
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		boolean filtrarPorRutCarga = false;
		boolean filtrarPorNombreCarga = false;
		boolean filtrarPorRutSocio = false;
		boolean filtrarPorNombreSocio = false;
		boolean filtrarPorTipoCarga = false;
		int contador = 0;

		String command = "SELECT  a.AF8HA CARRUT, trim(b.RUT) SOCRUT, "
				+ "b.DV SOCDV,"
				+ "b.FNNOMBRES SOCNOMBRE, b.FNAPELLPAT SOCAPEPAT,"
				+ "b.FNAPELLMAT SOCAPEMAT, " + "a.AF8IA CARDV,"
				+ "a.AF8DA CARNUM,a.AF8GA CARNOM, a.AF8EA CARAPEPAT,"
				+ "a.AF8FA CARAPEMAT, a.AF8JA CARFECNAC, "
				+ "a.AF8OA CARFECING, a.AF8RA CARFECEGR, "
				+ "a.AF8KA CARTIPO, a.AF8MA CARSEXO, " + "a.AF8LA CAREST FROM "
				+ benefDatabase + ".AF05F1 a, " + bonifDatabase
				+ ".funcionarios  b " + "WHERE  b.FNESTADO =  'V' and  " 
				+" b.rut = a.SE5FAJC ";
		/*
		 * verifico si vienen filtros
		 */
		if (carga.getRutCarga() != null && (carga.getRutCarga()).length() > 0) {
			filtrarPorRutCarga = true;
			command = command + " AND a.AF8HA LIKE ?";
		}
		if (carga.getNombreCarga() != null
				&& (carga.getNombreCarga()).length() > 0) {
			filtrarPorNombreCarga = true;
			command = command
					+ " AND (UCASE(a.AF8GA) LIKE ? OR UCASE(a.AF8EA) LIKE ? OR UCASE(a.AF8FA) LIKE ?)";
		}
		if (carga.getRutSocio() != null && (carga.getRutSocio()).length() > 0) {
			filtrarPorRutSocio = true;
			command = command + " AND (b.RUT = ?)";// a.SE5FAJC es el
			// rut sin DV
		}
		if (carga.getNombreSocio() != null
				&& (carga.getNombreSocio()).length() > 0) {
			filtrarPorNombreSocio = true;
			command = command
					+ " AND (UCASE(b.FNNOMBRES) LIKE ? OR UCASE(b.FNAPELLPAT) LIKE ? OR UCASE(b.FNAPELLMAT) LIKE ?)";
		}
		if (carga.getTipoCarga() != null && (carga.getTipoCarga()).length() > 0) {
			filtrarPorTipoCarga = true;
			command = command + " AND a.AF8KA = ?";
		}
		command = command + " ORDER BY a.SE5FAJC, a.AF8HA";

		ArrayList retorno = new ArrayList();

		try {

			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			if (filtrarPorRutCarga) {
				contador++;
				stmt.setString(contador, '%' + carga.getRutCarga() + '%');
			}
			if (filtrarPorNombreCarga) {
				contador++;
				stmt.setString(contador, '%' + carga.getNombreCarga()
						.toUpperCase() + '%');
				contador++;
				stmt.setString(contador, '%' + carga.getNombreCarga()
						.toUpperCase() + '%');
				contador++;
				stmt.setString(contador, '%' + carga.getNombreCarga()
						.toUpperCase() + '%');
			}
			if (filtrarPorRutSocio) {
				contador++;
				stmt.setString(contador, carga.getRutSocio());
			}
			if (filtrarPorNombreSocio) {
				contador++;
				stmt.setString(contador, '%' + carga.getNombreSocio()
						.toUpperCase() + '%');
				contador++;
				stmt.setString(contador, '%' + carga.getNombreSocio()
						.toUpperCase() + '%');
				contador++;
				stmt.setString(contador, '%' + carga.getNombreSocio()
						.toUpperCase() + '%');
			}
			if (filtrarPorTipoCarga) {
				contador++;
				stmt.setString(contador, carga.getTipoCarga());
			}

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				Carga car = new Carga();
				car.setRutCarga(ors.getString("CARRUT"));
				car.setRutSocio(ors.getString("SOCRUT"));
				car.setDvSocio(ors.getString("SOCDV"));
				car.setNombreSocio(ors.getString("SOCNOMBRE") + " "
						+ ors.getString("SOCAPEPAT") + " "
						+ ors.getString("SOCAPEMAT"));
				car.setDvCarga(ors.getString("CARDV"));
				car.setNumCarga(ors.getString("CARNUM"));
				car.setNombreCarga(ors.getString("CARNOM"));
				car.setApePatCarga(ors.getString("CARAPEPAT"));
				car.setApeMatCarga(ors.getString("CARAPEMAT"));
				car.setFecNacCarga(ors.getDate("CARFECNAC"));
				car.setFecIngCarga(ors.getDate("CARFECING"));
				car.setFecEgrCarga(ors.getDate("CARFECEGR"));
				car.setTipoCarga(ors.getString("CARTIPO"));
				car.setSexoCarga(ors.getString("CARSEXO"));
				car.setEstadoCarga(ors.getString("CAREST"));
				retorno.add(car);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.info("<<getListaCargas");
		return retorno;
	}

	/**
	 * RAC 10523 Obtiene una Carga Familiar desde Bienestar
	 * 
	 * @param rut
	 *            de la carga familiar buscada
	 * @return Carga
	 * @throws Exception
	 */
	public Carga getCarga(String rutCarga, String rutSocio) throws Exception,
			BusinessException {
		logger.info(">>getCarga");
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command = "SELECT  a.AF8HA CARRUT, TRIM(b.RUT) SOCRUT, "
				+ "b.DV SOCDV, "
				+ "b.FNNOMBRES SOCNOMBRE, b.FNAPELLPAT SOCAPEPAT, "
				+ "b.FNAPELLMAT SOCAPEMAT, "
				+ "a.AF8IA CARDV, a.AF8DA CARNUM, "
				+ "a.AF8GA CARNOM, a.AF8EA CARAPEPAT,a.AF8FA CARAPEMAT, "
				+ "a.AF8JA CARFECNAC, a.AF8OA CARFECING, a.AF8RA CARFECEGR,"
				+ " a.AF8KA CARTIPO, a.AF8MA CARSEXO, a.AF8LA CAREST FROM "
				+ benefDatabase + ".AF05F1 a, " + bonifDatabase
				+ ".FUNCIONARIOS  B WHERE b.RUT = ? " + "AND a.AF8HA = ?"
				+"and SE5FAJC = ?";

		Carga car = new Carga();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, rutSocio);
			stmt.setString(2, rutCarga);
			stmt.setString(3, rutSocio);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			if (ors.next()) {
				car.setRutCarga(ors.getString("CARRUT"));
				car.setRutSocio(ors.getString("SOCRUT"));
				car.setDvSocio(ors.getString("SOCDV"));
				car.setNombreSocio(ors.getString("SOCNOMBRE") + " "
						+ ors.getString("SOCAPEPAT") + " "
						+ ors.getString("SOCAPEMAT"));
				car.setDvCarga(ors.getString("CARDV"));
				car.setNumCarga(ors.getString("CARNUM"));
				car.setNombreCarga(ors.getString("CARNOM"));
				car.setApePatCarga(ors.getString("CARAPEPAT"));
				car.setApeMatCarga(ors.getString("CARAPEMAT"));
				car.setFecNacCarga(ors.getDate("CARFECNAC"));
				car.setFecIngCarga(ors.getDate("CARFECING"));
				car.setFecEgrCarga(ors.getDate("CARFECEGR"));
				car.setTipoCarga(ors.getString("CARTIPO"));
				car.setSexoCarga(ors.getString("CARSEXO"));
				car.setEstadoCarga(ors.getString("CAREST"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.info("<<getCarga");
		return car;
	}

	/**
	 * Obtiene una Carga Familiar desde Bienestar antes de rac 10523
	 * 
	 * @param rut
	 *            de la carga familiar buscada
	 * @return Carga
	 * @throws Exception
	 */
	public Carga getCarga_OLD(String rutCarga) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command = "SELECT  CARRUT, C.SOCRUT, SOCDV, "
				+ "SOCNOMBRE, SOCAPEPAT, SOCAPEMAT, CARDV, CARNUM, CARNOM, CARAPEPAT,CARAPEMAT, "
				+ "CARFECNAC, CARFECING, CARFECEGR, CARTIPO, CARSEXO, CAREST FROM "
				+ bonifDatabase + ".BF02F1 C, " + bonifDatabase
				+ ".BF01F1 S WHERE C.SOCRUT = S.SOCRUT AND CARRUT = ?";

		Carga car = new Carga();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, rutCarga);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			if (ors.next()) {
				car.setRutCarga(ors.getString("CARRUT"));
				car.setRutSocio(ors.getString("SOCRUT"));
				car.setDvSocio(ors.getString("SOCDV"));
				car.setNombreSocio(ors.getString("SOCNOMBRE") + " "
						+ ors.getString("SOCAPEPAT") + " "
						+ ors.getString("SOCAPEMAT"));
				car.setDvCarga(ors.getString("CARDV"));
				car.setNumCarga(ors.getString("CARNUM"));
				car.setNombreCarga(ors.getString("CARNOM"));
				car.setApePatCarga(ors.getString("CARAPEPAT"));
				car.setApeMatCarga(ors.getString("CARAPEMAT"));
				car.setFecNacCarga(ors.getDate("CARFECNAC"));
				car.setFecIngCarga(ors.getDate("CARFECING"));
				car.setFecEgrCarga(ors.getDate("CARFECEGR"));
				car.setTipoCarga(ors.getString("CARTIPO"));
				car.setSexoCarga(ors.getString("CARSEXO"));
				car.setEstadoCarga(ors.getString("CAREST"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return car;
	}

	/**
	 * Obtiene la lista de Cargas Familiares dependientes de un Socio que esten
	 * registradas en Bienestar antes del RAC 10523
	 * 
	 * @param rut
	 *            del Socio
	 * @return ArrayList de Carga
	 * @throws Exception
	 */
	public ArrayList getCargasSocio_OLD(String rutSocio) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command = "SELECT  CARRUT, SOCRUT, CARDV, CARNUM, CARNOM, CARAPEPAT,"
				+ " CARAPEMAT, CARFECNAC, CARFECING, CARFECEGR, CARTIPO, CARSEXO, CAREST "
				+ "FROM "
				+ bonifDatabase
				+ ".BF02F1 WHERE SOCRUT = ? ORDER BY CARRUT";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			logger.debug("Rut: " + rutSocio);
			stmt.setString(1, rutSocio);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				Carga car = new Carga();
				car.setRutCarga(ors.getString("CARRUT"));
				car.setRutSocio(ors.getString("SOCRUT"));
				car.setDvCarga(ors.getString("CARDV"));
				car.setNumCarga(ors.getString("CARNUM"));
				car.setNombreCarga(ors.getString("CARNOM"));
				car.setApePatCarga(ors.getString("CARAPEPAT"));
				car.setApeMatCarga(ors.getString("CARAPEMAT"));
				car.setFecNacCarga(ors.getDate("CARFECNAC"));
				car.setFecIngCarga(ors.getDate("CARFECING"));
				car.setFecEgrCarga(ors.getDate("CARFECEGR"));
				car.setTipoCarga(ors.getString("CARTIPO"));
				car.setSexoCarga(ors.getString("CARSEXO"));
				car.setEstadoCarga(ors.getString("CAREST"));
				retorno.add(car);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return retorno;
	}

	/**
	 * RAC 10523 Obtiene la lista de Cargas Familiares dependientes de un Socio
	 * que esten registradas en Bienestar
	 * 
	 * @param rut
	 *            del Socio
	 * @return ArrayList de Carga
	 * @throws Exception
	 */
	public ArrayList getCargasSocio(String rutSocio) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command = "SELECT  AF8HA CARRUT, SE5FAJC SOCRUT, AF8IA CARDV, AF8DA CARNUM, AF8GA CARNOM, AF8EA CARAPEPAT,"
				+ " AF8FA CARAPEMAT, AF8JA CARFECNAC, AF8OA CARFECING, AF8RA CARFECEGR,"
				+ " AF8KA CARTIPO, AF8MA CARSEXO,  AF8LA CAREST "
				+ "FROM "
				+ benefDatabase + ".AF05F1 WHERE SE5FAJC = ? ORDER BY AF8HA";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			logger.debug("Rut: " + rutSocio);
			stmt.setString(1, rutSocio);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				Carga car = new Carga();
				car.setRutCarga(ors.getString("CARRUT"));
				car.setRutSocio(ors.getString("SOCRUT"));
				car.setDvCarga(ors.getString("CARDV"));
				car.setNumCarga(ors.getString("CARNUM"));
				car.setNombreCarga(ors.getString("CARNOM"));
				car.setApePatCarga(ors.getString("CARAPEPAT"));
				car.setApeMatCarga(ors.getString("CARAPEMAT"));
				car.setFecNacCarga(ors.getDate("CARFECNAC"));
				car.setFecIngCarga(ors.getDate("CARFECING"));
				car.setFecEgrCarga(ors.getDate("CARFECEGR"));
				car.setTipoCarga(ors.getString("CARTIPO"));
				car.setSexoCarga(ors.getString("CARSEXO"));
				car.setEstadoCarga(ors.getString("CAREST"));
				retorno.add(car);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return retorno;
	}

	/**
	 * Crea una nueva carga en Bienestar
	 * 
	 * @param carga:
	 *            el Objeto Carga
	 */
	public void insertCarga(Carga carga) throws Exception, BusinessException {
		if (carga == null) {
			throw new BusinessException("CCAF_CARGAINVALIDA",
					"Se ha intentado crear una Carga Nula");
		}
		/*
		 * Connection conn = null; CallableStatement stmt = null; ResultSet ors =
		 * null; String command = "INSERT INTO " + bonifDatabase +
		 * ".BF02F1(CARRUT, SOCRUT, CARDV," + "CARNUM, CARNOM, CARAPEPAT,
		 * CARAPEMAT, CARFECNAC, CARFECING, CARFECEGR, CARTIPO," + " CARSEXO,
		 * CAREST) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		 * 
		 * try { conn = DB2Utils.createConnection(bonifJNDIDataSource); stmt =
		 * conn.prepareCall(command); logger.debug("Carga: " +
		 * carga.getRutCarga()); logger.debug("Socio: " + carga.getRutSocio());
		 * stmt.setString(1, carga.getRutCarga()); stmt.setString(2,
		 * carga.getRutSocio()); stmt.setString(3, carga.getDvCarga());
		 * stmt.setString(4, carga.getNumCarga()); stmt.setString(5,
		 * carga.getNombreCarga()); stmt.setString(6, carga.getApePatCarga());
		 * stmt.setString(7, carga.getApeMatCarga()); if (carga.getFecNacCarga() !=
		 * null) { stmt.setTimestamp(8, new java.sql.Timestamp(carga
		 * .getFecNacCarga().getTime())); } else { stmt.setNull(8,
		 * Types.TIMESTAMP); } if (carga.getFecIngCarga() != null) {
		 * stmt.setTimestamp(9, new java.sql.Timestamp(carga
		 * .getFecIngCarga().getTime())); } else { stmt.setNull(9,
		 * Types.TIMESTAMP); } if (carga.getFecEgrCarga() != null) {
		 * stmt.setTimestamp(10, new java.sql.Timestamp(carga
		 * .getFecEgrCarga().getTime())); } else { stmt.setNull(10,
		 * Types.TIMESTAMP); } stmt.setString(11, carga.getTipoCarga());
		 * stmt.setString(12, carga.getSexoCarga()); stmt.setString(13,
		 * carga.getEstadoCarga());
		 * 
		 * logger.debug("Inicia operación: " + command); stmt.execute();
		 * logger.debug("Finaliza operación: " + command); } catch (SQLException
		 * ex) { ex.printStackTrace(); int code = ex.getErrorCode(); throw new
		 * BusinessException(PREFIX + code); } finally { DB2Utils.closeAll(conn,
		 * stmt, ors); }
		 */
	}

	/**
	 * Modifica los datos de una carga en Bienestar
	 * 
	 * @param carga:
	 *            el Objeto Carga
	 */
	public void updateCarga(Carga carga) throws Exception, BusinessException {
		if (carga == null) {
			throw new BusinessException("CCAF_BONIF_CARGAINVALIDA",
					"Se ha intentado modificar una Carga Nula");
		}

		/*
		 * Connection conn = null; CallableStatement stmt = null; ResultSet ors =
		 * null;
		 * 
		 * String command = "UPDATE " + bonifDatabase + ".BF02F1 SET CARNUM = ?,
		 * CARNOM = ?, CARAPEPAT = ?, " + " CARAPEMAT = ?, CARFECNAC =
		 * ?,CARFECEGR = ?, CARTIPO = ?, CARSEXO = ?, CAREST = ? WHERE CARRUT = ? " + "
		 * AND SOCRUT = ?";
		 * 
		 * try { conn = DB2Utils.createConnection(bonifJNDIDataSource); stmt =
		 * conn.prepareCall(command);
		 * 
		 * stmt.setString(1, carga.getNumCarga()); stmt.setString(2,
		 * carga.getNombreCarga()); stmt.setString(3, carga.getApePatCarga());
		 * stmt.setString(4, carga.getApeMatCarga()); if (carga.getFecNacCarga() !=
		 * null) { stmt.setTimestamp(5, new java.sql.Timestamp(carga
		 * .getFecNacCarga().getTime())); } else { stmt.setNull(5,
		 * Types.TIMESTAMP); } if (carga.getFecEgrCarga() != null) {
		 * stmt.setTimestamp(6, new java.sql.Timestamp(carga
		 * .getFecEgrCarga().getTime())); } else { stmt.setNull(6,
		 * Types.TIMESTAMP); } stmt.setString(7, carga.getTipoCarga());
		 * stmt.setString(8, carga.getSexoCarga()); stmt.setString(9,
		 * carga.getEstadoCarga()); stmt.setString(10, carga.getRutCarga());
		 * stmt.setString(11, carga.getRutSocio());
		 * 
		 * logger.debug("Inicia operación: " + command); stmt.execute();
		 * logger.debug("Finaliza operación: " + command); } catch (SQLException
		 * ex) { ex.printStackTrace(); int code = ex.getErrorCode(); throw new
		 * BusinessException(PREFIX + code); } finally { DB2Utils.closeAll(conn,
		 * stmt, ors); }
		 */
	}

	/**
	 * Obtiene la lista de conceptos existentes
	 * 
	 * @param tipo
	 *            de concepto
	 * @return ArrayList de Concepto
	 * @throws Exception
	 */
	public ArrayList getConceptos() throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command = null;

		command = "SELECT TCOCOD, TCODESCRIP, TCOFECCREA, TCOCTADEU, TCOCTAACRE, "
				+ "TCOTESAREA, TCOTESCOIN, TCOTESCOEG "
				+ "FROM "
				+ bonifDatabase + ".BF14F1 ORDER BY TCOCOD";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				Concepto concepto = new Concepto();
				concepto.setCodigo(ors.getLong("TCOCOD"));
				concepto.setDescripcion(ors.getString("TCODESCRIP"));
				concepto.setFechaCreacion(ors.getDate("TCOFECCREA"));
				concepto.setCuentaDeudor(ors.getLong("TCOCTADEU"));
				concepto.setCuentaAcreedor(ors.getLong("TCOCTAACRE"));
				concepto.setTesoreriaArea(ors.getLong("TCOTESAREA"));
				concepto.setTesoreriaConceptoIngreso(ors.getLong("TCOTESCOIN"));
				concepto.setTesoreriaConceptoEgreso(ors.getLong("TCOTESCOEG"));

				retorno.add(concepto);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return retorno;
	}

	/**
	 * Obtiene un concepto existente
	 * 
	 * @param codigo
	 *            y tipo de concepto
	 * @return Concepto
	 * @throws Exception
	 */
	public Concepto getConcepto(long codigo) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command = null;

		command = "SELECT TCOCOD, TCODESCRIP, TCOFECCREA, TCOCTADEU, TCOCTAACRE, "
				+ "TCOTESAREA, TCOTESCOIN, TCOTESCOEG "
				+ "FROM "
				+ bonifDatabase + ".BF14F1 WHERE TCOCOD = ?";

		Concepto concepto = new Concepto();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigo);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			if (ors.next()) {
				concepto.setCodigo(ors.getLong("TCOCOD"));
				concepto.setDescripcion(ors.getString("TCODESCRIP"));
				concepto.setFechaCreacion(ors.getDate("TCOFECCREA"));
				concepto.setCuentaDeudor(ors.getLong("TCOCTADEU"));
				concepto.setCuentaAcreedor(ors.getLong("TCOCTAACRE"));
				concepto.setTesoreriaArea(ors.getLong("TCOTESAREA"));
				concepto.setTesoreriaConceptoIngreso(ors.getLong("TCOTESCOIN"));
				concepto.setTesoreriaConceptoEgreso(ors.getLong("TCOTESCOEG"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return concepto;
	}

	/**
	 * Crea un nuevo concepto en Bienestar
	 * 
	 * @param concepto:
	 *            el Objeto Concepto
	 */
	public void insertConcepto(Concepto concepto) throws Exception,
			BusinessException {
		if (concepto == null) {
			throw new BusinessException("CCAF_BONIF_CONCEPTOINVALIDO",
					"Se ha intentado crear un Concepto Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		long idDisponible = 0;

		String command;

		command = "INSERT INTO " + bonifDatabase + ".BF14F1 "
				+ "(TCOCOD,TCODESCRIP,TCOFECCREA,TCOCTADEU,TCOCTAACRE,"
				+ "TCOTESAREA, TCOTESCOIN, TCOTESCOEG) "
				+ "VALUES (?,?,?,?,?,?,?,?)";

		try {

			/*
			 * Obtiene un ID
			 */
			idDisponible = getID("BF14F1");
			if (idDisponible > 0) {
				concepto.setCodigo(idDisponible);
			} else {
				throw new BusinessException("CCAF_BONIF_IDENTIFICADORINVALIDO",
						"No se pudo obtener un ID");
			}

			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, concepto.getCodigo());
			stmt.setString(2, concepto.getDescripcion());
			stmt.setTimestamp(3, new java.sql.Timestamp(concepto
					.getFechaCreacion().getTime()));
			stmt.setLong(4, concepto.getCuentaDeudor());
			stmt.setLong(5, concepto.getCuentaAcreedor());
			stmt.setLong(6, concepto.getTesoreriaArea());
			stmt.setLong(7, concepto.getTesoreriaConceptoIngreso());
			stmt.setLong(8, concepto.getTesoreriaConceptoEgreso());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Actualiza un concepto de Bienestar
	 * 
	 * @param concepto:
	 *            el Objeto Concepto
	 */
	public void updateConcepto(Concepto concepto) throws Exception,
			BusinessException {
		if (concepto == null) {
			throw new BusinessException("CCAF_BONIF_CONCEPTOINVALIDO",
					"Se ha intentado modificar un Concepto Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "UPDATE " + bonifDatabase + ".BF14F1 SET TCODESCRIP = ?,"
				+ "TCOCTADEU = ?,TCOCTAACRE = ?, "
				+ "TCOTESAREA = ?, TCOTESCOIN = ?, TCOTESCOEG = ? "
				+ "WHERE TCOCOD = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, concepto.getDescripcion());
			stmt.setLong(2, concepto.getCuentaDeudor());
			stmt.setLong(3, concepto.getCuentaAcreedor());
			stmt.setLong(4, concepto.getTesoreriaArea());
			stmt.setLong(5, concepto.getTesoreriaConceptoIngreso());
			stmt.setLong(6, concepto.getTesoreriaConceptoEgreso());

			stmt.setLong(7, concepto.getCodigo());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Elimina un concepto de Bienestar
	 * 
	 * @param concepto:
	 *            el Objeto Concepto
	 */
	public void deleteConcepto(Concepto concepto) throws Exception,
			BusinessException {
		if (concepto == null) {
			throw new BusinessException("CCAF_BONIF_CONCEPTOINVALIDO",
					"Se ha intentado eliminar un Concepto Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "DELETE FROM " + bonifDatabase + ".BF14F1 WHERE TCOCOD = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, concepto.getCodigo());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Registra el detalle del aporte de una cobertura para un detalle de caso
	 * en particular
	 * 
	 * @param AporteCobertura
	 * @throws Exception
	 * @throws BusinessException
	 */

	public void registraAporteCobertura(AporteCobertura aporteCobertura)
			throws Exception, BusinessException {

		if (aporteCobertura == null)
			throw new BusinessException("CCAF_BONIF_APORTECOBERTURAINVALIDO",
					"La Información del aporte de la cobertura es incorrecta");

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "INSERT INTO " + bonifDatabase
				+ ".BF17F1 (DCAID,CASID,COBCOD,ABIMONTO) "
				+ "VALUES (?, ?, ?, ?)";

		try {

			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setInt(1, aporteCobertura.getIdDetalle());
			stmt.setLong(2, aporteCobertura.getCasoID());
			stmt.setLong(3, aporteCobertura.getCodigoCobertura());
			stmt.setLong(4, aporteCobertura.getAporteBienestar());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);
		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Elimina los detalles del aporte de bienestar por cada cobertura
	 * 
	 * @param long
	 *            casoId
	 */
	public boolean deleteAporteCobertura(long casoId) throws Exception,
			BusinessException {
		
		boolean exito = false;
		if (casoId == 0) {
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "DELETE FROM " + bonifDatabase + ".BF17F1 WHERE CASID = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, casoId);

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);
			
			exito = true;

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return exito;
	}

	/**
	 * Elimina el detalle del aporte de bienestar pora el caso y cobertura
	 * 
	 * @param long
	 *            casoId, int idDetalle
	 */
	public void deleteAporteCoberturaByDetalle(long casoId, int idDetalle)
			throws Exception, BusinessException {
		if (casoId == 0) {
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "DELETE " + "FROM " + bonifDatabase + ".BF17F1 " + "WHERE "
				+ "CASID = ? " + "AND " + "DCAID = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, casoId);
			stmt.setInt(2, idDetalle);

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Obtiene la lista de coberturas adicionales que tiene una cobertura
	 * 
	 * @return ArrayList de CoberturaAdicional
	 * @throws Exception
	 */
	public ArrayList getRelacionCoberturaAdicional(long codigoCobertura)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command = null;

		command = "SELECT COBCOD,CADCOBCOD,CADORD " + "FROM " + bonifDatabase
				+ ".BF16F1 WHERE COBCOD = ?";

		ArrayList ret = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoCobertura);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				CoberturaAdicional cobAdi = new CoberturaAdicional();
				cobAdi.setCodigo(ors.getLong("COBCOD"));
				cobAdi.setCodigoCoberturaAdicional(ors.getLong("CADCOBCOD"));
				cobAdi.setOrden(ors.getInt("CADORD"));
				ret.add(cobAdi);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return ret;
	}

	/**
	 * Actualiza una relación coberturaAdicional
	 * 
	 * @param CoberturaAdicional
	 */
	public void updateRelacionCoberturaAdicional(
			CoberturaAdicional coberturaAdicional) throws Exception,
			BusinessException {
		if (coberturaAdicional == null) {
			throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
					"La relación con la cobertura adicional es incorrecta");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "UPDATE " + bonifDatabase + ".BF16F1 SET CADCOBCOD = ? "
				+ "WHERE COBCOD = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, coberturaAdicional.getCodigoCoberturaAdicional());
			stmt.setLong(2, coberturaAdicional.getCodigo());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Registra una relación CoberturaAdicional
	 * 
	 * @param CoberturaAdicional
	 * @throws Exception
	 * @throws BusinessException
	 */

	public void registraRelacionCoberturaAdicional(
			CoberturaAdicional coberturaAdicional) throws Exception,
			BusinessException {

		if (coberturaAdicional == null)
			throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
					"La Información de la cobertura Adicional es incorrecta");

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "INSERT INTO " + bonifDatabase
				+ ".BF16F1 (COBCOD,CADCOBCOD) VALUES (?, ?)";

		try {

			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, coberturaAdicional.getCodigo());
			stmt.setLong(2, coberturaAdicional.getCodigoCoberturaAdicional());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);
		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Elimina todas las relaciones donde el codigoCobertura pasado como
	 * parametro corresponda al codigo de coberturaAdicional
	 * 
	 * @param codigo
	 *            Cobertura
	 */
	public void deleteRelacionesCoberturaAdicionalByAdicional(
			long codigoCobertura) throws Exception, BusinessException {
		if (codigoCobertura == 0) {
			throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
					"Codigo de cobertura invalido");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "DELETE FROM " + bonifDatabase
				+ ".BF16F1 WHERE CADCOBCOD = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoCobertura);

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Elimina la relación coberturaAdiconal para una cobertura
	 * 
	 * @param codigo
	 *            Cobertura
	 */
	public void deleteRelacionCoberturaAdicional(long codigoCobertura)
			throws Exception, BusinessException {
		if (codigoCobertura == 0) {
			throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
					"Codigo de cobertura invalido");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "DELETE FROM " + bonifDatabase + ".BF16F1 WHERE COBCOD = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoCobertura);

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Busca si la cobertura pasada como parámetro tiene la definición de rangos
	 * en otra cobertura, en caso de ser así, significa que ambas coberturas
	 * utilizan los mismos aportes de bienestar. Si n o tiene devuelve cero
	 * 
	 * @param long
	 *            codigoCobertura
	 * @return AgrupacionCobertura != null si existe la relación = null si no
	 *         existe la relación
	 * @throws Exception
	 */
	public AgrupacionCobertura getRelacionAgrupacionCobertura(
			long codigoCobertura) throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		AgrupacionCobertura vo = new AgrupacionCobertura();

		String command = null;

		command = "SELECT COBCOD, ADCCOBMST, ADCFECING " + "FROM "
				+ bonifDatabase + ".BF27F1 WHERE COBCOD = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoCobertura);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			if (ors.next()) {
				vo.setCodigoCobertura(ors.getLong("COBCOD"));
				vo.setCodigoCoberturaMaestra(ors.getLong("ADCCOBMST"));
				vo.setFechaRegistro(ors.getDate("ADCFECING"));
				return vo;
			} else
				return null;

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Busca todas la relaciones que posee una cobertura maestra
	 * 
	 * @param long
	 *            codigoCoberturaMaestra
	 * @return ArrayList de AgrupacionCobertura
	 * @throws Exception
	 */
	public ArrayList getRelacionAgrupacionCoberturaByCoberturaMaestra(
			long codigoCobertura) throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		ArrayList retorno = new ArrayList();

		String command = null;

		command = "SELECT COBCOD, ADCCOBMST, ADCFECING " + "FROM "
				+ bonifDatabase + ".BF27F1 WHERE ADCCOBMST = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoCobertura);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				AgrupacionCobertura vo = new AgrupacionCobertura();
				vo.setCodigoCobertura(ors.getLong("COBCOD"));
				vo.setCodigoCoberturaMaestra(ors.getLong("ADCCOBMST"));
				vo.setFechaRegistro(ors.getDate("ADCFECING"));
				retorno.add(vo);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return retorno;
	}

	/**
	 * Obtiene la lista de Coberturas (Bonificaciones) del tipo y estado
	 * solicitado
	 * 
	 * @return ArrayList de Cobertura
	 * @throws Exception
	 */
	public ArrayList getListaCoberturasPorTipoAndEstado(Cobertura cobertura)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "SELECT  COBCOD, B.TCOCOD, TCODESCRIP, COBTOPE, COBGLS, COBVALREF, "
				+ "COBTIPTOPE, COBEST, COBPERCAR, COBCTAGSTO, COBTIPO, COBOCU, COBOCUETI "
				+ "FROM "
				+ bonifDatabase
				+ ".BF04F1 B, "
				+ bonifDatabase
				+ ".BF14F1 C "
				+ "WHERE "
				+ "B.TCOCOD = C.TCOCOD AND "
				+ "COBTIPO = ? AND COBEST = ?";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, cobertura.getTipoCobertura());
			stmt.setString(2, cobertura.getEstado());

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				Cobertura cob = new Cobertura();
				cob.setCodigo(ors.getLong("COBCOD"));
				cob.setConceptoCodigo(ors.getLong("TCOCOD"));
				cob.setConceptoDescripcion(ors.getString("TCODESCRIP"));
				cob.setTope(ors.getDouble("COBTOPE"));
				cob.setDescripcion(ors.getString("COBGLS"));
				cob.setValorReferencial(ors.getDouble("COBVALREF"));
				cob.setTipoTope(ors.getString("COBTIPTOPE"));
				cob.setEstado(ors.getString("COBEST"));
				cob.setPeriodoCarencia(ors.getInt("COBPERCAR"));
				cob.setCuentaGasto(ors.getLong("COBCTAGSTO"));
				cob.setTipoCobertura(ors.getString("COBTIPO"));
				cob.setTieneOcurrencias(ors.getString("COBOCU"));
				cob.setEtiquetaOcurrencia(ors.getString("COBOCUETI"));
				retorno.add(cob);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return retorno;

	}

	/**
	 * Obtiene la lista de Coberturas que no esten en la lista de productos de
	 * un convenio
	 * 
	 * @param codigo
	 *            de convenio
	 * @return ArrayList de Cobertura
	 * @throws Exception
	 */
	public ArrayList getComplementoProductos(long codigoConvenio,
			Cobertura cobertura) throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "SELECT  COBCOD, B.TCOCOD, TCODESCRIP, COBTOPE, COBGLS, COBVALREF, "
				+ "COBTIPTOPE, COBEST, COBPERCAR, COBCTAGSTO, COBTIPO, COBOCU, COBOCUETI "
				+ "FROM "
				+ bonifDatabase
				+ ".BF04F1 B, "
				+ bonifDatabase
				+ ".BF14F1 C "
				+ "WHERE "
				+ "B.TCOCOD = C.TCOCOD AND "
				+ "COBCOD NOT IN (SELECT COBCOD "
				+ "FROM "
				+ bonifDatabase
				+ ".BF06F1 "
				+ "WHERE "
				+ "CONCOD = ? AND "
				+ "PRDEST = ? ) AND "
				+ "COBEST = ? AND "
				+ "COBTIPO = ? "
				+ "ORDER BY COBCOD";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoConvenio);
			stmt.setString(2, cobertura.getEstado());
			stmt.setString(3, cobertura.getEstado());
			stmt.setString(4, cobertura.getTipoCobertura());

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				Cobertura cob = new Cobertura();
				cob.setCodigo(ors.getLong("COBCOD"));
				cob.setConceptoCodigo(ors.getLong("TCOCOD"));
				cob.setConceptoDescripcion(ors.getString("TCODESCRIP"));
				cob.setTope(ors.getDouble("COBTOPE"));
				cob.setDescripcion(ors.getString("COBGLS"));
				cob.setValorReferencial(ors.getDouble("COBVALREF"));
				cob.setTipoTope(ors.getString("COBTIPTOPE"));
				cob.setEstado(ors.getString("COBEST"));
				cob.setPeriodoCarencia(ors.getInt("COBPERCAR"));
				cob.setCuentaGasto(ors.getLong("COBCTAGSTO"));
				cob.setTipoCobertura(ors.getString("COBTIPO"));
				cob.setTieneOcurrencias(ors.getString("COBOCU"));
				cob.setEtiquetaOcurrencia(ors.getString("COBOCUETI"));
				retorno.add(cob);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return retorno;
	}

	/**
	 * Obtiene la lista de Coberturas (Bonificaciones) existentes
	 * 
	 * @param Cobertura
	 * @return ArrayList de Cobertura
	 * @throws Exception
	 */
	public ArrayList getCoberturas(Cobertura cobertura) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		boolean filtrarPorCodigoCobertura = false;
		boolean filtrarPorDescripcionCodigoCobertura = false;
		boolean filtrarPorCodigoConcepto = false;
		boolean filtrarPorEstado = false;
		int contador = 0;

		String command;

		command = "SELECT  COBCOD, B.TCOCOD, TCODESCRIP, COBTOPE, COBGLS, COBVALREF, "
				+ "COBTIPTOPE, COBEST, COBPERCAR, COBCTAGSTO, COBTIPO, COBOCU, COBOCUETI "
				+ "FROM "
				+ bonifDatabase
				+ ".BF04F1 B, "
				+ bonifDatabase
				+ ".BF14F1 C " + "WHERE B.TCOCOD = C.TCOCOD";

		if (cobertura != null) {
			// verifico si vienen filtros

			if (cobertura.getCodigo() > 0) {
				logger.debug("Cobertura Codigo: " + cobertura.getCodigo());
				command = command + " AND COBCOD = ?";
				filtrarPorCodigoCobertura = true;
			}
			if (cobertura.getDescripcion() != null
					&& (cobertura.getDescripcion()).length() > 0) {
				logger.debug("Descripcion: " + cobertura.getDescripcion());
				command = command + " AND UCASE(COBGLS) LIKE ?";
				filtrarPorDescripcionCodigoCobertura = true;
			}
			if (cobertura.getConceptoCodigo() != 0) {
				logger.debug("Concepto Codigo: "
						+ cobertura.getConceptoCodigo());
				command = command + " AND B.TCOCOD = ?";
				filtrarPorCodigoConcepto = true;
			}
			if (cobertura.getEstado() != null
					&& (cobertura.getEstado()).length() > 0) {
				logger.debug("Cobertura Estado: " + cobertura.getEstado());
				command = command + " AND COBEST = ?";
				filtrarPorEstado = true;
			}
		}

		command = command + " ORDER BY COBEST, COBCOD";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			if (filtrarPorCodigoCobertura) {
				contador++;
				stmt.setLong(contador, cobertura.getCodigo());
			}
			if (filtrarPorDescripcionCodigoCobertura) {
				contador++;
				stmt.setString(contador, '%' + cobertura.getDescripcion()
						.toUpperCase() + '%');
			}
			if (filtrarPorCodigoConcepto) {
				contador++;
				stmt.setLong(contador, cobertura.getConceptoCodigo());
			}
			if (filtrarPorEstado) {
				contador++;
				stmt.setString(contador, cobertura.getEstado());
			}

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				Cobertura cob = new Cobertura();
				cob.setCodigo(ors.getLong("COBCOD"));
				cob.setConceptoCodigo(ors.getLong("TCOCOD"));
				cob.setConceptoDescripcion(ors.getString("TCODESCRIP"));
				cob.setTope(ors.getDouble("COBTOPE"));
				cob.setDescripcion(ors.getString("COBGLS"));
				cob.setValorReferencial(ors.getDouble("COBVALREF"));
				cob.setTipoTope(ors.getString("COBTIPTOPE"));
				cob.setEstado(ors.getString("COBEST"));
				cob.setPeriodoCarencia(ors.getInt("COBPERCAR"));
				cob.setCuentaGasto(ors.getLong("COBCTAGSTO"));
				cob.setTipoCobertura(ors.getString("COBTIPO"));
				cob.setTieneOcurrencias(ors.getString("COBOCU"));
				cob.setEtiquetaOcurrencia(ors.getString("COBOCUETI"));
				retorno.add(cob);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return retorno;
	}

	/**
	 * Obtiene una Cobertura (Bonificacion) existente
	 * 
	 * @param codigo
	 *            de cobertura
	 * @return Cobertura
	 * @throws Exception
	 */
	public Cobertura getCobertura(long codigoCobertura) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "SELECT  COBCOD, B.TCOCOD, TCODESCRIP, COBTOPE, COBGLS, COBVALREF, "
				+ "COBTIPTOPE, COBEST, COBPERCAR, COBCTAGSTO, COBTIPO, COBOCU, COBOCUETI "
				+ "FROM "
				+ bonifDatabase
				+ ".BF04F1 B, "
				+ bonifDatabase
				+ ".BF14F1 C "
				+ "WHERE "
				+ "B.TCOCOD = C.TCOCOD AND "
				+ "COBCOD = ?";

		Cobertura cob = new Cobertura();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoCobertura);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			if (ors.next()) {
				cob.setCodigo(ors.getLong("COBCOD"));
				cob.setConceptoCodigo(ors.getLong("TCOCOD"));
				cob.setConceptoDescripcion(ors.getString("TCODESCRIP"));
				cob.setTope(ors.getDouble("COBTOPE"));
				cob.setDescripcion(ors.getString("COBGLS"));
				cob.setValorReferencial(ors.getDouble("COBVALREF"));
				cob.setTipoTope(ors.getString("COBTIPTOPE"));
				cob.setEstado(ors.getString("COBEST"));
				cob.setPeriodoCarencia(ors.getInt("COBPERCAR"));
				cob.setCuentaGasto(ors.getLong("COBCTAGSTO"));
				cob.setTipoCobertura(ors.getString("COBTIPO"));
				cob.setTieneOcurrencias(ors.getString("COBOCU"));
				cob.setEtiquetaOcurrencia(ors.getString("COBOCUETI"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return cob;
	}

	/**
	 * Crea una nueva cobertura en Bienestar
	 * 
	 * @param Cobertura
	 */
	public void insertCobertura(Cobertura cobertura) throws Exception,
			BusinessException {
		if (cobertura == null) {
			throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
					"Se ha intentado crear una cobertura Nula");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		long idDisponible = 0;

		String command;

		command = "INSERT INTO "
				+ bonifDatabase
				+ ".BF04F1 (COBCOD, TCOCOD, "
				+ "COBTOPE, COBGLS, COBVALREF, COBTIPTOPE, COBEST, COBPERCAR,COBCTAGSTO,COBTIPO,COBOCU, COBOCUETI) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {

			/*
			 * Obtiene un ID
			 */
			idDisponible = getID("BF04F1");
			if (idDisponible > 0) {
				cobertura.setCodigo(idDisponible);
			} else {
				throw new BusinessException("CCAF_BONIF_IDENTIFICADORINVALIDO",
						"No se pudo obtener un ID");
			}

			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, cobertura.getCodigo());
			stmt.setLong(2, cobertura.getConceptoCodigo());
			stmt.setDouble(3, cobertura.getTope());
			stmt.setString(4, cobertura.getDescripcion());
			stmt.setDouble(5, cobertura.getValorReferencial());
			stmt.setString(6, cobertura.getTipoTope());
			stmt.setString(7, cobertura.getEstado());
			stmt.setInt(8, cobertura.getPeriodoCarencia());
			stmt.setLong(9, cobertura.getCuentaGasto());
			stmt.setString(10, cobertura.getTipoCobertura());
			stmt.setString(11, cobertura.getTieneOcurrencias());
			stmt.setString(12, cobertura.getEtiquetaOcurrencia());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Actualiza una cobertura en Bienestar
	 * 
	 * @param Cobertura
	 */
	public void updateCobertura(Cobertura cobertura) throws Exception,
			BusinessException {
		if (cobertura == null) {
			throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
					"Se ha intentado modificar una cobertura Nula");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "UPDATE " + bonifDatabase + ".BF04F1 " + "SET "
				+ "TCOCOD = ?, COBTOPE = ?, " + "COBGLS = ?, COBVALREF = ?, "
				+ "COBTIPTOPE = ?, COBEST = ?, "
				+ "COBPERCAR = ?, COBCTAGSTO = ?, "
				+ "COBTIPO = ?, COBOCU = ?, " + "COBOCUETI = ? "
				+ "WHERE COBCOD = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, cobertura.getConceptoCodigo());
			stmt.setDouble(2, cobertura.getTope());
			stmt.setString(3, cobertura.getDescripcion());
			stmt.setDouble(4, cobertura.getValorReferencial());
			stmt.setString(5, cobertura.getTipoTope());
			stmt.setString(6, cobertura.getEstado());
			stmt.setInt(7, cobertura.getPeriodoCarencia());
			stmt.setLong(8, cobertura.getCuentaGasto());
			stmt.setString(9, cobertura.getTipoCobertura());
			stmt.setString(10, cobertura.getTieneOcurrencias());
			stmt.setString(11, cobertura.getEtiquetaOcurrencia());
			stmt.setLong(12, cobertura.getCodigo());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Crea un nuevo Pago ya realizado
	 * 
	 * @param PagoConvenioPendienteVO
	 */
	public void insertPagoConvenio(PagoConvenioPendienteVO pagoConvenio)
			throws Exception, BusinessException {
		if (pagoConvenio == null) {
			throw new BusinessException("CCAF_BONIF_DATOSINCORRECTOS",
					"La información entregada es nula");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "INSERT INTO " + bonifDatabase + ".BF26F1 (" + "CONCOD, "
				+ "RPCMONTO, " + "RPCFOLBIE, " + "RPCCOD, " + "RPCFECHA) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, pagoConvenio.getCodigoConvenio());
			stmt.setInt(2, pagoConvenio.getMonto());
			stmt.setLong(3, pagoConvenio.getFolioTesoreria());
			stmt.setLong(4, pagoConvenio.getCodigoPago());
			stmt.setTimestamp(5, new java.sql.Timestamp(pagoConvenio
					.getFechaPago().getTime()));

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Actualiza un VigenciaRango
	 * 
	 * @param VigenciaRango
	 */
	public void updateVigenciaRango(VigenciaRango vigenciaRango)
			throws Exception, BusinessException {
		if (vigenciaRango == null) {
			throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
					"Se ha intentado actualizar un Rango Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "UPDATE " + bonifDatabase + ".BF13F1 " + "SET "
				+ "VRCFECINI = ?, " + "VRCFECFIN = ? " + "WHERE "
				+ "VRCCOD = ? " + "AND " + "COBCOD = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			stmt.setDate(1, new java.sql.Date(vigenciaRango.getInicioVigencia()
					.getTime()));

			if (vigenciaRango.getFinVigencia() != null) {
				stmt.setDate(2, new java.sql.Date(vigenciaRango
						.getFinVigencia().getTime()));
			} else {
				stmt.setNull(2, Types.DATE);
			}

			stmt.setLong(3, vigenciaRango.getCodigo());
			stmt.setLong(4, vigenciaRango.getCodigoCobertura());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Elimina un VigenciaRango
	 * 
	 * @param VigenciaRango
	 */
	public void deleteVigenciaRango(VigenciaRango vigenciaRango)
			throws Exception, BusinessException {
		if (vigenciaRango == null) {
			throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
					"Codigo de cobertura invalido");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "DELETE " + "FROM " + bonifDatabase + ".BF13F1 " + "WHERE "
				+ "COBCOD = ? " + "AND " + "VRCCOD = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, vigenciaRango.getCodigoCobertura());
			stmt.setLong(2, vigenciaRango.getCodigo());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Obtiene todos los Rangos de una Cobertura (Bonificacion) existentes
	 * Obtiene el rango vigente si existe, los rangos historicos y el rango
	 * futuro si existe
	 * 
	 * @param codigo
	 *            Cobertura
	 * @return ArrayList RangoCoberturaSinFormatoVO
	 * @throws Exception
	 */
	public ArrayList getAllRangosCobertura(long codigoCobertura)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "SELECT " + "A.COBCOD, " + "A.VRCCOD, " + "VRCFECINI, "
				+ "VRCFECFIN, " + "CRARANID, " + "CRARANINI, " + "CRARANFIN, "
				+ "CRAPCTAJE " + "FROM " + bonifDatabase + ".BF13F1 A, "
				+ bonifDatabase + ".BF07F1 B " + "WHERE "
				+ "A.COBCOD = B.COBCOD " + "AND " + "A.VRCCOD = B.VRCCOD "
				+ "AND " + "A.COBCOD = ? " + "ORDER BY "
				+ "A.VRCFECINI, CRARANID";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoCobertura);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				RangoCoberturaSinFormatoVO ran = new RangoCoberturaSinFormatoVO();
				ran.setCodigoCobertura(ors.getLong("COBCOD"));
				ran.setCodigo(ors.getLong("VRCCOD"));
				ran.setInicioVigencia(ors.getDate("VRCFECINI"));
				ran.setFinVigencia(ors.getDate("VRCFECFIN"));
				ran.setRangoID(ors.getInt("CRARANID"));
				ran.setRangoInicio(ors.getDouble("CRARANINI"));
				ran.setRangoFin(ors.getDouble("CRARANFIN"));
				ran.setRangoPorcentajeCobertura(ors.getInt("CRAPCTAJE"));

				retorno.add(ran);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene el mayor codigo de rangoCobertura existente en la BD para la
	 * cobertura especificada
	 * 
	 * @param codigo
	 *            Cobertura
	 * @return int maximo actual
	 * @throws Exception
	 */
	public int getMaximoRangoByCobertura(long codigoCobertura)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "SELECT " + "MAX(VRCCOD) \"MAX\" " + "FROM " + bonifDatabase
				+ ".BF13F1 " + "WHERE " + "COBCOD = ? ";

		int retorno = 0;

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoCobertura);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			if (ors.next()) {
				retorno = ors.getInt("MAX");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Maximo: " + retorno);
		return retorno;
	}

	/**
	 * Crea un nuevo rango asociado a una cobertura en Bienestar
	 * 
	 * @param rango:
	 *            el Objeto Rango
	 * @param long
	 *            codigoCobertura
	 * @param long
	 *            codigoGrupoRango
	 */
	public void insertRangoCobertura(Rango rango, long codigoCobertura,
			long codigoGrupoRango) throws Exception, BusinessException {
		if (rango == null || codigoCobertura == 0) {
			throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
					"Se ha intentado crear un Rango Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "INSERT INTO " + bonifDatabase + ".BF07F1 " + "(VRCCOD, "
				+ "CRARANID, " + "COBCOD, " + "CRARANINI, " + "CRARANFIN, "
				+ "CRAPCTAJE) " + "VALUES (?, ?, ?, ?, ?, ?)";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoGrupoRango);
			stmt.setDouble(2, rango.getRangoID());
			stmt.setLong(3, codigoCobertura);
			stmt.setDouble(4, rango.getRangoInicio());
			stmt.setDouble(5, rango.getRangoFin());
			stmt.setDouble(6, rango.getRangoPorcentajeCobertura());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Elimina los rangos de cobertura en Bienestar
	 * 
	 * @param long
	 *            codigoCobertura
	 * @param long
	 *            codigoGrupoRango
	 */
	public void deleteRangosCobertura(long codigoCobertura,
			long codigoGrupoRango) throws Exception, BusinessException {
		if (codigoCobertura == 0) {
			throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
					"Codigo de cobertura invalido");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "DELETE FROM " + bonifDatabase + ".BF07F1 " + "WHERE "
				+ "COBCOD = ? " + "AND " + "VRCCOD = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoCobertura);
			stmt.setLong(2, codigoGrupoRango);

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Obtiene la lista de Convenios existentes
	 * 
	 * @param Convenio
	 * @return ArrayList de Convenio
	 * @throws Exception
	 */
	public ArrayList getConvenios(Convenio convenio, long codigoCobertura)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		boolean filtrarPorCodigoCobertura = false;
		boolean filtrarPorCodigoConvenio = false;
		boolean filtrarPorNombreConvenio = false;
		boolean filtrarPorCodigoConcepto = false;
		boolean filtrarPorEstadoConvenio = false;
		boolean filtrarPorRutConvenio = false;
		int contador = 0;

		String command;

		command = "SELECT CONCOD, A.TCOCOD, TCODESCRIP, CONRUT, CONDV, CONNOM, CONMAXCUO, "
				+ "CONEST, CONFECINI, CONFECFIN FROM "
				+ bonifDatabase
				+ ".BF05F1 A, "
				+ bonifDatabase
				+ ".BF14F1 B WHERE A.TCOCOD = B.TCOCOD";

		// Primero reviso si viene consulta por codigo de Cobertura

		if (codigoCobertura != 0) {
			command = command + " AND CONCOD IN (SELECT CONCOD FROM "
					+ bonifDatabase + ".BF06F1 WHERE COBCOD = ?)";
			filtrarPorCodigoCobertura = true;
		}

		// Reviso si vienen filtros

		if (convenio.getCodigo() > 0) {
			command = command + " AND CONCOD = ?";
			filtrarPorCodigoConvenio = true;
		}
		if (convenio.getNombre() != null && (convenio.getNombre()).length() > 0) {
			command = command + " AND UCASE(CONNOM) LIKE ?";
			filtrarPorNombreConvenio = true;
		}
		if (convenio.getCodigoConcepto() != 0) {
			command = command + " AND A.TCOCOD = ?";
			filtrarPorCodigoConcepto = true;
		}
		if (convenio.getEstado() != null && (convenio.getEstado()).length() > 0) {
			command = command + " AND CONEST = ?";
			filtrarPorEstadoConvenio = true;
		}
		if (convenio.getRut() != null && (convenio.getRut()).length() > 0) {
			command = command + " AND CONRUT LIKE ?";
			filtrarPorRutConvenio = true;
		}

		command = command + " ORDER BY CONEST, CONCOD";

		logger.debug("filtrarPorCodigoCobertura: " + filtrarPorCodigoCobertura);
		logger.debug("filtrarPorCodigoConvenio: " + filtrarPorCodigoConvenio);
		logger.debug("filtrarPorNombreConvenio: " + filtrarPorNombreConvenio);
		logger.debug("filtrarPorCodigoConcepto: " + filtrarPorCodigoConcepto);
		logger.debug("filtrarPorEstadoConvenio: " + filtrarPorEstadoConvenio);
		logger.debug("filtrarPorRutConvenio: " + filtrarPorRutConvenio);

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			if (filtrarPorCodigoCobertura) {
				contador++;
				stmt.setLong(contador, codigoCobertura);
			}
			if (filtrarPorCodigoConvenio) {
				contador++;
				stmt.setLong(contador, convenio.getCodigo());
			}
			if (filtrarPorNombreConvenio) {
				contador++;
				stmt.setString(contador, '%' + convenio.getNombre()
						.toUpperCase() + '%');
			}
			if (filtrarPorCodigoConcepto) {
				contador++;
				stmt.setLong(contador, convenio.getCodigoConcepto());
			}
			if (filtrarPorEstadoConvenio) {
				contador++;
				stmt.setString(contador, convenio.getEstado());
			}
			if (filtrarPorRutConvenio) {
				contador++;
				stmt.setString(contador, '%' + convenio.getRut() + '%');
			}

			logger.debug("Dato Rut: " + '%' + convenio.getRut() + '%');
			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				Convenio con = new Convenio();
				con.setCodigo(ors.getLong("CONCOD"));
				con.setCodigoConcepto(ors.getLong("TCOCOD"));
				con.setDescripcionConcepto(ors.getString("TCODESCRIP"));
				con.setRut(ors.getString("CONRUT"));
				con.setDvRut(ors.getString("CONDV"));
				con.setNombre(ors.getString("CONNOM"));
				con.setNumeroMaximoCuotas(ors.getInt("CONMAXCUO"));
				con.setEstado(ors.getString("CONEST"));
				con.setFecInicio(ors.getDate("CONFECINI"));
				con.setFecFin(ors.getDate("CONFECFIN"));
				retorno.add(con);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene un Convenio existente
	 * 
	 * @param codigo
	 *            de Convenio
	 * @return Convenio
	 * @throws Exception
	 */
	public Convenio getConvenio(long codigoConvenio) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "SELECT CONCOD, A.TCOCOD, TCODESCRIP, CONRUT, CONDV, CONNOM, CONMAXCUO, "
				+ "CONEST, CONFECINI, CONFECFIN FROM "
				+ bonifDatabase
				+ ".BF05F1 A, "
				+ bonifDatabase
				+ ".BF14F1 B WHERE A.TCOCOD = B.TCOCOD AND CONCOD = ?";

		Convenio con = new Convenio();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoConvenio);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				con.setCodigo(ors.getLong("CONCOD"));
				con.setCodigoConcepto(ors.getLong("TCOCOD"));
				con.setDescripcionConcepto(ors.getString("TCODESCRIP"));
				con.setRut(ors.getString("CONRUT"));
				con.setDvRut(ors.getString("CONDV"));
				con.setNombre(ors.getString("CONNOM"));
				con.setNumeroMaximoCuotas(ors.getInt("CONMAXCUO"));
				con.setEstado(ors.getString("CONEST"));
				con.setFecInicio(ors.getDate("CONFECINI"));
				con.setFecFin(ors.getDate("CONFECFIN"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return con;
	}

	/**
	 * Crea un nuevo Convenio en Bienestar
	 * 
	 * @param convenio:
	 *            el Objeto Convenio
	 */
	public void insertConvenio(Convenio convenio) throws Exception,
			BusinessException {
		if (convenio == null) {
			throw new BusinessException("BusinessException",
					"Se ha intentado crear un Convenio Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		long idDisponible = 0;

		String command;

		command = "INSERT INTO "
				+ bonifDatabase
				+ ".BF05F1 (CONCOD, TCOCOD, "
				+ "CONRUT, CONDV, CONNOM, CONMAXCUO, CONEST, CONFECINI, CONFECFIN) VALUES "
				+ " (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {

			/*
			 * Obtiene un ID
			 */
			idDisponible = getID("BF05F1");
			if (idDisponible > 0) {
				convenio.setCodigo(idDisponible);
			} else {
				throw new GeneralException(this, null, "CCAF_IDINVALIDO",
						"No se pudo obtener un ID");
			}

			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, convenio.getCodigo());
			stmt.setLong(2, convenio.getCodigoConcepto());
			stmt.setString(3, convenio.getRut());
			stmt.setString(4, convenio.getDvRut());
			stmt.setString(5, convenio.getNombre());
			stmt.setInt(6, convenio.getNumeroMaximoCuotas());
			stmt.setString(7, convenio.getEstado());
			if (convenio.getFecInicio() != null) {
				stmt.setTimestamp(8, new java.sql.Timestamp(convenio
						.getFecInicio().getTime()));
			} else {
				stmt.setNull(8, Types.TIMESTAMP);
			}
			if (convenio.getFecFin() != null) {
				stmt.setTimestamp(9, new java.sql.Timestamp(convenio
						.getFecFin().getTime()));
			} else {
				stmt.setNull(9, Types.TIMESTAMP);
			}

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Modifica un Convenio en Bienestar
	 * 
	 * @param convenio:
	 *            el Objeto Convenio
	 */
	public void updateConvenio(Convenio convenio) throws Exception,
			BusinessException {
		if (convenio == null) {
			throw new BusinessException("CCAF_CONVENIOINVALIDO",
					"Se ha intentado modificar un Convenio Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "UPDATE "
				+ bonifDatabase
				+ ".BF05F1 SET TCOCOD = ?, "
				+ "CONRUT = ?, CONDV = ?, CONNOM = ?, CONMAXCUO = ?, CONEST = ?, "
				+ "CONFECINI = ?, CONFECFIN = ? WHERE CONCOD = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, convenio.getCodigoConcepto());
			stmt.setString(2, convenio.getRut());
			stmt.setString(3, convenio.getDvRut());
			stmt.setString(4, convenio.getNombre());
			stmt.setInt(5, convenio.getNumeroMaximoCuotas());
			stmt.setString(6, convenio.getEstado());
			if (convenio.getFecInicio() != null) {
				stmt.setTimestamp(7, new java.sql.Timestamp(convenio
						.getFecInicio().getTime()));
			} else {
				stmt.setNull(7, Types.TIMESTAMP);
			}
			if (convenio.getFecFin() != null) {
				stmt.setTimestamp(8, new java.sql.Timestamp(convenio
						.getFecFin().getTime()));
			} else {
				stmt.setNull(8, Types.TIMESTAMP);
			}

			stmt.setLong(9, convenio.getCodigo());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Obtiene la lista de Talonarios segun parametros de filtro
	 * 
	 * @param talonario,
	 *            Objeto alonario paa opciones de filtro y codigo convenio
	 * @return ArrayList de Talonario
	 * @throws Exception
	 */
	public ArrayList getTalonarios(Talonario talonario, long codigoConvenio)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		boolean filtrarPorInicioSecuencia = false;
		boolean filtrarPorFinSecuencia = false;
		boolean filtrarPorEstado = false;
		int contador = 0;

		String command;

		command = "SELECT TALCOD, TALFECING, TALSECINI, TALSECFIN, TALVALDSP, "
				+ "TALCODEST FROM " + bonifDatabase
				+ ".BF12F1 WHERE CONCOD = ?";

		/*
		 * Reviso si vienen filtros
		 */
		if (talonario.getInicioSecuencia() > 0) {
			command = command + " AND TALSECINI = ?";
			filtrarPorInicioSecuencia = true;
		}
		if (talonario.getFinSecuencia() > 0) {
			command = command + " AND TALSECFIN = ?";
			filtrarPorFinSecuencia = true;
		}
		if (talonario.getEstado() != null
				&& (talonario.getEstado()).length() > 0) {
			command = command + " AND TALCODEST = ?";
			filtrarPorEstado = true;
		}

		command = command + " ORDER BY TALCOD";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			contador++;
			stmt.setLong(contador, codigoConvenio);

			if (filtrarPorInicioSecuencia) {
				contador++;
				stmt.setLong(contador, talonario.getInicioSecuencia());
			}
			if (filtrarPorFinSecuencia) {
				contador++;
				stmt.setLong(contador, talonario.getFinSecuencia());
			}
			if (filtrarPorEstado) {
				contador++;
				stmt.setString(contador, talonario.getEstado());
			}

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				Talonario tal = new Talonario();
				tal.setCodigoTalonario(ors.getLong("TALCOD"));
				tal.setFechaIngreso(ors.getDate("TALFECING"));
				tal.setInicioSecuencia(ors.getLong("TALSECINI"));
				tal.setFinSecuencia(ors.getLong("TALSECFIN"));
				tal.setValeDisponible(ors.getLong("TALVALDSP"));
				tal.setEstado(ors.getString("TALCODEST"));
				retorno.add(tal);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size() + " Talonarios");
		return retorno;
	}

	/**
	 * Obtiene un Talonario existente
	 * 
	 * @param codigo
	 * @return ArrayList de Talonario
	 * @throws Exception
	 */
	public Talonario getTalonario(long codigoTalonario) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "SELECT TALCOD, TALFECING, TALSECINI, TALSECFIN, TALVALDSP, "
				+ "TALCODEST FROM " + bonifDatabase
				+ ".BF12F1 WHERE TALCOD = ?";

		Talonario tal = new Talonario();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoTalonario);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			if (ors.next()) {
				tal.setCodigoTalonario(ors.getLong("TALCOD"));
				tal.setFechaIngreso(ors.getDate("TALFECING"));
				tal.setInicioSecuencia(ors.getLong("TALSECINI"));
				tal.setFinSecuencia(ors.getLong("TALSECFIN"));
				tal.setValeDisponible(ors.getLong("TALVALDSP"));
				tal.setEstado(ors.getString("TALCODEST"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return tal;
	}

	/**
	 * Obtiene la lista de Talonarios segun parametros de filtro
	 * 
	 * @param talonario,
	 *            Objeto alonario para opciones de filtro
	 * @return ArrayList de TalonarioVO
	 * @throws Exception
	 */
	public ArrayList getTalonariosVO(TalonarioVO talonarioVO) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		boolean filtrarPorCodigoConvenio = false;
		boolean filtrarPorEstado = false;
		boolean filtrarPorCodigoTalonario = false;
		boolean filtrarPorNombreConvenio = false;
		boolean filtrarPorEstadoConvenio = false;
		int contador = 0;

		String command;

		command = "SELECT TALCOD, TALFECING, A.CONCOD, B.CONNOM, B.CONEST, TALSECINI, TALSECFIN, "
				+ "TALVALDSP, TALCODEST FROM "
				+ bonifDatabase
				+ ".BF12F1 A, "
				+ bonifDatabase + ".BF05F1 B WHERE A.CONCOD = B.CONCOD";

		/*
		 * Reviso si vienen filtros
		 */
		if (talonarioVO.getCodigoConvenio() > 0) {
			command = command + " AND A.CONCOD = ?";
			filtrarPorCodigoConvenio = true;
		}
		if (talonarioVO.getEstado() != null
				&& talonarioVO.getEstado().length() > 0) {
			command = command + " AND TALCODEST = ?";
			filtrarPorEstado = true;
		}
		if (talonarioVO.getCodigoTalonario() > 0) {
			command = command + " AND TALCOD = ?";
			filtrarPorCodigoTalonario = true;
		}
		if (talonarioVO.getNombreConvenio() != null
				&& talonarioVO.getNombreConvenio().length() > 0) {
			command = command + " AND UCASE(B.CONNOM) LIKE ?";
			filtrarPorNombreConvenio = true;
		}
		if (talonarioVO.getEstadoConvenio() != null
				&& talonarioVO.getEstadoConvenio().length() > 0) {
			command = command + " AND B.CONEST = ?";
			filtrarPorEstadoConvenio = true;
		}

		command = command + " ORDER BY B.CONEST,A.CONCOD,TALCOD,TALCODEST";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			if (filtrarPorCodigoConvenio) {
				contador++;
				stmt.setLong(contador, talonarioVO.getCodigoConvenio());
			}
			if (filtrarPorEstado) {
				contador++;
				stmt.setString(contador, talonarioVO.getEstado());
			}
			if (filtrarPorCodigoTalonario) {
				contador++;
				stmt.setLong(contador, talonarioVO.getCodigoTalonario());
			}
			if (filtrarPorNombreConvenio) {
				contador++;
				stmt.setString(contador, '%' + talonarioVO.getNombreConvenio()
						.toUpperCase() + '%');
			}
			if (filtrarPorEstadoConvenio) {
				contador++;
				stmt.setString(contador, talonarioVO.getEstadoConvenio());
			}

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				TalonarioVO talVO = new TalonarioVO();
				talVO.setCodigoTalonario(ors.getLong("TALCOD"));
				talVO.setFechaIngreso(ors.getDate("TALFECING"));
				talVO.setCodigoConvenio(ors.getLong("CONCOD"));
				talVO.setNombreConvenio(ors.getString("CONNOM"));
				talVO.setEstadoConvenio(ors.getString("CONEST"));
				talVO.setInicioSecuencia(ors.getLong("TALSECINI"));
				talVO.setFinSecuencia(ors.getLong("TALSECFIN"));
				talVO.setValeDisponible(ors.getLong("TALVALDSP"));
				talVO.setEstado(ors.getString("TALCODEST"));
				retorno.add(talVO);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size() + " Talonarios");
		return retorno;
	}

	/**
	 * Crea un nuevo Talonario asociado a un Convenio en Bienestar
	 * 
	 * @param talonario:
	 *            el Objeto Talonario y codigo de convenio
	 */
	public void insertTalonario(Talonario talonario, long codigoConvenio)
			throws Exception, BusinessException {
		if (talonario == null) {
			throw new BusinessException("CCAF_BONIF_TALONARIOINVALIDO",
					"Se ha intentado crear un Talonario Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		long idDisponible = 0;

		String command;

		command = "INSERT INTO " + bonifDatabase + ".BF12F1 (TALCOD, CONCOD, "
				+ "TALFECING, TALSECINI, TALSECFIN, TALVALDSP, TALCODEST) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {

			/*
			 * Obtiene un ID
			 */
			idDisponible = getID("BF12F1");
			if (idDisponible > 0) {
				talonario.setCodigoTalonario(idDisponible);
			} else {
				throw new GeneralException(this, null, "CCAF_IDINVALIDO",
						"No se pudo obtener un ID");
			}

			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, talonario.getCodigoTalonario());
			stmt.setLong(2, codigoConvenio);
			stmt.setTimestamp(3, new java.sql.Timestamp(talonario
					.getFechaIngreso().getTime()));
			stmt.setLong(4, talonario.getInicioSecuencia());
			stmt.setLong(5, talonario.getFinSecuencia());
			stmt.setLong(6, talonario.getValeDisponible());
			stmt.setString(7, talonario.getEstado());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Actualiza la informacion de un Talonario asociado a un Convenio en
	 * Bienestar
	 * 
	 * @param talonario:
	 *            el Objeto Talonario y codigo de convenio
	 */
	public void updateTalonario(Talonario talonario) throws Exception,
			BusinessException {
		if (talonario == null) {
			throw new BusinessException("CCAF_BONIF_TALONARIOINVALIDO",
					"Se ha intentado modificar un Talonario Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "UPDATE "
				+ bonifDatabase
				+ ".BF12F1 SET TALSECINI = ?, "
				+ "TALSECFIN = ?, TALVALDSP = ?, TALCODEST = ? WHERE TALCOD = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, talonario.getInicioSecuencia());
			stmt.setLong(2, talonario.getFinSecuencia());
			stmt.setLong(3, talonario.getValeDisponible());
			stmt.setString(4, talonario.getEstado());
			stmt.setLong(5, talonario.getCodigoTalonario());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Obtiene la lista de Productos asociados a un convenio
	 * 
	 * @param producto
	 *            Producto
	 * @return ArrayList de Producto
	 * @throws Exception
	 */
	public ArrayList getProductos(Producto producto, long codigoConvenio)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		boolean filtrarPorCodigoCobertura = false;
		boolean filtrarPorCodigoConcepto = false;
		boolean filtrarPorDescripcionCobertura = false;
		int contador = 0;

		String command;

		// @TODO cambiar nombre columna PRDDESSOC

		command = "SELECT A.COBCOD, B.TCOCOD, "
				+ "C.TCODESCRIP, B.COBTOPE, B.COBGLS, "
				+ "B.COBVALREF, B.COBTIPTOPE, B.COBPERCAR, "
				+ "B.COBCTAGSTO, B.COBOCU, B.COBOCUETI, "
				+ "A.PRDDSCTO, A.PRDFECING, "
				+ "A.PRDEST, A.PRDCTAGSTO, A.PRDDESSOC " + "FROM "
				+ bonifDatabase + ".BF06F1 A, " + bonifDatabase + ".BF04F1 B, "
				+ bonifDatabase + ".BF14F1 C " + "WHERE "
				+ "A.COBCOD = B.COBCOD AND " + "B.TCOCOD = C.TCOCOD AND "
				+ "A.CONCOD = ? AND " + "A.PRDEST = ?";

		/*
		 * Reviso si vienen filtros
		 */
		if (producto.getCobertura().getCodigo() > 0) {
			command = command + " AND A.COBCOD = ?";
			filtrarPorCodigoCobertura = true;
		}
		if (producto.getCobertura().getConceptoCodigo() != 0) {
			command = command + " AND B.TCOCOD = ?";
			filtrarPorCodigoConcepto = true;
		}
		if (producto.getCobertura().getDescripcion() != null
				&& (producto.getCobertura().getDescripcion()).length() > 0) {
			command = command + " AND UCASE(B.COBGLS) LIKE ?";
			filtrarPorDescripcionCobertura = true;
		}

		command = command + " ORDER BY A.COBCOD";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			contador++;
			stmt.setLong(contador, codigoConvenio);
			contador++;
			stmt.setString(contador, producto.getEstado());

			if (filtrarPorCodigoCobertura) {
				contador++;
				stmt.setLong(contador, producto.getCobertura().getCodigo());
			}
			if (filtrarPorCodigoConcepto) {
				contador++;
				stmt.setLong(contador, producto.getCobertura()
						.getConceptoCodigo());
			}
			if (filtrarPorDescripcionCobertura) {
				contador++;
				stmt.setString(contador, '%' + producto.getCobertura()
						.getDescripcion().toUpperCase() + '%');
			}

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				Cobertura cob = new Cobertura();
				Producto pro = new Producto();
				cob.setCodigo(ors.getLong("COBCOD"));
				cob.setConceptoCodigo(ors.getLong("TCOCOD"));
				cob.setConceptoDescripcion(ors.getString("TCODESCRIP"));
				cob.setTope(ors.getDouble("COBTOPE"));
				cob.setDescripcion(ors.getString("COBGLS"));
				cob.setValorReferencial(ors.getDouble("COBVALREF"));
				cob.setTipoTope(ors.getString("COBTIPTOPE"));
				cob.setPeriodoCarencia(ors.getInt("COBPERCAR"));
				cob.setCuentaGasto(ors.getLong("COBCTAGSTO"));
				cob.setTieneOcurrencias(ors.getString("COBOCU"));
				cob.setEtiquetaOcurrencia(ors.getString("COBOCUETI"));
				pro.setCobertura(cob);
				pro.setDescuento(ors.getInt("PRDDSCTO"));
				pro.setFechaIngreso(ors.getDate("PRDFECING"));
				pro.setCuentaGasto(ors.getLong("COBCTAGSTO"));
				// @TODO cambiar nombre columna PRDDESSOC
				pro.setPorcentajeAporteConvenio(ors.getInt("PRDDESSOC"));
				retorno.add(pro);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return retorno;
	}

	/**
	 * Obtiene un Producto asociado a un convenio
	 * 
	 * @param codigo
	 *            de producto y codigo de convenio
	 * @return producto
	 * @throws Exception
	 */
	public Producto getProducto(long codProducto, long codConvenio)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		// @TODO cambiar nombre columna PRDDESSOC
		command = "SELECT A.COBCOD, B.TCOCOD, C.TCODESCRIP, "
				+ "B.COBTOPE, B.COBGLS, B.COBVALREF, B.COBTIPTOPE, "
				+ "B.COBPERCAR, B.COBCTAGSTO, B.COBOCU, B.COBOCUETI, "
				+ "A.PRDDSCTO, A.PRDFECING, A.PRDEST, A.PRDCTAGSTO, "
				+ "A.PRDDESSOC " + "FROM " + bonifDatabase + ".BF06F1 A, "
				+ bonifDatabase + ".BF04F1 B, " + bonifDatabase + ".BF14F1 C "
				+ "WHERE " + "A.COBCOD = B.COBCOD AND "
				+ "B.TCOCOD = C.TCOCOD AND " + "A.CONCOD = ? AND "
				+ "A.COBCOD = ?";

		Producto pro = new Producto();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codConvenio);
			stmt.setLong(2, codProducto);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			if (ors.next()) {
				Cobertura cob = new Cobertura();
				cob.setCodigo(ors.getLong("COBCOD"));
				cob.setConceptoCodigo(ors.getLong("TCOCOD"));
				cob.setConceptoDescripcion(ors.getString("TCODESCRIP"));
				cob.setTope(ors.getDouble("COBTOPE"));
				cob.setDescripcion(ors.getString("COBGLS"));
				cob.setValorReferencial(ors.getDouble("COBVALREF"));
				cob.setTipoTope(ors.getString("COBTIPTOPE"));
				cob.setPeriodoCarencia(ors.getInt("COBPERCAR"));
				cob.setCuentaGasto(ors.getLong("COBCTAGSTO"));
				cob.setTieneOcurrencias(ors.getString("COBOCU"));
				cob.setEtiquetaOcurrencia(ors.getString("COBOCUETI"));
				pro.setCobertura(cob);
				pro.setDescuento(ors.getInt("PRDDSCTO"));
				pro.setFechaIngreso(ors.getDate("PRDFECING"));
				pro.setEstado(ors.getString("PRDEST"));
				pro.setCuentaGasto(ors.getLong("COBCTAGSTO"));
				// @TODO cambiar nombre columna PRDDESSOC
				pro.setPorcentajeAporteConvenio(ors.getInt("PRDDESSOC"));
				logger.debug("encontro producto");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return pro;
	}

	/**
	 * Crea un nuevo Producto asociado a un Convenio en Bienestar
	 * 
	 * @param producto:
	 *            el Objeto Producto y codigo de convenio
	 */
	public void insertProducto(Producto producto, long codigoConvenio)
			throws Exception, BusinessException {
		if (producto == null) {
			throw new BusinessException("CCAF_BONIF_PRODUCTOINVALIDO",
					"Se ha intentado crear un Producto Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		// @TODO cambiar nombre columna PRDDESSOC
		command = "INSERT INTO "
				+ bonifDatabase
				+ ".BF06F1 (CONCOD, "
				+ "COBCOD, PRDDSCTO, PRDFECING, PRDEST, PRDDESSOC) VALUES (?,?,?,?,?,?)";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoConvenio);
			stmt.setLong(2, producto.getCobertura().getCodigo());
			stmt.setDouble(3, producto.getDescuento());
			stmt.setTimestamp(4, new java.sql.Timestamp(producto
					.getFechaIngreso().getTime()));
			stmt.setString(5, producto.getEstado());
			stmt.setInt(6, producto.getPorcentajeAporteConvenio());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Actualiza un Producto asociado a un Convenio en Bienestar
	 * 
	 * @param producto:
	 *            el Objeto Producto y codigo de convenio
	 */
	public void updateProducto(Producto producto, long codigoConvenio)
			throws Exception, BusinessException {
		if (producto == null) {
			throw new BusinessException("CCAF_BONIF_PRODUCTOINVALIDO",
					"Se ha intentado actualizar un Producto Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		// @TODO cambiar nombre columna PRDDESSOC
		command = "UPDATE " + bonifDatabase
				+ ".BF06F1 SET PRDDSCTO = ?, PRDEST = ?, PRDDESSOC = ? "
				+ "WHERE CONCOD = ? AND COBCOD = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setDouble(1, producto.getDescuento());
			stmt.setString(2, producto.getEstado());
			stmt.setInt(3, producto.getPorcentajeAporteConvenio());
			stmt.setLong(4, codigoConvenio);
			stmt.setLong(5, producto.getCobertura().getCodigo());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Actualiza la cuenta de gastos de un Producto asociado a un Convenio en
	 * Bienestar
	 * 
	 * @param producto:
	 *            el Objeto Producto y codigo de convenio
	 */
	public void updateCuentaProducto(Producto producto, long codigoConvenio)
			throws Exception, BusinessException {
		if (producto == null) {
			throw new BusinessException("CCAF_BONIF_PRODUCTOINVALIDO",
					"Se ha intentado actualizar un Producto Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "UPDATE " + bonifDatabase + ".BF06F1 SET PRDCTAGSTO = ? "
				+ "WHERE CONCOD = ? AND COBCOD = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, producto.getCuentaGasto());
			stmt.setLong(2, codigoConvenio);
			stmt.setLong(3, producto.getCobertura().getCodigo());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Actualiza el Estado de los Producto de un mismo código
	 * 
	 * @param codigoProducto
	 * @param estadoOld
	 * @param estadoNew
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void updateEstadoProductos(long codigoProducto, String estadoOld,
			String estadoNew) throws Exception, BusinessException {
		if (codigoProducto == 0) {
			throw new BusinessException("CCAF_BONIF_PRODUCTOINVALIDO",
					"Se ha intentado actualizar un Producto Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "UPDATE " + bonifDatabase + ".BF06F1 SET PRDEST = ? "
				+ "WHERE COBCOD = ? AND PRDEST = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, estadoNew);
			stmt.setLong(2, codigoProducto);
			stmt.setString(3, estadoOld);

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Obtiene los Rangos de un Producto existente
	 * 
	 * @param codigo
	 *            convenio y codigo de producto
	 * @return ArrayList de Rangos
	 * @throws Exception
	 */
	public ArrayList getRangosProducto(long codigoConvenio, long codigoProducto)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "SELECT PRARANID, CONCOD, COBCOD, PRARANINI, PRARANFIN, PRAPJECOB "
				+ "FROM "
				+ bonifDatabase
				+ ".BF11F1 WHERE CONCOD = ? AND COBCOD = ? "
				+ "ORDER BY PRARANID";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoConvenio);
			stmt.setLong(2, codigoProducto);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				Rango ran = new Rango();
				ran.setRangoID(ors.getDouble("PRARANID"));
				ran.setRangoInicio(ors.getDouble("PRARANINI"));
				ran.setRangoFin(ors.getDouble("PRARANFIN"));
				ran.setRangoPorcentajeCobertura(ors.getDouble("PRAPJECOB"));
				retorno.add(ran);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Crea un nuevo rango asociado a un producto
	 * 
	 * @param producto:
	 *            el Objeto Producto, codigo de Convenio y codigo de producto
	 */
	public void insertRangoProducto(Rango rango, long codigoConvenio,
			long codigoCobertura) throws Exception, BusinessException {
		if (rango == null) {
			throw new BusinessException("CCAF_BONIF_PRODUCTOINVALIDO",
					"Se ha intentado crear un Rango Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "INSERT INTO " + bonifDatabase
				+ ".BF11F1 (PRARANID, CONCOD, COBCOD, "
				+ "PRARANINI, PRARANFIN, PRAPJECOB) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setDouble(1, rango.getRangoID());
			stmt.setLong(2, codigoConvenio);
			stmt.setLong(3, codigoCobertura);
			stmt.setDouble(4, rango.getRangoInicio());
			stmt.setDouble(5, rango.getRangoFin());
			stmt.setDouble(6, rango.getRangoPorcentajeCobertura());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Elimina los rangos asociado a un Producto
	 * 
	 * @param codigo
	 *            de Convenio y codigo de producto
	 */
	public void deleteRangosProducto(long codigoConvenio, long codigoProducto)
			throws Exception, BusinessException {
		if (codigoConvenio == 0 || codigoProducto == 0) {
			throw new BusinessException("CCAF_BONIF_PRODUCTOINVALIDO",
					"Se ha intentado modificar un Rango invalido");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "DELETE FROM " + bonifDatabase + ".BF11F1 "
				+ "WHERE CONCOD = ? AND COBCOD = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoConvenio);
			stmt.setLong(2, codigoProducto);

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Obtiene una lista de Vales
	 * 
	 * @param vale
	 *            Objeto Vale
	 * @return ArrayList de Vale
	 * @throws Exception
	 */
	public ArrayList getVales(Vale vale, String socioRut, long codigoConvenio)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		boolean filtrarPorRutSocio = false;
		boolean filtrarPorcodigoConvenio = false;
		boolean filtrarPorCodigoVale = false;
		boolean filtrarPorCodigoTalonario = false;
		boolean filtrarPorCasoId = false;
		boolean filtrarPorEstadoVale = false;
		int contador = 0;

		String command;

		command = "SELECT VALCOD, A.TALCOD, C.CONCOD, C.CONNOM, A.SOCRUT, D.DV SOCDV, CASID, VALFECENTR, "
				+ "VALFECRECE, VALMONTO, VALANULADO FROM "
				+ bonifDatabase
				+ ".BF15F1 A, "
				+ bonifDatabase
				+ ".BF12F1 B, "
				+ bonifDatabase
				+ ".BF05F1 C, "
				+ bonifDatabase
				+ ".FUNCIONARIOS D "
				+ "WHERE A.TALCOD = B.TALCOD AND B.CONCOD = C.CONCOD AND D.RUT = A.SOCRUT ";

		if (socioRut != null && socioRut.length() > 0) {
			logger.debug("Rut: " + socioRut);
			// devuelve un conjunto de registros
			command = command + " AND A.SOCRUT = ?";
			filtrarPorRutSocio = true;
		}
		if (codigoConvenio > 0) {
			logger.debug("Codigo Convenio: " + codigoConvenio);
			command = command + " AND C.CONCOD = ?";
			filtrarPorcodigoConvenio = true;
		}

		if (vale != null) {
			// Reviso si vienen filtros
			if (vale.getCodigoVale() > 0) {
				logger.debug("Codigo Vale: " + vale.getCodigoVale());
				// devuelve solo un registro
				command = command + " AND VALCOD = ?";
				filtrarPorCodigoVale = true;
			}
			if (vale.getCodigoTalonario() > 0) {
				logger.debug("Codigo Talonario: " + vale.getCodigoTalonario());
				// devuelve un conjunto de registros
				command = command + " AND A.TALCOD = ?";
				filtrarPorCodigoTalonario = true;
			}
			if (vale.getCaso_id() >= 0) {
				logger.debug("Caso Id: " + vale.getCaso_id());
				// devuelve solo un registro
				command = command + " AND CASID = ?";
				filtrarPorCasoId = true;
			}
			if (vale.getValeAnulado() != null
					&& vale.getValeAnulado().length() > 0) {
				logger.debug("Vale Anulado: " + vale.getValeAnulado());
				command = command + " AND VALANULADO = ?";
				filtrarPorEstadoVale = true;
			}
		}

		command = command + " ORDER BY CASID, A.TALCOD, VALCOD";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			if (filtrarPorRutSocio) {
				contador++;
				stmt.setString(contador, socioRut);
			}
			if (filtrarPorcodigoConvenio) {
				contador++;
				stmt.setLong(contador, codigoConvenio);
			}
			if (filtrarPorCodigoVale) {
				contador++;
				stmt.setLong(contador, vale.getCodigoVale());
			}
			if (filtrarPorCodigoTalonario) {
				contador++;
				stmt.setLong(contador, vale.getCodigoTalonario());
			}
			if (filtrarPorCasoId) {
				contador++;
				stmt.setDouble(contador, vale.getCaso_id());
			}
			if (filtrarPorEstadoVale) {
				contador++;
				stmt.setString(contador, vale.getValeAnulado());
			}

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				Vale val = new Vale();
				val.setCodigoVale(ors.getLong("VALCOD"));
				val.setCodigoTalonario(ors.getLong("TALCOD"));
				val.setCaso_id(ors.getLong("CASID"));
				val.setFechaEntrega(ors.getDate("VALFECENTR"));
				val.setFechaRecepcion(ors.getDate("VALFECRECE"));
				val.setMonto(ors.getDouble("VALMONTO"));
				val.setValeAnulado(ors.getString("VALANULADO"));
				val.setCodigoConvenio(ors.getLong("CONCOD"));
				val.setNombreConvenio(ors.getString("CONNOM"));
				val.setRutSocio(ors.getString("SOCRUT"));
				val.setRutSocioDv(ors.getString("SOCDV"));

				retorno.add(val);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return retorno;
	}

	/**
	 * Asocia un Vale con un Socio
	 * 
	 * @param vale,
	 *            Objeto Vale y rut de Socio
	 */
	public void insertVale(Vale vale, String rutSocio) throws Exception,
			BusinessException {
		if (vale == null) {
			throw new BusinessException("CCAF_BONIF_VALEINVALIDO",
					"Se ha intentado crear un Vale Nulo");
		} else if (rutSocio == null) {
			throw new BusinessException("CCAF_BONIF_VALEINVALIDO",
					"Se ha intentado crear un Vale con el rut de socio Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "INSERT INTO " + bonifDatabase + ".BF15F1 (VALCOD, TALCOD, "
				+ "SOCRUT, CASID, VALFECENTR, VALMONTO, VALANULADO) VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?)";

		try {
			logger.debug("Pasando parametros en insertVale");
			logger.debug("Codigo Talonario: " + vale.getCodigoTalonario());
			logger.debug("Codigo Vale: " + vale.getCodigoVale());
			logger.debug("Socio Rut: " + rutSocio);
			logger.debug("Caso Id: " + vale.getCaso_id());
			logger.debug("Monto: " + vale.getMonto());
			logger.debug("Fec Entrega: "
					+ new java.sql.Timestamp(vale.getFechaEntrega().getTime()));
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, vale.getCodigoVale());
			stmt.setLong(2, vale.getCodigoTalonario());
			stmt.setString(3, rutSocio);
			stmt.setLong(4, vale.getCaso_id());
			stmt.setTimestamp(5, new java.sql.Timestamp(vale.getFechaEntrega()
					.getTime()));
			stmt.setDouble(6, vale.getMonto());
			stmt.setString(7, vale.getValeAnulado());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Actualiza la informacion de un Vale
	 * 
	 * @param vale,
	 *            Objeto Vale y rut de Socio
	 */
	public void updateVale(Vale vale, String rutSocio) throws Exception,
			BusinessException {
		if (vale == null) {
			throw new BusinessException("CCAF_BONIF_VALEINVALIDO",
					"Se ha intentado actualizar un Vale Nulo");
		} else if (rutSocio == null) {
			throw new BusinessException("CCAF_VALEINVALIDO",
					"Se ha intentado actualizar un Vale con el rut de socio Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "UPDATE "
				+ bonifDatabase
				+ ".BF15F1 SET CASID = ?, "
				+ "VALFECRECE = ?, VALMONTO = ?, VALANULADO = ? WHERE VALCOD = ? "
				+ "AND TALCOD = ? AND SOCRUT = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			logger.debug("Vale: " + vale.getCaso_id());
			stmt.setLong(1, vale.getCaso_id());
			logger.debug("Fecha recepcion: " + vale.getFechaRecepcion());
			stmt.setTimestamp(2, new java.sql.Timestamp(vale
					.getFechaRecepcion().getTime()));
			logger.debug("Monto: " + vale.getMonto());
			stmt.setDouble(3, vale.getMonto());
			logger.debug("Vale Anulado: " + vale.getValeAnulado());
			stmt.setString(4, vale.getValeAnulado());
			logger.debug("Codigo Vale: " + vale.getCodigoVale());
			stmt.setLong(5, vale.getCodigoVale());
			logger.debug("Codigo Talonario: " + vale.getCodigoTalonario());
			stmt.setLong(6, vale.getCodigoTalonario());
			logger.debug("Rut Socio: " + rutSocio);
			stmt.setString(7, rutSocio);

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Obtiene el ID disponible de la tabla pasada como parametro Es para
	 * mantener un valor que sirva como identity
	 * 
	 * @param nombre
	 *            de la tabla que necesita un nuevo ID
	 * @return ID disponible
	 * @throws Exception
	 */
	public long getIDDisponible(String tabla) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		long idDisponible = 0;

		String command;

		command = "SELECT CTDVALDSP FROM " + bonifDatabase + ".BF23F1 "
				+ "WHERE CTDCOD = ?";

		// valida que el nombre de la tabla no sea nulo y que el largo no sea
		// distinto a 6 (seis)
		if (tabla == null || tabla.length() != 6) {
			throw new BusinessException("CCAF_BONIF_IDENTIFICADORINVALIDO",
					"El nombre de la tabla es incorrecto");
		}

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, tabla);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			if (ors.next()) {
				idDisponible = ors.getLong("CTDVALDSP");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return idDisponible;
	}

	/**
	 * Actualiza el ID disponible de la tabla pasada como parametro Es para
	 * mantener un valor que sirva como identity
	 * 
	 * @param nombre
	 *            de la tabla que necesita un nuevo ID
	 * @param nuevo
	 *            valor disponible
	 * @return ID disponible
	 * @throws Exception
	 */
	public int updateIDDisponible(String tabla, double nuevoValorDisponible)
			throws Exception, BusinessException {
		if (tabla == null || tabla.length() != 6) {
			throw new BusinessException("CCAF_BONIF_IDENTIFICADORINVALIDO",
					"El nombre de la tabla es incorrecto");
		} else if (nuevoValorDisponible == 0) {
			throw new BusinessException("CCAF_BONIF_IDENTIFICADORINVALIDO",
					"El nuevo valor del ID es incorrecto");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		int filasActualizadas = 0;

		String command;

		command = "UPDATE " + bonifDatabase + ".BF23F1 SET CTDVALDSP = ? "
				+ "WHERE CTDCOD = ? AND CTDVALDSP = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setDouble(1, nuevoValorDisponible);
			stmt.setString(2, tabla);
			stmt.setDouble(3, nuevoValorDisponible - 1);

			logger.debug("Inicia operación: " + command);
			filasActualizadas = stmt.executeUpdate();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return filasActualizadas;
	}

	/**
	 * Obtiene un nuevo ID para la tabla pasada como parametro
	 * 
	 * @param nombre
	 *            de la tabla que necesita un nuevo ID
	 */
	public long getID(String tabla) throws Exception, BusinessException {
		long idDisponibleActual = 0;
		int intentos = 0;

		do {
			intentos++;
			idDisponibleActual = getIDDisponible(tabla);
			if (idDisponibleActual > 0) {
				if (updateIDDisponible(tabla, idDisponibleActual + 1) == 0) {
					idDisponibleActual = 0;
				}
			}
		} while (idDisponibleActual == 0 && intentos < 10);
		logger.debug("idDisponibleActual: " + idDisponibleActual
				+ " Intentos: " + intentos);
		return idDisponibleActual;
	}

	/**
	 * Obtiene un nuevo ID para la tabla pasada como parametro y no lo registra
	 * 
	 * @param nombre
	 *            de la tabla que necesita un nuevo ID
	 */
	public long getID_(String tabla) throws Exception, BusinessException {

		long idDisponibleActual = 0;
		//int intentos = 0;

		idDisponibleActual = getIDDisponible(tabla);
		return idDisponibleActual;
	}

	/**
	 * Obtiene una lista de Eventos
	 * 
	 * @param codigo
	 *            de caso (caso ID)
	 * @param evento,
	 *            Objeto Evento con filtros posibles
	 * @return ArrayList de Evento
	 * @throws Exception
	 */
	public ArrayList getEventos(long casoId, Evento evento) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		boolean filtrarPorTipoEvento = false;
		boolean filtrarPorUsuario = false;
		int contador = 0;

		String command;

		command = "SELECT EVECOMEN, EVEFECING, EVETIPCOD, EVEUSUARIO FROM "
				+ bonifDatabase + ".BF10F1 WHERE CASID = ?";

		/*
		 * Reviso si vienen filtros
		 */
		if (evento != null) {
			if (evento.getTipo() != null && evento.getTipo().length() > 0) {
				command = command + " AND EVETIPCOD = ?";
				filtrarPorTipoEvento = true;
			}
			if (evento.getUsuario() != null && evento.getUsuario().length() > 0) {
				command = command + " AND EVEUSUARIO = ?";
				filtrarPorUsuario = true;
			}
		}

		command = command + " ORDER BY EVEFECING";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			contador++;
			stmt.setLong(contador, casoId);

			if (filtrarPorTipoEvento) {
				contador++;
				stmt.setString(contador, evento.getTipo());
			}
			if (filtrarPorUsuario) {
				contador++;
				stmt.setString(contador, evento.getUsuario());
			}

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				Evento eve = new Evento();
				eve.setComentario(ors.getString("EVECOMEN"));
				eve.setFechaIngreso(ors.getDate("EVEFECING"));
				eve.setTipo(ors.getString("EVETIPCOD"));
				eve.setUsuario(ors.getString("EVEUSUARIO"));
				retorno.add(eve);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return retorno;
	}

	/**
	 * Crea un Evento asociado a un Caso
	 * 
	 * @param caso
	 *            Id y Evento con los datos
	 */
	public void insertEvento(long casoId, Evento evento) throws Exception,
			BusinessException {
		if (evento == null) {
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"Se ha intentado crear un Evento Nulo");
		} else if (casoId == 0) {
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"Se ha intentado crear un Evento con el caso Id Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "INSERT INTO " + bonifDatabase + ".BF10F1 (CASID, EVECOMEN, "
				+ "EVEFECING, EVETIPCOD, EVEUSUARIO) VALUES (?, ?, ?, ?, ?)";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, casoId);
			stmt.setString(2, evento.getComentario());
			stmt.setTimestamp(3, new java.sql.Timestamp(evento
					.getFechaIngreso().getTime()));
			stmt.setString(4, evento.getTipo());
			stmt.setString(5, evento.getUsuario());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Obtiene una lista de Detalle de Caso
	 * 
	 * @param codigo
	 *            de caso (caso ID)
	 * @return ArrayList de Detalle Caso
	 * @throws Exception
	 */
	public ArrayList getDetallesCaso(long casoId) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		// @TODO cambiar nombre columna PRDDESSOC
		command = "SELECT DCAID, A.COBCOD, B.TCOCOD, "
				+ "B.COBTOPE, B.COBGLS, B.COBVALREF, "
				+ "B.COBTIPTOPE, B.COBEST, B.COBPERCAR, "
				+ "B.COBOCU, B.COBOCUETI, "
				+ "DCAMONTO, DCAMTODCTO, DCAAPOISA, "
				+ "DCAAPOBIE, DCAAPOSOC, DCAAPOCON, "
				+ "DCANUMDOC, DCACNTOCU, " + "C.PRDDSCTO, C.PRDFECING, "
				+ "C.PRDEST, C.PRDDESSOC " + "FROM " + bonifDatabase
				+ ".BF09F1 A, " + bonifDatabase + ".BF04F1 B, " + bonifDatabase
				+ ".BF06F1 C " + "WHERE " + "A.COBCOD = C.COBCOD AND "
				+ "A.CONCOD = C.CONCOD AND " + "A.COBCOD = B.COBCOD AND "
				+ "CASID = ? " + "ORDER BY A.COBCOD";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, casoId);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				DetalleCaso detalleCaso = new DetalleCaso();
				Producto producto = new Producto();
				Cobertura cobertura = new Cobertura();
				cobertura.setCodigo(ors.getLong("COBCOD"));
				cobertura.setConceptoCodigo(ors.getLong("TCOCOD"));
				cobertura.setTope(ors.getDouble("COBTOPE"));
				cobertura.setDescripcion(ors.getString("COBGLS"));
				cobertura.setValorReferencial(ors.getDouble("COBVALREF"));
				cobertura.setTipoTope(ors.getString("COBTIPTOPE"));
				cobertura.setEstado(ors.getString("COBEST"));
				cobertura.setPeriodoCarencia(ors.getInt("COBPERCAR"));
				cobertura.setTieneOcurrencias(ors.getString("COBOCU"));
				cobertura.setEtiquetaOcurrencia(ors.getString("COBOCUETI"));
				producto.setCobertura(cobertura);
				producto.setDescuento(ors.getDouble("PRDDSCTO"));
				producto.setFechaIngreso(ors.getDate("PRDFECING"));
				producto.setEstado(ors.getString("PRDEST"));
				// @TODO cambiar nombre columna PRDDESSOC
				producto.setPorcentajeAporteConvenio(ors.getInt("PRDDESSOC"));
				detalleCaso.setProducto(producto);
				detalleCaso.setIdDetalle(ors.getInt("DCAID"));
				detalleCaso.setMonto(ors.getDouble("DCAMONTO"));
				detalleCaso.setMontoDescuento(ors.getDouble("DCAMTODCTO"));
				detalleCaso.setAporteIsapre(ors.getDouble("DCAAPOISA"));
				detalleCaso.setAporteBienestar(ors.getDouble("DCAAPOBIE"));
				detalleCaso.setAporteSocio(ors.getDouble("DCAAPOSOC"));
				detalleCaso.setDocumento(ors.getString("DCANUMDOC"));
				detalleCaso.setAporteConvenio(ors.getDouble("DCAAPOCON"));
				detalleCaso.setCantidadOcurencias(ors.getInt("DCACNTOCU"));
				retorno.add(detalleCaso);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return retorno;
	}

	/**
	 * Crea un Detalle asociado a un Caso
	 * 
	 * @param caso
	 *            Id y Detalle Caso con los datos
	 */
	public void insertDetalle(long casoId, long codigoConvenio,
			DetalleCaso detalleCaso) throws Exception, BusinessException {
		if (detalleCaso == null) {
			throw new BusinessException("CCAF_BONIF_DETALLEINVALIDO",
					"Se ha intentado crear un Detalle Nulo");
		} else if (casoId == 0) {
			throw new BusinessException("CCAF_BONIF_DETALLEINVALIDO",
					"Se ha intentado crear un Detalle con el caso Id Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "INSERT INTO "
				+ bonifDatabase
				+ ".BF09F1 (DCAID, COBCOD, CASID, CONCOD, "
				+ "DCAMONTO, DCAMTODCTO, DCAAPOISA, DCAAPOBIE, DCAAPOSOC, DCANUMDOC, DCAAPOCON, DCACNTOCU) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			logger.debug("Detalle Id: " + detalleCaso.getIdDetalle());
			stmt.setInt(1, detalleCaso.getIdDetalle());
			logger.debug("Codigo Cobertura: "
					+ detalleCaso.getProducto().getCobertura().getCodigo());
			stmt.setLong(2, detalleCaso.getProducto().getCobertura()
					.getCodigo());
			logger.debug("Caso Id: " + casoId);
			stmt.setLong(3, casoId);
			logger.debug("codigoConvenio: " + codigoConvenio);
			stmt.setLong(4, codigoConvenio);
			logger.debug("Monto: " + detalleCaso.getMonto());
			stmt.setDouble(5, detalleCaso.getMonto());
			logger.debug("Descuento: " + detalleCaso.getMontoDescuento());
			stmt.setDouble(6, detalleCaso.getMontoDescuento());
			logger.debug("Aporte Isapre: " + detalleCaso.getAporteIsapre());
			stmt.setDouble(7, detalleCaso.getAporteIsapre());
			logger.debug("Aporte Bienestar: "
					+ detalleCaso.getAporteBienestar());
			stmt.setDouble(8, detalleCaso.getAporteBienestar());
			logger.debug("Aporte Socio: " + detalleCaso.getAporteSocio());
			stmt.setDouble(9, detalleCaso.getAporteSocio());
			logger.debug("Documento: " + detalleCaso.getDocumento());
			stmt.setString(10, detalleCaso.getDocumento());
			logger.debug("Aporte Convenio: " + detalleCaso.getAporteConvenio());
			stmt.setDouble(11, detalleCaso.getAporteConvenio());
			logger.debug("Cantidad Ocurrencias: "
					+ detalleCaso.getCantidadOcurencias());
			stmt.setDouble(12, detalleCaso.getCantidadOcurencias());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Modifica un Detalle asociado a un Caso
	 * 
	 * @param caso
	 *            Id y Detalle caso con los datos
	 */
	public void updateDetalle(long casoId, DetalleCaso detalleCaso)
			throws Exception, BusinessException {
		if (detalleCaso == null) {
			throw new BusinessException("CCAF_BONIF_DETALLEINVALIDO",
					"Se ha intentado actualizar un Detalle Nulo");
		} else if (casoId == 0) {
			throw new BusinessException("CCAF_BONIF_DETALLEINVALIDO",
					"Se ha intentado actualizar un Detalle con el caso Id Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "UPDATE " + bonifDatabase + ".BF09F1 " + "SET "
				+ "DCAMONTO = ?, " + "DCAMTODCTO = ?, " + "DCAAPOISA = ?, "
				+ "DCAAPOBIE = ?, " + "DCAAPOSOC = ?, " + "DCANUMDOC = ?, "
				+ "DCAAPOCON = ?, " + "DCACNTOCU = ? " + "WHERE "
				+ "CASID = ? AND " + "DCAID = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			logger.debug("Monto: " + detalleCaso.getMonto());
			stmt.setDouble(1, detalleCaso.getMonto());
			logger.debug("Monto Descuento: " + detalleCaso.getMontoDescuento());
			stmt.setDouble(2, detalleCaso.getMontoDescuento());
			logger.debug("Aporte Isapre: " + detalleCaso.getAporteIsapre());
			stmt.setDouble(3, detalleCaso.getAporteIsapre());
			logger.debug("Aporte Bienestar: "
					+ detalleCaso.getAporteBienestar());
			stmt.setDouble(4, detalleCaso.getAporteBienestar());
			logger.debug("Aporte Socio: " + detalleCaso.getAporteSocio());
			stmt.setDouble(5, detalleCaso.getAporteSocio());
			logger.debug("Documento: " + detalleCaso.getDocumento());
			stmt.setString(6, detalleCaso.getDocumento());
			logger.debug("Aporte Convenio: " + detalleCaso.getAporteConvenio());
			stmt.setDouble(7, detalleCaso.getAporteConvenio());
			logger.debug("Cantidad Ocurrencias: "
					+ detalleCaso.getCantidadOcurencias());
			stmt.setInt(8, detalleCaso.getCantidadOcurencias());
			logger.debug("Caso Id: " + casoId);
			stmt.setLong(9, casoId);
			logger.debug("Id Detalle(): " + detalleCaso.getIdDetalle());
			stmt.setInt(10, detalleCaso.getIdDetalle());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Elimina un Detalle asociado a un Caso
	 * 
	 * @param caso
	 *            Id y Detalle caso con los datos
	 */
	public void deleteDetalle(long casoId, DetalleCaso detalleCaso)
			throws Exception, BusinessException {
		if (detalleCaso == null) {
			throw new BusinessException("CCAF_BONIF_DETALLEINVALIDO",
					"Se ha intentado eliminar un Detalle Nulo");
		} else if (casoId == 0) {
			throw new BusinessException("CCAF_BONIF_DETALLEINVALIDO",
					"Se ha intentado eliminar un Detalle con el caso Id Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "DELETE FROM " + bonifDatabase
				+ ".BF09F1 WHERE CASID = ? AND DCAID = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, casoId);
			stmt.setInt(2, detalleCaso.getIdDetalle());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Elimina los Detalles asociados a un Caso
	 * 
	 * @param caso
	 *            Id
	 */
	public void deleteDetalles(long casoId) throws Exception, BusinessException {

		if (casoId == 0)
			throw new BusinessException("CCAF_BONIF_DETALLEINVALIDO",
					"Se ha intentado eliminar un Detalle con el caso Id Nulo");

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "DELETE FROM " + bonifDatabase + ".BF09F1 WHERE CASID = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, casoId);

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Obtiene una lista de Cuotas
	 * 
	 * @param casoId
	 *            y Cuota como Filtro
	 * @return ArrayList de Cuota
	 * @throws Exception
	 */
	public ArrayList getCuotasCaso(long casoId, Cuota cuotaFiltro)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		boolean filtrarPorCuotaNumero = false;
		boolean filtrarPorValor = false;
		boolean filtrarPorFechaVencimiento = false;
		boolean filtrarPorEstado = false;
		int contador = 0;
		String command;

		if (casoId == 0)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"Se ha Consultado Cuotas de un Caso Nulo");

		command = "SELECT CUONUM, CUOVALOR, CUOFECVCTO, CUOEST FROM "
				+ bonifDatabase + ".BF08F1 WHERE CASID = ?";

		if (cuotaFiltro != null) {
			if (cuotaFiltro.getCuotaNumero() > 0) {
				filtrarPorCuotaNumero = true;
				command = command + " AND CUONUM = ?";
			}
			if (cuotaFiltro.getValorCuota() > 0) {
				filtrarPorValor = true;
				command = command + " AND CUOVALOR = ?";
			}
			if (cuotaFiltro.getFechaVencimiento() != null) {
				filtrarPorFechaVencimiento = true;
				command = command + " AND CUOFECVCTO = ?";
			}
			if (cuotaFiltro.getCuotaEstado() != null
					&& cuotaFiltro.getCuotaEstado().length() > 0) {
				filtrarPorEstado = true;
				command = command + " AND CUOEST = ?";
			}
		}

		command = command + " ORDER BY CUONUM";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, casoId);
			contador++;
			if (filtrarPorCuotaNumero) {
				contador++;
				stmt.setInt(contador, cuotaFiltro.getCuotaNumero());
			}
			if (filtrarPorValor) {
				contador++;
				stmt.setDouble(contador, cuotaFiltro.getValorCuota());
			}
			if (filtrarPorFechaVencimiento) {
				contador++;
				stmt.setTimestamp(contador, new java.sql.Timestamp(cuotaFiltro
						.getFechaVencimiento().getTime()));
			}
			if (filtrarPorEstado) {
				contador++;
				stmt.setString(contador, cuotaFiltro.getCuotaEstado());
			}

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				Cuota cuota = new Cuota();
				cuota.setCuotaNumero(ors.getInt("CUONUM"));
				cuota.setValorCuota(ors.getDouble("CUOVALOR"));
				cuota.setFechaVencimiento(ors.getDate("CUOFECVCTO"));
				cuota.setCuotaEstado(ors.getString("CUOEST"));
				retorno.add(cuota);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return retorno;
	}

	/**
	 * Crea una cuota asociada a un Caso
	 * 
	 * @param caso
	 *            Id y cuota con los datos
	 */
	public void insertCuotaCaso(long casoId, Cuota cuota) throws Exception,
			BusinessException {
		if (cuota == null) {
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"Se ha intentado crear una Cuota Nula");
		} else if (casoId == 0) {
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"Se ha intentado crear una Cuota con el caso Id Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "INSERT INTO " + bonifDatabase + ".BF08F1 (CASID, CUONUM, "
				+ "CUOVALOR, CUOFECVCTO, CUOEST) VALUES(?, ?, ?, ?, ?)";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, casoId);
			stmt.setInt(2, cuota.getCuotaNumero());
			stmt.setDouble(3, cuota.getValorCuota());
			stmt.setTimestamp(4, new java.sql.Timestamp(cuota
					.getFechaVencimiento().getTime()));
			stmt.setString(5, cuota.getCuotaEstado());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Modifica una cuota asociada a un Caso
	 * 
	 * @param caso
	 *            Id y cuota con los datos
	 */
	public void updateCuotaCaso(long casoId, Cuota cuota) throws Exception,
			BusinessException {

		if (cuota == null) {
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"Se ha modificar una Cuota Nula");
		} else if (casoId == 0) {
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"Se ha intentado modificar una Cuota con el caso Id Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "UPDATE " + bonifDatabase + ".BF08F1 SET CUOEST = ? "
				+ "WHERE CASID = ? AND CUONUM = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, cuota.getCuotaEstado());
			stmt.setLong(2, casoId);
			stmt.setInt(3, cuota.getCuotaNumero());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Modifica todas cuota asociada a un Caso (usado en saldo deuda total)
	 * 
	 * @param caso
	 *            Id y cuota con los datos
	 */
	public void updateCuotasCaso(long casoId, Cuota cuota) throws Exception,
			BusinessException {

		if (cuota == null) {
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"Se ha modificar una Cuota Nula");
		} else if (casoId == 0) {
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"Se ha intentado modificar una Cuota con el caso Id Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "UPDATE " + bonifDatabase + ".BF08F1 SET CUOEST = ? "
				+ "WHERE CASID = ? and CUOEST = ? ";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, cuota.getCuotaEstado());
			stmt.setLong(2, casoId);
			stmt.setString(3, Cuota.STD_NO_DESCONTADA);

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Elimina las cuotas asociadas a un Caso
	 * 
	 * @param caso
	 *            Id
	 */
	public void deleteCuotasCaso(long casoId) throws Exception,
			BusinessException {
		if (casoId == 0) {
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"Se ha intentado eliminar Cuotas con el caso Id Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "DELETE FROM " + bonifDatabase + ".BF08F1 WHERE CASID = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, casoId);

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Crea un Caso
	 * 
	 * @param caso
	 * @return ID del Caso insertdo
	 */
	public long insertCaso(Caso caso) throws Exception, BusinessException {
		if (caso == null) {
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"Se ha intentado crear un Caso Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		long idDisponible = 0;

		String command;

		command = "INSERT INTO "
				+ bonifDatabase
				+ ".BF03F1 (CASID, SOCRUT, "
				+ "CARRUT, CONCOD, CASMONTO, CASDSCTO, CASAPOISA, CASAPOBIE, CASAPOSOC, "
				+ "CASFECING, CASFECEST, CASFECOCU, CASCUOCONV, CASCUOBIE, CASTIPO, CASEST, "
				+ "CASTIPDOC, CASNUMDOC, CASPRSTAMO, CASINDREE, CASINDDES, CASINDBON, "
				+ "CASINDPAG, CASBONO,CASINDPAN,CASABONO,CASNUMPTM,CASAPOCON,CASUSUARIO, "
				+ "CASINDPC, CASINDPCEG, CASINDPCIG) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {

			/*
			 * Obtiene un ID
			 */
			idDisponible = getID("BF03F1");
			if (idDisponible > 0) {
				caso.setCasoID(idDisponible);
			} else {
				throw new GeneralException(this, null, "CCAF_IDINVALIDO",
						"No se pudo obtener un ID");
			}

			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, caso.getCasoID());
			stmt.setString(2, caso.getRutSocio());
			stmt.setString(3, caso.getRutCarga());
			stmt.setLong(4, caso.getCodigoConvenio());
			stmt.setDouble(5, caso.getMonto());
			stmt.setDouble(6, caso.getMontoDescuento());
			stmt.setDouble(7, caso.getAporteIsapre());
			stmt.setDouble(8, caso.getAporteBienestar());
			stmt.setDouble(9, caso.getAporteSocio());
			stmt.setTimestamp(10, new java.sql.Timestamp(caso.getFechaIngreso()
					.getTime()));
			stmt.setTimestamp(11, new java.sql.Timestamp(caso.getFechaEstado()
					.getTime()));
			stmt
					.setTimestamp(
							12,
							(caso.getFechaDeOcurrencia() != null ? new java.sql.Timestamp(
									caso.getFechaDeOcurrencia().getTime())
									: null));
			stmt.setInt(13, caso.getCuotasConvenio());
			stmt.setInt(14, caso.getCuotasBienestar());
			stmt.setString(15, caso.getTipoCaso());
			stmt.setString(16, caso.getEstado());
			stmt.setString(17, caso.getTipoDocumento());
			stmt.setString(18, caso.getNumeroDocumento());
			stmt.setDouble(19, caso.getPrestamo());
			stmt.setString(20, caso.getIndicadorReembolso());
			stmt.setString(21, caso.getIndicadorDescontado());
			stmt.setString(22, caso.getIndicadorBonificacion());
			stmt.setString(23, caso.getIndicadorPago());
			stmt.setString(24, caso.getTipoBono());
			stmt.setString(25, caso.getIndicadorPagoAnticipado());
			stmt.setDouble(26, caso.getAbono());
			stmt.setInt(27, caso.getNumeroPrestamo());
			stmt.setDouble(28, caso.getAporteConvenio());
			stmt.setString(29, caso.getUsuario());
			stmt.setString(30, caso.getIndicadorPreCaso());
			stmt.setString(31, caso.getIndicadorPreCasoEgresoGenerado());
			stmt.setString(32, caso.getIndicadorPreCasoIngresoGenerado());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return idDisponible;
	}

	/**
	 * Actualiza un Caso
	 * 
	 * @param caso
	 */
	public void updateCaso(Caso caso) throws Exception, BusinessException {
		if (caso == null) {
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"Se ha intentado modificar un Caso Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "UPDATE "
				+ bonifDatabase
				+ ".BF03F1 SET SOCRUT = ?, CARRUT = ?, "
				+ "CONCOD = ?, CASMONTO = ?, CASDSCTO = ?, CASAPOISA = ?, CASAPOBIE = ?, CASAPOSOC = ? ,"
				+ "CASFECEST = ?, CASFECOCU = ?, CASCUOCONV = ?, CASCUOBIE = ?, CASTIPO = ?, "
				+ "CASEST = ?, CASTIPDOC = ?, CASNUMDOC = ?, CASPRSTAMO = ?, CASINDREE  = ?, "
				+ "CASINDDES = ?, CASINDBON = ?, CASINDPAG = ?, CASBONO = ?, CASINDPAN = ?, "
				+ "CASABONO = ?, CASNUMPTM = ?, CASAPOCON = ?, CASUSUARIO = ?, "
				+ "CASINDPC = ?, CASINDPCEG = ?, CASINDPCIG = ? "
				+ "WHERE CASID = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, caso.getRutSocio());
			stmt.setString(2, caso.getRutCarga());
			stmt.setLong(3, caso.getCodigoConvenio());
			stmt.setDouble(4, caso.getMonto());
			stmt.setDouble(5, caso.getMontoDescuento());
			stmt.setDouble(6, caso.getAporteIsapre());
			stmt.setDouble(7, caso.getAporteBienestar());
			stmt.setDouble(8, caso.getAporteSocio());
			stmt.setTimestamp(9, new java.sql.Timestamp(caso.getFechaEstado()
					.getTime()));
			stmt
					.setTimestamp(
							10,
							(caso.getFechaDeOcurrencia() != null ? new java.sql.Timestamp(
									caso.getFechaDeOcurrencia().getTime())
									: null));
			stmt.setInt(11, caso.getCuotasConvenio());
			stmt.setInt(12, caso.getCuotasBienestar());
			stmt.setString(13, caso.getTipoCaso());
			stmt.setString(14, caso.getEstado());
			stmt.setString(15, caso.getTipoDocumento());
			stmt.setString(16, caso.getNumeroDocumento());
			stmt.setDouble(17, caso.getPrestamo());
			stmt.setString(18, caso.getIndicadorReembolso());
			stmt.setString(19, caso.getIndicadorDescontado());
			stmt.setString(20, caso.getIndicadorBonificacion());
			stmt.setString(21, caso.getIndicadorPago());
			stmt.setString(22, caso.getTipoBono());
			stmt.setString(23, caso.getIndicadorPagoAnticipado());
			stmt.setDouble(24, caso.getAbono());
			stmt.setInt(25, caso.getNumeroPrestamo());
			stmt.setDouble(26, caso.getAporteConvenio());
			stmt.setString(27, caso.getUsuario());
			stmt.setString(28, caso.getIndicadorPreCaso());
			stmt.setString(29, caso.getIndicadorPreCasoEgresoGenerado());
			stmt.setString(30, caso.getIndicadorPreCasoIngresoGenerado());

			stmt.setLong(31, caso.getCasoID());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Obtiene informacion de un Caso
	 * 
	 * @param codigo
	 *            de caso (caso ID)
	 * @return CasoVO
	 * @throws Exception
	 */
	public CasoVO getCasoVO(long casoId) throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		logger.debug("****Caso Id: " + casoId);

		command = "SELECT  A.CASID, A.SOCRUT, B.DV SOCDV, B.FNNOMBRES SOCNOMBRE, B.FNAPELLPAT SOCAPEPAT, B.FNAPELLMAT SOCAPEMAT, B.FNFEC1 SOCFECING, " //antes de req 6083 se leía: B.FNFECHING SOCFECING, "
				+ "A.CARRUT, A.CONCOD, C.CONNOM, CONMAXCUO, A.CASMONTO, A.CASDSCTO, A.CASAPOISA, A.CASAPOBIE, A.CASAPOSOC, "
				+ "A.CASFECING, A.CASFECEST, A.CASFECOCU, A.CASCUOCONV, A.CASCUOBIE, A.CASTIPO, A.CASEST, "
				+ "A.CASTIPDOC, A.CASNUMDOC, A.CASPRSTAMO, A.CASINDREE, A.CASINDDES, A.CASINDBON, A.CASINDPAG, "
				+ "A.CASBONO, A.CASINDPAN, A.CASABONO, A.CASNUMPTM, A.CASAPOCON, A.CASUSUARIO, "
				+ "A.CASINDPC, A.CASINDPCEG, A.CASINDPCIG "
				+ "FROM "
				+ bonifDatabase
				+ ".BF03F1 A, "
				+ bonifDatabase
				+ ".FUNCIONARIOS B, "
				+ bonifDatabase
				+ ".BF05F1 C WHERE A.SOCRUT = B.RUT "
				+ "AND A.CONCOD = C.CONCOD AND A.CASID = ?";

		CasoVO casoVO = new CasoVO();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, casoId);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			if (ors.next()) {
				casoVO.setCasoID(ors.getLong("CASID"));
				casoVO.setRutSocio(ors.getString("SOCRUT"));
				casoVO.setDvRutSocio(ors.getString("SOCDV"));
				casoVO.setNombreSocio(ors.getString("SOCNOMBRE") + " "
						+ ors.getString("SOCAPEPAT") + " "
						+ ors.getString("SOCAPEMAT"));
				casoVO.setFecIngSocio(ors.getDate("SOCFECING"));
				casoVO.setRutCarga(ors.getString("CARRUT"));
				casoVO.setCodigoConvenio(ors.getLong("CONCOD"));
				casoVO.setNombreConvenio(ors.getString("CONNOM"));
				casoVO.setNumeroMaximoCuotas(ors.getInt("CONMAXCUO"));
				casoVO.setMonto(ors.getDouble("CASMONTO"));
				casoVO.setMontoDescuento(ors.getDouble("CASDSCTO"));
				casoVO.setAporteIsapre(ors.getDouble("CASAPOISA"));
				casoVO.setAporteBienestar(ors.getDouble("CASAPOBIE"));
				casoVO.setAporteSocio(ors.getDouble("CASAPOSOC"));
				casoVO.setFechaIngreso(ors.getDate("CASFECING"));
				casoVO.setFechaEstado(ors.getDate("CASFECEST"));
				casoVO.setFechaDeOcurrencia(ors.getDate("CASFECOCU"));
				casoVO.setCuotasConvenio(ors.getInt("CASCUOCONV"));
				casoVO.setCuotasBienestar(ors.getInt("CASCUOBIE"));
				casoVO.setTipoCaso(ors.getString("CASTIPO"));
				casoVO.setEstado(ors.getString("CASEST"));
				casoVO.setTipoDocumento(ors.getString("CASTIPDOC"));
				casoVO.setNumeroDocumento(ors.getString("CASNUMDOC"));
				casoVO.setPrestamo(ors.getDouble("CASPRSTAMO"));
				casoVO.setIndicadorReembolso(ors.getString("CASINDREE"));
				casoVO.setIndicadorDescontado(ors.getString("CASINDDES"));
				casoVO.setIndicadorBonificacion(ors.getString("CASINDBON"));
				casoVO.setIndicadorPago(ors.getString("CASINDPAG"));
				casoVO.setTipoBono(ors.getString("CASBONO"));
				casoVO.setIndicadorPagoAnticipado(ors.getString("CASINDPAN"));
				casoVO.setAbono(ors.getDouble("CASABONO"));
				casoVO.setNumeroPrestamo(ors.getInt("CASNUMPTM"));
				casoVO.setAporteConvenio(ors.getDouble("CASAPOCON"));
				casoVO.setUsuario(ors.getString("CASUSUARIO"));
				casoVO.setIndicadorPreCaso(ors.getString("CASINDPC"));
				casoVO.setIndicadorPreCasoEgresoGenerado(ors
						.getString("CASINDPCEG"));
				casoVO.setIndicadorPreCasoIngresoGenerado(ors
						.getString("CASINDPCIG"));

				// Verifica si tiene una carga asociada
				if (casoVO.getRutCarga() != null
						&& casoVO.getRutCarga().length() > 0) {
					Carga carga = getCarga(casoVO.getRutCarga(), casoVO
							.getRutSocio());
					casoVO.setNombreCarga(carga.getNombreCarga() + " "
							+ carga.getApePatCarga() + " "
							+ carga.getApeMatCarga());
					casoVO.setDvRutCarga(carga.getDvCarga());
				}

				// Información del Vale asociado (en caso de tener)
				Vale valeFiltro = new Vale();
				valeFiltro.setCaso_id(casoVO.getCasoID());
				ArrayList retorno = getVales(valeFiltro, null, 0);
				if (retorno.size() == 1) {
					Vale vale = (Vale) retorno.get(0);
					casoVO.setVale(vale);
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return casoVO;
	}

	/**
	 * Obtiene una lista de casos segun los parametros
	 * 
	 * @param Caso
	 * @return ArrayList de CasoVO
	 * @throws Exception
	 */
	public ArrayList getCasos(Caso caso, long codigoCobertura)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		boolean filtrarPorCasoId = false;
		boolean filtrarPorRutSocio = false;
		boolean filtrarPorRutCarga = false;
		boolean filtrarPorFechaIngreso = false;
		boolean filtrarPorFechaDeOcurrencia = false;
		boolean filtrarPorTipoCaso = false;
		boolean filtrarPorEstado = false;
		boolean filtrarPorIndicadorBonificacion = false;
		boolean filtrarPorIndicadorReembolso = false;
		boolean filtrarPorIndicadorDescontado = false;
		boolean filtrarPorIndicadorPago = false;
		boolean filtrarPorCodigoCobertura = false;
		boolean filtrarPorTipoBono = false;
		boolean filtrarPorCodigoConvenio = false;
		boolean filtrarPorIndicadorPreCaso = false;
		boolean filtrarPorIndicadorPreCasoEgresoGenerado = false;
		boolean filtrarPorIndicadorPreCasoIngresoGenerado = false;
		// NUEVO
		//boolean filtrarUsuarioRegistradoSocio = false;

		int contador = 0;

		String command;

		command = "SELECT CASID, A.SOCRUT, DV SOCDV, FNNOMBRES SOCNOMBRE, FNAPELLPAT  SOCAPEPAT, FNAPELLMAT SOCAPEMAT, FNFEC1 SOCFECING, " //antes de req 6083 se leía: FNFECHING SOCFECING, "
				+ "CARRUT, A.CONCOD, CONNOM, CONMAXCUO, CASMONTO, CASDSCTO, CASAPOISA, CASAPOBIE, CASAPOSOC, CASFECING,"
				+ "CASFECEST, CASFECOCU, CASCUOCONV, CASCUOBIE, CASTIPO, CASEST, CASTIPDOC, CASNUMDOC,"
				+ "CASPRSTAMO, CASINDREE, CASINDDES, CASINDBON, CASINDPAG, CASBONO, CASINDPAN,CASABONO, "
				+ "CASNUMPTM, CASAPOCON, CASUSUARIO, CASINDPC, CASINDPCEG, CASINDPCIG, "
				+ "D.TCOCOD, TCODESCRIP FROM "
				+ bonifDatabase
				+ ".BF03F1 A, "
				+ bonifDatabase
				+ ".funcionarios B, "
				+ bonifDatabase
				+ ".BF05F1 C, "
				+ bonifDatabase
				+ ".BF14F1 D "
				+ "WHERE "
				+ "A.SOCRUT = B.RUT "
				+ "AND "
				+ "A.CONCOD = C.CONCOD "
				+ "AND " + "C.TCOCOD = D.TCOCOD " + "AND CASEST <> ? ";

		// reviso si viene consulta por cobertura
		if (codigoCobertura > 0) {
			command = command + " AND CASID IN (SELECT DISTINCT(CASID) FROM "
					+ bonifDatabase + ".BF17F1 WHERE COBCOD = ?)";
			filtrarPorCodigoCobertura = true;
		} else {
			/*
			 * Reviso si vienen filtros
			 */
			if (caso != null) {
				if (caso.getCasoID() > 0) {
					logger.debug("CasoID: " + caso.getCasoID());
					command = command + " AND CASID = ?";
					filtrarPorCasoId = true;
				}
				if (caso.getRutSocio() != null
						&& caso.getRutSocio().length() > 0) {
					logger.debug("Rut Socio: " + caso.getRutSocio());
					command = command + " AND A.SOCRUT LIKE ?";
					// if(codUsuarioRegistrado == 5){ //NUEVO, si es socio debo
					// añadir lo siguiente
					// command = command + " AND A.CASUSUARIO LIKE ?";
					// filtrarUsuarioRegistradoSocio = true;
					// }
					filtrarPorRutSocio = true;
				}
				if (caso.getRutCarga() != null
						&& caso.getRutCarga().length() > 0) {
					logger.debug("Rut Carga: " + caso.getRutCarga());
					command = command + " AND CARRUT = ?";
					filtrarPorRutCarga = true;
				}
				if (caso.getCodigoConvenio() > 0) {
					logger
							.debug("Codigo Convenio: "
									+ caso.getCodigoConvenio());
					command = command + " AND A.CONCOD = ?";
					filtrarPorCodigoConvenio = true;
				}
				if (caso.getFechaIngreso() != null) {
					logger.debug("Fecha Ingreso: " + caso.getFechaIngreso());
					command = command + " AND CASFECING = ?";
					filtrarPorFechaIngreso = true;
				}
				if (caso.getFechaDeOcurrencia() != null) {
					logger.debug("Fecha De Ocurrencia: "
							+ caso.getFechaDeOcurrencia());
					command = command + " AND CASFECOCU = ?";
					filtrarPorFechaDeOcurrencia = true;
				}
				if (caso.getTipoCaso() != null
						&& caso.getTipoCaso().length() > 0) {
					logger.debug("Tipo Caso: " + caso.getTipoCaso());
					command = command + " AND CASTIPO = ?";
					filtrarPorTipoCaso = true;
				}
				if (caso.getEstado() != null && caso.getEstado().length() > 0) {
					logger.debug("Estado: " + caso.getEstado());
					command = command + " AND CASEST = ?";
					filtrarPorEstado = true;
				}
				if (caso.getIndicadorBonificacion() != null
						&& caso.getIndicadorBonificacion().length() > 0) {
					logger.debug("Indicador Bonificacion: "
							+ caso.getIndicadorBonificacion());
					command = command + " AND CASINDBON = ?";
					filtrarPorIndicadorBonificacion = true;
				}
				if (caso.getIndicadorReembolso() != null
						&& caso.getIndicadorReembolso().length() > 0) {
					logger.debug("Indicador Reembolso: "
							+ caso.getIndicadorReembolso());
					command = command + " AND CASINDREE = ?";
					filtrarPorIndicadorReembolso = true;
				}
				if (caso.getIndicadorDescontado() != null
						&& caso.getIndicadorDescontado().length() > 0) {
					logger.debug("Indicador Descontado: "
							+ caso.getIndicadorDescontado());
					command = command + " AND CASINDDES = ?";
					filtrarPorIndicadorDescontado = true;
				}
				if (caso.getIndicadorPago() != null
						&& caso.getIndicadorPago().length() > 0) {
					logger.debug("Indicador Pago: " + caso.getIndicadorPago());
					command = command + " AND CASINDPAG = ?";
					filtrarPorIndicadorPago = true;
				}
				if (caso.getTipoBono() != null
						&& caso.getTipoBono().length() > 0) {
					logger.debug("Tipo Bono: " + caso.getTipoBono());
					command = command + " AND CASBONO = ?";
					filtrarPorTipoBono = true;
				}
				if (caso.getIndicadorPreCaso() != null
						&& caso.getIndicadorPreCaso().length() > 0) {
					logger.debug("Indicador Pre-Caso: "
							+ caso.getIndicadorPreCaso());
					command = command + " AND CASINDPC = ?";
					filtrarPorIndicadorPreCaso = true;
				}
				if (caso.getIndicadorPreCasoEgresoGenerado() != null
						&& caso.getIndicadorPreCasoEgresoGenerado().length() > 0) {
					logger.debug("Indicador Pre-Caso Egreso: "
							+ caso.getIndicadorPreCasoEgresoGenerado());
					command = command + " AND CASINDPCEG = ?";
					filtrarPorIndicadorPreCasoEgresoGenerado = true;
				}
				if (caso.getIndicadorPreCasoIngresoGenerado() != null
						&& caso.getIndicadorPreCasoIngresoGenerado().length() > 0) {
					logger.debug("Indicador Pre-Caso Ingreso: "
							+ caso.getIndicadorPreCasoIngresoGenerado());
					command = command + " AND CASINDPCIG = ?";
					filtrarPorIndicadorPreCasoIngresoGenerado = true;
				}
			}
		}

		command = command + " ORDER BY CASEST ASC,CASID DESC";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			contador++;
			stmt.setString(contador, Caso.STD_PRECASO);

			if (filtrarPorCodigoCobertura) {
				contador++;
				stmt.setLong(contador, codigoCobertura);
			} else {
				if (filtrarPorCasoId) {
					contador++;
					stmt.setLong(contador, caso.getCasoID());
				}
				if (filtrarPorRutSocio) {
					contador++;
					stmt.setString(contador, '%' + caso.getRutSocio() + '%');
					// if(filtrarUsuarioRegistradoSocio){ //NUEVO (pedido 5 de
					// junio 2007),solo si el usuario es SOCIO :D
					// contador++;
					// stmt.setString(contador,'%' + caso.getRutSocio() + '%');
					// //NUEVO(pedido 24 mayo 2007) ademas filtramos por
					// BFDTA.CASUSUARIO
					// }
				}
				if (filtrarPorRutCarga) {
					contador++;
					stmt.setString(contador, caso.getRutCarga());
				}
				if (filtrarPorCodigoConvenio) {
					contador++;
					stmt.setLong(contador, caso.getCodigoConvenio());
				}
				if (filtrarPorFechaIngreso) {
					contador++;
					stmt.setTimestamp(contador, new java.sql.Timestamp(caso
							.getFechaIngreso().getTime()));
				}
				if (filtrarPorFechaDeOcurrencia) {
					contador++;
					stmt.setTimestamp(contador, new java.sql.Timestamp(caso
							.getFechaDeOcurrencia().getTime()));
				}
				if (filtrarPorTipoCaso) {
					contador++;
					stmt.setString(contador, caso.getTipoCaso());
				}
				if (filtrarPorEstado) {
					contador++;
					stmt.setString(contador, caso.getEstado());
				}
				if (filtrarPorIndicadorBonificacion) {
					contador++;
					stmt.setString(contador, caso.getIndicadorBonificacion());
				}
				if (filtrarPorIndicadorReembolso) {
					contador++;
					stmt.setString(contador, caso.getIndicadorReembolso());
				}
				if (filtrarPorIndicadorDescontado) {
					contador++;
					stmt.setString(contador, caso.getIndicadorDescontado());
				}
				if (filtrarPorIndicadorPago) {
					contador++;
					stmt.setString(contador, caso.getIndicadorPago());
				}
				if (filtrarPorTipoBono) {
					contador++;
					stmt.setString(contador, caso.getTipoBono());
				}
				if (filtrarPorIndicadorPreCaso) {
					contador++;
					stmt.setString(contador, caso.getIndicadorPreCaso());
				}
				if (filtrarPorIndicadorPreCasoEgresoGenerado) {
					contador++;
					stmt.setString(contador, caso
							.getIndicadorPreCasoEgresoGenerado());
				}
				if (filtrarPorIndicadorPreCasoIngresoGenerado) {
					contador++;
					stmt.setString(contador, caso
							.getIndicadorPreCasoIngresoGenerado());
				}
			}

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				CasoVO cas = new CasoVO();
				cas.setCasoID(ors.getLong("CASID"));
				cas.setRutSocio(ors.getString("SOCRUT"));
				cas.setDvRutSocio(ors.getString("SOCDV"));
				cas.setNombreSocio(ors.getString("SOCNOMBRE") + " "
						+ ors.getString("SOCAPEPAT") + " "
						+ ors.getString("SOCAPEMAT"));
				cas.setFecIngSocio(ors.getDate("SOCFECING"));
				cas.setRutCarga(ors.getString("CARRUT"));
				cas.setCodigoConvenio(ors.getLong("CONCOD"));
				cas.setNombreConvenio(ors.getString("CONNOM"));
				cas.setNumeroMaximoCuotas(ors.getInt("CONMAXCUO"));
				cas.setMonto(ors.getDouble("CASMONTO"));
				cas.setMontoDescuento(ors.getDouble("CASDSCTO"));
				cas.setAporteIsapre(ors.getDouble("CASAPOISA"));
				cas.setAporteBienestar(ors.getDouble("CASAPOBIE"));
				cas.setAporteSocio(ors.getDouble("CASAPOSOC"));
				cas.setFechaIngreso(ors.getDate("CASFECING"));
				cas.setFechaEstado(ors.getDate("CASFECEST"));
				cas.setFechaDeOcurrencia(ors.getDate("CASFECOCU"));
				cas.setCuotasConvenio(ors.getInt("CASCUOCONV"));
				cas.setCuotasBienestar(ors.getInt("CASCUOBIE"));
				cas.setTipoCaso(ors.getString("CASTIPO"));
				cas.setEstado(ors.getString("CASEST"));
				cas.setTipoDocumento(ors.getString("CASTIPDOC"));
				cas.setNumeroDocumento(ors.getString("CASNUMDOC"));
				cas.setPrestamo(ors.getDouble("CASPRSTAMO"));
				cas.setIndicadorReembolso(ors.getString("CASINDREE"));
				cas.setIndicadorDescontado(ors.getString("CASINDDES"));
				cas.setIndicadorBonificacion(ors.getString("CASINDBON"));
				cas.setIndicadorPago(ors.getString("CASINDPAG"));
				cas.setTipoBono(ors.getString("CASBONO"));
				cas.setIndicadorPagoAnticipado(ors.getString("CASINDPAN"));
				cas.setAbono(ors.getDouble("CASABONO"));
				cas.setNumeroPrestamo(ors.getInt("CASNUMPTM"));
				cas.setAporteConvenio(ors.getDouble("CASAPOCON"));
				cas.setUsuario(ors.getString("CASUSUARIO"));
				cas.setIndicadorPreCaso(ors.getString("CASINDPC"));
				cas.setIndicadorPreCasoEgresoGenerado(ors
						.getString("CASINDPCEG"));
				cas.setIndicadorPreCasoIngresoGenerado(ors
						.getString("CASINDPCIG"));
				cas.setCodigoConcepto(ors.getLong("TCOCOD"));
				cas.setDescripcionConcepto(ors.getString("TCODESCRIP"));
				retorno.add(cas);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Calcula el aporte de Bienestar en otros casos segun los parametros
	 * pasados siempre para una cobertura especifica.
	 * 
	 * @param rutSocio
	 * @param rutCarga
	 * @param tipoTope
	 * @param codigoCobertura
	 * @param fechaInicio
	 * @param fechaFin
	 * @return double (aporte de bienestar en otros casos dentro del periodo
	 *         consultado)
	 * @throws Exception
	 */
	public double calculaAporteBienestar(String rutSocio, String rutCarga,
			String tipoTope, long codigoCobertura, Date fechaInicio,
			Date fechaFin, long casoId) throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		boolean filtrarPorCargaRut = false;
		boolean filtrarPorCasoId = false;
		double aporteBienestar = -1;

		String command;

		if (rutSocio == null || rutSocio.length() == 0)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"Rut Socio incorrecto");
		if (codigoCobertura < 1)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"Codigo de Cobertura incorrecto");
		if (fechaFin == null)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"Fecha Fin incorrecta");
		if (fechaFin == null)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"Fecha Fin incorrecta");

		// anual o mensual depende de los parametros de fecha

		// por defecto es anual o mensual por grupo
		command = "SELECT SUM(ABIMONTO) \"TOTAPOBIE\" FROM " + bonifDatabase
				+ ".BF03F1 A, " + bonifDatabase
				+ ".BF17F1 B WHERE CASEST IN (?, ?) AND CASINDBON = ? "
				+ "AND CASFECOCU >= ? AND CASFECOCU < ? AND A.CASID = B.CASID "
				+ "AND COBCOD = ? AND SOCRUT = ?";

		/*
		 * Si está rebonificando el mismo caso, no lo debe considerar en los
		 * aportes de bienestar realizados anteriormente
		 */
		if (casoId > 0) {
			command = command + " AND A.CASID <> ?";
			filtrarPorCasoId = true;
		}

		// Tope anual o mensual beneficiario
		if (tipoTope.equals(Cobertura.TOPE_ANUALBENEFICIARIO)) {
			// anual beneficiario (carga)
			if (rutCarga != null && rutCarga.length() > 0) {
				command = command + " AND CARRUT = ?";
				filtrarPorCargaRut = true;
			}
			// anual beneficiario (socio)
			else
				command = command + " AND CARRUT IS NULL";
		}

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, Caso.STD_ACTIVO);
			stmt.setString(2, Caso.STD_CERRADO);
			stmt.setString(3, Caso.ESTADOINDICADOR_SI);
			stmt.setTimestamp(4, new java.sql.Timestamp(fechaInicio.getTime()));
			stmt.setTimestamp(5, new java.sql.Timestamp(fechaFin.getTime()));
			stmt.setLong(6, codigoCobertura);
			stmt.setString(7, rutSocio);

			int contador = 7;
			if (filtrarPorCasoId) {
				contador++;
				stmt.setLong(contador, casoId);
			}

			if (filtrarPorCargaRut) {
				contador++;
				stmt.setString(contador, rutCarga);
			}

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				aporteBienestar = ors.getDouble("TOTAPOBIE");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return aporteBienestar;
	}

	/**
	 * Devuelve una lista con los aportes realzados por Bienestar y aportes de
	 * Socio, etc. para los productos de un convenio en particular
	 * 
	 * @param codigoConvenio
	 * @param fechaInicio
	 * @param fechaFin
	 * @param AporteBienestarVO
	 *            con filtros
	 * @return ArrayList de DetalleAporteBienestarVO
	 * @throws Exception
	 */
	public ArrayList getResumenAportesBienestar(Date fechaInicio,
			Date fechaFin, DetalleAporteBienestarVO resumenFiltro)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		boolean filtrarPorCodigoCobertura = false;
		boolean filtrarPorNombreCobertura = false;
		int contador = 0;

		String command;

		if (fechaFin == null)
			throw new BusinessException("CCAF_BONIF_REPORTE",
					"Fecha Fin incorrecta");
		if (fechaFin == null)
			throw new BusinessException("CCAF_BONIF_REPORTE",
					"Fecha Fin incorrecta");

		// anual o mensual depende de los parametros de fecha

		// por defecto es anual o mensual por grupo

		command = "SELECT  A.COBCOD, B.COBGLS, SUM(ABIMONTO) \"MONTO\" "
				+ "FROM " + bonifDatabase + ".BF17F1 A, " + bonifDatabase
				+ ".BF04F1 B, " + bonifDatabase + ".BF03F1 C " + "WHERE "
				+ "A.COBCOD = B.COBCOD AND " + "A.CASID = C.CASID AND "
				+ "CASFECOCU >= ? AND " + "CASFECOCU < ?";

		// Reviso si vienen filtros
		if (resumenFiltro != null) {
			if (resumenFiltro.getCodigoCobertura() > 0) {
				command = command + " AND A.COBCOD = ?";
				filtrarPorCodigoCobertura = true;
			}
			if (resumenFiltro.getDescripcion() != null
					&& resumenFiltro.getDescripcion().length() > 0) {
				command = command + " AND UCASE(B.COBGLS) LIKE ?";
				filtrarPorNombreCobertura = true;
			}
		}

		command = command + " GROUP BY A.COBCOD, B.COBGLS ORDER BY A.COBCOD";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			contador++;
			stmt.setTimestamp(contador, new java.sql.Timestamp(fechaInicio
					.getTime()));
			contador++;
			stmt.setTimestamp(contador, new java.sql.Timestamp(fechaFin
					.getTime()));
			if (filtrarPorCodigoCobertura) {
				contador++;
				stmt.setLong(contador, resumenFiltro.getCodigoCobertura());
				logger.debug("Codigo Cobertura: "
						+ resumenFiltro.getCodigoCobertura());
			}
			if (filtrarPorNombreCobertura) {
				contador++;
				stmt.setString(contador, '%' + resumenFiltro.getDescripcion()
						.toUpperCase() + '%');
				logger.debug("Nombre Cobertura: "
						+ resumenFiltro.getDescripcion());
			}

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				DetalleAporteBienestarVO resumen = new DetalleAporteBienestarVO();
				resumen.setCodigoCobertura(ors.getLong("COBCOD"));
				resumen.setDescripcion(ors.getString("COBGLS"));
				resumen.setMonto(ors.getDouble("MONTO"));

				retorno.add(resumen);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Devuelve una lista con los aportes realzados por Bienestar y aportes de
	 * Socio, etc. para los productos de un convenio en particular
	 * 
	 * @param codigoConvenio
	 * @param fechaInicio
	 * @param fechaFin
	 * @return ArrayList de ResumenMovimientosConvenioVO
	 * @throws Exception
	 */
	public ArrayList movimientosConvenio(long codigoConvenio, Date fechaInicio,
			Date fechaFin, ResumenMovimientosConvenioVO resumenFiltro)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		boolean filtrarPorCodigoProducto = false;
		boolean filtrarPorNombreProducto = false;
		int contador = 0;

		String command;

		if (codigoConvenio < 1)
			throw new BusinessException("CCAF_BONIF_CONVENIOINVALIDO",
					"Codigo de Convenio incorrecto");
		if (fechaFin == null)
			throw new BusinessException("CCAF_BONIF_CONVENIOINVALIDO",
					"Fecha Fin incorrecta");
		if (fechaFin == null)
			throw new BusinessException("CCAF_BONIF_CONVENIOINVALIDO",
					"Fecha Fin incorrecta");

		// anual o mensual depende de los parametros de fecha

		// por defecto es anual o mensual por grupo

		command = "SELECT B.COBCOD, C.COBGLS, SUM(ABIMONTO) \"TOTAPOBIE\" "
				+ "FROM " + bonifDatabase + ".BF03F1 A, " + bonifDatabase
				+ ".BF17F1 B, " + bonifDatabase + ".BF04F1 C " + "WHERE "
				+ "CASEST IN (?, ?) AND " + "CASFECOCU >= ? AND "
				+ "CASFECOCU < ? AND " + "CASINDBON = ? AND "
				+ "A.CONCOD = ? AND " + "A.CASID = B.CASID AND "
				+ "B.COBCOD = C.COBCOD";

		// Reviso si vienen filtros
		if (resumenFiltro != null) {
			if (resumenFiltro.getCodigoProducto() > 0) {
				command = command + " AND B.COBCOD = ?";
				filtrarPorCodigoProducto = true;
			}
			if (resumenFiltro.getNombreProducto() != null
					&& resumenFiltro.getNombreProducto().length() > 0) {
				command = command + " AND UCASE(C.COBGLS) LIKE ?";
				filtrarPorNombreProducto = true;
			}
		}

		command = command + " GROUP BY B.COBCOD, C.COBGLS ORDER BY B.COBCOD";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, Caso.STD_ACTIVO);
			stmt.setString(2, Caso.STD_CERRADO);
			stmt.setTimestamp(3, new java.sql.Timestamp(fechaInicio.getTime()));
			stmt.setTimestamp(4, new java.sql.Timestamp(fechaFin.getTime()));
			stmt.setString(5, Caso.ESTADOINDICADOR_SI);
			logger.debug("Codigo Convenio: " + codigoConvenio);
			stmt.setLong(6, codigoConvenio);
			contador = 6;
			if (filtrarPorCodigoProducto) {
				contador++;
				stmt.setLong(contador, resumenFiltro.getCodigoProducto());
				logger.debug("Codigo Producto: "
						+ resumenFiltro.getCodigoProducto());
			}
			if (filtrarPorNombreProducto) {
				contador++;
				stmt.setString(contador, '%' + resumenFiltro
						.getNombreProducto().toUpperCase() + '%');
				logger.debug("Nombre roducto: "
						+ resumenFiltro.getNombreProducto());
			}

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				ResumenMovimientosConvenioVO resumen = new ResumenMovimientosConvenioVO();
				resumen.setCodigoProducto(ors.getLong("COBCOD"));
				resumen.setNombreProducto(ors.getString("COBGLS"));
				resumen.setAporteBienestarAcumulado(ors.getDouble("TOTAPOBIE"));

				retorno.add(resumen);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene una lista con los Casos Por Reembolsar segun las fecha indicadas
	 * 
	 * @param ParamOperacionesVO
	 * @return ArrayList de ReembolsoVO
	 * @throws Exception
	 */
	public ArrayList getCasosPorReembolsar(ParamOperacionesVO param, String rut)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		boolean filtrarPorRut = false;

		String command;

		command = "SELECT "
				+ "CASID, CASEST, CASFECEST, "
				+ "A.SOCRUT, b.DV SOCDV, b.FNNOMBRES SOCNOMBRE, "
				+ "b.FNAPELLPAT SOCAPEPAT,b.FNAPELLMAT SOCAPEMAT,b.FNLPAGO SOCOFICINA, "
				+ "CASAPOBIE, CASFECOCU, "
				+ "B.FNTXA7 as CORREO, B.FNMAR6 as TIPO_CUENTA, B.FNCTACTE AS CUENTA, B.FNBANCO AS BANCO, B.FNCOBR as TIPOCOBRO "
				+ "FROM " + bonifDatabase
				+ ".BF03F1 A, " + bonifDatabase + ".funcionarios B " + "WHERE "
				+ "A.SOCRUT = B.RUT " + "AND " + "CASEST = ? " + "AND "
				+ "CASINDBON = ? " + "AND " + "CASINDREE = ? " + "AND "
				+ "CASTIPO = ? " + "AND " + "CASFECOCU <= ?";

		if (rut != null && rut.length() > 0) {
			filtrarPorRut = true;
			command += " AND A.SOCRUT = ?";
		}

		command += " ORDER BY b.FNLPAGO, A.SOCRUT";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, Caso.STD_ACTIVO);
			stmt.setString(2, Caso.ESTADOINDICADOR_SI);
			stmt.setString(3, Caso.ESTADOINDICADOR_NO);
			stmt.setString(4, Caso.TIPO_REEMBOLSO);
			stmt.setTimestamp(5, new java.sql.Timestamp(param.getFechaFin()
					.getTime()));
			if (filtrarPorRut)
				stmt.setString(6, rut);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				ReembolsoVO reembolso = new ReembolsoVO();
				reembolso.setCasoId(ors.getLong("CASID"));
				reembolso.setEstado(ors.getString("CASEST"));
				reembolso.setFechaEstado(ors.getDate("CASFECEST"));
				reembolso.setRut(ors.getString("SOCRUT"));
				reembolso.setDv(ors.getString("SOCDV"));
				reembolso.setNombre(ors.getString("SOCNOMBRE"));
				reembolso.setApellidoPaterno(ors.getString("SOCAPEPAT"));
				reembolso.setApellidoMaterno(ors.getString("SOCAPEMAT"));
				reembolso.setOficina(ors.getString("SOCOFICINA"));
				reembolso.setMontoReembolso(ors.getDouble("CASAPOBIE"));
				reembolso.setFechaOcurrencia(ors.getDate("CASFECOCU"));
				//Si tipo de cobro es T (transferencia), entonces recupero los datos de la cuenta, en caso contrario los ignoro
				String tipoCobro = ors.getString("TIPOCOBRO");
				if(tipoCobro != null && "T".equalsIgnoreCase(tipoCobro)) {
					reembolso.setBanco(ors.getString("BANCO"));
					reembolso.setTipoCuenta(ors.getString("TIPO_CUENTA"));
					reembolso.setCorreo(ors.getString("CORREO"));
					reembolso.setCuenta(ors.getString("CUENTA"));	
				}				
				retorno.add(reembolso);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene una lista con los Casos Por Descontar segun las fecha indicadas
	 * 
	 * @param ParamOperacionesVO
	 * @return ArrayList de DescuentosVO
	 * @throws Exception
	 */
	public ArrayList getCasosPorDescontar(ParamOperacionesVO param)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		//int contador = 0;

		String command;

		command = "SELECT "
				+ "CASID, CASEST, CASFECEST, "
				+ "CASINDDES, A.SOCRUT, b.DV SOCDV, "
				+ "b.FNNOMBRES SOCNOMBRE, b.FNAPELLPAT SOCAPEPAT, b.FNAPELLMAT SOCAPEMAT, "
				+ "b.FNLPAGO SOCOFICINA, CASAPOSOC, CASCUOCONV, "
				+ "CASCUOBIE, CASBONO, CASAPOCON, CASFECOCU " + "FROM "
				+ bonifDatabase + ".BF03F1 A, " + bonifDatabase
				+ ".FUNCIONARIOS B " + "WHERE " + "A.SOCRUT = B.RUT " + "AND "
				+ "CASTIPO = ? " + "AND " + "CASEST = ? " + "AND "
				+ "CASINDBON = ? " + "AND " + "CASINDDES <> ? " + "AND "
				+ "CASFECOCU <= ? " + "ORDER BY "
				+ "FNLPAGO, A.SOCRUT, CASFECOCU";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, Caso.TIPO_DESCUENTO);
			stmt.setString(2, Caso.STD_ACTIVO);
			stmt.setString(3, Caso.ESTADOINDICADOR_SI);
			stmt.setString(4, Caso.ESTADOINDICADOR_SI);
			stmt.setTimestamp(5, new java.sql.Timestamp(param.getFechaFin()
					.getTime()));

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				DescuentosVO descuento = new DescuentosVO();
				descuento.setCasoId(ors.getLong("CASID"));
				descuento.setEstado(ors.getString("CASEST"));
				descuento.setFechaEstado(ors.getDate("CASFECEST"));
				descuento.setIndicadorDescuento(ors.getString("CASINDDES"));
				descuento.setRut(ors.getString("SOCRUT"));
				descuento.setDv(ors.getString("SOCDV"));
				descuento.setNombre(ors.getString("SOCNOMBRE"));
				descuento.setApellidoPaterno(ors.getString("SOCAPEPAT"));
				descuento.setApellidoMaterno(ors.getString("SOCAPEMAT"));
				descuento.setOficina(ors.getString("SOCOFICINA"));
				descuento.setMontoDescuento(ors.getDouble("CASAPOSOC"));
				descuento.setNumeroCuotasConvenio(ors.getInt("CASCUOCONV"));
				descuento.setNumeroCuotasBienestar(ors.getInt("CASCUOBIE"));
				descuento.setTipoBono(ors.getString("CASBONO"));
				descuento.setAporteConvenio(ors.getDouble("CASAPOCON"));
				descuento.setFechaOcurrencia(ors.getDate("CASFECOCU"));

				retorno.add(descuento);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene una lista con el Caso Por Descontar (saldo deuda anticipada /
	 * finiquito) segun las fecha indicadas
	 * 
	 * @param ParamOperacionesVO
	 * @return ArrayList de DescuentosVO
	 * @throws Exception
	 */
	public ArrayList getCasoPorDescontar(long casId) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		//int contador = 0;

		String command;

		command = "SELECT "
				+ "CASID, CASEST, CASFECEST, "
				+ "CASINDDES, A.SOCRUT, b.DV SOCDV, "
				+ "b.FNNOMBRES SOCNOMBRE,b.FNAPELLPAT SOCAPEPAT, b.FNAPELLMAT SOCAPEMAT, "
				+ "b.FNLPAGO SOCOFICINA, CASAPOSOC, CASCUOCONV, "
				+ "CASCUOBIE, CASBONO, CASAPOCON, CASFECOCU " + "FROM "
				+ bonifDatabase + ".BF03F1 A, " + bonifDatabase
				+ ".FUNCIONARIOS B " + "WHERE " + "A.SOCRUT = B.RUT " + "AND "
				+ "CASTIPO = ? " + "AND " + "CASEST = ? " + "AND "
				+ "CASINDBON = ? " + "AND " + "CASINDDES <> ? " + "AND "
				+ "CASID = ? " + "ORDER BY " + "b.FNLPAGO, A.SOCRUT, CASFECOCU";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, Caso.TIPO_DESCUENTO);
			stmt.setString(2, Caso.STD_ACTIVO);
			stmt.setString(3, Caso.ESTADOINDICADOR_SI);
			stmt.setString(4, Caso.ESTADOINDICADOR_SI);
			stmt.setLong(5, casId);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				DescuentosVO descuento = new DescuentosVO();
				descuento.setCasoId(ors.getLong("CASID"));
				descuento.setEstado(ors.getString("CASEST"));
				descuento.setFechaEstado(ors.getDate("CASFECEST"));
				descuento.setIndicadorDescuento(ors.getString("CASINDDES"));
				descuento.setRut(ors.getString("SOCRUT"));
				descuento.setDv(ors.getString("SOCDV"));
				descuento.setNombre(ors.getString("SOCNOMBRE"));
				descuento.setApellidoPaterno(ors.getString("SOCAPEPAT"));
				descuento.setApellidoMaterno(ors.getString("SOCAPEMAT"));
				descuento.setOficina(ors.getString("SOCOFICINA"));
				descuento.setMontoDescuento(ors.getDouble("CASAPOSOC"));
				descuento.setNumeroCuotasConvenio(ors.getInt("CASCUOCONV"));
				descuento.setNumeroCuotasBienestar(ors.getInt("CASCUOBIE"));
				descuento.setTipoBono(ors.getString("CASBONO"));
				descuento.setAporteConvenio(ors.getDouble("CASAPOCON"));
				descuento.setFechaOcurrencia(ors.getDate("CASFECOCU"));

				retorno.add(descuento);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene información de la siguiente cuota por cobrar al Socio
	 * 
	 * @return CuotaPendienteVO
	 * @throws Exception
	 */
	public CuotaPendienteVO getCuotaNoCobrada(long casoId) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "SELECT CUONUM, CUOVALOR, CUOFECVCTO FROM "
				+ bonifDatabase
				+ ".BF08F1 "
				+ "WHERE CASID = ? AND CUOEST = ? AND CUONUM = (SELECT MIN(CUONUM) FROM "
				+ bonifDatabase + ".BF08F1 WHERE CASID = ? AND CUOEST = ?)";

		CuotaPendienteVO cuota = new CuotaPendienteVO();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, casoId);
			stmt.setString(2, Cuota.STD_NO_DESCONTADA);
			stmt.setLong(3, casoId);
			stmt.setString(4, Cuota.STD_NO_DESCONTADA);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			if (ors.next()) {

				cuota.setCuota(ors.getInt("CUONUM"));
				cuota.setMonto((int) ors.getDouble("CUOVALOR"));
				cuota.setFecha(ors.getDate("CUOFECVCTO"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return cuota;
	}

	/**
	 * Obtiene información de la siguiente cuota por pagar al convenio
	 * 
	 * @return CuotaPendienteVO
	 * @throws Exception
	 */
	public CuotaPendienteVO getCuotaNoPagada(long casoId) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "SELECT CUONUM, CUOVALOR, CUOFECVCTO FROM "
				+ bonifDatabase
				+ ".BF08F1 "
				+ "WHERE CASID = ? AND CUOEST = ? AND CUONUM = (SELECT MIN(CUONUM) FROM "
				+ bonifDatabase + ".BF08F1 WHERE CASID = ? AND CUOEST = ?)";

		CuotaPendienteVO cuota = new CuotaPendienteVO();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, casoId);
			stmt.setString(2, Cuota.STD_DESCONTADA);
			stmt.setLong(3, casoId);
			stmt.setString(4, Cuota.STD_DESCONTADA);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			if (ors.next()) {

				cuota.setCuota(ors.getInt("CUONUM"));
				cuota.setMonto((int) ors.getDouble("CUOVALOR"));
				cuota.setFecha(ors.getDate("CUOFECVCTO"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return cuota;
	}

	/**
	 * Obtiene una lista con los Casos Por Pagar segun el codigo de descuento
	 * pasado como parametro modificado según req 4353p ara que incluya los
	 * casos saldados anticipadamente
	 * 
	 * @return ArrayList de PagoConvenioVO
	 * @throws Exception
	 */

	public ArrayList getCasosPorPagar(long codigoDescuento) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		//int contador = 0;

		String command;
		/*
		 * command = "SELECT CASID, CASAPOBIE, A.SOCRUT, SOCDV, SOCNOMBRE,
		 * SOCAPEPAT, SOCAPEMAT, " + "SOCOFICINA, A.CONCOD, CONRUT, CONDV,
		 * CONNOM, TCOTESCOEG, CASMONTO, CASFECING, CASFECEST, "+ "CASCUOCONV,
		 * CASCUOBIE, CASEST, CASINDPAG " + "FROM "+bonifDatabase+".BF03F1 A, "+
		 * bonifDatabase+".BF01F1 B, "+ bonifDatabase+".BF05F1 C, " +
		 * bonifDatabase+".BF14F1 D " + "WHERE " + "A.SOCRUT = B.SOCRUT AND "+
		 * "A.CONCOD = C.CONCOD AND " + "C.TCOCOD = D.TCOCOD AND " + "A.CASBONO = ?
		 * AND " + "CASID IN " + "(SELECT CASID " + "FROM "+
		 * bonifDatabase+".BF20F1 "+ "WHERE TDTCOD = ? " + "UNION " + "SELECT
		 * CASID " + "FROM "+ bonifDatabase+".BF32F1 "+ "WHERE TDTCOD = ?) " +
		 * "ORDER BY SOCOFICINA, A.SOCRUT";
		 */

		command = "SELECT  A.CASID, CASAPOBIE, A.SOCRUT, b.DV SOCDV, b.FNNOMBRES SOCNOMBRE, b.FNAPELLPAT SOCAPEPAT,"
				+ "b.FNAPELLPAT SOCAPEMAT,"
				+ "b.FNLPAGO SOCOFICINA, A.CONCOD, CONRUT, CONDV, CONNOM, TCOTESCOEG, CASMONTO,"
				+ "CASFECING, CASFECEST,"
				+ "CASCUOCONV, CASCUOBIE, CASEST, CASINDPAG "
				+ "FROM "
				+ bonifDatabase
				+ ".BF03F1 A, "
				+ bonifDatabase
				+ ".FUNCIONARIOS B, "
				+ bonifDatabase
				+ ".BF05F1 C, "
				+ bonifDatabase
				+ ".BF14F1 D, "
				+ "(SELECT CASID "
				+ "FROM "
				+ bonifDatabase
				+ ".BF20F1 "
				+ "WHERE TDTCOD = ? "
				+ "UNION "
				+ "SELECT CASID "
				+ "FROM "
				+ bonifDatabase
				+ ".BF32F1 "
				+ "WHERE TDTCOD = ?) E "
				+ "WHERE "
				+ "A.SOCRUT = B.RUT AND "
				+ "A.CONCOD = C.CONCOD AND "
				+ "C.TCOCOD = D.TCOCOD AND "
				+ "A.CASBONO = ? AND "
				+ "A.CASID = E.CASID "
				+ "ORDER BY FNLPAGO, A.SOCRUT";

		ArrayList retorno = new ArrayList();
		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoDescuento);
			stmt.setLong(2, codigoDescuento);
			stmt.setString(3, Caso.TIPOBONO_NO);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				PagoConvenioVO pago = new PagoConvenioVO();

				pago.setCodigoConvenio(ors.getLong("CONCOD"));
				pago.setRutConvenio(ors.getString("CONRUT"));
				pago.setDvConvenio(ors.getString("CONDV"));
				pago.setNombreConvenio(ors.getString("CONNOM"));
				pago.setCasoId(ors.getLong("CASID"));
				pago.setEstado(ors.getString("CASEST"));
				pago.setFechaEstado(ors.getDate("CASFECEST"));
				pago.setIndicadorPago(ors.getString("CASINDPAG"));
				pago.setRut(ors.getString("SOCRUT"));
				pago.setDv(ors.getString("SOCDV"));
				pago.setNombre(ors.getString("SOCNOMBRE"));
				pago.setApellidoPaterno(ors.getString("SOCAPEPAT"));
				pago.setApellidoMaterno(ors.getString("SOCAPEMAT"));
				pago.setOficina(ors.getString("SOCOFICINA"));
				pago.setMontoPago(ors.getDouble("CASMONTO"));
				pago.setNumeroCuotasBienestar(ors.getInt("CASCUOBIE"));
				pago.setNumeroCuotasConvenio(ors.getInt("CASCUOCONV"));
				pago.setConceptoTesoreriaConvenioEgreso(ors
						.getLong("TCOTESCOEG"));
				pago.setAporteBienestar(ors.getInt("CASAPOBIE"));

				retorno.add(pago);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Actualiza el estado e indicador de reembolso de un caso valida que el
	 * caso se encuentre en un estado válido para reembolsar
	 * 
	 * @param casoReembolso
	 * @param estadoPrevio
	 * @param indicadorPrevio
	 * @return nuúmero de filas afectadas
	 * @throws Exception
	 * @throws BusinessException
	 */

	public int updateIndicadorReembolso(ReembolsoVO casoReembolso,
			String estadoPrevio, String indicadorPrevio) throws Exception,
			BusinessException {

		if (casoReembolso == null) {
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"Se ha intentado modificar un Caso Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		int filasActualizadas = 0;

		String command;

		command = "UPDATE "
				+ bonifDatabase
				+ ".BF03F1 SET  CASEST = ?, "
				+ "CASFECEST = ?, CASINDREE  = ? WHERE CASID = ? AND CASAPOBIE = ? "
				+ "AND CASEST = ? AND CASINDREE = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, casoReembolso.getEstado());
			stmt.setTimestamp(2, new java.sql.Timestamp(casoReembolso
					.getFechaEstado().getTime()));
			stmt.setString(3, casoReembolso.getIndicadorReembolso());
			stmt.setLong(4, casoReembolso.getCasoId());
			stmt.setDouble(5, casoReembolso.getMontoReembolso());
			stmt.setString(6, estadoPrevio);
			stmt.setString(7, indicadorPrevio);

			logger.debug("Inicia operación: " + command);
			filasActualizadas = stmt.executeUpdate();
			logger.debug("Filas Actualizadas: " + filasActualizadas);
			logger.debug("Finaliza operación: " + command);

			return filasActualizadas;

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Actualiza el estado e indicador de egreso de un preCaso valida que el
	 * caso se encuentre en un estado precaso
	 * 
	 * @param CasoVO
	 *            casoVo
	 * @param String
	 *            estadoPrevio
	 * @return número de filas afectadas
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int updateIndicadorEgresoPreCaso(CasoVO casoVo, String estadoPrevio)
			throws Exception, BusinessException {

		if (casoVo == null) {
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"Se ha intentado modificar un Caso Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		int filasActualizadas = 0;

		String command;

		command = "UPDATE " + bonifDatabase + ".BF03F1 SET "
				+ "CASINDPCEG  = ? WHERE CASID = ? " + "AND CASEST = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, casoVo.getIndicadorPreCasoEgresoGenerado());
			stmt.setLong(2, casoVo.getCasoID());
			stmt.setString(3, estadoPrevio);

			logger.debug("Inicia operación: " + command);
			filasActualizadas = stmt.executeUpdate();
			logger.debug("Filas Actualizadas: " + filasActualizadas);
			logger.debug("Finaliza operación: " + command);

			return filasActualizadas;

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Actualiza el estado e indicador de ingreso de un preCaso valida que el
	 * caso se encuentre en un estado precaso
	 * 
	 * @param CasoVO
	 *            casoVo
	 * @param String
	 *            estadoPrevio
	 * @return número de filas afectadas
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int updateIndicadorIngresoPreCaso(CasoVO casoVo, String estadoPrevio)
			throws Exception, BusinessException {

		if (casoVo == null) {
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"Se ha intentado modificar un Caso Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		int filasActualizadas = 0;

		String command;

		command = "UPDATE " + bonifDatabase + ".BF03F1 SET "
				+ "CASINDPCIG  = ? WHERE CASID = ? " + "AND CASEST = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, casoVo.getIndicadorPreCasoIngresoGenerado());
			stmt.setLong(2, casoVo.getCasoID());
			stmt.setString(3, estadoPrevio);

			logger.debug("Inicia operación: " + command);
			filasActualizadas = stmt.executeUpdate();
			logger.debug("Filas Actualizadas: " + filasActualizadas);
			logger.debug("Finaliza operación: " + command);

			return filasActualizadas;

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Actualiza el estado e indicador de descuento de un caso valida que el
	 * caso se encuentre en un estado válido para descontar
	 * 
	 * @param casoDescuento
	 * @param estadoPrevio
	 * @param indicadorPrevio
	 * @return nuúmero de filas afectadas
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int updateIndicadorDescuento(DescuentosVO casoDescuento,
			String indicadorPrevio, String estadoPrevio) throws Exception,
			BusinessException {

		if (casoDescuento == null) {
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"Se ha intentado modificar un Caso Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		int filasActualizadas = 0;

		String command;

		command = "UPDATE "
				+ bonifDatabase
				+ ".BF03F1 SET CASINDDES = ?, CASEST = ?, "
				+ "CASFECEST = ? WHERE CASID = ? AND CASEST = ? AND CASINDDES = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			logger.debug("Indicador Descuento: "
					+ casoDescuento.getIndicadorDescuento());
			stmt.setString(1, casoDescuento.getIndicadorDescuento());
			logger.debug("Estado: " + casoDescuento.getEstado());
			stmt.setString(2, casoDescuento.getEstado());
			logger.debug("Fecha: " + casoDescuento.getFechaEstado());
			stmt.setTimestamp(3, new java.sql.Timestamp(casoDescuento
					.getFechaEstado().getTime()));
			logger.debug("Caso ID: " + casoDescuento.getCasoId());
			stmt.setLong(4, casoDescuento.getCasoId());
			logger.debug("Etado Previo: " + estadoPrevio);
			stmt.setString(5, estadoPrevio);
			logger.debug("Indicador Previo: " + indicadorPrevio);
			stmt.setString(6, indicadorPrevio);

			logger.debug("Inicia operación: " + command);
			filasActualizadas = stmt.executeUpdate();
			logger.debug("Finaliza operación: " + command);

			return filasActualizadas;

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Actualiza el estado e indicador de Pago de un caso valida que el caso se
	 * encuentre en un estado válido para pagar
	 * 
	 * @param casoPago
	 * @param indicadorPrevio
	 * @return nuúmero de filas afectadas
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int updateIndicadorPago(PagoConvenioVO casoPago,
			String indicadorPrevio, String estadoPrevio) throws Exception,
			BusinessException {

		if (casoPago == null) {
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"Se ha intentado modificar un Caso Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		int filasActualizadas = 0;

		String command;

		command = "UPDATE "
				+ bonifDatabase
				+ ".BF03F1 SET CASINDPAG = ?, CASEST = ?, "
				+ "CASFECEST = ? WHERE CASID = ? AND CASEST = ? AND CASINDPAG = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			logger.debug("Indicador Pago: " + casoPago.getIndicadorPago());
			stmt.setString(1, casoPago.getIndicadorPago());
			logger.debug("Estado: " + casoPago.getEstado());
			stmt.setString(2, casoPago.getEstado());
			logger.debug("Fecha Estado: " + casoPago.getFechaEstado());
			stmt.setTimestamp(3, new java.sql.Timestamp(casoPago
					.getFechaEstado().getTime()));
			logger.debug("Caso Id: " + casoPago.getCasoId());
			stmt.setLong(4, casoPago.getCasoId());
			logger.debug("Estado Previo: " + estadoPrevio);
			stmt.setString(5, estadoPrevio);
			logger.debug("Indicador Previo: " + indicadorPrevio);
			stmt.setString(6, indicadorPrevio);

			logger.debug("Inicia operación: " + command);
			filasActualizadas = stmt.executeUpdate();
			logger.debug("Finaliza operación: " + command);

			return filasActualizadas;

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Actualiza el estado e indicador de Pago Anticipado de un Caso además,
	 * actualiza los datos del pago anticipado: monto abono, monto prestamo, num
	 * prestamo valida que el caso se encuentre en un estado válido para pagar
	 * anticipadamente
	 * 
	 * @param caso
	 * @param indicadorPrevio
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void updateIndicadorPagoAnticipado(Caso caso) throws Exception,
			BusinessException {

		if (caso == null) {
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"Se ha intentado modificar un Caso Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "UPDATE "
				+ bonifDatabase
				+ ".BF03F1 SET CASINDPAN = ?, CASEST = ?, "
				+ "CASFECEST = ?, CASABONO = ?, CASPRSTAMO = ?, CASNUMPTM = ? WHERE CASID = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			logger
					.debug("Indicador Pago: "
							+ caso.getIndicadorPagoAnticipado());
			stmt.setString(1, caso.getIndicadorPagoAnticipado());
			logger.debug("Estado: " + caso.getEstado());
			stmt.setString(2, caso.getEstado());
			logger.debug("Fecha Estado: " + caso.getFechaEstado());
			stmt.setTimestamp(3, new java.sql.Timestamp(caso.getFechaEstado()
					.getTime()));
			logger.debug("Abono: " + caso.getAbono());
			stmt.setDouble(4, caso.getAbono());
			logger.debug("Prestamo: " + caso.getPrestamo());
			stmt.setDouble(5, caso.getPrestamo());
			logger.debug("Num Prestamo: " + caso.getNumeroPrestamo());
			stmt.setDouble(6, caso.getNumeroPrestamo());
			logger.debug("Caso Id: " + caso.getCasoID());
			stmt.setLong(7, caso.getCasoID());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Registra un Caso a Reembolsar
	 * 
	 * @param casoReembolso
	 * @throws Exception
	 * @throws BusinessException
	 */

	public void insertReembolso(ReembolsoVO casoReembolso) throws Exception,
			BusinessException {

		if (casoReembolso == null)
			throw new BusinessException("CCAF_BONIF_REEMBOLSOINVALIDO",
					"Se ha intentado crear un Reembolso Nulo");

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "INSERT INTO "
				+ bonifDatabase
				+ ".BF18F1 (CASID, TRECOD, SOCRUT, REEMONTO, REEFOLBIE, REEFOLARAU) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		try {

			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			logger.debug("CasoId: " + casoReembolso.getCasoId());
			stmt.setLong(1, casoReembolso.getCasoId());
			logger.debug("CodigoReembolso: " + casoReembolso.getCodigoReembolso());
			stmt.setLong(2, casoReembolso.getCodigoReembolso());
			logger.debug("Rut: " + casoReembolso.getRut());
			stmt.setString(3, casoReembolso.getRut());
			logger.debug("MontoReembolso: " + casoReembolso.getMontoReembolso());
			stmt.setDouble(4, casoReembolso.getMontoReembolso());
			logger.debug("FolioTesoreriaBienestar: " + casoReembolso.getFolioTesoreriaBienestar());
			stmt.setLong(5, casoReembolso.getFolioTesoreriaBienestar());
			logger.debug("FolioTesoreriaAraucana: " + casoReembolso.getFolioTesoreriaAraucana());
			stmt.setLong(6, casoReembolso.getFolioTesoreriaAraucana());			
			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);
		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}
	
	/**
	 * Registra informacion adicional de un Reembolso
	 * 
	 * @param casoReembolso
	 * @throws Exception
	 * @throws BusinessException
	 */

	public void insertInfoAdiReembolso(ReembolsoVO casoReembolso) throws Exception, BusinessException {

		if (casoReembolso == null)
			throw new BusinessException("CCAF_BONIF_REEMBOLSOINVALIDO",
					"Se ha intentado crear un Reembolso Nulo");

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "INSERT INTO "
				+ bonifDatabase
				+ ".BF36F1 (CASID, REETIPCTA, REENUMCTA, REEBANCO, REECORREO ) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try {

			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, casoReembolso.getCasoId());
			stmt.setString(2, casoReembolso.getTipoCuenta());
			stmt.setString(3, casoReembolso.getCuenta());
			stmt.setString(4, casoReembolso.getBanco());
			stmt.setString(5, casoReembolso.getCorreo());
			
			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);
		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}	

	/**
	 * Registra un nuevo Reembolso Total Retorna el codigo de Reembolso generado
	 */
	public long insertReembolsoTotal(ReembolsoTotalVO reembolsoTotal)
			throws Exception, BusinessException {

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		long idDisponible = 0;
		String command;

		if (reembolsoTotal == null)
			throw new BusinessException("CCAF_BONIF_REEMBOLSOINVALIDO",
					"Se ha intentado crear un Reembolso Nulo");

		command = "INSERT INTO "
				+ bonifDatabase
				+ ".BF22F1 (TRECOD, TREFEC, TREFOLEBIE, "
				+ "TREFOLIBIE, TREFOLIARA, TRETOTAL, TREUSUARIO) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {

			/*
			 * Obtiene un ID
			 */
			idDisponible = getID("BF22F1");
			if (idDisponible > 0) {
				reembolsoTotal.setCodigo(idDisponible);
			} else {
				throw new GeneralException(this, null, "CCAF_IDINVALIDO",
						"No se pudo obtener un ID");
			}

			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, reembolsoTotal.getCodigo());
			if (reembolsoTotal.getFecha() != null) {
				stmt.setTimestamp(2, new java.sql.Timestamp(reembolsoTotal
						.getFecha().getTime()));
			} else {
				stmt.setNull(2, Types.TIMESTAMP);
			}
			stmt.setLong(3, reembolsoTotal.getFolioEgresoBienestar());
			stmt.setLong(4, reembolsoTotal.getFolioIngresoBienestar());
			stmt.setLong(5, reembolsoTotal.getFolioIngresoAraucana());
			stmt.setLong(6, reembolsoTotal.getTotal());
			stmt.setString(7, reembolsoTotal.getUsuario());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);
			return reembolsoTotal.getCodigo();

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}
	
	/**
	 * Registra el e-mail de Reembolso Total
	 */
	public void insertEmailReembolsoTotal(ReembolsoTotalVO reembolsoTotal)
			throws Exception, BusinessException {

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		if (reembolsoTotal == null)
			throw new BusinessException("CCAF_BONIF_REEMBOLSOINVALIDO",
					"Se ha intentado crear un Reembolso Nulo");

		command = "INSERT INTO "
				+ bonifDatabase
				+ ".BF37F1 (TRECOD, MAIL_ENVIADO) VALUES (?, ?)";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, reembolsoTotal.getCodigo());
			stmt.setString(2, reembolsoTotal.isMailEnviado() ? "1" : "0");

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}	

	/**
	 * Actualiza un Reembolso Total Retorna el codigo de Reembolso generado
	 */

	public void updateReembolsoTotal(ReembolsoTotalVO reembolsoTotal)
			throws Exception, BusinessException {

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		if (reembolsoTotal == null)
			throw new BusinessException("CCAF_BONIF_REEMBOLSOINVALIDO",
					"Se ha intentado modificar un Reembolso Nulo");

		command = "UPDATE "
				+ bonifDatabase
				+ ".BF22F1 SET TREFOLEBIE = ?, "
				+ "TREFOLIBIE = ?, TREFOLIARA = ?, TRETOTAL = ? WHERE TRECOD = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			stmt.setLong(1, reembolsoTotal.getFolioEgresoBienestar());
			stmt.setLong(2, reembolsoTotal.getFolioIngresoBienestar());
			stmt.setLong(3, reembolsoTotal.getFolioIngresoAraucana());
			stmt.setLong(4, reembolsoTotal.getTotal());
			stmt.setLong(5, reembolsoTotal.getCodigo());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}
	
	/**
	 * Actualiza el indicador de envío de e-mail para un proceso de reembolso
	 * (Lo cambia a enviado)
	 */

	public void updateIndicadorEnvioEMailReembolso(long codigoReembolso) throws Exception, BusinessException {

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "UPDATE "
				+ bonifDatabase
				+ ".BF37F1 SET MAIL_ENVIADO = ?, FECENVIOMAIL = CURRENT TIMESTAMP "
				+ "WHERE TRECOD = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			stmt.setString(1, "1");
			stmt.setLong(2, codigoReembolso);

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}	

	/**
	 * Registra una Cuota de Prestamos
	 * 
	 * @param cuota
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void insertCuotaPrestamo(CuotaPrestamoVO cuota) throws Exception,
			BusinessException {

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		if (cuota == null)
			throw new BusinessException("CCAF_BONIF_CUOTAPRESTAMOINVALIDO",
					"Se ha intentado crear un Cuota de Préstamo Nula");

		command = "INSERT INTO "
				+ bonifDatabase
				+ ".BF21F1 (SOCRUT, CPRTIPO, CPRNUMCUO, "
				+ "CPRCTACOB, CPRFECVCTO, CPRMTOCUO,TDTCOD, CPRTIPFINAN, CPRCTACONT) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, cuota.getRut());
			stmt.setInt(2, cuota.getTipoPrestamo());
			stmt.setInt(3, cuota.getNumeroCuotasTotales());
			stmt.setInt(4, cuota.getCuotaActual());
			if (cuota.getFecha() != null) {
				stmt.setTimestamp(5, new java.sql.Timestamp(cuota.getFecha()
						.getTime()));
			} else {
				stmt.setNull(5, Types.TIMESTAMP);
			}
			stmt.setInt(6, cuota.getMonto());
			stmt.setLong(7, cuota.getCodigoDescuento());
			
			stmt.setString(8, cuota.getTipoFinanciamiento());
			stmt.setString(9, cuota.getCuentaContable());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);
		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Genera un nuevo código de Descuento
	 * 
	 */
	public long generaCodigoDescuento() throws Exception, BusinessException {

		long idDisponible = 0;
		/*
		 * Obtiene un ID
		 */
		idDisponible = getID("BF24F1");
		if (idDisponible > 0) {
			return idDisponible;
		} else {
			throw new GeneralException(this, null, "CCAF_IDINVALIDO",
					"No se pudo obtener un ID");
		}
	}

	/**
	 * Registra el total descontado a un socio se suman los casos y los
	 * descuentos por los prestamos
	 * 
	 * @param casoReembolso
	 * @throws Exception
	 * @throws BusinessException
	 */

	public void insertDescuentoTotalSocio(DescuentoTotalSocioVO descuentoTotal)
			throws Exception, BusinessException {

		if (descuentoTotal == null)
			throw new BusinessException("CCAF_BONIF_DECUENTOINVALIDO",
					"Se ha intentado crear un Descuento Nulo");

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "INSERT INTO " + bonifDatabase + ".BF24F1 (TDTCOD,SOCRUT,"
				+ "TDTMONTO,TDTFECHA, TDTUSUARIO) VALUES (?,?,?,?,?)";

		try {

			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, descuentoTotal.getCodigoDescuento());
			stmt.setString(2, descuentoTotal.getRut());
			stmt.setDouble(3, descuentoTotal.getMontoDescuento());
			if (descuentoTotal.getFecha() != null) {
				stmt.setTimestamp(4, new java.sql.Timestamp(descuentoTotal
						.getFecha().getTime()));
			} else {
				stmt.setNull(4, Types.TIMESTAMP);
			}
			stmt.setString(5, descuentoTotal.getUsuario());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);
		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Registra el total descontado a un socio se suman los casos y los
	 * descuentos por los prestamos
	 * 
	 * @param casoReembolso
	 * @throws Exception
	 * @throws BusinessException
	 */
	/*
	 * public void insertDescuentoTotalSocioSaldoDeuda(DescuentoTotalSocioVO
	 * descuentoTotal) throws Exception, BusinessException {
	 * 
	 * if (descuentoTotal == null) throw new
	 * BusinessException("CCAF_BONIF_DECUENTOINVALIDO", "Se ha intentado crear
	 * un Descuento Nulo");
	 * 
	 * Connection conn = null; CallableStatement stmt = null; ResultSet ors =
	 * null; String command;
	 * 
	 * command = "INSERT INTO " + bonifDatabase + ".BF24F1 (TDTCOD,SOCRUT,"+
	 * "TDTMONTO,TDTFECHA, TDTUSUARIO, PAGOANT) VALUES (?,?,?,?,?,?)";
	 * 
	 * try {
	 * 
	 * conn = DB2Utils.createConnection(bonifJNDIDataSource); stmt =
	 * conn.prepareCall(command);
	 * stmt.setLong(1,descuentoTotal.getCodigoDescuento());
	 * stmt.setString(2,descuentoTotal.getRut());
	 * stmt.setDouble(3,descuentoTotal.getMontoDescuento()); if
	 * (descuentoTotal.getFecha()!=null) { stmt.setTimestamp(4, new
	 * java.sql.Timestamp(descuentoTotal.getFecha().getTime())); } else {
	 * stmt.setNull(4,Types.TIMESTAMP); }
	 * stmt.setString(5,descuentoTotal.getUsuario()); stmt.setString(6,"S");
	 * 
	 * logger.debug("Inicia operación: " + command); stmt.execute();
	 * logger.debug("Finaliza operación: " + command); } catch (SQLException ex) {
	 * ex.printStackTrace(); int code=ex.getErrorCode(); throw new
	 * BusinessException(PREFIX+code); } finally { DB2Utils.closeAll(conn, stmt,
	 * ors); } }
	 */
	/**
	 * Obtiene las cuotas de los prestamos registradas en Bienestar según los
	 * parametros pasados
	 * 
	 * @return ArrayList de CuotaPrestamoVO
	 * @throws Exception
	 */
	public ArrayList getCuotasPrestamo(CuotaPrestamoVO cuotaPrestamoFiltro)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		int contador = 0;
		boolean filtrarPorRut = false;
		boolean filtrarPorFecha = false;
		boolean filtrarPorCodigoDescuento = false;
		boolean filtrarPorCodigoOficina = false;

		String command;

		if (cuotaPrestamoFiltro == null)
			throw new BusinessException("CCAF_BONIF_CUOTAPRESTAMOINVALIDO",
					"No se especificaron los filtros necesarios");

		command = "SELECT A.SOCRUT, B.DV SOCDV, CPRTIPO, CPRNUMCUO, CPRCTACOB, CPRFECVCTO, "
				+ "CPRMTOCUO, TDTCOD, CPRTIPFINAN, CPRCTACONT FROM "
				+ bonifDatabase
				+ ".BF21F1 A, "
				+ bonifDatabase
				+ ".FUNCIONARIOS B "
				+ "WHERE  A.SOCRUT = B.RUT";

		if (cuotaPrestamoFiltro.getRut() != null
				&& cuotaPrestamoFiltro.getRut().length() > 0) {
			command = command + " AND A.SOCRUT = ?";
			filtrarPorRut = true;
		}

		if (cuotaPrestamoFiltro.getFecha() != null) {
			command = command + " AND CPRFECVCTO = ?";
			filtrarPorFecha = true;
		}

		if (cuotaPrestamoFiltro.getCodigoDescuento() > 0) {
			command = command + " AND TDTCOD = ?";
			filtrarPorCodigoDescuento = true;
		}

		if (cuotaPrestamoFiltro.getCodigoOficina() != null
				&& cuotaPrestamoFiltro.getCodigoOficina().length() > 0) {
			command = command + " AND B.FNLPAGO = ?";
			filtrarPorCodigoOficina = true;
		}

		command = command + " ORDER BY A.SOCRUT";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			if (filtrarPorRut) {
				contador++;
				stmt.setString(contador, cuotaPrestamoFiltro.getRut());
			}
			if (filtrarPorFecha) {
				contador++;
				stmt.setTimestamp(contador, new java.sql.Timestamp(
						cuotaPrestamoFiltro.getFecha().getTime()));
			}

			if (filtrarPorCodigoDescuento) {
				contador++;
				stmt
						.setLong(contador, cuotaPrestamoFiltro
								.getCodigoDescuento());
			}

			if (filtrarPorCodigoOficina) {
				contador++;
				stmt
						.setString(contador, cuotaPrestamoFiltro
								.getCodigoOficina());
			}

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				CuotaPrestamoVO cuotaPrestamo = new CuotaPrestamoVO();
				cuotaPrestamo.setRut(ors.getString("SOCRUT"));
				cuotaPrestamo.setDv(ors.getString("SOCDV"));
				cuotaPrestamo.setTipoPrestamo(ors.getInt("CPRTIPO"));
				cuotaPrestamo.setNumeroCuotasTotales(ors.getInt("CPRNUMCUO"));
				cuotaPrestamo.setCuotaActual(ors.getInt("CPRCTACOB"));
				cuotaPrestamo.setFecha(ors.getDate("CPRFECVCTO"));
				cuotaPrestamo.setMonto(ors.getInt("CPRMTOCUO"));
				cuotaPrestamo.setCodigoDescuento(ors.getLong("TDTCOD"));
				cuotaPrestamo.setTipoFinanciamiento(ors.getString("CPRTIPFINAN"));
				cuotaPrestamo.setCuentaContable(ors.getString("CPRCTACONT"));

				retorno.add(cuotaPrestamo);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene información de las cuotas de los prestamos que se encuentran
	 * vigentes
	 * 
	 * @return ArrayList de CuotaPrestamoVO
	 * @throws Exception
	 */
	public ArrayList getCuotasPrestamoVigenteSocio(String rut)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "SELECT SOCRUT,CPRTIPO,CPRNUMCUO,CPRCTACOB,CPRMTOCUO,CPRFECVCTO, CPRTIPFINAN, CPRCTACONT "
				+ "FROM "
				+ bonifDatabase
				+ ".BF21F1 WHERE CPRNUMCUO > CPRCTACOB AND SOCRUT = ? "
				+ "AND CPRFECVCTO = (SELECT MAX(CPRFECVCTO) FROM "
				+ bonifDatabase + ".BF21F1)";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, rut);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				CuotaPrestamoVO cuotaPrestamo = new CuotaPrestamoVO();
				cuotaPrestamo.setRut(ors.getString("SOCRUT"));
				cuotaPrestamo.setTipoPrestamo(ors.getInt("CPRTIPO"));
				cuotaPrestamo.setNumeroCuotasTotales(ors.getInt("CPRNUMCUO"));
				cuotaPrestamo.setCuotaActual(ors.getInt("CPRCTACOB"));
				cuotaPrestamo.setFecha(ors.getDate("CPRFECVCTO"));
				cuotaPrestamo.setMonto(ors.getInt("CPRMTOCUO"));
				
				cuotaPrestamo.setTipoFinanciamiento(ors.getString("CPRTIPFINAN"));
				cuotaPrestamo.setCuentaContable(ors.getString("CPRCTACONT"));				

				retorno.add(cuotaPrestamo);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Registra un Caso a Descontar
	 * 
	 * @param casoDescuento
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void insertDescuento(DescuentosVO casoDescuento) throws Exception,
			BusinessException {

		if (casoDescuento == null)
			throw new BusinessException("CCAF_BONIF_DECUENTOINVALIDO",
					"Se ha intentado crear un Descuento Nulo");

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "INSERT INTO "
				+ bonifDatabase
				+ ".BF20F1 (SOCRUT, CASID, "
				+ "DUCMONTO, DUCCUONUM, DUCCUOTOT, DUCFECPRO,TDTCOD,DUCAPOCON) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {

			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, casoDescuento.getRut());
			stmt.setLong(2, casoDescuento.getCasoId());
			logger.debug("Monto Descuento: "
					+ casoDescuento.getMontoDescuento());
			stmt.setInt(3, (int) casoDescuento.getMontoDescuento());
			stmt.setInt(4, casoDescuento.getCuotaActual());
			logger.debug("Cotas Totales:" + casoDescuento.getNumeroCuotas());
			stmt.setInt(5, casoDescuento.getNumeroCuotas());
			if (casoDescuento.getFechaDescuento() != null)
				stmt.setTimestamp(6, new java.sql.Timestamp(casoDescuento
						.getFechaDescuento().getTime()));
			else
				stmt.setNull(6, Types.TIMESTAMP);
			stmt.setLong(7, casoDescuento.getCodigoDescuento());
			stmt.setInt(8, (int) casoDescuento.getAporteConvenio());
			// stmt.setString(9,casoDescuento.getTipoDescuento());//req 4353

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);
		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Registra un Caso que se paga anticipadamente todas sus cuotas
	 * 
	 * @param casoPagoAnticipado
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void insertPagoAnticipado(DescuentosVO casoPagoAnticipado)
			throws Exception, BusinessException {

		if (casoPagoAnticipado == null)
			throw new BusinessException("CCAF_BONIF_DECUENTOINVALIDO",
					"Se ha intentado crear un Descuento Nulo");

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "INSERT INTO "
				+ bonifDatabase
				+ ".BF32F1 (SOCRUT, CASID, "
				+ "DUCMONTO, DUCCUONUM, DUCCUOTOT, DUCFECPRO,TDTCOD,DUCAPOCON) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {

			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, casoPagoAnticipado.getRut());
			stmt.setLong(2, casoPagoAnticipado.getCasoId());
			logger.debug("Monto Descuento: "
					+ casoPagoAnticipado.getMontoDescuento());
			stmt.setInt(3, (int) casoPagoAnticipado.getMontoDescuento());
			stmt.setInt(4, casoPagoAnticipado.getCuotaActual());
			logger.debug("Cotas Totales:"
					+ casoPagoAnticipado.getNumeroCuotas());
			stmt.setInt(5, casoPagoAnticipado.getNumeroCuotas());
			if (casoPagoAnticipado.getFechaDescuento() != null)
				stmt.setTimestamp(6, new java.sql.Timestamp(casoPagoAnticipado
						.getFechaDescuento().getTime()));
			else
				stmt.setNull(6, Types.TIMESTAMP);
			stmt.setLong(7, casoPagoAnticipado.getCodigoDescuento());
			stmt.setInt(8, (int) casoPagoAnticipado.getAporteConvenio());
			// stmt.setString(9,casoPagoAnticipado.getTipoDescuento());//req
			// 4353

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);
		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Obtiene la fecha en la cual se realizo el ultimo proceso de descuento
	 * 
	 * @return Date
	 * @throws Exception
	 */
	public Date getFechaUltimoDescuento() throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "SELECT MAX(DUCFECPRO) \"MAX\" " + "FROM " + bonifDatabase
				+ ".BF20F1";

		Date fechaUltimoDescuento = null;

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			if (ors.next()) {
				fechaUltimoDescuento = ors.getDate("MAX");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Fecha: " + fechaUltimoDescuento);
		return fechaUltimoDescuento;
	}

	/**
	 * Registra un Caso a Pagar
	 * 
	 * @param casoPago
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void insertPago(PagoConvenioVO casoPago) throws Exception,
			BusinessException {

		if (casoPago == null)
			throw new BusinessException("CCAF_BONIF_DECUENTOINVALIDO",
					"Se ha intentado crear un Descuento Nulo");

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "INSERT INTO "
				+ bonifDatabase
				+ ".BF19F1 (CONCOD, SOCRUT, CASID, "
				+ "PCOMONTO, PCOCUONUM, PCOCUOTOT, PCOFECPRO, PCOFOLBIE,PCOCOD,PCOUSUARIO) VALUES (?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, ?)";

		try {

			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, casoPago.getCodigoConvenio());
			stmt.setString(2, casoPago.getRut());
			stmt.setLong(3, casoPago.getCasoId());
			stmt.setDouble(4, casoPago.getMontoPago());
			stmt.setInt(5, casoPago.getCuotaActual());
			stmt.setInt(6, casoPago.getNumeroCuotas());
			stmt.setTimestamp(7, new java.sql.Timestamp(casoPago.getFechaPago()
					.getTime()));
			stmt.setLong(8, casoPago.getFolioTesoreriaBienestar());
			stmt.setLong(9, casoPago.getCodigoPago());
			stmt.setString(10, casoPago.getUsuario());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);
		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Obtiene una lista con los descuentos que se efectuaron en el mes
	 * consultado
	 * 
	 * @param mes
	 *            de consulta
	 * @return ContabilidadVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getDescuentosMes(long codigoDescuento) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;
		command = "SELECT A.CASID, A.SOCRUT, "
				+ "b.DV SOCDV, C.CONCOD, CASNUMDOC,"
				+ "DUCMONTO,DUCCUONUM,DUCCUOTOT,"
				+ "CASAPOSOC,CASAPOBIE,CASBONO,"
				+ "TCOCTAACRE, TCOCTADEU, DUCAPOCON,"
				+ "CONRUT,CONDV, CONNOM, "
				+ "b.FNNOMBRES SOCNOMBRE, b.FNAPELLPAT SOCAPEPAT, b.FNAPELLMAT SOCAPEMAT "
				+ "FROM " + bonifDatabase + ".BF20F1 A," + bonifDatabase
				+ ".FUNCIONARIOS B," + bonifDatabase + ".BF03F1 C,"
				+ bonifDatabase + ".BF05F1 D," + bonifDatabase + ".BF14F1 E "
				+ "WHERE " + "TDTCOD = ? " + "AND A.SOCRUT = B.RUT "
				+ "AND A.CASID = C.CASID " + "AND C.CONCOD = D.CONCOD "
				+ "AND D.TCOCOD = E.TCOCOD";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoDescuento);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				ContabilidadVO descuento = new ContabilidadVO();
				descuento.setAporteBienestar(ors.getInt("CASAPOBIE"));
				descuento.setAporteSocio(ors.getInt("CASAPOSOC"));
				descuento.setCasoId(ors.getLong("CASID"));
				descuento.setCodigoConvenio(ors.getLong("CONCOD"));
				descuento.setCuentaAcreedor(String.valueOf(ors
						.getLong("TCOCTAACRE")));
				descuento.setCuentaDeudor(String.valueOf(ors
						.getLong("TCOCTADEU")));
				descuento.setDocumento(ors.getString("CASNUMDOC"));
				descuento.setDvSocio(ors.getString("SOCDV"));
				descuento.setRutSocio(ors.getString("SOCRUT"));
				descuento.setTotal(ors.getInt("DUCMONTO"));
				descuento.setTipoBono(ors.getString("CASBONO"));
				descuento.setAporteConvenio(ors.getInt("DUCAPOCON"));
				descuento.setRutConvenio(ors.getString("CONRUT"));
				descuento.setDvConvenio(ors.getString("CONDV"));
				descuento.setNombreConvenio(ors.getString("CONNOM"));
				descuento.setNombre(ors.getString("SOCNOMBRE"));
				descuento.setApePat(ors.getString("SOCAPEPAT"));
				descuento.setApeMat(ors.getString("SOCAPEMAT"));
				descuento.setCuotaActual(ors.getInt("DUCCUONUM"));
				descuento.setCuotasTotales(ors.getInt("DUCCUOTOT"));

				retorno.add(descuento);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;

	}

	/**
	 * Obtiene una lista con casos que se pagaron totalmente en forma anticipada
	 * por pago con pretamo y/o abono del socio
	 * 
	 * @param Date
	 *            fechaInicio, Date fechaFin
	 * @return ArrayList de ContabilidadVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getCasosPagadosAnticipadamente(Date fechaInicio,
			Date fechaFin) throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;
		command = "SELECT  CASID, B.RUT SOCRUT, "
				+ "B.DV SOCDV, C.CONCOD, CASNUMDOC, CASAPOSOC, "
				+ "CASAPOBIE, CASBONO, TCOCTAACRE, TCOCTADEU, "
				+ "CONRUT, CONDV, CONNOM, b.FNNOMBRES SOCNOMBRE, b.FNAPELLPAT SOCAPEPAT, b.FNAPELLMAT SOCAPEMAT "
				+ "FROM " + bonifDatabase + ".FUNCIONARIOS B, " + bonifDatabase
				+ ".BF03F1 C, " + bonifDatabase + ".BF05F1 D, " + bonifDatabase
				+ ".BF14F1 E " + "WHERE " + "C.SOCRUT = B.RUT "
				+ "AND C.CONCOD = D.CONCOD " + "AND D.TCOCOD = E.TCOCOD "
				+ "AND (CASPRSTAMO+CASABONO) = CASAPOSOC "
				+ "AND (CASPRSTAMO > 0 OR CASABONO >0) " + "AND CASEST = ? "
				+ "AND CASFECEST BETWEEN ? AND ?";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, Caso.STD_CERRADO);
			stmt.setTimestamp(2, new java.sql.Timestamp(fechaInicio.getTime()));
			stmt.setTimestamp(3, new java.sql.Timestamp(fechaFin.getTime()));

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				ContabilidadVO descuento = new ContabilidadVO();
				descuento.setAporteBienestar(ors.getInt("CASAPOBIE"));
				descuento.setAporteSocio(ors.getInt("CASAPOSOC"));
				descuento.setCasoId(ors.getLong("CASID"));
				descuento.setCodigoConvenio(ors.getLong("CONCOD"));
				descuento.setCuentaAcreedor(String.valueOf(ors
						.getLong("TCOCTAACRE")));
				descuento.setCuentaDeudor(String.valueOf(ors
						.getLong("TCOCTADEU")));
				descuento.setDocumento(ors.getString("CASNUMDOC"));
				descuento.setDvSocio(ors.getString("SOCDV"));
				descuento.setRutSocio(ors.getString("SOCRUT"));
				// descuento.setTotal(ors.getInt("DUCMONTO"));
				descuento.setTipoBono(ors.getString("CASBONO"));
				// descuento.setAporteConvenio(ors.getInt("DUCAPOCON"));
				descuento.setRutConvenio(ors.getString("CONRUT"));
				descuento.setDvConvenio(ors.getString("CONDV"));
				descuento.setNombreConvenio(ors.getString("CONNOM"));
				descuento.setNombre(ors.getString("SOCNOMBRE"));
				descuento.setApePat(ors.getString("SOCAPEPAT"));
				descuento.setApeMat(ors.getString("SOCAPEMAT"));
				// descuento.setCuotaActual(ors.getInt("DUCCUONUM"));
				// descuento.setCuotasTotales(ors.getInt("DUCCUOTOT"));

				retorno.add(descuento);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;

	}

	/**
	 * Obtiene una lista con los reembolsos que se efectuaron en el mes
	 * consultado
	 * 
	 * @param mes
	 *            de consulta
	 * @return ContabilidadVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getReembolsosMes(Date fechaInicio, Date fechaFin)
			throws Exception, BusinessException {

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "SELECT "
				+ "A.CASID, "
				+ "A.SOCRUT, "
				+ "b.DV SOCDV, "
				+ "C.CONCOD, "
				+ "CASNUMDOC, "
				+ "(CASMONTO - CASDSCTO - CASAPOISA) \"CASMONTO\", "
				+ "CASAPOSOC, "
				+ "CASAPOBIE, "
				+ "TCOCTAACRE, "
				+ "TCOCTADEU, "
				+ "b.FNNOMBRES SOCNOMBRE, "
				+ "b.FNAPELLPAT SOCAPEPAT, "
				+ "b.FNAPELLMAT SOCAPEMAT "
				+ "FROM "
				+ bonifDatabase
				+ ".BF18F1 A,  "
				+ bonifDatabase
				+ ".FUNCIONARIOS B,"
				+ bonifDatabase
				+ ".BF03F1 C,"
				+ bonifDatabase
				+ ".BF05F1 D,"
				+ bonifDatabase
				+ ".BF14F1 E "
				+ "WHERE TRECOD IN (SELECT TRECOD FROM "
				+ bonifDatabase
				+ ".BF22F1 WHERE TREFEC "
				+ "BETWEEN ? AND ?) AND A.SOCRUT = B.RUT AND A.CASID = C.CASID AND C.CONCOD = "
				+ "D.CONCOD AND D.TCOCOD = E.TCOCOD";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setTimestamp(1, new java.sql.Timestamp(fechaInicio.getTime()));
			stmt.setTimestamp(2, new java.sql.Timestamp(fechaFin.getTime()));

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				ContabilidadVO reembolso = new ContabilidadVO();
				reembolso.setAporteBienestar(ors.getInt("CASAPOBIE"));
				reembolso.setAporteSocio(ors.getInt("CASAPOSOC"));
				reembolso.setCasoId(ors.getLong("CASID"));
				reembolso.setCodigoConvenio(ors.getLong("CONCOD"));
				reembolso.setCuentaAcreedor(String.valueOf(ors
						.getLong("TCOCTAACRE")));
				reembolso.setCuentaDeudor(String.valueOf(ors
						.getLong("TCOCTADEU")));
				reembolso.setDocumento(ors.getString("CASNUMDOC"));
				reembolso.setDvSocio(ors.getString("SOCDV"));
				reembolso.setRutSocio(ors.getString("SOCRUT"));
				reembolso.setTotal(ors.getInt("CASMONTO"));
				reembolso.setNombre(ors.getString("SOCNOMBRE"));
				reembolso.setApePat(ors.getString("SOCAPEPAT"));
				reembolso.setApeMat(ors.getString("SOCAPEMAT"));

				retorno.add(reembolso);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene una lista con los codigos de reembolsos por contabilizar
	 * 
	 * @param mes
	 *            de consulta
	 * @return InformacionAsiento
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getCodigoReembolsosPorContabilizar(Date fechaInicio,
			Date fechaFin) throws Exception, BusinessException {

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "SELECT TRECOD FROM " + bonifDatabase + ".BF22F1 WHERE "
				+ " TREFEC >= ? AND TREFEC < ?";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setTimestamp(1, new java.sql.Timestamp(fechaInicio.getTime()));
			stmt.setTimestamp(2, new java.sql.Timestamp(fechaFin.getTime()));

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				InformacionAsiento infoReembolso = new InformacionAsiento();
				infoReembolso.setCodigo(ors.getLong("TRECOD"));
				retorno.add(infoReembolso);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene una lista con las cuotas de los préstamos que se descontaron en
	 * el mes consultado
	 * 
	 * @param mes
	 *            de consulta
	 * @return ContabilidadVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getPrestamoMes(long codigoDescuento) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "SELECT " + "A.SOCRUT, " + "B.DV SOCDV, " + "CPRMTOCUO, "
				+ "CPRTIPO, " + "CPRCTACOB, " + "CPRNUMCUO, CPRTIPFINAN, CPRCTACONT, "
				+ "B.FNNOMBRES SOCNOMBRE, " + "b.FNAPELLPAT SOCAPEPAT, "
				+ "b.FNAPELLMAT SOCAPEMAT " + "FROM " + bonifDatabase
				+ ".BF21F1 A, " + bonifDatabase + ".FUNCIONARIOS B " + "WHERE "
				+ "TDTCOD = ? " + "AND " + "A.SOCRUT = B.RUT";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoDescuento);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				ContabilidadVO cuotaPrestamo = new ContabilidadVO();
				cuotaPrestamo.setTipoPrestamo(ors.getInt("CPRTIPO"));
				cuotaPrestamo.setAporteSocio(ors.getInt("CPRMTOCUO"));
				cuotaPrestamo.setDvSocio(ors.getString("SOCDV"));
				cuotaPrestamo.setRutSocio(ors.getString("SOCRUT"));
				cuotaPrestamo.setDocumento(cuotaPrestamo.getTipoPrestamo()
						+ ":" + String.valueOf(ors.getInt("CPRCTACOB")) + "/"
						+ String.valueOf(ors.getInt("CPRNUMCUO")));

				cuotaPrestamo.setTipoFinanciamiento(ors.getString("CPRTIPFINAN"));
				cuotaPrestamo.setCuentaContable(ors.getString("CPRCTACONT"));
				
				cuotaPrestamo.setNombre(ors.getString("SOCNOMBRE"));
				cuotaPrestamo.setApePat(ors.getString("SOCAPEPAT"));
				cuotaPrestamo.setApeMat(ors.getString("SOCAPEMAT"));

				retorno.add(cuotaPrestamo);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene la información de los totales de un reembolso semanal
	 * 
	 * @param codigoReembolso,
	 *            si es 0 (cero) los trae todos
	 * @return ReembolsoTotalVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getReembolsoTotal(long codigoReembolso) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		boolean filtarPorCodigoReembolso = false;
		int contador = 0;

		String command;

		command = "SELECT A.TRECOD, A.TREFEC, A.TREFOLEBIE, A.TREFOLIBIE, A.TREFOLIARA, A.TRETOTAL, B.MAIL_ENVIADO "
				+ "FROM " + bonifDatabase + ".BF22F1 A, " + bonifDatabase + ".BF37F1 B "
				+ "WHERE A.TRECOD = B.TRECOD";

		if (codigoReembolso != 0) {
			command = command + " AND A.TRECOD = ?";
			filtarPorCodigoReembolso = true;
		}

		command = command + " ORDER BY A.TRECOD DESC";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			if (filtarPorCodigoReembolso) {
				contador++;
				stmt.setLong(contador, codigoReembolso);
			}

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				ReembolsoTotalVO reemTot = new ReembolsoTotalVO();
				reemTot.setCodigo(ors.getLong("TRECOD"));
				reemTot.setFecha(ors.getDate("TREFEC"));
				reemTot.setFolioEgresoBienestar(ors.getLong("TREFOLEBIE"));
				reemTot.setFolioIngresoBienestar(ors.getLong("TREFOLIBIE"));
				reemTot.setFolioIngresoAraucana(ors.getLong("TREFOLIARA"));
				reemTot.setTotal(ors.getInt("TRETOTAL"));
				reemTot.setMailEnviado(ors.getString("MAIL_ENVIADO").equals("1") ? true : false);
				retorno.add(reemTot);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene la información de los detalles de un reembolso semanal
	 * 
	 * @param codigoReembolso
	 * @return ReembolsoVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getDetallesReembolso(long codigoReembolso)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "SELECT A.CASID, A.TRECOD,A.SOCRUT,B.DV SOCDV,b.FNNOMBRES SOCNOMBRE,"
				+ "b.FNAPELLPAT SOCAPEPAT,b.FNAPELLMAT SOCAPEMAT,"
				+ "b.FNLPAGO,REEMONTO,REEFOLBIE,REEFOLARAU, FNLPAGO SOCOFICINA, "
				+ "C.REECORREO as CORREO, C.REETIPCTA as TIPO_CUENTA, C.REENUMCTA as CUENTA, C.REEBANCO as BANCO " 
				+ "FROM " + bonifDatabase + ".BF18F1 A, " + bonifDatabase + ".FUNCIONARIOS B, " + bonifDatabase + ".BF36F1 C " 
				+ "WHERE A.SOCRUT = B.RUT "
				+ "AND A.CASID = C.CASID "
				+ "AND TRECOD = ? ORDER BY "
				+ "B.FNLPAGO, A.SOCRUT";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoReembolso);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				ReembolsoVO reem = new ReembolsoVO();
				reem.setRut(ors.getString("SOCRUT"));
				reem.setDv(ors.getString("SOCDV"));
				reem.setNombre(ors.getString("SOCNOMBRE"));
				reem.setApellidoMaterno(ors.getString("SOCAPEMAT"));
				reem.setApellidoPaterno(ors.getString("SOCAPEPAT"));
				reem.setOficina(ors.getString("SOCOFICINA"));
				reem.setCasoId(ors.getLong("CASID"));
				reem.setCodigoReembolso(ors.getLong("TRECOD"));
				reem.setFolioTesoreriaBienestar(ors.getLong("REEFOLBIE"));
				reem.setFolioTesoreriaAraucana(ors.getLong("REEFOLARAU"));
				reem.setMontoReembolso(ors.getInt("REEMONTO"));
				reem.setBanco(ors.getString("BANCO"));
				reem.setTipoCuenta(ors.getString("TIPO_CUENTA"));
				reem.setCorreo(ors.getString("CORREO"));
				reem.setCuenta(ors.getString("CUENTA"));

				retorno.add(reem);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene la información resumida por Oficina de los detalles de un
	 * reembolso semanal
	 * 
	 * @param codigoReembolso
	 * @return DetalleInformeReembolsosVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getResumenReembolso(long codigoReembolso)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "SELECT B.FNLPAGO SOCOFICINA, COUNT(DISTINCT(A.SOCRUT)) \"FILAS\", "
				+ "SUM(REEMONTO) \"SUMA\" FROM "
				+ bonifDatabase
				+ ".BF18F1 A, "
				+ bonifDatabase
				+ ".FUNCIONARIOS B WHERE A.SOCRUT = B.RUT AND TRECOD = ? GROUP BY B.FNLPAGO "
				+ "ORDER BY B.FNLPAGO";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoReembolso);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				DetalleInformeReembolsosVO detReem = new DetalleInformeReembolsosVO();
				detReem.setCodigoOficina(ors.getString("SOCOFICINA"));
				detReem.setCantidadFilas(ors.getInt("FILAS"));
				detReem.setMontoTotalOficina(ors.getInt("SUMA"));

				retorno.add(detReem);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Actualiza las cuotas de los prestamos con el codigo de descuento asignado
	 * en el proceso
	 * 
	 */
	public void updateCuotasPrestamos(long codigoDescuento, Date fecha)
			throws Exception, BusinessException {

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		if (codigoDescuento == 0)
			throw new BusinessException("CCAF_BONIF_DECUENTOINVALIDO",
					"El código de descuento es incorrecto");

		command = "UPDATE " + bonifDatabase + ".BF21F1 SET TDTCOD = ? "
				+ "WHERE CPRFECVCTO = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			stmt.setLong(1, codigoDescuento);
			stmt.setTimestamp(2, new java.sql.Timestamp(fecha.getTime()));

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Obtiene información de los codigos de descuento generados y la fecha de
	 * descuento. (Los cuales se realizan en forma mensual)
	 * 
	 * @return InformeDescuentosVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getDescuentosRealizados() throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "SELECT TDTCOD, TDTFECHA, SUM(TDTMONTO) \"MONTO\", COUNT(1) \"FILAS\" "
				+ " FROM "
				+ bonifDatabase
				+ ".BF24F1 GROUP BY TDTCOD, TDTFECHA "
				+ " ORDER BY TDTCOD DESC";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				InformeDescuentosVO inf = new InformeDescuentosVO();
				inf.setCodigoDescuento(ors.getLong("TDTCOD"));
				inf.setFechaDescuento(ors.getDate("TDTFECHA"));
				inf.setMontoTotal(ors.getInt("MONTO"));
				inf.setFilas(ors.getInt("FILAS"));
				retorno.add(inf);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene información de los codigos de descuento en los cuales el socio ha
	 * enido descuento. (Los cuales se realizan en forma mensual)
	 * 
	 * @param String
	 *            rutSocio
	 * @return ArrayList de InformeDescuentosVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getDescuentosRealizadosBySocio(String rutSocio)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "SELECT TDTCOD, TDTFECHA, SUM(TDTMONTO) \"MONTO\", COUNT(1) \"FILAS\" "
				+ " FROM "
				+ bonifDatabase
				+ ".BF24F1 "
				+ " WHERE   SOCRUT = ?"
				+ " GROUP BY TDTCOD, TDTFECHA" + " ORDER BY TDTCOD DESC";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			if(rutSocio.length()<8)
				rutSocio = "0" + rutSocio;
			stmt.setString(1, rutSocio);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				InformeDescuentosVO inf = new InformeDescuentosVO();
				inf.setCodigoDescuento(ors.getLong("TDTCOD"));
				inf.setFechaDescuento(ors.getDate("TDTFECHA"));
				inf.setMontoTotal(ors.getInt("MONTO"));
				inf.setFilas(ors.getInt("FILAS"));
				retorno.add(inf);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene información de los codigos de descuento en los cuales el socio ha
	 * enido descuento. (Los cuales se realizan en forma mensual)
	 * 
	 * @param String
	 *            rutSocio
	 * @return ArrayList de InformeDescuentosVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getDescuentosRealizadosBySocioEnPeriodo(String rutSocio,
			long codigoDescuento) throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "SELECT TDTCOD, TDTFECHA, SUM(TDTMONTO) \"MONTO\", COUNT(1) \"FILAS\" "
				+ " FROM "
				+ bonifDatabase
				+ ".BF24F1 "
				+ " WHERE   SOCRUT = ? and TDTCOD = ? "
				+ " GROUP BY TDTCOD, TDTFECHA" + " ORDER BY TDTCOD DESC";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, rutSocio);
			stmt.setLong(2, codigoDescuento);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				InformeDescuentosVO inf = new InformeDescuentosVO();
				inf.setCodigoDescuento(ors.getLong("TDTCOD"));
				inf.setFechaDescuento(ors.getDate("TDTFECHA"));
				inf.setMontoTotal(ors.getInt("MONTO"));
				inf.setFilas(ors.getInt("FILAS"));
				retorno.add(inf);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene información de los codigos de descuento generados y la fecha de
	 * descuento. (Los cuales se realizan en forma mensual) Esta información
	 * corresponde a los cobros que se debe realizar a los convenios (es para
	 * los convenios en los cuales ellos pagan el co-pago del socio para ciertas
	 * prestaciones.
	 * 
	 * @param
	 * @return InformeDescuentosConveniosVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getDescuentosConvenios() throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "SELECT TDTCOD, DUCFECPRO, SUM(DUCAPOCON) \"MONTO\", COUNT(1) \"FILAS\" "
				+ " FROM "
				+ bonifDatabase
				+ ".BF20F1 WHERE DUCAPOCON > 0 GROUP BY TDTCOD, DUCFECPRO";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				InformeDescuentosConveniosVO inf = new InformeDescuentosConveniosVO();
				inf.setCodigoDescuento(ors.getLong("TDTCOD"));
				inf.setFechaDescuento(ors.getDate("DUCFECPRO"));
				inf.setMontoTotal(ors.getInt("MONTO"));
				inf.setFilas(ors.getInt("FILAS"));
				retorno.add(inf);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene los montos que se deben descontar a cada convenio luego del
	 * proceso de descuento. (Los cuales se realizan en forma mensual) Esta
	 * información corresponde a los cobros que se debe realizar a los convenios
	 * (es para los convenios en los cuales ellos pagan el co-pago del socio
	 * para ciertas prestaciones.
	 * 
	 * @param long
	 *            codigoConvenio
	 * @param long
	 *            codigoDescuento
	 * @return ArrayList de DetalleDescuentosConveniosVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getConveniosConDescuentos(long codigoConvenio,
			long codigoDescuento) throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;
		boolean filtarPorConvenio = false;

		command = "SELECT " + "C.CONCOD, CONNOM, CONRUT, "
				+ "CONDV, DUCFECPRO, "
				+ "SUM(DUCAPOCON) \"MONTO\", COUNT(1) \"FILAS\" " + "FROM "
				+ bonifDatabase + ".BF20F1 A, " + bonifDatabase + ".BF03F1 B, "
				+ bonifDatabase + ".BF05F1 C " + "WHERE "
				+ "A.CASID = B.CASID " + "AND B.CONCOD = C.CONCOD "
				+ "AND DUCAPOCON > 0 " + "AND TDTCOD = ?";

		if (codigoConvenio != 0) {
			command = command + " AND B.CONCOD = ?";
			filtarPorConvenio = true;
		}

		command = command
				+ " GROUP BY C.CONCOD, CONNOM, CONRUT, CONDV, DUCFECPRO";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoDescuento);
			if (filtarPorConvenio)
				stmt.setLong(2, codigoConvenio);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				DetalleDescuentosConveniosVO inf = new DetalleDescuentosConveniosVO();
				inf.setCodigoConvenio(ors.getLong("CONCOD"));
				inf.setConvenio(ors.getString("CONNOM"));
				inf.setRut(ors.getString("CONRUT"));
				inf.setDv(ors.getString("CONDV"));
				inf.setFechaDescuento(ors.getDate("DUCFECPRO"));
				inf.setMontoTotal(ors.getInt("MONTO"));
				inf.setFilas(ors.getInt("FILAS"));
				retorno.add(inf);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Genera el detalle del descuento que se debe realizar a un convenio
	 * 
	 * @param codigo
	 *            de convenio
	 * @param long
	 *            codigoDescuento
	 * @return ArrayList de DetalleCasosDescontadosConvenioVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getDetalleDescuentosConvenio(long codigoConvenio,
			long codigoDescuento) throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "SELECT " + "A.SOCRUT, C.DV SOCDV, c.FNNOMBRES SOCNOMBRE, "
				+ "c.FNAPELLPAT SOCAPEPAT, c.FNAPELLMAT SOCAPEMAT, DCAAPOCON, "
				+ "CASFECOCU, A.CASID, COBGLS " + "FROM " + bonifDatabase
				+ ".BF20F1 A, " + bonifDatabase + ".BF03F1 B, " + bonifDatabase
				+ ".FUNCIONARIOS C, " + bonifDatabase + ".BF09F1 D, "
				+ bonifDatabase + ".BF04F1 E " + "WHERE " + "A.SOCRUT = C.RUT "
				+ "AND A.CASID = B.CASID " + "AND B.CASID = D.CASID "
				+ "AND D.COBCOD = E.COBCOD " + "AND DUCAPOCON > 0 "
				+ "AND DCAAPOCON > 0 " + "AND TDTCOD = ? " + "AND B.CONCOD = ?";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoDescuento);
			stmt.setLong(2, codigoConvenio);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				DetalleCasosDescontadosConvenioVO inf = new DetalleCasosDescontadosConvenioVO();
				inf.setRut(ors.getString("SOCRUT"));
				inf.setDv(ors.getString("SOCDV"));
				inf.setNombre(ors.getString("SOCNOMBRE"));
				inf.setApellidoPaterno(ors.getString("SOCAPEPAT"));
				inf.setApellidoMaterno(ors.getString("SOCAPEMAT"));
				inf.setAporteConvenio(ors.getInt("DCAAPOCON"));
				inf.setFechaOcurrencia(ors.getDate("CASFECOCU"));
				inf.setCasoID(ors.getLong("CASID"));
				inf.setDetalleCobertura(ors.getString("COBGLS"));

				retorno.add(inf);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene información de las oficinas que presentan descuento en sus
	 * empleados para el codigo de descuento pasado como parametro
	 * 
	 * @param long
	 *            codigoDescuento, String codigoOficina
	 * @return DetalleDescuentosOficinaVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getOficinasInformeDescuentos(long codigoDescuento,
			String codigoOficina) throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;
		boolean filtarPorOficina = false;

		command = "SELECT b.FNLPAGO SOCOFICINA, SUM(TDTMONTO) \"MONTO\", COUNT(1) \"FILAS\""
				+ "FROM "
				+ bonifDatabase
				+ ".BF24F1 A, "
				+ bonifDatabase
				+ ".FUNCIONARIOS B WHERE A.SOCRUT " + "= B.RUT AND TDTCOD = ?";

		if (codigoOficina != null && codigoOficina.length() > 0) {
			command = command + " AND b.FNLPAGO = ?";
			filtarPorOficina = true;
		}

		command = command + " GROUP BY b.FNLPAGO";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			stmt.setLong(1, codigoDescuento);
			if (filtarPorOficina)
				stmt.setString(2, codigoOficina);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				DetalleDescuentosOficinaVO oficina = new DetalleDescuentosOficinaVO();
				oficina.setCodigoOficina(ors.getString("SOCOFICINA"));
				oficina.setMontoTotal(ors.getInt("MONTO"));
				oficina.setFilas(ors.getInt("FILAS"));
				oficina.setCodigoDescuento(codigoDescuento);
				retorno.add(oficina);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene información de los totales descontados a cada socio que presenta
	 * descuento para el codigo de descuento pasado como parametro
	 * 
	 * @param long
	 *            codigoDescuento, String rut, String oficina
	 * @return ArrayList de DetalleDescuentosSocioVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getSociosInformeDescuentos(long codigoDescuento,
			String rut, String oficina) throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		boolean filtarPorRut = false;
		boolean filtarPorOficina = false;
		int contador = 0;
		String command;

		command = "SELECT A.SOCRUT, B.DV SOCDV,b.FNNOMBRES SOCNOMBRE,b.FNAPELLPAT SOCAPEPAT,b.FNAPELLMAT SOCAPEMAT,"
				+ "B.FNLPAGO SOCOFICINA, b.FNCODCARG SOCCARG, TDTMONTO, TDTFECHA, c.CGCOD3 FROM "
				+ bonifDatabase
				+ ".BF24F1 A,"
				+ bonifDatabase
				+ ".FUNCIONARIOS B, "
				+ personalDatabase
				+ ".APLCG01 C "
				+ "WHERE A.SOCRUT = B.RUT "
				+ "AND b.FNCODCARG = CGCODCARG "
				+ "AND TDTCOD = ? ";

		if (rut != null && rut.length() > 0) {
			
			if(rut.length()<8)//Si el rut es menor que 8 se agrega un cero al inico ya que en la BD mantienen este formato
				rut = "0" + rut;
			
			filtarPorRut = true;
			command = command + " AND A.SOCRUT = ?";
		}

		if (oficina != null && oficina.length() > 0) {
			filtarPorOficina = true;
			command = command + " AND B.FNLPAGO = ?";
		}

		command = command + " ORDER BY C.CGCOD3, b.FNCODCARG, A.SOCRUT";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			contador++;
			stmt.setLong(contador, codigoDescuento);
			if (filtarPorRut) {
				contador++;
				stmt.setString(contador, rut);
			}
			if (filtarPorOficina) {
				contador++;
				stmt.setString(contador, oficina);
			}

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				DetalleDescuentosSocioVO soc = new DetalleDescuentosSocioVO();
				soc.setRut(ors.getString("SOCRUT"));
				soc.setDv(ors.getString("SOCDV"));
				soc.setNombre(ors.getString("SOCNOMBRE"));
				soc.setApellidoPaterno(ors.getString("SOCAPEPAT"));
				soc.setApellidoMaterno(ors.getString("SOCAPEMAT"));
				soc.setCodigoOficina(ors.getString("SOCOFICINA"));
				soc.setCodCargo(ors.getString("SOCCARG"));
				soc.setMontoDescuentoTotal(ors.getInt("TDTMONTO"));
				soc.setFecha(ors.getDate("TDTFECHA"));
				soc.setCodDepartamento(ors.getString("CGCOD3"));
				retorno.add(soc);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
 			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene información de los casos descontados a cada socio 2005.05.18
	 * Tambien obtiene información del detalle del caso que presenta descuento
	 * para el codigo de descuento pasado como parametro
	 * 
	 * @param long
	 *            codigoDescuento
	 * @param String
	 *            rut
	 * @param String
	 *            oficina
	 * @return ArrayList de CasosDescontadosSinFormatoVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getCasosInformeDescuentos(long codigoDescuento,
			String rut, String oficina) throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		boolean filtarPorRut = false;
		boolean filtarPorOficina = false;
		int contador = 0;
		String command;

		command = "SELECT " + "		A.SOCRUT, A.CASID, DUCMONTO, DUCCUONUM,"
				+ "		DUCCUOTOT, B.CONCOD, CONNOM, DUCAPOCON,"
				+ "		COBGLS, DCAAPOSOC, DCACNTOCU " + " FROM " + bonifDatabase
				+ ".BF20F1 A," + bonifDatabase + ".BF03F1 B," + bonifDatabase
				+ ".BF05F1 C," + bonifDatabase + ".FUNCIONARIOS D,"
				+ bonifDatabase + ".BF09F1 E," + bonifDatabase + ".BF04F1 F"
				+ " WHERE " + "	A.CASID = B.CASID AND "
				+ "   B.CONCOD = C.CONCOD	AND " + "   A.SOCRUT = D.RUT	AND "
				+ "   E.CASID = B.CASID AND " + "   E.COBCOD = F.COBCOD	AND "
				+ "   DUCMONTO > 0 AND " + "   TDTCOD = ?";

		if (rut != null && rut.length() > 0) {
			filtarPorRut = true;
			command = command + " AND A.SOCRUT = ?";
		}

		if (oficina != null && oficina.length() > 0) {
			filtarPorOficina = true;
			command = command + " AND D.FNLPAGO = ?";
		}

		command = command + " ORDER BY A.SOCRUT, A.CASID";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			contador++;
			stmt.setLong(contador, codigoDescuento);
			if (filtarPorRut) {
				contador++;
				stmt.setString(contador, rut);
			}
			if (filtarPorOficina) {
				contador++;
				stmt.setString(contador, oficina);
			}

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				CasosDescontadosSinFormatoVO casos = new CasosDescontadosSinFormatoVO();
				casos.setRut(ors.getString("SOCRUT"));
				casos.setCasoId(ors.getLong("CASID"));
				casos.setMontoDescuento(ors.getInt("DUCMONTO"));
				casos.setCuotaActual(ors.getInt("DUCCUONUM"));
				casos.setNumeroCuotas(ors.getInt("DUCCUOTOT"));
				casos.setCodigoConvenio(ors.getLong("CONCOD"));
				casos.setConvenio(ors.getString("CONNOM"));
				casos.setAporteConvenio(ors.getInt("DUCAPOCON"));
				casos.setDetalleDescripcion(ors.getString("COBGLS"));
				casos.setDetalleAporteSocio(ors.getInt("DCAAPOSOC"));
				casos.setCantidadOcurencias(ors.getInt("DCACNTOCU"));

				retorno.add(casos);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene información de los codigos de pagos generados y la fecha de pago.
	 * (Los cuales se realizan en forma mensual)
	 * 
	 * @return InformePagoConvenioVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getPagosRealizados() throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "SELECT RPCCOD, RPCFECHA, SUM(RPCMONTO) \"MONTO\", COUNT(1) \"FILAS\" "
				+ "FROM "
				+ bonifDatabase
				+ ".BF26F1 GROUP BY RPCCOD, RPCFECHA " + "ORDER BY RPCCOD DESC";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				InformePagoConvenioVO inf = new InformePagoConvenioVO();
				inf.setCodigoPago(ors.getLong("RPCCOD"));
				inf.setFechaPago(ors.getDate("RPCFECHA"));
				inf.setMontoTotal(ors.getInt("MONTO"));
				inf.setFilas(ors.getInt("FILAS"));
				retorno.add(inf);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene información de los convenios que presentan pago para el codigo de
	 * pago pasado como parametro
	 * 
	 * @param long
	 *            codigoPago
	 * @return DetallePagoConvenioVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getConveniosInformePago(long codigoPago) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "SELECT B.CONCOD, CONNOM, CONRUT, CONDV, "
				+ "RPCFOLBIE, RPCMONTO " + "FROM " + bonifDatabase
				+ ".BF26F1 A, " + bonifDatabase + ".BF05F1 B "
				+ "WHERE B.CONCOD = A.CONCOD " + "AND RPCCOD = ?";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			stmt.setLong(1, codigoPago);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				DetallePagoConvenioVO convenio = new DetallePagoConvenioVO();
				convenio.setCodigoConvenio(ors.getLong("CONCOD"));
				convenio.setNombreConvenio(ors.getString("CONNOM"));
				convenio.setRut(ors.getString("CONRUT"));
				convenio.setDv(ors.getString("CONDV"));
				convenio.setFolioTesoreria(ors.getLong("RPCFOLBIE"));
				convenio.setMonto(ors.getInt("RPCMONTO"));

				retorno.add(convenio);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene información de los pagos que se deben realizar a los convenios
	 * para compras con cuota
	 * 
	 * @param long
	 *            codigoDescuento
	 * @return ArrayList PagoConvenioPendienteCuotaVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getDetalleCasosPagoConvenioPendientesConCuotas(
			long codigoDescuento) throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "SELECT B.CASID, B.CONCOD, CONNOM, C.CONRUT, "
				+ "CONDV, TCOTESAREA, TCOTESCOEG, DUCMONTO, "
				+ "CASCUOCONV, CASCUOBIE, DUCCUONUM, "
				+ "(CASMONTO - CASDSCTO - CASAPOISA) \"MONTO\", "
				+ "CASAPOBIE " + "FROM " + bonifDatabase + ".BF20F1 A, "
				+ bonifDatabase + ".BF03F1 B, " + bonifDatabase + ".BF05F1 C, "
				+ bonifDatabase + ".BF14F1 D " + "WHERE "
				+ "A.CASID = B.CASID " + "AND B.CONCOD = C.CONCOD "
				+ "AND C.TCOCOD = D.TCOCOD " + "AND CASBONO = ? "
				+ "AND TDTCOD = ? " + "AND DUCCUOTOT >1 " + "ORDER BY B.CONCOD";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			stmt.setString(1, Caso.TIPOBONO_NO);
			stmt.setLong(2, codigoDescuento);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				PagoConvenioPendienteCuotaVO vo = new PagoConvenioPendienteCuotaVO();
				vo.setCasid(ors.getLong("CASID"));
				vo.setCodigoConvenio(ors.getLong("CONCOD"));
				vo.setCuotaDescontada(ors.getInt("DUCCUONUM"));
				vo.setMontoCuota(ors.getInt("DUCMONTO"));
				vo.setMontoTotal(ors.getInt("MONTO"));
				vo.setNumeroCuotasBienestar(ors.getInt("CASCUOBIE"));
				vo.setNumeroCuotasConvenio(ors.getInt("CASCUOCONV"));
				vo.setNombreConvenio(ors.getString("CONNOM"));
				vo.setRut(ors.getString("CONRUT"));
				vo.setDv(ors.getString("CONDV"));
				vo.setArea(ors.getLong("TCOTESAREA"));
				vo.setConceptoEgreso(ors.getLong("TCOTESCOEG"));
				vo.setAporteBienestar(ors.getInt("CASAPOBIE"));

				retorno.add(vo);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene información de los pagos que se deben realizar a los convenios
	 * para compras sin cuota
	 * 
	 * @param long
	 *            codigoDescuento
	 * @return ArrayList PagoConvenioPendienteVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getPagoConvenioPendientesSinCuotas(long codigoDescuento)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "SELECT B.CONCOD, CONNOM, C.CONRUT, CONDV, TCOTESAREA, "
				+ "TCOTESCOEG, SUM((CASMONTO - CASDSCTO - CASAPOISA)) \"MONTO\" "
				+ "FROM "
				+ bonifDatabase
				+ ".BF20F1 A, "
				+ bonifDatabase
				+ ".BF03F1 B, "
				+ bonifDatabase
				+ ".BF05F1 C, "
				+ bonifDatabase
				+ ".BF14F1 D "
				+ "WHERE "
				+ "A.CASID = B.CASID "
				+ "AND B.CONCOD = C.CONCOD "
				+ "AND C.TCOCOD = D.TCOCOD "
				+ "AND CASBONO = ? "
				+ "AND TDTCOD = ? "
				+ "AND DUCCUOTOT <=1 "
				+ "GROUP BY B.CONCOD, CONNOM, C.CONRUT, CONDV, TCOTESAREA, TCOTESCOEG "
				+ "ORDER BY B.CONCOD";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			stmt.setString(1, Caso.TIPOBONO_NO);
			stmt.setLong(2, codigoDescuento);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				PagoConvenioPendienteVO convenio = new PagoConvenioPendienteVO();
				convenio.setCodigoConvenio(ors.getLong("CONCOD"));
				convenio.setNombreConvenio(ors.getString("CONNOM"));
				convenio.setRut(ors.getString("CONRUT"));
				convenio.setDv(ors.getString("CONDV"));
				convenio.setMonto(ors.getInt("MONTO"));
				convenio.setArea(ors.getLong("TCOTESAREA"));
				convenio.setConceptoEgreso(ors.getLong("TCOTESCOEG"));

				retorno.add(convenio);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene información de los pagos que se deben realizar a los convenios
	 * para compras con cuota
	 * 
	 * @param long
	 *            codigoDescuento
	 * @return ArrayList PagoConvenioPendienteCuotaVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getPagoConvenioPendientesConCuotas(long codigoDescuento)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "SELECT B.CONCOD, CONNOM, C.CONRUT, "
				+ "CONDV, TCOTESAREA, TCOTESCOEG, DUCMONTO, "
				+ "CASCUOCONV, CASCUOBIE, DUCCUONUM, "
				+ "(CASMONTO - CASDSCTO - CASAPOISA) \"MONTO\", "
				+ "CASAPOBIE " + "FROM " + bonifDatabase + ".BF20F1 A, "
				+ bonifDatabase + ".BF03F1 B, " + bonifDatabase + ".BF05F1 C, "
				+ bonifDatabase + ".BF14F1 D " + "WHERE "
				+ "A.CASID = B.CASID " + "AND B.CONCOD = C.CONCOD "
				+ "AND C.TCOCOD = D.TCOCOD " + "AND CASBONO = ? "
				+ "AND TDTCOD = ? " + "AND DUCCUOTOT >1 " + "ORDER BY B.CONCOD";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			stmt.setString(1, Caso.TIPOBONO_NO);
			stmt.setLong(2, codigoDescuento);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				PagoConvenioPendienteCuotaVO vo = new PagoConvenioPendienteCuotaVO();

				vo.setCodigoConvenio(ors.getLong("CONCOD"));
				vo.setCuotaDescontada(ors.getInt("DUCCUONUM"));
				vo.setMontoCuota(ors.getInt("DUCMONTO"));
				vo.setMontoTotal(ors.getInt("MONTO"));
				vo.setNumeroCuotasBienestar(ors.getInt("CASCUOBIE"));
				vo.setNumeroCuotasConvenio(ors.getInt("CASCUOCONV"));
				vo.setNombreConvenio(ors.getString("CONNOM"));
				vo.setRut(ors.getString("CONRUT"));
				vo.setDv(ors.getString("CONDV"));
				vo.setArea(ors.getLong("TCOTESAREA"));
				vo.setConceptoEgreso(ors.getLong("TCOTESCOEG"));
				vo.setAporteBienestar(ors.getInt("CASAPOBIE"));

				retorno.add(vo);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene información de los codigos de descuento que aún no se han pagado
	 * 
	 * @return PagoPendienteVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getPagosPorRealizar() throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "SELECT DISTINCT(TDTCOD), "
				+ "(select min(tdtfecha) from bfdta.bf24f1 b where b.tdtcod = a.tdtcod) as tdtfecha "
				+ "FROM " + bonifDatabase + ".BF24F1 A "
				+ "WHERE TDTCOD NOT IN (SELECT DISTINCT(PCOCOD) FROM "
				+ bonifDatabase + ".BF19F1)";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				PagoPendienteVO pago = new PagoPendienteVO();
				pago.setCodigoDescuento(ors.getLong("TDTCOD"));
				pago.setFechaDescuento(ors.getDate("TDTFECHA"));

				retorno.add(pago);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Registra el codigo del descuento o del reembolso que se considera en la
	 * generación de los asientos contables
	 * 
	 */
	public void insertInformacionAsientos(InformacionAsiento datos)
			throws Exception, BusinessException {

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "INSERT INTO " + bonifDatabase
				+ ".BF25F1 (TACTIPO, TACCODIGO, "
				+ "TACFECHA, TACSEQ, TACUSUARIO) VALUES(?,?,?,?,?)";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			stmt.setString(1, datos.getTipo());
			stmt.setLong(2, datos.getCodigo());
			stmt.setTimestamp(3, new java.sql.Timestamp(datos.getFecha()
					.getTime()));
			stmt.setLong(4, datos.getSecuencia());
			stmt.setString(5, datos.getUsuario());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Obtiene información de los periodos que aún no se han contabilizado
	 * 
	 * @param String
	 *            tipo
	 * @return ContabilidadPendienteVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getPeriodosPorContabilizar(String tipo) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "SELECT DISTINCT(TDTCOD),MAX(TDTFECHA) TDTFECHA FROM "
				+ bonifDatabase + ".BF24F1 "
				+ "WHERE TDTCOD NOT IN (SELECT DISTINCT(TACCODIGO) FROM "
				+ bonifDatabase + ".BF25F1 "
				+ "WHERE TACTIPO = ?) group by tdtcod ";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, tipo);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				ContabilidadPendienteVO pendiente = new ContabilidadPendienteVO();
				pendiente.setCodigoDescuento(ors.getLong("TDTCOD"));
				pendiente.setFechaDescuento(ors.getDate("TDTFECHA"));
				retorno.add(pendiente);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene el monto total descontado por bienestar en el proceso de
	 * descuento or concepto de prestamos para el proceso de descuento
	 * consultado.
	 * 
	 * @param long
	 *            codigoDescuento
	 * @return int montoDescuento
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int getMontoTotalDescuentoPrestamos(long codigoDescuento)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;
		int montoTotalDescuento = 0;

		command = "SELECT SUM(CPRMTOCUO) \"MONTO\" " + "FROM " + bonifDatabase
				+ ".BF21F1 " + "WHERE TDTCOD = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoDescuento);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			if (ors.next()) {
				montoTotalDescuento = ors.getInt("MONTO");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return montoTotalDescuento;
	}

	/**
	 * Obtiene el monto total descontado por bienestar en el proceso de
	 * descuento consultado.
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int getMontoTotalDescuento(long codigoDescuento) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;
		int montoTotalDescuento = 0;

		// command = "SELECT SUM(DUCMONTO+DUCAPOCON) \"MONTO\" "+
		// "FROM "+
		// bonifDatabase+".BF20F1 "+
		// "WHERE TDTCOD = ?";

		// ahora no se debe contabilizar el aporte del convenio
		command = "SELECT SUM(DUCMONTO) \"MONTO\" " + "FROM " + bonifDatabase
				+ ".BF20F1 " + "WHERE TDTCOD = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoDescuento);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			if (ors.next()) {
				montoTotalDescuento = ors.getInt("MONTO");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return montoTotalDescuento;
	}

	/**
	 * Obtiene el monto total que se le debe a un convenio para un periodo de
	 * descuento consultado
	 * 
	 * @param long
	 *            codigoDescuento
	 * @return ContabilidadVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getAcreedoresConvenios(long codigoDescuento)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;
		command = "SELECT B.CONCOD, CONNOM, CONRUT, "
				+ "CONDV, TCOCTAACRE, TCOCTADEU,E.FNNOMBRES SOCNOMBRE, "
				+ "e.FNAPELLPAT SOCAPEPAT, E.FNAPELLMAT SOCAPEMAT, E.RUT SOCRUT, E.DV SOCDV, CASNUMDOC, "
				+ "SUM(CASMONTO-CASDSCTO-CASAPOISA) \"MONTO\" " + "FROM "
				+ bonifDatabase + ".BF20F1 A," + bonifDatabase + ".BF03F1 B,"
				+ bonifDatabase + ".BF05F1 C," + bonifDatabase + ".BF14F1 D,"
				+ bonifDatabase + ".FUNCIONARIOS E " + "WHERE "
				+ "TDTCOD = ? AND " + "DUCCUONUM <= 1 AND "
				+ "A.CASID = B.CASID AND " + "CASBONO = ? AND "
				+ "B.CONCOD = C.CONCOD AND " + "C.TCOCOD = D.TCOCOD AND "
				+ "B.SOCRUT = E.RUT "
				+ "GROUP BY B.CONCOD, CONNOM, CONRUT, CONDV,"
				+ "TCOCTAACRE, TCOCTADEU, E.FNNOMBRES, "
				+ "E.FNAPELLPAT, E.FNAPELLMAT, E.RUT, E.DV, " + "CASNUMDOC";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoDescuento);
			stmt.setString(2, Caso.TIPOBONO_NO);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				ContabilidadVO acreedores = new ContabilidadVO();
				acreedores.setCodigoConvenio(ors.getLong("CONCOD"));
				acreedores.setNombreConvenio(ors.getString("CONNOM"));
				acreedores.setRutConvenio(ors.getString("CONRUT"));
				acreedores.setDvConvenio(ors.getString("CONDV"));
				acreedores.setTotal(ors.getInt("MONTO"));
				acreedores.setDocumento(ors.getString("CASNUMDOC"));
				acreedores.setCuentaAcreedor(String.valueOf(ors
						.getLong("TCOCTAACRE")));
				acreedores.setCuentaDeudor(String.valueOf(ors
						.getLong("TCOCTADEU")));
				acreedores.setNombre(ors.getString("SOCNOMBRE"));
				acreedores.setApePat(ors.getString("SOCAPEPAT"));
				acreedores.setApeMat(ors.getString("SOCAPEMAT"));
				acreedores.setRutSocio(ors.getString("SOCRUT"));
				acreedores.setDvSocio(ors.getString("SOCDV"));

				retorno.add(acreedores);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene la información de los descuentos pendientes del socio
	 * 
	 * @param String
	 *            rut
	 * @return DescuentoPendienteSocioVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getDescuentosPendientesSocio(String rut) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "SELECT CASEST,CASID,CASMONTO,CASCUOBIE,CASCUOCONV,CURRENT TIMESTAMP \"FECHA\", "
				+ "0 \"CUOTA\",CASMONTO \"SALDO\" FROM "
				+ bonifDatabase
				+ ".BF03F1 WHERE SOCRUT = ? AND CASTIPO = ? "
				+ "AND CASINDDES <> ? AND (CASEST = ? OR CASEST = ? ) AND CASCUOBIE < 1 AND CASCUOCONV < 1 "
				+ "UNION "
				+ "SELECT A.CASEST,A.CASID,CASMONTO,CASCUOBIE,CASCUOCONV,MIN(CUOFECVCTO) \"FECHA\",MIN(CUONUM) \"CUOTA\","
				+ "SUM(CUOVALOR) \"SALDO\" FROM "
				+ bonifDatabase
				+ ".BF03F1 A,"
				+ bonifDatabase
				+ ".BF08F1 B "
				+ "WHERE CASTIPO = ? AND (CASEST = ? OR CASEST = ? ) AND CASINDDES <> ? AND A.CASID = B.CASID AND CUOEST = ? AND SOCRUT = ? "
				+ "GROUP BY A.CASID,CASMONTO,CASCUOBIE, CASCUOCONV,A.CASEST";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, rut);
			stmt.setString(2, Caso.TIPO_DESCUENTO);
			stmt.setString(3, Caso.ESTADOINDICADOR_SI);
			stmt.setString(4, Caso.STD_ACTIVO);
			stmt.setString(5, Caso.STD_PRECASO); // ----
			stmt.setString(6, Caso.TIPO_DESCUENTO);
			stmt.setString(7, Caso.STD_ACTIVO);
			stmt.setString(8, Caso.STD_PRECASO); // ----
			stmt.setString(9, Caso.ESTADOINDICADOR_SI);
			stmt.setString(10, Cuota.STD_NO_DESCONTADA);
			stmt.setString(11, rut);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				DescuentoPendienteSocioVO descuento = new DescuentoPendienteSocioVO();
				descuento.setCasoId(ors.getLong("CASID"));
				descuento.setMontoTotal(ors.getInt("CASMONTO"));
				descuento.setNumeroCuotasBienestar(ors.getInt("CASCUOBIE"));
				descuento.setNumeroCuotasConvenio(ors.getInt("CASCUOCONV"));
				descuento.setFechaCobroCuotaPendiente(ors.getDate("FECHA"));
				descuento.setCuotaPendiente(ors.getInt("CUOTA"));
				descuento.setSaldoPendiente(ors.getInt("SALDO"));
				descuento.setTipoCaso(ors.getString("CASEST"));

				retorno.add(descuento);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene el monto total que bienestar aportado durante un mes para cada
	 * tipo de prestacion que presenta movimientos en el mes consultado
	 * 
	 * @param codigoDescuento
	 * @param ArrayList
	 *            codigosReembolso
	 * @param Date
	 *            fechaInicio, Date fechaFin
	 * @return ArrayList de ContabilidadVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getAportesBienestar(long codigoDescuento,
			ArrayList codigosReembolso, Date fechaInicio, Date fechaFin)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		String codigosRembolsoSql = null;
		for (int x = 0; x < codigosReembolso.size(); x++) {
			if (x == 0)
				codigosRembolsoSql = "?";
			else
				codigosRembolsoSql = codigosRembolsoSql + ",?";
		}

		if (codigosRembolsoSql == null)
			codigosRembolsoSql = "0";

		// 2006.03.17 asepulveda
		// Ahora tambien se suman los aportes de bienestar en los casos que se
		// pagan anticipadamente
		// con prestamo o abono,en los cuales no se realiza un descuento.

		command = "SELECT COBCTAGSTO, " + 
				  "SUM(ABIMONTO) \"MONTO\" " + 
				  "FROM " + 
				  bonifDatabase + ".BF17F1 A, " + 
				  bonifDatabase + ".BF04F1 B "	+ 
				  "WHERE A.CASID IN(" + 
				  	"SELECT DISTINCT(CASID) FROM ( " + 
				  		"(	SELECT	CASID " + "FROM " + bonifDatabase + ".BF20F1 " + 
				  			"WHERE	TDTCOD = ? " + "AND	DUCCUONUM <= 1" +
				  		") " +
				  		"UNION " + 
				  		"(	SELECT	CASID " + "FROM " + bonifDatabase + ".BF18F1 "+ 
				  			"WHERE	TRECOD IN (" + codigosRembolsoSql + ")" +
				  		")" + 
				  		"UNION "+ 
				  		"(	SELECT	CASID " + "FROM " + bonifDatabase + ".BF03F1 "+ 
				  			"WHERE	(CASPRSTAMO+CASABONO) = CASAPOSOC " + 
				  			"AND	(CASPRSTAMO > 0 OR CASABONO >0) " + 
				  			"AND	CASFECEST BETWEEN ? AND ? " + "AND	CASEST = ? )" + 
				  		") " + 
				  	"AS		UTABLE) " + 
				  	"AND	A.COBCOD = B.COBCOD "+ 
				  	"GROUP BY	COBCTAGSTO " + "HAVING SUM(ABIMONTO) > 0 "+
				  	"ORDER BY COBCTAGSTO";

		ArrayList retorno = new ArrayList();
		int contador = 0;

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			contador++;
			stmt.setLong(contador, codigoDescuento);
			for (int x = 0; x < codigosReembolso.size(); x++) {
				InformacionAsiento inf = (InformacionAsiento) codigosReembolso
						.get(x);
				contador++;
				stmt.setLong(contador, inf.getCodigo());
			}

			contador++;
			stmt.setTimestamp(contador, new java.sql.Timestamp(fechaInicio
					.getTime()));
			contador++;
			stmt.setTimestamp(contador, new java.sql.Timestamp(fechaFin
					.getTime()));
			contador++;
			stmt.setString(contador, Caso.STD_CERRADO);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				ContabilidadVO aporteBienestar = new ContabilidadVO();
				aporteBienestar.setAporteBienestar(ors.getInt("MONTO"));
				aporteBienestar.setCuentaGasto(String.valueOf(ors
						.getLong("COBCTAGSTO")));

				retorno.add(aporteBienestar);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene el monto total que bienestar de bonificaciones especiales 
	 * aportado durante un mes para cada
	 * tipo de prestacion que presenta movimientos en el mes consultado
	 * 
	 * @param codigoDescuento
	 * @param ArrayList
	 *            codigosReembolso
	 * @param Date
	 *            fechaInicio, Date fechaFin
	 * @return ArrayList de ContabilidadVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getAportesBienestar_BonificacionEspecial(long codigoDescuento,
			ArrayList codigosReembolso, Date fechaInicio, Date fechaFin)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		String codigosRembolsoSql = null;
		for (int x = 0; x < codigosReembolso.size(); x++) {
			if (x == 0)
				codigosRembolsoSql = "?";
			else
				codigosRembolsoSql = codigosRembolsoSql + ",?";
		}

		if (codigosRembolsoSql == null)
			codigosRembolsoSql = "0";

		command = "SELECT COBCTAGSTO, " + 
				  "SUM(ABIMONTO) \"MONTO\" " + 
				  "FROM " + 
				  bonifDatabase + ".BF34F1 A, " + 
				  bonifDatabase + ".BF04F1 B "	+ 
				  "WHERE A.CASID IN(" + 
				  	"SELECT DISTINCT(CASID) FROM ( " + 
				  		"(	SELECT	CASID " + "FROM " + bonifDatabase + ".BF20F1 " + 
				  			"WHERE	TDTCOD = ? " + "AND	DUCCUONUM <= 1" +
				  		") " +
				  		"UNION " + 
				  		"(	SELECT	CASID " + "FROM " + bonifDatabase + ".BF18F1 "+ 
				  			"WHERE	TRECOD IN (" + codigosRembolsoSql + ")" +
				  		")" + 
				  		"UNION "+ 
				  		"(	SELECT	CASID " + "FROM " + bonifDatabase + ".BF03F1 "+ 
				  			"WHERE	(CASPRSTAMO+CASABONO) = CASAPOSOC " + 
				  			"AND	(CASPRSTAMO > 0 OR CASABONO >0) " + 
				  			"AND	CASFECEST BETWEEN ? AND ? " + "AND	CASEST = ? )" + 
				  		") " + 
				  	"AS		UTABLE) " + 
				  	"AND	A.COBCOD = B.COBCOD "+ 
				  	"GROUP BY	COBCTAGSTO " + "HAVING SUM(ABIMONTO) > 0 "+
				  	"ORDER BY COBCTAGSTO";

		ArrayList retorno = new ArrayList();
		int contador = 0;

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			contador++;
			stmt.setLong(contador, codigoDescuento);
			for (int x = 0; x < codigosReembolso.size(); x++) {
				InformacionAsiento inf = (InformacionAsiento) codigosReembolso
						.get(x);
				contador++;
				stmt.setLong(contador, inf.getCodigo());
			}

			contador++;
			stmt.setTimestamp(contador, new java.sql.Timestamp(fechaInicio
					.getTime()));
			contador++;
			stmt.setTimestamp(contador, new java.sql.Timestamp(fechaFin
					.getTime()));
			contador++;
			stmt.setString(contador, Caso.STD_CERRADO);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				ContabilidadVO aporteBienestar = new ContabilidadVO();
				aporteBienestar.setAporteBienestar(ors.getInt("MONTO"));
				aporteBienestar.setCuentaGasto(String.valueOf(ors
						.getLong("COBCTAGSTO")));

				retorno.add(aporteBienestar);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}
	
	/**
	 * Obtiene información de los descuentos aplicados a un Socio
	 * 
	 * @return ArrayList de DescuentoAplicadosSocioVO
	 * @throws Exception
	 */
	public ArrayList getDescuentosAplicadosSocio(String rut) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "SELECT TDTCOD,SOCRUT, TDTFECHA, SUM(TDTMONTO) \"MONTO\" "
				+ "FROM " + bonifDatabase + ".BF24F1 WHERE SOCRUT = ? "
				+ "GROUP BY TDTCOD,SOCRUT, TDTFECHA " + "ORDER BY TDTCOD DESC";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, rut);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				DescuentoAplicadosSocioVO descuento = new DescuentoAplicadosSocioVO();
				descuento.setCodigoDescuento(ors.getLong("TDTCOD"));
				descuento.setFechaDescuento(ors.getDate("TDTFECHA"));
				descuento.setMontoTotal(ors.getInt("MONTO"));

				retorno.add(descuento);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene una lista con información de los casos que se encuentran abiertos
	 * o en borrador
	 * 
	 * @return ArrayList de CasoAbiertoVO
	 * @throws Exception
	 */
	public ArrayList getCasosAbiertos(String rut) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "SELECT CASID, CASEST " + "FROM " + bonifDatabase
				+ ".BF03F1 WHERE SOCRUT = ? AND (CASEST = ? OR CASEST = ?)";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, rut);
			stmt.setString(2, Caso.STD_ACTIVO);
			stmt.setString(3, Caso.STD_BORRADOR);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				CasoAbiertoVO caso = new CasoAbiertoVO();
				caso.setCasoId(ors.getLong("CASID"));
				caso.setEstadoActual(ors.getString("CASEST"));

				retorno.add(caso);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Cambia el estado y la fecha del estado de un caso
	 * 
	 * @param CasoAbiertoVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void finalizaCaso(CasoAbiertoVO caso) throws Exception,
			BusinessException {

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "UPDATE " + bonifDatabase
				+ ".BF03F1 SET CASEST = ?, CASFECEST = ? " + "WHERE CASID = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			stmt.setString(1, caso.getEstadoNuevo());
			stmt.setTimestamp(2, new java.sql.Timestamp(caso
					.getFechaEstadoNuevo().getTime()));
			stmt.setLong(3, caso.getCasoId());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Obtiene una lista con los casos cerrados por pago con prestamo
	 * 
	 * @param Date
	 *            fechaInicio, Date fechaFin
	 * @return ArrayList PagoConPrestamoVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getPagoConPrestamo(Date fechaInicio, Date fechaFin)
			throws Exception, BusinessException {

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "SELECT "
				+ "CASID, A.SOCRUT, B.DV SOCDV, "
				+ "C.CONCOD, CASNUMDOC, C.CONRUT, "
				+ "C.CONDV, C.CONNOM, "
				+ "(CASMONTO - CASDSCTO - CASAPOISA) \"CASMONTO\", "
				+ "CASAPOSOC, CASAPOBIE,CASPRSTAMO,"
				+ "CASBONO,CASABONO, CASNUMPTM, "
				+ "D.TCOCTAACRE, D.TCOCTADEU,	"
				+ "B.FNNOMBRES SOCNOMBRE,b.FNAPELLPAT SOCAPEPAT,b.FNAPELLMAT SOCAPEMAT "
				+ "FROM " + bonifDatabase + ".BF03F1 A," + bonifDatabase
				+ ".FUNCIONARIOS B," + bonifDatabase + ".BF05F1 C,"
				+ bonifDatabase + ".BF14F1 D " + "WHERE A.SOCRUT = B.RUT "
				+ "AND A.CONCOD = C.CONCOD " + "AND C.TCOCOD = D.TCOCOD "
				+ "AND (CASPRSTAMO > 0 OR CASABONO >0) "
				+ "AND CASFECEST BETWEEN ? AND ?";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setTimestamp(1, new java.sql.Timestamp(fechaInicio.getTime()));
			stmt.setTimestamp(2, new java.sql.Timestamp(fechaFin.getTime()));

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				PagoConPrestamoVO casos = new PagoConPrestamoVO();
				casos.setCasoId(ors.getLong("CASID"));
				casos.setRutSocio(ors.getString("SOCRUT"));
				casos.setDvSocio(ors.getString("SOCDV"));
				casos.setRutConvenio(ors.getString("CONRUT"));
				casos.setDvConvenio(ors.getString("CONDV"));
				casos.setNombreConvenio(ors.getString("CONNOM"));
				casos.setCodigoConvenio(ors.getLong("CONCOD"));
				casos.setDocumento(ors.getString("CASNUMDOC"));
				casos.setCuentaDeudor(ors.getString("TCOCTADEU"));
				casos.setCuentaAcreedor(ors.getString("TCOCTAACRE"));
				casos.setTipoBono(ors.getString("CASBONO"));
				casos.setTotal(ors.getInt("CASMONTO"));
				casos.setAporteBienestar(ors.getInt("CASAPOBIE"));
				casos.setAporteSocio(ors.getInt("CASAPOSOC"));
				casos.setMontoPrestamo(ors.getInt("CASPRSTAMO"));
				casos.setMontoAbono(ors.getInt("CASABONO"));
				casos.setNumeroPrestamo(ors.getInt("CASNUMPTM"));
				casos.setNombre(ors.getString("SOCNOMBRE"));
				casos.setApePat(ors.getString("SOCAPEPAT"));
				casos.setApeMat(ors.getString("SOCAPEMAT"));

				retorno.add(casos);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Verifica si el caso existe, si el caso existe retorna Caso, si no existe
	 * retorna null
	 * 
	 * @param
	 * @return Caso si existe, null si no existe
	 * @throws Exception
	 * @throws BusinessException
	 */
	public Caso getCasoByUpLoadFile(Caso caso) throws Exception,
			BusinessException {

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "SELECT "
				+ "CASID,SOCRUT,CARRUT,CONCOD,CASMONTO,CASDSCTO,CASAPOISA, "
				+ "CASAPOBIE,CASAPOSOC,CASFECING,CASFECEST,CASFECOCU,CASCUOCONV, "
				+ "CASCUOBIE,CASTIPO,CASEST,CASTIPDOC,CASNUMDOC,CASPRSTAMO, "
				+ "CASINDREE,CASINDDES,CASINDBON,CASINDPAG,CASBONO,CASINDPAN, "
				+ "CASABONO,CASNUMPTM,CASAPOCON,CASUSUARIO " + "FROM "
				+ bonifDatabase + ".BF03F1 " + "WHERE " + "SOCRUT = ? "
				+ "AND CONCOD = ? " + "AND CASNUMDOC = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, caso.getRutSocio());
			stmt.setLong(2, caso.getCodigoConvenio());
			stmt.setString(3, caso.getNumeroDocumento());

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			if (ors.next()) {
				//Caso casoOuput = new Caso();
				caso.setCasoID(ors.getLong("CASID"));
				caso.setRutSocio(ors.getString("SOCRUT"));
				caso.setCodigoConvenio(ors.getLong("CONCOD"));
				caso.setNumeroDocumento(ors.getString("CASNUMDOC"));
				caso.setTipoBono(ors.getString("CASBONO"));
				caso.setMonto(ors.getInt("CASMONTO"));
				caso.setAporteBienestar(ors.getInt("CASAPOBIE"));
				caso.setAporteSocio(ors.getInt("CASAPOSOC"));
				caso.setPrestamo(ors.getInt("CASPRSTAMO"));
				caso.setAbono(ors.getInt("CASABONO"));
				caso.setNumeroPrestamo(ors.getInt("CASNUMPTM"));
				caso.setUsuario(ors.getString("CASUSUARIO"));

				return caso;
			} else {
				return null;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Crea un nuevo VigenciaRango
	 * 
	 * @param VigenciaRango
	 */
	public void insertVigenciaRango(VigenciaRango vigenciaRango)
			throws Exception, BusinessException {
		if (vigenciaRango == null) {
			throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
					"Se ha intentado crear un Rango Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "INSERT INTO " + bonifDatabase + ".BF13F1 (" + "VRCCOD, "
				+ "COBCOD, " + "VRCFECINI, " + "VRCFECFIN) "
				+ "VALUES (?, ?, ?, ?)";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, vigenciaRango.getCodigo());
			stmt.setLong(2, vigenciaRango.getCodigoCobertura());
			stmt.setDate(3, new java.sql.Date(vigenciaRango.getInicioVigencia()
					.getTime()));

			if (vigenciaRango.getFinVigencia() != null) {
				stmt.setDate(4, new java.sql.Date(vigenciaRango
						.getFinVigencia().getTime()));
			} else {
				stmt.setNull(4, Types.DATE);
			}

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Registra la informaciòn de un moviiento registrado en tesoreria debido a
	 * un pre-caso
	 * 
	 * @param InfoMovimientoPreCasoVO
	 * @return void
	 */
	public long insertMovimientoTesoreriaPreCaso(
			InfoMovimientoPreCasoVO infoMovimientoPreCasoVO) throws Exception,
			BusinessException {

		if (infoMovimientoPreCasoVO == null) {
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"Se ha intentado crear un Movimiento Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		long idDisponible = 0;

		String command;

		command = "INSERT INTO " + bonifDatabase + ".BF28F1 ( "
				+ "CASID, DTPFOL, DTPFEC, DTPUSU, "
				+ "DTPTIPO, RUT, DV, NOMBRE, MONTO) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {

			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, infoMovimientoPreCasoVO.getCasoId());
			stmt.setLong(2, infoMovimientoPreCasoVO.getFolioTesoreria());
			stmt.setTimestamp(3, new java.sql.Timestamp(infoMovimientoPreCasoVO
					.getFecha().getTime()));
			stmt.setString(4, infoMovimientoPreCasoVO.getUsuario());
			stmt.setString(5, infoMovimientoPreCasoVO.getTipoMovimiento());
			stmt.setString(6, infoMovimientoPreCasoVO.getRut());
			stmt.setString(7, infoMovimientoPreCasoVO.getDigito());
			stmt.setString(8, infoMovimientoPreCasoVO.getNombre());
			stmt.setDouble(9, infoMovimientoPreCasoVO.getMonto());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return idDisponible;
	}

	/**
	 * Información de los egresos y/o ingresos generados en tesoreria
	 * 
	 * @param long
	 *            casoId
	 * @return ArrayList de InfoMovimientoPreCasoVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getMovimientosTesoreriaPreCaso(long casoId)
			throws Exception, BusinessException {

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		ArrayList resultado = new ArrayList();
		String command;

		command = "SELECT " + "CASID, DTPFOL, DTPFEC, DTPUSU, "
				+ "DTPTIPO, RUT, DV, NOMBRE, MONTO " + "FROM " + bonifDatabase
				+ ".BF28F1 " + "WHERE " + "CASID = ? ";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, casoId);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				InfoMovimientoPreCasoVO infoMovimientoPreCasoVO = new InfoMovimientoPreCasoVO();

				infoMovimientoPreCasoVO.setCasoId(ors.getLong("CASID"));
				infoMovimientoPreCasoVO
						.setFolioTesoreria(ors.getLong("DTPFOL"));
				infoMovimientoPreCasoVO.setFecha(ors.getDate("DTPFEC"));
				infoMovimientoPreCasoVO.setUsuario(ors.getString("DTPUSU"));
				infoMovimientoPreCasoVO.setTipoMovimiento(ors
						.getString("DTPTIPO"));
				infoMovimientoPreCasoVO.setRut(ors.getString("RUT"));
				infoMovimientoPreCasoVO.setDigito(ors.getString("DV"));
				infoMovimientoPreCasoVO.setNombre(ors.getString("NOMBRE"));
				infoMovimientoPreCasoVO.setMonto(ors.getDouble("MONTO"));

				resultado.add(infoMovimientoPreCasoVO);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}

		return resultado;
	}

	/**
	 * Obtiene la lista de Profesionales
	 * 
	 * @param Profesional
	 * @return ArrayList de Profesional
	 * @throws Exception
	 */
	public ArrayList getListaProfesionales(Profesional profesional)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String instruccionSql = null;
		boolean filtrarPorRut = false;
		boolean filtrarPorNombre = false;
		int contador = 0;

		String command = "SELECT DPERUT, DPEDV, DPENOMBRE " + "FROM "
				+ bonifDatabase + ".BF29F1";

		// verifico si vienen filtros
		if (profesional.getRut() != null && (profesional.getRut()).length() > 0) {
			command = command + " WHERE DPERUT like ?";
			filtrarPorRut = true;
		}
		if (profesional.getNombre() != null
				&& (profesional.getNombre()).length() > 0) {
			if (filtrarPorRut)
				instruccionSql = " AND ";
			else
				instruccionSql = " WHERE ";

			command = command + instruccionSql + "UCASE(DPENOMBRE) LIKE ? ";
			filtrarPorNombre = true;
		}

		command = command + " ORDER BY DPERUT";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			if (filtrarPorRut) {
				contador++;
				stmt.setString(contador, '%' + profesional.getRut() + '%');
			}
			if (filtrarPorNombre) {
				contador++;
				stmt.setString(contador, '%' + profesional.getNombre()
						.toUpperCase() + '%');
			}

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				Profesional vo = new Profesional();
				vo.setRut(ors.getString("DPERUT"));
				vo.setDigito(ors.getString("DPEDV"));
				vo.setNombre(ors.getString("DPENOMBRE"));

				retorno.add(vo);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("retorna filas: " + retorno.size());
		return retorno;
	}

	/**
	 * Obtiene la informacion de un Profesional
	 * 
	 * @param String
	 *            rut
	 * @return Profesional
	 * @throws Exception
	 */
	public Profesional getProfesionalByRut(String rut) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		Profesional vo = new Profesional();

		String command = "SELECT DPERUT, DPEDV, DPENOMBRE " + "FROM "
				+ bonifDatabase + ".BF29F1 " + "WHERE DPERUT = ?";
		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, rut);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			if (ors.next()) {
				vo.setRut(ors.getString("DPERUT"));
				vo.setDigito(ors.getString("DPEDV"));
				vo.setNombre(ors.getString("DPENOMBRE"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return vo;
	}

	/**
	 * Crea un nuevo profesional en Bienestar
	 * 
	 * @param Profesional
	 */
	public void insertProfesional(Profesional profesional) throws Exception,
			BusinessException {
		if (profesional == null) {
			throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
					"Se ha intentado crear un profesional Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "INSERT INTO " + bonifDatabase
				+ ".BF29F1 (DPERUT, DPEDV, DPENOMBRE) " + "VALUES (?, ?, ?)";

		try {

			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, profesional.getRut());
			stmt.setString(2, profesional.getDigito());
			stmt.setString(3, profesional.getNombre());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Actualiza los Datos de un profesional en Bienestar
	 * 
	 * @param socio:
	 *            el Objeto Profesional
	 */
	public void updateProfesional(Profesional profesional) throws Exception,
			BusinessException {
		if (profesional == null) {
			throw new BusinessException("CCAF_BONIF_SOCIOINVALIDO",
					"Se ha intentado modificar un profesional Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command = "UPDATE " + bonifDatabase
				+ ".BF29F1 SET DPENOMBRE = ? " + " WHERE DPERUT = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			stmt.setString(1, profesional.getNombre());
			stmt.setString(2, profesional.getRut());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);
		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);

		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Devuelve la suma de los egresos/ingresos previos del mismo caso
	 * 
	 * @param long
	 *            casoId
	 * @param String
	 *            tipo (Ingreso/Egreso)
	 * @return int, monto
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int getMontoMovimientosPrevios(long casoId, String tipo)
			throws Exception, BusinessException {

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;
		int total = 0;

		command = "SELECT  SUM(MONTO) \"MONTO\" FROM " + bonifDatabase
				+ ".BF28F1 " + "WHERE CASID = ? " + "AND DTPTIPO = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			stmt.setLong(1, casoId);
			stmt.setString(2, tipo);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			if (ors.next()) {
				total = (int) ors.getDouble("MONTO");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return total;
	}

	/**
	 * Obtiene una lista de pre-casos que cumplen con las condiciones entregadas
	 * 
	 * @param Caso
	 *            casoFiltro
	 * @return ArrayList de CasoVO
	 * @throws Exception
	 */
	public ArrayList getListaPreCasosByFiltro(Caso casoFiltro)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "SELECT  CASID, A.SOCRUT, B.DV SOCDV,B.FNNOMBRES SOCNOMBRE,b.FNAPELLPAT SOCAPEPAT,b.FNAPELLMAT SOCAPEMAT, b.FNFEC1 SOCFECING, " //antes de req 6083 se leía: b.FNFECHING SOCFECING,"
				+ "CARRUT, A.CONCOD, CONNOM, CONMAXCUO, CASMONTO, CASDSCTO, CASAPOISA, CASAPOBIE, CASAPOSOC, CASFECING,"
				+ "CASFECEST, CASFECOCU, CASCUOCONV, CASCUOBIE, CASTIPO, CASEST, CASTIPDOC, CASNUMDOC,"
				+ "CASPRSTAMO, CASINDREE, CASINDDES, CASINDBON, CASINDPAG, CASBONO, CASINDPAN,CASABONO, "
				+ "CASNUMPTM, CASAPOCON, CASUSUARIO, CASINDPC, CASINDPCEG, CASINDPCIG, "
				+ "D.TCOCOD, TCODESCRIP FROM "
				+ bonifDatabase
				+ ".BF03F1 A, "
				+ bonifDatabase
				+ ".FUNCIONARIOS B, "
				+ bonifDatabase
				+ ".BF05F1 C, "
				+ bonifDatabase
				+ ".BF14F1 D "
				+ "WHERE A.CONCOD = C.CONCOD "
				+ "AND C.TCOCOD = D.TCOCOD "
				+ "AND A.SOCRUT = B.RUT "
				+ "AND CASEST = ? "
				+ "AND CASINDPC = ? " + "AND CASINDPCEG = ? ";

		if (casoFiltro.getIndicadorPreCasoIngresoGenerado() != null)
			command = command + "AND CASINDPCIG = ? ";

		command = command + "ORDER BY CASFECOCU";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			stmt.setString(1, casoFiltro.getEstado());
			stmt.setString(2, casoFiltro.getIndicadorPreCaso());
			stmt.setString(3, casoFiltro.getIndicadorPreCasoEgresoGenerado());
			if (casoFiltro.getIndicadorPreCasoIngresoGenerado() != null)
				stmt.setString(4, casoFiltro
						.getIndicadorPreCasoIngresoGenerado());

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				CasoVO cas = new CasoVO();
				cas.setCasoID(ors.getLong("CASID"));
				cas.setRutSocio(ors.getString("SOCRUT"));
				cas.setDvRutSocio(ors.getString("SOCDV"));
				cas.setNombreSocio(ors.getString("SOCNOMBRE") + " "
						+ ors.getString("SOCAPEPAT") + " "
						+ ors.getString("SOCAPEMAT"));
				cas.setFecIngSocio(ors.getDate("SOCFECING"));
				cas.setRutCarga(ors.getString("CARRUT"));
				cas.setCodigoConvenio(ors.getLong("CONCOD"));
				cas.setNombreConvenio(ors.getString("CONNOM"));
				cas.setNumeroMaximoCuotas(ors.getInt("CONMAXCUO"));
				cas.setMonto(ors.getDouble("CASMONTO"));
				cas.setMontoDescuento(ors.getDouble("CASDSCTO"));
				cas.setAporteIsapre(ors.getDouble("CASAPOISA"));
				cas.setAporteBienestar(ors.getDouble("CASAPOBIE"));
				cas.setAporteSocio(ors.getDouble("CASAPOSOC"));
				cas.setFechaIngreso(ors.getDate("CASFECING"));
				cas.setFechaEstado(ors.getDate("CASFECEST"));
				cas.setFechaDeOcurrencia(ors.getDate("CASFECOCU"));
				cas.setCuotasConvenio(ors.getInt("CASCUOCONV"));
				cas.setCuotasBienestar(ors.getInt("CASCUOBIE"));
				cas.setTipoCaso(ors.getString("CASTIPO"));
				cas.setEstado(ors.getString("CASEST"));
				cas.setTipoDocumento(ors.getString("CASTIPDOC"));
				cas.setNumeroDocumento(ors.getString("CASNUMDOC"));
				cas.setPrestamo(ors.getDouble("CASPRSTAMO"));
				cas.setIndicadorReembolso(ors.getString("CASINDREE"));
				cas.setIndicadorDescontado(ors.getString("CASINDDES"));
				cas.setIndicadorBonificacion(ors.getString("CASINDBON"));
				cas.setIndicadorPago(ors.getString("CASINDPAG"));
				cas.setTipoBono(ors.getString("CASBONO"));
				cas.setIndicadorPagoAnticipado(ors.getString("CASINDPAN"));
				cas.setAbono(ors.getDouble("CASABONO"));
				cas.setNumeroPrestamo(ors.getInt("CASNUMPTM"));
				cas.setAporteConvenio(ors.getDouble("CASAPOCON"));
				cas.setUsuario(ors.getString("CASUSUARIO"));
				cas.setIndicadorPreCaso(ors.getString("CASINDPC"));
				cas.setIndicadorPreCasoEgresoGenerado(ors
						.getString("CASINDPCEG"));
				cas.setIndicadorPreCasoIngresoGenerado(ors
						.getString("CASINDPCIG"));
				cas.setCodigoConcepto(ors.getLong("TCOCOD"));
				cas.setDescripcionConcepto(ors.getString("TCODESCRIP"));
				retorno.add(cas);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Registra datos de u socio y cobertura con aportes inconsistentes
	 * 
	 * @param DatosInconsistenciaVO
	 * @return void
	 */
	public long insertInconsistenciaAportesBienestar(
			DatosInconsistenciaVO datosInconsistenciaVO) throws Exception,
			BusinessException {

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		long idDisponible = 0;

		String command;

		command = "INSERT INTO " + bonifDatabase + ".BF30F1 ( "
				+ "SOCRUT, COBCOD, TIATIPTOPE, TIAMES, "
				+ "TIAANIO, TIAFECING) " + "VALUES (?, ?, ?, ?, ?, ?)";

		try {

			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, datosInconsistenciaVO.getRutSocio());
			stmt.setLong(2, datosInconsistenciaVO.getCodigoCobertura());
			stmt.setString(3, datosInconsistenciaVO.getTipoTope());
			stmt.setInt(4, datosInconsistenciaVO.getMes());
			stmt.setInt(5, datosInconsistenciaVO.getAnio());
			stmt.setTimestamp(6, datosInconsistenciaVO.getFecha());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return idDisponible;
	}

	/**
	 * Crea un nuevo detalle con información del detalle de egreso en tesoreria
	 * 
	 * @param DetalleMovimientoPreCasoVO
	 */
	public void insertDetalleEgreso(DetalleMovimientoPreCasoVO vo)
			throws Exception, BusinessException {
		if (vo == null) {
			throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
					"Se ha intentado crear un detalle nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "INSERT INTO " + bonifDatabase
				+ ".BF31F1 (CASID, DTPFOL, COBCOD, MONTO, DCAID) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try {

			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, vo.getCasoId());
			stmt.setLong(2, vo.getFolioTesoreria());
			stmt.setLong(3, vo.getCoberturaCodigo());
			stmt.setDouble(4, vo.getMonto());
			stmt.setInt(5, vo.getIdDetalle());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	/**
	 * Obtiene una lista de detalles de egresos de pre-casos dado un numero de
	 * folio de tesoreria
	 * 
	 * @param long
	 *            folio
	 * @return ArrayList de DetalleMovimientoPreCasoVO
	 * @throws Exception
	 */
	public ArrayList getListaDetallesEgresoByFolio(long folio)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "SELECT  A.CASID, A.DTPFOL, A.COBCOD, A.MONTO, A.DCAID FROM "
				+ bonifDatabase + ".BF31F1 A, " + bonifDatabase + ".BF28F2 B "
				+ "WHERE   A.CASID = B.CASID " + "AND     A.DTPFOL = B.DTPFOL "
				+ "AND     A.DTPFOL = ? " + "AND     DTPTIPO = ?";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			stmt.setLong(1, folio);
			stmt.setString(2, Constants.MOVI_EGRESO);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				DetalleMovimientoPreCasoVO vo = new DetalleMovimientoPreCasoVO();
				vo.setCasoId(ors.getLong("CASID"));
				vo.setFolioTesoreria(ors.getLong("DTPFOL"));
				vo.setCoberturaCodigo(ors.getLong("COBCOD"));
				vo.setMonto(ors.getDouble("MONTO"));
				vo.setIdDetalle(ors.getInt("DCAID"));

				retorno.add(vo);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * Devuelve la suma de los montos de los detalles de los movimientos previos
	 * del mismo caso y cobertura
	 * 
	 * @param long
	 *            casoId, long coberturaCodigo, int idDetalle
	 * @return int, monto
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int getMontoCoberturaMovimientosPrevios(long casoId,
			long coberturaCodigo, int idDetalle) throws Exception,
			BusinessException {

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;
		int total = 0;

		command = "SELECT  SUM(MONTO) \"MONTO\" FROM " + bonifDatabase
				+ ".BF31F1 " + "WHERE CASID = ? " + "AND COBCOD = ? "
				+ "AND DCAID = ?";// id del detalle.

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			stmt.setLong(1, casoId);
			stmt.setLong(2, coberturaCodigo);
			stmt.setInt(3, idDetalle);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			if (ors.next()) {
				total = (int) ors.getDouble("MONTO");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return total;
	}

	/*
	 * (no Javadoc)
	 * 
	 * @see cl.araucana.bienestar.bonificaciones.dao.BonificacionesDAO#getGrupoUsuario(long)
	 */
	public int getGrupoUsuario(long rut) throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet r = null;
		String command;
		int grupo = 0;

		command = "select grupo_codigo as grupoUsuario from " + usuarioDatabase
				+ ".usuario where rut = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			stmt.setLong(1, rut);

			logger.debug("Inicia operación: " + command);
			r = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			if (r.next()) {
				grupo = new Integer(r.getString("grupoUsuario")).intValue();
			}
			logger.debug("grupo: " + grupo);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, r);
		}
		return grupo;
	}

	/**
	 * INICIO NUEVO
	 */
	/**
	 * Obtiene la lista de Convenios existentes
	 * 
	 * @param Convenio
	 * @return ArrayList de Convenio
	 * @throws Exception
	 */
	public ArrayList getConveniosRe(Convenio convenio, long codigoCobertura,
			String convenios) throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		boolean filtrarPorCodigoCobertura = false;
		boolean filtrarPorCodigoConvenio = false;
		boolean filtrarPorNombreConvenio = false;
		boolean filtrarPorCodigoConcepto = false;
		boolean filtrarPorEstadoConvenio = false;
		boolean filtrarPorRutConvenio = false;
		int contador = 0;

		String command;

		command = "SELECT CONCOD, A.TCOCOD, TCODESCRIP, CONRUT, CONDV, CONNOM, CONMAXCUO, "
				+ "CONEST, CONFECINI, CONFECFIN FROM "
				+ bonifDatabase
				+ ".BF05F1 A, "
				+ bonifDatabase
				+ ".BF14F1 B WHERE A.TCOCOD = B.TCOCOD";

		// Primero reviso si viene consulta por codigo de Cobertura

		if (codigoCobertura != 0) {
			command = command + " AND CONCOD IN (SELECT CONCOD FROM "
					+ bonifDatabase + ".BF06F1 WHERE COBCOD = ?)";
			filtrarPorCodigoCobertura = true;
		}

		// Reviso si vienen filtros

		if (convenio.getCodigo() > 0) {
			command = command + " AND CONCOD = ?";
			filtrarPorCodigoConvenio = true;
		}
		if (convenio.getNombre() != null && (convenio.getNombre()).length() > 0) {
			command = command + " AND UCASE(CONNOM) LIKE ?";
			filtrarPorNombreConvenio = true;
		}
		if (convenio.getCodigoConcepto() != 0) {
			command = command + " AND A.TCOCOD = ?";
			filtrarPorCodigoConcepto = true;
		}
		if (convenio.getEstado() != null && (convenio.getEstado()).length() > 0) {
			command = command + " AND CONEST = ?";
			filtrarPorEstadoConvenio = true;
		}
		if (convenio.getRut() != null && (convenio.getRut()).length() > 0) {
			command = command + " AND CONRUT LIKE ?";
			filtrarPorRutConvenio = true;
		}

		command = command + " and  CONCOD  in (" + convenios + ")";
		command = command + " ORDER BY CONEST, CONCOD";

		// System.out.println(" SQLSQLSQL"+command);

		logger.debug("filtrarPorCodigoCobertura: " + filtrarPorCodigoCobertura);
		logger.debug("filtrarPorCodigoConvenio: " + filtrarPorCodigoConvenio);
		logger.debug("filtrarPorNombreConvenio: " + filtrarPorNombreConvenio);
		logger.debug("filtrarPorCodigoConcepto: " + filtrarPorCodigoConcepto);
		logger.debug("filtrarPorEstadoConvenio: " + filtrarPorEstadoConvenio);
		logger.debug("filtrarPorRutConvenio: " + filtrarPorRutConvenio);

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			if (filtrarPorCodigoCobertura) {
				contador++;
				stmt.setLong(contador, codigoCobertura);
			}
			if (filtrarPorCodigoConvenio) {
				contador++;
				stmt.setLong(contador, convenio.getCodigo());
			}
			if (filtrarPorNombreConvenio) {
				contador++;
				stmt.setString(contador, '%' + convenio.getNombre()
						.toUpperCase() + '%');
			}
			if (filtrarPorCodigoConcepto) {
				contador++;
				stmt.setLong(contador, convenio.getCodigoConcepto());
			}
			if (filtrarPorEstadoConvenio) {
				contador++;
				stmt.setString(contador, convenio.getEstado());
			}
			if (filtrarPorRutConvenio) {
				contador++;
				stmt.setString(contador, '%' + convenio.getRut() + '%');
			}

			logger.debug("Dato Rut: " + '%' + convenio.getRut() + '%');
			logger.debug("Inicia operación: " + command);

			logger.debug("Finaliza operación: " + command);

			// System.out.println(" quary "+command);

			ors = stmt.executeQuery();
			while (ors.next()) {
				Convenio con = new Convenio();
				con.setCodigo(ors.getLong("CONCOD"));

//				int cod = (new Long(con.getCodigo()).intValue());

				// System.out.println("codigo convenio "+con.getCodigo());

				con.setCodigoConcepto(ors.getLong("TCOCOD"));
				con.setDescripcionConcepto(ors.getString("TCODESCRIP"));
				con.setRut(ors.getString("CONRUT"));
				con.setDvRut(ors.getString("CONDV"));
				con.setNombre(ors.getString("CONNOM"));
				con.setNumeroMaximoCuotas(ors.getInt("CONMAXCUO"));
				con.setEstado(ors.getString("CONEST"));
				con.setFecInicio(ors.getDate("CONFECINI"));
				con.setFecFin(ors.getDate("CONFECFIN"));

				retorno.add(con);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		}

		finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return retorno;
	}

	/*
	 * (no Javadoc)
	 * 
	 * @see cl.araucana.bienestar.bonificaciones.dao.BonificacionesDAO#getProximoCodigoDescuento()
	 */
	public long getProximoCodigoDescuento() throws Exception, BusinessException {
		long idDisponible = 0;
		/*
		 * Obtiene un ID
		 */
		idDisponible = getID_("BF24F1");
		if (idDisponible > 0) {
			return idDisponible;
		} else {
			throw new GeneralException(this, null, "CCAF_IDINVALIDO",
					"No se pudo obtener un ID");
		}
	}

	/**
	 * req 4353, trae todas las cuotas que no se han cobrado al socio (TODAS, no
	 * una)
	 */
	public List getCuotasNoCobradas(long casoId) throws Exception,
			BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		List coleccion = new ArrayList();
		String command;

		command = "SELECT CUONUM, CUOVALOR, CUOFECVCTO FROM " + bonifDatabase
				+ ".BF08F1 " + "WHERE CASID = ? AND CUOEST = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, casoId);
			stmt.setString(2, Cuota.STD_NO_DESCONTADA);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {

				CuotaPendienteVO cuota = new CuotaPendienteVO();

				cuota.setCuota(ors.getInt("CUONUM"));
				cuota.setMonto((int) ors.getDouble("CUOVALOR"));
				cuota.setFecha(ors.getDate("CUOFECVCTO"));

				coleccion.add(cuota);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return coleccion;
	}

	/*
	 * (no Javadoc)
	 * 
	 * @see cl.araucana.bienestar.bonificaciones.dao.BonificacionesDAO#deleteCuotasCasoNoCobradas(long)
	 */
	public void deleteCuotasCasoNoCobradas(long casoId)
			throws BusinessException, GeneralException {
		if (casoId == 0) {
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"Se ha intentado eliminar Cuotas con el caso Id Nulo");
		}

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "DELETE FROM " + bonifDatabase
				+ ".BF08F1 WHERE CASID = ? and CUOEST = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, casoId);
			stmt.setString(2, Cuota.STD_NO_DESCONTADA);

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	public void updateDescuentoTotalSocio(
			DescuentoTotalSocioVO descuentoTotalSocio)
			throws BusinessException, Exception {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "UPDATE " + bonifDatabase + ".BF24F1 SET TDTMONTO = ? "
				+ "WHERE TDTCOD = ? and SOCRUT = ?";

		try {

			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setDouble(1, descuentoTotalSocio.getMontoDescuento());
			stmt.setLong(2, descuentoTotalSocio.getCodigoDescuento());
			stmt.setString(3, descuentoTotalSocio.getRut());

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);
		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}

	public ArrayList getpagoConvenioPendientesAnticipado(long codigoDescuento)
			throws BusinessException, Exception {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		/*
		 * command = "SELECT B.CONCOD, CONNOM, C.CONRUT, CONDV, TCOTESAREA, "+
		 * "TCOTESCOEG, SUM((CASMONTO - CASDSCTO - CASAPOISA)) \"MONTO\" "+
		 * "FROM "+ bonifDatabase+".BF32F1 A, "+ bonifDatabase+".BF03F1 B, "+
		 * bonifDatabase+".BF05F1 C, "+ bonifDatabase+".BF14F1 D "+ "WHERE "+
		 * "A.CASID = B.CASID " + "AND B.CONCOD = C.CONCOD "+ "AND C.TCOCOD =
		 * D.TCOCOD "+ "AND CASBONO = ? "+ "AND TDTCOD = ? "+ //"AND DUCCUOTOT
		 * <=1 " + "GROUP BY B.CONCOD, CONNOM, C.CONRUT, CONDV, TCOTESAREA,
		 * TCOTESCOEG "+ "ORDER BY B.CONCOD";
		 */

		command = "SELECT B.CASID, B.CONCOD, CONNOM, C.CONRUT, "
				+ "CONDV, TCOTESAREA, TCOTESCOEG, DUCMONTO, "
				+ "CASCUOCONV, CASCUOBIE, DUCCUONUM, "
				+ "(CASMONTO - CASDSCTO - CASAPOISA) \"MONTO\", "
				+ "CASAPOBIE " + "FROM " + bonifDatabase + ".BF32F1 A, "
				+ bonifDatabase + ".BF03F1 B, " + bonifDatabase + ".BF05F1 C, "
				+ bonifDatabase + ".BF14F1 D " + "WHERE "
				+ "A.CASID = B.CASID " + "AND B.CONCOD = C.CONCOD "
				+ "AND C.TCOCOD = D.TCOCOD " + "AND CASBONO = ? "
				+ "AND TDTCOD = ? " +
				// "AND DUCCUOTOT >1 "+
				"ORDER BY B.CONCOD";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			stmt.setString(1, Caso.TIPOBONO_NO);
			stmt.setLong(2, codigoDescuento);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				/*
				 * PagoConvenioPendienteVO convenio = new
				 * PagoConvenioPendienteVO ();
				 * 
				 * convenio.setCodigoConvenio(ors.getLong("CONCOD"));
				 * convenio.setNombreConvenio(ors.getString("CONNOM"));
				 * convenio.setRut(ors.getString("CONRUT"));
				 * convenio.setDv(ors.getString("CONDV"));
				 * convenio.setMonto(ors.getInt("MONTO"));
				 * convenio.setArea(ors.getLong("TCOTESAREA"));
				 * convenio.setConceptoEgreso(ors.getLong("TCOTESCOEG"));
				 */
				PagoConvenioPendienteCuotaVO convenio = new PagoConvenioPendienteCuotaVO();
				convenio.setCasid(ors.getLong("CASID"));
				convenio.setCodigoConvenio(ors.getLong("CONCOD"));
				convenio.setCuotaDescontada(ors.getInt("DUCCUONUM"));
				convenio.setMontoCuota(ors.getInt("DUCMONTO"));
				convenio.setMontoTotal(ors.getInt("MONTO"));
				convenio.setNumeroCuotasBienestar(ors.getInt("CASCUOBIE"));
				convenio.setNumeroCuotasConvenio(ors.getInt("CASCUOCONV"));
				convenio.setNombreConvenio(ors.getString("CONNOM"));
				convenio.setRut(ors.getString("CONRUT"));
				convenio.setDv(ors.getString("CONDV"));
				convenio.setArea(ors.getLong("TCOTESAREA"));
				convenio.setConceptoEgreso(ors.getLong("TCOTESCOEG"));
				convenio.setAporteBienestar(ors.getInt("CASAPOBIE"));

				retorno.add(convenio);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	public List getSociosInactivosConCasosAbiertos() throws BusinessException,
			Exception {

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;
		
		command = "SELECT a.RUT, a.DV, a.FNNOMBRES, a.FNAPELLPAT,a.FNAPELLMAT,"
				+ "a.FNFECHEGR, count(casid) CANTCASOS "
				+ "FROM "
				+ bonifDatabase
				+ ".funcionarios a, "
				+ bonifDatabase
				+ ".bf03f1 b "
				+ "WHERE a.rut = b.socrut and B.CASEST = ? and A.FNESTADO = ? and "
				+"casid not in (select casid from "+bonifDatabase+".bf32f1) "
				+ "group by a.RUT, a.DV, a.FNNOMBRES, a.FNAPELLPAT,a.FNAPELLMAT,"
				+ " a.fNCODCARG, a.FNFECHEGR";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			stmt.setString(1, Caso.STD_ACTIVO);
			stmt.setString(2, Socio.STD_ELIMINADO);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				SocioInactivoConCasoAbierto s = new SocioInactivoConCasoAbierto();
				s.setRut(ors.getLong("RUT"));
				s.setDv(ors.getString("DV"));
				s.setNombre(ors.getString("FNNOMBRES"));
				s.setApellidoPaterno(ors.getString("FNAPELLPAT"));
				s.setApellidoMaterno(ors.getString("FNAPELLMAT"));
				s.setContadorCasosAbiertos(ors.getInt("CANTCASOS"));
				retorno.add(s);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}

	/**
	 * REQ 4969
	 * Obtiene una lista de casos segun los parametros
	 * 
	 * @param Caso
	 * @return ArrayList de CasoVO
	 * @throws Exception
	 */
	public ArrayList getCasosSocioInactivo(Caso caso, long codigoCobertura)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		boolean filtrarPorCasoId = false;
		boolean filtrarPorRutSocio = false;
		boolean filtrarPorRutCarga = false;
		boolean filtrarPorFechaIngreso = false;
		boolean filtrarPorFechaDeOcurrencia = false;
		boolean filtrarPorTipoCaso = false;
		boolean filtrarPorEstado = false;
		boolean filtrarPorIndicadorBonificacion = false;
		boolean filtrarPorIndicadorReembolso = false;
		boolean filtrarPorIndicadorDescontado = false;
		boolean filtrarPorIndicadorPago = false;
		boolean filtrarPorCodigoCobertura = false;
		boolean filtrarPorTipoBono = false;
		boolean filtrarPorCodigoConvenio = false;
		boolean filtrarPorIndicadorPreCaso = false;
		boolean filtrarPorIndicadorPreCasoEgresoGenerado = false;
		boolean filtrarPorIndicadorPreCasoIngresoGenerado = false;
		// NUEVO
//		boolean filtrarUsuarioRegistradoSocio = false;

		int contador = 0;

		String command;

		command = "SELECT CASID, A.SOCRUT, DV SOCDV, FNNOMBRES SOCNOMBRE, FNAPELLPAT  SOCAPEPAT, FNAPELLMAT SOCAPEMAT, FNFEC1 SOCFECING, " //antes de req 6083 se leía: FNFECHING   SOCFECING,"
				+ "CARRUT, A.CONCOD, CONNOM, CONMAXCUO, CASMONTO, CASDSCTO, CASAPOISA, CASAPOBIE, CASAPOSOC, CASFECING,"
				+ "CASFECEST, CASFECOCU, CASCUOCONV, CASCUOBIE, CASTIPO, CASEST, CASTIPDOC, CASNUMDOC,"
				+ "CASPRSTAMO, CASINDREE, CASINDDES, CASINDBON, CASINDPAG, CASBONO, CASINDPAN,CASABONO, "
				+ "CASNUMPTM, CASAPOCON, CASUSUARIO, CASINDPC, CASINDPCEG, CASINDPCIG, "
				+ "D.TCOCOD, TCODESCRIP FROM "
				+ bonifDatabase
				+ ".BF03F1 A, "
				+ bonifDatabase
				+ ".funcionarios B, "
				+ bonifDatabase
				+ ".BF05F1 C, "
				+ bonifDatabase
				+ ".BF14F1 D "
				+ "WHERE "
				+ "A.SOCRUT = B.RUT "
				+ "AND "
				+"A.CASID not in ( select casid from "+ bonifDatabase+".BF32F1) "
				+ "AND "
				+ "A.CONCOD = C.CONCOD "
				+ "AND " + "C.TCOCOD = D.TCOCOD " + "AND CASEST <> ? ";

		// reviso si viene consulta por cobertura
		if (codigoCobertura > 0) {
			command = command + " AND CASID IN (SELECT DISTINCT(CASID) FROM "
					+ bonifDatabase + ".BF17F1 WHERE COBCOD = ?)";
			filtrarPorCodigoCobertura = true;
		} else {
			/*
			 * Reviso si vienen filtros
			 */
			if (caso != null) {
				if (caso.getCasoID() > 0) {
					logger.debug("CasoID: " + caso.getCasoID());
					command = command + " AND CASID = ?";
					filtrarPorCasoId = true;
				}
				if (caso.getRutSocio() != null
						&& caso.getRutSocio().length() > 0) {
					logger.debug("Rut Socio: " + caso.getRutSocio());
					command = command + " AND A.SOCRUT LIKE ?";
					// if(codUsuarioRegistrado == 5){ //NUEVO, si es socio debo
					// añadir lo siguiente
					// command = command + " AND A.CASUSUARIO LIKE ?";
					// filtrarUsuarioRegistradoSocio = true;
					// }
					filtrarPorRutSocio = true;
				}
				if (caso.getRutCarga() != null
						&& caso.getRutCarga().length() > 0) {
					logger.debug("Rut Carga: " + caso.getRutCarga());
					command = command + " AND CARRUT = ?";
					filtrarPorRutCarga = true;
				}
				if (caso.getCodigoConvenio() > 0) {
					logger
							.debug("Codigo Convenio: "
									+ caso.getCodigoConvenio());
					command = command + " AND A.CONCOD = ?";
					filtrarPorCodigoConvenio = true;
				}
				if (caso.getFechaIngreso() != null) {
					logger.debug("Fecha Ingreso: " + caso.getFechaIngreso());
					command = command + " AND CASFECING = ?";
					filtrarPorFechaIngreso = true;
				}
				if (caso.getFechaDeOcurrencia() != null) {
					logger.debug("Fecha De Ocurrencia: "
							+ caso.getFechaDeOcurrencia());
					command = command + " AND CASFECOCU = ?";
					filtrarPorFechaDeOcurrencia = true;
				}
				if (caso.getTipoCaso() != null
						&& caso.getTipoCaso().length() > 0) {
					logger.debug("Tipo Caso: " + caso.getTipoCaso());
					command = command + " AND CASTIPO = ?";
					filtrarPorTipoCaso = true;
				}
				if (caso.getEstado() != null && caso.getEstado().length() > 0) {
					logger.debug("Estado: " + caso.getEstado());
					command = command + " AND CASEST = ?";
					filtrarPorEstado = true;
				}
				if (caso.getIndicadorBonificacion() != null
						&& caso.getIndicadorBonificacion().length() > 0) {
					logger.debug("Indicador Bonificacion: "
							+ caso.getIndicadorBonificacion());
					command = command + " AND CASINDBON = ?";
					filtrarPorIndicadorBonificacion = true;
				}
				if (caso.getIndicadorReembolso() != null
						&& caso.getIndicadorReembolso().length() > 0) {
					logger.debug("Indicador Reembolso: "
							+ caso.getIndicadorReembolso());
					command = command + " AND CASINDREE = ?";
					filtrarPorIndicadorReembolso = true;
				}
				if (caso.getIndicadorDescontado() != null
						&& caso.getIndicadorDescontado().length() > 0) {
					logger.debug("Indicador Descontado: "
							+ caso.getIndicadorDescontado());
					command = command + " AND CASINDDES = ?";
					filtrarPorIndicadorDescontado = true;
				}
				if (caso.getIndicadorPago() != null
						&& caso.getIndicadorPago().length() > 0) {
					logger.debug("Indicador Pago: " + caso.getIndicadorPago());
					command = command + " AND CASINDPAG = ?";
					filtrarPorIndicadorPago = true;
				}
				if (caso.getTipoBono() != null
						&& caso.getTipoBono().length() > 0) {
					logger.debug("Tipo Bono: " + caso.getTipoBono());
					command = command + " AND CASBONO = ?";
					filtrarPorTipoBono = true;
				}
				if (caso.getIndicadorPreCaso() != null
						&& caso.getIndicadorPreCaso().length() > 0) {
					logger.debug("Indicador Pre-Caso: "
							+ caso.getIndicadorPreCaso());
					command = command + " AND CASINDPC = ?";
					filtrarPorIndicadorPreCaso = true;
				}
				if (caso.getIndicadorPreCasoEgresoGenerado() != null
						&& caso.getIndicadorPreCasoEgresoGenerado().length() > 0) {
					logger.debug("Indicador Pre-Caso Egreso: "
							+ caso.getIndicadorPreCasoEgresoGenerado());
					command = command + " AND CASINDPCEG = ?";
					filtrarPorIndicadorPreCasoEgresoGenerado = true;
				}
				if (caso.getIndicadorPreCasoIngresoGenerado() != null
						&& caso.getIndicadorPreCasoIngresoGenerado().length() > 0) {
					logger.debug("Indicador Pre-Caso Ingreso: "
							+ caso.getIndicadorPreCasoIngresoGenerado());
					command = command + " AND CASINDPCIG = ?";
					filtrarPorIndicadorPreCasoIngresoGenerado = true;
				}
			}
		}

		command = command + " ORDER BY CASEST,CASID";

		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			contador++;
			stmt.setString(contador, Caso.STD_PRECASO);

			if (filtrarPorCodigoCobertura) {
				contador++;
				stmt.setLong(contador, codigoCobertura);
			} else {
				if (filtrarPorCasoId) {
					contador++;
					stmt.setLong(contador, caso.getCasoID());
				}
				if (filtrarPorRutSocio) {
					contador++;
					stmt.setString(contador, '%' + caso.getRutSocio() + '%');
					// if(filtrarUsuarioRegistradoSocio){ //NUEVO (pedido 5 de
					// junio 2007),solo si el usuario es SOCIO :D
					// contador++;
					// stmt.setString(contador,'%' + caso.getRutSocio() + '%');
					// //NUEVO(pedido 24 mayo 2007) ademas filtramos por
					// BFDTA.CASUSUARIO
					// }
				}
				if (filtrarPorRutCarga) {
					contador++;
					stmt.setString(contador, caso.getRutCarga());
				}
				if (filtrarPorCodigoConvenio) {
					contador++;
					stmt.setLong(contador, caso.getCodigoConvenio());
				}
				if (filtrarPorFechaIngreso) {
					contador++;
					stmt.setTimestamp(contador, new java.sql.Timestamp(caso
							.getFechaIngreso().getTime()));
				}
				if (filtrarPorFechaDeOcurrencia) {
					contador++;
					stmt.setTimestamp(contador, new java.sql.Timestamp(caso
							.getFechaDeOcurrencia().getTime()));
				}
				if (filtrarPorTipoCaso) {
					contador++;
					stmt.setString(contador, caso.getTipoCaso());
				}
				if (filtrarPorEstado) {
					contador++;
					stmt.setString(contador, caso.getEstado());
				}
				if (filtrarPorIndicadorBonificacion) {
					contador++;
					stmt.setString(contador, caso.getIndicadorBonificacion());
				}
				if (filtrarPorIndicadorReembolso) {
					contador++;
					stmt.setString(contador, caso.getIndicadorReembolso());
				}
				if (filtrarPorIndicadorDescontado) {
					contador++;
					stmt.setString(contador, caso.getIndicadorDescontado());
				}
				if (filtrarPorIndicadorPago) {
					contador++;
					stmt.setString(contador, caso.getIndicadorPago());
				}
				if (filtrarPorTipoBono) {
					contador++;
					stmt.setString(contador, caso.getTipoBono());
				}
				if (filtrarPorIndicadorPreCaso) {
					contador++;
					stmt.setString(contador, caso.getIndicadorPreCaso());
				}
				if (filtrarPorIndicadorPreCasoEgresoGenerado) {
					contador++;
					stmt.setString(contador, caso
							.getIndicadorPreCasoEgresoGenerado());
				}
				if (filtrarPorIndicadorPreCasoIngresoGenerado) {
					contador++;
					stmt.setString(contador, caso
							.getIndicadorPreCasoIngresoGenerado());
				}
			}

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				CasoVO cas = new CasoVO();
				cas.setCasoID(ors.getLong("CASID"));
				cas.setRutSocio(ors.getString("SOCRUT"));
				cas.setDvRutSocio(ors.getString("SOCDV"));
				cas.setNombreSocio(ors.getString("SOCNOMBRE") + " "
						+ ors.getString("SOCAPEPAT") + " "
						+ ors.getString("SOCAPEMAT"));
				cas.setFecIngSocio(ors.getDate("SOCFECING"));
				cas.setRutCarga(ors.getString("CARRUT"));
				cas.setCodigoConvenio(ors.getLong("CONCOD"));
				cas.setNombreConvenio(ors.getString("CONNOM"));
				cas.setNumeroMaximoCuotas(ors.getInt("CONMAXCUO"));
				cas.setMonto(ors.getDouble("CASMONTO"));
				cas.setMontoDescuento(ors.getDouble("CASDSCTO"));
				cas.setAporteIsapre(ors.getDouble("CASAPOISA"));
				cas.setAporteBienestar(ors.getDouble("CASAPOBIE"));
				cas.setAporteSocio(ors.getDouble("CASAPOSOC"));
				cas.setFechaIngreso(ors.getDate("CASFECING"));
				cas.setFechaEstado(ors.getDate("CASFECEST"));
				cas.setFechaDeOcurrencia(ors.getDate("CASFECOCU"));
				cas.setCuotasConvenio(ors.getInt("CASCUOCONV"));
				cas.setCuotasBienestar(ors.getInt("CASCUOBIE"));
				cas.setTipoCaso(ors.getString("CASTIPO"));
				cas.setEstado(ors.getString("CASEST"));
				cas.setTipoDocumento(ors.getString("CASTIPDOC"));
				cas.setNumeroDocumento(ors.getString("CASNUMDOC"));
				cas.setPrestamo(ors.getDouble("CASPRSTAMO"));
				cas.setIndicadorReembolso(ors.getString("CASINDREE"));
				cas.setIndicadorDescontado(ors.getString("CASINDDES"));
				cas.setIndicadorBonificacion(ors.getString("CASINDBON"));
				cas.setIndicadorPago(ors.getString("CASINDPAG"));
				cas.setTipoBono(ors.getString("CASBONO"));
				cas.setIndicadorPagoAnticipado(ors.getString("CASINDPAN"));
				cas.setAbono(ors.getDouble("CASABONO"));
				cas.setNumeroPrestamo(ors.getInt("CASNUMPTM"));
				cas.setAporteConvenio(ors.getDouble("CASAPOCON"));
				cas.setUsuario(ors.getString("CASUSUARIO"));
				cas.setIndicadorPreCaso(ors.getString("CASINDPC"));
				cas.setIndicadorPreCasoEgresoGenerado(ors
						.getString("CASINDPCEG"));
				cas.setIndicadorPreCasoIngresoGenerado(ors
						.getString("CASINDPCIG"));
				cas.setCodigoConcepto(ors.getLong("TCOCOD"));
				cas.setDescripcionConcepto(ors.getString("TCODESCRIP"));
				retorno.add(cas);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}
	
	/**
	 * REQ 5030
	 * Obtiene la información de los detalles de un caso de tipo reembolso 
	 * 
	 * @param idCaso
	 * @return ReembolsoVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public List getDetallesCasoReembolso(long idCaso)
			throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		/*command = "SELECT CASID,TRECOD,A.SOCRUT,B.DV SOCDV,b.FNNOMBRES SOCNOMBRE,"
				+ "b.FNAPELLPAT SOCAPEPAT,b.FNAPELLMAT SOCAPEMAT,"
				+ "b.FNLPAGO,REEMONTO,REEFOLBIE,REEFOLARAU FROM "
				+ bonifDatabase
				+ ".BF18F1 A, "
				+ bonifDatabase
				+ ".FUNCIONARIOS B WHERE A.SOCRUT = B.RUT AND CASID = ? ORDER BY "
				+ "B.FNLPAGO, A.SOCRUT";
		 */
		
		//Solo se permite egresos con un solo caso.
		command = "select count(b.reefolbie) cantfolios, " +
				"aux.CASID, aux.TRECOD,aux.SOCRUT,aux.SOCDV, " +
				"aux.SOCNOMBRE, aux.SOCAPEPAT, aux.SOCAPEMAT, aux.FNLPAGO, " +
				"aux.REEMONTO,aux.REEFOLBIE,aux.REEFOLARAU from ( " +
				"SELECT CASID,TRECOD,A.SOCRUT,B.DV SOCDV,b.FNNOMBRES SOCNOMBRE, " +
				"b.FNAPELLPAT SOCAPEPAT,b.FNAPELLMAT SOCAPEMAT," +
				"b.FNLPAGO,REEMONTO,REEFOLBIE,REEFOLARAU " +
				"FROM " +
				bonifDatabase+".bf18F1 A, "+bonifDatabase+".FUNCIONARIOS B " +
				"WHERE A.SOCRUT = B.RUT AND CASID = ? " +
				"ORDER BY B.FNLPAGO, A.SOCRUT) AUX, "+bonifDatabase+".BF18F1 B " +
				"where aux.reefolbie = b.reefolbie " +
				"group by aux.CASID, aux.TRECOD,aux.SOCRUT,aux.SOCDV, aux.SOCNOMBRE, " +
				"aux.SOCAPEPAT, aux.SOCAPEMAT, aux.FNLPAGO," +
				"aux.REEMONTO,aux.REEFOLBIE,aux.REEFOLARAU " +
				"having count(b.reefolbie) = 1 ";
		List retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, idCaso);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				ReembolsoVO reem = new ReembolsoVO();
				reem.setRut(ors.getString("SOCRUT"));
				reem.setDv(ors.getString("SOCDV"));
				reem.setNombre(ors.getString("SOCNOMBRE"));
				reem.setApellidoMaterno(ors.getString("SOCAPEMAT"));
				reem.setApellidoPaterno(ors.getString("SOCAPEPAT"));
				reem.setOficina("");
				reem.setCasoId(ors.getLong("CASID"));
				reem.setCodigoReembolso(ors.getLong("TRECOD"));
				reem.setFolioTesoreriaBienestar(ors.getLong("REEFOLBIE"));
				reem.setFolioTesoreriaAraucana(ors.getLong("REEFOLARAU"));
				reem.setMontoReembolso(ors.getInt("REEMONTO"));

				retorno.add(reem);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;
	}	
	
	/**
	 * REQ 5030
	 */
	public List getAporteCobertura(long casoId) throws Exception, BusinessException{
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		
		//Solo se permite egresos con un solo caso.
		command = "SELECT DCAID, CASID, COBCOD, ABIMONTO FROM bfdta.bf17f1 WHERE casid = ?";
		List retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, casoId);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				AporteCobertura ap = new AporteCobertura();
				ap.setCasoID(ors.getLong("CASID"));
				ap.setCodigoCobertura(ors.getLong("COBCOD"));
				ap.setAporteBienestar(ors.getInt("ABIMONTO"));
				ap.setIdDetalle(ors.getInt("DCAID"));
				retorno.add(ap);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;		

	}
	
	/**
	 * REQ 5030
	 * @param casoId
	 * @param aporteBienestar
	 * @param codigoCobertura
	 * @param idDetalle
	 * @param user
	 * @return
	 * @throws GeneralException 
	 * @throws BusinessException 
	 * @throws Exception
	 * @throws BusinessException
	 */
	public boolean registraBitacoraElimCobertura(long casoID, int aporteBienestar, long codigoCobertura, int idDetalle, String user) throws GeneralException, BusinessException {
		
		boolean exito = false;
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "INSERT INTO BFDTA.BF33F1 (CASID, FECHA, USUARIO, IDDETALLE, COBCOD," +
				" ABIMONTO) VALUES(?, ?, ?, ?, ?, ?)";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, casoID);
			stmt.setDate(2, new java.sql.Date(new Date().getTime()));
			stmt.setString(3, user);
			stmt.setLong(4, idDetalle);
			stmt.setLong(5, codigoCobertura);
			stmt.setLong(6, aporteBienestar);
			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);
			

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
			exito = true;
		}
		
		return exito;
	}
	
	/**
	 * REQ 5030
	 * @param casoId
	 * @param aporteBienestar
	 * @param codigoCobertura
	 * @param idDetalle
	 * @param user
	 * @return
	 * @throws GeneralException 
	 * @throws BusinessException 
	 * @throws Exception
	 * @throws BusinessException
	 */
	public List getBitacoraElimCobertura(long casoId) throws Exception, BusinessException{
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		
		//Solo se permite egresos con un solo caso.
		command = "SELECT CASID, FECHA, USUARIO, IDDETALLE, COBCOD, ABIMONTO " +
				"FROM bfdta.bf33f1 WHERE casid = ?";
		List retorno = new ArrayList();
		                              
		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, casoId);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				AporteCobertura ap = new AporteCobertura();
				ap.setCasoID(ors.getLong("CASID"));
				ap.setCodigoCobertura(ors.getLong("COBCOD"));
				ap.setAporteBienestar(ors.getInt("ABIMONTO"));
				ap.setIdDetalle(ors.getInt("IDDETALLE"));
				ap.setUsuario(ors.getString("USUARIO"));
				ap.setFecha(ors.getDate("FECHA"));
				retorno.add(ap);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + retorno.size());
		return retorno;		

	}

	public void registraAporteBienestarBonificacionEspecial(AporteCobertura aporteCobertura, String user) throws Exception, BusinessException {

		if (aporteCobertura == null)
			throw new BusinessException("CCAF_BONIF_APORTECOBERTURAINVALIDO",
					"La Información del aporte de la cobertura es incorrecta");

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "INSERT INTO " + bonifDatabase
				+ ".BF34F1 (DCAID,CASID,COBCOD,ABIMONTO, USER, FECOP) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		try {

			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setInt(1, aporteCobertura.getIdDetalle());
			stmt.setLong(2, aporteCobertura.getCasoID());
			stmt.setLong(3, aporteCobertura.getCodigoCobertura());
			stmt.setLong(4, aporteCobertura.getAporteBienestar());
			stmt.setString(5, user);
			stmt.setDate(6, new java.sql.Date((new java.util.Date()).getTime()));

			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);
		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}
	
	/**
	 * REQ 5065
	 * Busca si ya se encuentra registrado un documento. 
	 * @param rutSocio
	 * @param casoId
	 * @param numDocumento
	 * @param tipoDocumento
	 * @return 
	 * @throws BusinessException
	 * @throws Exception
	 */
	public String getNumeroDocumento(String rutSocio, long casoId, String numDocumento, String tipoDocumento ) throws BusinessException, Exception{
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;
		String numeroDocumento = null;
		
		command = "SELECT CASNUMDOC " +
				  "FROM " + bonifDatabase + ".bf03f1 " +
				  "WHERE  socrut = ? " +
				  "and    casid  <> ? " +
				  "and    castipdoc = ? " +
				  "and    casnumdoc = ? ";
		                              
		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1, rutSocio);
			stmt.setLong(2, casoId);
			stmt.setString(3, tipoDocumento);
			stmt.setString(4, numDocumento);

			logger.debug("Inicia operación: " + command);
			
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);
			
			if (ors.next()) {
				numeroDocumento = ors.getString("CASNUMDOC");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("numeroDocumento: " + numeroDocumento);
		return numeroDocumento;
	}
	
	/**
	 * Obtiene la información del monto que se le debe transferir a cada banco en el proceso de reembolso seleccionado
	 * 
	 * @param codigoReembolso
	 * @return Lista DetalleBancoVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public DetalleBancoVO[] getDetalleBancos(long codigoReembolso) throws Exception, BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command = "select b.reebanco as banco , sum(a.reemonto) as monto " +
				"from " + bonifDatabase + ".bf18f1 a, " + bonifDatabase + ".bf36f1 b " +
				"where a.trecod = ? " +
				"and a.casid = b.casid " +
				"group by b.reebanco"; 
			
		ArrayList lista = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoReembolso);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				DetalleBancoVO detalleBancoVO = new DetalleBancoVO();
				detalleBancoVO.setCodigoBanco(ors.getString("banco"));
				detalleBancoVO.setMonto(ors.getLong("monto"));
				lista.add(detalleBancoVO);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + lista.size());
		    	
		return (DetalleBancoVO[] )lista.toArray( new DetalleBancoVO[lista.size()] );
		
	}	
	
	/**
	 * Obtiene la información del ultimo reembolso
	 * 
	 * @return ReembolsoTotalVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ReembolsoTotalVO getUltimoReembolsoTotal() throws Exception {
		
		ReembolsoTotalVO reemTot = new ReembolsoTotalVO();
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command = "SELECT A.TRECOD, A.TREFEC, A.TREFOLEBIE, A.TREFOLIBIE, A.TREFOLIARA, A.TRETOTAL, B.MAIL_ENVIADO " +
				"FROM " + bonifDatabase + ".BF22F1 A, " + bonifDatabase + ".BF37F1 B " +
				"WHERE A.TRECOD = B.TRECOD " +
						"order by A.trecod desc " +
						"fetch first 1 rows only";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);
 
			while (ors.next()) { 
				reemTot.setCodigo(ors.getLong("TRECOD"));
				reemTot.setFecha(ors.getDate("TREFEC"));
				reemTot.setFolioEgresoBienestar(ors.getLong("TREFOLEBIE"));
				reemTot.setFolioIngresoBienestar(ors.getLong("TREFOLIBIE"));
				reemTot.setFolioIngresoAraucana(ors.getLong("TREFOLIARA"));
				reemTot.setTotal(ors.getInt("TRETOTAL"));
				reemTot.setMailEnviado(ors.getString("MAIL_ENVIADO").equals("1") ? true : false);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		return reemTot;
	}
	
	/**
	 * Obtiene la información de los detalles de un reembolso semanal por Socio
	 * 
	 * @param codigoReembolso
	 * @return lista de ReembolsoSocioVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ReembolsoSocioVO[] getDetallesReembolsoPorSocio(long codigoReembolso) throws Exception {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "SELECT A.SOCRUT,B.DV SOCDV,b.FNNOMBRES SOCNOMBRE,"
				+ "b.FNAPELLPAT SOCAPEPAT,b.FNAPELLMAT SOCAPEMAT,"
				+ "REECORREO as CORREO, REETIPCTA as TIPO_CUENTA, REENUMCTA as CUENTA, REEBANCO as BANCO, "
				+ "sum (REEMONTO) as monto "
				+ "FROM " + bonifDatabase + ".BF18F1 A, " + bonifDatabase + ".FUNCIONARIOS B, "  + bonifDatabase + ".BF36F1 C "
				+ "WHERE A.SOCRUT = B.RUT "
				+ "AND A.CASID = C.CASID "
				+ "AND TRECOD = ? "
				+ "AND REEMONTO > 0 "  
//TODO quitar comentarios				
//				+ "AND REECORREO is not null "
//				+ "AND REECORREO != '' "  
				+ "group by A.SOCRUT,B.DV,b.FNNOMBRES, "
				+ "b.FNAPELLPAT,b.FNAPELLMAT, "
				+ "C.REECORREO, C.REETIPCTA, C.REENUMCTA, C.REEBANCO "
				+ "order by A.SOCRUT";

		ArrayList lista = new ArrayList();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1, codigoReembolso);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				ReembolsoSocioVO reem = new ReembolsoSocioVO();
				reem.setRut(ors.getString("SOCRUT"));
				reem.setDv(ors.getString("SOCDV"));
				reem.setNombre(ors.getString("SOCNOMBRE"));
				reem.setApellidoMaterno(ors.getString("SOCAPEMAT"));
				reem.setApellidoPaterno(ors.getString("SOCAPEPAT"));
				reem.setMontoReembolso(ors.getInt("monto"));
				reem.setBanco(ors.getString("BANCO"));
				reem.setTipoCuenta(ors.getString("TIPO_CUENTA"));
				reem.setCorreo(ors.getString("CORREO"));
				reem.setCuenta(ors.getString("CUENTA"));

				lista.add(reem);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		logger.debug("Retornaron: " + lista.size());
		
		return (ReembolsoSocioVO[] )lista.toArray( new ReembolsoSocioVO[lista.size()] );
	}
	
	/**
	 * Obtiene los parámetros configurados
	 * 
	 * @return HashMap con lista de ParametroVO
	 * @throws Exception
	 */
	public HashMap getParametros() throws Exception {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;

		String command;

		command = "SELECT PARCOD, PARDES, PARVALOR "
				+ "FROM " + bonifDatabase + ".BF35F1";

		HashMap hashParam  = new HashMap();

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);

			while (ors.next()) {
				ParametroVO param = new ParametroVO();
				param.setCodigo(ors.getString("PARCOD"));
				param.setDescripcion(ors.getString("PARDES"));
				param.setValor(ors.getString("PARVALOR"));

				hashParam.put(param.getCodigo(), param);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
		
		return hashParam;
	}
	
	/**
	 * Verifica si el usuario tiene casos activo y es disvinculado de la Caja
	 *  
	 * 
	 * @return HashMap con lista de ParametroVO
	 * @throws Exception
	 */
	public boolean isSocioInactivosConCasosAbiertosByRut(String rut) throws BusinessException,Exception {
			
			Connection conn = null;
			CallableStatement stmt = null;
			ResultSet ors = null;
			String command;
			boolean isInactivo= false;
			
			command = "SELECT a.RUT "
			+ "FROM "
			+ bonifDatabase
			+ ".funcionarios a, "
			+ bonifDatabase
			+ ".bf03f1 b "
			+ "WHERE a.rut = b.socrut and b.socrut = ? and B.CASEST = ? and A.FNESTADO = ? and "
			+"casid not in (select casid from "+bonifDatabase+".bf32f1) "
			+ "group by a.RUT, a.DV, a.FNNOMBRES, a.FNAPELLPAT,a.FNAPELLMAT,"
			+ " a.fNCODCARG, a.FNFECHEGR";
			
			try {
				conn = DB2Utils.createConnection(bonifJNDIDataSource);
				stmt = conn.prepareCall(command);
			
				stmt.setString(1, rut);
				stmt.setString(2, Caso.STD_ACTIVO);
				stmt.setString(3, Socio.STD_ELIMINADO);
			
				logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);
			
			while (ors.next()) {
				isInactivo= true;
			}
			
			} catch (SQLException ex) {
				ex.printStackTrace();
				int code = ex.getErrorCode();
				throw new BusinessException(PREFIX + code);
			} finally {
				DB2Utils.closeAll(conn, stmt, ors);
			}
			logger.debug("Retornaron: " + rut + "es inactivo");
			return isInactivo;
		}
	/**
	 * Cambia el estado, fecha y deja en estado NO los campos (CASINDDES, CASINDBON, CASINDPAG) del caso
	 * 
	 * @param CasoAbiertoVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void finalizaCasoEnProceso(Caso caso) throws Exception,BusinessException {

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String command;

		command = "UPDATE " + bonifDatabase
				+ ".BF03F1 SET CASEST = ?, CASFECEST = ?,  CASINDDES = ?, CASINDBON = ?, CASINDPAG = ? " + "WHERE CASID = ?";

		try {
			conn = DB2Utils.createConnection(bonifJNDIDataSource);
			stmt = conn.prepareCall(command);

			stmt.setString(1, caso.getEstado());
			stmt.setTimestamp(2, new java.sql.Timestamp(caso.getFechaEstado().getTime()));
			stmt.setString(3, caso.getIndicadorDescontado());
			stmt.setString(4, caso.getIndicadorBonificacion());
			stmt.setString(5, caso.getIndicadorPago());
			stmt.setLong(6, caso.getCasoID());
			
			logger.debug("Inicia operación: " + command);
			stmt.execute();
			logger.debug("Finaliza operación: " + command);

		} catch (SQLException ex) {
			ex.printStackTrace();
			int code = ex.getErrorCode();
			throw new BusinessException(PREFIX + code);
		} finally {
			DB2Utils.closeAll(conn, stmt, ors);
		}
	}
}

