package cl.araucana.wsafiliado.to;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Informacion de la consulta de causante desde Webservice
 * @author Administrador
 *
 */
public class TuplaTO{

	private static final long serialVersionUID = 1781116637915805692L;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", new Locale("Cl"));

	public TuplaTO() {

	}

	private String id;
	private String codigo;
	private String FechaEmision;
	private String Tupla;
	private String TrackID;
	private String CodEstadoTupla;
	private String NomEstadoTupla;
	private String CodTipoCausante;
	private String NomTipoCausante;
	private String RutCausante;
	private String DVCausante;
	private String NomCausante;
	private String SexoCausante;
	private String FecNacCausante;
	private String CodRegionCausante;
	private String NomRegionCausante;
	private String CodComunaCausante;
	private String NomComunaCausante;
	private String CodTipoBeneficiario;
	private String NomTipoBeneficiario;
	private String RutBeneficiario;
	private String DVBeneficiario;
	private String NomBeneficiario;
	private String CodRegionBeneficiario;
	private String NomRegionBeneficiario;
	private String CodComunaBeneficiario;
	private String NomComunaBeneficiario;
	private String IngPromedio;
	private String RutEmpleador;
	private String DVEmpleador;
	private String NomEmpleador;
	private String Acteco;
	private String CodRegionEmpleador;
	private String NomRegionEmpleador;
	private String CodComunaEmpleador;
	private String NomComunaEmpleador;
	private String CodEntidadAdm;
	private String NomEntidadAdm;
	private String CodTipoBeneficio;
	private String NomTipoBeneficio;
	private String FecRecCausante;
	private String FecPagoBeneficio;
	private String MontoUnitarioBeneficio;
	private String PuntajeFichaProtSocial;
	private String TramoAsigFam;
	private String FecExtCausante;
	private String CausaExtCausante;
	private String GlosaExtCausante;
	private String FechaTransaccion;
	private String Periodo;
	private String numtramo;
	private String ingPromedioTramo;
	private String MontoUnitarioBeneficiottramo;
	private ArrayList tramo;
	private String estado;
	
	private String afsiRube;
	private String dvBene;
	private String afiliado;
	private String dvAfiliado;
	
	private String tramo1;
	private String tramo2;
	private String tramo3;
	private String tramo4;

	public String getActeco() {
		return Acteco;
	}

	public void setActeco(String acteco) {
		Acteco = acteco;
	}

	public String getCausaExtCausante() {
		return CausaExtCausante;
	}

	public void setCausaExtCausante(String causaExtCausante) {
		CausaExtCausante = causaExtCausante;
	}

	public String getCodComunaBeneficiario() {
		return CodComunaBeneficiario;
	}

	public void setCodComunaBeneficiario(String codComunaBeneficiario) {
		CodComunaBeneficiario = codComunaBeneficiario;
	}

	public String getCodComunaCausante() {
		return CodComunaCausante;
	}

	public void setCodComunaCausante(String codComunaCausante) {
		CodComunaCausante = codComunaCausante;
	}

	public String getCodComunaEmpleador() {
		return CodComunaEmpleador;
	}

	public void setCodComunaEmpleador(String codComunaEmpleador) {
		CodComunaEmpleador = codComunaEmpleador;
	}

	public String getCodEntidadAdm() {
		return CodEntidadAdm;
	}

	public void setCodEntidadAdm(String codEntidadAdm) {
		CodEntidadAdm = codEntidadAdm;
	}

	public String getCodEstadoTupla() {
		return CodEstadoTupla;
	}

	public void setCodEstadoTupla(String codEstadoTupla) {
		CodEstadoTupla = codEstadoTupla;
	}

	public String getCodRegionBeneficiario() {
		return CodRegionBeneficiario;
	}

	public void setCodRegionBeneficiario(String codRegionBeneficiario) {
		CodRegionBeneficiario = codRegionBeneficiario;
	}

	public String getCodRegionCausante() {
		return CodRegionCausante;
	}

	public void setCodRegionCausante(String codRegionCausante) {
		CodRegionCausante = codRegionCausante;
	}

	public String getCodRegionEmpleador() {
		return CodRegionEmpleador;
	}

	public void setCodRegionEmpleador(String codRegionEmpleador) {
		CodRegionEmpleador = codRegionEmpleador;
	}

	public String getCodTipoBeneficiario() {
		return CodTipoBeneficiario;
	}

	public void setCodTipoBeneficiario(String codTipoBeneficiario) {
		CodTipoBeneficiario = codTipoBeneficiario;
	}

	public String getCodTipoBeneficio() {
		return CodTipoBeneficio;
	}

	public void setCodTipoBeneficio(String codTipoBeneficio) {
		CodTipoBeneficio = codTipoBeneficio;
	}

