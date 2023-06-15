package ztest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.json.JSONException;

import cl.jfactory.planillas.carga.sap.impl.ProcesoCargaSAP;
import cl.jfactory.planillas.service.helper.GeneradorNominasHelper;
import cl.jfactory.planillas.service.helper.WorkFlowHelper;

public class TestGeneracionComprobantes {

	public static void main(String[] args) {
		try {
			new WorkFlowHelper().generarComprobantes(null, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
