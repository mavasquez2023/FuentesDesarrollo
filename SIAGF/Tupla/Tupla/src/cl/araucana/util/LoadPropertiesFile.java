package cl.araucana.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadPropertiesFile {

	/**
	 * Load a properties file from the classpath
	 * @param propsName
	 * @return Properties
	 * @throws Exception
	 */
	public Properties load(String propsName) throws Exception {
		Properties props = new Properties();
		props.load(getClass().getClassLoader().getResourceAsStream(propsName));
		return props;
	}

	/**
	 * Load a Properties File
	 * @param propsFile
	 * @return Properties
	 * @throws IOException
	 */
	public Properties load(File propsFile) throws IOException {
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream(propsFile);
		props.load(fis);
		fis.close();
		return props;
	}

}
