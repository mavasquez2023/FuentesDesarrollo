package test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import cl.laaraucana.silmsil.dao.PLANOSDAO;
import cl.laaraucana.silmsil.vo.ILFSIL052VO;

public class TestEscribir {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String fecha = "201303";
		
		//Obtiene datos a escribir
		ArrayList<ILFSIL052VO> silList = PLANOSDAO.obtenerDatosPlanoSIL();
		
		//Escritura.
		FileWriter fileWriter = new FileWriter("C:\\prueba\\testeoSil2.csv");
		BufferedWriter wr = new BufferedWriter(fileWriter);
		
		String periodo;
		boolean swEsPrimeraVez=true;
		for(ILFSIL052VO  vo: silList){
			if(swEsPrimeraVez==true){
				String partes[] = vo.getRsil().split(";");
				periodo = (partes[0].equalsIgnoreCase("10105")?partes[1]+partes[2]:null);
				
				if(periodo!=null){
					if(fecha.equalsIgnoreCase(periodo)){
						//Escribe unicamente la primera l�nea si coinciden los per�odos.
						wr.write(vo.getRsil() + "\r"+"\n");
					}else{
						System.out.println("Per�odos comparados son distintos.");
					}
				}
			}
			//Escribe el resto del archivo.
			wr.write(vo.getRsil() + "\r"+"\n");
			
			swEsPrimeraVez=false;
		}
		wr.flush();
		wr.close();
		
		System.out.println("���������T�RMINO!!!!!!!1");
	}
	
}