	public String getCodTipoCausante() {
		return CodTipoCausante;
	}

	public void setCodTipoCausante(String codTipoCausante) {
		CodTipoCausante = codTipoCausante;
	}

	public String getGlosaExtCausante() {
		return GlosaExtCausante;
	}

	public void setGlosaExtCausante(String glosaExtCausante) {
		GlosaExtCausante = glosaExtCausante;
	}

	public String getIngPromedio() {
		return IngPromedio;
	}

	public void setIngPromedio(String ingPromedio) {
		IngPromedio = ingPromedio;
	}

	public String getMontoUnitarioBeneficio() {
		return MontoUnitarioBeneficio;
	}

	public void setMontoUnitarioBeneficio(String montoUnitarioBeneficio) {
		MontoUnitarioBeneficio = montoUnitarioBeneficio;
	}

	public String getNomBeneficiario() {
		return NomBeneficiario;
	}

	public void setNomBeneficiario(String nomBeneficiario) {
		NomBeneficiario = nomBeneficiario;
	}

	public String getNomCausante() {
		return NomCausante;
	}

	public void setNomCausante(String nomCausante) {
		NomCausante = nomCausante;
	}

	public String getNomComunaBeneficiario() {
		return NomComunaBeneficiario;
	}

	public void setNomComunaBeneficiario(String nomComunaBeneficiario) {
		NomComunaBeneficiario = nomComunaBeneficiario;
	}

	public String getNomComunaCausante() {
		return NomComunaCausante;
	}

	public void setNomComunaCausante(String nomComunaCausante) {
		NomComunaCausante = nomComunaCausante;
	}

	public String getNomComunaEmpleador() {
		return NomComunaEmpleador;
	}

	public void setNomComunaEmpleador(String nomComunaEmpleador) {
		NomComunaEmpleador = nomComunaEmpleador;
	}

	public String getNomEmpleador() {
		return NomEmpleador;
	}

	public void setNomEmpleador(String nomEmpleador) {
		NomEmpleador = nomEmpleador;
	}

	public String getNomEntidadAdm() {
		return NomEntidadAdm;
	}

	public void setNomEntidadAdm(String nomEntidadAdm) {
		NomEntidadAdm = nomEntidadAdm;
	}

	public String getNomEstadoTupla() {
		return NomEstadoTupla;
	}

	public void setNomEstadoTupla(String nomEstadoTupla) {
		NomEstadoTupla = nomEstadoTupla;
	}

	public String getNomRegionBeneficiario() {
		return NomRegionBeneficiario;
	}

	public void setNomRegionBeneficiario(String nomRegionBeneficiario) {
		NomRegionBeneficiario = nomRegionBeneficiario;
	}

	public String getNomRegionCausante() {
		return NomRegionCausante;
	}

	public void setNomRegionCausante(String nomRegionCausante) {
		NomRegionCausante = nomRegionCausante;
	}

	public String getNomRegionEmpleador() {
		return NomRegionEmpleador;
	}

	public void setNomRegionEmpleador(String nomRegionEmpleador) {
		NomRegionEmpleador = nomRegionEmpleador;
	}

	public String getNomTipoBeneficiario() {
		return NomTipoBeneficiario;
	}

	public void setNomTipoBeneficiario(String nomTipoBeneficiario) {
		NomTipoBeneficiario = nomTipoBeneficiario;
	}

	public String getNomTipoBeneficio() {
		return NomTipoBeneficio;
	}

	public void setNomTipoBeneficio(String nomTipoBeneficio) {
		NomTipoBeneficio = nomTipoBeneficio;
	}

	public String getNomTipoCausante() {
		return NomTipoCausante;
	}

	public void setNomTipoCausante(String nomTipoCausante) {
		NomTipoCausante = nomTipoCausante;
	}

	public String getPuntajeFichaProtSocial() {
		return PuntajeFichaProtSocial;
	}

	public void setPuntajeFichaProtSocial(String puntajeFichaProtSocial) {
		PuntajeFichaProtSocial = puntajeFichaProtSocial;
	}

	public String getRutBeneficiario() {
		return RutBeneficiario;
	}

	public void setRutBeneficiario(String rutBeneficiario) {
		RutBeneficiario = rutBeneficiario;
	}

	public String getRutCausante() {
		return RutCausante;
	}

	public void setRutCausante(String rutCausante) {
		RutCausante = rutCausante;
	}

	public String getRutEmpleador() {
		return RutEmpleador;
	}

	public void setRutEmpleador(String rutEmpleador) {
		RutEmpleador = rutEmpleador;
	}

	public String getSexoCausante() {
		return SexoCausante;
	}

