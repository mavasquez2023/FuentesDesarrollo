

/*
 * @(#) ConverterXLS.java    1.0 28-04-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.laaraucana.satelites.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.Cookie;

import org.apache.xerces.parsers.SAXParser;
import org.xml.sax.XMLReader;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.IFSFile;
import com.ibm.as400.access.IFSFileReader;

/**
 * ...
 *
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 28-04-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> CLAUDIO LILLO AZORÍN <BR> clillo@laaraucana.cl </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD>   </TD>
 *            <TD align="center">  </TD>
 *            <TD>   </TD>
 *            <TD>  </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author CLAUDIO LILLO AZORÍN (clillo@laaraucana.cl)
 *
 * @version 1.0
 */
public class GeneratorXLS {
	private PrintStream salida;
	private AS400 system=null;
	
	public GeneratorXLS(PrintStream salida){
		this.salida= salida;
	}
	public GeneratorXLS(AS400 system, PrintStream salida){
		this.system= system;
		this.salida= salida;
	}
	
	
	public void generarXLSfromCollection(Collection objs, String[] columnas, String[] titulos, String colorRGB, Cookie filtro ){
	
		try{

			//Se arma cabecera del excel
			salida.println("<html xmlns:o=\"urn:schemas-microsoft-com:office:office\"");
			salida.println("xmlns:x=\"urn:schemas-microsoft-com:office:excel\">");
			salida.println("<head><!--[if gte mso 9]><xml><x:ExcelWorkbook>");
			salida.println("<x:ExcelWorksheets><x:ExcelWorksheet>");
			salida.println("<x:Name>N&oacute;mina</x:Name>");
			salida.println("<x:WorksheetOptions><x:DefaultColWidth>10</x:DefaultColWidth></x:WorksheetOptions>");
			salida.println("</x:ExcelWorksheet></x:ExcelWorksheets>");
			salida.println("</x:ExcelWorkbook></xml><![endif]--></head>");
			salida.println("<body><table border=1>");
						
			Method[] metodos=null;
			int numobj=1;
			//Recorriendo la lista de objetos de la colección
			for (Iterator iterator = objs.iterator(); iterator.hasNext();) {
				Object objeto = (Object) iterator.next();
				//por reflexión se rescatan los metodos de la Clase asociados a las columnas definidas, análisis solo en el primer objeto
				if(numobj==1){
					if(columnas!=null){
						metodos= new Method[columnas.length];
						for (int i = 0; i < columnas.length; i++) {
							String nommetodo= "get" + columnas[i].substring(0, 1).toUpperCase() + columnas[i].substring(1);
							metodos[i]= objeto.getClass().getMethod(nommetodo, null);
						}
					}else{
						metodos= objeto.getClass().getMethods();
					}
					//en omisión se setea en azul el color las cabeceras de las columnas
					if (colorRGB== null){
						colorRGB="000080";
					}
					
					//Se rescata los títulos de las cabeceras de las columnas
					salida.println("<tr>");
					if(titulos!=null){
						for (int j= 0; j < titulos.length; j++ ) {
							//se rescata nombre de las columnas
							salida.print("<th bgcolor='#" + colorRGB + "' align='center'><font color='#FFFFFF'>" + titulos[j] + "</font></th>");
						}
					}else{
						//se asocia el nombre de la cabecera como el nombre de la propiedad
						for (int i = 0; i < metodos.length; i++) {
							String propiedad= metodos[i].getName();
							String tipo= propiedad.substring(0, 3);
							//se rescatan solo los métodos geters
							if(tipo.equals("get")){
								//se rescata nombre de las columnas
								salida.print("<th bgcolor='#000080' align='center'><font color='#FFFFFF'>" + propiedad.substring(3) + "</font></th>");
							}
						}
					}
					salida.println("</tr>");
				}
				
				//Iterando sobre las columnas definidas, se arma la fila completa.
				
				StringBuffer salidatemp= new StringBuffer();
				boolean condicion=false;
				salidatemp.append("<tr>");
				for (int i = 0; i < metodos.length; i++) {
					String tipo= metodos[i].getName().substring(0, 3);
					String nombre= metodos[i].getName().substring(3);
					if (tipo.equals("get")) {
						Object result= metodos[i].invoke(objeto, null);
						salidatemp.append("<td align='" + returnAlign(result) + "'>" + result + "</td>");
						if(filtro!=null){
							if(nombre.equalsIgnoreCase(filtro.getName())){
								if(result.toString().equalsIgnoreCase(filtro.getValue())){
									condicion= true;
								}
							}
						}else{
							condicion= true;
						}
						//salida.print("<td align='" + returnAlign(result) + "'>" + result + "</td>");
					}
				}
				salidatemp.append("</tr>");
				if(condicion){
					salida.print(salidatemp.toString());
				}
				
				numobj++;
			}

			//Se cierra archivo excel
			salida.println("	");
			 
		}catch(Exception e){
			System.out.println("GeneratorXLS.generarXLS, error en la generación del excel.");
			e.printStackTrace();
		}
	}
	
