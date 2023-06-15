package cl.liv.archivos.comun.txt;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import cl.liv.comun.utiles.MiHashMap;

public class ShowLogFile {

	public static HashMap<String, MiHashMap> logs = new HashMap<String, MiHashMap>();
	public static int TIEMPO_REFRESCO = 2000;
	public static int TIEMPO_MAXIMO_VIDA = 60*1000*5;
	
	public static void eliminarLog() {

		
		Iterator it = logs.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String)it.next();
			if(logs.get(key)!= null && (new Date().getTime() - ((Long)((MiHashMap)logs.get(key)).get("INIT")).longValue())>TIEMPO_MAXIMO_VIDA ) {
				logs.remove(key);
			}
		}
		
	}
	public static MiHashMap addLog(String key) {
		//if(logs.get(key)!= null && ((MiHashMap)logs.get(key)).get("INIT")) 
		MiHashMap pars = new MiHashMap();
		pars.put("INIT", new Long(new Date().getTime()));
		pars.put("LAST_READ", new Long(0));
		pars.put("TO", new Long(0));
		pars.put("READ", Boolean.FALSE);
		logs.put(key, pars);
		return logs.get(key);
	}
	
	
	public static ArrayList getLines(String key, int primerasLineas) {
		eliminarLog();
		MiHashMap pars = logs.get(key);
		if(pars == null) {
			pars = addLog(key);
		}
		System.out.println(pars);
		ArrayList<String> lineas = new ArrayList<String>();
		BufferedReader br = ManejoArchivoTXT.getOpenedFileToRead(key, false, null);
		String linea = ManejoArchivoTXT.getLineFromFileOpened(br);
		while(linea !=null ) {
			lineas.add(linea);
			linea = ManejoArchivoTXT.getLineFromFileOpened(br);			
		}
		ManejoArchivoTXT.closeFileToRead(br);
		
		int from = 0;
		int to = 0;
		
		if(((Boolean)pars.get("READ"))==Boolean.FALSE) {
			from = lineas.size() - primerasLineas;
			to = from + primerasLineas;
			pars.put("READ", Boolean.TRUE);
		}
		else {
			from = ((Long)pars.get("TO")).intValue();
			to = lineas.size();
		}
		System.out.println("line -> [from:"+from+",to:"+to+"]");
		ArrayList<String> lineasToReturn = new ArrayList<String>();
		for(int i=from; i< to; i++) {
			lineasToReturn.add( lineas.get(i) );
		}

		pars.put("TO", new Long(to));
		pars.put("LAST_READ", new Long(new Date().getTime()));
		return lineasToReturn;
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				
			}
		}).start();
		
		for(int i=0; i< 100; i++) {
			ArrayList<String> lineas = getLines("C:\\nrp\\logs\\server.log", 100);
			for (String linea : lineas) {
				System.out.println("->"+ linea);
			}
			Thread.sleep(1000);
		}
		
	}
	
}
