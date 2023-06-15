package cl.lib.export.xls.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReporteXLS {
	public static void main(String[] args) {
		generar();
	}
	
	public static void generar(){
		
		HSSFWorkbook libro = new HSSFWorkbook();
		HSSFSheet hoja = libro.createSheet();

		//hoja.setColumnWidth(jPosition, (espacioPorCaracter + 30) *  (columna.get("header")+"").length()  );
	
		
	
	
		//setCeldaValue(libro, hoja, contadorRows+1, jPosition,registro.get(columna.get("nombre"))+"" , false, heightDefault);
	
	}
	public static void setCeldaValue(HSSFWorkbook libro, HSSFSheet hoja, int row, int col, String data, boolean bold, short height){
		HSSFRow fila = hoja.getRow(row);
		if(fila == null)
			fila = hoja.createRow(row);
		
		fila.setHeight((short) height);
		
		HSSFCell celda = fila.getCell(col);
		if(celda == null)
			celda = fila.createCell((short) col);
		
		
		
		HSSFRichTextString texto = new HSSFRichTextString(data);
		
		if(bold){
			HSSFFont font=libro.createFont();  
			  
			font.setBoldweight((short)1000);
			
			texto.applyFont(font);
		}
		celda.setCellValue(texto);
		
	}
}
