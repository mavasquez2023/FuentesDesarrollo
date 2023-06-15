/*
 * Creado el 07-05-2007
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package cl.araucana.autoconsulta.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.channels.FileChannel;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * @author Fabrizio Barisione Biso
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class ImageProcessing {
	public static String addTextToImg(String pathWorkingDirectory,String pathSaveDirectory,String imageName,String textNombre,String textMonto,String idImg) throws Exception{

//CODIGO ANTIGUO: llamada a otra aplicacion via JNDI y clase URL
		//variables definidas en web.xml, se acceden a ellas mediante JNDI
//		Context initCtx = new InitialContext();
//		Context envCtx = (Context) initCtx.lookup("java:comp/env");
//		String ipServidor = (String) envCtx.lookup("conf/ipServerImagen");
//		String puertoServidor = (String) envCtx.lookup("conf/puertoServerImagen");
 		
//		CODIGO ANTIGUO
			  String array[] = Funciones.split(textNombre," ");
			  int countLetras = 0;
					
			  StringBuffer sb = new StringBuffer();
			  for(int i = 0; i < array.length ; i++){
						
				  String s = array[i];
				  if(!s.equals("")){	
						
					  for(int j=0;j < array[i].length();j++){
						  if(countLetras > 50){
							  break;
						  }
						  sb.append(s.charAt(j));
						  countLetras++;
					  }
					  if(countLetras < 50){
						  sb.append(" ");//MODIFICADO, antes como era distribuido hacia esto: "%20" espacio en blanco en HTML - ASCII,util para enviar el string x la URL
						  countLetras++;
					  }
				  }		
			  }
					
			  textNombre = sb.toString();
 		
			  //TODO: llevar a funcion format de monto
			  if(textMonto.length() > 0){
				  int largo = textMonto.length();
				  int numeroPuntos = 0;
			
				  /**
				   * obtengo en numero de puntos
				   */
				  if(largo % 3 != 0){ //sino es multiplo de 3
					  int resto = largo % 3;
					  numeroPuntos = (new Float(largo % 3).intValue())*3 - resto;
				  }
				  else{
					  numeroPuntos = (largo / 3) - 1;
				  }
			
				  /**
				   * añado los puntos en orden inverso
				   */
				  String resTemp = new String();
			
				  for(int i=1; i < largo ; i++){
					  char charAux = textMonto.charAt(largo - i);
					  resTemp += charAux;
					  if(i % 3 == 0) //añado punto
						  resTemp += ".";
				  }
				  resTemp += textMonto.charAt(0)+"$";
			
				  /**
				   * roto el String
				   */
				  String resTempAux = new String();
				  for(int i=1; i < resTemp.length() ; i++){
					  resTempAux += resTemp.charAt(resTemp.length() - i);
				  }
				  resTempAux += resTemp.charAt(0);
				  textMonto = resTempAux;
			  } 		
 	
 		//textNombre = textNombre.trim();
		//textMonto = textMonto.trim();				
		//se carga la imagen
		BufferedImage image = ImageIO.read(new File(pathWorkingDirectory+imageName));
	
		Graphics graphics = image.getGraphics();
		Color color = new Color(1,1,1);
		graphics.setColor(color);
		
		
		/**
		 * ESCRIBIMOS TEXTO EN IMAGEN
		 */
		int x1,y1,x2,y2;
		if(imageName.equals("Credito_Pensionados.jpg")){
			x1 = 446;
			y1 = 70;
			x2 = 513;
			y2 = 306;
		}	
		else{//AFILIADOS
			x1 = 446;
			y1 = 55;
			x2 = 513;
			y2 = 276;
		}
		Font font = new Font("Verdana",Font.BOLD,14);
		graphics.setFont(font);
		graphics.drawString(textNombre,x1,y1); //texto y coordenadas
		
		font = new Font("Verdana",Font.BOLD,20);
		graphics.setFont(font);
		graphics.drawString(textMonto,x2,y2); //texto y coordenadas
		
		String pathAbsolutoFisico = "";
				
		try{
		//crea un nuevo archivo
		//File file = new File(pathWorkingDirectory+idImg+".jpg");
		File file = new File(pathSaveDirectory+idImg+".jpg");
		pathAbsolutoFisico = file.getAbsolutePath();
		OutputStream salida = new FileOutputStream(file);
		//JPEGImageEncoder encoder = JPEGCoder.CreateJPEGEncoder( salida );
		ImageIO.write(image,"JPG",salida);
		
		salida.close();
		
		}catch(IOException e){
			throw new Exception(e);
		}
		
		System.out.println("PATH ABSOLUTO IMAGEN GENERADA : "+pathAbsolutoFisico);
		return pathAbsolutoFisico;
	}
	
	public static void deleteImg(String pathWorkingDirectory,String imageName){
		
		//Borra todos los archivos con extension JPG que no sea el de la session y los que 
		//se ocupan de plantilla, asi cada vez 
		//que se corra, borro la foto anterior, solo quedaran 3 en el sistema.
		File f1 = new File(pathWorkingDirectory);
		if(f1.isDirectory()){
			String s[] = f1.list();
			for(int i=0;i < s.length ; i++){
				//borramos todas las imagenes que no sean las 2 plantillas y la que se utiliza en la session
				if(!s[i].equals("Credito_Afiliados.jpg") && 
				   !s[i].equals("Credito_Pensionados.jpg") &&
				   !s[i].equals(imageName+".jpg")){
						File faux = new File(pathWorkingDirectory+s[i]);
						if(faux.delete())
							System.out.println("[ OK ] deleted temp IMG: "+s[i]);
						else
							System.out.println("[ NOK ] deleted temp IMG: "+s[i]);
				   }
			}
		}
	}
}
