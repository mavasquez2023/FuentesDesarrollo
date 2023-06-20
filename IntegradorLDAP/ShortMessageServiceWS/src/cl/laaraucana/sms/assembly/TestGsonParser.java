package cl.laaraucana.sms.assembly;

import cl.laaraucana.sms.domain.altera.StatusSMSResponse;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class TestGsonParser {
	private static final Logger logger = Logger.getLogger(TestGsonParser.class);

	@RequestMapping(value = { "/fromJson.test" }, method = RequestMethod.GET)
	public String fromJson() {
		try {
			String response = " {\r\n" +
					"    \"success\": true,\r\n" +
					"    \"data\": {\r\n" +
					"        \"message\": \"Hola! Somos La Araucana\",\r\n" +
					"        \"tag\": null,\r\n" +
					"        \"code\": \"wcgU5-24-TEST\",\r\n" +
					"        \"to\": \"56912345678\",\r\n" +
					"        \"status\": \"Enviado\",\r\n" +
					"        \"callback\": null,\r\n" +
					"        \"sent_at\": \"2020-08-21 00:38:39\",\r\n" +
					"        \"received_at\": \"2020-08-21 00:38:39\",\r\n" +
					"        \"error\": null\r\n" +
					"    }\r\n" +
					"}";
			
			response = response.replace("sent_at", "sentAt");
			response = response.replace("received_at", "receivedAt");
			StatusSMSResponse statusSMSResponse = new Gson().fromJson(response, StatusSMSResponse.class);
			logger.info("TestGsonParser.fromJson, StatusSMSResponse StatusSMSData status: " + statusSMSResponse.getData().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}