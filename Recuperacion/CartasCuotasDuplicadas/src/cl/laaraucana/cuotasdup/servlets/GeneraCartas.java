package cl.laaraucana.cuotasdup.servlets;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cl.laaraucana.cuotasdup.business.GeneracionCartas;
import cl.laaraucana.cuotasdup.jcrontab.CronCuotasDuplicadas;

@WebServlet("/GeneraCartas")
public class GeneraCartas extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -387343909829181466L;
	protected Logger logger = Logger.getLogger(this.getClass());

	public GeneraCartas() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProccesRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProccesRequest(request, response);
	}

	protected void ProccesRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			logger.info("<< Ingreso a Genera Cartas");
			GeneracionCartas envio= new GeneracionCartas();
			envio.generarCartas();
			logger.info("<< Fin Genera Cartas");
			RequestDispatcher rd = request.getRequestDispatcher("/ok.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.reset();
			logger.error("Error en " + this.getClass().getName() + ":" + e.getMessage(), e);
			RequestDispatcher rd = request.getRequestDispatcher("/main/error.do?errorMsg=" + e.getMessage());
			rd.forward(request, response);
		}

	}

}
