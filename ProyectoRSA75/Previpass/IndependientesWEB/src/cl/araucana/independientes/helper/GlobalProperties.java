/**
 * @(#)GlobalProperties.java 2009
 *
 * Copyright 2009 Telef&oacute;nica M&oacute;viles Soluciones y Aplicaciones, SA.
 * Av. Del Condor 720, Huechuraba, Santiago de Chile, Chile
 * Todos los derechos reservados.
 *
 * Este software es informaci&oacute;n propietaria y confidencial de TM-mAs SA.
 * Usted no debe develar tal información y s&oactute;lo debe usarla en concordancia
 * con los t&eacute;rminos de derechos de licencias que sean adquiridos con TM-mAs.
 *
 */

package cl.araucana.independientes.helper;

/**
 * La clase GlobalProperties provee unos método de acceso a valores de 
 * propiedades contenidas en un archivo interno y externo para el proyecto
 * Archivo interno: cl/araucana/independientes/resources/ApplicationResources.properties
 * Archivo externo: <user.dir>/archivos/independientes/propiedades/IndependientesWEB.properties
 *
 * @author  Everis
 * @version 1.0, 13/09/2010
 * @cambio  Versión Inicial
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

public final class GlobalProperties {

    private static GlobalProperties instance;
    //private static String propertiesExterno = null;//"/archivos/independientes/propiedades/IndependientesWEB.properties";
    private static String propertiesExterno = "/archivos/independientes/propiedades/IndependientesWEB.properties";
    private ResourceBundle recurso;

    //--------------------------------------------------------------------------
    private GlobalProperties() {
        Properties propertieExterno = new Properties();
        FileInputStream propFile = null;
        try {
            propFile = new FileInputStream(System.getProperty("user.dir") + propertiesExterno);
            propertieExterno.load(propFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final String archivoRecurso = propertieExterno.getProperty("GE.properties.interno.GE");
        recurso = ResourceBundle.getBundle(archivoRecurso);
    }
    
    // --------------------------------------------------------------------------
    /*private GlobalProperties() {
        Properties propertieExterno = new Properties();
        FileInputStream propFile = null;
        try {
        	propertiesExterno = getClass().getResource("cl/araucana/independientes/resources/IndependientesWEB.properties").getFile();
            propFile = new FileInputStream(propertiesExterno); //System.getProperty("user.dir") + propertiesExterno);
            propertieExterno.load(propFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final String archivoRecurso = propertieExterno.getProperty("GE.properties.interno.GE");
        recurso = ResourceBundle.getBundle(archivoRecurso);
    }*/

    // --------------------------------------------------------------------------
    public static synchronized GlobalProperties getInstance() {
        if (instance == null) {
            instance = new GlobalProperties();
        }
        return instance;
    }

    public String getValor(String valorClave) {
        try {
            String valor = this.recurso.getString(valorClave);
            return valor;
        } catch (Exception e) {
            return null;
        }
    }

    public int getValorInt(String valorClave) {
        try {
            int valor = Integer.parseInt(this.recurso.getString(valorClave));
            return valor;
        } catch (Exception e) {
            return 0;
        }
    }
    //
    public String getValorExterno(String str) {
        Properties propertieExterno = new Properties();
        FileInputStream propFile = null;
        try {
        	propFile = new FileInputStream(System.getProperty("user.dir") + propertiesExterno);
            propertieExterno.load(propFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertieExterno.getProperty(str);
    }

    /*
    public String getValorExterno(String str) {
        Properties propertieExterno = new Properties();
        FileInputStream propFile = null;
        try {
        	propertiesExterno = getClass().getResource("cl/araucana/independientes/resources/IndependientesWEB.properties").getFile();
        	propFile = new FileInputStream(propertiesExterno);
        	//propFile = new FileInputStream(System.getProperty("user.dir") + propertiesExterno);
            propertieExterno.load(propFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertieExterno.getProperty(str);
    }*/
	
}
