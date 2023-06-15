package cl.laaraucana.botonpago.web.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;

import cl.laaraucana.botonpago.web.database.dao.ComunasDAO;
import cl.laaraucana.botonpago.web.database.dao.ParametrosDAO;
import cl.laaraucana.botonpago.web.database.dao.SinaDtaDAO;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Afp51f1;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Sinat03;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Sinat04;

public class Constantes {

	/* para instancia de clase */
	private static Constantes c;
	private static ResourceBundle parametros = ResourceBundle.getBundle("parametros");

	public static void kill() throws Exception {
		c = null;
	}

	public static Constantes getInstancia() throws Exception {

		if (c == null) {
			c = new Constantes();

			// asignar los valores desde la BD
			c.cargarParametros();
		} else {
		}
		return c;
	}

	// recarga los parametros desde la base de datos
	/*
	 * public static void recargarParametros() throws Exception { c = new
	 * Constantes(); // Reasignar los valores desde la BD cargarParametros(); }
	 */

	public String APP_COD_ERROR;

	public static List<String> MUST_RELOAD_DEUDA = new ArrayList<String>();

	public String APP_COD_SUCCESS;
	public String APP_COD_VACIO;
	public String BUNDLE_ESTADOCUOTA_AS400;
	public String BUNDLE_LINEACOMERCIAL_SAP;
	public String COD_NOAFI_BPAGC002_MIG;
	public String COD_NOAFI_BPAGC002_NO_MIG;

	public String COD_PRO_SINAT04 = "1";
	public HashMap<String, String> COMUNAS;
	public List<Sinat04> CONDONACION_GASTOS_COBRANZA;

	public List<Sinat03> CONDONACION_GRAVAMENES;
	public String EMAIL_CONTACTO;
	public String ESTADO_ANULADO;

	public String ESTADO_CURSADO;

	public String ESTADO_IMPRESO;
	public String GLOSA_ANULA_DEMONIO;
	public String GLOSA_FOLIO;

	public String GLOSA_PAGO;
	public String ITEM_DOS_CODIGO;

	public String ITEM_DOS_GLOSA;
	public String ITEM_TRES_CODIGO;
	public String ITEM_TRES_GLOSA;
	
	//SCA-01 codigo de concepto para AS400 y SAP
	public String ITEM_UNO_CODIGO_SAP;
	public String ITEM_UNO_CODIGO_AS400;

	public String ITEM_UNO_GLOSA;
	public String KEY_ENCODE;

	public String LDAP_APLICACION;
	public String LDAP_ROL_ADMIN;
	public String LDAP_ROL_DEUDOR;

	public String LDAP_ROL_EJECUTIVO;
	public String MSG_TIPO_ALERTA;
	public String MSG_TIPO_ERROR;

	public String MSG_TIPO_EXITO;
	public String MSG_TIPO_INFO;
	public String NOMBRE_USUARIO_APP;

	public String ORIGEN_AS400;
	public String ORIGEN_SAP;
	public String PORCENTAJE_PAGO_MINIMO;
	public String PREFIJO_CODIGO_BARRA;
	public String PRM_BPAGC002_ACTUALIZA;
	public String PRM_BPAGC002_ESDEUDOR_NOG;

	public String PRM_BPAGC002_ESDEUDOR_SIG;
	public HashMap<String, String> PROVINCIAS;

	public HashMap<String, String> REGIONES;
	public String SAP_HOST;
	public String SAP_INTERNALORG;

	public String SAP_PASSWORD;
	public String SAP_USER;
	public String SAP_USERNAME;
	public String TEL_CONTACTO;
	public String TIEMPO_ANULA_CUPON;
	public String TIEMPO_BUSCA_COMPROBANTE;
	public String TIPO_PAGO_CAJA;

	public String TIPO_PAGO_LINEA;
	public String URL_NOTIFICACION;
	public String URL_RETORNO;
	public String URL_SITIO_WEB;
	public String URL_SPL;
	public String WS_COD_ERROR;

	public String WS_COD_SUCCESS;
	public String WS_COD_VACIO;
	public String IMG_LOGO_REDUCIDO;
	public String MAIL_SESSION;

