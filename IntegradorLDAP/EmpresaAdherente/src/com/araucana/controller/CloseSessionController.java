package com.araucana.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class CloseSessionController extends HttpServlet {
	private static final long serialVersionUID = -6617618921037415744L;
	private static final Logger log = Logger.getLogger(CloseSessionController.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		HttpServletResponse res = (HttpServletResponse) response;
		try {
			res.sendRedirect(getUrl());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	private String getUrl() {
		// Leer properties donde esta la URL a ejecutar cuando se dé click en Cerrar
		String xRutaOrigen = "";
		Properties properties = new Properties();

		try  {
			InputStream input = Rutas.class.getClassLoader().getResourceAsStream("application.properties");
			if (input == null) {
				log.error("Archivo 'application.properties' no encontrado");
			}
			properties.load(input);
			xRutaOrigen = properties.getProperty("OnCloseSessionURL");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		log.info("***URL ejecutada al Cerrar: " + xRutaOrigen);
		return xRutaOrigen;
	}
}
