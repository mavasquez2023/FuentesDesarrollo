package cl.laaraucana.silmsil.vo;
/**
 * clase que representa estructura para datos SIL
 * LIEXP.ILFSIL050
 * **/
public class SIL_VO {

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

/*adicionales*/
	/* correlativo, largo: 4 */
	private int correlativ;
	/* correlativo B, largo: 4 */
	private int correlab;
	/* Nombre Archivo, largo: 10 */
	private int lichasfec;
	/* pago folio, largo: 10 */
	private int pagfol;
	/* periodo pago, largo: 6 */
	private int perpag;
	/* nobre archivo , largo: 10 */
	private String archiv;
	
	
	public SIL_VO() {
	}


	public SIL_VO(int codope, int tpofor, String nrofol, String ruttrabaj,
			int fecemi, int diasub, int mtoliq, int mtocot, int codint,
			int finipa, int mocope, int baseca, int idlice, int inimes,
			int tpolic, int ndicot, int ndiinc, int ndipag, int mtsbpa,
			int mtsbdi, int mcsegc, int motcot, String ofipgo, int ccopgo,
			String inssal, int submat, int tpoliq, int fecpgo, int mliqpa,
			int rimpms, int correlativ, int correlab, int lichasfec,
			int pagfol, int perpag, String archiv) {
		super();
		this.codope = codope;
		this.tpofor = tpofor;
		this.nrofol = nrofol;
		this.ruttrabaj = ruttrabaj;
		this.fecemi = fecemi;
		this.diasub = diasub;
		this.mtoliq = mtoliq;
		this.mtocot = mtocot;
		this.codint = codint;
		this.finipa = finipa;
		this.mocope = mocope;
		this.baseca = baseca;
		this.idlice = idlice;
		this.inimes = inimes;
		this.tpolic = tpolic;
		this.ndicot = ndicot;
		this.ndiinc = ndiinc;
		this.ndipag = ndipag;
		this.mtsbpa = mtsbpa;
		this.mtsbdi = mtsbdi;
		this.mcsegc = mcsegc;
		this.motcot = motcot;
		this.ofipgo = ofipgo;
		this.ccopgo = ccopgo;
		this.inssal = inssal;
		this.submat = submat;
		this.tpoliq = tpoliq;
		this.fecpgo = fecpgo;
		this.mliqpa = mliqpa;
		this.rimpms = rimpms;
		this.correlativ = correlativ;
		this.correlab = correlab;
		this.lichasfec = lichasfec;
		this.pagfol = pagfol;
		this.perpag = perpag;
		this.archiv = archiv;
	}


	public int getCodope() {
		return codope;
	}


	public void setCodope(int codope) {
		this.codope = codope;
	}


	public int getTpofor() {
		return tpofor;
	}


	public void setTpofor(int tpofor) {
		this.tpofor = tpofor;
	}


	public String getNrofol() {
		return nrofol;
	}


	public void setNrofol(String nrofol) {
		this.nrofol = nrofol;
	}


	public String getRuttrabaj() {
		return ruttrabaj;
	}


	public void setRuttrabaj(String ruttrabaj) {
		this.ruttrabaj = ruttrabaj;
	}


	public int getFecemi() {
		return fecemi;
	}


	public void setFecemi(int fecemi) {
		this.fecemi = fecemi;
	}


	public int getDiasub() {
		return diasub;
	}


	public void setDiasub(int diasub) {
		this.diasub = diasub;
	}


	public int getMtoliq() {
		return mtoliq;
	}


	public void setMtoliq(int mtoliq) {
		this.mtoliq = mtoliq;
	}


	public int getMtocot() {
		return mtocot;
	}


	public void setMtocot(int mtocot) {
		this.mtocot = mtocot;
	}


	public int getCodint() {
		return codint;
	}


	public void setCodint(int codint) {
		this.codint = codint;
	}


	public int getFinipa() {
		return finipa;
	}


	public void setFinipa(int finipa) {
		this.finipa = finipa;
	}


	public int getMocope() {
		return mocope;
	}


	public void setMocope(int mocope) {
		this.mocope = mocope;
	}


	public int getBaseca() {
		return baseca;
	}


	public void setBaseca(int baseca) {
		this.baseca = baseca;
	}


	public int getIdlice() {
		return idlice;
	}


	public void setIdlice(int idlice) {
		this.idlice = idlice;
	}


	public int getInimes() {
		return inimes;
	}


	public void setInimes(int inimes) {
		this.inimes = inimes;
	}


	public int getTpolic() {
		return tpolic;
	}


	public void setTpolic(int tpolic) {
		this.tpolic = tpolic;
	}


	public int getNdicot() {
		return ndicot;
	}


	public void setNdicot(int ndicot) {
		this.ndicot = ndicot;
	}


	public int getNdiinc() {
		return ndiinc;
	}


	public void setNdiinc(int ndiinc) {
		this.ndiinc = ndiinc;
	}


	public int getNdipag() {
		return ndipag;
	}


	public void setNdipag(int ndipag) {
		this.ndipag = ndipag;
	}


	public int getMtsbpa() {
		return mtsbpa;
	}


	public void setMtsbpa(int mtsbpa) {
		this.mtsbpa = mtsbpa;
	}


	public int getMtsbdi() {
		return mtsbdi;
	}


	public void setMtsbdi(int mtsbdi) {
		this.mtsbdi = mtsbdi;
	}


	public int getMcsegc() {
		return mcsegc;
	}


	public void setMcsegc(int mcsegc) {
		this.mcsegc = mcsegc;
	}


	public int getMotcot() {
		return motcot;
	}


	public void setMotcot(int motcot) {
		this.motcot = motcot;
	}


	public String getOfipgo() {
		return ofipgo;
	}


	public void setOfipgo(String ofipgo) {
		this.ofipgo = ofipgo;
	}


	public int getCcopgo() {
		return ccopgo;
	}


	public void setCcopgo(int ccopgo) {
		this.ccopgo = ccopgo;
	}


	public String getInssal() {
		return inssal;
	}


	public void setInssal(String inssal) {
		this.inssal = inssal;
	}


	public int getSubmat() {
		return submat;
	}


	public void setSubmat(int submat) {
		this.submat = submat;
	}


	public int getTpoliq() {
		return tpoliq;
	}


	public void setTpoliq(int tpoliq) {
		this.tpoliq = tpoliq;
	}


	public int getFecpgo() {
		return fecpgo;
	}


	public void setFecpgo(int fecpgo) {
		this.fecpgo = fecpgo;
	}


	public int getMliqpa() {
		return mliqpa;
	}


	public void setMliqpa(int mliqpa) {
		this.mliqpa = mliqpa;
	}


	public int getRimpms() {
		return rimpms;
	}


	public void setRimpms(int rimpms) {
		this.rimpms = rimpms;
	}


	public int getCorrelativ() {
		return correlativ;
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


	public int getLichasfec() {
		return lichasfec;
	}


	public void setLichasfec(int lichasfec) {
		this.lichasfec = lichasfec;
	}


	public int getPagfol() {
		return pagfol;
	}


	public void setPagfol(int pagfol) {
		this.pagfol = pagfol;
	}


	public int getPerpag() {
		return perpag;
	}


	public void setPerpag(int perpag) {
		this.perpag = perpag;
	}


	public String getArchiv() {
		return archiv;
	}


	public void setArchiv(String archiv) {
		this.archiv = archiv;
	}

	
	
}//end class SIL_VO
