/**
 * 
 */
package cl.araucana.cheque.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

/**
 * @author usist199
 *
 */
public class TriggerCheque {
	public static void main(String args[]) throws Exception 
	{
		
		try 
		{
			if(args.length!=4){
				System.out.println("Error en parámetros ingresados. Ingrese: Folio Cola Dispositivo Usuario");
				System.exit(1);
			}
			Properties properties = new Properties();
			properties.load(TriggerCheque.class.getResourceAsStream("/etc/config.properties"));
			String http= properties.getProperty("urlwas");
			String paramsencode= Encripta.encode(args[3], args[0]);
			String parametros= "?folio=" + paramsencode+ "&cola=" + args[1] + "&dispositivo=" + args[2];
			URL url = new URL(http + parametros);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine;
	        while((inputLine = in.readLine()) != null)
	            System.out.println(inputLine);
	        in.close();
		} 
		catch (Exception ex) 
		{	 
			ex.printStackTrace();
		}	
	}
	
}
