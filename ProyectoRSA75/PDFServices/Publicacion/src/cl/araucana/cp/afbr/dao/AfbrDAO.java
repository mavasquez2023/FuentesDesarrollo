package cl.araucana.cp.afbr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import cl.araucana.cp.afbr.business.ParametrosReporteBean;
import cl.araucana.cp.afbr.business.PlanillaBean;
import cl.araucana.cp.hibernate.beans.MesesbeanVO;

public class AfbrDAO {

	public AfbrDAO() {

	}

	public Connection getConnection() {
		try {

			Hashtable env = new Hashtable();
			env.put(Context.INITIAL_CONTEXT_FACTORY,
					"com.ibm.websphere.naming.WsnInitialContextFactory");
			javax.naming.Context ctx = new InitialContext(env);

			// **cambiar aqui**DataSource ds =
			// (DataSource)ctx.lookup("jdbc/cppub");
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cppub");
			ctx.close();
			return ds.getConnection();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public List getDatosAfbr() {

		List lista = new ArrayList();

		Connection con = this.getConnection();
		ResultSet rs = null;

		String sql = "";
		sql += "select PWCITIPRO,PWCIFOLIO, PWCICOPRO, PWCIRUTPA ";
		sql += " || '-' || PWCIDIGPA as rutEmpresa,PWCICONVE, PWCISUCUR ";
		sql += " from pw1407c002.pwf1000";
		int i = 0;

		String SQL = sql.toString();

		try {

			PreparedStatement press = con.prepareStatement(SQL);
			rs = press.executeQuery();

			if (rs.next()) {
				do {

					PlanillaBean obean = new PlanillaBean();
					i++;
					obean.setTipoProceso(rs.getString(1));
					obean.setFolio(rs.getString(2));
					obean.setFechaProceso(rs.getString(3));
					obean.setRutEmpresa(rs.getString(4));
					obean.setConvenio(rs.getString(5));
					obean.setSucursal(rs.getString(6));
					obean.setId(String.valueOf(i));

					lista.add(obean);

				} while (rs.next());
			}
			return lista;

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ignored) {
			}
		}

		return null;
	}

	public ParametrosReporteBean getDatosReporte(String codigoProceso,
			String empag, String folio, String rutEmpresa) {

		Connection con = this.getConnection();
		ResultSet rs = null;

		String sql = "";
		sql += "SELECT PWCACOPRO, PWCAEMPAG, PWCAFOLIO, PWCARUTEM, PWCADIGEM, ";
		sql += "PWCASECFO, PWCATIPRO, PWCARAZSO, PWCAACTEC, PWCADIREM, ";
		sql += "PWCACOMEM, PWCACIUEM, PWCAREGEM, PWCAEMAIL, PWCATELEM, ";
		sql += "PWCANOMRE, PWCARUTRE, PWCADIGRE, PWCACAMRE, PWCATOTRA, ";
		sql += "PWCATOAPO,PWCATOPAG, PWCAREMIM, PWCATIPAG, PWCAFEDES, ";
		sql += "PWCAFEHAS, PWCAFEPAG, PWCATOREM, PWCAHOJAN, PWCACONVE, ";
		sql += "PWCASUCUR, PWCACDHOL,PWCADECLA FROM PWDTAD.PWF8300 ";
		sql += "where PWCACOPRO =" + codigoProceso + " and trim(PWCAEMPAG) ='"
				+ empag.trim();
		sql += "' and cast(PWCAFOLIO as NUMERIC(6,0)) = "
				+ Integer.parseInt(folio) + " and PWCARUTEM = " + rutEmpresa;

		ParametrosReporteBean obean = new ParametrosReporteBean();

		try {

			PreparedStatement press = con.prepareStatement(sql);
			rs = press.executeQuery();

			if (rs.next()) {

				obean.setPWCACOPRO(rs.getString(1));
				obean.setPWCAEMPAG(rs.getString(2));
				obean.setPWCAFOLIO(rs.getString(3));
				obean.setPWCARUTEM(rs.getString(4));
				obean.setPWCADIGEM(rs.getString(5));
				obean.setPWCASECFO(rs.getString(6));
				obean.setPWCATIPRO(rs.getString(7));
				obean.setPWCARAZSO(rs.getString(8));
				obean.setPWCAACTEC(rs.getString(9));
				obean.setPWCADIREM(rs.getString(10));
				obean.setPWCACOMEM(rs.getString(11));
				obean.setPWCACIUEM(rs.getString(12));
				obean.setPWCAREGEM(rs.getString(13));
				obean.setPWCAEMAIL(rs.getString(14));
				obean.setPWCATELEM(rs.getString(15));
				obean.setPWCANOMRE(rs.getString(16));
				obean.setPWCARUTRE(rs.getString(17));
				obean.setPWCADIGRE(rs.getString(18));
				obean.setPWCACAMRE(rs.getString(19));
				obean.setPWCATOTRA(rs.getString(20));
				obean.setPWCATOAPO(rs.getString(21));
				obean.setPWCATOPAG(rs.getString(22));
				obean.setPWCAREMIM(rs.getString(23));
				obean.setPWCATIPAG(rs.getString(24));
				obean.setPWCAFEDES(rs.getString(25));
				obean.setPWCAFEHAS(rs.getString(26));
				obean.setPWCAFEPAG(rs.getString(27));
				obean.setPWCATOREM(rs.getString(28));
				obean.setPWCAHOJAN(rs.getString(29));
				obean.setPWCACONVE(rs.getString(30));
				obean.setPWCASUCUR(rs.getString(31));
				obean.setPWCACDHOL(rs.getString(32));
				obean.setPWCADECLA(rs.getString(33));

			}
			return obean;

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ignored) {
			}
		}

		return null;
	}

