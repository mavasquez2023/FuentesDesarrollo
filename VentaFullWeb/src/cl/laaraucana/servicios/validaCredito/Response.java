/**
 * Response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicios.validaCredito;

public class Response  implements java.io.Serializable {
    private int codigoRespuesta;

    private java.lang.String RUTAFILIADO;

    private java.lang.String RUTEMPRESA;

    private java.lang.String AFILIADOVIGENTE;

    private double RIESGOTOTAL;

    private java.lang.String VECESRENTA;

    private java.lang.String SUPERAVECESRENTA;

    private java.lang.String MAXIMOENDEUDAMIENTO;

    private double PORCENTAJEENDEUDAMIENTO;

    private java.lang.String SUPERAMAXENDEUDAMIENTONORMATIVO;

    private java.lang.String FUERARANGOEDAD;

    private java.lang.String FUERARANGOANTIGUEDAD;

    private java.lang.String PLAZOCREDITOEDAD;

    private java.lang.String AFILIADOPEP;

    private java.lang.String ESTADOINSOLVENCIA;

    private java.lang.String AFILBLOQUEOALCREDITO;

    private java.lang.String EMPBLOQUEOALCREDITO;

    private java.lang.String DEUDAINTERCAJA;

    private java.lang.String LICENCIAACTIVA;

    private java.lang.String DIASLICENCIA;

    private java.lang.String CREDITOSCASTIGASDOS;

    private java.lang.String CREDITOSAFPPROVIDA;

    private java.lang.String CREDITOMOROSIDAD;

    private java.lang.String PENSIONADOAFP;

    private java.lang.String AFILIADOPUBLICO15;

    private java.lang.String FUNCIONARIOCAJA;

    private java.lang.String DATOSOBLIGATORIOS;

    private java.lang.String SUPERATASAPIZARRA;

    private java.lang.String TASAMENSUALCERO;

    private java.lang.String CALIFICA_UCHILE;

    private java.lang.String EXITOSINACOFI;

    private java.lang.String AFILIADOMANTENEDOR;

    private java.lang.String EMPRESAMANTENEDOR;

    private java.lang.String SEXO;

    private java.lang.String RIESGOEMPRESA;

    private java.lang.String FECHANACIMIENTO;

    private java.lang.String AFILCREDITOREPROVIGENTE;

    private java.lang.String PENSIONADOPBS;

    private java.lang.String AFILCAMPREPROVIGENTE;

    private double PERIODO1;

    private double MONTO1;

    private double PERIODO2;

    private double MONTO2;

    private double PERIODO3;

    private double MONTO3;

    private double PERIODO4;

    private double MONTO4;

    private double PERIODO5;

    private double MONTO5;

    private double PERIODO6;

    private double MONTO6;

    private double PERIODO7;

    private double MONTO7;

    private double PERIODO8;

    private double MONTO8;

    private double PERIODO9;

    private double MONTO9;

    private double PERIODO10;

    private double MONTO10;

    private double PERIODO11;

    private double MONTO11;

    private double PERIODO12;

    private double MONTO12;

    private double PERIODO13;

    private double MONTO13;

    private double PERIODO14;

    private double MONTO14;

    private double PERIODO15;

    private double MONTO15;

    private double PERIODO16;

    private double MONTO16;

    private double PERIODO17;

    private double MONTO17;

    private double PERIODO18;

    private double MONTO18;

    private java.lang.String SEGMENTO;

    private java.lang.String FECHAFALLECIMIENTO;

    private java.lang.String FECHACONTRATO;

    private java.lang.String ESTADOCIVIL;

    private java.lang.String TIPOCONTRATO;

    private java.lang.String DIRECCION;

    private java.lang.String NUMERO;

    private java.lang.String DEPTO;

    private java.lang.String VILLAPOBLACION;

    private java.lang.String CODIGOREGION;

    private java.lang.String CODIGOPROVINCIA;

    private java.lang.String CODIGOCOMUNA;

    private java.lang.String EMAIL;

    private java.lang.String CELULAR;

    private java.lang.String TELEFONO;

    private java.lang.String NUMEROCUENTA;

    private java.lang.String TIPOCUENTA;

    private java.lang.String CODIGOBANCO;

    private java.lang.String DECLARACIONJURADA;

    private java.lang.String NACIONALIDAD;

    public Response() {
    }

    public Response(
           int codigoRespuesta,
           java.lang.String RUTAFILIADO,
           java.lang.String RUTEMPRESA,
           java.lang.String AFILIADOVIGENTE,
           double RIESGOTOTAL,
           java.lang.String VECESRENTA,
           java.lang.String SUPERAVECESRENTA,
           java.lang.String MAXIMOENDEUDAMIENTO,
           double PORCENTAJEENDEUDAMIENTO,
           java.lang.String SUPERAMAXENDEUDAMIENTONORMATIVO,
           java.lang.String FUERARANGOEDAD,
           java.lang.String FUERARANGOANTIGUEDAD,
           java.lang.String PLAZOCREDITOEDAD,
           java.lang.String AFILIADOPEP,
           java.lang.String ESTADOINSOLVENCIA,
           java.lang.String AFILBLOQUEOALCREDITO,
           java.lang.String EMPBLOQUEOALCREDITO,
           java.lang.String DEUDAINTERCAJA,
           java.lang.String LICENCIAACTIVA,
           java.lang.String DIASLICENCIA,
           java.lang.String CREDITOSCASTIGASDOS,
           java.lang.String CREDITOSAFPPROVIDA,
           java.lang.String CREDITOMOROSIDAD,
           java.lang.String PENSIONADOAFP,
           java.lang.String AFILIADOPUBLICO15,
           java.lang.String FUNCIONARIOCAJA,
           java.lang.String DATOSOBLIGATORIOS,
           java.lang.String SUPERATASAPIZARRA,
           java.lang.String TASAMENSUALCERO,
           java.lang.String CALIFICA_UCHILE,
           java.lang.String EXITOSINACOFI,
           java.lang.String AFILIADOMANTENEDOR,
           java.lang.String EMPRESAMANTENEDOR,
           java.lang.String SEXO,
           java.lang.String RIESGOEMPRESA,
           java.lang.String FECHANACIMIENTO,
           java.lang.String AFILCREDITOREPROVIGENTE,
           java.lang.String PENSIONADOPBS,
           java.lang.String AFILCAMPREPROVIGENTE,
           double PERIODO1,
           double MONTO1,
           double PERIODO2,
           double MONTO2,
           double PERIODO3,
           double MONTO3,
           double PERIODO4,
           double MONTO4,
           double PERIODO5,
           double MONTO5,
           double PERIODO6,
           double MONTO6,
           double PERIODO7,
           double MONTO7,
           double PERIODO8,
           double MONTO8,
           double PERIODO9,
           double MONTO9,
           double PERIODO10,
           double MONTO10,
           double PERIODO11,
           double MONTO11,
           double PERIODO12,
           double MONTO12,
           double PERIODO13,
           double MONTO13,
           double PERIODO14,
           double MONTO14,
           double PERIODO15,
           double MONTO15,
           double PERIODO16,
           double MONTO16,
           double PERIODO17,
           double MONTO17,
           double PERIODO18,
           double MONTO18,
           java.lang.String SEGMENTO,
           java.lang.String FECHAFALLECIMIENTO,
           java.lang.String FECHACONTRATO,
           java.lang.String ESTADOCIVIL,
           java.lang.String TIPOCONTRATO,
           java.lang.String DIRECCION,
           java.lang.String NUMERO,
           java.lang.String DEPTO,
           java.lang.String VILLAPOBLACION,
           java.lang.String CODIGOREGION,
           java.lang.String CODIGOPROVINCIA,
           java.lang.String CODIGOCOMUNA,
           java.lang.String EMAIL,
           java.lang.String CELULAR,
           java.lang.String TELEFONO,
           java.lang.String NUMEROCUENTA,
           java.lang.String TIPOCUENTA,
           java.lang.String CODIGOBANCO,
           java.lang.String DECLARACIONJURADA,
           java.lang.String NACIONALIDAD) {
           this.codigoRespuesta = codigoRespuesta;
           this.RUTAFILIADO = RUTAFILIADO;
           this.RUTEMPRESA = RUTEMPRESA;
           this.AFILIADOVIGENTE = AFILIADOVIGENTE;
           this.RIESGOTOTAL = RIESGOTOTAL;
           this.VECESRENTA = VECESRENTA;
           this.SUPERAVECESRENTA = SUPERAVECESRENTA;
           this.MAXIMOENDEUDAMIENTO = MAXIMOENDEUDAMIENTO;
           this.PORCENTAJEENDEUDAMIENTO = PORCENTAJEENDEUDAMIENTO;
           this.SUPERAMAXENDEUDAMIENTONORMATIVO = SUPERAMAXENDEUDAMIENTONORMATIVO;
           this.FUERARANGOEDAD = FUERARANGOEDAD;
           this.FUERARANGOANTIGUEDAD = FUERARANGOANTIGUEDAD;
           this.PLAZOCREDITOEDAD = PLAZOCREDITOEDAD;
           this.AFILIADOPEP = AFILIADOPEP;
           this.ESTADOINSOLVENCIA = ESTADOINSOLVENCIA;
           this.AFILBLOQUEOALCREDITO = AFILBLOQUEOALCREDITO;
           this.EMPBLOQUEOALCREDITO = EMPBLOQUEOALCREDITO;
           this.DEUDAINTERCAJA = DEUDAINTERCAJA;
           this.LICENCIAACTIVA = LICENCIAACTIVA;
           this.DIASLICENCIA = DIASLICENCIA;
           this.CREDITOSCASTIGASDOS = CREDITOSCASTIGASDOS;
           this.CREDITOSAFPPROVIDA = CREDITOSAFPPROVIDA;
           this.CREDITOMOROSIDAD = CREDITOMOROSIDAD;
           this.PENSIONADOAFP = PENSIONADOAFP;
           this.AFILIADOPUBLICO15 = AFILIADOPUBLICO15;
           this.FUNCIONARIOCAJA = FUNCIONARIOCAJA;
           this.DATOSOBLIGATORIOS = DATOSOBLIGATORIOS;
           this.SUPERATASAPIZARRA = SUPERATASAPIZARRA;
           this.TASAMENSUALCERO = TASAMENSUALCERO;
           this.CALIFICA_UCHILE = CALIFICA_UCHILE;
           this.EXITOSINACOFI = EXITOSINACOFI;
           this.AFILIADOMANTENEDOR = AFILIADOMANTENEDOR;
           this.EMPRESAMANTENEDOR = EMPRESAMANTENEDOR;
           this.SEXO = SEXO;
           this.RIESGOEMPRESA = RIESGOEMPRESA;
           this.FECHANACIMIENTO = FECHANACIMIENTO;
           this.AFILCREDITOREPROVIGENTE = AFILCREDITOREPROVIGENTE;
           this.PENSIONADOPBS = PENSIONADOPBS;
           this.AFILCAMPREPROVIGENTE = AFILCAMPREPROVIGENTE;
           this.PERIODO1 = PERIODO1;
           this.MONTO1 = MONTO1;
           this.PERIODO2 = PERIODO2;
           this.MONTO2 = MONTO2;
           this.PERIODO3 = PERIODO3;
           this.MONTO3 = MONTO3;
           this.PERIODO4 = PERIODO4;
           this.MONTO4 = MONTO4;
           this.PERIODO5 = PERIODO5;
           this.MONTO5 = MONTO5;
           this.PERIODO6 = PERIODO6;
           this.MONTO6 = MONTO6;
           this.PERIODO7 = PERIODO7;
           this.MONTO7 = MONTO7;
           this.PERIODO8 = PERIODO8;
           this.MONTO8 = MONTO8;
           this.PERIODO9 = PERIODO9;
           this.MONTO9 = MONTO9;
           this.PERIODO10 = PERIODO10;
           this.MONTO10 = MONTO10;
           this.PERIODO11 = PERIODO11;
           this.MONTO11 = MONTO11;
           this.PERIODO12 = PERIODO12;
           this.MONTO12 = MONTO12;
           this.PERIODO13 = PERIODO13;
           this.MONTO13 = MONTO13;
           this.PERIODO14 = PERIODO14;
           this.MONTO14 = MONTO14;
           this.PERIODO15 = PERIODO15;
           this.MONTO15 = MONTO15;
           this.PERIODO16 = PERIODO16;
           this.MONTO16 = MONTO16;
           this.PERIODO17 = PERIODO17;
           this.MONTO17 = MONTO17;
           this.PERIODO18 = PERIODO18;
           this.MONTO18 = MONTO18;
           this.SEGMENTO = SEGMENTO;
           this.FECHAFALLECIMIENTO = FECHAFALLECIMIENTO;
           this.FECHACONTRATO = FECHACONTRATO;
           this.ESTADOCIVIL = ESTADOCIVIL;
           this.TIPOCONTRATO = TIPOCONTRATO;
           this.DIRECCION = DIRECCION;
           this.NUMERO = NUMERO;
           this.DEPTO = DEPTO;
           this.VILLAPOBLACION = VILLAPOBLACION;
           this.CODIGOREGION = CODIGOREGION;
           this.CODIGOPROVINCIA = CODIGOPROVINCIA;
           this.CODIGOCOMUNA = CODIGOCOMUNA;
           this.EMAIL = EMAIL;
           this.CELULAR = CELULAR;
           this.TELEFONO = TELEFONO;
           this.NUMEROCUENTA = NUMEROCUENTA;
           this.TIPOCUENTA = TIPOCUENTA;
           this.CODIGOBANCO = CODIGOBANCO;
           this.DECLARACIONJURADA = DECLARACIONJURADA;
           this.NACIONALIDAD = NACIONALIDAD;
    }


    /**
     * Gets the codigoRespuesta value for this Response.
     * 
     * @return codigoRespuesta
     */
    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }


    /**
     * Sets the codigoRespuesta value for this Response.
     * 
     * @param codigoRespuesta
     */
    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }


    /**
     * Gets the RUTAFILIADO value for this Response.
     * 
     * @return RUTAFILIADO
     */
    public java.lang.String getRUTAFILIADO() {
        return RUTAFILIADO;
    }


    /**
     * Sets the RUTAFILIADO value for this Response.
     * 
     * @param RUTAFILIADO
     */
    public void setRUTAFILIADO(java.lang.String RUTAFILIADO) {
        this.RUTAFILIADO = RUTAFILIADO;
    }


    /**
     * Gets the RUTEMPRESA value for this Response.
     * 
     * @return RUTEMPRESA
     */
    public java.lang.String getRUTEMPRESA() {
        return RUTEMPRESA;
    }


    /**
     * Sets the RUTEMPRESA value for this Response.
     * 
     * @param RUTEMPRESA
     */
    public void setRUTEMPRESA(java.lang.String RUTEMPRESA) {
        this.RUTEMPRESA = RUTEMPRESA;
    }


    /**
     * Gets the AFILIADOVIGENTE value for this Response.
     * 
     * @return AFILIADOVIGENTE
     */
    public java.lang.String getAFILIADOVIGENTE() {
        return AFILIADOVIGENTE;
    }


    /**
     * Sets the AFILIADOVIGENTE value for this Response.
     * 
     * @param AFILIADOVIGENTE
     */
    public void setAFILIADOVIGENTE(java.lang.String AFILIADOVIGENTE) {
        this.AFILIADOVIGENTE = AFILIADOVIGENTE;
    }


    /**
     * Gets the RIESGOTOTAL value for this Response.
     * 
     * @return RIESGOTOTAL
     */
    public double getRIESGOTOTAL() {
        return RIESGOTOTAL;
    }


    /**
     * Sets the RIESGOTOTAL value for this Response.
     * 
     * @param RIESGOTOTAL
     */
    public void setRIESGOTOTAL(double RIESGOTOTAL) {
        this.RIESGOTOTAL = RIESGOTOTAL;
    }


    /**
     * Gets the VECESRENTA value for this Response.
     * 
     * @return VECESRENTA
     */
    public java.lang.String getVECESRENTA() {
        return VECESRENTA;
    }


    /**
     * Sets the VECESRENTA value for this Response.
     * 
     * @param VECESRENTA
     */
    public void setVECESRENTA(java.lang.String VECESRENTA) {
        this.VECESRENTA = VECESRENTA;
    }


    /**
     * Gets the SUPERAVECESRENTA value for this Response.
     * 
     * @return SUPERAVECESRENTA
     */
    public java.lang.String getSUPERAVECESRENTA() {
        return SUPERAVECESRENTA;
    }


    /**
     * Sets the SUPERAVECESRENTA value for this Response.
     * 
     * @param SUPERAVECESRENTA
     */
    public void setSUPERAVECESRENTA(java.lang.String SUPERAVECESRENTA) {
        this.SUPERAVECESRENTA = SUPERAVECESRENTA;
    }


    /**
     * Gets the MAXIMOENDEUDAMIENTO value for this Response.
     * 
     * @return MAXIMOENDEUDAMIENTO
     */
    public java.lang.String getMAXIMOENDEUDAMIENTO() {
        return MAXIMOENDEUDAMIENTO;
    }


    /**
     * Sets the MAXIMOENDEUDAMIENTO value for this Response.
     * 
     * @param MAXIMOENDEUDAMIENTO
     */
    public void setMAXIMOENDEUDAMIENTO(java.lang.String MAXIMOENDEUDAMIENTO) {
        this.MAXIMOENDEUDAMIENTO = MAXIMOENDEUDAMIENTO;
    }


    /**
     * Gets the PORCENTAJEENDEUDAMIENTO value for this Response.
     * 
     * @return PORCENTAJEENDEUDAMIENTO
     */
    public double getPORCENTAJEENDEUDAMIENTO() {
        return PORCENTAJEENDEUDAMIENTO;
    }


    /**
     * Sets the PORCENTAJEENDEUDAMIENTO value for this Response.
     * 
     * @param PORCENTAJEENDEUDAMIENTO
     */
    public void setPORCENTAJEENDEUDAMIENTO(double PORCENTAJEENDEUDAMIENTO) {
        this.PORCENTAJEENDEUDAMIENTO = PORCENTAJEENDEUDAMIENTO;
    }


    /**
     * Gets the SUPERAMAXENDEUDAMIENTONORMATIVO value for this Response.
     * 
     * @return SUPERAMAXENDEUDAMIENTONORMATIVO
     */
    public java.lang.String getSUPERAMAXENDEUDAMIENTONORMATIVO() {
        return SUPERAMAXENDEUDAMIENTONORMATIVO;
    }


    /**
     * Sets the SUPERAMAXENDEUDAMIENTONORMATIVO value for this Response.
     * 
     * @param SUPERAMAXENDEUDAMIENTONORMATIVO
     */
    public void setSUPERAMAXENDEUDAMIENTONORMATIVO(java.lang.String SUPERAMAXENDEUDAMIENTONORMATIVO) {
        this.SUPERAMAXENDEUDAMIENTONORMATIVO = SUPERAMAXENDEUDAMIENTONORMATIVO;
    }


    /**
     * Gets the FUERARANGOEDAD value for this Response.
     * 
     * @return FUERARANGOEDAD
     */
    public java.lang.String getFUERARANGOEDAD() {
        return FUERARANGOEDAD;
    }


    /**
     * Sets the FUERARANGOEDAD value for this Response.
     * 
     * @param FUERARANGOEDAD
     */
    public void setFUERARANGOEDAD(java.lang.String FUERARANGOEDAD) {
        this.FUERARANGOEDAD = FUERARANGOEDAD;
    }


    /**
     * Gets the FUERARANGOANTIGUEDAD value for this Response.
     * 
     * @return FUERARANGOANTIGUEDAD
     */
    public java.lang.String getFUERARANGOANTIGUEDAD() {
        return FUERARANGOANTIGUEDAD;
    }


    /**
     * Sets the FUERARANGOANTIGUEDAD value for this Response.
     * 
     * @param FUERARANGOANTIGUEDAD
     */
    public void setFUERARANGOANTIGUEDAD(java.lang.String FUERARANGOANTIGUEDAD) {
        this.FUERARANGOANTIGUEDAD = FUERARANGOANTIGUEDAD;
    }


    /**
     * Gets the PLAZOCREDITOEDAD value for this Response.
     * 
     * @return PLAZOCREDITOEDAD
     */
    public java.lang.String getPLAZOCREDITOEDAD() {
        return PLAZOCREDITOEDAD;
    }


    /**
     * Sets the PLAZOCREDITOEDAD value for this Response.
     * 
     * @param PLAZOCREDITOEDAD
     */
    public void setPLAZOCREDITOEDAD(java.lang.String PLAZOCREDITOEDAD) {
        this.PLAZOCREDITOEDAD = PLAZOCREDITOEDAD;
    }


    /**
     * Gets the AFILIADOPEP value for this Response.
     * 
     * @return AFILIADOPEP
     */
    public java.lang.String getAFILIADOPEP() {
        return AFILIADOPEP;
    }


    /**
     * Sets the AFILIADOPEP value for this Response.
     * 
     * @param AFILIADOPEP
     */
    public void setAFILIADOPEP(java.lang.String AFILIADOPEP) {
        this.AFILIADOPEP = AFILIADOPEP;
    }


    /**
     * Gets the ESTADOINSOLVENCIA value for this Response.
     * 
     * @return ESTADOINSOLVENCIA
     */
    public java.lang.String getESTADOINSOLVENCIA() {
        return ESTADOINSOLVENCIA;
    }


    /**
     * Sets the ESTADOINSOLVENCIA value for this Response.
     * 
     * @param ESTADOINSOLVENCIA
     */
    public void setESTADOINSOLVENCIA(java.lang.String ESTADOINSOLVENCIA) {
        this.ESTADOINSOLVENCIA = ESTADOINSOLVENCIA;
    }


    /**
     * Gets the AFILBLOQUEOALCREDITO value for this Response.
     * 
     * @return AFILBLOQUEOALCREDITO
     */
    public java.lang.String getAFILBLOQUEOALCREDITO() {
        return AFILBLOQUEOALCREDITO;
    }


    /**
     * Sets the AFILBLOQUEOALCREDITO value for this Response.
     * 
     * @param AFILBLOQUEOALCREDITO
     */
    public void setAFILBLOQUEOALCREDITO(java.lang.String AFILBLOQUEOALCREDITO) {
        this.AFILBLOQUEOALCREDITO = AFILBLOQUEOALCREDITO;
    }


    /**
     * Gets the EMPBLOQUEOALCREDITO value for this Response.
     * 
     * @return EMPBLOQUEOALCREDITO
     */
    public java.lang.String getEMPBLOQUEOALCREDITO() {
        return EMPBLOQUEOALCREDITO;
    }


    /**
     * Sets the EMPBLOQUEOALCREDITO value for this Response.
     * 
     * @param EMPBLOQUEOALCREDITO
     */
    public void setEMPBLOQUEOALCREDITO(java.lang.String EMPBLOQUEOALCREDITO) {
        this.EMPBLOQUEOALCREDITO = EMPBLOQUEOALCREDITO;
    }


    /**
     * Gets the DEUDAINTERCAJA value for this Response.
     * 
     * @return DEUDAINTERCAJA
     */
    public java.lang.String getDEUDAINTERCAJA() {
        return DEUDAINTERCAJA;
    }


    /**
     * Sets the DEUDAINTERCAJA value for this Response.
     * 
     * @param DEUDAINTERCAJA
     */
    public void setDEUDAINTERCAJA(java.lang.String DEUDAINTERCAJA) {
        this.DEUDAINTERCAJA = DEUDAINTERCAJA;
    }


    /**
     * Gets the LICENCIAACTIVA value for this Response.
     * 
     * @return LICENCIAACTIVA
     */
    public java.lang.String getLICENCIAACTIVA() {
        return LICENCIAACTIVA;
    }


    /**
     * Sets the LICENCIAACTIVA value for this Response.
     * 
     * @param LICENCIAACTIVA
     */
    public void setLICENCIAACTIVA(java.lang.String LICENCIAACTIVA) {
        this.LICENCIAACTIVA = LICENCIAACTIVA;
    }


    /**
     * Gets the DIASLICENCIA value for this Response.
     * 
     * @return DIASLICENCIA
     */
    public java.lang.String getDIASLICENCIA() {
        return DIASLICENCIA;
    }


    /**
     * Sets the DIASLICENCIA value for this Response.
     * 
     * @param DIASLICENCIA
     */
    public void setDIASLICENCIA(java.lang.String DIASLICENCIA) {
        this.DIASLICENCIA = DIASLICENCIA;
    }


    /**
     * Gets the CREDITOSCASTIGASDOS value for this Response.
     * 
     * @return CREDITOSCASTIGASDOS
     */
    public java.lang.String getCREDITOSCASTIGASDOS() {
        return CREDITOSCASTIGASDOS;
    }


    /**
     * Sets the CREDITOSCASTIGASDOS value for this Response.
     * 
     * @param CREDITOSCASTIGASDOS
     */
    public void setCREDITOSCASTIGASDOS(java.lang.String CREDITOSCASTIGASDOS) {
        this.CREDITOSCASTIGASDOS = CREDITOSCASTIGASDOS;
    }


    /**
     * Gets the CREDITOSAFPPROVIDA value for this Response.
     * 
     * @return CREDITOSAFPPROVIDA
     */
    public java.lang.String getCREDITOSAFPPROVIDA() {
        return CREDITOSAFPPROVIDA;
    }


    /**
     * Sets the CREDITOSAFPPROVIDA value for this Response.
     * 
     * @param CREDITOSAFPPROVIDA
     */
    public void setCREDITOSAFPPROVIDA(java.lang.String CREDITOSAFPPROVIDA) {
        this.CREDITOSAFPPROVIDA = CREDITOSAFPPROVIDA;
    }


    /**
     * Gets the CREDITOMOROSIDAD value for this Response.
     * 
     * @return CREDITOMOROSIDAD
     */
    public java.lang.String getCREDITOMOROSIDAD() {
        return CREDITOMOROSIDAD;
    }


    /**
     * Sets the CREDITOMOROSIDAD value for this Response.
     * 
     * @param CREDITOMOROSIDAD
     */
    public void setCREDITOMOROSIDAD(java.lang.String CREDITOMOROSIDAD) {
        this.CREDITOMOROSIDAD = CREDITOMOROSIDAD;
    }


    /**
     * Gets the PENSIONADOAFP value for this Response.
     * 
     * @return PENSIONADOAFP
     */
    public java.lang.String getPENSIONADOAFP() {
        return PENSIONADOAFP;
    }


    /**
     * Sets the PENSIONADOAFP value for this Response.
     * 
     * @param PENSIONADOAFP
     */
    public void setPENSIONADOAFP(java.lang.String PENSIONADOAFP) {
        this.PENSIONADOAFP = PENSIONADOAFP;
    }


    /**
     * Gets the AFILIADOPUBLICO15 value for this Response.
     * 
     * @return AFILIADOPUBLICO15
     */
    public java.lang.String getAFILIADOPUBLICO15() {
        return AFILIADOPUBLICO15;
    }


    /**
     * Sets the AFILIADOPUBLICO15 value for this Response.
     * 
     * @param AFILIADOPUBLICO15
     */
    public void setAFILIADOPUBLICO15(java.lang.String AFILIADOPUBLICO15) {
        this.AFILIADOPUBLICO15 = AFILIADOPUBLICO15;
    }


    /**
     * Gets the FUNCIONARIOCAJA value for this Response.
     * 
     * @return FUNCIONARIOCAJA
     */
    public java.lang.String getFUNCIONARIOCAJA() {
        return FUNCIONARIOCAJA;
    }


    /**
     * Sets the FUNCIONARIOCAJA value for this Response.
     * 
     * @param FUNCIONARIOCAJA
     */
    public void setFUNCIONARIOCAJA(java.lang.String FUNCIONARIOCAJA) {
        this.FUNCIONARIOCAJA = FUNCIONARIOCAJA;
    }


    /**
     * Gets the DATOSOBLIGATORIOS value for this Response.
     * 
     * @return DATOSOBLIGATORIOS
     */
    public java.lang.String getDATOSOBLIGATORIOS() {
        return DATOSOBLIGATORIOS;
    }


    /**
     * Sets the DATOSOBLIGATORIOS value for this Response.
     * 
     * @param DATOSOBLIGATORIOS
     */
    public void setDATOSOBLIGATORIOS(java.lang.String DATOSOBLIGATORIOS) {
        this.DATOSOBLIGATORIOS = DATOSOBLIGATORIOS;
    }


    /**
     * Gets the SUPERATASAPIZARRA value for this Response.
     * 
     * @return SUPERATASAPIZARRA
     */
    public java.lang.String getSUPERATASAPIZARRA() {
        return SUPERATASAPIZARRA;
    }


    /**
     * Sets the SUPERATASAPIZARRA value for this Response.
     * 
     * @param SUPERATASAPIZARRA
     */
    public void setSUPERATASAPIZARRA(java.lang.String SUPERATASAPIZARRA) {
        this.SUPERATASAPIZARRA = SUPERATASAPIZARRA;
    }


    /**
     * Gets the TASAMENSUALCERO value for this Response.
     * 
     * @return TASAMENSUALCERO
     */
    public java.lang.String getTASAMENSUALCERO() {
        return TASAMENSUALCERO;
    }


    /**
     * Sets the TASAMENSUALCERO value for this Response.
     * 
     * @param TASAMENSUALCERO
     */
    public void setTASAMENSUALCERO(java.lang.String TASAMENSUALCERO) {
        this.TASAMENSUALCERO = TASAMENSUALCERO;
    }


    /**
     * Gets the CALIFICA_UCHILE value for this Response.
     * 
     * @return CALIFICA_UCHILE
     */
    public java.lang.String getCALIFICA_UCHILE() {
        return CALIFICA_UCHILE;
    }


    /**
     * Sets the CALIFICA_UCHILE value for this Response.
     * 
     * @param CALIFICA_UCHILE
     */
    public void setCALIFICA_UCHILE(java.lang.String CALIFICA_UCHILE) {
        this.CALIFICA_UCHILE = CALIFICA_UCHILE;
    }


    /**
     * Gets the EXITOSINACOFI value for this Response.
     * 
     * @return EXITOSINACOFI
     */
    public java.lang.String getEXITOSINACOFI() {
        return EXITOSINACOFI;
    }


    /**
     * Sets the EXITOSINACOFI value for this Response.
     * 
     * @param EXITOSINACOFI
     */
    public void setEXITOSINACOFI(java.lang.String EXITOSINACOFI) {
        this.EXITOSINACOFI = EXITOSINACOFI;
    }


    /**
     * Gets the AFILIADOMANTENEDOR value for this Response.
     * 
     * @return AFILIADOMANTENEDOR
     */
    public java.lang.String getAFILIADOMANTENEDOR() {
        return AFILIADOMANTENEDOR;
    }


    /**
     * Sets the AFILIADOMANTENEDOR value for this Response.
     * 
     * @param AFILIADOMANTENEDOR
     */
    public void setAFILIADOMANTENEDOR(java.lang.String AFILIADOMANTENEDOR) {
        this.AFILIADOMANTENEDOR = AFILIADOMANTENEDOR;
    }


    /**
     * Gets the EMPRESAMANTENEDOR value for this Response.
     * 
     * @return EMPRESAMANTENEDOR
     */
    public java.lang.String getEMPRESAMANTENEDOR() {
        return EMPRESAMANTENEDOR;
    }


    /**
     * Sets the EMPRESAMANTENEDOR value for this Response.
     * 
     * @param EMPRESAMANTENEDOR
     */
    public void setEMPRESAMANTENEDOR(java.lang.String EMPRESAMANTENEDOR) {
        this.EMPRESAMANTENEDOR = EMPRESAMANTENEDOR;
    }


    /**
     * Gets the SEXO value for this Response.
     * 
     * @return SEXO
     */
    public java.lang.String getSEXO() {
        return SEXO;
    }


    /**
     * Sets the SEXO value for this Response.
     * 
     * @param SEXO
     */
    public void setSEXO(java.lang.String SEXO) {
        this.SEXO = SEXO;
    }


    /**
     * Gets the RIESGOEMPRESA value for this Response.
     * 
     * @return RIESGOEMPRESA
     */
    public java.lang.String getRIESGOEMPRESA() {
        return RIESGOEMPRESA;
    }


    /**
     * Sets the RIESGOEMPRESA value for this Response.
     * 
     * @param RIESGOEMPRESA
     */
    public void setRIESGOEMPRESA(java.lang.String RIESGOEMPRESA) {
        this.RIESGOEMPRESA = RIESGOEMPRESA;
    }


    /**
     * Gets the FECHANACIMIENTO value for this Response.
     * 
     * @return FECHANACIMIENTO
     */
    public java.lang.String getFECHANACIMIENTO() {
        return FECHANACIMIENTO;
    }


    /**
     * Sets the FECHANACIMIENTO value for this Response.
     * 
     * @param FECHANACIMIENTO
     */
    public void setFECHANACIMIENTO(java.lang.String FECHANACIMIENTO) {
        this.FECHANACIMIENTO = FECHANACIMIENTO;
    }


    /**
     * Gets the AFILCREDITOREPROVIGENTE value for this Response.
     * 
     * @return AFILCREDITOREPROVIGENTE
     */
    public java.lang.String getAFILCREDITOREPROVIGENTE() {
        return AFILCREDITOREPROVIGENTE;
    }


    /**
     * Sets the AFILCREDITOREPROVIGENTE value for this Response.
     * 
     * @param AFILCREDITOREPROVIGENTE
     */
    public void setAFILCREDITOREPROVIGENTE(java.lang.String AFILCREDITOREPROVIGENTE) {
        this.AFILCREDITOREPROVIGENTE = AFILCREDITOREPROVIGENTE;
    }


    /**
     * Gets the PENSIONADOPBS value for this Response.
     * 
     * @return PENSIONADOPBS
     */
    public java.lang.String getPENSIONADOPBS() {
        return PENSIONADOPBS;
    }


    /**
     * Sets the PENSIONADOPBS value for this Response.
     * 
     * @param PENSIONADOPBS
     */
    public void setPENSIONADOPBS(java.lang.String PENSIONADOPBS) {
        this.PENSIONADOPBS = PENSIONADOPBS;
    }


    /**
     * Gets the AFILCAMPREPROVIGENTE value for this Response.
     * 
     * @return AFILCAMPREPROVIGENTE
     */
    public java.lang.String getAFILCAMPREPROVIGENTE() {
        return AFILCAMPREPROVIGENTE;
    }


    /**
     * Sets the AFILCAMPREPROVIGENTE value for this Response.
     * 
     * @param AFILCAMPREPROVIGENTE
     */
    public void setAFILCAMPREPROVIGENTE(java.lang.String AFILCAMPREPROVIGENTE) {
        this.AFILCAMPREPROVIGENTE = AFILCAMPREPROVIGENTE;
    }


    /**
     * Gets the PERIODO1 value for this Response.
     * 
     * @return PERIODO1
     */
    public double getPERIODO1() {
        return PERIODO1;
    }


    /**
     * Sets the PERIODO1 value for this Response.
     * 
     * @param PERIODO1
     */
    public void setPERIODO1(double PERIODO1) {
        this.PERIODO1 = PERIODO1;
    }


    /**
     * Gets the MONTO1 value for this Response.
     * 
     * @return MONTO1
     */
    public double getMONTO1() {
        return MONTO1;
    }


    /**
     * Sets the MONTO1 value for this Response.
     * 
     * @param MONTO1
     */
    public void setMONTO1(double MONTO1) {
        this.MONTO1 = MONTO1;
    }


    /**
     * Gets the PERIODO2 value for this Response.
     * 
     * @return PERIODO2
     */
    public double getPERIODO2() {
        return PERIODO2;
    }


    /**
     * Sets the PERIODO2 value for this Response.
     * 
     * @param PERIODO2
     */
    public void setPERIODO2(double PERIODO2) {
        this.PERIODO2 = PERIODO2;
    }


    /**
     * Gets the MONTO2 value for this Response.
     * 
     * @return MONTO2
     */
    public double getMONTO2() {
        return MONTO2;
    }


    /**
     * Sets the MONTO2 value for this Response.
     * 
     * @param MONTO2
     */
    public void setMONTO2(double MONTO2) {
        this.MONTO2 = MONTO2;
    }


    /**
     * Gets the PERIODO3 value for this Response.
     * 
     * @return PERIODO3
     */
    public double getPERIODO3() {
        return PERIODO3;
    }


    /**
     * Sets the PERIODO3 value for this Response.
     * 
     * @param PERIODO3
     */
    public void setPERIODO3(double PERIODO3) {
        this.PERIODO3 = PERIODO3;
    }


    /**
     * Gets the MONTO3 value for this Response.
     * 
     * @return MONTO3
     */
    public double getMONTO3() {
        return MONTO3;
    }


    /**
     * Sets the MONTO3 value for this Response.
     * 
     * @param MONTO3
     */
    public void setMONTO3(double MONTO3) {
        this.MONTO3 = MONTO3;
    }


    /**
     * Gets the PERIODO4 value for this Response.
     * 
     * @return PERIODO4
     */
    public double getPERIODO4() {
        return PERIODO4;
    }


    /**
     * Sets the PERIODO4 value for this Response.
     * 
     * @param PERIODO4
     */
    public void setPERIODO4(double PERIODO4) {
        this.PERIODO4 = PERIODO4;
    }


    /**
     * Gets the MONTO4 value for this Response.
     * 
     * @return MONTO4
     */
    public double getMONTO4() {
        return MONTO4;
    }


    /**
     * Sets the MONTO4 value for this Response.
     * 
     * @param MONTO4
     */
    public void setMONTO4(double MONTO4) {
        this.MONTO4 = MONTO4;
    }


    /**
     * Gets the PERIODO5 value for this Response.
     * 
     * @return PERIODO5
     */
    public double getPERIODO5() {
        return PERIODO5;
    }


    /**
     * Sets the PERIODO5 value for this Response.
     * 
     * @param PERIODO5
     */
    public void setPERIODO5(double PERIODO5) {
        this.PERIODO5 = PERIODO5;
    }


    /**
     * Gets the MONTO5 value for this Response.
     * 
     * @return MONTO5
     */
    public double getMONTO5() {
        return MONTO5;
    }


    /**
     * Sets the MONTO5 value for this Response.
     * 
     * @param MONTO5
     */
    public void setMONTO5(double MONTO5) {
        this.MONTO5 = MONTO5;
    }


    /**
     * Gets the PERIODO6 value for this Response.
     * 
     * @return PERIODO6
     */
    public double getPERIODO6() {
        return PERIODO6;
    }


    /**
     * Sets the PERIODO6 value for this Response.
     * 
     * @param PERIODO6
     */
    public void setPERIODO6(double PERIODO6) {
        this.PERIODO6 = PERIODO6;
    }


    /**
     * Gets the MONTO6 value for this Response.
     * 
     * @return MONTO6
     */
    public double getMONTO6() {
        return MONTO6;
    }


    /**
     * Sets the MONTO6 value for this Response.
     * 
     * @param MONTO6
     */
    public void setMONTO6(double MONTO6) {
        this.MONTO6 = MONTO6;
    }


    /**
     * Gets the PERIODO7 value for this Response.
     * 
     * @return PERIODO7
     */
    public double getPERIODO7() {
        return PERIODO7;
    }


    /**
     * Sets the PERIODO7 value for this Response.
     * 
     * @param PERIODO7
     */
    public void setPERIODO7(double PERIODO7) {
        this.PERIODO7 = PERIODO7;
    }


    /**
     * Gets the MONTO7 value for this Response.
     * 
     * @return MONTO7
     */
    public double getMONTO7() {
        return MONTO7;
    }


    /**
     * Sets the MONTO7 value for this Response.
     * 
     * @param MONTO7
     */
    public void setMONTO7(double MONTO7) {
        this.MONTO7 = MONTO7;
    }


    /**
     * Gets the PERIODO8 value for this Response.
     * 
     * @return PERIODO8
     */
    public double getPERIODO8() {
        return PERIODO8;
    }


    /**
     * Sets the PERIODO8 value for this Response.
     * 
     * @param PERIODO8
     */
    public void setPERIODO8(double PERIODO8) {
        this.PERIODO8 = PERIODO8;
    }


    /**
     * Gets the MONTO8 value for this Response.
     * 
     * @return MONTO8
     */
    public double getMONTO8() {
        return MONTO8;
    }


    /**
     * Sets the MONTO8 value for this Response.
     * 
     * @param MONTO8
     */
    public void setMONTO8(double MONTO8) {
        this.MONTO8 = MONTO8;
    }


    /**
     * Gets the PERIODO9 value for this Response.
     * 
     * @return PERIODO9
     */
    public double getPERIODO9() {
        return PERIODO9;
    }


    /**
     * Sets the PERIODO9 value for this Response.
     * 
     * @param PERIODO9
     */
    public void setPERIODO9(double PERIODO9) {
        this.PERIODO9 = PERIODO9;
    }


    /**
     * Gets the MONTO9 value for this Response.
     * 
     * @return MONTO9
     */
    public double getMONTO9() {
        return MONTO9;
    }


    /**
     * Sets the MONTO9 value for this Response.
     * 
     * @param MONTO9
     */
    public void setMONTO9(double MONTO9) {
        this.MONTO9 = MONTO9;
    }


    /**
     * Gets the PERIODO10 value for this Response.
     * 
     * @return PERIODO10
     */
    public double getPERIODO10() {
        return PERIODO10;
    }


    /**
     * Sets the PERIODO10 value for this Response.
     * 
     * @param PERIODO10
     */
    public void setPERIODO10(double PERIODO10) {
        this.PERIODO10 = PERIODO10;
    }


    /**
     * Gets the MONTO10 value for this Response.
     * 
     * @return MONTO10
     */
    public double getMONTO10() {
        return MONTO10;
    }


    /**
     * Sets the MONTO10 value for this Response.
     * 
     * @param MONTO10
     */
    public void setMONTO10(double MONTO10) {
        this.MONTO10 = MONTO10;
    }


    /**
     * Gets the PERIODO11 value for this Response.
     * 
     * @return PERIODO11
     */
    public double getPERIODO11() {
        return PERIODO11;
    }


    /**
     * Sets the PERIODO11 value for this Response.
     * 
     * @param PERIODO11
     */
    public void setPERIODO11(double PERIODO11) {
        this.PERIODO11 = PERIODO11;
    }


    /**
     * Gets the MONTO11 value for this Response.
     * 
     * @return MONTO11
     */
    public double getMONTO11() {
        return MONTO11;
    }


    /**
     * Sets the MONTO11 value for this Response.
     * 
     * @param MONTO11
     */
    public void setMONTO11(double MONTO11) {
        this.MONTO11 = MONTO11;
    }


    /**
     * Gets the PERIODO12 value for this Response.
     * 
     * @return PERIODO12
     */
    public double getPERIODO12() {
        return PERIODO12;
    }


    /**
     * Sets the PERIODO12 value for this Response.
     * 
     * @param PERIODO12
     */
    public void setPERIODO12(double PERIODO12) {
        this.PERIODO12 = PERIODO12;
    }


    /**
     * Gets the MONTO12 value for this Response.
     * 
     * @return MONTO12
     */
    public double getMONTO12() {
        return MONTO12;
    }


    /**
     * Sets the MONTO12 value for this Response.
     * 
     * @param MONTO12
     */
    public void setMONTO12(double MONTO12) {
        this.MONTO12 = MONTO12;
    }


    /**
     * Gets the PERIODO13 value for this Response.
     * 
     * @return PERIODO13
     */
    public double getPERIODO13() {
        return PERIODO13;
    }


    /**
     * Sets the PERIODO13 value for this Response.
     * 
     * @param PERIODO13
     */
    public void setPERIODO13(double PERIODO13) {
        this.PERIODO13 = PERIODO13;
    }


    /**
     * Gets the MONTO13 value for this Response.
     * 
     * @return MONTO13
     */
    public double getMONTO13() {
        return MONTO13;
    }


    /**
     * Sets the MONTO13 value for this Response.
     * 
     * @param MONTO13
     */
    public void setMONTO13(double MONTO13) {
        this.MONTO13 = MONTO13;
    }


    /**
     * Gets the PERIODO14 value for this Response.
     * 
     * @return PERIODO14
     */
    public double getPERIODO14() {
        return PERIODO14;
    }


    /**
     * Sets the PERIODO14 value for this Response.
     * 
     * @param PERIODO14
     */
    public void setPERIODO14(double PERIODO14) {
        this.PERIODO14 = PERIODO14;
    }


    /**
     * Gets the MONTO14 value for this Response.
     * 
     * @return MONTO14
     */
    public double getMONTO14() {
        return MONTO14;
    }


    /**
     * Sets the MONTO14 value for this Response.
     * 
     * @param MONTO14
     */
    public void setMONTO14(double MONTO14) {
        this.MONTO14 = MONTO14;
    }


    /**
     * Gets the PERIODO15 value for this Response.
     * 
     * @return PERIODO15
     */
    public double getPERIODO15() {
        return PERIODO15;
    }


    /**
     * Sets the PERIODO15 value for this Response.
     * 
     * @param PERIODO15
     */
    public void setPERIODO15(double PERIODO15) {
        this.PERIODO15 = PERIODO15;
    }


    /**
     * Gets the MONTO15 value for this Response.
     * 
     * @return MONTO15
     */
    public double getMONTO15() {
        return MONTO15;
    }


    /**
     * Sets the MONTO15 value for this Response.
     * 
     * @param MONTO15
     */
    public void setMONTO15(double MONTO15) {
        this.MONTO15 = MONTO15;
    }


    /**
     * Gets the PERIODO16 value for this Response.
     * 
     * @return PERIODO16
     */
    public double getPERIODO16() {
        return PERIODO16;
    }


    /**
     * Sets the PERIODO16 value for this Response.
     * 
     * @param PERIODO16
     */
    public void setPERIODO16(double PERIODO16) {
        this.PERIODO16 = PERIODO16;
    }


    /**
     * Gets the MONTO16 value for this Response.
     * 
     * @return MONTO16
     */
    public double getMONTO16() {
        return MONTO16;
    }


    /**
     * Sets the MONTO16 value for this Response.
     * 
     * @param MONTO16
     */
    public void setMONTO16(double MONTO16) {
        this.MONTO16 = MONTO16;
    }


    /**
     * Gets the PERIODO17 value for this Response.
     * 
     * @return PERIODO17
     */
    public double getPERIODO17() {
        return PERIODO17;
    }


    /**
     * Sets the PERIODO17 value for this Response.
     * 
     * @param PERIODO17
     */
    public void setPERIODO17(double PERIODO17) {
        this.PERIODO17 = PERIODO17;
    }


    /**
     * Gets the MONTO17 value for this Response.
     * 
     * @return MONTO17
     */
    public double getMONTO17() {
        return MONTO17;
    }


    /**
     * Sets the MONTO17 value for this Response.
     * 
     * @param MONTO17
     */
    public void setMONTO17(double MONTO17) {
        this.MONTO17 = MONTO17;
    }


    /**
     * Gets the PERIODO18 value for this Response.
     * 
     * @return PERIODO18
     */
    public double getPERIODO18() {
        return PERIODO18;
    }


    /**
     * Sets the PERIODO18 value for this Response.
     * 
     * @param PERIODO18
     */
    public void setPERIODO18(double PERIODO18) {
        this.PERIODO18 = PERIODO18;
    }


    /**
     * Gets the MONTO18 value for this Response.
     * 
     * @return MONTO18
     */
    public double getMONTO18() {
        return MONTO18;
    }


    /**
     * Sets the MONTO18 value for this Response.
     * 
     * @param MONTO18
     */
    public void setMONTO18(double MONTO18) {
        this.MONTO18 = MONTO18;
    }


    /**
     * Gets the SEGMENTO value for this Response.
     * 
     * @return SEGMENTO
     */
    public java.lang.String getSEGMENTO() {
        return SEGMENTO;
    }


    /**
     * Sets the SEGMENTO value for this Response.
     * 
     * @param SEGMENTO
     */
    public void setSEGMENTO(java.lang.String SEGMENTO) {
        this.SEGMENTO = SEGMENTO;
    }


    /**
     * Gets the FECHAFALLECIMIENTO value for this Response.
     * 
     * @return FECHAFALLECIMIENTO
     */
    public java.lang.String getFECHAFALLECIMIENTO() {
        return FECHAFALLECIMIENTO;
    }


    /**
     * Sets the FECHAFALLECIMIENTO value for this Response.
     * 
     * @param FECHAFALLECIMIENTO
     */
    public void setFECHAFALLECIMIENTO(java.lang.String FECHAFALLECIMIENTO) {
        this.FECHAFALLECIMIENTO = FECHAFALLECIMIENTO;
    }


    /**
     * Gets the FECHACONTRATO value for this Response.
     * 
     * @return FECHACONTRATO
     */
    public java.lang.String getFECHACONTRATO() {
        return FECHACONTRATO;
    }


    /**
     * Sets the FECHACONTRATO value for this Response.
     * 
     * @param FECHACONTRATO
     */
    public void setFECHACONTRATO(java.lang.String FECHACONTRATO) {
        this.FECHACONTRATO = FECHACONTRATO;
    }


    /**
     * Gets the ESTADOCIVIL value for this Response.
     * 
     * @return ESTADOCIVIL
     */
    public java.lang.String getESTADOCIVIL() {
        return ESTADOCIVIL;
    }


    /**
     * Sets the ESTADOCIVIL value for this Response.
     * 
     * @param ESTADOCIVIL
     */
    public void setESTADOCIVIL(java.lang.String ESTADOCIVIL) {
        this.ESTADOCIVIL = ESTADOCIVIL;
    }


    /**
     * Gets the TIPOCONTRATO value for this Response.
     * 
     * @return TIPOCONTRATO
     */
    public java.lang.String getTIPOCONTRATO() {
        return TIPOCONTRATO;
    }


    /**
     * Sets the TIPOCONTRATO value for this Response.
     * 
     * @param TIPOCONTRATO
     */
    public void setTIPOCONTRATO(java.lang.String TIPOCONTRATO) {
        this.TIPOCONTRATO = TIPOCONTRATO;
    }


    /**
     * Gets the DIRECCION value for this Response.
     * 
     * @return DIRECCION
     */
    public java.lang.String getDIRECCION() {
        return DIRECCION;
    }


    /**
     * Sets the DIRECCION value for this Response.
     * 
     * @param DIRECCION
     */
    public void setDIRECCION(java.lang.String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }


    /**
     * Gets the NUMERO value for this Response.
     * 
     * @return NUMERO
     */
    public java.lang.String getNUMERO() {
        return NUMERO;
    }


    /**
     * Sets the NUMERO value for this Response.
     * 
     * @param NUMERO
     */
    public void setNUMERO(java.lang.String NUMERO) {
        this.NUMERO = NUMERO;
    }


    /**
     * Gets the DEPTO value for this Response.
     * 
     * @return DEPTO
     */
    public java.lang.String getDEPTO() {
        return DEPTO;
    }


    /**
     * Sets the DEPTO value for this Response.
     * 
     * @param DEPTO
     */
    public void setDEPTO(java.lang.String DEPTO) {
        this.DEPTO = DEPTO;
    }


    /**
     * Gets the VILLAPOBLACION value for this Response.
     * 
     * @return VILLAPOBLACION
     */
    public java.lang.String getVILLAPOBLACION() {
        return VILLAPOBLACION;
    }


    /**
     * Sets the VILLAPOBLACION value for this Response.
     * 
     * @param VILLAPOBLACION
     */
    public void setVILLAPOBLACION(java.lang.String VILLAPOBLACION) {
        this.VILLAPOBLACION = VILLAPOBLACION;
    }


    /**
     * Gets the CODIGOREGION value for this Response.
     * 
     * @return CODIGOREGION
     */
    public java.lang.String getCODIGOREGION() {
        return CODIGOREGION;
    }


    /**
     * Sets the CODIGOREGION value for this Response.
     * 
     * @param CODIGOREGION
     */
    public void setCODIGOREGION(java.lang.String CODIGOREGION) {
        this.CODIGOREGION = CODIGOREGION;
    }


    /**
     * Gets the CODIGOPROVINCIA value for this Response.
     * 
     * @return CODIGOPROVINCIA
     */
    public java.lang.String getCODIGOPROVINCIA() {
        return CODIGOPROVINCIA;
    }


    /**
     * Sets the CODIGOPROVINCIA value for this Response.
     * 
     * @param CODIGOPROVINCIA
     */
    public void setCODIGOPROVINCIA(java.lang.String CODIGOPROVINCIA) {
        this.CODIGOPROVINCIA = CODIGOPROVINCIA;
    }


    /**
     * Gets the CODIGOCOMUNA value for this Response.
     * 
     * @return CODIGOCOMUNA
     */
    public java.lang.String getCODIGOCOMUNA() {
        return CODIGOCOMUNA;
    }


    /**
     * Sets the CODIGOCOMUNA value for this Response.
     * 
     * @param CODIGOCOMUNA
     */
    public void setCODIGOCOMUNA(java.lang.String CODIGOCOMUNA) {
        this.CODIGOCOMUNA = CODIGOCOMUNA;
    }


    /**
     * Gets the EMAIL value for this Response.
     * 
     * @return EMAIL
     */
    public java.lang.String getEMAIL() {
        return EMAIL;
    }


    /**
     * Sets the EMAIL value for this Response.
     * 
     * @param EMAIL
     */
    public void setEMAIL(java.lang.String EMAIL) {
        this.EMAIL = EMAIL;
    }


    /**
     * Gets the CELULAR value for this Response.
     * 
     * @return CELULAR
     */
    public java.lang.String getCELULAR() {
        return CELULAR;
    }


    /**
     * Sets the CELULAR value for this Response.
     * 
     * @param CELULAR
     */
    public void setCELULAR(java.lang.String CELULAR) {
        this.CELULAR = CELULAR;
    }


    /**
     * Gets the TELEFONO value for this Response.
     * 
     * @return TELEFONO
     */
    public java.lang.String getTELEFONO() {
        return TELEFONO;
    }


    /**
     * Sets the TELEFONO value for this Response.
     * 
     * @param TELEFONO
     */
    public void setTELEFONO(java.lang.String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }


    /**
     * Gets the NUMEROCUENTA value for this Response.
     * 
     * @return NUMEROCUENTA
     */
    public java.lang.String getNUMEROCUENTA() {
        return NUMEROCUENTA;
    }


    /**
     * Sets the NUMEROCUENTA value for this Response.
     * 
     * @param NUMEROCUENTA
     */
    public void setNUMEROCUENTA(java.lang.String NUMEROCUENTA) {
        this.NUMEROCUENTA = NUMEROCUENTA;
    }


    /**
     * Gets the TIPOCUENTA value for this Response.
     * 
     * @return TIPOCUENTA
     */
    public java.lang.String getTIPOCUENTA() {
        return TIPOCUENTA;
    }


    /**
     * Sets the TIPOCUENTA value for this Response.
     * 
     * @param TIPOCUENTA
     */
    public void setTIPOCUENTA(java.lang.String TIPOCUENTA) {
        this.TIPOCUENTA = TIPOCUENTA;
    }


    /**
     * Gets the CODIGOBANCO value for this Response.
     * 
     * @return CODIGOBANCO
     */
    public java.lang.String getCODIGOBANCO() {
        return CODIGOBANCO;
    }


    /**
     * Sets the CODIGOBANCO value for this Response.
     * 
     * @param CODIGOBANCO
     */
    public void setCODIGOBANCO(java.lang.String CODIGOBANCO) {
        this.CODIGOBANCO = CODIGOBANCO;
    }


    /**
     * Gets the DECLARACIONJURADA value for this Response.
     * 
     * @return DECLARACIONJURADA
     */
    public java.lang.String getDECLARACIONJURADA() {
        return DECLARACIONJURADA;
    }


    /**
     * Sets the DECLARACIONJURADA value for this Response.
     * 
     * @param DECLARACIONJURADA
     */
    public void setDECLARACIONJURADA(java.lang.String DECLARACIONJURADA) {
        this.DECLARACIONJURADA = DECLARACIONJURADA;
    }


    /**
     * Gets the NACIONALIDAD value for this Response.
     * 
     * @return NACIONALIDAD
     */
    public java.lang.String getNACIONALIDAD() {
        return NACIONALIDAD;
    }


    /**
     * Sets the NACIONALIDAD value for this Response.
     * 
     * @param NACIONALIDAD
     */
    public void setNACIONALIDAD(java.lang.String NACIONALIDAD) {
        this.NACIONALIDAD = NACIONALIDAD;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Response)) return false;
        Response other = (Response) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.codigoRespuesta == other.getCodigoRespuesta() &&
            ((this.RUTAFILIADO==null && other.getRUTAFILIADO()==null) || 
             (this.RUTAFILIADO!=null &&
              this.RUTAFILIADO.equals(other.getRUTAFILIADO()))) &&
            ((this.RUTEMPRESA==null && other.getRUTEMPRESA()==null) || 
             (this.RUTEMPRESA!=null &&
              this.RUTEMPRESA.equals(other.getRUTEMPRESA()))) &&
            ((this.AFILIADOVIGENTE==null && other.getAFILIADOVIGENTE()==null) || 
             (this.AFILIADOVIGENTE!=null &&
              this.AFILIADOVIGENTE.equals(other.getAFILIADOVIGENTE()))) &&
            this.RIESGOTOTAL == other.getRIESGOTOTAL() &&
            ((this.VECESRENTA==null && other.getVECESRENTA()==null) || 
             (this.VECESRENTA!=null &&
              this.VECESRENTA.equals(other.getVECESRENTA()))) &&
            ((this.SUPERAVECESRENTA==null && other.getSUPERAVECESRENTA()==null) || 
             (this.SUPERAVECESRENTA!=null &&
              this.SUPERAVECESRENTA.equals(other.getSUPERAVECESRENTA()))) &&
            ((this.MAXIMOENDEUDAMIENTO==null && other.getMAXIMOENDEUDAMIENTO()==null) || 
             (this.MAXIMOENDEUDAMIENTO!=null &&
              this.MAXIMOENDEUDAMIENTO.equals(other.getMAXIMOENDEUDAMIENTO()))) &&
            this.PORCENTAJEENDEUDAMIENTO == other.getPORCENTAJEENDEUDAMIENTO() &&
            ((this.SUPERAMAXENDEUDAMIENTONORMATIVO==null && other.getSUPERAMAXENDEUDAMIENTONORMATIVO()==null) || 
             (this.SUPERAMAXENDEUDAMIENTONORMATIVO!=null &&
              this.SUPERAMAXENDEUDAMIENTONORMATIVO.equals(other.getSUPERAMAXENDEUDAMIENTONORMATIVO()))) &&
            ((this.FUERARANGOEDAD==null && other.getFUERARANGOEDAD()==null) || 
             (this.FUERARANGOEDAD!=null &&
              this.FUERARANGOEDAD.equals(other.getFUERARANGOEDAD()))) &&
            ((this.FUERARANGOANTIGUEDAD==null && other.getFUERARANGOANTIGUEDAD()==null) || 
             (this.FUERARANGOANTIGUEDAD!=null &&
              this.FUERARANGOANTIGUEDAD.equals(other.getFUERARANGOANTIGUEDAD()))) &&
            ((this.PLAZOCREDITOEDAD==null && other.getPLAZOCREDITOEDAD()==null) || 
             (this.PLAZOCREDITOEDAD!=null &&
              this.PLAZOCREDITOEDAD.equals(other.getPLAZOCREDITOEDAD()))) &&
            ((this.AFILIADOPEP==null && other.getAFILIADOPEP()==null) || 
             (this.AFILIADOPEP!=null &&
              this.AFILIADOPEP.equals(other.getAFILIADOPEP()))) &&
            ((this.ESTADOINSOLVENCIA==null && other.getESTADOINSOLVENCIA()==null) || 
             (this.ESTADOINSOLVENCIA!=null &&
              this.ESTADOINSOLVENCIA.equals(other.getESTADOINSOLVENCIA()))) &&
            ((this.AFILBLOQUEOALCREDITO==null && other.getAFILBLOQUEOALCREDITO()==null) || 
             (this.AFILBLOQUEOALCREDITO!=null &&
              this.AFILBLOQUEOALCREDITO.equals(other.getAFILBLOQUEOALCREDITO()))) &&
            ((this.EMPBLOQUEOALCREDITO==null && other.getEMPBLOQUEOALCREDITO()==null) || 
             (this.EMPBLOQUEOALCREDITO!=null &&
              this.EMPBLOQUEOALCREDITO.equals(other.getEMPBLOQUEOALCREDITO()))) &&
            ((this.DEUDAINTERCAJA==null && other.getDEUDAINTERCAJA()==null) || 
             (this.DEUDAINTERCAJA!=null &&
              this.DEUDAINTERCAJA.equals(other.getDEUDAINTERCAJA()))) &&
            ((this.LICENCIAACTIVA==null && other.getLICENCIAACTIVA()==null) || 
             (this.LICENCIAACTIVA!=null &&
              this.LICENCIAACTIVA.equals(other.getLICENCIAACTIVA()))) &&
            ((this.DIASLICENCIA==null && other.getDIASLICENCIA()==null) || 
             (this.DIASLICENCIA!=null &&
              this.DIASLICENCIA.equals(other.getDIASLICENCIA()))) &&
            ((this.CREDITOSCASTIGASDOS==null && other.getCREDITOSCASTIGASDOS()==null) || 
             (this.CREDITOSCASTIGASDOS!=null &&
              this.CREDITOSCASTIGASDOS.equals(other.getCREDITOSCASTIGASDOS()))) &&
            ((this.CREDITOSAFPPROVIDA==null && other.getCREDITOSAFPPROVIDA()==null) || 
             (this.CREDITOSAFPPROVIDA!=null &&
              this.CREDITOSAFPPROVIDA.equals(other.getCREDITOSAFPPROVIDA()))) &&
            ((this.CREDITOMOROSIDAD==null && other.getCREDITOMOROSIDAD()==null) || 
             (this.CREDITOMOROSIDAD!=null &&
              this.CREDITOMOROSIDAD.equals(other.getCREDITOMOROSIDAD()))) &&
            ((this.PENSIONADOAFP==null && other.getPENSIONADOAFP()==null) || 
             (this.PENSIONADOAFP!=null &&
              this.PENSIONADOAFP.equals(other.getPENSIONADOAFP()))) &&
            ((this.AFILIADOPUBLICO15==null && other.getAFILIADOPUBLICO15()==null) || 
             (this.AFILIADOPUBLICO15!=null &&
              this.AFILIADOPUBLICO15.equals(other.getAFILIADOPUBLICO15()))) &&
            ((this.FUNCIONARIOCAJA==null && other.getFUNCIONARIOCAJA()==null) || 
             (this.FUNCIONARIOCAJA!=null &&
              this.FUNCIONARIOCAJA.equals(other.getFUNCIONARIOCAJA()))) &&
            ((this.DATOSOBLIGATORIOS==null && other.getDATOSOBLIGATORIOS()==null) || 
             (this.DATOSOBLIGATORIOS!=null &&
              this.DATOSOBLIGATORIOS.equals(other.getDATOSOBLIGATORIOS()))) &&
            ((this.SUPERATASAPIZARRA==null && other.getSUPERATASAPIZARRA()==null) || 
             (this.SUPERATASAPIZARRA!=null &&
              this.SUPERATASAPIZARRA.equals(other.getSUPERATASAPIZARRA()))) &&
            ((this.TASAMENSUALCERO==null && other.getTASAMENSUALCERO()==null) || 
             (this.TASAMENSUALCERO!=null &&
              this.TASAMENSUALCERO.equals(other.getTASAMENSUALCERO()))) &&
            ((this.CALIFICA_UCHILE==null && other.getCALIFICA_UCHILE()==null) || 
             (this.CALIFICA_UCHILE!=null &&
              this.CALIFICA_UCHILE.equals(other.getCALIFICA_UCHILE()))) &&
            ((this.EXITOSINACOFI==null && other.getEXITOSINACOFI()==null) || 
             (this.EXITOSINACOFI!=null &&
              this.EXITOSINACOFI.equals(other.getEXITOSINACOFI()))) &&
            ((this.AFILIADOMANTENEDOR==null && other.getAFILIADOMANTENEDOR()==null) || 
             (this.AFILIADOMANTENEDOR!=null &&
              this.AFILIADOMANTENEDOR.equals(other.getAFILIADOMANTENEDOR()))) &&
            ((this.EMPRESAMANTENEDOR==null && other.getEMPRESAMANTENEDOR()==null) || 
             (this.EMPRESAMANTENEDOR!=null &&
              this.EMPRESAMANTENEDOR.equals(other.getEMPRESAMANTENEDOR()))) &&
            ((this.SEXO==null && other.getSEXO()==null) || 
             (this.SEXO!=null &&
              this.SEXO.equals(other.getSEXO()))) &&
            ((this.RIESGOEMPRESA==null && other.getRIESGOEMPRESA()==null) || 
             (this.RIESGOEMPRESA!=null &&
              this.RIESGOEMPRESA.equals(other.getRIESGOEMPRESA()))) &&
            ((this.FECHANACIMIENTO==null && other.getFECHANACIMIENTO()==null) || 
             (this.FECHANACIMIENTO!=null &&
              this.FECHANACIMIENTO.equals(other.getFECHANACIMIENTO()))) &&
            ((this.AFILCREDITOREPROVIGENTE==null && other.getAFILCREDITOREPROVIGENTE()==null) || 
             (this.AFILCREDITOREPROVIGENTE!=null &&
              this.AFILCREDITOREPROVIGENTE.equals(other.getAFILCREDITOREPROVIGENTE()))) &&
            ((this.PENSIONADOPBS==null && other.getPENSIONADOPBS()==null) || 
             (this.PENSIONADOPBS!=null &&
              this.PENSIONADOPBS.equals(other.getPENSIONADOPBS()))) &&
            ((this.AFILCAMPREPROVIGENTE==null && other.getAFILCAMPREPROVIGENTE()==null) || 
             (this.AFILCAMPREPROVIGENTE!=null &&
              this.AFILCAMPREPROVIGENTE.equals(other.getAFILCAMPREPROVIGENTE()))) &&
            this.PERIODO1 == other.getPERIODO1() &&
            this.MONTO1 == other.getMONTO1() &&
            this.PERIODO2 == other.getPERIODO2() &&
            this.MONTO2 == other.getMONTO2() &&
            this.PERIODO3 == other.getPERIODO3() &&
            this.MONTO3 == other.getMONTO3() &&
            this.PERIODO4 == other.getPERIODO4() &&
            this.MONTO4 == other.getMONTO4() &&
            this.PERIODO5 == other.getPERIODO5() &&
            this.MONTO5 == other.getMONTO5() &&
            this.PERIODO6 == other.getPERIODO6() &&
            this.MONTO6 == other.getMONTO6() &&
            this.PERIODO7 == other.getPERIODO7() &&
            this.MONTO7 == other.getMONTO7() &&
            this.PERIODO8 == other.getPERIODO8() &&
            this.MONTO8 == other.getMONTO8() &&
            this.PERIODO9 == other.getPERIODO9() &&
            this.MONTO9 == other.getMONTO9() &&
            this.PERIODO10 == other.getPERIODO10() &&
            this.MONTO10 == other.getMONTO10() &&
            this.PERIODO11 == other.getPERIODO11() &&
            this.MONTO11 == other.getMONTO11() &&
            this.PERIODO12 == other.getPERIODO12() &&
            this.MONTO12 == other.getMONTO12() &&
            this.PERIODO13 == other.getPERIODO13() &&
            this.MONTO13 == other.getMONTO13() &&
            this.PERIODO14 == other.getPERIODO14() &&
            this.MONTO14 == other.getMONTO14() &&
            this.PERIODO15 == other.getPERIODO15() &&
            this.MONTO15 == other.getMONTO15() &&
            this.PERIODO16 == other.getPERIODO16() &&
            this.MONTO16 == other.getMONTO16() &&
            this.PERIODO17 == other.getPERIODO17() &&
            this.MONTO17 == other.getMONTO17() &&
            this.PERIODO18 == other.getPERIODO18() &&
            this.MONTO18 == other.getMONTO18() &&
            ((this.SEGMENTO==null && other.getSEGMENTO()==null) || 
             (this.SEGMENTO!=null &&
              this.SEGMENTO.equals(other.getSEGMENTO()))) &&
            ((this.FECHAFALLECIMIENTO==null && other.getFECHAFALLECIMIENTO()==null) || 
             (this.FECHAFALLECIMIENTO!=null &&
              this.FECHAFALLECIMIENTO.equals(other.getFECHAFALLECIMIENTO()))) &&
            ((this.FECHACONTRATO==null && other.getFECHACONTRATO()==null) || 
             (this.FECHACONTRATO!=null &&
              this.FECHACONTRATO.equals(other.getFECHACONTRATO()))) &&
            ((this.ESTADOCIVIL==null && other.getESTADOCIVIL()==null) || 
             (this.ESTADOCIVIL!=null &&
              this.ESTADOCIVIL.equals(other.getESTADOCIVIL()))) &&
            ((this.TIPOCONTRATO==null && other.getTIPOCONTRATO()==null) || 
             (this.TIPOCONTRATO!=null &&
              this.TIPOCONTRATO.equals(other.getTIPOCONTRATO()))) &&
            ((this.DIRECCION==null && other.getDIRECCION()==null) || 
             (this.DIRECCION!=null &&
              this.DIRECCION.equals(other.getDIRECCION()))) &&
            ((this.NUMERO==null && other.getNUMERO()==null) || 
             (this.NUMERO!=null &&
              this.NUMERO.equals(other.getNUMERO()))) &&
            ((this.DEPTO==null && other.getDEPTO()==null) || 
             (this.DEPTO!=null &&
              this.DEPTO.equals(other.getDEPTO()))) &&
            ((this.VILLAPOBLACION==null && other.getVILLAPOBLACION()==null) || 
             (this.VILLAPOBLACION!=null &&
              this.VILLAPOBLACION.equals(other.getVILLAPOBLACION()))) &&
            ((this.CODIGOREGION==null && other.getCODIGOREGION()==null) || 
             (this.CODIGOREGION!=null &&
              this.CODIGOREGION.equals(other.getCODIGOREGION()))) &&
            ((this.CODIGOPROVINCIA==null && other.getCODIGOPROVINCIA()==null) || 
             (this.CODIGOPROVINCIA!=null &&
              this.CODIGOPROVINCIA.equals(other.getCODIGOPROVINCIA()))) &&
            ((this.CODIGOCOMUNA==null && other.getCODIGOCOMUNA()==null) || 
             (this.CODIGOCOMUNA!=null &&
              this.CODIGOCOMUNA.equals(other.getCODIGOCOMUNA()))) &&
            ((this.EMAIL==null && other.getEMAIL()==null) || 
             (this.EMAIL!=null &&
              this.EMAIL.equals(other.getEMAIL()))) &&
            ((this.CELULAR==null && other.getCELULAR()==null) || 
             (this.CELULAR!=null &&
              this.CELULAR.equals(other.getCELULAR()))) &&
            ((this.TELEFONO==null && other.getTELEFONO()==null) || 
             (this.TELEFONO!=null &&
              this.TELEFONO.equals(other.getTELEFONO()))) &&
            ((this.NUMEROCUENTA==null && other.getNUMEROCUENTA()==null) || 
             (this.NUMEROCUENTA!=null &&
              this.NUMEROCUENTA.equals(other.getNUMEROCUENTA()))) &&
            ((this.TIPOCUENTA==null && other.getTIPOCUENTA()==null) || 
             (this.TIPOCUENTA!=null &&
              this.TIPOCUENTA.equals(other.getTIPOCUENTA()))) &&
            ((this.CODIGOBANCO==null && other.getCODIGOBANCO()==null) || 
             (this.CODIGOBANCO!=null &&
              this.CODIGOBANCO.equals(other.getCODIGOBANCO()))) &&
            ((this.DECLARACIONJURADA==null && other.getDECLARACIONJURADA()==null) || 
             (this.DECLARACIONJURADA!=null &&
              this.DECLARACIONJURADA.equals(other.getDECLARACIONJURADA()))) &&
            ((this.NACIONALIDAD==null && other.getNACIONALIDAD()==null) || 
             (this.NACIONALIDAD!=null &&
              this.NACIONALIDAD.equals(other.getNACIONALIDAD())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getCodigoRespuesta();
        if (getRUTAFILIADO() != null) {
            _hashCode += getRUTAFILIADO().hashCode();
        }
        if (getRUTEMPRESA() != null) {
            _hashCode += getRUTEMPRESA().hashCode();
        }
        if (getAFILIADOVIGENTE() != null) {
            _hashCode += getAFILIADOVIGENTE().hashCode();
        }
        _hashCode += new Double(getRIESGOTOTAL()).hashCode();
        if (getVECESRENTA() != null) {
            _hashCode += getVECESRENTA().hashCode();
        }
        if (getSUPERAVECESRENTA() != null) {
            _hashCode += getSUPERAVECESRENTA().hashCode();
        }
        if (getMAXIMOENDEUDAMIENTO() != null) {
            _hashCode += getMAXIMOENDEUDAMIENTO().hashCode();
        }
        _hashCode += new Double(getPORCENTAJEENDEUDAMIENTO()).hashCode();
        if (getSUPERAMAXENDEUDAMIENTONORMATIVO() != null) {
            _hashCode += getSUPERAMAXENDEUDAMIENTONORMATIVO().hashCode();
        }
        if (getFUERARANGOEDAD() != null) {
            _hashCode += getFUERARANGOEDAD().hashCode();
        }
        if (getFUERARANGOANTIGUEDAD() != null) {
            _hashCode += getFUERARANGOANTIGUEDAD().hashCode();
        }
        if (getPLAZOCREDITOEDAD() != null) {
            _hashCode += getPLAZOCREDITOEDAD().hashCode();
        }
        if (getAFILIADOPEP() != null) {
            _hashCode += getAFILIADOPEP().hashCode();
        }
        if (getESTADOINSOLVENCIA() != null) {
            _hashCode += getESTADOINSOLVENCIA().hashCode();
        }
        if (getAFILBLOQUEOALCREDITO() != null) {
            _hashCode += getAFILBLOQUEOALCREDITO().hashCode();
        }
        if (getEMPBLOQUEOALCREDITO() != null) {
            _hashCode += getEMPBLOQUEOALCREDITO().hashCode();
        }
        if (getDEUDAINTERCAJA() != null) {
            _hashCode += getDEUDAINTERCAJA().hashCode();
        }
        if (getLICENCIAACTIVA() != null) {
            _hashCode += getLICENCIAACTIVA().hashCode();
        }
        if (getDIASLICENCIA() != null) {
            _hashCode += getDIASLICENCIA().hashCode();
        }
        if (getCREDITOSCASTIGASDOS() != null) {
            _hashCode += getCREDITOSCASTIGASDOS().hashCode();
        }
        if (getCREDITOSAFPPROVIDA() != null) {
            _hashCode += getCREDITOSAFPPROVIDA().hashCode();
        }
        if (getCREDITOMOROSIDAD() != null) {
            _hashCode += getCREDITOMOROSIDAD().hashCode();
        }
        if (getPENSIONADOAFP() != null) {
            _hashCode += getPENSIONADOAFP().hashCode();
        }
        if (getAFILIADOPUBLICO15() != null) {
            _hashCode += getAFILIADOPUBLICO15().hashCode();
        }
        if (getFUNCIONARIOCAJA() != null) {
            _hashCode += getFUNCIONARIOCAJA().hashCode();
        }
        if (getDATOSOBLIGATORIOS() != null) {
            _hashCode += getDATOSOBLIGATORIOS().hashCode();
        }
        if (getSUPERATASAPIZARRA() != null) {
            _hashCode += getSUPERATASAPIZARRA().hashCode();
        }
        if (getTASAMENSUALCERO() != null) {
            _hashCode += getTASAMENSUALCERO().hashCode();
        }
        if (getCALIFICA_UCHILE() != null) {
            _hashCode += getCALIFICA_UCHILE().hashCode();
        }
        if (getEXITOSINACOFI() != null) {
            _hashCode += getEXITOSINACOFI().hashCode();
        }
        if (getAFILIADOMANTENEDOR() != null) {
            _hashCode += getAFILIADOMANTENEDOR().hashCode();
        }
        if (getEMPRESAMANTENEDOR() != null) {
            _hashCode += getEMPRESAMANTENEDOR().hashCode();
        }
        if (getSEXO() != null) {
            _hashCode += getSEXO().hashCode();
        }
        if (getRIESGOEMPRESA() != null) {
            _hashCode += getRIESGOEMPRESA().hashCode();
        }
        if (getFECHANACIMIENTO() != null) {
            _hashCode += getFECHANACIMIENTO().hashCode();
        }
        if (getAFILCREDITOREPROVIGENTE() != null) {
            _hashCode += getAFILCREDITOREPROVIGENTE().hashCode();
        }
        if (getPENSIONADOPBS() != null) {
            _hashCode += getPENSIONADOPBS().hashCode();
        }
        if (getAFILCAMPREPROVIGENTE() != null) {
            _hashCode += getAFILCAMPREPROVIGENTE().hashCode();
        }
        _hashCode += new Double(getPERIODO1()).hashCode();
        _hashCode += new Double(getMONTO1()).hashCode();
        _hashCode += new Double(getPERIODO2()).hashCode();
        _hashCode += new Double(getMONTO2()).hashCode();
        _hashCode += new Double(getPERIODO3()).hashCode();
        _hashCode += new Double(getMONTO3()).hashCode();
        _hashCode += new Double(getPERIODO4()).hashCode();
        _hashCode += new Double(getMONTO4()).hashCode();
        _hashCode += new Double(getPERIODO5()).hashCode();
        _hashCode += new Double(getMONTO5()).hashCode();
        _hashCode += new Double(getPERIODO6()).hashCode();
        _hashCode += new Double(getMONTO6()).hashCode();
        _hashCode += new Double(getPERIODO7()).hashCode();
        _hashCode += new Double(getMONTO7()).hashCode();
        _hashCode += new Double(getPERIODO8()).hashCode();
        _hashCode += new Double(getMONTO8()).hashCode();
        _hashCode += new Double(getPERIODO9()).hashCode();
        _hashCode += new Double(getMONTO9()).hashCode();
        _hashCode += new Double(getPERIODO10()).hashCode();
        _hashCode += new Double(getMONTO10()).hashCode();
        _hashCode += new Double(getPERIODO11()).hashCode();
        _hashCode += new Double(getMONTO11()).hashCode();
        _hashCode += new Double(getPERIODO12()).hashCode();
        _hashCode += new Double(getMONTO12()).hashCode();
        _hashCode += new Double(getPERIODO13()).hashCode();
        _hashCode += new Double(getMONTO13()).hashCode();
        _hashCode += new Double(getPERIODO14()).hashCode();
        _hashCode += new Double(getMONTO14()).hashCode();
        _hashCode += new Double(getPERIODO15()).hashCode();
        _hashCode += new Double(getMONTO15()).hashCode();
        _hashCode += new Double(getPERIODO16()).hashCode();
        _hashCode += new Double(getMONTO16()).hashCode();
        _hashCode += new Double(getPERIODO17()).hashCode();
        _hashCode += new Double(getMONTO17()).hashCode();
        _hashCode += new Double(getPERIODO18()).hashCode();
        _hashCode += new Double(getMONTO18()).hashCode();
        if (getSEGMENTO() != null) {
            _hashCode += getSEGMENTO().hashCode();
        }
        if (getFECHAFALLECIMIENTO() != null) {
            _hashCode += getFECHAFALLECIMIENTO().hashCode();
        }
        if (getFECHACONTRATO() != null) {
            _hashCode += getFECHACONTRATO().hashCode();
        }
        if (getESTADOCIVIL() != null) {
            _hashCode += getESTADOCIVIL().hashCode();
        }
        if (getTIPOCONTRATO() != null) {
            _hashCode += getTIPOCONTRATO().hashCode();
        }
        if (getDIRECCION() != null) {
            _hashCode += getDIRECCION().hashCode();
        }
        if (getNUMERO() != null) {
            _hashCode += getNUMERO().hashCode();
        }
        if (getDEPTO() != null) {
            _hashCode += getDEPTO().hashCode();
        }
        if (getVILLAPOBLACION() != null) {
            _hashCode += getVILLAPOBLACION().hashCode();
        }
        if (getCODIGOREGION() != null) {
            _hashCode += getCODIGOREGION().hashCode();
        }
        if (getCODIGOPROVINCIA() != null) {
            _hashCode += getCODIGOPROVINCIA().hashCode();
        }
        if (getCODIGOCOMUNA() != null) {
            _hashCode += getCODIGOCOMUNA().hashCode();
        }
        if (getEMAIL() != null) {
            _hashCode += getEMAIL().hashCode();
        }
        if (getCELULAR() != null) {
            _hashCode += getCELULAR().hashCode();
        }
        if (getTELEFONO() != null) {
            _hashCode += getTELEFONO().hashCode();
        }
        if (getNUMEROCUENTA() != null) {
            _hashCode += getNUMEROCUENTA().hashCode();
        }
        if (getTIPOCUENTA() != null) {
            _hashCode += getTIPOCUENTA().hashCode();
        }
        if (getCODIGOBANCO() != null) {
            _hashCode += getCODIGOBANCO().hashCode();
        }
        if (getDECLARACIONJURADA() != null) {
            _hashCode += getDECLARACIONJURADA().hashCode();
        }
        if (getNACIONALIDAD() != null) {
            _hashCode += getNACIONALIDAD().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.laaraucana.cl/validaCredito", "Response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoRespuesta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoRespuesta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RUTAFILIADO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RUTAFILIADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RUTEMPRESA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RUTEMPRESA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AFILIADOVIGENTE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AFILIADOVIGENTE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RIESGOTOTAL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RIESGOTOTAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VECESRENTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "VECESRENTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SUPERAVECESRENTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SUPERAVECESRENTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MAXIMOENDEUDAMIENTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MAXIMOENDEUDAMIENTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PORCENTAJEENDEUDAMIENTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PORCENTAJEENDEUDAMIENTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SUPERAMAXENDEUDAMIENTONORMATIVO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SUPERAMAXENDEUDAMIENTONORMATIVO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FUERARANGOEDAD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FUERARANGOEDAD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FUERARANGOANTIGUEDAD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FUERARANGOANTIGUEDAD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PLAZOCREDITOEDAD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PLAZOCREDITOEDAD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AFILIADOPEP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AFILIADOPEP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESTADOINSOLVENCIA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ESTADOINSOLVENCIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AFILBLOQUEOALCREDITO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AFILBLOQUEOALCREDITO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EMPBLOQUEOALCREDITO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EMPBLOQUEOALCREDITO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEUDAINTERCAJA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEUDAINTERCAJA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LICENCIAACTIVA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LICENCIAACTIVA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DIASLICENCIA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DIASLICENCIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CREDITOSCASTIGASDOS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CREDITOSCASTIGASDOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CREDITOSAFPPROVIDA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CREDITOSAFPPROVIDA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CREDITOMOROSIDAD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CREDITOMOROSIDAD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PENSIONADOAFP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PENSIONADOAFP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AFILIADOPUBLICO15");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AFILIADOPUBLICO15"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FUNCIONARIOCAJA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FUNCIONARIOCAJA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DATOSOBLIGATORIOS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DATOSOBLIGATORIOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SUPERATASAPIZARRA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SUPERATASAPIZARRA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TASAMENSUALCERO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TASAMENSUALCERO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CALIFICA_UCHILE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CALIFICA_UCHILE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EXITOSINACOFI");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EXITOSINACOFI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AFILIADOMANTENEDOR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AFILIADOMANTENEDOR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EMPRESAMANTENEDOR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EMPRESAMANTENEDOR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SEXO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SEXO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RIESGOEMPRESA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RIESGOEMPRESA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHANACIMIENTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FECHANACIMIENTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AFILCREDITOREPROVIGENTE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AFILCREDITOREPROVIGENTE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PENSIONADOPBS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PENSIONADOPBS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AFILCAMPREPROVIGENTE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AFILCAMPREPROVIGENTE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERIODO1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PERIODO1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERIODO2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PERIODO2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERIODO3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PERIODO3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERIODO4");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PERIODO4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO4");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERIODO5");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PERIODO5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO5");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERIODO6");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PERIODO6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO6");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERIODO7");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PERIODO7"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO7");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO7"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERIODO8");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PERIODO8"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO8");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO8"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERIODO9");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PERIODO9"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO9");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO9"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERIODO10");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PERIODO10"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO10");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO10"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERIODO11");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PERIODO11"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO11");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO11"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERIODO12");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PERIODO12"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO12");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO12"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERIODO13");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PERIODO13"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO13");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO13"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERIODO14");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PERIODO14"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO14");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO14"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERIODO15");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PERIODO15"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO15");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO15"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERIODO16");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PERIODO16"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO16");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO16"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERIODO17");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PERIODO17"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO17");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO17"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERIODO18");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PERIODO18"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO18");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO18"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SEGMENTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SEGMENTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHAFALLECIMIENTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FECHAFALLECIMIENTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHACONTRATO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FECHACONTRATO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESTADOCIVIL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ESTADOCIVIL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPOCONTRATO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TIPOCONTRATO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DIRECCION");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DIRECCION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMERO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NUMERO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEPTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEPTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VILLAPOBLACION");
        elemField.setXmlName(new javax.xml.namespace.QName("", "VILLAPOBLACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODIGOREGION");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CODIGOREGION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODIGOPROVINCIA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CODIGOPROVINCIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODIGOCOMUNA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CODIGOCOMUNA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EMAIL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EMAIL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CELULAR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CELULAR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TELEFONO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TELEFONO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMEROCUENTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NUMEROCUENTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPOCUENTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TIPOCUENTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODIGOBANCO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CODIGOBANCO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DECLARACIONJURADA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DECLARACIONJURADA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NACIONALIDAD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NACIONALIDAD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
