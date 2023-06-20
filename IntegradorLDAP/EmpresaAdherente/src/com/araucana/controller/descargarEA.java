/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.araucana.controller;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.araucana.dao.BitacoraDAO;
import com.araucana.entity.BitacoraVo;
import com.araucana.service.Archivos_unificar;

import cl.araucana.core.util.Rut;

import java.util.Date;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author TestGroup
 */
public class descargarEA {
	private static final Logger log = Logger.getLogger(Archivos_unificar.class);
	private static DateFormat f_t = new SimpleDateFormat("HHmmss.SSS");
	private static DateFormat f_d = new SimpleDateFormat("yyyyMMdd");
	
    List<String> fileList;

    public static String gloRutaProyecto = "";
    public static String gloRutaProyecto_NC = ""; //1	
    public static String gloRutaProyecto_CF = ""; //2
    public static String gloRutaProyecto_NL = ""; //3
    public static String gloRutaProyecto_AT = ""; //4

    public static String gloPeriodo = "";
    public static String gloRUTusuario = "";
    public static String gloCarpetaZipear = "";
    public static String gloCarpetaZipeada = "";
    public static String gloFormato = "";
    public static String gloFormato_NC = "";
    public static String gloFormato_NL = "";
    public static String gloFormato_CF = "";
    
    public static int gloRetardo_Limpiar_Temporal = 10000;
    public static int gloTotCarpetasEspeciales = 0;
    
    public static List<String> gloListaComas = new ArrayList<String>();

    //public static String OUTPUT_ZIP_FILE = "";
    //public static String SOURCE_FOLDER = "";
    public static void main(String[] args) throws IOException, InterruptedException {
        descargarEA appZip = new descargarEA();

        Map<String, String> mapEmpresasEA = new TreeMap<String, String>();
        mapEmpresasEA.put("088506100", "SSS");
        mapEmpresasEA.put("088518600", "SSS");
        mapEmpresasEA.put("088519100", "SSS");
        mapEmpresasEA.put("088525600", "SSS");
        mapEmpresasEA.put("088528700", "SSS");

        subProcesarDescarga("SSSS", "S", mapEmpresasEA, "202201", "1234567890", "txt");
    }

    public static String subProcesarDescarga(String OrganizaInfo, String xHayNominaMarcada ,Map<String, String> mapEmpresasEA, String xPer, String xRUT, String xFormato) throws IOException, InterruptedException {
        gloPeriodo = xPer;
        gloRUTusuario = xRUT;
        gloFormato = xFormato;

        subObtenerRutas();
        subGenerarDescargas(OrganizaInfo, xHayNominaMarcada, mapEmpresasEA);

        return gloCarpetaZipeada;
    }

    public static void subObtenerRutas() throws IOException {
        Rutas funRutas = new Rutas();

        gloRutaProyecto = funRutas.funObtenerRuta("0");
        gloRutaProyecto_NC = funRutas.funObtenerRuta("1"); //Ruta Destino Nomina Cr√©dito
        gloRutaProyecto_CF = funRutas.funObtenerRuta("2"); //Ruta Destino Nominas cargas trabajadores
        gloRutaProyecto_NL = funRutas.funObtenerRuta("3"); //Ruta Destino Nominas Leasing   
        gloRutaProyecto_AT = funRutas.funObtenerRuta("4"); //Ruta Destino Nominas anexos trabajadores
        
        gloRetardo_Limpiar_Temporal = Integer.parseInt(funRutas.funObtenerRuta("5")); //Retardo_Limpiar_Temporal

        log.info("gloRutaProyecto: " + gloRutaProyecto);
        log.info("gloRutaProyecto_NC: " + gloRutaProyecto_NC);
        log.info("gloRutaProyecto_CF: " + gloRutaProyecto_CF);
        log.info("gloRutaProyecto_NL: " + gloRutaProyecto_NL);
        log.info("gloRutaProyecto_AT: " + gloRutaProyecto_AT);
    }

