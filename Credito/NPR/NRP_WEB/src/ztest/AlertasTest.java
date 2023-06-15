package ztest;

import cl.jfactory.planillas.service.helper.AlertasHelper;

public class AlertasTest {

	
	public static void main(String[] args) {
		
		ejecutar();
		
	}
	
	public static void ejecutar(){
		try{
			
			int i=0;
			int j = 10 / i;
		}catch(Exception e){
			e.printStackTrace();
			new AlertasHelper().procesarAlertaError("ERROR_GENERICO",e);
		}
	}
}
