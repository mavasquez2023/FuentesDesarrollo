

package cl.araucana.wssiagf;


import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

import cl.araucana.core.util.BasicDataSource;
import cl.araucana.wssiagf.wsinterfaces.DB2WebServicesInterface;


public class WSSIAGFConnectorLauncher implements Operations {

	public static final String PROPERTIES_FILE = "/app/etc/wssiagf.properties";
	public static final long DEFAULT_SAMPLE_TIME = 180000L;

	private WSSIAGFConnectorConfig config;
	private DB2WebServicesInterface db2Interface;
	private Properties properties;
	private HashMap serviceURLs = new HashMap();

    public static void main(String args[]) {
		WSSIAGFConnectorLauncher launcher = new WSSIAGFConnectorLauncher();
		int rc = 0;
		long sampleTime = 0L;

		if (args.length == 1) {
			sampleTime = DEFAULT_SAMPLE_TIME;
			sampleTime = Long.parseLong(args[0]);

			try {
			} catch (NumberFormatException e) {}

			System.out.println(
					  "Running in sample mode. "
					+ "sampleTime=" + sampleTime + " ms.");
		}

		try {
			launcher.loadWSSIAGFConfiguration();

			WSSIAGFConnectorConfig config = launcher.getConfig();

			if (config.isDB2InterfaceEnabled()) {
				launcher.startDB2Interface();

				if (sampleTime > 0L) {
					try {
						Thread.sleep(sampleTime);
					} catch (InterruptedException e) {}

					launcher.stopDB2Interface();
				} else {
					launcher.sleep();
				}
			} else {
				System.out.println("DB/2 interface is disabled.");
			}
		} catch (WSSIAGFException e) {
			System.out.println("WSSIAGF connector cannot be started:");
			e.printStackTrace(System.err);

			rc = 1;
		}

		System.exit(rc);
	}

	public void loadWSSIAGFConfiguration() throws WSSIAGFException {
		InputStream input = getResourceAsStream(PROPERTIES_FILE);

		if (input == null) {
			throw new WSSIAGFException(
						"cannot find resource '" + PROPERTIES_FILE + "'.");
		}

		properties = new Properties();

		try {
			properties.load(input);
		} catch (IOException e) {
			throw new WSSIAGFException(
					"cannot load resource '" + PROPERTIES_FILE + "'.");
		} finally {
			try {
				input.close();
			} catch (IOException e) {}
		}

		config = new WSSIAGFConnectorConfig();

		config.setEntidadAdministradora(
				getIntProperty("entidadAdministradora"));

		Credential credential = new Credential();

		credential.setID(getIntProperty("credential.id"));
		credential.setUserName(getStringProperty("credential.userName"));
		credential.setPassword(getStringProperty("credential.password"));

		config.setCredential(credential);

		addWebService("Version");
		addWebService("Autenticacion");
		addWebService("IngresoReconocimiento");
		addWebService("ConsultaCausante");
		addWebService("ExtincionReconocimiento");
		addWebService("ActualizarCausante");

		config.setServiceURLs(getWebServices());

		config.setTimeout(getIntProperty("timeout"));
		config.setNRetries(getIntProperty("nRetries"));

		config.setXmlHelpedSystemIDs(getStringProperty("xmlHelpedSystemIDs"));
		
		config.setXmlHempedSystemIDRetrocompatible(getStringProperty("xmlHelpedSystemIDRetrocompatible"));
		config.setModoTramosRetroactivos(getBooleanProperty("modoTramosRetroactivos"));
		
		int nOperations = getIntProperty("nOperations");
		String opDummyModes = getStringProperty("opDummyModes");

		if (opDummyModes.trim().equals("")) {
			opDummyModes = "";

			for (int i = 0; i < nOperations; i++) {
				if (i + 1 < nOperations) {
					opDummyModes += "true:";
				} else {
					opDummyModes += "true";
				}
			}
		}

		config.setOpDummyModes(opDummyModes);

		config.setDB2InterfaceEnabled(getBooleanProperty("db2.enabled"));
		config.setHTTPInterfaceEnabled(getBooleanProperty("http.enabled"));

		config.setSleepTime(getIntProperty("db2.sleepTime"));
		config.setRequestTableName(getStringProperty("db2.requestTableName"));
		config.setBusinessDataTableName(getStringProperty("db2.businessDataTableName"));

		config.setMaxParameterLength(getIntProperty("db2.maxParameterLength"));
		config.setMaxMessageLength(getIntProperty("db2.maxMessageLength"));

		String dataSourcePropertiesFile =
				getStringProperty("db2.dataSourcePropertiesFile");

		input = getResourceAsStream(dataSourcePropertiesFile);

		if (input == null) {
			throw new WSSIAGFException(
						  "cannot find resource "
						+ "'" + dataSourcePropertiesFile + "'.");
		}

		properties = new Properties();

		try {
			properties.load(input);
		} catch (IOException e) {
			throw new WSSIAGFException(
					  "cannot load resource '"
					+ dataSourcePropertiesFile + "'.");
		} finally {
			try {
				input.close();
			} catch (IOException e) {}
		}

		BasicDataSource dataSource = null;

		try {
			dataSource = new BasicDataSource(properties);
		} catch (SQLException e) {
			throw new WSSIAGFException(
					  "data source cannot be created. "
					+ "[cause=" + e.getMessage() + "]");
		}

		config.setDataSource(dataSource);

		db2Interface = new DB2WebServicesInterface(config);
	}

	public WSSIAGFConnectorConfig getConfig() {
		return config;
	}

	public void startDB2Interface() {
		db2Interface.start();
	}

	public void sleep() {
		db2Interface.join();
	}

	public void stopDB2Interface() {
		db2Interface.stop();
	}

	private void addWebService(String name) throws WSSIAGFException {

		serviceURLs.put(name, getStringProperty("webService." + name));
	}

	private HashMap getWebServices() {
		return serviceURLs;
	}

	private InputStream getResourceAsStream(String resource) {
		return getClass().getResourceAsStream(resource);
	}

	private boolean getBooleanProperty(String name) throws WSSIAGFException {
		return getBooleanProperty(name, null);
	}

	private boolean getBooleanProperty(String name, Boolean defaultValue)
			throws WSSIAGFException {

		String value = properties.getProperty(name);

		if (value == null) {
			if (defaultValue == null) {
				throw new WSSIAGFException("Missing property '" + name + "'.");
			}

			return defaultValue.booleanValue();
		}

		try {
			return Boolean.valueOf(value).booleanValue();
		} catch (NumberFormatException e) {
			throw new WSSIAGFException(
					"Bad value '" + value + "' for property '" + name + "'.");
		}
	}

	private int getIntProperty(String name) throws WSSIAGFException {
		return getIntProperty(name, null);
	}

	private int getIntProperty(String name, Integer defaultValue)
			throws WSSIAGFException {

		String value = properties.getProperty(name);

		if (value == null) {
			if (defaultValue == null) {
				throw new WSSIAGFException("Missing property '" + name + "'.");
			}

			return defaultValue.intValue();
		}

		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new WSSIAGFException(
					"Bad value '" + value + "' for property '" + name + "'.");
		}
	}

	private String getStringProperty(String name) throws WSSIAGFException {
		return getStringProperty(name, null);
	}

	private String getStringProperty(String name, String defaultValue)
			throws WSSIAGFException {

		String value = properties.getProperty(name);

		if (value == null) {
			if (defaultValue == null) {
				throw new WSSIAGFException("Missing property '" + name + "'.");
			}

			return defaultValue;
		}

		return value;
	}
}
