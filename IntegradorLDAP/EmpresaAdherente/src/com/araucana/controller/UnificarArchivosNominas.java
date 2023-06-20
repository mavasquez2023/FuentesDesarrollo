package com.araucana.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.araucana.service.Archivos_unificar;

//@WebServlet(name = "/UnificarNominas")
public class UnificarArchivosNominas extends HttpServlet {
	private static final long serialVersionUID = 4447387729533929591L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String periodo = request.getParameter("Periodo");
		String tipoNomina = request.getParameter("TipoNomina");
		String procesaFtp = request.getParameter("ProcesaFTP");
		
		Archivos_unificar unificador = new Archivos_unificar();
		unificador.procesoUnificarNominas(periodo, tipoNomina, procesaFtp);
	}
}
