package cl.araucana.cp.web.menu;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

public class Menus 
{
	private static final Logger logger = Logger.getLogger(Menus.class);
	private Modulo moduloActivo;

	private List modulos;

	public Menus(List modulos, boolean administrador) 
	{
		this.modulos = modulos;
		logger.debug("lista modulos");
		for (Iterator it = this.modulos.iterator(); it.hasNext();)
		{
			Modulo modulo = (Modulo) it.next();
			logger.debug("modulo:" + modulo.getNombre() + ":" + modulo.getUrl() + ":--->"+administrador);
			if(modulo.getNombre().equalsIgnoreCase("Administración"))
			{
				List tabs=modulo.getTabs();
				Iterator t=tabs.iterator();
				while(t.hasNext())
				{
					Tab tab=(Tab)t.next();
					if(tab.getNombre().equalsIgnoreCase("usuarios"))
					{
						if(!administrador)
						{
							tab.setVisible("no");
							logger.debug("BORRADO");
						} else
							tab.setVisible("si");
					}
				}
			}
			
		}
	}

	private Modulo buscarModulo(String accion) 
	{
		for (Iterator it = this.modulos.iterator(); it.hasNext(); ) 
		{
			Modulo modulo = (Modulo) it.next();
			if (modulo.getAccion().equals(accion)) 
				return modulo;
		}
		logger.warn("Modulo " + accion + " no encontrado");
		return null;
	}
	
	public Tab buscarTab(String accionModulo, String subAccion) 
	{
		this.moduloActivo = buscarModulo(accionModulo);
		if (this.moduloActivo == null) 
			return null;
		for (Iterator it = this.moduloActivo.getTabs().iterator(); it.hasNext(); ) 
		{
			Tab tab = (Tab) it.next();
			if (tab.getSubAccion().equals(subAccion)) 
				return tab;
		}
		logger.warn("Tab " + subAccion + " no encontrado");
		return null;
	}

	public List getModulos() 
	{
		return this.modulos;
	}
}

