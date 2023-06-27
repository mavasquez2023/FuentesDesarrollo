/**
 * 
 */
package cl.araucana.lme.test;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.Calendar;

import WsLMEInet_pkg.WsLMEInetProxy;
import WsLMEInet_pkg.LMEDetLcc;
import WsLMEInet_pkg.LMEDetLccResponse;
import WsLMEInet_pkg.LMEEvenLcc;
import WsLMEInet_pkg.LMEEvenLccResponse;

/**
 * @author usist199
 *
 */
public class TestLME {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String codigoOperador="3";
		String tipoInstitucion="C";
		String codigoUsuario="10105";
		String clave="petrohue";
		int year= 2015;
		int month= 11;
		int dayOfMonth=11;
		int hour=0;
		int minute= 1;
		Calendar fecConsulta = Calendar.getInstance();
		fecConsulta.clear();
		fecConsulta.set(1, year);
		fecConsulta.set(2, month - 1);
		fecConsulta.set(5, dayOfMonth);
		fecConsulta.set(11, hour);
		fecConsulta.set(12, minute);
		LMEEvenLcc evento= new LMEEvenLcc(codigoOperador, tipoInstitucion, codigoUsuario, clave, fecConsulta);
		WsLMEInetProxy proxy= new WsLMEInetProxy();
		System.out.println(proxy.getEndpoint());
		LMEEvenLccResponse respuesta=null;
		try {
			respuesta= proxy.LMEEvenLcc(evento);
			if(respuesta!=null){
				System.out.println("Num Licencias:" + respuesta.getListaLicencias().length);
			}
			
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		try {
			BigInteger bigint= new BigInteger("5551731"); 
			String dv="2";
			LMEDetLcc det= new LMEDetLcc(codigoOperador, tipoInstitucion, codigoUsuario, clave, bigint, dv);
			LMEDetLccResponse detresp= proxy.LMEDetLcc(det);
			if(detresp!=null){
				System.out.println(detresp.toString());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("FIN");
	}

}
