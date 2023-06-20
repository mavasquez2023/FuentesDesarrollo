package cl.laaraucana.silmsil.vo;

import com.tivoli.pd.jasn1.boolean32;

/**
 * clase que representa estructura para datos LM_VO
 * LIEXP.ILFLM050
 * **/
public class LM_VistaErrores_VO {

	private boolean keyErrores;
	private boolean keyExiste;
	private int keyEstado;

	/* Operador, largo: 1 */
	private String operador;
	
	/* Tipo formulario, largo: 1 */
	private String tipoform;
	
	/* Folio, largo: 20 */
	private String folio;
	
	/* Artículo 77 bis, largo: 1 */
	private String art77bis;
	
	/* Fecha de información, largo: 8 */
	private String fecinform;
	
	/* RUT trabajador, largo: 10 */
	private String ruttrabaj;
	
	/* Fecha emisión licencia, largo: 8 */
	private String fecemision;
	
	/* Fecha inicio reposo, largo: 8 */
	private String fecinirepo;
	
	/* Fecha término reposo, largo: 8 */
	private String fecterrepo;
	
	/* Edad del trabajador, largo: 2 */
	private String edadtrabaj;
	
	/* Fecha nacimiento trabajador, largo: 8 */
	private String fecnactrab;
	
	/* Género trabajador (M/F), largo: 1 */
	private String gentrabaj;
	
	/* Número días licencia, largo: 3 */
	private String numdiaslic;
	
	/* Licencia maternal suplement., largo: 1 */
	private String licmatsupl;
	
	/* Fecha nacimiento hijo, largo: 8 */
	private String fecnachijo;
	
	/* RUT hijo, largo: 10 */
	private String ruthijo;
	
	/* Tipo de licencia, largo: 1 */
	private String tipolic;
	
	/* Recuperabilidad laboral, largo: 1 */
	private String recuplabor;
	
	/* Inicio trámite invalidez, largo: 1 */
	private String iniinvalid;
	
	/* Año y mes concepción, largo: 6 */
	private String fecconcep;
	
	/* Tipo de reposo, largo: 1 */
	private String tiporeposo;
	
	/* Jornada de reposo, largo: 1 */
	private String jorreposo;
	
	/* Lugar de reposo, largo: 1 */
	private String lugreposo;
	
	/* Descripción especialidad, largo: 30 */
	private String especialid;
	
	/* Tipo de profesional, largo: 1 */
	private String tipoprofes;
	
	/* RUT profesional médico, largo: 10 */
	private String rutprofes;
	
	/* Nombre profesional médico, largo: 60 */
	private String nomprofes;
	
	/* Licencia modificada, largo: 1 */
	private String licmodific;
	
	/* Código entidad autorizadora, largo: 5 */
	private String entautoriz;
	
	/* Tipo licencia méd. resuelto, largo: 1 */
	private String tipolmresu;
	
	/* Núm días incapacidad autoriz, largo: 3 */
	private String ndiasincap;
	
	/* Código diagnóstico resuelto, largo: 5 */
	private String diagresuel;
	
	/* Período, largo: 1 */
	private String periodo;
	
	/* Núm.días previos autorizados, largo: 4 */
	private String ndiasprev;
	
	/* Estado de la resolución, largo: 1 */
	private String estadoreso;
	
	/* Tipo de resolución, largo: 1 */
	private String tiporesolu;
	
	/* Redictamen, largo: 1 */
	private String redictamen;
	
	/* Causa de rechazo, largo: 1 */
	private String causarecha;
	
	/* Tipo de reposo autorizado, largo: 1 */
	private String tiporepoau;
	
	/* Jornada reposo autorizada, largo: 1 */
	private String jorrepoaut;
	
	/* Derecho a subsidio, largo: 1 */
	private String desubsidio;
	
	/* Fec.recepción ent.autoriz, largo: 8 */
	private String fecreceent;
	
