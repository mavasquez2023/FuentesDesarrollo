package ztest;

public class TestKillHebras {

	
	public static void main(String[] args) throws InterruptedException {
		
		
		Thread nuevoThread = new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					System.out.println("esperando...");
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		nuevoThread.start();
		
		
		Thread.sleep(7000);
		System.out.println("esperando para terminar");
		
		nuevoThread.stop();

		System.out.println("esperando para salir");
		Thread.sleep(7000);
		System.out.println("saliendo");
		
	}
	
}
