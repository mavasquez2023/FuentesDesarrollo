package cl.laaraucana.silmsil.vo;
/**
 * clase que representa estructura para datos LM_VO
 * LIEXP.ILFLM050
 * **/
public class LM_VO {
	
	/* Operador, largo: 1 */
	private int operador;
	
	/* Tipo formulario, largo: 1 */
	private int tipoform;
	
	/* Folio, largo: 20 */
	private String folio;
	
	/* Art�culo 77 bis, largo: 1 */
	private int art77bis;
	
	/* Fecha de informaci�n, largo: 8 */
	private int fecinform;
	
	/* RUT trabajador, largo: 10 */
	private String ruttrabaj;
	
	/* Fecha emisi�n licencia, largo: 8 */
	private int fecemision;
	
	/* Fecha inicio reposo, largo: 8 */
	private int fecinirepo;
	
	/* Fecha t�rmino reposo, largo: 8 */
	private int fecterrepo;
	
	/* Edad del trabajador, largo: 2 */
	private int edadtrabaj;
	
	/* Fecha nacimiento trabajador, largo: 8 */
	private int fecnactrab;
	
	/* G�nero trabajador (M/F), largo: 1 */
	private int gentrabaj;
	
	/* N�mero d�as licencia, largo: 3 */
	private int numdiaslic;
	
	/* Licencia maternal suplement., largo: 1 */
	private int licmatsupl;
	
	/* Fecha nacimiento hijo, largo: 8 */
	private int fecnachijo;
	
	/* RUT hijo, largo: 10 */
	private String ruthijo;
	
	/* Tipo de licencia, largo: 1 */
	private int tipolic;
	
	/* Recuperabilidad laboral, largo: 1 */
	private int recuplabor;
	
	/* Inicio tr�mite invalidez, largo: 1 */
	private int iniinvalid;
	
	/* A�o y mes concepci�n, largo: 6 */
	private int fecconcep;
	
	/* Tipo de reposo, largo: 1 */
	private int tiporeposo;
	
	/* Jornada de reposo, largo: 1 */
	private String jorreposo;
	
	/* Lugar de reposo, largo: 1 */
	private int lugreposo;
	
	/* Descripci�n especialidad, largo: 30 */
	private String especialid;
	
	/* Tipo de profesional, largo: 1 */
	private int tipoprofes;
	
	/* RUT profesional m�dico, largo: 10 */
	private String rutprofes;
	
	/* Nombre profesional m�dico, largo: 60 */
	private String nomprofes;
	
	/* Licencia modificada, largo: 1 */
	private int licmodific;
	
	/* C�digo entidad autorizadora, largo: 5 */
	private int entautoriz;
	
	/* Tipo licencia m�d. resuelto, largo: 1 */
	private int tipolmresu;
	
	/* N�m d�as incapacidad autoriz, largo: 3 */
	private int ndiasincap;
	
	/* C�digo diagn�stico resuelto, largo: 5 */
	private String diagresuel;
	
	/* Per�odo, largo: 1 */
	private int periodo;
	
	/* N�m.d�as previos autorizados, largo: 4 */
	private int ndiasprev;
	
	/* Estado de la resoluci�n, largo: 1 */
	private int estadoreso;
	
	/* Tipo de resoluci�n, largo: 1 */
	private int tiporesolu;
	
	/* Redictamen, largo: 1 */
	private int redictamen;
	
	/* Causa de rechazo, largo: 1 */
	private int causarecha;
	
	/* Tipo de reposo autorizado, largo: 1 */
	private int tiporepoau;
	
	/* Jornada reposo autorizada, largo: 1 */
	private String jorrepoaut;
	
	/* Derecho a subsidio, largo: 1 */
	private String desubsidio;
	
	/* Fec.recepci�n ent.autoriz, largo: 8 */
	private int fecreceent;
	
	/* Fec.resoluci�n ent.autoriza, largo: 8 */
	private int fecresoent;
	