	private void cargarParametros() throws Exception {
		ParametrosDAO paramDao = new ParametrosDAO();
		HashMap<String, String> map = paramDao.getParametros();

		getRegionesComunas();
		getCondonacionGravamenes();
		getCondonacionGastosCobranza();

		setLDAP_APLICACION(map.get(parametros.getString("ldap_aplicacion")));
		setSAP_USERNAME(Configuraciones.getConfig("servicios.sap.username"));
		setSAP_PASSWORD(Configuraciones.getConfig("servicios.sap.pass"));
		setSAP_INTERNALORG("");
		setSAP_USER("");
		setSAP_HOST("");

		setWS_COD_SUCCESS("3");
		setWS_COD_ERROR("5");
		setWS_COD_VACIO("1");

		setAPP_COD_SUCCESS("succes");
		setAPP_COD_ERROR("error");
		setAPP_COD_VACIO("vacio");

		setKEY_ENCODE(map.get(parametros.getString("key_encode")));

		setURL_RETORNO(map.get(parametros.getString("url_retorno")));
		setURL_NOTIFICACION(map.get(parametros.getString("url_notificacion")));

		setGLOSA_PAGO(map.get(parametros.getString("glosa_pago")));
		setGLOSA_FOLIO(map.get(parametros.getString("glosa_folio")));

		setMSG_TIPO_ERROR("cajaError");
		setMSG_TIPO_ALERTA("alerta");
		setMSG_TIPO_EXITO("exito");
		setMSG_TIPO_INFO("info");

		setBUNDLE_LINEACOMERCIAL_SAP("resources.lineaComercialSAP");
		setBUNDLE_ESTADOCUOTA_AS400("resources.estadoCuotaAS400");

		setLDAP_ROL_ADMIN(map.get(parametros.getString("ldap_rol_admin")));
		setLDAP_ROL_EJECUTIVO(map.get(parametros.getString("ldap_rol_ejecutivo")));
		setLDAP_ROL_DEUDOR(map.get(parametros.getString("ldap_rol_deudor")));

		setPRM_BPAGC002_ACTUALIZA(map.get(parametros.getString("prm_bpagc002_actualiza")));
		setPRM_BPAGC002_ESDEUDOR_SIG(map.get(parametros.getString("prm_bpagc002_esdeudor_sig")));
		setPRM_BPAGC002_ESDEUDOR_NOG(map.get(parametros.getString("prm_bpagc002_esdeudor_nog")));

		setEMAIL_CONTACTO(map.get(parametros.getString("prm_email_contacto")));
		setTEL_CONTACTO(map.get(parametros.getString("prm_tel_contacto")));
		setURL_SITIO_WEB(map.get(parametros.getString("prm_url_sitio_web")));
		setCOD_NOAFI_BPAGC002_MIG(map.get(parametros.getString("cod_noafi_bpagc002_mig")));
		setCOD_NOAFI_BPAGC002_NO_MIG(map.get(parametros.getString("cod_noafi_bpagc002_no_mig")));
		setNOMBRE_USUARIO_APP(map.get(parametros.getString("nombre_usuario_app")));
		setPREFIJO_CODIGO_BARRA(map.get(parametros.getString("prefijo_cod_barra")));
		setPORCENTAJE_PAGO_MINIMO(map.get(parametros.getString("porcentaje_pago_minimo")));
		setURL_SPL(map.get(parametros.getString("url_spl")));

		setITEM_UNO_GLOSA(map.get(parametros.getString("item_uno_glosa")));
		
		//SCA-01
		//setITEM_UNO_CODIGO(map.get(parametros.getString("item_uno_cod_concepto")));
		setITEM_UNO_CODIGO_AS400(map.get(parametros.getString("item_uno_cod_concepto_AS400")));
		setITEM_UNO_CODIGO_SAP(map.get(parametros.getString("item_uno_cod_concepto_SAP")));
		
		setITEM_DOS_GLOSA(map.get(parametros.getString("item_dos_glosa")));
		setITEM_DOS_CODIGO(map.get(parametros.getString("item_dos_cod_concepto")));
		setITEM_TRES_GLOSA(map.get(parametros.getString("item_tres_glosa")));
		setITEM_TRES_CODIGO(map.get(parametros.getString("item_tres_cod_concepto")));

		setCOD_PRO_SINAT04(parametros.getString("cod_pro_sinat04"));
		setTIEMPO_ANULA_CUPON(parametros.getString("tiempo.anula.cupon"));
		setTIEMPO_BUSCA_COMPROBANTE(parametros.getString("tiempo.busca.comprobante"));
		setESTADO_ANULADO(parametros.getString("estado.anulado"));
		setESTADO_IMPRESO(parametros.getString("estado.impreso"));
		setESTADO_CURSADO(parametros.getString("estado.cursado"));

		setTIPO_PAGO_LINEA(parametros.getString("tipo.pago.linea"));
		setTIPO_PAGO_CAJA(parametros.getString("tipo.pago.caja"));
		setGLOSA_ANULA_DEMONIO(parametros.getString("glosa.anula.demonio"));

		setORIGEN_AS400(parametros.getString("origen.as400"));
		setORIGEN_SAP(parametros.getString("origen.sap"));
		setIMG_LOGO_REDUCIDO(parametros.getString("path.logoReducido.img"));
		setMAIL_SESSION(parametros.getString("mail.session"));

	}

