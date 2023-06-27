/**
 * 
 */
package cl.domino.rentaspro;

import cl.recursos.Formato;
import lotus.domino.*;

/**
 * @author usist24
 *
 */
public class ParametrosSistema {
private String sistema="";
private String usuario;
private String password;
private String periodo;
private String rut_excluir;
private String[] emailUsuarios;

public ParametrosSistema(Database db){
	View view;
	ViewEntry entry;
	Document doc;
	try {
			if (db.isOpen()){
				view 	= 	db.getView("fechacierre");
				entry	= 	view.getEntryByKey("EP", true);
				if (	entry != null	) {
					doc= entry.getDocument();
					sistema= doc.getItemValueString("sistema");
					usuario= doc.getItemValueString("usuario");
					password= doc.getItemValueString("contrasena");
					periodo= doc.getItemValueString("codigoproceso");
					rut_excluir= doc.getItemValueString("rut_excluir");
					if (rut_excluir==null){
						rut_excluir="";
					}
					String emails= doc.getItemValueString("emailusuarios");
					if (emails==null){
						emails="";
					}else{
						emailUsuarios=Formato.split(emails, ",");
					}
				}else{
					System.out.println("paramAS400(), doc. de parámetro no encontrado");
				}
				entry.recycle();
				view.recycle();
			}
		
	}catch(Exception e) {
		System.out.println("CAI EN ParametrosSistema()  " );
		e.printStackTrace();
	}
}
/**
 * @return el password
 */
public String getPassword() {
	return password;
}
/**
 * @param password el password a establecer
 */
public void setPassword(String password) {
	this.password = password;
}
/**
 * @return el periodo
 */
public String getPeriodo() {
	return periodo;
}
/**
 * @param periodo el periodo a establecer
 */
public void setPeriodo(String periodo) {
	this.periodo = periodo;
}
/**
 * @return el sistema
 */
public String getSistema() {
	return sistema;
}
/**
 * @param sistema el sistema a establecer
 */
public void setSistema(String sistema) {
	this.sistema = sistema;
}
/**
 * @return el usuario
 */
public String getUsuario() {
	return usuario;
}
/**
 * @param usuario el usuario a establecer
 */
public void setUsuario(String usuario) {
	this.usuario = usuario;
}
/**
 * @return el rut_excliur
 */
public String getRut_excluir() {
	return rut_excluir;
}
/**
 * @param rut_excliur el rut_excliur a establecer
 */
public void setRut_excliur(String rut_excluir) {
	this.rut_excluir = rut_excluir;
}
/**
 * @return el emailUsuarios
 */
public String[] getEmailUsuarios() {
	return emailUsuarios;
}
/**
 * @param emailUsuarios el emailUsuarios a establecer
 */
public void setEmailUsuarios(String[] emailUsuarios) {
	this.emailUsuarios = emailUsuarios;
}


}
