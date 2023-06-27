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
public class ValidacionRentasPromedio extends AgentBase {
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
		ValidacionRentasPromedio valida= new ValidacionRentasPromedio();
		valida.NotesMain();

	}

	public void NotesMain() {
		int lineanro=0;
		int conerror_envio=0, conerror_archivo=0;
		long id_envio=0, id_envio_old=0;
		try {
			System.out.println(" .....................COMIENZA (ValidaCircular 2511)   ............");
			//Session s = getSession();
			Session s= NotesFactory.createSession("146.83.1.49", "clillo", "claudio7");
			//AgentContext ac	= s.getAgentContext();
			//PrintWriter pw = getAgentOutput();
			//db 		= ac.getCurrentDatabase();
			db =   s.getDatabase(s.getServerName(), "circular2511.nsf");
			View view = db.getView("recibidos");
			ViewEntryCollection vec = view.getAllEntries();
			if ( vec.getCount()>0 && Mapeo()){
				
				mensaje= new Vector();
				//System.out.println("VAB. Nro de archivos s validar= " + vec.getCount());
				paramSistema= new ParametrosSistema(db);
				ViewEntry entry=null;
				for (int i=0; i<vec.getCount(); i++){
					entry	 	= vec.getNthEntry(i+1);
					Document doc= entry.getDocument();
					conerror_archivo=0;
					//se rescata solo el id_envio de siguiente archivo registrar recpeción y enviar mail de envio anterior
					id_envio=new Long(doc.getItemValueString("id_envio")).longValue();
					if(id_envio!=id_envio_old && id_envio_old!=0){
						//pw.println("<p>Detalle validación:<br>" +  mensaje.toString() + "</p>");
						enviarMail(conerror_envio, param.getMailEncargados(), mensaje, param) ;
						mensaje= new Vector();
						conerror_envio=0;
					}
					param= new ParametrosEnvio(doc);
					if (param.getArchivo()!=null) {
						lineanro=0;
						Object rutArchivo= param.getRutArchivo();
						if(rutArchivo!=null){
							mensaje.add("<li>Archivo " + param.getNombreArchivo() + " (Empresa RUT: " + rutArchivo.toString() + ") :<BR>");
						}else{
							mensaje.add("<li>Archivo " + param.getNombreArchivo() + ") :<BR>");
						}
						String rutempArchivo= param.getNombreArchivo().substring(0, param.getNombreArchivo().indexOf("."));
						if(paramSistema.getRut_excluir().indexOf(rutempArchivo)>-1){
							mensaje.add("Empresa NO autorizada. Comuníquese con su ejecutivo para mayor información.<BR>");
							conerror_archivo++;
						}else{
							if (param.getFormatoAttach().equalsIgnoreCase("XLS")){
								try{
									//System.out.println("VRP, PROCESANDO ARCHIVO: " + param.getNombreArchivo());
									java.io.InputStream objstrm = param.getArchivo().getInputStream();
									POIFSFileSystem fs =  new POIFSFileSystem(objstrm  );
									hssfworkbook = new HSSFWorkbook(fs);
									HSSFWorkbook wb = hssfworkbook;
									HSSFSheet sheet = wb.getSheetAt( 0 );
									MapeoRow mapeo= new MapeoRow();
									Iterator rowIt = sheet.rowIterator();
									//rowIt.next();
									while( rowIt.hasNext()) {
										HSSFRow row   = (HSSFRow) rowIt.next();
										StringBuffer linData = new StringBuffer( 1024 );
										Iterator cellIt = row.cellIterator();
										String linea= mapeo.leerExcel(row);
										if(!linea.equals("")){
											if(!linea.equals("titulos")){
												lineanro++;
												//System.out.println("VRP, linea N° " + lineanro + " de excel mapeada:" +  linea );
												conerror_archivo+= validarLinea(linea, lineanro);
											}
										}else{
											break;
										}

									}
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
									conerror_archivo+= validarLinea(linea, lineanro);
									linea= f1.readLine();			                            	
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
										conerror_archivo+= validarLinea(lineatxt, lineanro);
									}
									lineacsv= f1.readLine();			                            	
								}  while (lineacsv != null);
								f1.close();
							} // fin de CSV
						}
					} // fin de has file
					else{
						mensaje.add("Sin archivo adjunto." + "<BR>");
						conerror_archivo++;
					}
					//System.out.println("Cambiando el estado al archivo, conerror_envio=" + conerror_archivo);
					if(conerror_archivo>0){
						conerror_envio++;
					}
					if (conerror_archivo== 0){
						doc.replaceItemValue("estado", "1");
						doc.replaceItemValue("counttra", new Integer(lineanro));
						mensaje.add("N° Trabajadores informados=" + lineanro + "<BR>");
						param.setEstadoProceso('1');
					}else{
						doc.replaceItemValue("estado", "2");
						String mensajedoc=mensaje.toString();
						if(mensajedoc.length()>1000){
							mensajedoc= mensajedoc.substring(0, 1000);
						}
						doc.replaceItemValue("mensaje", mensajedoc);
						param.setEstadoProceso('2');
						//doc.replaceItemValue("mensaje", mensaje.toString());
					}
					id_envio_old= id_envio;
					doc.save(true,true);
				} //fin de For
				//procesar último envío
				enviarMail(conerror_envio, param.getMailEncargados(), mensaje, param) ;
			} //fin de IF (Mapeo())


		} catch(Exception e) {
			System.out.println("VAB: CAI EN MAIN " );
			e.printStackTrace();
		}
	}

	
	public String getdescripcion(int nrocolumna){
		String nombre="";
		try{
			switch (nrocolumna) {
			case 1:
				nombre="Periodo";
				break;
			case 2:
				nombre="RUT Empresa";
				break;
			case 3:
				nombre="DV RUT Empresa";
				break;
			case 4:
				nombre="RUT Afiliado";
				break;
			case 5:
				nombre="DV RUT Afiliado";
				break;
			case 6:
				nombre="Apellido Paterno";
				break;
			case 7:
				nombre="Apellido Materno";
				break;
			case 8:
				nombre="Nombre Afiliado";
				break;
			case 9:
				nombre="Remuneraciones con mismo Empleador";
				break;
			case 10:
				nombre="Otras Remumeraciones con distintos Empleadores";
				break;
			case 11:
				nombre="Renta Trabajador Independiente";
				break;
			case 12:
				nombre="Subsidios";
				break;
			case 13:
				nombre="Pensiones";
				break;
			case 14:
				nombre="Total Ingresos";
				break;
			case 15:
				nombre="Número de meses a que corresponde la información";
				break;
			case 16:
				nombre="Ingreso promedio mensual";
				break;
			case 17:
				nombre="Trabajador con o sin declaración jurada de ingresos";
				break;
			}
			
		}catch(Exception e) {
			System.out.println("CAI EN getdescripcion");
			e.printStackTrace();
		}
		return nombre;
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

	public int validarLinea(String fila, int lineanro) {
		String dvrutemp, dvruttra, nomtra, materno, paterno;
		int periodo, rutemp,  ruttra; 
		double rentaimpemp, rentaimpotraemp, rentatrabind, subsidios, pensiones, totingresos, nmesesinfo, ingpromedio, trabdeclaing;
		int nrovar=0;
		int fin=0;
		Rut rutvalido;
		try {
			if ( fila.trim().length() >1 && (fila.length() == LARGOMAX || !param.getFormatoAttach().equalsIgnoreCase("TXT"))){
				/*while (fila.indexOf(".")>0) {
					fila= fila.replaceAll(".", "");
				}*/
				
				nrovar=1;
				periodo 				= Integer.parseInt(fila.substring(poslar[1][0],poslar[1][1]).trim());
				//System.out.println("periodo " + periodo);

				if(periodo != param.getPeriodoSistema()){
					throw new Exception();
				}

				nrovar=2;
				rutemp 				= Integer.parseInt(fila.substring(poslar[2][0],poslar[2][1]).trim());
				rutvalido= new Rut(rutemp);
				//System.out.println("rutemp " + rutemp);
				if(rutemp != param.getRutArchivo().getNumber()){
					throw new Exception();
				}
				
				nrovar=3;
				dvrutemp			= fila.substring(poslar[3][0],poslar[3][1]).trim().toUpperCase();
				if(rutvalido.getDV()!= dvrutemp.charAt(0)){
					throw new Exception();
				}
				//System.out.println("dvrutemp " + dvrutemp);

				nrovar=4;
				ruttra 				= Integer.parseInt(fila.substring(poslar[4][0],poslar[4][1]).trim());
				rutvalido= new Rut(ruttra);
				//System.out.println("ruttra " + ruttra);


				nrovar=5;
				dvruttra				= fila.substring(poslar[5][0],poslar[5][1]).trim().toUpperCase();
				if(rutvalido.getDV()!= dvruttra.charAt(0)){
					throw new Exception();
				}
				//System.out.println("dvruttra " + dvruttra);


				nrovar=6;
				paterno				=	fila.substring(poslar[6][0],poslar[6][1]).trim();
				if(paterno.trim().equals("")){
					throw new Exception();
				}
				//System.out.println("paterno " + paterno);

				nrovar=7;
				materno				=	fila.substring(poslar[7][0],poslar[7][1]).trim();
				if(materno.trim().equals("")){
					throw new Exception();
				}
				//System.out.println("materno " + materno);

				nrovar=8;
				nomtra				=	fila.substring(poslar[8][0],poslar[8][1]).trim();
				if(nomtra.trim().equals("")){
					throw new Exception();
				}
				//System.out.println("nomtra " + nomtra);

				//Remuneraciones con mismo Empleador
				nrovar=9;
				rentaimpemp			=	Formato.cambiaStr2double(BlancoCero(fila.substring(poslar[9][0],poslar[9][1])));
				//System.out.println("rentaimpemp " + rentaimpemp);

				//Otras Remumeraciones con distintos Empleadores
				nrovar=10;
				rentaimpotraemp	=	Formato.cambiaStr2double(BlancoCero(fila.substring(poslar[10][0],poslar[10][1])));
				//System.out.println("rentaimpotraemp " + rentaimpotraemp);

				//Renta Trabajador Independiente
				nrovar=11;
				rentatrabind			=	Formato.cambiaStr2double(BlancoCero(fila.substring(poslar[11][0],poslar[11][1])));
				//System.out.println("rentatrabind " + rentatrabind);

				//Subsidios
				nrovar=12;
				subsidios			=	Formato.cambiaStr2double(BlancoCero(fila.substring(poslar[12][0],poslar[12][1])));
				//System.out.println("subsidios " + subsidios);

				//Pensiones
				nrovar=13;
				pensiones			=	Formato.cambiaStr2double(BlancoCero(fila.substring(poslar[13][0],poslar[13][1])));
				//System.out.println("pensiones " + pensiones);

				//Total Ingresos
				nrovar=14;
				totingresos			=	Formato.cambiaStr2double(BlancoCero(fila.substring(poslar[14][0],poslar[14][1])));
				//System.out.println("totingresos " + totingresos);

				if(totingresos == 0){
					throw new Exception();
				}						

				//Número de meses a que corresponde la información
				nrovar=15;
				nmesesinfo			=	Formato.cambiaStr2double(BlancoCero(fila.substring(poslar[15][0],poslar[15][1])));
				//System.out.println("nmesesinfo " + nmesesinfo);

				if(nmesesinfo == 0){
					throw new Exception();
				}

				//Ingreso promedio mensual
				nrovar=16;
				ingpromedio			=	Formato.cambiaStr2double(BlancoCero(fila.substring(poslar[16][0],poslar[16][1])));
				//System.out.println("ingpromedio " + ingpromedio);
				
				if(ingpromedio == 0){
					throw new Exception();
				}


				//Trabajador con o sin declaración jurada de ingresos
				nrovar=17;
				trabdeclaing			=	Formato.cambiaStr2double(BlancoCero(fila.substring(poslar[17][0],poslar[17][1])));
				//System.out.println("trabdeclaing " + trabdeclaing);
				if(trabdeclaing == 0){
					throw new Exception();
				}
				
			}else if ( fila.length() != LARGOMAX ){
				mensaje.add("<ul>Largo del registro incorrecto .. Linea " + lineanro + " informa "+ fila.length() +" caracteres y largo correcto debe ser de " + LARGOMAX + "</ul>");	
				//System.out.println(mensaje);
				fin=1;
			}

		}
		catch(NumberFormatException n) {
			System.out.println("Error en validación numérica de campo "+ nrovar);
			fin = 1;
		}		
		catch(Exception e) {
			System.out.println("Error en validación de campo "+ nrovar);
			fin = 1;
		}
		finally{
			if (fin==1 && (fila.length() == LARGOMAX || !param.getFormatoAttach().equalsIgnoreCase("TXT"))){
				mensaje.add("<ul>En línea N°  " + lineanro + ", el campo '" + getdescripcion(nrovar) + "' contiene un valor inválido==> " + fila.substring(poslar[nrovar][0],poslar[nrovar][1]) + "</ul>");
			}
		}
		return fin;
 	 }
 	 
 	   
     public String BlancoCero( String texto) {
			// Si el string contiene blancos retorna el string "0"
			if (texto.trim().equals("")) 
				texto = "0";
			return texto;
    }

	   //Mail de aviso al cliente  x existencia de Observaciones o éxito proceso
    public void enviarMail(int conerror_envio, Vector mailEncargados, Vector mensaje, ParametrosEnvio paramEnvio) {
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
			if(conerror_envio==0){
				subject= " Validación exitosa en archivo " + paramEnvio.getNombreEnvio() ;
				body.append("Señor Empleador: su archivo " + mensajeRut + " ha sido validado exitosamente. <BR>");
				blindCopyTo.add("ingresospromedios@laaraucana.cl");
			}else{
				subject= " Aviso de Observaciones en archivo " + paramEnvio.getNombreEnvio() ;
				if(param.getFormatoEnvio().equalsIgnoreCase("ZIP")){
					body.append("Señor Empleador: su archivo <b>presenta " + conerror_envio + " empresa(s) con observaciones</b> <BR>");
					body.append("Consecuente con lo anterior, es necesario corregir y reenviar archivos erroneos dentro de los plazos establecidos.<BR><BR>");
				}else{
					body.append("Señor Empleador: su archivo <b>no pudo ser procesado</b>. <BR>");
					body.append("Consecuente con lo anterior, es necesario corregir y reenviar dentro de los plazos establecidos.<BR><BR>");
				}
				blindCopyTo.add("ingresospromedioscontacto@laaraucana.cl");
				blindCopyTo.add("ingresospromedios@laaraucana.cl");
			}
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
