/**
 * 
 */
package cl.araucana.cotcarserv.servlets;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import cl.araucana.cotcarserv.dao.VO.InformeSAPVO;
import cl.araucana.cotcarserv.main.dao.ConsultaServicesDAO;
import cl.araucana.cotcarserv.utils.CertificadoConst;
import cl.laaraucana.satelites.Utils.FTPUtils;
import cl.laaraucana.satelites.Utils.GeneratorXLS;



/**
 * @author IBM Software Factory
 *
 */
public class ArchivoSAP extends HttpServlet{
	protected Logger logger = Logger.getLogger(this.getClass());
	
	public boolean enviarArchivo(){
		
		try {
			ConsultaServicesDAO bitaDAO= new ConsultaServicesDAO();
			logger.info("Consultando registros envío SAP");

			List<InformeSAPVO> listaSAP= bitaDAO.generaArchivoSAP();
			
			if(listaSAP.size()>0){
				String pathfile= CertificadoConst.RES_CERTIFICADOS.getString("was.ruta.ftp.sap");
				logger.info("Se generará salida en: " + pathfile);

				OutputStream out = new FileOutputStream(pathfile);
				PrintStream flujo= new PrintStream(out);
				GeneratorXLS xls= new GeneratorXLS(flujo);

				//Configurando columnas a desplegar y titulos de estas.
				String[] columnas={"rutEmpresa", "oficina", "sucursal", "anexo", "rutTrabajador", "fechaDesvinculacion", "motivo", "caja"};
				//String[] titulos={"Rut Empresa", "Oficina", "Suc. Caja", "Anexo", "Rut Trabajador", "Fecha Desvinculacion", "Motivo", "Caja"};

				xls.generarCSVfromCollection(listaSAP, columnas, null);
				logger.info("Archivo " + pathfile + " ha sido generado.");
				//Cerrando salida
				out.flush();
				out.close();

				//Enviando archivo por FTP
				FTPUtils.enviarArchivo(pathfile);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(enviarArchivo()){
			response.getWriter().print("Archivo Generado");
		}else{
			response.getWriter().print("Archivo No Generado");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
