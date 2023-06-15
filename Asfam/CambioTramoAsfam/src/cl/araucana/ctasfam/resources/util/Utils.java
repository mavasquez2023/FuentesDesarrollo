package cl.araucana.ctasfam.resources.util;

//--alexis advise 15-06-2012--//

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.upload.FormFile;

import cl.araucana.ctasfam.business.service.impl.PropuestaRentasServiceImpl;
import cl.araucana.ctasfam.business.to.AfiliadosErrorTO;
import cl.araucana.ctasfam.business.to.AfiliadosTO;
import cl.araucana.ctasfam.business.to.FlujoTO;
import cl.araucana.ctasfam.business.to.HoldingafiliadosTO;
import cl.araucana.ctasfam.business.to.ProcesoBashTO;
import cl.araucana.ctasfam.integration.jdbc.dao.AraucanaJdbcDao;
import cl.araucana.ctasfam.integration.jdbc.dao.impl.Mejoras2016DaoImpl;
import cl.araucana.ctasfam.presentation.struts.resources.ServiceLocatorWeb;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.ExtendedIOException;
import com.ibm.as400.access.IFSFileInputStream;
import com.ibm.as400.access.IFSFileOutputStream;
import com.ibm.as400.access.IFSJavaFile;
import com.smartxls.WorkBook;

public class Utils {

	AraucanaJdbcDao dao = new AraucanaJdbcDao();
	Logger log = Logger.getLogger(this.getClass());
	FlujoTO flujo = new FlujoTO();
	String fail = null;
	String rutaBash = null;
	Encargado enc = new Encargado();

	private static Properties Config = null;

