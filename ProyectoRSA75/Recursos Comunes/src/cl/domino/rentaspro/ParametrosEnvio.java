/**
 * 
 */
package cl.domino.rentaspro;

import java.util.Enumeration;
import java.util.Vector;
import lotus.domino.Document;
import lotus.domino.EmbeddedObject;
import lotus.domino.Item;
import lotus.domino.NotesException;
import cl.araucana.core.util.Rut;

/**
 * @author usist24
 *
 */
public class ParametrosEnvio {
private Vector mailEncargados;
private int periodoSistema;
private String formatoAttach;
private String formatoEnvio="";
private Rut rutEmpresa;
private Rut rutUsuario;
private String nombreUsuario;
private EmbeddedObject archivo=null;
private String nombreArchivo="";
private String extensionArchivo="";
private String nombreEnvio="";
private char estadoProceso='0';
private int cantidadArchivos=1;
private long id_envio;
private Rut rutArchivo=null;


public ParametrosEnvio(Document doc){
	
	try {
		mailEncargados= new Vector();
		periodoSistema= Integer.parseInt(doc.getItemValueString("periodo"));
		id_envio= new Long(doc.getItemValueString("id_envio")).longValue();
		String rut_temp= doc.getItemValueString("rutempresa");
		rut_temp= rut_temp.substring(0, rut_temp.indexOf("-"));
		rutEmpresa= new Rut(rut_temp);
		rut_temp= doc.getItemValueString("rutusuario");
		rut_temp= rut_temp.substring(0, rut_temp.indexOf("-"));
		rutUsuario= new Rut(rut_temp);
		nombreUsuario= doc.getItemValueString("usuario").trim();
		String emailEncargado1= doc.getItemValueString("email");
		String emailEncargado2= doc.getItemValueString("email_2");
		String emailEncargado3= doc.getItemValueString("email_3");
		mailEncargados.add(emailEncargado1.trim());
		if (emailEncargado2!=null && !emailEncargado2.trim().equals("")){
			mailEncargados.add(emailEncargado2.trim());
		}
		if (emailEncargado3!=null && !emailEncargado3.trim().equals("")){
			mailEncargados.add(emailEncargado3.trim());
		}
		if (doc.hasItem("$FILE")) {
        		Enumeration items= doc.getItems().elements();
			while (items.hasMoreElements() ) {
	               	Item item = (Item) items.nextElement();
       	    		if (item.getType()== Item.ATTACHMENT) {
       	    			archivo 	= doc.getAttachment(item.getValueString());
       	    			nombreArchivo= archivo.getSource();
       	    			int pos=nombreArchivo.lastIndexOf(".");
       	    			if(pos>-1){
       	    				try {
								int rut=Integer.parseInt(nombreArchivo.substring(0, pos));
								rutArchivo= new Rut(rut);
								
							} catch (NumberFormatException e) {
								
							}
       	    				extensionArchivo= nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1).toUpperCase();
       	    			}
       	    			formatoAttach= extensionArchivo;
        		     	break;
       	    		}
			}
			nombreEnvio= doc.getItemValueString("nombre_envio");
			if(nombreEnvio==null || nombreEnvio.equals("")){
				nombreEnvio= nombreArchivo;
			}
			if(formatoEnvio==null || formatoEnvio.equals("")){
				formatoEnvio= formatoAttach;
			}
		}
	} catch (NotesException e) {
		// TODO Bloque catch generado automáticamente
		e.printStackTrace();
	}
}

/**
 * @return el mailEncargados
 */
public Vector getMailEncargados() {
	return mailEncargados;
}
/**
 * @param mailEncargados el mailEncargados a establecer
 */
public void setMailEncargados(Vector mailEncargados) {
	this.mailEncargados = mailEncargados;
}
/**
 * @return el nombreUsuario
 */
public String getNombreUsuario() {
	return nombreUsuario;
}
/**
 * @param nombreUsuario el nombreUsuario a establecer
 */
