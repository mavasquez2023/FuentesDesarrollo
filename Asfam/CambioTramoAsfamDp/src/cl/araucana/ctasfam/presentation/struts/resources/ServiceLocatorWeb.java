package cl.araucana.ctasfam.presentation.struts.resources;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.araucana.ctasfam.business.service.PropuestaRentasService;
import cl.araucana.ctasfam.business.service.impl.PropuestaRentasServiceImpl;
import cl.araucana.ctasfam.presentation.struts.vo.AfiliadosPropuesta;
import cl.araucana.ctasfam.presentation.struts.vo.ArchivoPrpuesta;
import cl.araucana.ctasfam.resources.util.ApplicationPropertiesSingleton;
import cl.araucana.ctasfam.resources.util.ExplorerManagerAs400;
import cl.araucana.ctasfam.resources.util.Utils;

public class ServiceLocatorWeb {

	private static final Log log = LogFactory.getLog(ServiceLocatorWeb.class);

	private HttpServletRequest request;

	private PropuestaRentasService propuestaRentasService;

	public ServiceLocatorWeb(HttpServletRequest request) {
		this.request = request;
	}

	public ApplicationPropertiesSingleton getApplicationProperties() {
		String location = this.request.getSession().getServletContext()
				.getInitParameter("environmentPropertiesLocation");
		String path = this.request.getSession().getServletContext()
				.getRealPath("/WEB-INF/conf/" + location);
		return ApplicationPropertiesSingleton.getInstance(path);
	}

	public PropuestaRentasService getPropuestaRentasService() {
		try {
			String datasource = this.getApplicationProperties().getProperty(
					"jndiDataSource");
			this.propuestaRentasService = new PropuestaRentasServiceImpl(
					datasource);
		} catch (Exception e) {
			log.error(
					"Error: al obtener servicios, " + e.getLocalizedMessage(),
					e);
		}
		return this.propuestaRentasService;
	}

	public List obtenerArchivosPropuesta(String rutEmpresa) throws IOException
	{
		List result = new ArrayList();
		ExplorerManagerAs400 archivoAs400 = this.prerareExplorerManager();
		 
		String ruta = getRutaArchivo(rutEmpresa);
		 
		ArchivoPrpuesta txt = new ArchivoPrpuesta(rutEmpresa + ".txt", ruta
				+ ".txt", "txt");
		txt.setArchivoAs400(archivoAs400);
		result.add(txt);
		ArchivoPrpuesta csv = new ArchivoPrpuesta(rutEmpresa + ".CSV", ruta
				+ ".CSV", "CSV");
		csv.setArchivoAs400(archivoAs400);
		result.add(csv);
		ArchivoPrpuesta xls = new ArchivoPrpuesta(rutEmpresa + ".xls", ruta
				+ ".xls", "xls");
		xls.setArchivoAs400(archivoAs400);
		result.add(xls);
		 
		return result;
	}


 	private ExplorerManagerAs400 prerareExplorerManager() {
		String server = this.getApplicationProperties().getProperty(
				"servidorArchivos");
		String username = this.getApplicationProperties().getProperty(
				"servidorArchivos.username");
		String password = this.getApplicationProperties().getProperty(
				"servidorArchivos.password");
		return new ExplorerManagerAs400(server, username, password);
	}
	public boolean empresaTieneArchivosPropuesta(String rutEmpresa) {
		ArchivoPrpuesta temp = new ArchivoPrpuesta(rutEmpresa + ".xsl",
				getRutaArchivo(rutEmpresa) + ".xsl", "xsl");
		temp.setArchivoAs400(this.prerareExplorerManager());
		System.out.println("AS400 Conectado...");
		this.prerareExplorerManager().disconect();
		this.prerareExplorerManager().estatusAS400();
		return temp.getExiste();
	}

	public String getRutaArchivo(String rutEmpresa) {
		String documentBaseDir = this.getApplicationProperties().getProperty(
				"documentBaseDir");
		String separadorCarpetas = this.getApplicationProperties().getProperty(
				"separadorCarpetas");
		String ruta = documentBaseDir + separadorCarpetas + rutEmpresa
				+ separadorCarpetas + rutEmpresa;
		return ruta;
	}

	public String prepareDescargaPropuesta(String rutEmpresa, String fileFormat) {
		return this.request.getContextPath() + "/"  
				+ "descargaArchivos.do?&rutEmpresa=" + rutEmpresa
				+ "&formato=" + fileFormat;
	}
	
	public StringBuffer prepareSaldoAfiliados(List saldos) throws Exception {
		if(saldos == null)
			return null;
		StringBuffer result = new StringBuffer();
		for (Iterator iter = saldos.iterator(); iter.hasNext();) {
			AfiliadosPropuesta infoAfiliado = (AfiliadosPropuesta) iter.next();
			result.append(this.getApplicationProperties().getProperty("periodo") + ";");
			result.append(infoAfiliado.getOficina() + ";");
			result.append(infoAfiliado.getSucursal() + ";");
			result.append(infoAfiliado.getRutEmpresa() + ";");
			result.append(infoAfiliado.getDvRutEmpresa() + ";");
			result.append(infoAfiliado.getRutAfiliado() + ";");
			result.append(infoAfiliado.getDvRutAfiliado() + ";");
			String[] nombreCompleto = infoAfiliado.getNombreAfiliado().split(" ");
			result.append(nombreCompleto[0] + ";");
			result.append(nombreCompleto[1] + ";");
			result.append(nombreCompleto[2] + ";");
			result.append(infoAfiliado.getRemuneracionEmpleador() + ";");
			result.append(infoAfiliado.getRemuneracionOtroEmpleador() + ";");
			result.append(infoAfiliado.getRemuneracionIndependiente() + ";");
			result.append(infoAfiliado.getRentaSubsidio() + ";");
			result.append(infoAfiliado.getRentaPensiones() + ";");
			result.append(infoAfiliado.getTotalIngresos() + ";");
			result.append(infoAfiliado.getNumeroMeses() + ";");
			result.append(infoAfiliado.getIngresoPromedio() + ";");
			result.append(infoAfiliado.getDeclaracion() + ";");
			result.append(infoAfiliado.getTramo() + ";");
			result.append(infoAfiliado.getValorTramo() + ";");
			result.append("\n");
		}
		return result;
	}
	
	public String prepareSaldoAfiliadosHead() {
		StringBuffer sb = new StringBuffer();
		sb.append("PERIODO;");
		sb.append("OFICINA;");
		sb.append("SUCURSAL;");
		sb.append("RUTEMPRESA;");
		sb.append("DVEMPRESA;");
		sb.append("RUTAFILIAD;");
		sb.append("DVAFILIADO;");
		sb.append("APELLIDOPA;");
		sb.append("APELLIDOMA;");
		sb.append("NOMBREAFIL;");
		sb.append("REMUNERAC;");
		sb.append("OTRASREMU;");
		sb.append("RENTATRAN;");
		sb.append("SUBSIDIOS;");
		sb.append("PENSIONES;");
		sb.append("TOTALINGR;");
		sb.append("NROMESES;");
		sb.append("PROMMENS;");
		sb.append("DECLARAC;");
		sb.append("CODTRAMO;");
		sb.append("VALTRAMO");
		sb.append("\n");
		return sb.toString();
	}
}
