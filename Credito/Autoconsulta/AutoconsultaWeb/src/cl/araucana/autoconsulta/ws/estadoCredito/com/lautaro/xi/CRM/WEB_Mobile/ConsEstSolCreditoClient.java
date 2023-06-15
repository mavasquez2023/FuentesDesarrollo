package cl.araucana.autoconsulta.ws.estadoCredito.com.lautaro.xi.CRM.WEB_Mobile;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsEstSolCreditoClient {

	public EstadoCreditosSalida consultarEstadoCredito(String rut) throws Exception{
		EstadoCreditosSalida salida = new EstadoCreditosSalida();
		EstadoCreditosResponse respuesta = null;
		ResourceBundle messages =  ResourceBundle.getBundle("resources.application"); 
		String endpoint = messages.getString("ws.consulta.estadosolcred");
		String userSap = messages.getString("sap.services.user");
		String passSap = messages.getString("sap.services.pass");
		try {
			EstadoCreditosOUTBindingStub stub = new EstadoCreditosOUTBindingStub();
			stub.setCachedEndpoint(endpoint);
			stub.setUsername(userSap);
			stub.setPassword(passSap);
			
			EstadoCreditosRequest request = new EstadoCreditosRequest();
			request.setRUT(rut);
			respuesta = stub.estadoCreditosOUT(request);
			
			//Mapear
			salida.setId(respuesta.getID().trim());
			salida.setNumero(respuesta.getNUMERO());
			salida.setTipo(respuesta.getTYPE());
			salida.setMensaje(respuesta.getMESSAGE());
			if(salida.getId().equals("0000") || 
					salida.getId().equals("5") || 
					salida.getId().equals("ZServicio")){
				salida.setId("5");
			}else{
				salida.setFolioCredito(extraeFolioCredito(respuesta.getMESSAGE()));
			}
			
		} catch (Exception e) {
			throw new Exception("Error al consultar estado de crédito", e);
		}
		return salida;
		
	}
	
	//Si el servicio no trae folio, se cae y despliega un mensaje de error generico
	private String extraeFolioCredito(String texto){
		Pattern pattern = Pattern.compile("\\d{1,}");
		Matcher matcher = pattern.matcher(texto);
		matcher.find();
		String salida = matcher.group();
		return salida;
	}
	
	public static void main(String[] args) throws Exception {
		ConsEstSolCreditoClient wsClient = new ConsEstSolCreditoClient();
		String[] ruts = {
				"6044640-7",
				"3582825-7",
				"9099436-0",
				"5099514-3",
				"6644149-0",
				"17077415-9",
				"2443750-7",
				"6688161-K",
				"18147606-0",
				"3396590-7",
				"8822399-3",
				"8948660-2",
				"4032398-8",
				"6232832-0",
				"15669467-3",
				"6645994-2",
				"8128887-9",
				"16902232-1",
				"11120266-4",
				"9271531-0",
				"13972542-5",
				"18495286-6",
				"10840057-9",
				"15563971-7",
				"11438047-4",
				"3909641-2",
				"11723246-8",
				"4628503-4",
				"10958650-1",
				"17342434-5",
				"4064091-6",
				"14437127-5",
				"8888751-4",
				"6589878-0",
				"17013847-3"
				};
		
		for (int i = 0; i < ruts.length; i++) {
			try {
				EstadoCreditosSalida wsResponse = wsClient.consultarEstadoCredito(ruts[i]);
				System.out.println("Rut: " + ruts[i]+ ", TYPE: "+wsResponse.getTipo() +  ", ID: " +wsResponse.getId() + ", MENSAJE: "+ wsResponse.getMensaje());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Rut: " + ruts[i]+ ", Respuesta con error " );
			}
		}
		
		
	}
}
