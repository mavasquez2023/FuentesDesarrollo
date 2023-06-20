package cl.laaraucana.sms.assembly;

import cl.laaraucana.sms.utils.Configuration;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestMiscellaneous {

	private static final Logger logger = Logger.getLogger(TestMiscellaneous.class);

	@RequestMapping(value = { "/home.test" }, method = RequestMethod.GET)
	public String homeGet(ModelMap model) {
		model.addAttribute("message", "get");
		return "home";
	}

	@RequestMapping(value = { "/home.test" }, method = RequestMethod.POST)
	public String homePost(ModelMap model) {
		model.addAttribute("message", "post");
		return "home";
	}

	@RequestMapping(value = { "/config.test" }, method = RequestMethod.GET)
	public String config() {
		boolean proxyEnabled = Configuration.getProperty("proxy.enabled").equals("true");
		String proxyAddress = Configuration.getProperty("proxy.address");
		String proxyPort = Configuration.getProperty("proxy.port");
		logger.info("proxy enabled: " + proxyEnabled);
		logger.info("proxy address: " + proxyAddress);
		logger.info("proxy port   : " + proxyPort);
		return null;
	}
}
