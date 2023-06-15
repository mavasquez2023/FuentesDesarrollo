package ztest;

import java.io.IOException;
import java.sql.SQLException;

import org.json.JSONException;

import cl.jfactory.planillas.service.helper.WorkFlowHelper;

public class TestLimpieza {
	public static void main(String[] args) throws JSONException, SQLException, IOException, InterruptedException {
		
		new WorkFlowHelper().limpiarCarpetaPublicacion(null, null);
		
	}
}
