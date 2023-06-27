package cl.araucana.personal.dao;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cl.araucana.common.BusinessException;
import cl.araucana.personal.vo.DescuentoVO;
/**
 * @author asepulveda
 *
 */
public class TxtDescuentoDAO implements DescuentoDAO {
	Logger logger = Logger.getLogger(TxtDescuentoDAO.class);
	private final static String PREFIX="TXT-";
	
/*	
	private String benefDatabase;
	private String benefJNDIDataSource;
	
	/**
	 * Constructor de DAO
	 * Recupera nombre de Bases de Datos utilizadas
	 */

/*	 
	public TxtDescuentoDAO(){
		benefDatabase=FileSettings.getValue(AppConfig.getInstance().settingsFileName,
			 "/application-settings/databases/beneficios");
		benefJNDIDataSource=FileSettings.getValue(AppConfig.getInstance().settingsFileName,
			 "/application-settings/jdbc/beneficios");
	}
*/
		
	/**
	 * Registra los descuentos en el archivo txt especificado
	 * @param ArrayList descuento
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void insertDescuento(ArrayList descuentos, String fileName) throws Exception,BusinessException {
		
		String delimitador = "\t";
		
		//Genero Línea con encabezados
		DescuentoVO desc = new DescuentoVO();
		desc.setClEmpr("CLEMPR");
		desc.setClCoFu("CLCOFU");
		desc.setClConc("CLCONC");
		desc.setClFeMo("CLFEMO");
		desc.setClBaim("CLBAIM");
		desc.setClCant("CLCANT");
		desc.setClPort("CLPORT");
		desc.setClValo("CLVALO");
		desc.setClCeco("CLCECO");
		desc.setClDesc("CLDESC");
		desc.setClEsta("CLESTA");
		desc.setClPede("CLPEDE");
		desc.setClPeha("CLPEHA");
		desc.setClCotr("CLCOTR");
		desc.setClCoac("CLCOAC");
		desc.setClCate("CLCATE");
		desc.setClPeri("CLPERI");
		desc.setClPere("CLPERE");
		desc.setClVere("CLVERE");

		//Inserto linea de encabezado
		descuentos.add(0,desc);

		PrintWriter out=null;
		try{
			out=new PrintWriter(new FileOutputStream(fileName),true);
			
			for (int x=0;x<descuentos.size();x++) {
				DescuentoVO descuento = (DescuentoVO) descuentos.get(x);
		
				String filaDescuento = descuento.getClEmpr() + delimitador + descuento.getClCoFu() +
				delimitador + descuento.getClConc() + delimitador + descuento.getClFeMo() +
				delimitador + descuento.getClBaim() + delimitador + descuento.getClCant() +
				delimitador + descuento.getClPort() + delimitador + descuento.getClValo() +
				delimitador + descuento.getClCeco() + delimitador + descuento.getClDesc() +
				delimitador + descuento.getClEsta() + delimitador + descuento.getClPede() +
				delimitador + descuento.getClPeha() + delimitador + descuento.getClCotr() +
				delimitador + descuento.getClCoac() + delimitador + descuento.getClCate() +
				delimitador + descuento.getClPeri() + delimitador + descuento.getClPere() +
				delimitador + descuento.getClVere();
				//Registra fila de descuento en el archivo de Descuento
				out.println(filaDescuento);
			}
		}
		catch(Exception ex){
			//int code=ex.getErrorCode();
			//throw new BusinessException(PREFIX+code);
			throw new BusinessException(PREFIX);
		} finally {
			if (out != null)
				out.close();
		}
	}

}
