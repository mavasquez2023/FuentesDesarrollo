package cl.laaraucana.sms.assembly;

import cl.laaraucana.sms.ibatis.model.EstadoSMS;
import cl.laaraucana.sms.domain.altera.BulkSMSResponse;
import cl.laaraucana.sms.domain.exchange.MessageInput;
import cl.laaraucana.sms.service.local.BatchReportSMSService;
import cl.laaraucana.sms.service.local.BulkSMSService;
import cl.laaraucana.sms.service.local.StatusURLServiceLocal;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Controller
public class TestMessagingService {

    private static final Logger logger = Logger.getLogger(TestMessagingService.class);

    @RequestMapping(value = {"/sendSMS.test"}, method = RequestMethod.POST, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public String sendSMS() {
        logger.info("method not implemented");
        // TODO
        return null;
    }

    @RequestMapping(value = {"/statusSMS.test"}, method = RequestMethod.POST)
    public String statusSMS() {
        logger.info("method not implemented");
        // TODO
        return null;
    }

    @RequestMapping(value = {"/statusURL.test"}, method = RequestMethod.GET)
    public String statusURL(HttpServletResponse httpServletResponse) {
        logger.info("TestMessagingService.statusURL");
        // if (null == id)
        String urlKey = "40folTnRYU1E=";
        String usr = "ASSEMBLER";

        EstadoSMS estadoSMS = new EstadoSMS();
        estadoSMS.setCodigoURL(urlKey);
        estadoSMS.setUsuario(usr);

        StatusURLServiceLocal service = new StatusURLServiceLocal();

        // StatusURLResponse output = service.saveStatusURL(estadoSMS);
        Object output = null;

        try {
            // Response
            MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
            MediaType jsonMimeType = MediaType.APPLICATION_JSON;
            jsonConverter.write(output, jsonMimeType, new ServletServerHttpResponse(httpServletResponse));
        } catch (Exception e) {
            logger.error("Error processing statusURL call", e);
        }
        return null;
    }

    @RequestMapping(value = {"/bulkSMS.test"}, method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public String bulkSMS(HttpServletResponse httpServletResponse) {
        logger.info("TestMessagingService.bulkSMS");

        ArrayList<MessageInput> messages = new ArrayList<MessageInput>();

        ExchangeIO exchange = new ExchangeIO();
        MessageInput input = exchange.getMessageInput();

        for (int i = 0; i < 2; i++) {
            // Debug
            String testMessage = String.format("%s [test: %s]", input.getMessage(), i);
            logger.info(String.format("Adding test message to Send Bulk SMS %s", testMessage));

            // ...
            MessageInput message = new MessageInput();
            message.setPhone(input.getPhone());
            message.setMessage(testMessage);
            messages.add(message);
        }

        BulkSMSService service = new BulkSMSService();
        // BulkSMSResponse output = service.bulkSMS(messages);
        Object output = null;

        try {
            // Response
            MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
            MediaType jsonMimeType = MediaType.APPLICATION_JSON;
            jsonConverter.write(output, jsonMimeType, new ServletServerHttpResponse(httpServletResponse));
        } catch (Exception e) {
            logger.error("Error processing bulkSMS call", e);
        }
        return null;
    }

    @RequestMapping(value = {"/batchReportSMS.test"}, method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public String batchReportSMS(HttpServletResponse httpServletResponse) {
        logger.info("TestMessagingService.batchReportSMS");

        String batchCode = "8A2Wu7-36-107e797";

        BatchReportSMSService service = new BatchReportSMSService();
        //BulkSMSResponse output = service.batchReportSMS(batchCode);
        Object output = null;
        try {
            // Response
            MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
            MediaType jsonMimeType = MediaType.APPLICATION_JSON;
            jsonConverter.write(output, jsonMimeType, new ServletServerHttpResponse(httpServletResponse));
        } catch (Exception e) {
            logger.error("Error processing bulkSMS call", e);
        }
        return null;
    }
}
