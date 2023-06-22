import java.io.BufferedReader; 
import java.io.InputStream; 
import java.io.InputStreamReader; 
import java.net.URL;
import java.net.URLConnection;
/** 
 * @author Daryl Banttari 
 * 
 */ 
public class SSLTest { 

    public static void main(String[] args) { 
        // default url: 

//El proxy es solo si usas proxy para salir a internet 
        //System.setProperty("https.proxyHost", "9.7.2.52"); 
       // System.setProperty("https.proxyPort", "80"); 
                //Sin comentario el trust.jks que usaras 
    	System.setProperty("javax.net.debug","true");
        //System.setProperty("javax.net.ssl.trustStore", "C:/trustfiles/trust.jks");                                 
        System.setProperty("javax.net.ssl.trustStore", "C:/trustfiles/Trustexpirado/trust.jks");                                 
        System.setProperty("javax.net.ssl.trustStorePassword","metricarts"); 
        
        System.setProperty("javax.net.ssl.keyStore", "C:/trustfiles/Trustexpirado/keys.jks"); 
        System.setProperty("javax.net.ssl.keyStorePassword","metricarts"); 
                
        String urlString = "https://www.paypal.com/"; 
        
        System.out.println("Trust: " + System.getProperty("javax.net.ssl.trustStore"));
        System.out.println("Keys: " + System.getProperty("javax.net.ssl.keyStore"));
        // if any url specified, use that instead: 

        if(args.length > 0) { 
            urlString = args[0]; 
        } 
        System.out.println("Connecting to " + urlString + "..."); 
        
        try { 
            // convert user string to URL object 

            URL url = new URL(urlString); 

            // connect! 

            URLConnection cnx = url.openConnection(); 
            cnx.connect(); 

            // read the page returned 

            InputStream ins = cnx.getInputStream(); 
            BufferedReader in = new BufferedReader(new InputStreamReader(ins)); 
            String curline; 
            while( (curline = in.readLine()) != null ) { 
                System.out.println(curline); 
            } 

            // close the connection 

            ins.close(); 
        } 
        catch(Throwable t) { 
            t.printStackTrace(); 
        } 

    } 
} 