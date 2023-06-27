/**
 * LmeTrabType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package WsLMEInet_pkg;

public class LmeTrabType  implements java.io.Serializable {
    private java.math.BigInteger idLicencia;

    private java.lang.String dvLicencia;

    private java.math.BigInteger lmeEstado;

    private java.lang.String paternoTrabajador;

    private java.lang.String maternoTrabajador;

    private java.lang.String nombreTrabajador;

    private java.util.Calendar fechaEmision;

    private java.util.Calendar fechaInicioReposo;

    private java.math.BigInteger diasLicencia;

    private java.math.BigInteger tipoLicencia;

    private java.math.BigInteger recuperabilidad;

    private java.math.BigInteger rutProfesional;

    private java.lang.String digProfesional;

    private java.lang.String paternoProfesional;

    private java.lang.String maternoProfesional;

    private java.lang.String nombreProfesional;

    private java.math.BigInteger tipoLmeResuelta;

    private java.math.BigInteger diasAutorizados;

    private java.util.Date fecAutDesde;

    private java.util.Date fecAutHasta;

    private java.math.BigInteger tipResolucion;

    private java.math.BigInteger tipoReposoAutoriz;

    private java.lang.String jornadaRepoAut;

    private java.lang.String derechoSubsidio;

    private java.math.BigInteger codCausaRechazo;

    private java.lang.String gloCausaRechazo;

    private java.math.BigInteger rutEmpleador;

    private java.lang.String digEmpleador;

    private java.lang.String nombreEmpleador;

    private java.lang.String paternoEmpleador;

    private java.lang.String maternoEmpleador;

    private WsLMEInet_pkg.Liquidacion[] listaLiquid;

    public LmeTrabType() {
    }

    public LmeTrabType(
           java.math.BigInteger idLicencia,
           java.lang.String dvLicencia,
           java.math.BigInteger lmeEstado,
           java.lang.String paternoTrabajador,
           java.lang.String maternoTrabajador,
           java.lang.String nombreTrabajador,
           java.util.Calendar fechaEmision,
           java.util.Calendar fechaInicioReposo,
           java.math.BigInteger diasLicencia,
           java.math.BigInteger tipoLicencia,
           java.math.BigInteger recuperabilidad,
           java.math.BigInteger rutProfesional,
           java.lang.String digProfesional,
           java.lang.String paternoProfesional,
           java.lang.String maternoProfesional,
           java.lang.String nombreProfesional,
           java.math.BigInteger tipoLmeResuelta,
           java.math.BigInteger diasAutorizados,
           java.util.Date fecAutDesde,
           java.util.Date fecAutHasta,
           java.math.BigInteger tipResolucion,
           java.math.BigInteger tipoReposoAutoriz,
           java.lang.String jornadaRepoAut,
           java.lang.String derechoSubsidio,
           java.math.BigInteger codCausaRechazo,
           java.lang.String gloCausaRechazo,
           java.math.BigInteger rutEmpleador,
           java.lang.String digEmpleador,
           java.lang.String nombreEmpleador,
           java.lang.String paternoEmpleador,
           java.lang.String maternoEmpleador,
           WsLMEInet_pkg.Liquidacion[] listaLiquid) {
           this.idLicencia = idLicencia;
           this.dvLicencia = dvLicencia;
           this.lmeEstado = lmeEstado;
           this.paternoTrabajador = paternoTrabajador;
           this.maternoTrabajador = maternoTrabajador;
           this.nombreTrabajador = nombreTrabajador;
           this.fechaEmision = fechaEmision;
           this.fechaInicioReposo = fechaInicioReposo;
           this.diasLicencia = diasLicencia;
           this.tipoLicencia = tipoLicencia;
           this.recuperabilidad = recuperabilidad;
           this.rutProfesional = rutProfesional;
           this.digProfesional = digProfesional;
           this.paternoProfesional = paternoProfesional;
           this.maternoProfesional = maternoProfesional;
           this.nombreProfesional = nombreProfesional;
           this.tipoLmeResuelta = tipoLmeResuelta;
           this.diasAutorizados = diasAutorizados;
           this.fecAutDesde = fecAutDesde;
           this.fecAutHasta = fecAutHasta;
           this.tipResolucion = tipResolucion;
           this.tipoReposoAutoriz = tipoReposoAutoriz;
           this.jornadaRepoAut = jornadaRepoAut;
           this.derechoSubsidio = derechoSubsidio;
           this.codCausaRechazo = codCausaRechazo;
           this.gloCausaRechazo = gloCausaRechazo;
           this.rutEmpleador = rutEmpleador;
           this.digEmpleador = digEmpleador;
           this.nombreEmpleador = nombreEmpleador;
           this.paternoEmpleador = paternoEmpleador;
           this.maternoEmpleador = maternoEmpleador;
           this.listaLiquid = listaLiquid;
    }


    /**
     * Gets the idLicencia value for this LmeTrabType.
     * 
     * @return idLicencia
     */
    public java.math.BigInteger getIdLicencia() {
        return idLicencia;
    }


    /**
     * Sets the idLicencia value for this LmeTrabType.
     * 
     * @param idLicencia
     */
    public void setIdLicencia(java.math.BigInteger idLicencia) {
        this.idLicencia = idLicencia;
    }


    /**
     * Gets the dvLicencia value for this LmeTrabType.
     * 
     * @return dvLicencia
     */
    public java.lang.String getDvLicencia() {
        return dvLicencia;
    }


    /**
     * Sets the dvLicencia value for this LmeTrabType.
     * 
     * @param dvLicencia
     */
    public void setDvLicencia(java.lang.String dvLicencia) {
        this.dvLicencia = dvLicencia;
    }


    /**
     * Gets the lmeEstado value for this LmeTrabType.
     * 
     * @return lmeEstado
     */
    public java.math.BigInteger getLmeEstado() {
        return lmeEstado;
    }


    /**
     * Sets the lmeEstado value for this LmeTrabType.
     * 
     * @param lmeEstado
     */
    public void setLmeEstado(java.math.BigInteger lmeEstado) {
        this.lmeEstado = lmeEstado;
    }


    /**
     * Gets the paternoTrabajador value for this LmeTrabType.
     * 
     * @return paternoTrabajador
     */
    public java.lang.String getPaternoTrabajador() {
        return paternoTrabajador;
    }


    /**
     * Sets the paternoTrabajador value for this LmeTrabType.
     * 
     * @param paternoTrabajador
     */
    public void setPaternoTrabajador(java.lang.String paternoTrabajador) {
        this.paternoTrabajador = paternoTrabajador;
    }


    /**
     * Gets the maternoTrabajador value for this LmeTrabType.
     * 
     * @return maternoTrabajador
     */
    public java.lang.String getMaternoTrabajador() {
        return maternoTrabajador;
    }


    /**
     * Sets the maternoTrabajador value for this LmeTrabType.
     * 
     * @param maternoTrabajador
     */
    public void setMaternoTrabajador(java.lang.String maternoTrabajador) {
        this.maternoTrabajador = maternoTrabajador;
    }


    /**
     * Gets the nombreTrabajador value for this LmeTrabType.
     * 
     * @return nombreTrabajador
     */
    public java.lang.String getNombreTrabajador() {
        return nombreTrabajador;
    }


    /**
     * Sets the nombreTrabajador value for this LmeTrabType.
     * 
     * @param nombreTrabajador
     */
    public void setNombreTrabajador(java.lang.String nombreTrabajador) {
        this.nombreTrabajador = nombreTrabajador;
    }


    /**
     * Gets the fechaEmision value for this LmeTrabType.
     * 
     * @return fechaEmision
     */
    public java.util.Calendar getFechaEmision() {
        return fechaEmision;
    }


    /**
     * Sets the fechaEmision value for this LmeTrabType.
     * 
     * @param fechaEmision
     */
    public void setFechaEmision(java.util.Calendar fechaEmision) {
        this.fechaEmision = fechaEmision;
    }


    /**
     * Gets the fechaInicioReposo value for this LmeTrabType.
     * 
     * @return fechaInicioReposo
     */
    public java.util.Calendar getFechaInicioReposo() {
        return fechaInicioReposo;
    }


    /**
     * Sets the fechaInicioReposo value for this LmeTrabType.
     * 
     * @param fechaInicioReposo
     */
    public void setFechaInicioReposo(java.util.Calendar fechaInicioReposo) {
        this.fechaInicioReposo = fechaInicioReposo;
    }


    /**
     * Gets the diasLicencia value for this LmeTrabType.
     * 
     * @return diasLicencia
     */
    public java.math.BigInteger getDiasLicencia() {
        return diasLicencia;
    }


    /**
     * Sets the diasLicencia value for this LmeTrabType.
     * 
     * @param diasLicencia
     */
    public void setDiasLicencia(java.math.BigInteger diasLicencia) {
        this.diasLicencia = diasLicencia;
    }


    /**
     * Gets the tipoLicencia value for this LmeTrabType.
     * 
     * @return tipoLicencia
     */
    public java.math.BigInteger getTipoLicencia() {
        return tipoLicencia;
    }


    /**
     * Sets the tipoLicencia value for this LmeTrabType.
     * 
     * @param tipoLicencia
     */
    public void setTipoLicencia(java.math.BigInteger tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }


    /**
     * Gets the recuperabilidad value for this LmeTrabType.
     * 
     * @return recuperabilidad
     */
    public java.math.BigInteger getRecuperabilidad() {
        return recuperabilidad;
    }


    /**
     * Sets the recuperabilidad value for this LmeTrabType.
     * 
     * @param recuperabilidad
     */
    public void setRecuperabilidad(java.math.BigInteger recuperabilidad) {
        this.recuperabilidad = recuperabilidad;
    }


    /**
     * Gets the rutProfesional value for this LmeTrabType.
     * 
     * @return rutProfesional
     */
    public java.math.BigInteger getRutProfesional() {
        return rutProfesional;
    }


    /**
     * Sets the rutProfesional value for this LmeTrabType.
     * 
     * @param rutProfesional
     */
    public void setRutProfesional(java.math.BigInteger rutProfesional) {
        this.rutProfesional = rutProfesional;
    }


    /**
     * Gets the digProfesional value for this LmeTrabType.
     * 
     * @return digProfesional
     */
    public java.lang.String getDigProfesional() {
        return digProfesional;
    }


    /**
     * Sets the digProfesional value for this LmeTrabType.
     * 
     * @param digProfesional
     */
    public void setDigProfesional(java.lang.String digProfesional) {
        this.digProfesional = digProfesional;
    }


    /**
     * Gets the paternoProfesional value for this LmeTrabType.
     * 
     * @return paternoProfesional
     */
    public java.lang.String getPaternoProfesional() {
        return paternoProfesional;
    }


    /**
     * Sets the paternoProfesional value for this LmeTrabType.
     * 
     * @param paternoProfesional
     */
    public void setPaternoProfesional(java.lang.String paternoProfesional) {
        this.paternoProfesional = paternoProfesional;
    }


    /**
     * Gets the maternoProfesional value for this LmeTrabType.
     * 
     * @return maternoProfesional
     */
    public java.lang.String getMaternoProfesional() {
        return maternoProfesional;
    }


    /**
     * Sets the maternoProfesional value for this LmeTrabType.
     * 
     * @param maternoProfesional
     */
    public void setMaternoProfesional(java.lang.String maternoProfesional) {
        this.maternoProfesional = maternoProfesional;
    }


    /**
     * Gets the nombreProfesional value for this LmeTrabType.
     * 
     * @return nombreProfesional
     */
    public java.lang.String getNombreProfesional() {
        return nombreProfesional;
    }


    /**
     * Sets the nombreProfesional value for this LmeTrabType.
     * 
     * @param nombreProfesional
     */
    public void setNombreProfesional(java.lang.String nombreProfesional) {
        this.nombreProfesional = nombreProfesional;
    }


    /**
     * Gets the tipoLmeResuelta value for this LmeTrabType.
     * 
     * @return tipoLmeResuelta
     */
    public java.math.BigInteger getTipoLmeResuelta() {
        return tipoLmeResuelta;
    }


    /**
     * Sets the tipoLmeResuelta value for this LmeTrabType.
     * 
     * @param tipoLmeResuelta
     */
    public void setTipoLmeResuelta(java.math.BigInteger tipoLmeResuelta) {
        this.tipoLmeResuelta = tipoLmeResuelta;
    }


    /**
     * Gets the diasAutorizados value for this LmeTrabType.
     * 
     * @return diasAutorizados
     */
    public java.math.BigInteger getDiasAutorizados() {
        return diasAutorizados;
    }


    /**
     * Sets the diasAutorizados value for this LmeTrabType.
     * 
     * @param diasAutorizados
     */
    public void setDiasAutorizados(java.math.BigInteger diasAutorizados) {
        this.diasAutorizados = diasAutorizados;
    }


    /**
     * Gets the fecAutDesde value for this LmeTrabType.
     * 
     * @return fecAutDesde
     */
    public java.util.Date getFecAutDesde() {
        return fecAutDesde;
    }


    /**
     * Sets the fecAutDesde value for this LmeTrabType.
     * 
     * @param fecAutDesde
     */
    public void setFecAutDesde(java.util.Date fecAutDesde) {
        this.fecAutDesde = fecAutDesde;
    }


    /**
     * Gets the fecAutHasta value for this LmeTrabType.
     * 
     * @return fecAutHasta
     */
    public java.util.Date getFecAutHasta() {
        return fecAutHasta;
    }


    /**
     * Sets the fecAutHasta value for this LmeTrabType.
     * 
     * @param fecAutHasta
     */
    public void setFecAutHasta(java.util.Date fecAutHasta) {
        this.fecAutHasta = fecAutHasta;
    }


    /**
     * Gets the tipResolucion value for this LmeTrabType.
     * 
     * @return tipResolucion
     */
    public java.math.BigInteger getTipResolucion() {
        return tipResolucion;
    }


    /**
     * Sets the tipResolucion value for this LmeTrabType.
     * 
     * @param tipResolucion
     */
    public void setTipResolucion(java.math.BigInteger tipResolucion) {
        this.tipResolucion = tipResolucion;
    }


    /**
     * Gets the tipoReposoAutoriz value for this LmeTrabType.
     * 
     * @return tipoReposoAutoriz
     */
    public java.math.BigInteger getTipoReposoAutoriz() {
        return tipoReposoAutoriz;
    }


    /**
     * Sets the tipoReposoAutoriz value for this LmeTrabType.
     * 
     * @param tipoReposoAutoriz
     */
    public void setTipoReposoAutoriz(java.math.BigInteger tipoReposoAutoriz) {
        this.tipoReposoAutoriz = tipoReposoAutoriz;
    }


    /**
     * Gets the jornadaRepoAut value for this LmeTrabType.
     * 
     * @return jornadaRepoAut
     */
    public java.lang.String getJornadaRepoAut() {
        return jornadaRepoAut;
    }


    /**
     * Sets the jornadaRepoAut value for this LmeTrabType.
     * 
     * @param jornadaRepoAut
     */
    public void setJornadaRepoAut(java.lang.String jornadaRepoAut) {
        this.jornadaRepoAut = jornadaRepoAut;
    }


    /**
     * Gets the derechoSubsidio value for this LmeTrabType.
     * 
     * @return derechoSubsidio
     */
    public java.lang.String getDerechoSubsidio() {
        return derechoSubsidio;
    }


    /**
     * Sets the derechoSubsidio value for this LmeTrabType.
     * 
     * @param derechoSubsidio
     */
    public void setDerechoSubsidio(java.lang.String derechoSubsidio) {
        this.derechoSubsidio = derechoSubsidio;
    }


    /**
     * Gets the codCausaRechazo value for this LmeTrabType.
     * 
     * @return codCausaRechazo
     */
    public java.math.BigInteger getCodCausaRechazo() {
        return codCausaRechazo;
    }


    /**
     * Sets the codCausaRechazo value for this LmeTrabType.
     * 
     * @param codCausaRechazo
     */
    public void setCodCausaRechazo(java.math.BigInteger codCausaRechazo) {
        this.codCausaRechazo = codCausaRechazo;
    }


    /**
     * Gets the gloCausaRechazo value for this LmeTrabType.
     * 
     * @return gloCausaRechazo
     */
    public java.lang.String getGloCausaRechazo() {
        return gloCausaRechazo;
    }


    /**
     * Sets the gloCausaRechazo value for this LmeTrabType.
     * 
     * @param gloCausaRechazo
     */
    public void setGloCausaRechazo(java.lang.String gloCausaRechazo) {
        this.gloCausaRechazo = gloCausaRechazo;
    }


    /**
     * Gets the rutEmpleador value for this LmeTrabType.
     * 
     * @return rutEmpleador
     */
    public java.math.BigInteger getRutEmpleador() {
        return rutEmpleador;
    }


    /**
     * Sets the rutEmpleador value for this LmeTrabType.
     * 
     * @param rutEmpleador
     */
    public void setRutEmpleador(java.math.BigInteger rutEmpleador) {
        this.rutEmpleador = rutEmpleador;
    }


    /**
     * Gets the digEmpleador value for this LmeTrabType.
     * 
     * @return digEmpleador
     */
    public java.lang.String getDigEmpleador() {
        return digEmpleador;
    }


    /**
     * Sets the digEmpleador value for this LmeTrabType.
     * 
     * @param digEmpleador
     */
    public void setDigEmpleador(java.lang.String digEmpleador) {
        this.digEmpleador = digEmpleador;
    }


    /**
     * Gets the nombreEmpleador value for this LmeTrabType.
     * 
     * @return nombreEmpleador
     */
    public java.lang.String getNombreEmpleador() {
        return nombreEmpleador;
    }


    /**
     * Sets the nombreEmpleador value for this LmeTrabType.
     * 
     * @param nombreEmpleador
     */
    public void setNombreEmpleador(java.lang.String nombreEmpleador) {
        this.nombreEmpleador = nombreEmpleador;
    }


    /**
     * Gets the paternoEmpleador value for this LmeTrabType.
     * 
     * @return paternoEmpleador
     */
    public java.lang.String getPaternoEmpleador() {
        return paternoEmpleador;
    }


    /**
     * Sets the paternoEmpleador value for this LmeTrabType.
     * 
     * @param paternoEmpleador
     */
    public void setPaternoEmpleador(java.lang.String paternoEmpleador) {
        this.paternoEmpleador = paternoEmpleador;
    }


    /**
     * Gets the maternoEmpleador value for this LmeTrabType.
     * 
     * @return maternoEmpleador
     */
    public java.lang.String getMaternoEmpleador() {
        return maternoEmpleador;
    }


    /**
     * Sets the maternoEmpleador value for this LmeTrabType.
     * 
     * @param maternoEmpleador
     */
    public void setMaternoEmpleador(java.lang.String maternoEmpleador) {
        this.maternoEmpleador = maternoEmpleador;
    }


    /**
     * Gets the listaLiquid value for this LmeTrabType.
     * 
     * @return listaLiquid
     */
    public WsLMEInet_pkg.Liquidacion[] getListaLiquid() {
        return listaLiquid;
    }


    /**
     * Sets the listaLiquid value for this LmeTrabType.
     * 
     * @param listaLiquid
     */
    public void setListaLiquid(WsLMEInet_pkg.Liquidacion[] listaLiquid) {
        this.listaLiquid = listaLiquid;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LmeTrabType)) return false;
        LmeTrabType other = (LmeTrabType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idLicencia==null && other.getIdLicencia()==null) || 
             (this.idLicencia!=null &&
              this.idLicencia.equals(other.getIdLicencia()))) &&
            ((this.dvLicencia==null && other.getDvLicencia()==null) || 
             (this.dvLicencia!=null &&
              this.dvLicencia.equals(other.getDvLicencia()))) &&
            ((this.lmeEstado==null && other.getLmeEstado()==null) || 
             (this.lmeEstado!=null &&
              this.lmeEstado.equals(other.getLmeEstado()))) &&
            ((this.paternoTrabajador==null && other.getPaternoTrabajador()==null) || 
             (this.paternoTrabajador!=null &&
              this.paternoTrabajador.equals(other.getPaternoTrabajador()))) &&
            ((this.maternoTrabajador==null && other.getMaternoTrabajador()==null) || 
             (this.maternoTrabajador!=null &&
              this.maternoTrabajador.equals(other.getMaternoTrabajador()))) &&
            ((this.nombreTrabajador==null && other.getNombreTrabajador()==null) || 
             (this.nombreTrabajador!=null &&
              this.nombreTrabajador.equals(other.getNombreTrabajador()))) &&
            ((this.fechaEmision==null && other.getFechaEmision()==null) || 
             (this.fechaEmision!=null &&
              this.fechaEmision.equals(other.getFechaEmision()))) &&
            ((this.fechaInicioReposo==null && other.getFechaInicioReposo()==null) || 
             (this.fechaInicioReposo!=null &&
              this.fechaInicioReposo.equals(other.getFechaInicioReposo()))) &&
            ((this.diasLicencia==null && other.getDiasLicencia()==null) || 
             (this.diasLicencia!=null &&
              this.diasLicencia.equals(other.getDiasLicencia()))) &&
            ((this.tipoLicencia==null && other.getTipoLicencia()==null) || 
             (this.tipoLicencia!=null &&
              this.tipoLicencia.equals(other.getTipoLicencia()))) &&
            ((this.recuperabilidad==null && other.getRecuperabilidad()==null) || 
             (this.recuperabilidad!=null &&
              this.recuperabilidad.equals(other.getRecuperabilidad()))) &&
            ((this.rutProfesional==null && other.getRutProfesional()==null) || 
             (this.rutProfesional!=null &&
              this.rutProfesional.equals(other.getRutProfesional()))) &&
            ((this.digProfesional==null && other.getDigProfesional()==null) || 
             (this.digProfesional!=null &&
              this.digProfesional.equals(other.getDigProfesional()))) &&
            ((this.paternoProfesional==null && other.getPaternoProfesional()==null) || 
             (this.paternoProfesional!=null &&
              this.paternoProfesional.equals(other.getPaternoProfesional()))) &&
            ((this.maternoProfesional==null && other.getMaternoProfesional()==null) || 
             (this.maternoProfesional!=null &&
              this.maternoProfesional.equals(other.getMaternoProfesional()))) &&
            ((this.nombreProfesional==null && other.getNombreProfesional()==null) || 
             (this.nombreProfesional!=null &&
              this.nombreProfesional.equals(other.getNombreProfesional()))) &&
            ((this.tipoLmeResuelta==null && other.getTipoLmeResuelta()==null) || 
             (this.tipoLmeResuelta!=null &&
              this.tipoLmeResuelta.equals(other.getTipoLmeResuelta()))) &&
            ((this.diasAutorizados==null && other.getDiasAutorizados()==null) || 
             (this.diasAutorizados!=null &&
              this.diasAutorizados.equals(other.getDiasAutorizados()))) &&
            ((this.fecAutDesde==null && other.getFecAutDesde()==null) || 
             (this.fecAutDesde!=null &&
              this.fecAutDesde.equals(other.getFecAutDesde()))) &&
            ((this.fecAutHasta==null && other.getFecAutHasta()==null) || 
             (this.fecAutHasta!=null &&
              this.fecAutHasta.equals(other.getFecAutHasta()))) &&
            ((this.tipResolucion==null && other.getTipResolucion()==null) || 
             (this.tipResolucion!=null &&
              this.tipResolucion.equals(other.getTipResolucion()))) &&
            ((this.tipoReposoAutoriz==null && other.getTipoReposoAutoriz()==null) || 
             (this.tipoReposoAutoriz!=null &&
              this.tipoReposoAutoriz.equals(other.getTipoReposoAutoriz()))) &&
            ((this.jornadaRepoAut==null && other.getJornadaRepoAut()==null) || 
             (this.jornadaRepoAut!=null &&
              this.jornadaRepoAut.equals(other.getJornadaRepoAut()))) &&
            ((this.derechoSubsidio==null && other.getDerechoSubsidio()==null) || 
             (this.derechoSubsidio!=null &&
              this.derechoSubsidio.equals(other.getDerechoSubsidio()))) &&
            ((this.codCausaRechazo==null && other.getCodCausaRechazo()==null) || 
             (this.codCausaRechazo!=null &&
              this.codCausaRechazo.equals(other.getCodCausaRechazo()))) &&
            ((this.gloCausaRechazo==null && other.getGloCausaRechazo()==null) || 
             (this.gloCausaRechazo!=null &&
              this.gloCausaRechazo.equals(other.getGloCausaRechazo()))) &&
            ((this.rutEmpleador==null && other.getRutEmpleador()==null) || 
             (this.rutEmpleador!=null &&
              this.rutEmpleador.equals(other.getRutEmpleador()))) &&
            ((this.digEmpleador==null && other.getDigEmpleador()==null) || 
             (this.digEmpleador!=null &&
              this.digEmpleador.equals(other.getDigEmpleador()))) &&
            ((this.nombreEmpleador==null && other.getNombreEmpleador()==null) || 
             (this.nombreEmpleador!=null &&
              this.nombreEmpleador.equals(other.getNombreEmpleador()))) &&
            ((this.paternoEmpleador==null && other.getPaternoEmpleador()==null) || 
             (this.paternoEmpleador!=null &&
              this.paternoEmpleador.equals(other.getPaternoEmpleador()))) &&
            ((this.maternoEmpleador==null && other.getMaternoEmpleador()==null) || 
             (this.maternoEmpleador!=null &&
              this.maternoEmpleador.equals(other.getMaternoEmpleador()))) &&
            ((this.listaLiquid==null && other.getListaLiquid()==null) || 
             (this.listaLiquid!=null &&
              java.util.Arrays.equals(this.listaLiquid, other.getListaLiquid())));
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
        if (getIdLicencia() != null) {
            _hashCode += getIdLicencia().hashCode();
        }
        if (getDvLicencia() != null) {
            _hashCode += getDvLicencia().hashCode();
        }
        if (getLmeEstado() != null) {
            _hashCode += getLmeEstado().hashCode();
        }
        if (getPaternoTrabajador() != null) {
            _hashCode += getPaternoTrabajador().hashCode();
        }
        if (getMaternoTrabajador() != null) {
            _hashCode += getMaternoTrabajador().hashCode();
        }
        if (getNombreTrabajador() != null) {
            _hashCode += getNombreTrabajador().hashCode();
        }
        if (getFechaEmision() != null) {
            _hashCode += getFechaEmision().hashCode();
        }
        if (getFechaInicioReposo() != null) {
            _hashCode += getFechaInicioReposo().hashCode();
        }
        if (getDiasLicencia() != null) {
            _hashCode += getDiasLicencia().hashCode();
        }
        if (getTipoLicencia() != null) {
            _hashCode += getTipoLicencia().hashCode();
        }
        if (getRecuperabilidad() != null) {
            _hashCode += getRecuperabilidad().hashCode();
        }
        if (getRutProfesional() != null) {
            _hashCode += getRutProfesional().hashCode();
        }
        if (getDigProfesional() != null) {
            _hashCode += getDigProfesional().hashCode();
        }
        if (getPaternoProfesional() != null) {
            _hashCode += getPaternoProfesional().hashCode();
        }
        if (getMaternoProfesional() != null) {
            _hashCode += getMaternoProfesional().hashCode();
        }
        if (getNombreProfesional() != null) {
            _hashCode += getNombreProfesional().hashCode();
        }
        if (getTipoLmeResuelta() != null) {
            _hashCode += getTipoLmeResuelta().hashCode();
        }
        if (getDiasAutorizados() != null) {
            _hashCode += getDiasAutorizados().hashCode();
        }
        if (getFecAutDesde() != null) {
            _hashCode += getFecAutDesde().hashCode();
        }
        if (getFecAutHasta() != null) {
            _hashCode += getFecAutHasta().hashCode();
        }
        if (getTipResolucion() != null) {
            _hashCode += getTipResolucion().hashCode();
        }
        if (getTipoReposoAutoriz() != null) {
            _hashCode += getTipoReposoAutoriz().hashCode();
        }
        if (getJornadaRepoAut() != null) {
            _hashCode += getJornadaRepoAut().hashCode();
        }
        if (getDerechoSubsidio() != null) {
            _hashCode += getDerechoSubsidio().hashCode();
        }
        if (getCodCausaRechazo() != null) {
            _hashCode += getCodCausaRechazo().hashCode();
        }
        if (getGloCausaRechazo() != null) {
            _hashCode += getGloCausaRechazo().hashCode();
        }
        if (getRutEmpleador() != null) {
            _hashCode += getRutEmpleador().hashCode();
        }
        if (getDigEmpleador() != null) {
            _hashCode += getDigEmpleador().hashCode();
        }
        if (getNombreEmpleador() != null) {
            _hashCode += getNombreEmpleador().hashCode();
        }
        if (getPaternoEmpleador() != null) {
            _hashCode += getPaternoEmpleador().hashCode();
        }
        if (getMaternoEmpleador() != null) {
            _hashCode += getMaternoEmpleador().hashCode();
        }
        if (getListaLiquid() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaLiquid());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaLiquid(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LmeTrabType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:WsLMEInet", "LmeTrabType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idLicencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IdLicencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dvLicencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DvLicencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lmeEstado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LmeEstado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paternoTrabajador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PaternoTrabajador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maternoTrabajador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MaternoTrabajador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreTrabajador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NombreTrabajador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaEmision");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FechaEmision"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaInicioReposo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FechaInicioReposo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("diasLicencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DiasLicencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoLicencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TipoLicencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recuperabilidad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Recuperabilidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rutProfesional");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RutProfesional"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("digProfesional");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DigProfesional"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paternoProfesional");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PaternoProfesional"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maternoProfesional");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MaternoProfesional"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreProfesional");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NombreProfesional"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoLmeResuelta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TipoLmeResuelta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("diasAutorizados");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DiasAutorizados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecAutDesde");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FecAutDesde"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecAutHasta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FecAutHasta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipResolucion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TipResolucion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoReposoAutoriz");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TipoReposoAutoriz"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jornadaRepoAut");
        elemField.setXmlName(new javax.xml.namespace.QName("", "JornadaRepoAut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("derechoSubsidio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DerechoSubsidio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codCausaRechazo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CodCausaRechazo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gloCausaRechazo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GloCausaRechazo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rutEmpleador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RutEmpleador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("digEmpleador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DigEmpleador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreEmpleador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NombreEmpleador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paternoEmpleador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PaternoEmpleador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maternoEmpleador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MaternoEmpleador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaLiquid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ListaLiquid"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:WsLMEInet", "Liquidacion"));
        elemField.setMinOccurs(0);
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
