package cl.laaraucana.silmsil.vo;
/**
 * Clase que representa estructura para datos SIL
 * LIEXP.ILFSIL050
 * **/
public class SIL_VistaErrores_VO {
	
	private boolean keyErrores;
	private boolean keyExiste;
	
	/* Representa estados arrojados por método 
	 * UtilProcesar.actualizarRegistro(). */
	private int keyEstado;
	
	/* Codigo Operador, largo: 1 */
	private String codope;
	/* Tipo Formulario, largo: 1 */
	private String tpofor;
	/* Nro. Folio, largo: 20 */
	private String nrofol;
	/* Rut Trab.con DV, largo: 10 */
	private String ruttrabaj;
	/* Fecha Emisión Lic., largo: 8 */
	private String fecemi;
	/* N°Dias Subsidio, largo: 3 */
	private String diasub;
	/* Monto Subsid.Liquido, largo: 9 */
	private String mtoliq;
	/* Monto Cotiz. Salud, largo: 9 */
	private String mtocot;
	/* Cd.Instit Previsonal, largo: 5 */
	private String codint;
	/* Fecha Inicio Pago, largo: 8 */
	private String finipa;
	/* Mto Cotiz. Pension, largo: 8 */
	private String mocope;
	/* Mto.Base Calculo Sub., largo: 8 */
	private String baseca;
	/* Ident. Lic.Continua, largo: 10 */
	private String idlice;
	/* Inicia Mes que Informa, largo: 1 */
	private String inimes;
	/* Tipo Lic. Medica Resuel., largo: 1 */
	private String tpolic;
	/* Nr.Dias Cotiz.Medicas, largo: 3 */
	private String ndicot;
	/* Nr.Dias Incap.Autoriz ., largo: 3 */
	private String ndiinc;
	/* Nr.Dias Pagados Periodo, largo: 3 */
	private String ndipag;
	/* Mto.Sub. Pagado, largo: 8 */
	private String mtsbpa;
	/* Mto.Sub. Diario, largo: 8 */
	private String mtsbdi;
	/* Mto.Cot. Seg.Cesantia, largo: 8 */
	private String mcsegc;
	/* Mto.Otras Cotizaciones, largo: 8 */
	private String motcot;	
	/* Oficina Pago, largo: 40 */
	private String ofipgo;
	/* Codigo Comuna Pago, largo: 5 */
	private String ccopgo;
	/* Institucion de Salud, largo: 10 */
	private String inssal;
	/* Tp.Subsidio Maternal, largo: 1 */
	private String submat;
	/* Tp.de Liquidacion, largo: 1 */
	private String tpoliq;
	/* Fecha de Pago Subsid., largo: 8 */
	private String fecpgo;
	/* Mto.Liq. Pagado, largo: 8 */
	private String mliqpa;
	/* Remun.Impon Mes Ant.Lic, largo: 8 */
	private String rimpms;

/*adicionales*/
	/* correlativo, largo: 4 */
	private String correlativ;
	/* Nombre Archivo, largo: 10 */
	private String lichasfec;
	/* pago folio, largo: 10 */
	private String pagfol;
	/* periodo pago, largo: 6 */
	private String perpag;
	/* nombre archivo , largo: 10 */
	private String archiv;
	
	
	public SIL_VistaErrores_VO() {
		this.codope = "";
		this.tpofor = "";
		this.nrofol = "";
		this.ruttrabaj = "";
		this.fecemi = "";
		this.diasub = "";
		this.mtoliq = "";
		this.mtocot = "";
		this.codint = "";
		this.finipa = "";
		this.mocope = "";
		this.baseca = "";
		this.idlice = "";
		this.inimes = "";
		this.tpolic = "";
		this.ndicot = "";
		this.ndiinc = "";
		this.ndipag = "";
		this.mtsbpa = "";
		this.mtsbdi = "";
		this.mcsegc = "";
		this.motcot = "";
		this.ofipgo = "";
		this.ccopgo = "";
		this.inssal = "";
		this.submat = "";
		this.tpoliq = "";
		this.fecpgo = "";
		this.mliqpa = "";
		this.rimpms = "";
		this.correlativ = "";
		this.lichasfec = "";
		this.pagfol = "";
		this.perpag = "";
		this.archiv = "";
	}

	

