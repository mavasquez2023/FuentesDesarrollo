package cse.database.objects;

import java.math.BigDecimal;
import java.util.Date;

public class Solicitud {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column MotorCreditScoring.dbo.Solicitud.IdSolicitud
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    private String idsolicitud;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column MotorCreditScoring.dbo.Solicitud.RUT
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    private Integer rut;
    private String dv;
    private Integer tipoAfiliado;
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column MotorCreditScoring.dbo.Solicitud.IdGenero
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    private String idgenero;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column MotorCreditScoring.dbo.Solicitud.IdEstadoCivil
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    private Integer idestadocivil;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column MotorCreditScoring.dbo.Solicitud.FechaNacimiento
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    private String fechanacimiento;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column MotorCreditScoring.dbo.Solicitud.Remuneracion
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    private Integer remuneracion;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column MotorCreditScoring.dbo.Solicitud.FechaMorosidad
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    private String fechamorosidad;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column MotorCreditScoring.dbo.Solicitud.DiasMorosidad
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    private Integer diasmorosidad;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column MotorCreditScoring.dbo.Solicitud.CreditosAnteriores
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    private Integer creditosanteriores;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column MotorCreditScoring.dbo.Solicitud.LicenciaMedica
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    private Integer licenciamedica;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column MotorCreditScoring.dbo.Solicitud.MontoNominalSolicitado
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    private Integer montonominalsolicitado;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column MotorCreditScoring.dbo.Solicitud.RUTEmpresa
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    private Integer rutempresa;
    private String dvempresa;
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column MotorCreditScoring.dbo.Solicitud.IdClasificacionEmpresa
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    private Integer idclasificacionempresa;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column MotorCreditScoring.dbo.Solicitud.FechaAntiguedadLaboral
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    private String fechaantiguedadlaboral;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column MotorCreditScoring.dbo.Solicitud.AntiguedadLaboral
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    private Integer antiguedadlaboral;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column MotorCreditScoring.dbo.Solicitud.RiesgoExterno
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    private BigDecimal riesgoexterno;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column MotorCreditScoring.dbo.Solicitud.NroSueldos
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    private String nrosueldos;