	public void generarCSVfromCollection(Collection objs, String[] columnas, String[] titulos){
		
		try{
						
			Method[] metodos=null;
			int numobj=0;
			//Recorriendo la lista de objetos de la colección
			for (Iterator iterator = objs.iterator(); iterator.hasNext();) {
				Object objeto = (Object) iterator.next();
				//por reflexión se rescatan los metodos de la Clase asociados a las columnas definidas, análisis solo en el primer objeto
				if(numobj==0){
					if(columnas!=null){
						metodos= new Method[columnas.length];
						for (int i = 0; i < columnas.length; i++) {
							String nommetodo= "get" + columnas[i].substring(0, 1).toUpperCase() + columnas[i].substring(1);
							metodos[i]= objeto.getClass().getMethod(nommetodo, null);
						}
					}else{
						metodos= objeto.getClass().getMethods();
					}
				}
				
//				Se rescata los títulos de las cabeceras de las columnas
				//salida.println("<tr>");
				if(titulos!=null && numobj==0){
					for (int j= 0; j < titulos.length; j++ ) {
						//se rescata nombre de las columnas
						salida.print(titulos[j] + ";");
					}
					salida.println("");
				}
				//Iterando sobre las columnas definidas, se arma la fila completa.
				for (int i = 0; i < metodos.length; i++) {
					String tipo= metodos[i].getName().substring(0, 3);
					if (tipo.equals("get")) {
						Object result= metodos[i].invoke(objeto, null);
						salida.print( result + ";");
					}
				}
				salida.println("");
				numobj++;
			}
			 
		}catch(Exception e){
			System.out.println("GeneratorXLS.generarXLS, error en la generación del excel.");
			e.printStackTrace();
		}
	}
	public void generarXLSfromCollection(Collection objs, String[] columnas, String[] titulos, String colorRGB){
		generarXLSfromCollection(objs, columnas, titulos, colorRGB, null);
	}
	
	public void generarXLSfromCollection(Collection objs, String[] columnas, String colorRGB){
		generarXLSfromCollection(objs, columnas, null, colorRGB, null);
	}
	
	public void generarXLSfromCollection(Collection objs, String[] columnas){
		generarXLSfromCollection(objs, columnas, null, null, null);
	}
	
	public void generarXLSfromCollection(Collection objs, String colorRGB){
		generarXLSfromCollection(objs, null, null, colorRGB, null);
	}
	
	public void generarXLSfromCollection(Collection objs){
		generarXLSfromCollection(objs, null, null, null, null);
	}
	
	public void generarCSVfromCollection(Collection objs, String[] columnas){
		generarCSVfromCollection(objs, columnas, null);
	}
	
	public void generarCSVfromCollection(Collection objs){
		generarCSVfromCollection(objs, null, null);
	}
	
	public String returnAlign(Object obj){
		String align;
		if (obj instanceof Number) {
			align="right";
		}else{
			align="left";
		}
		return align;
	}
	
	
	public static String replaceCaracter(String texto, char oldchar, char newchar){
		int pos= texto.indexOf('\\');
		while (pos >= 0) {
			texto=texto.substring(0, pos) + "/" + texto.substring(pos +1);
			pos= texto.indexOf('\\');
		}
		return texto;
	}
}

