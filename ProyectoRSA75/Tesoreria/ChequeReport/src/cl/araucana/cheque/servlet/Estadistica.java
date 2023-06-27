/**
 * 
 */
package cl.araucana.cheque.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cl.araucana.cheque.dao.EstadisticasDAO;
import cl.araucana.cheque.dao.TesoDAO;
import cl.araucana.cheque.to.EstadisticasTO;
/**
 * @author usist199
 *
 */
public class Estadistica extends HttpServlet {

	private static Logger log = Logger.getLogger(Estadistica.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		TesoDAO dao=null;
		String forward="";
		try{
			HttpSession session = request.getSession();
			//Rescatando parametros de url
			String mes= request.getParameter("mes");
			String fecha= request.getParameter("fecha");
			String estado= request.getParameter("estado");
			String oficina= request.getParameter("oficina");
			String usuario= request.getParameter("usuario");
			String action= request.getParameter("action");
			String folio= request.getParameter("folio");
			EstadisticasTO param= new EstadisticasTO();
			if(mes==null){
				mes="0";
			}
			param.setMes(Integer.parseInt(mes));
			param.setFecha(fecha);
			param.setOficina(oficina);
			param.setEstado(estado);
			param.setUsuario(usuario);
			if(folio==null){
				folio="0";
			}
			param.setFolio(Integer.parseInt(folio));
			String rutaout="";
			String hide="";
			
			dao= new TesoDAO();
			EstadisticasDAO estadisdao= new EstadisticasDAO(dao.getConnection());
			List resultado=null;
			String filtro="";
			String order="desc";

			if((mes.equals("0"))&&fecha==null&&oficina==null&&action==null){
				//pantalla inicial
				resultado= (List)estadisdao.selectCountbyMes();
				forward="/estadistica_mes.jsp";
			}else{
				//log.info("Action=" + action);
				if(action.equals("FOL")){
					forward="/estadistica_detalle.jsp";
					rutaout=getRutaOut("Folio "+ String.valueOf(folio), null, null);
					resultado= (List)estadisdao.selectDetalleCheques(param);
				}else if(action.equals("DET")){
					forward="/estadistica_detalle.jsp";
					rutaout=getRutaOut(mes, fecha, oficina);
					resultado= (List)estadisdao.selectDetalleCheques(param);
				}else{
					if(action.equals("FEC")){
						forward="/estadistica_fecha.jsp";
						filtro="fecha_creacion";
						rutaout=getRutaOut(mes, null, oficina);
					}else if(action.equals("OFI")){
						forward="/estadistica_ofi.jsp";
						filtro="oficina";
						order="asc";
						rutaout=getRutaOut(mes, fecha, null);
					}else if(action.equals("USU")){
						forward="/estadistica_usu.jsp";
						filtro="usuario";
						order="asc";
						rutaout=getRutaOut(mes, fecha, oficina);
					}
					if(param.getFecha()!= null || param.getOficina()!=null){
						hide="1";
					}
					resultado= (List)estadisdao.selectCount(param, filtro, order);
				}
			}
			
			request.setAttribute("ruta", rutaout);
			request.setAttribute("hidecol", hide);
			request.setAttribute("estadisticas", resultado);
			request.setAttribute("estado", estado);
			request.getRequestDispatcher(forward).forward(request,response);
		}catch (Exception e) {
				e.printStackTrace();
				log.error("Mensaje=" + e.getMessage());
		}
		finally{
		if(dao!=null){
			dao.closeConnectionDAO();
		}
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request, response);
	}
	
	public String getRutaOut(String periodo, String fecha, String oficina){
		String salida="";
		if(!periodo.equals("0")){
			salida+= periodo+"/";
		}
		if(fecha!=null){
			salida+= fecha+"/";
		}
		if(oficina!=null){
			salida+= oficina+"/";
		}
		return salida;
	}
}
