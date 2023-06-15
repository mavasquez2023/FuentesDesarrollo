package cl.laaraucana.simulacion.utils;

import java.util.Date;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MailUtils mail = new MailUtils();
		
		String ruta = System.getProperty("java.io.tmpdir") +  "fdgdfgdfhdfhdfhdfhdfh.pdf";
		
		try {
		//	mail.sendMailAdjTest("13247428-1", "alexis.mendez@hrlxdeveloper.com",BodyEmailUtils.bodyMensajeSucursal(), ruta);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