	/* RUT contralor�a m�dica, largo: 10 */
	private String rutcontral;
	
	/* RUT empleador, largo: 10 */
	private String rutemplead;
	
	/* Fec.recepci�n lic.empleador, largo: 8 */
	private int fecreceemp;
	
	/* Regi�n empleador, largo: 2 */
	private int regionempl;
	
	/* Comuna empleador, largo: 5 */
	private int comunaempl;
	
	/* Actividad laboral trabajador, largo: 2 */
	private int actlabtrab;
	
	/* Ocupaci�n del trabajador, largo: 2 */
	private int ocupactrab;
	
	/* Fec.recepci�n ent.pagadora, largo: 8 */
	private int fecrecepag;
	
	/* Tipo de r�gimen previsional, largo: 1 */
	private int tipregprev;
	
	/* Calidad del trabajador, largo: 1 */
	private int calitrabaj;
	
	/* Tipo ent.pagadora subsidio, largo: 1 */
	private String tipoentpag;
	
	/* Fec.primera afil.ent.previs., largo: 8 */
	private int fecpriafil;
	
	/* Fecha contrato trabajo, largo: 8 */
	private int feccontrab;

//adicionales
	/* Estado de la licencia, largo: 1 */
	private int estadolic;
	
	/* Licencia rechazada, largo: 1 */
	private String licrechaz;
	
	/* Per�odo a procesar, largo: 6 */
	private int fecproceso;
	
	/* Archivo origen datos, largo: 10 */
	private String archivo;
	
	/*Correlativo, largo: 6*/
	private int correlativ;
	
	/* correlativo B, largo: 4 */
	private int correlab;
	
	public LM_VO() {
	}
	
	public LM_VO(int operador, int tipoform, String folio, int art77bis,
			int fecinform, String ruttrabaj, int fecemision, int fecinirepo,
			int fecterrepo, int edadtrabaj, int fecnactrab, int gentrabaj,
			int numdiaslic, int licmatsupl, int fecnachijo, String ruthijo,
			int tipolic, int recuplabor, int iniinvalid, int fecconcep,
			int tiporeposo, String jorreposo, int lugreposo, String especialid,
			int tipoprofes, String rutprofes, String nomprofes, int licmodific,
			int entautoriz, int tipolmresu, int ndiasincap, String diagresuel,
			int periodo, int ndiasprev, int estadoreso, int tiporesolu,
			int redictamen, int causarecha, int tiporepoau, String jorrepoaut,
			String desubsidio, int fecreceent, int fecresoent,
			String rutcontral, String rutemplead, int fecreceemp,
			int regionempl, int comunaempl, int actlabtrab, int ocupactrab,
			int fecrecepag, int tipregprev, int calitrabaj, String tipoentpag,
			int fecpriafil, int feccontrab, int estadolic, String licrechaz,
			int fecproceso, String archivo, int correlativ, int correlab) {
		super();
		this.operador=operador;
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
		this.correlab = correlab;
	}






	public int getOperador() {
		return operador;
	}

	public int getTipoform() {
		return tipoform;
	}

	public String getFolio() {
		return folio;
	}

	public int getArt77bis() {
		return art77bis;
	}

	public int getFecinform() {
		return fecinform;
	}

	public String getRuttrabaj() {
		return ruttrabaj;
	}

	public int getFecemision() {
		return fecemision;
	}

	public int getFecinirepo() {
		return fecinirepo;
	}

	public int getFecterrepo() {
		return fecterrepo;
	}

	public int getEdadtrabaj() {
		return edadtrabaj;
	}

	public int getFecnactrab() {
		return fecnactrab;
	}

	public int getGentrabaj() {
		return gentrabaj;
	}

	public int getNumdiaslic() {
		return numdiaslic;
	}

	public int getLicmatsupl() {
		return licmatsupl;
	}

	public int getFecnachijo() {
		return fecnachijo;
	}

	public String getRuthijo() {
		return ruthijo;
	}

	public int getTipolic() {
		return tipolic;
	}

