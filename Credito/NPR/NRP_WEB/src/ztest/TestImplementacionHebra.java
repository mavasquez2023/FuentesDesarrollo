package ztest;

import java.util.HashMap;

public class TestImplementacionHebra {

	
	public static void procesando(HashMap parametros) throws InterruptedException{
		Thread.sleep( (int)(Math.random()*10000 ) );
		System.out.println("procesando...."+ parametros);
	}
}
