/**
 * 
 */
package cl.domino.rentaspro;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

/**
 * @author usist24
 *
 */
public class MapeoRow {
	private String periodo=""; 
	private String rutemp=""; 
	private String dvrutemp=""; 
	private String rutafi=""; 
	private String dvrutafi=""; 
	private String paterno="";
	private String materno="";  
	private String nombres=""; 
	private String remmismoempleador="0"; 
	private String remotrooempleador="0"; 
	private String remindependiente="0"; 
	private String subsidio="0"; 
	private String pensiones="0"; 
	private String totalingresos="0";
	private String nummeses="0"; 
	private String prommensual="0"; 
	private String declaracion="0"; 
	private char origen; 
	private char estado;
	private String lineaTXT="";
	
	private String generarLineaTXT(){
		StringBuffer linea=new StringBuffer();
		linea.append(getPeriodo());
		String paso = "000000000" + getRutemp();
		linea.append(paso.substring(paso.length()-9,paso.length()).toUpperCase());
		paso = "0" + getDvrutemp();
		linea.append(paso.substring(paso.length()-1, paso.length()).toUpperCase());
		paso = "000000000" + getRutafi();
		linea.append(paso.substring(paso.length()-9, paso.length()).toUpperCase());
		paso = "0" + getDvrutafi();
		linea.append(paso.substring(paso.length()-1, paso.length()).toUpperCase());
		paso = getPaterno() + "               ";
		linea.append(paso.substring(0,15).toUpperCase());
		paso = getMaterno() + "               ";
		linea.append(paso.substring(0,15).toUpperCase());
		paso = getNombres() + "                    ";
		linea.append(paso.substring(0,20).toUpperCase());
		paso = "0000000" + getRemmismoempleador();
		linea.append(paso.substring(paso.length()-7, paso.length()).toUpperCase());
		paso = "0000000" + getRemotrooempleador();
		linea.append(paso.substring(paso.length()-7, paso.length()).toUpperCase());
		paso = "0000000" + getRemindependiente();
		linea.append(paso.substring(paso.length()-7, paso.length()).toUpperCase());
		paso = "0000000" + getSubsidio();
		linea.append(paso.substring(paso.length()-7, paso.length()).toUpperCase());
		paso = "0000000" + getPensiones();
		linea.append(paso.substring(paso.length()-7, paso.length()).toUpperCase());
		paso = "0000000" + getTotalingresos();
		linea.append(paso.substring(paso.length()-7, paso.length()).toUpperCase());
		paso = "00" + getNummeses();
		linea.append(paso.substring(paso.length()-2, paso.length()).toUpperCase());
		paso = "       " + getPrommensual();
		linea.append(paso.substring(paso.length()-7, paso.length()).toUpperCase());
		paso = " " + getDeclaracion();
		linea.append(paso.substring(paso.length()-1, paso.length()).toUpperCase());

		return linea.toString();
	}
	
