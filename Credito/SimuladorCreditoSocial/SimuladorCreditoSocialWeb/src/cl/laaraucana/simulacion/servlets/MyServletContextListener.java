/**
 * 
 */
package cl.laaraucana.simulacion.servlets;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cl.laaraucana.simulacion.ibatis.dao.CuotasDAO;
import cl.laaraucana.simulacion.ibatis.dao.SucursalesDAO;
import cl.laaraucana.simulacion.ibatis.dao.UtilesDAO;
import cl.laaraucana.simulacion.ibatis.vo.ParametroVO;
import cl.laaraucana.simulacion.ibatis.vo.ParamsSingleton;
import cl.laaraucana.simulacion.ibatis.vo.SucursalesVO;

/**
 * @author IBM Software Factory
 *
 */
public class MyServletContextListener implements ServletContextListener {
	//variable para solicitar instancia de singleton Parametros
	private ParamsSingleton param;

	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
		ServletContext sc= event.getServletContext();
		try {
			//Se setean sucursales
			SucursalesDAO sucursalesDao = new SucursalesDAO();
			List<SucursalesVO> listaSucursales=sucursalesDao.consultaSucursales();
			param.getInstance().setSucursales(listaSucursales);
			
			//se genera mapa de sucursales
			Map<String, SucursalesVO> mapSucursales= new HashMap<String, SucursalesVO>();
			for (Iterator iterator = listaSucursales.iterator(); iterator
					.hasNext();) {
				SucursalesVO sucursalVO = (SucursalesVO) iterator.next();
				mapSucursales.put(sucursalVO.getCodigo(), sucursalVO);
			}
			param.getInstance().setMapSucursales(mapSucursales);
			
			//Se setean cuotas
			CuotasDAO cuotasDao = new CuotasDAO();
			param.getInstance().setCuotas(cuotasDao.consultaCuotas());
        	
			//se setea texto condiciones
			ParametroVO parametroVO = new ParametroVO();
			parametroVO = UtilesDAO.consultaParametro(1, 3);
			param.getInstance().setParametrosCondiciones(parametroVO.getValor());
			
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
