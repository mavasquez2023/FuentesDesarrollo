package cl.laaraucana.sms.assembly;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.net.InetSocketAddress;
import java.net.Proxy;

@Controller
public class TestOkHttpClient {

	private static final Logger logger = Logger.getLogger(TestOkHttpClient.class);

	@RequestMapping(value = { "/send-sms.test" }, method = RequestMethod.GET)
	public String alteraSendSMS(HttpServletResponse httpServletResponse) {
		MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
		MediaType jsonMimeType = MediaType.APPLICATION_JSON;
		try {
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("18.218.13.104", 8899));
			OkHttpClient client = new OkHttpClient().newBuilder().proxy(proxy).build();
			okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/json");
			okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType,
					"{\n    \"msg\": \"Hola! Somos La Araucana\",\n    \"to\": \"56990842361\"\n}");
			okhttp3.Request request = new okhttp3.Request.Builder().url("http://167.71.149.161:8687/api/sms/send-sms")
					.method("POST", body).addHeader("Content-Type", "application/json")
					.addHeader("Authorization", "Basic YXJhdWNhbmEuZGV2QGFsdGVyYS5jbDo5YzB5RHJPRTd2d1lTTU4=")
					.addHeader("Api-key", "gl7VPfR7bpvRgM9AGw5PmCIHcBRdt7LAiJl").addHeader("Accept", "application/json")
					.build();
			Response response = client.newCall(request).execute();
			String result = response.body().string();
			logger.info(result);
			jsonConverter.write(result, jsonMimeType, new ServletServerHttpResponse(httpServletResponse));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = { "/status-sms.test" }, method = RequestMethod.GET)
	public String alteraStatusSMS(HttpServletResponse httpServletResponse) {
		MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
		MediaType jsonMimeType = MediaType.APPLICATION_JSON;
		try {
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("18.218.13.104", 8899));
			OkHttpClient client = new OkHttpClient().newBuilder().proxy(proxy).build();
			okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/json");
			okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, "{\"code\": \"JBoxl-25-TEST\"}");
			okhttp3.Request request = new okhttp3.Request.Builder().url("http://167.71.149.161:8687/api/sms/status-sms")
					.method("POST", body).addHeader("Content-Type", "application/json")
					.addHeader("Authorization", "Basic YXJhdWNhbmEuZGV2QGFsdGVyYS5jbDo5YzB5RHJPRTd2d1lTTU4=")
					.addHeader("Api-key", "gl7VPfR7bpvRgM9AGw5PmCIHcBRdt7LAiJl").addHeader("Accept", "application/json")
					.build();
			Response response = client.newCall(request).execute();
			String result = response.body().string();
			logger.info(result);
			jsonConverter.write(result, jsonMimeType, new ServletServerHttpResponse(httpServletResponse));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
