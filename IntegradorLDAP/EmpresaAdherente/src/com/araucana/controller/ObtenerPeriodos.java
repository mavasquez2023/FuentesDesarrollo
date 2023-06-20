/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.araucana.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.araucana.service.Archivos_unificar;

/**
 *
 * @author TestGroup
 */
public class ObtenerPeriodos {
	private static final Logger log = Logger.getLogger(Archivos_unificar.class);
    public List<String> listaPeriodos = new ArrayList<String>();

    public static void main(String[] args) throws IOException {

    }

    public List<String> funObtenerPeriodos(Map<String, String> mapaEA1) throws IOException {
        String xRutaProyecto_NC = ""; //1	
        String xRutaProyecto_CF = ""; //2
        String xRutaProyecto_NL = ""; //3
        String xRutaProyecto_AT = ""; //4
        
        log.info("Entró a funObtenerPeriodos...");

        listaPeriodos.clear();
        Rutas funRutas = new Rutas();

        xRutaProyecto_NC = funRutas.funObtenerRuta("1"); //Ruta Destino Nomina Credito
        xRutaProyecto_CF = funRutas.funObtenerRuta("2"); //Ruta Destino Nominas Cargas Familiares
        xRutaProyecto_NL = funRutas.funObtenerRuta("3"); //Ruta Destino Nominas Leasing
        xRutaProyecto_AT = funRutas.funObtenerRuta("4"); //Ruta Destino Nominas Anexos Trabajadores

        subAgregarLista(xRutaProyecto_NC, mapaEA1);
        subAgregarLista(xRutaProyecto_CF, mapaEA1);
        subAgregarLista(xRutaProyecto_NL, mapaEA1);
        subAgregarLista(xRutaProyecto_AT, mapaEA1);

        return listaPeriodos;
    }

    public void subAgregarLista(String xDir, Map<String, String> mapaEA1) {
        String xValor1 = "";
        String xValor2 = "";
        String xExisteValor = "";
        String xKey = "";
        char xCar = ' ';
        char xCar2 = '-';
        
        
        File dirPeriodoDestinoEA = new File(xDir);
        
        log.error("subAgregarLista - Validar Si existe Carpeta NC, NL o CF para obtener Periodos: " + dirPeriodoDestinoEA);

        if (dirPeriodoDestinoEA.exists()) { //Verificar si existe Carpeta NC, NL o CF.
            File listFile[] = dirPeriodoDestinoEA.listFiles();
            
            log.error("subAgregarLista - Si existe Carpeta: " + xDir + ". Hay " + listFile.length + " Periodos.");

            for (int ii = 0; ii < listFile.length; ii++) { //Verificar cada Periodo dentro de NC, NL o CF.
                xValor1 = listFile[ii].getName();

                Iterator it = mapaEA1.keySet().iterator();
                while (it.hasNext()) { //Verificar si cada Empresa existe en el Periodo.
                    String key = (String) it.next();
                    //log.info("Clave: " + key + " -> Valor: " + map.get(key));
                    
                    log.info("subAgregarLista. Empresa con Digito: " + key + " (" + xDir + ")");
                    
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
        			
        			log.info("subAgregarLista. Empresa sin Digito: " + key + " (" + xDir + ")");

                    File dirPeriodoDestinoEA1 = new File(xDir + "\\" + xValor1 + "\\" + key);
                    if (dirPeriodoDestinoEA1.exists()) {
                        log.info("Periodo " + ii + ": " + listFile[ii]);
                        
                        xExisteValor = "N";
                        for (int jj = 0; jj < listaPeriodos.size(); jj++) {
                            xValor2 = listaPeriodos.get(jj);
                            if (xValor1.equals(xValor2)) {
                                xExisteValor = "S";
                            }
                        }
                        if (xExisteValor.equals("N")) {
                            listaPeriodos.add(xValor1);
                            log.info("Periodo agregado: " + xValor1);
                        }

                        break;
                    }
                }
            }
        } else {
        	log.error("subAgregarLista - No existe Carpeta NC, NL o CF para obtener Periodos: " + dirPeriodoDestinoEA);
        }
    }
}