	public String leerExcel(HSSFRow row) {
		try {
			HSSFCell cell;
//		COLUMNA A;
			cell = row.getCell( 0 );
			if(cell==null){
				return "";
			}
			if ( cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
			{	periodo = (int) cell.getNumericCellValue() + "";
			}else
			{	if ( cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
				{	periodo = cell.getRichStringCellValue().getString();
				}
			}
			if (periodo.equalsIgnoreCase("periodo") || periodo.equalsIgnoreCase("período") || periodo.equalsIgnoreCase("perido")){
				return "titulos";
			}
			if (periodo.equals("")){
				return "";
			}
			//COLUMNA B;
			cell = row.getCell( 1 );
			if ( cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
			{	rutemp = (int) cell.getNumericCellValue() + "";
			}else
			{	if ( cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
				{	rutemp = cell.getRichStringCellValue().getString().replaceAll("\\.", "");
				}
			}

			//COLUMNA C;
			cell = row.getCell( 2 );
			if ( cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
			{	dvrutemp = (int) cell.getNumericCellValue() + "";
			}else
			{	if ( cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
				{	dvrutemp = cell.getRichStringCellValue().getString();
				}
			}

			//COLUMNA D;
			cell = row.getCell( 3 );
			if ( cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
			{	rutafi = (int) cell.getNumericCellValue() + "";
			}else
			{	if ( cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
				{	rutafi = cell.getRichStringCellValue().getString().replaceAll("\\.", "");
				}
			}
											
			//COLUMNA E;
			cell = row.getCell( 4 );
			if ( cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
			{	dvrutafi = (int) cell.getNumericCellValue() + "";
			}else
			{	if ( cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
				{	dvrutafi = cell.getRichStringCellValue().getString();
				}
			}

			//COLUMNA F;
			cell = row.getCell( 5 );
			if ( cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
			{	paterno = (int) cell.getNumericCellValue() + "";
			}else
			{	if ( cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
				{	paterno = cell.getRichStringCellValue().getString();
				}
			}

			//COLUMNA G;
			cell = row.getCell( 6 );
			if(cell!= null){
			if ( cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
			{	materno = (int) cell.getNumericCellValue() + "";
			}else
			{	if ( cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
				{	materno = cell.getRichStringCellValue().getString();
				}
			}
			}
			
			//COLUMNA H;
			cell = row.getCell( 7 );
			if ( cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
			{	nombres = (int) cell.getNumericCellValue() + "";
			}else
			{	if ( cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
				{	nombres = cell.getRichStringCellValue().getString();
				}
			}
											
			//COLUMNA I;
			cell = row.getCell( 8 );
			if(cell!=null){
				if ( cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){	
					remmismoempleador = (int) cell.getNumericCellValue() + "";
				}else if ( cell.getCellType() == HSSFCell.CELL_TYPE_STRING){	
						remmismoempleador = cell.getRichStringCellValue().getString().replaceAll("\\.", "");
				}
			}

			//COLUMNA J;
			cell = row.getCell( 9 );
			if(cell!=null){
				if ( cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){	
					remotrooempleador = (int) cell.getNumericCellValue() + "";
				}else if ( cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
					remotrooempleador = cell.getRichStringCellValue().getString().replaceAll("\\.", "");
				}
			}

			//COLUMNA K;
			cell = row.getCell( 10 );
			if(cell!=null){
				if ( cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
					remindependiente = (int) cell.getNumericCellValue() + "";
				}else if ( cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
					remindependiente = cell.getRichStringCellValue().getString().replaceAll("\\.", "");
				}
			}

			//COLUMNA L;
			cell = row.getCell( 11 );
			if(cell!=null){
				if ( cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
					subsidio = (int) cell.getNumericCellValue() + "";
				}else if ( cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
					subsidio = cell.getRichStringCellValue().getString().replaceAll("\\.", "");
				}
			}

			//COLUMNA M;
			cell = row.getCell( 12 );
			if(cell!=null){
				if ( cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
					pensiones = (int) cell.getNumericCellValue() + "";
				}else if ( cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
					pensiones = cell.getRichStringCellValue().getString().replaceAll("\\.", "");
				}
			}

			//COLUMNA N;
			cell = row.getCell( 13 );
			if(cell!=null){
				if ( cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
					totalingresos = (int) cell.getNumericCellValue() + "";
				}else if ( cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
					totalingresos = cell.getRichStringCellValue().getString().replaceAll("\\.", "");
				}else if ( cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
					totalingresos = cell.getCellFormula() + "";
					if(totalingresos.length()>7){
						totalingresos= totalingresos.substring(0, 7);
					}
				}
			}

			//COLUMNA O;
			cell = row.getCell( 14 );
			if(cell!=null){
				if ( cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
					nummeses = (int) cell.getNumericCellValue() + "";
				}else if ( cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
					nummeses = cell.getRichStringCellValue().getString();
				}
			}

			//COLUMNA P;
			cell = row.getCell( 15 );
			if(cell!=null){
				if ( cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
					prommensual = (int) Math.round(cell.getNumericCellValue()) + "";
				}else if ( cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
					prommensual = cell.getRichStringCellValue().getString().replaceAll("\\.", "");
				}else if ( cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
					prommensual = cell.getCellFormula() + "";
					if(prommensual.length()>7){
						prommensual= prommensual.substring(0, 7);
					}
				}
			}

			//COLUMNA P;
			cell = row.getCell( 16 );
			if(cell!=null){
				if ( cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
					declaracion = (int) cell.getNumericCellValue() + "";
				}else if ( cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
					declaracion = cell.getRichStringCellValue().getString();
				}
			}
			setLineaTXT(generarLineaTXT());
		} catch (NullPointerException e) {
			System.out.println("Error al mapear Afiliado:" + rutafi);
			e.printStackTrace();
			return "error";
		}
		return getLineaTXT();
	}
	
	public String leerCSV(String row) {
		String[] campos= split(row, ";");
		try {
			if(campos.length==17){
				periodo=campos[0];
				if (periodo.equalsIgnoreCase("periodo") || periodo.equalsIgnoreCase("período")){
					return "titulos";
				}
				rutemp=campos[1].replaceAll("\\.", "");
				dvrutemp=campos[2];
				rutafi=campos[3].replaceAll("\\.", "");
				dvrutafi=campos[4];
				paterno=campos[5];
				materno=campos[6];
				nombres=campos[7];
				remmismoempleador=campos[8].replaceAll("\\.", "");
				remotrooempleador=campos[9].replaceAll("\\.", "");
				remindependiente=campos[10].replaceAll("\\.", "");
				subsidio=campos[11].replaceAll("\\.", "");
				pensiones=campos[12].replaceAll("\\.", "");
				totalingresos=campos[13].replaceAll("\\.", "");
				nummeses=campos[14];
				prommensual=campos[15].replaceAll("\\.", "");
				declaracion=campos[16];
				
				setLineaTXT(generarLineaTXT());
			}
			
		} catch (NullPointerException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return getLineaTXT();
	}
	
	public static String[] split(String s, String delimiter) {
		List tokens = new ArrayList();
		int fromIndex = 0;
		int index;
		int length = s.length();
		int delimiterLength = delimiter.length();

		while (fromIndex < length) {
			index = s.indexOf(delimiter, fromIndex);
			if (index < 0) {
				tokens.add(s.substring(fromIndex));
				break;
			}
			tokens.add(s.substring(fromIndex, index));
			fromIndex = index + delimiterLength;
		}
		return (String[]) tokens.toArray(new String[0]);
	}
	
	/**
	 * @return el declaracion
	 */
	public String getDeclaracion() {
		return declaracion;
	}
	/**
	 * @param declaracion el declaracion a establecer
	 */
	public void setDeclaracion(String declaracion) {
		this.declaracion = declaracion;
	}
	/**
	 * @return el dvrutemp
	 */
	public String getDvrutemp() {
		return dvrutemp;
	}
	/**
	 * @param dvrutemp el dvrutemp a establecer
	 */
	public void setDvrutemp(String dvrutemp) {
		this.dvrutemp = dvrutemp;
	}
	/**
	 * @return el dvrutafi
	 */
	public String getDvrutafi() {
		return dvrutafi;
	}
	/**
	 * @param dvrutafi el dvrutafi a establecer
	 */
	public void setDvrutafi(String dvrutafi) {
		this.dvrutafi = dvrutafi;
	}
	/**
	 * @return el estado
	 */
	public char getEstado() {
		return estado;
	}
	/**
	 * @param estado el estado a establecer
	 */
	public void setEstado(char estado) {
		this.estado = estado;
	}
	/**
	 * @return el materno
	 */
	public String getMaterno() {
		return materno;
	}
	/**
	 * @param materno el materno a establecer
	 */
	public void setMaterno(String materno) {
		this.materno = materno;
	}
	/**
	 * @return el nomtra
	 */
	public String getNombres() {
		return nombres;
	}
	/**
	 * @param nombres el nombres a establecer
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	/**
	 * @return el nummeses
	 */
	public String getNummeses() {
		return nummeses;
	}
	/**
	 * @param nummeses el nummeses a establecer
	 */
	public void setNummeses(String nummeses) {
		this.nummeses = nummeses;
	}
	/**
	 * @return el origen
	 */
	public char getOrigen() {
		return origen;
	}
	/**
	 * @param origen el origen a establecer
	 */
	public void setOrigen(char origen) {
		this.origen = origen;
	}
	/**
	 * @return el paterno
	 */
	public String getPaterno() {
		return paterno;
	}
	/**
	 * @param paterno el paterno a establecer
	 */
	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}
	/**
	 * @return el pensiones
	 */
	public String getPensiones() {
		return pensiones;
	}
	/**
	 * @param pensiones el pensiones a establecer
	 */
	public void setPensiones(String pensiones) {
		this.pensiones = pensiones;
	}
	/**
	 * @return el periodo
	 */
	public String getPeriodo() {
		return periodo;
	}
	/**
	 * @param periodo el periodo a establecer
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	/**
	 * @return el prommensual
	 */
	public String getPrommensual() {
		return prommensual;
	}
	/**
	 * @param prommensual el prommensual a establecer
	 */
	public void setPrommensual(String prommensual) {
		this.prommensual = prommensual;
	}
	/**
	 * @return el remindependiente
	 */
	public String getRemindependiente() {
		return remindependiente;
	}
	/**
	 * @param remindependiente el remindependiente a establecer
	 */
	public void setRemindependiente(String remindependiente) {
		this.remindependiente = remindependiente;
	}
	/**
	 * @return el remmismoempleador
	 */
	public String getRemmismoempleador() {
		return remmismoempleador;
	}
	/**
	 * @param remmismoempleador el remmismoempleador a establecer
	 */
	public void setRemmismoempleador(String remmismoempleador) {
		this.remmismoempleador = remmismoempleador;
	}
	/**
	 * @return el remotrooempleador
	 */
	public String getRemotrooempleador() {
		return remotrooempleador;
	}
	/**
	 * @param remotrooempleador el remotrooempleador a establecer
	 */
	public void setRemotrooempleador(String remotrooempleador) {
		this.remotrooempleador = remotrooempleador;
	}
	/**
	 * @return el rutemp
	 */
	public String getRutemp() {
		return rutemp;
	}
	/**
	 * @param rutemp el rutemp a establecer
	 */
	public void setRutemp(String rutemp) {
		this.rutemp = rutemp;
	}
	/**
	 * @return el rutafi
	 */
	public String getRutafi() {
		return rutafi;
	}
	/**
	 * @param rutafi el rutafi a establecer
	 */
	public void setRutafi(String rutafi) {
		this.rutafi = rutafi;
	}
	/**
	 * @return el subsidio
	 */
	public String getSubsidio() {
		return subsidio;
	}
	/**
	 * @param subsidio el subsidio a establecer
	 */
	public void setSubsidio(String subsidio) {
		this.subsidio = subsidio;
	}
	/**
	 * @return el totalingresos
	 */
	public String getTotalingresos() {
		return totalingresos;
	}
	/**
	 * @param totalingresos el totalingresos a establecer
	 */
	public void setTotalingresos(String totalingresos) {
		this.totalingresos = totalingresos;
	}

	/**
	 * @return el linea
	 */
	public String getLineaTXT() {
		return lineaTXT;
	}

	/**
	 * @param linea el linea a establecer
	 */
	public void setLineaTXT(String lineaTXT) {
		this.lineaTXT = lineaTXT;
	}
	
}
