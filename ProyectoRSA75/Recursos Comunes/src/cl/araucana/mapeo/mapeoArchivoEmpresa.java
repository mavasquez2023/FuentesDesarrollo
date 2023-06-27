/**
 * 
 */
package cl.araucana.mapeo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import cl.recursos.Archivo;


/**
 * @author usist24
 *
 */


public class mapeoArchivoEmpresa {
	private static Archivo lista= new Archivo();
	private static List log= new ArrayList(); 
	public static void main(String[] args) throws Exception {
		System.out.println("Inicio Mapeo Directorio");
		mapeoArchivoEmpresa mapeo= new mapeoArchivoEmpresa();
		log.add("nueva liunes");
		lista.borrarArchivo(args[0] + "/resumen.log");
		File[] filesRaiz= lista.getListaDeArchivos(args[0]);
		for (int i = 0; i < filesRaiz.length; i++) {
			System.out.println("procesando carpeta: " + filesRaiz[i].getAbsolutePath());
			File[] filesSubRaiz= lista.getListaDeArchivos(filesRaiz[i].getAbsolutePath());
			for (int j = 0; j < filesSubRaiz.length; j++) {
				System.out.println("procesando archivo: " + filesSubRaiz[j].getAbsolutePath());
				log.add("Procesando archivo: " + filesSubRaiz[j].getAbsolutePath());
				mapeo.procesaArchivo(filesSubRaiz[j].getAbsolutePath());
				lista.crearArchivo(args[0] + "/resumen.log", log);
			}
			
		}
		//mapeo.procesaArchivo("C:/tmp/ARCHIVOS/Remu200202/EMPR0202");
		System.out.println("FIN Mapeo Directorio");
	}
	public void procesaArchivo(String archivo) {
		System.out.println("Inicio Mapeo Archivo " + archivo);
		mapeoArchivoEmpresa mapeo= new mapeoArchivoEmpresa();
		List registros= new ArrayList();
		List cabeceras= new ArrayList();
		try {
			if(mapeo.leerArchivo(archivo, registros, cabeceras)){
				//mapeo.crearArchivoDetalle("C:/tmp/ARCHIVOS 200108/EMPRESA_DETALLE.sql", registros);
				CertificadoDAO certdao= new CertificadoDAO();
				if(certdao.insertArchivoDetalle(registros)){
					log.addAll(certdao.getLog());
					if(certdao.insertArchivoCabecera(cabeceras)){
						certdao.commit();
						log.addAll(certdao.getLog());
						System.out.println("Archivo OK: " + archivo);
						log.add("Archivo Exitoso!");
					}else{
						System.out.println("Error en insertArchivoCabecera");
						log.add("Error en insert Archivo Cabecera.");
						certdao.rollback();
					}
				}else{
					System.out.println("Error en insertArchivoDetalle, no insert Cabecera archivo: " + archivo);
					log.add("Error en insert Archivo Detalle.");
					certdao.rollback();
				}
			}else{
				System.out.println("Archivo NOT OK: " + archivo);
				log.add("Error al leer Archivo.");
			}
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			System.out.println("Error en archivo:" + archivo);
			log.add("Error General al leer Archivo, mensaje: " + e.getMessage());
			e.printStackTrace();
		}
	}
	public boolean leerArchivo(String pathfile, List retorno, List cabeceras) {
		BufferedReader f1;
		String linea = "";
		try {
			//leyendo archivo, usando BufferedReader
			f1 = new BufferedReader(new FileReader(pathfile));
			DetalleCertificadoTO registroTO=null;
			CabeceraCertificadoTO cabeceraTO= null;
			String folio="";
			String secuenciaFolio="";
			String mesRemu="";
			String fechapago="";
			String fechaGrati="";
			int numeroPaginas=0;
			int añoRemu=0;
			int rutemp=0;
			char dvRutemp=' ';
			String razonSocial="";
			String direccion="";
			String region="";
			String entidad="";
			double tasa=0.0;
			double tasaINP=0.0;
			double tasaFonasa=0.0;
			int codregistro_old=0;
			while ((linea = f1.readLine()) != null) {
				if(linea.length()>2){
					int codregistro= Integer.parseInt(linea.substring(0, 2));
					switch (codregistro) {
					case 10: //Inicio INP
						folio= linea.substring(42, 49);
						mesRemu=linea.substring(52, 54).trim();
						añoRemu=Integer.parseInt(linea.substring(54, 58).trim());
						fechaGrati= linea.substring(66, 70).trim();
						rutemp=Integer.parseInt(linea.substring(81, 90).trim());
						dvRutemp=linea.substring(90, 91).charAt(0);
						razonSocial= CambiarCaracteres(linea.substring(91, 131).trim());
						direccion= CambiarCaracteres(linea.substring(148, 198).trim() + ", " + linea.substring(248, 278).trim());
						region="";
						break;
					case 16:
						if(linea.length()>5){
							tasa= Double.parseDouble(linea.substring(28, 33).replace(',', '.'));
							if(tasa>10){
								tasaINP= tasa;
							}else{
								tasaFonasa= tasa;
							}
						}
						break;
					case 11:
						fechapago= linea.substring(225, 235);
						numeroPaginas= Integer.parseInt(linea.substring(62, 65).trim());
						break;
					case 26: case 20: break;
					case 12:{
						int montoRemuneracion= Integer.valueOf(linea.substring(80, 88).trim()).intValue();
						cabeceraTO= new CabeceraCertificadoTO();
						if(montoRemuneracion>0){
							registroTO= new DetalleCertificadoTO();
							if(!fechaGrati.equals("0")){
								registroTO.setTipoNomina("G");
								cabeceraTO.setTipoNomina("G");
								String[] periodo= getAñoMes(fechapago);
								añoRemu=Integer.parseInt(periodo[0]);
								mesRemu=periodo[1];
							}else{
								registroTO.setTipoNomina("R");
								cabeceraTO.setTipoNomina("R");
							}
							registroTO.setFolio(folio);
							registroTO.setSecuenciaFolio(secuenciaFolio);
							registroTO.setMesRemu(mesRemu);
							registroTO.setAnioRemu(añoRemu);
							registroTO.setRutemp(rutemp);
							registroTO.setDvRutemp(dvRutemp);
							try {
								registroTO.setFechaPago(Integer.parseInt(fechapago.substring(6, 10) + fechapago.substring(3, 5) + fechapago.substring(0, 2)));
							} catch (Exception e) {
								// TODO Bloque catch generado automáticamente
								e.printStackTrace();
							}
							{
								int rutafi= Integer.parseInt(linea.substring(5, 13).trim());
								char dvRutafi= linea.substring(13, 14).charAt(0);
								String apellidos= CambiarCaracteres(linea.substring(14, 44).trim());
								String nombres= CambiarCaracteres(linea.substring(44, 74).trim());
								int monto_calc=0;
								//Pensión + Fonasa
								monto_calc= (int)Math.round((montoRemuneracion * tasaINP)/100);
								registroTO.setTipoEntidad("T");
								registroTO.setEntidad("INP(Pago Salud/Pensión)");
								registroTO.setRutafi(rutafi);
								registroTO.setDvRutafi(dvRutafi);
								registroTO.setRemuneracion(montoRemuneracion);
								registroTO.setMonto(monto_calc);
								
								//Set Cabecera
								cabeceraTO.setApellidos(apellidos);
								cabeceraTO.setConvenio(1);
								cabeceraTO.setDireccion(direccion);
								cabeceraTO.setDvRutafi(dvRutafi);
								cabeceraTO.setDvRutemp(dvRutemp);
								cabeceraTO.setFechaEmision(registroTO.getFechaPago());
								cabeceraTO.setHolding(0);
								cabeceraTO.setNombres(nombres);
								cabeceraTO.setPeriodo(Integer.parseInt(añoRemu + "" + mesRemu));
								cabeceraTO.setRazonSocial(razonSocial);
								cabeceraTO.setRegion(region);
								cabeceraTO.setRutafi(rutafi);
								cabeceraTO.setRutemp(rutemp);
								cabeceraTO.setSucursal("CASMAT");
								
							}
							retorno.add(registroTO);
							cabeceras.add(cabeceraTO);
						}
					}
						break;
					case 23:{
						int montoRemuneracion= Integer.valueOf(linea.substring(74, 82).trim()).intValue();
						cabeceraTO= new CabeceraCertificadoTO();
						if(montoRemuneracion>0){
							registroTO= new DetalleCertificadoTO();
							if(!fechaGrati.equals("0")){
								registroTO.setTipoNomina("G");
								cabeceraTO.setTipoNomina("G");
								String[] periodo= getAñoMes(fechapago);
								añoRemu=Integer.parseInt(periodo[0]);
								mesRemu=periodo[1];
							}else{
								registroTO.setTipoNomina("R");
								cabeceraTO.setTipoNomina("R");
							}
							registroTO.setFolio(folio);
							registroTO.setSecuenciaFolio(secuenciaFolio);
							registroTO.setMesRemu(mesRemu);
							registroTO.setAnioRemu(añoRemu);
							registroTO.setRutemp(rutemp);
							registroTO.setDvRutemp(dvRutemp);
							registroTO.setFechaPago(Integer.parseInt(fechapago.substring(6, 10) + fechapago.substring(3, 5) + fechapago.substring(0, 2)));
							{
								int rutafi= Integer.parseInt(linea.substring(5, 13).trim());
								char dvRutafi= linea.substring(13, 14).charAt(0);
								String apellidos= CambiarCaracteres(linea.substring(14, 44).trim());
								String nombres= CambiarCaracteres(linea.substring(44, 74).trim());
								int monto_calc=0;
								monto_calc= (int)Math.round((montoRemuneracion * tasaFonasa)/100);
								//FONASA
								registroTO.setTipoEntidad("S");
								registroTO.setEntidad("INP(Pago Salud)");
								registroTO.setRutafi(rutafi);
								registroTO.setDvRutafi(dvRutafi);
								registroTO.setRemuneracion(montoRemuneracion);
								registroTO.setMonto(monto_calc);

								//Set Cabecera
								cabeceraTO.setApellidos(apellidos);
								cabeceraTO.setConvenio(1);
								cabeceraTO.setDireccion(direccion);
								cabeceraTO.setDvRutafi(dvRutafi);
								cabeceraTO.setDvRutemp(dvRutemp);
								cabeceraTO.setFechaEmision(registroTO.getFechaPago());
								cabeceraTO.setHolding(0);
								cabeceraTO.setNombres(nombres);
								cabeceraTO.setPeriodo(Integer.parseInt(añoRemu + "" + mesRemu));
								cabeceraTO.setRazonSocial(razonSocial);
								cabeceraTO.setRegion(region);
								cabeceraTO.setRutafi(rutafi);
								cabeceraTO.setRutemp(rutemp);
								cabeceraTO.setSucursal("CASMAT");

							}
							retorno.add(registroTO);
							cabeceras.add(cabeceraTO);
						}
					}
						break;
					case 24: //Fin INP
						numeroPaginas--;
						if(numeroPaginas==0){
							entidad="";
							folio="";
							secuenciaFolio="";
							mesRemu="";
							fechapago="";
							fechaGrati="";
							añoRemu=0;
							rutemp=0;
							dvRutemp=' ';
							razonSocial="";
							direccion="";
							region="";
							tasa=0.0;
						}
						//fin INP
						//mapeoINP(codregistro, linea);
						break;
					case 7: //Inicio MUTUAL
						entidad= linea.substring(2, 42).trim();
						folio= linea.substring(42, 49);
						secuenciaFolio= linea.substring(49, 52).trim();
						mesRemu=linea.substring(53, 55);
						añoRemu=Integer.parseInt(linea.substring(55, 59));
						rutemp=Integer.parseInt(linea.substring(125, 134).trim());
						dvRutemp=linea.substring(134, 135).charAt(0);
						razonSocial= CambiarCaracteres(linea.substring(85, 126).trim());
						direccion= CambiarCaracteres(linea.substring(140, 190).trim() + ", " + linea.substring(240, 270).trim());
						region= "";
						fechaGrati= linea.substring(52, 53).trim();
						break;
					case 8:
						tasa= Double.parseDouble(linea.substring(2, 7).replace(',', '.'));
						fechapago= linea.substring(42, 52);
						numeroPaginas= Integer.parseInt(linea.substring(15, 18).trim());
						break;
					case 9:
						registroTO= new DetalleCertificadoTO();
						if(fechaGrati.equals("2")){
							registroTO.setTipoNomina("G");
						}else{
							//equals("1")
							registroTO.setTipoNomina("R");
						}
						registroTO.setFolio(folio);
						registroTO.setSecuenciaFolio(secuenciaFolio);
						registroTO.setEntidad(entidad);
						registroTO.setTipoEntidad("M");
						registroTO.setMesRemu(mesRemu);
						registroTO.setAnioRemu(añoRemu);
						registroTO.setRutemp(rutemp);
						registroTO.setDvRutemp(dvRutemp);
						registroTO.setFechaPago(Integer.parseInt(fechapago.substring(6, 10) + fechapago.substring(3, 5) + fechapago.substring(0, 2)));
						{
							int rutafi= Integer.parseInt(linea.substring(5, 13).trim());
							char dvRutafi= linea.substring(13, 14).charAt(0);
							String apellidos= CambiarCaracteres(linea.substring(14, 44).trim());
							String nombres= CambiarCaracteres(linea.substring(44, 74).trim());
							int remuneracion= Integer.parseInt(linea.substring(77, 86).trim());
							int monto= (int)Math.round((remuneracion * tasa)/100);
							registroTO.setRutafi(rutafi);
							registroTO.setDvRutafi(dvRutafi);
							registroTO.setRemuneracion(remuneracion);
							registroTO.setMonto(monto);
						}
						retorno.add(registroTO);
						break;
					case 30: //Fin MUTUAL
						numeroPaginas--;
						if(numeroPaginas==0 || codregistro==codregistro_old){
							entidad="";
							folio="";
							secuenciaFolio="";
							mesRemu="";
							fechapago="";
							fechaGrati="";
							añoRemu=0;
							rutemp=0;
							dvRutemp=' ';
							razonSocial="";
							direccion="";
							region="";
							tasa=0.0;
						}
						break;
					case 1: //Inicio AFP
						entidad= linea.substring(2, 42).trim();
						folio= linea.substring(42, 49);
						secuenciaFolio= linea.substring(49, 52).trim();
						rutemp=Integer.parseInt(linea.substring(54, 62).trim());
						dvRutemp=linea.substring(62, 63).charAt(0);
						razonSocial= CambiarCaracteres(linea.substring(63, 103).trim());
						direccion= CambiarCaracteres(linea.substring(108, 158).trim() + ", " + linea.substring(228, 258).trim());
						region= linea.substring(288, 290).trim();
						break;
					case 2:
						mesRemu=linea.substring(63, 65);
						añoRemu=Integer.parseInt(linea.substring(66, 70));
						fechapago= linea.substring(118, 128);
						fechaGrati= linea.substring(93, 103).trim();
						break;
					case 3:{
						int[] monto= new int[3];
						String montoaux= linea.substring(82, 90).trim();
						if (!montoaux.equals("")){
							monto[0]= Integer.parseInt(montoaux);
						}
						montoaux= linea.substring(98, 106).trim();
						if (!montoaux.equals("")){
							monto[1]= Integer.parseInt(montoaux);
						}
						montoaux= linea.substring(90, 98).trim();
						if (!montoaux.equals("")){
							monto[2]= Integer.parseInt(montoaux);
						}
						cabeceraTO= new CabeceraCertificadoTO();
						for (int i = 0; i < 3; i++) {
							if(monto[i]>0){
								registroTO= new DetalleCertificadoTO();
								if(!(fechaGrati.equals("00/00/0") || fechaGrati.equals("00/00/0000"))){
									registroTO.setTipoNomina("G");
									cabeceraTO.setTipoNomina("G");
								}else{
									registroTO.setTipoNomina("R");
									cabeceraTO.setTipoNomina("R");
								}
								registroTO.setFolio(folio);
								registroTO.setSecuenciaFolio(secuenciaFolio);
								registroTO.setEntidad(entidad);
								if(i==0){
									registroTO.setTipoEntidad("P");
								}else if(i==1){
									//Cuenta2 Ahorro
									registroTO.setTipoEntidad("A");
								}else{
									//APV
									registroTO.setTipoEntidad("1");
								}
								registroTO.setMesRemu(mesRemu);
								registroTO.setAnioRemu(añoRemu);
								registroTO.setRutemp(rutemp);
								registroTO.setDvRutemp(dvRutemp);
								registroTO.setFechaPago(Integer.parseInt(fechapago.substring(6, 10) + fechapago.substring(3, 5) + fechapago.substring(0, 2)));
								{
									int rutafi= Integer.parseInt(linea.substring(5, 13).trim());
									char dvRutafi= linea.substring(13, 14).charAt(0);
									String apellidos= CambiarCaracteres(linea.substring(14, 44).trim());
									String nombres= CambiarCaracteres(linea.substring(44, 74).trim());
									int remuneracion= Integer.parseInt(linea.substring(75, 82).trim());
									//monto= Integer.parseInt(linea.substring(112, 119).trim());
									registroTO.setRutafi(rutafi);
									registroTO.setDvRutafi(dvRutafi);
									registroTO.setRemuneracion(remuneracion);
									registroTO.setMonto(monto[i]);
									
									if(i==0){
										//Set Cabecera
										cabeceraTO.setApellidos(apellidos);
										cabeceraTO.setConvenio(1);
										cabeceraTO.setDireccion(direccion);
										cabeceraTO.setDvRutafi(dvRutafi);
										cabeceraTO.setDvRutemp(dvRutemp);
										cabeceraTO.setFechaEmision(registroTO.getFechaPago());
										cabeceraTO.setHolding(0);
										cabeceraTO.setNombres(nombres);
										cabeceraTO.setPeriodo(Integer.parseInt(añoRemu + "" + mesRemu));
										cabeceraTO.setRazonSocial(razonSocial);
										cabeceraTO.setRegion(region);
										cabeceraTO.setRutafi(rutafi);
										cabeceraTO.setRutemp(rutemp);
										cabeceraTO.setSucursal("CASMAT");
									}
								}
								retorno.add(registroTO);
							}
						}
						cabeceras.add(cabeceraTO);
					}
						break;
					case 28: //FIn AFP
						if(codregistro==codregistro_old){
							entidad="";
							folio="";
							secuenciaFolio="";
							mesRemu="";
							fechapago="";
							fechaGrati="";
							añoRemu=0;
							rutemp=0;
							dvRutemp=' ';
							razonSocial="";
							direccion="";
							region="";
							tasa=0.0;
						}
						break;
					case 50: //Inicio TP
						entidad= linea.substring(2, 42).trim();
						folio= linea.substring(42, 49);
						secuenciaFolio= linea.substring(49, 52).trim();
						rutemp=Integer.parseInt(linea.substring(54, 62).trim());
						dvRutemp=linea.substring(62, 63).charAt(0);
						razonSocial= CambiarCaracteres(linea.substring(63, 103).trim());
						direccion= CambiarCaracteres(linea.substring(108, 158).trim() + ", " + linea.substring(228, 258).trim());
						region= linea.substring(288, 290).trim();
						break;
					case 51:
						mesRemu=linea.substring(63, 65);
						añoRemu=Integer.parseInt(linea.substring(66, 70));
						fechapago= linea.substring(118, 128);
						fechaGrati= linea.substring(93, 103).trim();
						break;
					case 52:{
						String montoaux=linea.substring(126, 134).trim();
						int monto=0;
						if (!montoaux.equals("")){
							monto= Integer.parseInt(montoaux);
						}
						cabeceraTO= new CabeceraCertificadoTO();
						if(monto>0){
							registroTO= new DetalleCertificadoTO();
							if(!(fechaGrati.equals("00/00/0") || fechaGrati.equals("00/00/0000"))){
								registroTO.setTipoNomina("G");
								cabeceraTO.setTipoNomina("G");
							}else{
								registroTO.setTipoNomina("R");
								cabeceraTO.setTipoNomina("R");
							}
								
							registroTO.setFolio(folio);
							registroTO.setSecuenciaFolio(secuenciaFolio);
							registroTO.setEntidad(entidad);
							registroTO.setTipoEntidad("K");
							registroTO.setMesRemu(mesRemu);
							registroTO.setAnioRemu(añoRemu);
							registroTO.setRutemp(rutemp);
							registroTO.setDvRutemp(dvRutemp);
							registroTO.setFechaPago(Integer.parseInt(fechapago.substring(6, 10) + fechapago.substring(3, 5) + fechapago.substring(0, 2)));
							
							int rutafi= Integer.parseInt(linea.substring(5, 13).trim());
							char dvRutafi= linea.substring(13, 14).charAt(0);
							int remuneracion= Integer.parseInt(linea.substring(115, 123).trim());
							
							registroTO.setRutafi(rutafi);
							registroTO.setDvRutafi(dvRutafi);
							String apellidos= CambiarCaracteres(linea.substring(14, 44).trim());
							String nombres= CambiarCaracteres(linea.substring(44, 74).trim());
							registroTO.setRemuneracion(remuneracion);
							registroTO.setMonto(monto);
							//Set Cabecera
							cabeceraTO.setApellidos(apellidos);
							cabeceraTO.setConvenio(1);
							cabeceraTO.setDireccion(direccion);
							cabeceraTO.setDvRutafi(dvRutafi);
							cabeceraTO.setDvRutemp(dvRutemp);
							cabeceraTO.setFechaEmision(registroTO.getFechaPago());
							cabeceraTO.setHolding(0);
							cabeceraTO.setNombres(nombres);
							cabeceraTO.setPeriodo(Integer.parseInt(añoRemu + "" + mesRemu));
							cabeceraTO.setRazonSocial(razonSocial);
							cabeceraTO.setRegion(region);
							cabeceraTO.setRutafi(rutafi);
							cabeceraTO.setRutemp(rutemp);
							cabeceraTO.setSucursal("CASMAT");
							retorno.add(registroTO);
							cabeceras.add(cabeceraTO);
						}
					}
					break;
					case 53: //FIN TP
						if(codregistro==codregistro_old){
							entidad="";
							folio="";
							secuenciaFolio="";
							mesRemu="";
							fechapago="";
							fechaGrati="";
							añoRemu=0;
							rutemp=0;
							dvRutemp=' ';
							razonSocial="";
							direccion="";
							region="";
							tasa=0.0;
						}
						break;
					case 4: //Inicio ISAPRE
						entidad= linea.substring(2, 42).trim();
						folio= linea.substring(42, 49);
						secuenciaFolio= linea.substring(49, 52).trim();
						rutemp=Integer.parseInt(linea.substring(102, 110).trim());
						dvRutemp=linea.substring(110, 111).charAt(0);
						razonSocial= CambiarCaracteres(linea.substring(61, 101).trim());
						direccion= CambiarCaracteres(linea.substring(111, 161).trim() + ", " + linea.substring(211, 241).trim());
						region= linea.substring(271, 273).trim();
						break;
					case 5:
						mesRemu=linea.substring(92, 94);
						añoRemu=Integer.parseInt(linea.substring(95, 99));
						fechapago= linea.substring(77, 87);
						fechaGrati= linea.substring(106, 113).trim();
						break;
					case 6:{
						String montoaux=linea.substring(112, 119).trim();
						int monto=0;
						if (!montoaux.equals("")){
							monto= Integer.parseInt(montoaux);
						}
						cabeceraTO= new CabeceraCertificadoTO();
						if(monto>0){
							registroTO= new DetalleCertificadoTO();
							if(!fechaGrati.equals("")){
								registroTO.setTipoNomina("G");
								cabeceraTO.setTipoNomina("G");
							}else{
								registroTO.setTipoNomina("R");
								cabeceraTO.setTipoNomina("R");
							}
								
							registroTO.setFolio(folio);
							registroTO.setSecuenciaFolio(secuenciaFolio);
							registroTO.setEntidad(entidad);
							registroTO.setTipoEntidad("S");
							registroTO.setMesRemu(mesRemu);
							registroTO.setAnioRemu(añoRemu);
							registroTO.setRutemp(rutemp);
							registroTO.setDvRutemp(dvRutemp);
							registroTO.setFechaPago(Integer.parseInt(fechapago.substring(6, 10) + fechapago.substring(3, 5) + fechapago.substring(0, 2)));
							int rutafi= Integer.parseInt(linea.substring(5, 13).trim());
							char dvRutafi= linea.substring(13, 14).charAt(0);
							int remuneracion= Integer.parseInt(linea.substring(75, 82).trim());
							//monto= Integer.parseInt(linea.substring(112, 119).trim());
							registroTO.setRutafi(rutafi);
							registroTO.setDvRutafi(dvRutafi);
							String apellidos= CambiarCaracteres(linea.substring(14, 44).trim());
							String nombres= CambiarCaracteres(linea.substring(44, 74).trim());
							registroTO.setRemuneracion(remuneracion);
							registroTO.setMonto(monto);
							//Set Cabecera
							cabeceraTO.setApellidos(apellidos);
							cabeceraTO.setConvenio(1);
							cabeceraTO.setDireccion(direccion);
							cabeceraTO.setDvRutafi(dvRutafi);
							cabeceraTO.setDvRutemp(dvRutemp);
							cabeceraTO.setFechaEmision(registroTO.getFechaPago());
							cabeceraTO.setHolding(0);
							cabeceraTO.setNombres(nombres);
							cabeceraTO.setPeriodo(Integer.parseInt(añoRemu + "" + mesRemu));
							cabeceraTO.setRazonSocial(razonSocial);
							cabeceraTO.setRegion(region);
							cabeceraTO.setRutafi(rutafi);
							cabeceraTO.setRutemp(rutemp);
							cabeceraTO.setSucursal("CASMAT");
							retorno.add(registroTO);
							cabeceras.add(cabeceraTO);
						}
					}
					break;
					case 29: //FIN ISAPRE
						if(codregistro==codregistro_old){
							entidad="";
							folio="";
							secuenciaFolio="";
							mesRemu="";
							fechapago="";
							fechaGrati="";
							añoRemu=0;
							rutemp=0;
							dvRutemp=' ';
							razonSocial="";
							direccion="";
							region="";
							tasa=0.0;
						}
						break;
					default:
						System.out.println(">>> Codigo registro no mapeado:" + codregistro);
						break;
					}
					codregistro_old=codregistro;
				}
			}
			f1.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private String[] getAñoMes(String fechapago) {
		// fecha formato dd/mm/aaaa
		String[] salida= new String[2];
		String[] periodo= fechapago.split("/");
		String año= periodo[2];
		String mes= periodo[1];
		if(mes.equals("01")){
			salida[0]= String.valueOf(Integer.parseInt(año)-1);
			salida[1]= "12";
		}else{
			salida[0]= año;
			String aux= String.valueOf(Integer.parseInt(mes)-1);
			if(aux.length()==1){
				aux= "0" + aux;
			}
			salida[1]= aux;
		}
		return salida;
	}

	public boolean crearArchivoDetalle(String pathfile, List registros){
		OutputStream out;
		try{
			out = new FileOutputStream(pathfile);
			PrintStream flujo= new PrintStream(out);
			for (Iterator iter = registros.iterator(); iter.hasNext();) {
				DetalleCertificadoTO registro = (DetalleCertificadoTO) iter.next();
				String linea= registro.toString();
				flujo.println(linea);
			}
			flujo.close();
			out.close();
			return true;
		  } catch(Exception e) {
			System.out.println("CAI en crearArchivo()");
			e.printStackTrace();
			//throw new IOException();
			return false;
		}
	}
	
	
	public boolean crearArchivoCabecera(String pathfile, List registros){
		OutputStream out;
		try{
			out = new FileOutputStream(pathfile);
			PrintStream flujo= new PrintStream(out);
			for (Iterator iter = registros.iterator(); iter.hasNext();) {
				CabeceraCertificadoTO registro = (CabeceraCertificadoTO) iter.next();
				//Falta Consultar por Holding de Registro y ver si trabajador ya existe
				
				String linea= registro.toString();
				flujo.println(linea);
			}
			flujo.close();
			out.close();
			return true;
		  } catch(Exception e) {
			System.out.println("CAI en crearArchivo()");
			e.printStackTrace();
			//throw new IOException();
			return false;
		}
	}
	
	private String CambiarCaracteres( String texto ) {
		// texto -> double
  if(isMatchCharInvalid(texto)){
	  texto = texto.replace('á','a');
	  texto = texto.replace('é','e');
	  texto = texto.replace('í','i');
	  texto = texto.replace('ó','o');
	  texto = texto.replace('ú','u');

	  texto = texto.replace('à','a');
	  texto = texto.replace('è','e');
	  texto = texto.replace('ì','i');
	  texto = texto.replace('ò','o');
	  texto = texto.replace('ù','u');

	  texto = texto.replace('ä','a');
	  texto = texto.replace('ë','e');
	  texto = texto.replace('ï','i');
	  texto = texto.replace('ö','o');
	  texto = texto.replace('ü','u');

	  texto = texto.replace('â','a');
	  texto = texto.replace('ê','e');
	  texto = texto.replace('î','i');
	  texto = texto.replace('ô','o');
	  texto = texto.replace('û','u');

	  texto = texto.replace('Á','A');
	  texto = texto.replace('É','E');
	  texto = texto.replace('Í','I');
	  texto = texto.replace('Ó','O');
	  texto = texto.replace('Ú','U');

	  texto = texto.replace('À','A');
	  texto = texto.replace('È','E');
	  texto = texto.replace('Ì','I');
	  texto = texto.replace('Ò','O');
	  texto = texto.replace('Ù','U');

	  texto = texto.replace('Ä','A');
	  texto = texto.replace('Ë','E');
	  texto = texto.replace('Ï','I');
	  texto = texto.replace('Ö','O');
	  texto = texto.replace('Ü','U');

	  texto = texto.replace('Â','A');
	  texto = texto.replace('Ê','E');
	  texto = texto.replace('Î','I');
	  texto = texto.replace('Ô','O');
	  texto = texto.replace('Û','U');

	  texto = texto.replace('ñ','n');
	  texto = texto.replace('Ñ','N');

	  texto = texto.replace('\'',' ');
	  texto = texto.replace('/', ' ');
	  texto = texto.replace('£','u'); 
	  texto = texto.replace('¡','i');
	  texto = texto.replace('ª',' ');
	  texto = texto.replace('º',' ');
	  texto = texto.replace('¥','N');
	  texto = texto.replace('°','.');
	  texto = texto.replace('±','N');
	  texto = texto.replace('#','n');
	  texto = texto.replace('Ð','n');
	  texto = texto.replace('¾','n');
	  texto = texto.replace('µ','n');
	  texto = texto.replace('|','I');
	  texto = texto.replace(';',' ');
	  texto = texto.replace('&','y');
	  texto = texto.replace('~',' ');		
	  texto = texto.replace('(',' ');		
	  texto = texto.replace(')',' ');				
	  texto = texto.replace('´',' ');
	  texto = texto.replace('¤','N');
	  texto = texto.replace('š','U');			
	  texto = texto.replace('<','N');
	  texto = texto.replace('>','N');
	  texto = texto.replace('¢','o');
	  texto = texto.replace('£','u');
	  
	  texto= matchReplace(texto);
  }
  return texto;
 }

public static boolean isMatchCharInvalid(String entrada){
  Pattern patron = Pattern.compile("[^A-Za-z _.,-]");
  Matcher encaja = patron.matcher(entrada);
  if(!encaja.find()){
      return false;
  }else{
  	return true;
  }
}

public static String matchReplace(String entrada){
  Pattern patron = Pattern.compile("[^A-Za-z _.-]");
  Matcher encaja = patron.matcher(entrada);
  if(!encaja.find()){
      return entrada;
  }else{
  	String salida= encaja.replaceAll("");
  	return salida;
  }
}
}
