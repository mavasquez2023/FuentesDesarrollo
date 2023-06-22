

package cl.araucana.wssiagf;


import java.io.Serializable;
import java.util.HashMap;

import javax.sql.DataSource;


public class WSSIAGFConnectorConfig implements Serializable {

	private int entidadAdministradora;

	private Credential credential;
	private HashMap serviceURLs;
	private long timeout;
	private int nRetries;
	private String xmlHelpedSystemIDs;
	private String xmlHempedSystemIDRetrocompatible;
	private boolean modoTramosRetroactivos;
	
	private boolean db2InterfaceEnabled;
	private boolean httpInterfaceEnabled;

	private DataSource dataSource;
	private long sleepTime;
	private String requestTableName;
	private String businessDataTableName;
	private String opDummyModes;
	private int maxParameterLength;
	private int maxMessageLength;

	public WSSIAGFConnectorConfig() {
	}

	public void setEntidadAdministradora(int entidad) {
		this.entidadAdministradora = entidad;
	}

	public int getEntidadAdministradora() {
		return entidadAdministradora;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	public Credential getCredential() {
		return credential;
	}

	public void setServiceURLs(HashMap serviceURLs) {
		this.serviceURLs = serviceURLs;
	}

	public HashMap getServiceURLs() {
		return serviceURLs;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setNRetries(int nRetries) {
		this.nRetries = nRetries;
	}

	public int getNRetries() {
		return nRetries;
	}

	public void setXmlHelpedSystemIDs(String xmlHelpedSystemIDs) {
		this.xmlHelpedSystemIDs = xmlHelpedSystemIDs;
	}

	public String getXmlHelpedSystemIDs() {
		return xmlHelpedSystemIDs;
	}

	public String getXmlHempedSystemIDRetrocompatible() {
		return xmlHempedSystemIDRetrocompatible;
	}

	public void setXmlHempedSystemIDRetrocompatible(
			String xmlHempedSystemIDRetrocompatible) {
		this.xmlHempedSystemIDRetrocompatible = xmlHempedSystemIDRetrocompatible;
	}

	public void setDB2InterfaceEnabled(boolean enabled) {
		this.db2InterfaceEnabled = enabled;
	}

	public boolean isDB2InterfaceEnabled() {
		return db2InterfaceEnabled;
	}

	public void setHTTPInterfaceEnabled(boolean enabled) {
		this.httpInterfaceEnabled = enabled;
	}

	public boolean isHTTPInterfaceEnabled() {
		return httpInterfaceEnabled;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setSleepTime(long sleepTime) {
		this.sleepTime = sleepTime;
	}

	public long getSleepTime() {
		return sleepTime;
	}

	public void setRequestTableName(String tableName) {
		this.requestTableName = tableName;
	}

	public String getRequestTableName() {
		return requestTableName;
	}

	public void setBusinessDataTableName(String tableName) {
		this.businessDataTableName = tableName;
	}

	public String getBusinessDataTableName() {
		return businessDataTableName;
	}

	public void setOpDummyModes(String modes) {
		this.opDummyModes = modes;
	}

	public String getOpDummyModes() {
		return opDummyModes;
	}

	public void setMaxParameterLength(int length) {
		this.maxParameterLength = length;
	}

	public int getMaxParameterLength() {
		return maxParameterLength;
	}

	public void setMaxMessageLength(int length) {
		this.maxMessageLength = length;
	}

	public int getMaxMessageLength() {
		return maxMessageLength;
	}

	public boolean getModoTramosRetroactivos() {
		return modoTramosRetroactivos;
	}

	public void setModoTramosRetroactivos(boolean modoTramosRetroactivos) {
		this.modoTramosRetroactivos = modoTramosRetroactivos;
	}
}
