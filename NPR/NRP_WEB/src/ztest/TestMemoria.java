package ztest;

public class TestMemoria {

	public static void main(String[] args) {
		
		int memoriaLibre = ((int)Runtime.getRuntime().freeMemory()/(1024*1024)) ;
		int memoriaTotal = ((int)Runtime.getRuntime().totalMemory()/(1024*1024)) ;
		System.out.println("memoria total|libre [ "+memoriaTotal+"mb |  "+memoriaLibre+"mb ]");
		
		
		
	}
	
}
