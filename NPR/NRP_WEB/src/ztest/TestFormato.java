package ztest;

import java.util.HashMap;

import org.json.JSONException;

import cl.jfactory.planillas.service.helper.ConfiguradorHelper;

public class TestFormato {

	public static void main(String[] args) {
		HashMap parameters = new HashMap();
		HashMap session = new HashMap();
		try {
			HashMap formato = new ConfiguradorHelper().obtenerConfiguracionFormato(session, parameters);
			System.out.println("formato -> " + formato);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
