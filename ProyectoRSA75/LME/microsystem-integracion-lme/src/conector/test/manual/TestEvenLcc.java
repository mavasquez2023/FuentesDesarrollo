package conector.test.manual;

import java.util.Calendar;
import java.util.Date;

import conector.configuracion.excepciones.ConfiguracionException;
import conector.lme.ws.cliente.operador.ServiciosMultiOperador;
import conector.lme.ws.cliente.operador.excepciones.ErrorInvocacionOperadorException;
import conector.vo.SalidaLMEEvenLcc;

public class TestEvenLcc {
	
	public static void main(String[] args) throws Exception{
		
		//for (int i = 0; i < 5; i++) {
			Date now = new Date();
			String urlope = "http://172.16.137.116:9081/ServicioVordelImed/services/WsLMEInetSOAP?LMEInfSeccC";
			//String urlope = "http://localhost:9081/ImedServiciosLME/services/WsLMEInetSOAP?wsdlMPEvenLcc";
			//String urlope = EndPointUtil.getInstance().getEndPoint("4", "CONSULTA", "2");
			//System.out.println("la url es "+urlope);
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(now);
	        
			int agno = calendar.get(Calendar.YEAR);
	        int mes = calendar.get(Calendar.MONTH) + 1;
	        int dia = calendar.get(Calendar.DAY_OF_MONTH);
	        //
	        int hour = calendar.get(Calendar.HOUR_OF_DAY); 
	        int minute = calendar.get(Calendar.MINUTE); 
	        
			ServiciosMultiOperador servicio = new ServiciosMultiOperador("4", "C", "10105","fonasa", urlope, urlope);
			//try {
				//LicenciaType[] listaLicencias = servicio.consultaEventosLicencias(agno, mes, dia, hour, minute);
			long inicio = System.currentTimeMillis();
			SalidaLMEEvenLcc salidaLMEEvenLcc;
			try {
				salidaLMEEvenLcc = servicio.consultaEventosLicencias2(2013, 8, 9, hour, minute);
				System.out.println("demoro respuesta 1 "+salidaLMEEvenLcc.getTiempo1()+" demoro tiempo 2 "+salidaLMEEvenLcc.getTiempo2());
				long termino = System.currentTimeMillis();
				long demoraMiliSegundos = termino - inicio;
				System.out.println("demora en milisegundos "+demoraMiliSegundos);
				System.out.println(salidaLMEEvenLcc.getListaLicencias());
			} catch (ConfiguracionException e) {
				System.out.println("entra al primeroooooooooooooooo");
				e.printStackTrace();
			} catch (ErrorInvocacionOperadorException e) {
				System.out.println("entra al segundooooooooooooooo");
				System.out.println(e.getMessage());
				e.printStackTrace();
			} 
			System.out.println("paso los katsch");
			/*} catch (java.net.SocketTimeoutException e) {
			}*/
			
		//}
	}

}
