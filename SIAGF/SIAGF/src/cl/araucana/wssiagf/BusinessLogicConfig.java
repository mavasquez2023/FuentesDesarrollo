package cl.araucana.wssiagf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import cl.araucana.core.util.logging.LogManager;

public class BusinessLogicConfig {
	private static Logger logger = LogManager.getLogger();
	
	private static Properties properties;
	private String DSJNDIName;
	private String tramosHistoricos;
	private String tramosHistoricosAfiliado;
	private String rentaHistoricaAfiliado;
	private String rentaHistoricaAfiliado2;
	private long valorMaximoRenta;
	private int maxTramosRetroactivos;
	private int numTramoDefault;
		
	BusinessLogicConfig(String propertyFile) throws WSSIAGFException{
		InputStream input = getResourceAsStream(propertyFile);

		if (input == null) {
			throw new WSSIAGFException(
						"cannot find resource '" + propertyFile + "'.");
		}

		properties = new Properties();

		try {
			properties.load(input);
		} catch (IOException e) {
			throw new WSSIAGFException(
					"cannot load resource '" + propertyFile + "'.");
		} finally {
			try {
				input.close();
			} catch (IOException e) {}
		}
		
		DSJNDIName = getStringProperty("logic.jdbc");
		tramosHistoricos = getStringProperty("logic.db2.tramosHistoricos");
		tramosHistoricosAfiliado = getStringProperty("logic.db2.tramosHistoricosAfiliado");
		rentaHistoricaAfiliado = getStringProperty("logic.db2.rentaHistoricaAfiliado");
		rentaHistoricaAfiliado2 = getStringProperty("logic.db2.rentaHistoricaAfiliado2");
		valorMaximoRenta = getLongProperty("logic.valorMaximo");
		maxTramosRetroactivos = getIntProperty("logic.maxTramosRetroactivos");
		numTramoDefault = getIntProperty("logic.numTramoDefault");
		
		logger.config("Business Logic Configuration:");
		logger.config("    JNDI Name					= " + DSJNDIName);
		logger.config("    Tabla tramos Historicos		= " + tramosHistoricos);
		logger.config("    Tabla tramos Hist Afiliados	= " + tramosHistoricosAfiliado);
		logger.config("    Tabla renta Hist	Afiliados	= " + rentaHistoricaAfiliado);
		logger.config("    Tabla renta Hist Afiliados 2	= " + rentaHistoricaAfiliado2);
		logger.config("    Valor Maximo Renta			= " + valorMaximoRenta);
		logger.config("    Maximo Tramos Retroactivos	= " + maxTramosRetroactivos);
		logger.config("    Numero tramo default			= " + numTramoDefault);
		logger.config("");

	}
	
	private InputStream getResourceAsStream(String resource) {
		return getClass().getResourceAsStream(resource);
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
	
	private long getLongProperty(String name) throws WSSIAGFException {
		return getLongProperty(name, null);
	}

	private long getLongProperty(String name, Integer defaultValue)
			throws WSSIAGFException {

		String value = properties.getProperty(name);

		if (value == null) {
			if (defaultValue == null) {
				throw new WSSIAGFException("Missing property '" + name + "'.");
			}

			return defaultValue.longValue();
		}

		try {
			return Long.parseLong(value);
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

	public String getDSJNDIName() {
		return DSJNDIName;
	}

	public int getMaxTramosRetroactivos() {
		return maxTramosRetroactivos;
	}

	public int getNumTramoDefault() {
		return numTramoDefault;
	}

	public String getRentaHistoricaAfiliado() {
		return rentaHistoricaAfiliado;
	}

	public String getRentaHistoricaAfiliado2() {
		return rentaHistoricaAfiliado2;
	}

	public String getTramosHistoricos() {
		return tramosHistoricos;
	}

	public String getTramosHistoricosAfiliado() {
		return tramosHistoricosAfiliado;
	}

	public long getValorMaximoRenta() {
		return valorMaximoRenta;
	}
	
}
