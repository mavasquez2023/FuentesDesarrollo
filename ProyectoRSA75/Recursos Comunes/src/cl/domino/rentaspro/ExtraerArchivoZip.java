/**
 * 
 */
package cl.domino.rentaspro;

import lotus.domino.*;
import java.util.*;
import java.io.*;
import java.util.zip.*;
/**
 * @author usist24
 *
 */
public class ExtraerArchivoZip extends AgentBase{
	private Database db;
	private boolean flagerror= false;
	private String dirBase;
	
	private Vector informe= new Vector();
	private ParametrosEnvio param;
	private int countAttach=0;
	private int countError=0;
	
	public ExtraerArchivoZip(Database db, String dirBase){
		this.db= db;
		this.dirBase= dirBase;
			
	}
	public Vector extraerZip(Document doc) {
		ZipFile zf;
		String nombrearchivo="";
		String tipoError="";
		try {
			param= new ParametrosEnvio(doc);
			if (param.getArchivo()!=null) {
				if (param.getExtensionArchivo().equalsIgnoreCase("ZIP")) {
					param.getArchivo().extractFile(dirBase + param.getNombreArchivo());
					//creando entrada zip
					zf = new ZipFile(dirBase + param.getNombreArchivo());
					Enumeration e = zf.entries();
					countAttach= zf.size();
					//System.out.println("recuperando entradas del zip");
					for (; e.hasMoreElements();) {
						ZipEntry z = (ZipEntry) e.nextElement();
						nombrearchivo = z.getName();
						tipoError=procesaArchivo(zf, z);
						if (tipoError.equals("")) {
							crearDocumento(doc, nombrearchivo);
						}
						else{
							System.out.println("error en entrada de archivo zip: " + nombrearchivo);
							countError++;
							ListadoErrores(nombrearchivo, tipoError);
						}
					}
					//System.out.println("Archivo deszipeado con exito");
				} else {
					System.out.println("archivo ZIP no posee la extensión correcta.");
					ListadoErrores(param.getNombreArchivo(), "zipext");
				}
				//Se registra cantidad de archivos en Zip
				setCountAttach(countAttach);
				setCountError(countError);
			}
		}catch(ZipException e) {
			System.out.println("Rentas Promedio.EAZ, Error, archivo ZIP no válido>>>>>>>>>>");
			e.printStackTrace();
			ListadoErrores(param.getNombreArchivo(), "zip");
		}
		catch(Exception e) {
			System.out.println("Rentas Promedio.EAZ, Error, archivo ZIP  con informe desconocido>>>>>>>>>>");
			e.printStackTrace();
			ListadoErrores(param.getNombreArchivo(), "zip");
		}

		return informe;
	}

	public String procesaArchivo(ZipFile zf, ZipEntry z){
		int c;
		int rutemp=0;
		try {
			String nombreEntryZip=z.getName();
			rutemp= Integer.parseInt(nombreEntryZip.substring(0, nombreEntryZip.indexOf(".")));
//			System.out.println("archivo a extraer: " + nombreEntryZip);
			String extension = nombreEntryZip.substring(nombreEntryZip.indexOf(".") + 1);
			if (extension.equalsIgnoreCase("txt") || extension.equalsIgnoreCase("csv") || extension.equalsIgnoreCase("xls")){
				InputStream in = zf.getInputStream(z);
				//System.out.println("InputStream asignado");
				FileOutputStream fTarget = new FileOutputStream(dirBase + nombreEntryZip);
				//System.out.println("fTarget asignado");
				while ((c = in.read()) != -1) {
					fTarget.write(c);
				}
				fTarget.close();
				//System.out.println("fTartget cerrado");
				return "";
			}else{
				return "extension";
			}
		}catch(NumberFormatException e){
			System.out.println("JavaException EAZ.procesaArchivo Zip>>>>>>Format Exception, error empresa informe=" + rutemp + "<<<<<<<");
			return "nombre";
		}
		catch(Exception e){
			System.out.println("JavaException EAZ.procesaArchivo Zip>>>>>><<<<<<<");	
			e.printStackTrace();
			return "entradazip";
		}
	}

	public boolean crearDocumento(Document olddoc, String file) {
	    //creando documento con archivo atachado de empresa
	try {
		String formato= file.substring(file.lastIndexOf(".")+1);
	    Document docnew = db.createDocument();
	    olddoc.copyAllItems(docnew, true);
		docnew.removeItem("$FILE");
	    docnew.removeItem("grabado");
	    docnew.replaceItemValue("tipoattach", formato);
	    docnew.replaceItemValue("formato", "ZIP");
	    docnew.replaceItemValue("nombre_envio", param.getNombreArchivo());
	    docnew.replaceItemValue("Form", "cargaarchivos");
	    //String rutaux = rutemp;
	    //rutaux = rutaux.substring(0, rutaux.length() - 1) + "-" + rutaux.substring(rutaux.length() - 1);
	    //docnew.replaceItemValue("rutempresa", rutaux);
	    RichTextItem zip = docnew.createRichTextItem("$TXT");
	    zip.embedObject(
	    EmbeddedObject.EMBED_ATTACHMENT,
	    null,
	    dirBase + file,
	    null);
	    docnew.save(true, true);
	    //System.out.println("documento creado");
	    return true;
	} catch(NotesException ne){
			System.out.println("Error en EAZ.CrearDocumento >>>>>>>>>>>");
			ne.printStackTrace();
			return false;
	    } catch (Exception e) {
	        e.printStackTrace();
		   System.out.println(e.getMessage());
		   return false;
	    }
	}

	public void ListadoErrores(String nomfile, String tipo) {
		String vlista="";
		flagerror= true;
		if (tipo.equals("zip")){
			vlista= "Error en el archivo zip: " + nomfile + "<br>. No se puede descomprimir, intente comprimirlo nuevamente y reenviarlo. <br>";	
		}
		else if (tipo.equals("zipext")){
			vlista= "Error en el formato del archivo zip: " + nomfile + "<br>. Este debe ser con extensión .zip <br>";
		}
		else if (tipo.equals("extension")){
			vlista= "Error en la extensión de uno de los archivos enviados dentro del zip: " + nomfile + "<br>";
		}
		else if (tipo.equals("nombre")){
			vlista= "Error en el nombre de uno de los archivos enviados dentro del zip: " + nomfile + "<br>";
		}
		else if (tipo.equals("entradazip")){
			vlista= "Error interno, uno de los archivos enviados dentro del zip no se pudo descomprimir, envíe este archivo de forma individual: " + nomfile + "<br>";
		}
		else{
			vlista= "Error desconocido, uno de los archivos enviados dentro del zip no se pudo descomprimir: " + nomfile + "<br>";
		}
		informe.addElement(vlista);
	}

	/**
	 * @return el countAttach
	 */
	public int getCountAttach() {
		return countAttach;
	}

	/**
	 * @param countAttach el countAttach a establecer
	 */
	public void setCountAttach(int countAttach) {
		this.countAttach = countAttach;
	}
	/**
	 * @return el countError
	 */
	public int getCountError() {
		return countError;
	}
	/**
	 * @param countError el countError a establecer
	 */
	public void setCountError(int countError) {
		this.countError = countError;
	}

	}