	/*
	 * private static void setC(Constantes c) { Constantes.c = c; }
	 */

	private void getCondonacionGastosCobranza() throws Exception {
		try {
			SinaDtaDAO dao = new SinaDtaDAO();
			List<Sinat04> lista = new ArrayList<Sinat04>();
			lista.addAll((List<Sinat04>) dao.getSinat04(COD_PRO_SINAT04));
			setCONDONACION_GASTOS_COBRANZA(lista);
		} catch (Exception e) {
			throw new Exception("Error al obtener condonacion de gastos de cobranza");
		}
	}

	private void getCondonacionGravamenes() throws Exception {
		try {
			SinaDtaDAO dao = new SinaDtaDAO();
			List<Sinat03> lista = new ArrayList<Sinat03>();
			lista.addAll((List<Sinat03>) dao.getSinat03());
			setCONDONACION_GRAVAMENES(lista);
		} catch (Exception e) {
			throw new Exception("Error al obtener condonacion de grvamenes");
		}
	}

	private void getRegionesComunas() throws Exception {
		HashMap<String, String> regiones = new LinkedHashMap<String, String>();
		HashMap<String, String> provincias = new LinkedHashMap<String, String>();
		HashMap<String, String> comunas = new LinkedHashMap<String, String>();
		try {
			ComunasDAO dao = new ComunasDAO();
			List<Afp51f1> lista = new ArrayList<Afp51f1>();
			lista.addAll((List<Afp51f1>) dao.selectAll());
			Collections.sort(lista, new Comparator<Afp51f1>() {
				public int compare(Afp51f1 v1, Afp51f1 v2) {
					return v1.getNomCom().trim().compareTo(v2.getNomCom().trim());
				}
			});

			for (Afp51f1 afp51f1 : lista) {
				String codReg = String.valueOf(afp51f1.getCodReg());
				String codPro = String.valueOf(afp51f1.getCodPro());
				String codCom = codReg + ":" + codPro + ":" + String.valueOf(afp51f1.getCodCom());

				comunas.put(codCom, String.valueOf(afp51f1.getNomCom()).trim());
				regiones.put(codReg, String.valueOf(afp51f1.getNomReg()).trim());
				provincias.put(codPro, String.valueOf(afp51f1.getNomPro()).trim());

			}
			setREGIONES(regiones);
			setCOMUNAS(comunas);
			setPROVINCIAS(provincias);
		} catch (Exception e) {
			throw new Exception("Error al leer archivo de regiones");
		}
	}

	private void setAPP_COD_ERROR(String aPP_COD_ERROR) {
		APP_COD_ERROR = aPP_COD_ERROR;
	}

	private void setAPP_COD_SUCCESS(String aPP_COD_SUCCESS) {
		APP_COD_SUCCESS = aPP_COD_SUCCESS;
	}

	private void setAPP_COD_VACIO(String aPP_COD_VACIO) {
		APP_COD_VACIO = aPP_COD_VACIO;
	}

	private void setBUNDLE_ESTADOCUOTA_AS400(String bUNDLE_ESTADOCUOTA_AS400) {
		BUNDLE_ESTADOCUOTA_AS400 = bUNDLE_ESTADOCUOTA_AS400;
	}

	private void setBUNDLE_LINEACOMERCIAL_SAP(String bUNDLE_LINEACOMERCIAL_SAP) {
		BUNDLE_LINEACOMERCIAL_SAP = bUNDLE_LINEACOMERCIAL_SAP;
	}

	private void setCOD_NOAFI_BPAGC002_MIG(String cOD_NOAFI_BPAGC002_MIG) {
		COD_NOAFI_BPAGC002_MIG = cOD_NOAFI_BPAGC002_MIG;
	}

	private void setCOD_NOAFI_BPAGC002_NO_MIG(String cOD_NOAFI_BPAGC002_NO_MIG) {
		COD_NOAFI_BPAGC002_NO_MIG = cOD_NOAFI_BPAGC002_NO_MIG;
	}

	private void setCOD_PRO_SINAT04(String cOD_PRO_SINAT04) {
		COD_PRO_SINAT04 = cOD_PRO_SINAT04;
	}

	private void setCOMUNAS(HashMap<String, String> cOMUNAS) {
		COMUNAS = cOMUNAS;
	}

