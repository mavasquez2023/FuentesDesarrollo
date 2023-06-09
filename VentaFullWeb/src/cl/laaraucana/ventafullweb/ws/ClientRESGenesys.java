/**
 * 
 */
package cl.laaraucana.ventafullweb.ws;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.lautaro.xi.CRM.Legacy.OfertasVigentes_DT;
import com.mypurecloud.sdk.v2.ApiClient;
import com.mypurecloud.sdk.v2.ApiResponse;
import com.mypurecloud.sdk.v2.extensions.AuthResponse;
import com.mypurecloud.sdk.v2.Configuration;
import com.mypurecloud.sdk.v2.api.ConversationsApi;
import com.mypurecloud.sdk.v2.model.CreateCallbackResponse;

import cl.laaraucana.ventafullweb.controller.InitController;
import cl.laaraucana.ventafullweb.util.AfiliadoUtils;
import cl.laaraucana.ventafullweb.util.Configuraciones;
import cl.laaraucana.ventafullweb.util.Utils;
import cl.laaraucana.ventafullweb.vo.AfiliadoVo;
import cl.laaraucana.ventafullweb.vo.SalidaEvaluadorModeloAISVo;

import com.mypurecloud.sdk.v2.model.ConversationTagsUpdate;
import com.mypurecloud.sdk.v2.model.CreateCallbackCommand;

/**
 * @author 13247428-1
 *
 */
public class ClientRESGenesys {
	private static final Logger logger = Logger.getLogger(ClientRESGenesys.class);
	
