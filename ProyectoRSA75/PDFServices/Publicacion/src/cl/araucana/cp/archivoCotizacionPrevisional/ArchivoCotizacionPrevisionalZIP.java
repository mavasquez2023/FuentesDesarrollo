package cl.araucana.cp.archivoCotizacionPrevisional;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utilPub.UtilPub;


/**
 * Servlet implementation class for Servlet: ComprobantePagoPDF
 *
 */
 public class ArchivoCotizacionPrevisionalZIP extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = 458482265167071787L;

	public ArchivoCotizacionPrevisionalZIP() {
		super();
	} 
	 
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		try {
			
			String indice = request.getParameter("indice").toString();
			
			HttpSession session = request.getSession();
			ArrayList listaFinal = new ArrayList();
			listaFinal = (ArrayList)session.getAttribute("campos");
			Map map = new HashMap();
			map = (HashMap) listaFinal.get(Integer.parseInt(indice));
			
			String fechaIni = map.get("fechaIni").toString();
			String proceso = map.get("proceso").toString();
			String tabla = map.get("tabla").toString();
			String rut = map.get("rut").toString();
			String digito = map.get("digito").toString();
			String convenio = map.get("convenio").toString();
			String holding = map.get("holding").toString();
			String fechaFin = map.get("fechaFin").toString();
			String fecha = map.get("fecha").toString();
			
			UtilPub util = new UtilPub();
			
			File archivo= new File("texto.txt");
			archivo.createNewFile();
			//FileWriter escribir=new FileWriter(archivo,true);
			BufferedWriter escribir = new BufferedWriter(new FileWriter(archivo));
			
			int x = 0;
			
			List lista = new ArrayList();
			
			ArchivoCotizacionPrevisionalDAO dao = new ArchivoCotizacionPrevisionalDAO();
			
			lista = dao.GerenaConsultaDetalle(tabla, rut, convenio, fecha, holding);
			String rutAF = "";
			int cantidadMOV = 0;
			int cantidadAPV = 0;
			int correlativoMOV = 0;
			int correlativoAPV = 0;
			for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
				Object[] object = (Object[]) iterator.next();
				
				if (!tabla.equals("PWF6100")) {
					if (!rutAF.equals(String.valueOf(object[0]).trim())) {
						this.rellenaArchivo(escribir, object, util, "PRINCIPAL");
						rutAF = String.valueOf(object[0]).trim();
					}
					
				}
				else //remuneraciones
				{
					//identificar rut si rut es distinto al anterior se ingresa columna 1
					if (!rutAF.equals(String.valueOf(object[0]).trim())) {
						this.rellenaArchivo(escribir, object, util, "PRINCIPAL");
						if(null != object[62]) cantidadAPV = Integer.parseInt(object[62].toString()); else cantidadAPV = 0;
						if(null != object[63]) cantidadMOV = Integer.parseInt(object[63].toString()); else cantidadMOV = 0;
						if(null != object[60]) correlativoAPV = Integer.parseInt(object[60].toString()); else correlativoAPV = 0;
						if(null != object[61]) correlativoMOV = Integer.parseInt(object[61].toString()); else correlativoMOV = 0;
	
						rutAF = String.valueOf(object[0]).trim();
					}else{ //si es igual al rut anterior verificar
						
						if (cantidadAPV > correlativoAPV) {//comprueba si hay APV
							int num = 0;
							if(null != object[60]) num = Integer.parseInt(object[60].toString()); 
							if (correlativoAPV+1 == num) {//comprueba que el registro que viene es correlativo al apv que ya se ingreso anteriormente
						//se comenta por requerimiento
						//		this.rellenaArchivo(escribir, result, util, "APV");
						//**
							}
							
						}else if (cantidadMOV > correlativoMOV) {//comprueba si hay MOVIMIENTOS
							//if (correlativoMOV+1 == result.getInt("ordenMOV")) {//comprueba que el registro que viene es correlativo al apv que ya se ingreso anteriormente
							int num = 0;
							if(null != object[61]) num = Integer.parseInt(object[61].toString());
							if (1 == num) {	
							//	this.rellenaArchivo(escribir, object, util, "MOV");
								correlativoMOV = num;
							}
						}
						
					}
					
				}
			}
			
			escribir.close();
	                
	        //para enviar el archivo por descarga
	                
			try {
				ByteArrayOutputStream bufferedOutput = new ByteArrayOutputStream(1024);
		        
				ZipOutputStream out = new ZipOutputStream(bufferedOutput);
				
				out.putNextEntry(new ZipEntry("cotizacion.txt"));
				
				ByteArrayOutputStream output = new ByteArrayOutputStream();
				
				
				
				
				byte buf[] = this.getBytesFromFile(archivo);
				output.write(buf); 
				out.write(output.toByteArray());
				out.closeEntry();
				
				out.close();
				
				response.setContentType("application/zip");
				response.setHeader("Content-Disposition", "inline; filename=notificaciones.zip");
				response.setContentLength(bufferedOutput.size());
				ServletOutputStream outp = response.getOutputStream();
				bufferedOutput.writeTo(outp);
				output.close();
				bufferedOutput.close();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			System.out.println("DONE");
		} catch (Exception e) {
			System.out.println("Error en ArchivoCotizacionPrevisionalZIP");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void rellenaArchivo(BufferedWriter escribir, Object[] campos, UtilPub util, String TIPO) throws IOException, SQLException {
		
		
		boolean isAFP = false;
		boolean isINP = false;
		
		String totalHaberes = "";
		if (null != String.valueOf(campos[18]).trim() && !"0".equals(String.valueOf(campos[18]).trim()) && !" ".equals(String.valueOf(campos[18]).trim())) {
			totalHaberes = String.valueOf(campos[18]).trim();
		}
		else if (null != String.valueOf(campos[19]).trim() && !"0".equals(String.valueOf(campos[19]).trim()) && !" ".equals(String.valueOf(campos[19]).trim())) {
			totalHaberes = String.valueOf(campos[19]).trim();
		}
		else if (null != String.valueOf(campos[20]).trim() && !"0".equals(String.valueOf(campos[20]).trim()) && !" ".equals(String.valueOf(campos[20]).trim())) {
			totalHaberes = String.valueOf(campos[20]).trim();
		}
		else if (null != String.valueOf(campos[21]).trim() && !"0".equals(String.valueOf(campos[21]).trim()) && !" ".equals(String.valueOf(campos[21]).trim())) {
			totalHaberes = String.valueOf(campos[21]).trim();
		}
		
		
		if (TIPO.equals("PRINCIPAL")) {
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[0]).trim(), 11));  							//1
			escribir.write(util.rellenaStringConEspacios(String.valueOf(campos[1]).trim(), 1)); 							//2
			
			String[] apellidos = String.valueOf(campos[2]).trim().split(" ");				
			if (apellidos.length == 2) {
				escribir.write(util.rellenaStringConEspacios(apellidos[0], 30));      							//3
				escribir.write(util.rellenaStringConEspacios(apellidos[1], 30));								//4
			}else{
				escribir.write(util.rellenaStringConEspacios(apellidos[0], 30)); 								//3
				escribir.write(util.rellenaStringConEspacios("", 30));											//4
			}
			
			escribir.write(util.rellenaStringConEspacios(String.valueOf(campos[3]).trim(), 30)); 						//5
			escribir.write(util.rellenaStringConEspacios(String.valueOf(campos[4]).trim(), 1)); 						//6
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[5]).trim(), 1)); 					//7
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[7]).trim(), 2)); 						//8
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[8]).trim(), 6));					//9
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[9]).trim(), 6)); 					//10
			if (null == String.valueOf(campos[10]).trim() || "".equals(String.valueOf(campos[10]).trim().trim()))
			{
				escribir.write(util.rellenaStringConEspacios("SIP", 3));									//11
			}
			else
			{
				escribir.write(util.rellenaStringConEspacios(String.valueOf(campos[10]).trim(), 3));		//11
			}
			
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[11]).trim(), 1));					//12
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[13]).trim(), 2));							//13
			escribir.write(util.rellenaStringConEspacios(String.valueOf(campos[12]).trim(), 2));					//14
			//DUDA MOVIMIENTO PERSONAL tabla PWF6104 campos PWDCMOVPE, PWDCFECINI, PWDCFECTER
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[14]).trim(), 2));		//15
			if (null == String.valueOf(campos[14]).trim() || "0".equals(String.valueOf(campos[14]).trim())) {
				
				escribir.write(util.rellenaStringConEspacios("", 10));	//16
				escribir.write(util.rellenaStringConEspacios("", 10));	//16
			}
			else 
			{
				escribir.write(util.rellenaStringConEspacios(util.formatoFecha(String.valueOf(campos[15]).trim()), 10));	//16
				escribir.write(util.rellenaStringConEspacios(util.formatoFecha(String.valueOf(campos[16]).trim()), 10));	//17
			}
			
			
			//FIN MOVIMIENTO PERSONAL
			escribir.write(util.rellenaStringConEspacios(String.valueOf(campos[17]).trim(), 1));						//18
			escribir.write(util.rellenaNumerosConCeros("0", 2)); //ausente										//19
			escribir.write(util.rellenaNumerosConCeros("0", 1)); //ausente										//20
			escribir.write(util.rellenaNumerosConCeros("0", 1)); //ausente										//21
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[22]).trim(), 6));				//22
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[23]).trim(), 6));	//23
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[24]).trim(), 6));		//24
			escribir.write(util.rellenaStringConEspacios(String.valueOf(campos[25]).trim(), 1));		//25
	//2.- DATOS AFP //
			if (null != String.valueOf(campos[10]).trim() && String.valueOf(campos[10]).trim().equals("AFP") && !"00".equals(String.valueOf(campos[26]).trim())) {
				isAFP = true;
			}
			else
			{
				isINP = true;
			}
			if (isAFP) {
				escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[26]).trim(), 2));						//26
				escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[27]).trim(), 8));					//27
				escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[28]).trim(), 8));					//28
			}
			else
			{
				escribir.write(util.rellenaNumerosConCeros("0", 2));												//26
				escribir.write(util.rellenaNumerosConCeros("0", 8));												//27
				escribir.write(util.rellenaNumerosConCeros("0", 8));												//28
				
			}
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[29]).trim(), 8));			//29
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[30]).trim(), 8));				//30
			escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//31
			escribir.write(util.rellenaNumerosConCeros("00,00", 5)); //ausente									//32
			escribir.write(util.rellenaNumerosConCeros("0", 9)); //ausente										//33
			escribir.write(util.rellenaNumerosConCeros("0", 2)); //ausente										//34	
			escribir.write(util.rellenaStringConEspacios("", 10)); //ausente									//35
			escribir.write(util.rellenaStringConEspacios("", 10)); //ausente									//36
			escribir.write(util.rellenaStringConEspacios("", 40)); //ausente									//37
			//TRABAJO PESADO
			escribir.write(util.rellenaNumerosConCeros("00,00", 5)); //ausente									//38
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[32]).trim(), 6));					//39
	//3.- DATOS AHORRO PREVISIONAL VOLUNTARIO INDIVIDUAL //		solo hay individual
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[35]).trim(), 3));						//40
			escribir.write(util.rellenaStringConEspacios("", 20)); //ausente									//41
			escribir.write(util.rellenaNumerosConCeros("0", 1)); //ausente										//42
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[36]).trim(), 8));					//43
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[37]).trim(), 8));				//44
	//4.- DATOS AHORRO PREVISIONAL VOLUNTARIO COLECTIVO //				
			escribir.write(util.rellenaNumerosConCeros("0", 3)); //ausente										//45
			escribir.write(util.rellenaStringConEspacios("", 20)); //ausente									//46
			escribir.write(util.rellenaNumerosConCeros("0", 1)); //ausente										//47
			escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//48
			escribir.write(util.rellenaNumerosConCeros("", 8)); //ausente										//49
	//5.- DATOS AFILIADO VOLUNTARIO  //	
			escribir.write(util.rellenaNumerosConCeros("0", 11)); //ausente										//50
			escribir.write(util.rellenaStringConEspacios("", 1)); //ausente						 				//51
			escribir.write(util.rellenaStringConEspacios("", 30)); //ausente									//52
			//DUDA!!!
			escribir.write(util.rellenaStringConEspacios("", 60)); //ausente			**********					//54
			
			escribir.write(util.rellenaNumerosConCeros("0", 2)); //ausente										//55
			escribir.write(util.rellenaStringConEspacios("", 10)); //ausente									//56
			escribir.write(util.rellenaStringConEspacios("", 10)); //ausente									//57
			escribir.write(util.rellenaNumerosConCeros("0", 2)); //ausente										//58
			escribir.write(util.rellenaNumerosConCeros("", 8)); //ausente										//59
			escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//60
			escribir.write(util.rellenaNumerosConCeros("0", 2)); //ausente										//61
			
	//6.- DATOS IPS - ISL - FONASA  //
			if (null != String.valueOf(campos[41]).trim() && "07".equals(String.valueOf(campos[41]).trim())) {
				escribir.write(util.rellenaNumerosConCeros("0", 4)); //ausente										//62
				escribir.write(util.rellenaNumerosConCeros("00,00", 5)); //ausente						 			//63
				escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[42]).trim(), 8));					//64
				escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[64]).trim(), 8));					//65
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//66
				escribir.write(util.rellenaNumerosConCeros("0", 4)); //ausente										//67
				escribir.write(util.rellenaNumerosConCeros("00,00", 5)); //ausente									//68
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//69
				escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[44]).trim(), 8));					//70
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //???											//71
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//72
				escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[40]).trim(), 8)); 			//73
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//74
			}
			else
			{
				escribir.write(util.rellenaNumerosConCeros("0", 4)); //ausente										//62
				escribir.write(util.rellenaNumerosConCeros("00,00", 5)); //ausente						 			//63
				escribir.write(util.rellenaNumerosConCeros("0", 8));												//64
				escribir.write(util.rellenaNumerosConCeros("0", 8));												//65
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//66
				escribir.write(util.rellenaNumerosConCeros("0", 4)); //ausente										//67
				escribir.write(util.rellenaNumerosConCeros("00,00", 5)); //ausente									//68
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//69
				escribir.write(util.rellenaNumerosConCeros("0", 8));												//70
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //???											//71
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//72
				escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[40]).trim(), 8)); 			//73
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//74
			}

			
	//7.- DATOS SALUD //
			if (null != String.valueOf(campos[41]).trim() && "07".equals(String.valueOf(campos[41]).trim())) {
				escribir.write(util.rellenaNumerosConCeros("0", 2));												//75
				escribir.write(util.rellenaStringConEspacios("", 16)); //ausente						 			//76
				escribir.write(util.rellenaNumerosConCeros("0", 8));												//77
				escribir.write(util.rellenaNumerosConCeros("0", 1));												//78
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//79
				escribir.write(util.rellenaNumerosConCeros("0", 8));		 										//80
				escribir.write(util.rellenaNumerosConCeros("0", 8)); 												//81
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//82
			}
			else {
				escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[41]).trim(), 2));				//75
				escribir.write(util.rellenaStringConEspacios("", 16)); //ausente						 			//76
				escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[42]).trim(), 8));					//77
				escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[43]).trim(), 1));							//78
				escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[44]).trim(), 8)); 					//79
				
				long resta = Long.parseLong(campos[44].toString()) - Long.parseLong(campos[45].toString());
				escribir.write(util.rellenaNumerosConCeros(String.valueOf(resta), 8));		 			//80
				
				escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[45]).trim(), 8)); 					//81
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//82
			}
			
			
			
			
	//8.- CAJA COPMPENSACION //
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[46]).trim(), 2)); 						//83
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[47]).trim(), 8));	 				//84
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[48]).trim(), 8));						//85
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[49]).trim(), 8));				//86
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[50]).trim(), 8));						//87
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[51]).trim(), 8));					//88
			escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//89
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[53]).trim(), 8)); 			//90
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[52]).trim(), 8));						//91
			escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//92
			escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//93
			escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente							 			//94
			escribir.write(util.rellenaStringConEspacios("", 20)); //ausente									//95
	//9.- DATOS MUTUALIDAD //		
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[54]).trim(), 2)); 						//96
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[55]).trim(), 8)); 					//97
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[56]).trim(), 8)); 		 			//98
			escribir.write(util.rellenaNumerosConCeros("0", 3)); //ausente										//99
	//10.- DATOS SEGURO CESANTIA //
			long suma = 0;
			int segurox = 0;
			int seguroy = 0;
			
			if(null != campos[33])segurox = Integer.parseInt(campos[33].toString());
			if(null != campos[34])seguroy = Integer.parseInt(campos[34].toString());
			
			if (0 != segurox) {
				suma = segurox;
			}
			else
			{
				suma = seguroy;
			}
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(suma), 8));								//100
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[57]).trim(), 8));				//101
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[58]).trim(), 8));	 			//102
	//11.- DATOS PAGADOR SUBSIDIOS //
			if (null != String.valueOf(campos[59]).trim()) {
				String[] pagador = String.valueOf(campos[59]).trim().split("-");				
				if (pagador.length == 2) {
					escribir.write(util.rellenaNumerosConCeros(pagador[0], 11)); //ausente							 			//103
					escribir.write(util.rellenaStringConEspacios(pagador[1], 1)); //ausente										//104
				}else{
					escribir.write(util.rellenaNumerosConCeros("0", 11)); //ausente							 					//103
					escribir.write(util.rellenaStringConEspacios("", 1)); //ausente												//104
				}
			}
			else
			{
				escribir.write(util.rellenaNumerosConCeros("0", 11)); //ausente							 					//103
				escribir.write(util.rellenaStringConEspacios("", 1)); //ausente												//104
			}
			
	//12- OTROS DATOS DE LA EMPRESA//
			escribir.write(util.rellenaStringConEspacios(String.valueOf(campos[6]).trim(), 20));					//105
	//13- OTROS DATOS//
			escribir.write(util.rellenaNumerosConCeros(totalHaberes, 11)); 											//106		
		}