	public List getDatosReporteGrilla(MesesbeanVO omeses) {

		List lista = new ArrayList();

		Connection con = this.getConnection();
		ResultSet rs = null;

		String sql = "";
		sql += "SELECT PWCACOPRO, PWCAFOLIO, PWCARUTEM, PWCADIGEM,";
		sql += "PWCATIPRO,PWCARUTRE, PWCADIGRE,trim(PWCAEMPAG) as PWCAEMPAG,";
		sql += "PWCACONVE,PWCASUCUR FROM PWDTAD.PWF8300 where 1=1 ";
		if (!omeses.getConvenio().trim().equals("")) {
			sql += " and PWCACONVE in ('" + omeses.getConvenio() + "')";
		}
		if (!omeses.getHolding().trim().equals("")) {
			sql += " and PWCACDHOL in (" + omeses.getHolding() + ")";
		}
		if (!omeses.getRutEmpresa().trim().equals("")) {
			sql += " and PWCARUTEM in (" + omeses.getRutEmpresa() + ") ";
		}
		if (!omeses.getTipoProceso().equals("T")) {
			sql += " and PWCATIPRO = '" + omeses.getTipoProceso() + "'";
		}
		if (!omeses.getFecha1().trim().equals("")&&!omeses.getFecha2().trim().equals("")) {
			sql += " and PWCACOPRO >= " + Integer.parseInt(omeses.getFecha1())
					+ " and PWCACOPRO <= "
					+ Integer.parseInt(omeses.getFecha2());
		}
		sql += " order by 1,3,9 fetch first 200 rows only";

		System.out.println(">>query: " + sql);
		int i = 0;

		try {

			PreparedStatement press = con.prepareStatement(sql);
			rs = press.executeQuery();

			if (rs.next()) {
				do {

					ParametrosReporteBean obean = new ParametrosReporteBean();
					i++;

					obean.setId(i + "");
					obean.setPWCACOPRO(rs.getString("PWCACOPRO"));
					obean.setPWCAFOLIO(rs.getString("PWCAFOLIO"));
					obean.setPWCARUTEM(rs.getString("PWCARUTEM"));
					obean.setPWCADIGEM(rs.getString("PWCADIGEM"));
					obean.setPWCATIPRO(rs.getString("PWCATIPRO"));
					obean.setPWCACONVE(rs.getString("PWCACONVE"));
					obean.setPWCASUCUR(rs.getString("PWCASUCUR"));
					obean.setPWCAEMPAG(rs.getString("PWCAEMPAG"));
					obean.setFullRutEmpresa();

					lista.add(obean);

				} while (rs.next());
			}
			return lista;

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ignored) {
			}
		}

		return null;
	}

}
