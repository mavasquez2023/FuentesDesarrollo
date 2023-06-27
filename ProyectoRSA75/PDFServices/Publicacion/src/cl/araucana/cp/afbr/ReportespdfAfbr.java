package cl.araucana.cp.afbr;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cl.araucana.cp.afbr.servicios.Servicios;
import net.sf.jasperreports.engine.JRException;

public class ReportespdfAfbr extends HttpServlet implements
		javax.servlet.Servlet {

	private static final long serialVersionUID = -6927900331859667291L;

	public ReportespdfAfbr() {

	}

	private static String rutaJasper = null;

	private static String rutaPdf = null;

	private static String rutaBarra = null;

	public void init() throws ServletException {
		getServletContext().log("getinit init");

		rutaJasper = getServletConfig().getInitParameter("rutaJasperAfbr");
		rutaPdf = getServletConfig().getInitParameter("rutaPdfAfbr");
		rutaBarra = getServletConfig().getInitParameter("rutaBarra");

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			String pathJrxml = rutaJasper + "/Afbr.jrxml";
			String pathPdf = rutaPdf + "/Afbr.pdf";
			String codigoProceso = request.getParameter("fechaProceso");
			String empag = request.getParameter("empag");
			String folio = request.getParameter("folio");
			String rutEmpresa = request.getParameter("rutEmpresa");

			Servicios.generarReporte(codigoProceso, empag, folio, rutEmpresa,
					rutaBarra, rutaJasper, rutaPdf, pathJrxml, pathPdf,
					response);

		} catch (JRException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}

}