    public static void subGenerarDescargas(String OrganizaInfo, String xHayNominaMarcada, Map<String, String> mapEmpresasEA) throws IOException, InterruptedException {
    	String xSufijo = "", xHora = "", xFecha = "";
    	
        //Carpeta Periodo Destino
        File dirPeriodoDestino = new File(gloRutaProyecto + "\\" + "zDescargaEA" + "\\" + gloPeriodo);

        if (!dirPeriodoDestino.exists()) {
            if (dirPeriodoDestino.mkdir()) {
                //log.info("*** Carpeta Periodo creada: " + dirPeriodoDestino);
            } else {
                //log.info("*** Error al crear Carpeta Periodo: " + dirPeriodoDestino);
            }
        }
        //Fin
        
        //Obtener Fecha y Hora
        xHora = f_t.format(new Date());
	    xFecha = f_d.format(new Date());     
        
        xSufijo = xFecha + "_" + xHora;
        //
        
        //Carpeta Periodo + RUT
        String dirPeriodoDestinoRUT_string = gloRutaProyecto + "\\" + "zDescargaEA" + "\\" + gloPeriodo + "\\" + gloRUTusuario + "_" + xSufijo;
        File dirPeriodoDestinoRUT = new File(dirPeriodoDestinoRUT_string);

        if ((gloPeriodo.trim() != "") && (xHayNominaMarcada == "S"))  {
	        if (!dirPeriodoDestinoRUT.exists()) {
	            if (dirPeriodoDestinoRUT.mkdir()) {
	                //log.info("*** Carpeta Periodo+RUT creada: " + dirPeriodoDestinoRUT);
	            } else {
	                //log.info("*** Error al crear Carpeta Periodo+RUT: " + dirPeriodoDestinoRUT);
	            }
	        }
        }
        //Fin

       subDescargaNominas(dirPeriodoDestinoRUT_string, OrganizaInfo, mapEmpresasEA, xSufijo);
    }

