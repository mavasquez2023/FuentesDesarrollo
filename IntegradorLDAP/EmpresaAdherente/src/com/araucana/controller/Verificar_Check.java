/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.araucana.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


import com.araucana.service.Archivos_unificar;

/**
 *
 * @author TestGroup
 */
public class Verificar_Check {
	private static final Logger log = Logger.getLogger(Archivos_unificar.class);
	
	public static String gloRutaProyecto = "";
	public static String gloRutaProyecto_NC = "";        // 1
	public static String gloRutaProyecto_NC_Indice = ""; // 1
	public static String gloRutaProyecto_CF = "";        // 2
	public static String gloRutaProyecto_CF_Indice = ""; // 1
	public static String gloRutaProyecto_NL = "";        // 3
	public static String gloRutaProyecto_NL_Indice = ""; // 1
	public static String gloRutaProyecto_AT = "";        // 3
	public static String gloRutaProyecto_AT_Indice = ""; // 1

	public static void main(String[] args) {
		// TODO code application logic here
	}

	public String funcionCheck(String xPeriodo, String xRutBuscar) throws IOException {
		String xReturn1 = "X";
		String xReturn2 = "X";
		String xReturn3 = "X";

		subObtenerRutas(xPeriodo);
		
		xReturn1 = funcionLeeIndice(gloRutaProyecto_NC_Indice, xRutBuscar);
		xReturn2 = funcionLeeIndice(gloRutaProyecto_NL_Indice, xRutBuscar);
		xReturn3 = funcionLeeIndice(gloRutaProyecto_CF_Indice, xRutBuscar);
		if (xReturn3 == "N") {
			xReturn3 = funcionLeeIndice(gloRutaProyecto_AT_Indice, xRutBuscar);
		}

		return xReturn1 + xReturn2 + xReturn3;
	}

	public String funcionLeeIndice(String xArchivoIndice, String xRutBuscar) throws IOException {
		String xLinea = "";
		String xReturn = "N";
		String xKey = "";
		char xCar = ' ';
		char xCar2 = '-';
		
        //Sacar el Digito Verificador
		xKey = "";
		for (int kkk = 0; kkk < xRutBuscar.length(); kkk++) {
			xCar = xRutBuscar.charAt(kkk);
			
			if (xCar != xCar2) {
				xKey = xKey + xCar;
			} else {
				break;
			}
		}
		xRutBuscar = xKey;
		//Fin	

		try 
		{
			File txtArchivoIndice = new File(xArchivoIndice);
	
			if (txtArchivoIndice.exists()) {
				FileReader frArchivoIndice = new FileReader(txtArchivoIndice);
				BufferedReader brArchivoIndice = new BufferedReader(frArchivoIndice);
	
				while (true) {
					xLinea = brArchivoIndice.readLine();
	
					if (xLinea == null) {
						break;
					} else {
						if (xLinea.equals(xRutBuscar)) {
							xReturn = "S";
							break;
						}
					}
				}
	
				log.info("Verificar Check - xReturn: " + xReturn + " (Buscar '" + xRutBuscar + "' en '" + xArchivoIndice + "')");
			} else {
				log.info("***");
				log.info("***Verificar Check - Archivo índice No existe: " + xArchivoIndice);
				log.info("***");
			}
			
		} catch (FileNotFoundException e) {
			log.info("***");
			log.info("***Verificar Check - FileNotFoundException en: " + xArchivoIndice);
			log.info(e.getMessage());
			log.info("***");
		} catch (IOException e) {
			log.info("***");
			log.info("***Verificar Check - IOException en: " + xArchivoIndice);
			log.info(e.getMessage());
			log.info("***");
		}		

		return xReturn;
	}

	public static void subObtenerRutas(String xPeriodo) throws IOException {
		String xRutaOrigen = "";
		Properties properties = new Properties();

		try  {
			InputStream input = Rutas.class.getClassLoader().getResourceAsStream("application.properties");
			if (input == null) {
				log.info("Archivo 'application.properties' no encontrado");
			}
			properties.load(input);
			xRutaOrigen = properties.getProperty("Ruta_Origen");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		log.info("***Verificar_Check.gloRutaOrigen: " + xRutaOrigen);
		gloRutaProyecto = xRutaOrigen;
		// gloRutaProyecto = "C:\\extranet\\EA"; Ruta usada para la Prueba;
		// Fin

		// Ruta Destino Nomina Credito
			gloRutaProyecto_NC = properties.getProperty("NC_ruta_destino");
			gloRutaProyecto_NC_Indice = gloRutaProyecto_NC + "\\" + xPeriodo + "\\ArchivoIndice.txt";

		// Ruta Destino Nominas Anexos Cargas Familiares
			gloRutaProyecto_CF = properties.getProperty("CF_ruta_destino");
			gloRutaProyecto_CF_Indice = gloRutaProyecto_CF + "\\" + xPeriodo + "\\ArchivoIndice.txt";
			
		// Ruta Destino Nominas Leasing
			gloRutaProyecto_NL = properties.getProperty("NL_ruta_destino");
			gloRutaProyecto_NL_Indice = gloRutaProyecto_NL + "\\" + xPeriodo + "\\ArchivoIndice.txt";
			
		// Ruta Destino Nominas Anexos Cargas Familiares
			gloRutaProyecto_AT = properties.getProperty("AT_ruta_destino");
			gloRutaProyecto_AT_Indice = gloRutaProyecto_AT + "\\" + xPeriodo + "\\ArchivoIndice.txt";			

		log.info("Verificar Check - gloRutaProyecto: " + gloRutaProyecto);
		log.info("Verificar Check - gloRutaProyecto_NC_Indice: " + gloRutaProyecto_NC_Indice);
		log.info("Verificar Check - gloRutaProyecto_CF_Indice: " + gloRutaProyecto_CF_Indice);
		log.info("Verificar Check - gloRutaProyecto_NL_Indice: " + gloRutaProyecto_NL_Indice);
		log.info("Verificar Check - gloRutaProyecto_AT_Indice: " + gloRutaProyecto_AT_Indice);
		log.info("Verificar Check - xPeriodo: " + xPeriodo);
	}

}