	/* Fec.resolución ent.autoriza, largo: 8 */
	private String fecresoent;
	
	/* RUT contraloría médica, largo: 10 */
	private String rutcontral;
	
	/* RUT empleador, largo: 10 */
	private String rutemplead;
	
	/* Fec.recepción lic.empleador, largo: 8 */
	private String fecreceemp;
	
	/* Región empleador, largo: 2 */
	private String regionempl;
	
	/* Comuna empleador, largo: 5 */
	private String comunaempl;
	
	/* Actividad laboral trabajador, largo: 2 */
	private String actlabtrab;
	
	/* Ocupación del trabajador, largo: 2 */
	private String ocupactrab;
	
	/* Fec.recepción ent.pagadora, largo: 8 */
	private String fecrecepag;
	
	/* Tipo de régimen previsional, largo: 1 */
	private String tipregprev;
	
	/* Calidad del trabajador, largo: 1 */
	private String calitrabaj;
	
	/* Tipo ent.pagadora subsidio, largo: 1 */
	private String tipoentpag;
	
	/* Fec.primera afil.ent.previs., largo: 8 */
	private String fecpriafil;
	
	/* Fecha contrato trabajo, largo: 8 */
	private String feccontrab;
	
	//adicionales
	/* Estado de la licencia, largo: 1 */
	private String estadolic;
	
	/* Licencia rechazada, largo: 1 */
	private String licrechaz;
	
	/* Período a procesar, largo: 6 */
	private String fecproceso;
	
	/* Archivo origen datos, largo: 10 */
	private String archivo;
	
	/*Correlativo, largo: 6*/
	private String correlativ;
	
	public LM_VistaErrores_VO() {
		super();
		this.operador = "";
		this.tipoform = "";
		this.folio = "";
		this.art77bis = "";
		this.fecinform = "";
		this.ruttrabaj = "";
		this.fecemision = "";
		this.fecinirepo = "";
		this.fecterrepo = "";
		this.edadtrabaj = "";
		this.fecnactrab = "";
		this.gentrabaj = "";
		this.numdiaslic = "";
		this.licmatsupl = "";
		this.fecnachijo = "";
		this.ruthijo = "";
		this.tipolic = "";
		this.recuplabor = "";
		this.iniinvalid = "";
		this.fecconcep = "";
		this.tiporeposo = "";
		this.jorreposo = "";
		this.lugreposo = "";
		this.especialid = "";
		this.tipoprofes = "";
		this.rutprofes = "";
		this.nomprofes = "";
		this.licmodific = "";
		this.entautoriz = "";
		this.tipolmresu = "";
		this.ndiasincap = "";
		this.diagresuel = "";
		this.periodo ="";
		this.ndiasprev = "";
		this.estadoreso = "";
		this.tiporesolu = "";
		this.redictamen = "";
		this.causarecha = "";
		this.tiporepoau = "";
		this.jorrepoaut = "";
		this.desubsidio = "";
		this.fecreceent = "";
		this.fecresoent = "";
		this.rutcontral = "";
		this.rutemplead = "";
		this.fecreceemp = "";
		this.regionempl = "";
		this.comunaempl = "";
		this.actlabtrab = "";
		this.ocupactrab = "";
		this.fecrecepag = "";
		this.tipregprev = "";
		this.calitrabaj = "";
		this.tipoentpag = "";
		this.fecpriafil = "";
		this.feccontrab = "";
		this.estadolic = "";
		this.licrechaz = "";
		this.fecproceso = "";
		this.archivo = "";
		this.correlativ = "";
	}
	