    public static String subDescargaNominas(String xRutaDescarga, String OrganizaInfo, Map<String, String> mapEmpresasEA, String xSufijo) throws IOException, InterruptedException {
        String xRUT_ea = "";
        File xArchivoDescarga = new File("");
        String lineaLeida = "";
        String xRutaDescarga_aux = "";
        String xRutaDescarga_aux1 = "";
        String xRutaDescarga_aux2 = "";
        String xCrearDescarga = "";
        int xCuentaDescarga = 0; int xCuentaDescarga1 = 0; int xCuentaDescarga2 = 0; int xCuentaDescarga3 = 0; int xCuentaDescarga4 = 0;
        BufferedWriter escribeDescarga = null;
        String xTipoDescarga = "";
        String xTipoDescargaExcel = "";
        String xTipoDescarga2 = "";
        String xCarpetaDescarga = "";
        String xSeguir = "";
        String xCrearCarpetaNomina = "";
        String xHay = ""; String xSeleccion = "";
        int kk = 0; int nn1 = 0; int nn2 = 0;
        String SeparadaPorEmpresas = "";
        String SeparadaNC = ""; String SeparadaNL = ""; String SeparadaCF = "";
        String xFormato = ""; String xFormatoOriginal = "";
        String xTipoCorto = "";
        
        gloTotCarpetasEspeciales = 0;
        
        log.info("Iniciamos descarga: " + gloFormato);
        log.info("Retardo para limpiar Carpeta temporal (Milisegundos): " + gloRetardo_Limpiar_Temporal + " (1000 milisegundos es 1 segundo).");

        gloFormato_NC = "N";
        gloFormato_NL = "N";
        gloFormato_CF = "N";

        SeparadaPorEmpresas = OrganizaInfo.substring(0, 1);
        SeparadaNC = OrganizaInfo.substring(1, 2);
        SeparadaNL = OrganizaInfo.substring(2, 3);
        SeparadaCF = OrganizaInfo.substring(3, 4);
        String separador=",";
        xFormato = gloFormato;
        xFormatoOriginal = gloFormato;
        if (xFormato.equals("xls")) {
        	xFormato = "txt";
        }
        if(xFormato.equals("csv2")){
        	separador=";";
        	xFormato="csv";
        }
        
        //xLocal = SeparadaPorEmpresas;
        xRutaDescarga_aux = xRutaDescarga;

        gloCarpetaZipear = gloRutaProyecto + "\\zDescargaEA\\" + gloPeriodo + "\\" + gloRUTusuario + "_" + xSufijo;
        gloCarpetaZipeada = gloCarpetaZipear + "\\Descarga_EA.zip";

        //Borra todo el contenido de la Carpeta
        File dirPeriodoDestino1 = new File(gloCarpetaZipear);
        if (dirPeriodoDestino1.exists()) {
            File listFile1[] = dirPeriodoDestino1.listFiles();

            if (listFile1.length > 0) {
            	Thread.sleep(gloRetardo_Limpiar_Temporal);
            }
        	
        	subBorrarDescargaAnterior(gloCarpetaZipear);
        }
        //Fin

        //Eliminar ZIP anterior
        File xArchivoDescargaZIP = new File(xRutaDescarga + "\\Descarga_EA.zip");

        if (xArchivoDescargaZIP.exists()) {
            xArchivoDescargaZIP.delete();
        }
        //Fin

        Iterator it = mapEmpresasEA.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            //log.info("Clave: " + key + " -> Valor: " + mapEmpresasEA.get(key));

            nn1 = 0;
            nn2 = 0;
            xRUT_ea = key;
            xSeleccion = mapEmpresasEA.get(key);
            xRutaDescarga = xRutaDescarga_aux;
            BitacoraVo bitacoraVO= new BitacoraVo();
            log.info("Llenando datos bitacora periodo: " + gloPeriodo + ", usuario: " + gloRUTusuario + ", RutEmpresa: " + key + ", formato: " + gloFormato );
            bitacoraVO.setPeriodo(Integer.parseInt(gloPeriodo));
            bitacoraVO.setRutusu(Integer.parseInt(gloRUTusuario.split("-")[0]));
            bitacoraVO.setDvrutusu(gloRUTusuario.split("-")[1]);
            Rut rutemp= new Rut(key);
            bitacoraVO.setRutemp(Integer.parseInt(key));
            bitacoraVO.setDvrutemp(""+rutemp.getDV());
            bitacoraVO.setFormato(gloFormato);
           
            
            for (int ii = 1; ii <= 4; ii++) {
                xSeguir = "N";
                xCrearCarpetaNomina = "N";

                if (ii > 2) {
                    xHay = xSeleccion.substring(3 - 1, 3);
                } else {
                    xHay = xSeleccion.substring(ii - 1, ii);
                }
                
                //log.info(xHay);
               
                switch (ii) {
                    case 1:
                        if (xHay.equals("S")) {
                            xTipoDescarga = "\\nominas_credito." + xFormato;
                            xTipoDescargaExcel = "\\nominas_credito.xls";
                            xTipoDescarga2 = "\\nominas_credito";
                            xCarpetaDescarga = gloRutaProyecto_NC;
                            xTipoCorto = "NC";

                            if (SeparadaNC.equals("S")) {
                                xCrearCarpetaNomina = "S";
                            }
                            gloFormato_NC = "S";
                            xCuentaDescarga1 += 1;
                            xCuentaDescarga = xCuentaDescarga1;

                            xSeguir = "S";
                            bitacoraVO.setNomina(xTipoCorto);
                            log.info("Grabando datos bitacora Tipo NÛmina: " + xTipoCorto);
                            BitacoraDAO bitaDao= new BitacoraDAO();
                            bitaDao.insertBitacora(bitacoraVO);
                        }
                        break;
                    case 2:
                        if (xHay.equals("S")) {
                            xTipoDescarga = "\\nominas_ahorro." + xFormato;
                            xTipoDescargaExcel = "\\nominas_ahorro.xls";
                            xTipoDescarga2 = "\\nominas_ahorro";
                            xCarpetaDescarga = gloRutaProyecto_NL;
                            xTipoCorto = "NL";

                            if (SeparadaNL.equals("S")) {
                                xCrearCarpetaNomina = "S";
                            }
                            gloFormato_NL = "S";
                            xCuentaDescarga2 += 1;
                            xCuentaDescarga = xCuentaDescarga2;

                            xSeguir = "S";
                            bitacoraVO.setNomina(xTipoCorto);
                            log.info("Grabando datos bitacora Tipo NÛmina: " + xTipoCorto);
                            BitacoraDAO bitaDao= new BitacoraDAO();
                            bitaDao.insertBitacora(bitacoraVO);
                        }
                        break;
                    case 3:
                        if (xHay.equals("S")) {
                            xTipoDescarga = "\\cargas_trabajadores." + xFormato;
                            xTipoDescargaExcel = "\\cargas_trabajadores.xls";
                            xTipoDescarga2 = "\\cargas_trabajadores";
                            xCarpetaDescarga = gloRutaProyecto_CF;
                            xTipoCorto = "CF";

                            if (SeparadaCF.equals("S")) {
                                xCrearCarpetaNomina = "S";
                            }
                            gloFormato_CF = "S";
                            xCuentaDescarga3 += 1;
                            xCuentaDescarga = xCuentaDescarga3;

                            xSeguir = "S";
                            bitacoraVO.setNomina(xTipoCorto);
                            log.info("Grabando datos bitacora Tipo NÛmina: " + xTipoCorto);
                            BitacoraDAO bitaDao= new BitacoraDAO();
                            bitaDao.insertBitacora(bitacoraVO);
                        }
                        break;
                    case 4:
                        if (xHay.equals("S")) {
                            xTipoDescarga = "\\anexos_trabajadores." + xFormato;
                            xTipoDescargaExcel = "\\anexos_trabajadores.xls";
                            xTipoDescarga2 = "\\anexos_trabajadores";
                            xCarpetaDescarga = gloRutaProyecto_AT;
                            xTipoCorto = "AT";

                            if (SeparadaCF.equals("S")) {
                                xCrearCarpetaNomina = "S";
                            }
                            gloFormato_CF = "S";
                            xCuentaDescarga4 += 1;
                            xCuentaDescarga = xCuentaDescarga4;

                            xSeguir = "S";
                            bitacoraVO.setNomina(xTipoCorto);
                            log.info("Grabando datos bitacora Tipo NÛmina: " + xTipoCorto);
                            BitacoraDAO bitaDao= new BitacoraDAO();
                            bitaDao.insertBitacora(bitacoraVO);
                        }
                        break;
                    default:
                        break;
                }

                if (xSeguir == "S") {
                    if (xCuentaDescarga == 1) {
                        xCrearDescarga = "Si";
                    } else {
                        if (SeparadaPorEmpresas.equals("S")) {
                            xCrearDescarga = "Si";
                        } else {
                            xCrearDescarga = "No";
                        }
                    }                	
                	
                	log.info("Iniciamos proceso de descarga: " + xRutaDescarga);
                	
                    if (ii == 1) {
                    	gloTotCarpetasEspeciales = 0;
                        subEspeciales(xCarpetaDescarga + "\\" + gloPeriodo + "\\" + xRUT_ea, xRUT_ea, xRutaDescarga);
                    }

                    log.info("Formato a procesar: " + xFormato + " (Original: " + xFormatoOriginal + ")");
                    //25-11-22 j-factory se agrega lectura para csv pc
                    File dirPeriodoDestinoRUT_nc=null;

                    if((xFormato.equals("txt") || xFormato.equals("csv")) && separador.equals(",")) {
                    	dirPeriodoDestinoRUT_nc = new File(xCarpetaDescarga + "\\" + gloPeriodo + "\\" + xRUT_ea + "\\1." + xFormato);
                    }else if(xFormato.equals("csv") && separador.equals(";")){
                    	dirPeriodoDestinoRUT_nc = new File(xCarpetaDescarga + "\\" + gloPeriodo + "\\" + xRUT_ea + "\\1PC." + xFormato);
                    }

                    if (dirPeriodoDestinoRUT_nc.exists()) { //Verificar si archivo a Descargar existe
                        if (SeparadaPorEmpresas.equals("S")) {
                            nn1 += 1;
                            xArchivoDescarga = new File(xRutaDescarga + xTipoDescarga);

                            if (nn1 == 1) {
                                xRutaDescarga_aux1 = xRutaDescarga;
                                xRutaDescarga = xRutaDescarga + "\\" + xRUT_ea;
                            } else {
                                xRutaDescarga = xRutaDescarga_aux1 + "\\" + xRUT_ea;
                            }
                            log.info("3: " + xRutaDescarga);
                            File dirRutaDescarga = new File(xRutaDescarga);
                            //el nn1 > 1 es porque para la 2da y 3ra nomina la Carpera Empresa ya esta creada.
                            if (!dirRutaDescarga.exists() || nn1 > 1) {
                                if (dirRutaDescarga.mkdir() || nn1 > 1) {
                                    //log.info("*** RUT Empresa Adherente creada: " + dirRutaDescarga);

                                    if (xCrearCarpetaNomina == "S") {
                                        nn2 += 1;

                                        if (nn2 == 1) {
                                            xRutaDescarga_aux2 = xRutaDescarga;
                                            xRutaDescarga = xRutaDescarga + "\\" + xTipoDescarga2;
                                        } else {
                                            xRutaDescarga = xRutaDescarga_aux2 + "\\" + xTipoDescarga2;
                                        }

                                        log.info("1: " + xRutaDescarga);

                                        //log.info(xRutaDescarga);
                                        File dirRutaDescarga2 = new File(xRutaDescarga);
                                        if (!dirRutaDescarga2.exists()) {
                                            if (dirRutaDescarga2.mkdir()) {
                                                //log.info("*** Carpeta Nomina creada: " + dirRutaDescarga2);
                                            } else {
                                                //log.info("*** Error al crear Carpeta Nomina: " + dirRutaDescarga2);
                                            }
                                        }
                                    }
                                } else {
                                    //log.info("*** Error al crear Empresa Adherente: " + dirRutaDescarga);
                                }
                            }
                        }

                        if (xFormatoOriginal.equals("xls")) {                        	
	                        if (xCrearDescarga.equals("Si")) {
	                        	log.info("Crear el Excel primero con el Encabezado: " + xRutaDescarga + xTipoDescargaExcel);
	                        	subCreaExcel(dirPeriodoDestinoRUT_nc, xRutaDescarga + xTipoDescargaExcel, ii, "S", xTipoCorto);
	                        }                
	                        
	                        log.info("Agregar registros en el Excel ya creado: " + xRutaDescarga + xTipoDescargaExcel);
                        	subCreaExcel(dirPeriodoDestinoRUT_nc, xRutaDescarga + xTipoDescargaExcel, ii, "N", xTipoCorto);
                        } else {
	                        log.info("2: " + xRutaDescarga);
	                        xArchivoDescarga = new File(xRutaDescarga + xTipoDescarga);
	
	                        if (xCrearDescarga.equals("Si")) {
	                            xArchivoDescarga.createNewFile(); //Crear archivo con la Descarga
	                        }
	
	                        escribeDescarga = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(xArchivoDescarga, true), "iso-8859-1"));
	
	                        BufferedReader archivoLeer = new BufferedReader(new FileReader(dirPeriodoDestinoRUT_nc));
	                        
	                        //Si es CSV, colocar los titulos
	                        if (xFormatoOriginal.equals("csv") || xFormatoOriginal.equals("csv2")) {    
		                        if (xCrearDescarga.equals("Si")) {
		                        	 //25-11-22 j-factory se agrega llamado con separador
		                        	escribeDescarga.write(funTituloCSV(ii, separador) + "\r\n");
		                        }
	                        }
	                        //Fin
	                        
	                        while ((lineaLeida = archivoLeer.readLine()) != null) {
	                            if (lineaLeida.length() != 0) {
	                                escribeDescarga.write(lineaLeida + "\r\n");
	                            }
	                        }
	
	                        escribeDescarga.close();
	                        archivoLeer.close();
                        }

                        kk += 1;
                    }
                }
            }
        }
        //Fin

        if ((kk > 0) || (gloTotCarpetasEspeciales > 0)) {
        	if (kk > 0) {
        		subFormatos(gloCarpetaZipear, gloFormato_NC, gloFormato_NL, gloFormato_CF);
        	}
        	
            subGenerarZIP();
            log.info("Descarga OK! (Archivos: " + kk + " / Carpetas especiales: " + gloTotCarpetasEspeciales + ")");
        } else {
        	log.info("No Hay nada para Descargar!");
        	gloCarpetaZipeada="";
        }  
        
		return gloCarpetaZipeada;
    }
    
    public static void subCreaExcel(File xArchivoLeer, String xDirFormato, int xFmto, String xCrea, String xTipoCorto) throws IOException {
        String xArchivoFormato = "";
        String xLeerFormato = "";

        log.info("Dir Formato: " + xDirFormato);
        File dirFormato = new File(xDirFormato);

        //log.info("*** Carpeta 'formato' creada: " + dirFormato);
        if (xFmto == 1) {
            xArchivoFormato = xDirFormato; //nomina_credito.xls
            xLeerFormato = gloRutaProyecto + "\\zFormatosExcel\\nomina_credito.txt";

            subEscribeExcel(xArchivoLeer, xArchivoFormato, xLeerFormato, xCrea, xTipoCorto);
        }
        
        if (xFmto == 2) {
            xArchivoFormato = xDirFormato; //nomina_ahorro.xls
            xLeerFormato = gloRutaProyecto + "\\zFormatosExcel\\nomina_ahorro.txt";

            subEscribeExcel(xArchivoLeer, xArchivoFormato, xLeerFormato, xCrea, xTipoCorto);
        }
        
        if (xFmto == 3) {
            xArchivoFormato = xDirFormato; //cargas_familiares.xls
            xLeerFormato = gloRutaProyecto + "\\zFormatosExcel\\cargas_familiares.txt";

            subEscribeExcel(xArchivoLeer, xArchivoFormato, xLeerFormato, xCrea, xTipoCorto);
        }   	
        
        if (xFmto == 4) {
            xArchivoFormato = xDirFormato; //anexo_trabajadores.xls
            xLeerFormato = gloRutaProyecto + "\\zFormatosExcel\\anexo_trabajadores.txt";

            subEscribeExcel(xArchivoLeer, xArchivoFormato, xLeerFormato, xCrea, xTipoCorto);
        }        
    }
    
    //25-11-22 j-factory se agrega separador
    public static String funTituloCSV(int xFmto, String separador) throws IOException {
    	String lineaLeida = "", lineaTitulo = "", xLeerFormato = "";
    	int ii = 0;
    	
        //Llenar columnas Encabezado para CSV
    	
        switch (xFmto) {
	        case 1:
	        	xLeerFormato = gloRutaProyecto + "\\zFormatosExcel\\nomina_credito.txt";
	            break;
	        case 2:
	        	xLeerFormato = gloRutaProyecto + "\\zFormatosExcel\\nomina_ahorro.txt";
	            break;
	        case 3:
	        	xLeerFormato = gloRutaProyecto + "\\zFormatosExcel\\cargas_familiares.txt";
	            break;
	        case 4:
	        	xLeerFormato = gloRutaProyecto + "\\zFormatosExcel\\anexo_trabajadores.txt";
	            break;
	        default:
	            break;
        }        
        
        BufferedReader archivoLeer5 = new BufferedReader(new FileReader(xLeerFormato));
    	
        while ((lineaLeida = archivoLeer5.readLine()) != null) {
            if (lineaLeida.length() != 0) {
            	ii += 1;
            	
            	if (ii == 1) {
            		lineaTitulo = lineaLeida;
            	} else {
            		lineaTitulo = lineaTitulo + separador + lineaLeida;
            	}
            }
        }
        archivoLeer5.close();
        //Fin
        
        return lineaTitulo;
    }
    
    public static void subEscribeExcel(File xArchivoLeer, String xArchivoFormato, String xLeerFormato, String xCrea, String xTipoCorto) throws IOException {
    	File xArchivoDescarga = new File("");
        BufferedWriter escribeFormato = null;
        String lineaLeida = ""; String lineaLeidaOK = ""; int xLeidos = 0;
        int nn = 0, jj = 0, pp0 = 0, pp1 = 0, pp2 = 0, pp_Ant = 0, largoLinea = 0, largoLista = 0, xFila = 0, kk1 = 0;
        String xTextoCorto1 = "";
        
    	try {
	    	xFila = 0;
	    	String xFileName = "";     
	        
	    	xFileName = xArchivoFormato;
	        xArchivoDescarga = new File(xArchivoFormato);
	
	        if (xCrea == "S") {
	        	log.info("Inicio - Crear Fila encabezados Excel: " + xFileName);
	        	log.info("Inicio - Archivo con encabezados Excel: " + xLeerFormato);
	        	
	        	xFila = 0;
	        	
	            HSSFWorkbook workbook = new HSSFWorkbook();
	            HSSFSheet sheet = workbook.createSheet("Hoja 1");
	            HSSFRow row = sheet.createRow(xFila);
	            
		        BufferedReader archivoLeer = new BufferedReader(new FileReader(xLeerFormato));
		
		        //Contar total de columnas del Encabezado
		        while ((lineaLeida = archivoLeer.readLine()) != null) {
		            if (lineaLeida.length() != 0) {
		            	xLeidos += 1;
		            }
		        }
		        archivoLeer.close();
		        
		        if (xLeidos > 0) {
			        String[] table1 = new String[xLeidos];
			        
			        //Llenar columnas Encabezado para el Excel
			        kk1 = -1;
			        BufferedReader archivoLeer2 = new BufferedReader(new FileReader(xLeerFormato));
			    	
			        while ((lineaLeida = archivoLeer2.readLine()) != null) {
			            if (lineaLeida.length() != 0) {
			            	kk1 = kk1 + 1;
			            	table1[kk1] = lineaLeida;
			            }
			        }
			        archivoLeer2.close();
			        //Fin
			        
		            for (int ii = 0; ii < table1.length; ii++) {
		                HSSFCell cell = row.createCell((short) ii);
		                cell.getStringCellValue().getBytes(Charset.forName("iso-8859-1"));
		                cell.setCellValue(table1[ii]);
		            }
			        
			        if (xLeidos > 0) {	        	
			            FileOutputStream fos = null;
			            File file;
		
			            file = new File(xFileName);
			            fos = new FileOutputStream(file);
			            workbook.write(fos);
			            
			            fos.close();	        	
			        }
		        } else {
		        	log.info("Que raro - Archivo con encabezados Excel no tiene registros: " + xLeerFormato);
		        }
		        
		        log.info("Fin - Crear Fila encabezados Excel: " + xFileName);
	        } else {
	        	log.info("Inicio - Agregar nominas en Excel: " + xFileName);
	        	
	        	gloListaComas = Archivos_unificar.subAsignarComas(xTipoCorto);
	        	largoLista = gloListaComas.size();
	        	String[] table2 = new String[largoLista];
	        	
	            InputStream inp2 = new FileInputStream(new File(xFileName));
	            HSSFWorkbook oldWorkbook2 = new HSSFWorkbook(inp2);
	            HSSFSheet oldSheet2 = oldWorkbook2.getSheetAt(0);
	            
	            FileOutputStream fos2 = null;
	            File file2;
	            file2 = new File(xFileName);      
	        	
	            BufferedReader archivoLeer = new BufferedReader(new FileReader(xArchivoLeer));
	
	            while ((lineaLeida = archivoLeer.readLine()) != null) {
	                if (lineaLeida.length() != 0) {
	                    HSSFRow oldRow2;
	                    xFila = oldSheet2.getLastRowNum() + 1;
	                    oldRow2 = oldSheet2.createRow(xFila);               	
	                	
						//Obtiene las columnas del Excel
	                	kk1 = -1;
						for (int kk = 0; kk < largoLista; kk++) {
							kk1 += 1;
							pp0 = Integer.parseInt(gloListaComas.get(kk));
	
							if (kk == 0) {
								pp1 = 0;
								pp2 = pp0;
								xTextoCorto1 = lineaLeida.substring(pp1, pp2);
								table2[kk1] = xTextoCorto1;
							} else {
								pp1 = pp_Ant;
								pp2 = pp0;
								xTextoCorto1 = lineaLeida.substring(pp1, pp2);
								table2[kk1] = xTextoCorto1;
							}
							
			                HSSFCell cell2 = oldRow2.createCell((short) kk1);
			                cell2.setCellValue(table2[kk1]);
	
							pp_Ant = pp2;
						} //Fin for             	
						
						fos2 = new FileOutputStream(file2);
						oldWorkbook2.write(fos2);
						log.info("Fila agregada en Excel: " + xFila);
	                }
	            }
	
	            archivoLeer.close();
	            fos2.close();
	            
	            log.info("Fin - Agregar nominas en Excel: " + xFileName);
	        }           
	    } catch (FileNotFoundException ex) {
	        log.info("Error de filenotfound: " + ex.getMessage());
	    } catch (IOException ex) {
	        log.info("Error de IOException: " + ex.getMessage());
	    }        
    }

    //Eliminar Carpetas o Archivos de la Descarga anterior
    public static void subBorrarDescargaAnterior(String xCarpeta) {
        File dirPeriodoDestino = new File(xCarpeta);

        if (dirPeriodoDestino.exists()) {
            File listFile[] = dirPeriodoDestino.listFiles();

            for (int ii = 0; ii < listFile.length; ii++) {
                if (listFile[ii].isDirectory()) {
                    //Primero: Eliminar los archivos que contiene la Carpeta
                    subEliminar(listFile[ii]);

                    //Segundo: Eliminar Carpeta
                    if (listFile[ii].delete()) {
                        //Carpeta eliminada
                    } else {
                        //Error eliminar Carpeta
                    }
                } else {
                    if (listFile[ii].delete()) {
                        //Archivo eliminado
                    } else {
                        //Error eliminar Archivo
                    }
                }
            }
            
            log.info("Descarga anterior eliminada: " + xCarpeta);
        }
    }

    public static void subEliminar(File f) {
        if (f.listFiles().length > 0) {
            if (f.exists() && f.isDirectory()) { // determina si es un archivo o un directorio
                File delFile[] = f.listFiles();
                int i = f.listFiles().length;

                for (int j = 0; j < i; j++) {
                    if (delFile[j].isDirectory()) {
                        subEliminar(delFile[j]);
                    }

                    delFile[j].delete(); // eliminar un archivo
                }
            }
        }
    }

    //Metodos para Zipear
    descargarEA() {
        fileList = new ArrayList<String>();
    }

    public static void subGenerarZIP() {
        descargarEA appZip = new descargarEA();

        appZip.generateFileList(new File(gloCarpetaZipear));
        appZip.zipIt(gloCarpetaZipeada);
    }

    public void generateFileList(File node) {
        if (node.isFile()) {
            log.info("isFile : " + node.getName());
            fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
        }

        if (node.isDirectory()) {
            log.info("isDirectory : " + node.getName());
            String[] subNote = node.list();
            for (String filename : subNote) {
                generateFileList(new File(node, filename));
            }
        }
    }

    public void zipIt(String zipFile) {
        byte[] buffer = new byte[1024];

        try {
            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);
            //log.info("Output to Zip : " + zipFile);

            for (String file : this.fileList) {
                log.info("File Added : " + file);
                ZipEntry ze = new ZipEntry(file);
                zos.putNextEntry(ze);
                FileInputStream in
                        = new FileInputStream(gloCarpetaZipear + File.separator + file);
                int len;
                while ((len = in.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                in.close();
            }
            zos.closeEntry();

            zos.close();
            //log.info("Done");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String generateZipEntry(String file) {
        return file.substring(gloCarpetaZipear.length() + 1, file.length());
    }

    public static void subFormatos(String xDirFormato, String xNC, String xNL, String xCF) throws IOException {
        String xArchivoFormato = "";
        String xLeerFormato = "";

        log.info("Dir Formato: " + xDirFormato);
        File dirFormato = new File(xDirFormato + "\\formatos");
        if (dirFormato.mkdir()) {
            //log.info("*** Carpeta 'formato' creada: " + dirFormato);
            if (xNC.equals("S")) {
                xArchivoFormato = xDirFormato + "\\formatos\\nomina_credito.txt";
                xLeerFormato = gloRutaProyecto + "\\zFormatos\\nomina_credito.txt";

                subEscribeFormato(xArchivoFormato, xLeerFormato);
            }
            if (xNL.equals("S")) {
                xArchivoFormato = xDirFormato + "\\formatos\\nomina_ahorro.txt";
                xLeerFormato = gloRutaProyecto + "\\zFormatos\\nomina_ahorro.txt";

                subEscribeFormato(xArchivoFormato, xLeerFormato);
            }
            if (xCF.equals("S")) {
                xArchivoFormato = xDirFormato + "\\formatos\\anexo_trabajadores.txt";
                xLeerFormato = gloRutaProyecto + "\\zFormatos\\anexo_trabajadores.txt";

                subEscribeFormato(xArchivoFormato, xLeerFormato);

                xArchivoFormato = xDirFormato + "\\formatos\\cargas_familiares.txt";
                xLeerFormato = gloRutaProyecto + "\\zFormatos\\cargas_familiares.txt";

                subEscribeFormato(xArchivoFormato, xLeerFormato);
            }
        } else {
            //log.info("*** Error al crear Carpeta 'formato': " + dirFormato);
        }
    }

    public static void subEscribeFormato(String xArchivoFormato, String xLeerFormato) throws IOException {
        BufferedWriter escribeFormato = null;
        String lineaLeida = "";

        escribeFormato = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(xArchivoFormato, true), "iso-8859-1"));
        BufferedReader archivoLeer = new BufferedReader(new FileReader(xLeerFormato));

        while ((lineaLeida = archivoLeer.readLine()) != null) {
            if (lineaLeida.length() != 0) {
                escribeFormato.write(lineaLeida + "\r\n");
            }
        }

        escribeFormato.close();
        archivoLeer.close();
    }

    public static void subEspeciales(String xDirRut, String xRut, String xDirDescarga) throws IOException {

        log.info("Dir Rut para verificar Especiales: " + xDirRut + "\\" + xRut + "_especiales\\");
        File dirEspecial = new File(xDirRut + "\\" + xRut + "_especiales");

        if (dirEspecial.exists()) { //Verificar si existe Directorio con Nominas especiales
            String[] xLista = dirEspecial.list();
            File dirDescarga = new File(xDirDescarga + "\\" + xRut + "_especiales");
            //log.info("Agregar Csrpeta Rut especiales: " + xDirDescarga + "\\" + xRut + "_especiales");
            if (dirDescarga.mkdir()) {
                int ii = xLista.length;
                log.info("*** Hay Carpeta 'especiales'. Se crea: " + dirDescarga + " (con " + ii + " archivos)");
                for (int jj = 0; jj < ii; jj++) {
                    Path archivoEspecial_origen = Paths.get(xDirRut + "\\" + xRut + "_especiales\\" + xLista[jj]);
                    Path archivoEspecial_destino = Paths.get(dirDescarga + "\\" + xLista[jj]);

                    Files.copy(archivoEspecial_origen, archivoEspecial_destino, StandardCopyOption.REPLACE_EXISTING);

                    log.info("*** Archivo origen - " + (jj + 1) + ": " + archivoEspecial_origen);
                    log.info("*** Archivo destino - " + (jj + 1) + ": " + archivoEspecial_destino);
                    
                    gloTotCarpetasEspeciales += 1;
                }
            }
        }
    }
}
