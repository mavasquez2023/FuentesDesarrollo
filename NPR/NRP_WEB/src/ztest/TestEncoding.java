package ztest;

import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;

import cl.liv.archivos.comun.txt.ManejoArchivoTXT;

public class TestEncoding {
	public static void main(String[] args) {
		
		
		//BufferedReader br = ManejoArchivoTXT.getOpenedFileToRead("/home/clillo/nrp/input/PREV_201809.txt_0.584974552794623", true);
		//BufferedReader br = ManejoArchivoTXT.getOpenedFileToRead("/home/clillo/nrp/input/test", true);
		BufferedReader br = ManejoArchivoTXT.getOpenedFileToRead("/home/clillo/nrp/input/PREV_201809.txt_0.1843449250832948", 0,"ISO-8859-1");
		
		String registro = ManejoArchivoTXT.getLineFromFileOpened(br);
		String cadena = "";
		while(registro != null){
			
			if(registro.contains("02391377")){
				
				System.out.println(registro);
				if(true) return;
				for(int i=0; i< registro.length();i++){
					
					int code = (int) 209;
					char caracter = registro.charAt(i);
					if(caracter == 65533){

						cadena = cadena + "Ñ";
					}
					else{
						cadena = cadena + registro.charAt(i);
					}
					System.out.println("["+i+"]["+registro.charAt(i)+"][caracter->"+ (int)caracter +"]");
				}
				System.out.println("cadena: "+ cadena);
				/*try {
					texto = new String(texto.getBytes("ISO-8859-1"),"UTF-8");

					System.out.println(texto);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
			
			System.out.println(registro);
			registro = ManejoArchivoTXT.getLineFromFileOpened(br);
		}
		
		ManejoArchivoTXT.closeFileToRead(br);
	}
}
