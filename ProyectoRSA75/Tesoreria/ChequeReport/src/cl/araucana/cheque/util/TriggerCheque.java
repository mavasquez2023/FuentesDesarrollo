/**
 * 
 */
package cl.araucana.cheque.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author usist199
 *
 */
public class TriggerCheque {
	private static Connection con=null;
	public static void main(String args[]) throws Exception 
	{
		try
		{
			if(args.length != 4)
			{
				System.out.println("Error en par\341metros ingresados. Ingrese: Folio Cola Dispositivo Usuario");
				System.exit(1);
			}
			Properties properties = new Properties();
			properties.load(cl.araucana.cheque.util.TriggerCheque.class.getResourceAsStream("/etc/config.properties"));
			String http = properties.getProperty("urlwas");
			String paramsencode = Encripta.encode(args[3], args[0].trim());
			String parametros = "?folio=" + paramsencode + "&cola=" + args[1].trim() + "&dispositivo=" + args[2].trim();
			URL url = new URL(http + parametros);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine;
			while((inputLine = in.readLine()) != null) 
				System.out.println(inputLine);
			in.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
}