	public String authorization() {
		String token=null;
		try {
			String clientId = Configuraciones.getConfig("genesys.token.clientId");
			String clientSecret = Configuraciones.getConfig("genesys.token.clientSecret");
			String uri=Configuraciones.getConfig("genesys.token.url");
			logger.info("Solicitando token Genesys");
			//Set Region
			//PureCloudRegionHosts region = PureCloudRegionHosts.us_east_1;

			ApiClient apiClient = ApiClient.Builder.standard().withBasePath(uri).build();
			ApiResponse<AuthResponse> authResponse = apiClient.authorizeClientCredentials(clientId, clientSecret);

			logger.info("Respuesta: " + authResponse.getBody().toString());
			token= authResponse.getBody().getAccess_token();
			apiClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}
	
	public String authorizationURL() {
		String token="";
		try {
			String clientId = Configuraciones.getConfig("genesys.token.clientId");
			String clientSecret = Configuraciones.getConfig("genesys.token.clientSecret");
			String uri=Configuraciones.getConfig("genesys.token.url");
 
			String credenciales=clientId+":"+clientSecret;
			
			URL url = new URL(uri);
			logger.info("Conectando url: " + uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			
			String encoded= Base64.getEncoder().encodeToString(credenciales.getBytes());
			connection.setRequestProperty("Authorization", "Basic " + encoded);
			//connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Type", "text/plain");
			connection.setRequestProperty("charset", "UTF-8");
			connection.setRequestProperty("Content-Length", "0" );
			connection.setUseCaches(false);
			System.out.println("Escribiendo parametros en outputstream");
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes("");
			wr.flush();
			
			BufferedReader reader=null;
			try {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			String line;
			Object message;
			Object success;
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = null;
			if (reader!=null && (line = reader.readLine()) != null) {
				Object obj = parser.parse(line);
				jsonObject = (JSONObject) obj;
				message = jsonObject.get("access_token");
				success = jsonObject.get("expires_in");
				token= message.toString();
				//System.out.println(line);
				System.out.println("Resultado:" +token);

			}
			System.out.println("Cerrando conexión");
			wr.close();
			connection.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}
	

	    public String callback(String token, AfiliadoVo datos_afiliado, SalidaEvaluadorModeloAISVo respuestaMotorAIS, OfertasVigentes_DT respuestaWSOfertasVigentes, String fechaSeleccionada) {
	    	String idConversation=null;
	    	String queueId= Configuraciones.getConfig("genesys.callback.queueId");
        	String scriptId= Configuraciones.getConfig("genesys.callback.scriptId");
        	String uri=Configuraciones.getConfig("genesys.callback.url");
        	
        	try {
        		// Create ApiClient instance
        		logger.info("Invocando callback Genesys");
        		ApiClient apiClient = ApiClient.Builder.standard()
        				.withAccessToken(token)   // or use authorizeClientCredentials(...), authorizeCodeAuthorization(...) or authorizeSaml2Bearer(...)
        				.withBasePath(uri) // expected format: https://api.[base path]
        				.build();

        		// Use the ApiClient instance
        		Configuration.setDefaultApiClient(apiClient);
        		ConversationsApi apiInstance = new ConversationsApi();
        		CreateCallbackCommand body = new CreateCallbackCommand(); // CreateCallbackCommand | Callback

        		//Params body
        		logger.info("Seteando parámetros body");
        		List<String> callbackNumbers= new ArrayList<>();
        		callbackNumbers.add(datos_afiliado.getCelular());
        		body.callbackNumbers(callbackNumbers);
        		body.callbackUserName(datos_afiliado.getNombreAfiliado());
        		body.scriptId(scriptId);
        		body.queueId(queueId);
        		
        		String fechaSeleccionadaSinHora = "";
        		String HoraSeleccionada = "";
        		
        		if(fechaSeleccionada!=null) {
        			body.callbackScheduledTime(Utils.stringToDateTime(fechaSeleccionada));
        			String[] fechaSeleccionadaArray = fechaSeleccionada.split(" ");
            		fechaSeleccionadaSinHora = fechaSeleccionadaArray[0];
            		HoraSeleccionada = fechaSeleccionadaArray[1];
        		}
        		Map<String, String> data= new HashMap<String, String>();
        		data.put("RUT", datos_afiliado.getRutAfiliado());
        		data.put("Nombre completo afiliado", datos_afiliado.getNombreAfiliado());
        		data.put("Celular", datos_afiliado.getCelular());
        		
        		//data.put("Monto campaña referencial", "$" +AfiliadoUtils.formateaVal(datos_afiliado.getMontoCampagna()));
        		data.put("Monto campaña referencial", "");
        		
        		//data.put("Monto solicitado", "$" + AfiliadoUtils.formateaVal(datos_afiliado.getMontoSimulacion()));
        		data.put("Monto solicitado", "");
        		
        		//data.put("Nro cuotas", String.valueOf(datos_afiliado.getCuotas()));
        		data.put("Nro cuotas", "");
        		
        		//data.put("Monto cuota",  "$" + AfiliadoUtils.formateaVal(datos_afiliado.getMontoCuota()));
        		data.put("Monto cuota",  "");
        		
        		//data.put("Costo mensual seguro desgravamen",  "$" + AfiliadoUtils.formateaVal(datos_afiliado.getValorMensualSeguroDesgravamen()));
        		data.put("Costo mensual seguro desgravamen",  "");
        		
        		/*
        		if(datos_afiliado.getValorMensualSeguroCesantia()!=0) {
        			data.put("Costo mensual seguro cesantía",  "$" + AfiliadoUtils.formateaVal(datos_afiliado.getValorMensualSeguroCesantia()));
        		}
        		*/
        		data.put("Costo mensual seguro cesantía",  "");
        		
        		//data.put("Tasa interés mensual", String.valueOf(datos_afiliado.getTasaInteresMensual()) + "%");
        		data.put("Tasa interés mensual", "");
        		
        		//data.put("CAE", String.valueOf(datos_afiliado.getCae()) + "%");
        		data.put("CAE", "");
        		
        		//data.put("Impuesto (LTE)",  "$" + AfiliadoUtils.formateaVal(datos_afiliado.getImpuesto()));
        		data.put("Impuesto (LTE)",  "");
        		
        		//data.put("Gasto notarial",  "$" + AfiliadoUtils.formateaVal(datos_afiliado.getGastoNotaria()));
        		data.put("Gasto notarial",  "");
        		
        		//data.put("Costo total del crédito",  "$" + AfiliadoUtils.formateaVal(datos_afiliado.getCostoTotalCredito()));
        		data.put("Costo total del crédito",  "");
        		
        		//data.put("Fecha primer vencimiento", datos_afiliado.getPagoPrimeraCuota().replaceAll("-", "/"));
        		data.put("Fecha primer vencimiento", "");
        		
        		data.put("Fecha en que agendó", Utils.getFecha());
        		data.put("Hora en se agendó", Utils.getHora());
        		
        		// Nuevos campos para la cola de Genesys release 2
        		data.put("Fecha agenda", fechaSeleccionadaSinHora);
        		data.put("Hora agenda", HoraSeleccionada);
        		data.put("Motivo de rechazo Motor AIS", 
        				(respuestaMotorAIS == null?"":
        					(Utils.getVectorPoliticaAIS(respuestaMotorAIS)==""?"":"Politicas: " + Utils.getVectorPoliticaAIS(respuestaMotorAIS)) + 
        					(Utils.getVectorReglaNegocioAIS(respuestaMotorAIS)==""?"":"Regla Negocio: " + Utils.getVectorReglaNegocioAIS(respuestaMotorAIS))
        				));
        		data.put("Deuda alimenticia", "");
        		data.put("Nro oferta en curso", (respuestaWSOfertasVigentes==null?"":respuestaWSOfertasVigentes.getNRO_OFERTA_GENERADA()));
        		data.put("Estado oferta en curso", (respuestaWSOfertasVigentes==null?"":respuestaWSOfertasVigentes.getDESC_ESTADO_ACTUAL()));
        		data.put("Sucursal oferta en curso", (respuestaWSOfertasVigentes==null?"":respuestaWSOfertasVigentes.getDESC_SUC_OFERTA()));
        		data.put("Fecha ultimo estado oferta en curso", (respuestaWSOfertasVigentes==null?"":respuestaWSOfertasVigentes.getFCH_ESTADO_ACTUAL()));
        		
        		if(datos_afiliado.getDeclaracionJurada().equals("2")) {
        			data.put("Empresa acepta declaracion jurada", "Empresa permite declaración jurada");
        		} else {
        			data.put("Empresa acepta declaracion jurada", "Empresa no permite declaración jurada");
        		}
        		
        		body.data(data);
        		// Create a Callback
        		CreateCallbackResponse result = apiInstance.postConversationsCallbacks(body);
        		logger.info("Respuesta:" + result);
        		idConversation= result.getConversation().getId();
        		
        		//Add Conversation Tags
        		ConversationTagsUpdate bodyETags = new ConversationTagsUpdate(); // ConversationTagsUpdate | Conversation Tags
                try { 
                	bodyETags.setExternalTag(datos_afiliado.getRutAfiliado());
                	logger.info("Enviando para IdConversation: " + idConversation + ", External tag:" + bodyETags.toString());
                    // Update the tags on a conversation.
                    String resultETag = apiInstance.putConversationTags(idConversation, bodyETags);
                    logger.info("Respuesta ExternalTag:" + resultETag);
                } catch (Exception e) {
                	logger.error("Exception when calling ConversationsApi#putConversationTags");
                    e.printStackTrace();
                }
        		
        		
        		apiClient.close();
        	} catch (Exception e) {
        		System.err.println("Exception when calling ConversationsApi#postConversationsCallbacks");
        		e.printStackTrace();
        	}
	        return idConversation;
	}
}
