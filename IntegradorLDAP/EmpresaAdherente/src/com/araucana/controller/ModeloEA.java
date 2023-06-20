/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.araucana.controller;

import java.util.List;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.Iterator;
import com.araucana.controller.mapaEA.*;
import com.araucana.legacy.EmpresaVO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;

/**
 *
 * @author TestGroup
 */
public class ModeloEA {
	private static final Logger log = Logger.getLogger(ModeloEA.class);

    public Map<String, String> getEA1(HttpServletRequest request, String xRUT) throws Exception {
        mapaEA funMapaEA = new mapaEA();
        Map<String, String> mapaEA1 = new TreeMap<String, String>();
        mapaEA1 = funMapaEA.funcionMapaEA(request, xRUT);

        return mapaEA1;

    }
    
    public String funObtieneRol(HttpServletRequest request) throws IOException, UserRegistryException {
    	//1 de 2: Aqui colocar E o A para ejecucion Local
    	//        Aqui colocar A para ejecucion Integrada
    	String xRol = "A";
    	String appIntID = "IntegradorLDAP";
    	
    	log.info("Inicio obtención del Rol.");

    	//2 de 2: Aqui comentar para ejecucion Local
    	///*
		Principal principal = request.getUserPrincipal();

		if ((principal != null) && (principal.getName() != null)) {
			//USUARIO
			String userID = principal.getName();
			log.info("RUT: '" + userID + "'.");

			//USERREGISTRY - Nombre Usuario
			UserRegistryConnection connection = new UserRegistryConnection();
			User user = connection.getUser(userID);
			log.info("Nombre Usuario: '" + user.getFullName() + "'.");

			//ROLES
			List roles = (List)connection.getUserRoles(userID, appIntID);
			if (roles.size() > 0) {
				log.info("Es Rol Ejecutivo. Tiene " + roles.size() + " roles.");
				xRol = "E";
			} else {
				log.info("No tiene Roles. Es Rol Administrativo entonces.");
				xRol = "A";
			}
		} else {
			log.info("(principal = null) o bien, (principal.getName() == null). Se asume Rol Administrativo.");
			xRol = "A";			
		}
		//*/
		//Fin comentar
		
		if (xRol != null) {
			if (xRol.equals("")) {
				xRol = "A";
				log.info("###funObtieneRol: Rol venia en blanco. Se asume Administrador");
			}
		} else {
			xRol = "A";
			log.info("###funObtieneRol: Rol venia con valor null. Se asume Administrador");
		}
		
		log.info("Rol obtenido: " + xRol);
		log.info("Fin obtener Rol.");
    	
    	return xRol;
    }
}
