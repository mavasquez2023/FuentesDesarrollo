package cl.laaraucana.simulacion.xml_old;

import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class TestXml {
	
	
		  public static void main(String[] args) throws Exception {
			  
		    JAXBContext contextObj = JAXBContext.newInstance(Comuna.class);
		 
		    Marshaller marshallerObj = contextObj.createMarshaller();
		    marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		 
		    Region region = new Region();
		    region.setIdRegion("id");
		    region.setNombreRegion("elnombre");
		    
		    Ciudad ciudad = new Ciudad();
		    ciudad.setIdCiudad("elId");
		    ciudad.setNombreCiudad("el nombre");
		    ciudad.setRegion(region);
		    
		    Comuna comuna = new Comuna();
		    comuna.setIdComuna("elId");
		    comuna.setNombreComuna("nombreComuna");
		    comuna.setCiudad(ciudad);
		    marshallerObj.marshal(comuna, new FileOutputStream("comuna.xml"));  
		  
		 
		  }


}
