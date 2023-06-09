/**
 * EvaluadorModelosSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.ais_int.schemas.soap.AIS_Rules_WebServices.EvaluadorModelos;

public interface EvaluadorModelosSoap extends java.rmi.Remote {

    /**
     * Evalua un modelo, a partir de su ID y de los datos a evaluar.
     * Devuelve el resultado de la evaluación
     */
    public java.lang.String evaluarMotorGMR(int idModelo, java.lang.String contenidoPeticion) throws java.rmi.RemoteException;
}
