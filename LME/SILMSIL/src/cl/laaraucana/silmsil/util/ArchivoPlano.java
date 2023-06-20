package cl.laaraucana.silmsil.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cl.laaraucana.silmsil.vo.ILFLM052VO;
import cl.laaraucana.silmsil.vo.ILFSIL052VO;

/**
 * Escritura y lectura de archivo planos a través de JasperReport.
 * 
 * @author usist42
 * 
 */
public class ArchivoPlano {
	
	/**
	 * Método para determinar existencia archivo buscado.
	 * @param ruta
	 * @param fileName
	 * @return
	 */
	public static boolean valArchivo(String ruta, String fileName)throws Exception
	{
		System.out.println("Ruta = " + ruta + " Nombre = " + fileName);
		boolean val =  false;
		
		File file = new File(ruta);
		String[] archivos = file.list();
		
		if(!file.exists())
			return false;
		else
		{
			for(int i = 0; i < file.length()-1; i++)
			{
				System.out.println(archivos[i]);
				if(archivos[i].equalsIgnoreCase(fileName))
				{
					System.out.println(i+": "+archivos[i]);
					return true;
				}
			}
		}	
		System.out.println("Val = " + val);
		return val;
	}
	
	/**
	 * Método para determinar existencia directorio
	 * @param ruta
	 * @return
	 * @throws Exception
	 */
	public static boolean valDirectorio(String ruta)throws Exception
	{
		System.out.println("Comprobando directorio...");
		boolean val =  false;
		
		File[] roots = File.listRoots();
		
		if(roots.length == 0)
			return false;
		else
		{
			for(int i = 0; i < roots.length; i++)
			{
				System.out.println("File = " + roots[i]);
			}
		}
		return val;
	}
	
