package ztest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class TestHebras {
	static int hebraAEliminar = 4;
	public static void main(String[] args) throws InterruptedException {
		
		for(int i=0; i< 10; i++){
			final int id = i;
			Thread t = new Thread(new Runnable() {
				
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("ejecutando: ["+id+"]");
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("terminando: ["+id+"]");
				}
			});
			t.setName("t_"+id);
			t.start();
		}
		
		
		Thread.sleep(2000);
		System.out.println(Thread.activeCount());
		Set threadSet = Thread.getAllStackTraces().keySet();
		Thread[] threadArray = (Thread[])threadSet.toArray(new Thread[threadSet.size()]);
		
		for(int i=0; i< threadArray.length; i++){
			System.out.println("thread [contador: "+i+"]-> "+threadArray[i].getName());
			if(threadArray[i].getName().equals("t_"+hebraAEliminar)){
				threadArray[i].interrupt();
				threadArray[i].stop();
				
			}
		}
	}
	
}
