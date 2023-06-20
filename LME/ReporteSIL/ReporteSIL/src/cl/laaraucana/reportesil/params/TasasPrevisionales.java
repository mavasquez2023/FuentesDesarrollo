/**
 * 
 */
package cl.laaraucana.reportesil.params;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cl.laaraucana.reportesil.dao.vo.ImpuestoVO;
import cl.laaraucana.reportesil.dao.vo.TasaSISVO;

/**
 * @author IBM Software Factory
 *
 */
public class TasasPrevisionales {
		private static TasasPrevisionales instance = new TasasPrevisionales();
		Map<String, Double> tasas=null;
		List<TasaSISVO> tasasSIS;
		Map<String, ImpuestoVO> impuestos;
		double montoDiarioMinimo;
		
		public static TasasPrevisionales getInstance(){
				return instance;
		}

		public TasasPrevisionales(){
		}

		/**
		 * @return the tasa
		 */
		public Map<String, Double> getTasas() {
			return tasas;
		}

		/**
		 * @param tasa the tasa to set
		 */
		public void setTasas(Map<String, Double> tasas) {
			this.tasas = tasas;
		}
		
		
		/**
		 * @return the tasasSIS
		 */
		public List<TasaSISVO> getTasasSIS() {
			return tasasSIS;
		}

		/**
		 * @param tasasSIS the tasasSIS to set
		 */
		public void setTasasSIS(List<TasaSISVO> tasasSIS) {
			this.tasasSIS = tasasSIS;
		}


		/**
		 * @return the impuestos
		 */
		public Map<String, ImpuestoVO> getImpuestos() {
			return impuestos;
		}

		/**
		 * @param impuestos the impuestos to set
		 */
		public void setImpuestos(Map<String, ImpuestoVO> impuestos) {
			this.impuestos = impuestos;
		}

		/**
		 * @return the montoDiarioMinimo
		 */
		public double getMontoDiarioMinimo() {
			return montoDiarioMinimo;
		}

		/**
		 * @param montoDiarioMinimo the montoDiarioMinimo to set
		 */
		public void setMontoDiarioMinimo(double montoDiarioMinimo) {
			this.montoDiarioMinimo = montoDiarioMinimo;
		}

		


}