    private Integer maximomontonominalotorgable;
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column MotorCreditScoring.dbo.Solicitud.ScoreModeloPersonas
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    private BigDecimal scoremodelopersonas;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column MotorCreditScoring.dbo.Solicitud.PerfilRiesgo
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    private String perfilriesgo;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column MotorCreditScoring.dbo.Solicitud.FechaCreacion
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    private Date fechacreacion;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column MotorCreditScoring.dbo.Solicitud.IdSolicitud
     *
     * @return the value of MotorCreditScoring.dbo.Solicitud.IdSolicitud
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public String getIdsolicitud() {
        return idsolicitud;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column MotorCreditScoring.dbo.Solicitud.IdSolicitud
     *
     * @param idsolicitud the value for MotorCreditScoring.dbo.Solicitud.IdSolicitud
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public void setIdsolicitud(String idsolicitud) {
        this.idsolicitud = idsolicitud == null ? null : idsolicitud.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column MotorCreditScoring.dbo.Solicitud.RUT
     *
     * @return the value of MotorCreditScoring.dbo.Solicitud.RUT
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public Integer getRut() {
        return rut;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column MotorCreditScoring.dbo.Solicitud.RUT
     *
     * @param rut the value for MotorCreditScoring.dbo.Solicitud.RUT
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public void setRut(Integer rut) {
        this.rut = rut;
    }

    public String getDv() {
    	return dv;
    }
    
    public void setDv(String dv) {
        this.dv = dv == null ? null : dv.trim();
    }
    
    public void setTipoAfiliado(int tipoAfiliado) {
    	this.tipoAfiliado = new Integer(tipoAfiliado);
	}

    public void setTipoAfiliado(Integer tipoAfiliado) {
    	this.tipoAfiliado = new Integer(tipoAfiliado.intValue());
	}
    
	public Integer getTipoAfiliado() {
		return tipoAfiliado;
	}

	/**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column MotorCreditScoring.dbo.Solicitud.IdGenero
     *
     * @return the value of MotorCreditScoring.dbo.Solicitud.IdGenero
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public String getIdgenero() {
        return idgenero;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column MotorCreditScoring.dbo.Solicitud.IdGenero
     *
     * @param idgenero the value for MotorCreditScoring.dbo.Solicitud.IdGenero
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public void setIdgenero(String idgenero) {
        this.idgenero = idgenero == null ? null : idgenero.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column MotorCreditScoring.dbo.Solicitud.IdEstadoCivil
     *
     * @return the value of MotorCreditScoring.dbo.Solicitud.IdEstadoCivil
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public Integer getIdestadocivil() {
        return idestadocivil;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column MotorCreditScoring.dbo.Solicitud.IdEstadoCivil
     *
     * @param idestadocivil the value for MotorCreditScoring.dbo.Solicitud.IdEstadoCivil
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public void setIdestadocivil(Integer idestadocivil) {
        this.idestadocivil = idestadocivil;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column MotorCreditScoring.dbo.Solicitud.FechaNacimiento
     *
     * @return the value of MotorCreditScoring.dbo.Solicitud.FechaNacimiento
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public String getFechanacimiento() {
        return fechanacimiento;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column MotorCreditScoring.dbo.Solicitud.FechaNacimiento
     *
     * @param fechanacimiento the value for MotorCreditScoring.dbo.Solicitud.FechaNacimiento
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento == null ? null : fechanacimiento.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column MotorCreditScoring.dbo.Solicitud.Remuneracion
     *
     * @return the value of MotorCreditScoring.dbo.Solicitud.Remuneracion
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public Integer getRemuneracion() {
        return remuneracion;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column MotorCreditScoring.dbo.Solicitud.Remuneracion
     *
     * @param remuneracion the value for MotorCreditScoring.dbo.Solicitud.Remuneracion
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public void setRemuneracion(Integer remuneracion) {
        this.remuneracion = remuneracion;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column MotorCreditScoring.dbo.Solicitud.FechaMorosidad
     *
     * @return the value of MotorCreditScoring.dbo.Solicitud.FechaMorosidad
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public String getFechamorosidad() {
        return fechamorosidad;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column MotorCreditScoring.dbo.Solicitud.FechaMorosidad
     *
     * @param fechamorosidad the value for MotorCreditScoring.dbo.Solicitud.FechaMorosidad
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public void setFechamorosidad(String fechamorosidad) {
        this.fechamorosidad = fechamorosidad == null ? null : fechamorosidad.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column MotorCreditScoring.dbo.Solicitud.DiasMorosidad
     *
     * @return the value of MotorCreditScoring.dbo.Solicitud.DiasMorosidad
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public Integer getDiasmorosidad() {
        return diasmorosidad;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column MotorCreditScoring.dbo.Solicitud.DiasMorosidad
     *
     * @param diasmorosidad the value for MotorCreditScoring.dbo.Solicitud.DiasMorosidad
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public void setDiasmorosidad(Integer diasmorosidad) {
        this.diasmorosidad = diasmorosidad;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column MotorCreditScoring.dbo.Solicitud.CreditosAnteriores
     *
     * @return the value of MotorCreditScoring.dbo.Solicitud.CreditosAnteriores
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public Integer getCreditosanteriores() {
        return creditosanteriores;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column MotorCreditScoring.dbo.Solicitud.CreditosAnteriores
     *
     * @param creditosanteriores the value for MotorCreditScoring.dbo.Solicitud.CreditosAnteriores
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public void setCreditosanteriores(Integer creditosanteriores) {
        this.creditosanteriores = creditosanteriores;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column MotorCreditScoring.dbo.Solicitud.LicenciaMedica
     *
     * @return the value of MotorCreditScoring.dbo.Solicitud.LicenciaMedica
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public Integer getLicenciamedica() {
        return licenciamedica;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column MotorCreditScoring.dbo.Solicitud.LicenciaMedica
     *
     * @param licenciamedica the value for MotorCreditScoring.dbo.Solicitud.LicenciaMedica
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public void setLicenciamedica(Integer licenciamedica) {
        this.licenciamedica = licenciamedica;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column MotorCreditScoring.dbo.Solicitud.MontoNominalSolicitado
     *
     * @return the value of MotorCreditScoring.dbo.Solicitud.MontoNominalSolicitado
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public Integer getMontonominalsolicitado() {
        return montonominalsolicitado;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column MotorCreditScoring.dbo.Solicitud.MontoNominalSolicitado
     *
     * @param montonominalsolicitado the value for MotorCreditScoring.dbo.Solicitud.MontoNominalSolicitado
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public void setMontonominalsolicitado(Integer montonominalsolicitado) {
        this.montonominalsolicitado = montonominalsolicitado;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column MotorCreditScoring.dbo.Solicitud.RUTEmpresa
     *
     * @return the value of MotorCreditScoring.dbo.Solicitud.RUTEmpresa
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public Integer getRutempresa() {
        return rutempresa;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column MotorCreditScoring.dbo.Solicitud.RUTEmpresa
     *
     * @param rutempresa the value for MotorCreditScoring.dbo.Solicitud.RUTEmpresa
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public void setRutempresa(Integer rutempresa) {
        this.rutempresa = rutempresa;
    }

    public String getDvempresa() {
    	return dvempresa;
    }
    
    public void setDvempresa(String dv) {
        this.dvempresa = dv == null ? null : dv.trim();
    }
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column MotorCreditScoring.dbo.Solicitud.IdClasificacionEmpresa
     *
     * @return the value of MotorCreditScoring.dbo.Solicitud.IdClasificacionEmpresa
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public Integer getIdclasificacionempresa() {
        return idclasificacionempresa;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column MotorCreditScoring.dbo.Solicitud.IdClasificacionEmpresa
     *
     * @param idclasificacionempresa the value for MotorCreditScoring.dbo.Solicitud.IdClasificacionEmpresa
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public void setIdclasificacionempresa(Integer idclasificacionempresa) {
        this.idclasificacionempresa = idclasificacionempresa;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column MotorCreditScoring.dbo.Solicitud.FechaAntiguedadLaboral
     *
     * @return the value of MotorCreditScoring.dbo.Solicitud.FechaAntiguedadLaboral
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public String getFechaantiguedadlaboral() {
        return fechaantiguedadlaboral;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column MotorCreditScoring.dbo.Solicitud.FechaAntiguedadLaboral
     *
     * @param fechaantiguedadlaboral the value for MotorCreditScoring.dbo.Solicitud.FechaAntiguedadLaboral
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public void setFechaantiguedadlaboral(String fechaantiguedadlaboral) {
        this.fechaantiguedadlaboral = fechaantiguedadlaboral == null ? null : fechaantiguedadlaboral.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column MotorCreditScoring.dbo.Solicitud.AntiguedadLaboral
     *
     * @return the value of MotorCreditScoring.dbo.Solicitud.AntiguedadLaboral
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public Integer getAntiguedadlaboral() {
        return antiguedadlaboral;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column MotorCreditScoring.dbo.Solicitud.AntiguedadLaboral
     *
     * @param antiguedadlaboral the value for MotorCreditScoring.dbo.Solicitud.AntiguedadLaboral
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public void setAntiguedadlaboral(Integer antiguedadlaboral) {
        this.antiguedadlaboral = antiguedadlaboral;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column MotorCreditScoring.dbo.Solicitud.RiesgoExterno
     *
     * @return the value of MotorCreditScoring.dbo.Solicitud.RiesgoExterno
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public BigDecimal getRiesgoexterno() {
        return riesgoexterno;
    }

    public void setMaximomontonominalotorgable(Integer maximomontonominalotorgable) {
		this.maximomontonominalotorgable = maximomontonominalotorgable;
	}

	public Integer getMaximomontonominalotorgable() {
		return maximomontonominalotorgable;
	}

	/**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column MotorCreditScoring.dbo.Solicitud.RiesgoExterno
     *
     * @param riesgoexterno the value for MotorCreditScoring.dbo.Solicitud.RiesgoExterno
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public void setRiesgoexterno(BigDecimal riesgoexterno) {
        this.riesgoexterno = riesgoexterno;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column MotorCreditScoring.dbo.Solicitud.NroSueldos
     *
     * @return the value of MotorCreditScoring.dbo.Solicitud.NroSueldos
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public String getNrosueldos() {
        return nrosueldos;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column MotorCreditScoring.dbo.Solicitud.NroSueldos
     *
     * @param nrosueldos the value for MotorCreditScoring.dbo.Solicitud.NroSueldos
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public void setNrosueldos(String nrosueldos) {
        this.nrosueldos = nrosueldos == null ? null : nrosueldos.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column MotorCreditScoring.dbo.Solicitud.ScoreModeloPersonas
     *
     * @return the value of MotorCreditScoring.dbo.Solicitud.ScoreModeloPersonas
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public BigDecimal getScoremodelopersonas() {
        return scoremodelopersonas;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column MotorCreditScoring.dbo.Solicitud.ScoreModeloPersonas
     *
     * @param scoremodelopersonas the value for MotorCreditScoring.dbo.Solicitud.ScoreModeloPersonas
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public void setScoremodelopersonas(BigDecimal scoremodelopersonas) {
        this.scoremodelopersonas = scoremodelopersonas;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column MotorCreditScoring.dbo.Solicitud.PerfilRiesgo
     *
     * @return the value of MotorCreditScoring.dbo.Solicitud.PerfilRiesgo
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public String getPerfilriesgo() {
        return perfilriesgo;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column MotorCreditScoring.dbo.Solicitud.PerfilRiesgo
     *
     * @param perfilriesgo the value for MotorCreditScoring.dbo.Solicitud.PerfilRiesgo
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public void setPerfilriesgo(String perfilriesgo) {
        this.perfilriesgo = perfilriesgo == null ? null : perfilriesgo.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column MotorCreditScoring.dbo.Solicitud.FechaCreacion
     *
     * @return the value of MotorCreditScoring.dbo.Solicitud.FechaCreacion
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public Date getFechacreacion() {
        return fechacreacion;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column MotorCreditScoring.dbo.Solicitud.FechaCreacion
     *
     * @param fechacreacion the value for MotorCreditScoring.dbo.Solicitud.FechaCreacion
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }
}