package cl.araucana.wsafiliado.to;


public class CamposXmlTO  {

	public CamposXmlTO() {

	}

	/**
	 * Metodo constructor para test
	 * @param test Si es TRUE, el objeto se llena con datos de prueba
	 */
	public CamposXmlTO(boolean test) {
		if (test) {
			id = "ID";
			FechaEmision = "FECHA_EMISION";
			TipoCausante = "TIPO_CAUSANTE";
			SexoCausante = "SEXO_CAUSANTE";
			RutCausante = "RUT_CAUSANTE";
			NomCausante = "NOM_CAUSANTE";
			TipoBeneficiario = "TIPO_BENEFICIARIO";
			RutBeneficiario = "RUT_BENEFICIARIO";
			NomBeneficiario = "NOM_BENEFICIARIO";
			RutEmpleador = "RUT_EMPLEADOR";
			NomEmpleador = "NOM_EMPLEADOR";
			Acteco = "ACTECO";
			RegionEmpleador = "REGION_EMPLEADOR";
			ComunaEmpleador = "COMUNA_EMPLEADOR";
			CodEntidadAdm = "COD_ENTIDAD_ADM";
			IdTipoBeneficio = "ID_TIPO_BENEFICIARIO";
			FecRecCausante = "FEC_REC_CAUSANTE";
			FecPagoBeneficio = "FEC_PAGO_BENEFICIO";
			FecExtCausante = "FEC_EXT_CAUSANTE";
			Periodo = "PERIODO";
			numerotramo = "NUMERO_TRAMO";
			TramoAsigFam = "TRAMO_ASIG_FAM";
			IngPromedio = "ING_PROMEDIO";
			MontoUnitarioBeneficio = "MONTO_UNITARIO_BENEFICIO";
			tupla = "TUPLA";
			codtipocausante = "COD_TIPO_CAUSANTE";
			fNacCausante = "FEC_NAC_CAUSANTE";
		}
	}

	private String id;
	private String FechaEmision;
	private String TipoCausante;
	private String SexoCausante;
	private String RutCausante;
	private String NomCausante;
	private String TipoBeneficiario;
	private String RutBeneficiario;
	private String NomBeneficiario;
	private String RutEmpleador;
	private String NomEmpleador;
	private String Acteco;
	private String RegionEmpleador;
	private String ComunaEmpleador;
	private String CodEntidadAdm;
	private String IdTipoBeneficio;
	private String FecRecCausante;
	private String FecPagoBeneficio;
	private String FecExtCausante;
	private String Periodo;
	private String numerotramo;
	private String TramoAsigFam;
	private String IngPromedio;
	private String MontoUnitarioBeneficio;
	private String tupla;
	private String codtipocausante;
	private String fNacCausante;
	private boolean actualizaSIAGF;
	private String valorTramo;
	private boolean causante;
	private int diferencia;
	private String codigoArchivo;
	private String estadoTupla;
	private String RegionBeneficiario;
	private String ComunaBeneficiario;
	
	/**
	 * @return the actualizaSIAGF
	 */
	public boolean isActualizaSIAGF() {
		return actualizaSIAGF;
	}

	/**
	 * @param actualizaSIAGF the actualizaSIAGF to set
	 */
	public void setActualizaSIAGF(boolean actualizaSIAGF) {
		this.actualizaSIAGF = actualizaSIAGF;
	}

	public String getActeco() {
		return Acteco;
	}

	public void setActeco(String acteco) {
		Acteco = acteco;
	}

	public String getCodEntidadAdm() {
		return CodEntidadAdm;
	}

	public void setCodEntidadAdm(String codEntidadAdm) {
		CodEntidadAdm = codEntidadAdm;
	}

	public String getComunaEmpleador() {
		return ComunaEmpleador;
	}

