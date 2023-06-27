/**
 * 
 */
package cl.domino.rentaspro;

/**
 * @author usist24
 *
 */
import lotus.domino.*;

import java.util.*;

public class EnviarMailDomino{

	private Database dbmail = null;
	private Database db = null;
	private Document docmail = null;
	private Session session = null;
	boolean mailok= true;
	
public EnviarMailDomino() {
}	
	
public boolean mailTo(String from, Vector sendto, Vector copyto, Vector blindcopyto, String subject, Vector body) {
	try {
		Vector recipients= new Vector();
		session= NotesFactory.createSession("146.83.1.49", "clillo", "claudio7");
		if (session!=null){
			dbmail  =   session.getDatabase(session.getServerName(), "mail.box");
			if (dbmail.isOpen()){
				docmail = dbmail.createDocument();
				docmail.replaceItemValue("form","memo");
				docmail.replaceItemValue("principal",from);
				docmail.replaceItemValue("SendTo", sendto);
				for (int i=0; i<sendto.size(); i++){
					recipients.addElement(sendto.elementAt(i));
				}
				System.out.println("SendTo= " + sendto);
				if (copyto!=null && !copyto.isEmpty()){
					System.out.println("CopyTo= " + copyto);
					docmail.replaceItemValue("CopyTo", copyto);
					for (int i=0; i<copyto.size(); i++){
						recipients.addElement(copyto.elementAt(i).toString());
					}
				}
				if (blindcopyto!=null && !blindcopyto.isEmpty()){
					System.out.println("BlindCopyTo= " + blindcopyto);
					docmail.replaceItemValue("BlindCopyTo", blindcopyto);
					int size= blindcopyto.size();
					for (int i=0; i<size; i++){
						recipients.addElement(blindcopyto.elementAt(i).toString());
					}
				}
				 
				System.out.println("Recipients= " + sendto);
				docmail.replaceItemValue("Recipients",recipients);
				docmail.replaceItemValue("Subject", subject);	
				docmail.replaceItemValue("body", body);	
				docmail.replaceItemValue("from", from);	
				docmail.save(true,true);   
			}
			else{
				System.out.println("Base de datos de MAIL.BOX no ha sido abierta,MAIL NO ENVIADO>");
				mailok= false;
			}
			//session.recycle();
			//session= null;
		}
	}
	catch(Exception e) {
		System.out.println("CAI EN LIBRERIA ENVIARMAIL, metodo MAILTO  " );
		e.printStackTrace();
		mailok= false;
	}
	return mailok;
}
	
public boolean mailTo(String from, String sendto, String copyto, String subject, String body) {
	try {
		session = NotesFactory.createSession();
		if (session!=null){
			dbmail  =   session.getDatabase(session.getServerName(), "mail.box");
			if (dbmail.isOpen()){
				Vector recipients= new Vector();
				docmail = dbmail.createDocument();
				docmail.replaceItemValue("form","memo");
				docmail.replaceItemValue("principal",from);
				docmail.replaceItemValue("SendTo", sendto);
				if (!copyto.equals("")){
					docmail.replaceItemValue("CopyTo", copyto);
					recipients.addElement(sendto);
					recipients.addElement(copyto);
				}
				docmail.replaceItemValue("Recipients",recipients);
				docmail.replaceItemValue("Subject", subject);	
				docmail.replaceItemValue("body", body);	
				docmail.replaceItemValue("from", from);	
				docmail.save(true,true);   
			}
			else{
				System.out.println("Base de datos de MAIL.BOX no ha sido abierta,MAIL NO ENVIADO>");
				mailok= false;
			}
			session.recycle();
			session= null;
		}	
	}
	catch(Exception e) {
		System.out.println("CAI EN LIBRERIA ENVIARMAIL, metodo MAILTO  " );
		e.printStackTrace();
		mailok= false;
	}
	return mailok;
}

}
