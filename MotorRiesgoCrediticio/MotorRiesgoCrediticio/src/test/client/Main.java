package test.client;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;

import cse.dao.factory.impl.DAOFactoryImpl;
import cse.database.dao.exception.DAOException;
import cse.database.dao.jdbc.JdbcUtil;
import cse.database.objects.Solicitud;
import cse.legacy.connection.AS400ProgramExecution;
import cse.legacy.connection.AS400ProgramExecutionException;
import cse.legacy.connection.impl.AS400ProgramExecutionImpl;
import cse.model.businessobject.CondicionOtorgamiento;
import cse.model.businessobject.Monto;
import cse.model.businessobject.Rut;
import cse.model.exception.ScoringModuleException;
import cse.model.service.CondicionesOtorgamientoService;
import cse.model.service.data.EvaluarCondicionesResponse;
import cse.model.service.impl.CondicionesOtorgamientoServiceImpl;


public class Main {
	
	Logger log = Logger.getLogger(Main.class.getName());
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Main main = new Main();
		main.testFlow();
		
	}

	private void testFormat() {
		String uid = JdbcUtil.getUniqueSolicitudId(null);		 
		System.out.println(uid);
	}

	public void testAS400(){
		AS400ProgramExecution program = new AS400ProgramExecutionImpl();
		try {
//			program.execute("020339366", "0");
			program.execute("12563", "0");
		} catch (AS400ProgramExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public void testFlow() {
		try {
			CondicionesOtorgamientoService myService = new CondicionesOtorgamientoServiceImpl();
			Rut rut = new Rut("20339366", "0");
			Monto monto = new Monto(19578754);
			int  tipoAfiliado = 0;
			EvaluarCondicionesResponse response = (EvaluarCondicionesResponse) myService.evaluarCondicionesOtorgamiento(rut, monto, tipoAfiliado);
			CondicionOtorgamiento[] listado = response.getCondiciones();
			for (int i = 0; i < listado.length; i++) {
				CondicionOtorgamiento condicionOtorgamiento = listado[i];
				System.out.println(condicionOtorgamiento.getNombre() + " - " + condicionOtorgamiento.getDescripcion());
			}

		} catch (ScoringModuleException e) {
			e.printStackTrace();
		}

	}

	/*
	 * "82217686-824e-4926-830e-02b1e542674c"
	 * "e087f5af-dba7-4b73-ad9a-032ad499f118"
	 * "181e56cc-063b-422a-8559-0346fa52d707"
	 * "617fccdd-40a2-491a-8701-061a9792b9fb"
	 */


	public void testJaimeProcedure() {
		String idSolicitud = null;
		try {
			Solicitud solicitud = DAOFactoryImpl.getInstance().getSolicitudDAO()
					.selectByPrimaryKey("82217686-824e-4926-830e-02b1e542674c");
			idSolicitud = solicitud.getIdsolicitud();
			System.out.println(idSolicitud);
			int result = DAOFactoryImpl.getInstance().getEvaluaCreditScoringPersonasDAO()
					.execute(idSolicitud);
			System.out.println("result : " + result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	public void testMyProcedure() throws SQLException {		
		try {
//			sqlMapClient = DAOFactoryImpl.getInstance().getSqlMap();
//			sqlMapClient.startTransaction();
			
			HashMap params = new HashMap();
//			params.put("first", new Integer(100));
			params.put("first", new Integer(100));
			params.put("second", "Centésimos");		
			params.put("third", "DebiCambiar");
			
//			sqlMapClient. queryForObject("MotorCreditScoring_dbo_StoredProcedures.myProcedure", params);
			Object returnValue = params.get("result");
			System.out.println(returnValue);
			Object result = params.get("third");
			System.out.println(result);
			
//			sqlMapClient.commitTransaction();
			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (DAOException e) {
//			e.printStackTrace();
		} finally {
//			sqlMapClient.endTransaction();
		}
	
	}

}
