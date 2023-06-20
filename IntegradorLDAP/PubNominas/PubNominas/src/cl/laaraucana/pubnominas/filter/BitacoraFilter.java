/**
 * 
 */
package cl.laaraucana.pubnominas.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;

import cl.araucana.arsdoc.vo.FilaVO;
import cl.araucana.arsdoc.vo.IndiceVO;
import cl.laaraucana.pubnominas.services.BitacoraService;
import cl.laaraucana.pubnominas.services.BitacoraServiceImpl;

/**
 * @author J-Factory
 *
 */

public class BitacoraFilter implements Filter{
	
	private static final Logger logger = Logger.getLogger(BitacoraFilter.class);
	
	@Autowired
	private BitacoraService bitacora;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
			try {
				HttpServletRequest req = (HttpServletRequest)request;
				HttpServletResponse res = (HttpServletResponse)response;
				String cmd= req.getParameter("cmd");
				HttpSession sesionHttp = req.getSession(false);
				
				IndiceVO indice = (IndiceVO)sesionHttp.getAttribute("indice_filas");
				FilaVO filaGet = indice.getFilas()[java.lang.Integer.parseInt(cmd)];
				String[] columnas= filaGet.getColumnas();
				String folder= (String)sesionHttp.getAttribute("folder");
				if(folder.indexOf("Creditos")>=0){
					folder="CREDITO";
				}else if(folder.indexOf("Leasing")>=0){
					folder="LEASING";
				}else if(folder.indexOf("Anexos")>=0){
					folder="ANEXO";
				}
					
				//Grabando Bitacora
				logger.info("Grabando Bitácora:" + folder + ", Periodo: " + columnas[0] + ", RutEmpresa:" + columnas[1] + ", Oficina:" + columnas[3] + ", Sucursal:" + columnas[4]);
				bitacora.insertBitacora(folder, (String)req.getSession().getAttribute("rutUsuario"), (String)req.getSession().getAttribute("rol"), columnas[0], columnas[1], columnas[3], columnas[4]);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
