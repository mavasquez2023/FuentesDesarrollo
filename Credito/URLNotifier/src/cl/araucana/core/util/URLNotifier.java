// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 25-01-2023 15:57:36
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 

package cl.araucana.core.util;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class URLNotifier
{
	private static boolean verbose = true;
	
    public URLNotifier()
    {
    }

    public static void main(String args[])
    {
       
        String url = args[0];

        if(url == null || url.equals(""))
        {
            System.err.println("usage: [-v] url");
            System.exit(1);
        }
        try
        {
        	int pos = url.indexOf('?');
        	if(pos==-1){
        		System.err.println("usage: [-v] url");
        		System.exit(1);
        	}

        	Properties propiedades = new Properties();
        	if(verbose){
        		System.out.println("cargando archivo de properties");
        	}
        	propiedades.load(new FileReader("/java/lib/URLNotifier.properties"));
        	String urlbase= propiedades.getProperty("url.ea");
        	if(verbose){
        		System.out.println("url base: " + urlbase);
        	}

        	StringBuffer new_params= new StringBuffer();

        	//String urlbase = "http://172.22.6.137:9080/EmpresaAdherente/UnificarNominas";
        	String params= url.substring(pos+1);
        	String tipoNomina=null;
        	String periodo=null;
        	String modoReplace=null;
        	//reemplazando los nombre de los parámetros
        	String[] campo= params.split("&");
        	for (int i = 0; i < campo.length; i++) {
        		if(campo[i].indexOf("docTypes")>-1){
        			tipoNomina= campo[i].replaceAll("docTypes", "TipoNomina").replaceAll("NC", "1").replaceAll("NL", "3").replaceAll("CF", "2").replaceAll("AT", "4");
        		}else if(campo[i].indexOf("period")>-1){
        			periodo= campo[i].replaceAll("period", "Periodo");
        		}else if(campo[i].indexOf("replaceMode")>-1){
        			if(tipoNomina.indexOf("1")>-1 || tipoNomina.indexOf("3")>-1){
        				modoReplace= "ProcesaFTP=M";
        			}else{
        				modoReplace= "ProcesaFTP=S";
        			}
        		}
        	}

        	String[] numnominas= tipoNomina.split("=")[1].split(":");
        	for (int i = 0; i < numnominas.length; i++) {
        		tipoNomina= "TipoNomina=" + numnominas[i];
        		params= "?"+tipoNomina+"&"+periodo+"&"+modoReplace;
        		if(verbose){
        			System.out.println("Parámetros para el llamado: " + params);
        		}
        		sendNotification(urlbase, params);
			}

        }
        catch(IOException ioexception)
        {
        	System.err.println(ioexception.getMessage());
        	System.exit(4);
        }
        System.exit(0);
    }


   

    private static void sendNotification(String urlbase, String params)
        throws IOException
    {
        boolean flag = true;
        while(flag) 
        {	
        	String url_full=null;
            try
            {	url_full= urlbase+ params;
                URL url = new URL(url_full);
                if(verbose)
                {
                    System.out.println("Notifing to '" + url_full + "' ...");
                }
                
                BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(url.openStream()));
                if(verbose)
                {
                    System.out.println("Ejecución exitosa!");
                }
                String s5;
                int i;
                for(i = 0; (s5 = bufferedreader.readLine()) != null; i++)
                {
                    if(verbose)
                        System.out.println(s5);
                    if(i == 0 )
                        flag = false;
                }

                if(i == 0)
                    flag = false;
                bufferedreader.close();
            }
            catch(MalformedURLException _ex)
            {
                throw new IOException("Invalid URL '" + url_full + "'.");
            }
            catch(IOException _ex)
            {
                throw new IOException("I/O exception trying opening connection to URL '" + url_full + "'.");
            }
        }
    }
   

}
