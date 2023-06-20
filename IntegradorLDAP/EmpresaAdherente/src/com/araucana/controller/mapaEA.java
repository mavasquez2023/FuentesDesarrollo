/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.araucana.controller;

import java.util.TreeMap;
import java.util.Map;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.araucana.dao.SessionFactory;
import com.araucana.entity.Empresa;
import com.araucana.service.RutService;
import com.araucana.service.impl.RutServiceImpl;


import cl.araucana.core.registry.exception.UserRegistryException;

/**
 *
 * @author TestGroup
 */
public class mapaEA {
	
	private ModeloEA modeloEA = new ModeloEA();

    public Map<String, String> funcionMapaEA(HttpServletRequest request, String xRUT) throws UserRegistryException, IOException {
    	
    	String xRol = modeloEA.funObtieneRol(request);
    	
    	Map<String, String> map = new TreeMap<String, String>();
    	
    	//1 de 3: Aqui comentar para ejecucion Local
    	///*
    	if (xRol.equals("E")) { //Rol Ejecutivo
			map = funcionProcesaEjecutivo(request, xRUT);
    	} else {	    	
    		map = (Map<String, String>) request.getSession().getAttribute("Empresas");
    	}
	    //*/
        //Fin comentar
    	
    	
    	//2 de 3: Aqui des-comentar para ejecucion local
        /*
    	if (xRol.equals("E")) { //Rol Ejecutivo
    		map = funcionProcesaEjecutivo(request, xRUT);
    	} else {	    	
	        map.put("5109450-1", "Razon Social 1");  
	        map.put("5220602-2", "Razon Social 2");
	        map.put("5398422-3", "Razon Social 3");
	        map.put("2639011-4", "Razon Social 4");
	        map.put("3147286-5", "Razon Social 5");
	        map.put("3375204-6", "Razon Social 6");
	        map.put("1890951-0", "Razon Social 7");
	        map.put("1457776-9", "Razon Social 8");
    	}
	    */
        //Fin comentar
    	
        return map;
    }
    
    public Map<String, String> funcionProcesaEjecutivo(HttpServletRequest request, String xRUT) {
    	String xRazonSocial = "";
    	String xValidaDV = "";
    	String xNroRUT = "";
    	String xDV = "";
        char xCar = ' ';
        char xCar2 = '-';
        String xHayGuion = "";
        Boolean esNumerico = true;
        
        Map<String, String> map = new TreeMap<String, String>();
    	
		request.setAttribute("txtEjecutivo", "Luego, seleccione Nómina y haga Click en Descargar.");
		
		if (!xRUT.equals("")) {
            //Separar Numero y DV desde el RUT.
			xNroRUT = "";
			xDV = "";
			xHayGuion = "N";
			for (int kk = 0; kk < xRUT.length(); kk++) {
				xCar = xRUT.charAt(kk);
				
				if (xHayGuion == "N") {
    				if (xCar != xCar2) {
    					xNroRUT = xNroRUT + xCar;
    				} else {
    					xDV = xDV + xCar;
    					xHayGuion = "S";
    				}
				} else {
					xDV = xDV + xCar;
				}
			}    			
			//Fin
			
			esNumerico =  xNroRUT.matches("[+-]?\\d*(\\.\\d+)?");
			
			if (esNumerico == true) {
				if (xHayGuion == "S") {
	    			xValidaDV = funValidaDV(xRUT);
	    			if (xValidaDV.equals("S")) {
	    				xRazonSocial = funObtieneRazonSocial(xNroRUT);
	    				
	    				if (xRazonSocial == null) {
	    					xRazonSocial = "";
	    				}
	    				
	    				if (!xRazonSocial.equals("")) {
	    					map.put(xRUT, xRazonSocial);
	    				} else {
	    					request.setAttribute("txtEjecutivo", "RUT ingresado no existe.");
	    				}
	    			} else {
	    				request.setAttribute("txtEjecutivo", "Dígito Verificador del RUT ingresado está incorrecto.");
	    			}
				} else {
					request.setAttribute("txtEjecutivo", "Faltó incluir el Guión en el RUT ingresado.");
				}
			} else {
				request.setAttribute("txtEjecutivo", "Número del RUT ingresado está incorrecto, debe ser numérico.");
			}
		}    
		
		return map;
    }
    
    public String funObtieneRazonSocial(String xRUT) {
    	String xRazonSocial = "";
    	    	
    	//3 de 3: Aqui comentar para ejecucion Local
    	xRazonSocial = retrieveEnterpriseName(xRUT);
    	
    	//xRazonSocial = "Razón Socia ejec. local";
    	
    	return xRazonSocial;
    }
    
    private String retrieveEnterpriseName(String rut) {
		SqlSessionFactory sqlSession = SessionFactory.getSessionFactory();
		SqlSession session = sqlSession.openSession();

		String nombreEmpresa = "";
		try {
			Empresa empresa = session.selectOne("Empresas.findByRut", rut);
			nombreEmpresa = empresa.getRazon();
			
			if (nombreEmpresa.trim().length() == 0) {
				nombreEmpresa = "Razón Social en blancos en B.D.";
			}
		} catch (Exception e) {
			e.printStackTrace();
			nombreEmpresa = "";
		} finally {
			session.close();
		}

		return nombreEmpresa;
	}

    
    public String funValidaDV(String xRUT) {
		RutService rutService = new RutServiceImpl();

		String xValidaDV = rutService.validate(xRUT) ? "S" : "N";
    	
    	return xValidaDV;
    }

}