//APV *************************************************************************************************************************************	
		else if (TIPO.equals("APV")) {
				escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[0]).trim(), 11));  							//1
				escribir.write(util.rellenaStringConEspacios(String.valueOf(campos[1]).trim(), 1)); 							//2
				
				String[] apellidos = String.valueOf(campos[2]).trim().split(" ");				
				if (apellidos.length == 2) {
					escribir.write(util.rellenaStringConEspacios(apellidos[0], 30));      							//3
					escribir.write(util.rellenaStringConEspacios(apellidos[1], 30));								//4
				}else{
					escribir.write(util.rellenaStringConEspacios(apellidos[0], 30)); 								//3
					escribir.write(util.rellenaStringConEspacios("", 30));											//4
				}
				
				escribir.write(util.rellenaStringConEspacios(String.valueOf(campos[3]).trim(), 30)); 						//5
				escribir.write(util.rellenaStringConEspacios(String.valueOf(campos[4]).trim(), 1)); 						//6
				escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[5]).trim(), 1)); 					//7
				escribir.write(util.rellenaNumerosConCeros("01", 2)); 												//8
				escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[8]).trim(), 6));					//9
				escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[9]).trim(), 6)); 					//10
				if (null == String.valueOf(campos[10]).trim() || "".equals(String.valueOf(campos[10]).trim().trim()))
				{
					escribir.write(util.rellenaStringConEspacios("SIP", 3));									//11
				}
				else
				{
					escribir.write(util.rellenaStringConEspacios(String.valueOf(campos[10]).trim(), 3));		//11
				}
				escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[11]).trim(), 1));					//12
				escribir.write(util.rellenaNumerosConCeros("0", 2));												//13
				escribir.write(util.rellenaStringConEspacios("1", 2));												//14
				// MOVIMIENTO PERSONAL tabla PWF6104 campos PWDCMOVPE, PWDCFECINI, PWDCFECTER
				escribir.write(util.rellenaNumerosConCeros("0", 2));												//15
				escribir.write(util.rellenaStringConEspacios("", 10));												//16
				escribir.write(util.rellenaStringConEspacios("", 10));												//17
				//FIN MOVIMIENTO PERSONAL
				escribir.write(util.rellenaStringConEspacios("", 1));												//18
				escribir.write(util.rellenaNumerosConCeros("0", 2)); //ausente										//19
				escribir.write(util.rellenaNumerosConCeros("0", 1)); //ausente										//20
				escribir.write(util.rellenaNumerosConCeros("0", 1)); //ausente										//21
				escribir.write(util.rellenaNumerosConCeros("0", 6));												//22
				escribir.write(util.rellenaNumerosConCeros("0", 6));												//23
				escribir.write(util.rellenaNumerosConCeros("0", 6));												//24
				escribir.write(util.rellenaStringConEspacios("", 1));												//25
		//2.- DATOS AFP //
				if (String.valueOf(campos[10]).trim().equals("AFP")) {
					isAFP = true;
					escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[26]).trim(), 2));					//26
				}
				else
				{
					escribir.write(util.rellenaNumerosConCeros("0", 2));											//26
				}
				
				escribir.write(util.rellenaNumerosConCeros("0", 8));												//27
				escribir.write(util.rellenaNumerosConCeros("0", 8));												//28
				escribir.write(util.rellenaNumerosConCeros("0", 8));												//29
				escribir.write(util.rellenaNumerosConCeros("0", 8));												//30
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//31
				escribir.write(util.rellenaNumerosConCeros("00,00", 5)); //ausente									//32
				escribir.write(util.rellenaNumerosConCeros("0", 9)); //ausente										//33
				escribir.write(util.rellenaNumerosConCeros("0", 2)); //ausente										//34	
				escribir.write(util.rellenaStringConEspacios("", 10)); //ausente									//35
				escribir.write(util.rellenaStringConEspacios("", 10)); //ausente									//36
				escribir.write(util.rellenaStringConEspacios("", 40)); //ausente									//37
				//TRABAJO PESADO	
				escribir.write(util.rellenaNumerosConCeros("00,00", 5)); //ausente									//38
				escribir.write(util.rellenaNumerosConCeros("0", 6));												//39
		//3.- DATOS AHORRO PREVISIONAL VOLUNTARIO INDIVIDUAL //		solo hay individual
				escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[35]).trim(), 3));						//40
				escribir.write(util.rellenaStringConEspacios("", 20)); //ausente									//41
				escribir.write(util.rellenaNumerosConCeros("0", 1)); //ausente										//42
				escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[36]).trim(), 8));					//43
				escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[37]).trim(), 8));				//44
		//4.- DATOS AHORRO PREVISIONAL VOLUNTARIO COLECTIVO //				
				escribir.write(util.rellenaNumerosConCeros("0", 3)); //ausente										//45
				escribir.write(util.rellenaStringConEspacios("", 20)); //ausente									//46
				escribir.write(util.rellenaNumerosConCeros("0", 1)); //ausente										//47
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//48
				escribir.write(util.rellenaNumerosConCeros("", 8)); //ausente										//49
		//5.- DATOS AFILIADO VOLUNTARIO  //	
				escribir.write(util.rellenaNumerosConCeros("0", 11)); //ausente										//50
				escribir.write(util.rellenaStringConEspacios("", 1)); //ausente						 				//51
				escribir.write(util.rellenaStringConEspacios("", 30)); //ausente									//52
				escribir.write(util.rellenaStringConEspacios("", 60)); //ausente			**********					//54
				
				escribir.write(util.rellenaNumerosConCeros("0", 2)); //ausente										//55
				escribir.write(util.rellenaStringConEspacios("", 10)); //ausente									//56
				escribir.write(util.rellenaStringConEspacios("", 10)); //ausente									//57
				escribir.write(util.rellenaNumerosConCeros("0", 2)); //ausente										//58
				escribir.write(util.rellenaNumerosConCeros("", 8)); //ausente										//59
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//60
				escribir.write(util.rellenaNumerosConCeros("0", 2)); //ausente										//61
		//6.- DATOS IPS - ISL - FONASA  //
				//obligatorio si es inp
				escribir.write(util.rellenaNumerosConCeros("0", 4)); //ausente										//62
				escribir.write(util.rellenaNumerosConCeros("00,00", 5)); //ausente					 				//63
				escribir.write(util.rellenaNumerosConCeros("0", 8));												//64
				escribir.write(util.rellenaNumerosConCeros("0", 8));												//65
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//66
				escribir.write(util.rellenaNumerosConCeros("0", 4)); //ausente										//67
				escribir.write(util.rellenaNumerosConCeros("00,00", 5)); //ausente									//68
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//69
				escribir.write(util.rellenaNumerosConCeros("0", 8));												//70
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //???											//71
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//72
				escribir.write(util.rellenaNumerosConCeros("0", 8)); 												//73
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//74
		//7.- DATOS SALUD //
				escribir.write(util.rellenaNumerosConCeros("0", 2));												//75
				escribir.write(util.rellenaStringConEspacios("0", 16)); //ausente						 			//76
				escribir.write(util.rellenaNumerosConCeros("0", 8));												//77
				escribir.write(util.rellenaNumerosConCeros("0", 1));												//78
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//79
				escribir.write(util.rellenaNumerosConCeros("0", 8));		 										//80
				escribir.write(util.rellenaNumerosConCeros("0", 8)); 												//81
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//82
		//8.- DATOS SALUD //
				escribir.write(util.rellenaNumerosConCeros("0", 2)); 												//83
				escribir.write(util.rellenaNumerosConCeros("0", 8));	 											//84
				escribir.write(util.rellenaNumerosConCeros("0", 8));												//85
				escribir.write(util.rellenaNumerosConCeros("0", 8));												//86
				escribir.write(util.rellenaNumerosConCeros("0", 8));												//87
				escribir.write(util.rellenaNumerosConCeros("0", 8));												//88
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//89
				escribir.write(util.rellenaNumerosConCeros("0", 8)); 												//90
				escribir.write(util.rellenaNumerosConCeros("0", 8));												//91
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//92
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//93
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente							 			//94
				escribir.write(util.rellenaStringConEspacios("0", 20)); //ausente									//95
		//9.- DATOS MUTUALIDAD //		
				escribir.write(util.rellenaNumerosConCeros("0", 2)); 												//96
				escribir.write(util.rellenaNumerosConCeros("0", 8)); 												//97
				escribir.write(util.rellenaNumerosConCeros("0", 8)); 		 										//98
				escribir.write(util.rellenaNumerosConCeros("0", 3)); //ausente										//99
		//10.- DATOS SEGURO CESANTIA //
				escribir.write(util.rellenaNumerosConCeros("0", 8));												//100
				escribir.write(util.rellenaNumerosConCeros("0", 8));												//101
				escribir.write(util.rellenaNumerosConCeros("0", 8));	 											//102
		//11.- DATOS PAGADOR SUBSIDIOS //
				escribir.write(util.rellenaNumerosConCeros("0", 11)); //ausente							 					//103
				escribir.write(util.rellenaStringConEspacios("", 1)); //ausente												//104
		//12- OTROS DATOS DE LA EMPRESA//
				escribir.write(util.rellenaStringConEspacios(String.valueOf(campos[6]).trim(), 20));					//105
		//13- OTROS DATOS//
				escribir.write(util.rellenaNumerosConCeros(totalHaberes, 11)); 									//106	
		}
