package cl.araucana.Processor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

import cse.database.dao.exception.DAOException;
import cse.model.service.data.EvaluarCondicionesResponse;

public class As400DAO {

	private static Logger logger = Logger.getLogger(As400DAO.class.getName());

	private String id = null;
	private String as400id = null;
	private EvaluarCondicionesResponse respuesta = null;
	private HashMap payload = null;
	private String payloadOriginal = null;

	private Properties p = null;
	private String ip;
	private String biblioteca;
	private String biblioteca2;	
	private String archivo;
	private String programa;

	ConnectionHelper helper;

	public As400DAO(String id, String as400id, EvaluarCondicionesResponse respuesta, HashMap payload, String payloadOriginal) {
		super();
		this.id = id;
		this.as400id = as400id;
		this.respuesta = respuesta;
		this.payload = payload;
		this.payloadOriginal = payloadOriginal;

		p = new Properties();
		p.put("user", System.getProperty("cl.araucana.as400.user"));
		p.put("password", System.getProperty("cl.araucana.as400.password"));
		p.put("naming", "sql");

		this.ip = System.getProperty("cl.araucana.as400.server");
		this.biblioteca = System.getProperty("cl.araucana.as400.biblioteca");
		this.biblioteca2 = System.getProperty("cl.araucana.as400.biblioteca2");

		this.archivo = System.getProperty("cl.araucana.as400.archivo");
		this.programa = System.getProperty("cl.araucana.as400.programa");

		helper = new ConnectionHelper();
	}

	public void informarError(int i) throws SQLException {
		if (as400id == null) {
			informarErrorPlainToDb(i);
		}
	}
	
