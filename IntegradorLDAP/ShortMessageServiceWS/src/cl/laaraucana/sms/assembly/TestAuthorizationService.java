package cl.laaraucana.sms.assembly;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cl.laaraucana.sms.domain.exchange.MessageInput;
import cl.laaraucana.sms.service.security.AuthorizationService;

@Controller
public class TestAuthorizationService {

    private static final Logger logger = Logger.getLogger(TestAuthorizationService.class);

    @RequestMapping(value = {"/authorizationService.test"}, method = RequestMethod.GET)
    public String authorizationService(@RequestBody MessageInput input) {
        try {
            AuthorizationService authorizationService = new AuthorizationService();
            boolean isUserAuthorized = authorizationService.isUserAuthorized(input.getUsername(), input.getPassword());
            logger.info(String.format("AuthorizationService.authenticate test isUserAuthorized: %s", isUserAuthorized));
        } catch (Exception e) {
            logger.error("Error processing AuthorizationService.authenticate for userId " + input.getUsername(), e);
        }
        return null;
    }
}
