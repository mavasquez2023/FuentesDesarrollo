package ztest;

import java.util.Date;

import cl.liv.archivos.as400.impl.ProxyAS400;

public class TestFolios {

	public static void main(String[] args) {
		Date inicio = new Date();
		for(int i=0; i< 1000; i++){
			Integer folio = ProxyAS400.getInstancia().obtenerFolio();
			System.out.println("indice: "+i);
			if(i % 100 == 0){
				System.out.println("tiempo: "+ (new Date().getTime() - inicio.getTime()) );
			}
		}
		Date termino = new Date();

		System.out.println("inicio: " + inicio );
		System.out.println("fin: " + termino );
		System.out.println("tiempo: " + (termino.getTime() - inicio.getTime()) );
		
		
		new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				
			}
		}).start();
	}
	
}
