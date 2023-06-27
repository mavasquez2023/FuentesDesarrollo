/**
 * 
 */
package cl.domino.rentaspro;

import lotus.domino.*;

import java.util.*;
import java.io.*;
import java.util.Iterator;
import java.lang.StringBuffer;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hssf.usermodel.*;

import cl.araucana.core.util.Rut;
import cl.recursos.EnviarMail;
import cl.recursos.Formato;
/**
 * @author usist24
 *
 */
public class ValidacionNoRecepcionados extends AgentBase {
	int [][] poslar = new int[18][2] ;
	private final int LARGOMAX=128;
	private Vector mensaje;
	private ParametrosEnvio param=null;
	ParametrosSistema paramSistema=null;
	protected HSSFWorkbook hssfworkbook = null;
	private boolean rowWithData;
	private Database db;
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ValidacionNoRecepcionados valida= new ValidacionNoRecepcionados();
		valida.NotesMain();

	}

	public void NotesMain() {
		int lineanro=0;
		int rutEmpresa=0;
		int conerror_archivo=0;
		try {
			System.out.println(" .....................COMIENZA (Validar Erroneos)   ............");
			//Session s = getSession();
			Session s= NotesFactory.createSession("10.10.10.106", "clillo", "claudio7");
			//AgentContext ac	= s.getAgentContext();
			//PrintWriter pw = getAgentOutput();
			//db 		= ac.getCurrentDatabase();
			db =   s.getDatabase(s.getServerName(), "circular2511.nsf");
			View viewnorecepcionados = db.getView("norecepcionados");
			
			ViewEntryCollection vec = viewnorecepcionados.getAllEntries();
			System.out.println("numero de archivos no recepcionados:" + vec.getCount());
			if ( vec.getCount()>0 ){
				
				mensaje= new Vector();
				//System.out.println("VAB. Nro de archivos s validar= " + vec.getCount());
				paramSistema= new ParametrosSistema(db);
				ViewEntry entry=null;
				List empresas_informadas= new ArrayList();
				for (int i=0; i<vec.getCount(); i++){
					entry	 	= vec.getNthEntry(i+1);
					Document doc= entry.getDocument();
					mensaje= new Vector();
					rutEmpresa=0;
					param= new ParametrosEnvio(doc);
					try{
					if (param.getArchivo()!=null && Mapeo()) {
						Object rutArchivo= param.getRutArchivo();
						if(rutArchivo!=null){
							mensaje.add("<li>Archivo " + param.getNombreArchivo() + " (Empresa RUT: " + rutArchivo.toString() + ") :<BR>");
						}else{
							mensaje.add("<li>Archivo " + param.getNombreArchivo() + ") :<BR>");
						}
						//String rutempArchivo= param.getNombreArchivo().substring(0, param.getNombreArchivo().indexOf("."));
						
						if (param.getFormatoAttach().equalsIgnoreCase("XLS")){
							try{
								System.out.println("VRP, PROCESANDO ARCHIVO: " + param.getNombreArchivo());
								java.io.InputStream objstrm = param.getArchivo().getInputStream();
								POIFSFileSystem fs =  new POIFSFileSystem(objstrm  );
								hssfworkbook = new HSSFWorkbook(fs);
								HSSFWorkbook wb = hssfworkbook;
								HSSFSheet sheet = wb.getSheetAt( 0 );
								MapeoRow mapeo= new MapeoRow();
								Iterator rowIt = sheet.rowIterator();
								rowIt.next();
								while( rowIt.hasNext()) {
									HSSFRow row   = (HSSFRow) rowIt.next();
									StringBuffer linData = new StringBuffer( 1024 );
									Iterator cellIt = row.cellIterator();
									String linea= mapeo.leerExcel(row);
									if(!linea.equals("")){
										if(!linea.equals("titulos")){
											lineanro++;
											System.out.println("VRP, linea N° " + lineanro + " de excel mapeada:" +  linea );
											rutEmpresa= buscarRutEmpresa(linea);
											break;
										}
									}else{
										break;
									}
									
								}
								objstrm.close();
								if(lineanro==0){
									mensaje.add("<ul>Archivo con formato de estructura inválido, no se ha procesado ningún trabajador.</ul>");
									conerror_archivo++;
								}
							}catch (Exception e) {
								mensaje.add("<li>Archivo " + param.getNombreArchivo() + " con formato inválido");
								conerror_archivo++;
								e.printStackTrace();
							}
						}else if (param.getFormatoAttach().equalsIgnoreCase("TXT")){
							//Es archivo TXT

							BufferedReader f1 		= new BufferedReader(param.getArchivo().getReader());
							String linea	= f1.readLine();
							do {
								lineanro++;
								rutEmpresa= buscarRutEmpresa(linea);
								linea= f1.readLine();
								break;
							}  while (linea != null);
							f1.close();
						} else if (param.getFormatoAttach().equalsIgnoreCase("CSV")){
							//Es archivo CSV

							BufferedReader f1 		= new BufferedReader(param.getArchivo().getReader());
							MapeoRow mapeocsv= new MapeoRow();
							String lineacsv	= f1.readLine();
							do {
								lineanro++;
								String lineatxt= mapeocsv.leerCSV(lineacsv);
								if(!lineatxt.equals("titulos")){
									rutEmpresa= buscarRutEmpresa(lineatxt);
									break;
								}
								lineacsv= f1.readLine();			                            	
							}  while (lineacsv != null);
							f1.close();
						} // fin de CSV
						
						//Validar si no está dentro de los que tienen Aviso de Observaciones
						if(rutEmpresa>0){
							View viewerroneos = db.getView("listadoerror");
							ViewEntry entryerror= viewerroneos.getEntryByKey(String.valueOf(rutEmpresa), true);
							System.out.println("Archivo a validar para " + param.getNombreArchivo() + ":" + entryerror);
							if (entryerror==null){
								//Validar si no está dentro de los Validados OK
								View viewvalidados = db.getView("okxrut");
								ViewEntry entryok= viewvalidados.getEntryByKey(String.valueOf(rutEmpresa), true);
								System.out.println("Archivo a validar para " + param.getNombreArchivo() + ":" + entryok);
								if (entryok==null){
									doc.replaceItemValue("norecepcionado", "1");
									doc.replaceItemValue("rutArchivo", String.valueOf(rutEmpresa));
									doc.save(true,true);
									if (!empresas_informadas.contains(String.valueOf(rutEmpresa))){
										//enviarMail(1, param.getMailEncargados(), mensaje, param) ;
										empresas_informadas.add(String.valueOf(rutEmpresa));
									}
								}else{
										doc.remove(true);
								}
							}else{
								doc.remove(true);
							}
						}else{
							doc.replaceItemValue("norecepcionado", "2");
							doc.save(true,true);
						}
							
					} // fin de has file
				} catch(Exception e){
					System.out.println("VAB: CAI EN Archivo: " +  param.getNombreEnvio());
				}
					
				} //fin de For
				
			} //fin de IF (Mapeo())

			System.out.println("VNR: FIN*******");
		} catch(Exception e) {
			System.out.println("VAB: CAI EN MAIN " );
			e.printStackTrace();
		}
	}

	
	
	public boolean Mapeo() {
	try {
			int i;
			//largomax =0;
			for ( i= 1; i < poslar.length; i++ ) {
					poslar[i][0]=0;
					poslar[i][1]=0;						
			}				
			// Leer mapeo para Bonificaciones
				View view	=	db.getView("MAB");
				ViewEntry entry 	= 	view.getEntryByKey("70016160-9", true);
			     if ( entry != null ) {
					Document doc=entry.getDocument();
					for ( i= 1; i < poslar.length; i++ ) { 
						poslar[i][0]=doc.getItemValueInteger("pos_" + i) - 1;
						poslar[i][1]=poslar[i][0] + doc.getItemValueInteger("largo_" + i);
						//System.out.println("MAPEO: posini=" + poslar[i][0] + ", posfin=" + poslar[i][1]);
						/*if ( largomax < poslar[i][0] + doc.getItemValueInteger("largo_" + i) ){
								largomax = poslar[i][0] + doc.getItemValueInteger("largo_" + i) ;
						}*/
					}
					return true;		
				}else{
					System.out.println("VAB.Mapeo, no se encontró archivo de Mapeo");
					return false;
				}			
	}
	catch(Exception e) {
			System.out.println("VAB: CAI EN MAPEO " );
			e.printStackTrace();
			return false;
	}
   }

	public int buscarRutEmpresa(String fila) {
		int rutemp; 
		Rut rutvalido;
		try{
			rutemp 	= Integer.parseInt(fila.substring(poslar[2][0],poslar[2][1]).trim());

			rutvalido= new Rut(rutemp);
			return rutvalido.getNumber();
		}catch(Exception e) {
			System.out.println("Error en validación de campo rutemp");
			return 0;
		}
		
 	 }
 	 
 	   
     public String BlancoCero( String texto) {
			// Si el string contiene blancos retorna el string "0"
			if (texto.trim().equals("")) 
				texto = "0";
			return texto;
    }

	   //Mail de aviso al cliente  x existencia de Observaciones o éxito proceso
    public void enviarMail(int rutEmpresa, Vector mailEncargados, Vector mensaje, ParametrosEnvio paramEnvio) {
    	String subject="", casilla="", mensajeRut="";
		try {			
			EnviarMail mail= new EnviarMail("portal", "portal08");
			StringBuffer body= new  StringBuffer();
			Vector blindCopyTo= new Vector();
			//se rescata mmail de usuarios para incluir en correos ocultos
			String[] emailUsuarios= paramSistema.getEmailUsuarios();
			for (int i = 0; i < emailUsuarios.length; i++) {
				blindCopyTo.add(emailUsuarios[i]);
			}
			if(param.getRutArchivo()!= null){
				mensajeRut= ", asociado a la empresa " + paramEnvio.getRutArchivo().toString() + ", ";
			}
			subject= " Aviso térnimo plazo 18:30 hrs. - Archivo NO Recepcionado: " + paramEnvio.getNombreEnvio() ;
			body.append("Señor Empleador: se recuerda que aún NO se envía correctamente sus antecedentes de Rentas Promedio, favor regularizar a la brevedad. <BR> ");
			body.append("Plazo vence inpostergablemente el lunes 16 de Agosto a las 18:30 hrs.<BR><BR>");
			body.append("El nombre correcto del archivo debe ser el Rut de la Empresa sin digito verificador.<BR><BR>");			
			blindCopyTo.add("ingresospromedioscontacto@laaraucana.cl");
			blindCopyTo.add("ingresospromedios@laaraucana.cl");
			
		  	body.append("Detalle validación:<BR>");
		  	//Se rescatan los primeros 100 mensajes
		  	int tope = mensaje.size();
			if( tope>500 ){
				tope=500;
			}
		  	for ( int	i = 0; 	i< tope;	i++ ) {
				body.append(mensaje.elementAt(i));
			}
		  	 body.append("<br><br>");
		  	 body.append("Si usted ya regularizó su situación favor omitir este correo.");
		  	 body.append("<br><br>");
		  	 body.append("Saluda atte. a Ud. "+"<BR>");
		  	 body.append("La Araucana - Soluciones Sociales.");

		  	mail.mailTo("portal@laaraucana.cl", mailEncargados, null, blindCopyTo , subject, body.toString());
		  	
		  	//System.out.println(".............. EMAIL GENERADO .................... " );
		  	
			}catch(Exception e) {
				System.out.println("CAI EN MAIL  " );
				e.printStackTrace();
			}
 	 }
}
