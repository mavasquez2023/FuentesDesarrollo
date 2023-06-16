package cl.araucana.sivegam.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author giftsam
 */
public class ExcelSheetReader {
    /**
     * This method is used to read the data's from an excel file.
     * 
     * @param fileName -
     *            Name of the excel file.
     */

    public List readExcelFile(String fileName) {
        /**
         * Create a new instance for cellDataList
         */
        List cellDataList = new ArrayList();
        try {
            /**
             * Create a new instance for FileInputStream class
             */
            FileInputStream fileInputStreamHss = new FileInputStream(fileName);
            /**
             * Create a new instance for POIFSFileSystem class
             */

            HSSFWorkbook workBook = new HSSFWorkbook(fileInputStreamHss);

            HSSFSheet hssfSheet = workBook.getSheetAt(0);

            /**
             * Iterate the rows and cells of the spreadsheet to get all the
             * datas.
             */
            Iterator rowIterator = hssfSheet.rowIterator();

            while (rowIterator.hasNext()) {

                HSSFRow hssfRow = (HSSFRow) rowIterator.next();

                Iterator iterator = hssfRow.cellIterator();
                List cellTempList = new ArrayList();

                while (iterator.hasNext()) {
                    HSSFCell hssfCell = (HSSFCell) iterator.next();

                    if (hssfCell != null) {
                        if (HSSFCell.CELL_TYPE_STRING == hssfCell.getCellType()) {
                            cellTempList.add(hssfCell.getRichStringCellValue().toString());
                        } else if (HSSFCell.CELL_TYPE_BOOLEAN == hssfCell.getCellType()) {
                            if (hssfCell.getBooleanCellValue()) {
                                cellTempList.add("true");
                            } else {
                                cellTempList.add("false");
                            }
                        } else if (HSSFCell.CELL_TYPE_BLANK == hssfCell.getCellType()) {
                            cellTempList.add("");
                        } else if (HSSFCell.CELL_TYPE_NUMERIC == hssfCell.getCellType()) {
                            if (HSSFDateUtil.isCellDateFormatted(hssfCell)) {
                                cellTempList.add(new SimpleDateFormat("dd/MM/yyyy").format(hssfCell.getDateCellValue()));
                            } else {
                                cellTempList.add(new BigDecimal(hssfCell.getNumericCellValue()).toString());
                            }
                        }
                    }
                }
                cellDataList.add(cellTempList);
            }

        } catch (OfficeXmlFileException OffEx) {
            try {

                File file = new File(fileName);
                /**
                 * Create a new instance for FileInputStream class
                 */
                InputStream fileInputStreamXss = new FileInputStream(file);
                /**
                 * Create a new instance for POIFSFileSystem class
                 */
                XSSFWorkbook xssWorkBook = new XSSFWorkbook(fileInputStreamXss);

                XSSFSheet xssfSheet = xssWorkBook.getSheetAt(0);

                /**
                 * Iterate the rows and cells of the spreadsheet to get all the
                 * datas.
                 */
                Iterator rowIterator = xssfSheet.rowIterator();

                while (rowIterator.hasNext()) {

                    XSSFRow xssfRow = (XSSFRow) rowIterator.next();

                    Iterator iterator = xssfRow.cellIterator();
                    List cellTempList = new ArrayList();

                    while (iterator.hasNext()) {
                        XSSFCell xssfCell = (XSSFCell) iterator.next();
                        if (xssfCell != null) {
                            if (XSSFCell.CELL_TYPE_STRING == xssfCell.getCellType()) {
                                cellTempList.add(xssfCell.getRichStringCellValue().toString());
                            } else if (XSSFCell.CELL_TYPE_BOOLEAN == xssfCell.getCellType()) {
                                if (xssfCell.getBooleanCellValue()) {
                                    cellTempList.add("true");
                                } else {
                                    cellTempList.add("false");
                                }
                            } else if (XSSFCell.CELL_TYPE_BLANK == xssfCell.getCellType()) {
                                cellTempList.add("");
                            } else if (XSSFCell.CELL_TYPE_NUMERIC == xssfCell.getCellType()) {
                                cellTempList.add(new BigDecimal(xssfCell.getNumericCellValue()).toString());
                            }
                        }
                    }
                    cellDataList.add(cellTempList);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * Call the printToConsole method to print the cell data in the console.
         */

        return cellDataList;
    }

}
