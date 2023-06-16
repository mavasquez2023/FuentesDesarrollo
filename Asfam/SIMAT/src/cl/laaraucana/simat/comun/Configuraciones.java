package cl.laaraucana.simat.comun;

import java.util.ResourceBundle;

public class Configuraciones {
	private String urlWebServiceSAP = "";
	private String userWebServiceSAP = "";
	private String passWebServiceSAP = "";

	public Configuraciones() {
		ResourceBundle mainCfg = ResourceBundle.getBundle("cl.laaraucana.simat.config.configuraciones");
		String ambiente = mainCfg.getString("ambiente");
		ResourceBundle cfg = ResourceBundle.getBundle("cl.laaraucana.simat.config." + ambiente);
		//FALTA EL CAMBIO ENTRE LAS "".
		this.urlWebServiceSAP = cfg.getString(""); //urlsap
		this.userWebServiceSAP = cfg.getString(""); //usrsap
		this.passWebServiceSAP = cfg.getString(""); //clavesap
	}

	public static String getConfig(String key) {
		ResourceBundle mainCfg = ResourceBundle.getBundle("cl.laaraucana.simat.config.configuraciones");
		String ambiente = mainCfg.getString("ambiente");
		ResourceBundle cfg = ResourceBundle.getBundle("cl.laaraucana.simat.config." + ambiente);
		return cfg.getString(key);
	}

	public static String getMainConfig(String key) {
		ResourceBundle mainCfg = ResourceBundle.getBundle("cl.laaraucana.simat.config.configuraciones");
		String ret = mainCfg.getString(key);
		//   	Logger log = Logger.getLogger("class Configuraciones().getMainConfig()");
		//   	log.info(":::Usando para ["+key+"]" + ret);
		return ret;
	}

	public String getPassWebServiceSAP() {
		return passWebServiceSAP;
	}

	public void setPassWebServiceSAP(String passWebServiceSAP) {
		this.passWebServiceSAP = passWebServiceSAP;
	}

	public String getUrlWebServiceSAP() {
		return urlWebServiceSAP;
	}

	public void setUrlWebServiceSAP(String urlWebServiceSAP) {
		this.urlWebServiceSAP = urlWebServiceSAP;
	}

	public String getUserWebServiceSAP() {
		return userWebServiceSAP;
	}

	public void setUserWebServiceSAP(String userWebServiceSAP) {
		this.userWebServiceSAP = userWebServiceSAP;
	}
}