	/**
	 * Método que sirve para grabar archivo plano en pc.
	 * @param fileName
	 * @param content
	 * @param ruta
	 */
	public static void setLocalFile(String fileName, byte[] content, String ruta) {
		try {
			System.out.println("Creación archivo en directorio local.");
			String archivo = ruta + fileName;
			System.out.println("Ruta = " + archivo);

			// Creación archivo ha procesar en disco local.
			FileOutputStream out = new FileOutputStream(new File(archivo));
			out.write(content);

			out.flush();
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Método escritura archivo.
	 * @param fileIn -> ruta entrada + nombre archivo.
	 * @param fileOut-> ruta salida + nombre archivo.
	 * @return
	 */
	public static boolean writeFile(String fileIn, String fileOut) {
		try {
			// Manejo de archivos en carpeta local.
			File archivoEntrada = new File(fileIn);
			File archivoSalida = new File(fileOut); // Archivo enviado a cargar en AS400.
			FileReader file = new FileReader(archivoEntrada);
			BufferedReader br = new BufferedReader(file);
			FileWriter fileWriter = new FileWriter(archivoSalida);
			BufferedWriter wr = new BufferedWriter(fileWriter);

			String texto = ""; 

			while ((texto = br.readLine()) != null) {

				wr.write(texto +"\r"+"\n");
			}
			wr.flush();
			wr.close();
			br.close();

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Método escritura archivo SIL.
	 * @param silList
	 * @param fileOut
	 * @return
	 */
	public static boolean writeFileSIL(ArrayList<ILFSIL052VO> silList, String ruta, String fileOut, String fecha)  throws IOException, Exception{
		try {
			//crear Directorio cambio 26-03-2015
			File file = new File(ruta);
			file.mkdirs();
			
			//Escritura.
			FileWriter fileWriter = new FileWriter(fileOut);
			BufferedWriter wr = new BufferedWriter(fileWriter);
			
			String periodo;
			boolean swEsPrimeraVez=true;
			
			int largo = silList.size();
			int count = 0;
			
			//Recorre la lista y escribe el archivo.
			for(ILFSIL052VO  vo: silList){
				count++;
				if(swEsPrimeraVez==true){
					String partes[] = vo.getRsil().split(";");
					periodo = (partes[0].equalsIgnoreCase("10105")?partes[1]+partes[2]:null);
					
					//Validando que período de primera línea corresponda al período ingresado.
					if(periodo!=null){
						if(!fecha.equalsIgnoreCase(periodo)){
							System.out.println("Períodos comparados son distintos.");
							return false;
						}
					}
				}
				if(largo != count){
					//Escribiendo el resto de las líneas. 
					wr.write(vo.getRsil() + "\r"+"\n");
				}else{
					System.out.println("Última línea count = ["+count+"] - largo = ["+largo+"].");
					//Escribiendo la última línea. 
					wr.write(vo.getRsil());
				}
			}
			swEsPrimeraVez=false;
			
			//Cierra archivo plano creado.
			wr.flush();
			wr.close();
			fileWriter.close();
			return true;
			
		} catch (Exception ex) {
			System.out.println("Error writeFileSIL() : " + ex.getMessage());
			ex.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Método escritura archivo LM.
	 * @param silList
	 * @param fileOut
	 * @return
	 */
	public static boolean writeFileLM(ArrayList<ILFLM052VO> lmList, String ruta, String fileOut, String fecha)  throws IOException, Exception{
		System.out.println("* * [writeFileLM] * *");
		try {
			//crear Directorio cambio 26-03-2015
			File file = new File(ruta);
			file.mkdirs();
			
			//Escritura.
			FileWriter fileWriter = new FileWriter(fileOut);
			BufferedWriter wr = new BufferedWriter(fileWriter);
			
			String periodo;
			boolean swEsPrimeraVez=true;
			
			int largo = lmList.size();
			int count = 0;
			
			//Recorre la lista y escribe el archivo.
			for(ILFLM052VO  vo: lmList){
				if(swEsPrimeraVez==true){
					String partes[] = vo.getRlm().split(";");
					periodo = (partes[0].equalsIgnoreCase("10105")?partes[1]+partes[2]:null);
					
					//Validando que período de primera línea corresponda al período ingresado.
					if(periodo!=null){
						if(!fecha.equalsIgnoreCase(periodo)){
							System.out.println("Períodos comparados son distintos.");
							return false;
						}
					}
					if(largo != count){
						//Escribiendo el resto de las líneas. 
						wr.write(vo.getRlm() + "\r"+"\n");
					}else{
						System.out.println("Última línea count = ["+count+"] - largo = ["+largo+"].");
						//Escribiendo la última línea. 
						wr.write(vo.getRlm());
					}
				}
			}
			swEsPrimeraVez=false;
			
			//Cierra archivo plano creado.
			wr.flush();
			wr.close();
			fileWriter.close();
			return true;
		} catch (Exception ex) {
			System.out.println("Error writeFileLM() : " + ex.getMessage());
			ex.printStackTrace();
		}
		return false;
	}

	public static String readFile(String fileIn) {
		try {
			//Lectura de archivo.
			File archivoEntrada = new File(fileIn);
			FileReader file = new FileReader(archivoEntrada);
			BufferedReader br = new BufferedReader(file);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	
	/**
	 * Método que realiza una copia de archivo desde la carpeta local
	 * hacia una ruta remota en sistema de archivos integrado mediante jt400,
	 * recibe por parámetros el período y el nombre archivo a copiar.
	 * -> rutaArchivo: Es la ruta local de archivo a copiar.
	 * -> carpetaDestino: Es la ruta de escritura remota.
	 * **/
	public static boolean setCopyByIFS(String rutaArchivo,String carpetaDestino) throws IOException, Exception{
		boolean result = false;
		
		try{
			String server = Configuraciones.getMainConfig("server");
			String user = Configuraciones.getMainConfig("user");
			String pass = Configuraciones.getMainConfig("pass");
			
			//Se conecta a carpeta AS400.
			System.out.println("Conexión a carpeta compartida.");
			UtilAS400 usf4 = new UtilAS400(server,user,pass);
			
			//Copia archivo en carpeta AS400 y cierra conexión.
			System.out.println("Escritura archivo en carpeta compartida.");
			result = usf4.copiarArchivoToAS400(rutaArchivo, carpetaDestino);
			System.out.println("Éxito al escribir el archivo plano : " + result);
			usf4.closeConnection();
			
			return result;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}//END setCopyByIFS
	
	public static boolean writerByIFS(String rutaArchivo,String carpetaDestino){
		boolean keyEscritura=false;
		try{
			System.out.println("* * * * * IFS: LOGIN  * * * * *");
			String server = Configuraciones.getMainConfig("server");
			String user = Configuraciones.getMainConfig("user");
			String pass = Configuraciones.getMainConfig("pass");
	
			UtilAS400 usf4 = new UtilAS400(server,user,pass);
			
			System.out.println("* * * * * IFS: directorio * * * * *");	
			
			//usf4.crearDirectorio(carpetaDestino);		
			//rutaIfsdestino+=periodo+"/"+nombreArchivo;
			//rutaLocalTemporales+=nombreArchivo;
			
			final byte[] buf = new byte[16 * 1024 * 1024];
			FileInputStream fis = new FileInputStream(rutaArchivo);
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();        
	        for (int readNum; (readNum = fis.read(buf)) != -1;) {
	            bos.write(buf, 0, readNum); //no doubt here is 0
	            //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
	           // System.out.println("read " + readNum + " bytes,");
	        }
	        byte[] bytesArchivo = bos.toByteArray();
	        keyEscritura=usf4.crearArchivo(carpetaDestino, bytesArchivo);
	        usf4.closeConnection();
			System.out.println("* * * * * IFS: END  writerByIFS * * * * *");
		}catch(Exception ex){
			System.out.println("**CATCH: "+ex);
			keyEscritura=false;
		}
		return keyEscritura;
	}//END writerByIFS
}
