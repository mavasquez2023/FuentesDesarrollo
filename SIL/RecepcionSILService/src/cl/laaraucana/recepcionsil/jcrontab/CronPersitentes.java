package cl.laaraucana.recepcionsil.jcrontab;


import org.apache.log4j.Logger;

import cl.laaraucana.recepcionsil.manager.RestorePersistent;


public class CronPersitentes {
	private static Logger log = Logger.getLogger(CronPersitentes.class);
	//static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/mail");
	public static void main(String[] args) throws Exception {
		try {
			RestorePersistent restore= new RestorePersistent();
			restore.processIlfPersist();

		} catch (Exception e) {
			log.error("Problemas en ejecución proceso CronPersitentes, mensaje:", e);
		}
	}
}