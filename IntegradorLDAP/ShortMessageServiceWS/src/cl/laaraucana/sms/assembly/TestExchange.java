package cl.laaraucana.sms.assembly;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
public class TestExchange {

	private static final Logger logger = Logger.getLogger(TestExchange.class);
	private static final ExchangeIO exchangeIO = new ExchangeIO();

	@RequestMapping(value = { "/exchangeMessageInput.test" }, method = RequestMethod.GET)
	public String exchangeMessageInput(HttpServletResponse response) {
		try {
			MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
			MediaType jsonMimeType = MediaType.APPLICATION_JSON;
			jsonConverter.write(exchangeIO.getMessageInput(), jsonMimeType, new ServletServerHttpResponse(response));
		} catch (Exception e) {
			logger.error("Error processing exchangeMessageInput", e);
		}
		return null;
	}

	@RequestMapping(value = { "/exchangeMessageOutput.test" }, method = RequestMethod.GET)
	public String exchangeMessageOutput(HttpServletResponse response) {
		try {
			MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
			MediaType jsonMimeType = MediaType.APPLICATION_JSON;
			jsonConverter.write(exchangeIO.getMessageOutput(), jsonMimeType, new ServletServerHttpResponse(response));
		} catch (Exception e) {
			logger.error("Error processing exchangeMessageOutput", e);
		}
		return null;
	}
}