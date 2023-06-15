package ztest;

import java.util.HashMap;

import cl.jfactory.planillas.service.util.ITerminadorHebra;
import cl.jfactory.planillas.service.util.PoolHebras;

public class TestPoolHebras {

	
	public static void main(String[] args) {
		
		
		new PoolHebras(10, "ztest.TestImplementacionHebra", "procesando", new Class[]{HashMap.class}, new Object[]{null}, new ITerminadorHebra() {
			
			public void notificarCierre(int tipo) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
	}
	
}
