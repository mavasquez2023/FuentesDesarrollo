package cl.araucana.tesoreria.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import cl.araucana.tesoreria.dao.ReporteTesoreriaDB2DAO;
import cl.araucana.tesoreria.dao.dvo.ComprobanteIngresoDVO;
import cl.araucana.tesoreria.dao.dvo.GenericDVO;
import cl.araucana.tesoreria.dao.dvo.ReporteComprobantesDVO;
import cl.araucana.tesoreria.modelo.Usuario;

import com.schema.util.dao.DB2Utils;

public class ReporteTesoreriaDB2DAOImpl implements ReporteTesoreriaDB2DAO {
	private DB2Utils db2Util;
	public ReporteTesoreriaDB2DAOImpl() {
		String datasource = "jdbc/corporativo";
		db2Util = new DB2Utils(datasource, this);
	}
	private Object getUnique(Collection<?> coleccion) {
		if (coleccion == null || coleccion.isEmpty())
			return null;
		return coleccion.iterator().next();
	}
	private static Date getDate(String fecha) {
		if (fecha == null)
			return null;
		if ("0001-01-01".equals(fecha) || "1900-01-01".equals(fecha))
			return null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.parse(fecha);
		} catch (Exception ex1) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
				return sdf.parse(fecha);
			} catch (Exception ex2) {
				return null;
			}
		}
	}
	public static String getCleanData(String data) {
		return data == null ? null : (data.trim().equals("") ? null : data.trim());
	}
	public static GenericDVO buildGenericDVO(ResultSet srs) throws SQLException {
		GenericDVO dvo = new GenericDVO();
		dvo.setString(getCleanData(srs.getString("DATA_STRING")));
		dvo.setNumber(srs.getLong("DATA_NUMBER"));
		dvo.setDecimal(srs.getDouble("DATA_DECIMAL"));
		dvo.setDate(getDate(srs.getString("DATA_DATE")));
		return dvo;
	}
	public static ComprobanteIngresoDVO buildComprobanteIngresoDVO(ResultSet ors) throws SQLException {
		ComprobanteIngresoDVO dvo = new ComprobanteIngresoDVO();
		dvo.setOficinaNombre(ors.getString("CMBA"));
		dvo.setOficinaCodigo(ors.getString("CMCA"));
		dvo.setFolio(ors.getString("TE3WA"));
		dvo.setEstado(ors.getString("TE3YA"));
		dvo.setCodigoBarra(ors.getString("TEA7A"));
		dvo.setMonto(ors.getString("TE7NA"));
		dvo.setAreaNegocio(ors.getString("TEQA"));
		dvo.setFecha(ors.getString("TE3ZA"));
		dvo.setNombre(ors.getString("SAJKM92"));
		dvo.setRut(ors.getString("TE42A"));  
		dvo.setDV(ors.getString("TE43A")); 
		return dvo;
	}
	public static ReporteComprobantesDVO buildReporteComprobantesDVO(ResultSet ors) throws SQLException {
		ReporteComprobantesDVO dvo = new ReporteComprobantesDVO();
		dvo.setComprobantesMonto(ors.getString("TE7NA"));
		dvo.setComprobantesNumero(ors.getString("TE3WA"));
		dvo.setOficinaCodigo(ors.getString("CMBA"));
		dvo.setOficinaNombre(ors.getString("CMCA"));
		return dvo;
	}
	public void crearUsuario(Usuario usuarioDVO) throws Exception {
		String command = "INSERT INTO TEDTA.REPORTEWEB_ROL (RUT, DV, ROL) VALUES (?, ?, ?)";
		db2Util.prepareCall(command);
		db2Util.getStatement().setString(1, usuarioDVO.getRut());
		db2Util.getStatement().setString(2, usuarioDVO.getDv());
		db2Util.getStatement().setString(3, usuarioDVO.getRol());
		db2Util.executeUpdate();
	}
	public void eliminarUsuario(Usuario usuarioDVO) throws Exception {
		String command = "INSERT INTO TEDTA.REPORTEWEB_LOG(RUT, DV, ROL, ELIMINACION) VALUES (?,?,(SELECT ROL FROM BEDTA.USUARIOS WHERE RUT = ?),CURRENT TIMESTAMP)";
		db2Util.prepareCall(command);
		db2Util.getStatement().setString(1, usuarioDVO.getRut());
		db2Util.getStatement().setString(2, usuarioDVO.getDv());
		db2Util.getStatement().setString(3, usuarioDVO.getRut());
		db2Util.executeUpdate();
		command = "DELETE FROM TEDTA.REPORTEWEB_ROL RUT = ? AND DV = ?";
		db2Util.prepareCall(command);
		db2Util.getStatement().setString(1, usuarioDVO.getRut());
		db2Util.getStatement().setString(2, usuarioDVO.getDv());
		db2Util.executeUpdate();
	}
	public String obtenerRolUsuario(String usuario) throws Exception {
		String command = "SELECT ROL AS DATA_STRING FROM TEDTA.REPORTEWEB_ROL WHERE RUT = ?";
		db2Util.prepareCall(command);
		db2Util.getStatement().setString(1, usuario);
		GenericDVO obj = (GenericDVO) this.getUnique(db2Util.executeQuery(GenericDVO.class));
		if (obj == null)
			return null;
		return obj.getString();
	}
	public ComprobanteIngresoDVO[] obtenerComprobantesIngresoPorOficina(String fechaDesde, String fechaHasta, String oficina)
		throws Exception {
		String command = "select o.CMCA, t.CMBA, t.TE3WA, t.TE3YA, t.TEA7A, t.TE7NA, t.TEQA, t.TE3ZA, t.SAJKM92, t.TE42A, t.TE43A from tedta.te07f1 t  left join cmdta.cm01f1 o on t.CMBA = o.CMBA where o.CMBA = ? and t.TE3ZA between ? and ? and t.TE3XA ='I' and t.TE3YA ='I' and substr(t.TEA7A, 1, 1) = '4' order by t.CMBA, t.TE3ZA, t.TE3YA";
		db2Util.prepareCall(command);
		db2Util.getStatement().setString(1, oficina);
		db2Util.getStatement().setString(2, fechaDesde);
		db2Util.getStatement().setString(3, fechaHasta);
		ArrayList comprobantes = (ArrayList) db2Util.executeQuery(ComprobanteIngresoDVO.class);
		return (ComprobanteIngresoDVO[]) comprobantes.toArray(new ComprobanteIngresoDVO[comprobantes.size()]);
	}
	
	public ReporteComprobantesDVO[] obtenerReporteComprobantes(String fechaDesde, String fechaHasta) throws Exception {
		String command = "select  b.cmca,  a.cmba, b.cmca, count(te3wa) as te3wa, sum(a.te7na) as te7na from tedta.te07f1 a left join cmdta.cm01f1 b on a.cmba = b.cmba where TE3ZA between ? and ? and TE3XA ='I' and TE3YA ='I' and substr(TEA7A, 1, 1) = '4' group by a.cmba, b.cmca order by a.cmba";
		db2Util.prepareCall(command);
		db2Util.getStatement().setString(1, fechaDesde);
		db2Util.getStatement().setString(2, fechaHasta);
		ArrayList reporte = (ArrayList) db2Util.executeQuery(ReporteComprobantesDVO.class);
		return (ReporteComprobantesDVO[]) reporte.toArray(new ReporteComprobantesDVO[reporte.size()]);
	}
	
	public ReporteComprobantesDVO[] obtenerReporteComprobantesPorOficina(String fechaDesde, String fechaHasta, String oficina) throws Exception {
		String command = "select  b.cmca,  a.cmba, b.cmca, count(te3wa) as te3wa, sum(a.te7na) as te7na from tedta.te07f1 a left join cmdta.cm01f1 b on a.cmba = b.cmba where a.cmba= ? and TE3ZA between ? and ? and TE3XA ='I' and TE3YA ='I' and substr(TEA7A, 1, 1) = '4' group by a.cmba, b.cmca order by a.cmba";
		db2Util.prepareCall(command);
		db2Util.getStatement().setString(1, oficina);
		db2Util.getStatement().setString(2, fechaDesde);
		db2Util.getStatement().setString(3, fechaHasta);
		ArrayList reporte = (ArrayList) db2Util.executeQuery(ReporteComprobantesDVO.class);
		return (ReporteComprobantesDVO[]) reporte.toArray(new ReporteComprobantesDVO[reporte.size()]);
	}
}
