package cl.domino.rentaspro;

import lotus.domino.*;
import java.util.*;
import java.io.*;
import java.util.Iterator;
import java.lang.StringBuffer;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hssf.usermodel.*;
import cl.recursos.Formato;

public class ProcesarRentasPromedio extends AgentBase{
	int [][] poslar = new int[18][2] ;
	private Vector mensaje;
	private ParametrosEnvio param=null;
	protected HSSFWorkbook hssfworkbook = null;
	private boolean rowWithData;
	private Database db;
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ProcesarRentasPromedio valida= new ProcesarRentasPromedio();
		valida.NotesMain();

	}

	public void NotesMain() {
		int lineanro=0;
		int conerror_archivo=0;
		try {
			System.out.println(" .....................COMIENZA (ProcesarCircular 2511)   ............");
			//Session s = getSession();
			Session s= NotesFactory.createSession("10.10.10.106", "clillo", "claudio7");
			//AgentContext ac	= s.getAgentContext();
			//PrintWriter pw = getAgentOutput();
			//db 		= ac.getCurrentDatabase();
			db =   s.getDatabase(s.getServerName(), "circular2511.nsf");
			View view = db.getView("validados");
			ViewEntryCollection vec = view.getAllEntries();
			System.out.println("Numero de archivos a cargar AS400:" + vec.getCount());
			if ( vec.getCount()>0 && Mapeo()){
				
				//System.out.println("VAB. Nro de archivos a procesar= " + vec.getCount());
				//Se abre conexión a Db2 para registrar recepción
				ParametrosSistema paramSistema= new ParametrosSistema(db);
				RentasPromedioDAO rentasDAO= new RentasPromedioDAO(paramSistema.getSistema(), paramSistema.getUsuario(), paramSistema.getPassword());
				rentasDAO.setAutoCommit(false);
				ViewEntry entry=null;
				
				for (int i=0; i<vec.getCount(); i++){
					entry	 	= vec.getNthEntry(i+1);
					Document doc= entry.getDocument();
					conerror_archivo=0;
					mensaje= new Vector();
					//se rescata solo el id_envio de siguiente archivo registrar recpeción y enviar mail de envio anterior
					param= new ParametrosEnvio(doc);
					if (param.getArchivo()!=null) {
						lineanro=0;
						mensaje.add("<li>Archivo " + param.getNombreArchivo() + " :<BR>");
						String rutempArchivo= param.getNombreArchivo().substring(0, param.getNombreArchivo().indexOf("."));

						//rentasDAO.deleteEmpresa(Integer.parseInt(paramSistema.getPeriodo()), Integer.parseInt(rutempArchivo));
						//rentasDAO.commit();
							if (param.getFormatoAttach().equalsIgnoreCase("XLS")){
								InputStream objstrm=null;
								try{
									System.out.println("VRP, PROCESANDO ARCHIVO: " + param.getNombreArchivo());
									objstrm = param.getArchivo().getInputStream();
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
										if(linea.equals("") && lineanro==0){
											linea="titulos";
										}
										int finalizar=1;
										if(!linea.equals("") && !linea.equals("error")){
											if(!linea.equals("titulos")){
												lineanro++;
												//System.out.println("VRP, linea N° " + lineanro + " de excel mapeada:" +  linea );
												AfiliadoRPTO afiliado= getAfiliadoObject(linea);
												if (afiliado!=null){
													rentasDAO.insertaTrabajador(afiliado);
												}else{
													conerror_archivo++;
													break;
												}
											}
										}else{
											if (linea.equals("error")){
												System.out.println("VRP, fin de archivo por error inesperado");
												conerror_archivo++;
											}
											//fin de archivo excel
											break;
										}
									}
									if(lineanro==0){
										System.out.println("VRP, Archivo con formato de estructura inválido, no se ha procesado ningún trabajador.");
										conerror_archivo++;
									}
									
								}catch (Exception e) {
									mensaje.add("<li>Archivo " + param.getNombreArchivo() + " no se pudo guardar" + e.getMessage());
									conerror_archivo++;
									e.printStackTrace();
								}
								objstrm.close();
							}else if (param.getFormatoAttach().equalsIgnoreCase("TXT")){
								//Es archivo TXT

								BufferedReader f1 		= new BufferedReader(param.getArchivo().getReader());
								String linea	= f1.readLine();
								do {
									lineanro++;
									AfiliadoRPTO afiliado= getAfiliadoObject(linea);
									if (afiliado!=null){
										rentasDAO.insertaTrabajador(afiliado);
									}else{
										conerror_archivo++;
										break;
									}
									linea= f1.readLine();			                            	
								}  while (linea != null);
								f1.close();
							} else if (param.getFormatoAttach().equalsIgnoreCase("CSV")){
								//Es archivo TXT o CSV

								BufferedReader f1 		= new BufferedReader(param.getArchivo().getReader());
								MapeoRow mapeocsv= new MapeoRow();
								String lineacsv	= f1.readLine();
								do {
									lineanro++;
									String lineatxt= mapeocsv.leerCSV(lineacsv);
									if(!lineatxt.equals("titulos")){
										AfiliadoRPTO afiliado= getAfiliadoObject(lineatxt);
										if (afiliado!=null){
											rentasDAO.insertaTrabajador(afiliado);
										}else{
											conerror_archivo++;
											break;
										}
									}
									lineacsv= f1.readLine();			                            	
								}  while (lineacsv != null);
								f1.close();
							} // fin de CSV
							if (conerror_archivo== 0 && lineanro>0){
								doc.replaceItemValue("estado", "3");
								rentasDAO.commit();
							}else{
								doc.replaceItemValue("estado", "4");
								doc.replaceItemValue("mensajeNP", mensaje.toString());
								rentasDAO.rollback();
							}
							doc.save(true,true);
					} // fin de has file
				} //fin de For
			} //fin de IF (Mapeo())


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

	public AfiliadoRPTO getAfiliadoObject(String fila) {
		String nomtra, materno, paterno;
		int periodo, rutemp,  ruttra; 
		int rentaimpemp, rentaimpotraemp, rentatrabind, subsidios, pensiones, totingresos, nmesesinfo, ingpromedio, trabdeclaing;
		char dvrutemp, dvruttra;
		int nrovar=0;
		int fin=0;
		AfiliadoRPTO afiliado=null;
		try {
			afiliado= new AfiliadoRPTO();
			if ( fila.trim().length() >1 && fila.length() == 128){
				while (fila.indexOf(".")>0) {
					fila= fila.replace('.', ' ');
				}
				
				nrovar=1;
				periodo 				= Integer.parseInt(fila.substring(poslar[1][0],poslar[1][1]).trim());
				afiliado.setPeriodo(periodo);

				nrovar=2;
				rutemp 				= Integer.parseInt(fila.substring(poslar[2][0],poslar[2][1]).trim());
				afiliado.setRutEmpresa(rutemp);

				nrovar=3;
				dvrutemp			= fila.substring(poslar[3][0],poslar[3][1]).trim().toUpperCase().charAt(0);
				afiliado.setDvRutEmpresa(dvrutemp);

				nrovar=4;
				ruttra 				= Integer.parseInt(fila.substring(poslar[4][0],poslar[4][1]).trim());
				afiliado.setRutAfiliado(ruttra);


				nrovar=5;
				dvruttra				= fila.substring(poslar[5][0],poslar[5][1]).trim().toUpperCase().charAt(0);
				afiliado.setDvRutAfiliado(dvruttra);


				nrovar=6;
				paterno				=	Formato.cambiarCaracteres(fila.substring(poslar[6][0],poslar[6][1]).trim());
				afiliado.setApellidoPaterno(paterno);

				nrovar=7;
				materno				=	Formato.cambiarCaracteres(fila.substring(poslar[7][0],poslar[7][1]).trim());
				afiliado.setApellidoMaterno(materno);

				nrovar=8;
				nomtra				=	Formato.cambiarCaracteres(fila.substring(poslar[8][0],poslar[8][1]).trim());
				afiliado.setNombres(nomtra);


				//Remuneraciones con mismo Empleador
				nrovar=9;
				rentaimpemp			=	Integer.parseInt(BlancoCero(fila.substring(poslar[9][0],poslar[9][1])));
				afiliado.setRemuMismoEmpleador(rentaimpemp);

				//Otras Remumeraciones con distintos Empleadores
				nrovar=10;
				rentaimpotraemp	=	Integer.parseInt(BlancoCero(fila.substring(poslar[10][0],poslar[10][1])));
				afiliado.setRemuOtroEmpleador(rentaimpotraemp);

				//Renta Trabajador Independiente
				nrovar=11;
				rentatrabind			=	Integer.parseInt(BlancoCero(fila.substring(poslar[11][0],poslar[11][1])));
				afiliado.setRemuIndependiente(rentatrabind);

				//Subsidios
				nrovar=12;
				subsidios			=	Integer.parseInt(BlancoCero(fila.substring(poslar[12][0],poslar[12][1])));
				afiliado.setSubsidio(subsidios);

				//Pensiones
				nrovar=13;
				pensiones			=	Integer.parseInt(BlancoCero(fila.substring(poslar[13][0],poslar[13][1])));
				afiliado.setPensiones(pensiones); 

				//Total Ingresos
				nrovar=14;
				totingresos			=	Integer.parseInt(BlancoCero(fila.substring(poslar[14][0],poslar[14][1])));
				afiliado.setTotalIngresos(totingresos);			

				//Número de meses a que corresponde la información
				nrovar=15;
				nmesesinfo			=	Integer.parseInt(BlancoCero(fila.substring(poslar[15][0],poslar[15][1])));
				afiliado.setNumMeses(nmesesinfo);

				//Ingreso promedio mensual
				nrovar=16;
				ingpromedio			=	Integer.parseInt(BlancoCero(fila.substring(poslar[16][0],poslar[16][1])));
				afiliado.setPromedioMensual(ingpromedio);


				//Trabajador con o sin declaración jurada de ingresos
				nrovar=17;
				trabdeclaing			=	Integer.parseInt(BlancoCero(fila.substring(poslar[17][0],poslar[17][1])));
				afiliado.setDeclaracion((short)trabdeclaing);
				
			}

		}
		catch(NumberFormatException n) {
			System.out.println("Error en procesar trabajador, numérica de campo "+ nrovar);
			fin = 1;
		}		
		catch(Exception e) {
			System.out.println("Error en procesar de campo "+ nrovar);
			fin = 1;
		}
		if(fin==1){
			return null;
		}else{
			return afiliado;
		}
 	 }
 	 
 	   
     public String BlancoCero( String texto) {
			// Si el string contiene blancos retorna el string "0"
			if (texto.trim().equals("")) 
				texto = "0";
			return texto.trim();
    }

}
