package cl.lib.export.xls.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import cl.liv.archivos.comun.txt.ManejoArchivoTXT;

public class ConvertidorCSVaEXCEL {

	static short heightDefault = 300;
	
	public static String convertir(String rutaCSV, String rutaXLS, String separador){
		
		ArrayList<String> lineas = new ArrayList<String>();
		BufferedReader br = ManejoArchivoTXT.getOpenedFileToRead(rutaCSV,true, null);
		int col = 0;
		boolean quedanRegistros = true;
		while(quedanRegistros){
			String registro = ManejoArchivoTXT.getLineFromFileOpened(br);
			if(col % 200 == 0){
			}
			if(registro != null){
				lineas.add(registro);
			}
			else{
				quedanRegistros = false;
			}
			col++;
		}
		ManejoArchivoTXT.closeFileToRead(br);

		
		HSSFWorkbook libro = new HSSFWorkbook();
		HSSFSheet hoja = libro.createSheet();
		for(int j=0; j< lineas.size(); j++){
			String registro = lineas.get(j);
			if(j % 200 == 0){
			}
			if(registro != null){
				String[] columnas = registro.split(separador);
				Row row = hoja.createRow(j);
				for(int i=0; i< columnas.length; i++){					
					 Cell cell = row.createCell(i);
					String valor= columnas[i].trim();
					try {
						Double valorint= new Double(valor);
						cell.setCellValue(valorint);
					} catch (Exception e) {
						 cell.setCellValue(valor);
					}
					
				}
			}
		}
		
		FileOutputStream elFichero;
		try {
			elFichero = new FileOutputStream(rutaXLS);
			libro.write(elFichero);
			elFichero.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rutaXLS;
	}
	
}