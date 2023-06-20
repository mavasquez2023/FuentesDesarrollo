package cl.laaraucana.sms.utils;

import java.net.InetSocketAddress;
import java.net.Proxy;

import org.apache.log4j.Logger;

public class Networking {
	private static final Logger logger = Logger.getLogger(Networking.class);

	private boolean proxyEnabled;
	private String proxyAddress;
	private int proxyPort;
	Proxy proxy;

	public Networking() {
		try {
			proxyEnabled = Configuration.getProperty("proxy.enabled").equals("true");
			proxyAddress = Configuration.getProperty("proxy.address");
			proxyPort = Integer.parseInt(Configuration.getProperty("proxy.port"));
			proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyAddress, proxyPort));
		} catch (Exception e) {
			logger.error("Error setting Networking properties for Proxy", e);
		}
	}

	public boolean isProxyEnabled() {
		return proxyEnabled;
	}

	public void setProxyEnabled(boolean proxyEnabled) {
		this.proxyEnabled = proxyEnabled;
	}

	public String getProxyAddress() {
		return proxyAddress;
	}

	public void setProxyAddress(String proxyAddress) {
		this.proxyAddress = proxyAddress;
	}

	public int getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}

	public Proxy getProxy() {
		return proxy;
	}

	public void setProxy(Proxy proxy) {
		this.proxy = proxy;
	}

}