	public int getRecuplabor() {
		return recuplabor;
	}

	public int getIniinvalid() {
		return iniinvalid;
	}

	public int getFecconcep() {
		return fecconcep;
	}

	public int getTiporeposo() {
		return tiporeposo;
	}

	public String getJorreposo() {
		return jorreposo;
	}

	public int getLugreposo() {
		return lugreposo;
	}

	public String getEspecialid() {
		return especialid;
	}

	public int getTipoprofes() {
		return tipoprofes;
	}

	public String getRutprofes() {
		return rutprofes;
	}

	public String getNomprofes() {
		return nomprofes;
	}

	public int getLicmodific() {
		return licmodific;
	}

	public int getEntautoriz() {
		return entautoriz;
	}

	public int getTipolmresu() {
		return tipolmresu;
	}

	public int getNdiasincap() {
		return ndiasincap;
	}

	public String getDiagresuel() {
		return diagresuel;
	}

	public int getPeriodo() {
		return periodo;
	}

	public int getNdiasprev() {
		return ndiasprev;
	}

	public int getEstadoreso() {
		return estadoreso;
	}

	public int getTiporesolu() {
		return tiporesolu;
	}

	public int getRedictamen() {
		return redictamen;
	}

	public int getCausarecha() {
		return causarecha;
	}

	public int getTiporepoau() {
		return tiporepoau;
	}

	public String getJorrepoaut() {
		return jorrepoaut;
	}

	public String getDesubsidio() {
		return desubsidio;
	}

	public int getFecreceent() {
		return fecreceent;
	}

	public int getFecresoent() {
		return fecresoent;
	}

	public String getRutcontral() {
		return rutcontral;
	}

	public String getRutemplead() {
		return rutemplead;
	}

	public int getFecreceemp() {
		return fecreceemp;
	}

	public int getRegionempl() {
		return regionempl;
	}

	public int getComunaempl() {
		return comunaempl;
	}

	public int getActlabtrab() {
		return actlabtrab;
	}

	public int getOcupactrab() {
		return ocupactrab;
	}

	public int getFecrecepag() {
		return fecrecepag;
	}

	public int getTipregprev() {
		return tipregprev;
	}

	public int getCalitrabaj() {
		return calitrabaj;
	}

	public String getTipoentpag() {
		return tipoentpag;
	}

	public int getFecpriafil() {
		return fecpriafil;
	}

	public int getFeccontrab() {
		return feccontrab;
	}

	public int getEstadolic() {
		return estadolic;
	}

	public String getLicrechaz() {
		return licrechaz;
	}

	public int getFecproceso() {
		return fecproceso;
	}

	public String getArchivo() {
		return archivo;
	}
	
	public int getCorrelativ() {
		return correlativ;
	}
	
/*set*/
	
	public void setOperador(int operador) {
		this.operador = operador;
	}