	public LM_VistaErrores_VO(boolean keyErrores, boolean keyExiste) {
		super();
		this.keyErrores=keyErrores;
		this.keyExiste=keyExiste;
		this.operador = "";
		this.tipoform = "";
		this.folio = "";
		this.art77bis = "";
		this.fecinform = "";
		this.ruttrabaj = "";
		this.fecemision = "";
		this.fecinirepo = "";
		this.fecterrepo = "";
		this.edadtrabaj = "";
		this.fecnactrab = "";
		this.gentrabaj = "";
		this.numdiaslic = "";
		this.licmatsupl = "";
		this.fecnachijo = "";
		this.ruthijo = "";
		this.tipolic = "";
		this.recuplabor = "";
		this.iniinvalid = "";
		this.fecconcep = "";
		this.tiporeposo = "";
		this.jorreposo = "";
		this.lugreposo = "";
		this.especialid = "";
		this.tipoprofes = "";
		this.rutprofes = "";
		this.nomprofes = "";
		this.licmodific = "";
		this.entautoriz = "";
		this.tipolmresu = "";
		this.ndiasincap = "";
		this.diagresuel = "";
		this.periodo ="";
		this.ndiasprev = "";
		this.estadoreso = "";
		this.tiporesolu = "";
		this.redictamen = "";
		this.causarecha = "";
		this.tiporepoau = "";
		this.jorrepoaut = "";
		this.desubsidio = "";
		this.fecreceent = "";
		this.fecresoent = "";
		this.rutcontral = "";
		this.rutemplead = "";
		this.fecreceemp = "";
		this.regionempl = "";
		this.comunaempl = "";
		this.actlabtrab = "";
		this.ocupactrab = "";
		this.fecrecepag = "";
		this.tipregprev = "";
		this.calitrabaj = "";
		this.tipoentpag = "";
		this.fecpriafil = "";
		this.feccontrab = "";
		this.estadolic = "";
		this.licrechaz = "";
		this.fecproceso = "";
		this.archivo = "";
		this.correlativ = "";
	}
	
	public LM_VistaErrores_VO(String operador, String tipoform, String folio,
			String art77bis, String fecinform, String ruttrabaj,
			String fecemision, String fecinirepo, String fecterrepo,
			String edadtrabaj, String fecnactrab, String gentrabaj,
			String numdiaslic, String licmatsupl, String fecnachijo,
			String ruthijo, String tipolic, String recuplabor,
			String iniinvalid, String fecconcep, String tiporeposo,
			String jorreposo, String lugreposo, String especialid,
			String tipoprofes, String rutprofes, String nomprofes,
			String licmodific, String entautoriz, String tipolmresu,
			String ndiasincap, String diagresuel, String periodo,
			String ndiasprev, String estadoreso, String tiporesolu,
			String redictamen, String causarecha, String tiporepoau,
			String jorrepoaut, String desubsidio, String fecreceent,
			String fecresoent, String rutcontral, String rutemplead,
			String fecreceemp, String regionempl, String comunaempl,
			String actlabtrab, String ocupactrab, String fecrecepag,
			String tipregprev, String calitrabaj, String tipoentpag,
			String fecpriafil, String feccontrab, String estadolic,
			String licrechaz, String fecproceso, String archivo,
			String correlativ) {
		super();
		this.operador = operador;
		this.tipoform = tipoform;
		this.folio = folio;
		this.art77bis = art77bis;
		this.fecinform = fecinform;
		this.ruttrabaj = ruttrabaj;
		this.fecemision = fecemision;
		this.fecinirepo = fecinirepo;
		this.fecterrepo = fecterrepo;
		this.edadtrabaj = edadtrabaj;
		this.fecnactrab = fecnactrab;
		this.gentrabaj = gentrabaj;
		this.numdiaslic = numdiaslic;
		this.licmatsupl = licmatsupl;
		this.fecnachijo = fecnachijo;
		this.ruthijo = ruthijo;
		this.tipolic = tipolic;
		this.recuplabor = recuplabor;
		this.iniinvalid = iniinvalid;
		this.fecconcep = fecconcep;
		this.tiporeposo = tiporeposo;
		this.jorreposo = jorreposo;
		this.lugreposo = lugreposo;
		this.especialid = especialid;
		this.tipoprofes = tipoprofes;
		this.rutprofes = rutprofes;
		this.nomprofes = nomprofes;
		this.licmodific = licmodific;
		this.entautoriz = entautoriz;
		this.tipolmresu = tipolmresu;
		this.ndiasincap = ndiasincap;
		this.diagresuel = diagresuel;
		this.periodo = periodo;
		this.ndiasprev = ndiasprev;
		this.estadoreso = estadoreso;
		this.tiporesolu = tiporesolu;
		this.redictamen = redictamen;
		this.causarecha = causarecha;
		this.tiporepoau = tiporepoau;
		this.jorrepoaut = jorrepoaut;
		this.desubsidio = desubsidio;
		this.fecreceent = fecreceent;
		this.fecresoent = fecresoent;
		this.rutcontral = rutcontral;
		this.rutemplead = rutemplead;
		this.fecreceemp = fecreceemp;
		this.regionempl = regionempl;
		this.comunaempl = comunaempl;
		this.actlabtrab = actlabtrab;
		this.ocupactrab = ocupactrab;
		this.fecrecepag = fecrecepag;
		this.tipregprev = tipregprev;
		this.calitrabaj = calitrabaj;
		this.tipoentpag = tipoentpag;
		this.fecpriafil = fecpriafil;
		this.feccontrab = feccontrab;
		this.estadolic = estadolic;
		this.licrechaz = licrechaz;
		this.fecproceso = fecproceso;
		this.archivo = archivo;
		this.correlativ = correlativ;
	}

	
	
