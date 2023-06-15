/**
 * 
 */
package cl.araucana.wsafiliado.mgr;

import java.util.HashMap;
import java.util.Map;

import cl.araucana.wsafiliado.to.SistemaVO;
import cl.araucana.wsafiliado.util.Configuraciones;



/**
 * @author IBM Software Factory
 *
 */
public class SistemasSingleton {

	private static SistemasSingleton instance = null;
	private Map<Integer, SistemaVO> listaSistemas= new HashMap<Integer, SistemaVO>();
	private Map<Integer, SistemaVO> listaSistemasGDA= new HashMap<Integer, SistemaVO>();
	protected SistemasSingleton(){
		String prioridadServicio;
		String sistemaServicio;
		String estadoServicio;
		//Para método GEA
		int i=1;
		prioridadServicio= Configuraciones.getConfig("sistema."+ i + ".prioridad");
		do {
			sistemaServicio= Configuraciones.getConfig("sistema."+ i + ".nombre");
			estadoServicio= Configuraciones.getConfig("sistema."+ i + ".estado");
			SistemaVO sistemaVO= new SistemaVO();
			sistemaVO.setPrioridad(Integer.parseInt(prioridadServicio));
			sistemaVO.setSistema(sistemaServicio);
			sistemaVO.setEstado(Integer.parseInt(estadoServicio));
			this.listaSistemas.put(Integer.parseInt(prioridadServicio), sistemaVO);
			i++;
			try {
				prioridadServicio= Configuraciones.getConfig("sistema."+ i + ".prioridad");
			} catch (Exception e) {
				prioridadServicio=null;
			}
		} while (prioridadServicio!=null);
		//Para método GDA
		int j=1;
		prioridadServicio= Configuraciones.getConfig("sistemaGDA."+ j + ".prioridad");
		do {
			sistemaServicio= Configuraciones.getConfig("sistemaGDA."+ j + ".nombre");
			estadoServicio= Configuraciones.getConfig("sistemaGDA."+ j + ".estado");
			SistemaVO sistemaVO= new SistemaVO();
			sistemaVO.setPrioridad(Integer.parseInt(prioridadServicio));
			sistemaVO.setSistema(sistemaServicio);
			sistemaVO.setEstado(Integer.parseInt(estadoServicio));
			this.listaSistemasGDA.put(Integer.parseInt(prioridadServicio), sistemaVO);
			j++;
			try {
				prioridadServicio= Configuraciones.getConfig("sistemaGDA."+ j + ".prioridad");
			} catch (Exception e) {
				prioridadServicio=null;
			}
		} while (prioridadServicio!=null);
	}

	/**
	 * @return the instance
	 */
	public static SistemasSingleton getInstance() {
		 if(instance == null) {
			 instance = new SistemasSingleton();
		 }
		return instance;
	}


	/**
	 * @return the listaSistemas
	 */
	public Map<Integer, SistemaVO> getListaSistemas() {
		return listaSistemas;
	}

	/**
	 * @return the listaSistemasGDA
	 */
	public Map<Integer, SistemaVO> getListaSistemasGDA() {
		return listaSistemasGDA;
	}
	
}