	public void setTipoform(int tipoform) {
		this.tipoform = tipoform;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public void setArt77bis(int art77bis) {
		this.art77bis = art77bis;
	}

	public void setFecinform(int fecinform) {
		this.fecinform = fecinform;
	}

	public void setRuttrabaj(String ruttrabaj) {
		this.ruttrabaj = ruttrabaj;
	}

	public void setFecemision(int fecemision) {
		this.fecemision = fecemision;
	}

	public void setFecinirepo(int fecinirepo) {
		this.fecinirepo = fecinirepo;
	}

	public void setFecterrepo(int fecterrepo) {
		this.fecterrepo = fecterrepo;
	}

	public void setEdadtrabaj(int edadtrabaj) {
		this.edadtrabaj = edadtrabaj;
	}

	public void setFecnactrab(int fecnactrab) {
		this.fecnactrab = fecnactrab;
	}

	public void setGentrabaj(int gentrabaj) {
		this.gentrabaj = gentrabaj;
	}

	public void setNumdiaslic(int numdiaslic) {
		this.numdiaslic = numdiaslic;
	}

	public void setLicmatsupl(int licmatsupl) {
		this.licmatsupl = licmatsupl;
	}

	public void setFecnachijo(int fecnachijo) {
		this.fecnachijo = fecnachijo;
	}

	public void setRuthijo(String ruthijo) {
		this.ruthijo = ruthijo;
	}

	public void setTipolic(int tipolic) {
		this.tipolic = tipolic;
	}

	public void setRecuplabor(int recuplabor) {
		this.recuplabor = recuplabor;
	}

	public void setIniinvalid(int iniinvalid) {
		this.iniinvalid = iniinvalid;
	}

	public void setFecconcep(int fecconcep) {
		this.fecconcep = fecconcep;
	}

	public void setTiporeposo(int tiporeposo) {
		this.tiporeposo = tiporeposo;
	}

	public void setJorreposo(String jorreposo) {
		this.jorreposo = jorreposo;
	}

	public void setLugreposo(int lugreposo) {
		this.lugreposo = lugreposo;
	}

	public void setEspecialid(String especialid) {
		this.especialid = especialid;
	}

	public void setTipoprofes(int tipoprofes) {
		this.tipoprofes = tipoprofes;
	}

	public void setRutprofes(String rutprofes) {
		this.rutprofes = rutprofes;
	}

	public void setNomprofes(String nomprofes) {
		this.nomprofes = nomprofes;
	}

	public void setLicmodific(int licmodific) {
		this.licmodific = licmodific;
	}

	public void setEntautoriz(int entautoriz) {
		this.entautoriz = entautoriz;
	}

	public void setTipolmresu(int tipolmresu) {
		this.tipolmresu = tipolmresu;
	}

	public void setNdiasincap(int ndiasincap) {
		this.ndiasincap = ndiasincap;
	}

	public void setDiagresuel(String diagresuel) {
		this.diagresuel = diagresuel;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public void setNdiasprev(int ndiasprev) {
		this.ndiasprev = ndiasprev;
	}

	public void setEstadoreso(int estadoreso) {
		this.estadoreso = estadoreso;
	}

	public void setTiporesolu(int tiporesolu) {
		this.tiporesolu = tiporesolu;
	}

	public void setRedictamen(int redictamen) {
		this.redictamen = redictamen;
	}

	public void setCausarecha(int causarecha) {
		this.causarecha = causarecha;
	}

	public void setTiporepoau(int tiporepoau) {
		this.tiporepoau = tiporepoau;
	}

	public void setJorrepoaut(String jorrepoaut) {
		this.jorrepoaut = jorrepoaut;
	}

	public void setDesubsidio(String desubsidio) {
		this.desubsidio = desubsidio;
	}

	public void setFecreceent(int fecreceent) {
		this.fecreceent = fecreceent;
	}

	public void setFecresoent(int fecresoent) {
		this.fecresoent = fecresoent;
	}

	public void setRutcontral(String rutcontral) {
		this.rutcontral = rutcontral;
	}

	public void setRutemplead(String rutemplead) {
		this.rutemplead = rutemplead;
	}

	public void setFecreceemp(int fecreceemp) {
		this.fecreceemp = fecreceemp;
	}

	public void setRegionempl(int regionempl) {
		this.regionempl = regionempl;
	}

	public void setComunaempl(int comunaempl) {
		this.comunaempl = comunaempl;
	}

	public void setActlabtrab(int actlabtrab) {
		this.actlabtrab = actlabtrab;
	}

	public void setOcupactrab(int ocupactrab) {
		this.ocupactrab = ocupactrab;
	}

	public void setFecrecepag(int fecrecepag) {
		this.fecrecepag = fecrecepag;
	}

	public void setTipregprev(int tipregprev) {
		this.tipregprev = tipregprev;
	}

	public void setCalitrabaj(int calitrabaj) {
		this.calitrabaj = calitrabaj;
	}

	public void setTipoentpag(String tipoentpag) {
		this.tipoentpag = tipoentpag;
	}

	public void setFecpriafil(int fecpriafil) {
		this.fecpriafil = fecpriafil;
	}

	public void setFeccontrab(int feccontrab) {
		this.feccontrab = feccontrab;
	}

	public void setEstadolic(int estadolic) {
		this.estadolic = estadolic;
	}

	public void setLicrechaz(String licrechaz) {
		this.licrechaz = licrechaz;
	}

	public void setFecproceso(int fecproceso) {
		this.fecproceso = fecproceso;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public void setCorrelativ(int correlativ) {
		this.correlativ = correlativ;
	}
	
		
	public int getCorrelab() {
		return correlab;
	}

	public void setCorrelab(int correlab) {
		this.correlab = correlab;
	}

	public String toString(){
		return 
		"operador="+operador+"\n"+
		"tipoform="+tipoform+"\n"+
		"folio="+folio+"\n"+
		"art77bis="+art77bis+"\n"+
		"fecinform="+fecinform+"\n"+
		"ruttrabaj="+ruttrabaj+"\n"+
		"fecemision="+fecemision+"\n"+
		"fecinirepo="+fecinirepo+"\n"+
		"fecterrepo="+fecterrepo+"\n"+
		"edadtrabaj="+edadtrabaj+"\n"+
		"fecnactrab="+fecnactrab+"\n"+
		"gentrabaj="+gentrabaj+"\n"+
		"numdiaslic="+numdiaslic+"\n"+
		"licmatsupl="+licmatsupl+"\n"+
		"fecnachijo="+fecnachijo+"\n"+
		"ruthijo="+ruthijo+"\n"+
		"tipolic="+tipolic+"\n"+
		"recuplabor="+recuplabor+"\n"+
		"iniinvalid="+iniinvalid+"\n"+
		"fecconcep="+fecconcep+"\n"+
		"tiporeposo="+tiporeposo+"\n"+
		"jorreposo="+jorreposo+"\n"+
		"lugreposo="+lugreposo+"\n"+
		"especialid="+especialid+"\n"+
		"tipoprofes="+tipoprofes+"\n"+
		"rutprofes="+rutprofes+"\n"+
		"nomprofes="+nomprofes+"\n"+
		"licmodific="+licmodific+"\n"+
		"entautoriz="+entautoriz+"\n"+
		"tipolmresu="+tipolmresu+"\n"+
		"ndiasincap="+ndiasincap+"\n"+
		"diagresuel="+diagresuel+"\n"+
		"periodo="+periodo+"\n"+
		"ndiasprev="+ndiasprev+"\n"+
		"estadoreso="+estadoreso+"\n"+
		"tiporesolu="+tiporesolu+"\n"+
		"redictamen="+redictamen+"\n"+
		"causarecha="+causarecha+"\n"+
		"tiporepoau="+tiporepoau+"\n"+
		"jorrepoaut="+jorrepoaut+"\n"+
		"desubsidio="+desubsidio+"\n"+
		"fecreceent="+fecreceent+"\n"+
		"fecresoent="+fecresoent+"\n"+
		"rutcontral="+rutcontral+"\n"+
		"rutemplead="+rutemplead+"\n"+
		"fecreceemp="+fecreceemp+"\n"+
		"regionempl="+regionempl+"\n"+
		"comunaempl="+comunaempl+"\n"+
		"actlabtrab="+actlabtrab+"\n"+
		"ocupactrab="+ocupactrab+"\n"+
		"fecrecepag="+fecrecepag+"\n"+
		"tipregprev="+tipregprev+"\n"+
		"calitrabaj="+calitrabaj+"\n"+
		"tipoentpag="+tipoentpag+"\n"+
		"fecpriafil="+fecpriafil+"\n"+
		"feccontrab="+feccontrab+"\n"+
		"estadolic="+estadolic+"\n"+
		"licrechaz="+licrechaz+"\n"+
		"fecproceso="+fecproceso+"\n"+
		"archivo="+archivo+"\n"+
		"correlativ="+correlativ+"\n"
				;
		
	}
}//end class L_VO
