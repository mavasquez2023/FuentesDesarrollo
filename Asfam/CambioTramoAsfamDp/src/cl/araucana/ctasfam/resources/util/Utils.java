package cl.araucana.ctasfam.resources.util;

//--alexis advise 15-06-2012--//

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.upload.FormFile;

import cl.araucana.ctasfam.business.to.AfiliadosTO;
import cl.araucana.ctasfam.business.to.FlujoTO;
import cl.araucana.ctasfam.business.to.HoldingafiliadosTO;
import cl.araucana.ctasfam.business.to.ProcesoBashTO;
import cl.araucana.ctasfam.integration.jdbc.dao.AraucanaJdbcDao;
import cl.araucana.ctasfam.integration.jdbc.dao.impl.Mejoras2016DaoImpl;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.ExtendedIOException;
import com.ibm.as400.access.IFSFileInputStream;
import com.ibm.as400.access.IFSFileOutputStream;
import com.ibm.as400.access.IFSJavaFile;
import com.smartxls.WorkBook;



public class Utils {

	AraucanaJdbcDao dao=new AraucanaJdbcDao();
	FlujoTO flujo=new FlujoTO();

	String fail=null;
	
	String rutaBash = null;

	Encargado enc =new Encargado();


	private static Properties Config = null;

	public Utils(){
		if(Config == null)
		{
			Config = new Properties();

			try{
				Config.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
			}catch(Exception e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public AS400 creaConexionAS400() throws IOException {
		//Properties Config=new Properties();
		//Config.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		String servidor=Config.getProperty("SERVIDOR");
		String user=Config.getProperty("USER");
		String pass=Config.getProperty("PASS");
		return new AS400(servidor, user, pass);
	}

	public void cierraconexionAS400(AS400 as400) {
		as400.disconnectAllServices();
	}

	private void getEstadoAS400(AS400 as400) {
		if(as400.isConnected()){
			System.out.println("AS400 Conectado...");
		} else {
			System.out.println("AS400 Desconectado...");
		}
	}


	public boolean empresaTieneArchivosPropuesta(String rutEmpresa)throws IOException{
		//Properties Config=new Properties();
		//Config.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		String ruta=Config.getProperty("RUTAAS400");

		AS400 as400 = creaConexionAS400();
		//getEstadoAS400(as400);

		boolean test = false;
		try {
			File file = new IFSJavaFile(as400, ruta + rutEmpresa + "/" + rutEmpresa + ".txt");
			//getEstadoAS400(as400);
			test = file.exists();
		} catch (Exception e) {
			e.printStackTrace();
		}

		cierraconexionAS400(as400);
		//getEstadoAS400(as400);

		return test;

	}


	public File[] obtenerArchivosPropuesta(String rutEmpresa)throws IOException{


		//Properties Config=new Properties();
		File[] result=new File[3];
		//Config.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		String ruta=Config.getProperty("RUTAAS400");


		AS400 as400=this.creaConexionAS400();
		result=this.getListaDeArchivos(as400, ruta + "/" + rutEmpresa);

		this.cierraconexionAS400(as400);
		return result;
	}


	public boolean existFile(AS400 as4001, String pathfile)throws IOException {
		File file;
		AS400 as400=this.creaConexionAS400();
		if (as400 == null) {
			file = new File(pathfile);

		} else {
			file = new IFSJavaFile(as400, pathfile);

		}
		this.cierraconexionAS400(as400);
		return file.exists();
	}

	public File[] getListaDeArchivos(AS400 as400, String folderOfile) {
		File file;
		File[] listFiles = null;
		try {
			if (as400 == null) {
				file = new File(folderOfile);
			} else {
				file = new IFSJavaFile(as400, folderOfile);
			}
			// Se extrae lista de archivos dependiendo si 'folderOfile' es una
			// carpeta
			if (file.isDirectory()) {
				listFiles = file.listFiles();
				// Se arma el nombre del archivo zip en caso que pathfileout no
				// venga
			} else {
				listFiles = new File[1];
				listFiles[0] = file.getAbsoluteFile();
				// Se arma el nombre del archivo zip en caso que pathfileout no
				// venga
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.cierraconexionAS400(as400);
		return listFiles;
	}


	public void leerArchivoBintemp(AS400 as400, String pathfile, String destino) {
		IFSFileInputStream f1=null;
		IFSJavaFile file=null;

		File path=new File(pathfile);

		byte[] buffer= new byte[1024 * 64];
		try {

			System.out.println("pathfile " + pathfile);
			file = new IFSJavaFile(as400, pathfile);
			f1 = new IFSFileInputStream(file);
			//se determina largo del archivo
			int largo = f1.available();

			FileOutputStream target = null;
			target = new FileOutputStream(destino);
			int bytesRead = f1.read(buffer);
			while (bytesRead > 0) {
				target.write(buffer, 0, bytesRead);
				bytesRead = f1.read(buffer);
			}

			f1.close();
			target.close();
			this.cierraconexionAS400(as400);
			//return buffer;
		} catch (Exception e) {
			e.printStackTrace();

		}
	}


	public List createExcel(FormFile Archivo, String extension)
			throws Exception {
		System.out.println("begin create report");

		//InputStream is = this.excel(extension, Archivo);

		// new code**/
		byte[] fileData = Archivo.getFileData(); 
		InputStream is = new ByteArrayInputStream(fileData);

		HSSFWorkbook wb = new HSSFWorkbook(is); //referencia al archivo
		HSSFSheet sheet = wb.getSheetAt(0); //referencia a la primera hoja del archivo

		int ultimaFila = sheet.getLastRowNum();
		// ***********
		List result = new ArrayList();
		AfiliadosTO oafil = new AfiliadosTO();

		List lista = new ArrayList();
		String celda[] = new String[21];

		ArrayList numColum = new ArrayList();
		ArrayList recValidador = new ArrayList();

		/*WorkBook workbook = new WorkBook();
		if (extension.equalsIgnoreCase("xls"))
			workbook.read(is);
		else if (extension.equalsIgnoreCase("xlsx"))
			workbook.readXLSX(is);

		if (!"periodo".equalsIgnoreCase(workbook.getText(0, 0).trim()
				.toLowerCase().replaceAll("í", "i"))) {
			result.add("13;0/0/0");
		}*/

		// new code***
		for(int i = 0; i <= ultimaFila - 1; i++) { 
			HSSFRow row = sheet.getRow(1 + i); //obtenemos la fila

			for(int j=0;j<21;j++) {
				if(null == row.getCell(j)){
					celda[j] = ""; 
				}else
				if(row.getCell(j).getCellType() == 1){
					celda[j] = row.getCell(j).getRichStringCellValue().getString().trim(); 
				}else
				if(row.getCell(j).getCellType() == 0){
					celda[j] = String.valueOf(Math.round(row.getCell(j).getNumericCellValue()));
				}
			}

				int v=0;

				for(int k=0;k<21;k++) { if(celda[k].trim().equals("")) { v++; } }

				if(v!=21) { recValidador=this.validador(celda, i+1, extension);
				
				int size = recValidador.size();
				
				fail = (String) recValidador.get(0);
				numColum = (ArrayList) recValidador.get(1);

				if(fail.length()>0) {     
				result.add(fail);
				result.add(numColum);
				}

				if(result.size()==0) { oafil=new AfiliadosTO();

				oafil.setPeriodo(Integer.parseInt(celda[0]));
				oafil.setOficina(Integer.parseInt(celda[1].trim()));
				oafil.setSucursal(Integer.parseInt(celda[2].trim()));
				oafil.setRutempresa(Integer.parseInt(celda[3]));
				oafil.setDvempresa(celda[4]);
				oafil.setRuttrabajador(Integer.parseInt(celda[5]));
				oafil.setDvtrabajador(celda[6]);
				oafil.setApellidopaterno(celda[7].toUpperCase());
				oafil.setApellidomaterno(celda[8].toUpperCase());
				oafil.setNombreafiliado(celda[9].toUpperCase());
				oafil.setRemuneracionesmismoempleador(Integer.parseInt(celda[10]));
				oafil.setOtrasremuneraciones(Integer.parseInt(celda[11]));
				oafil.setRentatrabajadorindependiente(Integer.parseInt(celda[12]));
				oafil.setSubsidio(Integer.parseInt(celda[13]));
				oafil.setPensiones(Integer.parseInt(celda[14]));
				oafil.setTotalingresos(Integer.parseInt(celda[15]));
				oafil.setNumeromeses(Integer.parseInt(celda[16]));
				oafil.setIngresopromedio(Integer.parseInt(celda[17]));
				oafil.setTrabajadorconsindeclaracion(Integer.parseInt(celda[18]));
				oafil.setCodigotramo(Integer.parseInt(celda[19]));
				oafil.setValortramo(Integer.parseInt(celda[20].trim()));
				lista.add(oafil); } } }

		// ***********
		/*for (int i = 1; i < workbook.getLastRow() + 1; i++) {
			// en este for se llena el vector 'celda' con los valores del
			// archivo excel
			for (int j = 0; j < 21; j++) {
				celda[j] = workbook.getText(i, j); // rescatamos el valor de la
													// celda en la posición
													// FILA, COLUMNA
			}

			int v = 0;

			for (int k = 0; k < 21; k++) {
				if (celda[k].trim().equals("")) {
					v++;
				}
			}

			if (v != 21) {
				// fail=this.validador(celda, i+1);
				fail = this.validador(celda, i + 1);

				if (fail.length() > 0)
					result.add(fail);

				if (result.size() == 0) {
					oafil = new AfiliadosTO();

					oafil.setPeriodo(Integer.parseInt(celda[0]));
					oafil.setOficina(Integer.parseInt(celda[1].trim()));
					oafil.setSucursal(Integer.parseInt(celda[2].trim()));
					oafil.setRutempresa(Integer.parseInt(celda[3]));
					oafil.setDvempresa(celda[4]);
					oafil.setRuttrabajador(Integer.parseInt(celda[5]));
					oafil.setDvtrabajador(celda[6]);
					oafil.setApellidopaterno(celda[7].toUpperCase());
					oafil.setApellidomaterno(celda[8].toUpperCase());
					oafil.setNombreafiliado(celda[9].toUpperCase());
					oafil.setRemuneracionesmismoempleador(Integer
							.parseInt(celda[10]));
					oafil.setOtrasremuneraciones(Integer.parseInt(celda[11]));
					oafil.setRentatrabajadorindependiente(Integer
							.parseInt(celda[12]));
					oafil.setSubsidio(Integer.parseInt(celda[13]));
					oafil.setPensiones(Integer.parseInt(celda[14]));
					oafil.setTotalingresos(Integer.parseInt(celda[15]));
					oafil.setNumeromeses(Integer.parseInt(celda[16]));
					oafil.setIngresopromedio(Integer.parseInt(celda[17]));
					oafil.setTrabajadorconsindeclaracion(Integer
							.parseInt(celda[18]));
					oafil.setCodigotramo((celda[19] != null
							&& celda[19].trim().length() > 0 ? Integer
							.parseInt(celda[19].trim()) : 0));
					oafil.setValortramo((celda[20] != null
							&& celda[20].trim().length() > 0 ? Integer
							.parseInt(celda[20].trim()) : 0));
					lista.add(oafil);
				}
			}
		}

		workbook = null;
		celda = null;
		is.close();*/

		System.out.println("fin create report");

		if (result.size() == 0) {

			return lista;

		} else {
			result.add("error");
			return result;
		}

	}


	public List createExcelZip(File Archivo, String extension) throws Exception {

		List result = new ArrayList();
		InputStream is = this.excelZip(extension, Archivo);

		// new code***
		HSSFWorkbook wb = new HSSFWorkbook(is); //referencia al archivo
		HSSFSheet sheet = wb.getSheetAt(0); //referencia a la primera hoja del archivo

		int ultimaFila = sheet.getLastRowNum();
		// ***********
		AfiliadosTO oafil = new AfiliadosTO();
		ArrayList numColum = new ArrayList();
		ArrayList recValidador = new ArrayList();

		List lista = new ArrayList();
		String celda[] = new String[21];

		WorkBook workbook = new WorkBook();
		if (extension.equalsIgnoreCase("xls"))
			workbook.read(is);
		else if (extension.equalsIgnoreCase("xlsx"))
			workbook.readXLSX(is);

		if (!"periodo".equalsIgnoreCase(workbook.getText(0, 0).trim()
				.toLowerCase().replaceAll("í", "i"))) {
			result.add("13;0/0/0");
		}

		// new code***
		for(int i = 0; i <= ultimaFila - 1; i++) { HSSFRow row =
				sheet.getRow(1 + i); //obtenemos la fila

				for(int j=0;j<21;j++) { if(row.getCell(j).getCellType() == 1){
				celda[j] =
				row.getCell(j).getRichStringCellValue().getString().trim(); }else
				if(row.getCell(j).getCellType() == 0){ celda[j] =
				String.valueOf(Math.round(row.getCell(j).getNumericCellValue())); } }

				int v=0;

				for(int k=0;k<21;k++) { if(celda[k].trim().equals("")) { v++; } }

				if(v!=21) { recValidador=this.validador(celda, i+1, extension); 
				fail = (String) recValidador.get(0);
				numColum = (ArrayList) recValidador.get(1);
				
				
				if(fail.length()>0) {
					result.add(fail);
					result.add(numColum);
					}

				if(result.size()==0) { oafil=new AfiliadosTO();

				oafil.setPeriodo(Integer.parseInt(celda[0]));
				oafil.setOficina(Integer.parseInt(celda[1].trim()));
				oafil.setSucursal(Integer.parseInt(celda[2].trim()));
				oafil.setRutempresa(Integer.parseInt(celda[3]));
				oafil.setDvempresa(celda[4]);
				oafil.setRuttrabajador(Integer.parseInt(celda[5]));
				oafil.setDvtrabajador(celda[6]);
				oafil.setApellidopaterno(celda[7].toUpperCase());
				oafil.setApellidomaterno(celda[8].toUpperCase());
				oafil.setNombreafiliado(celda[9].toUpperCase());
				oafil.setRemuneracionesmismoempleador(Integer.parseInt(celda[10]));
				oafil.setOtrasremuneraciones(Integer.parseInt(celda[11]));
				oafil.setRentatrabajadorindependiente(Integer.parseInt(celda[12]));
				oafil.setSubsidio(Integer.parseInt(celda[13]));
				oafil.setPensiones(Integer.parseInt(celda[14]));
				oafil.setTotalingresos(Integer.parseInt(celda[15]));
				oafil.setNumeromeses(Integer.parseInt(celda[16]));
				oafil.setIngresopromedio(Integer.parseInt(celda[17]));
				oafil.setTrabajadorconsindeclaracion(Integer.parseInt(celda[18]));
				oafil.setCodigotramo(Integer.parseInt(celda[19]));
				oafil.setValortramo(Integer.parseInt(celda[20].trim()));
				lista.add(oafil);
				 } } }

		/*for (int i = 1; i < workbook.getLastRow() + 1; i++) {

			for (int j = 0; j < 21; j++) {
				celda[j] = workbook.getText(i, j);

			}

			int v = 0;
			for (int k = 0; k < 21; k++) {
				if (celda[k].trim().equals("")) {
					v++;
				}
			}
			if (v != 21) {

				fail = this.validador(celda, i + 1);
				if (fail.length() > 0)
					result.add(fail);

				if (result.size() == 0) {

					oafil = new AfiliadosTO();

					oafil.setPeriodo(Integer.parseInt(celda[0]));
					oafil.setOficina(Integer.parseInt(celda[1].trim()));
					oafil.setSucursal(Integer.parseInt(celda[2].trim()));
					oafil.setRutempresa(Integer.parseInt(celda[3]));
					oafil.setDvempresa(celda[4]);
					oafil.setRuttrabajador(Integer.parseInt(celda[5]));
					oafil.setDvtrabajador(celda[6]);
					oafil.setApellidopaterno(celda[7].toUpperCase());
					oafil.setApellidomaterno(celda[8].toUpperCase());
					oafil.setNombreafiliado(celda[9].toUpperCase());
					oafil.setRemuneracionesmismoempleador(Integer
							.parseInt(celda[10]));
					oafil.setOtrasremuneraciones(Integer.parseInt(celda[11]));
					oafil.setRentatrabajadorindependiente(Integer
							.parseInt(celda[12]));
					oafil.setSubsidio(Integer.parseInt(celda[13]));
					oafil.setPensiones(Integer.parseInt(celda[14]));
					oafil.setTotalingresos(Integer.parseInt(celda[15]));
					oafil.setNumeromeses(Integer.parseInt(celda[16]));
					oafil.setIngresopromedio(Integer.parseInt(celda[17]));
					oafil.setTrabajadorconsindeclaracion(Integer
							.parseInt(celda[18]));
					oafil.setCodigotramo((celda[19] != null
							&& celda[19].trim().length() > 0 ? Integer
							.parseInt(celda[19].trim()) : 0));
					oafil.setValortramo((celda[20] != null
							&& celda[20].trim().length() > 0 ? Integer
							.parseInt(celda[20].trim()) : 0));
					lista.add(oafil);

				}
			}
		}

		workbook = null;
		celda = null;
		is.close();*/

		if (result.size() == 0) {
			return lista;
		} else {
			result.add("error");
			return result;
		}

	}


	public List creaTexto(File Archivo,String extension) throws IOException, Exception {

		List result = new ArrayList();
		AfiliadosTO oafil = null;
		List lista = new ArrayList();
		ArrayList numColum = new ArrayList();
		ArrayList recValidador = new ArrayList();

		int v[][] = new int[21][2];
		v = dao.getTexto("txt");

		try {

			BufferedReader reader = new BufferedReader(new FileReader(Archivo));
			String linea;

			int k = 1;
			while ((linea = reader.readLine()) != null) {

				oafil = new AfiliadosTO();
				String param[] = new String[21];
				if (linea.trim().length() != 0) {

					param[0] = linea.substring(v[0][0] - 1, v[0][1]);

					for (int j = 1; j < 21; j++) {
						param[j] = linea.substring(v[j][0] - 1, v[j][0]
								+ v[j][1] - 1);
					}

					for (int i = 0; i < 21; i++) {
						if (k == 9) {
							System.out.print(param[i] + ";");
						}
					}

					if (linea.length() == 147) {
						recValidador = this.validador(param, k, extension);
						fail = (String) recValidador.get(0);
						numColum = (ArrayList) recValidador.get(1);
						
						if(fail.length()>0) {
							result.add(fail);
							result.add(numColum);
							}

						if (result.size() == 0) {

							oafil.setPeriodo(Integer.parseInt(param[0]));
							oafil.setOficina(Integer.parseInt(param[1].trim()));
							oafil
									.setSucursal(Integer.parseInt(param[2]
											.trim()));
							oafil.setRutempresa(Integer.parseInt(param[3]));
							oafil.setDvempresa(param[4]);
							oafil.setRuttrabajador(Integer.parseInt(param[5]));
							oafil.setDvtrabajador(param[6]);
							oafil.setApellidopaterno(param[7]);
							oafil.setApellidomaterno(param[8]);
							oafil.setNombreafiliado(param[9]);
							oafil.setRemuneracionesmismoempleador(Integer
									.parseInt(param[10]));
							oafil.setOtrasremuneraciones(Integer
									.parseInt(param[11]));
							oafil.setRentatrabajadorindependiente(Integer
									.parseInt(param[12]));
							oafil.setSubsidio(Integer.parseInt(param[13]));
							oafil.setPensiones(Integer.parseInt(param[14]));
							oafil.setTotalingresos(Integer.parseInt(param[15]));
							oafil.setNumeromeses(Integer.parseInt(param[16]));
							oafil.setIngresopromedio(Integer
									.parseInt(param[17]));
							oafil.setTrabajadorconsindeclaracion(Integer
									.parseInt(param[18]));
							oafil.setCodigotramo((param[19] != null
									&& param[19].trim().length() > 0 ? Integer
									.parseInt(param[19].trim()) : 0));
							oafil.setValortramo((param[20] != null
									&& param[20].trim().length() > 0 ? Integer
									.parseInt(param[20].trim()) : 0));

							lista.add(oafil);

						}
					} else {
						System.out.println("largo linea " + linea.length());
						result.add("4;" + param[5] + "/" + param[6] + "/" + k);
					}
					k++;

				} else {
					k++;
				}
			}

			reader.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (result.size() == 0) {
			return lista;
		} else {
			result.add("error");
			return result;
		}
	}

	public List creaCsvholding(File Archivo,String extension) throws Exception {


		List result=new ArrayList();
		HoldingafiliadosTO oafil=null;
		List lista=new ArrayList();
		String linea;
		String nombre=Archivo.getName();
		Utils util=new Utils();
		ArrayList numColum = new ArrayList();
		ArrayList recValidador = new ArrayList();






		BufferedReader reader= new BufferedReader(new FileReader(Archivo)); 

		linea=reader.readLine();
		String cabe[]=linea.split(";");
		String v[]=new String[18];





		int k=2;
		while((linea=reader.readLine())!=null) 
		{



			if(linea.trim().length()!=0){

				int meses=0;
				int c=0;


				oafil=new HoldingafiliadosTO();
				String temp[]=linea.split(";");
				for(int j=12;j<18;j++)
				{
					if(!util.isNumeric(temp[j]))
					{
						temp[j]="0";
					}
					if(Integer.parseInt(temp[j])>0){
						c++;
					}
				}
				int n=0;
				meses=c;
				v[0]=temp[0]; //periodo
				v[1]=temp[1]; //holding
				v[2]=temp[2]; //dvholding
				v[3]=temp[3]; //oficina
				v[4]=temp[4]; //sucursal
				v[5]=temp[5]; //rutemp
				v[6]=temp[6]; //dvrutemp
				v[7]=temp[7]; //rutaf
				v[8]=temp[8]; //dvrutaf
				v[9]=temp[12]; //mes valta1
				v[10]=temp[13]; //mes valta2
				v[11]=temp[14]; //mes valta3
				v[12]=temp[15]; //mes valta4
				v[13]=temp[16]; //mes valta5
				v[14]=temp[17]; //mes valta6
				v[15]=temp[18]; //val promedio
				v[16]=temp[19]; //cod tramo
				v[17]=temp[20]; //val tramo



				if(temp.length==21){
					recValidador=this.validaholding(v,k, extension);
					fail = (String) recValidador.get(0);
					numColum = (ArrayList) recValidador.get(1);
					
					if(fail.length()>0) {
					result.add(fail);
					result.add(numColum);
					}



					if(result.size()==0){
						oafil.setPeriodo(Integer.parseInt(v[0]));
						oafil.setOficina(Integer.parseInt(v[3]));
						oafil.setSucursal(Integer.parseInt(v[4]));
						oafil.setRutempresa(Integer.parseInt(v[5]));
						oafil.setDvempresa(v[6]);
						oafil.setRuttrabajador(Integer.parseInt(v[7]));
						oafil.setDvtrabajador(v[8]);
						oafil.setNombre(temp[11].trim().toUpperCase()+" "+ temp[9].trim().toUpperCase() + " " + temp[10].trim().toUpperCase());
						oafil.setRemuneracionesmismoempleador(0);
						oafil.setOtrasremuneraciones(0);
						oafil.setRentatrabajadorindependiente(0);
						oafil.setSubsidio(0);
						oafil.setPensiones(0);
						oafil.setTotalingresos(0);
						oafil.setNumeromeses(meses);
						oafil.setIngresopromedio(Integer.parseInt(v[15]));
						oafil.setTrabajadorconsindeclaracion(0);
						oafil.setCodigotramo(Integer.parseInt(temp[16]));
						oafil.setValortramo(Integer.parseInt(temp[17]));

						lista.add(oafil);
					}

				}
				else
				{
					result.add("5;" + temp[5] +"/" +temp[6]+"/"+ k);
				}



				k++;

			}
			else
			{
				k++;
			}

		}	





		reader.close();





		if (result.size() == 0) {
			return lista;
		} 
		else 
		{
			result.add("error");
			return result;
		}
	}




	public List creaTextoholding(File Archivo, String extension) throws IOException,Exception {


		List result=new ArrayList();
		HoldingafiliadosTO oafil=null;
		List lista=new ArrayList();
		Utils util=new Utils();
		String vector[]=new String[18];
		ArrayList numColum = new ArrayList();
		ArrayList recValidador = new ArrayList();



		int v[][]=new int[21][2];
		v=dao.getTexto("hol");





		try{



			BufferedReader reader= new BufferedReader(new FileReader(Archivo)); 
			String linea;

			int k=1;
			while((linea=reader.readLine())!=null) 
			{





				oafil=new HoldingafiliadosTO();
				String param[]=new String[21];
				if(linea.trim().length()!=0){



					param[0]=linea.substring(v[0][0]-1,v[0][1]);

					for(int j=1;j<21;j++){
						param[j]=linea.substring(v[j][0]-1,v[j][0]+v[j][1]-1);
					}



					for(int i=0;i<21;i++){
						if(k==9){
							System.out.print(param[i] + ";");
						}}

					if(linea.length()==149){
						int meses=0;
						int c=0;


						oafil=new HoldingafiliadosTO();

						for(int j=12;j<18;j++)
						{
							if(!util.isNumeric(param[j]))
							{
								param[j]="0";
							}
							if(Integer.parseInt(param[j])>0){
								c++;
							}
						}

						meses=c;

						vector[0]=param[0]; //periodo
						vector[1]=param[1]; //holding
						vector[2]=param[2]; //dvholding
						vector[3]=param[3]; //oficina
						vector[4]=param[4]; //sucursal
						vector[5]=param[5]; //rutemp
						vector[6]=param[6]; //dvrutemp
						vector[7]=param[7]; //rutaf
						vector[8]=param[8]; //dvrutaf
						vector[9]=param[12]; //mes valta1
						vector[10]=param[13]; //mes valta2
						vector[11]=param[14]; //mes valta3
						vector[12]=param[15]; //mes valta4
						vector[13]=param[16]; //mes valta5
						vector[14]=param[17]; //mes valta6
						vector[15]=param[18]; //val promedio
						vector[16]=param[19]; //cod tramo
						vector[17]=param[20]; //val tramo


						recValidador=this.validaholding(vector, k, extension);
						fail = (String) recValidador.get(0);
						numColum = (ArrayList) recValidador.get(1);
						
						if(fail.length()>0) {
						result.add(fail);
						result.add(numColum);
						}


						if(result.size()==0){

							oafil.setPeriodo(Integer.parseInt(vector[0]));
							oafil.setOficina(Integer.parseInt(vector[3]));
							oafil.setSucursal(Integer.parseInt(vector[4]));
							oafil.setRutempresa(Integer.parseInt(vector[5]));
							oafil.setDvempresa(vector[6]);
							oafil.setRuttrabajador(Integer.parseInt(vector[7]));
							oafil.setDvtrabajador(vector[8]);
							oafil.setNombre(param[11].trim().toUpperCase() + " " + param[9].trim().toUpperCase()+ " " + param[10].trim().toUpperCase());
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
					else
					{
						System.out.println("largo linea " + linea.length());
						result.add("4;" + param[5]+"/"+param[6]+"/" + k);
					}
					k++;

				}
				else
				{
					k++;
				}
			}

			reader.close();


		  }
		
		  catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		
		if (result.size() == 0) {
			return lista;
		}
		
		else 
		{
			result.add("error");
			return result;
		}
		
	}



	public List creaCsv(File Archivo, String extension) throws Exception {

		List result = new ArrayList();
		AfiliadosTO oafil = null;
		List lista = new ArrayList();
		String linea;
		String nombre = Archivo.getName();
		ArrayList numColum = new ArrayList();
		ArrayList recValidador = new ArrayList();

		BufferedReader reader = new BufferedReader(new FileReader(Archivo));

		int k = 1;
		while ((linea = reader.readLine()) != null) {

			if (!linea.replaceAll(";", "").trim().equals("")) {
				if (linea.trim().length() != 0) {

					oafil = new AfiliadosTO();
					String temp[] = linea.split(";");
					if (temp.length == 21) {
						recValidador = this.validador(temp, k, extension);
						fail = (String) recValidador.get(0);
						numColum = (ArrayList) recValidador.get(1);
						
						if(fail.length()>0) {
						result.add(fail);
						result.add(numColum);
						}
						
						

						if (result.size() == 0) {
							oafil.setPeriodo(Integer.parseInt(temp[0]));
							oafil.setOficina(Integer.parseInt(temp[1].trim()));
							oafil.setSucursal(Integer.parseInt(temp[2].trim()));
							oafil.setRutempresa(Integer.parseInt(temp[3]));
							oafil.setDvempresa(temp[4]);
							oafil.setRuttrabajador(Integer.parseInt(temp[5]));
							oafil.setDvtrabajador(temp[6]);
							oafil.setApellidopaterno(temp[7].toUpperCase());
							oafil.setApellidomaterno(temp[8].toUpperCase());
							oafil.setNombreafiliado(temp[9].toUpperCase());
							oafil.setRemuneracionesmismoempleador(Integer
									.parseInt(temp[10]));
							oafil.setOtrasremuneraciones(Integer
									.parseInt(temp[11]));
							oafil.setRentatrabajadorindependiente(Integer
									.parseInt(temp[12]));
							oafil.setSubsidio(Integer.parseInt(temp[13]));
							oafil.setPensiones(Integer.parseInt(temp[14]));
							oafil.setTotalingresos(Integer.parseInt(temp[15]));
							oafil.setNumeromeses(Integer.parseInt(temp[16]));
							oafil
									.setIngresopromedio(Integer
											.parseInt(temp[17]));
							oafil.setTrabajadorconsindeclaracion(Integer
									.parseInt(temp[18]));
							oafil.setCodigotramo((temp[19] != null
									&& temp[19].trim().length() > 0 ? Integer
									.parseInt(temp[19].trim()) : 0));
							oafil.setValortramo((temp[20] != null
									&& temp[20].trim().length() > 0 ? Integer
									.parseInt(temp[20].trim()) : 0));

							lista.add(oafil);
						}

					} else {
						result.add("5;" + temp[5] + "/" + temp[6] + "/" + k);
					}

				} else {
					k++;
				}
			} else {
				result.add("17;" + "0" + "/" + "0" + "/" + k);

			}
			k++;

		}

		reader.close();

		if (result.size() == 0) {
			return lista;
		} else {
			result.add("error");
			return result;
		}
	}





	public char getDigito(Object rut) {
		char p=0;
		try{
			int M=0,S=1,T=Integer.parseInt(rut.toString());
			for(;T!=0;T/=10)S=(S+T%10*(9-M++%6))%11;
			p= (char)(S!=0?S+47:75); 
		}
		catch(NumberFormatException ignored)
		{}
		return p;
	}


	public boolean validarNombre(File archivo) {
		String nombreArch=archivo.getName();
		String temp=this.extencion(archivo);
		if(archivo.isDirectory())
			return false;

		if(!temp.equalsIgnoreCase("txt")&&!temp.equalsIgnoreCase("csv")&&!temp.equalsIgnoreCase("zip")&&!temp.equalsIgnoreCase("xls")&&!temp.equalsIgnoreCase("xlsx"))
			return false;
		String patron="/(\"|\')/";
		if(patron.matches(nombreArch)) 
			return false; 
		return true;

	}

	public String extencion(File archivo){

		String nombreArch=archivo.getName();
		String temp=nombreArch.substring(nombreArch.lastIndexOf(".")+1, nombreArch.length());

		return temp;
	}


	public boolean unZip(String path, String destino){
		final int BUFFER = 2048;


		try {

			System.out.println("zip path entry" + path);
			BufferedOutputStream dest = null;
			FileInputStream fis = new FileInputStream(path);
			ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
			ZipEntry entry;

			File folder=new File(destino);
			if(!folder.exists())
				folder.mkdir();


			while((entry = zis.getNextEntry()) != null) {
				System.out.println("Extracting: " +destino+entry.getName());
				if(!entry.isDirectory())
				{
					int count;
					byte data[] = new byte[BUFFER];

					// write the files to the disk
					FileOutputStream fos = new FileOutputStream(destino+entry.getName());
					dest = new BufferedOutputStream(fos, BUFFER);
					while ((count = zis.read(data, 0, BUFFER))!= -1) {
						dest.write(data, 0, count);
					}
					dest.flush();
					dest.close();
				}
				else
				{

					return  false;
				}

			}
			zis.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public InputStream excel(String extension, FormFile archivo)throws Exception{

		InputStream  is=null;  

		try{

			byte[] fileData= archivo.getFileData();

			is = new ByteArrayInputStream(fileData);

		}
		catch(IOException ex)
		{ex.printStackTrace();
		}
		return is;

	}

	public InputStream excelZip(String extension, File archivo)throws Exception{

		InputStream  is=null;  

		try{



			is = new FileInputStream (archivo);


		}
		catch(IOException ex)
		{ex.printStackTrace();
		}
		return is;

	}

	public File creaArchivo(String filePath, FormFile Archivo, String ext) throws IOException,Exception{

		//Properties Config = new Properties();
		//Config.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));

		Date now=new Date();
		SimpleDateFormat sdfF=new SimpleDateFormat("yyyyMMdd", new Locale("cl"));
		SimpleDateFormat sdfH=new SimpleDateFormat("HHmmss", new Locale("cl"));
		String carpeta=sdfF.format(now) + "_" +  sdfH.format(now);

		//AS400 system=new AS400(servidor,user,pass);
		//ExplorerManagerAs400 as400=new ExplorerManagerAs400(system);
		Utils util=new Utils();
		String nombre=null;
		File file=new File(Archivo.getFileName());
		String fileName=Archivo.getFileName();

		File fileToCreate=null;

		if (!fileName.equals("")){

			File folder=new File(filePath + carpeta);
			if(!folder.exists())
				folder.mkdir();

			System.out.println("Server path: "+ filePath + carpeta +"/" + nombre + "_" +  ext + "."+ util.extencion(file));
			nombre=fileName.substring(0,fileName.indexOf("."));
			fileToCreate=new File(filePath + carpeta +"/" + nombre + "_" +  ext + "."+ util.extencion(file));

			if(!fileToCreate.exists())	{
				FileOutputStream fileOutStream=new FileOutputStream(fileToCreate);
				fileOutStream.write(Archivo.getFileData());
				fileOutStream.flush();
				fileOutStream.close();
			}
		}




		return  fileToCreate;
	}


	public void leerArchivoBinas400(AS400 as400, String pathfile, String destino) {
		FileInputStream f1=null;
		File file=null;

		byte[] buffer= new byte[1024 * 64];
		try {

			System.out.println("pathfile " + pathfile);
			file = new File(pathfile);

			try
			{
				f1 = new FileInputStream(file);
			}catch(Exception e){}

			//se determina largo del archivo
			//int largo = f1.available();
			String path=destino.substring(0, destino.lastIndexOf("/"));
			System.out.println("carpeta " + path);
			IFSJavaFile file2 = new IFSJavaFile(as400, path);
			if(!file2.exists()){
				file2.mkdir();
			}

			IFSFileOutputStream target = null;
			try{
				target = new IFSFileOutputStream(as400, destino, IFSFileOutputStream.SHARE_NONE, false);

				int bytesRead = f1.read(buffer);
				while (bytesRead > 0) {
					target.write(buffer, 0, bytesRead);
					bytesRead = f1.read(buffer);
				}

				f1.close();
				target.close();

			} catch(ExtendedIOException ignored) {
				System.out.println("Archivo ya existe");
			}

			//return buffer;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/* public File creaArchivoas400(String filePath, FormFile Archivo, String ext) throws IOException,Exception{

		 Properties Config = new Properties();
		  Config.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));

		  String rutaAs400=Config.getProperty("RUTAAS400RESPALDO");
		  Date now=new Date();
		  SimpleDateFormat sdfF=new SimpleDateFormat("yyyyMMdd", new Locale("cl"));
		  SimpleDateFormat sdfH=new SimpleDateFormat("HHmmss", new Locale("cl"));
		  String carpeta=sdfF.format(now) + "_" +  sdfH.format(now);

		  //AS400 system=new AS400(servidor,user,pass);
		  //ExplorerManagerAs400 as400=new ExplorerManagerAs400(system);
		  AS400 as400=this.creaConexionAS400();
			Utils util=new Utils();
			String nombre=null;
			File file=new File(Archivo.getFileName());
		    String fileName=Archivo.getFileName();

		    File fileToCreate=null;

			if (!fileName.equals("")){

				File folder=new File(filePath + carpeta);
				if(!folder.exists())
				folder.mkdir();

				System.out.println("Server path: "+ filePath + carpeta +"/" + nombre + "_" +  ext + "."+ util.extencion(file));
				nombre=fileName.substring(0,fileName.indexOf("."));
				 fileToCreate=new File(filePath + carpeta +"/" + nombre + "_" +  ext + "."+ util.extencion(file));

				if(!fileToCreate.exists())	{
					FileOutputStream fileOutStream=new FileOutputStream(fileToCreate);
					fileOutStream.write(Archivo.getFileData());
					fileOutStream.flush();
					fileOutStream.close();
				}
			}

			System.out.println(">> file " + fileToCreate.getAbsolutePath());
			System.out.println(">> as400 " + rutaAs400 + nombre + "/" + carpeta + "/"+ fileName);

			 this.leerArchivoBinas400(as400,filePath + carpeta +"/" + nombre + "_" +  ext + "."+ util.extencion(file),rutaAs400 + nombre +"/" + nombre + "_" +  ext + "."+ util.extencion(file));
			 this.cierraconexionAS400(as400);

			return  fileToCreate;
		  }



	 */

	public File creaArchivoas400(String filePath, FormFile Archivo, String ext) throws IOException,Exception{

		//Properties Config = new Properties();
		//Config.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		//String servidor=Config.getProperty("SERVIDOR");
		//String user=Config.getProperty("USER");
		//String pass=Config.getProperty("PASS");
		String rutaAs400=Config.getProperty("RUTAAS400RESPALDO");
		Date now=new Date();
		SimpleDateFormat sdfF=new SimpleDateFormat("yyyyMMdd", new Locale("cl"));
		SimpleDateFormat sdfH=new SimpleDateFormat("HHmmss", new Locale("cl"));
		String carpeta=sdfF.format(now) + "_" +  sdfH.format(now);


		//AS400 system=new AS400(servidor,user,pass);
		//ExplorerManagerAs400 as400=new ExplorerManagerAs400(system);
		//System.out.println("AS400 Conectado...");
		//Utils util=new Utils();

		String nombre=null;
		File file=new File(Archivo.getFileName());
		String fileName=Archivo.getFileName();



		File fileToCreate=null;

		if (!fileName.equals("")){

			File folder=new File(filePath + carpeta);
			if(!folder.exists())
				folder.mkdir();

			nombre=fileName.substring(0,fileName.indexOf("."));

			//carpeta = nombre;

			System.out.println("Server path: "+ filePath + carpeta +"/" + nombre + "_" +  ext + "."+ this.extencion(file));

			fileToCreate=new File(filePath + carpeta +"/" + nombre + "_" +  ext + "."+ this.extencion(file));

			try
			{
				if(!fileToCreate.exists())	{
					FileOutputStream fileOutStream=new FileOutputStream(fileToCreate);
					fileOutStream.write(Archivo.getFileData());
					fileOutStream.flush();
					fileOutStream.close();
				}
			}catch(Exception e){
				System.out.println("errorrrrr");
			}
		}

		System.out.println(">> file " + fileToCreate.getAbsolutePath());
		System.out.println(">> as400 " + rutaAs400 + nombre + "/" + carpeta + "/"+ fileName);

		AS400 as400 = creaConexionAS400();

		this.leerArchivoBinas400(as400 , filePath + carpeta +"/" + nombre + "_" +  ext + "."+ this.extencion(file) , rutaAs400 + nombre +"/" + nombre + "_" +  ext + "."+ this.extencion(file));

		rutaBash = rutaAs400 + nombre + "/" + nombre + "_" + ext + "." + this.extencion(file);
		
		//as400.disconect();
		//as400.estatusAS400();
		cierraconexionAS400(as400);

		return  fileToCreate;
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
			while( (c = in.read() ) != -1)
				out.write(c);

			in.close();
			out.close();
		} catch(IOException e) {
			System.err.println("Hubo un error de entrada/salida!!!");
		}
	}



	public String creaCarpetas(String rutaZip, String rutaRes, String ext, String extension)throws IOException,Exception{

		File folder=null;
		File folderDes=null;
		String nombre=null;
		String folderDespath=null;

		if(extension.equalsIgnoreCase("zip"))
		{
			folderDes=new File(rutaZip +"/" + ext);
			if(!folderDes.exists()) {
				folderDes.mkdir();
			}
		}

		folder=new File(rutaRes +"/"+ ext);

		if(!folder.exists()){
			folder.mkdir();
		}

		if(folderDes==null)
			folderDespath="NAN";
		else
			folderDespath=folderDes.getAbsolutePath();

		nombre=folder.getAbsolutePath() + "-" + folderDespath;


		return nombre;
	}

	public List procesaFicheros(String path,HttpServletRequest request)throws Exception{

		List listazip=new ArrayList();
		List listaTotal=new ArrayList();


		File directorio = new File(path);
		String [] ficheros = directorio.list();
		String extension=null;

		for (int i = 0; i < ficheros.length; i++) {
			try {

				System.out.println("Contenido del fichero " + path + "\\" +  ficheros[i]);



				String tipo="NORMAL";


				File fichero=new File(path + "\\" +  ficheros[i]);
				extension=this.extencion(fichero);
				listazip=this.procesaArchivosZip(extension, fichero,tipo, request);

				if(listazip.size()==0)
				{
					listaTotal.add("file/" + ficheros[i]);
				}

				for(int k=0;k<listazip.size();k++){
					if(k==0)
						listaTotal.add(listazip.get(k) + "/" + ficheros[i]+"/" + 0);
					else
						listaTotal.add(listazip.get(k) + "/" + ficheros[i]+"/" + 1); 
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return listaTotal;
	}

	public ArrayList validador(String string[], int fila, String extension) throws Exception {

		String periodo = Config.getProperty("PERIODO");

		String errores = "";
		Vector error = new Vector();
		ArrayList retValidador = new ArrayList();
		ArrayList numColum = new ArrayList(); // numero de columna en el reporte de error
		                                      // se le suma uno, por que empieza de cero
		try {

			int b = 0;
			for (int i = 0; i < string.length; i++) {
				if (string[i].trim().equals("")) {
					b++;
				}
			}

			if (b != string.length) {

				for (int i = 0; i < string.length; i++) {

					if (i != 4 && i != 6 && i != 7 && i != 8 && i != 9
							&& i != 15 && i != 16 && i != 17 && i != 20
							&& i != 19) {
						if (!this.noformato(string[i].trim())) {
							error.add("6");
							if (extension.equalsIgnoreCase("xls")
									|| extension.equalsIgnoreCase("xlsx")){
							numColum.add(devuelveColumna(i) );
							}
							else {
								numColum.add(i+1);
							}
						}
					}
				}			
				
				if (string[4].trim().equals(""))
					string[4] = "20";

				if (string[6].trim().equals(""))
					string[6] = "20";

				if (!this.isNumeric(string[3]))
					string[3] = "0";
				if (!this.isNumeric(string[5]))
					string[5] = "0";

				for (int i = 1; i < 7; i++) {
					if (string[i].trim().equals(""))
						string[i] = "0";
				}

				if (string[19].trim().equals("")) {
					string[19] = "0";
				}
				if (string[20].trim().equals("")) {
					string[20] = "0";
				}

				try {
					
					if (!this.validaDigito(string[3], string[4])) {
						error.add("8");
						
						if (extension.equalsIgnoreCase("xls")
								|| extension.equalsIgnoreCase("xlsx")){
							numColum.add(devuelveColumna(4));
						}
						
						else {
							numColum.add(4+1);
						}
						
					}
							

					if (!this.validaDigito(string[5], string[6])) {
						error.add("9");
						if (extension.equalsIgnoreCase("xls")
								|| extension.equalsIgnoreCase("xlsx")){
							numColum.add(devuelveColumna(6));
						}
						
						else {
							numColum.add(6+1);
						}
						
					}

					if (!this.isNumeric(string[1].trim())){
						error.add("6");
					    if (extension.equalsIgnoreCase("xls")
							  || extension.equalsIgnoreCase("xlsx")){
						      numColum.add(devuelveColumna(1));
					    }
					    
					    else {
							numColum.add(1+1);
						}
					}
					
					
					    
					if (!this.isNumeric(string[2].trim())){
						error.add("6");
					       if (extension.equalsIgnoreCase("xls")
							  || extension.equalsIgnoreCase("xlsx")){
						       numColum.add(devuelveColumna(2));
					       }
					
					       else {
						       numColum.add(2+1);
					        }
					  
					}
					   

					/*
					 * if(!this.isNumeric(v[20].trim())) error.add("6");
					 */
					
					    //original y separado en 3 partes para mandar el numero de la columna
//					if(!validaCaracteresEspeciales(string[7].toString().replace(".", ""))
//							|| !validaCaracteresEspeciales(string[8].toString().replace(".", ""))
//							|| !validaCaracteresEspeciales(string[9].toString().replace(".", "")))
//						error.add("6");
					
					if(!validaCaracteresEspeciales(string[7].toString().replace(".", ""))){
							error.add("6");
							if (extension.equalsIgnoreCase("xls")
									|| extension.equalsIgnoreCase("xlsx")){
								numColum.add(devuelveColumna(7));
							}
							else {
								numColum.add(7+1);
							}
							
					}
					
					if(!validaCaracteresEspeciales(string[8].toString().replace(".", ""))){
						error.add("6");
						if (extension.equalsIgnoreCase("xls")
								|| extension.equalsIgnoreCase("xlsx")){
							numColum.add(devuelveColumna(8));
						}
						
						else {
							numColum.add(8+1);
						}
						
				    }
					
					if(!validaCaracteresEspeciales(string[9].toString().replace(".", ""))){
						error.add("6");
						if (extension.equalsIgnoreCase("xls")
								|| extension.equalsIgnoreCase("xlsx")){
							numColum.add(devuelveColumna(9));
						}
						
						else {
							numColum.add(9+1);
						}
						
				    }
					
					if (!this.monto(string[15])) {
						error.add("11");
						if (extension.equalsIgnoreCase("xls")
								|| extension.equalsIgnoreCase("xlsx")){
							numColum.add(devuelveColumna(15));
						}
						
						else {
							numColum.add(15+1);
						}
						
					}
					if (!this.monto(string[17])) {
						error.add("10");
						if (extension.equalsIgnoreCase("xls")
								|| extension.equalsIgnoreCase("xlsx")){
							numColum.add(devuelveColumna(17));
						}
						
						else {
							numColum.add(17+1);
						}
						

					}
					if (!this.meses(string[16])) {
						error.add("12");
						if (extension.equalsIgnoreCase("xls")
								|| extension.equalsIgnoreCase("xlsx")){
							numColum.add(devuelveColumna(16));
						}
						
						else {
							numColum.add(16+1);
						}
						
					}
					if (string[10].trim().length() > 7) { // 16-18
						error.add("14");
						if (extension.equalsIgnoreCase("xls")
								|| extension.equalsIgnoreCase("xlsx")){
							numColum.add(devuelveColumna(10));
						}
						
						else {
							numColum.add(10+1);
						}
						
					}

					if (string[15].trim().length() > 7) { // 16-18
						error.add("14");
						if (extension.equalsIgnoreCase("xls")
								|| extension.equalsIgnoreCase("xlsx")){
							numColum.add(devuelveColumna(15));
						}
						
						else {
							numColum.add(15+1);
						}
						
					}
					if (string[17].trim().equals("") && string[17].trim().length() > 7) { // 16-18
						error.add("14");
						if (extension.equalsIgnoreCase("xls")
								|| extension.equalsIgnoreCase("xlsx")){
							numColum.add(devuelveColumna(17));
						}
						
						else {
							numColum.add(17+1);
						}
						
					}

					if (!string[0].trim().equalsIgnoreCase(periodo)) {
						error.add("7");
						if (extension.equalsIgnoreCase("xls")
								|| extension.equalsIgnoreCase("xlsx")){
							numColum.add(devuelveColumna(0));
						}
						
						else {
							numColum.add(0+1);
						}
						
				  }

				} catch (NumberFormatException ignored) {
					error.add("6");
				}

			}
		} catch (Exception ignored) {
			error.add("17");
		}

		for (int j = 0; j < error.size(); j++) {

			errores = errores + error.get(j) + "/";

		}

		if (errores.length() > 0) {
			errores = errores.substring(0, errores.length() - 1);
		}

		if (errores.length() > 0) {
			if (string[6].equals("20")) {
				string[6] = "0";
			}

			errores = errores + ";" + string[5] + "/" + string[6] + "/" + fila;

		}
		

		retValidador.add(errores);
		retValidador.add(numColum);
		
		return retValidador;

	}


	public List procesaArchivos(String extension, FormFile archivo,
			String carpetaRes, String ext, String tipo,
			HttpServletRequest request) throws Exception {

		List lista = null;
		lista = new ArrayList();
		List listaerror = new ArrayList();

		enc = (Encargado) request.getSession().getAttribute("edocs_encargado");
		String rutenc = String.valueOf(enc.getRut());
		// Properties Config = new Properties();
		// Config.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		String periodo = Config.getProperty("PERIODO");

		String usser = String.valueOf(enc.getFullRut());
		
		int rutEmpresa=0;
		int periodoInt = 0;
		Mejoras2016DaoImpl probashDao = new Mejoras2016DaoImpl();
		String fechaStamp = null;
		String horaStamp = null;
		
		
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdh= new SimpleDateFormat("HH:mm:ss");
		fechaStamp = sdf.format(new Date());
		horaStamp = sdh.format(new Date());
		
		try {

			if (extension.equalsIgnoreCase("xls")
					|| extension.equalsIgnoreCase("xlsx")) {

				lista = this.createExcel(archivo, extension);

				if (!lista.get(lista.size() - 1).equals("error")) {

					flujo.setRutencargado(rutenc);
					flujo.setEtapa("3");
					flujo.setISAJKM92("CTADMIN");
					flujo.setRutempresa(archivo.getFileName().substring(0,
							archivo.getFileName().toString().indexOf(".")));
					flujo.setPeriodo(periodo);
					flujo.setCantregistros(lista.size());
					flujo.setOperacion("ENVIA ARCHIVO");
					flujo.setISAJKM92("");
					flujo.setISAJKM94("CTADMIN");
					flujo.setEtapa("3");
					flujo.setNombrearchivo(archivo.getFileName());

					dao.InsertaBitacora(flujo);

					for(int i=0;i<lista.size();i++) {

						AfiliadosTO oafil=(AfiliadosTO)lista.get(i);
						
						rutEmpresa = oafil.getRutempresa();
						periodoInt = oafil.getPeriodo();
						
						if(tipo.equals("empresa")) { oafil.setOrigen("C"); }else {
						oafil.setOrigen("E"); }
	
						int existe=Integer.parseInt(dao.existeAfiliado(String.valueOf(oafil.getPeriodo()),String.valueOf(oafil.getRutempresa()),String.valueOf(oafil.getRuttrabajador()))[0]);
//						if(existe==0){ dao.insertaArchivo(oafil); } else {
//						dao.updateaAfiliados(oafil); }

					 }
					
					this.creaArchivo(carpetaRes, archivo, ext);
					this.creaArchivoas400(carpetaRes, archivo, ext);
					
					ProcesoBashTO procesoBashTO = new ProcesoBashTO();
					procesoBashTO.setRutaArchivo(rutaBash);
					procesoBashTO.setEstado("P");
					procesoBashTO.setEmpresa(rutEmpresa); 
					procesoBashTO.setUsuarioSube(usser); 
					procesoBashTO.setFechaSubida(fechaStamp);
					procesoBashTO.setHoraSubida(horaStamp);
					procesoBashTO.setRegistrosInformados(lista.size());
					procesoBashTO.setCantidadIntento(0);
					procesoBashTO.setOrigen("C");
					procesoBashTO.setPeriodo(periodoInt);

					probashDao.insertBash(procesoBashTO);

				}

				else {

					listaerror = lista;

				}

			}

			else if (extension.equalsIgnoreCase("txt")) {

				File createfile = this.creaArchivo(carpetaRes, archivo, ext);
				if (extension.equalsIgnoreCase("txt"))
					lista = this.creaTexto(createfile, extension);

				if (!lista.get(lista.size() - 1).equals("error")) {

					flujo.setRutencargado(rutenc);
					flujo.setEtapa("3");
					flujo.setISAJKM92("CTADMIN");
					flujo.setRutempresa(archivo.getFileName().substring(0,
							archivo.getFileName().toString().indexOf(".")));
					flujo.setPeriodo(periodo);
					flujo.setCantregistros(lista.size());
					flujo.setOperacion("ENVIA ARCHIVO");
					flujo.setISAJKM92("");
					flujo.setISAJKM94("CTADMIN");
					flujo.setEtapa("3");
					flujo.setNombrearchivo((archivo.getFileName()));

					dao.InsertaBitacora(flujo);

					for(int i=0;i<lista.size();i++) {

						AfiliadosTO oafil=(AfiliadosTO)lista.get(i);
						
						rutEmpresa = oafil.getRutempresa();
						periodoInt = oafil.getPeriodo();
						
						if(tipo.equals("empresa")) { oafil.setOrigen("C"); }else {
						oafil.setOrigen("E"); }

						int existe=Integer.parseInt(dao.existeAfiliado(String.valueOf(oafil.getPeriodo()),String.valueOf(oafil.getRutempresa()),String.valueOf(oafil.getRuttrabajador()))[0]);
//						if(existe==0){ dao.insertaArchivo(oafil); } else {
//						dao.updateaAfiliados(oafil); } 
						}
					
					this.creaArchivoas400(carpetaRes, archivo, ext);
					
					ProcesoBashTO procesoBashTO = new ProcesoBashTO();
					procesoBashTO.setRutaArchivo(rutaBash);
					procesoBashTO.setEstado("P");
					procesoBashTO.setEmpresa(rutEmpresa); 
					procesoBashTO.setUsuarioSube(usser); 
					procesoBashTO.setFechaSubida(fechaStamp);
					procesoBashTO.setHoraSubida(horaStamp);
					procesoBashTO.setRegistrosInformados(lista.size());
					procesoBashTO.setCantidadIntento(0);
					procesoBashTO.setOrigen("C");
					procesoBashTO.setPeriodo(periodoInt);

					probashDao.insertBash(procesoBashTO);
				} else {
					listaerror = lista;
				}

			} else if (extension.equalsIgnoreCase("csv")) {

				File createfile = this.creaArchivo(carpetaRes, archivo, ext);
				lista = this.creaCsv(createfile, extension);
				if (!lista.get(lista.size() - 1).equals("error")) {

					flujo.setRutencargado(rutenc);
					flujo.setEtapa("3");
					flujo.setISAJKM92("CTADMIN");
					flujo.setRutempresa(archivo.getFileName().substring(0,
							archivo.getFileName().toString().indexOf(".")));
					flujo.setPeriodo(periodo);
					flujo.setCantregistros(lista.size());
					flujo.setOperacion("ENVIA ARCHIVO");
					flujo.setISAJKM92("");
					flujo.setISAJKM94("CTADMIN");
					flujo.setEtapa("3");
					flujo.setNombrearchivo((archivo.getFileName()));

					dao.InsertaBitacora(flujo);

					System.out.println("lista csv " + lista.size()); int
					c=0;int k=0;

					for(int i=0;i<lista.size();i++) {

					AfiliadosTO oafil=(AfiliadosTO)lista.get(i);
					
					rutEmpresa = oafil.getRutempresa();
					periodoInt = oafil.getPeriodo();
					
					if(tipo.equals("empresa")) { oafil.setOrigen("C"); }else {
					oafil.setOrigen("E"); } 
					int existe=Integer.parseInt(dao.existeAfiliado(String.valueOf(oafil.getPeriodo()),String.valueOf(oafil.getRutempresa()),String.valueOf(oafil.getRuttrabajador()))[0]);
					if(existe==0){


//					if(!dao.insertaArchivo(oafil)) { 
//						System.out.println("no inserta " + i); }

					c++;

					 } else { k++; 
//					 dao.updateaAfiliados(oafil); 
					 }
					 } System.out.println("c " + c + " k " + k);
					 
					this.creaArchivoas400(carpetaRes, archivo, ext);
						
					ProcesoBashTO procesoBashTO = new ProcesoBashTO();
					procesoBashTO.setRutaArchivo(rutaBash);
					procesoBashTO.setEstado("P");
					procesoBashTO.setEmpresa(rutEmpresa); 
					procesoBashTO.setUsuarioSube(usser); 
					procesoBashTO.setFechaSubida(fechaStamp);
					procesoBashTO.setHoraSubida(horaStamp);
					procesoBashTO.setRegistrosInformados(lista.size());
					procesoBashTO.setCantidadIntento(0);
					procesoBashTO.setOrigen("C");
					procesoBashTO.setPeriodo(periodoInt);

					probashDao.insertBash(procesoBashTO);
				} else {
					listaerror = lista;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return listaerror;
	}





	public List procesaArchivosHolding(String extension, FormFile archivo, String carpetaRes, String ext , HttpServletRequest request ) throws Exception{
		
		Encargado enc1 =new Encargado();
		enc1=(Encargado)request.getSession().getAttribute("edocs_encargado");
		String usser = String.valueOf(enc1.getFullRut());

		List lista=new ArrayList();
		List listaerror=new ArrayList(); 
		java.sql.Time time =  new java.sql.Time(new Date().getTime());
		int rutEmpresa=0;
		int periodo =0;
		Mejoras2016DaoImpl probashDao = new Mejoras2016DaoImpl();
		String fechaStamp = null;
        String horaStamp = null;
        String holding = archivo.toString();
        holding = holding.replace("."+extension, "");
        if(isNumeric(holding)) {
        	
        	rutEmpresa = Integer.parseInt(holding);
		}
        
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdh= new SimpleDateFormat("HH:mm:ss");
		fechaStamp = sdf.format(new Date());
		horaStamp = sdh.format(new Date());

		try{

			if(extension.equalsIgnoreCase("xls")||extension.equalsIgnoreCase("xlsx")){


				lista=this.createExcelHolding(archivo, extension);

				if(lista==null)
					return null;

				if(!lista.get(lista.size()-1).equals("error")) {

					System.out.println("size" + lista.size());

					for(int i=0;i<lista.size();i++)
					{


						HoldingafiliadosTO oafil=(HoldingafiliadosTO)lista.get(i);
						
						//rutEmpresa = oafil.getRutempresa();
						periodo = oafil.getPeriodo();
						
						oafil.setOrigen("C");

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
//							dao.insertaArchivoholding(oafil);
//						}
//						else if(!dao.existeAfiliado(String.valueOf(oafil.getPeriodo()),String.valueOf(oafil.getRutempresa()),String.valueOf(oafil.getRuttrabajador()))[1].equals("E"))
//						{
//							dao.updateaAfiliadosholding(oafil);
//						}

					}
					this.creaArchivoas400(carpetaRes, archivo, ext);
					
					ProcesoBashTO procesoBashTO = new ProcesoBashTO();
					procesoBashTO.setRutaArchivo(rutaBash);
					procesoBashTO.setEstado("P");
					procesoBashTO.setEmpresa(rutEmpresa);
					procesoBashTO.setUsuarioSube(usser); //es el que se loguea
					procesoBashTO.setFechaSubida(fechaStamp);
					procesoBashTO.setHoraSubida(horaStamp);
					procesoBashTO.setRegistrosInformados(lista.size());
					procesoBashTO.setCantidadIntento(0);
					procesoBashTO.setOrigen("H");
					procesoBashTO.setPeriodo(periodo);

					if (probashDao.insertBash(procesoBashTO)) {
						//proceso bash insertado correctamente
					}
					
					else{
						//proceso bash no pudo ser insertado
					}


				}

				else
				{
					listaerror=lista;

				}
				
				

			}
			
			else  if(extension.equalsIgnoreCase("txt"))
			{


				File createfile=this.creaArchivoas400(carpetaRes + "\\", archivo, ext);
				if(extension.equalsIgnoreCase("txt"))
					lista=this.creaTextoholding(createfile, extension);
				if(!lista.get(lista.size()-1).equals("error")) {
					for(int i=0;i<lista.size();i++)
					{

						HoldingafiliadosTO oafil=(HoldingafiliadosTO)lista.get(i);
						
						//rutEmpresa = oafil.getRutempresa();
						periodo = oafil.getPeriodo();

						oafil.setOrigen("C");


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
//							dao.insertaArchivoholding(oafil);
//						}
//						else  
//						{
//							dao.updateaAfiliadosholding(oafil);
//						}
					}
					
					ProcesoBashTO procesoBashTO = new ProcesoBashTO();
					procesoBashTO.setRutaArchivo(rutaBash);
					procesoBashTO.setEstado("P");
					procesoBashTO.setEmpresa(rutEmpresa);
					procesoBashTO.setUsuarioSube(usser); //es el que se loguea
					procesoBashTO.setFechaSubida(fechaStamp);
					procesoBashTO.setHoraSubida(horaStamp);
					procesoBashTO.setRegistrosInformados(lista.size());
					procesoBashTO.setCantidadIntento(0);
					procesoBashTO.setOrigen("H");
					procesoBashTO.setPeriodo(periodo);

					if (probashDao.insertBash(procesoBashTO)) {
						//proceso bash insertado correctamente
					}
					
					else{
						//proceso bash no pudo ser insertado
					}

				}
				else{
					listaerror=lista;
				}
				
				
			}
			
			else if(extension.equalsIgnoreCase("csv"))
			{

				File createfile=this.creaArchivoas400(carpetaRes + "\\", archivo, ext);
				lista=this.creaCsvholding(createfile, extension);
				if(!lista.get(lista.size()-1).equals("error")) {
					System.out.println("lista csv " + lista.size()); 
					int c=0;int k=0;

					for(int i=0;i<lista.size();i++)
					{


						HoldingafiliadosTO oafil=(HoldingafiliadosTO)lista.get(i);
						
						//rutEmpresa = oafil.getRutempresa();
						periodo = oafil.getPeriodo();

						oafil.setOrigen("C");

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
//
//
//							if(!dao.insertaArchivoholding(oafil))
//							{
//								System.out.println("no inserta " + i);
//							}
//
//							c++;
//						}
//						else  {
//							k++;
//							dao.updateaAfiliadosholding(oafil);
//						}

					}
					System.out.println("c " + c + " k " + k);
					
					ProcesoBashTO procesoBashTO = new ProcesoBashTO();
					procesoBashTO.setRutaArchivo(rutaBash);
					procesoBashTO.setEstado("P");
					procesoBashTO.setEmpresa(rutEmpresa);
					procesoBashTO.setUsuarioSube(usser); //es el que se loguea
					procesoBashTO.setFechaSubida(fechaStamp);
					procesoBashTO.setHoraSubida(horaStamp);
					procesoBashTO.setRegistrosInformados(lista.size());
					procesoBashTO.setCantidadIntento(0);
					procesoBashTO.setOrigen("H");
					procesoBashTO.setPeriodo(periodo);

					if (probashDao.insertBash(procesoBashTO)) {
						//proceso bash insertado correctamente
					}
					
					else{
						//proceso bash no pudo ser insertado
					}
					
				}else
				{
					listaerror=lista;
				}
				
				
			}


		}catch(Exception ex)
		{
			ex.printStackTrace();
		}



		return listaerror;
	}


	public List procesaArchivosZip(String extension, File archivo, String tipo,
			HttpServletRequest request) throws Exception {

		List lista = null;
		lista = new ArrayList();
		List listaerror = new ArrayList();
		enc = (Encargado) request.getSession().getAttribute("edocs_encargado");
		String rutenc = String.valueOf(enc.getRut());
		// Properties Config = new Properties();
		// Config.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		String periodo = Config.getProperty("PERIODO");

		if (extension.equalsIgnoreCase("xls")
				|| extension.equalsIgnoreCase("xlsx")) {

			lista = this.createExcelZip(archivo, extension);
			System.out.println(lista.size());

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
				 * for(int i=0;i<lista.size();i++) { AfiliadosTO
				 * oafil=(AfiliadosTO)lista.get(i); oafil.setOrigen("E"); int
				 * existe=Integer.parseInt(dao.existeAfiliado(String.valueOf(oafil.getPeriodo()),String.valueOf(oafil.getRutempresa()),String.valueOf(oafil.getRuttrabajador()))[0]);
				 * if(existe==0){ dao.insertaArchivo(oafil); } else {
				 * dao.updateaAfiliados(oafil); } }
				 *  }
				 * 
				 * else { listaerror=lista;
				 *  }
				 *  }
				 * 
				 * 
				 * else if(extension.equalsIgnoreCase("txt")) {
				 * 
				 * 
				 * if(extension.equalsIgnoreCase("txt"))
				 * lista=this.creaTexto(archivo);
				 * if(!lista.get(lista.size()-1).equals("error")) { for(int
				 * i=0;i<lista.size();i++) {
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
		} else if (extension.equalsIgnoreCase("csv")) {

			lista = this.creaCsv(archivo, extension);
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

	

	//----------------valida archivos--------------------------

	public List procesaFicherosgrava(String path, HttpServletRequest request) throws Exception {

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


	public List procesaArchivosgrava(String extension, FormFile archivo,
			String carpetaRes, String ext, String tipo, HttpServletRequest request) throws Exception {
		
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

			if (extension.equalsIgnoreCase("xls")
					|| extension.equalsIgnoreCase("xlsx")) {

				lista = this.createExcel(archivo, extension);

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
//
//							dao.updateaAfiliados(oafil);
//						}

					}
					this.creaArchivoas400(carpetaRes, archivo, ext);
					
					ProcesoBashTO procesoBashTO = new ProcesoBashTO();
					procesoBashTO.setRutaArchivo(rutaBash);
					procesoBashTO.setEstado("P");
					procesoBashTO.setEmpresa(rutEmpresa);
					procesoBashTO.setUsuarioSube(usser); //es el que se loguea
					procesoBashTO.setFechaSubida(fechaStamp);
					procesoBashTO.setHoraSubida(horaStamp);
					procesoBashTO.setRegistrosInformados(lista.size());
					procesoBashTO.setCantidadIntento(0);
					procesoBashTO.setOrigen("C");
					procesoBashTO.setPeriodo(periodo);

					if (probashDao.insertBash(procesoBashTO)) {
						//proceso bash insertado correctamente
					}
					
					else{
						//proceso bash no pudo ser insertado
					}

				}

				else {
					listaerror = lista;

				}
				

			}

			else if (extension.equalsIgnoreCase("txt")) {

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
					procesoBashTO.setOrigen("C");
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
//						if (Integer.parseInt(valor[0]) == 0) {
//							if (!dao.insertaArchivo(oafil)) {
//								System.out.println("no inserta " + i);
//							}
//
//							c++;
//						} else {
//							k++;
//							dao.updateaAfiliados(oafil);
//						}

					}
					System.out.println("c " + c + " k " + k);
					
					ProcesoBashTO procesoBashTO = new ProcesoBashTO();
					procesoBashTO.setRutaArchivo(rutaBash);
					procesoBashTO.setEstado("P");
					procesoBashTO.setEmpresa(rutEmpresa);
					procesoBashTO.setUsuarioSube(usser); //es el que se loguea
					procesoBashTO.setFechaSubida(fechaStamp);
					procesoBashTO.setHoraSubida(horaStamp);
					procesoBashTO.setRegistrosInformados(lista.size());
					procesoBashTO.setCantidadIntento(0);
					procesoBashTO.setOrigen("C");
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




	public List procesaArchivosZipgrava(String extension, File archivo,
			String tipo, HttpServletRequest request) throws Exception {
		
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
		
		if (extension.equalsIgnoreCase("xls")
				|| extension.equalsIgnoreCase("xlsx")) {

			lista = this.createExcelZip(archivo, extension);
			System.out.println(lista.size());
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
				procesoBashTO.setUsuarioSube(usser); //es el que se loguea
				procesoBashTO.setFechaSubida(fechaStamp);
				procesoBashTO.setHoraSubida(horaStamp);
				procesoBashTO.setRegistrosInformados(lista.size());
				procesoBashTO.setCantidadIntento(0);
				procesoBashTO.setOrigen("C");
				procesoBashTO.setPeriodo(periodo);

				if (probashDao.insertBash(procesoBashTO)) {
					//proceso bash insertado correctamente
				}
				
				else{
					//proceso bash no pudo ser insertado
				}

			}

			else {
				listaerror = lista;

			}
			
			

		}

		else if (extension.equalsIgnoreCase("txt")) {

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
				procesoBashTO.setUsuarioSube(usser); //es el que se loguea
				procesoBashTO.setFechaSubida(fechaStamp);
				procesoBashTO.setHoraSubida(horaStamp);
				procesoBashTO.setRegistrosInformados(lista.size());
				procesoBashTO.setCantidadIntento(0);
				procesoBashTO.setOrigen("C");
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
				procesoBashTO.setUsuarioSube(usser); //es el que se loguea
				procesoBashTO.setFechaSubida(fechaStamp);
				procesoBashTO.setHoraSubida(horaStamp);
				procesoBashTO.setRegistrosInformados(lista.size());
				procesoBashTO.setCantidadIntento(0);
				procesoBashTO.setOrigen("C");
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



	//---------------------------------------------------------

	public boolean noformato(String str){

		int valor=0;
		try{

			valor=Integer.parseInt(str);

		}catch(NumberFormatException ignored)
		{
			return false;
		}

		return true;
	}

	public boolean validaDigito(String rut,String digito ){

		try{
			char dv=this.getDigito(rut);

			if(!String.valueOf(dv).equalsIgnoreCase(String.valueOf(digito)))
			{
				return false;
			}

		}catch(Exception ex)
		{ex.printStackTrace();

		return false;
		}

		return true; 
	}

	public boolean monto(String monto){
		try{
			int montoi=Integer.parseInt(String.valueOf(monto));
			if(montoi<=0){
				return false;
			}
			else
				return true;
		}catch(Exception e){
			return false;
		}
	}

	public boolean meses(String mes){
		try{
			int meses=Integer.parseInt(mes);
	
			if(meses>12||meses<1)
			{
				return false;
			}
			else
			{
				return true;
			}
		}catch(Exception e){
			return false;
		}
	}

	public boolean isNumeric(String str){

		int c=0;
		for(int i=0;i<str.length();i++){
			if(!Character.isDigit(str.charAt(i))){
				c++;	 
			}
		}
		if(c!=0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}


	/*---------------------Holding----------------------------------
	 * 
	 */ 

	public List createExcelHolding(FormFile Archivo, String extension)
	{
		List result=new ArrayList();
		ArrayList numColum = new ArrayList();
		ArrayList recValidador = new ArrayList();

		try
		{
			System.out.println("begin create report");
			InputStream is=this.excel(extension, Archivo);

			HoldingafiliadosTO oafil=new HoldingafiliadosTO();

			List lista=new ArrayList();
			String celda[]=new String[21];
			String vector[]=new String[18]; 

			WorkBook workbook=new WorkBook();
			if(extension.equalsIgnoreCase("xls"))
				workbook.read(is);
			else if(extension.equalsIgnoreCase("xlsx"))
				workbook.readXLSX(is);

			//new code***
			/*
			byte[] fileData = Archivo.getFileData();
			InputStream is = new ByteArrayInputStream(fileData);

			HSSFWorkbook wb = new HSSFWorkbook(is); //referencia al archivo
			HSSFSheet sheet = wb.getSheetAt(0); //referencia a la primera hoja del archivo

			int ultimaFila = sheet.getLastRowNum();
			 */
			//***********	 


			//new code***
			/*
		 	for(int i = 0; i <= ultimaFila - 1; i++)
		 	{
		 		HSSFRow row = sheet.getRow(1 + i); //obtenemos la fila

		 		for(int j=0;j<21;j++)
		 		{
		 			if(row.getCell(j).getCellType() == 1){
		 				celda[j] = row.getCell(j).getRichStringCellValue().getString().trim();
		 			}else if(row.getCell(j).getCellType() == 0){
		 				celda[j] = String.valueOf(Math.round(row.getCell(j).getNumericCellValue()));
		 			}
		 		}

				int meses=0;
				int c=0; 

				for(int k=12;k<18;k++)
				{
					if(Integer.parseInt(celda[k])>0){
						c++;	
					}
				}

				meses=c;

			    vector[0]=String.valueOf(Math.round(row.getCell(0).getNumericCellValue())); //periodo
			    vector[1]=row.getCell(1).getRichStringCellValue().getString().trim(); //holding
			    vector[2]=row.getCell(2).getRichStringCellValue().getString().trim(); //dvholding
			    vector[3]=row.getCell(3).getRichStringCellValue().getString().trim(); //oficina
			    vector[4]=row.getCell(4).getRichStringCellValue().getString().trim(); //sucursal
			    vector[5]=row.getCell(5).getRichStringCellValue().getString().trim(); //rutemp
			    vector[6]=row.getCell(6).getRichStringCellValue().getString().trim(); //dvrutemp
			    vector[7]=row.getCell(7).getRichStringCellValue().getString().trim(); //rutaf
			    vector[8]=row.getCell(8).getRichStringCellValue().getString().trim(); //dvrutaf
			    vector[9]=row.getCell(12).getRichStringCellValue().getString().trim(); //mes valta1
			    vector[10]=row.getCell(13).getRichStringCellValue().getString().trim(); //mes valta2
			    vector[11]=row.getCell(14).getRichStringCellValue().getString().trim(); //mes valta3
			    vector[12]=row.getCell(15).getRichStringCellValue().getString().trim(); //mes valta4
			    vector[13]=row.getCell(16).getRichStringCellValue().getString().trim(); //mes valta5
			    vector[14]=row.getCell(17).getRichStringCellValue().getString().trim(); //mes valta6
			    vector[15]=row.getCell(18).getRichStringCellValue().getString().trim(); //val promedio
			    vector[16]=row.getCell(19) != null ? String.valueOf(Math.round(row.getCell(19).getNumericCellValue())):"0"; //cod tramo
			    vector[17]=row.getCell(20) != null ? String.valueOf(Math.round(row.getCell(20).getNumericCellValue())):"0"; //val tramo

				fail=this.validaholding(vector, i+1);

				if(fail.length()>0)
					result.add(fail);

				if(result.size()==0){

				 	oafil=new HoldingafiliadosTO();

					oafil.setPeriodo(Integer.parseInt(vector[0]));
					oafil.setOficina(Integer.parseInt(vector[3]));
					oafil.setSucursal(Integer.parseInt(vector[4]));
					oafil.setRutempresa(Integer.parseInt(vector[5]));
					oafil.setDvempresa(vector[6]);
					oafil.setRuttrabajador(Integer.parseInt(vector[7]));
					oafil.setDvtrabajador(vector[8]);
					oafil.setNombre(celda[11].trim().toUpperCase() + " " + celda[9].trim().toUpperCase() + " " + celda[10].trim().toUpperCase());
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
					System.out.println("bien");
				}
		 	}*/

			for(int i=1;i<workbook.getLastRow()+1;i++){

				for(int j=0;j<21;j++){
					celda[j]=workbook.getText(i, j);

				}

				int meses=0;
				int c=0; 
				for(int k=12;k<18;k++){


					if(Integer.parseInt(celda[k])>0){
						c++;	
					}
				}

				meses=c;

				vector[0]=workbook.getText(i, 0); //periodo
				vector[1]=workbook.getText(i, 1); //holding
				vector[2]=workbook.getText(i, 2); //dvholding
				vector[3]=workbook.getText(i, 3); //oficina
				vector[4]=workbook.getText(i, 4); //sucursal
				vector[5]=workbook.getText(i, 5); //rutemp
				vector[6]=workbook.getText(i, 6); //dvrutemp
				vector[7]=workbook.getText(i, 7); //rutaf
				vector[8]=workbook.getText(i, 8); //dvrutaf
				vector[9]=workbook.getText(i, 12); //mes valta1
				vector[10]=workbook.getText(i, 13); //mes valta2
				vector[11]=workbook.getText(i, 14); //mes valta3
				vector[12]=workbook.getText(i, 15); //mes valta4
				vector[13]=workbook.getText(i, 16); //mes valta5
				vector[14]=workbook.getText(i, 17); //mes valta6
				vector[15]=workbook.getText(i, 18); //val promedio
				vector[16]=(workbook.getText(i, 19) != null && workbook.getText(i, 19).trim().length()>0 ?  workbook.getText(i, 19):"0"); //cod tramo
				vector[17]=(workbook.getText(i, 20) != null && workbook.getText(i, 20).trim().length()>0 ?  workbook.getText(i, 20):"0"); //val tramo



				recValidador=this.validaholding(vector, i+1, extension);
				fail = (String) recValidador.get(0);
				numColum = (ArrayList) recValidador.get(1);
				
				if(fail.length()>0) {
				result.add(fail);
				result.add(numColum);
				}

				if(result.size()==0){


					oafil=new HoldingafiliadosTO();

					oafil.setPeriodo(Integer.parseInt(vector[0]));
					oafil.setOficina(Integer.parseInt(vector[3]));
					oafil.setSucursal(Integer.parseInt(vector[4]));
					oafil.setRutempresa(Integer.parseInt(vector[5]));
					oafil.setDvempresa(vector[6]);
					oafil.setRuttrabajador(Integer.parseInt(vector[7]));
					oafil.setDvtrabajador(vector[8]);
					oafil.setNombre(celda[11].trim().toUpperCase() + " " + celda[9].trim().toUpperCase() + " " + celda[10].trim().toUpperCase());
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





			workbook=null;
			celda=null;
			is.close();



			if (result.size() == 0) {
				return lista;
			} 
			else 
			{
				result.add("error");
				return result;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList validaholding(String v[], int fila,String extension)throws Exception{


		Vector vector=new Vector();
		String errores="";
		ArrayList retValidador = new ArrayList();
		ArrayList numColum = new ArrayList();

		String ruttrab="";
		String ruttrabdv="";
		int mes=0;
		int meses=0;

		ruttrab=v[7];
		ruttrabdv=v[8];

		Properties Carpetas = new Properties();
		Carpetas.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		String periodo=Carpetas.getProperty("PERIODO");


		for(int i=9;i<15;i++){
			try{

				mes=Integer.parseInt(v[i]);

				
			}catch(NumberFormatException ignored)
			{
				vector.add("6");
				
				if (extension.equalsIgnoreCase("xls")
						|| extension.equalsIgnoreCase("xlsx")){
					numColum.add(devuelveColumna(i));
				}
				
				else
				{
					numColum.add(i+1);
				}

			}}

		int m=0;
		for(int n=3;n<5;n++){
			try{

				int g=Integer.parseInt(v[n]);


			}
			catch(NumberFormatException ignored)
			{
				vector.add("6");
				
				if (extension.equalsIgnoreCase("xls")
						|| extension.equalsIgnoreCase("xlsx")){
					numColum.add(devuelveColumna(n));
				}
				
				else
				{
					numColum.add(n+1);
				}
				
			}
		}



		if(!v[0].trim().equalsIgnoreCase(periodo))
		{
			vector.add("7");
			
			if (extension.equalsIgnoreCase("xls")
					|| extension.equalsIgnoreCase("xlsx")){
				numColum.add(devuelveColumna(0));
			}
			
			else 
			{
				numColum.add(0+1);
			}
			
		}
		if(!this.isNumeric(v[1])){
			vector.add("6");
			
			if (extension.equalsIgnoreCase("xls")
					|| extension.equalsIgnoreCase("xlsx")){
				numColum.add(devuelveColumna(1));
			}
			
			else
			{
				numColum.add(1+1);;
			}
			
		}
		if(!this.isNumeric(v[3])){
			vector.add("6");
			
			if (extension.equalsIgnoreCase("xls")
					|| extension.equalsIgnoreCase("xlsx")){
				numColum.add(devuelveColumna(3));
			}
			
			else 
			{
				numColum.add(3+1);
			}
			
		}
		if(!this.isNumeric(v[4])){
			vector.add("6");
			
			if (extension.equalsIgnoreCase("xls")
					|| extension.equalsIgnoreCase("xlsx")){
				numColum.add(devuelveColumna(4));
			}
			
			else 
			{
				numColum.add(4+1);
			}
			
			
		}
		if(!this.isNumeric(v[5])){
			vector.add("6");
			
			if (extension.equalsIgnoreCase("xls")
					|| extension.equalsIgnoreCase("xlsx")){
				numColum.add(devuelveColumna(5));
			}
			
			else
			{
				numColum.add(5+1);
			}
			
		}
		if(!this.isNumeric(v[7])){
			vector.add("6");
			
			if (extension.equalsIgnoreCase("xls")
					|| extension.equalsIgnoreCase("xlsx")){
				numColum.add(devuelveColumna(7));
			}
			
			else 
			{
				numColum.add(7+1);
			}
			
		}
		if(!this.isNumeric(v[15])){
			vector.add("6");
			
			if (extension.equalsIgnoreCase("xls")
					|| extension.equalsIgnoreCase("xlsx")){
				numColum.add(devuelveColumna(15));
			}
			
			else 
			{
				numColum.add(15+1);
			}
			
		}
		if(!this.isNumeric(v[16])){
			vector.add("6");
			
			if (extension.equalsIgnoreCase("xls")
					|| extension.equalsIgnoreCase("xlsx")){
				numColum.add(devuelveColumna(16));
			}
			
			else 
			{
				numColum.add(16+1);
			}
			
		}
		if(!this.isNumeric(v[17])){
			vector.add("6");
			
			if (extension.equalsIgnoreCase("xls")
					|| extension.equalsIgnoreCase("xlsx")){
				numColum.add(devuelveColumna(17));
			}
			
			else 
			{
				numColum.add(17+1);
			}
			
		}

		try{


			if(!this.validaDigito(v[5],v[6])){
				vector.add("8");
				
				if (extension.equalsIgnoreCase("xls")
						|| extension.equalsIgnoreCase("xlsx")){
					numColum.add(devuelveColumna(6));
				}
				
				else
				{
					numColum.add(6+1);
				}
				
			}
			if(!this.validaDigito(v[7],v[8])){
				vector.add("9");
				
				if (extension.equalsIgnoreCase("xls")
						|| extension.equalsIgnoreCase("xlsx")){
					numColum.add(devuelveColumna(8));
				}
				
				else 
				{
					numColum.add(8+1);
				}
				
			}


		}catch(NumberFormatException ignored)
		{
			vector.add("6");
		}


		for(int j=0;j<vector.size();j++){

			errores=errores+vector.get(j) + "/";

		}

		if(errores.length()>0){
			errores=errores.substring(0,errores.length()-1); 
		}

		if(errores.length()>0){
			try{
				int t=Integer.parseInt(ruttrab);


			}catch(NumberFormatException ignored)
			{
				ruttrab="0";

			}
			errores=errores + ";" + ruttrab + "/" + ruttrabdv + "/" + fila;
		}
		
		retValidador.add(errores);
		retValidador.add(numColum);


		return retValidador;
	}

	public boolean esholding(FormFile archivo, String extension)throws Exception{

		InputStream  is=null; 
		boolean resu=false;

		try{

			byte[] fileData= archivo.getFileData();

			is = new ByteArrayInputStream(fileData);

		}
		catch(IOException ex)
		{ex.printStackTrace();
		}

		WorkBook workbook= new WorkBook();
		try{


			if(extension.equalsIgnoreCase("xls"))
				workbook.read(is);
			else if(extension.equalsIgnoreCase("xlsx"))
				workbook.readXLSX(is);

			//new code***
			/*
		byte[] fileData = archivo.getFileData();
		is = new ByteArrayInputStream(fileData);

		HSSFWorkbook wb = new HSSFWorkbook(is); //referencia al archivo
		HSSFSheet sheet = wb.getSheetAt(0); //referencia a la primera hoja del archivo
			 */
			//***********

			//String celda=sheet.getRow(0).getCell(1).getRichStringCellValue().getString().trim();
			String celda = workbook.getText(0, 1);
			System.out.println(">>celda " + celda);
			if(celda.trim().equalsIgnoreCase("RUTHOLDING"))
			{
				resu=true;
			}
			else
			{
				resu=false;
			}

		}
		catch(Exception ex)
		{ex.printStackTrace();
		return false;
		}


		return resu;
	}


	public void zipFolder(String inf, String outf, String ruta){
		try
		{
			File inFolder=new File(inf);
			File outFolder=new File(outf);
			ZipOutputStream out = new ZipOutputStream(new 
					BufferedOutputStream(new FileOutputStream(outFolder)));
			BufferedInputStream in = null;
			byte[] data  = new byte[1000];
			String files[] = inFolder.list();
			for (int i=0; i<files.length; i++)
			{
				in = new BufferedInputStream(new FileInputStream
						(inFolder.getPath()+"/carpeta/" + files[i]), 1000);
				System.out.println(inFolder.getPath()+"/carpeta/" + files[i]);
				out.putNextEntry(new ZipEntry(files[i])); 
				int count;
				while((count = in.read(data,0,1000)) != -1)
				{
					out.write(data, 0, count);
				}
				out.closeEntry();
			}
			out.flush();
			out.close();

		}

		catch(Exception e)
		{
			e.printStackTrace();
		} 
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
	
	public int compararFechasInicFin(String fechaInicio, String fechaFinal) {  
		  System.out.println("Parametro String Fecha 1 = "+fechaInicio+"\n" +
		    "Parametro String fechaFinal = "+fechaFinal+"\n");  
		  int resultado=0;
		  try {
		   /**Obtenemos las fechas enviadas en el formato a comparar*/
		   SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy"); 
		   Date fechaDate1 = formateador.parse(fechaInicio);
		   Date fechaDate2 = formateador.parse(fechaFinal);
		    
		   System.out.println("Parametro Date Fecha 1 = "+fechaDate1+"\n" +
		     "Parametro Date fechaFinal = "+fechaDate2+"\n");
		    
		    if ( fechaDate1.before(fechaDate2) ){
		      resultado= 1;
		    }else{
		     if ( fechaDate2.before(fechaDate1) ){
		      resultado= 0;
		     }else{
		      resultado= 0;
		     } 
		    }
		  } catch (ParseException e) {
		   System.out.println("Se Produjo un Error!!!  "+e.getMessage());
		  }  
		  return resultado;
	  }
	
	
	public boolean validaCaracteresEspeciales (String cadena){
	    return StringUtils.isAlphaSpace(cadena);
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
	
}
	

	
