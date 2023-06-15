package cl.laaraucana.simulacion.xml;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import cl.laaraucana.simulacion.utils.UtilLeerArchivo;

public class TestXml {
	
	
		  public static void main(String[] args) throws Exception {
			  
		    JAXBContext contextObj = JAXBContext.newInstance(Regiones.class);
		 
		    Marshaller marshallerObj = contextObj.createMarshaller();
		    marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    
		    List<Region> listaRegiones = new ArrayList<Region>();
		    Regiones regiones = new Regiones();
		    
		    //Leer archivo
		    List<String> fileRegiones = UtilLeerArchivo.leerArchivo("C:/Regiones.csv");
		    
		    for (String string : fileRegiones) {
				System.out.println(string);
			}
		    
		    int pos = 0;
		    for (int i = 1; i <= 13; i++) {
		    	String[] dividirStr = fileRegiones.get(pos).split(";");
		    	
		    	Region region = new Region();
			    region.setIdRegion(String.valueOf(i));
			    region.setNombreRegion(dividirStr[0]);
			    List<Comuna> comunas = new ArrayList<Comuna>();
			    
			    while(true) {
			    	Comuna comuna = new Comuna();
				    comuna.setIdComuna(dividirStr[1]);
				    comuna.setNombreComuna(dividirStr[2]);
				    comunas.add(comuna);
				    pos++;
				    dividirStr = fileRegiones.get(pos).split(";");
				    if(!dividirStr[0].equals("")){
			    		break;
			    	}
				}
			    region.setComuna(comunas);
			    listaRegiones.add(region);
			}
		    
		    regiones.setRegion(listaRegiones);
		    
		    marshallerObj.marshal(regiones, new FileOutputStream("regiones.xml"));  
		 
		  }
}
