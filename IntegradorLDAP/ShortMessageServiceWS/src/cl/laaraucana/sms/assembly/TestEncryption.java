package cl.laaraucana.sms.assembly;

import cl.laaraucana.sms.utils.Encryption;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestEncryption {
    private static final Logger logger = Logger.getLogger(TestEncryption.class);
    private static final Encryption crypto = new Encryption();

    @RequestMapping(value = {"/AESEncrypt.test"}, method = RequestMethod.GET)
    public String AESEncrypt(@RequestParam(required = false) String text) {
        testAESEncrypt(text);
        return null;
    }

    @RequestMapping(value = {"/AESDecrypt.test"}, method = RequestMethod.GET)
    public String AESDecrypt() {
        testAESDecrypt();
        return null;
    }

    @RequestMapping(value = {"/base64Encode.test"}, method = RequestMethod.GET)
    public String base64Encode(@RequestParam(required = false) String text) {
        testBase64Encode(text);
        return null;
    }

    @RequestMapping(value = {"/base64Decode.test"}, method = RequestMethod.GET)
    public String base64Decode(@RequestParam(required = false) String text) {
        testBase64Decode(text);
        return null;
    }

    public void testAESEncrypt(String text) {
        if (null == text)
            text = "araucana.dev@altera.cl:9c0yDrOE7vwYSMN"; // user:pass
        String secret = crypto.AESEncrypt(text);
        logger.info("Secret: " + secret);
        logger.info(String.format("Encryption.testAESEncrypt test result: %s",
                "0El9R0DTj1euC8NkpGV2flRgP2x44xeOCLOIYA/cu81tCVeS0ZRxddUjbHFYunVq".equals(secret)));
    }

    public void testAESDecrypt() {
        String secret = "0El9R0DTj1euC8NkpGV2flRgP2x44xeOCLOIYA/cu81tCVeS0ZRxddUjbHFYunVq";
        String text = crypto.AESDecrypt(secret);
        logger.info("Text: " + text);
        logger.info(String.format("Encryption.testAESEncrypt test result: %s",
                "araucana.dev@altera.cl:9c0yDrOE7vwYSMN".contentEquals(text)));
    }

    public void testBase64Encode(String text) {
        if (null == text)
            text = "araucana.dev@altera.cl:9c0yDrOE7vwYSMN"; // user:pass
        String secret = crypto.base64Encode(text);
        logger.info("Secret: " + secret);
        logger.info(String.format("Encryption.testBase64Encode test result: %s",
                "YXJhdWNhbmEuZGV2QGFsdGVyYS5jbDo5YzB5RHJPRTd2d1lTTU4=".equals(secret)));
    }

    public void testBase64Decode(String secret) {
        if (null == secret)
            secret = "YXJhdWNhbmEuZGV2QGFsdGVyYS5jbDo5YzB5RHJPRTd2d1lTTU4=";
        String text = crypto.base64Decode(secret);
        logger.info("Text: " + text);
        logger.info(String.format("Encryption.testBase64Decode test result: %s",
                "araucana.dev@altera.cl:9c0yDrOE7vwYSMN".contentEquals(text)));
    }
}