package cl.araucana.ctasfam.batch.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cl.araucana.ctasfam.batch.common.util.InformacionGeneralUtil;
import cl.araucana.ctasfam.batch.dao.BasicDb2Connection;
import cl.araucana.ctasfam.batch.thread.AdministradorHebrasThread;
import cl.araucana.ctasfam.batch.thread.ProcesadorPeticionesThread;

public class MonitorProcesamientoServlet extends HttpServlet {

	final static Logger logger = Logger.getLogger(MonitorProcesamientoServlet.class);
	
	public void init() throws ServletException {}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("	<head></head>");
		out.println("<body>");
		
		infoGeneralApp(out);
		if("Ok".equalsIgnoreCase(InformacionGeneralUtil.estadoConfigInternaApp) 
	   			&& "Ok".equalsIgnoreCase(InformacionGeneralUtil.estadoConfigBaseDatoApp)){
			infoGeneralProcess(out);
			infoDetalleHebras(out);
			infoDetalleConexiones(out);
		}
				
		out.println("</body>");
		out.println("<html>");
	}

	public void destroy() {	}
	
	private void infoGeneralApp(PrintWriter out){
		out.println("	<table border='1'>");
		out.println("		<tr>");
		out.println("			<td colspan='2'><b>Informacion General de Aplicacion</b></td>");
		out.println("		</tr>");
		out.println("		<tr>");
		out.println("			<td>Estado </td>");
		out.println("			<td>" + InformacionGeneralUtil.estadoApp + "</td>");
		out.println("		</tr>");
		out.println("		<tr>");
		out.println("			<td>Configuraciones internas</td>");
		out.println("			<td>" + InformacionGeneralUtil.estadoConfigInternaApp + "</td>");
		out.println("		</tr>");
		out.println("		<tr>");
		out.println("			<td>Configuraciones por base de dato</td>");
		out.println("			<td>" + InformacionGeneralUtil.estadoConfigBaseDatoApp + "</td>");
		out.println("		</tr>");
		out.println("	</table>");
	}
	
	private void infoGeneralProcess(PrintWriter out){
		out.println("	<table border='1'>");
		out.println("		<tr>");
		out.println("			<td colspan='2'><b>Informacion General de Proceso</b></td>");
		out.println("		</tr>");
//		out.println("		<tr>");
//		out.println("			<td>Estado de procesamiento</td>");
//		out.println("			<td>" + (("Ok".equalsIgnoreCase(InformacionGeneralUtil.estadoProcessManager))?"Corriendo":"Detenido") + "</td>");
//		out.println("		</tr>");
		out.println("		<tr>");
		out.println("			<td>Proceso en ejecucion</td>");
		out.println("			<td>" + AdministradorHebrasThread.getRunning() + "</td>");
		out.println("		</tr>");
		out.println("		<tr>");
		out.println("			<td>Cantidad de Hebras configuradas</td>");
		int poolHebrasSize = (AdministradorHebrasThread.getPoolHebras()!=null)?AdministradorHebrasThread.getPoolHebras().length:0;
		out.println("			<td>" + poolHebrasSize + "</td>");
		out.println("		</tr>");
		if("Ok".equalsIgnoreCase(InformacionGeneralUtil.estadoProcessManager)){
			Integer qtyThreadsDisponibles = AdministradorHebrasThread.getQtyThreadsDisponibles();
			Integer qtyThreadsOcupadas = poolHebrasSize - qtyThreadsDisponibles;
			
			out.println("		<tr>");
			out.println("			<td>Cantidad de Hebras en ejecucion</td>");
			out.println("			<td>" + qtyThreadsOcupadas + "</td>");
			out.println("		</tr>");
			out.println("		<tr>");
			out.println("			<td>Cantidad de Hebras disponibles</td>");
			out.println("			<td>" + qtyThreadsDisponibles + "</td>");
			out.println("		</tr>");
		}
		out.println("		<tr>");
		out.println("			<td>Cantidad de peticiones en cola </td>");
		out.println("			<td>" + AdministradorHebrasThread.getColaProcesamiento().size() + "</td>");
		out.println("		</tr>");
		out.println("		<tr>");
		out.println("			<td>Cantidad de peticiones fuera de cola </td>");
		out.println("			<td>" + 0 + "</td>");
		out.println("		</tr>");
		out.println("	</table>");
	}
	
	private void infoDetalleHebras(PrintWriter out){
		out.println("	<table border='1'>");
		out.println("		<tr>");
		out.println("			<td colspan='2'><b>Pool de hebras</b></td>");
		out.println("		</tr>");
		out.println("		<tr>");
		out.println("			<td><b>Index hebra</b></td>");
		out.println("			<td><b>Codigo peticion en proceso</b></td>");
		
		out.println("		</tr>");
		
		for(ProcesadorPeticionesThread hebra : AdministradorHebrasThread.getPoolHebras()){
			out.println("		<tr>");
			if(hebra != null){
				out.println("			<td>" + hebra.getIndexThread() + "</td>");
				if(hebra.isAlive()){
					out.println("			<td>" + hebra.getPeticionProcesamiento().getIdPeticionProcesamiento() + "</td>");
				}else{
					out.println("			<td></td>");
				}
			}
			
			out.println("		</tr>");	
		}
		out.println("	</table>");
	}
	
	private void infoDetalleConexiones(PrintWriter out){
		out.println("	<table border='1'>");
		out.println("		<tr>");
		out.println("			<td colspan='2'><b>Pool de conexiones</b></td>");
		out.println("		</tr>");
		out.println("		<tr>");
		out.println("			<td><b>Index conexion</b></td>");
		out.println("			<td><b>Cantidad de veces usada</b></td>");
		out.println("		</tr>");
		
		BasicDb2Connection [] poolConexiones = AdministradorHebrasThread.getPoolConexiones();
		for(int i = 0; i < poolConexiones.length; i++){
			out.println("		<tr>");
			if(poolConexiones[i] != null){
				out.println("			<td>" + i + "</td>");
				out.println("			<td>" + poolConexiones[i].getCountVecesUsada() + "</td>");
			}
			out.println("		</tr>");	
		}
		out.println("	</table>");
	}
}