public void setNombreUsuario(String nombreUsuario) {
	this.nombreUsuario = nombreUsuario;
}
/**
 * @return el periodoSistema
 */
public int getPeriodoSistema() {
	return periodoSistema;
}
/**
 * @param periodoSistema el periodoSistema a establecer
 */
public void setPeriodoSistema(int periodoSistema) {
	this.periodoSistema = periodoSistema;
}
/**
 * @return el rutEmpresa
 */
public Rut getRutEmpresa() {
	return rutEmpresa;
}
/**
 * @param rutEmpresa el rutEmpresa a establecer
 */
public void setRutEmpresa(Rut rutEmpresa) {
	this.rutEmpresa = rutEmpresa;
}
/**
 * @return el rutUsuario
 */
public Rut getRutUsuario() {
	return rutUsuario;
}
/**
 * @param rutUsuario el rutUsuario a establecer
 */
public void setRutUsuario(Rut rutUsuario) {
	this.rutUsuario = rutUsuario;
}

/**
 * @return el obj
 */
public EmbeddedObject getArchivo() {
	return archivo;
}

/**
 * @param obj el obj a establecer
 */
public void setObj(EmbeddedObject archivo) {
	this.archivo = archivo;
}

/**
 * @return el nombreArchivo
 */
public String getNombreArchivo() {
	return nombreArchivo;
}

/**
 * @param nombreArchivo el nombreArchivo a establecer
 */
public void setNombreArchivo(String nombreArchivo) {
	this.nombreArchivo = nombreArchivo;
}

/**
 * @return el estadoProceso
 */
public char getEstadoProceso() {
	return estadoProceso;
}

/**
 * @param estadoProceso el estadoProceso a establecer
 */
public void setEstadoProceso(char estadoProceso) {
	this.estadoProceso = estadoProceso;
}

/**
 * @return el cantidadArchivos
 */
public int getCantidadArchivos() {
	return cantidadArchivos;
}

/**
 * @param cantidadArchivos el cantidadArchivos a establecer
 */
public void setCantidadArchivos(int cantidadArchivos) {
	this.cantidadArchivos = cantidadArchivos;
}

/**
 * @return el nombreEnvio
 */
public String getNombreEnvio() {
	return nombreEnvio;
}

/**
 * @param nombreEnvio el nombreEnvio a establecer
 */
public void setNombreEnvio(String nombreEnvio) {
	this.nombreEnvio = nombreEnvio;
}

/**
 * @return el id_envio
 */
public long getId_envio() {
	return id_envio;
}

/**
 * @param id_envio el id_envio a establecer
 */
public void setId_envio(long id_envio) {
	this.id_envio = id_envio;
}

/**
 * @return el formatoAttach
 */
public String getFormatoAttach() {
	return formatoAttach;
}

/**
 * @param formatoAttach el formatoAttach a establecer
 */
public void setFormatoAttach(String formatoAttach) {
	this.formatoAttach = formatoAttach;
}

/**
 * @return el formatoEnvio
 */
public String getFormatoEnvio() {
	return formatoEnvio;
}

/**
 * @param formatoEnvio el formatoEnvio a establecer
 */
public void setFormatoEnvio(String formatoEnvio) {
	this.formatoEnvio = formatoEnvio;
}

/**
 * @return el extensionArchivo
 */
public String getExtensionArchivo() {
	return extensionArchivo;
}

/**
 * @param extensionArchivo el extensionArchivo a establecer
 */
public void setExtensionArchivo(String extensionArchivo) {
	this.extensionArchivo = extensionArchivo;
}

/**
 * @return el rutArchivo
 */
public Rut getRutArchivo() {
	return rutArchivo;
}

/**
 * @param rutArchivo el rutArchivo a establecer
 */
public void setRutArchivo(Rut rutArchivo) {
	this.rutArchivo = rutArchivo;
}


}
