package cl.laaraucana.integracion.impl;


import org.apache.log4j.Logger;
import org.xml.sax.*;

public class XMLErrorHandler implements ErrorHandler
{
	private static Logger log = Logger.getLogger(XMLErrorHandler.class);

	public void warning(SAXParseException exception)
    {
		log.warn("Warning: " + exception.getMessage());
        System.out.println("Warning: " + exception.getMessage());
    }
    public void error(SAXParseException exception)
    {
    	log.error("Error: " + exception.getMessage());
        System.out.println("Error: " + exception.getMessage());
    }
    public void fatalError(SAXParseException exception)
    {
    	log.fatal("Fatal Error: " + exception.getMessage());
        System.out.println("Fatal Error: " + exception.getMessage());
    }


}
