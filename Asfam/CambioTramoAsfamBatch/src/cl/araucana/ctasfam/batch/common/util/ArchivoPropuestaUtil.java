package cl.araucana.ctasfam.batch.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cl.araucana.ctasfam.batch.common.dto.ArchivoPropuestaDto;
import cl.araucana.ctasfam.batch.common.dto.PropuestaAfiliadoDto;
import cl.araucana.ctasfam.batch.common.exceptions.TechnicalException;

public class ArchivoPropuestaUtil {
	public static String getTipoArchivo(String path) {
		if (path != null && path.indexOf(".") >= 0) {
			return path.substring(path.lastIndexOf(".") + 1, path.length());
		} else {
			return null;
		}
	}

	public static List<PropuestaAfiliadoDto> leePropuestaExcel(byte[] contenido) throws TechnicalException {
		List<PropuestaAfiliadoDto> listResult = new ArrayList<PropuestaAfiliadoDto>();
		try {
			InputStream is = new ByteArrayInputStream(contenido);
			HSSFWorkbook libro = new HSSFWorkbook(is);
			HSSFSheet hoja = libro.getSheetAt(0);

			for (int i = 0; i < hoja.getLastRowNum(); i++) {
				HSSFRow row = hoja.getRow(1 + i);

				PropuestaAfiliadoDto afilProp = new PropuestaAfiliadoDto();

				afilProp.setPeriodo(Integer.parseInt(getValorCelda(row
						.getCell(0))));
				afilProp.setOficina(Integer.parseInt(getValorCelda(row
						.getCell(1))));
				afilProp.setSucursal(Integer.parseInt(getValorCelda(row
						.getCell(2))));
				afilProp.setRutEmpresa(Integer.parseInt(getValorCelda(row
						.getCell(3))));
				afilProp.setDvEmpresa(getValorCelda(row.getCell(4)));
				afilProp.setRutTrabajador(Integer.parseInt(getValorCelda(row
						.getCell(5))));
				afilProp.setDvTrabajador(getValorCelda(row.getCell(6)));
				afilProp.setApellidoPaterno(getValorCelda(row.getCell(7))
						.toUpperCase());
				afilProp.setApellidoMaterno(getValorCelda(row.getCell(8))
						.toUpperCase());
				afilProp.setNombreAfiliado(getValorCelda(row.getCell(9))
						.toUpperCase());
				afilProp.setRemuneracionesMismoEmpleador(Integer
						.parseInt(getValorCelda(row.getCell(10))));
				afilProp.setOtrasRemuneraciones(Integer
						.parseInt(getValorCelda(row.getCell(11))));
				afilProp.setRentaTrabajadorIndependiente(Integer
						.parseInt(getValorCelda(row.getCell(12))));
				afilProp.setSubsidio(Integer.parseInt(getValorCelda(row
						.getCell(13))));
				afilProp.setPensiones(Integer.parseInt(getValorCelda(row
						.getCell(14))));
				afilProp.setTotalIngresos(Integer.parseInt(getValorCelda(row
						.getCell(15))));
				afilProp.setNumeroMeses(Integer.parseInt(getValorCelda(row
						.getCell(16))));
				afilProp.setIngresoPromedio(Integer.parseInt(getValorCelda(row
						.getCell(17))));
				afilProp.setTrabajadorConSinDeclaracion(Integer
						.parseInt(getValorCelda(row.getCell(18))));
				
				String codigoTramo = getValorCelda(row.getCell(19)).trim();
				afilProp.setCodigoTramo("".equals(codigoTramo)?0:Integer.parseInt(codigoTramo));
				
				String valorTramo = getValorCelda(row.getCell(20)).trim();
				afilProp.setValorTramo("".equals(valorTramo)?0:Integer.parseInt(valorTramo));

				listResult.add(afilProp);
			}
			return listResult;
		} catch (IOException e) {
			throw new TechnicalException("0801","Ocurrio un error al leer el archivo de propuesta Excel", e);
		} catch (Exception e) {
			throw new TechnicalException("0802","Ocurrio un error al leer el archivo de propuesta Excel", e);
		}

	}

