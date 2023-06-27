/**
 * 
 */
package conector.lme.ws.cliente.operador;

import java.math.BigInteger;

import wwwLmeGovClLme.CTZONA0;
import wwwLmeGovClLme.CTZONAA;
import wwwLmeGovClLme.CTZONAB;
import wwwLmeGovClLme.CTZONAC;
import wwwLmeGovClLme.CTZONAD;

/**
 * Representa la respuesta a la consulta del detalle de una licencia.
 * 
 * @author amartoq@microsystem.cl
 */
public class RespuestaDetalleLicencia {
    /** Serial Version UID. */
    private static final long serialVersionUID = 1L;

    private BigInteger numLicencia;

    private String digLicencia;

    private CTZONA0 zona0;

    private CTZONAA zonaA;

    private CTZONAB zonaB;

    private CTZONAC zonaC;

    private CTZONAD[] zonaD;

    private String xmlLME;

    /**
     * @param numLicencia
     * @param digLicencia
     * @param zonaA
     * @param zonaB
     * @param zonaC
     */
    public RespuestaDetalleLicencia(BigInteger numLicencia, String digLicencia, CTZONA0 zona0, CTZONAA zonaA, CTZONAB zonaB,
            CTZONAC zonaC, CTZONAD[] zonaD, String xmlLME) {
        super();
        this.numLicencia = numLicencia;
        this.digLicencia = digLicencia;
        this.zona0 = zona0;
        this.zonaA = zonaA;
        this.zonaB = zonaB;
        this.zonaC = zonaC;
        this.zonaD = zonaD;
        this.xmlLME = xmlLME;
    }

    /**
     * @return the numLicencia
     */
    public BigInteger getNumLicencia() {
        return numLicencia;
    }

    /**
     * @return the digLicencia
     */
    public String getDigLicencia() {
        return digLicencia;
    }

    /**
     * @return the zona0
     */
    public CTZONA0 getZona0() {
        return zona0;
    }

    /**
     * @return the zonaA
     */
    public CTZONAA getZonaA() {
        return zonaA;
    }

    /**
     * @return the zonaB
     */
    public CTZONAB getZonaB() {
        return zonaB;
    }

    /**
     * @return the zonaC
     */
    public CTZONAC getZonaC() {
        return zonaC;
    }

    /**
     * @return the zonaD
     */
    public CTZONAD[] getZonaD() {
        return zonaD;
    }

    /**
     * @return the xmlLME
     */
    public String getXmlLME() {
        return xmlLME;
    }
}
