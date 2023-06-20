package com.araucana.legacy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;


import cl.araucana.core.registry.Enterprise;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;
import com.araucana.legacy.EmpresaVO;
import com.araucana.legacy.ProxyLDAP;

public class EmpresasUtils {
	private static final Logger log = Logger.getLogger(EmpresasUtils.class);

	private static String appID = "PorEmpAdhe";
	private static String rolID = "PorEmpAdheEnc";

	static public Map<String, String> getEmpresasLDAP(String username) {
		Collection<Enterprise> empresas = ProxyLDAP.getUserEnterprisesAut(username, appID, rolID);
		Map<String, String> listamap = new HashMap<String, String>();

		for (Iterator iterator = empresas.iterator(); iterator.hasNext();) {
			Enterprise enterprise = (Enterprise) iterator.next();
			EmpresaVO empresa = new EmpresaVO();
			empresa.setRutEmpresa(enterprise.getID());
			empresa.setNombreEmpresa(enterprise.getName());
			listamap.put(enterprise.getID(), enterprise.getName());
		}
		TreeMap<String, String> lista_sorted = new TreeMap(listamap);
		return lista_sorted;
	}

	public static Collection getUserEnterprisesAut(String userID, String appID, String rolID) {
		UserRegistryConnection connection = null;
		List salida = new ArrayList();
		try {
			connection = new UserRegistryConnection();
			return connection.getEnterprises(userID, appID, rolID);

		} catch (UserRegistryException e) {
			log.error("getUserEnterprisesAut, error al consultar empresa autorizadas de usuario:" + userID + " y rol:"
					+ rolID + ", mensaje:", e);
			salida.add("Error en consulta");
			return salida;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (UserRegistryException e) {
					log.error(e.getMessage(), e);
				}
			}
		}
	}
}