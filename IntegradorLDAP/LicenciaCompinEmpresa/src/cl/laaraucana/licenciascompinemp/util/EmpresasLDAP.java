/**
 * 
 */
package cl.laaraucana.licenciascompinemp.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import cl.araucana.core.registry.Enterprise;
import cl.laaraucana.licenciascompinemp.vo.EmpresaVO;


/**
 * @author IBM Software Factory
 *
 */
public class EmpresasLDAP {
	private static Logger logger = Logger.getLogger("EmpresasLDAP");
	private static String appID= "PorEmpAdhe";
	private static String rolID= "PorEmpAdheEnc";
	
	static public Map<String, String> getEmpresasLDAP(String username){
		
		Collection<Enterprise> empresas= ProxyLDAP.getUserEnterprisesAut(username, appID, rolID);
		Map<String, String> listamap= new HashMap<String, String>();
		
		for (Iterator iterator = empresas.iterator(); iterator.hasNext();) {
			Enterprise enterprise = (Enterprise) iterator.next();
			EmpresaVO empresa= new EmpresaVO();
			empresa.setRutEmpresa(enterprise.getID());
			empresa.setNombreEmpresa(enterprise.getName());
			listamap.put(enterprise.getID(), enterprise.getName());
		}
		TreeMap<String, String> lista_sorted= new TreeMap(listamap);
		return lista_sorted;
	}
	
	
}
