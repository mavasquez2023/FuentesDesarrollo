package ztest;

import java.sql.SQLException;
import java.util.HashMap;

import org.json.JSONException;

import cl.jfactory.planillas.service.helper.WorkFlowHelper;
import cl.liv.comun.utiles.UtilesComunes;

public class TestEnvioSAP {
	public static void main(String[] args) throws JSONException, SQLException, InterruptedException {

		UtilesComunes.variablesEstaticas.put("sys.YearMonth", "201902");
		new WorkFlowHelper().enviarNominaSAP(new HashMap(), new HashMap());
	};
}