	public String getOperador() {
		return operador;
	}

	public String getTipoform() {
		return tipoform;
	}

	public String getFolio() {
		return folio;
	}

	public String getArt77bis() {
		return art77bis;
	}

	public String getFecinform() {
		return fecinform;
	}

	public String getRuttrabaj() {
		return ruttrabaj;
	}

	public String getFecemision() {
		return fecemision;
	}

	public String getFecinirepo() {
		return fecinirepo;
	}

	public String getFecterrepo() {
		return fecterrepo;
	}

	public String getEdadtrabaj() {
		return edadtrabaj;
	}

	public String getFecnactrab() {
		return fecnactrab;
	}

	public String getGentrabaj() {
		return gentrabaj;
	}

	public String getNumdiaslic() {
		return numdiaslic;
	}

	public String getLicmatsupl() {
		return licmatsupl;
	}

	public String getFecnachijo() {
		return fecnachijo;
	}

	public String getRuthijo() {
		return ruthijo;
	}

	public String getTipolic() {
		return tipolic;
	}

	public String getRecuplabor() {
		return recuplabor;
	}

	public String getIniinvalid() {
		return iniinvalid;
	}

	public String getFecconcep() {
		return fecconcep;
	}

	public String getTiporeposo() {
		return tiporeposo;
	}

	public String getJorreposo() {
		return jorreposo;
	}

	public String getLugreposo() {
		return lugreposo;
	}

	public String getEspecialid() {
		return especialid;
	}

	public String getTipoprofes() {
		return tipoprofes;
	}

	public String getRutprofes() {
		return rutprofes;
	}

	public String getNomprofes() {
		return nomprofes;
	}

	public String getLicmodific() {
		return licmodific;
	}

	public String getEntautoriz() {
		return entautoriz;
	}

	public String getTipolmresu() {
		return tipolmresu;
	}

	public String getNdiasincap() {
		return ndiasincap;
	}

	public String getDiagresuel() {
		return diagresuel;
	}

	public String getPeriodo() {
		return periodo;
	}

	public String getNdiasprev() {
		return ndiasprev;
	}

	public String getEstadoreso() {
		return estadoreso;
	}

	public String getTiporesolu() {
		return tiporesolu;
	}

	public String getRedictamen() {
		return redictamen;
	}

