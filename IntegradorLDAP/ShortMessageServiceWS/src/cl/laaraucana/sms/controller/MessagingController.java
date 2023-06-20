package cl.laaraucana.sms.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cl.laaraucana.sms.domain.exchange.MessageInput;
import cl.laaraucana.sms.domain.exchange.MessageOutput;
import cl.laaraucana.sms.domain.exchange.StatusSMSInput;
import cl.laaraucana.sms.domain.exchange.StatusSMSOutput;
import cl.laaraucana.sms.domain.exchange.StatusURLInput;
import cl.laaraucana.sms.domain.exchange.StatusURLOutput;
import cl.laaraucana.sms.service.remote.SendSMSService;
import cl.laaraucana.sms.service.remote.StatusSMSService;
import cl.laaraucana.sms.service.remote.StatusURLService;

@Controller
public class MessagingController {

    private static final Logger logger = Logger.getLogger(MessagingController.class);

    @RequestMapping(value = {"/send-sms.do"}, method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String sendSMS(@RequestBody MessageInput input, HttpServletResponse httpServletResponse) {
        try {
            // Service
            SendSMSService sendSMSService = new SendSMSService();
            MessageOutput output = sendSMSService.sendSMS(input);

            // Response
            MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
            MediaType jsonMimeType = MediaType.APPLICATION_JSON;
            jsonConverter.write(output, jsonMimeType, new ServletServerHttpResponse(httpServletResponse));
        } catch (Exception e) {
            logger.error("Error processing sendSMS call", e);
        }
        return null;
    }

    @RequestMapping(value = {"/status-sms.do"}, method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String statusSMS(@RequestBody StatusSMSInput input, HttpServletResponse httpServletResponse) {
        try {
            // Service
            StatusSMSService statusSMSService = new StatusSMSService();
            StatusSMSOutput output = statusSMSService.statusSMS(input);

            // Response
            MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
            MediaType jsonMimeType = MediaType.APPLICATION_JSON;
            jsonConverter.write(output, jsonMimeType, new ServletServerHttpResponse(httpServletResponse));
        } catch (Exception e) {
            logger.error("Error processing statusSMS call", e);
        }
        return null;
    }

    @RequestMapping(value = {"/status-url.do"}, method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String statusURL(@RequestBody StatusURLInput input, HttpServletResponse httpServletResponse) {
        try {
            // Service
            StatusURLService statusURLService = new StatusURLService();
            StatusURLOutput output = statusURLService.statusURL(input);

            // Response
            MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
            MediaType jsonMimeType = MediaType.APPLICATION_JSON;
            jsonConverter.write(output, jsonMimeType, new ServletServerHttpResponse(httpServletResponse));
        } catch (Exception e) {
            logger.error("Error processing statusURL call", e);
        }
        return null;
    }
}