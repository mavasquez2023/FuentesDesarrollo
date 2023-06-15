/**
 * 
 */
package cl.laaraucana.simulacion.ibatis.vo;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author IBM Software Factory
 *
 */
public class ParamsSingleton {
		private static ParamsSingleton instance = new ParamsSingleton();

		List<SucursalesVO> sucursales;
		List<CuotasVO> cuotas;
		String parametrosCondiciones;
		Map<String, SucursalesVO> mapSucursales;
		
		public static ParamsSingleton getInstance(){
				return instance;
		}

		public ParamsSingleton(){
		}

		/**
		 * @return the sucursales
		 */
		public List<SucursalesVO> getSucursales() {
			return sucursales;
		}

		/**
		 * @param sucursales the sucursales to set
		 */
		public void setSucursales(List<SucursalesVO> sucursales) {
			this.sucursales = sucursales;
		}

		/**
		 * @return the cuotas
		 */
		public List<CuotasVO> getCuotas() {
			return cuotas;
		}

		/**
		 * @param cuotas the cuotas to set
		 */
		public void setCuotas(List<CuotasVO> cuotas) {
			this.cuotas = cuotas;
		}

		/**
		 * @return the parametrosCondiciones
		 */
		public String getParametrosCondiciones() {
			return parametrosCondiciones;
		}

		/**
		 * @param parametrosCondiciones the parametrosCondiciones to set
		 */
		public void setParametrosCondiciones(String parametrosCondiciones) {
			this.parametrosCondiciones = parametrosCondiciones;
		}

		/**
		 * @return the mapSucursales
		 */
		public Map<String, SucursalesVO> getMapSucursales() {
			return mapSucursales;
		}

		/**
		 * @param mapSucursales the mapSucursales to set
		 */
		public void setMapSucursales(Map<String, SucursalesVO> mapSucursales) {
			this.mapSucursales = mapSucursales;
		}
		
		

}
