package cl.lib.export.xls.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class XLSHelper {
	
		
	
	private static String getValue(HashMap<String, Object> data, String key){
		Object texto = data.get(key);
		if(texto == null)
			texto ="";
		
		return texto.toString();
	}

	private static HashMap<String, Object> createEstadoLocal(String login,String local, String estado, String respuesta, String linkEvidencia){
		HashMap<String, Object> h = new HashMap<String, Object>();
		h.put("login", login);
		h.put("local", local);
		h.put("estado", estado);
		h.put("respuesta", respuesta);
		h.put("link_evidencia", linkEvidencia);
		return h;
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
	
	private static void setHyperLinkValue(HSSFWorkbook libro, HSSFSheet hoja, int row, int col, String data, String url){
		
		HSSFRow fila = hoja.getRow(row);
		if(fila == null)
			fila = hoja.createRow(row);
		
		
		HSSFCell celda = fila.getCell(col);
		if(celda == null)
			celda = fila.createCell((short) col);
		
		
		
		HSSFHyperlink link =new HSSFHyperlink(HSSFHyperlink.LINK_URL);
		link.setAddress(url);
		celda.setHyperlink(link);
		celda.setCellValue(data);
		
	}
	
	}
