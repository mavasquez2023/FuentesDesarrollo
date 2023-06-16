package cl.araucana.ctasfam.presentation.struts.actions;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import cl.araucana.ctasfam.business.to.FlujoTO;
import cl.araucana.ctasfam.integration.jdbc.dao.AraucanaJdbcDao;
import cl.araucana.ctasfam.presentation.struts.resources.ServiceLocatorWeb;
import cl.araucana.ctasfam.presentation.struts.vo.Empresa;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;
import cl.araucana.ctasfam.resources.util.Padder;

public class DescargaPropuestaRentasZipAction extends DispatchAction {

	private static final Log log = LogFactory
			.getLog(DescargaPropuestaRentasZipAction.class);

 
	
	public ActionForward descargaPropuestaZip(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Properties Config = new Properties();
		Config.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		  String periodo=Config.getProperty("PERIODO");
		  FlujoTO flujo=new FlujoTO();
		  AraucanaJdbcDao dao= new AraucanaJdbcDao();
		try {
			ServiceLocatorWeb service = new ServiceLocatorWeb(request);
			HttpSession session = request.getSession();
			
			Encargado encargado = (Encargado) session.getAttribute("edocs_encargado");
			Collection enterprises = encargado.getEmpresas();
			String[] source = new String[(encargado.getEmpresas().size() + 1) * 3];
			int k = 0, p = 0;
			for (Iterator iter = enterprises.iterator(); iter.hasNext();) {
				Empresa empresa = (Empresa) iter.next();
				String rutEmpresa = "" + empresa.getRut();
				rutEmpresa = Padder.pad(rutEmpresa, 8, '0', true);
				
				 flujo.setRutempresa(rutEmpresa);
				  flujo.setPeriodo(periodo);
				  flujo.setEtapa("1");
				  flujo.setISAJKM94("CTADMIN");
				  flujo.setISAJKM92("");
				  flujo.setOperacion("DESCARGA ARCHIVO");
				  flujo.setNombrearchivo("");
				  flujo.setRutencargado(String.valueOf(encargado.getRut()));
				  
				  dao.InsertaBitacora(flujo);		  
				  dao.updateFlujo(flujo);
				
				 
				if (empresa.getFlag() != 0 && getOption(request, "BONO", ++p)) {
					source[++k] =  service.getRutaArchivo(rutEmpresa) + ".txt";
					System.out.println(service.getRutaArchivo(rutEmpresa) + ".txt");
					source[++k] = service.getRutaArchivo(rutEmpresa) + ".CSV";
					System.out.println(service.getRutaArchivo(rutEmpresa) + ".CSV");
					source[++k] = service.getRutaArchivo(rutEmpresa) + ".xls";
					System.out.println(service.getRutaArchivo(rutEmpresa) + ".xls");
				}
			}

			 
			
			int zipBufferSize = Integer.parseInt(service
					.getApplicationProperties().getProperty(
							"initialZipBufferSize"));
			ByteArrayOutputStream bufferedOutput = new ByteArrayOutputStream(
					zipBufferSize);
			byte[] buf = new byte[zipBufferSize];
			ZipOutputStream out = new ZipOutputStream(bufferedOutput);
			for (int i = 0; i < source.length; i++) {
				if (source[i] != null) {
					try {
						FileInputStream in = new FileInputStream(source[i]);
						System.out.println(source[i]);
						String temp = source[i].substring(6);
						out.putNextEntry(new ZipEntry(temp));
						int len;
						while ((len = in.read(buf)) > 0) {
							out.write(buf, 0, len);
						}
						out.closeEntry();
						in.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
						log.error("Horror: " + e.getLocalizedMessage(), e);
					}
				}
			}
			out.close();
			response.setContentType("application/zip");
			response.setHeader("Content-Disposition", "inline; filename="
					+ service.getApplicationProperties().getProperty(
							"zippedDocPrefix") + "_" + encargado.getRut()
					+ ".zip");
			response.setContentLength(bufferedOutput.size());
			OutputStream output = response.getOutputStream();
			bufferedOutput.writeTo(output);
			output.close();
			bufferedOutput.close();
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
			log.error("Horror: " + e.getLocalizedMessage(), e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Horror: " + e.getLocalizedMessage(), e);
		}
		return null;
	}

	private boolean getOption(HttpServletRequest request, String optionName,
			int index) {
		String optionValue = request.getParameter(optionName + index);
		return optionValue != null && optionValue.equals("ALL");
	}
}