	private void setCONDONACION_GASTOS_COBRANZA(List<Sinat04> cONDONACION_GASTOS_COBRANZA) {
		CONDONACION_GASTOS_COBRANZA = cONDONACION_GASTOS_COBRANZA;
	}

	private void setCONDONACION_GRAVAMENES(List<Sinat03> cONDONACION_GRAVAMENES) {
		CONDONACION_GRAVAMENES = cONDONACION_GRAVAMENES;
	}

	private void setEMAIL_CONTACTO(String eMAIL_CONTACTO) {
		EMAIL_CONTACTO = eMAIL_CONTACTO;
	}

	private void setESTADO_ANULADO(String eSTADO_ANULADO) {
		ESTADO_ANULADO = eSTADO_ANULADO;
	}

	private void setESTADO_CURSADO(String eSTADO_CURSADO) {
		ESTADO_CURSADO = eSTADO_CURSADO;
	}

	private void setESTADO_IMPRESO(String eSTADO_IMPRESO) {
		ESTADO_IMPRESO = eSTADO_IMPRESO;
	}

	public void setGLOSA_ANULA_DEMONIO(String gLOSA_ANULA_DEMONIO) {
		GLOSA_ANULA_DEMONIO = gLOSA_ANULA_DEMONIO;
	}

	private void setGLOSA_FOLIO(String gLOSA_FOLIO) {
		GLOSA_FOLIO = gLOSA_FOLIO;
	}

	private void setGLOSA_PAGO(String gLOSA_PAGO) {
		GLOSA_PAGO = gLOSA_PAGO;
	}

	private void setITEM_DOS_CODIGO(String iTEM_DOS_CODIGO) {
		ITEM_DOS_CODIGO = iTEM_DOS_CODIGO;
	}

	private void setITEM_DOS_GLOSA(String iTEM_DOS_GLOSA) {
		ITEM_DOS_GLOSA = iTEM_DOS_GLOSA;
	}

	private void setITEM_TRES_CODIGO(String iTEM_TRES_CODIGO) {
		ITEM_TRES_CODIGO = iTEM_TRES_CODIGO;
	}

	private void setITEM_TRES_GLOSA(String iTEM_TRES_GLOSA) {
		ITEM_TRES_GLOSA = iTEM_TRES_GLOSA;
	}

	private void setITEM_UNO_GLOSA(String iTEM_UNO_GLOSA) {
		ITEM_UNO_GLOSA = iTEM_UNO_GLOSA;
	}

	private void setKEY_ENCODE(String kEY_ENCODE) {
		KEY_ENCODE = kEY_ENCODE;
	}

	private void setLDAP_APLICACION(String lDAP_APLICACION) {
		LDAP_APLICACION = lDAP_APLICACION;
	}

	private void setLDAP_ROL_ADMIN(String lDAP_ROL_ADMIN) {
		LDAP_ROL_ADMIN = lDAP_ROL_ADMIN;
	}

	private void setLDAP_ROL_DEUDOR(String lDAP_ROL_DEUDOR) {
		LDAP_ROL_DEUDOR = lDAP_ROL_DEUDOR;
	}

	private void setLDAP_ROL_EJECUTIVO(String lDAP_ROL_EJECUTIVO) {
		LDAP_ROL_EJECUTIVO = lDAP_ROL_EJECUTIVO;
	}

	private void setMSG_TIPO_ALERTA(String mSG_TIPO_ALERTA) {
		MSG_TIPO_ALERTA = mSG_TIPO_ALERTA;
	}

	private void setMSG_TIPO_ERROR(String mSG_TIPO_ERROR) {
		MSG_TIPO_ERROR = mSG_TIPO_ERROR;
	}

	private void setMSG_TIPO_EXITO(String mSG_TIPO_EXITO) {
		MSG_TIPO_EXITO = mSG_TIPO_EXITO;
	}

	private void setMSG_TIPO_INFO(String mSG_TIPO_INFO) {
		MSG_TIPO_INFO = mSG_TIPO_INFO;
	}

	private void setNOMBRE_USUARIO_APP(String nOMBRE_USUARIO_APP) {
		NOMBRE_USUARIO_APP = nOMBRE_USUARIO_APP;
	}

	public void setORIGEN_AS400(String oRIGEN_AS400) {
		ORIGEN_AS400 = oRIGEN_AS400;
	}

	public void setORIGEN_SAP(String oRIGEN_SAP) {
		ORIGEN_SAP = oRIGEN_SAP;
	}

	private void setPORCENTAJE_PAGO_MINIMO(String pORCENTAJE_PAGO_MINIMO) {
		PORCENTAJE_PAGO_MINIMO = pORCENTAJE_PAGO_MINIMO;
	}