	public void setSexoCausante(String sexoCausante) {
		SexoCausante = sexoCausante;
	}

	public String getTrackID() {
		return TrackID;
	}

	public void setTrackID(String trackID) {
		TrackID = trackID;
	}

	public String getTramoAsigFam() {
		return TramoAsigFam;
	}

	public void setTramoAsigFam(String tramoAsigFam) {
		TramoAsigFam = tramoAsigFam;
	}

	public String getTupla() {
		return Tupla;
	}

	public void setTupla(String tupla) {
		Tupla = tupla;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getPeriodo() {
		return Periodo;
	}

	public void setPeriodo(String periodo) {
		Periodo = periodo;
	}

	public String getIngPromedioTramo() {
		return ingPromedioTramo;
	}

	public void setIngPromedioTramo(String ingPromedioTramo) {
		this.ingPromedioTramo = ingPromedioTramo;
	}

	public String getMontoUnitarioBeneficiottramo() {
		return MontoUnitarioBeneficiottramo;
	}

	public void setMontoUnitarioBeneficiottramo(String montoUnitarioBeneficiottramo) {
		MontoUnitarioBeneficiottramo = montoUnitarioBeneficiottramo;
	}

	public String getNumtramo() {
		return numtramo;
	}

	public void setNumtramo(String numtramo) {
		this.numtramo = numtramo;
	}

	public ArrayList getTramo() {
		return tramo;
	}

	public void setTramo(ArrayList tramo) {
		this.tramo = tramo;
	}

	public String getFecExtCausante() {
		return FecExtCausante;
	}

	public void setFecExtCausante(String fecExtCausante) {
		FecExtCausante = fecExtCausante;
	}

	public String getFechaEmision() {
		return FechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		FechaEmision = fechaEmision;
	}

	public String getFechaTransaccion() {
		return FechaTransaccion;
	}

	public void setFechaTransaccion(String fechaTransaccion) {
		FechaTransaccion = fechaTransaccion;
	}

	public String getFecNacCausante() {
		return FecNacCausante;
	}

	public void setFecNacCausante(String fecNacCausante) {
		FecNacCausante = fecNacCausante;
	}

	public String getFecPagoBeneficio() {
		return FecPagoBeneficio;
	}

	public void setFecPagoBeneficio(String fecPagoBeneficio) {
		FecPagoBeneficio = fecPagoBeneficio;
	}

	public String getFecRecCausante() {
		return FecRecCausante;
	}

	public void setFecRecCausante(String fecRecCausante) {
		FecRecCausante = fecRecCausante;
	}

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

	public String getAfsiRube() {
		return afsiRube;
	}

	public void setAfsiRube(String afsiRube) {
		this.afsiRube = afsiRube;
	}

	public String getDvBene() {
		return dvBene;
	}

	public void setDvBene(String dvBene) {
		this.dvBene = dvBene;
	}

	public String getAfiliado() {
		return afiliado;
	}

	public void setAfiliado(String afiliado) {
		this.afiliado = afiliado;
	}

	public String getDvAfiliado() {
		return dvAfiliado;
	}

	public void setDvAfiliado(String dvAfiliado) {
		this.dvAfiliado = dvAfiliado;
	}

	public String getTramo1() {
		return tramo1;
	}

	public void setTramo1(String tramo1) {
		this.tramo1 = tramo1;
	}

	public String getTramo2() {
		return tramo2;
	}

	public void setTramo2(String tramo2) {
		this.tramo2 = tramo2;
	}

	public String getTramo3() {
		return tramo3;
	}

	public void setTramo3(String tramo3) {
		this.tramo3 = tramo3;
	}

	public String getTramo4() {
		return tramo4;
	}

	public void setTramo4(String tramo4) {
		this.tramo4 = tramo4;
	}

	/**
	 * @return the dVCausante
	 */
	public String getDVCausante() {
		return DVCausante;
	}

	/**
	 * @param dVCausante the dVCausante to set
	 */
	public void setDVCausante(String dVCausante) {
		DVCausante = dVCausante;
	}

	/**
	 * @return the dVBeneficiario
	 */
	public String getDVBeneficiario() {
		return DVBeneficiario;
	}

	/**
	 * @param dVBeneficiario the dVBeneficiario to set
	 */
	public void setDVBeneficiario(String dVBeneficiario) {
		DVBeneficiario = dVBeneficiario;
	}

	/**
	 * @return the dVEmpleador
	 */
	public String getDVEmpleador() {
		return DVEmpleador;
	}

	/**
	 * @param dVEmpleador the dVEmpleador to set
	 */
	public void setDVEmpleador(String dVEmpleador) {
		DVEmpleador = dVEmpleador;
	}
	
	
}
