package cl.araucana.wssiagf.vo;

import java.io.IOException;
import java.util.Properties;

import cl.araucana.wssiagf.WSSIAGFException;

public class BusinessLogicConfig {

	private static Properties properties;
	private String DSJNDIName;
	private String tramosHistoricos;
	private String tramosHistoricosAfiliado;
	private String rentaHistoricaAfiliado;
	private String rentaHistoricaAfiliado2;
	private long valorMaximoRenta;
	private int maxTramosRetroactivos;
	private int numTramoDefault;

	public BusinessLogicConfig(String propertyFile) throws WSSIAGFException {

		Properties proper = new Properties();
		try {
			proper.load(getClass().getClassLoader().getResourceAsStream(propertyFile));
		} catch (IOException e1) {
			// TODO Bloque catch generado automáticamente
			e1.printStackTrace();
		}
		DSJNDIName = proper.getProperty("logic.jdbc");
		tramosHistoricos = proper.getProperty("logic.db2.tramosHistoricos");
		tramosHistoricosAfiliado = proper.getProperty("logic.db2.tramosHistoricosAfiliado");
		rentaHistoricaAfiliado = proper.getProperty("logic.db2.rentaHistoricaAfiliado");
		rentaHistoricaAfiliado2 = proper.getProperty("logic.db2.rentaHistoricaAfiliado2");
		valorMaximoRenta = Long.parseLong(proper.getProperty("logic.valorMaximo"));
		maxTramosRetroactivos = Integer.parseInt(proper.getProperty("logic.maxTramosRetroactivos"));
		numTramoDefault = Integer.parseInt(proper.getProperty("logic.numTramoDefault"));

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
