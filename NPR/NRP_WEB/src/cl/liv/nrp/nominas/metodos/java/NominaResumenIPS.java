package cl.liv.nrp.nominas.metodos.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import cl.liv.archivos.comun.txt.ManejoArchivoTXT;
import cl.liv.comun.utiles.PropertiesUtil;
import cl.liv.comun.utiles.UtilesComunes;

public class NominaResumenIPS {
	
	int totalRegistros = 0;
	long montoTotal = 0;

	public ArrayList obtenerResumenesIPS(HashMap parametros) throws IOException{
		ArrayList data = new ArrayList();
		String codigoEntidad = "61533000";
		String year = UtilesComunes.getVariable("sys.year4");
		String mes = UtilesComunes.getVariable("sys.month2");
		String periodo_sig= UtilesComunes.getVariable("sys.periodo.MMAAAA.siguiente");
		
		String PATH_NOMINAS_IPS = PropertiesUtil.propertiesNominas.getString("config.output.nomina.path")+"/"+year+mes+"/"+codigoEntidad+"/";
		
		boolean esperar = true;
		while(esperar){
			
			File archivo81 = new File(PATH_NOMINAS_IPS, "FU22581"+periodo_sig+".txt");
			File archivo83 = new File(PATH_NOMINAS_IPS, "FU22583"+periodo_sig+".txt");
			File archivo41 = new File(PATH_NOMINAS_IPS, "FU22541"+periodo_sig+".txt");
			/*
			File archivo81 = getFile(PATH_NOMINAS_IPS, "FU22581"+periodo_sig+".txt");
			File archivo83 = getFile(PATH_NOMINAS_IPS, "FU22583"+periodo_sig+".txt");
			File archivo41 = getFile(PATH_NOMINAS_IPS, "FU22541"+periodo_sig+".txt");
			*/
			
			System.out.println("Existe directorio: "+ new File(PATH_NOMINAS_IPS).exists());
			System.out.println("Existe file: "+ archivo81);
			
			if( archivo81 != null && archivo81.exists() && archivo81.length() > 0 ){
				if( archivo83 != null && archivo83.exists() && archivo83.length() > 0 ){
					if( archivo41 != null  && archivo41.exists() && archivo41.length() > 0){
						esperar = false;
					}
				}
			}
			
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		data.addAll( obtenerDataArchivo(PATH_NOMINAS_IPS, "FU22581"+periodo_sig+".txt", "81", "a)", "01,06,07,09,10,11,12,14,16,44,45,57")   );
		//data.addAll( obtenerDataArchivo(PATH_NOMINAS_IPS, "FU22582"+mes+year+".txt", "82", "b)", "01, 06, 07, 10, 11, 12, 14, 16, 45")   );
		data.addAll( obtenerDataArchivo(PATH_NOMINAS_IPS, "FU22583"+periodo_sig+".txt", "83", "b)","02,05,08,13,15,17,18,20,21,22,23,24,39,51,58,94")   );
		data.addAll( obtenerDataArchivo(PATH_NOMINAS_IPS, "FU22541"+periodo_sig+".txt", "41", "c)","41")   );
		data.add( getLinea(";;;") );
		data.add( getLinea(";;;") );
		data.add( getLinea(";TOTAL REGISTROS;:;"+totalRegistros) );
		data.add( getLinea(";MONTO TOTAL;:;"+UtilesComunes.getMontoFormateado(montoTotal+"")) );
		
		return data;
	}
	
	
	private File getFile(String path, String fileName) {
		File folder = new File(path);
		if(folder != null && folder.exists()) {
			File[] archivos = folder.listFiles();
			for(int i=0; i< archivos.length; i++) {
				if(fileName.equalsIgnoreCase(archivos[i].getName()) ) {
					return archivos[i];
				}
			}
			
		}
		return null;
	}
	
	
	private ArrayList obtenerDataArchivo(String path, String file, String codigo, String prefijo, String exCajas) throws IOException{
		ArrayList data = new ArrayList();

		String year = UtilesComunes.getVariable("sys.year4");
		String mes = UtilesComunes.getVariable("sys.month2");
		
		String mesDescriptivo = UtilesComunes.obtenerDescripcionMes(Integer.parseInt( mes ));
		data.add( getLinea(prefijo+";Mes de Proceso;:;"+mesDescriptivo+" "+ year)  );
		data.add( getLinea(";Número del Archivo;:; "+ codigo)  );
		data.add( getLinea(";Nombre del Archivo;:; "+ file ) );
		data.add( getLinea(";ExCajas;:; "+ exCajas ) );
		data.add( getLinea(";Código de Descuento;:; 225" ) );

		int contador = 0;
		long monto = 0;
		
		if(new File(path+file).exists()){
		
			String encoding = "ISO-8859-1";
			BufferedReader br = ManejoArchivoTXT.getOpenedFileToRead(path+ file, 0, encoding);
			int indiceInicialMonto = 77;
			int indiceFinalMonto = 84;
			String linea = br.readLine();
			while(linea != null){
				long subtotal = Integer.parseInt(linea.substring(indiceInicialMonto, indiceFinalMonto));
				monto = monto + subtotal;
				linea = br.readLine();
				contador++;
			}
			ManejoArchivoTXT.closeFileToRead(br);
			
		}
		
		totalRegistros = totalRegistros+contador;
		montoTotal = montoTotal + monto;
		data.add( getLinea(";Cantidad de registros;:; "+ contador ) );
		data.add( getLinea(";Monto;:; "+ UtilesComunes.getMontoFormateado(monto+"") ) );
		data.add( getLinea(";;;") );
		
		return data;
	}
	
	private HashMap getLinea(String texto){
		HashMap map = new HashMap();
		map.put("LINEA", texto);
		return map;
	}
}