	//@DEPRECATED!!!!!
	public void informarErrorPlainToDb(int i) throws SQLException {

		String fecha = StructuredStringHelper.parseId("fecha",id);
		String hora = StructuredStringHelper.parseId("hora",id);
		String ofipro = StructuredStringHelper.parseId("ofipro",id);

		String query = null;

		Connection connection = null;
		try {
			System.out.println("A punto de obtener la conexion a AS400");

			DriverManager.registerDriver(new com.ibm.as400.access.AS400JDBCDriver());
			connection = DriverManager.getConnection("jdbc:as400://" + ip + "/" + biblioteca, p);

			System.out.println("Obtuve la conexion a AS400");

			Statement mySelect = connection.createStatement();

			query = " UPDATE " + archivo + " SET ESTMOT = 'R', RESPUE = 1, "
			+ "RUTAFI = RUTMOT, DVAFI ='" + (String) payload.get("digito") + "'"
			+ ", NUMSOL = " + "'" + id + "' " + "WHERE RUTMOT = "
			+ (String) payload.get("rut") + " AND FECMOT = " + fecha + " AND HORMOT = "
			+ hora + " AND OFIPRO = " + ofipro;

			Object[] params = new Object[] { query };
			System.out.println("Consulta: " + query);
			logger.log(Level.FINE, "Ejecutando la consulta ", params);
			int respuesta = mySelect.executeUpdate(query);
			logger.log(Level.FINE, "Consulta ejecutada ", params);

		} catch (SQLException e) {
			logger.log(Level.SEVERE, "No se pudo escribir en la tabla AS/400 " +
					biblioteca + "." + archivo + "' de un error en" +
					" la tabla AS/400 ", e);
			throw e;
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// Hacer caso omiso.
			}
		}
	}

	public int updateRecord() throws SQLException{

		if (as400id == null) {
			//@DEPRECATED
			//return updatePlainToDB();
			return 0;
		}
		else return sendToStoredProcedure();
	}

	//@DEPRECATED!!!!!!
	private int updatePlainToDB() throws SQLException {

		String fecha = StructuredStringHelper.parseId("fecha",id);
		String hora = StructuredStringHelper.parseId("hora",id);
		String ofipro = StructuredStringHelper.parseId("ofipro",id);

		Connection connection = null;

		HashMap mapaCond = null;
		try {

			DriverManager.registerDriver(new com.ibm.as400.access.AS400JDBCDriver());

			logger.log(Level.FINE, "A punto de obtener la conexion a AS400");
			connection = DriverManager.getConnection("jdbc:as400://" + ip + "/" + biblioteca, p);
			logger.log(Level.FINE, "Obtuve la conexion a AS400");

			mapaCond = StructuredStringHelper.parseCondiciones(respuesta);

			Statement mySelect = connection.createStatement();

			StringBuffer query = new StringBuffer();
			query.append("UPDATE " + archivo + " SET ESTMOT = 'R', RESPUE = 0, ");
			query.append("RUTAFI = " + StringUtils.leftPad((String) payload.get("rut"), 9, "0") + ", ");
			query.append("DVAFI = '" + (String) payload.get("digito") + "', ");
			query.append("NUMSOL = '" + (String)respuesta.getIdSolicitud() + "', ");
			query.append("PERFIL = '" + (String)respuesta.getPerfilRiesgo() + "', ");
			query.append("SCORE = " + respuesta.getScore().intValue() + ", ");
			query.append("RENTAS = " + respuesta.getNumSueldos() + ", ");
			query.append("ENDMAX = " + respuesta.getEndeudMax() + ", ");
			query.append("AVAL = '" + (String) mapaCond.get("A") + "', ");
			query.append("SEGCES = '" + (String) mapaCond.get("SC") + "', ");
			query.append("AVASC = '" + (String) mapaCond.get("ASC") + "', ");
			query.append("RIESGO = '" + (String) mapaCond.get("DR") + "', ");
			query.append("COMITE = '" + (String) mapaCond.get("CC") + "', ");
			query.append("USOFUT = '" + StringUtils.leftPad("0", 141, "0") + "' ");

			query.append("WHERE ");
			query.append("RUTMOT = " + (String) payload.get("rut"));
			query.append(" AND FECMOT = " + fecha + " AND HORMOT = ");
			query.append(hora + " AND OFIPRO = " + ofipro);

			Object[] params = new Object[] { query };

			logger.log(Level.FINE, "Ejecutando la consulta de ingreso de datos a AS/400", params);
			mySelect.executeUpdate(query.toString());
			logger.log(Level.FINE, "Consulta ejecutada AS/400 ejecutada", params);

		} catch (SQLException e) {
			logger.log(Level.SEVERE, "No se pudo actualizar la tabla AS/400 con la respuesta de" +
					" la evaluacion crediticia", e);
			throw e;
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// Hacer caso omiso.
			}
		}
		return 0;
	}

	/**
	 * Envia la respuesta a un procedimiento almacenado
	 * @return
	 * @throws SQLException
	 */
	public int sendToStoredProcedure() throws SQLException {

		String tramaRespuesta = null;
		StringBuffer buf = new StringBuffer();

		buf.append(StructuredStringHelper.buildAS400Response(id, as400id, payload, payloadOriginal, respuesta));
		tramaRespuesta = buf.toString();

		CallableStatement proc;
		String resultado = null;
		try {
			proc = helper.getConnection().prepareCall("CALL " + programa + "(?)");

			proc.setString(1,tramaRespuesta);
			proc.registerOutParameter(1,Types.VARCHAR);
			
			logger.log(Level.INFO,"Se enviara respuesta a AS/400 con los siguientes componentes:");
			logger.log(Level.INFO,"id de trama SCORB   : [" + id + "]");
			logger.log(Level.INFO,"id original SCORR   : [" + as400id + "]");
			logger.log(Level.INFO,"pregunta original   : [" + payloadOriginal + "]");
			logger.log(Level.INFO,"Respuesta formateada: [" +
					StructuredStringHelper.buildResponseString(payload,respuesta) + "]");

			proc.execute();
			
			resultado = proc.getString(1);
			proc.close();

			//chooseBehaviour(resultado, tramaRespuesta);

			logger.log(Level.INFO,"Se envio respuesta de evaluacion Crediticia a AS400," +
					" recibiendo codigo de confirmacion "
					+ StructuredStringHelper.parseCodigoRespuestaAS400(resultado));
			
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "No se pudo enviar la respuesta al programa AS/400 con" +
					" la evaluacion crediticia", e);
			throw e;
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			helper.closeConnection();
		}

		return 1;

	}

	//@DEPRECATED!!!
	private void chooseBehaviour(String resultado, String tramaRespuesta) throws SQLException{

		String codigo = StructuredStringHelper.parseCodigoRespuestaAS400(resultado);

		logger.log(Level.INFO, "Se recibio codigo " + codigo + " desde AS/400 despues de respuesta a solicitud");

		String miResultado = null;
		if ("0005".equals(codigo) || "9998".equals(codigo)) {
			CallableStatement proc;
			try {
				Thread.sleep(500);
				proc = helper.getConnection().prepareCall("CALL " + programa + "(?)");

				proc.setString(1,tramaRespuesta);
				proc.registerOutParameter(1,Types.VARCHAR);
				proc.execute();
				miResultado = proc.getString(1);
				proc.close();

				chooseBehaviour(resultado, tramaRespuesta);


			} catch (SQLException e) {
				logger.log(Level.SEVERE, "No se pudo enviar la respuesta al programa AS/400 con" +
						" la evaluacion crediticia", e);
				throw e;
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				//				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				helper.closeConnection();
			}

		}


	}




}
