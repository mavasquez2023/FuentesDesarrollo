package cl.araucana.cp.web.menu;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.digester.Digester;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

public class MenusConfig 
{
	public static final String KEY_MENUS_CONFIG = "KEY_MENUS_CONFIG";
	private static final Logger logger = Logger.getLogger(MenusConfig.class);
	private List modulos;

	public MenusConfig(String path) throws IOException, SAXException 
	{
		parse(path);
	}

	private void parse(String path) throws IOException, SAXException 
	{
		// Inicializo el parser.
		Digester digester = new Digester();
		digester.setValidating(false);

		digester.setUseContextClassLoader(true); // Para evitar problemas de classnotfoundexception en Websphere
		
		// Agrego al stack el Arraylist donde voy guardando cada 'modulo'.
		this.modulos = new ArrayList(); 
		digester.push(this.modulos);

		digester.addObjectCreate("menus-config/modulo", Modulo.class);
		digester.addSetProperties("menus-config/modulo", "nombre", "nombre");
		digester.addSetProperties("menus-config/modulo", "url", "url");
		digester.addSetProperties("menus-config/modulo", "accion", "accion");
		digester.addSetProperties("menus-config/modulo", "roles", "rolesPermitidos");
		
		digester.addObjectCreate("menus-config/modulo/tab", Tab.class);
		digester.addSetProperties("menus-config/modulo/tab", "nombre", "nombre");
		digester.addSetProperties("menus-config/modulo/tab", "url", "url");
		digester.addSetProperties("menus-config/modulo/tab", "subAccion", "subAccion");
		digester.addSetProperties("menus-config/modulo/tab", "activar", "activar");
		digester.addSetProperties("menus-config/modulo/tab", "onclick", "onclick");
		digester.addSetProperties("menus-config/modulo/tab", "roles", "rolesPermitidos");
		
		digester.addObjectCreate("menus-config/modulo/tab/subTab", SubTab.class);
		digester.addSetProperties("menus-config/modulo/tab/subTab", "nombre", "nombre");
		digester.addSetProperties("menus-config/modulo/tab/subTab", "subSubAccion", "subSubAccion");

		digester.addSetNext("menus-config/modulo/tab/subTab",  "addSubTab");
		digester.addSetNext("menus-config/modulo/tab",  "addTab");
		digester.addSetNext("menus-config/modulo",  "add");

		// Finalmente llamo al parser...
		InputStream input = new FileInputStream(path);
		digester.parse(input);

		logger.debug("***EL ARBOL DE MENUS***");
		Modulo mod;
		Tab tab;
		for (Iterator it = this.modulos.iterator(); it.hasNext();)
		{
			mod = (Modulo) it.next();
			logger.debug("MODULO: \"" + mod.getNombre() + "\", roles: " + mod.getRolesPermitidos());
			for (Iterator itp = mod.getTabs().iterator(); itp.hasNext();)
			{
				tab = (Tab) itp.next();
				logger.debug("\tTAB: \"" + tab.getNombre() + "\", roles: " + tab.getRolesPermitidos());
			}
		}
	}

	/**
	 * Recupera el listado de modulos configurados.
	 * @return this.Un listado de objetos de tipo Modulo.
	 */
	public List getModulos() 
	{
		return this.modulos;
	}
}
