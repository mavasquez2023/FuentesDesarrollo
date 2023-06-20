/**
 * 
 */
package cl.laaraucana.reportesil.servlets;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cl.laaraucana.reportesil.dao.ConsultaServicesDAO;
import cl.laaraucana.reportesil.dao.vo.ImpuestoVO;
import cl.laaraucana.reportesil.dao.vo.PeriodosRentaVO;
import cl.laaraucana.reportesil.dao.vo.TasaPrevisionalVO;
import cl.laaraucana.reportesil.dao.vo.TasaSISVO;
import cl.laaraucana.reportesil.params.TasasPrevisionales;
import cl.laaraucana.reportesil.utils.Configuraciones;
import cl.laaraucana.reportesil.utils.Utils;

/**
 * @author IBM Software Factory
 *
 */
public class MyServletContextListener implements ServletContextListener {
	//variable para solicitar instancia de singleton Parametros
	private TasasPrevisionales param;

	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
		ServletContext sc= event.getServletContext();
		try {
			//Se busca las tasa previsionales por periodo para cada entidad
			ConsultaServicesDAO dao= new ConsultaServicesDAO();
			Map<String , Integer> param= new HashMap<String, Integer>();
			int meses_historico= Integer.parseInt(Configuraciones.getConfig("num.meses.tasas.previsionales"));
			String periodo_ini= Utils.obtenerPeriodoCualquiera(meses_historico * -1);
			String periodo_fin= Utils.getFechaPeriodo();
			
			param.put("periodo_ini", Integer.parseInt(periodo_ini.substring(2)));
			param.put("periodo_fin", Integer.parseInt(periodo_fin.substring(2)));
			List<TasaPrevisionalVO> tasas= dao.tasasPrevisonales(param);
			
			//Se arma un mapa de las tasas
			Map<String, Double> maptasas= new HashMap<String, Double>();
			for (Iterator iterator = tasas.iterator(); iterator.hasNext();) {
				TasaPrevisionalVO tasaVO = (TasaPrevisionalVO) iterator
						.next();
				maptasas.put(tasaVO.getPeriodo() + tasaVO.getEntidad(), tasaVO.getTasa());
			}
			//Se carga clase singleton
	    	TasasPrevisionales.getInstance().setTasas(maptasas);
			
	    	//Se busca las tasas sis por rango periodos
	    	List<TasaSISVO> tasasSIS=  dao.tasasSIS();
	    	TasasPrevisionales.getInstance().setTasasSIS(tasasSIS);
	    	
	    	//Se busca los impuestos por año
	    	param.put("periodo_ini", Integer.parseInt(periodo_ini));
			param.put("periodo_fin", Integer.parseInt(periodo_fin));
			
	    	List<ImpuestoVO>  impuestos= dao.impuestoRenta(param);
	    	Map<String, ImpuestoVO> mapImpuestos= new HashMap<String, ImpuestoVO>();
	    	for (Iterator iterator = impuestos.iterator(); iterator.hasNext();) {
				ImpuestoVO impuestoVO = (ImpuestoVO) iterator.next();
				mapImpuestos.put(impuestoVO.getPeriodo(), impuestoVO);
			}
	    	TasasPrevisionales.getInstance().setImpuestos(mapImpuestos);
	    	
	    	//Se busca el Monto Diario Minimo
	    	double montoDiarioMinimo= dao.montoDiarioMinimo(param);
	    	TasasPrevisionales.getInstance().setMontoDiarioMinimo(montoDiarioMinimo);
		} catch (Exception e) {

			e.printStackTrace();
		}
    	
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub

	}

}
