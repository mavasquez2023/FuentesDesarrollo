package cl.laaraucana.sms.assembly;

import cl.laaraucana.sms.domain.exchange.*;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
public class TestExchangeParseIO {

	private static final Logger logger = Logger.getLogger(TestExchangeParseIO.class);
	private static final ExchangeIO exchangeIO = new ExchangeIO();

	@RequestMapping(value = { "/parseMessageInputOutput.test" }, method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public String parseMessageInputOutput(@RequestBody MessageInput input,
										  HttpServletResponse httpServletResponse) {
		try {
			// Input (only debug)
			Gson gson = new Gson();
			String inputJson = gson.toJson(input);
			logger.info("input: " + inputJson);

			// Output (simulated)
			MessageOutput output = exchangeIO.getMessageOutput();
			String outputJson = gson.toJson(output);
			logger.info("output: " + outputJson);

			// Response
			MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
			MediaType jsonMimeType = MediaType.APPLICATION_JSON;
			jsonConverter.write(output, jsonMimeType, new ServletServerHttpResponse(httpServletResponse));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = { "/parseStatusSMSInputOutput.test" }, method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public String parseStatusSMSInputOutput(@RequestBody StatusSMSInput input,
											HttpServletResponse httpServletResponse) {
		try {
			// Input (only debug)
			Gson gson = new Gson();
			String inputJson = gson.toJson(input);
			logger.info("input: " + inputJson);

			// Output (simulated)
			StatusSMSOutput output = exchangeIO.getStatusSMSOutput();
			String outputJson = gson.toJson(output);
			logger.info("output: " + outputJson);

			// Response
			MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
			MediaType jsonMimeType = MediaType.APPLICATION_JSON;
			jsonConverter.write(output, jsonMimeType, new ServletServerHttpResponse(httpServletResponse));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = { "/parseStatusURLInputOutput.test" }, method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public String parseStatusURLInputOutput(@RequestBody StatusURLInput input,
											HttpServletResponse httpServletResponse) {
		try {
			// Input (only debug)
			Gson gson = new Gson();
			String inputJson = gson.toJson(input);
			logger.info("input: " + inputJson);

			// Output (simulated)
			StatusURLOutput output = exchangeIO.getStatusURLOutput();
			String outputJson = gson.toJson(output);
			logger.info("output: " + outputJson);

			// Response
			MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
			MediaType jsonMimeType = MediaType.APPLICATION_JSON;
			jsonConverter.write(output, jsonMimeType, new ServletServerHttpResponse(httpServletResponse));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
