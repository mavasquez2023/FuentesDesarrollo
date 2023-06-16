/*
 * Creado el 17-02-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.entidades.business;


import cl.araucana.core.util.PropertiesLoader;
import cl.araucana.core.util.logging.LogManager;

import com.ibm.as400.access.*;
import java.math.BigDecimal;
import java.util.Properties;
import java.util.logging.Logger;


/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class Folio{
	private static Folio instance= new Folio();
	private String ip, pgm, us, ps;
	private String errorMsg="";
	private static Logger logger = LogManager.getLogger();
	protected Properties properties;

	public static Folio getInstance(){
		return instance;
	}
	
	public Folio() {
		try {
			loadProperties("/etc/folio.properties");
			ip= properties.getProperty("ip");
			pgm= properties.getProperty("programa");
			us= properties.getProperty("usuario");
			ps= properties.getProperty("password");
		/*	Context ctx = new InitialContext();			
			//Se rescata DataSource de conexión a BD
			ip = (String)ctx.lookup("java:comp/env/IP");
			pgm = (String)ctx.lookup("java:comp/env/Programa");
			us = (String)ctx.lookup("java:comp/env/Usuario");
			ps = (String)ctx.lookup("java:comp/env/Password");
			*/
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}

    
    public String getFolio()
    {
        AS400 as400 = new AS400(ip,us,ps);
        BigDecimal packedin = new BigDecimal(3D);
        BigDecimal packedReturn = new BigDecimal(9D);
        ProgramParameter pList[] = new ProgramParameter[4];
        BigDecimal status = new BigDecimal(0.0D);
        BigDecimal codigo = new BigDecimal(1.0D);
        BigDecimal folio = new BigDecimal(0.0D);
        pList[0] = new ProgramParameter((new AS400ZonedDecimal(7, 0)).toBytes(status), 0);
        pList[1] = new ProgramParameter((new AS400PackedDecimal(3, 0)).toBytes(codigo), 0);
        pList[2] = new ProgramParameter((new AS400PackedDecimal(3, 0)).toBytes(codigo), 0);
        pList[3] = new ProgramParameter((new AS400PackedDecimal(9, 0)).toBytes(folio), 9);
        QSYSObjectPathName programName = new QSYSObjectPathName(pgm);
        ProgramCall inv0011 = new ProgramCall(as400, programName.getPath(), pList);
        try
        {
            if(!inv0011.run())
            {
                StringBuffer msg = new StringBuffer();
                AS400Message msgList[] = inv0011.getMessageList();
                for(int i = 0; i < msgList.length; i++)
                    msg.append(msgList[i].getText().toString());

                errorMsg = "Retorno Rutina FOLIO.\n" + msg.toString();
                return null;
            } else
            {
                packedin = (BigDecimal)(new AS400PackedDecimal(3, 0)).toObject(pList[2].getInputData());
                packedReturn = (BigDecimal)(new AS400PackedDecimal(9, 0)).toObject(pList[3].getOutputData());
                return packedReturn.toString();
            }
        }
        catch(ObjectDoesNotExistException dnf)
        {
            errorMsg = "Error al ejecutar la Rutina FOLIO.'n" + dnf.getMessage();
            logger.severe(errorMsg);
            dnf.printStackTrace();
            return null;
        }
        catch(Exception error)
        {
            errorMsg = "Error al ejecutar la Rutina FOLIO.'n" + error.getMessage();
            logger.severe(errorMsg);
            error.printStackTrace();
            return null;
        }
    }
    
    private void loadProperties(String fileproperties){
    PropertiesLoader propertiesloader = new PropertiesLoader();
    try
    {
        properties = propertiesloader.load(fileproperties, cl.araucana.cierrecpe.entidades.business.Folio.class);
    }
    catch(Exception eproperties)
    {
    	logger.severe("cannot open '/etc/folio.properties' properties file." + eproperties.getMessage());
        eproperties.printStackTrace();
    }
}
    
}