	public SIL_VistaErrores_VO(boolean keyErrores, boolean keyExiste,
			int keyEstado, String codope, String tpofor, String nrofol,
			String ruttrabaj, String fecemi, String diasub, String mtoliq,
			String mtocot, String codint, String finipa, String mocope,
			String baseca, String idlice, String inimes, String tpolic,
			String ndicot, String ndiinc, String ndipag, String mtsbpa,
			String mtsbdi, String mcsegc, String motcot, String ofipgo,
			String ccopgo, String inssal, String submat, String tpoliq,
			String fecpgo, String mliqpa, String rimpms, String correlativ,
			String lichasfec, String pagfol, String perpag, String archiv) {
		super();
		this.keyErrores = keyErrores;
		this.keyExiste = keyExiste;
		this.keyEstado = keyEstado;
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
		this.lichasfec = lichasfec;
		this.pagfol = pagfol;
		this.perpag = perpag;
		this.archiv = archiv;
	}

	
	public SIL_VistaErrores_VO(boolean keyErrores, boolean keyExiste) {
		super();
		this.keyErrores=keyErrores;
		this.keyExiste=keyExiste;
		this.codope = "";
		this.tpofor = "";
		this.nrofol = "";
		this.ruttrabaj = "";
		this.fecemi = "";
		this.diasub = "";
		this.mtoliq = "";
		this.mtocot = "";
		this.codint = "";
		this.finipa = "";
		this.mocope = "";
		this.baseca = "";
		this.idlice = "";
		this.inimes = "";
		this.tpolic = "";
		this.ndicot = "";
		this.ndiinc = "";
		this.ndipag = "";
		this.mtsbpa = "";
		this.mtsbdi = "";
		this.mcsegc = "";
		this.motcot = "";
		this.ofipgo = "";
		this.ccopgo = "";
		this.inssal = "";
		this.submat = "";
		this.tpoliq = "";
		this.fecpgo = "";
		this.mliqpa = "";
		this.rimpms = "";
		this.correlativ = "";
		this.lichasfec = "";
		this.pagfol = "";
		this.perpag = "";
		this.archiv = "";
	}


	public SIL_VistaErrores_VO(boolean keyErrores, boolean keyExiste, int keyEstado) {
		super();
		this.keyErrores=keyErrores;
		this.keyExiste=keyExiste;
		this.keyEstado=keyEstado;
		this.codope = "";
		this.tpofor = "";
		this.nrofol = "";
		this.ruttrabaj = "";
		this.fecemi = "";
		this.diasub = "";
		this.mtoliq = "";
		this.mtocot = "";
		this.codint = "";
		this.finipa = "";
		this.mocope = "";
		this.baseca = "";
		this.idlice = "";
		this.inimes = "";
		this.tpolic = "";
		this.ndicot = "";
		this.ndiinc = "";
		this.ndipag = "";
		this.mtsbpa = "";
		this.mtsbdi = "";
		this.mcsegc = "";
		this.motcot = "";
		this.ofipgo = "";
		this.ccopgo = "";
		this.inssal = "";
		this.submat = "";
		this.tpoliq = "";
		this.fecpgo = "";
		this.mliqpa = "";
		this.rimpms = "";
		this.correlativ = "";
		this.lichasfec = "";
		this.pagfol = "";
		this.perpag = "";
		this.archiv = "";
	}



	public boolean isKeyErrores() {
		return keyErrores;
	}


	public boolean isKeyExiste() {
		return keyExiste;
	}


	public String getCodope() {
		return codope;
	}


	public String getTpofor() {
		return tpofor;
	}


	public String getNrofol() {
		return nrofol;
	}


	public String getRuttrabaj() {
		return ruttrabaj;
	}


	public String getFecemi() {
		return fecemi;
	}


	public String getDiasub() {
		return diasub;
	}


	public String getMtoliq() {
		return mtoliq;
	}


	public String getMtocot() {
		return mtocot;
	}


	public String getCodint() {
		return codint;
	}


	public String getFinipa() {
		return finipa;
	}


	public String getMocope() {
		return mocope;
	}


	public String getBaseca() {
		return baseca;
	}


	public String getIdlice() {
		return idlice;
	}


	public String getInimes() {
		return inimes;
	}


	public String getTpolic() {
		return tpolic;
	}


	public String getNdicot() {
		return ndicot;
	}


	public String getNdiinc() {
		return ndiinc;
	}


	public String getNdipag() {
		return ndipag;
	}


	public String getMtsbpa() {
		return mtsbpa;
	}


