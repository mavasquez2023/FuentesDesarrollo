package cl.recursos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;
import cl.recursos.Archivo;
import org.apache.xerces.parsers.SAXParser;
import org.xml.sax.XMLReader;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.IFSFile;
import com.ibm.as400.access.IFSFileReader;
import com.ibm.as400.access.IFSJavaFile;

public class ConverterTXTtoCSV_XLS {
	private AS400 system=null;
	
	public ConverterTXTtoCSV_XLS(){
		
	}
	public ConverterTXTtoCSV_XLS(AS400 system){
		this.system= system;
	}
//	Este método permite covertir un archivo TXT a CSV y EXCEL dado un formato para la conversión dado por:
//	nombre de columnas, largo de los campos y alineamiento.
	public boolean convertir(String folderofile, String pathout, boolean zipear, String pathxml ){
		BufferedReader f1;
		File file;
		IFSFile ifsfile;
		File[] listFiles;
		String pathfile="", linea="", lineacsv="", lineaxls="", nombrecampo, aligncampo;
		int  largocampo, posinicampo;
		Vector csv, excel, txt;
		Vector xml, nombre, posini, largo, align;
		StringBuffer cabeceraexcel;
		GeneradorXLS_ContenedorXML cxml;
		boolean firstline= true;
		try{
			xml=procesarXML(pathxml);
			if (xml.size()>0){
					cxml= (GeneradorXLS_ContenedorXML) xml.elementAt(0);
					nombre= cxml.getNombres();
					posini= cxml.getPosIni();
					largo= cxml.getLargos();
					align= cxml.getAligns();
			}else{
				System.out.println("Error, xml no procesado correctamente");
				return false;
			}
			if (system==null){
				file = new File(folderofile);
			}else{
				file = new IFSJavaFile(system, folderofile);
			}
			
			//Se extrae lista de archivos dependiendo si 'folderOfile' es una carpeta 
			if (file.isDirectory()){
				listFiles= file.listFiles();
			}else{
				listFiles= new File[1];
	           	listFiles[0] = file.getAbsoluteFile();
			}
			//Se rescata el número de columnas del archivo
			int numcolumnas = nombre.size();
			
			//Se arma cabecera del excel
			cabeceraexcel= new StringBuffer();
			cabeceraexcel.append("<html xmlns:o=\"urn:schemas-microsoft-com:office:office\"");
			cabeceraexcel.append("xmlns:x=\"urn:schemas-microsoft-com:office:excel\">\n");
			cabeceraexcel.append("<head><!--[if gte mso 9]><xml><x:ExcelWorkbook>'n");
			cabeceraexcel.append("<x:ExcelWorksheets><x:ExcelWorksheet>\n");
			cabeceraexcel.append("<x:Name>N&oacute;mina</x:Name>\n");
			cabeceraexcel.append("<x:WorksheetOptions><x:DefaultColWidth>10</x:DefaultColWidth></x:WorksheetOptions>\n");
			cabeceraexcel.append("</x:ExcelWorksheet></x:ExcelWorksheets>\n");
			cabeceraexcel.append("</x:ExcelWorkbook></xml><![endif]--></head>\n");
			cabeceraexcel.append("<body><table border=1>\n");
			
			int cantidad_files=listFiles.length;
			for (int i = 0; i < cantidad_files; i++) {
				if (listFiles[i].isFile()){
					pathfile= replaceCaracter(listFiles[i].getAbsolutePath(), '\\', '/');
					csv= new Vector();
					excel= new Vector();
					txt= new Vector();
					lineaxls="";
					//Se lee archivo origen
					if (system==null){
						f1 = new java.io.BufferedReader(new FileReader(pathfile));
					}else{
						ifsfile = new IFSFile(system, pathfile);
						f1 = new BufferedReader(new IFSFileReader(ifsfile));
					}
					
					//Se asigna cabecera del excel
					excel.addElement(cabeceraexcel.toString());
					
					for (int j= 0; j < numcolumnas; j++ ) {
						//se rescata nombre de las columnas
						nombrecampo= nombre.elementAt(j).toString().trim();
						lineaxls = lineaxls + "<th bgcolor='#000080' align='center'><font color='#FFFFFF'>" + nombrecampo + "</font></th>\n";
					}
					excel.addElement("<tr>" + lineaxls + "</tr>");
					//***fin cabecera
					
					lineaxls="";
					while ((linea = f1.readLine()) != null) {
						//ini=0;
						if (linea.length()>5){
							for (int j= 0; j< numcolumnas; j++ ) {
								//se rescata posini, largo y alineamiento de las columnas
								posinicampo= Integer.parseInt(posini.elementAt(j).toString());
								largocampo= Integer.parseInt(largo.elementAt(j).toString());
								aligncampo= align.elementAt(j).toString().trim();
								
								//generando linea csv separada por ";"
								lineacsv = lineacsv + linea.substring(posinicampo, posinicampo + largocampo).trim() + ";";
								
								//generando linea excel como registros de una tabla en html
								lineaxls = lineaxls + "<td align='" + aligncampo + "'>" + linea.substring(posinicampo, posinicampo + largocampo).trim();
								//ini = ini + largocampo;
							}
							//Se agrega registro de txt original
							txt.add(linea);
							
							//Se agrega registro formateado de csv
							csv.addElement(lineacsv);
							
							//Se cierra linea excel y se acumula registro formateado
							excel.addElement("<tr>" + lineaxls + "</tr>");
							
							//Se limpian variables para formatear nueva línea
							lineacsv="";
							lineaxls="";
							firstline= false;
						}
					}
					//Se cierra archivo excel
					excel.addElement("	");
					//Se cierra archivo origen
					f1.close();
					
					//creando archivos de salida
					crearTXTyCSVyExcel(pathfile, pathout, zipear, "csv", csv);
					crearTXTyCSVyExcel(pathfile, pathout, zipear, "xls", excel);
					crearTXTyCSVyExcel(pathfile, pathout, zipear, "txt", txt);
				}
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	private boolean crearTXTyCSVyExcel(String pathfile, String pathout, boolean zipear, String extension, Vector texto){
		String fileout="", nameentry="";
		try{
		//grabando archivo csv
		nameentry= pathfile.substring(pathfile.lastIndexOf('/')+1) + "." + extension;
		if (pathout== null || pathout.equals("")){
			fileout= pathfile + "." + extension;
		}else{
			fileout= pathout + '/'  + nameentry;
		}
		
		if (zipear){
			Zipeador zip= new Zipeador(system);
			zip.zipearString(texto, nameentry, fileout);
		}else{
			Archivo file= new Archivo(system);
			file.crearArchivo(fileout, texto);
		}
		/*crearArchivo(fileout, texto);
		zipearArchivos(fileout, fileout + ".zip");
		borrarArchivo(fileout);
		*/
		return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	private Vector procesarXML (String filename) throws Exception{
		Vector instancias= new Vector();
		try
		{
			XMLReader parser = new SAXParser();

			//Añadimos al parser nuestro "ContentHandler", pasandole el vector "instancias" .
			parser.setContentHandler(new GeneratorXLS_XMLHandler(instancias));
			//parsear archivo xml de retorno especificando su ruta por "filename"
			parser.parse(filename);	
		}
		catch (Exception e)
		{
			System.out.println ("Error al procesar el xml: " + e.getMessage());
			e.printStackTrace();
			throw new Exception();
		}
		return instancias;
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
