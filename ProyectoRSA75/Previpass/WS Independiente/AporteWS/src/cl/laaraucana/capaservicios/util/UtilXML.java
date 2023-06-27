package cl.laaraucana.capaservicios.util;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import cl.laaraucana.capaservicios.xml.notificacion.Rendicion;
import cl.laaraucana.capaservicios.xml.solicitud.SolicitudTEF;

public class UtilXML {
	
	/**Retorna estructura y datos a que vienen en una clase "Solicitud" a un String en formato de XML*/
	public static String getStringFromXML (SolicitudTEF solicitud){
		
		String theXML = ""; 

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(SolicitudTEF.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// setea Propiedades del XML a Generar 
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);//xml formateado
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");//codificacion del xml

			// obtener el xml en String
			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(solicitud, writer);
			theXML = writer.toString();

		} catch (PropertyException e) {
			e.printStackTrace();
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return theXML;
	}

	/**
	 * Retorna objeto SolicitudTEF, con los datos obtenidos del xml
	 * */
	public static SolicitudTEF getObjectXMLSolicitud(String  xmlString) throws JAXBException{
		SolicitudTEF solicitud= new SolicitudTEF();
		
		ByteArrayInputStream input = new ByteArrayInputStream (xmlString.getBytes()); 
		
		JAXBContext context = JAXBContext.newInstance(
				cl.laaraucana.capaservicios.xml.solicitud.Transferencia.class,
				cl.laaraucana.capaservicios.xml.solicitud.Solicitud.class, 
				cl.laaraucana.capaservicios.xml.solicitud.Beneficiario.class,
				cl.laaraucana.capaservicios.xml.solicitud.SistemaOrigen.class,
				cl.laaraucana.capaservicios.xml.solicitud.ListaTEF.class,
				cl.laaraucana.capaservicios.xml.solicitud.SolicitudTEF.class
				);
		
		Unmarshaller um;
		um = context.createUnmarshaller();
		solicitud=(SolicitudTEF)um.unmarshal(input);
		
		return solicitud; 
	}
	

	/**
	 * Mapea el Xml de respuesta del servicio de Notificacion a objeto
	 * @param xmlString
	 * @return
	 * @throws JAXBException
	 */
	public static Rendicion getObjectXMLRendicion(String  xmlString) throws JAXBException{
		Rendicion rendicion= new Rendicion();

		ByteArrayInputStream input = new ByteArrayInputStream (xmlString.getBytes()); 
		
		JAXBContext context = JAXBContext.newInstance(
				cl.laaraucana.capaservicios.xml.notificacion.Transferencia.class,
				cl.laaraucana.capaservicios.xml.notificacion.Beneficiario.class,
				cl.laaraucana.capaservicios.xml.notificacion.Rendicion.class
				);
		
		Unmarshaller um;
		um = context.createUnmarshaller();
		rendicion=(Rendicion)um.unmarshal(input);
		
		return rendicion; 
	}

}
