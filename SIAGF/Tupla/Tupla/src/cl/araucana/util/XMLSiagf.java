/**
 * 
 */
package cl.araucana.util;

import java.io.File;
import java.io.FileWriter;
import java.util.Properties;

/**
 * @author IBM Software Factory
 *
 */
public class XMLSiagf {
	private static Properties properties;
	static {
		try {
			properties = new LoadPropertiesFile().load("wssiagf.properties");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void saveXML(String rutBeneficiario, String data)
    {
        String extension = ".xml";
        String path= properties.getProperty("path.xml.siagf");
        try
        {
            File file = new File(path+ "/" + rutBeneficiario + extension);
            FileWriter fw = new FileWriter(file);
            fw.write(data);
            fw.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
