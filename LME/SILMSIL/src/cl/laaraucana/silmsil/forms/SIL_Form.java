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
public class SIL_Form extends ActionForm{
	
	/* Codigo Operador, largo: 1 */
	private int codope;
	
	/* Tipo Formulario, largo: 1 */
	private int tpofor;
	
	/* Nro. Folio, largo: 20 */
	private String nrofol;
	
	/* Rut Trab.con DV, largo: 10 */
	private String ruttrabaj;
	
	/* Fecha Emisión Lic., largo: 8 */
	private int fecemi;
	
	/* N°Dias Subsidio, largo: 3 */
	private int diasub;
	
	/* Monto Subsid.Liquido, largo: 9 */
	private int mtoliq;
	
	/* Monto Cotiz. Salud, largo: 9 */
	private int mtocot;
	
	/* Cd.Instit Previsonal, largo: 5 */
	private int codint;
	
	/* Fecha Inicio Pago, largo: 8 */
	private int finipa;
	
	/* Mto Cotiz. Pension, largo: 8 */
	private int mocope;
	
	/* Mto.Base Calculo Sub., largo: 8 */
	private int baseca;
	
	/* Ident. Lic.Continua, largo: 10 */
	private int idlice;
	
	/* Inicia Mes que Informa, largo: 1 */
	private int inimes;
	
	/* Tipo Lic. Medica Resuel., largo: 1 */
	private int tpolic;
	
	/* Nr.Dias Cotiz.Medicas, largo: 3 */
	private int ndicot;
	
	/* Nr.Dias Incap.Autoriz ., largo: 3 */
	private int ndiinc;
	
	/* Nr.Dias Pagados Periodo, largo: 3 */
	private int ndipag;
	
	/* Mto.Sub. Pagado, largo: 8 */
	private int mtsbpa;
	
	/* Mto.Sub. Diario, largo: 8 */
	private int mtsbdi;
	
	/* Mto.Cot. Seg.Cesantia, largo: 8 */
	private int mcsegc;
	
	/* Mto.Otras Cotizaciones, largo: 8 */
	private int motcot;
	
	/* Oficina Pago, largo: 40 */
	private String ofipgo;
	
	/* Codigo Comuna Pago, largo: 5 */
	private int ccopgo;
	
	/* Institucion de Salud, largo: 10 */
	private String inssal;
	
	/* Tp.Subsidio Maternal, largo: 1 */
	private int submat;
	
	/* Tp.de Liquidacion, largo: 1 */
	private int tpoliq;
	
	/* Fecha de Pago Subsid., largo: 8 */
	private int fecpgo;
	
	/* Mto.Liq. Pagado, largo: 8 */
	private int mliqpa;
	
	/* Remun.Impon Mes Ant.Lic, largo: 8 */
	private int rimpms;

//adicionales
	/* Periodo Pago, largo: 6 */
	private int perpag;
	
	/* Nombre Archivo, largo: 10 */
	private String archiv;
	
	/*Correlativo, largo: 6*/
	private int correlativ;
	
	/*Correlativo, largo: 6*/
	private int correlab;
	
	/*pago folio, */
	private int pagfol;
	
	/*licencia fecha hasta.*/
	private int lichasfec;
	
