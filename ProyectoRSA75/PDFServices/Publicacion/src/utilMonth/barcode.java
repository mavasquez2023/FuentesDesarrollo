package utilMonth;

import javax.servlet.http.HttpServlet;

import com.onbarcode.barcode.IBarcode;
import com.onbarcode.barcode.PDF417;

public class barcode extends HttpServlet{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -323082327274703891L;
	 
	
	
	public String barra(String num, String rutaBarra)
	{
	
		
String ruta= rutaBarra + "barcode.gif";
		
		try{
	PDF417 bar=new PDF417();
	
	bar.setData(num);
	
	// PDF 417 Error Correction Level
	bar.setEcl(PDF417.ECL_8);
	bar.setRowCount(30);
	bar.setColumnCount(5);
	bar.setDataMode(PDF417.M_AUTO);
	
	bar.setTruncated(true);
	
	//  Set the processTilde property to true, if you want use the tilde character "~" to specify special characters in the input data. Default is false.
	//  1-byte character: ~ddd (character value from 0 ~ 255)
	//  ASCII (with EXT): from ~000 to ~255
	//  2-byte character: ~6ddddd (character value from 0 ~ 65535)
	//  Unicode: from ~600000 to ~665535
	//  ECI: from ~7000000 to ~7999999
	bar.setProcessTilde(true);
	
	/*
	// for macro PDF 417
	barcode.setMacro(false);
	barcode.setMacroSegmentIndex(0);
	barcode.setMacroSegmentCount(0);
	barcode.setMacroFileIndex(0);
	*/
	
	// PDF-417 unit of measure for X, Y, LeftMargin, RightMargin, TopMargin, BottomMargin
	bar.setUom(IBarcode.UOM_PIXEL);
	// PDF-417 barcode module width in pixel
	bar.setX(3f);
	bar.setXtoYRatio(0.3f);
	
	bar.setLeftMargin(10f);
	bar.setRightMargin(10f);
	bar.setTopMargin(10f);
	bar.setBottomMargin(10f);
	// barcode image resolution in dpi
	bar.setResolution(72);
	
	
	
	bar.drawBarcode(ruta);
		}
	catch(Exception ex)
	{ex.printStackTrace();}
	return ruta;
	}

}