	public String getMtsbdi() {
		return mtsbdi;
	}


	public String getMcsegc() {
		return mcsegc;
	}


	public String getMotcot() {
		return motcot;
	}


	public String getOfipgo() {
		return ofipgo;
	}


	public String getCcopgo() {
		return ccopgo;
	}


	public String getInssal() {
		return inssal;
	}


	public String getSubmat() {
		return submat;
	}


	public String getTpoliq() {
		return tpoliq;
	}


	public String getFecpgo() {
		return fecpgo;
	}


	public String getMliqpa() {
		return mliqpa;
	}


	public String getRimpms() {
		return rimpms;
	}


	public String getCorrelativ() {
		return correlativ;
	}


	public String getLichasfec() {
		return lichasfec;
	}


	public String getPagfol() {
		return pagfol;
	}


	public String getPerpag() {
		return perpag;
	}


	public String getArchiv() {
		return archiv;
	}


	public void setKeyErrores(boolean keyErrores) {
		this.keyErrores = keyErrores;
	}


	public void setKeyExiste(boolean keyExiste) {
		this.keyExiste = keyExiste;
	}


	public void setCodope(String codope) {
		this.codope = codope;
	}


	public void setTpofor(String tpofor) {
		this.tpofor = tpofor;
	}


	public void setNrofol(String nrofol) {
		this.nrofol = nrofol;
	}


	public void setRuttrabaj(String ruttrabaj) {
		this.ruttrabaj = ruttrabaj;
	}


	public void setFecemi(String fecemi) {
		this.fecemi = fecemi;
	}


	public void setDiasub(String diasub) {
		this.diasub = diasub;
	}


	public void setMtoliq(String mtoliq) {
		this.mtoliq = mtoliq;
	}


	public void setMtocot(String mtocot) {
		this.mtocot = mtocot;
	}


	public void setCodint(String codint) {
		this.codint = codint;
	}


	public void setFinipa(String finipa) {
		this.finipa = finipa;
	}


	public void setMocope(String mocope) {
		this.mocope = mocope;
	}


	public void setBaseca(String baseca) {
		this.baseca = baseca;
	}


	public void setIdlice(String idlice) {
		this.idlice = idlice;
	}


	public void setInimes(String inimes) {
		this.inimes = inimes;
	}


	public void setTpolic(String tpolic) {
		this.tpolic = tpolic;
	}


	public void setNdicot(String ndicot) {
		this.ndicot = ndicot;
	}


	public void setNdiinc(String ndiinc) {
		this.ndiinc = ndiinc;
	}


	public void setNdipag(String ndipag) {
		this.ndipag = ndipag;
	}


	public void setMtsbpa(String mtsbpa) {
		this.mtsbpa = mtsbpa;
	}


	public void setMtsbdi(String mtsbdi) {
		this.mtsbdi = mtsbdi;
	}


	public void setMcsegc(String mcsegc) {
		this.mcsegc = mcsegc;
	}


	public void setMotcot(String motcot) {
		this.motcot = motcot;
	}


	public void setOfipgo(String ofipgo) {
		this.ofipgo = ofipgo;
	}


	public void setCcopgo(String ccopgo) {
		this.ccopgo = ccopgo;
	}


	public void setInssal(String inssal) {
		this.inssal = inssal;
	}


	public void setSubmat(String submat) {
		this.submat = submat;
	}


	public void setTpoliq(String tpoliq) {
		this.tpoliq = tpoliq;
	}


	public void setFecpgo(String fecpgo) {
		this.fecpgo = fecpgo;
	}


	public void setMliqpa(String mliqpa) {
		this.mliqpa = mliqpa;
	}


	public void setRimpms(String rimpms) {
		this.rimpms = rimpms;
	}


	public void setCorrelativ(String correlativ) {
		this.correlativ = correlativ;
	}


	public void setLichasfec(String lichasfec) {
		this.lichasfec = lichasfec;
	}


	public void setPagfol(String pagfol) {
		this.pagfol = pagfol;
	}


	public void setPerpag(String perpag) {
		this.perpag = perpag;
	}


	public void setArchiv(String archiv) {
		this.archiv = archiv;
	}


	public int getKeyEstado() {
		return keyEstado;
	}


	public void setKeyEstado(int keyEstado) {
		this.keyEstado = keyEstado;
	}
	
	
	
	
}//end class SIL_VO
