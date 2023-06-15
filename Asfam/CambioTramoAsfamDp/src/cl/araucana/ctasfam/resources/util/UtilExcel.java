package cl.araucana.ctasfam.resources.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cl.araucana.ctasfam.business.to.AfiliadosPropuestaTO;
import cl.araucana.ctasfam.business.to.EstadisticaProcesoTO;
import cl.araucana.ctasfam.integration.jdbc.dao.AraucanaJdbcDao;
 


public class UtilExcel  {
	
	AraucanaJdbcDao dao=new AraucanaJdbcDao();
	
	public HSSFWorkbook estadisticaProcesoXls() throws IOException
	{
        String[] titles = {"OFICINA","NOMBRE OFICINA","RUT EMPRESA","NOMBRE EMPRESA","% INFORMADO","TOTAL INFORMADO","% PENDIENTE","TOTAL PENDIENTE","TOTAL"};
        
        HSSFWorkbook objWB = new HSSFWorkbook();
        
        HSSFSheet hoja1 = objWB.createSheet("DATA");

        HSSFRow fila = hoja1.createRow(0);
        
        HSSFCellStyle style = objWB.createCellStyle();
        HSSFFont font = objWB.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short)10);
        font.setBoldweight((short)700);
        font.setColor((short)8);
        style.setFont(font);
        
        for(int i=0;i<=titles.length-1;i++)
        {
        	HSSFCell title = fila.createCell(i);
        	title.setCellValue(new HSSFRichTextString(titles[i]));
        	title.setCellStyle(style);
        	
        	hoja1.autoSizeColumn((short) (i));
        }
        
        List data = dao.getEstadisticaProceso();
        
        Iterator loop = data.iterator();
        
        for(int i=1;loop.hasNext();i++){
        	fila = hoja1.createRow(i);
        	
        	EstadisticaProcesoTO estadisticaProceso = (EstadisticaProcesoTO) loop.next();

           	HSSFCell value = fila.createCell(0);
           	value.setCellValue(new HSSFRichTextString(estadisticaProceso.getOficina()));
           	
           	value = fila.createCell(1);
           	value.setCellValue(new HSSFRichTextString(estadisticaProceso.getNombreOficina()));

           	value = fila.createCell(2);
           	value.setCellValue(new HSSFRichTextString(estadisticaProceso.getRutEmpresa()));
           	
           	value = fila.createCell(3);
           	value.setCellValue(new HSSFRichTextString(estadisticaProceso.getNombreEmpresa()));
           	
           	value = fila.createCell(4);
           	value.setCellValue(new HSSFRichTextString(estadisticaProceso.getPorcentajeInformado()));
           	
           	value = fila.createCell(5);
           	value.setCellValue(new HSSFRichTextString(estadisticaProceso.getTotalInformado()));
           	
           	value = fila.createCell(6);
           	value.setCellValue(new HSSFRichTextString(estadisticaProceso.getPorcentajePendiente()));
           	
           	value = fila.createCell(7);
           	value.setCellValue(new HSSFRichTextString(estadisticaProceso.getTotalPendiente()));
           	
           	value = fila.createCell(8);
           	value.setCellValue(new HSSFRichTextString(estadisticaProceso.getTotal()));
        }
        
        return objWB;
	}
	
	 public HSSFWorkbook generaPlantillaExcel(int oficina, int sucursal, int empresa)
	 throws IOException
	    {
		 
		 
	        List excel = dao.getAfiliadosNoInformados(oficina, sucursal, empresa);
	        
	        AfiliadosPropuestaTO afiliado=null;
	        HSSFRichTextString textoPeriodo = new HSSFRichTextString("PERIODO");
	        HSSFRichTextString textoOficina = new HSSFRichTextString("OFICINA");
	        HSSFRichTextString textoSucursal = new HSSFRichTextString("SUCURSAL");
	        HSSFRichTextString textoEmpresa = new HSSFRichTextString("RUTEMPRESA");
	        HSSFRichTextString textoDvempresa = new HSSFRichTextString("DVEMPRESA");
	        HSSFRichTextString textoRutafiliado = new HSSFRichTextString("RUTAFILIADO");
	        HSSFRichTextString textoDvafiliado = new HSSFRichTextString("DVAFILIADO");
	        HSSFRichTextString textoApaterno = new HSSFRichTextString("APELLIDO PATERNO");
	        HSSFRichTextString textoAmaterno = new HSSFRichTextString("APELLIDO MATERNO");
	        HSSFRichTextString textoNombres = new HSSFRichTextString("NOMBRES");
	        HSSFRichTextString valorRemuemp = new HSSFRichTextString("REMUNERACIÓN EMP");
	        HSSFRichTextString valorRemuotroemp = new HSSFRichTextString("REMUNERACIÓN OTRO EMP");
	        HSSFRichTextString valorRentaindependiente = new HSSFRichTextString("RENTA INDEPENDIENTE");
	        HSSFRichTextString valorRentasubsidio = new HSSFRichTextString("RENTA SUBSIDIO");
	        HSSFRichTextString valorRentapensiones = new HSSFRichTextString("RENTA PENSIONES");
	        HSSFRichTextString valortotalingresos = new HSSFRichTextString("TOTAL INGRESOS");
	        HSSFRichTextString valornumeromeses = new HSSFRichTextString("NUMERO MESES");
	        HSSFRichTextString valorIngresoprommensual = new HSSFRichTextString("INGRESO PROMEDIO MENSUAL");
	        HSSFRichTextString valorConsindeclaracion = new HSSFRichTextString("CON/SIN DECLARACIÓN");
	        HSSFRichTextString valorcodtramo = new HSSFRichTextString("COD. TRAMO");
	        HSSFRichTextString valorValortramo = new HSSFRichTextString("VALOR TRAMO");
	        
	        
	        HSSFWorkbook objWB = new HSSFWorkbook();
	        HSSFCellStyle styleLibre = objWB.createCellStyle();
	        styleLibre.setLocked(false);
	        HSSFCellStyle style = objWB.createCellStyle();
	        style.setFillBackgroundColor((short)22);
	        HSSFFont font = objWB.createFont();
	        font.setFontName("Arial");
	        font.setFontHeightInPoints((short)10);
	        font.setBoldweight((short)700);
	        font.setColor((short)8);
	        style.setFont(font);
	        HSSFSheet hoja1 = objWB.createSheet(String.valueOf(empresa));
	       
	        HSSFRow fila = hoja1.createRow(0);
	        HSSFCell celdaRut1 = fila.createCell(0);
	        celdaRut1.setCellValue(textoPeriodo);
	        celdaRut1.setCellStyle(style);
	        HSSFCell celdaRut2 = fila.createCell(1);
	        celdaRut2.setCellValue(textoOficina);
	        HSSFCell celdaRut3 = fila.createCell(2);
	        celdaRut3.setCellValue(textoSucursal);
	        celdaRut3.setCellStyle(style);
	        HSSFCell celdaRut4 = fila.createCell(3);
	        celdaRut4.setCellValue(textoEmpresa);
	        HSSFCell celdaRut5 = fila.createCell(4);
	        celdaRut5.setCellValue(textoDvempresa);
	        celdaRut5.setCellStyle(style);
	        HSSFCell celdaRut6 = fila.createCell(5);
	        celdaRut6.setCellValue(textoRutafiliado);
	        celdaRut6.setCellStyle(style);
	        HSSFCell celdaRut7 = fila.createCell(6);
	        celdaRut7.setCellValue(textoDvafiliado);
	        celdaRut7.setCellStyle(style);
	        HSSFCell celdaRut8 = fila.createCell(7);
	        celdaRut8.setCellValue(textoApaterno);
	        celdaRut8.setCellStyle(style);
	        HSSFCell celdaRut9 = fila.createCell(8);
	        celdaRut9.setCellValue( textoAmaterno);
	        celdaRut9.setCellStyle(style);
	        HSSFCell celdaRut10 = fila.createCell(9);
	        celdaRut10.setCellValue(textoNombres);
	        celdaRut10.setCellStyle(style);
	        HSSFCell celdaRut11 = fila.createCell(10);
	        celdaRut11.setCellValue(valorRemuemp);
	        celdaRut11.setCellStyle(style);
	        HSSFCell celdaRut12 = fila.createCell(11);
	        celdaRut12.setCellValue(valorRemuotroemp );
	        celdaRut12.setCellStyle(style);
	        HSSFCell celdaRut13 = fila.createCell(12);
	        celdaRut13.setCellValue(valorRentaindependiente );
	        celdaRut13.setCellStyle(style);
	        HSSFCell celdaRut14 = fila.createCell(13);
	        celdaRut14.setCellValue(valorRentasubsidio);
	        celdaRut14.setCellStyle(style);
	        HSSFCell celdaRut15 = fila.createCell(14);
	        celdaRut15.setCellValue(valorRentapensiones );
	        celdaRut15.setCellStyle(style);
	        HSSFCell celdaRut18 = fila.createCell(15);
	        celdaRut18.setCellValue( valortotalingresos);
	        celdaRut18.setCellStyle(style);
	        HSSFCell celdaRut19 = fila.createCell(16);
	        celdaRut19.setCellValue( valornumeromeses);
	        celdaRut19.setCellStyle(style);
	        HSSFCell celdaRut20 = fila.createCell(17);
	        celdaRut20.setCellValue(valorIngresoprommensual );
	        celdaRut20.setCellStyle(style);
	        HSSFCell celdaRut21 = fila.createCell(18);
	        celdaRut21.setCellValue(valorConsindeclaracion );
	        celdaRut21.setCellStyle(style);
	        HSSFCell celdaRut22 = fila.createCell(19);
	        celdaRut22.setCellValue(valorcodtramo);
	        celdaRut22.setCellStyle(style);
	        HSSFCell celdaRut23 = fila.createCell(20);
	        celdaRut23.setCellValue(valorValortramo);
	        celdaRut23.setCellStyle(style);
	         
	        
	   
	        
	        Iterator itr = excel.iterator();
	        
	        int i=1;
	        for(; itr.hasNext();)
	        {
	          
	            afiliado = new AfiliadosPropuestaTO();
	            afiliado = (AfiliadosPropuestaTO)itr.next();
	            HSSFRow filaCuerpo = hoja1.createRow(i);
	            HSSFCell celda0 = filaCuerpo.createCell(0);
	            celda0.setCellValue(afiliado.getPeriodo());
	            HSSFCell celda1 = filaCuerpo.createCell(1);
	            celda1.setCellValue(afiliado.getOficina());
	            HSSFCell celda2 = filaCuerpo.createCell(2);
	            celda2.setCellValue(afiliado.getSucursal());
	            celda2.setCellType(0);
	            HSSFCell celda3 = filaCuerpo.createCell(3);
	            celda3.setCellValue(afiliado.getRutEmpresa());
	            celda3.setCellType(0);
	            HSSFCell celda4 = filaCuerpo.createCell(4);
	            celda4.setCellValue(new HSSFRichTextString(afiliado.getDvRutEmpresa()));
	            HSSFCell celda5 = filaCuerpo.createCell(5);
	            celda5.setCellValue(afiliado.getRutAfiliado());
	            HSSFCell celda6 = filaCuerpo.createCell(6);
	            celda6.setCellValue(new HSSFRichTextString(afiliado.getDvRutAfiliado()));
	            HSSFCell celda7 = filaCuerpo.createCell(7);
	            celda7.setCellValue(new HSSFRichTextString( afiliado.getApaterno()));
	            HSSFCell celda8 = filaCuerpo.createCell(8);
	            celda8.setCellValue(new HSSFRichTextString( afiliado.getAmaterno()));
	            HSSFCell celda9 = filaCuerpo.createCell(9);
	            celda9.setCellValue(new HSSFRichTextString( afiliado.getNombreAfiliado()));
	            HSSFCell celda10 = filaCuerpo.createCell(10);
	            celda10.setCellValue(afiliado.getRemuneracionEmpleador());
	            HSSFCell celda11 = filaCuerpo.createCell(11);
	            celda11.setCellValue(afiliado.getRemuneracionOtroEmpleador());
	            HSSFCell celda12 = filaCuerpo.createCell(12);
	            celda12.setCellValue(afiliado.getRemuneracionIndependiente());
	            HSSFCell celda13 = filaCuerpo.createCell(13);
	            celda13.setCellValue(afiliado.getRentaSubsidio());
	            HSSFCell celda14 = filaCuerpo.createCell(14);
	            celda14.setCellValue(afiliado.getRentaPensiones());
	            HSSFCell celda15 = filaCuerpo.createCell(15);
	            celda15.setCellValue(afiliado.getTotalIngresos());
	            HSSFCell celda16 = filaCuerpo.createCell(16);
	            celda16.setCellValue(afiliado.getNumeroMeses());
	            HSSFCell celda17 = filaCuerpo.createCell(17);
	            celda17.setCellValue(afiliado.getIngresoPromedio());
	            HSSFCell celda18 = filaCuerpo.createCell(18);
	            celda18.setCellValue(afiliado.getDeclaracion());
	            HSSFCell celda19 = filaCuerpo.createCell(19);
	            celda19.setCellValue(afiliado.getTramo());
	            HSSFCell celda20 = filaCuerpo.createCell(20);
	            celda20.setCellValue(afiliado.getValorTramo());
	            i++;
	            
	        }

	        hoja1.autoSizeColumn((short)0);
	        hoja1.autoSizeColumn((short)1);
	        hoja1.autoSizeColumn((short)2);
	        hoja1.autoSizeColumn((short)3);
	        hoja1.autoSizeColumn((short)4);
	        hoja1.autoSizeColumn((short)5);
	        hoja1.autoSizeColumn((short)6);
	        hoja1.autoSizeColumn((short)7);
	        hoja1.autoSizeColumn((short)8);
	        hoja1.autoSizeColumn((short)9);
	        hoja1.autoSizeColumn((short)10);
	        hoja1.autoSizeColumn((short)11);
	        hoja1.autoSizeColumn((short)12);
	        hoja1.autoSizeColumn((short)13);
	        hoja1.autoSizeColumn((short)14);
	        hoja1.autoSizeColumn((short)15);
	        hoja1.autoSizeColumn((short)16);
	        hoja1.autoSizeColumn((short)17);
	        hoja1.autoSizeColumn((short)18);
	        hoja1.autoSizeColumn((short)19);
	        hoja1.autoSizeColumn((short)20);
	        
	       
	        
	        
	        return objWB;
	    }
	    
 


}
