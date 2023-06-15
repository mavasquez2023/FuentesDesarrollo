package cl.jfactory.planillas.service.util;

import java.util.ArrayList;
import java.util.HashMap;

import cl.liv.comun.utiles.MiHashMap;
import cl.liv.comun.utiles.PropertiesUtil;
import cl.liv.comun.utiles.UtilesComunes;
import cl.liv.mail.impl.MailImpl;

public class MailUtil {

	private String mailer = "mailer_wf";
	private String from = null;
	private String dataAdicional = null;
	private String subject = null;
	private String textoCuerpo = null;
	private ArrayList attrs = new ArrayList();
	private ArrayList imagenes = new ArrayList();
	private String tipoNomina = "trabajadores";

	public void init(MiHashMap data, MiHashMap envio, ArrayList attrs) {
		this.from = PropertiesUtil.configuracionesMail.getString("mail.smtp.from");
		this.dataAdicional = envio.get("data_adicional").toString();
		if (data != null) {
			if (data.get("TIPO_NOMINA") != null) {
				if (data.get("TIPO_NOMINA").toString().equals("4")) {
					this.tipoNomina = "pensionados";
				} else {
					this.tipoNomina = "trabajadores";
				}
			}
		}
		this.subject = UtilesComunes
				.reemplazarVariables(PropertiesUtil.configuracionesMail.getString("mail.config.envio.nomina.subject"));
		this.attrs = attrs;
		this.imagenes = new ArrayList();
		this.textoCuerpo = Utiles.obtenerTextoMailNominas(data, this.tipoNomina);

		try {
			String[] imagenes = PropertiesUtil.configuracionesMail.getString("mail.config.envio.nomina.images")
					.split(";;");
			if(imagenes != null){
				for(int i=0; i < imagenes.length; i++){
					if(imagenes[i].length() >0 && imagenes[i].split("::").length == 2){
						String[] img = imagenes[i].split("::");
						HashMap item = new HashMap();
						item.put("ruta_file", img[1]);
						item.put("id_image", img[0]);
						this.imagenes.add(item);
					}
				}
			}
		} catch (Exception e) {

		}

	}

	public void send() {
		MailImpl.enviarMail("mailer_wf", this.from, this.dataAdicional, this.subject, this.textoCuerpo, this.attrs,
				this.imagenes, false);
	}

}
