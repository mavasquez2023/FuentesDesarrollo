/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.araucana.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.araucana.service.Archivos_unificar;

/**
 *
 * @author TestGroup
 */
public class Rutas {
	private static final Logger log = Logger.getLogger(Archivos_unificar.class);
	
	public static void main(String[] args) throws IOException {
	}

	public String funObtenerRuta(String xNro) throws IOException {
		String xReturn = "";
		String xRutaProyecto = "";

		// Leer properties donde esta la Ruta Origen
		String xRutaOrigen = "";
		Properties properties = new Properties();

		try {
			InputStream input = Rutas.class.getClassLoader().getResourceAsStream("application.properties");
			if (input == null) {
				log.error("Archivo 'application.properties' no encontrado");
			} else {
				properties.load(input);
				xRutaOrigen = properties.getProperty("Ruta_Origen");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		log.info("***Rutas.xRutaOrigen: " + xRutaOrigen);
		xReturn = xRutaOrigen;
		// Fin
		int xnro= Integer.parseInt(xNro);
		if (xNro != "0") {
			xRutaProyecto = xReturn;

	        switch (xnro) {      
	            case 1: //NC
	            	xReturn = properties.getProperty("NC_ruta_destino");
	                break;
	            case 2: //CF
	            	xReturn = properties.getProperty("CF_ruta_destino");
	                break;
	            case 3: //NL
	            	xReturn = properties.getProperty("NL_ruta_destino");
	                break;
	            case 4: //AT
	            	xReturn = properties.getProperty("AT_ruta_destino");
	                break;
            	case 5: //Retardo_Limpiar_Temporal
            		xReturn = properties.getProperty("Retardo_Limpiar_Temporal");
            		break;	  	        
	            default:
	                break;
	        }				
		}

		return xReturn;

	}
}