	private void setPREFIJO_CODIGO_BARRA(String pREFIJO_CODIGO_BARRA) {
		PREFIJO_CODIGO_BARRA = pREFIJO_CODIGO_BARRA;
	}

	private void setPRM_BPAGC002_ACTUALIZA(String pRM_BPAGC002_ACTUALIZA) {
		PRM_BPAGC002_ACTUALIZA = pRM_BPAGC002_ACTUALIZA;
	}

	private void setPRM_BPAGC002_ESDEUDOR_NOG(String pRM_BPAGC002_ESDEUDOR_NOG) {
		PRM_BPAGC002_ESDEUDOR_NOG = pRM_BPAGC002_ESDEUDOR_NOG;
	}

	private void setPRM_BPAGC002_ESDEUDOR_SIG(String pRM_BPAGC002_ESDEUDOR_SIG) {
		PRM_BPAGC002_ESDEUDOR_SIG = pRM_BPAGC002_ESDEUDOR_SIG;
	}

	private void setPROVINCIAS(HashMap<String, String> pROVINCIAS) {
		PROVINCIAS = pROVINCIAS;
	}

	private void setREGIONES(HashMap<String, String> rEGIONES) {
		REGIONES = rEGIONES;
	}

	private void setSAP_HOST(String sAP_HOST) {
		SAP_HOST = sAP_HOST;
	}

	private void setSAP_INTERNALORG(String sAP_INTERNALORG) {
		SAP_INTERNALORG = sAP_INTERNALORG;
	}

	private void setSAP_PASSWORD(String sAP_PASSWORD) {
		SAP_PASSWORD = sAP_PASSWORD;
	}

	private void setSAP_USER(String sAP_USER) {
		SAP_USER = sAP_USER;
	}

	private void setSAP_USERNAME(String sAP_USERNAME) {
		SAP_USERNAME = sAP_USERNAME;
	}

	private void setTEL_CONTACTO(String tEL_CONTACTO) {
		TEL_CONTACTO = tEL_CONTACTO;
	}

	private void setTIEMPO_ANULA_CUPON(String tIEMPO_ANULA_CUPON) {
		TIEMPO_ANULA_CUPON = tIEMPO_ANULA_CUPON;
	}

	private void setTIEMPO_BUSCA_COMPROBANTE(String tIEMPO_BUSCA_COMPROBANTE) {
		TIEMPO_BUSCA_COMPROBANTE = tIEMPO_BUSCA_COMPROBANTE;
	}

	private void setTIPO_PAGO_CAJA(String tIPO_PAGO_CAJA) {
		TIPO_PAGO_CAJA = tIPO_PAGO_CAJA;
	}

	private void setTIPO_PAGO_LINEA(String tIPO_PAGO_LINEA) {
		TIPO_PAGO_LINEA = tIPO_PAGO_LINEA;
	}

	private void setURL_NOTIFICACION(String uRL_NOTIFICACION) {
		URL_NOTIFICACION = uRL_NOTIFICACION;
	}

	private void setURL_RETORNO(String uRL_RETORNO) {
		URL_RETORNO = uRL_RETORNO;
	}

	private void setURL_SITIO_WEB(String uRL_SITIO_WEB) {
		URL_SITIO_WEB = uRL_SITIO_WEB;
	}

	private void setURL_SPL(String uRL_SPL) {
		URL_SPL = uRL_SPL;
	}

	private void setWS_COD_ERROR(String wS_COD_ERROR) {
		WS_COD_ERROR = wS_COD_ERROR;
	}

	private void setWS_COD_SUCCESS(String wS_COD_SUCCESS) {
		WS_COD_SUCCESS = wS_COD_SUCCESS;
	}

	private void setWS_COD_VACIO(String wS_COD_VACIO) {
		WS_COD_VACIO = wS_COD_VACIO;
	}

	public void setIMG_LOGO_REDUCIDO(String iMG_LOGO_REDUCIDO) {
		IMG_LOGO_REDUCIDO = iMG_LOGO_REDUCIDO;
	}

	public void setMAIL_SESSION(String mAIL_SESSION) {
		MAIL_SESSION = mAIL_SESSION;
	}

	public void setITEM_UNO_CODIGO_SAP(String iTEM_UNO_CODIGO_SAP) {
		ITEM_UNO_CODIGO_SAP = iTEM_UNO_CODIGO_SAP;
	}

	public void setITEM_UNO_CODIGO_AS400(String iTEM_UNO_CODIGO_AS400) {
		ITEM_UNO_CODIGO_AS400 = iTEM_UNO_CODIGO_AS400;
	}

}
