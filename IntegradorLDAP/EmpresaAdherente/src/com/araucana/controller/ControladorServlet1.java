/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.araucana.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.araucana.legacy.EmpresaVO;
import com.araucana.legacy.EmpresasUtils;

import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;

/**
 *
 * @author TestGroup
 */
//1 de 6: Aqui des-comentar para ejecucion Local
//@WebServlet(name = "/ControladorServlet1")
public class ControladorServlet1 extends HttpServlet {
	private static final Logger log = Logger.getLogger(ControladorServlet1.class);
	private static final long serialVersionUID = 1L;

	private ModeloEA modeloEA = new ModeloEA();

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try  {
			PrintWriter out = response.getWriter();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		tituloEA(request, response, "S");
		log.info("obtenerEmpresas desde doGet.");	
		obtenerEmpresas(request, response, "N", null, null, null, null, "S", "", "","");
	}

	private void obtenerEmpresas(HttpServletRequest request, HttpServletResponse response, String xIndica,
			String[] xMarcaNC, String[] xMarcaNL, String[] xMarcaCF, String[] xOI, String xIndica2,
			String xPerSeleccionado, String xFormatoDescarga, String xRutEjecutivo) throws IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Map<String, String> empresas1 = null;
		String[] marcaNC;
		String[] marcaNL;
		String[] marcaCF;
		String[] oi;
		List<String> funPeriodos = new ArrayList<String>();
		String xPerSeleccionado2 = "";
		int xCuenta = 0;
		
		String urlRequerida = httpRequest.getRequestURI();
		log.info("URL: " + urlRequerida);			

