package ztest;

import cl.jfactory.planillas.service.helper.AlertasHelper;
import cl.jfactory.planillas.service.helper.WorkFlowHelper;

public class TestHelper {

	public static void main(String[] args) {
		
		AlertasHelper.procesarAlertaCambioEstado("E1", "E2");
		
	}
}