	public String getCausarecha() {
		return causarecha;
	}

	public String getTiporepoau() {
		return tiporepoau;
	}

	public String getJorrepoaut() {
		return jorrepoaut;
	}

	public String getDesubsidio() {
		return desubsidio;
	}

	public String getFecreceent() {
		return fecreceent;
	}

	public String getFecresoent() {
		return fecresoent;
	}

	public String getRutcontral() {
		return rutcontral;
	}

	public String getRutemplead() {
		return rutemplead;
	}

	public String getFecreceemp() {
		return fecreceemp;
	}

	public String getRegionempl() {
		return regionempl;
	}

	public String getComunaempl() {
		return comunaempl;
	}

	public String getActlabtrab() {
		return actlabtrab;
	}

	public String getOcupactrab() {
		return ocupactrab;
	}

	public String getFecrecepag() {
		return fecrecepag;
	}

	public String getTipregprev() {
		return tipregprev;
	}

	public String getCalitrabaj() {
		return calitrabaj;
	}

	public String getTipoentpag() {
		return tipoentpag;
	}

	public String getFecpriafil() {
		return fecpriafil;
	}

	public String getFeccontrab() {
		return feccontrab;
	}

	public String getEstadolic() {
		return estadolic;
	}

	public String getLicrechaz() {
		return licrechaz;
	}

	public String getFecproceso() {
		return fecproceso;
	}

	public String getArchivo() {
		return archivo;
	}