	public static String getValorCelda(HSSFCell cell) {
		if (cell != null) {
			if (cell.getCellType() == 1) {
				return cell.getRichStringCellValue().getString().trim();
			} else if (cell.getCellType() == 0) {
				return String.valueOf(Math.round(cell.getNumericCellValue()));
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	public static List<PropuestaAfiliadoDto> leePropuestaTxt(byte[] contenido) throws TechnicalException {
		List<PropuestaAfiliadoDto> listResult = new ArrayList<PropuestaAfiliadoDto>();
		try {
			InputStream is = new ByteArrayInputStream(contenido);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			String linea = null;
			while ((linea = reader.readLine()) != null) {
				if (linea != null && linea.length() == 147) {
					PropuestaAfiliadoDto afilProp = new PropuestaAfiliadoDto();

					afilProp.setPeriodo(Integer.parseInt(linea.substring(0, 6)
							.trim()));
					afilProp.setOficina(Integer.parseInt(linea.substring(6, 9)
							.trim()));

					afilProp.setSucursal(Integer.parseInt(linea
							.substring(9, 12).trim()));
					afilProp.setRutEmpresa(Integer.parseInt(linea.substring(12,
							21).trim()));
					afilProp.setDvEmpresa(linea.substring(21, 22));
					afilProp.setRutTrabajador(Integer.parseInt(linea.substring(
							22, 31).trim()));
					afilProp.setDvTrabajador(linea.substring(31, 32));
					afilProp.setApellidoPaterno(linea.substring(32, 47)
							.toUpperCase());
					afilProp.setApellidoMaterno(linea.substring(47, 62)
							.toUpperCase());
					afilProp.setNombreAfiliado(linea.substring(62, 82)
							.toUpperCase());
					afilProp.setRemuneracionesMismoEmpleador(Integer
							.parseInt(linea.substring(82, 89).trim()));
					afilProp.setOtrasRemuneraciones(Integer.parseInt(linea
							.substring(89, 96).trim()));
					afilProp.setRentaTrabajadorIndependiente(Integer
							.parseInt(linea.substring(96, 103).trim()));
					afilProp.setSubsidio(Integer.parseInt(linea.substring(103,
							110).trim()));
					afilProp.setPensiones(Integer.parseInt(linea.substring(110,
							117).trim()));
					afilProp.setTotalIngresos(Integer.parseInt(linea.substring(
							117, 124).trim()));
					afilProp.setNumeroMeses(Integer.parseInt(linea.substring(
							124, 126).trim()));
					afilProp.setIngresoPromedio(Integer.parseInt(linea
							.substring(126, 133).trim()));
					afilProp.setTrabajadorConSinDeclaracion(Integer
							.parseInt(linea.substring(133, 134).trim()));
					
					String codigoTramo = linea.substring(134, 135).trim();
					afilProp.setCodigoTramo("".equals(codigoTramo)?0:Integer.parseInt(codigoTramo));
					
					String valorTramo = linea.substring(135, 147).trim();
					afilProp.setValorTramo("".equals(valorTramo)?0:Integer.parseInt(valorTramo));

					listResult.add(afilProp);

				}
			}
			return listResult;
		} catch (IOException e) {
			throw new TechnicalException("0803","Ocurrio un error al leer el archivo de propuesta TXT", e);
		} catch (Exception e) {
			throw new TechnicalException("0804","Ocurrio un error al leer el archivo de propuesta TXT", e);
		}
	}

	public static List<PropuestaAfiliadoDto> leePropuestaCsv(byte[] contenido) throws TechnicalException {
		List<PropuestaAfiliadoDto> listResult = new ArrayList<PropuestaAfiliadoDto>();
		try {
			InputStream is = new ByteArrayInputStream(contenido);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			String linea = null;
			while ((linea = reader.readLine()) != null) {
				if (linea != null
						&& !linea.replaceAll(";", "").trim().equals("")) {
					String[] datosLinea = linea.split(";");
					if (datosLinea.length == 21) {
						PropuestaAfiliadoDto afilProp = new PropuestaAfiliadoDto();

						afilProp.setPeriodo(Integer.parseInt(datosLinea[0]
								.trim()));
						afilProp.setOficina(Integer.parseInt(datosLinea[1]
								.trim()));
						afilProp.setSucursal(Integer.parseInt(datosLinea[2]
								.trim()));
						afilProp.setRutEmpresa(Integer.parseInt(datosLinea[3]
								.trim()));
						afilProp.setDvEmpresa(datosLinea[4]);
						afilProp.setRutTrabajador(Integer
								.parseInt(datosLinea[5].trim()));
						afilProp.setDvTrabajador(datosLinea[6]);
						afilProp.setApellidoPaterno(datosLinea[7].toUpperCase());
						afilProp.setApellidoMaterno(datosLinea[8].toUpperCase());
						afilProp.setNombreAfiliado(datosLinea[9].toUpperCase());
						afilProp.setRemuneracionesMismoEmpleador(Integer
								.parseInt(datosLinea[10].trim()));
						afilProp.setOtrasRemuneraciones(Integer
								.parseInt(datosLinea[11].trim()));
						afilProp.setRentaTrabajadorIndependiente(Integer
								.parseInt(datosLinea[12].trim()));
						afilProp.setSubsidio(Integer.parseInt(datosLinea[13]
								.trim()));
						afilProp.setPensiones(Integer.parseInt(datosLinea[14]
								.trim()));
						afilProp.setTotalIngresos(Integer
								.parseInt(datosLinea[15].trim()));
						afilProp.setNumeroMeses(Integer.parseInt(datosLinea[16]
								.trim()));
						afilProp.setIngresoPromedio(Integer
								.parseInt(datosLinea[17].trim()));
						afilProp.setTrabajadorConSinDeclaracion(Integer
								.parseInt(datosLinea[18].trim()));
						
						String codigoTramo = datosLinea[19].trim();
						afilProp.setCodigoTramo("".equals(codigoTramo)?0:Integer.parseInt(codigoTramo));
						
						String valorTramo = datosLinea[20].trim();
						afilProp.setValorTramo("".equals(valorTramo)?0:Integer.parseInt(valorTramo));

						listResult.add(afilProp);
					}
				}
			}
			return listResult;
		} catch (IOException e) {
			throw new TechnicalException("0805","Ocurrio un error al leer el archivo de propuesta CSV", e);
		} catch (Exception e) {
			throw new TechnicalException("0806","Ocurrio un error al leer el archivo de propuesta CSV", e);
		}
	}
	
	public static List<PropuestaAfiliadoDto> leePropuestaZip(byte[] contenidoZip) throws TechnicalException {
		List<PropuestaAfiliadoDto> listPropuestas = new ArrayList<PropuestaAfiliadoDto>();
		List<ArchivoPropuestaDto> listArchivos = unZip(contenidoZip);
		
		for(ArchivoPropuestaDto archProp : listArchivos){
			if("xls".equalsIgnoreCase(archProp.getTipo())){
				listPropuestas.addAll(ArchivoPropuestaUtil.leePropuestaExcel(archProp.getContenido()));
			}else if("txt".equalsIgnoreCase(archProp.getTipo())){
				listPropuestas.addAll(ArchivoPropuestaUtil.leePropuestaTxt(archProp.getContenido()));
			}else if("csv".equalsIgnoreCase(archProp.getTipo())){
				listPropuestas.addAll(ArchivoPropuestaUtil.leePropuestaCsv(archProp.getContenido()));
			}else {
				throw new TechnicalException("0807","Ocurrio un error al leer un archivo de tipo desconocido en el fichero ZIP");
			}
		}
		
		return listPropuestas;
	}

	public static List<ArchivoPropuestaDto> unZip(byte[] contenidoZip) throws TechnicalException{
		List<ArchivoPropuestaDto> listResult = new ArrayList<ArchivoPropuestaDto>();
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(contenidoZip);
			ZipInputStream zis = new ZipInputStream(new BufferedInputStream(bais));

			int sizeBuffer = 2048;
			ZipEntry entry = null;
			while ((entry = zis.getNextEntry()) != null) {
				if (!entry.isDirectory()) {
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					
					byte buffer[] = new byte[sizeBuffer];
					int count;
					while ((count = zis.read(buffer)) != -1) {
						baos.write(buffer, 0, count);
					}
					baos.flush();
					baos.close();
					
					String tipo = getTipoArchivo(entry.getName());
					ArchivoPropuestaDto archivoProp = new ArchivoPropuestaDto(tipo, baos.toByteArray());
					listResult.add(archivoProp);
				}else {
					throw new TechnicalException("", "");
				}
			}
			zis.close();
			
			return listResult;
		} catch (Exception e) {
			throw new TechnicalException("0808","Ocurrio un error al descomprimir el fichero ZIP");
		}
	}
}
