package cl.laaraucana.simat.documentos.InformeFinanciero;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cl.laaraucana.simat.entidades.BalanceGeneral;
import cl.laaraucana.simat.utiles.EscritorArchivoSimple;
import cl.laaraucana.simat.utiles.LectorPropiedades;

public class LectorBalanceExcel {

	public ArrayList readWorkbook(String nameFile, String rutaIFOrigen) throws Exception {
		ArrayList valoresBG = new ArrayList();
		BalanceGeneral bg = null;
		/*obtencion ruta temporal para copiar ahi el balance general.*/
		LectorPropiedades lp = new LectorPropiedades();

		//String rutaTemporal=getClass().getResource(lp.getAtributoRepositorio("rutaLocalTemporales")).getPath()+"";
		String rutaTemporal = lp.getAtributoRepositorio("rutaLocalTemporales") + nameFile;
		//rutaTemporal+=nameFile;
		System.out.println("* * * * readWorkbook * * * * *");
		//File archivoOrigen=new File(rutaTemporal);
		EscritorArchivoSimple eas = new EscritorArchivoSimple();
		//eas.writerBySamba(carpetaDestino, nombreArchivo, archivoOrigen);
		eas.getCopyByIFS(nameFile, rutaTemporal);

		InputStream input = new FileInputStream(rutaTemporal);
		HSSFWorkbook wb = new HSSFWorkbook(input);
		for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
			HSSFSheet sheet = wb.getSheetAt(sheetIndex);
			Iterator rowIter = sheet.rowIterator();
			while (rowIter.hasNext()) {
				HSSFRow row = (HSSFRow) rowIter.next();
				Iterator cellIter = row.cellIterator();
				int vc = 0;
				bg = new BalanceGeneral();
				while (cellIter.hasNext()) {
					HSSFCell cell = (HSSFCell) cellIter.next();
					//como el balance trae 5 filas y necesitamos 3
					if (vc == 0) {
						bg.setCuenta(this.getCellValue(cell));
					} else if (vc == 1) {
						bg.setTexto_breve(this.getCellValue(cell));
					} else if (vc == 4) {
						bg.setResultado(this.getCellValue(cell));
					}
					vc++;
				}
				valoresBG.add(bg);
			}
		}

		return valoresBG;
	}

	public String getCellValue(HSSFCell cell) {
		String res = null;
		cell.setCellType(1);
		int cellType = cell.getCellType();
		DecimalFormat formateador = new DecimalFormat("####################");//hasta cifras de 20 digitos
		if (cellType == HSSFCell.CELL_TYPE_BOOLEAN) {
			res = String.valueOf(cell.getBooleanCellValue());
		} else if (cellType == HSSFCell.CELL_TYPE_STRING) {
			//			se utiliza decimal format por las cifras mayores a 10 digitos
			res = String.valueOf(cell.getRichStringCellValue().getString());
		} else if (cellType == HSSFCell.CELL_TYPE_FORMULA) {
			res = String.valueOf(cell.getCellFormula());
		} else if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
			//se utiliza decimal format por las cifras mayores a 10 digitos
			res = String.valueOf(formateador.format(cell.getNumericCellValue()));
		} else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
			res = String.valueOf(cell.getErrorCellValue());
		} else if (cellType == HSSFCell.CELL_TYPE_ERROR) {
			res = String.valueOf(cell.getErrorCellValue());
		}

		return res;
	}

}
