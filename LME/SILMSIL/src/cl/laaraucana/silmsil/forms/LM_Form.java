package cl.laaraucana.silmsil.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * @version 	1.0
 * @author
 */
public class LM_Form extends ActionForm{
	
	/* Operador, largo: 1 */
	private int operador;
	
	/* Tipo formulario, largo: 1 */
	private int tipoform;
	
	/* Folio, largo: 20 */
	private String folio;
	
	/* Artículo 77 bis, largo: 1 */
	private int art77bis;
	
	/* Fecha de información, largo: 8 */
	private int fecinform;
	
	/* RUT trabajador, largo: 10 */
	private String ruttrabaj;
	
	/* Fecha emisión licencia, largo: 8 */
	private int fecemision;
	
	/* Fecha inicio reposo, largo: 8 */
	private int fecinirepo;
	
	/* Fecha término reposo, largo: 8 */
	private int fecterrepo;
	
	/* Edad del trabajador, largo: 2 */
	private int edadtrabaj;
	
	/* Fecha nacimiento trabajador, largo: 8 */
	private int fecnactrab;
	
	/* Género trabajador (M/F), largo: 1 */
	private int gentrabaj;
	
	/* Número días licencia, largo: 3 */
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
	
	/* Inicio trámite invalidez, largo: 1 */
	private int iniinvalid;
	
	/* Año y mes concepción, largo: 6 */
	private int fecconcep;
	
	/* Tipo de reposo, largo: 1 */
	private int tiporeposo;
	
	/* Jornada de reposo, largo: 1 */
	private String jorreposo;
	
	/* Lugar de reposo, largo: 1 */
	private int lugreposo;
	
	/* Descripción especialidad, largo: 30 */
	private String especialid;
	
	/* Tipo de profesional, largo: 1 */
	private int tipoprofes;
	
	/* RUT profesional médico, largo: 10 */
	private String rutprofes;
	
	/* Nombre profesional médico, largo: 60 */
	private String nomprofes;
	
	/* Licencia modificada, largo: 1 */
	private int licmodific;
	
	/* Código entidad autorizadora, largo: 5 */
	private int entautoriz;
	
	/* Tipo licencia méd. resuelto, largo: 1 */
	private int tipolmresu;
	
	/* Núm días incapacidad autoriz, largo: 3 */
	private int ndiasincap;
	
	/* Código diagnóstico resuelto, largo: 5 */
	private String diagresuel;
	
	/* Período, largo: 1 */
	private int periodo;
	
	/* Núm.días previos autorizados, largo: 4 */
	private int ndiasprev;
	
	/* Estado de la resolución, largo: 1 */
	private int estadoreso;
	
	/* Tipo de resolución, largo: 1 */
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
	
	/* Fec.recepción ent.autoriz, largo: 8 */
	private int fecreceent;
	
	/* Fec.resolución ent.autoriza, largo: 8 */
	private int fecresoent;
	
	/* RUT contraloría médica, largo: 10 */
	private String rutcontral;
	
	/* RUT empleador, largo: 10 */
	private String rutemplead;
	
	/* Fec.recepción lic.empleador, largo: 8 */
	private int fecreceemp;
	
	/* Región empleador, largo: 2 */
	private int regionempl;
	
	/* Comuna empleador, largo: 5 */
	private int comunaempl;
	
	/* Actividad laboral trabajador, largo: 2 */
	private int actlabtrab;
	
	/* Ocupación del trabajador, largo: 2 */
	private int ocupactrab;
	
	/* Fec.recepción ent.pagadora, largo: 8 */
	private int fecrecepag;
	
	/* Tipo de régimen previsional, largo: 1 */
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
	
	/* Período a procesar, largo: 6 */
	private int fecproceso;
	
	/* Archivo origen datos, largo: 10 */
	private String archivo;
	
	/*Correlativo, largo: 6*/
	private int correlativ;
	
	/*Correlativo, largo: 6*/
	private int correlab;
	
//add busuqeda.
	private String folio_b;
	private int fecterrepo_b;
	
	/* Campo checkbox para agrupar por errores */ 
	private String chk_agrupar_LM;
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		operador=0;
		tipoform=0;
		folio="";
		art77bis=0;
		fecinform=0;
		ruttrabaj="";
		fecemision=0;
		fecinirepo=0;
		fecterrepo=0;
		edadtrabaj=0;
		fecnactrab=0;
		gentrabaj=0;
		numdiaslic=0;
		licmatsupl=0;
		fecnachijo=0;
		ruthijo="";
		tipolic=0;
		recuplabor=0;
		iniinvalid=0;
		fecconcep=0;
		tiporeposo=0;
		jorreposo="";
		lugreposo=0;
		especialid="";
		tipoprofes=0;
		rutprofes="";
		nomprofes="";
		licmodific=0;
		entautoriz=0;
		tipolmresu=0;
		ndiasincap=0;
		diagresuel="";
		periodo=0;
		ndiasprev=0;
		estadoreso=0;
		tiporesolu=0;
		redictamen=0;
		causarecha=0;
		tiporepoau=0;
		jorrepoaut="";
		desubsidio="";
		fecreceent=0;
		fecresoent=0;
		rutcontral="";
		rutemplead="";
		fecreceemp=0;
		regionempl=0;
		comunaempl=0;
		actlabtrab=0;
		ocupactrab=0;
		fecrecepag=0;
		tipregprev=0;
		calitrabaj=0;
		tipoentpag="";
		fecpriafil=0;
		feccontrab=0;
		
		estadolic=0;
		licrechaz="";
		fecproceso=0;
		archivo="";
		correlativ=0;
		correlab=0;
		
		folio_b="";
		fecterrepo_b=0;
		chk_agrupar_LM="";
    }
	
    public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {
	ActionErrors errors = new ActionErrors();
	return errors;
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

	public int getCorrelativ() {
		return correlativ;
	}

	public void setCorrelativ(int correlativ) {
		this.correlativ = correlativ;
	}

	public String getFolio_b() {
		return folio_b;
	}

	public int getFecterrepo_b() {
		return fecterrepo_b;
	}

	public void setFolio_b(String folio_b) {
		this.folio_b = folio_b;
	}

	public void setFecterrepo_b(int fecterrepo_b) {
		this.fecterrepo_b = fecterrepo_b;
	}

	public String getChk_agrupar_LM() {
		return chk_agrupar_LM;
	}

	public void setChk_agrupar_LM(String chk_agrupar_LM) {
		this.chk_agrupar_LM = chk_agrupar_LM;
	}

	public int getCorrelab() {
		return correlab;
	}

	public void setCorrelab(int correlab) {
		this.correlab = correlab;
	}    
    
	
	
}//end class LM_Form
