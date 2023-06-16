package cl.araucana.tupla2.struts.bussiness.TO;

import org.apache.struts.action.ActionForm;

public class DatosValidacionTO extends ActionForm {

	public DatosValidacionTO() {

	}

	private String ID;
	private String AFSIRUBE;
	private String DVBENE;
	private String AFSIFEAU;
	private String AFFEEXTC;
	private String CODCAUSANTE;
	private String TRAMO;
	private String MONTO;
	private String RUTBENEFICIARIO;
	private String DVBENEFICIARIO;
	private String EXISTENCIASIAGF;
	private String IDTUPLA;
	private String CANTIDADTRAMO;
	private String ESTADOTRAMO;
	private String ESTADOCAUSANTE;
	private String ESTADOFINAL;
	private String ESTADOBENEFICIARIO;
	private String CODIGOENTIDAD;

	public String getAFFEEXTC() {
		return AFFEEXTC;
	}

	public void setAFFEEXTC(String affeextc) {
		AFFEEXTC = affeextc;
	}

	public String getAFSIFEAU() {
		return AFSIFEAU;
	}

	public void setAFSIFEAU(String afsifeau) {
		AFSIFEAU = afsifeau;
	}

	public String getAFSIRUBE() {
		return AFSIRUBE;
	}

	public void setAFSIRUBE(String afsirube) {
		AFSIRUBE = afsirube;
	}

	public String getCANTIDADTRAMO() {
		return CANTIDADTRAMO;
	}

	public void setCANTIDADTRAMO(String cantidadtramo) {
		CANTIDADTRAMO = cantidadtramo;
	}

	public String getCODCAUSANTE() {
		return CODCAUSANTE;
	}

	public void setCODCAUSANTE(String codcausante) {
		CODCAUSANTE = codcausante;
	}

	public String getDVBENE() {
		return DVBENE;
	}

	public void setDVBENE(String dvbene) {
		DVBENE = dvbene;
	}

	public String getDVBENEFICIARIO() {
		return DVBENEFICIARIO;
	}

	public void setDVBENEFICIARIO(String dvbeneficiario) {
		DVBENEFICIARIO = dvbeneficiario;
	}

	public String getESTADOCAUSANTE() {
		return ESTADOCAUSANTE;
	}

	public void setESTADOCAUSANTE(String estadocausante) {
		ESTADOCAUSANTE = estadocausante;
	}

	public String getESTADOFINAL() {
		return ESTADOFINAL;
	}

	public void setESTADOFINAL(String estadofinal) {
		ESTADOFINAL = estadofinal;
	}

	public String getESTADOTRAMO() {
		return ESTADOTRAMO;
	}

	public void setESTADOTRAMO(String estadotramo) {
		ESTADOTRAMO = estadotramo;
	}

	public String getEXISTENCIASIAGF() {
		return EXISTENCIASIAGF;
	}

	public void setEXISTENCIASIAGF(String existenciasiagf) {
		EXISTENCIASIAGF = existenciasiagf;
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public String getIDTUPLA() {
		return IDTUPLA;
	}

	public void setIDTUPLA(String idtupla) {
		IDTUPLA = idtupla;
	}

	public String getMONTO() {
		return MONTO;
	}

	public void setMONTO(String monto) {
		MONTO = monto;
	}

	public String getRUTBENEFICIARIO() {
		return RUTBENEFICIARIO;
	}

	public void setRUTBENEFICIARIO(String rutbeneficiario) {
		RUTBENEFICIARIO = rutbeneficiario;
	}

	public String getTRAMO() {
		return TRAMO;
	}

	public void setTRAMO(String tramo) {
		TRAMO = tramo;
	}

	public String getCODIGOENTIDAD() {
		return CODIGOENTIDAD;
	}

	public void setCODIGOENTIDAD(String codigoentidad) {
		CODIGOENTIDAD = codigoentidad;
	}

	public String getESTADOBENEFICIARIO() {
		return ESTADOBENEFICIARIO;
	}

	public void setESTADOBENEFICIARIO(String estadobeneficiario) {
		ESTADOBENEFICIARIO = estadobeneficiario;
	}

}