//MOV *************************************************************************************************************************************		
		else if (TIPO.equals("MOV")) {
			
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[0]).trim(), 11));  							//1
			escribir.write(util.rellenaStringConEspacios(String.valueOf(campos[1]).trim(), 1)); 							//2
			
			String[] apellidos = String.valueOf(campos[2]).trim().split(" ");				
			if (apellidos.length == 2) {
				escribir.write(util.rellenaStringConEspacios(apellidos[0], 30));      							//3
				escribir.write(util.rellenaStringConEspacios(apellidos[1], 30));								//4
			}else{
				escribir.write(util.rellenaStringConEspacios(apellidos[0], 30)); 								//3
				escribir.write(util.rellenaStringConEspacios("", 30));											//4
			}
			
			escribir.write(util.rellenaStringConEspacios(String.valueOf(campos[3]).trim(), 30)); 						//5
			escribir.write(util.rellenaStringConEspacios(String.valueOf(campos[4]).trim(), 1)); 						//6
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[5]).trim(), 1)); 					//7
			escribir.write(util.rellenaNumerosConCeros("01", 2)); 												//8
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[8]).trim(), 6));					//9
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[9]).trim(), 6)); 					//10
			if (null == String.valueOf(campos[10]).trim() || "".equals(String.valueOf(campos[10]).trim().trim()))
			{
				escribir.write(util.rellenaStringConEspacios("SIP", 3));									//11
			}
			else
			{
				escribir.write(util.rellenaStringConEspacios(String.valueOf(campos[10]).trim(), 3));		//11
			}
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[11]).trim(), 1));					//12
			escribir.write(util.rellenaNumerosConCeros("0", 2));												//13
			escribir.write(util.rellenaStringConEspacios("01", 2));												//14
			//DUDA MOVIMIENTO PERSONAL tabla PWF6104 campos PWDCMOVPE, PWDCFECINI, PWDCFECTER
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[14]).trim(), 2));		//15
			if (null == String.valueOf(campos[14]).trim() || "0".equals(String.valueOf(campos[14]).trim())) {
				
				escribir.write(util.rellenaStringConEspacios("", 10));	//16
				escribir.write(util.rellenaStringConEspacios("", 10));	//16
			}
			else 
			{
				escribir.write(util.rellenaStringConEspacios(util.formatoFecha(String.valueOf(campos[15]).trim()), 10));	//16
				escribir.write(util.rellenaStringConEspacios(util.formatoFecha(String.valueOf(campos[16]).trim()), 10));	//17
			}
			//FIN MOVIMIENTO PERSONAL
			escribir.write(util.rellenaStringConEspacios("", 1));												//18
			escribir.write(util.rellenaNumerosConCeros("0", 2)); //ausente										//19
			escribir.write(util.rellenaNumerosConCeros("0", 1)); //ausente										//20
			escribir.write(util.rellenaNumerosConCeros("0", 1)); //ausente										//21
			escribir.write(util.rellenaNumerosConCeros("0", 6));												//22
			escribir.write(util.rellenaNumerosConCeros("0", 6));												//23
			escribir.write(util.rellenaNumerosConCeros("0", 6));												//24
			escribir.write(util.rellenaStringConEspacios("", 1));		//25
	//2.- DATOS AFP //
			if (String.valueOf(campos[10]).trim().equals("AFP")) {
				isAFP = true;
				escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[26]).trim(), 2));					//26
			}
			else
			{
				escribir.write(util.rellenaNumerosConCeros("0", 2));											//26
			}
			escribir.write(util.rellenaNumerosConCeros("0", 8));												//27
			escribir.write(util.rellenaNumerosConCeros("0", 8));												//28
			escribir.write(util.rellenaNumerosConCeros("0", 8));												//29
			escribir.write(util.rellenaNumerosConCeros("0", 8));												//30
			escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//31
			escribir.write(util.rellenaNumerosConCeros("00,00", 5)); //ausente									//32
			escribir.write(util.rellenaNumerosConCeros("0", 9)); //ausente										//33
			escribir.write(util.rellenaNumerosConCeros("0", 2)); //ausente										//34	
			escribir.write(util.rellenaStringConEspacios("", 10)); //ausente									//35
			escribir.write(util.rellenaStringConEspacios("", 10)); //ausente									//36
			escribir.write(util.rellenaStringConEspacios("", 40)); //ausente									//37
			//TRABAJO PESADO
			escribir.write(util.rellenaNumerosConCeros("00,00", 5)); //ausente									//38
			escribir.write(util.rellenaNumerosConCeros("0", 6));												//39
	//3.- DATOS AHORRO PREVISIONAL VOLUNTARIO INDIVIDUAL //		solo hay individual
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[35]).trim(), 3));						//40
			escribir.write(util.rellenaStringConEspacios("", 20)); //ausente									//41
			escribir.write(util.rellenaNumerosConCeros("0", 1)); //ausente										//42
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[36]).trim(), 8));					//43
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[37]).trim(), 8));				//44
	//4.- DATOS AHORRO PREVISIONAL VOLUNTARIO COLECTIVO //				
			escribir.write(util.rellenaNumerosConCeros("0", 3)); //ausente										//45
			escribir.write(util.rellenaStringConEspacios("", 20)); //ausente									//46
			escribir.write(util.rellenaNumerosConCeros("0", 1)); //ausente										//47
			escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//48
			escribir.write(util.rellenaNumerosConCeros("", 8)); //ausente										//49
	//5.- DATOS AFILIADO VOLUNTARIO  //	
			escribir.write(util.rellenaNumerosConCeros("0", 11)); //ausente										//50
			escribir.write(util.rellenaStringConEspacios("", 1)); //ausente						 				//51
			escribir.write(util.rellenaStringConEspacios("", 30)); //ausente									//52
			escribir.write(util.rellenaStringConEspacios("", 60)); //ausente			**********					//54
			
			escribir.write(util.rellenaNumerosConCeros("0", 2)); //ausente										//55
			escribir.write(util.rellenaStringConEspacios("", 10)); //ausente									//56
			escribir.write(util.rellenaStringConEspacios("", 10)); //ausente									//57
			escribir.write(util.rellenaNumerosConCeros("0", 2)); //ausente										//58
			escribir.write(util.rellenaNumerosConCeros("", 8)); //ausente										//59
			escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//60
			escribir.write(util.rellenaNumerosConCeros("0", 2)); //ausente										//61
	//6.- DATOS IPS - ISL - FONASA  //
			//obligatorio si es inp
			escribir.write(util.rellenaNumerosConCeros("0", 4)); //ausente										//62
			escribir.write(util.rellenaNumerosConCeros("00,00", 5)); //ausente					 				//63
			escribir.write(util.rellenaNumerosConCeros("0", 8));												//64
			escribir.write(util.rellenaNumerosConCeros("0", 8));												//65
			escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//66
			escribir.write(util.rellenaNumerosConCeros("0", 4)); //ausente										//67
			escribir.write(util.rellenaNumerosConCeros("00,00", 5)); //ausente									//68
			escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//69
			escribir.write(util.rellenaNumerosConCeros("0", 8));												//70
			escribir.write(util.rellenaNumerosConCeros("0", 8)); //???											//71
			escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//72
			escribir.write(util.rellenaNumerosConCeros("0", 8)); 												//73
			escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//74
	//7.- DATOS SALUD //
			if (null != String.valueOf(campos[41]).trim() && "07".equals(String.valueOf(campos[41]).trim())) {
				escribir.write(util.rellenaNumerosConCeros("0", 2));												//75
				escribir.write(util.rellenaStringConEspacios("", 16)); //ausente						 			//76
				escribir.write(util.rellenaNumerosConCeros("0", 8));												//77
				escribir.write(util.rellenaNumerosConCeros("0", 1));												//78
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//79
				escribir.write(util.rellenaNumerosConCeros("0", 8));		 										//80
				escribir.write(util.rellenaNumerosConCeros("0", 8)); 												//81
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//82
			}
			else {
				escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[41]).trim(), 2));				//75
				escribir.write(util.rellenaStringConEspacios("", 16)); //ausente						 			//76
				escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[42]).trim(), 8));					//77
				escribir.write(util.rellenaNumerosConCeros("0", 1));												//78
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//79
				escribir.write(util.rellenaNumerosConCeros("0", 8));		 										//80
				escribir.write(util.rellenaNumerosConCeros("0", 8)); 												//81
				escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//82
			}
			
	//8.- DATOS SALUD //
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[46]).trim(), 2)); 						//83
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[47]).trim(), 8));	 				//84
			escribir.write(util.rellenaNumerosConCeros("0", 8));												//85
			escribir.write(util.rellenaNumerosConCeros("0", 8));												//86
			escribir.write(util.rellenaNumerosConCeros("0", 8));												//87
			escribir.write(util.rellenaNumerosConCeros("0", 8));												//88
			escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//89
			escribir.write(util.rellenaNumerosConCeros("0", 8)); //condicion ver doc							//90
			escribir.write(util.rellenaNumerosConCeros("0", 8));												//91
			escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//92
			escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente										//93
			escribir.write(util.rellenaNumerosConCeros("0", 8)); //ausente							 			//94
			escribir.write(util.rellenaStringConEspacios("0", 20)); //ausente									//95
	//9.- DATOS MUTUALIDAD //		
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[54]).trim(), 2)); 						//96
			escribir.write(util.rellenaNumerosConCeros(String.valueOf(campos[55]).trim(), 8)); 					//97
			escribir.write(util.rellenaNumerosConCeros("0", 8)); 		 										//98
			escribir.write(util.rellenaNumerosConCeros("0", 3)); //ausente										//99
	//10.- DATOS SEGURO CESANTIA //
			escribir.write(util.rellenaNumerosConCeros("0", 8));												//100
			escribir.write(util.rellenaNumerosConCeros("0", 8));												//101
			escribir.write(util.rellenaNumerosConCeros("0", 8));	 											//102
	//11.- DATOS PAGADOR SUBSIDIOS //
			if (null != String.valueOf(campos[59]).trim()) {
				String[] pagador = String.valueOf(campos[59]).trim().split("-");				
				if (pagador.length == 2) {
					escribir.write(util.rellenaNumerosConCeros(pagador[0], 11)); //ausente							 			//103
					escribir.write(util.rellenaStringConEspacios(pagador[1], 1)); //ausente										//104
				}else{
					escribir.write(util.rellenaNumerosConCeros("0", 11)); //ausente							 					//103
					escribir.write(util.rellenaStringConEspacios("", 1)); //ausente												//104
				}
			}else{
				escribir.write(util.rellenaNumerosConCeros("0", 11)); //ausente							 					//103
				escribir.write(util.rellenaStringConEspacios("", 1)); //ausente												//104
			}
	//12- OTROS DATOS DE LA EMPRESA//
			escribir.write(util.rellenaStringConEspacios(String.valueOf(campos[6]).trim(), 20));					//105
	//13- OTROS DATOS//
			escribir.write(util.rellenaNumerosConCeros(totalHaberes, 11)); 										//106		
		}
		
		
		
		escribir.write("\r\n");
		
	}

	public static byte[] getBytesFromFile(File file) throws IOException {        
        // Get the size of the file
        long length = file.length();

        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            // File is too large
            throw new IOException("File is too large!");
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;

        InputStream is = new FileInputStream(file);
        try {
            while (offset < bytes.length
                   && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
                offset += numRead;
            }
        } finally {
            is.close();
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }
        return bytes;
    }

	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DOGET");
		processRequest(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DOPOST");
		processRequest(request, response);
	}   	  	  
	
}