	public void setComunaEmpleador(String comunaEmpleador) {
		ComunaEmpleador = comunaEmpleador;
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

	public String getIdTipoBeneficio() {
		return IdTipoBeneficio;
	}

	public void setIdTipoBeneficio(String idTipoBeneficio) {
		IdTipoBeneficio = idTipoBeneficio;
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

	public String getNomEmpleador() {
		return NomEmpleador;
	}

	public void setNomEmpleador(String nomEmpleador) {
		NomEmpleador = nomEmpleador;
	}

	public String getPeriodo() {
		return Periodo;
	}

	public void setPeriodo(String periodo) {
		Periodo = periodo;
	}

	public String getRegionEmpleador() {
		return RegionEmpleador;
	}

	public void setRegionEmpleador(String regionEmpleador) {
		RegionEmpleador = regionEmpleador;
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

	public String getTipoBeneficiario() {
		return TipoBeneficiario;
	}

	public void setTipoBeneficiario(String tipoBeneficiario) {
		TipoBeneficiario = tipoBeneficiario;
	}

	public String getTipoCausante() {
		return TipoCausante;
	}

	public void setTipoCausante(String tipoCausante) {
		TipoCausante = tipoCausante;
	}

	public String getTramoAsigFam() {
		return TramoAsigFam;
	}

	public void setTramoAsigFam(String tramoAsigFam) {
		TramoAsigFam = tramoAsigFam;
	}

	public String getNumerotramo() {
		return numerotramo;
	}

	public void setNumerotramo(String numerotramo) {
		this.numerotramo = numerotramo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTupla() {
		return tupla;
	}

	public void setTupla(String tupla) {
		this.tupla = tupla;
	}

	public String getCodtipocausante() {
		return codtipocausante;
	}

	public void setCodtipocausante(String codtipocausante) {
		this.codtipocausante = codtipocausante;
	}

	public String getfNacCausante() {
		return fNacCausante;
	}

	public void setfNacCausante(String fNacCausante) {
		this.fNacCausante = fNacCausante;
	}

	/**
	 * @return the valorTramo
	 */
	public String getValorTramo() {
		return valorTramo;
	}

	/**
	 * @param valorTramo the valorTramo to set
	 */
	public void setValorTramo(String valorTramo) {
		this.valorTramo = valorTramo;
	}

	/**
	 * @return the causante
	 */
	public boolean isCausante() {
		return causante;
	}

	/**
	 * @param causante the causante to set
	 */
	public void setCausante(boolean causante) {
		this.causante = causante;
	}

	/**
	 * @return the diferencia
	 */
	public int getDiferencia() {
		return diferencia;
	}

	/**
	 * @param diferencia the diferencia to set
	 */
	public void setDiferencia(int diferencia) {
		this.diferencia = diferencia;
	}

	/**
	 * @return the codigoArchivo
	 */
	public String getCodigoArchivo() {
		return codigoArchivo;
	}

	/**
	 * @param codigoArchivo the codigoArchivo to set
	 */
	public void setCodigoArchivo(String codigoArchivo) {
		this.codigoArchivo = codigoArchivo;
	}

	/**
	 * @return the estadoTupla
	 */
	public String getEstadoTupla() {
		return estadoTupla;
	}

	/**
	 * @param estadoTupla the estadoTupla to set
	 */
	public void setEstadoTupla(String estadoTupla) {
		this.estadoTupla = estadoTupla;
	}

	/**
	 * @return the regionBeneficiario
	 */
	public String getRegionBeneficiario() {
		return RegionBeneficiario;
	}

	/**
	 * @param regionBeneficiario the regionBeneficiario to set
	 */
	public void setRegionBeneficiario(String regionBeneficiario) {
		RegionBeneficiario = regionBeneficiario;
	}

	/**
	 * @return the comunaBeneficiario
	 */
	public String getComunaBeneficiario() {
		return ComunaBeneficiario;
	}

	/**
	 * @param comunaBeneficiario the comunaBeneficiario to set
	 */
	public void setComunaBeneficiario(String comunaBeneficiario) {
		ComunaBeneficiario = comunaBeneficiario;
	}
	
	
}