	/* Campo checkbox para agrupar por errores */ 
	private String chk_agrupar_SIL;
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		codope = 0;
		tpofor = 0;
		nrofol = "";
		ruttrabaj = "";
		fecemi = 0;
		diasub = 0;
		mtoliq = 0;
		mtocot = 0;
		codint = 0;
		finipa = 0;
		mocope = 0;
		baseca = 0;
		idlice = 0;
		inimes = 0;
		tpolic = 0;
		ndicot = 0;
		ndiinc = 0;
		ndipag = 0;
		mtsbpa = 0;
		mtsbdi = 0;
		mcsegc = 0;
		motcot = 0;
		ofipgo = "";
		ccopgo = 0;
		inssal = "";
		submat = 0;
		tpoliq = 0;
		fecpgo = 0;
		mliqpa = 0;
		rimpms = 0;
		
		
		perpag=0;
		archiv="";
		correlativ=0;
		correlab=0;
		pagfol=0;
		lichasfec=0;
		chk_agrupar_SIL="";
    }
	
    public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {
	ActionErrors errors = new ActionErrors();
	return errors;
    }

	public int getCodope() {
		return codope;
	}

	public int getTpofor() {
		return tpofor;
	}

	public String getNrofol() {
		return nrofol;
	}

	public String getRuttrabaj() {
		return ruttrabaj;
	}

	public int getFecemi() {
		return fecemi;
	}

	public int getDiasub() {
		return diasub;
	}

	public int getMtoliq() {
		return mtoliq;
	}

	public int getMtocot() {
		return mtocot;
	}

	public int getCodint() {
		return codint;
	}

	public int getFinipa() {
		return finipa;
	}

	public int getMocope() {
		return mocope;
	}

	public int getBaseca() {
		return baseca;
	}

	public int getIdlice() {
		return idlice;
	}

	public int getInimes() {
		return inimes;
	}

	public int getTpolic() {
		return tpolic;
	}

	public int getNdicot() {
		return ndicot;
	}

	public int getNdiinc() {
		return ndiinc;
	}

	public int getNdipag() {
		return ndipag;
	}

	public int getMtsbpa() {
		return mtsbpa;
	}

	public int getMtsbdi() {
		return mtsbdi;
	}

	public int getMcsegc() {
		return mcsegc;
	}

	public int getMotcot() {
		return motcot;
	}

	public String getOfipgo() {
		return ofipgo;
	}

	public int getCcopgo() {
		return ccopgo;
	}

	public String getInssal() {
		return inssal;
	}

	public int getSubmat() {
		return submat;
	}

	public int getTpoliq() {
		return tpoliq;
	}

	public int getFecpgo() {
		return fecpgo;
	}

	public int getMliqpa() {
		return mliqpa;
	}

	public int getRimpms() {
		return rimpms;
	}

	public int getPerpag() {
		return perpag;
	}

	public String getArchiv() {
		return archiv;
	}

	public int getCorrelativ() {
		return correlativ;
	}

	public int getPagfol() {
		return pagfol;
	}

	public int getLichasfec() {
		return lichasfec;
	}

	public void setCodope(int codope) {
		this.codope = codope;
	}

	public void setTpofor(int tpofor) {
		this.tpofor = tpofor;
	}

	public void setNrofol(String nrofol) {
		this.nrofol = nrofol;
	}

	public void setRuttrabaj(String ruttrabaj) {
		this.ruttrabaj = ruttrabaj;
	}

	public void setFecemi(int fecemi) {
		this.fecemi = fecemi;
	}

	public void setDiasub(int diasub) {
		this.diasub = diasub;
	}

	public void setMtoliq(int mtoliq) {
		this.mtoliq = mtoliq;
	}

	public void setMtocot(int mtocot) {
		this.mtocot = mtocot;
	}

	public void setCodint(int codint) {
		this.codint = codint;
	}

	public void setFinipa(int finipa) {
		this.finipa = finipa;
	}

	public void setMocope(int mocope) {
		this.mocope = mocope;
	}

	public void setBaseca(int baseca) {
		this.baseca = baseca;
	}

	public void setIdlice(int idlice) {
		this.idlice = idlice;
	}

	public void setInimes(int inimes) {
		this.inimes = inimes;
	}

	public void setTpolic(int tpolic) {
		this.tpolic = tpolic;
	}

	public void setNdicot(int ndicot) {
		this.ndicot = ndicot;
	}

	public void setNdiinc(int ndiinc) {
		this.ndiinc = ndiinc;
	}

	public void setNdipag(int ndipag) {
		this.ndipag = ndipag;
	}

	public void setMtsbpa(int mtsbpa) {
		this.mtsbpa = mtsbpa;
	}

	public void setMtsbdi(int mtsbdi) {
		this.mtsbdi = mtsbdi;
	}

	public void setMcsegc(int mcsegc) {
		this.mcsegc = mcsegc;
	}

	public void setMotcot(int motcot) {
		this.motcot = motcot;
	}

	public void setOfipgo(String ofipgo) {
		this.ofipgo = ofipgo;
	}

	public void setCcopgo(int ccopgo) {
		this.ccopgo = ccopgo;
	}

	public void setInssal(String inssal) {
		this.inssal = inssal;
	}

	public void setSubmat(int submat) {
		this.submat = submat;
	}

	public void setTpoliq(int tpoliq) {
		this.tpoliq = tpoliq;
	}

	public void setFecpgo(int fecpgo) {
		this.fecpgo = fecpgo;
	}

	public void setMliqpa(int mliqpa) {
		this.mliqpa = mliqpa;
	}

	public void setRimpms(int rimpms) {
		this.rimpms = rimpms;
	}

	public void setPerpag(int perpag) {
		this.perpag = perpag;
	}

	public void setArchiv(String archiv) {
		this.archiv = archiv;
	}

	public void setCorrelativ(int correlativ) {
		this.correlativ = correlativ;
	}

	public void setPagfol(int pagfol) {
		this.pagfol = pagfol;
	}

	public void setLichasfec(int lichasfec) {
		this.lichasfec = lichasfec;
	}

	public String getChk_agrupar_SIL() {
		return chk_agrupar_SIL;
	}

	public void setChk_agrupar_SIL(String chk_agrupar_SIL) {
		this.chk_agrupar_SIL = chk_agrupar_SIL;
	}

	public int getCorrelab() {
		return correlab;
	}

	public void setCorrelab(int correlab) {
		this.correlab = correlab;
	}

	
    
}//end class SIL_Form