		try {
			//2 de 6: Aqui comentar para ejecucion Local
			String userID = getUserName(request);
			request.getSession().setAttribute("Empresas", getEmpresas(userID));
			//Fin comentar
			
			empresas1 = modeloEA.getEA1(request, xRutEjecutivo);

			ObtenerPeriodos newPeriodos = new ObtenerPeriodos();
			funPeriodos = newPeriodos.funObtenerPeriodos(empresas1);
			Collections.sort(funPeriodos, Collections.reverseOrder());
			
			log.info("Total Periodos seleccionados: " + funPeriodos.size());
			for (int ii = 0; ii < funPeriodos.size(); ii++) {
				log.info("Periodo seleccionado " + ii + ": " + funPeriodos.get(ii));
			}
			
			xCuenta = empresas1.size();
			
			if (xIndica2.equals("S")) { // Para indicar cuando es primera vez.
				xPerSeleccionado = "";
				xRutEjecutivo = "";
				request.setAttribute("perAnteriorOculto", "");
				request.setAttribute("rutEjecutivoAnt", "");
				xFormatoDescarga = "csv2";

				if (!funPeriodos.isEmpty()) {
					xPerSeleccionado = funPeriodos.get(0);
					request.setAttribute("perAnteriorOculto", xPerSeleccionado);
				}
			}
			
			String xRol = modeloEA.funObtieneRol(request);
			
			if (xRol.equals("E")) {
				if (!funPeriodos.isEmpty()) {
					int xCuentaPeriodos = funPeriodos.size();
					
					if (xCuentaPeriodos == 1) {
						xPerSeleccionado = funPeriodos.get(0);
						log.info("Rol Ejecutivo. Hay solo 1. Periodo Seleccionado es funPeriodos.get(0): " + xPerSeleccionado);						
					} else {
						String txtPer = request.getParameter("txtPer");
						log.info("Rol Ejecutivo. Periodo txtPer: " + txtPer);
	
						if (txtPer != null) {
							// Formato de periodo yyyyMM
							String mes = txtPer.substring(4, 6);
							String anno = txtPer.substring(0, 4);
							xPerSeleccionado = anno + "" + mes;
							log.info("Rol Ejecutivo. Periodo Seleccionado: " + xPerSeleccionado);	
						} else {
							xPerSeleccionado = funPeriodos.get(0);
							log.info("Rol Ejecutivo. Periodo Seleccionado es funPeriodos.get(0): " + xPerSeleccionado);
						}
					}
				} else {
					xPerSeleccionado = "";
					log.info("Rol Ejecutivo. No hay Periodos.");
				}
			}
			log.info("Empresas a mostrar: " + xCuenta);

			marcaNC = new String[xCuenta];
			marcaNL = new String[xCuenta];
			marcaCF = new String[xCuenta];
			oi = new String[4];

			for (int jj = 0; jj < xCuenta; jj++) {
				if (xIndica.equals("N")) {
					marcaNC[jj] = "N";
					marcaNL[jj] = "N";
					marcaCF[jj] = "N";
				} else {
					marcaNC[jj] = xMarcaNC[jj];
					marcaNL[jj] = xMarcaNL[jj];
					marcaCF[jj] = xMarcaCF[jj];
				}
			}

			for (int jj = 0; jj < 4; jj++) {
				if (xIndica.equals("N")) {
					oi[jj] = "N";
				} else {
					oi[jj] = xOI[jj];
				}
			}

			request.setAttribute("marcaNC", marcaNC);
			request.setAttribute("marcaNL", marcaNL);
			request.setAttribute("marcaCF", marcaCF);
			request.setAttribute("oi", oi);
			request.setAttribute("lista_empresas1", empresas1);
			request.setAttribute("perSeleccionado", xPerSeleccionado);
			request.setAttribute("formatoDescarga", xFormatoDescarga);
			request.setAttribute("funPeriodos", funPeriodos);
			
			String txtRutEjecutivo = request.getParameter("rutEjecutivo");
			request.setAttribute("rutEjecutivo", xRutEjecutivo);
			request.setAttribute("rutEjecutivoAnt", txtRutEjecutivo);
			request.setAttribute("xRol", xRol);
			
			//Nombre Usuario logeado
			
			//3 de 6: Aqui comentar para ejecucion Local
			///*
			String userID1 = getUserName(request);
			log.info("Se obtendrá el nombre del Usuario: " + userID1);
			
			UserRegistryConnection urConnection = null;
			urConnection = new UserRegistryConnection();
			User xUser = urConnection.getUser(userID1.toUpperCase());
			String xUserName =  xUser.getFullName(true);
			request.setAttribute("txtRUTnombre", xUserName);
			
			log.info("Nombre obtenido: " + xUserName);
			//*/
			//Fin comentar
			
			//4 de 6: Aqui des-comentar para ejecucion local
			//String xUserName =  "Nélsoñ CÓLMAN ÑÑ";
			//request.setAttribute("txtRUTnombre", xUserName);
			//log.info("Nombre obtenido: " + xUserName);
			//Fin
			
			RequestDispatcher miDispatcher = request.getRequestDispatcher("/descarga-nominas3.jsp");
			miDispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void tituloEA(HttpServletRequest request, HttpServletResponse response, String valorT) {
		String valor_T = valorT;
		log.info("TITULO: " + valor_T);
		request.setAttribute("valorT", valorT);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String perAnteriorOculto = "";
		String rutEjecutivoAnt = "";
		String periodoFormat = "";
		String xKey = "";
		char xCar = ' ';
		char xCar2 = '-';
		String xSeguir = "N";
		String xHayNominaMarcada = "";

		String rutEjecutivo = request.getParameter("rutEjecutivo");
		log.info("Rut ingresado por Ejecutivo: " + rutEjecutivo);
		
		String xRol = "A";
		try {
			xRol = modeloEA.funObtieneRol(request);
		} catch (Exception e1) {
			log.info("Algo pasó al obtener Rol." + e1.getMessage());
		}
		
		// Cargar Empresas
		mapaEA funMapaEA = new mapaEA();
		Map<String, String> mapaEA1 = new TreeMap<String, String>();
		try {
			mapaEA1 = funMapaEA.funcionMapaEA(request, rutEjecutivo);
		} catch (Exception e1) {
			log.info("Algo pasó al Cargar Empresas." + e1.getMessage());
		}

		int xCuenta = mapaEA1.size();
		log.info("Cuenta empresas: " + xCuenta);
		// Fin

		// Parametros generales
		String txtPer = request.getParameter("txtPer");
		log.info("Periodo txtPer: " + txtPer);
		String txtRUT = getUserName(request);
		log.info("RUT: " + txtRUT);
		String txtFormato = request.getParameter("formato-exportacion");
		log.info("formato-exportacion: " + txtFormato);	

		if (txtPer != null) {
			// Formato de periodo yyyyMM
			String mes = txtPer.substring(4, 6);
			String anno = txtPer.substring(0, 4);
			periodoFormat = anno + "" + mes;
			String periodoFormat2 = periodoFormat; // mes + "/" + anno;
			log.info("Periodo Seleccionado: " + periodoFormat);
		
			perAnteriorOculto = request.getParameter("perAnteriorOculto");
			request.setAttribute("perAnteriorOculto", periodoFormat2);
			
			rutEjecutivoAnt = request.getParameter("rutEjecutivoAnt");		
		} else {
			request.setAttribute("perAnteriorOculto", "");
			log.info("Periodo Seleccionado: Esta Nulo.");
		}
		
		if (xRol.equals("A")) { //Rol Administrador
			if ((!perAnteriorOculto.equals(txtPer)) || (txtPer == null)) {
				if (!perAnteriorOculto.equals("")) {
					xSeguir = "N";
					log.info("###Cambió a periodo: " + txtPer + " (Per.Ant.: " + perAnteriorOculto + ")");
				} else {
					xSeguir = "S";
				}
			} else {
				xSeguir = "S";
			}
		} else { //Rol Ejecutivo
			if ((!perAnteriorOculto.equals(txtPer)) || (txtPer == null)) {
				if (!perAnteriorOculto.equals("")) {
					xSeguir = "N";
					log.info("###Cambió a periodo: " + txtPer + " (Per.Ant.: " + perAnteriorOculto + ")");
				} else {
					xSeguir = "S";
				}
			} else {
				xSeguir = "S";
			}
				
			if (xSeguir.equals("S")) {
				if ((!rutEjecutivoAnt.equals(rutEjecutivo)) || (rutEjecutivoAnt == null)) {
					if (!rutEjecutivoAnt.equals("")) {
						xSeguir = "N";
						log.info("###Usuario ejecutivo recién ingresa. RUT ingresado: " + rutEjecutivo + " (Rut.Ant.: " + rutEjecutivoAnt + ")");
					} else {
						xSeguir = "S";
					}
				} else {
					xSeguir = "S";
				}
			}			
		}
		
		log.info("###xSeguir: " + xSeguir);
		log.info("###Periodo: " + txtPer + " (Per.Ant.: " + perAnteriorOculto + ")");
		log.info("###RUT ingresado: " + rutEjecutivo + " (Rut.Ant.: " + rutEjecutivoAnt + ")");
		
		if (xSeguir == "N") {
			log.info("obtenerEmpresas por Cambio Periodo o RUT ingresado por Ejecutivo.");	
			obtenerEmpresas(request, response, "N", null, null, null, null, "N", periodoFormat, txtFormato, rutEjecutivo);
		} else {
			// Organizacion de la informacion
			String[] oi;
			oi = new String[4];

			for (int jj = 1; jj <= 4; jj++) {
				oi[jj - 1] = "N";
			}

			String xOrganiza = "", kk = "", xIzq = "", xDer = "";
			String[] organizacion_informacion = request.getParameterValues("organizacion-informacion");

			xOrganiza = "NNNN";
			
			if (organizacion_informacion != null) {
				for (int ii = 0; ii < organizacion_informacion.length; ii++) {
					for (int jj = 1; jj <= 4; jj++) { // Se recorren los 4 parametros
						kk = String.valueOf(jj);

						if (organizacion_informacion[ii].equals(kk)) {
							oi[jj - 1] = "S";

							if (jj == 1) { // Primero
								xDer = xOrganiza.substring(1, 4);
								xOrganiza = "S" + xDer;
							} else {
								if (jj == 4) { // Ultimo
									xIzq = xOrganiza.substring(0, 3);
									xOrganiza = xIzq + "S";
								} else { // En el medio
									xIzq = xOrganiza.substring(0, jj - 1);
									xDer = xOrganiza.substring(jj, 4);
									xOrganiza = xIzq + "S" + xDer;
								}
							}
						}
					}
				}
			}
			log.info("Organizacion informacion: " + xOrganiza);
			// Fin

			String[] marcaNC;
			marcaNC = new String[xCuenta];
			String[] marcaNL;
			marcaNL = new String[xCuenta];
			String[] marcaCF;
			marcaCF = new String[xCuenta];
			
			xHayNominaMarcada = "N";

			String xNC = "", xNL = "", xCF = "";
			for (int jj = 1; jj <= xCuenta; jj++) {
				xNC += "N";
				xNL += "N";
				xCF += "N";

				marcaNC[jj - 1] = "N";
				marcaNL[jj - 1] = "N";
				marcaCF[jj - 1] = "N";
			}

			// Nomina Credito
			String[] miNC = request.getParameterValues("nominas-credito");

			if (miNC != null) {
				for (int ii = 0; ii < miNC.length; ii++) {
					for (int jj = 1; jj <= xCuenta; jj++) {
						kk = String.valueOf(jj);

						if (miNC[ii].equals(kk)) {
							marcaNC[jj - 1] = "S";
							
							xHayNominaMarcada = "S";

							if (jj == 1) {
								xDer = xNC.substring(1, xCuenta);
								xNC = "S" + xDer;
							} else {
								if (jj == xCuenta) {
									xIzq = xNC.substring(0, xCuenta - 1);
									xNC = xIzq + "S";
								} else {
									xIzq = xNC.substring(0, jj - 1);
									xDer = xNC.substring(jj, xCuenta);
									xNC = xIzq + "S" + xDer;
								}
							}
						}
					}
				}
			}
			log.info("Nominas credito: " + xNC);
			// Fin

			// Nomina Ahorro
			String[] miNL = request.getParameterValues("nominas-ahorro");
			
			if (miNL != null) {
				for (int ii = 0; ii < miNL.length; ii++) {
					for (int jj = 1; jj <= xCuenta; jj++) {
						kk = String.valueOf(jj);
						if (miNL[ii].equals(kk)) {
							marcaNL[jj - 1] = "S";
							
							xHayNominaMarcada = "S";

							if (jj == 1) {
								xDer = xNL.substring(1, xCuenta);
								xNL = "S" + xDer;
							} else {
								if (jj == xCuenta) {
									xIzq = xNL.substring(0, xCuenta - 1);
									xNL = xIzq + "S";
								} else {
									xIzq = xNL.substring(0, jj - 1);
									xDer = xNL.substring(jj, xCuenta);
									xNL = xIzq + "S" + xDer;
								}
							}
						}
					}
				}
			}
			log.info("Nominas ahorro: " + xNL);
			// Fin

			// Nomina Trabajadores
			String[] miCF = request.getParameterValues("nominas-trabajadores");
			
			if (miCF != null) {
				for (int ii = 0; ii < miCF.length; ii++) {
					for (int jj = 1; jj <= xCuenta; jj++) {
						kk = String.valueOf(jj);
						if (miCF[ii].equals(kk)) {
							marcaCF[jj - 1] = "S";
							
							xHayNominaMarcada = "S";

							if (jj == 1) {
								xDer = xCF.substring(1, xCuenta);
								xCF = "S" + xDer;
							} else {
								if (jj == xCuenta) {
									xIzq = xCF.substring(0, xCuenta - 1);
									xCF = xIzq + "S";
								} else {
									xIzq = xCF.substring(0, jj - 1);
									xDer = xCF.substring(jj, xCuenta);
									xCF = xIzq + "S" + xDer;
								}
							}
						}
					}
				}
			}
			log.info("Nominas trabajadores: " + xCF);
			// Fin
			
			// Mapa de Salida
			int pp = 0;
			String xNC_car = "", xNL_car = "", xCF_car = "", xRutEA = "";
			Map<String, String> mapEmpresasEA2 = new TreeMap<String, String>();

			for (int jj = 1; jj <= xCuenta; jj++) {
				xNC_car = xNC.substring(jj - 1, jj);
				xNL_car = xNL.substring(jj - 1, jj);
				xCF_car = xCF.substring(jj - 1, jj);

				if (xNC_car.equals("S") || xNL_car.equals("S") || xCF_car.equals("S")) {
					String xSSS = xNC_car + xNL_car + xCF_car;

					xRutEA = "???";
					pp = 0;
					// Recorrer Mapa de Entrada buscando la posiciÃ³n del RUT

					// Procesamos el Map con un Iterador
					Iterator it = mapaEA1.keySet().iterator();
					while (it.hasNext()) {
						String key = (String) it.next();

						pp += 1;
						if (pp == jj) {
							//log.info("Clave: " + key + " -> Valor: " + mapaEA2.get(key));
							log.info("xSSS: " + xSSS);
							
							//log.info("Empresa con Digito: " + key);
							
		                    //Sacar el Digito Verificador
		        			xKey = "";
		        			for (int kkk = 0; kkk < key.length(); kkk++) {
		        				xCar = key.charAt(kkk);
		        				
		        				if (xCar != xCar2) {
		        					xKey = xKey + xCar;
		        				} else {
		        					break;
		        				}
		        			}
		        			key = xKey;
		        			//Fin		
		        			
		        			//log.info("Empresa sin Digito: " + key);
							
							xRutEA = key;
						}
					}

					mapEmpresasEA2.put(xRutEA, xSSS);
				}
			}
			txtRUT = getUserName(request);
			log.info(">>>RUT: " + txtRUT);

			String fileName;
			try {
				fileName = descargarEA.subProcesarDescarga(xOrganiza, xHayNominaMarcada, mapEmpresasEA2, periodoFormat, txtRUT,
						txtFormato);

				log.info("RUTA archivo ZIP: " + fileName);
	
				// Descargar archivo ZIP generado
				File xArchivoDescargaZIP = new File(fileName);
				log.info("RUTA archivo ZIP - existe?: " + xArchivoDescargaZIP.exists());
				if (xArchivoDescargaZIP.exists()) {
					log.info("RUTA archivo ZIP - Si existe: " + xArchivoDescargaZIP.exists());
					
					Path p = new File(fileName).toPath();
					byte[] contents = Files.readAllBytes(p);
	
					// HttpServletResponse response1 = null;
					response.addHeader("content-type", "application/zip");
					response.addHeader("content-disposition", "filename=\"" + p.getFileName().toString() + "\"");
					response.addHeader("cache-control", "must-revalidate, post-check=0, pre-check=0");
	
					ServletOutputStream out = response.getOutputStream();
					out.write(contents);
	
					out.flush();
					out.close();
				} else {
					log.info("obtenerEmpresas porque no se generó Descarga.");	
					obtenerEmpresas(request, response, "S", marcaNC, marcaNL, marcaCF, oi, "N", periodoFormat, txtFormato, rutEjecutivo);
				}
				// Fin descargar archivo ZIP
			} catch (Exception e) {
				log.info("Error en descargar el Archivo ZIP generado: " + e.getMessage());
			}			
		}
		// Fin
	}

	private String getUserName(HttpServletRequest httpRequest) {
		//5 de 6: Aqui comentar para ejecucion local
		Principal userPrincipal = httpRequest.getUserPrincipal();
		return userPrincipal.getName();
		//Fin comentar
		
		//6 de 6: Aqui des-comentar para ejecucion local
		//return "123456789";
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}
	
	private Map<String, String> getEmpresas(String user) {
		  Map<String, String> empresas = EmpresasUtils.getEmpresasLDAP(user);
		  
		  List<EmpresaVO> out = new ArrayList<EmpresaVO>(); EmpresaVO emp; for (String
		  rutEmpresa : empresas.keySet()) { String nombreEmpresa =
		  empresas.get(rutEmpresa); emp = new EmpresaVO();
		  emp.setRutEmpresa(rutEmpresa); emp.setNombreEmpresa(nombreEmpresa);
		  out.add(emp); }
		  
	      return empresas; 
	 }
}
