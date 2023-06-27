/**
 * 
 */
package cl.araucana.cheque.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;
import java.util.Properties;

/**
 * @author usist199
 *
 */
public class TriggerCheque_sinDB2 {

	public static void main(String args[]) throws Exception 
	{
		
		try 
		{
			if(args.length!=4){
				System.out.println("Error en parámetros ingresados. Ingrese: Folio Cola Dispositivo Usuario");
				System.exit(1);
			}
			Properties properties = new Properties();
			properties.load(TriggerCheque_sinDB2.class.getResourceAsStream("/etc/config.properties"));
			String http= properties.getProperty("urlwas");
			String http2= properties.getProperty("urlwas2");
			String paramsencode= Encripta.encode(args[3], args[0]);
			String parametros= "?folio=" + paramsencode+ "&cola=" + args[1] + "&dispositivo=" + args[2];
			//Se conecta a server WASpara invocar la URL
			URL url = new URL(http + parametros);
			if(!connectServer(url)){
				//Si servidor WAS tuvo problemas se usa server de Respaldo
				url = new URL(http2 + parametros);
				connectServer(url);
					
			}
		} 
		catch (Exception ex) 
		{	 
			ex.printStackTrace();
		}
	
	}
	
	public static boolean connectServer(URL url){
		boolean resultado= false;
		try 
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine;
			while((inputLine = in.readLine()) != null)
				System.out.println(inputLine);
			in.close();
			resultado=true;
		}catch (ConnectException ex) 
		{	 
			ex.printStackTrace();
		}
		catch (IOException ex) 
		{	 
			ex.printStackTrace();
		}
		return resultado;
	}
	
}