	public String getCorrelativ() {
		return correlativ;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public void setTipoform(String tipoform) {
		this.tipoform = tipoform;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public void setArt77bis(String art77bis) {
		this.art77bis = art77bis;
	}

	public void setFecinform(String fecinform) {
		this.fecinform = fecinform;
	}

	public void setRuttrabaj(String ruttrabaj) {
		this.ruttrabaj = ruttrabaj;
	}

	public void setFecemision(String fecemision) {
		this.fecemision = fecemision;
	}

	public void setFecinirepo(String fecinirepo) {
		this.fecinirepo = fecinirepo;
	}

	public void setFecterrepo(String fecterrepo) {
		this.fecterrepo = fecterrepo;
	}

	public void setEdadtrabaj(String edadtrabaj) {
		this.edadtrabaj = edadtrabaj;
	}

	public void setFecnactrab(String fecnactrab) {
		this.fecnactrab = fecnactrab;
	}

	public void setGentrabaj(String gentrabaj) {
		this.gentrabaj = gentrabaj;
	}

	public void setNumdiaslic(String numdiaslic) {
		this.numdiaslic = numdiaslic;
	}

	public void setLicmatsupl(String licmatsupl) {
		this.licmatsupl = licmatsupl;
	}

	public void setFecnachijo(String fecnachijo) {
		this.fecnachijo = fecnachijo;
	}

	public void setRuthijo(String ruthijo) {
		this.ruthijo = ruthijo;
	}

	public void setTipolic(String tipolic) {
		this.tipolic = tipolic;
	}

	public void setRecuplabor(String recuplabor) {
		this.recuplabor = recuplabor;
	}

	public void setIniinvalid(String iniinvalid) {
		this.iniinvalid = iniinvalid;
	}

	public void setFecconcep(String fecconcep) {
		this.fecconcep = fecconcep;
	}

	public void setTiporeposo(String tiporeposo) {
		this.tiporeposo = tiporeposo;
	}

	public void setJorreposo(String jorreposo) {
		this.jorreposo = jorreposo;
	}

	public void setLugreposo(String lugreposo) {
		this.lugreposo = lugreposo;
	}

	public void setEspecialid(String especialid) {
		this.especialid = especialid;
	}

	public void setTipoprofes(String tipoprofes) {
		this.tipoprofes = tipoprofes;
	}

	public void setRutprofes(String rutprofes) {
		this.rutprofes = rutprofes;
	}

	public void setNomprofes(String nomprofes) {
		this.nomprofes = nomprofes;
	}

	public void setLicmodific(String licmodific) {
		this.licmodific = licmodific;
	}

	public void setEntautoriz(String entautoriz) {
		this.entautoriz = entautoriz;
	}

	public void setTipolmresu(String tipolmresu) {
		this.tipolmresu = tipolmresu;
	}

	public void setNdiasincap(String ndiasincap) {
		this.ndiasincap = ndiasincap;
	}

	public void setDiagresuel(String diagresuel) {
		this.diagresuel = diagresuel;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public void setNdiasprev(String ndiasprev) {
		this.ndiasprev = ndiasprev;
	}

	public void setEstadoreso(String estadoreso) {
		this.estadoreso = estadoreso;
	}

	public void setTiporesolu(String tiporesolu) {
		this.tiporesolu = tiporesolu;
	}

	public void setRedictamen(String redictamen) {
		this.redictamen = redictamen;
	}

	public void setCausarecha(String causarecha) {
		this.causarecha = causarecha;
	}

	public void setTiporepoau(String tiporepoau) {
		this.tiporepoau = tiporepoau;
	}

	public void setJorrepoaut(String jorrepoaut) {
		this.jorrepoaut = jorrepoaut;
	}

	public void setDesubsidio(String desubsidio) {
		this.desubsidio = desubsidio;
	}

	public void setFecreceent(String fecreceent) {
		this.fecreceent = fecreceent;
	}

	public void setFecresoent(String fecresoent) {
		this.fecresoent = fecresoent;
	}

	public void setRutcontral(String rutcontral) {
		this.rutcontral = rutcontral;
	}

	public void setRutemplead(String rutemplead) {
		this.rutemplead = rutemplead;
	}

	public void setFecreceemp(String fecreceemp) {
		this.fecreceemp = fecreceemp;
	}

	public void setRegionempl(String regionempl) {
		this.regionempl = regionempl;
	}

	public void setComunaempl(String comunaempl) {
		this.comunaempl = comunaempl;
	}

	public void setActlabtrab(String actlabtrab) {
		this.actlabtrab = actlabtrab;
	}

	public void setOcupactrab(String ocupactrab) {
		this.ocupactrab = ocupactrab;
	}

	public void setFecrecepag(String fecrecepag) {
		this.fecrecepag = fecrecepag;
	}

	public void setTipregprev(String tipregprev) {
		this.tipregprev = tipregprev;
	}

	public void setCalitrabaj(String calitrabaj) {
		this.calitrabaj = calitrabaj;
	}

	public void setTipoentpag(String tipoentpag) {
		this.tipoentpag = tipoentpag;
	}

	public void setFecpriafil(String fecpriafil) {
		this.fecpriafil = fecpriafil;
	}

	public void setFeccontrab(String feccontrab) {
		this.feccontrab = feccontrab;
	}

	public void setEstadolic(String estadolic) {
		this.estadolic = estadolic;
	}

	public void setLicrechaz(String licrechaz) {
		this.licrechaz = licrechaz;
	}

	public void setFecproceso(String fecproceso) {
		this.fecproceso = fecproceso;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public void setCorrelativ(String correlativ) {
		this.correlativ = correlativ;
	}

	public boolean isKeyErrores() {
		return keyErrores;
	}

	public void setKeyErrores(boolean keyErrores) {
		this.keyErrores = keyErrores;
	}
	
	public boolean getKeyErrores() {
		return keyErrores;
	}

	public boolean isKeyExiste() {
		return keyExiste;
	}

	public void setKeyExiste(boolean keyExiste) {
		this.keyExiste = keyExiste;
	}

	public int getKeyEstado() {
		return keyEstado;
	}

	public void setKeyEstado(int keyEstado) {
		this.keyEstado = keyEstado;
	}

	

}//end class L_VO
