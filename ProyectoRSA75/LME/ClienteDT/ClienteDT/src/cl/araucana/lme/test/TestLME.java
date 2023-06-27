/**
 * 
 */
package cl.araucana.lme.test;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WsLMEInet.LMEEvenLcc;
import WsLMEInet.LMEEvenLccResponse;
import WsLMEInet.LicenciaType;
import WsLMEInet.WsLMEInetPortTypeProxy;

/**
 * @author usist199
 *
 */
public class TestLME extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		try{
			HttpSession session = request.getSession();
			int numlicencias= runLME();
			request.setAttribute("cantidad", new Integer(numlicencias));
			String forward= "ok.jsp";
			
			request.getRequestDispatcher("/" + forward).forward(request,response);
		}catch (Exception e) {
				e.printStackTrace();
		}			
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * @param args
	 */
	public int runLME() {
		int numlicencias=0;
		String codigoOperador="3";
		String tipoInstitucion="C";
		String codigoUsuario="10105";
		String clave="petrohue";
		int year= 2015;
		int month= 11;
		int dayOfMonth=13;
		int hour=0;
		int minute= 1;
		//Locale locale= new Locale("es", "CL");
		//Calendar fecConsulta = Calendar.getInstance(TimeZone.getTimeZone("Chile/Continental"), locale);
		Calendar fecConsulta = Calendar.getInstance();
		System.out.println("TimeZone:" + fecConsulta.getTimeZone());
		System.out.println("ZONE_OFFSET:" + fecConsulta.get(Calendar.ZONE_OFFSET));
		//fecConsulta.set(Calendar.ZONE_OFFSET, Math.abs(fecConsulta.get(Calendar.ZONE_OFFSET)));
		fecConsulta.set(1, year);
		fecConsulta.set(2, month - 1);
		fecConsulta.set(5, dayOfMonth);
		fecConsulta.set(11, hour);
		fecConsulta.set(12, minute);
		LMEEvenLcc evento= new LMEEvenLcc(codigoOperador, tipoInstitucion, codigoUsuario, clave, fecConsulta);
		WsLMEInetPortTypeProxy proxy= new WsLMEInetPortTypeProxy();
		//proxy.setEndpoint("http://10.11.87.27:8080/LME/MPEvenLcc");
		System.out.println(proxy.getEndpoint());
		LMEEvenLccResponse respuesta=null;
		try {
			respuesta= proxy.LMEEvenLcc(evento);
			if(respuesta!=null){
				numlicencias= respuesta.getListaLicencias().length;
				System.out.println("Num Licencias:" + numlicencias);
			}
			LicenciaType licencia= (LicenciaType)respuesta.getListaLicencias()[0];
			System.out.println("LICENCIA:" + licencia.getNumLicencia().intValue());
			Calendar calendar= licencia.getFecha();
			//calendar.set(Calendar.ZONE_OFFSET, 14400000);
			//calendar.setTimeZone(TimeZone.getTimeZone("GMT-4:00"));
			System.out.println("YEAR:" + calendar.get(Calendar.YEAR));
			System.out.println("MONTH:" + calendar.get(Calendar.MONTH)); // Jan = 0, dec = 11
			System.out.println("DAY_OF_MONTH:" + calendar.get(Calendar.DAY_OF_MONTH)); 
			System.out.println("HOUR:" + calendar.get(Calendar.HOUR));
			System.out.println("HOUR_OF_DAY:" + calendar.get(Calendar.HOUR_OF_DAY));
			System.out.println("MINUTE:" + calendar.get(Calendar.MINUTE));
			System.out.println("ZONE_OFFSET:" + calendar.get(Calendar.ZONE_OFFSET));
			System.out.println("DST_OFFSET:" + calendar.get(Calendar.DST_OFFSET));
			System.out.println("TimeInMillis:" + calendar.getTimeInMillis());
			Date date= new Date(calendar.getTimeInMillis());
			System.out.println("Date:" + date);
			
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
/*		try {
			BigInteger bigint= new BigInteger("5551731"); 
			String dv="2";
			LMEDetLcc det= new LMEDetLcc(codigoOperador, tipoInstitucion, codigoUsuario, clave, bigint, dv);
			LMEDetLccResponse detresp= proxy.LMEDetLcc(det);
			if(detresp!=null){
				System.out.println(detresp.toString());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}*/
		System.out.println("FIN");
		return numlicencias;
		
	}
	
	public static void main(String[] args) {
		TestLME test= new TestLME();
		test.runLME();
	}
}