	public Utils() {
		if (Config == null) {
			Config = new Properties();

			try {
				Config.load(getClass().getClassLoader().getResourceAsStream(
						"configuracion.properties"));
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	
	
	public List creaCsv(File Archivo, int rutEmpresa) throws Exception {

		List<Object> result= new ArrayList<Object>();
		AfiliadosTO oafil = null;
		AfiliadosErrorTO afilerror=null;
		List<AfiliadosErrorTO> listaerrores= new ArrayList<AfiliadosErrorTO>();
		List<AfiliadosTO> listaregistros= new ArrayList<AfiliadosTO>();
		BufferedReader reader = new BufferedReader(new FileReader(Archivo));
		Mejoras2016DaoImpl daoMejoras= new Mejoras2016DaoImpl();
		String oficina="";
		String sucursal="";
		String linea;
		int numerolinea = 1;
		boolean conerror= false;
		List<Integer> listruttra= new ArrayList<Integer>();
		try {
			while ((linea = reader.readLine()) != null) {

				if (!linea.replaceAll(";", "").trim().equals("")) {
					if (linea.trim().length() != 0) {
						
						oafil = new AfiliadosTO();
						String columnas[] = linea.split(";");
						if (columnas.length == 17) {
							ArrayList recValidador = this.validador(columnas, numerolinea, rutEmpresa);
							listaerrores.addAll(recValidador);
							
							if(recValidador.size()==0) {
								if(numerolinea==1){
									String oficina_sucursal= daoMejoras.getOficinaSucursal(Integer.parseInt(columnas[1]), Integer.parseInt(columnas[3]));
									String[] aux= oficina_sucursal.split(":");
									oficina= aux[0];
									sucursal= aux[1];
								}
								oafil.setPeriodo(Integer.parseInt(columnas[0]));
								oafil.setOficina(Integer.parseInt(oficina));
								oafil.setSucursal(Integer.parseInt(sucursal));
								oafil.setRutempresa(Integer.parseInt(columnas[1]));
								oafil.setDvempresa(columnas[2]);
								oafil.setRuttrabajador(Integer.parseInt(columnas[3]));
								oafil.setDvtrabajador(columnas[4]);
								oafil.setApellidopaterno(columnas[5].toUpperCase());
								oafil.setApellidomaterno(columnas[6].toUpperCase());
								oafil.setNombreafiliado(columnas[7].toUpperCase());
								oafil.setRemuneracionesmismoempleador(Integer
										.parseInt(columnas[8]));
								oafil.setOtrasremuneraciones(Integer
										.parseInt(columnas[9]));
								oafil.setRentatrabajadorindependiente(Integer
										.parseInt(columnas[10]));
								oafil.setSubsidio(Integer.parseInt(columnas[11]));
								oafil.setPensiones(Integer.parseInt(columnas[12]));
								oafil.setTotalingresos(Integer.parseInt(columnas[13]));
								oafil.setNumeromeses(Integer.parseInt(columnas[14]));
								oafil
								.setIngresopromedio(Integer
										.parseInt(columnas[15]));
								oafil.setTrabajadorconsindeclaracion(Integer
										.parseInt(columnas[16]));
								oafil.setOrigen("E");
								if(listruttra.contains(Integer.parseInt(columnas[3]))){
									afilerror= new AfiliadosErrorTO();
									afilerror.setDescripcionerror("Rut Duplicado");
									afilerror.setRuttrabajador(columnas[3]);
									afilerror.setNumerolinea(numerolinea);
									afilerror.setNumeroColumna(4);
									listaerrores.add(afilerror);
								}else{
									listaregistros.add(oafil);
									listruttra.add(Integer.parseInt(columnas[3]));
								}
							}

						}else{
							afilerror= new AfiliadosErrorTO();
							afilerror.setDescripcionerror("Número de columnas erróneo");
							afilerror.setRuttrabajador("");
							afilerror.setNumerolinea(numerolinea);
							afilerror.setNumeroColumna(columnas.length);
							listaerrores.add(afilerror);
						}
					} 
				} 
				numerolinea++;
				if(listaerrores.size()==50){
					break;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(listaerrores.size()==0){
				afilerror= new AfiliadosErrorTO();
				afilerror.setDescripcionerror("Error desconocido, intente nuevamente");
				afilerror.setRuttrabajador("");
				afilerror.setNumerolinea(numerolinea);
				afilerror.setNumeroColumna(0);
				listaerrores.add(afilerror);
			}
					
		}
		finally{
			reader.close();
		}
		

		if(listaerrores.size()>0){
			listaregistros.clear();
			result.add(listaerrores);
			result.add("error");
		}else{
			result.add(listaregistros);
		}
		
		return result;
	}

	public char getDigito(Object rut) {
		char p = 0;
		try {
			int M = 0, S = 1, T = Integer.parseInt(rut.toString());
			for (; T != 0; T /= 10)
				S = (S + T % 10 * (9 - M++ % 6)) % 11;
			p = (char) (S != 0 ? S + 47 : 75);
		} catch (NumberFormatException ignored) {
		}
		return p;
	}

	public boolean validarNombre(File archivo) {
		String nombreArch = archivo.getName();
		String temp = this.extencion(archivo);
		if (archivo.isDirectory())
			return false;

		if (!temp.equalsIgnoreCase("txt") && !temp.equalsIgnoreCase("csv")
				&& !temp.equalsIgnoreCase("zip"))
			return false;
		String patron = "/(\"|\')/";
		if (patron.matches(nombreArch))
			return false;
		return true;

	}

	public String extencion(File archivo) {

		String nombreArch = archivo.getName();
		String temp = nombreArch.substring(nombreArch.lastIndexOf(".") + 1,
				nombreArch.length());

		return temp;
	}

	public boolean unZip(String path, String destino) {
		final int BUFFER = 2048;

		try {

			System.out.println("zip path entry" + path);
			BufferedOutputStream dest = null;
			FileInputStream fis = new FileInputStream(path);
			ZipInputStream zis = new ZipInputStream(
					new BufferedInputStream(fis));
			ZipEntry entry;

			File folder = new File(destino);
			if (!folder.exists())
				folder.mkdir();

			while ((entry = zis.getNextEntry()) != null) {
				System.out.println("Extracting: " + destino + entry.getName());
				if (!entry.isDirectory()) {
					int count;
					byte data[] = new byte[BUFFER];

					// write the files to the disk
					FileOutputStream fos = new FileOutputStream(destino
							+ entry.getName());
					dest = new BufferedOutputStream(fos, BUFFER);
					while ((count = zis.read(data, 0, BUFFER)) != -1) {
						dest.write(data, 0, count);
					}
					dest.flush();
					dest.close();
				} else {

					return false;
				}

			}
			zis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	
	public File creaArchivo(String filePath, FormFile Archivo, String ext)
			throws IOException, Exception {

		// Properties Config = new Properties();
		// Config.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		Date now = new Date();
		SimpleDateFormat sdfF = new SimpleDateFormat("yyyyMMdd", new Locale("cl"));
		SimpleDateFormat sdfH = new SimpleDateFormat("HHmmss", new Locale("cl"));
		//String carpeta = sdfF.format(now) + "_" + sdfH.format(now);
		String carpeta = sdfF.format(now);

		// AS400 system=new AS400(servidor,user,pass);
		// ExplorerManagerAs400 as400=new ExplorerManagerAs400(system);
		Utils util = new Utils();
		String nombre = null;
		File file = new File(Archivo.getFileName());
		String fileName = Archivo.getFileName();

		File fileToCreate = null;

		if (!fileName.equals("")) {

			File folder = new File(filePath + carpeta);
			if (!folder.exists())
				folder.mkdir();

			
			nombre = fileName.substring(0, fileName.indexOf("."));
			log.info("Creando archivo, Server path: " + filePath + carpeta + "/"
					+ nombre + "_" + ext + "." + util.extencion(file));
			fileToCreate = new File(filePath + carpeta + "/" + nombre + "_"
					+ ext + "." + util.extencion(file));

			if (!fileToCreate.exists()) {
				FileOutputStream fileOutStream = new FileOutputStream(
						fileToCreate);
				fileOutStream.write(Archivo.getFileData());
				fileOutStream.flush();
				fileOutStream.close();
			}
		}

		return fileToCreate;
	}

	

	public void FileCopy(String sourceFile, String destinationFile) {
		System.out.println("Desde: " + sourceFile);
		System.out.println("Hacia: " + destinationFile);

		try {
			File inFile = new File(sourceFile);
			File outFile = new File(destinationFile);

			FileInputStream in = new FileInputStream(inFile);
			FileOutputStream out = new FileOutputStream(outFile);

			int c;
			while ((c = in.read()) != -1)
				out.write(c);

			in.close();
			out.close();
		} catch (IOException e) {
			System.err.println("Hubo un error de entrada/salida!!!");
		}
	}

	public String creaCarpetas(String rutaZip, String rutaRes, String ext,
			String extension) throws IOException, Exception {

		File folder = null;
		File folderDes = null;
		String nombre = null;
		String folderDespath = null;

		if (extension.equalsIgnoreCase("zip")) {
			folderDes = new File(rutaZip + "/" + ext);
			if (!folderDes.exists()) {
				folderDes.mkdir();
			}
		}

		folder = new File(rutaRes + "/" + ext);

		if (!folder.exists()) {
			folder.mkdir();
		}

		if (folderDes == null)
			folderDespath = "NAN";
		else
			folderDespath = folderDes.getAbsolutePath();

		nombre = folder.getAbsolutePath() + "-" + folderDespath;

		return nombre;
	}

	public List procesaFicheros(String path, HttpServletRequest request)
			throws Exception {

		List listazip = new ArrayList();
		List listaTotal = new ArrayList();

		File directorio = new File(path);
		String[] ficheros = directorio.list();
		String extension = null;

		for (int i = 0; i < ficheros.length; i++) {
			try {

				System.out.println("Contenido del fichero " + path + "\\"
						+ ficheros[i]);

				String tipo = "NORMAL";

				File fichero = new File(path + "\\" + ficheros[i]);
				extension = this.extencion(fichero);
				listazip = this.procesaArchivosZip(extension, fichero, tipo,
						request);

				if (listazip.size() == 0) {
					listaTotal.add("file/" + ficheros[i]);
				}

				for (int k = 0; k < listazip.size(); k++) {
					if (k == 0)
						listaTotal.add(listazip.get(k) + "/" + ficheros[i]
								+ "/" + 0);
					else
						listaTotal.add(listazip.get(k) + "/" + ficheros[i]
								+ "/" + 1);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return listaTotal;
	}

	public ArrayList validador(String columnas[], int fila, int rutEmpresa) throws Exception {

		String periodo = Parametros.getInstance().getParam().getPeriodoProceso();

		ArrayList retValidador = new ArrayList();
		
		try {

			String ruttrabajador="";
			if(this.isNumeric(columnas[3]) && this.isDV(columnas[4])){
				ruttrabajador= columnas[3] + "-" +columnas[4];
			}

			//Columna 1 Periodo
			if (!columnas[0].equals(periodo)){
				AfiliadosErrorTO afilerror= new AfiliadosErrorTO();
				afilerror.setDescripcionerror("Periodo no válido");
				afilerror.setNumeroColumna(1);
				afilerror.setNumerolinea(fila);
				afilerror.setRuttrabajador(ruttrabajador);
				retValidador.add(afilerror);
			}
			//Columna 2 RutEmpresa
			if (!this.isNumeric(columnas[1]) || Integer.parseInt(columnas[1])!=rutEmpresa){
				AfiliadosErrorTO afilerror= new AfiliadosErrorTO();
				afilerror.setDescripcionerror("Rut Empresa no válido");
				afilerror.setNumeroColumna(2);
				afilerror.setNumerolinea(fila);
				afilerror.setRuttrabajador(ruttrabajador);
				retValidador.add(afilerror);
			}
			//Columna 3 DVEmpresa
			if (!this.isDV(columnas[2]) || !this.validaDigito(columnas[1], columnas[2])){
				AfiliadosErrorTO afilerror= new AfiliadosErrorTO();
				afilerror.setDescripcionerror("DV Rut Empresa incorrecto");
				afilerror.setNumeroColumna(3);
				afilerror.setNumerolinea(fila);
				afilerror.setRuttrabajador(ruttrabajador);
				retValidador.add(afilerror);
			}
			//Columna 4 RutTrabajador
			if (!this.isNumeric(columnas[3])){
				AfiliadosErrorTO afilerror= new AfiliadosErrorTO();
				afilerror.setDescripcionerror("Rut Trabajador no válido");
				afilerror.setNumeroColumna(4);
				afilerror.setNumerolinea(fila);
				afilerror.setRuttrabajador(ruttrabajador);
				retValidador.add(afilerror);
			}
			//Columna 5 DVTrabajador
			if (!this.isDV(columnas[4]) || !this.validaDigito(columnas[3], columnas[4])){
				AfiliadosErrorTO afilerror= new AfiliadosErrorTO();
				afilerror.setDescripcionerror("DV Trabajador incorrecto");
				afilerror.setNumeroColumna(5);
				afilerror.setNumerolinea(fila);
				afilerror.setRuttrabajador(ruttrabajador);
				retValidador.add(afilerror);
			}
			//Columna 6 Apellido Paterno
			if(!validaCaracteresEspeciales(columnas[5])){
				AfiliadosErrorTO afilerror= new AfiliadosErrorTO();
				afilerror.setDescripcionerror("Appelido Paterno con caracteres no válidos");
				afilerror.setNumeroColumna(6);
				afilerror.setNumerolinea(fila);
				afilerror.setRuttrabajador(ruttrabajador);
				retValidador.add(afilerror);
			}
			//Columna 7 Apellido Paterno
			if(!validaCaracteresEspeciales(columnas[6])){
				AfiliadosErrorTO afilerror= new AfiliadosErrorTO();
				afilerror.setDescripcionerror("Apelido Materno con caracteres no válidos");
				afilerror.setNumeroColumna(7);
				afilerror.setNumerolinea(fila);
				afilerror.setRuttrabajador(ruttrabajador);
				retValidador.add(afilerror);
			}
			//Columna 8 Apellido Nombres
			if(!validaCaracteresEspeciales(columnas[7])){
				AfiliadosErrorTO afilerror= new AfiliadosErrorTO();
				afilerror.setDescripcionerror("Nombre con caracteres no válidos");
				afilerror.setNumeroColumna(8);
				afilerror.setNumerolinea(fila);
				afilerror.setRuttrabajador(ruttrabajador);
				retValidador.add(afilerror);
			}
			//Columna 9 Remuneraciones
			if(columnas[8].trim().equals("")){
				columnas[8]="0";
			}
			if (!this.isNumeric(columnas[8]) || columnas[8].length()>12){
				AfiliadosErrorTO afilerror= new AfiliadosErrorTO();
				afilerror.setDescripcionerror("Monto no válido");
				afilerror.setNumeroColumna(9);
				afilerror.setNumerolinea(fila);
				afilerror.setRuttrabajador(ruttrabajador);
				retValidador.add(afilerror);
			}
			//Columna 10 Otras Remuneraciones
			if(columnas[9].trim().equals("")){
				columnas[9]="0";
			}
			if (!this.isNumeric(columnas[9]) || columnas[9].length()>12){
				AfiliadosErrorTO afilerror= new AfiliadosErrorTO();
				afilerror.setDescripcionerror("Monto no válido");
				afilerror.setNumeroColumna(10);
				afilerror.setNumerolinea(fila);
				afilerror.setRuttrabajador(ruttrabajador);
				retValidador.add(afilerror);
			}
			//Columna 11 Renta Independiente
			if(columnas[10].trim().equals("")){
				columnas[10]="0";
			}
			if (!this.isNumeric(columnas[10]) || columnas[10].length()>12){
				AfiliadosErrorTO afilerror= new AfiliadosErrorTO();
				afilerror.setDescripcionerror("Monto no válido");
				afilerror.setNumeroColumna(11);
				afilerror.setNumerolinea(fila);
				afilerror.setRuttrabajador(ruttrabajador);
				retValidador.add(afilerror);
			}
			//Columna 12 Subsidios
			if(columnas[11].trim().equals("")){
				columnas[11]="0";
			}
			if (!this.isNumeric(columnas[11]) || columnas[11].length()>12){
				AfiliadosErrorTO afilerror= new AfiliadosErrorTO();
				afilerror.setDescripcionerror("Monto no válido");
				afilerror.setNumeroColumna(12);
				afilerror.setNumerolinea(fila);
				afilerror.setRuttrabajador(ruttrabajador);
				retValidador.add(afilerror);
			}
			//Columna 13 Pensiones
			if(columnas[12].trim().equals("")){
				columnas[12]="0";
			}
			if (!this.isNumeric(columnas[12]) || columnas[12].length()>12){
				AfiliadosErrorTO afilerror= new AfiliadosErrorTO();
				afilerror.setDescripcionerror("Monto no válido");
				afilerror.setNumeroColumna(13);
				afilerror.setNumerolinea(fila);
				afilerror.setRuttrabajador(ruttrabajador);
				retValidador.add(afilerror);
			}
			//Columna 14 Total Ingresos
			if(columnas[13].trim().equals("")){
				columnas[13]="0";
			}
			if (!this.isNumeric(columnas[13]) || columnas[13].length()>12){
				AfiliadosErrorTO afilerror= new AfiliadosErrorTO();
				afilerror.setDescripcionerror("Monto no válido");
				afilerror.setNumeroColumna(14);
				afilerror.setNumerolinea(fila);
				afilerror.setRuttrabajador(ruttrabajador);
				retValidador.add(afilerror);
			}
			//Columna 15 Numero Meses
			if(columnas[14].trim().equals("")){
				columnas[14]="0";
			}
			if (!this.isNumeric(columnas[14]) || columnas[14].length()>2){
				AfiliadosErrorTO afilerror= new AfiliadosErrorTO();
				afilerror.setDescripcionerror("Cantidad de meses no válido");
				afilerror.setNumeroColumna(15);
				afilerror.setNumerolinea(fila);
				afilerror.setRuttrabajador(ruttrabajador);
				retValidador.add(afilerror);
			}
			//Columna 15 Ingreso Promedio
			if(columnas[15].trim().equals("")){
				columnas[15]="0";
			}
			if (!this.isNumeric(columnas[15]) || columnas[15].length()>12){
				AfiliadosErrorTO afilerror= new AfiliadosErrorTO();
				afilerror.setDescripcionerror("Monto no válido");
				afilerror.setNumeroColumna(16);
				afilerror.setNumerolinea(fila);
				afilerror.setRuttrabajador(ruttrabajador);
				retValidador.add(afilerror);
			}
			//Columna 15 Declaración Jurada
			if(columnas[16].trim().equals("")){
				columnas[16]="0";
			}
			if (!this.isNumeric(columnas[16]) || columnas[16].length()>1){
				AfiliadosErrorTO afilerror= new AfiliadosErrorTO();
				afilerror.setDescripcionerror("Declaración no válida");
				afilerror.setNumeroColumna(17);
				afilerror.setNumerolinea(fila);
				afilerror.setRuttrabajador(ruttrabajador);
				retValidador.add(afilerror);
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retValidador;

	}

	public List procesaArchivos(String extension, FormFile archivo,
			String carpetaRes, String ext, String tipo,
			HttpServletRequest request) throws Exception {

		List lista = new ArrayList();


		enc = (Encargado) request.getSession().getAttribute("edocs_encargado");
		String rol= (String)request.getSession().getAttribute("rol");  
		String rutEmpresa= (String)request.getSession().getAttribute("rutestado"); 
		if(rol.equals("Ejecutivo")){
			rutEmpresa= archivo.getFileName().substring(0, archivo.getFileName().toString().indexOf("."));
		}else{
			rutEmpresa= (String)request.getSession().getAttribute("rutestado");
		}

		String rutenc = String.valueOf(enc.getRut());
		// Properties Config = new Properties();
		// Config.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		String periodo = Parametros.getInstance().getParam().getPeriodoProceso();

		try {

		
			if (extension.equalsIgnoreCase("csv") || extension.equalsIgnoreCase("txt")) {

				File createfile = this.creaArchivo(carpetaRes, archivo, ext);
				int rutemp= Integer.parseInt((rutEmpresa.split("-"))[0]);
				lista = this.creaCsv(createfile, rutemp);
				
				 /*if (!lista.get(lista.size() - 1).equals("error")) {
					  
					  for(int i=0;i<lista.size();i++) {
						  AfiliadosTO oafil=(AfiliadosTO)lista.get(i);
						  dao.updateaAfiliados(oafil);

					  }
				 }*/
			}else{
				AfiliadosErrorTO afierror= new AfiliadosErrorTO();
				afierror.setNombrearchivo(archivo.getFileName());
				afierror.setNumerolinea(0);
				afierror.setDescripcionerror("Error en la extensión del archivo");
				lista.add(afierror);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return lista;
	}

	
	public List procesaArchivosZip(String extension, File archivo, String tipo,
			HttpServletRequest request) throws Exception {

		List lista = null;
		lista = new ArrayList();
		List listaerror = new ArrayList();
		enc = (Encargado) request.getSession().getAttribute("edocs_encargado");
		String rutEmpresa= (String)request.getSession().getAttribute("rutestado");  
		String rutenc = String.valueOf(enc.getRut());
		// Properties Config = new Properties();
		// Config.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		String periodo = Config.getProperty("PERIODO");

		if (extension.equalsIgnoreCase("csv")) {
			int rutemp= Integer.parseInt((rutEmpresa.split("-"))[0]);
			lista = this.creaCsv(archivo, rutemp);
			if (!lista.get(lista.size() - 1).equals("error")) {

				flujo.setRutencargado(rutenc);
				flujo.setEtapa("3");
				flujo.setISAJKM92("CTADMIN");
				flujo.setRutempresa(archivo.getName().substring(0,
						archivo.getName().toString().indexOf(".")));
				flujo.setPeriodo(periodo);
				flujo.setCantregistros(lista.size());
				flujo.setOperacion("ENVIA ARCHIVO");
				flujo.setISAJKM92("");
				flujo.setISAJKM94("CTADMIN");
				flujo.setEtapa("3");
				flujo.setNombrearchivo(archivo.getName());

				dao.InsertaBitacora(flujo);
				/*
				 * for(int i=1;i<lista.size();i++) {
				 * 
				 * 
				 * AfiliadosTO oafil=(AfiliadosTO)lista.get(i);
				 * oafil.setOrigen("E"); int
				 * existe=Integer.parseInt(dao.existeAfiliado(String.valueOf(oafil.getPeriodo()),String.valueOf(oafil.getRutempresa()),String.valueOf(oafil.getRuttrabajador()))[0]);
				 * if(existe==0){ dao.insertaArchivo(oafil); } else {
				 * dao.updateaAfiliados(oafil); }
				 *  }
				 */
			} else {
				listaerror = lista;
			}

		}
		return listaerror;
	}

	// ----------------valida archivos--------------------------

/*	public List procesaFicherosgrava(String path, HttpServletRequest request) throws Exception {

		List listazip = new ArrayList();
		List listaTotal = new ArrayList();

		File directorio = new File(path);
		String[] ficheros = directorio.list();
		String extension = null;

		for (int i = 0; i < ficheros.length; i++) {
			try {

				System.out.println("Contenido del fichero " + path + "\\"
						+ ficheros[i]);

				String tipo = "NORMAL";

				File fichero = new File(path + "\\" + ficheros[i]);
				extension = this.extencion(fichero);
				listazip = this.procesaArchivosZipgrava(extension, fichero,
						tipo, request);

				if (listazip.size() == 0) {
					listaTotal.add("file/" + ficheros[i]);
				}

				for (int k = 0; k < listazip.size(); k++) {
					if (k == 0)
						listaTotal.add(listazip.get(k) + "/" + ficheros[i]
								+ "/" + 0);
					else
						listaTotal.add(listazip.get(k) + "/" + ficheros[i]
								+ "/" + 1);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return listaTotal;
	}
*/
	/*public List procesaArchivosgrava(String extension, FormFile archivo,
			String carpetaRes, String ext, String tipo,HttpServletRequest request) throws Exception {

		Encargado enc1 =new Encargado();
		enc1=(Encargado)request.getSession().getAttribute("edocs_encargado");
		String usser = String.valueOf(enc1.getFullRut());
		
		
		List lista = null;
		lista = new ArrayList();
		List listaerror = new ArrayList();
		java.sql.Time time =  new java.sql.Time(new Date().getTime());
		int rutEmpresa=0;
		int periodo =0;
		Mejoras2016DaoImpl probashDao = new Mejoras2016DaoImpl();
		String fechaStamp = null;
		String horaStamp = null;
		
		
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdh= new SimpleDateFormat("HH:mm:ss");
		fechaStamp = sdf.format(new Date());
		horaStamp = sdh.format(new Date());
		

		try {

			if (extension.equalsIgnoreCase("txt")) {

				File createfile = this.creaArchivoas400(carpetaRes + "\\",
						archivo, ext);
				if (extension.equalsIgnoreCase("txt"))
					lista = this.creaTexto(createfile, extension);
				if (!lista.get(lista.size() - 1).equals("error")) {
					for (int i = 0; i < lista.size(); i++) {

						AfiliadosTO oafil = (AfiliadosTO) lista.get(i);
						
						rutEmpresa = oafil.getRutempresa();
						periodo = oafil.getPeriodo();
						
						if (tipo.equals("empresa")) {
							oafil.setOrigen("C");
						} else {
							oafil.setOrigen("E");
						}
						oafil.setTiempo(time);
						String valor[] = new String[3];
						valor = dao.existeAfiliado(String
								.valueOf(oafil.getPeriodo()), String
								.valueOf(oafil.getRutempresa()), String
								.valueOf(oafil.getRuttrabajador()));
						if(Integer.parseInt(valor[2]) == 0){
							oafil.setAfama(1);
						}else{
							oafil.setAfama(0);
						}
//						if (Integer.parseInt(valor[0]) == 0) {
//							dao.insertaArchivo(oafil);
//						} else {
//							dao.updateaAfiliados(oafil);
//						}
					}
					
					ProcesoBashTO procesoBashTO = new ProcesoBashTO();
					procesoBashTO.setRutaArchivo(rutaBash);
					procesoBashTO.setEstado("P");
					procesoBashTO.setEmpresa(rutEmpresa);
					procesoBashTO.setUsuarioSube(usser); 
					procesoBashTO.setFechaSubida(fechaStamp);
					procesoBashTO.setHoraSubida(horaStamp);
					procesoBashTO.setRegistrosInformados(lista.size());
					procesoBashTO.setCantidadIntento(0);
					procesoBashTO.setOrigen("E");
					procesoBashTO.setPeriodo(periodo);

					if (probashDao.insertBash(procesoBashTO)) {
						//proceso bash insertado correctamente
					}
					
					else{
						//proceso bash no pudo ser insertado
					}

				} else {
					listaerror = lista;
				}
						

			} 
			
			
			else if (extension.equalsIgnoreCase("csv")) {

				File createfile = this.creaArchivoas400(carpetaRes + "\\",
						archivo, ext);
				lista = this.creaCsv(createfile, extension);
				if (!lista.get(lista.size() - 1).equals("error")) {
					System.out.println("lista csv " + lista.size());
					int c = 0;
					int k = 0;

					for (int i = 0; i < lista.size(); i++) {

						AfiliadosTO oafil = (AfiliadosTO) lista.get(i);
						
						rutEmpresa = oafil.getRutempresa();
						periodo = oafil.getPeriodo();
						
						if (tipo.equals("empresa")) {
							oafil.setOrigen("C");
						} else {
							oafil.setOrigen("E");
						}
						oafil.setTiempo(time);
						String valor[] = new String[3];
						valor = dao.existeAfiliado(String
								.valueOf(oafil.getPeriodo()), String
								.valueOf(oafil.getRutempresa()), String
								.valueOf(oafil.getRuttrabajador()));
						if(Integer.parseInt(valor[2]) == 0){
							oafil.setAfama(1);
						}else{
							oafil.setAfama(0);
						}
						if (Integer.parseInt(valor[0]) == 0) {
//							if (!dao.insertaArchivo(oafil)) {
//								System.out.println("no inserta " + i);
//							}

							c++;
						} else {
							k++;
//							dao.updateaAfiliados(oafil);
						}

					}
					System.out.println("c " + c + " k " + k);
					
					ProcesoBashTO procesoBashTO = new ProcesoBashTO();
					procesoBashTO.setRutaArchivo(rutaBash);
					procesoBashTO.setEstado("P");
					procesoBashTO.setEmpresa(rutEmpresa);
					procesoBashTO.setUsuarioSube(usser); 
					procesoBashTO.setFechaSubida(fechaStamp);
					procesoBashTO.setHoraSubida(horaStamp);
					procesoBashTO.setRegistrosInformados(lista.size());
					procesoBashTO.setCantidadIntento(0);
					procesoBashTO.setOrigen("E");
					procesoBashTO.setPeriodo(periodo);

					if (probashDao.insertBash(procesoBashTO)) {
						//proceso bash insertado correctamente
					}
					
					else{
						//proceso bash no pudo ser insertado
					}
					
				} else {
				listaerror = lista;
				}

				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return listaerror;
	}
*/
/*	public List procesaArchivosZipgrava(String extension, File archivo,
			String tipo, HttpServletRequest request) throws Exception {
		
		Encargado enc1 =new Encargado();
		enc1=(Encargado)request.getSession().getAttribute("edocs_encargado");
		String usser = String.valueOf(enc1.getFullRut());

		List lista = null;
		lista = new ArrayList();
		List listaerror = new ArrayList();
		java.sql.Time time =  new java.sql.Time(new Date().getTime());
		int rutEmpresa= 0;
		int periodo = 0; 
		Mejoras2016DaoImpl probashDao = new Mejoras2016DaoImpl();
		String fechaStamp = null;
        String horaStamp = null;
		
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdh= new SimpleDateFormat("HH:mm:ss");
		fechaStamp = sdf.format(new Date());
		horaStamp = sdh.format(new Date());

		
		if (extension.equalsIgnoreCase("txt")) {

			if (extension.equalsIgnoreCase("txt"))
				lista = this.creaTexto(archivo, extension);
			if (!lista.get(lista.size() - 1).equals("error")) {
				for (int i = 0; i < lista.size(); i++) {

					AfiliadosTO oafil = (AfiliadosTO) lista.get(i);
					
					rutEmpresa = oafil.getRutempresa();
					periodo = oafil.getPeriodo();

					
					oafil.setOrigen("E");
					oafil.setTiempo(time);
					String valor[] = new String[3];
					valor = dao.existeAfiliado(String
							.valueOf(oafil.getPeriodo()), String
							.valueOf(oafil.getRutempresa()), String
							.valueOf(oafil.getRuttrabajador()));
					if(Integer.parseInt(valor[2]) == 0){
						oafil.setAfama(1);
					}else{
						oafil.setAfama(0);
					}
//					if (Integer.parseInt(valor[0]) == 0) {
//						dao.insertaArchivo(oafil);
//					} else {
//						dao.updateaAfiliados(oafil);
//					}

				}
				
				ProcesoBashTO procesoBashTO = new ProcesoBashTO();
				procesoBashTO.setRutaArchivo(rutaBash);
				procesoBashTO.setEstado("P");
				procesoBashTO.setEmpresa(rutEmpresa);
				procesoBashTO.setUsuarioSube(usser); 
				procesoBashTO.setFechaSubida(fechaStamp);
				procesoBashTO.setHoraSubida(horaStamp);
				procesoBashTO.setRegistrosInformados(lista.size());
				procesoBashTO.setCantidadIntento(0);
				procesoBashTO.setOrigen("E");
				procesoBashTO.setPeriodo(periodo);

				if (probashDao.insertBash(procesoBashTO)) {
					//proceso bash insertado correctamente
				}
				
				else{
					//proceso bash no pudo ser insertado
				}

			} else {
				listaerror = lista;
			}
			
			
		}

		
		else if (extension.equalsIgnoreCase("csv")) {

			lista = this.creaCsv(archivo, extension);
			
			if (!lista.get(lista.size() - 1).equals("error")) {
				System.out.println(lista.size());
				
				for (int i = 1; i < lista.size(); i++) {

					AfiliadosTO oafil = (AfiliadosTO) lista.get(i);
					
					rutEmpresa = oafil.getRutempresa();
					periodo = oafil.getPeriodo();

					
					oafil.setOrigen("E");
					oafil.setTiempo(time);
					String valor[] = new String[3];
					valor = dao.existeAfiliado(String
							.valueOf(oafil.getPeriodo()), String
							.valueOf(oafil.getRutempresa()), String
							.valueOf(oafil.getRuttrabajador()));
					if(Integer.parseInt(valor[2]) == 0){
						oafil.setAfama(1);
					}else{
						oafil.setAfama(0);
					}
//					if (Integer.parseInt(valor[0]) == 0) {
//						dao.insertaArchivo(oafil);
//					} else {
//						dao.updateaAfiliados(oafil);
//					}

				}
				
				ProcesoBashTO procesoBashTO = new ProcesoBashTO();
				procesoBashTO.setRutaArchivo(rutaBash);
				procesoBashTO.setEstado("P");
				procesoBashTO.setEmpresa(rutEmpresa);
				procesoBashTO.setUsuarioSube(usser); 
				procesoBashTO.setFechaSubida(fechaStamp);
				procesoBashTO.setHoraSubida(horaStamp);
				procesoBashTO.setRegistrosInformados(lista.size());
				procesoBashTO.setCantidadIntento(0);
				procesoBashTO.setOrigen("E");
				procesoBashTO.setPeriodo(periodo);

				if (probashDao.insertBash(procesoBashTO)) {
					//proceso bash insertado correctamente
				}
				
				else{
					//proceso bash no pudo ser insertado
				}
				
			} else {
				listaerror = lista;
			}
			
			

		}
		return listaerror;
	}
*/
	// ---------------------------------------------------------

	public boolean noformato(String str) {

		int valor = 0;
		try {

			valor = Integer.parseInt(str);

		} catch (NumberFormatException ignored) {
			return false;
		}

		return true;
	}

	public boolean validaDigito(String rut, String digito) {

		try {
			char dv = this.getDigito(rut);

			if (!String.valueOf(dv).equalsIgnoreCase(String.valueOf(digito))) {
				return false;
			}

		} catch (Exception ex) {
			ex.printStackTrace();

			return false;
		}

		return true;
	}

	public boolean monto(String monto) {
		try{
			int montoi = Integer.parseInt(String.valueOf(monto));
			if (montoi <= 0) {
				return false;
			} else
	
				return true;
		}catch(Exception e){
			return false;
		}
	}

	public boolean meses(String mes) {
		try{
	
			int meses = Integer.parseInt(mes);
	
			if (meses > 12 || meses < 1) {
				return false;
			} else {
				return true;
			}
		}catch(Exception e){
			return false;
		}

	}
	public boolean isDV(String dv) {
		String digitos= "1,2,3,4,5,6,7,8,9,0,K,k";
		try{
	
			if (digitos.contains(dv)) {
				return true;
			} else {
				return false;
			}
		}catch(Exception e){
			return false;
		}

	}

	public boolean isNumeric(String str) {

		try{
			int montoi = Integer.parseInt(String.valueOf(str));
			if (montoi < 0) {
				return false;
			} else
	
				return true;
		}catch(Exception e){
			return false;
		}
	}

	/*---------------------Holding----------------------------------
	 * 
	 */

	/*public List createExcelHolding(FormFile Archivo, String extension)
			throws Exception {
		System.out.println("begin create report");

		InputStream is = this.excel(extension, Archivo);

		List result = new ArrayList();
		HoldingafiliadosTO oafil = new HoldingafiliadosTO();

		List lista = new ArrayList();
		String celda[] = new String[21];
		String vector[] = new String[18];

		WorkBook workbook = new WorkBook();
		if (extension.equalsIgnoreCase("xls"))
			workbook.read(is);
		else if (extension.equalsIgnoreCase("xlsx"))
			workbook.readXLSX(is);

		// new code***
		/*
		 * byte[] fileData = Archivo.getFileData(); InputStream is = new
		 * ByteArrayInputStream(fileData);
		 * 
		 * HSSFWorkbook wb = new HSSFWorkbook(is); //referencia al archivo
		 * HSSFSheet sheet = wb.getSheetAt(0); //referencia a la primera hoja
		 * del archivo
		 * 
		 * int ultimaFila = sheet.getLastRowNum();
		 */
		// ***********

		// new code***
		/*
		 * for(int i = 0; i <= ultimaFila - 1; i++) { HSSFRow row =
		 * sheet.getRow(1 + i); //obtenemos la fila
		 * 
		 * for(int j=0;j<21;j++) { if(row.getCell(j).getCellType() == 1){
		 * celda[j] =
		 * row.getCell(j).getRichStringCellValue().getString().trim(); }else
		 * if(row.getCell(j).getCellType() == 0){ celda[j] =
		 * String.valueOf(Math.round(row.getCell(j).getNumericCellValue())); } }
		 * 
		 * int meses=0; int c=0;
		 * 
		 * for(int k=12;k<18;k++) { if(Integer.parseInt(celda[k])>0){ c++; } }
		 * 
		 * meses=c;
		 * 
		 * vector[0]=String.valueOf(Math.round(row.getCell(0).getNumericCellValue()));
		 * //periodo
		 * vector[1]=row.getCell(1).getRichStringCellValue().getString().trim();
		 * //holding
		 * vector[2]=row.getCell(2).getRichStringCellValue().getString().trim();
		 * //dvholding
		 * vector[3]=row.getCell(3).getRichStringCellValue().getString().trim();
		 * //oficina
		 * vector[4]=row.getCell(4).getRichStringCellValue().getString().trim();
		 * //sucursal
		 * vector[5]=row.getCell(5).getRichStringCellValue().getString().trim();
		 * //rutemp
		 * vector[6]=row.getCell(6).getRichStringCellValue().getString().trim();
		 * //dvrutemp
		 * vector[7]=row.getCell(7).getRichStringCellValue().getString().trim();
		 * //rutaf
		 * vector[8]=row.getCell(8).getRichStringCellValue().getString().trim();
		 * //dvrutaf
		 * vector[9]=row.getCell(12).getRichStringCellValue().getString().trim();
		 * //mes valta1
		 * vector[10]=row.getCell(13).getRichStringCellValue().getString().trim();
		 * //mes valta2
		 * vector[11]=row.getCell(14).getRichStringCellValue().getString().trim();
		 * //mes valta3
		 * vector[12]=row.getCell(15).getRichStringCellValue().getString().trim();
		 * //mes valta4
		 * vector[13]=row.getCell(16).getRichStringCellValue().getString().trim();
		 * //mes valta5
		 * vector[14]=row.getCell(17).getRichStringCellValue().getString().trim();
		 * //mes valta6
		 * vector[15]=row.getCell(18).getRichStringCellValue().getString().trim();
		 * //val promedio
		 * vector[16]=row.getCell(19).getRichStringCellValue().getString().trim();
		 * //cod tramo
		 * vector[17]=row.getCell(20).getRichStringCellValue().getString().trim();
		 * //val tramo
		 * 
		 * fail=this.validaholding(vector, i+1);
		 * 
		 * if(fail.length()>0) result.add(fail);
		 * 
		 * if(result.size()==0){
		 * 
		 * oafil=new HoldingafiliadosTO();
		 * 
		 * oafil.setPeriodo(Integer.parseInt(vector[0]));
		 * oafil.setOficina(Integer.parseInt(vector[3]));
		 * oafil.setSucursal(Integer.parseInt(vector[4]));
		 * oafil.setRutempresa(Integer.parseInt(vector[5]));
		 * oafil.setDvempresa(vector[6]);
		 * oafil.setRuttrabajador(Integer.parseInt(vector[7]));
		 * oafil.setDvtrabajador(vector[8]);
		 * oafil.setNombre(celda[11].trim().toUpperCase() + " " +
		 * celda[9].trim().toUpperCase() + " " +
		 * celda[10].trim().toUpperCase());
		 * oafil.setRemuneracionesmismoempleador(0);
		 * oafil.setOtrasremuneraciones(0);
		 * oafil.setRentatrabajadorindependiente(0); oafil.setSubsidio(0);
		 * oafil.setPensiones(0); oafil.setTotalingresos(0);
		 * oafil.setNumeromeses(meses);
		 * oafil.setIngresopromedio(Integer.parseInt(vector[15]));
		 * oafil.setTrabajadorconsindeclaracion(0);
		 * oafil.setCodigotramo(Integer.parseInt(vector[16]));
		 * oafil.setValortramo(Integer.parseInt(vector[17]));
		 * 
		 * lista.add(oafil); } }
		 */

	/*	for (int i = 1; i < workbook.getLastRow() + 1; i++) {

			for (int j = 0; j < 21; j++) {
				celda[j] = workbook.getText(i, j);

			}

			int meses = 0;
			int c = 0;
			for (int k = 12; k < 18; k++) {

				if (Integer.parseInt(celda[k]) > 0) {
					c++;
				}
			}

			meses = c;

			vector[0] = workbook.getText(i, 0); // periodo
			vector[1] = workbook.getText(i, 1); // holding
			vector[2] = workbook.getText(i, 2); // dvholding
			vector[3] = workbook.getText(i, 3); // oficina
			vector[4] = workbook.getText(i, 4); // sucursal
			vector[5] = workbook.getText(i, 5); // rutemp
			vector[6] = workbook.getText(i, 6); // dvrutemp
			vector[7] = workbook.getText(i, 7); // rutaf
			vector[8] = workbook.getText(i, 8); // dvrutaf
			vector[9] = workbook.getText(i, 12); // mes valta1
			vector[10] = workbook.getText(i, 13); // mes valta2
			vector[11] = workbook.getText(i, 14); // mes valta3
			vector[12] = workbook.getText(i, 15); // mes valta4
			vector[13] = workbook.getText(i, 16); // mes valta5
			vector[14] = workbook.getText(i, 17); // mes valta6
			vector[15] = workbook.getText(i, 18); // val promedio
			vector[16] = workbook.getText(i, 19) != null
					&& workbook.getText(i, 19).trim().length() > 0 ? workbook
					.getText(i, 19) : "0"; // cod tramo
			vector[17] = workbook.getText(i, 20) != null
					&& workbook.getText(i, 20).trim().length() > 0 ? workbook
					.getText(i, 20) : "0"; // val tramo

			fail = this.validaholding(vector, i + 1);
			if (fail.length() > 0)
				result.add(fail);

			if (result.size() == 0) {

				oafil = new HoldingafiliadosTO();

				oafil.setPeriodo(Integer.parseInt(vector[0]));
				oafil.setOficina(Integer.parseInt(vector[3]));
				oafil.setSucursal(Integer.parseInt(vector[4]));
				oafil.setRutempresa(Integer.parseInt(vector[5]));
				oafil.setDvempresa(vector[6]);
				oafil.setRuttrabajador(Integer.parseInt(vector[7]));
				oafil.setDvtrabajador(vector[8]);
				oafil.setNombre(celda[11].trim().toUpperCase() + " "
						+ celda[9].trim().toUpperCase() + " "
						+ celda[10].trim().toUpperCase());
				oafil.setRemuneracionesmismoempleador(0);
				oafil.setOtrasremuneraciones(0);
				oafil.setRentatrabajadorindependiente(0);
				oafil.setSubsidio(0);
				oafil.setPensiones(0);
				oafil.setTotalingresos(0);
				oafil.setNumeromeses(meses);
				oafil.setIngresopromedio(Integer.parseInt(vector[15]));
				oafil.setTrabajadorconsindeclaracion(0);
				oafil.setCodigotramo(Integer.parseInt(vector[16]));
				oafil.setValortramo(Integer.parseInt(vector[17]));

				lista.add(oafil);

			}
		}

		workbook = null;
		celda = null;
		is.close();

		System.out.println("fin create report");

		if (result.size() == 0) {
			return lista;
		} else {
			result.add("error");
			return result;
		}

	}
*/
	public String validaholding(String v[], int fila) throws Exception {

		Vector vector = new Vector();
		String errores = "";

		String ruttrab = "";
		String ruttrabdv = "";
		int mes = 0;
		int meses = 0;

		ruttrab = v[7];
		ruttrabdv = v[8];

		// Properties Carpetas = new Properties();
		// Carpetas.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		String periodo = Config.getProperty("PERIODO");

		for (int i = 9; i < 15; i++) {
			try {

				mes = Integer.parseInt(v[i]);

			} catch (NumberFormatException ignored) {
				vector.add("6");

			}
		}

		int m = 0;
		for (int n = 3; n < 5; n++) {
			try {

				int g = Integer.parseInt(v[n]);

			} catch (NumberFormatException ignored) {
				vector.add("6");

			}
		}

		if (!v[0].trim().equalsIgnoreCase(periodo)) {
			vector.add("7");
		}
		if (!this.isNumeric(v[1])) {
			vector.add("6");
		}
		if (!this.isNumeric(v[3])) {
			vector.add("6");
		}
		if (!this.isNumeric(v[4])) {
			vector.add("6");
		}
		if (!this.isNumeric(v[5])) {
			vector.add("6");
		}
		if (!this.isNumeric(v[7])) {
			vector.add("6");
		}
		if (!this.isNumeric(v[15])) {
			vector.add("6");
		}
		if (!this.isNumeric(v[16])) {
			vector.add("6");
		}
		if (!this.isNumeric(v[17])) {
			vector.add("6");
		}

		try {

			if (!this.validaDigito(v[5], v[6])) {
				vector.add("8");
			}
			if (!this.validaDigito(v[7], v[8])) {
				vector.add("9");
			}

		} catch (NumberFormatException ignored) {
			vector.add("6");
		}

		for (int j = 0; j < vector.size(); j++) {

			errores = errores + vector.get(j) + "/";

		}

		if (errores.length() > 0) {
			errores = errores.substring(0, errores.length() - 1);
		}

		if (errores.length() > 0) {
			try {
				int t = Integer.parseInt(ruttrab);

			} catch (NumberFormatException ignored) {
				ruttrab = "0";

			}
			errores = errores + ";" + ruttrab + "/" + ruttrabdv + "/" + fila;
		}

		return errores;
	}

	public boolean esholding(FormFile archivo, String extension)
			throws Exception {

		InputStream is = null;
		boolean resu = false;

		try {
			byte[] fileData = archivo.getFileData();
			is = new ByteArrayInputStream(fileData);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		WorkBook workbook = new WorkBook();

		try {
			if (extension.equalsIgnoreCase("xls"))
				workbook.read(is);
			else if (extension.equalsIgnoreCase("xlsx"))
				workbook.readXLSX(is);

			// new code***
			/*
			 * byte[] fileData = archivo.getFileData(); is = new
			 * ByteArrayInputStream(fileData);
			 * 
			 * HSSFWorkbook wb = new HSSFWorkbook(is); //referencia al archivo
			 * HSSFSheet sheet = wb.getSheetAt(0); //referencia a la primera
			 * hoja del archivo
			 */
			// ***********
			String celda = workbook.getText(0, 1);
			System.out.println(">>celda " + celda);
			if (celda.trim().equalsIgnoreCase("RUTHOLDING")) {
				resu = true;
			} else {
				resu = false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

		return resu;
	}

	public void zipFolder(String inf, String outf, String ruta) {
		try {
			File inFolder = new File(inf);
			File outFolder = new File(outf);
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
					new FileOutputStream(outFolder)));
			BufferedInputStream in = null;
			byte[] data = new byte[1000];
			String files[] = inFolder.list();
			for (int i = 0; i < files.length; i++) {
				in = new BufferedInputStream(new FileInputStream(inFolder
						.getPath()
						+ "/carpeta/" + files[i]), 1000);
				System.out.println(inFolder.getPath() + "/carpeta/" + files[i]);
				out.putNextEntry(new ZipEntry(files[i]));
				int count;
				while ((count = in.read(data, 0, 1000)) != -1) {
					out.write(data, 0, count);
				}
				out.closeEntry();
			}
			out.flush();
			out.close();

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	//COMPARA UNA FECHA PASADA POR PARÁMETRO CON LA FECHA ACTUAL
	//LA FECHA DEBE SER PASADA CON EL FORMATO dd/MM/yyyy
	//RETORNA 0 SI LAS FECHAS SON IGUALES, 1 SI LA FECHA PROPORCIONADA ES MENOR O 2 SI ES MAYOR
	//RETORNA 3 EN CASO DE ERROR
	public int compareCurrentDay(String dateCompare) {
		try {
			if (dateCompare.split("/").length != 3)
				return 3; //error, fecha no cumple con el formato

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			String strFecha1 = dateCompare;

			Date fecha1 = sdf.parse(strFecha1, new ParsePosition(0));

			Calendar c = Calendar.getInstance();

			String currentDay = String.valueOf(c.get(Calendar.DATE));
			String currentMonth = String.valueOf(c.get(Calendar.MONTH) + 1);
			String currentYear = String.valueOf(c.get(Calendar.YEAR));

			for (; currentDay.length() < 2; currentDay = "0" + currentDay)
				;
			for (; currentMonth.length() < 2; currentMonth = "0" + currentMonth)
				;

			String currentDate = currentDay + "/" + currentMonth + "/"
					+ currentYear;

			Date fecha2 = sdf.parse(currentDate, new ParsePosition(0));

			if (strFecha1.equals(currentDate)) {
				return 0; //las fechas son iguales
			} else if (fecha1.before(fecha2)) {
				return 1; //la fecha proporcionada es menor que la fecha actual
			} else {
				return 2; //la fecha proporcionada es mayor que la fecha actual
			}
		} catch (Exception e) {
			return 3; //error al procesar
		}
	}
	
	
	public boolean validaCaracteresEspeciales (String cadena){
	    if( !StringUtils.isAlphaSpace(cadena)){
	    	cadena=cadena.replaceAll("\\.", "");
	    	cadena=cadena.replaceAll("-", "");
	    	cadena=cadena.replaceAll("#", "Ñ");
	    	 if( !StringUtils.isAlphaSpace(cadena)){
	    		 return false;
	    	 }
	    }
	    
	    return true;
		//boolean alfa = Pattern.matches("^[A-Za-z\u00C0-\u017F]*$", cadena);
		//return alfa;
	}
	
	public String devuelveColumna(int numCol){
		  String numeroColumna = "";
	      switch ( numCol ) {
	      case 0:
	    	  numeroColumna="A";
	           break;
	      case 1:
	    	  numeroColumna="B";
	           break;
	      case 2:
	    	  numeroColumna="C";
	           break;
	      case 3:
	    	  numeroColumna="D";
	           break;
	      case 4:
	    	  numeroColumna="E";
	           break;
	      case 5:
	    	  numeroColumna="F";
	           break;
	      case 6:
	    	  numeroColumna="G";
	           break;
	      case 7:
	    	  numeroColumna="H";
	           break;
	      case 8:
	    	  numeroColumna="I";
	           break;
	      case 9:
	    	  numeroColumna="J";
	           break;
	      case 10:
	    	  numeroColumna="K";
	           break;
	      case 11:
	    	  numeroColumna="L";
	           break;
	      case 12:
	    	  numeroColumna="M";
	           break;
	      case 13:
	    	  numeroColumna="N";
	           break;
	      case 14:
	    	  numeroColumna="O";
	           break;
	      case 15:
	    	  numeroColumna="P";
	           break;
	      case 16:
	    	  numeroColumna="Q";
	           break;
	      case 17:
	    	  numeroColumna="R";
	           break;
	      case 18:
	    	  numeroColumna="S";
	           break;
	      case 19:
	    	  numeroColumna="T";
	           break;
	      case 20:
	    	  numeroColumna="U";
	           break;
	      default:
	    	  numeroColumna="fuera de rango";
	           break;
	      }
	      
	      return numeroColumna;
		
	}
	
	public String getFechaHora()
	{
		Date hoy=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd", new Locale("cl"));
		SimpleDateFormat sdf2=new SimpleDateFormat("HHmmss", new Locale("cl"));
		String hora=sdf2.format(hoy);
		String fecha=sdf.format(hoy);
		String ext=fecha+hora;

		return ext;
	}
	
}