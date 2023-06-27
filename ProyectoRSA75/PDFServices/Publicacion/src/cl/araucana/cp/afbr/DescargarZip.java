package cl.araucana.cp.afbr;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.jar.JarException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utilMonth.Zippeo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import cl.araucana.cp.afbr.business.ParametrosReporteBean;
import cl.araucana.cp.afbr.dao.AfbrDAO;
import cl.araucana.cp.afbr.servicios.Servicios;
import cl.araucana.cp.hibernate.beans.MesesbeanVO;

public class DescargarZip extends HttpServlet {

	private static final long serialVersionUID = 5272007738080664700L;

	private static String rutaZip = null;

	private static String rutaJasper = null;

	private static String rutaBarra = null;

	public void init() throws ServletException {
		getServletContext().log("getinit init");

		rutaZip = getServletConfig().getInitParameter("rutaZipAfbr");
		rutaJasper = getServletConfig().getInitParameter("rutaJasperAfbr");
		rutaBarra = getServletConfig().getInitParameter("rutaBarra");

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		MesesbeanVO omeses = new MesesbeanVO();

		try {

			String rutEmpresa = request.getParameter("RutEmpresa");
			String convenio = request.getParameter("Convenio");
			String tipoProceso = request.getParameter("TipoProceso");
			String desde = request.getParameter("fechaProceso");
			String hasta = request.getParameter("FechaProceso2");
			String holding = request.getParameter("holdingA");
			String pathJrxml = rutaJasper + "/Afbr.jrxml";

			omeses.setFecha1(desde);
			omeses.setFecha2(hasta);
			omeses.setRutEmpresa(rutEmpresa);
			omeses.setConvenio(convenio);
			omeses.setTipoProceso(tipoProceso);
			omeses.setHolding(holding);

			Servicios.descargarZip(omeses, response, rutaZip, convenio,
					rutEmpresa, rutaBarra, pathJrxml, rutaJasper);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
